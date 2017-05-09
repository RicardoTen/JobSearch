// Generated code from Butter Knife. Do not modify!
package com.boss.im.holder;

import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Finder;
import com.boss.R;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class ReceiveLocationHolder_ViewBinding<T extends ReceiveLocationHolder> implements Unbinder {
  protected T target;

  public ReceiveLocationHolder_ViewBinding(T target, Finder finder, Object source) {
    this.target = target;

    target.iv_avatar = finder.findRequiredViewAsType(source, R.id.iv_avatar, "field 'iv_avatar'", ImageView.class);
    target.tv_time = finder.findRequiredViewAsType(source, R.id.tv_time, "field 'tv_time'", TextView.class);
    target.tv_location = finder.findRequiredViewAsType(source, R.id.tv_location, "field 'tv_location'", TextView.class);
  }

  @Override
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.iv_avatar = null;
    target.tv_time = null;
    target.tv_location = null;

    this.target = null;
  }
}
