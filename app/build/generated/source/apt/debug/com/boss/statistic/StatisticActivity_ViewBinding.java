// Generated code from Butter Knife. Do not modify!
package com.boss.statistic;

import butterknife.Unbinder;
import butterknife.internal.Finder;
import com.boss.R;
import com.boss.util.numer_increase.NumberRollingView;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;
import lecho.lib.hellocharts.view.ColumnChartView;
import lecho.lib.hellocharts.view.PieChartView;

public class StatisticActivity_ViewBinding<T extends StatisticActivity> implements Unbinder {
  protected T target;

  public StatisticActivity_ViewBinding(T target, Finder finder, Object source) {
    this.target = target;

    target.tvRegistPerson = finder.findRequiredViewAsType(source, R.id.tv_regist_person, "field 'tvRegistPerson'", NumberRollingView.class);
    target.chartPerson = finder.findRequiredViewAsType(source, R.id.chart_person, "field 'chartPerson'", PieChartView.class);
    target.tvRegistCompany = finder.findRequiredViewAsType(source, R.id.tv_regist_company, "field 'tvRegistCompany'", NumberRollingView.class);
    target.tvRegistJob = finder.findRequiredViewAsType(source, R.id.tv_regist_job, "field 'tvRegistJob'", NumberRollingView.class);
    target.chartCompany = finder.findRequiredViewAsType(source, R.id.chart_company, "field 'chartCompany'", PieChartView.class);
    target.chartJob = finder.findRequiredViewAsType(source, R.id.chart_job, "field 'chartJob'", PieChartView.class);
    target.chartStudentSchool = finder.findRequiredViewAsType(source, R.id.chart_student_school, "field 'chartStudentSchool'", ColumnChartView.class);
    target.chartJobFlag = finder.findRequiredViewAsType(source, R.id.chart_job_flag, "field 'chartJobFlag'", ColumnChartView.class);
    target.chartCompanyFlag = finder.findRequiredViewAsType(source, R.id.chart_company_flag, "field 'chartCompanyFlag'", ColumnChartView.class);
  }

  @Override
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.tvRegistPerson = null;
    target.chartPerson = null;
    target.tvRegistCompany = null;
    target.tvRegistJob = null;
    target.chartCompany = null;
    target.chartJob = null;
    target.chartStudentSchool = null;
    target.chartJobFlag = null;
    target.chartCompanyFlag = null;

    this.target = null;
  }
}
