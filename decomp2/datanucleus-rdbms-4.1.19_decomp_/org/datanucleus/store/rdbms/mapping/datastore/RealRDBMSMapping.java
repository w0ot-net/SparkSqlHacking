package org.datanucleus.store.rdbms.mapping.datastore;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.datanucleus.exceptions.NucleusDataStoreException;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.exceptions.NullValueException;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.table.Column;
import org.datanucleus.util.Localiser;

public class RealRDBMSMapping extends AbstractDatastoreMapping {
   public RealRDBMSMapping(JavaTypeMapping mapping, RDBMSStoreManager storeMgr, Column col) {
      super(storeMgr, mapping);
      this.column = col;
      this.initialize();
   }

   private void initialize() {
      if (this.column != null) {
         this.column.checkPrimitive();
      }

      this.initTypeInfo();
   }

   public boolean isDecimalBased() {
      return true;
   }

   public int getJDBCType() {
      return 7;
   }

   public void setFloat(PreparedStatement ps, int param, float value) {
      try {
         ps.setFloat(param, value);
      } catch (SQLException e) {
         throw new NucleusDataStoreException(Localiser.msg("055001", new Object[]{"float", "" + value, this.column, e.getMessage()}), e);
      }
   }

   public float getFloat(ResultSet rs, int param) {
      float value;
      try {
         value = rs.getFloat(param);
         if ((this.column == null || this.column.getColumnMetaData() == null || !this.column.getColumnMetaData().isAllowsNull()) && rs.wasNull()) {
            throw new NullValueException(Localiser.msg("055003", new Object[]{this.column}));
         }
      } catch (SQLException var9) {
         SQLException e = var9;

         try {
            value = Float.parseFloat(rs.getString(param));
            if ((this.column == null || this.column.getColumnMetaData() == null || !this.column.getColumnMetaData().isAllowsNull()) && rs.wasNull()) {
               throw new NullValueException(Localiser.msg("055003", new Object[]{this.column}));
            }
         } catch (SQLException var8) {
            try {
               throw new NucleusDataStoreException("Can't get float result: param = " + param + " - " + rs.getString(param), e);
            } catch (SQLException var7) {
               throw new NucleusDataStoreException(Localiser.msg("055002", new Object[]{"float", "" + param, this.column, var9.getMessage()}), var9);
            }
         }
      }

      return value;
   }

   public void setObject(PreparedStatement ps, int param, Object value) {
      try {
         if (value == null) {
            ps.setNull(param, this.getJDBCType());
         } else {
            ps.setFloat(param, (Float)value);
         }

      } catch (SQLException e) {
         throw new NucleusDataStoreException(Localiser.msg("055001", new Object[]{"Object", "" + value, this.column, e.getMessage()}), e);
      }
   }

   public Object getObject(ResultSet rs, int param) {
      Object value;
      try {
         float f = rs.getFloat(param);
         value = rs.wasNull() ? null : f;
      } catch (SQLException var9) {
         SQLException e = var9;

         try {
            value = new Float(Float.parseFloat(rs.getString(param)));
            if ((this.column == null || this.column.getColumnMetaData() == null || !this.column.getColumnMetaData().isAllowsNull()) && rs.wasNull()) {
               throw new NullValueException(Localiser.msg("055003", new Object[]{this.column}));
            }
         } catch (SQLException var8) {
            try {
               throw new NucleusDataStoreException("Can't get float result: param = " + param + " - " + rs.getString(param), e);
            } catch (SQLException var7) {
               throw new NucleusDataStoreException(Localiser.msg("055002", new Object[]{"Object", "" + param, this.column, var9.getMessage()}), var9);
            }
         }
      }

      return value;
   }
}
