// Generated code from Butter Knife. Do not modify!
package com.boss.company2;

import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Finder;
import com.boss.R;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class CompanyInfoActiviy_ViewBinding<T extends CompanyInfoActiviy> implements Unbinder {
  protected T target;

  public CompanyInfoActiviy_ViewBinding(T target, Finder finder, Object source) {
    this.target = target;

    target.tvIsFromSpider = finder.findRequiredViewAsType(source, R.id.tv_is_from_spider, "field 'tvIsFromSpider'", TextView.class);
  }

  @Override
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.tvIsFromSpider = null;

    this.target = null;
  }
}
