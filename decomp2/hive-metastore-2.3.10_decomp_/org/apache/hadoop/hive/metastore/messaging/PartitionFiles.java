package org.apache.hadoop.hive.metastore.messaging;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;

public class PartitionFiles {
   @JsonProperty
   private String partitionName;
   @JsonProperty
   private List files;

   public PartitionFiles(String partitionName, Iterator files) {
      this.partitionName = partitionName;
      this.files = Lists.newArrayList(files);
   }

   public PartitionFiles() {
   }

   public String getPartitionName() {
      return this.partitionName;
   }

   public void setPartitionName(String partitionName) {
      this.partitionName = partitionName;
   }

   public Iterable getFiles() {
      return this.files;
   }
}
