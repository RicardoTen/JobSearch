// Generated code from Butter Knife. Do not modify!
package com.boss.admin.fragment;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Finder;
import com.boss.R;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class AdminFragment_ViewBinding<T extends AdminFragment> implements Unbinder {
  protected T target;

  private View view2131558846;

  private View view2131558845;

  private View view2131558577;

  private View view2131558847;

  public AdminFragment_ViewBinding(final T target, Finder finder, Object source) {
    this.target = target;

    View view;
    view = finder.findRequiredView(source, R.id.layout_statistic, "method 'onStatisticClick'");
    view2131558846 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onStatisticClick(p0);
      }
    });
    view = finder.findRequiredView(source, R.id.layout_create_admin_user, "method 'onCreateAdminUserClick'");
    view2131558845 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onCreateAdminUserClick(p0);
      }
    });
    view = finder.findRequiredView(source, R.id.company3_in_setting_exit, "method 'onClickLogout'");
    view2131558577 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickLogout(p0);
      }
    });
    view = finder.findRequiredView(source, R.id.layout_ercode, "method 'onClickErCode'");
    view2131558847 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickErCode(p0);
      }
    });
  }

  @Override
  public void unbind() {
    if (this.target == null) throw new IllegalStateException("Bindings already cleared.");

    view2131558846.setOnClickListener(null);
    view2131558846 = null;
    view2131558845.setOnClickListener(null);
    view2131558845 = null;
    view2131558577.setOnClickListener(null);
    view2131558577 = null;
    view2131558847.setOnClickListener(null);
    view2131558847 = null;

    this.target = null;
  }
}
