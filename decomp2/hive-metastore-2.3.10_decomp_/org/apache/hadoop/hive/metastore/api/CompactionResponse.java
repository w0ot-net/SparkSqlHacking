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

public class CompactionResponse implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("CompactionResponse");
   private static final TField ID_FIELD_DESC = new TField("id", (byte)10, (short)1);
   private static final TField STATE_FIELD_DESC = new TField("state", (byte)11, (short)2);
   private static final TField ACCEPTED_FIELD_DESC = new TField("accepted", (byte)2, (short)3);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new CompactionResponseStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new CompactionResponseTupleSchemeFactory();
   private long id;
   @Nullable
   private String state;
   private boolean accepted;
   private static final int __ID_ISSET_ID = 0;
   private static final int __ACCEPTED_ISSET_ID = 1;
   private byte __isset_bitfield;
   public static final Map metaDataMap;

   public CompactionResponse() {
      this.__isset_bitfield = 0;
   }

   public CompactionResponse(long id, String state, boolean accepted) {
      this();
      this.id = id;
      this.setIdIsSet(true);
      this.state = state;
      this.accepted = accepted;
      this.setAcceptedIsSet(true);
   }

   public CompactionResponse(CompactionResponse other) {
      this.__isset_bitfield = 0;
      this.__isset_bitfield = other.__isset_bitfield;
      this.id = other.id;
      if (other.isSetState()) {
         this.state = other.state;
      }

      this.accepted = other.accepted;
   }

   public CompactionResponse deepCopy() {
      return new CompactionResponse(this);
   }

   public void clear() {
      this.setIdIsSet(false);
      this.id = 0L;
      this.state = null;
      this.setAcceptedIsSet(false);
      this.accepted = false;
   }

   public long getId() {
      return this.id;
   }

   public void setId(long id) {
      this.id = id;
      this.setIdIsSet(true);
   }

   public void unsetId() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 0);
   }

   public boolean isSetId() {
      return EncodingUtils.testBit(this.__isset_bitfield, 0);
   }

   public void setIdIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 0, value);
   }

   @Nullable
   public String getState() {
      return this.state;
   }

   public void setState(@Nullable String state) {
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

   public boolean isAccepted() {
      return this.accepted;
   }

   public void setAccepted(boolean accepted) {
      this.accepted = accepted;
      this.setAcceptedIsSet(true);
   }

   public void unsetAccepted() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 1);
   }

   public boolean isSetAccepted() {
      return EncodingUtils.testBit(this.__isset_bitfield, 1);
   }

   public void setAcceptedIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 1, value);
   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case ID:
            if (value == null) {
               this.unsetId();
            } else {
               this.setId((Long)value);
            }
            break;
         case STATE:
            if (value == null) {
               this.unsetState();
            } else {
               this.setState((String)value);
            }
            break;
         case ACCEPTED:
            if (value == null) {
               this.unsetAccepted();
            } else {
               this.setAccepted((Boolean)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case ID:
            return this.getId();
         case STATE:
            return this.getState();
         case ACCEPTED:
            return this.isAccepted();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case ID:
               return this.isSetId();
            case STATE:
               return this.isSetState();
            case ACCEPTED:
               return this.isSetAccepted();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof CompactionResponse ? this.equals((CompactionResponse)that) : false;
   }

   public boolean equals(CompactionResponse that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_id = true;
         boolean that_present_id = true;
         if (this_present_id || that_present_id) {
            if (!this_present_id || !that_present_id) {
               return false;
            }

            if (this.id != that.id) {
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

         boolean this_present_accepted = true;
         boolean that_present_accepted = true;
         if (this_present_accepted || that_present_accepted) {
            if (!this_present_accepted || !that_present_accepted) {
               return false;
            }

            if (this.accepted != that.accepted) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + TBaseHelper.hashCode(this.id);
      hashCode = hashCode * 8191 + (this.isSetState() ? 131071 : 524287);
      if (this.isSetState()) {
         hashCode = hashCode * 8191 + this.state.hashCode();
      }

      hashCode = hashCode * 8191 + (this.accepted ? 131071 : 524287);
      return hashCode;
   }

   public int compareTo(CompactionResponse other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetId(), other.isSetId());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetId()) {
               lastComparison = TBaseHelper.compareTo(this.id, other.id);
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

               lastComparison = Boolean.compare(this.isSetAccepted(), other.isSetAccepted());
               if (lastComparison != 0) {
                  return lastComparison;
               } else {
                  if (this.isSetAccepted()) {
                     lastComparison = TBaseHelper.compareTo(this.accepted, other.accepted);
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
      return CompactionResponse._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("CompactionResponse(");
      boolean first = true;
      sb.append("id:");
      sb.append(this.id);
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
      if (!first) {
         sb.append(", ");
      }

      sb.append("accepted:");
      sb.append(this.accepted);
      first = false;
      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (!this.isSetId()) {
         throw new TProtocolException("Required field 'id' is unset! Struct:" + this.toString());
      } else if (!this.isSetState()) {
         throw new TProtocolException("Required field 'state' is unset! Struct:" + this.toString());
      } else if (!this.isSetAccepted()) {
         throw new TProtocolException("Required field 'accepted' is unset! Struct:" + this.toString());
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
      tmpMap.put(CompactionResponse._Fields.ID, new FieldMetaData("id", (byte)1, new FieldValueMetaData((byte)10)));
      tmpMap.put(CompactionResponse._Fields.STATE, new FieldMetaData("state", (byte)1, new FieldValueMetaData((byte)11)));
      tmpMap.put(CompactionResponse._Fields.ACCEPTED, new FieldMetaData("accepted", (byte)1, new FieldValueMetaData((byte)2)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(CompactionResponse.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      ID((short)1, "id"),
      STATE((short)2, "state"),
      ACCEPTED((short)3, "accepted");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return ID;
            case 2:
               return STATE;
            case 3:
               return ACCEPTED;
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

   private static class CompactionResponseStandardSchemeFactory implements SchemeFactory {
      private CompactionResponseStandardSchemeFactory() {
      }

      public CompactionResponseStandardScheme getScheme() {
         return new CompactionResponseStandardScheme();
      }
   }

   private static class CompactionResponseStandardScheme extends StandardScheme {
      private CompactionResponseStandardScheme() {
      }

      public void read(TProtocol iprot, CompactionResponse struct) throws TException {
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
                     struct.id = iprot.readI64();
                     struct.setIdIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 2:
                  if (schemeField.type == 11) {
                     struct.state = iprot.readString();
                     struct.setStateIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 3:
                  if (schemeField.type == 2) {
                     struct.accepted = iprot.readBool();
                     struct.setAcceptedIsSet(true);
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

      public void write(TProtocol oprot, CompactionResponse struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(CompactionResponse.STRUCT_DESC);
         oprot.writeFieldBegin(CompactionResponse.ID_FIELD_DESC);
         oprot.writeI64(struct.id);
         oprot.writeFieldEnd();
         if (struct.state != null) {
            oprot.writeFieldBegin(CompactionResponse.STATE_FIELD_DESC);
            oprot.writeString(struct.state);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldBegin(CompactionResponse.ACCEPTED_FIELD_DESC);
         oprot.writeBool(struct.accepted);
         oprot.writeFieldEnd();
         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class CompactionResponseTupleSchemeFactory implements SchemeFactory {
      private CompactionResponseTupleSchemeFactory() {
      }

      public CompactionResponseTupleScheme getScheme() {
         return new CompactionResponseTupleScheme();
      }
   }

   private static class CompactionResponseTupleScheme extends TupleScheme {
      private CompactionResponseTupleScheme() {
      }

      public void write(TProtocol prot, CompactionResponse struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         oprot.writeI64(struct.id);
         oprot.writeString(struct.state);
         oprot.writeBool(struct.accepted);
      }

      public void read(TProtocol prot, CompactionResponse struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         struct.id = iprot.readI64();
         struct.setIdIsSet(true);
         struct.state = iprot.readString();
         struct.setStateIsSet(true);
         struct.accepted = iprot.readBool();
         struct.setAcceptedIsSet(true);
      }
   }
}
