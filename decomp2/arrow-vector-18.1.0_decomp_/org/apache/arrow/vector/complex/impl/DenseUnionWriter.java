package org.apache.arrow.vector.complex.impl;

import org.apache.arrow.memory.ArrowBuf;
import org.apache.arrow.vector.BigIntVector;
import org.apache.arrow.vector.BitVector;
import org.apache.arrow.vector.DateDayVector;
import org.apache.arrow.vector.DateMilliVector;
import org.apache.arrow.vector.Decimal256Vector;
import org.apache.arrow.vector.DecimalVector;
import org.apache.arrow.vector.Float2Vector;
import org.apache.arrow.vector.Float4Vector;
import org.apache.arrow.vector.Float8Vector;
import org.apache.arrow.vector.IntVector;
import org.apache.arrow.vector.IntervalDayVector;
import org.apache.arrow.vector.IntervalMonthDayNanoVector;
import org.apache.arrow.vector.IntervalYearVector;
import org.apache.arrow.vector.LargeVarBinaryVector;
import org.apache.arrow.vector.LargeVarCharVector;
import org.apache.arrow.vector.SmallIntVector;
import org.apache.arrow.vector.TimeMicroVector;
import org.apache.arrow.vector.TimeMilliVector;
import org.apache.arrow.vector.TimeNanoVector;
import org.apache.arrow.vector.TimeSecVector;
import org.apache.arrow.vector.TimeStampMicroVector;
import org.apache.arrow.vector.TimeStampMilliVector;
import org.apache.arrow.vector.TimeStampNanoVector;
import org.apache.arrow.vector.TimeStampSecVector;
import org.apache.arrow.vector.TinyIntVector;
import org.apache.arrow.vector.UInt1Vector;
import org.apache.arrow.vector.UInt2Vector;
import org.apache.arrow.vector.UInt4Vector;
import org.apache.arrow.vector.UInt8Vector;
import org.apache.arrow.vector.VarBinaryVector;
import org.apache.arrow.vector.VarCharVector;
import org.apache.arrow.vector.ViewVarBinaryVector;
import org.apache.arrow.vector.ViewVarCharVector;
import org.apache.arrow.vector.complex.DenseUnionVector;
import org.apache.arrow.vector.complex.ListVector;
import org.apache.arrow.vector.complex.ListViewVector;
import org.apache.arrow.vector.complex.MapVector;
import org.apache.arrow.vector.complex.StructVector;
import org.apache.arrow.vector.complex.writer.BaseWriter;
import org.apache.arrow.vector.complex.writer.BigIntWriter;
import org.apache.arrow.vector.complex.writer.BitWriter;
import org.apache.arrow.vector.complex.writer.DateDayWriter;
import org.apache.arrow.vector.complex.writer.DateMilliWriter;
import org.apache.arrow.vector.complex.writer.Decimal256Writer;
import org.apache.arrow.vector.complex.writer.DecimalWriter;
import org.apache.arrow.vector.complex.writer.FieldWriter;
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
import org.apache.arrow.vector.complex.writer.TimeStampMicroWriter;
import org.apache.arrow.vector.complex.writer.TimeStampMilliWriter;
import org.apache.arrow.vector.complex.writer.TimeStampNanoWriter;
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
import org.apache.arrow.vector.holders.TimeStampMilliHolder;
import org.apache.arrow.vector.holders.TimeStampNanoHolder;
import org.apache.arrow.vector.holders.TimeStampSecHolder;
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

public class DenseUnionWriter extends AbstractFieldWriter implements FieldWriter {
   DenseUnionVector data;
   private BaseWriter[] writers;
   private final NullableStructWriterFactory nullableStructWriterFactory;

   public DenseUnionWriter(DenseUnionVector vector) {
      this(vector, NullableStructWriterFactory.getNullableStructWriterFactoryInstance());
   }

   public DenseUnionWriter(DenseUnionVector vector, NullableStructWriterFactory nullableStructWriterFactory) {
      this.writers = new BaseWriter[128];
      this.data = vector;
      this.nullableStructWriterFactory = nullableStructWriterFactory;
   }

   public void setPosition(int index) {
      super.setPosition(index);

      for(BaseWriter writer : this.writers) {
         writer.setPosition(index);
      }

   }

   public void start() {
      byte typeId = this.data.getTypeId(this.idx());
      this.getStructWriter((byte)this.idx()).start();
   }

   public void end() {
      byte typeId = this.data.getTypeId(this.idx());
      this.getStructWriter(typeId).end();
   }

   public void startList() {
      byte typeId = this.data.getTypeId(this.idx());
      this.getListWriter(typeId).startList();
   }

   public void endList() {
      byte typeId = this.data.getTypeId(this.idx());
      this.getListWriter(typeId).endList();
   }

   public void startListView() {
      byte typeId = this.data.getTypeId(this.idx());
      this.getListViewWriter(typeId).startList();
   }

   public void endListView() {
      byte typeId = this.data.getTypeId(this.idx());
      this.getListViewWriter(typeId).endList();
   }

   private BaseWriter.StructWriter getStructWriter(byte typeId) {
      BaseWriter.StructWriter structWriter = (BaseWriter.StructWriter)this.writers[typeId];
      if (structWriter == null) {
         structWriter = this.nullableStructWriterFactory.build((StructVector)this.data.getVectorByType(typeId));
         this.writers[typeId] = structWriter;
      }

      return structWriter;
   }

   public BaseWriter.StructWriter asStruct(byte typeId) {
      this.data.setTypeId(this.idx(), typeId);
      return this.getStructWriter(typeId);
   }

   private BaseWriter.ListWriter getListWriter(byte typeId) {
      BaseWriter.ListWriter listWriter = (BaseWriter.ListWriter)this.writers[typeId];
      if (listWriter == null) {
         listWriter = new UnionListWriter((ListVector)this.data.getVectorByType(typeId), this.nullableStructWriterFactory);
         this.writers[typeId] = listWriter;
      }

      return listWriter;
   }

   private BaseWriter.ListWriter getListViewWriter(byte typeId) {
      BaseWriter.ListWriter listWriter = (BaseWriter.ListWriter)this.writers[typeId];
      if (listWriter == null) {
         listWriter = new UnionListViewWriter((ListViewVector)this.data.getVectorByType(typeId), this.nullableStructWriterFactory);
         this.writers[typeId] = listWriter;
      }

      return listWriter;
   }

   public BaseWriter.ListWriter asList(byte typeId) {
      this.data.setTypeId(this.idx(), typeId);
      return this.getListWriter(typeId);
   }

   private BaseWriter.MapWriter getMapWriter(byte typeId) {
      BaseWriter.MapWriter mapWriter = (BaseWriter.MapWriter)this.writers[typeId];
      if (mapWriter == null) {
         mapWriter = new UnionMapWriter((MapVector)this.data.getVectorByType(typeId));
         this.writers[typeId] = mapWriter;
      }

      return mapWriter;
   }

   public BaseWriter.MapWriter asMap(byte typeId) {
      this.data.setTypeId(this.idx(), typeId);
      return this.getMapWriter(typeId);
   }

   BaseWriter getWriter(byte typeId) {
      Types.MinorType minorType = this.data.getVectorByType(typeId).getMinorType();
      switch (minorType) {
         case STRUCT:
            return this.getStructWriter(typeId);
         case LIST:
            return this.getListWriter(typeId);
         case MAP:
            return this.getMapWriter(typeId);
         case TINYINT:
            return this.getTinyIntWriter(typeId);
         case UINT1:
            return this.getUInt1Writer(typeId);
         case UINT2:
            return this.getUInt2Writer(typeId);
         case SMALLINT:
            return this.getSmallIntWriter(typeId);
         case FLOAT2:
            return this.getFloat2Writer(typeId);
         case INT:
            return this.getIntWriter(typeId);
         case UINT4:
            return this.getUInt4Writer(typeId);
         case FLOAT4:
            return this.getFloat4Writer(typeId);
         case DATEDAY:
            return this.getDateDayWriter(typeId);
         case INTERVALYEAR:
            return this.getIntervalYearWriter(typeId);
         case TIMESEC:
            return this.getTimeSecWriter(typeId);
         case TIMEMILLI:
            return this.getTimeMilliWriter(typeId);
         case BIGINT:
            return this.getBigIntWriter(typeId);
         case UINT8:
            return this.getUInt8Writer(typeId);
         case FLOAT8:
            return this.getFloat8Writer(typeId);
         case DATEMILLI:
            return this.getDateMilliWriter(typeId);
         case TIMESTAMPSEC:
            return this.getTimeStampSecWriter(typeId);
         case TIMESTAMPMILLI:
            return this.getTimeStampMilliWriter(typeId);
         case TIMESTAMPMICRO:
            return this.getTimeStampMicroWriter(typeId);
         case TIMESTAMPNANO:
            return this.getTimeStampNanoWriter(typeId);
         case TIMEMICRO:
            return this.getTimeMicroWriter(typeId);
         case TIMENANO:
            return this.getTimeNanoWriter(typeId);
         case INTERVALDAY:
            return this.getIntervalDayWriter(typeId);
         case INTERVALMONTHDAYNANO:
            return this.getIntervalMonthDayNanoWriter(typeId);
         case DECIMAL256:
            return this.getDecimal256Writer(typeId);
         case DECIMAL:
            return this.getDecimalWriter(typeId);
         case VARBINARY:
            return this.getVarBinaryWriter(typeId);
         case VARCHAR:
            return this.getVarCharWriter(typeId);
         case VIEWVARBINARY:
            return this.getViewVarBinaryWriter(typeId);
         case VIEWVARCHAR:
            return this.getViewVarCharWriter(typeId);
         case LARGEVARCHAR:
            return this.getLargeVarCharWriter(typeId);
         case LARGEVARBINARY:
            return this.getLargeVarBinaryWriter(typeId);
         case BIT:
            return this.getBitWriter(typeId);
         default:
            throw new UnsupportedOperationException("Unknown type: " + String.valueOf(minorType));
      }
   }

   private TinyIntWriter getTinyIntWriter(byte typeId) {
      TinyIntWriter writer = (TinyIntWriter)this.writers[typeId];
      if (writer == null) {
         writer = new TinyIntWriterImpl((TinyIntVector)this.data.getVectorByType(typeId));
         this.writers[typeId] = writer;
      }

      return writer;
   }

   public TinyIntWriter asTinyInt(byte typeId) {
      this.data.setTypeId(this.idx(), typeId);
      return this.getTinyIntWriter(typeId);
   }

   public void write(TinyIntHolder holder) {
      throw new UnsupportedOperationException();
   }

   public void writeTinyInt(byte value, byte typeId) {
      this.data.setTypeId(this.idx(), typeId);
      this.getTinyIntWriter(typeId).setPosition(this.data.getOffset(this.idx()));
      this.getTinyIntWriter(typeId).writeTinyInt(value);
   }

   private UInt1Writer getUInt1Writer(byte typeId) {
      UInt1Writer writer = (UInt1Writer)this.writers[typeId];
      if (writer == null) {
         writer = new UInt1WriterImpl((UInt1Vector)this.data.getVectorByType(typeId));
         this.writers[typeId] = writer;
      }

      return writer;
   }

   public UInt1Writer asUInt1(byte typeId) {
      this.data.setTypeId(this.idx(), typeId);
      return this.getUInt1Writer(typeId);
   }

   public void write(UInt1Holder holder) {
      throw new UnsupportedOperationException();
   }

   public void writeUInt1(byte value, byte typeId) {
      this.data.setTypeId(this.idx(), typeId);
      this.getUInt1Writer(typeId).setPosition(this.data.getOffset(this.idx()));
      this.getUInt1Writer(typeId).writeUInt1(value);
   }

   private UInt2Writer getUInt2Writer(byte typeId) {
      UInt2Writer writer = (UInt2Writer)this.writers[typeId];
      if (writer == null) {
         writer = new UInt2WriterImpl((UInt2Vector)this.data.getVectorByType(typeId));
         this.writers[typeId] = writer;
      }

      return writer;
   }

   public UInt2Writer asUInt2(byte typeId) {
      this.data.setTypeId(this.idx(), typeId);
      return this.getUInt2Writer(typeId);
   }

   public void write(UInt2Holder holder) {
      throw new UnsupportedOperationException();
   }

   public void writeUInt2(char value, byte typeId) {
      this.data.setTypeId(this.idx(), typeId);
      this.getUInt2Writer(typeId).setPosition(this.data.getOffset(this.idx()));
      this.getUInt2Writer(typeId).writeUInt2(value);
   }

   private SmallIntWriter getSmallIntWriter(byte typeId) {
      SmallIntWriter writer = (SmallIntWriter)this.writers[typeId];
      if (writer == null) {
         writer = new SmallIntWriterImpl((SmallIntVector)this.data.getVectorByType(typeId));
         this.writers[typeId] = writer;
      }

      return writer;
   }

   public SmallIntWriter asSmallInt(byte typeId) {
      this.data.setTypeId(this.idx(), typeId);
      return this.getSmallIntWriter(typeId);
   }

   public void write(SmallIntHolder holder) {
      throw new UnsupportedOperationException();
   }

   public void writeSmallInt(short value, byte typeId) {
      this.data.setTypeId(this.idx(), typeId);
      this.getSmallIntWriter(typeId).setPosition(this.data.getOffset(this.idx()));
      this.getSmallIntWriter(typeId).writeSmallInt(value);
   }

   private Float2Writer getFloat2Writer(byte typeId) {
      Float2Writer writer = (Float2Writer)this.writers[typeId];
      if (writer == null) {
         writer = new Float2WriterImpl((Float2Vector)this.data.getVectorByType(typeId));
         this.writers[typeId] = writer;
      }

      return writer;
   }

   public Float2Writer asFloat2(byte typeId) {
      this.data.setTypeId(this.idx(), typeId);
      return this.getFloat2Writer(typeId);
   }

   public void write(Float2Holder holder) {
      throw new UnsupportedOperationException();
   }

   public void writeFloat2(short value, byte typeId) {
      this.data.setTypeId(this.idx(), typeId);
      this.getFloat2Writer(typeId).setPosition(this.data.getOffset(this.idx()));
      this.getFloat2Writer(typeId).writeFloat2(value);
   }

   private IntWriter getIntWriter(byte typeId) {
      IntWriter writer = (IntWriter)this.writers[typeId];
      if (writer == null) {
         writer = new IntWriterImpl((IntVector)this.data.getVectorByType(typeId));
         this.writers[typeId] = writer;
      }

      return writer;
   }

   public IntWriter asInt(byte typeId) {
      this.data.setTypeId(this.idx(), typeId);
      return this.getIntWriter(typeId);
   }

   public void write(IntHolder holder) {
      throw new UnsupportedOperationException();
   }

   public void writeInt(int value, byte typeId) {
      this.data.setTypeId(this.idx(), typeId);
      this.getIntWriter(typeId).setPosition(this.data.getOffset(this.idx()));
      this.getIntWriter(typeId).writeInt(value);
   }

   private UInt4Writer getUInt4Writer(byte typeId) {
      UInt4Writer writer = (UInt4Writer)this.writers[typeId];
      if (writer == null) {
         writer = new UInt4WriterImpl((UInt4Vector)this.data.getVectorByType(typeId));
         this.writers[typeId] = writer;
      }

      return writer;
   }

   public UInt4Writer asUInt4(byte typeId) {
      this.data.setTypeId(this.idx(), typeId);
      return this.getUInt4Writer(typeId);
   }

   public void write(UInt4Holder holder) {
      throw new UnsupportedOperationException();
   }

   public void writeUInt4(int value, byte typeId) {
      this.data.setTypeId(this.idx(), typeId);
      this.getUInt4Writer(typeId).setPosition(this.data.getOffset(this.idx()));
      this.getUInt4Writer(typeId).writeUInt4(value);
   }

   private Float4Writer getFloat4Writer(byte typeId) {
      Float4Writer writer = (Float4Writer)this.writers[typeId];
      if (writer == null) {
         writer = new Float4WriterImpl((Float4Vector)this.data.getVectorByType(typeId));
         this.writers[typeId] = writer;
      }

      return writer;
   }

   public Float4Writer asFloat4(byte typeId) {
      this.data.setTypeId(this.idx(), typeId);
      return this.getFloat4Writer(typeId);
   }

   public void write(Float4Holder holder) {
      throw new UnsupportedOperationException();
   }

   public void writeFloat4(float value, byte typeId) {
      this.data.setTypeId(this.idx(), typeId);
      this.getFloat4Writer(typeId).setPosition(this.data.getOffset(this.idx()));
      this.getFloat4Writer(typeId).writeFloat4(value);
   }

   private DateDayWriter getDateDayWriter(byte typeId) {
      DateDayWriter writer = (DateDayWriter)this.writers[typeId];
      if (writer == null) {
         writer = new DateDayWriterImpl((DateDayVector)this.data.getVectorByType(typeId));
         this.writers[typeId] = writer;
      }

      return writer;
   }

   public DateDayWriter asDateDay(byte typeId) {
      this.data.setTypeId(this.idx(), typeId);
      return this.getDateDayWriter(typeId);
   }

   public void write(DateDayHolder holder) {
      throw new UnsupportedOperationException();
   }

   public void writeDateDay(int value, byte typeId) {
      this.data.setTypeId(this.idx(), typeId);
      this.getDateDayWriter(typeId).setPosition(this.data.getOffset(this.idx()));
      this.getDateDayWriter(typeId).writeDateDay(value);
   }

   private IntervalYearWriter getIntervalYearWriter(byte typeId) {
      IntervalYearWriter writer = (IntervalYearWriter)this.writers[typeId];
      if (writer == null) {
         writer = new IntervalYearWriterImpl((IntervalYearVector)this.data.getVectorByType(typeId));
         this.writers[typeId] = writer;
      }

      return writer;
   }

   public IntervalYearWriter asIntervalYear(byte typeId) {
      this.data.setTypeId(this.idx(), typeId);
      return this.getIntervalYearWriter(typeId);
   }

   public void write(IntervalYearHolder holder) {
      throw new UnsupportedOperationException();
   }

   public void writeIntervalYear(int value, byte typeId) {
      this.data.setTypeId(this.idx(), typeId);
      this.getIntervalYearWriter(typeId).setPosition(this.data.getOffset(this.idx()));
      this.getIntervalYearWriter(typeId).writeIntervalYear(value);
   }

   private TimeSecWriter getTimeSecWriter(byte typeId) {
      TimeSecWriter writer = (TimeSecWriter)this.writers[typeId];
      if (writer == null) {
         writer = new TimeSecWriterImpl((TimeSecVector)this.data.getVectorByType(typeId));
         this.writers[typeId] = writer;
      }

      return writer;
   }

   public TimeSecWriter asTimeSec(byte typeId) {
      this.data.setTypeId(this.idx(), typeId);
      return this.getTimeSecWriter(typeId);
   }

   public void write(TimeSecHolder holder) {
      throw new UnsupportedOperationException();
   }

   public void writeTimeSec(int value, byte typeId) {
      this.data.setTypeId(this.idx(), typeId);
      this.getTimeSecWriter(typeId).setPosition(this.data.getOffset(this.idx()));
      this.getTimeSecWriter(typeId).writeTimeSec(value);
   }

   private TimeMilliWriter getTimeMilliWriter(byte typeId) {
      TimeMilliWriter writer = (TimeMilliWriter)this.writers[typeId];
      if (writer == null) {
         writer = new TimeMilliWriterImpl((TimeMilliVector)this.data.getVectorByType(typeId));
         this.writers[typeId] = writer;
      }

      return writer;
   }

   public TimeMilliWriter asTimeMilli(byte typeId) {
      this.data.setTypeId(this.idx(), typeId);
      return this.getTimeMilliWriter(typeId);
   }

   public void write(TimeMilliHolder holder) {
      throw new UnsupportedOperationException();
   }

   public void writeTimeMilli(int value, byte typeId) {
      this.data.setTypeId(this.idx(), typeId);
      this.getTimeMilliWriter(typeId).setPosition(this.data.getOffset(this.idx()));
      this.getTimeMilliWriter(typeId).writeTimeMilli(value);
   }

   private BigIntWriter getBigIntWriter(byte typeId) {
      BigIntWriter writer = (BigIntWriter)this.writers[typeId];
      if (writer == null) {
         writer = new BigIntWriterImpl((BigIntVector)this.data.getVectorByType(typeId));
         this.writers[typeId] = writer;
      }

      return writer;
   }

   public BigIntWriter asBigInt(byte typeId) {
      this.data.setTypeId(this.idx(), typeId);
      return this.getBigIntWriter(typeId);
   }

   public void write(BigIntHolder holder) {
      throw new UnsupportedOperationException();
   }

   public void writeBigInt(long value, byte typeId) {
      this.data.setTypeId(this.idx(), typeId);
      this.getBigIntWriter(typeId).setPosition(this.data.getOffset(this.idx()));
      this.getBigIntWriter(typeId).writeBigInt(value);
   }

   private UInt8Writer getUInt8Writer(byte typeId) {
      UInt8Writer writer = (UInt8Writer)this.writers[typeId];
      if (writer == null) {
         writer = new UInt8WriterImpl((UInt8Vector)this.data.getVectorByType(typeId));
         this.writers[typeId] = writer;
      }

      return writer;
   }

   public UInt8Writer asUInt8(byte typeId) {
      this.data.setTypeId(this.idx(), typeId);
      return this.getUInt8Writer(typeId);
   }

   public void write(UInt8Holder holder) {
      throw new UnsupportedOperationException();
   }

   public void writeUInt8(long value, byte typeId) {
      this.data.setTypeId(this.idx(), typeId);
      this.getUInt8Writer(typeId).setPosition(this.data.getOffset(this.idx()));
      this.getUInt8Writer(typeId).writeUInt8(value);
   }

   private Float8Writer getFloat8Writer(byte typeId) {
      Float8Writer writer = (Float8Writer)this.writers[typeId];
      if (writer == null) {
         writer = new Float8WriterImpl((Float8Vector)this.data.getVectorByType(typeId));
         this.writers[typeId] = writer;
      }

      return writer;
   }

   public Float8Writer asFloat8(byte typeId) {
      this.data.setTypeId(this.idx(), typeId);
      return this.getFloat8Writer(typeId);
   }

   public void write(Float8Holder holder) {
      throw new UnsupportedOperationException();
   }

   public void writeFloat8(double value, byte typeId) {
      this.data.setTypeId(this.idx(), typeId);
      this.getFloat8Writer(typeId).setPosition(this.data.getOffset(this.idx()));
      this.getFloat8Writer(typeId).writeFloat8(value);
   }

   private DateMilliWriter getDateMilliWriter(byte typeId) {
      DateMilliWriter writer = (DateMilliWriter)this.writers[typeId];
      if (writer == null) {
         writer = new DateMilliWriterImpl((DateMilliVector)this.data.getVectorByType(typeId));
         this.writers[typeId] = writer;
      }

      return writer;
   }

   public DateMilliWriter asDateMilli(byte typeId) {
      this.data.setTypeId(this.idx(), typeId);
      return this.getDateMilliWriter(typeId);
   }

   public void write(DateMilliHolder holder) {
      throw new UnsupportedOperationException();
   }

   public void writeDateMilli(long value, byte typeId) {
      this.data.setTypeId(this.idx(), typeId);
      this.getDateMilliWriter(typeId).setPosition(this.data.getOffset(this.idx()));
      this.getDateMilliWriter(typeId).writeDateMilli(value);
   }

   private TimeStampSecWriter getTimeStampSecWriter(byte typeId) {
      TimeStampSecWriter writer = (TimeStampSecWriter)this.writers[typeId];
      if (writer == null) {
         writer = new TimeStampSecWriterImpl((TimeStampSecVector)this.data.getVectorByType(typeId));
         this.writers[typeId] = writer;
      }

      return writer;
   }

   public TimeStampSecWriter asTimeStampSec(byte typeId) {
      this.data.setTypeId(this.idx(), typeId);
      return this.getTimeStampSecWriter(typeId);
   }

   public void write(TimeStampSecHolder holder) {
      throw new UnsupportedOperationException();
   }

   public void writeTimeStampSec(long value, byte typeId) {
      this.data.setTypeId(this.idx(), typeId);
      this.getTimeStampSecWriter(typeId).setPosition(this.data.getOffset(this.idx()));
      this.getTimeStampSecWriter(typeId).writeTimeStampSec(value);
   }

   private TimeStampMilliWriter getTimeStampMilliWriter(byte typeId) {
      TimeStampMilliWriter writer = (TimeStampMilliWriter)this.writers[typeId];
      if (writer == null) {
         writer = new TimeStampMilliWriterImpl((TimeStampMilliVector)this.data.getVectorByType(typeId));
         this.writers[typeId] = writer;
      }

      return writer;
   }

   public TimeStampMilliWriter asTimeStampMilli(byte typeId) {
      this.data.setTypeId(this.idx(), typeId);
      return this.getTimeStampMilliWriter(typeId);
   }

   public void write(TimeStampMilliHolder holder) {
      throw new UnsupportedOperationException();
   }

   public void writeTimeStampMilli(long value, byte typeId) {
      this.data.setTypeId(this.idx(), typeId);
      this.getTimeStampMilliWriter(typeId).setPosition(this.data.getOffset(this.idx()));
      this.getTimeStampMilliWriter(typeId).writeTimeStampMilli(value);
   }

   private TimeStampMicroWriter getTimeStampMicroWriter(byte typeId) {
      TimeStampMicroWriter writer = (TimeStampMicroWriter)this.writers[typeId];
      if (writer == null) {
         writer = new TimeStampMicroWriterImpl((TimeStampMicroVector)this.data.getVectorByType(typeId));
         this.writers[typeId] = writer;
      }

      return writer;
   }

   public TimeStampMicroWriter asTimeStampMicro(byte typeId) {
      this.data.setTypeId(this.idx(), typeId);
      return this.getTimeStampMicroWriter(typeId);
   }

   public void write(TimeStampMicroHolder holder) {
      throw new UnsupportedOperationException();
   }

   public void writeTimeStampMicro(long value, byte typeId) {
      this.data.setTypeId(this.idx(), typeId);
      this.getTimeStampMicroWriter(typeId).setPosition(this.data.getOffset(this.idx()));
      this.getTimeStampMicroWriter(typeId).writeTimeStampMicro(value);
   }

   private TimeStampNanoWriter getTimeStampNanoWriter(byte typeId) {
      TimeStampNanoWriter writer = (TimeStampNanoWriter)this.writers[typeId];
      if (writer == null) {
         writer = new TimeStampNanoWriterImpl((TimeStampNanoVector)this.data.getVectorByType(typeId));
         this.writers[typeId] = writer;
      }

      return writer;
   }

   public TimeStampNanoWriter asTimeStampNano(byte typeId) {
      this.data.setTypeId(this.idx(), typeId);
      return this.getTimeStampNanoWriter(typeId);
   }

   public void write(TimeStampNanoHolder holder) {
      throw new UnsupportedOperationException();
   }

   public void writeTimeStampNano(long value, byte typeId) {
      this.data.setTypeId(this.idx(), typeId);
      this.getTimeStampNanoWriter(typeId).setPosition(this.data.getOffset(this.idx()));
      this.getTimeStampNanoWriter(typeId).writeTimeStampNano(value);
   }

   private TimeMicroWriter getTimeMicroWriter(byte typeId) {
      TimeMicroWriter writer = (TimeMicroWriter)this.writers[typeId];
      if (writer == null) {
         writer = new TimeMicroWriterImpl((TimeMicroVector)this.data.getVectorByType(typeId));
         this.writers[typeId] = writer;
      }

      return writer;
   }

   public TimeMicroWriter asTimeMicro(byte typeId) {
      this.data.setTypeId(this.idx(), typeId);
      return this.getTimeMicroWriter(typeId);
   }

   public void write(TimeMicroHolder holder) {
      throw new UnsupportedOperationException();
   }

   public void writeTimeMicro(long value, byte typeId) {
      this.data.setTypeId(this.idx(), typeId);
      this.getTimeMicroWriter(typeId).setPosition(this.data.getOffset(this.idx()));
      this.getTimeMicroWriter(typeId).writeTimeMicro(value);
   }

   private TimeNanoWriter getTimeNanoWriter(byte typeId) {
      TimeNanoWriter writer = (TimeNanoWriter)this.writers[typeId];
      if (writer == null) {
         writer = new TimeNanoWriterImpl((TimeNanoVector)this.data.getVectorByType(typeId));
         this.writers[typeId] = writer;
      }

      return writer;
   }

   public TimeNanoWriter asTimeNano(byte typeId) {
      this.data.setTypeId(this.idx(), typeId);
      return this.getTimeNanoWriter(typeId);
   }

   public void write(TimeNanoHolder holder) {
      throw new UnsupportedOperationException();
   }

   public void writeTimeNano(long value, byte typeId) {
      this.data.setTypeId(this.idx(), typeId);
      this.getTimeNanoWriter(typeId).setPosition(this.data.getOffset(this.idx()));
      this.getTimeNanoWriter(typeId).writeTimeNano(value);
   }

   private IntervalDayWriter getIntervalDayWriter(byte typeId) {
      IntervalDayWriter writer = (IntervalDayWriter)this.writers[typeId];
      if (writer == null) {
         writer = new IntervalDayWriterImpl((IntervalDayVector)this.data.getVectorByType(typeId));
         this.writers[typeId] = writer;
      }

      return writer;
   }

   public IntervalDayWriter asIntervalDay(byte typeId) {
      this.data.setTypeId(this.idx(), typeId);
      return this.getIntervalDayWriter(typeId);
   }

   public void write(IntervalDayHolder holder) {
      throw new UnsupportedOperationException();
   }

   public void writeIntervalDay(int days, int milliseconds, byte typeId) {
      this.data.setTypeId(this.idx(), typeId);
      this.getIntervalDayWriter(typeId).setPosition(this.data.getOffset(this.idx()));
      this.getIntervalDayWriter(typeId).writeIntervalDay(days, milliseconds);
   }

   private IntervalMonthDayNanoWriter getIntervalMonthDayNanoWriter(byte typeId) {
      IntervalMonthDayNanoWriter writer = (IntervalMonthDayNanoWriter)this.writers[typeId];
      if (writer == null) {
         writer = new IntervalMonthDayNanoWriterImpl((IntervalMonthDayNanoVector)this.data.getVectorByType(typeId));
         this.writers[typeId] = writer;
      }

      return writer;
   }

   public IntervalMonthDayNanoWriter asIntervalMonthDayNano(byte typeId) {
      this.data.setTypeId(this.idx(), typeId);
      return this.getIntervalMonthDayNanoWriter(typeId);
   }

   public void write(IntervalMonthDayNanoHolder holder) {
      throw new UnsupportedOperationException();
   }

   public void writeIntervalMonthDayNano(int months, int days, long nanoseconds, byte typeId) {
      this.data.setTypeId(this.idx(), typeId);
      this.getIntervalMonthDayNanoWriter(typeId).setPosition(this.data.getOffset(this.idx()));
      this.getIntervalMonthDayNanoWriter(typeId).writeIntervalMonthDayNano(months, days, nanoseconds);
   }

   private Decimal256Writer getDecimal256Writer(byte typeId) {
      Decimal256Writer writer = (Decimal256Writer)this.writers[typeId];
      if (writer == null) {
         writer = new Decimal256WriterImpl((Decimal256Vector)this.data.getVectorByType(typeId));
         this.writers[typeId] = writer;
      }

      return writer;
   }

   public Decimal256Writer asDecimal256(byte typeId) {
      this.data.setTypeId(this.idx(), typeId);
      return this.getDecimal256Writer(typeId);
   }

   public void write(Decimal256Holder holder) {
      throw new UnsupportedOperationException();
   }

   public void writeDecimal256(long start, ArrowBuf buffer, byte typeId, ArrowType arrowType) {
      this.data.setTypeId(this.idx(), typeId);
      this.getDecimal256Writer(typeId).setPosition(this.data.getOffset(this.idx()));
      this.getDecimal256Writer(typeId).writeDecimal256(start, buffer, arrowType);
   }

   private DecimalWriter getDecimalWriter(byte typeId) {
      DecimalWriter writer = (DecimalWriter)this.writers[typeId];
      if (writer == null) {
         writer = new DecimalWriterImpl((DecimalVector)this.data.getVectorByType(typeId));
         this.writers[typeId] = writer;
      }

      return writer;
   }

   public DecimalWriter asDecimal(byte typeId) {
      this.data.setTypeId(this.idx(), typeId);
      return this.getDecimalWriter(typeId);
   }

   public void write(DecimalHolder holder) {
      throw new UnsupportedOperationException();
   }

   public void writeDecimal(long start, ArrowBuf buffer, byte typeId, ArrowType arrowType) {
      this.data.setTypeId(this.idx(), typeId);
      this.getDecimalWriter(typeId).setPosition(this.data.getOffset(this.idx()));
      this.getDecimalWriter(typeId).writeDecimal(start, buffer, arrowType);
   }

   private VarBinaryWriter getVarBinaryWriter(byte typeId) {
      VarBinaryWriter writer = (VarBinaryWriter)this.writers[typeId];
      if (writer == null) {
         writer = new VarBinaryWriterImpl((VarBinaryVector)this.data.getVectorByType(typeId));
         this.writers[typeId] = writer;
      }

      return writer;
   }

   public VarBinaryWriter asVarBinary(byte typeId) {
      this.data.setTypeId(this.idx(), typeId);
      return this.getVarBinaryWriter(typeId);
   }

   public void write(VarBinaryHolder holder) {
      throw new UnsupportedOperationException();
   }

   public void writeVarBinary(int start, int end, ArrowBuf buffer, byte typeId) {
      this.data.setTypeId(this.idx(), typeId);
      this.getVarBinaryWriter(typeId).setPosition(this.data.getOffset(this.idx()));
      this.getVarBinaryWriter(typeId).writeVarBinary(start, end, buffer);
   }

   private VarCharWriter getVarCharWriter(byte typeId) {
      VarCharWriter writer = (VarCharWriter)this.writers[typeId];
      if (writer == null) {
         writer = new VarCharWriterImpl((VarCharVector)this.data.getVectorByType(typeId));
         this.writers[typeId] = writer;
      }

      return writer;
   }

   public VarCharWriter asVarChar(byte typeId) {
      this.data.setTypeId(this.idx(), typeId);
      return this.getVarCharWriter(typeId);
   }

   public void write(VarCharHolder holder) {
      throw new UnsupportedOperationException();
   }

   public void writeVarChar(int start, int end, ArrowBuf buffer, byte typeId) {
      this.data.setTypeId(this.idx(), typeId);
      this.getVarCharWriter(typeId).setPosition(this.data.getOffset(this.idx()));
      this.getVarCharWriter(typeId).writeVarChar(start, end, buffer);
   }

   private ViewVarBinaryWriter getViewVarBinaryWriter(byte typeId) {
      ViewVarBinaryWriter writer = (ViewVarBinaryWriter)this.writers[typeId];
      if (writer == null) {
         writer = new ViewVarBinaryWriterImpl((ViewVarBinaryVector)this.data.getVectorByType(typeId));
         this.writers[typeId] = writer;
      }

      return writer;
   }

   public ViewVarBinaryWriter asViewVarBinary(byte typeId) {
      this.data.setTypeId(this.idx(), typeId);
      return this.getViewVarBinaryWriter(typeId);
   }

   public void write(ViewVarBinaryHolder holder) {
      throw new UnsupportedOperationException();
   }

   public void writeViewVarBinary(int start, int end, ArrowBuf buffer, byte typeId) {
      this.data.setTypeId(this.idx(), typeId);
      this.getViewVarBinaryWriter(typeId).setPosition(this.data.getOffset(this.idx()));
      this.getViewVarBinaryWriter(typeId).writeViewVarBinary(start, end, buffer);
   }

   private ViewVarCharWriter getViewVarCharWriter(byte typeId) {
      ViewVarCharWriter writer = (ViewVarCharWriter)this.writers[typeId];
      if (writer == null) {
         writer = new ViewVarCharWriterImpl((ViewVarCharVector)this.data.getVectorByType(typeId));
         this.writers[typeId] = writer;
      }

      return writer;
   }

   public ViewVarCharWriter asViewVarChar(byte typeId) {
      this.data.setTypeId(this.idx(), typeId);
      return this.getViewVarCharWriter(typeId);
   }

   public void write(ViewVarCharHolder holder) {
      throw new UnsupportedOperationException();
   }

   public void writeViewVarChar(int start, int end, ArrowBuf buffer, byte typeId) {
      this.data.setTypeId(this.idx(), typeId);
      this.getViewVarCharWriter(typeId).setPosition(this.data.getOffset(this.idx()));
      this.getViewVarCharWriter(typeId).writeViewVarChar(start, end, buffer);
   }

   private LargeVarCharWriter getLargeVarCharWriter(byte typeId) {
      LargeVarCharWriter writer = (LargeVarCharWriter)this.writers[typeId];
      if (writer == null) {
         writer = new LargeVarCharWriterImpl((LargeVarCharVector)this.data.getVectorByType(typeId));
         this.writers[typeId] = writer;
      }

      return writer;
   }

   public LargeVarCharWriter asLargeVarChar(byte typeId) {
      this.data.setTypeId(this.idx(), typeId);
      return this.getLargeVarCharWriter(typeId);
   }

   public void write(LargeVarCharHolder holder) {
      throw new UnsupportedOperationException();
   }

   public void writeLargeVarChar(long start, long end, ArrowBuf buffer, byte typeId) {
      this.data.setTypeId(this.idx(), typeId);
      this.getLargeVarCharWriter(typeId).setPosition(this.data.getOffset(this.idx()));
      this.getLargeVarCharWriter(typeId).writeLargeVarChar(start, end, buffer);
   }

   private LargeVarBinaryWriter getLargeVarBinaryWriter(byte typeId) {
      LargeVarBinaryWriter writer = (LargeVarBinaryWriter)this.writers[typeId];
      if (writer == null) {
         writer = new LargeVarBinaryWriterImpl((LargeVarBinaryVector)this.data.getVectorByType(typeId));
         this.writers[typeId] = writer;
      }

      return writer;
   }

   public LargeVarBinaryWriter asLargeVarBinary(byte typeId) {
      this.data.setTypeId(this.idx(), typeId);
      return this.getLargeVarBinaryWriter(typeId);
   }

   public void write(LargeVarBinaryHolder holder) {
      throw new UnsupportedOperationException();
   }

   public void writeLargeVarBinary(long start, long end, ArrowBuf buffer, byte typeId) {
      this.data.setTypeId(this.idx(), typeId);
      this.getLargeVarBinaryWriter(typeId).setPosition(this.data.getOffset(this.idx()));
      this.getLargeVarBinaryWriter(typeId).writeLargeVarBinary(start, end, buffer);
   }

   private BitWriter getBitWriter(byte typeId) {
      BitWriter writer = (BitWriter)this.writers[typeId];
      if (writer == null) {
         writer = new BitWriterImpl((BitVector)this.data.getVectorByType(typeId));
         this.writers[typeId] = writer;
      }

      return writer;
   }

   public BitWriter asBit(byte typeId) {
      this.data.setTypeId(this.idx(), typeId);
      return this.getBitWriter(typeId);
   }

   public void write(BitHolder holder) {
      throw new UnsupportedOperationException();
   }

   public void writeBit(int value, byte typeId) {
      this.data.setTypeId(this.idx(), typeId);
      this.getBitWriter(typeId).setPosition(this.data.getOffset(this.idx()));
      this.getBitWriter(typeId).writeBit(value);
   }

   public void writeNull() {
   }

   public BaseWriter.StructWriter struct() {
      byte typeId = this.data.getTypeId(this.idx());
      this.data.setTypeId(this.idx(), typeId);
      this.getListWriter(typeId).setPosition(this.data.getOffset(this.idx()));
      return this.getListWriter(typeId).struct();
   }

   public BaseWriter.ListWriter list() {
      byte typeId = this.data.getTypeId(this.idx());
      this.data.setTypeId(this.idx(), typeId);
      this.getListWriter(typeId).setPosition(this.data.getOffset(this.idx()));
      return this.getListWriter(typeId).list();
   }

   public BaseWriter.ListWriter list(String name) {
      byte typeId = this.data.getTypeId(this.idx());
      this.data.setTypeId(this.idx(), typeId);
      this.getStructWriter(typeId).setPosition(this.data.getOffset(this.idx()));
      return this.getStructWriter(typeId).list(name);
   }

   public BaseWriter.MapWriter map() {
      byte typeId = this.data.getTypeId(this.idx());
      this.data.setTypeId(this.idx(), typeId);
      this.getListWriter(typeId).setPosition(this.data.getOffset(this.idx()));
      return this.getMapWriter(typeId).map();
   }

   public BaseWriter.MapWriter map(String name) {
      byte typeId = this.data.getTypeId(this.idx());
      this.data.setTypeId(this.idx(), typeId);
      this.getStructWriter(typeId).setPosition(this.data.getOffset(this.idx()));
      return this.getStructWriter(typeId).map(name);
   }

   public BaseWriter.MapWriter map(String name, boolean keysSorted) {
      byte typeId = this.data.getTypeId(this.idx());
      this.data.setTypeId(this.idx(), typeId);
      this.getStructWriter(typeId).setPosition(this.data.getOffset(this.idx()));
      return this.getStructWriter(typeId).map(name, keysSorted);
   }

   public BaseWriter.StructWriter struct(String name) {
      byte typeId = this.data.getTypeId(this.idx());
      this.data.setTypeId(this.idx(), typeId);
      this.getStructWriter(typeId).setPosition(this.data.getOffset(this.idx()));
      return this.getStructWriter(typeId).struct(name);
   }

   public TinyIntWriter tinyInt(String name) {
      byte typeId = this.data.getTypeId(this.idx());
      this.data.setTypeId(this.idx(), typeId);
      this.getStructWriter(typeId).setPosition(this.data.getOffset(this.idx()));
      return this.getStructWriter(typeId).tinyInt(name);
   }

   public TinyIntWriter tinyInt() {
      byte typeId = this.data.getTypeId(this.idx());
      this.data.setTypeId(this.idx(), typeId);
      this.getListWriter(typeId).setPosition(this.data.getOffset(this.idx()));
      return this.getListWriter(typeId).tinyInt();
   }

   public UInt1Writer uInt1(String name) {
      byte typeId = this.data.getTypeId(this.idx());
      this.data.setTypeId(this.idx(), typeId);
      this.getStructWriter(typeId).setPosition(this.data.getOffset(this.idx()));
      return this.getStructWriter(typeId).uInt1(name);
   }

   public UInt1Writer uInt1() {
      byte typeId = this.data.getTypeId(this.idx());
      this.data.setTypeId(this.idx(), typeId);
      this.getListWriter(typeId).setPosition(this.data.getOffset(this.idx()));
      return this.getListWriter(typeId).uInt1();
   }

   public UInt2Writer uInt2(String name) {
      byte typeId = this.data.getTypeId(this.idx());
      this.data.setTypeId(this.idx(), typeId);
      this.getStructWriter(typeId).setPosition(this.data.getOffset(this.idx()));
      return this.getStructWriter(typeId).uInt2(name);
   }

   public UInt2Writer uInt2() {
      byte typeId = this.data.getTypeId(this.idx());
      this.data.setTypeId(this.idx(), typeId);
      this.getListWriter(typeId).setPosition(this.data.getOffset(this.idx()));
      return this.getListWriter(typeId).uInt2();
   }

   public SmallIntWriter smallInt(String name) {
      byte typeId = this.data.getTypeId(this.idx());
      this.data.setTypeId(this.idx(), typeId);
      this.getStructWriter(typeId).setPosition(this.data.getOffset(this.idx()));
      return this.getStructWriter(typeId).smallInt(name);
   }

   public SmallIntWriter smallInt() {
      byte typeId = this.data.getTypeId(this.idx());
      this.data.setTypeId(this.idx(), typeId);
      this.getListWriter(typeId).setPosition(this.data.getOffset(this.idx()));
      return this.getListWriter(typeId).smallInt();
   }

   public Float2Writer float2(String name) {
      byte typeId = this.data.getTypeId(this.idx());
      this.data.setTypeId(this.idx(), typeId);
      this.getStructWriter(typeId).setPosition(this.data.getOffset(this.idx()));
      return this.getStructWriter(typeId).float2(name);
   }

   public Float2Writer float2() {
      byte typeId = this.data.getTypeId(this.idx());
      this.data.setTypeId(this.idx(), typeId);
      this.getListWriter(typeId).setPosition(this.data.getOffset(this.idx()));
      return this.getListWriter(typeId).float2();
   }

   public IntWriter integer(String name) {
      byte typeId = this.data.getTypeId(this.idx());
      this.data.setTypeId(this.idx(), typeId);
      this.getStructWriter(typeId).setPosition(this.data.getOffset(this.idx()));
      return this.getStructWriter(typeId).integer(name);
   }

   public IntWriter integer() {
      byte typeId = this.data.getTypeId(this.idx());
      this.data.setTypeId(this.idx(), typeId);
      this.getListWriter(typeId).setPosition(this.data.getOffset(this.idx()));
      return this.getListWriter(typeId).integer();
   }

   public UInt4Writer uInt4(String name) {
      byte typeId = this.data.getTypeId(this.idx());
      this.data.setTypeId(this.idx(), typeId);
      this.getStructWriter(typeId).setPosition(this.data.getOffset(this.idx()));
      return this.getStructWriter(typeId).uInt4(name);
   }

   public UInt4Writer uInt4() {
      byte typeId = this.data.getTypeId(this.idx());
      this.data.setTypeId(this.idx(), typeId);
      this.getListWriter(typeId).setPosition(this.data.getOffset(this.idx()));
      return this.getListWriter(typeId).uInt4();
   }

   public Float4Writer float4(String name) {
      byte typeId = this.data.getTypeId(this.idx());
      this.data.setTypeId(this.idx(), typeId);
      this.getStructWriter(typeId).setPosition(this.data.getOffset(this.idx()));
      return this.getStructWriter(typeId).float4(name);
   }

   public Float4Writer float4() {
      byte typeId = this.data.getTypeId(this.idx());
      this.data.setTypeId(this.idx(), typeId);
      this.getListWriter(typeId).setPosition(this.data.getOffset(this.idx()));
      return this.getListWriter(typeId).float4();
   }

   public DateDayWriter dateDay(String name) {
      byte typeId = this.data.getTypeId(this.idx());
      this.data.setTypeId(this.idx(), typeId);
      this.getStructWriter(typeId).setPosition(this.data.getOffset(this.idx()));
      return this.getStructWriter(typeId).dateDay(name);
   }

   public DateDayWriter dateDay() {
      byte typeId = this.data.getTypeId(this.idx());
      this.data.setTypeId(this.idx(), typeId);
      this.getListWriter(typeId).setPosition(this.data.getOffset(this.idx()));
      return this.getListWriter(typeId).dateDay();
   }

   public IntervalYearWriter intervalYear(String name) {
      byte typeId = this.data.getTypeId(this.idx());
      this.data.setTypeId(this.idx(), typeId);
      this.getStructWriter(typeId).setPosition(this.data.getOffset(this.idx()));
      return this.getStructWriter(typeId).intervalYear(name);
   }

   public IntervalYearWriter intervalYear() {
      byte typeId = this.data.getTypeId(this.idx());
      this.data.setTypeId(this.idx(), typeId);
      this.getListWriter(typeId).setPosition(this.data.getOffset(this.idx()));
      return this.getListWriter(typeId).intervalYear();
   }

   public TimeSecWriter timeSec(String name) {
      byte typeId = this.data.getTypeId(this.idx());
      this.data.setTypeId(this.idx(), typeId);
      this.getStructWriter(typeId).setPosition(this.data.getOffset(this.idx()));
      return this.getStructWriter(typeId).timeSec(name);
   }

   public TimeSecWriter timeSec() {
      byte typeId = this.data.getTypeId(this.idx());
      this.data.setTypeId(this.idx(), typeId);
      this.getListWriter(typeId).setPosition(this.data.getOffset(this.idx()));
      return this.getListWriter(typeId).timeSec();
   }

   public TimeMilliWriter timeMilli(String name) {
      byte typeId = this.data.getTypeId(this.idx());
      this.data.setTypeId(this.idx(), typeId);
      this.getStructWriter(typeId).setPosition(this.data.getOffset(this.idx()));
      return this.getStructWriter(typeId).timeMilli(name);
   }

   public TimeMilliWriter timeMilli() {
      byte typeId = this.data.getTypeId(this.idx());
      this.data.setTypeId(this.idx(), typeId);
      this.getListWriter(typeId).setPosition(this.data.getOffset(this.idx()));
      return this.getListWriter(typeId).timeMilli();
   }

   public BigIntWriter bigInt(String name) {
      byte typeId = this.data.getTypeId(this.idx());
      this.data.setTypeId(this.idx(), typeId);
      this.getStructWriter(typeId).setPosition(this.data.getOffset(this.idx()));
      return this.getStructWriter(typeId).bigInt(name);
   }

   public BigIntWriter bigInt() {
      byte typeId = this.data.getTypeId(this.idx());
      this.data.setTypeId(this.idx(), typeId);
      this.getListWriter(typeId).setPosition(this.data.getOffset(this.idx()));
      return this.getListWriter(typeId).bigInt();
   }

   public UInt8Writer uInt8(String name) {
      byte typeId = this.data.getTypeId(this.idx());
      this.data.setTypeId(this.idx(), typeId);
      this.getStructWriter(typeId).setPosition(this.data.getOffset(this.idx()));
      return this.getStructWriter(typeId).uInt8(name);
   }

   public UInt8Writer uInt8() {
      byte typeId = this.data.getTypeId(this.idx());
      this.data.setTypeId(this.idx(), typeId);
      this.getListWriter(typeId).setPosition(this.data.getOffset(this.idx()));
      return this.getListWriter(typeId).uInt8();
   }

   public Float8Writer float8(String name) {
      byte typeId = this.data.getTypeId(this.idx());
      this.data.setTypeId(this.idx(), typeId);
      this.getStructWriter(typeId).setPosition(this.data.getOffset(this.idx()));
      return this.getStructWriter(typeId).float8(name);
   }

   public Float8Writer float8() {
      byte typeId = this.data.getTypeId(this.idx());
      this.data.setTypeId(this.idx(), typeId);
      this.getListWriter(typeId).setPosition(this.data.getOffset(this.idx()));
      return this.getListWriter(typeId).float8();
   }

   public DateMilliWriter dateMilli(String name) {
      byte typeId = this.data.getTypeId(this.idx());
      this.data.setTypeId(this.idx(), typeId);
      this.getStructWriter(typeId).setPosition(this.data.getOffset(this.idx()));
      return this.getStructWriter(typeId).dateMilli(name);
   }

   public DateMilliWriter dateMilli() {
      byte typeId = this.data.getTypeId(this.idx());
      this.data.setTypeId(this.idx(), typeId);
      this.getListWriter(typeId).setPosition(this.data.getOffset(this.idx()));
      return this.getListWriter(typeId).dateMilli();
   }

   public TimeStampSecWriter timeStampSec(String name) {
      byte typeId = this.data.getTypeId(this.idx());
      this.data.setTypeId(this.idx(), typeId);
      this.getStructWriter(typeId).setPosition(this.data.getOffset(this.idx()));
      return this.getStructWriter(typeId).timeStampSec(name);
   }

   public TimeStampSecWriter timeStampSec() {
      byte typeId = this.data.getTypeId(this.idx());
      this.data.setTypeId(this.idx(), typeId);
      this.getListWriter(typeId).setPosition(this.data.getOffset(this.idx()));
      return this.getListWriter(typeId).timeStampSec();
   }

   public TimeStampMilliWriter timeStampMilli(String name) {
      byte typeId = this.data.getTypeId(this.idx());
      this.data.setTypeId(this.idx(), typeId);
      this.getStructWriter(typeId).setPosition(this.data.getOffset(this.idx()));
      return this.getStructWriter(typeId).timeStampMilli(name);
   }

   public TimeStampMilliWriter timeStampMilli() {
      byte typeId = this.data.getTypeId(this.idx());
      this.data.setTypeId(this.idx(), typeId);
      this.getListWriter(typeId).setPosition(this.data.getOffset(this.idx()));
      return this.getListWriter(typeId).timeStampMilli();
   }

   public TimeStampMicroWriter timeStampMicro(String name) {
      byte typeId = this.data.getTypeId(this.idx());
      this.data.setTypeId(this.idx(), typeId);
      this.getStructWriter(typeId).setPosition(this.data.getOffset(this.idx()));
      return this.getStructWriter(typeId).timeStampMicro(name);
   }

   public TimeStampMicroWriter timeStampMicro() {
      byte typeId = this.data.getTypeId(this.idx());
      this.data.setTypeId(this.idx(), typeId);
      this.getListWriter(typeId).setPosition(this.data.getOffset(this.idx()));
      return this.getListWriter(typeId).timeStampMicro();
   }

   public TimeStampNanoWriter timeStampNano(String name) {
      byte typeId = this.data.getTypeId(this.idx());
      this.data.setTypeId(this.idx(), typeId);
      this.getStructWriter(typeId).setPosition(this.data.getOffset(this.idx()));
      return this.getStructWriter(typeId).timeStampNano(name);
   }

   public TimeStampNanoWriter timeStampNano() {
      byte typeId = this.data.getTypeId(this.idx());
      this.data.setTypeId(this.idx(), typeId);
      this.getListWriter(typeId).setPosition(this.data.getOffset(this.idx()));
      return this.getListWriter(typeId).timeStampNano();
   }

   public TimeMicroWriter timeMicro(String name) {
      byte typeId = this.data.getTypeId(this.idx());
      this.data.setTypeId(this.idx(), typeId);
      this.getStructWriter(typeId).setPosition(this.data.getOffset(this.idx()));
      return this.getStructWriter(typeId).timeMicro(name);
   }

   public TimeMicroWriter timeMicro() {
      byte typeId = this.data.getTypeId(this.idx());
      this.data.setTypeId(this.idx(), typeId);
      this.getListWriter(typeId).setPosition(this.data.getOffset(this.idx()));
      return this.getListWriter(typeId).timeMicro();
   }

   public TimeNanoWriter timeNano(String name) {
      byte typeId = this.data.getTypeId(this.idx());
      this.data.setTypeId(this.idx(), typeId);
      this.getStructWriter(typeId).setPosition(this.data.getOffset(this.idx()));
      return this.getStructWriter(typeId).timeNano(name);
   }

   public TimeNanoWriter timeNano() {
      byte typeId = this.data.getTypeId(this.idx());
      this.data.setTypeId(this.idx(), typeId);
      this.getListWriter(typeId).setPosition(this.data.getOffset(this.idx()));
      return this.getListWriter(typeId).timeNano();
   }

   public IntervalDayWriter intervalDay(String name) {
      byte typeId = this.data.getTypeId(this.idx());
      this.data.setTypeId(this.idx(), typeId);
      this.getStructWriter(typeId).setPosition(this.data.getOffset(this.idx()));
      return this.getStructWriter(typeId).intervalDay(name);
   }

   public IntervalDayWriter intervalDay() {
      byte typeId = this.data.getTypeId(this.idx());
      this.data.setTypeId(this.idx(), typeId);
      this.getListWriter(typeId).setPosition(this.data.getOffset(this.idx()));
      return this.getListWriter(typeId).intervalDay();
   }

   public IntervalMonthDayNanoWriter intervalMonthDayNano(String name) {
      byte typeId = this.data.getTypeId(this.idx());
      this.data.setTypeId(this.idx(), typeId);
      this.getStructWriter(typeId).setPosition(this.data.getOffset(this.idx()));
      return this.getStructWriter(typeId).intervalMonthDayNano(name);
   }

   public IntervalMonthDayNanoWriter intervalMonthDayNano() {
      byte typeId = this.data.getTypeId(this.idx());
      this.data.setTypeId(this.idx(), typeId);
      this.getListWriter(typeId).setPosition(this.data.getOffset(this.idx()));
      return this.getListWriter(typeId).intervalMonthDayNano();
   }

   public Decimal256Writer decimal256(String name) {
      byte typeId = this.data.getTypeId(this.idx());
      this.data.setTypeId(this.idx(), typeId);
      this.getStructWriter(typeId).setPosition(this.data.getOffset(this.idx()));
      return this.getStructWriter(typeId).decimal256(name);
   }

   public Decimal256Writer decimal256() {
      byte typeId = this.data.getTypeId(this.idx());
      this.data.setTypeId(this.idx(), typeId);
      this.getListWriter(typeId).setPosition(this.data.getOffset(this.idx()));
      return this.getListWriter(typeId).decimal256();
   }

   public Decimal256Writer decimal256(String name, int scale, int precision) {
      byte typeId = this.data.getTypeId(this.idx());
      this.data.setTypeId(this.idx(), typeId);
      this.getStructWriter(typeId).setPosition(this.data.getOffset(this.idx()));
      return this.getStructWriter(typeId).decimal256(name, scale, precision);
   }

   public DecimalWriter decimal(String name) {
      byte typeId = this.data.getTypeId(this.idx());
      this.data.setTypeId(this.idx(), typeId);
      this.getStructWriter(typeId).setPosition(this.data.getOffset(this.idx()));
      return this.getStructWriter(typeId).decimal(name);
   }

   public DecimalWriter decimal() {
      byte typeId = this.data.getTypeId(this.idx());
      this.data.setTypeId(this.idx(), typeId);
      this.getListWriter(typeId).setPosition(this.data.getOffset(this.idx()));
      return this.getListWriter(typeId).decimal();
   }

   public DecimalWriter decimal(String name, int scale, int precision) {
      byte typeId = this.data.getTypeId(this.idx());
      this.data.setTypeId(this.idx(), typeId);
      this.getStructWriter(typeId).setPosition(this.data.getOffset(this.idx()));
      return this.getStructWriter(typeId).decimal(name, scale, precision);
   }

   public VarBinaryWriter varBinary(String name) {
      byte typeId = this.data.getTypeId(this.idx());
      this.data.setTypeId(this.idx(), typeId);
      this.getStructWriter(typeId).setPosition(this.data.getOffset(this.idx()));
      return this.getStructWriter(typeId).varBinary(name);
   }

   public VarBinaryWriter varBinary() {
      byte typeId = this.data.getTypeId(this.idx());
      this.data.setTypeId(this.idx(), typeId);
      this.getListWriter(typeId).setPosition(this.data.getOffset(this.idx()));
      return this.getListWriter(typeId).varBinary();
   }

   public VarCharWriter varChar(String name) {
      byte typeId = this.data.getTypeId(this.idx());
      this.data.setTypeId(this.idx(), typeId);
      this.getStructWriter(typeId).setPosition(this.data.getOffset(this.idx()));
      return this.getStructWriter(typeId).varChar(name);
   }

   public VarCharWriter varChar() {
      byte typeId = this.data.getTypeId(this.idx());
      this.data.setTypeId(this.idx(), typeId);
      this.getListWriter(typeId).setPosition(this.data.getOffset(this.idx()));
      return this.getListWriter(typeId).varChar();
   }

   public ViewVarBinaryWriter viewVarBinary(String name) {
      byte typeId = this.data.getTypeId(this.idx());
      this.data.setTypeId(this.idx(), typeId);
      this.getStructWriter(typeId).setPosition(this.data.getOffset(this.idx()));
      return this.getStructWriter(typeId).viewVarBinary(name);
   }

   public ViewVarBinaryWriter viewVarBinary() {
      byte typeId = this.data.getTypeId(this.idx());
      this.data.setTypeId(this.idx(), typeId);
      this.getListWriter(typeId).setPosition(this.data.getOffset(this.idx()));
      return this.getListWriter(typeId).viewVarBinary();
   }

   public ViewVarCharWriter viewVarChar(String name) {
      byte typeId = this.data.getTypeId(this.idx());
      this.data.setTypeId(this.idx(), typeId);
      this.getStructWriter(typeId).setPosition(this.data.getOffset(this.idx()));
      return this.getStructWriter(typeId).viewVarChar(name);
   }

   public ViewVarCharWriter viewVarChar() {
      byte typeId = this.data.getTypeId(this.idx());
      this.data.setTypeId(this.idx(), typeId);
      this.getListWriter(typeId).setPosition(this.data.getOffset(this.idx()));
      return this.getListWriter(typeId).viewVarChar();
   }

   public LargeVarCharWriter largeVarChar(String name) {
      byte typeId = this.data.getTypeId(this.idx());
      this.data.setTypeId(this.idx(), typeId);
      this.getStructWriter(typeId).setPosition(this.data.getOffset(this.idx()));
      return this.getStructWriter(typeId).largeVarChar(name);
   }

   public LargeVarCharWriter largeVarChar() {
      byte typeId = this.data.getTypeId(this.idx());
      this.data.setTypeId(this.idx(), typeId);
      this.getListWriter(typeId).setPosition(this.data.getOffset(this.idx()));
      return this.getListWriter(typeId).largeVarChar();
   }

   public LargeVarBinaryWriter largeVarBinary(String name) {
      byte typeId = this.data.getTypeId(this.idx());
      this.data.setTypeId(this.idx(), typeId);
      this.getStructWriter(typeId).setPosition(this.data.getOffset(this.idx()));
      return this.getStructWriter(typeId).largeVarBinary(name);
   }

   public LargeVarBinaryWriter largeVarBinary() {
      byte typeId = this.data.getTypeId(this.idx());
      this.data.setTypeId(this.idx(), typeId);
      this.getListWriter(typeId).setPosition(this.data.getOffset(this.idx()));
      return this.getListWriter(typeId).largeVarBinary();
   }

   public BitWriter bit(String name) {
      byte typeId = this.data.getTypeId(this.idx());
      this.data.setTypeId(this.idx(), typeId);
      this.getStructWriter(typeId).setPosition(this.data.getOffset(this.idx()));
      return this.getStructWriter(typeId).bit(name);
   }

   public BitWriter bit() {
      byte typeId = this.data.getTypeId(this.idx());
      this.data.setTypeId(this.idx(), typeId);
      this.getListWriter(typeId).setPosition(this.data.getOffset(this.idx()));
      return this.getListWriter(typeId).bit();
   }

   public void allocate() {
      this.data.allocateNew();
   }

   public void clear() {
      this.data.clear();
   }

   public void close() throws Exception {
      this.data.close();
   }

   public Field getField() {
      return this.data.getField();
   }

   public int getValueCapacity() {
      return this.data.getValueCapacity();
   }
}
