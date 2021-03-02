package com.ktc.todyinfo.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ktc.todyinfo.R;

public class HeaderBarView extends RelativeLayout {

    private ImageView img_left;
    private ImageView img_right;
    private TextView text_center;
    private RelativeLayout layout_root;
    private Context context;

    private int showView;
    private String element;

    public HeaderBarView(Context context) {
        super(context);
        this.context = context;
        initView(context);
    }

    public HeaderBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView(context);
        initAttrs(context, attrs);
    }

    public HeaderBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView(context);
        initAttrs(context, attrs);
    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.layout_view_header, this, true);
        layout_root = (RelativeLayout) findViewById(R.id.header_root_layout);
        layout_root.setBackgroundColor(Color.BLACK);
        img_left = (ImageView) findViewById(R.id.header_left_img);
        img_right = (ImageView) findViewById(R.id.header_right_img);
        text_center = (TextView) findViewById(R.id.header_center_text);
        text_center.setTextColor(Color.WHITE);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.HeaderBar);
        //获取title_text属性
        String title = mTypedArray.getString(R.styleable.HeaderBar_title_text);
        setTitle(title);
        //获取show_views属性，如果没有设置时默认为0x26
        showView = mTypedArray.getInt(R.styleable.HeaderBar_show_views, 0x26);
        text_center.setTextColor(mTypedArray.getColor(R.styleable.HeaderBar_title_text_clolor, Color.WHITE));
        mTypedArray.recycle();
        showView(showView);
    }

    private void showView(int showView) {
        //将showView转换为二进制数，根据不同位置上的值设置对应View的显示或者隐藏。
        Long data = Long.valueOf(Integer.toBinaryString(showView));
        element = String.format("%06d", data);
        for (int i = 0; i < element.length(); i++) {
            if (i == 0) ;
            if (i == 1)
                text_center.setVisibility(element.substring(i, i + 1).equals("1") ? View.VISIBLE : View.GONE);
            if (i == 2)
                img_right.setVisibility(element.substring(i, i + 1).equals("1") ? View.VISIBLE : View.GONE);
            if (i == 3) ;
            if (i == 4)
                img_left.setVisibility(element.substring(i, i + 1).equals("1") ? View.VISIBLE : View.GONE);
            if (i == 5) ;
        }
    }

    private void setTitle(String title) {
        if (!TextUtils.isEmpty(title)) {
            text_center.setText(title);
        }
    }

    public void setLeftListener(OnClickListener onClickListener) {
        img_left.setOnClickListener(onClickListener);
    }

    public void setRightListener(OnClickListener onClickListener) {
        img_right.setOnClickListener(onClickListener);
    }
}
