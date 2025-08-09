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
import org.apache.thrift.meta_data.MapMetaData;
import org.apache.thrift.meta_data.StructMetaData;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TField;
import org.apache.thrift.protocol.TMap;
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
public class TDownloadDataReq implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("TDownloadDataReq");
   private static final TField SESSION_HANDLE_FIELD_DESC = new TField("sessionHandle", (byte)12, (short)1);
   private static final TField TABLE_NAME_FIELD_DESC = new TField("tableName", (byte)11, (short)2);
   private static final TField QUERY_FIELD_DESC = new TField("query", (byte)11, (short)3);
   private static final TField FORMAT_FIELD_DESC = new TField("format", (byte)11, (short)4);
   private static final TField DOWNLOAD_OPTIONS_FIELD_DESC = new TField("downloadOptions", (byte)13, (short)5);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new TDownloadDataReqStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new TDownloadDataReqTupleSchemeFactory();
   @Nullable
   private TSessionHandle sessionHandle;
   @Nullable
   private String tableName;
   @Nullable
   private String query;
   @Nullable
   private String format;
   @Nullable
   private Map downloadOptions;
   private static final _Fields[] optionals;
   public static final Map metaDataMap;

   public TDownloadDataReq() {
   }

   public TDownloadDataReq(TSessionHandle sessionHandle) {
      this();
      this.sessionHandle = sessionHandle;
   }

   public TDownloadDataReq(TDownloadDataReq other) {
      if (other.isSetSessionHandle()) {
         this.sessionHandle = new TSessionHandle(other.sessionHandle);
      }

      if (other.isSetTableName()) {
         this.tableName = other.tableName;
      }

      if (other.isSetQuery()) {
         this.query = other.query;
      }

      if (other.isSetFormat()) {
         this.format = other.format;
      }

      if (other.isSetDownloadOptions()) {
         Map<String, String> __this__downloadOptions = new HashMap(other.downloadOptions);
         this.downloadOptions = __this__downloadOptions;
      }

   }

   public TDownloadDataReq deepCopy() {
      return new TDownloadDataReq(this);
   }

   public void clear() {
      this.sessionHandle = null;
      this.tableName = null;
      this.query = null;
      this.format = null;
      this.downloadOptions = null;
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
   public String getQuery() {
      return this.query;
   }

   public void setQuery(@Nullable String query) {
      this.query = query;
   }

   public void unsetQuery() {
      this.query = null;
   }

   public boolean isSetQuery() {
      return this.query != null;
   }

   public void setQueryIsSet(boolean value) {
      if (!value) {
         this.query = null;
      }

   }

   @Nullable
   public String getFormat() {
      return this.format;
   }

   public void setFormat(@Nullable String format) {
      this.format = format;
   }

   public void unsetFormat() {
      this.format = null;
   }

   public boolean isSetFormat() {
      return this.format != null;
   }

   public void setFormatIsSet(boolean value) {
      if (!value) {
         this.format = null;
      }

   }

   public int getDownloadOptionsSize() {
      return this.downloadOptions == null ? 0 : this.downloadOptions.size();
   }

   public void putToDownloadOptions(String key, String val) {
      if (this.downloadOptions == null) {
         this.downloadOptions = new HashMap();
      }

      this.downloadOptions.put(key, val);
   }

   @Nullable
   public Map getDownloadOptions() {
      return this.downloadOptions;
   }

   public void setDownloadOptions(@Nullable Map downloadOptions) {
      this.downloadOptions = downloadOptions;
   }

   public void unsetDownloadOptions() {
      this.downloadOptions = null;
   }

   public boolean isSetDownloadOptions() {
      return this.downloadOptions != null;
   }

   public void setDownloadOptionsIsSet(boolean value) {
      if (!value) {
         this.downloadOptions = null;
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
         case QUERY:
            if (value == null) {
               this.unsetQuery();
            } else {
               this.setQuery((String)value);
            }
            break;
         case FORMAT:
            if (value == null) {
               this.unsetFormat();
            } else {
               this.setFormat((String)value);
            }
            break;
         case DOWNLOAD_OPTIONS:
            if (value == null) {
               this.unsetDownloadOptions();
            } else {
               this.setDownloadOptions((Map)value);
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
         case QUERY:
            return this.getQuery();
         case FORMAT:
            return this.getFormat();
         case DOWNLOAD_OPTIONS:
            return this.getDownloadOptions();
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
            case QUERY:
               return this.isSetQuery();
            case FORMAT:
               return this.isSetFormat();
            case DOWNLOAD_OPTIONS:
               return this.isSetDownloadOptions();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof TDownloadDataReq ? this.equals((TDownloadDataReq)that) : false;
   }

   public boolean equals(TDownloadDataReq that) {
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

         boolean this_present_query = this.isSetQuery();
         boolean that_present_query = that.isSetQuery();
         if (this_present_query || that_present_query) {
            if (!this_present_query || !that_present_query) {
               return false;
            }

            if (!this.query.equals(that.query)) {
               return false;
            }
         }

         boolean this_present_format = this.isSetFormat();
         boolean that_present_format = that.isSetFormat();
         if (this_present_format || that_present_format) {
            if (!this_present_format || !that_present_format) {
               return false;
            }

            if (!this.format.equals(that.format)) {
               return false;
            }
         }

         boolean this_present_downloadOptions = this.isSetDownloadOptions();
         boolean that_present_downloadOptions = that.isSetDownloadOptions();
         if (this_present_downloadOptions || that_present_downloadOptions) {
            if (!this_present_downloadOptions || !that_present_downloadOptions) {
               return false;
            }

            if (!this.downloadOptions.equals(that.downloadOptions)) {
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

      hashCode = hashCode * 8191 + (this.isSetQuery() ? 131071 : 524287);
      if (this.isSetQuery()) {
         hashCode = hashCode * 8191 + this.query.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetFormat() ? 131071 : 524287);
      if (this.isSetFormat()) {
         hashCode = hashCode * 8191 + this.format.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetDownloadOptions() ? 131071 : 524287);
      if (this.isSetDownloadOptions()) {
         hashCode = hashCode * 8191 + this.downloadOptions.hashCode();
      }

      return hashCode;
   }

   public int compareTo(TDownloadDataReq other) {
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

               lastComparison = Boolean.compare(this.isSetQuery(), other.isSetQuery());
               if (lastComparison != 0) {
                  return lastComparison;
               } else {
                  if (this.isSetQuery()) {
                     lastComparison = TBaseHelper.compareTo(this.query, other.query);
                     if (lastComparison != 0) {
                        return lastComparison;
                     }
                  }

                  lastComparison = Boolean.compare(this.isSetFormat(), other.isSetFormat());
                  if (lastComparison != 0) {
                     return lastComparison;
                  } else {
                     if (this.isSetFormat()) {
                        lastComparison = TBaseHelper.compareTo(this.format, other.format);
                        if (lastComparison != 0) {
                           return lastComparison;
                        }
                     }

                     lastComparison = Boolean.compare(this.isSetDownloadOptions(), other.isSetDownloadOptions());
                     if (lastComparison != 0) {
                        return lastComparison;
                     } else {
                        if (this.isSetDownloadOptions()) {
                           lastComparison = TBaseHelper.compareTo(this.downloadOptions, other.downloadOptions);
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
      return TDownloadDataReq._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("TDownloadDataReq(");
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

      if (this.isSetQuery()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("query:");
         if (this.query == null) {
            sb.append("null");
         } else {
            sb.append(this.query);
         }

         first = false;
      }

      if (this.isSetFormat()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("format:");
         if (this.format == null) {
            sb.append("null");
         } else {
            sb.append(this.format);
         }

         first = false;
      }

      if (this.isSetDownloadOptions()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("downloadOptions:");
         if (this.downloadOptions == null) {
            sb.append("null");
         } else {
            sb.append(this.downloadOptions);
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
      optionals = new _Fields[]{TDownloadDataReq._Fields.TABLE_NAME, TDownloadDataReq._Fields.QUERY, TDownloadDataReq._Fields.FORMAT, TDownloadDataReq._Fields.DOWNLOAD_OPTIONS};
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(TDownloadDataReq._Fields.SESSION_HANDLE, new FieldMetaData("sessionHandle", (byte)1, new StructMetaData((byte)12, TSessionHandle.class)));
      tmpMap.put(TDownloadDataReq._Fields.TABLE_NAME, new FieldMetaData("tableName", (byte)2, new FieldValueMetaData((byte)11, "TPatternOrIdentifier")));
      tmpMap.put(TDownloadDataReq._Fields.QUERY, new FieldMetaData("query", (byte)2, new FieldValueMetaData((byte)11)));
      tmpMap.put(TDownloadDataReq._Fields.FORMAT, new FieldMetaData("format", (byte)2, new FieldValueMetaData((byte)11)));
      tmpMap.put(TDownloadDataReq._Fields.DOWNLOAD_OPTIONS, new FieldMetaData("downloadOptions", (byte)2, new MapMetaData((byte)13, new FieldValueMetaData((byte)11), new FieldValueMetaData((byte)11))));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(TDownloadDataReq.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      SESSION_HANDLE((short)1, "sessionHandle"),
      TABLE_NAME((short)2, "tableName"),
      QUERY((short)3, "query"),
      FORMAT((short)4, "format"),
      DOWNLOAD_OPTIONS((short)5, "downloadOptions");

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
               return QUERY;
            case 4:
               return FORMAT;
            case 5:
               return DOWNLOAD_OPTIONS;
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

   private static class TDownloadDataReqStandardSchemeFactory implements SchemeFactory {
      private TDownloadDataReqStandardSchemeFactory() {
      }

      public TDownloadDataReqStandardScheme getScheme() {
         return new TDownloadDataReqStandardScheme();
      }
   }

   private static class TDownloadDataReqStandardScheme extends StandardScheme {
      private TDownloadDataReqStandardScheme() {
      }

      public void read(TProtocol iprot, TDownloadDataReq struct) throws TException {
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
                     struct.query = iprot.readString();
                     struct.setQueryIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 4:
                  if (schemeField.type == 11) {
                     struct.format = iprot.readString();
                     struct.setFormatIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 5:
                  if (schemeField.type != 13) {
                     TProtocolUtil.skip(iprot, schemeField.type);
                     break;
                  }

                  TMap _map182 = iprot.readMapBegin();
                  struct.downloadOptions = new HashMap(2 * _map182.size);

                  for(int _i185 = 0; _i185 < _map182.size; ++_i185) {
                     String _key183 = iprot.readString();
                     String _val184 = iprot.readString();
                     struct.downloadOptions.put(_key183, _val184);
                  }

                  iprot.readMapEnd();
                  struct.setDownloadOptionsIsSet(true);
                  break;
               default:
                  TProtocolUtil.skip(iprot, schemeField.type);
            }

            iprot.readFieldEnd();
         }
      }

      public void write(TProtocol oprot, TDownloadDataReq struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(TDownloadDataReq.STRUCT_DESC);
         if (struct.sessionHandle != null) {
            oprot.writeFieldBegin(TDownloadDataReq.SESSION_HANDLE_FIELD_DESC);
            struct.sessionHandle.write(oprot);
            oprot.writeFieldEnd();
         }

         if (struct.tableName != null && struct.isSetTableName()) {
            oprot.writeFieldBegin(TDownloadDataReq.TABLE_NAME_FIELD_DESC);
            oprot.writeString(struct.tableName);
            oprot.writeFieldEnd();
         }

         if (struct.query != null && struct.isSetQuery()) {
            oprot.writeFieldBegin(TDownloadDataReq.QUERY_FIELD_DESC);
            oprot.writeString(struct.query);
            oprot.writeFieldEnd();
         }

         if (struct.format != null && struct.isSetFormat()) {
            oprot.writeFieldBegin(TDownloadDataReq.FORMAT_FIELD_DESC);
            oprot.writeString(struct.format);
            oprot.writeFieldEnd();
         }

         if (struct.downloadOptions != null && struct.isSetDownloadOptions()) {
            oprot.writeFieldBegin(TDownloadDataReq.DOWNLOAD_OPTIONS_FIELD_DESC);
            oprot.writeMapBegin(new TMap((byte)11, (byte)11, struct.downloadOptions.size()));

            for(Map.Entry _iter186 : struct.downloadOptions.entrySet()) {
               oprot.writeString((String)_iter186.getKey());
               oprot.writeString((String)_iter186.getValue());
            }

            oprot.writeMapEnd();
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class TDownloadDataReqTupleSchemeFactory implements SchemeFactory {
      private TDownloadDataReqTupleSchemeFactory() {
      }

      public TDownloadDataReqTupleScheme getScheme() {
         return new TDownloadDataReqTupleScheme();
      }
   }

   private static class TDownloadDataReqTupleScheme extends TupleScheme {
      private TDownloadDataReqTupleScheme() {
      }

      public void write(TProtocol prot, TDownloadDataReq struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         struct.sessionHandle.write(oprot);
         BitSet optionals = new BitSet();
         if (struct.isSetTableName()) {
            optionals.set(0);
         }

         if (struct.isSetQuery()) {
            optionals.set(1);
         }

         if (struct.isSetFormat()) {
            optionals.set(2);
         }

         if (struct.isSetDownloadOptions()) {
            optionals.set(3);
         }

         oprot.writeBitSet(optionals, 4);
         if (struct.isSetTableName()) {
            oprot.writeString(struct.tableName);
         }

         if (struct.isSetQuery()) {
            oprot.writeString(struct.query);
         }

         if (struct.isSetFormat()) {
            oprot.writeString(struct.format);
         }

         if (struct.isSetDownloadOptions()) {
            oprot.writeI32(struct.downloadOptions.size());

            for(Map.Entry _iter187 : struct.downloadOptions.entrySet()) {
               oprot.writeString((String)_iter187.getKey());
               oprot.writeString((String)_iter187.getValue());
            }
         }

      }

      public void read(TProtocol prot, TDownloadDataReq struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         struct.sessionHandle = new TSessionHandle();
         struct.sessionHandle.read(iprot);
         struct.setSessionHandleIsSet(true);
         BitSet incoming = iprot.readBitSet(4);
         if (incoming.get(0)) {
            struct.tableName = iprot.readString();
            struct.setTableNameIsSet(true);
         }

         if (incoming.get(1)) {
            struct.query = iprot.readString();
            struct.setQueryIsSet(true);
         }

         if (incoming.get(2)) {
            struct.format = iprot.readString();
            struct.setFormatIsSet(true);
         }

         if (incoming.get(3)) {
            TMap _map188 = iprot.readMapBegin((byte)11, (byte)11);
            struct.downloadOptions = new HashMap(2 * _map188.size);

            for(int _i191 = 0; _i191 < _map188.size; ++_i191) {
               String _key189 = iprot.readString();
               String _val190 = iprot.readString();
               struct.downloadOptions.put(_key189, _val190);
            }

            struct.setDownloadOptionsIsSet(true);
         }

      }
   }
}
