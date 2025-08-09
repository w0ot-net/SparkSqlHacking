package org.apache.hadoop.hive.metastore.client.builder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.hadoop.hive.metastore.api.FieldSchema;
import org.apache.hadoop.hive.metastore.api.MetaException;
import org.apache.hadoop.hive.metastore.api.Order;
import org.apache.hadoop.hive.metastore.api.SerDeInfo;
import org.apache.hadoop.hive.metastore.api.SkewedInfo;
import org.apache.hadoop.hive.metastore.api.StorageDescriptor;

abstract class StorageDescriptorBuilder {
   private static final String SERDE_LIB = "org.apache.hadoop.hive.serde2.lazy.LazySimpleSerDe";
   private static final String INPUT_FORMAT = "org.apache.hadoop.hive.ql.io.HiveInputFormat";
   private static final String OUTPUT_FORMAT = "org.apache.hadoop.hive.ql.io.HiveOutputFormat";
   private String location;
   private String inputFormat = "org.apache.hadoop.hive.ql.io.HiveInputFormat";
   private String outputFormat = "org.apache.hadoop.hive.ql.io.HiveOutputFormat";
   private String serdeName;
   private String serdeLib = "org.apache.hadoop.hive.serde2.lazy.LazySimpleSerDe";
   private List cols;
   private int numBuckets = 0;
   private Map storageDescriptorParams = new HashMap();
   private Map serdeParams = new HashMap();
   private boolean compressed = false;
   private boolean storedAsSubDirectories;
   private List bucketCols = new ArrayList();
   private List skewedColNames = new ArrayList();
   private List sortCols = new ArrayList();
   private List skewedColValues = new ArrayList();
   private Map skewedColValueLocationMaps = new HashMap();
   private Object child;

   protected StorageDescriptorBuilder() {
   }

   protected StorageDescriptor buildSd() throws MetaException {
      if (this.cols == null) {
         throw new MetaException("You must provide the columns");
      } else {
         SerDeInfo serdeInfo = new SerDeInfo(this.serdeName, this.serdeLib, this.serdeParams);
         StorageDescriptor sd = new StorageDescriptor(this.cols, this.location, this.inputFormat, this.outputFormat, this.compressed, this.numBuckets, serdeInfo, this.bucketCols, this.sortCols, this.storageDescriptorParams);
         sd.setStoredAsSubDirectories(this.storedAsSubDirectories);
         if (this.skewedColNames != null) {
            SkewedInfo skewed = new SkewedInfo(this.skewedColNames, this.skewedColValues, this.skewedColValueLocationMaps);
            sd.setSkewedInfo(skewed);
         }

         return sd;
      }
   }

   protected void setChild(Object child) {
      this.child = child;
   }

   public Object setLocation(String location) {
      this.location = location;
      return this.child;
   }

   public Object setInputFormat(String inputFormat) {
      this.inputFormat = inputFormat;
      return this.child;
   }

   public Object setOutputFormat(String outputFormat) {
      this.outputFormat = outputFormat;
      return this.child;
   }

   public Object setSerdeName(String serdeName) {
      this.serdeName = serdeName;
      return this.child;
   }

   public Object setSerdeLib(String serdeLib) {
      this.serdeLib = serdeLib;
      return this.child;
   }

   public Object setCols(List cols) {
      this.cols = cols;
      return this.child;
   }

   public Object addCol(String name, String type, String comment) {
      if (this.cols == null) {
         this.cols = new ArrayList();
      }

      this.cols.add(new FieldSchema(name, type, comment));
      return this.child;
   }

   public Object addCol(String name, String type) {
      return this.addCol(name, type, "");
   }

   public Object setNumBuckets(int numBuckets) {
      this.numBuckets = numBuckets;
      return this.child;
   }

   public Object setStorageDescriptorParams(Map storageDescriptorParams) {
      this.storageDescriptorParams = storageDescriptorParams;
      return this.child;
   }

   public Object addStorageDescriptorParam(String key, String value) {
      if (this.storageDescriptorParams == null) {
         this.storageDescriptorParams = new HashMap();
      }

      this.storageDescriptorParams.put(key, value);
      return this.child;
   }

   public Object setSerdeParams(Map serdeParams) {
      this.serdeParams = serdeParams;
      return this.child;
   }

   public Object addSerdeParam(String key, String value) {
      if (this.serdeParams == null) {
         this.serdeParams = new HashMap();
      }

      this.serdeParams.put(key, value);
      return this.child;
   }

   public Object setCompressed(boolean compressed) {
      this.compressed = compressed;
      return this.child;
   }

   public Object setStoredAsSubDirectories(boolean storedAsSubDirectories) {
      this.storedAsSubDirectories = storedAsSubDirectories;
      return this.child;
   }

   public Object setBucketCols(List bucketCols) {
      this.bucketCols = bucketCols;
      return this.child;
   }

   public Object addBucketCol(String bucketCol) {
      if (this.bucketCols == null) {
         this.bucketCols = new ArrayList();
      }

      this.bucketCols.add(bucketCol);
      return this.child;
   }

   public Object setSkewedColNames(List skewedColNames) {
      this.skewedColNames = skewedColNames;
      return this.child;
   }

   public Object addSkewedColName(String skewedColName) {
      if (this.skewedColNames == null) {
         this.skewedColNames = new ArrayList();
      }

      this.skewedColNames.add(skewedColName);
      return this.child;
   }

   public Object setSortCols(List sortCols) {
      this.sortCols = sortCols;
      return this.child;
   }

   public Object addSortCol(String col, int order) {
      if (this.sortCols == null) {
         this.sortCols = new ArrayList();
      }

      this.sortCols.add(new Order(col, order));
      return this.child;
   }

   public Object setSkewedColValues(List skewedColValues) {
      this.skewedColValues = skewedColValues;
      return this.child;
   }

   public Object setSkewedColValueLocationMaps(Map skewedColValueLocationMaps) {
      this.skewedColValueLocationMaps = skewedColValueLocationMaps;
      return this.child;
   }
}
