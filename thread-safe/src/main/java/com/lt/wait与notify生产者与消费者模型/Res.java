package com.lt.wait与notify生产者与消费者模型;

/**
 * @Author LiTeng
 * @Date 2023/9/10 15:25
 * Version 1.0
 * @Description 共享对象
 */
public class Res {
    private String userName;

    private char sex;

    /**
     * flag 为false 输入线程 输入值
     * flag 为true 输出线程 输出值
     */
    public Boolean flag = false;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }
}
