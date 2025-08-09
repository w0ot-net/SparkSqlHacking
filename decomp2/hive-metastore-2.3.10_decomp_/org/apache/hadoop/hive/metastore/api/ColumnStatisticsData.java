package org.apache.hadoop.hive.metastore.api;

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
import org.apache.thrift.TBaseHelper;
import org.apache.thrift.TEnum;
import org.apache.thrift.TException;
import org.apache.thrift.TFieldIdEnum;
import org.apache.thrift.TUnion;
import org.apache.thrift.annotation.Nullable;
import org.apache.thrift.meta_data.FieldMetaData;
import org.apache.thrift.meta_data.StructMetaData;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TField;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.protocol.TProtocolUtil;
import org.apache.thrift.protocol.TStruct;
import org.apache.thrift.transport.TIOStreamTransport;

public class ColumnStatisticsData extends TUnion {
   private static final TStruct STRUCT_DESC = new TStruct("ColumnStatisticsData");
   private static final TField BOOLEAN_STATS_FIELD_DESC = new TField("booleanStats", (byte)12, (short)1);
   private static final TField LONG_STATS_FIELD_DESC = new TField("longStats", (byte)12, (short)2);
   private static final TField DOUBLE_STATS_FIELD_DESC = new TField("doubleStats", (byte)12, (short)3);
   private static final TField STRING_STATS_FIELD_DESC = new TField("stringStats", (byte)12, (short)4);
   private static final TField BINARY_STATS_FIELD_DESC = new TField("binaryStats", (byte)12, (short)5);
   private static final TField DECIMAL_STATS_FIELD_DESC = new TField("decimalStats", (byte)12, (short)6);
   private static final TField DATE_STATS_FIELD_DESC = new TField("dateStats", (byte)12, (short)7);
   public static final Map metaDataMap;

   public ColumnStatisticsData() {
   }

   public ColumnStatisticsData(_Fields setField, Object value) {
      super(setField, value);
   }

   public ColumnStatisticsData(ColumnStatisticsData other) {
      super(other);
   }

   public ColumnStatisticsData deepCopy() {
      return new ColumnStatisticsData(this);
   }

   public static ColumnStatisticsData booleanStats(BooleanColumnStatsData value) {
      ColumnStatisticsData x = new ColumnStatisticsData();
      x.setBooleanStats(value);
      return x;
   }

   public static ColumnStatisticsData longStats(LongColumnStatsData value) {
      ColumnStatisticsData x = new ColumnStatisticsData();
      x.setLongStats(value);
      return x;
   }

   public static ColumnStatisticsData doubleStats(DoubleColumnStatsData value) {
      ColumnStatisticsData x = new ColumnStatisticsData();
      x.setDoubleStats(value);
      return x;
   }

   public static ColumnStatisticsData stringStats(StringColumnStatsData value) {
      ColumnStatisticsData x = new ColumnStatisticsData();
      x.setStringStats(value);
      return x;
   }

   public static ColumnStatisticsData binaryStats(BinaryColumnStatsData value) {
      ColumnStatisticsData x = new ColumnStatisticsData();
      x.setBinaryStats(value);
      return x;
   }

   public static ColumnStatisticsData decimalStats(DecimalColumnStatsData value) {
      ColumnStatisticsData x = new ColumnStatisticsData();
      x.setDecimalStats(value);
      return x;
   }

   public static ColumnStatisticsData dateStats(DateColumnStatsData value) {
      ColumnStatisticsData x = new ColumnStatisticsData();
      x.setDateStats(value);
      return x;
   }

   protected void checkType(_Fields setField, Object value) throws ClassCastException {
      switch (setField) {
         case BOOLEAN_STATS:
            if (!(value instanceof BooleanColumnStatsData)) {
               throw new ClassCastException("Was expecting value of type BooleanColumnStatsData for field 'booleanStats', but got " + value.getClass().getSimpleName());
            }
            break;
         case LONG_STATS:
            if (!(value instanceof LongColumnStatsData)) {
               throw new ClassCastException("Was expecting value of type LongColumnStatsData for field 'longStats', but got " + value.getClass().getSimpleName());
            }
            break;
         case DOUBLE_STATS:
            if (!(value instanceof DoubleColumnStatsData)) {
               throw new ClassCastException("Was expecting value of type DoubleColumnStatsData for field 'doubleStats', but got " + value.getClass().getSimpleName());
            }
            break;
         case STRING_STATS:
            if (!(value instanceof StringColumnStatsData)) {
               throw new ClassCastException("Was expecting value of type StringColumnStatsData for field 'stringStats', but got " + value.getClass().getSimpleName());
            }
            break;
         case BINARY_STATS:
            if (!(value instanceof BinaryColumnStatsData)) {
               throw new ClassCastException("Was expecting value of type BinaryColumnStatsData for field 'binaryStats', but got " + value.getClass().getSimpleName());
            }
            break;
         case DECIMAL_STATS:
            if (!(value instanceof DecimalColumnStatsData)) {
               throw new ClassCastException("Was expecting value of type DecimalColumnStatsData for field 'decimalStats', but got " + value.getClass().getSimpleName());
            }
            break;
         case DATE_STATS:
            if (!(value instanceof DateColumnStatsData)) {
               throw new ClassCastException("Was expecting value of type DateColumnStatsData for field 'dateStats', but got " + value.getClass().getSimpleName());
            }
            break;
         default:
            throw new IllegalArgumentException("Unknown field id " + setField);
      }

   }

   protected Object standardSchemeReadValue(TProtocol iprot, TField field) throws TException {
      _Fields setField = ColumnStatisticsData._Fields.findByThriftId(field.id);
      if (setField != null) {
         switch (setField) {
            case BOOLEAN_STATS:
               if (field.type == BOOLEAN_STATS_FIELD_DESC.type) {
                  BooleanColumnStatsData booleanStats = new BooleanColumnStatsData();
                  booleanStats.read(iprot);
                  return booleanStats;
               }

               TProtocolUtil.skip(iprot, field.type);
               return null;
            case LONG_STATS:
               if (field.type == LONG_STATS_FIELD_DESC.type) {
                  LongColumnStatsData longStats = new LongColumnStatsData();
                  longStats.read(iprot);
                  return longStats;
               }

               TProtocolUtil.skip(iprot, field.type);
               return null;
            case DOUBLE_STATS:
               if (field.type == DOUBLE_STATS_FIELD_DESC.type) {
                  DoubleColumnStatsData doubleStats = new DoubleColumnStatsData();
                  doubleStats.read(iprot);
                  return doubleStats;
               }

               TProtocolUtil.skip(iprot, field.type);
               return null;
            case STRING_STATS:
               if (field.type == STRING_STATS_FIELD_DESC.type) {
                  StringColumnStatsData stringStats = new StringColumnStatsData();
                  stringStats.read(iprot);
                  return stringStats;
               }

               TProtocolUtil.skip(iprot, field.type);
               return null;
            case BINARY_STATS:
               if (field.type == BINARY_STATS_FIELD_DESC.type) {
                  BinaryColumnStatsData binaryStats = new BinaryColumnStatsData();
                  binaryStats.read(iprot);
                  return binaryStats;
               }

               TProtocolUtil.skip(iprot, field.type);
               return null;
            case DECIMAL_STATS:
               if (field.type == DECIMAL_STATS_FIELD_DESC.type) {
                  DecimalColumnStatsData decimalStats = new DecimalColumnStatsData();
                  decimalStats.read(iprot);
                  return decimalStats;
               }

               TProtocolUtil.skip(iprot, field.type);
               return null;
            case DATE_STATS:
               if (field.type == DATE_STATS_FIELD_DESC.type) {
                  DateColumnStatsData dateStats = new DateColumnStatsData();
                  dateStats.read(iprot);
                  return dateStats;
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
         case BOOLEAN_STATS:
            BooleanColumnStatsData booleanStats = (BooleanColumnStatsData)this.value_;
            booleanStats.write(oprot);
            return;
         case LONG_STATS:
            LongColumnStatsData longStats = (LongColumnStatsData)this.value_;
            longStats.write(oprot);
            return;
         case DOUBLE_STATS:
            DoubleColumnStatsData doubleStats = (DoubleColumnStatsData)this.value_;
            doubleStats.write(oprot);
            return;
         case STRING_STATS:
            StringColumnStatsData stringStats = (StringColumnStatsData)this.value_;
            stringStats.write(oprot);
            return;
         case BINARY_STATS:
            BinaryColumnStatsData binaryStats = (BinaryColumnStatsData)this.value_;
            binaryStats.write(oprot);
            return;
         case DECIMAL_STATS:
            DecimalColumnStatsData decimalStats = (DecimalColumnStatsData)this.value_;
            decimalStats.write(oprot);
            return;
         case DATE_STATS:
            DateColumnStatsData dateStats = (DateColumnStatsData)this.value_;
            dateStats.write(oprot);
            return;
         default:
            throw new IllegalStateException("Cannot write union with unknown field " + this.setField_);
      }
   }

   protected Object tupleSchemeReadValue(TProtocol iprot, short fieldID) throws TException {
      _Fields setField = ColumnStatisticsData._Fields.findByThriftId(fieldID);
      if (setField != null) {
         switch (setField) {
            case BOOLEAN_STATS:
               BooleanColumnStatsData booleanStats = new BooleanColumnStatsData();
               booleanStats.read(iprot);
               return booleanStats;
            case LONG_STATS:
               LongColumnStatsData longStats = new LongColumnStatsData();
               longStats.read(iprot);
               return longStats;
            case DOUBLE_STATS:
               DoubleColumnStatsData doubleStats = new DoubleColumnStatsData();
               doubleStats.read(iprot);
               return doubleStats;
            case STRING_STATS:
               StringColumnStatsData stringStats = new StringColumnStatsData();
               stringStats.read(iprot);
               return stringStats;
            case BINARY_STATS:
               BinaryColumnStatsData binaryStats = new BinaryColumnStatsData();
               binaryStats.read(iprot);
               return binaryStats;
            case DECIMAL_STATS:
               DecimalColumnStatsData decimalStats = new DecimalColumnStatsData();
               decimalStats.read(iprot);
               return decimalStats;
            case DATE_STATS:
               DateColumnStatsData dateStats = new DateColumnStatsData();
               dateStats.read(iprot);
               return dateStats;
            default:
               throw new IllegalStateException("setField wasn't null, but didn't match any of the case statements!");
         }
      } else {
         throw new TProtocolException("Couldn't find a field with field id " + fieldID);
      }
   }

   protected void tupleSchemeWriteValue(TProtocol oprot) throws TException {
      switch ((_Fields)this.setField_) {
         case BOOLEAN_STATS:
            BooleanColumnStatsData booleanStats = (BooleanColumnStatsData)this.value_;
            booleanStats.write(oprot);
            return;
         case LONG_STATS:
            LongColumnStatsData longStats = (LongColumnStatsData)this.value_;
            longStats.write(oprot);
            return;
         case DOUBLE_STATS:
            DoubleColumnStatsData doubleStats = (DoubleColumnStatsData)this.value_;
            doubleStats.write(oprot);
            return;
         case STRING_STATS:
            StringColumnStatsData stringStats = (StringColumnStatsData)this.value_;
            stringStats.write(oprot);
            return;
         case BINARY_STATS:
            BinaryColumnStatsData binaryStats = (BinaryColumnStatsData)this.value_;
            binaryStats.write(oprot);
            return;
         case DECIMAL_STATS:
            DecimalColumnStatsData decimalStats = (DecimalColumnStatsData)this.value_;
            decimalStats.write(oprot);
            return;
         case DATE_STATS:
            DateColumnStatsData dateStats = (DateColumnStatsData)this.value_;
            dateStats.write(oprot);
            return;
         default:
            throw new IllegalStateException("Cannot write union with unknown field " + this.setField_);
      }
   }

   protected TField getFieldDesc(_Fields setField) {
      switch (setField) {
         case BOOLEAN_STATS:
            return BOOLEAN_STATS_FIELD_DESC;
         case LONG_STATS:
            return LONG_STATS_FIELD_DESC;
         case DOUBLE_STATS:
            return DOUBLE_STATS_FIELD_DESC;
         case STRING_STATS:
            return STRING_STATS_FIELD_DESC;
         case BINARY_STATS:
            return BINARY_STATS_FIELD_DESC;
         case DECIMAL_STATS:
            return DECIMAL_STATS_FIELD_DESC;
         case DATE_STATS:
            return DATE_STATS_FIELD_DESC;
         default:
            throw new IllegalArgumentException("Unknown field id " + setField);
      }
   }

   protected TStruct getStructDesc() {
      return STRUCT_DESC;
   }

   protected _Fields enumForId(short id) {
      return ColumnStatisticsData._Fields.findByThriftIdOrThrow(id);
   }

   @Nullable
   public _Fields fieldForId(int fieldId) {
      return ColumnStatisticsData._Fields.findByThriftId(fieldId);
   }

   public BooleanColumnStatsData getBooleanStats() {
      if (this.getSetField() == ColumnStatisticsData._Fields.BOOLEAN_STATS) {
         return (BooleanColumnStatsData)this.getFieldValue();
      } else {
         throw new RuntimeException("Cannot get field 'booleanStats' because union is currently set to " + this.getFieldDesc((_Fields)this.getSetField()).name);
      }
   }

   public void setBooleanStats(BooleanColumnStatsData value) {
      this.setField_ = ColumnStatisticsData._Fields.BOOLEAN_STATS;
      this.value_ = Objects.requireNonNull(value, "_Fields.BOOLEAN_STATS");
   }

   public LongColumnStatsData getLongStats() {
      if (this.getSetField() == ColumnStatisticsData._Fields.LONG_STATS) {
         return (LongColumnStatsData)this.getFieldValue();
      } else {
         throw new RuntimeException("Cannot get field 'longStats' because union is currently set to " + this.getFieldDesc((_Fields)this.getSetField()).name);
      }
   }

   public void setLongStats(LongColumnStatsData value) {
      this.setField_ = ColumnStatisticsData._Fields.LONG_STATS;
      this.value_ = Objects.requireNonNull(value, "_Fields.LONG_STATS");
   }

   public DoubleColumnStatsData getDoubleStats() {
      if (this.getSetField() == ColumnStatisticsData._Fields.DOUBLE_STATS) {
         return (DoubleColumnStatsData)this.getFieldValue();
      } else {
         throw new RuntimeException("Cannot get field 'doubleStats' because union is currently set to " + this.getFieldDesc((_Fields)this.getSetField()).name);
      }
   }

   public void setDoubleStats(DoubleColumnStatsData value) {
      this.setField_ = ColumnStatisticsData._Fields.DOUBLE_STATS;
      this.value_ = Objects.requireNonNull(value, "_Fields.DOUBLE_STATS");
   }

   public StringColumnStatsData getStringStats() {
      if (this.getSetField() == ColumnStatisticsData._Fields.STRING_STATS) {
         return (StringColumnStatsData)this.getFieldValue();
      } else {
         throw new RuntimeException("Cannot get field 'stringStats' because union is currently set to " + this.getFieldDesc((_Fields)this.getSetField()).name);
      }
   }

   public void setStringStats(StringColumnStatsData value) {
      this.setField_ = ColumnStatisticsData._Fields.STRING_STATS;
      this.value_ = Objects.requireNonNull(value, "_Fields.STRING_STATS");
   }

   public BinaryColumnStatsData getBinaryStats() {
      if (this.getSetField() == ColumnStatisticsData._Fields.BINARY_STATS) {
         return (BinaryColumnStatsData)this.getFieldValue();
      } else {
         throw new RuntimeException("Cannot get field 'binaryStats' because union is currently set to " + this.getFieldDesc((_Fields)this.getSetField()).name);
      }
   }

   public void setBinaryStats(BinaryColumnStatsData value) {
      this.setField_ = ColumnStatisticsData._Fields.BINARY_STATS;
      this.value_ = Objects.requireNonNull(value, "_Fields.BINARY_STATS");
   }

   public DecimalColumnStatsData getDecimalStats() {
      if (this.getSetField() == ColumnStatisticsData._Fields.DECIMAL_STATS) {
         return (DecimalColumnStatsData)this.getFieldValue();
      } else {
         throw new RuntimeException("Cannot get field 'decimalStats' because union is currently set to " + this.getFieldDesc((_Fields)this.getSetField()).name);
      }
   }

   public void setDecimalStats(DecimalColumnStatsData value) {
      this.setField_ = ColumnStatisticsData._Fields.DECIMAL_STATS;
      this.value_ = Objects.requireNonNull(value, "_Fields.DECIMAL_STATS");
   }

   public DateColumnStatsData getDateStats() {
      if (this.getSetField() == ColumnStatisticsData._Fields.DATE_STATS) {
         return (DateColumnStatsData)this.getFieldValue();
      } else {
         throw new RuntimeException("Cannot get field 'dateStats' because union is currently set to " + this.getFieldDesc((_Fields)this.getSetField()).name);
      }
   }

   public void setDateStats(DateColumnStatsData value) {
      this.setField_ = ColumnStatisticsData._Fields.DATE_STATS;
      this.value_ = Objects.requireNonNull(value, "_Fields.DATE_STATS");
   }

   public boolean isSetBooleanStats() {
      return this.setField_ == ColumnStatisticsData._Fields.BOOLEAN_STATS;
   }

   public boolean isSetLongStats() {
      return this.setField_ == ColumnStatisticsData._Fields.LONG_STATS;
   }

   public boolean isSetDoubleStats() {
      return this.setField_ == ColumnStatisticsData._Fields.DOUBLE_STATS;
   }

   public boolean isSetStringStats() {
      return this.setField_ == ColumnStatisticsData._Fields.STRING_STATS;
   }

   public boolean isSetBinaryStats() {
      return this.setField_ == ColumnStatisticsData._Fields.BINARY_STATS;
   }

   public boolean isSetDecimalStats() {
      return this.setField_ == ColumnStatisticsData._Fields.DECIMAL_STATS;
   }

   public boolean isSetDateStats() {
      return this.setField_ == ColumnStatisticsData._Fields.DATE_STATS;
   }

   public boolean equals(Object other) {
      return other instanceof ColumnStatisticsData ? this.equals((ColumnStatisticsData)other) : false;
   }

   public boolean equals(ColumnStatisticsData other) {
      return other != null && this.getSetField() == other.getSetField() && this.getFieldValue().equals(other.getFieldValue());
   }

   public int compareTo(ColumnStatisticsData other) {
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
      tmpMap.put(ColumnStatisticsData._Fields.BOOLEAN_STATS, new FieldMetaData("booleanStats", (byte)2, new StructMetaData((byte)12, BooleanColumnStatsData.class)));
      tmpMap.put(ColumnStatisticsData._Fields.LONG_STATS, new FieldMetaData("longStats", (byte)2, new StructMetaData((byte)12, LongColumnStatsData.class)));
      tmpMap.put(ColumnStatisticsData._Fields.DOUBLE_STATS, new FieldMetaData("doubleStats", (byte)2, new StructMetaData((byte)12, DoubleColumnStatsData.class)));
      tmpMap.put(ColumnStatisticsData._Fields.STRING_STATS, new FieldMetaData("stringStats", (byte)2, new StructMetaData((byte)12, StringColumnStatsData.class)));
      tmpMap.put(ColumnStatisticsData._Fields.BINARY_STATS, new FieldMetaData("binaryStats", (byte)2, new StructMetaData((byte)12, BinaryColumnStatsData.class)));
      tmpMap.put(ColumnStatisticsData._Fields.DECIMAL_STATS, new FieldMetaData("decimalStats", (byte)2, new StructMetaData((byte)12, DecimalColumnStatsData.class)));
      tmpMap.put(ColumnStatisticsData._Fields.DATE_STATS, new FieldMetaData("dateStats", (byte)2, new StructMetaData((byte)12, DateColumnStatsData.class)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(ColumnStatisticsData.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      BOOLEAN_STATS((short)1, "booleanStats"),
      LONG_STATS((short)2, "longStats"),
      DOUBLE_STATS((short)3, "doubleStats"),
      STRING_STATS((short)4, "stringStats"),
      BINARY_STATS((short)5, "binaryStats"),
      DECIMAL_STATS((short)6, "decimalStats"),
      DATE_STATS((short)7, "dateStats");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return BOOLEAN_STATS;
            case 2:
               return LONG_STATS;
            case 3:
               return DOUBLE_STATS;
            case 4:
               return STRING_STATS;
            case 5:
               return BINARY_STATS;
            case 6:
               return DECIMAL_STATS;
            case 7:
               return DATE_STATS;
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
