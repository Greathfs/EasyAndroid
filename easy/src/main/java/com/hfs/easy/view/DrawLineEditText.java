package com.hfs.easy.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.hfs.easy.R;


public class DrawLineEditText extends EditText {
    public static final int GRAVITY_LEFT = 0;
    public static final int GRAVITY_RIGHT = 1;
    public static final int GRAVITY_TOP = 2;
    public static final int GRAVITY_BOTTOM = 3;
    public static final int GRAVITY_ALL = 4;
    public static final int GRAVITY_TOP_BOTTOM = 5;
    public static final int GRAVITY_RIGHT_BOTTOM = 6;
    public static final int GRAVITY_LEFT_RIGHT_BOTTOM = 7;
    private boolean hasInitDraw;
    private Paint paint;
    /**
     * 线条颜色
     */
    private int lineColor;
    /**
     * 线条宽度
     */
    private float lineWidth;
    /**
     * 线条方向
     */
    private int lineGravity;
    /**
     * 是否处理焦点事件
     */
    private boolean dealOnTouch;
    private int width;
    private int height;
    private Context context;
    private float linePaddingLeft;
    private float leftAndRightHeight;

    public DrawLineEditText(Context context) {
        this(context, null);
    }

    public DrawLineEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init(attrs);
    }

    /**
     * 读取XML
     *
     * @param attrs
     */
    private void readXML(AttributeSet attrs) {
        TypedArray ta = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.DrawLineLayout, 0, 0);
        lineColor = ta.getColor(R.styleable.DrawLineLayout_lineColor,
                getResources().getColor(R.color.color_line_light));
        lineWidth = ta.getDimension(R.styleable.DrawLineLayout_lineWidth, 1);
        lineGravity = ta.getInt(R.styleable.DrawLineLayout_lineGravity,
                GRAVITY_BOTTOM);
        linePaddingLeft = ta.getDimension(
                R.styleable.DrawLineLayout_lineMarginLeft, 0);
        dealOnTouch = ta.getBoolean(R.styleable.DrawLineLayout_dealOnTouch,
                false);
        leftAndRightHeight = ta.getDimension(R.styleable.DrawLineLayout_left_right_height, 0);
    }

    private void init(AttributeSet attrs) {
        readXML(attrs);
        hasInitDraw = false;
        paint = new Paint();
        paint.setColor(lineColor);
        paint.setStrokeWidth(lineWidth);
        if (dealOnTouch) {
            setOnTouchListener(new OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            setLineColor(getResources()
                                    .getColor(R.color.login_color_btn));
                            break;

                        case MotionEvent.ACTION_UP:
                            setLineColor(lineColor);
                            break;
                    }
                    return false;
                }
            });
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        height = getHeight();
        width = getWidth();
        switch (lineGravity) {
            case GRAVITY_LEFT:
                drawLeftLine(canvas, height, width);
                break;
            case GRAVITY_RIGHT:
                drawRightLine(canvas, height, width);
                break;
            case GRAVITY_TOP:
                drawTopLine(canvas, height, width);
                break;
            case GRAVITY_BOTTOM:
                drawBottomLine(canvas, height, width);
                break;
            case GRAVITY_TOP_BOTTOM:
                drawTopLine(canvas, height, width);
                drawBottomLine(canvas, height, width);
                break;
            case GRAVITY_ALL:
                drawRect(canvas, height, width);
                break;
            case GRAVITY_RIGHT_BOTTOM:
                drawRightLine(canvas, height, width);
                drawBottomLine(canvas, height, width);
                break;
            case GRAVITY_LEFT_RIGHT_BOTTOM:
                drawRightLine(canvas, height, width);
                drawLeftLine(canvas, height, width);
                drawBottomLine(canvas, height, width);
                break;
        }
        super.onDraw(canvas);
    }

    public void setLinePaddingLeft(int linePaddingLeft) {
        this.linePaddingLeft = linePaddingLeft;
    }

    public void drawTopLine(Canvas canvas, int height, int width) {
        canvas.drawLine(0, 0, width, 0, paint);
    }

    public void drawBottomLine(Canvas canvas, int height, int width) {
//        height -= lineWidth;
        canvas.drawLine(linePaddingLeft, height, width, height, paint);
    }

    public void drawLeftLine(Canvas canvas, int height, int width) {

        canvas.drawLine(0, leftAndRightHeight > 0 ? (height - leftAndRightHeight) : 0, 0, height, paint);
    }

    public void drawRightLine(Canvas canvas, int height, int width) {
//        width -= lineWidth * 2;
        canvas.drawLine(width, leftAndRightHeight > 0 ? (height - leftAndRightHeight) : 0, width, height, paint);
    }

    public void drawRect(Canvas canvas, int height, int width) {
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(lineWidth, lineWidth, width - lineWidth * 2, height
                - lineWidth * 2, paint);
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    public void setLineColor(int lineColor) {
        paint.setColor(lineColor);
        invalidate();
    }

    public void setLineWidth(int lineWidth) {
        this.lineWidth = lineWidth;
    }

    public void setLineGravity(int lineGravity) {
        this.lineGravity = lineGravity;
    }
}
