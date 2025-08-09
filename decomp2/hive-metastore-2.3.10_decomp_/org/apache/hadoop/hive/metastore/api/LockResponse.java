package org.apache.hadoop.hive.metastore.api;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
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
import org.apache.thrift.meta_data.EnumMetaData;
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

public class LockResponse implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("LockResponse");
   private static final TField LOCKID_FIELD_DESC = new TField("lockid", (byte)10, (short)1);
   private static final TField STATE_FIELD_DESC = new TField("state", (byte)8, (short)2);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new LockResponseStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new LockResponseTupleSchemeFactory();
   private long lockid;
   @Nullable
   private LockState state;
   private static final int __LOCKID_ISSET_ID = 0;
   private byte __isset_bitfield;
   public static final Map metaDataMap;

   public LockResponse() {
      this.__isset_bitfield = 0;
   }

   public LockResponse(long lockid, LockState state) {
      this();
      this.lockid = lockid;
      this.setLockidIsSet(true);
      this.state = state;
   }

   public LockResponse(LockResponse other) {
      this.__isset_bitfield = 0;
      this.__isset_bitfield = other.__isset_bitfield;
      this.lockid = other.lockid;
      if (other.isSetState()) {
         this.state = other.state;
      }

   }

   public LockResponse deepCopy() {
      return new LockResponse(this);
   }

   public void clear() {
      this.setLockidIsSet(false);
      this.lockid = 0L;
      this.state = null;
   }

   public long getLockid() {
      return this.lockid;
   }

   public void setLockid(long lockid) {
      this.lockid = lockid;
      this.setLockidIsSet(true);
   }

   public void unsetLockid() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 0);
   }

   public boolean isSetLockid() {
      return EncodingUtils.testBit(this.__isset_bitfield, 0);
   }

   public void setLockidIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 0, value);
   }

   @Nullable
   public LockState getState() {
      return this.state;
   }

   public void setState(@Nullable LockState state) {
      this.state = state;
   }

   public void unsetState() {
      this.state = null;
   }

   public boolean isSetState() {
      return this.state != null;
   }

   public void setStateIsSet(boolean value) {
      if (!value) {
         this.state = null;
      }

   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case LOCKID:
            if (value == null) {
               this.unsetLockid();
            } else {
               this.setLockid((Long)value);
            }
            break;
         case STATE:
            if (value == null) {
               this.unsetState();
            } else {
               this.setState((LockState)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case LOCKID:
            return this.getLockid();
         case STATE:
            return this.getState();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case LOCKID:
               return this.isSetLockid();
            case STATE:
               return this.isSetState();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof LockResponse ? this.equals((LockResponse)that) : false;
   }

   public boolean equals(LockResponse that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_lockid = true;
         boolean that_present_lockid = true;
         if (this_present_lockid || that_present_lockid) {
            if (!this_present_lockid || !that_present_lockid) {
               return false;
            }

            if (this.lockid != that.lockid) {
               return false;
            }
         }

         boolean this_present_state = this.isSetState();
         boolean that_present_state = that.isSetState();
         if (this_present_state || that_present_state) {
            if (!this_present_state || !that_present_state) {
               return false;
            }

            if (!this.state.equals(that.state)) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + TBaseHelper.hashCode(this.lockid);
      hashCode = hashCode * 8191 + (this.isSetState() ? 131071 : 524287);
      if (this.isSetState()) {
         hashCode = hashCode * 8191 + this.state.getValue();
      }

      return hashCode;
   }

   public int compareTo(LockResponse other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetLockid(), other.isSetLockid());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetLockid()) {
               lastComparison = TBaseHelper.compareTo(this.lockid, other.lockid);
               if (lastComparison != 0) {
                  return lastComparison;
               }
            }

            lastComparison = Boolean.compare(this.isSetState(), other.isSetState());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetState()) {
                  lastComparison = TBaseHelper.compareTo(this.state, other.state);
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
      return LockResponse._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("LockResponse(");
      boolean first = true;
      sb.append("lockid:");
      sb.append(this.lockid);
      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("state:");
      if (this.state == null) {
         sb.append("null");
      } else {
         sb.append(this.state);
      }

      first = false;
      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (!this.isSetLockid()) {
         throw new TProtocolException("Required field 'lockid' is unset! Struct:" + this.toString());
      } else if (!this.isSetState()) {
         throw new TProtocolException("Required field 'state' is unset! Struct:" + this.toString());
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
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(LockResponse._Fields.LOCKID, new FieldMetaData("lockid", (byte)1, new FieldValueMetaData((byte)10)));
      tmpMap.put(LockResponse._Fields.STATE, new FieldMetaData("state", (byte)1, new EnumMetaData((byte)16, LockState.class)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(LockResponse.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      LOCKID((short)1, "lockid"),
      STATE((short)2, "state");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return LOCKID;
            case 2:
               return STATE;
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

   private static class LockResponseStandardSchemeFactory implements SchemeFactory {
      private LockResponseStandardSchemeFactory() {
      }

      public LockResponseStandardScheme getScheme() {
         return new LockResponseStandardScheme();
      }
   }

   private static class LockResponseStandardScheme extends StandardScheme {
      private LockResponseStandardScheme() {
      }

      public void read(TProtocol iprot, LockResponse struct) throws TException {
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
                  if (schemeField.type == 10) {
                     struct.lockid = iprot.readI64();
                     struct.setLockidIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 2:
                  if (schemeField.type == 8) {
                     struct.state = LockState.findByValue(iprot.readI32());
                     struct.setStateIsSet(true);
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

      public void write(TProtocol oprot, LockResponse struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(LockResponse.STRUCT_DESC);
         oprot.writeFieldBegin(LockResponse.LOCKID_FIELD_DESC);
         oprot.writeI64(struct.lockid);
         oprot.writeFieldEnd();
         if (struct.state != null) {
            oprot.writeFieldBegin(LockResponse.STATE_FIELD_DESC);
            oprot.writeI32(struct.state.getValue());
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class LockResponseTupleSchemeFactory implements SchemeFactory {
      private LockResponseTupleSchemeFactory() {
      }

      public LockResponseTupleScheme getScheme() {
         return new LockResponseTupleScheme();
      }
   }

   private static class LockResponseTupleScheme extends TupleScheme {
      private LockResponseTupleScheme() {
      }

      public void write(TProtocol prot, LockResponse struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         oprot.writeI64(struct.lockid);
         oprot.writeI32(struct.state.getValue());
      }

      public void read(TProtocol prot, LockResponse struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         struct.lockid = iprot.readI64();
         struct.setLockidIsSet(true);
         struct.state = LockState.findByValue(iprot.readI32());
         struct.setStateIsSet(true);
      }
   }
}
