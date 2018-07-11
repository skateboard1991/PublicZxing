package com.skateboard.zxinglib;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class ViewFinder extends View
{

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private int strokeColor = Color.GREEN;

    private int lineColor = Color.RED;

    private int shadeColor = Color.parseColor("#08000000");

    private float strokeWidth = 12f;

    public ViewFinder(Context context)
    {
        this(context, null, 0);
    }

    public ViewFinder(Context context, @Nullable AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public ViewFinder(Context context, @Nullable AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        if (attrs != null)
        {
            parseAttrs(attrs);
        }
    }


    private void parseAttrs(AttributeSet attrs)
    {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.ViewFinder);
        lineColor = typedArray.getColor(R.styleable.ViewFinder_lineColor, Color.RED);
        strokeColor = typedArray.getColor(R.styleable.ViewFinder_strokeColor, Color.GREEN);
        strokeWidth = typedArray.getDimension(R.styleable.ViewFinder_strokeWidth, 12f);
        typedArray.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        drawShader(canvas);
        drawStroke(canvas);
        drawLine(canvas);
    }

    private void drawShader(Canvas canvas)
    {
        canvas.drawColor(shadeColor);
    }

    private void drawStroke(Canvas canvas)
    {
        canvas.save();
        paint.setColor(Color.TRANSPARENT);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP));
        int size = Math.min(getWidth(), getHeight()) / 2;
        float centerX = getWidth() / 2;
        float centerY = getHeight() / 2;
        RectF bound = new RectF(centerX - size / 2, centerY - size / 2, centerX + size / 2, centerY + size / 2);
        canvas.drawRect(bound.left, bound.top, bound.right, bound.bottom, paint);
        paint.setColor(strokeColor);
        paint.setStrokeWidth(strokeWidth);
        int strokeLength = size / 5;
        canvas.drawLine(bound.left, bound.top, bound.left + strokeLength, bound.top, paint);
        canvas.drawLine(bound.left, bound.top, bound.left, bound.top + strokeLength, paint);
        canvas.drawLine(bound.right, bound.top, bound.right - strokeLength, bound.top, paint);
        canvas.drawLine(bound.right, bound.top, bound.right, bound.top + strokeLength, paint);
        canvas.drawLine(bound.left, bound.bottom, bound.left, bound.bottom - strokeLength, paint);
        canvas.drawLine(bound.left, bound.bottom, bound.left + strokeLength, bound.bottom, paint);
        canvas.drawLine(bound.right, bound.bottom, bound.right - strokeLength, bound.bottom, paint);
        canvas.drawLine(bound.right, bound.bottom, bound.right, bound.bottom - strokeLength, paint);
        canvas.restore();
    }

    private void drawLine(Canvas canvas)
    {

    }
}
