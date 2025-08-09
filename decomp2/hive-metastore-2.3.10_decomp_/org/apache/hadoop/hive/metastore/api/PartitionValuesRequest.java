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
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TBase;
import org.apache.thrift.TBaseHelper;
import org.apache.thrift.TException;
import org.apache.thrift.TFieldIdEnum;
import org.apache.thrift.annotation.Nullable;
import org.apache.thrift.meta_data.FieldMetaData;
import org.apache.thrift.meta_data.FieldValueMetaData;
import org.apache.thrift.meta_data.ListMetaData;
import org.apache.thrift.meta_data.StructMetaData;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TField;
import org.apache.thrift.protocol.TList;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.protocol.TProtocolUtil;
import org.apache.thrift.protocol.TStruct;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;
import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.transport.TIOStreamTransport;

public class PartitionValuesRequest implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("PartitionValuesRequest");
   private static final TField DB_NAME_FIELD_DESC = new TField("dbName", (byte)11, (short)1);
   private static final TField TBL_NAME_FIELD_DESC = new TField("tblName", (byte)11, (short)2);
   private static final TField PARTITION_KEYS_FIELD_DESC = new TField("partitionKeys", (byte)15, (short)3);
   private static final TField APPLY_DISTINCT_FIELD_DESC = new TField("applyDistinct", (byte)2, (short)4);
   private static final TField FILTER_FIELD_DESC = new TField("filter", (byte)11, (short)5);
   private static final TField PARTITION_ORDER_FIELD_DESC = new TField("partitionOrder", (byte)15, (short)6);
   private static final TField ASCENDING_FIELD_DESC = new TField("ascending", (byte)2, (short)7);
   private static final TField MAX_PARTS_FIELD_DESC = new TField("maxParts", (byte)10, (short)8);
   private static final TField CAT_NAME_FIELD_DESC = new TField("catName", (byte)11, (short)9);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new PartitionValuesRequestStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new PartitionValuesRequestTupleSchemeFactory();
   @Nullable
   private String dbName;
   @Nullable
   private String tblName;
   @Nullable
   private List partitionKeys;
   private boolean applyDistinct;
   @Nullable
   private String filter;
   @Nullable
   private List partitionOrder;
   private boolean ascending;
   private long maxParts;
   @Nullable
   private String catName;
   private static final int __APPLYDISTINCT_ISSET_ID = 0;
   private static final int __ASCENDING_ISSET_ID = 1;
   private static final int __MAXPARTS_ISSET_ID = 2;
   private byte __isset_bitfield;
   private static final _Fields[] optionals;
   public static final Map metaDataMap;

   public PartitionValuesRequest() {
      this.__isset_bitfield = 0;
      this.applyDistinct = true;
      this.ascending = true;
      this.maxParts = -1L;
   }

   public PartitionValuesRequest(String dbName, String tblName, List partitionKeys) {
      this();
      this.dbName = dbName;
      this.tblName = tblName;
      this.partitionKeys = partitionKeys;
   }

   public PartitionValuesRequest(PartitionValuesRequest other) {
      this.__isset_bitfield = 0;
      this.__isset_bitfield = other.__isset_bitfield;
      if (other.isSetDbName()) {
         this.dbName = other.dbName;
      }

      if (other.isSetTblName()) {
         this.tblName = other.tblName;
      }

      if (other.isSetPartitionKeys()) {
         List<FieldSchema> __this__partitionKeys = new ArrayList(other.partitionKeys.size());

         for(FieldSchema other_element : other.partitionKeys) {
            __this__partitionKeys.add(new FieldSchema(other_element));
         }

         this.partitionKeys = __this__partitionKeys;
      }

      this.applyDistinct = other.applyDistinct;
      if (other.isSetFilter()) {
         this.filter = other.filter;
      }

      if (other.isSetPartitionOrder()) {
         List<FieldSchema> __this__partitionOrder = new ArrayList(other.partitionOrder.size());

         for(FieldSchema other_element : other.partitionOrder) {
            __this__partitionOrder.add(new FieldSchema(other_element));
         }

         this.partitionOrder = __this__partitionOrder;
      }

      this.ascending = other.ascending;
      this.maxParts = other.maxParts;
      if (other.isSetCatName()) {
         this.catName = other.catName;
      }

   }

   public PartitionValuesRequest deepCopy() {
      return new PartitionValuesRequest(this);
   }

   public void clear() {
      this.dbName = null;
      this.tblName = null;
      this.partitionKeys = null;
      this.applyDistinct = true;
      this.filter = null;
      this.partitionOrder = null;
      this.ascending = true;
      this.maxParts = -1L;
      this.catName = null;
   }

   @Nullable
   public String getDbName() {
      return this.dbName;
   }

   public void setDbName(@Nullable String dbName) {
      this.dbName = dbName;
   }

   public void unsetDbName() {
      this.dbName = null;
   }

   public boolean isSetDbName() {
      return this.dbName != null;
   }

   public void setDbNameIsSet(boolean value) {
      if (!value) {
         this.dbName = null;
      }

   }

   @Nullable
   public String getTblName() {
      return this.tblName;
   }

   public void setTblName(@Nullable String tblName) {
      this.tblName = tblName;
   }

   public void unsetTblName() {
      this.tblName = null;
   }

   public boolean isSetTblName() {
      return this.tblName != null;
   }

   public void setTblNameIsSet(boolean value) {
      if (!value) {
         this.tblName = null;
      }

   }

   public int getPartitionKeysSize() {
      return this.partitionKeys == null ? 0 : this.partitionKeys.size();
   }

   @Nullable
   public Iterator getPartitionKeysIterator() {
      return this.partitionKeys == null ? null : this.partitionKeys.iterator();
   }

   public void addToPartitionKeys(FieldSchema elem) {
      if (this.partitionKeys == null) {
         this.partitionKeys = new ArrayList();
      }

      this.partitionKeys.add(elem);
   }

   @Nullable
   public List getPartitionKeys() {
      return this.partitionKeys;
   }

   public void setPartitionKeys(@Nullable List partitionKeys) {
      this.partitionKeys = partitionKeys;
   }

   public void unsetPartitionKeys() {
      this.partitionKeys = null;
   }

   public boolean isSetPartitionKeys() {
      return this.partitionKeys != null;
   }

   public void setPartitionKeysIsSet(boolean value) {
      if (!value) {
         this.partitionKeys = null;
      }

   }

   public boolean isApplyDistinct() {
      return this.applyDistinct;
   }

   public void setApplyDistinct(boolean applyDistinct) {
      this.applyDistinct = applyDistinct;
      this.setApplyDistinctIsSet(true);
   }

   public void unsetApplyDistinct() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 0);
   }

   public boolean isSetApplyDistinct() {
      return EncodingUtils.testBit(this.__isset_bitfield, 0);
   }

   public void setApplyDistinctIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 0, value);
   }

   @Nullable
   public String getFilter() {
      return this.filter;
   }

   public void setFilter(@Nullable String filter) {
      this.filter = filter;
   }

   public void unsetFilter() {
      this.filter = null;
   }

   public boolean isSetFilter() {
      return this.filter != null;
   }

   public void setFilterIsSet(boolean value) {
      if (!value) {
         this.filter = null;
      }

   }

   public int getPartitionOrderSize() {
      return this.partitionOrder == null ? 0 : this.partitionOrder.size();
   }

   @Nullable
   public Iterator getPartitionOrderIterator() {
      return this.partitionOrder == null ? null : this.partitionOrder.iterator();
   }

   public void addToPartitionOrder(FieldSchema elem) {
      if (this.partitionOrder == null) {
         this.partitionOrder = new ArrayList();
      }

      this.partitionOrder.add(elem);
   }

   @Nullable
   public List getPartitionOrder() {
      return this.partitionOrder;
   }

   public void setPartitionOrder(@Nullable List partitionOrder) {
      this.partitionOrder = partitionOrder;
   }

   public void unsetPartitionOrder() {
      this.partitionOrder = null;
   }

   public boolean isSetPartitionOrder() {
      return this.partitionOrder != null;
   }

   public void setPartitionOrderIsSet(boolean value) {
      if (!value) {
         this.partitionOrder = null;
      }

   }

   public boolean isAscending() {
      return this.ascending;
   }

   public void setAscending(boolean ascending) {
      this.ascending = ascending;
      this.setAscendingIsSet(true);
   }

   public void unsetAscending() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 1);
   }

   public boolean isSetAscending() {
      return EncodingUtils.testBit(this.__isset_bitfield, 1);
   }

   public void setAscendingIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 1, value);
   }

   public long getMaxParts() {
      return this.maxParts;
   }

   public void setMaxParts(long maxParts) {
      this.maxParts = maxParts;
      this.setMaxPartsIsSet(true);
   }

   public void unsetMaxParts() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 2);
   }

   public boolean isSetMaxParts() {
      return EncodingUtils.testBit(this.__isset_bitfield, 2);
   }

   public void setMaxPartsIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 2, value);
   }

   @Nullable
   public String getCatName() {
      return this.catName;
   }

   public void setCatName(@Nullable String catName) {
      this.catName = catName;
   }

   public void unsetCatName() {
      this.catName = null;
   }

   public boolean isSetCatName() {
      return this.catName != null;
   }

   public void setCatNameIsSet(boolean value) {
      if (!value) {
         this.catName = null;
      }

   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case DB_NAME:
            if (value == null) {
               this.unsetDbName();
            } else {
               this.setDbName((String)value);
            }
            break;
         case TBL_NAME:
            if (value == null) {
               this.unsetTblName();
            } else {
               this.setTblName((String)value);
            }
            break;
         case PARTITION_KEYS:
            if (value == null) {
               this.unsetPartitionKeys();
            } else {
               this.setPartitionKeys((List)value);
            }
            break;
         case APPLY_DISTINCT:
            if (value == null) {
               this.unsetApplyDistinct();
            } else {
               this.setApplyDistinct((Boolean)value);
            }
            break;
         case FILTER:
            if (value == null) {
               this.unsetFilter();
            } else {
               this.setFilter((String)value);
            }
            break;
         case PARTITION_ORDER:
            if (value == null) {
               this.unsetPartitionOrder();
            } else {
               this.setPartitionOrder((List)value);
            }
            break;
         case ASCENDING:
            if (value == null) {
               this.unsetAscending();
            } else {
               this.setAscending((Boolean)value);
            }
            break;
         case MAX_PARTS:
            if (value == null) {
               this.unsetMaxParts();
            } else {
               this.setMaxParts((Long)value);
            }
            break;
         case CAT_NAME:
            if (value == null) {
               this.unsetCatName();
            } else {
               this.setCatName((String)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case DB_NAME:
            return this.getDbName();
         case TBL_NAME:
            return this.getTblName();
         case PARTITION_KEYS:
            return this.getPartitionKeys();
         case APPLY_DISTINCT:
            return this.isApplyDistinct();
         case FILTER:
            return this.getFilter();
         case PARTITION_ORDER:
            return this.getPartitionOrder();
         case ASCENDING:
            return this.isAscending();
         case MAX_PARTS:
            return this.getMaxParts();
         case CAT_NAME:
            return this.getCatName();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case DB_NAME:
               return this.isSetDbName();
            case TBL_NAME:
               return this.isSetTblName();
            case PARTITION_KEYS:
               return this.isSetPartitionKeys();
            case APPLY_DISTINCT:
               return this.isSetApplyDistinct();
            case FILTER:
               return this.isSetFilter();
            case PARTITION_ORDER:
               return this.isSetPartitionOrder();
            case ASCENDING:
               return this.isSetAscending();
            case MAX_PARTS:
               return this.isSetMaxParts();
            case CAT_NAME:
               return this.isSetCatName();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof PartitionValuesRequest ? this.equals((PartitionValuesRequest)that) : false;
   }

   public boolean equals(PartitionValuesRequest that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_dbName = this.isSetDbName();
         boolean that_present_dbName = that.isSetDbName();
         if (this_present_dbName || that_present_dbName) {
            if (!this_present_dbName || !that_present_dbName) {
               return false;
            }

            if (!this.dbName.equals(that.dbName)) {
               return false;
            }
         }

         boolean this_present_tblName = this.isSetTblName();
         boolean that_present_tblName = that.isSetTblName();
         if (this_present_tblName || that_present_tblName) {
            if (!this_present_tblName || !that_present_tblName) {
               return false;
            }

            if (!this.tblName.equals(that.tblName)) {
               return false;
            }
         }

         boolean this_present_partitionKeys = this.isSetPartitionKeys();
         boolean that_present_partitionKeys = that.isSetPartitionKeys();
         if (this_present_partitionKeys || that_present_partitionKeys) {
            if (!this_present_partitionKeys || !that_present_partitionKeys) {
               return false;
            }

            if (!this.partitionKeys.equals(that.partitionKeys)) {
               return false;
            }
         }

         boolean this_present_applyDistinct = this.isSetApplyDistinct();
         boolean that_present_applyDistinct = that.isSetApplyDistinct();
         if (this_present_applyDistinct || that_present_applyDistinct) {
            if (!this_present_applyDistinct || !that_present_applyDistinct) {
               return false;
            }

            if (this.applyDistinct != that.applyDistinct) {
               return false;
            }
         }

         boolean this_present_filter = this.isSetFilter();
         boolean that_present_filter = that.isSetFilter();
         if (this_present_filter || that_present_filter) {
            if (!this_present_filter || !that_present_filter) {
               return false;
            }

            if (!this.filter.equals(that.filter)) {
               return false;
            }
         }

         boolean this_present_partitionOrder = this.isSetPartitionOrder();
         boolean that_present_partitionOrder = that.isSetPartitionOrder();
         if (this_present_partitionOrder || that_present_partitionOrder) {
            if (!this_present_partitionOrder || !that_present_partitionOrder) {
               return false;
            }

            if (!this.partitionOrder.equals(that.partitionOrder)) {
               return false;
            }
         }

         boolean this_present_ascending = this.isSetAscending();
         boolean that_present_ascending = that.isSetAscending();
         if (this_present_ascending || that_present_ascending) {
            if (!this_present_ascending || !that_present_ascending) {
               return false;
            }

            if (this.ascending != that.ascending) {
               return false;
            }
         }

         boolean this_present_maxParts = this.isSetMaxParts();
         boolean that_present_maxParts = that.isSetMaxParts();
         if (this_present_maxParts || that_present_maxParts) {
            if (!this_present_maxParts || !that_present_maxParts) {
               return false;
            }

            if (this.maxParts != that.maxParts) {
               return false;
            }
         }

         boolean this_present_catName = this.isSetCatName();
         boolean that_present_catName = that.isSetCatName();
         if (this_present_catName || that_present_catName) {
            if (!this_present_catName || !that_present_catName) {
               return false;
            }

            if (!this.catName.equals(that.catName)) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + (this.isSetDbName() ? 131071 : 524287);
      if (this.isSetDbName()) {
         hashCode = hashCode * 8191 + this.dbName.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetTblName() ? 131071 : 524287);
      if (this.isSetTblName()) {
         hashCode = hashCode * 8191 + this.tblName.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetPartitionKeys() ? 131071 : 524287);
      if (this.isSetPartitionKeys()) {
         hashCode = hashCode * 8191 + this.partitionKeys.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetApplyDistinct() ? 131071 : 524287);
      if (this.isSetApplyDistinct()) {
         hashCode = hashCode * 8191 + (this.applyDistinct ? 131071 : 524287);
      }

      hashCode = hashCode * 8191 + (this.isSetFilter() ? 131071 : 524287);
      if (this.isSetFilter()) {
         hashCode = hashCode * 8191 + this.filter.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetPartitionOrder() ? 131071 : 524287);
      if (this.isSetPartitionOrder()) {
         hashCode = hashCode * 8191 + this.partitionOrder.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetAscending() ? 131071 : 524287);
      if (this.isSetAscending()) {
         hashCode = hashCode * 8191 + (this.ascending ? 131071 : 524287);
      }

      hashCode = hashCode * 8191 + (this.isSetMaxParts() ? 131071 : 524287);
      if (this.isSetMaxParts()) {
         hashCode = hashCode * 8191 + TBaseHelper.hashCode(this.maxParts);
      }

      hashCode = hashCode * 8191 + (this.isSetCatName() ? 131071 : 524287);
      if (this.isSetCatName()) {
         hashCode = hashCode * 8191 + this.catName.hashCode();
      }

      return hashCode;
   }

   public int compareTo(PartitionValuesRequest other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetDbName(), other.isSetDbName());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetDbName()) {
               lastComparison = TBaseHelper.compareTo(this.dbName, other.dbName);
               if (lastComparison != 0) {
                  return lastComparison;
               }
            }

            lastComparison = Boolean.compare(this.isSetTblName(), other.isSetTblName());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetTblName()) {
                  lastComparison = TBaseHelper.compareTo(this.tblName, other.tblName);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               lastComparison = Boolean.compare(this.isSetPartitionKeys(), other.isSetPartitionKeys());
               if (lastComparison != 0) {
                  return lastComparison;
               } else {
                  if (this.isSetPartitionKeys()) {
                     lastComparison = TBaseHelper.compareTo(this.partitionKeys, other.partitionKeys);
                     if (lastComparison != 0) {
                        return lastComparison;
                     }
                  }

                  lastComparison = Boolean.compare(this.isSetApplyDistinct(), other.isSetApplyDistinct());
                  if (lastComparison != 0) {
                     return lastComparison;
                  } else {
                     if (this.isSetApplyDistinct()) {
                        lastComparison = TBaseHelper.compareTo(this.applyDistinct, other.applyDistinct);
                        if (lastComparison != 0) {
                           return lastComparison;
                        }
                     }

                     lastComparison = Boolean.compare(this.isSetFilter(), other.isSetFilter());
                     if (lastComparison != 0) {
                        return lastComparison;
                     } else {
                        if (this.isSetFilter()) {
                           lastComparison = TBaseHelper.compareTo(this.filter, other.filter);
                           if (lastComparison != 0) {
                              return lastComparison;
                           }
                        }

                        lastComparison = Boolean.compare(this.isSetPartitionOrder(), other.isSetPartitionOrder());
                        if (lastComparison != 0) {
                           return lastComparison;
                        } else {
                           if (this.isSetPartitionOrder()) {
                              lastComparison = TBaseHelper.compareTo(this.partitionOrder, other.partitionOrder);
                              if (lastComparison != 0) {
                                 return lastComparison;
                              }
                           }

                           lastComparison = Boolean.compare(this.isSetAscending(), other.isSetAscending());
                           if (lastComparison != 0) {
                              return lastComparison;
                           } else {
                              if (this.isSetAscending()) {
                                 lastComparison = TBaseHelper.compareTo(this.ascending, other.ascending);
                                 if (lastComparison != 0) {
                                    return lastComparison;
                                 }
                              }

                              lastComparison = Boolean.compare(this.isSetMaxParts(), other.isSetMaxParts());
                              if (lastComparison != 0) {
                                 return lastComparison;
                              } else {
                                 if (this.isSetMaxParts()) {
                                    lastComparison = TBaseHelper.compareTo(this.maxParts, other.maxParts);
                                    if (lastComparison != 0) {
                                       return lastComparison;
                                    }
                                 }

                                 lastComparison = Boolean.compare(this.isSetCatName(), other.isSetCatName());
                                 if (lastComparison != 0) {
                                    return lastComparison;
                                 } else {
                                    if (this.isSetCatName()) {
                                       lastComparison = TBaseHelper.compareTo(this.catName, other.catName);
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

   @Nullable
   public _Fields fieldForId(int fieldId) {
      return PartitionValuesRequest._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("PartitionValuesRequest(");
      boolean first = true;
      sb.append("dbName:");
      if (this.dbName == null) {
         sb.append("null");
      } else {
         sb.append(this.dbName);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("tblName:");
      if (this.tblName == null) {
         sb.append("null");
      } else {
         sb.append(this.tblName);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("partitionKeys:");
      if (this.partitionKeys == null) {
         sb.append("null");
      } else {
         sb.append(this.partitionKeys);
      }

      first = false;
      if (this.isSetApplyDistinct()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("applyDistinct:");
         sb.append(this.applyDistinct);
         first = false;
      }

      if (this.isSetFilter()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("filter:");
         if (this.filter == null) {
            sb.append("null");
         } else {
            sb.append(this.filter);
         }

         first = false;
      }

      if (this.isSetPartitionOrder()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("partitionOrder:");
         if (this.partitionOrder == null) {
            sb.append("null");
         } else {
            sb.append(this.partitionOrder);
         }

         first = false;
      }

      if (this.isSetAscending()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("ascending:");
         sb.append(this.ascending);
         first = false;
      }

      if (this.isSetMaxParts()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("maxParts:");
         sb.append(this.maxParts);
         first = false;
      }

      if (this.isSetCatName()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("catName:");
         if (this.catName == null) {
            sb.append("null");
         } else {
            sb.append(this.catName);
         }

         first = false;
      }

      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (!this.isSetDbName()) {
         throw new TProtocolException("Required field 'dbName' is unset! Struct:" + this.toString());
      } else if (!this.isSetTblName()) {
         throw new TProtocolException("Required field 'tblName' is unset! Struct:" + this.toString());
      } else if (!this.isSetPartitionKeys()) {
         throw new TProtocolException("Required field 'partitionKeys' is unset! Struct:" + this.toString());
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
      optionals = new _Fields[]{PartitionValuesRequest._Fields.APPLY_DISTINCT, PartitionValuesRequest._Fields.FILTER, PartitionValuesRequest._Fields.PARTITION_ORDER, PartitionValuesRequest._Fields.ASCENDING, PartitionValuesRequest._Fields.MAX_PARTS, PartitionValuesRequest._Fields.CAT_NAME};
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(PartitionValuesRequest._Fields.DB_NAME, new FieldMetaData("dbName", (byte)1, new FieldValueMetaData((byte)11)));
      tmpMap.put(PartitionValuesRequest._Fields.TBL_NAME, new FieldMetaData("tblName", (byte)1, new FieldValueMetaData((byte)11)));
      tmpMap.put(PartitionValuesRequest._Fields.PARTITION_KEYS, new FieldMetaData("partitionKeys", (byte)1, new ListMetaData((byte)15, new StructMetaData((byte)12, FieldSchema.class))));
      tmpMap.put(PartitionValuesRequest._Fields.APPLY_DISTINCT, new FieldMetaData("applyDistinct", (byte)2, new FieldValueMetaData((byte)2)));
      tmpMap.put(PartitionValuesRequest._Fields.FILTER, new FieldMetaData("filter", (byte)2, new FieldValueMetaData((byte)11)));
      tmpMap.put(PartitionValuesRequest._Fields.PARTITION_ORDER, new FieldMetaData("partitionOrder", (byte)2, new ListMetaData((byte)15, new StructMetaData((byte)12, FieldSchema.class))));
      tmpMap.put(PartitionValuesRequest._Fields.ASCENDING, new FieldMetaData("ascending", (byte)2, new FieldValueMetaData((byte)2)));
      tmpMap.put(PartitionValuesRequest._Fields.MAX_PARTS, new FieldMetaData("maxParts", (byte)2, new FieldValueMetaData((byte)10)));
      tmpMap.put(PartitionValuesRequest._Fields.CAT_NAME, new FieldMetaData("catName", (byte)2, new FieldValueMetaData((byte)11)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(PartitionValuesRequest.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      DB_NAME((short)1, "dbName"),
      TBL_NAME((short)2, "tblName"),
      PARTITION_KEYS((short)3, "partitionKeys"),
      APPLY_DISTINCT((short)4, "applyDistinct"),
      FILTER((short)5, "filter"),
      PARTITION_ORDER((short)6, "partitionOrder"),
      ASCENDING((short)7, "ascending"),
      MAX_PARTS((short)8, "maxParts"),
      CAT_NAME((short)9, "catName");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return DB_NAME;
            case 2:
               return TBL_NAME;
            case 3:
               return PARTITION_KEYS;
            case 4:
               return APPLY_DISTINCT;
            case 5:
               return FILTER;
            case 6:
               return PARTITION_ORDER;
            case 7:
               return ASCENDING;
            case 8:
               return MAX_PARTS;
            case 9:
               return CAT_NAME;
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

   private static class PartitionValuesRequestStandardSchemeFactory implements SchemeFactory {
      private PartitionValuesRequestStandardSchemeFactory() {
      }

      public PartitionValuesRequestStandardScheme getScheme() {
         return new PartitionValuesRequestStandardScheme();
      }
   }

   private static class PartitionValuesRequestStandardScheme extends StandardScheme {
      private PartitionValuesRequestStandardScheme() {
      }

      public void read(TProtocol iprot, PartitionValuesRequest struct) throws TException {
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
                  if (schemeField.type == 11) {
                     struct.dbName = iprot.readString();
                     struct.setDbNameIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 2:
                  if (schemeField.type == 11) {
                     struct.tblName = iprot.readString();
                     struct.setTblNameIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 3:
                  if (schemeField.type != 15) {
                     TProtocolUtil.skip(iprot, schemeField.type);
                     break;
                  }

                  TList _list452 = iprot.readListBegin();
                  struct.partitionKeys = new ArrayList(_list452.size);

                  for(int _i454 = 0; _i454 < _list452.size; ++_i454) {
                     FieldSchema _elem453 = new FieldSchema();
                     _elem453.read(iprot);
                     struct.partitionKeys.add(_elem453);
                  }

                  iprot.readListEnd();
                  struct.setPartitionKeysIsSet(true);
                  break;
               case 4:
                  if (schemeField.type == 2) {
                     struct.applyDistinct = iprot.readBool();
                     struct.setApplyDistinctIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 5:
                  if (schemeField.type == 11) {
                     struct.filter = iprot.readString();
                     struct.setFilterIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 6:
                  if (schemeField.type != 15) {
                     TProtocolUtil.skip(iprot, schemeField.type);
                     break;
                  }

                  TList _list455 = iprot.readListBegin();
                  struct.partitionOrder = new ArrayList(_list455.size);

                  for(int _i457 = 0; _i457 < _list455.size; ++_i457) {
                     FieldSchema _elem456 = new FieldSchema();
                     _elem456.read(iprot);
                     struct.partitionOrder.add(_elem456);
                  }

                  iprot.readListEnd();
                  struct.setPartitionOrderIsSet(true);
                  break;
               case 7:
                  if (schemeField.type == 2) {
                     struct.ascending = iprot.readBool();
                     struct.setAscendingIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 8:
                  if (schemeField.type == 10) {
                     struct.maxParts = iprot.readI64();
                     struct.setMaxPartsIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 9:
                  if (schemeField.type == 11) {
                     struct.catName = iprot.readString();
                     struct.setCatNameIsSet(true);
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

      public void write(TProtocol oprot, PartitionValuesRequest struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(PartitionValuesRequest.STRUCT_DESC);
         if (struct.dbName != null) {
            oprot.writeFieldBegin(PartitionValuesRequest.DB_NAME_FIELD_DESC);
            oprot.writeString(struct.dbName);
            oprot.writeFieldEnd();
         }

         if (struct.tblName != null) {
            oprot.writeFieldBegin(PartitionValuesRequest.TBL_NAME_FIELD_DESC);
            oprot.writeString(struct.tblName);
            oprot.writeFieldEnd();
         }

         if (struct.partitionKeys != null) {
            oprot.writeFieldBegin(PartitionValuesRequest.PARTITION_KEYS_FIELD_DESC);
            oprot.writeListBegin(new TList((byte)12, struct.partitionKeys.size()));

            for(FieldSchema _iter458 : struct.partitionKeys) {
               _iter458.write(oprot);
            }

            oprot.writeListEnd();
            oprot.writeFieldEnd();
         }

         if (struct.isSetApplyDistinct()) {
            oprot.writeFieldBegin(PartitionValuesRequest.APPLY_DISTINCT_FIELD_DESC);
            oprot.writeBool(struct.applyDistinct);
            oprot.writeFieldEnd();
         }

         if (struct.filter != null && struct.isSetFilter()) {
            oprot.writeFieldBegin(PartitionValuesRequest.FILTER_FIELD_DESC);
            oprot.writeString(struct.filter);
            oprot.writeFieldEnd();
         }

         if (struct.partitionOrder != null && struct.isSetPartitionOrder()) {
            oprot.writeFieldBegin(PartitionValuesRequest.PARTITION_ORDER_FIELD_DESC);
            oprot.writeListBegin(new TList((byte)12, struct.partitionOrder.size()));

            for(FieldSchema _iter459 : struct.partitionOrder) {
               _iter459.write(oprot);
            }

            oprot.writeListEnd();
            oprot.writeFieldEnd();
         }

         if (struct.isSetAscending()) {
            oprot.writeFieldBegin(PartitionValuesRequest.ASCENDING_FIELD_DESC);
            oprot.writeBool(struct.ascending);
            oprot.writeFieldEnd();
         }

         if (struct.isSetMaxParts()) {
            oprot.writeFieldBegin(PartitionValuesRequest.MAX_PARTS_FIELD_DESC);
            oprot.writeI64(struct.maxParts);
            oprot.writeFieldEnd();
         }

         if (struct.catName != null && struct.isSetCatName()) {
            oprot.writeFieldBegin(PartitionValuesRequest.CAT_NAME_FIELD_DESC);
            oprot.writeString(struct.catName);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class PartitionValuesRequestTupleSchemeFactory implements SchemeFactory {
      private PartitionValuesRequestTupleSchemeFactory() {
      }

      public PartitionValuesRequestTupleScheme getScheme() {
         return new PartitionValuesRequestTupleScheme();
      }
   }

   private static class PartitionValuesRequestTupleScheme extends TupleScheme {
      private PartitionValuesRequestTupleScheme() {
      }

      public void write(TProtocol prot, PartitionValuesRequest struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         oprot.writeString(struct.dbName);
         oprot.writeString(struct.tblName);
         oprot.writeI32(struct.partitionKeys.size());

         for(FieldSchema _iter460 : struct.partitionKeys) {
            _iter460.write(oprot);
         }

         BitSet optionals = new BitSet();
         if (struct.isSetApplyDistinct()) {
            optionals.set(0);
         }

         if (struct.isSetFilter()) {
            optionals.set(1);
         }

         if (struct.isSetPartitionOrder()) {
            optionals.set(2);
         }

         if (struct.isSetAscending()) {
            optionals.set(3);
         }

         if (struct.isSetMaxParts()) {
            optionals.set(4);
         }

         if (struct.isSetCatName()) {
            optionals.set(5);
         }

         oprot.writeBitSet(optionals, 6);
         if (struct.isSetApplyDistinct()) {
            oprot.writeBool(struct.applyDistinct);
         }

         if (struct.isSetFilter()) {
            oprot.writeString(struct.filter);
         }

         if (struct.isSetPartitionOrder()) {
            oprot.writeI32(struct.partitionOrder.size());

            for(FieldSchema _iter461 : struct.partitionOrder) {
               _iter461.write(oprot);
            }
         }

         if (struct.isSetAscending()) {
            oprot.writeBool(struct.ascending);
         }

         if (struct.isSetMaxParts()) {
            oprot.writeI64(struct.maxParts);
         }

         if (struct.isSetCatName()) {
            oprot.writeString(struct.catName);
         }

      }

      public void read(TProtocol prot, PartitionValuesRequest struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         struct.dbName = iprot.readString();
         struct.setDbNameIsSet(true);
         struct.tblName = iprot.readString();
         struct.setTblNameIsSet(true);
         TList _list462 = iprot.readListBegin((byte)12);
         struct.partitionKeys = new ArrayList(_list462.size);

         for(int _i464 = 0; _i464 < _list462.size; ++_i464) {
            FieldSchema _elem463 = new FieldSchema();
            _elem463.read(iprot);
            struct.partitionKeys.add(_elem463);
         }

         struct.setPartitionKeysIsSet(true);
         BitSet incoming = iprot.readBitSet(6);
         if (incoming.get(0)) {
            struct.applyDistinct = iprot.readBool();
            struct.setApplyDistinctIsSet(true);
         }

         if (incoming.get(1)) {
            struct.filter = iprot.readString();
            struct.setFilterIsSet(true);
         }

         if (incoming.get(2)) {
            TList _list465 = iprot.readListBegin((byte)12);
            struct.partitionOrder = new ArrayList(_list465.size);

            for(int _i467 = 0; _i467 < _list465.size; ++_i467) {
               FieldSchema _elem466 = new FieldSchema();
               _elem466.read(iprot);
               struct.partitionOrder.add(_elem466);
            }

            struct.setPartitionOrderIsSet(true);
         }

         if (incoming.get(3)) {
            struct.ascending = iprot.readBool();
            struct.setAscendingIsSet(true);
         }

         if (incoming.get(4)) {
            struct.maxParts = iprot.readI64();
            struct.setMaxPartsIsSet(true);
         }

         if (incoming.get(5)) {
            struct.catName = iprot.readString();
            struct.setCatNameIsSet(true);
         }

      }
   }
}
