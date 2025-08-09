package org.apache.arrow.vector.complex.impl;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Iterator;
import org.apache.arrow.vector.PeriodDuration;
import org.apache.arrow.vector.complex.UnionVector;
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
import org.apache.arrow.vector.holders.UnionHolder;
import org.apache.arrow.vector.types.Types;
import org.apache.arrow.vector.types.pojo.Field;
import org.apache.arrow.vector.util.Text;

public class UnionReader extends AbstractFieldReader {
   private static final int NUM_SUPPORTED_TYPES = 51;
   private BaseReader[] readers = new BaseReader[51];
   public UnionVector data;
   private static Types.MinorType[] TYPES = new Types.MinorType[51];
   private SingleStructReaderImpl structReader;
   private UnionListReader listReader;
   private UnionListViewReader listViewReader;
   private UnionMapReader mapReader;
   private TinyIntReaderImpl tinyIntReader;
   private UInt1ReaderImpl uInt1Reader;
   private UInt2ReaderImpl uInt2Reader;
   private SmallIntReaderImpl smallIntReader;
   private Float2ReaderImpl float2Reader;
   private IntReaderImpl intReader;
   private UInt4ReaderImpl uInt4Reader;
   private Float4ReaderImpl float4Reader;
   private DateDayReaderImpl dateDayReader;
   private IntervalYearReaderImpl intervalYearReader;
   private TimeSecReaderImpl timeSecReader;
   private TimeMilliReaderImpl timeMilliReader;
   private BigIntReaderImpl bigIntReader;
   private UInt8ReaderImpl uInt8Reader;
   private Float8ReaderImpl float8Reader;
   private DateMilliReaderImpl dateMilliReader;
   private DurationReaderImpl durationReader;
   private TimeStampSecReaderImpl timeStampSecReader;
   private TimeStampMilliReaderImpl timeStampMilliReader;
   private TimeStampMicroReaderImpl timeStampMicroReader;
   private TimeStampNanoReaderImpl timeStampNanoReader;
   private TimeStampSecTZReaderImpl timeStampSecTZReader;
   private TimeStampMilliTZReaderImpl timeStampMilliTZReader;
   private TimeStampMicroTZReaderImpl timeStampMicroTZReader;
   private TimeStampNanoTZReaderImpl timeStampNanoTZReader;
   private TimeMicroReaderImpl timeMicroReader;
   private TimeNanoReaderImpl timeNanoReader;
   private IntervalDayReaderImpl intervalDayReader;
   private IntervalMonthDayNanoReaderImpl intervalMonthDayNanoReader;
   private Decimal256ReaderImpl decimal256Reader;
   private DecimalReaderImpl decimalReader;
   private FixedSizeBinaryReaderImpl fixedSizeBinaryReader;
   private VarBinaryReaderImpl varBinaryReader;
   private VarCharReaderImpl varCharReader;
   private ViewVarBinaryReaderImpl viewVarBinaryReader;
   private ViewVarCharReaderImpl viewVarCharReader;
   private LargeVarCharReaderImpl largeVarCharReader;
   private LargeVarBinaryReaderImpl largeVarBinaryReader;
   private BitReaderImpl bitReader;

   public UnionReader(UnionVector data) {
      this.data = data;
   }

   public Types.MinorType getMinorType() {
      return TYPES[this.data.getTypeValue(this.idx())];
   }

   public Field getField() {
      return this.data.getField();
   }

   public boolean isSet() {
      return !this.data.isNull(this.idx());
   }

   public void read(UnionHolder holder) {
      holder.reader = this;
      holder.isSet = this.isSet() ? 1 : 0;
   }

   public void read(int index, UnionHolder holder) {
      this.getList().read(index, holder);
   }

   private FieldReader getReaderForIndex(int index) {
      int typeValue = this.data.getTypeValue(index);
      FieldReader reader = (FieldReader)this.readers[typeValue];
      if (reader != null) {
         return reader;
      } else {
         switch (Types.MinorType.values()[typeValue]) {
            case NULL:
               return NullReader.INSTANCE;
            case STRUCT:
               return (FieldReader)this.getStruct();
            case LIST:
               return this.getList();
            case LISTVIEW:
               return this.getListView();
            case MAP:
               return this.getMap();
            case TINYINT:
               return this.getTinyInt();
            case UINT1:
               return this.getUInt1();
            case UINT2:
               return this.getUInt2();
            case SMALLINT:
               return this.getSmallInt();
            case FLOAT2:
               return this.getFloat2();
            case INT:
               return this.getInt();
            case UINT4:
               return this.getUInt4();
            case FLOAT4:
               return this.getFloat4();
            case DATEDAY:
               return this.getDateDay();
            case INTERVALYEAR:
               return this.getIntervalYear();
            case TIMESEC:
               return this.getTimeSec();
            case TIMEMILLI:
               return this.getTimeMilli();
            case BIGINT:
               return this.getBigInt();
            case UINT8:
               return this.getUInt8();
            case FLOAT8:
               return this.getFloat8();
            case DATEMILLI:
               return this.getDateMilli();
            case DURATION:
               return this.getDuration();
            case TIMESTAMPSEC:
               return this.getTimeStampSec();
            case TIMESTAMPMILLI:
               return this.getTimeStampMilli();
            case TIMESTAMPMICRO:
               return this.getTimeStampMicro();
            case TIMESTAMPNANO:
               return this.getTimeStampNano();
            case TIMESTAMPSECTZ:
               return this.getTimeStampSecTZ();
            case TIMESTAMPMILLITZ:
               return this.getTimeStampMilliTZ();
            case TIMESTAMPMICROTZ:
               return this.getTimeStampMicroTZ();
            case TIMESTAMPNANOTZ:
               return this.getTimeStampNanoTZ();
            case TIMEMICRO:
               return this.getTimeMicro();
            case TIMENANO:
               return this.getTimeNano();
            case INTERVALDAY:
               return this.getIntervalDay();
            case INTERVALMONTHDAYNANO:
               return this.getIntervalMonthDayNano();
            case DECIMAL256:
               return this.getDecimal256();
            case DECIMAL:
               return this.getDecimal();
            case FIXEDSIZEBINARY:
               return this.getFixedSizeBinary();
            case VARBINARY:
               return this.getVarBinary();
            case VARCHAR:
               return this.getVarChar();
            case VIEWVARBINARY:
               return this.getViewVarBinary();
            case VIEWVARCHAR:
               return this.getViewVarChar();
            case LARGEVARCHAR:
               return this.getLargeVarChar();
            case LARGEVARBINARY:
               return this.getLargeVarBinary();
            case BIT:
               return this.getBit();
            default:
               String var10002 = String.valueOf(Types.MinorType.values()[typeValue]);
               throw new UnsupportedOperationException("Unsupported type: " + var10002);
         }
      }
   }

   private BaseReader.StructReader getStruct() {
      if (this.structReader == null) {
         this.structReader = this.data.getStruct().getReader();
         this.structReader.setPosition(this.idx());
         this.readers[Types.MinorType.STRUCT.ordinal()] = this.structReader;
      }

      return this.structReader;
   }

   private FieldReader getList() {
      if (this.listReader == null) {
         this.listReader = new UnionListReader(this.data.getList());
         this.listReader.setPosition(this.idx());
         this.readers[Types.MinorType.LIST.ordinal()] = this.listReader;
      }

      return this.listReader;
   }

   private FieldReader getListView() {
      if (this.listViewReader == null) {
         this.listViewReader = new UnionListViewReader(this.data.getListView());
         this.listViewReader.setPosition(this.idx());
         this.readers[Types.MinorType.LISTVIEW.ordinal()] = this.listViewReader;
      }

      return this.listViewReader;
   }

   private FieldReader getMap() {
      if (this.mapReader == null) {
         this.mapReader = new UnionMapReader(this.data.getMap());
         this.mapReader.setPosition(this.idx());
         this.readers[Types.MinorType.MAP.ordinal()] = this.mapReader;
      }

      return this.mapReader;
   }

   public Iterator iterator() {
      return this.getStruct().iterator();
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

   private TinyIntReaderImpl getTinyInt() {
      if (this.tinyIntReader == null) {
         this.tinyIntReader = new TinyIntReaderImpl(this.data.getTinyIntVector());
         this.tinyIntReader.setPosition(this.idx());
         this.readers[Types.MinorType.TINYINT.ordinal()] = this.tinyIntReader;
      }

      return this.tinyIntReader;
   }

   public void read(NullableTinyIntHolder holder) {
      this.getReaderForIndex(this.idx()).read(holder);
   }

   public void copyAsValue(TinyIntWriter writer) {
      this.getReaderForIndex(this.idx()).copyAsValue(writer);
   }

   private UInt1ReaderImpl getUInt1() {
      if (this.uInt1Reader == null) {
         this.uInt1Reader = new UInt1ReaderImpl(this.data.getUInt1Vector());
         this.uInt1Reader.setPosition(this.idx());
         this.readers[Types.MinorType.UINT1.ordinal()] = this.uInt1Reader;
      }

      return this.uInt1Reader;
   }

   public void read(NullableUInt1Holder holder) {
      this.getReaderForIndex(this.idx()).read(holder);
   }

   public void copyAsValue(UInt1Writer writer) {
      this.getReaderForIndex(this.idx()).copyAsValue(writer);
   }

   private UInt2ReaderImpl getUInt2() {
      if (this.uInt2Reader == null) {
         this.uInt2Reader = new UInt2ReaderImpl(this.data.getUInt2Vector());
         this.uInt2Reader.setPosition(this.idx());
         this.readers[Types.MinorType.UINT2.ordinal()] = this.uInt2Reader;
      }

      return this.uInt2Reader;
   }

   public void read(NullableUInt2Holder holder) {
      this.getReaderForIndex(this.idx()).read(holder);
   }

   public void copyAsValue(UInt2Writer writer) {
      this.getReaderForIndex(this.idx()).copyAsValue(writer);
   }

   private SmallIntReaderImpl getSmallInt() {
      if (this.smallIntReader == null) {
         this.smallIntReader = new SmallIntReaderImpl(this.data.getSmallIntVector());
         this.smallIntReader.setPosition(this.idx());
         this.readers[Types.MinorType.SMALLINT.ordinal()] = this.smallIntReader;
      }

      return this.smallIntReader;
   }

   public void read(NullableSmallIntHolder holder) {
      this.getReaderForIndex(this.idx()).read(holder);
   }

   public void copyAsValue(SmallIntWriter writer) {
      this.getReaderForIndex(this.idx()).copyAsValue(writer);
   }

   private Float2ReaderImpl getFloat2() {
      if (this.float2Reader == null) {
         this.float2Reader = new Float2ReaderImpl(this.data.getFloat2Vector());
         this.float2Reader.setPosition(this.idx());
         this.readers[Types.MinorType.FLOAT2.ordinal()] = this.float2Reader;
      }

      return this.float2Reader;
   }

   public void read(NullableFloat2Holder holder) {
      this.getReaderForIndex(this.idx()).read(holder);
   }

   public void copyAsValue(Float2Writer writer) {
      this.getReaderForIndex(this.idx()).copyAsValue(writer);
   }

   private IntReaderImpl getInt() {
      if (this.intReader == null) {
         this.intReader = new IntReaderImpl(this.data.getIntVector());
         this.intReader.setPosition(this.idx());
         this.readers[Types.MinorType.INT.ordinal()] = this.intReader;
      }

      return this.intReader;
   }

   public void read(NullableIntHolder holder) {
      this.getReaderForIndex(this.idx()).read(holder);
   }

   public void copyAsValue(IntWriter writer) {
      this.getReaderForIndex(this.idx()).copyAsValue(writer);
   }

   private UInt4ReaderImpl getUInt4() {
      if (this.uInt4Reader == null) {
         this.uInt4Reader = new UInt4ReaderImpl(this.data.getUInt4Vector());
         this.uInt4Reader.setPosition(this.idx());
         this.readers[Types.MinorType.UINT4.ordinal()] = this.uInt4Reader;
      }

      return this.uInt4Reader;
   }

   public void read(NullableUInt4Holder holder) {
      this.getReaderForIndex(this.idx()).read(holder);
   }

   public void copyAsValue(UInt4Writer writer) {
      this.getReaderForIndex(this.idx()).copyAsValue(writer);
   }

   private Float4ReaderImpl getFloat4() {
      if (this.float4Reader == null) {
         this.float4Reader = new Float4ReaderImpl(this.data.getFloat4Vector());
         this.float4Reader.setPosition(this.idx());
         this.readers[Types.MinorType.FLOAT4.ordinal()] = this.float4Reader;
      }

      return this.float4Reader;
   }

   public void read(NullableFloat4Holder holder) {
      this.getReaderForIndex(this.idx()).read(holder);
   }

   public void copyAsValue(Float4Writer writer) {
      this.getReaderForIndex(this.idx()).copyAsValue(writer);
   }

   private DateDayReaderImpl getDateDay() {
      if (this.dateDayReader == null) {
         this.dateDayReader = new DateDayReaderImpl(this.data.getDateDayVector());
         this.dateDayReader.setPosition(this.idx());
         this.readers[Types.MinorType.DATEDAY.ordinal()] = this.dateDayReader;
      }

      return this.dateDayReader;
   }

   public void read(NullableDateDayHolder holder) {
      this.getReaderForIndex(this.idx()).read(holder);
   }

   public void copyAsValue(DateDayWriter writer) {
      this.getReaderForIndex(this.idx()).copyAsValue(writer);
   }

   private IntervalYearReaderImpl getIntervalYear() {
      if (this.intervalYearReader == null) {
         this.intervalYearReader = new IntervalYearReaderImpl(this.data.getIntervalYearVector());
         this.intervalYearReader.setPosition(this.idx());
         this.readers[Types.MinorType.INTERVALYEAR.ordinal()] = this.intervalYearReader;
      }

      return this.intervalYearReader;
   }

   public void read(NullableIntervalYearHolder holder) {
      this.getReaderForIndex(this.idx()).read(holder);
   }

   public void copyAsValue(IntervalYearWriter writer) {
      this.getReaderForIndex(this.idx()).copyAsValue(writer);
   }

   private TimeSecReaderImpl getTimeSec() {
      if (this.timeSecReader == null) {
         this.timeSecReader = new TimeSecReaderImpl(this.data.getTimeSecVector());
         this.timeSecReader.setPosition(this.idx());
         this.readers[Types.MinorType.TIMESEC.ordinal()] = this.timeSecReader;
      }

      return this.timeSecReader;
   }

   public void read(NullableTimeSecHolder holder) {
      this.getReaderForIndex(this.idx()).read(holder);
   }

   public void copyAsValue(TimeSecWriter writer) {
      this.getReaderForIndex(this.idx()).copyAsValue(writer);
   }

   private TimeMilliReaderImpl getTimeMilli() {
      if (this.timeMilliReader == null) {
         this.timeMilliReader = new TimeMilliReaderImpl(this.data.getTimeMilliVector());
         this.timeMilliReader.setPosition(this.idx());
         this.readers[Types.MinorType.TIMEMILLI.ordinal()] = this.timeMilliReader;
      }

      return this.timeMilliReader;
   }

   public void read(NullableTimeMilliHolder holder) {
      this.getReaderForIndex(this.idx()).read(holder);
   }

   public void copyAsValue(TimeMilliWriter writer) {
      this.getReaderForIndex(this.idx()).copyAsValue(writer);
   }

   private BigIntReaderImpl getBigInt() {
      if (this.bigIntReader == null) {
         this.bigIntReader = new BigIntReaderImpl(this.data.getBigIntVector());
         this.bigIntReader.setPosition(this.idx());
         this.readers[Types.MinorType.BIGINT.ordinal()] = this.bigIntReader;
      }

      return this.bigIntReader;
   }

   public void read(NullableBigIntHolder holder) {
      this.getReaderForIndex(this.idx()).read(holder);
   }

   public void copyAsValue(BigIntWriter writer) {
      this.getReaderForIndex(this.idx()).copyAsValue(writer);
   }

   private UInt8ReaderImpl getUInt8() {
      if (this.uInt8Reader == null) {
         this.uInt8Reader = new UInt8ReaderImpl(this.data.getUInt8Vector());
         this.uInt8Reader.setPosition(this.idx());
         this.readers[Types.MinorType.UINT8.ordinal()] = this.uInt8Reader;
      }

      return this.uInt8Reader;
   }

   public void read(NullableUInt8Holder holder) {
      this.getReaderForIndex(this.idx()).read(holder);
   }

   public void copyAsValue(UInt8Writer writer) {
      this.getReaderForIndex(this.idx()).copyAsValue(writer);
   }

   private Float8ReaderImpl getFloat8() {
      if (this.float8Reader == null) {
         this.float8Reader = new Float8ReaderImpl(this.data.getFloat8Vector());
         this.float8Reader.setPosition(this.idx());
         this.readers[Types.MinorType.FLOAT8.ordinal()] = this.float8Reader;
      }

      return this.float8Reader;
   }

   public void read(NullableFloat8Holder holder) {
      this.getReaderForIndex(this.idx()).read(holder);
   }

   public void copyAsValue(Float8Writer writer) {
      this.getReaderForIndex(this.idx()).copyAsValue(writer);
   }

   private DateMilliReaderImpl getDateMilli() {
      if (this.dateMilliReader == null) {
         this.dateMilliReader = new DateMilliReaderImpl(this.data.getDateMilliVector());
         this.dateMilliReader.setPosition(this.idx());
         this.readers[Types.MinorType.DATEMILLI.ordinal()] = this.dateMilliReader;
      }

      return this.dateMilliReader;
   }

   public void read(NullableDateMilliHolder holder) {
      this.getReaderForIndex(this.idx()).read(holder);
   }

   public void copyAsValue(DateMilliWriter writer) {
      this.getReaderForIndex(this.idx()).copyAsValue(writer);
   }

   private DurationReaderImpl getDuration() {
      if (this.durationReader == null) {
         this.durationReader = new DurationReaderImpl(this.data.getDurationVector());
         this.durationReader.setPosition(this.idx());
         this.readers[Types.MinorType.DURATION.ordinal()] = this.durationReader;
      }

      return this.durationReader;
   }

   public void read(NullableDurationHolder holder) {
      this.getReaderForIndex(this.idx()).read(holder);
   }

   public void copyAsValue(DurationWriter writer) {
      this.getReaderForIndex(this.idx()).copyAsValue(writer);
   }

   private TimeStampSecReaderImpl getTimeStampSec() {
      if (this.timeStampSecReader == null) {
         this.timeStampSecReader = new TimeStampSecReaderImpl(this.data.getTimeStampSecVector());
         this.timeStampSecReader.setPosition(this.idx());
         this.readers[Types.MinorType.TIMESTAMPSEC.ordinal()] = this.timeStampSecReader;
      }

      return this.timeStampSecReader;
   }

   public void read(NullableTimeStampSecHolder holder) {
      this.getReaderForIndex(this.idx()).read(holder);
   }

   public void copyAsValue(TimeStampSecWriter writer) {
      this.getReaderForIndex(this.idx()).copyAsValue(writer);
   }

   private TimeStampMilliReaderImpl getTimeStampMilli() {
      if (this.timeStampMilliReader == null) {
         this.timeStampMilliReader = new TimeStampMilliReaderImpl(this.data.getTimeStampMilliVector());
         this.timeStampMilliReader.setPosition(this.idx());
         this.readers[Types.MinorType.TIMESTAMPMILLI.ordinal()] = this.timeStampMilliReader;
      }

      return this.timeStampMilliReader;
   }

   public void read(NullableTimeStampMilliHolder holder) {
      this.getReaderForIndex(this.idx()).read(holder);
   }

   public void copyAsValue(TimeStampMilliWriter writer) {
      this.getReaderForIndex(this.idx()).copyAsValue(writer);
   }

   private TimeStampMicroReaderImpl getTimeStampMicro() {
      if (this.timeStampMicroReader == null) {
         this.timeStampMicroReader = new TimeStampMicroReaderImpl(this.data.getTimeStampMicroVector());
         this.timeStampMicroReader.setPosition(this.idx());
         this.readers[Types.MinorType.TIMESTAMPMICRO.ordinal()] = this.timeStampMicroReader;
      }

      return this.timeStampMicroReader;
   }

   public void read(NullableTimeStampMicroHolder holder) {
      this.getReaderForIndex(this.idx()).read(holder);
   }

   public void copyAsValue(TimeStampMicroWriter writer) {
      this.getReaderForIndex(this.idx()).copyAsValue(writer);
   }

   private TimeStampNanoReaderImpl getTimeStampNano() {
      if (this.timeStampNanoReader == null) {
         this.timeStampNanoReader = new TimeStampNanoReaderImpl(this.data.getTimeStampNanoVector());
         this.timeStampNanoReader.setPosition(this.idx());
         this.readers[Types.MinorType.TIMESTAMPNANO.ordinal()] = this.timeStampNanoReader;
      }

      return this.timeStampNanoReader;
   }

   public void read(NullableTimeStampNanoHolder holder) {
      this.getReaderForIndex(this.idx()).read(holder);
   }

   public void copyAsValue(TimeStampNanoWriter writer) {
      this.getReaderForIndex(this.idx()).copyAsValue(writer);
   }

   private TimeStampSecTZReaderImpl getTimeStampSecTZ() {
      if (this.timeStampSecTZReader == null) {
         this.timeStampSecTZReader = new TimeStampSecTZReaderImpl(this.data.getTimeStampSecTZVector());
         this.timeStampSecTZReader.setPosition(this.idx());
         this.readers[Types.MinorType.TIMESTAMPSECTZ.ordinal()] = this.timeStampSecTZReader;
      }

      return this.timeStampSecTZReader;
   }

   public void read(NullableTimeStampSecTZHolder holder) {
      this.getReaderForIndex(this.idx()).read(holder);
   }

   public void copyAsValue(TimeStampSecTZWriter writer) {
      this.getReaderForIndex(this.idx()).copyAsValue(writer);
   }

   private TimeStampMilliTZReaderImpl getTimeStampMilliTZ() {
      if (this.timeStampMilliTZReader == null) {
         this.timeStampMilliTZReader = new TimeStampMilliTZReaderImpl(this.data.getTimeStampMilliTZVector());
         this.timeStampMilliTZReader.setPosition(this.idx());
         this.readers[Types.MinorType.TIMESTAMPMILLITZ.ordinal()] = this.timeStampMilliTZReader;
      }

      return this.timeStampMilliTZReader;
   }

   public void read(NullableTimeStampMilliTZHolder holder) {
      this.getReaderForIndex(this.idx()).read(holder);
   }

   public void copyAsValue(TimeStampMilliTZWriter writer) {
      this.getReaderForIndex(this.idx()).copyAsValue(writer);
   }

   private TimeStampMicroTZReaderImpl getTimeStampMicroTZ() {
      if (this.timeStampMicroTZReader == null) {
         this.timeStampMicroTZReader = new TimeStampMicroTZReaderImpl(this.data.getTimeStampMicroTZVector());
         this.timeStampMicroTZReader.setPosition(this.idx());
         this.readers[Types.MinorType.TIMESTAMPMICROTZ.ordinal()] = this.timeStampMicroTZReader;
      }

      return this.timeStampMicroTZReader;
   }

   public void read(NullableTimeStampMicroTZHolder holder) {
      this.getReaderForIndex(this.idx()).read(holder);
   }

   public void copyAsValue(TimeStampMicroTZWriter writer) {
      this.getReaderForIndex(this.idx()).copyAsValue(writer);
   }

   private TimeStampNanoTZReaderImpl getTimeStampNanoTZ() {
      if (this.timeStampNanoTZReader == null) {
         this.timeStampNanoTZReader = new TimeStampNanoTZReaderImpl(this.data.getTimeStampNanoTZVector());
         this.timeStampNanoTZReader.setPosition(this.idx());
         this.readers[Types.MinorType.TIMESTAMPNANOTZ.ordinal()] = this.timeStampNanoTZReader;
      }

      return this.timeStampNanoTZReader;
   }

   public void read(NullableTimeStampNanoTZHolder holder) {
      this.getReaderForIndex(this.idx()).read(holder);
   }

   public void copyAsValue(TimeStampNanoTZWriter writer) {
      this.getReaderForIndex(this.idx()).copyAsValue(writer);
   }

   private TimeMicroReaderImpl getTimeMicro() {
      if (this.timeMicroReader == null) {
         this.timeMicroReader = new TimeMicroReaderImpl(this.data.getTimeMicroVector());
         this.timeMicroReader.setPosition(this.idx());
         this.readers[Types.MinorType.TIMEMICRO.ordinal()] = this.timeMicroReader;
      }

      return this.timeMicroReader;
   }

   public void read(NullableTimeMicroHolder holder) {
      this.getReaderForIndex(this.idx()).read(holder);
   }

   public void copyAsValue(TimeMicroWriter writer) {
      this.getReaderForIndex(this.idx()).copyAsValue(writer);
   }

   private TimeNanoReaderImpl getTimeNano() {
      if (this.timeNanoReader == null) {
         this.timeNanoReader = new TimeNanoReaderImpl(this.data.getTimeNanoVector());
         this.timeNanoReader.setPosition(this.idx());
         this.readers[Types.MinorType.TIMENANO.ordinal()] = this.timeNanoReader;
      }

      return this.timeNanoReader;
   }

   public void read(NullableTimeNanoHolder holder) {
      this.getReaderForIndex(this.idx()).read(holder);
   }

   public void copyAsValue(TimeNanoWriter writer) {
      this.getReaderForIndex(this.idx()).copyAsValue(writer);
   }

   private IntervalDayReaderImpl getIntervalDay() {
      if (this.intervalDayReader == null) {
         this.intervalDayReader = new IntervalDayReaderImpl(this.data.getIntervalDayVector());
         this.intervalDayReader.setPosition(this.idx());
         this.readers[Types.MinorType.INTERVALDAY.ordinal()] = this.intervalDayReader;
      }

      return this.intervalDayReader;
   }

   public void read(NullableIntervalDayHolder holder) {
      this.getReaderForIndex(this.idx()).read(holder);
   }

   public void copyAsValue(IntervalDayWriter writer) {
      this.getReaderForIndex(this.idx()).copyAsValue(writer);
   }

   private IntervalMonthDayNanoReaderImpl getIntervalMonthDayNano() {
      if (this.intervalMonthDayNanoReader == null) {
         this.intervalMonthDayNanoReader = new IntervalMonthDayNanoReaderImpl(this.data.getIntervalMonthDayNanoVector());
         this.intervalMonthDayNanoReader.setPosition(this.idx());
         this.readers[Types.MinorType.INTERVALMONTHDAYNANO.ordinal()] = this.intervalMonthDayNanoReader;
      }

      return this.intervalMonthDayNanoReader;
   }

   public void read(NullableIntervalMonthDayNanoHolder holder) {
      this.getReaderForIndex(this.idx()).read(holder);
   }

   public void copyAsValue(IntervalMonthDayNanoWriter writer) {
      this.getReaderForIndex(this.idx()).copyAsValue(writer);
   }

   private Decimal256ReaderImpl getDecimal256() {
      if (this.decimal256Reader == null) {
         this.decimal256Reader = new Decimal256ReaderImpl(this.data.getDecimal256Vector());
         this.decimal256Reader.setPosition(this.idx());
         this.readers[Types.MinorType.DECIMAL256.ordinal()] = this.decimal256Reader;
      }

      return this.decimal256Reader;
   }

   public void read(NullableDecimal256Holder holder) {
      this.getReaderForIndex(this.idx()).read(holder);
   }

   public void copyAsValue(Decimal256Writer writer) {
      this.getReaderForIndex(this.idx()).copyAsValue(writer);
   }

   private DecimalReaderImpl getDecimal() {
      if (this.decimalReader == null) {
         this.decimalReader = new DecimalReaderImpl(this.data.getDecimalVector());
         this.decimalReader.setPosition(this.idx());
         this.readers[Types.MinorType.DECIMAL.ordinal()] = this.decimalReader;
      }

      return this.decimalReader;
   }

   public void read(NullableDecimalHolder holder) {
      this.getReaderForIndex(this.idx()).read(holder);
   }

   public void copyAsValue(DecimalWriter writer) {
      this.getReaderForIndex(this.idx()).copyAsValue(writer);
   }

   private FixedSizeBinaryReaderImpl getFixedSizeBinary() {
      if (this.fixedSizeBinaryReader == null) {
         this.fixedSizeBinaryReader = new FixedSizeBinaryReaderImpl(this.data.getFixedSizeBinaryVector());
         this.fixedSizeBinaryReader.setPosition(this.idx());
         this.readers[Types.MinorType.FIXEDSIZEBINARY.ordinal()] = this.fixedSizeBinaryReader;
      }

      return this.fixedSizeBinaryReader;
   }

   public void read(NullableFixedSizeBinaryHolder holder) {
      this.getReaderForIndex(this.idx()).read(holder);
   }

   public void copyAsValue(FixedSizeBinaryWriter writer) {
      this.getReaderForIndex(this.idx()).copyAsValue(writer);
   }

   private VarBinaryReaderImpl getVarBinary() {
      if (this.varBinaryReader == null) {
         this.varBinaryReader = new VarBinaryReaderImpl(this.data.getVarBinaryVector());
         this.varBinaryReader.setPosition(this.idx());
         this.readers[Types.MinorType.VARBINARY.ordinal()] = this.varBinaryReader;
      }

      return this.varBinaryReader;
   }

   public void read(NullableVarBinaryHolder holder) {
      this.getReaderForIndex(this.idx()).read(holder);
   }

   public void copyAsValue(VarBinaryWriter writer) {
      this.getReaderForIndex(this.idx()).copyAsValue(writer);
   }

   private VarCharReaderImpl getVarChar() {
      if (this.varCharReader == null) {
         this.varCharReader = new VarCharReaderImpl(this.data.getVarCharVector());
         this.varCharReader.setPosition(this.idx());
         this.readers[Types.MinorType.VARCHAR.ordinal()] = this.varCharReader;
      }

      return this.varCharReader;
   }

   public void read(NullableVarCharHolder holder) {
      this.getReaderForIndex(this.idx()).read(holder);
   }

   public void copyAsValue(VarCharWriter writer) {
      this.getReaderForIndex(this.idx()).copyAsValue(writer);
   }

   private ViewVarBinaryReaderImpl getViewVarBinary() {
      if (this.viewVarBinaryReader == null) {
         this.viewVarBinaryReader = new ViewVarBinaryReaderImpl(this.data.getViewVarBinaryVector());
         this.viewVarBinaryReader.setPosition(this.idx());
         this.readers[Types.MinorType.VIEWVARBINARY.ordinal()] = this.viewVarBinaryReader;
      }

      return this.viewVarBinaryReader;
   }

   public void read(NullableViewVarBinaryHolder holder) {
      this.getReaderForIndex(this.idx()).read(holder);
   }

   public void copyAsValue(ViewVarBinaryWriter writer) {
      this.getReaderForIndex(this.idx()).copyAsValue(writer);
   }

   private ViewVarCharReaderImpl getViewVarChar() {
      if (this.viewVarCharReader == null) {
         this.viewVarCharReader = new ViewVarCharReaderImpl(this.data.getViewVarCharVector());
         this.viewVarCharReader.setPosition(this.idx());
         this.readers[Types.MinorType.VIEWVARCHAR.ordinal()] = this.viewVarCharReader;
      }

      return this.viewVarCharReader;
   }

   public void read(NullableViewVarCharHolder holder) {
      this.getReaderForIndex(this.idx()).read(holder);
   }

   public void copyAsValue(ViewVarCharWriter writer) {
      this.getReaderForIndex(this.idx()).copyAsValue(writer);
   }

   private LargeVarCharReaderImpl getLargeVarChar() {
      if (this.largeVarCharReader == null) {
         this.largeVarCharReader = new LargeVarCharReaderImpl(this.data.getLargeVarCharVector());
         this.largeVarCharReader.setPosition(this.idx());
         this.readers[Types.MinorType.LARGEVARCHAR.ordinal()] = this.largeVarCharReader;
      }

      return this.largeVarCharReader;
   }

   public void read(NullableLargeVarCharHolder holder) {
      this.getReaderForIndex(this.idx()).read(holder);
   }

   public void copyAsValue(LargeVarCharWriter writer) {
      this.getReaderForIndex(this.idx()).copyAsValue(writer);
   }

   private LargeVarBinaryReaderImpl getLargeVarBinary() {
      if (this.largeVarBinaryReader == null) {
         this.largeVarBinaryReader = new LargeVarBinaryReaderImpl(this.data.getLargeVarBinaryVector());
         this.largeVarBinaryReader.setPosition(this.idx());
         this.readers[Types.MinorType.LARGEVARBINARY.ordinal()] = this.largeVarBinaryReader;
      }

      return this.largeVarBinaryReader;
   }

   public void read(NullableLargeVarBinaryHolder holder) {
      this.getReaderForIndex(this.idx()).read(holder);
   }

   public void copyAsValue(LargeVarBinaryWriter writer) {
      this.getReaderForIndex(this.idx()).copyAsValue(writer);
   }

   private BitReaderImpl getBit() {
      if (this.bitReader == null) {
         this.bitReader = new BitReaderImpl(this.data.getBitVector());
         this.bitReader.setPosition(this.idx());
         this.readers[Types.MinorType.BIT.ordinal()] = this.bitReader;
      }

      return this.bitReader;
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

      for(BaseReader reader : this.readers) {
         if (reader != null) {
            reader.setPosition(index);
         }
      }

   }

   public FieldReader reader(String name) {
      return this.getStruct().reader(name);
   }

   public FieldReader reader() {
      return this.getList().reader();
   }

   public boolean next() {
      return this.getReaderForIndex(this.idx()).next();
   }

   static {
      for(Types.MinorType minorType : Types.MinorType.values()) {
         TYPES[minorType.ordinal()] = minorType;
      }

   }
}
