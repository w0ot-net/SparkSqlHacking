package org.apache.hive.service.rpc.thrift;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
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
public class TGetQueryIdResp implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("TGetQueryIdResp");
   private static final TField QUERY_ID_FIELD_DESC = new TField("queryId", (byte)11, (short)1);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new TGetQueryIdRespStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new TGetQueryIdRespTupleSchemeFactory();
   @Nullable
   private String queryId;
   public static final Map metaDataMap;

   public TGetQueryIdResp() {
   }

   public TGetQueryIdResp(String queryId) {
      this();
      this.queryId = queryId;
   }

   public TGetQueryIdResp(TGetQueryIdResp other) {
      if (other.isSetQueryId()) {
         this.queryId = other.queryId;
      }

   }

   public TGetQueryIdResp deepCopy() {
      return new TGetQueryIdResp(this);
   }

   public void clear() {
      this.queryId = null;
   }

   @Nullable
   public String getQueryId() {
      return this.queryId;
   }

   public void setQueryId(@Nullable String queryId) {
      this.queryId = queryId;
   }

   public void unsetQueryId() {
      this.queryId = null;
   }

   public boolean isSetQueryId() {
      return this.queryId != null;
   }

   public void setQueryIdIsSet(boolean value) {
      if (!value) {
         this.queryId = null;
      }

   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case QUERY_ID:
            if (value == null) {
               this.unsetQueryId();
            } else {
               this.setQueryId((String)value);
            }
         default:
      }
   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case QUERY_ID:
            return this.getQueryId();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case QUERY_ID:
               return this.isSetQueryId();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof TGetQueryIdResp ? this.equals((TGetQueryIdResp)that) : false;
   }

   public boolean equals(TGetQueryIdResp that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_queryId = this.isSetQueryId();
         boolean that_present_queryId = that.isSetQueryId();
         if (this_present_queryId || that_present_queryId) {
            if (!this_present_queryId || !that_present_queryId) {
               return false;
            }

            if (!this.queryId.equals(that.queryId)) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + (this.isSetQueryId() ? 131071 : 524287);
      if (this.isSetQueryId()) {
         hashCode = hashCode * 8191 + this.queryId.hashCode();
      }

      return hashCode;
   }

   public int compareTo(TGetQueryIdResp other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetQueryId(), other.isSetQueryId());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetQueryId()) {
               lastComparison = TBaseHelper.compareTo(this.queryId, other.queryId);
               if (lastComparison != 0) {
                  return lastComparison;
               }
            }

            return 0;
         }
      }
   }

   @Nullable
   public _Fields fieldForId(int fieldId) {
      return TGetQueryIdResp._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("TGetQueryIdResp(");
      boolean first = true;
      sb.append("queryId:");
      if (this.queryId == null) {
         sb.append("null");
      } else {
         sb.append(this.queryId);
      }

      first = false;
      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (!this.isSetQueryId()) {
         throw new TProtocolException("Required field 'queryId' is unset! Struct:" + this.toString());
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
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(TGetQueryIdResp._Fields.QUERY_ID, new FieldMetaData("queryId", (byte)1, new FieldValueMetaData((byte)11)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(TGetQueryIdResp.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      QUERY_ID((short)1, "queryId");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return QUERY_ID;
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

   private static class TGetQueryIdRespStandardSchemeFactory implements SchemeFactory {
      private TGetQueryIdRespStandardSchemeFactory() {
      }

      public TGetQueryIdRespStandardScheme getScheme() {
         return new TGetQueryIdRespStandardScheme();
      }
   }

   private static class TGetQueryIdRespStandardScheme extends StandardScheme {
      private TGetQueryIdRespStandardScheme() {
      }

      public void read(TProtocol iprot, TGetQueryIdResp struct) throws TException {
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
                     struct.queryId = iprot.readString();
                     struct.setQueryIdIsSet(true);
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

      public void write(TProtocol oprot, TGetQueryIdResp struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(TGetQueryIdResp.STRUCT_DESC);
         if (struct.queryId != null) {
            oprot.writeFieldBegin(TGetQueryIdResp.QUERY_ID_FIELD_DESC);
            oprot.writeString(struct.queryId);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class TGetQueryIdRespTupleSchemeFactory implements SchemeFactory {
      private TGetQueryIdRespTupleSchemeFactory() {
      }

      public TGetQueryIdRespTupleScheme getScheme() {
         return new TGetQueryIdRespTupleScheme();
      }
   }

   private static class TGetQueryIdRespTupleScheme extends TupleScheme {
      private TGetQueryIdRespTupleScheme() {
      }

      public void write(TProtocol prot, TGetQueryIdResp struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         oprot.writeString(struct.queryId);
      }

      public void read(TProtocol prot, TGetQueryIdResp struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         struct.queryId = iprot.readString();
         struct.setQueryIdIsSet(true);
      }
   }
}
