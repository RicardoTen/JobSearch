// Generated code from Butter Knife. Do not modify!
package com.boss.im.holder;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Finder;
import com.boss.R;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class SearchUserHolder_ViewBinding<T extends SearchUserHolder> implements Unbinder {
  protected T target;

  public SearchUserHolder_ViewBinding(T target, Finder finder, Object source) {
    this.target = target;

    target.avatar = finder.findRequiredViewAsType(source, R.id.avatar, "field 'avatar'", ImageView.class);
    target.name = finder.findRequiredViewAsType(source, R.id.name, "field 'name'", TextView.class);
    target.btn_add = finder.findRequiredViewAsType(source, R.id.btn_add, "field 'btn_add'", Button.class);
  }

  @Override
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.avatar = null;
    target.name = null;
    target.btn_add = null;

    this.target = null;
  }
}
