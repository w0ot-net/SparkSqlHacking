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

public class CurrentNotificationEventId implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("CurrentNotificationEventId");
   private static final TField EVENT_ID_FIELD_DESC = new TField("eventId", (byte)10, (short)1);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new CurrentNotificationEventIdStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new CurrentNotificationEventIdTupleSchemeFactory();
   private long eventId;
   private static final int __EVENTID_ISSET_ID = 0;
   private byte __isset_bitfield;
   public static final Map metaDataMap;

   public CurrentNotificationEventId() {
      this.__isset_bitfield = 0;
   }

   public CurrentNotificationEventId(long eventId) {
      this();
      this.eventId = eventId;
      this.setEventIdIsSet(true);
   }

   public CurrentNotificationEventId(CurrentNotificationEventId other) {
      this.__isset_bitfield = 0;
      this.__isset_bitfield = other.__isset_bitfield;
      this.eventId = other.eventId;
   }

   public CurrentNotificationEventId deepCopy() {
      return new CurrentNotificationEventId(this);
   }

   public void clear() {
      this.setEventIdIsSet(false);
      this.eventId = 0L;
   }

   public long getEventId() {
      return this.eventId;
   }

   public void setEventId(long eventId) {
      this.eventId = eventId;
      this.setEventIdIsSet(true);
   }

   public void unsetEventId() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 0);
   }

   public boolean isSetEventId() {
      return EncodingUtils.testBit(this.__isset_bitfield, 0);
   }

   public void setEventIdIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 0, value);
   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case EVENT_ID:
            if (value == null) {
               this.unsetEventId();
            } else {
               this.setEventId((Long)value);
            }
         default:
      }
   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case EVENT_ID:
            return this.getEventId();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case EVENT_ID:
               return this.isSetEventId();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof CurrentNotificationEventId ? this.equals((CurrentNotificationEventId)that) : false;
   }

   public boolean equals(CurrentNotificationEventId that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_eventId = true;
         boolean that_present_eventId = true;
         if (this_present_eventId || that_present_eventId) {
            if (!this_present_eventId || !that_present_eventId) {
               return false;
            }

            if (this.eventId != that.eventId) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + TBaseHelper.hashCode(this.eventId);
      return hashCode;
   }

   public int compareTo(CurrentNotificationEventId other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetEventId(), other.isSetEventId());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetEventId()) {
               lastComparison = TBaseHelper.compareTo(this.eventId, other.eventId);
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
      return CurrentNotificationEventId._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("CurrentNotificationEventId(");
      boolean first = true;
      sb.append("eventId:");
      sb.append(this.eventId);
      first = false;
      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (!this.isSetEventId()) {
         throw new TProtocolException("Required field 'eventId' is unset! Struct:" + this.toString());
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
      tmpMap.put(CurrentNotificationEventId._Fields.EVENT_ID, new FieldMetaData("eventId", (byte)1, new FieldValueMetaData((byte)10)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(CurrentNotificationEventId.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      EVENT_ID((short)1, "eventId");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return EVENT_ID;
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

   private static class CurrentNotificationEventIdStandardSchemeFactory implements SchemeFactory {
      private CurrentNotificationEventIdStandardSchemeFactory() {
      }

      public CurrentNotificationEventIdStandardScheme getScheme() {
         return new CurrentNotificationEventIdStandardScheme();
      }
   }

   private static class CurrentNotificationEventIdStandardScheme extends StandardScheme {
      private CurrentNotificationEventIdStandardScheme() {
      }

      public void read(TProtocol iprot, CurrentNotificationEventId struct) throws TException {
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
                     struct.eventId = iprot.readI64();
                     struct.setEventIdIsSet(true);
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

      public void write(TProtocol oprot, CurrentNotificationEventId struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(CurrentNotificationEventId.STRUCT_DESC);
         oprot.writeFieldBegin(CurrentNotificationEventId.EVENT_ID_FIELD_DESC);
         oprot.writeI64(struct.eventId);
         oprot.writeFieldEnd();
         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class CurrentNotificationEventIdTupleSchemeFactory implements SchemeFactory {
      private CurrentNotificationEventIdTupleSchemeFactory() {
      }

      public CurrentNotificationEventIdTupleScheme getScheme() {
         return new CurrentNotificationEventIdTupleScheme();
      }
   }

   private static class CurrentNotificationEventIdTupleScheme extends TupleScheme {
      private CurrentNotificationEventIdTupleScheme() {
      }

      public void write(TProtocol prot, CurrentNotificationEventId struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         oprot.writeI64(struct.eventId);
      }

      public void read(TProtocol prot, CurrentNotificationEventId struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         struct.eventId = iprot.readI64();
         struct.setEventIdIsSet(true);
      }
   }
}
