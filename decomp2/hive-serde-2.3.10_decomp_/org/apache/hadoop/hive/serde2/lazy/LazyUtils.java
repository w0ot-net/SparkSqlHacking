package org.apache.hadoop.hive.serde2.lazy;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.util.Arrays;
import org.apache.commons.codec.binary.Base64;
import org.apache.hadoop.hive.serde2.SerDeException;
import org.apache.hadoop.hive.serde2.io.HiveCharWritable;
import org.apache.hadoop.hive.serde2.io.HiveVarcharWritable;
import org.apache.hadoop.hive.serde2.objectinspector.PrimitiveObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.BinaryObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.BooleanObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.ByteObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.DateObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.DoubleObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.FloatObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.HiveCharObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.HiveDecimalObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.HiveIntervalDayTimeObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.HiveIntervalYearMonthObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.HiveVarcharObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.IntObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.LongObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.ShortObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.StringObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.TimestampObjectInspector;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Text;

public final class LazyUtils {
   public static byte[] trueBytes = new byte[]{116, 114, 117, 101};
   public static byte[] falseBytes = new byte[]{102, 97, 108, 115, 101};

   public static int digit(int b, int radix) {
      int r = -1;
      if (b >= 48 && b <= 57) {
         r = b - 48;
      } else if (b >= 65 && b <= 90) {
         r = b - 65 + 10;
      } else if (b >= 97 && b <= 122) {
         r = b - 97 + 10;
      }

      if (r >= radix) {
         r = -1;
      }

      return r;
   }

   public static boolean isNumberMaybe(byte[] buf, int offset, int len) {
      switch (len) {
         case 0:
            return false;
         case 1:
            return Character.isDigit(buf[offset]);
         case 2:
            return Character.isDigit(buf[offset + 1]) || Character.isDigit(buf[offset + 0]);
         case 4:
            if (buf[offset] == 78 || buf[offset] == 110) {
               return false;
            }
         case 3:
         default:
            return true;
      }
   }

   public static boolean isDateMaybe(byte[] buf, int offset, int len) {
      return len >= 8;
   }

   public static int compare(byte[] b1, int start1, int length1, byte[] b2, int start2, int length2) {
      int min = Math.min(length1, length2);

      for(int i = 0; i < min; ++i) {
         if (b1[start1 + i] != b2[start2 + i]) {
            if (b1[start1 + i] < b2[start2 + i]) {
               return -1;
            }

            return 1;
         }
      }

      if (length1 < length2) {
         return -1;
      } else if (length1 > length2) {
         return 1;
      } else {
         return 0;
      }
   }

   public static String convertToString(byte[] bytes, int start, int length) {
      try {
         return Text.decode(bytes, start, length);
      } catch (CharacterCodingException var4) {
         return null;
      }
   }

   public static void writeEscaped(OutputStream out, byte[] bytes, int start, int len, boolean escaped, byte escapeChar, boolean[] needsEscape) throws IOException {
      if (escaped) {
         int end = start + len;

         for(int i = start; i <= end; ++i) {
            if (i == end || needsEscape[bytes[i] & 255]) {
               if (i > start) {
                  out.write(bytes, start, i - start);
               }

               if (i == end) {
                  break;
               }

               out.write(escapeChar);
               if (bytes[i] == 13) {
                  out.write(114);
                  start = i + 1;
               } else if (bytes[i] == 10) {
                  out.write(110);
                  start = i + 1;
               } else {
                  start = i;
               }
            }
         }
      } else {
         out.write(bytes, start, len);
      }

   }

   public static void writePrimitiveUTF8(OutputStream out, Object o, PrimitiveObjectInspector oi, boolean escaped, byte escapeChar, boolean[] needsEscape) throws IOException {
      switch (oi.getPrimitiveCategory()) {
         case BOOLEAN:
            boolean b = ((BooleanObjectInspector)oi).get(o);
            if (b) {
               out.write(trueBytes, 0, trueBytes.length);
            } else {
               out.write(falseBytes, 0, falseBytes.length);
            }
            break;
         case BYTE:
            LazyInteger.writeUTF8(out, ((ByteObjectInspector)oi).get(o));
            break;
         case SHORT:
            LazyInteger.writeUTF8(out, ((ShortObjectInspector)oi).get(o));
            break;
         case INT:
            LazyInteger.writeUTF8(out, ((IntObjectInspector)oi).get(o));
            break;
         case LONG:
            LazyLong.writeUTF8(out, ((LongObjectInspector)oi).get(o));
            break;
         case FLOAT:
            float f = ((FloatObjectInspector)oi).get(o);
            ByteBuffer b = Text.encode(String.valueOf(f));
            out.write(b.array(), 0, b.limit());
            break;
         case DOUBLE:
            double d = ((DoubleObjectInspector)oi).get(o);
            ByteBuffer b = Text.encode(String.valueOf(d));
            out.write(b.array(), 0, b.limit());
            break;
         case STRING:
            Text t = ((StringObjectInspector)oi).getPrimitiveWritableObject(o);
            writeEscaped(out, t.getBytes(), 0, t.getLength(), escaped, escapeChar, needsEscape);
            break;
         case CHAR:
            HiveCharWritable hc = ((HiveCharObjectInspector)oi).getPrimitiveWritableObject(o);
            Text t = hc.getPaddedValue();
            writeEscaped(out, t.getBytes(), 0, t.getLength(), escaped, escapeChar, needsEscape);
            break;
         case VARCHAR:
            HiveVarcharWritable hc = ((HiveVarcharObjectInspector)oi).getPrimitiveWritableObject(o);
            Text t = hc.getTextValue();
            writeEscaped(out, t.getBytes(), 0, t.getLength(), escaped, escapeChar, needsEscape);
            break;
         case BINARY:
            BytesWritable bw = ((BinaryObjectInspector)oi).getPrimitiveWritableObject(o);
            byte[] toEncode = new byte[bw.getLength()];
            System.arraycopy(bw.getBytes(), 0, toEncode, 0, bw.getLength());
            byte[] toWrite = Base64.encodeBase64(toEncode);
            out.write(toWrite, 0, toWrite.length);
            break;
         case DATE:
            LazyDate.writeUTF8(out, ((DateObjectInspector)oi).getPrimitiveWritableObject(o));
            break;
         case TIMESTAMP:
            LazyTimestamp.writeUTF8(out, ((TimestampObjectInspector)oi).getPrimitiveWritableObject(o));
            break;
         case INTERVAL_YEAR_MONTH:
            LazyHiveIntervalYearMonth.writeUTF8(out, ((HiveIntervalYearMonthObjectInspector)oi).getPrimitiveWritableObject(o));
            break;
         case INTERVAL_DAY_TIME:
            LazyHiveIntervalDayTime.writeUTF8(out, ((HiveIntervalDayTimeObjectInspector)oi).getPrimitiveWritableObject(o));
            break;
         case DECIMAL:
            HiveDecimalObjectInspector decimalOI = (HiveDecimalObjectInspector)oi;
            LazyHiveDecimal.writeUTF8(out, decimalOI.getPrimitiveJavaObject(o), decimalOI.scale());
            break;
         default:
            throw new RuntimeException("Hive internal error.");
      }

   }

   public static void writePrimitive(OutputStream out, Object o, PrimitiveObjectInspector oi) throws IOException {
      DataOutputStream dos = new DataOutputStream(out);

      try {
         switch (oi.getPrimitiveCategory()) {
            case BOOLEAN:
               boolean b = ((BooleanObjectInspector)oi).get(o);
               dos.writeBoolean(b);
               break;
            case BYTE:
               byte bt = ((ByteObjectInspector)oi).get(o);
               dos.writeByte(bt);
               break;
            case SHORT:
               short s = ((ShortObjectInspector)oi).get(o);
               dos.writeShort(s);
               break;
            case INT:
               int i = ((IntObjectInspector)oi).get(o);
               dos.writeInt(i);
               break;
            case LONG:
               long l = ((LongObjectInspector)oi).get(o);
               dos.writeLong(l);
               break;
            case FLOAT:
               float f = ((FloatObjectInspector)oi).get(o);
               dos.writeFloat(f);
               break;
            case DOUBLE:
               double d = ((DoubleObjectInspector)oi).get(o);
               dos.writeDouble(d);
               break;
            case STRING:
            case CHAR:
            case VARCHAR:
            default:
               throw new RuntimeException("Hive internal error.");
            case BINARY:
               BytesWritable bw = ((BinaryObjectInspector)oi).getPrimitiveWritableObject(o);
               out.write(bw.getBytes(), 0, bw.getLength());
         }
      } finally {
         dos.close();
      }

   }

   public static int hashBytes(byte[] data, int start, int len) {
      int hash = 1;

      for(int i = start; i < len; ++i) {
         hash = 31 * hash + data[i];
      }

      return hash;
   }

   public static byte[] createByteArray(BytesWritable sourceBw) {
      return Arrays.copyOf(sourceBw.getBytes(), sourceBw.getLength());
   }

   static byte getSeparator(byte[] separators, int level) throws SerDeException {
      try {
         return separators[level];
      } catch (ArrayIndexOutOfBoundsException e) {
         String msg = "Number of levels of nesting supported for LazySimpleSerde is " + (separators.length - 1) + " Unable to work with level " + level;
         String txt = ". Use %s serde property for tables using LazySimpleSerde.";
         if (separators.length < 9) {
            msg = msg + String.format(txt, "hive.serialization.extend.nesting.levels");
         } else if (separators.length < 25) {
            msg = msg + String.format(txt, "hive.serialization.extend.additional.nesting.levels");
         }

         throw new SerDeException(msg, e);
      }
   }

   public static void copyAndEscapeStringDataToText(byte[] inputBytes, int start, int length, byte escapeChar, Text data) {
      int outputLength = 0;

      for(int i = 0; i < length; ++i) {
         if (inputBytes[start + i] != escapeChar) {
            ++outputLength;
         } else {
            ++outputLength;
            ++i;
         }
      }

      data.set(inputBytes, start, outputLength);
      if (outputLength < length) {
         int k = 0;
         byte[] outputBytes = data.getBytes();

         for(int i = 0; i < length; ++i) {
            byte b = inputBytes[start + i];
            if (b == escapeChar && i < length - 1) {
               ++i;
               if (inputBytes[start + i] == 114) {
                  outputBytes[k++] = 13;
               } else if (inputBytes[start + i] == 110) {
                  outputBytes[k++] = 10;
               } else {
                  outputBytes[k++] = inputBytes[start + i];
               }
            } else {
               outputBytes[k++] = b;
            }
         }

         assert k == outputLength;
      }

   }

   public static byte getByte(String altValue, byte defaultVal) {
      if (altValue != null && altValue.length() > 0) {
         try {
            return Byte.parseByte(altValue);
         } catch (NumberFormatException var3) {
            return (byte)altValue.charAt(0);
         }
      } else {
         return defaultVal;
      }
   }

   private LazyUtils() {
   }
}
