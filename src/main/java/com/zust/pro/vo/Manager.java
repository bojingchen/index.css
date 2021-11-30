package com.zust.pro.vo;

/**
 * @author 86178
 */
public class Manager extends User{
    int id;
    String password;

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public Manager() {
    }
}
