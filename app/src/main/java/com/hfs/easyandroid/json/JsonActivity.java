package com.hfs.easyandroid.json;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.hfs.easy.gson.GsonAdapter;
import com.hfs.easyandroid.R;

/**
 * @author HuangFusheng
 * @date 2018/12/19
 * @description JsonActivity
 */
public class JsonActivity extends AppCompatActivity {

    private TextView mTvJson;
    private Gson mGson;
    private JsonBean mJsonBean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);


        mTvJson = findViewById(R.id.tv_json);
        mGson=GsonAdapter.buildGson();
        mJsonBean = new JsonBean();
    }

    public void toJson(View view) {
        mJsonBean.setName("Jack");
        mJsonBean.setNickName(null);
        mJsonBean.setAge(10);
        mJsonBean.setTime(System.currentTimeMillis());
        mJsonBean.setMoney(555.5555);
        String json = mGson.toJson(mJsonBean);
        mTvJson.setText(json);
    }

    public void fromJson(View view) {

        String json = "{\n" +
                "\t\"name\": \"Jack\",\n" +
                "\t\"nickName\": null,\n" +
                "\t\"age\": null,\n" +
                "\t\"time\": null,\n" +
                "\t\"money\": 16.66\n" +
                "\n" +
                "}";

        mJsonBean = mGson.fromJson(json, JsonBean.class);

        mTvJson.setText(mJsonBean.toString());
    }
}
