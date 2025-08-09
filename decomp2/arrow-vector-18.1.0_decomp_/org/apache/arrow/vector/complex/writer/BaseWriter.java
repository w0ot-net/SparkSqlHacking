package org.apache.arrow.vector.complex.writer;

import org.apache.arrow.vector.complex.Positionable;
import org.apache.arrow.vector.complex.reader.FieldReader;
import org.apache.arrow.vector.types.TimeUnit;
import org.apache.arrow.vector.types.pojo.Field;

public interface BaseWriter extends AutoCloseable, Positionable {
   int getValueCapacity();

   void writeNull();

   public interface ComplexWriter {
      void allocate();

      void clear();

      void copyReader(FieldReader var1);

      StructWriter rootAsStruct();

      ListWriter rootAsList();

      ListWriter rootAsListView();

      MapWriter rootAsMap(boolean var1);

      void setPosition(int var1);

      void setValueCount(int var1);

      void reset();
   }

   public interface ListWriter extends BaseWriter {
      void startList();

      void endList();

      void startListView();

      void endListView();

      StructWriter struct();

      ListWriter list();

      ListWriter listView();

      MapWriter map();

      MapWriter map(boolean var1);

      void copyReader(FieldReader var1);

      TinyIntWriter tinyInt();

      UInt1Writer uInt1();

      UInt2Writer uInt2();

      SmallIntWriter smallInt();

      Float2Writer float2();

      IntWriter integer();

      UInt4Writer uInt4();

      Float4Writer float4();

      DateDayWriter dateDay();

      IntervalYearWriter intervalYear();

      TimeSecWriter timeSec();

      TimeMilliWriter timeMilli();

      BigIntWriter bigInt();

      UInt8Writer uInt8();

      Float8Writer float8();

      DateMilliWriter dateMilli();

      DurationWriter duration();

      TimeStampSecWriter timeStampSec();

      TimeStampMilliWriter timeStampMilli();

      TimeStampMicroWriter timeStampMicro();

      TimeStampNanoWriter timeStampNano();

      TimeStampSecTZWriter timeStampSecTZ();

      TimeStampMilliTZWriter timeStampMilliTZ();

      TimeStampMicroTZWriter timeStampMicroTZ();

      TimeStampNanoTZWriter timeStampNanoTZ();

      TimeMicroWriter timeMicro();

      TimeNanoWriter timeNano();

      IntervalDayWriter intervalDay();

      IntervalMonthDayNanoWriter intervalMonthDayNano();

      Decimal256Writer decimal256();

      DecimalWriter decimal();

      FixedSizeBinaryWriter fixedSizeBinary();

      VarBinaryWriter varBinary();

      VarCharWriter varChar();

      ViewVarBinaryWriter viewVarBinary();

      ViewVarCharWriter viewVarChar();

      LargeVarCharWriter largeVarChar();

      LargeVarBinaryWriter largeVarBinary();

      BitWriter bit();
   }

   public interface MapWriter extends ListWriter {
      void startMap();

      void endMap();

      void startEntry();

      void endEntry();

      MapWriter key();

      MapWriter value();
   }

   public interface ScalarWriter extends TinyIntWriter, UInt1Writer, UInt2Writer, SmallIntWriter, Float2Writer, IntWriter, UInt4Writer, Float4Writer, DateDayWriter, IntervalYearWriter, TimeSecWriter, TimeMilliWriter, BigIntWriter, UInt8Writer, Float8Writer, DateMilliWriter, DurationWriter, TimeStampSecWriter, TimeStampMilliWriter, TimeStampMicroWriter, TimeStampNanoWriter, TimeStampSecTZWriter, TimeStampMilliTZWriter, TimeStampMicroTZWriter, TimeStampNanoTZWriter, TimeMicroWriter, TimeNanoWriter, IntervalDayWriter, IntervalMonthDayNanoWriter, Decimal256Writer, DecimalWriter, FixedSizeBinaryWriter, VarBinaryWriter, VarCharWriter, ViewVarBinaryWriter, ViewVarCharWriter, LargeVarCharWriter, LargeVarBinaryWriter, BitWriter, BaseWriter {
   }

   public interface StructOrListWriter {
      void start();

      void end();

      StructOrListWriter struct(String var1);

      /** @deprecated */
      @Deprecated
      StructOrListWriter listoftstruct(String var1);

      StructOrListWriter listOfStruct(String var1);

      StructOrListWriter list(String var1);

      boolean isStructWriter();

      boolean isListWriter();

      VarCharWriter varChar(String var1);

      IntWriter integer(String var1);

      BigIntWriter bigInt(String var1);

      Float4Writer float4(String var1);

      Float8Writer float8(String var1);

      BitWriter bit(String var1);

      VarBinaryWriter binary(String var1);
   }

   public interface StructWriter extends BaseWriter {
      Field getField();

      boolean isEmptyStruct();

      TinyIntWriter tinyInt(String var1);

      UInt1Writer uInt1(String var1);

      UInt2Writer uInt2(String var1);

      SmallIntWriter smallInt(String var1);

      Float2Writer float2(String var1);

      IntWriter integer(String var1);

      UInt4Writer uInt4(String var1);

      Float4Writer float4(String var1);

      DateDayWriter dateDay(String var1);

      IntervalYearWriter intervalYear(String var1);

      TimeSecWriter timeSec(String var1);

      TimeMilliWriter timeMilli(String var1);

      BigIntWriter bigInt(String var1);

      UInt8Writer uInt8(String var1);

      Float8Writer float8(String var1);

      DateMilliWriter dateMilli(String var1);

      DurationWriter duration(String var1, TimeUnit var2);

      DurationWriter duration(String var1);

      TimeStampSecWriter timeStampSec(String var1);

      TimeStampMilliWriter timeStampMilli(String var1);

      TimeStampMicroWriter timeStampMicro(String var1);

      TimeStampNanoWriter timeStampNano(String var1);

      TimeStampSecTZWriter timeStampSecTZ(String var1, String var2);

      TimeStampSecTZWriter timeStampSecTZ(String var1);

      TimeStampMilliTZWriter timeStampMilliTZ(String var1, String var2);

      TimeStampMilliTZWriter timeStampMilliTZ(String var1);

      TimeStampMicroTZWriter timeStampMicroTZ(String var1, String var2);

      TimeStampMicroTZWriter timeStampMicroTZ(String var1);

      TimeStampNanoTZWriter timeStampNanoTZ(String var1, String var2);

      TimeStampNanoTZWriter timeStampNanoTZ(String var1);

      TimeMicroWriter timeMicro(String var1);

      TimeNanoWriter timeNano(String var1);

      IntervalDayWriter intervalDay(String var1);

      IntervalMonthDayNanoWriter intervalMonthDayNano(String var1);

      Decimal256Writer decimal256(String var1, int var2, int var3);

      Decimal256Writer decimal256(String var1);

      DecimalWriter decimal(String var1, int var2, int var3);

      DecimalWriter decimal(String var1);

      FixedSizeBinaryWriter fixedSizeBinary(String var1, int var2);

      FixedSizeBinaryWriter fixedSizeBinary(String var1);

      VarBinaryWriter varBinary(String var1);

      VarCharWriter varChar(String var1);

      ViewVarBinaryWriter viewVarBinary(String var1);

      ViewVarCharWriter viewVarChar(String var1);

      LargeVarCharWriter largeVarChar(String var1);

      LargeVarBinaryWriter largeVarBinary(String var1);

      BitWriter bit(String var1);

      void copyReaderToField(String var1, FieldReader var2);

      StructWriter struct(String var1);

      ListWriter list(String var1);

      ListWriter listView(String var1);

      MapWriter map(String var1);

      MapWriter map(String var1, boolean var2);

      void start();

      void end();
   }
}
