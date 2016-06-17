// Generated code from Butter Knife. Do not modify!
package com.qypt.just.justson_beautiful_wallpaper.Activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class MainActivity$$ViewBinder<T extends com.qypt.just.justson_beautiful_wallpaper.Activity.MainActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131493009, "field 'mToolbar'");
    target.mToolbar = finder.castView(view, 2131493009, "field 'mToolbar'");
    view = finder.findRequiredView(source, 2131493011, "field 'content'");
    target.content = finder.castView(view, 2131493011, "field 'content'");
    view = finder.findRequiredView(source, 2131493012, "field 'navView'");
    target.navView = finder.castView(view, 2131493012, "field 'navView'");
    view = finder.findRequiredView(source, 2131493010, "field 'mDrawerLayout'");
    target.mDrawerLayout = finder.castView(view, 2131493010, "field 'mDrawerLayout'");
  }

  @Override public void unbind(T target) {
    target.mToolbar = null;
    target.content = null;
    target.navView = null;
    target.mDrawerLayout = null;
  }
}
