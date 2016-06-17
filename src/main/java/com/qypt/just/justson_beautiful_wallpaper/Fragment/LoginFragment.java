package com.qypt.just.justson_beautiful_wallpaper.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;

import com.qypt.just.justson_beautiful_wallpaper.Presenter.MainPresenter;
import com.qypt.just.justson_beautiful_wallpaper.R;
import com.qypt.just.justson_beautiful_wallpaper.Utils.OauthLoagin;
import com.umeng.socialize.bean.SHARE_MEDIA;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/6/13.
 */
public class LoginFragment extends BaseFragment {
    @Bind(R.id.account_account)
    AutoCompleteTextView accountAccount;
    @Bind(R.id.account_textinput)
    TextInputLayout accountTextinputa;
    @Bind(R.id.account_password)
    AutoCompleteTextView accountPassword;
    @Bind(R.id.account_textinput_)
    TextInputLayout accountTextinput;
    @Bind(R.id.account_register)
    Button accountRegister;
    @Bind(R.id.account_login)
    Button accountLogin;
    @Bind(R.id.account_qq_login)
    ImageView accountQqLogin;
    @Bind(R.id.account_sina_login)
    ImageView accountSinaLogin;
    private OauthLoagin login;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_login;
    }

    @Override
    protected MainPresenter getMainPresenter() {
        return null;
    }

    /**创建View完成**/
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init() {
        login = new OauthLoagin(this.getActivity());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        login.umShareAPI.onActivityResult(requestCode,resultCode,data);
        Log.i("Info","fragment------------onActivityResult");
    }

    /**
     * 创建View
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
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

    @OnClick({R.id.account_register, R.id.account_login, R.id.account_qq_login, R.id.account_sina_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.account_register:
                baseActivity.startFragment(R.id.account_content,new ResgiterFragment(),"Resgiter",true);
                break;
            case R.id.account_login:
                break;
            case R.id.account_qq_login:
                login.onOauthLogin(SHARE_MEDIA.QQ);
                break;
            case R.id.account_sina_login:
                break;
        }
    }
}
