package com.zhoujian.application;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.header.MaterialHeader;
import in.srain.cube.views.ptr.util.PtrLocalDisplay;

public class MainActivity extends Activity {


    //下拉刷新框架android-Ultra-Pull-To-Refresh


    private boolean mImageHasLoaded = false;
    private long mStartLoadingTime = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




      Button start = (Button)findViewById(R.id.start);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                startActivity(new Intent(MainActivity.this,SecondActivity.class));
            }
        });



        final   PtrFrameLayout   mPtrFrameLayout = (PtrFrameLayout)findViewById(R.id.material_style_ptr_frame);

        // header
        final MaterialHeader header = new MaterialHeader(MainActivity.this);
        int[] colors = getResources().getIntArray(R.array.google_colors);
        header.setColorSchemeColors(colors);
        header.setLayoutParams(new PtrFrameLayout.LayoutParams(-1, -2));
        header.setPadding(0, PtrLocalDisplay.dp2px(15), 0, PtrLocalDisplay.dp2px(10));
        header.setPtrFrameLayout(mPtrFrameLayout);

        mPtrFrameLayout.setLoadingMinTime(1000);
        mPtrFrameLayout.setDurationToCloseHeader(1500);
        mPtrFrameLayout.setHeaderView(header);
        mPtrFrameLayout.addPtrUIHandler(header);
      /*  mPtrFrameLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPtrFrameLayout.autoRefresh(false);
            }
        }, 100);*/

        mPtrFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return true;
            }

            @Override
            public void onRefreshBegin(final PtrFrameLayout frame) {
                if (mImageHasLoaded) {
                    long delay = (long) (1000 + Math.random() * 2000);
                    delay = Math.max(0, delay);
                    delay = 0;
                    frame.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            frame.refreshComplete();
                        }
                    }, delay);
                } else {
                    mStartLoadingTime = System.currentTimeMillis();
                    mPtrFrameLayout.refreshComplete();
                }
            }
        });
    }

}
