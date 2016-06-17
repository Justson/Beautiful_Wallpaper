// Generated code from Butter Knife. Do not modify!
package com.qypt.just.justson_beautiful_wallpaper.Fragment;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class SaxModelFragment$$ViewBinder<T extends com.qypt.just.justson_beautiful_wallpaper.Fragment.SaxModelFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131493040, "field 'recyclerviewSaxModel'");
    target.recyclerviewSaxModel = finder.castView(view, 2131493040, "field 'recyclerviewSaxModel'");
    view = finder.findRequiredView(source, 2131493039, "field 'swipeRefreshLayoutSaxModel'");
    target.swipeRefreshLayoutSaxModel = finder.castView(view, 2131493039, "field 'swipeRefreshLayoutSaxModel'");
  }

  @Override public void unbind(T target) {
    target.recyclerviewSaxModel = null;
    target.swipeRefreshLayoutSaxModel = null;
  }
}
