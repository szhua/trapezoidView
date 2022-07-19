package com.fencer.trapezoidview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.PathShape;
import android.util.AttributeSet;
import android.view.View;

import com.blankj.utilcode.util.ConvertUtils;


public class TrapezoidView extends View {

    private final int tx;
    private final int txColor;
    private ShapeDrawable mTrapezoid;
    private Paint pathPaint ;
    private Path pathBorder;

    public TrapezoidView(Context context, AttributeSet attrs) {
        super(context, attrs);
        final TypedArray attributes = context.getTheme()
                .obtainStyledAttributes(attrs, R.styleable.TrapezoidView, -1, 0);

        tx  = attributes.getDimensionPixelSize(R.styleable.TrapezoidView_tx_width,
                ConvertUtils.dp2px(20f));
        txColor =attributes.getColor(R.styleable.TrapezoidView_tx_color,
                context.getResources().getColor(R.color.text_main_blue));
        pathPaint= new Paint();
        pathPaint.setStrokeWidth(2);
        pathPaint.setStyle(Paint.Style.STROKE);
        pathPaint.setColor(Color.parseColor("#8EC3E6"));
        pathPaint.setAntiAlias(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightSize =MeasureSpec.getSize(heightMeasureSpec);
        int heightModel =MeasureSpec.getMode(heightMeasureSpec);

        int realHeight ;
        if (heightModel ==MeasureSpec.EXACTLY){
            realHeight = heightSize;
        }else{
            realHeight = getPaddingTop()+getPaddingBottom();
        }
        setMeasuredDimension(widthSize,realHeight);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {

        Path path = new Path();

        path.moveTo(tx, 0.0f);

        path.lineTo(0f, h);

        path.lineTo(w, h);

        path.lineTo(w -tx, 0);

        path.lineTo( tx, 0.0f);

        mTrapezoid = new ShapeDrawable(new PathShape(path, w, h));

        mTrapezoid.getPaint().setStyle(Paint.Style.FILL);

        mTrapezoid.getPaint().setColor(txColor);

        mTrapezoid.setBounds(0, 0, w, h);


        pathBorder = new Path();

        pathBorder.moveTo(0, h);

        pathBorder.lineTo(tx, 0);

        pathBorder.lineTo(w-tx, 0);

        pathBorder.lineTo(w, h);




    }

    @Override
    protected void onDraw(Canvas canvas) {



        mTrapezoid.draw(canvas);
        if (pathBorder!=null) {
            canvas.drawPath(pathBorder, pathPaint);
        }


    }

}
