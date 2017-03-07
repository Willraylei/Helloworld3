package com.example.leibin.helloworld3;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private String[] names = new String[]
            {"Lion","Tiger","Monkey","Dog","Cat","Elephant"};
    private int[] imageIds = new int[]
            { R.drawable.lion, R.drawable.tiger,
                    R.drawable.monkey,R.drawable.dog,R.drawable.cat,R.drawable.elephant};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //创建一个List，List集合元素是map
        List<Map<String,Object>> listItems = new ArrayList<Map<String,Object>>();
        for (int i = 0;i < names.length;i++)
        {
            Map<String,Object> listItem = new HashMap<String,Object>();
            listItem.put("animalName",names[i]);
            listItem.put("picture",imageIds[i]);
            listItems.add(listItem);
        }
        //创建一个 SimpleAdapter
        SimpleAdapter simpleAdapter = new SimpleAdapter(this,listItems,R.layout.simple_item,
                new String[] {"picture","animalName"},new int[]{R.id.picture,R.id.header});
        ListView list = (ListView) findViewById(R.id.mylist1);
        list.setAdapter(simpleAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            //为ListView的列表项的单击时间绑定事件监听器
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //创建一个Toast提示信息
                Toast toast = Toast.makeText(MainActivity.this,names[position],Toast.LENGTH_LONG);
                //得到当前点击的View ，将其保存在Tag信息中，设置背景色为红色；如果tag信息存在的话，说明用户又单击了其他的View，这个时候将背景色取消即可，
                if (((ListView)parent).getTag() != null){
                    ((View)((ListView)parent).getTag()).setBackgroundDrawable(null);
                }
                ((ListView)parent).setTag(view);
                parent.getChildAt(position);
                View v=parent.getChildAt(position);
                v.setBackgroundColor(Color.parseColor("#FF2341"));

                toast.show();
            }
        });
    }
}
