package org.apache.hive.service.rpc.thrift;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;
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
public class TUploadDataReq implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("TUploadDataReq");
   private static final TField SESSION_HANDLE_FIELD_DESC = new TField("sessionHandle", (byte)12, (short)1);
   private static final TField TABLE_NAME_FIELD_DESC = new TField("tableName", (byte)11, (short)2);
   private static final TField PATH_FIELD_DESC = new TField("path", (byte)11, (short)3);
   private static final TField VALUES_FIELD_DESC = new TField("values", (byte)11, (short)4);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new TUploadDataReqStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new TUploadDataReqTupleSchemeFactory();
   @Nullable
   private TSessionHandle sessionHandle;
   @Nullable
   private String tableName;
   @Nullable
   private String path;
   @Nullable
   private ByteBuffer values;
   private static final _Fields[] optionals;
   public static final Map metaDataMap;

   public TUploadDataReq() {
   }

   public TUploadDataReq(TSessionHandle sessionHandle, ByteBuffer values) {
      this();
      this.sessionHandle = sessionHandle;
      this.values = TBaseHelper.copyBinary(values);
   }

   public TUploadDataReq(TUploadDataReq other) {
      if (other.isSetSessionHandle()) {
         this.sessionHandle = new TSessionHandle(other.sessionHandle);
      }

      if (other.isSetTableName()) {
         this.tableName = other.tableName;
      }

      if (other.isSetPath()) {
         this.path = other.path;
      }

      if (other.isSetValues()) {
         this.values = TBaseHelper.copyBinary(other.values);
      }

   }

   public TUploadDataReq deepCopy() {
      return new TUploadDataReq(this);
   }

   public void clear() {
      this.sessionHandle = null;
      this.tableName = null;
      this.path = null;
      this.values = null;
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
   public String getPath() {
      return this.path;
   }

   public void setPath(@Nullable String path) {
      this.path = path;
   }

   public void unsetPath() {
      this.path = null;
   }

   public boolean isSetPath() {
      return this.path != null;
   }

   public void setPathIsSet(boolean value) {
      if (!value) {
         this.path = null;
      }

   }

   public byte[] getValues() {
      this.setValues(TBaseHelper.rightSize(this.values));
      return this.values == null ? null : this.values.array();
   }

   public ByteBuffer bufferForValues() {
      return TBaseHelper.copyBinary(this.values);
   }

   public void setValues(byte[] values) {
      this.values = values == null ? (ByteBuffer)null : ByteBuffer.wrap((byte[])(([B)values).clone());
   }

   public void setValues(@Nullable ByteBuffer values) {
      this.values = TBaseHelper.copyBinary(values);
   }

   public void unsetValues() {
      this.values = null;
   }

   public boolean isSetValues() {
      return this.values != null;
   }

   public void setValuesIsSet(boolean value) {
      if (!value) {
         this.values = null;
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
         case TABLE_NAME:
            if (value == null) {
               this.unsetTableName();
            } else {
               this.setTableName((String)value);
            }
            break;
         case PATH:
            if (value == null) {
               this.unsetPath();
            } else {
               this.setPath((String)value);
            }
            break;
         case VALUES:
            if (value == null) {
               this.unsetValues();
            } else if (value instanceof byte[]) {
               this.setValues((byte[])value);
            } else {
               this.setValues((ByteBuffer)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case SESSION_HANDLE:
            return this.getSessionHandle();
         case TABLE_NAME:
            return this.getTableName();
         case PATH:
            return this.getPath();
         case VALUES:
            return this.getValues();
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
            case TABLE_NAME:
               return this.isSetTableName();
            case PATH:
               return this.isSetPath();
            case VALUES:
               return this.isSetValues();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof TUploadDataReq ? this.equals((TUploadDataReq)that) : false;
   }

   public boolean equals(TUploadDataReq that) {
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

         boolean this_present_path = this.isSetPath();
         boolean that_present_path = that.isSetPath();
         if (this_present_path || that_present_path) {
            if (!this_present_path || !that_present_path) {
               return false;
            }

            if (!this.path.equals(that.path)) {
               return false;
            }
         }

         boolean this_present_values = this.isSetValues();
         boolean that_present_values = that.isSetValues();
         if (this_present_values || that_present_values) {
            if (!this_present_values || !that_present_values) {
               return false;
            }

            if (!this.values.equals(that.values)) {
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

      hashCode = hashCode * 8191 + (this.isSetTableName() ? 131071 : 524287);
      if (this.isSetTableName()) {
         hashCode = hashCode * 8191 + this.tableName.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetPath() ? 131071 : 524287);
      if (this.isSetPath()) {
         hashCode = hashCode * 8191 + this.path.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetValues() ? 131071 : 524287);
      if (this.isSetValues()) {
         hashCode = hashCode * 8191 + this.values.hashCode();
      }

      return hashCode;
   }

   public int compareTo(TUploadDataReq other) {
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

               lastComparison = Boolean.compare(this.isSetPath(), other.isSetPath());
               if (lastComparison != 0) {
                  return lastComparison;
               } else {
                  if (this.isSetPath()) {
                     lastComparison = TBaseHelper.compareTo(this.path, other.path);
                     if (lastComparison != 0) {
                        return lastComparison;
                     }
                  }

                  lastComparison = Boolean.compare(this.isSetValues(), other.isSetValues());
                  if (lastComparison != 0) {
                     return lastComparison;
                  } else {
                     if (this.isSetValues()) {
                        lastComparison = TBaseHelper.compareTo(this.values, other.values);
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

   @Nullable
   public _Fields fieldForId(int fieldId) {
      return TUploadDataReq._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("TUploadDataReq(");
      boolean first = true;
      sb.append("sessionHandle:");
      if (this.sessionHandle == null) {
         sb.append("null");
      } else {
         sb.append(this.sessionHandle);
      }

      first = false;
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

      if (this.isSetPath()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("path:");
         if (this.path == null) {
            sb.append("null");
         } else {
            sb.append(this.path);
         }

         first = false;
      }

      if (!first) {
         sb.append(", ");
      }

      sb.append("values:");
      if (this.values == null) {
         sb.append("null");
      } else {
         TBaseHelper.toString(this.values, sb);
      }

      first = false;
      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (!this.isSetSessionHandle()) {
         throw new TProtocolException("Required field 'sessionHandle' is unset! Struct:" + this.toString());
      } else if (!this.isSetValues()) {
         throw new TProtocolException("Required field 'values' is unset! Struct:" + this.toString());
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
      optionals = new _Fields[]{TUploadDataReq._Fields.TABLE_NAME, TUploadDataReq._Fields.PATH};
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(TUploadDataReq._Fields.SESSION_HANDLE, new FieldMetaData("sessionHandle", (byte)1, new StructMetaData((byte)12, TSessionHandle.class)));
      tmpMap.put(TUploadDataReq._Fields.TABLE_NAME, new FieldMetaData("tableName", (byte)2, new FieldValueMetaData((byte)11)));
      tmpMap.put(TUploadDataReq._Fields.PATH, new FieldMetaData("path", (byte)2, new FieldValueMetaData((byte)11)));
      tmpMap.put(TUploadDataReq._Fields.VALUES, new FieldMetaData("values", (byte)1, new FieldValueMetaData((byte)11, true)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(TUploadDataReq.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      SESSION_HANDLE((short)1, "sessionHandle"),
      TABLE_NAME((short)2, "tableName"),
      PATH((short)3, "path"),
      VALUES((short)4, "values");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return SESSION_HANDLE;
            case 2:
               return TABLE_NAME;
            case 3:
               return PATH;
            case 4:
               return VALUES;
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

   private static class TUploadDataReqStandardSchemeFactory implements SchemeFactory {
      private TUploadDataReqStandardSchemeFactory() {
      }

      public TUploadDataReqStandardScheme getScheme() {
         return new TUploadDataReqStandardScheme();
      }
   }

   private static class TUploadDataReqStandardScheme extends StandardScheme {
      private TUploadDataReqStandardScheme() {
      }

      public void read(TProtocol iprot, TUploadDataReq struct) throws TException {
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
                     struct.tableName = iprot.readString();
                     struct.setTableNameIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 3:
                  if (schemeField.type == 11) {
                     struct.path = iprot.readString();
                     struct.setPathIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 4:
                  if (schemeField.type == 11) {
                     struct.values = iprot.readBinary();
                     struct.setValuesIsSet(true);
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

      public void write(TProtocol oprot, TUploadDataReq struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(TUploadDataReq.STRUCT_DESC);
         if (struct.sessionHandle != null) {
            oprot.writeFieldBegin(TUploadDataReq.SESSION_HANDLE_FIELD_DESC);
            struct.sessionHandle.write(oprot);
            oprot.writeFieldEnd();
         }

         if (struct.tableName != null && struct.isSetTableName()) {
            oprot.writeFieldBegin(TUploadDataReq.TABLE_NAME_FIELD_DESC);
            oprot.writeString(struct.tableName);
            oprot.writeFieldEnd();
         }

         if (struct.path != null && struct.isSetPath()) {
            oprot.writeFieldBegin(TUploadDataReq.PATH_FIELD_DESC);
            oprot.writeString(struct.path);
            oprot.writeFieldEnd();
         }

         if (struct.values != null) {
            oprot.writeFieldBegin(TUploadDataReq.VALUES_FIELD_DESC);
            oprot.writeBinary(struct.values);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class TUploadDataReqTupleSchemeFactory implements SchemeFactory {
      private TUploadDataReqTupleSchemeFactory() {
      }

      public TUploadDataReqTupleScheme getScheme() {
         return new TUploadDataReqTupleScheme();
      }
   }

   private static class TUploadDataReqTupleScheme extends TupleScheme {
      private TUploadDataReqTupleScheme() {
      }

      public void write(TProtocol prot, TUploadDataReq struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         struct.sessionHandle.write(oprot);
         oprot.writeBinary(struct.values);
         BitSet optionals = new BitSet();
         if (struct.isSetTableName()) {
            optionals.set(0);
         }

         if (struct.isSetPath()) {
            optionals.set(1);
         }

         oprot.writeBitSet(optionals, 2);
         if (struct.isSetTableName()) {
            oprot.writeString(struct.tableName);
         }

         if (struct.isSetPath()) {
            oprot.writeString(struct.path);
         }

      }

      public void read(TProtocol prot, TUploadDataReq struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         struct.sessionHandle = new TSessionHandle();
         struct.sessionHandle.read(iprot);
         struct.setSessionHandleIsSet(true);
         struct.values = iprot.readBinary();
         struct.setValuesIsSet(true);
         BitSet incoming = iprot.readBitSet(2);
         if (incoming.get(0)) {
            struct.tableName = iprot.readString();
            struct.setTableNameIsSet(true);
         }

         if (incoming.get(1)) {
            struct.path = iprot.readString();
            struct.setPathIsSet(true);
         }

      }
   }
}
