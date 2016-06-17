// Generated code from Butter Knife. Do not modify!
package com.qypt.just.justson_beautiful_wallpaper.Fragment;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class StarFragment$$ViewBinder<T extends com.qypt.just.justson_beautiful_wallpaper.Fragment.StarFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131493035, "field 'mRecylcerView'");
    target.mRecylcerView = finder.castView(view, 2131493035, "field 'mRecylcerView'");
    view = finder.findRequiredView(source, 2131493034, "field 'mSwipeRefreshLayout'");
    target.mSwipeRefreshLayout = finder.castView(view, 2131493034, "field 'mSwipeRefreshLayout'");
  }

  @Override public void unbind(T target) {
    target.mRecylcerView = null;
    target.mSwipeRefreshLayout = null;
  }
}
