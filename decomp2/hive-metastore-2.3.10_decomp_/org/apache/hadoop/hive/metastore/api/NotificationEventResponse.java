package org.apache.hadoop.hive.metastore.api;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.thrift.TBase;
import org.apache.thrift.TBaseHelper;
import org.apache.thrift.TException;
import org.apache.thrift.TFieldIdEnum;
import org.apache.thrift.annotation.Nullable;
import org.apache.thrift.meta_data.FieldMetaData;
import org.apache.thrift.meta_data.ListMetaData;
import org.apache.thrift.meta_data.StructMetaData;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TField;
import org.apache.thrift.protocol.TList;
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

public class NotificationEventResponse implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("NotificationEventResponse");
   private static final TField EVENTS_FIELD_DESC = new TField("events", (byte)15, (short)1);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new NotificationEventResponseStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new NotificationEventResponseTupleSchemeFactory();
   @Nullable
   private List events;
   public static final Map metaDataMap;

   public NotificationEventResponse() {
   }

   public NotificationEventResponse(List events) {
      this();
      this.events = events;
   }

   public NotificationEventResponse(NotificationEventResponse other) {
      if (other.isSetEvents()) {
         List<NotificationEvent> __this__events = new ArrayList(other.events.size());

         for(NotificationEvent other_element : other.events) {
            __this__events.add(new NotificationEvent(other_element));
         }

         this.events = __this__events;
      }

   }

   public NotificationEventResponse deepCopy() {
      return new NotificationEventResponse(this);
   }

   public void clear() {
      this.events = null;
   }

   public int getEventsSize() {
      return this.events == null ? 0 : this.events.size();
   }

   @Nullable
   public Iterator getEventsIterator() {
      return this.events == null ? null : this.events.iterator();
   }

   public void addToEvents(NotificationEvent elem) {
      if (this.events == null) {
         this.events = new ArrayList();
      }

      this.events.add(elem);
   }

   @Nullable
   public List getEvents() {
      return this.events;
   }

   public void setEvents(@Nullable List events) {
      this.events = events;
   }

   public void unsetEvents() {
      this.events = null;
   }

   public boolean isSetEvents() {
      return this.events != null;
   }

   public void setEventsIsSet(boolean value) {
      if (!value) {
         this.events = null;
      }

   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case EVENTS:
            if (value == null) {
               this.unsetEvents();
            } else {
               this.setEvents((List)value);
            }
         default:
      }
   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case EVENTS:
            return this.getEvents();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case EVENTS:
               return this.isSetEvents();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof NotificationEventResponse ? this.equals((NotificationEventResponse)that) : false;
   }

   public boolean equals(NotificationEventResponse that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_events = this.isSetEvents();
         boolean that_present_events = that.isSetEvents();
         if (this_present_events || that_present_events) {
            if (!this_present_events || !that_present_events) {
               return false;
            }

            if (!this.events.equals(that.events)) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + (this.isSetEvents() ? 131071 : 524287);
      if (this.isSetEvents()) {
         hashCode = hashCode * 8191 + this.events.hashCode();
      }

      return hashCode;
   }

   public int compareTo(NotificationEventResponse other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetEvents(), other.isSetEvents());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetEvents()) {
               lastComparison = TBaseHelper.compareTo(this.events, other.events);
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
      return NotificationEventResponse._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("NotificationEventResponse(");
      boolean first = true;
      sb.append("events:");
      if (this.events == null) {
         sb.append("null");
      } else {
         sb.append(this.events);
      }

      first = false;
      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (!this.isSetEvents()) {
         throw new TProtocolException("Required field 'events' is unset! Struct:" + this.toString());
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
      tmpMap.put(NotificationEventResponse._Fields.EVENTS, new FieldMetaData("events", (byte)1, new ListMetaData((byte)15, new StructMetaData((byte)12, NotificationEvent.class))));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(NotificationEventResponse.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      EVENTS((short)1, "events");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return EVENTS;
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

   private static class NotificationEventResponseStandardSchemeFactory implements SchemeFactory {
      private NotificationEventResponseStandardSchemeFactory() {
      }

      public NotificationEventResponseStandardScheme getScheme() {
         return new NotificationEventResponseStandardScheme();
      }
   }

   private static class NotificationEventResponseStandardScheme extends StandardScheme {
      private NotificationEventResponseStandardScheme() {
      }

      public void read(TProtocol iprot, NotificationEventResponse struct) throws TException {
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
                  if (schemeField.type != 15) {
                     TProtocolUtil.skip(iprot, schemeField.type);
                     break;
                  }

                  TList _list582 = iprot.readListBegin();
                  struct.events = new ArrayList(_list582.size);

                  for(int _i584 = 0; _i584 < _list582.size; ++_i584) {
                     NotificationEvent _elem583 = new NotificationEvent();
                     _elem583.read(iprot);
                     struct.events.add(_elem583);
                  }

                  iprot.readListEnd();
                  struct.setEventsIsSet(true);
                  break;
               default:
                  TProtocolUtil.skip(iprot, schemeField.type);
            }

            iprot.readFieldEnd();
         }
      }

      public void write(TProtocol oprot, NotificationEventResponse struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(NotificationEventResponse.STRUCT_DESC);
         if (struct.events != null) {
            oprot.writeFieldBegin(NotificationEventResponse.EVENTS_FIELD_DESC);
            oprot.writeListBegin(new TList((byte)12, struct.events.size()));

            for(NotificationEvent _iter585 : struct.events) {
               _iter585.write(oprot);
            }

            oprot.writeListEnd();
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class NotificationEventResponseTupleSchemeFactory implements SchemeFactory {
      private NotificationEventResponseTupleSchemeFactory() {
      }

      public NotificationEventResponseTupleScheme getScheme() {
         return new NotificationEventResponseTupleScheme();
      }
   }

   private static class NotificationEventResponseTupleScheme extends TupleScheme {
      private NotificationEventResponseTupleScheme() {
      }

      public void write(TProtocol prot, NotificationEventResponse struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         oprot.writeI32(struct.events.size());

         for(NotificationEvent _iter586 : struct.events) {
            _iter586.write(oprot);
         }

      }

      public void read(TProtocol prot, NotificationEventResponse struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         TList _list587 = iprot.readListBegin((byte)12);
         struct.events = new ArrayList(_list587.size);

         for(int _i589 = 0; _i589 < _list587.size; ++_i589) {
            NotificationEvent _elem588 = new NotificationEvent();
            _elem588.read(iprot);
            struct.events.add(_elem588);
         }

         struct.setEventsIsSet(true);
      }
   }
}
