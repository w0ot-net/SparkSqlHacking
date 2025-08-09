package org.apache.hadoop.hive.metastore.api;

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

public class NotificationEventRequest implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("NotificationEventRequest");
   private static final TField LAST_EVENT_FIELD_DESC = new TField("lastEvent", (byte)10, (short)1);
   private static final TField MAX_EVENTS_FIELD_DESC = new TField("maxEvents", (byte)8, (short)2);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new NotificationEventRequestStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new NotificationEventRequestTupleSchemeFactory();
   private long lastEvent;
   private int maxEvents;
   private static final int __LASTEVENT_ISSET_ID = 0;
   private static final int __MAXEVENTS_ISSET_ID = 1;
   private byte __isset_bitfield;
   private static final _Fields[] optionals;
   public static final Map metaDataMap;

   public NotificationEventRequest() {
      this.__isset_bitfield = 0;
   }

   public NotificationEventRequest(long lastEvent) {
      this();
      this.lastEvent = lastEvent;
      this.setLastEventIsSet(true);
   }

   public NotificationEventRequest(NotificationEventRequest other) {
      this.__isset_bitfield = 0;
      this.__isset_bitfield = other.__isset_bitfield;
      this.lastEvent = other.lastEvent;
      this.maxEvents = other.maxEvents;
   }

   public NotificationEventRequest deepCopy() {
      return new NotificationEventRequest(this);
   }

   public void clear() {
      this.setLastEventIsSet(false);
      this.lastEvent = 0L;
      this.setMaxEventsIsSet(false);
      this.maxEvents = 0;
   }

   public long getLastEvent() {
      return this.lastEvent;
   }

   public void setLastEvent(long lastEvent) {
      this.lastEvent = lastEvent;
      this.setLastEventIsSet(true);
   }

   public void unsetLastEvent() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 0);
   }

   public boolean isSetLastEvent() {
      return EncodingUtils.testBit(this.__isset_bitfield, 0);
   }

   public void setLastEventIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 0, value);
   }

   public int getMaxEvents() {
      return this.maxEvents;
   }

   public void setMaxEvents(int maxEvents) {
      this.maxEvents = maxEvents;
      this.setMaxEventsIsSet(true);
   }

   public void unsetMaxEvents() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 1);
   }

   public boolean isSetMaxEvents() {
      return EncodingUtils.testBit(this.__isset_bitfield, 1);
   }

   public void setMaxEventsIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 1, value);
   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case LAST_EVENT:
            if (value == null) {
               this.unsetLastEvent();
            } else {
               this.setLastEvent((Long)value);
            }
            break;
         case MAX_EVENTS:
            if (value == null) {
               this.unsetMaxEvents();
            } else {
               this.setMaxEvents((Integer)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case LAST_EVENT:
            return this.getLastEvent();
         case MAX_EVENTS:
            return this.getMaxEvents();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case LAST_EVENT:
               return this.isSetLastEvent();
            case MAX_EVENTS:
               return this.isSetMaxEvents();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof NotificationEventRequest ? this.equals((NotificationEventRequest)that) : false;
   }

   public boolean equals(NotificationEventRequest that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_lastEvent = true;
         boolean that_present_lastEvent = true;
         if (this_present_lastEvent || that_present_lastEvent) {
            if (!this_present_lastEvent || !that_present_lastEvent) {
               return false;
            }

            if (this.lastEvent != that.lastEvent) {
               return false;
            }
         }

         boolean this_present_maxEvents = this.isSetMaxEvents();
         boolean that_present_maxEvents = that.isSetMaxEvents();
         if (this_present_maxEvents || that_present_maxEvents) {
            if (!this_present_maxEvents || !that_present_maxEvents) {
               return false;
            }

            if (this.maxEvents != that.maxEvents) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + TBaseHelper.hashCode(this.lastEvent);
      hashCode = hashCode * 8191 + (this.isSetMaxEvents() ? 131071 : 524287);
      if (this.isSetMaxEvents()) {
         hashCode = hashCode * 8191 + this.maxEvents;
      }

      return hashCode;
   }

   public int compareTo(NotificationEventRequest other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetLastEvent(), other.isSetLastEvent());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetLastEvent()) {
               lastComparison = TBaseHelper.compareTo(this.lastEvent, other.lastEvent);
               if (lastComparison != 0) {
                  return lastComparison;
               }
            }

            lastComparison = Boolean.compare(this.isSetMaxEvents(), other.isSetMaxEvents());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetMaxEvents()) {
                  lastComparison = TBaseHelper.compareTo(this.maxEvents, other.maxEvents);
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
      return NotificationEventRequest._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("NotificationEventRequest(");
      boolean first = true;
      sb.append("lastEvent:");
      sb.append(this.lastEvent);
      first = false;
      if (this.isSetMaxEvents()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("maxEvents:");
         sb.append(this.maxEvents);
         first = false;
      }

      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (!this.isSetLastEvent()) {
         throw new TProtocolException("Required field 'lastEvent' is unset! Struct:" + this.toString());
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
      optionals = new _Fields[]{NotificationEventRequest._Fields.MAX_EVENTS};
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(NotificationEventRequest._Fields.LAST_EVENT, new FieldMetaData("lastEvent", (byte)1, new FieldValueMetaData((byte)10)));
      tmpMap.put(NotificationEventRequest._Fields.MAX_EVENTS, new FieldMetaData("maxEvents", (byte)2, new FieldValueMetaData((byte)8)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(NotificationEventRequest.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      LAST_EVENT((short)1, "lastEvent"),
      MAX_EVENTS((short)2, "maxEvents");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return LAST_EVENT;
            case 2:
               return MAX_EVENTS;
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

   private static class NotificationEventRequestStandardSchemeFactory implements SchemeFactory {
      private NotificationEventRequestStandardSchemeFactory() {
      }

      public NotificationEventRequestStandardScheme getScheme() {
         return new NotificationEventRequestStandardScheme();
      }
   }

   private static class NotificationEventRequestStandardScheme extends StandardScheme {
      private NotificationEventRequestStandardScheme() {
      }

      public void read(TProtocol iprot, NotificationEventRequest struct) throws TException {
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
                     struct.lastEvent = iprot.readI64();
                     struct.setLastEventIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 2:
                  if (schemeField.type == 8) {
                     struct.maxEvents = iprot.readI32();
                     struct.setMaxEventsIsSet(true);
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

      public void write(TProtocol oprot, NotificationEventRequest struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(NotificationEventRequest.STRUCT_DESC);
         oprot.writeFieldBegin(NotificationEventRequest.LAST_EVENT_FIELD_DESC);
         oprot.writeI64(struct.lastEvent);
         oprot.writeFieldEnd();
         if (struct.isSetMaxEvents()) {
            oprot.writeFieldBegin(NotificationEventRequest.MAX_EVENTS_FIELD_DESC);
            oprot.writeI32(struct.maxEvents);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class NotificationEventRequestTupleSchemeFactory implements SchemeFactory {
      private NotificationEventRequestTupleSchemeFactory() {
      }

      public NotificationEventRequestTupleScheme getScheme() {
         return new NotificationEventRequestTupleScheme();
      }
   }

   private static class NotificationEventRequestTupleScheme extends TupleScheme {
      private NotificationEventRequestTupleScheme() {
      }

      public void write(TProtocol prot, NotificationEventRequest struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         oprot.writeI64(struct.lastEvent);
         BitSet optionals = new BitSet();
         if (struct.isSetMaxEvents()) {
            optionals.set(0);
         }

         oprot.writeBitSet(optionals, 1);
         if (struct.isSetMaxEvents()) {
            oprot.writeI32(struct.maxEvents);
         }

      }

      public void read(TProtocol prot, NotificationEventRequest struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         struct.lastEvent = iprot.readI64();
         struct.setLastEventIsSet(true);
         BitSet incoming = iprot.readBitSet(1);
         if (incoming.get(0)) {
            struct.maxEvents = iprot.readI32();
            struct.setMaxEventsIsSet(true);
         }

      }
   }
}
