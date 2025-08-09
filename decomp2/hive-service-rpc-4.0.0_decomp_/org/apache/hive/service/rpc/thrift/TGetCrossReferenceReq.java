package org.apache.hive.service.rpc.thrift;

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
import org.apache.hadoop.hive.common.classification.InterfaceAudience.Public;
import org.apache.hadoop.hive.common.classification.InterfaceStability.Stable;
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

@Public
@Stable
public class TGetCrossReferenceReq implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("TGetCrossReferenceReq");
   private static final TField SESSION_HANDLE_FIELD_DESC = new TField("sessionHandle", (byte)12, (short)1);
   private static final TField PARENT_CATALOG_NAME_FIELD_DESC = new TField("parentCatalogName", (byte)11, (short)2);
   private static final TField PARENT_SCHEMA_NAME_FIELD_DESC = new TField("parentSchemaName", (byte)11, (short)3);
   private static final TField PARENT_TABLE_NAME_FIELD_DESC = new TField("parentTableName", (byte)11, (short)4);
   private static final TField FOREIGN_CATALOG_NAME_FIELD_DESC = new TField("foreignCatalogName", (byte)11, (short)5);
   private static final TField FOREIGN_SCHEMA_NAME_FIELD_DESC = new TField("foreignSchemaName", (byte)11, (short)6);
   private static final TField FOREIGN_TABLE_NAME_FIELD_DESC = new TField("foreignTableName", (byte)11, (short)7);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new TGetCrossReferenceReqStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new TGetCrossReferenceReqTupleSchemeFactory();
   @Nullable
   private TSessionHandle sessionHandle;
   @Nullable
   private String parentCatalogName;
   @Nullable
   private String parentSchemaName;
   @Nullable
   private String parentTableName;
   @Nullable
   private String foreignCatalogName;
   @Nullable
   private String foreignSchemaName;
   @Nullable
   private String foreignTableName;
   private static final _Fields[] optionals;
   public static final Map metaDataMap;

   public TGetCrossReferenceReq() {
   }

   public TGetCrossReferenceReq(TSessionHandle sessionHandle) {
      this();
      this.sessionHandle = sessionHandle;
   }

   public TGetCrossReferenceReq(TGetCrossReferenceReq other) {
      if (other.isSetSessionHandle()) {
         this.sessionHandle = new TSessionHandle(other.sessionHandle);
      }

      if (other.isSetParentCatalogName()) {
         this.parentCatalogName = other.parentCatalogName;
      }

      if (other.isSetParentSchemaName()) {
         this.parentSchemaName = other.parentSchemaName;
      }

      if (other.isSetParentTableName()) {
         this.parentTableName = other.parentTableName;
      }

      if (other.isSetForeignCatalogName()) {
         this.foreignCatalogName = other.foreignCatalogName;
      }

      if (other.isSetForeignSchemaName()) {
         this.foreignSchemaName = other.foreignSchemaName;
      }

      if (other.isSetForeignTableName()) {
         this.foreignTableName = other.foreignTableName;
      }

   }

   public TGetCrossReferenceReq deepCopy() {
      return new TGetCrossReferenceReq(this);
   }

   public void clear() {
      this.sessionHandle = null;
      this.parentCatalogName = null;
      this.parentSchemaName = null;
      this.parentTableName = null;
      this.foreignCatalogName = null;
      this.foreignSchemaName = null;
      this.foreignTableName = null;
   }

   @Nullable
   public TSessionHandle getSessionHandle() {
      return this.sessionHandle;
   }

   public void setSessionHandle(@Nullable TSessionHandle sessionHandle) {
      this.sessionHandle = sessionHandle;
   }

   public void unsetSessionHandle() {
      this.sessionHandle = null;
   }

   public boolean isSetSessionHandle() {
      return this.sessionHandle != null;
   }

   public void setSessionHandleIsSet(boolean value) {
      if (!value) {
         this.sessionHandle = null;
      }

   }

   @Nullable
   public String getParentCatalogName() {
      return this.parentCatalogName;
   }

   public void setParentCatalogName(@Nullable String parentCatalogName) {
      this.parentCatalogName = parentCatalogName;
   }

   public void unsetParentCatalogName() {
      this.parentCatalogName = null;
   }

   public boolean isSetParentCatalogName() {
      return this.parentCatalogName != null;
   }

   public void setParentCatalogNameIsSet(boolean value) {
      if (!value) {
         this.parentCatalogName = null;
      }

   }

   @Nullable
   public String getParentSchemaName() {
      return this.parentSchemaName;
   }

   public void setParentSchemaName(@Nullable String parentSchemaName) {
      this.parentSchemaName = parentSchemaName;
   }

   public void unsetParentSchemaName() {
      this.parentSchemaName = null;
   }

   public boolean isSetParentSchemaName() {
      return this.parentSchemaName != null;
   }

   public void setParentSchemaNameIsSet(boolean value) {
      if (!value) {
         this.parentSchemaName = null;
      }

   }

   @Nullable
   public String getParentTableName() {
      return this.parentTableName;
   }

   public void setParentTableName(@Nullable String parentTableName) {
      this.parentTableName = parentTableName;
   }

   public void unsetParentTableName() {
      this.parentTableName = null;
   }

   public boolean isSetParentTableName() {
      return this.parentTableName != null;
   }

   public void setParentTableNameIsSet(boolean value) {
      if (!value) {
         this.parentTableName = null;
      }

   }

   @Nullable
   public String getForeignCatalogName() {
      return this.foreignCatalogName;
   }

   public void setForeignCatalogName(@Nullable String foreignCatalogName) {
      this.foreignCatalogName = foreignCatalogName;
   }

   public void unsetForeignCatalogName() {
      this.foreignCatalogName = null;
   }

   public boolean isSetForeignCatalogName() {
      return this.foreignCatalogName != null;
   }

   public void setForeignCatalogNameIsSet(boolean value) {
      if (!value) {
         this.foreignCatalogName = null;
      }

   }

   @Nullable
   public String getForeignSchemaName() {
      return this.foreignSchemaName;
   }

   public void setForeignSchemaName(@Nullable String foreignSchemaName) {
      this.foreignSchemaName = foreignSchemaName;
   }

   public void unsetForeignSchemaName() {
      this.foreignSchemaName = null;
   }

   public boolean isSetForeignSchemaName() {
      return this.foreignSchemaName != null;
   }

   public void setForeignSchemaNameIsSet(boolean value) {
      if (!value) {
         this.foreignSchemaName = null;
      }

   }

   @Nullable
   public String getForeignTableName() {
      return this.foreignTableName;
   }

   public void setForeignTableName(@Nullable String foreignTableName) {
      this.foreignTableName = foreignTableName;
   }

   public void unsetForeignTableName() {
      this.foreignTableName = null;
   }

   public boolean isSetForeignTableName() {
      return this.foreignTableName != null;
   }

   public void setForeignTableNameIsSet(boolean value) {
      if (!value) {
         this.foreignTableName = null;
      }

   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case SESSION_HANDLE:
            if (value == null) {
               this.unsetSessionHandle();
            } else {
               this.setSessionHandle((TSessionHandle)value);
            }
            break;
         case PARENT_CATALOG_NAME:
            if (value == null) {
               this.unsetParentCatalogName();
            } else {
               this.setParentCatalogName((String)value);
            }
            break;
         case PARENT_SCHEMA_NAME:
            if (value == null) {
               this.unsetParentSchemaName();
            } else {
               this.setParentSchemaName((String)value);
            }
            break;
         case PARENT_TABLE_NAME:
            if (value == null) {
               this.unsetParentTableName();
            } else {
               this.setParentTableName((String)value);
            }
            break;
         case FOREIGN_CATALOG_NAME:
            if (value == null) {
               this.unsetForeignCatalogName();
            } else {
               this.setForeignCatalogName((String)value);
            }
            break;
         case FOREIGN_SCHEMA_NAME:
            if (value == null) {
               this.unsetForeignSchemaName();
            } else {
               this.setForeignSchemaName((String)value);
            }
            break;
         case FOREIGN_TABLE_NAME:
            if (value == null) {
               this.unsetForeignTableName();
            } else {
               this.setForeignTableName((String)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case SESSION_HANDLE:
            return this.getSessionHandle();
         case PARENT_CATALOG_NAME:
            return this.getParentCatalogName();
         case PARENT_SCHEMA_NAME:
            return this.getParentSchemaName();
         case PARENT_TABLE_NAME:
            return this.getParentTableName();
         case FOREIGN_CATALOG_NAME:
            return this.getForeignCatalogName();
         case FOREIGN_SCHEMA_NAME:
            return this.getForeignSchemaName();
         case FOREIGN_TABLE_NAME:
            return this.getForeignTableName();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case SESSION_HANDLE:
               return this.isSetSessionHandle();
            case PARENT_CATALOG_NAME:
               return this.isSetParentCatalogName();
            case PARENT_SCHEMA_NAME:
               return this.isSetParentSchemaName();
            case PARENT_TABLE_NAME:
               return this.isSetParentTableName();
            case FOREIGN_CATALOG_NAME:
               return this.isSetForeignCatalogName();
            case FOREIGN_SCHEMA_NAME:
               return this.isSetForeignSchemaName();
            case FOREIGN_TABLE_NAME:
               return this.isSetForeignTableName();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof TGetCrossReferenceReq ? this.equals((TGetCrossReferenceReq)that) : false;
   }

   public boolean equals(TGetCrossReferenceReq that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_sessionHandle = this.isSetSessionHandle();
         boolean that_present_sessionHandle = that.isSetSessionHandle();
         if (this_present_sessionHandle || that_present_sessionHandle) {
            if (!this_present_sessionHandle || !that_present_sessionHandle) {
               return false;
            }

            if (!this.sessionHandle.equals(that.sessionHandle)) {
               return false;
            }
         }

         boolean this_present_parentCatalogName = this.isSetParentCatalogName();
         boolean that_present_parentCatalogName = that.isSetParentCatalogName();
         if (this_present_parentCatalogName || that_present_parentCatalogName) {
            if (!this_present_parentCatalogName || !that_present_parentCatalogName) {
               return false;
            }

            if (!this.parentCatalogName.equals(that.parentCatalogName)) {
               return false;
            }
         }

         boolean this_present_parentSchemaName = this.isSetParentSchemaName();
         boolean that_present_parentSchemaName = that.isSetParentSchemaName();
         if (this_present_parentSchemaName || that_present_parentSchemaName) {
            if (!this_present_parentSchemaName || !that_present_parentSchemaName) {
               return false;
            }

            if (!this.parentSchemaName.equals(that.parentSchemaName)) {
               return false;
            }
         }

         boolean this_present_parentTableName = this.isSetParentTableName();
         boolean that_present_parentTableName = that.isSetParentTableName();
         if (this_present_parentTableName || that_present_parentTableName) {
            if (!this_present_parentTableName || !that_present_parentTableName) {
               return false;
            }

            if (!this.parentTableName.equals(that.parentTableName)) {
               return false;
            }
         }

         boolean this_present_foreignCatalogName = this.isSetForeignCatalogName();
         boolean that_present_foreignCatalogName = that.isSetForeignCatalogName();
         if (this_present_foreignCatalogName || that_present_foreignCatalogName) {
            if (!this_present_foreignCatalogName || !that_present_foreignCatalogName) {
               return false;
            }

            if (!this.foreignCatalogName.equals(that.foreignCatalogName)) {
               return false;
            }
         }

         boolean this_present_foreignSchemaName = this.isSetForeignSchemaName();
         boolean that_present_foreignSchemaName = that.isSetForeignSchemaName();
         if (this_present_foreignSchemaName || that_present_foreignSchemaName) {
            if (!this_present_foreignSchemaName || !that_present_foreignSchemaName) {
               return false;
            }

            if (!this.foreignSchemaName.equals(that.foreignSchemaName)) {
               return false;
            }
         }

         boolean this_present_foreignTableName = this.isSetForeignTableName();
         boolean that_present_foreignTableName = that.isSetForeignTableName();
         if (this_present_foreignTableName || that_present_foreignTableName) {
            if (!this_present_foreignTableName || !that_present_foreignTableName) {
               return false;
            }

            if (!this.foreignTableName.equals(that.foreignTableName)) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + (this.isSetSessionHandle() ? 131071 : 524287);
      if (this.isSetSessionHandle()) {
         hashCode = hashCode * 8191 + this.sessionHandle.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetParentCatalogName() ? 131071 : 524287);
      if (this.isSetParentCatalogName()) {
         hashCode = hashCode * 8191 + this.parentCatalogName.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetParentSchemaName() ? 131071 : 524287);
      if (this.isSetParentSchemaName()) {
         hashCode = hashCode * 8191 + this.parentSchemaName.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetParentTableName() ? 131071 : 524287);
      if (this.isSetParentTableName()) {
         hashCode = hashCode * 8191 + this.parentTableName.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetForeignCatalogName() ? 131071 : 524287);
      if (this.isSetForeignCatalogName()) {
         hashCode = hashCode * 8191 + this.foreignCatalogName.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetForeignSchemaName() ? 131071 : 524287);
      if (this.isSetForeignSchemaName()) {
         hashCode = hashCode * 8191 + this.foreignSchemaName.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetForeignTableName() ? 131071 : 524287);
      if (this.isSetForeignTableName()) {
         hashCode = hashCode * 8191 + this.foreignTableName.hashCode();
      }

      return hashCode;
   }

   public int compareTo(TGetCrossReferenceReq other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetSessionHandle(), other.isSetSessionHandle());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetSessionHandle()) {
               lastComparison = TBaseHelper.compareTo(this.sessionHandle, other.sessionHandle);
               if (lastComparison != 0) {
                  return lastComparison;
               }
            }

            lastComparison = Boolean.compare(this.isSetParentCatalogName(), other.isSetParentCatalogName());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetParentCatalogName()) {
                  lastComparison = TBaseHelper.compareTo(this.parentCatalogName, other.parentCatalogName);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               lastComparison = Boolean.compare(this.isSetParentSchemaName(), other.isSetParentSchemaName());
               if (lastComparison != 0) {
                  return lastComparison;
               } else {
                  if (this.isSetParentSchemaName()) {
                     lastComparison = TBaseHelper.compareTo(this.parentSchemaName, other.parentSchemaName);
                     if (lastComparison != 0) {
                        return lastComparison;
                     }
                  }

                  lastComparison = Boolean.compare(this.isSetParentTableName(), other.isSetParentTableName());
                  if (lastComparison != 0) {
                     return lastComparison;
                  } else {
                     if (this.isSetParentTableName()) {
                        lastComparison = TBaseHelper.compareTo(this.parentTableName, other.parentTableName);
                        if (lastComparison != 0) {
                           return lastComparison;
                        }
                     }

                     lastComparison = Boolean.compare(this.isSetForeignCatalogName(), other.isSetForeignCatalogName());
                     if (lastComparison != 0) {
                        return lastComparison;
                     } else {
                        if (this.isSetForeignCatalogName()) {
                           lastComparison = TBaseHelper.compareTo(this.foreignCatalogName, other.foreignCatalogName);
                           if (lastComparison != 0) {
                              return lastComparison;
                           }
                        }

                        lastComparison = Boolean.compare(this.isSetForeignSchemaName(), other.isSetForeignSchemaName());
                        if (lastComparison != 0) {
                           return lastComparison;
                        } else {
                           if (this.isSetForeignSchemaName()) {
                              lastComparison = TBaseHelper.compareTo(this.foreignSchemaName, other.foreignSchemaName);
                              if (lastComparison != 0) {
                                 return lastComparison;
                              }
                           }

                           lastComparison = Boolean.compare(this.isSetForeignTableName(), other.isSetForeignTableName());
                           if (lastComparison != 0) {
                              return lastComparison;
                           } else {
                              if (this.isSetForeignTableName()) {
                                 lastComparison = TBaseHelper.compareTo(this.foreignTableName, other.foreignTableName);
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

   @Nullable
   public _Fields fieldForId(int fieldId) {
      return TGetCrossReferenceReq._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("TGetCrossReferenceReq(");
      boolean first = true;
      sb.append("sessionHandle:");
      if (this.sessionHandle == null) {
         sb.append("null");
      } else {
         sb.append(this.sessionHandle);
      }

      first = false;
      if (this.isSetParentCatalogName()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("parentCatalogName:");
         if (this.parentCatalogName == null) {
            sb.append("null");
         } else {
            sb.append(this.parentCatalogName);
         }

         first = false;
      }

      if (this.isSetParentSchemaName()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("parentSchemaName:");
         if (this.parentSchemaName == null) {
            sb.append("null");
         } else {
            sb.append(this.parentSchemaName);
         }

         first = false;
      }

      if (this.isSetParentTableName()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("parentTableName:");
         if (this.parentTableName == null) {
            sb.append("null");
         } else {
            sb.append(this.parentTableName);
         }

         first = false;
      }

      if (this.isSetForeignCatalogName()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("foreignCatalogName:");
         if (this.foreignCatalogName == null) {
            sb.append("null");
         } else {
            sb.append(this.foreignCatalogName);
         }

         first = false;
      }

      if (this.isSetForeignSchemaName()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("foreignSchemaName:");
         if (this.foreignSchemaName == null) {
            sb.append("null");
         } else {
            sb.append(this.foreignSchemaName);
         }

         first = false;
      }

      if (this.isSetForeignTableName()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("foreignTableName:");
         if (this.foreignTableName == null) {
            sb.append("null");
         } else {
            sb.append(this.foreignTableName);
         }

         first = false;
      }

      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (!this.isSetSessionHandle()) {
         throw new TProtocolException("Required field 'sessionHandle' is unset! Struct:" + this.toString());
      } else {
         if (this.sessionHandle != null) {
            this.sessionHandle.validate();
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
         this.read(new TCompactProtocol(new TIOStreamTransport(in)));
      } catch (TException te) {
         throw new IOException(te);
      }
   }

   private static IScheme scheme(TProtocol proto) {
      return (StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
   }

   static {
      optionals = new _Fields[]{TGetCrossReferenceReq._Fields.PARENT_CATALOG_NAME, TGetCrossReferenceReq._Fields.PARENT_SCHEMA_NAME, TGetCrossReferenceReq._Fields.PARENT_TABLE_NAME, TGetCrossReferenceReq._Fields.FOREIGN_CATALOG_NAME, TGetCrossReferenceReq._Fields.FOREIGN_SCHEMA_NAME, TGetCrossReferenceReq._Fields.FOREIGN_TABLE_NAME};
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(TGetCrossReferenceReq._Fields.SESSION_HANDLE, new FieldMetaData("sessionHandle", (byte)1, new StructMetaData((byte)12, TSessionHandle.class)));
      tmpMap.put(TGetCrossReferenceReq._Fields.PARENT_CATALOG_NAME, new FieldMetaData("parentCatalogName", (byte)2, new FieldValueMetaData((byte)11, "TIdentifier")));
      tmpMap.put(TGetCrossReferenceReq._Fields.PARENT_SCHEMA_NAME, new FieldMetaData("parentSchemaName", (byte)2, new FieldValueMetaData((byte)11, "TIdentifier")));
      tmpMap.put(TGetCrossReferenceReq._Fields.PARENT_TABLE_NAME, new FieldMetaData("parentTableName", (byte)2, new FieldValueMetaData((byte)11, "TIdentifier")));
      tmpMap.put(TGetCrossReferenceReq._Fields.FOREIGN_CATALOG_NAME, new FieldMetaData("foreignCatalogName", (byte)2, new FieldValueMetaData((byte)11, "TIdentifier")));
      tmpMap.put(TGetCrossReferenceReq._Fields.FOREIGN_SCHEMA_NAME, new FieldMetaData("foreignSchemaName", (byte)2, new FieldValueMetaData((byte)11, "TIdentifier")));
      tmpMap.put(TGetCrossReferenceReq._Fields.FOREIGN_TABLE_NAME, new FieldMetaData("foreignTableName", (byte)2, new FieldValueMetaData((byte)11, "TIdentifier")));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(TGetCrossReferenceReq.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      SESSION_HANDLE((short)1, "sessionHandle"),
      PARENT_CATALOG_NAME((short)2, "parentCatalogName"),
      PARENT_SCHEMA_NAME((short)3, "parentSchemaName"),
      PARENT_TABLE_NAME((short)4, "parentTableName"),
      FOREIGN_CATALOG_NAME((short)5, "foreignCatalogName"),
      FOREIGN_SCHEMA_NAME((short)6, "foreignSchemaName"),
      FOREIGN_TABLE_NAME((short)7, "foreignTableName");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return SESSION_HANDLE;
            case 2:
               return PARENT_CATALOG_NAME;
            case 3:
               return PARENT_SCHEMA_NAME;
            case 4:
               return PARENT_TABLE_NAME;
            case 5:
               return FOREIGN_CATALOG_NAME;
            case 6:
               return FOREIGN_SCHEMA_NAME;
            case 7:
               return FOREIGN_TABLE_NAME;
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

   private static class TGetCrossReferenceReqStandardSchemeFactory implements SchemeFactory {
      private TGetCrossReferenceReqStandardSchemeFactory() {
      }

      public TGetCrossReferenceReqStandardScheme getScheme() {
         return new TGetCrossReferenceReqStandardScheme();
      }
   }

   private static class TGetCrossReferenceReqStandardScheme extends StandardScheme {
      private TGetCrossReferenceReqStandardScheme() {
      }

      public void read(TProtocol iprot, TGetCrossReferenceReq struct) throws TException {
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
                  if (schemeField.type == 12) {
                     struct.sessionHandle = new TSessionHandle();
                     struct.sessionHandle.read(iprot);
                     struct.setSessionHandleIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 2:
                  if (schemeField.type == 11) {
                     struct.parentCatalogName = iprot.readString();
                     struct.setParentCatalogNameIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 3:
                  if (schemeField.type == 11) {
                     struct.parentSchemaName = iprot.readString();
                     struct.setParentSchemaNameIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 4:
                  if (schemeField.type == 11) {
                     struct.parentTableName = iprot.readString();
                     struct.setParentTableNameIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 5:
                  if (schemeField.type == 11) {
                     struct.foreignCatalogName = iprot.readString();
                     struct.setForeignCatalogNameIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 6:
                  if (schemeField.type == 11) {
                     struct.foreignSchemaName = iprot.readString();
                     struct.setForeignSchemaNameIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 7:
                  if (schemeField.type == 11) {
                     struct.foreignTableName = iprot.readString();
                     struct.setForeignTableNameIsSet(true);
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

      public void write(TProtocol oprot, TGetCrossReferenceReq struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(TGetCrossReferenceReq.STRUCT_DESC);
         if (struct.sessionHandle != null) {
            oprot.writeFieldBegin(TGetCrossReferenceReq.SESSION_HANDLE_FIELD_DESC);
            struct.sessionHandle.write(oprot);
            oprot.writeFieldEnd();
         }

         if (struct.parentCatalogName != null && struct.isSetParentCatalogName()) {
            oprot.writeFieldBegin(TGetCrossReferenceReq.PARENT_CATALOG_NAME_FIELD_DESC);
            oprot.writeString(struct.parentCatalogName);
            oprot.writeFieldEnd();
         }

         if (struct.parentSchemaName != null && struct.isSetParentSchemaName()) {
            oprot.writeFieldBegin(TGetCrossReferenceReq.PARENT_SCHEMA_NAME_FIELD_DESC);
            oprot.writeString(struct.parentSchemaName);
            oprot.writeFieldEnd();
         }

         if (struct.parentTableName != null && struct.isSetParentTableName()) {
            oprot.writeFieldBegin(TGetCrossReferenceReq.PARENT_TABLE_NAME_FIELD_DESC);
            oprot.writeString(struct.parentTableName);
            oprot.writeFieldEnd();
         }

         if (struct.foreignCatalogName != null && struct.isSetForeignCatalogName()) {
            oprot.writeFieldBegin(TGetCrossReferenceReq.FOREIGN_CATALOG_NAME_FIELD_DESC);
            oprot.writeString(struct.foreignCatalogName);
            oprot.writeFieldEnd();
         }

         if (struct.foreignSchemaName != null && struct.isSetForeignSchemaName()) {
            oprot.writeFieldBegin(TGetCrossReferenceReq.FOREIGN_SCHEMA_NAME_FIELD_DESC);
            oprot.writeString(struct.foreignSchemaName);
            oprot.writeFieldEnd();
         }

         if (struct.foreignTableName != null && struct.isSetForeignTableName()) {
            oprot.writeFieldBegin(TGetCrossReferenceReq.FOREIGN_TABLE_NAME_FIELD_DESC);
            oprot.writeString(struct.foreignTableName);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class TGetCrossReferenceReqTupleSchemeFactory implements SchemeFactory {
      private TGetCrossReferenceReqTupleSchemeFactory() {
      }

      public TGetCrossReferenceReqTupleScheme getScheme() {
         return new TGetCrossReferenceReqTupleScheme();
      }
   }

   private static class TGetCrossReferenceReqTupleScheme extends TupleScheme {
      private TGetCrossReferenceReqTupleScheme() {
      }

      public void write(TProtocol prot, TGetCrossReferenceReq struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         struct.sessionHandle.write(oprot);
         BitSet optionals = new BitSet();
         if (struct.isSetParentCatalogName()) {
            optionals.set(0);
         }

         if (struct.isSetParentSchemaName()) {
            optionals.set(1);
         }

         if (struct.isSetParentTableName()) {
            optionals.set(2);
         }

         if (struct.isSetForeignCatalogName()) {
            optionals.set(3);
         }

         if (struct.isSetForeignSchemaName()) {
            optionals.set(4);
         }

         if (struct.isSetForeignTableName()) {
            optionals.set(5);
         }

         oprot.writeBitSet(optionals, 6);
         if (struct.isSetParentCatalogName()) {
            oprot.writeString(struct.parentCatalogName);
         }

         if (struct.isSetParentSchemaName()) {
            oprot.writeString(struct.parentSchemaName);
         }

         if (struct.isSetParentTableName()) {
            oprot.writeString(struct.parentTableName);
         }

         if (struct.isSetForeignCatalogName()) {
            oprot.writeString(struct.foreignCatalogName);
         }

         if (struct.isSetForeignSchemaName()) {
            oprot.writeString(struct.foreignSchemaName);
         }

         if (struct.isSetForeignTableName()) {
            oprot.writeString(struct.foreignTableName);
         }

      }

      public void read(TProtocol prot, TGetCrossReferenceReq struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         struct.sessionHandle = new TSessionHandle();
         struct.sessionHandle.read(iprot);
         struct.setSessionHandleIsSet(true);
         BitSet incoming = iprot.readBitSet(6);
         if (incoming.get(0)) {
            struct.parentCatalogName = iprot.readString();
            struct.setParentCatalogNameIsSet(true);
         }

         if (incoming.get(1)) {
            struct.parentSchemaName = iprot.readString();
            struct.setParentSchemaNameIsSet(true);
         }

         if (incoming.get(2)) {
            struct.parentTableName = iprot.readString();
            struct.setParentTableNameIsSet(true);
         }

         if (incoming.get(3)) {
            struct.foreignCatalogName = iprot.readString();
            struct.setForeignCatalogNameIsSet(true);
         }

         if (incoming.get(4)) {
            struct.foreignSchemaName = iprot.readString();
            struct.setForeignSchemaNameIsSet(true);
         }

         if (incoming.get(5)) {
            struct.foreignTableName = iprot.readString();
            struct.setForeignTableNameIsSet(true);
         }

      }
   }
}
