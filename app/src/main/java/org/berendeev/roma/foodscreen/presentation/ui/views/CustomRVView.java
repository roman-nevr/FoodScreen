package org.berendeev.roma.foodscreen.presentation.ui.views;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import org.berendeev.roma.foodscreen.R;

import java.util.ArrayList;
import java.util.List;

import static android.support.v7.widget.RecyclerView.SCROLL_STATE_IDLE;
import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import static org.berendeev.roma.foodscreen.utils.ImageUtils.dpToPixels;


public class CustomRVView extends FrameLayout {

    private static final int PROGRESS_TOP_MARGIN = 16; //dp

    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private float density;

    public CustomRVView(@NonNull Context context) {
        super(context);
        initUi(context, null);
    }

    public CustomRVView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initUi(context, attrs);
    }

    public CustomRVView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initUi(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        if (progressBar.getVisibility() != GONE){
            int childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(
                    height, MeasureSpec.AT_MOST);
            int childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(
                    width, MeasureSpec.AT_MOST);
            progressBar.measure(childWidthMeasureSpec, childHeightMeasureSpec);
        }

        recyclerView.measure(widthMeasureSpec, heightMeasureSpec);

        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.getSize(heightMeasureSpec));
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        if (changed){
            if (progressBar.getVisibility() != GONE){
                int pbTop = dpToPixels(PROGRESS_TOP_MARGIN, density);
                int pbLeft = (left + right) / 2 - progressBar.getMeasuredWidth() / 2;
                int pbRight = pbLeft + progressBar.getMeasuredWidth();
                int pbBottom = pbTop + progressBar.getMeasuredHeight();
                progressBar.layout(pbLeft, pbTop, pbRight, pbBottom);
            }
            recyclerView.layout(left, top, right, bottom);
        }
    }

    private void initUi(Context context, AttributeSet attributeSet){
        recyclerView = new RecyclerView(context);
        recyclerView.setLayoutParams(new ViewGroup.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setHasFixedSize(true);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                enableLayers(newState != SCROLL_STATE_IDLE);
            }

            private void enableLayers(boolean enable){
                final int layerType = enable
                        ? View.LAYER_TYPE_HARDWARE : View.LAYER_TYPE_NONE;
                recyclerView.setLayerType(layerType, null);
            }

            @Override public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        addView(recyclerView);

        progressBar = new ProgressBar(context);
        progressBar.setLayoutParams(new ViewGroup.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
        addView(progressBar);
        progressBar.setVisibility(GONE);
        density = context.getResources().getDisplayMetrics().density;
    }

    public void setAdapter(RecyclerView.Adapter adapter){
        this.adapter = adapter;
        recyclerView.setAdapter(adapter);
    }

    public RecyclerView.Adapter getAdapter(){
        return adapter;
    }

    public void showProgress(){
        progressBar.setVisibility(VISIBLE);
    }

    public void hideProgress(){
        progressBar.setVisibility(GONE);
    }

    @Override public boolean hasOverlappingRendering() {
        return false;
    }
}
