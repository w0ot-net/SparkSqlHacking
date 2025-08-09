package org.apache.hadoop.hive.metastore.partition.spec;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.hadoop.hive.metastore.api.MetaException;
import org.apache.hadoop.hive.metastore.api.Partition;
import org.apache.hadoop.hive.metastore.api.PartitionSpec;

public class CompositePartitionSpecProxy extends PartitionSpecProxy {
   private String dbName;
   private String tableName;
   private List partitionSpecs;
   private List partitionSpecProxies;
   private int size = 0;

   protected CompositePartitionSpecProxy(List partitionSpecs) {
      this.partitionSpecs = partitionSpecs;
      if (partitionSpecs.isEmpty()) {
         this.dbName = null;
         this.tableName = null;
      } else {
         this.dbName = ((PartitionSpec)partitionSpecs.get(0)).getDbName();
         this.tableName = ((PartitionSpec)partitionSpecs.get(0)).getTableName();
         this.partitionSpecProxies = new ArrayList(partitionSpecs.size());

         for(PartitionSpec partitionSpec : partitionSpecs) {
            PartitionSpecProxy partitionSpecProxy = PartitionSpecProxy.Factory.get(partitionSpec);
            this.partitionSpecProxies.add(partitionSpecProxy);
            this.size += partitionSpecProxy.size();
         }
      }

      assert this.isValid() : "Invalid CompositePartitionSpecProxy!";

   }

   protected CompositePartitionSpecProxy(String dbName, String tableName, List partitionSpecs) {
      this.dbName = dbName;
      this.tableName = tableName;
      this.partitionSpecs = partitionSpecs;
      this.partitionSpecProxies = new ArrayList(partitionSpecs.size());

      for(PartitionSpec partitionSpec : partitionSpecs) {
         this.partitionSpecProxies.add(PartitionSpecProxy.Factory.get(partitionSpec));
      }

      assert this.isValid() : "Invalid CompositePartitionSpecProxy!";

   }

   private boolean isValid() {
      for(PartitionSpecProxy partitionSpecProxy : this.partitionSpecProxies) {
         if (partitionSpecProxy instanceof CompositePartitionSpecProxy) {
            return false;
         }
      }

      return true;
   }

   public int size() {
      return this.size;
   }

   public void setDbName(String dbName) {
      this.dbName = dbName;

      for(PartitionSpecProxy partSpecProxy : this.partitionSpecProxies) {
         partSpecProxy.setDbName(dbName);
      }

   }

   public void setTableName(String tableName) {
      this.tableName = tableName;

      for(PartitionSpecProxy partSpecProxy : this.partitionSpecProxies) {
         partSpecProxy.setTableName(tableName);
      }

   }

   public String getDbName() {
      return this.dbName;
   }

   public String getTableName() {
      return this.tableName;
   }

   public PartitionSpecProxy.PartitionIterator getPartitionIterator() {
      return new Iterator(this);
   }

   public List toPartitionSpec() {
      return this.partitionSpecs;
   }

   public void setRootLocation(String rootLocation) throws MetaException {
      for(PartitionSpecProxy partSpecProxy : this.partitionSpecProxies) {
         partSpecProxy.setRootLocation(rootLocation);
      }

   }

   public static class Iterator implements PartitionSpecProxy.PartitionIterator {
      private CompositePartitionSpecProxy composite;
      private List partitionSpecProxies;
      private int index = -1;
      private PartitionSpecProxy.PartitionIterator iterator = null;

      public Iterator(CompositePartitionSpecProxy composite) {
         this.composite = composite;
         this.partitionSpecProxies = composite.partitionSpecProxies;
         if (this.partitionSpecProxies != null && !this.partitionSpecProxies.isEmpty()) {
            this.index = 0;
            this.iterator = ((PartitionSpecProxy)this.partitionSpecProxies.get(this.index)).getPartitionIterator();
         }

      }

      public boolean hasNext() {
         if (this.iterator == null) {
            return false;
         } else if (this.iterator.hasNext()) {
            return true;
         } else {
            while(++this.index < this.partitionSpecProxies.size() && !(this.iterator = ((PartitionSpecProxy)this.partitionSpecProxies.get(this.index)).getPartitionIterator()).hasNext()) {
            }

            return this.index < this.partitionSpecProxies.size() && this.iterator.hasNext();
         }
      }

      public Partition next() {
         if (this.iterator.hasNext()) {
            return (Partition)this.iterator.next();
         } else {
            while(++this.index < this.partitionSpecProxies.size() && !(this.iterator = ((PartitionSpecProxy)this.partitionSpecProxies.get(this.index)).getPartitionIterator()).hasNext()) {
            }

            return this.index == this.partitionSpecProxies.size() ? null : (Partition)this.iterator.next();
         }
      }

      public void remove() {
         this.iterator.remove();
      }

      public Partition getCurrent() {
         return this.iterator.getCurrent();
      }

      public String getDbName() {
         return this.composite.dbName;
      }

      public String getTableName() {
         return this.composite.tableName;
      }

      public Map getParameters() {
         return this.iterator.getParameters();
      }

      public void setParameters(Map parameters) {
         this.iterator.setParameters(parameters);
      }

      public String getLocation() {
         return this.iterator.getLocation();
      }

      public void putToParameters(String key, String value) {
         this.iterator.putToParameters(key, value);
      }

      public void setCreateTime(long time) {
         this.iterator.setCreateTime(time);
      }
   }
}
