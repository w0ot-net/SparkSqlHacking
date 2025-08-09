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

public class NotificationEvent implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("NotificationEvent");
   private static final TField EVENT_ID_FIELD_DESC = new TField("eventId", (byte)10, (short)1);
   private static final TField EVENT_TIME_FIELD_DESC = new TField("eventTime", (byte)8, (short)2);
   private static final TField EVENT_TYPE_FIELD_DESC = new TField("eventType", (byte)11, (short)3);
   private static final TField DB_NAME_FIELD_DESC = new TField("dbName", (byte)11, (short)4);
   private static final TField TABLE_NAME_FIELD_DESC = new TField("tableName", (byte)11, (short)5);
   private static final TField MESSAGE_FIELD_DESC = new TField("message", (byte)11, (short)6);
   private static final TField MESSAGE_FORMAT_FIELD_DESC = new TField("messageFormat", (byte)11, (short)7);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new NotificationEventStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new NotificationEventTupleSchemeFactory();
   private long eventId;
   private int eventTime;
   @Nullable
   private String eventType;
   @Nullable
   private String dbName;
   @Nullable
   private String tableName;
   @Nullable
   private String message;
   @Nullable
   private String messageFormat;
   private static final int __EVENTID_ISSET_ID = 0;
   private static final int __EVENTTIME_ISSET_ID = 1;
   private byte __isset_bitfield;
   private static final _Fields[] optionals;
   public static final Map metaDataMap;

   public NotificationEvent() {
      this.__isset_bitfield = 0;
   }

   public NotificationEvent(long eventId, int eventTime, String eventType, String message) {
      this();
      this.eventId = eventId;
      this.setEventIdIsSet(true);
      this.eventTime = eventTime;
      this.setEventTimeIsSet(true);
      this.eventType = eventType;
      this.message = message;
   }

   public NotificationEvent(NotificationEvent other) {
      this.__isset_bitfield = 0;
      this.__isset_bitfield = other.__isset_bitfield;
      this.eventId = other.eventId;
      this.eventTime = other.eventTime;
      if (other.isSetEventType()) {
         this.eventType = other.eventType;
      }

      if (other.isSetDbName()) {
         this.dbName = other.dbName;
      }

      if (other.isSetTableName()) {
         this.tableName = other.tableName;
      }

      if (other.isSetMessage()) {
         this.message = other.message;
      }

      if (other.isSetMessageFormat()) {
         this.messageFormat = other.messageFormat;
      }

   }

   public NotificationEvent deepCopy() {
      return new NotificationEvent(this);
   }

   public void clear() {
      this.setEventIdIsSet(false);
      this.eventId = 0L;
      this.setEventTimeIsSet(false);
      this.eventTime = 0;
      this.eventType = null;
      this.dbName = null;
      this.tableName = null;
      this.message = null;
      this.messageFormat = null;
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

   public int getEventTime() {
      return this.eventTime;
   }

   public void setEventTime(int eventTime) {
      this.eventTime = eventTime;
      this.setEventTimeIsSet(true);
   }

   public void unsetEventTime() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 1);
   }

   public boolean isSetEventTime() {
      return EncodingUtils.testBit(this.__isset_bitfield, 1);
   }

   public void setEventTimeIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 1, value);
   }

   @Nullable
   public String getEventType() {
      return this.eventType;
   }

   public void setEventType(@Nullable String eventType) {
      this.eventType = eventType;
   }

   public void unsetEventType() {
      this.eventType = null;
   }

   public boolean isSetEventType() {
      return this.eventType != null;
   }

   public void setEventTypeIsSet(boolean value) {
      if (!value) {
         this.eventType = null;
      }

   }

   @Nullable
   public String getDbName() {
      return this.dbName;
   }

   public void setDbName(@Nullable String dbName) {
      this.dbName = dbName;
   }

   public void unsetDbName() {
      this.dbName = null;
   }

   public boolean isSetDbName() {
      return this.dbName != null;
   }

   public void setDbNameIsSet(boolean value) {
      if (!value) {
         this.dbName = null;
      }

   }

   @Nullable
   public String getTableName() {
      return this.tableName;
   }

   public void setTableName(@Nullable String tableName) {
      this.tableName = tableName;
   }

   public void unsetTableName() {
      this.tableName = null;
   }

   public boolean isSetTableName() {
      return this.tableName != null;
   }

   public void setTableNameIsSet(boolean value) {
      if (!value) {
         this.tableName = null;
      }

   }

   @Nullable
   public String getMessage() {
      return this.message;
   }

   public void setMessage(@Nullable String message) {
      this.message = message;
   }

   public void unsetMessage() {
      this.message = null;
   }

   public boolean isSetMessage() {
      return this.message != null;
   }

   public void setMessageIsSet(boolean value) {
      if (!value) {
         this.message = null;
      }

   }

   @Nullable
   public String getMessageFormat() {
      return this.messageFormat;
   }

   public void setMessageFormat(@Nullable String messageFormat) {
      this.messageFormat = messageFormat;
   }

   public void unsetMessageFormat() {
      this.messageFormat = null;
   }

   public boolean isSetMessageFormat() {
      return this.messageFormat != null;
   }

   public void setMessageFormatIsSet(boolean value) {
      if (!value) {
         this.messageFormat = null;
      }

   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case EVENT_ID:
            if (value == null) {
               this.unsetEventId();
            } else {
               this.setEventId((Long)value);
            }
            break;
         case EVENT_TIME:
            if (value == null) {
               this.unsetEventTime();
            } else {
               this.setEventTime((Integer)value);
            }
            break;
         case EVENT_TYPE:
            if (value == null) {
               this.unsetEventType();
            } else {
               this.setEventType((String)value);
            }
            break;
         case DB_NAME:
            if (value == null) {
               this.unsetDbName();
            } else {
               this.setDbName((String)value);
            }
            break;
         case TABLE_NAME:
            if (value == null) {
               this.unsetTableName();
            } else {
               this.setTableName((String)value);
            }
            break;
         case MESSAGE:
            if (value == null) {
               this.unsetMessage();
            } else {
               this.setMessage((String)value);
            }
            break;
         case MESSAGE_FORMAT:
            if (value == null) {
               this.unsetMessageFormat();
            } else {
               this.setMessageFormat((String)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case EVENT_ID:
            return this.getEventId();
         case EVENT_TIME:
            return this.getEventTime();
         case EVENT_TYPE:
            return this.getEventType();
         case DB_NAME:
            return this.getDbName();
         case TABLE_NAME:
            return this.getTableName();
         case MESSAGE:
            return this.getMessage();
         case MESSAGE_FORMAT:
            return this.getMessageFormat();
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
            case EVENT_TIME:
               return this.isSetEventTime();
            case EVENT_TYPE:
               return this.isSetEventType();
            case DB_NAME:
               return this.isSetDbName();
            case TABLE_NAME:
               return this.isSetTableName();
            case MESSAGE:
               return this.isSetMessage();
            case MESSAGE_FORMAT:
               return this.isSetMessageFormat();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof NotificationEvent ? this.equals((NotificationEvent)that) : false;
   }

   public boolean equals(NotificationEvent that) {
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

         boolean this_present_eventTime = true;
         boolean that_present_eventTime = true;
         if (this_present_eventTime || that_present_eventTime) {
            if (!this_present_eventTime || !that_present_eventTime) {
               return false;
            }

            if (this.eventTime != that.eventTime) {
               return false;
            }
         }

         boolean this_present_eventType = this.isSetEventType();
         boolean that_present_eventType = that.isSetEventType();
         if (this_present_eventType || that_present_eventType) {
            if (!this_present_eventType || !that_present_eventType) {
               return false;
            }

            if (!this.eventType.equals(that.eventType)) {
               return false;
            }
         }

         boolean this_present_dbName = this.isSetDbName();
         boolean that_present_dbName = that.isSetDbName();
         if (this_present_dbName || that_present_dbName) {
            if (!this_present_dbName || !that_present_dbName) {
               return false;
            }

            if (!this.dbName.equals(that.dbName)) {
               return false;
            }
         }

         boolean this_present_tableName = this.isSetTableName();
         boolean that_present_tableName = that.isSetTableName();
         if (this_present_tableName || that_present_tableName) {
            if (!this_present_tableName || !that_present_tableName) {
               return false;
            }

            if (!this.tableName.equals(that.tableName)) {
               return false;
            }
         }

         boolean this_present_message = this.isSetMessage();
         boolean that_present_message = that.isSetMessage();
         if (this_present_message || that_present_message) {
            if (!this_present_message || !that_present_message) {
               return false;
            }

            if (!this.message.equals(that.message)) {
               return false;
            }
         }

         boolean this_present_messageFormat = this.isSetMessageFormat();
         boolean that_present_messageFormat = that.isSetMessageFormat();
         if (this_present_messageFormat || that_present_messageFormat) {
            if (!this_present_messageFormat || !that_present_messageFormat) {
               return false;
            }

            if (!this.messageFormat.equals(that.messageFormat)) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + TBaseHelper.hashCode(this.eventId);
      hashCode = hashCode * 8191 + this.eventTime;
      hashCode = hashCode * 8191 + (this.isSetEventType() ? 131071 : 524287);
      if (this.isSetEventType()) {
         hashCode = hashCode * 8191 + this.eventType.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetDbName() ? 131071 : 524287);
      if (this.isSetDbName()) {
         hashCode = hashCode * 8191 + this.dbName.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetTableName() ? 131071 : 524287);
      if (this.isSetTableName()) {
         hashCode = hashCode * 8191 + this.tableName.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetMessage() ? 131071 : 524287);
      if (this.isSetMessage()) {
         hashCode = hashCode * 8191 + this.message.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetMessageFormat() ? 131071 : 524287);
      if (this.isSetMessageFormat()) {
         hashCode = hashCode * 8191 + this.messageFormat.hashCode();
      }

      return hashCode;
   }

   public int compareTo(NotificationEvent other) {
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

            lastComparison = Boolean.compare(this.isSetEventTime(), other.isSetEventTime());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetEventTime()) {
                  lastComparison = TBaseHelper.compareTo(this.eventTime, other.eventTime);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               lastComparison = Boolean.compare(this.isSetEventType(), other.isSetEventType());
               if (lastComparison != 0) {
                  return lastComparison;
               } else {
                  if (this.isSetEventType()) {
                     lastComparison = TBaseHelper.compareTo(this.eventType, other.eventType);
                     if (lastComparison != 0) {
                        return lastComparison;
                     }
                  }

                  lastComparison = Boolean.compare(this.isSetDbName(), other.isSetDbName());
                  if (lastComparison != 0) {
                     return lastComparison;
                  } else {
                     if (this.isSetDbName()) {
                        lastComparison = TBaseHelper.compareTo(this.dbName, other.dbName);
                        if (lastComparison != 0) {
                           return lastComparison;
                        }
                     }

                     lastComparison = Boolean.compare(this.isSetTableName(), other.isSetTableName());
                     if (lastComparison != 0) {
                        return lastComparison;
                     } else {
                        if (this.isSetTableName()) {
                           lastComparison = TBaseHelper.compareTo(this.tableName, other.tableName);
                           if (lastComparison != 0) {
                              return lastComparison;
                           }
                        }

                        lastComparison = Boolean.compare(this.isSetMessage(), other.isSetMessage());
                        if (lastComparison != 0) {
                           return lastComparison;
                        } else {
                           if (this.isSetMessage()) {
                              lastComparison = TBaseHelper.compareTo(this.message, other.message);
                              if (lastComparison != 0) {
                                 return lastComparison;
                              }
                           }

                           lastComparison = Boolean.compare(this.isSetMessageFormat(), other.isSetMessageFormat());
                           if (lastComparison != 0) {
                              return lastComparison;
                           } else {
                              if (this.isSetMessageFormat()) {
                                 lastComparison = TBaseHelper.compareTo(this.messageFormat, other.messageFormat);
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
            }
         }
      }
   }

   @Nullable
   public _Fields fieldForId(int fieldId) {
      return NotificationEvent._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("NotificationEvent(");
      boolean first = true;
      sb.append("eventId:");
      sb.append(this.eventId);
      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("eventTime:");
      sb.append(this.eventTime);
      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("eventType:");
      if (this.eventType == null) {
         sb.append("null");
      } else {
         sb.append(this.eventType);
      }

      first = false;
      if (this.isSetDbName()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("dbName:");
         if (this.dbName == null) {
            sb.append("null");
         } else {
            sb.append(this.dbName);
         }

         first = false;
      }

      if (this.isSetTableName()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("tableName:");
         if (this.tableName == null) {
            sb.append("null");
         } else {
            sb.append(this.tableName);
         }

         first = false;
      }

      if (!first) {
         sb.append(", ");
      }

      sb.append("message:");
      if (this.message == null) {
         sb.append("null");
      } else {
         sb.append(this.message);
      }

      first = false;
      if (this.isSetMessageFormat()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("messageFormat:");
         if (this.messageFormat == null) {
            sb.append("null");
         } else {
            sb.append(this.messageFormat);
         }

         first = false;
      }

      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (!this.isSetEventId()) {
         throw new TProtocolException("Required field 'eventId' is unset! Struct:" + this.toString());
      } else if (!this.isSetEventTime()) {
         throw new TProtocolException("Required field 'eventTime' is unset! Struct:" + this.toString());
      } else if (!this.isSetEventType()) {
         throw new TProtocolException("Required field 'eventType' is unset! Struct:" + this.toString());
      } else if (!this.isSetMessage()) {
         throw new TProtocolException("Required field 'message' is unset! Struct:" + this.toString());
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
      optionals = new _Fields[]{NotificationEvent._Fields.DB_NAME, NotificationEvent._Fields.TABLE_NAME, NotificationEvent._Fields.MESSAGE_FORMAT};
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(NotificationEvent._Fields.EVENT_ID, new FieldMetaData("eventId", (byte)1, new FieldValueMetaData((byte)10)));
      tmpMap.put(NotificationEvent._Fields.EVENT_TIME, new FieldMetaData("eventTime", (byte)1, new FieldValueMetaData((byte)8)));
      tmpMap.put(NotificationEvent._Fields.EVENT_TYPE, new FieldMetaData("eventType", (byte)1, new FieldValueMetaData((byte)11)));
      tmpMap.put(NotificationEvent._Fields.DB_NAME, new FieldMetaData("dbName", (byte)2, new FieldValueMetaData((byte)11)));
      tmpMap.put(NotificationEvent._Fields.TABLE_NAME, new FieldMetaData("tableName", (byte)2, new FieldValueMetaData((byte)11)));
      tmpMap.put(NotificationEvent._Fields.MESSAGE, new FieldMetaData("message", (byte)1, new FieldValueMetaData((byte)11)));
      tmpMap.put(NotificationEvent._Fields.MESSAGE_FORMAT, new FieldMetaData("messageFormat", (byte)2, new FieldValueMetaData((byte)11)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(NotificationEvent.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      EVENT_ID((short)1, "eventId"),
      EVENT_TIME((short)2, "eventTime"),
      EVENT_TYPE((short)3, "eventType"),
      DB_NAME((short)4, "dbName"),
      TABLE_NAME((short)5, "tableName"),
      MESSAGE((short)6, "message"),
      MESSAGE_FORMAT((short)7, "messageFormat");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return EVENT_ID;
            case 2:
               return EVENT_TIME;
            case 3:
               return EVENT_TYPE;
            case 4:
               return DB_NAME;
            case 5:
               return TABLE_NAME;
            case 6:
               return MESSAGE;
            case 7:
               return MESSAGE_FORMAT;
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

   private static class NotificationEventStandardSchemeFactory implements SchemeFactory {
      private NotificationEventStandardSchemeFactory() {
      }

      public NotificationEventStandardScheme getScheme() {
         return new NotificationEventStandardScheme();
      }
   }

   private static class NotificationEventStandardScheme extends StandardScheme {
      private NotificationEventStandardScheme() {
      }

      public void read(TProtocol iprot, NotificationEvent struct) throws TException {
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
               case 2:
                  if (schemeField.type == 8) {
                     struct.eventTime = iprot.readI32();
                     struct.setEventTimeIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 3:
                  if (schemeField.type == 11) {
                     struct.eventType = iprot.readString();
                     struct.setEventTypeIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 4:
                  if (schemeField.type == 11) {
                     struct.dbName = iprot.readString();
                     struct.setDbNameIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 5:
                  if (schemeField.type == 11) {
                     struct.tableName = iprot.readString();
                     struct.setTableNameIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 6:
                  if (schemeField.type == 11) {
                     struct.message = iprot.readString();
                     struct.setMessageIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 7:
                  if (schemeField.type == 11) {
                     struct.messageFormat = iprot.readString();
                     struct.setMessageFormatIsSet(true);
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

      public void write(TProtocol oprot, NotificationEvent struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(NotificationEvent.STRUCT_DESC);
         oprot.writeFieldBegin(NotificationEvent.EVENT_ID_FIELD_DESC);
         oprot.writeI64(struct.eventId);
         oprot.writeFieldEnd();
         oprot.writeFieldBegin(NotificationEvent.EVENT_TIME_FIELD_DESC);
         oprot.writeI32(struct.eventTime);
         oprot.writeFieldEnd();
         if (struct.eventType != null) {
            oprot.writeFieldBegin(NotificationEvent.EVENT_TYPE_FIELD_DESC);
            oprot.writeString(struct.eventType);
            oprot.writeFieldEnd();
         }

         if (struct.dbName != null && struct.isSetDbName()) {
            oprot.writeFieldBegin(NotificationEvent.DB_NAME_FIELD_DESC);
            oprot.writeString(struct.dbName);
            oprot.writeFieldEnd();
         }

         if (struct.tableName != null && struct.isSetTableName()) {
            oprot.writeFieldBegin(NotificationEvent.TABLE_NAME_FIELD_DESC);
            oprot.writeString(struct.tableName);
            oprot.writeFieldEnd();
         }

         if (struct.message != null) {
            oprot.writeFieldBegin(NotificationEvent.MESSAGE_FIELD_DESC);
            oprot.writeString(struct.message);
            oprot.writeFieldEnd();
         }

         if (struct.messageFormat != null && struct.isSetMessageFormat()) {
            oprot.writeFieldBegin(NotificationEvent.MESSAGE_FORMAT_FIELD_DESC);
            oprot.writeString(struct.messageFormat);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class NotificationEventTupleSchemeFactory implements SchemeFactory {
      private NotificationEventTupleSchemeFactory() {
      }

      public NotificationEventTupleScheme getScheme() {
         return new NotificationEventTupleScheme();
      }
   }

   private static class NotificationEventTupleScheme extends TupleScheme {
      private NotificationEventTupleScheme() {
      }

      public void write(TProtocol prot, NotificationEvent struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         oprot.writeI64(struct.eventId);
         oprot.writeI32(struct.eventTime);
         oprot.writeString(struct.eventType);
         oprot.writeString(struct.message);
         BitSet optionals = new BitSet();
         if (struct.isSetDbName()) {
            optionals.set(0);
         }

         if (struct.isSetTableName()) {
            optionals.set(1);
         }

         if (struct.isSetMessageFormat()) {
            optionals.set(2);
         }

         oprot.writeBitSet(optionals, 3);
         if (struct.isSetDbName()) {
            oprot.writeString(struct.dbName);
         }

         if (struct.isSetTableName()) {
            oprot.writeString(struct.tableName);
         }

         if (struct.isSetMessageFormat()) {
            oprot.writeString(struct.messageFormat);
         }

      }

      public void read(TProtocol prot, NotificationEvent struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         struct.eventId = iprot.readI64();
         struct.setEventIdIsSet(true);
         struct.eventTime = iprot.readI32();
         struct.setEventTimeIsSet(true);
         struct.eventType = iprot.readString();
         struct.setEventTypeIsSet(true);
         struct.message = iprot.readString();
         struct.setMessageIsSet(true);
         BitSet incoming = iprot.readBitSet(3);
         if (incoming.get(0)) {
            struct.dbName = iprot.readString();
            struct.setDbNameIsSet(true);
         }

         if (incoming.get(1)) {
            struct.tableName = iprot.readString();
            struct.setTableNameIsSet(true);
         }

         if (incoming.get(2)) {
            struct.messageFormat = iprot.readString();
            struct.setMessageFormatIsSet(true);
         }

      }
   }
}
