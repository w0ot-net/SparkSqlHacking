package org.apache.arrow.vector;

import java.math.BigDecimal;
import java.nio.charset.Charset;

public class GenerateSampleData {
   private GenerateSampleData() {
   }

   public static void generateTestData(ValueVector vector, int valueCount) {
      if (vector instanceof IntVector) {
         writeIntData((IntVector)vector, valueCount);
      } else if (vector instanceof DecimalVector) {
         writeDecimalData((DecimalVector)vector, valueCount);
      } else if (vector instanceof BitVector) {
         writeBooleanData((BitVector)vector, valueCount);
      } else if (vector instanceof VarCharVector) {
         writeVarCharData((VarCharVector)vector, valueCount);
      } else if (vector instanceof VarBinaryVector) {
         writeVarBinaryData((VarBinaryVector)vector, valueCount);
      } else if (vector instanceof BigIntVector) {
         writeBigIntData((BigIntVector)vector, valueCount);
      } else if (vector instanceof Float4Vector) {
         writeFloatData((Float4Vector)vector, valueCount);
      } else if (vector instanceof Float8Vector) {
         writeDoubleData((Float8Vector)vector, valueCount);
      } else if (vector instanceof DateDayVector) {
         writeDateDayData((DateDayVector)vector, valueCount);
      } else if (vector instanceof DateMilliVector) {
         writeDateMilliData((DateMilliVector)vector, valueCount);
      } else if (vector instanceof IntervalDayVector) {
         writeIntervalDayData((IntervalDayVector)vector, valueCount);
      } else if (vector instanceof IntervalYearVector) {
         writeIntervalYearData((IntervalYearVector)vector, valueCount);
      } else if (vector instanceof SmallIntVector) {
         writeSmallIntData((SmallIntVector)vector, valueCount);
      } else if (vector instanceof TinyIntVector) {
         writeTinyIntData((TinyIntVector)vector, valueCount);
      } else if (vector instanceof TimeMicroVector) {
         writeTimeMicroData((TimeMicroVector)vector, valueCount);
      } else if (vector instanceof TimeMilliVector) {
         writeTimeMilliData((TimeMilliVector)vector, valueCount);
      } else if (vector instanceof TimeNanoVector) {
         writeTimeNanoData((TimeNanoVector)vector, valueCount);
      } else if (vector instanceof TimeSecVector) {
         writeTimeSecData((TimeSecVector)vector, valueCount);
      } else if (vector instanceof TimeStampSecVector) {
         writeTimeStampData((TimeStampSecVector)vector, valueCount);
      } else if (vector instanceof TimeStampMicroVector) {
         writeTimeStampData((TimeStampMicroVector)vector, valueCount);
      } else if (vector instanceof TimeStampMilliVector) {
         writeTimeStampData((TimeStampMilliVector)vector, valueCount);
      } else if (vector instanceof TimeStampNanoVector) {
         writeTimeStampData((TimeStampNanoVector)vector, valueCount);
      } else if (vector instanceof TimeStampSecTZVector) {
         writeTimeStampData((TimeStampSecTZVector)vector, valueCount);
      } else if (vector instanceof TimeStampMicroTZVector) {
         writeTimeStampData((TimeStampMicroTZVector)vector, valueCount);
      } else if (vector instanceof TimeStampMilliTZVector) {
         writeTimeStampData((TimeStampMilliTZVector)vector, valueCount);
      } else if (vector instanceof TimeStampNanoTZVector) {
         writeTimeStampData((TimeStampNanoTZVector)vector, valueCount);
      } else if (vector instanceof UInt1Vector) {
         writeUInt1Data((UInt1Vector)vector, valueCount);
      } else if (vector instanceof UInt2Vector) {
         writeUInt2Data((UInt2Vector)vector, valueCount);
      } else if (vector instanceof UInt4Vector) {
         writeUInt4Data((UInt4Vector)vector, valueCount);
      } else if (vector instanceof UInt8Vector) {
         writeUInt8Data((UInt8Vector)vector, valueCount);
      }

   }

   private static void writeTimeStampData(TimeStampVector vector, int valueCount) {
      long even = 100000L;
      long odd = 200000L;

      for(int i = 0; i < valueCount; ++i) {
         if (i % 2 == 0) {
            vector.setSafe(i, 100000L);
         } else {
            vector.setSafe(i, 200000L);
         }
      }

      vector.setValueCount(valueCount);
   }

   private static void writeDecimalData(DecimalVector vector, int valueCount) {
      BigDecimal even = new BigDecimal("0.0543278923");
      BigDecimal odd = new BigDecimal("2.0543278923");

      for(int i = 0; i < valueCount; ++i) {
         if (i % 2 == 0) {
            vector.setSafe(i, even);
         } else {
            vector.setSafe(i, odd);
         }
      }

      vector.setValueCount(valueCount);
   }

   private static void writeIntData(IntVector vector, int valueCount) {
      int even = 1000;
      int odd = 2000;

      for(int i = 0; i < valueCount; ++i) {
         if (i % 2 == 0) {
            vector.setSafe(i, 1000);
         } else {
            vector.setSafe(i, 2000);
         }
      }

      vector.setValueCount(valueCount);
   }

   private static void writeBooleanData(BitVector vector, int valueCount) {
      int even = 0;
      int odd = 1;

      for(int i = 0; i < valueCount; ++i) {
         if (i % 2 == 0) {
            vector.setSafe(i, 0);
         } else {
            vector.setSafe(i, 1);
         }
      }

      vector.setValueCount(valueCount);
   }

   private static void writeIntervalYearData(IntervalYearVector vector, int valueCount) {
      int even = 1;
      int odd = 2;

      for(int i = 0; i < valueCount; ++i) {
         if (i % 2 == 0) {
            vector.setSafe(i, 1);
         } else {
            vector.setSafe(i, 2);
         }
      }

      vector.setValueCount(valueCount);
   }

   private static void writeIntervalDayData(IntervalDayVector vector, int valueCount) {
      for(int i = 0; i < valueCount; ++i) {
         if (i % 2 == 0) {
            vector.setSafe(i, 1, 50);
         } else {
            vector.setSafe(i, 2, 100);
         }
      }

      vector.setValueCount(valueCount);
   }

   private static void writeTimeSecData(TimeSecVector vector, int valueCount) {
      int even = 500;
      int odd = 900;

      for(int i = 0; i < valueCount; ++i) {
         if (i % 2 == 0) {
            vector.setSafe(i, 500);
         } else {
            vector.setSafe(i, 900);
         }
      }

      vector.setValueCount(valueCount);
   }

   private static void writeTimeMilliData(TimeMilliVector vector, int valueCount) {
      int even = 1000;
      int odd = 2000;

      for(int i = 0; i < valueCount; ++i) {
         if (i % 2 == 0) {
            vector.setSafe(i, 1000);
         } else {
            vector.setSafe(i, 2000);
         }
      }

      vector.setValueCount(valueCount);
   }

   private static void writeTimeMicroData(TimeMicroVector vector, int valueCount) {
      long even = 1000000000L;
      long odd = 2000000000L;

      for(int i = 0; i < valueCount; ++i) {
         if (i % 2 == 0) {
            vector.setSafe(i, 1000000000L);
         } else {
            vector.setSafe(i, 2000000000L);
         }
      }

      vector.setValueCount(valueCount);
   }

   private static void writeTimeNanoData(TimeNanoVector vector, int valueCount) {
      long even = 1000000000L;
      long odd = 2000000000L;

      for(int i = 0; i < valueCount; ++i) {
         if (i % 2 == 0) {
            vector.setSafe(i, 1000000000L);
         } else {
            vector.setSafe(i, 2000000000L);
         }
      }

      vector.setValueCount(valueCount);
   }

   private static void writeDateDayData(DateDayVector vector, int valueCount) {
      int even = 1000;
      int odd = 2000;

      for(int i = 0; i < valueCount; ++i) {
         if (i % 2 == 0) {
            vector.setSafe(i, 1000);
         } else {
            vector.setSafe(i, 2000);
         }
      }

      vector.setValueCount(valueCount);
   }

   private static void writeDateMilliData(DateMilliVector vector, int valueCount) {
      long even = 1000000000L;
      long odd = 2000000000L;

      for(int i = 0; i < valueCount; ++i) {
         if (i % 2 == 0) {
            vector.setSafe(i, 1000000000L);
         } else {
            vector.setSafe(i, 2000000000L);
         }
      }

      vector.setValueCount(valueCount);
   }

   private static void writeSmallIntData(SmallIntVector vector, int valueCount) {
      short even = 10;
      short odd = 20;

      for(int i = 0; i < valueCount; ++i) {
         if (i % 2 == 0) {
            vector.setSafe(i, (short)10);
         } else {
            vector.setSafe(i, (short)20);
         }
      }

      vector.setValueCount(valueCount);
   }

   private static void writeTinyIntData(TinyIntVector vector, int valueCount) {
      byte even = 1;
      byte odd = 2;

      for(int i = 0; i < valueCount; ++i) {
         if (i % 2 == 0) {
            vector.setSafe(i, (byte)1);
         } else {
            vector.setSafe(i, (byte)2);
         }
      }

      vector.setValueCount(valueCount);
   }

   private static void writeUInt1Data(UInt1Vector vector, int valueCount) {
      byte even = 1;
      byte odd = 2;

      for(int i = 0; i < valueCount; ++i) {
         if (i % 2 == 0) {
            vector.setSafe(i, (byte)1);
         } else {
            vector.setSafe(i, (byte)2);
         }
      }

      vector.setValueCount(valueCount);
   }

   private static void writeUInt2Data(UInt2Vector vector, int valueCount) {
      short even = 10;
      short odd = 20;

      for(int i = 0; i < valueCount; ++i) {
         if (i % 2 == 0) {
            vector.setSafe(i, (int)10);
         } else {
            vector.setSafe(i, (int)20);
         }
      }

      vector.setValueCount(valueCount);
   }

   private static void writeUInt4Data(UInt4Vector vector, int valueCount) {
      int even = 1000;
      int odd = 2000;

      for(int i = 0; i < valueCount; ++i) {
         if (i % 2 == 0) {
            vector.setSafe(i, 1000);
         } else {
            vector.setSafe(i, 2000);
         }
      }

      vector.setValueCount(valueCount);
   }

   private static void writeUInt8Data(UInt8Vector vector, int valueCount) {
      long even = 1000000000L;
      long odd = 2000000000L;

      for(int i = 0; i < valueCount; ++i) {
         if (i % 2 == 0) {
            vector.setSafe(i, 1000000000L);
         } else {
            vector.setSafe(i, 2000000000L);
         }
      }

      vector.setValueCount(valueCount);
   }

   private static void writeBigIntData(BigIntVector vector, int valueCount) {
      long even = 1000000000L;
      long odd = 2000000000L;

      for(int i = 0; i < valueCount; ++i) {
         if (i % 2 == 0) {
            vector.setSafe(i, 1000000000L);
         } else {
            vector.setSafe(i, 2000000000L);
         }
      }

      vector.setValueCount(valueCount);
   }

   private static void writeFloatData(Float4Vector vector, int valueCount) {
      float even = 20.3F;
      float odd = 40.2F;

      for(int i = 0; i < valueCount; ++i) {
         if (i % 2 == 0) {
            vector.setSafe(i, 20.3F);
         } else {
            vector.setSafe(i, 40.2F);
         }
      }

      vector.setValueCount(valueCount);
   }

   private static void writeDoubleData(Float8Vector vector, int valueCount) {
      double even = 20.2373;
      double odd = 40.2378;

      for(int i = 0; i < valueCount; ++i) {
         if (i % 2 == 0) {
            vector.setSafe(i, 20.2373);
         } else {
            vector.setSafe(i, 40.2378);
         }
      }

      vector.setValueCount(valueCount);
   }

   private static void writeVarBinaryData(VarBinaryVector vector, int valueCount) {
      Charset utf8Charset = Charset.forName("UTF-8");
      byte[] even = "AAAAA1".getBytes(utf8Charset);
      byte[] odd = "BBBBBBBBB2".getBytes(utf8Charset);

      for(int i = 0; i < valueCount; ++i) {
         if (i % 2 == 0) {
            vector.setSafe(i, (byte[])even);
         } else {
            vector.setSafe(i, (byte[])odd);
         }
      }

      vector.setValueCount(valueCount);
   }

   private static void writeVarCharData(VarCharVector vector, int valueCount) {
      Charset utf8Charset = Charset.forName("UTF-8");
      byte[] even = "AAAAA1".getBytes(utf8Charset);
      byte[] odd = "BBBBBBBBB2".getBytes(utf8Charset);

      for(int i = 0; i < valueCount; ++i) {
         if (i % 2 == 0) {
            vector.setSafe(i, (byte[])even);
         } else {
            vector.setSafe(i, (byte[])odd);
         }
      }

      vector.setValueCount(valueCount);
   }
}
