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
public class TSetClientInfoReq implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("TSetClientInfoReq");
   private static final TField SESSION_HANDLE_FIELD_DESC = new TField("sessionHandle", (byte)12, (short)1);
   private static final TField CONFIGURATION_FIELD_DESC = new TField("configuration", (byte)13, (short)2);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new TSetClientInfoReqStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new TSetClientInfoReqTupleSchemeFactory();
   @Nullable
   private TSessionHandle sessionHandle;
   @Nullable
   private Map configuration;
   private static final _Fields[] optionals;
   public static final Map metaDataMap;

   public TSetClientInfoReq() {
   }

   public TSetClientInfoReq(TSessionHandle sessionHandle) {
      this();
      this.sessionHandle = sessionHandle;
   }

   public TSetClientInfoReq(TSetClientInfoReq other) {
      if (other.isSetSessionHandle()) {
         this.sessionHandle = new TSessionHandle(other.sessionHandle);
      }

      if (other.isSetConfiguration()) {
         Map<String, String> __this__configuration = new HashMap(other.configuration);
         this.configuration = __this__configuration;
      }

   }

   public TSetClientInfoReq deepCopy() {
      return new TSetClientInfoReq(this);
   }

   public void clear() {
      this.sessionHandle = null;
      this.configuration = null;
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

   public int getConfigurationSize() {
      return this.configuration == null ? 0 : this.configuration.size();
   }

   public void putToConfiguration(String key, String val) {
      if (this.configuration == null) {
         this.configuration = new HashMap();
      }

      this.configuration.put(key, val);
   }

   @Nullable
   public Map getConfiguration() {
      return this.configuration;
   }

   public void setConfiguration(@Nullable Map configuration) {
      this.configuration = configuration;
   }

   public void unsetConfiguration() {
      this.configuration = null;
   }

   public boolean isSetConfiguration() {
      return this.configuration != null;
   }

   public void setConfigurationIsSet(boolean value) {
      if (!value) {
         this.configuration = null;
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
         case CONFIGURATION:
            if (value == null) {
               this.unsetConfiguration();
            } else {
               this.setConfiguration((Map)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case SESSION_HANDLE:
            return this.getSessionHandle();
         case CONFIGURATION:
            return this.getConfiguration();
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
            case CONFIGURATION:
               return this.isSetConfiguration();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof TSetClientInfoReq ? this.equals((TSetClientInfoReq)that) : false;
   }

   public boolean equals(TSetClientInfoReq that) {
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

         boolean this_present_configuration = this.isSetConfiguration();
         boolean that_present_configuration = that.isSetConfiguration();
         if (this_present_configuration || that_present_configuration) {
            if (!this_present_configuration || !that_present_configuration) {
               return false;
            }

            if (!this.configuration.equals(that.configuration)) {
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

      hashCode = hashCode * 8191 + (this.isSetConfiguration() ? 131071 : 524287);
      if (this.isSetConfiguration()) {
         hashCode = hashCode * 8191 + this.configuration.hashCode();
      }

      return hashCode;
   }

   public int compareTo(TSetClientInfoReq other) {
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

            lastComparison = Boolean.compare(this.isSetConfiguration(), other.isSetConfiguration());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetConfiguration()) {
                  lastComparison = TBaseHelper.compareTo(this.configuration, other.configuration);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               return 0;
            }
         }
      }
   }

   @Nullable
   public _Fields fieldForId(int fieldId) {
      return TSetClientInfoReq._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("TSetClientInfoReq(");
      boolean first = true;
      sb.append("sessionHandle:");
      if (this.sessionHandle == null) {
         sb.append("null");
      } else {
         sb.append(this.sessionHandle);
      }

      first = false;
      if (this.isSetConfiguration()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("configuration:");
         if (this.configuration == null) {
            sb.append("null");
         } else {
            sb.append(this.configuration);
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
      optionals = new _Fields[]{TSetClientInfoReq._Fields.CONFIGURATION};
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(TSetClientInfoReq._Fields.SESSION_HANDLE, new FieldMetaData("sessionHandle", (byte)1, new StructMetaData((byte)12, TSessionHandle.class)));
      tmpMap.put(TSetClientInfoReq._Fields.CONFIGURATION, new FieldMetaData("configuration", (byte)2, new MapMetaData((byte)13, new FieldValueMetaData((byte)11), new FieldValueMetaData((byte)11))));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(TSetClientInfoReq.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      SESSION_HANDLE((short)1, "sessionHandle"),
      CONFIGURATION((short)2, "configuration");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return SESSION_HANDLE;
            case 2:
               return CONFIGURATION;
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

   private static class TSetClientInfoReqStandardSchemeFactory implements SchemeFactory {
      private TSetClientInfoReqStandardSchemeFactory() {
      }

      public TSetClientInfoReqStandardScheme getScheme() {
         return new TSetClientInfoReqStandardScheme();
      }
   }

   private static class TSetClientInfoReqStandardScheme extends StandardScheme {
      private TSetClientInfoReqStandardScheme() {
      }

      public void read(TProtocol iprot, TSetClientInfoReq struct) throws TException {
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
                  if (schemeField.type != 13) {
                     TProtocolUtil.skip(iprot, schemeField.type);
                     break;
                  }

                  TMap _map162 = iprot.readMapBegin();
                  struct.configuration = new HashMap(2 * _map162.size);

                  for(int _i165 = 0; _i165 < _map162.size; ++_i165) {
                     String _key163 = iprot.readString();
                     String _val164 = iprot.readString();
                     struct.configuration.put(_key163, _val164);
                  }

                  iprot.readMapEnd();
                  struct.setConfigurationIsSet(true);
                  break;
               default:
                  TProtocolUtil.skip(iprot, schemeField.type);
            }

            iprot.readFieldEnd();
         }
      }

      public void write(TProtocol oprot, TSetClientInfoReq struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(TSetClientInfoReq.STRUCT_DESC);
         if (struct.sessionHandle != null) {
            oprot.writeFieldBegin(TSetClientInfoReq.SESSION_HANDLE_FIELD_DESC);
            struct.sessionHandle.write(oprot);
            oprot.writeFieldEnd();
         }

         if (struct.configuration != null && struct.isSetConfiguration()) {
            oprot.writeFieldBegin(TSetClientInfoReq.CONFIGURATION_FIELD_DESC);
            oprot.writeMapBegin(new TMap((byte)11, (byte)11, struct.configuration.size()));

            for(Map.Entry _iter166 : struct.configuration.entrySet()) {
               oprot.writeString((String)_iter166.getKey());
               oprot.writeString((String)_iter166.getValue());
            }

            oprot.writeMapEnd();
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class TSetClientInfoReqTupleSchemeFactory implements SchemeFactory {
      private TSetClientInfoReqTupleSchemeFactory() {
      }

      public TSetClientInfoReqTupleScheme getScheme() {
         return new TSetClientInfoReqTupleScheme();
      }
   }

   private static class TSetClientInfoReqTupleScheme extends TupleScheme {
      private TSetClientInfoReqTupleScheme() {
      }

      public void write(TProtocol prot, TSetClientInfoReq struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         struct.sessionHandle.write(oprot);
         BitSet optionals = new BitSet();
         if (struct.isSetConfiguration()) {
            optionals.set(0);
         }

         oprot.writeBitSet(optionals, 1);
         if (struct.isSetConfiguration()) {
            oprot.writeI32(struct.configuration.size());

            for(Map.Entry _iter167 : struct.configuration.entrySet()) {
               oprot.writeString((String)_iter167.getKey());
               oprot.writeString((String)_iter167.getValue());
            }
         }

      }

      public void read(TProtocol prot, TSetClientInfoReq struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         struct.sessionHandle = new TSessionHandle();
         struct.sessionHandle.read(iprot);
         struct.setSessionHandleIsSet(true);
         BitSet incoming = iprot.readBitSet(1);
         if (incoming.get(0)) {
            TMap _map168 = iprot.readMapBegin((byte)11, (byte)11);
            struct.configuration = new HashMap(2 * _map168.size);

            for(int _i171 = 0; _i171 < _map168.size; ++_i171) {
               String _key169 = iprot.readString();
               String _val170 = iprot.readString();
               struct.configuration.put(_key169, _val170);
            }

            struct.setConfigurationIsSet(true);
         }

      }
   }
}
