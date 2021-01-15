/* Generated by Together */

package com.hzjc.hz2004.service;
import com.hzjc.wsstruts.exception.*;
import com.hzjc.hz2004.base.login.AuthToken;
import com.hzjc.hz2004.service.Hz2004Service;

/**
 *
 * <p>Title: Hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: HZJC</p>
 * @author Kansan Ku(kgb_hz@126.com)
 * @version 1.0
 */
public interface AuthTokenService
    extends Hz2004Service {
  AuthToken getKDSAuthToken(String strUserID) throws
      UnAuthException;
  /**
   *
   * @param strUserID
   * @param strPassword
   * @return
   */
  AuthToken getSOAPAuthToken(String strUserID, String strPassword) throws
      UnAuthException;

  /**
   *
   * @param strUserID
   * @param strPassword
   * @param strIP
   * @return
   * @throws UnAuthException
   */
  AuthToken getWebAuthToken(String strUserID, String strPassword,
                            String strIP) throws
      UnAuthException;

}