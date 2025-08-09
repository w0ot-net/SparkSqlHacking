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
import org.apache.thrift.meta_data.EnumMetaData;
import org.apache.thrift.meta_data.FieldMetaData;
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
public class TGetInfoReq implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("TGetInfoReq");
   private static final TField SESSION_HANDLE_FIELD_DESC = new TField("sessionHandle", (byte)12, (short)1);
   private static final TField INFO_TYPE_FIELD_DESC = new TField("infoType", (byte)8, (short)2);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new TGetInfoReqStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new TGetInfoReqTupleSchemeFactory();
   @Nullable
   private TSessionHandle sessionHandle;
   @Nullable
   private TGetInfoType infoType;
   public static final Map metaDataMap;

   public TGetInfoReq() {
   }

   public TGetInfoReq(TSessionHandle sessionHandle, TGetInfoType infoType) {
      this();
      this.sessionHandle = sessionHandle;
      this.infoType = infoType;
   }

   public TGetInfoReq(TGetInfoReq other) {
      if (other.isSetSessionHandle()) {
         this.sessionHandle = new TSessionHandle(other.sessionHandle);
      }

      if (other.isSetInfoType()) {
         this.infoType = other.infoType;
      }

   }

   public TGetInfoReq deepCopy() {
      return new TGetInfoReq(this);
   }

   public void clear() {
      this.sessionHandle = null;
      this.infoType = null;
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
   public TGetInfoType getInfoType() {
      return this.infoType;
   }

   public void setInfoType(@Nullable TGetInfoType infoType) {
      this.infoType = infoType;
   }

   public void unsetInfoType() {
      this.infoType = null;
   }

   public boolean isSetInfoType() {
      return this.infoType != null;
   }

   public void setInfoTypeIsSet(boolean value) {
      if (!value) {
         this.infoType = null;
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
         case INFO_TYPE:
            if (value == null) {
               this.unsetInfoType();
            } else {
               this.setInfoType((TGetInfoType)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case SESSION_HANDLE:
            return this.getSessionHandle();
         case INFO_TYPE:
            return this.getInfoType();
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
            case INFO_TYPE:
               return this.isSetInfoType();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof TGetInfoReq ? this.equals((TGetInfoReq)that) : false;
   }

   public boolean equals(TGetInfoReq that) {
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

         boolean this_present_infoType = this.isSetInfoType();
         boolean that_present_infoType = that.isSetInfoType();
         if (this_present_infoType || that_present_infoType) {
            if (!this_present_infoType || !that_present_infoType) {
               return false;
            }

            if (!this.infoType.equals(that.infoType)) {
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

      hashCode = hashCode * 8191 + (this.isSetInfoType() ? 131071 : 524287);
      if (this.isSetInfoType()) {
         hashCode = hashCode * 8191 + this.infoType.getValue();
      }

      return hashCode;
   }

   public int compareTo(TGetInfoReq other) {
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

            lastComparison = Boolean.compare(this.isSetInfoType(), other.isSetInfoType());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetInfoType()) {
                  lastComparison = TBaseHelper.compareTo(this.infoType, other.infoType);
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
      return TGetInfoReq._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("TGetInfoReq(");
      boolean first = true;
      sb.append("sessionHandle:");
      if (this.sessionHandle == null) {
         sb.append("null");
      } else {
         sb.append(this.sessionHandle);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("infoType:");
      if (this.infoType == null) {
         sb.append("null");
      } else {
         sb.append(this.infoType);
      }

      first = false;
      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (!this.isSetSessionHandle()) {
         throw new TProtocolException("Required field 'sessionHandle' is unset! Struct:" + this.toString());
      } else if (!this.isSetInfoType()) {
         throw new TProtocolException("Required field 'infoType' is unset! Struct:" + this.toString());
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
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(TGetInfoReq._Fields.SESSION_HANDLE, new FieldMetaData("sessionHandle", (byte)1, new StructMetaData((byte)12, TSessionHandle.class)));
      tmpMap.put(TGetInfoReq._Fields.INFO_TYPE, new FieldMetaData("infoType", (byte)1, new EnumMetaData((byte)16, TGetInfoType.class)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(TGetInfoReq.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      SESSION_HANDLE((short)1, "sessionHandle"),
      INFO_TYPE((short)2, "infoType");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return SESSION_HANDLE;
            case 2:
               return INFO_TYPE;
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

   private static class TGetInfoReqStandardSchemeFactory implements SchemeFactory {
      private TGetInfoReqStandardSchemeFactory() {
      }

      public TGetInfoReqStandardScheme getScheme() {
         return new TGetInfoReqStandardScheme();
      }
   }

   private static class TGetInfoReqStandardScheme extends StandardScheme {
      private TGetInfoReqStandardScheme() {
      }

      public void read(TProtocol iprot, TGetInfoReq struct) throws TException {
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
                  if (schemeField.type == 8) {
                     struct.infoType = TGetInfoType.findByValue(iprot.readI32());
                     struct.setInfoTypeIsSet(true);
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

      public void write(TProtocol oprot, TGetInfoReq struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(TGetInfoReq.STRUCT_DESC);
         if (struct.sessionHandle != null) {
            oprot.writeFieldBegin(TGetInfoReq.SESSION_HANDLE_FIELD_DESC);
            struct.sessionHandle.write(oprot);
            oprot.writeFieldEnd();
         }

         if (struct.infoType != null) {
            oprot.writeFieldBegin(TGetInfoReq.INFO_TYPE_FIELD_DESC);
            oprot.writeI32(struct.infoType.getValue());
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class TGetInfoReqTupleSchemeFactory implements SchemeFactory {
      private TGetInfoReqTupleSchemeFactory() {
      }

      public TGetInfoReqTupleScheme getScheme() {
         return new TGetInfoReqTupleScheme();
      }
   }

   private static class TGetInfoReqTupleScheme extends TupleScheme {
      private TGetInfoReqTupleScheme() {
      }

      public void write(TProtocol prot, TGetInfoReq struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         struct.sessionHandle.write(oprot);
         oprot.writeI32(struct.infoType.getValue());
      }

      public void read(TProtocol prot, TGetInfoReq struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         struct.sessionHandle = new TSessionHandle();
         struct.sessionHandle.read(iprot);
         struct.setSessionHandleIsSet(true);
         struct.infoType = TGetInfoType.findByValue(iprot.readI32());
         struct.setInfoTypeIsSet(true);
      }
   }
}
