package com.it.onex.layerdrawable;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import java.io.File;

public class MainActivity extends AppCompatActivity implements AudioRecoderUtils.OnAudioStatusUpdateListener, View.OnTouchListener {

    private AudioRecoderUtils recoderUtils;

    private Button btnStartRecord;
    private long downT;
    private AudioRecoderDialog recoderDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recoderUtils = new AudioRecoderUtils(new File(Environment.getExternalStorageDirectory() + "/recoder.amr"));
        recoderUtils.setOnAudioStatusUpdateListener(this);


        recoderDialog = new AudioRecoderDialog(this);
        recoderDialog.setShowAlpha(0.98f);

        btnStartRecord = (Button) findViewById(R.id.btn_am_record);
        btnStartRecord.setOnTouchListener(this);

//        tvRecordTime = (TextView) findViewById(R.id.tv_am_record_time);
//        ivProgress = (ImageView) findViewById(R.id.progress);
//        ivProgress.getDrawable().setLevel(7000);
    }

    @Override
    public void onUpdate(double db) {
        if(null != recoderDialog) {
            int level = (int) db;
            recoderDialog.setLevel((int)db);
            recoderDialog.setTime(System.currentTimeMillis() - downT);
        }
    }


    @Override
    public boolean onTouch(View view, MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                recoderUtils.startRecord();
                downT = System.currentTimeMillis();

                recoderDialog.showAtLocation(view, Gravity.CENTER, 0, 0);
                btnStartRecord.setBackgroundResource(R.drawable.btn_recode_normal);
                return true;

            case MotionEvent.ACTION_UP:
                recoderUtils.stopRecord();
                recoderDialog.dismiss();
                btnStartRecord.setBackgroundResource(R.drawable.btn_recode_press);
                return true;
        }
        return false;
    }
}
