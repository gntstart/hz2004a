package com.hzjc.hz2004.service.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URLDecoder;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.annotation.Resource;
import javax.persistence.GeneratedValue;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.LockOptions;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.reflect.TypeToken;
import com.hzjc.hz2004.base.ExtMap;
import com.hzjc.hz2004.base.Page;
import com.hzjc.hz2004.base.ServiceImpl;
import com.hzjc.hz2004.base.SpringContextHolder;
import com.hzjc.hz2004.base.bean.SysCode;
import com.hzjc.hz2004.base.dict.DictData;
import com.hzjc.hz2004.base.login.AuthToken;
import com.hzjc.hz2004.base.login.BaseContext;
import com.hzjc.hz2004.base.sqltemplate.sql.SqlParam;
import com.hzjc.hz2004.base.sqltemplate.sql.SqlParse;
import com.hzjc.hz2004.bean.CheckQrspBean;
import com.hzjc.hz2004.constant.PublicConstant;
import com.hzjc.hz2004.dao.DAOFactory;
import com.hzjc.hz2004.dao.PojoInfo;
import com.hzjc.hz2004.po.PoSFXXB;
//import com.hzjc.hz2004.po.PoUPLOAD_TEMP;
import com.hzjc.hz2004.po.PoHJSP_HJSPZB;
import com.hzjc.hz2004.po.PoHJSP_ZQZXXB;
import com.hzjc.hz2004.po.PoHJXX_CZRKJBXXB;
import com.hzjc.hz2004.po.PoHJXX_DYXXB;
import com.hzjc.hz2004.po.PoHJXX_HXXB;
import com.hzjc.hz2004.po.PoHJXX_JWHZPLSB;
import com.hzjc.hz2004.po.PoHJXX_MLPXXXXB;
import com.hzjc.hz2004.po.PoHJXX_RHFLXXB;
import com.hzjc.hz2004.po.PoHJXX_RYZPXXB;
import com.hzjc.hz2004.po.PoHJYW_RKBKXXB;
import com.hzjc.hz2004.po.PoPERSON_DY_SET;
import com.hzjc.hz2004.po.PoSFJFFJB;
import com.hzjc.hz2004.po.PoXT_DWXXB;
import com.hzjc.hz2004.po.PoXT_JLXXXB;
import com.hzjc.hz2004.po.PoXT_JWHXXB;
import com.hzjc.hz2004.po.PoXT_XTGNB;
import com.hzjc.hz2004.po.PoXT_XTKZCSB;
import com.hzjc.hz2004.po.PoXT_XTRZB;
import com.hzjc.hz2004.po.PoXT_XZQHB;
import com.hzjc.hz2004.po.PoXT_YHXXB;
import com.hzjc.hz2004.service.CommonService;
import com.hzjc.hz2004.service.DictService;
import com.hzjc.hz2004.service.HjService;
import com.hzjc.hz2004.service.MessageService;
import com.hzjc.hz2004.service.QueryService;
import com.hzjc.hz2004.util.CommonUtil;
import com.hzjc.hz2004.util.DateHelper;
import com.hzjc.hz2004.util.ExcelToDBUtil;
import com.hzjc.util.FileUtils;
import com.hzjc.hz2004.util.JSONUtil;
import com.hzjc.hz2004.vo.VoBb;
import com.hzjc.hz2004.vo.VoHxxHqFhxx;
import com.hzjc.hz2004.vo.VoMessageRtxx;
import com.hzjc.hz2004.vo.VoMessagexx;
import com.hzjc.hz2004.vo.VoQyzdyxxHqFhxx;
import com.hzjc.hz2004.vo.VoRhflxx;
import com.hzjc.hz2004.vo.VoRhflywfhxx;
import com.hzjc.hz2004.vo.VoSfxxb;
import com.hzjc.hz2004.vo.VoZqzxx;
import com.hzjc.util.StringUtils;
import com.hzjc.wsstruts.common.ID;
import com.hzjc.wsstruts.exception.DAOException;
import com.hzjc.wsstruts.exception.ServiceException;
import com.hzjc.wsstruts.exception.WSErrCode;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Service(value = "commonService")
public class CommonServiceImpl extends ServiceImpl implements CommonService {
	@Autowired
	private HjService hjService;
	@Autowired
	private MessageService messageService;
	@Autowired
	private DictService dictService;
	@Resource
	private QueryService queryService;
	public Connection getConnection(){
		return super.getConnection();
	}
	
	public Session getSession(){
		return super.getCurrentSession();
	}
	
	public List<?> queryAll(String hql){
		return super.getObjectListByHql(hql);
	}
	
	public  Serializable getId(Class<?> entityClass) throws DAOException{
		return super.getIdBySequence(entityClass);
	}
	
	public  Serializable getId(ID id) throws DAOException{
		return super.getIdBySequence(id);
	}
	
	public Date getSjksj() throws DAOException {
		return super.getSjksj();
	}
	
	public void insertObject(Object obj){
		super.create(obj);
	}
	
	public void deleteObject(Object obj){
		super.delete(obj);
	}
	
	public void updateObject(Object obj){
		super.update(obj);
	}
	
	public <T> T getByID(final Class<T> entityClass, final Serializable id){
		return super.get(entityClass, id);
	}
	
	public void refreshObject(Object obj, LockOptions lockMode) throws DAOException{
		super.refresh(obj, lockMode);
	}
	
	public List<?> queryLock(String hsql){
		return super.getObjectListByHqlLock(hsql, new Object[]{});
	}
	
	public Page queryPoHJXX_CZRKJBXXB(ExtMap<String,Object> params){
		//{jccz=1, tokey=571148c3528e216f4f1a7f53183e89de, xm=??????, pageIndex=1, dqbm=3407, authToken=com.hzjc.hz2004.base.login.AuthToken@2ea92b05, pageSize=20, hhnbid=3407000001000387376, kdq_gmsfhm=340222197910132643, yhdlm=HZADMIN}
		if(params.containsKey("jqcx") && params.get("jqcx").equals("1")){
			return super.getPageRecords("/conf/segment/common", "queryPoHJXX_CZRKJBXXB_JCCZ", params);
		}
		
		//???????????????
		if(!params.containsKey("hhnbid") && "1".equals(params.getString("pageIndex"))){
			Page p = super.getPageRecords("/conf/segment/common", "queryPoHJXX_CZRKJBXXB_NEW", params);
			return p;
		}
		
		//???????????????
		String pageSize = params.getString("pageSize");

		//??????????????????????????????????????????
		Page p1 = super.getPageRecords("/conf/segment/common", "queryPoHJXX_CZRKJBXXB_NEW", params);
			
		//????????????????????????????????????
		params.put("pageSize", ( (Integer.parseInt(pageSize) - p1.getList().size()) + ""));
		//??????????????????????????????????????????????????????
		params.put("_more", "1");
		
		Page p2 = super.getPageRecords("/conf/segment/common", "queryPoHJXX_CZRKJBXXB_NEW", params);
			
		List<Object> relist = new ArrayList<Object>();
		relist.addAll(p1.getList());
		relist.addAll(p2.getList());
		p2.setList(relist);
		p2.setTotalCount(relist.size());
		return p2;
	}
	
	public Page queryPoHJXX_LODOP(ExtMap<String,Object> params){
		String type=params.get("type").toString();
		if(type.equals("1")) {
			params.put("pageSize", 99999);
			return super.getPageRecordsOflodop("/conf/segment/common", "queryLodopf", params);

		}else if(type.equals("2")) {
			return super.getPageRecordsOflodop("/conf/segment/common", "queryLodops", params);
		}else if(type.equals("3")) {
			return super.getPageRecordsOflodop("/conf/segment/common", "queryLodopMore", params);
		}else if(type.equals("4")) {//????????????????????????????????????rynbid????????????
			return super.getPageRecordsOflodop("/conf/segment/common", "queryLodopfByRynbid", params);
		}else if(type.equals("5")) {//????????????
			return super.getPageRecordsOflodop("/conf/segment/common", "queryLodopQfBg", params);
		}else if(type.equals("6")) {//????????????????????????????????????
			return super.getPageRecordsOflodop("/conf/segment/common", "queryLodopHz", params);
		}
		return null;
	}
	public Page queryPoHJXX_HXXB(ExtMap<String,Object> params){
		return super.getPageRecords("/conf/segment/common", "queryPoHJXX_HXXB", params);
	}
	
	public Page queryPoHJXX_RHFLXXB(ExtMap<String,Object> params){
		return super.getPageRecords("/conf/segment/common", "queryRhflxx", params);
	}
	
	public Page queryPageByConf(ExtMap<String,Object> params){
		return super.getPageRecords("/conf/segment/common", params.getString("config_key"), params);
	}
	
	/**
	 * ????????????????????????
	 * @param params
	 * @return
	 */
	public Page queryPoHJSP_HJSPSQB(ExtMap<String,Object> params){
		return super.getPageRecords("/conf/segment/common", "queryPoHJSP_HJSPSQB", params);
	}
	
	/**
	 * ???????????????????????????
	 * @param params
	 * @return
	 */
	public List<?> queryPoHJSP_HJSPZB(ExtMap<String,Object> params){
		return super.getObjectListByHql("/conf/segment/common", "queryPoHJSP_HJSPZB", params);
	}

	@Override
	public List<?> queryPoHZ_ZJ_SB(ExtMap<String, Object> params) {
		
		SqlParse sqlParse = new SqlParse("/conf/segment/common", "queryPoXT_XTKZCSB");
		sqlParse.bind(params);
		
		SqlParam sqlParam = sqlParse.parse();
		
		PoXT_XTKZCSB xtcs = (PoXT_XTKZCSB) super.getObject(sqlParam.getSql(), sqlParam.getParamsArrays());
		
		if(xtcs != null) {
			xtcs.getKzz();
		}

		return null;
	}
	
	public Object getDzxz(ExtMap<String, Object> params){
		//GetDzxz(TClientDataSet(wsCdsRyxx),'ssxq','jlx','mlph','mlxz','pcs','zrq','xzjd','jcwh','1009');
        String ssxq = params.getString("ssxq");
        String jlx = params.getString("jlx");
        String mlph = params.getString("mlph");
        String mlxz = params.getString("mlxz");
        String pcs = params.getString("pcs");
        //String zrq = params.getString("zrq");
        //String xzjd = params.getString("xzjd");
        //String jcwh = params.getString("jcwh");
        
        String xz = "";
        PoXT_XZQHB qh = super.get(PoXT_XZQHB.class, ssxq);
        if(qh!=null)
        	xz = qh.getMc();
        
        if(CommonUtil.isNotEmpty(jlx)){
        	PoXT_JLXXXB jlxxxb = super.get(PoXT_JLXXXB.class, jlx);
        	if(jlxxxb!=null){
        		xz += jlxxxb.getMc();
        	}
        }
        
        if(CommonUtil.isNotEmpty(mlph)){
        	xz += mlph;
        }
        
        if(CommonUtil.isNotEmpty(mlxz)){
        	xz += mlxz;
        }
        
        Map<String,String> data = new HashMap<String,String>();
        data.put("xxdz", xz);
        
        if(CommonUtil.isNotEmpty(pcs)){
        	PoXT_DWXXB dw = super.get(PoXT_DWXXB.class, pcs);
        	if(dw!=null){
        		data.put("pcsmc", dw.getMc());
        	}
        }
        
		return data;
	}
	
	public CheckQrspBean checkQrspdjyw(ExtMap<String, Object> params){
		if(!params.containsKey("sfzh") || !params.containsKey("xm"))
			throw new ServiceException("???????????????");
		
		CheckQrspBean bean = new CheckQrspBean();
		
		String s[] = params.getString("sfzh").split(",");
		String xm[] = params.getString("xm").split(",");
		if(s.length!=xm.length)
			throw new ServiceException("???????????????");
		
		for(int i=0;i<s.length;i++){
			if(CommonUtil.isEmpty(s[i]))
				continue;
			
			bean.getXmMap().put(s[i], xm[i]);
		}
		
		for(String sfzh: s){
			if(CommonUtil.isEmpty(sfzh))
				continue;
			
			String hsql = "from PoHJXX_CZRKJBXXB where gmsfhm=? and jlbz='1' and cxbz='0' order by qysj desc";
			List list = super.getObjectListByHql(hsql, new Object[]{sfzh});
			if(CommonUtil.isNotEmpty(list)){
				bean.getChMap().put(sfzh, list);
			}
			
			hsql = "from PoHJSP_HJSPSQB where gmsfhm=? and spjg='1' and lsbz='0'";
			List list2 = super.getObjectListByHql(hsql, new Object[]{sfzh});
			if(CommonUtil.isNotEmpty(list2)){
				bean.getSpMap().put(sfzh, list2);
			}
		}
		
		return bean;
	}
	@GeneratedValue
	public void saveHjxxDyxxb(PoHJXX_CZRKJBXXB vohjxx_czrkjbxxb, String lodopId,String zjbh,String yznf) {
		PojoInfo  xt_slhxlbDAO = DAOFactory.createHJXX_DYXXBDAO(); //??????????????????DAO
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		Calendar calendar = Calendar.getInstance();
		String slsj = df.format(calendar.getTime());
		
		//?????????????????????????????????
		PoHJXX_DYXXB pohjxx_dyxxb = new PoHJXX_DYXXB();
		Long id = (Long) xt_slhxlbDAO.getId();
		pohjxx_dyxxb.setDyid(id);
		pohjxx_dyxxb.setRyid(vohjxx_czrkjbxxb.getRyid());
		pohjxx_dyxxb.setRynbid(vohjxx_czrkjbxxb.getRynbid());
		pohjxx_dyxxb.setGmsfhm(vohjxx_czrkjbxxb.getGmsfhm());
		pohjxx_dyxxb.setXm(vohjxx_czrkjbxxb.getXm());
		pohjxx_dyxxb.setPcs(vohjxx_czrkjbxxb.getPcs());
		pohjxx_dyxxb.setSsxq(vohjxx_czrkjbxxb.getSsxq());
		//?????? ??????  01  ????????????  02  ??????????????? 03  ?????????04  ????????????  05  ?????????    06  ?????????    07  ????????????   08   ???????????????      09  ????????????????????????????????????     10  ????????????????????????   11
		if(lodopId.equals("czrkdjbq")||lodopId.equals("czrkdjbthf")||lodopId.equals("czrkdjbq")) {//???????????????????????????
			pohjxx_dyxxb.setDylb("01");
			pohjxx_dyxxb.setZjbh(vohjxx_czrkjbxxb.getGmsfhm());//??????????????????????????????????????????
		}else if(lodopId.equals("jmhkb_sy")) {//?????????????????????
			pohjxx_dyxxb.setDylb("03");
			pohjxx_dyxxb.setZjbh(vohjxx_czrkjbxxb.getHh());//??????????????????????????????????????????
		}else if(lodopId.equals("jmhkb_bm")) {//???????????????
			pohjxx_dyxxb.setDylb("04");
			pohjxx_dyxxb.setZjbh(vohjxx_czrkjbxxb.getHh());//??????????????????????????????????????????
		}else if(lodopId.equals("jmhkb_jthfs_sy")) {//????????????????????????
			pohjxx_dyxxb.setDylb("04");
			pohjxx_dyxxb.setZjbh(vohjxx_czrkjbxxb.getHh());//??????????????????????????????????????????
		}else if(lodopId.equals("jmhkb_ny")) {//???????????????
			pohjxx_dyxxb.setDylb("04");
			pohjxx_dyxxb.setZjbh(vohjxx_czrkjbxxb.getHh());//??????????????????????????????????????????
		}else if(lodopId.equals("hjzm")) {//????????????
			pohjxx_dyxxb.setDylb("05");
			pohjxx_dyxxb.setZjbh(vohjxx_czrkjbxxb.getGmsfhm());//??????????????????????????????????????????
		}else if(lodopId.equals("djksyb")) {//??????????????????
			pohjxx_dyxxb.setDylb("04");
			pohjxx_dyxxb.setZjbh(vohjxx_czrkjbxxb.getHh());//??????????????????????????????????????????
		}else if(lodopId.equals("zdydy")) {//???????????????
			pohjxx_dyxxb.setDylb("");//??????   
			pohjxx_dyxxb.setZjbh(vohjxx_czrkjbxxb.getGmsfhm());//??????????????????????????????????????????
		}else if(lodopId.equals("zqz")) {//???????????????
			pohjxx_dyxxb.setDylb("07");//?????????
			pohjxx_dyxxb.setZjbh(zjbh);//??????????????????????????????????????????
		}else if(lodopId.equals("qyz1")||lodopId.equals("qyz2")) {//???????????????
			pohjxx_dyxxb.setDylb("06");//??????   
			pohjxx_dyxxb.setZjbh(zjbh);//??????????????????????????????????????????
		}else if(lodopId.equals("hjblspb")) {//???????????????????????????
			pohjxx_dyxxb.setDylb("12");
			pohjxx_dyxxb.setZjbh(vohjxx_czrkjbxxb.getGmsfhm());//??????????????????????????????????????????
		}else if(lodopId.equals("hjscspb")) {//???????????????????????????
			pohjxx_dyxxb.setDylb("13");
			pohjxx_dyxxb.setZjbh(vohjxx_czrkjbxxb.getGmsfhm());//??????????????????????????????????????????
		}else if(lodopId.equals("swzxzm")) {//????????????????????????
			pohjxx_dyxxb.setDylb("08");
			pohjxx_dyxxb.setZjbh(vohjxx_czrkjbxxb.getGmsfhm());//??????????????????????????????????????????
		}else if(lodopId.equals("hjzmx")) {//???????????????
			pohjxx_dyxxb.setDylb("14");
			pohjxx_dyxxb.setZjbh(vohjxx_czrkjbxxb.getGmsfhm());//??????????????????????????????????????????
		}else if(lodopId.equals("wdjhk")) {//???????????????????????????????????????
			pohjxx_dyxxb.setDylb("15");
			pohjxx_dyxxb.setZjbh(vohjxx_czrkjbxxb.getGmsfhm());//??????????????????????????????????????????
		}else if(lodopId.equals("fhzm")) {//????????????
			pohjxx_dyxxb.setDylb("16");
			pohjxx_dyxxb.setZjbh(vohjxx_czrkjbxxb.getGmsfhm());//??????????????????????????????????????????
		}else if(lodopId.equals("bggzxmzm")) {//??????????????????????????????????????????
			pohjxx_dyxxb.setDylb("17");
			pohjxx_dyxxb.setZjbh(vohjxx_czrkjbxxb.getGmsfhm());//??????????????????????????????????????????
		}else if(lodopId.equals("gatdjzxzm")) {//????????????????????????????????????????????????
			pohjxx_dyxxb.setDylb("18");
			pohjxx_dyxxb.setZjbh(vohjxx_czrkjbxxb.getGmsfhm());//??????????????????????????????????????????
		}else if(lodopId.equals("gwdjzxzm")) {//??????????????????????????????
			pohjxx_dyxxb.setDylb("19");
			pohjxx_dyxxb.setZjbh(vohjxx_czrkjbxxb.getGmsfhm());//??????????????????????????????????????????
		}else if(lodopId.equals("sftyrxzhczm")) {//??????????????????????????????????????????
			pohjxx_dyxxb.setDylb("20");
			pohjxx_dyxxb.setZjbh(vohjxx_czrkjbxxb.getGmsfhm());//??????????????????????????????????????????
		}else if(lodopId.equals("qczxzmly")||lodopId.equals("hkzxzm")) {//??????????????????
			pohjxx_dyxxb.setDylb("21");
			pohjxx_dyxxb.setZjbh(vohjxx_czrkjbxxb.getGmsfhm());//??????????????????????????????????????????
		}else if(lodopId.equals("zxqsgxzm")) {//??????????????????????????????
			pohjxx_dyxxb.setDylb("22");
			pohjxx_dyxxb.setZjbh(vohjxx_czrkjbxxb.getGmsfhm());//??????????????????????????????????????????
		}else if(lodopId.equals("cbqdqcwb")||lodopId.equals("cbtdqcwb")) {//????????????????????????
			pohjxx_dyxxb.setDylb("23");
			pohjxx_dyxxb.setZjbh(vohjxx_czrkjbxxb.getGmsfhm());//??????????????????????????????????????????
		}
		pohjxx_dyxxb.setSlsj(slsj);//????????????  20180702112346
		pohjxx_dyxxb.setSldw(this.getUserInfo().getDwdm());//????????????  340700000
		pohjxx_dyxxb.setSlrid(this.getUserInfo().getYhid());//?????????id  3407000001000000001   
		pohjxx_dyxxb.setCzip(BaseContext.getUser().getIp());
		pohjxx_dyxxb.setYznf(yznf);//????????????
		pohjxx_dyxxb.setMlpnbid(vohjxx_czrkjbxxb.getMlpnbid());//???????????????ID  3407000001000250366
		pohjxx_dyxxb.setJlx(vohjxx_czrkjbxxb.getJlx());//?????????   340702002060
		pohjxx_dyxxb.setMlph(vohjxx_czrkjbxxb.getMlph());//????????????    274???
		pohjxx_dyxxb.setMlxz(vohjxx_czrkjbxxb.getMlxz());//????????????  ????????????????????????
		pohjxx_dyxxb.setZrq(vohjxx_czrkjbxxb.getZrq());//?????????  
		pohjxx_dyxxb.setXzjd(vohjxx_czrkjbxxb.getXzjd());//????????????  340702005
		pohjxx_dyxxb.setJcwh(vohjxx_czrkjbxxb.getJcwh());//??????????????????    340702006005  
		SpringContextHolder.getCommonService().insertObject(pohjxx_dyxxb);
	}

	@Override
	public List queryZqzList(ExtMap<String, Object> params) {
		List poHJSP_ZQZXXBList = super.getPageRecordsOflodop("/conf/segment/common", "zqzList", params).getList();
		
		PoHJSP_ZQZXXB poHJSP_ZQZXXB = new PoHJSP_ZQZXXB();
		List list = new ArrayList<>();
		for(int i=0;i<poHJSP_ZQZXXBList.size();i++) {
			String hql="";
			
			poHJSP_ZQZXXB = (PoHJSP_ZQZXXB) poHJSP_ZQZXXBList.get(i);
			int arrayLength=1;
			if(poHJSP_ZQZXXB.getQyrxm2()!=null&&poHJSP_ZQZXXB.getQyrxm3()==null&&poHJSP_ZQZXXB.getQyrxm4()==null) {
				arrayLength=2;
			}else if(poHJSP_ZQZXXB.getQyrxm2()!=null&&poHJSP_ZQZXXB.getQyrxm3()!=null&&poHJSP_ZQZXXB.getQyrxm4()==null) {
				arrayLength=3;
			}else if(poHJSP_ZQZXXB.getQyrxm2()!=null&&poHJSP_ZQZXXB.getQyrxm3()!=null&&poHJSP_ZQZXXB.getQyrxm4()!=null) {
				arrayLength=4;
			}
			for(int y=0;y<arrayLength;y++) {
				VoZqzxx vozqzxx = new VoZqzxx();
				String temp = "";
				if(y==0) {
					temp = poHJSP_ZQZXXB.getQyrgmsfhm1();
				}else if(y==1) {
					temp = poHJSP_ZQZXXB.getQyrgmsfhm2();
				}else if(y==2) {
					temp = poHJSP_ZQZXXB.getQyrgmsfhm3();
				}else if(y==3) {
					temp = poHJSP_ZQZXXB.getQyrgmsfhm4();
				}
				hql ="from "+PoHJSP_HJSPZB.class.getName()+" where  spywid='"+poHJSP_ZQZXXB.getSpywid()+"' and gmsfhm='"+temp+"'";
				List voHjsp_zqzxxb = super.findAllByHQL(hql);
				try {
					BeanUtils.copyProperties(vozqzxx, voHjsp_zqzxxb.get(0));
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
				vozqzxx.setZqid(poHJSP_ZQZXXB.getZqid());
				list.add(vozqzxx);
			}
		}
		return list;
	}

	@Override
	public VoQyzdyxxHqFhxx queryQyzList(ExtMap<String, Object> params) {
		Long hjywid = params.getLong("hjywid");
		//hjService.queryQyzdyxx(hjywid);
		return hjService.queryQyzdyxx(hjywid);
	}	
	
	@Override
	public List<?> queryXt_bssqb(ExtMap<String, Object> params) {
		return super.getObjectListByHql("from PoXT_BSSQB where qybz='1'");
	}
	
	public List<?> getZp(ExtMap<String, Object> params){
		Long zpid = params.getLong("zpid");
		String hql = "from PoHJXX_RYZPXXB where zpid=?";
		return super.getObjectListByHql(hql, new Object[]{zpid});
	}
	
	public Page queryKDQHjspyw(ExtMap<String,Object> params){
		return super.getPageRecords("/conf/segment/common", "queryKDQHjspyw", params);
	}
	
	public VoHxxHqFhxx queryHxx(ExtMap<String, Object> params){
		List<?> list = super.getObjectListByHql("/conf/segment/common", "queryCommonHxx", params);
		if(list!=null && list.size()==1){
			Object[] obj = (Object[])list.get(0);
			PoHJXX_HXXB a = (PoHJXX_HXXB)obj[0];
			PoHJXX_MLPXXXXB b = (PoHJXX_MLPXXXXB)obj[1];
			
			VoHxxHqFhxx fh = new VoHxxHqFhxx(a,b);
			return fh;
		}
		
		return null;
	}

	@Override
	public Page queryPoHJXX_CZRKJBXXB1(ExtMap<String, Object> params) {
		return super.getPageRecords("/conf/segment/common", "queryPoHJXX_CZRKJBXXB5", params);
	}

	@Override
	public Page queryPoHJXX_CZRKJBXXB6(ExtMap<String, Object> params) {
		return super.getPageRecords("/conf/segment/common", "queryPoHJXX_CZRKJBXXB6", params);
	}
	@Override
	public List<?> getYdZp(ExtMap<String, Object> params) {
		Long zpid = params.getLong("zpid");
		String hql = "from PoHJXX_ZPLSB where zpid=?";
		return super.getObjectListByHql(hql, new Object[]{zpid});
	}

	@Override
	public Page queryMessage(ExtMap<String, Object> params) {
		// TODO Auto-generated method stub
		return super.getPageRecords("/conf/segment/common", "queryMessage", params);
//		if(params.containsKey("jqcx") && params.get("jqcx").equals("1")){
//			return super.getPageRecords("/conf/segment/common", "queryMessage", params);
//		}else {
//			return null;
//		}
	}

	@Override
	public VoMessageRtxx xxhf(ExtMap<String, Object> params) {
		VoMessagexx voMessagexx = new VoMessagexx();
		try {
			BeanUtils.copyProperties(voMessagexx, params);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return messageService.processXxhf(voMessagexx);
	}

	@Override
	public VoMessagexx deleteMessage(ExtMap<String, Object> params) {
		Long messageid = params.getLong("messageid");
		return messageService.deleteMessage(messageid);
	}

	@Override
	public VoMessagexx updateMessage(ExtMap<String, Object> params) {
		Long messageid = params.getLong("messageid");
		return messageService.updateMessage(messageid);
	}

	@Override
	public List<?> checkUnReadMessage(ExtMap<String, Object> params) {
		Long jsryhid = params.getLong("jsryhid");
		String dwdm = params.getString("dwdm");
		return messageService.checkUnReadMessage(jsryhid,dwdm);
		//return super.getPageRecords("/conf/segment/common", "checkUnReadMessage", params);
	}

	@Override
	public Page queryRhfl(ExtMap<String, Object> params) {
		return super.getPageRecords("/conf/segment/common", "queryRhfl", params);
	}

	@Override
	public VoRhflywfhxx addRhfl(ExtMap<String, Object> params) {
		String rhflxx = params.getString("VoRhflywfhxx");
		VoRhflxx voRhflxx[] = null;
		if(CommonUtil.isNotEmpty(rhflxx)){
			TypeToken<List<VoRhflxx>> typeToken = new TypeToken<List<VoRhflxx>>(){};
			List<VoRhflxx> list  = JSONUtil.getJsonData(typeToken, rhflxx);
			voRhflxx = list.toArray(new VoRhflxx[]{});
		}
		return hjService.processRhflyw(voRhflxx);
	}

	@Override
	public PoHJXX_RHFLXXB deleteRhfl(ExtMap<String, Object> params) {
		String rhflid = params.getString("rhflid");
		if(CommonUtil.isNotEmpty(rhflid)) {
			PoHJXX_RHFLXXB poHjxx_rhfkxxb= super.get(PoHJXX_RHFLXXB.class,
					Long.parseLong(rhflid));
			super.delete(poHjxx_rhfkxxb);
			return poHjxx_rhfkxxb;
		}
		return null;
	}
	
	//???????????????
	static private Map<String,String> gnMap = new HashMap<>();
	  
	public void saveRzxx(AuthToken user, String logcode, String logstr, long startTime, String startDate){
		 if(gnMap.size()==0){
		        synchronized(gnMap){
		        		if(gnMap.size()==0) {
		        			List<?> xtgnb = super.findAllByHQL("from PoXT_XTGNB");
		        			for(java.util.Iterator<?> it=xtgnb.iterator();it.hasNext();){
		        				PoXT_XTGNB gn = (PoXT_XTGNB)it.next();
		        				gnMap.put(gn.getGnbh(),gn.getGnmc());
		        			}
		        	}
		        }
		        gnMap.put("F1041","??????????????????");
		 }
		 
		String lognameString = gnMap.get(logcode);
		if(CommonUtil.isEmpty(lognameString))
			return;
		
		PoXT_XTRZB rz = new PoXT_XTRZB();
        if(logstr.getBytes().length>2000){
              byte[] buff = new byte[2000];
              System.arraycopy(logstr.getBytes(),0,buff,0,2000);
              logstr = new String(buff);
         }
        
         rz.setRznr(logstr);
         String strCzJssj = StringUtils.formateDateTime();
         rz.setCzkssj(startDate);
         rz.setCzjssj(strCzJssj);
         rz.setCzyid(user.getUser().getYhid());
         rz.setYwbz(logcode);
         rz.setResource_name(lognameString);
         rz.setKhdip(user.getIp());
         rz.setRzlx(PublicConstant.RZLX_XTCZ);
         rz.setZxsj(new Long(System.currentTimeMillis()-startTime));
         rz.setOrganization_id(user.getUser().getDwdm());
         rz.setOrganization(user.getOrganize().getMc());
         rz.setCzyxm(user.getUser().getYhdlm());
         rz.setOperate_result("1");
         rz.setResource_type("1");
         
         if(rz.getResource_name()!=null){
             if (rz.getResource_name().startsWith("??????") ||
            		 rz.getResource_name().endsWith("??????")) {
            	 rz.setOperate_type("4");
             }
             else if (rz.getResource_name().indexOf("??????") >= 0) {
            	 rz.setOperate_type("1");
             }
             if (rz.getResource_name().startsWith("??????") ||
            		 rz.getResource_name().endsWith("??????")
                 || rz.getResource_name().startsWith("??????") ||
                 rz.getResource_name().endsWith("??????")) {
            	 rz.setOperate_type("2");
             }
           }
           if(rz.getYwbz()!=null && rz.getYwbz().equals("F9084")){
        	   rz.setOperate_type("0");
           }
           
		Long rzid = (Long) super.getIdBySequence(PoXT_XTRZB.class);
		rz.setRzid(rzid);
		
		super.create(rz);
	}

	@Override
	public PoSFXXB bjfyySave(ExtMap<String, Object> params) {
		String type = params.getString("type");
		String bzxjfyy = params.getString("bzxjfyy");       // bzxjfyy
		Long sfxxbid = params.getLong("sfxxbid"); 
		PoSFXXB sfxxb= super.get(PoSFXXB.class, sfxxbid);
		if(type.equals("bzx")) {
			sfxxb.setBzxjfyy(bzxjfyy);
		}
		sfxxb.setJfflag("0");
		super.update(sfxxb);
		return sfxxb;
	}

	@Override
	public PoSFXXB insertSfxxb(VoSfxxb vosfxxb) {
		PoSFXXB sfxxb= new PoSFXXB();
		try {
			String dylbValue = vosfxxb.getDylb();
			if(CommonUtil.isEmpty(dylbValue)) {
				throw new ServiceException("?????????????????????");
			}
			if(vosfxxb.getJe()==null) {
				throw new ServiceException("?????????????????????");
			}
			BeanUtils.copyProperties(sfxxb, vosfxxb);
			if(dylbValue.equals("jmhkb_sy")) {
				sfxxb.setDylb("03");
			}else if(dylbValue.equals("zqz")) {
				sfxxb.setDylb("07");
			}else if(dylbValue.equals("qyz1")||dylbValue.equals("qyz2")) {
				sfxxb.setDylb("06");
			}
			PoXT_YHXXB xt_yhxxb = this.getUser();
			sfxxb.setDwdm(xt_yhxxb.getDwdm());
			sfxxb.setDysj(DateHelper.formateDate("yyyyMMddHHmmss"));
			sfxxb.setCzyid(xt_yhxxb.getYhid());
			PoXT_DWXXB dwxxb = super.get(PoXT_DWXXB.class,xt_yhxxb.getDwdm());
			sfxxb.setQhdm(dwxxb.getQhdm());
			Long sfid = (Long) super.getIdBySequence(PoSFXXB.class);
			sfxxb.setSfxxbid(sfid);
			super.create(sfxxb);
			
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return sfxxb;
	}

	@Override
	public int queryDycsByhh(ExtMap<String, Object> params) {
		String rynbid = params.getString("rynbid");
		if(CommonUtil.isNotEmpty(rynbid)) {
			PoHJXX_CZRKJBXXB hjxx_czrkjbxxb = super.get(PoHJXX_CZRKJBXXB.class, Long.parseLong(rynbid));
			String hhnbid = hjxx_czrkjbxxb.getHhnbid()+"";
			if(CommonUtil.isNotEmpty(hhnbid)) {
				String sql = "select count(*) from PoHJXX_CZRKJBXXB_DYLS where hhnbid='" + hhnbid + "'";
				return Integer.parseInt(String.valueOf(super.getCount(sql)));
			}else {
				throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
	                    "hhnbid????????????", null);
			}
		}else {
			throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                    "rynbid????????????", null);
		}
		
		
	}

	@Override
	public PoPERSON_DY_SET getPersonDyset(ExtMap<String, Object> params) {
		String sql = " from PoPERSON_DY_SET where yhid='" + this.getUser().getYhid() + "'";
		return (PoPERSON_DY_SET) super.getObject(sql, null);
	}

	@Override
	public Page validHhid(ExtMap<String, Object> params) {
		return super.getPageRecords("/conf/segment/common", "validHhid", params);
	}

	@Override
	public PoHJXX_JWHZPLSB uploadZp(MultipartHttpServletRequest params) throws IOException {
		PoHJXX_JWHZPLSB jwhzplsb= new PoHJXX_JWHZPLSB();
		MultipartFile logoFile = params.getFile("logoFile");
		String zpType = params.getParameter("zpType");
		String jwhdm = params.getParameter("dm");
		PoXT_JWHXXB jwhxxb = super.get(PoXT_JWHXXB.class, jwhdm);
		String sql = " from PoHJXX_JWHZPLSB where jwhdm = '"+jwhdm+"' and zpType = '"+zpType+"' ";
		List<PoHJXX_JWHZPLSB> jwhzplsbList = (List<PoHJXX_JWHZPLSB>) super.getObjectListByHql(sql);
		FileInputStream inputStream;
		try {
//			inputStream = new FileInputStream(realPath);
//			int i = inputStream.available();
//			//byte????????????????????????????????????
//			byte[] buff = new byte[i];
//			 byte[] imageByte;
//			 File file1 = new File(realPath);//?????????????????????????????????????????????????????????
//			 FileInputStream fls = new FileInputStream(file1);
//			 imageByte = new byte[ (int) file1.length() ];
			 
			 
			if(jwhzplsbList.size()==0) {
				PojoInfo  hjxx_jwhzplsbDAO = DAOFactory.createHJXX_JWHZPLSBDAO();
				Long id = (Long) hjxx_jwhzplsbDAO.getId();
				jwhzplsb.setJwhdm(jwhdm);
				jwhzplsb.setJwhzpid(id);
				jwhzplsb.setCzsj(StringUtils.getServiceTime());
				jwhzplsb.setCzrid(this.getUser().getYhid());
				jwhzplsb.setZpType(zpType);
				jwhzplsb.setZp(logoFile.getBytes());
				super.create(jwhzplsb);
				if(zpType.equals("1")) {
					jwhxxb.setHzzpid(id);
				}else if(zpType.equals("2")) {
					jwhxxb.setQmzpid(id);
				}
				jwhxxb.setBdsj(StringUtils.getServiceTime());
				jwhxxb.setCzrid(this.getUser().getYhid());
			}else {
				jwhzplsb = jwhzplsbList.get(0);
				jwhzplsb.setCzsj(StringUtils.getServiceTime());
				jwhzplsb.setCzrid(this.getUser().getYhid());
				jwhzplsb.setZp(logoFile.getBytes());
				jwhxxb.setBdsj(StringUtils.getServiceTime());
				jwhxxb.setCzrid(this.getUser().getYhid());
				super.update(jwhzplsb);
			}
			super.update(jwhxxb);
//			fls.read(imageByte);
//			fls.close();
			return null;
		} catch (FileNotFoundException e) {
			throw e;
			//e.printStackTrace();
		} catch (IOException e) {
			throw e;
//			e.printStackTrace();
		}
	}

	@Override
	public VoBb checkDjJth(ExtMap<String, Object> params) {
		VoBb vobb = new VoBb();
		PoHJXX_HXXB hxxb = new PoHJXX_HXXB();
		Long hhnbid = params.getLong("hhnbid");
		Long hhid = params.getLong("hhid");
		String gmsfhm = params.getString("gmsfhm");
		StringBuffer hqlbuffer = new StringBuffer();
		if(CommonUtil.isNotEmpty(params.getString("hhnbid"))) {
			hxxb = super.get(PoHJXX_HXXB.class, hhnbid);
			vobb.setJttdbz(hxxb.getJttdbz());
			hqlbuffer.append("from PoHJXX_CZRKJBXXB where ryzt='0' and cxbz='0' and jlbz='1'");
			if(CommonUtil.isNotEmpty(params.getString("gmsfhm"))) {
				hqlbuffer.append("  and gmsfhm='"+gmsfhm+"'");
			}
			if(CommonUtil.isNotEmpty(params.getString("hhnbid"))) {
				hqlbuffer.append("  and hhnbid='"+hhnbid+"'");
			}
		}else if(CommonUtil.isNotEmpty(params.getString("hhid"))) {
			String hql = "from PoHJXX_HXXB where  cxbz='0' and jlbz='1' and hhid ='"+hhid+"'";
			List<PoHJXX_HXXB> listhxx = (List<PoHJXX_HXXB>) queryAll(hql);
			if(listhxx.size()>0) {
				hxxb = listhxx.get(0);
				vobb.setJttdbz(hxxb.getJttdbz());
			}
			hqlbuffer.append("from PoHJXX_CZRKJBXXB where ryzt='0' and cxbz='0' and jlbz='1'");
			if(CommonUtil.isNotEmpty(params.getString("hhid"))) {
				hqlbuffer.append("  and hhid='"+hhid+"'");
			}
		}
		List<PoHJXX_CZRKJBXXB> list = (List<PoHJXX_CZRKJBXXB>) queryAll(hqlbuffer.toString());
		if(list.size()>0) {
			PoHJXX_CZRKJBXXB hjxx_czrkjbxxb = list.get(0);
			vobb.setDjzt(hjxx_czrkjbxxb.getDjzt());//hjxx_czrkjbxxb.getDjzt()
		}
		return vobb;
	}

//	@Override
//	public PoUPLOAD_TEMP commonUploadZp(MultipartHttpServletRequest params) throws IOException{
//		PoUPLOAD_TEMP uploadTemp = new PoUPLOAD_TEMP();
//		MultipartFile logoFile = params.getFile("logoFile");
//		try {
//			PojoInfo  upload_tempDAO = DAOFactory.createUPLOAD_TEMPDAO();
//			Long id = (Long) upload_tempDAO.getId();
//			uploadTemp.setUploadzpid(id);
//			uploadTemp.setCzsj(StringUtils.getServiceTime());
//			uploadTemp.setCzrid(this.getUser().getYhid());
//			uploadTemp.setZp(logoFile.getBytes());
//			super.create(uploadTemp);
//			return null;
//		} catch (FileNotFoundException e) {
//			throw e;
//		} catch (IOException e) {
//			throw e;
//		}
//	
//	}

	@Override
	public void downZp(
			HttpServletRequest request,
			HttpServletResponse response,ExtMap<String, Object> params) throws IOException,	ServletException{
//		try {
//			String savePath = params.getString("path");
//			Long ryid = params.getLong("ryid");
//			String sql = " from PoHJXX_RYZPXXB where ryid = '"+ryid+"'";
//			List<PoHJXX_RYZPXXB> hjxx_ryzpxxbList = (List<PoHJXX_RYZPXXB>) super.getObjectListByHql(sql);
//			for(int i = 0;i<hjxx_ryzpxxbList.size();i++) {
//				PoHJXX_RYZPXXB  ryzpxxb = hjxx_ryzpxxbList.get(i);
//				InputStream is = new ByteArrayInputStream(ryzpxxb.getZp());  
//				// 1K???????????????  
//		        byte[] bs = new byte[1024];  
//		        // ????????????????????????  
//		        int len;  
//		        // ??????????????????  
//		       File sf=new File(savePath);  
//		       if(!sf.exists()){  
//		           sf.mkdirs();  
//		       }  
//		       // ????????????????????????  filename  1_li1325169021.jpg
//		       String extensionName = "png";//filename.substring(filename.lastIndexOf(".") +     1);
//		       String bianhao =hjxx_ryzpxxbList.size()>1?(1+i)+"":"";
//		       // ????????????????????? = ?????? +"."???????????????
//		       String newFileName = ryzpxxb.getGmsfhm()+bianhao+"." + extensionName;
//		       OutputStream os = new FileOutputStream(sf.getPath()+"\\"+newFileName);  
//		        // ????????????  
//		        while ((len = is.read(bs)) != -1) {  
//		          os.write(bs, 0, len);  
//		        }  
//		        // ???????????????????????????  
//		        os.close();  
//		        is.close();  
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return;
		
//		
//		InputStream inputstream = new FileInputStream("c:/test.doc");
//		ServletOutputStream servletoutputstream;
//      //????????????????????????
//        String filename = 
//   			new String("aaa.doc".getBytes(),"iso-8859-1");
//   		rep.setHeader("Content-Disposition",
//   					"attachment;filename=\"" + filename + "\"");
//   		rep.setHeader("path",
//					"c:/"+filename);
//   		servletoutputstream = rep.getOutputStream();
//		try {
//			byte abyte0[] = new byte[2048];
//			for (int i = inputstream.read(abyte0); i != -1; 
//				i = inputstream.read(abyte0))
//				servletoutputstream.write(abyte0, 0, i);
//				
//			servletoutputstream.flush();
//		} catch (IOException _ex) {
//			rep.getOutputStream().println(_ex.toString());
//			return;
//		} finally {				
//			inputstream.close();
//			servletoutputstream.close();
//		}
		
		
//		String fileName = params.getString("fileName");
//		String savePath = params.getString("path");
//		response.setContentType("application/octet-stream");
//		fileName = URLDecoder.decode(fileName, "utf-8");
//		// ??????response
//		response.reset();
//		// ??????response???Header
//		response.addHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("gbk"),"iso8859-1"));
//		try{
//			Long zpid = params.getLong("zpid");
//			PoHJXX_RYZPXXB hjxx_ryzpxxb  = super.get(PoHJXX_RYZPXXB.class, zpid);
//		//???????????????????????????
//		InputStream fis = new ByteArrayInputStream(hjxx_ryzpxxb.getZp());
//		byte[] buffer = new byte[fis.available()];
//		fis.read(buffer);
//		fis.close();
//		OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
//		toClient.write(buffer);
//		toClient.flush();
//		toClient.close();
//		}catch(Exception e){
//		e.printStackTrace();
//		}
		zipFile("d:/temp/",params);
		return;
	}
	
	private void zipFile(String sourcePath,ExtMap<String, Object> params){
        /**?????????????????????????????????
         * ???????????????????????????????????????????????????
         * ????????????????????????????????????.rar??????.zip*/
        FileOutputStream fos = null;  
        ZipOutputStream zos = null;  
        //String path = "c:/guidang.zip";//?????????????????????????????????????????????????????????????????????????????????
        //windows????????????c????????????????????????????????????Linux????????????????????????????????????????????????????????????????????????????????????tempPath???
        String path = "tempPath.zip";
        File sourceFile = new File(sourcePath);
        File zipFile = new File(path);
        try {
            if (!zipFile.exists()){   
                zipFile.createNewFile();   
            }
            if (!sourceFile.exists()){   
            	sourceFile.mkdir();  
            }
			Long ryid = params.getLong("ryid");
			String sql = " from PoHJXX_RYZPXXB where ryid = '"+ryid+"'";
			List<PoHJXX_RYZPXXB> hjxx_ryzpxxbList = (List<PoHJXX_RYZPXXB>) super.getObjectListByHql(sql);
			for(int i = 0;i<hjxx_ryzpxxbList.size();i++) {
				PoHJXX_RYZPXXB  ryzpxxb = hjxx_ryzpxxbList.get(i);
				InputStream is = new ByteArrayInputStream(ryzpxxb.getZp());  
				// 1K???????????????  
		        byte[] bs = new byte[1024];  
		        // ????????????????????????  
		        int len;  
		        // ??????????????????  
		       // ????????????????????????  filename  1_li1325169021.jpg
		       String extensionName = "png";//filename.substring(filename.lastIndexOf(".") +     1);
		       String bianhao =hjxx_ryzpxxbList.size()>1?(1+i)+"":"";
		       // ????????????????????? = ?????? +"."???????????????
		       String newFileName = ryzpxxb.getXm()+ryzpxxb.getGmsfhm()+bianhao+"." + extensionName;
		       OutputStream os = new FileOutputStream(sourceFile.getPath()+"\\"+newFileName);  
		        // ????????????  
		        while ((len = is.read(bs)) != -1) {  
		          os.write(bs, 0, len);  
		        }  
		        // ???????????????????????????  
		        os.close();  
		        is.close();  				
			}
            //?????????????????????
            fos = new FileOutputStream(zipFile);   
            /**??????????????????????????????ZipOutputStream?????????????????????,
             * ??????????????????????????????????????????*/
            zos = new ZipOutputStream(fos);
            writeZip(sourceFile, "", zos);
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
            throw new RuntimeException(e);  
        } catch (IOException e) {  
            e.printStackTrace();  
            throw new RuntimeException(e);  
        } finally{  
            try {
                if (zos != null) {
                    zos.close();
                }
            } catch (IOException e) {
            	e.printStackTrace();
                //log.error("??????ZIP????????????",e);
            }
            this.downLoad(path);
            //??????????????????
            if (zipFile.exists()) {
                zipFile.delete();
            }
            if (sourceFile.exists()) {
            	if (sourceFile.isDirectory()) {
                    String[] children = sourceFile.list();//????????????????????????????????????
                    for (int i=0; i<children.length; i++) {
                    	(new  File(sourceFile, children[i])).delete();
                    }
                }
            }
            sourceFile.delete();
        }
    }
	private static void writeZip(File file, String parentPath, ZipOutputStream zos) {
        if(file.exists()){
            if(file.isDirectory()){//???????????????
                parentPath+=file.getName()+File.separator;
                File [] files=file.listFiles();
                for(File f:files){
                    writeZip(f, parentPath, zos);
                }
            }else{

                FileInputStream fis=null;
                try {
                    fis=new FileInputStream(file);
                    ZipEntry ze = new ZipEntry(parentPath + file.getName());
                    zos.putNextEntry(ze);
                    byte [] content=new byte[1024];
                    int len;
                    while((len=fis.read(content))!=-1){
                        zos.write(content,0,len);
                        zos.flush();
                    }

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally{
                     //?????????  
                    try {  
                        if(null != fis) fis.close();  
                    } catch (IOException e) {  
                        e.printStackTrace();  
                        throw new RuntimeException(e);  
                    } 
                }
            }
        }
    } 
	public void downLoad(String filePath){

        HttpServletResponse response = BaseContext.getContext().getResponse();

        BufferedInputStream bis=null;
        BufferedOutputStream  bos=null;
        try{
             String filename=filePath.substring(filePath.lastIndexOf("/") + 1, filePath.length());
             response.setContentType("image/jpeg");
             response.setCharacterEncoding("gbk");
             response.setHeader("Content-Disposition","filename="+new String(filename.getBytes("gbk"),"iso8859-1"));
             bis =new BufferedInputStream(new FileInputStream(filePath));
             bos=new BufferedOutputStream(response.getOutputStream()); 
             byte[] buff = new byte[2048];
             int bytesread;
             while(-1 != (bytesread = bis.read(buff, 0, buff.length))) {
              bos.write(buff,0,bytesread);
             }
        }catch(Exception e){
             e.printStackTrace();
        }finally {
         if (bis != null)
            try {
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
         if (bos != null)
            try {
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

	@Override
	public void downExcelZip(HttpServletRequest req,HttpServletResponse rep, 
			ExtMap<String, Object> params) {
		String filename = "";
		String FileDate = DateHelper.formateDate("yyyyMMdd");
		try {
			
			String type = params.getString("type");
			if(type==null||type.equals("undefined")) {
				throw new  Exception("???????????????");
			}else {
				ExtMap<String, Object> sqlParam = (ExtMap<String, Object>)BaseContext.getContext().getSession().getAttribute(type);
				Page p = new Page();
				if(type.equals("queryCzrk")) {//????????????
					p = queryService.queryRkxx(sqlParam);
				}else if(type.equals("queryQydjRy")) {//????????????
					p = queryService.queryQydjRy(sqlParam);
				}else if(type.equals("getRkxx")) {//??????????????????
					p = queryService.queryRkxx(sqlParam);
				}else if(type.equals("getHxx")) {//?????????
					p = queryService.getHxx(sqlParam);
				}else if(type.equals("queryMlpxxcx")) {//???????????????
					p = queryService.getMlpxx(sqlParam);
				}else if(type.equals("queryThry")) {//??????????????????
					p = queryService.queryThry(sqlParam);
				}else if(type.equals("xzdcx")) {//???????????????
					p = queryService.queryXxxx(sqlParam);
				}else if(type.equals("qrdjcx")) {//??????????????????
					p = queryService.queryQrxx(sqlParam);
				}else if(type.equals("qczxcx")) {//??????????????????
					p = queryService.queryQcxx(sqlParam);
				}else if(type.equals("csdjcx")) {//??????????????????
					p = queryService.queryCsdjxx(sqlParam);
				}else if(type.equals("swzxcx")) {//??????????????????
					p = queryService.getSwzxxx(sqlParam);
				}else if(type.equals("zzbdcx")) {//??????????????????
					p = queryService.getZzbdxx(sqlParam);
				}else if(type.equals("bggzcx")) {//??????????????????
					p = queryService.getBggzxx(sqlParam);
				}else if(type.equals("hbbgcx")) {//??????????????????
					p = queryService.getHbbgxx(sqlParam);
				}else if(type.equals("hjblcx")) {//??????????????????
					p = queryService.getHjblxx(sqlParam);
				}else if(type.equals("hjsccx")) {//??????????????????
					p = queryService.getHjscxx(sqlParam);
				}else if(type.equals("hcybdcx")) {//?????????????????????
					p = queryService.getHcybdxx(sqlParam);
				}else if(type.equals("bdqkcx")) {//??????????????????
					p = queryService.getBdqkxx(sqlParam);
				}else if(type.equals("dycx")) {//????????????
					p = queryService.getDyxx(sqlParam);
				}else if(type.equals("edzslcx")) {//?????????????????????
					p = queryService.getEdzslxx(sqlParam);
				}else if(type.equals("sjcx")) {//????????????
					p = queryService.getSjxx(sqlParam);
				}else if(type.equals("yscx")) {//????????????
					p = queryService.getYsxx(sqlParam);
				}else if(type.equals("lqffcx")) {//??????????????????
					p = queryService.getLqffxx(sqlParam);
				}else if(type.equals("gscx")) {//????????????
					p = queryService.getGsxx(sqlParam);
				}else if(type.equals("tdcx")) {//????????????
					p = queryService.getTdxx(sqlParam);
				}else if(type.equals("zzfkcx")) {//??????????????????
					p = queryService.getZzfkxx(sqlParam);
				}else if(type.equals("zlfkcx")) {//??????????????????
					p = queryService.getZlfkxx(sqlParam);
				}else if(type.equals("ffjgcx")) {//??????????????????
					p = queryService.getFjjgxx(sqlParam);
				}else if(type.equals("ffjscx")) {//??????????????????
					p = queryService.getFfjsxx(sqlParam);
				}else if(type.equals("sfzcx")) {//???????????????
					p = queryService.getSfzxx(sqlParam);
				}else if(type.equals("chcx")) {//????????????
					p = queryService.getChxx(sqlParam);
				}else if(type.equals("sfhfpcx")) {//?????????????????????
					p = queryService.getSfhfpxx(sqlParam);
				}else if(type.equals("qyzcx")) {//???????????????
					p = queryService.getQyzxx(sqlParam);
				}else if(type.equals("zqzcx")) {//???????????????
					p = queryService.getZqzxx(sqlParam);
				}else if(type.equals("ydzslcx")) {//?????????????????????
					p = queryService.getYdzslxx(sqlParam);
				}else if(type.equals("shcx")) {//????????????
					p = queryService.getShxx(sqlParam);
				}else if(type.equals("xhcx")) {//????????????
					p = queryService.getXhxx(sqlParam);
				}else if(type.equals("qrspcx")) {//????????????
					p = queryService.queryQrsp(sqlParam);
				}else if(type.equals("bgspcx")) {//????????????
					p = queryService.queryBgsp(sqlParam);
				}else if(type.equals("hbbgspcx")) {//??????????????????
					p = queryService.queryHbbgsp(sqlParam);
				}else if(type.equals("hjblspcx")) {//??????????????????
					p = queryService.queryQydjRy(sqlParam);
				}else if(type.equals("hjscspcx")) {//??????????????????
					p = queryService.queryHjscsp(sqlParam);
				}else if(type.equals("pzlogcx")) {//??????????????????
					p = queryService.queryPzxx(sqlParam);
				}else if(type.equals("pzlogcx_ycl")) {//?????????????????????
					p = queryService.queryPzxxycl(sqlParam);
				}else if(type.equals("sfzxxcx")) {//????????????
					p = queryService.querySfzxxcx(sqlParam);
				}else if(type.equals("czyxxcx")) {//???????????????
					p = queryService.queryczyxxcx(sqlParam);
				}else if(type.equals("zjadresscx")) {//????????????
					p = queryService.queryzjAdresscx(sqlParam);
				}else if(type.equals("zzzjrzcx")) {//????????????
					p = queryService.queryrzxxcx(sqlParam);
				}else if(type.equals("tfbdxxcx")) {//??????????????????
					p = queryService.querytfbdcx(sqlParam);
				}else if(type.equals("queryJssdls")) {//????????????????????????
					p = queryService.queryJssdls(sqlParam);
				}else if(type.equals("queryQcclxx")) {//????????????
					p = queryService.queryQcclxx(sqlParam);
				}else if(type.equals("queryFxjsktj")) {//???????????????????????????
					p = queryService.queryFxjsktj(sqlParam);
				}else if(type.equals("fxjsktjcx")) {//???????????????????????????
					p = queryService.queryFxjsktjInfo(sqlParam);
				}else if(type.equals("querysjkfjglxxcx")){//?????????????????????
					p = queryService.querySjkfjgl(sqlParam);
				}else if(type.equals("querySjkxxgl")){//?????????????????????
					p = queryService.querySjkxx(sqlParam);
				}else if(type.equals("queryCsxx")||type.equals("queryHbbgxx")||type.equals("queryQcxx")
						||type.equals("queryQrxx")||type.equals("querySjblxx")||type.equals("querySjscxx")||
						type.equals("querySwxx")||type.equals("queryZzbdxx")) {//????????????
					p = queryService.queryXxxx(sqlParam);
				}
				     
				filename = type+FileDate+".xls.zip";	
				String zipname = filename.substring(0,filename.length()-4);
				rep.setHeader("Content-Disposition", "attachment;filename=\"" +  new String(filename.getBytes("gb2312"), "ISO8859-1" ) + "\"");

				org.apache.tools.zip.ZipOutputStream out = new org.apache.tools.zip.ZipOutputStream(rep.getOutputStream());
				org.apache.tools.zip.ZipEntry zipEntry = new org.apache.tools.zip.ZipEntry(zipname);
				
				out.putNextEntry(zipEntry);
				out.setEncoding("GBK");
				out.setComment("????????????????????????????????????????????????");
				
				String[] shuxing = sqlParam.getString("shuxing").split(",");
				String[] header = java.net.URLDecoder.decode(sqlParam.getString("header"), "UTF-8").split(",");
				JSONObject zdyValueKey = (JSONObject) JSONObject.parse(sqlParam.getString("zdyValueKey"));
				importXlsfile(req,out,p.getList(),shuxing,header,zdyValueKey,type);
				out.flush();
				out.close();
			}
			return;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return; 
		}
		
	}
	
	public  void importXlsfile(HttpServletRequest req,
			OutputStream buffout, List<?> l, String[] shuxing, String[] header, JSONObject zdyValueKey,String type) throws ServletException, IOException {
		boolean sbFlag = (type.equals("queryCsxx")||type.equals("queryHbbgxx")||type.equals("queryQcxx")
				||type.equals("queryQrxx")||type.equals("querySjblxx")||type.equals("querySjscxx")||
				type.equals("querySwxx")||type.equals("queryZzbdxx"))?true:false; 
		try{
			jxl.WorkbookSettings a = new jxl.WorkbookSettings();
			InputStream is = null;
			is = FileUtils.class.getResourceAsStream("/conf/daochu.xls");
			//ByteArrayOutputStream buffout = new ByteArrayOutputStream();
			Workbook rwb = Workbook.getWorkbook(is);
			WritableWorkbook wwb = Workbook.createWorkbook(buffout, rwb, a);
			WritableSheet ws = wwb.getSheet(0);
			//4?????????titles
			//5:?????????
			Label label=null;
			if(sbFlag) {
				String zibiaoti ="";
				if(type.equals("queryCsxx")) {
					zibiaoti = "??????";
				}else if(type.equals("queryHbbgxx")) {
					zibiaoti = "????????????";
				}else if(type.equals("queryQcxx")) {
					zibiaoti = "??????";
				}else if(type.equals("queryQrxx")) {
					zibiaoti = "??????";
				}else if(type.equals("querySjblxx")) {
					zibiaoti = "????????????";
				}else if(type.equals("querySjscxx")) {
					zibiaoti = "????????????";
				}else if(type.equals("querySwxx")) {
					zibiaoti = "??????";
				}else if(type.equals("queryZzbdxx")) {
					zibiaoti = "????????????";
				}
				//?????????
				label=new Label(1,0,zibiaoti);
			    ws.addCell(label);
			}
			//6:????????????????????????
			for(int i=0;i<header.length;i++){
			    //x,y,??????????????????
			    label=new Label(i,sbFlag?1:0,header[i]);
			    //7??????????????????
			    ws.addCell(label);
			}
			int i =0;
			
			for(int j = 0;j<l.size();j++){
				Map<String, String> map = new HashMap<String,String>();
				Object u = l.get(j);
				Class c1= u.getClass();
				Field[] fields1  = c1.getDeclaredFields();
				for(Field f : fields1) {
					// ????????????????????????????????? 
					boolean accessFlag = f.isAccessible(); 
					// ???????????????????????? 
					f.setAccessible(true); 
					map.put(f.getName(),f.get(u)!=null?f.get(u).toString():"");
				}
				for(int k = 0;k<shuxing.length;k++) { 
					String dict = zdyValueKey.getString(shuxing[k]);
					if(CommonUtil.isNotEmpty(zdyValueKey.getString(shuxing[k]))&&CommonUtil.isNotEmpty(map.get(shuxing[k]))) {
						//????????????cs???CS???????????????????????????????????????map????????????
						if(zdyValueKey.getString(shuxing[k]).startsWith("cs")||zdyValueKey.getString(shuxing[k]).startsWith("CS")) {
							jxl.write.Label labelC = new Label(k,sbFlag?(j+2):(j+1),DictData.getCodeName(zdyValueKey.getString(shuxing[k]).toUpperCase(), map.get(shuxing[k])));
							ws.addCell(labelC);
						}else {//??????????????????????????????dictService??????????????????????????????????????????????????????
							SysCode dictSysCode = dictService.getRemoteDictItem(zdyValueKey.getString(shuxing[k]).toUpperCase(), map.get(shuxing[k]));
							if(dictSysCode!=null) {
								jxl.write.Label labelC = new Label(k,sbFlag?(j+2):(j+1),dictSysCode.getCodename());
								ws.addCell(labelC);
							}else if(map.get(shuxing[k])!=null){
								jxl.write.Label labelC = new Label(k,sbFlag?(j+2):(j+1),map.get(shuxing[k]));
								ws.addCell(labelC);
							}
							
						}
					}else {
						//?????????????????????????????????????????????
						if(header[k].equals("??????")) {//???????????? dateToAge
							jxl.write.Label labelC = new Label(k,sbFlag?(j+2):(j+1),StringUtils.dateToAgeDay(map.get(shuxing[k])));
							ws.addCell(labelC);
						}else {
							jxl.write.Label labelC = new Label(k,sbFlag?(j+2):(j+1),map.get(shuxing[k]));
							ws.addCell(labelC);
						}
						
					}
					
				}
				//i++;
			}

			wwb.write();
			wwb.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	
	}

	@Override
	public void uploadQdZp(MultipartHttpServletRequest params) {
		
		MultipartFile logoFile = params.getFile("logoFile");
		String pzType = params.getParameter("pzType");//1 ???????????? 2????????????
		String sfxxbidList = params.getParameter("sfxxbidList");
		String czsj = StringUtils.getServiceTime();
		String[] sfxxbidArray = sfxxbidList.split(",");
		List<PoSFJFFJB> l = new ArrayList<>();
		for (String sfxxbid : sfxxbidArray) {  
			PoSFJFFJB sfjffjb= new PoSFJFFJB();
			String sql = " from PoSFJFFJB where sfxxbid = '"+sfxxbid+"'";
			List<PoSFJFFJB> sfjffjbList = (List<PoSFJFFJB>) super.getObjectListByHql(sql);
			try {
				createOrupdateSfjfxxb(sfjffjb, logoFile, pzType, sfxxbid, sfjffjbList,czsj);
			} catch (IOException e) {
				e.printStackTrace();
			}
	     }
	}

	public void createOrupdateSfjfxxb(PoSFJFFJB sfjffjb, MultipartFile logoFile, String pzType, String sfxxbid,
			List<PoSFJFFJB> sfjffjbList,String czsj) throws IOException {
		/*
		 * ?????????????????????????????????
		 */
		if(sfjffjbList.size()==0) {
			PojoInfo  hjxx_jwhzplsbDAO = DAOFactory.createSFJFFJBDAO();
			Long id = (Long) hjxx_jwhzplsbDAO.getId();
			sfjffjb.setCzsj(czsj);
			sfjffjb.setCzrid(this.getUser().getYhid());
			if(pzType.equals("1")) {
				sfjffjb.setSfqd(logoFile.getBytes());
			}else if(pzType.equals("2")) {
				sfjffjb.setJfqd(logoFile.getBytes());
				updateSfxxb(sfxxbid,czsj);
			}
			sfjffjb.setSfjffjbid(id);
			sfjffjb.setSfxxbid(Long.valueOf(sfxxbid));
			super.create(sfjffjb);
		}else {
			/*
			 * ???????????????????????????????????????
			 */
			sfjffjb = sfjffjbList.get(0);
			sfjffjb.setGxsj(czsj);
			sfjffjb.setGxrid(this.getUser().getYhid());
			if(pzType.equals("1")) {
				sfjffjb.setSfqd(logoFile.getBytes());
			}else if(pzType.equals("2")) {
				sfjffjb.setJfqd(logoFile.getBytes());
				updateSfxxb(sfxxbid,czsj);
			}
			super.update(sfjffjb);
		}
	}

	/**
	 * @param sfxxbid
	 * @param time 
	 */
	private void updateSfxxb(String sfxxbid, String time) {
		PoSFXXB sfxxb = super.get(PoSFXXB.class, Long.parseLong(sfxxbid));
		sfxxb.setJfflag("1");
		sfxxb.setJkrid(this.getUser().getYhid());
		sfxxb.setJksj(time);
		
	}

	@Override
	public PoSFXXB updateSfxxb(PoSFXXB posfxxb) {
		PoSFXXB sfxxb = super.get(PoSFXXB.class, posfxxb.getSfxxbid());
		if(sfxxb.getXgcs()>0) {
			throw new RuntimeException("???????????????????????????????????????????????????");
		}
		if(CommonUtil.isNotEmpty(posfxxb.getBzxjfyy())){
			sfxxb.setBzxjfyy(posfxxb.getBzxjfyy());
		}
		if(posfxxb.getJe()!=null){
			sfxxb.setJe(posfxxb.getJe());
		}
		if(CommonUtil.isNotEmpty(posfxxb.getSffs())){
			sfxxb.setSffs(posfxxb.getSffs());
		}
		sfxxb.setXgcs(1);
		super.update(sfxxb);
		return sfxxb;
	}

	@Override
	public void downBkMb(HttpServletRequest req, HttpServletResponse rep, ExtMap<String, Object> params) {
//		String filename = "";
		String FileDate = DateHelper.formateDate("yyyyMMdd");
		try {
			     
            // ??????????????????
            String filename = new String("bkmb.xls".getBytes("UTF-8"),"ISO-8859-1");;
            // ???????????????????????????
            String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();
            // ??????????????????????????????
            InputStream fis = FileUtils.class.getResourceAsStream("/conf/bkmb.xls");
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // ??????response
            rep.reset();
            // ??????response???Header
            rep.setHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes("utf-8")));
            OutputStream toClient = new BufferedOutputStream(rep.getOutputStream());
            rep.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
			return;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return; 
		}
		
	}

	@Override
	public void uploadBkMb(MultipartHttpServletRequest bkMbFile) throws IOException {
		//????????????????????????
//		Object deptAttachmentObj = bkMbFile.get("bkmb", Object.class);
//		bkMbFile.getMultiFileMap()
		MultipartFile logoFile = bkMbFile.getFile("bkmb");
		String deptAttachmentName = null;
			if (logoFile instanceof MultipartFile) {//???????????????????????????
			       MultipartFile  deptAttachment = (MultipartFile) logoFile;
			       //?????????????????????
			       deptAttachmentName = deptAttachment.getOriginalFilename() ;
			       //??????????????????????????????
			 InputStream inputStream;
			 inputStream = deptAttachment.getInputStream();
			 //????????????Excel?????????List
			 long exStart = System.currentTimeMillis();
			 List<Map<String, Object>> deptList = ExcelToDBUtil.sheetsToList(deptAttachmentName, inputStream);
			 if (deptList == null || deptList.isEmpty()) {
				throw new RuntimeException("?????????????????????");
			 }else {
				 for(Map<String, Object> map:deptList) {
					 String gmsfhm = (String) map.get("key2");
					 if(CommonUtil.isNotEmpty(gmsfhm)) {
						 PoHJYW_RKBKXXB ry1 = (PoHJYW_RKBKXXB)super.getObject("from PoHJYW_RKBKXXB where gmsfhm=?", new Object[] {gmsfhm});
						 if(ry1!=null) {
							continue;
						 }else {
							 PoHJYW_RKBKXXB bkry = new PoHJYW_RKBKXXB();
							 String bdlxDict = (String) map.get("key3");
							 if(CommonUtil.isNotEmpty(bdlxDict)) {
								 String[] bdlxArray = bdlxDict.split("-");
								 bkry.setBklx(bdlxArray[0]);
								 bkry.setBklxmc(bdlxArray[1]);
							 }
							 bkry.setBkmjxm(this.getUser().getYhdlm());
							 bkry.setBksj(DateHelper.formateDate("yyyyMMddHHmmss"));
							 if(CommonUtil.isNotEmpty((String) map.get("key4"))) {
								 bkry.setBktx((String) map.get("key4"));
							 }
							 bkry.setGmsfhm(gmsfhm);
							 if(CommonUtil.isNotEmpty((String) map.get("key1"))) {
								 bkry.setXm((String) map.get("key1"));
							 }
							 bkry.setYwid(null);
							 super.create(bkry);
						 }
					 }
					 
				 }
			 }
		
			}
	}
}
