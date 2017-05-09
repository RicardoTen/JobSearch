package com.boss.util;

import android.os.Environment;

import com.boss.R;

public class BossConstants {

	public static final String ACTION_REGISTER_SUCCESS_FINISH = "register.success.finish";// 注册成功之后登陆页面退出
	public static final String ACTION_CHANGE_MAINCOMPANY_FINISH = "login.maincompanyt.finish";// 注册成功之后登陆页面退出
	public static final String ACTION_REGISTER_SUCCESS_COMPANY_FINISH = "register.success_company_finish";// 注册公司成功后销毁Activity
	public static final String ACTION_REGISTER_SUCCESS_EMPLOYEE_FINISH = "register.success_employee_finish";// 注册公司成功后销毁Activity
	public static final String ACTION_CHANGE_MAIN_COMPANY_FINISH="main_compa}ny_finish";//销毁MainCompanyActivity
	public static final String ACTION_CHANGE_MAIN_EMPLOYEE_FINISH="main_employee_finish";//销毁MainEmployeeActivity

	/**
	 * 本地图片共八张
	 */
	public static final int DEFAULT_PICTURE[] = { R.drawable.a, R.drawable.b,
			R.drawable.c, R.drawable.d, R.drawable.e, R.drawable.f,
			R.drawable.g, R.drawable.h };

	//public static final String BMOB_ID = "834fb108a26c7b42ae9d5550ccc32c81"; // Bmob
	
	public static final String BMOB_ID = "a39a53c657c892c5ed37124f2ead2a32"; // Bmob

	/**
	 * 我的头像保存目录
	 */
	public static String MyAvatarDir = Environment
			.getExternalStorageDirectory() + "/boss/avatar/";

	/**
	 * 参加工作的时间
	 */
	public static final String GET_WORK_YEAR[] = { "应届生", "2016年", "2015年",
			"2014年", "2013年", "2012年", "2011年", "2010年", "2009年", "2008年",
			"2007年", "2006年", "2006年以前", };

	/**
	 * 工作类型
	 */
	public static final String GET_WORK_TYPE[] = { "技术", "产品", "设计", "运营",
			"市场", "职能/高级管理", "销售", "传媒", "金融" };

	/**
	 * 技能标签
	 */
	public static final String GET_WORK_SKILL[] = { "Java", "后端开发",
			"Javascript", "Android", "数据库", "iOS", "HTMLVCS5" , "移动开发", "Web前端",
			"PHP", "HTML5", "系统构架", "Linux", "CVC++", "前端开发", "需求分析", "解决方案",
			".NET", "JS", "C#", "Python", "数据挖掘", "Swift", "Flash", "Hadoop",
			"企业软件", "Node.js", "游戏开发", "项目实施", "Unity3D", "Cocos2d-X", "机器学习",
			"全栈", "系统集成", "通信", "ERP", "Shell", "图像算法", "ASP", "推荐算法", "中间件",
			"搜索算法", "自然语言处理", "网络优化", "视频算法" };
	/**
	 * 学历
	 */
	public static final String GET_EDUCATION[] = { "大专", "本科", "硕士", "博士" };
	/**
	 * 行业
	 */
	public static final String HANG_YE[] = {"健康医疗","生活服务","旅游",
			"金融","信息安全","广告营销","数据服务","智能硬件",
			"文化娱乐","网络招聘","分类信息","电子商务","互联网",
			"企业服务","社交网络","教育培训","O2O","游戏","移动互联网",
			"媒体","IT软件","通信","公关会展","房地产/建筑","汽车",
			"供应链/物流","咨询/翻译/法律","采购/贸易","非互联网行业"};
	
	/**
	 * 工作城市
	 */
	public static final String CITY[]= {"全国","北京","上海","杭州","深圳","广州","程度","南京","武汉","天津","西安","苏州"};
	/**
	 * 薪资
	 */
	public static final String Money[] = {"3k","4k","5k","6k","7k","8k","9k","10k","11k","12k","13k","14k","15k","16k","17k"};
	
	/**
	 * 求职状态
	 */
	public static final String STATIC[] = {"离职-随时到岗","在职-月内到岗","在职-考虑机会","在职-暂不考虑"};


	public static final String SEARCH_KEYWORDS[] = {"Java", "后端开发",
			"Javascript", "Android", "数据库", "iOS", "HTMLVCS5" , "移动开发", "Web前端",
			"PHP", "HTML5", "系统构架", "Linux", "CVC++", "前端开发", "需求分析", "解决方案",
			".NET", "JS", "C#", "Python", "数据挖掘", "Swift", "Flash", "Hadoop",
			"企业软件", "Node.js", "游戏开发", "项目实施", "Unity3D", "Cocos2d-X", "机器学习",
			"全栈", "系统集成", "通信", "ERP", "Shell", "图像算法", "ASP", "推荐算法", "中间件",
			"搜索算法", "自然语言处理", "网络优化", "视频算法", "技术", "产品", "设计", "运营",
			"市场", "职能/高级管理", "销售", "传媒", "金融"};

	/**
	 * 规模
	 */
	public static final String COMPANY_MEMBER[] = {"0-100","100-300","300-500","500以上"};

	/**
	 * 性别
	 */
	public static final String sex[] = {"男","女"};

	/**
	 * 选择薪资
	 */
	public static final String CHOOSE_Money[] = {"全部","3k","4k","5k","6k","7k","8k","9k","10k","11k","12k","13k","14k","15k","16k","17k"};


	public static final String USER_CHOOSE_PROPERTY[] = {"sex","education","workType","education","companyName","",""};


	/**
	 * 选择行业
	 */
	public static final String CHOOSE_HANGYE[] =  {"全部","健康医疗","生活服务","旅游","金融"
			,"信息安全","广告营销","数据服务","智能硬件","文化娱乐","网络招聘","分类信息"
			,"电子商务","互联网","企业服务","社交网络","教育培训","O2O","游戏","移动互联网"
			,"媒体","IT软件","通信","公关会展","房地产/建筑","汽车","供应链/物流"
			,"咨询/翻译/法律","采购/贸易","非互联网行业"};


	/**
	 * 选择行业
	 */
	public static final String CHOOSE_MEMBER[] = {"全部","0-100","100-300","300-500","500以上"};

	/**
	 * 工作城市
	 */
	public static final String CHOOSE_CITY[]= {"全部","北京","上海","杭州","深圳","广州","程度","南京","武汉","天津","西安","苏州"};

	public static final String COMPANY_CHOOSE_PROPERTY[] = {"comanyPoi","comanyFlag","companyMemberNum"};

	public static final String CHOOSE_CHILD_HANGYE[] = {"全部","技术", "产品", "设计", "运营",
			"市场", "职能/高级管理", "销售", "传媒", "金融" };

	public static final String CHOOSE_CHILD_GRADUATE[] = {"全部","本科", "硕士", "博士"};

	public static final String JOB_CHOOSE_PROPERTY[] = {"jobCompanyPoi","JobRequestGraduate","jobFlag","jobOfferMoney"};

	public static final String CHOSSE_COMPANY_IS_FROM_SPIDER [] = {"推荐","已注册","未注册"};

	public static final String CHOSSE_JOB_IS_FROM_SPIDER [] = {"推荐","已注册","未注册"};

	public static final String SHCOOL[] = {"生命与环境科学学院","信息通信学院","计算机科学与工程学院" ,
			"有机电工程学院","艺术与设计学院","数学与计算科学学院","信息科技学院"
	,"电子工程与自动化学院","材料科学与工程学院","建筑与交通工程学院"};

	public static final String STATISTICS_SUCCSSS = "success";

	public static final String STATISTICS_ING = "checking";

	public static final String STATISTICS_FAIL = "fail";

}
