// Generated code from Butter Knife. Do not modify!
package com.boss.admin.adaper;

import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Finder;
import com.boss.R;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class PrepareCompanyAdapter$ViewHolder_ViewBinding<T extends PrepareCompanyAdapter.ViewHolder> implements Unbinder {
  protected T target;

  public PrepareCompanyAdapter$ViewHolder_ViewBinding(T target, Finder finder, Object source) {
    this.target = target;

    target.imgCompanyAvtar = finder.findRequiredViewAsType(source, R.id.img_company_avtar, "field 'imgCompanyAvtar'", ImageView.class);
    target.tvCompanyName = finder.findRequiredViewAsType(source, R.id.tv_company_name, "field 'tvCompanyName'", TextView.class);
    target.tvCompanyPoi = finder.findRequiredViewAsType(source, R.id.tv_company_poi, "field 'tvCompanyPoi'", TextView.class);
    target.itemCompany1Companyfragment1State = finder.findRequiredViewAsType(source, R.id.item_company1_companyfragment1_state, "field 'itemCompany1Companyfragment1State'", TextView.class);
    target.itemCompany1Companyfragment1Company = finder.findRequiredViewAsType(source, R.id.item_company1_companyfragment1_company, "field 'itemCompany1Companyfragment1Company'", TextView.class);
    target.tvCompanyFlag = finder.findRequiredViewAsType(source, R.id.tv_company_flag, "field 'tvCompanyFlag'", TextView.class);
    target.tvCompanyMemNum = finder.findRequiredViewAsType(source, R.id.tv_company_mem_num, "field 'tvCompanyMemNum'", TextView.class);
  }

  @Override
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.imgCompanyAvtar = null;
    target.tvCompanyName = null;
    target.tvCompanyPoi = null;
    target.itemCompany1Companyfragment1State = null;
    target.itemCompany1Companyfragment1Company = null;
    target.tvCompanyFlag = null;
    target.tvCompanyMemNum = null;

    this.target = null;
  }
}
