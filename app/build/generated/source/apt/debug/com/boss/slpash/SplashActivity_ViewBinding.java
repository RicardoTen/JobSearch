// Generated code from Butter Knife. Do not modify!
package com.boss.slpash;

import android.view.View;
import android.widget.Button;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Finder;
import com.boss.R;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class SplashActivity_ViewBinding<T extends SplashActivity> implements Unbinder {
  protected T target;

  private View view2131558787;

  private View view2131558786;

  public SplashActivity_ViewBinding(final T target, Finder finder, Object source) {
    this.target = target;

    View view;
    view = finder.findRequiredView(source, R.id.btnEnter, "field 'btnEnter' and method 'click'");
    target.btnEnter = finder.castView(view, R.id.btnEnter, "field 'btnEnter'", Button.class);
    view2131558787 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click();
      }
    });
    view = finder.findRequiredView(source, R.id.btnLogin, "method 'login'");
    view2131558786 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.login();
      }
    });
  }

  @Override
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.btnEnter = null;

    view2131558787.setOnClickListener(null);
    view2131558787 = null;
    view2131558786.setOnClickListener(null);
    view2131558786 = null;

    this.target = null;
  }
}
