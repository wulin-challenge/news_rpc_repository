package com.bjhy.news.demo.vo;

import java.io.Serializable;
import java.util.Date;

//import com.bjhy.news.demo.enums.LGFEnum;
//import com.bjhy.news.demo.enums.SexEnum;

/**
 * 罪犯基础信息Vo，对应com.bjhy.criminal.core.domain.CriminalBaseInfoEntity
 * 
 * @author wlh
 *
 */
public class CriminalBaseInfoVo implements Serializable {

	private static final long serialVersionUID = 6409728597073618426L;
	private String id; // Id
	private String bh; // 罪犯编号 OK
	private String dah; // 档案号 OK
	private String fdh; // 副档号(暂不使用) OK
	private Boolean spbm; // 身份不明（默认为false ）
	private String zsxm; // 真实姓名
	private String xm; // 姓名 ok
	// 司法部上传添加字段===>>
	// 送押机关（看守所代码）
	private String sykss;
	// 收押备注
	private String sybz;
	// 所学专业Id
	private String sxzyId; // OK
	// 捕前职务
	private String bqzwId;
	private String bqzeName;
	// 脸型id
	private String lxId;
	// <<===司法部上传添加字段
	// 姓名的简拼
	private String simpleSpell;// OK
	// 姓名的全拼
	private String fullSpell;// OK
	// private SexEnum sexEnum;// 性别 0：未知的性别1：男性2：女性9：为说明的性别 OK
	private Date csrq; // 出生日期 OK
	private String mzId; // 民族 OK
	private String mzName; // 民族名称 OK
	private String sfzh; // 身份证号 OK
	private String dqxq; // 当前刑期 OK
	private Date dqxqqr; // 当前刑期起日 OK
	private Date dqxqzr;// 当前刑期止日 OK
	private Date rjrq; // 入监日期 格式：YYYY-MM-DD 注：罪犯终审判决后，由看守所送至监狱收押的日期。
	private String sylbId; // 收押类别 OK
	private String sylbName; // 收押类别 OK
	private Date syrq; // 收押日期
	private Date fzrq; // 犯罪日期 格式：YYYY-MM-DD
	private Boolean isWcnfz; // 犯罪时是否未成年 0否 1是 OK
	private Integer xfcs; // k往刑罚次数 OK
	private String gydwId; // 监区 OK
	private String gydwName;// OK
	private String jsh; // 监舍号 OK
	private String jshName;// ok
	private String fylxId; // 分押类型OK
	private String fylxName; // 分押类型名称 OK
	private String cydjId; // 处遇等级 OK
	private String cydjName;// 处遇等级名称 OK
	private String ldnlId; // 劳动能力 OK
	private String ldnlName;// 劳动能力名称 OK
	private String gz; // 工种 OK
	private String fjljjn; // 罚金累计交纳： 注： "单位：人民币元 保留2位小数" OK
	private String mscclx; // 没收财产履行情况 OK
	private String mspcljlx; // 民事赔偿累计履行金额 注： "单位：人民币元 保留2位小数" OK
	private String jtzkId; // 未成年犯家庭状况 OK
	private String jtzkName;// OK
	// 大状态
	private String zfztId; // 罪犯状态 默认为“在押” ok
	private String zfztName; // 罪犯状态名称 OK
	// 小状态
	private String zyxzId; // 在押现状 默认为“在押” ok
	private String zyxzName; // 在押现状名称 ok
	private String birthplaceProvinceId;// 籍贯省份ID OK
	private String birthplaceProvinceName;// 籍贯省份名称 OK
	private String birthplaceCityId;// 籍贯城市ID OK
	private String birthplaceCityName;// 籍贯城市名称 OK
	private String birthplaceAreaId;// 籍贯区ID OK
	private String birthplaceAreaName;// 籍贯区名称 OK
	private String birthplaceContent;// 籍贯内容详情
	private String birthAddressId; // 出生地存储最后一层叶子节点
	private String birthAddressName; // 出生地详情
	private String familyProvinceId;// 家庭省份ID OK
	private String familyProvinceName;// 家庭省份名称 OK
	private String familyCityId;// 家庭城市ID OK
	private String familyCityName;// 家庭城市名称 OK
	private String familyAreaId;// 家庭区ID OK
	private String familyAreaName;// 家庭区名称 OK
	private String familyAddressDetail; // 家庭地址详情 OK
	private String censusRegisterProvinceId;// 户藉省份ID OK
	private String censusRegisterProvinceName;// 户藉省份名称 OK
	private String censusRegisterCityId;// 户藉城市ID OK
	private String censusRegisterCityName;// 户藉城市名称 OK
	private String censusRegisterAreaId;// 户藉区ID OK
	private String censusRegisterAreaName;// 户藉区名称 OK
	private String censusRegisterDetail;// 户藉所在地址详情 OK
	private String pcsId; // 派出所
	private String pcsName; // 派出所
	private String hklxId; // 户口类型 OK
	private String hklxName; // 户口类型名称 OK
	private String dwellProvinceId;// 居住地省份ID OK
	private String dwellProvinceName;// 居住地省份名称 OK
	private String dwellCityId;// 居住地城市ID OK
	private String dwellCityName;// 居住城市名称 OK
	private String dwellAreaId;// 居住地区ID OK
	private String dwellAreaName;// 居住区名称 OK
	private String dwellDetail; // 居住地址详情 OK
	private String yx; // 余刑 注： 系统计算得出 OK
	private String nl; // 年龄 注： 系统计算得出 OK
	private String bqmmId; // 捕前政治面貌 OK
	private String bqmmName; // 捕前政治面貌名称 OK
	private String bqwhcdId; // 捕前文化程度 OK
	private String bqwhcdName; // 捕前文化程度名称 OK
	private String xwhcdId; // 现文化程度 OK
	private String xwhcdName; // 现文化程度 名称 OK
	private String hyzkId; // 捕前婚姻状况 OK
	private String hyzkName; // 捕前婚姻状况名称 OK
	private String xhyzkId; // 现婚姻状况 OK
	private String xhyzkName; // 现婚姻状况名称 OK
	private String bqzyId; // 捕前职业
	private String bqzyName; // 捕前职业名称
	private String bqzylbId; // 捕前职业分类
	private String bqzylbName; // 捕前职业分类名称
	private String bqzjId; // 捕前职级
	private String bqzjName; // 捕前职级名称
	private String bqzcId; // 捕前职称
	private String bqzcName; // 捕前职称名称
	private String sxzy; // 捕前所学专业 OK
	private String yjsdjId; // 原技术等级 OK
	private String yjsdjName; // 原技术等级 名称 OK
	private String tcId; // 特长/捕前技能 OK
	private String tcName; // 特长/捕前技能 名称 OK
	private String zjxyId; // 宗教信仰 OK
	private String zjxyName; // 宗教信仰 名称 OK
	private String dptt; // 参加过何党派团体 OK
	private String qkcs; // 前科次数 OK
	private String swryId; // 三无人员 OK
	private String swryName; // 三无人员 名称 OK
	private String lczalbId; // 流窜作案类别 OK
	private String lczalbName; // 流窜作案类别 名称 OK
	private String tglbId;// 特管类型 OK
	private String tglbName; // 特管类型 名称 OK
	private String tglbIds;// 特管类型（档卡多个,保存时以;(注意分号是英文状态下的)来分割）OK
	private String tglbNames; // 特管类型 名称（档卡多个保存时以;(注意分号是英文状态下的)来分割）OK
	private String tgbz;// 特管备注 OK
	private String sjryId; // 三假人员 OK
	private String sjryName; // 三假人员 名称 OK
	private String zwfId; // 职务犯 OK
	private String zwfName; // 职务犯名称 OK
	// private LGFEnum lgfEnum; // 累惯犯 OK
	private String slzfId; // 三类罪犯 OK
	private String slzfName; // 三类罪犯名称 OK
	private String sslbId; // 三试类别 OK
	private String sslbName; // 三试类别名称 OK
	private String stlbId; // 三停类别 OK
	private String stlbName; // 三停类别名称 OK
	private Boolean isSfge; // 是否孤儿 OK
	private String jxcdId; // 减刑尺度 OK
	private String jxcdName; // 减刑尺度名称 OK
	private String ssId; // 四史 OK
	private String ssName; // 四史名称 OK
	private Boolean isSfzmf; // 是否知密犯 OK
	private String wfId; // 五涉 存储多Id值并以逗号分隔 OK
	private String wfName; // 五涉 存储多名称值并以逗号分隔 OK
	private String jjjlId; // 军警经历 OK
	private String jjjlName; // 军警经历名称 OK
	private String gbdjId; // 干部等级
	private String gbdjName; // 干部等级名称
	private String cscc; // 曾受何种惩处 OK
	private String wwctId; // 维稳处突 OK
	private String wwctName; // 维稳处突名称 OK
	private String hdrychId; // 获得荣誉称号奖章 OK
	private String hdrychName; // 获得荣誉称号奖章名称 OK
	private String zdqxjzId; // 重大抢险救灾 OK
	private String zdqxjzName;// ok
	private Boolean isjgqcjgz; // 建国前参加工作 OK
	private String ldgw; // 劳动岗位 存储多Id值并以逗号分隔 ok
	private String ldgwName; // 劳动岗位 存储多名称值并以逗号分隔 ok
	private String xxpxqk; // 学习培训情况 OK
	private Boolean isSfthf; // 是否团伙犯 OK
	private Integer thrs; // 团伙人数 OK
	private String gtfzlxId; // 共同犯罪参与类型 OK
	private String gtfzlxName; // 共同犯罪参与类型名称 OK
	private String yaflbId; // 原案犯类别 OK
	private String yaflbName; // 原案犯类别名称 OK
	private String xaflbId; // 现案犯类别 OK
	private String xaflbName; // 现案犯类别名称 OK
	private Boolean isCnbj; // 成年标记 ok
	private String jggj; // 监管干警 OK
	private Boolean isGjzsz; // 是否高级知识分子 OK
	private String gyfsId; // 关押方式 OK
	private String gyfsName; // 关押方式名称 OK
	private String sfzyzfId; // 是否重要罪犯 ok
	private String sfzyzfName; // 是否重要罪犯名称 OK
	// private String syjglxId;//调入机关Id 行政区划+新疆兵团+全省监狱+全国监狱管理局
	// private String syjglxName;//调入机关名称
	private String syjgshenId;// 调入机关Id OK 行政区划+新疆兵团+全省监狱+全国监狱管理局
	private String syjgshenName;// 调入机关名称 OK
	private String syjgshiId;// 调入机关Id 市 OK
	private String syjgshiName;// 调入机关名称 OK
	private String syjgxianId;// 调入机关Id 县 OK
	private String syjgxianName;// 调入机关名称 OK
	private String khjf; // 考核积分
	private Boolean isSflf; // 是否老犯 OK
	private Boolean issfcf; // 是否残犯 OK
	private Boolean issfbf; // 是否病犯 OK
	private Boolean issfwxf; // 是否危险犯 //OK
	private Integer xzjlcs; // 行政奖励次数 OK
	private Integer xzcfcs; // 行政处罚次数 OK
	private String jtzk;// 家庭状况
	private String yjxq;// 已减刑期 YYMMDD OK
	private String yaxq;// 已加刑期 YYMMDD OK
	private Date pjrq; // 判决日期 OK
	private String tazh; // OK 同案字号
							// 注：，格式：一审判决法院全称+一审案号年度字符串+一审案号法院简称及字号+一审案号序号字符串
	private String pcqhCode; // 判决法院 存储最后一级Code值 OK
	private String pcqhName; // 判决法院 值名称 OK
	private String pcmx; // 判决法院（法院称谓） OK
	private String pcnd; // 判决案号（年度) OK
	private String pczh; // 判决案号（法院简称及字号） OK
	private String pcxh; // 判决案号（序号） OK
	private Date pcrq; // 判决日期 OK
	private String accusationId; // 主罪名: 罪名表Id OK
	private String accusationName; // 主罪名: 罪名名称 OK
	private String accusationIds; // 收押罪名： 罪名表Id,以逗号分隔
	private String accusationNames; // 收押罪名： 罪名名称,以逗号分隔 OK
	private String xq; // 原判刑期 OK
	private Date qr; // 原判刑期起日 OK
	private Date zr; // 原判刑期止日 OK
	private String xbznx;// 现剥夺政治权利期限 OK
	private String bznx; // 剥夺政治权利期限 OK
	private String fjje; // 罚金金额 OK
	private Boolean isQzcj; // 是否驱逐出境 OK
	private String mscc; // 没收财产情况 OK
	private String mspj; // 附带民事判决 OK
	private String mspcje; // 民事赔偿金额 OK
	// 限制减刑
	private String xzjxId;// OK
	private String xzjxName;// OK
	private Boolean isByxjs; // 不允许假释 OK
	private Date zxrq; // 执行通知书下达日期 OK
	private String rzfpId; // 是否认罪服判 OK
	private String rzfpName; // 是否认罪服判名称 OK
	private String pjhx; // 判决缓刑 OK
	private String sg; // 身高 OK
	private String tx; // 体型 OK
	private String tz; // 体重 OK
	private String lx; // 脸型 OK
	private String xxId; // 血型 OK
	private String xxName; // 血型名称 OK
	private String zc; // 足长 OK
	private String ky; // 口音 OK
	private String pfbj; // 皮肤特殊标记 OK
	private String qttz; // 其他特征
	private Boolean isDelete = false;// 是否删除标记
	private String rjfsId; // 入境方式 OK
	private String rjfsName; // 入境方式名称 ok
	private Boolean isHy; // 是否华裔 OK
	private String gjCode; // 国籍 OK
	private String gjName; // 国籍名称 OK
	private String yzId; // 语种 OK
	private String yzName; // 语种名称 OK
	private Boolean isHj;// 是否华侨 OK
	private String wjxxbz;// 外籍信息备注 OK
	private Date createDate;// 创建时间 OK
	private String xfbdbz;// 刑罚变动备注 OK
	// 离监日期
	private Date ljrq;
	private Double jlcyScore;// 激励处遇分数 OK
	// 叶岱添加的 家庭住址详细信息
	private String jtzzxxxx;
	private Date ksdrrq;// 跨省调犯调入日期 OK
	private String ksslqh;// 跨省调犯调出单位；调出单位包含：全国监狱管理局+全国监狱 OK
	private String ywgkPassword;// 狱务公开的密码
	// 监区调动次数
	private Integer jqydcs = 0;// OK
	// 是否使用戒具 是/否
	private String sfsyjj;
	// 档卡id
	private String dangkaid;
	// 来源档卡监狱编号
	private String dkjybh;
	// 来源档卡监狱名称
	private String dkjymc;
	// 来源档卡数据库url
	private String dkurl;
	// 跨省调犯调出单位
	private String ksdcdw; // 全国监狱 没用
	// 是否身份不明
	private Boolean sfbn;// ok
	// 是否成年
	private Boolean cnbj;// ok
	// 骨龄鉴定
	private Boolean gljd;
	// 监区修改日期
	private Date gydwModifyDate;
	private String sid;// 刑罚变动备注
	// 考核分
	private KpiCrimianlPointVo kpiCrimianlPoint;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBh() {
		return bh;
	}

	public void setBh(String bh) {
		this.bh = bh;
	}

	public String getDah() {
		return dah;
	}

	public void setDah(String dah) {
		this.dah = dah;
	}

	public String getFdh() {
		return fdh;
	}

	public void setFdh(String fdh) {
		this.fdh = fdh;
	}

	public Boolean getSpbm() {
		return spbm;
	}

	public void setSpbm(Boolean spbm) {
		this.spbm = spbm;
	}

	public String getZsxm() {
		return zsxm;
	}

	public void setZsxm(String zsxm) {
		this.zsxm = zsxm;
	}

	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	public String getSykss() {
		return sykss;
	}

	public void setSykss(String sykss) {
		this.sykss = sykss;
	}

	public String getSybz() {
		return sybz;
	}

	public void setSybz(String sybz) {
		this.sybz = sybz;
	}

	public String getSxzyId() {
		return sxzyId;
	}

	public void setSxzyId(String sxzyId) {
		this.sxzyId = sxzyId;
	}

	public String getBqzwId() {
		return bqzwId;
	}

	public void setBqzwId(String bqzwId) {
		this.bqzwId = bqzwId;
	}

	public String getBqzeName() {
		return bqzeName;
	}

	public void setBqzeName(String bqzeName) {
		this.bqzeName = bqzeName;
	}

	public String getLxId() {
		return lxId;
	}

	public void setLxId(String lxId) {
		this.lxId = lxId;
	}

	public String getSimpleSpell() {
		return simpleSpell;
	}

	public void setSimpleSpell(String simpleSpell) {
		this.simpleSpell = simpleSpell;
	}

	public String getFullSpell() {
		return fullSpell;
	}

	public void setFullSpell(String fullSpell) {
		this.fullSpell = fullSpell;
	}

	// public SexEnum getSexEnum() {
	// return sexEnum;
	// }
	//
	// public void setSexEnum(SexEnum sexEnum) {
	// this.sexEnum = sexEnum;
	// }

	public Date getCsrq() {
		return csrq;
	}

	public void setCsrq(Date csrq) {
		this.csrq = csrq;
	}

	public String getMzId() {
		return mzId;
	}

	public void setMzId(String mzId) {
		this.mzId = mzId;
	}

	public String getMzName() {
		return mzName;
	}

	public void setMzName(String mzName) {
		this.mzName = mzName;
	}

	public String getSfzh() {
		return sfzh;
	}

	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}

	public String getDqxq() {
		return dqxq;
	}

	public void setDqxq(String dqxq) {
		this.dqxq = dqxq;
	}

	public Date getDqxqqr() {
		return dqxqqr;
	}

	public void setDqxqqr(Date dqxqqr) {
		this.dqxqqr = dqxqqr;
	}

	public Date getDqxqzr() {
		return dqxqzr;
	}

	public void setDqxqzr(Date dqxqzr) {
		this.dqxqzr = dqxqzr;
	}

	public Date getRjrq() {
		return rjrq;
	}

	public void setRjrq(Date rjrq) {
		this.rjrq = rjrq;
	}

	public String getSylbId() {
		return sylbId;
	}

	public void setSylbId(String sylbId) {
		this.sylbId = sylbId;
	}

	public String getSylbName() {
		return sylbName;
	}

	public void setSylbName(String sylbName) {
		this.sylbName = sylbName;
	}

	public Date getSyrq() {
		return syrq;
	}

	public void setSyrq(Date syrq) {
		this.syrq = syrq;
	}

	public Date getFzrq() {
		return fzrq;
	}

	public void setFzrq(Date fzrq) {
		this.fzrq = fzrq;
	}

	public Boolean getIsWcnfz() {
		return isWcnfz;
	}

	public void setIsWcnfz(Boolean isWcnfz) {
		this.isWcnfz = isWcnfz;
	}

	public Integer getXfcs() {
		return xfcs;
	}

	public void setXfcs(Integer xfcs) {
		this.xfcs = xfcs;
	}

	public String getGydwId() {
		return gydwId;
	}

	public void setGydwId(String gydwId) {
		this.gydwId = gydwId;
	}

	public String getGydwName() {
		return gydwName;
	}

	public void setGydwName(String gydwName) {
		this.gydwName = gydwName;
	}

	public String getJsh() {
		return jsh;
	}

	public void setJsh(String jsh) {
		this.jsh = jsh;
	}

	public String getJshName() {
		return jshName;
	}

	public void setJshName(String jshName) {
		this.jshName = jshName;
	}

	public String getFylxId() {
		return fylxId;
	}

	public void setFylxId(String fylxId) {
		this.fylxId = fylxId;
	}

	public String getFylxName() {
		return fylxName;
	}

	public void setFylxName(String fylxName) {
		this.fylxName = fylxName;
	}

	public String getCydjId() {
		return cydjId;
	}

	public void setCydjId(String cydjId) {
		this.cydjId = cydjId;
	}

	public String getCydjName() {
		return cydjName;
	}

	public void setCydjName(String cydjName) {
		this.cydjName = cydjName;
	}

	public String getLdnlId() {
		return ldnlId;
	}

	public void setLdnlId(String ldnlId) {
		this.ldnlId = ldnlId;
	}

	public String getLdnlName() {
		return ldnlName;
	}

	public void setLdnlName(String ldnlName) {
		this.ldnlName = ldnlName;
	}

	public String getGz() {
		return gz;
	}

	public void setGz(String gz) {
		this.gz = gz;
	}

	public String getFjljjn() {
		return fjljjn;
	}

	public void setFjljjn(String fjljjn) {
		this.fjljjn = fjljjn;
	}

	public String getMscclx() {
		return mscclx;
	}

	public void setMscclx(String mscclx) {
		this.mscclx = mscclx;
	}

	public String getMspcljlx() {
		return mspcljlx;
	}

	public void setMspcljlx(String mspcljlx) {
		this.mspcljlx = mspcljlx;
	}

	public String getJtzkId() {
		return jtzkId;
	}

	public void setJtzkId(String jtzkId) {
		this.jtzkId = jtzkId;
	}

	public String getJtzkName() {
		return jtzkName;
	}

	public void setJtzkName(String jtzkName) {
		this.jtzkName = jtzkName;
	}

	public String getZfztId() {
		return zfztId;
	}

	public void setZfztId(String zfztId) {
		this.zfztId = zfztId;
	}

	public String getZfztName() {
		return zfztName;
	}

	public void setZfztName(String zfztName) {
		this.zfztName = zfztName;
	}

	public String getZyxzId() {
		return zyxzId;
	}

	public void setZyxzId(String zyxzId) {
		this.zyxzId = zyxzId;
	}

	public String getZyxzName() {
		return zyxzName;
	}

	public void setZyxzName(String zyxzName) {
		this.zyxzName = zyxzName;
	}

	public String getBirthplaceProvinceId() {
		return birthplaceProvinceId;
	}

	public void setBirthplaceProvinceId(String birthplaceProvinceId) {
		this.birthplaceProvinceId = birthplaceProvinceId;
	}

	public String getBirthplaceProvinceName() {
		return birthplaceProvinceName;
	}

	public void setBirthplaceProvinceName(String birthplaceProvinceName) {
		this.birthplaceProvinceName = birthplaceProvinceName;
	}

	public String getBirthplaceCityId() {
		return birthplaceCityId;
	}

	public void setBirthplaceCityId(String birthplaceCityId) {
		this.birthplaceCityId = birthplaceCityId;
	}

	public String getBirthplaceCityName() {
		return birthplaceCityName;
	}

	public void setBirthplaceCityName(String birthplaceCityName) {
		this.birthplaceCityName = birthplaceCityName;
	}

	public String getBirthplaceAreaId() {
		return birthplaceAreaId;
	}

	public void setBirthplaceAreaId(String birthplaceAreaId) {
		this.birthplaceAreaId = birthplaceAreaId;
	}

	public String getBirthplaceAreaName() {
		return birthplaceAreaName;
	}

	public void setBirthplaceAreaName(String birthplaceAreaName) {
		this.birthplaceAreaName = birthplaceAreaName;
	}

	public String getBirthplaceContent() {
		return birthplaceContent;
	}

	public void setBirthplaceContent(String birthplaceContent) {
		this.birthplaceContent = birthplaceContent;
	}

	public String getBirthAddressId() {
		return birthAddressId;
	}

	public void setBirthAddressId(String birthAddressId) {
		this.birthAddressId = birthAddressId;
	}

	public String getBirthAddressName() {
		return birthAddressName;
	}

	public void setBirthAddressName(String birthAddressName) {
		this.birthAddressName = birthAddressName;
	}

	public String getFamilyProvinceId() {
		return familyProvinceId;
	}

	public void setFamilyProvinceId(String familyProvinceId) {
		this.familyProvinceId = familyProvinceId;
	}

	public String getFamilyProvinceName() {
		return familyProvinceName;
	}

	public void setFamilyProvinceName(String familyProvinceName) {
		this.familyProvinceName = familyProvinceName;
	}

	public String getFamilyCityId() {
		return familyCityId;
	}

	public void setFamilyCityId(String familyCityId) {
		this.familyCityId = familyCityId;
	}

	public String getFamilyCityName() {
		return familyCityName;
	}

	public void setFamilyCityName(String familyCityName) {
		this.familyCityName = familyCityName;
	}

	public String getFamilyAreaId() {
		return familyAreaId;
	}

	public void setFamilyAreaId(String familyAreaId) {
		this.familyAreaId = familyAreaId;
	}

	public String getFamilyAreaName() {
		return familyAreaName;
	}

	public void setFamilyAreaName(String familyAreaName) {
		this.familyAreaName = familyAreaName;
	}

	public String getFamilyAddressDetail() {
		return familyAddressDetail;
	}

	public void setFamilyAddressDetail(String familyAddressDetail) {
		this.familyAddressDetail = familyAddressDetail;
	}

	public String getCensusRegisterProvinceId() {
		return censusRegisterProvinceId;
	}

	public void setCensusRegisterProvinceId(String censusRegisterProvinceId) {
		this.censusRegisterProvinceId = censusRegisterProvinceId;
	}

	public String getCensusRegisterProvinceName() {
		return censusRegisterProvinceName;
	}

	public void setCensusRegisterProvinceName(String censusRegisterProvinceName) {
		this.censusRegisterProvinceName = censusRegisterProvinceName;
	}

	public String getCensusRegisterCityId() {
		return censusRegisterCityId;
	}

	public void setCensusRegisterCityId(String censusRegisterCityId) {
		this.censusRegisterCityId = censusRegisterCityId;
	}

	public String getCensusRegisterCityName() {
		return censusRegisterCityName;
	}

	public void setCensusRegisterCityName(String censusRegisterCityName) {
		this.censusRegisterCityName = censusRegisterCityName;
	}

	public String getCensusRegisterAreaId() {
		return censusRegisterAreaId;
	}

	public void setCensusRegisterAreaId(String censusRegisterAreaId) {
		this.censusRegisterAreaId = censusRegisterAreaId;
	}

	public String getCensusRegisterAreaName() {
		return censusRegisterAreaName;
	}

	public void setCensusRegisterAreaName(String censusRegisterAreaName) {
		this.censusRegisterAreaName = censusRegisterAreaName;
	}

	public String getCensusRegisterDetail() {
		return censusRegisterDetail;
	}

	public void setCensusRegisterDetail(String censusRegisterDetail) {
		this.censusRegisterDetail = censusRegisterDetail;
	}

	public String getPcsId() {
		return pcsId;
	}

	public void setPcsId(String pcsId) {
		this.pcsId = pcsId;
	}

	public String getPcsName() {
		return pcsName;
	}

	public void setPcsName(String pcsName) {
		this.pcsName = pcsName;
	}

	public String getHklxId() {
		return hklxId;
	}

	public void setHklxId(String hklxId) {
		this.hklxId = hklxId;
	}

	public String getHklxName() {
		return hklxName;
	}

	public void setHklxName(String hklxName) {
		this.hklxName = hklxName;
	}

	public String getDwellProvinceId() {
		return dwellProvinceId;
	}

	public void setDwellProvinceId(String dwellProvinceId) {
		this.dwellProvinceId = dwellProvinceId;
	}

	public String getDwellProvinceName() {
		return dwellProvinceName;
	}

	public void setDwellProvinceName(String dwellProvinceName) {
		this.dwellProvinceName = dwellProvinceName;
	}

	public String getDwellCityId() {
		return dwellCityId;
	}

	public void setDwellCityId(String dwellCityId) {
		this.dwellCityId = dwellCityId;
	}

	public String getDwellCityName() {
		return dwellCityName;
	}

	public void setDwellCityName(String dwellCityName) {
		this.dwellCityName = dwellCityName;
	}

	public String getDwellAreaId() {
		return dwellAreaId;
	}

	public void setDwellAreaId(String dwellAreaId) {
		this.dwellAreaId = dwellAreaId;
	}

	public String getDwellAreaName() {
		return dwellAreaName;
	}

	public void setDwellAreaName(String dwellAreaName) {
		this.dwellAreaName = dwellAreaName;
	}

	public String getDwellDetail() {
		return dwellDetail;
	}

	public void setDwellDetail(String dwellDetail) {
		this.dwellDetail = dwellDetail;
	}

	public String getYx() {
		return yx;
	}

	public void setYx(String yx) {
		this.yx = yx;
	}

	public String getNl() {
		return nl;
	}

	public void setNl(String nl) {
		this.nl = nl;
	}

	public String getBqmmId() {
		return bqmmId;
	}

	public void setBqmmId(String bqmmId) {
		this.bqmmId = bqmmId;
	}

	public String getBqmmName() {
		return bqmmName;
	}

	public void setBqmmName(String bqmmName) {
		this.bqmmName = bqmmName;
	}

	public String getBqwhcdId() {
		return bqwhcdId;
	}

	public void setBqwhcdId(String bqwhcdId) {
		this.bqwhcdId = bqwhcdId;
	}

	public String getBqwhcdName() {
		return bqwhcdName;
	}

	public void setBqwhcdName(String bqwhcdName) {
		this.bqwhcdName = bqwhcdName;
	}

	public String getXwhcdId() {
		return xwhcdId;
	}

	public void setXwhcdId(String xwhcdId) {
		this.xwhcdId = xwhcdId;
	}

	public String getXwhcdName() {
		return xwhcdName;
	}

	public void setXwhcdName(String xwhcdName) {
		this.xwhcdName = xwhcdName;
	}

	public String getHyzkId() {
		return hyzkId;
	}

	public void setHyzkId(String hyzkId) {
		this.hyzkId = hyzkId;
	}

	public String getHyzkName() {
		return hyzkName;
	}

	public void setHyzkName(String hyzkName) {
		this.hyzkName = hyzkName;
	}

	public String getXhyzkId() {
		return xhyzkId;
	}

	public void setXhyzkId(String xhyzkId) {
		this.xhyzkId = xhyzkId;
	}

	public String getXhyzkName() {
		return xhyzkName;
	}

	public void setXhyzkName(String xhyzkName) {
		this.xhyzkName = xhyzkName;
	}

	public String getBqzyId() {
		return bqzyId;
	}

	public void setBqzyId(String bqzyId) {
		this.bqzyId = bqzyId;
	}

	public String getBqzyName() {
		return bqzyName;
	}

	public void setBqzyName(String bqzyName) {
		this.bqzyName = bqzyName;
	}

	public String getBqzylbId() {
		return bqzylbId;
	}

	public void setBqzylbId(String bqzylbId) {
		this.bqzylbId = bqzylbId;
	}

	public String getBqzylbName() {
		return bqzylbName;
	}

	public void setBqzylbName(String bqzylbName) {
		this.bqzylbName = bqzylbName;
	}

	public String getBqzjId() {
		return bqzjId;
	}

	public void setBqzjId(String bqzjId) {
		this.bqzjId = bqzjId;
	}

	public String getBqzjName() {
		return bqzjName;
	}

	public void setBqzjName(String bqzjName) {
		this.bqzjName = bqzjName;
	}

	public String getBqzcId() {
		return bqzcId;
	}

	public void setBqzcId(String bqzcId) {
		this.bqzcId = bqzcId;
	}

	public String getBqzcName() {
		return bqzcName;
	}

	public void setBqzcName(String bqzcName) {
		this.bqzcName = bqzcName;
	}

	public String getSxzy() {
		return sxzy;
	}

	public void setSxzy(String sxzy) {
		this.sxzy = sxzy;
	}

	public String getYjsdjId() {
		return yjsdjId;
	}

	public void setYjsdjId(String yjsdjId) {
		this.yjsdjId = yjsdjId;
	}

	public String getYjsdjName() {
		return yjsdjName;
	}

	public void setYjsdjName(String yjsdjName) {
		this.yjsdjName = yjsdjName;
	}

	public String getTcId() {
		return tcId;
	}

	public void setTcId(String tcId) {
		this.tcId = tcId;
	}

	public String getTcName() {
		return tcName;
	}

	public void setTcName(String tcName) {
		this.tcName = tcName;
	}

	public String getZjxyId() {
		return zjxyId;
	}

	public void setZjxyId(String zjxyId) {
		this.zjxyId = zjxyId;
	}

	public String getZjxyName() {
		return zjxyName;
	}

	public void setZjxyName(String zjxyName) {
		this.zjxyName = zjxyName;
	}

	public String getDptt() {
		return dptt;
	}

	public void setDptt(String dptt) {
		this.dptt = dptt;
	}

	public String getQkcs() {
		return qkcs;
	}

	public void setQkcs(String qkcs) {
		this.qkcs = qkcs;
	}

	public String getSwryId() {
		return swryId;
	}

	public void setSwryId(String swryId) {
		this.swryId = swryId;
	}

	public String getSwryName() {
		return swryName;
	}

	public void setSwryName(String swryName) {
		this.swryName = swryName;
	}

	public String getLczalbId() {
		return lczalbId;
	}

	public void setLczalbId(String lczalbId) {
		this.lczalbId = lczalbId;
	}

	public String getLczalbName() {
		return lczalbName;
	}

	public void setLczalbName(String lczalbName) {
		this.lczalbName = lczalbName;
	}

	public String getTglbId() {
		return tglbId;
	}

	public void setTglbId(String tglbId) {
		this.tglbId = tglbId;
	}

	public String getTglbName() {
		return tglbName;
	}

	public void setTglbName(String tglbName) {
		this.tglbName = tglbName;
	}

	public String getTglbIds() {
		return tglbIds;
	}

	public void setTglbIds(String tglbIds) {
		this.tglbIds = tglbIds;
	}

	public String getTglbNames() {
		return tglbNames;
	}

	public void setTglbNames(String tglbNames) {
		this.tglbNames = tglbNames;
	}

	public String getTgbz() {
		return tgbz;
	}

	public void setTgbz(String tgbz) {
		this.tgbz = tgbz;
	}

	public String getSjryId() {
		return sjryId;
	}

	public void setSjryId(String sjryId) {
		this.sjryId = sjryId;
	}

	public String getSjryName() {
		return sjryName;
	}

	public void setSjryName(String sjryName) {
		this.sjryName = sjryName;
	}

	public String getZwfId() {
		return zwfId;
	}

	public void setZwfId(String zwfId) {
		this.zwfId = zwfId;
	}

	public String getZwfName() {
		return zwfName;
	}

	public void setZwfName(String zwfName) {
		this.zwfName = zwfName;
	}

	// public LGFEnum getLgfEnum() {
	// return lgfEnum;
	// }
	//
	// public void setLgfEnum(LGFEnum lgfEnum) {
	// this.lgfEnum = lgfEnum;
	// }

	public String getSlzfId() {
		return slzfId;
	}

	public void setSlzfId(String slzfId) {
		this.slzfId = slzfId;
	}

	public String getSlzfName() {
		return slzfName;
	}

	public void setSlzfName(String slzfName) {
		this.slzfName = slzfName;
	}

	public String getSslbId() {
		return sslbId;
	}

	public void setSslbId(String sslbId) {
		this.sslbId = sslbId;
	}

	public String getSslbName() {
		return sslbName;
	}

	public void setSslbName(String sslbName) {
		this.sslbName = sslbName;
	}

	public String getStlbId() {
		return stlbId;
	}

	public void setStlbId(String stlbId) {
		this.stlbId = stlbId;
	}

	public String getStlbName() {
		return stlbName;
	}

	public void setStlbName(String stlbName) {
		this.stlbName = stlbName;
	}

	public Boolean getIsSfge() {
		return isSfge;
	}

	public void setIsSfge(Boolean isSfge) {
		this.isSfge = isSfge;
	}

	public String getJxcdId() {
		return jxcdId;
	}

	public void setJxcdId(String jxcdId) {
		this.jxcdId = jxcdId;
	}

	public String getJxcdName() {
		return jxcdName;
	}

	public void setJxcdName(String jxcdName) {
		this.jxcdName = jxcdName;
	}

	public String getSsId() {
		return ssId;
	}

	public void setSsId(String ssId) {
		this.ssId = ssId;
	}

	public String getSsName() {
		return ssName;
	}

	public void setSsName(String ssName) {
		this.ssName = ssName;
	}

	public Boolean getIsSfzmf() {
		return isSfzmf;
	}

	public void setIsSfzmf(Boolean isSfzmf) {
		this.isSfzmf = isSfzmf;
	}

	public String getWfId() {
		return wfId;
	}

	public void setWfId(String wfId) {
		this.wfId = wfId;
	}

	public String getWfName() {
		return wfName;
	}

	public void setWfName(String wfName) {
		this.wfName = wfName;
	}

	public String getJjjlId() {
		return jjjlId;
	}

	public void setJjjlId(String jjjlId) {
		this.jjjlId = jjjlId;
	}

	public String getJjjlName() {
		return jjjlName;
	}

	public void setJjjlName(String jjjlName) {
		this.jjjlName = jjjlName;
	}

	public String getGbdjId() {
		return gbdjId;
	}

	public void setGbdjId(String gbdjId) {
		this.gbdjId = gbdjId;
	}

	public String getGbdjName() {
		return gbdjName;
	}

	public void setGbdjName(String gbdjName) {
		this.gbdjName = gbdjName;
	}

	public String getCscc() {
		return cscc;
	}

	public void setCscc(String cscc) {
		this.cscc = cscc;
	}

	public String getWwctId() {
		return wwctId;
	}

	public void setWwctId(String wwctId) {
		this.wwctId = wwctId;
	}

	public String getWwctName() {
		return wwctName;
	}

	public void setWwctName(String wwctName) {
		this.wwctName = wwctName;
	}

	public String getHdrychId() {
		return hdrychId;
	}

	public void setHdrychId(String hdrychId) {
		this.hdrychId = hdrychId;
	}

	public String getHdrychName() {
		return hdrychName;
	}

	public void setHdrychName(String hdrychName) {
		this.hdrychName = hdrychName;
	}

	public String getZdqxjzId() {
		return zdqxjzId;
	}

	public void setZdqxjzId(String zdqxjzId) {
		this.zdqxjzId = zdqxjzId;
	}

	public String getZdqxjzName() {
		return zdqxjzName;
	}

	public void setZdqxjzName(String zdqxjzName) {
		this.zdqxjzName = zdqxjzName;
	}

	public Boolean getIsjgqcjgz() {
		return isjgqcjgz;
	}

	public void setIsjgqcjgz(Boolean isjgqcjgz) {
		this.isjgqcjgz = isjgqcjgz;
	}

	public String getLdgw() {
		return ldgw;
	}

	public void setLdgw(String ldgw) {
		this.ldgw = ldgw;
	}

	public String getLdgwName() {
		return ldgwName;
	}

	public void setLdgwName(String ldgwName) {
		this.ldgwName = ldgwName;
	}

	public String getXxpxqk() {
		return xxpxqk;
	}

	public void setXxpxqk(String xxpxqk) {
		this.xxpxqk = xxpxqk;
	}

	public Boolean getIsSfthf() {
		return isSfthf;
	}

	public void setIsSfthf(Boolean isSfthf) {
		this.isSfthf = isSfthf;
	}

	public Integer getThrs() {
		return thrs;
	}

	public void setThrs(Integer thrs) {
		this.thrs = thrs;
	}

	public String getGtfzlxId() {
		return gtfzlxId;
	}

	public void setGtfzlxId(String gtfzlxId) {
		this.gtfzlxId = gtfzlxId;
	}

	public String getGtfzlxName() {
		return gtfzlxName;
	}

	public void setGtfzlxName(String gtfzlxName) {
		this.gtfzlxName = gtfzlxName;
	}

	public String getYaflbId() {
		return yaflbId;
	}

	public void setYaflbId(String yaflbId) {
		this.yaflbId = yaflbId;
	}

	public String getYaflbName() {
		return yaflbName;
	}

	public void setYaflbName(String yaflbName) {
		this.yaflbName = yaflbName;
	}

	public String getXaflbId() {
		return xaflbId;
	}

	public void setXaflbId(String xaflbId) {
		this.xaflbId = xaflbId;
	}

	public String getXaflbName() {
		return xaflbName;
	}

	public void setXaflbName(String xaflbName) {
		this.xaflbName = xaflbName;
	}

	public Boolean getIsCnbj() {
		return isCnbj;
	}

	public void setIsCnbj(Boolean isCnbj) {
		this.isCnbj = isCnbj;
	}

	public String getJggj() {
		return jggj;
	}

	public void setJggj(String jggj) {
		this.jggj = jggj;
	}

	public Boolean getIsGjzsz() {
		return isGjzsz;
	}

	public void setIsGjzsz(Boolean isGjzsz) {
		this.isGjzsz = isGjzsz;
	}

	public String getGyfsId() {
		return gyfsId;
	}

	public void setGyfsId(String gyfsId) {
		this.gyfsId = gyfsId;
	}

	public String getGyfsName() {
		return gyfsName;
	}

	public void setGyfsName(String gyfsName) {
		this.gyfsName = gyfsName;
	}

	public String getSfzyzfId() {
		return sfzyzfId;
	}

	public void setSfzyzfId(String sfzyzfId) {
		this.sfzyzfId = sfzyzfId;
	}

	public String getSfzyzfName() {
		return sfzyzfName;
	}

	public void setSfzyzfName(String sfzyzfName) {
		this.sfzyzfName = sfzyzfName;
	}

	public String getSyjgshenId() {
		return syjgshenId;
	}

	public void setSyjgshenId(String syjgshenId) {
		this.syjgshenId = syjgshenId;
	}

	public String getSyjgshenName() {
		return syjgshenName;
	}

	public void setSyjgshenName(String syjgshenName) {
		this.syjgshenName = syjgshenName;
	}

	public String getSyjgshiId() {
		return syjgshiId;
	}

	public void setSyjgshiId(String syjgshiId) {
		this.syjgshiId = syjgshiId;
	}

	public String getSyjgshiName() {
		return syjgshiName;
	}

	public void setSyjgshiName(String syjgshiName) {
		this.syjgshiName = syjgshiName;
	}

	public String getSyjgxianId() {
		return syjgxianId;
	}

	public void setSyjgxianId(String syjgxianId) {
		this.syjgxianId = syjgxianId;
	}

	public String getSyjgxianName() {
		return syjgxianName;
	}

	public void setSyjgxianName(String syjgxianName) {
		this.syjgxianName = syjgxianName;
	}

	public String getKhjf() {
		return khjf;
	}

	public void setKhjf(String khjf) {
		this.khjf = khjf;
	}

	public Boolean getIsSflf() {
		return isSflf;
	}

	public void setIsSflf(Boolean isSflf) {
		this.isSflf = isSflf;
	}

	public Boolean getIssfcf() {
		return issfcf;
	}

	public void setIssfcf(Boolean issfcf) {
		this.issfcf = issfcf;
	}

	public Boolean getIssfbf() {
		return issfbf;
	}

	public void setIssfbf(Boolean issfbf) {
		this.issfbf = issfbf;
	}

	public Boolean getIssfwxf() {
		return issfwxf;
	}

	public void setIssfwxf(Boolean issfwxf) {
		this.issfwxf = issfwxf;
	}

	public Integer getXzjlcs() {
		return xzjlcs;
	}

	public void setXzjlcs(Integer xzjlcs) {
		this.xzjlcs = xzjlcs;
	}

	public Integer getXzcfcs() {
		return xzcfcs;
	}

	public void setXzcfcs(Integer xzcfcs) {
		this.xzcfcs = xzcfcs;
	}

	public String getJtzk() {
		return jtzk;
	}

	public void setJtzk(String jtzk) {
		this.jtzk = jtzk;
	}

	public String getYjxq() {
		return yjxq;
	}

	public void setYjxq(String yjxq) {
		this.yjxq = yjxq;
	}

	public String getYaxq() {
		return yaxq;
	}

	public void setYaxq(String yaxq) {
		this.yaxq = yaxq;
	}

	public Date getPjrq() {
		return pjrq;
	}

	public void setPjrq(Date pjrq) {
		this.pjrq = pjrq;
	}

	public String getTazh() {
		return tazh;
	}

	public void setTazh(String tazh) {
		this.tazh = tazh;
	}

	public String getPcqhCode() {
		return pcqhCode;
	}

	public void setPcqhCode(String pcqhCode) {
		this.pcqhCode = pcqhCode;
	}

	public String getPcqhName() {
		return pcqhName;
	}

	public void setPcqhName(String pcqhName) {
		this.pcqhName = pcqhName;
	}

	public String getPcmx() {
		return pcmx;
	}

	public void setPcmx(String pcmx) {
		this.pcmx = pcmx;
	}

	public String getPcnd() {
		return pcnd;
	}

	public void setPcnd(String pcnd) {
		this.pcnd = pcnd;
	}

	public String getPczh() {
		return pczh;
	}

	public void setPczh(String pczh) {
		this.pczh = pczh;
	}

	public String getPcxh() {
		return pcxh;
	}

	public void setPcxh(String pcxh) {
		this.pcxh = pcxh;
	}

	public Date getPcrq() {
		return pcrq;
	}

	public void setPcrq(Date pcrq) {
		this.pcrq = pcrq;
	}

	public String getAccusationId() {
		return accusationId;
	}

	public void setAccusationId(String accusationId) {
		this.accusationId = accusationId;
	}

	public String getAccusationName() {
		return accusationName;
	}

	public void setAccusationName(String accusationName) {
		this.accusationName = accusationName;
	}

	public String getAccusationIds() {
		return accusationIds;
	}

	public void setAccusationIds(String accusationIds) {
		this.accusationIds = accusationIds;
	}

	public String getAccusationNames() {
		return accusationNames;
	}

	public void setAccusationNames(String accusationNames) {
		this.accusationNames = accusationNames;
	}

	public String getXq() {
		return xq;
	}

	public void setXq(String xq) {
		this.xq = xq;
	}

	public Date getQr() {
		return qr;
	}

	public void setQr(Date qr) {
		this.qr = qr;
	}

	public Date getZr() {
		return zr;
	}

	public void setZr(Date zr) {
		this.zr = zr;
	}

	public String getXbznx() {
		return xbznx;
	}

	public void setXbznx(String xbznx) {
		this.xbznx = xbznx;
	}

	public String getBznx() {
		return bznx;
	}

	public void setBznx(String bznx) {
		this.bznx = bznx;
	}

	public String getFjje() {
		return fjje;
	}

	public void setFjje(String fjje) {
		this.fjje = fjje;
	}

	public Boolean getIsQzcj() {
		return isQzcj;
	}

	public void setIsQzcj(Boolean isQzcj) {
		this.isQzcj = isQzcj;
	}

	public String getMscc() {
		return mscc;
	}

	public void setMscc(String mscc) {
		this.mscc = mscc;
	}

	public String getMspj() {
		return mspj;
	}

	public void setMspj(String mspj) {
		this.mspj = mspj;
	}

	public String getMspcje() {
		return mspcje;
	}

	public void setMspcje(String mspcje) {
		this.mspcje = mspcje;
	}

	public String getXzjxId() {
		return xzjxId;
	}

	public void setXzjxId(String xzjxId) {
		this.xzjxId = xzjxId;
	}

	public String getXzjxName() {
		return xzjxName;
	}

	public void setXzjxName(String xzjxName) {
		this.xzjxName = xzjxName;
	}

	public Boolean getIsByxjs() {
		return isByxjs;
	}

	public void setIsByxjs(Boolean isByxjs) {
		this.isByxjs = isByxjs;
	}

	public Date getZxrq() {
		return zxrq;
	}

	public void setZxrq(Date zxrq) {
		this.zxrq = zxrq;
	}

	public String getRzfpId() {
		return rzfpId;
	}

	public void setRzfpId(String rzfpId) {
		this.rzfpId = rzfpId;
	}

	public String getRzfpName() {
		return rzfpName;
	}

	public void setRzfpName(String rzfpName) {
		this.rzfpName = rzfpName;
	}

	public String getPjhx() {
		return pjhx;
	}

	public void setPjhx(String pjhx) {
		this.pjhx = pjhx;
	}

	public String getSg() {
		return sg;
	}

	public void setSg(String sg) {
		this.sg = sg;
	}

	public String getTx() {
		return tx;
	}

	public void setTx(String tx) {
		this.tx = tx;
	}

	public String getTz() {
		return tz;
	}

	public void setTz(String tz) {
		this.tz = tz;
	}

	public String getLx() {
		return lx;
	}

	public void setLx(String lx) {
		this.lx = lx;
	}

	public String getXxId() {
		return xxId;
	}

	public void setXxId(String xxId) {
		this.xxId = xxId;
	}

	public String getXxName() {
		return xxName;
	}

	public void setXxName(String xxName) {
		this.xxName = xxName;
	}

	public String getZc() {
		return zc;
	}

	public void setZc(String zc) {
		this.zc = zc;
	}

	public String getKy() {
		return ky;
	}

	public void setKy(String ky) {
		this.ky = ky;
	}

	public String getPfbj() {
		return pfbj;
	}

	public void setPfbj(String pfbj) {
		this.pfbj = pfbj;
	}

	public String getQttz() {
		return qttz;
	}

	public void setQttz(String qttz) {
		this.qttz = qttz;
	}

	public Boolean getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}

	public String getRjfsId() {
		return rjfsId;
	}

	public void setRjfsId(String rjfsId) {
		this.rjfsId = rjfsId;
	}

	public String getRjfsName() {
		return rjfsName;
	}

	public void setRjfsName(String rjfsName) {
		this.rjfsName = rjfsName;
	}

	public Boolean getIsHy() {
		return isHy;
	}

	public void setIsHy(Boolean isHy) {
		this.isHy = isHy;
	}

	public String getGjCode() {
		return gjCode;
	}

	public void setGjCode(String gjCode) {
		this.gjCode = gjCode;
	}

	public String getGjName() {
		return gjName;
	}

	public void setGjName(String gjName) {
		this.gjName = gjName;
	}

	public String getYzId() {
		return yzId;
	}

	public void setYzId(String yzId) {
		this.yzId = yzId;
	}

	public String getYzName() {
		return yzName;
	}

	public void setYzName(String yzName) {
		this.yzName = yzName;
	}

	public Boolean getIsHj() {
		return isHj;
	}

	public void setIsHj(Boolean isHj) {
		this.isHj = isHj;
	}

	public String getWjxxbz() {
		return wjxxbz;
	}

	public void setWjxxbz(String wjxxbz) {
		this.wjxxbz = wjxxbz;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getXfbdbz() {
		return xfbdbz;
	}

	public void setXfbdbz(String xfbdbz) {
		this.xfbdbz = xfbdbz;
	}

	public Date getLjrq() {
		return ljrq;
	}

	public void setLjrq(Date ljrq) {
		this.ljrq = ljrq;
	}

	public Double getJlcyScore() {
		return jlcyScore;
	}

	public void setJlcyScore(Double jlcyScore) {
		this.jlcyScore = jlcyScore;
	}

	public String getJtzzxxxx() {
		return jtzzxxxx;
	}

	public void setJtzzxxxx(String jtzzxxxx) {
		this.jtzzxxxx = jtzzxxxx;
	}

	public Date getKsdrrq() {
		return ksdrrq;
	}

	public void setKsdrrq(Date ksdrrq) {
		this.ksdrrq = ksdrrq;
	}

	public String getKsslqh() {
		return ksslqh;
	}

	public void setKsslqh(String ksslqh) {
		this.ksslqh = ksslqh;
	}

	public String getYwgkPassword() {
		return ywgkPassword;
	}

	public void setYwgkPassword(String ywgkPassword) {
		this.ywgkPassword = ywgkPassword;
	}

	public Integer getJqydcs() {
		return jqydcs;
	}

	public void setJqydcs(Integer jqydcs) {
		this.jqydcs = jqydcs;
	}

	public String getSfsyjj() {
		return sfsyjj;
	}

	public void setSfsyjj(String sfsyjj) {
		this.sfsyjj = sfsyjj;
	}

	public String getDangkaid() {
		return dangkaid;
	}

	public void setDangkaid(String dangkaid) {
		this.dangkaid = dangkaid;
	}

	public String getDkjybh() {
		return dkjybh;
	}

	public void setDkjybh(String dkjybh) {
		this.dkjybh = dkjybh;
	}

	public String getDkjymc() {
		return dkjymc;
	}

	public void setDkjymc(String dkjymc) {
		this.dkjymc = dkjymc;
	}

	public String getDkurl() {
		return dkurl;
	}

	public void setDkurl(String dkurl) {
		this.dkurl = dkurl;
	}

	public String getKsdcdw() {
		return ksdcdw;
	}

	public void setKsdcdw(String ksdcdw) {
		this.ksdcdw = ksdcdw;
	}

	public Boolean getSfbn() {
		return sfbn;
	}

	public void setSfbn(Boolean sfbn) {
		this.sfbn = sfbn;
	}

	public Boolean getCnbj() {
		return cnbj;
	}

	public void setCnbj(Boolean cnbj) {
		this.cnbj = cnbj;
	}

	public Boolean getGljd() {
		return gljd;
	}

	public void setGljd(Boolean gljd) {
		this.gljd = gljd;
	}

	public Date getGydwModifyDate() {
		return gydwModifyDate;
	}

	public void setGydwModifyDate(Date gydwModifyDate) {
		this.gydwModifyDate = gydwModifyDate;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public KpiCrimianlPointVo getKpiCrimianlPoint() {
		return kpiCrimianlPoint;
	}

	public void setKpiCrimianlPoint(KpiCrimianlPointVo kpiCrimianlPoint) {
		this.kpiCrimianlPoint = kpiCrimianlPoint;
	}
}
