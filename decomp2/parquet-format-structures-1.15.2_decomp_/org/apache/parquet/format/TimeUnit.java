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

public class TimeUnit extends TUnion {
   private static final TStruct STRUCT_DESC = new TStruct("TimeUnit");
   private static final TField MILLIS_FIELD_DESC = new TField("MILLIS", (byte)12, (short)1);
   private static final TField MICROS_FIELD_DESC = new TField("MICROS", (byte)12, (short)2);
   private static final TField NANOS_FIELD_DESC = new TField("NANOS", (byte)12, (short)3);
   public static final Map metaDataMap;

   public TimeUnit() {
   }

   public TimeUnit(_Fields setField, Object value) {
      super(setField, value);
   }

   public TimeUnit(TimeUnit other) {
      super(other);
   }

   public TimeUnit deepCopy() {
      return new TimeUnit(this);
   }

   public static TimeUnit MILLIS(MilliSeconds value) {
      TimeUnit x = new TimeUnit();
      x.setMILLIS(value);
      return x;
   }

   public static TimeUnit MICROS(MicroSeconds value) {
      TimeUnit x = new TimeUnit();
      x.setMICROS(value);
      return x;
   }

   public static TimeUnit NANOS(NanoSeconds value) {
      TimeUnit x = new TimeUnit();
      x.setNANOS(value);
      return x;
   }

   protected void checkType(_Fields setField, Object value) throws ClassCastException {
      switch (setField) {
         case MILLIS:
            if (!(value instanceof MilliSeconds)) {
               throw new ClassCastException("Was expecting value of type MilliSeconds for field 'MILLIS', but got " + value.getClass().getSimpleName());
            }
            break;
         case MICROS:
            if (!(value instanceof MicroSeconds)) {
               throw new ClassCastException("Was expecting value of type MicroSeconds for field 'MICROS', but got " + value.getClass().getSimpleName());
            }
            break;
         case NANOS:
            if (!(value instanceof NanoSeconds)) {
               throw new ClassCastException("Was expecting value of type NanoSeconds for field 'NANOS', but got " + value.getClass().getSimpleName());
            }
            break;
         default:
            throw new IllegalArgumentException("Unknown field id " + setField);
      }

   }

   protected Object standardSchemeReadValue(TProtocol iprot, TField field) throws TException {
      _Fields setField = TimeUnit._Fields.findByThriftId(field.id);
      if (setField != null) {
         switch (setField) {
            case MILLIS:
               if (field.type == MILLIS_FIELD_DESC.type) {
                  MilliSeconds MILLIS = new MilliSeconds();
                  MILLIS.read(iprot);
                  return MILLIS;
               }

               TProtocolUtil.skip(iprot, field.type);
               return null;
            case MICROS:
               if (field.type == MICROS_FIELD_DESC.type) {
                  MicroSeconds MICROS = new MicroSeconds();
                  MICROS.read(iprot);
                  return MICROS;
               }

               TProtocolUtil.skip(iprot, field.type);
               return null;
            case NANOS:
               if (field.type == NANOS_FIELD_DESC.type) {
                  NanoSeconds NANOS = new NanoSeconds();
                  NANOS.read(iprot);
                  return NANOS;
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
         case MILLIS:
            MilliSeconds MILLIS = (MilliSeconds)this.value_;
            MILLIS.write(oprot);
            return;
         case MICROS:
            MicroSeconds MICROS = (MicroSeconds)this.value_;
            MICROS.write(oprot);
            return;
         case NANOS:
            NanoSeconds NANOS = (NanoSeconds)this.value_;
            NANOS.write(oprot);
            return;
         default:
            throw new IllegalStateException("Cannot write union with unknown field " + this.setField_);
      }
   }

   protected Object tupleSchemeReadValue(TProtocol iprot, short fieldID) throws TException {
      _Fields setField = TimeUnit._Fields.findByThriftId(fieldID);
      if (setField != null) {
         switch (setField) {
            case MILLIS:
               MilliSeconds MILLIS = new MilliSeconds();
               MILLIS.read(iprot);
               return MILLIS;
            case MICROS:
               MicroSeconds MICROS = new MicroSeconds();
               MICROS.read(iprot);
               return MICROS;
            case NANOS:
               NanoSeconds NANOS = new NanoSeconds();
               NANOS.read(iprot);
               return NANOS;
            default:
               throw new IllegalStateException("setField wasn't null, but didn't match any of the case statements!");
         }
      } else {
         throw new TProtocolException("Couldn't find a field with field id " + fieldID);
      }
   }

   protected void tupleSchemeWriteValue(TProtocol oprot) throws TException {
      switch ((_Fields)this.setField_) {
         case MILLIS:
            MilliSeconds MILLIS = (MilliSeconds)this.value_;
            MILLIS.write(oprot);
            return;
         case MICROS:
            MicroSeconds MICROS = (MicroSeconds)this.value_;
            MICROS.write(oprot);
            return;
         case NANOS:
            NanoSeconds NANOS = (NanoSeconds)this.value_;
            NANOS.write(oprot);
            return;
         default:
            throw new IllegalStateException("Cannot write union with unknown field " + this.setField_);
      }
   }

   protected TField getFieldDesc(_Fields setField) {
      switch (setField) {
         case MILLIS:
            return MILLIS_FIELD_DESC;
         case MICROS:
            return MICROS_FIELD_DESC;
         case NANOS:
            return NANOS_FIELD_DESC;
         default:
            throw new IllegalArgumentException("Unknown field id " + setField);
      }
   }

   protected TStruct getStructDesc() {
      return STRUCT_DESC;
   }

   protected _Fields enumForId(short id) {
      return TimeUnit._Fields.findByThriftIdOrThrow(id);
   }

   @Nullable
   public _Fields fieldForId(int fieldId) {
      return TimeUnit._Fields.findByThriftId(fieldId);
   }

   public MilliSeconds getMILLIS() {
      if (this.getSetField() == TimeUnit._Fields.MILLIS) {
         return (MilliSeconds)this.getFieldValue();
      } else {
         throw new RuntimeException("Cannot get field 'MILLIS' because union is currently set to " + this.getFieldDesc((_Fields)this.getSetField()).name);
      }
   }

   public void setMILLIS(MilliSeconds value) {
      this.setField_ = TimeUnit._Fields.MILLIS;
      this.value_ = Objects.requireNonNull(value, "_Fields.MILLIS");
   }

   public MicroSeconds getMICROS() {
      if (this.getSetField() == TimeUnit._Fields.MICROS) {
         return (MicroSeconds)this.getFieldValue();
      } else {
         throw new RuntimeException("Cannot get field 'MICROS' because union is currently set to " + this.getFieldDesc((_Fields)this.getSetField()).name);
      }
   }

   public void setMICROS(MicroSeconds value) {
      this.setField_ = TimeUnit._Fields.MICROS;
      this.value_ = Objects.requireNonNull(value, "_Fields.MICROS");
   }

   public NanoSeconds getNANOS() {
      if (this.getSetField() == TimeUnit._Fields.NANOS) {
         return (NanoSeconds)this.getFieldValue();
      } else {
         throw new RuntimeException("Cannot get field 'NANOS' because union is currently set to " + this.getFieldDesc((_Fields)this.getSetField()).name);
      }
   }

   public void setNANOS(NanoSeconds value) {
      this.setField_ = TimeUnit._Fields.NANOS;
      this.value_ = Objects.requireNonNull(value, "_Fields.NANOS");
   }

   public boolean isSetMILLIS() {
      return this.setField_ == TimeUnit._Fields.MILLIS;
   }

   public boolean isSetMICROS() {
      return this.setField_ == TimeUnit._Fields.MICROS;
   }

   public boolean isSetNANOS() {
      return this.setField_ == TimeUnit._Fields.NANOS;
   }

   public boolean equals(Object other) {
      return other instanceof TimeUnit ? this.equals((TimeUnit)other) : false;
   }

   public boolean equals(TimeUnit other) {
      return other != null && this.getSetField() == other.getSetField() && this.getFieldValue().equals(other.getFieldValue());
   }

   public int compareTo(TimeUnit other) {
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
      tmpMap.put(TimeUnit._Fields.MILLIS, new FieldMetaData("MILLIS", (byte)2, new StructMetaData((byte)12, MilliSeconds.class)));
      tmpMap.put(TimeUnit._Fields.MICROS, new FieldMetaData("MICROS", (byte)2, new StructMetaData((byte)12, MicroSeconds.class)));
      tmpMap.put(TimeUnit._Fields.NANOS, new FieldMetaData("NANOS", (byte)2, new StructMetaData((byte)12, NanoSeconds.class)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(TimeUnit.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      MILLIS((short)1, "MILLIS"),
      MICROS((short)2, "MICROS"),
      NANOS((short)3, "NANOS");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return MILLIS;
            case 2:
               return MICROS;
            case 3:
               return NANOS;
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
}
