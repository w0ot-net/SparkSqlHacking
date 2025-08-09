package org.apache.hadoop.hive.metastore.partition.spec;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.apache.hadoop.hive.metastore.api.MetaException;
import org.apache.hadoop.hive.metastore.api.Partition;
import org.apache.hadoop.hive.metastore.api.PartitionSpec;
import org.apache.hadoop.hive.metastore.api.PartitionSpecWithSharedSD;
import org.apache.hadoop.hive.metastore.api.PartitionWithoutSD;
import org.apache.hadoop.hive.metastore.api.StorageDescriptor;

public class PartitionSpecWithSharedSDProxy extends PartitionSpecProxy {
   private PartitionSpec partitionSpec;

   public PartitionSpecWithSharedSDProxy(PartitionSpec partitionSpec) {
      assert partitionSpec.isSetSharedSDPartitionSpec();

      this.partitionSpec = partitionSpec;
   }

   public int size() {
      return this.partitionSpec.getSharedSDPartitionSpec().getPartitionsSize();
   }

   public void setDbName(String dbName) {
      this.partitionSpec.setDbName(dbName);
   }

   public void setTableName(String tableName) {
      this.partitionSpec.setTableName(tableName);
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

   public void setRootLocation(String rootLocation) throws MetaException {
      this.partitionSpec.setRootPath(rootLocation);
      this.partitionSpec.getSharedSDPartitionSpec().getSd().setLocation(rootLocation);
   }

   public static class Iterator implements PartitionSpecProxy.PartitionIterator {
      private PartitionSpecWithSharedSDProxy partitionSpecWithSharedSDProxy;
      private PartitionSpecWithSharedSD pSpec;
      private int index;

      Iterator(PartitionSpecWithSharedSDProxy partitionSpecWithSharedSDProxy) {
         this.partitionSpecWithSharedSDProxy = partitionSpecWithSharedSDProxy;
         this.pSpec = this.partitionSpecWithSharedSDProxy.partitionSpec.getSharedSDPartitionSpec();
         this.index = 0;
      }

      public boolean hasNext() {
         return this.index < this.pSpec.getPartitions().size();
      }

      public Partition next() {
         Partition partition = this.getCurrent();
         ++this.index;
         return partition;
      }

      public void remove() {
         this.pSpec.getPartitions().remove(this.index);
      }

      public Partition getCurrent() {
         PartitionWithoutSD partWithoutSD = (PartitionWithoutSD)this.pSpec.getPartitions().get(this.index);
         StorageDescriptor partSD = new StorageDescriptor(this.pSpec.getSd());
         partSD.setLocation(partSD.getLocation() + partWithoutSD.getRelativePath());
         return new Partition(partWithoutSD.getValues(), this.partitionSpecWithSharedSDProxy.partitionSpec.getDbName(), this.partitionSpecWithSharedSDProxy.partitionSpec.getTableName(), partWithoutSD.getCreateTime(), partWithoutSD.getLastAccessTime(), partSD, partWithoutSD.getParameters());
      }

      public String getDbName() {
         return this.partitionSpecWithSharedSDProxy.partitionSpec.getDbName();
      }

      public String getTableName() {
         return this.partitionSpecWithSharedSDProxy.partitionSpec.getTableName();
      }

      public Map getParameters() {
         return ((PartitionWithoutSD)this.pSpec.getPartitions().get(this.index)).getParameters();
      }

      public void setParameters(Map parameters) {
         ((PartitionWithoutSD)this.pSpec.getPartitions().get(this.index)).setParameters(parameters);
      }

      public String getLocation() {
         return this.pSpec.getSd().getLocation() + ((PartitionWithoutSD)this.pSpec.getPartitions().get(this.index)).getRelativePath();
      }

      public void putToParameters(String key, String value) {
         ((PartitionWithoutSD)this.pSpec.getPartitions().get(this.index)).putToParameters(key, value);
      }

      public void setCreateTime(long time) {
         ((PartitionWithoutSD)this.pSpec.getPartitions().get(this.index)).setCreateTime((int)time);
      }
   }
}
