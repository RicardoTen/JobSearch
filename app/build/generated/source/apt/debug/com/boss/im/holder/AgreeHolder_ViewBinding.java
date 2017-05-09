// Generated code from Butter Knife. Do not modify!
package com.boss.im.holder;

import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Finder;
import com.boss.R;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class AgreeHolder_ViewBinding<T extends AgreeHolder> implements Unbinder {
  protected T target;

  public AgreeHolder_ViewBinding(T target, Finder finder, Object source) {
    this.target = target;

    target.tv_time = finder.findRequiredViewAsType(source, R.id.tv_time, "field 'tv_time'", TextView.class);
    target.tv_message = finder.findRequiredViewAsType(source, R.id.tv_message, "field 'tv_message'", TextView.class);
  }

  @Override
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.tv_time = null;
    target.tv_message = null;

    this.target = null;
  }
}
