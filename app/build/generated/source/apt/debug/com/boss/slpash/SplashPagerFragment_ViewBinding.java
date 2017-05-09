// Generated code from Butter Knife. Do not modify!
package com.boss.slpash;

import android.content.res.Resources;
import android.support.v4.view.ViewPager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.Finder;
import butterknife.internal.Utils;
import com.boss.R;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;
import me.relex.circleindicator.CircleIndicator;

public class SplashPagerFragment_ViewBinding<T extends SplashPagerFragment> implements Unbinder {
  protected T target;

  public SplashPagerFragment_ViewBinding(T target, Finder finder, Object source, Resources res, Resources.Theme theme) {
    this.target = target;

    target.frameLayout = finder.findRequiredViewAsType(source, R.id.content, "field 'frameLayout'", FrameLayout.class);
    target.phoneFrame = finder.findRequiredViewAsType(source, R.id.layoutPhone, "field 'phoneFrame'", FrameLayout.class);
    target.ivPhoneFont = finder.findRequiredViewAsType(source, R.id.ivPhoneFont, "field 'ivPhoneFont'", ImageView.class);
    target.viewPager = finder.findRequiredViewAsType(source, R.id.viewPager, "field 'viewPager'", ViewPager.class);
    target.indicator = finder.findRequiredViewAsType(source, R.id.indicator, "field 'indicator'", CircleIndicator.class);

    target.colorRed = Utils.getColor(res, theme, R.color.colorRed);
    target.colorYellow = Utils.getColor(res, theme, R.color.colorYellow);
    target.colorGreen = Utils.getColor(res, theme, R.color.colorGreen);
  }

  @Override
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.frameLayout = null;
    target.phoneFrame = null;
    target.ivPhoneFont = null;
    target.viewPager = null;
    target.indicator = null;

    this.target = null;
  }
}
