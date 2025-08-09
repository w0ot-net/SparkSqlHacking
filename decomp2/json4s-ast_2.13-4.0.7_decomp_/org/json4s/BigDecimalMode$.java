package org.json4s;

import scala.math.BigDecimal;
import scala.math.BigInt;

public final class BigDecimalMode$ implements Implicits, BigDecimalMode {
   public static final BigDecimalMode$ MODULE$ = new BigDecimalMode$();

   static {
      Implicits.$init$(MODULE$);
      BigDecimalMode.$init$(MODULE$);
   }

   public JValue double2jvalue(final double x) {
      return BigDecimalMode.double2jvalue$(this, x);
   }

   public JValue float2jvalue(final float x) {
      return BigDecimalMode.float2jvalue$(this, x);
   }

   public JValue bigdecimal2jvalue(final BigDecimal x) {
      return BigDecimalMode.bigdecimal2jvalue$(this, x);
   }

   public JValue short2jvalue(final short x) {
      return Implicits.short2jvalue$(this, x);
   }

   public JValue byte2jvalue(final byte x) {
      return Implicits.byte2jvalue$(this, x);
   }

   public JValue char2jvalue(final char x) {
      return Implicits.char2jvalue$(this, x);
   }

   public JValue int2jvalue(final int x) {
      return Implicits.int2jvalue$(this, x);
   }

   public JValue long2jvalue(final long x) {
      return Implicits.long2jvalue$(this, x);
   }

   public JValue bigint2jvalue(final BigInt x) {
      return Implicits.bigint2jvalue$(this, x);
   }

   public JValue boolean2jvalue(final boolean x) {
      return Implicits.boolean2jvalue$(this, x);
   }

   public JValue string2jvalue(final String x) {
      return Implicits.string2jvalue$(this, x);
   }

   private BigDecimalMode$() {
   }
}
