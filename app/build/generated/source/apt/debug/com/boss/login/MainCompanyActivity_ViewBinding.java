// Generated code from Butter Knife. Do not modify!
package com.boss.login;

import android.view.View;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Finder;
import com.boss.R;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class MainCompanyActivity_ViewBinding<T extends MainCompanyActivity> implements Unbinder {
  protected T target;

  private View view2131558521;

  public MainCompanyActivity_ViewBinding(final T target, Finder finder, Object source) {
    this.target = target;

    View view;
    view = finder.findRequiredView(source, R.id.img_serch, "field 'imgSearch' and method 'onSearchClick'");
    target.imgSearch = finder.castView(view, R.id.img_serch, "field 'imgSearch'", ImageView.class);
    view2131558521 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onSearchClick(p0);
      }
    });
  }

  @Override
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.imgSearch = null;

    view2131558521.setOnClickListener(null);
    view2131558521 = null;

    this.target = null;
  }
}
