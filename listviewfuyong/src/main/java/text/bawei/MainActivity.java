package text.bawei;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import text.bawei.adapter.MyAdapter;
import text.bawei.bean.MyData;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ListView listView;
    private List<MyData> list;
    private MyAdapter adapter;

    private Button btnHide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView) findViewById(R.id.id_listView);
        Button quanxuan = (Button) findViewById(R.id.id_quanxuan);
        Button fanxuan = (Button) findViewById(R.id.id_fanxuan);
        btnHide = (Button) findViewById(R.id.id_hide);
        btnHide.setOnClickListener(this);
        quanxuan.setOnClickListener(this);
        fanxuan.setOnClickListener(this);
        list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            MyData  data = new MyData();
            data.name = "name"+i;
            list.add(data);
        }

        adapter = new MyAdapter(this, list,false);
        listView.setAdapter(adapter);

    }
    private boolean isHide = false;
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.id_quanxuan:
                check(true);
                break;
            case R.id.id_fanxuan:
                check(false);
                break;
            case R.id.id_hide:
//                isHide = isHide==true?false:true;
                isHide = isHide==true?false:true;
                Hide(isHide);
                break;
        }
    }

    private void Hide(boolean isHide) {
        if (isHide){
            btnHide.setText("取消隐藏");
        }else {
            btnHide.setText("隐藏已选");
        }
        adapter.setHide(isHide);
        adapter.check();

    }

    private void check(boolean flag) {
        for (MyData data:list){
            data.ischeck = flag;

        }
        adapter.check();
    }
}
