package org.apache.arrow.vector.complex.impl;

import org.apache.arrow.vector.complex.MapVector;
import org.apache.arrow.vector.complex.writer.BaseWriter;
import org.apache.arrow.vector.complex.writer.BigIntWriter;
import org.apache.arrow.vector.complex.writer.BitWriter;
import org.apache.arrow.vector.complex.writer.DateDayWriter;
import org.apache.arrow.vector.complex.writer.DateMilliWriter;
import org.apache.arrow.vector.complex.writer.Decimal256Writer;
import org.apache.arrow.vector.complex.writer.DecimalWriter;
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

public class UnionMapWriter extends UnionListWriter {
   private MapWriteMode mode;
   private BaseWriter.StructWriter entryWriter;

   public UnionMapWriter(MapVector vector) {
      super(vector);
      this.mode = UnionMapWriter.MapWriteMode.OFF;
      this.entryWriter = this.struct();
   }

   public void startMap() {
      this.startList();
   }

   public void endMap() {
      this.endList();
   }

   public void startEntry() {
      this.writer.setAddVectorAsNullable(false);
      this.entryWriter.start();
   }

   public void endEntry() {
      this.entryWriter.end();
      this.mode = UnionMapWriter.MapWriteMode.OFF;
      this.writer.setAddVectorAsNullable(true);
   }

   public UnionMapWriter key() {
      this.writer.setAddVectorAsNullable(false);
      this.mode = UnionMapWriter.MapWriteMode.KEY;
      return this;
   }

   public UnionMapWriter value() {
      this.writer.setAddVectorAsNullable(true);
      this.mode = UnionMapWriter.MapWriteMode.VALUE;
      return this;
   }

   public TinyIntWriter tinyInt() {
      switch (this.mode.ordinal()) {
         case 1:
            return this.entryWriter.tinyInt("key");
         case 2:
            return this.entryWriter.tinyInt("value");
         default:
            return this;
      }
   }

   public UInt1Writer uInt1() {
      switch (this.mode.ordinal()) {
         case 1:
            return this.entryWriter.uInt1("key");
         case 2:
            return this.entryWriter.uInt1("value");
         default:
            return this;
      }
   }

   public UInt2Writer uInt2() {
      switch (this.mode.ordinal()) {
         case 1:
            return this.entryWriter.uInt2("key");
         case 2:
            return this.entryWriter.uInt2("value");
         default:
            return this;
      }
   }

   public SmallIntWriter smallInt() {
      switch (this.mode.ordinal()) {
         case 1:
            return this.entryWriter.smallInt("key");
         case 2:
            return this.entryWriter.smallInt("value");
         default:
            return this;
      }
   }

   public Float2Writer float2() {
      switch (this.mode.ordinal()) {
         case 1:
            return this.entryWriter.float2("key");
         case 2:
            return this.entryWriter.float2("value");
         default:
            return this;
      }
   }

   public IntWriter integer() {
      switch (this.mode.ordinal()) {
         case 1:
            return this.entryWriter.integer("key");
         case 2:
            return this.entryWriter.integer("value");
         default:
            return this;
      }
   }

   public UInt4Writer uInt4() {
      switch (this.mode.ordinal()) {
         case 1:
            return this.entryWriter.uInt4("key");
         case 2:
            return this.entryWriter.uInt4("value");
         default:
            return this;
      }
   }

   public Float4Writer float4() {
      switch (this.mode.ordinal()) {
         case 1:
            return this.entryWriter.float4("key");
         case 2:
            return this.entryWriter.float4("value");
         default:
            return this;
      }
   }

   public DateDayWriter dateDay() {
      switch (this.mode.ordinal()) {
         case 1:
            return this.entryWriter.dateDay("key");
         case 2:
            return this.entryWriter.dateDay("value");
         default:
            return this;
      }
   }

   public IntervalYearWriter intervalYear() {
      switch (this.mode.ordinal()) {
         case 1:
            return this.entryWriter.intervalYear("key");
         case 2:
            return this.entryWriter.intervalYear("value");
         default:
            return this;
      }
   }

   public TimeSecWriter timeSec() {
      switch (this.mode.ordinal()) {
         case 1:
            return this.entryWriter.timeSec("key");
         case 2:
            return this.entryWriter.timeSec("value");
         default:
            return this;
      }
   }

   public TimeMilliWriter timeMilli() {
      switch (this.mode.ordinal()) {
         case 1:
            return this.entryWriter.timeMilli("key");
         case 2:
            return this.entryWriter.timeMilli("value");
         default:
            return this;
      }
   }

   public BigIntWriter bigInt() {
      switch (this.mode.ordinal()) {
         case 1:
            return this.entryWriter.bigInt("key");
         case 2:
            return this.entryWriter.bigInt("value");
         default:
            return this;
      }
   }

   public UInt8Writer uInt8() {
      switch (this.mode.ordinal()) {
         case 1:
            return this.entryWriter.uInt8("key");
         case 2:
            return this.entryWriter.uInt8("value");
         default:
            return this;
      }
   }

   public Float8Writer float8() {
      switch (this.mode.ordinal()) {
         case 1:
            return this.entryWriter.float8("key");
         case 2:
            return this.entryWriter.float8("value");
         default:
            return this;
      }
   }

   public DateMilliWriter dateMilli() {
      switch (this.mode.ordinal()) {
         case 1:
            return this.entryWriter.dateMilli("key");
         case 2:
            return this.entryWriter.dateMilli("value");
         default:
            return this;
      }
   }

   public TimeStampSecWriter timeStampSec() {
      switch (this.mode.ordinal()) {
         case 1:
            return this.entryWriter.timeStampSec("key");
         case 2:
            return this.entryWriter.timeStampSec("value");
         default:
            return this;
      }
   }

   public TimeStampMilliWriter timeStampMilli() {
      switch (this.mode.ordinal()) {
         case 1:
            return this.entryWriter.timeStampMilli("key");
         case 2:
            return this.entryWriter.timeStampMilli("value");
         default:
            return this;
      }
   }

   public TimeStampMicroWriter timeStampMicro() {
      switch (this.mode.ordinal()) {
         case 1:
            return this.entryWriter.timeStampMicro("key");
         case 2:
            return this.entryWriter.timeStampMicro("value");
         default:
            return this;
      }
   }

   public TimeStampNanoWriter timeStampNano() {
      switch (this.mode.ordinal()) {
         case 1:
            return this.entryWriter.timeStampNano("key");
         case 2:
            return this.entryWriter.timeStampNano("value");
         default:
            return this;
      }
   }

   public TimeMicroWriter timeMicro() {
      switch (this.mode.ordinal()) {
         case 1:
            return this.entryWriter.timeMicro("key");
         case 2:
            return this.entryWriter.timeMicro("value");
         default:
            return this;
      }
   }

   public TimeNanoWriter timeNano() {
      switch (this.mode.ordinal()) {
         case 1:
            return this.entryWriter.timeNano("key");
         case 2:
            return this.entryWriter.timeNano("value");
         default:
            return this;
      }
   }

   public IntervalDayWriter intervalDay() {
      switch (this.mode.ordinal()) {
         case 1:
            return this.entryWriter.intervalDay("key");
         case 2:
            return this.entryWriter.intervalDay("value");
         default:
            return this;
      }
   }

   public IntervalMonthDayNanoWriter intervalMonthDayNano() {
      switch (this.mode.ordinal()) {
         case 1:
            return this.entryWriter.intervalMonthDayNano("key");
         case 2:
            return this.entryWriter.intervalMonthDayNano("value");
         default:
            return this;
      }
   }

   public VarBinaryWriter varBinary() {
      switch (this.mode.ordinal()) {
         case 1:
            return this.entryWriter.varBinary("key");
         case 2:
            return this.entryWriter.varBinary("value");
         default:
            return this;
      }
   }

   public VarCharWriter varChar() {
      switch (this.mode.ordinal()) {
         case 1:
            return this.entryWriter.varChar("key");
         case 2:
            return this.entryWriter.varChar("value");
         default:
            return this;
      }
   }

   public ViewVarBinaryWriter viewVarBinary() {
      switch (this.mode.ordinal()) {
         case 1:
            return this.entryWriter.viewVarBinary("key");
         case 2:
            return this.entryWriter.viewVarBinary("value");
         default:
            return this;
      }
   }

   public ViewVarCharWriter viewVarChar() {
      switch (this.mode.ordinal()) {
         case 1:
            return this.entryWriter.viewVarChar("key");
         case 2:
            return this.entryWriter.viewVarChar("value");
         default:
            return this;
      }
   }

   public LargeVarCharWriter largeVarChar() {
      switch (this.mode.ordinal()) {
         case 1:
            return this.entryWriter.largeVarChar("key");
         case 2:
            return this.entryWriter.largeVarChar("value");
         default:
            return this;
      }
   }

   public LargeVarBinaryWriter largeVarBinary() {
      switch (this.mode.ordinal()) {
         case 1:
            return this.entryWriter.largeVarBinary("key");
         case 2:
            return this.entryWriter.largeVarBinary("value");
         default:
            return this;
      }
   }

   public BitWriter bit() {
      switch (this.mode.ordinal()) {
         case 1:
            return this.entryWriter.bit("key");
         case 2:
            return this.entryWriter.bit("value");
         default:
            return this;
      }
   }

   public DecimalWriter decimal() {
      switch (this.mode.ordinal()) {
         case 1:
            return this.entryWriter.decimal("key");
         case 2:
            return this.entryWriter.decimal("value");
         default:
            return this;
      }
   }

   public Decimal256Writer decimal256() {
      switch (this.mode.ordinal()) {
         case 1:
            return this.entryWriter.decimal256("key");
         case 2:
            return this.entryWriter.decimal256("value");
         default:
            return this;
      }
   }

   public BaseWriter.StructWriter struct() {
      switch (this.mode.ordinal()) {
         case 1:
            return this.entryWriter.struct("key");
         case 2:
            return this.entryWriter.struct("value");
         default:
            return super.struct();
      }
   }

   public BaseWriter.ListWriter list() {
      switch (this.mode.ordinal()) {
         case 1:
            return this.entryWriter.list("key");
         case 2:
            return this.entryWriter.list("value");
         default:
            return super.list();
      }
   }

   public BaseWriter.MapWriter map(boolean keysSorted) {
      switch (this.mode.ordinal()) {
         case 1:
            return this.entryWriter.map("key", keysSorted);
         case 2:
            return this.entryWriter.map("value", keysSorted);
         default:
            return super.map();
      }
   }

   public BaseWriter.MapWriter map() {
      switch (this.mode.ordinal()) {
         case 1:
            return this.entryWriter.map("key");
         case 2:
            return this.entryWriter.map("value");
         default:
            return super.map();
      }
   }

   private static enum MapWriteMode {
      OFF,
      KEY,
      VALUE;

      // $FF: synthetic method
      private static MapWriteMode[] $values() {
         return new MapWriteMode[]{OFF, KEY, VALUE};
      }
   }
}
