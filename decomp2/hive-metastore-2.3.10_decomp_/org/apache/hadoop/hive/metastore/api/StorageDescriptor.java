package org.apache.hadoop.hive.metastore.api;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.hive.common.util.HiveStringUtils;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TBase;
import org.apache.thrift.TBaseHelper;
import org.apache.thrift.TException;
import org.apache.thrift.TFieldIdEnum;
import org.apache.thrift.annotation.Nullable;
import org.apache.thrift.meta_data.FieldMetaData;
import org.apache.thrift.meta_data.FieldValueMetaData;
import org.apache.thrift.meta_data.ListMetaData;
import org.apache.thrift.meta_data.MapMetaData;
import org.apache.thrift.meta_data.StructMetaData;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TField;
import org.apache.thrift.protocol.TList;
import org.apache.thrift.protocol.TMap;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolUtil;
import org.apache.thrift.protocol.TStruct;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;
import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.transport.TIOStreamTransport;

public class StorageDescriptor implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("StorageDescriptor");
   private static final TField COLS_FIELD_DESC = new TField("cols", (byte)15, (short)1);
   private static final TField LOCATION_FIELD_DESC = new TField("location", (byte)11, (short)2);
   private static final TField INPUT_FORMAT_FIELD_DESC = new TField("inputFormat", (byte)11, (short)3);
   private static final TField OUTPUT_FORMAT_FIELD_DESC = new TField("outputFormat", (byte)11, (short)4);
   private static final TField COMPRESSED_FIELD_DESC = new TField("compressed", (byte)2, (short)5);
   private static final TField NUM_BUCKETS_FIELD_DESC = new TField("numBuckets", (byte)8, (short)6);
   private static final TField SERDE_INFO_FIELD_DESC = new TField("serdeInfo", (byte)12, (short)7);
   private static final TField BUCKET_COLS_FIELD_DESC = new TField("bucketCols", (byte)15, (short)8);
   private static final TField SORT_COLS_FIELD_DESC = new TField("sortCols", (byte)15, (short)9);
   private static final TField PARAMETERS_FIELD_DESC = new TField("parameters", (byte)13, (short)10);
   private static final TField SKEWED_INFO_FIELD_DESC = new TField("skewedInfo", (byte)12, (short)11);
   private static final TField STORED_AS_SUB_DIRECTORIES_FIELD_DESC = new TField("storedAsSubDirectories", (byte)2, (short)12);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new StorageDescriptorStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new StorageDescriptorTupleSchemeFactory();
   @Nullable
   private List cols;
   @Nullable
   private String location;
   @Nullable
   private String inputFormat;
   @Nullable
   private String outputFormat;
   private boolean compressed;
   private int numBuckets;
   @Nullable
   private SerDeInfo serdeInfo;
   @Nullable
   private List bucketCols;
   @Nullable
   private List sortCols;
   @Nullable
   private Map parameters;
   @Nullable
   private SkewedInfo skewedInfo;
   private boolean storedAsSubDirectories;
   private static final int __COMPRESSED_ISSET_ID = 0;
   private static final int __NUMBUCKETS_ISSET_ID = 1;
   private static final int __STOREDASSUBDIRECTORIES_ISSET_ID = 2;
   private byte __isset_bitfield;
   private static final _Fields[] optionals;
   public static final Map metaDataMap;

   public StorageDescriptor() {
      this.__isset_bitfield = 0;
   }

   public StorageDescriptor(List cols, String location, String inputFormat, String outputFormat, boolean compressed, int numBuckets, SerDeInfo serdeInfo, List bucketCols, List sortCols, Map parameters) {
      this();
      this.cols = cols;
      this.location = HiveStringUtils.intern(location);
      this.inputFormat = HiveStringUtils.intern(inputFormat);
      this.outputFormat = HiveStringUtils.intern(outputFormat);
      this.compressed = compressed;
      this.setCompressedIsSet(true);
      this.numBuckets = numBuckets;
      this.setNumBucketsIsSet(true);
      this.serdeInfo = serdeInfo;
      this.bucketCols = HiveStringUtils.intern(bucketCols);
      this.sortCols = sortCols;
      this.parameters = HiveStringUtils.intern(parameters);
   }

   public StorageDescriptor(StorageDescriptor other) {
      this.__isset_bitfield = 0;
      this.__isset_bitfield = other.__isset_bitfield;
      if (other.isSetCols()) {
         List<FieldSchema> __this__cols = new ArrayList(other.cols.size());

         for(FieldSchema other_element : other.cols) {
            __this__cols.add(new FieldSchema(other_element));
         }

         this.cols = __this__cols;
      }

      if (other.isSetLocation()) {
         this.location = HiveStringUtils.intern(other.location);
      }

      if (other.isSetInputFormat()) {
         this.inputFormat = HiveStringUtils.intern(other.inputFormat);
      }

      if (other.isSetOutputFormat()) {
         this.outputFormat = HiveStringUtils.intern(other.outputFormat);
      }

      this.compressed = other.compressed;
      this.numBuckets = other.numBuckets;
      if (other.isSetSerdeInfo()) {
         this.serdeInfo = new SerDeInfo(other.serdeInfo);
      }

      if (other.isSetBucketCols()) {
         List<String> __this__bucketCols = new ArrayList(other.bucketCols);
         this.bucketCols = __this__bucketCols;
      }

      if (other.isSetSortCols()) {
         List<Order> __this__sortCols = new ArrayList(other.sortCols.size());

         for(Order other_element : other.sortCols) {
            __this__sortCols.add(new Order(other_element));
         }

         this.sortCols = __this__sortCols;
      }

      if (other.isSetParameters()) {
         Map<String, String> __this__parameters = new HashMap(other.parameters);
         this.parameters = __this__parameters;
      }

      if (other.isSetSkewedInfo()) {
         this.skewedInfo = new SkewedInfo(other.skewedInfo);
      }

      this.storedAsSubDirectories = other.storedAsSubDirectories;
   }

   public StorageDescriptor deepCopy() {
      return new StorageDescriptor(this);
   }

   public void clear() {
      this.cols = null;
      this.location = null;
      this.inputFormat = null;
      this.outputFormat = null;
      this.setCompressedIsSet(false);
      this.compressed = false;
      this.setNumBucketsIsSet(false);
      this.numBuckets = 0;
      this.serdeInfo = null;
      this.bucketCols = null;
      this.sortCols = null;
      this.parameters = null;
      this.skewedInfo = null;
      this.setStoredAsSubDirectoriesIsSet(false);
      this.storedAsSubDirectories = false;
   }

   public int getColsSize() {
      return this.cols == null ? 0 : this.cols.size();
   }

   @Nullable
   public Iterator getColsIterator() {
      return this.cols == null ? null : this.cols.iterator();
   }

   public void addToCols(FieldSchema elem) {
      if (this.cols == null) {
         this.cols = new ArrayList();
      }

      this.cols.add(elem);
   }

   @Nullable
   public List getCols() {
      return this.cols;
   }

   public void setCols(@Nullable List cols) {
      this.cols = cols;
   }

   public void unsetCols() {
      this.cols = null;
   }

   public boolean isSetCols() {
      return this.cols != null;
   }

   public void setColsIsSet(boolean value) {
      if (!value) {
         this.cols = null;
      }

   }

   @Nullable
   public String getLocation() {
      return this.location;
   }

   public void setLocation(@Nullable String location) {
      this.location = HiveStringUtils.intern(location);
   }

   public void unsetLocation() {
      this.location = null;
   }

   public boolean isSetLocation() {
      return this.location != null;
   }

   public void setLocationIsSet(boolean value) {
      if (!value) {
         this.location = null;
      }

   }

   @Nullable
   public String getInputFormat() {
      return this.inputFormat;
   }

   public void setInputFormat(@Nullable String inputFormat) {
      this.inputFormat = HiveStringUtils.intern(inputFormat);
   }

   public void unsetInputFormat() {
      this.inputFormat = null;
   }

   public boolean isSetInputFormat() {
      return this.inputFormat != null;
   }

   public void setInputFormatIsSet(boolean value) {
      if (!value) {
         this.inputFormat = null;
      }

   }

   @Nullable
   public String getOutputFormat() {
      return this.outputFormat;
   }

   public void setOutputFormat(@Nullable String outputFormat) {
      this.outputFormat = HiveStringUtils.intern(outputFormat);
   }

   public void unsetOutputFormat() {
      this.outputFormat = null;
   }

   public boolean isSetOutputFormat() {
      return this.outputFormat != null;
   }

   public void setOutputFormatIsSet(boolean value) {
      if (!value) {
         this.outputFormat = null;
      }

   }

   public boolean isCompressed() {
      return this.compressed;
   }

   public void setCompressed(boolean compressed) {
      this.compressed = compressed;
      this.setCompressedIsSet(true);
   }

   public void unsetCompressed() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 0);
   }

   public boolean isSetCompressed() {
      return EncodingUtils.testBit(this.__isset_bitfield, 0);
   }

   public void setCompressedIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 0, value);
   }

   public int getNumBuckets() {
      return this.numBuckets;
   }

   public void setNumBuckets(int numBuckets) {
      this.numBuckets = numBuckets;
      this.setNumBucketsIsSet(true);
   }

   public void unsetNumBuckets() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 1);
   }

   public boolean isSetNumBuckets() {
      return EncodingUtils.testBit(this.__isset_bitfield, 1);
   }

   public void setNumBucketsIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 1, value);
   }

   @Nullable
   public SerDeInfo getSerdeInfo() {
      return this.serdeInfo;
   }

   public void setSerdeInfo(@Nullable SerDeInfo serdeInfo) {
      this.serdeInfo = serdeInfo;
   }

   public void unsetSerdeInfo() {
      this.serdeInfo = null;
   }

   public boolean isSetSerdeInfo() {
      return this.serdeInfo != null;
   }

   public void setSerdeInfoIsSet(boolean value) {
      if (!value) {
         this.serdeInfo = null;
      }

   }

   public int getBucketColsSize() {
      return this.bucketCols == null ? 0 : this.bucketCols.size();
   }

   @Nullable
   public Iterator getBucketColsIterator() {
      return this.bucketCols == null ? null : this.bucketCols.iterator();
   }

   public void addToBucketCols(String elem) {
      if (this.bucketCols == null) {
         this.bucketCols = new ArrayList();
      }

      this.bucketCols.add(elem);
   }

   @Nullable
   public List getBucketCols() {
      return this.bucketCols;
   }

   public void setBucketCols(@Nullable List bucketCols) {
      this.bucketCols = HiveStringUtils.intern(bucketCols);
   }

   public void unsetBucketCols() {
      this.bucketCols = null;
   }

   public boolean isSetBucketCols() {
      return this.bucketCols != null;
   }

   public void setBucketColsIsSet(boolean value) {
      if (!value) {
         this.bucketCols = null;
      }

   }

   public int getSortColsSize() {
      return this.sortCols == null ? 0 : this.sortCols.size();
   }

   @Nullable
   public Iterator getSortColsIterator() {
      return this.sortCols == null ? null : this.sortCols.iterator();
   }

   public void addToSortCols(Order elem) {
      if (this.sortCols == null) {
         this.sortCols = new ArrayList();
      }

      this.sortCols.add(elem);
   }

   @Nullable
   public List getSortCols() {
      return this.sortCols;
   }

   public void setSortCols(@Nullable List sortCols) {
      this.sortCols = sortCols;
   }

   public void unsetSortCols() {
      this.sortCols = null;
   }

   public boolean isSetSortCols() {
      return this.sortCols != null;
   }

   public void setSortColsIsSet(boolean value) {
      if (!value) {
         this.sortCols = null;
      }

   }

   public int getParametersSize() {
      return this.parameters == null ? 0 : this.parameters.size();
   }

   public void putToParameters(String key, String val) {
      if (this.parameters == null) {
         this.parameters = new HashMap();
      }

      this.parameters.put(key, val);
   }

   @Nullable
   public Map getParameters() {
      return this.parameters;
   }

   public void setParameters(@Nullable Map parameters) {
      this.parameters = HiveStringUtils.intern(parameters);
   }

   public void unsetParameters() {
      this.parameters = null;
   }

   public boolean isSetParameters() {
      return this.parameters != null;
   }

   public void setParametersIsSet(boolean value) {
      if (!value) {
         this.parameters = null;
      }

   }

   @Nullable
   public SkewedInfo getSkewedInfo() {
      return this.skewedInfo;
   }

   public void setSkewedInfo(@Nullable SkewedInfo skewedInfo) {
      this.skewedInfo = skewedInfo;
   }

   public void unsetSkewedInfo() {
      this.skewedInfo = null;
   }

   public boolean isSetSkewedInfo() {
      return this.skewedInfo != null;
   }

   public void setSkewedInfoIsSet(boolean value) {
      if (!value) {
         this.skewedInfo = null;
      }

   }

   public boolean isStoredAsSubDirectories() {
      return this.storedAsSubDirectories;
   }

   public void setStoredAsSubDirectories(boolean storedAsSubDirectories) {
      this.storedAsSubDirectories = storedAsSubDirectories;
      this.setStoredAsSubDirectoriesIsSet(true);
   }

   public void unsetStoredAsSubDirectories() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 2);
   }

   public boolean isSetStoredAsSubDirectories() {
      return EncodingUtils.testBit(this.__isset_bitfield, 2);
   }

   public void setStoredAsSubDirectoriesIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 2, value);
   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case COLS:
            if (value == null) {
               this.unsetCols();
            } else {
               this.setCols((List)value);
            }
            break;
         case LOCATION:
            if (value == null) {
               this.unsetLocation();
            } else {
               this.setLocation((String)value);
            }
            break;
         case INPUT_FORMAT:
            if (value == null) {
               this.unsetInputFormat();
            } else {
               this.setInputFormat((String)value);
            }
            break;
         case OUTPUT_FORMAT:
            if (value == null) {
               this.unsetOutputFormat();
            } else {
               this.setOutputFormat((String)value);
            }
            break;
         case COMPRESSED:
            if (value == null) {
               this.unsetCompressed();
            } else {
               this.setCompressed((Boolean)value);
            }
            break;
         case NUM_BUCKETS:
            if (value == null) {
               this.unsetNumBuckets();
            } else {
               this.setNumBuckets((Integer)value);
            }
            break;
         case SERDE_INFO:
            if (value == null) {
               this.unsetSerdeInfo();
            } else {
               this.setSerdeInfo((SerDeInfo)value);
            }
            break;
         case BUCKET_COLS:
            if (value == null) {
               this.unsetBucketCols();
            } else {
               this.setBucketCols((List)value);
            }
            break;
         case SORT_COLS:
            if (value == null) {
               this.unsetSortCols();
            } else {
               this.setSortCols((List)value);
            }
            break;
         case PARAMETERS:
            if (value == null) {
               this.unsetParameters();
            } else {
               this.setParameters((Map)value);
            }
            break;
         case SKEWED_INFO:
            if (value == null) {
               this.unsetSkewedInfo();
            } else {
               this.setSkewedInfo((SkewedInfo)value);
            }
            break;
         case STORED_AS_SUB_DIRECTORIES:
            if (value == null) {
               this.unsetStoredAsSubDirectories();
            } else {
               this.setStoredAsSubDirectories((Boolean)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case COLS:
            return this.getCols();
         case LOCATION:
            return this.getLocation();
         case INPUT_FORMAT:
            return this.getInputFormat();
         case OUTPUT_FORMAT:
            return this.getOutputFormat();
         case COMPRESSED:
            return this.isCompressed();
         case NUM_BUCKETS:
            return this.getNumBuckets();
         case SERDE_INFO:
            return this.getSerdeInfo();
         case BUCKET_COLS:
            return this.getBucketCols();
         case SORT_COLS:
            return this.getSortCols();
         case PARAMETERS:
            return this.getParameters();
         case SKEWED_INFO:
            return this.getSkewedInfo();
         case STORED_AS_SUB_DIRECTORIES:
            return this.isStoredAsSubDirectories();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case COLS:
               return this.isSetCols();
            case LOCATION:
               return this.isSetLocation();
            case INPUT_FORMAT:
               return this.isSetInputFormat();
            case OUTPUT_FORMAT:
               return this.isSetOutputFormat();
            case COMPRESSED:
               return this.isSetCompressed();
            case NUM_BUCKETS:
               return this.isSetNumBuckets();
            case SERDE_INFO:
               return this.isSetSerdeInfo();
            case BUCKET_COLS:
               return this.isSetBucketCols();
            case SORT_COLS:
               return this.isSetSortCols();
            case PARAMETERS:
               return this.isSetParameters();
            case SKEWED_INFO:
               return this.isSetSkewedInfo();
            case STORED_AS_SUB_DIRECTORIES:
               return this.isSetStoredAsSubDirectories();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof StorageDescriptor ? this.equals((StorageDescriptor)that) : false;
   }

   public boolean equals(StorageDescriptor that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_cols = this.isSetCols();
         boolean that_present_cols = that.isSetCols();
         if (this_present_cols || that_present_cols) {
            if (!this_present_cols || !that_present_cols) {
               return false;
            }

            if (!this.cols.equals(that.cols)) {
               return false;
            }
         }

         boolean this_present_location = this.isSetLocation();
         boolean that_present_location = that.isSetLocation();
         if (this_present_location || that_present_location) {
            if (!this_present_location || !that_present_location) {
               return false;
            }

            if (!this.location.equals(that.location)) {
               return false;
            }
         }

         boolean this_present_inputFormat = this.isSetInputFormat();
         boolean that_present_inputFormat = that.isSetInputFormat();
         if (this_present_inputFormat || that_present_inputFormat) {
            if (!this_present_inputFormat || !that_present_inputFormat) {
               return false;
            }

            if (!this.inputFormat.equals(that.inputFormat)) {
               return false;
            }
         }

         boolean this_present_outputFormat = this.isSetOutputFormat();
         boolean that_present_outputFormat = that.isSetOutputFormat();
         if (this_present_outputFormat || that_present_outputFormat) {
            if (!this_present_outputFormat || !that_present_outputFormat) {
               return false;
            }

            if (!this.outputFormat.equals(that.outputFormat)) {
               return false;
            }
         }

         boolean this_present_compressed = true;
         boolean that_present_compressed = true;
         if (this_present_compressed || that_present_compressed) {
            if (!this_present_compressed || !that_present_compressed) {
               return false;
            }

            if (this.compressed != that.compressed) {
               return false;
            }
         }

         boolean this_present_numBuckets = true;
         boolean that_present_numBuckets = true;
         if (this_present_numBuckets || that_present_numBuckets) {
            if (!this_present_numBuckets || !that_present_numBuckets) {
               return false;
            }

            if (this.numBuckets != that.numBuckets) {
               return false;
            }
         }

         boolean this_present_serdeInfo = this.isSetSerdeInfo();
         boolean that_present_serdeInfo = that.isSetSerdeInfo();
         if (this_present_serdeInfo || that_present_serdeInfo) {
            if (!this_present_serdeInfo || !that_present_serdeInfo) {
               return false;
            }

            if (!this.serdeInfo.equals(that.serdeInfo)) {
               return false;
            }
         }

         boolean this_present_bucketCols = this.isSetBucketCols();
         boolean that_present_bucketCols = that.isSetBucketCols();
         if (this_present_bucketCols || that_present_bucketCols) {
            if (!this_present_bucketCols || !that_present_bucketCols) {
               return false;
            }

            if (!this.bucketCols.equals(that.bucketCols)) {
               return false;
            }
         }

         boolean this_present_sortCols = this.isSetSortCols();
         boolean that_present_sortCols = that.isSetSortCols();
         if (this_present_sortCols || that_present_sortCols) {
            if (!this_present_sortCols || !that_present_sortCols) {
               return false;
            }

            if (!this.sortCols.equals(that.sortCols)) {
               return false;
            }
         }

         boolean this_present_parameters = this.isSetParameters();
         boolean that_present_parameters = that.isSetParameters();
         if (this_present_parameters || that_present_parameters) {
            if (!this_present_parameters || !that_present_parameters) {
               return false;
            }

            if (!this.parameters.equals(that.parameters)) {
               return false;
            }
         }

         boolean this_present_skewedInfo = this.isSetSkewedInfo();
         boolean that_present_skewedInfo = that.isSetSkewedInfo();
         if (this_present_skewedInfo || that_present_skewedInfo) {
            if (!this_present_skewedInfo || !that_present_skewedInfo) {
               return false;
            }

            if (!this.skewedInfo.equals(that.skewedInfo)) {
               return false;
            }
         }

         boolean this_present_storedAsSubDirectories = this.isSetStoredAsSubDirectories();
         boolean that_present_storedAsSubDirectories = that.isSetStoredAsSubDirectories();
         if (this_present_storedAsSubDirectories || that_present_storedAsSubDirectories) {
            if (!this_present_storedAsSubDirectories || !that_present_storedAsSubDirectories) {
               return false;
            }

            if (this.storedAsSubDirectories != that.storedAsSubDirectories) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + (this.isSetCols() ? 131071 : 524287);
      if (this.isSetCols()) {
         hashCode = hashCode * 8191 + this.cols.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetLocation() ? 131071 : 524287);
      if (this.isSetLocation()) {
         hashCode = hashCode * 8191 + this.location.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetInputFormat() ? 131071 : 524287);
      if (this.isSetInputFormat()) {
         hashCode = hashCode * 8191 + this.inputFormat.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetOutputFormat() ? 131071 : 524287);
      if (this.isSetOutputFormat()) {
         hashCode = hashCode * 8191 + this.outputFormat.hashCode();
      }

      hashCode = hashCode * 8191 + (this.compressed ? 131071 : 524287);
      hashCode = hashCode * 8191 + this.numBuckets;
      hashCode = hashCode * 8191 + (this.isSetSerdeInfo() ? 131071 : 524287);
      if (this.isSetSerdeInfo()) {
         hashCode = hashCode * 8191 + this.serdeInfo.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetBucketCols() ? 131071 : 524287);
      if (this.isSetBucketCols()) {
         hashCode = hashCode * 8191 + this.bucketCols.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetSortCols() ? 131071 : 524287);
      if (this.isSetSortCols()) {
         hashCode = hashCode * 8191 + this.sortCols.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetParameters() ? 131071 : 524287);
      if (this.isSetParameters()) {
         hashCode = hashCode * 8191 + this.parameters.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetSkewedInfo() ? 131071 : 524287);
      if (this.isSetSkewedInfo()) {
         hashCode = hashCode * 8191 + this.skewedInfo.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetStoredAsSubDirectories() ? 131071 : 524287);
      if (this.isSetStoredAsSubDirectories()) {
         hashCode = hashCode * 8191 + (this.storedAsSubDirectories ? 131071 : 524287);
      }

      return hashCode;
   }

   public int compareTo(StorageDescriptor other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetCols(), other.isSetCols());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetCols()) {
               lastComparison = TBaseHelper.compareTo(this.cols, other.cols);
               if (lastComparison != 0) {
                  return lastComparison;
               }
            }

            lastComparison = Boolean.compare(this.isSetLocation(), other.isSetLocation());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetLocation()) {
                  lastComparison = TBaseHelper.compareTo(this.location, other.location);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               lastComparison = Boolean.compare(this.isSetInputFormat(), other.isSetInputFormat());
               if (lastComparison != 0) {
                  return lastComparison;
               } else {
                  if (this.isSetInputFormat()) {
                     lastComparison = TBaseHelper.compareTo(this.inputFormat, other.inputFormat);
                     if (lastComparison != 0) {
                        return lastComparison;
                     }
                  }

                  lastComparison = Boolean.compare(this.isSetOutputFormat(), other.isSetOutputFormat());
                  if (lastComparison != 0) {
                     return lastComparison;
                  } else {
                     if (this.isSetOutputFormat()) {
                        lastComparison = TBaseHelper.compareTo(this.outputFormat, other.outputFormat);
                        if (lastComparison != 0) {
                           return lastComparison;
                        }
                     }

                     lastComparison = Boolean.compare(this.isSetCompressed(), other.isSetCompressed());
                     if (lastComparison != 0) {
                        return lastComparison;
                     } else {
                        if (this.isSetCompressed()) {
                           lastComparison = TBaseHelper.compareTo(this.compressed, other.compressed);
                           if (lastComparison != 0) {
                              return lastComparison;
                           }
                        }

                        lastComparison = Boolean.compare(this.isSetNumBuckets(), other.isSetNumBuckets());
                        if (lastComparison != 0) {
                           return lastComparison;
                        } else {
                           if (this.isSetNumBuckets()) {
                              lastComparison = TBaseHelper.compareTo(this.numBuckets, other.numBuckets);
                              if (lastComparison != 0) {
                                 return lastComparison;
                              }
                           }

                           lastComparison = Boolean.compare(this.isSetSerdeInfo(), other.isSetSerdeInfo());
                           if (lastComparison != 0) {
                              return lastComparison;
                           } else {
                              if (this.isSetSerdeInfo()) {
                                 lastComparison = TBaseHelper.compareTo(this.serdeInfo, other.serdeInfo);
                                 if (lastComparison != 0) {
                                    return lastComparison;
                                 }
                              }

                              lastComparison = Boolean.compare(this.isSetBucketCols(), other.isSetBucketCols());
                              if (lastComparison != 0) {
                                 return lastComparison;
                              } else {
                                 if (this.isSetBucketCols()) {
                                    lastComparison = TBaseHelper.compareTo(this.bucketCols, other.bucketCols);
                                    if (lastComparison != 0) {
                                       return lastComparison;
                                    }
                                 }

                                 lastComparison = Boolean.compare(this.isSetSortCols(), other.isSetSortCols());
                                 if (lastComparison != 0) {
                                    return lastComparison;
                                 } else {
                                    if (this.isSetSortCols()) {
                                       lastComparison = TBaseHelper.compareTo(this.sortCols, other.sortCols);
                                       if (lastComparison != 0) {
                                          return lastComparison;
                                       }
                                    }

                                    lastComparison = Boolean.compare(this.isSetParameters(), other.isSetParameters());
                                    if (lastComparison != 0) {
                                       return lastComparison;
                                    } else {
                                       if (this.isSetParameters()) {
                                          lastComparison = TBaseHelper.compareTo(this.parameters, other.parameters);
                                          if (lastComparison != 0) {
                                             return lastComparison;
                                          }
                                       }

                                       lastComparison = Boolean.compare(this.isSetSkewedInfo(), other.isSetSkewedInfo());
                                       if (lastComparison != 0) {
                                          return lastComparison;
                                       } else {
                                          if (this.isSetSkewedInfo()) {
                                             lastComparison = TBaseHelper.compareTo(this.skewedInfo, other.skewedInfo);
                                             if (lastComparison != 0) {
                                                return lastComparison;
                                             }
                                          }

                                          lastComparison = Boolean.compare(this.isSetStoredAsSubDirectories(), other.isSetStoredAsSubDirectories());
                                          if (lastComparison != 0) {
                                             return lastComparison;
                                          } else {
                                             if (this.isSetStoredAsSubDirectories()) {
                                                lastComparison = TBaseHelper.compareTo(this.storedAsSubDirectories, other.storedAsSubDirectories);
                                                if (lastComparison != 0) {
                                                   return lastComparison;
                                                }
                                             }

                                             return 0;
                                          }
                                       }
                                    }
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }

   @Nullable
   public _Fields fieldForId(int fieldId) {
      return StorageDescriptor._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("StorageDescriptor(");
      boolean first = true;
      sb.append("cols:");
      if (this.cols == null) {
         sb.append("null");
      } else {
         sb.append(this.cols);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("location:");
      if (this.location == null) {
         sb.append("null");
      } else {
         sb.append(this.location);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("inputFormat:");
      if (this.inputFormat == null) {
         sb.append("null");
      } else {
         sb.append(this.inputFormat);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("outputFormat:");
      if (this.outputFormat == null) {
         sb.append("null");
      } else {
         sb.append(this.outputFormat);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("compressed:");
      sb.append(this.compressed);
      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("numBuckets:");
      sb.append(this.numBuckets);
      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("serdeInfo:");
      if (this.serdeInfo == null) {
         sb.append("null");
      } else {
         sb.append(this.serdeInfo);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("bucketCols:");
      if (this.bucketCols == null) {
         sb.append("null");
      } else {
         sb.append(this.bucketCols);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("sortCols:");
      if (this.sortCols == null) {
         sb.append("null");
      } else {
         sb.append(this.sortCols);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("parameters:");
      if (this.parameters == null) {
         sb.append("null");
      } else {
         sb.append(this.parameters);
      }

      first = false;
      if (this.isSetSkewedInfo()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("skewedInfo:");
         if (this.skewedInfo == null) {
            sb.append("null");
         } else {
            sb.append(this.skewedInfo);
         }

         first = false;
      }

      if (this.isSetStoredAsSubDirectories()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("storedAsSubDirectories:");
         sb.append(this.storedAsSubDirectories);
         first = false;
      }

      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (this.serdeInfo != null) {
         this.serdeInfo.validate();
      }

      if (this.skewedInfo != null) {
         this.skewedInfo.validate();
      }

   }

   private void writeObject(ObjectOutputStream out) throws IOException {
      try {
         this.write(new TCompactProtocol(new TIOStreamTransport(out)));
      } catch (TException te) {
         throw new IOException(te);
      }
   }

   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
      try {
         this.__isset_bitfield = 0;
         this.read(new TCompactProtocol(new TIOStreamTransport(in)));
      } catch (TException te) {
         throw new IOException(te);
      }
   }

   private static IScheme scheme(TProtocol proto) {
      return (StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
   }

   static {
      optionals = new _Fields[]{StorageDescriptor._Fields.SKEWED_INFO, StorageDescriptor._Fields.STORED_AS_SUB_DIRECTORIES};
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(StorageDescriptor._Fields.COLS, new FieldMetaData("cols", (byte)3, new ListMetaData((byte)15, new StructMetaData((byte)12, FieldSchema.class))));
      tmpMap.put(StorageDescriptor._Fields.LOCATION, new FieldMetaData("location", (byte)3, new FieldValueMetaData((byte)11)));
      tmpMap.put(StorageDescriptor._Fields.INPUT_FORMAT, new FieldMetaData("inputFormat", (byte)3, new FieldValueMetaData((byte)11)));
      tmpMap.put(StorageDescriptor._Fields.OUTPUT_FORMAT, new FieldMetaData("outputFormat", (byte)3, new FieldValueMetaData((byte)11)));
      tmpMap.put(StorageDescriptor._Fields.COMPRESSED, new FieldMetaData("compressed", (byte)3, new FieldValueMetaData((byte)2)));
      tmpMap.put(StorageDescriptor._Fields.NUM_BUCKETS, new FieldMetaData("numBuckets", (byte)3, new FieldValueMetaData((byte)8)));
      tmpMap.put(StorageDescriptor._Fields.SERDE_INFO, new FieldMetaData("serdeInfo", (byte)3, new StructMetaData((byte)12, SerDeInfo.class)));
      tmpMap.put(StorageDescriptor._Fields.BUCKET_COLS, new FieldMetaData("bucketCols", (byte)3, new ListMetaData((byte)15, new FieldValueMetaData((byte)11))));
      tmpMap.put(StorageDescriptor._Fields.SORT_COLS, new FieldMetaData("sortCols", (byte)3, new ListMetaData((byte)15, new StructMetaData((byte)12, Order.class))));
      tmpMap.put(StorageDescriptor._Fields.PARAMETERS, new FieldMetaData("parameters", (byte)3, new MapMetaData((byte)13, new FieldValueMetaData((byte)11), new FieldValueMetaData((byte)11))));
      tmpMap.put(StorageDescriptor._Fields.SKEWED_INFO, new FieldMetaData("skewedInfo", (byte)2, new StructMetaData((byte)12, SkewedInfo.class)));
      tmpMap.put(StorageDescriptor._Fields.STORED_AS_SUB_DIRECTORIES, new FieldMetaData("storedAsSubDirectories", (byte)2, new FieldValueMetaData((byte)2)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(StorageDescriptor.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      COLS((short)1, "cols"),
      LOCATION((short)2, "location"),
      INPUT_FORMAT((short)3, "inputFormat"),
      OUTPUT_FORMAT((short)4, "outputFormat"),
      COMPRESSED((short)5, "compressed"),
      NUM_BUCKETS((short)6, "numBuckets"),
      SERDE_INFO((short)7, "serdeInfo"),
      BUCKET_COLS((short)8, "bucketCols"),
      SORT_COLS((short)9, "sortCols"),
      PARAMETERS((short)10, "parameters"),
      SKEWED_INFO((short)11, "skewedInfo"),
      STORED_AS_SUB_DIRECTORIES((short)12, "storedAsSubDirectories");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return COLS;
            case 2:
               return LOCATION;
            case 3:
               return INPUT_FORMAT;
            case 4:
               return OUTPUT_FORMAT;
            case 5:
               return COMPRESSED;
            case 6:
               return NUM_BUCKETS;
            case 7:
               return SERDE_INFO;
            case 8:
               return BUCKET_COLS;
            case 9:
               return SORT_COLS;
            case 10:
               return PARAMETERS;
            case 11:
               return SKEWED_INFO;
            case 12:
               return STORED_AS_SUB_DIRECTORIES;
            default:
               return null;
         }
      }

      public static _Fields findByThriftIdOrThrow(int fieldId) {
         _Fields fields = findByThriftId(fieldId);
         if (fields == null) {
            throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
         } else {
            return fields;
         }
      }

      @Nullable
      public static _Fields findByName(String name) {
         return (_Fields)byName.get(name);
      }

      private _Fields(short thriftId, String fieldName) {
         this._thriftId = thriftId;
         this._fieldName = fieldName;
      }

      public short getThriftFieldId() {
         return this._thriftId;
      }

      public String getFieldName() {
         return this._fieldName;
      }

      static {
         for(_Fields field : EnumSet.allOf(_Fields.class)) {
            byName.put(field.getFieldName(), field);
         }

      }
   }

   private static class StorageDescriptorStandardSchemeFactory implements SchemeFactory {
      private StorageDescriptorStandardSchemeFactory() {
      }

      public StorageDescriptorStandardScheme getScheme() {
         return new StorageDescriptorStandardScheme();
      }
   }

   private static class StorageDescriptorStandardScheme extends StandardScheme {
      private StorageDescriptorStandardScheme() {
      }

      public void read(TProtocol iprot, StorageDescriptor struct) throws TException {
         iprot.readStructBegin();

         while(true) {
            TField schemeField = iprot.readFieldBegin();
            if (schemeField.type == 0) {
               iprot.readStructEnd();
               struct.validate();
               return;
            }

            switch (schemeField.id) {
               case 1:
                  if (schemeField.type != 15) {
                     TProtocolUtil.skip(iprot, schemeField.type);
                     break;
                  }

                  TList _list156 = iprot.readListBegin();
                  struct.cols = new ArrayList(_list156.size);

                  for(int _i158 = 0; _i158 < _list156.size; ++_i158) {
                     FieldSchema _elem157 = new FieldSchema();
                     _elem157.read(iprot);
                     struct.cols.add(_elem157);
                  }

                  iprot.readListEnd();
                  struct.setColsIsSet(true);
                  break;
               case 2:
                  if (schemeField.type == 11) {
                     struct.location = iprot.readString();
                     struct.setLocationIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 3:
                  if (schemeField.type == 11) {
                     struct.inputFormat = iprot.readString();
                     struct.setInputFormatIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 4:
                  if (schemeField.type == 11) {
                     struct.outputFormat = iprot.readString();
                     struct.setOutputFormatIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 5:
                  if (schemeField.type == 2) {
                     struct.compressed = iprot.readBool();
                     struct.setCompressedIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 6:
                  if (schemeField.type == 8) {
                     struct.numBuckets = iprot.readI32();
                     struct.setNumBucketsIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 7:
                  if (schemeField.type == 12) {
                     struct.serdeInfo = new SerDeInfo();
                     struct.serdeInfo.read(iprot);
                     struct.setSerdeInfoIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 8:
                  if (schemeField.type != 15) {
                     TProtocolUtil.skip(iprot, schemeField.type);
                     break;
                  }

                  TList _list159 = iprot.readListBegin();
                  struct.bucketCols = new ArrayList(_list159.size);

                  for(int _i161 = 0; _i161 < _list159.size; ++_i161) {
                     String _elem160 = iprot.readString();
                     struct.bucketCols.add(_elem160);
                  }

                  iprot.readListEnd();
                  struct.setBucketColsIsSet(true);
                  break;
               case 9:
                  if (schemeField.type != 15) {
                     TProtocolUtil.skip(iprot, schemeField.type);
                     break;
                  }

                  TList _list162 = iprot.readListBegin();
                  struct.sortCols = new ArrayList(_list162.size);

                  for(int _i164 = 0; _i164 < _list162.size; ++_i164) {
                     Order _elem163 = new Order();
                     _elem163.read(iprot);
                     struct.sortCols.add(_elem163);
                  }

                  iprot.readListEnd();
                  struct.setSortColsIsSet(true);
                  break;
               case 10:
                  if (schemeField.type != 13) {
                     TProtocolUtil.skip(iprot, schemeField.type);
                     break;
                  }

                  TMap _map165 = iprot.readMapBegin();
                  struct.parameters = new HashMap(2 * _map165.size);

                  for(int _i168 = 0; _i168 < _map165.size; ++_i168) {
                     String _key166 = iprot.readString();
                     String _val167 = iprot.readString();
                     struct.parameters.put(_key166, _val167);
                  }

                  iprot.readMapEnd();
                  struct.setParametersIsSet(true);
                  break;
               case 11:
                  if (schemeField.type == 12) {
                     struct.skewedInfo = new SkewedInfo();
                     struct.skewedInfo.read(iprot);
                     struct.setSkewedInfoIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 12:
                  if (schemeField.type == 2) {
                     struct.storedAsSubDirectories = iprot.readBool();
                     struct.setStoredAsSubDirectoriesIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               default:
                  TProtocolUtil.skip(iprot, schemeField.type);
            }

            iprot.readFieldEnd();
         }
      }

      public void write(TProtocol oprot, StorageDescriptor struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(StorageDescriptor.STRUCT_DESC);
         if (struct.cols != null) {
            oprot.writeFieldBegin(StorageDescriptor.COLS_FIELD_DESC);
            oprot.writeListBegin(new TList((byte)12, struct.cols.size()));

            for(FieldSchema _iter169 : struct.cols) {
               _iter169.write(oprot);
            }

            oprot.writeListEnd();
            oprot.writeFieldEnd();
         }

         if (struct.location != null) {
            oprot.writeFieldBegin(StorageDescriptor.LOCATION_FIELD_DESC);
            oprot.writeString(struct.location);
            oprot.writeFieldEnd();
         }

         if (struct.inputFormat != null) {
            oprot.writeFieldBegin(StorageDescriptor.INPUT_FORMAT_FIELD_DESC);
            oprot.writeString(struct.inputFormat);
            oprot.writeFieldEnd();
         }

         if (struct.outputFormat != null) {
            oprot.writeFieldBegin(StorageDescriptor.OUTPUT_FORMAT_FIELD_DESC);
            oprot.writeString(struct.outputFormat);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldBegin(StorageDescriptor.COMPRESSED_FIELD_DESC);
         oprot.writeBool(struct.compressed);
         oprot.writeFieldEnd();
         oprot.writeFieldBegin(StorageDescriptor.NUM_BUCKETS_FIELD_DESC);
         oprot.writeI32(struct.numBuckets);
         oprot.writeFieldEnd();
         if (struct.serdeInfo != null) {
            oprot.writeFieldBegin(StorageDescriptor.SERDE_INFO_FIELD_DESC);
            struct.serdeInfo.write(oprot);
            oprot.writeFieldEnd();
         }

         if (struct.bucketCols != null) {
            oprot.writeFieldBegin(StorageDescriptor.BUCKET_COLS_FIELD_DESC);
            oprot.writeListBegin(new TList((byte)11, struct.bucketCols.size()));

            for(String _iter170 : struct.bucketCols) {
               oprot.writeString(_iter170);
            }

            oprot.writeListEnd();
            oprot.writeFieldEnd();
         }

         if (struct.sortCols != null) {
            oprot.writeFieldBegin(StorageDescriptor.SORT_COLS_FIELD_DESC);
            oprot.writeListBegin(new TList((byte)12, struct.sortCols.size()));

            for(Order _iter171 : struct.sortCols) {
               _iter171.write(oprot);
            }

            oprot.writeListEnd();
            oprot.writeFieldEnd();
         }

         if (struct.parameters != null) {
            oprot.writeFieldBegin(StorageDescriptor.PARAMETERS_FIELD_DESC);
            oprot.writeMapBegin(new TMap((byte)11, (byte)11, struct.parameters.size()));

            for(Map.Entry _iter172 : struct.parameters.entrySet()) {
               oprot.writeString((String)_iter172.getKey());
               oprot.writeString((String)_iter172.getValue());
            }

            oprot.writeMapEnd();
            oprot.writeFieldEnd();
         }

         if (struct.skewedInfo != null && struct.isSetSkewedInfo()) {
            oprot.writeFieldBegin(StorageDescriptor.SKEWED_INFO_FIELD_DESC);
            struct.skewedInfo.write(oprot);
            oprot.writeFieldEnd();
         }

         if (struct.isSetStoredAsSubDirectories()) {
            oprot.writeFieldBegin(StorageDescriptor.STORED_AS_SUB_DIRECTORIES_FIELD_DESC);
            oprot.writeBool(struct.storedAsSubDirectories);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class StorageDescriptorTupleSchemeFactory implements SchemeFactory {
      private StorageDescriptorTupleSchemeFactory() {
      }

      public StorageDescriptorTupleScheme getScheme() {
         return new StorageDescriptorTupleScheme();
      }
   }

   private static class StorageDescriptorTupleScheme extends TupleScheme {
      private StorageDescriptorTupleScheme() {
      }

      public void write(TProtocol prot, StorageDescriptor struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         BitSet optionals = new BitSet();
         if (struct.isSetCols()) {
            optionals.set(0);
         }

         if (struct.isSetLocation()) {
            optionals.set(1);
         }

         if (struct.isSetInputFormat()) {
            optionals.set(2);
         }

         if (struct.isSetOutputFormat()) {
            optionals.set(3);
         }

         if (struct.isSetCompressed()) {
            optionals.set(4);
         }

         if (struct.isSetNumBuckets()) {
            optionals.set(5);
         }

         if (struct.isSetSerdeInfo()) {
            optionals.set(6);
         }

         if (struct.isSetBucketCols()) {
            optionals.set(7);
         }

         if (struct.isSetSortCols()) {
            optionals.set(8);
         }

         if (struct.isSetParameters()) {
            optionals.set(9);
         }

         if (struct.isSetSkewedInfo()) {
            optionals.set(10);
         }

         if (struct.isSetStoredAsSubDirectories()) {
            optionals.set(11);
         }

         oprot.writeBitSet(optionals, 12);
         if (struct.isSetCols()) {
            oprot.writeI32(struct.cols.size());

            for(FieldSchema _iter173 : struct.cols) {
               _iter173.write(oprot);
            }
         }

         if (struct.isSetLocation()) {
            oprot.writeString(struct.location);
         }

         if (struct.isSetInputFormat()) {
            oprot.writeString(struct.inputFormat);
         }

         if (struct.isSetOutputFormat()) {
            oprot.writeString(struct.outputFormat);
         }

         if (struct.isSetCompressed()) {
            oprot.writeBool(struct.compressed);
         }

         if (struct.isSetNumBuckets()) {
            oprot.writeI32(struct.numBuckets);
         }

         if (struct.isSetSerdeInfo()) {
            struct.serdeInfo.write(oprot);
         }

         if (struct.isSetBucketCols()) {
            oprot.writeI32(struct.bucketCols.size());

            for(String _iter174 : struct.bucketCols) {
               oprot.writeString(_iter174);
            }
         }

         if (struct.isSetSortCols()) {
            oprot.writeI32(struct.sortCols.size());

            for(Order _iter175 : struct.sortCols) {
               _iter175.write(oprot);
            }
         }

         if (struct.isSetParameters()) {
            oprot.writeI32(struct.parameters.size());

            for(Map.Entry _iter176 : struct.parameters.entrySet()) {
               oprot.writeString((String)_iter176.getKey());
               oprot.writeString((String)_iter176.getValue());
            }
         }

         if (struct.isSetSkewedInfo()) {
            struct.skewedInfo.write(oprot);
         }

         if (struct.isSetStoredAsSubDirectories()) {
            oprot.writeBool(struct.storedAsSubDirectories);
         }

      }

      public void read(TProtocol prot, StorageDescriptor struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         BitSet incoming = iprot.readBitSet(12);
         if (incoming.get(0)) {
            TList _list177 = iprot.readListBegin((byte)12);
            struct.cols = new ArrayList(_list177.size);

            for(int _i179 = 0; _i179 < _list177.size; ++_i179) {
               FieldSchema _elem178 = new FieldSchema();
               _elem178.read(iprot);
               struct.cols.add(_elem178);
            }

            struct.setColsIsSet(true);
         }

         if (incoming.get(1)) {
            struct.location = iprot.readString();
            struct.setLocationIsSet(true);
         }

         if (incoming.get(2)) {
            struct.inputFormat = iprot.readString();
            struct.setInputFormatIsSet(true);
         }

         if (incoming.get(3)) {
            struct.outputFormat = iprot.readString();
            struct.setOutputFormatIsSet(true);
         }

         if (incoming.get(4)) {
            struct.compressed = iprot.readBool();
            struct.setCompressedIsSet(true);
         }

         if (incoming.get(5)) {
            struct.numBuckets = iprot.readI32();
            struct.setNumBucketsIsSet(true);
         }

         if (incoming.get(6)) {
            struct.serdeInfo = new SerDeInfo();
            struct.serdeInfo.read(iprot);
            struct.setSerdeInfoIsSet(true);
         }

         if (incoming.get(7)) {
            TList _list180 = iprot.readListBegin((byte)11);
            struct.bucketCols = new ArrayList(_list180.size);

            for(int _i182 = 0; _i182 < _list180.size; ++_i182) {
               String _elem181 = iprot.readString();
               struct.bucketCols.add(_elem181);
            }

            struct.setBucketColsIsSet(true);
         }

         if (incoming.get(8)) {
            TList _list183 = iprot.readListBegin((byte)12);
            struct.sortCols = new ArrayList(_list183.size);

            for(int _i185 = 0; _i185 < _list183.size; ++_i185) {
               Order _elem184 = new Order();
               _elem184.read(iprot);
               struct.sortCols.add(_elem184);
            }

            struct.setSortColsIsSet(true);
         }

         if (incoming.get(9)) {
            TMap _map186 = iprot.readMapBegin((byte)11, (byte)11);
            struct.parameters = new HashMap(2 * _map186.size);

            for(int _i189 = 0; _i189 < _map186.size; ++_i189) {
               String _key187 = iprot.readString();
               String _val188 = iprot.readString();
               struct.parameters.put(_key187, _val188);
            }

            struct.setParametersIsSet(true);
         }

         if (incoming.get(10)) {
            struct.skewedInfo = new SkewedInfo();
            struct.skewedInfo.read(iprot);
            struct.setSkewedInfoIsSet(true);
         }

         if (incoming.get(11)) {
            struct.storedAsSubDirectories = iprot.readBool();
            struct.setStoredAsSubDirectoriesIsSet(true);
         }

      }
   }
}
