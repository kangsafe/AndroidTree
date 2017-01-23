package com.ks.androidtree;

/**
 * Created by Administrator on 2017/1/17.
 */

/**
 * 菜单树的各级菜单实体类
 *
 * @author tjs
 */
public class MenuTree {
    /**
     * 菜单编号
     */
    private String id;
    /**
     * 菜单内容
     */
    private String text;

    private boolean expanded = false;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }
}
