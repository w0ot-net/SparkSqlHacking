package org.apache.arrow.vector.complex.impl;

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
import org.apache.arrow.vector.types.Types;
import org.apache.arrow.vector.types.pojo.ArrowType;
import org.apache.arrow.vector.util.Text;

abstract class AbstractPromotableFieldWriter extends AbstractFieldWriter {
   protected FieldWriter getWriter(Types.MinorType type) {
      return this.getWriter(type, (ArrowType)null);
   }

   protected abstract FieldWriter getWriter(Types.MinorType var1, ArrowType var2);

   protected abstract FieldWriter getWriter();

   public void start() {
      this.getWriter(Types.MinorType.STRUCT).start();
   }

   public void end() {
      this.getWriter(Types.MinorType.STRUCT).end();
      this.setPosition(this.idx() + 1);
   }

   public void startList() {
      this.getWriter(Types.MinorType.LIST).startList();
   }

   public void endList() {
      this.getWriter(Types.MinorType.LIST).endList();
      this.setPosition(this.idx() + 1);
   }

   public void startListView() {
      this.getWriter(Types.MinorType.LISTVIEW).startListView();
   }

   public void endListView() {
      this.getWriter(Types.MinorType.LISTVIEW).endListView();
      this.setPosition(this.idx() + 1);
   }

   public void startMap() {
      this.getWriter(Types.MinorType.MAP).startMap();
   }

   public void endMap() {
      this.getWriter(Types.MinorType.MAP).endMap();
      this.setPosition(this.idx() + 1);
   }

   public void startEntry() {
      this.getWriter(Types.MinorType.MAP).startEntry();
   }

   public BaseWriter.MapWriter key() {
      return this.getWriter(Types.MinorType.MAP).key();
   }

   public BaseWriter.MapWriter value() {
      return this.getWriter(Types.MinorType.MAP).value();
   }

   public void endEntry() {
      this.getWriter(Types.MinorType.MAP).endEntry();
   }

   public void write(TinyIntHolder holder) {
      this.getWriter(Types.MinorType.TINYINT).write(holder);
   }

   public void writeTinyInt(byte value) {
      this.getWriter(Types.MinorType.TINYINT).writeTinyInt(value);
   }

   public void write(UInt1Holder holder) {
      this.getWriter(Types.MinorType.UINT1).write(holder);
   }

   public void writeUInt1(byte value) {
      this.getWriter(Types.MinorType.UINT1).writeUInt1(value);
   }

   public void write(UInt2Holder holder) {
      this.getWriter(Types.MinorType.UINT2).write(holder);
   }

   public void writeUInt2(char value) {
      this.getWriter(Types.MinorType.UINT2).writeUInt2(value);
   }

   public void write(SmallIntHolder holder) {
      this.getWriter(Types.MinorType.SMALLINT).write(holder);
   }

   public void writeSmallInt(short value) {
      this.getWriter(Types.MinorType.SMALLINT).writeSmallInt(value);
   }

   public void write(Float2Holder holder) {
      this.getWriter(Types.MinorType.FLOAT2).write(holder);
   }

   public void writeFloat2(short value) {
      this.getWriter(Types.MinorType.FLOAT2).writeFloat2(value);
   }

   public void write(IntHolder holder) {
      this.getWriter(Types.MinorType.INT).write(holder);
   }

   public void writeInt(int value) {
      this.getWriter(Types.MinorType.INT).writeInt(value);
   }

   public void write(UInt4Holder holder) {
      this.getWriter(Types.MinorType.UINT4).write(holder);
   }

   public void writeUInt4(int value) {
      this.getWriter(Types.MinorType.UINT4).writeUInt4(value);
   }

   public void write(Float4Holder holder) {
      this.getWriter(Types.MinorType.FLOAT4).write(holder);
   }

   public void writeFloat4(float value) {
      this.getWriter(Types.MinorType.FLOAT4).writeFloat4(value);
   }

   public void write(DateDayHolder holder) {
      this.getWriter(Types.MinorType.DATEDAY).write(holder);
   }

   public void writeDateDay(int value) {
      this.getWriter(Types.MinorType.DATEDAY).writeDateDay(value);
   }

   public void write(IntervalYearHolder holder) {
      this.getWriter(Types.MinorType.INTERVALYEAR).write(holder);
   }

   public void writeIntervalYear(int value) {
      this.getWriter(Types.MinorType.INTERVALYEAR).writeIntervalYear(value);
   }

   public void write(TimeSecHolder holder) {
      this.getWriter(Types.MinorType.TIMESEC).write(holder);
   }

   public void writeTimeSec(int value) {
      this.getWriter(Types.MinorType.TIMESEC).writeTimeSec(value);
   }

   public void write(TimeMilliHolder holder) {
      this.getWriter(Types.MinorType.TIMEMILLI).write(holder);
   }

   public void writeTimeMilli(int value) {
      this.getWriter(Types.MinorType.TIMEMILLI).writeTimeMilli(value);
   }

   public void write(BigIntHolder holder) {
      this.getWriter(Types.MinorType.BIGINT).write(holder);
   }

   public void writeBigInt(long value) {
      this.getWriter(Types.MinorType.BIGINT).writeBigInt(value);
   }

   public void write(UInt8Holder holder) {
      this.getWriter(Types.MinorType.UINT8).write(holder);
   }

   public void writeUInt8(long value) {
      this.getWriter(Types.MinorType.UINT8).writeUInt8(value);
   }

   public void write(Float8Holder holder) {
      this.getWriter(Types.MinorType.FLOAT8).write(holder);
   }

   public void writeFloat8(double value) {
      this.getWriter(Types.MinorType.FLOAT8).writeFloat8(value);
   }

   public void write(DateMilliHolder holder) {
      this.getWriter(Types.MinorType.DATEMILLI).write(holder);
   }

   public void writeDateMilli(long value) {
      this.getWriter(Types.MinorType.DATEMILLI).writeDateMilli(value);
   }

   public void write(DurationHolder holder) {
      ArrowType.Duration arrowType = new ArrowType.Duration(holder.unit);
      this.getWriter(Types.MinorType.DURATION, arrowType).write(holder);
   }

   /** @deprecated */
   @Deprecated
   public void writeDuration(long value) {
      this.getWriter(Types.MinorType.DURATION).writeDuration(value);
   }

   public void write(TimeStampSecHolder holder) {
      this.getWriter(Types.MinorType.TIMESTAMPSEC).write(holder);
   }

   public void writeTimeStampSec(long value) {
      this.getWriter(Types.MinorType.TIMESTAMPSEC).writeTimeStampSec(value);
   }

   public void write(TimeStampMilliHolder holder) {
      this.getWriter(Types.MinorType.TIMESTAMPMILLI).write(holder);
   }

   public void writeTimeStampMilli(long value) {
      this.getWriter(Types.MinorType.TIMESTAMPMILLI).writeTimeStampMilli(value);
   }

   public void write(TimeStampMicroHolder holder) {
      this.getWriter(Types.MinorType.TIMESTAMPMICRO).write(holder);
   }

   public void writeTimeStampMicro(long value) {
      this.getWriter(Types.MinorType.TIMESTAMPMICRO).writeTimeStampMicro(value);
   }

   public void write(TimeStampNanoHolder holder) {
      this.getWriter(Types.MinorType.TIMESTAMPNANO).write(holder);
   }

   public void writeTimeStampNano(long value) {
      this.getWriter(Types.MinorType.TIMESTAMPNANO).writeTimeStampNano(value);
   }

   public void write(TimeStampSecTZHolder holder) {
      ArrowType.Timestamp arrowTypeWithoutTz = (ArrowType.Timestamp)Types.MinorType.TIMESTAMPSEC.getType();
      ArrowType.Timestamp arrowType = new ArrowType.Timestamp(arrowTypeWithoutTz.getUnit(), holder.timezone);
      this.getWriter(Types.MinorType.TIMESTAMPSECTZ, arrowType).write(holder);
   }

   /** @deprecated */
   @Deprecated
   public void writeTimeStampSecTZ(long value) {
      ArrowType.Timestamp arrowTypeWithoutTz = (ArrowType.Timestamp)Types.MinorType.TIMESTAMPSEC.getType();
      ArrowType.Timestamp arrowType = new ArrowType.Timestamp(arrowTypeWithoutTz.getUnit(), "UTC");
      this.getWriter(Types.MinorType.TIMESTAMPSECTZ, arrowType).writeTimeStampSecTZ(value);
   }

   public void write(TimeStampMilliTZHolder holder) {
      ArrowType.Timestamp arrowTypeWithoutTz = (ArrowType.Timestamp)Types.MinorType.TIMESTAMPMILLI.getType();
      ArrowType.Timestamp arrowType = new ArrowType.Timestamp(arrowTypeWithoutTz.getUnit(), holder.timezone);
      this.getWriter(Types.MinorType.TIMESTAMPMILLITZ, arrowType).write(holder);
   }

   /** @deprecated */
   @Deprecated
   public void writeTimeStampMilliTZ(long value) {
      ArrowType.Timestamp arrowTypeWithoutTz = (ArrowType.Timestamp)Types.MinorType.TIMESTAMPMILLI.getType();
      ArrowType.Timestamp arrowType = new ArrowType.Timestamp(arrowTypeWithoutTz.getUnit(), "UTC");
      this.getWriter(Types.MinorType.TIMESTAMPMILLITZ, arrowType).writeTimeStampMilliTZ(value);
   }

   public void write(TimeStampMicroTZHolder holder) {
      ArrowType.Timestamp arrowTypeWithoutTz = (ArrowType.Timestamp)Types.MinorType.TIMESTAMPMICRO.getType();
      ArrowType.Timestamp arrowType = new ArrowType.Timestamp(arrowTypeWithoutTz.getUnit(), holder.timezone);
      this.getWriter(Types.MinorType.TIMESTAMPMICROTZ, arrowType).write(holder);
   }

   /** @deprecated */
   @Deprecated
   public void writeTimeStampMicroTZ(long value) {
      ArrowType.Timestamp arrowTypeWithoutTz = (ArrowType.Timestamp)Types.MinorType.TIMESTAMPMICRO.getType();
      ArrowType.Timestamp arrowType = new ArrowType.Timestamp(arrowTypeWithoutTz.getUnit(), "UTC");
      this.getWriter(Types.MinorType.TIMESTAMPMICROTZ, arrowType).writeTimeStampMicroTZ(value);
   }

   public void write(TimeStampNanoTZHolder holder) {
      ArrowType.Timestamp arrowTypeWithoutTz = (ArrowType.Timestamp)Types.MinorType.TIMESTAMPNANO.getType();
      ArrowType.Timestamp arrowType = new ArrowType.Timestamp(arrowTypeWithoutTz.getUnit(), holder.timezone);
      this.getWriter(Types.MinorType.TIMESTAMPNANOTZ, arrowType).write(holder);
   }

   /** @deprecated */
   @Deprecated
   public void writeTimeStampNanoTZ(long value) {
      ArrowType.Timestamp arrowTypeWithoutTz = (ArrowType.Timestamp)Types.MinorType.TIMESTAMPNANO.getType();
      ArrowType.Timestamp arrowType = new ArrowType.Timestamp(arrowTypeWithoutTz.getUnit(), "UTC");
      this.getWriter(Types.MinorType.TIMESTAMPNANOTZ, arrowType).writeTimeStampNanoTZ(value);
   }

   public void write(TimeMicroHolder holder) {
      this.getWriter(Types.MinorType.TIMEMICRO).write(holder);
   }

   public void writeTimeMicro(long value) {
      this.getWriter(Types.MinorType.TIMEMICRO).writeTimeMicro(value);
   }

   public void write(TimeNanoHolder holder) {
      this.getWriter(Types.MinorType.TIMENANO).write(holder);
   }

   public void writeTimeNano(long value) {
      this.getWriter(Types.MinorType.TIMENANO).writeTimeNano(value);
   }

   public void write(IntervalDayHolder holder) {
      this.getWriter(Types.MinorType.INTERVALDAY).write(holder);
   }

   public void writeIntervalDay(int days, int milliseconds) {
      this.getWriter(Types.MinorType.INTERVALDAY).writeIntervalDay(days, milliseconds);
   }

   public void write(IntervalMonthDayNanoHolder holder) {
      this.getWriter(Types.MinorType.INTERVALMONTHDAYNANO).write(holder);
   }

   public void writeIntervalMonthDayNano(int months, int days, long nanoseconds) {
      this.getWriter(Types.MinorType.INTERVALMONTHDAYNANO).writeIntervalMonthDayNano(months, days, nanoseconds);
   }

   public void write(Decimal256Holder holder) {
      this.getWriter(Types.MinorType.DECIMAL256).write(holder);
   }

   public void writeDecimal256(long start, ArrowBuf buffer, ArrowType arrowType) {
      this.getWriter(Types.MinorType.DECIMAL256).writeDecimal256(start, buffer, arrowType);
   }

   public void writeDecimal256(long start, ArrowBuf buffer) {
      this.getWriter(Types.MinorType.DECIMAL256).writeDecimal256(start, buffer);
   }

   public void writeBigEndianBytesToDecimal256(byte[] value, ArrowType arrowType) {
      this.getWriter(Types.MinorType.DECIMAL256).writeBigEndianBytesToDecimal256(value, arrowType);
   }

   public void writeBigEndianBytesToDecimal256(byte[] value) {
      this.getWriter(Types.MinorType.DECIMAL256).writeBigEndianBytesToDecimal256(value);
   }

   public void write(DecimalHolder holder) {
      this.getWriter(Types.MinorType.DECIMAL).write(holder);
   }

   public void writeDecimal(int start, ArrowBuf buffer, ArrowType arrowType) {
      this.getWriter(Types.MinorType.DECIMAL).writeDecimal((long)start, buffer, arrowType);
   }

   public void writeDecimal(int start, ArrowBuf buffer) {
      this.getWriter(Types.MinorType.DECIMAL).writeDecimal((long)start, buffer);
   }

   public void writeBigEndianBytesToDecimal(byte[] value, ArrowType arrowType) {
      this.getWriter(Types.MinorType.DECIMAL).writeBigEndianBytesToDecimal(value, arrowType);
   }

   public void writeBigEndianBytesToDecimal(byte[] value) {
      this.getWriter(Types.MinorType.DECIMAL).writeBigEndianBytesToDecimal(value);
   }

   public void write(FixedSizeBinaryHolder holder) {
      ArrowType.FixedSizeBinary arrowType = new ArrowType.FixedSizeBinary(holder.byteWidth);
      this.getWriter(Types.MinorType.FIXEDSIZEBINARY, arrowType).write(holder);
   }

   /** @deprecated */
   @Deprecated
   public void writeFixedSizeBinary(ArrowBuf buffer) {
      this.getWriter(Types.MinorType.FIXEDSIZEBINARY).writeFixedSizeBinary(buffer);
   }

   public void write(VarBinaryHolder holder) {
      this.getWriter(Types.MinorType.VARBINARY).write(holder);
   }

   public void writeVarBinary(int start, int end, ArrowBuf buffer) {
      this.getWriter(Types.MinorType.VARBINARY).writeVarBinary(start, end, buffer);
   }

   public void writeVarBinary(byte[] value) {
      this.getWriter(Types.MinorType.VARBINARY).writeVarBinary(value);
   }

   public void writeVarBinary(byte[] value, int offset, int length) {
      this.getWriter(Types.MinorType.VARBINARY).writeVarBinary(value, offset, length);
   }

   public void writeVarBinary(ByteBuffer value) {
      this.getWriter(Types.MinorType.VARBINARY).writeVarBinary(value);
   }

   public void writeVarBinary(ByteBuffer value, int offset, int length) {
      this.getWriter(Types.MinorType.VARBINARY).writeVarBinary(value, offset, length);
   }

   public void write(VarCharHolder holder) {
      this.getWriter(Types.MinorType.VARCHAR).write(holder);
   }

   public void writeVarChar(int start, int end, ArrowBuf buffer) {
      this.getWriter(Types.MinorType.VARCHAR).writeVarChar(start, end, buffer);
   }

   public void writeVarChar(Text value) {
      this.getWriter(Types.MinorType.VARCHAR).writeVarChar(value);
   }

   public void writeVarChar(String value) {
      this.getWriter(Types.MinorType.VARCHAR).writeVarChar(value);
   }

   public void write(ViewVarBinaryHolder holder) {
      this.getWriter(Types.MinorType.VIEWVARBINARY).write(holder);
   }

   public void writeViewVarBinary(int start, int end, ArrowBuf buffer) {
      this.getWriter(Types.MinorType.VIEWVARBINARY).writeViewVarBinary(start, end, buffer);
   }

   public void writeViewVarBinary(byte[] value) {
      this.getWriter(Types.MinorType.VIEWVARBINARY).writeViewVarBinary(value);
   }

   public void writeViewVarBinary(byte[] value, int offset, int length) {
      this.getWriter(Types.MinorType.VIEWVARBINARY).writeViewVarBinary(value, offset, length);
   }

   public void writeViewVarBinary(ByteBuffer value) {
      this.getWriter(Types.MinorType.VIEWVARBINARY).writeViewVarBinary(value);
   }

   public void writeViewVarBinary(ByteBuffer value, int offset, int length) {
      this.getWriter(Types.MinorType.VIEWVARBINARY).writeViewVarBinary(value, offset, length);
   }

   public void write(ViewVarCharHolder holder) {
      this.getWriter(Types.MinorType.VIEWVARCHAR).write(holder);
   }

   public void writeViewVarChar(int start, int end, ArrowBuf buffer) {
      this.getWriter(Types.MinorType.VIEWVARCHAR).writeViewVarChar(start, end, buffer);
   }

   public void writeViewVarChar(Text value) {
      this.getWriter(Types.MinorType.VIEWVARCHAR).writeViewVarChar(value);
   }

   public void writeViewVarChar(String value) {
      this.getWriter(Types.MinorType.VIEWVARCHAR).writeViewVarChar(value);
   }

   public void write(LargeVarCharHolder holder) {
      this.getWriter(Types.MinorType.LARGEVARCHAR).write(holder);
   }

   public void writeLargeVarChar(long start, long end, ArrowBuf buffer) {
      this.getWriter(Types.MinorType.LARGEVARCHAR).writeLargeVarChar(start, end, buffer);
   }

   public void writeLargeVarChar(Text value) {
      this.getWriter(Types.MinorType.LARGEVARCHAR).writeLargeVarChar(value);
   }

   public void writeLargeVarChar(String value) {
      this.getWriter(Types.MinorType.LARGEVARCHAR).writeLargeVarChar(value);
   }

   public void write(LargeVarBinaryHolder holder) {
      this.getWriter(Types.MinorType.LARGEVARBINARY).write(holder);
   }

   public void writeLargeVarBinary(long start, long end, ArrowBuf buffer) {
      this.getWriter(Types.MinorType.LARGEVARBINARY).writeLargeVarBinary(start, end, buffer);
   }

   public void writeLargeVarBinary(byte[] value) {
      this.getWriter(Types.MinorType.LARGEVARBINARY).writeLargeVarBinary(value);
   }

   public void writeLargeVarBinary(byte[] value, int offset, int length) {
      this.getWriter(Types.MinorType.LARGEVARBINARY).writeLargeVarBinary(value, offset, length);
   }

   public void writeLargeVarBinary(ByteBuffer value) {
      this.getWriter(Types.MinorType.LARGEVARBINARY).writeLargeVarBinary(value);
   }

   public void writeLargeVarBinary(ByteBuffer value, int offset, int length) {
      this.getWriter(Types.MinorType.LARGEVARBINARY).writeLargeVarBinary(value, offset, length);
   }

   public void write(BitHolder holder) {
      this.getWriter(Types.MinorType.BIT).write(holder);
   }

   public void writeBit(int value) {
      this.getWriter(Types.MinorType.BIT).writeBit(value);
   }

   public void writeNull() {
   }

   public BaseWriter.StructWriter struct() {
      return this.getWriter(Types.MinorType.LIST).struct();
   }

   public BaseWriter.ListWriter list() {
      return this.getWriter(Types.MinorType.LIST).list();
   }

   public BaseWriter.ListWriter listView() {
      return this.getWriter(Types.MinorType.LISTVIEW).listView();
   }

   public BaseWriter.MapWriter map() {
      return this.getWriter(Types.MinorType.LIST).map();
   }

   public BaseWriter.MapWriter map(boolean keysSorted) {
      return this.getWriter(Types.MinorType.MAP, new ArrowType.Map(keysSorted));
   }

   public BaseWriter.StructWriter struct(String name) {
      return this.getWriter(Types.MinorType.STRUCT).struct(name);
   }

   public BaseWriter.ListWriter list(String name) {
      return this.getWriter(Types.MinorType.STRUCT).list(name);
   }

   public BaseWriter.ListWriter listView(String name) {
      return this.getWriter(Types.MinorType.STRUCT).listView(name);
   }

   public BaseWriter.MapWriter map(String name) {
      return this.getWriter(Types.MinorType.STRUCT).map(name);
   }

   public BaseWriter.MapWriter map(String name, boolean keysSorted) {
      return this.getWriter(Types.MinorType.STRUCT).map(name, keysSorted);
   }

   public TinyIntWriter tinyInt(String name) {
      return this.getWriter(Types.MinorType.STRUCT).tinyInt(name);
   }

   public TinyIntWriter tinyInt() {
      return this.getWriter(Types.MinorType.LIST).tinyInt();
   }

   public UInt1Writer uInt1(String name) {
      return this.getWriter(Types.MinorType.STRUCT).uInt1(name);
   }

   public UInt1Writer uInt1() {
      return this.getWriter(Types.MinorType.LIST).uInt1();
   }

   public UInt2Writer uInt2(String name) {
      return this.getWriter(Types.MinorType.STRUCT).uInt2(name);
   }

   public UInt2Writer uInt2() {
      return this.getWriter(Types.MinorType.LIST).uInt2();
   }

   public SmallIntWriter smallInt(String name) {
      return this.getWriter(Types.MinorType.STRUCT).smallInt(name);
   }

   public SmallIntWriter smallInt() {
      return this.getWriter(Types.MinorType.LIST).smallInt();
   }

   public Float2Writer float2(String name) {
      return this.getWriter(Types.MinorType.STRUCT).float2(name);
   }

   public Float2Writer float2() {
      return this.getWriter(Types.MinorType.LIST).float2();
   }

   public IntWriter integer(String name) {
      return this.getWriter(Types.MinorType.STRUCT).integer(name);
   }

   public IntWriter integer() {
      return this.getWriter(Types.MinorType.LIST).integer();
   }

   public UInt4Writer uInt4(String name) {
      return this.getWriter(Types.MinorType.STRUCT).uInt4(name);
   }

   public UInt4Writer uInt4() {
      return this.getWriter(Types.MinorType.LIST).uInt4();
   }

   public Float4Writer float4(String name) {
      return this.getWriter(Types.MinorType.STRUCT).float4(name);
   }

   public Float4Writer float4() {
      return this.getWriter(Types.MinorType.LIST).float4();
   }

   public DateDayWriter dateDay(String name) {
      return this.getWriter(Types.MinorType.STRUCT).dateDay(name);
   }

   public DateDayWriter dateDay() {
      return this.getWriter(Types.MinorType.LIST).dateDay();
   }

   public IntervalYearWriter intervalYear(String name) {
      return this.getWriter(Types.MinorType.STRUCT).intervalYear(name);
   }

   public IntervalYearWriter intervalYear() {
      return this.getWriter(Types.MinorType.LIST).intervalYear();
   }

   public TimeSecWriter timeSec(String name) {
      return this.getWriter(Types.MinorType.STRUCT).timeSec(name);
   }

   public TimeSecWriter timeSec() {
      return this.getWriter(Types.MinorType.LIST).timeSec();
   }

   public TimeMilliWriter timeMilli(String name) {
      return this.getWriter(Types.MinorType.STRUCT).timeMilli(name);
   }

   public TimeMilliWriter timeMilli() {
      return this.getWriter(Types.MinorType.LIST).timeMilli();
   }

   public BigIntWriter bigInt(String name) {
      return this.getWriter(Types.MinorType.STRUCT).bigInt(name);
   }

   public BigIntWriter bigInt() {
      return this.getWriter(Types.MinorType.LIST).bigInt();
   }

   public UInt8Writer uInt8(String name) {
      return this.getWriter(Types.MinorType.STRUCT).uInt8(name);
   }

   public UInt8Writer uInt8() {
      return this.getWriter(Types.MinorType.LIST).uInt8();
   }

   public Float8Writer float8(String name) {
      return this.getWriter(Types.MinorType.STRUCT).float8(name);
   }

   public Float8Writer float8() {
      return this.getWriter(Types.MinorType.LIST).float8();
   }

   public DateMilliWriter dateMilli(String name) {
      return this.getWriter(Types.MinorType.STRUCT).dateMilli(name);
   }

   public DateMilliWriter dateMilli() {
      return this.getWriter(Types.MinorType.LIST).dateMilli();
   }

   public DurationWriter duration(String name, TimeUnit unit) {
      return this.getWriter(Types.MinorType.STRUCT).duration(name, unit);
   }

   public DurationWriter duration(String name) {
      return this.getWriter(Types.MinorType.STRUCT).duration(name);
   }

   public DurationWriter duration() {
      return this.getWriter(Types.MinorType.LIST).duration();
   }

   public TimeStampSecWriter timeStampSec(String name) {
      return this.getWriter(Types.MinorType.STRUCT).timeStampSec(name);
   }

   public TimeStampSecWriter timeStampSec() {
      return this.getWriter(Types.MinorType.LIST).timeStampSec();
   }

   public TimeStampMilliWriter timeStampMilli(String name) {
      return this.getWriter(Types.MinorType.STRUCT).timeStampMilli(name);
   }

   public TimeStampMilliWriter timeStampMilli() {
      return this.getWriter(Types.MinorType.LIST).timeStampMilli();
   }

   public TimeStampMicroWriter timeStampMicro(String name) {
      return this.getWriter(Types.MinorType.STRUCT).timeStampMicro(name);
   }

   public TimeStampMicroWriter timeStampMicro() {
      return this.getWriter(Types.MinorType.LIST).timeStampMicro();
   }

   public TimeStampNanoWriter timeStampNano(String name) {
      return this.getWriter(Types.MinorType.STRUCT).timeStampNano(name);
   }

   public TimeStampNanoWriter timeStampNano() {
      return this.getWriter(Types.MinorType.LIST).timeStampNano();
   }

   public TimeStampSecTZWriter timeStampSecTZ(String name, String timezone) {
      return this.getWriter(Types.MinorType.STRUCT).timeStampSecTZ(name, timezone);
   }

   public TimeStampSecTZWriter timeStampSecTZ(String name) {
      return this.getWriter(Types.MinorType.STRUCT).timeStampSecTZ(name);
   }

   public TimeStampSecTZWriter timeStampSecTZ() {
      return this.getWriter(Types.MinorType.LIST).timeStampSecTZ();
   }

   public TimeStampMilliTZWriter timeStampMilliTZ(String name, String timezone) {
      return this.getWriter(Types.MinorType.STRUCT).timeStampMilliTZ(name, timezone);
   }

   public TimeStampMilliTZWriter timeStampMilliTZ(String name) {
      return this.getWriter(Types.MinorType.STRUCT).timeStampMilliTZ(name);
   }

   public TimeStampMilliTZWriter timeStampMilliTZ() {
      return this.getWriter(Types.MinorType.LIST).timeStampMilliTZ();
   }

   public TimeStampMicroTZWriter timeStampMicroTZ(String name, String timezone) {
      return this.getWriter(Types.MinorType.STRUCT).timeStampMicroTZ(name, timezone);
   }

   public TimeStampMicroTZWriter timeStampMicroTZ(String name) {
      return this.getWriter(Types.MinorType.STRUCT).timeStampMicroTZ(name);
   }

   public TimeStampMicroTZWriter timeStampMicroTZ() {
      return this.getWriter(Types.MinorType.LIST).timeStampMicroTZ();
   }

   public TimeStampNanoTZWriter timeStampNanoTZ(String name, String timezone) {
      return this.getWriter(Types.MinorType.STRUCT).timeStampNanoTZ(name, timezone);
   }

   public TimeStampNanoTZWriter timeStampNanoTZ(String name) {
      return this.getWriter(Types.MinorType.STRUCT).timeStampNanoTZ(name);
   }

   public TimeStampNanoTZWriter timeStampNanoTZ() {
      return this.getWriter(Types.MinorType.LIST).timeStampNanoTZ();
   }

   public TimeMicroWriter timeMicro(String name) {
      return this.getWriter(Types.MinorType.STRUCT).timeMicro(name);
   }

   public TimeMicroWriter timeMicro() {
      return this.getWriter(Types.MinorType.LIST).timeMicro();
   }

   public TimeNanoWriter timeNano(String name) {
      return this.getWriter(Types.MinorType.STRUCT).timeNano(name);
   }

   public TimeNanoWriter timeNano() {
      return this.getWriter(Types.MinorType.LIST).timeNano();
   }

   public IntervalDayWriter intervalDay(String name) {
      return this.getWriter(Types.MinorType.STRUCT).intervalDay(name);
   }

   public IntervalDayWriter intervalDay() {
      return this.getWriter(Types.MinorType.LIST).intervalDay();
   }

   public IntervalMonthDayNanoWriter intervalMonthDayNano(String name) {
      return this.getWriter(Types.MinorType.STRUCT).intervalMonthDayNano(name);
   }

   public IntervalMonthDayNanoWriter intervalMonthDayNano() {
      return this.getWriter(Types.MinorType.LIST).intervalMonthDayNano();
   }

   public Decimal256Writer decimal256(String name, int scale, int precision) {
      return this.getWriter(Types.MinorType.STRUCT).decimal256(name, scale, precision);
   }

   public Decimal256Writer decimal256(String name) {
      return this.getWriter(Types.MinorType.STRUCT).decimal256(name);
   }

   public Decimal256Writer decimal256() {
      return this.getWriter(Types.MinorType.LIST).decimal256();
   }

   public DecimalWriter decimal(String name, int scale, int precision) {
      return this.getWriter(Types.MinorType.STRUCT).decimal(name, scale, precision);
   }

   public DecimalWriter decimal(String name) {
      return this.getWriter(Types.MinorType.STRUCT).decimal(name);
   }

   public DecimalWriter decimal() {
      return this.getWriter(Types.MinorType.LIST).decimal();
   }

   public FixedSizeBinaryWriter fixedSizeBinary(String name, int byteWidth) {
      return this.getWriter(Types.MinorType.STRUCT).fixedSizeBinary(name, byteWidth);
   }

   public FixedSizeBinaryWriter fixedSizeBinary(String name) {
      return this.getWriter(Types.MinorType.STRUCT).fixedSizeBinary(name);
   }

   public FixedSizeBinaryWriter fixedSizeBinary() {
      return this.getWriter(Types.MinorType.LIST).fixedSizeBinary();
   }

   public VarBinaryWriter varBinary(String name) {
      return this.getWriter(Types.MinorType.STRUCT).varBinary(name);
   }

   public VarBinaryWriter varBinary() {
      return this.getWriter(Types.MinorType.LIST).varBinary();
   }

   public VarCharWriter varChar(String name) {
      return this.getWriter(Types.MinorType.STRUCT).varChar(name);
   }

   public VarCharWriter varChar() {
      return this.getWriter(Types.MinorType.LIST).varChar();
   }

   public ViewVarBinaryWriter viewVarBinary(String name) {
      return this.getWriter(Types.MinorType.STRUCT).viewVarBinary(name);
   }

   public ViewVarBinaryWriter viewVarBinary() {
      return this.getWriter(Types.MinorType.LIST).viewVarBinary();
   }

   public ViewVarCharWriter viewVarChar(String name) {
      return this.getWriter(Types.MinorType.STRUCT).viewVarChar(name);
   }

   public ViewVarCharWriter viewVarChar() {
      return this.getWriter(Types.MinorType.LIST).viewVarChar();
   }

   public LargeVarCharWriter largeVarChar(String name) {
      return this.getWriter(Types.MinorType.STRUCT).largeVarChar(name);
   }

   public LargeVarCharWriter largeVarChar() {
      return this.getWriter(Types.MinorType.LIST).largeVarChar();
   }

   public LargeVarBinaryWriter largeVarBinary(String name) {
      return this.getWriter(Types.MinorType.STRUCT).largeVarBinary(name);
   }

   public LargeVarBinaryWriter largeVarBinary() {
      return this.getWriter(Types.MinorType.LIST).largeVarBinary();
   }

   public BitWriter bit(String name) {
      return this.getWriter(Types.MinorType.STRUCT).bit(name);
   }

   public BitWriter bit() {
      return this.getWriter(Types.MinorType.LIST).bit();
   }

   public void copyReader(FieldReader reader) {
      this.getWriter().copyReader(reader);
   }

   public void copyReaderToField(String name, FieldReader reader) {
      this.getWriter().copyReaderToField(name, reader);
   }
}
