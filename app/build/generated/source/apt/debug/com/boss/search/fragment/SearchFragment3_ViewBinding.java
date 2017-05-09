// Generated code from Butter Knife. Do not modify!
package com.boss.search.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Finder;
import com.boss.R;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class SearchFragment3_ViewBinding<T extends SearchFragment3> implements Unbinder {
  protected T target;

  private View view2131558783;

  public SearchFragment3_ViewBinding(final T target, Finder finder, Object source) {
    this.target = target;

    View view;
    target.et_find_name = finder.findRequiredViewAsType(source, R.id.et_find_name, "field 'et_find_name'", EditText.class);
    target.sw_refresh = finder.findRequiredViewAsType(source, R.id.sw_refresh, "field 'sw_refresh'", SwipeRefreshLayout.class);
    view = finder.findRequiredView(source, R.id.btn_search, "field 'btn_search' and method 'onSearchClick'");
    target.btn_search = finder.castView(view, R.id.btn_search, "field 'btn_search'", Button.class);
    view2131558783 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onSearchClick(p0);
      }
    });
    target.rc_view = finder.findRequiredViewAsType(source, R.id.rc_view, "field 'rc_view'", RecyclerView.class);
    target.layoutSearchBox = finder.findRequiredViewAsType(source, R.id.layout_serach_box, "field 'layoutSearchBox'", LinearLayout.class);
  }

  @Override
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.et_find_name = null;
    target.sw_refresh = null;
    target.btn_search = null;
    target.rc_view = null;
    target.layoutSearchBox = null;

    view2131558783.setOnClickListener(null);
    view2131558783 = null;

    this.target = null;
  }
}
