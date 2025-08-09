package org.apache.arrow.vector.table;

import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import org.apache.arrow.memory.ArrowBuf;
import org.apache.arrow.vector.BigIntVector;
import org.apache.arrow.vector.BitVector;
import org.apache.arrow.vector.DateDayVector;
import org.apache.arrow.vector.DateMilliVector;
import org.apache.arrow.vector.DecimalVector;
import org.apache.arrow.vector.DurationVector;
import org.apache.arrow.vector.FieldVector;
import org.apache.arrow.vector.FixedSizeBinaryVector;
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
import org.apache.arrow.vector.TimeStampMicroTZVector;
import org.apache.arrow.vector.TimeStampMicroVector;
import org.apache.arrow.vector.TimeStampMilliTZVector;
import org.apache.arrow.vector.TimeStampMilliVector;
import org.apache.arrow.vector.TimeStampNanoTZVector;
import org.apache.arrow.vector.TimeStampNanoVector;
import org.apache.arrow.vector.TimeStampSecTZVector;
import org.apache.arrow.vector.TimeStampSecVector;
import org.apache.arrow.vector.TinyIntVector;
import org.apache.arrow.vector.UInt1Vector;
import org.apache.arrow.vector.UInt2Vector;
import org.apache.arrow.vector.UInt4Vector;
import org.apache.arrow.vector.UInt8Vector;
import org.apache.arrow.vector.ValueVector;
import org.apache.arrow.vector.VarBinaryVector;
import org.apache.arrow.vector.VarCharVector;
import org.apache.arrow.vector.complex.DenseUnionVector;
import org.apache.arrow.vector.complex.ListVector;
import org.apache.arrow.vector.complex.StructVector;
import org.apache.arrow.vector.complex.UnionVector;
import org.apache.arrow.vector.holders.NullableBigIntHolder;
import org.apache.arrow.vector.holders.NullableBitHolder;
import org.apache.arrow.vector.holders.NullableDateDayHolder;
import org.apache.arrow.vector.holders.NullableDateMilliHolder;
import org.apache.arrow.vector.holders.NullableDecimalHolder;
import org.apache.arrow.vector.holders.NullableDurationHolder;
import org.apache.arrow.vector.holders.NullableFloat4Holder;
import org.apache.arrow.vector.holders.NullableFloat8Holder;
import org.apache.arrow.vector.holders.NullableIntHolder;
import org.apache.arrow.vector.holders.NullableIntervalDayHolder;
import org.apache.arrow.vector.holders.NullableIntervalMonthDayNanoHolder;
import org.apache.arrow.vector.holders.NullableIntervalYearHolder;
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

public class Row implements Iterator {
   private static final Charset DEFAULT_CHARACTER_SET;
   protected final BaseTable table;
   protected int rowNumber = -1;
   private boolean nextRowSet;
   private final Iterator iterator = this.intIterator();

   public Row(BaseTable table) {
      this.table = table;
   }

   public Row resetPosition() {
      this.rowNumber = -1;
      return this;
   }

   public Row setPosition(int rowNumber) {
      this.rowNumber = rowNumber;
      this.nextRowSet = false;
      return this;
   }

   public boolean isNull(String columnName) {
      ValueVector vector = this.table.getVector(columnName);
      return vector.isNull(this.rowNumber);
   }

   public boolean isNull(int columnIndex) {
      ValueVector vector = this.table.getVector(columnIndex);
      return vector.isNull(this.rowNumber);
   }

   public Object getExtensionType(int vectorIndex) {
      FieldVector vector = this.table.getVector(vectorIndex);
      return vector.getObject(this.rowNumber);
   }

   public Object getExtensionType(String columnName) {
      FieldVector vector = this.table.getVector(columnName);
      return vector.getObject(this.rowNumber);
   }

   public List getMap(int vectorIndex) {
      ListVector vector = (ListVector)this.table.getVector(vectorIndex);
      return vector.getObject(this.rowNumber);
   }

   public List getMap(String columnName) {
      ListVector vector = (ListVector)this.table.getVector(columnName);
      return vector.getObject(this.rowNumber);
   }

   public Object getStruct(int vectorIndex) {
      StructVector vector = (StructVector)this.table.getVector(vectorIndex);
      return vector.getObject(this.rowNumber);
   }

   public Object getStruct(String columnName) {
      StructVector vector = (StructVector)this.table.getVector(columnName);
      return vector.getObject(this.rowNumber);
   }

   public Object getUnion(int vectorIndex) {
      UnionVector vector = (UnionVector)this.table.getVector(vectorIndex);
      return vector.getObject(this.rowNumber);
   }

   public Object getUnion(String columnName) {
      UnionVector vector = (UnionVector)this.table.getVector(columnName);
      return vector.getObject(this.rowNumber);
   }

   public Object getDenseUnion(String columnName) {
      DenseUnionVector vector = (DenseUnionVector)this.table.getVector(columnName);
      return vector.getObject(this.rowNumber);
   }

   public Object getDenseUnion(int vectorIndex) {
      DenseUnionVector vector = (DenseUnionVector)this.table.getVector(vectorIndex);
      return vector.getObject(this.rowNumber);
   }

   public List getList(String columnName) {
      ListVector vector = (ListVector)this.table.getVector(columnName);
      return vector.getObject(this.rowNumber);
   }

   public List getList(int columnIndex) {
      ListVector vector = (ListVector)this.table.getVector(columnIndex);
      return vector.getObject(this.rowNumber);
   }

   public int getInt(String columnName) {
      IntVector vector = (IntVector)this.table.getVector(columnName);
      return vector.get(this.rowNumber);
   }

   public int getInt(int columnIndex) {
      IntVector vector = (IntVector)this.table.getVector(columnIndex);
      return vector.get(this.rowNumber);
   }

   public void getInt(String columnName, NullableIntHolder holder) {
      IntVector vector = (IntVector)this.table.getVector(columnName);
      vector.get(this.rowNumber, holder);
   }

   public void getInt(int columnIndex, NullableIntHolder holder) {
      IntVector vector = (IntVector)this.table.getVector(columnIndex);
      vector.get(this.rowNumber, holder);
   }

   public int getUInt4(String columnName) {
      UInt4Vector vector = (UInt4Vector)this.table.getVector(columnName);
      return vector.get(this.rowNumber);
   }

   public int getUInt4(int columnIndex) {
      UInt4Vector vector = (UInt4Vector)this.table.getVector(columnIndex);
      return vector.get(this.rowNumber);
   }

   public void getUInt4(String columnName, NullableUInt4Holder holder) {
      UInt4Vector vector = (UInt4Vector)this.table.getVector(columnName);
      vector.get(this.rowNumber, holder);
   }

   public void getUInt4(int columnIndex, NullableUInt4Holder holder) {
      UInt4Vector vector = (UInt4Vector)this.table.getVector(columnIndex);
      vector.get(this.rowNumber, holder);
   }

   public short getSmallInt(String columnName) {
      SmallIntVector vector = (SmallIntVector)this.table.getVector(columnName);
      return vector.get(this.rowNumber);
   }

   public short getSmallInt(int columnIndex) {
      SmallIntVector vector = (SmallIntVector)this.table.getVector(columnIndex);
      return vector.get(this.rowNumber);
   }

   public void getSmallInt(String columnName, NullableSmallIntHolder holder) {
      SmallIntVector vector = (SmallIntVector)this.table.getVector(columnName);
      vector.get(this.rowNumber, holder);
   }

   public void getSmallInt(int columnIndex, NullableSmallIntHolder holder) {
      SmallIntVector vector = (SmallIntVector)this.table.getVector(columnIndex);
      vector.get(this.rowNumber, holder);
   }

   public char getUInt2(String columnName) {
      UInt2Vector vector = (UInt2Vector)this.table.getVector(columnName);
      return vector.get(this.rowNumber);
   }

   public char getUInt2(int columnIndex) {
      UInt2Vector vector = (UInt2Vector)this.table.getVector(columnIndex);
      return vector.get(this.rowNumber);
   }

   public void getUInt2(String columnName, NullableUInt2Holder holder) {
      UInt2Vector vector = (UInt2Vector)this.table.getVector(columnName);
      vector.get(this.rowNumber, holder);
   }

   public void getUInt2(int columnIndex, NullableUInt2Holder holder) {
      UInt2Vector vector = (UInt2Vector)this.table.getVector(columnIndex);
      vector.get(this.rowNumber, holder);
   }

   public byte getTinyInt(String columnName) {
      TinyIntVector vector = (TinyIntVector)this.table.getVector(columnName);
      return vector.get(this.rowNumber);
   }

   public byte getTinyInt(int columnIndex) {
      TinyIntVector vector = (TinyIntVector)this.table.getVector(columnIndex);
      return vector.get(this.rowNumber);
   }

   public void getTinyInt(String columnName, NullableTinyIntHolder holder) {
      TinyIntVector vector = (TinyIntVector)this.table.getVector(columnName);
      vector.get(this.rowNumber, holder);
   }

   public void getTinyInt(int columnIndex, NullableTinyIntHolder holder) {
      TinyIntVector vector = (TinyIntVector)this.table.getVector(columnIndex);
      vector.get(this.rowNumber, holder);
   }

   public byte getUInt1(String columnName) {
      UInt1Vector vector = (UInt1Vector)this.table.getVector(columnName);
      return vector.get(this.rowNumber);
   }

   public byte getUInt1(int columnIndex) {
      UInt1Vector vector = (UInt1Vector)this.table.getVector(columnIndex);
      return vector.get(this.rowNumber);
   }

   public void getUInt1(String columnName, NullableUInt1Holder holder) {
      UInt1Vector vector = (UInt1Vector)this.table.getVector(columnName);
      vector.get(this.rowNumber, holder);
   }

   public void getUInt1(int columnIndex, NullableUInt1Holder holder) {
      UInt1Vector vector = (UInt1Vector)this.table.getVector(columnIndex);
      vector.get(this.rowNumber, holder);
   }

   public long getBigInt(String columnName) {
      BigIntVector vector = (BigIntVector)this.table.getVector(columnName);
      return vector.get(this.rowNumber);
   }

   public long getBigInt(int columnIndex) {
      BigIntVector vector = (BigIntVector)this.table.getVector(columnIndex);
      return vector.get(this.rowNumber);
   }

   public void getBigInt(String columnName, NullableBigIntHolder holder) {
      BigIntVector vector = (BigIntVector)this.table.getVector(columnName);
      vector.get(this.rowNumber, holder);
   }

   public void getBigInt(int columnIndex, NullableBigIntHolder holder) {
      BigIntVector vector = (BigIntVector)this.table.getVector(columnIndex);
      vector.get(this.rowNumber, holder);
   }

   public long getUInt8(String columnName) {
      UInt8Vector vector = (UInt8Vector)this.table.getVector(columnName);
      return vector.get(this.rowNumber);
   }

   public long getUInt8(int columnIndex) {
      UInt8Vector vector = (UInt8Vector)this.table.getVector(columnIndex);
      return vector.get(this.rowNumber);
   }

   public void getUInt8(String columnName, NullableUInt8Holder holder) {
      UInt8Vector vector = (UInt8Vector)this.table.getVector(columnName);
      vector.get(this.rowNumber, holder);
   }

   public void getUInt8(int columnIndex, NullableUInt8Holder holder) {
      UInt8Vector vector = (UInt8Vector)this.table.getVector(columnIndex);
      vector.get(this.rowNumber, holder);
   }

   public float getFloat4(String columnName) {
      Float4Vector vector = (Float4Vector)this.table.getVector(columnName);
      return vector.get(this.rowNumber);
   }

   public float getFloat4(int columnIndex) {
      Float4Vector vector = (Float4Vector)this.table.getVector(columnIndex);
      return vector.get(this.rowNumber);
   }

   public void getFloat4(String columnName, NullableFloat4Holder holder) {
      Float4Vector vector = (Float4Vector)this.table.getVector(columnName);
      vector.get(this.rowNumber, holder);
   }

   public void getFloat4(int columnIndex, NullableFloat4Holder holder) {
      Float4Vector vector = (Float4Vector)this.table.getVector(columnIndex);
      vector.get(this.rowNumber, holder);
   }

   public double getFloat8(String columnName) {
      Float8Vector vector = (Float8Vector)this.table.getVector(columnName);
      return vector.get(this.rowNumber);
   }

   public double getFloat8(int columnIndex) {
      Float8Vector vector = (Float8Vector)this.table.getVector(columnIndex);
      return vector.get(this.rowNumber);
   }

   public void getFloat8(String columnName, NullableFloat8Holder holder) {
      Float8Vector vector = (Float8Vector)this.table.getVector(columnName);
      vector.get(this.rowNumber, holder);
   }

   public void getFloat8(int columnIndex, NullableFloat8Holder holder) {
      Float8Vector vector = (Float8Vector)this.table.getVector(columnIndex);
      vector.get(this.rowNumber, holder);
   }

   public int getBit(String columnName) {
      BitVector vector = (BitVector)this.table.getVector(columnName);
      return vector.get(this.rowNumber);
   }

   public int getBit(int columnIndex) {
      BitVector vector = (BitVector)this.table.getVector(columnIndex);
      return vector.get(this.rowNumber);
   }

   public void getBit(String columnName, NullableBitHolder holder) {
      BitVector vector = (BitVector)this.table.getVector(columnName);
      vector.get(this.rowNumber, holder);
   }

   public void getBit(int columnIndex, NullableBitHolder holder) {
      BitVector vector = (BitVector)this.table.getVector(columnIndex);
      vector.get(this.rowNumber, holder);
   }

   public long getDateMilli(String columnName) {
      DateMilliVector vector = (DateMilliVector)this.table.getVector(columnName);
      return vector.get(this.rowNumber);
   }

   public long getDateMilli(int columnIndex) {
      DateMilliVector vector = (DateMilliVector)this.table.getVector(columnIndex);
      return vector.get(this.rowNumber);
   }

   public void getDateMilli(String columnName, NullableDateMilliHolder holder) {
      DateMilliVector vector = (DateMilliVector)this.table.getVector(columnName);
      vector.get(this.rowNumber, holder);
   }

   public void getDateMilli(int columnIndex, NullableDateMilliHolder holder) {
      DateMilliVector vector = (DateMilliVector)this.table.getVector(columnIndex);
      vector.get(this.rowNumber, holder);
   }

   public int getDateDay(String columnName) {
      DateDayVector vector = (DateDayVector)this.table.getVector(columnName);
      return vector.get(this.rowNumber);
   }

   public int getDateDay(int columnIndex) {
      DateDayVector vector = (DateDayVector)this.table.getVector(columnIndex);
      return vector.get(this.rowNumber);
   }

   public void getDateDay(String columnName, NullableDateDayHolder holder) {
      DateDayVector vector = (DateDayVector)this.table.getVector(columnName);
      vector.get(this.rowNumber, holder);
   }

   public void getDateDay(int columnIndex, NullableDateDayHolder holder) {
      DateDayVector vector = (DateDayVector)this.table.getVector(columnIndex);
      vector.get(this.rowNumber, holder);
   }

   public long getTimeNano(String columnName) {
      TimeNanoVector vector = (TimeNanoVector)this.table.getVector(columnName);
      return vector.get(this.rowNumber);
   }

   public long getTimeNano(int columnIndex) {
      TimeNanoVector vector = (TimeNanoVector)this.table.getVector(columnIndex);
      return vector.get(this.rowNumber);
   }

   public void getTimeNano(String columnName, NullableTimeNanoHolder holder) {
      TimeNanoVector vector = (TimeNanoVector)this.table.getVector(columnName);
      vector.get(this.rowNumber, holder);
   }

   public void getTimeNano(int columnIndex, NullableTimeNanoHolder holder) {
      TimeNanoVector vector = (TimeNanoVector)this.table.getVector(columnIndex);
      vector.get(this.rowNumber, holder);
   }

   public long getTimeMicro(String columnName) {
      TimeMicroVector vector = (TimeMicroVector)this.table.getVector(columnName);
      return vector.get(this.rowNumber);
   }

   public long getTimeMicro(int columnIndex) {
      TimeMicroVector vector = (TimeMicroVector)this.table.getVector(columnIndex);
      return vector.get(this.rowNumber);
   }

   public void getTimeMicro(String columnName, NullableTimeMicroHolder holder) {
      TimeMicroVector vector = (TimeMicroVector)this.table.getVector(columnName);
      vector.get(this.rowNumber, holder);
   }

   public void getTimeMicro(int columnIndex, NullableTimeMicroHolder holder) {
      TimeMicroVector vector = (TimeMicroVector)this.table.getVector(columnIndex);
      vector.get(this.rowNumber, holder);
   }

   public int getTimeMilli(String columnName) {
      TimeMilliVector vector = (TimeMilliVector)this.table.getVector(columnName);
      return vector.get(this.rowNumber);
   }

   public int getTimeMilli(int columnIndex) {
      TimeMilliVector vector = (TimeMilliVector)this.table.getVector(columnIndex);
      return vector.get(this.rowNumber);
   }

   public void getTimeMilli(String columnName, NullableTimeMilliHolder holder) {
      TimeMilliVector vector = (TimeMilliVector)this.table.getVector(columnName);
      vector.get(this.rowNumber, holder);
   }

   public void getTimeMilli(int columnIndex, NullableTimeMilliHolder holder) {
      TimeMilliVector vector = (TimeMilliVector)this.table.getVector(columnIndex);
      vector.get(this.rowNumber, holder);
   }

   public LocalDateTime getTimeMilliObj(String columnName) {
      TimeMilliVector vector = (TimeMilliVector)this.table.getVector(columnName);
      return vector.getObject(this.rowNumber);
   }

   public LocalDateTime getTimeMilliObj(int columnIndex) {
      TimeMilliVector vector = (TimeMilliVector)this.table.getVector(columnIndex);
      return vector.getObject(this.rowNumber);
   }

   public int getTimeSec(String columnName) {
      TimeSecVector vector = (TimeSecVector)this.table.getVector(columnName);
      return vector.get(this.rowNumber);
   }

   public int getTimeSec(int columnIndex) {
      TimeSecVector vector = (TimeSecVector)this.table.getVector(columnIndex);
      return vector.get(this.rowNumber);
   }

   public void getTimeSec(String columnName, NullableTimeSecHolder holder) {
      TimeSecVector vector = (TimeSecVector)this.table.getVector(columnName);
      vector.get(this.rowNumber, holder);
   }

   public void getTimeSec(int columnIndex, NullableTimeSecHolder holder) {
      TimeSecVector vector = (TimeSecVector)this.table.getVector(columnIndex);
      vector.get(this.rowNumber, holder);
   }

   public long getTimeStampSec(String columnName) {
      TimeStampSecVector vector = (TimeStampSecVector)this.table.getVector(columnName);
      return vector.get(this.rowNumber);
   }

   public long getTimeStampSec(int columnIndex) {
      TimeStampSecVector vector = (TimeStampSecVector)this.table.getVector(columnIndex);
      return vector.get(this.rowNumber);
   }

   public void getTimeStampSec(String columnName, NullableTimeStampSecHolder holder) {
      TimeStampSecVector vector = (TimeStampSecVector)this.table.getVector(columnName);
      vector.get(this.rowNumber, holder);
   }

   public void getTimeStampSec(int columnIndex, NullableTimeStampSecHolder holder) {
      TimeStampSecVector vector = (TimeStampSecVector)this.table.getVector(columnIndex);
      vector.get(this.rowNumber, holder);
   }

   public LocalDateTime getTimeStampSecObj(String columnName) {
      TimeStampSecVector vector = (TimeStampSecVector)this.table.getVector(columnName);
      return vector.getObject(this.rowNumber);
   }

   public LocalDateTime getTimeStampSecObj(int columnIndex) {
      TimeStampSecVector vector = (TimeStampSecVector)this.table.getVector(columnIndex);
      return vector.getObject(this.rowNumber);
   }

   public long getTimeStampSecTZ(String columnName) {
      TimeStampSecTZVector vector = (TimeStampSecTZVector)this.table.getVector(columnName);
      return vector.get(this.rowNumber);
   }

   public long getTimeStampSecTZ(int columnIndex) {
      TimeStampSecTZVector vector = (TimeStampSecTZVector)this.table.getVector(columnIndex);
      return vector.get(this.rowNumber);
   }

   public void getTimeStampSecTZ(String columnName, NullableTimeStampSecTZHolder holder) {
      TimeStampSecTZVector vector = (TimeStampSecTZVector)this.table.getVector(columnName);
      vector.get(this.rowNumber, holder);
   }

   public void getTimeStampSecTZ(int columnIndex, NullableTimeStampSecTZHolder holder) {
      TimeStampSecTZVector vector = (TimeStampSecTZVector)this.table.getVector(columnIndex);
      vector.get(this.rowNumber, holder);
   }

   public long getTimeStampNano(String columnName) {
      TimeStampNanoVector vector = (TimeStampNanoVector)this.table.getVector(columnName);
      return vector.get(this.rowNumber);
   }

   public long getTimeStampNano(int columnIndex) {
      TimeStampNanoVector vector = (TimeStampNanoVector)this.table.getVector(columnIndex);
      return vector.get(this.rowNumber);
   }

   public void getTimeStampNano(String columnName, NullableTimeStampNanoHolder holder) {
      TimeStampNanoVector vector = (TimeStampNanoVector)this.table.getVector(columnName);
      vector.get(this.rowNumber, holder);
   }

   public void getTimeStampNano(int columnIndex, NullableTimeStampNanoHolder holder) {
      TimeStampNanoVector vector = (TimeStampNanoVector)this.table.getVector(columnIndex);
      vector.get(this.rowNumber, holder);
   }

   public LocalDateTime getTimeStampNanoObj(String columnName) {
      TimeStampNanoVector vector = (TimeStampNanoVector)this.table.getVector(columnName);
      return vector.getObject(this.rowNumber);
   }

   public LocalDateTime getTimeStampNanoObj(int columnIndex) {
      TimeStampNanoVector vector = (TimeStampNanoVector)this.table.getVector(columnIndex);
      return vector.getObject(this.rowNumber);
   }

   public long getTimeStampNanoTZ(String columnName) {
      TimeStampNanoTZVector vector = (TimeStampNanoTZVector)this.table.getVector(columnName);
      return vector.get(this.rowNumber);
   }

   public long getTimeStampNanoTZ(int columnIndex) {
      TimeStampNanoTZVector vector = (TimeStampNanoTZVector)this.table.getVector(columnIndex);
      return vector.get(this.rowNumber);
   }

   public void getTimeStampNanoTZ(String columnName, NullableTimeStampNanoTZHolder holder) {
      TimeStampNanoTZVector vector = (TimeStampNanoTZVector)this.table.getVector(columnName);
      vector.get(this.rowNumber, holder);
   }

   public void getTimeStampNanoTZ(int columnIndex, NullableTimeStampNanoTZHolder holder) {
      TimeStampNanoTZVector vector = (TimeStampNanoTZVector)this.table.getVector(columnIndex);
      vector.get(this.rowNumber, holder);
   }

   public long getTimeStampMilli(String columnName) {
      TimeStampMilliVector vector = (TimeStampMilliVector)this.table.getVector(columnName);
      return vector.get(this.rowNumber);
   }

   public long getTimeStampMilli(int columnIndex) {
      TimeStampMilliVector vector = (TimeStampMilliVector)this.table.getVector(columnIndex);
      return vector.get(this.rowNumber);
   }

   public void getTimeStampMilli(String columnName, NullableTimeStampMilliHolder holder) {
      TimeStampMilliVector vector = (TimeStampMilliVector)this.table.getVector(columnName);
      vector.get(this.rowNumber, holder);
   }

   public void getTimeStampMilli(int columnIndex, NullableTimeStampMilliHolder holder) {
      TimeStampMilliVector vector = (TimeStampMilliVector)this.table.getVector(columnIndex);
      vector.get(this.rowNumber, holder);
   }

   public LocalDateTime getTimeStampMilliObj(String columnName) {
      TimeStampMilliVector vector = (TimeStampMilliVector)this.table.getVector(columnName);
      return vector.getObject(this.rowNumber);
   }

   public LocalDateTime getTimeStampMilliObj(int columnIndex) {
      TimeStampMilliVector vector = (TimeStampMilliVector)this.table.getVector(columnIndex);
      return vector.getObject(this.rowNumber);
   }

   public long getTimeStampMilliTZ(String columnName) {
      TimeStampMilliTZVector vector = (TimeStampMilliTZVector)this.table.getVector(columnName);
      return vector.get(this.rowNumber);
   }

   public long getTimeStampMilliTZ(int columnIndex) {
      TimeStampMilliTZVector vector = (TimeStampMilliTZVector)this.table.getVector(columnIndex);
      return vector.get(this.rowNumber);
   }

   public void getTimeStampMilliTZ(String columnName, NullableTimeStampMilliTZHolder holder) {
      TimeStampMilliTZVector vector = (TimeStampMilliTZVector)this.table.getVector(columnName);
      vector.get(this.rowNumber, holder);
   }

   public void getTimeStampMilliTZ(int columnIndex, NullableTimeStampMilliTZHolder holder) {
      TimeStampMilliTZVector vector = (TimeStampMilliTZVector)this.table.getVector(columnIndex);
      vector.get(this.rowNumber, holder);
   }

   public long getTimeStampMicro(String columnName) {
      TimeStampMicroVector vector = (TimeStampMicroVector)this.table.getVector(columnName);
      return vector.get(this.rowNumber);
   }

   public long getTimeStampMicro(int columnIndex) {
      TimeStampMicroVector vector = (TimeStampMicroVector)this.table.getVector(columnIndex);
      return vector.get(this.rowNumber);
   }

   public void getTimeStampMicro(String columnName, NullableTimeStampMicroHolder holder) {
      TimeStampMicroVector vector = (TimeStampMicroVector)this.table.getVector(columnName);
      vector.get(this.rowNumber, holder);
   }

   public void getTimeStampMicro(int columnIndex, NullableTimeStampMicroHolder holder) {
      TimeStampMicroVector vector = (TimeStampMicroVector)this.table.getVector(columnIndex);
      vector.get(this.rowNumber, holder);
   }

   public LocalDateTime getTimeStampMicroObj(String columnName) {
      TimeStampMicroVector vector = (TimeStampMicroVector)this.table.getVector(columnName);
      return vector.getObject(this.rowNumber);
   }

   public LocalDateTime getTimeStampMicroObj(int columnIndex) {
      TimeStampMicroVector vector = (TimeStampMicroVector)this.table.getVector(columnIndex);
      return vector.getObject(this.rowNumber);
   }

   public long getTimeStampMicroTZ(String columnName) {
      TimeStampMicroTZVector vector = (TimeStampMicroTZVector)this.table.getVector(columnName);
      return vector.get(this.rowNumber);
   }

   public long getTimeStampMicroTZ(int columnIndex) {
      TimeStampMicroTZVector vector = (TimeStampMicroTZVector)this.table.getVector(columnIndex);
      return vector.get(this.rowNumber);
   }

   public void getTimeStampMicroTZ(String columnName, NullableTimeStampMicroTZHolder holder) {
      TimeStampMicroTZVector vector = (TimeStampMicroTZVector)this.table.getVector(columnName);
      vector.get(this.rowNumber, holder);
   }

   public void getTimeStampMicroTZ(int columnIndex, NullableTimeStampMicroTZHolder holder) {
      TimeStampMicroTZVector vector = (TimeStampMicroTZVector)this.table.getVector(columnIndex);
      vector.get(this.rowNumber, holder);
   }

   public Duration getDurationObj(String columnName) {
      DurationVector vector = (DurationVector)this.table.getVector(columnName);
      return vector.getObject(this.rowNumber);
   }

   public Duration getDurationObj(int columnIndex) {
      DurationVector vector = (DurationVector)this.table.getVector(columnIndex);
      return vector.getObject(this.rowNumber);
   }

   public ArrowBuf getDuration(String columnName) {
      DurationVector vector = (DurationVector)this.table.getVector(columnName);
      return vector.get(this.rowNumber);
   }

   public ArrowBuf getDuration(int columnIndex) {
      DurationVector vector = (DurationVector)this.table.getVector(columnIndex);
      return vector.get(this.rowNumber);
   }

   public void getDuration(String columnName, NullableDurationHolder holder) {
      DurationVector vector = (DurationVector)this.table.getVector(columnName);
      vector.get(this.rowNumber, holder);
   }

   public void getDuration(int columnIndex, NullableDurationHolder holder) {
      DurationVector vector = (DurationVector)this.table.getVector(columnIndex);
      vector.get(this.rowNumber, holder);
   }

   public PeriodDuration getIntervalMonthDayNanoObj(String columnName) {
      IntervalMonthDayNanoVector vector = (IntervalMonthDayNanoVector)this.table.getVector(columnName);
      return vector.getObject(this.rowNumber);
   }

   public PeriodDuration getIntervalMonthDayNanoObj(int columnIndex) {
      IntervalMonthDayNanoVector vector = (IntervalMonthDayNanoVector)this.table.getVector(columnIndex);
      return vector.getObject(this.rowNumber);
   }

   public ArrowBuf getIntervalMonthDayNano(String columnName) {
      IntervalMonthDayNanoVector vector = (IntervalMonthDayNanoVector)this.table.getVector(columnName);
      return vector.get(this.rowNumber);
   }

   public ArrowBuf getIntervalMonthDayNano(int columnIndex) {
      IntervalMonthDayNanoVector vector = (IntervalMonthDayNanoVector)this.table.getVector(columnIndex);
      return vector.get(this.rowNumber);
   }

   public void getIntervalMonthDayNano(String columnName, NullableIntervalMonthDayNanoHolder holder) {
      IntervalMonthDayNanoVector vector = (IntervalMonthDayNanoVector)this.table.getVector(columnName);
      vector.get(this.rowNumber, holder);
   }

   public void getIntervalMonthDayNano(int columnIndex, NullableIntervalMonthDayNanoHolder holder) {
      IntervalMonthDayNanoVector vector = (IntervalMonthDayNanoVector)this.table.getVector(columnIndex);
      vector.get(this.rowNumber, holder);
   }

   public ArrowBuf getIntervalDay(String columnName) {
      IntervalDayVector vector = (IntervalDayVector)this.table.getVector(columnName);
      return vector.get(this.rowNumber);
   }

   public ArrowBuf getIntervalDay(int columnIndex) {
      IntervalDayVector vector = (IntervalDayVector)this.table.getVector(columnIndex);
      return vector.get(this.rowNumber);
   }

   public void getIntervalDay(String columnName, NullableIntervalDayHolder holder) {
      IntervalDayVector vector = (IntervalDayVector)this.table.getVector(columnName);
      vector.get(this.rowNumber, holder);
   }

   public void getIntervalDay(int columnIndex, NullableIntervalDayHolder holder) {
      IntervalDayVector vector = (IntervalDayVector)this.table.getVector(columnIndex);
      vector.get(this.rowNumber, holder);
   }

   public Duration getIntervalDayObj(int columnIndex) {
      IntervalDayVector vector = (IntervalDayVector)this.table.getVector(columnIndex);
      return vector.getObject(this.rowNumber);
   }

   public Duration getIntervalDayObj(String columnName) {
      IntervalDayVector vector = (IntervalDayVector)this.table.getVector(columnName);
      return vector.getObject(this.rowNumber);
   }

   public Period getIntervalYearObj(String columnName) {
      IntervalYearVector vector = (IntervalYearVector)this.table.getVector(columnName);
      return vector.getObject(this.rowNumber);
   }

   public Period getIntervalYearObj(int columnIndex) {
      IntervalYearVector vector = (IntervalYearVector)this.table.getVector(columnIndex);
      return vector.getObject(this.rowNumber);
   }

   public int getIntervalYear(String columnName) {
      IntervalYearVector vector = (IntervalYearVector)this.table.getVector(columnName);
      return vector.get(this.rowNumber);
   }

   public int getIntervalYear(int columnIndex) {
      IntervalYearVector vector = (IntervalYearVector)this.table.getVector(columnIndex);
      return vector.get(this.rowNumber);
   }

   public void getIntervalYear(String columnName, NullableIntervalYearHolder holder) {
      IntervalYearVector vector = (IntervalYearVector)this.table.getVector(columnName);
      vector.get(this.rowNumber, holder);
   }

   public void getIntervalYear(int columnIndex, NullableIntervalYearHolder holder) {
      IntervalYearVector vector = (IntervalYearVector)this.table.getVector(columnIndex);
      vector.get(this.rowNumber, holder);
   }

   public void getDecimal(int columnIndex, NullableDecimalHolder holder) {
      DecimalVector vector = (DecimalVector)this.table.getVector(columnIndex);
      vector.get(this.rowNumber, holder);
   }

   public void getDecimal(String columnName, NullableDecimalHolder holder) {
      DecimalVector vector = (DecimalVector)this.table.getVector(columnName);
      vector.get(this.rowNumber, holder);
   }

   public BigDecimal getDecimalObj(String columnName) {
      DecimalVector vector = (DecimalVector)this.table.getVector(columnName);
      return vector.getObject(this.rowNumber);
   }

   public BigDecimal getDecimalObj(int columnIndex) {
      DecimalVector vector = (DecimalVector)this.table.getVector(columnIndex);
      return vector.getObject(this.rowNumber);
   }

   public ArrowBuf getDecimal(String columnName) {
      DecimalVector vector = (DecimalVector)this.table.getVector(columnName);
      return vector.get(this.rowNumber);
   }

   public ArrowBuf getDecimal(int columnIndex) {
      DecimalVector vector = (DecimalVector)this.table.getVector(columnIndex);
      return vector.get(this.rowNumber);
   }

   public byte[] getVarBinary(String columnName) {
      VarBinaryVector vector = (VarBinaryVector)this.table.getVector(columnName);
      return vector.get(this.rowNumber);
   }

   public byte[] getVarBinary(int columnIndex) {
      VarBinaryVector vector = (VarBinaryVector)this.table.getVector(columnIndex);
      return vector.get(this.rowNumber);
   }

   public byte[] getFixedSizeBinary(String columnName) {
      FixedSizeBinaryVector vector = (FixedSizeBinaryVector)this.table.getVector(columnName);
      return vector.get(this.rowNumber);
   }

   public byte[] getFixedSizeBinary(int columnIndex) {
      FixedSizeBinaryVector vector = (FixedSizeBinaryVector)this.table.getVector(columnIndex);
      return vector.get(this.rowNumber);
   }

   public byte[] getLargeVarBinary(String columnName) {
      LargeVarBinaryVector vector = (LargeVarBinaryVector)this.table.getVector(columnName);
      return vector.get(this.rowNumber);
   }

   public byte[] getLargeVarBinary(int columnIndex) {
      LargeVarBinaryVector vector = (LargeVarBinaryVector)this.table.getVector(columnIndex);
      return vector.get(this.rowNumber);
   }

   public String getVarCharObj(String columnName) {
      VarCharVector vector = (VarCharVector)this.table.getVector(columnName);
      return new String(vector.get(this.rowNumber), this.getDefaultCharacterSet());
   }

   public String getVarCharObj(int columnIndex) {
      VarCharVector vector = (VarCharVector)this.table.getVector(columnIndex);
      return new String(vector.get(this.rowNumber), this.getDefaultCharacterSet());
   }

   public byte[] getVarChar(String columnName) {
      VarCharVector vector = (VarCharVector)this.table.getVector(columnName);
      return vector.get(this.rowNumber);
   }

   public byte[] getVarChar(int columnIndex) {
      VarCharVector vector = (VarCharVector)this.table.getVector(columnIndex);
      return vector.get(this.rowNumber);
   }

   public String getLargeVarCharObj(String columnName) {
      LargeVarCharVector vector = (LargeVarCharVector)this.table.getVector(columnName);
      return new String(vector.get(this.rowNumber), this.getDefaultCharacterSet());
   }

   public String getLargeVarCharObj(int columnIndex) {
      LargeVarCharVector vector = (LargeVarCharVector)this.table.getVector(columnIndex);
      return new String(vector.get(this.rowNumber), this.getDefaultCharacterSet());
   }

   public byte[] getLargeVarChar(String columnName) {
      LargeVarCharVector vector = (LargeVarCharVector)this.table.getVector(columnName);
      return vector.get(this.rowNumber);
   }

   public byte[] getLargeVarChar(int columnIndex) {
      LargeVarCharVector vector = (LargeVarCharVector)this.table.getVector(columnIndex);
      return vector.get(this.rowNumber);
   }

   public boolean hasNext() {
      return this.nextRowSet || this.setNextObject();
   }

   public Row next() {
      if (!this.nextRowSet && !this.setNextObject()) {
         throw new NoSuchElementException();
      } else {
         this.nextRowSet = false;
         return this;
      }
   }

   private boolean setNextObject() {
      while(true) {
         if (this.iterator.hasNext()) {
            int row = (Integer)this.iterator.next();
            if (this.rowIsDeleted(row)) {
               continue;
            }

            this.rowNumber = row;
            this.nextRowSet = true;
            return true;
         }

         return false;
      }
   }

   private Iterator intIterator() {
      return new Iterator() {
         public boolean hasNext() {
            return (long)Row.this.rowNumber < Row.this.table.getRowCount() - 1L;
         }

         public Integer next() {
            ++Row.this.rowNumber;
            return Row.this.rowNumber;
         }
      };
   }

   public int getRowNumber() {
      return this.rowNumber;
   }

   private boolean rowIsDeleted(int rowNumber) {
      return this.table.isRowDeleted(rowNumber);
   }

   public Charset getDefaultCharacterSet() {
      return DEFAULT_CHARACTER_SET;
   }

   static {
      DEFAULT_CHARACTER_SET = StandardCharsets.UTF_8;
   }
}
