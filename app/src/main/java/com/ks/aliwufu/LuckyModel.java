package com.ks.aliwufu;

/**
 * Created by Admin on 2017/1/22 0022 13:34.
 * Author: kang
 * Email: kangsafe@163.com
 */

public class LuckyModel {
    private int id;
    private String shangjia;
    private String text;
    private int type;
    private boolean isAnimPerforming = false;

    public boolean isAnimPerforming() {
        return isAnimPerforming;
    }

    public void setAnimPerforming(boolean animPerforming) {
        isAnimPerforming = animPerforming;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShangjia() {
        return shangjia;
    }

    public void setShangjia(String shangjia) {
        this.shangjia = shangjia;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
