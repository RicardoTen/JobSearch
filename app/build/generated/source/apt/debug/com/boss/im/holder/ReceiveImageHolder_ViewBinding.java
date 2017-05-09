// Generated code from Butter Knife. Do not modify!
package com.boss.im.holder;

import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Finder;
import com.boss.R;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class ReceiveImageHolder_ViewBinding<T extends ReceiveImageHolder> implements Unbinder {
  protected T target;

  public ReceiveImageHolder_ViewBinding(T target, Finder finder, Object source) {
    this.target = target;

    target.iv_avatar = finder.findRequiredViewAsType(source, R.id.iv_avatar, "field 'iv_avatar'", ImageView.class);
    target.tv_time = finder.findRequiredViewAsType(source, R.id.tv_time, "field 'tv_time'", TextView.class);
    target.iv_picture = finder.findRequiredViewAsType(source, R.id.iv_picture, "field 'iv_picture'", ImageView.class);
    target.progress_load = finder.findRequiredViewAsType(source, R.id.progress_load, "field 'progress_load'", ProgressBar.class);
  }

  @Override
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.iv_avatar = null;
    target.tv_time = null;
    target.iv_picture = null;
    target.progress_load = null;

    this.target = null;
  }
}
