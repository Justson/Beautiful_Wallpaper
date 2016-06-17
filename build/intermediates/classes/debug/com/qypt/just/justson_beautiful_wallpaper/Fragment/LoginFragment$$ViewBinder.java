// Generated code from Butter Knife. Do not modify!
package com.qypt.just.justson_beautiful_wallpaper.Fragment;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class LoginFragment$$ViewBinder<T extends com.qypt.just.justson_beautiful_wallpaper.Fragment.LoginFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131493025, "field 'accountAccount'");
    target.accountAccount = finder.castView(view, 2131493025, "field 'accountAccount'");
    view = finder.findRequiredView(source, 2131493024, "field 'accountTextinputa'");
    target.accountTextinputa = finder.castView(view, 2131493024, "field 'accountTextinputa'");
    view = finder.findRequiredView(source, 2131493027, "field 'accountPassword'");
    target.accountPassword = finder.castView(view, 2131493027, "field 'accountPassword'");
    view = finder.findRequiredView(source, 2131493026, "field 'accountTextinput'");
    target.accountTextinput = finder.castView(view, 2131493026, "field 'accountTextinput'");
    view = finder.findRequiredView(source, 2131493028, "field 'accountRegister' and method 'onClick'");
    target.accountRegister = finder.castView(view, 2131493028, "field 'accountRegister'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131493029, "field 'accountLogin' and method 'onClick'");
    target.accountLogin = finder.castView(view, 2131493029, "field 'accountLogin'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131493030, "field 'accountQqLogin' and method 'onClick'");
    target.accountQqLogin = finder.castView(view, 2131493030, "field 'accountQqLogin'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131493031, "field 'accountSinaLogin' and method 'onClick'");
    target.accountSinaLogin = finder.castView(view, 2131493031, "field 'accountSinaLogin'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
  }

  @Override public void unbind(T target) {
    target.accountAccount = null;
    target.accountTextinputa = null;
    target.accountPassword = null;
    target.accountTextinput = null;
    target.accountRegister = null;
    target.accountLogin = null;
    target.accountQqLogin = null;
    target.accountSinaLogin = null;
  }
}
