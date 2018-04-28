package com.ycit.manage.bean.criteria;

/**
 * 密码更新表单
 * <p>
 * Created by xlch at 2018/4/28
 */
public class PwForm {

    private int id;
    private String oldPw;
    private String newPw;
    private String reNewPw;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOldPw() {
        return oldPw;
    }

    public void setOldPw(String oldPw) {
        this.oldPw = oldPw;
    }

    public String getNewPw() {
        return newPw;
    }

    public void setNewPw(String newPw) {
        this.newPw = newPw;
    }

    public String getReNewPw() {
        return reNewPw;
    }

    public void setReNewPw(String reNewPw) {
        this.reNewPw = reNewPw;
    }
}
