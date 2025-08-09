package org.datanucleus.store.rdbms.mapping.datastore;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Clob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.datanucleus.exceptions.NucleusDataStoreException;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.table.Column;
import org.datanucleus.util.Localiser;

public class ClobRDBMSMapping extends LongVarcharRDBMSMapping {
   public ClobRDBMSMapping(JavaTypeMapping mapping, RDBMSStoreManager storeMgr, Column col) {
      super(mapping, storeMgr, col);
   }

   public int getJDBCType() {
      return 2005;
   }

   public void setString(PreparedStatement ps, int param, String value) {
      if (this.getDatastoreAdapter().supportsOption("ClobSetUsingSetString")) {
         super.setString(ps, param, value);
      } else {
         this.setObject(ps, param, value);
      }

   }

   public void setObject(PreparedStatement ps, int param, Object value) {
      if (this.getDatastoreAdapter().supportsOption("ClobSetUsingSetString")) {
         super.setObject(ps, param, value);
      } else {
         try {
            if (value == null) {
               ps.setNull(param, this.getJDBCType());
            } else {
               ps.setClob(param, new ClobImpl((String)value));
            }
         } catch (SQLException e) {
            throw new NucleusDataStoreException(Localiser.msg("055001", new Object[]{"Object", "" + value, this.column, e.getMessage()}), e);
         } catch (IOException e) {
            throw new NucleusDataStoreException(Localiser.msg("055001", new Object[]{"Object", "" + value, this.column, e.getMessage()}), e);
         }
      }

   }

   public String getString(ResultSet rs, int param) {
      return this.getDatastoreAdapter().supportsOption("ClobSetUsingSetString") ? super.getString(rs, param) : (String)this.getObject(rs, param);
   }

   public Object getObject(ResultSet rs, int param) {
      if (this.getDatastoreAdapter().supportsOption("ClobSetUsingSetString")) {
         return super.getObject(rs, param);
      } else {
         try {
            Clob clob = rs.getClob(param);
            Object value;
            if (!rs.wasNull()) {
               BufferedReader br = new BufferedReader(clob.getCharacterStream());

               try {
                  StringBuilder sb = new StringBuilder();

                  int c;
                  while((c = br.read()) != -1) {
                     sb.append((char)c);
                  }

                  value = sb.toString();
               } finally {
                  br.close();
               }
            } else {
               value = null;
            }

            return value;
         } catch (SQLException e) {
            throw new NucleusDataStoreException(Localiser.msg("055002", new Object[]{"Object", "" + param, this.column, e.getMessage()}), e);
         } catch (IOException e) {
            throw new NucleusDataStoreException(Localiser.msg("055002", new Object[]{"Object", "" + param, this.column, e.getMessage()}), e);
         }
      }
   }
}
