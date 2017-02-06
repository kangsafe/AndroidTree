package com.recyclefloat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ks.androidtree.R;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class FloatViewActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private MyRecyclerAdapter adapter;

    /**
     * 联网请求所需的url
     */
    public String url = "http://api.meituan.com/mmdb/movie/v2/list/rt/order/coming.json?ci=1&limit=12&token=&__vhost=api.maoyan.com&utm_campaign=AmovieBmovieCD-1&movieBundleVersion=6801&utm_source=xiaomi&utm_medium=android&utm_term=6.8.0&utm_content=868030022327462&net=255&dModel=MI%205&uuid=0894DE03C76F6045D55977B6D4E32B7F3C6AAB02F9CEA042987B380EC5687C43&lat=40.100673&lng=116.378619&__skck=6a375bce8c66a0dc293860dfa83833ef&__skts=1463704714271&__skua=7e01cf8dd30a179800a7a93979b430b2&__skno=1a0b4a9b-44ec-42fc-b110-ead68bcc2824&__skcy=sXcDKbGi20CGXQPPZvhCU3%2FkzdE%3D";
    private List<NameBean> dataList = new ArrayList<>();
    private List<ComingBean> list = new ArrayList<>();

    private void setPullAction(List<ComingBean> comingslist) {
        dataList = new ArrayList<>();

        for (int i = 0; i < comingslist.size(); i++) {
            NameBean nameBean = new NameBean();
            String name0 = comingslist.get(i).getComingTitle();
            nameBean.setName(name0);
            dataList.add(nameBean);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_float_view);
        initView();
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.vrecycle);
        GridLayoutManager manager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(manager);
        //联网获取数据
        getDataFromNet();
    }


    /**
     * 使用okhttpUtils进行联网请求数据
     */
    private void getDataFromNet() {
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e) {

                    }

                    @Override
                    public void onResponse(Call call, String s) {
//联网成功后使用fastjson解析
                        Log.d("TAG", s);
                        processData(s);
                    }
//
//                    @Override
//                    public void onError(okhttp3.Call call, Exception e, int id) {
//                        Log.e("TAG", "联网失败" + e.getMessage());
//                    }
//
//                    @Override
//                    public void onResponse(String response, int id) {
//                        Log.e("TAG", "联网成功==" + response);
//
//                        //联网成功后使用fastjson解析
//                        processData(response);
//                    }
                });
    }

    /**
     * 使用fastjson进行解析
     *
     * @param json
     */
    private void processData(String json) {
        //这里使用GsonFormat生成对应的bean类
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);

//
            String data = jsonObject.getString("data");
            JSONObject dataObj = new JSONObject(data);
//
            String coming = dataObj.getString("coming");
//        List<WaitMVBean.DataBean.ComingBean> comingslist = parseArray(coming, WaitMVBean.DataBean.ComingBean.class);
            Gson g = new Gson();
            List<ComingBean> comingslist = g.fromJson(coming, new TypeToken<List<ComingBean>>() {
            }.getType());

            //解析数据成功,设置适配器-->
            setPullAction(comingslist);
            recyclerView.addItemDecoration(new SectionDecoration(dataList, this, new SectionDecoration.DecorationCallback() {
                //返回标记id (即每一项对应的标志性的字符串)
                @Override
                public String getGroupId(int position) {
                    if (dataList.get(position).getName() != null) {
                        return dataList.get(position).getName();
                    }
                    return "-1";
                }

                //获取同组中的第一个内容
                @Override
                public String getGroupFirstLine(int position) {
                    if (dataList.get(position).getName() != null) {
                        return dataList.get(position).getName();
                    }
                    return "";
                }
            }));

            adapter = new MyRecyclerAdapter(this, comingslist);
            recyclerView.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
