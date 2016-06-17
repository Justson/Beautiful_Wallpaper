// Generated code from Butter Knife. Do not modify!
package com.qypt.just.justson_beautiful_wallpaper.Fragment;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class SceneryFragment$$ViewBinder<T extends com.qypt.just.justson_beautiful_wallpaper.Fragment.SceneryFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131493042, "field 'recyclerviewScenery'");
    target.recyclerviewScenery = finder.castView(view, 2131493042, "field 'recyclerviewScenery'");
    view = finder.findRequiredView(source, 2131493041, "field 'swipeRefreshLayoutScenery'");
    target.swipeRefreshLayoutScenery = finder.castView(view, 2131493041, "field 'swipeRefreshLayoutScenery'");
  }

  @Override public void unbind(T target) {
    target.recyclerviewScenery = null;
    target.swipeRefreshLayoutScenery = null;
  }
}
