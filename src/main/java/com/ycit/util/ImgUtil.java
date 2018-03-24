package com.ycit.util;

import com.ycit.YcitException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * 图像处理工具类
 *
 * @author xlch
 * @Date 2018-03-23 16:51
 */
public class ImgUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImgUtil.class);

    /**
     * 缩放图片方法
     * @param srcImageFile 要缩放的图片路径
     * @param storeDir 缩放后的图片保存路径
     * @param height 目标高度像素
     * @param width  目标宽度像素
     * @param bb     是否补白
     */
    public static void compress(String srcImageFile, String storeDir, int height, int width, boolean bb) {
        try {
            double ratio; // 缩放比例
            File f = new File(srcImageFile);
            BufferedImage bi = ImageIO.read(f);
            //BufferedImage.SCALE_SMOOTH  选择图像平滑度比缩放速度具有更高优先级的图像缩放算法。
            Image itemp = bi.getScaledInstance(width, height, BufferedImage.SCALE_SMOOTH);
            // 计算比例
            if ((bi.getHeight() > height) || (bi.getWidth() > width)) {
                double   ratioHeight = (Integer.valueOf(height)).doubleValue()/ bi.getHeight();
                double   ratioWhidth = (Integer.valueOf(width)).doubleValue()/ bi.getWidth();
                if(ratioHeight > ratioWhidth){
                    ratio = ratioHeight;
                }else{
                    ratio = ratioWhidth;
                }
                AffineTransformOp op = new AffineTransformOp(AffineTransform//仿射转换
                        .getScaleInstance(ratio, ratio), null);//返回表示剪切变换的变换
                itemp = op.filter(bi, null);//转换源 BufferedImage 并将结果存储在目标 BufferedImage 中。
            }
            if (bb) {//补白
                BufferedImage image = new BufferedImage(width, height,
                        BufferedImage.TYPE_INT_RGB);//构造一个类型为预定义图像类型之一的 BufferedImage。
                Graphics2D g = image.createGraphics();//创建一个 Graphics2D，可以将它绘制到此 BufferedImage 中。
                g.setColor(Color.white);//控制颜色
                g.fillRect(0, 0, width, height);// 使用 Graphics2D 上下文的设置，填充 Shape 的内部区域。
                if (width == itemp.getWidth(null))
                    g.drawImage(itemp, 0, (height - itemp.getHeight(null)) / 2,
                            itemp.getWidth(null), itemp.getHeight(null),
                            Color.white, null);
                else
                    g.drawImage(itemp, (width - itemp.getWidth(null)) / 2, 0,
                            itemp.getWidth(null), itemp.getHeight(null),
                            Color.white, null);
                g.dispose();
                itemp = image;
            }
            createDir(storeDir);
            storeDir = storeDir + File.separator + UUIDGenerator.getUUID() + ".jpg";
            ImageIO.write((BufferedImage) itemp, "JPEG", new File(storeDir));      //输出压缩图片
        } catch (IOException e) {
            LOGGER.debug("异常详情", e);
        }
    }

    public static void createDir(String dir) {
        File file = new File(dir);
        if (!file.exists()) {
            if (!file.mkdirs()) {
                throw new YcitException("创建目录出错");
            }
        } else {
            LOGGER.debug("{}地址目录已存在", dir);
        }
    }

    /**
     * 裁剪图片方法
     * @param bufferedImage 图像源
     * @param startX 裁剪开始x坐标
     * @param startY 裁剪开始y坐标
     * @param endX 裁剪结束x坐标
     * @param endY 裁剪结束y坐标
     * @return
     */
    public static BufferedImage cropImage(BufferedImage bufferedImage, int startX, int startY, int endX, int endY) {
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        if (startX == -1) {
            startX = 0;
        }
        if (startY == -1) {
            startY = 0;
        }
        if (endX == -1) {
            endX = width - 1;
        }
        if (endY == -1) {
            endY = height - 1;
        }
        BufferedImage result = new BufferedImage(endX - startX, endY - startY, 4);
        for (int x = startX; x < endX; ++x) {
            for (int y = startY; y < endY; ++y) {
                int rgb = bufferedImage.getRGB(x, y);
                result.setRGB(x - startX, y - startY, rgb);
            }
        }
        return result;
    }

    public static void cutImage(String source, String targetDir, int targetWidth, int targetHeight, String strategy)throws IOException {
        File file = new File(source);
        if (!file.exists()) {
            throw new YcitException("目标文件不存在");
        }
        BufferedImage bufferedimage=ImageIO.read(new File(source));
        int width = bufferedimage.getWidth();
        int height = bufferedimage.getHeight();
        int startX;
        int startY;
        int endX;
        int endY;
        if ("center".equals(strategy)) {
            if (targetWidth > width) {
                startX = 0;
                endX = 0;
            } else {
                startX = (width - targetWidth)/2;
                endX = targetWidth + startX;
            }
            if (targetHeight > height) {
                startY = 0;
                endY = height;
            } else {
                startY = ( height - targetHeight)/ 2;
                endY = targetHeight + startY;
            }
        } else {
            throw new YcitException("未定义的裁剪策略");
        }
        cropImage(bufferedimage, startX, startY,endX, endY);
        createDir(targetDir);
        targetDir = targetDir + File.separator + UUIDGenerator.getUUID() + ".jpg";
        ImageIO.write(bufferedimage, "jpg", new File(targetDir));
    }

    public static void main(String[] args) throws Exception{
        String source="E:\\desktop picture\\776f02ae661a2328bce237c6dd8fc95f.jpg";
        String scaleTarget = "E:\\desktop picture\\new";
        String cropTarget = "E:\\desktop picture\\new\\crop.jpg";
//        compress(source,scaleTarget, 180, 240, true);//等比例缩放  输出缩放图片
//        cutImage(source, scaleTarget, 240, 160, "center");


        File newfile=new File(source);
        BufferedImage bufferedimage=ImageIO.read(newfile);
        int width = bufferedimage.getWidth();
        int height = bufferedimage.getHeight();
        //目标将图片裁剪成 宽240，高160
        if (width > 240) {
                                                            /*开始x坐标              开始y坐标             结束x坐标                     结束y坐标*/
            bufferedimage=cropImage(bufferedimage,(int) ((width - 240) / 2),0,(int) (width - (width-240) / 2),(int) (height)
            );
            if (height > 160) {
                bufferedimage=cropImage(bufferedimage,0,(int) ((height - 160) / 2),240,(int) (height - (height - 160) / 2)
                );
            }
        }else{
            if (height > 160) {
                bufferedimage=cropImage(bufferedimage,0,(int) ((height - 160) / 2),(int) (width),(int) (height - (height - 160) / 2)
                );
            }
        }
        ImageIO.write(bufferedimage, "jpg", new File(cropTarget));    //输出裁剪图片
    }

}
