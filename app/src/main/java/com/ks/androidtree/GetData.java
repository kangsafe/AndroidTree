package com.ks.androidtree;

/**
 * Created by Administrator on 2017/1/17.
 */

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ks.aliwufu.LuckyModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GetData {

    private static Context mContext;
    private static Gson g;

    public static void init(Context context) {
        mContext = context;
        g = new Gson();
    }

    public static BufferedReader getJsonReader(String jsonFile) {
        try {
            InputStream input = mContext.getResources().getAssets().open(jsonFile);
            BufferedReader reader = new BufferedReader(new InputStreamReader(input, "UTF-8"));
            return reader;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 描述:获得模块列表,解析出层级目录
     */
    public static List<MenuTree> getAllFolders() {
        List<MenuTree> list = new ArrayList<MenuTree>();
        try {
            Type type = new TypeToken<List<MenuTree>>() {
            }.getType();
            list = g.fromJson(getJsonReader("data/tree.json"), type);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<LuckyModel> getAllLuckyCard(){
        List<LuckyModel> list = new ArrayList<LuckyModel>();
        try {
            Type type = new TypeToken<List<LuckyModel>>() {
            }.getType();
            list = g.fromJson(getJsonReader("data/lucky.json"), type);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
