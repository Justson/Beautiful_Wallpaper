// Generated code from Butter Knife. Do not modify!
package com.qypt.just.justson_beautiful_wallpaper.Activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class AccountActivity$$ViewBinder<T extends com.qypt.just.justson_beautiful_wallpaper.Activity.AccountActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131493001, "field 'accountTitle'");
    target.accountTitle = finder.castView(view, 2131493001, "field 'accountTitle'");
    view = finder.findRequiredView(source, 2131493000, "field 'accountToolbar'");
    target.accountToolbar = finder.castView(view, 2131493000, "field 'accountToolbar'");
    view = finder.findRequiredView(source, 2131493002, "field 'accountContent'");
    target.accountContent = finder.castView(view, 2131493002, "field 'accountContent'");
  }

  @Override public void unbind(T target) {
    target.accountTitle = null;
    target.accountToolbar = null;
    target.accountContent = null;
  }
}
