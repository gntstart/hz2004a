package com.hzjc.hz2004.po;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="V_HJ_HJBLXXB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoV_HJ_HJBLXXB
    implements com.hzjc.wsstruts.po.PO {
	/*
	*户籍补录ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
  private Long hjblid;
  private Long rynbid;
  private String hjbllb;
  private Long hjywid;
  private String cxbz;
  private Long cxrid;
  private String cxsj;
  private Long cxhjywid;
  private Long ryid;
  private Long hhnbid;
  private String gmsfhm;
  private String xm;
  private String xb;
  private String mz;
  private String cssj;
  private String csdssxq;
  private Long mlpnbid;
  private String ssxq;
  private String jlx;
  private String mlph;
  private String mlxz;
  private String pcs;
  private String zrq;
  private String xzjd;
  private String jcwh;
  private String pxh;
  private String ywlx;
  private Long czsm;
  private String sbsj;
  private String sbryxm;
  private String sbrgmsfhm;
  private String slsj;
  private String sldw;
  private Long slrid;
  private String hzxm;
  private String yhzgx;
  private String hzgmsfhm;
  private String hb;
  private String csrq;
  private String pozjhm;
  private String pozjzl;
  private String powwx;
  private String powwm;
  private String mqwwm;
  private String mqwwx;
  private String fqwwm;
  private String fqwwx;
  private String mqzjhm;
  private String mqzjzl;
  private String fqzjhm;
  private String fqzjzl;
  private String jhryzjzl;
  private String jhryzjhm;
  private String jhrywwx;
  private String jhrywwm;
  private String jhrylxdh;
  private String jhrezjzl;
  private String jhrezjhm;
  private String jhrewwx;
  private String jhrewwm;
  private String jhrelxdh;
  private String xmx;
  private String xmm;
  private String jgxz;
  private String qyldyy;
  private String sbrjtgx;

  public String getCsrq() {
    return csrq;
  }

  public void setCsrq(String csrq) {
    this.csrq = csrq;
  }

  public String getHb() {
    return hb;
  }

  public void setHb(String hb) {
    this.hb = hb;
  }

  public void setHjblid(Long hjblid) {
    this.hjblid = hjblid;
  }

  public Long getHjblid() {
    return hjblid;
  }

  public void setRynbid(Long rynbid) {
    this.rynbid = rynbid;
  }

  public Long getRynbid() {
    return rynbid;
  }

  public void setHjbllb(String hjbllb) {
    this.hjbllb = hjbllb;
  }

  public String getHjbllb() {
    return hjbllb;
  }

  public void setHjywid(Long hjywid) {
    this.hjywid = hjywid;
  }

  public Long getHjywid() {
    return hjywid;
  }

  public void setCxbz(String cxbz) {
    this.cxbz = cxbz;
  }

  public String getCxbz() {
    return cxbz;
  }

  public void setCxrid(Long cxrid) {
    this.cxrid = cxrid;
  }

  public Long getCxrid() {
    return cxrid;
  }

  public void setCxsj(String cxsj) {
    this.cxsj = cxsj;
  }

  public String getCxsj() {
    return cxsj;
  }

  public void setCxhjywid(Long cxhjywid) {
    this.cxhjywid = cxhjywid;
  }

  public Long getCxhjywid() {
    return cxhjywid;
  }

  public void setRyid(Long ryid) {
    this.ryid = ryid;
  }

  public Long getRyid() {
    return ryid;
  }

  public void setHhnbid(Long hhnbid) {
    this.hhnbid = hhnbid;
  }

  public Long getHhnbid() {
    return hhnbid;
  }

  public void setGmsfhm(String gmsfhm) {
    this.gmsfhm = gmsfhm;
  }

  public String getGmsfhm() {
    return gmsfhm;
  }

  public void setXm(String xm) {
    this.xm = xm;
  }

  public String getXm() {
    return xm;
  }

  public void setXb(String xb) {
    this.xb = xb;
  }

  public String getXb() {
    return xb;
  }

  public void setMz(String mz) {
    this.mz = mz;
  }

  public String getMz() {
    return mz;
  }

  public void setCssj(String cssj) {
    this.cssj = cssj;
  }

  public String getCssj() {
    return cssj;
  }

  public void setCsdssxq(String csdssxq) {
    this.csdssxq = csdssxq;
  }

  public String getCsdssxq() {
    return csdssxq;
  }

  public void setMlpnbid(Long mlpnbid) {
    this.mlpnbid = mlpnbid;
  }

  public Long getMlpnbid() {
    return mlpnbid;
  }

  public void setSsxq(String ssxq) {
    this.ssxq = ssxq;
  }

  public String getSsxq() {
    return ssxq;
  }

  public void setJlx(String jlx) {
    this.jlx = jlx;
  }

  public String getJlx() {
    return jlx;
  }

  public void setMlph(String mlph) {
    this.mlph = mlph;
  }

  public String getMlph() {
    return mlph;
  }

  public void setMlxz(String mlxz) {
    this.mlxz = mlxz;
  }

  public String getMlxz() {
    return mlxz;
  }

  public void setPcs(String pcs) {
    this.pcs = pcs;
  }

  public String getPcs() {
    return pcs;
  }

  public void setZrq(String zrq) {
    this.zrq = zrq;
  }

  public String getZrq() {
    return zrq;
  }

  public void setXzjd(String xzjd) {
    this.xzjd = xzjd;
  }

  public String getXzjd() {
    return xzjd;
  }

  public void setJcwh(String jcwh) {
    this.jcwh = jcwh;
  }

  public String getJcwh() {
    return jcwh;
  }

  public void setPxh(String pxh) {
    this.pxh = pxh;
  }

  public String getPxh() {
    return pxh;
  }

  public void setYwlx(String ywlx) {
    this.ywlx = ywlx;
  }

  public String getYwlx() {
    return ywlx;
  }

  public void setCzsm(Long czsm) {
    this.czsm = czsm;
  }

  public Long getCzsm() {
    return czsm;
  }

  public void setSbsj(String sbsj) {
    this.sbsj = sbsj;
  }

  public String getSbsj() {
    return sbsj;
  }

  public void setSbryxm(String sbryxm) {
    this.sbryxm = sbryxm;
  }

  public String getSbryxm() {
    return sbryxm;
  }

  public void setSbrgmsfhm(String sbrgmsfhm) {
    this.sbrgmsfhm = sbrgmsfhm;
  }

  public String getSbrgmsfhm() {
    return sbrgmsfhm;
  }

  public void setSlsj(String slsj) {
    this.slsj = slsj;
  }

  public String getSlsj() {
    return slsj;
  }

  public void setSldw(String sldw) {
    this.sldw = sldw;
  }

  public String getSldw() {
    return sldw;
  }

  public void setSlrid(Long slrid) {
    this.slrid = slrid;
  }

  public Long getSlrid() {
    return slrid;
  }

  public void setHzxm(String hzxm) {
    this.hzxm = hzxm;
  }

  public String getHzxm() {
    return hzxm;
  }

  public void setYhzgx(String yhzgx) {
    this.yhzgx = yhzgx;
  }

  public String getYhzgx() {
    return yhzgx;
  }

  public void setHzgmsfhm(String hzgmsfhm) {
    this.hzgmsfhm = hzgmsfhm;
  }

  public void setPozjhm(String pozjhm) {
    this.pozjhm = pozjhm;
  }

  public void setPozjzl(String pozjzl) {
    this.pozjzl = pozjzl;
  }

  public void setPowwm(String powwm) {
    this.powwm = powwm;
  }

  public void setPowwx(String powwx) {
    this.powwx = powwx;
  }

  public void setMqwwm(String mqwwm) {
    this.mqwwm = mqwwm;
  }

  public void setMqwwx(String mqwwx) {
    this.mqwwx = mqwwx;
  }

  public void setFqwwm(String fqwwm) {
    this.fqwwm = fqwwm;
  }

  public void setFqwwx(String fqwwx) {
    this.fqwwx = fqwwx;
  }

  public void setFqzjhm(String fqzjhm) {
    this.fqzjhm = fqzjhm;
  }

  public void setFqzjzl(String fqzjzl) {
    this.fqzjzl = fqzjzl;
  }

  public void setMqzjhm(String mqzjhm) {
    this.mqzjhm = mqzjhm;
  }

  public void setMqzjzl(String mqzjzl) {
    this.mqzjzl = mqzjzl;
  }

  public void setJhryzjzl(String jhryzjzl) {
    this.jhryzjzl = jhryzjzl;
  }

  public void setJhryzjhm(String jhryzjhm) {
    this.jhryzjhm = jhryzjhm;
  }

  public void setJhrywwx(String jhrywwx) {
    this.jhrywwx = jhrywwx;
  }

  public void setJhrywwm(String jhrywwm) {
    this.jhrywwm = jhrywwm;
  }

  public void setJhrylxdh(String jhrylxdh) {
    this.jhrylxdh = jhrylxdh;
  }

  public void setJhrezjzl(String jhrezjzl) {
    this.jhrezjzl = jhrezjzl;
  }

  public void setJhrezjhm(String jhrezjhm) {
    this.jhrezjhm = jhrezjhm;
  }

  public void setJhrewwx(String jhrewwx) {
    this.jhrewwx = jhrewwx;
  }

  public void setJhrewwm(String jhrewwm) {
    this.jhrewwm = jhrewwm;
  }

  public void setJhrelxdh(String jhrelxdh) {
    this.jhrelxdh = jhrelxdh;
  }

  public void setSbrjtgx(String sbrjtgx) {
    this.sbrjtgx = sbrjtgx;
  }

  public void setXmm(String xmm) {
    this.xmm = xmm;
  }

  public void setXmx(String xmx) {
    this.xmx = xmx;
  }

  public void setQyldyy(String qyldyy) {
    this.qyldyy = qyldyy;
  }

  public void setJgxz(String jgxz) {
    this.jgxz = jgxz;
  }

  public String getHzgmsfhm() {
    return hzgmsfhm;
  }

  public String getPozjhm() {
    return pozjhm;
  }

  public String getPozjzl() {
    return pozjzl;
  }

  public String getPowwm() {
    return powwm;
  }

  public String getPowwx() {
    return powwx;
  }

  public String getMqwwx() {
    return mqwwx;
  }

  public String getMqwwm() {
    return mqwwm;
  }

  public String getFqwwm() {
    return fqwwm;
  }

  public String getFqwwx() {
    return fqwwx;
  }

  public String getFqzjhm() {
    return fqzjhm;
  }

  public String getFqzjzl() {
    return fqzjzl;
  }

  public String getMqzjhm() {
    return mqzjhm;
  }

  public String getMqzjzl() {
    return mqzjzl;
  }

  public String getJhrelxdh() {
    return jhrelxdh;
  }

  public String getJhrewwm() {
    return jhrewwm;
  }

  public String getJhrewwx() {
    return jhrewwx;
  }

  public String getJhrezjhm() {
    return jhrezjhm;
  }

  public String getJhrezjzl() {
    return jhrezjzl;
  }

  public String getJhrylxdh() {
    return jhrylxdh;
  }

  public String getJhrywwm() {
    return jhrywwm;
  }

  public String getJhrywwx() {
    return jhrywwx;
  }

  public String getJhryzjhm() {
    return jhryzjhm;
  }

  public String getJhryzjzl() {
    return jhryzjzl;
  }

  public String getSbrjtgx() {
    return sbrjtgx;
  }

  public String getXmm() {
    return xmm;
  }

  public String getXmx() {
    return xmx;
  }

  public String getQyldyy() {
    return qyldyy;
  }

  public String getJgxz() {
    return jgxz;
  }

}
