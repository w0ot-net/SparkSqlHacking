package org.datanucleus.store.rdbms.mapping.datastore;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import org.datanucleus.exceptions.NucleusDataStoreException;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.table.Column;
import org.datanucleus.util.Localiser;

public class TimeRDBMSMapping extends AbstractDatastoreMapping {
   public TimeRDBMSMapping(JavaTypeMapping mapping, RDBMSStoreManager storeMgr, Column col) {
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

   public int getJDBCType() {
      return 92;
   }

   public void setObject(PreparedStatement ps, int param, Object value) {
      try {
         if (value == null) {
            ps.setNull(param, this.getJDBCType());
         } else if (value instanceof Calendar) {
            ps.setTime(param, new Time(((Calendar)value).getTime().getTime()));
         } else if (value instanceof Time) {
            ps.setTime(param, (Time)value);
         } else if (value instanceof Date) {
            ps.setTime(param, new Time(((Date)value).getTime()));
         } else {
            ps.setTime(param, (Time)value);
         }

      } catch (SQLException e) {
         throw new NucleusDataStoreException(Localiser.msg("055001", new Object[]{"java.sql.Time", "" + value, this.column, e.getMessage()}), e);
      }
   }

   protected Time getTime(ResultSet rs, int param) {
      try {
         Time value = rs.getTime(param);
         return value;
      } catch (SQLException e) {
         throw new NucleusDataStoreException(Localiser.msg("055002", new Object[]{"java.sql.Time", "" + param, this.column, e.getMessage()}), e);
      }
   }

   public Object getObject(ResultSet rs, int param) {
      Time value = this.getTime(rs, param);
      return value == null ? null : value;
   }
}
