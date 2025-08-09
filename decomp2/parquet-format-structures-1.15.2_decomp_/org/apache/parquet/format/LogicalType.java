package org.apache.parquet.format;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import shaded.parquet.org.apache.thrift.TBaseHelper;
import shaded.parquet.org.apache.thrift.TEnum;
import shaded.parquet.org.apache.thrift.TException;
import shaded.parquet.org.apache.thrift.TFieldIdEnum;
import shaded.parquet.org.apache.thrift.TUnion;
import shaded.parquet.org.apache.thrift.annotation.Nullable;
import shaded.parquet.org.apache.thrift.meta_data.FieldMetaData;
import shaded.parquet.org.apache.thrift.meta_data.StructMetaData;
import shaded.parquet.org.apache.thrift.protocol.TCompactProtocol;
import shaded.parquet.org.apache.thrift.protocol.TField;
import shaded.parquet.org.apache.thrift.protocol.TProtocol;
import shaded.parquet.org.apache.thrift.protocol.TProtocolException;
import shaded.parquet.org.apache.thrift.protocol.TProtocolUtil;
import shaded.parquet.org.apache.thrift.protocol.TStruct;
import shaded.parquet.org.apache.thrift.transport.TIOStreamTransport;

public class LogicalType extends TUnion {
   private static final TStruct STRUCT_DESC = new TStruct("LogicalType");
   private static final TField STRING_FIELD_DESC = new TField("STRING", (byte)12, (short)1);
   private static final TField MAP_FIELD_DESC = new TField("MAP", (byte)12, (short)2);
   private static final TField LIST_FIELD_DESC = new TField("LIST", (byte)12, (short)3);
   private static final TField ENUM_FIELD_DESC = new TField("ENUM", (byte)12, (short)4);
   private static final TField DECIMAL_FIELD_DESC = new TField("DECIMAL", (byte)12, (short)5);
   private static final TField DATE_FIELD_DESC = new TField("DATE", (byte)12, (short)6);
   private static final TField TIME_FIELD_DESC = new TField("TIME", (byte)12, (short)7);
   private static final TField TIMESTAMP_FIELD_DESC = new TField("TIMESTAMP", (byte)12, (short)8);
   private static final TField INTEGER_FIELD_DESC = new TField("INTEGER", (byte)12, (short)10);
   private static final TField UNKNOWN_FIELD_DESC = new TField("UNKNOWN", (byte)12, (short)11);
   private static final TField JSON_FIELD_DESC = new TField("JSON", (byte)12, (short)12);
   private static final TField BSON_FIELD_DESC = new TField("BSON", (byte)12, (short)13);
   private static final TField UUID_FIELD_DESC = new TField("UUID", (byte)12, (short)14);
   private static final TField FLOAT16_FIELD_DESC = new TField("FLOAT16", (byte)12, (short)15);
   public static final Map metaDataMap;

   public LogicalType() {
   }

   public LogicalType(_Fields setField, Object value) {
      super(setField, value);
   }

   public LogicalType(LogicalType other) {
      super(other);
   }

   public LogicalType deepCopy() {
      return new LogicalType(this);
   }

   public static LogicalType STRING(StringType value) {
      LogicalType x = new LogicalType();
      x.setSTRING(value);
      return x;
   }

   public static LogicalType MAP(MapType value) {
      LogicalType x = new LogicalType();
      x.setMAP(value);
      return x;
   }

   public static LogicalType LIST(ListType value) {
      LogicalType x = new LogicalType();
      x.setLIST(value);
      return x;
   }

   public static LogicalType ENUM(EnumType value) {
      LogicalType x = new LogicalType();
      x.setENUM(value);
      return x;
   }

   public static LogicalType DECIMAL(DecimalType value) {
      LogicalType x = new LogicalType();
      x.setDECIMAL(value);
      return x;
   }

   public static LogicalType DATE(DateType value) {
      LogicalType x = new LogicalType();
      x.setDATE(value);
      return x;
   }

   public static LogicalType TIME(TimeType value) {
      LogicalType x = new LogicalType();
      x.setTIME(value);
      return x;
   }

   public static LogicalType TIMESTAMP(TimestampType value) {
      LogicalType x = new LogicalType();
      x.setTIMESTAMP(value);
      return x;
   }

   public static LogicalType INTEGER(IntType value) {
      LogicalType x = new LogicalType();
      x.setINTEGER(value);
      return x;
   }

   public static LogicalType UNKNOWN(NullType value) {
      LogicalType x = new LogicalType();
      x.setUNKNOWN(value);
      return x;
   }

   public static LogicalType JSON(JsonType value) {
      LogicalType x = new LogicalType();
      x.setJSON(value);
      return x;
   }

   public static LogicalType BSON(BsonType value) {
      LogicalType x = new LogicalType();
      x.setBSON(value);
      return x;
   }

   public static LogicalType UUID(UUIDType value) {
      LogicalType x = new LogicalType();
      x.setUUID(value);
      return x;
   }

   public static LogicalType FLOAT16(Float16Type value) {
      LogicalType x = new LogicalType();
      x.setFLOAT16(value);
      return x;
   }

   protected void checkType(_Fields setField, Object value) throws ClassCastException {
      switch (setField) {
         case STRING:
            if (!(value instanceof StringType)) {
               throw new ClassCastException("Was expecting value of type StringType for field 'STRING', but got " + value.getClass().getSimpleName());
            }
            break;
         case MAP:
            if (!(value instanceof MapType)) {
               throw new ClassCastException("Was expecting value of type MapType for field 'MAP', but got " + value.getClass().getSimpleName());
            }
            break;
         case LIST:
            if (!(value instanceof ListType)) {
               throw new ClassCastException("Was expecting value of type ListType for field 'LIST', but got " + value.getClass().getSimpleName());
            }
            break;
         case ENUM:
            if (!(value instanceof EnumType)) {
               throw new ClassCastException("Was expecting value of type EnumType for field 'ENUM', but got " + value.getClass().getSimpleName());
            }
            break;
         case DECIMAL:
            if (!(value instanceof DecimalType)) {
               throw new ClassCastException("Was expecting value of type DecimalType for field 'DECIMAL', but got " + value.getClass().getSimpleName());
            }
            break;
         case DATE:
            if (!(value instanceof DateType)) {
               throw new ClassCastException("Was expecting value of type DateType for field 'DATE', but got " + value.getClass().getSimpleName());
            }
            break;
         case TIME:
            if (!(value instanceof TimeType)) {
               throw new ClassCastException("Was expecting value of type TimeType for field 'TIME', but got " + value.getClass().getSimpleName());
            }
            break;
         case TIMESTAMP:
            if (!(value instanceof TimestampType)) {
               throw new ClassCastException("Was expecting value of type TimestampType for field 'TIMESTAMP', but got " + value.getClass().getSimpleName());
            }
            break;
         case INTEGER:
            if (!(value instanceof IntType)) {
               throw new ClassCastException("Was expecting value of type IntType for field 'INTEGER', but got " + value.getClass().getSimpleName());
            }
            break;
         case UNKNOWN:
            if (!(value instanceof NullType)) {
               throw new ClassCastException("Was expecting value of type NullType for field 'UNKNOWN', but got " + value.getClass().getSimpleName());
            }
            break;
         case JSON:
            if (!(value instanceof JsonType)) {
               throw new ClassCastException("Was expecting value of type JsonType for field 'JSON', but got " + value.getClass().getSimpleName());
            }
            break;
         case BSON:
            if (!(value instanceof BsonType)) {
               throw new ClassCastException("Was expecting value of type BsonType for field 'BSON', but got " + value.getClass().getSimpleName());
            }
            break;
         case UUID:
            if (!(value instanceof UUIDType)) {
               throw new ClassCastException("Was expecting value of type UUIDType for field 'UUID', but got " + value.getClass().getSimpleName());
            }
            break;
         case FLOAT16:
            if (!(value instanceof Float16Type)) {
               throw new ClassCastException("Was expecting value of type Float16Type for field 'FLOAT16', but got " + value.getClass().getSimpleName());
            }
            break;
         default:
            throw new IllegalArgumentException("Unknown field id " + setField);
      }

   }

   protected Object standardSchemeReadValue(TProtocol iprot, TField field) throws TException {
      _Fields setField = LogicalType._Fields.findByThriftId(field.id);
      if (setField != null) {
         switch (setField) {
            case STRING:
               if (field.type == STRING_FIELD_DESC.type) {
                  StringType STRING = new StringType();
                  STRING.read(iprot);
                  return STRING;
               }

               TProtocolUtil.skip(iprot, field.type);
               return null;
            case MAP:
               if (field.type == MAP_FIELD_DESC.type) {
                  MapType MAP = new MapType();
                  MAP.read(iprot);
                  return MAP;
               }

               TProtocolUtil.skip(iprot, field.type);
               return null;
            case LIST:
               if (field.type == LIST_FIELD_DESC.type) {
                  ListType LIST = new ListType();
                  LIST.read(iprot);
                  return LIST;
               }

               TProtocolUtil.skip(iprot, field.type);
               return null;
            case ENUM:
               if (field.type == ENUM_FIELD_DESC.type) {
                  EnumType ENUM = new EnumType();
                  ENUM.read(iprot);
                  return ENUM;
               }

               TProtocolUtil.skip(iprot, field.type);
               return null;
            case DECIMAL:
               if (field.type == DECIMAL_FIELD_DESC.type) {
                  DecimalType DECIMAL = new DecimalType();
                  DECIMAL.read(iprot);
                  return DECIMAL;
               }

               TProtocolUtil.skip(iprot, field.type);
               return null;
            case DATE:
               if (field.type == DATE_FIELD_DESC.type) {
                  DateType DATE = new DateType();
                  DATE.read(iprot);
                  return DATE;
               }

               TProtocolUtil.skip(iprot, field.type);
               return null;
            case TIME:
               if (field.type == TIME_FIELD_DESC.type) {
                  TimeType TIME = new TimeType();
                  TIME.read(iprot);
                  return TIME;
               }

               TProtocolUtil.skip(iprot, field.type);
               return null;
            case TIMESTAMP:
               if (field.type == TIMESTAMP_FIELD_DESC.type) {
                  TimestampType TIMESTAMP = new TimestampType();
                  TIMESTAMP.read(iprot);
                  return TIMESTAMP;
               }

               TProtocolUtil.skip(iprot, field.type);
               return null;
            case INTEGER:
               if (field.type == INTEGER_FIELD_DESC.type) {
                  IntType INTEGER = new IntType();
                  INTEGER.read(iprot);
                  return INTEGER;
               }

               TProtocolUtil.skip(iprot, field.type);
               return null;
            case UNKNOWN:
               if (field.type == UNKNOWN_FIELD_DESC.type) {
                  NullType UNKNOWN = new NullType();
                  UNKNOWN.read(iprot);
                  return UNKNOWN;
               }

               TProtocolUtil.skip(iprot, field.type);
               return null;
            case JSON:
               if (field.type == JSON_FIELD_DESC.type) {
                  JsonType JSON = new JsonType();
                  JSON.read(iprot);
                  return JSON;
               }

               TProtocolUtil.skip(iprot, field.type);
               return null;
            case BSON:
               if (field.type == BSON_FIELD_DESC.type) {
                  BsonType BSON = new BsonType();
                  BSON.read(iprot);
                  return BSON;
               }

               TProtocolUtil.skip(iprot, field.type);
               return null;
            case UUID:
               if (field.type == UUID_FIELD_DESC.type) {
                  UUIDType UUID = new UUIDType();
                  UUID.read(iprot);
                  return UUID;
               }

               TProtocolUtil.skip(iprot, field.type);
               return null;
            case FLOAT16:
               if (field.type == FLOAT16_FIELD_DESC.type) {
                  Float16Type FLOAT16 = new Float16Type();
                  FLOAT16.read(iprot);
                  return FLOAT16;
               }

               TProtocolUtil.skip(iprot, field.type);
               return null;
            default:
               throw new IllegalStateException("setField wasn't null, but didn't match any of the case statements!");
         }
      } else {
         TProtocolUtil.skip(iprot, field.type);
         return null;
      }
   }

   protected void standardSchemeWriteValue(TProtocol oprot) throws TException {
      switch ((_Fields)this.setField_) {
         case STRING:
            StringType STRING = (StringType)this.value_;
            STRING.write(oprot);
            return;
         case MAP:
            MapType MAP = (MapType)this.value_;
            MAP.write(oprot);
            return;
         case LIST:
            ListType LIST = (ListType)this.value_;
            LIST.write(oprot);
            return;
         case ENUM:
            EnumType ENUM = (EnumType)this.value_;
            ENUM.write(oprot);
            return;
         case DECIMAL:
            DecimalType DECIMAL = (DecimalType)this.value_;
            DECIMAL.write(oprot);
            return;
         case DATE:
            DateType DATE = (DateType)this.value_;
            DATE.write(oprot);
            return;
         case TIME:
            TimeType TIME = (TimeType)this.value_;
            TIME.write(oprot);
            return;
         case TIMESTAMP:
            TimestampType TIMESTAMP = (TimestampType)this.value_;
            TIMESTAMP.write(oprot);
            return;
         case INTEGER:
            IntType INTEGER = (IntType)this.value_;
            INTEGER.write(oprot);
            return;
         case UNKNOWN:
            NullType UNKNOWN = (NullType)this.value_;
            UNKNOWN.write(oprot);
            return;
         case JSON:
            JsonType JSON = (JsonType)this.value_;
            JSON.write(oprot);
            return;
         case BSON:
            BsonType BSON = (BsonType)this.value_;
            BSON.write(oprot);
            return;
         case UUID:
            UUIDType UUID = (UUIDType)this.value_;
            UUID.write(oprot);
            return;
         case FLOAT16:
            Float16Type FLOAT16 = (Float16Type)this.value_;
            FLOAT16.write(oprot);
            return;
         default:
            throw new IllegalStateException("Cannot write union with unknown field " + this.setField_);
      }
   }

   protected Object tupleSchemeReadValue(TProtocol iprot, short fieldID) throws TException {
      _Fields setField = LogicalType._Fields.findByThriftId(fieldID);
      if (setField != null) {
         switch (setField) {
            case STRING:
               StringType STRING = new StringType();
               STRING.read(iprot);
               return STRING;
            case MAP:
               MapType MAP = new MapType();
               MAP.read(iprot);
               return MAP;
            case LIST:
               ListType LIST = new ListType();
               LIST.read(iprot);
               return LIST;
            case ENUM:
               EnumType ENUM = new EnumType();
               ENUM.read(iprot);
               return ENUM;
            case DECIMAL:
               DecimalType DECIMAL = new DecimalType();
               DECIMAL.read(iprot);
               return DECIMAL;
            case DATE:
               DateType DATE = new DateType();
               DATE.read(iprot);
               return DATE;
            case TIME:
               TimeType TIME = new TimeType();
               TIME.read(iprot);
               return TIME;
            case TIMESTAMP:
               TimestampType TIMESTAMP = new TimestampType();
               TIMESTAMP.read(iprot);
               return TIMESTAMP;
            case INTEGER:
               IntType INTEGER = new IntType();
               INTEGER.read(iprot);
               return INTEGER;
            case UNKNOWN:
               NullType UNKNOWN = new NullType();
               UNKNOWN.read(iprot);
               return UNKNOWN;
            case JSON:
               JsonType JSON = new JsonType();
               JSON.read(iprot);
               return JSON;
            case BSON:
               BsonType BSON = new BsonType();
               BSON.read(iprot);
               return BSON;
            case UUID:
               UUIDType UUID = new UUIDType();
               UUID.read(iprot);
               return UUID;
            case FLOAT16:
               Float16Type FLOAT16 = new Float16Type();
               FLOAT16.read(iprot);
               return FLOAT16;
            default:
               throw new IllegalStateException("setField wasn't null, but didn't match any of the case statements!");
         }
      } else {
         throw new TProtocolException("Couldn't find a field with field id " + fieldID);
      }
   }

   protected void tupleSchemeWriteValue(TProtocol oprot) throws TException {
      switch ((_Fields)this.setField_) {
         case STRING:
            StringType STRING = (StringType)this.value_;
            STRING.write(oprot);
            return;
         case MAP:
            MapType MAP = (MapType)this.value_;
            MAP.write(oprot);
            return;
         case LIST:
            ListType LIST = (ListType)this.value_;
            LIST.write(oprot);
            return;
         case ENUM:
            EnumType ENUM = (EnumType)this.value_;
            ENUM.write(oprot);
            return;
         case DECIMAL:
            DecimalType DECIMAL = (DecimalType)this.value_;
            DECIMAL.write(oprot);
            return;
         case DATE:
            DateType DATE = (DateType)this.value_;
            DATE.write(oprot);
            return;
         case TIME:
            TimeType TIME = (TimeType)this.value_;
            TIME.write(oprot);
            return;
         case TIMESTAMP:
            TimestampType TIMESTAMP = (TimestampType)this.value_;
            TIMESTAMP.write(oprot);
            return;
         case INTEGER:
            IntType INTEGER = (IntType)this.value_;
            INTEGER.write(oprot);
            return;
         case UNKNOWN:
            NullType UNKNOWN = (NullType)this.value_;
            UNKNOWN.write(oprot);
            return;
         case JSON:
            JsonType JSON = (JsonType)this.value_;
            JSON.write(oprot);
            return;
         case BSON:
            BsonType BSON = (BsonType)this.value_;
            BSON.write(oprot);
            return;
         case UUID:
            UUIDType UUID = (UUIDType)this.value_;
            UUID.write(oprot);
            return;
         case FLOAT16:
            Float16Type FLOAT16 = (Float16Type)this.value_;
            FLOAT16.write(oprot);
            return;
         default:
            throw new IllegalStateException("Cannot write union with unknown field " + this.setField_);
      }
   }

   protected TField getFieldDesc(_Fields setField) {
      switch (setField) {
         case STRING:
            return STRING_FIELD_DESC;
         case MAP:
            return MAP_FIELD_DESC;
         case LIST:
            return LIST_FIELD_DESC;
         case ENUM:
            return ENUM_FIELD_DESC;
         case DECIMAL:
            return DECIMAL_FIELD_DESC;
         case DATE:
            return DATE_FIELD_DESC;
         case TIME:
            return TIME_FIELD_DESC;
         case TIMESTAMP:
            return TIMESTAMP_FIELD_DESC;
         case INTEGER:
            return INTEGER_FIELD_DESC;
         case UNKNOWN:
            return UNKNOWN_FIELD_DESC;
         case JSON:
            return JSON_FIELD_DESC;
         case BSON:
            return BSON_FIELD_DESC;
         case UUID:
            return UUID_FIELD_DESC;
         case FLOAT16:
            return FLOAT16_FIELD_DESC;
         default:
            throw new IllegalArgumentException("Unknown field id " + setField);
      }
   }

   protected TStruct getStructDesc() {
      return STRUCT_DESC;
   }

   protected _Fields enumForId(short id) {
      return LogicalType._Fields.findByThriftIdOrThrow(id);
   }

   @Nullable
   public _Fields fieldForId(int fieldId) {
      return LogicalType._Fields.findByThriftId(fieldId);
   }

   public StringType getSTRING() {
      if (this.getSetField() == LogicalType._Fields.STRING) {
         return (StringType)this.getFieldValue();
      } else {
         throw new RuntimeException("Cannot get field 'STRING' because union is currently set to " + this.getFieldDesc((_Fields)this.getSetField()).name);
      }
   }

   public void setSTRING(StringType value) {
      this.setField_ = LogicalType._Fields.STRING;
      this.value_ = Objects.requireNonNull(value, "_Fields.STRING");
   }

   public MapType getMAP() {
      if (this.getSetField() == LogicalType._Fields.MAP) {
         return (MapType)this.getFieldValue();
      } else {
         throw new RuntimeException("Cannot get field 'MAP' because union is currently set to " + this.getFieldDesc((_Fields)this.getSetField()).name);
      }
   }

   public void setMAP(MapType value) {
      this.setField_ = LogicalType._Fields.MAP;
      this.value_ = Objects.requireNonNull(value, "_Fields.MAP");
   }

   public ListType getLIST() {
      if (this.getSetField() == LogicalType._Fields.LIST) {
         return (ListType)this.getFieldValue();
      } else {
         throw new RuntimeException("Cannot get field 'LIST' because union is currently set to " + this.getFieldDesc((_Fields)this.getSetField()).name);
      }
   }

   public void setLIST(ListType value) {
      this.setField_ = LogicalType._Fields.LIST;
      this.value_ = Objects.requireNonNull(value, "_Fields.LIST");
   }

   public EnumType getENUM() {
      if (this.getSetField() == LogicalType._Fields.ENUM) {
         return (EnumType)this.getFieldValue();
      } else {
         throw new RuntimeException("Cannot get field 'ENUM' because union is currently set to " + this.getFieldDesc((_Fields)this.getSetField()).name);
      }
   }

   public void setENUM(EnumType value) {
      this.setField_ = LogicalType._Fields.ENUM;
      this.value_ = Objects.requireNonNull(value, "_Fields.ENUM");
   }

   public DecimalType getDECIMAL() {
      if (this.getSetField() == LogicalType._Fields.DECIMAL) {
         return (DecimalType)this.getFieldValue();
      } else {
         throw new RuntimeException("Cannot get field 'DECIMAL' because union is currently set to " + this.getFieldDesc((_Fields)this.getSetField()).name);
      }
   }

   public void setDECIMAL(DecimalType value) {
      this.setField_ = LogicalType._Fields.DECIMAL;
      this.value_ = Objects.requireNonNull(value, "_Fields.DECIMAL");
   }

   public DateType getDATE() {
      if (this.getSetField() == LogicalType._Fields.DATE) {
         return (DateType)this.getFieldValue();
      } else {
         throw new RuntimeException("Cannot get field 'DATE' because union is currently set to " + this.getFieldDesc((_Fields)this.getSetField()).name);
      }
   }

   public void setDATE(DateType value) {
      this.setField_ = LogicalType._Fields.DATE;
      this.value_ = Objects.requireNonNull(value, "_Fields.DATE");
   }

   public TimeType getTIME() {
      if (this.getSetField() == LogicalType._Fields.TIME) {
         return (TimeType)this.getFieldValue();
      } else {
         throw new RuntimeException("Cannot get field 'TIME' because union is currently set to " + this.getFieldDesc((_Fields)this.getSetField()).name);
      }
   }

   public void setTIME(TimeType value) {
      this.setField_ = LogicalType._Fields.TIME;
      this.value_ = Objects.requireNonNull(value, "_Fields.TIME");
   }

   public TimestampType getTIMESTAMP() {
      if (this.getSetField() == LogicalType._Fields.TIMESTAMP) {
         return (TimestampType)this.getFieldValue();
      } else {
         throw new RuntimeException("Cannot get field 'TIMESTAMP' because union is currently set to " + this.getFieldDesc((_Fields)this.getSetField()).name);
      }
   }

   public void setTIMESTAMP(TimestampType value) {
      this.setField_ = LogicalType._Fields.TIMESTAMP;
      this.value_ = Objects.requireNonNull(value, "_Fields.TIMESTAMP");
   }

   public IntType getINTEGER() {
      if (this.getSetField() == LogicalType._Fields.INTEGER) {
         return (IntType)this.getFieldValue();
      } else {
         throw new RuntimeException("Cannot get field 'INTEGER' because union is currently set to " + this.getFieldDesc((_Fields)this.getSetField()).name);
      }
   }

   public void setINTEGER(IntType value) {
      this.setField_ = LogicalType._Fields.INTEGER;
      this.value_ = Objects.requireNonNull(value, "_Fields.INTEGER");
   }

   public NullType getUNKNOWN() {
      if (this.getSetField() == LogicalType._Fields.UNKNOWN) {
         return (NullType)this.getFieldValue();
      } else {
         throw new RuntimeException("Cannot get field 'UNKNOWN' because union is currently set to " + this.getFieldDesc((_Fields)this.getSetField()).name);
      }
   }

   public void setUNKNOWN(NullType value) {
      this.setField_ = LogicalType._Fields.UNKNOWN;
      this.value_ = Objects.requireNonNull(value, "_Fields.UNKNOWN");
   }

   public JsonType getJSON() {
      if (this.getSetField() == LogicalType._Fields.JSON) {
         return (JsonType)this.getFieldValue();
      } else {
         throw new RuntimeException("Cannot get field 'JSON' because union is currently set to " + this.getFieldDesc((_Fields)this.getSetField()).name);
      }
   }

   public void setJSON(JsonType value) {
      this.setField_ = LogicalType._Fields.JSON;
      this.value_ = Objects.requireNonNull(value, "_Fields.JSON");
   }

   public BsonType getBSON() {
      if (this.getSetField() == LogicalType._Fields.BSON) {
         return (BsonType)this.getFieldValue();
      } else {
         throw new RuntimeException("Cannot get field 'BSON' because union is currently set to " + this.getFieldDesc((_Fields)this.getSetField()).name);
      }
   }

   public void setBSON(BsonType value) {
      this.setField_ = LogicalType._Fields.BSON;
      this.value_ = Objects.requireNonNull(value, "_Fields.BSON");
   }

   public UUIDType getUUID() {
      if (this.getSetField() == LogicalType._Fields.UUID) {
         return (UUIDType)this.getFieldValue();
      } else {
         throw new RuntimeException("Cannot get field 'UUID' because union is currently set to " + this.getFieldDesc((_Fields)this.getSetField()).name);
      }
   }

   public void setUUID(UUIDType value) {
      this.setField_ = LogicalType._Fields.UUID;
      this.value_ = Objects.requireNonNull(value, "_Fields.UUID");
   }

   public Float16Type getFLOAT16() {
      if (this.getSetField() == LogicalType._Fields.FLOAT16) {
         return (Float16Type)this.getFieldValue();
      } else {
         throw new RuntimeException("Cannot get field 'FLOAT16' because union is currently set to " + this.getFieldDesc((_Fields)this.getSetField()).name);
      }
   }

   public void setFLOAT16(Float16Type value) {
      this.setField_ = LogicalType._Fields.FLOAT16;
      this.value_ = Objects.requireNonNull(value, "_Fields.FLOAT16");
   }

   public boolean isSetSTRING() {
      return this.setField_ == LogicalType._Fields.STRING;
   }

   public boolean isSetMAP() {
      return this.setField_ == LogicalType._Fields.MAP;
   }

   public boolean isSetLIST() {
      return this.setField_ == LogicalType._Fields.LIST;
   }

   public boolean isSetENUM() {
      return this.setField_ == LogicalType._Fields.ENUM;
   }

   public boolean isSetDECIMAL() {
      return this.setField_ == LogicalType._Fields.DECIMAL;
   }

   public boolean isSetDATE() {
      return this.setField_ == LogicalType._Fields.DATE;
   }

   public boolean isSetTIME() {
      return this.setField_ == LogicalType._Fields.TIME;
   }

   public boolean isSetTIMESTAMP() {
      return this.setField_ == LogicalType._Fields.TIMESTAMP;
   }

   public boolean isSetINTEGER() {
      return this.setField_ == LogicalType._Fields.INTEGER;
   }

   public boolean isSetUNKNOWN() {
      return this.setField_ == LogicalType._Fields.UNKNOWN;
   }

   public boolean isSetJSON() {
      return this.setField_ == LogicalType._Fields.JSON;
   }

   public boolean isSetBSON() {
      return this.setField_ == LogicalType._Fields.BSON;
   }

   public boolean isSetUUID() {
      return this.setField_ == LogicalType._Fields.UUID;
   }

   public boolean isSetFLOAT16() {
      return this.setField_ == LogicalType._Fields.FLOAT16;
   }

   public boolean equals(Object other) {
      return other instanceof LogicalType ? this.equals((LogicalType)other) : false;
   }

   public boolean equals(LogicalType other) {
      return other != null && this.getSetField() == other.getSetField() && this.getFieldValue().equals(other.getFieldValue());
   }

   public int compareTo(LogicalType other) {
      int lastComparison = TBaseHelper.compareTo((Comparable)this.getSetField(), (Comparable)other.getSetField());
      return lastComparison == 0 ? TBaseHelper.compareTo(this.getFieldValue(), other.getFieldValue()) : lastComparison;
   }

   public int hashCode() {
      List<Object> list = new ArrayList();
      list.add(this.getClass().getName());
      TFieldIdEnum setField = this.getSetField();
      if (setField != null) {
         list.add(setField.getThriftFieldId());
         Object value = this.getFieldValue();
         if (value instanceof TEnum) {
            list.add(((TEnum)this.getFieldValue()).getValue());
         } else {
            list.add(value);
         }
      }

      return list.hashCode();
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

   static {
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(LogicalType._Fields.STRING, new FieldMetaData("STRING", (byte)2, new StructMetaData((byte)12, StringType.class)));
      tmpMap.put(LogicalType._Fields.MAP, new FieldMetaData("MAP", (byte)2, new StructMetaData((byte)12, MapType.class)));
      tmpMap.put(LogicalType._Fields.LIST, new FieldMetaData("LIST", (byte)2, new StructMetaData((byte)12, ListType.class)));
      tmpMap.put(LogicalType._Fields.ENUM, new FieldMetaData("ENUM", (byte)2, new StructMetaData((byte)12, EnumType.class)));
      tmpMap.put(LogicalType._Fields.DECIMAL, new FieldMetaData("DECIMAL", (byte)2, new StructMetaData((byte)12, DecimalType.class)));
      tmpMap.put(LogicalType._Fields.DATE, new FieldMetaData("DATE", (byte)2, new StructMetaData((byte)12, DateType.class)));
      tmpMap.put(LogicalType._Fields.TIME, new FieldMetaData("TIME", (byte)2, new StructMetaData((byte)12, TimeType.class)));
      tmpMap.put(LogicalType._Fields.TIMESTAMP, new FieldMetaData("TIMESTAMP", (byte)2, new StructMetaData((byte)12, TimestampType.class)));
      tmpMap.put(LogicalType._Fields.INTEGER, new FieldMetaData("INTEGER", (byte)2, new StructMetaData((byte)12, IntType.class)));
      tmpMap.put(LogicalType._Fields.UNKNOWN, new FieldMetaData("UNKNOWN", (byte)2, new StructMetaData((byte)12, NullType.class)));
      tmpMap.put(LogicalType._Fields.JSON, new FieldMetaData("JSON", (byte)2, new StructMetaData((byte)12, JsonType.class)));
      tmpMap.put(LogicalType._Fields.BSON, new FieldMetaData("BSON", (byte)2, new StructMetaData((byte)12, BsonType.class)));
      tmpMap.put(LogicalType._Fields.UUID, new FieldMetaData("UUID", (byte)2, new StructMetaData((byte)12, UUIDType.class)));
      tmpMap.put(LogicalType._Fields.FLOAT16, new FieldMetaData("FLOAT16", (byte)2, new StructMetaData((byte)12, Float16Type.class)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(LogicalType.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      STRING((short)1, "STRING"),
      MAP((short)2, "MAP"),
      LIST((short)3, "LIST"),
      ENUM((short)4, "ENUM"),
      DECIMAL((short)5, "DECIMAL"),
      DATE((short)6, "DATE"),
      TIME((short)7, "TIME"),
      TIMESTAMP((short)8, "TIMESTAMP"),
      INTEGER((short)10, "INTEGER"),
      UNKNOWN((short)11, "UNKNOWN"),
      JSON((short)12, "JSON"),
      BSON((short)13, "BSON"),
      UUID((short)14, "UUID"),
      FLOAT16((short)15, "FLOAT16");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return STRING;
            case 2:
               return MAP;
            case 3:
               return LIST;
            case 4:
               return ENUM;
            case 5:
               return DECIMAL;
            case 6:
               return DATE;
            case 7:
               return TIME;
            case 8:
               return TIMESTAMP;
            case 9:
            default:
               return null;
            case 10:
               return INTEGER;
            case 11:
               return UNKNOWN;
            case 12:
               return JSON;
            case 13:
               return BSON;
            case 14:
               return UUID;
            case 15:
               return FLOAT16;
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
}
