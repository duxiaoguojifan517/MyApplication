package text.bawei.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import text.bawei.R;
import text.bawei.bean.MyData;


/**
 * Created by ${Duxiaoke} on 2016/11/2.
 */
public class MyAdapter extends BaseAdapter {

    private Context context;
    private List<MyData> list= new ArrayList<>();
    private List<MyData> listAll ;

    private boolean isHide;


    public MyAdapter(Context context, List<MyData> list, boolean isHide) {
        listAll = list;
        this.list.addAll(listAll);
        this.context = context;
        this.isHide = isHide;
    }
    public void setHide(boolean isHide){
        this.isHide = isHide;
    }
    public void check() {
        list.clear();
        list.addAll(listAll);
        if (isHide) {
            List<MyData> listDel = new ArrayList<>();
            for (MyData data : list) {
                if(data.ischeck){
                    listDel.add(data);
                }
            }


            for (MyData data : listDel) {
                list.remove(data);

            }
        }
        super.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        MyData data = list.get(position);
        ViewHolder vh;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.list_item, null);
            vh = new ViewHolder();
            vh.tv1 = (TextView) convertView.findViewById(R.id.id_textView);
            vh.cb = (CheckBox) convertView.findViewById(R.id.id_checkBox);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();


        }
        vh.tv1.setText(data.name);
        vh.cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.e("check", "check>>>>>" + isChecked + "-----------------" + position);
                list.get(position).ischeck = isChecked;
                check();
            }
        });
        vh.cb.setChecked(data.ischeck);
        return convertView;
    }

    class ViewHolder {
        TextView tv1;
        CheckBox cb;
    }
}
