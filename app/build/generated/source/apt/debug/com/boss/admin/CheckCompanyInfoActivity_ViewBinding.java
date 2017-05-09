// Generated code from Butter Knife. Do not modify!
package com.boss.admin;

import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Finder;
import com.boss.R;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class CheckCompanyInfoActivity_ViewBinding<T extends CheckCompanyInfoActivity> implements Unbinder {
  protected T target;

  private View view2131558547;

  private View view2131558548;

  public CheckCompanyInfoActivity_ViewBinding(final T target, Finder finder, Object source) {
    this.target = target;

    View view;
    target.tvIsFromSpider = finder.findRequiredViewAsType(source, R.id.tv_is_from_spider, "field 'tvIsFromSpider'", TextView.class);
    view = finder.findRequiredView(source, R.id.btn_pass, "method 'onClickCompanyPass'");
    view2131558547 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickCompanyPass(p0);
      }
    });
    view = finder.findRequiredView(source, R.id.btn_not_pass, "method 'onClickCompanyNotPass'");
    view2131558548 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickCompanyNotPass(p0);
      }
    });
  }

  @Override
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.tvIsFromSpider = null;

    view2131558547.setOnClickListener(null);
    view2131558547 = null;
    view2131558548.setOnClickListener(null);
    view2131558548 = null;

    this.target = null;
  }
}
