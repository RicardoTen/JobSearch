// Generated code from Butter Knife. Do not modify!
package com.boss.company3.in.release;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Finder;
import com.boss.R;
import com.boss.view.IconFontTextView;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class ReleseJobActivity_ViewBinding<T extends ReleseJobActivity> implements Unbinder {
  protected T target;

  private View view2131558756;

  private View view2131558757;

  private View view2131558759;

  private View view2131558761;

  private View view2131558765;

  private View view2131558766;

  private View view2131558767;

  private View view2131558768;

  private View view2131558727;

  private View view2131558763;

  public ReleseJobActivity_ViewBinding(final T target, Finder finder, Object source) {
    this.target = target;

    View view;
    target.actionbarLeft = finder.findRequiredViewAsType(source, R.id.actionbar_left, "field 'actionbarLeft'", IconFontTextView.class);
    target.actionbarCenter = finder.findRequiredViewAsType(source, R.id.actionbar_center, "field 'actionbarCenter'", TextView.class);
    target.actionbarRight = finder.findRequiredViewAsType(source, R.id.actionbar_right, "field 'actionbarRight'", IconFontTextView.class);
    target.imgSerch = finder.findRequiredViewAsType(source, R.id.img_serch, "field 'imgSerch'", ImageView.class);
    target.tvDevider = finder.findRequiredViewAsType(source, R.id.tv_devider, "field 'tvDevider'", TextView.class);
    target.tvJobName = finder.findRequiredViewAsType(source, R.id.tv_job_name, "field 'tvJobName'", TextView.class);
    view = finder.findRequiredView(source, R.id.layout_job_name, "field 'layoutJobName' and method 'onViewClicked'");
    target.layoutJobName = finder.castView(view, R.id.layout_job_name, "field 'layoutJobName'", LinearLayout.class);
    view2131558756 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvJobExp = finder.findRequiredViewAsType(source, R.id.tv_job_exp, "field 'tvJobExp'", TextView.class);
    view = finder.findRequiredView(source, R.id.layout_job_exp, "field 'layoutJobExp' and method 'onViewClicked'");
    target.layoutJobExp = finder.castView(view, R.id.layout_job_exp, "field 'layoutJobExp'", LinearLayout.class);
    view2131558757 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvJobEdu = finder.findRequiredViewAsType(source, R.id.tv_job_edu, "field 'tvJobEdu'", TextView.class);
    view = finder.findRequiredView(source, R.id.layout_job_edu, "field 'layoutJobEdu' and method 'onViewClicked'");
    target.layoutJobEdu = finder.castView(view, R.id.layout_job_edu, "field 'layoutJobEdu'", LinearLayout.class);
    view2131558759 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvJobMoney = finder.findRequiredViewAsType(source, R.id.tv_job_money, "field 'tvJobMoney'", TextView.class);
    view = finder.findRequiredView(source, R.id.layout_job_money, "field 'layoutJobMoney' and method 'onViewClicked'");
    target.layoutJobMoney = finder.castView(view, R.id.layout_job_money, "field 'layoutJobMoney'", LinearLayout.class);
    view2131558761 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvHintCompanyPoi = finder.findRequiredViewAsType(source, R.id.tv_hint_company_poi, "field 'tvHintCompanyPoi'", TextView.class);
    view = finder.findRequiredView(source, R.id.layout_job_request, "field 'layoutJobRequest' and method 'onViewClicked'");
    target.layoutJobRequest = finder.castView(view, R.id.layout_job_request, "field 'layoutJobRequest'", LinearLayout.class);
    view2131558765 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvHintCompanyMember = finder.findRequiredViewAsType(source, R.id.tv_hint_company_member, "field 'tvHintCompanyMember'", TextView.class);
    view = finder.findRequiredView(source, R.id.laout_job_skill, "field 'laoutJobSkill' and method 'onViewClicked'");
    target.laoutJobSkill = finder.castView(view, R.id.laout_job_skill, "field 'laoutJobSkill'", LinearLayout.class);
    view2131558766 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = finder.findRequiredView(source, R.id.layout_job_detail_poi, "field 'layoutJobDetailPoi' and method 'onViewClicked'");
    target.layoutJobDetailPoi = finder.castView(view, R.id.layout_job_detail_poi, "field 'layoutJobDetailPoi'", LinearLayout.class);
    view2131558767 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = finder.findRequiredView(source, R.id.layout_job_type, "field 'layoutJobType' and method 'onViewClicked'");
    target.layoutJobType = finder.castView(view, R.id.layout_job_type, "field 'layoutJobType'", LinearLayout.class);
    view2131558768 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = finder.findRequiredView(source, R.id.register_companystep1_goCompanyMain, "field 'registerCompanystep1GoCompanyMain' and method 'onViewClicked'");
    target.registerCompanystep1GoCompanyMain = finder.castView(view, R.id.register_companystep1_goCompanyMain, "field 'registerCompanystep1GoCompanyMain'", Button.class);
    view2131558727 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvJobFlag = finder.findRequiredViewAsType(source, R.id.tv_job_flag, "field 'tvJobFlag'", TextView.class);
    target.tvJobMemberNum = finder.findRequiredViewAsType(source, R.id.tv_job_member_num, "field 'tvJobMemberNum'", TextView.class);
    view = finder.findRequiredView(source, R.id.layout_job_member_num, "field 'layoutJobMemberNum' and method 'onJobmemerClick'");
    target.layoutJobMemberNum = finder.castView(view, R.id.layout_job_member_num, "field 'layoutJobMemberNum'", LinearLayout.class);
    view2131558763 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onJobmemerClick(p0);
      }
    });
  }

  @Override
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.actionbarLeft = null;
    target.actionbarCenter = null;
    target.actionbarRight = null;
    target.imgSerch = null;
    target.tvDevider = null;
    target.tvJobName = null;
    target.layoutJobName = null;
    target.tvJobExp = null;
    target.layoutJobExp = null;
    target.tvJobEdu = null;
    target.layoutJobEdu = null;
    target.tvJobMoney = null;
    target.layoutJobMoney = null;
    target.tvHintCompanyPoi = null;
    target.layoutJobRequest = null;
    target.tvHintCompanyMember = null;
    target.laoutJobSkill = null;
    target.layoutJobDetailPoi = null;
    target.layoutJobType = null;
    target.registerCompanystep1GoCompanyMain = null;
    target.tvJobFlag = null;
    target.tvJobMemberNum = null;
    target.layoutJobMemberNum = null;

    view2131558756.setOnClickListener(null);
    view2131558756 = null;
    view2131558757.setOnClickListener(null);
    view2131558757 = null;
    view2131558759.setOnClickListener(null);
    view2131558759 = null;
    view2131558761.setOnClickListener(null);
    view2131558761 = null;
    view2131558765.setOnClickListener(null);
    view2131558765 = null;
    view2131558766.setOnClickListener(null);
    view2131558766 = null;
    view2131558767.setOnClickListener(null);
    view2131558767 = null;
    view2131558768.setOnClickListener(null);
    view2131558768 = null;
    view2131558727.setOnClickListener(null);
    view2131558727 = null;
    view2131558763.setOnClickListener(null);
    view2131558763 = null;

    this.target = null;
  }
}
