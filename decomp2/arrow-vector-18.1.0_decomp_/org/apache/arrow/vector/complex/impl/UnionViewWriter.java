package org.apache.arrow.vector.complex.impl;

import java.math.BigDecimal;
import java.nio.ByteBuffer;
import org.apache.arrow.memory.ArrowBuf;
import org.apache.arrow.vector.complex.UnionVector;
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
import org.apache.arrow.vector.types.Types;
import org.apache.arrow.vector.types.pojo.ArrowType;
import org.apache.arrow.vector.util.Text;

public class UnionViewWriter extends UnionWriter {
   private TinyIntWriter tinyIntWriter;
   private UInt1Writer uInt1Writer;
   private UInt2Writer uInt2Writer;
   private SmallIntWriter smallIntWriter;
   private Float2Writer float2Writer;
   private IntWriter intWriter;
   private UInt4Writer uInt4Writer;
   private Float4Writer float4Writer;
   private DateDayWriter dateDayWriter;
   private IntervalYearWriter intervalYearWriter;
   private TimeSecWriter timeSecWriter;
   private TimeMilliWriter timeMilliWriter;
   private BigIntWriter bigIntWriter;
   private UInt8Writer uInt8Writer;
   private Float8Writer float8Writer;
   private DateMilliWriter dateMilliWriter;
   private DurationWriter durationWriter;
   private TimeStampSecWriter timeStampSecWriter;
   private TimeStampMilliWriter timeStampMilliWriter;
   private TimeStampMicroWriter timeStampMicroWriter;
   private TimeStampNanoWriter timeStampNanoWriter;
   private TimeStampSecTZWriter timeStampSecTZWriter;
   private TimeStampMilliTZWriter timeStampMilliTZWriter;
   private TimeStampMicroTZWriter timeStampMicroTZWriter;
   private TimeStampNanoTZWriter timeStampNanoTZWriter;
   private TimeMicroWriter timeMicroWriter;
   private TimeNanoWriter timeNanoWriter;
   private IntervalDayWriter intervalDayWriter;
   private IntervalMonthDayNanoWriter intervalMonthDayNanoWriter;
   private Decimal256Writer decimal256Writer;
   private DecimalWriter decimalWriter;
   private FixedSizeBinaryWriter fixedSizeBinaryWriter;
   private VarBinaryWriter varBinaryWriter;
   private VarCharWriter varCharWriter;
   private ViewVarBinaryWriter viewVarBinaryWriter;
   private ViewVarCharWriter viewVarCharWriter;
   private LargeVarCharWriter largeVarCharWriter;
   private LargeVarBinaryWriter largeVarBinaryWriter;
   private BitWriter bitWriter;

   public UnionViewWriter(UnionVector vector) {
      this(vector, NullableStructWriterFactory.getNullableStructWriterFactoryInstance());
   }

   public UnionViewWriter(UnionVector vector, NullableStructWriterFactory nullableStructWriterFactory) {
      super(vector, nullableStructWriterFactory);
   }

   public BaseWriter.StructWriter struct() {
      this.data.setType(this.idx(), Types.MinorType.LISTVIEW);
      this.getListWriter().setPosition(this.idx());
      return this.getListWriter().struct();
   }

   private TinyIntWriter getTinyIntWriter() {
      if (this.tinyIntWriter == null) {
         this.tinyIntWriter = new TinyIntWriterImpl(this.data.getTinyIntVector());
         this.tinyIntWriter.setPosition(this.idx());
         this.writers.add(this.tinyIntWriter);
      }

      return this.tinyIntWriter;
   }

   public TinyIntWriter asTinyInt() {
      this.data.setType(this.idx(), Types.MinorType.TINYINT);
      return this.getTinyIntWriter();
   }

   public void write(TinyIntHolder holder) {
      this.data.setType(this.idx(), Types.MinorType.TINYINT);
      this.getTinyIntWriter().setPosition(this.idx());
      this.getTinyIntWriter().writeTinyInt(holder.value);
   }

   public void writeTinyInt(byte value) {
      this.data.setType(this.idx(), Types.MinorType.TINYINT);
      this.getTinyIntWriter().setPosition(this.idx());
      this.getTinyIntWriter().writeTinyInt(value);
   }

   private UInt1Writer getUInt1Writer() {
      if (this.uInt1Writer == null) {
         this.uInt1Writer = new UInt1WriterImpl(this.data.getUInt1Vector());
         this.uInt1Writer.setPosition(this.idx());
         this.writers.add(this.uInt1Writer);
      }

      return this.uInt1Writer;
   }

   public UInt1Writer asUInt1() {
      this.data.setType(this.idx(), Types.MinorType.UINT1);
      return this.getUInt1Writer();
   }

   public void write(UInt1Holder holder) {
      this.data.setType(this.idx(), Types.MinorType.UINT1);
      this.getUInt1Writer().setPosition(this.idx());
      this.getUInt1Writer().writeUInt1(holder.value);
   }

   public void writeUInt1(byte value) {
      this.data.setType(this.idx(), Types.MinorType.UINT1);
      this.getUInt1Writer().setPosition(this.idx());
      this.getUInt1Writer().writeUInt1(value);
   }

   private UInt2Writer getUInt2Writer() {
      if (this.uInt2Writer == null) {
         this.uInt2Writer = new UInt2WriterImpl(this.data.getUInt2Vector());
         this.uInt2Writer.setPosition(this.idx());
         this.writers.add(this.uInt2Writer);
      }

      return this.uInt2Writer;
   }

   public UInt2Writer asUInt2() {
      this.data.setType(this.idx(), Types.MinorType.UINT2);
      return this.getUInt2Writer();
   }

   public void write(UInt2Holder holder) {
      this.data.setType(this.idx(), Types.MinorType.UINT2);
      this.getUInt2Writer().setPosition(this.idx());
      this.getUInt2Writer().writeUInt2(holder.value);
   }

   public void writeUInt2(char value) {
      this.data.setType(this.idx(), Types.MinorType.UINT2);
      this.getUInt2Writer().setPosition(this.idx());
      this.getUInt2Writer().writeUInt2(value);
   }

   private SmallIntWriter getSmallIntWriter() {
      if (this.smallIntWriter == null) {
         this.smallIntWriter = new SmallIntWriterImpl(this.data.getSmallIntVector());
         this.smallIntWriter.setPosition(this.idx());
         this.writers.add(this.smallIntWriter);
      }

      return this.smallIntWriter;
   }

   public SmallIntWriter asSmallInt() {
      this.data.setType(this.idx(), Types.MinorType.SMALLINT);
      return this.getSmallIntWriter();
   }

   public void write(SmallIntHolder holder) {
      this.data.setType(this.idx(), Types.MinorType.SMALLINT);
      this.getSmallIntWriter().setPosition(this.idx());
      this.getSmallIntWriter().writeSmallInt(holder.value);
   }

   public void writeSmallInt(short value) {
      this.data.setType(this.idx(), Types.MinorType.SMALLINT);
      this.getSmallIntWriter().setPosition(this.idx());
      this.getSmallIntWriter().writeSmallInt(value);
   }

   private Float2Writer getFloat2Writer() {
      if (this.float2Writer == null) {
         this.float2Writer = new Float2WriterImpl(this.data.getFloat2Vector());
         this.float2Writer.setPosition(this.idx());
         this.writers.add(this.float2Writer);
      }

      return this.float2Writer;
   }

   public Float2Writer asFloat2() {
      this.data.setType(this.idx(), Types.MinorType.FLOAT2);
      return this.getFloat2Writer();
   }

   public void write(Float2Holder holder) {
      this.data.setType(this.idx(), Types.MinorType.FLOAT2);
      this.getFloat2Writer().setPosition(this.idx());
      this.getFloat2Writer().writeFloat2(holder.value);
   }

   public void writeFloat2(short value) {
      this.data.setType(this.idx(), Types.MinorType.FLOAT2);
      this.getFloat2Writer().setPosition(this.idx());
      this.getFloat2Writer().writeFloat2(value);
   }

   private IntWriter getIntWriter() {
      if (this.intWriter == null) {
         this.intWriter = new IntWriterImpl(this.data.getIntVector());
         this.intWriter.setPosition(this.idx());
         this.writers.add(this.intWriter);
      }

      return this.intWriter;
   }

   public IntWriter asInt() {
      this.data.setType(this.idx(), Types.MinorType.INT);
      return this.getIntWriter();
   }

   public void write(IntHolder holder) {
      this.data.setType(this.idx(), Types.MinorType.INT);
      this.getIntWriter().setPosition(this.idx());
      this.getIntWriter().writeInt(holder.value);
   }

   public void writeInt(int value) {
      this.data.setType(this.idx(), Types.MinorType.INT);
      this.getIntWriter().setPosition(this.idx());
      this.getIntWriter().writeInt(value);
   }

   private UInt4Writer getUInt4Writer() {
      if (this.uInt4Writer == null) {
         this.uInt4Writer = new UInt4WriterImpl(this.data.getUInt4Vector());
         this.uInt4Writer.setPosition(this.idx());
         this.writers.add(this.uInt4Writer);
      }

      return this.uInt4Writer;
   }

   public UInt4Writer asUInt4() {
      this.data.setType(this.idx(), Types.MinorType.UINT4);
      return this.getUInt4Writer();
   }

   public void write(UInt4Holder holder) {
      this.data.setType(this.idx(), Types.MinorType.UINT4);
      this.getUInt4Writer().setPosition(this.idx());
      this.getUInt4Writer().writeUInt4(holder.value);
   }

   public void writeUInt4(int value) {
      this.data.setType(this.idx(), Types.MinorType.UINT4);
      this.getUInt4Writer().setPosition(this.idx());
      this.getUInt4Writer().writeUInt4(value);
   }

   private Float4Writer getFloat4Writer() {
      if (this.float4Writer == null) {
         this.float4Writer = new Float4WriterImpl(this.data.getFloat4Vector());
         this.float4Writer.setPosition(this.idx());
         this.writers.add(this.float4Writer);
      }

      return this.float4Writer;
   }

   public Float4Writer asFloat4() {
      this.data.setType(this.idx(), Types.MinorType.FLOAT4);
      return this.getFloat4Writer();
   }

   public void write(Float4Holder holder) {
      this.data.setType(this.idx(), Types.MinorType.FLOAT4);
      this.getFloat4Writer().setPosition(this.idx());
      this.getFloat4Writer().writeFloat4(holder.value);
   }

   public void writeFloat4(float value) {
      this.data.setType(this.idx(), Types.MinorType.FLOAT4);
      this.getFloat4Writer().setPosition(this.idx());
      this.getFloat4Writer().writeFloat4(value);
   }

   private DateDayWriter getDateDayWriter() {
      if (this.dateDayWriter == null) {
         this.dateDayWriter = new DateDayWriterImpl(this.data.getDateDayVector());
         this.dateDayWriter.setPosition(this.idx());
         this.writers.add(this.dateDayWriter);
      }

      return this.dateDayWriter;
   }

   public DateDayWriter asDateDay() {
      this.data.setType(this.idx(), Types.MinorType.DATEDAY);
      return this.getDateDayWriter();
   }

   public void write(DateDayHolder holder) {
      this.data.setType(this.idx(), Types.MinorType.DATEDAY);
      this.getDateDayWriter().setPosition(this.idx());
      this.getDateDayWriter().writeDateDay(holder.value);
   }

   public void writeDateDay(int value) {
      this.data.setType(this.idx(), Types.MinorType.DATEDAY);
      this.getDateDayWriter().setPosition(this.idx());
      this.getDateDayWriter().writeDateDay(value);
   }

   private IntervalYearWriter getIntervalYearWriter() {
      if (this.intervalYearWriter == null) {
         this.intervalYearWriter = new IntervalYearWriterImpl(this.data.getIntervalYearVector());
         this.intervalYearWriter.setPosition(this.idx());
         this.writers.add(this.intervalYearWriter);
      }

      return this.intervalYearWriter;
   }

   public IntervalYearWriter asIntervalYear() {
      this.data.setType(this.idx(), Types.MinorType.INTERVALYEAR);
      return this.getIntervalYearWriter();
   }

   public void write(IntervalYearHolder holder) {
      this.data.setType(this.idx(), Types.MinorType.INTERVALYEAR);
      this.getIntervalYearWriter().setPosition(this.idx());
      this.getIntervalYearWriter().writeIntervalYear(holder.value);
   }

   public void writeIntervalYear(int value) {
      this.data.setType(this.idx(), Types.MinorType.INTERVALYEAR);
      this.getIntervalYearWriter().setPosition(this.idx());
      this.getIntervalYearWriter().writeIntervalYear(value);
   }

   private TimeSecWriter getTimeSecWriter() {
      if (this.timeSecWriter == null) {
         this.timeSecWriter = new TimeSecWriterImpl(this.data.getTimeSecVector());
         this.timeSecWriter.setPosition(this.idx());
         this.writers.add(this.timeSecWriter);
      }

      return this.timeSecWriter;
   }

   public TimeSecWriter asTimeSec() {
      this.data.setType(this.idx(), Types.MinorType.TIMESEC);
      return this.getTimeSecWriter();
   }

   public void write(TimeSecHolder holder) {
      this.data.setType(this.idx(), Types.MinorType.TIMESEC);
      this.getTimeSecWriter().setPosition(this.idx());
      this.getTimeSecWriter().writeTimeSec(holder.value);
   }

   public void writeTimeSec(int value) {
      this.data.setType(this.idx(), Types.MinorType.TIMESEC);
      this.getTimeSecWriter().setPosition(this.idx());
      this.getTimeSecWriter().writeTimeSec(value);
   }

   private TimeMilliWriter getTimeMilliWriter() {
      if (this.timeMilliWriter == null) {
         this.timeMilliWriter = new TimeMilliWriterImpl(this.data.getTimeMilliVector());
         this.timeMilliWriter.setPosition(this.idx());
         this.writers.add(this.timeMilliWriter);
      }

      return this.timeMilliWriter;
   }

   public TimeMilliWriter asTimeMilli() {
      this.data.setType(this.idx(), Types.MinorType.TIMEMILLI);
      return this.getTimeMilliWriter();
   }

   public void write(TimeMilliHolder holder) {
      this.data.setType(this.idx(), Types.MinorType.TIMEMILLI);
      this.getTimeMilliWriter().setPosition(this.idx());
      this.getTimeMilliWriter().writeTimeMilli(holder.value);
   }

   public void writeTimeMilli(int value) {
      this.data.setType(this.idx(), Types.MinorType.TIMEMILLI);
      this.getTimeMilliWriter().setPosition(this.idx());
      this.getTimeMilliWriter().writeTimeMilli(value);
   }

   private BigIntWriter getBigIntWriter() {
      if (this.bigIntWriter == null) {
         this.bigIntWriter = new BigIntWriterImpl(this.data.getBigIntVector());
         this.bigIntWriter.setPosition(this.idx());
         this.writers.add(this.bigIntWriter);
      }

      return this.bigIntWriter;
   }

   public BigIntWriter asBigInt() {
      this.data.setType(this.idx(), Types.MinorType.BIGINT);
      return this.getBigIntWriter();
   }

   public void write(BigIntHolder holder) {
      this.data.setType(this.idx(), Types.MinorType.BIGINT);
      this.getBigIntWriter().setPosition(this.idx());
      this.getBigIntWriter().writeBigInt(holder.value);
   }

   public void writeBigInt(long value) {
      this.data.setType(this.idx(), Types.MinorType.BIGINT);
      this.getBigIntWriter().setPosition(this.idx());
      this.getBigIntWriter().writeBigInt(value);
   }

   private UInt8Writer getUInt8Writer() {
      if (this.uInt8Writer == null) {
         this.uInt8Writer = new UInt8WriterImpl(this.data.getUInt8Vector());
         this.uInt8Writer.setPosition(this.idx());
         this.writers.add(this.uInt8Writer);
      }

      return this.uInt8Writer;
   }

   public UInt8Writer asUInt8() {
      this.data.setType(this.idx(), Types.MinorType.UINT8);
      return this.getUInt8Writer();
   }

   public void write(UInt8Holder holder) {
      this.data.setType(this.idx(), Types.MinorType.UINT8);
      this.getUInt8Writer().setPosition(this.idx());
      this.getUInt8Writer().writeUInt8(holder.value);
   }

   public void writeUInt8(long value) {
      this.data.setType(this.idx(), Types.MinorType.UINT8);
      this.getUInt8Writer().setPosition(this.idx());
      this.getUInt8Writer().writeUInt8(value);
   }

   private Float8Writer getFloat8Writer() {
      if (this.float8Writer == null) {
         this.float8Writer = new Float8WriterImpl(this.data.getFloat8Vector());
         this.float8Writer.setPosition(this.idx());
         this.writers.add(this.float8Writer);
      }

      return this.float8Writer;
   }

   public Float8Writer asFloat8() {
      this.data.setType(this.idx(), Types.MinorType.FLOAT8);
      return this.getFloat8Writer();
   }

   public void write(Float8Holder holder) {
      this.data.setType(this.idx(), Types.MinorType.FLOAT8);
      this.getFloat8Writer().setPosition(this.idx());
      this.getFloat8Writer().writeFloat8(holder.value);
   }

   public void writeFloat8(double value) {
      this.data.setType(this.idx(), Types.MinorType.FLOAT8);
      this.getFloat8Writer().setPosition(this.idx());
      this.getFloat8Writer().writeFloat8(value);
   }

   private DateMilliWriter getDateMilliWriter() {
      if (this.dateMilliWriter == null) {
         this.dateMilliWriter = new DateMilliWriterImpl(this.data.getDateMilliVector());
         this.dateMilliWriter.setPosition(this.idx());
         this.writers.add(this.dateMilliWriter);
      }

      return this.dateMilliWriter;
   }

   public DateMilliWriter asDateMilli() {
      this.data.setType(this.idx(), Types.MinorType.DATEMILLI);
      return this.getDateMilliWriter();
   }

   public void write(DateMilliHolder holder) {
      this.data.setType(this.idx(), Types.MinorType.DATEMILLI);
      this.getDateMilliWriter().setPosition(this.idx());
      this.getDateMilliWriter().writeDateMilli(holder.value);
   }

   public void writeDateMilli(long value) {
      this.data.setType(this.idx(), Types.MinorType.DATEMILLI);
      this.getDateMilliWriter().setPosition(this.idx());
      this.getDateMilliWriter().writeDateMilli(value);
   }

   private DurationWriter getDurationWriter(ArrowType arrowType) {
      if (this.durationWriter == null) {
         this.durationWriter = new DurationWriterImpl(this.data.getDurationVector(arrowType));
         this.durationWriter.setPosition(this.idx());
         this.writers.add(this.durationWriter);
      }

      return this.durationWriter;
   }

   public DurationWriter asDuration(ArrowType arrowType) {
      this.data.setType(this.idx(), Types.MinorType.DURATION);
      return this.getDurationWriter(arrowType);
   }

   public void write(DurationHolder holder) {
      this.data.setType(this.idx(), Types.MinorType.DURATION);
      ArrowType arrowType = new ArrowType.Duration(holder.unit);
      this.getDurationWriter(arrowType).setPosition(this.idx());
      this.getDurationWriter(arrowType).write(holder);
   }

   public void writeDuration(long value) {
      this.data.setType(this.idx(), Types.MinorType.DURATION);
      ArrowType arrowType = Types.MinorType.DURATION.getType();
      this.getDurationWriter(arrowType).setPosition(this.idx());
      this.getDurationWriter(arrowType).writeDuration(value);
   }

   private TimeStampSecWriter getTimeStampSecWriter() {
      if (this.timeStampSecWriter == null) {
         this.timeStampSecWriter = new TimeStampSecWriterImpl(this.data.getTimeStampSecVector());
         this.timeStampSecWriter.setPosition(this.idx());
         this.writers.add(this.timeStampSecWriter);
      }

      return this.timeStampSecWriter;
   }

   public TimeStampSecWriter asTimeStampSec() {
      this.data.setType(this.idx(), Types.MinorType.TIMESTAMPSEC);
      return this.getTimeStampSecWriter();
   }

   public void write(TimeStampSecHolder holder) {
      this.data.setType(this.idx(), Types.MinorType.TIMESTAMPSEC);
      this.getTimeStampSecWriter().setPosition(this.idx());
      this.getTimeStampSecWriter().writeTimeStampSec(holder.value);
   }

   public void writeTimeStampSec(long value) {
      this.data.setType(this.idx(), Types.MinorType.TIMESTAMPSEC);
      this.getTimeStampSecWriter().setPosition(this.idx());
      this.getTimeStampSecWriter().writeTimeStampSec(value);
   }

   private TimeStampMilliWriter getTimeStampMilliWriter() {
      if (this.timeStampMilliWriter == null) {
         this.timeStampMilliWriter = new TimeStampMilliWriterImpl(this.data.getTimeStampMilliVector());
         this.timeStampMilliWriter.setPosition(this.idx());
         this.writers.add(this.timeStampMilliWriter);
      }

      return this.timeStampMilliWriter;
   }

   public TimeStampMilliWriter asTimeStampMilli() {
      this.data.setType(this.idx(), Types.MinorType.TIMESTAMPMILLI);
      return this.getTimeStampMilliWriter();
   }

   public void write(TimeStampMilliHolder holder) {
      this.data.setType(this.idx(), Types.MinorType.TIMESTAMPMILLI);
      this.getTimeStampMilliWriter().setPosition(this.idx());
      this.getTimeStampMilliWriter().writeTimeStampMilli(holder.value);
   }

   public void writeTimeStampMilli(long value) {
      this.data.setType(this.idx(), Types.MinorType.TIMESTAMPMILLI);
      this.getTimeStampMilliWriter().setPosition(this.idx());
      this.getTimeStampMilliWriter().writeTimeStampMilli(value);
   }

   private TimeStampMicroWriter getTimeStampMicroWriter() {
      if (this.timeStampMicroWriter == null) {
         this.timeStampMicroWriter = new TimeStampMicroWriterImpl(this.data.getTimeStampMicroVector());
         this.timeStampMicroWriter.setPosition(this.idx());
         this.writers.add(this.timeStampMicroWriter);
      }

      return this.timeStampMicroWriter;
   }

   public TimeStampMicroWriter asTimeStampMicro() {
      this.data.setType(this.idx(), Types.MinorType.TIMESTAMPMICRO);
      return this.getTimeStampMicroWriter();
   }

   public void write(TimeStampMicroHolder holder) {
      this.data.setType(this.idx(), Types.MinorType.TIMESTAMPMICRO);
      this.getTimeStampMicroWriter().setPosition(this.idx());
      this.getTimeStampMicroWriter().writeTimeStampMicro(holder.value);
   }

   public void writeTimeStampMicro(long value) {
      this.data.setType(this.idx(), Types.MinorType.TIMESTAMPMICRO);
      this.getTimeStampMicroWriter().setPosition(this.idx());
      this.getTimeStampMicroWriter().writeTimeStampMicro(value);
   }

   private TimeStampNanoWriter getTimeStampNanoWriter() {
      if (this.timeStampNanoWriter == null) {
         this.timeStampNanoWriter = new TimeStampNanoWriterImpl(this.data.getTimeStampNanoVector());
         this.timeStampNanoWriter.setPosition(this.idx());
         this.writers.add(this.timeStampNanoWriter);
      }

      return this.timeStampNanoWriter;
   }

   public TimeStampNanoWriter asTimeStampNano() {
      this.data.setType(this.idx(), Types.MinorType.TIMESTAMPNANO);
      return this.getTimeStampNanoWriter();
   }

   public void write(TimeStampNanoHolder holder) {
      this.data.setType(this.idx(), Types.MinorType.TIMESTAMPNANO);
      this.getTimeStampNanoWriter().setPosition(this.idx());
      this.getTimeStampNanoWriter().writeTimeStampNano(holder.value);
   }

   public void writeTimeStampNano(long value) {
      this.data.setType(this.idx(), Types.MinorType.TIMESTAMPNANO);
      this.getTimeStampNanoWriter().setPosition(this.idx());
      this.getTimeStampNanoWriter().writeTimeStampNano(value);
   }

   private TimeStampSecTZWriter getTimeStampSecTZWriter(ArrowType arrowType) {
      if (this.timeStampSecTZWriter == null) {
         this.timeStampSecTZWriter = new TimeStampSecTZWriterImpl(this.data.getTimeStampSecTZVector(arrowType));
         this.timeStampSecTZWriter.setPosition(this.idx());
         this.writers.add(this.timeStampSecTZWriter);
      }

      return this.timeStampSecTZWriter;
   }

   public TimeStampSecTZWriter asTimeStampSecTZ(ArrowType arrowType) {
      this.data.setType(this.idx(), Types.MinorType.TIMESTAMPSECTZ);
      return this.getTimeStampSecTZWriter(arrowType);
   }

   public void write(TimeStampSecTZHolder holder) {
      this.data.setType(this.idx(), Types.MinorType.TIMESTAMPSECTZ);
      ArrowType.Timestamp arrowTypeWithoutTz = (ArrowType.Timestamp)Types.MinorType.TIMESTAMPSEC.getType();
      ArrowType arrowType = new ArrowType.Timestamp(arrowTypeWithoutTz.getUnit(), holder.timezone);
      this.getTimeStampSecTZWriter(arrowType).setPosition(this.idx());
      this.getTimeStampSecTZWriter(arrowType).write(holder);
   }

   public void writeTimeStampSecTZ(long value) {
      this.data.setType(this.idx(), Types.MinorType.TIMESTAMPSECTZ);
      ArrowType.Timestamp arrowTypeWithoutTz = (ArrowType.Timestamp)Types.MinorType.TIMESTAMPSEC.getType();
      ArrowType arrowType = new ArrowType.Timestamp(arrowTypeWithoutTz.getUnit(), "UTC");
      this.getTimeStampSecTZWriter(arrowType).setPosition(this.idx());
      this.getTimeStampSecTZWriter(arrowType).writeTimeStampSecTZ(value);
   }

   private TimeStampMilliTZWriter getTimeStampMilliTZWriter(ArrowType arrowType) {
      if (this.timeStampMilliTZWriter == null) {
         this.timeStampMilliTZWriter = new TimeStampMilliTZWriterImpl(this.data.getTimeStampMilliTZVector(arrowType));
         this.timeStampMilliTZWriter.setPosition(this.idx());
         this.writers.add(this.timeStampMilliTZWriter);
      }

      return this.timeStampMilliTZWriter;
   }

   public TimeStampMilliTZWriter asTimeStampMilliTZ(ArrowType arrowType) {
      this.data.setType(this.idx(), Types.MinorType.TIMESTAMPMILLITZ);
      return this.getTimeStampMilliTZWriter(arrowType);
   }

   public void write(TimeStampMilliTZHolder holder) {
      this.data.setType(this.idx(), Types.MinorType.TIMESTAMPMILLITZ);
      ArrowType.Timestamp arrowTypeWithoutTz = (ArrowType.Timestamp)Types.MinorType.TIMESTAMPMILLI.getType();
      ArrowType arrowType = new ArrowType.Timestamp(arrowTypeWithoutTz.getUnit(), holder.timezone);
      this.getTimeStampMilliTZWriter(arrowType).setPosition(this.idx());
      this.getTimeStampMilliTZWriter(arrowType).write(holder);
   }

   public void writeTimeStampMilliTZ(long value) {
      this.data.setType(this.idx(), Types.MinorType.TIMESTAMPMILLITZ);
      ArrowType.Timestamp arrowTypeWithoutTz = (ArrowType.Timestamp)Types.MinorType.TIMESTAMPMILLI.getType();
      ArrowType arrowType = new ArrowType.Timestamp(arrowTypeWithoutTz.getUnit(), "UTC");
      this.getTimeStampMilliTZWriter(arrowType).setPosition(this.idx());
      this.getTimeStampMilliTZWriter(arrowType).writeTimeStampMilliTZ(value);
   }

   private TimeStampMicroTZWriter getTimeStampMicroTZWriter(ArrowType arrowType) {
      if (this.timeStampMicroTZWriter == null) {
         this.timeStampMicroTZWriter = new TimeStampMicroTZWriterImpl(this.data.getTimeStampMicroTZVector(arrowType));
         this.timeStampMicroTZWriter.setPosition(this.idx());
         this.writers.add(this.timeStampMicroTZWriter);
      }

      return this.timeStampMicroTZWriter;
   }

   public TimeStampMicroTZWriter asTimeStampMicroTZ(ArrowType arrowType) {
      this.data.setType(this.idx(), Types.MinorType.TIMESTAMPMICROTZ);
      return this.getTimeStampMicroTZWriter(arrowType);
   }

   public void write(TimeStampMicroTZHolder holder) {
      this.data.setType(this.idx(), Types.MinorType.TIMESTAMPMICROTZ);
      ArrowType.Timestamp arrowTypeWithoutTz = (ArrowType.Timestamp)Types.MinorType.TIMESTAMPMICRO.getType();
      ArrowType arrowType = new ArrowType.Timestamp(arrowTypeWithoutTz.getUnit(), holder.timezone);
      this.getTimeStampMicroTZWriter(arrowType).setPosition(this.idx());
      this.getTimeStampMicroTZWriter(arrowType).write(holder);
   }

   public void writeTimeStampMicroTZ(long value) {
      this.data.setType(this.idx(), Types.MinorType.TIMESTAMPMICROTZ);
      ArrowType.Timestamp arrowTypeWithoutTz = (ArrowType.Timestamp)Types.MinorType.TIMESTAMPMICRO.getType();
      ArrowType arrowType = new ArrowType.Timestamp(arrowTypeWithoutTz.getUnit(), "UTC");
      this.getTimeStampMicroTZWriter(arrowType).setPosition(this.idx());
      this.getTimeStampMicroTZWriter(arrowType).writeTimeStampMicroTZ(value);
   }

   private TimeStampNanoTZWriter getTimeStampNanoTZWriter(ArrowType arrowType) {
      if (this.timeStampNanoTZWriter == null) {
         this.timeStampNanoTZWriter = new TimeStampNanoTZWriterImpl(this.data.getTimeStampNanoTZVector(arrowType));
         this.timeStampNanoTZWriter.setPosition(this.idx());
         this.writers.add(this.timeStampNanoTZWriter);
      }

      return this.timeStampNanoTZWriter;
   }

   public TimeStampNanoTZWriter asTimeStampNanoTZ(ArrowType arrowType) {
      this.data.setType(this.idx(), Types.MinorType.TIMESTAMPNANOTZ);
      return this.getTimeStampNanoTZWriter(arrowType);
   }

   public void write(TimeStampNanoTZHolder holder) {
      this.data.setType(this.idx(), Types.MinorType.TIMESTAMPNANOTZ);
      ArrowType.Timestamp arrowTypeWithoutTz = (ArrowType.Timestamp)Types.MinorType.TIMESTAMPNANO.getType();
      ArrowType arrowType = new ArrowType.Timestamp(arrowTypeWithoutTz.getUnit(), holder.timezone);
      this.getTimeStampNanoTZWriter(arrowType).setPosition(this.idx());
      this.getTimeStampNanoTZWriter(arrowType).write(holder);
   }

   public void writeTimeStampNanoTZ(long value) {
      this.data.setType(this.idx(), Types.MinorType.TIMESTAMPNANOTZ);
      ArrowType.Timestamp arrowTypeWithoutTz = (ArrowType.Timestamp)Types.MinorType.TIMESTAMPNANO.getType();
      ArrowType arrowType = new ArrowType.Timestamp(arrowTypeWithoutTz.getUnit(), "UTC");
      this.getTimeStampNanoTZWriter(arrowType).setPosition(this.idx());
      this.getTimeStampNanoTZWriter(arrowType).writeTimeStampNanoTZ(value);
   }

   private TimeMicroWriter getTimeMicroWriter() {
      if (this.timeMicroWriter == null) {
         this.timeMicroWriter = new TimeMicroWriterImpl(this.data.getTimeMicroVector());
         this.timeMicroWriter.setPosition(this.idx());
         this.writers.add(this.timeMicroWriter);
      }

      return this.timeMicroWriter;
   }

   public TimeMicroWriter asTimeMicro() {
      this.data.setType(this.idx(), Types.MinorType.TIMEMICRO);
      return this.getTimeMicroWriter();
   }

   public void write(TimeMicroHolder holder) {
      this.data.setType(this.idx(), Types.MinorType.TIMEMICRO);
      this.getTimeMicroWriter().setPosition(this.idx());
      this.getTimeMicroWriter().writeTimeMicro(holder.value);
   }

   public void writeTimeMicro(long value) {
      this.data.setType(this.idx(), Types.MinorType.TIMEMICRO);
      this.getTimeMicroWriter().setPosition(this.idx());
      this.getTimeMicroWriter().writeTimeMicro(value);
   }

   private TimeNanoWriter getTimeNanoWriter() {
      if (this.timeNanoWriter == null) {
         this.timeNanoWriter = new TimeNanoWriterImpl(this.data.getTimeNanoVector());
         this.timeNanoWriter.setPosition(this.idx());
         this.writers.add(this.timeNanoWriter);
      }

      return this.timeNanoWriter;
   }

   public TimeNanoWriter asTimeNano() {
      this.data.setType(this.idx(), Types.MinorType.TIMENANO);
      return this.getTimeNanoWriter();
   }

   public void write(TimeNanoHolder holder) {
      this.data.setType(this.idx(), Types.MinorType.TIMENANO);
      this.getTimeNanoWriter().setPosition(this.idx());
      this.getTimeNanoWriter().writeTimeNano(holder.value);
   }

   public void writeTimeNano(long value) {
      this.data.setType(this.idx(), Types.MinorType.TIMENANO);
      this.getTimeNanoWriter().setPosition(this.idx());
      this.getTimeNanoWriter().writeTimeNano(value);
   }

   private IntervalDayWriter getIntervalDayWriter() {
      if (this.intervalDayWriter == null) {
         this.intervalDayWriter = new IntervalDayWriterImpl(this.data.getIntervalDayVector());
         this.intervalDayWriter.setPosition(this.idx());
         this.writers.add(this.intervalDayWriter);
      }

      return this.intervalDayWriter;
   }

   public IntervalDayWriter asIntervalDay() {
      this.data.setType(this.idx(), Types.MinorType.INTERVALDAY);
      return this.getIntervalDayWriter();
   }

   public void write(IntervalDayHolder holder) {
      this.data.setType(this.idx(), Types.MinorType.INTERVALDAY);
      this.getIntervalDayWriter().setPosition(this.idx());
      this.getIntervalDayWriter().writeIntervalDay(holder.days, holder.milliseconds);
   }

   public void writeIntervalDay(int days, int milliseconds) {
      this.data.setType(this.idx(), Types.MinorType.INTERVALDAY);
      this.getIntervalDayWriter().setPosition(this.idx());
      this.getIntervalDayWriter().writeIntervalDay(days, milliseconds);
   }

   private IntervalMonthDayNanoWriter getIntervalMonthDayNanoWriter() {
      if (this.intervalMonthDayNanoWriter == null) {
         this.intervalMonthDayNanoWriter = new IntervalMonthDayNanoWriterImpl(this.data.getIntervalMonthDayNanoVector());
         this.intervalMonthDayNanoWriter.setPosition(this.idx());
         this.writers.add(this.intervalMonthDayNanoWriter);
      }

      return this.intervalMonthDayNanoWriter;
   }

   public IntervalMonthDayNanoWriter asIntervalMonthDayNano() {
      this.data.setType(this.idx(), Types.MinorType.INTERVALMONTHDAYNANO);
      return this.getIntervalMonthDayNanoWriter();
   }

   public void write(IntervalMonthDayNanoHolder holder) {
      this.data.setType(this.idx(), Types.MinorType.INTERVALMONTHDAYNANO);
      this.getIntervalMonthDayNanoWriter().setPosition(this.idx());
      this.getIntervalMonthDayNanoWriter().writeIntervalMonthDayNano(holder.months, holder.days, holder.nanoseconds);
   }

   public void writeIntervalMonthDayNano(int months, int days, long nanoseconds) {
      this.data.setType(this.idx(), Types.MinorType.INTERVALMONTHDAYNANO);
      this.getIntervalMonthDayNanoWriter().setPosition(this.idx());
      this.getIntervalMonthDayNanoWriter().writeIntervalMonthDayNano(months, days, nanoseconds);
   }

   private Decimal256Writer getDecimal256Writer(ArrowType arrowType) {
      if (this.decimal256Writer == null) {
         this.decimal256Writer = new Decimal256WriterImpl(this.data.getDecimal256Vector(arrowType));
         this.decimal256Writer.setPosition(this.idx());
         this.writers.add(this.decimal256Writer);
      }

      return this.decimal256Writer;
   }

   public Decimal256Writer asDecimal256(ArrowType arrowType) {
      this.data.setType(this.idx(), Types.MinorType.DECIMAL256);
      return this.getDecimal256Writer(arrowType);
   }

   public void write(Decimal256Holder holder) {
      this.data.setType(this.idx(), Types.MinorType.DECIMAL256);
      ArrowType arrowType = new ArrowType.Decimal(holder.precision, holder.scale, 256);
      this.getDecimal256Writer(arrowType).setPosition(this.idx());
      this.getDecimal256Writer(arrowType).writeDecimal256(holder.start, holder.buffer, arrowType);
   }

   public void writeDecimal256(long start, ArrowBuf buffer, ArrowType arrowType) {
      this.data.setType(this.idx(), Types.MinorType.DECIMAL256);
      this.getDecimal256Writer(arrowType).setPosition(this.idx());
      this.getDecimal256Writer(arrowType).writeDecimal256(start, buffer, arrowType);
   }

   public void writeDecimal256(BigDecimal value) {
      this.data.setType(this.idx(), Types.MinorType.DECIMAL256);
      ArrowType arrowType = new ArrowType.Decimal(value.precision(), value.scale(), 256);
      this.getDecimal256Writer(arrowType).setPosition(this.idx());
      this.getDecimal256Writer(arrowType).writeDecimal256(value);
   }

   public void writeBigEndianBytesToDecimal256(byte[] value, ArrowType arrowType) {
      this.data.setType(this.idx(), Types.MinorType.DECIMAL256);
      this.getDecimal256Writer(arrowType).setPosition(this.idx());
      this.getDecimal256Writer(arrowType).writeBigEndianBytesToDecimal256(value, arrowType);
   }

   private DecimalWriter getDecimalWriter(ArrowType arrowType) {
      if (this.decimalWriter == null) {
         this.decimalWriter = new DecimalWriterImpl(this.data.getDecimalVector(arrowType));
         this.decimalWriter.setPosition(this.idx());
         this.writers.add(this.decimalWriter);
      }

      return this.decimalWriter;
   }

   public DecimalWriter asDecimal(ArrowType arrowType) {
      this.data.setType(this.idx(), Types.MinorType.DECIMAL);
      return this.getDecimalWriter(arrowType);
   }

   public void write(DecimalHolder holder) {
      this.data.setType(this.idx(), Types.MinorType.DECIMAL);
      ArrowType arrowType = new ArrowType.Decimal(holder.precision, holder.scale, 128);
      this.getDecimalWriter(arrowType).setPosition(this.idx());
      this.getDecimalWriter(arrowType).writeDecimal(holder.start, holder.buffer, arrowType);
   }

   public void writeDecimal(long start, ArrowBuf buffer, ArrowType arrowType) {
      this.data.setType(this.idx(), Types.MinorType.DECIMAL);
      this.getDecimalWriter(arrowType).setPosition(this.idx());
      this.getDecimalWriter(arrowType).writeDecimal(start, buffer, arrowType);
   }

   public void writeDecimal(BigDecimal value) {
      this.data.setType(this.idx(), Types.MinorType.DECIMAL);
      ArrowType arrowType = new ArrowType.Decimal(value.precision(), value.scale(), 128);
      this.getDecimalWriter(arrowType).setPosition(this.idx());
      this.getDecimalWriter(arrowType).writeDecimal(value);
   }

   public void writeBigEndianBytesToDecimal(byte[] value, ArrowType arrowType) {
      this.data.setType(this.idx(), Types.MinorType.DECIMAL);
      this.getDecimalWriter(arrowType).setPosition(this.idx());
      this.getDecimalWriter(arrowType).writeBigEndianBytesToDecimal(value, arrowType);
   }

   private FixedSizeBinaryWriter getFixedSizeBinaryWriter(ArrowType arrowType) {
      if (this.fixedSizeBinaryWriter == null) {
         this.fixedSizeBinaryWriter = new FixedSizeBinaryWriterImpl(this.data.getFixedSizeBinaryVector(arrowType));
         this.fixedSizeBinaryWriter.setPosition(this.idx());
         this.writers.add(this.fixedSizeBinaryWriter);
      }

      return this.fixedSizeBinaryWriter;
   }

   public FixedSizeBinaryWriter asFixedSizeBinary(ArrowType arrowType) {
      this.data.setType(this.idx(), Types.MinorType.FIXEDSIZEBINARY);
      return this.getFixedSizeBinaryWriter(arrowType);
   }

   public void write(FixedSizeBinaryHolder holder) {
      this.data.setType(this.idx(), Types.MinorType.FIXEDSIZEBINARY);
      ArrowType arrowType = new ArrowType.FixedSizeBinary(holder.byteWidth);
      this.getFixedSizeBinaryWriter(arrowType).setPosition(this.idx());
      this.getFixedSizeBinaryWriter(arrowType).write(holder);
   }

   public void writeFixedSizeBinary(ArrowBuf buffer) {
      this.data.setType(this.idx(), Types.MinorType.FIXEDSIZEBINARY);
      ArrowType arrowType = Types.MinorType.FIXEDSIZEBINARY.getType();
      this.getFixedSizeBinaryWriter(arrowType).setPosition(this.idx());
      this.getFixedSizeBinaryWriter(arrowType).writeFixedSizeBinary(buffer);
   }

   private VarBinaryWriter getVarBinaryWriter() {
      if (this.varBinaryWriter == null) {
         this.varBinaryWriter = new VarBinaryWriterImpl(this.data.getVarBinaryVector());
         this.varBinaryWriter.setPosition(this.idx());
         this.writers.add(this.varBinaryWriter);
      }

      return this.varBinaryWriter;
   }

   public VarBinaryWriter asVarBinary() {
      this.data.setType(this.idx(), Types.MinorType.VARBINARY);
      return this.getVarBinaryWriter();
   }

   public void write(VarBinaryHolder holder) {
      this.data.setType(this.idx(), Types.MinorType.VARBINARY);
      this.getVarBinaryWriter().setPosition(this.idx());
      this.getVarBinaryWriter().writeVarBinary(holder.start, holder.end, holder.buffer);
   }

   public void writeVarBinary(int start, int end, ArrowBuf buffer) {
      this.data.setType(this.idx(), Types.MinorType.VARBINARY);
      this.getVarBinaryWriter().setPosition(this.idx());
      this.getVarBinaryWriter().writeVarBinary(start, end, buffer);
   }

   public void writeVarBinary(byte[] value) {
      this.getVarBinaryWriter().setPosition(this.idx());
      this.getVarBinaryWriter().writeVarBinary(value);
   }

   public void writeVarBinary(byte[] value, int offset, int length) {
      this.getVarBinaryWriter().setPosition(this.idx());
      this.getVarBinaryWriter().writeVarBinary(value, offset, length);
   }

   public void writeVarBinary(ByteBuffer value) {
      this.getVarBinaryWriter().setPosition(this.idx());
      this.getVarBinaryWriter().writeVarBinary(value);
   }

   public void writeVarBinary(ByteBuffer value, int offset, int length) {
      this.getVarBinaryWriter().setPosition(this.idx());
      this.getVarBinaryWriter().writeVarBinary(value, offset, length);
   }

   private VarCharWriter getVarCharWriter() {
      if (this.varCharWriter == null) {
         this.varCharWriter = new VarCharWriterImpl(this.data.getVarCharVector());
         this.varCharWriter.setPosition(this.idx());
         this.writers.add(this.varCharWriter);
      }

      return this.varCharWriter;
   }

   public VarCharWriter asVarChar() {
      this.data.setType(this.idx(), Types.MinorType.VARCHAR);
      return this.getVarCharWriter();
   }

   public void write(VarCharHolder holder) {
      this.data.setType(this.idx(), Types.MinorType.VARCHAR);
      this.getVarCharWriter().setPosition(this.idx());
      this.getVarCharWriter().writeVarChar(holder.start, holder.end, holder.buffer);
   }

   public void writeVarChar(int start, int end, ArrowBuf buffer) {
      this.data.setType(this.idx(), Types.MinorType.VARCHAR);
      this.getVarCharWriter().setPosition(this.idx());
      this.getVarCharWriter().writeVarChar(start, end, buffer);
   }

   public void writeVarChar(Text value) {
      this.getVarCharWriter().setPosition(this.idx());
      this.getVarCharWriter().writeVarChar(value);
   }

   public void writeVarChar(String value) {
      this.getVarCharWriter().setPosition(this.idx());
      this.getVarCharWriter().writeVarChar(value);
   }

   private ViewVarBinaryWriter getViewVarBinaryWriter() {
      if (this.viewVarBinaryWriter == null) {
         this.viewVarBinaryWriter = new ViewVarBinaryWriterImpl(this.data.getViewVarBinaryVector());
         this.viewVarBinaryWriter.setPosition(this.idx());
         this.writers.add(this.viewVarBinaryWriter);
      }

      return this.viewVarBinaryWriter;
   }

   public ViewVarBinaryWriter asViewVarBinary() {
      this.data.setType(this.idx(), Types.MinorType.VIEWVARBINARY);
      return this.getViewVarBinaryWriter();
   }

   public void write(ViewVarBinaryHolder holder) {
      this.data.setType(this.idx(), Types.MinorType.VIEWVARBINARY);
      this.getViewVarBinaryWriter().setPosition(this.idx());
      this.getViewVarBinaryWriter().writeViewVarBinary(holder.start, holder.end, holder.buffer);
   }

   public void writeViewVarBinary(int start, int end, ArrowBuf buffer) {
      this.data.setType(this.idx(), Types.MinorType.VIEWVARBINARY);
      this.getViewVarBinaryWriter().setPosition(this.idx());
      this.getViewVarBinaryWriter().writeViewVarBinary(start, end, buffer);
   }

   public void writeViewVarBinary(byte[] value) {
      this.getViewVarBinaryWriter().setPosition(this.idx());
      this.getViewVarBinaryWriter().writeViewVarBinary(value);
   }

   public void writeViewVarBinary(byte[] value, int offset, int length) {
      this.getViewVarBinaryWriter().setPosition(this.idx());
      this.getViewVarBinaryWriter().writeViewVarBinary(value, offset, length);
   }

   public void writeViewVarBinary(ByteBuffer value) {
      this.getViewVarBinaryWriter().setPosition(this.idx());
      this.getViewVarBinaryWriter().writeViewVarBinary(value);
   }

   public void writeViewVarBinary(ByteBuffer value, int offset, int length) {
      this.getViewVarBinaryWriter().setPosition(this.idx());
      this.getViewVarBinaryWriter().writeViewVarBinary(value, offset, length);
   }

   private ViewVarCharWriter getViewVarCharWriter() {
      if (this.viewVarCharWriter == null) {
         this.viewVarCharWriter = new ViewVarCharWriterImpl(this.data.getViewVarCharVector());
         this.viewVarCharWriter.setPosition(this.idx());
         this.writers.add(this.viewVarCharWriter);
      }

      return this.viewVarCharWriter;
   }

   public ViewVarCharWriter asViewVarChar() {
      this.data.setType(this.idx(), Types.MinorType.VIEWVARCHAR);
      return this.getViewVarCharWriter();
   }

   public void write(ViewVarCharHolder holder) {
      this.data.setType(this.idx(), Types.MinorType.VIEWVARCHAR);
      this.getViewVarCharWriter().setPosition(this.idx());
      this.getViewVarCharWriter().writeViewVarChar(holder.start, holder.end, holder.buffer);
   }

   public void writeViewVarChar(int start, int end, ArrowBuf buffer) {
      this.data.setType(this.idx(), Types.MinorType.VIEWVARCHAR);
      this.getViewVarCharWriter().setPosition(this.idx());
      this.getViewVarCharWriter().writeViewVarChar(start, end, buffer);
   }

   public void writeViewVarChar(Text value) {
      this.getViewVarCharWriter().setPosition(this.idx());
      this.getViewVarCharWriter().writeViewVarChar(value);
   }

   public void writeViewVarChar(String value) {
      this.getViewVarCharWriter().setPosition(this.idx());
      this.getViewVarCharWriter().writeViewVarChar(value);
   }

   private LargeVarCharWriter getLargeVarCharWriter() {
      if (this.largeVarCharWriter == null) {
         this.largeVarCharWriter = new LargeVarCharWriterImpl(this.data.getLargeVarCharVector());
         this.largeVarCharWriter.setPosition(this.idx());
         this.writers.add(this.largeVarCharWriter);
      }

      return this.largeVarCharWriter;
   }

   public LargeVarCharWriter asLargeVarChar() {
      this.data.setType(this.idx(), Types.MinorType.LARGEVARCHAR);
      return this.getLargeVarCharWriter();
   }

   public void write(LargeVarCharHolder holder) {
      this.data.setType(this.idx(), Types.MinorType.LARGEVARCHAR);
      this.getLargeVarCharWriter().setPosition(this.idx());
      this.getLargeVarCharWriter().writeLargeVarChar(holder.start, holder.end, holder.buffer);
   }

   public void writeLargeVarChar(long start, long end, ArrowBuf buffer) {
      this.data.setType(this.idx(), Types.MinorType.LARGEVARCHAR);
      this.getLargeVarCharWriter().setPosition(this.idx());
      this.getLargeVarCharWriter().writeLargeVarChar(start, end, buffer);
   }

   public void writeLargeVarChar(Text value) {
      this.getLargeVarCharWriter().setPosition(this.idx());
      this.getLargeVarCharWriter().writeLargeVarChar(value);
   }

   public void writeLargeVarChar(String value) {
      this.getLargeVarCharWriter().setPosition(this.idx());
      this.getLargeVarCharWriter().writeLargeVarChar(value);
   }

   private LargeVarBinaryWriter getLargeVarBinaryWriter() {
      if (this.largeVarBinaryWriter == null) {
         this.largeVarBinaryWriter = new LargeVarBinaryWriterImpl(this.data.getLargeVarBinaryVector());
         this.largeVarBinaryWriter.setPosition(this.idx());
         this.writers.add(this.largeVarBinaryWriter);
      }

      return this.largeVarBinaryWriter;
   }

   public LargeVarBinaryWriter asLargeVarBinary() {
      this.data.setType(this.idx(), Types.MinorType.LARGEVARBINARY);
      return this.getLargeVarBinaryWriter();
   }

   public void write(LargeVarBinaryHolder holder) {
      this.data.setType(this.idx(), Types.MinorType.LARGEVARBINARY);
      this.getLargeVarBinaryWriter().setPosition(this.idx());
      this.getLargeVarBinaryWriter().writeLargeVarBinary(holder.start, holder.end, holder.buffer);
   }

   public void writeLargeVarBinary(long start, long end, ArrowBuf buffer) {
      this.data.setType(this.idx(), Types.MinorType.LARGEVARBINARY);
      this.getLargeVarBinaryWriter().setPosition(this.idx());
      this.getLargeVarBinaryWriter().writeLargeVarBinary(start, end, buffer);
   }

   public void writeLargeVarBinary(byte[] value) {
      this.getLargeVarBinaryWriter().setPosition(this.idx());
      this.getLargeVarBinaryWriter().writeLargeVarBinary(value);
   }

   public void writeLargeVarBinary(byte[] value, int offset, int length) {
      this.getLargeVarBinaryWriter().setPosition(this.idx());
      this.getLargeVarBinaryWriter().writeLargeVarBinary(value, offset, length);
   }

   public void writeLargeVarBinary(ByteBuffer value) {
      this.getLargeVarBinaryWriter().setPosition(this.idx());
      this.getLargeVarBinaryWriter().writeLargeVarBinary(value);
   }

   public void writeLargeVarBinary(ByteBuffer value, int offset, int length) {
      this.getLargeVarBinaryWriter().setPosition(this.idx());
      this.getLargeVarBinaryWriter().writeLargeVarBinary(value, offset, length);
   }

   private BitWriter getBitWriter() {
      if (this.bitWriter == null) {
         this.bitWriter = new BitWriterImpl(this.data.getBitVector());
         this.bitWriter.setPosition(this.idx());
         this.writers.add(this.bitWriter);
      }

      return this.bitWriter;
   }

   public BitWriter asBit() {
      this.data.setType(this.idx(), Types.MinorType.BIT);
      return this.getBitWriter();
   }

   public void write(BitHolder holder) {
      this.data.setType(this.idx(), Types.MinorType.BIT);
      this.getBitWriter().setPosition(this.idx());
      this.getBitWriter().writeBit(holder.value);
   }

   public void writeBit(int value) {
      this.data.setType(this.idx(), Types.MinorType.BIT);
      this.getBitWriter().setPosition(this.idx());
      this.getBitWriter().writeBit(value);
   }

   public TinyIntWriter tinyInt() {
      this.data.setType(this.idx(), Types.MinorType.LISTVIEW);
      this.getListViewWriter().setPosition(this.idx());
      return this.getListViewWriter().tinyInt();
   }

   public UInt1Writer uInt1() {
      this.data.setType(this.idx(), Types.MinorType.LISTVIEW);
      this.getListViewWriter().setPosition(this.idx());
      return this.getListViewWriter().uInt1();
   }

   public UInt2Writer uInt2() {
      this.data.setType(this.idx(), Types.MinorType.LISTVIEW);
      this.getListViewWriter().setPosition(this.idx());
      return this.getListViewWriter().uInt2();
   }

   public SmallIntWriter smallInt() {
      this.data.setType(this.idx(), Types.MinorType.LISTVIEW);
      this.getListViewWriter().setPosition(this.idx());
      return this.getListViewWriter().smallInt();
   }

   public Float2Writer float2() {
      this.data.setType(this.idx(), Types.MinorType.LISTVIEW);
      this.getListViewWriter().setPosition(this.idx());
      return this.getListViewWriter().float2();
   }

   public IntWriter integer() {
      this.data.setType(this.idx(), Types.MinorType.LISTVIEW);
      this.getListViewWriter().setPosition(this.idx());
      return this.getListViewWriter().integer();
   }

   public UInt4Writer uInt4() {
      this.data.setType(this.idx(), Types.MinorType.LISTVIEW);
      this.getListViewWriter().setPosition(this.idx());
      return this.getListViewWriter().uInt4();
   }

   public Float4Writer float4() {
      this.data.setType(this.idx(), Types.MinorType.LISTVIEW);
      this.getListViewWriter().setPosition(this.idx());
      return this.getListViewWriter().float4();
   }

   public DateDayWriter dateDay() {
      this.data.setType(this.idx(), Types.MinorType.LISTVIEW);
      this.getListViewWriter().setPosition(this.idx());
      return this.getListViewWriter().dateDay();
   }

   public IntervalYearWriter intervalYear() {
      this.data.setType(this.idx(), Types.MinorType.LISTVIEW);
      this.getListViewWriter().setPosition(this.idx());
      return this.getListViewWriter().intervalYear();
   }

   public TimeSecWriter timeSec() {
      this.data.setType(this.idx(), Types.MinorType.LISTVIEW);
      this.getListViewWriter().setPosition(this.idx());
      return this.getListViewWriter().timeSec();
   }

   public TimeMilliWriter timeMilli() {
      this.data.setType(this.idx(), Types.MinorType.LISTVIEW);
      this.getListViewWriter().setPosition(this.idx());
      return this.getListViewWriter().timeMilli();
   }

   public BigIntWriter bigInt() {
      this.data.setType(this.idx(), Types.MinorType.LISTVIEW);
      this.getListViewWriter().setPosition(this.idx());
      return this.getListViewWriter().bigInt();
   }

   public UInt8Writer uInt8() {
      this.data.setType(this.idx(), Types.MinorType.LISTVIEW);
      this.getListViewWriter().setPosition(this.idx());
      return this.getListViewWriter().uInt8();
   }

   public Float8Writer float8() {
      this.data.setType(this.idx(), Types.MinorType.LISTVIEW);
      this.getListViewWriter().setPosition(this.idx());
      return this.getListViewWriter().float8();
   }

   public DateMilliWriter dateMilli() {
      this.data.setType(this.idx(), Types.MinorType.LISTVIEW);
      this.getListViewWriter().setPosition(this.idx());
      return this.getListViewWriter().dateMilli();
   }

   public DurationWriter duration() {
      this.data.setType(this.idx(), Types.MinorType.LISTVIEW);
      this.getListViewWriter().setPosition(this.idx());
      return this.getListViewWriter().duration();
   }

   public TimeStampSecWriter timeStampSec() {
      this.data.setType(this.idx(), Types.MinorType.LISTVIEW);
      this.getListViewWriter().setPosition(this.idx());
      return this.getListViewWriter().timeStampSec();
   }

   public TimeStampMilliWriter timeStampMilli() {
      this.data.setType(this.idx(), Types.MinorType.LISTVIEW);
      this.getListViewWriter().setPosition(this.idx());
      return this.getListViewWriter().timeStampMilli();
   }

   public TimeStampMicroWriter timeStampMicro() {
      this.data.setType(this.idx(), Types.MinorType.LISTVIEW);
      this.getListViewWriter().setPosition(this.idx());
      return this.getListViewWriter().timeStampMicro();
   }

   public TimeStampNanoWriter timeStampNano() {
      this.data.setType(this.idx(), Types.MinorType.LISTVIEW);
      this.getListViewWriter().setPosition(this.idx());
      return this.getListViewWriter().timeStampNano();
   }

   public TimeStampSecTZWriter timeStampSecTZ() {
      this.data.setType(this.idx(), Types.MinorType.LISTVIEW);
      this.getListViewWriter().setPosition(this.idx());
      return this.getListViewWriter().timeStampSecTZ();
   }

   public TimeStampMilliTZWriter timeStampMilliTZ() {
      this.data.setType(this.idx(), Types.MinorType.LISTVIEW);
      this.getListViewWriter().setPosition(this.idx());
      return this.getListViewWriter().timeStampMilliTZ();
   }

   public TimeStampMicroTZWriter timeStampMicroTZ() {
      this.data.setType(this.idx(), Types.MinorType.LISTVIEW);
      this.getListViewWriter().setPosition(this.idx());
      return this.getListViewWriter().timeStampMicroTZ();
   }

   public TimeStampNanoTZWriter timeStampNanoTZ() {
      this.data.setType(this.idx(), Types.MinorType.LISTVIEW);
      this.getListViewWriter().setPosition(this.idx());
      return this.getListViewWriter().timeStampNanoTZ();
   }

   public TimeMicroWriter timeMicro() {
      this.data.setType(this.idx(), Types.MinorType.LISTVIEW);
      this.getListViewWriter().setPosition(this.idx());
      return this.getListViewWriter().timeMicro();
   }

   public TimeNanoWriter timeNano() {
      this.data.setType(this.idx(), Types.MinorType.LISTVIEW);
      this.getListViewWriter().setPosition(this.idx());
      return this.getListViewWriter().timeNano();
   }

   public IntervalDayWriter intervalDay() {
      this.data.setType(this.idx(), Types.MinorType.LISTVIEW);
      this.getListViewWriter().setPosition(this.idx());
      return this.getListViewWriter().intervalDay();
   }

   public IntervalMonthDayNanoWriter intervalMonthDayNano() {
      this.data.setType(this.idx(), Types.MinorType.LISTVIEW);
      this.getListViewWriter().setPosition(this.idx());
      return this.getListViewWriter().intervalMonthDayNano();
   }

   public Decimal256Writer decimal256() {
      this.data.setType(this.idx(), Types.MinorType.LISTVIEW);
      this.getListViewWriter().setPosition(this.idx());
      return this.getListViewWriter().decimal256();
   }

   public DecimalWriter decimal() {
      this.data.setType(this.idx(), Types.MinorType.LISTVIEW);
      this.getListViewWriter().setPosition(this.idx());
      return this.getListViewWriter().decimal();
   }

   public FixedSizeBinaryWriter fixedSizeBinary() {
      this.data.setType(this.idx(), Types.MinorType.LISTVIEW);
      this.getListViewWriter().setPosition(this.idx());
      return this.getListViewWriter().fixedSizeBinary();
   }

   public VarBinaryWriter varBinary() {
      this.data.setType(this.idx(), Types.MinorType.LISTVIEW);
      this.getListViewWriter().setPosition(this.idx());
      return this.getListViewWriter().varBinary();
   }

   public VarCharWriter varChar() {
      this.data.setType(this.idx(), Types.MinorType.LISTVIEW);
      this.getListViewWriter().setPosition(this.idx());
      return this.getListViewWriter().varChar();
   }

   public ViewVarBinaryWriter viewVarBinary() {
      this.data.setType(this.idx(), Types.MinorType.LISTVIEW);
      this.getListViewWriter().setPosition(this.idx());
      return this.getListViewWriter().viewVarBinary();
   }

   public ViewVarCharWriter viewVarChar() {
      this.data.setType(this.idx(), Types.MinorType.LISTVIEW);
      this.getListViewWriter().setPosition(this.idx());
      return this.getListViewWriter().viewVarChar();
   }

   public LargeVarCharWriter largeVarChar() {
      this.data.setType(this.idx(), Types.MinorType.LISTVIEW);
      this.getListViewWriter().setPosition(this.idx());
      return this.getListViewWriter().largeVarChar();
   }

   public LargeVarBinaryWriter largeVarBinary() {
      this.data.setType(this.idx(), Types.MinorType.LISTVIEW);
      this.getListViewWriter().setPosition(this.idx());
      return this.getListViewWriter().largeVarBinary();
   }

   public BitWriter bit() {
      this.data.setType(this.idx(), Types.MinorType.LISTVIEW);
      this.getListViewWriter().setPosition(this.idx());
      return this.getListViewWriter().bit();
   }
}
