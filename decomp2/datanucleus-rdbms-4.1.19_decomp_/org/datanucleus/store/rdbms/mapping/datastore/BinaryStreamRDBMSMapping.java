package org.datanucleus.store.rdbms.mapping.datastore;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.datanucleus.exceptions.NucleusDataStoreException;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.mapping.java.FileMapping;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.table.Column;
import org.datanucleus.util.Localiser;

public class BinaryStreamRDBMSMapping extends AbstractDatastoreMapping {
   public BinaryStreamRDBMSMapping(JavaTypeMapping mapping, RDBMSStoreManager storeMgr, Column col) {
      super(storeMgr, mapping);
      this.column = col;
      this.initialize();
   }

   private void initialize() {
      this.initTypeInfo();
   }

   public int getJDBCType() {
      return -4;
   }

   public void setObject(PreparedStatement ps, int param, Object value) {
      try {
         if (value == null) {
            ps.setNull(param, this.getJDBCType());
         } else {
            if (!(value instanceof File)) {
               throw new NucleusDataStoreException("setObject unsupported for java type " + value.getClass().getName());
            }

            File file = (File)value;
            ps.setBinaryStream(param, new FileInputStream(file), (int)file.length());
         }

      } catch (SQLException e) {
         throw new NucleusDataStoreException(Localiser.msg("055001", new Object[]{"Object", "" + value, this.column, e.getMessage()}), e);
      } catch (IOException e) {
         throw new NucleusDataStoreException(Localiser.msg("055001", new Object[]{"Object", "" + value, this.column, e.getMessage()}), e);
      }
   }

   public Object getObject(ResultSet resultSet, int param) {
      Object so = null;

      try {
         InputStream is = resultSet.getBinaryStream(param);
         if (!resultSet.wasNull()) {
            if (!(this.getJavaTypeMapping() instanceof FileMapping)) {
               throw new NucleusDataStoreException("getObject unsupported for java type mapping of type " + this.getJavaTypeMapping());
            }

            so = StreamableSpooler.instance().spoolStream(is);
         }

         return so;
      } catch (IOException e) {
         throw new NucleusDataStoreException(Localiser.msg("055002", new Object[]{"Object", "" + param, this.column, e.getMessage()}), e);
      } catch (SQLException e) {
         throw new NucleusDataStoreException(Localiser.msg("055002", new Object[]{"Object", "" + param, this.column, e.getMessage()}), e);
      }
   }
}
