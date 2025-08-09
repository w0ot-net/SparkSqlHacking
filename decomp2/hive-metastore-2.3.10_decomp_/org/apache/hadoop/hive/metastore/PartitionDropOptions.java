package org.apache.hadoop.hive.metastore;

public class PartitionDropOptions {
   public boolean deleteData = true;
   public boolean ifExists = false;
   public boolean returnResults = true;
   public boolean purgeData = false;

   public static PartitionDropOptions instance() {
      return new PartitionDropOptions();
   }

   public PartitionDropOptions deleteData(boolean deleteData) {
      this.deleteData = deleteData;
      return this;
   }

   public PartitionDropOptions ifExists(boolean ifExists) {
      this.ifExists = ifExists;
      return this;
   }

   public PartitionDropOptions returnResults(boolean returnResults) {
      this.returnResults = returnResults;
      return this;
   }

   public PartitionDropOptions purgeData(boolean purgeData) {
      this.purgeData = purgeData;
      return this;
   }
}
