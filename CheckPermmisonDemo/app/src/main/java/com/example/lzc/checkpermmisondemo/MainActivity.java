package com.example.lzc.checkpermmisondemo;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * 类描述：运行时权限测试
 * 创建人：zz
 * 创建时间：2017/2/24 10:44
 */
public class MainActivity extends Activity implements View.OnClickListener{

    private Button buttonCall;
    private static final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    /**
     * 初始化UI
     */
    private void initView() {
        buttonCall = ((Button) findViewById(R.id.button_call));
        buttonCall.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_call:
                checkPremmison();
                break;
        }
    }

    /**
     * 检查权限
     */
    private void checkPremmison() {
        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.CALL_PHONE},REQUEST_CODE);
        }else {
            call();
        }
    }

    private void call() {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:10086"));
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode){
            case REQUEST_CODE:
                   if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                       call();
                   }else {
                       Toast.makeText(getApplicationContext(),"权限没有申请",Toast.LENGTH_SHORT).show();
                   }
                break;
        }
    }
}
