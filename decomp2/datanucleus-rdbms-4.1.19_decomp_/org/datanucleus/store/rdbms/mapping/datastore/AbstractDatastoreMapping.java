package org.datanucleus.store.rdbms.mapping.datastore;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.adapter.DatastoreAdapter;
import org.datanucleus.store.rdbms.exceptions.UnsupportedDataTypeException;
import org.datanucleus.store.rdbms.mapping.RDBMSMappingManager;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.schema.SQLTypeInfo;
import org.datanucleus.store.rdbms.table.Column;
import org.datanucleus.util.Localiser;

public abstract class AbstractDatastoreMapping implements DatastoreMapping {
   protected final JavaTypeMapping mapping;
   protected final RDBMSStoreManager storeMgr;
   protected Column column;

   protected AbstractDatastoreMapping(RDBMSStoreManager storeMgr, JavaTypeMapping mapping) {
      this.mapping = mapping;
      if (mapping != null) {
         mapping.addDatastoreMapping(this);
      }

      this.storeMgr = storeMgr;
   }

   public JavaTypeMapping getJavaTypeMapping() {
      return this.mapping;
   }

   protected DatastoreAdapter getDatastoreAdapter() {
      return this.storeMgr.getDatastoreAdapter();
   }

   protected void initTypeInfo() {
      SQLTypeInfo typeInfo = this.getTypeInfo();
      if (typeInfo == null) {
         throw new UnsupportedDataTypeException(Localiser.msg("055000", new Object[]{this.column}));
      } else {
         if (this.column != null) {
            this.column.setTypeInfo(typeInfo);
         }

      }
   }

   public abstract int getJDBCType();

   public SQLTypeInfo getTypeInfo() {
      if (this.column != null) {
         if (this.column.getColumnMetaData().getSqlType() != null) {
            return this.storeMgr.getSQLTypeInfoForJDBCType(this.getJDBCType(), this.column.getColumnMetaData().getSqlType());
         }

         if (this.storeMgr.getBooleanProperty("datanucleus.rdbms.useDefaultSqlType")) {
            String sqlTypeDflt = ((RDBMSMappingManager)this.storeMgr.getMappingManager()).getDefaultSqlTypeForJavaType(this.getJavaTypeMapping().getJavaType().getName(), this.storeMgr.getDatastoreAdapter().getNameForJDBCType(this.getJDBCType()));
            if (sqlTypeDflt != null) {
               return this.storeMgr.getSQLTypeInfoForJDBCType(this.getJDBCType(), sqlTypeDflt);
            }
         }
      }

      return this.storeMgr.getSQLTypeInfoForJDBCType(this.getJDBCType());
   }

   public boolean isNullable() {
      return this.column != null ? this.column.isNullable() : true;
   }

   public boolean includeInFetchStatement() {
      return true;
   }

   public boolean insertValuesOnInsert() {
      return this.getInsertionInputParameter().indexOf(63) > -1;
   }

   public String getInsertionInputParameter() {
      return this.column.getWrapperFunction(1);
   }

   public String getUpdateInputParameter() {
      return this.column.getWrapperFunction(2);
   }

   public Column getColumn() {
      return this.column;
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (!(obj instanceof AbstractDatastoreMapping)) {
         return false;
      } else {
         boolean var10000;
         label38: {
            AbstractDatastoreMapping cm = (AbstractDatastoreMapping)obj;
            if (this.getClass().equals(cm.getClass()) && this.storeMgr.equals(cm.storeMgr)) {
               if (this.column == null) {
                  if (cm.column == null) {
                     break label38;
                  }
               } else if (this.column.equals(cm.column)) {
                  break label38;
               }
            }

            var10000 = false;
            return var10000;
         }

         var10000 = true;
         return var10000;
      }
   }

   public int hashCode() {
      return this.storeMgr.hashCode() ^ (this.column == null ? 0 : this.column.hashCode());
   }

   protected String failureMessage(String method, int position, Exception e) {
      return Localiser.msg("041050", new Object[]{this.getClass().getName() + "." + method, position, this.column, e.getMessage()});
   }

   protected String failureMessage(String method, Object value, Exception e) {
      return Localiser.msg("041050", new Object[]{this.getClass().getName() + "." + method, value, this.column, e.getMessage()});
   }

   public void setBoolean(PreparedStatement ps, int exprIndex, boolean value) {
      throw (new NucleusException(this.failureMessage("setBoolean"))).setFatal();
   }

   public boolean getBoolean(ResultSet resultSet, int exprIndex) {
      throw (new NucleusException(this.failureMessage("getBoolean"))).setFatal();
   }

   public void setChar(PreparedStatement ps, int exprIndex, char value) {
      throw (new NucleusException(this.failureMessage("setChar"))).setFatal();
   }

   public char getChar(ResultSet resultSet, int exprIndex) {
      throw (new NucleusException(this.failureMessage("getChar"))).setFatal();
   }

   public void setByte(PreparedStatement ps, int exprIndex, byte value) {
      throw (new NucleusException(this.failureMessage("setByte"))).setFatal();
   }

   public byte getByte(ResultSet resultSet, int exprIndex) {
      throw (new NucleusException(this.failureMessage("getByte"))).setFatal();
   }

   public void setShort(PreparedStatement ps, int exprIndex, short value) {
      throw (new NucleusException(this.failureMessage("setShort"))).setFatal();
   }

   public short getShort(ResultSet resultSet, int exprIndex) {
      throw (new NucleusException(this.failureMessage("getShort"))).setFatal();
   }

   public void setInt(PreparedStatement ps, int exprIndex, int value) {
      throw (new NucleusException(this.failureMessage("setInt"))).setFatal();
   }

   public int getInt(ResultSet resultSet, int exprIndex) {
      throw (new NucleusException(this.failureMessage("getInt"))).setFatal();
   }

   public void setLong(PreparedStatement ps, int exprIndex, long value) {
      throw (new NucleusException(this.failureMessage("setLong"))).setFatal();
   }

   public long getLong(ResultSet resultSet, int exprIndex) {
      throw (new NucleusException(this.failureMessage("getLong"))).setFatal();
   }

   public void setFloat(PreparedStatement ps, int exprIndex, float value) {
      throw (new NucleusException(this.failureMessage("setFloat"))).setFatal();
   }

   public float getFloat(ResultSet resultSet, int exprIndex) {
      throw (new NucleusException(this.failureMessage("getFloat"))).setFatal();
   }

   public void setDouble(PreparedStatement ps, int exprIndex, double value) {
      throw (new NucleusException(this.failureMessage("setDouble"))).setFatal();
   }

   public double getDouble(ResultSet resultSet, int exprIndex) {
      throw (new NucleusException(this.failureMessage("getDouble"))).setFatal();
   }

   public void setString(PreparedStatement ps, int exprIndex, String value) {
      throw (new NucleusException(this.failureMessage("setString"))).setFatal();
   }

   public String getString(ResultSet resultSet, int exprIndex) {
      throw (new NucleusException(this.failureMessage("getString"))).setFatal();
   }

   public void setObject(PreparedStatement ps, int exprIndex, Object value) {
      throw (new NucleusException(this.failureMessage("setObject"))).setFatal();
   }

   public Object getObject(ResultSet resultSet, int exprIndex) {
      throw (new NucleusException(this.failureMessage("getObject"))).setFatal();
   }

   public boolean isDecimalBased() {
      return false;
   }

   public boolean isIntegerBased() {
      return false;
   }

   public boolean isStringBased() {
      return false;
   }

   public boolean isBitBased() {
      return false;
   }

   public boolean isBooleanBased() {
      return false;
   }

   protected String failureMessage(String method) {
      return Localiser.msg("041005", new Object[]{this.getClass().getName(), method, this.mapping.getMemberMetaData().getFullFieldName()});
   }
}
