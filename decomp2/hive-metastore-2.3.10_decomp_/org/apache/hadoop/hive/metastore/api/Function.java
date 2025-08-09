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
import org.apache.thrift.meta_data.EnumMetaData;
import org.apache.thrift.meta_data.FieldMetaData;
import org.apache.thrift.meta_data.FieldValueMetaData;
import org.apache.thrift.meta_data.ListMetaData;
import org.apache.thrift.meta_data.StructMetaData;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TField;
import org.apache.thrift.protocol.TList;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolUtil;
import org.apache.thrift.protocol.TStruct;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;
import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.transport.TIOStreamTransport;

public class Function implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("Function");
   private static final TField FUNCTION_NAME_FIELD_DESC = new TField("functionName", (byte)11, (short)1);
   private static final TField DB_NAME_FIELD_DESC = new TField("dbName", (byte)11, (short)2);
   private static final TField CLASS_NAME_FIELD_DESC = new TField("className", (byte)11, (short)3);
   private static final TField OWNER_NAME_FIELD_DESC = new TField("ownerName", (byte)11, (short)4);
   private static final TField OWNER_TYPE_FIELD_DESC = new TField("ownerType", (byte)8, (short)5);
   private static final TField CREATE_TIME_FIELD_DESC = new TField("createTime", (byte)8, (short)6);
   private static final TField FUNCTION_TYPE_FIELD_DESC = new TField("functionType", (byte)8, (short)7);
   private static final TField RESOURCE_URIS_FIELD_DESC = new TField("resourceUris", (byte)15, (short)8);
   private static final TField CAT_NAME_FIELD_DESC = new TField("catName", (byte)11, (short)9);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new FunctionStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new FunctionTupleSchemeFactory();
   @Nullable
   private String functionName;
   @Nullable
   private String dbName;
   @Nullable
   private String className;
   @Nullable
   private String ownerName;
   @Nullable
   private PrincipalType ownerType;
   private int createTime;
   @Nullable
   private FunctionType functionType;
   @Nullable
   private List resourceUris;
   @Nullable
   private String catName;
   private static final int __CREATETIME_ISSET_ID = 0;
   private byte __isset_bitfield;
   private static final _Fields[] optionals;
   public static final Map metaDataMap;

   public Function() {
      this.__isset_bitfield = 0;
   }

   public Function(String functionName, String dbName, String className, String ownerName, PrincipalType ownerType, int createTime, FunctionType functionType, List resourceUris) {
      this();
      this.functionName = functionName;
      this.dbName = dbName;
      this.className = className;
      this.ownerName = ownerName;
      this.ownerType = ownerType;
      this.createTime = createTime;
      this.setCreateTimeIsSet(true);
      this.functionType = functionType;
      this.resourceUris = resourceUris;
   }

   public Function(Function other) {
      this.__isset_bitfield = 0;
      this.__isset_bitfield = other.__isset_bitfield;
      if (other.isSetFunctionName()) {
         this.functionName = other.functionName;
      }

      if (other.isSetDbName()) {
         this.dbName = other.dbName;
      }

      if (other.isSetClassName()) {
         this.className = other.className;
      }

      if (other.isSetOwnerName()) {
         this.ownerName = other.ownerName;
      }

      if (other.isSetOwnerType()) {
         this.ownerType = other.ownerType;
      }

      this.createTime = other.createTime;
      if (other.isSetFunctionType()) {
         this.functionType = other.functionType;
      }

      if (other.isSetResourceUris()) {
         List<ResourceUri> __this__resourceUris = new ArrayList(other.resourceUris.size());

         for(ResourceUri other_element : other.resourceUris) {
            __this__resourceUris.add(new ResourceUri(other_element));
         }

         this.resourceUris = __this__resourceUris;
      }

      if (other.isSetCatName()) {
         this.catName = other.catName;
      }

   }

   public Function deepCopy() {
      return new Function(this);
   }

   public void clear() {
      this.functionName = null;
      this.dbName = null;
      this.className = null;
      this.ownerName = null;
      this.ownerType = null;
      this.setCreateTimeIsSet(false);
      this.createTime = 0;
      this.functionType = null;
      this.resourceUris = null;
      this.catName = null;
   }

   @Nullable
   public String getFunctionName() {
      return this.functionName;
   }

   public void setFunctionName(@Nullable String functionName) {
      this.functionName = functionName;
   }

   public void unsetFunctionName() {
      this.functionName = null;
   }

   public boolean isSetFunctionName() {
      return this.functionName != null;
   }

   public void setFunctionNameIsSet(boolean value) {
      if (!value) {
         this.functionName = null;
      }

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
   public String getClassName() {
      return this.className;
   }

   public void setClassName(@Nullable String className) {
      this.className = className;
   }

   public void unsetClassName() {
      this.className = null;
   }

   public boolean isSetClassName() {
      return this.className != null;
   }

   public void setClassNameIsSet(boolean value) {
      if (!value) {
         this.className = null;
      }

   }

   @Nullable
   public String getOwnerName() {
      return this.ownerName;
   }

   public void setOwnerName(@Nullable String ownerName) {
      this.ownerName = ownerName;
   }

   public void unsetOwnerName() {
      this.ownerName = null;
   }

   public boolean isSetOwnerName() {
      return this.ownerName != null;
   }

   public void setOwnerNameIsSet(boolean value) {
      if (!value) {
         this.ownerName = null;
      }

   }

   @Nullable
   public PrincipalType getOwnerType() {
      return this.ownerType;
   }

   public void setOwnerType(@Nullable PrincipalType ownerType) {
      this.ownerType = ownerType;
   }

   public void unsetOwnerType() {
      this.ownerType = null;
   }

   public boolean isSetOwnerType() {
      return this.ownerType != null;
   }

   public void setOwnerTypeIsSet(boolean value) {
      if (!value) {
         this.ownerType = null;
      }

   }

   public int getCreateTime() {
      return this.createTime;
   }

   public void setCreateTime(int createTime) {
      this.createTime = createTime;
      this.setCreateTimeIsSet(true);
   }

   public void unsetCreateTime() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 0);
   }

   public boolean isSetCreateTime() {
      return EncodingUtils.testBit(this.__isset_bitfield, 0);
   }

   public void setCreateTimeIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 0, value);
   }

   @Nullable
   public FunctionType getFunctionType() {
      return this.functionType;
   }

   public void setFunctionType(@Nullable FunctionType functionType) {
      this.functionType = functionType;
   }

   public void unsetFunctionType() {
      this.functionType = null;
   }

   public boolean isSetFunctionType() {
      return this.functionType != null;
   }

   public void setFunctionTypeIsSet(boolean value) {
      if (!value) {
         this.functionType = null;
      }

   }

   public int getResourceUrisSize() {
      return this.resourceUris == null ? 0 : this.resourceUris.size();
   }

   @Nullable
   public Iterator getResourceUrisIterator() {
      return this.resourceUris == null ? null : this.resourceUris.iterator();
   }

   public void addToResourceUris(ResourceUri elem) {
      if (this.resourceUris == null) {
         this.resourceUris = new ArrayList();
      }

      this.resourceUris.add(elem);
   }

   @Nullable
   public List getResourceUris() {
      return this.resourceUris;
   }

   public void setResourceUris(@Nullable List resourceUris) {
      this.resourceUris = resourceUris;
   }

   public void unsetResourceUris() {
      this.resourceUris = null;
   }

   public boolean isSetResourceUris() {
      return this.resourceUris != null;
   }

   public void setResourceUrisIsSet(boolean value) {
      if (!value) {
         this.resourceUris = null;
      }

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
         case FUNCTION_NAME:
            if (value == null) {
               this.unsetFunctionName();
            } else {
               this.setFunctionName((String)value);
            }
            break;
         case DB_NAME:
            if (value == null) {
               this.unsetDbName();
            } else {
               this.setDbName((String)value);
            }
            break;
         case CLASS_NAME:
            if (value == null) {
               this.unsetClassName();
            } else {
               this.setClassName((String)value);
            }
            break;
         case OWNER_NAME:
            if (value == null) {
               this.unsetOwnerName();
            } else {
               this.setOwnerName((String)value);
            }
            break;
         case OWNER_TYPE:
            if (value == null) {
               this.unsetOwnerType();
            } else {
               this.setOwnerType((PrincipalType)value);
            }
            break;
         case CREATE_TIME:
            if (value == null) {
               this.unsetCreateTime();
            } else {
               this.setCreateTime((Integer)value);
            }
            break;
         case FUNCTION_TYPE:
            if (value == null) {
               this.unsetFunctionType();
            } else {
               this.setFunctionType((FunctionType)value);
            }
            break;
         case RESOURCE_URIS:
            if (value == null) {
               this.unsetResourceUris();
            } else {
               this.setResourceUris((List)value);
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
         case FUNCTION_NAME:
            return this.getFunctionName();
         case DB_NAME:
            return this.getDbName();
         case CLASS_NAME:
            return this.getClassName();
         case OWNER_NAME:
            return this.getOwnerName();
         case OWNER_TYPE:
            return this.getOwnerType();
         case CREATE_TIME:
            return this.getCreateTime();
         case FUNCTION_TYPE:
            return this.getFunctionType();
         case RESOURCE_URIS:
            return this.getResourceUris();
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
            case FUNCTION_NAME:
               return this.isSetFunctionName();
            case DB_NAME:
               return this.isSetDbName();
            case CLASS_NAME:
               return this.isSetClassName();
            case OWNER_NAME:
               return this.isSetOwnerName();
            case OWNER_TYPE:
               return this.isSetOwnerType();
            case CREATE_TIME:
               return this.isSetCreateTime();
            case FUNCTION_TYPE:
               return this.isSetFunctionType();
            case RESOURCE_URIS:
               return this.isSetResourceUris();
            case CAT_NAME:
               return this.isSetCatName();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof Function ? this.equals((Function)that) : false;
   }

   public boolean equals(Function that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_functionName = this.isSetFunctionName();
         boolean that_present_functionName = that.isSetFunctionName();
         if (this_present_functionName || that_present_functionName) {
            if (!this_present_functionName || !that_present_functionName) {
               return false;
            }

            if (!this.functionName.equals(that.functionName)) {
               return false;
            }
         }

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

         boolean this_present_className = this.isSetClassName();
         boolean that_present_className = that.isSetClassName();
         if (this_present_className || that_present_className) {
            if (!this_present_className || !that_present_className) {
               return false;
            }

            if (!this.className.equals(that.className)) {
               return false;
            }
         }

         boolean this_present_ownerName = this.isSetOwnerName();
         boolean that_present_ownerName = that.isSetOwnerName();
         if (this_present_ownerName || that_present_ownerName) {
            if (!this_present_ownerName || !that_present_ownerName) {
               return false;
            }

            if (!this.ownerName.equals(that.ownerName)) {
               return false;
            }
         }

         boolean this_present_ownerType = this.isSetOwnerType();
         boolean that_present_ownerType = that.isSetOwnerType();
         if (this_present_ownerType || that_present_ownerType) {
            if (!this_present_ownerType || !that_present_ownerType) {
               return false;
            }

            if (!this.ownerType.equals(that.ownerType)) {
               return false;
            }
         }

         boolean this_present_createTime = true;
         boolean that_present_createTime = true;
         if (this_present_createTime || that_present_createTime) {
            if (!this_present_createTime || !that_present_createTime) {
               return false;
            }

            if (this.createTime != that.createTime) {
               return false;
            }
         }

         boolean this_present_functionType = this.isSetFunctionType();
         boolean that_present_functionType = that.isSetFunctionType();
         if (this_present_functionType || that_present_functionType) {
            if (!this_present_functionType || !that_present_functionType) {
               return false;
            }

            if (!this.functionType.equals(that.functionType)) {
               return false;
            }
         }

         boolean this_present_resourceUris = this.isSetResourceUris();
         boolean that_present_resourceUris = that.isSetResourceUris();
         if (this_present_resourceUris || that_present_resourceUris) {
            if (!this_present_resourceUris || !that_present_resourceUris) {
               return false;
            }

            if (!this.resourceUris.equals(that.resourceUris)) {
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
      hashCode = hashCode * 8191 + (this.isSetFunctionName() ? 131071 : 524287);
      if (this.isSetFunctionName()) {
         hashCode = hashCode * 8191 + this.functionName.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetDbName() ? 131071 : 524287);
      if (this.isSetDbName()) {
         hashCode = hashCode * 8191 + this.dbName.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetClassName() ? 131071 : 524287);
      if (this.isSetClassName()) {
         hashCode = hashCode * 8191 + this.className.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetOwnerName() ? 131071 : 524287);
      if (this.isSetOwnerName()) {
         hashCode = hashCode * 8191 + this.ownerName.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetOwnerType() ? 131071 : 524287);
      if (this.isSetOwnerType()) {
         hashCode = hashCode * 8191 + this.ownerType.getValue();
      }

      hashCode = hashCode * 8191 + this.createTime;
      hashCode = hashCode * 8191 + (this.isSetFunctionType() ? 131071 : 524287);
      if (this.isSetFunctionType()) {
         hashCode = hashCode * 8191 + this.functionType.getValue();
      }

      hashCode = hashCode * 8191 + (this.isSetResourceUris() ? 131071 : 524287);
      if (this.isSetResourceUris()) {
         hashCode = hashCode * 8191 + this.resourceUris.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetCatName() ? 131071 : 524287);
      if (this.isSetCatName()) {
         hashCode = hashCode * 8191 + this.catName.hashCode();
      }

      return hashCode;
   }

   public int compareTo(Function other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetFunctionName(), other.isSetFunctionName());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetFunctionName()) {
               lastComparison = TBaseHelper.compareTo(this.functionName, other.functionName);
               if (lastComparison != 0) {
                  return lastComparison;
               }
            }

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

               lastComparison = Boolean.compare(this.isSetClassName(), other.isSetClassName());
               if (lastComparison != 0) {
                  return lastComparison;
               } else {
                  if (this.isSetClassName()) {
                     lastComparison = TBaseHelper.compareTo(this.className, other.className);
                     if (lastComparison != 0) {
                        return lastComparison;
                     }
                  }

                  lastComparison = Boolean.compare(this.isSetOwnerName(), other.isSetOwnerName());
                  if (lastComparison != 0) {
                     return lastComparison;
                  } else {
                     if (this.isSetOwnerName()) {
                        lastComparison = TBaseHelper.compareTo(this.ownerName, other.ownerName);
                        if (lastComparison != 0) {
                           return lastComparison;
                        }
                     }

                     lastComparison = Boolean.compare(this.isSetOwnerType(), other.isSetOwnerType());
                     if (lastComparison != 0) {
                        return lastComparison;
                     } else {
                        if (this.isSetOwnerType()) {
                           lastComparison = TBaseHelper.compareTo(this.ownerType, other.ownerType);
                           if (lastComparison != 0) {
                              return lastComparison;
                           }
                        }

                        lastComparison = Boolean.compare(this.isSetCreateTime(), other.isSetCreateTime());
                        if (lastComparison != 0) {
                           return lastComparison;
                        } else {
                           if (this.isSetCreateTime()) {
                              lastComparison = TBaseHelper.compareTo(this.createTime, other.createTime);
                              if (lastComparison != 0) {
                                 return lastComparison;
                              }
                           }

                           lastComparison = Boolean.compare(this.isSetFunctionType(), other.isSetFunctionType());
                           if (lastComparison != 0) {
                              return lastComparison;
                           } else {
                              if (this.isSetFunctionType()) {
                                 lastComparison = TBaseHelper.compareTo(this.functionType, other.functionType);
                                 if (lastComparison != 0) {
                                    return lastComparison;
                                 }
                              }

                              lastComparison = Boolean.compare(this.isSetResourceUris(), other.isSetResourceUris());
                              if (lastComparison != 0) {
                                 return lastComparison;
                              } else {
                                 if (this.isSetResourceUris()) {
                                    lastComparison = TBaseHelper.compareTo(this.resourceUris, other.resourceUris);
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
      return Function._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("Function(");
      boolean first = true;
      sb.append("functionName:");
      if (this.functionName == null) {
         sb.append("null");
      } else {
         sb.append(this.functionName);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

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

      sb.append("className:");
      if (this.className == null) {
         sb.append("null");
      } else {
         sb.append(this.className);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("ownerName:");
      if (this.ownerName == null) {
         sb.append("null");
      } else {
         sb.append(this.ownerName);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("ownerType:");
      if (this.ownerType == null) {
         sb.append("null");
      } else {
         sb.append(this.ownerType);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("createTime:");
      sb.append(this.createTime);
      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("functionType:");
      if (this.functionType == null) {
         sb.append("null");
      } else {
         sb.append(this.functionType);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("resourceUris:");
      if (this.resourceUris == null) {
         sb.append("null");
      } else {
         sb.append(this.resourceUris);
      }

      first = false;
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
      optionals = new _Fields[]{Function._Fields.CAT_NAME};
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(Function._Fields.FUNCTION_NAME, new FieldMetaData("functionName", (byte)3, new FieldValueMetaData((byte)11)));
      tmpMap.put(Function._Fields.DB_NAME, new FieldMetaData("dbName", (byte)3, new FieldValueMetaData((byte)11)));
      tmpMap.put(Function._Fields.CLASS_NAME, new FieldMetaData("className", (byte)3, new FieldValueMetaData((byte)11)));
      tmpMap.put(Function._Fields.OWNER_NAME, new FieldMetaData("ownerName", (byte)3, new FieldValueMetaData((byte)11)));
      tmpMap.put(Function._Fields.OWNER_TYPE, new FieldMetaData("ownerType", (byte)3, new EnumMetaData((byte)16, PrincipalType.class)));
      tmpMap.put(Function._Fields.CREATE_TIME, new FieldMetaData("createTime", (byte)3, new FieldValueMetaData((byte)8)));
      tmpMap.put(Function._Fields.FUNCTION_TYPE, new FieldMetaData("functionType", (byte)3, new EnumMetaData((byte)16, FunctionType.class)));
      tmpMap.put(Function._Fields.RESOURCE_URIS, new FieldMetaData("resourceUris", (byte)3, new ListMetaData((byte)15, new StructMetaData((byte)12, ResourceUri.class))));
      tmpMap.put(Function._Fields.CAT_NAME, new FieldMetaData("catName", (byte)2, new FieldValueMetaData((byte)11)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(Function.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      FUNCTION_NAME((short)1, "functionName"),
      DB_NAME((short)2, "dbName"),
      CLASS_NAME((short)3, "className"),
      OWNER_NAME((short)4, "ownerName"),
      OWNER_TYPE((short)5, "ownerType"),
      CREATE_TIME((short)6, "createTime"),
      FUNCTION_TYPE((short)7, "functionType"),
      RESOURCE_URIS((short)8, "resourceUris"),
      CAT_NAME((short)9, "catName");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return FUNCTION_NAME;
            case 2:
               return DB_NAME;
            case 3:
               return CLASS_NAME;
            case 4:
               return OWNER_NAME;
            case 5:
               return OWNER_TYPE;
            case 6:
               return CREATE_TIME;
            case 7:
               return FUNCTION_TYPE;
            case 8:
               return RESOURCE_URIS;
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

   private static class FunctionStandardSchemeFactory implements SchemeFactory {
      private FunctionStandardSchemeFactory() {
      }

      public FunctionStandardScheme getScheme() {
         return new FunctionStandardScheme();
      }
   }

   private static class FunctionStandardScheme extends StandardScheme {
      private FunctionStandardScheme() {
      }

      public void read(TProtocol iprot, Function struct) throws TException {
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
                     struct.functionName = iprot.readString();
                     struct.setFunctionNameIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 2:
                  if (schemeField.type == 11) {
                     struct.dbName = iprot.readString();
                     struct.setDbNameIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 3:
                  if (schemeField.type == 11) {
                     struct.className = iprot.readString();
                     struct.setClassNameIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 4:
                  if (schemeField.type == 11) {
                     struct.ownerName = iprot.readString();
                     struct.setOwnerNameIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 5:
                  if (schemeField.type == 8) {
                     struct.ownerType = PrincipalType.findByValue(iprot.readI32());
                     struct.setOwnerTypeIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 6:
                  if (schemeField.type == 8) {
                     struct.createTime = iprot.readI32();
                     struct.setCreateTimeIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 7:
                  if (schemeField.type == 8) {
                     struct.functionType = FunctionType.findByValue(iprot.readI32());
                     struct.setFunctionTypeIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 8:
                  if (schemeField.type != 15) {
                     TProtocolUtil.skip(iprot, schemeField.type);
                     break;
                  }

                  TList _list484 = iprot.readListBegin();
                  struct.resourceUris = new ArrayList(_list484.size);

                  for(int _i486 = 0; _i486 < _list484.size; ++_i486) {
                     ResourceUri _elem485 = new ResourceUri();
                     _elem485.read(iprot);
                     struct.resourceUris.add(_elem485);
                  }

                  iprot.readListEnd();
                  struct.setResourceUrisIsSet(true);
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

      public void write(TProtocol oprot, Function struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(Function.STRUCT_DESC);
         if (struct.functionName != null) {
            oprot.writeFieldBegin(Function.FUNCTION_NAME_FIELD_DESC);
            oprot.writeString(struct.functionName);
            oprot.writeFieldEnd();
         }

         if (struct.dbName != null) {
            oprot.writeFieldBegin(Function.DB_NAME_FIELD_DESC);
            oprot.writeString(struct.dbName);
            oprot.writeFieldEnd();
         }

         if (struct.className != null) {
            oprot.writeFieldBegin(Function.CLASS_NAME_FIELD_DESC);
            oprot.writeString(struct.className);
            oprot.writeFieldEnd();
         }

         if (struct.ownerName != null) {
            oprot.writeFieldBegin(Function.OWNER_NAME_FIELD_DESC);
            oprot.writeString(struct.ownerName);
            oprot.writeFieldEnd();
         }

         if (struct.ownerType != null) {
            oprot.writeFieldBegin(Function.OWNER_TYPE_FIELD_DESC);
            oprot.writeI32(struct.ownerType.getValue());
            oprot.writeFieldEnd();
         }

         oprot.writeFieldBegin(Function.CREATE_TIME_FIELD_DESC);
         oprot.writeI32(struct.createTime);
         oprot.writeFieldEnd();
         if (struct.functionType != null) {
            oprot.writeFieldBegin(Function.FUNCTION_TYPE_FIELD_DESC);
            oprot.writeI32(struct.functionType.getValue());
            oprot.writeFieldEnd();
         }

         if (struct.resourceUris != null) {
            oprot.writeFieldBegin(Function.RESOURCE_URIS_FIELD_DESC);
            oprot.writeListBegin(new TList((byte)12, struct.resourceUris.size()));

            for(ResourceUri _iter487 : struct.resourceUris) {
               _iter487.write(oprot);
            }

            oprot.writeListEnd();
            oprot.writeFieldEnd();
         }

         if (struct.catName != null && struct.isSetCatName()) {
            oprot.writeFieldBegin(Function.CAT_NAME_FIELD_DESC);
            oprot.writeString(struct.catName);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class FunctionTupleSchemeFactory implements SchemeFactory {
      private FunctionTupleSchemeFactory() {
      }

      public FunctionTupleScheme getScheme() {
         return new FunctionTupleScheme();
      }
   }

   private static class FunctionTupleScheme extends TupleScheme {
      private FunctionTupleScheme() {
      }

      public void write(TProtocol prot, Function struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         BitSet optionals = new BitSet();
         if (struct.isSetFunctionName()) {
            optionals.set(0);
         }

         if (struct.isSetDbName()) {
            optionals.set(1);
         }

         if (struct.isSetClassName()) {
            optionals.set(2);
         }

         if (struct.isSetOwnerName()) {
            optionals.set(3);
         }

         if (struct.isSetOwnerType()) {
            optionals.set(4);
         }

         if (struct.isSetCreateTime()) {
            optionals.set(5);
         }

         if (struct.isSetFunctionType()) {
            optionals.set(6);
         }

         if (struct.isSetResourceUris()) {
            optionals.set(7);
         }

         if (struct.isSetCatName()) {
            optionals.set(8);
         }

         oprot.writeBitSet(optionals, 9);
         if (struct.isSetFunctionName()) {
            oprot.writeString(struct.functionName);
         }

         if (struct.isSetDbName()) {
            oprot.writeString(struct.dbName);
         }

         if (struct.isSetClassName()) {
            oprot.writeString(struct.className);
         }

         if (struct.isSetOwnerName()) {
            oprot.writeString(struct.ownerName);
         }

         if (struct.isSetOwnerType()) {
            oprot.writeI32(struct.ownerType.getValue());
         }

         if (struct.isSetCreateTime()) {
            oprot.writeI32(struct.createTime);
         }

         if (struct.isSetFunctionType()) {
            oprot.writeI32(struct.functionType.getValue());
         }

         if (struct.isSetResourceUris()) {
            oprot.writeI32(struct.resourceUris.size());

            for(ResourceUri _iter488 : struct.resourceUris) {
               _iter488.write(oprot);
            }
         }

         if (struct.isSetCatName()) {
            oprot.writeString(struct.catName);
         }

      }

      public void read(TProtocol prot, Function struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         BitSet incoming = iprot.readBitSet(9);
         if (incoming.get(0)) {
            struct.functionName = iprot.readString();
            struct.setFunctionNameIsSet(true);
         }

         if (incoming.get(1)) {
            struct.dbName = iprot.readString();
            struct.setDbNameIsSet(true);
         }

         if (incoming.get(2)) {
            struct.className = iprot.readString();
            struct.setClassNameIsSet(true);
         }

         if (incoming.get(3)) {
            struct.ownerName = iprot.readString();
            struct.setOwnerNameIsSet(true);
         }

         if (incoming.get(4)) {
            struct.ownerType = PrincipalType.findByValue(iprot.readI32());
            struct.setOwnerTypeIsSet(true);
         }

         if (incoming.get(5)) {
            struct.createTime = iprot.readI32();
            struct.setCreateTimeIsSet(true);
         }

         if (incoming.get(6)) {
            struct.functionType = FunctionType.findByValue(iprot.readI32());
            struct.setFunctionTypeIsSet(true);
         }

         if (incoming.get(7)) {
            TList _list489 = iprot.readListBegin((byte)12);
            struct.resourceUris = new ArrayList(_list489.size);

            for(int _i491 = 0; _i491 < _list489.size; ++_i491) {
               ResourceUri _elem490 = new ResourceUri();
               _elem490.read(iprot);
               struct.resourceUris.add(_elem490);
            }

            struct.setResourceUrisIsSet(true);
         }

         if (incoming.get(8)) {
            struct.catName = iprot.readString();
            struct.setCatNameIsSet(true);
         }

      }
   }
}
