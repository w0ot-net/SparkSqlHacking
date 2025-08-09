package org.apache.arrow.vector.complex.impl;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Iterator;
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
import org.apache.arrow.vector.PeriodDuration;
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
import org.apache.arrow.vector.complex.MapVector;
import org.apache.arrow.vector.complex.reader.BaseReader;
import org.apache.arrow.vector.complex.reader.FieldReader;
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
import org.apache.arrow.vector.holders.DenseUnionHolder;
import org.apache.arrow.vector.holders.NullableBigIntHolder;
import org.apache.arrow.vector.holders.NullableBitHolder;
import org.apache.arrow.vector.holders.NullableDateDayHolder;
import org.apache.arrow.vector.holders.NullableDateMilliHolder;
import org.apache.arrow.vector.holders.NullableDecimal256Holder;
import org.apache.arrow.vector.holders.NullableDecimalHolder;
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
import org.apache.arrow.vector.holders.NullableTimeStampMilliHolder;
import org.apache.arrow.vector.holders.NullableTimeStampNanoHolder;
import org.apache.arrow.vector.holders.NullableTimeStampSecHolder;
import org.apache.arrow.vector.holders.NullableTinyIntHolder;
import org.apache.arrow.vector.holders.NullableUInt1Holder;
import org.apache.arrow.vector.holders.NullableUInt2Holder;
import org.apache.arrow.vector.holders.NullableUInt4Holder;
import org.apache.arrow.vector.holders.NullableUInt8Holder;
import org.apache.arrow.vector.holders.NullableVarBinaryHolder;
import org.apache.arrow.vector.holders.NullableVarCharHolder;
import org.apache.arrow.vector.holders.NullableViewVarBinaryHolder;
import org.apache.arrow.vector.holders.NullableViewVarCharHolder;
import org.apache.arrow.vector.holders.UnionHolder;
import org.apache.arrow.vector.types.Types;
import org.apache.arrow.vector.types.pojo.Field;
import org.apache.arrow.vector.util.Text;

public class DenseUnionReader extends AbstractFieldReader {
   private BaseReader[] readers = new BaseReader[128];
   public DenseUnionVector data;
   private SingleStructReaderImpl structReader;
   private UnionListReader listReader;
   private UnionMapReader mapReader;

   public DenseUnionReader(DenseUnionVector data) {
      this.data = data;
   }

   public Types.MinorType getMinorType() {
      byte typeId = this.data.getTypeId(this.idx());
      return this.data.getVectorByType(typeId).getMinorType();
   }

   public byte getTypeId() {
      return this.data.getTypeId(this.idx());
   }

   public Field getField() {
      return this.data.getField();
   }

   public boolean isSet() {
      return !this.data.isNull(this.idx());
   }

   public void read(DenseUnionHolder holder) {
      holder.reader = this;
      holder.isSet = this.isSet() ? 1 : 0;
      holder.typeId = this.getTypeId();
   }

   public void read(int index, UnionHolder holder) {
      byte typeId = this.data.getTypeId(index);
      this.getList(typeId).read(index, holder);
   }

   private FieldReader getReaderForIndex(int index) {
      byte typeId = this.data.getTypeId(index);
      Types.MinorType minorType = this.data.getVectorByType(typeId).getMinorType();
      FieldReader reader = (FieldReader)this.readers[typeId];
      if (reader != null) {
         return reader;
      } else {
         switch (minorType) {
            case NULL:
               reader = NullReader.INSTANCE;
               break;
            case STRUCT:
               reader = (FieldReader)this.getStruct(typeId);
               break;
            case LIST:
               reader = this.getList(typeId);
               break;
            case TINYINT:
               reader = this.getTinyInt(typeId);
               break;
            case UINT1:
               reader = this.getUInt1(typeId);
               break;
            case UINT2:
               reader = this.getUInt2(typeId);
               break;
            case SMALLINT:
               reader = this.getSmallInt(typeId);
               break;
            case FLOAT2:
               reader = this.getFloat2(typeId);
               break;
            case INT:
               reader = this.getInt(typeId);
               break;
            case UINT4:
               reader = this.getUInt4(typeId);
               break;
            case FLOAT4:
               reader = this.getFloat4(typeId);
               break;
            case DATEDAY:
               reader = this.getDateDay(typeId);
               break;
            case INTERVALYEAR:
               reader = this.getIntervalYear(typeId);
               break;
            case TIMESEC:
               reader = this.getTimeSec(typeId);
               break;
            case TIMEMILLI:
               reader = this.getTimeMilli(typeId);
               break;
            case BIGINT:
               reader = this.getBigInt(typeId);
               break;
            case UINT8:
               reader = this.getUInt8(typeId);
               break;
            case FLOAT8:
               reader = this.getFloat8(typeId);
               break;
            case DATEMILLI:
               reader = this.getDateMilli(typeId);
               break;
            case TIMESTAMPSEC:
               reader = this.getTimeStampSec(typeId);
               break;
            case TIMESTAMPMILLI:
               reader = this.getTimeStampMilli(typeId);
               break;
            case TIMESTAMPMICRO:
               reader = this.getTimeStampMicro(typeId);
               break;
            case TIMESTAMPNANO:
               reader = this.getTimeStampNano(typeId);
               break;
            case TIMEMICRO:
               reader = this.getTimeMicro(typeId);
               break;
            case TIMENANO:
               reader = this.getTimeNano(typeId);
               break;
            case INTERVALDAY:
               reader = this.getIntervalDay(typeId);
               break;
            case INTERVALMONTHDAYNANO:
               reader = this.getIntervalMonthDayNano(typeId);
               break;
            case DECIMAL256:
               reader = this.getDecimal256(typeId);
               break;
            case DECIMAL:
               reader = this.getDecimal(typeId);
               break;
            case VARBINARY:
               reader = this.getVarBinary(typeId);
               break;
            case VARCHAR:
               reader = this.getVarChar(typeId);
               break;
            case VIEWVARBINARY:
               reader = this.getViewVarBinary(typeId);
               break;
            case VIEWVARCHAR:
               reader = this.getViewVarChar(typeId);
               break;
            case LARGEVARCHAR:
               reader = this.getLargeVarChar(typeId);
               break;
            case LARGEVARBINARY:
               reader = this.getLargeVarBinary(typeId);
               break;
            case BIT:
               reader = this.getBit(typeId);
               break;
            default:
               String var10002 = String.valueOf(Types.MinorType.values()[typeId]);
               throw new UnsupportedOperationException("Unsupported type: " + var10002);
         }

         return reader;
      }
   }

   private BaseReader.StructReader getStruct(byte typeId) {
      BaseReader.StructReader structReader = (BaseReader.StructReader)this.readers[typeId];
      if (structReader == null) {
         structReader = (SingleStructReaderImpl)this.data.getVectorByType(typeId).getReader();
         structReader.setPosition(this.idx());
         this.readers[typeId] = structReader;
      }

      return structReader;
   }

   private FieldReader getList(byte typeId) {
      UnionListReader listReader = (UnionListReader)this.readers[typeId];
      if (listReader == null) {
         listReader = new UnionListReader((ListVector)this.data.getVectorByType(typeId));
         listReader.setPosition(this.idx());
         this.readers[typeId] = listReader;
      }

      return listReader;
   }

   private FieldReader getMap(byte typeId) {
      UnionMapReader mapReader = (UnionMapReader)this.readers[typeId];
      if (mapReader == null) {
         mapReader = new UnionMapReader((MapVector)this.data.getVectorByType(typeId));
         mapReader.setPosition(this.idx());
         this.readers[typeId] = mapReader;
      }

      return mapReader;
   }

   public Iterator iterator() {
      throw new UnsupportedOperationException();
   }

   public void copyAsValue(UnionWriter writer) {
      writer.data.copyFrom(this.idx(), writer.idx(), this.data);
   }

   public Object readObject() {
      return this.getReaderForIndex(this.idx()).readObject();
   }

   public BigDecimal readBigDecimal() {
      return this.getReaderForIndex(this.idx()).readBigDecimal();
   }

   public Short readShort() {
      return this.getReaderForIndex(this.idx()).readShort();
   }

   public Integer readInteger() {
      return this.getReaderForIndex(this.idx()).readInteger();
   }

   public Long readLong() {
      return this.getReaderForIndex(this.idx()).readLong();
   }

   public Boolean readBoolean() {
      return this.getReaderForIndex(this.idx()).readBoolean();
   }

   public LocalDateTime readLocalDateTime() {
      return this.getReaderForIndex(this.idx()).readLocalDateTime();
   }

   public Duration readDuration() {
      return this.getReaderForIndex(this.idx()).readDuration();
   }

   public Period readPeriod() {
      return this.getReaderForIndex(this.idx()).readPeriod();
   }

   public Double readDouble() {
      return this.getReaderForIndex(this.idx()).readDouble();
   }

   public Float readFloat() {
      return this.getReaderForIndex(this.idx()).readFloat();
   }

   public Character readCharacter() {
      return this.getReaderForIndex(this.idx()).readCharacter();
   }

   public Text readText() {
      return this.getReaderForIndex(this.idx()).readText();
   }

   public Byte readByte() {
      return this.getReaderForIndex(this.idx()).readByte();
   }

   public byte[] readByteArray() {
      return this.getReaderForIndex(this.idx()).readByteArray();
   }

   public PeriodDuration readPeriodDuration() {
      return this.getReaderForIndex(this.idx()).readPeriodDuration();
   }

   public int size() {
      return this.getReaderForIndex(this.idx()).size();
   }

   private TinyIntReaderImpl getTinyInt(byte typeId) {
      TinyIntReaderImpl reader = (TinyIntReaderImpl)this.readers[typeId];
      if (reader == null) {
         reader = new TinyIntReaderImpl((TinyIntVector)this.data.getVectorByType(typeId));
         reader.setPosition(this.idx());
         this.readers[typeId] = reader;
      }

      return reader;
   }

   public void read(NullableTinyIntHolder holder) {
      this.getReaderForIndex(this.idx()).read(holder);
   }

   public void copyAsValue(TinyIntWriter writer) {
      this.getReaderForIndex(this.idx()).copyAsValue(writer);
   }

   private UInt1ReaderImpl getUInt1(byte typeId) {
      UInt1ReaderImpl reader = (UInt1ReaderImpl)this.readers[typeId];
      if (reader == null) {
         reader = new UInt1ReaderImpl((UInt1Vector)this.data.getVectorByType(typeId));
         reader.setPosition(this.idx());
         this.readers[typeId] = reader;
      }

      return reader;
   }

   public void read(NullableUInt1Holder holder) {
      this.getReaderForIndex(this.idx()).read(holder);
   }

   public void copyAsValue(UInt1Writer writer) {
      this.getReaderForIndex(this.idx()).copyAsValue(writer);
   }

   private UInt2ReaderImpl getUInt2(byte typeId) {
      UInt2ReaderImpl reader = (UInt2ReaderImpl)this.readers[typeId];
      if (reader == null) {
         reader = new UInt2ReaderImpl((UInt2Vector)this.data.getVectorByType(typeId));
         reader.setPosition(this.idx());
         this.readers[typeId] = reader;
      }

      return reader;
   }

   public void read(NullableUInt2Holder holder) {
      this.getReaderForIndex(this.idx()).read(holder);
   }

   public void copyAsValue(UInt2Writer writer) {
      this.getReaderForIndex(this.idx()).copyAsValue(writer);
   }

   private SmallIntReaderImpl getSmallInt(byte typeId) {
      SmallIntReaderImpl reader = (SmallIntReaderImpl)this.readers[typeId];
      if (reader == null) {
         reader = new SmallIntReaderImpl((SmallIntVector)this.data.getVectorByType(typeId));
         reader.setPosition(this.idx());
         this.readers[typeId] = reader;
      }

      return reader;
   }

   public void read(NullableSmallIntHolder holder) {
      this.getReaderForIndex(this.idx()).read(holder);
   }

   public void copyAsValue(SmallIntWriter writer) {
      this.getReaderForIndex(this.idx()).copyAsValue(writer);
   }

   private Float2ReaderImpl getFloat2(byte typeId) {
      Float2ReaderImpl reader = (Float2ReaderImpl)this.readers[typeId];
      if (reader == null) {
         reader = new Float2ReaderImpl((Float2Vector)this.data.getVectorByType(typeId));
         reader.setPosition(this.idx());
         this.readers[typeId] = reader;
      }

      return reader;
   }

   public void read(NullableFloat2Holder holder) {
      this.getReaderForIndex(this.idx()).read(holder);
   }

   public void copyAsValue(Float2Writer writer) {
      this.getReaderForIndex(this.idx()).copyAsValue(writer);
   }

   private IntReaderImpl getInt(byte typeId) {
      IntReaderImpl reader = (IntReaderImpl)this.readers[typeId];
      if (reader == null) {
         reader = new IntReaderImpl((IntVector)this.data.getVectorByType(typeId));
         reader.setPosition(this.idx());
         this.readers[typeId] = reader;
      }

      return reader;
   }

   public void read(NullableIntHolder holder) {
      this.getReaderForIndex(this.idx()).read(holder);
   }

   public void copyAsValue(IntWriter writer) {
      this.getReaderForIndex(this.idx()).copyAsValue(writer);
   }

   private UInt4ReaderImpl getUInt4(byte typeId) {
      UInt4ReaderImpl reader = (UInt4ReaderImpl)this.readers[typeId];
      if (reader == null) {
         reader = new UInt4ReaderImpl((UInt4Vector)this.data.getVectorByType(typeId));
         reader.setPosition(this.idx());
         this.readers[typeId] = reader;
      }

      return reader;
   }

   public void read(NullableUInt4Holder holder) {
      this.getReaderForIndex(this.idx()).read(holder);
   }

   public void copyAsValue(UInt4Writer writer) {
      this.getReaderForIndex(this.idx()).copyAsValue(writer);
   }

   private Float4ReaderImpl getFloat4(byte typeId) {
      Float4ReaderImpl reader = (Float4ReaderImpl)this.readers[typeId];
      if (reader == null) {
         reader = new Float4ReaderImpl((Float4Vector)this.data.getVectorByType(typeId));
         reader.setPosition(this.idx());
         this.readers[typeId] = reader;
      }

      return reader;
   }

   public void read(NullableFloat4Holder holder) {
      this.getReaderForIndex(this.idx()).read(holder);
   }

   public void copyAsValue(Float4Writer writer) {
      this.getReaderForIndex(this.idx()).copyAsValue(writer);
   }

   private DateDayReaderImpl getDateDay(byte typeId) {
      DateDayReaderImpl reader = (DateDayReaderImpl)this.readers[typeId];
      if (reader == null) {
         reader = new DateDayReaderImpl((DateDayVector)this.data.getVectorByType(typeId));
         reader.setPosition(this.idx());
         this.readers[typeId] = reader;
      }

      return reader;
   }

   public void read(NullableDateDayHolder holder) {
      this.getReaderForIndex(this.idx()).read(holder);
   }

   public void copyAsValue(DateDayWriter writer) {
      this.getReaderForIndex(this.idx()).copyAsValue(writer);
   }

   private IntervalYearReaderImpl getIntervalYear(byte typeId) {
      IntervalYearReaderImpl reader = (IntervalYearReaderImpl)this.readers[typeId];
      if (reader == null) {
         reader = new IntervalYearReaderImpl((IntervalYearVector)this.data.getVectorByType(typeId));
         reader.setPosition(this.idx());
         this.readers[typeId] = reader;
      }

      return reader;
   }

   public void read(NullableIntervalYearHolder holder) {
      this.getReaderForIndex(this.idx()).read(holder);
   }

   public void copyAsValue(IntervalYearWriter writer) {
      this.getReaderForIndex(this.idx()).copyAsValue(writer);
   }

   private TimeSecReaderImpl getTimeSec(byte typeId) {
      TimeSecReaderImpl reader = (TimeSecReaderImpl)this.readers[typeId];
      if (reader == null) {
         reader = new TimeSecReaderImpl((TimeSecVector)this.data.getVectorByType(typeId));
         reader.setPosition(this.idx());
         this.readers[typeId] = reader;
      }

      return reader;
   }

   public void read(NullableTimeSecHolder holder) {
      this.getReaderForIndex(this.idx()).read(holder);
   }

   public void copyAsValue(TimeSecWriter writer) {
      this.getReaderForIndex(this.idx()).copyAsValue(writer);
   }

   private TimeMilliReaderImpl getTimeMilli(byte typeId) {
      TimeMilliReaderImpl reader = (TimeMilliReaderImpl)this.readers[typeId];
      if (reader == null) {
         reader = new TimeMilliReaderImpl((TimeMilliVector)this.data.getVectorByType(typeId));
         reader.setPosition(this.idx());
         this.readers[typeId] = reader;
      }

      return reader;
   }

   public void read(NullableTimeMilliHolder holder) {
      this.getReaderForIndex(this.idx()).read(holder);
   }

   public void copyAsValue(TimeMilliWriter writer) {
      this.getReaderForIndex(this.idx()).copyAsValue(writer);
   }

   private BigIntReaderImpl getBigInt(byte typeId) {
      BigIntReaderImpl reader = (BigIntReaderImpl)this.readers[typeId];
      if (reader == null) {
         reader = new BigIntReaderImpl((BigIntVector)this.data.getVectorByType(typeId));
         reader.setPosition(this.idx());
         this.readers[typeId] = reader;
      }

      return reader;
   }

   public void read(NullableBigIntHolder holder) {
      this.getReaderForIndex(this.idx()).read(holder);
   }

   public void copyAsValue(BigIntWriter writer) {
      this.getReaderForIndex(this.idx()).copyAsValue(writer);
   }

   private UInt8ReaderImpl getUInt8(byte typeId) {
      UInt8ReaderImpl reader = (UInt8ReaderImpl)this.readers[typeId];
      if (reader == null) {
         reader = new UInt8ReaderImpl((UInt8Vector)this.data.getVectorByType(typeId));
         reader.setPosition(this.idx());
         this.readers[typeId] = reader;
      }

      return reader;
   }

   public void read(NullableUInt8Holder holder) {
      this.getReaderForIndex(this.idx()).read(holder);
   }

   public void copyAsValue(UInt8Writer writer) {
      this.getReaderForIndex(this.idx()).copyAsValue(writer);
   }

   private Float8ReaderImpl getFloat8(byte typeId) {
      Float8ReaderImpl reader = (Float8ReaderImpl)this.readers[typeId];
      if (reader == null) {
         reader = new Float8ReaderImpl((Float8Vector)this.data.getVectorByType(typeId));
         reader.setPosition(this.idx());
         this.readers[typeId] = reader;
      }

      return reader;
   }

   public void read(NullableFloat8Holder holder) {
      this.getReaderForIndex(this.idx()).read(holder);
   }

   public void copyAsValue(Float8Writer writer) {
      this.getReaderForIndex(this.idx()).copyAsValue(writer);
   }

   private DateMilliReaderImpl getDateMilli(byte typeId) {
      DateMilliReaderImpl reader = (DateMilliReaderImpl)this.readers[typeId];
      if (reader == null) {
         reader = new DateMilliReaderImpl((DateMilliVector)this.data.getVectorByType(typeId));
         reader.setPosition(this.idx());
         this.readers[typeId] = reader;
      }

      return reader;
   }

   public void read(NullableDateMilliHolder holder) {
      this.getReaderForIndex(this.idx()).read(holder);
   }

   public void copyAsValue(DateMilliWriter writer) {
      this.getReaderForIndex(this.idx()).copyAsValue(writer);
   }

   private TimeStampSecReaderImpl getTimeStampSec(byte typeId) {
      TimeStampSecReaderImpl reader = (TimeStampSecReaderImpl)this.readers[typeId];
      if (reader == null) {
         reader = new TimeStampSecReaderImpl((TimeStampSecVector)this.data.getVectorByType(typeId));
         reader.setPosition(this.idx());
         this.readers[typeId] = reader;
      }

      return reader;
   }

   public void read(NullableTimeStampSecHolder holder) {
      this.getReaderForIndex(this.idx()).read(holder);
   }

   public void copyAsValue(TimeStampSecWriter writer) {
      this.getReaderForIndex(this.idx()).copyAsValue(writer);
   }

   private TimeStampMilliReaderImpl getTimeStampMilli(byte typeId) {
      TimeStampMilliReaderImpl reader = (TimeStampMilliReaderImpl)this.readers[typeId];
      if (reader == null) {
         reader = new TimeStampMilliReaderImpl((TimeStampMilliVector)this.data.getVectorByType(typeId));
         reader.setPosition(this.idx());
         this.readers[typeId] = reader;
      }

      return reader;
   }

   public void read(NullableTimeStampMilliHolder holder) {
      this.getReaderForIndex(this.idx()).read(holder);
   }

   public void copyAsValue(TimeStampMilliWriter writer) {
      this.getReaderForIndex(this.idx()).copyAsValue(writer);
   }

   private TimeStampMicroReaderImpl getTimeStampMicro(byte typeId) {
      TimeStampMicroReaderImpl reader = (TimeStampMicroReaderImpl)this.readers[typeId];
      if (reader == null) {
         reader = new TimeStampMicroReaderImpl((TimeStampMicroVector)this.data.getVectorByType(typeId));
         reader.setPosition(this.idx());
         this.readers[typeId] = reader;
      }

      return reader;
   }

   public void read(NullableTimeStampMicroHolder holder) {
      this.getReaderForIndex(this.idx()).read(holder);
   }

   public void copyAsValue(TimeStampMicroWriter writer) {
      this.getReaderForIndex(this.idx()).copyAsValue(writer);
   }

   private TimeStampNanoReaderImpl getTimeStampNano(byte typeId) {
      TimeStampNanoReaderImpl reader = (TimeStampNanoReaderImpl)this.readers[typeId];
      if (reader == null) {
         reader = new TimeStampNanoReaderImpl((TimeStampNanoVector)this.data.getVectorByType(typeId));
         reader.setPosition(this.idx());
         this.readers[typeId] = reader;
      }

      return reader;
   }

   public void read(NullableTimeStampNanoHolder holder) {
      this.getReaderForIndex(this.idx()).read(holder);
   }

   public void copyAsValue(TimeStampNanoWriter writer) {
      this.getReaderForIndex(this.idx()).copyAsValue(writer);
   }

   private TimeMicroReaderImpl getTimeMicro(byte typeId) {
      TimeMicroReaderImpl reader = (TimeMicroReaderImpl)this.readers[typeId];
      if (reader == null) {
         reader = new TimeMicroReaderImpl((TimeMicroVector)this.data.getVectorByType(typeId));
         reader.setPosition(this.idx());
         this.readers[typeId] = reader;
      }

      return reader;
   }

   public void read(NullableTimeMicroHolder holder) {
      this.getReaderForIndex(this.idx()).read(holder);
   }

   public void copyAsValue(TimeMicroWriter writer) {
      this.getReaderForIndex(this.idx()).copyAsValue(writer);
   }

   private TimeNanoReaderImpl getTimeNano(byte typeId) {
      TimeNanoReaderImpl reader = (TimeNanoReaderImpl)this.readers[typeId];
      if (reader == null) {
         reader = new TimeNanoReaderImpl((TimeNanoVector)this.data.getVectorByType(typeId));
         reader.setPosition(this.idx());
         this.readers[typeId] = reader;
      }

      return reader;
   }

   public void read(NullableTimeNanoHolder holder) {
      this.getReaderForIndex(this.idx()).read(holder);
   }

   public void copyAsValue(TimeNanoWriter writer) {
      this.getReaderForIndex(this.idx()).copyAsValue(writer);
   }

   private IntervalDayReaderImpl getIntervalDay(byte typeId) {
      IntervalDayReaderImpl reader = (IntervalDayReaderImpl)this.readers[typeId];
      if (reader == null) {
         reader = new IntervalDayReaderImpl((IntervalDayVector)this.data.getVectorByType(typeId));
         reader.setPosition(this.idx());
         this.readers[typeId] = reader;
      }

      return reader;
   }

   public void read(NullableIntervalDayHolder holder) {
      this.getReaderForIndex(this.idx()).read(holder);
   }

   public void copyAsValue(IntervalDayWriter writer) {
      this.getReaderForIndex(this.idx()).copyAsValue(writer);
   }

   private IntervalMonthDayNanoReaderImpl getIntervalMonthDayNano(byte typeId) {
      IntervalMonthDayNanoReaderImpl reader = (IntervalMonthDayNanoReaderImpl)this.readers[typeId];
      if (reader == null) {
         reader = new IntervalMonthDayNanoReaderImpl((IntervalMonthDayNanoVector)this.data.getVectorByType(typeId));
         reader.setPosition(this.idx());
         this.readers[typeId] = reader;
      }

      return reader;
   }

   public void read(NullableIntervalMonthDayNanoHolder holder) {
      this.getReaderForIndex(this.idx()).read(holder);
   }

   public void copyAsValue(IntervalMonthDayNanoWriter writer) {
      this.getReaderForIndex(this.idx()).copyAsValue(writer);
   }

   private Decimal256ReaderImpl getDecimal256(byte typeId) {
      Decimal256ReaderImpl reader = (Decimal256ReaderImpl)this.readers[typeId];
      if (reader == null) {
         reader = new Decimal256ReaderImpl((Decimal256Vector)this.data.getVectorByType(typeId));
         reader.setPosition(this.idx());
         this.readers[typeId] = reader;
      }

      return reader;
   }

   public void read(NullableDecimal256Holder holder) {
      this.getReaderForIndex(this.idx()).read(holder);
   }

   public void copyAsValue(Decimal256Writer writer) {
      this.getReaderForIndex(this.idx()).copyAsValue(writer);
   }

   private DecimalReaderImpl getDecimal(byte typeId) {
      DecimalReaderImpl reader = (DecimalReaderImpl)this.readers[typeId];
      if (reader == null) {
         reader = new DecimalReaderImpl((DecimalVector)this.data.getVectorByType(typeId));
         reader.setPosition(this.idx());
         this.readers[typeId] = reader;
      }

      return reader;
   }

   public void read(NullableDecimalHolder holder) {
      this.getReaderForIndex(this.idx()).read(holder);
   }

   public void copyAsValue(DecimalWriter writer) {
      this.getReaderForIndex(this.idx()).copyAsValue(writer);
   }

   private VarBinaryReaderImpl getVarBinary(byte typeId) {
      VarBinaryReaderImpl reader = (VarBinaryReaderImpl)this.readers[typeId];
      if (reader == null) {
         reader = new VarBinaryReaderImpl((VarBinaryVector)this.data.getVectorByType(typeId));
         reader.setPosition(this.idx());
         this.readers[typeId] = reader;
      }

      return reader;
   }

   public void read(NullableVarBinaryHolder holder) {
      this.getReaderForIndex(this.idx()).read(holder);
   }

   public void copyAsValue(VarBinaryWriter writer) {
      this.getReaderForIndex(this.idx()).copyAsValue(writer);
   }

   private VarCharReaderImpl getVarChar(byte typeId) {
      VarCharReaderImpl reader = (VarCharReaderImpl)this.readers[typeId];
      if (reader == null) {
         reader = new VarCharReaderImpl((VarCharVector)this.data.getVectorByType(typeId));
         reader.setPosition(this.idx());
         this.readers[typeId] = reader;
      }

      return reader;
   }

   public void read(NullableVarCharHolder holder) {
      this.getReaderForIndex(this.idx()).read(holder);
   }

   public void copyAsValue(VarCharWriter writer) {
      this.getReaderForIndex(this.idx()).copyAsValue(writer);
   }

   private ViewVarBinaryReaderImpl getViewVarBinary(byte typeId) {
      ViewVarBinaryReaderImpl reader = (ViewVarBinaryReaderImpl)this.readers[typeId];
      if (reader == null) {
         reader = new ViewVarBinaryReaderImpl((ViewVarBinaryVector)this.data.getVectorByType(typeId));
         reader.setPosition(this.idx());
         this.readers[typeId] = reader;
      }

      return reader;
   }

   public void read(NullableViewVarBinaryHolder holder) {
      this.getReaderForIndex(this.idx()).read(holder);
   }

   public void copyAsValue(ViewVarBinaryWriter writer) {
      this.getReaderForIndex(this.idx()).copyAsValue(writer);
   }

   private ViewVarCharReaderImpl getViewVarChar(byte typeId) {
      ViewVarCharReaderImpl reader = (ViewVarCharReaderImpl)this.readers[typeId];
      if (reader == null) {
         reader = new ViewVarCharReaderImpl((ViewVarCharVector)this.data.getVectorByType(typeId));
         reader.setPosition(this.idx());
         this.readers[typeId] = reader;
      }

      return reader;
   }

   public void read(NullableViewVarCharHolder holder) {
      this.getReaderForIndex(this.idx()).read(holder);
   }

   public void copyAsValue(ViewVarCharWriter writer) {
      this.getReaderForIndex(this.idx()).copyAsValue(writer);
   }

   private LargeVarCharReaderImpl getLargeVarChar(byte typeId) {
      LargeVarCharReaderImpl reader = (LargeVarCharReaderImpl)this.readers[typeId];
      if (reader == null) {
         reader = new LargeVarCharReaderImpl((LargeVarCharVector)this.data.getVectorByType(typeId));
         reader.setPosition(this.idx());
         this.readers[typeId] = reader;
      }

      return reader;
   }

   public void read(NullableLargeVarCharHolder holder) {
      this.getReaderForIndex(this.idx()).read(holder);
   }

   public void copyAsValue(LargeVarCharWriter writer) {
      this.getReaderForIndex(this.idx()).copyAsValue(writer);
   }

   private LargeVarBinaryReaderImpl getLargeVarBinary(byte typeId) {
      LargeVarBinaryReaderImpl reader = (LargeVarBinaryReaderImpl)this.readers[typeId];
      if (reader == null) {
         reader = new LargeVarBinaryReaderImpl((LargeVarBinaryVector)this.data.getVectorByType(typeId));
         reader.setPosition(this.idx());
         this.readers[typeId] = reader;
      }

      return reader;
   }

   public void read(NullableLargeVarBinaryHolder holder) {
      this.getReaderForIndex(this.idx()).read(holder);
   }

   public void copyAsValue(LargeVarBinaryWriter writer) {
      this.getReaderForIndex(this.idx()).copyAsValue(writer);
   }

   private BitReaderImpl getBit(byte typeId) {
      BitReaderImpl reader = (BitReaderImpl)this.readers[typeId];
      if (reader == null) {
         reader = new BitReaderImpl((BitVector)this.data.getVectorByType(typeId));
         reader.setPosition(this.idx());
         this.readers[typeId] = reader;
      }

      return reader;
   }

   public void read(NullableBitHolder holder) {
      this.getReaderForIndex(this.idx()).read(holder);
   }

   public void copyAsValue(BitWriter writer) {
      this.getReaderForIndex(this.idx()).copyAsValue(writer);
   }

   public void copyAsValue(BaseWriter.ListWriter writer) {
      ComplexCopier.copy(this, (FieldWriter)writer);
   }

   public void setPosition(int index) {
      super.setPosition(index);
      byte typeId = this.data.getTypeId(index);
      if (this.readers[typeId] != null) {
         int offset = this.data.getOffset(index);
         this.readers[typeId].setPosition(offset);
      }

   }

   public FieldReader reader(byte typeId, String name) {
      return this.getStruct(typeId).reader(name);
   }

   public FieldReader reader(byte typeId) {
      return this.getList(typeId).reader();
   }

   public boolean next() {
      return this.getReaderForIndex(this.idx()).next();
   }
}
