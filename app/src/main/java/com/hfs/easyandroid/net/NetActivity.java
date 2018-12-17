package com.hfs.easyandroid.net;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.hfs.easyandroid.R;
import com.hfs.easyhttp.EasyHttp;
import com.huantansheng.easyphotos.EasyPhotos;
import com.huantansheng.easyphotos.models.album.entity.Photo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import top.zibin.luban.Luban;


/**
 * @author HuangFusheng
 * @date 2018/12/17
 * @description NetActivity
 */
public class NetActivity extends AppCompatActivity {

    private static final int REQ_CODE = 100;

    private static final String TAG = "NetActivity";

    private static final String BASE_URL = "https://dev.yilihuo.com/";

    private static final String UPLOAD_URL = "http://t.xinhuo.com/index.php/Api/Pic/uploadPic";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net);

    }


    public void login(View view) {
        EasyHttp.getInstance()
                .postWithForm(BASE_URL + "v2/portal/login/rest/loginajax", 43343)
                .params("username", "ylhtest")
                .params("captNum", "")
                .params("password", "123qwe")
                .removeAllHeaders()
                .execute(new JSHCallBack() {
                    @Override
                    public void onLoadSuccess(int requestCode, String result) {
                        showToast(result);
                    }

                    @Override
                    public void onLoadFailure(int requestCode, String errorMsg) {
                        showToast(errorMsg);
                    }

                });
    }

    public void get(View view) {
        EasyHttp.getInstance()
                .get(BASE_URL + "ylh-cloud-service-goods-dev/api/page/sample/manager/list-base-dictionary-by-item-type", 666666)
                .params("itemType", "REASON_FOR_SAMPLE")
                .execute(new JSHCallBack() {
                    @Override
                    public void onLoadSuccess(int requestCode, String result) {
                        showToast(result);
                    }

                    @Override
                    public void onLoadFailure(int requestCode, String errorMsg) {
                        showToast(errorMsg);
                    }

                });
    }

    public void post(View view) {
        EasyHttp.getInstance()
                .post("https://dev.yilihuo.com/ylh-cloud-service-goods-dev/api/page/sample/manager/get-sample-info",454545)
                .params("barcode","CBAGDP00012312333333")
                .params("industryCode","ABA")
                .execute(new JSHCallBack() {
                    @Override
                    public void onLoadSuccess(int requestCode, String result) {
                        showToast(result);
                    }

                    @Override
                    public void onLoadFailure(int requestCode, String errorMsg) {
                        showToast(errorMsg);
                    }
                });
    }

    public void postWithJsonByParam(View view) {
        EasyHttp.getInstance()
                .postWithJson(BASE_URL + "ylh-cloud-service-goods-dev/api/page/sample/effect-picture/list-effect-picture", 66666)
                .params("picType", "3")
                .params("siteId", "111269")
                .params("industryCode", "")
                .execute(new JSHCallBack() {
                    @Override
                    public void onLoadSuccess(int requestCode, String result) {
                        showToast(result);
                    }

                    @Override
                    public void onLoadFailure(int requestCode, String errorMsg) {
                        showToast(errorMsg);
                    }
                });

    }

    public void postWithJsonByObject(View view) {

        TestBean testBean = new TestBean();
        testBean.setPicType("3");
        testBean.setSiteId("111269");
        testBean.setIndustryCode("");
        EasyHttp.getInstance()
                .postWithJson(BASE_URL + "ylh-cloud-service-goods-dev/api/page/sample/effect-picture/list-effect-picture", 66666)
                .withObject(testBean)
                .execute(new JSHCallBack() {
                    @Override
                    public void onLoadSuccess(int requestCode, String result) {
                        showToast(result);
                    }

                    @Override
                    public void onLoadFailure(int requestCode, String errorMsg) {
                        showToast(errorMsg);
                    }
                });
    }

    public void postWithJsonByJson(View view) {
        String json = "{\n" +
                "\t\"picType\": \"3\",\n" +
                "\t\"siteId\": \"111269\",\n" +
                "\t\"industryCode\": \"\"\n" +
                "}";

        EasyHttp.getInstance()
                .postWithJson(BASE_URL + "ylh-cloud-service-goods-dev/api/page/sample/effect-picture/list-effect-picture", 66666)
                .withJson(json)
                .execute(new JSHCallBack() {
                    @Override
                    public void onLoadSuccess(int requestCode, String result) {
                        showToast(result);
                    }

                    @Override
                    public void onLoadFailure(int requestCode, String errorMsg) {
                        showToast(errorMsg);
                    }
                });
    }


    public void uploadFile(View view) {

        EasyPhotos.createAlbum(this, false, GlideEngine.getInstance())
                .setCount(5)
                .start(REQ_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (RESULT_OK == resultCode) {
            //相机或相册回调
            if (requestCode == REQ_CODE) {
                //返回对象集合：如果你需要了解图片的宽、高、大小、用户是否选中原图选项等信息，可以用这个
                final ArrayList<Photo> resultPhotos = data.getParcelableArrayListExtra(EasyPhotos.RESULT_PHOTOS);

                //返回图片地址集合：如果你只需要获取图片的地址，可以用这个
                ArrayList<String> resultPaths = data.getStringArrayListExtra(EasyPhotos.RESULT_PATHS);

                List<File> files = new ArrayList<>();
                for (int i = 0; i < resultPhotos.size(); i++) {
                    Photo photo = resultPhotos.get(i);
                    files.add(new File(photo.path));
                }
                try {
                    uploadImgs(Luban.with(NetActivity.this).load(files).get());
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }



    /**
     * 一次上传多张图片
     *
     */
    private void uploadImgs(List<File> files) {


        EasyHttp.getInstance()
                .uploadFile(UPLOAD_URL, 6666,files)
                .useFileName(true)
                .execute(new JSHCallBack() {
                    @Override
                    public void onLoadSuccess(int requestCode, String result) {
                        showToast(result);
                    }

                    @Override
                    public void onLoadFailure(int requestCode, String errorMsg) {
                        showToast(errorMsg);
                    }
                });

    }



    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}
