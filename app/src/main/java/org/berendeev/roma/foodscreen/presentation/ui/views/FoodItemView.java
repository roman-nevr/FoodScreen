package org.berendeev.roma.foodscreen.presentation.ui.views;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.berendeev.roma.foodscreen.R;

import static android.view.View.MeasureSpec.EXACTLY;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import static org.berendeev.roma.foodscreen.utils.ImageUtils.dpToPixels;
import static org.berendeev.roma.foodscreen.utils.ImageUtils.getVectorDrawable;


public class FoodItemView extends FrameLayout {

    private static final int LAYOUT_PADDING_TOP = 8;
    private static final int LAYOUT_PADDING_BOTTOM = 8;
    private static final int LAYOUT_PADDING_LEFT = 16;
    private static final int LAYOUT_PADDING_RIGHT = 16;

    private static final int PROGRESS_TOP_MARGIN = 16; //dp

    private ImageView imageView;
    private TextView title;
    private float density;
    private int leftPadding;
    private int topPadding;
    private int rightPadding;
    private int bottomPadding;
    private ProgressBar progressBar;

    public FoodItemView(@NonNull Context context) {
        super(context);
        initUi(context);
    }

    public FoodItemView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initUi(context);
    }

    public FoodItemView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initUi(context);
    }

    @TargetApi(21)
    public FoodItemView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initUi(context);
    }

    private void initUi(Context context){
        setLayoutParams(new LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
        imageView = new AppCompatImageView(context);
        imageView.setLayoutParams(new LayoutParams(EXACTLY, EXACTLY));
//        imageView.setImageDrawable(getVectorDrawable(context, R.drawable.round_pizza));
        addView(imageView);
        title = new AppCompatTextView(context);
        title.setTextColor(Color.BLACK);
//        title.setText("Text");
        setDefault(title);

        density = context.getResources().getDisplayMetrics().density;
        leftPadding = dpToPixels(LAYOUT_PADDING_LEFT, density);
        topPadding = dpToPixels(LAYOUT_PADDING_TOP, density);
        rightPadding = dpToPixels(LAYOUT_PADDING_RIGHT, density);
        bottomPadding = dpToPixels(LAYOUT_PADDING_BOTTOM, density);
        setLayerType(View.LAYER_TYPE_HARDWARE, null);
//        progressBar = inflate(context, R.layout.progress_bar, this).findViewById(R.id.progress_bar);
    }

    public ImageView getImageView(){
        return imageView;
    }

    public TextView getTitle(){
        return title;
    }

    private void setDefault(View view){
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
        view.setLayoutParams(layoutParams);
        addView(view);
    }

    @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        long time = System.currentTimeMillis();
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int _128dp = dpToPixels(128, density);

        int childHeightMeasureSpec = makeExactlyMeasureSpec(_128dp);
        int childWidthMeasureSpec = makeExactlyMeasureSpec(_128dp);
        imageView.measure(childWidthMeasureSpec, childHeightMeasureSpec);

        int tvHeight = title.getLayoutParams().height;
        childWidthMeasureSpec = makeExactlyMeasureSpec(width - _128dp);
        childHeightMeasureSpec = getChildMeasureSpec(heightMeasureSpec, 0, tvHeight);
        title.measure(childWidthMeasureSpec, childHeightMeasureSpec);

//        childHeightMeasureSpec = getChildMeasureSpec(heightMeasureSpec, 0, progressBar.getLayoutParams().height);
//        childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(
//                width, MeasureSpec.AT_MOST);
//        progressBar.measure(childWidthMeasureSpec, childHeightMeasureSpec);

        setMeasuredDimension(width, imageView.getMeasuredHeight() + topPadding + bottomPadding);
        Log.d("onMeasure", "time " + (System.currentTimeMillis() - time));
        System.out.println("onMeasure time " + (System.currentTimeMillis() - time));
    }

    private int makeAtMostMeasureSpec(int size){
        return MeasureSpec.makeMeasureSpec(
                size, MeasureSpec.AT_MOST);
    }

    private int makeExactlyMeasureSpec(int size){
        return MeasureSpec.makeMeasureSpec(
                size, MeasureSpec.EXACTLY);
    }

    @Override protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        long time = System.currentTimeMillis();
        int viewLeft, viewTop, viewRight, viewBottom;
        if (changed){
            int ivLeft = leftPadding;
            int ivTop = topPadding;
            int ivRight = ivLeft + imageView.getMeasuredWidth();
            int ivBottom = ivTop + topPadding + imageView.getMeasuredHeight();

            imageView.layout(ivLeft, ivTop, ivRight, ivBottom);

            int tvLeft = leftPadding + imageView.getMeasuredWidth() + ivLeft;
            int tvTop = topPadding;
            int tvRight = right - rightPadding;
            int tvBottom = tvTop + topPadding + title.getMeasuredHeight();
            title.layout(tvLeft, tvTop, tvRight, tvBottom);

//            int pbTop = dpToPixels(PROGRESS_TOP_MARGIN, density);
//            int pbLeft = (left + right) / 2 - progressBar.getMeasuredWidth() / 2;
//            int pbRight = pbLeft + progressBar.getMeasuredWidth();
//            int pbBottom = pbTop + progressBar.getMeasuredHeight();
//            progressBar.layout(pbLeft, pbTop, pbRight, pbBottom);
        }
        Log.d("onLayout", "time " + (System.currentTimeMillis() - time));
        System.out.println("onLayout time " + (System.currentTimeMillis() - time));
    }

    @Override protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
        return super.drawChild(canvas, child, drawingTime);
    }

    @Override public void draw(Canvas canvas) {
        super.draw(canvas);
    }

    @Override protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override public boolean hasOverlappingRendering() {
        return false;
    }
}
