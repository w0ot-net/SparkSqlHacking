package org.apache.arrow.vector.complex.impl;

import java.math.BigDecimal;
import java.nio.ByteBuffer;
import org.apache.arrow.memory.ArrowBuf;
import org.apache.arrow.vector.complex.reader.FieldReader;
import org.apache.arrow.vector.complex.writer.BaseWriter;
import org.apache.arrow.vector.complex.writer.BigIntWriter;
import org.apache.arrow.vector.complex.writer.BitWriter;
import org.apache.arrow.vector.complex.writer.DateDayWriter;
import org.apache.arrow.vector.complex.writer.DateMilliWriter;
import org.apache.arrow.vector.complex.writer.Decimal256Writer;
import org.apache.arrow.vector.complex.writer.DecimalWriter;
import org.apache.arrow.vector.complex.writer.DurationWriter;
import org.apache.arrow.vector.complex.writer.FieldWriter;
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
import org.apache.arrow.vector.types.TimeUnit;
import org.apache.arrow.vector.types.pojo.ArrowType;
import org.apache.arrow.vector.util.Text;

abstract class AbstractFieldWriter extends AbstractBaseWriter implements FieldWriter {
   protected boolean addVectorAsNullable = true;

   public void setAddVectorAsNullable(boolean nullable) {
      this.addVectorAsNullable = nullable;
   }

   public void start() {
      throw new IllegalStateException(String.format("You tried to start when you are using a ValueWriter of type %s.", this.getClass().getSimpleName()));
   }

   public void end() {
      throw new IllegalStateException(String.format("You tried to end when you are using a ValueWriter of type %s.", this.getClass().getSimpleName()));
   }

   public void startList() {
      throw new IllegalStateException(String.format("You tried to start a list when you are using a ValueWriter of type %s.", this.getClass().getSimpleName()));
   }

   public void endList() {
      throw new IllegalStateException(String.format("You tried to end a list when you are using a ValueWriter of type %s.", this.getClass().getSimpleName()));
   }

   public void startListView() {
      throw new IllegalStateException(String.format("You tried to start a list view when you are using a ValueWriter of type %s.", this.getClass().getSimpleName()));
   }

   public void endListView() {
      throw new IllegalStateException(String.format("You tried to end a list view when you are using a ValueWriter of type %s.", this.getClass().getSimpleName()));
   }

   public void startMap() {
      throw new IllegalStateException(String.format("You tried to start a map when you are using a ValueWriter of type %s.", this.getClass().getSimpleName()));
   }

   public void endMap() {
      throw new IllegalStateException(String.format("You tried to end a map when you are using a ValueWriter of type %s.", this.getClass().getSimpleName()));
   }

   public void startEntry() {
      throw new IllegalStateException(String.format("You tried to start a map entry when you are using a ValueWriter of type %s.", this.getClass().getSimpleName()));
   }

   public BaseWriter.MapWriter key() {
      throw new IllegalStateException(String.format("You tried to start a map key when you are using a ValueWriter of type %s.", this.getClass().getSimpleName()));
   }

   public BaseWriter.MapWriter value() {
      throw new IllegalStateException(String.format("You tried to start a map value when you are using a ValueWriter of type %s.", this.getClass().getSimpleName()));
   }

   public void endEntry() {
      throw new IllegalStateException(String.format("You tried to end a map entry when you are using a ValueWriter of type %s.", this.getClass().getSimpleName()));
   }

   public void write(TinyIntHolder holder) {
      this.fail("TinyInt");
   }

   public void writeTinyInt(byte value) {
      this.fail("TinyInt");
   }

   public void write(UInt1Holder holder) {
      this.fail("UInt1");
   }

   public void writeUInt1(byte value) {
      this.fail("UInt1");
   }

   public void write(UInt2Holder holder) {
      this.fail("UInt2");
   }

   public void writeUInt2(char value) {
      this.fail("UInt2");
   }

   public void write(SmallIntHolder holder) {
      this.fail("SmallInt");
   }

   public void writeSmallInt(short value) {
      this.fail("SmallInt");
   }

   public void write(Float2Holder holder) {
      this.fail("Float2");
   }

   public void writeFloat2(short value) {
      this.fail("Float2");
   }

   public void write(IntHolder holder) {
      this.fail("Int");
   }

   public void writeInt(int value) {
      this.fail("Int");
   }

   public void write(UInt4Holder holder) {
      this.fail("UInt4");
   }

   public void writeUInt4(int value) {
      this.fail("UInt4");
   }

   public void write(Float4Holder holder) {
      this.fail("Float4");
   }

   public void writeFloat4(float value) {
      this.fail("Float4");
   }

   public void write(DateDayHolder holder) {
      this.fail("DateDay");
   }

   public void writeDateDay(int value) {
      this.fail("DateDay");
   }

   public void write(IntervalYearHolder holder) {
      this.fail("IntervalYear");
   }

   public void writeIntervalYear(int value) {
      this.fail("IntervalYear");
   }

   public void write(TimeSecHolder holder) {
      this.fail("TimeSec");
   }

   public void writeTimeSec(int value) {
      this.fail("TimeSec");
   }

   public void write(TimeMilliHolder holder) {
      this.fail("TimeMilli");
   }

   public void writeTimeMilli(int value) {
      this.fail("TimeMilli");
   }

   public void write(BigIntHolder holder) {
      this.fail("BigInt");
   }

   public void writeBigInt(long value) {
      this.fail("BigInt");
   }

   public void write(UInt8Holder holder) {
      this.fail("UInt8");
   }

   public void writeUInt8(long value) {
      this.fail("UInt8");
   }

   public void write(Float8Holder holder) {
      this.fail("Float8");
   }

   public void writeFloat8(double value) {
      this.fail("Float8");
   }

   public void write(DateMilliHolder holder) {
      this.fail("DateMilli");
   }

   public void writeDateMilli(long value) {
      this.fail("DateMilli");
   }

   public void write(DurationHolder holder) {
      this.fail("Duration");
   }

   public void writeDuration(long value) {
      this.fail("Duration");
   }

   public void write(TimeStampSecHolder holder) {
      this.fail("TimeStampSec");
   }

   public void writeTimeStampSec(long value) {
      this.fail("TimeStampSec");
   }

   public void write(TimeStampMilliHolder holder) {
      this.fail("TimeStampMilli");
   }

   public void writeTimeStampMilli(long value) {
      this.fail("TimeStampMilli");
   }

   public void write(TimeStampMicroHolder holder) {
      this.fail("TimeStampMicro");
   }

   public void writeTimeStampMicro(long value) {
      this.fail("TimeStampMicro");
   }

   public void write(TimeStampNanoHolder holder) {
      this.fail("TimeStampNano");
   }

   public void writeTimeStampNano(long value) {
      this.fail("TimeStampNano");
   }

   public void write(TimeStampSecTZHolder holder) {
      this.fail("TimeStampSecTZ");
   }

   public void writeTimeStampSecTZ(long value) {
      this.fail("TimeStampSecTZ");
   }

   public void write(TimeStampMilliTZHolder holder) {
      this.fail("TimeStampMilliTZ");
   }

   public void writeTimeStampMilliTZ(long value) {
      this.fail("TimeStampMilliTZ");
   }

   public void write(TimeStampMicroTZHolder holder) {
      this.fail("TimeStampMicroTZ");
   }

   public void writeTimeStampMicroTZ(long value) {
      this.fail("TimeStampMicroTZ");
   }

   public void write(TimeStampNanoTZHolder holder) {
      this.fail("TimeStampNanoTZ");
   }

   public void writeTimeStampNanoTZ(long value) {
      this.fail("TimeStampNanoTZ");
   }

   public void write(TimeMicroHolder holder) {
      this.fail("TimeMicro");
   }

   public void writeTimeMicro(long value) {
      this.fail("TimeMicro");
   }

   public void write(TimeNanoHolder holder) {
      this.fail("TimeNano");
   }

   public void writeTimeNano(long value) {
      this.fail("TimeNano");
   }

   public void write(IntervalDayHolder holder) {
      this.fail("IntervalDay");
   }

   public void writeIntervalDay(int days, int milliseconds) {
      this.fail("IntervalDay");
   }

   public void write(IntervalMonthDayNanoHolder holder) {
      this.fail("IntervalMonthDayNano");
   }

   public void writeIntervalMonthDayNano(int months, int days, long nanoseconds) {
      this.fail("IntervalMonthDayNano");
   }

   public void write(Decimal256Holder holder) {
      this.fail("Decimal256");
   }

   public void writeDecimal256(long start, ArrowBuf buffer) {
      this.fail("Decimal256");
   }

   public void writeDecimal256(BigDecimal value) {
      this.fail("Decimal256");
   }

   public void writeDecimal256(long start, ArrowBuf buffer, ArrowType arrowType) {
      this.fail("Decimal256");
   }

   public void writeBigEndianBytesToDecimal256(byte[] value) {
      this.fail("Decimal256");
   }

   public void writeBigEndianBytesToDecimal256(byte[] value, ArrowType arrowType) {
      this.fail("Decimal256");
   }

   public void write(DecimalHolder holder) {
      this.fail("Decimal");
   }

   public void writeDecimal(long start, ArrowBuf buffer) {
      this.fail("Decimal");
   }

   public void writeDecimal(BigDecimal value) {
      this.fail("Decimal");
   }

   public void writeDecimal(long start, ArrowBuf buffer, ArrowType arrowType) {
      this.fail("Decimal");
   }

   public void writeBigEndianBytesToDecimal(byte[] value) {
      this.fail("Decimal");
   }

   public void writeBigEndianBytesToDecimal(byte[] value, ArrowType arrowType) {
      this.fail("Decimal");
   }

   public void write(FixedSizeBinaryHolder holder) {
      this.fail("FixedSizeBinary");
   }

   public void writeFixedSizeBinary(ArrowBuf buffer) {
      this.fail("FixedSizeBinary");
   }

   public void write(VarBinaryHolder holder) {
      this.fail("VarBinary");
   }

   public void writeVarBinary(int start, int end, ArrowBuf buffer) {
      this.fail("VarBinary");
   }

   public void writeVarBinary(byte[] value) {
      this.fail("VarBinary");
   }

   public void writeVarBinary(byte[] value, int offset, int length) {
      this.fail("VarBinary");
   }

   public void writeVarBinary(ByteBuffer value) {
      this.fail("VarBinary");
   }

   public void writeVarBinary(ByteBuffer value, int offset, int length) {
      this.fail("VarBinary");
   }

   public void write(VarCharHolder holder) {
      this.fail("VarChar");
   }

   public void writeVarChar(int start, int end, ArrowBuf buffer) {
      this.fail("VarChar");
   }

   public void writeVarChar(Text value) {
      this.fail("VarChar");
   }

   public void writeVarChar(String value) {
      this.fail("VarChar");
   }

   public void write(ViewVarBinaryHolder holder) {
      this.fail("ViewVarBinary");
   }

   public void writeViewVarBinary(int start, int end, ArrowBuf buffer) {
      this.fail("ViewVarBinary");
   }

   public void writeViewVarBinary(byte[] value) {
      this.fail("ViewVarBinary");
   }

   public void writeViewVarBinary(byte[] value, int offset, int length) {
      this.fail("ViewVarBinary");
   }

   public void writeViewVarBinary(ByteBuffer value) {
      this.fail("ViewVarBinary");
   }

   public void writeViewVarBinary(ByteBuffer value, int offset, int length) {
      this.fail("ViewVarBinary");
   }

   public void write(ViewVarCharHolder holder) {
      this.fail("ViewVarChar");
   }

   public void writeViewVarChar(int start, int end, ArrowBuf buffer) {
      this.fail("ViewVarChar");
   }

   public void writeViewVarChar(Text value) {
      this.fail("ViewVarChar");
   }

   public void writeViewVarChar(String value) {
      this.fail("ViewVarChar");
   }

   public void write(LargeVarCharHolder holder) {
      this.fail("LargeVarChar");
   }

   public void writeLargeVarChar(long start, long end, ArrowBuf buffer) {
      this.fail("LargeVarChar");
   }

   public void writeLargeVarChar(Text value) {
      this.fail("LargeVarChar");
   }

   public void writeLargeVarChar(String value) {
      this.fail("LargeVarChar");
   }

   public void write(LargeVarBinaryHolder holder) {
      this.fail("LargeVarBinary");
   }

   public void writeLargeVarBinary(long start, long end, ArrowBuf buffer) {
      this.fail("LargeVarBinary");
   }

   public void writeLargeVarBinary(byte[] value) {
      this.fail("LargeVarBinary");
   }

   public void writeLargeVarBinary(byte[] value, int offset, int length) {
      this.fail("LargeVarBinary");
   }

   public void writeLargeVarBinary(ByteBuffer value) {
      this.fail("LargeVarBinary");
   }

   public void writeLargeVarBinary(ByteBuffer value, int offset, int length) {
      this.fail("LargeVarBinary");
   }

   public void write(BitHolder holder) {
      this.fail("Bit");
   }

   public void writeBit(int value) {
      this.fail("Bit");
   }

   public void writeNull() {
      this.fail("Bit");
   }

   public boolean isEmptyStruct() {
      return false;
   }

   public BaseWriter.StructWriter struct() {
      this.fail("Struct");
      return null;
   }

   public BaseWriter.ListWriter list() {
      this.fail("List");
      return null;
   }

   public BaseWriter.ListWriter listView() {
      this.fail("ListView");
      return null;
   }

   public BaseWriter.MapWriter map() {
      this.fail("Map");
      return null;
   }

   public BaseWriter.StructWriter struct(String name) {
      this.fail("Struct");
      return null;
   }

   public BaseWriter.ListWriter list(String name) {
      this.fail("List");
      return null;
   }

   public BaseWriter.ListWriter listView(String name) {
      this.fail("ListView");
      return null;
   }

   public BaseWriter.MapWriter map(String name) {
      this.fail("Map");
      return null;
   }

   public BaseWriter.MapWriter map(boolean keysSorted) {
      this.fail("Map");
      return null;
   }

   public BaseWriter.MapWriter map(String name, boolean keysSorted) {
      this.fail("Map");
      return null;
   }

   public TinyIntWriter tinyInt(String name) {
      this.fail("TinyInt");
      return null;
   }

   public TinyIntWriter tinyInt() {
      this.fail("TinyInt");
      return null;
   }

   public UInt1Writer uInt1(String name) {
      this.fail("UInt1");
      return null;
   }

   public UInt1Writer uInt1() {
      this.fail("UInt1");
      return null;
   }

   public UInt2Writer uInt2(String name) {
      this.fail("UInt2");
      return null;
   }

   public UInt2Writer uInt2() {
      this.fail("UInt2");
      return null;
   }

   public SmallIntWriter smallInt(String name) {
      this.fail("SmallInt");
      return null;
   }

   public SmallIntWriter smallInt() {
      this.fail("SmallInt");
      return null;
   }

   public Float2Writer float2(String name) {
      this.fail("Float2");
      return null;
   }

   public Float2Writer float2() {
      this.fail("Float2");
      return null;
   }

   public IntWriter integer(String name) {
      this.fail("Int");
      return null;
   }

   public IntWriter integer() {
      this.fail("Int");
      return null;
   }

   public UInt4Writer uInt4(String name) {
      this.fail("UInt4");
      return null;
   }

   public UInt4Writer uInt4() {
      this.fail("UInt4");
      return null;
   }

   public Float4Writer float4(String name) {
      this.fail("Float4");
      return null;
   }

   public Float4Writer float4() {
      this.fail("Float4");
      return null;
   }

   public DateDayWriter dateDay(String name) {
      this.fail("DateDay");
      return null;
   }

   public DateDayWriter dateDay() {
      this.fail("DateDay");
      return null;
   }

   public IntervalYearWriter intervalYear(String name) {
      this.fail("IntervalYear");
      return null;
   }

   public IntervalYearWriter intervalYear() {
      this.fail("IntervalYear");
      return null;
   }

   public TimeSecWriter timeSec(String name) {
      this.fail("TimeSec");
      return null;
   }

   public TimeSecWriter timeSec() {
      this.fail("TimeSec");
      return null;
   }

   public TimeMilliWriter timeMilli(String name) {
      this.fail("TimeMilli");
      return null;
   }

   public TimeMilliWriter timeMilli() {
      this.fail("TimeMilli");
      return null;
   }

   public BigIntWriter bigInt(String name) {
      this.fail("BigInt");
      return null;
   }

   public BigIntWriter bigInt() {
      this.fail("BigInt");
      return null;
   }

   public UInt8Writer uInt8(String name) {
      this.fail("UInt8");
      return null;
   }

   public UInt8Writer uInt8() {
      this.fail("UInt8");
      return null;
   }

   public Float8Writer float8(String name) {
      this.fail("Float8");
      return null;
   }

   public Float8Writer float8() {
      this.fail("Float8");
      return null;
   }

   public DateMilliWriter dateMilli(String name) {
      this.fail("DateMilli");
      return null;
   }

   public DateMilliWriter dateMilli() {
      this.fail("DateMilli");
      return null;
   }

   public DurationWriter duration(String name, TimeUnit unit) {
      this.fail("Duration(unit: " + String.valueOf(unit) + ", )");
      return null;
   }

   public DurationWriter duration(String name) {
      this.fail("Duration");
      return null;
   }

   public DurationWriter duration() {
      this.fail("Duration");
      return null;
   }

   public TimeStampSecWriter timeStampSec(String name) {
      this.fail("TimeStampSec");
      return null;
   }

   public TimeStampSecWriter timeStampSec() {
      this.fail("TimeStampSec");
      return null;
   }

   public TimeStampMilliWriter timeStampMilli(String name) {
      this.fail("TimeStampMilli");
      return null;
   }

   public TimeStampMilliWriter timeStampMilli() {
      this.fail("TimeStampMilli");
      return null;
   }

   public TimeStampMicroWriter timeStampMicro(String name) {
      this.fail("TimeStampMicro");
      return null;
   }

   public TimeStampMicroWriter timeStampMicro() {
      this.fail("TimeStampMicro");
      return null;
   }

   public TimeStampNanoWriter timeStampNano(String name) {
      this.fail("TimeStampNano");
      return null;
   }

   public TimeStampNanoWriter timeStampNano() {
      this.fail("TimeStampNano");
      return null;
   }

   public TimeStampSecTZWriter timeStampSecTZ(String name, String timezone) {
      this.fail("TimeStampSecTZ(timezone: " + timezone + ", )");
      return null;
   }

   public TimeStampSecTZWriter timeStampSecTZ(String name) {
      this.fail("TimeStampSecTZ");
      return null;
   }

   public TimeStampSecTZWriter timeStampSecTZ() {
      this.fail("TimeStampSecTZ");
      return null;
   }

   public TimeStampMilliTZWriter timeStampMilliTZ(String name, String timezone) {
      this.fail("TimeStampMilliTZ(timezone: " + timezone + ", )");
      return null;
   }

   public TimeStampMilliTZWriter timeStampMilliTZ(String name) {
      this.fail("TimeStampMilliTZ");
      return null;
   }

   public TimeStampMilliTZWriter timeStampMilliTZ() {
      this.fail("TimeStampMilliTZ");
      return null;
   }

   public TimeStampMicroTZWriter timeStampMicroTZ(String name, String timezone) {
      this.fail("TimeStampMicroTZ(timezone: " + timezone + ", )");
      return null;
   }

   public TimeStampMicroTZWriter timeStampMicroTZ(String name) {
      this.fail("TimeStampMicroTZ");
      return null;
   }

   public TimeStampMicroTZWriter timeStampMicroTZ() {
      this.fail("TimeStampMicroTZ");
      return null;
   }

   public TimeStampNanoTZWriter timeStampNanoTZ(String name, String timezone) {
      this.fail("TimeStampNanoTZ(timezone: " + timezone + ", )");
      return null;
   }

   public TimeStampNanoTZWriter timeStampNanoTZ(String name) {
      this.fail("TimeStampNanoTZ");
      return null;
   }

   public TimeStampNanoTZWriter timeStampNanoTZ() {
      this.fail("TimeStampNanoTZ");
      return null;
   }

   public TimeMicroWriter timeMicro(String name) {
      this.fail("TimeMicro");
      return null;
   }

   public TimeMicroWriter timeMicro() {
      this.fail("TimeMicro");
      return null;
   }

   public TimeNanoWriter timeNano(String name) {
      this.fail("TimeNano");
      return null;
   }

   public TimeNanoWriter timeNano() {
      this.fail("TimeNano");
      return null;
   }

   public IntervalDayWriter intervalDay(String name) {
      this.fail("IntervalDay");
      return null;
   }

   public IntervalDayWriter intervalDay() {
      this.fail("IntervalDay");
      return null;
   }

   public IntervalMonthDayNanoWriter intervalMonthDayNano(String name) {
      this.fail("IntervalMonthDayNano");
      return null;
   }

   public IntervalMonthDayNanoWriter intervalMonthDayNano() {
      this.fail("IntervalMonthDayNano");
      return null;
   }

   public Decimal256Writer decimal256(String name, int scale, int precision) {
      this.fail("Decimal256(scale: " + scale + ", precision: " + precision + ", )");
      return null;
   }

   public Decimal256Writer decimal256(String name) {
      this.fail("Decimal256");
      return null;
   }

   public Decimal256Writer decimal256() {
      this.fail("Decimal256");
      return null;
   }

   public DecimalWriter decimal(String name, int scale, int precision) {
      this.fail("Decimal(scale: " + scale + ", precision: " + precision + ", )");
      return null;
   }

   public DecimalWriter decimal(String name) {
      this.fail("Decimal");
      return null;
   }

   public DecimalWriter decimal() {
      this.fail("Decimal");
      return null;
   }

   public FixedSizeBinaryWriter fixedSizeBinary(String name, int byteWidth) {
      this.fail("FixedSizeBinary(byteWidth: " + byteWidth + ", )");
      return null;
   }

   public FixedSizeBinaryWriter fixedSizeBinary(String name) {
      this.fail("FixedSizeBinary");
      return null;
   }

   public FixedSizeBinaryWriter fixedSizeBinary() {
      this.fail("FixedSizeBinary");
      return null;
   }

   public VarBinaryWriter varBinary(String name) {
      this.fail("VarBinary");
      return null;
   }

   public VarBinaryWriter varBinary() {
      this.fail("VarBinary");
      return null;
   }

   public VarCharWriter varChar(String name) {
      this.fail("VarChar");
      return null;
   }

   public VarCharWriter varChar() {
      this.fail("VarChar");
      return null;
   }

   public ViewVarBinaryWriter viewVarBinary(String name) {
      this.fail("ViewVarBinary");
      return null;
   }

   public ViewVarBinaryWriter viewVarBinary() {
      this.fail("ViewVarBinary");
      return null;
   }

   public ViewVarCharWriter viewVarChar(String name) {
      this.fail("ViewVarChar");
      return null;
   }

   public ViewVarCharWriter viewVarChar() {
      this.fail("ViewVarChar");
      return null;
   }

   public LargeVarCharWriter largeVarChar(String name) {
      this.fail("LargeVarChar");
      return null;
   }

   public LargeVarCharWriter largeVarChar() {
      this.fail("LargeVarChar");
      return null;
   }

   public LargeVarBinaryWriter largeVarBinary(String name) {
      this.fail("LargeVarBinary");
      return null;
   }

   public LargeVarBinaryWriter largeVarBinary() {
      this.fail("LargeVarBinary");
      return null;
   }

   public BitWriter bit(String name) {
      this.fail("Bit");
      return null;
   }

   public BitWriter bit() {
      this.fail("Bit");
      return null;
   }

   public void copyReader(FieldReader reader) {
      this.fail("Copy FieldReader");
   }

   public void copyReaderToField(String name, FieldReader reader) {
      this.fail("Copy FieldReader to STring");
   }

   private void fail(String name) {
      throw new IllegalArgumentException(String.format("You tried to write a %s type when you are using a ValueWriter of type %s.", name, this.getClass().getSimpleName()));
   }
}
