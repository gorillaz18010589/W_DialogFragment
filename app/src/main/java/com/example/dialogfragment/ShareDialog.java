package com.example.dialogfragment;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShareDialog extends DialogFragment {

    private final static String ShareDialog_TAG = "ShareDialog";
    private volatile static ShareDialog shareDialog;

    public static ShareDialog getInstance() {
        if (shareDialog == null) {
            shareDialog = new ShareDialog();
        }
        return shareDialog;
    }

    public static ShareDialog getInstance(String title) {
        shareDialog = new ShareDialog();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        shareDialog.setArguments(bundle);
        return shareDialog;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //设置dialog的基本样式参数
        this.getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = this.getDialog().getWindow();
        //去掉dialog默认的padding
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        //设置dialog的位置在底部
        lp.gravity = Gravity.BOTTOM;
        //设置dialog的动画
        lp.windowAnimations = R.style.BottomToTopAnim;
        window.setAttributes(lp);
        window.setBackgroundDrawable(new ColorDrawable());

        View view = inflater.inflate(R.layout.dialog_share_bottom, container, false);
        ButterKnife.bind(this, view);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return view;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @OnClick({R.id.wechartShareLayout, R.id.circleShareLayout, R.id.cancleText})
    void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.wechartShareLayout:
                Log.e("TAG", "点击了微信分享");
                break;
            case R.id.circleShareLayout:
                Log.e("TAG", "点击了朋友圈分享");
                break;
            case R.id.cancleText:
                dismiss();
                break;
        }
    }

    public void show(FragmentManager fragmentManager) {
        show(fragmentManager, ShareDialog_TAG);
    }
}

