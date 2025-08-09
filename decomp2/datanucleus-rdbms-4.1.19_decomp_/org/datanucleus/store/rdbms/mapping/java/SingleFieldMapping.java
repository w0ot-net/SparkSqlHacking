package org.datanucleus.store.rdbms.mapping.java;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.ExecutionContext;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.store.rdbms.mapping.MappingManager;
import org.datanucleus.store.rdbms.table.Column;
import org.datanucleus.store.rdbms.table.Table;

public abstract class SingleFieldMapping extends JavaTypeMapping {
   public void initialize(AbstractMemberMetaData fmd, Table table, ClassLoaderResolver clr) {
      super.initialize(fmd, table, clr);
      this.prepareDatastoreMapping();
   }

   protected void prepareDatastoreMapping() {
      MappingManager mmgr = this.storeMgr.getMappingManager();
      Column col = mmgr.createColumn(this, this.getJavaTypeForDatastoreMapping(0), 0);
      mmgr.createDatastoreMapping(this, this.mmd, 0, col);
   }

   public int getDefaultLength(int index) {
      return -1;
   }

   public Object[] getValidValues(int index) {
      return null;
   }

   public String getJavaTypeForDatastoreMapping(int index) {
      return this.getJavaType() == null ? null : this.getJavaType().getName();
   }

   public void setBoolean(ExecutionContext ec, PreparedStatement ps, int[] exprIndex, boolean value) {
      this.getDatastoreMapping(0).setBoolean(ps, exprIndex[0], value);
   }

   public boolean getBoolean(ExecutionContext ec, ResultSet resultSet, int[] exprIndex) {
      return this.getDatastoreMapping(0).getBoolean(resultSet, exprIndex[0]);
   }

   public void setChar(ExecutionContext ec, PreparedStatement ps, int[] exprIndex, char value) {
      this.getDatastoreMapping(0).setChar(ps, exprIndex[0], value);
   }

   public char getChar(ExecutionContext ec, ResultSet resultSet, int[] exprIndex) {
      return this.getDatastoreMapping(0).getChar(resultSet, exprIndex[0]);
   }

   public void setByte(ExecutionContext ec, PreparedStatement ps, int[] exprIndex, byte value) {
      this.getDatastoreMapping(0).setByte(ps, exprIndex[0], value);
   }

   public byte getByte(ExecutionContext ec, ResultSet resultSet, int[] exprIndex) {
      return this.getDatastoreMapping(0).getByte(resultSet, exprIndex[0]);
   }

   public void setShort(ExecutionContext ec, PreparedStatement ps, int[] exprIndex, short value) {
      this.getDatastoreMapping(0).setShort(ps, exprIndex[0], value);
   }

   public short getShort(ExecutionContext ec, ResultSet resultSet, int[] exprIndex) {
      return this.getDatastoreMapping(0).getShort(resultSet, exprIndex[0]);
   }

   public void setInt(ExecutionContext ec, PreparedStatement ps, int[] exprIndex, int value) {
      this.getDatastoreMapping(0).setInt(ps, exprIndex[0], value);
   }

   public int getInt(ExecutionContext ec, ResultSet resultSet, int[] exprIndex) {
      return this.getDatastoreMapping(0).getInt(resultSet, exprIndex[0]);
   }

   public void setLong(ExecutionContext ec, PreparedStatement ps, int[] exprIndex, long value) {
      this.getDatastoreMapping(0).setLong(ps, exprIndex[0], value);
   }

   public long getLong(ExecutionContext ec, ResultSet resultSet, int[] exprIndex) {
      return this.getDatastoreMapping(0).getLong(resultSet, exprIndex[0]);
   }

   public void setFloat(ExecutionContext ec, PreparedStatement ps, int[] exprIndex, float value) {
      this.getDatastoreMapping(0).setFloat(ps, exprIndex[0], value);
   }

   public float getFloat(ExecutionContext ec, ResultSet resultSet, int[] exprIndex) {
      return this.getDatastoreMapping(0).getFloat(resultSet, exprIndex[0]);
   }

   public void setDouble(ExecutionContext ec, PreparedStatement ps, int[] exprIndex, double value) {
      this.getDatastoreMapping(0).setDouble(ps, exprIndex[0], value);
   }

   public double getDouble(ExecutionContext ec, ResultSet resultSet, int[] exprIndex) {
      return this.getDatastoreMapping(0).getDouble(resultSet, exprIndex[0]);
   }

   public void setString(ExecutionContext ec, PreparedStatement ps, int[] exprIndex, String value) {
      this.getDatastoreMapping(0).setString(ps, exprIndex[0], value);
   }

   public String getString(ExecutionContext ec, ResultSet resultSet, int[] exprIndex) {
      return this.getDatastoreMapping(0).getString(resultSet, exprIndex[0]);
   }

   public void setObject(ExecutionContext ec, PreparedStatement ps, int[] exprIndex, Object value) {
      this.getDatastoreMapping(0).setObject(ps, exprIndex[0], value);
   }

   public Object getObject(ExecutionContext ec, ResultSet resultSet, int[] exprIndex) {
      return exprIndex == null ? null : this.getDatastoreMapping(0).getObject(resultSet, exprIndex[0]);
   }
}
