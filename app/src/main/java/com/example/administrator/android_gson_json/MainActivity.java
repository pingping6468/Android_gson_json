package com.example.administrator.android_gson_json;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import java.util.ArrayList;



public class MainActivity extends Activity implements OnClickListener {

    private Button button1, button2;
    private static final String TAG = "MainActivity";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = (Button) this.findViewById(R.id.button1);
        button2 = (Button) this.findViewById(R.id.button2);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
    }

    //点击按钮，开始使用Gson解析Json数据：Person对象、List嵌套的Person对象的集合
    public void onClick(final View v) {
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                switch (v.getId()) {
                    case R.id.button1:
                        String path1 = "http://v.juhe.cn/joke/content/list.php?key=c037fdc5bbb9e640d6b5a701dcb11441&page=10&pageSize=10&sort=asc&time=1418745237";
                        String jsonString1 = HttpUtils.getJsonContent(path1);//从网络获取数据
                        Person person = GsonTools.getData(jsonString1, Person.class);//解析json数据
                        Log.i(TAG, person.toString());
                        //System.out.println(person.toString());
                        break;
                    case R.id.button2:
                        String path2 = "http://v.juhe.cn/joke/content/list.php?key=c037fdc5bbb9e640d6b5a701dcb11441&page=10&pageSize=10&sort=asc&time=1418745237";
                        String jsonString2 = HttpUtils.getJsonContent(path2);//从网络获取数据
                        ArrayList<Person.Result.Data> list = (ArrayList<Person.Result.Data>) GsonTools.getDatas(jsonString2, Person.Result.Data.class);//解析json数据
                        Log.i(TAG, list.toString());
                        //System.out.println(list.toString());
                        break;
                }

            }
        });
        thread.start();

    }

}