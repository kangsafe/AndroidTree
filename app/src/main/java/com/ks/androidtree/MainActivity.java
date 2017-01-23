package com.ks.androidtree;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TreeViewAdapter treeViewAdapter = null;

    /**
     * 所有的目录列表
     */
    private List<MenuTree> menutreeList = new ArrayList<MenuTree>();
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GetData.init(this);
        listView = (ListView) findViewById(R.id.vlist);
        menutreeList = GetData.getAllFolders();

        treeViewAdapter = new TreeViewAdapter(this);
        treeViewAdapter.setAllMenu(menutreeList);

        treeViewAdapter.setListener(new OnIconListener() {
            @Override
            public void onClick(View view, int position) {

            }
        });
        listView.setAdapter(treeViewAdapter);
    }

    private boolean hasChild(List<MenuTree> list, String s) {
        boolean has = false;
        for (MenuTree m : list
                ) {
            if (m.getText().startsWith(s) && getDeep(m.getText()).length == getDeep(s).length + 1) {
                has = true;
                break;
            }
        }
        return has;
    }

    private String[] getDeep(String s) {
        if (s.startsWith("/")) {
            s = s.substring(1);
        }
        if (s.endsWith("/")) {
            s = s.substring(0, s.length() - 1);
        }
        return s.split("/");
    }

    private interface OnIconListener {
        void onClick(View view, int position);
    }

    private class TreeViewAdapter extends BaseAdapter {
        private LayoutInflater mInflater;
        private List<MenuTree> allMenu = new ArrayList<>();

        public void setListener(OnIconListener listener) {
            this.listener = listener;
        }

        private OnIconListener listener;

        public void setAllMenu(List<MenuTree> allMenu) {
            this.allMenu = allMenu;
            for (MenuTree tree : allMenu) {
                // 添加最顶层目录
                if (getDeep(tree.getText()).length == 1) {
                    mfilelist.add(tree);
                }
            }
        }

        public List<MenuTree> mfilelist = new ArrayList<>();
        private Context context;

        public TreeViewAdapter(Context context) {
            mInflater = LayoutInflater.from(context);
            this.context = context;
        }

        @Override
        public int getCount() {
            return mfilelist == null ? 0 : mfilelist.size();
        }

        @Override
        public Object getItem(int i) {
            return mfilelist.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = mInflater.inflate(R.layout.outline, null, false);
                holder.vtitle = (TextView) convertView.findViewById(R.id.text);
                holder.vicon = (ImageView) convertView.findViewById(R.id.icon);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.vicon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MenuTree cur = mfilelist.get(position);
                    if (hasChild(allMenu, cur.getText())) {
                        if (cur.isExpanded()) {// 展开状态,点击关闭
                            cur.setExpanded(false);
                            for (int i = position + 1; i < mfilelist.size(); ) {
                                if (!mfilelist.get(i).getText().startsWith(cur.getText())) {
                                    break;
                                }
                                mfilelist.remove(i);
                            }
                        } else {// 关闭状态,点击打开
                            cur.setExpanded(true);
                            int j = 1;
                            for (MenuTree mt : allMenu) {
                                if (mt.getText().startsWith(cur.getText()) && getDeep(mt.getText()).length == getDeep(cur.getText()).length + 1) {
                                    MenuTree tmp = mt;
                                    tmp.setExpanded(false);
                                    mfilelist.add(position + j, tmp);
                                    j++;
                                }
                            }
                        }
                        notifyDataSetChanged();
                    }
                }
            });
            MenuTree mt = mfilelist.get(position);
            String[] s = getDeep(mt.getText());
//            holder.vtitle.setPadding(holder.vtitle.getPaddingTop() + DPUtils.dip2px(context, 20) * (s.length - 1),
//                    holder.vtitle.getPaddingTop(), holder.vtitle.getPaddingRight(), holder.vtitle.getPaddingBottom());

            holder.vtitle.setText(s[s.length - 1]);
            if (hasChild(allMenu, mt.getText())) {
                if (mt.isExpanded()) {
                    holder.vicon.setImageResource(R.mipmap.icon_expanded_true);
                } else {
                    holder.vicon.setImageResource(R.mipmap.icon_expanded_false);
                }
                holder.vicon.setVisibility(View.VISIBLE);
            } else {
                holder.vicon.setVisibility(View.GONE);//.setImageResource(R.drawable.no_child);
            }
            if (s.length == 1) {
                holder.vtitle.setCompoundDrawables(null, null, null, null);
                holder.vtitle.setPadding(DPUtils.dip2px(context, 10), 0, 0, 0);
            } else {
                Drawable drawable = getResources().getDrawable(R.drawable.background_volume);
                // 这一步必须要做,否则不会显示.
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                holder.vtitle.setCompoundDrawables(drawable, null, null, null);
                holder.vtitle.setCompoundDrawablePadding(DPUtils.dip2px(context, 20) * (s.length - 1));
                holder.vtitle.setPadding(DPUtils.dip2px(context, 30), 0, 0, 0);
            }
            return convertView;
        }
    }

    private class ViewHolder {
        TextView vtitle;
        ImageView vicon;
    }
}
