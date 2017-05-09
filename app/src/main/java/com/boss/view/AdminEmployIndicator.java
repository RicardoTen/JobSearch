package com.boss.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.boss.R;

/**
 * Created by 滕新科 on 2017/5/8.
 */

public class AdminEmployIndicator extends LinearLayout implements
        View.OnClickListener {

    private int mDefaultIndicator = 0;//初始界面

    public static int mCurIndicator;

    private static View[] mIndicators;

    private AdminEmployIndicator.OnIndicateListener mOnIndicateListener;

    /**
     * 图片标记
     */
    private static final String TAG_ICON_0 = "icon_tag_0";
    private static final String TAG_ICON_1 = "icon_tag_1";
    private static final String TAG_ICON_2 = "icon_tag_2";

    /**
     * 文字标记
     */
    private static final String TAG_TEXT_0 = "text_tag_0";
    private static final String TAG_TEXT_1 = "text_tag_1";
    private static final String TAG_TEXT_2 = "text_tag_2";

    /**
     * Company底部指示器被点击时的图片
     */
    private static final Integer ICON_FOCUSE[] = { R.drawable.a,
            R.drawable.b, R.drawable.c };

    /**
     * Company底部指示器没被点击时的图片
     */
    private static final Integer ICON_NORMAL[] = { R.drawable.e,
            R.drawable.f, R.drawable.g };
    /**
     * Company底部指示器的文字
     */
    private static final String TEXT[] = { "牛人", "消息", "我的" };

    /**
     * Company底部指示器的文字大小
     */
    private static final int TEXTSIZE = 12;

    /**
     * 字体没被选中的颜色
     */
    private static final int COLOR_UNSELECT = Color.GRAY;
    /**
     *Color.parseColor("#B0E0E6")
     * 字体被选中时的颜色
     */

    private static final int COLOR_SELECT = Color.GRAY;

    private AdminEmployIndicator(Context context) {
        super(context);
    }

    public AdminEmployIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);

        mCurIndicator = mDefaultIndicator;
        setOrientation(LinearLayout.HORIZONTAL);
        init();

    }

    // put in value and param
    private View createIndicator(int iconResID, String textContent,
                                 int stringColor, String iconTag, String textTag) {

        LinearLayout view = new LinearLayout(getContext());

        view.setOrientation(LinearLayout.VERTICAL);
        view.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
        view.setGravity(Gravity.CENTER_HORIZONTAL);

        ImageView iconView = new ImageView(getContext());
        iconView.setTag(iconTag);
        iconView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
        iconView.setImageResource(iconResID);

        TextView textView = new TextView(getContext());
        textView.setTag(textTag);
        textView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1));

        textView.setTextColor(stringColor);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, TEXTSIZE);
        textView.setText(textContent);

        view.addView(iconView);
        view.addView(textView);

        return view;

    }

    // initialization indicator
    private void init() {
        mIndicators = new View[2];

        mIndicators[0] = createIndicator(ICON_FOCUSE[0],
                TEXT[0], COLOR_SELECT, TAG_ICON_0, TAG_TEXT_0);
//		mIndicators[0].setBackgroundResource(R.drawable.indic_select);
        mIndicators[0].setTag(Integer.valueOf(0));
        mIndicators[0].setOnClickListener(this);
        addView(mIndicators[0]);
        mIndicators[1] = createIndicator(ICON_NORMAL[1],
                TEXT[1], COLOR_UNSELECT, TAG_ICON_1, TAG_TEXT_1);
        mIndicators[1].setBackgroundColor(Color.alpha(0));
        mIndicators[1].setTag(Integer.valueOf(1));
        mIndicators[1].setOnClickListener(this);
        addView(mIndicators[1]);
      /*  mIndicators[2] = createIndicator(ICON_NORMAL[2],
                TEXT[2], COLOR_UNSELECT, TAG_ICON_2, TAG_TEXT_2);
        mIndicators[2].setBackgroundColor(Color.alpha(0));
        mIndicators[2].setTag(Integer.valueOf(2));
        mIndicators[2].setOnClickListener(this);
        addView(mIndicators[2]);*/

    }

    // clear previous status.
    public static void setIndicator(int which) {

        mIndicators[mCurIndicator].setBackgroundColor(Color.alpha(0));
        ImageView prevIcon;
        TextView prevText;
        switch (mCurIndicator) {
            case 0:
                prevIcon = (ImageView) mIndicators[mCurIndicator]
                        .findViewWithTag(TAG_ICON_0);

                prevIcon.setImageResource(ICON_NORMAL[0]);
                prevText = (TextView) mIndicators[mCurIndicator]
                        .findViewWithTag(TAG_TEXT_0);
                prevText.setTextColor(COLOR_UNSELECT);

                break;
            case 1:
                prevIcon = (ImageView) mIndicators[mCurIndicator]
                        .findViewWithTag(TAG_ICON_1);
                prevIcon.setImageResource(ICON_NORMAL[1]);
                prevText = (TextView) mIndicators[mCurIndicator]
                        .findViewWithTag(TAG_TEXT_1);
                prevText.setTextColor(COLOR_UNSELECT);
                break;
            case 2:
                prevIcon = (ImageView) mIndicators[mCurIndicator]
                        .findViewWithTag(TAG_ICON_2);
                prevIcon.setImageResource(ICON_NORMAL[2]);
                prevText = (TextView) mIndicators[mCurIndicator]
                        .findViewWithTag(TAG_TEXT_2);
                prevText.setTextColor(COLOR_UNSELECT);
                break;

        }

        // update current status.
        // 背景颜色
        // mIndicators[which].setBackgroundResource(R.drawable.indic_select);
        ImageView currIcon;
        TextView currText;
        switch (which) {
            case 0:
                currIcon = (ImageView) mIndicators[which]
                        .findViewWithTag(TAG_ICON_0);
                currIcon.setImageResource(ICON_FOCUSE[0]);
                currText = (TextView) mIndicators[which]
                        .findViewWithTag(TAG_TEXT_0);
                currText.setTextColor(COLOR_SELECT);

                break;
            case 1:
                currIcon = (ImageView) mIndicators[which]
                        .findViewWithTag(TAG_ICON_1);
                currIcon.setImageResource(ICON_FOCUSE[1]);
                currText = (TextView) mIndicators[which]
                        .findViewWithTag(TAG_TEXT_1);
                currText.setTextColor(COLOR_SELECT);
                break;
            case 2:
                currIcon = (ImageView) mIndicators[which]
                        .findViewWithTag(TAG_ICON_2);
                currIcon.setImageResource(ICON_FOCUSE[2]);
                currText = (TextView) mIndicators[which]
                        .findViewWithTag(TAG_TEXT_2);
                currText.setTextColor(COLOR_SELECT);
                break;

        }

        mCurIndicator = which;
    }

    // judge what your select
    public interface OnIndicateListener {
        public void onIndicate(View v, int which);
    }

    // judge what your select
    public void setOnIndicateListener(AdminEmployIndicator.OnIndicateListener listener) {
        mOnIndicateListener = listener;
    }

    // judge what your select
    public void onClick(View v) {
        if (mOnIndicateListener != null) {

            int tag = (Integer) v.getTag();
            switch (tag) {
                case 0:
                    if (mCurIndicator != 0) {
                        mOnIndicateListener.onIndicate(v, 0);
                        setIndicator(0);
                    }
                    break;
                case 1:
                    if (mCurIndicator != 1) {
                        mOnIndicateListener.onIndicate(v, 1);
                        setIndicator(1);

                    }
                    break;
                case 2:
                    if (mCurIndicator != 2) {
                        mOnIndicateListener.onIndicate(v, 2);
                        setIndicator(2);
                    }
                    break;

            }
        }
    }
}
