package org.datanucleus.store.rdbms.mapping.java;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.datanucleus.ClassNameConstants;
import org.datanucleus.ExecutionContext;
import org.datanucleus.metadata.ColumnMetaData;
import org.datanucleus.metadata.DiscriminatorMetaData;
import org.datanucleus.metadata.DiscriminatorStrategy;
import org.datanucleus.metadata.MetaDataUtils;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.identifier.DatastoreIdentifier;
import org.datanucleus.store.rdbms.identifier.IdentifierFactory;
import org.datanucleus.store.rdbms.mapping.MappingManager;
import org.datanucleus.store.rdbms.mapping.datastore.DatastoreMapping;
import org.datanucleus.store.rdbms.table.Column;
import org.datanucleus.store.rdbms.table.Table;

public class DiscriminatorMapping extends SingleFieldMapping {
   private final JavaTypeMapping delegate;

   public DiscriminatorMapping(Table table, JavaTypeMapping delegate, DiscriminatorMetaData dismd) {
      this.initialize(table.getStoreManager(), delegate.getType());
      this.table = table;
      this.delegate = delegate;
      IdentifierFactory idFactory = table.getStoreManager().getIdentifierFactory();
      DatastoreIdentifier id = null;
      if (dismd.getColumnMetaData() == null) {
         id = idFactory.newDiscriminatorFieldIdentifier();
         ColumnMetaData colmd = new ColumnMetaData();
         colmd.setName(id.getName());
         dismd.setColumnMetaData(colmd);
      } else {
         ColumnMetaData colmd = dismd.getColumnMetaData();
         if (colmd.getName() == null) {
            id = idFactory.newDiscriminatorFieldIdentifier();
            colmd.setName(id.getName());
         } else {
            id = idFactory.newColumnIdentifier(colmd.getName());
         }
      }

      Column column = table.addColumn(this.getType(), id, this, dismd.getColumnMetaData());
      table.getStoreManager().getMappingManager().createDatastoreMapping(delegate, column, this.getType());
   }

   public Class getJavaType() {
      return DiscriminatorMapping.class;
   }

   public void setObject(ExecutionContext ec, PreparedStatement ps, int[] exprIndex, Object value) {
      Object valueObj = value;
      if (value instanceof String && (this.getType().equals(ClassNameConstants.LONG) || this.getType().equals(ClassNameConstants.JAVA_LANG_LONG))) {
         valueObj = Long.valueOf((String)value);
      }

      this.delegate.setObject(ec, ps, exprIndex, valueObj);
   }

   public Object getObject(ExecutionContext ec, ResultSet resultSet, int[] exprIndex) {
      Object value = this.delegate.getObject(ec, resultSet, exprIndex);
      Object valueObj = value;
      if (value instanceof String && (this.getType().equals(ClassNameConstants.LONG) || this.getType().equals(ClassNameConstants.JAVA_LANG_LONG))) {
         valueObj = Long.valueOf((String)value);
      }

      return valueObj;
   }

   public int getNumberOfDatastoreMappings() {
      return this.delegate.getNumberOfDatastoreMappings();
   }

   public DatastoreMapping getDatastoreMapping(int index) {
      return this.delegate.getDatastoreMapping(index);
   }

   public DatastoreMapping[] getDatastoreMappings() {
      return this.delegate.getDatastoreMappings();
   }

   public void addDatastoreMapping(DatastoreMapping datastoreMapping) {
      this.delegate.addDatastoreMapping(datastoreMapping);
   }

   public static DiscriminatorMapping createDiscriminatorMapping(Table table, DiscriminatorMetaData dismd) {
      RDBMSStoreManager storeMgr = table.getStoreManager();
      MappingManager mapMgr = storeMgr.getMappingManager();
      if (dismd.getStrategy() == DiscriminatorStrategy.CLASS_NAME) {
         return new DiscriminatorStringMapping(table, mapMgr.getMapping(String.class), dismd);
      } else if (dismd.getStrategy() == DiscriminatorStrategy.VALUE_MAP) {
         ColumnMetaData disColmd = dismd.getColumnMetaData();
         if (disColmd != null && disColmd.getJdbcType() != null) {
            return (DiscriminatorMapping)(MetaDataUtils.isJdbcTypeNumeric(disColmd.getJdbcType()) ? new DiscriminatorLongMapping(table, mapMgr.getMapping(Long.class), dismd) : new DiscriminatorStringMapping(table, mapMgr.getMapping(String.class), dismd));
         } else {
            return new DiscriminatorStringMapping(table, mapMgr.getMapping(String.class), dismd);
         }
      } else {
         return null;
      }
   }
}
