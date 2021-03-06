package com.hzjc.hz2004.po.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="zzj_Vrkjbxxall" )
public class Vrkjbxxall implements com.hzjc.wsstruts.po.PO{
private static final long serialVersionUID = 1L;
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	

	public String getRynbid() {
		return rynbid;
	}
	public void setRynbid(String rynbid) {
		this.rynbid = rynbid;
	}
	public String getHzxm() {
		return hzxm;
	}
	public void setHzxm(String hzxm) {
		this.hzxm = hzxm;
	}
	public String getYhzgx() {
		return yhzgx;
	}
	public void setYhzgx(String yhzgx) {
		this.yhzgx = yhzgx;
	}
	public String getHh() {
		return hh;
	}
	public void setHh(String hh) {
		this.hh = hh;
	}
	public String getGmsfzh() {
		return gmsfzh;
	}
	public void setGmsfzh(String gmsfzh) {
		this.gmsfzh = gmsfzh;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getXb() {
		return xb;
	}
	public void setXb(String xb) {
		this.xb = xb;
	}
	public String getCym() {
		return cym;
	}
	public void setCym(String cym) {
		this.cym = cym;
	}
	public String getMz() {
		return mz;
	}
	public void setMz(String mz) {
		this.mz = mz;
	}
	public String getCsrq() {
		return csrq;
	}
	public void setCsrq(String csrq) {
		this.csrq = csrq;
	}
	public String getWhcd() {
		return whcd;
	}
	public void setWhcd(String whcd) {
		this.whcd = whcd;
	}
	public String getZy() {
		return zy;
	}
	public void setZy(String zy) {
		this.zy = zy;
	}
	public String getDzxx() {
		return dzxx;
	}
	public void setDzxx(String dzxx) {
		this.dzxx = dzxx;
	}
	public String getFwcs() {
		return fwcs;
	}
	public void setFwcs(String fwcs) {
		this.fwcs = fwcs;
	}
	public String getJgssxq() {
		return jgssxq;
	}
	public void setJgssxq(String jgssxq) {
		this.jgssxq = jgssxq;
	}
	public String getSjgjdw() {
		return sjgjdw;
	}
	public void setSjgjdw(String sjgjdw) {
		this.sjgjdw = sjgjdw;
	}

	public byte[] getZp() {
		return zp;
	}

	public void setZp(byte[] zp) {
		this.zp = zp;
	}

	private String    rynbid;	//--??????ID, ??????????????????
	private String	    hzxm;	//--????????????
	private String	    yhzgx;	//--???????????????
	private String	    hh;	//--??????
	private String	    gmsfzh;	//--??????????????????
		private String	xm; 	//--?????????
		public String getRyzt() {
			return ryzt;
		}

		public void setRyzt(String ryzt) {
			this.ryzt = ryzt;
		}

		private String	xb;	//--??????
	private String	    cym;	//--?????????
		private String	mz;	//--??????
		private String	csrq; //--????????????????????????yyyyMMdd
	private String	    whcd;	//--????????????
	private String	    zy;//	--??????
	private String	    dzxx;	//--??????
	private String	    fwcs;	//--????????????
		private String	jgssxq;//	--???????????????/???
	private String	   sjgjdw;	//--??????????????????
	private String	   ryzt;	//--????????????
	private byte[]	   zp;	//--??????base64
	private String swzxlb;
	private String swzxrq;

	public String getSwzxlb() {
		return swzxlb;
	}

	public void setSwzxlb(String swzxlb) {
		this.swzxlb = swzxlb;
	}

	public String getSwzxrq() {
		return swzxrq;
	}

	public void setSwzxrq(String swzxrq) {
		this.swzxrq = swzxrq;
	}
}
