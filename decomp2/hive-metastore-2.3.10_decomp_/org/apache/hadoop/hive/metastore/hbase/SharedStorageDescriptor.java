package org.apache.hadoop.hive.metastore.hbase;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.hadoop.hive.metastore.api.FieldSchema;
import org.apache.hadoop.hive.metastore.api.Order;
import org.apache.hadoop.hive.metastore.api.SerDeInfo;
import org.apache.hadoop.hive.metastore.api.SkewedInfo;
import org.apache.hadoop.hive.metastore.api.StorageDescriptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SharedStorageDescriptor extends StorageDescriptor {
   private static final Logger LOG = LoggerFactory.getLogger(SharedStorageDescriptor.class.getName());
   private boolean colsCopied = false;
   private boolean serdeCopied = false;
   private boolean bucketsCopied = false;
   private boolean sortCopied = false;
   private boolean skewedCopied = false;

   SharedStorageDescriptor() {
   }

   void setShared(StorageDescriptor shared) {
      if (shared.getCols() != null) {
         super.setCols(shared.getCols());
      }

      if (shared.getInputFormat() != null) {
         super.setInputFormat(shared.getInputFormat());
      }

      if (shared.getOutputFormat() != null) {
         super.setOutputFormat(shared.getOutputFormat());
      }

      super.setCompressed(shared.isCompressed());
      super.setNumBuckets(shared.getNumBuckets());
      if (shared.getSerdeInfo() != null) {
         super.setSerdeInfo(shared.getSerdeInfo());
      }

      if (shared.getBucketCols() != null) {
         super.setBucketCols(shared.getBucketCols());
      }

      if (shared.getSortCols() != null) {
         super.setSortCols(shared.getSortCols());
      }

      if (shared.getSkewedInfo() != null) {
         super.setSkewedInfo(shared.getSkewedInfo());
      }

      super.setStoredAsSubDirectories(shared.isStoredAsSubDirectories());
   }

   public void setReadOnly() {
      this.colsCopied = this.serdeCopied = this.bucketsCopied = this.sortCopied = this.skewedCopied = true;
   }

   public void addToCols(FieldSchema fs) {
      this.copyCols();
      super.addToCols(fs);
   }

   public List getCols() {
      this.copyCols();
      return super.getCols();
   }

   public void setCols(List cols) {
      this.colsCopied = true;
      super.setCols(cols);
   }

   public void unsetCols() {
      this.colsCopied = true;
      super.unsetCols();
   }

   public Iterator getColsIterator() {
      this.copyCols();
      return super.getColsIterator();
   }

   private void copyCols() {
      if (!this.colsCopied) {
         this.colsCopied = true;
         if (super.getCols() != null) {
            List<FieldSchema> cols = new ArrayList(super.getColsSize());

            for(FieldSchema fs : super.getCols()) {
               cols.add(new FieldSchema(fs));
            }

            super.setCols(cols);
         }
      }

   }

   public SerDeInfo getSerdeInfo() {
      this.copySerde();
      return super.getSerdeInfo();
   }

   public void setSerdeInfo(SerDeInfo serdeInfo) {
      this.serdeCopied = true;
      super.setSerdeInfo(serdeInfo);
   }

   public void unsetSerdeInfo() {
      this.serdeCopied = true;
      super.unsetSerdeInfo();
   }

   private void copySerde() {
      if (!this.serdeCopied) {
         this.serdeCopied = true;
         if (super.getSerdeInfo() != null) {
            super.setSerdeInfo(new SerDeInfo(super.getSerdeInfo()));
         }
      }

   }

   public void addToBucketCols(String bucket) {
      this.copyBucketCols();
      super.addToBucketCols(bucket);
   }

   public List getBucketCols() {
      this.copyBucketCols();
      return super.getBucketCols();
   }

   public void setBucketCols(List buckets) {
      this.bucketsCopied = true;
      super.setBucketCols(buckets);
   }

   public void unsetBucketCols() {
      this.bucketsCopied = true;
      super.unsetBucketCols();
   }

   public Iterator getBucketColsIterator() {
      this.copyBucketCols();
      return super.getBucketColsIterator();
   }

   private void copyBucketCols() {
      if (!this.bucketsCopied) {
         this.bucketsCopied = true;
         if (super.getBucketCols() != null) {
            List<String> buckets = new ArrayList(super.getBucketColsSize());

            for(String bucket : super.getBucketCols()) {
               buckets.add(bucket);
            }

            super.setBucketCols(buckets);
         }
      }

   }

   public void addToSortCols(Order sort) {
      this.copySort();
      super.addToSortCols(sort);
   }

   public List getSortCols() {
      this.copySort();
      return super.getSortCols();
   }

   public void setSortCols(List sorts) {
      this.sortCopied = true;
      super.setSortCols(sorts);
   }

   public void unsetSortCols() {
      this.sortCopied = true;
      super.unsetSortCols();
   }

   public Iterator getSortColsIterator() {
      this.copySort();
      return super.getSortColsIterator();
   }

   private void copySort() {
      if (!this.sortCopied) {
         this.sortCopied = true;
         if (super.getSortCols() != null) {
            List<Order> sortCols = new ArrayList(super.getSortColsSize());

            for(Order sortCol : super.getSortCols()) {
               sortCols.add(new Order(sortCol));
            }

            super.setSortCols(sortCols);
         }
      }

   }

   public SkewedInfo getSkewedInfo() {
      this.copySkewed();
      return super.getSkewedInfo();
   }

   public void setSkewedInfo(SkewedInfo skewedInfo) {
      this.skewedCopied = true;
      super.setSkewedInfo(skewedInfo);
   }

   public void unsetSkewedInfo() {
      this.skewedCopied = true;
      super.unsetSkewedInfo();
   }

   private void copySkewed() {
      if (!this.skewedCopied) {
         this.skewedCopied = true;
         if (super.getSkewedInfo() != null) {
            super.setSkewedInfo(new SkewedInfo(super.getSkewedInfo()));
         }
      }

   }
}
