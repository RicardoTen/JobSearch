// Generated code from Butter Knife. Do not modify!
package com.boss.slpash;

import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.Finder;
import com.boss.R;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class pager2_ViewBinding<T extends pager2> implements Unbinder {
  protected T target;

  public pager2_ViewBinding(T target, Finder finder, Object source) {
    this.target = target;

    target.ivBubble1 = finder.findRequiredViewAsType(source, R.id.ivBubble1, "field 'ivBubble1'", ImageView.class);
    target.ivBubble2 = finder.findRequiredViewAsType(source, R.id.ivBubble2, "field 'ivBubble2'", ImageView.class);
    target.ivBubble3 = finder.findRequiredViewAsType(source, R.id.ivBubble3, "field 'ivBubble3'", ImageView.class);
  }

  @Override
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.ivBubble1 = null;
    target.ivBubble2 = null;
    target.ivBubble3 = null;

    this.target = null;
  }
}
