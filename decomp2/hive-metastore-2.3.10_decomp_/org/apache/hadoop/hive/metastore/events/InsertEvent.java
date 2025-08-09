package org.apache.hadoop.hive.metastore.events;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.hadoop.hive.metastore.HiveMetaStore;
import org.apache.hadoop.hive.metastore.HiveMetaStoreClient;
import org.apache.hadoop.hive.metastore.api.FieldSchema;
import org.apache.hadoop.hive.metastore.api.GetTableRequest;
import org.apache.hadoop.hive.metastore.api.InsertEventRequestData;
import org.apache.hadoop.hive.metastore.api.MetaException;
import org.apache.hadoop.hive.metastore.api.NoSuchObjectException;
import org.apache.hadoop.hive.metastore.api.Table;

public class InsertEvent extends ListenerEvent {
   private final String db;
   private final String table;
   private final Map keyValues;
   private final List files;
   private List fileChecksums = new ArrayList();

   public InsertEvent(String db, String table, List partVals, InsertEventRequestData insertData, boolean status, HiveMetaStore.HMSHandler handler) throws MetaException, NoSuchObjectException {
      super(status, handler);
      this.db = db;
      this.table = table;
      this.files = insertData.getFilesAdded();
      GetTableRequest req = new GetTableRequest(db, table);
      req.setCapabilities(HiveMetaStoreClient.TEST_VERSION);
      Table t = handler.get_table_req(req).getTable();
      this.keyValues = new LinkedHashMap();
      if (partVals != null) {
         for(int i = 0; i < partVals.size(); ++i) {
            this.keyValues.put(((FieldSchema)t.getPartitionKeys().get(i)).getName(), partVals.get(i));
         }
      }

      if (insertData.isSetFilesAddedChecksum()) {
         this.fileChecksums = insertData.getFilesAddedChecksum();
      }

   }

   public String getDb() {
      return this.db;
   }

   public String getTable() {
      return this.table;
   }

   public Map getPartitionKeyValues() {
      return this.keyValues;
   }

   public List getFiles() {
      return this.files;
   }

   public List getFileChecksums() {
      return this.fileChecksums;
   }
}
