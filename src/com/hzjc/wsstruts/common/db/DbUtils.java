/* Generated by Together */

package com.hzjc.wsstruts.common.db;

import java.sql.*;
import java.io.*;
import com.hzjc.util.StringUtils;

/**
 *
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @Kansanku(kgb_hz@126.com)
 * @version 1.0
 */
public class DbUtils {

  private DbUtils() {
  }

  /**
   *
   * @param m_ResultSet
   * @param i
   * @return
   * @throws SQLException
   * @throws IOException
   */
  public static byte[] getImageToBytes(ResultSet m_ResultSet, int i) throws
      SQLException, IOException {
    //return m_ResultSet.getBytes(i);
    byte abyte0[] = null;
    try {
      InputStream inputstream = m_ResultSet.getBinaryStream(i);
      if (null != inputstream) {
        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream(
            1000);
        int j;
        while ( (j = inputstream.read()) != -1) {
          bytearrayoutputstream.write(j);
        }
        inputstream.close();
        abyte0 = bytearrayoutputstream.toByteArray();
        bytearrayoutputstream.close();
      }
      else {
        abyte0 = new byte[0];
      }
    }
    catch (IOException ex) {
      throw new IOException(ex.getMessage());
    }
    catch (SQLException ex) {
      throw new SQLException(ex.getMessage());
    }
    return abyte0;
  }

  /**
   * Blob字段转换为字节流
   * @param blob
   * @return
   */

  public static byte[] parseBlobToBytes(Blob blob) {
    byte abyte0[] = null;
    try {
      if (blob == null) {
        return abyte0;
      }
      InputStream inputstream = blob.getBinaryStream();
      if (null != inputstream) {
        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream(
            512);
        int j;
        while ( (j = inputstream.read()) != -1) {
          bytearrayoutputstream.write(j);
        }
        inputstream.close();
        abyte0 = bytearrayoutputstream.toByteArray();
        bytearrayoutputstream.close();
      }
      else {
        abyte0 = new byte[0];
      }
    }
    catch (Exception ex) {
    }
    return abyte0;
  }

  /**
   * after get Records Close Connection for clear memory Resource
   * @param Connection
   */
  public static void close(Connection conn) {
    if (conn != null) {
      try {
        conn.close();
      }
      catch (SQLException ex) {
        ex.printStackTrace();
      }
      conn = null;
    }
  }

  /**
   * after ExcuteQuery free the PreparedStatement  memory Resource
   * @param PreparedStatement
   */
  public static void close(Statement ps) {
    if (ps != null) {
      try {
        ps.close();
      }
      catch (SQLException ex) {
        ex.printStackTrace();
      }
      ps = null;
    }
  }

  /**
   * after ExcuteQuery free ResultSet  memory resource
   * @param ResultSet
   */
  public static void close(ResultSet rs) {
    if (rs != null) {
      try {
        rs.close();
      }
      catch (SQLException ex) {
        ex.printStackTrace();
      }
      rs = null;
    }
  }

  /**
   * if the Databse Excute throw exception rollback all Operations
   * @param Connection
   */
  public static void rollback(Connection conn) {
    if (conn != null) {
      try {
        conn.rollback();
      }
      catch (SQLException e) {
        e.printStackTrace();
      }
      conn = null;
    }
  }

  /**
   * in the Transation commit by programmer
   * @param conn
   */
  public static void commit(Connection conn) {
    if (conn != null) {
      try {
        conn.commit();
      }
      catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * if Excute a few Operations: setAutoCommit(false) in the Transation and Commit by programer
   * else if setAutoCommit(True) then commit by Jdbc itself
   * @param conn
   * @param isAutoCommit
   */

  public static void setAutoCommit(Connection conn, boolean isAutoCommit) {
    if (conn != null) {
      try {
        conn.setAutoCommit(isAutoCommit);
      }
      catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

}