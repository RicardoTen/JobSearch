// Generated code from Butter Knife. Do not modify!
package com.boss.company3;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Finder;
import com.boss.R;
import com.boss.view.MGridView;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class CompanyFragment3_ViewBinding<T extends CompanyFragment3> implements Unbinder {
  protected T target;

  private View view2131558569;

  private View view2131558574;

  private View view2131558575;

  private View view2131558577;

  public CompanyFragment3_ViewBinding(final T target, Finder finder, Object source) {
    this.target = target;

    View view;
    target.company3Companyfragment3Name = finder.findRequiredViewAsType(source, R.id.company3_companyfragment3_name, "field 'company3Companyfragment3Name'", TextView.class);
    target.company3Companyfragment3Setting = finder.findRequiredViewAsType(source, R.id.company3_companyfragment3_setting, "field 'company3Companyfragment3Setting'", ImageView.class);
    target.imgAvtar = finder.findRequiredViewAsType(source, R.id.img_avtar, "field 'imgAvtar'", ImageView.class);
    target.tvBossName = finder.findRequiredViewAsType(source, R.id.tv_boss_name, "field 'tvBossName'", TextView.class);
    target.company3Companyfragment3Girdview1 = finder.findRequiredViewAsType(source, R.id.company3_companyfragment3_girdview1, "field 'company3Companyfragment3Girdview1'", MGridView.class);
    target.company3Companyfragment3Girdview2 = finder.findRequiredViewAsType(source, R.id.company3_companyfragment3_girdview2, "field 'company3Companyfragment3Girdview2'", MGridView.class);
    view = finder.findRequiredView(source, R.id.company3_in_setting_tip, "field 'company3InSettingTip' and method 'onSetData'");
    target.company3InSettingTip = finder.castView(view, R.id.company3_in_setting_tip, "field 'company3InSettingTip'", LinearLayout.class);
    view2131558569 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onSetData(p0);
      }
    });
    target.company3InSettingShiping = finder.findRequiredViewAsType(source, R.id.company3_in_setting_shiping, "field 'company3InSettingShiping'", LinearLayout.class);
    target.company3InSettingChangephone = finder.findRequiredViewAsType(source, R.id.company3_in_setting_changephone, "field 'company3InSettingChangephone'", LinearLayout.class);
    target.company3InSettingChangepassword = finder.findRequiredViewAsType(source, R.id.company3_in_setting_changepassword, "field 'company3InSettingChangepassword'", LinearLayout.class);
    target.company3InSettingUpdata = finder.findRequiredViewAsType(source, R.id.company3_in_setting_updata, "field 'company3InSettingUpdata'", LinearLayout.class);
    view = finder.findRequiredView(source, R.id.company3_in_setting_help, "field 'company3InSettingHelp' and method 'onClickReleseJob'");
    target.company3InSettingHelp = finder.castView(view, R.id.company3_in_setting_help, "field 'company3InSettingHelp'", LinearLayout.class);
    view2131558574 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickReleseJob();
      }
    });
    view = finder.findRequiredView(source, R.id.company3_in_setting_aboutus, "field 'company3InSettingAboutus' and method 'onStatisticClick'");
    target.company3InSettingAboutus = finder.castView(view, R.id.company3_in_setting_aboutus, "field 'company3InSettingAboutus'", LinearLayout.class);
    view2131558575 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onStatisticClick(p0);
      }
    });
    target.company3InSettingChangetype = finder.findRequiredViewAsType(source, R.id.company3_in_setting_changetype, "field 'company3InSettingChangetype'", LinearLayout.class);
    view = finder.findRequiredView(source, R.id.company3_in_setting_exit, "field 'company3InSettingExit' and method 'onClickLogout'");
    target.company3InSettingExit = finder.castView(view, R.id.company3_in_setting_exit, "field 'company3InSettingExit'", LinearLayout.class);
    view2131558577 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickLogout(p0);
      }
    });
    target.tvBossJob = finder.findRequiredViewAsType(source, R.id.tv_boss_job, "field 'tvBossJob'", TextView.class);
  }

  @Override
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.company3Companyfragment3Name = null;
    target.company3Companyfragment3Setting = null;
    target.imgAvtar = null;
    target.tvBossName = null;
    target.company3Companyfragment3Girdview1 = null;
    target.company3Companyfragment3Girdview2 = null;
    target.company3InSettingTip = null;
    target.company3InSettingShiping = null;
    target.company3InSettingChangephone = null;
    target.company3InSettingChangepassword = null;
    target.company3InSettingUpdata = null;
    target.company3InSettingHelp = null;
    target.company3InSettingAboutus = null;
    target.company3InSettingChangetype = null;
    target.company3InSettingExit = null;
    target.tvBossJob = null;

    view2131558569.setOnClickListener(null);
    view2131558569 = null;
    view2131558574.setOnClickListener(null);
    view2131558574 = null;
    view2131558575.setOnClickListener(null);
    view2131558575 = null;
    view2131558577.setOnClickListener(null);
    view2131558577 = null;

    this.target = null;
  }
}
