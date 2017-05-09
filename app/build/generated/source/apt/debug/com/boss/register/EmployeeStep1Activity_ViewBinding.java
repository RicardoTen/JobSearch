// Generated code from Butter Knife. Do not modify!
package com.boss.register;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Finder;
import com.boss.R;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class EmployeeStep1Activity_ViewBinding<T extends EmployeeStep1Activity> implements Unbinder {
  protected T target;

  private View view2131558587;

  public EmployeeStep1Activity_ViewBinding(final T target, Finder finder, Object source) {
    this.target = target;

    View view;
    target.hintSex = finder.findRequiredViewAsType(source, R.id.hint_sex, "field 'hintSex'", TextView.class);
    view = finder.findRequiredView(source, R.id.layout_sex, "field 'layoutSex' and method 'onClickSex'");
    target.layoutSex = finder.castView(view, R.id.layout_sex, "field 'layoutSex'", LinearLayout.class);
    view2131558587 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickSex();
      }
    });
  }

  @Override
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.hintSex = null;
    target.layoutSex = null;

    view2131558587.setOnClickListener(null);
    view2131558587 = null;

    this.target = null;
  }
}
