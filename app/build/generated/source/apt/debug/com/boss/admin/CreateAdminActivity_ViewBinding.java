// Generated code from Butter Knife. Do not modify!
package com.boss.admin;

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

public class CreateAdminActivity_ViewBinding<T extends CreateAdminActivity> implements Unbinder {
  protected T target;

  private View view2131558587;

  private View view2131558585;

  public CreateAdminActivity_ViewBinding(final T target, Finder finder, Object source) {
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
    target.tvPhoneHint = finder.findRequiredViewAsType(source, R.id.tv_phone_hint, "field 'tvPhoneHint'", TextView.class);
    target.hintPassword = finder.findRequiredViewAsType(source, R.id.hint_password, "field 'hintPassword'", TextView.class);
    view = finder.findRequiredView(source, R.id.layout_password, "method 'oClickGoToSetPwd'");
    view2131558585 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.oClickGoToSetPwd(p0);
      }
    });
  }

  @Override
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.hintSex = null;
    target.layoutSex = null;
    target.tvPhoneHint = null;
    target.hintPassword = null;

    view2131558587.setOnClickListener(null);
    view2131558587 = null;
    view2131558585.setOnClickListener(null);
    view2131558585 = null;

    this.target = null;
  }
}
