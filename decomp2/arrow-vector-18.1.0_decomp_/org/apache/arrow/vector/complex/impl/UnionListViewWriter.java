package org.apache.arrow.vector.complex.impl;

import java.math.BigDecimal;
import java.nio.ByteBuffer;
import org.apache.arrow.memory.ArrowBuf;
import org.apache.arrow.vector.complex.ListViewVector;
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
import org.apache.arrow.vector.types.pojo.Field;
import org.apache.arrow.vector.util.Text;

public class UnionListViewWriter extends AbstractFieldWriter {
   protected ListViewVector vector;
   protected PromotableWriter writer;
   private boolean inStruct;
   private boolean listStarted;
   private String structName;
   private static final int OFFSET_WIDTH = 4;
   private static final long SIZE_WIDTH = 4L;

   public UnionListViewWriter(ListViewVector vector) {
      this(vector, NullableStructWriterFactory.getNullableStructWriterFactoryInstance());
   }

   public UnionListViewWriter(ListViewVector vector, NullableStructWriterFactory nullableStructWriterFactory) {
      this.inStruct = false;
      this.listStarted = false;
      this.vector = vector;
      this.writer = new PromotableViewWriter(vector.getDataVector(), vector, nullableStructWriterFactory);
   }

   public UnionListViewWriter(ListViewVector vector, AbstractFieldWriter parent) {
      this(vector);
   }

   public void allocate() {
      this.vector.allocateNew();
   }

   public void clear() {
      this.vector.clear();
   }

   public Field getField() {
      return this.vector.getField();
   }

   public void setValueCount(int count) {
      this.vector.setValueCount(count);
   }

   public int getValueCapacity() {
      return this.vector.getValueCapacity();
   }

   public void close() throws Exception {
      this.vector.close();
      this.writer.close();
   }

   public void setPosition(int index) {
      super.setPosition(index);
   }

   public TinyIntWriter tinyInt() {
      return this;
   }

   public TinyIntWriter tinyInt(String name) {
      this.structName = name;
      return this.writer.tinyInt(name);
   }

   public UInt1Writer uInt1() {
      return this;
   }

   public UInt1Writer uInt1(String name) {
      this.structName = name;
      return this.writer.uInt1(name);
   }

   public UInt2Writer uInt2() {
      return this;
   }

   public UInt2Writer uInt2(String name) {
      this.structName = name;
      return this.writer.uInt2(name);
   }

   public SmallIntWriter smallInt() {
      return this;
   }

   public SmallIntWriter smallInt(String name) {
      this.structName = name;
      return this.writer.smallInt(name);
   }

   public Float2Writer float2() {
      return this;
   }

   public Float2Writer float2(String name) {
      this.structName = name;
      return this.writer.float2(name);
   }

   public IntWriter integer() {
      return this;
   }

   public IntWriter integer(String name) {
      this.structName = name;
      return this.writer.integer(name);
   }

   public UInt4Writer uInt4() {
      return this;
   }

   public UInt4Writer uInt4(String name) {
      this.structName = name;
      return this.writer.uInt4(name);
   }

   public Float4Writer float4() {
      return this;
   }

   public Float4Writer float4(String name) {
      this.structName = name;
      return this.writer.float4(name);
   }

   public DateDayWriter dateDay() {
      return this;
   }

   public DateDayWriter dateDay(String name) {
      this.structName = name;
      return this.writer.dateDay(name);
   }

   public IntervalYearWriter intervalYear() {
      return this;
   }

   public IntervalYearWriter intervalYear(String name) {
      this.structName = name;
      return this.writer.intervalYear(name);
   }

   public TimeSecWriter timeSec() {
      return this;
   }

   public TimeSecWriter timeSec(String name) {
      this.structName = name;
      return this.writer.timeSec(name);
   }

   public TimeMilliWriter timeMilli() {
      return this;
   }

   public TimeMilliWriter timeMilli(String name) {
      this.structName = name;
      return this.writer.timeMilli(name);
   }

   public BigIntWriter bigInt() {
      return this;
   }

   public BigIntWriter bigInt(String name) {
      this.structName = name;
      return this.writer.bigInt(name);
   }

   public UInt8Writer uInt8() {
      return this;
   }

   public UInt8Writer uInt8(String name) {
      this.structName = name;
      return this.writer.uInt8(name);
   }

   public Float8Writer float8() {
      return this;
   }

   public Float8Writer float8(String name) {
      this.structName = name;
      return this.writer.float8(name);
   }

   public DateMilliWriter dateMilli() {
      return this;
   }

   public DateMilliWriter dateMilli(String name) {
      this.structName = name;
      return this.writer.dateMilli(name);
   }

   public DurationWriter duration() {
      return this;
   }

   public DurationWriter duration(String name, TimeUnit unit) {
      return this.writer.duration(name, unit);
   }

   public DurationWriter duration(String name) {
      this.structName = name;
      return this.writer.duration(name);
   }

   public TimeStampSecWriter timeStampSec() {
      return this;
   }

   public TimeStampSecWriter timeStampSec(String name) {
      this.structName = name;
      return this.writer.timeStampSec(name);
   }

   public TimeStampMilliWriter timeStampMilli() {
      return this;
   }

   public TimeStampMilliWriter timeStampMilli(String name) {
      this.structName = name;
      return this.writer.timeStampMilli(name);
   }

   public TimeStampMicroWriter timeStampMicro() {
      return this;
   }

   public TimeStampMicroWriter timeStampMicro(String name) {
      this.structName = name;
      return this.writer.timeStampMicro(name);
   }

   public TimeStampNanoWriter timeStampNano() {
      return this;
   }

   public TimeStampNanoWriter timeStampNano(String name) {
      this.structName = name;
      return this.writer.timeStampNano(name);
   }

   public TimeStampSecTZWriter timeStampSecTZ() {
      return this;
   }

   public TimeStampSecTZWriter timeStampSecTZ(String name, String timezone) {
      return this.writer.timeStampSecTZ(name, timezone);
   }

   public TimeStampSecTZWriter timeStampSecTZ(String name) {
      this.structName = name;
      return this.writer.timeStampSecTZ(name);
   }

   public TimeStampMilliTZWriter timeStampMilliTZ() {
      return this;
   }

   public TimeStampMilliTZWriter timeStampMilliTZ(String name, String timezone) {
      return this.writer.timeStampMilliTZ(name, timezone);
   }

   public TimeStampMilliTZWriter timeStampMilliTZ(String name) {
      this.structName = name;
      return this.writer.timeStampMilliTZ(name);
   }

   public TimeStampMicroTZWriter timeStampMicroTZ() {
      return this;
   }

   public TimeStampMicroTZWriter timeStampMicroTZ(String name, String timezone) {
      return this.writer.timeStampMicroTZ(name, timezone);
   }

   public TimeStampMicroTZWriter timeStampMicroTZ(String name) {
      this.structName = name;
      return this.writer.timeStampMicroTZ(name);
   }

   public TimeStampNanoTZWriter timeStampNanoTZ() {
      return this;
   }

   public TimeStampNanoTZWriter timeStampNanoTZ(String name, String timezone) {
      return this.writer.timeStampNanoTZ(name, timezone);
   }

   public TimeStampNanoTZWriter timeStampNanoTZ(String name) {
      this.structName = name;
      return this.writer.timeStampNanoTZ(name);
   }

   public TimeMicroWriter timeMicro() {
      return this;
   }

   public TimeMicroWriter timeMicro(String name) {
      this.structName = name;
      return this.writer.timeMicro(name);
   }

   public TimeNanoWriter timeNano() {
      return this;
   }

   public TimeNanoWriter timeNano(String name) {
      this.structName = name;
      return this.writer.timeNano(name);
   }

   public IntervalDayWriter intervalDay() {
      return this;
   }

   public IntervalDayWriter intervalDay(String name) {
      this.structName = name;
      return this.writer.intervalDay(name);
   }

   public IntervalMonthDayNanoWriter intervalMonthDayNano() {
      return this;
   }

   public IntervalMonthDayNanoWriter intervalMonthDayNano(String name) {
      this.structName = name;
      return this.writer.intervalMonthDayNano(name);
   }

   public Decimal256Writer decimal256() {
      return this;
   }

   public Decimal256Writer decimal256(String name, int scale, int precision) {
      return this.writer.decimal256(name, scale, precision);
   }

   public Decimal256Writer decimal256(String name) {
      this.structName = name;
      return this.writer.decimal256(name);
   }

   public DecimalWriter decimal() {
      return this;
   }

   public DecimalWriter decimal(String name, int scale, int precision) {
      return this.writer.decimal(name, scale, precision);
   }

   public DecimalWriter decimal(String name) {
      this.structName = name;
      return this.writer.decimal(name);
   }

   public FixedSizeBinaryWriter fixedSizeBinary() {
      return this;
   }

   public FixedSizeBinaryWriter fixedSizeBinary(String name, int byteWidth) {
      return this.writer.fixedSizeBinary(name, byteWidth);
   }

   public FixedSizeBinaryWriter fixedSizeBinary(String name) {
      this.structName = name;
      return this.writer.fixedSizeBinary(name);
   }

   public VarBinaryWriter varBinary() {
      return this;
   }

   public VarBinaryWriter varBinary(String name) {
      this.structName = name;
      return this.writer.varBinary(name);
   }

   public VarCharWriter varChar() {
      return this;
   }

   public VarCharWriter varChar(String name) {
      this.structName = name;
      return this.writer.varChar(name);
   }

   public ViewVarBinaryWriter viewVarBinary() {
      return this;
   }

   public ViewVarBinaryWriter viewVarBinary(String name) {
      this.structName = name;
      return this.writer.viewVarBinary(name);
   }

   public ViewVarCharWriter viewVarChar() {
      return this;
   }

   public ViewVarCharWriter viewVarChar(String name) {
      this.structName = name;
      return this.writer.viewVarChar(name);
   }

   public LargeVarCharWriter largeVarChar() {
      return this;
   }

   public LargeVarCharWriter largeVarChar(String name) {
      this.structName = name;
      return this.writer.largeVarChar(name);
   }

   public LargeVarBinaryWriter largeVarBinary() {
      return this;
   }

   public LargeVarBinaryWriter largeVarBinary(String name) {
      this.structName = name;
      return this.writer.largeVarBinary(name);
   }

   public BitWriter bit() {
      return this;
   }

   public BitWriter bit(String name) {
      this.structName = name;
      return this.writer.bit(name);
   }

   public BaseWriter.StructWriter struct() {
      this.inStruct = true;
      return this;
   }

   public BaseWriter.ListWriter list() {
      return this.writer;
   }

   public BaseWriter.ListWriter list(String name) {
      BaseWriter.ListWriter listWriter = this.writer.list(name);
      return listWriter;
   }

   public BaseWriter.ListWriter listView() {
      return this.writer;
   }

   public BaseWriter.ListWriter listView(String name) {
      BaseWriter.ListWriter listWriter = this.writer.listView(name);
      return listWriter;
   }

   public BaseWriter.StructWriter struct(String name) {
      BaseWriter.StructWriter structWriter = this.writer.struct(name);
      return structWriter;
   }

   public BaseWriter.MapWriter map() {
      return this.writer;
   }

   public BaseWriter.MapWriter map(String name) {
      BaseWriter.MapWriter mapWriter = this.writer.map(name);
      return mapWriter;
   }

   public BaseWriter.MapWriter map(boolean keysSorted) {
      this.writer.map(keysSorted);
      return this.writer;
   }

   public BaseWriter.MapWriter map(String name, boolean keysSorted) {
      BaseWriter.MapWriter mapWriter = this.writer.map(name, keysSorted);
      return mapWriter;
   }

   public void startList() {
      this.vector.startNewValue(this.idx());
      this.writer.setPosition(this.vector.getOffsetBuffer().getInt((long)(this.idx() * 4)));
      this.listStarted = true;
   }

   public void endList() {
      int sizeUptoIdx = 0;

      for(int i = 0; i < this.idx(); ++i) {
         sizeUptoIdx += this.vector.getSizeBuffer().getInt((long)i * 4L);
      }

      this.vector.getSizeBuffer().setInt((long)this.idx() * 4L, this.writer.idx() - sizeUptoIdx);
      this.setPosition(this.idx() + 1);
      this.listStarted = false;
   }

   public void startListView() {
      this.vector.startNewValue(this.idx());
      this.writer.setPosition(this.vector.getOffsetBuffer().getInt((long)(this.idx() * 4)));
      this.listStarted = true;
   }

   public void endListView() {
      int sizeUptoIdx = 0;

      for(int i = 0; i < this.idx(); ++i) {
         sizeUptoIdx += this.vector.getSizeBuffer().getInt((long)i * 4L);
      }

      this.vector.getSizeBuffer().setInt((long)this.idx() * 4L, this.writer.idx() - sizeUptoIdx);
      this.setPosition(this.idx() + 1);
      this.listStarted = false;
   }

   public void start() {
      this.writer.start();
   }

   public void end() {
      this.writer.end();
      this.inStruct = false;
   }

   public void writeNull() {
      if (!this.listStarted) {
         this.vector.setNull(this.idx());
      } else {
         this.writer.writeNull();
      }

   }

   public void writeTinyInt(byte value) {
      this.writer.writeTinyInt(value);
      this.writer.setPosition(this.writer.idx() + 1);
   }

   public void write(TinyIntHolder holder) {
      this.writer.writeTinyInt(holder.value);
      this.writer.setPosition(this.writer.idx() + 1);
   }

   public void writeUInt1(byte value) {
      this.writer.writeUInt1(value);
      this.writer.setPosition(this.writer.idx() + 1);
   }

   public void write(UInt1Holder holder) {
      this.writer.writeUInt1(holder.value);
      this.writer.setPosition(this.writer.idx() + 1);
   }

   public void writeUInt2(char value) {
      this.writer.writeUInt2(value);
      this.writer.setPosition(this.writer.idx() + 1);
   }

   public void write(UInt2Holder holder) {
      this.writer.writeUInt2(holder.value);
      this.writer.setPosition(this.writer.idx() + 1);
   }

   public void writeSmallInt(short value) {
      this.writer.writeSmallInt(value);
      this.writer.setPosition(this.writer.idx() + 1);
   }

   public void write(SmallIntHolder holder) {
      this.writer.writeSmallInt(holder.value);
      this.writer.setPosition(this.writer.idx() + 1);
   }

   public void writeFloat2(short value) {
      this.writer.writeFloat2(value);
      this.writer.setPosition(this.writer.idx() + 1);
   }

   public void write(Float2Holder holder) {
      this.writer.writeFloat2(holder.value);
      this.writer.setPosition(this.writer.idx() + 1);
   }

   public void writeInt(int value) {
      this.writer.writeInt(value);
      this.writer.setPosition(this.writer.idx() + 1);
   }

   public void write(IntHolder holder) {
      this.writer.writeInt(holder.value);
      this.writer.setPosition(this.writer.idx() + 1);
   }

   public void writeUInt4(int value) {
      this.writer.writeUInt4(value);
      this.writer.setPosition(this.writer.idx() + 1);
   }

   public void write(UInt4Holder holder) {
      this.writer.writeUInt4(holder.value);
      this.writer.setPosition(this.writer.idx() + 1);
   }

   public void writeFloat4(float value) {
      this.writer.writeFloat4(value);
      this.writer.setPosition(this.writer.idx() + 1);
   }

   public void write(Float4Holder holder) {
      this.writer.writeFloat4(holder.value);
      this.writer.setPosition(this.writer.idx() + 1);
   }

   public void writeDateDay(int value) {
      this.writer.writeDateDay(value);
      this.writer.setPosition(this.writer.idx() + 1);
   }

   public void write(DateDayHolder holder) {
      this.writer.writeDateDay(holder.value);
      this.writer.setPosition(this.writer.idx() + 1);
   }

   public void writeIntervalYear(int value) {
      this.writer.writeIntervalYear(value);
      this.writer.setPosition(this.writer.idx() + 1);
   }

   public void write(IntervalYearHolder holder) {
      this.writer.writeIntervalYear(holder.value);
      this.writer.setPosition(this.writer.idx() + 1);
   }

   public void writeTimeSec(int value) {
      this.writer.writeTimeSec(value);
      this.writer.setPosition(this.writer.idx() + 1);
   }

   public void write(TimeSecHolder holder) {
      this.writer.writeTimeSec(holder.value);
      this.writer.setPosition(this.writer.idx() + 1);
   }

   public void writeTimeMilli(int value) {
      this.writer.writeTimeMilli(value);
      this.writer.setPosition(this.writer.idx() + 1);
   }

   public void write(TimeMilliHolder holder) {
      this.writer.writeTimeMilli(holder.value);
      this.writer.setPosition(this.writer.idx() + 1);
   }

   public void writeBigInt(long value) {
      this.writer.writeBigInt(value);
      this.writer.setPosition(this.writer.idx() + 1);
   }

   public void write(BigIntHolder holder) {
      this.writer.writeBigInt(holder.value);
      this.writer.setPosition(this.writer.idx() + 1);
   }

   public void writeUInt8(long value) {
      this.writer.writeUInt8(value);
      this.writer.setPosition(this.writer.idx() + 1);
   }

   public void write(UInt8Holder holder) {
      this.writer.writeUInt8(holder.value);
      this.writer.setPosition(this.writer.idx() + 1);
   }

   public void writeFloat8(double value) {
      this.writer.writeFloat8(value);
      this.writer.setPosition(this.writer.idx() + 1);
   }

   public void write(Float8Holder holder) {
      this.writer.writeFloat8(holder.value);
      this.writer.setPosition(this.writer.idx() + 1);
   }

   public void writeDateMilli(long value) {
      this.writer.writeDateMilli(value);
      this.writer.setPosition(this.writer.idx() + 1);
   }

   public void write(DateMilliHolder holder) {
      this.writer.writeDateMilli(holder.value);
      this.writer.setPosition(this.writer.idx() + 1);
   }

   public void writeDuration(long value) {
      this.writer.writeDuration(value);
      this.writer.setPosition(this.writer.idx() + 1);
   }

   public void write(DurationHolder holder) {
      this.writer.write(holder);
      this.writer.setPosition(this.writer.idx() + 1);
   }

   public void writeTimeStampSec(long value) {
      this.writer.writeTimeStampSec(value);
      this.writer.setPosition(this.writer.idx() + 1);
   }

   public void write(TimeStampSecHolder holder) {
      this.writer.writeTimeStampSec(holder.value);
      this.writer.setPosition(this.writer.idx() + 1);
   }

   public void writeTimeStampMilli(long value) {
      this.writer.writeTimeStampMilli(value);
      this.writer.setPosition(this.writer.idx() + 1);
   }

   public void write(TimeStampMilliHolder holder) {
      this.writer.writeTimeStampMilli(holder.value);
      this.writer.setPosition(this.writer.idx() + 1);
   }

   public void writeTimeStampMicro(long value) {
      this.writer.writeTimeStampMicro(value);
      this.writer.setPosition(this.writer.idx() + 1);
   }

   public void write(TimeStampMicroHolder holder) {
      this.writer.writeTimeStampMicro(holder.value);
      this.writer.setPosition(this.writer.idx() + 1);
   }

   public void writeTimeStampNano(long value) {
      this.writer.writeTimeStampNano(value);
      this.writer.setPosition(this.writer.idx() + 1);
   }

   public void write(TimeStampNanoHolder holder) {
      this.writer.writeTimeStampNano(holder.value);
      this.writer.setPosition(this.writer.idx() + 1);
   }

   public void writeTimeStampSecTZ(long value) {
      this.writer.writeTimeStampSecTZ(value);
      this.writer.setPosition(this.writer.idx() + 1);
   }

   public void write(TimeStampSecTZHolder holder) {
      this.writer.write(holder);
      this.writer.setPosition(this.writer.idx() + 1);
   }

   public void writeTimeStampMilliTZ(long value) {
      this.writer.writeTimeStampMilliTZ(value);
      this.writer.setPosition(this.writer.idx() + 1);
   }

   public void write(TimeStampMilliTZHolder holder) {
      this.writer.write(holder);
      this.writer.setPosition(this.writer.idx() + 1);
   }

   public void writeTimeStampMicroTZ(long value) {
      this.writer.writeTimeStampMicroTZ(value);
      this.writer.setPosition(this.writer.idx() + 1);
   }

   public void write(TimeStampMicroTZHolder holder) {
      this.writer.write(holder);
      this.writer.setPosition(this.writer.idx() + 1);
   }

   public void writeTimeStampNanoTZ(long value) {
      this.writer.writeTimeStampNanoTZ(value);
      this.writer.setPosition(this.writer.idx() + 1);
   }

   public void write(TimeStampNanoTZHolder holder) {
      this.writer.write(holder);
      this.writer.setPosition(this.writer.idx() + 1);
   }

   public void writeTimeMicro(long value) {
      this.writer.writeTimeMicro(value);
      this.writer.setPosition(this.writer.idx() + 1);
   }

   public void write(TimeMicroHolder holder) {
      this.writer.writeTimeMicro(holder.value);
      this.writer.setPosition(this.writer.idx() + 1);
   }

   public void writeTimeNano(long value) {
      this.writer.writeTimeNano(value);
      this.writer.setPosition(this.writer.idx() + 1);
   }

   public void write(TimeNanoHolder holder) {
      this.writer.writeTimeNano(holder.value);
      this.writer.setPosition(this.writer.idx() + 1);
   }

   public void writeIntervalDay(int days, int milliseconds) {
      this.writer.writeIntervalDay(days, milliseconds);
      this.writer.setPosition(this.writer.idx() + 1);
   }

   public void write(IntervalDayHolder holder) {
      this.writer.writeIntervalDay(holder.days, holder.milliseconds);
      this.writer.setPosition(this.writer.idx() + 1);
   }

   public void writeIntervalMonthDayNano(int months, int days, long nanoseconds) {
      this.writer.writeIntervalMonthDayNano(months, days, nanoseconds);
      this.writer.setPosition(this.writer.idx() + 1);
   }

   public void write(IntervalMonthDayNanoHolder holder) {
      this.writer.writeIntervalMonthDayNano(holder.months, holder.days, holder.nanoseconds);
      this.writer.setPosition(this.writer.idx() + 1);
   }

   public void writeDecimal256(long start, ArrowBuf buffer) {
      this.writer.writeDecimal256(start, buffer);
      this.writer.setPosition(this.writer.idx() + 1);
   }

   public void writeDecimal256(long start, ArrowBuf buffer, ArrowType arrowType) {
      this.writer.writeDecimal256(start, buffer, arrowType);
      this.writer.setPosition(this.writer.idx() + 1);
   }

   public void write(Decimal256Holder holder) {
      this.writer.write(holder);
      this.writer.setPosition(this.writer.idx() + 1);
   }

   public void writeDecimal256(BigDecimal value) {
      this.writer.writeDecimal256(value);
      this.writer.setPosition(this.writer.idx() + 1);
   }

   public void writeBigEndianBytesToDecimal256(byte[] value, ArrowType arrowType) {
      this.writer.writeBigEndianBytesToDecimal256(value, arrowType);
      this.writer.setPosition(this.writer.idx() + 1);
   }

   public void writeDecimal(long start, ArrowBuf buffer) {
      this.writer.writeDecimal(start, buffer);
      this.writer.setPosition(this.writer.idx() + 1);
   }

   public void writeDecimal(long start, ArrowBuf buffer, ArrowType arrowType) {
      this.writer.writeDecimal(start, buffer, arrowType);
      this.writer.setPosition(this.writer.idx() + 1);
   }

   public void write(DecimalHolder holder) {
      this.writer.write(holder);
      this.writer.setPosition(this.writer.idx() + 1);
   }

   public void writeDecimal(BigDecimal value) {
      this.writer.writeDecimal(value);
      this.writer.setPosition(this.writer.idx() + 1);
   }

   public void writeBigEndianBytesToDecimal(byte[] value, ArrowType arrowType) {
      this.writer.writeBigEndianBytesToDecimal(value, arrowType);
      this.writer.setPosition(this.writer.idx() + 1);
   }

   public void writeFixedSizeBinary(ArrowBuf buffer) {
      this.writer.writeFixedSizeBinary(buffer);
      this.writer.setPosition(this.writer.idx() + 1);
   }

   public void write(FixedSizeBinaryHolder holder) {
      this.writer.write(holder);
      this.writer.setPosition(this.writer.idx() + 1);
   }

   public void writeVarBinary(int start, int end, ArrowBuf buffer) {
      this.writer.writeVarBinary(start, end, buffer);
      this.writer.setPosition(this.writer.idx() + 1);
   }

   public void write(VarBinaryHolder holder) {
      this.writer.writeVarBinary(holder.start, holder.end, holder.buffer);
      this.writer.setPosition(this.writer.idx() + 1);
   }

   public void writeVarBinary(byte[] value) {
      this.writer.writeVarBinary(value);
      this.writer.setPosition(this.writer.idx() + 1);
   }

   public void writeVarBinary(byte[] value, int offset, int length) {
      this.writer.writeVarBinary(value, offset, length);
      this.writer.setPosition(this.writer.idx() + 1);
   }

   public void writeVarBinary(ByteBuffer value) {
      this.writer.writeVarBinary(value);
      this.writer.setPosition(this.writer.idx() + 1);
   }

   public void writeVarBinary(ByteBuffer value, int offset, int length) {
      this.writer.writeVarBinary(value, offset, length);
      this.writer.setPosition(this.writer.idx() + 1);
   }

   public void writeVarChar(int start, int end, ArrowBuf buffer) {
      this.writer.writeVarChar(start, end, buffer);
      this.writer.setPosition(this.writer.idx() + 1);
   }

   public void write(VarCharHolder holder) {
      this.writer.writeVarChar(holder.start, holder.end, holder.buffer);
      this.writer.setPosition(this.writer.idx() + 1);
   }

   public void writeVarChar(Text value) {
      this.writer.writeVarChar(value);
      this.writer.setPosition(this.writer.idx() + 1);
   }

   public void writeVarChar(String value) {
      this.writer.writeVarChar(value);
      this.writer.setPosition(this.writer.idx() + 1);
   }

   public void writeViewVarBinary(int start, int end, ArrowBuf buffer) {
      this.writer.writeViewVarBinary(start, end, buffer);
      this.writer.setPosition(this.writer.idx() + 1);
   }

   public void write(ViewVarBinaryHolder holder) {
      this.writer.writeViewVarBinary(holder.start, holder.end, holder.buffer);
      this.writer.setPosition(this.writer.idx() + 1);
   }

   public void writeViewVarBinary(byte[] value) {
      this.writer.writeViewVarBinary(value);
      this.writer.setPosition(this.writer.idx() + 1);
   }

   public void writeViewVarBinary(byte[] value, int offset, int length) {
      this.writer.writeViewVarBinary(value, offset, length);
      this.writer.setPosition(this.writer.idx() + 1);
   }

   public void writeViewVarBinary(ByteBuffer value) {
      this.writer.writeViewVarBinary(value);
      this.writer.setPosition(this.writer.idx() + 1);
   }

   public void writeViewVarBinary(ByteBuffer value, int offset, int length) {
      this.writer.writeViewVarBinary(value, offset, length);
      this.writer.setPosition(this.writer.idx() + 1);
   }

   public void writeViewVarChar(int start, int end, ArrowBuf buffer) {
      this.writer.writeViewVarChar(start, end, buffer);
      this.writer.setPosition(this.writer.idx() + 1);
   }

   public void write(ViewVarCharHolder holder) {
      this.writer.writeViewVarChar(holder.start, holder.end, holder.buffer);
      this.writer.setPosition(this.writer.idx() + 1);
   }

   public void writeViewVarChar(Text value) {
      this.writer.writeViewVarChar(value);
      this.writer.setPosition(this.writer.idx() + 1);
   }

   public void writeViewVarChar(String value) {
      this.writer.writeViewVarChar(value);
      this.writer.setPosition(this.writer.idx() + 1);
   }

   public void writeLargeVarChar(long start, long end, ArrowBuf buffer) {
      this.writer.writeLargeVarChar(start, end, buffer);
      this.writer.setPosition(this.writer.idx() + 1);
   }

   public void write(LargeVarCharHolder holder) {
      this.writer.writeLargeVarChar(holder.start, holder.end, holder.buffer);
      this.writer.setPosition(this.writer.idx() + 1);
   }

   public void writeLargeVarChar(Text value) {
      this.writer.writeLargeVarChar(value);
      this.writer.setPosition(this.writer.idx() + 1);
   }

   public void writeLargeVarChar(String value) {
      this.writer.writeLargeVarChar(value);
      this.writer.setPosition(this.writer.idx() + 1);
   }

   public void writeLargeVarBinary(long start, long end, ArrowBuf buffer) {
      this.writer.writeLargeVarBinary(start, end, buffer);
      this.writer.setPosition(this.writer.idx() + 1);
   }

   public void write(LargeVarBinaryHolder holder) {
      this.writer.writeLargeVarBinary(holder.start, holder.end, holder.buffer);
      this.writer.setPosition(this.writer.idx() + 1);
   }

   public void writeLargeVarBinary(byte[] value) {
      this.writer.writeLargeVarBinary(value);
      this.writer.setPosition(this.writer.idx() + 1);
   }

   public void writeLargeVarBinary(byte[] value, int offset, int length) {
      this.writer.writeLargeVarBinary(value, offset, length);
      this.writer.setPosition(this.writer.idx() + 1);
   }

   public void writeLargeVarBinary(ByteBuffer value) {
      this.writer.writeLargeVarBinary(value);
      this.writer.setPosition(this.writer.idx() + 1);
   }

   public void writeLargeVarBinary(ByteBuffer value, int offset, int length) {
      this.writer.writeLargeVarBinary(value, offset, length);
      this.writer.setPosition(this.writer.idx() + 1);
   }

   public void writeBit(int value) {
      this.writer.writeBit(value);
      this.writer.setPosition(this.writer.idx() + 1);
   }

   public void write(BitHolder holder) {
      this.writer.writeBit(holder.value);
      this.writer.setPosition(this.writer.idx() + 1);
   }
}
