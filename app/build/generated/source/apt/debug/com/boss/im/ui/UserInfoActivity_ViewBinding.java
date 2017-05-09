// Generated code from Butter Knife. Do not modify!
package com.boss.im.ui;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Finder;
import com.boss.R;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class UserInfoActivity_ViewBinding<T extends UserInfoActivity> implements Unbinder {
  protected T target;

  private View view2131558805;

  private View view2131558806;

  public UserInfoActivity_ViewBinding(final T target, Finder finder, Object source) {
    this.target = target;

    View view;
    target.iv_avator = finder.findRequiredViewAsType(source, R.id.iv_avator, "field 'iv_avator'", ImageView.class);
    target.tv_name = finder.findRequiredViewAsType(source, R.id.tv_name, "field 'tv_name'", TextView.class);
    view = finder.findRequiredView(source, R.id.btn_add_friend, "field 'btn_add_friend' and method 'onAddClick'");
    target.btn_add_friend = finder.castView(view, R.id.btn_add_friend, "field 'btn_add_friend'", Button.class);
    view2131558805 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onAddClick(p0);
      }
    });
    view = finder.findRequiredView(source, R.id.btn_chat, "field 'btn_chat' and method 'onChatClick'");
    target.btn_chat = finder.castView(view, R.id.btn_chat, "field 'btn_chat'", Button.class);
    view2131558806 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onChatClick(p0);
      }
    });
  }

  @Override
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.iv_avator = null;
    target.tv_name = null;
    target.btn_add_friend = null;
    target.btn_chat = null;

    view2131558805.setOnClickListener(null);
    view2131558805 = null;
    view2131558806.setOnClickListener(null);
    view2131558806 = null;

    this.target = null;
  }
}
