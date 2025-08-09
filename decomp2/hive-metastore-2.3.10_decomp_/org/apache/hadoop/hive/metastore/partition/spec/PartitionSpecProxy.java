package org.apache.hadoop.hive.metastore.partition.spec;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.hadoop.hive.metastore.api.MetaException;
import org.apache.hadoop.hive.metastore.api.Partition;
import org.apache.hadoop.hive.metastore.api.PartitionSpec;

public abstract class PartitionSpecProxy {
   public abstract int size();

   public abstract void setDbName(String var1);

   public abstract void setTableName(String var1);

   public abstract String getDbName();

   public abstract String getTableName();

   public abstract PartitionIterator getPartitionIterator();

   public abstract List toPartitionSpec();

   public abstract void setRootLocation(String var1) throws MetaException;

   public static class Factory {
      public static PartitionSpecProxy get(PartitionSpec partSpec) {
         if (partSpec == null) {
            return null;
         } else if (partSpec.isSetPartitionList()) {
            return new PartitionListComposingSpecProxy(partSpec);
         } else if (partSpec.isSetSharedSDPartitionSpec()) {
            return new PartitionSpecWithSharedSDProxy(partSpec);
         } else {
            assert false : "Unsupported type of PartitionSpec!";

            return null;
         }
      }

      public static PartitionSpecProxy get(List partitionSpecs) {
         return new CompositePartitionSpecProxy(partitionSpecs);
      }
   }

   public static class SimplePartitionWrapperIterator implements PartitionIterator {
      private Partition partition;

      public SimplePartitionWrapperIterator(Partition partition) {
         this.partition = partition;
      }

      public Partition getCurrent() {
         return this.partition;
      }

      public String getDbName() {
         return this.partition.getDbName();
      }

      public String getTableName() {
         return this.partition.getTableName();
      }

      public Map getParameters() {
         return this.partition.getParameters();
      }

      public void setParameters(Map parameters) {
         this.partition.setParameters(parameters);
      }

      public void putToParameters(String key, String value) {
         this.partition.putToParameters(key, value);
      }

      public String getLocation() {
         return this.partition.getSd().getLocation();
      }

      public void setCreateTime(long time) {
         this.partition.setCreateTime((int)time);
      }

      public boolean hasNext() {
         return false;
      }

      public Partition next() {
         return null;
      }

      public void remove() {
      }
   }

   public interface PartitionIterator extends Iterator {
      Partition getCurrent();

      String getDbName();

      String getTableName();

      Map getParameters();

      void setParameters(Map var1);

      void putToParameters(String var1, String var2);

      String getLocation();

      void setCreateTime(long var1);
   }
}
