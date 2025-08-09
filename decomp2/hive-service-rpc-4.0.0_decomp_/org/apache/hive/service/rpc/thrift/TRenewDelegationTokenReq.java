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
public class TRenewDelegationTokenReq implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("TRenewDelegationTokenReq");
   private static final TField SESSION_HANDLE_FIELD_DESC = new TField("sessionHandle", (byte)12, (short)1);
   private static final TField DELEGATION_TOKEN_FIELD_DESC = new TField("delegationToken", (byte)11, (short)2);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new TRenewDelegationTokenReqStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new TRenewDelegationTokenReqTupleSchemeFactory();
   @Nullable
   private TSessionHandle sessionHandle;
   @Nullable
   private String delegationToken;
   public static final Map metaDataMap;

   public TRenewDelegationTokenReq() {
   }

   public TRenewDelegationTokenReq(TSessionHandle sessionHandle, String delegationToken) {
      this();
      this.sessionHandle = sessionHandle;
      this.delegationToken = delegationToken;
   }

   public TRenewDelegationTokenReq(TRenewDelegationTokenReq other) {
      if (other.isSetSessionHandle()) {
         this.sessionHandle = new TSessionHandle(other.sessionHandle);
      }

      if (other.isSetDelegationToken()) {
         this.delegationToken = other.delegationToken;
      }

   }

   public TRenewDelegationTokenReq deepCopy() {
      return new TRenewDelegationTokenReq(this);
   }

   public void clear() {
      this.sessionHandle = null;
      this.delegationToken = null;
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
   public String getDelegationToken() {
      return this.delegationToken;
   }

   public void setDelegationToken(@Nullable String delegationToken) {
      this.delegationToken = delegationToken;
   }

   public void unsetDelegationToken() {
      this.delegationToken = null;
   }

   public boolean isSetDelegationToken() {
      return this.delegationToken != null;
   }

   public void setDelegationTokenIsSet(boolean value) {
      if (!value) {
         this.delegationToken = null;
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
         case DELEGATION_TOKEN:
            if (value == null) {
               this.unsetDelegationToken();
            } else {
               this.setDelegationToken((String)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case SESSION_HANDLE:
            return this.getSessionHandle();
         case DELEGATION_TOKEN:
            return this.getDelegationToken();
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
            case DELEGATION_TOKEN:
               return this.isSetDelegationToken();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof TRenewDelegationTokenReq ? this.equals((TRenewDelegationTokenReq)that) : false;
   }

   public boolean equals(TRenewDelegationTokenReq that) {
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

         boolean this_present_delegationToken = this.isSetDelegationToken();
         boolean that_present_delegationToken = that.isSetDelegationToken();
         if (this_present_delegationToken || that_present_delegationToken) {
            if (!this_present_delegationToken || !that_present_delegationToken) {
               return false;
            }

            if (!this.delegationToken.equals(that.delegationToken)) {
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

      hashCode = hashCode * 8191 + (this.isSetDelegationToken() ? 131071 : 524287);
      if (this.isSetDelegationToken()) {
         hashCode = hashCode * 8191 + this.delegationToken.hashCode();
      }

      return hashCode;
   }

   public int compareTo(TRenewDelegationTokenReq other) {
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

            lastComparison = Boolean.compare(this.isSetDelegationToken(), other.isSetDelegationToken());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetDelegationToken()) {
                  lastComparison = TBaseHelper.compareTo(this.delegationToken, other.delegationToken);
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
      return TRenewDelegationTokenReq._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("TRenewDelegationTokenReq(");
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

      sb.append("delegationToken:");
      if (this.delegationToken == null) {
         sb.append("null");
      } else {
         sb.append(this.delegationToken);
      }

      first = false;
      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (!this.isSetSessionHandle()) {
         throw new TProtocolException("Required field 'sessionHandle' is unset! Struct:" + this.toString());
      } else if (!this.isSetDelegationToken()) {
         throw new TProtocolException("Required field 'delegationToken' is unset! Struct:" + this.toString());
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
      tmpMap.put(TRenewDelegationTokenReq._Fields.SESSION_HANDLE, new FieldMetaData("sessionHandle", (byte)1, new StructMetaData((byte)12, TSessionHandle.class)));
      tmpMap.put(TRenewDelegationTokenReq._Fields.DELEGATION_TOKEN, new FieldMetaData("delegationToken", (byte)1, new FieldValueMetaData((byte)11)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(TRenewDelegationTokenReq.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      SESSION_HANDLE((short)1, "sessionHandle"),
      DELEGATION_TOKEN((short)2, "delegationToken");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return SESSION_HANDLE;
            case 2:
               return DELEGATION_TOKEN;
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

   private static class TRenewDelegationTokenReqStandardSchemeFactory implements SchemeFactory {
      private TRenewDelegationTokenReqStandardSchemeFactory() {
      }

      public TRenewDelegationTokenReqStandardScheme getScheme() {
         return new TRenewDelegationTokenReqStandardScheme();
      }
   }

   private static class TRenewDelegationTokenReqStandardScheme extends StandardScheme {
      private TRenewDelegationTokenReqStandardScheme() {
      }

      public void read(TProtocol iprot, TRenewDelegationTokenReq struct) throws TException {
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
                     struct.delegationToken = iprot.readString();
                     struct.setDelegationTokenIsSet(true);
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

      public void write(TProtocol oprot, TRenewDelegationTokenReq struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(TRenewDelegationTokenReq.STRUCT_DESC);
         if (struct.sessionHandle != null) {
            oprot.writeFieldBegin(TRenewDelegationTokenReq.SESSION_HANDLE_FIELD_DESC);
            struct.sessionHandle.write(oprot);
            oprot.writeFieldEnd();
         }

         if (struct.delegationToken != null) {
            oprot.writeFieldBegin(TRenewDelegationTokenReq.DELEGATION_TOKEN_FIELD_DESC);
            oprot.writeString(struct.delegationToken);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class TRenewDelegationTokenReqTupleSchemeFactory implements SchemeFactory {
      private TRenewDelegationTokenReqTupleSchemeFactory() {
      }

      public TRenewDelegationTokenReqTupleScheme getScheme() {
         return new TRenewDelegationTokenReqTupleScheme();
      }
   }

   private static class TRenewDelegationTokenReqTupleScheme extends TupleScheme {
      private TRenewDelegationTokenReqTupleScheme() {
      }

      public void write(TProtocol prot, TRenewDelegationTokenReq struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         struct.sessionHandle.write(oprot);
         oprot.writeString(struct.delegationToken);
      }

      public void read(TProtocol prot, TRenewDelegationTokenReq struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         struct.sessionHandle = new TSessionHandle();
         struct.sessionHandle.read(iprot);
         struct.setSessionHandleIsSet(true);
         struct.delegationToken = iprot.readString();
         struct.setDelegationTokenIsSet(true);
      }
   }
}
