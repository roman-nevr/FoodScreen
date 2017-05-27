package org.berendeev.roma.foodscreen.presentation.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;

import org.berendeev.roma.foodscreen.R;
import org.berendeev.roma.foodscreen.presentation.mvp.view.FoodMenuView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StubViewFragment extends MvpAppCompatFragment implements FoodMenuView {

    public static final String MESSAGE = "message";
    @BindView(R.id.stub_message) TextView tvMessage;
    private String messageString;

    @Nullable @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.stub_layout, container, false);
        readData();
        initUi(view);
        return view;
    }

    private void initUi(View view) {
        ButterKnife.bind(this, view);

        tvMessage.setText(messageString);
    }

    private void readData() {
        messageString = getArguments().getString(MESSAGE);
    }

    public static StubViewFragment getInstance(String message) {
        StubViewFragment fragment = new StubViewFragment();
        Bundle bundle = new Bundle();
        bundle.putString(MESSAGE, message);
        fragment.setArguments(bundle);
        return fragment;
    }

}
