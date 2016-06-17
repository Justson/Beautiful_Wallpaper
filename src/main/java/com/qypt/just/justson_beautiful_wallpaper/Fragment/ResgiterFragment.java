package com.qypt.just.justson_beautiful_wallpaper.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.qypt.just.justson_beautiful_wallpaper.Presenter.MainPresenter;
import com.qypt.just.justson_beautiful_wallpaper.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/6/17.
 */
public class ResgiterFragment extends BaseFragment {
    @Bind(R.id.resgiter_phone)
    AutoCompleteTextView resgiterPhone;
    @Bind(R.id.resgiter_name)
    AutoCompleteTextView resgiterName;
    @Bind(R.id.resgiter_password)
    AutoCompleteTextView resgiterPassword;
    @Bind(R.id.resgiter_input_vertify)
    EditText resgiterInputVertify;
    @Bind(R.id.resgiter_vertify)
    Button resgiterVertify;
    @Bind(R.id.resgiter_register)
    Button resgiterRegister;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_resgiter;
    }

    @Override
    protected MainPresenter getMainPresenter() {
        return new MainPresenter();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.resgiter_vertify, R.id.resgiter_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.resgiter_vertify:
                break;
            case R.id.resgiter_register:
                break;
        }
    }
}
