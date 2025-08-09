package org.datanucleus.util;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Locale;
import java.util.TimeZone;
import java.util.UUID;
import org.datanucleus.ExecutionContext;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.FieldRole;

public class TypeConversionHelper {
   private static int NR_BIGINTEGER_BYTES = 40;
   private static int NR_SCALE_BYTES = 4;
   private static int NR_SIGNAL_BYTES = 1;
   private static int TOTAL_BYTES;
   private static final String ZEROES = "000000000";

   public static boolean[] getBooleanArrayFromBitSet(BitSet value) {
      if (value == null) {
         return null;
      } else {
         boolean[] a = new boolean[value.length()];

         for(int i = 0; i < a.length; ++i) {
            a[i] = value.get(i);
         }

         return a;
      }
   }

   public static BitSet getBitSetFromBooleanArray(boolean[] buf) {
      BitSet set = new BitSet();

      for(int i = 0; i < buf.length; ++i) {
         if (buf[i]) {
            set.set(i);
         }
      }

      return set;
   }

   public static boolean[] getBooleanArrayFromByteArray(byte[] buf) {
      int n = buf.length;
      boolean[] a = new boolean[n];

      for(int i = 0; i < n; ++i) {
         a[i] = buf[i] != 0;
      }

      return a;
   }

   public static byte[] getByteArrayFromBooleanArray(Object value) {
      if (value == null) {
         return null;
      } else {
         boolean[] a = (boolean[])value;
         int n = a.length;
         byte[] buf = new byte[n];

         for(int i = 0; i < n; ++i) {
            buf[i] = (byte)(a[i] ? 1 : 0);
         }

         return buf;
      }
   }

   public static char[] getCharArrayFromByteArray(byte[] buf) {
      int n = buf.length / 2;
      char[] a = new char[n];
      int i = 0;

      for(int j = 0; i < n; a[i++] = (char)(((buf[j++] & 255) << 8) + (buf[j++] & 255))) {
      }

      return a;
   }

   public static byte[] getByteArrayFromCharArray(Object value) {
      if (value == null) {
         return null;
      } else {
         char[] a = (char[])value;
         int n = a.length;
         byte[] buf = new byte[n * 2];
         int i = 0;

         char x;
         for(int j = 0; i < n; buf[j++] = (byte)(x & 255)) {
            x = a[i++];
            buf[j++] = (byte)(x >>> 8 & 255);
         }

         return buf;
      }
   }

   public static double[] getDoubleArrayFromByteArray(byte[] buf) {
      int n = buf.length / 8;
      double[] a = new double[n];
      int i = 0;

      for(int j = 0; i < n; a[i++] = Double.longBitsToDouble(((long)(buf[j++] & 255) << 56) + ((long)(buf[j++] & 255) << 48) + ((long)(buf[j++] & 255) << 40) + ((long)(buf[j++] & 255) << 32) + ((long)(buf[j++] & 255) << 24) + (long)((buf[j++] & 255) << 16) + (long)((buf[j++] & 255) << 8) + (long)(buf[j++] & 255))) {
      }

      return a;
   }

   public static byte[] getByteArrayFromDoubleArray(Object value) {
      if (value == null) {
         return null;
      } else {
         double[] a = (double[])value;
         int n = a.length;
         byte[] buf = new byte[n * 8];
         int i = 0;

         long x;
         for(int j = 0; i < n; buf[j++] = (byte)((int)(x & 255L))) {
            x = Double.doubleToRawLongBits(a[i++]);
            buf[j++] = (byte)((int)(x >>> 56 & 255L));
            buf[j++] = (byte)((int)(x >>> 48 & 255L));
            buf[j++] = (byte)((int)(x >>> 40 & 255L));
            buf[j++] = (byte)((int)(x >>> 32 & 255L));
            buf[j++] = (byte)((int)(x >>> 24 & 255L));
            buf[j++] = (byte)((int)(x >>> 16 & 255L));
            buf[j++] = (byte)((int)(x >>> 8 & 255L));
         }

         return buf;
      }
   }

   public static float[] getFloatArrayFromByteArray(byte[] buf) {
      int n = buf.length / 4;
      float[] a = new float[n];
      int i = 0;

      for(int j = 0; i < n; a[i++] = Float.intBitsToFloat(((buf[j++] & 255) << 24) + ((buf[j++] & 255) << 16) + ((buf[j++] & 255) << 8) + (buf[j++] & 255))) {
      }

      return a;
   }

   public static byte[] getByteArrayFromFloatArray(Object value) {
      if (value == null) {
         return null;
      } else {
         float[] a = (float[])value;
         int n = a.length;
         byte[] buf = new byte[n * 4];
         int i = 0;

         int x;
         for(int j = 0; i < n; buf[j++] = (byte)(x & 255)) {
            x = Float.floatToRawIntBits(a[i++]);
            buf[j++] = (byte)(x >>> 24 & 255);
            buf[j++] = (byte)(x >>> 16 & 255);
            buf[j++] = (byte)(x >>> 8 & 255);
         }

         return buf;
      }
   }

   public static int[] getIntArrayFromByteArray(byte[] buf) {
      int n = buf.length / 4;
      int[] a = new int[n];
      int i = 0;

      for(int j = 0; i < n; a[i++] = ((buf[j++] & 255) << 24) + ((buf[j++] & 255) << 16) + ((buf[j++] & 255) << 8) + (buf[j++] & 255)) {
      }

      return a;
   }

   public static byte[] getByteArrayFromIntArray(Object value) {
      if (value == null) {
         return null;
      } else {
         int[] a = (int[])value;
         int n = a.length;
         byte[] buf = new byte[n * 4];
         int i = 0;

         int x;
         for(int j = 0; i < n; buf[j++] = (byte)(x & 255)) {
            x = a[i++];
            buf[j++] = (byte)(x >>> 24 & 255);
            buf[j++] = (byte)(x >>> 16 & 255);
            buf[j++] = (byte)(x >>> 8 & 255);
         }

         return buf;
      }
   }

   public static long[] getLongArrayFromByteArray(byte[] buf) {
      int n = buf.length / 8;
      long[] a = new long[n];
      int i = 0;

      for(int j = 0; i < n; a[i++] = ((long)(buf[j++] & 255) << 56) + ((long)(buf[j++] & 255) << 48) + ((long)(buf[j++] & 255) << 40) + ((long)(buf[j++] & 255) << 32) + ((long)(buf[j++] & 255) << 24) + (long)((buf[j++] & 255) << 16) + (long)((buf[j++] & 255) << 8) + (long)(buf[j++] & 255)) {
      }

      return a;
   }

   public static byte[] getByteArrayFromLongArray(Object value) {
      if (value == null) {
         return null;
      } else {
         long[] a = (long[])value;
         int n = a.length;
         byte[] buf = new byte[n * 8];
         int i = 0;

         long x;
         for(int j = 0; i < n; buf[j++] = (byte)((int)(x & 255L))) {
            x = a[i++];
            buf[j++] = (byte)((int)(x >>> 56 & 255L));
            buf[j++] = (byte)((int)(x >>> 48 & 255L));
            buf[j++] = (byte)((int)(x >>> 40 & 255L));
            buf[j++] = (byte)((int)(x >>> 32 & 255L));
            buf[j++] = (byte)((int)(x >>> 24 & 255L));
            buf[j++] = (byte)((int)(x >>> 16 & 255L));
            buf[j++] = (byte)((int)(x >>> 8 & 255L));
         }

         return buf;
      }
   }

   public static short[] getShortArrayFromByteArray(byte[] buf) {
      int n = buf.length / 2;
      short[] a = new short[n];
      int i = 0;

      for(int j = 0; i < n; a[i++] = (short)(((buf[j++] & 255) << 8) + (buf[j++] & 255))) {
      }

      return a;
   }

   public static byte[] getByteArrayFromShortArray(Object value) {
      if (value == null) {
         return null;
      } else {
         short[] a = (short[])value;
         int n = a.length;
         byte[] buf = new byte[n * 2];
         int i = 0;

         short x;
         for(int j = 0; i < n; buf[j++] = (byte)(x & 255)) {
            x = a[i++];
            buf[j++] = (byte)(x >>> 8 & 255);
         }

         return buf;
      }
   }

   public static byte[] getByteArrayFromBigDecimalArray(Object value) {
      if (value == null) {
         return null;
      } else {
         BigDecimal[] a = (BigDecimal[])value;
         byte[] total = new byte[a.length * TOTAL_BYTES];
         int index = 0;

         for(int i = 0; i < a.length; ++i) {
            System.arraycopy(new byte[]{(byte)a[i].signum()}, 0, total, index, NR_SIGNAL_BYTES);
            index += NR_SIGNAL_BYTES;
            byte[] b = a[i].unscaledValue().abs().toByteArray();
            System.arraycopy(b, 0, total, index + (NR_BIGINTEGER_BYTES - b.length), b.length);
            index += NR_BIGINTEGER_BYTES;
            byte[] s = getByteArrayFromIntArray(new int[]{a[i].scale()});
            System.arraycopy(s, 0, total, index, NR_SCALE_BYTES);
            index += NR_SCALE_BYTES;
         }

         return total;
      }
   }

   public static BigDecimal[] getBigDecimalArrayFromByteArray(byte[] buf) {
      BigDecimal[] a = new BigDecimal[buf.length / TOTAL_BYTES];
      int index = 0;

      for(int i = 0; i < a.length; ++i) {
         byte[] signal = new byte[NR_SIGNAL_BYTES];
         System.arraycopy(buf, index, signal, 0, NR_SIGNAL_BYTES);
         index += NR_SIGNAL_BYTES;
         byte[] b = new byte[NR_BIGINTEGER_BYTES];
         System.arraycopy(buf, index, b, 0, NR_BIGINTEGER_BYTES);
         BigInteger integer = new BigInteger(signal[0], b);
         index += NR_BIGINTEGER_BYTES;
         byte[] s = new byte[4];
         System.arraycopy(buf, index, s, 0, NR_SCALE_BYTES);
         int[] scale = getIntArrayFromByteArray(s);
         a[i] = new BigDecimal(integer, scale[0]);
         index += NR_SCALE_BYTES;
      }

      return a;
   }

   public static byte[] getByteArrayFromBigIntegerArray(Object value) {
      if (value == null) {
         return null;
      } else {
         BigInteger[] a = (BigInteger[])value;
         long[] d = new long[a.length];

         for(int i = 0; i < a.length; ++i) {
            d[i] = a[i].longValue();
         }

         return getByteArrayFromLongArray(d);
      }
   }

   public static BigInteger[] getBigIntegerArrayFromByteArray(byte[] buf) {
      long[] d = getLongArrayFromByteArray(buf);
      BigInteger[] a = new BigInteger[d.length];

      for(int i = 0; i < a.length; ++i) {
         a[i] = BigInteger.valueOf(d[i]);
      }

      return a;
   }

   public static byte[] getByteArrayFromBooleanObjectArray(Object value) {
      if (value == null) {
         return null;
      } else {
         Boolean[] a = (Boolean[])value;
         boolean[] d = new boolean[a.length];

         for(int i = 0; i < a.length; ++i) {
            d[i] = a[i];
         }

         return getByteArrayFromBooleanArray(d);
      }
   }

   public static Boolean[] getBooleanObjectArrayFromByteArray(byte[] buf) {
      boolean[] d = getBooleanArrayFromByteArray(buf);
      Boolean[] a = new Boolean[d.length];

      for(int i = 0; i < a.length; ++i) {
         a[i] = d[i];
      }

      return a;
   }

   public static byte[] getByteArrayFromByteObjectArray(Object value) {
      if (value == null) {
         return null;
      } else {
         Byte[] a = (Byte[])value;
         byte[] d = new byte[a.length];

         for(int i = 0; i < a.length; ++i) {
            d[i] = a[i];
         }

         return d;
      }
   }

   public static Byte[] getByteObjectArrayFromByteArray(byte[] buf) {
      if (buf == null) {
         return null;
      } else {
         Byte[] a = new Byte[buf.length];

         for(int i = 0; i < a.length; ++i) {
            a[i] = buf[i];
         }

         return a;
      }
   }

   public static byte[] getByteArrayFromCharObjectArray(Object value) {
      if (value == null) {
         return null;
      } else {
         Character[] a = (Character[])value;
         char[] d = new char[a.length];

         for(int i = 0; i < a.length; ++i) {
            d[i] = a[i];
         }

         return getByteArrayFromCharArray(d);
      }
   }

   public static Character[] getCharObjectArrayFromByteArray(byte[] buf) {
      char[] d = getCharArrayFromByteArray(buf);
      Character[] a = new Character[d.length];

      for(int i = 0; i < a.length; ++i) {
         a[i] = d[i];
      }

      return a;
   }

   public static byte[] getByteArrayFromDoubleObjectArray(Object value) {
      if (value == null) {
         return null;
      } else {
         Double[] a = (Double[])value;
         double[] d = new double[a.length];

         for(int i = 0; i < a.length; ++i) {
            d[i] = a[i];
         }

         return getByteArrayFromDoubleArray(d);
      }
   }

   public static Double[] getDoubleObjectArrayFromByteArray(byte[] buf) {
      double[] d = getDoubleArrayFromByteArray(buf);
      Double[] a = new Double[d.length];

      for(int i = 0; i < a.length; ++i) {
         a[i] = new Double(d[i]);
      }

      return a;
   }

   public static byte[] getByteArrayFromFloatObjectArray(Object value) {
      if (value == null) {
         return null;
      } else {
         Float[] a = (Float[])value;
         float[] d = new float[a.length];

         for(int i = 0; i < a.length; ++i) {
            d[i] = a[i];
         }

         return getByteArrayFromFloatArray(d);
      }
   }

   public static Float[] getFloatObjectArrayFromByteArray(byte[] buf) {
      float[] d = getFloatArrayFromByteArray(buf);
      Float[] a = new Float[d.length];

      for(int i = 0; i < a.length; ++i) {
         a[i] = new Float(d[i]);
      }

      return a;
   }

   public static byte[] getByteArrayFromIntObjectArray(Object value) {
      if (value == null) {
         return null;
      } else {
         Integer[] a = (Integer[])value;
         int[] d = new int[a.length];

         for(int i = 0; i < a.length; ++i) {
            d[i] = a[i];
         }

         return getByteArrayFromIntArray(d);
      }
   }

   public static Integer[] getIntObjectArrayFromByteArray(byte[] buf) {
      int[] d = getIntArrayFromByteArray(buf);
      Integer[] a = new Integer[d.length];

      for(int i = 0; i < a.length; ++i) {
         a[i] = d[i];
      }

      return a;
   }

   public static byte[] getByteArrayFromLongObjectArray(Object value) {
      if (value == null) {
         return null;
      } else {
         Long[] a = (Long[])value;
         long[] d = new long[a.length];

         for(int i = 0; i < a.length; ++i) {
            d[i] = a[i];
         }

         return getByteArrayFromLongArray(d);
      }
   }

   public static Long[] getLongObjectArrayFromByteArray(byte[] buf) {
      long[] d = getLongArrayFromByteArray(buf);
      Long[] a = new Long[d.length];

      for(int i = 0; i < a.length; ++i) {
         a[i] = d[i];
      }

      return a;
   }

   public static byte[] getByteArrayFromShortObjectArray(Object value) {
      if (value == null) {
         return null;
      } else {
         Short[] a = (Short[])value;
         short[] d = new short[a.length];

         for(int i = 0; i < a.length; ++i) {
            d[i] = a[i];
         }

         return getByteArrayFromShortArray(d);
      }
   }

   public static Short[] getShortObjectArrayFromByteArray(byte[] buf) {
      short[] d = getShortArrayFromByteArray(buf);
      Short[] a = new Short[d.length];

      for(int i = 0; i < a.length; ++i) {
         a[i] = d[i];
      }

      return a;
   }

   public static Object convertTo(Object value, Class type) {
      if (type != null && value != null) {
         if (!type.isAssignableFrom(value.getClass())) {
            if (type == Short.TYPE || type == Short.class) {
               return Short.valueOf(value.toString());
            }

            if (type == Character.TYPE || type == Character.class) {
               return value.toString().charAt(0);
            }

            if (type == Integer.TYPE || type == Integer.class) {
               return Integer.valueOf(value.toString());
            }

            if (type == Long.TYPE || type == Long.class) {
               return Long.valueOf(value.toString());
            }

            if (type == Boolean.TYPE || type == Boolean.class) {
               return Boolean.valueOf(value.toString());
            }

            if (type == Byte.TYPE || type == Byte.class) {
               return Byte.valueOf(value.toString());
            }

            if (type == Float.TYPE || type == Float.class) {
               return Float.valueOf(value.toString());
            }

            if (type == Double.TYPE || type == Double.class) {
               return Double.valueOf(value.toString());
            }

            if (type == BigDecimal.class) {
               return new BigDecimal(value.toString());
            }

            if (type == BigInteger.class) {
               return new BigInteger(value.toString());
            }

            if (type == Timestamp.class) {
               if (value instanceof Date) {
                  return new Timestamp(((Date)value).getTime());
               }

               return Timestamp.valueOf(value.toString());
            }

            if (type == String.class) {
               return value.toString();
            }

            if (type == UUID.class) {
               if (value instanceof String) {
                  return UUID.fromString((String)value);
               }
            } else if (type == TimeZone.class) {
               if (value instanceof String) {
                  return TimeZone.getTimeZone((String)value);
               }
            } else if (type == Currency.class) {
               if (value instanceof String) {
                  return Currency.getInstance((String)value);
               }
            } else if (type == Locale.class && value instanceof String) {
               return I18nUtils.getLocaleFromString((String)value);
            }

            NucleusLogger.PERSISTENCE.warn("Request to convert value of type " + value.getClass().getName() + " to type " + type.getName() + " but this is not yet supported.Raise a JIRA issue and contribute the code to support this conversion, with a testcase that demonstrates the problem");
         }

         return value;
      } else {
         return value;
      }
   }

   public static byte[] getBytesFromInt(int val) {
      byte[] arr = new byte[4];

      for(int i = 3; i >= 0; --i) {
         arr[i] = (byte)((int)((255L & (long)val) + -128L));
         val >>>= 8;
      }

      return arr;
   }

   public static byte[] getBytesFromShort(short val) {
      byte[] arr = new byte[2];

      for(int i = 1; i >= 0; --i) {
         arr[i] = (byte)((int)((255L & (long)val) + -128L));
         val = (short)(val >>> 8);
      }

      return arr;
   }

   public static String getStringFromInt(int val) {
      byte[] arr = new byte[4];

      for(int i = 3; i >= 0; --i) {
         arr[i] = (byte)((int)((255L & (long)val) + -128L));
         val >>>= 8;
      }

      return new String(arr);
   }

   public static String getStringFromShort(short val) {
      byte[] arr = new byte[2];

      for(int i = 1; i >= 0; --i) {
         arr[i] = (byte)((int)((255L & (long)val) + -128L));
         val = (short)(val >>> 8);
      }

      return new String(arr);
   }

   public static String getHexFromInt(int val) {
      StringBuilder str = new StringBuilder("00000000");
      String hexstr = Integer.toHexString(val);
      str.replace(8 - hexstr.length(), 8, hexstr);
      return str.toString();
   }

   public static String getHexFromShort(short val) {
      StringBuilder str = new StringBuilder("0000");
      String hexstr = Integer.toHexString(val);
      str.replace(4 - hexstr.length(), 4, hexstr);
      return str.toString();
   }

   public static int getIntFromByteArray(byte[] bytes) {
      int val = 0;

      for(int i = 0; i < 4; ++i) {
         val = (val << 8) - -128 + bytes[i];
      }

      return val;
   }

   public static Timestamp stringToTimestamp(String s, Calendar cal) {
      int[] numbers = convertStringToIntArray(s);
      if (numbers != null && numbers.length >= 6) {
         int year = numbers[0];
         int month = numbers[1];
         int day = numbers[2];
         int hour = numbers[3];
         int minute = numbers[4];
         int second = numbers[5];
         int nanos = 0;
         if (numbers.length > 6) {
            String zeroedNanos = "" + numbers[6];
            if (zeroedNanos.length() < 9) {
               int numZerosToAdd = 9 - zeroedNanos.length();

               for(int i = 0; i < numZerosToAdd; ++i) {
                  zeroedNanos = zeroedNanos + "0";
               }

               nanos = Integer.valueOf(zeroedNanos);
            } else {
               nanos = numbers[6];
            }
         }

         Calendar thecal = cal;
         if (cal == null) {
            thecal = new GregorianCalendar();
         }

         thecal.set(0, 1);
         thecal.set(1, year);
         thecal.set(2, month - 1);
         thecal.set(5, day);
         thecal.set(11, hour);
         thecal.set(12, minute);
         thecal.set(13, second);
         Timestamp ts = new Timestamp(thecal.getTime().getTime());
         ts.setNanos(nanos);
         return ts;
      } else {
         throw new IllegalArgumentException(Localiser.msg("030003", s));
      }
   }

   private static int[] convertStringToIntArray(String str) {
      if (str == null) {
         return null;
      } else {
         int[] values = null;
         ArrayList list = new ArrayList();
         int start = -1;

         for(int i = 0; i < str.length(); ++i) {
            if (start == -1 && Character.isDigit(str.charAt(i))) {
               start = i;
            }

            if (start != i && start >= 0 && !Character.isDigit(str.charAt(i))) {
               list.add(Integer.valueOf(str.substring(start, i)));
               start = -1;
            }

            if (i == str.length() - 1 && start >= 0) {
               list.add(Integer.valueOf(str.substring(start)));
            }
         }

         if (list.size() > 0) {
            values = new int[list.size()];
            Iterator iter = list.iterator();

            for(int n = 0; iter.hasNext(); values[n++] = (Integer)iter.next()) {
            }
         }

         return values;
      }
   }

   public static String timestampToString(Timestamp ts, Calendar cal) {
      cal.setTime(ts);
      int year = cal.get(1);
      int month = cal.get(2) + 1;
      int day = cal.get(5);
      int hour = cal.get(11);
      int minute = cal.get(12);
      int second = cal.get(13);
      String yearString = Integer.toString(year);
      String monthString = month < 10 ? "0" + month : Integer.toString(month);
      String dayString = day < 10 ? "0" + day : Integer.toString(day);
      String hourString = hour < 10 ? "0" + hour : Integer.toString(hour);
      String minuteString = minute < 10 ? "0" + minute : Integer.toString(minute);
      String secondString = second < 10 ? "0" + second : Integer.toString(second);
      String nanosString = Integer.toString(ts.getNanos());
      if (ts.getNanos() != 0) {
         nanosString = "000000000".substring(0, "000000000".length() - nanosString.length()) + nanosString;

         int truncIndex;
         for(truncIndex = nanosString.length() - 1; nanosString.charAt(truncIndex) == '0'; --truncIndex) {
         }

         nanosString = nanosString.substring(0, truncIndex + 1);
      }

      return yearString + "-" + monthString + "-" + dayString + " " + hourString + ":" + minuteString + ":" + secondString + "." + nanosString;
   }

   public static int intFromString(String str, int dflt) {
      try {
         Integer val = Integer.valueOf(str);
         return val;
      } catch (NumberFormatException var3) {
         return dflt;
      }
   }

   public static Object getEnumFromValue(AbstractMemberMetaData mmd, FieldRole role, ExecutionContext ec, long longValue) {
      Class enumType = mmd.getType();
      String getterMethodName = null;
      if (role == FieldRole.ROLE_FIELD && mmd.hasExtension("enum-getter-by-value")) {
         getterMethodName = mmd.getValueForExtension("enum-getter-by-value");
      }

      if (role == FieldRole.ROLE_COLLECTION_ELEMENT) {
         enumType = ec.getClassLoaderResolver().classForName(mmd.getCollection().getElementType());
         if (mmd.getElementMetaData() != null && mmd.getElementMetaData().hasExtension("enum-getter-by-value")) {
            getterMethodName = mmd.getElementMetaData().getValueForExtension("enum-getter-by-value");
         }
      } else if (role == FieldRole.ROLE_ARRAY_ELEMENT) {
         enumType = ec.getClassLoaderResolver().classForName(mmd.getArray().getElementType());
         if (mmd.getElementMetaData() != null && mmd.getElementMetaData().hasExtension("enum-getter-by-value")) {
            getterMethodName = mmd.getElementMetaData().getValueForExtension("enum-getter-by-value");
         }
      } else if (role == FieldRole.ROLE_MAP_KEY) {
         enumType = ec.getClassLoaderResolver().classForName(mmd.getMap().getKeyType());
         if (mmd.getKeyMetaData() != null && mmd.getKeyMetaData().hasExtension("enum-getter-by-value")) {
            getterMethodName = mmd.getKeyMetaData().getValueForExtension("enum-getter-by-value");
         }
      } else if (role == FieldRole.ROLE_MAP_VALUE) {
         enumType = ec.getClassLoaderResolver().classForName(mmd.getMap().getValueType());
         if (mmd.getValueMetaData() != null && mmd.getValueMetaData().hasExtension("enum-getter-by-value")) {
            getterMethodName = mmd.getValueMetaData().getValueForExtension("enum-getter-by-value");
         }
      }

      if (getterMethodName != null) {
         try {
            Method getterMethod = ClassUtils.getMethodForClass(enumType, getterMethodName, new Class[]{Short.TYPE});
            return getterMethod.invoke((Object)null, (short)((int)longValue));
         } catch (Exception e) {
            NucleusLogger.PERSISTENCE.warn("Specified enum getter-by-value for field " + mmd.getFullFieldName() + " gave an error on extracting the enum so just using the ordinal : " + e.getMessage());

            try {
               Method getterMethod = ClassUtils.getMethodForClass(enumType, getterMethodName, new Class[]{Integer.TYPE});
               return getterMethod.invoke((Object)null, (int)longValue);
            } catch (Exception e) {
               NucleusLogger.PERSISTENCE.warn("Specified enum getter-by-value for field " + mmd.getFullFieldName() + " gave an error on extracting the enum so just using the ordinal : " + e.getMessage());
            }
         }
      }

      return enumType.getEnumConstants()[(int)longValue];
   }

   public static long getValueFromEnum(AbstractMemberMetaData mmd, FieldRole role, Enum myEnum) {
      String methodName = null;
      if (role == FieldRole.ROLE_FIELD) {
         if (mmd != null && mmd.hasExtension("enum-value-getter")) {
            methodName = mmd.getValueForExtension("enum-value-getter");
         }
      } else if (role != FieldRole.ROLE_COLLECTION_ELEMENT && role != FieldRole.ROLE_ARRAY_ELEMENT) {
         if (role == FieldRole.ROLE_MAP_KEY) {
            if (mmd != null && mmd.getKeyMetaData() != null && mmd.getKeyMetaData().hasExtension("enum-value-getter")) {
               methodName = mmd.getKeyMetaData().getValueForExtension("enum-value-getter");
            }
         } else if (role == FieldRole.ROLE_MAP_VALUE && mmd != null && mmd.getValueMetaData() != null && mmd.getValueMetaData().hasExtension("enum-value-getter")) {
            methodName = mmd.getValueMetaData().getValueForExtension("enum-value-getter");
         }
      } else if (mmd != null && mmd.getElementMetaData() != null && mmd.getElementMetaData().hasExtension("enum-value-getter")) {
         methodName = mmd.getElementMetaData().getValueForExtension("enum-value-getter");
      }

      if (methodName != null) {
         Long longVal = null;

         try {
            Method getterMethod = ClassUtils.getMethodForClass(myEnum.getClass(), methodName, (Class[])null);
            Number num = (Number)getterMethod.invoke(myEnum);
            longVal = num.longValue();
            if (longVal != null) {
               return (long)longVal.intValue();
            }
         } catch (Exception e) {
            NucleusLogger.PERSISTENCE.warn("Specified enum value-getter for method " + methodName + " on field " + mmd.getFullFieldName() + " gave an error on extracting the value", e);
         }
      }

      return (long)myEnum.ordinal();
   }

   static {
      TOTAL_BYTES = NR_BIGINTEGER_BYTES + NR_SCALE_BYTES + NR_SIGNAL_BYTES;
   }
}
