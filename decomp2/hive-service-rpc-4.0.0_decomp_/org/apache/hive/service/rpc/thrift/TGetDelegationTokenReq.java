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
public class TGetDelegationTokenReq implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("TGetDelegationTokenReq");
   private static final TField SESSION_HANDLE_FIELD_DESC = new TField("sessionHandle", (byte)12, (short)1);
   private static final TField OWNER_FIELD_DESC = new TField("owner", (byte)11, (short)2);
   private static final TField RENEWER_FIELD_DESC = new TField("renewer", (byte)11, (short)3);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new TGetDelegationTokenReqStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new TGetDelegationTokenReqTupleSchemeFactory();
   @Nullable
   private TSessionHandle sessionHandle;
   @Nullable
   private String owner;
   @Nullable
   private String renewer;
   public static final Map metaDataMap;

   public TGetDelegationTokenReq() {
   }

   public TGetDelegationTokenReq(TSessionHandle sessionHandle, String owner, String renewer) {
      this();
      this.sessionHandle = sessionHandle;
      this.owner = owner;
      this.renewer = renewer;
   }

   public TGetDelegationTokenReq(TGetDelegationTokenReq other) {
      if (other.isSetSessionHandle()) {
         this.sessionHandle = new TSessionHandle(other.sessionHandle);
      }

      if (other.isSetOwner()) {
         this.owner = other.owner;
      }

      if (other.isSetRenewer()) {
         this.renewer = other.renewer;
      }

   }

   public TGetDelegationTokenReq deepCopy() {
      return new TGetDelegationTokenReq(this);
   }

   public void clear() {
      this.sessionHandle = null;
      this.owner = null;
      this.renewer = null;
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
   public String getOwner() {
      return this.owner;
   }

   public void setOwner(@Nullable String owner) {
      this.owner = owner;
   }

   public void unsetOwner() {
      this.owner = null;
   }

   public boolean isSetOwner() {
      return this.owner != null;
   }

   public void setOwnerIsSet(boolean value) {
      if (!value) {
         this.owner = null;
      }

   }

   @Nullable
   public String getRenewer() {
      return this.renewer;
   }

   public void setRenewer(@Nullable String renewer) {
      this.renewer = renewer;
   }

   public void unsetRenewer() {
      this.renewer = null;
   }

   public boolean isSetRenewer() {
      return this.renewer != null;
   }

   public void setRenewerIsSet(boolean value) {
      if (!value) {
         this.renewer = null;
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
         case OWNER:
            if (value == null) {
               this.unsetOwner();
            } else {
               this.setOwner((String)value);
            }
            break;
         case RENEWER:
            if (value == null) {
               this.unsetRenewer();
            } else {
               this.setRenewer((String)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case SESSION_HANDLE:
            return this.getSessionHandle();
         case OWNER:
            return this.getOwner();
         case RENEWER:
            return this.getRenewer();
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
            case OWNER:
               return this.isSetOwner();
            case RENEWER:
               return this.isSetRenewer();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof TGetDelegationTokenReq ? this.equals((TGetDelegationTokenReq)that) : false;
   }

   public boolean equals(TGetDelegationTokenReq that) {
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

         boolean this_present_owner = this.isSetOwner();
         boolean that_present_owner = that.isSetOwner();
         if (this_present_owner || that_present_owner) {
            if (!this_present_owner || !that_present_owner) {
               return false;
            }

            if (!this.owner.equals(that.owner)) {
               return false;
            }
         }

         boolean this_present_renewer = this.isSetRenewer();
         boolean that_present_renewer = that.isSetRenewer();
         if (this_present_renewer || that_present_renewer) {
            if (!this_present_renewer || !that_present_renewer) {
               return false;
            }

            if (!this.renewer.equals(that.renewer)) {
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

      hashCode = hashCode * 8191 + (this.isSetOwner() ? 131071 : 524287);
      if (this.isSetOwner()) {
         hashCode = hashCode * 8191 + this.owner.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetRenewer() ? 131071 : 524287);
      if (this.isSetRenewer()) {
         hashCode = hashCode * 8191 + this.renewer.hashCode();
      }

      return hashCode;
   }

   public int compareTo(TGetDelegationTokenReq other) {
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

            lastComparison = Boolean.compare(this.isSetOwner(), other.isSetOwner());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetOwner()) {
                  lastComparison = TBaseHelper.compareTo(this.owner, other.owner);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               lastComparison = Boolean.compare(this.isSetRenewer(), other.isSetRenewer());
               if (lastComparison != 0) {
                  return lastComparison;
               } else {
                  if (this.isSetRenewer()) {
                     lastComparison = TBaseHelper.compareTo(this.renewer, other.renewer);
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

   @Nullable
   public _Fields fieldForId(int fieldId) {
      return TGetDelegationTokenReq._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("TGetDelegationTokenReq(");
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

      sb.append("owner:");
      if (this.owner == null) {
         sb.append("null");
      } else {
         sb.append(this.owner);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("renewer:");
      if (this.renewer == null) {
         sb.append("null");
      } else {
         sb.append(this.renewer);
      }

      first = false;
      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (!this.isSetSessionHandle()) {
         throw new TProtocolException("Required field 'sessionHandle' is unset! Struct:" + this.toString());
      } else if (!this.isSetOwner()) {
         throw new TProtocolException("Required field 'owner' is unset! Struct:" + this.toString());
      } else if (!this.isSetRenewer()) {
         throw new TProtocolException("Required field 'renewer' is unset! Struct:" + this.toString());
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
      tmpMap.put(TGetDelegationTokenReq._Fields.SESSION_HANDLE, new FieldMetaData("sessionHandle", (byte)1, new StructMetaData((byte)12, TSessionHandle.class)));
      tmpMap.put(TGetDelegationTokenReq._Fields.OWNER, new FieldMetaData("owner", (byte)1, new FieldValueMetaData((byte)11)));
      tmpMap.put(TGetDelegationTokenReq._Fields.RENEWER, new FieldMetaData("renewer", (byte)1, new FieldValueMetaData((byte)11)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(TGetDelegationTokenReq.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      SESSION_HANDLE((short)1, "sessionHandle"),
      OWNER((short)2, "owner"),
      RENEWER((short)3, "renewer");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return SESSION_HANDLE;
            case 2:
               return OWNER;
            case 3:
               return RENEWER;
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

   private static class TGetDelegationTokenReqStandardSchemeFactory implements SchemeFactory {
      private TGetDelegationTokenReqStandardSchemeFactory() {
      }

      public TGetDelegationTokenReqStandardScheme getScheme() {
         return new TGetDelegationTokenReqStandardScheme();
      }
   }

   private static class TGetDelegationTokenReqStandardScheme extends StandardScheme {
      private TGetDelegationTokenReqStandardScheme() {
      }

      public void read(TProtocol iprot, TGetDelegationTokenReq struct) throws TException {
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
                     struct.owner = iprot.readString();
                     struct.setOwnerIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 3:
                  if (schemeField.type == 11) {
                     struct.renewer = iprot.readString();
                     struct.setRenewerIsSet(true);
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

      public void write(TProtocol oprot, TGetDelegationTokenReq struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(TGetDelegationTokenReq.STRUCT_DESC);
         if (struct.sessionHandle != null) {
            oprot.writeFieldBegin(TGetDelegationTokenReq.SESSION_HANDLE_FIELD_DESC);
            struct.sessionHandle.write(oprot);
            oprot.writeFieldEnd();
         }

         if (struct.owner != null) {
            oprot.writeFieldBegin(TGetDelegationTokenReq.OWNER_FIELD_DESC);
            oprot.writeString(struct.owner);
            oprot.writeFieldEnd();
         }

         if (struct.renewer != null) {
            oprot.writeFieldBegin(TGetDelegationTokenReq.RENEWER_FIELD_DESC);
            oprot.writeString(struct.renewer);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class TGetDelegationTokenReqTupleSchemeFactory implements SchemeFactory {
      private TGetDelegationTokenReqTupleSchemeFactory() {
      }

      public TGetDelegationTokenReqTupleScheme getScheme() {
         return new TGetDelegationTokenReqTupleScheme();
      }
   }

   private static class TGetDelegationTokenReqTupleScheme extends TupleScheme {
      private TGetDelegationTokenReqTupleScheme() {
      }

      public void write(TProtocol prot, TGetDelegationTokenReq struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         struct.sessionHandle.write(oprot);
         oprot.writeString(struct.owner);
         oprot.writeString(struct.renewer);
      }

      public void read(TProtocol prot, TGetDelegationTokenReq struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         struct.sessionHandle = new TSessionHandle();
         struct.sessionHandle.read(iprot);
         struct.setSessionHandleIsSet(true);
         struct.owner = iprot.readString();
         struct.setOwnerIsSet(true);
         struct.renewer = iprot.readString();
         struct.setRenewerIsSet(true);
      }
   }
}
