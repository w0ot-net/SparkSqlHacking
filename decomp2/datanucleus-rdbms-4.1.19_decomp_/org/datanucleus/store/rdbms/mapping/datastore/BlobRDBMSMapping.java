package org.datanucleus.store.rdbms.mapping.datastore;

import java.io.IOException;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.datanucleus.exceptions.NucleusDataStoreException;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.table.Column;
import org.datanucleus.util.Localiser;

public class BlobRDBMSMapping extends AbstractLargeBinaryRDBMSMapping {
   public BlobRDBMSMapping(JavaTypeMapping mapping, RDBMSStoreManager storeMgr, Column col) {
      super(mapping, storeMgr, col);
   }

   public int getJDBCType() {
      return 2004;
   }

   public Object getObject(ResultSet rs, int param) {
      byte[] bytes = null;

      try {
         bytes = rs.getBytes(param);
         if (bytes == null) {
            return null;
         }
      } catch (SQLException var7) {
         try {
            Blob blob = rs.getBlob(param);
            if (blob == null) {
               return null;
            }

            bytes = blob.getBytes(1L, (int)blob.length());
            if (bytes == null) {
               return null;
            }
         } catch (SQLException sqle2) {
            throw new NucleusDataStoreException(Localiser.msg("055002", new Object[]{"Object", "" + param, this.column, sqle2.getMessage()}), sqle2);
         }
      }

      return this.getObjectForBytes(bytes, param);
   }

   public void setString(PreparedStatement ps, int param, String value) {
      try {
         if (this.getDatastoreAdapter().supportsOption("BlobSetUsingSetString")) {
            if (value == null) {
               if (this.column.isDefaultable() && this.column.getDefaultValue() != null) {
                  ps.setString(param, this.column.getDefaultValue().toString().trim());
               } else {
                  ps.setNull(param, this.getJDBCType());
               }
            } else {
               ps.setString(param, value);
            }
         } else if (value == null) {
            if (this.column != null && this.column.isDefaultable() && this.column.getDefaultValue() != null) {
               ps.setBlob(param, new BlobImpl(this.column.getDefaultValue().toString().trim()));
            } else {
               ps.setNull(param, this.getJDBCType());
            }
         } else {
            ps.setBlob(param, new BlobImpl(value));
         }

      } catch (SQLException e) {
         throw new NucleusDataStoreException(Localiser.msg("055001", new Object[]{"String", "" + value, this.column, e.getMessage()}), e);
      } catch (IOException e) {
         throw new NucleusDataStoreException(Localiser.msg("055001", new Object[]{"String", "" + value, this.column, e.getMessage()}), e);
      }
   }

   public String getString(ResultSet rs, int param) {
      try {
         String value;
         if (this.getDatastoreAdapter().supportsOption("BlobSetUsingSetString")) {
            value = rs.getString(param);
         } else {
            byte[] bytes = rs.getBytes(param);
            if (bytes == null) {
               value = null;
            } else {
               BlobImpl blob = new BlobImpl(bytes);
               value = (String)blob.getObject();
            }
         }

         return value;
      } catch (SQLException e) {
         throw new NucleusDataStoreException(Localiser.msg("055002", new Object[]{"String", "" + param, this.column, e.getMessage()}), e);
      }
   }
}
