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
public class TGetColumnsReq implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("TGetColumnsReq");
   private static final TField SESSION_HANDLE_FIELD_DESC = new TField("sessionHandle", (byte)12, (short)1);
   private static final TField CATALOG_NAME_FIELD_DESC = new TField("catalogName", (byte)11, (short)2);
   private static final TField SCHEMA_NAME_FIELD_DESC = new TField("schemaName", (byte)11, (short)3);
   private static final TField TABLE_NAME_FIELD_DESC = new TField("tableName", (byte)11, (short)4);
   private static final TField COLUMN_NAME_FIELD_DESC = new TField("columnName", (byte)11, (short)5);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new TGetColumnsReqStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new TGetColumnsReqTupleSchemeFactory();
   @Nullable
   private TSessionHandle sessionHandle;
   @Nullable
   private String catalogName;
   @Nullable
   private String schemaName;
   @Nullable
   private String tableName;
   @Nullable
   private String columnName;
   private static final _Fields[] optionals;
   public static final Map metaDataMap;

   public TGetColumnsReq() {
   }

   public TGetColumnsReq(TSessionHandle sessionHandle) {
      this();
      this.sessionHandle = sessionHandle;
   }

   public TGetColumnsReq(TGetColumnsReq other) {
      if (other.isSetSessionHandle()) {
         this.sessionHandle = new TSessionHandle(other.sessionHandle);
      }

      if (other.isSetCatalogName()) {
         this.catalogName = other.catalogName;
      }

      if (other.isSetSchemaName()) {
         this.schemaName = other.schemaName;
      }

      if (other.isSetTableName()) {
         this.tableName = other.tableName;
      }

      if (other.isSetColumnName()) {
         this.columnName = other.columnName;
      }

   }

   public TGetColumnsReq deepCopy() {
      return new TGetColumnsReq(this);
   }

   public void clear() {
      this.sessionHandle = null;
      this.catalogName = null;
      this.schemaName = null;
      this.tableName = null;
      this.columnName = null;
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
   public String getCatalogName() {
      return this.catalogName;
   }

   public void setCatalogName(@Nullable String catalogName) {
      this.catalogName = catalogName;
   }

   public void unsetCatalogName() {
      this.catalogName = null;
   }

   public boolean isSetCatalogName() {
      return this.catalogName != null;
   }

   public void setCatalogNameIsSet(boolean value) {
      if (!value) {
         this.catalogName = null;
      }

   }

   @Nullable
   public String getSchemaName() {
      return this.schemaName;
   }

   public void setSchemaName(@Nullable String schemaName) {
      this.schemaName = schemaName;
   }

   public void unsetSchemaName() {
      this.schemaName = null;
   }

   public boolean isSetSchemaName() {
      return this.schemaName != null;
   }

   public void setSchemaNameIsSet(boolean value) {
      if (!value) {
         this.schemaName = null;
      }

   }

   @Nullable
   public String getTableName() {
      return this.tableName;
   }

   public void setTableName(@Nullable String tableName) {
      this.tableName = tableName;
   }

   public void unsetTableName() {
      this.tableName = null;
   }

   public boolean isSetTableName() {
      return this.tableName != null;
   }

   public void setTableNameIsSet(boolean value) {
      if (!value) {
         this.tableName = null;
      }

   }

   @Nullable
   public String getColumnName() {
      return this.columnName;
   }

   public void setColumnName(@Nullable String columnName) {
      this.columnName = columnName;
   }

   public void unsetColumnName() {
      this.columnName = null;
   }

   public boolean isSetColumnName() {
      return this.columnName != null;
   }

   public void setColumnNameIsSet(boolean value) {
      if (!value) {
         this.columnName = null;
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
         case CATALOG_NAME:
            if (value == null) {
               this.unsetCatalogName();
            } else {
               this.setCatalogName((String)value);
            }
            break;
         case SCHEMA_NAME:
            if (value == null) {
               this.unsetSchemaName();
            } else {
               this.setSchemaName((String)value);
            }
            break;
         case TABLE_NAME:
            if (value == null) {
               this.unsetTableName();
            } else {
               this.setTableName((String)value);
            }
            break;
         case COLUMN_NAME:
            if (value == null) {
               this.unsetColumnName();
            } else {
               this.setColumnName((String)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case SESSION_HANDLE:
            return this.getSessionHandle();
         case CATALOG_NAME:
            return this.getCatalogName();
         case SCHEMA_NAME:
            return this.getSchemaName();
         case TABLE_NAME:
            return this.getTableName();
         case COLUMN_NAME:
            return this.getColumnName();
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
            case CATALOG_NAME:
               return this.isSetCatalogName();
            case SCHEMA_NAME:
               return this.isSetSchemaName();
            case TABLE_NAME:
               return this.isSetTableName();
            case COLUMN_NAME:
               return this.isSetColumnName();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof TGetColumnsReq ? this.equals((TGetColumnsReq)that) : false;
   }

   public boolean equals(TGetColumnsReq that) {
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

         boolean this_present_catalogName = this.isSetCatalogName();
         boolean that_present_catalogName = that.isSetCatalogName();
         if (this_present_catalogName || that_present_catalogName) {
            if (!this_present_catalogName || !that_present_catalogName) {
               return false;
            }

            if (!this.catalogName.equals(that.catalogName)) {
               return false;
            }
         }

         boolean this_present_schemaName = this.isSetSchemaName();
         boolean that_present_schemaName = that.isSetSchemaName();
         if (this_present_schemaName || that_present_schemaName) {
            if (!this_present_schemaName || !that_present_schemaName) {
               return false;
            }

            if (!this.schemaName.equals(that.schemaName)) {
               return false;
            }
         }

         boolean this_present_tableName = this.isSetTableName();
         boolean that_present_tableName = that.isSetTableName();
         if (this_present_tableName || that_present_tableName) {
            if (!this_present_tableName || !that_present_tableName) {
               return false;
            }

            if (!this.tableName.equals(that.tableName)) {
               return false;
            }
         }

         boolean this_present_columnName = this.isSetColumnName();
         boolean that_present_columnName = that.isSetColumnName();
         if (this_present_columnName || that_present_columnName) {
            if (!this_present_columnName || !that_present_columnName) {
               return false;
            }

            if (!this.columnName.equals(that.columnName)) {
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

      hashCode = hashCode * 8191 + (this.isSetCatalogName() ? 131071 : 524287);
      if (this.isSetCatalogName()) {
         hashCode = hashCode * 8191 + this.catalogName.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetSchemaName() ? 131071 : 524287);
      if (this.isSetSchemaName()) {
         hashCode = hashCode * 8191 + this.schemaName.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetTableName() ? 131071 : 524287);
      if (this.isSetTableName()) {
         hashCode = hashCode * 8191 + this.tableName.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetColumnName() ? 131071 : 524287);
      if (this.isSetColumnName()) {
         hashCode = hashCode * 8191 + this.columnName.hashCode();
      }

      return hashCode;
   }

   public int compareTo(TGetColumnsReq other) {
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

            lastComparison = Boolean.compare(this.isSetCatalogName(), other.isSetCatalogName());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetCatalogName()) {
                  lastComparison = TBaseHelper.compareTo(this.catalogName, other.catalogName);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               lastComparison = Boolean.compare(this.isSetSchemaName(), other.isSetSchemaName());
               if (lastComparison != 0) {
                  return lastComparison;
               } else {
                  if (this.isSetSchemaName()) {
                     lastComparison = TBaseHelper.compareTo(this.schemaName, other.schemaName);
                     if (lastComparison != 0) {
                        return lastComparison;
                     }
                  }

                  lastComparison = Boolean.compare(this.isSetTableName(), other.isSetTableName());
                  if (lastComparison != 0) {
                     return lastComparison;
                  } else {
                     if (this.isSetTableName()) {
                        lastComparison = TBaseHelper.compareTo(this.tableName, other.tableName);
                        if (lastComparison != 0) {
                           return lastComparison;
                        }
                     }

                     lastComparison = Boolean.compare(this.isSetColumnName(), other.isSetColumnName());
                     if (lastComparison != 0) {
                        return lastComparison;
                     } else {
                        if (this.isSetColumnName()) {
                           lastComparison = TBaseHelper.compareTo(this.columnName, other.columnName);
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

   @Nullable
   public _Fields fieldForId(int fieldId) {
      return TGetColumnsReq._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("TGetColumnsReq(");
      boolean first = true;
      sb.append("sessionHandle:");
      if (this.sessionHandle == null) {
         sb.append("null");
      } else {
         sb.append(this.sessionHandle);
      }

      first = false;
      if (this.isSetCatalogName()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("catalogName:");
         if (this.catalogName == null) {
            sb.append("null");
         } else {
            sb.append(this.catalogName);
         }

         first = false;
      }

      if (this.isSetSchemaName()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("schemaName:");
         if (this.schemaName == null) {
            sb.append("null");
         } else {
            sb.append(this.schemaName);
         }

         first = false;
      }

      if (this.isSetTableName()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("tableName:");
         if (this.tableName == null) {
            sb.append("null");
         } else {
            sb.append(this.tableName);
         }

         first = false;
      }

      if (this.isSetColumnName()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("columnName:");
         if (this.columnName == null) {
            sb.append("null");
         } else {
            sb.append(this.columnName);
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
      optionals = new _Fields[]{TGetColumnsReq._Fields.CATALOG_NAME, TGetColumnsReq._Fields.SCHEMA_NAME, TGetColumnsReq._Fields.TABLE_NAME, TGetColumnsReq._Fields.COLUMN_NAME};
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(TGetColumnsReq._Fields.SESSION_HANDLE, new FieldMetaData("sessionHandle", (byte)1, new StructMetaData((byte)12, TSessionHandle.class)));
      tmpMap.put(TGetColumnsReq._Fields.CATALOG_NAME, new FieldMetaData("catalogName", (byte)2, new FieldValueMetaData((byte)11, "TIdentifier")));
      tmpMap.put(TGetColumnsReq._Fields.SCHEMA_NAME, new FieldMetaData("schemaName", (byte)2, new FieldValueMetaData((byte)11, "TPatternOrIdentifier")));
      tmpMap.put(TGetColumnsReq._Fields.TABLE_NAME, new FieldMetaData("tableName", (byte)2, new FieldValueMetaData((byte)11, "TPatternOrIdentifier")));
      tmpMap.put(TGetColumnsReq._Fields.COLUMN_NAME, new FieldMetaData("columnName", (byte)2, new FieldValueMetaData((byte)11, "TPatternOrIdentifier")));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(TGetColumnsReq.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      SESSION_HANDLE((short)1, "sessionHandle"),
      CATALOG_NAME((short)2, "catalogName"),
      SCHEMA_NAME((short)3, "schemaName"),
      TABLE_NAME((short)4, "tableName"),
      COLUMN_NAME((short)5, "columnName");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return SESSION_HANDLE;
            case 2:
               return CATALOG_NAME;
            case 3:
               return SCHEMA_NAME;
            case 4:
               return TABLE_NAME;
            case 5:
               return COLUMN_NAME;
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

   private static class TGetColumnsReqStandardSchemeFactory implements SchemeFactory {
      private TGetColumnsReqStandardSchemeFactory() {
      }

      public TGetColumnsReqStandardScheme getScheme() {
         return new TGetColumnsReqStandardScheme();
      }
   }

   private static class TGetColumnsReqStandardScheme extends StandardScheme {
      private TGetColumnsReqStandardScheme() {
      }

      public void read(TProtocol iprot, TGetColumnsReq struct) throws TException {
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
                     struct.catalogName = iprot.readString();
                     struct.setCatalogNameIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 3:
                  if (schemeField.type == 11) {
                     struct.schemaName = iprot.readString();
                     struct.setSchemaNameIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 4:
                  if (schemeField.type == 11) {
                     struct.tableName = iprot.readString();
                     struct.setTableNameIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 5:
                  if (schemeField.type == 11) {
                     struct.columnName = iprot.readString();
                     struct.setColumnNameIsSet(true);
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

      public void write(TProtocol oprot, TGetColumnsReq struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(TGetColumnsReq.STRUCT_DESC);
         if (struct.sessionHandle != null) {
            oprot.writeFieldBegin(TGetColumnsReq.SESSION_HANDLE_FIELD_DESC);
            struct.sessionHandle.write(oprot);
            oprot.writeFieldEnd();
         }

         if (struct.catalogName != null && struct.isSetCatalogName()) {
            oprot.writeFieldBegin(TGetColumnsReq.CATALOG_NAME_FIELD_DESC);
            oprot.writeString(struct.catalogName);
            oprot.writeFieldEnd();
         }

         if (struct.schemaName != null && struct.isSetSchemaName()) {
            oprot.writeFieldBegin(TGetColumnsReq.SCHEMA_NAME_FIELD_DESC);
            oprot.writeString(struct.schemaName);
            oprot.writeFieldEnd();
         }

         if (struct.tableName != null && struct.isSetTableName()) {
            oprot.writeFieldBegin(TGetColumnsReq.TABLE_NAME_FIELD_DESC);
            oprot.writeString(struct.tableName);
            oprot.writeFieldEnd();
         }

         if (struct.columnName != null && struct.isSetColumnName()) {
            oprot.writeFieldBegin(TGetColumnsReq.COLUMN_NAME_FIELD_DESC);
            oprot.writeString(struct.columnName);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class TGetColumnsReqTupleSchemeFactory implements SchemeFactory {
      private TGetColumnsReqTupleSchemeFactory() {
      }

      public TGetColumnsReqTupleScheme getScheme() {
         return new TGetColumnsReqTupleScheme();
      }
   }

   private static class TGetColumnsReqTupleScheme extends TupleScheme {
      private TGetColumnsReqTupleScheme() {
      }

      public void write(TProtocol prot, TGetColumnsReq struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         struct.sessionHandle.write(oprot);
         BitSet optionals = new BitSet();
         if (struct.isSetCatalogName()) {
            optionals.set(0);
         }

         if (struct.isSetSchemaName()) {
            optionals.set(1);
         }

         if (struct.isSetTableName()) {
            optionals.set(2);
         }

         if (struct.isSetColumnName()) {
            optionals.set(3);
         }

         oprot.writeBitSet(optionals, 4);
         if (struct.isSetCatalogName()) {
            oprot.writeString(struct.catalogName);
         }

         if (struct.isSetSchemaName()) {
            oprot.writeString(struct.schemaName);
         }

         if (struct.isSetTableName()) {
            oprot.writeString(struct.tableName);
         }

         if (struct.isSetColumnName()) {
            oprot.writeString(struct.columnName);
         }

      }

      public void read(TProtocol prot, TGetColumnsReq struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         struct.sessionHandle = new TSessionHandle();
         struct.sessionHandle.read(iprot);
         struct.setSessionHandleIsSet(true);
         BitSet incoming = iprot.readBitSet(4);
         if (incoming.get(0)) {
            struct.catalogName = iprot.readString();
            struct.setCatalogNameIsSet(true);
         }

         if (incoming.get(1)) {
            struct.schemaName = iprot.readString();
            struct.setSchemaNameIsSet(true);
         }

         if (incoming.get(2)) {
            struct.tableName = iprot.readString();
            struct.setTableNameIsSet(true);
         }

         if (incoming.get(3)) {
            struct.columnName = iprot.readString();
            struct.setColumnNameIsSet(true);
         }

      }
   }
}
