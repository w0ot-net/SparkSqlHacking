package org.datanucleus.store.rdbms.mapping.java;

import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.metadata.JdbcType;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.store.rdbms.mapping.MappingCallbacks;
import org.datanucleus.store.rdbms.mapping.datastore.OracleBlobRDBMSMapping;
import org.datanucleus.store.rdbms.mapping.datastore.OracleClobRDBMSMapping;

public class OracleStringLobMapping extends StringMapping implements MappingCallbacks {
   public void insertPostProcessing(ObjectProvider op) {
      String value = (String)op.provideField(this.mmd.getAbsoluteFieldNumber());
      op.isLoaded(this.mmd.getAbsoluteFieldNumber());
      if (value == null) {
         value = "";
      } else if (value.length() == 0) {
         if (this.storeMgr.getBooleanProperty("datanucleus.rdbms.persistEmptyStringAsNull")) {
            value = "";
         } else {
            value = this.storeMgr.getDatastoreAdapter().getSurrogateForEmptyStrings();
         }
      }

      if (this.mmd.getColumnMetaData()[0].getJdbcType() == JdbcType.BLOB) {
         OracleBlobRDBMSMapping.updateBlobColumn(op, this.getTable(), this.getDatastoreMapping(0), value.getBytes());
      } else {
         if (this.mmd.getColumnMetaData()[0].getJdbcType() != JdbcType.CLOB) {
            throw new NucleusException("AssertionError: Only JDBC types BLOB and CLOB are allowed!");
         }

         OracleClobRDBMSMapping.updateClobColumn(op, this.getTable(), this.getDatastoreMapping(0), value);
      }

   }

   public void postInsert(ObjectProvider op) {
   }

   public void postFetch(ObjectProvider op) {
   }

   public void postUpdate(ObjectProvider op) {
      this.insertPostProcessing(op);
   }

   public void preDelete(ObjectProvider op) {
   }
}
