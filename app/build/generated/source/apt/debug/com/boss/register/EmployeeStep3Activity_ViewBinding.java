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

public class EmployeeStep3Activity_ViewBinding<T extends EmployeeStep3Activity> implements Unbinder {
  protected T target;

  private View view2131558741;

  public EmployeeStep3Activity_ViewBinding(final T target, Finder finder, Object source) {
    this.target = target;

    View view;
    target.registerEmployeestep3SchoolSchoolTxt = finder.findRequiredViewAsType(source, R.id.register_employeestep3_school_schoolTxt, "field 'registerEmployeestep3SchoolSchoolTxt'", TextView.class);
    view = finder.findRequiredView(source, R.id.register_employeestep3_school_school, "field 'registerEmployeestep3SchoolSchool' and method 'onSchoolSchollClick'");
    target.registerEmployeestep3SchoolSchool = finder.castView(view, R.id.register_employeestep3_school_school, "field 'registerEmployeestep3SchoolSchool'", LinearLayout.class);
    view2131558741 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onSchoolSchollClick(p0);
      }
    });
  }

  @Override
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.registerEmployeestep3SchoolSchoolTxt = null;
    target.registerEmployeestep3SchoolSchool = null;

    view2131558741.setOnClickListener(null);
    view2131558741 = null;

    this.target = null;
  }
}
