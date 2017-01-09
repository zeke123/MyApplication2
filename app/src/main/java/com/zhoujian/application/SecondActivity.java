package com.zhoujian.application;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by zhoujian on 2017/1/9.
 */

public class SecondActivity extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        final PtrClassicFrameLayout ptrFrame = (PtrClassicFrameLayout) findViewById(R.id.fragment_rotate_header_with_text_view_frame);

        ptrFrame.setLastUpdateTimeRelateObject(this);
        ptrFrame.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ptrFrame.refreshComplete();
                    }
                }, 1500);
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return true;
            }
        });
    }
}
