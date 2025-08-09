package org.apache.arrow.vector.complex.impl;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import org.apache.arrow.vector.PeriodDuration;
import org.apache.arrow.vector.complex.reader.FieldReader;
import org.apache.arrow.vector.complex.writer.BaseWriter;
import org.apache.arrow.vector.complex.writer.BigIntWriter;
import org.apache.arrow.vector.complex.writer.BitWriter;
import org.apache.arrow.vector.complex.writer.DateDayWriter;
import org.apache.arrow.vector.complex.writer.DateMilliWriter;
import org.apache.arrow.vector.complex.writer.Decimal256Writer;
import org.apache.arrow.vector.complex.writer.DecimalWriter;
import org.apache.arrow.vector.complex.writer.DurationWriter;
import org.apache.arrow.vector.complex.writer.FixedSizeBinaryWriter;
import org.apache.arrow.vector.complex.writer.Float2Writer;
import org.apache.arrow.vector.complex.writer.Float4Writer;
import org.apache.arrow.vector.complex.writer.Float8Writer;
import org.apache.arrow.vector.complex.writer.IntWriter;
import org.apache.arrow.vector.complex.writer.IntervalDayWriter;
import org.apache.arrow.vector.complex.writer.IntervalMonthDayNanoWriter;
import org.apache.arrow.vector.complex.writer.IntervalYearWriter;
import org.apache.arrow.vector.complex.writer.LargeVarBinaryWriter;
import org.apache.arrow.vector.complex.writer.LargeVarCharWriter;
import org.apache.arrow.vector.complex.writer.SmallIntWriter;
import org.apache.arrow.vector.complex.writer.TimeMicroWriter;
import org.apache.arrow.vector.complex.writer.TimeMilliWriter;
import org.apache.arrow.vector.complex.writer.TimeNanoWriter;
import org.apache.arrow.vector.complex.writer.TimeSecWriter;
import org.apache.arrow.vector.complex.writer.TimeStampMicroTZWriter;
import org.apache.arrow.vector.complex.writer.TimeStampMicroWriter;
import org.apache.arrow.vector.complex.writer.TimeStampMilliTZWriter;
import org.apache.arrow.vector.complex.writer.TimeStampMilliWriter;
import org.apache.arrow.vector.complex.writer.TimeStampNanoTZWriter;
import org.apache.arrow.vector.complex.writer.TimeStampNanoWriter;
import org.apache.arrow.vector.complex.writer.TimeStampSecTZWriter;
import org.apache.arrow.vector.complex.writer.TimeStampSecWriter;
import org.apache.arrow.vector.complex.writer.TinyIntWriter;
import org.apache.arrow.vector.complex.writer.UInt1Writer;
import org.apache.arrow.vector.complex.writer.UInt2Writer;
import org.apache.arrow.vector.complex.writer.UInt4Writer;
import org.apache.arrow.vector.complex.writer.UInt8Writer;
import org.apache.arrow.vector.complex.writer.VarBinaryWriter;
import org.apache.arrow.vector.complex.writer.VarCharWriter;
import org.apache.arrow.vector.complex.writer.ViewVarBinaryWriter;
import org.apache.arrow.vector.complex.writer.ViewVarCharWriter;
import org.apache.arrow.vector.holders.BigIntHolder;
import org.apache.arrow.vector.holders.BitHolder;
import org.apache.arrow.vector.holders.DateDayHolder;
import org.apache.arrow.vector.holders.DateMilliHolder;
import org.apache.arrow.vector.holders.Decimal256Holder;
import org.apache.arrow.vector.holders.DecimalHolder;
import org.apache.arrow.vector.holders.DurationHolder;
import org.apache.arrow.vector.holders.FixedSizeBinaryHolder;
import org.apache.arrow.vector.holders.Float2Holder;
import org.apache.arrow.vector.holders.Float4Holder;
import org.apache.arrow.vector.holders.Float8Holder;
import org.apache.arrow.vector.holders.IntHolder;
import org.apache.arrow.vector.holders.IntervalDayHolder;
import org.apache.arrow.vector.holders.IntervalMonthDayNanoHolder;
import org.apache.arrow.vector.holders.IntervalYearHolder;
import org.apache.arrow.vector.holders.LargeVarBinaryHolder;
import org.apache.arrow.vector.holders.LargeVarCharHolder;
import org.apache.arrow.vector.holders.NullableBigIntHolder;
import org.apache.arrow.vector.holders.NullableBitHolder;
import org.apache.arrow.vector.holders.NullableDateDayHolder;
import org.apache.arrow.vector.holders.NullableDateMilliHolder;
import org.apache.arrow.vector.holders.NullableDecimal256Holder;
import org.apache.arrow.vector.holders.NullableDecimalHolder;
import org.apache.arrow.vector.holders.NullableDurationHolder;
import org.apache.arrow.vector.holders.NullableFixedSizeBinaryHolder;
import org.apache.arrow.vector.holders.NullableFloat2Holder;
import org.apache.arrow.vector.holders.NullableFloat4Holder;
import org.apache.arrow.vector.holders.NullableFloat8Holder;
import org.apache.arrow.vector.holders.NullableIntHolder;
import org.apache.arrow.vector.holders.NullableIntervalDayHolder;
import org.apache.arrow.vector.holders.NullableIntervalMonthDayNanoHolder;
import org.apache.arrow.vector.holders.NullableIntervalYearHolder;
import org.apache.arrow.vector.holders.NullableLargeVarBinaryHolder;
import org.apache.arrow.vector.holders.NullableLargeVarCharHolder;
import org.apache.arrow.vector.holders.NullableSmallIntHolder;
import org.apache.arrow.vector.holders.NullableTimeMicroHolder;
import org.apache.arrow.vector.holders.NullableTimeMilliHolder;
import org.apache.arrow.vector.holders.NullableTimeNanoHolder;
import org.apache.arrow.vector.holders.NullableTimeSecHolder;
import org.apache.arrow.vector.holders.NullableTimeStampMicroHolder;
import org.apache.arrow.vector.holders.NullableTimeStampMicroTZHolder;
import org.apache.arrow.vector.holders.NullableTimeStampMilliHolder;
import org.apache.arrow.vector.holders.NullableTimeStampMilliTZHolder;
import org.apache.arrow.vector.holders.NullableTimeStampNanoHolder;
import org.apache.arrow.vector.holders.NullableTimeStampNanoTZHolder;
import org.apache.arrow.vector.holders.NullableTimeStampSecHolder;
import org.apache.arrow.vector.holders.NullableTimeStampSecTZHolder;
import org.apache.arrow.vector.holders.NullableTinyIntHolder;
import org.apache.arrow.vector.holders.NullableUInt1Holder;
import org.apache.arrow.vector.holders.NullableUInt2Holder;
import org.apache.arrow.vector.holders.NullableUInt4Holder;
import org.apache.arrow.vector.holders.NullableUInt8Holder;
import org.apache.arrow.vector.holders.NullableVarBinaryHolder;
import org.apache.arrow.vector.holders.NullableVarCharHolder;
import org.apache.arrow.vector.holders.NullableViewVarBinaryHolder;
import org.apache.arrow.vector.holders.NullableViewVarCharHolder;
import org.apache.arrow.vector.holders.SmallIntHolder;
import org.apache.arrow.vector.holders.TimeMicroHolder;
import org.apache.arrow.vector.holders.TimeMilliHolder;
import org.apache.arrow.vector.holders.TimeNanoHolder;
import org.apache.arrow.vector.holders.TimeSecHolder;
import org.apache.arrow.vector.holders.TimeStampMicroHolder;
import org.apache.arrow.vector.holders.TimeStampMicroTZHolder;
import org.apache.arrow.vector.holders.TimeStampMilliHolder;
import org.apache.arrow.vector.holders.TimeStampMilliTZHolder;
import org.apache.arrow.vector.holders.TimeStampNanoHolder;
import org.apache.arrow.vector.holders.TimeStampNanoTZHolder;
import org.apache.arrow.vector.holders.TimeStampSecHolder;
import org.apache.arrow.vector.holders.TimeStampSecTZHolder;
import org.apache.arrow.vector.holders.TinyIntHolder;
import org.apache.arrow.vector.holders.UInt1Holder;
import org.apache.arrow.vector.holders.UInt2Holder;
import org.apache.arrow.vector.holders.UInt4Holder;
import org.apache.arrow.vector.holders.UInt8Holder;
import org.apache.arrow.vector.holders.VarBinaryHolder;
import org.apache.arrow.vector.holders.VarCharHolder;
import org.apache.arrow.vector.holders.ViewVarBinaryHolder;
import org.apache.arrow.vector.holders.ViewVarCharHolder;
import org.apache.arrow.vector.types.pojo.Field;
import org.apache.arrow.vector.util.Text;

abstract class AbstractFieldReader extends AbstractBaseReader implements FieldReader {
   public boolean isSet() {
      return true;
   }

   public Field getField() {
      this.fail("getField");
      return null;
   }

   public Object readObject(int arrayIndex) {
      this.fail("readObject(int arrayIndex)");
      return null;
   }

   public Object readObject() {
      this.fail("readObject()");
      return null;
   }

   public BigDecimal readBigDecimal(int arrayIndex) {
      this.fail("readBigDecimal(int arrayIndex)");
      return null;
   }

   public BigDecimal readBigDecimal() {
      this.fail("readBigDecimal()");
      return null;
   }

   public Short readShort(int arrayIndex) {
      this.fail("readShort(int arrayIndex)");
      return null;
   }

   public Short readShort() {
      this.fail("readShort()");
      return null;
   }

   public Integer readInteger(int arrayIndex) {
      this.fail("readInteger(int arrayIndex)");
      return null;
   }

   public Integer readInteger() {
      this.fail("readInteger()");
      return null;
   }

   public Long readLong(int arrayIndex) {
      this.fail("readLong(int arrayIndex)");
      return null;
   }

   public Long readLong() {
      this.fail("readLong()");
      return null;
   }

   public Boolean readBoolean(int arrayIndex) {
      this.fail("readBoolean(int arrayIndex)");
      return null;
   }

   public Boolean readBoolean() {
      this.fail("readBoolean()");
      return null;
   }

   public LocalDateTime readLocalDateTime(int arrayIndex) {
      this.fail("readLocalDateTime(int arrayIndex)");
      return null;
   }

   public LocalDateTime readLocalDateTime() {
      this.fail("readLocalDateTime()");
      return null;
   }

   public Duration readDuration(int arrayIndex) {
      this.fail("readDuration(int arrayIndex)");
      return null;
   }

   public Duration readDuration() {
      this.fail("readDuration()");
      return null;
   }

   public Period readPeriod(int arrayIndex) {
      this.fail("readPeriod(int arrayIndex)");
      return null;
   }

   public Period readPeriod() {
      this.fail("readPeriod()");
      return null;
   }

   public Double readDouble(int arrayIndex) {
      this.fail("readDouble(int arrayIndex)");
      return null;
   }

   public Double readDouble() {
      this.fail("readDouble()");
      return null;
   }

   public Float readFloat(int arrayIndex) {
      this.fail("readFloat(int arrayIndex)");
      return null;
   }

   public Float readFloat() {
      this.fail("readFloat()");
      return null;
   }

   public Character readCharacter(int arrayIndex) {
      this.fail("readCharacter(int arrayIndex)");
      return null;
   }

   public Character readCharacter() {
      this.fail("readCharacter()");
      return null;
   }

   public Text readText(int arrayIndex) {
      this.fail("readText(int arrayIndex)");
      return null;
   }

   public Text readText() {
      this.fail("readText()");
      return null;
   }

   public String readString(int arrayIndex) {
      this.fail("readString(int arrayIndex)");
      return null;
   }

   public String readString() {
      this.fail("readString()");
      return null;
   }

   public Byte readByte(int arrayIndex) {
      this.fail("readByte(int arrayIndex)");
      return null;
   }

   public Byte readByte() {
      this.fail("readByte()");
      return null;
   }

   public byte[] readByteArray(int arrayIndex) {
      this.fail("readByteArray(int arrayIndex)");
      return null;
   }

   public byte[] readByteArray() {
      this.fail("readByteArray()");
      return null;
   }

   public PeriodDuration readPeriodDuration(int arrayIndex) {
      this.fail("readPeriodDuration(int arrayIndex)");
      return null;
   }

   public PeriodDuration readPeriodDuration() {
      this.fail("readPeriodDuration()");
      return null;
   }

   public void copyAsValue(BaseWriter.StructWriter writer) {
      this.fail("CopyAsValue StructWriter");
   }

   public void copyAsField(String name, BaseWriter.StructWriter writer) {
      this.fail("CopyAsField StructWriter");
   }

   public void copyAsField(String name, BaseWriter.ListWriter writer) {
      this.fail("CopyAsFieldList");
   }

   public void copyAsField(String name, BaseWriter.MapWriter writer) {
      this.fail("CopyAsFieldMap");
   }

   public void read(TinyIntHolder holder) {
      this.fail("TinyInt");
   }

   public void read(NullableTinyIntHolder holder) {
      this.fail("TinyInt");
   }

   public void read(int arrayIndex, TinyIntHolder holder) {
      this.fail("RepeatedTinyInt");
   }

   public void read(int arrayIndex, NullableTinyIntHolder holder) {
      this.fail("RepeatedTinyInt");
   }

   public void copyAsValue(TinyIntWriter writer) {
      this.fail("CopyAsValueTinyInt");
   }

   public void copyAsField(String name, TinyIntWriter writer) {
      this.fail("CopyAsFieldTinyInt");
   }

   public void read(UInt1Holder holder) {
      this.fail("UInt1");
   }

   public void read(NullableUInt1Holder holder) {
      this.fail("UInt1");
   }

   public void read(int arrayIndex, UInt1Holder holder) {
      this.fail("RepeatedUInt1");
   }

   public void read(int arrayIndex, NullableUInt1Holder holder) {
      this.fail("RepeatedUInt1");
   }

   public void copyAsValue(UInt1Writer writer) {
      this.fail("CopyAsValueUInt1");
   }

   public void copyAsField(String name, UInt1Writer writer) {
      this.fail("CopyAsFieldUInt1");
   }

   public void read(UInt2Holder holder) {
      this.fail("UInt2");
   }

   public void read(NullableUInt2Holder holder) {
      this.fail("UInt2");
   }

   public void read(int arrayIndex, UInt2Holder holder) {
      this.fail("RepeatedUInt2");
   }

   public void read(int arrayIndex, NullableUInt2Holder holder) {
      this.fail("RepeatedUInt2");
   }

   public void copyAsValue(UInt2Writer writer) {
      this.fail("CopyAsValueUInt2");
   }

   public void copyAsField(String name, UInt2Writer writer) {
      this.fail("CopyAsFieldUInt2");
   }

   public void read(SmallIntHolder holder) {
      this.fail("SmallInt");
   }

   public void read(NullableSmallIntHolder holder) {
      this.fail("SmallInt");
   }

   public void read(int arrayIndex, SmallIntHolder holder) {
      this.fail("RepeatedSmallInt");
   }

   public void read(int arrayIndex, NullableSmallIntHolder holder) {
      this.fail("RepeatedSmallInt");
   }

   public void copyAsValue(SmallIntWriter writer) {
      this.fail("CopyAsValueSmallInt");
   }

   public void copyAsField(String name, SmallIntWriter writer) {
      this.fail("CopyAsFieldSmallInt");
   }

   public void read(Float2Holder holder) {
      this.fail("Float2");
   }

   public void read(NullableFloat2Holder holder) {
      this.fail("Float2");
   }

   public void read(int arrayIndex, Float2Holder holder) {
      this.fail("RepeatedFloat2");
   }

   public void read(int arrayIndex, NullableFloat2Holder holder) {
      this.fail("RepeatedFloat2");
   }

   public void copyAsValue(Float2Writer writer) {
      this.fail("CopyAsValueFloat2");
   }

   public void copyAsField(String name, Float2Writer writer) {
      this.fail("CopyAsFieldFloat2");
   }

   public void read(IntHolder holder) {
      this.fail("Int");
   }

   public void read(NullableIntHolder holder) {
      this.fail("Int");
   }

   public void read(int arrayIndex, IntHolder holder) {
      this.fail("RepeatedInt");
   }

   public void read(int arrayIndex, NullableIntHolder holder) {
      this.fail("RepeatedInt");
   }

   public void copyAsValue(IntWriter writer) {
      this.fail("CopyAsValueInt");
   }

   public void copyAsField(String name, IntWriter writer) {
      this.fail("CopyAsFieldInt");
   }

   public void read(UInt4Holder holder) {
      this.fail("UInt4");
   }

   public void read(NullableUInt4Holder holder) {
      this.fail("UInt4");
   }

   public void read(int arrayIndex, UInt4Holder holder) {
      this.fail("RepeatedUInt4");
   }

   public void read(int arrayIndex, NullableUInt4Holder holder) {
      this.fail("RepeatedUInt4");
   }

   public void copyAsValue(UInt4Writer writer) {
      this.fail("CopyAsValueUInt4");
   }

   public void copyAsField(String name, UInt4Writer writer) {
      this.fail("CopyAsFieldUInt4");
   }

   public void read(Float4Holder holder) {
      this.fail("Float4");
   }

   public void read(NullableFloat4Holder holder) {
      this.fail("Float4");
   }

   public void read(int arrayIndex, Float4Holder holder) {
      this.fail("RepeatedFloat4");
   }

   public void read(int arrayIndex, NullableFloat4Holder holder) {
      this.fail("RepeatedFloat4");
   }

   public void copyAsValue(Float4Writer writer) {
      this.fail("CopyAsValueFloat4");
   }

   public void copyAsField(String name, Float4Writer writer) {
      this.fail("CopyAsFieldFloat4");
   }

   public void read(DateDayHolder holder) {
      this.fail("DateDay");
   }

   public void read(NullableDateDayHolder holder) {
      this.fail("DateDay");
   }

   public void read(int arrayIndex, DateDayHolder holder) {
      this.fail("RepeatedDateDay");
   }

   public void read(int arrayIndex, NullableDateDayHolder holder) {
      this.fail("RepeatedDateDay");
   }

   public void copyAsValue(DateDayWriter writer) {
      this.fail("CopyAsValueDateDay");
   }

   public void copyAsField(String name, DateDayWriter writer) {
      this.fail("CopyAsFieldDateDay");
   }

   public void read(IntervalYearHolder holder) {
      this.fail("IntervalYear");
   }

   public void read(NullableIntervalYearHolder holder) {
      this.fail("IntervalYear");
   }

   public void read(int arrayIndex, IntervalYearHolder holder) {
      this.fail("RepeatedIntervalYear");
   }

   public void read(int arrayIndex, NullableIntervalYearHolder holder) {
      this.fail("RepeatedIntervalYear");
   }

   public void copyAsValue(IntervalYearWriter writer) {
      this.fail("CopyAsValueIntervalYear");
   }

   public void copyAsField(String name, IntervalYearWriter writer) {
      this.fail("CopyAsFieldIntervalYear");
   }

   public void read(TimeSecHolder holder) {
      this.fail("TimeSec");
   }

   public void read(NullableTimeSecHolder holder) {
      this.fail("TimeSec");
   }

   public void read(int arrayIndex, TimeSecHolder holder) {
      this.fail("RepeatedTimeSec");
   }

   public void read(int arrayIndex, NullableTimeSecHolder holder) {
      this.fail("RepeatedTimeSec");
   }

   public void copyAsValue(TimeSecWriter writer) {
      this.fail("CopyAsValueTimeSec");
   }

   public void copyAsField(String name, TimeSecWriter writer) {
      this.fail("CopyAsFieldTimeSec");
   }

   public void read(TimeMilliHolder holder) {
      this.fail("TimeMilli");
   }

   public void read(NullableTimeMilliHolder holder) {
      this.fail("TimeMilli");
   }

   public void read(int arrayIndex, TimeMilliHolder holder) {
      this.fail("RepeatedTimeMilli");
   }

   public void read(int arrayIndex, NullableTimeMilliHolder holder) {
      this.fail("RepeatedTimeMilli");
   }

   public void copyAsValue(TimeMilliWriter writer) {
      this.fail("CopyAsValueTimeMilli");
   }

   public void copyAsField(String name, TimeMilliWriter writer) {
      this.fail("CopyAsFieldTimeMilli");
   }

   public void read(BigIntHolder holder) {
      this.fail("BigInt");
   }

   public void read(NullableBigIntHolder holder) {
      this.fail("BigInt");
   }

   public void read(int arrayIndex, BigIntHolder holder) {
      this.fail("RepeatedBigInt");
   }

   public void read(int arrayIndex, NullableBigIntHolder holder) {
      this.fail("RepeatedBigInt");
   }

   public void copyAsValue(BigIntWriter writer) {
      this.fail("CopyAsValueBigInt");
   }

   public void copyAsField(String name, BigIntWriter writer) {
      this.fail("CopyAsFieldBigInt");
   }

   public void read(UInt8Holder holder) {
      this.fail("UInt8");
   }

   public void read(NullableUInt8Holder holder) {
      this.fail("UInt8");
   }

   public void read(int arrayIndex, UInt8Holder holder) {
      this.fail("RepeatedUInt8");
   }

   public void read(int arrayIndex, NullableUInt8Holder holder) {
      this.fail("RepeatedUInt8");
   }

   public void copyAsValue(UInt8Writer writer) {
      this.fail("CopyAsValueUInt8");
   }

   public void copyAsField(String name, UInt8Writer writer) {
      this.fail("CopyAsFieldUInt8");
   }

   public void read(Float8Holder holder) {
      this.fail("Float8");
   }

   public void read(NullableFloat8Holder holder) {
      this.fail("Float8");
   }

   public void read(int arrayIndex, Float8Holder holder) {
      this.fail("RepeatedFloat8");
   }

   public void read(int arrayIndex, NullableFloat8Holder holder) {
      this.fail("RepeatedFloat8");
   }

   public void copyAsValue(Float8Writer writer) {
      this.fail("CopyAsValueFloat8");
   }

   public void copyAsField(String name, Float8Writer writer) {
      this.fail("CopyAsFieldFloat8");
   }

   public void read(DateMilliHolder holder) {
      this.fail("DateMilli");
   }

   public void read(NullableDateMilliHolder holder) {
      this.fail("DateMilli");
   }

   public void read(int arrayIndex, DateMilliHolder holder) {
      this.fail("RepeatedDateMilli");
   }

   public void read(int arrayIndex, NullableDateMilliHolder holder) {
      this.fail("RepeatedDateMilli");
   }

   public void copyAsValue(DateMilliWriter writer) {
      this.fail("CopyAsValueDateMilli");
   }

   public void copyAsField(String name, DateMilliWriter writer) {
      this.fail("CopyAsFieldDateMilli");
   }

   public void read(DurationHolder holder) {
      this.fail("Duration");
   }

   public void read(NullableDurationHolder holder) {
      this.fail("Duration");
   }

   public void read(int arrayIndex, DurationHolder holder) {
      this.fail("RepeatedDuration");
   }

   public void read(int arrayIndex, NullableDurationHolder holder) {
      this.fail("RepeatedDuration");
   }

   public void copyAsValue(DurationWriter writer) {
      this.fail("CopyAsValueDuration");
   }

   public void copyAsField(String name, DurationWriter writer) {
      this.fail("CopyAsFieldDuration");
   }

   public void read(TimeStampSecHolder holder) {
      this.fail("TimeStampSec");
   }

   public void read(NullableTimeStampSecHolder holder) {
      this.fail("TimeStampSec");
   }

   public void read(int arrayIndex, TimeStampSecHolder holder) {
      this.fail("RepeatedTimeStampSec");
   }

   public void read(int arrayIndex, NullableTimeStampSecHolder holder) {
      this.fail("RepeatedTimeStampSec");
   }

   public void copyAsValue(TimeStampSecWriter writer) {
      this.fail("CopyAsValueTimeStampSec");
   }

   public void copyAsField(String name, TimeStampSecWriter writer) {
      this.fail("CopyAsFieldTimeStampSec");
   }

   public void read(TimeStampMilliHolder holder) {
      this.fail("TimeStampMilli");
   }

   public void read(NullableTimeStampMilliHolder holder) {
      this.fail("TimeStampMilli");
   }

   public void read(int arrayIndex, TimeStampMilliHolder holder) {
      this.fail("RepeatedTimeStampMilli");
   }

   public void read(int arrayIndex, NullableTimeStampMilliHolder holder) {
      this.fail("RepeatedTimeStampMilli");
   }

   public void copyAsValue(TimeStampMilliWriter writer) {
      this.fail("CopyAsValueTimeStampMilli");
   }

   public void copyAsField(String name, TimeStampMilliWriter writer) {
      this.fail("CopyAsFieldTimeStampMilli");
   }

   public void read(TimeStampMicroHolder holder) {
      this.fail("TimeStampMicro");
   }

   public void read(NullableTimeStampMicroHolder holder) {
      this.fail("TimeStampMicro");
   }

   public void read(int arrayIndex, TimeStampMicroHolder holder) {
      this.fail("RepeatedTimeStampMicro");
   }

   public void read(int arrayIndex, NullableTimeStampMicroHolder holder) {
      this.fail("RepeatedTimeStampMicro");
   }

   public void copyAsValue(TimeStampMicroWriter writer) {
      this.fail("CopyAsValueTimeStampMicro");
   }

   public void copyAsField(String name, TimeStampMicroWriter writer) {
      this.fail("CopyAsFieldTimeStampMicro");
   }

   public void read(TimeStampNanoHolder holder) {
      this.fail("TimeStampNano");
   }

   public void read(NullableTimeStampNanoHolder holder) {
      this.fail("TimeStampNano");
   }

   public void read(int arrayIndex, TimeStampNanoHolder holder) {
      this.fail("RepeatedTimeStampNano");
   }

   public void read(int arrayIndex, NullableTimeStampNanoHolder holder) {
      this.fail("RepeatedTimeStampNano");
   }

   public void copyAsValue(TimeStampNanoWriter writer) {
      this.fail("CopyAsValueTimeStampNano");
   }

   public void copyAsField(String name, TimeStampNanoWriter writer) {
      this.fail("CopyAsFieldTimeStampNano");
   }

   public void read(TimeStampSecTZHolder holder) {
      this.fail("TimeStampSecTZ");
   }

   public void read(NullableTimeStampSecTZHolder holder) {
      this.fail("TimeStampSecTZ");
   }

   public void read(int arrayIndex, TimeStampSecTZHolder holder) {
      this.fail("RepeatedTimeStampSecTZ");
   }

   public void read(int arrayIndex, NullableTimeStampSecTZHolder holder) {
      this.fail("RepeatedTimeStampSecTZ");
   }

   public void copyAsValue(TimeStampSecTZWriter writer) {
      this.fail("CopyAsValueTimeStampSecTZ");
   }

   public void copyAsField(String name, TimeStampSecTZWriter writer) {
      this.fail("CopyAsFieldTimeStampSecTZ");
   }

   public void read(TimeStampMilliTZHolder holder) {
      this.fail("TimeStampMilliTZ");
   }

   public void read(NullableTimeStampMilliTZHolder holder) {
      this.fail("TimeStampMilliTZ");
   }

   public void read(int arrayIndex, TimeStampMilliTZHolder holder) {
      this.fail("RepeatedTimeStampMilliTZ");
   }

   public void read(int arrayIndex, NullableTimeStampMilliTZHolder holder) {
      this.fail("RepeatedTimeStampMilliTZ");
   }

   public void copyAsValue(TimeStampMilliTZWriter writer) {
      this.fail("CopyAsValueTimeStampMilliTZ");
   }

   public void copyAsField(String name, TimeStampMilliTZWriter writer) {
      this.fail("CopyAsFieldTimeStampMilliTZ");
   }

   public void read(TimeStampMicroTZHolder holder) {
      this.fail("TimeStampMicroTZ");
   }

   public void read(NullableTimeStampMicroTZHolder holder) {
      this.fail("TimeStampMicroTZ");
   }

   public void read(int arrayIndex, TimeStampMicroTZHolder holder) {
      this.fail("RepeatedTimeStampMicroTZ");
   }

   public void read(int arrayIndex, NullableTimeStampMicroTZHolder holder) {
      this.fail("RepeatedTimeStampMicroTZ");
   }

   public void copyAsValue(TimeStampMicroTZWriter writer) {
      this.fail("CopyAsValueTimeStampMicroTZ");
   }

   public void copyAsField(String name, TimeStampMicroTZWriter writer) {
      this.fail("CopyAsFieldTimeStampMicroTZ");
   }

   public void read(TimeStampNanoTZHolder holder) {
      this.fail("TimeStampNanoTZ");
   }

   public void read(NullableTimeStampNanoTZHolder holder) {
      this.fail("TimeStampNanoTZ");
   }

   public void read(int arrayIndex, TimeStampNanoTZHolder holder) {
      this.fail("RepeatedTimeStampNanoTZ");
   }

   public void read(int arrayIndex, NullableTimeStampNanoTZHolder holder) {
      this.fail("RepeatedTimeStampNanoTZ");
   }

   public void copyAsValue(TimeStampNanoTZWriter writer) {
      this.fail("CopyAsValueTimeStampNanoTZ");
   }

   public void copyAsField(String name, TimeStampNanoTZWriter writer) {
      this.fail("CopyAsFieldTimeStampNanoTZ");
   }

   public void read(TimeMicroHolder holder) {
      this.fail("TimeMicro");
   }

   public void read(NullableTimeMicroHolder holder) {
      this.fail("TimeMicro");
   }

   public void read(int arrayIndex, TimeMicroHolder holder) {
      this.fail("RepeatedTimeMicro");
   }

   public void read(int arrayIndex, NullableTimeMicroHolder holder) {
      this.fail("RepeatedTimeMicro");
   }

   public void copyAsValue(TimeMicroWriter writer) {
      this.fail("CopyAsValueTimeMicro");
   }

   public void copyAsField(String name, TimeMicroWriter writer) {
      this.fail("CopyAsFieldTimeMicro");
   }

   public void read(TimeNanoHolder holder) {
      this.fail("TimeNano");
   }

   public void read(NullableTimeNanoHolder holder) {
      this.fail("TimeNano");
   }

   public void read(int arrayIndex, TimeNanoHolder holder) {
      this.fail("RepeatedTimeNano");
   }

   public void read(int arrayIndex, NullableTimeNanoHolder holder) {
      this.fail("RepeatedTimeNano");
   }

   public void copyAsValue(TimeNanoWriter writer) {
      this.fail("CopyAsValueTimeNano");
   }

   public void copyAsField(String name, TimeNanoWriter writer) {
      this.fail("CopyAsFieldTimeNano");
   }

   public void read(IntervalDayHolder holder) {
      this.fail("IntervalDay");
   }

   public void read(NullableIntervalDayHolder holder) {
      this.fail("IntervalDay");
   }

   public void read(int arrayIndex, IntervalDayHolder holder) {
      this.fail("RepeatedIntervalDay");
   }

   public void read(int arrayIndex, NullableIntervalDayHolder holder) {
      this.fail("RepeatedIntervalDay");
   }

   public void copyAsValue(IntervalDayWriter writer) {
      this.fail("CopyAsValueIntervalDay");
   }

   public void copyAsField(String name, IntervalDayWriter writer) {
      this.fail("CopyAsFieldIntervalDay");
   }

   public void read(IntervalMonthDayNanoHolder holder) {
      this.fail("IntervalMonthDayNano");
   }

   public void read(NullableIntervalMonthDayNanoHolder holder) {
      this.fail("IntervalMonthDayNano");
   }

   public void read(int arrayIndex, IntervalMonthDayNanoHolder holder) {
      this.fail("RepeatedIntervalMonthDayNano");
   }

   public void read(int arrayIndex, NullableIntervalMonthDayNanoHolder holder) {
      this.fail("RepeatedIntervalMonthDayNano");
   }

   public void copyAsValue(IntervalMonthDayNanoWriter writer) {
      this.fail("CopyAsValueIntervalMonthDayNano");
   }

   public void copyAsField(String name, IntervalMonthDayNanoWriter writer) {
      this.fail("CopyAsFieldIntervalMonthDayNano");
   }

   public void read(Decimal256Holder holder) {
      this.fail("Decimal256");
   }

   public void read(NullableDecimal256Holder holder) {
      this.fail("Decimal256");
   }

   public void read(int arrayIndex, Decimal256Holder holder) {
      this.fail("RepeatedDecimal256");
   }

   public void read(int arrayIndex, NullableDecimal256Holder holder) {
      this.fail("RepeatedDecimal256");
   }

   public void copyAsValue(Decimal256Writer writer) {
      this.fail("CopyAsValueDecimal256");
   }

   public void copyAsField(String name, Decimal256Writer writer) {
      this.fail("CopyAsFieldDecimal256");
   }

   public void read(DecimalHolder holder) {
      this.fail("Decimal");
   }

   public void read(NullableDecimalHolder holder) {
      this.fail("Decimal");
   }

   public void read(int arrayIndex, DecimalHolder holder) {
      this.fail("RepeatedDecimal");
   }

   public void read(int arrayIndex, NullableDecimalHolder holder) {
      this.fail("RepeatedDecimal");
   }

   public void copyAsValue(DecimalWriter writer) {
      this.fail("CopyAsValueDecimal");
   }

   public void copyAsField(String name, DecimalWriter writer) {
      this.fail("CopyAsFieldDecimal");
   }

   public void read(FixedSizeBinaryHolder holder) {
      this.fail("FixedSizeBinary");
   }

   public void read(NullableFixedSizeBinaryHolder holder) {
      this.fail("FixedSizeBinary");
   }

   public void read(int arrayIndex, FixedSizeBinaryHolder holder) {
      this.fail("RepeatedFixedSizeBinary");
   }

   public void read(int arrayIndex, NullableFixedSizeBinaryHolder holder) {
      this.fail("RepeatedFixedSizeBinary");
   }

   public void copyAsValue(FixedSizeBinaryWriter writer) {
      this.fail("CopyAsValueFixedSizeBinary");
   }

   public void copyAsField(String name, FixedSizeBinaryWriter writer) {
      this.fail("CopyAsFieldFixedSizeBinary");
   }

   public void read(VarBinaryHolder holder) {
      this.fail("VarBinary");
   }

   public void read(NullableVarBinaryHolder holder) {
      this.fail("VarBinary");
   }

   public void read(int arrayIndex, VarBinaryHolder holder) {
      this.fail("RepeatedVarBinary");
   }

   public void read(int arrayIndex, NullableVarBinaryHolder holder) {
      this.fail("RepeatedVarBinary");
   }

   public void copyAsValue(VarBinaryWriter writer) {
      this.fail("CopyAsValueVarBinary");
   }

   public void copyAsField(String name, VarBinaryWriter writer) {
      this.fail("CopyAsFieldVarBinary");
   }

   public void read(VarCharHolder holder) {
      this.fail("VarChar");
   }

   public void read(NullableVarCharHolder holder) {
      this.fail("VarChar");
   }

   public void read(int arrayIndex, VarCharHolder holder) {
      this.fail("RepeatedVarChar");
   }

   public void read(int arrayIndex, NullableVarCharHolder holder) {
      this.fail("RepeatedVarChar");
   }

   public void copyAsValue(VarCharWriter writer) {
      this.fail("CopyAsValueVarChar");
   }

   public void copyAsField(String name, VarCharWriter writer) {
      this.fail("CopyAsFieldVarChar");
   }

   public void read(ViewVarBinaryHolder holder) {
      this.fail("ViewVarBinary");
   }

   public void read(NullableViewVarBinaryHolder holder) {
      this.fail("ViewVarBinary");
   }

   public void read(int arrayIndex, ViewVarBinaryHolder holder) {
      this.fail("RepeatedViewVarBinary");
   }

   public void read(int arrayIndex, NullableViewVarBinaryHolder holder) {
      this.fail("RepeatedViewVarBinary");
   }

   public void copyAsValue(ViewVarBinaryWriter writer) {
      this.fail("CopyAsValueViewVarBinary");
   }

   public void copyAsField(String name, ViewVarBinaryWriter writer) {
      this.fail("CopyAsFieldViewVarBinary");
   }

   public void read(ViewVarCharHolder holder) {
      this.fail("ViewVarChar");
   }

   public void read(NullableViewVarCharHolder holder) {
      this.fail("ViewVarChar");
   }

   public void read(int arrayIndex, ViewVarCharHolder holder) {
      this.fail("RepeatedViewVarChar");
   }

   public void read(int arrayIndex, NullableViewVarCharHolder holder) {
      this.fail("RepeatedViewVarChar");
   }

   public void copyAsValue(ViewVarCharWriter writer) {
      this.fail("CopyAsValueViewVarChar");
   }

   public void copyAsField(String name, ViewVarCharWriter writer) {
      this.fail("CopyAsFieldViewVarChar");
   }

   public void read(LargeVarCharHolder holder) {
      this.fail("LargeVarChar");
   }

   public void read(NullableLargeVarCharHolder holder) {
      this.fail("LargeVarChar");
   }

   public void read(int arrayIndex, LargeVarCharHolder holder) {
      this.fail("RepeatedLargeVarChar");
   }

   public void read(int arrayIndex, NullableLargeVarCharHolder holder) {
      this.fail("RepeatedLargeVarChar");
   }

   public void copyAsValue(LargeVarCharWriter writer) {
      this.fail("CopyAsValueLargeVarChar");
   }

   public void copyAsField(String name, LargeVarCharWriter writer) {
      this.fail("CopyAsFieldLargeVarChar");
   }

   public void read(LargeVarBinaryHolder holder) {
      this.fail("LargeVarBinary");
   }

   public void read(NullableLargeVarBinaryHolder holder) {
      this.fail("LargeVarBinary");
   }

   public void read(int arrayIndex, LargeVarBinaryHolder holder) {
      this.fail("RepeatedLargeVarBinary");
   }

   public void read(int arrayIndex, NullableLargeVarBinaryHolder holder) {
      this.fail("RepeatedLargeVarBinary");
   }

   public void copyAsValue(LargeVarBinaryWriter writer) {
      this.fail("CopyAsValueLargeVarBinary");
   }

   public void copyAsField(String name, LargeVarBinaryWriter writer) {
      this.fail("CopyAsFieldLargeVarBinary");
   }

   public void read(BitHolder holder) {
      this.fail("Bit");
   }

   public void read(NullableBitHolder holder) {
      this.fail("Bit");
   }

   public void read(int arrayIndex, BitHolder holder) {
      this.fail("RepeatedBit");
   }

   public void read(int arrayIndex, NullableBitHolder holder) {
      this.fail("RepeatedBit");
   }

   public void copyAsValue(BitWriter writer) {
      this.fail("CopyAsValueBit");
   }

   public void copyAsField(String name, BitWriter writer) {
      this.fail("CopyAsFieldBit");
   }

   public FieldReader reader(String name) {
      this.fail("reader(String name)");
      return null;
   }

   public FieldReader reader() {
      this.fail("reader()");
      return null;
   }

   public int size() {
      this.fail("size()");
      return -1;
   }

   private void fail(String name) {
      throw new IllegalArgumentException(String.format("You tried to read a [%s] type when you are using a field reader of type [%s].", name, this.getClass().getSimpleName()));
   }
}
