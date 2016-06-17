// Generated code from Butter Knife. Do not modify!
package com.qypt.just.justson_beautiful_wallpaper.Activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class DetailActivity$$ViewBinder<T extends com.qypt.just.justson_beautiful_wallpaper.Activity.DetailActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131493003, "field 'framelayoutDetail'");
    target.framelayoutDetail = finder.castView(view, 2131493003, "field 'framelayoutDetail'");
    view = finder.findRequiredView(source, 2131493004, "field 'viewPager'");
    target.viewPager = finder.castView(view, 2131493004, "field 'viewPager'");
    view = finder.findRequiredView(source, 2131493006, "field 'settingWallDetail' and method 'onClick'");
    target.settingWallDetail = finder.castView(view, 2131493006, "field 'settingWallDetail'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131493007, "field 'downDetail' and method 'onClick'");
    target.downDetail = finder.castView(view, 2131493007, "field 'downDetail'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131493008, "field 'shareDetail' and method 'onClick'");
    target.shareDetail = finder.castView(view, 2131493008, "field 'shareDetail'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131493005, "field 'comebackDetail' and method 'onClick'");
    target.comebackDetail = finder.castView(view, 2131493005, "field 'comebackDetail'");
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
    target.framelayoutDetail = null;
    target.viewPager = null;
    target.settingWallDetail = null;
    target.downDetail = null;
    target.shareDetail = null;
    target.comebackDetail = null;
  }
}
