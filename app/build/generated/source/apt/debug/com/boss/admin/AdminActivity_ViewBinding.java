// Generated code from Butter Knife. Do not modify!
package com.boss.admin;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Finder;
import com.boss.R;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class AdminActivity_ViewBinding<T extends AdminActivity> implements Unbinder {
  protected T target;

  private View view2131558521;

  public AdminActivity_ViewBinding(final T target, Finder finder, Object source) {
    this.target = target;

    View view;
    view = finder.findRequiredView(source, R.id.img_serch, "method 'onSearchClick'");
    view2131558521 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onSearchClick(p0);
      }
    });
  }

  @Override
  public void unbind() {
    if (this.target == null) throw new IllegalStateException("Bindings already cleared.");

    view2131558521.setOnClickListener(null);
    view2131558521 = null;

    this.target = null;
  }
}
