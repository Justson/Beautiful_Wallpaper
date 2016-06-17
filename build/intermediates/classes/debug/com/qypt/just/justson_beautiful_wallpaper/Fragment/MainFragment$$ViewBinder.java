// Generated code from Butter Knife. Do not modify!
package com.qypt.just.justson_beautiful_wallpaper.Fragment;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class MainFragment$$ViewBinder<T extends com.qypt.just.justson_beautiful_wallpaper.Fragment.MainFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131493032, "field 'slider'");
    target.slider = finder.castView(view, 2131493032, "field 'slider'");
    view = finder.findRequiredView(source, 2131493033, "field 'recyclerviewMain'");
    target.recyclerviewMain = finder.castView(view, 2131493033, "field 'recyclerviewMain'");
  }

  @Override public void unbind(T target) {
    target.slider = null;
    target.recyclerviewMain = null;
  }
}
