package org.apache.hadoop.hive.metastore.partition.spec;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.apache.hadoop.hive.metastore.api.MetaException;
import org.apache.hadoop.hive.metastore.api.Partition;
import org.apache.hadoop.hive.metastore.api.PartitionSpec;

public class PartitionListComposingSpecProxy extends PartitionSpecProxy {
   private PartitionSpec partitionSpec;

   protected PartitionListComposingSpecProxy(PartitionSpec partitionSpec) {
      assert partitionSpec.isSetPartitionList() : "Partition-list should have been set.";

      this.partitionSpec = partitionSpec;
   }

   public String getDbName() {
      return this.partitionSpec.getDbName();
   }

   public String getTableName() {
      return this.partitionSpec.getTableName();
   }

   public PartitionSpecProxy.PartitionIterator getPartitionIterator() {
      return new Iterator(this);
   }

   public List toPartitionSpec() {
      return Arrays.asList(this.partitionSpec);
   }

   public int size() {
      return this.partitionSpec.getPartitionList().getPartitionsSize();
   }

   public void setDbName(String dbName) {
      this.partitionSpec.setDbName(dbName);

      for(Partition partition : this.partitionSpec.getPartitionList().getPartitions()) {
         partition.setDbName(dbName);
      }

   }

   public void setTableName(String tableName) {
      this.partitionSpec.setTableName(tableName);

      for(Partition partition : this.partitionSpec.getPartitionList().getPartitions()) {
         partition.setTableName(tableName);
      }

   }

   public void setRootLocation(String newRootPath) throws MetaException {
      String oldRootPath = this.partitionSpec.getRootPath();
      if (oldRootPath == null) {
         throw new MetaException("No common root-path. Can't replace root-path!");
      } else {
         for(Partition partition : this.partitionSpec.getPartitionList().getPartitions()) {
            String location = partition.getSd().getLocation();
            if (!location.startsWith(oldRootPath)) {
               throw new MetaException("Common root-path not found. Can't replace root-path!");
            }

            partition.getSd().setLocation(location.replace(oldRootPath, newRootPath));
         }

      }
   }

   public static class Iterator implements PartitionSpecProxy.PartitionIterator {
      PartitionListComposingSpecProxy partitionSpecProxy;
      List partitionList;
      int index;

      public Iterator(PartitionListComposingSpecProxy partitionSpecProxy) {
         this.partitionSpecProxy = partitionSpecProxy;
         this.partitionList = partitionSpecProxy.partitionSpec.getPartitionList().getPartitions();
         this.index = 0;
      }

      public Partition getCurrent() {
         return (Partition)this.partitionList.get(this.index);
      }

      public String getDbName() {
         return this.partitionSpecProxy.getDbName();
      }

      public String getTableName() {
         return this.partitionSpecProxy.getTableName();
      }

      public Map getParameters() {
         return ((Partition)this.partitionList.get(this.index)).getParameters();
      }

      public void setParameters(Map parameters) {
         ((Partition)this.partitionList.get(this.index)).setParameters(parameters);
      }

      public String getLocation() {
         return ((Partition)this.partitionList.get(this.index)).getSd().getLocation();
      }

      public void putToParameters(String key, String value) {
         ((Partition)this.partitionList.get(this.index)).putToParameters(key, value);
      }

      public void setCreateTime(long time) {
         ((Partition)this.partitionList.get(this.index)).setCreateTime((int)time);
      }

      public boolean hasNext() {
         return this.index < this.partitionList.size();
      }

      public Partition next() {
         return (Partition)this.partitionList.get(this.index++);
      }

      public void remove() {
         this.partitionList.remove(this.index);
      }
   }
}
