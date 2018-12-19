package com.hfs.easyandroid.json;

/**
 * @author HuangFusheng
 * @date 2018/12/19
 * @description JsonBean
 */
public class JsonBean {
    private String name;
    private String nickName;
    private int age;
    private long time;
    private double money;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "JsonBean{" +
                "name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                ", age=" + age +
                ", time=" + time +
                ", money=" + money +
                '}';
    }
}
