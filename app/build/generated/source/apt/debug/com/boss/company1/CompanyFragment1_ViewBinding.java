// Generated code from Butter Knife. Do not modify!
package com.boss.company1;

import butterknife.Unbinder;
import butterknife.internal.Finder;
import com.boss.R;
import com.boss.view.DropDownMenu;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class CompanyFragment1_ViewBinding<T extends CompanyFragment1> implements Unbinder {
  protected T target;

  public CompanyFragment1_ViewBinding(T target, Finder finder, Object source) {
    this.target = target;

    target.dropDownMenu = finder.findRequiredViewAsType(source, R.id.dropDownMenu, "field 'dropDownMenu'", DropDownMenu.class);
  }

  @Override
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.dropDownMenu = null;

    this.target = null;
  }
}
