// Generated code from Butter Knife. Do not modify!
package com.boss.employee2;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Finder;
import com.boss.R;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class JobInfoActivity_ViewBinding<T extends JobInfoActivity> implements Unbinder {
  protected T target;

  private View view2131558578;

  private View view2131558664;

  private View view2131558665;

  private View view2131558558;

  public JobInfoActivity_ViewBinding(final T target, Finder finder, Object source) {
    this.target = target;

    View view;
    target.txtNum = finder.findRequiredViewAsType(source, R.id.txt_num, "field 'txtNum'", TextView.class);
    target.tvJobName = finder.findRequiredViewAsType(source, R.id.tv_job_name, "field 'tvJobName'", TextView.class);
    target.tvJoOffer = finder.findRequiredViewAsType(source, R.id.tv_jo_offer, "field 'tvJoOffer'", TextView.class);
    target.tvJobCompanyPoi = finder.findRequiredViewAsType(source, R.id.tv_job_company_poi, "field 'tvJobCompanyPoi'", TextView.class);
    target.tvJobRequestExp = finder.findRequiredViewAsType(source, R.id.tv_job_request_exp, "field 'tvJobRequestExp'", TextView.class);
    target.tvJobRequestEdu = finder.findRequiredViewAsType(source, R.id.tv_job_request_edu, "field 'tvJobRequestEdu'", TextView.class);
    target.imgCompanyAvtar = finder.findRequiredViewAsType(source, R.id.img_company_avtar, "field 'imgCompanyAvtar'", ImageView.class);
    target.tvJobComanyName = finder.findRequiredViewAsType(source, R.id.tv_job_comany_name, "field 'tvJobComanyName'", TextView.class);
    target.tvJobCompanyFlag = finder.findRequiredViewAsType(source, R.id.tv_job_company_flag, "field 'tvJobCompanyFlag'", TextView.class);
    target.employee4InEditdataYulanjianliEducate = finder.findRequiredViewAsType(source, R.id.employee4_in_editdata_yulanjianli_educate, "field 'employee4InEditdataYulanjianliEducate'", TextView.class);
    target.tvJobFlag = finder.findRequiredViewAsType(source, R.id.tv_job_flag, "field 'tvJobFlag'", TextView.class);
    target.tvJobRequest = finder.findRequiredViewAsType(source, R.id.tv_job_request, "field 'tvJobRequest'", TextView.class);
    target.tvJobRequestSkill = finder.findRequiredViewAsType(source, R.id.tv_job_request_skill, "field 'tvJobRequestSkill'", TextView.class);
    target.imgBossAvtar = finder.findRequiredViewAsType(source, R.id.img_boss_avtar, "field 'imgBossAvtar'", ImageView.class);
    target.tvBosName = finder.findRequiredViewAsType(source, R.id.tv_bos_name, "field 'tvBosName'", TextView.class);
    target.employee4InEditdataYulanjianliAll = finder.findRequiredViewAsType(source, R.id.employee4_in_editdata_yulanjianli_all, "field 'employee4InEditdataYulanjianliAll'", LinearLayout.class);
    view = finder.findRequiredView(source, R.id.btn_begin_chat, "field 'btnBeginChat' and method 'beginChat'");
    target.btnBeginChat = finder.castView(view, R.id.btn_begin_chat, "field 'btnBeginChat'", Button.class);
    view2131558578 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.beginChat(p0);
      }
    });
    target.tvBossPhone = finder.findRequiredViewAsType(source, R.id.tv_boss_phone, "field 'tvBossPhone'", TextView.class);
    view = finder.findRequiredView(source, R.id.btn_begin_review, "field 'btnBeginReview' and method 'onClickReview'");
    target.btnBeginReview = finder.castView(view, R.id.btn_begin_review, "field 'btnBeginReview'", Button.class);
    view2131558664 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickReview(p0);
      }
    });
    view = finder.findRequiredView(source, R.id.btn_post_jianli, "field 'btnPostJianli' and method 'onClickPostJianli'");
    target.btnPostJianli = finder.castView(view, R.id.btn_post_jianli, "field 'btnPostJianli'", Button.class);
    view2131558665 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickPostJianli();
      }
    });
    target.tvIsFromSpider = finder.findRequiredViewAsType(source, R.id.tv_is_from_spider, "field 'tvIsFromSpider'", TextView.class);
    target.tvRequestNum = finder.findRequiredViewAsType(source, R.id.tv_request_num, "field 'tvRequestNum'", TextView.class);
    view = finder.findRequiredView(source, R.id.layout_company_info, "method 'onCompanyInfoClick'");
    view2131558558 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onCompanyInfoClick(p0);
      }
    });
  }

  @Override
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.txtNum = null;
    target.tvJobName = null;
    target.tvJoOffer = null;
    target.tvJobCompanyPoi = null;
    target.tvJobRequestExp = null;
    target.tvJobRequestEdu = null;
    target.imgCompanyAvtar = null;
    target.tvJobComanyName = null;
    target.tvJobCompanyFlag = null;
    target.employee4InEditdataYulanjianliEducate = null;
    target.tvJobFlag = null;
    target.tvJobRequest = null;
    target.tvJobRequestSkill = null;
    target.imgBossAvtar = null;
    target.tvBosName = null;
    target.employee4InEditdataYulanjianliAll = null;
    target.btnBeginChat = null;
    target.tvBossPhone = null;
    target.btnBeginReview = null;
    target.btnPostJianli = null;
    target.tvIsFromSpider = null;
    target.tvRequestNum = null;

    view2131558578.setOnClickListener(null);
    view2131558578 = null;
    view2131558664.setOnClickListener(null);
    view2131558664 = null;
    view2131558665.setOnClickListener(null);
    view2131558665 = null;
    view2131558558.setOnClickListener(null);
    view2131558558 = null;

    this.target = null;
  }
}
