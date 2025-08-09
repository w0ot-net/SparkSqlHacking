package org.datanucleus.store.rdbms.mapping.java;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.datanucleus.ExecutionContext;
import org.datanucleus.metadata.ColumnMetaData;
import org.datanucleus.metadata.VersionMetaData;
import org.datanucleus.store.rdbms.identifier.DatastoreIdentifier;
import org.datanucleus.store.rdbms.identifier.IdentifierFactory;
import org.datanucleus.store.rdbms.mapping.datastore.DatastoreMapping;
import org.datanucleus.store.rdbms.table.Column;
import org.datanucleus.store.rdbms.table.Table;

public class VersionMapping extends SingleFieldMapping {
   private final JavaTypeMapping delegate;

   public VersionMapping(Table table, JavaTypeMapping delegate) {
      this.initialize(table.getStoreManager(), delegate.getType());
      this.delegate = delegate;
      this.table = table;
      VersionMetaData vermd = table.getVersionMetaData();
      ColumnMetaData versionColumnMetaData = vermd.getColumnMetaData();
      IdentifierFactory idFactory = table.getStoreManager().getIdentifierFactory();
      DatastoreIdentifier id = null;
      ColumnMetaData colmd;
      if (versionColumnMetaData == null) {
         id = idFactory.newVersionFieldIdentifier();
         colmd = new ColumnMetaData();
         colmd.setName(id.getName());
         table.getVersionMetaData().setColumnMetaData(colmd);
      } else {
         colmd = versionColumnMetaData;
         if (versionColumnMetaData.getName() == null) {
            id = idFactory.newVersionFieldIdentifier();
            versionColumnMetaData.setName(id.getName());
         } else {
            id = idFactory.newColumnIdentifier(versionColumnMetaData.getName());
         }
      }

      Column column = table.addColumn(this.getType(), id, this, colmd);
      table.getStoreManager().getMappingManager().createDatastoreMapping(delegate, column, this.getType());
   }

   public boolean includeInFetchStatement() {
      return false;
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

   public Class getJavaType() {
      return VersionMapping.class;
   }

   public void setObject(ExecutionContext ec, PreparedStatement ps, int[] exprIndex, Object value) {
      this.delegate.setObject(ec, ps, exprIndex, value);
   }

   public Object getObject(ExecutionContext ec, ResultSet resultSet, int[] exprIndex) {
      return this.delegate.getObject(ec, resultSet, exprIndex);
   }
}
