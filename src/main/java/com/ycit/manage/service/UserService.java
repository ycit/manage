package com.ycit.manage.service;

import com.ycit.manage.YcitException;
import com.ycit.manage.bean.criteria.PwForm;
import com.ycit.manage.bean.modal.User;
import com.ycit.manage.mapper.UserMapper;
import com.ycit.manage.util.ConstantDefine;
import com.ycit.manage.util.ImgUtil;
import com.ycit.manage.util.UUIDGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * 用户 service 层
 *
 * @author xlch
 * @Date 2018-03-21 12:30
 */
@Service
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private UserMapper userMapper;

    @Resource
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    public User findByIdAndUsername(int id, String username) {
        return userMapper.findByIdAndUsername(id, username);
    }


    public int deleteById(int id) {
        return userMapper.deleteById(id);
    }

    public User findById(int id) {
        return userMapper.findById(id);
    }

    public List<User> finds() {
        return userMapper.finds();
    }

    public User insert(User user) {
        userMapper.insert(user);
        return user;
    }

    public int updateImg(int id, String img) {
        return userMapper.updateImg(id, img);
    }


    public User doUploadImg(int id, MultipartFile file) {
        String uploadPath = ConstantDefine.USER_IMG_PATH;
        String absolutePath = ConstantDefine.USER_IMG_ABSOLUTE_PATH;
        if (file != null) {
            String now = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));

            String originalName = file.getOriginalFilename();
            String uuid = UUIDGenerator.getUUID();
            if (originalName.lastIndexOf('.') == -1) {
                throw new YcitException("上传文件不合法");
            }
            String newName = uuid + originalName.substring(originalName.lastIndexOf('.'), originalName.length());
            String imgPath = uploadPath + absolutePath + now;
            String imgFullPath = uploadPath + absolutePath + now + File.separator + newName;
            ImgUtil.createDir(imgPath);
            try {
                File imgSource = new File(imgFullPath);
                file.transferTo(imgSource);
            } catch (IOException e) {
                LOGGER.debug("exception", e);
            }
            String imgSavePath = File.separator + imgFullPath.substring(imgFullPath.indexOf("manage"), imgFullPath.length());
            this.updateImg(id, imgSavePath);
        }
        return this.findById(id);
    }


    public User update(User user) {
        userMapper.update(user);
        return this.findById(user.getId());
    }

    private String findPwById(int id) {
        return userMapper.findPwById(id);
    }

    private int updatePwById(int id, String pw) {
        return userMapper.updatePwById(id, pw);
    }

    public String doChangePw(PwForm form) {
        String msg = "";
        int id = form.getId();
        String oldPw = form.getOldPw();
        String newPw = form.getNewPw();
        String reNewPw = form.getReNewPw();
        String pwDb = this.findPwById(id);
        if (!pwDb.equals(oldPw)) {
            msg = "当前密码输入错误";
            return msg;
        }
        if (!newPw.equals(reNewPw)) {
            msg = "两次输入的新密码不一致";
            return msg;
        }
        int num = this.updatePwById(id, newPw);
        if (num < 1) {
            msg = "密码修改失败";
            return msg;
        }
        return msg;

    }

}
