package org.apache.hadoop.hive.metastore.api;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TBase;
import org.apache.thrift.TBaseHelper;
import org.apache.thrift.TException;
import org.apache.thrift.TFieldIdEnum;
import org.apache.thrift.annotation.Nullable;
import org.apache.thrift.meta_data.FieldMetaData;
import org.apache.thrift.meta_data.FieldValueMetaData;
import org.apache.thrift.meta_data.StructMetaData;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TField;
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

public class DropPartitionsRequest implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("DropPartitionsRequest");
   private static final TField DB_NAME_FIELD_DESC = new TField("dbName", (byte)11, (short)1);
   private static final TField TBL_NAME_FIELD_DESC = new TField("tblName", (byte)11, (short)2);
   private static final TField PARTS_FIELD_DESC = new TField("parts", (byte)12, (short)3);
   private static final TField DELETE_DATA_FIELD_DESC = new TField("deleteData", (byte)2, (short)4);
   private static final TField IF_EXISTS_FIELD_DESC = new TField("ifExists", (byte)2, (short)5);
   private static final TField IGNORE_PROTECTION_FIELD_DESC = new TField("ignoreProtection", (byte)2, (short)6);
   private static final TField ENVIRONMENT_CONTEXT_FIELD_DESC = new TField("environmentContext", (byte)12, (short)7);
   private static final TField NEED_RESULT_FIELD_DESC = new TField("needResult", (byte)2, (short)8);
   private static final TField CAT_NAME_FIELD_DESC = new TField("catName", (byte)11, (short)9);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new DropPartitionsRequestStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new DropPartitionsRequestTupleSchemeFactory();
   @Nullable
   private String dbName;
   @Nullable
   private String tblName;
   @Nullable
   private RequestPartsSpec parts;
   private boolean deleteData;
   private boolean ifExists;
   private boolean ignoreProtection;
   @Nullable
   private EnvironmentContext environmentContext;
   private boolean needResult;
   @Nullable
   private String catName;
   private static final int __DELETEDATA_ISSET_ID = 0;
   private static final int __IFEXISTS_ISSET_ID = 1;
   private static final int __IGNOREPROTECTION_ISSET_ID = 2;
   private static final int __NEEDRESULT_ISSET_ID = 3;
   private byte __isset_bitfield;
   private static final _Fields[] optionals;
   public static final Map metaDataMap;

   public DropPartitionsRequest() {
      this.__isset_bitfield = 0;
      this.ifExists = true;
      this.needResult = true;
   }

   public DropPartitionsRequest(String dbName, String tblName, RequestPartsSpec parts) {
      this();
      this.dbName = dbName;
      this.tblName = tblName;
      this.parts = parts;
   }

   public DropPartitionsRequest(DropPartitionsRequest other) {
      this.__isset_bitfield = 0;
      this.__isset_bitfield = other.__isset_bitfield;
      if (other.isSetDbName()) {
         this.dbName = other.dbName;
      }

      if (other.isSetTblName()) {
         this.tblName = other.tblName;
      }

      if (other.isSetParts()) {
         this.parts = new RequestPartsSpec(other.parts);
      }

      this.deleteData = other.deleteData;
      this.ifExists = other.ifExists;
      this.ignoreProtection = other.ignoreProtection;
      if (other.isSetEnvironmentContext()) {
         this.environmentContext = new EnvironmentContext(other.environmentContext);
      }

      this.needResult = other.needResult;
      if (other.isSetCatName()) {
         this.catName = other.catName;
      }

   }

   public DropPartitionsRequest deepCopy() {
      return new DropPartitionsRequest(this);
   }

   public void clear() {
      this.dbName = null;
      this.tblName = null;
      this.parts = null;
      this.setDeleteDataIsSet(false);
      this.deleteData = false;
      this.ifExists = true;
      this.setIgnoreProtectionIsSet(false);
      this.ignoreProtection = false;
      this.environmentContext = null;
      this.needResult = true;
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

   @Nullable
   public RequestPartsSpec getParts() {
      return this.parts;
   }

   public void setParts(@Nullable RequestPartsSpec parts) {
      this.parts = parts;
   }

   public void unsetParts() {
      this.parts = null;
   }

   public boolean isSetParts() {
      return this.parts != null;
   }

   public void setPartsIsSet(boolean value) {
      if (!value) {
         this.parts = null;
      }

   }

   public boolean isDeleteData() {
      return this.deleteData;
   }

   public void setDeleteData(boolean deleteData) {
      this.deleteData = deleteData;
      this.setDeleteDataIsSet(true);
   }

   public void unsetDeleteData() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 0);
   }

   public boolean isSetDeleteData() {
      return EncodingUtils.testBit(this.__isset_bitfield, 0);
   }

   public void setDeleteDataIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 0, value);
   }

   public boolean isIfExists() {
      return this.ifExists;
   }

   public void setIfExists(boolean ifExists) {
      this.ifExists = ifExists;
      this.setIfExistsIsSet(true);
   }

   public void unsetIfExists() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 1);
   }

   public boolean isSetIfExists() {
      return EncodingUtils.testBit(this.__isset_bitfield, 1);
   }

   public void setIfExistsIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 1, value);
   }

   public boolean isIgnoreProtection() {
      return this.ignoreProtection;
   }

   public void setIgnoreProtection(boolean ignoreProtection) {
      this.ignoreProtection = ignoreProtection;
      this.setIgnoreProtectionIsSet(true);
   }

   public void unsetIgnoreProtection() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 2);
   }

   public boolean isSetIgnoreProtection() {
      return EncodingUtils.testBit(this.__isset_bitfield, 2);
   }

   public void setIgnoreProtectionIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 2, value);
   }

   @Nullable
   public EnvironmentContext getEnvironmentContext() {
      return this.environmentContext;
   }

   public void setEnvironmentContext(@Nullable EnvironmentContext environmentContext) {
      this.environmentContext = environmentContext;
   }

   public void unsetEnvironmentContext() {
      this.environmentContext = null;
   }

   public boolean isSetEnvironmentContext() {
      return this.environmentContext != null;
   }

   public void setEnvironmentContextIsSet(boolean value) {
      if (!value) {
         this.environmentContext = null;
      }

   }

   public boolean isNeedResult() {
      return this.needResult;
   }

   public void setNeedResult(boolean needResult) {
      this.needResult = needResult;
      this.setNeedResultIsSet(true);
   }

   public void unsetNeedResult() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 3);
   }

   public boolean isSetNeedResult() {
      return EncodingUtils.testBit(this.__isset_bitfield, 3);
   }

   public void setNeedResultIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 3, value);
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
         case PARTS:
            if (value == null) {
               this.unsetParts();
            } else {
               this.setParts((RequestPartsSpec)value);
            }
            break;
         case DELETE_DATA:
            if (value == null) {
               this.unsetDeleteData();
            } else {
               this.setDeleteData((Boolean)value);
            }
            break;
         case IF_EXISTS:
            if (value == null) {
               this.unsetIfExists();
            } else {
               this.setIfExists((Boolean)value);
            }
            break;
         case IGNORE_PROTECTION:
            if (value == null) {
               this.unsetIgnoreProtection();
            } else {
               this.setIgnoreProtection((Boolean)value);
            }
            break;
         case ENVIRONMENT_CONTEXT:
            if (value == null) {
               this.unsetEnvironmentContext();
            } else {
               this.setEnvironmentContext((EnvironmentContext)value);
            }
            break;
         case NEED_RESULT:
            if (value == null) {
               this.unsetNeedResult();
            } else {
               this.setNeedResult((Boolean)value);
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
         case PARTS:
            return this.getParts();
         case DELETE_DATA:
            return this.isDeleteData();
         case IF_EXISTS:
            return this.isIfExists();
         case IGNORE_PROTECTION:
            return this.isIgnoreProtection();
         case ENVIRONMENT_CONTEXT:
            return this.getEnvironmentContext();
         case NEED_RESULT:
            return this.isNeedResult();
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
            case PARTS:
               return this.isSetParts();
            case DELETE_DATA:
               return this.isSetDeleteData();
            case IF_EXISTS:
               return this.isSetIfExists();
            case IGNORE_PROTECTION:
               return this.isSetIgnoreProtection();
            case ENVIRONMENT_CONTEXT:
               return this.isSetEnvironmentContext();
            case NEED_RESULT:
               return this.isSetNeedResult();
            case CAT_NAME:
               return this.isSetCatName();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof DropPartitionsRequest ? this.equals((DropPartitionsRequest)that) : false;
   }

   public boolean equals(DropPartitionsRequest that) {
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

         boolean this_present_parts = this.isSetParts();
         boolean that_present_parts = that.isSetParts();
         if (this_present_parts || that_present_parts) {
            if (!this_present_parts || !that_present_parts) {
               return false;
            }

            if (!this.parts.equals(that.parts)) {
               return false;
            }
         }

         boolean this_present_deleteData = this.isSetDeleteData();
         boolean that_present_deleteData = that.isSetDeleteData();
         if (this_present_deleteData || that_present_deleteData) {
            if (!this_present_deleteData || !that_present_deleteData) {
               return false;
            }

            if (this.deleteData != that.deleteData) {
               return false;
            }
         }

         boolean this_present_ifExists = this.isSetIfExists();
         boolean that_present_ifExists = that.isSetIfExists();
         if (this_present_ifExists || that_present_ifExists) {
            if (!this_present_ifExists || !that_present_ifExists) {
               return false;
            }

            if (this.ifExists != that.ifExists) {
               return false;
            }
         }

         boolean this_present_ignoreProtection = this.isSetIgnoreProtection();
         boolean that_present_ignoreProtection = that.isSetIgnoreProtection();
         if (this_present_ignoreProtection || that_present_ignoreProtection) {
            if (!this_present_ignoreProtection || !that_present_ignoreProtection) {
               return false;
            }

            if (this.ignoreProtection != that.ignoreProtection) {
               return false;
            }
         }

         boolean this_present_environmentContext = this.isSetEnvironmentContext();
         boolean that_present_environmentContext = that.isSetEnvironmentContext();
         if (this_present_environmentContext || that_present_environmentContext) {
            if (!this_present_environmentContext || !that_present_environmentContext) {
               return false;
            }

            if (!this.environmentContext.equals(that.environmentContext)) {
               return false;
            }
         }

         boolean this_present_needResult = this.isSetNeedResult();
         boolean that_present_needResult = that.isSetNeedResult();
         if (this_present_needResult || that_present_needResult) {
            if (!this_present_needResult || !that_present_needResult) {
               return false;
            }

            if (this.needResult != that.needResult) {
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

      hashCode = hashCode * 8191 + (this.isSetParts() ? 131071 : 524287);
      if (this.isSetParts()) {
         hashCode = hashCode * 8191 + this.parts.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetDeleteData() ? 131071 : 524287);
      if (this.isSetDeleteData()) {
         hashCode = hashCode * 8191 + (this.deleteData ? 131071 : 524287);
      }

      hashCode = hashCode * 8191 + (this.isSetIfExists() ? 131071 : 524287);
      if (this.isSetIfExists()) {
         hashCode = hashCode * 8191 + (this.ifExists ? 131071 : 524287);
      }

      hashCode = hashCode * 8191 + (this.isSetIgnoreProtection() ? 131071 : 524287);
      if (this.isSetIgnoreProtection()) {
         hashCode = hashCode * 8191 + (this.ignoreProtection ? 131071 : 524287);
      }

      hashCode = hashCode * 8191 + (this.isSetEnvironmentContext() ? 131071 : 524287);
      if (this.isSetEnvironmentContext()) {
         hashCode = hashCode * 8191 + this.environmentContext.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetNeedResult() ? 131071 : 524287);
      if (this.isSetNeedResult()) {
         hashCode = hashCode * 8191 + (this.needResult ? 131071 : 524287);
      }

      hashCode = hashCode * 8191 + (this.isSetCatName() ? 131071 : 524287);
      if (this.isSetCatName()) {
         hashCode = hashCode * 8191 + this.catName.hashCode();
      }

      return hashCode;
   }

   public int compareTo(DropPartitionsRequest other) {
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

               lastComparison = Boolean.compare(this.isSetParts(), other.isSetParts());
               if (lastComparison != 0) {
                  return lastComparison;
               } else {
                  if (this.isSetParts()) {
                     lastComparison = TBaseHelper.compareTo(this.parts, other.parts);
                     if (lastComparison != 0) {
                        return lastComparison;
                     }
                  }

                  lastComparison = Boolean.compare(this.isSetDeleteData(), other.isSetDeleteData());
                  if (lastComparison != 0) {
                     return lastComparison;
                  } else {
                     if (this.isSetDeleteData()) {
                        lastComparison = TBaseHelper.compareTo(this.deleteData, other.deleteData);
                        if (lastComparison != 0) {
                           return lastComparison;
                        }
                     }

                     lastComparison = Boolean.compare(this.isSetIfExists(), other.isSetIfExists());
                     if (lastComparison != 0) {
                        return lastComparison;
                     } else {
                        if (this.isSetIfExists()) {
                           lastComparison = TBaseHelper.compareTo(this.ifExists, other.ifExists);
                           if (lastComparison != 0) {
                              return lastComparison;
                           }
                        }

                        lastComparison = Boolean.compare(this.isSetIgnoreProtection(), other.isSetIgnoreProtection());
                        if (lastComparison != 0) {
                           return lastComparison;
                        } else {
                           if (this.isSetIgnoreProtection()) {
                              lastComparison = TBaseHelper.compareTo(this.ignoreProtection, other.ignoreProtection);
                              if (lastComparison != 0) {
                                 return lastComparison;
                              }
                           }

                           lastComparison = Boolean.compare(this.isSetEnvironmentContext(), other.isSetEnvironmentContext());
                           if (lastComparison != 0) {
                              return lastComparison;
                           } else {
                              if (this.isSetEnvironmentContext()) {
                                 lastComparison = TBaseHelper.compareTo(this.environmentContext, other.environmentContext);
                                 if (lastComparison != 0) {
                                    return lastComparison;
                                 }
                              }

                              lastComparison = Boolean.compare(this.isSetNeedResult(), other.isSetNeedResult());
                              if (lastComparison != 0) {
                                 return lastComparison;
                              } else {
                                 if (this.isSetNeedResult()) {
                                    lastComparison = TBaseHelper.compareTo(this.needResult, other.needResult);
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
      return DropPartitionsRequest._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("DropPartitionsRequest(");
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

      sb.append("parts:");
      if (this.parts == null) {
         sb.append("null");
      } else {
         sb.append(this.parts);
      }

      first = false;
      if (this.isSetDeleteData()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("deleteData:");
         sb.append(this.deleteData);
         first = false;
      }

      if (this.isSetIfExists()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("ifExists:");
         sb.append(this.ifExists);
         first = false;
      }

      if (this.isSetIgnoreProtection()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("ignoreProtection:");
         sb.append(this.ignoreProtection);
         first = false;
      }

      if (this.isSetEnvironmentContext()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("environmentContext:");
         if (this.environmentContext == null) {
            sb.append("null");
         } else {
            sb.append(this.environmentContext);
         }

         first = false;
      }

      if (this.isSetNeedResult()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("needResult:");
         sb.append(this.needResult);
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
      } else if (!this.isSetParts()) {
         throw new TProtocolException("Required field 'parts' is unset! Struct:" + this.toString());
      } else {
         if (this.environmentContext != null) {
            this.environmentContext.validate();
         }

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
      optionals = new _Fields[]{DropPartitionsRequest._Fields.DELETE_DATA, DropPartitionsRequest._Fields.IF_EXISTS, DropPartitionsRequest._Fields.IGNORE_PROTECTION, DropPartitionsRequest._Fields.ENVIRONMENT_CONTEXT, DropPartitionsRequest._Fields.NEED_RESULT, DropPartitionsRequest._Fields.CAT_NAME};
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(DropPartitionsRequest._Fields.DB_NAME, new FieldMetaData("dbName", (byte)1, new FieldValueMetaData((byte)11)));
      tmpMap.put(DropPartitionsRequest._Fields.TBL_NAME, new FieldMetaData("tblName", (byte)1, new FieldValueMetaData((byte)11)));
      tmpMap.put(DropPartitionsRequest._Fields.PARTS, new FieldMetaData("parts", (byte)1, new StructMetaData((byte)12, RequestPartsSpec.class)));
      tmpMap.put(DropPartitionsRequest._Fields.DELETE_DATA, new FieldMetaData("deleteData", (byte)2, new FieldValueMetaData((byte)2)));
      tmpMap.put(DropPartitionsRequest._Fields.IF_EXISTS, new FieldMetaData("ifExists", (byte)2, new FieldValueMetaData((byte)2)));
      tmpMap.put(DropPartitionsRequest._Fields.IGNORE_PROTECTION, new FieldMetaData("ignoreProtection", (byte)2, new FieldValueMetaData((byte)2)));
      tmpMap.put(DropPartitionsRequest._Fields.ENVIRONMENT_CONTEXT, new FieldMetaData("environmentContext", (byte)2, new StructMetaData((byte)12, EnvironmentContext.class)));
      tmpMap.put(DropPartitionsRequest._Fields.NEED_RESULT, new FieldMetaData("needResult", (byte)2, new FieldValueMetaData((byte)2)));
      tmpMap.put(DropPartitionsRequest._Fields.CAT_NAME, new FieldMetaData("catName", (byte)2, new FieldValueMetaData((byte)11)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(DropPartitionsRequest.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      DB_NAME((short)1, "dbName"),
      TBL_NAME((short)2, "tblName"),
      PARTS((short)3, "parts"),
      DELETE_DATA((short)4, "deleteData"),
      IF_EXISTS((short)5, "ifExists"),
      IGNORE_PROTECTION((short)6, "ignoreProtection"),
      ENVIRONMENT_CONTEXT((short)7, "environmentContext"),
      NEED_RESULT((short)8, "needResult"),
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
               return PARTS;
            case 4:
               return DELETE_DATA;
            case 5:
               return IF_EXISTS;
            case 6:
               return IGNORE_PROTECTION;
            case 7:
               return ENVIRONMENT_CONTEXT;
            case 8:
               return NEED_RESULT;
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

   private static class DropPartitionsRequestStandardSchemeFactory implements SchemeFactory {
      private DropPartitionsRequestStandardSchemeFactory() {
      }

      public DropPartitionsRequestStandardScheme getScheme() {
         return new DropPartitionsRequestStandardScheme();
      }
   }

   private static class DropPartitionsRequestStandardScheme extends StandardScheme {
      private DropPartitionsRequestStandardScheme() {
      }

      public void read(TProtocol iprot, DropPartitionsRequest struct) throws TException {
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
                  if (schemeField.type == 12) {
                     struct.parts = new RequestPartsSpec();
                     struct.parts.read(iprot);
                     struct.setPartsIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 4:
                  if (schemeField.type == 2) {
                     struct.deleteData = iprot.readBool();
                     struct.setDeleteDataIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 5:
                  if (schemeField.type == 2) {
                     struct.ifExists = iprot.readBool();
                     struct.setIfExistsIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 6:
                  if (schemeField.type == 2) {
                     struct.ignoreProtection = iprot.readBool();
                     struct.setIgnoreProtectionIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 7:
                  if (schemeField.type == 12) {
                     struct.environmentContext = new EnvironmentContext();
                     struct.environmentContext.read(iprot);
                     struct.setEnvironmentContextIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 8:
                  if (schemeField.type == 2) {
                     struct.needResult = iprot.readBool();
                     struct.setNeedResultIsSet(true);
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

      public void write(TProtocol oprot, DropPartitionsRequest struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(DropPartitionsRequest.STRUCT_DESC);
         if (struct.dbName != null) {
            oprot.writeFieldBegin(DropPartitionsRequest.DB_NAME_FIELD_DESC);
            oprot.writeString(struct.dbName);
            oprot.writeFieldEnd();
         }

         if (struct.tblName != null) {
            oprot.writeFieldBegin(DropPartitionsRequest.TBL_NAME_FIELD_DESC);
            oprot.writeString(struct.tblName);
            oprot.writeFieldEnd();
         }

         if (struct.parts != null) {
            oprot.writeFieldBegin(DropPartitionsRequest.PARTS_FIELD_DESC);
            struct.parts.write(oprot);
            oprot.writeFieldEnd();
         }

         if (struct.isSetDeleteData()) {
            oprot.writeFieldBegin(DropPartitionsRequest.DELETE_DATA_FIELD_DESC);
            oprot.writeBool(struct.deleteData);
            oprot.writeFieldEnd();
         }

         if (struct.isSetIfExists()) {
            oprot.writeFieldBegin(DropPartitionsRequest.IF_EXISTS_FIELD_DESC);
            oprot.writeBool(struct.ifExists);
            oprot.writeFieldEnd();
         }

         if (struct.isSetIgnoreProtection()) {
            oprot.writeFieldBegin(DropPartitionsRequest.IGNORE_PROTECTION_FIELD_DESC);
            oprot.writeBool(struct.ignoreProtection);
            oprot.writeFieldEnd();
         }

         if (struct.environmentContext != null && struct.isSetEnvironmentContext()) {
            oprot.writeFieldBegin(DropPartitionsRequest.ENVIRONMENT_CONTEXT_FIELD_DESC);
            struct.environmentContext.write(oprot);
            oprot.writeFieldEnd();
         }

         if (struct.isSetNeedResult()) {
            oprot.writeFieldBegin(DropPartitionsRequest.NEED_RESULT_FIELD_DESC);
            oprot.writeBool(struct.needResult);
            oprot.writeFieldEnd();
         }

         if (struct.catName != null && struct.isSetCatName()) {
            oprot.writeFieldBegin(DropPartitionsRequest.CAT_NAME_FIELD_DESC);
            oprot.writeString(struct.catName);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class DropPartitionsRequestTupleSchemeFactory implements SchemeFactory {
      private DropPartitionsRequestTupleSchemeFactory() {
      }

      public DropPartitionsRequestTupleScheme getScheme() {
         return new DropPartitionsRequestTupleScheme();
      }
   }

   private static class DropPartitionsRequestTupleScheme extends TupleScheme {
      private DropPartitionsRequestTupleScheme() {
      }

      public void write(TProtocol prot, DropPartitionsRequest struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         oprot.writeString(struct.dbName);
         oprot.writeString(struct.tblName);
         struct.parts.write(oprot);
         BitSet optionals = new BitSet();
         if (struct.isSetDeleteData()) {
            optionals.set(0);
         }

         if (struct.isSetIfExists()) {
            optionals.set(1);
         }

         if (struct.isSetIgnoreProtection()) {
            optionals.set(2);
         }

         if (struct.isSetEnvironmentContext()) {
            optionals.set(3);
         }

         if (struct.isSetNeedResult()) {
            optionals.set(4);
         }

         if (struct.isSetCatName()) {
            optionals.set(5);
         }

         oprot.writeBitSet(optionals, 6);
         if (struct.isSetDeleteData()) {
            oprot.writeBool(struct.deleteData);
         }

         if (struct.isSetIfExists()) {
            oprot.writeBool(struct.ifExists);
         }

         if (struct.isSetIgnoreProtection()) {
            oprot.writeBool(struct.ignoreProtection);
         }

         if (struct.isSetEnvironmentContext()) {
            struct.environmentContext.write(oprot);
         }

         if (struct.isSetNeedResult()) {
            oprot.writeBool(struct.needResult);
         }

         if (struct.isSetCatName()) {
            oprot.writeString(struct.catName);
         }

      }

      public void read(TProtocol prot, DropPartitionsRequest struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         struct.dbName = iprot.readString();
         struct.setDbNameIsSet(true);
         struct.tblName = iprot.readString();
         struct.setTblNameIsSet(true);
         struct.parts = new RequestPartsSpec();
         struct.parts.read(iprot);
         struct.setPartsIsSet(true);
         BitSet incoming = iprot.readBitSet(6);
         if (incoming.get(0)) {
            struct.deleteData = iprot.readBool();
            struct.setDeleteDataIsSet(true);
         }

         if (incoming.get(1)) {
            struct.ifExists = iprot.readBool();
            struct.setIfExistsIsSet(true);
         }

         if (incoming.get(2)) {
            struct.ignoreProtection = iprot.readBool();
            struct.setIgnoreProtectionIsSet(true);
         }

         if (incoming.get(3)) {
            struct.environmentContext = new EnvironmentContext();
            struct.environmentContext.read(iprot);
            struct.setEnvironmentContextIsSet(true);
         }

         if (incoming.get(4)) {
            struct.needResult = iprot.readBool();
            struct.setNeedResultIsSet(true);
         }

         if (incoming.get(5)) {
            struct.catName = iprot.readString();
            struct.setCatNameIsSet(true);
         }

      }
   }
}
