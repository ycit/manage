package com.ycit.manage.util;

import com.ycit.manage.YcitException;
import com.ycit.manage.bean.vo.KeyValue;
import com.ycit.manage.bean.vo.Mail;
import com.ycit.manage.bean.vo.ProcessInfo;
import com.ycit.manage.bean.vo.ReportVo;
import com.ycit.manage.service.MailService;
import com.ycit.manage.service.SpringContext;
import org.hyperic.sigar.*;
import org.hyperic.sigar.cmd.Ps;
import org.omg.CORBA.ValueMember;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * 系统信息获取工具类
 * <p>
 * Created by xlch at 2018/5/3
 */
public class SigarUtil {

    private static Sigar sigar;

    public static Sigar getInstance() {
        if (sigar == null) {
            sigar = new Sigar();
        }
        return sigar;
    }

    public static ReportVo getCpuInfo() {
        long t1 = System.currentTimeMillis();
        double cpu = getCpuTotal();
        String value = CpuPerc.format(cpu);
        long t2 = System.currentTimeMillis();
        String time = DateUtil.longToLocalDateTime(t1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println("cost is " + (t2 - t1) + "ms");
        System.out.println("value  is" + value);
        String title = null;
        try {
            title = getInstance().getCpuInfoList()[0].getModel();
        } catch (SigarException e) {
            e.printStackTrace();
        }
        System.out.println(title);
        return new ReportVo(time, value, title);

    }

    public static double getCpuTotal() {
        CpuPerc[] cpuPercList = new CpuPerc[]{};
        try {
            cpuPercList = getInstance().getCpuPercList();
        } catch (SigarException e) {
            e.printStackTrace();
        }
        double total = 0;
        for (CpuPerc cpuPerc : cpuPercList) {
            total += cpuPerc.getCombined();
        }
        return total / cpuPercList.length;
    }

    public static ReportVo getMemoryInfo() {
        try {
            long t1 = System.currentTimeMillis();
            Mem mem = getInstance().getMem();
            float used = (float) mem.getUsed() / (float) (1024L * 1024L * 1024L);
            float total = (float) mem.getTotal() / (float) (1024L * 1024L * 1024L);
            System.out.println("total is " + String.format("%.1f", total));
            System.out.println("used is " + String.format("%.1f", used));
            String time = DateUtil.longToLocalDateTime(t1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            System.out.println("cost is " + (System.currentTimeMillis() - t1) + "ms");
            return new ReportVo(time, String.format("%.1f", used), String.format("%.1f", total));
        } catch (SigarException e) {
            e.printStackTrace();
            throw new YcitException("获取内存占用出错");
        }
    }

    public static float getMemUsed() {
        Mem mem = null;
        try {
            mem = getInstance().getMem();
        } catch (SigarException e) {
            e.printStackTrace();
        }
        return (float) mem.getUsed() / (float) (1024L * 1024L * 1024L);
    }

    public static ReportVo getDiskInfo() {
        try {
            FileSystem[] fileSystemList = getInstance().getFileSystemList();
            double total = 0;
            long t1 = System.currentTimeMillis();
            StringBuilder sb = new StringBuilder();
            for (FileSystem fileSystem : fileSystemList) {
                System.out.println("type is " + fileSystem.getType());
                if (fileSystem.getType() == 2) {
                    String devName = fileSystem.getDevName();
                    FileSystemUsage usage = getInstance().getFileSystemUsage(devName);
                    long singleUsed = usage.getUsed();
                    sb.append(devName.length() > 2 ? devName.substring(0, 2) : devName).append(String.format("%.1f", (float) singleUsed / (float) (1024L * 1024L))).append(",");
                    double usePercent = usage.getUsePercent();
                    total += usePercent;
                }
            }
            String value = CpuPerc.format(total / fileSystemList.length);
            String time = DateUtil.longToLocalDateTime(t1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            System.out.println("value is " + value);
            System.out.println("name is " + sb.toString());
            System.out.println("cost is " + (System.currentTimeMillis() - t1) + "ms");
            return new ReportVo(time, value, sb.substring(0, sb.lastIndexOf(",")));
        } catch (SigarException e) {
            e.printStackTrace();
            throw new YcitException("获取磁盘活动时间出错");
        }
    }

    public static double getDiskTotal() {
        FileSystem[] fileSystemList = new FileSystem[0];
        try {
            fileSystemList = getInstance().getFileSystemList();
        } catch (SigarException e) {
            e.printStackTrace();
        }
        double total = 0;
        for (FileSystem fileSystem : fileSystemList) {
            if (fileSystem.getType() == 2) {
                String devName = fileSystem.getDevName();
                FileSystemUsage usage = null;
                try {
                    usage = getInstance().getFileSystemUsage(devName);
                } catch (SigarException e) {
                    e.printStackTrace();
                }
                long singleUsed = usage.getUsed();
//                double usePercent = usage.getUsePercent();
                total += (double) singleUsed / (double) (1024L * 1024L);
            }
        }
        return total;
    }

    public static Map<String, String> getPsInfoTopN(int topN) {
        long cpuTotal = 0l;
        Map<String, Long> results = new HashMap();
        try {
            cpuTotal = getInstance().getCpu().getTotal();
            long[] pidArray = getInstance().getProcList();
            for (long pid : pidArray) {
                ProcCpu procCpu = null;
                try {
                    procCpu = getInstance().getProcCpu(pid);
                } catch (SigarException e) {
                    continue;
                }
                ProcState procState = null;
                procState = getInstance().getProcState(pid);
                procCpu.gather(getInstance(), pid);
                double d = procCpu.getPercent();
                String name = procState.getName() == null ? "" : procState.getName();
                long cpu = procCpu.getTotal();
                if ("idea64".equals(name)) {

                    System.out.println(name + "cpu percent==" + d);
                }
                Long value = results.get(name);
                if (value != null) {
                    cpu += value;
                }
                results.put(name, cpu);
            }
        } catch (SigarException e) {
            e.printStackTrace();
        }

        ValueComparator valueComparator = new ValueComparator(results);
        Map<String, Long> soredMap = new TreeMap<>(valueComparator);
        Map<String, String> topNPidSortedMap = new LinkedHashMap<>();
        soredMap.putAll(results);
        if (results.size() < topN) {
            topN = results.size();
        }
        Set<Map.Entry<String, Long>> entries = soredMap.entrySet();
        for (Map.Entry<String, Long> entry : entries) {
            if (topNPidSortedMap.size() > topN) {
                break;
            }
            String pt = CalculationUtil.getPt(entry.getValue(), cpuTotal);
            topNPidSortedMap.put(entry.getKey(), pt);
        }
//        System.out.println(cpuTotal);
        System.out.println(topNPidSortedMap);
        return topNPidSortedMap;
    }

    /**
     * prostate
     *
     * @param topN
     * @return
     */
    public static Map<String, Double> getPsPercentTopN(int topN) {
        long[] pidArray = new long[0];
        try {
            pidArray = getInstance().getProcList();
        } catch (SigarException e) {
            e.printStackTrace();
        }
        Map<String, Double> results = new HashMap<>();
        for (long pid : pidArray) {
            ProcCpu procCpu = new ProcCpu();
            ProcState procState = null;
            try {
                procState = getInstance().getProcState(pid);
                try {
                    procCpu.gather(getInstance(), pid);
                } catch (SigarException e) {
                    continue;
                }
            } catch (SigarException e) {
                e.printStackTrace();
            }
            if (procCpu.getTotal() != 0) {
                String name = procState.getName() == null ? "" : procState.getName();
                double cpu = procCpu.getPercent();
                Double value = results.get(name);
                if (value != null) {
                    cpu += value;
                }
                results.put(name, cpu);
                System.out.println(name + " cpu percent ===" + cpu);
            }
        }
        ValueComparator valueComparator = new ValueComparator(results);
        Map<String, Double> soredMap = new TreeMap<>(valueComparator);
        TreeMap<String, Double> topNPidSortedMap = new TreeMap<>(valueComparator);
        soredMap.putAll(results);
        if (results.size() < topN) {
            topN = results.size();
        }
        Set<Map.Entry<String, Double>> entries = soredMap.entrySet();
        for (Map.Entry<String, Double> entry : entries) {
            if (topNPidSortedMap.size() > topN) {
                break;
            }
            topNPidSortedMap.put(entry.getKey(), entry.getValue());
        }
        System.out.println(topNPidSortedMap);
        return topNPidSortedMap;
    }


    public static List<ProcessInfo> getProcessInfo() {
        Ps ps = new Ps();
        List<ProcessInfo> processInfos = new ArrayList<>();
        try {
            long[] pids = getInstance().getProcList();
            for (long pid : pids) {
                List<String> list = ps.getInfo(sigar, pid);
                ProcessInfo info = new ProcessInfo();
                for (int i = 0; i <= list.size(); i++) {
                    System.out.println(list.get(1));
                    switch (i) {
                        case 0:
                            info.setPid(list.get(0));
                            break;
                        case 1:
                            info.setUser(list.get(1));
                            break;
                        case 2:
                            info.setStartTime(list.get(2));
                            break;
                        case 3:
                            info.setMemSize(list.get(3));
                            break;
                        case 4:
                            info.setMemUse(list.get(4));
                            break;
                        case 5:
                            info.setMemhare(list.get(5));
                            break;
                        case 6:
                            info.setState(list.get(6));
                            break;
                        case 7:
                            info.setCpuTime(list.get(7));
                            break;
                        case 8:
                            info.setName(list.get(8));
                            break;
                    }
                }
                processInfos.add(info);
            }
        } catch (SigarException e) {
            e.printStackTrace();
        }
        System.out.println(processInfos);
        return processInfos;
    }


    public static void main(String[] args) {
//        System.out.println(System.getProperty("java.library.path"));
//        System.out.println(getCpuTotal());
        getPsInfoTopN(5);
//        getPsPercentTopN(5);
//        getProcessInfo();
//        getCpu();
    }

    public static void getCpu() {
        try {
//            System.out.println("manual===" + getCpuTotal());
//            CpuPerc cpuPerc = getInstance().getCpuPerc();
//            System.out.println("cpupec ==" + cpuPerc.getCombined());
            Cpu cpu = getInstance().getCpu();
            Cpu[] cpus = getInstance().getCpuList();
            CpuPerc[] cpuPercs = getInstance().getCpuPercList();
            System.out.println(getInstance().getCpu().getTotal());
            ;
        } catch (SigarException e) {
            e.printStackTrace();
        }

    }


}
