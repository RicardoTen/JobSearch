// Generated code from Butter Knife. Do not modify!
package com.boss.slpash;

import android.content.Context;
import android.content.res.Resources;
import butterknife.Unbinder;
import butterknife.internal.Finder;
import butterknife.internal.ViewBinder;
import java.lang.Object;
import java.lang.Override;

public final class SplashPagerFragment_ViewBinder implements ViewBinder<SplashPagerFragment> {
  @Override
  public Unbinder bind(Finder finder, SplashPagerFragment target, Object source) {
    Context context = finder.getContext(source);
    Resources res = context.getResources();
    Resources.Theme theme = context.getTheme();
    return new SplashPagerFragment_ViewBinding<>(target, finder, source, res, theme);
  }
}
