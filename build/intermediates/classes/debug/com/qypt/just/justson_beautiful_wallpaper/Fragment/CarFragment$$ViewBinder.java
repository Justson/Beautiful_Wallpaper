// Generated code from Butter Knife. Do not modify!
package com.qypt.just.justson_beautiful_wallpaper.Fragment;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class CarFragment$$ViewBinder<T extends com.qypt.just.justson_beautiful_wallpaper.Fragment.CarFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131493023, "field 'recyclerviewCar'");
    target.recyclerviewCar = finder.castView(view, 2131493023, "field 'recyclerviewCar'");
    view = finder.findRequiredView(source, 2131493022, "field 'swipeRefreshLayoutCar'");
    target.swipeRefreshLayoutCar = finder.castView(view, 2131493022, "field 'swipeRefreshLayoutCar'");
  }

  @Override public void unbind(T target) {
    target.recyclerviewCar = null;
    target.swipeRefreshLayoutCar = null;
  }
}
