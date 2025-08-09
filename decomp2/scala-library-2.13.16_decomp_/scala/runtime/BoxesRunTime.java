package scala.runtime;

import scala.math.ScalaNumber;

public final class BoxesRunTime {
   private static final int CHAR = 0;
   private static final int INT = 3;
   private static final int LONG = 4;
   private static final int FLOAT = 5;
   private static final int DOUBLE = 6;
   private static final int OTHER = 7;

   private static int typeCode(Object a) {
      if (a instanceof Integer) {
         return 3;
      } else if (a instanceof Double) {
         return 6;
      } else if (a instanceof Long) {
         return 4;
      } else if (a instanceof Character) {
         return 0;
      } else if (a instanceof Float) {
         return 5;
      } else {
         return !(a instanceof Byte) && !(a instanceof Short) ? 7 : 3;
      }
   }

   public static Boolean boxToBoolean(boolean b) {
      return b;
   }

   public static Character boxToCharacter(char c) {
      return c;
   }

   public static Byte boxToByte(byte b) {
      return b;
   }

   public static Short boxToShort(short s) {
      return s;
   }

   public static Integer boxToInteger(int i) {
      return i;
   }

   public static Long boxToLong(long l) {
      return l;
   }

   public static Float boxToFloat(float f) {
      return f;
   }

   public static Double boxToDouble(double d) {
      return d;
   }

   public static boolean unboxToBoolean(Object b) {
      return b == null ? false : (Boolean)b;
   }

   public static char unboxToChar(Object c) {
      return c == null ? '\u0000' : (Character)c;
   }

   public static byte unboxToByte(Object b) {
      return b == null ? 0 : (Byte)b;
   }

   public static short unboxToShort(Object s) {
      return s == null ? 0 : (Short)s;
   }

   public static int unboxToInt(Object i) {
      return i == null ? 0 : (Integer)i;
   }

   public static long unboxToLong(Object l) {
      return l == null ? 0L : (Long)l;
   }

   public static float unboxToFloat(Object f) {
      return f == null ? 0.0F : (Float)f;
   }

   public static double unboxToDouble(Object d) {
      return d == null ? (double)0.0F : (Double)d;
   }

   public static boolean equals(Object x, Object y) {
      return x == y ? true : equals2(x, y);
   }

   public static boolean equals2(Object x, Object y) {
      if (x instanceof Number) {
         return equalsNumObject((Number)x, y);
      } else if (x instanceof Character) {
         return equalsCharObject((Character)x, y);
      } else if (x == null) {
         return y == null;
      } else {
         return x.equals(y);
      }
   }

   public static boolean equalsNumObject(Number xn, Object y) {
      if (y instanceof Number) {
         return equalsNumNum(xn, (Number)y);
      } else if (y instanceof Character) {
         return equalsNumChar(xn, (Character)y);
      } else if (xn == null) {
         return y == null;
      } else {
         return xn.equals(y);
      }
   }

   public static boolean equalsNumNum(Number xn, Number yn) {
      int xcode = typeCode(xn);
      int ycode = typeCode(yn);
      switch (ycode > xcode ? ycode : xcode) {
         case 3:
            return xn.intValue() == yn.intValue();
         case 4:
            return xn.longValue() == yn.longValue();
         case 5:
            return xn.floatValue() == yn.floatValue();
         case 6:
            return xn.doubleValue() == yn.doubleValue();
         default:
            if (yn instanceof ScalaNumber && !(xn instanceof ScalaNumber)) {
               return yn.equals(xn);
            } else if (xn == null) {
               return yn == null;
            } else {
               return xn.equals(yn);
            }
      }
   }

   public static boolean equalsCharObject(Character xc, Object y) {
      if (y instanceof Character) {
         return xc == (Character)y;
      } else if (y instanceof Number) {
         return equalsNumChar((Number)y, xc);
      } else if (xc == null) {
         return y == null;
      } else {
         return xc.equals(y);
      }
   }

   public static boolean equalsNumChar(Number xn, Character yc) {
      if (yc == null) {
         return xn == null;
      } else {
         char ch = yc;
         switch (typeCode(xn)) {
            case 3:
               return xn.intValue() == ch;
            case 4:
               return xn.longValue() == (long)ch;
            case 5:
               return xn.floatValue() == (float)ch;
            case 6:
               return xn.doubleValue() == (double)ch;
            default:
               return xn.equals(yc);
         }
      }
   }

   private static int unboxCharOrInt(Object arg1, int code) {
      return code == 0 ? (Character)arg1 : ((Number)arg1).intValue();
   }

   private static long unboxCharOrLong(Object arg1, int code) {
      return code == 0 ? (long)(Character)arg1 : ((Number)arg1).longValue();
   }

   private static float unboxCharOrFloat(Object arg1, int code) {
      return code == 0 ? (float)(Character)arg1 : ((Number)arg1).floatValue();
   }

   private static double unboxCharOrDouble(Object arg1, int code) {
      return code == 0 ? (double)(Character)arg1 : ((Number)arg1).doubleValue();
   }

   public static Object add(Object arg1, Object arg2) throws NoSuchMethodException {
      int code1 = typeCode(arg1);
      int code2 = typeCode(arg2);
      int maxcode = code1 < code2 ? code2 : code1;
      if (maxcode <= 3) {
         return boxToInteger(unboxCharOrInt(arg1, code1) + unboxCharOrInt(arg2, code2));
      } else if (maxcode <= 4) {
         return boxToLong(unboxCharOrLong(arg1, code1) + unboxCharOrLong(arg2, code2));
      } else if (maxcode <= 5) {
         return boxToFloat(unboxCharOrFloat(arg1, code1) + unboxCharOrFloat(arg2, code2));
      } else if (maxcode <= 6) {
         return boxToDouble(unboxCharOrDouble(arg1, code1) + unboxCharOrDouble(arg2, code2));
      } else {
         throw new NoSuchMethodException();
      }
   }

   public static Object subtract(Object arg1, Object arg2) throws NoSuchMethodException {
      int code1 = typeCode(arg1);
      int code2 = typeCode(arg2);
      int maxcode = code1 < code2 ? code2 : code1;
      if (maxcode <= 3) {
         return boxToInteger(unboxCharOrInt(arg1, code1) - unboxCharOrInt(arg2, code2));
      } else if (maxcode <= 4) {
         return boxToLong(unboxCharOrLong(arg1, code1) - unboxCharOrLong(arg2, code2));
      } else if (maxcode <= 5) {
         return boxToFloat(unboxCharOrFloat(arg1, code1) - unboxCharOrFloat(arg2, code2));
      } else if (maxcode <= 6) {
         return boxToDouble(unboxCharOrDouble(arg1, code1) - unboxCharOrDouble(arg2, code2));
      } else {
         throw new NoSuchMethodException();
      }
   }

   public static Object multiply(Object arg1, Object arg2) throws NoSuchMethodException {
      int code1 = typeCode(arg1);
      int code2 = typeCode(arg2);
      int maxcode = code1 < code2 ? code2 : code1;
      if (maxcode <= 3) {
         return boxToInteger(unboxCharOrInt(arg1, code1) * unboxCharOrInt(arg2, code2));
      } else if (maxcode <= 4) {
         return boxToLong(unboxCharOrLong(arg1, code1) * unboxCharOrLong(arg2, code2));
      } else if (maxcode <= 5) {
         return boxToFloat(unboxCharOrFloat(arg1, code1) * unboxCharOrFloat(arg2, code2));
      } else if (maxcode <= 6) {
         return boxToDouble(unboxCharOrDouble(arg1, code1) * unboxCharOrDouble(arg2, code2));
      } else {
         throw new NoSuchMethodException();
      }
   }

   public static Object divide(Object arg1, Object arg2) throws NoSuchMethodException {
      int code1 = typeCode(arg1);
      int code2 = typeCode(arg2);
      int maxcode = code1 < code2 ? code2 : code1;
      if (maxcode <= 3) {
         return boxToInteger(unboxCharOrInt(arg1, code1) / unboxCharOrInt(arg2, code2));
      } else if (maxcode <= 4) {
         return boxToLong(unboxCharOrLong(arg1, code1) / unboxCharOrLong(arg2, code2));
      } else if (maxcode <= 5) {
         return boxToFloat(unboxCharOrFloat(arg1, code1) / unboxCharOrFloat(arg2, code2));
      } else if (maxcode <= 6) {
         return boxToDouble(unboxCharOrDouble(arg1, code1) / unboxCharOrDouble(arg2, code2));
      } else {
         throw new NoSuchMethodException();
      }
   }

   public static Object takeModulo(Object arg1, Object arg2) throws NoSuchMethodException {
      int code1 = typeCode(arg1);
      int code2 = typeCode(arg2);
      int maxcode = code1 < code2 ? code2 : code1;
      if (maxcode <= 3) {
         return boxToInteger(unboxCharOrInt(arg1, code1) % unboxCharOrInt(arg2, code2));
      } else if (maxcode <= 4) {
         return boxToLong(unboxCharOrLong(arg1, code1) % unboxCharOrLong(arg2, code2));
      } else if (maxcode <= 5) {
         return boxToFloat(unboxCharOrFloat(arg1, code1) % unboxCharOrFloat(arg2, code2));
      } else if (maxcode <= 6) {
         return boxToDouble(unboxCharOrDouble(arg1, code1) % unboxCharOrDouble(arg2, code2));
      } else {
         throw new NoSuchMethodException();
      }
   }

   public static Object shiftSignedRight(Object arg1, Object arg2) throws NoSuchMethodException {
      int code1 = typeCode(arg1);
      int code2 = typeCode(arg2);
      if (code1 <= 3) {
         int val1 = unboxCharOrInt(arg1, code1);
         if (code2 <= 3) {
            int val2 = unboxCharOrInt(arg2, code2);
            return boxToInteger(val1 >> val2);
         }

         if (code2 <= 4) {
            long val2 = unboxCharOrLong(arg2, code2);
            return boxToInteger(val1 >> (int)val2);
         }
      }

      if (code1 <= 4) {
         long val1 = unboxCharOrLong(arg1, code1);
         if (code2 <= 3) {
            int val2 = unboxCharOrInt(arg2, code2);
            return boxToLong(val1 >> val2);
         }

         if (code2 <= 4) {
            long val2 = unboxCharOrLong(arg2, code2);
            return boxToLong(val1 >> (int)val2);
         }
      }

      throw new NoSuchMethodException();
   }

   public static Object shiftSignedLeft(Object arg1, Object arg2) throws NoSuchMethodException {
      int code1 = typeCode(arg1);
      int code2 = typeCode(arg2);
      if (code1 <= 3) {
         int val1 = unboxCharOrInt(arg1, code1);
         if (code2 <= 3) {
            int val2 = unboxCharOrInt(arg2, code2);
            return boxToInteger(val1 << val2);
         }

         if (code2 <= 4) {
            long val2 = unboxCharOrLong(arg2, code2);
            return boxToInteger(val1 << (int)val2);
         }
      }

      if (code1 <= 4) {
         long val1 = unboxCharOrLong(arg1, code1);
         if (code2 <= 3) {
            int val2 = unboxCharOrInt(arg2, code2);
            return boxToLong(val1 << val2);
         }

         if (code2 <= 4) {
            long val2 = unboxCharOrLong(arg2, code2);
            return boxToLong(val1 << (int)val2);
         }
      }

      throw new NoSuchMethodException();
   }

   public static Object shiftLogicalRight(Object arg1, Object arg2) throws NoSuchMethodException {
      int code1 = typeCode(arg1);
      int code2 = typeCode(arg2);
      if (code1 <= 3) {
         int val1 = unboxCharOrInt(arg1, code1);
         if (code2 <= 3) {
            int val2 = unboxCharOrInt(arg2, code2);
            return boxToInteger(val1 >>> val2);
         }

         if (code2 <= 4) {
            long val2 = unboxCharOrLong(arg2, code2);
            return boxToInteger(val1 >>> (int)val2);
         }
      }

      if (code1 <= 4) {
         long val1 = unboxCharOrLong(arg1, code1);
         if (code2 <= 3) {
            int val2 = unboxCharOrInt(arg2, code2);
            return boxToLong(val1 >>> val2);
         }

         if (code2 <= 4) {
            long val2 = unboxCharOrLong(arg2, code2);
            return boxToLong(val1 >>> (int)val2);
         }
      }

      throw new NoSuchMethodException();
   }

   public static Object negate(Object arg) throws NoSuchMethodException {
      int code = typeCode(arg);
      if (code <= 3) {
         int val = unboxCharOrInt(arg, code);
         return boxToInteger(-val);
      } else if (code <= 4) {
         long val = unboxCharOrLong(arg, code);
         return boxToLong(-val);
      } else if (code <= 5) {
         float val = unboxCharOrFloat(arg, code);
         return boxToFloat(-val);
      } else if (code <= 6) {
         double val = unboxCharOrDouble(arg, code);
         return boxToDouble(-val);
      } else {
         throw new NoSuchMethodException();
      }
   }

   public static Object positive(Object arg) throws NoSuchMethodException {
      int code = typeCode(arg);
      if (code <= 3) {
         return boxToInteger(unboxCharOrInt(arg, code));
      } else if (code <= 4) {
         return boxToLong(unboxCharOrLong(arg, code));
      } else if (code <= 5) {
         return boxToFloat(unboxCharOrFloat(arg, code));
      } else if (code <= 6) {
         return boxToDouble(unboxCharOrDouble(arg, code));
      } else {
         throw new NoSuchMethodException();
      }
   }

   public static Object takeAnd(Object arg1, Object arg2) throws NoSuchMethodException {
      if (!(arg1 instanceof Boolean) && !(arg2 instanceof Boolean)) {
         int code1 = typeCode(arg1);
         int code2 = typeCode(arg2);
         int maxcode = code1 < code2 ? code2 : code1;
         if (maxcode <= 3) {
            return boxToInteger(unboxCharOrInt(arg1, code1) & unboxCharOrInt(arg2, code2));
         } else if (maxcode <= 4) {
            return boxToLong(unboxCharOrLong(arg1, code1) & unboxCharOrLong(arg2, code2));
         } else {
            throw new NoSuchMethodException();
         }
      } else if (arg1 instanceof Boolean && arg2 instanceof Boolean) {
         return boxToBoolean((Boolean)arg1 & (Boolean)arg2);
      } else {
         throw new NoSuchMethodException();
      }
   }

   public static Object takeOr(Object arg1, Object arg2) throws NoSuchMethodException {
      if (!(arg1 instanceof Boolean) && !(arg2 instanceof Boolean)) {
         int code1 = typeCode(arg1);
         int code2 = typeCode(arg2);
         int maxcode = code1 < code2 ? code2 : code1;
         if (maxcode <= 3) {
            return boxToInteger(unboxCharOrInt(arg1, code1) | unboxCharOrInt(arg2, code2));
         } else if (maxcode <= 4) {
            return boxToLong(unboxCharOrLong(arg1, code1) | unboxCharOrLong(arg2, code2));
         } else {
            throw new NoSuchMethodException();
         }
      } else if (arg1 instanceof Boolean && arg2 instanceof Boolean) {
         return boxToBoolean((Boolean)arg1 | (Boolean)arg2);
      } else {
         throw new NoSuchMethodException();
      }
   }

   public static Object takeXor(Object arg1, Object arg2) throws NoSuchMethodException {
      if (!(arg1 instanceof Boolean) && !(arg2 instanceof Boolean)) {
         int code1 = typeCode(arg1);
         int code2 = typeCode(arg2);
         int maxcode = code1 < code2 ? code2 : code1;
         if (maxcode <= 3) {
            return boxToInteger(unboxCharOrInt(arg1, code1) ^ unboxCharOrInt(arg2, code2));
         } else if (maxcode <= 4) {
            return boxToLong(unboxCharOrLong(arg1, code1) ^ unboxCharOrLong(arg2, code2));
         } else {
            throw new NoSuchMethodException();
         }
      } else if (arg1 instanceof Boolean && arg2 instanceof Boolean) {
         return boxToBoolean((Boolean)arg1 ^ (Boolean)arg2);
      } else {
         throw new NoSuchMethodException();
      }
   }

   public static Object takeConditionalAnd(Object arg1, Object arg2) throws NoSuchMethodException {
      if (arg1 instanceof Boolean && arg2 instanceof Boolean) {
         return boxToBoolean((Boolean)arg1 && (Boolean)arg2);
      } else {
         throw new NoSuchMethodException();
      }
   }

   public static Object takeConditionalOr(Object arg1, Object arg2) throws NoSuchMethodException {
      if (arg1 instanceof Boolean && arg2 instanceof Boolean) {
         return boxToBoolean((Boolean)arg1 || (Boolean)arg2);
      } else {
         throw new NoSuchMethodException();
      }
   }

   public static Object complement(Object arg) throws NoSuchMethodException {
      int code = typeCode(arg);
      if (code <= 3) {
         return boxToInteger(~unboxCharOrInt(arg, code));
      } else if (code <= 4) {
         return boxToLong(~unboxCharOrLong(arg, code));
      } else {
         throw new NoSuchMethodException();
      }
   }

   public static Object takeNot(Object arg) throws NoSuchMethodException {
      if (arg instanceof Boolean) {
         return boxToBoolean(!(Boolean)arg);
      } else {
         throw new NoSuchMethodException();
      }
   }

   public static Object testEqual(Object arg1, Object arg2) throws NoSuchMethodException {
      return boxToBoolean(arg1 == arg2);
   }

   public static Object testNotEqual(Object arg1, Object arg2) throws NoSuchMethodException {
      return boxToBoolean(arg1 != arg2);
   }

   public static Object testLessThan(Object arg1, Object arg2) throws NoSuchMethodException {
      int code1 = typeCode(arg1);
      int code2 = typeCode(arg2);
      int maxcode = code1 < code2 ? code2 : code1;
      if (maxcode <= 3) {
         int val1 = unboxCharOrInt(arg1, code1);
         int val2 = unboxCharOrInt(arg2, code2);
         return boxToBoolean(val1 < val2);
      } else if (maxcode <= 4) {
         long val1 = unboxCharOrLong(arg1, code1);
         long val2 = unboxCharOrLong(arg2, code2);
         return boxToBoolean(val1 < val2);
      } else if (maxcode <= 5) {
         float val1 = unboxCharOrFloat(arg1, code1);
         float val2 = unboxCharOrFloat(arg2, code2);
         return boxToBoolean(val1 < val2);
      } else if (maxcode <= 6) {
         double val1 = unboxCharOrDouble(arg1, code1);
         double val2 = unboxCharOrDouble(arg2, code2);
         return boxToBoolean(val1 < val2);
      } else {
         throw new NoSuchMethodException();
      }
   }

   public static Object testLessOrEqualThan(Object arg1, Object arg2) throws NoSuchMethodException {
      int code1 = typeCode(arg1);
      int code2 = typeCode(arg2);
      int maxcode = code1 < code2 ? code2 : code1;
      if (maxcode <= 3) {
         int val1 = unboxCharOrInt(arg1, code1);
         int val2 = unboxCharOrInt(arg2, code2);
         return boxToBoolean(val1 <= val2);
      } else if (maxcode <= 4) {
         long val1 = unboxCharOrLong(arg1, code1);
         long val2 = unboxCharOrLong(arg2, code2);
         return boxToBoolean(val1 <= val2);
      } else if (maxcode <= 5) {
         float val1 = unboxCharOrFloat(arg1, code1);
         float val2 = unboxCharOrFloat(arg2, code2);
         return boxToBoolean(val1 <= val2);
      } else if (maxcode <= 6) {
         double val1 = unboxCharOrDouble(arg1, code1);
         double val2 = unboxCharOrDouble(arg2, code2);
         return boxToBoolean(val1 <= val2);
      } else {
         throw new NoSuchMethodException();
      }
   }

   public static Object testGreaterOrEqualThan(Object arg1, Object arg2) throws NoSuchMethodException {
      int code1 = typeCode(arg1);
      int code2 = typeCode(arg2);
      int maxcode = code1 < code2 ? code2 : code1;
      if (maxcode <= 3) {
         int val1 = unboxCharOrInt(arg1, code1);
         int val2 = unboxCharOrInt(arg2, code2);
         return boxToBoolean(val1 >= val2);
      } else if (maxcode <= 4) {
         long val1 = unboxCharOrLong(arg1, code1);
         long val2 = unboxCharOrLong(arg2, code2);
         return boxToBoolean(val1 >= val2);
      } else if (maxcode <= 5) {
         float val1 = unboxCharOrFloat(arg1, code1);
         float val2 = unboxCharOrFloat(arg2, code2);
         return boxToBoolean(val1 >= val2);
      } else if (maxcode <= 6) {
         double val1 = unboxCharOrDouble(arg1, code1);
         double val2 = unboxCharOrDouble(arg2, code2);
         return boxToBoolean(val1 >= val2);
      } else {
         throw new NoSuchMethodException();
      }
   }

   public static Object testGreaterThan(Object arg1, Object arg2) throws NoSuchMethodException {
      int code1 = typeCode(arg1);
      int code2 = typeCode(arg2);
      int maxcode = code1 < code2 ? code2 : code1;
      if (maxcode <= 3) {
         int val1 = unboxCharOrInt(arg1, code1);
         int val2 = unboxCharOrInt(arg2, code2);
         return boxToBoolean(val1 > val2);
      } else if (maxcode <= 4) {
         long val1 = unboxCharOrLong(arg1, code1);
         long val2 = unboxCharOrLong(arg2, code2);
         return boxToBoolean(val1 > val2);
      } else if (maxcode <= 5) {
         float val1 = unboxCharOrFloat(arg1, code1);
         float val2 = unboxCharOrFloat(arg2, code2);
         return boxToBoolean(val1 > val2);
      } else if (maxcode <= 6) {
         double val1 = unboxCharOrDouble(arg1, code1);
         double val2 = unboxCharOrDouble(arg2, code2);
         return boxToBoolean(val1 > val2);
      } else {
         throw new NoSuchMethodException();
      }
   }

   public static boolean isBoxedNumberOrBoolean(Object arg) {
      return arg instanceof Boolean || isBoxedNumber(arg);
   }

   public static boolean isBoxedNumber(Object arg) {
      return arg instanceof Integer || arg instanceof Long || arg instanceof Double || arg instanceof Float || arg instanceof Short || arg instanceof Character || arg instanceof Byte;
   }

   public static Character toCharacter(Object arg) throws NoSuchMethodException {
      if (arg instanceof Integer) {
         return boxToCharacter((char)unboxToInt(arg));
      } else if (arg instanceof Short) {
         return boxToCharacter((char)unboxToShort(arg));
      } else if (arg instanceof Character) {
         return (Character)arg;
      } else if (arg instanceof Long) {
         return boxToCharacter((char)((int)unboxToLong(arg)));
      } else if (arg instanceof Byte) {
         return boxToCharacter((char)unboxToByte(arg));
      } else if (arg instanceof Float) {
         return boxToCharacter((char)((int)unboxToFloat(arg)));
      } else if (arg instanceof Double) {
         return boxToCharacter((char)((int)unboxToDouble(arg)));
      } else {
         throw new NoSuchMethodException();
      }
   }

   public static Byte toByte(Object arg) throws NoSuchMethodException {
      if (arg instanceof Integer) {
         return boxToByte((byte)unboxToInt(arg));
      } else if (arg instanceof Character) {
         return boxToByte((byte)unboxToChar(arg));
      } else if (arg instanceof Byte) {
         return (Byte)arg;
      } else if (arg instanceof Long) {
         return boxToByte((byte)((int)unboxToLong(arg)));
      } else if (arg instanceof Short) {
         return boxToByte((byte)unboxToShort(arg));
      } else if (arg instanceof Float) {
         return boxToByte((byte)((int)unboxToFloat(arg)));
      } else if (arg instanceof Double) {
         return boxToByte((byte)((int)unboxToDouble(arg)));
      } else {
         throw new NoSuchMethodException();
      }
   }

   public static Short toShort(Object arg) throws NoSuchMethodException {
      if (arg instanceof Integer) {
         return boxToShort((short)unboxToInt(arg));
      } else if (arg instanceof Long) {
         return boxToShort((short)((int)unboxToLong(arg)));
      } else if (arg instanceof Character) {
         return boxToShort((short)unboxToChar(arg));
      } else if (arg instanceof Byte) {
         return boxToShort((short)unboxToByte(arg));
      } else if (arg instanceof Short) {
         return (Short)arg;
      } else if (arg instanceof Float) {
         return boxToShort((short)((int)unboxToFloat(arg)));
      } else if (arg instanceof Double) {
         return boxToShort((short)((int)unboxToDouble(arg)));
      } else {
         throw new NoSuchMethodException();
      }
   }

   public static Integer toInteger(Object arg) throws NoSuchMethodException {
      if (arg instanceof Integer) {
         return (Integer)arg;
      } else if (arg instanceof Long) {
         return boxToInteger((int)unboxToLong(arg));
      } else if (arg instanceof Double) {
         return boxToInteger((int)unboxToDouble(arg));
      } else if (arg instanceof Float) {
         return boxToInteger((int)unboxToFloat(arg));
      } else if (arg instanceof Character) {
         return boxToInteger(unboxToChar(arg));
      } else if (arg instanceof Byte) {
         return boxToInteger(unboxToByte(arg));
      } else if (arg instanceof Short) {
         return boxToInteger(unboxToShort(arg));
      } else {
         throw new NoSuchMethodException();
      }
   }

   public static Long toLong(Object arg) throws NoSuchMethodException {
      if (arg instanceof Integer) {
         return boxToLong((long)unboxToInt(arg));
      } else if (arg instanceof Double) {
         return boxToLong((long)unboxToDouble(arg));
      } else if (arg instanceof Float) {
         return boxToLong((long)unboxToFloat(arg));
      } else if (arg instanceof Long) {
         return (Long)arg;
      } else if (arg instanceof Character) {
         return boxToLong((long)unboxToChar(arg));
      } else if (arg instanceof Byte) {
         return boxToLong((long)unboxToByte(arg));
      } else if (arg instanceof Short) {
         return boxToLong((long)unboxToShort(arg));
      } else {
         throw new NoSuchMethodException();
      }
   }

   public static Float toFloat(Object arg) throws NoSuchMethodException {
      if (arg instanceof Integer) {
         return boxToFloat((float)unboxToInt(arg));
      } else if (arg instanceof Long) {
         return boxToFloat((float)unboxToLong(arg));
      } else if (arg instanceof Float) {
         return (Float)arg;
      } else if (arg instanceof Double) {
         return boxToFloat((float)unboxToDouble(arg));
      } else if (arg instanceof Character) {
         return boxToFloat((float)unboxToChar(arg));
      } else if (arg instanceof Byte) {
         return boxToFloat((float)unboxToByte(arg));
      } else if (arg instanceof Short) {
         return boxToFloat((float)unboxToShort(arg));
      } else {
         throw new NoSuchMethodException();
      }
   }

   public static Double toDouble(Object arg) throws NoSuchMethodException {
      if (arg instanceof Integer) {
         return boxToDouble((double)unboxToInt(arg));
      } else if (arg instanceof Float) {
         return boxToDouble((double)unboxToFloat(arg));
      } else if (arg instanceof Double) {
         return (Double)arg;
      } else if (arg instanceof Long) {
         return boxToDouble((double)unboxToLong(arg));
      } else if (arg instanceof Character) {
         return boxToDouble((double)unboxToChar(arg));
      } else if (arg instanceof Byte) {
         return boxToDouble((double)unboxToByte(arg));
      } else if (arg instanceof Short) {
         return boxToDouble((double)unboxToShort(arg));
      } else {
         throw new NoSuchMethodException();
      }
   }
}
