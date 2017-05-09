package com.boss.statistic;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.boss.R;
import com.boss.db.Company;
import com.boss.db.Job;
import com.boss.db.User;
import com.boss.login.BaseActivity;
import com.boss.util.BossConstants;
import com.boss.util.numer_increase.NumberRollingView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.CountListener;
import lecho.lib.hellocharts.formatter.ColumnChartValueFormatter;
import lecho.lib.hellocharts.formatter.SimpleColumnChartValueFormatter;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.ColumnChartView;
import lecho.lib.hellocharts.view.PieChartView;

/**
 * Created by 滕新科 on 2017/5/7.
 */

public class StatisticActivity extends BaseActivity {
    @BindView(R.id.tv_regist_person)
    NumberRollingView tvRegistPerson;
    @BindView(R.id.chart_person)
    PieChartView chartPerson;
    @BindView(R.id.tv_regist_company)
    NumberRollingView tvRegistCompany;
    @BindView(R.id.tv_regist_job)
    NumberRollingView tvRegistJob;
    @BindView(R.id.chart_company)
    PieChartView chartCompany;
    @BindView(R.id.chart_job)
    PieChartView chartJob;
    @BindView(R.id.chart_student_school)
    ColumnChartView chartStudentSchool;
    @BindView(R.id.chart_job_flag)
    ColumnChartView chartJobFlag;
    @BindView(R.id.chart_company_flag)
    ColumnChartView chartCompanyFlag;

    private List<SliceValue> personData = new ArrayList<>();
    private List<SliceValue> companyData = new ArrayList<>();
    private List<SliceValue> jobData = new ArrayList<>();

    int schoolIndex = 0;
    private ColumnChartData schoolData;
    private List<Float> schoolList = new ArrayList<>();
    private String school[] = BossConstants.SHCOOL;


    int jobIndex = 0;
    private ColumnChartData jobColumnData;
    private List<Float> jobList = new ArrayList<>();
    private String jobFlag[] = BossConstants.GET_WORK_TYPE;


    int companyIndex = 0;
    private ColumnChartData companyColumnData;
    private List<Float> companyList = new ArrayList<>();
    private String companyFlag[] = BossConstants.HANG_YE;




    private boolean hasLabels = true;//是否在薄片上显示label
    private boolean hasLabelsOutside = true;//是否在薄片外显示label
    private boolean hasCenterCircle = false;//是否中间掏空一个圈
    private boolean hasCenterText1 = true;//掏空圈是的title1
    private boolean hasCenterText2 = true;//掏空圈是的title2
    private boolean isExploded = true;//薄片是否分离
    private boolean hasLabelForSelected = false;

    // private PieChartData data_person;

    private int allPerson = 0;
    private int allCompany = 0;
    private int allJobs = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        initPersonCompnyJobData();
        initPersonPieData();
        initCompanyPieData();
        initJobPieData();
        initStudentSchollColumnChart();


    }
    Handler companyHandle = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            companyIndex++;
            Map<String, String> para = new HashMap<>();
            para.put("comanyFlag", companyFlag[companyIndex]);
            queryData(Company.class, para, new onGetAddWhereToNumListener() {
                @Override
                void initView(int num) {
                    companyList.add((float) num);
                    if (companyList.size() == companyFlag.length) {
                        initColumnChart(chartCompanyFlag, companyColumnData, companyList, companyFlag);
                    } else {
                        Message msg = companyHandle.obtainMessage();
                        msg.sendToTarget();
                    }
                }
            });
        }
    };


    private void initCompanyColumnCharts() {
        companyIndex = 0;
        Map<String, String> para = new HashMap<>();
        para.put("comanyFlag", companyFlag[companyIndex]);
        queryData(Company.class, para, new onGetAddWhereToNumListener() {
            @Override
            void initView(int num) {
                companyList.add((float) num);
                if (companyList.size() == companyFlag.length) {
                    initColumnChart(chartCompanyFlag, companyColumnData, companyList, companyFlag);
                } else {
                    Message msg = companyHandle.obtainMessage();
                    msg.sendToTarget();
                }
            }
        });
    }


    Handler jobHandle = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            jobIndex++;
            Map<String, String> para = new HashMap<>();
            para.put("jobFlag", jobFlag[jobIndex]);
            queryData(Job.class, para, new onGetAddWhereToNumListener() {
                @Override
                void initView(int num) {
                    jobList.add((float) num);
                    if (jobList.size() == jobFlag.length) {
                        initColumnChart(chartJobFlag, jobColumnData, jobList, jobFlag);
                        initCompanyColumnCharts();
                    } else {
                        Message msg = jobHandle.obtainMessage();
                        msg.sendToTarget();
                    }
                }
            });
        }
    };
    private void initJobColumnChart() {
        jobIndex = 0;
       /* for(int i = 0; i < school.length ; i++){*/
        Map<String, String> para = new HashMap<>();
        para.put("jobFlag", jobFlag[jobIndex]);
        queryData(Job.class, para, new onGetAddWhereToNumListener() {
            @Override
            void initView(int num) {
                jobList.add((float) num);
                if (jobList.size() == jobFlag.length) {
                    initColumnChart(chartJobFlag, jobColumnData, jobList, jobFlag);
                    initCompanyColumnCharts();
                } else {
                    Message msg = jobHandle.obtainMessage();
                    msg.sendToTarget();
                }
            }
        });
    }

    Handler schollHandle = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            schoolIndex++;
            Map<String, String> para = new HashMap<>();
            para.put("major", school[schoolIndex]);
            queryData(User.class, para, new onGetAddWhereToNumListener() {
                @Override
                void initView(int num) {
                    schoolList.add((float) num);
                    if (schoolList.size() == school.length) {
                        initColumnChart(chartStudentSchool, schoolData, schoolList, school);
                        initJobColumnChart();
                    } else {
                        Message msg = schollHandle.obtainMessage();
                        msg.sendToTarget();
                    }
                }
            });
        }
    };

    private void initStudentSchollColumnChart() {
        schoolIndex = 0;
       /* for(int i = 0; i < school.length ; i++){*/
        Map<String, String> para = new HashMap<>();
        para.put("major", school[schoolIndex]);
        queryData(User.class, para, new onGetAddWhereToNumListener() {
            @Override
            void initView(int num) {
                schoolList.add((float) num);
                if (schoolList.size() == school.length) {
                    initColumnChart(chartStudentSchool, schoolData, schoolList, school);
                    initJobColumnChart();
                } else {
                    Message msg = schollHandle.obtainMessage();
                    msg.sendToTarget();
                }
            }
        });
      /*  }*/

    }

    private void initColumnChart(ColumnChartView columnChartView, ColumnChartData data, List<Float> list, String titles[]) {
        // 使用的 7列，每列1个subcolumn。
        int numSubcolumns = 1;
        int numColumns = titles.length;
        //定义一个圆柱对象集合
        List<Column> columns = new ArrayList<Column>();
        //子列数据集合
        List<SubcolumnValue> values;

        List<AxisValue> axisValues = new ArrayList<AxisValue>();
        //遍历列数numColumns
        for (int i = 0; i < numColumns; ++i) {

            values = new ArrayList<SubcolumnValue>();
            //遍历每一列的每一个子列
            for (int j = 0; j < numSubcolumns; ++j) {
                //为每一柱图添加颜色和数值
                float f = list.get(i);
                values.add(new SubcolumnValue(f, ChartUtils.pickColor()));
            }
            //创建Column对象
            Column column = new Column(values);
            //这一步是能让圆柱标注数据显示带小数的重要一步 让我找了好久问题
            //作者回答https://github.com/lecho/hellocharts-android/issues/185
            ColumnChartValueFormatter chartValueFormatter = new SimpleColumnChartValueFormatter(2);
            column.setFormatter(chartValueFormatter);
            //是否有数据标注
            column.setHasLabels(true);
            //是否是点击圆柱才显示数据标注
            column.setHasLabelsOnlyForSelected(false);
            columns.add(column);
            //给x轴坐标设置描述
            axisValues.add(new AxisValue(i).setLabel(titles[i]));
        }
        //创建一个带有之前圆柱对象column集合的ColumnChartData
        data = new ColumnChartData(columns);

        //定义x轴y轴相应参数
        Axis axisX = new Axis();
        Axis axisY = new Axis().setHasLines(true);
        axisY.setName("人数");//轴名称
        axisY.hasLines();//是否显示网格线
        axisY.setTextColor(R.color.red);//颜色

        axisX.hasLines();
        axisX.setTextColor(R.color.red);
        axisX.setValues(axisValues);
        //把X轴Y轴数据设置到ColumnChartData 对象中
        data.setAxisXBottom(axisX);
        data.setAxisYLeft(axisY);
        //给表填充数据，显示出来
        columnChartView.setColumnChartData(data);
        //columnChartView.setZ((float)10);
        columnChartView.setZoomEnabled(true);

    }

    private void initJobPieData() {
        Map<String, String> para = new HashMap<>();
        para.put("", "");
        {
            BmobQuery query = new BmobQuery();
            query.addWhereEqualTo("isFromSpider", true);
            query.count(this, Job.class, new CountListener() {
                @Override
                public void onSuccess(int i) {
                    SliceValue sliceValue = new SliceValue(
                            (float) i, ChartUtils.pickColor());
                    sliceValue.setLabel("未注册职位：" + (int) sliceValue.getValue() + "个");//设置label
                    jobData.add(sliceValue);

                    if (jobData.size() == 2) {
                        generateData(chartJob, jobData);
                    }
                }

                @Override
                public void onFailure(int i, String s) {
                    Toast(User.class.toString() + "get num fail");
                }
            });
        }

        {
            BmobQuery query = new BmobQuery();
            query.addWhereEqualTo("isFromSpider", false);
            query.count(this, Company.class, new CountListener() {
                @Override
                public void onSuccess(int i) {
                    SliceValue sliceValue = new SliceValue(
                            (float) i, ChartUtils.pickColor());
                    sliceValue.setLabel("已注册职位：" + (int) sliceValue.getValue() + "个");//设置label
                    jobData.add(sliceValue);

                    if (jobData.size() == 2) {
                        generateData(chartJob, jobData);
                    }
                }

                @Override
                public void onFailure(int i, String s) {
                    Toast(User.class.toString() + "get num fail");
                }
            });
        }
    }

    private void initCompanyPieData() {
        Map<String, String> para = new HashMap<>();
        para.put("", "");
        {
            BmobQuery query = new BmobQuery();
            query.addWhereEqualTo("isFromSpider", true);
            query.count(this, Company.class, new CountListener() {
                @Override
                public void onSuccess(int i) {
                    SliceValue sliceValue = new SliceValue(
                            (float) i, ChartUtils.pickColor());
                    sliceValue.setLabel("未注册公司：" + (int) sliceValue.getValue() + "个");//设置label
                    companyData.add(sliceValue);

                    if (companyData.size() == 2) {
                        generateData(chartCompany, companyData);

                    }
                }

                @Override
                public void onFailure(int i, String s) {
                    Toast(User.class.toString() + "get num fail");
                }
            });
        }

        {
            BmobQuery query = new BmobQuery();
            query.addWhereEqualTo("isFromSpider", false);
            query.count(this, Company.class, new CountListener() {
                @Override
                public void onSuccess(int i) {
                    SliceValue sliceValue = new SliceValue(
                            (float) i, ChartUtils.pickColor());
                    sliceValue.setLabel("已注册公司：" + (int) sliceValue.getValue() + "个");//设置label
                    companyData.add(sliceValue);

                    if (companyData.size() == 2) {
                        generateData(chartCompany, companyData);

                    }
                }

                @Override
                public void onFailure(int i, String s) {
                    Toast(User.class.toString() + "get num fail");
                }
            });
        }
    }

    private void initPersonCompnyJobData() {
        Map<String, String> para = new HashMap<>();
        queryData(User.class, para, new onGetAddWhereToNumListener() {
            @Override
            void initView(int num) {
                tvRegistPerson.setFrameNum(100);
                tvRegistPerson.setContent(num + "");
            }
        });

        queryData(Job.class, para, new onGetAddWhereToNumListener() {
            @Override
            void initView(int num) {
                tvRegistJob.setFrameNum(100);
                tvRegistJob.setContent(num + "");
            }
        });

        queryData(Company.class, para, new onGetAddWhereToNumListener() {
            @Override
            void initView(int num) {
                tvRegistCompany.setFrameNum(100);
                tvRegistCompany.setContent(num + "");
            }
        });


    }

    private void initPersonPieData() {
        Map<String, String> para = new HashMap<>();
        para.put("", "");
        {
            BmobQuery query = new BmobQuery();
            query.addWhereEqualTo("registerCompany", true);
            query.count(this, User.class, new CountListener() {
                @Override
                public void onSuccess(int i) {
                    SliceValue sliceValue = new SliceValue(
                            (float) i, ChartUtils.pickColor());
                    sliceValue.setLabel("注册公司：" + (int) sliceValue.getValue() + "人");//设置label
                    personData.add(sliceValue);

                    if (personData.size() == 2) {
                        generateData(chartPerson, personData);
                    }
                }

                @Override
                public void onFailure(int i, String s) {
                    Toast(User.class.toString() + "get num fail");
                }
            });
        }

        {
            BmobQuery query = new BmobQuery();
            query.addWhereEqualTo("registerEmployee", true);
            query.count(this, User.class, new CountListener() {
                @Override
                public void onSuccess(int i) {
                    SliceValue sliceValue = new SliceValue(
                            (float) i, ChartUtils.pickColor());
                    sliceValue.setLabel("注册学生：" + (int) sliceValue.getValue() + "人");//设置label
                    personData.add(sliceValue);

                    if (personData.size() == 2) {
                        generateData(chartPerson, personData);

                    }
                }

                @Override
                public void onFailure(int i, String s) {
                    Toast(User.class.toString() + "get num fail");
                }
            });
        }
    }


    private void generateData(PieChartView chart, List<SliceValue> values) {
        PieChartData data = new PieChartData(values);
        data.setHasLabels(hasLabels);
        data.setHasLabelsOnlyForSelected(hasLabelForSelected);
        data.setHasLabelsOutside(hasLabelsOutside);
        data.setHasCenterCircle(hasCenterCircle);

        if (isExploded) {
            data.setSlicesSpacing(24);//设置分离距离
        }

        if (hasCenterText1) {
            data.setCenterText1("Hello!");

            data.setCenterText1FontSize(ChartUtils.px2sp(getResources()
                    .getDisplayMetrics().scaledDensity, (int) getResources()
                    .getDimension(R.dimen.pie_chart_text1_size)));
        }

        if (hasCenterText2) {

            data.setCenterText2FontSize(ChartUtils.px2sp(getResources()
                    .getDisplayMetrics().scaledDensity, (int) getResources()
                    .getDimension(R.dimen.pie_chart_text1_size)));
        }

        chart.setPieChartData(data);
        chart.setCircleFillRatio(0.5f);//设置放大缩小范围
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    private void queryData(final Class<?> defClass, Map<String, String> para, final onGetAddWhereToNumListener listener) {
        BmobQuery query = new BmobQuery();
        if (para.size() != 0) {
            for (Map.Entry<String, String> entry : para.entrySet()) {
                query.addWhereContains(entry.getKey(), entry.getValue());
            }
        }
        query.count(this, defClass, new CountListener() {
            @Override
            public void onSuccess(int i) {
                listener.initView(i);
            }

            @Override
            public void onFailure(int i, String s) {
                Toast(s);
            }
        });
    }

    public abstract class onGetAddWhereToNumListener {
        abstract void initView(int num);
    }

}
