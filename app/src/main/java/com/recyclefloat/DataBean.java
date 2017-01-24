package com.recyclefloat;

import java.util.List;

/**
 * Created by Administrator on 2017/1/24.
 */


public class DataBean {
    private List<ComingBean> coming;

    private List<String> hot;

    private int[] movieIds;

    private String stid;

    public void setComing(List<ComingBean> coming) {
        this.coming = coming;
    }

    public List<ComingBean> getComing() {
        return this.coming;
    }

    public void setHot(List<String> hot) {
        this.hot = hot;
    }

    public List<String> getHot() {
        return this.hot;
    }

    public void setMovieIds(int[] movieIds) {
        this.movieIds = movieIds;
    }

    public int[] getMovieIds() {
        return this.movieIds;
    }

    public void setStid(String stid) {
        this.stid = stid;
    }

    public String getStid() {
        return this.stid;
    }
}
