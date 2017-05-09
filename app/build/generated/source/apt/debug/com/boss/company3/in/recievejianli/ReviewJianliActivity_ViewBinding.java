// Generated code from Butter Knife. Do not modify!
package com.boss.company3.in.recievejianli;

import android.view.View;
import android.widget.Button;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Finder;
import com.boss.R;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class ReviewJianliActivity_ViewBinding<T extends ReviewJianliActivity> implements Unbinder {
  protected T target;

  private View view2131558799;

  private View view2131558578;

  public ReviewJianliActivity_ViewBinding(final T target, Finder finder, Object source) {
    this.target = target;

    View view;
    view = finder.findRequiredView(source, R.id.btn_set_interest, "field 'btnSetInterest' and method 'setInterest'");
    target.btnSetInterest = finder.castView(view, R.id.btn_set_interest, "field 'btnSetInterest'", Button.class);
    view2131558799 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.setInterest();
      }
    });
    view = finder.findRequiredView(source, R.id.btn_begin_chat, "field 'btnChat' and method 'beginChat'");
    target.btnChat = finder.castView(view, R.id.btn_begin_chat, "field 'btnChat'", Button.class);
    view2131558578 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.beginChat(p0);
      }
    });
  }

  @Override
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.btnSetInterest = null;
    target.btnChat = null;

    view2131558799.setOnClickListener(null);
    view2131558799 = null;
    view2131558578.setOnClickListener(null);
    view2131558578 = null;

    this.target = null;
  }
}
