package com.hzjc.hz2004.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.hzjc.hz2004.base.ExtMap;
import com.hzjc.hz2004.base.Page;
import com.hzjc.hz2004.base.SimpleJson;
import com.hzjc.hz2004.base.dict.DictData;
import com.hzjc.hz2004.base.login.AuthToken;
import com.hzjc.hz2004.base.login.BaseContext;
import com.hzjc.hz2004.base.login.HSession;
import com.hzjc.hz2004.common.HjCommon;
import com.hzjc.hz2004.common.PID;
import com.hzjc.hz2004.constant.DzConstant;
import com.hzjc.hz2004.constant.HjConstant;
import com.hzjc.hz2004.constant.PublicConstant;
import com.hzjc.hz2004.constant.ZjConstant;
import com.hzjc.hz2004.dao.DAOFactory;
import com.hzjc.hz2004.dao.PojoInfo;
import com.hzjc.hz2004.po.PoHJLS_HJYWLSB;
import com.hzjc.hz2004.po.PoHJSP_HBBGSPSQB;
import com.hzjc.hz2004.po.PoHJSP_HJBLSPSQB;
import com.hzjc.hz2004.po.PoHJSP_HJSCSPSQB;
import com.hzjc.hz2004.po.PoHJSP_HJSPSQB;
import com.hzjc.hz2004.po.PoHJSP_HJSPZB;
import com.hzjc.hz2004.po.PoHJSP_QYZXXB;
import com.hzjc.hz2004.po.PoHJTJ_RYBDXXB;
import com.hzjc.hz2004.po.PoHJXX_CXSXBGB;
import com.hzjc.hz2004.po.PoHJXX_CZRKJBXXB;
import com.hzjc.hz2004.po.PoHJXX_DYXXB;
import com.hzjc.hz2004.po.PoHJXX_HGLGXB;
import com.hzjc.hz2004.po.PoHJXX_HXXB;
import com.hzjc.hz2004.po.PoHJXX_MLPXXXXB;
import com.hzjc.hz2004.po.PoHJXX_PZRZB;
import com.hzjc.hz2004.po.PoHJXX_RHFLXXB;
import com.hzjc.hz2004.po.PoHJXX_RYZPXXB;
import com.hzjc.hz2004.po.PoHJXX_ZPLSB;
import com.hzjc.hz2004.po.PoHJYW_BGGZXXB;
import com.hzjc.hz2004.po.PoHJYW_CHCLXXB;
import com.hzjc.hz2004.po.PoHJYW_CSDJXXB;
import com.hzjc.hz2004.po.PoHJYW_GMSFHMSXMFPXXB;
import com.hzjc.hz2004.po.PoHJYW_HBBGXXB;
import com.hzjc.hz2004.po.PoHJYW_HJBLXXB;
import com.hzjc.hz2004.po.PoHJYW_HJSCXXB;
import com.hzjc.hz2004.po.PoHJYW_QCCLXXB;
import com.hzjc.hz2004.po.PoHJYW_QCZXXXB;
import com.hzjc.hz2004.po.PoHJYW_QRDJXXB;
import com.hzjc.hz2004.po.PoHJYW_SWZXXXB;
import com.hzjc.hz2004.po.PoHJYW_ZZBDXXB;
import com.hzjc.hz2004.po.PoHZ_ZJ_SB;
import com.hzjc.hz2004.po.PoJSSDLOGXXB;
import com.hzjc.hz2004.po.PoTJ_CXJGBCRZB;
import com.hzjc.hz2004.po.PoV_HJ_BGGZXXB;
import com.hzjc.hz2004.po.PoV_HJ_CHCLXXB;
import com.hzjc.hz2004.po.PoV_HJ_CSDJXXB;
import com.hzjc.hz2004.po.PoV_HJ_HBBGXXB;
import com.hzjc.hz2004.po.PoV_HJ_HCYBDXXB;
import com.hzjc.hz2004.po.PoV_HJ_HJBLXXB;
import com.hzjc.hz2004.po.PoV_HJ_HJSCXXB;
import com.hzjc.hz2004.po.PoV_HJ_QCCLXXB;
import com.hzjc.hz2004.po.PoV_HJ_QCZXXXB;
import com.hzjc.hz2004.po.PoV_HJ_QRDJXXB;
import com.hzjc.hz2004.po.PoV_HJ_SWZXXXB;
import com.hzjc.hz2004.po.PoV_HJ_ZZBDXXB;
import com.hzjc.hz2004.po.PoXT_JWHXXB;
import com.hzjc.hz2004.po.PoXT_XTCSB;
import com.hzjc.hz2004.po.PoXT_YHXXB;
import com.hzjc.hz2004.po.PoZJXX_JMSFZXXB;
import com.hzjc.hz2004.po.PoZJYW_SLXXB;
import com.hzjc.hz2004.po.notable.PoOLD_HJTJ_RYBDXXB;
import com.hzjc.hz2004.po.notable.PoOLD_HJYW_BGGZXXB;
import com.hzjc.hz2004.service.HjService;
import com.hzjc.hz2004.service.hzpt.ReceiveStateService;
import com.hzjc.hz2004.util.CommonUtil;
import com.hzjc.hz2004.util.DateHelper;
import com.hzjc.hz2004.vo.*;
import com.hzjc.util.StringUtils;
import com.hzjc.wsstruts.KDSActionProxy;
import com.hzjc.wsstruts.exception.DAOException;
import com.hzjc.wsstruts.exception.ServiceException;
import com.hzjc.wsstruts.exception.WSErrCode;
import com.hzjc.wsstruts.vo.VoPage;
import com.hzjc.wsstruts.vo.VoQueryResult;

/**
 * ???????????????
 * <p>Title: hz2004</p>
 * <p>Description: ?????????????????????Hz2004???</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: </p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */

@Service(value = "hjService")
public class HjServiceImpl
    extends HjCommon
    implements HjService {

  //????????????
  protected static Log _log = LogFactory.getLog(HjServiceImpl.class);
  @Resource
  private ReceiveStateService receiveStateService;
  /**
   * ????????????????????????
   * @param voQhbgxx - ??????????????????
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoQhbgywfhxx processQhbgyw(VoSbjbxx voSbjbxx, VoQhbgxx voQhbgxx[]) throws
      ServiceException, DAOException {

    VoQhbgywfhxx voQhbgywfhxx = null;
    VoQhbgfhxx voQhbgfhxx[] = null;
    Long hjywid = null;
    String now = StringUtils.getServiceTime();

    /////////////////////////////////////
    //????????????
    if (voQhbgxx == null || (voQhbgxx != null && voQhbgxx.length <= 0)) {
      return null;
    }

    try {
      PojoInfo  hjls_hjywlsbDAO = DAOFactory.createHJLS_HJYWLSBDAO();
      PojoInfo  hjxx_hxxbDAO = DAOFactory.createHJXX_HXXBDAO();
      PojoInfo  hjxx_mlpxxxxbDAO = DAOFactory.createHJXX_MLPXXXXBDAO();
      PojoInfo  xt_jwhxxbDAO = DAOFactory.createXT_JWHXXBDAO();

      //////////////////////////////
      //??????????????????ID
      hjywid = (Long) hjls_hjywlsbDAO.getId();
      
      /////////////////////////////////////
      //????????????
      // add by zjm 20190805 ???id??????clbs??????
      if(voSbjbxx!=null && voSbjbxx.getHzywid()!=null){
    	updateHzyw(voSbjbxx.getHzywid(),hjywid,0l);
      }    


      ///////////////////////////////////////
      //????????????????????????
      voQhbgfhxx = new VoQhbgfhxx[voQhbgxx.length];
      for (int i = 0; i < voQhbgxx.length; i++) {
        //???????????????
        PoHJXX_HXXB poHxx = super.get(PoHJXX_HXXB.class, voQhbgxx[i].
            getHhnbid());
        if (poHxx == null) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_DAO_NOTEXIST,
                                     "??????????????????????????????????????????????????????", null);
        }
        //???????????????????????????
        this.checkHXX(poHxx, hjxx_hxxbDAO, "??????????????????");
        //??????????????????
        if (!HjConstant.HHZT_ZC.equals(poHxx.getHhzt())) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "??????????????????????????????????????????????????????????????????", null);
        }
        //???????????????
        PoHJXX_MLPXXXXB poMlpxxxx = super.get(PoHJXX_MLPXXXXB.class,
            poHxx.getMlpnbid());
        if (poMlpxxxx == null) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "??????????????????????????????????????????????????????????????????", null);
        }
        //??????????????????
        List sjfwList = new ArrayList();
        VoXtsjfw voXtsjfw = new VoXtsjfw();
        voXtsjfw.setSjfw(poMlpxxxx.getJcwh());
        voXtsjfw.setSjfwbz(PublicConstant.XT_QX_JWH);
        sjfwList.add(voXtsjfw);
        boolean bLimit = false;
        bLimit = XtywqxServiceImpl.VerifyDataRange(this.getUserInfo().getYhid().
            toString(),
            PublicConstant.GNBH_HJ_QHBGYW, sjfwList);
        if (!bLimit) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLIMIT,
        		  CommonUtil.getSjfwError(sjfwList)  + "????????????????????????????????????", null);
        }

        //?????????????????????????????????????????????
        VoDzzjxx voDzxx = new VoDzzjxx();
        BeanUtils.copyProperties(voDzxx, poMlpxxxx);
        BeanUtils.copyProperties(voDzxx, voQhbgxx[i]);
        PoHJXX_MLPXXXXB poMlpxxxxNew = this.getDXX(voDzxx);
        if (poMlpxxxxNew == null) {
          //??????????????????
          poMlpxxxxNew = new PoHJXX_MLPXXXXB();
          BeanUtils.copyProperties(poMlpxxxxNew, poMlpxxxx);
          BeanUtils.copyProperties(poMlpxxxxNew, voQhbgxx[i]);
          //Begin_??????????????????(2004/11/15 17:25:00)
          /*
                     poMlpxxxxNew.setXzjd(poMlpxxxxNew.getJcwh() != null &&
                               poMlpxxxxNew.getJcwh().length() > 9 ?
                               poMlpxxxxNew.getJcwh().substring(0, 9) : null);
           */
          PoXT_JWHXXB poXT_JWHXXB = (PoXT_JWHXXB) super.get(PoXT_JWHXXB.class,
              poMlpxxxxNew.getJcwh()); //?????????????????????????????????????????????
          if (poXT_JWHXXB == null) {
            throw new ServiceException(WSErrCode.ERR_SERVICE_DATANOTFULL,
                                       "???????????????????????????????????????????????????,?????????????????????????????????", null);
          }
          poMlpxxxxNew.setXzjd(poXT_JWHXXB.getXzjddm());
          //End_??????????????????(2004/11/15 17:25:00)
          poMlpxxxxNew.setPxh(this.generatePXH(poMlpxxxxNew.getMlph(),
                                               poMlpxxxxNew.getMlxz()));
          poMlpxxxxNew.setMlpnbid( (Long) hjxx_mlpxxxxbDAO.getId());
          poMlpxxxxNew.setMlpid(poMlpxxxxNew.getMlpnbid());
          poMlpxxxxNew.setQysj(now);
          poMlpxxxxNew.setJssj(null);
          poMlpxxxxNew.setCdlb(null);
          poMlpxxxxNew.setJdlb(HjConstant.JDLB_YWJL);
          poMlpxxxxNew.setCchjywid(null);
          poMlpxxxxNew.setCjhjywid(hjywid);
          poMlpxxxxNew.setJlbz(PublicConstant.JLBZ_ZX);
          super.create(poMlpxxxxNew);
        }
        else if (!DzConstant.MLPZT_ZC.equals(poMlpxxxxNew.getMlpzt())) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_DATANOTFULL,
                                     "??????????????????????????????????????????????????????????????????????????????,?????????????????????????????????", null);
        }

        //??????????????????????????????
        poHxx.setJssj(now);
        poHxx.setChlb(HjConstant.CHLB_YWZX);
        poHxx.setCchjywid(hjywid);
        poHxx.setJlbz(PublicConstant.JLBZ_LS);
        super.update(poHxx);
        //????????????????????????
        PoHJXX_HXXB poHxxNew = new PoHJXX_HXXB();
        BeanUtils.copyProperties(poHxxNew, poHxx);
        poHxxNew.setHhnbid( (Long) hjxx_hxxbDAO.getId());
        poHxxNew.setHlx(voQhbgxx[i].getHlx());
        poHxxNew.setMlpnbid(poMlpxxxxNew.getMlpnbid());
        poHxxNew.setQysj(now);
        poHxxNew.setJssj(null);
        poHxxNew.setChlb(null);
        poHxxNew.setJhlb(HjConstant.JHLB_YWSC);
        poHxxNew.setCchjywid(null);
        poHxxNew.setCjhjywid(hjywid);
        poHxxNew.setJlbz(PublicConstant.JLBZ_ZX);
        super.create(poHxxNew);

        //??????????????????
        VoRhdxx voRhdxx = new VoRhdxx(null, poHxxNew, poMlpxxxxNew,
                                      BaseContext.getUser());
        VoXtywxz voLimit = XtywqxServiceImpl.VerifyBusinessLimit(PublicConstant.
            GNBH_HJ_QHBGYW, voRhdxx);
        if (voLimit.getLimitflag()) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLIMIT,
                                     "??????????????????????????????????????????" + voLimit.getLimitinfo(), null);
        }
        voRhdxx = null;

        //??????????????????????????????
        this.changeRXX_HXX(hjywid, poHxx, poHxxNew, poMlpxxxx, poMlpxxxxNew,
                           PublicConstant.GNBH_HJ_QHBGYW, "14", now, voSbjbxx);
        //add by hh 20060323 ??????????????????????????????????????????????????????????????????
        //???????????????????????????
        //  saveQhBggzxx()
        //??????????????????????????????
        voQhbgfhxx[i] = new VoQhbgfhxx();
        voQhbgfhxx[i].setHhnbid(poHxxNew.getHhnbid());
        voQhbgfhxx[i].setOld_hhnbid(poHxx.getHhnbid());
        voQhbgfhxx[i].setMlpnbid(poMlpxxxxNew.getMlpnbid());
        voQhbgfhxx[i].setOld_mlpnbid(poMlpxxxx.getMlpnbid());
      }

      ////////////////////////////////////////
      //??????????????????????????????
      this.saveHJYWLSXX(hjywid, PublicConstant.GNBH_HJ_QHBGYW,
                        PublicConstant.HJYWLS_YWLX_QH,
                        voQhbgxx.length, null, now);

      ////////////////////////////
      //????????????????????????
      voQhbgywfhxx = new VoQhbgywfhxx();
      voQhbgywfhxx.setHjywid(hjywid);
      voQhbgywfhxx.setVoQhbgfhxx(voQhbgfhxx);

      /////////////////////////////
      //????????????
    }
    catch (DAOException ex) {
      //????????????
      throw ex;
    }
    catch (ServiceException ex) {
      //????????????
      throw ex;
    }
    catch (InvocationTargetException ex) {
      //????????????
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }
    catch (IllegalAccessException ex) {
      //????????????
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }
    catch (Exception ex) {
      //????????????
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }

    return voQhbgywfhxx;
  }

  /**
   * ????????????????????????
   * @param voRhflxx[] - ??????????????????
   * @return com.hzjc.hz2004.vo.VoRhflywfhxx
   * @roseuid 40628C4E00FB
   */
  public VoRhflywfhxx processRhflyw(VoRhflxx voRhflxx[]) throws
      ServiceException,
      DAOException {

    VoRhflywfhxx voRhflywfhxx = null;
    VoRhflfhxx voRhflfhxx[] = null;
    Long hjywid = null;
    String now = StringUtils.getServiceTime();

    if (voRhflxx == null || (voRhflxx != null && voRhflxx.length <= 0)) {
      return null;
    }

    try {
      PojoInfo  hjls_hjywlsbDAO = DAOFactory.createHJLS_HJYWLSBDAO();
      PojoInfo  hjxx_rhflxxbDAO = DAOFactory.createHJXX_RHFLXXBDAO();

      //////////////////////////////////
      //????????????

      //////////////////////////////
      //??????????????????ID
      hjywid = (Long) hjls_hjywlsbDAO.getId();

      /////////////////////////////////////
      //????????????????????????
      voRhflfhxx = new VoRhflfhxx[voRhflxx.length];
      for (int i = 0; i < voRhflxx.length; i++) {
        //??????????????????
        PoHJXX_CZRKJBXXB poRyxx = super.get(PoHJXX_CZRKJBXXB.class,
            voRhflxx[i].getRynbid());
        if (poRyxx == null) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "????????????????????????????????????????????????????????????????????????", null);
        }
        //???????????????
        PoHJXX_HXXB poHxx = super.get(PoHJXX_HXXB.class, poRyxx.getHhnbid());
        if (poHxx == null) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "?????????????????????????????????????????????????????????????????????????????????", null);
        }
        //?????????????????????
        PoHJXX_MLPXXXXB poMlpxxxx = super.get(PoHJXX_MLPXXXXB.class,
            poRyxx.getMlpnbid());
        if (poMlpxxxx == null) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "?????????????????????????????????????????????????????????????????????????????????", null);
        }

        //??????????????????
        VoRhdxx voRhdxx = new VoRhdxx(poRyxx, poHxx, poMlpxxxx,
                                      BaseContext.getUser());
        VoXtywxz voLimit = XtywqxServiceImpl.VerifyBusinessLimit(PublicConstant.
            GNBH_HJ_RHFLYW, voRhdxx);
        if (voLimit.getLimitflag()) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLIMIT,
                                     "??????????????????????????????????????????" + voLimit.getLimitinfo(), null);
        }
        voRhdxx = null;
        //??????????????????
        List sjfwList = new ArrayList();
        VoXtsjfw voXtsjfw = new VoXtsjfw();
        voXtsjfw.setSjfw(poMlpxxxx.getJcwh());
        voXtsjfw.setSjfwbz(PublicConstant.XT_QX_JWH);
        sjfwList.add(voXtsjfw);
        boolean bLimit = false;
        bLimit = XtywqxServiceImpl.VerifyDataRange(this.getUserInfo().getYhid().
            toString(),
            PublicConstant.GNBH_HJ_RHFLYW, sjfwList);
        if (!bLimit) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLIMIT,
        		  CommonUtil.getSjfwError(sjfwList)  + "????????????????????????????????????", null);
        }

        PoHJXX_RHFLXXB poRhflxx = new PoHJXX_RHFLXXB();
        if (voRhflxx[i].getRhflid()== null){
          //????????????????????????
          BeanUtils.copyProperties(poRhflxx, voRhflxx[i]);
          poRhflxx.setRhflid( (Long) hjxx_rhflxxbDAO.getId());
          poRhflxx.setRyid(poRyxx.getRyid());
          poRhflxx.setGmsfhm(poRyxx.getGmsfhm());
          poRhflxx.setXm(poRyxx.getXm());
          poRhflxx.setXb(poRyxx.getXb());
          poRhflxx.setCsrq(poRyxx.getCsrq());
          poRhflxx.setSlsj(now);
          poRhflxx.setSldw(this.getUserInfo().getDwdm());
          poRhflxx.setSlrid(this.getUserInfo().getYhid());
          poRhflxx.setCjhjywid(hjywid);
          poRhflxx.setRhflzt(HjConstant.RHFLZT_SYZ);
          super.create(poRhflxx);
        }
        else{
          //??????????????????????????????
          poRhflxx = super.get(PoHJXX_RHFLXXB.class,voRhflxx[i].getRhflid());
          if (poRhflxx == null) {
            throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                       "????????????????????????????????????????????????????????????????????????", null);
          }
          poRhflxx.setXzdfw(voRhflxx[i].getXzdfw());
          poRhflxx.setRhfl_ssxq(voRhflxx[i].getRhfl_ssxq());
          poRhflxx.setRhfl_jlx(voRhflxx[i].getRhfl_jlx());
          poRhflxx.setRhfl_mlph(voRhflxx[i].getRhfl_mlph());
          poRhflxx.setRhfl_mlxz(voRhflxx[i].getRhfl_mlxz());
          poRhflxx.setRhfl_pcs(voRhflxx[i].getRhfl_pcs());
          poRhflxx.setRhfl_zrq(voRhflxx[i].getRhfl_zrq());
          poRhflxx.setRhfl_xzjd(voRhflxx[i].getRhfl_xzjd());
          poRhflxx.setRhfl_jcwh(voRhflxx[i].getRhfl_jcwh());
          poRhflxx.setBz(voRhflxx[i].getBz());
          if (HjConstant.RHFLZT_ZX.equals(voRhflxx[i].getRhflzt())){
            poRhflxx.setRhflzt(voRhflxx[i].getRhflzt());
            poRhflxx.setZxsj(now);
            poRhflxx.setZxrid(this.getUserInfo().getYhid());
            poRhflxx.setZxdw(this.getUserInfo().getDwdm());
            poRhflxx.setZxyy(voRhflxx[i].getZxyy());
          }
          super.update(poRhflxx);

        }

        //??????????????????????????????
        voRhflfhxx[i] = new VoRhflfhxx();
        voRhflfhxx[i].setHhnbid(poRyxx.getHhnbid());
        voRhflfhxx[i].setRynbid(voRhflxx[i].getRynbid());
        voRhflfhxx[i].setRyid(poRyxx.getRyid());
        voRhflfhxx[i].setRhflid(poRhflxx.getRhflid());
        voRhflfhxx[i].setGmsfhm(poRyxx.getGmsfhm());
        voRhflfhxx[i].setXm(poRyxx.getXm());
        voRhflfhxx[i].setYhzgx(poRyxx.getYhzgx());
      }

      ////////////////////////////////////////
      //??????????????????????????????
      this.saveHJYWLSXX(hjywid, PublicConstant.GNBH_HJ_RHFLYW,
                        PublicConstant.HJYWLS_YWLX_GR,
                        voRhflxx.length, null, now);

      ////////////////////////////
      //????????????????????????
      voRhflywfhxx = new VoRhflywfhxx();
      voRhflywfhxx.setHjywid(hjywid);
      voRhflywfhxx.setVoRhflfhxx(voRhflfhxx);

      /////////////////////////////
      //????????????
    }
    catch (DAOException ex) {
      //????????????
      throw ex;
    }
    catch (ServiceException ex) {
      //????????????
      throw ex;
    }
    catch (InvocationTargetException ex) {
      //????????????
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }
    catch (IllegalAccessException ex) {
      //????????????
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }
    catch (Exception ex) {
      //????????????
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }

    return voRhflywfhxx;
  }

  /**
   * ????????????????????????
   * @param voLhhdxx - ??????????????????
   * @param voRhhdxx - ??????????????????
   * @param voSbjbxx - ??????????????????
   * @param voCsdjxx[] - ??????????????????
   * @param voBggzxx[] - ??????????????????
   * @return com.hzjc.hz2004.vo.VoCsdjywfhxx
   * @roseuid 4049C2260159
   */
  public VoCsdjywfhxx processCsdjyw(VoLhhdxx voLhhdxx, VoRhhdxx voRhhdxx,
                                    VoSbjbxx voSbjbxx, VoCsdjxx voCsdjxx[],
                                    VoBggzxxEx voBggzxxEx[]) throws
      ServiceException,
      DAOException {

    VoCsdjywfhxx voCsdjywfhxx = null;
    VoCsdjfhxx voCsdjfhxx[] = null;
    VoHcygxtzxxEx voHcygxtzxxEx[] = null;
    List bggzfhxxList = new ArrayList();
    List hcygxtzfhxxList = new ArrayList();
    List rybdxxList = new ArrayList();
    VoLhhdfhxx voLhhdfhxx = null;
    PoHJXX_MLPXXXXB poMlpxxxx = null;
    PoXT_JWHXXB poJwhxxb = null;

    PoHJXX_HXXB poHxx = null;
    Long hjywid = null;
    String rh_lh; //?????????????????????1-??????/2-?????????
    boolean bAdd = false;
    String now = StringUtils.getServiceTime();

    if (voCsdjxx == null || (voCsdjxx != null && voCsdjxx.length <= 0)) {
      return null;
    }

    try {

      PojoInfo  hjxx_mlpxxxxbDAO = DAOFactory.createHJXX_MLPXXXXBDAO();
      PojoInfo  hjxx_czrkjbxxbDAO = DAOFactory.createHJXX_CZRKJBXXBDAO();
      PojoInfo  hjxx_hxxbDAO = DAOFactory.createHJXX_HXXBDAO();
      PojoInfo  hjls_hjywlsbDAO = DAOFactory.createHJLS_HJYWLSBDAO();
      PojoInfo  hjyw_csdjxxbDAO = DAOFactory.createHJYW_CSDJXXBDAO();
      PojoInfo  hjxx_ryzpxxbDAO = DAOFactory.createHJXX_RYZPXXBDAO();
      PojoInfo  zjxx_jmsfzxxbDAO = DAOFactory.createZJXX_JMSFZXXBDAO();
      PojoInfo  hjyw_gmsfhmsxmfpxxbDAO = DAOFactory.
          createHJYW_GMSFHMSXMFPXXBDAO();
      PojoInfo  xt_jwhxxbDAO = DAOFactory.createXT_JWHXXBDAO();

      /////////////////////////////////////
      //??????????????????ID
      hjywid = (Long) hjls_hjywlsbDAO.getId();
      
      ////////////////////////////////
      //????????????

//      if(voSbjbxx!=null && voSbjbxx.getHzywid()!=null){
    	//modify by zjm 20190716 ???????????????????????????????????????clbs??????
//    	updateHzyw(voSbjbxx.getHzywid(),hjywid,0l);
        //List list = super.findAllByHQL("from PoHZ_ZJ_SB a where a.id=" + voSbjbxx.getHzywid());
//        if(list.size()>0){
//          PoHZ_ZJ_SB sb = (PoHZ_ZJ_SB)list.get(0);
//          sb.setClbs("1");
//          sb.setClsj(new java.sql.Timestamp(new Date().getTime()));
//          super.update(sb);
//        }
//      }
      String djzt="";

      //////////////////////////////////////
      //???????????????????????????
      //??????
      if (voRhhdxx != null && voRhhdxx.getHhnbid() != null) {
        //???????????????
        poHxx = super.get(PoHJXX_HXXB.class,voRhhdxx.getHhnbid());
        List list = super.findAllByHQL("from PoHJXX_CZRKJBXXB a where ryzt='0' and cxbz='0' and jlbz='1' and a.hhnbid=" + poHxx.getHhnbid());
        if(list.size()>0) {
        	PoHJXX_CZRKJBXXB czrkjbxxb = (PoHJXX_CZRKJBXXB) list.get(0);
        	if(czrkjbxxb.getDjzt()!=null&&!czrkjbxxb.getDjzt().equals("1")) {
        		djzt = czrkjbxxb.getDjzt();
        	}
        }
        if (poHxx == null) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "???????????????????????????????????????????????????????????????", null);
        }
        //???????????????????????????
        this.checkHXX(poHxx, hjxx_hxxbDAO, "??????????????????");
        //??????????????????
        if (!HjConstant.HHZT_ZC.equals(poHxx.getHhzt())) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "??????????????????????????????????????????????????????????????????", null);
        }
        //???????????????
        poMlpxxxx = super.get(PoHJXX_MLPXXXXB.class,poHxx.getMlpnbid());
        if (poMlpxxxx == null) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "??????????????????????????????????????????????????????????????????", null);
        }
        //?????????????????????(????????????2005/05/25 10:40:00)
        //if (!DzConstant.MLPZT_ZC.equals(poMlpxxxx.getMlpzt())) {
        //  throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
        //                             "?????????????????????????????????????????????????????????????????????", null);
        //}
        poJwhxxb = (PoXT_JWHXXB) super.get(PoXT_JWHXXB.class,poMlpxxxx.getJcwh());

        if (poJwhxxb == null) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "?????????????????????????????????????????????????????????????????????", null);
        }

        rh_lh = "2"; //?????????????????????1-??????/2-?????????
      }
      //??????
      else {
        voLhhdfhxx = this.createH(hjywid, voLhhdxx, voCsdjxx[0].getBdfw(),
                                  voCsdjxx[0].getCsdjlb(), HjConstant.YWNR_CSDJ,
                                  now);
        if (voLhhdfhxx == null ||
            (voLhhdfhxx != null &&
             (voLhhdfhxx.getHhnbid() == null || voLhhdfhxx.getMlpnbid() == null))) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "????????????????????????????????????????????????????????????", null);
        }
        poHxx = voLhhdfhxx.getPoHJXX_HXXB();
        poMlpxxxx = voLhhdfhxx.getPoHJXX_MLPXXXXB();

        poJwhxxb = (PoXT_JWHXXB) super.get(PoXT_JWHXXB.class,poMlpxxxx.getJcwh());

        if (poJwhxxb == null) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "?????????????????????????????????????????????????????????????????????", null);
        }

        rh_lh = "1"; //?????????????????????1-??????/2-?????????
      }

      /////////////////////////////////
      //??????????????????
      List sjfwList = new ArrayList();
      VoXtsjfw voXtsjfw = new VoXtsjfw();
      voXtsjfw.setSjfw(poMlpxxxx.getJcwh());
      voXtsjfw.setSjfwbz(PublicConstant.XT_QX_JWH);
      sjfwList.add(voXtsjfw);
      boolean bLimit = false;
      bLimit = XtywqxServiceImpl.VerifyDataRange(this.getUserInfo().getYhid().
                                                 toString(),
                                                 PublicConstant.GNBH_HJ_CSDJYW,
                                                 sjfwList);
      if (!bLimit) {
        throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLIMIT,
        		CommonUtil.getSjfwError(sjfwList)  + "????????????????????????????????????", null);
      }

      //////////////////////////////////////
      //????????????????????????
      voCsdjfhxx = new VoCsdjfhxx[voCsdjxx.length];
      voHcygxtzxxEx = new VoHcygxtzxxEx[voCsdjxx.length];
      for (int i = 0; i < voCsdjxx.length; i++) {
        //if(voCsdjxx[i].get
        Long rynbid = (Long) hjxx_czrkjbxxbDAO.getId();
        VoGmsfhmfpfhxx voGmsfhmfpfhxx = null;
        Long zpid = null;

        //??????????????????????????????????????????
        if (voCsdjxx[i].getGmsfhm() == null ||
            (voCsdjxx[i].getGmsfhm() != null &&
             voCsdjxx[i].getGmsfhm().length() == 0)) {

          VoGmsfhmfpsqxx voGmsfhmfpsqxx = new VoGmsfhmfpsqxx();
          //??????????????????????????????????????????
          voGmsfhmfpsqxx.setCsrq(voCsdjxx[i].getCsrq());
          voGmsfhmfpsqxx.setRyid(rynbid);
          voGmsfhmfpsqxx.setXb(voCsdjxx[i].getXb());
          voGmsfhmfpsqxx.setXm(voCsdjxx[i].getXm());
          voGmsfhmfpsqxx.setXzqh(poMlpxxxx.getSsxq());
          voGmsfhmfpsqxx.setPcs(poMlpxxxx.getPcs());
          voGmsfhmfpsqxx.setXzjd(poMlpxxxx.getXzjd());

          //????????????????????????????????????
          voGmsfhmfpfhxx = this.assignGMSFHM(hjywid, voGmsfhmfpsqxx, now);
          if (voGmsfhmfpfhxx == null ||
              (voGmsfhmfpfhxx != null && voGmsfhmfpfhxx.getGmsfhm() == null) ||
              (voGmsfhmfpfhxx != null && voGmsfhmfpfhxx.getGmsfhm() != null &&
               voGmsfhmfpfhxx.getGmsfhm().length() != 18)) {
            throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                       "??????????????????????????????????????????????????????????????????", null);
          }
        }
        //????????????????????????????????????????????????????????????
        else {
          //??????????????????
          if (!PID.IDCheck(voCsdjxx[i].getGmsfhm())) {
            throw new ServiceException(WSErrCode.ERR_SERVICE_DATANOTFULL,
                                       "??????????????????(" + voCsdjxx[i].getGmsfhm() +
                                       ")??????????????????????????????????????????", null);
          }
          //?????? 20060531 ??????????????????????????????????????????????????????2????????????0?????????1??????????????????
          if (!"0".equals(this.getXTKZCS("1022"))) {
            String strHQL =
                "select count(*) from PoHJXX_CZRKJBXXB where gmsfhm='" +
                voCsdjxx[i].getGmsfhm() + "'";
            if (super.getCount(strHQL) > 0) {
              throw new ServiceException(WSErrCode.ERR_SERVICE_DATANOTFULL,
                                         "??????????????????(" + voCsdjxx[i].getGmsfhm() +
                                         ")??????????????????????????????????????????", null);
            }
          }
          //????????????????????????
          if (this.countCHRYXX(voCsdjxx[i].getGmsfhm()) > 0) {
            throw new ServiceException(WSErrCode.ERR_SERVICE_DATANOTFULL,
                                       "??????????????????(" + voCsdjxx[i].getGmsfhm() +
                                       ")??????????????????????????????????????????", null);
          }
          //??????????????????????????????
          String strHQL =
              "select count(*) from PoHJYW_GMSFHMSXMFPXXB where gmsfhm='" +
              voCsdjxx[i].getGmsfhm() + "'";
          if (super.getCount(strHQL) <= 0) {
            PoHJYW_GMSFHMSXMFPXXB poFpxx = new PoHJYW_GMSFHMSXMFPXXB();
            poFpxx.setFpid( (Long) hjyw_gmsfhmsxmfpxxbDAO.getId());
            poFpxx.setRyid(rynbid);
            poFpxx.setCsrq(voCsdjxx[i].getCsrq());
            poFpxx.setXm(voCsdjxx[i].getXm());
            poFpxx.setGmsfhm(voCsdjxx[i].getGmsfhm());
            poFpxx.setXb(voCsdjxx[i].getXb());
            poFpxx.setDwdm(poMlpxxxx.getPcs());
            poFpxx.setSxh(voCsdjxx[i].getGmsfhm().substring(14, 17));
            poFpxx.setCzlb(HjConstant.GMSFHMFPWS_CZLB_LR);
            poFpxx.setHjywid(hjywid);
            super.create(poFpxx);
          }

        } //????????????????????????????????????????????????????????????
        if(voSbjbxx!=null && voSbjbxx.getHzywid()!=null){
            List list = super.findAllByHQL("from PoHZ_ZJ_SB a where a.id=" + voSbjbxx.getHzywid());
            if(list.size()>0){
          	  PoHZ_ZJ_SB sb = (PoHZ_ZJ_SB)list.get(0);
	              String kzz = super.getXTKZCS("10031");
	              String kzz1 = super.getXTKZCS("10032");
	              String kzz2 = super.getXTKZCS("10037");
	              //1.??????????????????   2.wx_code????????????    add by zjm 20191106 
	                if(kzz.equals("1")) {//????????????1??????????????????????????????????????????HZPT_RECEIVESTATE????????????
	              	  if(CommonUtil.isNotEmpty(sb.getWx_code())) {
	              		  updateHzptReceiveState(kzz,sb);
	              	  }
	                }
	                if(kzz1.equals("1")) {//????????????1??????????????????????????????????????????HZPT_RECEIVESTATE????????????
	              	  if(CommonUtil.isNotEmpty(sb.getWx_code())) {
	              		  insertEms(kzz1,sb);
	              	  }
	                }
	              if(sb.getPch()!=null && !sb.getPch().equals("")){
	                //???????????????????????????
	                list = findEntities("from PoHZ_ZJ_SB a where a.pch='" + sb.getPch() + "' and a.clbs='0' ");
	                int pccount = list.size();
	                for(int index=0;index<pccount;index++){
	                  PoHZ_ZJ_SB sbx = (PoHZ_ZJ_SB)list.get(index);
	                  if(sbx.getBsqrxm().equals(voCsdjxx[i].getXm())) {
	                	  sbx.setClbs("1");
  	                  if(hjywid>0) {
  	                	 sbx.setHjywid(hjywid);  
  	                  }
  	                  sbx.setBlrsfz(this.getUser().getGmsfhm());
  	                  sbx.setHjdpcs(this.getUser().getDwdm());
  	                  //modify by zjm 20200821 ???????????????????????????????????????????????? ?????????????????????????????????????????????????????????????????????????????????
  	                  if(voGmsfhmfpfhxx!=null){
  	                	  sbx.setBsqrsfz(voGmsfhmfpfhxx.getGmsfhm());
  	                  }else {
  	                	sbx.setBsqrsfz(voCsdjxx[i].getGmsfhm());
  	                  }
  	                  
  	                  sbx.setClsj(new java.sql.Timestamp(new Date().getTime()));
  	                  update(sbx);
  	                  if(kzz2.equals("1")) {//add by zjm 20200710 ????????????2????????????????????????????????????
  		                	autoInsertHzjsbs(kzz2,sbx);
  		              }
	                  }
	                  
	                  
	                  
	                }
	              }else{
	                sb.setClbs("1");
	                if(hjywid>0) {
	                	sb.setHjywid(hjywid);
	                }
	                sb.setBlrsfz(this.getUser().getGmsfhm());
	                sb.setHjdpcs(this.getUser().getDwdm());
	                if(sb.getBsqrsfz()==null || sb.getBsqrsfz().equals("")){
                    sb.setBsqrsfz(voGmsfhmfpfhxx.getGmsfhm());
                  }
	                sb.setClsj(new java.sql.Timestamp(new Date().getTime()));
	                update(sb);
	                if(kzz2.equals("1")) {//add by zjm 20200710 ????????????2????????????????????????????????????
	                	autoInsertHzjsbs(kzz2,sb);
	                }
	              }
            }
      }
        //???????????????????????????
        if (voCsdjxx[i].getZp() != null && voCsdjxx[i].getZp().length() > 0) {
          zpid = (Long) hjxx_ryzpxxbDAO.getId();
        }

        //??????????????????
        PoHJXX_CZRKJBXXB poRyxx = new PoHJXX_CZRKJBXXB();
        BeanUtils.copyProperties(poRyxx, poHxx);
        BeanUtils.copyProperties(poRyxx, poMlpxxxx); //add by mhb 2005/07/04 11:20
        BeanUtils.copyProperties(poRyxx, voCsdjxx[i]);

        poRyxx.setBzdz(poMlpxxxx.getBzdz());
        poRyxx.setBzdzid(poMlpxxxx.getBzdzid());
        poRyxx.setBzdzst(poMlpxxxx.getBzdzst());
        poRyxx.setBzdzx(poMlpxxxx.getBzdzx());
        poRyxx.setBzdzy(poMlpxxxx.getBzdzy());

        if ("0".equals(this.getXTKZCS(PublicConstant.XTKZCS_CSYWTBZHSBS))) {
          //??????
          poRyxx.setHslbz(now.substring(0, 8));
          poRyxx.setHylbz(voCsdjxx[i].getCsdjlb());
        }
        else {
          //??????
          poRyxx.setHsql(now.substring(0, 8));
          poRyxx.setHyql(voCsdjxx[i].getCsdjlb());
        }
        poRyxx.setRynbid(rynbid);
        poRyxx.setRyid(poRyxx.getRynbid());
        poRyxx.setMlpnbid(poMlpxxxx.getMlpnbid());
        poRyxx.setHhnbid(poHxx.getHhnbid());
        poRyxx.setZpid(zpid);
        if (voGmsfhmfpfhxx != null) {
          poRyxx.setGmsfhm(voGmsfhmfpfhxx.getGmsfhm());
        }
        poRyxx.setRyzt(HjConstant.RYZT_ZC);
        poRyxx.setRysdzt(HjConstant.RYSDZT_ZC);
        poRyxx.setRylb(HjConstant.RYLB_YB);
        poRyxx.setQysj(now);
        poRyxx.setJssj(null);
        poRyxx.setXxqysj(now);//add by hb 20061027
        poRyxx.setYwnr(HjConstant.YWNR_CSDJ);
        poRyxx.setCchjywid(null);
        poRyxx.setCjhjywid(hjywid);
        poRyxx.setJlbz(PublicConstant.JLBZ_ZX);
        poRyxx.setCxbz(PublicConstant.CXBZ_FCX); //add by mhb 2005/01/17 13:30:00
        poRyxx.setCxfldm(poJwhxxb.getCxfldm());
        if(CommonUtil.isNotEmpty(djzt)) {
        	poRyxx.setDjzt(djzt);
        }
        //2015-12-13????????????????????????????????????
        super.create(poRyxx);

        //??????????????????
        VoRhdxx voRhdxx = new VoRhdxx(poRyxx, poHxx, poMlpxxxx,
                                      BaseContext.getUser());
        VoXtywxz voLimit = XtywqxServiceImpl.VerifyBusinessLimit(PublicConstant.
            GNBH_HJ_CSDJYW, voRhdxx);
        if (voLimit.getLimitflag()) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLIMIT,
                                     "??????????????????????????????????????????" + voLimit.getLimitinfo(), null);
        }
        voRhdxx = null;

        //??????????????????????????????????????????????????????
        voHcygxtzxxEx[i] = new VoHcygxtzxxEx();
        voHcygxtzxxEx[i].setFlag(0); //?????????????????????????????????????????????????????????(0-?????????/1-??????)
        voHcygxtzxxEx[i].setHcybdlx(HjConstant.HCYBDLX_RH);
        voHcygxtzxxEx[i].setHcybdlb(voCsdjxx[i].getCsdjlb());
        voHcygxtzxxEx[i].setHhnbid(poHxx.getHhnbid());
        voHcygxtzxxEx[i].setRynbid(rynbid);
        voHcygxtzxxEx[i].setNew_rynbid(rynbid);
        voHcygxtzxxEx[i].setRyid(poRyxx.getRyid());
        voHcygxtzxxEx[i].setXm(poRyxx.getXm());
        voHcygxtzxxEx[i].setGmsfhm(poRyxx.getGmsfhm());
        voHcygxtzxxEx[i].setOld_yhzgx(null);
        voHcygxtzxxEx[i].setYhzgx(poRyxx.getYhzgx());
        voHcygxtzxxEx[i].setSbhjywid(null);

        //????????????????????????
        PoHJYW_CSDJXXB poCsdjxx = new PoHJYW_CSDJXXB();
        BeanUtils.copyProperties(poCsdjxx, voCsdjxx[i]);
        BeanUtils.copyProperties(poCsdjxx, poRyxx);
        if (voSbjbxx != null) {
          BeanUtils.copyProperties(poCsdjxx, voSbjbxx);
        }
        poCsdjxx.setHhid(poHxx.getHhid());
        poCsdjxx.setMlpid(poMlpxxxx.getMlpid());
        poCsdjxx.setCsdjid( (Long) hjyw_csdjxxbDAO.getId());
        poCsdjxx.setCxbz(PublicConstant.CXBZ_FCX);
        poCsdjxx.setSldw(this.getUserInfo().getDwdm());
        poCsdjxx.setSlrid(this.getUserInfo().getYhid());
        poCsdjxx.setSlsj(now);
        poCsdjxx.setHjywid(hjywid);
        poCsdjxx.setCxfldm(poJwhxxb.getCxfldm());
        super.create(poCsdjxx);

        //????????????????????????
        //2015-12-13????????????????????????????????????
        PoHJXX_CXSXBGB log = new PoHJXX_CXSXBGB();
        log.setBghcxsx(poJwhxxb.getCxfldm());
        log.setBgqcxsx(poJwhxxb.getCxfldm());
        log.setCjsj(poCsdjxx.getSlsj());
        log.setSldw(poJwhxxb.getDwdm());
        log.setBgqdw(poJwhxxb.getDwdm());
        log.setRynbid(poCsdjxx.getRynbid());
        log.setHjywid(poCsdjxx.getHjywid());
        log.setSsxq(poCsdjxx.getSsxq());
        log.setJwhdm(poJwhxxb.getDm());
        log.setBgyy(null);
        log.setYwlb("cssb");
        log.setBz("????????????");
        log.setRkbj("1");
        log.setRysl(new Long(1));
        super.create(log);

        //??????????????????
        if (zpid != null) {
          VoHJXX_RYZPXXB voRyzpxx = new VoHJXX_RYZPXXB();
          voRyzpxx.setZpid(zpid);
          voRyzpxx.setLrrq(now);
          voRyzpxx.setRyid(poRyxx.getRyid());
          voRyzpxx.setXm(voCsdjxx[i].getXm());
          voRyzpxx.setGmsfhm(poRyxx.getGmsfhm());
          voRyzpxx.setZp(voCsdjxx[i].getZp());
          super.create(voRyzpxx.toPoHJXX_RYZPXXB());
        }

        //????????????????????????(??????)
        VoRybdxx voRybdxx = new VoRybdxx();
        voRybdxx.setBdbid(poCsdjxx.getCsdjid());
        voRybdxx.setBdyy(poCsdjxx.getCsdjlb());
        voRybdxx.setBdrq(now.substring(0, 8)); //voRybdxx.setBdrq(poRyxx.getCsrq());
        voRybdxx.setBdqhb(null);
        voRybdxx.setBdq_hhnbid(null);
        voRybdxx.setBdh_hhnbid(poRyxx.getHhnbid());
        voRybdxx.setRynbid(poRyxx.getRynbid());
        voRybdxx.setRzjs(new Long(1));
        if (!bAdd && "1".equals(rh_lh)) {
          bAdd = true;
          voRybdxx.setHzjs(new Long(1));
        }
        else {
          voRybdxx.setHzjs(new Long(0));
        }
        rybdxxList.add(voRybdxx);

        //??????????????????????????????
        voCsdjfhxx[i] = new VoCsdjfhxx();
        voCsdjfhxx[i].setCsdjid(poCsdjxx.getCsdjid());
        voCsdjfhxx[i].setHhnbid(poRyxx.getHhnbid());
        voCsdjfhxx[i].setRynbid(poRyxx.getRynbid());
        voCsdjfhxx[i].setRyid(poRyxx.getRyid());
        voCsdjfhxx[i].setGmsfhm(poRyxx.getGmsfhm());
        voCsdjfhxx[i].setXm(poRyxx.getXm());
        voCsdjfhxx[i].setYhzgx(poRyxx.getYhzgx());
        voCsdjfhxx[i].setSys_bh(voCsdjxx[i].getSys_bh());
      }

      ////////////////////////////////////////
      //?????????????????????
      VoHcygxtzfhxx voh[] = this.adjustHCYGX(hjywid, voSbjbxx, voHcygxtzxxEx,
                                             now);
      if (voh != null) {
        for (int i = 0; i < voh.length; i++) {
          hcygxtzfhxxList.add(voh[i]);
        }
      }

      ////////////////////////////////////////
      //??????????????????
      if (voBggzxxEx != null) {
        VoBggzfhxxEx vobEx = this.saveBGGZXX(hjywid, voSbjbxx, voBggzxxEx,
                                             PublicConstant.GNBH_HJ_CSDJYW, now);
        if (vobEx != null) {
          if (vobEx.getVoBggzfhxx() != null) {
            for (int a = 0; a < vobEx.getVoBggzfhxx().length; a++) {
              bggzfhxxList.add(vobEx.getVoBggzfhxx()[a]);
            }
          } //if(vobEx!=null)
          if (vobEx.getVoHcygxtzfhxx() != null) {
            for (int a = 0; a < vobEx.getVoHcygxtzfhxx().length; a++) {
              hcygxtzfhxxList.add(vobEx.getVoHcygxtzfhxx()[a]);
            }
          }
        } //if(vobEx!=null)
      }

      ////////////////////////////////
      //??????????????????
      Map hhnbidMap = new HashMap();
      for (int h = 0; h < hcygxtzfhxxList.size(); h++) {
        VoHcygxtzfhxx vo = (VoHcygxtzfhxx) hcygxtzfhxxList.get(h);
        hhnbidMap.put(vo.getHhnbid(), null);
      }
      for (Iterator itr = hhnbidMap.keySet().iterator(); itr.hasNext(); ) {
        this.checkHCYXX( (Long) itr.next());
      }

      ////////////////////////////////////////
      //??????????????????????????????
      //rh_lh = "1"; //?????????????????????1-??????/2-?????????
      PoHJLS_HJYWLSB poHjywlsxx = this.saveHJYWLSXX(hjywid,
          PublicConstant.GNBH_HJ_CSDJYW,
          rh_lh.equals("1") ? PublicConstant.HJYWLS_YWLX_QH :
          PublicConstant.HJYWLS_YWLX_GR,
          voCsdjxx.length, voSbjbxx, now);

      //////////////////////////////////////////
      //????????????????????????(??????)
      this.saveRYBDXX(rybdxxList, poHjywlsxx);

      ///////////////////////////////////////////
      //????????????????????????????????????????????????????????????????????????
      PoHJXX_CZRKJBXXB poHzxx = null;
      String strHQL = "from PoHJYW_CSDJXXB where hjywid=" + hjywid;
      List csdjxxList = super.findAllByHQL(strHQL);
      if (csdjxxList != null && csdjxxList.size() > 0) {
        for (int i = 0; i < csdjxxList.size(); i++) {
          PoHJYW_CSDJXXB poCsdjxx = (PoHJYW_CSDJXXB) csdjxxList.get(i);
          if (poHzxx == null) {
            strHQL = "from PoHJXX_CZRKJBXXB where yhzgx < '10' and ryzt='" +
                HjConstant.RYZT_ZC + "' and jlbz='" +
                PublicConstant.JLBZ_ZX + "' and cxbz='" +
                PublicConstant.CXBZ_FCX +
                "' and hhnbid =" + poCsdjxx.getHhnbid();
            List hzxxList = super.findAllByHQL(strHQL);
            if (hzxxList != null && hzxxList.size() > 0) {
              poHzxx = (PoHJXX_CZRKJBXXB) hzxxList.get(0);
            }
          }
          poCsdjxx.setHzgmsfhm(poHzxx != null ? poHzxx.getGmsfhm() : null);
          poCsdjxx.setHzxm(poHzxx != null ? poHzxx.getXm() : null);
          poCsdjxx.setYwlx(poHjywlsxx.getYwlx());
          poCsdjxx.setCzsm(poHjywlsxx.getCzsm());
          super.update(poCsdjxx);
        }
      }

      ////////////////////////////////////
      //??????????????????
      voCsdjywfhxx = new VoCsdjywfhxx();
      voCsdjywfhxx.setHjywid(hjywid);
      voCsdjywfhxx.setHhnbid(poHxx.getHhnbid());
      voCsdjywfhxx.setMlpnbid(poMlpxxxx.getMlpnbid());
      voCsdjywfhxx.setVoCsdjfhxx(voCsdjfhxx);
      voCsdjywfhxx.setVoBggzfhxx( (VoBggzfhxx[]) bggzfhxxList.toArray(new
          VoBggzfhxx[bggzfhxxList.size()]));
      voCsdjywfhxx.setVoHcygxtzfhxx( (VoHcygxtzfhxx[]) hcygxtzfhxxList.toArray(new
          VoHcygxtzfhxx[hcygxtzfhxxList.size()]));

      /////////////////////////////////////////
      //????????????
    }
    catch (DAOException ex) {
      //????????????
      throw ex;
    }
    catch (ServiceException ex) {
      //????????????
      throw ex;
    }
    catch (InvocationTargetException ex) {
      //????????????
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }
    catch (IllegalAccessException ex) {
      //????????????
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }
    catch (Exception ex) {
      //????????????
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }

    return voCsdjywfhxx;
  }

  /**
   * ???????????????????????????
   * @param voLhhdxx - ??????????????????
   * @param voRhhdxx - ??????????????????
   * @param voSbjbxx - ??????????????????
   * @param voQrdjxx[] - ??????????????????
   * @param voChxx[] - ????????????
   * @param voBggzxx[] - ??????????????????
   * @return com.hzjc.hz2004.vo.VoQrdjywfhxx
   * @roseuid 4046F15F00FC
   */
  public VoQrdjywfhxx processQrdjyw(VoLhhdxx voLhhdxx, VoRhhdxx voRhhdxx,
                                    VoSbjbxx voSbjbxx,
                                    VoQrdjxx voQrdjxx[], VoChxx voChxx[],
                                    VoBggzxxEx voBggzxxEx[]) throws
      ServiceException,
      DAOException {
	  String sysdate = super.getServerTime(null);
	  for(VoQrdjxx dj:voQrdjxx){
		 if(CommonUtil.isEmpty(dj.getRylb()))
			 dj.setRylb("1");
		 
		 if(CommonUtil.isEmpty(dj.getQrrq())){
			 dj.setQrrq(sysdate);
		 }
	  }
	  
    VoQrdjywfhxx voQrdjywfhxx = null;
    VoQrdjfhxx voQrdjfhxx[] = null;
    VoHcygxtzxxEx voHcygxtzxxEx[] = null;
    List bggzfhxxList = new ArrayList();
    List hcygxtzfhxxList = new ArrayList();
    List rybdxxList = new ArrayList();
    VoLhhdfhxx voLhhdfhxx = null;
    List chxxList = new ArrayList();
    Long hjywid = null;
    PoHJXX_MLPXXXXB poMlpxxxx = null;
    PoHJXX_HXXB poHxx = null;
    PoXT_JWHXXB poJwhxxb = null;
    String rh_lh; //?????????????????????1-??????/2-?????????
    boolean bAdd = false;
    String now = StringUtils.getServiceTime();
    List poList = new ArrayList();

    if (voQrdjxx == null || (voQrdjxx != null && voQrdjxx.length <= 0)) {
      return null;
    }

    if(voQrdjxx!=null && voQrdjxx.length>0){
      VoQrdjxx dj = voQrdjxx[0];
      String dqbm = dj.getQcdssxq();
      if(dqbm!=null && dqbm.length()>4 && dqbm.startsWith("34")){
        dqbm = dqbm.substring(0,4);
        String sfz = "";
        for(int i=0;i<voQrdjxx.length;i++){
          sfz += voQrdjxx[i].getGmsfhm() + ",";
        }
        String error = KDSActionProxy.qrywGmsfhmCheck(dqbm,sfz);
        if(error!=null && !error.trim().equals("")){
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC, error, null);
        }
      }
    }

    //?????????????????????(???????????? By MHB 2005/08/01 11:10:00)
    voChxx = null;

    try {
      PojoInfo  hjxx_czrkjbxxbDAO = DAOFactory.createHJXX_CZRKJBXXBDAO();
      PojoInfo  hjxx_hxxbDAO = DAOFactory.createHJXX_HXXBDAO();
      PojoInfo  hjls_hjywlsbDAO = DAOFactory.createHJLS_HJYWLSBDAO();
      PojoInfo  hjyw_qrdjxxbDAO = DAOFactory.createHJYW_QRDJXXBDAO();
      PojoInfo  hjxx_ryzpxxbDAO = DAOFactory.createHJXX_RYZPXXBDAO();
      PojoInfo  zjxx_jmsfzxxbDAO = DAOFactory.createZJXX_JMSFZXXBDAO();
      PojoInfo  hjyw_gmsfhmsxmfpxxbDAO = DAOFactory.
          createHJYW_GMSFHMSXMFPXXBDAO();

      PojoInfo  hjyw_hbbgxxbDAO = DAOFactory.createHJYW_HBBGXXBDAO();


      /////////////////////////////////////
      //??????????????????ID
      hjywid = (Long) hjls_hjywlsbDAO.getId();
      //////////////////////////////////
      //????????????
      
      if(voSbjbxx!=null && voSbjbxx.getHzywid()!=null){
    	  updateHzyw(voSbjbxx.getHzywid(),hjywid,0l);
      }
      String djzt="";
      ///////////////////////////////////////
      //???????????????????????????
      //??????
      if (voRhhdxx != null && voRhhdxx.getHhnbid() != null&&!voRhhdxx.getHhnbid().equals(0L)) {
        //???????????????
        poHxx = super.get(PoHJXX_HXXB.class,voRhhdxx.getHhnbid());
        if (poHxx == null) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "???????????????????????????????????????????????????????????????", null);
        }
        //??????????????????
        if (!HjConstant.HHZT_ZC.equals(poHxx.getHhzt())) {
        	throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
        			"??????????????????????????????????????????????????????????????????", null);
        }
        //???????????????????????????
        this.checkHXX(poHxx, hjxx_hxxbDAO, "??????????????????");
        List list = super.findAllByHQL("from PoHJXX_CZRKJBXXB a where ryzt='0' and cxbz='0' and jlbz='1' and a.hhnbid=" + poHxx.getHhnbid());
        if(list.size()>0) {
        	PoHJXX_CZRKJBXXB czrkjbxxb = (PoHJXX_CZRKJBXXB) list.get(0);
        	if(czrkjbxxb.getDjzt()!=null&&!czrkjbxxb.getDjzt().equals("1")) {
        		djzt = czrkjbxxb.getDjzt();
        	}
        }
        //???????????????
        poMlpxxxx = super.get(PoHJXX_MLPXXXXB.class, poHxx.getMlpnbid());
        if (poMlpxxxx == null) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "??????????????????????????????????????????????????????????????????", null);
        }

        poJwhxxb = (PoXT_JWHXXB) super.get(PoXT_JWHXXB.class,poMlpxxxx.getJcwh());
        if (poJwhxxb == null) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "?????????????????????????????????????????????????????????", null);
        }

        //?????????????????????(????????????2005/05/25 10:40:00)
        //if (!DzConstant.MLPZT_ZC.equals(poMlpxxxx.getMlpzt())) {
        //  throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
        //                             "?????????????????????????????????????????????????????????????????????", null);
        //}
        rh_lh = "2"; //?????????????????????1-??????/2-?????????
      }
      //??????
      else {
        if(voQrdjxx[0].getSpywid()!=null){
          PojoInfo  sp = DAOFactory.createHJSP_HJSPSQBDAO();
          PoHJSP_HJSPSQB spyw = super.get(PoHJSP_HJSPSQB.class,voQrdjxx[0].getSpywid());
          if(spyw!=null){
            voLhhdxx.setBzdz(spyw.getBzdz());
            voLhhdxx.setBzdzid(spyw.getBzdzid());
            voLhhdxx.setBzdzst(spyw.getBzdzst());
            voLhhdxx.setBzdzx(spyw.getBzdzx());
            voLhhdxx.setBzdzy(spyw.getBzdzy());

            if(spyw.getKdqqy_qcdqbm()!=null
            		&& !spyw.getKdqqy_qcdqbm().equals("") 
            		&& spyw.getKdqqy_fkzt()!=null 
            		&& !spyw.getKdqqy_fkzt().equals("")){
              if(!spyw.getKdqqy_fkzt().equals("2")){
                throw new ServiceException(WSErrCode.ERR_SERVICE_DATANOTFULL,
                           "????????????????????????????????????????????????????????????", null);
              }
            }
          }
        }
        voLhhdfhxx = this.createH(hjywid, voLhhdxx, voQrdjxx[0].getBdfw(),
                                  voQrdjxx[0].getQrlb(), HjConstant.YWNR_QRDJ,
                                  now);
        int i;
        if (voLhhdfhxx == null ||
            (voLhhdfhxx != null &&
             (voLhhdfhxx.getHhnbid() == null || voLhhdfhxx.getMlpnbid() == null))) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "????????????????????????????????????????????????", null);
        }
        poHxx = voLhhdfhxx.getPoHJXX_HXXB();
        poMlpxxxx = voLhhdfhxx.getPoHJXX_MLPXXXXB();
        poJwhxxb = (PoXT_JWHXXB) super.get(PoXT_JWHXXB.class,poMlpxxxx.getJcwh());

        if (poJwhxxb == null) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "?????????????????????????????????????????????????????????????????????", null);
        }

        rh_lh = "1"; //?????????????????????1-??????/2-?????????
      }

      ////////////////////////////////////////
      //??????????????????
      List sjfwList = new ArrayList();
      VoXtsjfw voXtsjfw = new VoXtsjfw();
      voXtsjfw.setSjfw(poMlpxxxx.getJcwh());
      voXtsjfw.setSjfwbz(PublicConstant.XT_QX_JWH);
      sjfwList.add(voXtsjfw);
      boolean bLimit = false;
      bLimit = XtywqxServiceImpl.VerifyDataRange(this.getUserInfo().getYhid().
                                                 toString(),
                                                 PublicConstant.GNBH_HJ_QRDJYW,
                                                 sjfwList);
      if (!bLimit) {
        throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLIMIT,
        		CommonUtil.getSjfwError(sjfwList)  + "????????????????????????????????????", null);
      }

      //////////////////////////////////////
      //????????????????????????
      voQrdjfhxx = new VoQrdjfhxx[voQrdjxx.length];
      voHcygxtzxxEx = new VoHcygxtzxxEx[voQrdjxx.length];
      for (int i = 0; i < voQrdjxx.length; i++) {
        Long rynbid = (Long) hjxx_czrkjbxxbDAO.getId();
        boolean bDoubleID = false;
        VoGmsfhmfpfhxx voGmsfhmfpfhxx = null;
        Long zpid = null; //??????ID

        //????????????????????????????????????
        if (voQrdjxx[i].getRyid() != null &&
            (voQrdjxx[i].getGmsfhm() == null ||
             (voQrdjxx[i].getGmsfhm() != null &&
              voQrdjxx[i].getGmsfhm().length() == 0)
             )) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_DATANOTFULL,
                                     "????????????????????????????????????????????????????????????????????????", null);

        }

        //????????????????????????
        if (PublicConstant.XTKZCS_QY.equals(this.getXTKZCS(PublicConstant.
            XTKZCS_SHQYZDQYPD))) {
          if (this.checkZZBD(poMlpxxxx.getSsxq(), voQrdjxx[i].getQcdssxq())) {
            throw new ServiceException(WSErrCode.ERR_SERVICE_DATANOTFULL,
                                       "??????????????????????????????????????????????????????????????????????????????????????????", null);
          }
        }

        //????????????????????????????????????????????????????????????????????????????????????1032??????
        //????????????0308?????????0394??????
        if ( ("0308").equals(voQrdjxx[i].getQrlb()) ||
            ("0394").equals(voQrdjxx[i].getQrlb())) {
          if (PublicConstant.XTKZCS_QY.equals(this.getXTKZCS(PublicConstant.
              XTKZCS_HQPOXXJY))) {
            if (voQrdjxx[i].getPogmsfhm() == null || voQrdjxx[i].getPoxm() == null) {
              throw new ServiceException(WSErrCode.ERR_SERVICE_DATANOTFULL,
                                         "?????????????????????????????????????????????????????????????????????", null);
            }

            int hy = -1;
            if (voQrdjxx[i].getHyzk() != null && !"".equals(voQrdjxx[i].getHyzk())){
              hy = "[20][21][22][23]".indexOf(voQrdjxx[i].getHyzk());
            }
            if (hy < 0){
              throw new ServiceException(WSErrCode.ERR_SERVICE_DATANOTFULL,
                                         "????????????????????????????????????????????????????????????????????????????????????", null);
            }

            Map mpo = new HashMap();
            mpo.put("gmsfhm", voQrdjxx[i].getGmsfhm());
            mpo.put("xm", voQrdjxx[i].getXm());
            mpo.put("pogmsfhm", voQrdjxx[i].getPogmsfhm());
            mpo.put("poxm", voQrdjxx[i].getPoxm());
            mpo.put("hh", poHxx.getHh());
            poList.add(mpo);

          }
        }

        //??????????????????????????????????????????
        if (voQrdjxx[i].getGmsfhm() == null ||
            (voQrdjxx[i].getGmsfhm() != null &&
             voQrdjxx[i].getGmsfhm().length() == 0)) {

          VoGmsfhmfpsqxx voGmsfhmfpsqxx = new VoGmsfhmfpsqxx();

          //??????????????????????????????????????????
          voGmsfhmfpsqxx.setCsrq(voQrdjxx[i].getCsrq());
          voGmsfhmfpsqxx.setRyid(rynbid);
          voGmsfhmfpsqxx.setXb(voQrdjxx[i].getXb());
          voGmsfhmfpsqxx.setXm(voQrdjxx[i].getXm());
          voGmsfhmfpsqxx.setXzqh(poMlpxxxx.getSsxq());
          voGmsfhmfpsqxx.setPcs(poMlpxxxx.getPcs());
          voGmsfhmfpsqxx.setXzjd(poMlpxxxx.getXzjd());

          //????????????????????????????????????
          voGmsfhmfpfhxx = this.assignGMSFHM(hjywid, voGmsfhmfpsqxx, now);
          if (voGmsfhmfpfhxx == null ||
              (voGmsfhmfpfhxx != null && voGmsfhmfpfhxx.getGmsfhm() == null) ||
              (voGmsfhmfpfhxx != null && voGmsfhmfpfhxx.getGmsfhm() != null &&
               voGmsfhmfpfhxx.getGmsfhm().length() != 18)) {
            throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                       "??????????????????????????????????????????????????????????????????", null);
          }

        }
        //????????????????????????????????????????????????????????????
        else {
          //??????????????????
          if (!PID.IDCheck(voQrdjxx[i].getGmsfhm())) {
            throw new ServiceException(WSErrCode.ERR_SERVICE_DATANOTFULL,
                                       "??????????????????(" + voQrdjxx[i].getGmsfhm() +
                                       ")??????????????????????????????????????????", null);
          }
          //modi by hh 2005-10-14 ??????????????????????????????
          //?????????????????????????????????
          if (voChxx != null) {
            for (int k = 0; k < voChxx.length; k++) {
              if (voChxx[k].getChsfhm() != null &&
                  voChxx[k].getChsfhm().equals(voQrdjxx[i].getGmsfhm())) {
                bDoubleID = true;
                break;
              }
            }
          }
          if (!bDoubleID) {
            if (this.countCHRYXX(voQrdjxx[i].getGmsfhm()) > 0) {
              throw new ServiceException(WSErrCode.ERR_SERVICE_DATANOTFULL,
                                         "??????????????????(" + voQrdjxx[i].getGmsfhm() +
                                         ")??????????????????????????????????????????", null);
            }
          }

          //?????????????????????????????????
          if (voQrdjxx[i].getRyid() == null) {
            //?????????????????????????????????
            /*            if (voChxx != null) {
                          for (int k = 0; k < voChxx.length; k++) {
                            if (voChxx[k].getChsfhm() != null &&
                 voChxx[k].getChsfhm().equals(voQrdjxx[i].getGmsfhm())) {
                              bDoubleID = true;
                              break;
                            }
                          }
                        }
                        if (!bDoubleID) {
                          if (this.countCHRYXX(voQrdjxx[i].getGmsfhm()) > 0) {
                 throw new ServiceException(WSErrCode.ERR_SERVICE_DATANOTFULL,
                 "??????????????????(" + voQrdjxx[i].getGmsfhm() +
                 ")??????????????????????????????????????????", null);
                          }
                        }
             */
            //??????????????????????????????
            String strHQL =
                "select count(*) from PoHJYW_GMSFHMSXMFPXXB where gmsfhm='" +
                voQrdjxx[i].getGmsfhm() + "'";
            if (super.getCount(strHQL) <= 0) {
              PoHJYW_GMSFHMSXMFPXXB poFpxx = new PoHJYW_GMSFHMSXMFPXXB();
              poFpxx.setFpid( (Long) hjyw_gmsfhmsxmfpxxbDAO.getId());
              poFpxx.setRyid(rynbid);
              poFpxx.setCsrq(voQrdjxx[i].getCsrq());
              poFpxx.setXm(voQrdjxx[i].getXm());
              poFpxx.setGmsfhm(voQrdjxx[i].getGmsfhm());
              poFpxx.setXb(voQrdjxx[i].getXb());
              poFpxx.setDwdm(poMlpxxxx.getPcs());
              poFpxx.setSxh(voQrdjxx[i].getGmsfhm().substring(14, 17));
              poFpxx.setCzlb(HjConstant.GMSFHMFPWS_CZLB_LR);
              poFpxx.setHjywid(hjywid);
              super.create(poFpxx);
            }

          } //if (voQrdjxx[i].getRyid() == null)
        } //????????????????????????????????????????????????????????????

        //hubin 20080414 ?????????????????????6??????????????????????????????????????????????????????
        if (voQrdjxx[i].getBdfw() != null &&
            voQrdjxx[i].getBdfw().compareTo(HjConstant.BDFW_GN) >= 0){
          parseWSgmsfhm(voQrdjxx[i], voGmsfhmfpfhxx);
        }

        //???????????????????????????
        if (voQrdjxx[i].getZp() != null && voQrdjxx[i].getZp().length() > 0) {
          zpid = (Long) hjxx_ryzpxxbDAO.getId();
        }

        //??????????????????
        PoHJXX_CZRKJBXXB poRyxxHQ = null;
        if (voQrdjxx[i].getRyid() != null) {
          String strHQL = "from PoHJXX_CZRKJBXXB where ryid=" +
              voQrdjxx[i].getRyid().toString() + " and jlbz='" +
              PublicConstant.JLBZ_ZX + "' and cxbz='" +
              PublicConstant.CXBZ_FCX + "' ";

          List ryxxList = super.findAllByHQL(strHQL);
          if (ryxxList != null && ryxxList.size() > 0) {
            for (int k = 0; k < ryxxList.size(); k++) {
              poRyxxHQ = (PoHJXX_CZRKJBXXB) ryxxList.get(k);
              //
              if (!voQrdjxx[i].getGmsfhm().equals(poRyxxHQ.getGmsfhm())){
                throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                           poRyxxHQ.getXm() + "(" +
                                           poRyxxHQ.getGmsfhm() +
                                           ")???????????????????????????????????????????????????????????????", null);
              }

              //???????????????????????????
              this.checkRXX(poRyxxHQ, hjxx_czrkjbxxbDAO, "??????????????????");
              //????????????????????????
              if (HjConstant.RYZT_ZC.equals(poRyxxHQ.getRyzt())) {
                throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                           poRyxxHQ.getXm() + "(" +
                                           poRyxxHQ.getGmsfhm() +
                                           ")????????????????????????????????????????????????", null);
              }
              poRyxxHQ.setCchjywid(hjywid);
              poRyxxHQ.setJssj(now);
              poRyxxHQ.setJlbz(PublicConstant.JLBZ_LS);
              super.update(poRyxxHQ);
            }
          } //if(ryxxList!=null && ryxxList.size() > 0)
        }

        //??????????????????
        PoZJXX_JMSFZXXB poJmsfzxx = null;
        boolean bZjBc = false;
        if (voQrdjxx[i].getYxqxjzrq() != null &&
            voQrdjxx[i].getYxqxjzrq().length() > 0 &&
            voQrdjxx[i].getYxqxqsrq() != null &&
            voQrdjxx[i].getYxqxqsrq().length() > 0 &&
            voQrdjxx[i].getZjlb() != null &&
            voQrdjxx[i].getZjlb().length() > 0) {
          //add by hh 20060105 ????????????????????????????????????????????????????????????
          if (voQrdjxx[i].getRyid() != null) {
            List lstZjxx = super.findAllByHQL(
                " from PoZJXX_JMSFZXXB where ryid=" + voQrdjxx[i].getRyid() +
                " and yxqxqsrq='" + voQrdjxx[i].getYxqxqsrq() +
                "' and yxqxjzrq='" + voQrdjxx[i].getYxqxqsrq() + "'");
            if (lstZjxx != null && lstZjxx.size() > 0) {
              bZjBc = true;
            }
          }
          if (!bZjBc) { //??????????????????????????????????????????
            if (voQrdjxx[i].getGmsfhm() == null ||
                (voQrdjxx[i].getGmsfhm() != null &&
                 voQrdjxx[i].getGmsfhm().trim().length() <= 0)) {
              throw new ServiceException(WSErrCode.ERR_SERVICE_DATANOTFULL,
                                         "??????????????????????????????????????????????????????????????????????????????????????????????????????", null);
            }

            poJmsfzxx = new PoZJXX_JMSFZXXB();
            BeanUtils.copyProperties(poJmsfzxx, voQrdjxx[i]);
            poJmsfzxx.setNbsfzid( (Long) zjxx_jmsfzxxbDAO.getId());
            poJmsfzxx.setZjzt(ZjConstant.ZJ_ZJZT_ZC);
            //add by hh 20050920 ??????????????????????????????????????????????????????
            poJmsfzxx.setZjlx(ZjConstant.SFZXX_ZJLX_QR);
            poJmsfzxx.setZjsj(StringUtils.getServiceTime());

            poJmsfzxx.setRyid(voQrdjxx[i].getRyid() != null ?
                              voQrdjxx[i].getRyid() :
                              rynbid);
            super.create(poJmsfzxx);
          }
        }

        //??????????????????
        PoHJXX_CZRKJBXXB poRyxx = new PoHJXX_CZRKJBXXB();
        BeanUtils.copyProperties(poRyxx, poHxx);
        BeanUtils.copyProperties(poRyxx, poMlpxxxx); //add by mhb 2005/07/04 11:20
        BeanUtils.copyProperties(poRyxx, voQrdjxx[i]);
        //(bdfw <=32 ??????)
        if (voQrdjxx[i].getBdfw() != null &&
            voQrdjxx[i].getBdfw().compareTo(HjConstant.BDFW_BSJQ) <= 0) {
          //??????
          poRyxx.setHylbz(voQrdjxx[i].getQrlb()); //???????????????
          poRyxx.setHslbz(voQrdjxx[i].getQrrq()); //???????????????
          poRyxx.setHgjdqlbz(voQrdjxx[i].getQcdgjdq()); //??????????????????????????????
          poRyxx.setHsssqlbz(voQrdjxx[i].getQcdssxq()); //??????????????????????????????
          poRyxx.setHxzlbz(voQrdjxx[i].getQcdxz()); //???????????????
        }
        else {
          //??????
          poRyxx.setHsql(voQrdjxx[i].getQrrq()); //????????????
          poRyxx.setHyql(voQrdjxx[i].getQrlb()); //????????????
          poRyxx.setHgjdqql(voQrdjxx[i].getQcdgjdq()); //???????????????????????????
          poRyxx.setHssxqql(voQrdjxx[i].getQcdssxq()); //???????????????????????????
          poRyxx.setHxzql(voQrdjxx[i].getQcdxz()); //???????????????
        }
        poRyxx.setRynbid(rynbid);
        poRyxx.setRyid(voQrdjxx[i].getRyid() != null ? voQrdjxx[i].getRyid() :
                       rynbid);
        poRyxx.setZpid( (zpid == null && poRyxxHQ != null) ? poRyxxHQ.getZpid() :
                       zpid);
        poRyxx.setMlpnbid(poMlpxxxx.getMlpnbid());
        poRyxx.setHhnbid(poHxx.getHhnbid());
        if (voGmsfhmfpfhxx != null) {
          poRyxx.setGmsfhm(voGmsfhmfpfhxx.getGmsfhm());
        }
        poRyxx.setNbsfzid(poJmsfzxx != null ? poJmsfzxx.getNbsfzid() : null);
        poRyxx.setRyzt(HjConstant.RYZT_ZC);
        poRyxx.setRysdzt(HjConstant.RYSDZT_ZC);
        poRyxx.setRylb(HjConstant.RYLB_YB);
        poRyxx.setQysj(now);
        poRyxx.setJssj(null);
        poRyxx.setXxqysj(now);//add by hb 20061027
        poRyxx.setYwnr(HjConstant.YWNR_QRDJ);
        poRyxx.setCchjywid(null);
        poRyxx.setCjhjywid(hjywid);
        poRyxx.setJlbz(PublicConstant.JLBZ_ZX);
        poRyxx.setCxfldm(poJwhxxb.getCxfldm());
        if(CommonUtil.isNotEmpty(djzt)) {
        	poRyxx.setDjzt(djzt);
        }
        super.create(poRyxx);

        //??????????????????
        VoRhdxx voRhdxx = new VoRhdxx(poRyxx, poHxx, poMlpxxxx,
                                      BaseContext.getUser());
        //modi by hh 20051216 ?????????????????????????????????????????????
        if (voQrdjxx[i].getSpywid() == null && voQrdjxx[i].getSpsqzid() == null) {
          VoXtywxz voLimit = XtywqxServiceImpl.VerifyBusinessLimit(
              PublicConstant.
              GNBH_HJ_QRDJYW, voRhdxx);
          if (voLimit.getLimitflag()) {
            throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLIMIT,
                                       "??????????????????????????????????????????" + voLimit.getLimitinfo(), null);
          }
        }
        voRhdxx = null;

        //????????????????????????
        if (bDoubleID) {
          VoChxx ivoChxx = new VoChxx();
          ivoChxx.setBchryid(poRyxx.getRyid());
          ivoChxx.setChsfhm(poRyxx.getGmsfhm());
          chxxList.add(ivoChxx);
        }

        //??????????????????????????????????????????????????????
        voHcygxtzxxEx[i] = new VoHcygxtzxxEx();
        voHcygxtzxxEx[i].setFlag(0);
        voHcygxtzxxEx[i].setHcybdlx(HjConstant.HCYBDLX_RH);
        voHcygxtzxxEx[i].setHcybdlb(voQrdjxx[i].getQrlb());
        voHcygxtzxxEx[i].setHhnbid(poHxx.getHhnbid());
        voHcygxtzxxEx[i].setRynbid(rynbid);
        voHcygxtzxxEx[i].setNew_rynbid(rynbid);
        voHcygxtzxxEx[i].setRyid(poRyxx.getRyid());
        voHcygxtzxxEx[i].setXm(poRyxx.getXm());
        voHcygxtzxxEx[i].setGmsfhm(poRyxx.getGmsfhm());
        voHcygxtzxxEx[i].setOld_yhzgx(null);
        voHcygxtzxxEx[i].setYhzgx(poRyxx.getYhzgx());
        voHcygxtzxxEx[i].setSbhjywid(null);

        //??????????????????
        if (zpid != null) {
          VoHJXX_RYZPXXB voRyzpxx = new VoHJXX_RYZPXXB();
          voRyzpxx.setZpid(zpid);
          voRyzpxx.setLrrq(now);
          voRyzpxx.setRyid(poRyxx.getRyid());
          voRyzpxx.setXm(voQrdjxx[i].getXm());
          voRyzpxx.setGmsfhm(poRyxx.getGmsfhm());
          voRyzpxx.setZp(voQrdjxx[i].getZp());
          super.create(voRyzpxx.toPoHJXX_RYZPXXB());
        }

        //??????????????????
        if (voQrdjxx[i].getQrqhb() != null &&
            voQrdjxx[i].getQrqhb().length() > 0 &&
            !voQrdjxx[i].getQrqhb().equals(poRyxx.getHb())) {

          //????????????????????????
          PoHJYW_HBBGXXB poHbbgxx = new PoHJYW_HBBGXXB();
          if (voSbjbxx != null) {
            BeanUtils.copyProperties(poHbbgxx, voSbjbxx);
          }
          BeanUtils.copyProperties(poHbbgxx, poRyxx);
          poHbbgxx.setHhid(poHxx.getHhid());
          poHbbgxx.setMlpid(poMlpxxxx.getMlpid());
          poHbbgxx.setHbbgid( (Long) hjyw_hbbgxxbDAO.getId());
          poHbbgxx.setRynbid(poRyxx.getRynbid());
          poHbbgxx.setCxbz(PublicConstant.CXBZ_FCX);
          poHbbgxx.setBgqhb(voQrdjxx[i].getQrqhb());
          poHbbgxx.setBghhb(poRyxx.getHb());
          poHbbgxx.setHbbglb(voQrdjxx[i].getQrlb());
          poHbbgxx.setBdfw(voQrdjxx[i].getBdfw());
          poHbbgxx.setHbbgrq(voQrdjxx[i].getQrrq());
          poHbbgxx.setHjywid(hjywid);
          poHbbgxx.setSldw(this.getUserInfo().getDwdm());
          poHbbgxx.setSlrid(this.getUserInfo().getYhid());
          poHbbgxx.setSlsj(now);
          super.create(poHbbgxx);
        }

        //????????????????????????
        PoHJYW_QRDJXXB poQrdjxx = new PoHJYW_QRDJXXB();
        if (voSbjbxx != null) {
          BeanUtils.copyProperties(poQrdjxx, voSbjbxx);
        }
        BeanUtils.copyProperties(poQrdjxx, voQrdjxx[i]);
        BeanUtils.copyProperties(poQrdjxx, poRyxx);

        poQrdjxx.setHhid(poHxx.getHhid());
        poQrdjxx.setMlpid(poMlpxxxx.getMlpid());
        poQrdjxx.setCxbz(PublicConstant.CXBZ_FCX);
        poQrdjxx.setQrdjid( (Long) hjyw_qrdjxxbDAO.getId());
        poQrdjxx.setHjywid(hjywid);
        poQrdjxx.setSldw(this.getUserInfo().getDwdm());
        poQrdjxx.setSlrid(this.getUserInfo().getYhid());
        poQrdjxx.setSlsj(now);
        poQrdjxx.setCxfldm(voQrdjxx[i].getCxfldm());
        poQrdjxx.setHjdz_cxfldm(poRyxx.getCxfldm());
        super.create(poQrdjxx);

        //????????????????????????
        if(poQrdjxx.getCxfldm()==null || poQrdjxx.getCxfldm().equals(""))
          throw new Exception("???????????????????????????????????????????????????");

        if(poQrdjxx.getCxfldm().startsWith("2")){
          if(poQrdjxx.getNyzyrklhczyydm()==null || poQrdjxx.getNyzyrklhczyydm().equals(""))
            throw new Exception("???????????????????????????????????????????????????");
        }

        PoHJXX_CXSXBGB log = new PoHJXX_CXSXBGB();
        log.setBghcxsx(poJwhxxb.getCxfldm());
        log.setBgqcxsx(poQrdjxx.getCxfldm());
        log.setCjsj(poQrdjxx.getSlsj());
        log.setSldw(poJwhxxb.getDwdm());
        log.setBgqdw(poQrdjxx.getQcdssxq());
        log.setRynbid(poQrdjxx.getRynbid());
        log.setHjywid(poQrdjxx.getHjywid());
        log.setBgyy(poQrdjxx.getQrlb());
        log.setSsxq(poQrdjxx.getSsxq());
        log.setJwhdm(poJwhxxb.getDm());
        log.setNyzyrklhczyydm(poQrdjxx.getNyzyrklhczyydm());
        log.setYwlb("tsql");
        log.setBz("????????????");
        log.setRkbj("1");
        log.setRysl(new Long(1));
        super.create(log);

        //????????????????????????(??????)
        VoRybdxx voRybdxx = new VoRybdxx();
        voRybdxx.setBdbid(poQrdjxx.getQrdjid());
        voRybdxx.setBdfw(poQrdjxx.getBdfw());
        voRybdxx.setBdyy(poQrdjxx.getQrlb());
        voRybdxx.setQcdssxq(poQrdjxx.getQcdssxq());
        voRybdxx.setQcdxz(poQrdjxx.getQcdxz());
        voRybdxx.setBdrq(now.substring(0, 8));
        voRybdxx.setBdqhb(voQrdjxx[i].getQrqhb());
        voRybdxx.setBdq_hhnbid(null);
        voRybdxx.setBdh_hhnbid(poRyxx.getHhnbid());
        voRybdxx.setRynbid(poRyxx.getRynbid());
        voRybdxx.setRzjs(new Long(1));
        if (!bAdd && "1".equals(rh_lh)) {
          bAdd = true;
          voRybdxx.setHzjs(new Long(1));
        }
        else {
          voRybdxx.setHzjs(new Long(0));
        }
        rybdxxList.add(voRybdxx);

        //????????????
        if (voQrdjxx[i].getSpywid() != null) {
          PojoInfo  hjsp_hjspsqbDAO = DAOFactory.createHJSP_HJSPSQBDAO();
          PoHJSP_HJSPSQB poQrspxx = super.get(PoHJSP_HJSPSQB.class,
              voQrdjxx[i].getSpywid());
          if (poQrspxx == null) {
            throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                       "??????????????????????????????????????????????????????????????????????????????", null);
          }
          if (!HjConstant.SPJG_TY.equals(poQrspxx.getSpjg())) {
            throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                       "????????????????????????????????????????????????????????????", null);
          }
          poQrspxx.setHjywid(hjywid);
          poQrspxx.setRyid(poRyxx.getRyid());
          poQrspxx.setRynbid(poRyxx.getRynbid());
          poQrspxx.setLsbz(HjConstant.LSBZ_YLS);

          //???????????????????????????
          if(poQrspxx.getKdqqy_fkzt()!=null && !poQrspxx.getKdqqy_fkzt().equals("")){
            poQrspxx.setKdqqy_fkzt("3");
            poQrspxx.setKdqqy_fksj(StringUtils.formateDateTime());
          }

          super.update(poQrspxx);

          //?????????????????????????????????1
          this.decSPMBDQSYS(poQrspxx.getSpmbid());
        }
        if (voQrdjxx[i].getSpsqzid() != null) {
          PojoInfo  hjsp_hjspzbDAO = DAOFactory.createHJSP_HJSPZBDAO();
          PoHJSP_HJSPZB poQrspzxx = super.get(PoHJSP_HJSPZB.class, voQrdjxx[i].getSpsqzid());
          poQrspzxx.setRynbid(poRyxx.getRynbid());
          poQrspzxx.setRyid(poRyxx.getRyid());
          //???????????????????????????
          if(poQrspzxx.getKdqqy_fkzt()!=null && !poQrspzxx.getKdqqy_fkzt().equals("")){
            poQrspzxx.setKdqqy_fkzt("3");
            poQrspzxx.setKdqqy_fksj(StringUtils.formateDateTime());
          }
          
          super.update(poQrspzxx);
        }
        
        //??????????????????????????????
        voQrdjfhxx[i] = new VoQrdjfhxx();
        voQrdjfhxx[i].setQrdjid(poQrdjxx.getQrdjid());
        voQrdjfhxx[i].setHhnbid(poRyxx.getHhnbid());
        voQrdjfhxx[i].setRynbid(poRyxx.getRynbid());
        voQrdjfhxx[i].setRyid(poRyxx.getRyid());
        voQrdjfhxx[i].setGmsfhm(poRyxx.getGmsfhm());
        voQrdjfhxx[i].setXm(poRyxx.getXm());
        voQrdjfhxx[i].setYhzgx(poRyxx.getYhzgx());
        voQrdjfhxx[i].setSys_bh(voQrdjxx[i].getSys_bh());
      }

      ////////////////////////////////////////
      //?????????????????????
      VoHcygxtzfhxx voh[] = this.adjustHCYGX(hjywid, voSbjbxx,
                                             voHcygxtzxxEx, now);
      if (voh != null) {
        for (int i = 0; i < voh.length; i++) {
          hcygxtzfhxxList.add(voh[i]);
        }
      }

      ////////////////////////////////////////
      //??????????????????
      if (voBggzxxEx != null) {
        VoBggzfhxxEx vobEx = this.saveBGGZXX(hjywid, voSbjbxx, voBggzxxEx,
                                             PublicConstant.GNBH_HJ_QRDJYW, now);
        if (vobEx != null) {
          if (vobEx.getVoBggzfhxx() != null) {
            for (int a = 0; a < vobEx.getVoBggzfhxx().length; a++) {
              bggzfhxxList.add(vobEx.getVoBggzfhxx()[a]);
            }
          } //if(vobEx!=null)
          if (vobEx.getVoHcygxtzfhxx() != null) {
            for (int a = 0; a < vobEx.getVoHcygxtzfhxx().length; a++) {
              hcygxtzfhxxList.add(vobEx.getVoHcygxtzfhxx()[a]);
            }
          }
        } //if(vobEx!=null)
      }

      //////////////////////////////////
      //??????????????????
      Map hhnbidMap = new HashMap();
      for (int h = 0; h < hcygxtzfhxxList.size(); h++) {
        VoHcygxtzfhxx vo = (VoHcygxtzfhxx) hcygxtzfhxxList.get(h);
        hhnbidMap.put(vo.getHhnbid(), null);
      }
      for (Iterator itr = hhnbidMap.keySet().iterator(); itr.hasNext(); ) {
        this.checkHCYXX( (Long) itr.next());
      }

      ////////////////////////////////////////
      //??????????????????
      this.saveCHXX(hjywid, chxxList, now);

      ////////////////////////////////////////
      //??????????????????????????????
      //rh_lh = "1"; //?????????????????????1-??????/2-?????????
      PoHJLS_HJYWLSB poHjywlsxx = this.saveHJYWLSXX(hjywid,
          PublicConstant.GNBH_HJ_QRDJYW,
          rh_lh.equals("1") ? PublicConstant.HJYWLS_YWLX_QH :
          PublicConstant.HJYWLS_YWLX_GR,
          voQrdjxx.length, voSbjbxx, now);

      //////////////////////////////////////////
      //????????????????????????(??????)
      this.saveRYBDXX(rybdxxList, poHjywlsxx);

      ///////////////////////////////////////////
      //????????????????????????????????????????????????????????????????????????
      PoHJXX_CZRKJBXXB poHzxx = null;
      String strHQL = "from PoHJYW_QRDJXXB where hjywid=" + hjywid;
      List qrdjxxList = super.findAllByHQL(strHQL);
      if (qrdjxxList != null && qrdjxxList.size() > 0) {
        for (int i = 0; i < qrdjxxList.size(); i++) {
          PoHJYW_QRDJXXB poQrdjxx = (PoHJYW_QRDJXXB) qrdjxxList.get(i);
          if (poHzxx == null) {
            strHQL = "from PoHJXX_CZRKJBXXB where yhzgx < '10' and ryzt='" +
                HjConstant.RYZT_ZC + "' and jlbz='" +
                PublicConstant.JLBZ_ZX + "' and cxbz='" +
                PublicConstant.CXBZ_FCX +
                "' and hhnbid =" + poQrdjxx.getHhnbid();
            List hzxxList = super.findAllByHQL(strHQL);
            if (hzxxList != null && hzxxList.size() > 0) {
              poHzxx = (PoHJXX_CZRKJBXXB) hzxxList.get(0);
            }
          }
          poQrdjxx.setHzgmsfhm(poHzxx != null ? poHzxx.getGmsfhm() : null);
          poQrdjxx.setHzxm(poHzxx != null ? poHzxx.getXm() : null);
          poQrdjxx.setYwlx(poHjywlsxx.getYwlx());
          poQrdjxx.setCzsm(poHjywlsxx.getCzsm());
          super.update(poQrdjxx);
        }
      }

      ///////////////////////////////////////////
      //????????????????????????????????????????????????????????????????????????
      strHQL = "from PoHJYW_HBBGXXB where hjywid=" + hjywid;
      List hbbgxxList = super.findAllByHQL(strHQL);
      if (hbbgxxList != null && hbbgxxList.size() > 0) {
        for (int i = 0; i < hbbgxxList.size(); i++) {
          PoHJYW_HBBGXXB poHbbgxx = (PoHJYW_HBBGXXB) hbbgxxList.get(i);
          poHbbgxx.setHzgmsfhm(poHzxx != null ? poHzxx.getGmsfhm() : null);
          poHbbgxx.setHzxm(poHzxx != null ? poHzxx.getXm() : null);
          poHbbgxx.setYwlx(poHjywlsxx.getYwlx());
          poHbbgxx.setCzsm(poHjywlsxx.getCzsm());
          super.update(poHbbgxx);
        }
      }

      //??????????????????????????????????????????????????????????????????????????????
      for (int i = 0; i < poList.size(); i++) {
        Map mpo = (Map) poList.get(i);

        String strpoHQL = "from PoHJXX_CZRKJBXXB where ryzt='" + HjConstant.RYZT_ZC +
            "' and jlbz='" + PublicConstant.JLBZ_ZX +
            "' and cxbz='" + PublicConstant.CXBZ_FCX +
            "' and gmsfhm ='" + mpo.get("pogmsfhm") +
            "' and hh ='" + mpo.get("hh") + "'";
        List poxxList = super.findAllByHQL(strpoHQL);

        if (poxxList != null && poxxList.size() > 0) {
          PoHJXX_CZRKJBXXB poczrk = (PoHJXX_CZRKJBXXB) poxxList.get(0);
          if (! (mpo.get("gmsfhm").equals(poczrk.getPogmsfhm()) &&
                 mpo.get("xm").equals(poczrk.getPoxm()))) {
            throw new ServiceException(WSErrCode.ERR_SERVICE_DATANOTFULL,
                                       "????????????????????????????????????????????????????????????????????????????????????", null);
          }

          int hy = -1;
          if (poczrk.getHyzk() != null && !"".equals(poczrk.getHyzk())) {
            hy = "[20][21][22][23]".indexOf(poczrk.getHyzk());
          }
          if (hy < 0) {
            throw new ServiceException(WSErrCode.ERR_SERVICE_DATANOTFULL,
                                       "?????????????????????????????????????????????????????????????????????????????????", null);
          }
        }
      }

      ////////////////////////////////////
      //????????????????????????
      voQrdjywfhxx = new VoQrdjywfhxx();
      voQrdjywfhxx.setHjywid(hjywid);
      voQrdjywfhxx.setHhnbid(poHxx.getHhnbid());
      voQrdjywfhxx.setMlpnbid(poMlpxxxx.getMlpnbid());
      voQrdjywfhxx.setVoQrdjfhxx(voQrdjfhxx);
      voQrdjywfhxx.setVoBggzfhxx( (VoBggzfhxx[]) bggzfhxxList.toArray(new
          VoBggzfhxx[bggzfhxxList.size()]));
      voQrdjywfhxx.setVoHcygxtzfhxx( (VoHcygxtzfhxx[]) hcygxtzfhxxList.toArray(new
          VoHcygxtzfhxx[hcygxtzfhxxList.size()]));

      /////////////////////////////
      //????????????
    }
    catch (DAOException ex) {
      //????????????
      throw ex;
    }
    catch (ServiceException ex) {
      //????????????
      throw ex;
    }
    catch (InvocationTargetException ex) {
      //????????????
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }
    catch (IllegalAccessException ex) {
    	//????????????
    	throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }
    catch (SQLException ex) {
      //????????????
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }
    catch (Exception ex) {
      //????????????
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }

    return voQrdjywfhxx;
  }

  /**
   * ????????????????????????
   * @param voLhhdxx - ??????????????????
   * @param voRhhdxx - ??????????????????
   * @param voSbjbxx - ??????????????????
   * @param voZzbdxx[] - ??????????????????
   * @param voBggzxx[] - ??????????????????
   * @return com.hzjc.hz2004.vo.VoZzbdywfhxx
   * @roseuid 406A2CC10001
   */
  public VoZzbdywfhxx processZzbdyw(VoLhhdxx voLhhdxx, VoRhhdxx voRhhdxx,
                                    VoSbjbxx voSbjbxx, VoZzbdxx voZzbdxx[],
                                    VoBggzxxEx voBggzxxEx[]) throws
      ServiceException,
      DAOException {

    VoZzbdywfhxx voZzbdywfhxx = null;
    VoZzbdfhxx voZzbdfhxx[] = null;
    VoHcygxtzxxEx voHcygxtzxxEx[] = null;
    List bggzfhxxList = new ArrayList();
    List hcygxtzfhxxList = new ArrayList();
    List chxxList = new ArrayList();
    List rybdxxList = new ArrayList();
    VoLhhdfhxx voLhhdfhxx = null;
    VoZxhxx voZxhxx[] = null;
    Long hjywid = null;
    PoHJXX_MLPXXXXB poMlpxxxx_QR = null;
    PoXT_JWHXXB poJwhxxb_QR = null;
    PoHJXX_HXXB poHxx_QR = null;
    String rh_lh; //?????????????????????1-??????/2-?????????
    boolean bAdd = false;
    String now = StringUtils.getServiceTime();

    if (voZzbdxx == null || (voZzbdxx != null && voZzbdxx.length <= 0)) {
      return null;
    }

    try {

      PojoInfo  hjxx_mlpxxxxbDAO = DAOFactory.createHJXX_MLPXXXXBDAO();
      PojoInfo  hjxx_czrkjbxxbDAO = DAOFactory.createHJXX_CZRKJBXXBDAO();
      PojoInfo  hjxx_hxxbDAO = DAOFactory.createHJXX_HXXBDAO();
      PojoInfo  hjls_hjywlsbDAO = DAOFactory.createHJLS_HJYWLSBDAO();
      PojoInfo  hjyw_zzbdxxbDAO = DAOFactory.createHJYW_ZZBDXXBDAO();
      PojoInfo  hjyw_qcclxxbDAO = DAOFactory.createHJYW_QCCLXXBDAO();
      PojoInfo  hjyw_chclxxbDAO = DAOFactory.createHJYW_CHCLXXBDAO();
      PojoInfo  hjxx_hglgxbDAO = DAOFactory.createHJXX_HGLGXBDAO();
      PojoInfo  hjyw_hbbgxxbDAO = DAOFactory.createHJYW_HBBGXXBDAO();
      PojoInfo  xt_jwhxxbDAO = DAOFactory.createXT_JWHXXBDAO();

      /////////////////////////////////////
      //??????????????????ID
      hjywid = (Long) hjls_hjywlsbDAO.getId();
      
      ///////////////////////////////////////////
      //?????????

      if(voSbjbxx!=null && voSbjbxx.getHzywid()!=null){
                    //???????????????????????????
                    String gmsfhm = voSbjbxx.getSbrgmsfhm();
                    String hsql = "select count(a.gmsfhm) from PoHJXX_CZRKJBXXB a where a.gmsfhm='" + gmsfhm + "'";
                    List list = super.findEntities(hsql);
                    /*
                    if(list.size()==0 || Integer.parseInt(list.get(0).toString())<=0){
                      throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "???????????????????????????????????????????????????????????????", null);
                    }
                    */
                    list = super.findEntities("from PoHZ_ZJ_SB a where a.id=" + voSbjbxx.getHzywid());
                    if(list.size()>0){
                      PoHZ_ZJ_SB sb = (PoHZ_ZJ_SB)list.get(0);
                      String kzz = super.getXTKZCS("10031");
                      String kzz1 = super.getXTKZCS("10032");
                      String kzz2 = super.getXTKZCS("10037");
                    //1.??????????????????   2.wx_code????????????    add by zjm 20191106 
                      if(kzz.equals("1")) {//????????????1??????????????????????????????????????????HZPT_RECEIVESTATE????????????
                    	  if(CommonUtil.isNotEmpty(sb.getWx_code())) {
                    		  updateHzptReceiveState(kzz,sb);
                    	  }
                      }
                      if(kzz1.equals("1")) {//????????????1??????????????????????????????????????????HZPT_RECEIVESTATE????????????
                    	  if(CommonUtil.isNotEmpty(sb.getWx_code())) {
                    		  insertEms(kzz1,sb);
                    	  }
                      }
                      
                      if (sb.getPch() != null && !sb.getPch().equals("")) {
                        //???????????????????????????
                        System.out.println("???????????????????????????");
                        list = super.findEntities(
                            "from PoHZ_ZJ_SB a where a.pch='" + sb.getPch() + "' and a.clbs='0' ");
                        int pccount = list.size();
                        for (int index = 0; index < pccount; index++) {
                          PoHZ_ZJ_SB sbx = (PoHZ_ZJ_SB) list.get(index);
                          sbx.setClbs("1");
                          sbx.setHjywid(hjywid);
                          sbx.setHjdpcs(this.getUser().getDwdm());
                          sbx.setBlrsfz(this.getUser().getGmsfhm());
                          sbx.setClsj(CommonUtil.getTimestamp(null));
                          super.update(sbx);
                          if(kzz2.equals("1")) {//add by zjm 20200710 ????????????2????????????????????????????????????
                        	  autoInsertHzjsbs(kzz2,sbx);
                          }
                        }
                      }
                      else {
                        sb.setClbs("1");
                        sb.setHjywid(hjywid);
                        sb.setHjdpcs(this.getUser().getDwdm());
                        sb.setBlrsfz(this.getUser().getGmsfhm());
                        sb.setClsj(CommonUtil.getTimestamp(new Date()));
                        super.update(sb);
                        autoInsertHzjsbs(kzz2,sb);
                      }
                      
                    }
      }
      String djzt="";
      //////////////////////////////////////
      //???????????????????????????
      //??????
      if (voRhhdxx != null && voRhhdxx.getHhnbid() != null) {
        //???????????????
        poHxx_QR = super.get(PoHJXX_HXXB.class,voRhhdxx.getHhnbid());
        if (poHxx_QR == null) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "???????????????????????????????????????????????????????????????", null);
        }
        //???????????????????????????
        this.checkHXX(poHxx_QR, hjxx_hxxbDAO, "??????????????????");
        //??????????????????
        if (!HjConstant.HHZT_ZC.equals(poHxx_QR.getHhzt())) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "??????????????????????????????????????????????????????????????????", null);
        }
        List list = super.findAllByHQL("from PoHJXX_CZRKJBXXB a where ryzt='0' and cxbz='0' and jlbz='1' and a.hhnbid=" + poHxx_QR.getHhnbid());
        if(list.size()>0) {
        	PoHJXX_CZRKJBXXB czrkjbxxb = (PoHJXX_CZRKJBXXB) list.get(0);
        	if(czrkjbxxb.getDjzt()!=null&&!czrkjbxxb.getDjzt().equals("1")) {
        		djzt = czrkjbxxb.getDjzt();
        	}
        }
        //???????????????
        poMlpxxxx_QR = super.get(PoHJXX_MLPXXXXB.class, poHxx_QR.getMlpnbid());
        if (poMlpxxxx_QR == null) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "??????????????????????????????????????????????????????????????????", null);
        }

        poJwhxxb_QR = (PoXT_JWHXXB) super.get(PoXT_JWHXXB.class, poMlpxxxx_QR.getJcwh());

        if (poJwhxxb_QR == null) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "?????????????????????????????????????????????????????????", null);
        }


        //?????????????????????(????????????2005/05/25 10:40:00)
        //if (!DzConstant.MLPZT_ZC.equals(poMlpxxxx_QR.getMlpzt())) {
        //  throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
        //                             "?????????????????????????????????????????????????????????????????????", null);
        //}
        rh_lh = "2"; //?????????????????????1-??????/2-?????????
      }
      //??????
      else {
        voLhhdfhxx = this.createH(hjywid, voLhhdxx, voZzbdxx[0].getBdfw(),
                                  voZzbdxx[0].getZzbdlb(),
                                  HjConstant.YWNR_ZZBDQR, now);
        if (voLhhdfhxx == null ||
            (voLhhdfhxx != null &&
             (voLhhdfhxx.getHhnbid() == null || voLhhdfhxx.getMlpnbid() == null))) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "????????????????????????????????????????????????????????????", null);
        }
        poHxx_QR = voLhhdfhxx.getPoHJXX_HXXB();
        poMlpxxxx_QR = voLhhdfhxx.getPoHJXX_MLPXXXXB();
        rh_lh = "1"; //?????????????????????1-??????/2-?????????

        poJwhxxb_QR = (PoXT_JWHXXB) super.get(PoXT_JWHXXB.class,poMlpxxxx_QR.getJcwh());
        if (poJwhxxb_QR == null) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "?????????????????????????????????????????????????????????", null);
        }

      }

      //////////////////////////////////////////////
      //??????????????????
      List sjfwList = new ArrayList();
      VoXtsjfw voXtsjfw = new VoXtsjfw();
      voXtsjfw.setSjfw(poMlpxxxx_QR.getJcwh());
      voXtsjfw.setSjfwbz(PublicConstant.XT_QX_JWH);
      sjfwList.add(voXtsjfw);
      boolean bLimit = false;
      bLimit = XtywqxServiceImpl.VerifyDataRange(this.getUserInfo().getYhid().
                                                 toString(),
                                                 PublicConstant.GNBH_HJ_ZZBDYW,
                                                 sjfwList);
      if (!bLimit) {
        throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLIMIT,
        		CommonUtil.getSjfwError(sjfwList)  + "????????????????????????????????????", null);
      }

      //////////////////////////////////////
      //????????????????????????
      voZzbdfhxx = new VoZzbdfhxx[voZzbdxx.length];
      voHcygxtzxxEx = new VoHcygxtzxxEx[voZzbdxx.length * 2]; //??????????????????????????????????????????????????????????????????
      voZxhxx = new VoZxhxx[voZzbdxx.length];
      for (int i = 0; i < voZzbdxx.length; i++) {

        //??????????????????
        PoHJXX_CZRKJBXXB poRyxx = super.get(PoHJXX_CZRKJBXXB.class,voZzbdxx[i].getRynbid());
        if (poRyxx == null) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "???????????????????????????????????????????????????????????????", null);
        }
        //???????????????????????????
        this.checkRXX(poRyxx, hjxx_czrkjbxxbDAO, "??????????????????");
        //??????????????????
        if (!HjConstant.RYSDZT_ZC.equals(poRyxx.getRysdzt())) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "??????????????????????????????(?????????)????????????????????????????????????", null);
        }
        //????????????
        if (!HjConstant.RYZT_ZC.equals(poRyxx.getRyzt())) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "????????????????????????????????????????????????????????????", null);
        }
        PoHJXX_HXXB poHxx_QC = super.get(PoHJXX_HXXB.class,poRyxx.getHhnbid());
        if (poHxx_QC == null) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "???????????????????????????????????????????????????????????????????????????", null);
        }
        //?????????????????????????????????(poMlpxxxx_QC??????????????????)
        PoHJXX_MLPXXXXB poMlpxxxx_QC = super.get(PoHJXX_MLPXXXXB.class,
            poRyxx.
            getMlpnbid());

        if (poMlpxxxx_QC == null) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "???????????????????????????????????????????????????????????????????????????", null);
        }

        PoXT_JWHXXB poJwhxxb_QC = null;
        poJwhxxb_QC = (PoXT_JWHXXB) super.get(PoXT_JWHXXB.class,poMlpxxxx_QC.getJcwh());
        if (poJwhxxb_QC == null) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "??????????????????????????????????????????????????????????????????", null);
        }

        if (PublicConstant.XTKZCS_QY.equals(this.getXTKZCS(PublicConstant.
            XTKZCS_SHQYZDQYPD))) {
          if (!this.checkZZBD(poMlpxxxx_QR.getSsxq(), poMlpxxxx_QC.getSsxq())) {
            throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                       "??????????????????????????????????????????????????????????????????????????????????????????", null);
          }
        }

        //Begin_?????????????????????_MHB_2005/08/23 15:02:00_????????????
        if (this.countCHRYXX(poRyxx.getGmsfhm()) > 1) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "?????????????????????????????????????????????????????????????????????????????????", null);
        }
        //End_?????????????????????_MHB_2005/08/23 15:02:00

        //?????????????????????????????????
        poRyxx.setJssj(now);
        poRyxx.setCchjywid(hjywid);
        poRyxx.setJlbz(PublicConstant.JLBZ_LS);
        super.update(poRyxx);
        //???????????????
        PoHJXX_CZRKJBXXB poRyxxQC = new PoHJXX_CZRKJBXXB();
        BeanUtils.copyProperties(poRyxxQC, poRyxx);
        poRyxxQC.setRynbid( (Long) hjxx_czrkjbxxbDAO.getId());
        poRyxxQC.setQysj(now);
        poRyxxQC.setJssj(now);
        poRyxxQC.setXxqysj(now);//add by hb 20061027
        poRyxxQC.setCjhjywid(hjywid);
        poRyxxQC.setCchjywid(hjywid);
        poRyxxQC.setYwnr(HjConstant.YWNR_ZZBDQC);
        poRyxxQC.setRyzt(HjConstant.RYZT_QC);
        poRyxxQC.setJlbz(PublicConstant.JLBZ_LS);
        poRyxxQC.setBdfw(voZzbdxx[i].getBdfw());
        poRyxxQC.setCxfldm(poJwhxxb_QC.getCxfldm());

        super.create(poRyxxQC);
        //??????????????????????????????
        PoHJXX_CZRKJBXXB poRyxxNew = new PoHJXX_CZRKJBXXB();
        BeanUtils.copyProperties(poRyxxNew, poRyxx);
        BeanUtils.copyProperties(poRyxxNew, poHxx_QR);
        BeanUtils.copyProperties(poRyxxNew, poMlpxxxx_QR); //add by mhb 2005/07/04 11:20
        if(voZzbdxx[i].getYhzgx()!=null) {
        	poRyxxNew.setYhzgx(voZzbdxx[i].getYhzgx());
        }
        //??????????????????????????????????????????
        poRyxxNew.setRynbid( (Long) hjxx_czrkjbxxbDAO.getId());
        //??????poRyxxNew??????????????????begin
        if (voBggzxxEx != null) {
          for (int k = 0; k < voBggzxxEx.length; k++) {
            if (voZzbdxx[i].getRynbid().equals(voBggzxxEx[k].getRynbid())) {
              voBggzxxEx[k].setFlag(0); ////?????????????????????????????????????????????????????????(0-?????????/1-??????)
              VoBggzfhxxEx vobEx = this.disposalBggzxx(hjywid, voSbjbxx,
                  voBggzxxEx[k], poRyxxNew, PublicConstant.GNBH_HJ_ZZBDYW, now);
              if (vobEx != null) {
                if (vobEx.getVoBggzfhxx() != null) {
                  for (int a = 0; a < vobEx.getVoBggzfhxx().length; a++) {
                    bggzfhxxList.add(vobEx.getVoBggzfhxx()[a]);
                  }
                } //if(vobEx!=null)
                if (vobEx.getVoHcygxtzfhxx() != null) {
                  for (int a = 0; a < vobEx.getVoHcygxtzfhxx().length; a++) {
                    hcygxtzfhxxList.add(vobEx.getVoHcygxtzfhxx()[a]);
                  }
                }
              } //if(vobEx!=null)
            } //if(voHbbgxx[i].getRynbid().equals(voBggzxx[k].getRynbid()))
          } //for(int k=0;k<voBggzxx.length;k++)
        }
        //end
        if (voZzbdxx[i].getZzbdhhb() != null) {
          poRyxxNew.setHb(voZzbdxx[i].getZzbdhhb());
        }
        //>=33
        if (voZzbdxx[i].getBdfw() != null &&
            voZzbdxx[i].getBdfw().compareTo(HjConstant.BDFW_BSSXXC) >= 0) {
          //??????
          poRyxxNew.setHyql(voZzbdxx[i].getZzbdlb()); //????????????
          poRyxxNew.setHsql(voZzbdxx[i].getZzbdrq()); //????????????
          poRyxxNew.setNyzyrklhczyydm(voZzbdxx[i].getNyzyrklhczyydm());
          poRyxxNew.setHgjdqql(null); //???????????????????????????
          poRyxxNew.setHssxqql(poMlpxxxx_QC.getSsxq()); //???????????????????????????
          poRyxxNew.setHxzql(this.generateZZ(poMlpxxxx_QC,
                                             PublicConstant.XTKZCS_DZPZFS,
                                             PublicConstant.
                                             XTKZCS_DZPZFS_QCDXZ)); //???????????????
          //Begin_???????????????33???34???????????????????????????????????????(2004/11/19 11:00:00)
          if (HjConstant.BDFW_BSSXXC.compareTo(voZzbdxx[i].getBdfw()) == 0 ||
              HjConstant.BDFW_BSSXCZ.compareTo(voZzbdxx[i].getBdfw()) == 0) {
            //??????
            poRyxxNew.setHylbz(null); //???????????????
            poRyxxNew.setHslbz(null); //???????????????
            poRyxxNew.setHgjdqlbz(null); //??????????????????????????????
            poRyxxNew.setHsssqlbz(null); //??????????????????????????????
            poRyxxNew.setHxzlbz(null); //???????????????
          }
          //End_???????????????33???34???????????????????????????????????????
        }
        else {
          //??????
          poRyxxNew.setHylbz(voZzbdxx[i].getZzbdlb()); //???????????????
          poRyxxNew.setHslbz(voZzbdxx[i].getZzbdrq()); //???????????????
          poRyxxNew.setHgjdqlbz(null); //??????????????????????????????
          poRyxxNew.setHsssqlbz(poMlpxxxx_QC.getSsxq()); //??????????????????????????????
          poRyxxNew.setHxzlbz(this.generateZZ(poMlpxxxx_QC,
                                              PublicConstant.XTKZCS_DZPZFS,
                                              PublicConstant.
                                              XTKZCS_DZPZFS_QCDXZ)); //???????????????
        }
        poRyxxNew.setBdfw(voZzbdxx[i].getBdfw());
        poRyxxNew.setNyzyrklhczyydm(voZzbdxx[i].getNyzyrklhczyydm());
        poRyxxNew.setHhnbid(poHxx_QR.getHhnbid());
        poRyxxNew.setMlpnbid(poMlpxxxx_QR.getMlpnbid());
        poRyxxNew.setQysj(now);
        poRyxxNew.setJssj(null);
        poRyxxNew.setXxqysj(now);//add by hb 20061027
        poRyxxNew.setYwnr(HjConstant.YWNR_ZZBDQR);
        poRyxxNew.setCchjywid(null);
        poRyxxNew.setCjhjywid(hjywid);
        poRyxxNew.setJlbz(PublicConstant.JLBZ_ZX);
        poRyxxNew.setCxfldm(poJwhxxb_QR.getCxfldm());
        poRyxxNew.setJthzl(voZzbdxx[i].getJthzl());
        if(CommonUtil.isNotEmpty(djzt)) {
        	poRyxxNew.setDjzt(djzt);
        }
        super.create(poRyxxNew);

        if(poJwhxxb_QC.getCxfldm()==null || poJwhxxb_QR.getCxfldm()==null)
          throw new Exception("????????????????????????????????????????????????????????????????????????");

        //??????????????????
        if (voZzbdxx[i].getZzbdhhb() != null &&
            voZzbdxx[i].getZzbdhhb().length() > 0 &&
            !voZzbdxx[i].getZzbdhhb().equals(poRyxx.getHb())) {

          //????????????????????????
          PoHJYW_HBBGXXB poHbbgxx = new PoHJYW_HBBGXXB();
          if (voSbjbxx != null) {
            BeanUtils.copyProperties(poHbbgxx, voSbjbxx);
          }
          BeanUtils.copyProperties(poHbbgxx, poRyxxNew);
          poHbbgxx.setHhid(poHxx_QR.getHhid());
          poHbbgxx.setMlpid(poMlpxxxx_QR.getMlpid());
          poHbbgxx.setHbbgid( (Long) hjyw_hbbgxxbDAO.getId());
          poHbbgxx.setRynbid(poRyxxNew.getRynbid());
          poHbbgxx.setCxbz(PublicConstant.CXBZ_FCX);
          poHbbgxx.setBgqhb(poRyxx.getHb());
          poHbbgxx.setBghhb(poRyxxNew.getHb());
          poHbbgxx.setHbbglb(voZzbdxx[i].getZzbdlb());
          poHbbgxx.setBdfw(voZzbdxx[i].getBdfw());
          poHbbgxx.setHbbgrq(voZzbdxx[i].getZzbdrq());
          poHbbgxx.setHjywid(hjywid);
          poHbbgxx.setSldw(this.getUserInfo().getDwdm());
          poHbbgxx.setSlrid(this.getUserInfo().getYhid());
          poHbbgxx.setSlsj(now);
          super.create(poHbbgxx);
        }

        //??????????????????
        VoRhdxx voRhdxx = new VoRhdxx(poRyxxNew, poHxx_QR, poMlpxxxx_QR,
                                      BaseContext.getUser(), poRyxx);
        VoXtywxz voLimit = XtywqxServiceImpl.VerifyBusinessLimit(
            PublicConstant.
            GNBH_HJ_ZZBDYW, voRhdxx);
        if (voLimit.getLimitflag()) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLIMIT,
                                     "??????????????????????????????????????????" + voLimit.getLimitinfo(), null);
        }
        voRhdxx = null;

        //??????????????????(????????????????????????????????????????????????????????????????????????)
        String strHQL = "from PoHJYW_CHCLXXB as HJYW_CHCLXXB where clfs='" +
            HjConstant.CHCLFS_WCL + "' and chsfhm='" + poRyxxNew.getGmsfhm() +
            "' and cxbz='" + PublicConstant.CXBZ_FCX + "' ";
        List xcchxxList = super.findAllByHQL(strHQL);
        if (xcchxxList != null) {
          for (int j = 0; j < xcchxxList.size(); j++) {
            PoHJYW_CHCLXXB poChclxx = (PoHJYW_CHCLXXB) xcchxxList.get(j);
            //????????????
            if (poRyxxNew.getRyid().equals(poChclxx.getRyid())) {
              poChclxx.setClfs(HjConstant.CHCLFS_BRQC);
              poChclxx.setClhjywid(hjywid);
              super.update(poChclxx);
            }
            //??????????????????
            if (poRyxxNew.getRyid().equals(poChclxx.getBchryid())) {
              poChclxx.setClfs(HjConstant.CHCLFS_DFQC);
              poChclxx.setClhjywid(hjywid);
              super.update(poChclxx);
            }
          } //for (int j = 0; j < chxxList.size(); j++)
        }

        //??????????????????????????????????????????(????????????????????????????????????????????????????????????????????????)
        if (this.countCHRYXX(poRyxxNew.getGmsfhm()) > 1) {
          VoChxx ivoChxx = new VoChxx();
          ivoChxx.setBchryid(poRyxxNew.getRyid());
          ivoChxx.setChsfhm(poRyxxNew.getGmsfhm());
          chxxList.add(ivoChxx);
        }

        //????????????????????????
        PoHJYW_ZZBDXXB poZzbdxx = new PoHJYW_ZZBDXXB();
        if (voSbjbxx != null) {
          BeanUtils.copyProperties(poZzbdxx, voSbjbxx);
        }
        BeanUtils.copyProperties(poZzbdxx, voZzbdxx[i]);
        BeanUtils.copyProperties(poZzbdxx, poRyxxNew);
        poZzbdxx.setZzbdid( (Long) hjyw_zzbdxxbDAO.getId());
        poZzbdxx.setHhid(poHxx_QR.getHhid());
        poZzbdxx.setMlpid(poMlpxxxx_QR.getMlpid());
        poZzbdxx.setHh_q(poHxx_QC.getHh());
        poZzbdxx.setJthzl(voZzbdxx[i].getJthzl());
        poZzbdxx.setHlx_q(poHxx_QC.getHlx());
        poZzbdxx.setHb_q(poRyxxQC.getHb());
        poZzbdxx.setMlpnbid_q(poMlpxxxx_QC.getMlpnbid());
        poZzbdxx.setMlpid_q(poMlpxxxx_QC.getMlpid());
        poZzbdxx.setSsxq_q(poMlpxxxx_QC.getSsxq());
        poZzbdxx.setJlx_q(poMlpxxxx_QC.getJlx());
        poZzbdxx.setMlph_q(poMlpxxxx_QC.getMlph());
        poZzbdxx.setMlxz_q(poMlpxxxx_QC.getMlxz());
        poZzbdxx.setPcs_q(poMlpxxxx_QC.getPcs());
        poZzbdxx.setZrq_q(poMlpxxxx_QC.getZrq());
        poZzbdxx.setXzjd_q(poMlpxxxx_QC.getXzjd());
        poZzbdxx.setJcwh_q(poMlpxxxx_QC.getJcwh());
        poZzbdxx.setPxh_q(poMlpxxxx_QC.getPxh());
        poZzbdxx.setJdlb_q(poMlpxxxx_QC.getJdlb());
        poZzbdxx.setCdlb_q(poMlpxxxx_QC.getCdlb());
        poZzbdxx.setCxbz(PublicConstant.CXBZ_FCX);
        poZzbdxx.setYhhnbid(poRyxx.getHhnbid());
        poZzbdxx.setYhhid(poHxx_QC.getHhid());
        poZzbdxx.setHjywid(hjywid);
        poZzbdxx.setSldw(this.getUserInfo().getDwdm());
        poZzbdxx.setSlrid(this.getUserInfo().getYhid());
        poZzbdxx.setSlsj(now);
        poZzbdxx.setCxsxtz_pdbz("0");
        
        /**
			????????????????????????????????????????????? ?????????????????????????????????
			?????????????????????????????????????????????????????????????????????
			???????????????????????????
         */
//        poZzbdxx.setCxfldm_q(poZzbdxx.getCxfldm());
        
        if(poJwhxxb_QC!=null && poJwhxxb_QR!=null && poJwhxxb_QC.getCxfldm()!=null && poJwhxxb_QR.getCxfldm()!=null && poJwhxxb_QC.getCxfldm().length()>0 && poJwhxxb_QR.getCxfldm().length()>0){
          if(!poJwhxxb_QC.getCxfldm().substring(0,1).equals(poJwhxxb_QR.getCxfldm().substring(0,1))){
            poZzbdxx.setCxsxtz_pdbz("1");
          }
        }

        super.create(poZzbdxx);

        if(!poJwhxxb_QC.getCxfldm().equals(poJwhxxb_QR.getCxfldm())){
          //????????????
          PoHJXX_CXSXBGB log_QR = new PoHJXX_CXSXBGB();
          log_QR.setBghcxsx(poJwhxxb_QR.getCxfldm());
          log_QR.setBgqcxsx(poJwhxxb_QC.getCxfldm());
          log_QR.setCjsj(now);
          log_QR.setSldw(poJwhxxb_QR.getDwdm());
          log_QR.setBgqdw(poJwhxxb_QC.getDwdm());
          log_QR.setRynbid(poRyxxNew.getRynbid());
          log_QR.setHjywid(hjywid);
          log_QR.setBgyy(poZzbdxx.getZzbdlb());
          log_QR.setYwlb("snbd");
          log_QR.setBz("????????????,??????");
          log_QR.setRkbj("1");
          log_QR.setSsxq(poRyxxNew.getSsxq());
          log_QR.setJwhdm(poJwhxxb_QR.getDm());
          log_QR.setRysl(new Long(1));
          log_QR.setNyzyrklhczyydm(poRyxxNew.getNyzyrklhczyydm());
          super.create(log_QR);

          PoHJXX_CXSXBGB log_QC = new PoHJXX_CXSXBGB();
          log_QC.setBghcxsx(poJwhxxb_QC.getCxfldm());
          log_QC.setBgqcxsx(poJwhxxb_QR.getCxfldm());
          log_QC.setCjsj(now);
          log_QC.setSldw(poJwhxxb_QC.getDwdm());
          log_QC.setBgqdw(poJwhxxb_QR.getDwdm());
          log_QC.setRynbid(poRyxxNew.getRynbid());
          log_QC.setHjywid(hjywid);
          log_QC.setBgyy(poZzbdxx.getZzbdlb());
          log_QC.setYwlb("snbd");
          log_QC.setBz("????????????,??????");
          log_QC.setRkbj("0");
          log_QC.setSsxq(poMlpxxxx_QC.getSsxq());
          log_QC.setJwhdm(poJwhxxb_QC.getDm());
          log_QC.setRysl(new Long(1));
          super.create(log_QC);
        }

        //??????????????????????????????????????????????????????
        //?????????
        voHcygxtzxxEx[i * 2] = new VoHcygxtzxxEx();
        voHcygxtzxxEx[i * 2].setFlag(0);
        voHcygxtzxxEx[i * 2].setHcybdlx(HjConstant.HCYBDLX_LH);
        voHcygxtzxxEx[i * 2].setHcybdlb(voZzbdxx[i].getZzbdlb());
        voHcygxtzxxEx[i * 2].setHhnbid(poRyxx.getHhnbid());
        voHcygxtzxxEx[i * 2].setRynbid(poRyxx.getRynbid());
        voHcygxtzxxEx[i * 2].setNew_rynbid(poRyxxQC.getRynbid());
        voHcygxtzxxEx[i * 2].setRyid(poRyxx.getRyid());
        voHcygxtzxxEx[i * 2].setXm(poRyxx.getXm());
        voHcygxtzxxEx[i * 2].setGmsfhm(poRyxx.getGmsfhm());
        voHcygxtzxxEx[i * 2].setOld_yhzgx(poRyxx.getYhzgx());
        voHcygxtzxxEx[i * 2].setYhzgx(poRyxxQC.getYhzgx());
        voHcygxtzxxEx[i * 2].setSbhjywid(null);
        //?????????
        voHcygxtzxxEx[i * 2 + 1] = new VoHcygxtzxxEx();
        voHcygxtzxxEx[i * 2 + 1].setFlag(0);
        voHcygxtzxxEx[i * 2 + 1].setHcybdlx(HjConstant.HCYBDLX_RH);
        voHcygxtzxxEx[i * 2 + 1].setHcybdlb(voZzbdxx[i].getZzbdlb());
        voHcygxtzxxEx[i * 2 + 1].setHhnbid(poRyxxNew.getHhnbid());
        voHcygxtzxxEx[i * 2 + 1].setRynbid(poRyxxQC.getRynbid());
        voHcygxtzxxEx[i * 2 + 1].setNew_rynbid(poRyxxNew.getRynbid());
        voHcygxtzxxEx[i * 2 + 1].setRyid(poRyxxNew.getRyid());
        voHcygxtzxxEx[i * 2 + 1].setXm(poRyxxNew.getXm());
        voHcygxtzxxEx[i * 2 + 1].setGmsfhm(poRyxxNew.getGmsfhm());
        voHcygxtzxxEx[i * 2 + 1].setOld_yhzgx(poRyxxQC.getYhzgx());
        voHcygxtzxxEx[i * 2 + 1].setYhzgx(poRyxxNew.getYhzgx());
        voHcygxtzxxEx[i * 2 + 1].setSbhjywid(null);

        //??????????????????????????????????????????
        if (! (poMlpxxxx_QR.getPcs() != null &&
               poMlpxxxx_QR.getPcs().equals(poMlpxxxx_QC.getPcs()))) {
          PoHJYW_QCCLXXB poQcclxx = new PoHJYW_QCCLXXB();
          poQcclxx.setQcclid( (Long) hjyw_qcclxxbDAO.getId());
          BeanUtils.copyProperties(poQcclxx, poRyxxNew);
          poQcclxx.setMlpnbid_q(poMlpxxxx_QC.getMlpnbid());
          poQcclxx.setSsxq_q(poMlpxxxx_QC.getSsxq());
          poQcclxx.setJlx_q(poMlpxxxx_QC.getJlx());
          poQcclxx.setMlph_q(poMlpxxxx_QC.getMlph());
          poQcclxx.setMlxz_q(poMlpxxxx_QC.getMlxz());
          poQcclxx.setPcs_q(poMlpxxxx_QC.getPcs());
          poQcclxx.setZrq_q(poMlpxxxx_QC.getZrq());
          poQcclxx.setXzjd_q(poMlpxxxx_QC.getXzjd());
          poQcclxx.setJcwh_q(poMlpxxxx_QC.getJcwh());
          poQcclxx.setPxh_q(poMlpxxxx_QC.getPxh());
          poQcclxx.setMlpnbid_h(poMlpxxxx_QR.getMlpnbid());
          poQcclxx.setSsxq_h(poMlpxxxx_QR.getSsxq());
          poQcclxx.setJlx_h(poMlpxxxx_QR.getJlx());
          poQcclxx.setMlph_h(poMlpxxxx_QR.getMlph());
          poQcclxx.setMlxz_h(poMlpxxxx_QR.getMlxz());
          poQcclxx.setPcs_h(poMlpxxxx_QR.getPcs());
          poQcclxx.setZrq_h(poMlpxxxx_QR.getZrq());
          poQcclxx.setXzjd_h(poMlpxxxx_QR.getXzjd());
          poQcclxx.setJcwh_h(poMlpxxxx_QR.getJcwh());
          poQcclxx.setPxh_h(poMlpxxxx_QR.getPxh());
          poQcclxx.setCxbz(PublicConstant.CXBZ_FCX);
          poQcclxx.setQqrynbid(poRyxxQC.getRynbid());
          poQcclxx.setQhrynbid(poRyxxNew.getRynbid());
          poQcclxx.setCzlx(HjConstant.QCCL_CZXL_RYQC);
          poQcclxx.setClbz(HjConstant.QCCL_CLBZ_WCL);
          poQcclxx.setHjywid(hjywid);
          super.create(poQcclxx);
        }

        //?????????????????????
        voZxhxx[i] = new VoZxhxx();
        voZxhxx[i].setNew_hhnbid(poRyxxNew.getHhnbid());
        voZxhxx[i].setOld_hhnbid(poRyxx.getHhnbid());

        //????????????????????????(??????)
        //??????????????????
        VoRybdxx voRybdxxQC = new VoRybdxx();
        voRybdxxQC.setBdbid(poZzbdxx.getZzbdid());
        voRybdxxQC.setBdfw(poZzbdxx.getBdfw());
        voRybdxxQC.setBdyy(poZzbdxx.getZzbdlb());
        voRybdxxQC.setBdrq(now.substring(0, 8));
        voRybdxxQC.setBdqhb(null);
        voRybdxxQC.setBdq_hhnbid(poRyxx.getHhnbid());
        voRybdxxQC.setBdh_hhnbid(poRyxxNew.getHhnbid());
        voRybdxxQC.setRynbid(poRyxxQC.getRynbid());
        voRybdxxQC.setRzjs(new Long( -1));
        voRybdxxQC.setHzjs(new Long(0));
        rybdxxList.add(voRybdxxQC);
        //??????????????????
        VoRybdxx voRybdxxQR = new VoRybdxx();
        voRybdxxQR.setBdbid(poZzbdxx.getZzbdid());
        voRybdxxQR.setBdfw(poZzbdxx.getBdfw());
        voRybdxxQR.setBdyy(poZzbdxx.getZzbdlb());
        voRybdxxQR.setBdrq(now.substring(0, 8));
        voRybdxxQR.setBdqhb(poRyxxQC.getHb());
        voRybdxxQR.setBdq_hhnbid(poRyxx.getHhnbid());
        voRybdxxQR.setBdh_hhnbid(poRyxxNew.getHhnbid());
        voRybdxxQR.setRynbid(poRyxxNew.getRynbid());
        voRybdxxQR.setRzjs(new Long(1));
        if (!bAdd && "1".equals(rh_lh)) {
          bAdd = true;
          voRybdxxQR.setHzjs(new Long(1));
        }
        else {
          voRybdxxQR.setHzjs(new Long(0));
        }
        rybdxxList.add(voRybdxxQR);

        //??????????????????
        if (voZzbdxx[i].getSpywid() != null) {
          PojoInfo  hjsp_hjspsqbDAO = DAOFactory.createHJSP_HJSPSQBDAO();
          PoHJSP_HJSPSQB poQrspxx = super.get(PoHJSP_HJSPSQB.class,
              voZzbdxx[i].getSpywid());
          poQrspxx.setHjywid(hjywid);
          poQrspxx.setRynbid(poRyxxNew.getRynbid());
          poQrspxx.setLsbz(HjConstant.LSBZ_YLS);
          super.update(poQrspxx);
        }
        else if (voZzbdxx[i].getSpsqzid() != null) {
          PojoInfo  hjsp_hjspzbDAO = DAOFactory.createHJSP_HJSPZBDAO();
          PoHJSP_HJSPZB poQrspzxx = super.get(PoHJSP_HJSPZB.class,voZzbdxx[
              i].getSpsqzid());
          poQrspzxx.setRynbid(poRyxxNew.getRynbid());
          super.update(poQrspzxx);
        }

        //??????????????????????????????
        voZzbdfhxx[i] = new VoZzbdfhxx();
        voZzbdfhxx[i].setZzbdid(poZzbdxx.getZzbdid());
        voZzbdfhxx[i].setRynbid(poRyxxNew.getRynbid());
        voZzbdfhxx[i].setRyid(poRyxxNew.getRyid());
        voZzbdfhxx[i].setOld_rynbid(poRyxx.getRynbid());
        voZzbdfhxx[i].setHhnbid(poRyxxNew.getHhnbid());
        voZzbdfhxx[i].setOld_hhnbid(poRyxx.getHhnbid());
        voZzbdfhxx[i].setGmsfhm(poRyxxNew.getGmsfhm());
        voZzbdfhxx[i].setXm(poRyxxNew.getXm());
        voZzbdfhxx[i].setYhzgx(poRyxxNew.getYhzgx());
      } //for (int i = 0; i < voZzbdxx.length; i++) {

      ////////////////////////////////////////
      //?????????????????????
      VoHcygxtzfhxx voh[] = this.adjustHCYGX(hjywid, voSbjbxx,
                                             voHcygxtzxxEx, now);
      if (voh != null) {
        for (int i = 0; i < voh.length; i++) {
          hcygxtzfhxxList.add(voh[i]);
        }
      }

      ////////////////////////////////////////
      //??????????????????
      if (voBggzxxEx != null) {
        VoBggzfhxxEx vobEx = this.saveBGGZXX(hjywid, voSbjbxx, voBggzxxEx,
                                             PublicConstant.GNBH_HJ_ZZBDYW, now);
        if (vobEx != null) {
          if (vobEx.getVoBggzfhxx() != null) {
            for (int a = 0; a < vobEx.getVoBggzfhxx().length; a++) {
              bggzfhxxList.add(vobEx.getVoBggzfhxx()[a]);
            }
          } //if(vobEx!=null)
          if (vobEx.getVoHcygxtzfhxx() != null) {
            for (int a = 0; a < vobEx.getVoHcygxtzfhxx().length; a++) {
              hcygxtzfhxxList.add(vobEx.getVoHcygxtzfhxx()[a]);
            }
          }

          //?????????????????????????????????????????????
          VoBgryfhxx voBgryfhxx[] = vobEx.getVoBgryfhxx();
          if (voBgryfhxx != null) {
            for (int a = 0; a < voBgryfhxx.length; a++) {
              if (!poHxx_QR.getHhnbid().equals(voBgryfhxx[a].getHhnbid())) {
                PoHJYW_QCCLXXB poQcclxx = new PoHJYW_QCCLXXB();
                PoHJXX_CZRKJBXXB poRyxxBGH = super.
                    get(PoHJXX_CZRKJBXXB.class, voBgryfhxx[a].getRynbid());
                PoHJXX_CZRKJBXXB poRyxxBGQ = super.
                    get(PoHJXX_CZRKJBXXB.class, voBgryfhxx[a].getOld_rynbid());
                PoHJXX_MLPXXXXB poMlpxxxxBGH = super.
                    get(PoHJXX_MLPXXXXB.class, poRyxxBGH.getMlpnbid());
                PoHJXX_MLPXXXXB poMlpxxxxBGQ = super.
                    get(PoHJXX_MLPXXXXB.class,poRyxxBGQ.getMlpnbid());
                poQcclxx.setQcclid( (Long) hjyw_qcclxxbDAO.getId());
                BeanUtils.copyProperties(poQcclxx, poRyxxBGH);
                poQcclxx.setQqrynbid(poRyxxBGQ.getRynbid());
                poQcclxx.setQhrynbid(poRyxxBGH.getRynbid());
                poQcclxx.setMlpnbid_q(poMlpxxxxBGQ.getMlpnbid());
                poQcclxx.setSsxq_q(poMlpxxxxBGQ.getSsxq());
                poQcclxx.setJlx_q(poMlpxxxxBGQ.getJlx());
                poQcclxx.setMlph_q(poMlpxxxxBGQ.getMlph());
                poQcclxx.setMlxz_q(poMlpxxxxBGQ.getMlxz());
                poQcclxx.setPcs_q(poMlpxxxxBGQ.getPcs());
                poQcclxx.setZrq_q(poMlpxxxxBGQ.getZrq());
                poQcclxx.setXzjd_q(poMlpxxxxBGQ.getXzjd());
                poQcclxx.setJcwh_q(poMlpxxxxBGQ.getJcwh());
                poQcclxx.setPxh_q(poMlpxxxxBGQ.getPxh());
                poQcclxx.setMlpnbid_h(poMlpxxxxBGH.getMlpnbid());
                poQcclxx.setSsxq_h(poMlpxxxxBGH.getSsxq());
                poQcclxx.setJlx_h(poMlpxxxxBGH.getJlx());
                poQcclxx.setMlph_h(poMlpxxxxBGH.getMlph());
                poQcclxx.setMlxz_h(poMlpxxxxBGH.getMlxz());
                poQcclxx.setPcs_h(poMlpxxxxBGH.getPcs());
                poQcclxx.setZrq_h(poMlpxxxxBGH.getZrq());
                poQcclxx.setXzjd_h(poMlpxxxxBGH.getXzjd());
                poQcclxx.setJcwh_h(poMlpxxxxBGH.getJcwh());
                poQcclxx.setPxh_h(poMlpxxxxBGH.getPxh());
                poQcclxx.setHjywid(hjywid);
                poQcclxx.setCzlx(HjConstant.QCCL_CZXL_RYBG);
                poQcclxx.setClbz(HjConstant.QCCL_CLBZ_WCL);
                super.create(poQcclxx);
              }
            } //for (int a = 0; a < voBgryfhxx.length; a++)
          } //if ( voBgryfhxx != null)
        } //if(vobEx!=null)
      } //if (voBggzxx != null)

      /////////////////////////////////////////
      //????????????????????????
      List zxhhnbidList = this.logoutH(hjywid, voZxhxx, voZzbdxx[0].getBdfw(),
                                       voZzbdxx[0].getZzbdlb(),
                                       HjConstant.YWNR_ZZBDQC, now);

      ///////////////////////////////////////////
      //?????????????????????
      if (zxhhnbidList != null && zxhhnbidList.size() > 0) {
        for (int i = 0; i < zxhhnbidList.size(); i++) {
          PoHJXX_HXXB oldHxx = super.get(PoHJXX_HXXB.class, (Long)
              zxhhnbidList.get(i));
          if (oldHxx == null) {
            continue;
          }

          //???????????????
          String strHQL = "from PoHJXX_HGLGXB where hhid=" + oldHxx.getHhid() +
              " or glhhid=" + oldHxx.getHhid();
          List glgxList = super.findAllByHQL(strHQL);
          if (glgxList != null && glgxList.size() > 0) {
            for (int a = 0; a < glgxList.size(); a++) {
              PoHJXX_HGLGXB po1 = (PoHJXX_HGLGXB) glgxList.get(a);
              PoHJXX_HGLGXB po2 = new PoHJXX_HGLGXB();
              BeanUtils.copyProperties(po2, po1);
              po2.setGlid( (Long) hjxx_hglgxbDAO.getId());
              if (po2.getHhid().equals(oldHxx.getHhid())) {
                po2.setHhid(poHxx_QR.getHhid());
              }
              if (po2.getGlhhid().equals(oldHxx.getHhid())) {
                po2.setGlhhid(poHxx_QR.getHhid());
              }
              po2.setZt(HjConstant.HGLZT_YWSC);
              po2.setJljlrid(this.getUserInfo().getYhid());
              po2.setJljlsj(now);
              super.create(po2);
            }
          }

          PoHJXX_HGLGXB poHglgx1 = new PoHJXX_HGLGXB();
          poHglgx1.setGlid( (Long) hjxx_hglgxbDAO.getId());
          poHglgx1.setHhid(poHxx_QR.getHhid());
          poHglgx1.setGlhhid(oldHxx.getHhid());
          poHglgx1.setGlgx(oldHxx.getHh() + " ???????????? " + poHxx_QR.getHh());
          poHglgx1.setJljlsj(now);
          poHglgx1.setJljlrid(this.getUserInfo().getYhid());
          poHglgx1.setZt(HjConstant.HGLZT_YWSC);
          super.create(poHglgx1);
          PoHJXX_HGLGXB poHglgx2 = new PoHJXX_HGLGXB();
          poHglgx2.setGlid( (Long) hjxx_hglgxbDAO.getId());
          poHglgx2.setHhid(oldHxx.getHhid());
          poHglgx2.setGlhhid(poHxx_QR.getHhid());
          poHglgx2.setGlgx(oldHxx.getHh() + " ???????????? " + poHxx_QR.getHh());
          poHglgx2.setJljlsj(now);
          poHglgx2.setJljlrid(this.getUserInfo().getYhid());
          poHglgx2.setZt(HjConstant.HGLZT_YWSC);
          super.create(poHglgx2);
        }
      }

      ///////////////////////////////////////////
      //??????????????????
      Map hhnbidMap = new HashMap();
      for (int h = 0; h < hcygxtzfhxxList.size(); h++) {
        VoHcygxtzfhxx vo = (VoHcygxtzfhxx) hcygxtzfhxxList.get(h);
        hhnbidMap.put(vo.getHhnbid(), null);
      }
      //???????????????????????????ID
      if (zxhhnbidList != null) {
        for (int h = 0; h < zxhhnbidList.size(); h++) {
          hhnbidMap.remove(zxhhnbidList.get(h));
        }
      }
      for (Iterator itr = hhnbidMap.keySet().iterator(); itr.hasNext(); ) {
        this.checkHCYXX( (Long) itr.next());
      }

      ////////////////////////////////////////
      //??????????????????
      this.saveCHXX(hjywid, chxxList, now);

      ////////////////////////////////////////
      //??????????????????????????????
      //rh_lh = "1"; //?????????????????????1-??????/2-?????????
      PoHJLS_HJYWLSB poHjywlsxx = this.saveHJYWLSXX(hjywid,
          PublicConstant.GNBH_HJ_ZZBDYW,
          zxhhnbidList != null && zxhhnbidList.size() > 0 ?
          PublicConstant.HJYWLS_YWLX_QH :
          PublicConstant.HJYWLS_YWLX_GR,
          voZzbdxx.length, voSbjbxx, now);

      //////////////////////////////////////////
      //????????????????????????(??????)
      if (zxhhnbidList != null && zxhhnbidList.size() > 0) {
        for (int a = 0; a < zxhhnbidList.size(); a++) {
          Long hhnbid = (Long) zxhhnbidList.get(a);
          for (int b = 0; b < rybdxxList.size(); b++) {
            VoRybdxx vo = (VoRybdxx) rybdxxList.get(b);
            if (hhnbid.equals(vo.getBdq_hhnbid())) {
              vo.setHzjs(new Long( -1));
              break;
            }
          }
        }
      }
      this.saveRYBDXX(rybdxxList, poHjywlsxx);

      ///////////////////////////////////////////
      //??????????????????????????????????????????????????????????????????????????????
      PoHJXX_CZRKJBXXB poHzxx = null;
      String strHQL = "from PoHJYW_ZZBDXXB where hjywid=" + hjywid;
      List zzbdxxList = super.findAllByHQL(strHQL);
      if (zzbdxxList != null && zzbdxxList.size() > 0) {
        for (int i = 0; i < zzbdxxList.size(); i++) {
          PoHJYW_ZZBDXXB poZzbdxx = (PoHJYW_ZZBDXXB) zzbdxxList.get(i);
          if (poHzxx == null) {
            strHQL = "from PoHJXX_CZRKJBXXB where yhzgx < '10' and ryzt='" +
                HjConstant.RYZT_ZC + "' and jlbz='" +
                PublicConstant.JLBZ_ZX + "' and cxbz='" +
                PublicConstant.CXBZ_FCX +
                "' and hhnbid =" + poZzbdxx.getHhnbid();
            List hzxxList = super.findAllByHQL(strHQL);
            if (hzxxList != null && hzxxList.size() > 0) {
              poHzxx = (PoHJXX_CZRKJBXXB) hzxxList.get(0);
            }
          }
          poZzbdxx.setHzgmsfhm(poHzxx != null ? poHzxx.getGmsfhm() : null);
          poZzbdxx.setHzxm(poHzxx != null ? poHzxx.getXm() : null);
          poZzbdxx.setYwlx(poHjywlsxx.getYwlx());
          poZzbdxx.setCzsm(poHjywlsxx.getCzsm());
          super.update(poZzbdxx);
        }
      }

      ///////////////////////////////////////////
      //????????????????????????????????????????????????????????????????????????
      strHQL = "from PoHJYW_HBBGXXB where hjywid=" + hjywid;
      List hbbgxxList = super.findAllByHQL(strHQL);
      if (hbbgxxList != null && hbbgxxList.size() > 0) {
        for (int i = 0; i < hbbgxxList.size(); i++) {
          PoHJYW_HBBGXXB poHbbgxx = (PoHJYW_HBBGXXB) hbbgxxList.get(i);
          poHbbgxx.setHzgmsfhm(poHzxx != null ? poHzxx.getGmsfhm() : null);
          poHbbgxx.setHzxm(poHzxx != null ? poHzxx.getXm() : null);
          poHbbgxx.setYwlx(poHjywlsxx.getYwlx());
          poHbbgxx.setCzsm(poHjywlsxx.getCzsm());
          super.update(poHbbgxx);
        }
      }

      ////////////////////////////////////
      //????????????????????????
      voZzbdywfhxx = new VoZzbdywfhxx();
      voZzbdywfhxx.setHjywid(hjywid);
      voZzbdywfhxx.setHhnbid(poHxx_QR.getHhnbid());
      voZzbdywfhxx.setMlpnbid(poMlpxxxx_QR.getMlpnbid());
      voZzbdywfhxx.setVoZzbdfhxx(voZzbdfhxx);
      voZzbdywfhxx.setVoBggzfhxx( (VoBggzfhxx[]) bggzfhxxList.toArray(new
          VoBggzfhxx[bggzfhxxList.size()]));
      voZzbdywfhxx.setVoHcygxtzfhxx( (VoHcygxtzfhxx[]) hcygxtzfhxxList.toArray(new
          VoHcygxtzfhxx[hcygxtzfhxxList.size()]));

      /////////////////////////////////////////////
      //????????????
    }
    catch (DAOException ex) {
      //????????????
      throw ex;
    }
    catch (ServiceException ex) {
      //????????????
      throw ex;
    }
    catch (InvocationTargetException ex) {
      //????????????
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }
    catch (IllegalAccessException ex) {
      //????????????
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }
    catch (Exception ex) {
      //????????????
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }

    return voZzbdywfhxx;
  }

  /**
   * ????????????????????????
   *
   * @param voSbjbxx - ??????????????????
   * @param voQczxxx[] - ??????????????????
   * @param voBggzxx[] - ??????????????????
   * @return VoQczxywfhxx
   * @roseuid 40515132005B
   */
  public VoQczxywfhxx processQczxyw(VoSbjbxx voSbjbxx, VoQczxxx voQczxxx[],
                                    VoBggzxxEx voBggzxxEx[]) throws
      ServiceException,
      DAOException {

    VoQczxywfhxx voQczxywfhxx = null;
    VoQczxfhxx voQczxfhxx[] = null;
    VoHcygxtzxxEx voHcygxtzxxEx[] = null;
    List bggzfhxxList = new ArrayList();
    List hcygxtzfhxxList = new ArrayList();
    List rybdxxList = new ArrayList();
    VoZxhxx voZxhxx[] = null;
    Long hjywid = null;
    String now = StringUtils.getServiceTime();
    PoXT_JWHXXB poJwhxxb = null;
    VoQyzbhhtxx qyzdyxx = null;
    String qyzdyxx_yhzgx = null;
    Long qyzdyxx_rynbid = null;

    if (voQczxxx == null || (voQczxxx != null && voQczxxx.length <= 0)) {
      return null;
    }

    try {
      PojoInfo  hjxx_czrkjbxxbDAO = DAOFactory.createHJXX_CZRKJBXXBDAO();
      PojoInfo  hjyw_qczxxxbDAO = DAOFactory.createHJYW_QCZXXXBDAO();
      PojoInfo  hjls_hjywlsbDAO = DAOFactory.createHJLS_HJYWLSBDAO();
      PojoInfo  hjxx_rhflxxbDAO = DAOFactory.createHJXX_RHFLXXBDAO();
      PojoInfo  hjyw_chclxxbDAO = DAOFactory.createHJYW_CHCLXXBDAO();
      PojoInfo  hjxx_mlpxxxxbDAO = DAOFactory.createHJXX_MLPXXXXBDAO();
      PojoInfo  hjxx_hxxbDAO = DAOFactory.createHJXX_HXXBDAO();
      PojoInfo  xt_jwhxxbDAO = DAOFactory.createXT_JWHXXBDAO();

      /////////////////////////////////////
      //??????????????????ID
      hjywid = (Long) hjls_hjywlsbDAO.getId();
      
      ///////////////////////////////////////////
      //????????????
      Map map = new HashMap();
      String qyz = null, lscxdm = null;
      PoHZ_ZJ_SB sb = null;

      if(voSbjbxx!=null && voSbjbxx.getHzywid()!=null){
        //List list = super.findEntities("from PoHZ_ZJ_SB a where a.id=" + voSbjbxx.getHzywid());
        List list = super.findEntities("from PoHZ_ZJ_SB a where a.id in (" + voSbjbxx.getHzywid()+") ");
        Date date = new Date();
        for(int index=0;index<list.size();index++) {
        //if(list.size()>0){
          sb = (PoHZ_ZJ_SB)list.get(index);
          if(sb.getKdqqy_qrdqbm()!=null && !sb.getKdqqy_qrdqbm().trim().equals("") && sb.getClbs().equals("0") ){
            //????????????????????????????????????????????????
            qyz = KDSActionProxy.getXlh("","qyz");
            if(qyz==null || qyz.trim().equals(""))
              throw new RuntimeException("?????????????????????????????????????????????????????????");

            //??????????????????
            PoHJXX_CZRKJBXXB poRyxx = super.get(PoHJXX_CZRKJBXXB.class, voQczxxx[0].getRynbid());
            if (poRyxx == null) {
              throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "???????????????????????????????????????????????????????????????", null);
            }
            //???????????????
            PoHJXX_HXXB poHxx = super.get(PoHJXX_HXXB.class,poRyxx.getHhnbid());
            if (poHxx == null) {
              throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "?????????????????????????????????????????????????????????????????????????????????", null);
            }
            //???????????????
            PoHJXX_MLPXXXXB poMlpxxxx = super.get(PoHJXX_MLPXXXXB.class,poRyxx.getMlpnbid());
            if (poMlpxxxx == null) {
              throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "?????????????????????????????????????????????????????????????????????????????????", null);
            }
            poJwhxxb = (PoXT_JWHXXB) super.get(PoXT_JWHXXB.class,poMlpxxxx.getJcwh());
            if (poJwhxxb == null) {
              throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "??????????????????????????????????????????????????????", null);
            }

            lscxdm = poJwhxxb.getCxfldm();
          }
          if(sb.getPch()!=null && !sb.getPch().equals("")){
            //???????????????????????????
            System.out.println("???????????????????????????");
            sb.setClbs("1");
            sb.setClsj(CommonUtil.getTimestamp(date));
            sb.setKdqqy_qyzbh(qyz);
            sb.setKdqqy_lscxfldm(lscxdm);
            sb.setHjywid(hjywid);
            sb.setBlrsfz(this.getUser().getGmsfhm());
            super.update(sb);
            String kzz = super.getXTKZCS("10031");
            String kzz1 = super.getXTKZCS("10032");
            String kzz2 = super.getXTKZCS("10037");
            //1.??????????????????   2.wx_code????????????    add by zjm 20191106 
              if(kzz.equals("1")) {//????????????1??????????????????????????????????????????HZPT_RECEIVESTATE????????????
            	  if(CommonUtil.isNotEmpty(sb.getWx_code())) {
            		  updateHzptReceiveState(kzz,sb);
            	  }
              }
              if(kzz1.equals("1")) {//????????????1??????????????????????????????????????????HZPT_RECEIVESTATE????????????
            	  if(CommonUtil.isNotEmpty(sb.getWx_code())) {
            		  insertEms(kzz1,sb);
            	  }
              }
              if(kzz2.equals("1")) {//add by zjm 20200710 ????????????2????????????????????????????????????
            	autoInsertHzjsbs(kzz2,sb);
              }
            if(sb.getKdqqy_zqzbh()!=null && !sb.getKdqqy_zqzbh().trim().equals("")){
              map.put(sb.getBsqrsfz(), sb.getKdqqy_zqzbh());
            }
//            list = super.findEntities("from PoHZ_ZJ_SB a where a.pch='" + sb.getPch() + "'");
//            int pccount = list.size();
//            for(int index=0;index<pccount;index++){
//                 PoHZ_ZJ_SB sbx = (PoHZ_ZJ_SB)list.get(index);
//                 sbx.setClbs("1");
//                 sbx.setClsj(CommonUtil.getTimestamp(null));
//                 sbx.setKdqqy_qyzbh(qyz);
//                 sbx.setKdqqy_lscxfldm(lscxdm);
//                 sbx.setHjywid(hjywid);
//                 sbx.setBlrsfz(this.getUser().getGmsfhm());
//                 super.update(sbx);
//                 String kzz = super.getXTKZCS("10031");
//                 updateHzptReceiveState(kzz,sbx);
//                 if(sbx.getKdqqy_zqzbh()!=null && !sbx.getKdqqy_zqzbh().trim().equals("")){
//                   map.put(sbx.getBsqrsfz(), sbx.getKdqqy_zqzbh());
//                 }
//             }
          }else{
            if(sb.getKdqqy_zqzbh()!=null && !sb.getKdqqy_zqzbh().trim().equals("")){
              map.put(sb.getBsqrsfz(), sb.getKdqqy_zqzbh());
            }

             sb.setClbs("1");
             sb.setClsj(CommonUtil.getTimestamp(date));
             sb.setHjywid(hjywid);
             sb.setBlrsfz(this.getUser().getGmsfhm());
             sb.setKdqqy_qyzbh(qyz);
             sb.setKdqqy_lscxfldm(lscxdm);
             super.update(sb);
             String kzz2 = super.getXTKZCS("10037");
             if(kzz2.equals("1")) {//add by zjm 20200710 ????????????2????????????????????????????????????
             	autoInsertHzjsbs(kzz2,sb);
               }
          }
        }
      }

    

      //////////////////////////////////////////
      //????????????????????????
      voQczxfhxx = new VoQczxfhxx[voQczxxx.length];
      voZxhxx = new VoZxhxx[voQczxxx.length];
      voHcygxtzxxEx = new VoHcygxtzxxEx[voQczxxx.length];
      for (int i = 0; i < voQczxxx.length; i++) {
        //??????????????????
        PoHJXX_CZRKJBXXB poRyxx = super.get(PoHJXX_CZRKJBXXB.class,
            voQczxxx[i].getRynbid());
        if (poRyxx == null) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "???????????????????????????????????????????????????????????????", null);
        }
        //???????????????????????????
        this.checkRXX(poRyxx, hjxx_czrkjbxxbDAO, "??????????????????");
        //??????????????????
        if (!HjConstant.RYSDZT_ZC.equals(poRyxx.getRysdzt())) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "??????????????????????????????(?????????)????????????????????????????????????", null);
        }
        //????????????
        if (!HjConstant.RYZT_ZC.equals(poRyxx.getRyzt())) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "????????????????????????????????????????????????????????????", null);
        }
        //???????????????
        PoHJXX_HXXB poHxx = super.get(PoHJXX_HXXB.class, poRyxx.getHhnbid());
        if (poHxx == null) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "?????????????????????????????????????????????????????????????????????????????????", null);
        }
        //???????????????
        PoHJXX_MLPXXXXB poMlpxxxx = super.get(PoHJXX_MLPXXXXB.class,
            poRyxx.
            getMlpnbid());
        if (poMlpxxxx == null) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "?????????????????????????????????????????????????????????????????????????????????", null);
        }
        poJwhxxb = (PoXT_JWHXXB) super.get(PoXT_JWHXXB.class,poMlpxxxx.getJcwh());
        if (poJwhxxb == null) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "??????????????????????????????????????????????????????", null);
        }

        //????????????????????????
        if (PublicConstant.XTKZCS_QY.equals(this.getXTKZCS(PublicConstant.
            XTKZCS_SHQYZDQYPD))) {
          if (this.checkZZBD(voQczxxx[i].getQwdssxq(), poMlpxxxx.getSsxq())) {
            throw new ServiceException(WSErrCode.ERR_SERVICE_DATANOTFULL,
                                       "??????????????????????????????????????????????????????????????????????????????????????????", null);
          }
        }
        //??????????????????
        List sjfwList = new ArrayList();
        VoXtsjfw voXtsjfw = new VoXtsjfw();
        voXtsjfw.setSjfw(poMlpxxxx.getJcwh());
        voXtsjfw.setSjfwbz(PublicConstant.XT_QX_JWH);
        sjfwList.add(voXtsjfw);
        boolean bLimit = false;
        bLimit = XtywqxServiceImpl.VerifyDataRange(this.getUserInfo().getYhid().
            toString(),
            PublicConstant.GNBH_HJ_QCZXYW, sjfwList);
        if (!bLimit) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLIMIT,
        		  CommonUtil.getSjfwError(sjfwList)  + "????????????????????????????????????", null);
        }
        //?????????????????????????????????
        poRyxx.setJssj(now);
        poRyxx.setCchjywid(hjywid);
        poRyxx.setJlbz(PublicConstant.JLBZ_LS);
        super.update(poRyxx);
        //??????????????????????????????
        PoHJXX_CZRKJBXXB poRyxxNew = new PoHJXX_CZRKJBXXB();
        BeanUtils.copyProperties(poRyxxNew, poRyxx);
        BeanUtils.copyProperties(poRyxxNew, voQczxxx[i]);
        poRyxxNew.setQczxlb(voQczxxx[i].getQclb());
        poRyxxNew.setRynbid( (Long) hjxx_czrkjbxxbDAO.getId());
        //??????poRyxxNew??????????????????begin
        if (voBggzxxEx != null) {
          for (int k = 0; k < voBggzxxEx.length; k++) {
            if (voQczxxx[i].getRynbid().equals(voBggzxxEx[k].getRynbid())) {
              voBggzxxEx[k].setFlag(0); ////?????????????????????????????????????????????????????????(0-?????????/1-??????)
              VoBggzfhxxEx vobEx = this.disposalBggzxx(hjywid, voSbjbxx,
                  voBggzxxEx[k], poRyxxNew, PublicConstant.GNBH_HJ_QCZXYW, now);
              if (vobEx != null) {
                if (vobEx.getVoBggzfhxx() != null) {
                  for (int a = 0; a < vobEx.getVoBggzfhxx().length; a++) {
                    bggzfhxxList.add(vobEx.getVoBggzfhxx()[a]);
                  }
                } //if(vobEx!=null)
                if (vobEx.getVoHcygxtzfhxx() != null) {
                  for (int a = 0; a < vobEx.getVoHcygxtzfhxx().length; a++) {
                    hcygxtzfhxxList.add(vobEx.getVoHcygxtzfhxx()[a]);
                  }
                }
              } //if(vobEx!=null)
            } //if(voHbbgxx[i].getRynbid().equals(voBggzxx[k].getRynbid()))
          } //for(int k=0;k<voBggzxx.length;k++)
        }
        //end
        //????????????_????????????
        if (HjConstant.QCZXLB_CGDJ.equals(voQczxxx[i].getQclb()) ||
            HjConstant.QCZXLB_QWGA.equals(voQczxxx[i].getQclb()) ||
            HjConstant.QCZXLB_QWTW.equals(voQczxxx[i].getQclb()) ||
            HjConstant.QCZXLB_GZRYCG.equals(voQczxxx[i].getQclb()) ||
            HjConstant.QCZXLB_LXRYCG.equals(voQczxxx[i].getQclb())) {//add by zjm 20190411 ??????0482 0483 ??????????????????
          poRyxxNew.setRyzt(HjConstant.RYZT_CGJDJ);
        }
        //????????????_?????????
        else if (HjConstant.QCZXLB_CJFBY.equals(voQczxxx[i].getQclb())) {
          poRyxxNew.setRyzt(HjConstant.RYZT_FBY);
        }
        //?????? ???????????????6 add  by zjm 20190726 
        else if (HjConstant.QCZXLB_SZ.equals(voQczxxx[i].getQclb())) {
            poRyxxNew.setRyzt(HjConstant.RYZT_SZ);
        }
        //????????????_????????????
        else {
          poRyxxNew.setRyzt(HjConstant.RYZT_QC);
        }
        poRyxxNew.setQysj(now);
        poRyxxNew.setJssj(null);
        poRyxxNew.setXxqysj(now);//add by hb 20061027
        poRyxxNew.setYwnr(HjConstant.YWNR_QCZX);
        poRyxxNew.setCchjywid(null);
        poRyxxNew.setCjhjywid(hjywid);
        poRyxxNew.setJlbz(PublicConstant.JLBZ_ZX);
        super.create(poRyxxNew);

        if(qyzdyxx_yhzgx==null){
          qyzdyxx_yhzgx = poRyxxNew.getYhzgx();
          qyzdyxx_rynbid = poRyxxNew.getRynbid();
        }

        //??????????????????
        VoRhdxx voRhdxx = new VoRhdxx(poRyxxNew, poHxx, poMlpxxxx,
                                      BaseContext.getUser());
        VoXtywxz voLimit = XtywqxServiceImpl.VerifyBusinessLimit(PublicConstant.
            GNBH_HJ_QCZXYW, voRhdxx);
        if (voLimit.getLimitflag()) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLIMIT,
                                     "??????????????????????????????????????????" + voLimit.getLimitinfo(), null);
        }
        voRhdxx = null;

        //????????????????????????
        PoHJYW_QCZXXXB poQczxxx = new PoHJYW_QCZXXXB();
        if (voSbjbxx != null) {
          BeanUtils.copyProperties(poQczxxx, voSbjbxx);
        }
        BeanUtils.copyProperties(poQczxxx, voQczxxx[i]);
        BeanUtils.copyProperties(poQczxxx, poRyxxNew);
        poQczxxx.setHhid(poHxx.getHhid());
        poQczxxx.setMlpid(poMlpxxxx.getMlpid());
        poQczxxx.setQczxid( (Long) hjyw_qczxxxbDAO.getId());
        poQczxxx.setCxbz(PublicConstant.CXBZ_FCX);
        poQczxxx.setHjywid(hjywid);
        poQczxxx.setSldw(this.getUserInfo().getDwdm());
        poQczxxx.setSlrid(this.getUserInfo().getYhid());
        poQczxxx.setSlsj(now);

        //???????????????????????????
        if(sb!=null){
          if(sb.getKdqqy_qrdqbm()!=null && !sb.getKdqqy_qrdqbm().trim().equals("")){
            poQczxxx.setKdqqy_qrdqbm(sb.getKdqqy_qrdqbm());
          }
        }

        //???????????????????????????
        if(qyz!=null)
          poQczxxx.setQyzbh(qyz);

        //???????????????????????????
        if(map.containsKey(poQczxxx.getGmsfhm())){
          poQczxxx.setZqzbh((String)map.get(poQczxxx.getGmsfhm()));
        }

        //?????????????????????????????????
        poQczxxx.setCxfldm(poJwhxxb.getCxfldm());
        super.create(poQczxxx);

        PoHJXX_CXSXBGB log = new PoHJXX_CXSXBGB();
        //????????????????????????????????????????????????????????????
        log.setBghcxsx(poQczxxx.getCxfldm());
        log.setBgqcxsx(poJwhxxb.getCxfldm());
        log.setCjsj(now);
        log.setSldw(poJwhxxb.getDwdm());
        log.setBgqdw(poJwhxxb.getDwdm());
        log.setRynbid(poQczxxx.getRynbid());
        log.setHjywid(poQczxxx.getHjywid());
        log.setBgyy(poQczxxx.getQclb());
        log.setYwlb("qwts");
        log.setBz("????????????");
        log.setRkbj("0");
        log.setSsxq(poQczxxx.getSsxq());
        log.setJwhdm(poJwhxxb.getDm());
        log.setRysl(new Long(1));
        super.create(log);

        //??????????????????????????????????????????????????????
        voHcygxtzxxEx[i] = new VoHcygxtzxxEx();
        voHcygxtzxxEx[i].setFlag(0);
        voHcygxtzxxEx[i].setHcybdlx(HjConstant.HCYBDLX_LH);
        voHcygxtzxxEx[i].setHcybdlb(voQczxxx[i].getQclb());
        voHcygxtzxxEx[i].setHhnbid(poRyxxNew.getHhnbid());
        voHcygxtzxxEx[i].setRynbid(poRyxx.getRynbid());
        voHcygxtzxxEx[i].setNew_rynbid(poRyxxNew.getRynbid());
        voHcygxtzxxEx[i].setRyid(poRyxxNew.getRyid());
        voHcygxtzxxEx[i].setGmsfhm(poRyxxNew.getGmsfhm());
        voHcygxtzxxEx[i].setXm(poRyxxNew.getXm());
        voHcygxtzxxEx[i].setOld_yhzgx(null);
        voHcygxtzxxEx[i].setYhzgx(poRyxxNew.getYhzgx());
        voHcygxtzxxEx[i].setSbhjywid(null);

        //?????????????????????
        voZxhxx[i] = new VoZxhxx();
        voZxhxx[i].setNew_hhnbid(null);
        voZxhxx[i].setOld_hhnbid(poRyxx.getHhnbid());

        //????????????????????????
        String strHQL = new String();
        strHQL = "from PoHJXX_RHFLXXB as HJXX_RHFLXXB where rynbid=" +
            voQczxxx[i].getRynbid().toString();
        List rhflxxList = super.findAllByHQL(strHQL);
        if (rhflxxList != null) {
          for (int j = 0; j < rhflxxList.size(); j++) {
            PoHJXX_RHFLXXB poRhflxx = (PoHJXX_RHFLXXB) rhflxxList.get(j);
            poRhflxx.setCchjywid(hjywid);
            poRhflxx.setRhflzt(HjConstant.RHFLZT_HJQC);
            super.update(poRhflxx);
          }
        }

        //??????????????????
        strHQL = "from PoHJYW_CHCLXXB as HJYW_CHCLXXB where clfs='" +
            HjConstant.CHCLFS_WCL + "' and chsfhm='" + poRyxx.getGmsfhm() +
            "' and cxbz='" + PublicConstant.CXBZ_FCX + "' ";
        List chxxList = super.findAllByHQL(strHQL);
        if (chxxList != null) {
          for (int j = 0; j < chxxList.size(); j++) {
            PoHJYW_CHCLXXB poChclxx = (PoHJYW_CHCLXXB) chxxList.get(j);
            //????????????
            if (poRyxx.getRyid().equals(poChclxx.getRyid())) {
              poChclxx.setCxbz(PublicConstant.CXBZ_FCX);
              poChclxx.setClfs(HjConstant.CHCLFS_BRQC);
              poChclxx.setClhjywid(hjywid);
              super.update(poChclxx);
            }
            //??????????????????
            if (poRyxx.getRyid().equals(poChclxx.getBchryid())) {
              poChclxx.setClfs(HjConstant.CHCLFS_DFQC);
              poChclxx.setClhjywid(hjywid);
              super.update(poChclxx);
            }
          } //for (int j = 0; j < chxxList.size(); j++)
        }

        //????????????????????????(??????)
        VoRybdxx voRybdxx = new VoRybdxx();
        voRybdxx.setBdbid(poQczxxx.getQczxid());
        voRybdxx.setBdfw(poQczxxx.getBdfw());
        voRybdxx.setBdyy(poQczxxx.getQclb());
        voRybdxx.setQwdssxq(poQczxxx.getQwdssxq());
        voRybdxx.setQwdxz(poQczxxx.getQwdxz());
        voRybdxx.setBdrq(now.substring(0, 8)); //voRybdxx.setBdrq(poQczxxx.getQcrq());
        voRybdxx.setBdqhb(null);
        voRybdxx.setBdq_hhnbid(poRyxx.getHhnbid());
        voRybdxx.setBdh_hhnbid(null);
        voRybdxx.setRynbid(poRyxxNew.getRynbid());
        voRybdxx.setRzjs(new Long( -1));
        voRybdxx.setHzjs(new Long(0));
        rybdxxList.add(voRybdxx);

        //??????????????????????????????
        voQczxfhxx[i] = new VoQczxfhxx();
        voQczxfhxx[i].setRynbid(poRyxxNew.getRynbid());
        voQczxfhxx[i].setHhnbid(poRyxxNew.getHhnbid());
        voQczxfhxx[i].setRyid(poRyxxNew.getRyid());
        voQczxfhxx[i].setOld_rynbid(poRyxx.getRynbid());
        voQczxfhxx[i].setQczxid(poQczxxx.getQczxid());
        voQczxfhxx[i].setGmsfhm(poRyxxNew.getGmsfhm());
        voQczxfhxx[i].setXm(poRyxxNew.getXm());
        voQczxfhxx[i].setYhzgx(poRyxxNew.getYhzgx());
      }

      ////////////////////////////////////////
      //?????????????????????
      VoHcygxtzfhxx voh[] = this.adjustHCYGX(hjywid, voSbjbxx,
                                             voHcygxtzxxEx, now);
      if (voh != null) {
        for (int i = 0; i < voh.length; i++) {
          hcygxtzfhxxList.add(voh[i]);
        }
      }

      ////////////////////////////////////////
      //??????????????????
      if (voBggzxxEx != null) {
        VoBggzfhxxEx vobEx = this.saveBGGZXX(hjywid, voSbjbxx, voBggzxxEx,
                                             PublicConstant.GNBH_HJ_QCZXYW, now);
        if (vobEx != null) {
          if (vobEx.getVoBggzfhxx() != null) {
            for (int a = 0; a < vobEx.getVoBggzfhxx().length; a++) {
              bggzfhxxList.add(vobEx.getVoBggzfhxx()[a]);
            }
          } //if(vobEx!=null)
          if (vobEx.getVoHcygxtzfhxx() != null) {
            for (int a = 0; a < vobEx.getVoHcygxtzfhxx().length; a++) {
              hcygxtzfhxxList.add(vobEx.getVoHcygxtzfhxx()[a]);
            }
          }
        } //if(vobEx!=null)
      }

      ///////////////////////////////////////////
      //????????????????????????
      List zxhhnbidList = this.logoutH(hjywid, voZxhxx, voQczxxx[0].getBdfw(),
                                       voQczxxx[0].getQclb(),
                                       HjConstant.YWNR_QCZX, now);

      ///////////////////////////////
      //??????????????????
      Map hhnbidMap = new HashMap();
      for (int h = 0; h < hcygxtzfhxxList.size(); h++) {
        VoHcygxtzfhxx vo = (VoHcygxtzfhxx) hcygxtzfhxxList.get(h);
        hhnbidMap.put(vo.getHhnbid(), null);
      }
      //???????????????????????????ID
      if (zxhhnbidList != null) {
        for (int h = 0; h < zxhhnbidList.size(); h++) {
          hhnbidMap.remove(zxhhnbidList.get(h));
        }
      }
      for (Iterator itr = hhnbidMap.keySet().iterator(); itr.hasNext(); ) {
        this.checkHCYXX( (Long) itr.next());
      }

      ////////////////////////////////////////
      //??????????????????????????????
      PoHJLS_HJYWLSB poHjywlsxx = this.saveHJYWLSXX(hjywid,
          PublicConstant.GNBH_HJ_QCZXYW,
          zxhhnbidList != null && zxhhnbidList.size() > 0 ?
          PublicConstant.HJYWLS_YWLX_QH :
          PublicConstant.HJYWLS_YWLX_GR,
          voQczxxx.length, voSbjbxx, now);

      //???????????????????????????????????????
      if(qyz!=null){
          if(qyzdyxx==null){
            //qyz??????????????????????????????????????????
            if("0123456789".indexOf(qyz.substring(0,1))<0){
              qyz = qyz.substring(1);
            }

            qyzdyxx = new VoQyzbhhtxx();
            qyzdyxx.setDysx(new Long(1)); //1 ????????????
            qyzdyxx.setHjywid(poHjywlsxx.getHjywid());
            qyzdyxx.setQfrq(StringUtils.formateDate());
            qyzdyxx.setQyzbh(qyz);
            qyzdyxx.setRynbid(qyzdyxx_rynbid);
            qyzdyxx.setYczrgx(qyzdyxx_yhzgx);
            qyzdyxx.setYxqxjzrq("20300101");
            qyzdyxx.setYznf(StringUtils.formateDate().substring(0,4));
            VoQyzbhhtxx[] voQyzbhhtxx = new VoQyzbhhtxx[]{qyzdyxx};
            processQyzbhhtyw(voQyzbhhtxx);
        }
      }

      //////////////////////////////////////////
      //????????????????????????(??????)
      if (zxhhnbidList != null && zxhhnbidList.size() > 0) {
        for (int a = 0; a < zxhhnbidList.size(); a++) {
          Long hhnbid = (Long) zxhhnbidList.get(a);
          for (int b = 0; b < rybdxxList.size(); b++) {
            VoRybdxx vo = (VoRybdxx) rybdxxList.get(b);
            if (hhnbid.equals(vo.getBdq_hhnbid())) {
              vo.setHzjs(new Long( -1));
              break;
            }
          }
        }
      }
      this.saveRYBDXX(rybdxxList, poHjywlsxx);

      ///////////////////////////////////////////
      //??????????????????????????????????????????????????????????????????????????????
      PoHJXX_CZRKJBXXB poHzxx = null;
      String strHQL = "from PoHJYW_QCZXXXB where hjywid=" + hjywid;
      List qczxxxList =super.findAllByHQL(strHQL);
      if (qczxxxList != null && qczxxxList.size() > 0) {
        for (int i = 0; i < qczxxxList.size(); i++) {
          PoHJYW_QCZXXXB poQczxxx = (PoHJYW_QCZXXXB) qczxxxList.get(i);
          if (poHzxx == null) {
            strHQL = "from PoHJXX_CZRKJBXXB where yhzgx < '10' and jlbz='" +
                PublicConstant.JLBZ_ZX + "' and cxbz='" +
                PublicConstant.CXBZ_FCX +
                "' and hhnbid =" + poQczxxx.getHhnbid();
            List hzxxList = super.findAllByHQL(strHQL);
            poHzxx = this.findHZXX(hzxxList);
          }
          poQczxxx.setHzgmsfhm(poHzxx != null ? poHzxx.getGmsfhm() : null);
          poQczxxx.setHzxm(poHzxx != null ? poHzxx.getXm() : null);
          poQczxxx.setYwlx(poHjywlsxx.getYwlx());
          poQczxxx.setCzsm(poHjywlsxx.getCzsm());
          super.update(poQczxxx);
        }
      }

      ////////////////////////////////////
      //??????????????????
      voQczxywfhxx = new VoQczxywfhxx();
      voQczxywfhxx.setHjywid(hjywid);
      voQczxywfhxx.setVoQczxfhxx(voQczxfhxx);
      voQczxywfhxx.setVoBggzfhxx( (VoBggzfhxx[]) bggzfhxxList.toArray(new
          VoBggzfhxx[bggzfhxxList.size()]));
      voQczxywfhxx.setVoHcygxtzfhxx( (VoHcygxtzfhxx[]) hcygxtzfhxxList.toArray(new
          VoHcygxtzfhxx[hcygxtzfhxxList.size()]));

      ///////////////////////////////////////////
      //????????????
    }
    catch (DAOException ex) {
      //????????????
      throw ex;
    }
    catch (ServiceException ex) {
      //????????????
      throw ex;
    }
    catch (InvocationTargetException ex) {
      //????????????
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }
    catch (IllegalAccessException ex) {
      //????????????
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }
    catch (Exception ex) {
      //????????????
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }

    return voQczxywfhxx;
  }

/**
   * ????????????????????????
   *
   * @param voSbjbxx - ??????????????????
   * @param voHjscxx[] - ??????????????????
   * @param voBggzxx[] - ??????????????????
   * @return VoHjscywfhxx
   */
  public VoHjscywfhxx processHjscyw(VoSbjbxx voSbjbxx, VoHjscxx voHjscxx[],
                                    VoBggzxxEx voBggzxxEx[]) throws
      ServiceException,
      DAOException {

    VoHjscywfhxx voHjscywfhxx = null;
    VoHjscfhxx voHjscfhxx[] = null;
    VoHcygxtzxxEx voHcygxtzxxEx[] = null;
    List bggzfhxxList = new ArrayList();
    List hcygxtzfhxxList = new ArrayList();
    List rybdxxList = new ArrayList();
    VoZxhxx voZxhxx[] = null;
    Long hjywid = null;
    String now = StringUtils.getServiceTime();
    PoXT_JWHXXB poJwhxxb = null;
    if (voHjscxx == null || (voHjscxx != null && voHjscxx.length <= 0)) {
      return null;
    }

    try {
      PojoInfo  hjxx_czrkjbxxbDAO = DAOFactory.createHJXX_CZRKJBXXBDAO();
      PojoInfo  hjyw_hjscxxbDAO = DAOFactory.createHJYW_HJSCXXBDAO();
      PojoInfo  hjls_hjywlsbDAO = DAOFactory.createHJLS_HJYWLSBDAO();
      PojoInfo  hjxx_rhflxxbDAO = DAOFactory.createHJXX_RHFLXXBDAO();
      PojoInfo  hjyw_chclxxbDAO = DAOFactory.createHJYW_CHCLXXBDAO();
      PojoInfo  hjxx_mlpxxxxbDAO = DAOFactory.createHJXX_MLPXXXXBDAO();
      PojoInfo  hjxx_hxxbDAO = DAOFactory.createHJXX_HXXBDAO();
      PojoInfo  xt_jwhxxbDAO = DAOFactory.createXT_JWHXXBDAO();
      ///////////////////////////////////////////
      //????????????
      /////////////////////////////////////
      //??????????????????ID
      hjywid = (Long) hjls_hjywlsbDAO.getId();
      //????????????
      if(voSbjbxx!=null && voSbjbxx.getHzywid()!=null){
    	//modify by zjm 20190716 ?????????????????????????????????????????????
    	updateHzyw(voSbjbxx.getHzywid(),hjywid,0l);
        //List list = super.findAllByHQL("from PoHZ_ZJ_SB a where a.id=" + voSbjbxx.getHzywid());
//        if(list.size()>0){
//          PoHZ_ZJ_SB sb = (PoHZ_ZJ_SB)list.get(0);
//          sb.setClbs("1");
//          sb.setClsj(new java.sql.Timestamp(new Date().getTime()));
//          super.update(sb);
//        }
      }

      //////////////////////////////////////////
      //????????????????????????
      voHcygxtzxxEx = new VoHcygxtzxxEx[voHjscxx.length];
      voHjscfhxx = new VoHjscfhxx[voHjscxx.length];
      voZxhxx = new VoZxhxx[voHjscxx.length];
      for (int i = 0; i < voHjscxx.length; i++) {
        //??????????????????
        PoHJXX_CZRKJBXXB poRyxx  = super.get(PoHJXX_CZRKJBXXB.class,
            voHjscxx[i].getRynbid());
        if (poRyxx == null) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "?????????????????????????????????????????????????????????????????????", null);
        }
        //???????????????????????????
        this.checkRXX(poRyxx, hjxx_czrkjbxxbDAO, "??????????????????");
        //??????????????????
        if ( (voHjscxx[i].getSpywid() == null &&
              !HjConstant.RYSDZT_ZC.equals(poRyxx.getRysdzt())) ||
            (voHjscxx[i].getSpywid() != null &&
             !HjConstant.RYSDZT_HJSCSP.equals(poRyxx.getRysdzt()))) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "??????????????????????????????(?????????)????????????????????????????????????", null);
        }
        //????????????
        if (!HjConstant.RYZT_ZC.equals(poRyxx.getRyzt())) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "????????????????????????????????????????????????????????????", null);
        }
        //???????????????
        PoHJXX_HXXB poHxx  = super.get(PoHJXX_HXXB.class,poRyxx.getHhnbid());
        if (poHxx == null) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "?????????????????????????????????????????????????????????????????????????????????", null);
        }
        //?????????????????????
        PoHJXX_MLPXXXXB poMlpxxxx  = super.get(PoHJXX_MLPXXXXB.class,
            poRyxx.getMlpnbid());
        if (poMlpxxxx == null) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "?????????????????????????????????????????????????????????????????????????????????", null);
        }

        poJwhxxb  = super.get(PoXT_JWHXXB.class,poMlpxxxx.getJcwh());
        if (poJwhxxb == null) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "?????????????????????????????????????????????????????????", null);
        }

        //??????????????????
        List sjfwList = new ArrayList();
        VoXtsjfw voXtsjfw = new VoXtsjfw();
        voXtsjfw.setSjfw(poMlpxxxx.getJcwh());
        voXtsjfw.setSjfwbz(PublicConstant.XT_QX_JWH);
        sjfwList.add(voXtsjfw);
        boolean bLimit = false;
        bLimit = XtywqxServiceImpl.VerifyDataRange(this.getUserInfo().getYhid().
            toString(),
            PublicConstant.GNBH_HJ_HJSCYW, sjfwList);
        if (!bLimit) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLIMIT,
        		  CommonUtil.getSjfwError(sjfwList)  + "????????????????????????????????????", null);
        }

        //??????????????????????????????
        poRyxx.setRysdzt(HjConstant.RYSDZT_ZC);
        poRyxx.setJssj(now);
        poRyxx.setCchjywid(hjywid);
        poRyxx.setJlbz(PublicConstant.JLBZ_LS);
       super.update(poRyxx);
        //???????????????????????????
        PoHJXX_CZRKJBXXB poRyxxNew = new PoHJXX_CZRKJBXXB();
        BeanUtils.copyProperties(poRyxxNew, poRyxx);
        BeanUtils.copyProperties(poRyxxNew, voHjscxx[i]);
        poRyxxNew.setRynbid( (Long) hjxx_czrkjbxxbDAO.getId());

        String bz = "";
        //??????poRyxxNew??????????????????begin
        if (voBggzxxEx != null) {
          for (int k = 0; k < voBggzxxEx.length; k++) {
            if (voHjscxx[i].getRynbid().equals(voBggzxxEx[k].getRynbid())) {
              voBggzxxEx[k].setFlag(0); ////?????????????????????????????????????????????????????????(0-?????????/1-??????)
              VoBggzfhxxEx vobEx = this.disposalBggzxx(hjywid, voSbjbxx,
                  voBggzxxEx[k], poRyxxNew, PublicConstant.GNBH_HJ_HJSCYW, now);

              //???????????????????????????
              if(voBggzxxEx[k].getBggzxxList()!=null){
                for(int x=0;x<voBggzxxEx[k].getBggzxxList().size();x++){
                  Object obj = voBggzxxEx[k].getBggzxxList().get(x);
                  if(obj instanceof VoBggzxx){
                    VoBggzxx gz = (VoBggzxx) obj;
                    if (gz.getBggzxm().equals("bz")) {
                      bz = gz.getBggzhnr();
                    }
                  }
                }
              }

              if (vobEx != null) {
                if (vobEx.getVoBggzfhxx() != null) {
                  for (int a = 0; a < vobEx.getVoBggzfhxx().length; a++) {
                    bggzfhxxList.add(vobEx.getVoBggzfhxx()[a]);
                  }
                } //if(vobEx!=null)
                if (vobEx.getVoHcygxtzfhxx() != null) {
                  for (int a = 0; a < vobEx.getVoHcygxtzfhxx().length; a++) {
                    hcygxtzfhxxList.add(vobEx.getVoHcygxtzfhxx()[a]);
                  }
                }
              } //if(vobEx!=null)
            } //if(voHbbgxx[i].getRynbid().equals(voBggzxx[k].getRynbid()))
          } //for(int k=0;k<voBggzxx.length;k++)
        }
        //end
        poRyxxNew.setRyzt(HjConstant.RYZT_SC);
        poRyxxNew.setQysj(now);
        poRyxxNew.setJssj(null);
        poRyxxNew.setXxqysj(now);//add by hb 20061027
        poRyxxNew.setYwnr(HjConstant.YWNR_HJSC);
        poRyxxNew.setCchjywid(null);
        poRyxxNew.setCjhjywid(hjywid);
        poRyxxNew.setJlbz(PublicConstant.JLBZ_ZX);
        //poRyxxNew.setBz(bz);

       super.create(poRyxxNew);

        //??????????????????
        VoRhdxx voRhdxx = new VoRhdxx(poRyxxNew, poHxx, poMlpxxxx,
                                     BaseContext.getUser());
        voRhdxx.setHjsclb(voHjscxx[i].getHjsclb());
        VoXtywxz voLimit = XtywqxServiceImpl.VerifyBusinessLimit(PublicConstant.
            GNBH_HJ_HJSCYW, voRhdxx);
        if (voLimit.getLimitflag()) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLIMIT,
                                     "??????????????????????????????????????????" + voLimit.getLimitinfo(), null);
        }
        //??????????????????????????????
        if (voHjscxx[i].getSpywid() == null) {
          voLimit = XtywqxServiceImpl.VerifyCheckLimit(PublicConstant.
              GNBH_HJ_HJSCYW, voRhdxx);
          if (voLimit.getLimitflag()) {
            throw new ServiceException(WSErrCode.ERR_SERVICE_HJSCSXYSP,
                                       "????????????????????????????????????????????????" +
                                       voLimit.getLimitinfo() +
                                       ";?????????????????????ID???" + voLimit.getSpmbid(), null);
          }
        }
        
        if(voHjscxx[i].getSpywid()!=null){
        	PoHJSP_HJSCSPSQB sp = super.get(PoHJSP_HJSCSPSQB.class, voHjscxx[i].getSpywid());
        	voSbjbxx = new VoSbjbxx();
        	voSbjbxx.setSbrgmsfhm(sp.getSbrgmsfhm());
        	voSbjbxx.setSbryxm(sp.getSbryxm());
        	voSbjbxx.setSbsj(sp.getSbsj());
        }
        
        voRhdxx = null;

        //????????????????????????
        PoHJYW_HJSCXXB poHjscxx = new PoHJYW_HJSCXXB();
        if (voSbjbxx != null) {
          BeanUtils.copyProperties(poHjscxx, voSbjbxx);
        }
        BeanUtils.copyProperties(poHjscxx, voHjscxx[i]);
        BeanUtils.copyProperties(poHjscxx, poRyxxNew);
        poHjscxx.setHhid(poHxx.getHhid());
        poHjscxx.setMlpid(poMlpxxxx.getMlpid());
        poHjscxx.setHjscid( (Long) hjyw_hjscxxbDAO.getId());
        poHjscxx.setCxbz(PublicConstant.CXBZ_FCX);
        poHjscxx.setHjywid(hjywid);
        poHjscxx.setSldw(this.getUserInfo().getDwdm());
        poHjscxx.setSlrid(this.getUserInfo().getYhid());
        poHjscxx.setSlsj(now);
        poHjscxx.setHjscsm(voHjscxx[i].getHjscsm());//add hubin 20121030
       super.create(poHjscxx);

        PoHJXX_CXSXBGB log = new PoHJXX_CXSXBGB();
        log.setBghcxsx(poJwhxxb.getCxfldm());
        log.setBgqcxsx(poJwhxxb.getCxfldm());
        log.setCjsj(now);
        log.setSldw(poJwhxxb.getDwdm());
        log.setBgqdw(poJwhxxb.getDwdm());
        log.setRynbid(poHjscxx.getRynbid());
        log.setHjywid(poHjscxx.getHjywid());
        log.setBgyy(poHjscxx.getHjsclb());
        log.setYwlb("scjl");
        log.setBz("????????????");
        log.setRkbj("0");
        log.setSsxq(poHjscxx.getSsxq());
        log.setJwhdm(poJwhxxb.getDm());
        log.setRysl(new Long(1));
       super.create(log);

        //??????????????????????????????????????????????????????
        voHcygxtzxxEx[i] = new VoHcygxtzxxEx();
        voHcygxtzxxEx[i].setFlag(0);
        voHcygxtzxxEx[i].setHcybdlx(HjConstant.HCYBDLX_LH);
        voHcygxtzxxEx[i].setHcybdlb(voHjscxx[i].getHjsclb());
        voHcygxtzxxEx[i].setRynbid(poRyxx.getRynbid());
        voHcygxtzxxEx[i].setHhnbid(poRyxxNew.getHhnbid());
        voHcygxtzxxEx[i].setNew_rynbid(poRyxxNew.getRynbid());
        voHcygxtzxxEx[i].setRyid(poRyxxNew.getRyid());
        voHcygxtzxxEx[i].setGmsfhm(poRyxxNew.getGmsfhm());
        voHcygxtzxxEx[i].setXm(poRyxxNew.getXm());
        voHcygxtzxxEx[i].setOld_yhzgx(null);
        voHcygxtzxxEx[i].setYhzgx(poRyxxNew.getYhzgx());
        voHcygxtzxxEx[i].setSbhjywid(null);

        //?????????????????????
        voZxhxx[i] = new VoZxhxx();
        voZxhxx[i].setNew_hhnbid(null);
        voZxhxx[i].setOld_hhnbid(poRyxx.getHhnbid());

        //????????????????????????
        String strHQL = new String();
        strHQL = "from PoHJXX_RHFLXXB as HJXX_RHFLXXB where rynbid=" +
            voHjscxx[i].getRynbid().toString();
        List rhflxxList =super.findAllByHQL(strHQL);
        if (rhflxxList != null) {
          for (int j = 0; j < rhflxxList.size(); j++) {
            PoHJXX_RHFLXXB poRhflxx = (PoHJXX_RHFLXXB) rhflxxList.get(j);
            poRhflxx.setCchjywid(hjywid);
            poRhflxx.setRhflzt(HjConstant.RHFLZT_HJSC);
           super.update(poRhflxx);
          }
        }

        //??????????????????
        strHQL = "from PoHJYW_CHCLXXB as HJYW_CHCLXXB where clfs='" +
            HjConstant.CHCLFS_WCL + "' and chsfhm='" + poRyxx.getGmsfhm() +
            "' and cxbz='" + PublicConstant.CXBZ_FCX + "' ";
        List chxxList =super.findAllByHQL(strHQL);
        if (chxxList != null) {
          for (int j = 0; j < chxxList.size(); j++) {
            PoHJYW_CHCLXXB poChclxx = (PoHJYW_CHCLXXB) chxxList.get(j);
            //????????????
            if (poRyxx.getRyid().equals(poChclxx.getRyid())) {
              poChclxx.setCxbz(PublicConstant.CXBZ_FCX);
              poChclxx.setClfs(HjConstant.CHCLFS_BRZX);
              poChclxx.setClhjywid(hjywid);
             super.update(poChclxx);
            }
            //??????????????????
            if (poRyxx.getRyid().equals(poChclxx.getBchryid())) {
              poChclxx.setClfs(HjConstant.CHCLFS_DFZX);
              poChclxx.setClhjywid(hjywid);
             super.update(poChclxx);
            }
          } //for (int j = 0; j < chxxList.size(); j++)
        }

        //????????????????????????(??????)
        VoRybdxx voRybdxx = new VoRybdxx();
        voRybdxx.setBdbid(poHjscxx.getHjscid());
        voRybdxx.setBdyy(poHjscxx.getHjsclb());
        voRybdxx.setBdrq(now.substring(0, 8));
        voRybdxx.setBdqhb(null);
        voRybdxx.setBdq_hhnbid(poRyxx.getHhnbid());
        voRybdxx.setBdh_hhnbid(null);
        voRybdxx.setRynbid(poRyxxNew.getRynbid());
        voRybdxx.setRzjs(new Long( -1));
        voRybdxx.setHzjs(new Long(0));
        rybdxxList.add(voRybdxx);

        //????????????
        if (voHjscxx[i].getSpywid() != null) {
          PojoInfo  hjsp_hjscspsqbDAO = DAOFactory.
              createHJSP_HJSCSPSQBDAO();
          PoHJSP_HJSCSPSQB poHjscspxx = super.get(PoHJSP_HJSCSPSQB.class,
              voHjscxx[i].getSpywid());
          if (poHjscspxx == null) {
            throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                       "????????????????????????????????????????????????????????????????????????????????????", null);
          }
          if (!HjConstant.SPJG_TY.equals(poHjscspxx.getSpjg())) {
            throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                       "??????????????????????????????????????????????????????????????????", null);
          }
          poHjscspxx.setHjywid(hjywid);
          //poHjblspxx.setRyid(poRyxx.getRyid());
          poHjscspxx.setLsbz(HjConstant.LSBZ_YLS);
         super.update(poHjscspxx);

          //?????????????????????????????????1
          this.decSPMBDQSYS(poHjscspxx.getSpmbid());
        }

        //??????????????????????????????
        voHjscfhxx[i] = new VoHjscfhxx();
        voHjscfhxx[i].setHhnbid(poRyxxNew.getHhnbid());
        voHjscfhxx[i].setRynbid(poRyxxNew.getRynbid());
        voHjscfhxx[i].setRyid(poRyxxNew.getRyid());
        voHjscfhxx[i].setOld_rynbid(poRyxx.getRynbid());
        voHjscfhxx[i].setHjscid(poHjscxx.getHjscid());
        voHjscfhxx[i].setGmsfhm(poRyxxNew.getGmsfhm());
        voHjscfhxx[i].setXm(poRyxxNew.getXm());
        voHjscfhxx[i].setYhzgx(poRyxxNew.getYhzgx());
      }

      ////////////////////////////////////////
      //?????????????????????
      VoHcygxtzfhxx voh[] = this.adjustHCYGX(hjywid, voSbjbxx,
                                             voHcygxtzxxEx, now);
      if (voh != null) {
        for (int i = 0; i < voh.length; i++) {
          hcygxtzfhxxList.add(voh[i]);
        }
      }

      ////////////////////////////////////////
      //??????????????????
      if (voBggzxxEx != null) {
        VoBggzfhxxEx vobEx = this.saveBGGZXX(hjywid, voSbjbxx, voBggzxxEx,
                                             PublicConstant.GNBH_HJ_HJSCYW, now);
        if (vobEx != null) {
          if (vobEx.getVoBggzfhxx() != null) {
            for (int a = 0; a < vobEx.getVoBggzfhxx().length; a++) {
              bggzfhxxList.add(vobEx.getVoBggzfhxx()[a]);
            }
          } //if(vobEx!=null)
          if (vobEx.getVoHcygxtzfhxx() != null) {
            for (int a = 0; a < vobEx.getVoHcygxtzfhxx().length; a++) {
              hcygxtzfhxxList.add(vobEx.getVoHcygxtzfhxx()[a]);
            }
          }
        } //if(vobEx!=null)
      }

      ///////////////////////////////////////////
      //????????????????????????
      List zxhhnbidList = this.logoutH(hjywid, voZxhxx, voHjscxx[0].getBdfw(),
                                       voHjscxx[0].getHjsclb(),
                                       HjConstant.YWNR_HJSC, now);

      ////////////////////////////////////////////
      //??????????????????
      Map hhnbidMap = new HashMap();
      for (int h = 0; h < hcygxtzfhxxList.size(); h++) {
        VoHcygxtzfhxx vo = (VoHcygxtzfhxx) hcygxtzfhxxList.get(h);
        hhnbidMap.put(vo.getHhnbid(), null);
      }
      //???????????????????????????ID
      if (zxhhnbidList != null) {
        for (int h = 0; h < zxhhnbidList.size(); h++) {
          hhnbidMap.remove(zxhhnbidList.get(h));
        }
      }
      for (Iterator itr = hhnbidMap.keySet().iterator(); itr.hasNext(); ) {
        this.checkHCYXX( (Long) itr.next());
      }

      ////////////////////////////////////////
      //??????????????????????????????
      PoHJLS_HJYWLSB poHjywlsxx = this.saveHJYWLSXX(hjywid,
          PublicConstant.GNBH_HJ_HJSCYW,
          zxhhnbidList != null && zxhhnbidList.size() > 0 ?
          PublicConstant.HJYWLS_YWLX_QH :
          PublicConstant.HJYWLS_YWLX_GR,
          voHjscxx.length, voSbjbxx, now);

      //////////////////////////////////////////
      //????????????????????????(??????)
      if (zxhhnbidList != null && zxhhnbidList.size() > 0) {
        for (int a = 0; a < zxhhnbidList.size(); a++) {
          Long hhnbid = (Long) zxhhnbidList.get(a);
          for (int b = 0; b < rybdxxList.size(); b++) {
            VoRybdxx vo = (VoRybdxx) rybdxxList.get(b);
            if (hhnbid.equals(vo.getBdq_hhnbid())) {
              vo.setHzjs(new Long( -1));
              break;
            }
          }
        }
      }
      this.saveRYBDXX(rybdxxList, poHjywlsxx);

      ///////////////////////////////////////////
      //??????????????????????????????????????????????????????????????????????????????
      PoHJXX_CZRKJBXXB poHzxx = null;
      String strHQL = "from PoHJYW_HJSCXXB where hjywid=" + hjywid;
      List hjscxxList =super.findAllByHQL(strHQL);
      if (hjscxxList != null && hjscxxList.size() > 0) {
        for (int i = 0; i < hjscxxList.size(); i++) {
          PoHJYW_HJSCXXB poHjscxx = (PoHJYW_HJSCXXB) hjscxxList.get(i);
          if (poHzxx == null) {
            strHQL = "from PoHJXX_CZRKJBXXB where yhzgx < '10' and jlbz='" +
                PublicConstant.JLBZ_ZX + "' and cxbz='" +
                PublicConstant.CXBZ_FCX +
                "' and hhnbid =" + poHjscxx.getHhnbid();
            List hzxxList =super.findAllByHQL(strHQL);
            poHzxx = this.findHZXX(hzxxList);
          }
          poHjscxx.setHzgmsfhm(poHzxx != null ? poHzxx.getGmsfhm() : null);
          poHjscxx.setHzxm(poHzxx != null ? poHzxx.getXm() : null);
          poHjscxx.setYwlx(poHjywlsxx.getYwlx());
          poHjscxx.setCzsm(poHjywlsxx.getCzsm());
         super.update(poHjscxx);
        }
      }

      ////////////////////////////////////
      //??????????????????
      voHjscywfhxx = new VoHjscywfhxx();
      voHjscywfhxx.setHjywid(hjywid);
      voHjscywfhxx.setVoHjscfhxx(voHjscfhxx);
      voHjscywfhxx.setVoBggzfhxx( (VoBggzfhxx[]) bggzfhxxList.toArray(new
          VoBggzfhxx[bggzfhxxList.size()]));
      voHjscywfhxx.setVoHcygxtzfhxx( (VoHcygxtzfhxx[]) hcygxtzfhxxList.toArray(new
          VoHcygxtzfhxx[hcygxtzfhxxList.size()]));

      ///////////////////////////////////////////
      //????????????
    }
    catch (DAOException ex) {
      //????????????
      throw ex;
    }
    catch (ServiceException ex) {
      //????????????
      throw ex;
    }
    catch (InvocationTargetException ex) {
      //????????????
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }
    catch (IllegalAccessException ex) {
      //????????????
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }
    catch (Exception ex) {
      //????????????
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }

    return voHjscywfhxx;
  }

  /**
   * ????????????????????????
   * @param voRhhdxx - ??????????????????
   * @param voLhhdxx - ??????????????????
   * @param voSbjbxx - ??????????????????
   * @param voHjblxx[] - ??????????????????
   * @param voChxx[] - ????????????
   * @param voBggzxx[] - ??????????????????
   * @return VoHjblywfhxx
   * @roseuid 4049CE830331
   */
  public VoHjblywfhxx processHjblyw(VoRhhdxx voRhhdxx, VoLhhdxx voLhhdxx,
                                    VoSbjbxx voSbjbxx,
                                    VoHjblxx voHjblxx[], VoChxx voChxx[],
                                    VoBggzxxEx voBggzxxEx[]) throws
      ServiceException,
      DAOException {

    VoHjblywfhxx voHjblywfhxx = null;
    VoHjblfhxx voHjblfhxx[] = null;
    VoHcygxtzxxEx voHcygxtzxxEx[] = null;
    List bggzfhxxList = new ArrayList();
    List hcygxtzfhxxList = new ArrayList();
    List chxxList = new ArrayList();
    List rybdxxList = new ArrayList();
    VoLhhdfhxx voLhhdfhxx = null;
    PoHJXX_MLPXXXXB poMlpxxxx = null;
    PoHJXX_HXXB poHxx = null;
    Long hjywid = null;
    String rh_lh; //?????????????????????1-??????/2-?????????
    boolean bAdd = false;
    String now = StringUtils.getServiceTime();

    if (voHjblxx == null || (voHjblxx != null && voHjblxx.length <= 0)) {
      return null;
    }

    //?????????????????????(???????????? By MHB 2005/08/01 11:10:00)
    voChxx = null;
    PoXT_JWHXXB poJwhxxb = null;
    try {
      PojoInfo  xt_jwhxxbDAO = DAOFactory.createXT_JWHXXBDAO();
      PojoInfo  hjxx_mlpxxxxbDAO = DAOFactory.createHJXX_MLPXXXXBDAO();
      PojoInfo  hjxx_czrkjbxxbDAO = DAOFactory.createHJXX_CZRKJBXXBDAO();
      PojoInfo  hjxx_hxxbDAO = DAOFactory.createHJXX_HXXBDAO();
      PojoInfo  hjls_hjywlsbDAO = DAOFactory.createHJLS_HJYWLSBDAO();
      PojoInfo  hjyw_hjblxxbDAO = DAOFactory.createHJYW_HJBLXXBDAO();
      PojoInfo  hjxx_ryzpxxbDAO = DAOFactory.createHJXX_RYZPXXBDAO();
      PojoInfo  zjxx_jmsfzxxbDAO = DAOFactory.createZJXX_JMSFZXXBDAO();
      PojoInfo  hjyw_gmsfhmsxmfpxxbDAO = DAOFactory.
          createHJYW_GMSFHMSXMFPXXBDAO();

      /////////////////////////////////////
      //??????????????????ID
      hjywid = (Long) hjls_hjywlsbDAO.getId();
      ///////////////////////////////////////////////
      //????????????
      if(voSbjbxx!=null && voSbjbxx.getHzywid()!=null){
    	  //??????????????????
          updateHzyw(voSbjbxx.getHzywid(),hjywid,0l);
      }

      String djzt="";
      //////////////////////////////////////
      //???????????????????????????
      //??????
      if (voRhhdxx != null && voRhhdxx.getHhnbid() != null) {
        //???????????????
    	//add by zjm 20191029
    	//???????????????voRhhdxx?????????????????????hhid?????????hhid?????????????????????????????????????????? 
    	String hxxHql = "from "+PoHJXX_HXXB.class.getName()+" where hhid ='"+voRhhdxx.getHhnbid()+"' and jlbz ='1' and cxbz ='0' ";
		List list= super.findAllByHQL(hxxHql);
		if(list.size()==0) {
			throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                    "???????????????????????????????????????????????????????????????", null);
		}else {
			poHxx = (PoHJXX_HXXB) list.get(0);
		}
		
        //poHxx  = super.get(PoHJXX_HXXB.class,voRhhdxx.getHhnbid());
        if (poHxx == null) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "???????????????????????????????????????????????????????????????", null);
        }
        List czrklist = super.findAllByHQL("from PoHJXX_CZRKJBXXB a where ryzt='0' and cxbz='0' and jlbz='1' and a.hhnbid=" + poHxx.getHhnbid());
        if(czrklist.size()>0) {
        	PoHJXX_CZRKJBXXB czrkjbxxb = (PoHJXX_CZRKJBXXB) czrklist.get(0);
        	if(czrkjbxxb.getDjzt()!=null&&!czrkjbxxb.getDjzt().equals("1")) {
        		djzt = czrkjbxxb.getDjzt();
        	}
        }
        //???????????????????????????
        this.checkHXX(poHxx, hjxx_hxxbDAO, "??????????????????");
        //??????????????????
        if (!HjConstant.HHZT_ZC.equals(poHxx.getHhzt())) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "??????????????????????????????????????????????????????????????????", null);
        }
        //???????????????
        poMlpxxxx  = super.get(PoHJXX_MLPXXXXB.class,poHxx.getMlpnbid());
        if (poMlpxxxx == null) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "??????????????????????????????????????????????????????????????????", null);
        }
        poJwhxxb  = super.get(PoXT_JWHXXB.class,poMlpxxxx.getJcwh());
        if (poJwhxxb == null) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "?????????????????????????????????????????????????????????", null);
        }

        //?????????????????????(????????????2005/05/25 10:40:00)
        //if (!DzConstant.MLPZT_ZC.equals(poMlpxxxx.getMlpzt())) {
        //  throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
        //                             "?????????????????????????????????????????????????????????????????????", null);
        //}
        rh_lh = "2"; //?????????????????????1-??????/2-?????????
      }
      //??????
      else {
        if(voHjblxx!=null){
          Long spid = voHjblxx[0].getSpywid();
          if(spid!=null){
            PojoInfo  hjsp_hjblspsqbDAO = DAOFactory.createHJSP_HJBLSPSQBDAO();
            PoHJSP_HJBLSPSQB sp  = super.get(PoHJSP_HJBLSPSQB.class,spid);
            if(sp!=null){
              voLhhdxx.setBzdz(sp.getBzdz());
              voLhhdxx.setBzdzid(sp.getBzdzid());
              voLhhdxx.setBzdzst(sp.getBzdzst());
              voLhhdxx.setBzdzx(sp.getBzdzx());
              voLhhdxx.setBzdzy(sp.getBzdzy());
              
              //?????????????????????????????????
              voSbjbxx = new VoSbjbxx();
              voSbjbxx.setSbrgmsfhm(sp.getSbrgmsfhm());
              voSbjbxx.setSbryxm(sp.getSbryxm());
              voSbjbxx.setSbsj(sp.getSbsj());
            }
          }
        }
        voLhhdfhxx = this.createH(hjywid, voLhhdxx, voHjblxx[0].getBdfw(),
                                  voHjblxx[0].getHjbllb(), HjConstant.YWNR_HJBL,
                                  now);
        if (voLhhdfhxx == null ||
            (voLhhdfhxx != null &&
             (voLhhdfhxx.getHhnbid() == null || voLhhdfhxx.getMlpnbid() == null))) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "????????????????????????????????????????????????????????????", null);
        }
        poHxx = voLhhdfhxx.getPoHJXX_HXXB();
        poMlpxxxx = voLhhdfhxx.getPoHJXX_MLPXXXXB();
        rh_lh = "1"; //?????????????????????1-??????/2-?????????

        poJwhxxb  = super.get(PoXT_JWHXXB.class,poMlpxxxx.getJcwh());
        if (poJwhxxb == null) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "?????????????????????????????????????????????????????????", null);
        }

      }

      ////////////////////////////////////////
      //??????????????????
      List sjfwList = new ArrayList();
      VoXtsjfw voXtsjfw = new VoXtsjfw();
      voXtsjfw.setSjfw(poMlpxxxx.getJcwh());
      voXtsjfw.setSjfwbz(PublicConstant.XT_QX_JWH);
      sjfwList.add(voXtsjfw);
      boolean bLimit = false;
      bLimit = XtywqxServiceImpl.VerifyDataRange(this.getUserInfo().getYhid().
                                                 toString(),
                                                 PublicConstant.GNBH_HJ_HJBLYW,
                                                 sjfwList);
      if (!bLimit) {
        throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLIMIT,
        		CommonUtil.getSjfwError(sjfwList)  + "????????????????????????????????????", null);
      }

      //////////////////////////////////////
      //????????????????????????
      voHjblfhxx = new VoHjblfhxx[voHjblxx.length];
      voHcygxtzxxEx = new VoHcygxtzxxEx[voHjblxx.length];
      for (int i = 0; i < voHjblxx.length; i++) {
        Long rynbid = (Long) hjxx_czrkjbxxbDAO.getId();
        boolean bDoubleID = false;
        VoGmsfhmfpfhxx voGmsfhmfpfhxx = null;
        Long zpid = null; //??????ID

        //??????????????????????????????????????????
        if (voHjblxx[i].getGmsfhm() == null ||
            (voHjblxx[i].getGmsfhm() != null &&
             voHjblxx[i].getGmsfhm().length() == 0)) {

          VoGmsfhmfpsqxx voGmsfhmfpsqxx = new VoGmsfhmfpsqxx();

          //??????????????????????????????????????????
          voGmsfhmfpsqxx.setCsrq(voHjblxx[i].getCsrq());
          voGmsfhmfpsqxx.setRyid(rynbid);
          voGmsfhmfpsqxx.setXb(voHjblxx[i].getXb());
          voGmsfhmfpsqxx.setXm(voHjblxx[i].getXm());
          voGmsfhmfpsqxx.setXzqh(poMlpxxxx.getSsxq());
          voGmsfhmfpsqxx.setPcs(poMlpxxxx.getPcs());
          voGmsfhmfpsqxx.setXzjd(poMlpxxxx.getXzjd());

          //????????????????????????????????????
          voGmsfhmfpfhxx = this.assignGMSFHM(hjywid, voGmsfhmfpsqxx, now);
          if (voGmsfhmfpfhxx == null ||
              (voGmsfhmfpfhxx != null && voGmsfhmfpfhxx.getGmsfhm() == null) ||
              (voGmsfhmfpfhxx != null && voGmsfhmfpfhxx.getGmsfhm() != null &&
               voGmsfhmfpfhxx.getGmsfhm().length() != 18)) {
            throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                       "??????????????????????????????????????????????????????????????????", null);
          }

        }
        //????????????????????????????????????????????????????????????
        else {
          //??????????????????
          if (!PID.IDCheck(voHjblxx[i].getGmsfhm())) {
            throw new ServiceException(WSErrCode.ERR_SERVICE_DATANOTFULL,
                                       "??????????????????(" + voHjblxx[i].getGmsfhm() +
                                       ")??????????????????????????????????????????", null);
          }
          //?????????????????????????????????(?????????????????????????????????)
          if (voChxx != null) {
            for (int k = 0; k < voChxx.length; k++) {
              if (voChxx[k].getChsfhm() != null &&
                  voChxx[k].getChsfhm().equals(voHjblxx[i].getGmsfhm())) {
                bDoubleID = true;
                break;
              }
            }
          }
          if (!bDoubleID) {
            if (this.countCHRYXX(voHjblxx[i].getGmsfhm()) > 0) {
              throw new ServiceException(WSErrCode.ERR_SERVICE_DATANOTFULL,
                                         "??????????????????(" + voHjblxx[i].getGmsfhm() +
                                         ")??????????????????????????????????????????", null);
            }
          }
          //??????????????????????????????
          String strHQL =
              "select count(*) from PoHJYW_GMSFHMSXMFPXXB where gmsfhm='" +
              voHjblxx[i].getGmsfhm() + "'";
          if (super.getCount(strHQL) <= 0) {
            PoHJYW_GMSFHMSXMFPXXB poFpxx = new PoHJYW_GMSFHMSXMFPXXB();
            poFpxx.setFpid( (Long) hjyw_gmsfhmsxmfpxxbDAO.getId());
            poFpxx.setRyid(rynbid);
            poFpxx.setCsrq(voHjblxx[i].getCsrq());
            poFpxx.setXm(voHjblxx[i].getXm());
            poFpxx.setGmsfhm(voHjblxx[i].getGmsfhm());
            poFpxx.setXb(voHjblxx[i].getXb());
            poFpxx.setDwdm(poMlpxxxx.getPcs());
            poFpxx.setSxh(voHjblxx[i].getGmsfhm().substring(14, 17));
            poFpxx.setCzlb(HjConstant.GMSFHMFPWS_CZLB_LR);
            poFpxx.setHjywid(hjywid);
           super.create(poFpxx);
          }

        } //????????????????????????????????????????????????????????????

        //???????????????????????????
        if (voHjblxx[i].getZp() != null && voHjblxx[i].getZp().length() > 0) {
          zpid = (Long) hjxx_ryzpxxbDAO.getId();
        }

        //??????????????????
        PoZJXX_JMSFZXXB poJmsfzxx = null;
        if (voHjblxx[i].getYxqxjzrq() != null &&
            voHjblxx[i].getYxqxjzrq().length() > 0 &&
            voHjblxx[i].getYxqxqsrq() != null &&
            voHjblxx[i].getYxqxqsrq().length() > 0 &&
            voHjblxx[i].getZjlb() != null &&
            voHjblxx[i].getZjlb().length() > 0) {

          if (voHjblxx[i].getGmsfhm() == null ||
              (voHjblxx[i].getGmsfhm() != null &&
               voHjblxx[i].getGmsfhm().trim().length() <= 0)) {
            throw new ServiceException(WSErrCode.ERR_SERVICE_DATANOTFULL,
                                       "??????????????????????????????????????????????????????????????????????????????????????????????????????", null);
          }

          poJmsfzxx = new PoZJXX_JMSFZXXB();
          BeanUtils.copyProperties(poJmsfzxx, voHjblxx[i]);
          poJmsfzxx.setNbsfzid( (Long) zjxx_jmsfzxxbDAO.getId());
          poJmsfzxx.setZjzt(ZjConstant.ZJ_ZJZT_ZC);
          poJmsfzxx.setRyid(rynbid);
          //add by hh 20050920 ??????????????????????????????????????????????????????
          poJmsfzxx.setZjlx(ZjConstant.SFZXX_ZJLX_HJBL);
          poJmsfzxx.setZjsj(StringUtils.getServiceTime());

         super.create(poJmsfzxx);
        }

        //??????????????????
        PoHJXX_CZRKJBXXB poRyxx = new PoHJXX_CZRKJBXXB();
        BeanUtils.copyProperties(poRyxx, poHxx);
        BeanUtils.copyProperties(poRyxx, poMlpxxxx); //add by mhb 2005/07/04 11:20
        BeanUtils.copyProperties(poRyxx, voHjblxx[i]);
        poRyxx.setNbsfzid(poJmsfzxx != null ? poJmsfzxx.getNbsfzid() : null);
        poRyxx.setRynbid(rynbid);
        poRyxx.setRyid(poRyxx.getRynbid());
        poRyxx.setMlpnbid(poMlpxxxx.getMlpnbid());
        poRyxx.setHhnbid(poHxx.getHhnbid());
        poRyxx.setZpid(zpid);
        if (voGmsfhmfpfhxx != null) {
          poRyxx.setGmsfhm(voGmsfhmfpfhxx.getGmsfhm());
        }
        poRyxx.setRyzt(HjConstant.RYZT_ZC);
        poRyxx.setRysdzt(HjConstant.RYSDZT_ZC);
        poRyxx.setRylb(HjConstant.RYLB_YB);
        poRyxx.setQysj(now);
        poRyxx.setJssj(null);
        poRyxx.setXxqysj(now);//add by hb 20061027
        poRyxx.setYwnr(HjConstant.YWNR_HJBL);
        poRyxx.setCchjywid(null);
        poRyxx.setCjhjywid(hjywid);
        poRyxx.setJlbz(PublicConstant.JLBZ_ZX);
        poRyxx.setCxfldm(poJwhxxb.getCxfldm());
        if(CommonUtil.isNotEmpty(djzt)) {
        	poRyxx.setDjzt(djzt);
        }
       super.create(poRyxx);

        //??????????????????
        VoRhdxx voRhdxx = new VoRhdxx(poRyxx, poHxx, poMlpxxxx,
                                     BaseContext.getUser());
        voRhdxx.setHjbllb(voHjblxx[i].getHjbllb());
        VoXtywxz voLimit = XtywqxServiceImpl.VerifyBusinessLimit(PublicConstant.
            GNBH_HJ_HJBLYW, voRhdxx);
        if (voLimit.getLimitflag()) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLIMIT,
                                     "??????????????????????????????????????????" + voLimit.getLimitinfo(), null);
        }
        //??????????????????????????????
        if (voHjblxx[i].getSpywid() == null) {
          voLimit = XtywqxServiceImpl.VerifyCheckLimit(PublicConstant.
              GNBH_HJ_HJBLYW, voRhdxx);
          if (voLimit.getLimitflag()) {
            throw new ServiceException(WSErrCode.ERR_SERVICE_HJBLSXYSP,
                                       "????????????????????????????????????????????????" +
                                       voLimit.getLimitinfo() +
                                       ";?????????????????????ID???" + voLimit.getSpmbid(), null);
          }
        }
        voRhdxx = null;

        //????????????????????????
        if (bDoubleID) {
          VoChxx ivoChxx = new VoChxx();
          ivoChxx.setBchryid(poRyxx.getRyid());
          ivoChxx.setChsfhm(poRyxx.getGmsfhm());
          chxxList.add(ivoChxx);
        }

        //??????????????????????????????????????????????????????
        voHcygxtzxxEx[i] = new VoHcygxtzxxEx();
        voHcygxtzxxEx[i].setFlag(0);
        voHcygxtzxxEx[i].setHcybdlx(HjConstant.HCYBDLX_RH);
        voHcygxtzxxEx[i].setHcybdlb(voHjblxx[i].getHjbllb());
        voHcygxtzxxEx[i].setHhnbid(poHxx.getHhnbid());
        voHcygxtzxxEx[i].setRynbid(rynbid);
        voHcygxtzxxEx[i].setNew_rynbid(rynbid);
        voHcygxtzxxEx[i].setRyid(poRyxx.getRyid());
        voHcygxtzxxEx[i].setXm(poRyxx.getXm());
        voHcygxtzxxEx[i].setGmsfhm(poRyxx.getGmsfhm());
        voHcygxtzxxEx[i].setOld_yhzgx(null);
        voHcygxtzxxEx[i].setYhzgx(voHjblxx[i].getYhzgx());
        voHcygxtzxxEx[i].setSbhjywid(null);

        //??????????????????
        if (zpid != null) {
          VoHJXX_RYZPXXB voRyzpxx = new VoHJXX_RYZPXXB();
          voRyzpxx.setZpid(zpid);
          voRyzpxx.setLrrq(now);
          voRyzpxx.setRyid(poRyxx.getRyid());
          voRyzpxx.setXm(voHjblxx[i].getXm());
          voRyzpxx.setGmsfhm(poRyxx.getGmsfhm());
          voRyzpxx.setZp(voHjblxx[i].getZp());
         super.create(voRyzpxx.toPoHJXX_RYZPXXB());
        }

        //????????????????????????
        PoHJYW_HJBLXXB poHjblxx = new PoHJYW_HJBLXXB();
        if (voSbjbxx != null) {
          BeanUtils.copyProperties(poHjblxx, voSbjbxx);
        }
        BeanUtils.copyProperties(poHjblxx, voHjblxx[i]);
        BeanUtils.copyProperties(poHjblxx, poRyxx);
        poHjblxx.setHhid(poHxx.getHhid());
        poHjblxx.setMlpid(poMlpxxxx.getMlpid());
        poHjblxx.setHjblid( (Long) hjyw_hjblxxbDAO.getId());
        poHjblxx.setCxbz(PublicConstant.CXBZ_FCX);
        poHjblxx.setHjywid(hjywid);
        poHjblxx.setSldw(this.getUserInfo().getDwdm());
        poHjblxx.setSlrid(this.getUserInfo().getYhid());
        poHjblxx.setSlsj(now);
        poHjblxx.setCxfldm(poJwhxxb.getCxfldm());
        
       super.create(poHjblxx);

        PoHJXX_CXSXBGB log = new PoHJXX_CXSXBGB();
        log.setBghcxsx(poJwhxxb.getCxfldm());
        log.setBgqcxsx(poJwhxxb.getCxfldm());
        log.setCjsj(now);
        log.setSldw(poJwhxxb.getDwdm());
        log.setBgqdw(poJwhxxb.getDwdm());
        log.setRynbid(poHjblxx.getRynbid());
        log.setHjywid(poHjblxx.getHjywid());
        log.setBgyy(poHjblxx.getHjbllb());
        log.setYwlb("blxx");
        log.setBz("????????????");
        log.setRkbj("1");
        log.setSsxq(poHjblxx.getSsxq());
        log.setJwhdm(poJwhxxb.getDm());
        log.setRysl(new Long(1));
       super.create(log);

        //????????????????????????(??????)
        VoRybdxx voRybdxx = new VoRybdxx();
        voRybdxx.setBdbid(poHjblxx.getHjblid());
        voRybdxx.setBdyy(poHjblxx.getHjbllb());
        voRybdxx.setBdrq(now.substring(0, 8));
        voRybdxx.setBdqhb(null);
        voRybdxx.setBdq_hhnbid(null);
        voRybdxx.setBdh_hhnbid(poRyxx.getHhnbid());
        voRybdxx.setRynbid(poRyxx.getRynbid());
        voRybdxx.setRzjs(new Long(1));
        if (!bAdd && "1".equals(rh_lh)) {
          bAdd = true;
          voRybdxx.setHzjs(new Long(1));
        }
        else {
          voRybdxx.setHzjs(new Long(0));
        }
        rybdxxList.add(voRybdxx);

        //????????????
        if (voHjblxx[i].getSpywid() != null) {
          PojoInfo  hjsp_hjblspsqbDAO = DAOFactory.
              createHJSP_HJBLSPSQBDAO();
          PoHJSP_HJBLSPSQB poHjblspxx = super.get(PoHJSP_HJBLSPSQB.class,
              voHjblxx[i].getSpywid());
          if (poHjblspxx == null) {
            throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                       "????????????????????????????????????????????????????????????????????????????????????", null);
          }
          if (!HjConstant.SPJG_TY.equals(poHjblspxx.getSpjg())) {
            throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                       "??????????????????????????????????????????????????????????????????", null);
          }
          poHjblspxx.setHjywid(hjywid);
          //poHjblspxx.setRyid(poRyxx.getRyid());
          poHjblspxx.setRynbid(poRyxx.getRynbid());
          poHjblspxx.setLsbz(HjConstant.LSBZ_YLS);
          poHjblspxx.setCxfldm(poJwhxxb.getCxfldm());
         super.update(poHjblspxx);

          //?????????????????????????????????1
          this.decSPMBDQSYS(poHjblspxx.getSpmbid());
        }

        //??????????????????????????????
        voHjblfhxx[i] = new VoHjblfhxx();
        voHjblfhxx[i].setHhnbid(poRyxx.getHhnbid());
        voHjblfhxx[i].setRynbid(poRyxx.getRynbid());
        voHjblfhxx[i].setHjblid(poHjblxx.getHjblid());
        voHjblfhxx[i].setRyid(poRyxx.getRyid());
        voHjblfhxx[i].setGmsfhm(poRyxx.getGmsfhm());
        voHjblfhxx[i].setXm(poRyxx.getXm());
        voHjblfhxx[i].setYhzgx(poRyxx.getYhzgx());
        voHjblfhxx[i].setSys_bh(voHjblxx[i].getSys_bh());
      }

      ////////////////////////////////////////
      //?????????????????????
      VoHcygxtzfhxx voh[] = this.adjustHCYGX(hjywid, voSbjbxx,
                                             voHcygxtzxxEx, now);
      if (voh != null) {
        for (int i = 0; i < voh.length; i++) {
          hcygxtzfhxxList.add(voh[i]);
        }
      }

      ////////////////////////////////////////
      //??????????????????
      if (voBggzxxEx != null) {
        VoBggzfhxxEx vobEx = this.saveBGGZXX(hjywid, voSbjbxx, voBggzxxEx,
                                             PublicConstant.GNBH_HJ_HJBLYW, now);
        if (vobEx != null) {
          if (vobEx.getVoBggzfhxx() != null) {
            for (int a = 0; a < vobEx.getVoBggzfhxx().length; a++) {
              bggzfhxxList.add(vobEx.getVoBggzfhxx()[a]);
            }
          } //if(vobEx!=null)
          if (vobEx.getVoHcygxtzfhxx() != null) {
            for (int a = 0; a < vobEx.getVoHcygxtzfhxx().length; a++) {
              hcygxtzfhxxList.add(vobEx.getVoHcygxtzfhxx()[a]);
            }
          }
        } //if(vobEx!=null)
      }

      ///////////////////////////////////////////////////////////////
      //??????????????????
      Map hhnbidMap = new HashMap();
      for (int h = 0; h < hcygxtzfhxxList.size(); h++) {
        VoHcygxtzfhxx vo = (VoHcygxtzfhxx) hcygxtzfhxxList.get(h);
        hhnbidMap.put(vo.getHhnbid(), null);
      }
      for (Iterator itr = hhnbidMap.keySet().iterator(); itr.hasNext(); ) {
        this.checkHCYXX( (Long) itr.next());
      }

      ////////////////////////////////////////
      //??????????????????
      this.saveCHXX(hjywid, chxxList, now);

      ////////////////////////////////////////
      //??????????????????????????????
      //rh_lh = "1"; //?????????????????????1-??????/2-?????????
      PoHJLS_HJYWLSB poHjywlsxx = this.saveHJYWLSXX(hjywid,
          PublicConstant.GNBH_HJ_HJBLYW,
          rh_lh.equals("1") ? PublicConstant.HJYWLS_YWLX_QH :
          PublicConstant.HJYWLS_YWLX_GR,
          voHjblxx.length, voSbjbxx, now);

      //////////////////////////////////////////
      //????????????????????????(??????)
      this.saveRYBDXX(rybdxxList, poHjywlsxx);

      ///////////////////////////////////////////
      //??????????????????????????????????????????????????????????????????????????????
      PoHJXX_CZRKJBXXB poHzxx = null;
      String strHQL = "from PoHJYW_HJBLXXB where hjywid=" + hjywid;
      List hjblxxList =super.findAllByHQL(strHQL);
      if (hjblxxList != null && hjblxxList.size() > 0) {
        for (int i = 0; i < hjblxxList.size(); i++) {
          PoHJYW_HJBLXXB poHjblxx = (PoHJYW_HJBLXXB) hjblxxList.get(i);
          if (poHzxx == null) {
            strHQL = "from PoHJXX_CZRKJBXXB where yhzgx < '10' and ryzt='" +
                HjConstant.RYZT_ZC + "' and jlbz='" +
                PublicConstant.JLBZ_ZX + "' and cxbz='" +
                PublicConstant.CXBZ_FCX +
                "' and hhnbid =" + poHjblxx.getHhnbid();
            List hzxxList =super.findAllByHQL(strHQL);
            if (hzxxList != null && hzxxList.size() > 0) {
              poHzxx = (PoHJXX_CZRKJBXXB) hzxxList.get(0);
            }
          }
          poHjblxx.setHzgmsfhm(poHzxx != null ? poHzxx.getGmsfhm() : null);
          poHjblxx.setHzxm(poHzxx != null ? poHzxx.getXm() : null);
          poHjblxx.setYwlx(poHjywlsxx.getYwlx());
          poHjblxx.setCzsm(poHjywlsxx.getCzsm());
          poHjblxx.setCxfldm(poJwhxxb.getCxfldm());
         super.update(poHjblxx);
        }
      }

      ////////////////////////////////////
      //????????????????????????
      voHjblywfhxx = new VoHjblywfhxx();
      voHjblywfhxx.setHjywid(hjywid);
      voHjblywfhxx.setHhnbid(poHxx.getHhnbid());
      voHjblywfhxx.setMlpnbid(poMlpxxxx.getMlpnbid());
      voHjblywfhxx.setVoHjblfhxx(voHjblfhxx);
      voHjblywfhxx.setVoBggzfhxx( (VoBggzfhxx[]) bggzfhxxList.toArray(new
          VoBggzfhxx[bggzfhxxList.size()]));
      voHjblywfhxx.setVoHcygxtzfhxx( (VoHcygxtzfhxx[]) hcygxtzfhxxList.toArray(new
          VoHcygxtzfhxx[hcygxtzfhxxList.size()]));

      /////////////////////////////////////////
      //????????????
    }
    catch (DAOException ex) {
      //????????????
      throw ex;
    }
    catch (ServiceException ex) {
      //????????????
      throw ex;
    }
    catch (InvocationTargetException ex) {
      //????????????
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }
    catch (IllegalAccessException ex) {
    	//????????????
    	throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }
    catch (DataAccessException ex) {
      //????????????
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }
    catch (Exception ex) {
      //????????????
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }

    return voHjblywfhxx;
  }

  /**
   * ????????????????????????
   *
   * @param voSbjbxx - ??????????????????
   * @param voSwzxxx[] - ??????????????????
   * @param voBggzxx[] - ??????????????????
   * @return VoSwxzywfhxx
   * @roseuid 4049C5C601F4
   */
  public VoSwzxywfhxx processSwzxyw(VoSbjbxx voSbjbxx, VoSwzxxx voSwzxxx[],
                                    VoBggzxxEx voBggzxxEx[]) throws
      ServiceException,
      DAOException {

    VoSwzxywfhxx voSwzxywfhxx = null;
    VoSwzxfhxx voSwzxfhxx[] = null;
    VoHcygxtzxxEx voHcygxtzxxEx[] = null;
    List bggzfhxxList = new ArrayList();
    List hcygxtzfhxxList = new ArrayList();
    List rybdxxList = new ArrayList();
    VoZxhxx voZxhxx[] = null;
    Long hjywid = null;
    String now = StringUtils.getServiceTime();
    PoXT_JWHXXB poJwhxxb = null;
    if (voSwzxxx == null || (voSwzxxx != null && voSwzxxx.length <= 0)) {
      return null;
    }

    try {
      PojoInfo  hjxx_czrkjbxxbDAO = DAOFactory.createHJXX_CZRKJBXXBDAO();
      PojoInfo  hjyw_swzxxxbDAO = DAOFactory.createHJYW_SWZXXXBDAO();
      PojoInfo  hjls_hjywlsbDAO = DAOFactory.createHJLS_HJYWLSBDAO();
      PojoInfo  hjxx_rhflxxbDAO = DAOFactory.createHJXX_RHFLXXBDAO();
      PojoInfo  hjyw_chclxxbDAO = DAOFactory.createHJYW_CHCLXXBDAO();
      PojoInfo  hjxx_mlpxxxxbDAO = DAOFactory.createHJXX_MLPXXXXBDAO();
      PojoInfo  hjxx_hxxbDAO = DAOFactory.createHJXX_HXXBDAO();
      PojoInfo  xt_jwhxxbDAO = DAOFactory.createXT_JWHXXBDAO();
      
      /////////////////////////////////////
      //??????????????????ID
      hjywid = (Long) hjls_hjywlsbDAO.getId();
      
      ///////////////////////////////////////////
      //????????????
      //??????????????????
      if (voBggzxxEx != null) {
        VoBggzfhxxEx vobEx = this.saveBGGZXX(hjywid, voSbjbxx, voBggzxxEx,
                                             PublicConstant.GNBH_HJ_SWZXYW, now);
        if (vobEx != null) {
          if (vobEx.getVoBggzfhxx() != null) {
            for (int a = 0; a < vobEx.getVoBggzfhxx().length; a++) {
              bggzfhxxList.add(vobEx.getVoBggzfhxx()[a]);
            }
          } //if(vobEx!=null)
          if (vobEx.getVoHcygxtzfhxx() != null) {
            for (int a = 0; a < vobEx.getVoHcygxtzfhxx().length; a++) {
              hcygxtzfhxxList.add(vobEx.getVoHcygxtzfhxx()[a]);
            }
          }
        } //if(vobEx!=null)
      }
      
      //????????????
      if(voSbjbxx!=null && voSbjbxx.getHzywid()!=null){
          List list = super.findEntities("from PoHZ_ZJ_SB a where a.id in (" + voSbjbxx.getHzywid()+") ");
          if(list.size()>0) {
        	  PoHZ_ZJ_SB sb = (PoHZ_ZJ_SB)list.get(0);
        	  String kzz = super.getXTKZCS("10031");
        	  String kzz1 = super.getXTKZCS("10032");
        	  String kzz2 = super.getXTKZCS("10037");
              //1.??????????????????   2.wx_code????????????    add by zjm 20191106 
                if(kzz.equals("1")) {//????????????1??????????????????????????????????????????HZPT_RECEIVESTATE????????????
              	  if(CommonUtil.isNotEmpty(sb.getWx_code())) {
              		  updateHzptReceiveState(kzz,sb);
              	  }
                }
                if(kzz1.equals("1")) {//????????????1??????????????????????????????????????????HZPT_RECEIVESTATE????????????
              	  if(CommonUtil.isNotEmpty(sb.getWx_code())) {
              		  insertEms(kzz1,sb);
              	  }
                }
                
                for(int i = 0;i<list.size();i++){
                    PoHZ_ZJ_SB sb1 = (PoHZ_ZJ_SB)list.get(i);
                    sb1.setClbs("1");
                    sb1.setHjywid(hjywid);
                    sb1.setBlrsfz(this.getUser().getGmsfhm());
                    sb1.setClsj(CommonUtil.getTimestamp(new Date()));
                   super.update(sb1);
                   if(kzz2.equals("1")) {//add by zjm 20200710 ????????????2????????????????????????????????????
                 		autoInsertHzjsbs(kzz2,sb1);
                   }
                  }
          }
         
         
      }

      //////////////////////////////////////////
      //????????????????????????
      voSwzxfhxx = new VoSwzxfhxx[voSwzxxx.length];
      voZxhxx = new VoZxhxx[voSwzxxx.length];
      voHcygxtzxxEx = new VoHcygxtzxxEx[voSwzxxx.length];
      for (int i = 0; i < voSwzxxx.length; i++) {
        //??????????????????
        PoHJXX_CZRKJBXXB poRyxx  = super.get(PoHJXX_CZRKJBXXB.class,
            voSwzxxx[i].getRynbid());
        if (poRyxx == null) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "?????????????????????????????????????????????????????????????????????", null);
        }
        //???????????????????????????
        this.checkRXX(poRyxx, hjxx_czrkjbxxbDAO, "??????????????????");
        //??????????????????
        if (!HjConstant.RYSDZT_ZC.equals(poRyxx.getRysdzt())) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "??????????????????????????????(?????????)????????????????????????????????????", null);
        }
        //????????????
        if (!HjConstant.RYZT_ZC.equals(poRyxx.getRyzt())) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "????????????????????????????????????????????????????????????", null);
        }
        //???????????????
        PoHJXX_HXXB poHxx  = super.get(PoHJXX_HXXB.class,poRyxx.getHhnbid());
        if (poHxx == null) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "?????????????????????????????????????????????????????????????????????????????????", null);
        }
        //?????????????????????
        PoHJXX_MLPXXXXB poMlpxxxx  = super.get(PoHJXX_MLPXXXXB.class,
            poRyxx.getMlpnbid());
        if (poMlpxxxx == null) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "?????????????????????????????????????????????????????????????????????????????????", null);
        }
        poJwhxxb  = super.get(PoXT_JWHXXB.class,poMlpxxxx.getJcwh());
        if (poJwhxxb == null) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "?????????????????????????????????????????????????????????", null);
        }


        //??????????????????
        List sjfwList = new ArrayList();
        VoXtsjfw voXtsjfw = new VoXtsjfw();
        voXtsjfw.setSjfw(poMlpxxxx.getJcwh());
        voXtsjfw.setSjfwbz(PublicConstant.XT_QX_JWH);
        sjfwList.add(voXtsjfw);
        boolean bLimit = false;
        bLimit = XtywqxServiceImpl.VerifyDataRange(this.getUserInfo().getYhid().
            toString(),
            PublicConstant.GNBH_HJ_SWZXYW, sjfwList);
        if (!bLimit) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLIMIT,
        		  CommonUtil.getSjfwError(sjfwList)  + "????????????????????????????????????", null);
        }

        //?????????????????????????????????
        poRyxx.setJssj(now);
        poRyxx.setCchjywid(hjywid);
        poRyxx.setJlbz(PublicConstant.JLBZ_LS);
       super.update(poRyxx);
       //add by zjm 20190828 ????????????  ??????yhzgx???????????????????????????????????????hyzt????????????
//       if(poRyxx.getYhzgx().equals("11")||poRyxx.getYhzgx().equals("12")||poRyxx.getYhzgx().equals("01")||poRyxx.getYhzgx().equals("02")||poRyxx.getYhzgx().equals("03")) {//11???12??? 01 02 03 ??????
//    	   String strPoHQL ="";
//    	   if(poRyxx.getYhzgx().equals("11")||poRyxx.getYhzgx().equals("12")) {
//    		   strPoHQL = "from PoHJXX_CZRKJBXXB  where hhnbid='" +poRyxx.getHhnbid()+"' and yhzgx in ('01','02','03') and jlbz = '1' and  cxbz = '0' ";
//    	   }else if(poRyxx.getYhzgx().equals("01")||poRyxx.getYhzgx().equals("02")||poRyxx.getYhzgx().equals("03")) {
//    		   strPoHQL = "from PoHJXX_CZRKJBXXB  where hhnbid='" +poRyxx.getHhnbid()+"' and yhzgx in ('11','12')  and jlbz = '1' and cxbz = '0' ";
//    	   }
//    	   
//    	   List poList =super.findAllByHQL(strPoHQL);
//           if (poList != null) {
//             for (int j = 0; j < poList.size(); j++) {
//            	 PoHJXX_CZRKJBXXB poPoCzrkjbxxb = (PoHJXX_CZRKJBXXB) poList.get(j);
//            	 poPoCzrkjbxxb.setHyzk("30");;
//            	 super.update(poPoCzrkjbxxb);
//             }
//           }
//       }
        //??????????????????????????????
        PoHJXX_CZRKJBXXB poRyxxNew = new PoHJXX_CZRKJBXXB();
        BeanUtils.copyProperties(poRyxxNew, poRyxx);
        BeanUtils.copyProperties(poRyxxNew, voSwzxxx[i]);
        poRyxxNew.setRynbid( (Long) hjxx_czrkjbxxbDAO.getId());
        //??????poRyxxNew??????????????????begin
//        if (voBggzxxEx != null) {
//          for (int k = 0; k < voBggzxxEx.length; k++) {
//            if (voSwzxxx[i].getRynbid().equals(voBggzxxEx[k].getRynbid())) {
//              voBggzxxEx[k].setFlag(0); ////?????????????????????????????????????????????????????????(0-?????????/1-??????)
//              VoBggzfhxxEx vobEx = this.disposalBggzxx(hjywid, voSbjbxx,
//                  voBggzxxEx[k], poRyxxNew, PublicConstant.GNBH_HJ_SWZXYW, now);
//              if (vobEx != null) {
//                if (vobEx.getVoBggzfhxx() != null) {
//                  for (int a = 0; a < vobEx.getVoBggzfhxx().length; a++) {
//                    bggzfhxxList.add(vobEx.getVoBggzfhxx()[a]);
//                  }
//                } //if(vobEx!=null)
//                if (vobEx.getVoHcygxtzfhxx() != null) {
//                  for (int a = 0; a < vobEx.getVoHcygxtzfhxx().length; a++) {
//                    hcygxtzfhxxList.add(vobEx.getVoHcygxtzfhxx()[a]);
//                  }
//                }
//              } //if(vobEx!=null)
//            } //if(voHbbgxx[i].getRynbid().equals(voBggzxx[k].getRynbid()))
//          } //for(int k=0;k<voBggzxx.length;k++)
//        }
        //end
        poRyxxNew.setSwzxrq(now.substring(0, 8));
        //add by zjm 20200106  zxsj?????????swzxsj????????????swsj 
//        poRyxxNew.setZxsj(now.substring(0, 8));???????????????????????????????????????????????????
        poRyxxNew.setRyzt(HjConstant.RYZT_SW);
        poRyxxNew.setQysj(now);
        poRyxxNew.setJssj(null);
        poRyxxNew.setXxqysj(now);//add by hb 20061027
        poRyxxNew.setYwnr(HjConstant.YWNR_SWZX);
        poRyxxNew.setCchjywid(null);
        poRyxxNew.setCjhjywid(hjywid);
        poRyxxNew.setJlbz(PublicConstant.JLBZ_ZX);
       super.create(poRyxxNew);

        //??????????????????
        VoRhdxx voRhdxx = new VoRhdxx(poRyxxNew, poHxx, poMlpxxxx,
                                      BaseContext.getUser());
        VoXtywxz voLimit = XtywqxServiceImpl.VerifyBusinessLimit(PublicConstant.
            GNBH_HJ_SWZXYW, voRhdxx);
        if (voLimit.getLimitflag()) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLIMIT,
                                     "??????????????????????????????????????????" + voLimit.getLimitinfo(), null);
        }
        voRhdxx = null;

        //????????????????????????
        PoHJYW_SWZXXXB poSwzxxx = new PoHJYW_SWZXXXB();
        if (voSbjbxx != null) {
          BeanUtils.copyProperties(poSwzxxx, voSbjbxx);
        }
        BeanUtils.copyProperties(poSwzxxx, voSwzxxx[i]);
        BeanUtils.copyProperties(poSwzxxx, poRyxxNew);
        poSwzxxx.setHhid(poHxx.getHhid());
        poSwzxxx.setMlpid(poMlpxxxx.getMlpid());
        //??????????????????
        poSwzxxx.setSwnl(String.valueOf(this.calcSWNL(poRyxx.getCsrq(),
            voSwzxxx[i].getSwrq())));
        poSwzxxx.setSwzxid( (Long) hjyw_swzxxxbDAO.getId());
        poSwzxxx.setCxbz(PublicConstant.CXBZ_FCX);
        poSwzxxx.setHjywid(hjywid);
        poSwzxxx.setSldw(this.getUserInfo().getDwdm());
        poSwzxxx.setSlrid(this.getUserInfo().getYhid());
        poSwzxxx.setSlsj(now);
       super.create(poSwzxxx);

        PoHJXX_CXSXBGB log = new PoHJXX_CXSXBGB();
        log.setBghcxsx(poJwhxxb.getCxfldm());
        log.setBgqcxsx(poJwhxxb.getCxfldm());
        log.setCjsj(now);
        log.setSldw(poJwhxxb.getDwdm());
        log.setBgqdw(poJwhxxb.getDwdm());
        log.setRynbid(poSwzxxx.getRynbid());
        log.setHjywid(poSwzxxx.getHjywid());
        log.setBgyy(poSwzxxx.getSwzxlb());
        log.setYwlb("swzx");
        log.setBz("????????????");
        log.setRkbj("0");
        log.setSsxq(poSwzxxx.getSsxq());
        log.setJwhdm(poJwhxxb.getDm());
        log.setRysl(new Long(1));
       super.create(log);

        //??????????????????????????????????????????????????????
        voHcygxtzxxEx[i] = new VoHcygxtzxxEx();
        voHcygxtzxxEx[i].setFlag(0);
        voHcygxtzxxEx[i].setHcybdlx(HjConstant.HCYBDLX_LH);
        voHcygxtzxxEx[i].setHcybdlb(voSwzxxx[i].getSwzxlb());
        voHcygxtzxxEx[i].setHhnbid(poRyxxNew.getHhnbid());
        voHcygxtzxxEx[i].setRynbid(poRyxx.getRynbid());
        voHcygxtzxxEx[i].setNew_rynbid(poRyxxNew.getRynbid());
        voHcygxtzxxEx[i].setRyid(poRyxxNew.getRyid());
        voHcygxtzxxEx[i].setXm(poRyxxNew.getXm());
        voHcygxtzxxEx[i].setGmsfhm(poRyxxNew.getGmsfhm());
        voHcygxtzxxEx[i].setOld_yhzgx(null);
        voHcygxtzxxEx[i].setYhzgx(poRyxxNew.getYhzgx());
        voHcygxtzxxEx[i].setSbhjywid(null);

        //?????????????????????
        voZxhxx[i] = new VoZxhxx();
        voZxhxx[i].setNew_hhnbid(null);
        voZxhxx[i].setOld_hhnbid(poRyxx.getHhnbid());

        //????????????????????????
        String strHQL = new String();
        strHQL = "from PoHJXX_RHFLXXB as HJXX_RHFLXXB where rynbid=" +
            voSwzxxx[i].getRynbid().toString();
        List rhflxxList =super.findAllByHQL(strHQL);
        if (rhflxxList != null) {
          for (int j = 0; j < rhflxxList.size(); j++) {
            PoHJXX_RHFLXXB poRhflxx = (PoHJXX_RHFLXXB) rhflxxList.get(j);
            poRhflxx.setCchjywid(hjywid);
            poRhflxx.setRhflzt(HjConstant.RHFLZT_HJSW);
           super.update(poRhflxx);
          }
        }

        //??????????????????
        strHQL = "from PoHJYW_CHCLXXB as HJYW_CHCLXXB where clfs='" +
            HjConstant.CHCLFS_WCL + "' and chsfhm='" + poRyxx.getGmsfhm() +
            "' and cxbz='" + PublicConstant.CXBZ_FCX + "' ";
        List chxxList =super.findAllByHQL(strHQL);
        if (chxxList != null) {
          for (int j = 0; j < chxxList.size(); j++) {
            PoHJYW_CHCLXXB poChclxx = (PoHJYW_CHCLXXB) chxxList.get(j);
            //????????????
            if (poRyxx.getRyid().equals(poChclxx.getRyid())) {
              poChclxx.setClfs(HjConstant.CHCLFS_BRSW);
              poChclxx.setClhjywid(hjywid);
             super.update(poChclxx);
            }
            //??????????????????
            if (poRyxx.getRyid().equals(poChclxx.getBchryid())) {
              poChclxx.setClfs(HjConstant.CHCLFS_DFSW);
              poChclxx.setClhjywid(hjywid);
             super.update(poChclxx);
            }
          } //for (int j = 0; j < chxxList.size(); j++)
        }

        //????????????????????????(??????)
        VoRybdxx voRybdxx = new VoRybdxx();
        voRybdxx.setBdbid(poSwzxxx.getSwzxid());
        voRybdxx.setBdyy(poSwzxxx.getSwzxlb());
        voRybdxx.setBdrq(now.substring(0, 8)); //voRybdxx.setBdrq(poSwzxxx.getSwrq());
        voRybdxx.setBdqhb(null);
        voRybdxx.setBdq_hhnbid(poRyxx.getHhnbid());
        voRybdxx.setBdh_hhnbid(null);
        voRybdxx.setRynbid(poRyxxNew.getRynbid());
        voRybdxx.setRzjs(new Long( -1));
        voRybdxx.setHzjs(new Long(0));
        rybdxxList.add(voRybdxx);

        //??????????????????????????????
        voSwzxfhxx[i] = new VoSwzxfhxx();
        voSwzxfhxx[i].setHhnbid(poRyxxNew.getHhnbid());
        voSwzxfhxx[i].setRynbid(poRyxxNew.getRynbid());
        voSwzxfhxx[i].setRyid(poRyxxNew.getRyid());
        voSwzxfhxx[i].setOld_rynbid(poRyxx.getRynbid());
        voSwzxfhxx[i].setSwzxid(poSwzxxx.getSwzxid());
        voSwzxfhxx[i].setGmsfhm(poRyxxNew.getGmsfhm());
        voSwzxfhxx[i].setXm(poRyxxNew.getXm());
        voSwzxfhxx[i].setYhzgx(poRyxxNew.getYhzgx());
      }

      ////////////////////////////////////////
      //?????????????????????
      VoHcygxtzfhxx voh[] = this.adjustHCYGX(hjywid, voSbjbxx,
                                             voHcygxtzxxEx, now);
      if (voh != null) {
        for (int i = 0; i < voh.length; i++) {
          hcygxtzfhxxList.add(voh[i]);
        }
      }

      ////////////////////////////////////////


      ///////////////////////////////////////////
      //????????????????????????
      List zxhhnbidList = this.logoutH(hjywid, voZxhxx, voSwzxxx[0].getBdfw(),
                                       voSwzxxx[0].getSwzxlb(),
                                       HjConstant.YWNR_SWZX, now);

      /////////////////////////////////////////////
      //??????????????????
      Map hhnbidMap = new HashMap();
      for (int h = 0; h < hcygxtzfhxxList.size(); h++) {
        VoHcygxtzfhxx vo = (VoHcygxtzfhxx) hcygxtzfhxxList.get(h);
        hhnbidMap.put(vo.getHhnbid(), null);
      }
      //???????????????????????????ID
      if (zxhhnbidList != null) {
        for (int h = 0; h < zxhhnbidList.size(); h++) {
          hhnbidMap.remove(zxhhnbidList.get(h));
        }
      }
      for (Iterator itr = hhnbidMap.keySet().iterator(); itr.hasNext(); ) {
        this.checkHCYXX( (Long) itr.next());
      }

      ////////////////////////////////////////
      //??????????????????????????????
      PoHJLS_HJYWLSB poHjywlsxx = this.saveHJYWLSXX(hjywid,
          PublicConstant.GNBH_HJ_SWZXYW,
          zxhhnbidList != null && zxhhnbidList.size() > 0 ?
          PublicConstant.HJYWLS_YWLX_QH :
          PublicConstant.HJYWLS_YWLX_GR,
          voSwzxxx.length, voSbjbxx, now);

      //////////////////////////////////////////
      //????????????????????????(??????)
      if (zxhhnbidList != null && zxhhnbidList.size() > 0) {
        for (int a = 0; a < zxhhnbidList.size(); a++) {
          Long hhnbid = (Long) zxhhnbidList.get(a);
          for (int b = 0; b < rybdxxList.size(); b++) {
            VoRybdxx vo = (VoRybdxx) rybdxxList.get(b);
            if (hhnbid.equals(vo.getBdq_hhnbid())) {
              vo.setHzjs(new Long( -1));
              break;
            }
          }
        }
      }
      this.saveRYBDXX(rybdxxList, poHjywlsxx);

      ///////////////////////////////////////////
      //??????????????????????????????????????????????????????????????????????????????
      PoHJXX_CZRKJBXXB poHzxx = null;
      String strHQL = "from PoHJYW_SWZXXXB where hjywid=" + hjywid;
      List swzxxxList =super.findAllByHQL(strHQL);
      if (swzxxxList != null && swzxxxList.size() > 0) {
        for (int i = 0; i < swzxxxList.size(); i++) {
          PoHJYW_SWZXXXB poSwzxxx = (PoHJYW_SWZXXXB) swzxxxList.get(i);
          if (poHzxx == null) {
            strHQL = "from PoHJXX_CZRKJBXXB where yhzgx < '10' and jlbz='" +
                PublicConstant.JLBZ_ZX + "' and cxbz='" +
                PublicConstant.CXBZ_FCX +
                "' and hhnbid =" + poSwzxxx.getHhnbid();
            List hzxxList =super.findAllByHQL(strHQL);
            poHzxx = this.findHZXX(hzxxList);
          }
          poSwzxxx.setHzgmsfhm(poHzxx != null ? poHzxx.getGmsfhm() : null);
          poSwzxxx.setHzxm(poHzxx != null ? poHzxx.getXm() : null);
          poSwzxxx.setYwlx(poHjywlsxx.getYwlx());
          poSwzxxx.setCzsm(poHjywlsxx.getCzsm());
         super.update(poSwzxxx);
        }
      }

      ////////////////////////////////////
      //??????????????????
      voSwzxywfhxx = new VoSwzxywfhxx();
      voSwzxywfhxx.setHjywid(hjywid);
      voSwzxywfhxx.setVoSwzxfhxx(voSwzxfhxx);
      voSwzxywfhxx.setVoBggzfhxx( (VoBggzfhxx[]) bggzfhxxList.toArray(new
          VoBggzfhxx[bggzfhxxList.size()]));
      voSwzxywfhxx.setVoHcygxtzfhxx( (VoHcygxtzfhxx[]) hcygxtzfhxxList.toArray(new
          VoHcygxtzfhxx[hcygxtzfhxxList.size()]));

      ///////////////////////////////////////////
      //????????????
    }
    catch (DAOException ex) {
      //????????????
      throw ex;
    }
    catch (ServiceException ex) {
      //????????????
      throw ex;
    }
    catch (InvocationTargetException ex) {
      //????????????
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }
    catch (IllegalAccessException ex) {
      //????????????
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }catch (Exception ex) {
      //????????????
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }

    return voSwzxywfhxx;
  }

  /**
   * ????????????????????????
   *
   * @param voSbjbxx - ??????????????????
   * @param voBggzxx[] - ??????????????????
   * @return VoBggzywfhxx
   */
  public VoBggzywfhxx processBggzyw(VoSbjbxx voSbjbxx, VoBggzxxEx voBggzxxEx[]) throws
      ServiceException,
      DAOException {

    VoBggzywfhxx voBggzywfhxx = null;
    VoBggzfhxxEx voBggzfhxxEx = null;
    Long hjywid = null;
    List bggzfhxxList = new ArrayList();
    List hcygxtzfhxxList = new ArrayList();
    String now = StringUtils.getServiceTime();

    if (voBggzxxEx == null || (voBggzxxEx != null && voBggzxxEx.length <= 0)) {
      return null;
    }

    try {
      PojoInfo  hjxx_czrkjbxxbDAO = DAOFactory.createHJXX_CZRKJBXXBDAO();
      PojoInfo  hjxx_mlpxxxxbDAO = DAOFactory.createHJXX_MLPXXXXBDAO();
      PojoInfo  hjls_hjywlsbDAO = DAOFactory.createHJLS_HJYWLSBDAO();

      ////////////////////////////////////////
      //??????????????????ID
      hjywid = (Long) hjls_hjywlsbDAO.getId();
      
      ///////////////////////////////////////
      //????????????
      // modify by zjm 20190716 ?????????id??????????????????pch??????
      if(voSbjbxx!=null && voSbjbxx.getHzywid()!=null){
    	//modify by zjm 20190716 ???????????????????????????????????????clbs??????
    	updateHzyw(voSbjbxx.getHzywid(),hjywid,0l);
        //List list = super.findAllByHQL("from PoHZ_ZJ_SB a where a.id=" + voSbjbxx.getHzywid());
//        if(list.size()>0){
//          PoHZ_ZJ_SB sb = (PoHZ_ZJ_SB)list.get(0);
//          sb.setClbs("1");
//          sb.setClsj(new java.sql.Timestamp(new Date().getTime()));
//          super.update(sb);
//        }
      }


      /////////////////////////////////////////////////
      //????????????????????????
      for (int i = 0; i < voBggzxxEx.length; i++) {

        //??????????????????
        PoHJXX_CZRKJBXXB poRyxx  = super.get(PoHJXX_CZRKJBXXB.class,
            voBggzxxEx[i].getRynbid());
        if (poRyxx == null) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "??????????????????????????????????????????????????????????????????????????????", null);
        }

        //???????????????????????????
        this.checkRXX(poRyxx, hjxx_czrkjbxxbDAO, "??????????????????");
        //??????????????????
        if (!HjConstant.RYSDZT_ZC.equals(poRyxx.getRysdzt())) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "??????????????????????????????(?????????)????????????????????????????????????", null);
        }
        // add by hh 20051207 ?????????????????????????????????????????????
        boolean bZxbgFlag = false;
        for (int j = 0; j < voBggzxxEx[i].getBggzxxList().size(); j++) {
          VoBggzxx vo = (VoBggzxx) voBggzxxEx[i].getBggzxxList().get(j);
          if ("gmsfhm".equalsIgnoreCase(vo.getBggzxm()) ||
              "xm".equalsIgnoreCase(vo.getBggzxm())) {
            bZxbgFlag = true;
            break;
          }
        }
        PoHJXX_CZRKJBXXB poRyxxNew = null;
        VoBggzfhxxEx vo = null;
        if (bZxbgFlag) { //?????????????????????????????????????????????,?????????????????????

          poRyxx.setJssj(now);
          poRyxx.setCchjywid(hjywid);
          poRyxx.setJlbz(PublicConstant.JLBZ_LS);
         super.update(poRyxx);

          poRyxxNew = new PoHJXX_CZRKJBXXB();
          BeanUtils.copyProperties(poRyxxNew, poRyxx);
          poRyxxNew.setRynbid( (Long) hjxx_czrkjbxxbDAO.getId());
          vo = this.disposalBggzxx(hjywid, voSbjbxx, voBggzxxEx[i],
                                   poRyxxNew,
                                   PublicConstant.GNBH_HJ_BGGZYW, now);
        }
        else {
          /////////////////////////////////////////////////////////
          //????????????????????????
          vo = this.disposalBggzxx(hjywid, voSbjbxx, voBggzxxEx[i],
                                   poRyxx,
                                   PublicConstant.GNBH_HJ_BGGZYW, now);
        }
        if (vo != null) {
          VoBggzfhxx vob[] = vo.getVoBggzfhxx();
          VoHcygxtzfhxx voh[] = vo.getVoHcygxtzfhxx();
          if (vob != null) {
            for (int k = 0; k < vob.length; k++) {
              bggzfhxxList.add(vob[k]);
            }
          }
          if (voh != null) {
            for (int k = 0; k < voh.length; k++) {
              hcygxtzfhxxList.add(voh[k]);
            }
          }
        } //if(vo!=null)
        if (bZxbgFlag) {
          //??????????????????????????????????????????
          poRyxxNew.setQysj(now);
          poRyxxNew.setJssj(null);
          poRyxxNew.setXxqysj(now);//add by hb 20061027
          poRyxxNew.setYwnr(HjConstant.YWNR_BGGZ);
          poRyxxNew.setCchjywid(null);
          poRyxxNew.setCjhjywid(hjywid);
          poRyxxNew.setJlbz(PublicConstant.JLBZ_ZX);
         super.create(poRyxxNew);
        }
        else {
          //poRyxx.setXxqysj(now);//add by hb 20061027
          //??????????????????????????????
         super.create(poRyxx);
        }
      } //for (int i = 0; i < voBggzxx.length; i++)

      //////////////////////////////////////////////
      //??????????????????
      Map hhnbidMap = new HashMap();
      for (int h = 0; h < hcygxtzfhxxList.size(); h++) {
        VoHcygxtzfhxx vo = (VoHcygxtzfhxx) hcygxtzfhxxList.get(h);
        hhnbidMap.put(vo.getHhnbid(), null);
      }
      for (Iterator itr = hhnbidMap.keySet().iterator(); itr.hasNext(); ) {
        this.checkHCYXX( (Long) itr.next());
      }

      ////////////////////////////////////////
      //??????????????????????????????
      this.saveHJYWLSXX(hjywid, PublicConstant.GNBH_HJ_BGGZYW,
                        PublicConstant.HJYWLS_YWLX_GR,
                        voBggzxxEx.length, voSbjbxx, now);

      //////////////////////////////////////////////////
      //??????????????????
      voBggzywfhxx = new VoBggzywfhxx();
      voBggzywfhxx.setHjywid(hjywid);
      voBggzywfhxx.setVoBggzfhxx( (VoBggzfhxx[]) bggzfhxxList.toArray(new
          VoBggzfhxx[bggzfhxxList.size()]));
      voBggzywfhxx.setVoHcygxtzfhxx( (VoHcygxtzfhxx[]) hcygxtzfhxxList.
                                    toArray(new
                                            VoHcygxtzfhxx[hcygxtzfhxxList.
                                            size()]));

      ///////////////////////////////////////////////
      //????????????
    }
    catch (DAOException ex) {
      //????????????
      throw ex;
    }
    catch (ServiceException ex) {
      //????????????
      throw ex;
    }
    catch (Exception ex) {
      //????????????
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }

    return voBggzywfhxx;
  }

  /**
   * ????????????????????????
   *
   * @param voSbjbxx - ??????????????????
   * @param voHbbgxx[] - ??????????????????
   * @param voBggzxx[] - ??????????????????
   * @return Vohbbgywfhxx
   */
  public VoHbbgywfhxx processHbbgyw(VoSbjbxx voSbjbxx, VoHbbgxx voHbbgxx[],
                                    VoBggzxxEx voBggzxxEx[]) throws
      ServiceException,
      DAOException {

    VoHbbgywfhxx voHbbgywfhxx = null;
    VoHbbgfhxx voHbbgfhxx[] = null;
    List bggzfhxxList = new ArrayList();
    List hcygxtzfhxxList = new ArrayList();
    List rybdxxList = new ArrayList();
    Long hjywid = null;
    String now = StringUtils.getServiceTime();
    PoXT_JWHXXB poJwhxxb = null;
    if (voSbjbxx == null || voHbbgxx == null ||
        (voHbbgxx != null && voHbbgxx.length <= 0)) {
      return null;
    }

    try {
      PojoInfo  hjxx_czrkjbxxbDAO = DAOFactory.
          createHJXX_CZRKJBXXBDAO();
      PojoInfo  hjxx_hxxbDAO = DAOFactory.createHJXX_HXXBDAO();
      PojoInfo  hjls_hjywlsbDAO = DAOFactory.createHJLS_HJYWLSBDAO();
      PojoInfo  hjyw_hbbgxxbDAO = DAOFactory.createHJYW_HBBGXXBDAO();
      PojoInfo  hjxx_mlpxxxxbDAO = DAOFactory.createHJXX_MLPXXXXBDAO();
      PojoInfo  xt_jwhxxbDAO = DAOFactory.createXT_JWHXXBDAO();
      ///////////////////////////////////////////
      //????????????

      /////////////////////////////////////
      //??????????????????ID
      hjywid = (Long) hjls_hjywlsbDAO.getId();

      ////////////////////////////////////////
      //????????????????????????
      voHbbgfhxx = new VoHbbgfhxx[voHbbgxx.length];
      for (int i = 0; i < voHbbgxx.length; i++) {
        Long rynbid = null;
        Long hbbgid = null;

        //??????????????????
        PoHJXX_CZRKJBXXB poRyxx  = super.get(PoHJXX_CZRKJBXXB.class,
            voHbbgxx[i].
            getRynbid());
        if (poRyxx == null) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "?????????????????????????????????????????????????????????????????????", null);
        }
        //???????????????????????????
        this.checkRXX(poRyxx, hjxx_czrkjbxxbDAO, "??????????????????");
        //??????????????????
        if ( (voHbbgxx[i].getSpywid() == null &&
              !HjConstant.RYSDZT_ZC.equals(poRyxx.getRysdzt())) ||
            (voHbbgxx[i].getSpywid() != null &&
             !HjConstant.RYSDZT_HBBGSP.equals(poRyxx.getRysdzt()))) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "?????????" + poRyxx.getGmsfhm() + "???????????????????????????(?????????)????????????????????????????????????", null);
        }
        //????????????
        if (!HjConstant.RYZT_ZC.equals(poRyxx.getRyzt())) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "?????????" + poRyxx.getGmsfhm() + "?????????????????????????????????????????????????????????", null);
        }
        //???????????????
        PoHJXX_HXXB poHxx  = super.get(PoHJXX_HXXB.class,poRyxx.getHhnbid());
        if (poHxx == null) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "??????????????????????????????" + poRyxx.getGmsfhm() + "?????????????????????????????????????????????????????????", null);
        }
        //?????????????????????
        PoHJXX_MLPXXXXB poMlpxxxx  = super.get(PoHJXX_MLPXXXXB.class,
            poRyxx.getMlpnbid());
        if (poMlpxxxx == null) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "??????????????????" + poRyxx.getGmsfhm() + "?????????????????????????????????????????????????????????????????????", null);
        }
        poJwhxxb  = super.get(PoXT_JWHXXB.class,poMlpxxxx.getJcwh());
        if (poJwhxxb == null) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "?????????????????????????????????????????????????????????", null);
        }

        //??????????????????
        List sjfwList = new ArrayList();
        VoXtsjfw voXtsjfw = new VoXtsjfw();
        voXtsjfw.setSjfw(poMlpxxxx.getJcwh());
        voXtsjfw.setSjfwbz(PublicConstant.XT_QX_JWH);
        sjfwList.add(voXtsjfw);
        boolean bLimit = false;
        bLimit = XtywqxServiceImpl.VerifyDataRange(this.getUserInfo().getYhid().
            toString(),
            PublicConstant.GNBH_HJ_HBBGYW, sjfwList);
        if (!bLimit) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLIMIT,
        		  CommonUtil.getSjfwError(sjfwList)  + "????????????????????????????????????", null);
        }

        //?????????????????????????????????
        poRyxx.setRysdzt(HjConstant.RYSDZT_ZC);
        poRyxx.setJssj(now);
        poRyxx.setCchjywid(hjywid);
        poRyxx.setJlbz(PublicConstant.JLBZ_LS);

       super.update(poRyxx);
        //??????????????????????????????
        PoHJXX_CZRKJBXXB poRyxxNew = new PoHJXX_CZRKJBXXB();
        BeanUtils.copyProperties(poRyxxNew, poRyxx);
        rynbid = (Long) hjxx_czrkjbxxbDAO.getId();
        poRyxxNew.setHb(voHbbgxx[i].getBghhb());
        poRyxxNew.setJthzl(voHbbgxx[i].getJthzl());
        poRyxxNew.setRynbid(rynbid);
        //add by hh 20051207 ?????????????????????????????????11
        poRyxxNew.setBdfw(HjConstant.BDFW_HKLBZH);
        //??????poRyxxNew??????????????????begin
        if (voBggzxxEx != null) {
          for (int k = 0; k < voBggzxxEx.length; k++) {
            if (voHbbgxx[i].getRynbid().equals(voBggzxxEx[k].getRynbid())) {
              voBggzxxEx[k].setFlag( -1); ////?????????????????????????????????????????????????????????(-1-???????????????????????????????????????)/0-?????????/1-??????)
              VoBggzfhxxEx vobEx = this.disposalBggzxx(hjywid, voSbjbxx,
                  voBggzxxEx[k], poRyxxNew, PublicConstant.GNBH_HJ_HBBGYW, now);
              if (vobEx != null) {
                if (vobEx.getVoBggzfhxx() != null) {
                  for (int a = 0; a < vobEx.getVoBggzfhxx().length; a++) {
                    bggzfhxxList.add(vobEx.getVoBggzfhxx()[a]);
                  }
                } //if(vobEx!=null)
                if (vobEx.getVoHcygxtzfhxx() != null) {
                  for (int a = 0; a < vobEx.getVoHcygxtzfhxx().length; a++) {
                    hcygxtzfhxxList.add(vobEx.getVoHcygxtzfhxx()[a]);
                  }
                }
              } //if(vobEx!=null)
            } //if(voHbbgxx[i].getRynbid().equals(voBggzxx[k].getRynbid()))
          } //for(int k=0;k<voBggzxx.length;k++)
        }
        //end
        //?????????_begin(2004/09/25pm??????)
        poRyxxNew.setHylbz(voHbbgxx[i].getHbbglb());
        poRyxxNew.setHslbz(voHbbgxx[i].getHbbgrq());
        poRyxxNew.setHgjdqlbz(null);
        poRyxxNew.setHsssqlbz(poMlpxxxx.getSsxq());
        //???????????????
        poRyxxNew.setHxzlbz(this.generateZZ(poMlpxxxx,PublicConstant.XTKZCS_DZPZFS,PublicConstant.XTKZCS_DZPZFS_QCDXZ));
        //?????????_end
        poRyxxNew.setQysj(now);
        poRyxxNew.setJssj(null);
        poRyxxNew.setXxqysj(now);//add by hb 20061027
        poRyxxNew.setYwnr(HjConstant.YWNR_HBBG);
        poRyxxNew.setCchjywid(null);
        poRyxxNew.setCjhjywid(hjywid);
        poRyxxNew.setJlbz(PublicConstant.JLBZ_ZX);
       super.create(poRyxxNew); //???????????????

        //??????????????????
        VoRhdxx voRhdxx = new VoRhdxx(poRyxxNew, poHxx, poMlpxxxx, BaseContext.getUser());
        voRhdxx.setHbbglb(voHbbgxx[i].getHbbglb());
        VoXtywxz voLimit = XtywqxServiceImpl.VerifyBusinessLimit(PublicConstant.GNBH_HJ_HBBGYW, voRhdxx);
        if (voLimit.getLimitflag()) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLIMIT,
                                     "??????????????????????????????????????????" + voLimit.getLimitinfo(), null);
        }
        //??????????????????????????????
        if (voHbbgxx[i].getSpywid() == null) {
          voLimit = XtywqxServiceImpl.VerifyCheckLimit(PublicConstant.GNBH_HJ_HBBGYW, voRhdxx);
          if (voLimit.getLimitflag()) {
            throw new ServiceException(
            		WSErrCode.ERR_SERVICE_HBBGSXYSP,
                    "????????????????????????????????????????????????" + voLimit.getLimitinfo() + ";?????????????????????ID???" + voLimit.getSpmbid(), null);
          }
        }
        voRhdxx = null;

        //????????????????????????
        PoHJYW_HBBGXXB poHbbgxx = new PoHJYW_HBBGXXB();
        if (voSbjbxx != null) {
          BeanUtils.copyProperties(poHbbgxx, voSbjbxx);
        }
        BeanUtils.copyProperties(poHbbgxx, voHbbgxx[i]);
        BeanUtils.copyProperties(poHbbgxx, poRyxxNew);
        poHbbgxx.setHhid(poHxx.getHhid());
        poHbbgxx.setMlpid(poMlpxxxx.getMlpid());
        hbbgid = (Long) hjyw_hbbgxxbDAO.getId();
        poHbbgxx.setRynbid(poRyxxNew.getRynbid());
        poHbbgxx.setCxbz(PublicConstant.CXBZ_FCX);
        poHbbgxx.setHbbgid(hbbgid);
        poHbbgxx.setHjywid(hjywid);
        poHbbgxx.setBgqhb(poRyxx.getHb());
        poHbbgxx.setSldw(this.getUserInfo().getDwdm());
        poHbbgxx.setSlrid(this.getUserInfo().getYhid());
        poHbbgxx.setSlsj(now);
        //add by hh 20051207 ?????????????????????????????????11
        poHbbgxx.setBdfw(HjConstant.BDFW_HKLBZH);

       super.create(poHbbgxx);

        //????????????????????????(??????)
        VoRybdxx voRybdxx = new VoRybdxx();
        voRybdxx.setBdbid(poHbbgxx.getHbbgid());
        voRybdxx.setBdfw(poHbbgxx.getBdfw());
        voRybdxx.setBdyy(poHbbgxx.getHbbglb());
        voRybdxx.setBdrq(now.substring(0, 8)); //voRybdxx.setBdrq(poHbbgxx.getHbbgrq());
        voRybdxx.setBdqhb(poRyxx.getHb());
        voRybdxx.setBdq_hhnbid(poRyxx.getHhnbid());
        voRybdxx.setBdh_hhnbid(poRyxxNew.getHhnbid());
        voRybdxx.setRynbid(poRyxxNew.getRynbid());
        voRybdxx.setRzjs(new Long(0));
        voRybdxx.setHzjs(new Long(0));
        rybdxxList.add(voRybdxx);

        //????????????
        if (voHbbgxx[i].getSpywid() != null) {
          PojoInfo  hjsp_hbbgspsqbDAO = DAOFactory.
              createHJSP_HBBGSPSQBDAO();
          PoHJSP_HBBGSPSQB poHbbgspxx = super.get(PoHJSP_HBBGSPSQB.class,
              voHbbgxx[i].getSpywid());
          if (poHbbgspxx == null) {
            throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                       "????????????????????????????????????????????????????????????????????????????????????", null);
          }
          if (!HjConstant.SPJG_TY.equals(poHbbgspxx.getSpjg())) {
            throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                       "??????????????????????????????????????????????????????????????????", null);
          }
          poHbbgspxx.setHjywid(hjywid);
          poHbbgspxx.setBghrynbid(poRyxxNew.getRynbid());
          poHbbgspxx.setLsbz(HjConstant.LSBZ_YLS);
         super.update(poHbbgspxx);

          //?????????????????????????????????1
          this.decSPMBDQSYS(poHbbgspxx.getSpmbid());
        }

        //??????????????????????????????
        voHbbgfhxx[i] = new VoHbbgfhxx();
        voHbbgfhxx[i].setHbbgid(hbbgid);
        voHbbgfhxx[i].setHhnbid(poRyxxNew.getHhnbid());
        voHbbgfhxx[i].setRynbid(rynbid);
        voHbbgfhxx[i].setRyid(poRyxxNew.getRyid());
        voHbbgfhxx[i].setGmsfhm(poRyxxNew.getGmsfhm());
        voHbbgfhxx[i].setXm(poRyxxNew.getXm());
        voHbbgfhxx[i].setYhzgx(poRyxxNew.getYhzgx());
      }

      ////////////////////////////////////////
      //??????????????????
      if (voBggzxxEx != null) {
        VoBggzfhxxEx vobEx = this.saveBGGZXX(hjywid, voSbjbxx, voBggzxxEx,
                                             PublicConstant.GNBH_HJ_HBBGYW, now);
        if (vobEx != null) {
          if (vobEx.getVoBggzfhxx() != null) {
            for (int a = 0; a < vobEx.getVoBggzfhxx().length; a++) {
              bggzfhxxList.add(vobEx.getVoBggzfhxx()[a]);
            }
          } //if(vobEx!=null)
          if (vobEx.getVoHcygxtzfhxx() != null) {
            for (int a = 0; a < vobEx.getVoHcygxtzfhxx().length; a++) {
              hcygxtzfhxxList.add(vobEx.getVoHcygxtzfhxx()[a]);
            }
          }
        } //if(vobEx!=null)
      }

      ///////////////////////////////////////////
      //??????????????????
      Map hhnbidMap = new HashMap();
      for (int h = 0; h < hcygxtzfhxxList.size(); h++) {
        VoHcygxtzfhxx vo = (VoHcygxtzfhxx) hcygxtzfhxxList.get(h);
        hhnbidMap.put(vo.getHhnbid(), null);
      }
      for (Iterator itr = hhnbidMap.keySet().iterator(); itr.hasNext(); ) {
        this.checkHCYXX( (Long) itr.next());
      }

      ////////////////////////////////////////
      //??????????????????????????????
      PoHJLS_HJYWLSB poHjywlsxx = this.saveHJYWLSXX(hjywid,
          PublicConstant.GNBH_HJ_HBBGYW,
          PublicConstant.HJYWLS_YWLX_GR,
          voHbbgxx.length, voSbjbxx, now);

      //////////////////////////////////////////
      //????????????????????????(??????)
      this.saveRYBDXX(rybdxxList, poHjywlsxx);

      ///////////////////////////////////////////
      //????????????????????????????????????????????????????????????????????????
      PoHJXX_CZRKJBXXB poHzxx = null;
      String strHQL = "from PoHJYW_HBBGXXB where hjywid=" + hjywid;
      List hbbgxxList =super.findAllByHQL(strHQL);
      if (hbbgxxList != null && hbbgxxList.size() > 0) {
        for (int i = 0; i < hbbgxxList.size(); i++) {
          PoHJYW_HBBGXXB poHbbgxx = (PoHJYW_HBBGXXB) hbbgxxList.get(i);
          if (poHzxx == null) {
            strHQL = "from PoHJXX_CZRKJBXXB where yhzgx < '10' and ryzt='" +
                HjConstant.RYZT_ZC + "' and jlbz='" +
                PublicConstant.JLBZ_ZX + "' and cxbz='" +
                PublicConstant.CXBZ_FCX +
                "' and hhnbid =" + poHbbgxx.getHhnbid();
            List hzxxList =super.findAllByHQL(strHQL);
            if (hzxxList != null && hzxxList.size() > 0) {
              poHzxx = (PoHJXX_CZRKJBXXB) hzxxList.get(0);
            }
          }
          poHbbgxx.setHzgmsfhm(poHzxx != null ? poHzxx.getGmsfhm() : null);
          poHbbgxx.setHzxm(poHzxx != null ? poHzxx.getXm() : null);
          poHbbgxx.setYwlx(poHjywlsxx.getYwlx());
          poHbbgxx.setCzsm(poHjywlsxx.getCzsm());
         super.update(poHbbgxx);
        }
      }

      ////////////////////////////////////
      //????????????????????????????????????
      voHbbgywfhxx = new VoHbbgywfhxx();
      voHbbgywfhxx.setHjywid(hjywid);
      voHbbgywfhxx.setVoHbbgfhxx(voHbbgfhxx);
      voHbbgywfhxx.setVoBggzfhxx( (VoBggzfhxx[]) bggzfhxxList.toArray(new VoBggzfhxx[bggzfhxxList.size()]));
      voHbbgywfhxx.setVoHcygxtzfhxx( (VoHcygxtzfhxx[]) hcygxtzfhxxList.
                                    toArray(new VoHcygxtzfhxx[hcygxtzfhxxList.size()]));

      ///////////////////////////////////////////
      //????????????
    }
    catch (DAOException ex) {
      //????????????
      throw ex;
    }
    catch (ServiceException ex) {
      //????????????
      throw ex;
    }
    catch (InvocationTargetException ex) {
      //????????????
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }
    catch (IllegalAccessException ex) {
      //????????????
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }
    catch (Exception ex) {
      //????????????
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }

    return voHbbgywfhxx;
  }

  /**
   * ????????????????????????????????????
   *
   * @param voSfhmfpblxx[] - ??????????????????????????????
   * @return com.hzjc.hz2004.vo.VoBlsfhmfpywfhxx
   * @roseuid 406AD886013C
   */
  public VoSfhmfpblywfhxx processSfhmfpblyw(VoSfhmfpblxx
                                            voSfhmfpblxx[]) throws
      ServiceException,
      DAOException {

    VoSfhmfpblywfhxx voSfhmfpblywfhxx = null;
    VoSfhmfpblfhxx voSfhmfpblfhxx[] = null;
    Long hjywid = null;
    String strHQL = null;
    String now = StringUtils.getServiceTime();

    if (voSfhmfpblxx == null ||
        (voSfhmfpblxx != null && voSfhmfpblxx.length <= 0)) {
      return null;
    }

    try {
      PojoInfo  hjyw_gmsfhmsxmfpxxbDAO = DAOFactory.
          createHJYW_GMSFHMSXMFPXXBDAO();
      PojoInfo  hjls_hjywlsbDAO = DAOFactory.createHJLS_HJYWLSBDAO();

      ////////////////////////////////////////////
      //????????????

      ///////////////////////////////////////////////
      //????????????
      for (int i = 0; i < voSfhmfpblxx.length; i++) {
        VoXtywxz voLimit = XtywqxServiceImpl.VerifyBusinessLimit(
            PublicConstant.
            GNBH_HJ_SFHMFPBLYW, voSfhmfpblxx[i]);
        //????????????
        if (voLimit.getLimitflag()) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLIMIT,
                                     "??????????????????????????????????????????????????????" +
                                     voLimit.getLimitinfo(), null);
        }
      }

      ////////////////////////////////////////////
      //??????????????????ID
      hjywid = (Long) hjls_hjywlsbDAO.getId();

      ////////////////////////////////////////////
      //??????????????????????????????
      voSfhmfpblfhxx = new VoSfhmfpblfhxx[voSfhmfpblxx.length];
      for (int i = 0; i < voSfhmfpblxx.length; i++) {
        PoHJYW_GMSFHMSXMFPXXB poGmsfhmsxmfpxx = new PoHJYW_GMSFHMSXMFPXXB();
        BeanUtils.copyProperties(poGmsfhmsxmfpxx, voSfhmfpblxx[i]);
        if (!PID.IDCheck(voSfhmfpblxx[i].getGmsfhm())) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_DATANOTFULL,
                                     "????????????????????????????????????????????????????????????????????????", null);
        }

        //??????????????????
        strHQL = "select count(*) from PoHJYW_GMSFHMSXMFPXXB where gmsfhm='" +
            voSfhmfpblxx[i].getGmsfhm() + "' ";
        long iCount  = super.getCount(strHQL);
        if (iCount > 0) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_DATANOTFULL,
                                     "??????????????????(" + voSfhmfpblxx[i].getGmsfhm() +
                                     ")????????????????????????????????????????????????????????????????????????", null);
        }

        //????????????
        poGmsfhmsxmfpxx.setFpid( (Long) hjyw_gmsfhmsxmfpxxbDAO.getId());
        poGmsfhmsxmfpxx.setCsrq(voSfhmfpblxx[i].getGmsfhm().substring(6, 14));
        poGmsfhmsxmfpxx.setSxh(voSfhmfpblxx[i].getGmsfhm().substring(14, 17));
        poGmsfhmsxmfpxx.setCzlb(HjConstant.GMSFHMFPWS_CZLB_LR);
        poGmsfhmsxmfpxx.setHjywid(hjywid);
       super.create(poGmsfhmsxmfpxx);

        //??????????????????
        voSfhmfpblfhxx[i] = new VoSfhmfpblfhxx();
        voSfhmfpblfhxx[i].setFpid(poGmsfhmsxmfpxx.getFpid());
        voSfhmfpblfhxx[i].setRyid(null);
        voSfhmfpblfhxx[i].setGmsfhm(poGmsfhmsxmfpxx.getGmsfhm());
      }

      //??????????????????????????????
      this.saveHJYWLSXX(hjywid, PublicConstant.GNBH_HJ_SFHMFPBLYW,
                        PublicConstant.HJYWLS_YWLX_GR,
                        voSfhmfpblxx.length, null, now);

      ////////////////////////////////////
      //????????????????????????
      voSfhmfpblywfhxx = new VoSfhmfpblywfhxx();
      voSfhmfpblywfhxx.setHjywid(hjywid);
      voSfhmfpblywfhxx.setVoSfhmfpblfhxx(voSfhmfpblfhxx);

      ///////////////////////////////////////////////
      //????????????
    }
    catch (DAOException ex) {
      //????????????
      throw ex;
    }
    catch (ServiceException ex) {
      //????????????
      throw ex;
    }
    catch (InvocationTargetException ex) {
      //????????????
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }
    catch (IllegalAccessException ex) {
      //????????????
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }
    catch (Exception ex) {
      //????????????
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }

    return voSfhmfpblywfhxx;
  }

  /**
   * ????????????????????????????????????
   *
   * @param voSfhmfpblxx[] - ??????????????????????????????
   * @return com.hzjc.hz2004.vo.VoBlsfhmfpywfhxx
   * @roseuid 406AD886013C
   */
  public VoSfhmfpscywfhxx processSfhmfpscyw(VoSfhmfpscxx
                                            voSfhmfpscxx[]) throws
      ServiceException,
      DAOException {

    VoSfhmfpscywfhxx voSfhmfpscywfhxx = null;
    VoSfhmfpscfhxx voSfhmfpscfhxx[] = null;
    Long hjywid = null;
    String now = StringUtils.getServiceTime();

    if (voSfhmfpscxx == null ||
        (voSfhmfpscxx != null && voSfhmfpscxx.length <= 0)) {
      return null;
    }

    try {
      PojoInfo  hjyw_gmsfhmsxmfpxxbDAO = DAOFactory.
          createHJYW_GMSFHMSXMFPXXBDAO();
      PojoInfo  hjls_hjywlsbDAO = DAOFactory.createHJLS_HJYWLSBDAO();

      ////////////////////////////////////////////
      //????????????

      //////////////////////////////////////////////
      //????????????
      for (int i = 0; i < voSfhmfpscxx.length; i++) {
        VoXtywxz voLimit = XtywqxServiceImpl.VerifyBusinessLimit(
            PublicConstant.
            GNBH_HJ_SFHMFPSCYW, voSfhmfpscxx[i]);
        //????????????
        if (voLimit.getLimitflag()) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLIMIT,
                                     "??????????????????????????????????????????????????????" +
                                     voLimit.getLimitinfo(), null);
        }
      }

      ////////////////////////////////////////////
      //??????????????????ID
      hjywid = (Long) hjls_hjywlsbDAO.getId();

      ////////////////////////////////////////////
      //????????????????????????????????????
      voSfhmfpscfhxx = new VoSfhmfpscfhxx[voSfhmfpscxx.length];
      for (int i = 0; i < voSfhmfpscxx.length; i++) {
        PoHJYW_GMSFHMSXMFPXXB poGmsfhmsxmfpxx = super.
            get(PoHJYW_GMSFHMSXMFPXXB.class, voSfhmfpscxx[i].getFpid());
        if (poGmsfhmsxmfpxx == null) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_DATANOTFULL,
                                     "????????????????????????????????????????????????(" +
//                                     poGmsfhmsxmfpxx.getGmsfhm() +
                                     ")??????????????????????????????????????????????????????????????????", null);
        }
        if (this.countCHRYXX(poGmsfhmsxmfpxx.getGmsfhm()) > 0) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_DATANOTFULL,
                                     "????????????(" + poGmsfhmsxmfpxx.getGmsfhm() +
                                     ")????????????????????????????????????????????????????????????????????????????????????????????????", null);
        }
        super.delete(poGmsfhmsxmfpxx);

        //??????????????????
        voSfhmfpscfhxx[i] = new VoSfhmfpscfhxx();
        voSfhmfpscfhxx[i].setFpid(poGmsfhmsxmfpxx.getFpid());
      }

      ///////////////////////////////////////////////
      //??????????????????????????????
      this.saveHJYWLSXX(hjywid, PublicConstant.GNBH_HJ_SFHMFPSCYW,
                        PublicConstant.HJYWLS_YWLX_GR,
                        voSfhmfpscxx.length, null, now);

      ////////////////////////////////////
      //????????????????????????
      voSfhmfpscywfhxx = new VoSfhmfpscywfhxx();
      voSfhmfpscywfhxx.setHjywid(hjywid);
      voSfhmfpscywfhxx.setVoSfhmfpscfhxx(voSfhmfpscfhxx);

      ///////////////////////////////////////////////
      //????????????
    }
    catch (DAOException ex) {
      //????????????
      throw ex;
    }
    catch (ServiceException ex) {
      //????????????
      throw ex;
    }

    return voSfhmfpscywfhxx;
  }

  /**
   * ????????????????????????
   *
   * @param voQcclxx[] - ??????????????????
   * @return com.hzjc.hz2004.vo.VoQcclywfhxx
   * @roseuid 405FF10D02C7
   */
  public VoQcclywfhxx processQcclyw(VoQcclxx voQcclxx[]) throws
      ServiceException,
      DAOException {

    VoQcclywfhxx voQcclywfhxx = null;
    VoQcclfhxx voQcclfhxx[] = null;
    Long hjywid = null;
    String now = StringUtils.getServiceTime();

    if (voQcclxx == null || (voQcclxx != null && voQcclxx.length <= 0)) {
      return null;
    }

    try {
      PojoInfo  hjyw_qcclxxbDAO = DAOFactory.createHJYW_QCCLXXBDAO();
      PojoInfo  hjls_hjywlsbDAO = DAOFactory.createHJLS_HJYWLSBDAO();
      PojoInfo  hjxx_czrkjbxxbDAO = DAOFactory.createHJXX_CZRKJBXXBDAO();
      PojoInfo  hjxx_hxxbDAO = DAOFactory.createHJXX_HXXBDAO();
      PojoInfo  hjxx_mlpxxxxbDAO = DAOFactory.createHJXX_MLPXXXXBDAO();

      /////////////////////////////////////////
      //????????????

      /////////////////////////////////////
      //??????????????????ID
      hjywid = (Long) hjls_hjywlsbDAO.getId();

      ////////////////////////////////////////
      //????????????????????????
      voQcclfhxx = new VoQcclfhxx[voQcclxx.length];
      for (int i = 0; i < voQcclxx.length; i++) {
        PoHJYW_QCCLXXB poQcclxx = null;

        poQcclxx  = super.get(PoHJYW_QCCLXXB.class,voQcclxx[i].getQcclid());
        if (poQcclxx == null) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "????????????????????????????????????????????????????????????????????????", null);
        }

        if (HjConstant.QCCL_CLBZ_YCLBDY.equals(voQcclxx[i].getClbz())) {
          poQcclxx.setCgdyrq(now.substring(0, 8));
        }
        else {
          poQcclxx.setCgdyrq(null);
        }
        poQcclxx.setClbz(voQcclxx[i].getClbz());
        poQcclxx.setClrid(this.getUserInfo().getYhid());
        poQcclxx.setClrq(now.substring(0, 8));
       super.update(poQcclxx);

        //?????????????????????
        PoHJXX_HXXB poHxx = null;
        PoHJXX_MLPXXXXB poMlpxxxx = null;
        PoHJXX_CZRKJBXXB poRyxx = null;
        poRyxx  = super.get(PoHJXX_CZRKJBXXB.class,poQcclxx.getQqrynbid());
        if (poRyxx != null) {
          poHxx  = super.get(PoHJXX_HXXB.class,poRyxx.getHhnbid());
        }
        if (poHxx != null) {
          poMlpxxxx  = super.get(PoHJXX_MLPXXXXB.class,poHxx.getMlpnbid());
        }

        //????????????
        VoRhdxx voRhdxx = new VoRhdxx(poRyxx, poHxx, poMlpxxxx,
                                      BaseContext.getUser());
        VoXtywxz voLimit = XtywqxServiceImpl.VerifyBusinessLimit(
            PublicConstant.GNBH_HJ_QCCLYW, voRhdxx);
        if (voLimit.getLimitflag()) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLIMIT,
                                     "??????????????????????????????????????????" + voLimit.getLimitinfo(), null);
        }

        //??????????????????
        voQcclfhxx[i] = new VoQcclfhxx();
        voQcclfhxx[i].setQcclid(poQcclxx.getQcclid());
      }

      ////////////////////////////////////////
      //??????????????????????????????
      this.saveHJYWLSXX(hjywid, PublicConstant.GNBH_HJ_QCCLYW,
                        PublicConstant.HJYWLS_YWLX_GR,
                        voQcclxx.length, null, now);

      ////////////////////////////////////
      //????????????????????????
      voQcclywfhxx = new VoQcclywfhxx();
      voQcclywfhxx.setHjywid(hjywid);
      voQcclywfhxx.setVoQcclfhxx(voQcclfhxx);

      ///////////////////////////////////////////
      //????????????
    }
    catch (DAOException ex) {
      //????????????
      throw ex;
    }
    catch (ServiceException ex) {
      //????????????
      throw ex;
    }

    return voQcclywfhxx;
  }

  /**
   * ????????????????????????????????????????????????PoHJXX_HXXB(?????????)+PoHJXX_MLPXXXXB(?????????)+PoHJYW_BGGZXXB(??????????????????)?????????
   * @param strHQL
   * @param voPage
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoQueryResult queryBggzxx(String strHQL, VoPage voPage) throws
      ServiceException,
      DAOException {

    StringBuffer strAllHQL = new StringBuffer();
    StringBuffer strCountHQL = new StringBuffer();
    StringBuffer strFromHQL = new StringBuffer();
    List poList = null;
    List voList = null;
    VoQueryResult voQueryResult = new VoQueryResult();

    //??????FROM HQL??????
    strFromHQL.append("from PoV_HJ_BGGZXXB as V_HJ_BGGZXXB ")
        .append("where 1=1 ");
    //????????????begin
    List sjfwList = XtywqxServiceImpl.SelectDataRange(this.getUserInfo().
        getYhid().toString(), PublicConstant.GNBH_HJ_BGGZXXHQ);
    if (sjfwList != null) {
      StringBuffer sjfwHQL = new StringBuffer();
      for (int iFW = 0; iFW < sjfwList.size(); iFW++) {
        VoXtsjfw voXtsjfw = (VoXtsjfw) sjfwList.get(iFW);
        //???(???)??????
        if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_JWH)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or jcwh='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "jcwh='" + voXtsjfw.getSjfw().trim() +
                         "' ");
        }
        //?????????
        else if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_PCS)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or pcs='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "pcs='" + voXtsjfw.getSjfw().trim() +
                         "' ");
        }
        //????????????
        else if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_XZQH)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or ssxq='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "ssxq='" +
                         voXtsjfw.getSjfw().trim() +
                         "' ");
        }
      } //for(int i = 0; i < sjfwList.size(); i++)
      if (sjfwHQL.length() > 0) {
        strFromHQL.append("and ( ")
            .append(sjfwHQL.toString())
            .append(") ");
      }
    }
    //????????????end
    if (strHQL != null && strHQL.trim().length() > 0) {
      strFromHQL.append("and ")
          .append(strHQL);
    }

    //??????COUNT HQL??????
    if (voPage.getRecordcount() == -1) {
      strCountHQL.append("select count(*) ").append(strFromHQL.toString());
    }
    else {
      voQueryResult.setRecordcount(voPage.getRecordcount());
    }

    //??????SELECT HQL??????
    strAllHQL.append("select V_HJ_BGGZXXB ").append(strFromHQL.toString());

    //debug info
    _log.info(strAllHQL.toString());

    //??????DAO??????
    try {
      PojoInfo  hjyw_bggzxxbDAO = DAOFactory.
          createHJYW_BGGZXXBDAO();


      if (voPage.getRecordcount() == -1) {
        voQueryResult.setRecordcount(super.getCount(
            strCountHQL.
            toString()));
      }
      poList =super.getPageRecords(strAllHQL.
          toString(),
          new Object[]{},
          voPage.getPageindex(),
          voPage.getPagesize()).getList();

    }
    catch (DAOException ex) {
      throw ex;
    }

    //?????????Vo
    if (poList != null && poList.size() > 0) {
      voList = new ArrayList();
      for (int i = 0; i < poList.size(); i++) {
        PoV_HJ_BGGZXXB poV_HJ_BGGZXXB = (PoV_HJ_BGGZXXB)
            poList.get(i);

        VoBggzxxHqFhxx voBggzxxHqFhxx = new VoBggzxxHqFhxx(
            poV_HJ_BGGZXXB);
        voList.add(voBggzxxHqFhxx);
      }
    }

    voQueryResult.setPagelist(voList);
    voQueryResult.setVotype(VoBggzxxHqFhxx.class);
    return voQueryResult;
  }

  /**
   * ????????????????????????????????????????????????PoHJXX_HXXB(?????????)+PoHJXX_MLPXXXXB(?????????)+PoHJYW_CSDJXXB(??????????????????)?????????
   * @param strHQL
   * @param voPage
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoQueryResult queryCsdjxx(String strHQL, VoPage voPage) throws
      ServiceException,
      DAOException {
    StringBuffer strAllHQL = new StringBuffer();
    StringBuffer strCountHQL = new StringBuffer();
    StringBuffer strFromHQL = new StringBuffer();
    List poList = null;
    List voList = null;
    VoQueryResult voQueryResult = new VoQueryResult();

    //??????FROM HQL??????
    strFromHQL.append("from PoV_HJ_CSDJXXB as V_HJ_CSDJXXB ")
        .append("where 1=1 ");
    //????????????begin
    List sjfwList = XtywqxServiceImpl.SelectDataRange(this.getUserInfo().
        getYhid().toString(), PublicConstant.GNBH_HJ_CSDJXXHQ);
    if (sjfwList != null) {
      StringBuffer sjfwHQL = new StringBuffer();
      for (int iFW = 0; iFW < sjfwList.size(); iFW++) {
        VoXtsjfw voXtsjfw = (VoXtsjfw) sjfwList.get(iFW);
        //???(???)??????
        if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_JWH)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or jcwh='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "jcwh='" + voXtsjfw.getSjfw().trim() +
                         "' ");
        }
        //?????????
        else if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_PCS)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or pcs='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "pcs='" + voXtsjfw.getSjfw().trim() +
                         "' ");
        }
        //????????????
        else if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_XZQH)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or ssxq='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "ssxq='" +
                         voXtsjfw.getSjfw().trim() +
                         "' ");
        }
      } //for(int i = 0; i < sjfwList.size(); i++)
      if (sjfwHQL.length() > 0) {
        strFromHQL.append("and ( ")
            .append(sjfwHQL.toString())
            .append(") ");
      }
    }
    //????????????end
    if (strHQL != null && strHQL.trim().length() > 0) {
      strFromHQL.append("and ")
          .append(strHQL);
    }

    //??????COUNT HQL??????
    if (voPage.getRecordcount() == -1) {
      strCountHQL.append("select count(*) ").append(strFromHQL.toString());
    }
    else {
      voQueryResult.setRecordcount(voPage.getRecordcount());
    }

    //??????SELECT HQL??????
    strAllHQL.append("select V_HJ_CSDJXXB ").append(strFromHQL.toString());

    //debug info
    _log.info(strAllHQL.toString());

    //??????DAO??????
    try {
      PojoInfo  hjyw_csdjxxbDAO = DAOFactory.
          createHJYW_CSDJXXBDAO();


      if (voPage.getRecordcount() == -1) {
        voQueryResult.setRecordcount(super.getCount(
            strCountHQL.
            toString()));
      }
      poList =super.getPageRecords(strAllHQL.
          toString(),
          new Object[]{},
          voPage.getPageindex(),
          voPage.getPagesize()).getList();

    }
    catch (DAOException ex) {
      throw ex;
    }

    //?????????Vo
    if (poList != null && poList.size() > 0) {
      voList = new ArrayList();
      for (int i = 0; i < poList.size(); i++) {
        PoV_HJ_CSDJXXB poV_HJ_CSDJXXB = (PoV_HJ_CSDJXXB)
            poList.get(i);

        VoCsdjxxHqFhxx voCsdjxxHqFhxx = new VoCsdjxxHqFhxx(
            poV_HJ_CSDJXXB);
        voList.add(voCsdjxxHqFhxx);
      }
    }

    voQueryResult.setPagelist(voList);
    voQueryResult.setVotype(VoCsdjxxHqFhxx.class);
    return voQueryResult;
  }

  /**
   * ????????????????????????????????????????????????PoHJXX_HXXB(?????????)+PoHJXX_MLPXXXXB(?????????)+PoHJYW_HJBLXXB(??????????????????)?????????
   * @param strHQL
   * @param voPage
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoQueryResult queryHjblxx(String strHQL, VoPage voPage) throws
      ServiceException,
      DAOException {
    StringBuffer strAllHQL = new StringBuffer();
    StringBuffer strCountHQL = new StringBuffer();
    StringBuffer strFromHQL = new StringBuffer();
    List poList = null;
    List voList = null;
    VoQueryResult voQueryResult = new VoQueryResult();

    //??????FROM HQL??????
    strFromHQL.append("from PoV_HJ_HJBLXXB as V_HJ_HJBLXXB ")
        .append("where 1=1 ");
    //????????????begin
    List sjfwList = XtywqxServiceImpl.SelectDataRange(this.getUserInfo().
        getYhid().toString(), PublicConstant.GNBH_HJ_HJBLXXHQ);
    if (sjfwList != null) {
      StringBuffer sjfwHQL = new StringBuffer();
      for (int iFW = 0; iFW < sjfwList.size(); iFW++) {
        VoXtsjfw voXtsjfw = (VoXtsjfw) sjfwList.get(iFW);
        //???(???)??????
        if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_JWH)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or jcwh='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "jcwh='" + voXtsjfw.getSjfw().trim() +
                         "' ");
        }
        //?????????
        else if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_PCS)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or pcs='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "pcs='" + voXtsjfw.getSjfw().trim() +
                         "' ");
        }
        //????????????
        else if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_XZQH)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or ssxq='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "ssxq='" +
                         voXtsjfw.getSjfw().trim() +
                         "' ");
        }
      } //for(int i = 0; i < sjfwList.size(); i++)
      if (sjfwHQL.length() > 0) {
        strFromHQL.append("and ( ")
            .append(sjfwHQL.toString())
            .append(") ");
      }
    }
    //????????????end
    if (strHQL != null && strHQL.trim().length() > 0) {
      strFromHQL.append("and ")
          .append(strHQL);
    }

    //??????COUNT HQL??????
    if (voPage.getRecordcount() == -1) {
      strCountHQL.append("select count(*) ").append(strFromHQL.toString());
    }
    else {
      voQueryResult.setRecordcount(voPage.getRecordcount());
    }

    //??????SELECT HQL??????
    strAllHQL.append("select V_HJ_HJBLXXB ").append(strFromHQL.toString());

    //debug info
    _log.info(strAllHQL.toString());

    //??????DAO??????
    try {
      PojoInfo  hjyw_hjblxxbDAO = DAOFactory.
          createHJYW_HJBLXXBDAO();


      if (voPage.getRecordcount() == -1) {
        voQueryResult.setRecordcount(super.getCount(
            strCountHQL.
            toString()));
      }
      poList =super.getPageRecords(strAllHQL.
          toString(),
          new Object[]{},
          voPage.getPageindex(),
          voPage.getPagesize()).getList();

    }
    catch (DAOException ex) {
      throw ex;
    }

    //?????????Vo
    if (poList != null && poList.size() > 0) {
      voList = new ArrayList();
      for (int i = 0; i < poList.size(); i++) {
        PoV_HJ_HJBLXXB poV_HJ_HJBLXXB = (PoV_HJ_HJBLXXB)
            poList.get(i);

        VoHjblxxHqFhxx voHjblxxHqFhxx = new VoHjblxxHqFhxx(
            poV_HJ_HJBLXXB);
        voList.add(voHjblxxHqFhxx);
      }
    }

    voQueryResult.setPagelist(voList);
    voQueryResult.setVotype(VoHjblxxHqFhxx.class);
    return voQueryResult;
  }

  /**
   * ??????????????????????????????????????????????????????PoHJXX_HXXB(?????????)+PoHJXX_MLPXXXXB(?????????)+PoHJYW_HCYBDXXB(?????????????????????)?????????
   * @param strHQL
   * @param voPage
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoQueryResult queryHcybdxx(String strHQL, VoPage voPage) throws
      ServiceException,
      DAOException {
    StringBuffer strAllHQL = new StringBuffer();
    StringBuffer strCountHQL = new StringBuffer();
    StringBuffer strFromHQL = new StringBuffer();
    List poList = null;
    List voList = null;
    VoQueryResult voQueryResult = new VoQueryResult();

    //??????FROM HQL??????
    strFromHQL.append("from PoV_HJ_HCYBDXXB as V_HJ_HCYBDXXB ")
        .append("where 1=1 ");
    //????????????begin
    List sjfwList = XtywqxServiceImpl.SelectDataRange(this.getUserInfo().
        getYhid().toString(), PublicConstant.GNBH_HJ_HCYBDXXHQ);
    if (sjfwList != null) {
      StringBuffer sjfwHQL = new StringBuffer();
      for (int iFW = 0; iFW < sjfwList.size(); iFW++) {
        VoXtsjfw voXtsjfw = (VoXtsjfw) sjfwList.get(iFW);
        //???(???)??????
        if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_JWH)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or jcwh='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "jcwh='" + voXtsjfw.getSjfw().trim() +
                         "' ");
        }
        //?????????
        else if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_PCS)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or pcs='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "pcs='" + voXtsjfw.getSjfw().trim() +
                         "' ");
        }
        //????????????
        else if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_XZQH)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or ssxq='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "ssxq='" +
                         voXtsjfw.getSjfw().trim() +
                         "' ");
        }
      } //for(int i = 0; i < sjfwList.size(); i++)
      if (sjfwHQL.length() > 0) {
        strFromHQL.append("and ( ")
            .append(sjfwHQL.toString())
            .append(") ");
      }
    }
    //????????????end
    if (strHQL != null && strHQL.trim().length() > 0) {
      strFromHQL.append("and ")
          .append(strHQL);
    }

    //??????COUNT HQL??????
    if (voPage.getRecordcount() == -1) {
      strCountHQL.append("select count(*) ").append(strFromHQL.toString());
    }
    else {
      voQueryResult.setRecordcount(voPage.getRecordcount());
    }

    //??????SELECT HQL??????
    strAllHQL.append("select V_HJ_HCYBDXXB ").append(strFromHQL.toString());

    //debug info
    _log.info(strAllHQL.toString());

    //??????DAO??????
    try {
      PojoInfo  hjyw_hcybdxxbDAO = DAOFactory.
          createHJYW_HCYBDXXBDAO();


      if (voPage.getRecordcount() == -1) {
        voQueryResult.setRecordcount(super.getCount(
            strCountHQL.
            toString()));
      }
      poList =super.getPageRecords(strAllHQL.
          toString(),
          new Object[]{},
          voPage.getPageindex(),
          voPage.getPagesize()).getList();

    }
    catch (DAOException ex) {
      throw ex;
    }

    //?????????Vo
    if (poList != null && poList.size() > 0) {
      voList = new ArrayList();
      for (int i = 0; i < poList.size(); i++) {
        PoV_HJ_HCYBDXXB poV_HJ_HCYBDXXB = (PoV_HJ_HCYBDXXB)
            poList.get(i);

        VoHcybdxxHqFhxx voHcybdxxHqFhxx = new VoHcybdxxHqFhxx(
            poV_HJ_HCYBDXXB);
        voList.add(voHcybdxxHqFhxx);
      }
    }

    voQueryResult.setPagelist(voList);
    voQueryResult.setVotype(VoHcybdxxHqFhxx.class);
    return voQueryResult;
  }

  /**
   * ????????????????????????????????????????????????PoHJXX_HXXB(?????????)+PoHJXX_MLPXXXXB(?????????)+PoHJYW_HJSCXXB(??????????????????)?????????
   * @param strHQL
   * @param voPage
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoQueryResult queryHjscxx(String strHQL, VoPage voPage) throws
      ServiceException,
      DAOException {
    StringBuffer strAllHQL = new StringBuffer();
    StringBuffer strCountHQL = new StringBuffer();
    StringBuffer strFromHQL = new StringBuffer();
    List poList = null;
    List voList = null;
    VoQueryResult voQueryResult = new VoQueryResult();

    //??????FROM HQL??????
    strFromHQL.append("from PoV_HJ_HJSCXXB as V_HJ_HJSCXXB ")
        .append("where 1=1 ");
    //????????????begin
    List sjfwList = XtywqxServiceImpl.SelectDataRange(this.getUserInfo().
        getYhid().toString(), PublicConstant.GNBH_HJ_HJSCXXHQ);
    if (sjfwList != null) {
      StringBuffer sjfwHQL = new StringBuffer();
      for (int iFW = 0; iFW < sjfwList.size(); iFW++) {
        VoXtsjfw voXtsjfw = (VoXtsjfw) sjfwList.get(iFW);
        //???(???)??????
        if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_JWH)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or jcwh='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "jcwh='" + voXtsjfw.getSjfw().trim() +
                         "' ");
        }
        //?????????
        else if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_PCS)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or pcs='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "pcs='" + voXtsjfw.getSjfw().trim() +
                         "' ");
        }
        //????????????
        else if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_XZQH)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or ssxq='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "ssxq='" +
                         voXtsjfw.getSjfw().trim() +
                         "' ");
        }
      } //for(int i = 0; i < sjfwList.size(); i++)
      if (sjfwHQL.length() > 0) {
        strFromHQL.append("and ( ")
            .append(sjfwHQL.toString())
            .append(") ");
      }
    }
    //????????????end
    if (strHQL != null && strHQL.trim().length() > 0) {
      strFromHQL.append("and ")
          .append(strHQL);
    }

    //??????COUNT HQL??????
    if (voPage.getRecordcount() == -1) {
      strCountHQL.append("select count(*) ").append(strFromHQL.toString());
    }
    else {
      voQueryResult.setRecordcount(voPage.getRecordcount());
    }

    //??????SELECT HQL??????
    strAllHQL.append("select V_HJ_HJSCXXB ").append(strFromHQL.toString());

    //debug info
    _log.info(strAllHQL.toString());

    //??????DAO??????
    try {
      PojoInfo  hjyw_hjscxxbDAO = DAOFactory.
          createHJYW_HJSCXXBDAO();


      if (voPage.getRecordcount() == -1) {
        voQueryResult.setRecordcount(super.getCount(
            strCountHQL.
            toString()));
      }
      poList =super.getPageRecords(strAllHQL.
          toString(),
          new Object[]{},
          voPage.getPageindex(),
          voPage.getPagesize()).getList();

    }
    catch (DAOException ex) {
      throw ex;
    }

    //?????????Vo
    if (poList != null && poList.size() > 0) {
      voList = new ArrayList();
      for (int i = 0; i < poList.size(); i++) {
        PoV_HJ_HJSCXXB poV_HJ_HJSCXXB = (PoV_HJ_HJSCXXB)
            poList.get(i);

        VoHjscxxHqFhxx voHjscxxHqFhxx = new VoHjscxxHqFhxx(
            poV_HJ_HJSCXXB);
        voList.add(voHjscxxHqFhxx);
      }
    }

    voQueryResult.setPagelist(voList);
    voQueryResult.setVotype(VoHjscxxHqFhxx.class);
    return voQueryResult;
  }

  /**
   * ????????????????????????????????????????????????PoHJXX_HXXB(?????????)+PoHJXX_MLPXXXXB(?????????)+PoHJYW_QCCLXXB(??????????????????)?????????
   * @param strHQL
   * @param voPage
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoQueryResult queryQcclxx(String strHQL, VoPage voPage) throws
      ServiceException,
      DAOException {
    StringBuffer strAllHQL = new StringBuffer();
    StringBuffer strCountHQL = new StringBuffer();
    StringBuffer strFromHQL = new StringBuffer();
    List poList = null;
    List voList = null;
    VoQueryResult voQueryResult = new VoQueryResult();

    //??????FROM HQL??????
    strFromHQL.append("from PoV_HJ_QCCLXXB as V_HJ_QCCLXXB ")
        .append("where 1=1 ");
    //????????????begin
    List sjfwList = XtywqxServiceImpl.SelectDataRange(this.getUserInfo().
        getYhid().toString(), PublicConstant.GNBH_HJ_QCCLXXHQ);
    if (sjfwList != null) {
      StringBuffer sjfwHQL = new StringBuffer();
      for (int iFW = 0; iFW < sjfwList.size(); iFW++) {
        VoXtsjfw voXtsjfw = (VoXtsjfw) sjfwList.get(iFW);
        //???(???)??????
        if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_JWH)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or jcwh_q='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "jcwh_q='" + voXtsjfw.getSjfw().trim() +
                         "' ");
        }
        //?????????
        else if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_PCS)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or pcs_q='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "pcs_q='" + voXtsjfw.getSjfw().trim() +
                         "' ");
        }
        //????????????
        else if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_XZQH)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or ssxq_q='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "ssxq_q='" +
                         voXtsjfw.getSjfw().trim() +
                         "' ");
        }
      } //for(int i = 0; i < sjfwList.size(); i++)
      if (sjfwHQL.length() > 0) {
        strFromHQL.append("and ( ")
            .append(sjfwHQL.toString())
            .append(") ");
      }
    }
    //????????????end
    if (strHQL != null && strHQL.trim().length() > 0) {
      strFromHQL.append("and ")
          .append(strHQL);
    }

    //??????COUNT HQL??????
    if (voPage.getRecordcount() == -1) {
      strCountHQL.append("select count(*) ").append(strFromHQL.toString());
    }
    else {
      voQueryResult.setRecordcount(voPage.getRecordcount());
    }

    //??????SELECT HQL??????
    strAllHQL.append("select V_HJ_QCCLXXB ").append(strFromHQL.toString());

    //debug info
    _log.info(strAllHQL.toString());

    //??????DAO??????
    try {
      PojoInfo  hjyw_qcclxxbDAO = DAOFactory.
          createHJYW_QCCLXXBDAO();


      if (voPage.getRecordcount() == -1) {
        voQueryResult.setRecordcount(super.getCount(
            strCountHQL.
            toString()));
      }
      poList =super.getPageRecords(strAllHQL.
          toString(),
          new Object[]{},
          voPage.getPageindex(),
          voPage.getPagesize()).getList();

    }
    catch (DAOException ex) {
      throw ex;
    }

    //?????????Vo
    if (poList != null && poList.size() > 0) {
      voList = new ArrayList();
      for (int i = 0; i < poList.size(); i++) {
        PoV_HJ_QCCLXXB poV_HJ_QCCLXXB = (PoV_HJ_QCCLXXB)
            poList.get(i);

        VoQcclxxHqFhxx voQcclxxHqFhxx = new VoQcclxxHqFhxx(
            poV_HJ_QCCLXXB);
        voList.add(voQcclxxHqFhxx);
      }
    }

    voQueryResult.setPagelist(voList);
    voQueryResult.setVotype(VoQcclxxHqFhxx.class);
    return voQueryResult;
  }

  /**
   * ????????????????????????????????????????????????PoHJXX_HXXB(?????????)+PoHJXX_MLPXXXXB(?????????)+PoHJYW_QCZXXXB(??????????????????)?????????
   * @param strHQL
   * @param voPage
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoQueryResult queryQczxxx(String strHQL, VoPage voPage) throws
      ServiceException,
      DAOException {
    StringBuffer strAllHQL = new StringBuffer();
    StringBuffer strCountHQL = new StringBuffer();
    StringBuffer strFromHQL = new StringBuffer();
    List poList = null;
    List voList = null;
    VoQueryResult voQueryResult = new VoQueryResult();

    //??????FROM HQL??????
    strFromHQL.append("from PoV_HJ_QCZXXXB as V_HJ_QCZXXXB ")
        .append("where 1=1 ");
    //????????????begin
    List sjfwList = XtywqxServiceImpl.SelectDataRange(this.getUserInfo().
        getYhid().toString(), PublicConstant.GNBH_HJ_QCZXXXHQ);
    if (sjfwList != null) {
      StringBuffer sjfwHQL = new StringBuffer();
      for (int iFW = 0; iFW < sjfwList.size(); iFW++) {
        VoXtsjfw voXtsjfw = (VoXtsjfw) sjfwList.get(iFW);
        //???(???)??????
        if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_JWH)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or jcwh='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "jcwh='" + voXtsjfw.getSjfw().trim() +
                         "' ");
        }
        //?????????
        else if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_PCS)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or pcs='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "pcs='" + voXtsjfw.getSjfw().trim() +
                         "' ");
        }
        //????????????
        else if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_XZQH)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or ssxq='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "ssxq='" +
                         voXtsjfw.getSjfw().trim() +
                         "' ");
        }
      } //for(int i = 0; i < sjfwList.size(); i++)
      if (sjfwHQL.length() > 0) {
        strFromHQL.append("and ( ")
            .append(sjfwHQL.toString())
            .append(") ");
      }
    }
    //????????????end
    if (strHQL != null && strHQL.trim().length() > 0) {
      strFromHQL.append("and ")
          .append(strHQL);
    }

    //??????COUNT HQL??????
    if (voPage.getRecordcount() == -1) {
      strCountHQL.append("select count(*) ").append(strFromHQL.toString());
    }
    else {
      voQueryResult.setRecordcount(voPage.getRecordcount());
    }

    //??????SELECT HQL??????
    strAllHQL.append("select V_HJ_QCZXXXB ").append(strFromHQL.toString());

    //debug info
    _log.info(strAllHQL.toString());

    //??????DAO??????
    try {
      PojoInfo  hjyw_qczxxxbDAO = DAOFactory.
          createHJYW_QCZXXXBDAO();


      if (voPage.getRecordcount() == -1) {
        voQueryResult.setRecordcount(super.getCount(
            strCountHQL.
            toString()));
      }
      poList =super.getPageRecords(strAllHQL.
          toString(),
          new Object[]{},
          voPage.getPageindex(),
          voPage.getPagesize()).getList();

    }
    catch (DAOException ex) {
      throw ex;
    }

    //?????????Vo
    if (poList != null && poList.size() > 0) {
      voList = new ArrayList();
      for (int i = 0; i < poList.size(); i++) {
        PoV_HJ_QCZXXXB poV_HJ_QCZXXXB = (PoV_HJ_QCZXXXB)
            poList.get(i);

        VoQczxxxHqFhxx voQczxxxHqFhxx = new VoQczxxxHqFhxx(
            poV_HJ_QCZXXXB);
        voList.add(voQczxxxHqFhxx);
      }
    }

    voQueryResult.setPagelist(voList);
    voQueryResult.setVotype(VoQczxxxHqFhxx.class);
    return voQueryResult;

  }

  /**
   * ????????????????????????????????????????????????PoHJXX_HXXB(?????????)+PoHJXX_MLPXXXXB(?????????)+PoHJYW_QRDJXXB(??????????????????)?????????
   * @param strHQL
   * @param voPage
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoQueryResult queryQrdjxx(String strHQL, VoPage voPage) throws
      ServiceException,
      DAOException {
    StringBuffer strAllHQL = new StringBuffer();
    StringBuffer strCountHQL = new StringBuffer();
    StringBuffer strFromHQL = new StringBuffer();
    List poList = null;
    List voList = null;
    VoQueryResult voQueryResult = new VoQueryResult();

    //??????FROM HQL??????
    strFromHQL.append("from PoV_HJ_QRDJXXB as V_HJ_QRDJXXB ")
        .append("where 1=1 ");
    //????????????begin
    List sjfwList = XtywqxServiceImpl.SelectDataRange(this.getUserInfo().
        getYhid().toString(), PublicConstant.GNBH_HJ_QRDJXXHQ);
    if (sjfwList != null) {
      StringBuffer sjfwHQL = new StringBuffer();
      for (int iFW = 0; iFW < sjfwList.size(); iFW++) {
        VoXtsjfw voXtsjfw = (VoXtsjfw) sjfwList.get(iFW);
        //???(???)??????
        if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_JWH)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or jcwh='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "jcwh='" + voXtsjfw.getSjfw().trim() +
                         "' ");
        }
        //?????????
        else if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_PCS)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or pcs='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "pcs='" + voXtsjfw.getSjfw().trim() +
                         "' ");
        }
        //????????????
        else if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_XZQH)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or ssxq='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "ssxq='" +
                         voXtsjfw.getSjfw().trim() +
                         "' ");
        }
      } //for(int i = 0; i < sjfwList.size(); i++)
      if (sjfwHQL.length() > 0) {
        strFromHQL.append("and ( ")
            .append(sjfwHQL.toString())
            .append(") ");
      }
    }
    //????????????end
    if (strHQL != null && strHQL.trim().length() > 0) {
      strFromHQL.append("and ")
          .append(strHQL);
    }

    //??????COUNT HQL??????
    if (voPage.getRecordcount() == -1) {
      strCountHQL.append("select count(*) ").append(strFromHQL.toString());
    }
    else {
      voQueryResult.setRecordcount(voPage.getRecordcount());
    }

    //??????SELECT HQL??????
    strAllHQL.append("select V_HJ_QRDJXXB ").append(strFromHQL.toString());

    //debug info
    _log.info(strAllHQL.toString());

    //??????DAO??????
    try {
      PojoInfo  hjyw_qrdjxxbDAO = DAOFactory.
          createHJYW_QRDJXXBDAO();


      if (voPage.getRecordcount() == -1) {
        voQueryResult.setRecordcount(super.getCount(
            strCountHQL.
            toString()));
      }
      poList =super.getPageRecords(strAllHQL.
          toString(),
          new Object[]{},
          voPage.getPageindex(),
          voPage.getPagesize()).getList();

    }
    catch (DAOException ex) {
      throw ex;
    }

    //?????????Vo
    if (poList != null && poList.size() > 0) {
      voList = new ArrayList();
      for (int i = 0; i < poList.size(); i++) {
        PoV_HJ_QRDJXXB poV_HJ_QRDJXXB = (PoV_HJ_QRDJXXB)
            poList.get(i);

        VoQrdjxxHqFhxx voQrdjxxHqFhxx = new VoQrdjxxHqFhxx(
            poV_HJ_QRDJXXB);
        voList.add(voQrdjxxHqFhxx);
      }
    }

    voQueryResult.setPagelist(voList);
    voQueryResult.setVotype(VoQrdjxxHqFhxx.class);
    return voQueryResult;
  }

  /**
   * ????????????????????????????????????????????????PoHJXX_HXXB(?????????)+PoHJXX_MLPXXXXB(?????????)+PoHJYW_SWZXXXB(??????????????????)?????????
   * @param strHQL
   * @param voPage
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoQueryResult querySwzxxx(String strHQL, VoPage voPage) throws
      ServiceException,
      DAOException {
    StringBuffer strAllHQL = new StringBuffer();
    StringBuffer strCountHQL = new StringBuffer();
    StringBuffer strFromHQL = new StringBuffer();
    List poList = null;
    List voList = null;
    VoQueryResult voQueryResult = new VoQueryResult();

    //??????FROM HQL??????
    strFromHQL.append("from PoV_HJ_SWZXXXB as V_HJ_SWZXXXB ")
        .append("where 1=1 ");
    //????????????begin
    List sjfwList = XtywqxServiceImpl.SelectDataRange(this.getUserInfo().
        getYhid().toString(), PublicConstant.GNBH_HJ_SWZXXXHQ);
    if (sjfwList != null) {
      StringBuffer sjfwHQL = new StringBuffer();
      for (int iFW = 0; iFW < sjfwList.size(); iFW++) {
        VoXtsjfw voXtsjfw = (VoXtsjfw) sjfwList.get(iFW);
        //???(???)??????
        if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_JWH)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or jcwh='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "jcwh='" + voXtsjfw.getSjfw().trim() +
                         "' ");
        }
        //?????????
        else if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_PCS)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or pcs='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "pcs='" + voXtsjfw.getSjfw().trim() +
                         "' ");
        }
        //????????????
        else if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_XZQH)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or ssxq='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "ssxq='" +
                         voXtsjfw.getSjfw().trim() +
                         "' ");
        }
      } //for(int i = 0; i < sjfwList.size(); i++)
      if (sjfwHQL.length() > 0) {
        strFromHQL.append("and ( ")
            .append(sjfwHQL.toString())
            .append(") ");
      }
    }
    //????????????end
    if (strHQL != null && strHQL.trim().length() > 0) {
      strFromHQL.append("and ")
          .append(strHQL);
    }

    //??????COUNT HQL??????
    if (voPage.getRecordcount() == -1) {
      strCountHQL.append("select count(*) ").append(strFromHQL.toString());
    }
    else {
      voQueryResult.setRecordcount(voPage.getRecordcount());
    }

    //??????SELECT HQL??????
    strAllHQL.append("select V_HJ_SWZXXXB ").append(strFromHQL.toString());

    //debug info
    _log.info(strAllHQL.toString());

    //??????DAO??????
    try {
      PojoInfo  hjyw_swzxxxbDAO = DAOFactory.
          createHJYW_SWZXXXBDAO();


      if (voPage.getRecordcount() == -1) {
        voQueryResult.setRecordcount(super.getCount(
            strCountHQL.
            toString()));
      }
      poList =super.getPageRecords(strAllHQL.
          toString(),
          new Object[]{},
          voPage.getPageindex(),
          voPage.getPagesize()).getList();

    }
    catch (DAOException ex) {
      throw ex;
    }

    //?????????Vo
    if (poList != null && poList.size() > 0) {
      voList = new ArrayList();
      for (int i = 0; i < poList.size(); i++) {
        PoV_HJ_SWZXXXB poV_HJ_SWZXXXB = (PoV_HJ_SWZXXXB)
            poList.get(i);

        VoSwzxxxHqFhxx voSwzxxxHqFhxx = new VoSwzxxxHqFhxx(
            poV_HJ_SWZXXXB);
        voList.add(voSwzxxxHqFhxx);
      }
    }

    voQueryResult.setPagelist(voList);
    voQueryResult.setVotype(VoSwzxxxHqFhxx.class);
    return voQueryResult;
  }

  /**
   * ???????????????????????????????????????????????????PoHJYW_CHCLXXB(??????????????????)?????????
   * @param strHQL
   * @param voPage
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoQueryResult queryChclxx(String strHQL, VoPage voPage) throws
      ServiceException,
      DAOException {
    StringBuffer strAllHQL = new StringBuffer();
    StringBuffer strCountHQL = new StringBuffer();
    StringBuffer strFromHQL = new StringBuffer();
    List poList = null;
    List voList = null;
    VoQueryResult voQueryResult = new VoQueryResult();

    //??????FROM HQL??????
    strFromHQL.append("from PoV_HJ_CHCLXXB as V_HJ_CHCLXXB ")
        .append("where 1=1 ");
    //????????????begin
    List sjfwList = XtywqxServiceImpl.SelectDataRange(this.getUserInfo().
        getYhid().toString(), PublicConstant.GNBH_HJ_CHCLXXHQ);
    if (sjfwList != null) {
      StringBuffer sjfwHQL = new StringBuffer();
      for (int iFW = 0; iFW < sjfwList.size(); iFW++) {
        VoXtsjfw voXtsjfw = (VoXtsjfw) sjfwList.get(iFW);
        //???(???)??????
        if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_JWH)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or jcwh='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "jcwh='" + voXtsjfw.getSjfw().trim() +
                         "' ");
        }
        //?????????
        else if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_PCS)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or pcs='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "pcs='" + voXtsjfw.getSjfw().trim() +
                         "' ");
        }
        //????????????
        else if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_XZQH)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or ssxq='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "ssxq='" +
                         voXtsjfw.getSjfw().trim() +
                         "' ");
        }
      } //for(int i = 0; i < sjfwList.size(); i++)
      if (sjfwHQL.length() > 0) {
        strFromHQL.append("and ( ")
            .append(sjfwHQL.toString())
            .append(") ");
      }
    }
    //????????????end
    if (strHQL != null && strHQL.trim().length() > 0) {
      strFromHQL.append("and ")
          .append(strHQL);
    }

    //??????COUNT HQL??????
    if (voPage.getRecordcount() == -1) {
      strCountHQL.append("select count(*) ").append(strFromHQL.toString());
    }
    else {
      voQueryResult.setRecordcount(voPage.getRecordcount());
    }

    //??????SELECT HQL??????
    strAllHQL.append("select V_HJ_CHCLXXB ").append(strFromHQL.toString());

    //debug info
    _log.info(strAllHQL.toString());

    //??????DAO??????
    try {
      PojoInfo  hjyw_chclxxbDAO = DAOFactory.
          createHJYW_CHCLXXBDAO();


      if (voPage.getRecordcount() == -1) {
        voQueryResult.setRecordcount(super.getCount(
            strCountHQL.
            toString()));
      }
      poList =super.getPageRecords(strAllHQL.
          toString(),
          new Object[]{},
          voPage.getPageindex(),
          voPage.getPagesize()).getList();

    }
    catch (DAOException ex) {
      throw ex;
    }

    //?????????Vo
    if (poList != null && poList.size() > 0) {
      voList = new ArrayList();
      for (int i = 0; i < poList.size(); i++) {
        PoV_HJ_CHCLXXB poV_HJ_CHCLXXB = (PoV_HJ_CHCLXXB)
            poList.get(i);

        VoChclxxHqFhxx voChclxxHqFhxx = new VoChclxxHqFhxx(
            poV_HJ_CHCLXXB);
        voList.add(voChclxxHqFhxx);
      }
    }

    voQueryResult.setPagelist(voList);
    voQueryResult.setVotype(VoChclxxHqFhxx.class);
    return voQueryResult;
  }

  /**
   * ????????????????????????????????????????????????PoHJXX_HXXB(?????????)+PoHJXX_MLPXXXXB(?????????)+PoHJYW_ZZBDXXB(??????????????????)?????????
   * @param strHQL
   * @param voPage
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoQueryResult queryZzbdxx(String strHQL, VoPage voPage) throws
      ServiceException,
      DAOException {
    StringBuffer strAllHQL = new StringBuffer();
    StringBuffer strCountHQL = new StringBuffer();
    StringBuffer strFromHQL = new StringBuffer();
    List poList = null;
    List voList = null;
    VoQueryResult voQueryResult = new VoQueryResult();

    //??????FROM HQL??????
    strFromHQL.append("from PoV_HJ_ZZBDXXB as V_HJ_ZZBDXXB ")
        .append("where 1=1 ");
    //????????????begin
    List sjfwList = XtywqxServiceImpl.SelectDataRange(this.getUserInfo().
        getYhid().toString(), PublicConstant.GNBH_HJ_ZZBDXXHQ);
    if (sjfwList != null) {
      StringBuffer sjfwHQL = new StringBuffer();
      for (int iFW = 0; iFW < sjfwList.size(); iFW++) {
        VoXtsjfw voXtsjfw = (VoXtsjfw) sjfwList.get(iFW);
        //???(???)??????
        if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_JWH)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or jcwh='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "jcwh='" + voXtsjfw.getSjfw().trim() +
                         "' ");
        }
        //?????????
        else if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_PCS)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or pcs='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "pcs='" + voXtsjfw.getSjfw().trim() +
                         "' ");
        }
        //????????????
        else if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_XZQH)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or ssxq='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "ssxq='" +
                         voXtsjfw.getSjfw().trim() +
                         "' ");
        }
      } //for(int i = 0; i < sjfwList.size(); i++)
      if (sjfwHQL.length() > 0) {
        strFromHQL.append("and ( ")
            .append(sjfwHQL.toString())
            .append(") ");
      }
    }
    //????????????end
    if (strHQL != null && strHQL.trim().length() > 0) {
      strFromHQL.append("and ")
          .append(strHQL);
    }

    //??????COUNT HQL??????
    if (voPage.getRecordcount() == -1) {
      strCountHQL.append("select count(*) ").append(strFromHQL.toString());
    }
    else {
      voQueryResult.setRecordcount(voPage.getRecordcount());
    }

    //??????SELECT HQL??????
    strAllHQL.append("select V_HJ_ZZBDXXB ").append(strFromHQL.toString());

    //debug info
    _log.info(strAllHQL.toString());

    //??????DAO??????
    try {
      PojoInfo  hjyw_zzbdxxbDAO = DAOFactory.
          createHJYW_ZZBDXXBDAO();


      if (voPage.getRecordcount() == -1) {
        voQueryResult.setRecordcount(super.getCount(
            strCountHQL.
            toString()));
      }
      poList =super.getPageRecords(strAllHQL.
          toString(),
          new Object[]{},
          voPage.getPageindex(),
          voPage.getPagesize()).getList();

    }
    catch (DAOException ex) {
      throw ex;
    }

    //?????????Vo
    if (poList != null && poList.size() > 0) {
      voList = new ArrayList();
      for (int i = 0; i < poList.size(); i++) {
        PoV_HJ_ZZBDXXB poV_HJ_ZZBDXXB = (PoV_HJ_ZZBDXXB)
            poList.get(i);

        VoZzbdxxHqFhxx voZzbdxxHqFhxx = new VoZzbdxxHqFhxx(
            poV_HJ_ZZBDXXB);
        voList.add(voZzbdxxHqFhxx);
      }
    }

    voQueryResult.setPagelist(voList);
    voQueryResult.setVotype(VoZzbdxxHqFhxx.class);
    return voQueryResult;
  }

  /**
   * ??????????????????????????????PoHJXX_HXXB(?????????)+PoHJXX_MLPXXXXB(?????????)?????????
   * @param strHQL
   * @param voPage
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoQueryResult queryHxx(String strHQL, VoPage voPage) throws
      ServiceException,
      DAOException {
    StringBuffer strAllHQL = new StringBuffer();
    StringBuffer strCountHQL = new StringBuffer();
    StringBuffer strFromHQL = new StringBuffer();
    List poList = null;
    List voList = null;
    VoQueryResult voQueryResult = new VoQueryResult();

    //??????FROM HQL??????
    strFromHQL.append(
        "from PoHJXX_HXXB as HJXX_HXXB,PoHJXX_MLPXXXXB as HJXX_MLPXXXXB ")
        .append("where HJXX_HXXB.mlpnbid=HJXX_MLPXXXXB.mlpnbid ");
    //????????????begin
    List sjfwList = XtywqxServiceImpl.SelectDataRange(this.getUserInfo().
        getYhid().toString(), PublicConstant.GNBH_HJ_HXXHQ);
    if (sjfwList != null) {
      StringBuffer sjfwHQL = new StringBuffer();
      for (int iFW = 0; iFW < sjfwList.size(); iFW++) {
        VoXtsjfw voXtsjfw = (VoXtsjfw) sjfwList.get(iFW);
        //???(???)??????
        if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_JWH)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or HJXX_MLPXXXXB.jcwh='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "HJXX_MLPXXXXB.jcwh='" + voXtsjfw.getSjfw().trim() +
                         "' ");
        }
        //?????????
        else if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_PCS)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or HJXX_MLPXXXXB.pcs='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "HJXX_MLPXXXXB.pcs='" + voXtsjfw.getSjfw().trim() +
                         "' ");
        }
        //????????????
        else if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_XZQH)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or HJXX_MLPXXXXB.ssxq='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "HJXX_MLPXXXXB.ssxq='" +
                         voXtsjfw.getSjfw().trim() +
                         "' ");
        }
      } //for(int i = 0; i < sjfwList.size(); i++)
      if (sjfwHQL.length() > 0) {
        strFromHQL.append("and ( ")
            .append(sjfwHQL.toString())
            .append(") ");
      }
    }
    //????????????end
    if (strHQL != null && strHQL.trim().length() > 0) {
      strFromHQL.append("and ")
          .append(strHQL);
    }

    //??????COUNT HQL??????
    if (voPage.getRecordcount() == -1) {
      strCountHQL.append("select count(*) ").append(strFromHQL.toString());
    }
    else {
      voQueryResult.setRecordcount(voPage.getRecordcount());
    }

    //??????SELECT HQL??????
    strAllHQL.append("select HJXX_HXXB,HJXX_MLPXXXXB ").append(strFromHQL.
        toString());

    //debug info
    _log.info(strAllHQL.toString());

    //??????DAO??????
    try {
      PojoInfo  hjxx_hxxbDAO = DAOFactory.
          createHJXX_HXXBDAO();


      if (voPage.getRecordcount() == -1) {
        voQueryResult.setRecordcount(super.getCount(
            strCountHQL.
            toString()));
      }
      poList =super.getPageRecords(strAllHQL.
                                                  toString(),
                                                  new Object[]{},
                                                  voPage.getPageindex(),
                                                  voPage.getPagesize()).getList();

    }
    catch (DAOException ex) {
      throw ex;
    }

    //?????????Vo
    if (poList != null && poList.size() > 0) {
      voList = new ArrayList();
      for (int i = 0; i < poList.size(); i++) {
        Object ob[] = (Object[]) poList.get(i);
        PoHJXX_HXXB poHJXX_HXXB = (PoHJXX_HXXB) ob[0];
        PoHJXX_MLPXXXXB poHJXX_MLPXXXXB = (PoHJXX_MLPXXXXB) ob[1];

        VoHxxHqFhxx voHxxHqFhxx = new VoHxxHqFhxx(poHJXX_HXXB,
                                                  poHJXX_MLPXXXXB);
        voList.add(voHxxHqFhxx);
      }
    }

    voQueryResult.setPagelist(voList);
    voQueryResult.setVotype(VoHxxHqFhxx.class);
    return voQueryResult;
  }

  /**
   * ????????????????????????????????????????????????PoHJXX_HXXB(?????????)+PoHJXX_MLPXXXXB(?????????)+PoHJYW_HBBGXXB(??????????????????)?????????
   * @param strHQL
   * @param voPage
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoQueryResult queryHbbgxx(String strHQL, VoPage voPage) throws
      ServiceException,
      DAOException {
    StringBuffer strAllHQL = new StringBuffer();
    StringBuffer strCountHQL = new StringBuffer();
    StringBuffer strFromHQL = new StringBuffer();
    List poList = null;
    List voList = null;
    VoQueryResult voQueryResult = new VoQueryResult();

    //??????FROM HQL??????
    strFromHQL.append("from PoV_HJ_HBBGXXB as V_HJ_HBBGXXB ")
        .append("where 1=1 ");
    //????????????begin
    List sjfwList = XtywqxServiceImpl.SelectDataRange(this.getUserInfo().
        getYhid().toString(), PublicConstant.GNBH_HJ_HBBGXXHQ);
    if (sjfwList != null) {
      StringBuffer sjfwHQL = new StringBuffer();
      for (int iFW = 0; iFW < sjfwList.size(); iFW++) {
        VoXtsjfw voXtsjfw = (VoXtsjfw) sjfwList.get(iFW);
        //???(???)??????
        if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_JWH)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or jcwh='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "jcwh='" + voXtsjfw.getSjfw().trim() +
                         "' ");
        }
        //?????????
        else if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_PCS)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or pcs='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "pcs='" + voXtsjfw.getSjfw().trim() +
                         "' ");
        }
        //????????????
        else if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_XZQH)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or ssxq='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "ssxq='" +
                         voXtsjfw.getSjfw().trim() +
                         "' ");
        }
      } //for(int i = 0; i < sjfwList.size(); i++)
      if (sjfwHQL.length() > 0) {
        strFromHQL.append("and ( ")
            .append(sjfwHQL.toString())
            .append(") ");
      }
    }
    //????????????end
    if (strHQL != null && strHQL.trim().length() > 0) {
      strFromHQL.append("and ")
          .append(strHQL);
    }

    //??????COUNT HQL??????
    if (voPage.getRecordcount() == -1) {
      strCountHQL.append("select count(*) ").append(strFromHQL.toString());
    }
    else {
      voQueryResult.setRecordcount(voPage.getRecordcount());
    }

    //??????SELECT HQL??????
    strAllHQL.append("select V_HJ_HBBGXXB ").append(strFromHQL.toString());

    //debug info
    _log.info(strAllHQL.toString());

    //??????DAO??????
    try {
      PojoInfo  hjyw_hbbgxxbDAO = DAOFactory.
          createHJYW_HBBGXXBDAO();


      if (voPage.getRecordcount() == -1) {
        voQueryResult.setRecordcount(super.getCount(
            strCountHQL.
            toString()));
      }
      poList =super.getPageRecords(strAllHQL.
          toString(),
          new Object[]{},
          voPage.getPageindex(),
          voPage.getPagesize()).getList();

    }
    catch (DAOException ex) {
      throw ex;
    }

    //?????????Vo
    if (poList != null && poList.size() > 0) {
      voList = new ArrayList();
      for (int i = 0; i < poList.size(); i++) {
        PoV_HJ_HBBGXXB poV_HJ_HBBGXXB = (PoV_HJ_HBBGXXB)
            poList.get(i);

        VoHbbgxxHqFhxx voHbbgxxHqFhxx = new VoHbbgxxHqFhxx(
            poV_HJ_HBBGXXB);
        voList.add(voHbbgxxHqFhxx);
      }
    }

    voQueryResult.setPagelist(voList);
    voQueryResult.setVotype(VoHbbgxxHqFhxx.class);
    return voQueryResult;
  }

  /**
   * ?????????????????????????????????????????????PoHJXX_MLPXXXXB(?????????)+PoHJXX_RHFLXXB(??????????????????) + PoHJXX_CZRKJBXXB(?????????)?????????
   * @param strHQL
   * @param voPage
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoQueryResult queryRhflxx(String strHQL, VoPage voPage) throws
      ServiceException,
      DAOException {
    StringBuffer strAllHQL = new StringBuffer();
    StringBuffer strCountHQL = new StringBuffer();
    StringBuffer strFromHQL = new StringBuffer();
    List poList = null;
    List voList = null;
    VoQueryResult voQueryResult = new VoQueryResult();

    //??????FROM HQL??????
    strFromHQL.append(
        "from PoHJXX_CZRKJBXXB as HJXX_CZRKJBXXB,PoHJXX_RHFLXXB as HJXX_RHFLXXB ")
        .append("where HJXX_RHFLXXB.rynbid=HJXX_CZRKJBXXB.rynbid ");
    //????????????begin
    List sjfwList = XtywqxServiceImpl.SelectDataRange(this.getUserInfo().
        getYhid().toString(), PublicConstant.GNBH_HJ_RHFLXXHQ);
    if (sjfwList != null) {
      StringBuffer sjfwHQL = new StringBuffer();
      for (int iFW = 0; iFW < sjfwList.size(); iFW++) {
        VoXtsjfw voXtsjfw = (VoXtsjfw) sjfwList.get(iFW);
        //???(???)??????
        if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_JWH)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or HJXX_CZRKJBXXB.jcwh='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "HJXX_CZRKJBXXB.jcwh='" + voXtsjfw.getSjfw().trim() +
                         "' ");
        }
        //?????????
        else if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_PCS)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or HJXX_CZRKJBXXB.pcs='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "HJXX_CZRKJBXXB.pcs='" + voXtsjfw.getSjfw().trim() +
                         "' ");
        }
        //????????????
        else if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_XZQH)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or HJXX_CZRKJBXXB.ssxq='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "HJXX_CZRKJBXXB.ssxq='" +
                         voXtsjfw.getSjfw().trim() +
                         "' ");
        }
      } //for(int i = 0; i < sjfwList.size(); i++)
      if (sjfwHQL.length() > 0) {
        strFromHQL.append("and ( ")
            .append(sjfwHQL.toString())
            .append(") ");
      }
    }
    //????????????end
    if (strHQL != null && strHQL.trim().length() > 0) {
      strFromHQL.append("and ")
          .append(strHQL);
    }

    //??????COUNT HQL??????
    if (voPage.getRecordcount() == -1) {
      strCountHQL.append("select count(*) ").append(strFromHQL.toString());
    }
    else {
      voQueryResult.setRecordcount(voPage.getRecordcount());
    }

    //??????SELECT HQL??????
    strAllHQL.append("select HJXX_CZRKJBXXB,HJXX_RHFLXXB ").
        append(strFromHQL.toString());

    //debug info
    _log.info(strAllHQL.toString());

    //??????DAO??????
    try {
      PojoInfo  hjxx_rhflxxbDAO = DAOFactory.createHJXX_RHFLXXBDAO();


      if (voPage.getRecordcount() == -1) {
        voQueryResult.setRecordcount(super.getCount(
            strCountHQL.
            toString()));
      }
      poList =super.getPageRecords(strAllHQL.
          toString(),
          new Object[]{},
          voPage.getPageindex(),
          voPage.getPagesize()).getList();

    }
    catch (DAOException ex) {
      throw ex;
    }

    //?????????Vo
    if (poList != null && poList.size() > 0) {
      voList = new ArrayList();
      for (int i = 0; i < poList.size(); i++) {
        Object ob[] = (Object[]) poList.get(i);
        PoHJXX_CZRKJBXXB poHJXX_CZRKJBXXB = (PoHJXX_CZRKJBXXB) ob[0];
        PoHJXX_RHFLXXB poHJXX_RHFLXXB = (PoHJXX_RHFLXXB) ob[1];

        VoRhflxxHqFhxx voRhflxxHqFhxx = new VoRhflxxHqFhxx(poHJXX_CZRKJBXXB,
            null, poHJXX_RHFLXXB);
        voList.add(voRhflxxHqFhxx);
      }
    }

    voQueryResult.setPagelist(voList);
    voQueryResult.setVotype(VoRhflxxHqFhxx.class);
    return voQueryResult;
  }

  /**
   * ????????????????????????????????????????????????????????????PoHJYW_GMSFHMSXMFPXXB(???????????????????????????????????????)?????????
   * @param strHQL
   * @param voPage
   * @return
   */
  public VoQueryResult querySfhmfpxx(String strHQL, VoPage voPage) throws
      ServiceException,
      DAOException {

    StringBuffer strAllHQL = new StringBuffer();
    StringBuffer strCountHQL = new StringBuffer();
    StringBuffer strFromHQL = new StringBuffer();
    List poList = null;
    List voList = null;
    VoQueryResult voQueryResult = new VoQueryResult();

    //??????FROM HQL??????
    strFromHQL.append("from PoHJYW_GMSFHMSXMFPXXB as HJYW_GMSFHMSXMFPXXB ")
        .append("where 1=1 ");
    //???????????????
    if (strHQL != null && strHQL.trim().length() > 0) {
      strFromHQL.append("and ")
          .append(strHQL);
    }

    //??????COUNT HQL??????
    if (voPage.getRecordcount() == -1) {
      strCountHQL.append("select count(*) ").append(strFromHQL.toString());
    }
    else {
      voQueryResult.setRecordcount(voPage.getRecordcount());
    }

    //??????SELECT HQL??????
    strAllHQL.append("select HJYW_GMSFHMSXMFPXXB ").append(strFromHQL.
        toString());

    //debug info
    _log.info(strAllHQL.toString());

    //??????DAO??????
    try {
      PojoInfo  hjyw_gmsfhmsxmfpxxbDAO = DAOFactory.
          createHJYW_GMSFHMSXMFPXXBDAO();


      if (voPage.getRecordcount() == -1) {
        voQueryResult.setRecordcount(super.getCount(
            strCountHQL.
            toString()));
      }
      poList =super.getPageRecords(
          strAllHQL.
          toString(),
          new Object[]{},
          voPage.getPageindex(),
          voPage.getPagesize()).getList();

    }
    catch (DAOException ex) {
      throw ex;
    }

    //?????????Vo
    if (poList != null && poList.size() > 0) {
      voList = new ArrayList();
      for (int i = 0; i < poList.size(); i++) {
        PoHJYW_GMSFHMSXMFPXXB poHJYW_GMSFHMSXMFPXXB = (PoHJYW_GMSFHMSXMFPXXB)
            poList.get(i);

        VoSfhmfpxxHqFhxx voSfhmfpxxHqFhxx = new VoSfhmfpxxHqFhxx(
            poHJYW_GMSFHMSXMFPXXB);
        voList.add(voSfhmfpxxHqFhxx);
      }
    }

    voQueryResult.setPagelist(voList);
    voQueryResult.setVotype(VoSfhmfpxxHqFhxx.class);
    return voQueryResult;
  }

  /**
   * ????????????????????????(PoHJXX_RYZPXXB)
   * @param strHQL
   * @param voPage
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoQueryResult queryRyzpxx(String strHQL, VoPage voPage) throws
      ServiceException,
      DAOException {

    StringBuffer strAllHQL = new StringBuffer();
    StringBuffer strCountHQL = new StringBuffer();
    StringBuffer strFromHQL = new StringBuffer();
    List poList = null;
    List voList = null;
    VoQueryResult voQueryResult = new VoQueryResult();

    //??????FROM HQL??????
    strFromHQL.append("from PoHJXX_RYZPXXB as HJXX_RYZPXXB ")
        .append("where 1=1 ");
    //???????????????
    if (strHQL != null && strHQL.trim().length() > 0) {
      strFromHQL.append("and ")
          .append(strHQL);
    }

    //??????COUNT HQL??????
    if (voPage.getRecordcount() == -1) {
      strCountHQL.append("select count(*) ").append(strFromHQL.toString());
    }
    else {
      voQueryResult.setRecordcount(voPage.getRecordcount());
    }

    //??????SELECT HQL??????
    strAllHQL.append("select HJXX_RYZPXXB ").append(strFromHQL.toString());

    //debug info
    _log.info(strAllHQL.toString());

    //??????DAO??????
    try {
      PojoInfo  hjxx_ryzpxxbDAO = DAOFactory.createHJXX_RYZPXXBDAO();


      if (voPage.getRecordcount() == -1) {
        voQueryResult.setRecordcount(super.getCount(
            strCountHQL.
            toString()));
      }
      poList =super.getPageRecords(strAllHQL.
          toString(),
          new Object[]{},
          voPage.getPageindex(),
          voPage.getPagesize()).getList();

    }
    catch (DAOException ex) {
      throw ex;
    }

    //?????????Vo
    if (poList != null && poList.size() > 0) {
      voList = new ArrayList();
      for (int i = 0; i < poList.size(); i++) {
        PoHJXX_RYZPXXB poHJXX_RYZPXXB = (PoHJXX_RYZPXXB)
            poList.get(i);

        VoHJXX_RYZPXXB voRyzpxx = new VoHJXX_RYZPXXB(poHJXX_RYZPXXB);
        voList.add(voRyzpxx);
      }
    }

    voQueryResult.setPagelist(voList);
    voQueryResult.setVotype(VoHJXX_RYZPXXB.class);
    return voQueryResult;
  }

  /**
   * ?????????????????????????????????
   * @param rynbid
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public List queryCzrkdjbxx(Long rynbid) throws ServiceException,
      DAOException {

    List czrkdjbxxList = null;
    String strHQL;

    if (rynbid == null) {
      return null;
    }

    try {
      PojoInfo  hjxx_czrkjbxxbDAO = DAOFactory.
          createHJXX_CZRKJBXXBDAO();
      PojoInfo  hjxx_hxxbDAO = DAOFactory.createHJXX_HXXBDAO();
      PojoInfo  hjxx_mlpxxxxbDAO = DAOFactory.createHJXX_MLPXXXXBDAO();
      PojoInfo  zjxx_jmsfzxxbDAO = DAOFactory.createZJXX_JMSFZXXBDAO();

      //????????????

      //????????????
      PoHJXX_CZRKJBXXB poRyxx  = super.get(PoHJXX_CZRKJBXXB.class,
          rynbid);
      if (poRyxx == null) {
        throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                   "????????????????????????", null);
      }
      //?????????
      PoHJXX_HXXB poHxx  = super.get(PoHJXX_HXXB.class,poRyxx.getHhnbid());
      if (poHxx == null) {
        throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                   "?????????????????????", null);
      }
      //?????????
      PoHJXX_MLPXXXXB poMlpxxxx  = super.get(PoHJXX_MLPXXXXB.class,
          poRyxx.
          getMlpnbid());
      if (poMlpxxxx == null) {
        throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                   "?????????????????????", null);
      }
      //????????????
      PoHJXX_CZRKJBXXB poHzxx = null;
      if (PublicConstant.JLBZ_ZX.equals(poRyxx.getJlbz()) &&
          HjConstant.RYZT_ZC.equals(poRyxx.getRyzt())) {
        strHQL = "from PoHJXX_CZRKJBXXB where yhzgx < '10' and ryzt='" +
            HjConstant.RYZT_ZC + "' and cxbz='" + PublicConstant.CXBZ_FCX +
            "' and jlbz='" + PublicConstant.JLBZ_ZX + "' and hhnbid =" +
            poRyxx.getHhnbid();
      }
      else {
        strHQL = "from PoHJXX_CZRKJBXXB where yhzgx < '10' and hhnbid =" +
            poRyxx.getHhnbid() + " and qysj <= '" + poRyxx.getQysj() +
            "' and cxbz='" + PublicConstant.CXBZ_FCX + "' order by qysj desc";
      }
      List hzxxList =super.findAllByHQL(strHQL);
      if (hzxxList != null && hzxxList.size() > 0) {
        poHzxx = (PoHJXX_CZRKJBXXB) hzxxList.get(0);
      }
      //????????????
      PoZJXX_JMSFZXXB poZjxx = null;
      if (poRyxx.getNbsfzid() != null) {
        poZjxx  = super.get(PoZJXX_JMSFZXXB.class,poRyxx.getNbsfzid());
      }

      //????????????

      VoCzrkdjbHqFhxx vo = new VoCzrkdjbHqFhxx(poRyxx, poHxx, poMlpxxxx, poZjxx,
                                               poHzxx);
      vo.setGen_dz(this.generateZZ(poMlpxxxx,
                                   PublicConstant.XTKZCS_DZPZFS,
                                   PublicConstant.XTKZCS_DZPZFS_CZRKDJBZZ));

      czrkdjbxxList = new ArrayList();
      czrkdjbxxList.add(vo);
    }
    catch (DAOException ex) {
      //????????????
      throw ex;
    }
    catch (ServiceException ex) {
      //????????????
      throw ex;
    }

    return czrkdjbxxList;
  }

  /**
   * ????????????????????????
   * @param hjywid
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoQyzdyxxHqFhxx queryQyzdyxx(Long hjywid) throws ServiceException,
      DAOException {

    StringBuffer strHQL = new StringBuffer();
    VoQyzdyxxHqFhxx voQyzdyxxHqFhxx = null;
    VoZzbdryxx voZzbdryxx[] = null;
    VoQczxryxx voQczxryxx[] = null;

    if (hjywid == null) {
      return null;
    }

    try {
      PojoInfo  hjls_hjywlsbDAO = DAOFactory.createHJLS_HJYWLSBDAO();
      PojoInfo  hjyw_zzbdxxbDAO = DAOFactory.createHJYW_ZZBDXXBDAO();
      PojoInfo  hjyw_qczxxxbDAO = DAOFactory.createHJYW_QCZXXXBDAO();

      //????????????

      //??????????????????????????????
      PoHJLS_HJYWLSB poHjywlsxx  = super.get(PoHJLS_HJYWLSB.class,hjywid);
      if (poHjywlsxx == null) {
        throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                   "????????????????????????????????????", null);
      }

      //????????????
      if (PublicConstant.GNBH_HJ_QCZXYW.equals(poHjywlsxx.getYwbz())) {
        strHQL.append("select HJYW_QCZXXXB,HJXX_CZRKJBXXB,HJXX_MLPXXXXB from PoHJYW_QCZXXXB HJYW_QCZXXXB,PoHJXX_CZRKJBXXB HJXX_CZRKJBXXB,PoHJXX_MLPXXXXB HJXX_MLPXXXXB ")
            .append("where HJYW_QCZXXXB.hjywid=" + hjywid + " ")
            .append("and HJYW_QCZXXXB.rynbid=HJXX_CZRKJBXXB.rynbid ")
            .append("and HJXX_CZRKJBXXB.mlpnbid=HJXX_MLPXXXXB.mlpnbid order by HJYW_QCZXXXB.sbrjtgx desc ");
        List qczxxxList =super.findAllByHQL(strHQL.
            toString());
        if (qczxxxList != null && qczxxxList.size() > 0) {
          voQczxryxx = new VoQczxryxx[qczxxxList.size()];
          for (int i = 0; i < qczxxxList.size(); i++) {
            Object ob[] = (Object[]) qczxxxList.get(i);
            voQczxryxx[i] = new VoQczxryxx( (PoHJXX_CZRKJBXXB) ob[1],
                                           (PoHJXX_MLPXXXXB) ob[2],
                                           (PoHJYW_QCZXXXB) ob[0]);
          }
        }
      }
      //????????????
      else if (PublicConstant.GNBH_HJ_ZZBDYW.equals(poHjywlsxx.getYwbz())) {
        strHQL.append("select HJYW_ZZBDXXB,HJXX_CZRKJBXXB,yHJXX_MLPXXXXB,xHJXX_MLPXXXXB from PoHJYW_ZZBDXXB HJYW_ZZBDXXB,PoHJXX_CZRKJBXXB HJXX_CZRKJBXXB,PoHJXX_MLPXXXXB yHJXX_MLPXXXXB ,PoHJXX_MLPXXXXB xHJXX_MLPXXXXB,PoHJXX_HXXB HJXX_HXXB ")
            .append("where HJYW_ZZBDXXB.hjywid=" + hjywid + " ")
            .append("and HJYW_ZZBDXXB.yhhnbid=HJXX_HXXB.hhnbid and HJXX_HXXB.mlpnbid=yHJXX_MLPXXXXB.mlpnbid ")
            .append("and xHJXX_MLPXXXXB.mlpnbid=HJXX_CZRKJBXXB.mlpnbid ")
            .append("and HJYW_ZZBDXXB.rynbid=HJXX_CZRKJBXXB.rynbid ");
        List zzbdxxList =super.findAllByHQL(strHQL.
            toString());
        if (zzbdxxList != null && zzbdxxList.size() > 0) {
          voZzbdryxx = new VoZzbdryxx[zzbdxxList.size()];
          for (int i = 0; i < zzbdxxList.size(); i++) {
            Object ob[] = (Object[]) zzbdxxList.get(i);
            voZzbdryxx[i] = new VoZzbdryxx( (PoHJXX_CZRKJBXXB) ob[1],
                                           (PoHJXX_MLPXXXXB) ob[3],
                                           (PoHJXX_MLPXXXXB) ob[2],
                                           (PoHJYW_ZZBDXXB) ob[0]);
          }
        }
      }

      //????????????

      voQyzdyxxHqFhxx = new VoQyzdyxxHqFhxx();
      voQyzdyxxHqFhxx.setPoHJLS_HJYWLSB(poHjywlsxx);
      voQyzdyxxHqFhxx.setVoQczxryxx(voQczxryxx);
      voQyzdyxxHqFhxx.setVoZzbdryxx(voZzbdryxx);
    }
    catch (DAOException ex) {
      //????????????
      throw ex;
    }
    catch (ServiceException ex) {
      //????????????
      throw ex;
    }

    return voQyzdyxxHqFhxx;
  }

  /**
   * ?????????????????????????????????
   * @param voQyzbhhtxx
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoQyzbhhtywfhxx processQyzbhhtyw(VoQyzbhhtxx voQyzbhhtxx[]) throws
      ServiceException, DAOException {

    VoQyzbhhtywfhxx voQyzbhhtywfhxx = null;
    List qyzbhhtfhxxList = new ArrayList();
    String sjc = null; //?????????
    List qyzxxList = new ArrayList(); //???????????????List

    if (voQyzbhhtxx == null ||
        (voQyzbhhtxx != null && voQyzbhhtxx.length <= 0)) {
      return null;
    }

    try {
      PojoInfo  hjls_hjywlsbDAO = DAOFactory.createHJLS_HJYWLSBDAO();
      PojoInfo  hjyw_zzbdxxbDAO = DAOFactory.createHJYW_ZZBDXXBDAO();
      PojoInfo  hjyw_qczxxxbDAO = DAOFactory.createHJYW_QCZXXXBDAO();
      PojoInfo  hjsp_qyzxxbDAO = DAOFactory.createHJSP_QYZXXBDAO();
      PojoInfo  hjxx_czrkjbxxbDAO = DAOFactory.createHJXX_CZRKJBXXBDAO();
      PojoInfo  hjxx_mlpxxxxbDAO = DAOFactory.createHJXX_MLPXXXXBDAO();
      PojoInfo  xt_yhxxbDAO = DAOFactory.createXT_YHXXBDAO();
      PojoInfo  hjxx_hxxbDAO = DAOFactory.createHJXX_HXXBDAO();
      PojoInfo  xt_jwhxxbDAO = DAOFactory.createXT_JWHXXBDAO();

      ///////////////////////////////////////////
      //????????????

      //////////////////////////////////
      //????????????
      for (int i = 0; i < voQyzbhhtxx.length; i++) {
        VoXtywxz voLimit = XtywqxServiceImpl.VerifyBusinessLimit(
            PublicConstant.
            GNBH_HJ_QYZBHHTYW, voQyzbhhtxx[i]);
        //????????????
        if (voLimit.getLimitflag()) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLIMIT,
                                     "???????????????????????????????????????????????????" +
                                     voLimit.getLimitinfo(), null);
        }
      }

      /////////////////////////////////////////
      //???????????????
      sjc = this.getXTKZCS(PublicConstant.XTKZCS_XTSYSJC);
      if (sjc == null) {
        sjc = "";
      }

      ////////////////////////////////////////////
      //?????????????????????????????????
      for (int i = 0; i < voQyzbhhtxx.length; i++) {
        String qyyy = null; //????????????
        String qwdssxq = null; //?????????????????????
        String qwdxz = null; //???????????????
        String yzzssxq = null; //?????????????????????
        String yzzxz = null; //???????????????
        String cbr = null; //?????????

        //??????????????????????????????
        PoHJLS_HJYWLSB poHjywlsxx  = super.get(PoHJLS_HJYWLSB.class,
            voQyzbhhtxx[i].getHjywid());
        if (poHjywlsxx == null) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "????????????????????????????????????", null);
        }

        //???????????????
        if (voQyzbhhtxx[i].getDysx() != null
            && voQyzbhhtxx[i].getDysx().longValue() == 1
            && poHjywlsxx.getSlrid() != null) {
          PoXT_YHXXB poYhxx  = super.get(PoXT_YHXXB.class,poHjywlsxx.getSlrid());
          if (poYhxx != null) {
            cbr = poYhxx.getYhxm();
          }
        }

        //??????????????????
        PoHJXX_CZRKJBXXB poRyxx  = super.get(PoHJXX_CZRKJBXXB.class,
            voQyzbhhtxx[i].getRynbid());
        if (poRyxx == null) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "?????????????????????????????????", null);
        }

        //????????????
        if (PublicConstant.GNBH_HJ_QCZXYW.equals(poHjywlsxx.getYwbz())) {
          String strHQL = "from PoHJYW_QCZXXXB HJYW_QCZXXXB where hjywid=" +
              voQyzbhhtxx[i].getHjywid() + " and rynbid=" +
              voQyzbhhtxx[i].getRynbid();
          List qczxxxList =super.findAllByHQL(strHQL);
          if (qczxxxList != null && qczxxxList.size() > 0) {
            for (int k = 0; k < qczxxxList.size(); k++) {
              PoHJYW_QCZXXXB poQczxxx = (PoHJYW_QCZXXXB) qczxxxList.get(k);
              poQczxxx.setQyzbh(sjc + voQyzbhhtxx[i].getQyzbh());
             super.update(poQczxxx);

              qyyy = poQczxxx.getQclb();
              //??????(TMD????????????)
              if (voQyzbhhtxx[i].getDysx() != null
                  && voQyzbhhtxx[i].getDysx().longValue() == 1) {
                qwdssxq = poQczxxx.getQwdssxq();
                PoHJXX_MLPXXXXB poMlpxxxx = new PoHJXX_MLPXXXXB();
                poMlpxxxx.setSsxq(poQczxxx.getQwdssxq());
                poMlpxxxx.setPcs(poQczxxx.getQwdpcs());
                poMlpxxxx.setXzjd(poQczxxx.getQwdxzjd());
                poMlpxxxx.setJcwh(poQczxxx.getQwdjwh());
                poMlpxxxx.setJlx(poQczxxx.getQwdjlx());
                poMlpxxxx.setMlph(poQczxxx.getQwdmlph());
                poMlpxxxx.setMlxz(poQczxxx.getQwdxz());
                qwdxz = this.generateZZ(poMlpxxxx,
                                        PublicConstant.XTKZCS_DZPZFS,
                                        PublicConstant.XTKZCS_DZPZFS_EDZSLXX);
                poMlpxxxx  = super.get(PoHJXX_MLPXXXXB.class,poRyxx.
                    getMlpnbid());
                if (poMlpxxxx != null) {
                  yzzssxq = poMlpxxxx.getSsxq(); //?????????????????????
                  yzzxz = this.generateZZ(poMlpxxxx,
                                          PublicConstant.XTKZCS_DZPZFS,
                                          PublicConstant.XTKZCS_DZPZFS_EDZSLXX); //???????????????
                }
              }

              //??????????????????
              VoQyzbhhtfhxx vo = new VoQyzbhhtfhxx();
              vo.setRynbid(poQczxxx.getRynbid());
              vo.setQczxid(poQczxxx.getQczxid());
              qyzbhhtfhxxList.add(vo); ;
            }
          } //if(qczxxxList != null && qczxxxList.size() > 0)
        }
        //????????????
        else if (PublicConstant.GNBH_HJ_ZZBDYW.equals(poHjywlsxx.getYwbz())) {
          String strHQL = "from PoHJYW_ZZBDXXB HJYW_ZZBDXXB where hjywid=" +
              voQyzbhhtxx[i].getHjywid() + " and rynbid=" +
              voQyzbhhtxx[i].getRynbid();
          List zzbdxxList =super.findAllByHQL(strHQL);
          if (zzbdxxList != null && zzbdxxList.size() > 0) {
            for (int k = 0; k < zzbdxxList.size(); k++) {
              PoHJYW_ZZBDXXB poZzbdxx = (PoHJYW_ZZBDXXB) zzbdxxList.get(k);
              poZzbdxx.setQyzbh(sjc + voQyzbhhtxx[i].getQyzbh());
             super.update(poZzbdxx);

              qyyy = poZzbdxx.getZzbdlb();
              //??????(TMD????????????)
              if (voQyzbhhtxx[i].getDysx() != null
                  && voQyzbhhtxx[i].getDysx().longValue() == 1) {
                PoHJXX_MLPXXXXB poMlpxxxx = super.get(PoHJXX_MLPXXXXB.class,poRyxx.getMlpnbid());
                if (poMlpxxxx != null) {
                  qwdssxq = poMlpxxxx.getSsxq();
                  qwdxz = this.generateZZ(poMlpxxxx,
                                          PublicConstant.XTKZCS_DZPZFS,
                                          PublicConstant.XTKZCS_DZPZFS_EDZSLXX);
                }
                PoHJXX_HXXB poHxx  = super.get(PoHJXX_HXXB.class,poZzbdxx.
                    getYhhnbid());
                if (poHxx != null) {
                  poMlpxxxx  = super.get(PoHJXX_MLPXXXXB.class,poHxx.
                      getMlpnbid());
                  if (poMlpxxxx != null) {
                    yzzssxq = poMlpxxxx.getSsxq(); //?????????????????????
                    yzzxz = this.generateZZ(poMlpxxxx,
                                            PublicConstant.XTKZCS_DZPZFS,
                                            PublicConstant.
                                            XTKZCS_DZPZFS_EDZSLXX); //???????????????
                  } //if(poMlpxxxx!=null){
                } //if(poHxx!=null){
              }

              //??????????????????
              VoQyzbhhtfhxx vo = new VoQyzbhhtfhxx();
              vo.setRynbid(poZzbdxx.getRynbid());
              vo.setZzbdid(poZzbdxx.getZzbdid());
              qyzbhhtfhxxList.add(vo); ;
            }
          } //if(qczxxxList != null && qczxxxList.size() > 0)
        }
        else {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "????????????ID?????????????????????????????????????????????????????????????????????????????????????????????", null);
        }

        //?????????????????????_begin
        PoHJSP_QYZXXB poQyzxx = null;
        for (int n = 0; n < qyzxxList.size(); n++) {
          PoHJSP_QYZXXB po = (PoHJSP_QYZXXB) qyzxxList.get(n);
          if (voQyzbhhtxx[i].getQyzbh() != null &&
              voQyzbhhtxx[i].getQyzbh().equals(po.getQyzbh())) {
            poQyzxx = po;
            break;
          }
        }
        if (poQyzxx == null) {
          poQyzxx = new PoHJSP_QYZXXB();
          poQyzxx.setQyid( (Long) hjsp_qyzxxbDAO.getId());
          poQyzxx.setQyzbh(voQyzbhhtxx[i].getQyzbh());
          qyzxxList.add(poQyzxx);
        }
        if (voQyzbhhtxx[i].getDysx() != null &&
            voQyzbhhtxx[i].getDysx().longValue() == 1) {
          poQyzxx.setCzrgmsfhm(poRyxx.getGmsfhm());
          poQyzxx.setXm(poRyxx.getXm());
          poQyzxx.setCym(poRyxx.getCym());
          poQyzxx.setXb(poRyxx.getXb());
          poQyzxx.setMz(poRyxx.getMz());
          poQyzxx.setCsrq(poRyxx.getCsrq());
          poQyzxx.setCsdgjdq(poRyxx.getCsdgjdq());
          poQyzxx.setCsdssxq(poRyxx.getCsdssxq());
          poQyzxx.setCsdxz(poRyxx.getCsdxz());
          poQyzxx.setJggjdq(poRyxx.getJggjdq());
          poQyzxx.setJgssxq(poRyxx.getJgssxq());
          poQyzxx.setWhcd(poRyxx.getWhcd());
          poQyzxx.setZy(poRyxx.getZy());
          poQyzxx.setHyzk(poRyxx.getHyzk());
          poQyzxx.setQyyy(qyyy);
          poQyzxx.setQfrq(voQyzbhhtxx[i].getQfrq());
          poQyzxx.setYxqxjzrq(voQyzbhhtxx[i].getYxqxjzrq());
          poQyzxx.setYzzssxq(yzzssxq);
          poQyzxx.setYzzxz(yzzxz);
          poQyzxx.setQwdssxq(qwdssxq);
          poQyzxx.setQwdxz(qwdxz);
          poQyzxx.setCbr(cbr);
          poQyzxx.setYznf(voQyzbhhtxx[i].getYznf());//add hb 20060828 ????????????
          //Begin_???????????????????????????_2005/01/19 10:35
          String pcsmc = "";
          String jwhmc = "";
          PoHJXX_MLPXXXXB poMlpxxxx  = super.get(PoHJXX_MLPXXXXB.class,
              poRyxx.getMlpnbid());
          if (poMlpxxxx != null) {
            pcsmc = this.transDWDM(poMlpxxxx.getPcs());
            PoXT_JWHXXB poJwhxx  = super.get(PoXT_JWHXXB.class,
                poMlpxxxx.getJcwh());
            jwhmc = (poJwhxx != null ? poJwhxx.getMc() : "");
          }
          poQyzxx.setBz(pcsmc + jwhmc);
          //End_???????????????????????????_2005/01/19 10:35
        }
        else
        if (voQyzbhhtxx[i].getDysx() != null &&
            voQyzbhhtxx[i].getDysx().longValue() == 2) {
          poQyzxx.setSqryczrgx1(voQyzbhhtxx[i].getYczrgx());
          poQyzxx.setGmsfhm1(poRyxx.getGmsfhm());
          poQyzxx.setXm1(poRyxx.getXm());
          poQyzxx.setCym1(poRyxx.getCym());
          poQyzxx.setXb1(poRyxx.getXb());
          poQyzxx.setMz1(poRyxx.getMz());
          poQyzxx.setCsrq1(poRyxx.getCsrq());
          poQyzxx.setCsdgjdq1(poRyxx.getCsdgjdq());
          poQyzxx.setCsdssxq1(poRyxx.getCsdssxq());
          poQyzxx.setCsdxz1(poRyxx.getCsdxz());
          poQyzxx.setJggjdq1(poRyxx.getJggjdq());
          poQyzxx.setJgssxq1(poRyxx.getJgssxq());
          poQyzxx.setWhcd1(poRyxx.getWhcd());
          poQyzxx.setZy1(poRyxx.getZy());
          poQyzxx.setHyzk1(poRyxx.getHyzk());
          poQyzxx.setQyyy1(qyyy);
          poQyzxx.setYznf(voQyzbhhtxx[i].getYznf());//add hb 20060828 ????????????
        }
        else
        if (voQyzbhhtxx[i].getDysx() != null &&
            voQyzbhhtxx[i].getDysx().longValue() == 3) {
          poQyzxx.setSqryczrgx2(voQyzbhhtxx[i].getYczrgx());
          poQyzxx.setGmsfhm2(poRyxx.getGmsfhm());
          poQyzxx.setXm2(poRyxx.getXm());
          poQyzxx.setCym2(poRyxx.getCym());
          poQyzxx.setXb2(poRyxx.getXb());
          poQyzxx.setMz2(poRyxx.getMz());
          poQyzxx.setCsrq2(poRyxx.getCsrq());
          poQyzxx.setCsdgjdq2(poRyxx.getCsdgjdq());
          poQyzxx.setCsdssxq2(poRyxx.getCsdssxq());
          poQyzxx.setCsdxz2(poRyxx.getCsdxz());
          poQyzxx.setJggjdq2(poRyxx.getJggjdq());
          poQyzxx.setJgssxq2(poRyxx.getJgssxq());
          poQyzxx.setWhcd2(poRyxx.getWhcd());
          poQyzxx.setZy2(poRyxx.getZy());
          poQyzxx.setHyzk2(poRyxx.getHyzk());
          poQyzxx.setQyyy2(qyyy);
          poQyzxx.setYznf(voQyzbhhtxx[i].getYznf());//add hb 20060828 ????????????
        }
        else
        if (voQyzbhhtxx[i].getDysx() != null &&
            voQyzbhhtxx[i].getDysx().longValue() == 4) {
          poQyzxx.setSqryczrgx3(voQyzbhhtxx[i].getYczrgx());
          poQyzxx.setGmsfhm3(poRyxx.getGmsfhm());
          poQyzxx.setXm3(poRyxx.getXm());
          poQyzxx.setCym3(poRyxx.getCym());
          poQyzxx.setXb3(poRyxx.getXb());
          poQyzxx.setMz3(poRyxx.getMz());
          poQyzxx.setCsrq3(poRyxx.getCsrq());
          poQyzxx.setCsdgjdq3(poRyxx.getCsdgjdq());
          poQyzxx.setCsdssxq3(poRyxx.getCsdssxq());
          poQyzxx.setCsdxz3(poRyxx.getCsdxz());
          poQyzxx.setJggjdq3(poRyxx.getJggjdq());
          poQyzxx.setJgssxq3(poRyxx.getJgssxq());
          poQyzxx.setWhcd3(poRyxx.getWhcd());
          poQyzxx.setZy3(poRyxx.getZy());
          poQyzxx.setHyzk3(poRyxx.getHyzk());
          poQyzxx.setQyyy3(qyyy);
          poQyzxx.setYznf(voQyzbhhtxx[i].getYznf());//add hb 20060828 ????????????
        }
        //?????????????????????_end
      } //for (int i = 0; i < voQyzbhhtxx.length; i++)

      //?????????????????????
      for (int i = 0; i < qyzxxList.size(); i++) {
        PoHJSP_QYZXXB poQyzxx = (PoHJSP_QYZXXB) qyzxxList.get(i);
       super.create(poQyzxx);
      }

      ///////////////////////////////////////////
      //????????????

      /////////////////////////////////////////////
      //????????????????????????
      voQyzbhhtywfhxx = new VoQyzbhhtywfhxx();
      voQyzbhhtywfhxx.setVoQyzbhhtfhxx( (VoQyzbhhtfhxx[]) qyzbhhtfhxxList.
                                       toArray(new VoQyzbhhtfhxx[
                                               qyzbhhtfhxxList.size()]));
    }
    catch (DAOException ex) {
      //????????????
      throw ex;
    }
    catch (ServiceException ex) {
      //????????????
      throw ex;
    }

    return voQyzbhhtywfhxx;
  }

  /**
   * ????????????????????????
   * @param strHQL
   * @param voPage
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoQueryResult queryRybdxx(String strHQL, VoPage voPage) throws
      ServiceException, DAOException {

    StringBuffer strAllHQL = new StringBuffer();
    StringBuffer strCountHQL = new StringBuffer();
    StringBuffer strFromHQL = new StringBuffer();
    List poList = null;
    List voList = null;
    VoQueryResult voQueryResult = new VoQueryResult();

    //??????FROM HQL??????
    strFromHQL.append(
        "from PoHJTJ_RYBDXXB as HJTJ_RYBDXXB ")
        .append("where 1=1 ");
    //????????????begin
    List sjfwList = XtywqxServiceImpl.SelectDataRange(this.getUserInfo().
        getYhid().toString(), PublicConstant.GNBH_HJ_RYBDXXHQ);
    if (sjfwList != null) {
      StringBuffer sjfwHQL = new StringBuffer();
      for (int iFW = 0; iFW < sjfwList.size(); iFW++) {
        VoXtsjfw voXtsjfw = (VoXtsjfw) sjfwList.get(iFW);
        //???(???)??????
        if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_JWH)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or HJTJ_RYBDXXB.jcwh='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "HJTJ_RYBDXXB.jcwh='" + voXtsjfw.getSjfw().trim() +
                         "' ");
        }
        //?????????
        else if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_PCS)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or HJTJ_RYBDXXB.pcs='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "HJTJ_RYBDXXB.pcs='" + voXtsjfw.getSjfw().trim() +
                         "' ");
        }
        //????????????
        else if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_XZQH)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or HJTJ_RYBDXXB.ssxq='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "HJTJ_RYBDXXB.ssxq='" +
                         voXtsjfw.getSjfw().trim() +
                         "' ");
        }
      } //for(int i = 0; i < sjfwList.size(); i++)
      if (sjfwHQL.length() > 0) {
        strFromHQL.append("and ( ")
            .append(sjfwHQL.toString())
            .append(") ");
      }
    }
    //????????????end
    if (strHQL != null && strHQL.trim().length() > 0) {
      strFromHQL.append("and ")
          .append(strHQL);
    }

    //??????COUNT HQL??????
    if (voPage.getRecordcount() == -1) {
      strCountHQL.append("select count(*) ").append(strFromHQL.toString());
    }
    else {
      voQueryResult.setRecordcount(voPage.getRecordcount());
    }

    //??????SELECT HQL??????
    strAllHQL.append("select HJTJ_RYBDXXB ").append(strFromHQL.
        toString());

    //debug info
    _log.info(strAllHQL.toString());

    //??????DAO??????
    try {
      PojoInfo  hjyw_swzxxxbDAO = DAOFactory.
          createHJYW_SWZXXXBDAO();


      if (voPage.getRecordcount() == -1) {
        voQueryResult.setRecordcount(super.getCount(
            strCountHQL.
            toString()));
      }
      poList =super.getPageRecords(strAllHQL.
          toString(),
          new Object[]{},
          voPage.getPageindex(),
          voPage.getPagesize()).getList();

    }
    catch (DAOException ex) {
      throw ex;
    }

    //?????????Vo
    if (poList != null && poList.size() > 0) {
      voList = new ArrayList();
      for (int i = 0; i < poList.size(); i++) {
        PoHJTJ_RYBDXXB poRybdxx = (PoHJTJ_RYBDXXB) poList.get(i);

        VoRybdxxHqFhxx voRybdxxHqFhxx = new VoRybdxxHqFhxx(poRybdxx, null);
        PoHJXX_MLPXXXXB poMlpxxxxBDQ = new PoHJXX_MLPXXXXB();
        //??????????????????
        poMlpxxxxBDQ.setMlpnbid(voRybdxxHqFhxx.getBdqmlpnbid());
        poMlpxxxxBDQ.setSsxq(voRybdxxHqFhxx.getBdqssxq());
        poMlpxxxxBDQ.setJlx(voRybdxxHqFhxx.getBdqjlx());
        poMlpxxxxBDQ.setMlph(voRybdxxHqFhxx.getBdqmlph());
        poMlpxxxxBDQ.setMlxz(voRybdxxHqFhxx.getBdqmlxz());
        poMlpxxxxBDQ.setPcs(voRybdxxHqFhxx.getBdqpcs());
        poMlpxxxxBDQ.setZrq(voRybdxxHqFhxx.getBdqzrq());
        poMlpxxxxBDQ.setXzjd(voRybdxxHqFhxx.getBdqxzjd());
        poMlpxxxxBDQ.setJcwh(voRybdxxHqFhxx.getBdqjcwh());
        //??????????????????
        PoHJXX_MLPXXXXB poMlpxxxxBDH = new PoHJXX_MLPXXXXB();
        poMlpxxxxBDH.setMlpnbid(voRybdxxHqFhxx.getBdhmlpnbid());
        poMlpxxxxBDH.setSsxq(voRybdxxHqFhxx.getBdhssxq());
        poMlpxxxxBDH.setJlx(voRybdxxHqFhxx.getBdhjlx());
        poMlpxxxxBDH.setMlph(voRybdxxHqFhxx.getBdhmlph());
        poMlpxxxxBDH.setMlxz(voRybdxxHqFhxx.getBdhmlxz());
        poMlpxxxxBDH.setPcs(voRybdxxHqFhxx.getBdhpcs());
        poMlpxxxxBDH.setZrq(voRybdxxHqFhxx.getBdhzrq());
        poMlpxxxxBDH.setXzjd(voRybdxxHqFhxx.getBdhxzjd());
        poMlpxxxxBDH.setJcwh(voRybdxxHqFhxx.getBdhjcwh());
        voRybdxxHqFhxx.setBdqxz(this.generateZZ(poMlpxxxxBDQ,
                                                PublicConstant.XTKZCS_DZPZFS,
                                                PublicConstant.
                                                XTKZCS_DZPZFS_BDQXZ));
        voRybdxxHqFhxx.setBdhxz(this.generateZZ(poMlpxxxxBDH,
                                                PublicConstant.XTKZCS_DZPZFS,
                                                PublicConstant.
                                                XTKZCS_DZPZFS_BDHXZ));
        voList.add(voRybdxxHqFhxx);
      }
    }

    voQueryResult.setPagelist(voList);
    voQueryResult.setVotype(VoRybdxxHqFhxx.class);
    return voQueryResult;
  }

  /**
   * ??????????????????
   * @param voPzxx[] - ????????????
   * @return VoPzbcywfhxx - ??????????????????????????????
   * @throws ServiceException
   * @throws DAOException
   */
  public VoPzbcywfhxx processPzbcyw(VoPzxx voPzxx[]) throws ServiceException,
      DAOException {

    VoPzbcywfhxx voPzbcywfhxx = null;
    VoPzbcfhxx voPzbcfhxx[] = null;
    String now = StringUtils.getServiceTime();

    //????????????
    if (voPzxx == null || (voPzxx != null && voPzxx.length <= 0)) {
      return null;
    }

    try {
      PojoInfo  hjxx_zplsbDAO = DAOFactory.createHJXX_ZPLSBDAO();
      PojoInfo  zjyw_slxxbDAO = DAOFactory.createZJYW_SLXXBDAO();
      PojoInfo  hjxx_pzrzbDAO = DAOFactory.createHJXX_PZRZBDAO();
      PojoInfo  hjxx_smkzpbDAO = DAOFactory.createHJXX_SMKZPBDAO();

      ////////////////////////////////////////////
      //????????????

      /////////////////////////////////////////////
      //??????????????????
      voPzbcfhxx = new VoPzbcfhxx[voPzxx.length];
      for (int i = 0; i < voPzxx.length; i++) {
        //add by hh 20060403 ??????????????????????????????????????????????????????10?????????
        List lstPzlsb =super.findAllByHQL(
            " from PoHJXX_ZPLSB where slh='" + voPzxx[i].getSlh() +
            "' or gmsfhm='" + voPzxx[i].getGmsfhm() + "' ");
        if(lstPzlsb!=null && lstPzlsb.size()>10){
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "[" + voPzxx[i].getGmsfhm() +
                                     voPzxx[i].getSlh() + "]??????????????????10???,???????????????!", null);
        }
        //add by hh 20060112 ?????????????????????0,???????????????
        if (voPzxx[i].getZp().length() <= 0) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "[" + voPzxx[i].getGmsfhm() +
                                     voPzxx[i].getSlh() + "]???????????????0????????????????????????", null);
        }
        //?????????????????????????????????
        if ("1".equals(this.getXTKZCS(PublicConstant.XTKZCS_SHSCSMKZPBXX)) &&
            voPzxx[i].getSlh() != null && voPzxx[i].getSlh().length() > 0 &&
            "0".equals(voPzxx[i].getSlh().substring(0, 1))) {
          //???????????????????????????
          VoHJXX_SMKZPB voSmkzpxx = new VoHJXX_SMKZPB();
          voSmkzpxx.setZplsid( (Long) hjxx_smkzpbDAO.getId());
          voSmkzpxx.setSlh(voPzxx[i].getSlh());
          voSmkzpxx.setGmsfhm(voPzxx[i].getGmsfhm());
          voSmkzpxx.setZp(voPzxx[i].getZp());
          voSmkzpxx.setBcsj(now);
          voSmkzpxx.setIpdz(BaseContext.getUser().getIp());
          voSmkzpxx.setYhdlm(this.getUserInfo().getYhdlm());
          voSmkzpxx.setYhxm(this.getUserInfo().getYhxm());
          voSmkzpxx.setYhid(this.getUserInfo().getYhid());
          voSmkzpxx.setYhdw(this.getUserInfo().getDwdm());
         super.create(voSmkzpxx.toPoHJXX_SMKZPB());

          //???????????????????????????(2005/04/27 14:15:00)
          voPzxx[i].setGmsfhm(voPzxx[i].getSlh().length() > 18 ?
                              voPzxx[i].
                              getSlh().substring(voPzxx[i].getSlh().length() -
                                                 18) : "");
          voPzxx[i].setSlh(null);
        }

        //??????????????????
        VoHJXX_ZPLSB voZplsxx = new VoHJXX_ZPLSB();
        voZplsxx.setZplsid( (Long) hjxx_zplsbDAO.getId());
        voZplsxx.setSlh(voPzxx[i].getSlh());
        voZplsxx.setGmsfhm(voPzxx[i].getGmsfhm());
        voZplsxx.setZp(voPzxx[i].getZp());
        voZplsxx.setBcsj(now);
        voZplsxx.setCzrid(this.getUserInfo().getYhid());

        //20110524 hb ??????????????????begin
        voZplsxx.setJlbz("0");//20111110 hb ?????????????????????????????????????????????0
        //??????????????????end

        //20100113 hubin ????????????????????????
        /**
        _log.warn("??????????????????????????????[zplsid:" + voZplsxx.getZplsid()
                  + "][slh:" + voZplsxx.getSlh() + "][gmsfhm:" + voZplsxx.getGmsfhm()
                  + "][czrid:" + voZplsxx.getCzrid() + "][zp:" + voZplsxx.getZp().length()
                  + "][bcsj:" + voZplsxx.getBcsj() + "]");
        **/
       super.create(voZplsxx.toPoHJXX_ZPLSB());

        //??????????????????????????????(????????????????????????)
        if (voZplsxx.getSlh() != null && voZplsxx.getSlh().length() > 0) {
          String strHQL = "from PoZJYW_SLXXB where slh='" +
              voZplsxx.getSlh() + "' and slzt='" +
              ZjConstant.ZJ_BLBZ_2ID_CS + "' ";
          List slxxList =super.findAllByHQL(strHQL);
          if (slxxList != null && slxxList.size() > 0) {
            for (int k = 0; k < slxxList.size(); k++) {
              //????????????????????????
              PoZJYW_SLXXB poSlxx = (PoZJYW_SLXXB) slxxList.get(k);
              poSlxx.setSlzt(ZjConstant.ZJ_BLBZ_2ID_SJWZ);
             super.update(poSlxx);
            }
          }
        }

        //??????????????????
        PoHJXX_PZRZB poPzrzxx = new PoHJXX_PZRZB();
        poPzrzxx.setPzrzid( (Long) hjxx_pzrzbDAO.getId());
        poPzrzxx.setZplsid(voZplsxx.getZplsid());
        poPzrzxx.setIpdz(BaseContext.getUser().getIp());
        poPzrzxx.setYhdlm(this.getUserInfo().getYhdlm());
        poPzrzxx.setYhxm(this.getUserInfo().getYhxm());
        poPzrzxx.setYhid(this.getUserInfo().getYhid());
        poPzrzxx.setYhdw(this.getUserInfo().getDwdm());
        poPzrzxx.setBcsj(now);
        poPzrzxx.setGmsfhm(voZplsxx.getGmsfhm());
        poPzrzxx.setSlh(voZplsxx.getSlh());
        poPzrzxx.setPzxlh(voPzxx[i].getPzxlh());
       super.create(poPzrzxx);

        //??????????????????
        voPzbcfhxx[i] = new VoPzbcfhxx();
        voPzbcfhxx[i].setGmsfhm(voZplsxx.getGmsfhm());
        voPzbcfhxx[i].setSlh(voZplsxx.getSlh());
        voPzbcfhxx[i].setZplsid(voZplsxx.getZplsid());
      }

      //????????????????????????
      voPzbcywfhxx = new VoPzbcywfhxx();
      voPzbcywfhxx.setVoPzbcfhxx(voPzbcfhxx);

      /////////////////////////////////////////////////
      //????????????
    }
    catch (DAOException ex) {
      //????????????
      throw ex;
    }
    catch (ServiceException ex) {
      //????????????
      throw ex;
    }
    catch (Exception ex) {
      //????????????
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }

    return voPzbcywfhxx;
  }

  /**
   * ????????????????????????
   * @param strHQL
   * @param voPage
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoQueryResult queryPzbcxx(String strHQL, VoPage voPage) throws
      ServiceException, DAOException {
    StringBuffer strAllHQL = new StringBuffer();
    StringBuffer strCountHQL = new StringBuffer();
    StringBuffer strFromHQL = new StringBuffer();
    List poList = null;
    List voList = null;
    VoQueryResult voQueryResult = new VoQueryResult();

    //??????FROM HQL??????
    strFromHQL.append("from PoHJXX_ZPLSB as HJXX_ZPLSB ")
        .append("where 1=1 ");
    //20101221 hb ?????????????????????????????????
    //20111110 hb ?????????????????????????????????????????????
    if ("1".equals(this.getXtkzcsAsStr("5026"))) {
      String bdtj = this.getXtkzcsAsVo("5026").getBz();
      if (bdtj == null){
        bdtj = "";
      }
      String bddwdm = this.getUserInfo().getDwdm().trim();
      if (bddwdm.length() > 6) {
        bddwdm = bddwdm.substring(0, 6);
        int bdi = bdtj.indexOf(bddwdm);
        if (bdi >= 0) {
          strFromHQL.append(" and jlbz = '2' ");
        }
      }
    }

    if (strHQL != null && strHQL.trim().length() > 0) {
      strFromHQL.append("and ")
          .append(strHQL);
    }

    //??????COUNT HQL??????
    if (voPage.getRecordcount() == -1) {
      strCountHQL.append("select count(*) ").append(strFromHQL.toString());
    }
    else {
      voQueryResult.setRecordcount(voPage.getRecordcount());
    }

    //??????SELECT HQL??????
    strAllHQL.append("select HJXX_ZPLSB ").append(strFromHQL.toString());

    //debug info
    _log.info(strAllHQL.toString());

    //??????DAO??????
    try {
      PojoInfo  hjxx_zplsbDAO = DAOFactory.createHJXX_ZPLSBDAO();


      if (voPage.getRecordcount() == -1) {
        voQueryResult.setRecordcount(super.getCount(
            strCountHQL.
            toString()));
      }
      poList =super.getPageRecords(strAllHQL.
          toString(),
          new Object[]{},
          voPage.getPageindex(),
          voPage.getPagesize()).getList();

    }
    catch (DAOException ex) {
      throw ex;
    }

    //?????????Vo
    if (poList != null && poList.size() > 0) {
      voList = new ArrayList();
      for (int i = 0; i < poList.size(); i++) {
        PoHJXX_ZPLSB poHJXX_ZPLSB = (PoHJXX_ZPLSB)
            poList.get(i);

        VoHJXX_ZPLSB voZplsxx = new VoHJXX_ZPLSB(poHJXX_ZPLSB);
        voList.add(voZplsxx);
      }
    }

    voQueryResult.setPagelist(voList);
    voQueryResult.setVotype(VoHJXX_ZPLSB.class);
    return voQueryResult;
  }

  /**
   * ????????????????????????
   * @param voZpscxx - ??????????????????
   * @return voZpscywfhxx - ??????????????????????????????
   * @throws DAOException
   * @throws ServiceException
   */
  public VoZpscywfhxx processZpscyw(VoZpscxx voZpscxx[]) throws DAOException,
      ServiceException {

    VoZpscywfhxx voZpscywfhxx = null;
    VoZpscfhxx voZpscfhxx[] = null;
    Long hjywid = null;
    String strHQL = null;
    String now = StringUtils.getServiceTime();

    //????????????
    if (voZpscxx == null || (voZpscxx != null && voZpscxx.length <= 0)) {
      return null;
    }

    try {
      PojoInfo  hjxx_czrkjbxxbDAO = DAOFactory.createHJXX_CZRKJBXXBDAO();
      PojoInfo  hjyw_slxxbDAO = DAOFactory.createZJYW_SLXXBDAO();
      PojoInfo  hjls_hjywlsbDAO = DAOFactory.createHJLS_HJYWLSBDAO();
      PojoInfo  hjxx_ryzpxxbDAO = DAOFactory.createHJXX_RYZPXXBDAO();

      ////////////////////////////////////////
      //????????????

      /////////////////////////////////////
      //??????????????????ID
      hjywid = (Long) hjls_hjywlsbDAO.getId();

      ///////////////////////////////////////
      //????????????????????????
      voZpscfhxx = new VoZpscfhxx[voZpscxx.length];
      for (int i = 0; i < voZpscxx.length; i++) {

        //????????????????????????????????????????????????
        strHQL = "select count(*) from PoZJYW_SLXXB where zpid=" +
            voZpscxx[i].getZpid() + " and not (slzt = '" +
            ZjConstant.ZJ_BLBZ_2ID_ZF + "' or slzt = '" +
            ZjConstant.ZJ_BLBZ_2ID_ZFGD + "' or slzt = '" +
            ZjConstant.ZJ_BLBZ_2ID_YCB + "') ";
        if (super.getCount(strHQL) > 0) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "?????????????????????????????????????????????????????????????????????????????????", null);
        }

        //?????????????????????zpid????????????
        strHQL = "from PoHJXX_CZRKJBXXB where zpid=" + voZpscxx[i].getZpid();
        List ryxxList =super.findAllByHQL(strHQL);
        if (ryxxList != null && ryxxList.size() > 0) {
          for (int k = 0; k < ryxxList.size(); k++) {
            PoHJXX_CZRKJBXXB poRyxx = (PoHJXX_CZRKJBXXB) ryxxList.get(k);
            if (!HjConstant.RYSDZT_ZC.equals(poRyxx.getRysdzt())) {
              throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                         "?????????????????????????????????????????????????????????????????????", null);
            }
            poRyxx.setZpid(null);
           super.update(poRyxx);
          }
        } //if(ryxxList!=null && ryxxList.size() > 0){

        //??????????????????
        PoHJXX_RYZPXXB poZpxx  = super.get(PoHJXX_RYZPXXB.class,voZpscxx[
            i].getZpid());
        if (poZpxx == null) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "?????????????????????????????????????????????????????????????????????", null);
        }
        super.delete(poZpxx);

        //??????????????????
        voZpscfhxx[i] = new VoZpscfhxx();
        voZpscfhxx[i].setZpid(poZpxx.getZpid());
        voZpscfhxx[i].setRyid(poZpxx.getRyid());
        voZpscfhxx[i].setXm(poZpxx.getXm());
        voZpscfhxx[i].setGmsfhm(poZpxx.getGmsfhm());
      }

      ////////////////////////////////////////
      //??????????????????????????????
      this.saveHJYWLSXX(hjywid, PublicConstant.GNBH_HJ_ZPSCYW,
                        PublicConstant.HJYWLS_YWLX_GR,
                        voZpscxx.length, null, now);

      ////////////////////////////////////////
      //????????????????????????
      voZpscywfhxx = new VoZpscywfhxx();
      voZpscywfhxx.setHjywid(hjywid);
      voZpscywfhxx.setVoZpscfhxx(voZpscfhxx);

      ////////////////////////////////////////
      //????????????
    }
    catch (DAOException ex) {
      //????????????
      throw ex;
    }
    catch (ServiceException ex) {
      //????????????
      throw ex;
    }
    catch (Exception ex) {
      //????????????
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }

    return voZpscywfhxx;
  }

  /**
   * ????????????????????????
   * @param strHQL
   * @param voPage
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoQueryResult queryPzrzxx(String strHQL, VoPage voPage) throws
      ServiceException, DAOException {

    StringBuffer strAllHQL = new StringBuffer();
    StringBuffer strCountHQL = new StringBuffer();
    StringBuffer strFromHQL = new StringBuffer();
    List poList = null;
    List voList = null;
    VoQueryResult voQueryResult = new VoQueryResult();

    //??????FROM HQL??????
    strFromHQL.append("from PoHJXX_PZRZB as HJXX_PZRZB ")
        .append("where 1=1 ");
    //????????????begin
    List sjfwList = XtywqxServiceImpl.SelectDataRange(this.getUserInfo().
        getYhid().toString(), PublicConstant.GNBH_HJ_PZRZXXHQ);
    if (sjfwList != null) {
      StringBuffer sjfwHQL = new StringBuffer();
      for (int iFW = 0; iFW < sjfwList.size(); iFW++) {
        VoXtsjfw voXtsjfw = (VoXtsjfw) sjfwList.get(iFW);
        if (PublicConstant.XT_QX_PCS.equals(voXtsjfw.getSjfwbz())) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or yhdw='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "yhdw='" + voXtsjfw.getSjfw().trim() +
                         "' ");
        }
        //????????????
        else if (PublicConstant.XT_QX_XZQH.equals(voXtsjfw.getSjfwbz())) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or yhdw like '" + voXtsjfw.getSjfw().trim() +
                         "%' " :
                         "yhdw like '" +
                         voXtsjfw.getSjfw().trim() +
                         "%' ");
        }

      } //for(int i = 0; i < sjfwList.size(); i++)
      if (sjfwHQL.length() > 0) {
        strFromHQL.append("and ( ")
            .append(sjfwHQL.toString())
            .append(") ");
      }
    }
    //????????????end
    if (strHQL != null && strHQL.trim().length() > 0) {
      strFromHQL.append("and ")
          .append(strHQL);
    }

    //??????COUNT HQL??????
    if (voPage.getRecordcount() == -1) {
      strCountHQL.append("select count(*) ").append(strFromHQL.toString());
    }
    else {
      voQueryResult.setRecordcount(voPage.getRecordcount());
    }

    //??????SELECT HQL??????
    strAllHQL.append("select HJXX_PZRZB ").append(strFromHQL.toString());

    //debug info
    _log.info(strAllHQL.toString());

    //??????DAO??????
    try {
      PojoInfo  hjxx_pzrzbDAO = DAOFactory.createHJXX_PZRZBDAO();


      if (voPage.getRecordcount() == -1) {
        voQueryResult.setRecordcount(super.getCount(
            strCountHQL.
            toString()));
      }
      poList =super.getPageRecords(strAllHQL.
          toString(),
          new Object[]{},
          voPage.getPageindex(),
          voPage.getPagesize()).getList();

    }
    catch (DAOException ex) {
      throw ex;
    }

    voQueryResult.setPagelist(poList);
    voQueryResult.setVotype(PoHJXX_PZRZB.class);
    return voQueryResult;
  }

  /**
   * ??????????????????????????????
   * @param strHQL
   * @param voPage
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoQueryResult queryPzrzxxByYCL(String strHQL, VoPage voPage) throws
      ServiceException, DAOException {
    StringBuffer strAllHQL = new StringBuffer();
    StringBuffer strCountHQL = new StringBuffer();
    StringBuffer strFromHQL = new StringBuffer();
    List poList = null;
    List voList = null;
    VoQueryResult voQueryResult = new VoQueryResult();

    //??????FROM HQL??????
    strFromHQL.append(
        "from PoHJXX_PZRZB as HJXX_PZRZB,PoZJYW_SLXXB as ZJYW_SLXXB ")
        .append("where  HJXX_PZRZB.nbslid=ZJYW_SLXXB.nbslid ");
    //????????????begin
    List sjfwList = XtywqxServiceImpl.SelectDataRange(this.getUserInfo().
        getYhid().toString(), PublicConstant.GNBH_HJ_PZRZXXHQ_YCL);
    if (sjfwList != null) {
      StringBuffer sjfwHQL = new StringBuffer();
      for (int iFW = 0; iFW < sjfwList.size(); iFW++) {
        VoXtsjfw voXtsjfw = (VoXtsjfw) sjfwList.get(iFW);
        if (PublicConstant.XT_QX_PCS.equals(voXtsjfw.getSjfwbz())) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or yhdw='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "yhdw='" + voXtsjfw.getSjfw().trim() +
                         "' ");
        }
      } //for(int i = 0; i < sjfwList.size(); i++)
      if (sjfwHQL.length() > 0) {
        strFromHQL.append("and ( ")
            .append(sjfwHQL.toString())
            .append(") ");
      }
    }
    //????????????end
    if (strHQL != null && strHQL.trim().length() > 0) {
      strFromHQL.append("and ")
          .append(strHQL);
    }

    //??????COUNT HQL??????
    if (voPage.getRecordcount() == -1) {
      strCountHQL.append("select count(*) ").append(strFromHQL.toString());
    }
    else {
      voQueryResult.setRecordcount(voPage.getRecordcount());
    }

    //??????SELECT HQL??????
    strAllHQL.append("select HJXX_PZRZB,ZJYW_SLXXB ").append(strFromHQL.
        toString());

    //debug info
    _log.info(strAllHQL.toString());

    //??????DAO??????
    try {
      PojoInfo  hjxx_pzrzbDAO = DAOFactory.createHJXX_PZRZBDAO();


      if (voPage.getRecordcount() == -1) {
        voQueryResult.setRecordcount(super.getCount(
            strCountHQL.
            toString()));
      }
      poList =super.getPageRecords(strAllHQL.
          toString(),
          new Object[]{},
          voPage.getPageindex(),
          voPage.getPagesize()).getList();

    }
    catch (DAOException ex) {
      throw ex;
    }

    //?????????Vo
    if (poList != null && poList.size() > 0) {
      voList = new ArrayList();
      for (int i = 0; i < poList.size(); i++) {
        Object ob[] = (Object[]) poList.get(i);
        PoHJXX_PZRZB poPzrzxx = (PoHJXX_PZRZB) ob[0];
        PoZJYW_SLXXB poSlxx = (PoZJYW_SLXXB) ob[1];

        VoPzrzxxHqFhxx voPzrzxxHqFhxx = new VoPzrzxxHqFhxx(poPzrzxx, poSlxx);
        voList.add(voPzrzxxHqFhxx);
      }
    }

    voQueryResult.setPagelist(voList);
    voQueryResult.setVotype(VoPzrzxxHqFhxx.class);
    return voQueryResult;
  }

  /**
   * ??????????????????????????????
   * @param voPzzpscxx - ????????????????????????
   * @return voPzzpscywfhxx - ????????????????????????????????????
   * @throws DAOException
   * @throws ServiceException
   */
  public VoPzzpscywfhxx processPzzpscyw(VoPzzpscxx voPzzpscxx[]) throws
      DAOException, ServiceException {

    VoPzzpscywfhxx voPzzpscywfhxx = null;
    VoPzzpscfhxx voPzzpscfhxx[] = null;
    String strHQL = null;

    //????????????
    if (voPzzpscxx == null || (voPzzpscxx != null && voPzzpscxx.length <= 0)) {
      return null;
    }

    try {
      PojoInfo  hjxx_zplsbDAO = DAOFactory.createHJXX_ZPLSBDAO();
      PojoInfo  hjxx_pzrzbDAO = DAOFactory.createHJXX_PZRZBDAO();

      //????????????

      //??????????????????????????????
      voPzzpscfhxx = new VoPzzpscfhxx[voPzzpscxx.length];
      for (int i = 0; i < voPzzpscxx.length; i++) {
        //??????????????????
        PoHJXX_ZPLSB poZplsxx  = super.get(PoHJXX_ZPLSB.class,voPzzpscxx[i].
            getZplsid());
        if (poZplsxx == null) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_DAO_NOTEXIST,
                                     "??????????????????????????????????????????????????????????????????????????????", null);
        }
        super.delete(poZplsxx);
        //??????????????????
        strHQL = "from PoHJXX_PZRZB where zplsid=" + voPzzpscxx[i].getZplsid();
        List pzrzxxList =super.findAllByHQL(strHQL);
        if (pzrzxxList != null && pzrzxxList.size() > 0) {
          for (int k = 0; k < pzrzxxList.size(); k++) {
            PoHJXX_PZRZB poPzrzxx = (PoHJXX_PZRZB) pzrzxxList.get(k);
            super.delete(poPzrzxx);
          }
        }

        //??????????????????
        voPzzpscfhxx[i] = new VoPzzpscfhxx();
        voPzzpscfhxx[i].setZplsid(voPzzpscxx[i].getZplsid());
      }

      //????????????????????????
      voPzzpscywfhxx = new VoPzzpscywfhxx();
      voPzzpscywfhxx.setVoPzzpscfhxx(voPzzpscfhxx);

      //????????????
    }
    catch (DAOException ex) {
      //????????????
      throw ex;
    }
    catch (ServiceException ex) {
      //????????????
      throw ex;
    }
    catch (Exception ex) {
      //????????????
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }

    return voPzzpscywfhxx;
  }

  /**
   * ????????????????????????
   * @param voHjdyHqFhxx VoHjdyHqFhxx[] ????????????
   * @throws ServiceException
   * @throws DAOException
   * @return String
   */
  public int processDyxxbcyw(VoHjdyHqxx voHjdyHqxx[]) throws
      ServiceException,
      DAOException {
    VoHjdyHqxx voHjdyxx = null;
    PoHJXX_DYXXB poDyxx = null;
    String czsj = StringUtils.getServiceTime();

    if (voHjdyHqxx == null ||
        (voHjdyHqxx != null && voHjdyHqxx.length <= 0)) {
      return -1;
    }

    try {
      PojoInfo  hjxx_czrkjbxxbDAO = DAOFactory.
          createHJXX_CZRKJBXXBDAO();
      PojoInfo  hjxx_mlpxxxxbDAO = DAOFactory.createHJXX_MLPXXXXBDAO();
      PojoInfo  hjxx_dyxxbDAO = DAOFactory.createHJXX_DYXXBDAO();

      ///////////////////////////////////////
      //????????????

      for (int i = 0; i < voHjdyHqxx.length; i++) {
        voHjdyxx = voHjdyHqxx[i];

        //??????????????????
        PoHJXX_CZRKJBXXB poRyxx  = super.get(PoHJXX_CZRKJBXXB.class,
            voHjdyxx.getRynbid());
        if (poRyxx == null) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "????????????????????????????????????????????????????????????", null);
        }
        poDyxx = new PoHJXX_DYXXB();
        //BeanUtils.copyProperties(poDyxx, poRyxx);
        poDyxx.setRyid(poRyxx.getRyid());
        poDyxx.setRynbid(poRyxx.getRynbid());
        poDyxx.setGmsfhm(poRyxx.getGmsfhm());
        poDyxx.setXm(poRyxx.getXm());
        poDyxx.setSsxq(poRyxx.getSsxq());

        //???????????????
        PoHJXX_MLPXXXXB poMlpxx  = super.get(PoHJXX_MLPXXXXB.class,poRyxx.
            getMlpnbid());
        if (poMlpxx == null) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "??????????????????????????????????????????????????????????????????", null);
        }
        poDyxx.setPcs(poMlpxx.getPcs());
        poDyxx.setJcwh(poMlpxx.getJcwh());
        poDyxx.setJlx(poMlpxx.getJlx());
        poDyxx.setMlph(poMlpxx.getMlph());
        poDyxx.setMlpnbid(poMlpxx.getMlpnbid());
        poDyxx.setMlxz(poMlpxx.getMlxz());
        poDyxx.setZrq(poMlpxx.getZrq());
        poDyxx.setXzjd(poMlpxx.getXzjd());

        //??????????????????
        poDyxx.setDylb(voHjdyxx.getDylb());
        poDyxx.setYznf(voHjdyxx.getYznf());
        poDyxx.setZjbh(voHjdyxx.getZjbh());
        //?????????????????????
        poDyxx.setDyid((Long) hjxx_dyxxbDAO.getId());
        poDyxx.setCzip(BaseContext.getUser().getIp());
        poDyxx.setSldw(this.getUserInfo().getDwdm());
        poDyxx.setSlrid(this.getUserInfo().getYhid());
        poDyxx.setSlsj(czsj);

       super.create(poDyxx);
      } //for (int i = 0; i < voHjdyHqFhxx.length; i++)

      //????????????

    }
    catch (DAOException ex) {
      //????????????
      throw ex;
    }
    catch (ServiceException ex) {
      //????????????
      throw ex;
    }
    catch (Exception ex) {
      //????????????
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }

    return 0;
  }

  /**
   * ??????????????????(PoHJXX_DYXXB)
   * @param strHQL
   * @param voPage
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoQueryResult queryDyxx(String strHQL, VoPage voPage) throws
      ServiceException,
      DAOException {

    StringBuffer strAllHQL = new StringBuffer();
    StringBuffer strCountHQL = new StringBuffer();
    StringBuffer strFromHQL = new StringBuffer();
    List poList = null;
    List voList = null;
    VoQueryResult voQueryResult = new VoQueryResult();

    //??????FROM HQL??????
    strFromHQL.append("from PoHJXX_DYXXB as HJXX_DYXXB ")
        .append("where 1=1 ");
    //????????????begin
    List sjfwList = XtywqxServiceImpl.SelectDataRange(this.getUserInfo().
        getYhid().toString(), PublicConstant.GNBH_HJ_HBBGXXHQ);
    if (sjfwList != null) {
      StringBuffer sjfwHQL = new StringBuffer();
      for (int iFW = 0; iFW < sjfwList.size(); iFW++) {
        VoXtsjfw voXtsjfw = (VoXtsjfw) sjfwList.get(iFW);
        //???(???)??????
        if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_JWH)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or jcwh='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "jcwh='" + voXtsjfw.getSjfw().trim() +
                         "' ");
        }
        //?????????
        else if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_PCS)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or pcs='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "pcs='" + voXtsjfw.getSjfw().trim() +
                         "' ");
        }
        //????????????
        else if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_XZQH)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or ssxq='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "ssxq='" +
                         voXtsjfw.getSjfw().trim() +
                         "' ");
        }
      } //for(int i = 0; i < sjfwList.size(); i++)
      if (sjfwHQL.length() > 0) {
        strFromHQL.append("and ( ")
            .append(sjfwHQL.toString())
            .append(") ");
      }
    }
    //????????????end

    if (strHQL != null && strHQL.trim().length() > 0) {
      strFromHQL.append("and ")
          .append(strHQL);
    }

    //??????COUNT HQL??????
    if (voPage.getRecordcount() == -1) {
      strCountHQL.append("select count(*) ").append(strFromHQL.toString());
    }
    else {
      voQueryResult.setRecordcount(voPage.getRecordcount());
    }

    //??????SELECT HQL??????
    strAllHQL.append("select HJXX_DYXXB ").append(strFromHQL.toString());

    //debug info
    _log.info(strAllHQL.toString());

    //??????DAO??????
    try {
      PojoInfo  hjxx_dyxxbDAO = DAOFactory.createHJXX_DYXXBDAO();


      if (voPage.getRecordcount() == -1) {
        voQueryResult.setRecordcount(super.getCount(
            strCountHQL.
            toString()));
      }
      poList =super.getPageRecords(strAllHQL.
          toString(),
          new Object[]{},
          voPage.getPageindex(),
          voPage.getPagesize()).getList();

    }
    catch (DAOException ex) {
      throw ex;
    }

    //?????????Vo
    if (poList != null && poList.size() > 0) {
      voList = new ArrayList();
      for (int i = 0; i < poList.size(); i++) {
        PoHJXX_DYXXB poHJXX_DYXXB = (PoHJXX_DYXXB)
            poList.get(i);

        VoHjdyHqFhxx voHjdyHqFhxx = new VoHjdyHqFhxx(poHJXX_DYXXB);
        voList.add(voHjdyHqFhxx);
      }
    }

    voQueryResult.setPagelist(voList);
    voQueryResult.setVotype(VoHjdyxx.class);
    return voQueryResult;
  }

  /**
   * ?????????????????????????????????
   * @param strHQL
   * @param voPage
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoQueryResult queryLssbxx(String strHQL, VoPage voPage) throws
      ServiceException,
      DAOException {
    StringBuffer strAllHQL = new StringBuffer();
    StringBuffer strCountHQL = new StringBuffer();
    StringBuffer strFromHQL = new StringBuffer();
    List poList = null;
    List voList = null;
    VoQueryResult voQueryResult = new VoQueryResult();

    //??????FROM HQL??????
    strFromHQL.append(
        "from PoOLD_HJTJ_RYBDXXB as OLD_HJTJ_RYBDXXB ").append("where 1=1 ");
    //????????????begin
    List sjfwList = XtywqxServiceImpl.SelectDataRange(this.getUserInfo().
        getYhid().toString(), PublicConstant.GNBH_HJ_LSRYBDXXHQ);
    if (sjfwList != null) {
      StringBuffer sjfwHQL = new StringBuffer();
      for (int iFW = 0; iFW < sjfwList.size(); iFW++) {
        VoXtsjfw voXtsjfw = (VoXtsjfw) sjfwList.get(iFW);
        //???(???)??????
        if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_JWH)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or OLD_HJTJ_RYBDXXB.jcwh='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "OLD_HJTJ_RYBDXXB.jcwh='" + voXtsjfw.getSjfw().trim() +
                         "' ");
        }
        //?????????
        else if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_PCS)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or OLD_HJTJ_RYBDXXB.pcs='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "OLD_HJTJ_RYBDXXB.pcs='" + voXtsjfw.getSjfw().trim() +
                         "' ");
        }
        //????????????
        else if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_XZQH)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or OLD_HJTJ_RYBDXXB.ssxq='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "OLD_HJTJ_RYBDXXB.ssxq='" +
                         voXtsjfw.getSjfw().trim() +
                         "' ");
        }

      } //for(int i = 0; i < sjfwList.size(); i++)
      if (sjfwHQL.length() > 0) {
        strFromHQL.append("and ( ")
            .append(sjfwHQL.toString())
            .append(") ");
      }
    }
    //????????????end
    if (strHQL != null && strHQL.trim().length() > 0) {
      strFromHQL.append("and ")
          .append(strHQL);
    }

    //??????COUNT HQL??????
    if (voPage.getRecordcount() == -1) {
      strCountHQL.append("select count(*) ").append(strFromHQL.toString());
    }
    else {
      voQueryResult.setRecordcount(voPage.getRecordcount());
    }

    //??????SELECT HQL??????
    strAllHQL.append("select OLD_HJTJ_RYBDXXB ").append(strFromHQL.
        toString());

    //debug info
    _log.info(strAllHQL.toString());

    //??????DAO??????
    try {
      PojoInfo  hjtj_rybdxxbDAO = DAOFactory.createOLD_HJTJ_RYBDXXBDAO();


      if (voPage.getRecordcount() == -1) {
        voQueryResult.setRecordcount(super.getCount(
            strCountHQL.
            toString()));
      }
      poList =super.getPageRecords(strAllHQL.
                                                  toString(),
                                                  new Object[]{},
                                                  voPage.getPageindex(),
                                                  voPage.getPagesize()).getList();

    }
    catch (DAOException ex) {
      throw ex;
    }

    //?????????Vo
    if (poList != null && poList.size() > 0) {
      voList = new ArrayList();
      for (int i = 0; i < poList.size(); i++) {
        PoOLD_HJTJ_RYBDXXB poOLD_rybdxxb = (PoOLD_HJTJ_RYBDXXB) poList.get(i);

        voList.add(poOLD_rybdxxb);
      }
    }

    voQueryResult.setPagelist(voList);
    voQueryResult.setVotype(PoOLD_HJTJ_RYBDXXB.class);
    return voQueryResult;
  }
  /**
   * ?????????????????????????????????
   * @param strHQL
   * @param voPage
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoQueryResult queryLsbgxx(String strHQL, VoPage voPage) throws
      ServiceException,
      DAOException {
    StringBuffer strAllHQL = new StringBuffer();
    StringBuffer strCountHQL = new StringBuffer();
    StringBuffer strFromHQL = new StringBuffer();
    List poList = null;
    List voList = null;
    VoQueryResult voQueryResult = new VoQueryResult();

    //??????FROM HQL??????
    strFromHQL.append(
        "from PoOLD_HJYW_BGGZXXB as OLD_HJYW_BGGZXXB ").append("where 1=1 ");
    //????????????begin
    List sjfwList = XtywqxServiceImpl.SelectDataRange(this.getUserInfo().
        getYhid().toString(), PublicConstant.GNBH_HJ_LSRYBGXXHQ);
    if (sjfwList != null) {
      StringBuffer sjfwHQL = new StringBuffer();
      for (int iFW = 0; iFW < sjfwList.size(); iFW++) {
        VoXtsjfw voXtsjfw = (VoXtsjfw) sjfwList.get(iFW);
        //???(???)??????
        if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_JWH)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or OLD_HJYW_BGGZXXB.jcwh='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "OLD_HJYW_BGGZXXB.jcwh='" + voXtsjfw.getSjfw().trim() +
                         "' ");
        }
        //?????????
        else if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_PCS)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or OLD_HJYW_BGGZXXB.pcs='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "OLD_HJYW_BGGZXXB.pcs='" + voXtsjfw.getSjfw().trim() +
                         "' ");
        }
        //????????????
        else if (voXtsjfw.getSjfwbz().equals(PublicConstant.XT_QX_XZQH)) {
          sjfwHQL.append(sjfwHQL.length() > 0 ?
                         "or OLD_HJYW_BGGZXXB.ssxq='" + voXtsjfw.getSjfw().trim() +
                         "' " :
                         "OLD_HJYW_BGGZXXB.ssxq='" +
                         voXtsjfw.getSjfw().trim() +
                         "' ");
        }

      } //for(int i = 0; i < sjfwList.size(); i++)
      if (sjfwHQL.length() > 0) {
        strFromHQL.append("and ( ")
            .append(sjfwHQL.toString())
            .append(") ");
      }
    }
    //????????????end
    if (strHQL != null && strHQL.trim().length() > 0) {
      strFromHQL.append("and ")
          .append(strHQL);
    }

    //??????COUNT HQL??????
    if (voPage.getRecordcount() == -1) {
      strCountHQL.append("select count(*) ").append(strFromHQL.toString());
    }
    else {
      voQueryResult.setRecordcount(voPage.getRecordcount());
    }

    //??????SELECT HQL??????
    strAllHQL.append("select OLD_HJYW_BGGZXXB ").append(strFromHQL.
        toString());

    //debug info
    _log.info(strAllHQL.toString());

    //??????DAO??????
    try {
      PojoInfo  hjyw_bggzxxbDAO = DAOFactory.createOLD_HJYW_BGGZXXBDAO();


      if (voPage.getRecordcount() == -1) {
        voQueryResult.setRecordcount(super.getCount(
            strCountHQL.
            toString()));
      }
      poList =super.getPageRecords(strAllHQL.toString(),
    		  								new Object[]{},
                                                  voPage.getPageindex(),
                                                  voPage.getPagesize()).getList();

    }
    catch (DAOException ex) {
      throw ex;
    }

    //?????????Vo
    if (poList != null && poList.size() > 0) {
      voList = new ArrayList();
      for (int i = 0; i < poList.size(); i++) {
        PoOLD_HJYW_BGGZXXB poOLD_rybgxxb = (PoOLD_HJYW_BGGZXXB) poList.get(i);

        voList.add(poOLD_rybgxxb);
      }
    }

    voQueryResult.setPagelist(voList);
    voQueryResult.setVotype(PoOLD_HJYW_BGGZXXB.class);
    return voQueryResult;
  }

  /**
   * ????????????????????????????????????
   * @param voCxbcrzxx - ??????????????????????????????
   * @return String
   * @throws DAOException
   * @throws ServiceException
   */
  public String processCxjgbcrz(String gnbh,String cxyj) throws DAOException,
      ServiceException {

    String now = StringUtils.getServiceTime();

    //????????????
    if (gnbh == null && cxyj == null) {
      return null;
    }

    try {
      PojoInfo  tj_cxjgbcrzbDAO = DAOFactory.createTJ_CXJGBCRZBDAO();

      //????????????

      //????????????????????????????????????
      PoTJ_CXJGBCRZB poTj_cxjgbcrzb = new PoTJ_CXJGBCRZB();
      poTj_cxjgbcrzb.setCxrzid((Long)tj_cxjgbcrzbDAO.getId());
      poTj_cxjgbcrzb.setGnbh(gnbh);
      if (cxyj.length()>1000){
        cxyj = cxyj.substring(0,1000);
      }
      poTj_cxjgbcrzb.setCxyj(cxyj);
      poTj_cxjgbcrzb.setBcsj(now);
      poTj_cxjgbcrzb.setCzrid(this.getUserInfo().getYhid());
      poTj_cxjgbcrzb.setCzrdw(this.getUserInfo().getDwdm());
     super.create(poTj_cxjgbcrzb);

      //????????????
    }
    catch (DAOException ex) {
      //????????????
      throw ex;
    }
    catch (ServiceException ex) {
      //????????????
      throw ex;
    }
    catch (Exception ex) {
      //????????????
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }

    return "1";
  }

  public void gmsfhmCheck(Map params, SimpleJson simpleJson){
    PojoInfo  hjxx_czrkjbxxbDAO = DAOFactory.createHJXX_CZRKJBXXBDAO();
    String sfz = (String)params.get("sfz");
    String hql = "from PoHJXX_CZRKJBXXB a where a.ryzt='0' and a.jlbz='1' and a.cxbz='0' and (";
    String sfzs[] = sfz.split(",");
    int count = 0;
    for(int i=0;i<sfzs.length;i++){
      sfz = sfzs[i].trim();
      if(sfz.equals(""))
        continue;

      if(count>0) hql += " or ";

      hql += " a.gmsfhm='" + sfz + "'";

      count++;
    }
    hql += ")";

    List list = super.findEntities(hql);
    String msg = "";
    Set set = new HashSet();
    for(int i=0;i<list.size();i++){
      PoHJXX_CZRKJBXXB ry = (PoHJXX_CZRKJBXXB)list.get(i);
      if(set.contains(ry.getGmsfhm()))
        continue;
      else
        set.add(ry.getGmsfhm());

      if(!msg.equals(""))
        msg += ",";

      msg += ry.getGmsfhm();
    }
    if(!msg.equals("")){
      msg = "??????????????????" + msg + "???????????????????????????????????????????????????";
      simpleJson.setSuccess(false);
      simpleJson.setMessage(msg);
    }else{
      simpleJson.setSuccess(true);
    }
  }

	@Override
	public VoGmsfhmfpfhxx getSfz(ExtMap<String, Object> params) {
		
		String now = StringUtils.getServiceTime();
		String rynbid = params.getString("rynbid");
		String xm = params.getString("ryxm");
		String xb = params.getString("ryxb");
		String csrq = params.getString("csrq");
		
		
		PojoInfo  hjls_hjywlsbDAO = DAOFactory.createHJLS_HJYWLSBDAO();
		Long hjywid = (Long) hjls_hjywlsbDAO.getId();
		
		
		if(CommonUtil.isNotBlank(rynbid)) {
			Page p = super.getPageRecords("/conf/segment/common", "queryLodops", params);
			if(p != null && p.getList() != null && p.getList().size() > 0) {
				PoHJXX_CZRKJBXXB czrk = (PoHJXX_CZRKJBXXB) p.getList().get(0);
				VoGmsfhmfpfhxx voGmsfhmfpfhxx = null;
				
				PoHJXX_MLPXXXXB mlp = super.get(PoHJXX_MLPXXXXB.class,czrk.getMlpnbid());
				
				VoGmsfhmfpsqxx voGmsfhmfpsqxx = new VoGmsfhmfpsqxx();
		          //??????????????????????????????????????????
		          voGmsfhmfpsqxx.setCsrq(csrq);
		          voGmsfhmfpsqxx.setRyid(czrk.getRynbid());
		          voGmsfhmfpsqxx.setXb(xb);
		          voGmsfhmfpsqxx.setXm(xm);
		          voGmsfhmfpsqxx.setXzqh(mlp.getSsxq());
		          voGmsfhmfpsqxx.setPcs(mlp.getPcs());
		          voGmsfhmfpsqxx.setXzjd(mlp.getXzjd());
				
				voGmsfhmfpfhxx = this.assignGMSFHM(hjywid, voGmsfhmfpsqxx, now);
				if (voGmsfhmfpfhxx == null || (voGmsfhmfpfhxx != null && voGmsfhmfpfhxx.getGmsfhm() == null)
						|| (voGmsfhmfpfhxx != null && voGmsfhmfpfhxx.getGmsfhm() != null
								&& voGmsfhmfpfhxx.getGmsfhm().length() != 18)) {
					throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC, "?????????????????????????????????", null);
				} else {
					
					System.out.println("???????????????	:" + voGmsfhmfpfhxx.getGmsfhm());
					
					return voGmsfhmfpfhxx;
				}
				/*
		          voGmsfhmfpfhxx = new VoGmsfhmfpfhxx();
		          voGmsfhmfpfhxx.setGmsfhm("310107198909065674");
		          
		          return voGmsfhmfpfhxx;
		          */
			}
		}
		
		return null;
	}

	@Override
	public PoHJXX_CZRKJBXXB processCzrkzt(ExtMap<String, Object> params) {
		
		try {
			PojoInfo  jssdlogxxbDAO = DAOFactory.createJSSDLOGXXBDAO();
			Long rynbid = params.getLong("rynbid");
			
			PoHJXX_CZRKJBXXB czrk = super.get(PoHJXX_CZRKJBXXB.class, rynbid);
			
			String rysdzt = params.getString("rysdzt");
			PoJSSDLOGXXB pojssdlogxxb = new PoJSSDLOGXXB();
			AuthToken u = HSession.getBaseUser();
			BeanUtils.copyProperties(pojssdlogxxb, czrk);
			pojssdlogxxb.setJssdlogxxbid((Long) jssdlogxxbDAO.getId());
			pojssdlogxxb.setCzyid(this.getUser().getYhid());
			pojssdlogxxb.setCzsj(DateHelper.formateDate("yyyyMMddHHmmss"));
			pojssdlogxxb.setIp(u.getIp());
			pojssdlogxxb.setJssdyy(params.getString("jssdyy"));
			if(rysdzt.equals("0")) {//
				pojssdlogxxb.setDz("??????");
			}else if(rysdzt.equals("6")) {//??????
				pojssdlogxxb.setDz("??????");
			}
			super.create(pojssdlogxxb);
			if (rysdzt.equals(czrk.getRysdzt())) {
				throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC, "???????????????????????????????????????????????????????????????", null);
			}
			czrk.setRysdzt(rysdzt);
			super.update(czrk);
			return czrk;
		}  catch (DAOException ex) {
		      //????????????
		      throw ex;
		    }
		    catch (ServiceException ex) {
		      //????????????
		      throw ex;
		    }
		    catch (InvocationTargetException ex) {
		      //????????????
		      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
		    }
		    catch (IllegalAccessException ex) {
		      //????????????
		      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
		    }
		    catch (Exception ex) {
		      //????????????
		      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
		    }
	}
	public void updateHzyw(String hzywid,Long hjywid,Long spywid){
	      if(CommonUtil.isNotBlank(hzywid)){
	          List<?> list = findEntities("from PoHZ_ZJ_SB a where a.id=" + hzywid);
	          
	          if(list.size()>0){
	              PoHZ_ZJ_SB sb = (PoHZ_ZJ_SB)list.get(0);
	              String kzz = super.getXTKZCS("10031");
	              String kzz1 = super.getXTKZCS("10032");
	              String kzz2 = super.getXTKZCS("10037");
	              //1.??????????????????   2.wx_code????????????    add by zjm 20191106 
	                if(kzz.equals("1")) {//????????????1??????????????????????????????????????????HZPT_RECEIVESTATE????????????
	              	  if(CommonUtil.isNotEmpty(sb.getWx_code())) {
	              		  updateHzptReceiveState(kzz,sb);
	              	  }
	                }
	                if(kzz1.equals("1")) {//????????????1??????????????????????????????????????????HZPT_RECEIVESTATE????????????
	              	  if(CommonUtil.isNotEmpty(sb.getWx_code())) {
	              		  insertEms(kzz1,sb);
	              	  }
	                }
	              if(sb.getPch()!=null && !sb.getPch().equals("")){
	                //???????????????????????????
	                list = findEntities("from PoHZ_ZJ_SB a where a.pch='" + sb.getPch() + "' and a.clbs='0' ");
	                int pccount = list.size();
	                for(int index=0;index<pccount;index++){
	                  PoHZ_ZJ_SB sbx = (PoHZ_ZJ_SB)list.get(index);
	                  sbx.setClbs("1");
	                  if(hjywid>0) {
	                	 sbx.setHjywid(hjywid);  
	                  }
	                  if(spywid>0) {
	                	 sbx.setSpywid(spywid);  
	                  }
	                  sbx.setBlrsfz(this.getUser().getGmsfhm());
	                  sbx.setHjdpcs(this.getUser().getDwdm());
	                  sbx.setClsj(new java.sql.Timestamp(new Date().getTime()));
	                  update(sbx);
	                  if(kzz2.equals("1")) {//add by zjm 20200710 ????????????2????????????????????????????????????
		                	autoInsertHzjsbs(kzz2,sbx);
		                }
	                }
	              }else{
	                sb.setClbs("1");
	                if(hjywid>0) {
	                	sb.setHjywid(hjywid);
	                }
	                if(spywid>0) {
	                	sb.setSpywid(spywid);
	                }
	                sb.setBlrsfz(this.getUser().getGmsfhm());
	                sb.setHjdpcs(this.getUser().getDwdm());
	                sb.setClsj(new java.sql.Timestamp(new Date().getTime()));
	                update(sb);
	                if(kzz2.equals("1")) {//add by zjm 20200710 ????????????2????????????????????????????????????
	                	autoInsertHzjsbs(kzz2,sb);
	                }
	              }
	          }
	       }
	}
	public void updateHzptReceiveState(String kzz,PoHZ_ZJ_SB sbx) {
		String ywlb = sbx.getYwlb();
        String wx_code = sbx.getWx_code();
        PoXT_XTCSB xt_csb = new PoXT_XTCSB();
		if(ywlb.equals("1")) {//????????????
      	  xt_csb = super.getXTCSB("9038", "1");
        }else if(ywlb.equals("2")) {//????????????
      	  xt_csb = super.getXTCSB("9038", "2");
        }else if(ywlb.equals("3")) {//???????????????????????????
      	  if(sbx.getSbzt().equals("1")) {//??????????????????
      		  xt_csb = super.getXTCSB("9038", "3");
      	  }else {//????????????
      		  xt_csb = super.getXTCSB("9038", "4");
      	  }
        }else if(ywlb.equals("4")) {//????????????
      	  xt_csb = super.getXTCSB("9038", "5");
        }else if(ywlb.equals("5")) {//????????????
      	  xt_csb = super.getXTCSB("9038", "6");
        }else if(ywlb.equals("6")) {//????????????
      	  xt_csb = super.getXTCSB("9038", "7");
        }else if(ywlb.equals("7")) {//????????????
      	  xt_csb = super.getXTCSB("9038", "8");
        }else if(ywlb.equals("8")) {//????????????
      	  xt_csb = super.getXTCSB("9038", "9");
        }else if(ywlb.equals("9")) {//????????????
      	  xt_csb = super.getXTCSB("9038", "10");
        }else if(ywlb.equals("16")) {//????????????
      	  xt_csb = super.getXTCSB("9038", "11");
        }
		if(CommonUtil.isNotEmpty(wx_code)&&(kzz).equals("1")) {
			try {
				receiveStateService.updataReceiveState(wx_code, xt_csb.getMc());
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}
	public void insertEms(String kzz,PoHZ_ZJ_SB sbx) {
		 if(CommonUtil.isNotEmpty(sbx.getWx_code())&&(kzz).equals("1")) {
				try {
					String organid = super.getXTCSB("9041", "1").getMc();
					String organname = super.getXTCSB("9041", "2").getMc();
					String dwdm = super.getUser().getDwdm();
					receiveStateService.insertEmsSentInfo(sbx, organid,organname,dwdm); //???????????????????????????????????????????????????????????????????????? 20200212
				} catch (Exception e) {
					throw new ServiceException(e.toString());
				}
				
			}
	}
	private void autoInsertHzjsbs(String kzz, PoHZ_ZJ_SB sb) {
		 if(CommonUtil.isNotEmpty(sb.getFjid())&&(kzz).equals("1")) {
			 String ywlb = sb.getYwlb();
			 String ywlbH = null;
//			 4. ??????????????????????????????????????????????????????????????????????????????????????????  ????????????????????????????????????????????????10???????????????????????????????????????????????????????????????ywlb????????????10?????????????????????;
//			 5. ????????????????????????????????????????????????????????????????????????ywlb????????????11????????????????????? 
//			 6. ????????????????????????????????????????????????????????????ywlb????????????12?????????????????????
//			 7. ????????????????????????????????????????????????????????????ywlb????????????14?????????????????????
		      if(ywlb.equals("1")) {//????????????       
		    	  ywlbH="10";
		      }else if(ywlb.equals("2")) {//????????????
		    	  ywlbH="12";
		      }else if(ywlb.equals("3")) {//???????????????????????????
		    	  if(sb.getSbzt().equals("1")) {//??????????????????
		    		//????????????????????????  
		    		  if(sb.getQcdssxq().startsWith("34")) {
		    			  ywlbH="11";
		    		  }
		    	  }else {//????????????
		    		  ywlbH="10";
		    	  }
		      }else if(ywlb.equals("4")) {//????????????
		    	  ywlbH="10";
		      }else if(ywlb.equals("5")) {//????????????
		    	  ywlbH="10";
		      }else if(ywlb.equals("6")) {//????????????
		    	  ywlbH="10";
		      }else if(ywlb.equals("7")) {//????????????
		    	  ywlbH="14";
		      }else if(ywlb.equals("8")) {//????????????
		    	  ywlbH="10";
		      }else if(ywlb.equals("9")) {//????????????
		    	  ywlbH="10";
		      }else if(ywlb.equals("16")) {//????????????
		    	  ywlbH="10";
		      }else if(ywlb.equals("17")) {//????????????
		    	  ywlbH="10";
		      }
				try {
					if(ywlbH!=null) {
						PojoInfo  hz_js_bsDAO = DAOFactory.createHZ_JS_BSDAO();
						PoHZ_ZJ_SB hz_zj_sbTemp = new PoHZ_ZJ_SB();
				    	BeanUtils.copyProperties(hz_zj_sbTemp, sb);
				    	Long id = (Long)hz_js_bsDAO.getId();
				    	hz_zj_sbTemp.setId(id);
//				    	hz_zj_sbTemp.setFjid("1");
				    	hz_zj_sbTemp.setClsj(null);
				    	hz_zj_sbTemp.setFjdy("1");
				    	hz_zj_sbTemp.setClbs("0");
				    	hz_zj_sbTemp.setYwlb(ywlbH);
						super.create(hz_zj_sbTemp);
					}
				} catch (Exception e) {
					throw new ServiceException(e.toString());
				}
				
			}
	}
}
