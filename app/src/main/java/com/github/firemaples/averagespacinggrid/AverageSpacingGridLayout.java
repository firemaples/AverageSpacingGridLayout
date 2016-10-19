package com.github.firemaples.averagespacinggrid;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.GridLayout;

/**
 * Created by firemaples on 19/10/2016.
 */

public class AverageSpacingGridLayout extends GridLayout {
    public AverageSpacingGridLayout(Context context) {
        super(context);
    }

    public AverageSpacingGridLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AverageSpacingGridLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public AverageSpacingGridLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        if (getChildCount() > 0) {
            int childWidth = getChildAt(0).getMeasuredWidth();
            int totalChildWidth = childWidth * getColumnCount();
            int totalHorizontalSpacing = getMeasuredWidth() - totalChildWidth;
            int averageHorizontalSpacing = totalHorizontalSpacing / (getColumnCount() + 1);

            int childHeight = getChildAt(0).getMeasuredHeight();
            int totalChildHeight = childHeight * getRowCount();
            int totalVerticalSpacing = getMeasuredHeight() - totalChildHeight;
            int averageVerticalSpacing = totalVerticalSpacing / (getRowCount() + 1);

            for (int i = 0, count = getChildCount(); i < count; i++) {
                View c = getChildAt(i);

                int leftMargin, topMargin, rightMargin, bottomMargin;
                leftMargin = rightMargin = averageHorizontalSpacing / 2;
                topMargin = bottomMargin = averageVerticalSpacing / 2;

                double currentRow = Math.floor((double) i / (double) getColumnCount());
                double currentColumn = i % getColumnCount();

                if (currentRow == 0) {
                    //first row
                    topMargin = averageVerticalSpacing;
                } else if (currentRow == getRowCount() - 1) {
                    //last row
                    bottomMargin = averageVerticalSpacing;
                }

                if (currentColumn == 0) {
                    //first column
                    leftMargin = averageHorizontalSpacing;
                } else if (currentColumn == getColumnCount() - 1) {
                    //last column
                    rightMargin = averageHorizontalSpacing;
                }

                GridLayout.LayoutParams layoutParams = (LayoutParams) c.getLayoutParams();
                layoutParams.setMargins(leftMargin, topMargin, rightMargin, bottomMargin);
//                c.setLayoutParams(layoutParams);
            }
        }
    }
}
