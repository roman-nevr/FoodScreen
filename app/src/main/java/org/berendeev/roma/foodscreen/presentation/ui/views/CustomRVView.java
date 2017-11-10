package org.berendeev.roma.foodscreen.presentation.ui.views;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import org.berendeev.roma.foodscreen.R;

import java.util.ArrayList;
import java.util.List;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;


public class CustomRVView<T> extends FrameLayout {

    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

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
        if (progressBar.getVisibility() != GONE){
            int childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(
                    300, MeasureSpec.AT_MOST);
            int childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(
                    300, MeasureSpec.AT_MOST);
            progressBar.measure(childWidthMeasureSpec, childHeightMeasureSpec);
        }

        recyclerView.measure(widthMeasureSpec, heightMeasureSpec);

        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.getSize(heightMeasureSpec));
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        if (changed){
            if (progressBar.getVisibility() != GONE){
                progressBar.layout(left, top, right, bottom);
            }
            recyclerView.layout(left, top, right, bottom);
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }

    private void initUi(Context context, AttributeSet attributeSet){
        recyclerView = new RecyclerView(context);
        recyclerView.setLayoutParams(new ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT));
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setHasFixedSize(true);

        progressBar = (ProgressBar) inflate(context, R.layout.progress_bar, this).findViewById(R.id.progress_bar);
//        progressBar = new ProgressBar(context);
//        progressBar.setLayoutParams(new ViewGroup.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));

    }

    public void setAdapter(RecyclerView.Adapter adapter){
        this.adapter = adapter;
        recyclerView.setAdapter(adapter);
    }

    public void showProgress(){
        progressBar.setVisibility(VISIBLE);
    }

    public void hideProgress(){
        progressBar.setVisibility(GONE);
    }

    public interface Updatable<T>{
        abstract void update(List<T> items);
    }
}
