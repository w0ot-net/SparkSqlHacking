package scala.math;

import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005u3qAF\f\u0011\u0002\u0007\u0005A\u0004C\u0003\"\u0001\u0011\u0005!\u0005C\u0003'\u0001\u0019\u0005q\u0005C\u0003,\u0001\u0019\u0005A\u0006C\u00031\u0001\u0019\u0005\u0011\u0007C\u00036\u0001\u0019\u0005a\u0007C\u0003;\u0001\u0019\u00051\bC\u0003@\u0001\u0019\u0005\u0001\tC\u0003E\u0001\u0019\u0005Q\tC\u0003J\u0001\u0011\u0005!\nC\u0003O\u0001\u0011\u0005A\u0006C\u0003P\u0001\u0011\u0005\u0011\u0007C\u0003Q\u0001\u0011\u0005a\u0007C\u0003R\u0001\u0011\u00051\bC\u0003S\u0001\u0011\u0005\u0001\tC\u0003T\u0001\u0011\u0005Q\tC\u0003U\u0001\u0011\u0005q\u0005C\u0003V\u0001\u0011\u0005q\u0005C\u0003W\u0001\u0011\u0005q\u0005C\u0003X\u0001\u0011\u0005q\u0005C\u0003Y\u0001\u0011Ea\u0007C\u0003Z\u0001\u0011E!L\u0001\u000eTG\u0006d\u0017MT;nKJL7-\u00118z\u0007>tg/\u001a:tS>t7O\u0003\u0002\u00193\u0005!Q.\u0019;i\u0015\u0005Q\u0012!B:dC2\f7\u0001A\n\u0003\u0001u\u0001\"AH\u0010\u000e\u0003eI!\u0001I\r\u0003\u0007\u0005s\u00170\u0001\u0004%S:LG\u000f\n\u000b\u0002GA\u0011a\u0004J\u0005\u0003Ke\u0011A!\u00168ji\u00069\u0011n],i_2,W#\u0001\u0015\u0011\u0005yI\u0013B\u0001\u0016\u001a\u0005\u001d\u0011un\u001c7fC:\f\u0011BY=uKZ\u000bG.^3\u0016\u00035\u0002\"A\b\u0018\n\u0005=J\"\u0001\u0002\"zi\u0016\f!b\u001d5peR4\u0016\r\\;f+\u0005\u0011\u0004C\u0001\u00104\u0013\t!\u0014DA\u0003TQ>\u0014H/\u0001\u0005j]R4\u0016\r\\;f+\u00059\u0004C\u0001\u00109\u0013\tI\u0014DA\u0002J]R\f\u0011\u0002\\8oOZ\u000bG.^3\u0016\u0003q\u0002\"AH\u001f\n\u0005yJ\"\u0001\u0002'p]\u001e\f!B\u001a7pCR4\u0016\r\\;f+\u0005\t\u0005C\u0001\u0010C\u0013\t\u0019\u0015DA\u0003GY>\fG/A\u0006e_V\u0014G.\u001a,bYV,W#\u0001$\u0011\u0005y9\u0015B\u0001%\u001a\u0005\u0019!u.\u001e2mK\u00061Ao\\\"iCJ,\u0012a\u0013\t\u0003=1K!!T\r\u0003\t\rC\u0017M]\u0001\u0007i>\u0014\u0015\u0010^3\u0002\u000fQ|7\u000b[8si\u0006)Ao\\%oi\u00061Ao\u001c'p]\u001e\fq\u0001^8GY>\fG/\u0001\u0005u_\u0012{WO\u00197f\u0003-I7OV1mS\u0012\u0014\u0015\u0010^3\u0002\u0019%\u001ch+\u00197jINCwN\u001d;\u0002\u0015%\u001ch+\u00197jI&sG/A\u0006jgZ\u000bG.\u001b3DQ\u0006\u0014\u0018\u0001G;oS\u001aLW\r\u001a)sS6LG/\u001b<f\u0011\u0006\u001c\bnY8eK\u00061RO\\5gS\u0016$\u0007K]5nSRLg/Z#rk\u0006d7\u000f\u0006\u0002)7\")A,\u0006a\u0001;\u0005\t\u0001\u0010"
)
public interface ScalaNumericAnyConversions {
   boolean isWhole();

   byte byteValue();

   short shortValue();

   int intValue();

   long longValue();

   float floatValue();

   double doubleValue();

   // $FF: synthetic method
   static char toChar$(final ScalaNumericAnyConversions $this) {
      return $this.toChar();
   }

   default char toChar() {
      return (char)this.intValue();
   }

   // $FF: synthetic method
   static byte toByte$(final ScalaNumericAnyConversions $this) {
      return $this.toByte();
   }

   default byte toByte() {
      return this.byteValue();
   }

   // $FF: synthetic method
   static short toShort$(final ScalaNumericAnyConversions $this) {
      return $this.toShort();
   }

   default short toShort() {
      return this.shortValue();
   }

   // $FF: synthetic method
   static int toInt$(final ScalaNumericAnyConversions $this) {
      return $this.toInt();
   }

   default int toInt() {
      return this.intValue();
   }

   // $FF: synthetic method
   static long toLong$(final ScalaNumericAnyConversions $this) {
      return $this.toLong();
   }

   default long toLong() {
      return this.longValue();
   }

   // $FF: synthetic method
   static float toFloat$(final ScalaNumericAnyConversions $this) {
      return $this.toFloat();
   }

   default float toFloat() {
      return this.floatValue();
   }

   // $FF: synthetic method
   static double toDouble$(final ScalaNumericAnyConversions $this) {
      return $this.toDouble();
   }

   default double toDouble() {
      return this.doubleValue();
   }

   // $FF: synthetic method
   static boolean isValidByte$(final ScalaNumericAnyConversions $this) {
      return $this.isValidByte();
   }

   default boolean isValidByte() {
      return this.isWhole() && this.toInt() == this.toByte();
   }

   // $FF: synthetic method
   static boolean isValidShort$(final ScalaNumericAnyConversions $this) {
      return $this.isValidShort();
   }

   default boolean isValidShort() {
      return this.isWhole() && this.toInt() == this.toShort();
   }

   // $FF: synthetic method
   static boolean isValidInt$(final ScalaNumericAnyConversions $this) {
      return $this.isValidInt();
   }

   default boolean isValidInt() {
      return this.isWhole() && this.toLong() == (long)this.toInt();
   }

   // $FF: synthetic method
   static boolean isValidChar$(final ScalaNumericAnyConversions $this) {
      return $this.isValidChar();
   }

   default boolean isValidChar() {
      return this.isWhole() && this.toInt() >= 0 && this.toInt() <= 65535;
   }

   // $FF: synthetic method
   static int unifiedPrimitiveHashcode$(final ScalaNumericAnyConversions $this) {
      return $this.unifiedPrimitiveHashcode();
   }

   default int unifiedPrimitiveHashcode() {
      long lv = this.toLong();
      return lv >= -2147483648L && lv <= 2147483647L ? (int)lv : Statics.longHash(lv);
   }

   // $FF: synthetic method
   static boolean unifiedPrimitiveEquals$(final ScalaNumericAnyConversions $this, final Object x) {
      return $this.unifiedPrimitiveEquals(x);
   }

   default boolean unifiedPrimitiveEquals(final Object x) {
      if (x instanceof Character) {
         char var2 = BoxesRunTime.unboxToChar(x);
         return this.isValidChar() && this.toInt() == var2;
      } else if (x instanceof Byte) {
         byte var3 = BoxesRunTime.unboxToByte(x);
         return this.isValidByte() && this.toByte() == var3;
      } else if (x instanceof Short) {
         short var4 = BoxesRunTime.unboxToShort(x);
         return this.isValidShort() && this.toShort() == var4;
      } else if (x instanceof Integer) {
         int var5 = BoxesRunTime.unboxToInt(x);
         return this.isValidInt() && this.toInt() == var5;
      } else if (x instanceof Long) {
         long var6 = BoxesRunTime.unboxToLong(x);
         return this.toLong() == var6;
      } else if (x instanceof Float) {
         float var8 = BoxesRunTime.unboxToFloat(x);
         return this.toFloat() == var8;
      } else if (x instanceof Double) {
         double var9 = BoxesRunTime.unboxToDouble(x);
         return this.toDouble() == var9;
      } else {
         return false;
      }
   }

   static void $init$(final ScalaNumericAnyConversions $this) {
   }
}
