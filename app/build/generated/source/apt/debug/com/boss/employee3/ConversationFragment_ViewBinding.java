// Generated code from Butter Knife. Do not modify!
package com.boss.employee3;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Finder;
import com.boss.R;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class ConversationFragment_ViewBinding<T extends ConversationFragment> implements Unbinder {
  protected T target;

  public ConversationFragment_ViewBinding(T target, Finder finder, Object source) {
    this.target = target;

    target.rc_view = finder.findRequiredViewAsType(source, R.id.rc_view, "field 'rc_view'", RecyclerView.class);
    target.sw_refresh = finder.findRequiredViewAsType(source, R.id.sw_refresh, "field 'sw_refresh'", SwipeRefreshLayout.class);
  }

  @Override
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.rc_view = null;
    target.sw_refresh = null;

    this.target = null;
  }
}
