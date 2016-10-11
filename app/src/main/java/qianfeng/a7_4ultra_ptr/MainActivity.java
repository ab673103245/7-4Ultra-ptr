package qianfeng.a7_4ultra_ptr;

import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.header.StoreHouseHeader;

public class MainActivity extends AppCompatActivity {

    private ListView lv;
    private PtrFrameLayout ptr_frameLayout;

    private List<String> list;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = ((ListView) findViewById(R.id.lv));
        ptr_frameLayout = ((PtrFrameLayout) findViewById(R.id.ptr_frameLayout));


//        // 1.经典头布局
//        PtrClassicDefaultHeader classicDefaultHeader = new PtrClassicDefaultHeader(this);
//
//        // 添加头布局
//        ptr_frameLayout.setHeaderView(classicDefaultHeader);
//
//        //让头布局和刷新状态的视图(Listview)保持一致
//        ptr_frameLayout.addPtrUIHandler(classicDefaultHeader);

        //2.MD风格的头布局
//        MaterialHeader materialHeader = new MaterialHeader(this);
//
//        materialHeader.setColorSchemeColors(new int[]{Color.RED,Color.BLUE,Color.GREEN});
//
//        ptr_frameLayout.setHeaderView(materialHeader);
//        ptr_frameLayout.addPtrUIHandler(materialHeader);




        //3.闪烁文字的头布局

        StoreHouseHeader storeHouseHeader = new StoreHouseHeader(this);

        storeHouseHeader.initWithString("loading",48);
        storeHouseHeader.setTextColor(Color.BLUE);
        storeHouseHeader.setBackgroundColor(Color.GRAY);
        ptr_frameLayout.setHeaderView(storeHouseHeader);
        ptr_frameLayout.addPtrUIHandler(storeHouseHeader);

        initData();

        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
        lv.setAdapter(adapter);

        // 设置刷新的监听
        ptr_frameLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                SystemClock.sleep(5000);
                list.add(0,"张三");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                        // 刷新完成就记得停止动画
                        ptr_frameLayout.refreshComplete();
                    }
                });
            }
        });



    }

    private void initData() {
        list = new ArrayList<>();

        for(int i =0; i < 30; i++)
        {
            list.add("王五:" + i);
        }

    }
}
