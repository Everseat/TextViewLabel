package com.everseat.textviewlabel;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.widget.TextView;

public class TextViewLabel extends TextView {
  private float cornerRadius = Integer.MIN_VALUE;
  private int labelColor = Color.TRANSPARENT;
  private RectF labelBounds = new RectF();
  private Paint labelPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

  public TextViewLabel(Context context) {
    super(context);
    init(context, null);
  }

  public TextViewLabel(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(context, attrs);
  }

  public TextViewLabel(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context, attrs);
  }

  private void init(Context context, AttributeSet attrs) {
    DisplayMetrics dm = getResources().getDisplayMetrics();

    if (attrs != null) {
      TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TextViewLabel);
      ColorStateList color = ta.getColorStateList(R.styleable.TextViewLabel_labelColor);
      if (color != null) {
        setLabelColor(color.getDefaultColor());
      }
    }

    // Default padding
    if (getPaddingLeft() == 0
        || getPaddingRight() == 0
        || getPaddingBottom() == 0
        || getPaddingTop() == 0) {
      int padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, dm);
      setPadding(padding, padding, padding, padding);
    }
  }

  /**
   * Sets the label color.
   * @param color The color of the label
   */
  public void setLabelColor(int color) {
    labelColor = color;
    invalidate();
  }

  /**
   * Retrieves the current label color.
   * @return The color of the label
   */
  public int getLabelColor() {
    return labelColor;
  }

  /**
   * Sets the corner radius on the label. By default the corner radius is 1/20th of the
   * labels width.
   * @param cornerRadius The radius of each corner
   */
  public void setCornerRadius(float cornerRadius) {
    this.cornerRadius = cornerRadius;
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    labelBounds.left = 0;
    labelBounds.right = getMeasuredWidth();
    labelBounds.top = 0;
    labelBounds.bottom = getMeasuredHeight();
  }

  @Override
  protected void onDraw(Canvas canvas) {
    if (cornerRadius == Integer.MIN_VALUE) {
      drawLabel(canvas, getWidth()/20);
    } else {
      drawLabel(canvas, cornerRadius);
    }
    super.onDraw(canvas);
  }

  private void drawLabel(Canvas canvas, float cornerRadius) {
    labelPaint.setColor(labelColor);
    canvas.drawRoundRect(labelBounds, cornerRadius, cornerRadius, labelPaint);
  }
}
