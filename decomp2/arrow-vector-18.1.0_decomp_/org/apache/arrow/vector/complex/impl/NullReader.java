package org.apache.arrow.vector.complex.impl;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;
import org.apache.arrow.vector.PeriodDuration;
import org.apache.arrow.vector.complex.reader.BaseReader;
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
import org.apache.arrow.vector.types.Types;
import org.apache.arrow.vector.types.pojo.ArrowType;
import org.apache.arrow.vector.types.pojo.Field;
import org.apache.arrow.vector.types.pojo.FieldType;
import org.apache.arrow.vector.util.Text;

public class NullReader extends AbstractBaseReader implements FieldReader {
   public static final NullReader INSTANCE = new NullReader();
   public static final NullReader EMPTY_LIST_INSTANCE;
   public static final NullReader EMPTY_STRUCT_INSTANCE;
   private Types.MinorType type;

   private NullReader() {
      this.type = Types.MinorType.NULL;
   }

   private NullReader(Types.MinorType type) {
      this.type = type;
   }

   public Types.MinorType getMinorType() {
      return this.type;
   }

   public Field getField() {
      return new Field("", FieldType.nullable(new ArrowType.Null()), (List)null);
   }

   public void copyAsValue(BaseWriter.StructWriter writer) {
   }

   public void copyAsValue(BaseWriter.ListWriter writer) {
   }

   public void copyAsValue(UnionWriter writer) {
   }

   public void read(TinyIntHolder holder) {
      throw new UnsupportedOperationException("NullReader cannot write into non-nullable holder");
   }

   public void read(NullableTinyIntHolder holder) {
      holder.isSet = 0;
   }

   public void read(int arrayIndex, TinyIntHolder holder) {
      throw new ArrayIndexOutOfBoundsException();
   }

   public void copyAsValue(TinyIntWriter writer) {
   }

   public void copyAsField(String name, TinyIntWriter writer) {
   }

   public void read(int arrayIndex, NullableTinyIntHolder holder) {
      throw new ArrayIndexOutOfBoundsException();
   }

   public void read(UInt1Holder holder) {
      throw new UnsupportedOperationException("NullReader cannot write into non-nullable holder");
   }

   public void read(NullableUInt1Holder holder) {
      holder.isSet = 0;
   }

   public void read(int arrayIndex, UInt1Holder holder) {
      throw new ArrayIndexOutOfBoundsException();
   }

   public void copyAsValue(UInt1Writer writer) {
   }

   public void copyAsField(String name, UInt1Writer writer) {
   }

   public void read(int arrayIndex, NullableUInt1Holder holder) {
      throw new ArrayIndexOutOfBoundsException();
   }

   public void read(UInt2Holder holder) {
      throw new UnsupportedOperationException("NullReader cannot write into non-nullable holder");
   }

   public void read(NullableUInt2Holder holder) {
      holder.isSet = 0;
   }

   public void read(int arrayIndex, UInt2Holder holder) {
      throw new ArrayIndexOutOfBoundsException();
   }

   public void copyAsValue(UInt2Writer writer) {
   }

   public void copyAsField(String name, UInt2Writer writer) {
   }

   public void read(int arrayIndex, NullableUInt2Holder holder) {
      throw new ArrayIndexOutOfBoundsException();
   }

   public void read(SmallIntHolder holder) {
      throw new UnsupportedOperationException("NullReader cannot write into non-nullable holder");
   }

   public void read(NullableSmallIntHolder holder) {
      holder.isSet = 0;
   }

   public void read(int arrayIndex, SmallIntHolder holder) {
      throw new ArrayIndexOutOfBoundsException();
   }

   public void copyAsValue(SmallIntWriter writer) {
   }

   public void copyAsField(String name, SmallIntWriter writer) {
   }

   public void read(int arrayIndex, NullableSmallIntHolder holder) {
      throw new ArrayIndexOutOfBoundsException();
   }

   public void read(Float2Holder holder) {
      throw new UnsupportedOperationException("NullReader cannot write into non-nullable holder");
   }

   public void read(NullableFloat2Holder holder) {
      holder.isSet = 0;
   }

   public void read(int arrayIndex, Float2Holder holder) {
      throw new ArrayIndexOutOfBoundsException();
   }

   public void copyAsValue(Float2Writer writer) {
   }

   public void copyAsField(String name, Float2Writer writer) {
   }

   public void read(int arrayIndex, NullableFloat2Holder holder) {
      throw new ArrayIndexOutOfBoundsException();
   }

   public void read(IntHolder holder) {
      throw new UnsupportedOperationException("NullReader cannot write into non-nullable holder");
   }

   public void read(NullableIntHolder holder) {
      holder.isSet = 0;
   }

   public void read(int arrayIndex, IntHolder holder) {
      throw new ArrayIndexOutOfBoundsException();
   }

   public void copyAsValue(IntWriter writer) {
   }

   public void copyAsField(String name, IntWriter writer) {
   }

   public void read(int arrayIndex, NullableIntHolder holder) {
      throw new ArrayIndexOutOfBoundsException();
   }

   public void read(UInt4Holder holder) {
      throw new UnsupportedOperationException("NullReader cannot write into non-nullable holder");
   }

   public void read(NullableUInt4Holder holder) {
      holder.isSet = 0;
   }

   public void read(int arrayIndex, UInt4Holder holder) {
      throw new ArrayIndexOutOfBoundsException();
   }

   public void copyAsValue(UInt4Writer writer) {
   }

   public void copyAsField(String name, UInt4Writer writer) {
   }

   public void read(int arrayIndex, NullableUInt4Holder holder) {
      throw new ArrayIndexOutOfBoundsException();
   }

   public void read(Float4Holder holder) {
      throw new UnsupportedOperationException("NullReader cannot write into non-nullable holder");
   }

   public void read(NullableFloat4Holder holder) {
      holder.isSet = 0;
   }

   public void read(int arrayIndex, Float4Holder holder) {
      throw new ArrayIndexOutOfBoundsException();
   }

   public void copyAsValue(Float4Writer writer) {
   }

   public void copyAsField(String name, Float4Writer writer) {
   }

   public void read(int arrayIndex, NullableFloat4Holder holder) {
      throw new ArrayIndexOutOfBoundsException();
   }

   public void read(DateDayHolder holder) {
      throw new UnsupportedOperationException("NullReader cannot write into non-nullable holder");
   }

   public void read(NullableDateDayHolder holder) {
      holder.isSet = 0;
   }

   public void read(int arrayIndex, DateDayHolder holder) {
      throw new ArrayIndexOutOfBoundsException();
   }

   public void copyAsValue(DateDayWriter writer) {
   }

   public void copyAsField(String name, DateDayWriter writer) {
   }

   public void read(int arrayIndex, NullableDateDayHolder holder) {
      throw new ArrayIndexOutOfBoundsException();
   }

   public void read(IntervalYearHolder holder) {
      throw new UnsupportedOperationException("NullReader cannot write into non-nullable holder");
   }

   public void read(NullableIntervalYearHolder holder) {
      holder.isSet = 0;
   }

   public void read(int arrayIndex, IntervalYearHolder holder) {
      throw new ArrayIndexOutOfBoundsException();
   }

   public void copyAsValue(IntervalYearWriter writer) {
   }

   public void copyAsField(String name, IntervalYearWriter writer) {
   }

   public void read(int arrayIndex, NullableIntervalYearHolder holder) {
      throw new ArrayIndexOutOfBoundsException();
   }

   public void read(TimeSecHolder holder) {
      throw new UnsupportedOperationException("NullReader cannot write into non-nullable holder");
   }

   public void read(NullableTimeSecHolder holder) {
      holder.isSet = 0;
   }

   public void read(int arrayIndex, TimeSecHolder holder) {
      throw new ArrayIndexOutOfBoundsException();
   }

   public void copyAsValue(TimeSecWriter writer) {
   }

   public void copyAsField(String name, TimeSecWriter writer) {
   }

   public void read(int arrayIndex, NullableTimeSecHolder holder) {
      throw new ArrayIndexOutOfBoundsException();
   }

   public void read(TimeMilliHolder holder) {
      throw new UnsupportedOperationException("NullReader cannot write into non-nullable holder");
   }

   public void read(NullableTimeMilliHolder holder) {
      holder.isSet = 0;
   }

   public void read(int arrayIndex, TimeMilliHolder holder) {
      throw new ArrayIndexOutOfBoundsException();
   }

   public void copyAsValue(TimeMilliWriter writer) {
   }

   public void copyAsField(String name, TimeMilliWriter writer) {
   }

   public void read(int arrayIndex, NullableTimeMilliHolder holder) {
      throw new ArrayIndexOutOfBoundsException();
   }

   public void read(BigIntHolder holder) {
      throw new UnsupportedOperationException("NullReader cannot write into non-nullable holder");
   }

   public void read(NullableBigIntHolder holder) {
      holder.isSet = 0;
   }

   public void read(int arrayIndex, BigIntHolder holder) {
      throw new ArrayIndexOutOfBoundsException();
   }

   public void copyAsValue(BigIntWriter writer) {
   }

   public void copyAsField(String name, BigIntWriter writer) {
   }

   public void read(int arrayIndex, NullableBigIntHolder holder) {
      throw new ArrayIndexOutOfBoundsException();
   }

   public void read(UInt8Holder holder) {
      throw new UnsupportedOperationException("NullReader cannot write into non-nullable holder");
   }

   public void read(NullableUInt8Holder holder) {
      holder.isSet = 0;
   }

   public void read(int arrayIndex, UInt8Holder holder) {
      throw new ArrayIndexOutOfBoundsException();
   }

   public void copyAsValue(UInt8Writer writer) {
   }

   public void copyAsField(String name, UInt8Writer writer) {
   }

   public void read(int arrayIndex, NullableUInt8Holder holder) {
      throw new ArrayIndexOutOfBoundsException();
   }

   public void read(Float8Holder holder) {
      throw new UnsupportedOperationException("NullReader cannot write into non-nullable holder");
   }

   public void read(NullableFloat8Holder holder) {
      holder.isSet = 0;
   }

   public void read(int arrayIndex, Float8Holder holder) {
      throw new ArrayIndexOutOfBoundsException();
   }

   public void copyAsValue(Float8Writer writer) {
   }

   public void copyAsField(String name, Float8Writer writer) {
   }

   public void read(int arrayIndex, NullableFloat8Holder holder) {
      throw new ArrayIndexOutOfBoundsException();
   }

   public void read(DateMilliHolder holder) {
      throw new UnsupportedOperationException("NullReader cannot write into non-nullable holder");
   }

   public void read(NullableDateMilliHolder holder) {
      holder.isSet = 0;
   }

   public void read(int arrayIndex, DateMilliHolder holder) {
      throw new ArrayIndexOutOfBoundsException();
   }

   public void copyAsValue(DateMilliWriter writer) {
   }

   public void copyAsField(String name, DateMilliWriter writer) {
   }

   public void read(int arrayIndex, NullableDateMilliHolder holder) {
      throw new ArrayIndexOutOfBoundsException();
   }

   public void read(DurationHolder holder) {
      throw new UnsupportedOperationException("NullReader cannot write into non-nullable holder");
   }

   public void read(NullableDurationHolder holder) {
      holder.isSet = 0;
   }

   public void read(int arrayIndex, DurationHolder holder) {
      throw new ArrayIndexOutOfBoundsException();
   }

   public void copyAsValue(DurationWriter writer) {
   }

   public void copyAsField(String name, DurationWriter writer) {
   }

   public void read(int arrayIndex, NullableDurationHolder holder) {
      throw new ArrayIndexOutOfBoundsException();
   }

   public void read(TimeStampSecHolder holder) {
      throw new UnsupportedOperationException("NullReader cannot write into non-nullable holder");
   }

   public void read(NullableTimeStampSecHolder holder) {
      holder.isSet = 0;
   }

   public void read(int arrayIndex, TimeStampSecHolder holder) {
      throw new ArrayIndexOutOfBoundsException();
   }

   public void copyAsValue(TimeStampSecWriter writer) {
   }

   public void copyAsField(String name, TimeStampSecWriter writer) {
   }

   public void read(int arrayIndex, NullableTimeStampSecHolder holder) {
      throw new ArrayIndexOutOfBoundsException();
   }

   public void read(TimeStampMilliHolder holder) {
      throw new UnsupportedOperationException("NullReader cannot write into non-nullable holder");
   }

   public void read(NullableTimeStampMilliHolder holder) {
      holder.isSet = 0;
   }

   public void read(int arrayIndex, TimeStampMilliHolder holder) {
      throw new ArrayIndexOutOfBoundsException();
   }

   public void copyAsValue(TimeStampMilliWriter writer) {
   }

   public void copyAsField(String name, TimeStampMilliWriter writer) {
   }

   public void read(int arrayIndex, NullableTimeStampMilliHolder holder) {
      throw new ArrayIndexOutOfBoundsException();
   }

   public void read(TimeStampMicroHolder holder) {
      throw new UnsupportedOperationException("NullReader cannot write into non-nullable holder");
   }

   public void read(NullableTimeStampMicroHolder holder) {
      holder.isSet = 0;
   }

   public void read(int arrayIndex, TimeStampMicroHolder holder) {
      throw new ArrayIndexOutOfBoundsException();
   }

   public void copyAsValue(TimeStampMicroWriter writer) {
   }

   public void copyAsField(String name, TimeStampMicroWriter writer) {
   }

   public void read(int arrayIndex, NullableTimeStampMicroHolder holder) {
      throw new ArrayIndexOutOfBoundsException();
   }

   public void read(TimeStampNanoHolder holder) {
      throw new UnsupportedOperationException("NullReader cannot write into non-nullable holder");
   }

   public void read(NullableTimeStampNanoHolder holder) {
      holder.isSet = 0;
   }

   public void read(int arrayIndex, TimeStampNanoHolder holder) {
      throw new ArrayIndexOutOfBoundsException();
   }

   public void copyAsValue(TimeStampNanoWriter writer) {
   }

   public void copyAsField(String name, TimeStampNanoWriter writer) {
   }

   public void read(int arrayIndex, NullableTimeStampNanoHolder holder) {
      throw new ArrayIndexOutOfBoundsException();
   }

   public void read(TimeStampSecTZHolder holder) {
      throw new UnsupportedOperationException("NullReader cannot write into non-nullable holder");
   }

   public void read(NullableTimeStampSecTZHolder holder) {
      holder.isSet = 0;
   }

   public void read(int arrayIndex, TimeStampSecTZHolder holder) {
      throw new ArrayIndexOutOfBoundsException();
   }

   public void copyAsValue(TimeStampSecTZWriter writer) {
   }

   public void copyAsField(String name, TimeStampSecTZWriter writer) {
   }

   public void read(int arrayIndex, NullableTimeStampSecTZHolder holder) {
      throw new ArrayIndexOutOfBoundsException();
   }

   public void read(TimeStampMilliTZHolder holder) {
      throw new UnsupportedOperationException("NullReader cannot write into non-nullable holder");
   }

   public void read(NullableTimeStampMilliTZHolder holder) {
      holder.isSet = 0;
   }

   public void read(int arrayIndex, TimeStampMilliTZHolder holder) {
      throw new ArrayIndexOutOfBoundsException();
   }

   public void copyAsValue(TimeStampMilliTZWriter writer) {
   }

   public void copyAsField(String name, TimeStampMilliTZWriter writer) {
   }

   public void read(int arrayIndex, NullableTimeStampMilliTZHolder holder) {
      throw new ArrayIndexOutOfBoundsException();
   }

   public void read(TimeStampMicroTZHolder holder) {
      throw new UnsupportedOperationException("NullReader cannot write into non-nullable holder");
   }

   public void read(NullableTimeStampMicroTZHolder holder) {
      holder.isSet = 0;
   }

   public void read(int arrayIndex, TimeStampMicroTZHolder holder) {
      throw new ArrayIndexOutOfBoundsException();
   }

   public void copyAsValue(TimeStampMicroTZWriter writer) {
   }

   public void copyAsField(String name, TimeStampMicroTZWriter writer) {
   }

   public void read(int arrayIndex, NullableTimeStampMicroTZHolder holder) {
      throw new ArrayIndexOutOfBoundsException();
   }

   public void read(TimeStampNanoTZHolder holder) {
      throw new UnsupportedOperationException("NullReader cannot write into non-nullable holder");
   }

   public void read(NullableTimeStampNanoTZHolder holder) {
      holder.isSet = 0;
   }

   public void read(int arrayIndex, TimeStampNanoTZHolder holder) {
      throw new ArrayIndexOutOfBoundsException();
   }

   public void copyAsValue(TimeStampNanoTZWriter writer) {
   }

   public void copyAsField(String name, TimeStampNanoTZWriter writer) {
   }

   public void read(int arrayIndex, NullableTimeStampNanoTZHolder holder) {
      throw new ArrayIndexOutOfBoundsException();
   }

   public void read(TimeMicroHolder holder) {
      throw new UnsupportedOperationException("NullReader cannot write into non-nullable holder");
   }

   public void read(NullableTimeMicroHolder holder) {
      holder.isSet = 0;
   }

   public void read(int arrayIndex, TimeMicroHolder holder) {
      throw new ArrayIndexOutOfBoundsException();
   }

   public void copyAsValue(TimeMicroWriter writer) {
   }

   public void copyAsField(String name, TimeMicroWriter writer) {
   }

   public void read(int arrayIndex, NullableTimeMicroHolder holder) {
      throw new ArrayIndexOutOfBoundsException();
   }

   public void read(TimeNanoHolder holder) {
      throw new UnsupportedOperationException("NullReader cannot write into non-nullable holder");
   }

   public void read(NullableTimeNanoHolder holder) {
      holder.isSet = 0;
   }

   public void read(int arrayIndex, TimeNanoHolder holder) {
      throw new ArrayIndexOutOfBoundsException();
   }

   public void copyAsValue(TimeNanoWriter writer) {
   }

   public void copyAsField(String name, TimeNanoWriter writer) {
   }

   public void read(int arrayIndex, NullableTimeNanoHolder holder) {
      throw new ArrayIndexOutOfBoundsException();
   }

   public void read(IntervalDayHolder holder) {
      throw new UnsupportedOperationException("NullReader cannot write into non-nullable holder");
   }

   public void read(NullableIntervalDayHolder holder) {
      holder.isSet = 0;
   }

   public void read(int arrayIndex, IntervalDayHolder holder) {
      throw new ArrayIndexOutOfBoundsException();
   }

   public void copyAsValue(IntervalDayWriter writer) {
   }

   public void copyAsField(String name, IntervalDayWriter writer) {
   }

   public void read(int arrayIndex, NullableIntervalDayHolder holder) {
      throw new ArrayIndexOutOfBoundsException();
   }

   public void read(IntervalMonthDayNanoHolder holder) {
      throw new UnsupportedOperationException("NullReader cannot write into non-nullable holder");
   }

   public void read(NullableIntervalMonthDayNanoHolder holder) {
      holder.isSet = 0;
   }

   public void read(int arrayIndex, IntervalMonthDayNanoHolder holder) {
      throw new ArrayIndexOutOfBoundsException();
   }

   public void copyAsValue(IntervalMonthDayNanoWriter writer) {
   }

   public void copyAsField(String name, IntervalMonthDayNanoWriter writer) {
   }

   public void read(int arrayIndex, NullableIntervalMonthDayNanoHolder holder) {
      throw new ArrayIndexOutOfBoundsException();
   }

   public void read(Decimal256Holder holder) {
      throw new UnsupportedOperationException("NullReader cannot write into non-nullable holder");
   }

   public void read(NullableDecimal256Holder holder) {
      holder.isSet = 0;
   }

   public void read(int arrayIndex, Decimal256Holder holder) {
      throw new ArrayIndexOutOfBoundsException();
   }

   public void copyAsValue(Decimal256Writer writer) {
   }

   public void copyAsField(String name, Decimal256Writer writer) {
   }

   public void read(int arrayIndex, NullableDecimal256Holder holder) {
      throw new ArrayIndexOutOfBoundsException();
   }

   public void read(DecimalHolder holder) {
      throw new UnsupportedOperationException("NullReader cannot write into non-nullable holder");
   }

   public void read(NullableDecimalHolder holder) {
      holder.isSet = 0;
   }

   public void read(int arrayIndex, DecimalHolder holder) {
      throw new ArrayIndexOutOfBoundsException();
   }

   public void copyAsValue(DecimalWriter writer) {
   }

   public void copyAsField(String name, DecimalWriter writer) {
   }

   public void read(int arrayIndex, NullableDecimalHolder holder) {
      throw new ArrayIndexOutOfBoundsException();
   }

   public void read(FixedSizeBinaryHolder holder) {
      throw new UnsupportedOperationException("NullReader cannot write into non-nullable holder");
   }

   public void read(NullableFixedSizeBinaryHolder holder) {
      holder.isSet = 0;
   }

   public void read(int arrayIndex, FixedSizeBinaryHolder holder) {
      throw new ArrayIndexOutOfBoundsException();
   }

   public void copyAsValue(FixedSizeBinaryWriter writer) {
   }

   public void copyAsField(String name, FixedSizeBinaryWriter writer) {
   }

   public void read(int arrayIndex, NullableFixedSizeBinaryHolder holder) {
      throw new ArrayIndexOutOfBoundsException();
   }

   public void read(VarBinaryHolder holder) {
      throw new UnsupportedOperationException("NullReader cannot write into non-nullable holder");
   }

   public void read(NullableVarBinaryHolder holder) {
      holder.isSet = 0;
   }

   public void read(int arrayIndex, VarBinaryHolder holder) {
      throw new ArrayIndexOutOfBoundsException();
   }

   public void copyAsValue(VarBinaryWriter writer) {
   }

   public void copyAsField(String name, VarBinaryWriter writer) {
   }

   public void read(int arrayIndex, NullableVarBinaryHolder holder) {
      throw new ArrayIndexOutOfBoundsException();
   }

   public void read(VarCharHolder holder) {
      throw new UnsupportedOperationException("NullReader cannot write into non-nullable holder");
   }

   public void read(NullableVarCharHolder holder) {
      holder.isSet = 0;
   }

   public void read(int arrayIndex, VarCharHolder holder) {
      throw new ArrayIndexOutOfBoundsException();
   }

   public void copyAsValue(VarCharWriter writer) {
   }

   public void copyAsField(String name, VarCharWriter writer) {
   }

   public void read(int arrayIndex, NullableVarCharHolder holder) {
      throw new ArrayIndexOutOfBoundsException();
   }

   public void read(ViewVarBinaryHolder holder) {
      throw new UnsupportedOperationException("NullReader cannot write into non-nullable holder");
   }

   public void read(NullableViewVarBinaryHolder holder) {
      holder.isSet = 0;
   }

   public void read(int arrayIndex, ViewVarBinaryHolder holder) {
      throw new ArrayIndexOutOfBoundsException();
   }

   public void copyAsValue(ViewVarBinaryWriter writer) {
   }

   public void copyAsField(String name, ViewVarBinaryWriter writer) {
   }

   public void read(int arrayIndex, NullableViewVarBinaryHolder holder) {
      throw new ArrayIndexOutOfBoundsException();
   }

   public void read(ViewVarCharHolder holder) {
      throw new UnsupportedOperationException("NullReader cannot write into non-nullable holder");
   }

   public void read(NullableViewVarCharHolder holder) {
      holder.isSet = 0;
   }

   public void read(int arrayIndex, ViewVarCharHolder holder) {
      throw new ArrayIndexOutOfBoundsException();
   }

   public void copyAsValue(ViewVarCharWriter writer) {
   }

   public void copyAsField(String name, ViewVarCharWriter writer) {
   }

   public void read(int arrayIndex, NullableViewVarCharHolder holder) {
      throw new ArrayIndexOutOfBoundsException();
   }

   public void read(LargeVarCharHolder holder) {
      throw new UnsupportedOperationException("NullReader cannot write into non-nullable holder");
   }

   public void read(NullableLargeVarCharHolder holder) {
      holder.isSet = 0;
   }

   public void read(int arrayIndex, LargeVarCharHolder holder) {
      throw new ArrayIndexOutOfBoundsException();
   }

   public void copyAsValue(LargeVarCharWriter writer) {
   }

   public void copyAsField(String name, LargeVarCharWriter writer) {
   }

   public void read(int arrayIndex, NullableLargeVarCharHolder holder) {
      throw new ArrayIndexOutOfBoundsException();
   }

   public void read(LargeVarBinaryHolder holder) {
      throw new UnsupportedOperationException("NullReader cannot write into non-nullable holder");
   }

   public void read(NullableLargeVarBinaryHolder holder) {
      holder.isSet = 0;
   }

   public void read(int arrayIndex, LargeVarBinaryHolder holder) {
      throw new ArrayIndexOutOfBoundsException();
   }

   public void copyAsValue(LargeVarBinaryWriter writer) {
   }

   public void copyAsField(String name, LargeVarBinaryWriter writer) {
   }

   public void read(int arrayIndex, NullableLargeVarBinaryHolder holder) {
      throw new ArrayIndexOutOfBoundsException();
   }

   public void read(BitHolder holder) {
      throw new UnsupportedOperationException("NullReader cannot write into non-nullable holder");
   }

   public void read(NullableBitHolder holder) {
      holder.isSet = 0;
   }

   public void read(int arrayIndex, BitHolder holder) {
      throw new ArrayIndexOutOfBoundsException();
   }

   public void copyAsValue(BitWriter writer) {
   }

   public void copyAsField(String name, BitWriter writer) {
   }

   public void read(int arrayIndex, NullableBitHolder holder) {
      throw new ArrayIndexOutOfBoundsException();
   }

   public int size() {
      return 0;
   }

   public boolean isSet() {
      return false;
   }

   public boolean next() {
      return false;
   }

   public BaseReader.RepeatedStructReader struct() {
      return this;
   }

   public BaseReader.RepeatedListReader list() {
      return this;
   }

   public BaseReader.StructReader struct(String name) {
      return this;
   }

   public BaseReader.ListReader list(String name) {
      return this;
   }

   public FieldReader reader(String name) {
      return this;
   }

   public FieldReader reader() {
      return this;
   }

   private void fail(String name) {
      throw new IllegalArgumentException(String.format("You tried to read a %s type when you are using a ValueReader of type %s.", name, this.getClass().getSimpleName()));
   }

   public Object readObject(int arrayIndex) {
      return null;
   }

   public Object readObject() {
      return null;
   }

   public BigDecimal readBigDecimal(int arrayIndex) {
      return null;
   }

   public BigDecimal readBigDecimal() {
      return null;
   }

   public Short readShort(int arrayIndex) {
      return null;
   }

   public Short readShort() {
      return null;
   }

   public Integer readInteger(int arrayIndex) {
      return null;
   }

   public Integer readInteger() {
      return null;
   }

   public Long readLong(int arrayIndex) {
      return null;
   }

   public Long readLong() {
      return null;
   }

   public Boolean readBoolean(int arrayIndex) {
      return null;
   }

   public Boolean readBoolean() {
      return null;
   }

   public LocalDateTime readLocalDateTime(int arrayIndex) {
      return null;
   }

   public LocalDateTime readLocalDateTime() {
      return null;
   }

   public Duration readDuration(int arrayIndex) {
      return null;
   }

   public Duration readDuration() {
      return null;
   }

   public Period readPeriod(int arrayIndex) {
      return null;
   }

   public Period readPeriod() {
      return null;
   }

   public Double readDouble(int arrayIndex) {
      return null;
   }

   public Double readDouble() {
      return null;
   }

   public Float readFloat(int arrayIndex) {
      return null;
   }

   public Float readFloat() {
      return null;
   }

   public Character readCharacter(int arrayIndex) {
      return null;
   }

   public Character readCharacter() {
      return null;
   }

   public Text readText(int arrayIndex) {
      return null;
   }

   public Text readText() {
      return null;
   }

   public String readString(int arrayIndex) {
      return null;
   }

   public String readString() {
      return null;
   }

   public Byte readByte(int arrayIndex) {
      return null;
   }

   public Byte readByte() {
      return null;
   }

   public byte[] readByteArray(int arrayIndex) {
      return null;
   }

   public byte[] readByteArray() {
      return null;
   }

   public PeriodDuration readPeriodDuration(int arrayIndex) {
      return null;
   }

   public PeriodDuration readPeriodDuration() {
      return null;
   }

   static {
      EMPTY_LIST_INSTANCE = new NullReader(Types.MinorType.NULL);
      EMPTY_STRUCT_INSTANCE = new NullReader(Types.MinorType.STRUCT);
   }
}
