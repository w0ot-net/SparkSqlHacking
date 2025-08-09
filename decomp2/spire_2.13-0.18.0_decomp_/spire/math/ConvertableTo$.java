package spire.math;

import scala.math.BigDecimal;
import scala.math.BigInt;

public final class ConvertableTo$ {
   public static final ConvertableTo$ MODULE$ = new ConvertableTo$();
   private static final ConvertableTo ConvertableToByte = new ConvertableToByte() {
      public byte fromByte(final byte a) {
         return spire.math.ConvertableToByte.fromByte$(this, a);
      }

      public byte fromShort(final short a) {
         return spire.math.ConvertableToByte.fromShort$(this, a);
      }

      public byte fromInt(final int a) {
         return spire.math.ConvertableToByte.fromInt$(this, a);
      }

      public byte fromLong(final long a) {
         return spire.math.ConvertableToByte.fromLong$(this, a);
      }

      public byte fromFloat(final float a) {
         return spire.math.ConvertableToByte.fromFloat$(this, a);
      }

      public byte fromDouble(final double a) {
         return spire.math.ConvertableToByte.fromDouble$(this, a);
      }

      public byte fromBigInt(final BigInt a) {
         return spire.math.ConvertableToByte.fromBigInt$(this, a);
      }

      public byte fromBigDecimal(final BigDecimal a) {
         return spire.math.ConvertableToByte.fromBigDecimal$(this, a);
      }

      public byte fromRational(final Rational a) {
         return spire.math.ConvertableToByte.fromRational$(this, a);
      }

      public byte fromAlgebraic(final Algebraic a) {
         return spire.math.ConvertableToByte.fromAlgebraic$(this, a);
      }

      public byte fromReal(final Real a) {
         return spire.math.ConvertableToByte.fromReal$(this, a);
      }

      public byte fromType(final Object b, final ConvertableFrom evidence$2) {
         return spire.math.ConvertableToByte.fromType$(this, b, evidence$2);
      }

      public byte fromByte$mcB$sp(final byte a) {
         return spire.math.ConvertableToByte.fromByte$mcB$sp$(this, a);
      }

      public byte fromShort$mcB$sp(final short a) {
         return spire.math.ConvertableToByte.fromShort$mcB$sp$(this, a);
      }

      public byte fromInt$mcB$sp(final int a) {
         return spire.math.ConvertableToByte.fromInt$mcB$sp$(this, a);
      }

      public byte fromLong$mcB$sp(final long a) {
         return spire.math.ConvertableToByte.fromLong$mcB$sp$(this, a);
      }

      public byte fromFloat$mcB$sp(final float a) {
         return spire.math.ConvertableToByte.fromFloat$mcB$sp$(this, a);
      }

      public byte fromDouble$mcB$sp(final double a) {
         return spire.math.ConvertableToByte.fromDouble$mcB$sp$(this, a);
      }

      public byte fromBigInt$mcB$sp(final BigInt a) {
         return spire.math.ConvertableToByte.fromBigInt$mcB$sp$(this, a);
      }

      public byte fromBigDecimal$mcB$sp(final BigDecimal a) {
         return spire.math.ConvertableToByte.fromBigDecimal$mcB$sp$(this, a);
      }

      public byte fromRational$mcB$sp(final Rational a) {
         return spire.math.ConvertableToByte.fromRational$mcB$sp$(this, a);
      }

      public byte fromAlgebraic$mcB$sp(final Algebraic a) {
         return spire.math.ConvertableToByte.fromAlgebraic$mcB$sp$(this, a);
      }

      public byte fromReal$mcB$sp(final Real a) {
         return spire.math.ConvertableToByte.fromReal$mcB$sp$(this, a);
      }

      public byte fromType$mcB$sp(final Object b, final ConvertableFrom evidence$2) {
         return spire.math.ConvertableToByte.fromType$mcB$sp$(this, b, evidence$2);
      }

      public boolean fromByte$mcZ$sp(final byte n) {
         return ConvertableTo.fromByte$mcZ$sp$(this, n);
      }

      public char fromByte$mcC$sp(final byte n) {
         return ConvertableTo.fromByte$mcC$sp$(this, n);
      }

      public double fromByte$mcD$sp(final byte n) {
         return ConvertableTo.fromByte$mcD$sp$(this, n);
      }

      public float fromByte$mcF$sp(final byte n) {
         return ConvertableTo.fromByte$mcF$sp$(this, n);
      }

      public int fromByte$mcI$sp(final byte n) {
         return ConvertableTo.fromByte$mcI$sp$(this, n);
      }

      public long fromByte$mcJ$sp(final byte n) {
         return ConvertableTo.fromByte$mcJ$sp$(this, n);
      }

      public short fromByte$mcS$sp(final byte n) {
         return ConvertableTo.fromByte$mcS$sp$(this, n);
      }

      public void fromByte$mcV$sp(final byte n) {
         ConvertableTo.fromByte$mcV$sp$(this, n);
      }

      public boolean fromShort$mcZ$sp(final short n) {
         return ConvertableTo.fromShort$mcZ$sp$(this, n);
      }

      public char fromShort$mcC$sp(final short n) {
         return ConvertableTo.fromShort$mcC$sp$(this, n);
      }

      public double fromShort$mcD$sp(final short n) {
         return ConvertableTo.fromShort$mcD$sp$(this, n);
      }

      public float fromShort$mcF$sp(final short n) {
         return ConvertableTo.fromShort$mcF$sp$(this, n);
      }

      public int fromShort$mcI$sp(final short n) {
         return ConvertableTo.fromShort$mcI$sp$(this, n);
      }

      public long fromShort$mcJ$sp(final short n) {
         return ConvertableTo.fromShort$mcJ$sp$(this, n);
      }

      public short fromShort$mcS$sp(final short n) {
         return ConvertableTo.fromShort$mcS$sp$(this, n);
      }

      public void fromShort$mcV$sp(final short n) {
         ConvertableTo.fromShort$mcV$sp$(this, n);
      }

      public boolean fromInt$mcZ$sp(final int n) {
         return ConvertableTo.fromInt$mcZ$sp$(this, n);
      }

      public char fromInt$mcC$sp(final int n) {
         return ConvertableTo.fromInt$mcC$sp$(this, n);
      }

      public double fromInt$mcD$sp(final int n) {
         return ConvertableTo.fromInt$mcD$sp$(this, n);
      }

      public float fromInt$mcF$sp(final int n) {
         return ConvertableTo.fromInt$mcF$sp$(this, n);
      }

      public int fromInt$mcI$sp(final int n) {
         return ConvertableTo.fromInt$mcI$sp$(this, n);
      }

      public long fromInt$mcJ$sp(final int n) {
         return ConvertableTo.fromInt$mcJ$sp$(this, n);
      }

      public short fromInt$mcS$sp(final int n) {
         return ConvertableTo.fromInt$mcS$sp$(this, n);
      }

      public void fromInt$mcV$sp(final int n) {
         ConvertableTo.fromInt$mcV$sp$(this, n);
      }

      public boolean fromLong$mcZ$sp(final long n) {
         return ConvertableTo.fromLong$mcZ$sp$(this, n);
      }

      public char fromLong$mcC$sp(final long n) {
         return ConvertableTo.fromLong$mcC$sp$(this, n);
      }

      public double fromLong$mcD$sp(final long n) {
         return ConvertableTo.fromLong$mcD$sp$(this, n);
      }

      public float fromLong$mcF$sp(final long n) {
         return ConvertableTo.fromLong$mcF$sp$(this, n);
      }

      public int fromLong$mcI$sp(final long n) {
         return ConvertableTo.fromLong$mcI$sp$(this, n);
      }

      public long fromLong$mcJ$sp(final long n) {
         return ConvertableTo.fromLong$mcJ$sp$(this, n);
      }

      public short fromLong$mcS$sp(final long n) {
         return ConvertableTo.fromLong$mcS$sp$(this, n);
      }

      public void fromLong$mcV$sp(final long n) {
         ConvertableTo.fromLong$mcV$sp$(this, n);
      }

      public boolean fromFloat$mcZ$sp(final float n) {
         return ConvertableTo.fromFloat$mcZ$sp$(this, n);
      }

      public char fromFloat$mcC$sp(final float n) {
         return ConvertableTo.fromFloat$mcC$sp$(this, n);
      }

      public double fromFloat$mcD$sp(final float n) {
         return ConvertableTo.fromFloat$mcD$sp$(this, n);
      }

      public float fromFloat$mcF$sp(final float n) {
         return ConvertableTo.fromFloat$mcF$sp$(this, n);
      }

      public int fromFloat$mcI$sp(final float n) {
         return ConvertableTo.fromFloat$mcI$sp$(this, n);
      }

      public long fromFloat$mcJ$sp(final float n) {
         return ConvertableTo.fromFloat$mcJ$sp$(this, n);
      }

      public short fromFloat$mcS$sp(final float n) {
         return ConvertableTo.fromFloat$mcS$sp$(this, n);
      }

      public void fromFloat$mcV$sp(final float n) {
         ConvertableTo.fromFloat$mcV$sp$(this, n);
      }

      public boolean fromDouble$mcZ$sp(final double n) {
         return ConvertableTo.fromDouble$mcZ$sp$(this, n);
      }

      public char fromDouble$mcC$sp(final double n) {
         return ConvertableTo.fromDouble$mcC$sp$(this, n);
      }

      public double fromDouble$mcD$sp(final double n) {
         return ConvertableTo.fromDouble$mcD$sp$(this, n);
      }

      public float fromDouble$mcF$sp(final double n) {
         return ConvertableTo.fromDouble$mcF$sp$(this, n);
      }

      public int fromDouble$mcI$sp(final double n) {
         return ConvertableTo.fromDouble$mcI$sp$(this, n);
      }

      public long fromDouble$mcJ$sp(final double n) {
         return ConvertableTo.fromDouble$mcJ$sp$(this, n);
      }

      public short fromDouble$mcS$sp(final double n) {
         return ConvertableTo.fromDouble$mcS$sp$(this, n);
      }

      public void fromDouble$mcV$sp(final double n) {
         ConvertableTo.fromDouble$mcV$sp$(this, n);
      }

      public boolean fromBigInt$mcZ$sp(final BigInt n) {
         return ConvertableTo.fromBigInt$mcZ$sp$(this, n);
      }

      public char fromBigInt$mcC$sp(final BigInt n) {
         return ConvertableTo.fromBigInt$mcC$sp$(this, n);
      }

      public double fromBigInt$mcD$sp(final BigInt n) {
         return ConvertableTo.fromBigInt$mcD$sp$(this, n);
      }

      public float fromBigInt$mcF$sp(final BigInt n) {
         return ConvertableTo.fromBigInt$mcF$sp$(this, n);
      }

      public int fromBigInt$mcI$sp(final BigInt n) {
         return ConvertableTo.fromBigInt$mcI$sp$(this, n);
      }

      public long fromBigInt$mcJ$sp(final BigInt n) {
         return ConvertableTo.fromBigInt$mcJ$sp$(this, n);
      }

      public short fromBigInt$mcS$sp(final BigInt n) {
         return ConvertableTo.fromBigInt$mcS$sp$(this, n);
      }

      public void fromBigInt$mcV$sp(final BigInt n) {
         ConvertableTo.fromBigInt$mcV$sp$(this, n);
      }

      public boolean fromBigDecimal$mcZ$sp(final BigDecimal n) {
         return ConvertableTo.fromBigDecimal$mcZ$sp$(this, n);
      }

      public char fromBigDecimal$mcC$sp(final BigDecimal n) {
         return ConvertableTo.fromBigDecimal$mcC$sp$(this, n);
      }

      public double fromBigDecimal$mcD$sp(final BigDecimal n) {
         return ConvertableTo.fromBigDecimal$mcD$sp$(this, n);
      }

      public float fromBigDecimal$mcF$sp(final BigDecimal n) {
         return ConvertableTo.fromBigDecimal$mcF$sp$(this, n);
      }

      public int fromBigDecimal$mcI$sp(final BigDecimal n) {
         return ConvertableTo.fromBigDecimal$mcI$sp$(this, n);
      }

      public long fromBigDecimal$mcJ$sp(final BigDecimal n) {
         return ConvertableTo.fromBigDecimal$mcJ$sp$(this, n);
      }

      public short fromBigDecimal$mcS$sp(final BigDecimal n) {
         return ConvertableTo.fromBigDecimal$mcS$sp$(this, n);
      }

      public void fromBigDecimal$mcV$sp(final BigDecimal n) {
         ConvertableTo.fromBigDecimal$mcV$sp$(this, n);
      }

      public boolean fromRational$mcZ$sp(final Rational n) {
         return ConvertableTo.fromRational$mcZ$sp$(this, n);
      }

      public char fromRational$mcC$sp(final Rational n) {
         return ConvertableTo.fromRational$mcC$sp$(this, n);
      }

      public double fromRational$mcD$sp(final Rational n) {
         return ConvertableTo.fromRational$mcD$sp$(this, n);
      }

      public float fromRational$mcF$sp(final Rational n) {
         return ConvertableTo.fromRational$mcF$sp$(this, n);
      }

      public int fromRational$mcI$sp(final Rational n) {
         return ConvertableTo.fromRational$mcI$sp$(this, n);
      }

      public long fromRational$mcJ$sp(final Rational n) {
         return ConvertableTo.fromRational$mcJ$sp$(this, n);
      }

      public short fromRational$mcS$sp(final Rational n) {
         return ConvertableTo.fromRational$mcS$sp$(this, n);
      }

      public void fromRational$mcV$sp(final Rational n) {
         ConvertableTo.fromRational$mcV$sp$(this, n);
      }

      public boolean fromAlgebraic$mcZ$sp(final Algebraic n) {
         return ConvertableTo.fromAlgebraic$mcZ$sp$(this, n);
      }

      public char fromAlgebraic$mcC$sp(final Algebraic n) {
         return ConvertableTo.fromAlgebraic$mcC$sp$(this, n);
      }

      public double fromAlgebraic$mcD$sp(final Algebraic n) {
         return ConvertableTo.fromAlgebraic$mcD$sp$(this, n);
      }

      public float fromAlgebraic$mcF$sp(final Algebraic n) {
         return ConvertableTo.fromAlgebraic$mcF$sp$(this, n);
      }

      public int fromAlgebraic$mcI$sp(final Algebraic n) {
         return ConvertableTo.fromAlgebraic$mcI$sp$(this, n);
      }

      public long fromAlgebraic$mcJ$sp(final Algebraic n) {
         return ConvertableTo.fromAlgebraic$mcJ$sp$(this, n);
      }

      public short fromAlgebraic$mcS$sp(final Algebraic n) {
         return ConvertableTo.fromAlgebraic$mcS$sp$(this, n);
      }

      public void fromAlgebraic$mcV$sp(final Algebraic n) {
         ConvertableTo.fromAlgebraic$mcV$sp$(this, n);
      }

      public boolean fromReal$mcZ$sp(final Real n) {
         return ConvertableTo.fromReal$mcZ$sp$(this, n);
      }

      public char fromReal$mcC$sp(final Real n) {
         return ConvertableTo.fromReal$mcC$sp$(this, n);
      }

      public double fromReal$mcD$sp(final Real n) {
         return ConvertableTo.fromReal$mcD$sp$(this, n);
      }

      public float fromReal$mcF$sp(final Real n) {
         return ConvertableTo.fromReal$mcF$sp$(this, n);
      }

      public int fromReal$mcI$sp(final Real n) {
         return ConvertableTo.fromReal$mcI$sp$(this, n);
      }

      public long fromReal$mcJ$sp(final Real n) {
         return ConvertableTo.fromReal$mcJ$sp$(this, n);
      }

      public short fromReal$mcS$sp(final Real n) {
         return ConvertableTo.fromReal$mcS$sp$(this, n);
      }

      public void fromReal$mcV$sp(final Real n) {
         ConvertableTo.fromReal$mcV$sp$(this, n);
      }

      public boolean fromType$mcZ$sp(final Object b, final ConvertableFrom evidence$1) {
         return ConvertableTo.fromType$mcZ$sp$(this, b, evidence$1);
      }

      public char fromType$mcC$sp(final Object b, final ConvertableFrom evidence$1) {
         return ConvertableTo.fromType$mcC$sp$(this, b, evidence$1);
      }

      public double fromType$mcD$sp(final Object b, final ConvertableFrom evidence$1) {
         return ConvertableTo.fromType$mcD$sp$(this, b, evidence$1);
      }

      public float fromType$mcF$sp(final Object b, final ConvertableFrom evidence$1) {
         return ConvertableTo.fromType$mcF$sp$(this, b, evidence$1);
      }

      public int fromType$mcI$sp(final Object b, final ConvertableFrom evidence$1) {
         return ConvertableTo.fromType$mcI$sp$(this, b, evidence$1);
      }

      public long fromType$mcJ$sp(final Object b, final ConvertableFrom evidence$1) {
         return ConvertableTo.fromType$mcJ$sp$(this, b, evidence$1);
      }

      public short fromType$mcS$sp(final Object b, final ConvertableFrom evidence$1) {
         return ConvertableTo.fromType$mcS$sp$(this, b, evidence$1);
      }

      public void fromType$mcV$sp(final Object b, final ConvertableFrom evidence$1) {
         ConvertableTo.fromType$mcV$sp$(this, b, evidence$1);
      }

      public {
         spire.math.ConvertableToByte.$init$(this);
      }
   };
   private static final ConvertableTo ConvertableToShort = new ConvertableToShort() {
      public short fromByte(final byte a) {
         return spire.math.ConvertableToShort.fromByte$(this, a);
      }

      public short fromShort(final short a) {
         return spire.math.ConvertableToShort.fromShort$(this, a);
      }

      public short fromInt(final int a) {
         return spire.math.ConvertableToShort.fromInt$(this, a);
      }

      public short fromLong(final long a) {
         return spire.math.ConvertableToShort.fromLong$(this, a);
      }

      public short fromFloat(final float a) {
         return spire.math.ConvertableToShort.fromFloat$(this, a);
      }

      public short fromDouble(final double a) {
         return spire.math.ConvertableToShort.fromDouble$(this, a);
      }

      public short fromBigInt(final BigInt a) {
         return spire.math.ConvertableToShort.fromBigInt$(this, a);
      }

      public short fromBigDecimal(final BigDecimal a) {
         return spire.math.ConvertableToShort.fromBigDecimal$(this, a);
      }

      public short fromRational(final Rational a) {
         return spire.math.ConvertableToShort.fromRational$(this, a);
      }

      public short fromAlgebraic(final Algebraic a) {
         return spire.math.ConvertableToShort.fromAlgebraic$(this, a);
      }

      public short fromReal(final Real a) {
         return spire.math.ConvertableToShort.fromReal$(this, a);
      }

      public short fromType(final Object b, final ConvertableFrom evidence$3) {
         return spire.math.ConvertableToShort.fromType$(this, b, evidence$3);
      }

      public short fromByte$mcS$sp(final byte a) {
         return spire.math.ConvertableToShort.fromByte$mcS$sp$(this, a);
      }

      public short fromShort$mcS$sp(final short a) {
         return spire.math.ConvertableToShort.fromShort$mcS$sp$(this, a);
      }

      public short fromInt$mcS$sp(final int a) {
         return spire.math.ConvertableToShort.fromInt$mcS$sp$(this, a);
      }

      public short fromLong$mcS$sp(final long a) {
         return spire.math.ConvertableToShort.fromLong$mcS$sp$(this, a);
      }

      public short fromFloat$mcS$sp(final float a) {
         return spire.math.ConvertableToShort.fromFloat$mcS$sp$(this, a);
      }

      public short fromDouble$mcS$sp(final double a) {
         return spire.math.ConvertableToShort.fromDouble$mcS$sp$(this, a);
      }

      public short fromBigInt$mcS$sp(final BigInt a) {
         return spire.math.ConvertableToShort.fromBigInt$mcS$sp$(this, a);
      }

      public short fromBigDecimal$mcS$sp(final BigDecimal a) {
         return spire.math.ConvertableToShort.fromBigDecimal$mcS$sp$(this, a);
      }

      public short fromRational$mcS$sp(final Rational a) {
         return spire.math.ConvertableToShort.fromRational$mcS$sp$(this, a);
      }

      public short fromAlgebraic$mcS$sp(final Algebraic a) {
         return spire.math.ConvertableToShort.fromAlgebraic$mcS$sp$(this, a);
      }

      public short fromReal$mcS$sp(final Real a) {
         return spire.math.ConvertableToShort.fromReal$mcS$sp$(this, a);
      }

      public short fromType$mcS$sp(final Object b, final ConvertableFrom evidence$3) {
         return spire.math.ConvertableToShort.fromType$mcS$sp$(this, b, evidence$3);
      }

      public boolean fromByte$mcZ$sp(final byte n) {
         return ConvertableTo.fromByte$mcZ$sp$(this, n);
      }

      public byte fromByte$mcB$sp(final byte n) {
         return ConvertableTo.fromByte$mcB$sp$(this, n);
      }

      public char fromByte$mcC$sp(final byte n) {
         return ConvertableTo.fromByte$mcC$sp$(this, n);
      }

      public double fromByte$mcD$sp(final byte n) {
         return ConvertableTo.fromByte$mcD$sp$(this, n);
      }

      public float fromByte$mcF$sp(final byte n) {
         return ConvertableTo.fromByte$mcF$sp$(this, n);
      }

      public int fromByte$mcI$sp(final byte n) {
         return ConvertableTo.fromByte$mcI$sp$(this, n);
      }

      public long fromByte$mcJ$sp(final byte n) {
         return ConvertableTo.fromByte$mcJ$sp$(this, n);
      }

      public void fromByte$mcV$sp(final byte n) {
         ConvertableTo.fromByte$mcV$sp$(this, n);
      }

      public boolean fromShort$mcZ$sp(final short n) {
         return ConvertableTo.fromShort$mcZ$sp$(this, n);
      }

      public byte fromShort$mcB$sp(final short n) {
         return ConvertableTo.fromShort$mcB$sp$(this, n);
      }

      public char fromShort$mcC$sp(final short n) {
         return ConvertableTo.fromShort$mcC$sp$(this, n);
      }

      public double fromShort$mcD$sp(final short n) {
         return ConvertableTo.fromShort$mcD$sp$(this, n);
      }

      public float fromShort$mcF$sp(final short n) {
         return ConvertableTo.fromShort$mcF$sp$(this, n);
      }

      public int fromShort$mcI$sp(final short n) {
         return ConvertableTo.fromShort$mcI$sp$(this, n);
      }

      public long fromShort$mcJ$sp(final short n) {
         return ConvertableTo.fromShort$mcJ$sp$(this, n);
      }

      public void fromShort$mcV$sp(final short n) {
         ConvertableTo.fromShort$mcV$sp$(this, n);
      }

      public boolean fromInt$mcZ$sp(final int n) {
         return ConvertableTo.fromInt$mcZ$sp$(this, n);
      }

      public byte fromInt$mcB$sp(final int n) {
         return ConvertableTo.fromInt$mcB$sp$(this, n);
      }

      public char fromInt$mcC$sp(final int n) {
         return ConvertableTo.fromInt$mcC$sp$(this, n);
      }

      public double fromInt$mcD$sp(final int n) {
         return ConvertableTo.fromInt$mcD$sp$(this, n);
      }

      public float fromInt$mcF$sp(final int n) {
         return ConvertableTo.fromInt$mcF$sp$(this, n);
      }

      public int fromInt$mcI$sp(final int n) {
         return ConvertableTo.fromInt$mcI$sp$(this, n);
      }

      public long fromInt$mcJ$sp(final int n) {
         return ConvertableTo.fromInt$mcJ$sp$(this, n);
      }

      public void fromInt$mcV$sp(final int n) {
         ConvertableTo.fromInt$mcV$sp$(this, n);
      }

      public boolean fromLong$mcZ$sp(final long n) {
         return ConvertableTo.fromLong$mcZ$sp$(this, n);
      }

      public byte fromLong$mcB$sp(final long n) {
         return ConvertableTo.fromLong$mcB$sp$(this, n);
      }

      public char fromLong$mcC$sp(final long n) {
         return ConvertableTo.fromLong$mcC$sp$(this, n);
      }

      public double fromLong$mcD$sp(final long n) {
         return ConvertableTo.fromLong$mcD$sp$(this, n);
      }

      public float fromLong$mcF$sp(final long n) {
         return ConvertableTo.fromLong$mcF$sp$(this, n);
      }

      public int fromLong$mcI$sp(final long n) {
         return ConvertableTo.fromLong$mcI$sp$(this, n);
      }

      public long fromLong$mcJ$sp(final long n) {
         return ConvertableTo.fromLong$mcJ$sp$(this, n);
      }

      public void fromLong$mcV$sp(final long n) {
         ConvertableTo.fromLong$mcV$sp$(this, n);
      }

      public boolean fromFloat$mcZ$sp(final float n) {
         return ConvertableTo.fromFloat$mcZ$sp$(this, n);
      }

      public byte fromFloat$mcB$sp(final float n) {
         return ConvertableTo.fromFloat$mcB$sp$(this, n);
      }

      public char fromFloat$mcC$sp(final float n) {
         return ConvertableTo.fromFloat$mcC$sp$(this, n);
      }

      public double fromFloat$mcD$sp(final float n) {
         return ConvertableTo.fromFloat$mcD$sp$(this, n);
      }

      public float fromFloat$mcF$sp(final float n) {
         return ConvertableTo.fromFloat$mcF$sp$(this, n);
      }

      public int fromFloat$mcI$sp(final float n) {
         return ConvertableTo.fromFloat$mcI$sp$(this, n);
      }

      public long fromFloat$mcJ$sp(final float n) {
         return ConvertableTo.fromFloat$mcJ$sp$(this, n);
      }

      public void fromFloat$mcV$sp(final float n) {
         ConvertableTo.fromFloat$mcV$sp$(this, n);
      }

      public boolean fromDouble$mcZ$sp(final double n) {
         return ConvertableTo.fromDouble$mcZ$sp$(this, n);
      }

      public byte fromDouble$mcB$sp(final double n) {
         return ConvertableTo.fromDouble$mcB$sp$(this, n);
      }

      public char fromDouble$mcC$sp(final double n) {
         return ConvertableTo.fromDouble$mcC$sp$(this, n);
      }

      public double fromDouble$mcD$sp(final double n) {
         return ConvertableTo.fromDouble$mcD$sp$(this, n);
      }

      public float fromDouble$mcF$sp(final double n) {
         return ConvertableTo.fromDouble$mcF$sp$(this, n);
      }

      public int fromDouble$mcI$sp(final double n) {
         return ConvertableTo.fromDouble$mcI$sp$(this, n);
      }

      public long fromDouble$mcJ$sp(final double n) {
         return ConvertableTo.fromDouble$mcJ$sp$(this, n);
      }

      public void fromDouble$mcV$sp(final double n) {
         ConvertableTo.fromDouble$mcV$sp$(this, n);
      }

      public boolean fromBigInt$mcZ$sp(final BigInt n) {
         return ConvertableTo.fromBigInt$mcZ$sp$(this, n);
      }

      public byte fromBigInt$mcB$sp(final BigInt n) {
         return ConvertableTo.fromBigInt$mcB$sp$(this, n);
      }

      public char fromBigInt$mcC$sp(final BigInt n) {
         return ConvertableTo.fromBigInt$mcC$sp$(this, n);
      }

      public double fromBigInt$mcD$sp(final BigInt n) {
         return ConvertableTo.fromBigInt$mcD$sp$(this, n);
      }

      public float fromBigInt$mcF$sp(final BigInt n) {
         return ConvertableTo.fromBigInt$mcF$sp$(this, n);
      }

      public int fromBigInt$mcI$sp(final BigInt n) {
         return ConvertableTo.fromBigInt$mcI$sp$(this, n);
      }

      public long fromBigInt$mcJ$sp(final BigInt n) {
         return ConvertableTo.fromBigInt$mcJ$sp$(this, n);
      }

      public void fromBigInt$mcV$sp(final BigInt n) {
         ConvertableTo.fromBigInt$mcV$sp$(this, n);
      }

      public boolean fromBigDecimal$mcZ$sp(final BigDecimal n) {
         return ConvertableTo.fromBigDecimal$mcZ$sp$(this, n);
      }

      public byte fromBigDecimal$mcB$sp(final BigDecimal n) {
         return ConvertableTo.fromBigDecimal$mcB$sp$(this, n);
      }

      public char fromBigDecimal$mcC$sp(final BigDecimal n) {
         return ConvertableTo.fromBigDecimal$mcC$sp$(this, n);
      }

      public double fromBigDecimal$mcD$sp(final BigDecimal n) {
         return ConvertableTo.fromBigDecimal$mcD$sp$(this, n);
      }

      public float fromBigDecimal$mcF$sp(final BigDecimal n) {
         return ConvertableTo.fromBigDecimal$mcF$sp$(this, n);
      }

      public int fromBigDecimal$mcI$sp(final BigDecimal n) {
         return ConvertableTo.fromBigDecimal$mcI$sp$(this, n);
      }

      public long fromBigDecimal$mcJ$sp(final BigDecimal n) {
         return ConvertableTo.fromBigDecimal$mcJ$sp$(this, n);
      }

      public void fromBigDecimal$mcV$sp(final BigDecimal n) {
         ConvertableTo.fromBigDecimal$mcV$sp$(this, n);
      }

      public boolean fromRational$mcZ$sp(final Rational n) {
         return ConvertableTo.fromRational$mcZ$sp$(this, n);
      }

      public byte fromRational$mcB$sp(final Rational n) {
         return ConvertableTo.fromRational$mcB$sp$(this, n);
      }

      public char fromRational$mcC$sp(final Rational n) {
         return ConvertableTo.fromRational$mcC$sp$(this, n);
      }

      public double fromRational$mcD$sp(final Rational n) {
         return ConvertableTo.fromRational$mcD$sp$(this, n);
      }

      public float fromRational$mcF$sp(final Rational n) {
         return ConvertableTo.fromRational$mcF$sp$(this, n);
      }

      public int fromRational$mcI$sp(final Rational n) {
         return ConvertableTo.fromRational$mcI$sp$(this, n);
      }

      public long fromRational$mcJ$sp(final Rational n) {
         return ConvertableTo.fromRational$mcJ$sp$(this, n);
      }

      public void fromRational$mcV$sp(final Rational n) {
         ConvertableTo.fromRational$mcV$sp$(this, n);
      }

      public boolean fromAlgebraic$mcZ$sp(final Algebraic n) {
         return ConvertableTo.fromAlgebraic$mcZ$sp$(this, n);
      }

      public byte fromAlgebraic$mcB$sp(final Algebraic n) {
         return ConvertableTo.fromAlgebraic$mcB$sp$(this, n);
      }

      public char fromAlgebraic$mcC$sp(final Algebraic n) {
         return ConvertableTo.fromAlgebraic$mcC$sp$(this, n);
      }

      public double fromAlgebraic$mcD$sp(final Algebraic n) {
         return ConvertableTo.fromAlgebraic$mcD$sp$(this, n);
      }

      public float fromAlgebraic$mcF$sp(final Algebraic n) {
         return ConvertableTo.fromAlgebraic$mcF$sp$(this, n);
      }

      public int fromAlgebraic$mcI$sp(final Algebraic n) {
         return ConvertableTo.fromAlgebraic$mcI$sp$(this, n);
      }

      public long fromAlgebraic$mcJ$sp(final Algebraic n) {
         return ConvertableTo.fromAlgebraic$mcJ$sp$(this, n);
      }

      public void fromAlgebraic$mcV$sp(final Algebraic n) {
         ConvertableTo.fromAlgebraic$mcV$sp$(this, n);
      }

      public boolean fromReal$mcZ$sp(final Real n) {
         return ConvertableTo.fromReal$mcZ$sp$(this, n);
      }

      public byte fromReal$mcB$sp(final Real n) {
         return ConvertableTo.fromReal$mcB$sp$(this, n);
      }

      public char fromReal$mcC$sp(final Real n) {
         return ConvertableTo.fromReal$mcC$sp$(this, n);
      }

      public double fromReal$mcD$sp(final Real n) {
         return ConvertableTo.fromReal$mcD$sp$(this, n);
      }

      public float fromReal$mcF$sp(final Real n) {
         return ConvertableTo.fromReal$mcF$sp$(this, n);
      }

      public int fromReal$mcI$sp(final Real n) {
         return ConvertableTo.fromReal$mcI$sp$(this, n);
      }

      public long fromReal$mcJ$sp(final Real n) {
         return ConvertableTo.fromReal$mcJ$sp$(this, n);
      }

      public void fromReal$mcV$sp(final Real n) {
         ConvertableTo.fromReal$mcV$sp$(this, n);
      }

      public boolean fromType$mcZ$sp(final Object b, final ConvertableFrom evidence$1) {
         return ConvertableTo.fromType$mcZ$sp$(this, b, evidence$1);
      }

      public byte fromType$mcB$sp(final Object b, final ConvertableFrom evidence$1) {
         return ConvertableTo.fromType$mcB$sp$(this, b, evidence$1);
      }

      public char fromType$mcC$sp(final Object b, final ConvertableFrom evidence$1) {
         return ConvertableTo.fromType$mcC$sp$(this, b, evidence$1);
      }

      public double fromType$mcD$sp(final Object b, final ConvertableFrom evidence$1) {
         return ConvertableTo.fromType$mcD$sp$(this, b, evidence$1);
      }

      public float fromType$mcF$sp(final Object b, final ConvertableFrom evidence$1) {
         return ConvertableTo.fromType$mcF$sp$(this, b, evidence$1);
      }

      public int fromType$mcI$sp(final Object b, final ConvertableFrom evidence$1) {
         return ConvertableTo.fromType$mcI$sp$(this, b, evidence$1);
      }

      public long fromType$mcJ$sp(final Object b, final ConvertableFrom evidence$1) {
         return ConvertableTo.fromType$mcJ$sp$(this, b, evidence$1);
      }

      public void fromType$mcV$sp(final Object b, final ConvertableFrom evidence$1) {
         ConvertableTo.fromType$mcV$sp$(this, b, evidence$1);
      }

      public {
         spire.math.ConvertableToShort.$init$(this);
      }
   };
   private static final ConvertableTo ConvertableToInt = new ConvertableToInt() {
      public int fromByte(final byte a) {
         return spire.math.ConvertableToInt.fromByte$(this, a);
      }

      public int fromShort(final short a) {
         return spire.math.ConvertableToInt.fromShort$(this, a);
      }

      public int fromInt(final int a) {
         return spire.math.ConvertableToInt.fromInt$(this, a);
      }

      public int fromLong(final long a) {
         return spire.math.ConvertableToInt.fromLong$(this, a);
      }

      public int fromFloat(final float a) {
         return spire.math.ConvertableToInt.fromFloat$(this, a);
      }

      public int fromDouble(final double a) {
         return spire.math.ConvertableToInt.fromDouble$(this, a);
      }

      public int fromBigInt(final BigInt a) {
         return spire.math.ConvertableToInt.fromBigInt$(this, a);
      }

      public int fromBigDecimal(final BigDecimal a) {
         return spire.math.ConvertableToInt.fromBigDecimal$(this, a);
      }

      public int fromRational(final Rational a) {
         return spire.math.ConvertableToInt.fromRational$(this, a);
      }

      public int fromAlgebraic(final Algebraic a) {
         return spire.math.ConvertableToInt.fromAlgebraic$(this, a);
      }

      public int fromReal(final Real a) {
         return spire.math.ConvertableToInt.fromReal$(this, a);
      }

      public int fromType(final Object b, final ConvertableFrom evidence$4) {
         return spire.math.ConvertableToInt.fromType$(this, b, evidence$4);
      }

      public int fromByte$mcI$sp(final byte a) {
         return spire.math.ConvertableToInt.fromByte$mcI$sp$(this, a);
      }

      public int fromShort$mcI$sp(final short a) {
         return spire.math.ConvertableToInt.fromShort$mcI$sp$(this, a);
      }

      public int fromInt$mcI$sp(final int a) {
         return spire.math.ConvertableToInt.fromInt$mcI$sp$(this, a);
      }

      public int fromLong$mcI$sp(final long a) {
         return spire.math.ConvertableToInt.fromLong$mcI$sp$(this, a);
      }

      public int fromFloat$mcI$sp(final float a) {
         return spire.math.ConvertableToInt.fromFloat$mcI$sp$(this, a);
      }

      public int fromDouble$mcI$sp(final double a) {
         return spire.math.ConvertableToInt.fromDouble$mcI$sp$(this, a);
      }

      public int fromBigInt$mcI$sp(final BigInt a) {
         return spire.math.ConvertableToInt.fromBigInt$mcI$sp$(this, a);
      }

      public int fromBigDecimal$mcI$sp(final BigDecimal a) {
         return spire.math.ConvertableToInt.fromBigDecimal$mcI$sp$(this, a);
      }

      public int fromRational$mcI$sp(final Rational a) {
         return spire.math.ConvertableToInt.fromRational$mcI$sp$(this, a);
      }

      public int fromAlgebraic$mcI$sp(final Algebraic a) {
         return spire.math.ConvertableToInt.fromAlgebraic$mcI$sp$(this, a);
      }

      public int fromReal$mcI$sp(final Real a) {
         return spire.math.ConvertableToInt.fromReal$mcI$sp$(this, a);
      }

      public int fromType$mcI$sp(final Object b, final ConvertableFrom evidence$4) {
         return spire.math.ConvertableToInt.fromType$mcI$sp$(this, b, evidence$4);
      }

      public boolean fromByte$mcZ$sp(final byte n) {
         return ConvertableTo.fromByte$mcZ$sp$(this, n);
      }

      public byte fromByte$mcB$sp(final byte n) {
         return ConvertableTo.fromByte$mcB$sp$(this, n);
      }

      public char fromByte$mcC$sp(final byte n) {
         return ConvertableTo.fromByte$mcC$sp$(this, n);
      }

      public double fromByte$mcD$sp(final byte n) {
         return ConvertableTo.fromByte$mcD$sp$(this, n);
      }

      public float fromByte$mcF$sp(final byte n) {
         return ConvertableTo.fromByte$mcF$sp$(this, n);
      }

      public long fromByte$mcJ$sp(final byte n) {
         return ConvertableTo.fromByte$mcJ$sp$(this, n);
      }

      public short fromByte$mcS$sp(final byte n) {
         return ConvertableTo.fromByte$mcS$sp$(this, n);
      }

      public void fromByte$mcV$sp(final byte n) {
         ConvertableTo.fromByte$mcV$sp$(this, n);
      }

      public boolean fromShort$mcZ$sp(final short n) {
         return ConvertableTo.fromShort$mcZ$sp$(this, n);
      }

      public byte fromShort$mcB$sp(final short n) {
         return ConvertableTo.fromShort$mcB$sp$(this, n);
      }

      public char fromShort$mcC$sp(final short n) {
         return ConvertableTo.fromShort$mcC$sp$(this, n);
      }

      public double fromShort$mcD$sp(final short n) {
         return ConvertableTo.fromShort$mcD$sp$(this, n);
      }

      public float fromShort$mcF$sp(final short n) {
         return ConvertableTo.fromShort$mcF$sp$(this, n);
      }

      public long fromShort$mcJ$sp(final short n) {
         return ConvertableTo.fromShort$mcJ$sp$(this, n);
      }

      public short fromShort$mcS$sp(final short n) {
         return ConvertableTo.fromShort$mcS$sp$(this, n);
      }

      public void fromShort$mcV$sp(final short n) {
         ConvertableTo.fromShort$mcV$sp$(this, n);
      }

      public boolean fromInt$mcZ$sp(final int n) {
         return ConvertableTo.fromInt$mcZ$sp$(this, n);
      }

      public byte fromInt$mcB$sp(final int n) {
         return ConvertableTo.fromInt$mcB$sp$(this, n);
      }

      public char fromInt$mcC$sp(final int n) {
         return ConvertableTo.fromInt$mcC$sp$(this, n);
      }

      public double fromInt$mcD$sp(final int n) {
         return ConvertableTo.fromInt$mcD$sp$(this, n);
      }

      public float fromInt$mcF$sp(final int n) {
         return ConvertableTo.fromInt$mcF$sp$(this, n);
      }

      public long fromInt$mcJ$sp(final int n) {
         return ConvertableTo.fromInt$mcJ$sp$(this, n);
      }

      public short fromInt$mcS$sp(final int n) {
         return ConvertableTo.fromInt$mcS$sp$(this, n);
      }

      public void fromInt$mcV$sp(final int n) {
         ConvertableTo.fromInt$mcV$sp$(this, n);
      }

      public boolean fromLong$mcZ$sp(final long n) {
         return ConvertableTo.fromLong$mcZ$sp$(this, n);
      }

      public byte fromLong$mcB$sp(final long n) {
         return ConvertableTo.fromLong$mcB$sp$(this, n);
      }

      public char fromLong$mcC$sp(final long n) {
         return ConvertableTo.fromLong$mcC$sp$(this, n);
      }

      public double fromLong$mcD$sp(final long n) {
         return ConvertableTo.fromLong$mcD$sp$(this, n);
      }

      public float fromLong$mcF$sp(final long n) {
         return ConvertableTo.fromLong$mcF$sp$(this, n);
      }

      public long fromLong$mcJ$sp(final long n) {
         return ConvertableTo.fromLong$mcJ$sp$(this, n);
      }

      public short fromLong$mcS$sp(final long n) {
         return ConvertableTo.fromLong$mcS$sp$(this, n);
      }

      public void fromLong$mcV$sp(final long n) {
         ConvertableTo.fromLong$mcV$sp$(this, n);
      }

      public boolean fromFloat$mcZ$sp(final float n) {
         return ConvertableTo.fromFloat$mcZ$sp$(this, n);
      }

      public byte fromFloat$mcB$sp(final float n) {
         return ConvertableTo.fromFloat$mcB$sp$(this, n);
      }

      public char fromFloat$mcC$sp(final float n) {
         return ConvertableTo.fromFloat$mcC$sp$(this, n);
      }

      public double fromFloat$mcD$sp(final float n) {
         return ConvertableTo.fromFloat$mcD$sp$(this, n);
      }

      public float fromFloat$mcF$sp(final float n) {
         return ConvertableTo.fromFloat$mcF$sp$(this, n);
      }

      public long fromFloat$mcJ$sp(final float n) {
         return ConvertableTo.fromFloat$mcJ$sp$(this, n);
      }

      public short fromFloat$mcS$sp(final float n) {
         return ConvertableTo.fromFloat$mcS$sp$(this, n);
      }

      public void fromFloat$mcV$sp(final float n) {
         ConvertableTo.fromFloat$mcV$sp$(this, n);
      }

      public boolean fromDouble$mcZ$sp(final double n) {
         return ConvertableTo.fromDouble$mcZ$sp$(this, n);
      }

      public byte fromDouble$mcB$sp(final double n) {
         return ConvertableTo.fromDouble$mcB$sp$(this, n);
      }

      public char fromDouble$mcC$sp(final double n) {
         return ConvertableTo.fromDouble$mcC$sp$(this, n);
      }

      public double fromDouble$mcD$sp(final double n) {
         return ConvertableTo.fromDouble$mcD$sp$(this, n);
      }

      public float fromDouble$mcF$sp(final double n) {
         return ConvertableTo.fromDouble$mcF$sp$(this, n);
      }

      public long fromDouble$mcJ$sp(final double n) {
         return ConvertableTo.fromDouble$mcJ$sp$(this, n);
      }

      public short fromDouble$mcS$sp(final double n) {
         return ConvertableTo.fromDouble$mcS$sp$(this, n);
      }

      public void fromDouble$mcV$sp(final double n) {
         ConvertableTo.fromDouble$mcV$sp$(this, n);
      }

      public boolean fromBigInt$mcZ$sp(final BigInt n) {
         return ConvertableTo.fromBigInt$mcZ$sp$(this, n);
      }

      public byte fromBigInt$mcB$sp(final BigInt n) {
         return ConvertableTo.fromBigInt$mcB$sp$(this, n);
      }

      public char fromBigInt$mcC$sp(final BigInt n) {
         return ConvertableTo.fromBigInt$mcC$sp$(this, n);
      }

      public double fromBigInt$mcD$sp(final BigInt n) {
         return ConvertableTo.fromBigInt$mcD$sp$(this, n);
      }

      public float fromBigInt$mcF$sp(final BigInt n) {
         return ConvertableTo.fromBigInt$mcF$sp$(this, n);
      }

      public long fromBigInt$mcJ$sp(final BigInt n) {
         return ConvertableTo.fromBigInt$mcJ$sp$(this, n);
      }

      public short fromBigInt$mcS$sp(final BigInt n) {
         return ConvertableTo.fromBigInt$mcS$sp$(this, n);
      }

      public void fromBigInt$mcV$sp(final BigInt n) {
         ConvertableTo.fromBigInt$mcV$sp$(this, n);
      }

      public boolean fromBigDecimal$mcZ$sp(final BigDecimal n) {
         return ConvertableTo.fromBigDecimal$mcZ$sp$(this, n);
      }

      public byte fromBigDecimal$mcB$sp(final BigDecimal n) {
         return ConvertableTo.fromBigDecimal$mcB$sp$(this, n);
      }

      public char fromBigDecimal$mcC$sp(final BigDecimal n) {
         return ConvertableTo.fromBigDecimal$mcC$sp$(this, n);
      }

      public double fromBigDecimal$mcD$sp(final BigDecimal n) {
         return ConvertableTo.fromBigDecimal$mcD$sp$(this, n);
      }

      public float fromBigDecimal$mcF$sp(final BigDecimal n) {
         return ConvertableTo.fromBigDecimal$mcF$sp$(this, n);
      }

      public long fromBigDecimal$mcJ$sp(final BigDecimal n) {
         return ConvertableTo.fromBigDecimal$mcJ$sp$(this, n);
      }

      public short fromBigDecimal$mcS$sp(final BigDecimal n) {
         return ConvertableTo.fromBigDecimal$mcS$sp$(this, n);
      }

      public void fromBigDecimal$mcV$sp(final BigDecimal n) {
         ConvertableTo.fromBigDecimal$mcV$sp$(this, n);
      }

      public boolean fromRational$mcZ$sp(final Rational n) {
         return ConvertableTo.fromRational$mcZ$sp$(this, n);
      }

      public byte fromRational$mcB$sp(final Rational n) {
         return ConvertableTo.fromRational$mcB$sp$(this, n);
      }

      public char fromRational$mcC$sp(final Rational n) {
         return ConvertableTo.fromRational$mcC$sp$(this, n);
      }

      public double fromRational$mcD$sp(final Rational n) {
         return ConvertableTo.fromRational$mcD$sp$(this, n);
      }

      public float fromRational$mcF$sp(final Rational n) {
         return ConvertableTo.fromRational$mcF$sp$(this, n);
      }

      public long fromRational$mcJ$sp(final Rational n) {
         return ConvertableTo.fromRational$mcJ$sp$(this, n);
      }

      public short fromRational$mcS$sp(final Rational n) {
         return ConvertableTo.fromRational$mcS$sp$(this, n);
      }

      public void fromRational$mcV$sp(final Rational n) {
         ConvertableTo.fromRational$mcV$sp$(this, n);
      }

      public boolean fromAlgebraic$mcZ$sp(final Algebraic n) {
         return ConvertableTo.fromAlgebraic$mcZ$sp$(this, n);
      }

      public byte fromAlgebraic$mcB$sp(final Algebraic n) {
         return ConvertableTo.fromAlgebraic$mcB$sp$(this, n);
      }

      public char fromAlgebraic$mcC$sp(final Algebraic n) {
         return ConvertableTo.fromAlgebraic$mcC$sp$(this, n);
      }

      public double fromAlgebraic$mcD$sp(final Algebraic n) {
         return ConvertableTo.fromAlgebraic$mcD$sp$(this, n);
      }

      public float fromAlgebraic$mcF$sp(final Algebraic n) {
         return ConvertableTo.fromAlgebraic$mcF$sp$(this, n);
      }

      public long fromAlgebraic$mcJ$sp(final Algebraic n) {
         return ConvertableTo.fromAlgebraic$mcJ$sp$(this, n);
      }

      public short fromAlgebraic$mcS$sp(final Algebraic n) {
         return ConvertableTo.fromAlgebraic$mcS$sp$(this, n);
      }

      public void fromAlgebraic$mcV$sp(final Algebraic n) {
         ConvertableTo.fromAlgebraic$mcV$sp$(this, n);
      }

      public boolean fromReal$mcZ$sp(final Real n) {
         return ConvertableTo.fromReal$mcZ$sp$(this, n);
      }

      public byte fromReal$mcB$sp(final Real n) {
         return ConvertableTo.fromReal$mcB$sp$(this, n);
      }

      public char fromReal$mcC$sp(final Real n) {
         return ConvertableTo.fromReal$mcC$sp$(this, n);
      }

      public double fromReal$mcD$sp(final Real n) {
         return ConvertableTo.fromReal$mcD$sp$(this, n);
      }

      public float fromReal$mcF$sp(final Real n) {
         return ConvertableTo.fromReal$mcF$sp$(this, n);
      }

      public long fromReal$mcJ$sp(final Real n) {
         return ConvertableTo.fromReal$mcJ$sp$(this, n);
      }

      public short fromReal$mcS$sp(final Real n) {
         return ConvertableTo.fromReal$mcS$sp$(this, n);
      }

      public void fromReal$mcV$sp(final Real n) {
         ConvertableTo.fromReal$mcV$sp$(this, n);
      }

      public boolean fromType$mcZ$sp(final Object b, final ConvertableFrom evidence$1) {
         return ConvertableTo.fromType$mcZ$sp$(this, b, evidence$1);
      }

      public byte fromType$mcB$sp(final Object b, final ConvertableFrom evidence$1) {
         return ConvertableTo.fromType$mcB$sp$(this, b, evidence$1);
      }

      public char fromType$mcC$sp(final Object b, final ConvertableFrom evidence$1) {
         return ConvertableTo.fromType$mcC$sp$(this, b, evidence$1);
      }

      public double fromType$mcD$sp(final Object b, final ConvertableFrom evidence$1) {
         return ConvertableTo.fromType$mcD$sp$(this, b, evidence$1);
      }

      public float fromType$mcF$sp(final Object b, final ConvertableFrom evidence$1) {
         return ConvertableTo.fromType$mcF$sp$(this, b, evidence$1);
      }

      public long fromType$mcJ$sp(final Object b, final ConvertableFrom evidence$1) {
         return ConvertableTo.fromType$mcJ$sp$(this, b, evidence$1);
      }

      public short fromType$mcS$sp(final Object b, final ConvertableFrom evidence$1) {
         return ConvertableTo.fromType$mcS$sp$(this, b, evidence$1);
      }

      public void fromType$mcV$sp(final Object b, final ConvertableFrom evidence$1) {
         ConvertableTo.fromType$mcV$sp$(this, b, evidence$1);
      }

      public {
         spire.math.ConvertableToInt.$init$(this);
      }
   };
   private static final ConvertableTo ConvertableToLong = new ConvertableToLong() {
      public long fromByte(final byte a) {
         return spire.math.ConvertableToLong.fromByte$(this, a);
      }

      public long fromShort(final short a) {
         return spire.math.ConvertableToLong.fromShort$(this, a);
      }

      public long fromInt(final int a) {
         return spire.math.ConvertableToLong.fromInt$(this, a);
      }

      public long fromLong(final long a) {
         return spire.math.ConvertableToLong.fromLong$(this, a);
      }

      public long fromFloat(final float a) {
         return spire.math.ConvertableToLong.fromFloat$(this, a);
      }

      public long fromDouble(final double a) {
         return spire.math.ConvertableToLong.fromDouble$(this, a);
      }

      public long fromBigInt(final BigInt a) {
         return spire.math.ConvertableToLong.fromBigInt$(this, a);
      }

      public long fromBigDecimal(final BigDecimal a) {
         return spire.math.ConvertableToLong.fromBigDecimal$(this, a);
      }

      public long fromRational(final Rational a) {
         return spire.math.ConvertableToLong.fromRational$(this, a);
      }

      public long fromAlgebraic(final Algebraic a) {
         return spire.math.ConvertableToLong.fromAlgebraic$(this, a);
      }

      public long fromReal(final Real a) {
         return spire.math.ConvertableToLong.fromReal$(this, a);
      }

      public long fromType(final Object b, final ConvertableFrom evidence$5) {
         return spire.math.ConvertableToLong.fromType$(this, b, evidence$5);
      }

      public long fromByte$mcJ$sp(final byte a) {
         return spire.math.ConvertableToLong.fromByte$mcJ$sp$(this, a);
      }

      public long fromShort$mcJ$sp(final short a) {
         return spire.math.ConvertableToLong.fromShort$mcJ$sp$(this, a);
      }

      public long fromInt$mcJ$sp(final int a) {
         return spire.math.ConvertableToLong.fromInt$mcJ$sp$(this, a);
      }

      public long fromLong$mcJ$sp(final long a) {
         return spire.math.ConvertableToLong.fromLong$mcJ$sp$(this, a);
      }

      public long fromFloat$mcJ$sp(final float a) {
         return spire.math.ConvertableToLong.fromFloat$mcJ$sp$(this, a);
      }

      public long fromDouble$mcJ$sp(final double a) {
         return spire.math.ConvertableToLong.fromDouble$mcJ$sp$(this, a);
      }

      public long fromBigInt$mcJ$sp(final BigInt a) {
         return spire.math.ConvertableToLong.fromBigInt$mcJ$sp$(this, a);
      }

      public long fromBigDecimal$mcJ$sp(final BigDecimal a) {
         return spire.math.ConvertableToLong.fromBigDecimal$mcJ$sp$(this, a);
      }

      public long fromRational$mcJ$sp(final Rational a) {
         return spire.math.ConvertableToLong.fromRational$mcJ$sp$(this, a);
      }

      public long fromAlgebraic$mcJ$sp(final Algebraic a) {
         return spire.math.ConvertableToLong.fromAlgebraic$mcJ$sp$(this, a);
      }

      public long fromReal$mcJ$sp(final Real a) {
         return spire.math.ConvertableToLong.fromReal$mcJ$sp$(this, a);
      }

      public long fromType$mcJ$sp(final Object b, final ConvertableFrom evidence$5) {
         return spire.math.ConvertableToLong.fromType$mcJ$sp$(this, b, evidence$5);
      }

      public boolean fromByte$mcZ$sp(final byte n) {
         return ConvertableTo.fromByte$mcZ$sp$(this, n);
      }

      public byte fromByte$mcB$sp(final byte n) {
         return ConvertableTo.fromByte$mcB$sp$(this, n);
      }

      public char fromByte$mcC$sp(final byte n) {
         return ConvertableTo.fromByte$mcC$sp$(this, n);
      }

      public double fromByte$mcD$sp(final byte n) {
         return ConvertableTo.fromByte$mcD$sp$(this, n);
      }

      public float fromByte$mcF$sp(final byte n) {
         return ConvertableTo.fromByte$mcF$sp$(this, n);
      }

      public int fromByte$mcI$sp(final byte n) {
         return ConvertableTo.fromByte$mcI$sp$(this, n);
      }

      public short fromByte$mcS$sp(final byte n) {
         return ConvertableTo.fromByte$mcS$sp$(this, n);
      }

      public void fromByte$mcV$sp(final byte n) {
         ConvertableTo.fromByte$mcV$sp$(this, n);
      }

      public boolean fromShort$mcZ$sp(final short n) {
         return ConvertableTo.fromShort$mcZ$sp$(this, n);
      }

      public byte fromShort$mcB$sp(final short n) {
         return ConvertableTo.fromShort$mcB$sp$(this, n);
      }

      public char fromShort$mcC$sp(final short n) {
         return ConvertableTo.fromShort$mcC$sp$(this, n);
      }

      public double fromShort$mcD$sp(final short n) {
         return ConvertableTo.fromShort$mcD$sp$(this, n);
      }

      public float fromShort$mcF$sp(final short n) {
         return ConvertableTo.fromShort$mcF$sp$(this, n);
      }

      public int fromShort$mcI$sp(final short n) {
         return ConvertableTo.fromShort$mcI$sp$(this, n);
      }

      public short fromShort$mcS$sp(final short n) {
         return ConvertableTo.fromShort$mcS$sp$(this, n);
      }

      public void fromShort$mcV$sp(final short n) {
         ConvertableTo.fromShort$mcV$sp$(this, n);
      }

      public boolean fromInt$mcZ$sp(final int n) {
         return ConvertableTo.fromInt$mcZ$sp$(this, n);
      }

      public byte fromInt$mcB$sp(final int n) {
         return ConvertableTo.fromInt$mcB$sp$(this, n);
      }

      public char fromInt$mcC$sp(final int n) {
         return ConvertableTo.fromInt$mcC$sp$(this, n);
      }

      public double fromInt$mcD$sp(final int n) {
         return ConvertableTo.fromInt$mcD$sp$(this, n);
      }

      public float fromInt$mcF$sp(final int n) {
         return ConvertableTo.fromInt$mcF$sp$(this, n);
      }

      public int fromInt$mcI$sp(final int n) {
         return ConvertableTo.fromInt$mcI$sp$(this, n);
      }

      public short fromInt$mcS$sp(final int n) {
         return ConvertableTo.fromInt$mcS$sp$(this, n);
      }

      public void fromInt$mcV$sp(final int n) {
         ConvertableTo.fromInt$mcV$sp$(this, n);
      }

      public boolean fromLong$mcZ$sp(final long n) {
         return ConvertableTo.fromLong$mcZ$sp$(this, n);
      }

      public byte fromLong$mcB$sp(final long n) {
         return ConvertableTo.fromLong$mcB$sp$(this, n);
      }

      public char fromLong$mcC$sp(final long n) {
         return ConvertableTo.fromLong$mcC$sp$(this, n);
      }

      public double fromLong$mcD$sp(final long n) {
         return ConvertableTo.fromLong$mcD$sp$(this, n);
      }

      public float fromLong$mcF$sp(final long n) {
         return ConvertableTo.fromLong$mcF$sp$(this, n);
      }

      public int fromLong$mcI$sp(final long n) {
         return ConvertableTo.fromLong$mcI$sp$(this, n);
      }

      public short fromLong$mcS$sp(final long n) {
         return ConvertableTo.fromLong$mcS$sp$(this, n);
      }

      public void fromLong$mcV$sp(final long n) {
         ConvertableTo.fromLong$mcV$sp$(this, n);
      }

      public boolean fromFloat$mcZ$sp(final float n) {
         return ConvertableTo.fromFloat$mcZ$sp$(this, n);
      }

      public byte fromFloat$mcB$sp(final float n) {
         return ConvertableTo.fromFloat$mcB$sp$(this, n);
      }

      public char fromFloat$mcC$sp(final float n) {
         return ConvertableTo.fromFloat$mcC$sp$(this, n);
      }

      public double fromFloat$mcD$sp(final float n) {
         return ConvertableTo.fromFloat$mcD$sp$(this, n);
      }

      public float fromFloat$mcF$sp(final float n) {
         return ConvertableTo.fromFloat$mcF$sp$(this, n);
      }

      public int fromFloat$mcI$sp(final float n) {
         return ConvertableTo.fromFloat$mcI$sp$(this, n);
      }

      public short fromFloat$mcS$sp(final float n) {
         return ConvertableTo.fromFloat$mcS$sp$(this, n);
      }

      public void fromFloat$mcV$sp(final float n) {
         ConvertableTo.fromFloat$mcV$sp$(this, n);
      }

      public boolean fromDouble$mcZ$sp(final double n) {
         return ConvertableTo.fromDouble$mcZ$sp$(this, n);
      }

      public byte fromDouble$mcB$sp(final double n) {
         return ConvertableTo.fromDouble$mcB$sp$(this, n);
      }

      public char fromDouble$mcC$sp(final double n) {
         return ConvertableTo.fromDouble$mcC$sp$(this, n);
      }

      public double fromDouble$mcD$sp(final double n) {
         return ConvertableTo.fromDouble$mcD$sp$(this, n);
      }

      public float fromDouble$mcF$sp(final double n) {
         return ConvertableTo.fromDouble$mcF$sp$(this, n);
      }

      public int fromDouble$mcI$sp(final double n) {
         return ConvertableTo.fromDouble$mcI$sp$(this, n);
      }

      public short fromDouble$mcS$sp(final double n) {
         return ConvertableTo.fromDouble$mcS$sp$(this, n);
      }

      public void fromDouble$mcV$sp(final double n) {
         ConvertableTo.fromDouble$mcV$sp$(this, n);
      }

      public boolean fromBigInt$mcZ$sp(final BigInt n) {
         return ConvertableTo.fromBigInt$mcZ$sp$(this, n);
      }

      public byte fromBigInt$mcB$sp(final BigInt n) {
         return ConvertableTo.fromBigInt$mcB$sp$(this, n);
      }

      public char fromBigInt$mcC$sp(final BigInt n) {
         return ConvertableTo.fromBigInt$mcC$sp$(this, n);
      }

      public double fromBigInt$mcD$sp(final BigInt n) {
         return ConvertableTo.fromBigInt$mcD$sp$(this, n);
      }

      public float fromBigInt$mcF$sp(final BigInt n) {
         return ConvertableTo.fromBigInt$mcF$sp$(this, n);
      }

      public int fromBigInt$mcI$sp(final BigInt n) {
         return ConvertableTo.fromBigInt$mcI$sp$(this, n);
      }

      public short fromBigInt$mcS$sp(final BigInt n) {
         return ConvertableTo.fromBigInt$mcS$sp$(this, n);
      }

      public void fromBigInt$mcV$sp(final BigInt n) {
         ConvertableTo.fromBigInt$mcV$sp$(this, n);
      }

      public boolean fromBigDecimal$mcZ$sp(final BigDecimal n) {
         return ConvertableTo.fromBigDecimal$mcZ$sp$(this, n);
      }

      public byte fromBigDecimal$mcB$sp(final BigDecimal n) {
         return ConvertableTo.fromBigDecimal$mcB$sp$(this, n);
      }

      public char fromBigDecimal$mcC$sp(final BigDecimal n) {
         return ConvertableTo.fromBigDecimal$mcC$sp$(this, n);
      }

      public double fromBigDecimal$mcD$sp(final BigDecimal n) {
         return ConvertableTo.fromBigDecimal$mcD$sp$(this, n);
      }

      public float fromBigDecimal$mcF$sp(final BigDecimal n) {
         return ConvertableTo.fromBigDecimal$mcF$sp$(this, n);
      }

      public int fromBigDecimal$mcI$sp(final BigDecimal n) {
         return ConvertableTo.fromBigDecimal$mcI$sp$(this, n);
      }

      public short fromBigDecimal$mcS$sp(final BigDecimal n) {
         return ConvertableTo.fromBigDecimal$mcS$sp$(this, n);
      }

      public void fromBigDecimal$mcV$sp(final BigDecimal n) {
         ConvertableTo.fromBigDecimal$mcV$sp$(this, n);
      }

      public boolean fromRational$mcZ$sp(final Rational n) {
         return ConvertableTo.fromRational$mcZ$sp$(this, n);
      }

      public byte fromRational$mcB$sp(final Rational n) {
         return ConvertableTo.fromRational$mcB$sp$(this, n);
      }

      public char fromRational$mcC$sp(final Rational n) {
         return ConvertableTo.fromRational$mcC$sp$(this, n);
      }

      public double fromRational$mcD$sp(final Rational n) {
         return ConvertableTo.fromRational$mcD$sp$(this, n);
      }

      public float fromRational$mcF$sp(final Rational n) {
         return ConvertableTo.fromRational$mcF$sp$(this, n);
      }

      public int fromRational$mcI$sp(final Rational n) {
         return ConvertableTo.fromRational$mcI$sp$(this, n);
      }

      public short fromRational$mcS$sp(final Rational n) {
         return ConvertableTo.fromRational$mcS$sp$(this, n);
      }

      public void fromRational$mcV$sp(final Rational n) {
         ConvertableTo.fromRational$mcV$sp$(this, n);
      }

      public boolean fromAlgebraic$mcZ$sp(final Algebraic n) {
         return ConvertableTo.fromAlgebraic$mcZ$sp$(this, n);
      }

      public byte fromAlgebraic$mcB$sp(final Algebraic n) {
         return ConvertableTo.fromAlgebraic$mcB$sp$(this, n);
      }

      public char fromAlgebraic$mcC$sp(final Algebraic n) {
         return ConvertableTo.fromAlgebraic$mcC$sp$(this, n);
      }

      public double fromAlgebraic$mcD$sp(final Algebraic n) {
         return ConvertableTo.fromAlgebraic$mcD$sp$(this, n);
      }

      public float fromAlgebraic$mcF$sp(final Algebraic n) {
         return ConvertableTo.fromAlgebraic$mcF$sp$(this, n);
      }

      public int fromAlgebraic$mcI$sp(final Algebraic n) {
         return ConvertableTo.fromAlgebraic$mcI$sp$(this, n);
      }

      public short fromAlgebraic$mcS$sp(final Algebraic n) {
         return ConvertableTo.fromAlgebraic$mcS$sp$(this, n);
      }

      public void fromAlgebraic$mcV$sp(final Algebraic n) {
         ConvertableTo.fromAlgebraic$mcV$sp$(this, n);
      }

      public boolean fromReal$mcZ$sp(final Real n) {
         return ConvertableTo.fromReal$mcZ$sp$(this, n);
      }

      public byte fromReal$mcB$sp(final Real n) {
         return ConvertableTo.fromReal$mcB$sp$(this, n);
      }

      public char fromReal$mcC$sp(final Real n) {
         return ConvertableTo.fromReal$mcC$sp$(this, n);
      }

      public double fromReal$mcD$sp(final Real n) {
         return ConvertableTo.fromReal$mcD$sp$(this, n);
      }

      public float fromReal$mcF$sp(final Real n) {
         return ConvertableTo.fromReal$mcF$sp$(this, n);
      }

      public int fromReal$mcI$sp(final Real n) {
         return ConvertableTo.fromReal$mcI$sp$(this, n);
      }

      public short fromReal$mcS$sp(final Real n) {
         return ConvertableTo.fromReal$mcS$sp$(this, n);
      }

      public void fromReal$mcV$sp(final Real n) {
         ConvertableTo.fromReal$mcV$sp$(this, n);
      }

      public boolean fromType$mcZ$sp(final Object b, final ConvertableFrom evidence$1) {
         return ConvertableTo.fromType$mcZ$sp$(this, b, evidence$1);
      }

      public byte fromType$mcB$sp(final Object b, final ConvertableFrom evidence$1) {
         return ConvertableTo.fromType$mcB$sp$(this, b, evidence$1);
      }

      public char fromType$mcC$sp(final Object b, final ConvertableFrom evidence$1) {
         return ConvertableTo.fromType$mcC$sp$(this, b, evidence$1);
      }

      public double fromType$mcD$sp(final Object b, final ConvertableFrom evidence$1) {
         return ConvertableTo.fromType$mcD$sp$(this, b, evidence$1);
      }

      public float fromType$mcF$sp(final Object b, final ConvertableFrom evidence$1) {
         return ConvertableTo.fromType$mcF$sp$(this, b, evidence$1);
      }

      public int fromType$mcI$sp(final Object b, final ConvertableFrom evidence$1) {
         return ConvertableTo.fromType$mcI$sp$(this, b, evidence$1);
      }

      public short fromType$mcS$sp(final Object b, final ConvertableFrom evidence$1) {
         return ConvertableTo.fromType$mcS$sp$(this, b, evidence$1);
      }

      public void fromType$mcV$sp(final Object b, final ConvertableFrom evidence$1) {
         ConvertableTo.fromType$mcV$sp$(this, b, evidence$1);
      }

      public {
         spire.math.ConvertableToLong.$init$(this);
      }
   };
   private static final ConvertableTo ConvertableToBigInt = new ConvertableToBigInt() {
      public BigInt fromByte(final byte a) {
         return spire.math.ConvertableToBigInt.fromByte$(this, a);
      }

      public BigInt fromShort(final short a) {
         return spire.math.ConvertableToBigInt.fromShort$(this, a);
      }

      public BigInt fromInt(final int a) {
         return spire.math.ConvertableToBigInt.fromInt$(this, a);
      }

      public BigInt fromLong(final long a) {
         return spire.math.ConvertableToBigInt.fromLong$(this, a);
      }

      public BigInt fromFloat(final float a) {
         return spire.math.ConvertableToBigInt.fromFloat$(this, a);
      }

      public BigInt fromDouble(final double a) {
         return spire.math.ConvertableToBigInt.fromDouble$(this, a);
      }

      public BigInt fromBigInt(final BigInt a) {
         return spire.math.ConvertableToBigInt.fromBigInt$(this, a);
      }

      public BigInt fromBigDecimal(final BigDecimal a) {
         return spire.math.ConvertableToBigInt.fromBigDecimal$(this, a);
      }

      public BigInt fromRational(final Rational a) {
         return spire.math.ConvertableToBigInt.fromRational$(this, a);
      }

      public BigInt fromAlgebraic(final Algebraic a) {
         return spire.math.ConvertableToBigInt.fromAlgebraic$(this, a);
      }

      public BigInt fromReal(final Real a) {
         return spire.math.ConvertableToBigInt.fromReal$(this, a);
      }

      public BigInt fromType(final Object b, final ConvertableFrom evidence$8) {
         return spire.math.ConvertableToBigInt.fromType$(this, b, evidence$8);
      }

      public boolean fromByte$mcZ$sp(final byte n) {
         return ConvertableTo.fromByte$mcZ$sp$(this, n);
      }

      public byte fromByte$mcB$sp(final byte n) {
         return ConvertableTo.fromByte$mcB$sp$(this, n);
      }

      public char fromByte$mcC$sp(final byte n) {
         return ConvertableTo.fromByte$mcC$sp$(this, n);
      }

      public double fromByte$mcD$sp(final byte n) {
         return ConvertableTo.fromByte$mcD$sp$(this, n);
      }

      public float fromByte$mcF$sp(final byte n) {
         return ConvertableTo.fromByte$mcF$sp$(this, n);
      }

      public int fromByte$mcI$sp(final byte n) {
         return ConvertableTo.fromByte$mcI$sp$(this, n);
      }

      public long fromByte$mcJ$sp(final byte n) {
         return ConvertableTo.fromByte$mcJ$sp$(this, n);
      }

      public short fromByte$mcS$sp(final byte n) {
         return ConvertableTo.fromByte$mcS$sp$(this, n);
      }

      public void fromByte$mcV$sp(final byte n) {
         ConvertableTo.fromByte$mcV$sp$(this, n);
      }

      public boolean fromShort$mcZ$sp(final short n) {
         return ConvertableTo.fromShort$mcZ$sp$(this, n);
      }

      public byte fromShort$mcB$sp(final short n) {
         return ConvertableTo.fromShort$mcB$sp$(this, n);
      }

      public char fromShort$mcC$sp(final short n) {
         return ConvertableTo.fromShort$mcC$sp$(this, n);
      }

      public double fromShort$mcD$sp(final short n) {
         return ConvertableTo.fromShort$mcD$sp$(this, n);
      }

      public float fromShort$mcF$sp(final short n) {
         return ConvertableTo.fromShort$mcF$sp$(this, n);
      }

      public int fromShort$mcI$sp(final short n) {
         return ConvertableTo.fromShort$mcI$sp$(this, n);
      }

      public long fromShort$mcJ$sp(final short n) {
         return ConvertableTo.fromShort$mcJ$sp$(this, n);
      }

      public short fromShort$mcS$sp(final short n) {
         return ConvertableTo.fromShort$mcS$sp$(this, n);
      }

      public void fromShort$mcV$sp(final short n) {
         ConvertableTo.fromShort$mcV$sp$(this, n);
      }

      public boolean fromInt$mcZ$sp(final int n) {
         return ConvertableTo.fromInt$mcZ$sp$(this, n);
      }

      public byte fromInt$mcB$sp(final int n) {
         return ConvertableTo.fromInt$mcB$sp$(this, n);
      }

      public char fromInt$mcC$sp(final int n) {
         return ConvertableTo.fromInt$mcC$sp$(this, n);
      }

      public double fromInt$mcD$sp(final int n) {
         return ConvertableTo.fromInt$mcD$sp$(this, n);
      }

      public float fromInt$mcF$sp(final int n) {
         return ConvertableTo.fromInt$mcF$sp$(this, n);
      }

      public int fromInt$mcI$sp(final int n) {
         return ConvertableTo.fromInt$mcI$sp$(this, n);
      }

      public long fromInt$mcJ$sp(final int n) {
         return ConvertableTo.fromInt$mcJ$sp$(this, n);
      }

      public short fromInt$mcS$sp(final int n) {
         return ConvertableTo.fromInt$mcS$sp$(this, n);
      }

      public void fromInt$mcV$sp(final int n) {
         ConvertableTo.fromInt$mcV$sp$(this, n);
      }

      public boolean fromLong$mcZ$sp(final long n) {
         return ConvertableTo.fromLong$mcZ$sp$(this, n);
      }

      public byte fromLong$mcB$sp(final long n) {
         return ConvertableTo.fromLong$mcB$sp$(this, n);
      }

      public char fromLong$mcC$sp(final long n) {
         return ConvertableTo.fromLong$mcC$sp$(this, n);
      }

      public double fromLong$mcD$sp(final long n) {
         return ConvertableTo.fromLong$mcD$sp$(this, n);
      }

      public float fromLong$mcF$sp(final long n) {
         return ConvertableTo.fromLong$mcF$sp$(this, n);
      }

      public int fromLong$mcI$sp(final long n) {
         return ConvertableTo.fromLong$mcI$sp$(this, n);
      }

      public long fromLong$mcJ$sp(final long n) {
         return ConvertableTo.fromLong$mcJ$sp$(this, n);
      }

      public short fromLong$mcS$sp(final long n) {
         return ConvertableTo.fromLong$mcS$sp$(this, n);
      }

      public void fromLong$mcV$sp(final long n) {
         ConvertableTo.fromLong$mcV$sp$(this, n);
      }

      public boolean fromFloat$mcZ$sp(final float n) {
         return ConvertableTo.fromFloat$mcZ$sp$(this, n);
      }

      public byte fromFloat$mcB$sp(final float n) {
         return ConvertableTo.fromFloat$mcB$sp$(this, n);
      }

      public char fromFloat$mcC$sp(final float n) {
         return ConvertableTo.fromFloat$mcC$sp$(this, n);
      }

      public double fromFloat$mcD$sp(final float n) {
         return ConvertableTo.fromFloat$mcD$sp$(this, n);
      }

      public float fromFloat$mcF$sp(final float n) {
         return ConvertableTo.fromFloat$mcF$sp$(this, n);
      }

      public int fromFloat$mcI$sp(final float n) {
         return ConvertableTo.fromFloat$mcI$sp$(this, n);
      }

      public long fromFloat$mcJ$sp(final float n) {
         return ConvertableTo.fromFloat$mcJ$sp$(this, n);
      }

      public short fromFloat$mcS$sp(final float n) {
         return ConvertableTo.fromFloat$mcS$sp$(this, n);
      }

      public void fromFloat$mcV$sp(final float n) {
         ConvertableTo.fromFloat$mcV$sp$(this, n);
      }

      public boolean fromDouble$mcZ$sp(final double n) {
         return ConvertableTo.fromDouble$mcZ$sp$(this, n);
      }

      public byte fromDouble$mcB$sp(final double n) {
         return ConvertableTo.fromDouble$mcB$sp$(this, n);
      }

      public char fromDouble$mcC$sp(final double n) {
         return ConvertableTo.fromDouble$mcC$sp$(this, n);
      }

      public double fromDouble$mcD$sp(final double n) {
         return ConvertableTo.fromDouble$mcD$sp$(this, n);
      }

      public float fromDouble$mcF$sp(final double n) {
         return ConvertableTo.fromDouble$mcF$sp$(this, n);
      }

      public int fromDouble$mcI$sp(final double n) {
         return ConvertableTo.fromDouble$mcI$sp$(this, n);
      }

      public long fromDouble$mcJ$sp(final double n) {
         return ConvertableTo.fromDouble$mcJ$sp$(this, n);
      }

      public short fromDouble$mcS$sp(final double n) {
         return ConvertableTo.fromDouble$mcS$sp$(this, n);
      }

      public void fromDouble$mcV$sp(final double n) {
         ConvertableTo.fromDouble$mcV$sp$(this, n);
      }

      public boolean fromBigInt$mcZ$sp(final BigInt n) {
         return ConvertableTo.fromBigInt$mcZ$sp$(this, n);
      }

      public byte fromBigInt$mcB$sp(final BigInt n) {
         return ConvertableTo.fromBigInt$mcB$sp$(this, n);
      }

      public char fromBigInt$mcC$sp(final BigInt n) {
         return ConvertableTo.fromBigInt$mcC$sp$(this, n);
      }

      public double fromBigInt$mcD$sp(final BigInt n) {
         return ConvertableTo.fromBigInt$mcD$sp$(this, n);
      }

      public float fromBigInt$mcF$sp(final BigInt n) {
         return ConvertableTo.fromBigInt$mcF$sp$(this, n);
      }

      public int fromBigInt$mcI$sp(final BigInt n) {
         return ConvertableTo.fromBigInt$mcI$sp$(this, n);
      }

      public long fromBigInt$mcJ$sp(final BigInt n) {
         return ConvertableTo.fromBigInt$mcJ$sp$(this, n);
      }

      public short fromBigInt$mcS$sp(final BigInt n) {
         return ConvertableTo.fromBigInt$mcS$sp$(this, n);
      }

      public void fromBigInt$mcV$sp(final BigInt n) {
         ConvertableTo.fromBigInt$mcV$sp$(this, n);
      }

      public boolean fromBigDecimal$mcZ$sp(final BigDecimal n) {
         return ConvertableTo.fromBigDecimal$mcZ$sp$(this, n);
      }

      public byte fromBigDecimal$mcB$sp(final BigDecimal n) {
         return ConvertableTo.fromBigDecimal$mcB$sp$(this, n);
      }

      public char fromBigDecimal$mcC$sp(final BigDecimal n) {
         return ConvertableTo.fromBigDecimal$mcC$sp$(this, n);
      }

      public double fromBigDecimal$mcD$sp(final BigDecimal n) {
         return ConvertableTo.fromBigDecimal$mcD$sp$(this, n);
      }

      public float fromBigDecimal$mcF$sp(final BigDecimal n) {
         return ConvertableTo.fromBigDecimal$mcF$sp$(this, n);
      }

      public int fromBigDecimal$mcI$sp(final BigDecimal n) {
         return ConvertableTo.fromBigDecimal$mcI$sp$(this, n);
      }

      public long fromBigDecimal$mcJ$sp(final BigDecimal n) {
         return ConvertableTo.fromBigDecimal$mcJ$sp$(this, n);
      }

      public short fromBigDecimal$mcS$sp(final BigDecimal n) {
         return ConvertableTo.fromBigDecimal$mcS$sp$(this, n);
      }

      public void fromBigDecimal$mcV$sp(final BigDecimal n) {
         ConvertableTo.fromBigDecimal$mcV$sp$(this, n);
      }

      public boolean fromRational$mcZ$sp(final Rational n) {
         return ConvertableTo.fromRational$mcZ$sp$(this, n);
      }

      public byte fromRational$mcB$sp(final Rational n) {
         return ConvertableTo.fromRational$mcB$sp$(this, n);
      }

      public char fromRational$mcC$sp(final Rational n) {
         return ConvertableTo.fromRational$mcC$sp$(this, n);
      }

      public double fromRational$mcD$sp(final Rational n) {
         return ConvertableTo.fromRational$mcD$sp$(this, n);
      }

      public float fromRational$mcF$sp(final Rational n) {
         return ConvertableTo.fromRational$mcF$sp$(this, n);
      }

      public int fromRational$mcI$sp(final Rational n) {
         return ConvertableTo.fromRational$mcI$sp$(this, n);
      }

      public long fromRational$mcJ$sp(final Rational n) {
         return ConvertableTo.fromRational$mcJ$sp$(this, n);
      }

      public short fromRational$mcS$sp(final Rational n) {
         return ConvertableTo.fromRational$mcS$sp$(this, n);
      }

      public void fromRational$mcV$sp(final Rational n) {
         ConvertableTo.fromRational$mcV$sp$(this, n);
      }

      public boolean fromAlgebraic$mcZ$sp(final Algebraic n) {
         return ConvertableTo.fromAlgebraic$mcZ$sp$(this, n);
      }

      public byte fromAlgebraic$mcB$sp(final Algebraic n) {
         return ConvertableTo.fromAlgebraic$mcB$sp$(this, n);
      }

      public char fromAlgebraic$mcC$sp(final Algebraic n) {
         return ConvertableTo.fromAlgebraic$mcC$sp$(this, n);
      }

      public double fromAlgebraic$mcD$sp(final Algebraic n) {
         return ConvertableTo.fromAlgebraic$mcD$sp$(this, n);
      }

      public float fromAlgebraic$mcF$sp(final Algebraic n) {
         return ConvertableTo.fromAlgebraic$mcF$sp$(this, n);
      }

      public int fromAlgebraic$mcI$sp(final Algebraic n) {
         return ConvertableTo.fromAlgebraic$mcI$sp$(this, n);
      }

      public long fromAlgebraic$mcJ$sp(final Algebraic n) {
         return ConvertableTo.fromAlgebraic$mcJ$sp$(this, n);
      }

      public short fromAlgebraic$mcS$sp(final Algebraic n) {
         return ConvertableTo.fromAlgebraic$mcS$sp$(this, n);
      }

      public void fromAlgebraic$mcV$sp(final Algebraic n) {
         ConvertableTo.fromAlgebraic$mcV$sp$(this, n);
      }

      public boolean fromReal$mcZ$sp(final Real n) {
         return ConvertableTo.fromReal$mcZ$sp$(this, n);
      }

      public byte fromReal$mcB$sp(final Real n) {
         return ConvertableTo.fromReal$mcB$sp$(this, n);
      }

      public char fromReal$mcC$sp(final Real n) {
         return ConvertableTo.fromReal$mcC$sp$(this, n);
      }

      public double fromReal$mcD$sp(final Real n) {
         return ConvertableTo.fromReal$mcD$sp$(this, n);
      }

      public float fromReal$mcF$sp(final Real n) {
         return ConvertableTo.fromReal$mcF$sp$(this, n);
      }

      public int fromReal$mcI$sp(final Real n) {
         return ConvertableTo.fromReal$mcI$sp$(this, n);
      }

      public long fromReal$mcJ$sp(final Real n) {
         return ConvertableTo.fromReal$mcJ$sp$(this, n);
      }

      public short fromReal$mcS$sp(final Real n) {
         return ConvertableTo.fromReal$mcS$sp$(this, n);
      }

      public void fromReal$mcV$sp(final Real n) {
         ConvertableTo.fromReal$mcV$sp$(this, n);
      }

      public boolean fromType$mcZ$sp(final Object b, final ConvertableFrom evidence$1) {
         return ConvertableTo.fromType$mcZ$sp$(this, b, evidence$1);
      }

      public byte fromType$mcB$sp(final Object b, final ConvertableFrom evidence$1) {
         return ConvertableTo.fromType$mcB$sp$(this, b, evidence$1);
      }

      public char fromType$mcC$sp(final Object b, final ConvertableFrom evidence$1) {
         return ConvertableTo.fromType$mcC$sp$(this, b, evidence$1);
      }

      public double fromType$mcD$sp(final Object b, final ConvertableFrom evidence$1) {
         return ConvertableTo.fromType$mcD$sp$(this, b, evidence$1);
      }

      public float fromType$mcF$sp(final Object b, final ConvertableFrom evidence$1) {
         return ConvertableTo.fromType$mcF$sp$(this, b, evidence$1);
      }

      public int fromType$mcI$sp(final Object b, final ConvertableFrom evidence$1) {
         return ConvertableTo.fromType$mcI$sp$(this, b, evidence$1);
      }

      public long fromType$mcJ$sp(final Object b, final ConvertableFrom evidence$1) {
         return ConvertableTo.fromType$mcJ$sp$(this, b, evidence$1);
      }

      public short fromType$mcS$sp(final Object b, final ConvertableFrom evidence$1) {
         return ConvertableTo.fromType$mcS$sp$(this, b, evidence$1);
      }

      public void fromType$mcV$sp(final Object b, final ConvertableFrom evidence$1) {
         ConvertableTo.fromType$mcV$sp$(this, b, evidence$1);
      }

      public {
         spire.math.ConvertableToBigInt.$init$(this);
      }
   };
   private static final ConvertableTo ConvertableToFloat = new ConvertableToFloat() {
      public float fromByte(final byte a) {
         return spire.math.ConvertableToFloat.fromByte$(this, a);
      }

      public float fromShort(final short a) {
         return spire.math.ConvertableToFloat.fromShort$(this, a);
      }

      public float fromInt(final int a) {
         return spire.math.ConvertableToFloat.fromInt$(this, a);
      }

      public float fromLong(final long a) {
         return spire.math.ConvertableToFloat.fromLong$(this, a);
      }

      public float fromFloat(final float a) {
         return spire.math.ConvertableToFloat.fromFloat$(this, a);
      }

      public float fromDouble(final double a) {
         return spire.math.ConvertableToFloat.fromDouble$(this, a);
      }

      public float fromBigInt(final BigInt a) {
         return spire.math.ConvertableToFloat.fromBigInt$(this, a);
      }

      public float fromBigDecimal(final BigDecimal a) {
         return spire.math.ConvertableToFloat.fromBigDecimal$(this, a);
      }

      public float fromRational(final Rational a) {
         return spire.math.ConvertableToFloat.fromRational$(this, a);
      }

      public float fromAlgebraic(final Algebraic a) {
         return spire.math.ConvertableToFloat.fromAlgebraic$(this, a);
      }

      public float fromReal(final Real a) {
         return spire.math.ConvertableToFloat.fromReal$(this, a);
      }

      public float fromType(final Object b, final ConvertableFrom evidence$6) {
         return spire.math.ConvertableToFloat.fromType$(this, b, evidence$6);
      }

      public float fromByte$mcF$sp(final byte a) {
         return spire.math.ConvertableToFloat.fromByte$mcF$sp$(this, a);
      }

      public float fromShort$mcF$sp(final short a) {
         return spire.math.ConvertableToFloat.fromShort$mcF$sp$(this, a);
      }

      public float fromInt$mcF$sp(final int a) {
         return spire.math.ConvertableToFloat.fromInt$mcF$sp$(this, a);
      }

      public float fromLong$mcF$sp(final long a) {
         return spire.math.ConvertableToFloat.fromLong$mcF$sp$(this, a);
      }

      public float fromFloat$mcF$sp(final float a) {
         return spire.math.ConvertableToFloat.fromFloat$mcF$sp$(this, a);
      }

      public float fromDouble$mcF$sp(final double a) {
         return spire.math.ConvertableToFloat.fromDouble$mcF$sp$(this, a);
      }

      public float fromBigInt$mcF$sp(final BigInt a) {
         return spire.math.ConvertableToFloat.fromBigInt$mcF$sp$(this, a);
      }

      public float fromBigDecimal$mcF$sp(final BigDecimal a) {
         return spire.math.ConvertableToFloat.fromBigDecimal$mcF$sp$(this, a);
      }

      public float fromRational$mcF$sp(final Rational a) {
         return spire.math.ConvertableToFloat.fromRational$mcF$sp$(this, a);
      }

      public float fromAlgebraic$mcF$sp(final Algebraic a) {
         return spire.math.ConvertableToFloat.fromAlgebraic$mcF$sp$(this, a);
      }

      public float fromReal$mcF$sp(final Real a) {
         return spire.math.ConvertableToFloat.fromReal$mcF$sp$(this, a);
      }

      public float fromType$mcF$sp(final Object b, final ConvertableFrom evidence$6) {
         return spire.math.ConvertableToFloat.fromType$mcF$sp$(this, b, evidence$6);
      }

      public boolean fromByte$mcZ$sp(final byte n) {
         return ConvertableTo.fromByte$mcZ$sp$(this, n);
      }

      public byte fromByte$mcB$sp(final byte n) {
         return ConvertableTo.fromByte$mcB$sp$(this, n);
      }

      public char fromByte$mcC$sp(final byte n) {
         return ConvertableTo.fromByte$mcC$sp$(this, n);
      }

      public double fromByte$mcD$sp(final byte n) {
         return ConvertableTo.fromByte$mcD$sp$(this, n);
      }

      public int fromByte$mcI$sp(final byte n) {
         return ConvertableTo.fromByte$mcI$sp$(this, n);
      }

      public long fromByte$mcJ$sp(final byte n) {
         return ConvertableTo.fromByte$mcJ$sp$(this, n);
      }

      public short fromByte$mcS$sp(final byte n) {
         return ConvertableTo.fromByte$mcS$sp$(this, n);
      }

      public void fromByte$mcV$sp(final byte n) {
         ConvertableTo.fromByte$mcV$sp$(this, n);
      }

      public boolean fromShort$mcZ$sp(final short n) {
         return ConvertableTo.fromShort$mcZ$sp$(this, n);
      }

      public byte fromShort$mcB$sp(final short n) {
         return ConvertableTo.fromShort$mcB$sp$(this, n);
      }

      public char fromShort$mcC$sp(final short n) {
         return ConvertableTo.fromShort$mcC$sp$(this, n);
      }

      public double fromShort$mcD$sp(final short n) {
         return ConvertableTo.fromShort$mcD$sp$(this, n);
      }

      public int fromShort$mcI$sp(final short n) {
         return ConvertableTo.fromShort$mcI$sp$(this, n);
      }

      public long fromShort$mcJ$sp(final short n) {
         return ConvertableTo.fromShort$mcJ$sp$(this, n);
      }

      public short fromShort$mcS$sp(final short n) {
         return ConvertableTo.fromShort$mcS$sp$(this, n);
      }

      public void fromShort$mcV$sp(final short n) {
         ConvertableTo.fromShort$mcV$sp$(this, n);
      }

      public boolean fromInt$mcZ$sp(final int n) {
         return ConvertableTo.fromInt$mcZ$sp$(this, n);
      }

      public byte fromInt$mcB$sp(final int n) {
         return ConvertableTo.fromInt$mcB$sp$(this, n);
      }

      public char fromInt$mcC$sp(final int n) {
         return ConvertableTo.fromInt$mcC$sp$(this, n);
      }

      public double fromInt$mcD$sp(final int n) {
         return ConvertableTo.fromInt$mcD$sp$(this, n);
      }

      public int fromInt$mcI$sp(final int n) {
         return ConvertableTo.fromInt$mcI$sp$(this, n);
      }

      public long fromInt$mcJ$sp(final int n) {
         return ConvertableTo.fromInt$mcJ$sp$(this, n);
      }

      public short fromInt$mcS$sp(final int n) {
         return ConvertableTo.fromInt$mcS$sp$(this, n);
      }

      public void fromInt$mcV$sp(final int n) {
         ConvertableTo.fromInt$mcV$sp$(this, n);
      }

      public boolean fromLong$mcZ$sp(final long n) {
         return ConvertableTo.fromLong$mcZ$sp$(this, n);
      }

      public byte fromLong$mcB$sp(final long n) {
         return ConvertableTo.fromLong$mcB$sp$(this, n);
      }

      public char fromLong$mcC$sp(final long n) {
         return ConvertableTo.fromLong$mcC$sp$(this, n);
      }

      public double fromLong$mcD$sp(final long n) {
         return ConvertableTo.fromLong$mcD$sp$(this, n);
      }

      public int fromLong$mcI$sp(final long n) {
         return ConvertableTo.fromLong$mcI$sp$(this, n);
      }

      public long fromLong$mcJ$sp(final long n) {
         return ConvertableTo.fromLong$mcJ$sp$(this, n);
      }

      public short fromLong$mcS$sp(final long n) {
         return ConvertableTo.fromLong$mcS$sp$(this, n);
      }

      public void fromLong$mcV$sp(final long n) {
         ConvertableTo.fromLong$mcV$sp$(this, n);
      }

      public boolean fromFloat$mcZ$sp(final float n) {
         return ConvertableTo.fromFloat$mcZ$sp$(this, n);
      }

      public byte fromFloat$mcB$sp(final float n) {
         return ConvertableTo.fromFloat$mcB$sp$(this, n);
      }

      public char fromFloat$mcC$sp(final float n) {
         return ConvertableTo.fromFloat$mcC$sp$(this, n);
      }

      public double fromFloat$mcD$sp(final float n) {
         return ConvertableTo.fromFloat$mcD$sp$(this, n);
      }

      public int fromFloat$mcI$sp(final float n) {
         return ConvertableTo.fromFloat$mcI$sp$(this, n);
      }

      public long fromFloat$mcJ$sp(final float n) {
         return ConvertableTo.fromFloat$mcJ$sp$(this, n);
      }

      public short fromFloat$mcS$sp(final float n) {
         return ConvertableTo.fromFloat$mcS$sp$(this, n);
      }

      public void fromFloat$mcV$sp(final float n) {
         ConvertableTo.fromFloat$mcV$sp$(this, n);
      }

      public boolean fromDouble$mcZ$sp(final double n) {
         return ConvertableTo.fromDouble$mcZ$sp$(this, n);
      }

      public byte fromDouble$mcB$sp(final double n) {
         return ConvertableTo.fromDouble$mcB$sp$(this, n);
      }

      public char fromDouble$mcC$sp(final double n) {
         return ConvertableTo.fromDouble$mcC$sp$(this, n);
      }

      public double fromDouble$mcD$sp(final double n) {
         return ConvertableTo.fromDouble$mcD$sp$(this, n);
      }

      public int fromDouble$mcI$sp(final double n) {
         return ConvertableTo.fromDouble$mcI$sp$(this, n);
      }

      public long fromDouble$mcJ$sp(final double n) {
         return ConvertableTo.fromDouble$mcJ$sp$(this, n);
      }

      public short fromDouble$mcS$sp(final double n) {
         return ConvertableTo.fromDouble$mcS$sp$(this, n);
      }

      public void fromDouble$mcV$sp(final double n) {
         ConvertableTo.fromDouble$mcV$sp$(this, n);
      }

      public boolean fromBigInt$mcZ$sp(final BigInt n) {
         return ConvertableTo.fromBigInt$mcZ$sp$(this, n);
      }

      public byte fromBigInt$mcB$sp(final BigInt n) {
         return ConvertableTo.fromBigInt$mcB$sp$(this, n);
      }

      public char fromBigInt$mcC$sp(final BigInt n) {
         return ConvertableTo.fromBigInt$mcC$sp$(this, n);
      }

      public double fromBigInt$mcD$sp(final BigInt n) {
         return ConvertableTo.fromBigInt$mcD$sp$(this, n);
      }

      public int fromBigInt$mcI$sp(final BigInt n) {
         return ConvertableTo.fromBigInt$mcI$sp$(this, n);
      }

      public long fromBigInt$mcJ$sp(final BigInt n) {
         return ConvertableTo.fromBigInt$mcJ$sp$(this, n);
      }

      public short fromBigInt$mcS$sp(final BigInt n) {
         return ConvertableTo.fromBigInt$mcS$sp$(this, n);
      }

      public void fromBigInt$mcV$sp(final BigInt n) {
         ConvertableTo.fromBigInt$mcV$sp$(this, n);
      }

      public boolean fromBigDecimal$mcZ$sp(final BigDecimal n) {
         return ConvertableTo.fromBigDecimal$mcZ$sp$(this, n);
      }

      public byte fromBigDecimal$mcB$sp(final BigDecimal n) {
         return ConvertableTo.fromBigDecimal$mcB$sp$(this, n);
      }

      public char fromBigDecimal$mcC$sp(final BigDecimal n) {
         return ConvertableTo.fromBigDecimal$mcC$sp$(this, n);
      }

      public double fromBigDecimal$mcD$sp(final BigDecimal n) {
         return ConvertableTo.fromBigDecimal$mcD$sp$(this, n);
      }

      public int fromBigDecimal$mcI$sp(final BigDecimal n) {
         return ConvertableTo.fromBigDecimal$mcI$sp$(this, n);
      }

      public long fromBigDecimal$mcJ$sp(final BigDecimal n) {
         return ConvertableTo.fromBigDecimal$mcJ$sp$(this, n);
      }

      public short fromBigDecimal$mcS$sp(final BigDecimal n) {
         return ConvertableTo.fromBigDecimal$mcS$sp$(this, n);
      }

      public void fromBigDecimal$mcV$sp(final BigDecimal n) {
         ConvertableTo.fromBigDecimal$mcV$sp$(this, n);
      }

      public boolean fromRational$mcZ$sp(final Rational n) {
         return ConvertableTo.fromRational$mcZ$sp$(this, n);
      }

      public byte fromRational$mcB$sp(final Rational n) {
         return ConvertableTo.fromRational$mcB$sp$(this, n);
      }

      public char fromRational$mcC$sp(final Rational n) {
         return ConvertableTo.fromRational$mcC$sp$(this, n);
      }

      public double fromRational$mcD$sp(final Rational n) {
         return ConvertableTo.fromRational$mcD$sp$(this, n);
      }

      public int fromRational$mcI$sp(final Rational n) {
         return ConvertableTo.fromRational$mcI$sp$(this, n);
      }

      public long fromRational$mcJ$sp(final Rational n) {
         return ConvertableTo.fromRational$mcJ$sp$(this, n);
      }

      public short fromRational$mcS$sp(final Rational n) {
         return ConvertableTo.fromRational$mcS$sp$(this, n);
      }

      public void fromRational$mcV$sp(final Rational n) {
         ConvertableTo.fromRational$mcV$sp$(this, n);
      }

      public boolean fromAlgebraic$mcZ$sp(final Algebraic n) {
         return ConvertableTo.fromAlgebraic$mcZ$sp$(this, n);
      }

      public byte fromAlgebraic$mcB$sp(final Algebraic n) {
         return ConvertableTo.fromAlgebraic$mcB$sp$(this, n);
      }

      public char fromAlgebraic$mcC$sp(final Algebraic n) {
         return ConvertableTo.fromAlgebraic$mcC$sp$(this, n);
      }

      public double fromAlgebraic$mcD$sp(final Algebraic n) {
         return ConvertableTo.fromAlgebraic$mcD$sp$(this, n);
      }

      public int fromAlgebraic$mcI$sp(final Algebraic n) {
         return ConvertableTo.fromAlgebraic$mcI$sp$(this, n);
      }

      public long fromAlgebraic$mcJ$sp(final Algebraic n) {
         return ConvertableTo.fromAlgebraic$mcJ$sp$(this, n);
      }

      public short fromAlgebraic$mcS$sp(final Algebraic n) {
         return ConvertableTo.fromAlgebraic$mcS$sp$(this, n);
      }

      public void fromAlgebraic$mcV$sp(final Algebraic n) {
         ConvertableTo.fromAlgebraic$mcV$sp$(this, n);
      }

      public boolean fromReal$mcZ$sp(final Real n) {
         return ConvertableTo.fromReal$mcZ$sp$(this, n);
      }

      public byte fromReal$mcB$sp(final Real n) {
         return ConvertableTo.fromReal$mcB$sp$(this, n);
      }

      public char fromReal$mcC$sp(final Real n) {
         return ConvertableTo.fromReal$mcC$sp$(this, n);
      }

      public double fromReal$mcD$sp(final Real n) {
         return ConvertableTo.fromReal$mcD$sp$(this, n);
      }

      public int fromReal$mcI$sp(final Real n) {
         return ConvertableTo.fromReal$mcI$sp$(this, n);
      }

      public long fromReal$mcJ$sp(final Real n) {
         return ConvertableTo.fromReal$mcJ$sp$(this, n);
      }

      public short fromReal$mcS$sp(final Real n) {
         return ConvertableTo.fromReal$mcS$sp$(this, n);
      }

      public void fromReal$mcV$sp(final Real n) {
         ConvertableTo.fromReal$mcV$sp$(this, n);
      }

      public boolean fromType$mcZ$sp(final Object b, final ConvertableFrom evidence$1) {
         return ConvertableTo.fromType$mcZ$sp$(this, b, evidence$1);
      }

      public byte fromType$mcB$sp(final Object b, final ConvertableFrom evidence$1) {
         return ConvertableTo.fromType$mcB$sp$(this, b, evidence$1);
      }

      public char fromType$mcC$sp(final Object b, final ConvertableFrom evidence$1) {
         return ConvertableTo.fromType$mcC$sp$(this, b, evidence$1);
      }

      public double fromType$mcD$sp(final Object b, final ConvertableFrom evidence$1) {
         return ConvertableTo.fromType$mcD$sp$(this, b, evidence$1);
      }

      public int fromType$mcI$sp(final Object b, final ConvertableFrom evidence$1) {
         return ConvertableTo.fromType$mcI$sp$(this, b, evidence$1);
      }

      public long fromType$mcJ$sp(final Object b, final ConvertableFrom evidence$1) {
         return ConvertableTo.fromType$mcJ$sp$(this, b, evidence$1);
      }

      public short fromType$mcS$sp(final Object b, final ConvertableFrom evidence$1) {
         return ConvertableTo.fromType$mcS$sp$(this, b, evidence$1);
      }

      public void fromType$mcV$sp(final Object b, final ConvertableFrom evidence$1) {
         ConvertableTo.fromType$mcV$sp$(this, b, evidence$1);
      }

      public {
         spire.math.ConvertableToFloat.$init$(this);
      }
   };
   private static final ConvertableTo ConvertableToDouble = new ConvertableToDouble() {
      public double fromByte(final byte a) {
         return spire.math.ConvertableToDouble.fromByte$(this, a);
      }

      public double fromShort(final short a) {
         return spire.math.ConvertableToDouble.fromShort$(this, a);
      }

      public double fromInt(final int a) {
         return spire.math.ConvertableToDouble.fromInt$(this, a);
      }

      public double fromLong(final long a) {
         return spire.math.ConvertableToDouble.fromLong$(this, a);
      }

      public double fromFloat(final float a) {
         return spire.math.ConvertableToDouble.fromFloat$(this, a);
      }

      public double fromDouble(final double a) {
         return spire.math.ConvertableToDouble.fromDouble$(this, a);
      }

      public double fromBigInt(final BigInt a) {
         return spire.math.ConvertableToDouble.fromBigInt$(this, a);
      }

      public double fromBigDecimal(final BigDecimal a) {
         return spire.math.ConvertableToDouble.fromBigDecimal$(this, a);
      }

      public double fromRational(final Rational a) {
         return spire.math.ConvertableToDouble.fromRational$(this, a);
      }

      public double fromAlgebraic(final Algebraic a) {
         return spire.math.ConvertableToDouble.fromAlgebraic$(this, a);
      }

      public double fromReal(final Real a) {
         return spire.math.ConvertableToDouble.fromReal$(this, a);
      }

      public double fromType(final Object b, final ConvertableFrom evidence$7) {
         return spire.math.ConvertableToDouble.fromType$(this, b, evidence$7);
      }

      public double fromByte$mcD$sp(final byte a) {
         return spire.math.ConvertableToDouble.fromByte$mcD$sp$(this, a);
      }

      public double fromShort$mcD$sp(final short a) {
         return spire.math.ConvertableToDouble.fromShort$mcD$sp$(this, a);
      }

      public double fromInt$mcD$sp(final int a) {
         return spire.math.ConvertableToDouble.fromInt$mcD$sp$(this, a);
      }

      public double fromLong$mcD$sp(final long a) {
         return spire.math.ConvertableToDouble.fromLong$mcD$sp$(this, a);
      }

      public double fromFloat$mcD$sp(final float a) {
         return spire.math.ConvertableToDouble.fromFloat$mcD$sp$(this, a);
      }

      public double fromDouble$mcD$sp(final double a) {
         return spire.math.ConvertableToDouble.fromDouble$mcD$sp$(this, a);
      }

      public double fromBigInt$mcD$sp(final BigInt a) {
         return spire.math.ConvertableToDouble.fromBigInt$mcD$sp$(this, a);
      }

      public double fromBigDecimal$mcD$sp(final BigDecimal a) {
         return spire.math.ConvertableToDouble.fromBigDecimal$mcD$sp$(this, a);
      }

      public double fromRational$mcD$sp(final Rational a) {
         return spire.math.ConvertableToDouble.fromRational$mcD$sp$(this, a);
      }

      public double fromAlgebraic$mcD$sp(final Algebraic a) {
         return spire.math.ConvertableToDouble.fromAlgebraic$mcD$sp$(this, a);
      }

      public double fromReal$mcD$sp(final Real a) {
         return spire.math.ConvertableToDouble.fromReal$mcD$sp$(this, a);
      }

      public double fromType$mcD$sp(final Object b, final ConvertableFrom evidence$7) {
         return spire.math.ConvertableToDouble.fromType$mcD$sp$(this, b, evidence$7);
      }

      public boolean fromByte$mcZ$sp(final byte n) {
         return ConvertableTo.fromByte$mcZ$sp$(this, n);
      }

      public byte fromByte$mcB$sp(final byte n) {
         return ConvertableTo.fromByte$mcB$sp$(this, n);
      }

      public char fromByte$mcC$sp(final byte n) {
         return ConvertableTo.fromByte$mcC$sp$(this, n);
      }

      public float fromByte$mcF$sp(final byte n) {
         return ConvertableTo.fromByte$mcF$sp$(this, n);
      }

      public int fromByte$mcI$sp(final byte n) {
         return ConvertableTo.fromByte$mcI$sp$(this, n);
      }

      public long fromByte$mcJ$sp(final byte n) {
         return ConvertableTo.fromByte$mcJ$sp$(this, n);
      }

      public short fromByte$mcS$sp(final byte n) {
         return ConvertableTo.fromByte$mcS$sp$(this, n);
      }

      public void fromByte$mcV$sp(final byte n) {
         ConvertableTo.fromByte$mcV$sp$(this, n);
      }

      public boolean fromShort$mcZ$sp(final short n) {
         return ConvertableTo.fromShort$mcZ$sp$(this, n);
      }

      public byte fromShort$mcB$sp(final short n) {
         return ConvertableTo.fromShort$mcB$sp$(this, n);
      }

      public char fromShort$mcC$sp(final short n) {
         return ConvertableTo.fromShort$mcC$sp$(this, n);
      }

      public float fromShort$mcF$sp(final short n) {
         return ConvertableTo.fromShort$mcF$sp$(this, n);
      }

      public int fromShort$mcI$sp(final short n) {
         return ConvertableTo.fromShort$mcI$sp$(this, n);
      }

      public long fromShort$mcJ$sp(final short n) {
         return ConvertableTo.fromShort$mcJ$sp$(this, n);
      }

      public short fromShort$mcS$sp(final short n) {
         return ConvertableTo.fromShort$mcS$sp$(this, n);
      }

      public void fromShort$mcV$sp(final short n) {
         ConvertableTo.fromShort$mcV$sp$(this, n);
      }

      public boolean fromInt$mcZ$sp(final int n) {
         return ConvertableTo.fromInt$mcZ$sp$(this, n);
      }

      public byte fromInt$mcB$sp(final int n) {
         return ConvertableTo.fromInt$mcB$sp$(this, n);
      }

      public char fromInt$mcC$sp(final int n) {
         return ConvertableTo.fromInt$mcC$sp$(this, n);
      }

      public float fromInt$mcF$sp(final int n) {
         return ConvertableTo.fromInt$mcF$sp$(this, n);
      }

      public int fromInt$mcI$sp(final int n) {
         return ConvertableTo.fromInt$mcI$sp$(this, n);
      }

      public long fromInt$mcJ$sp(final int n) {
         return ConvertableTo.fromInt$mcJ$sp$(this, n);
      }

      public short fromInt$mcS$sp(final int n) {
         return ConvertableTo.fromInt$mcS$sp$(this, n);
      }

      public void fromInt$mcV$sp(final int n) {
         ConvertableTo.fromInt$mcV$sp$(this, n);
      }

      public boolean fromLong$mcZ$sp(final long n) {
         return ConvertableTo.fromLong$mcZ$sp$(this, n);
      }

      public byte fromLong$mcB$sp(final long n) {
         return ConvertableTo.fromLong$mcB$sp$(this, n);
      }

      public char fromLong$mcC$sp(final long n) {
         return ConvertableTo.fromLong$mcC$sp$(this, n);
      }

      public float fromLong$mcF$sp(final long n) {
         return ConvertableTo.fromLong$mcF$sp$(this, n);
      }

      public int fromLong$mcI$sp(final long n) {
         return ConvertableTo.fromLong$mcI$sp$(this, n);
      }

      public long fromLong$mcJ$sp(final long n) {
         return ConvertableTo.fromLong$mcJ$sp$(this, n);
      }

      public short fromLong$mcS$sp(final long n) {
         return ConvertableTo.fromLong$mcS$sp$(this, n);
      }

      public void fromLong$mcV$sp(final long n) {
         ConvertableTo.fromLong$mcV$sp$(this, n);
      }

      public boolean fromFloat$mcZ$sp(final float n) {
         return ConvertableTo.fromFloat$mcZ$sp$(this, n);
      }

      public byte fromFloat$mcB$sp(final float n) {
         return ConvertableTo.fromFloat$mcB$sp$(this, n);
      }

      public char fromFloat$mcC$sp(final float n) {
         return ConvertableTo.fromFloat$mcC$sp$(this, n);
      }

      public float fromFloat$mcF$sp(final float n) {
         return ConvertableTo.fromFloat$mcF$sp$(this, n);
      }

      public int fromFloat$mcI$sp(final float n) {
         return ConvertableTo.fromFloat$mcI$sp$(this, n);
      }

      public long fromFloat$mcJ$sp(final float n) {
         return ConvertableTo.fromFloat$mcJ$sp$(this, n);
      }

      public short fromFloat$mcS$sp(final float n) {
         return ConvertableTo.fromFloat$mcS$sp$(this, n);
      }

      public void fromFloat$mcV$sp(final float n) {
         ConvertableTo.fromFloat$mcV$sp$(this, n);
      }

      public boolean fromDouble$mcZ$sp(final double n) {
         return ConvertableTo.fromDouble$mcZ$sp$(this, n);
      }

      public byte fromDouble$mcB$sp(final double n) {
         return ConvertableTo.fromDouble$mcB$sp$(this, n);
      }

      public char fromDouble$mcC$sp(final double n) {
         return ConvertableTo.fromDouble$mcC$sp$(this, n);
      }

      public float fromDouble$mcF$sp(final double n) {
         return ConvertableTo.fromDouble$mcF$sp$(this, n);
      }

      public int fromDouble$mcI$sp(final double n) {
         return ConvertableTo.fromDouble$mcI$sp$(this, n);
      }

      public long fromDouble$mcJ$sp(final double n) {
         return ConvertableTo.fromDouble$mcJ$sp$(this, n);
      }

      public short fromDouble$mcS$sp(final double n) {
         return ConvertableTo.fromDouble$mcS$sp$(this, n);
      }

      public void fromDouble$mcV$sp(final double n) {
         ConvertableTo.fromDouble$mcV$sp$(this, n);
      }

      public boolean fromBigInt$mcZ$sp(final BigInt n) {
         return ConvertableTo.fromBigInt$mcZ$sp$(this, n);
      }

      public byte fromBigInt$mcB$sp(final BigInt n) {
         return ConvertableTo.fromBigInt$mcB$sp$(this, n);
      }

      public char fromBigInt$mcC$sp(final BigInt n) {
         return ConvertableTo.fromBigInt$mcC$sp$(this, n);
      }

      public float fromBigInt$mcF$sp(final BigInt n) {
         return ConvertableTo.fromBigInt$mcF$sp$(this, n);
      }

      public int fromBigInt$mcI$sp(final BigInt n) {
         return ConvertableTo.fromBigInt$mcI$sp$(this, n);
      }

      public long fromBigInt$mcJ$sp(final BigInt n) {
         return ConvertableTo.fromBigInt$mcJ$sp$(this, n);
      }

      public short fromBigInt$mcS$sp(final BigInt n) {
         return ConvertableTo.fromBigInt$mcS$sp$(this, n);
      }

      public void fromBigInt$mcV$sp(final BigInt n) {
         ConvertableTo.fromBigInt$mcV$sp$(this, n);
      }

      public boolean fromBigDecimal$mcZ$sp(final BigDecimal n) {
         return ConvertableTo.fromBigDecimal$mcZ$sp$(this, n);
      }

      public byte fromBigDecimal$mcB$sp(final BigDecimal n) {
         return ConvertableTo.fromBigDecimal$mcB$sp$(this, n);
      }

      public char fromBigDecimal$mcC$sp(final BigDecimal n) {
         return ConvertableTo.fromBigDecimal$mcC$sp$(this, n);
      }

      public float fromBigDecimal$mcF$sp(final BigDecimal n) {
         return ConvertableTo.fromBigDecimal$mcF$sp$(this, n);
      }

      public int fromBigDecimal$mcI$sp(final BigDecimal n) {
         return ConvertableTo.fromBigDecimal$mcI$sp$(this, n);
      }

      public long fromBigDecimal$mcJ$sp(final BigDecimal n) {
         return ConvertableTo.fromBigDecimal$mcJ$sp$(this, n);
      }

      public short fromBigDecimal$mcS$sp(final BigDecimal n) {
         return ConvertableTo.fromBigDecimal$mcS$sp$(this, n);
      }

      public void fromBigDecimal$mcV$sp(final BigDecimal n) {
         ConvertableTo.fromBigDecimal$mcV$sp$(this, n);
      }

      public boolean fromRational$mcZ$sp(final Rational n) {
         return ConvertableTo.fromRational$mcZ$sp$(this, n);
      }

      public byte fromRational$mcB$sp(final Rational n) {
         return ConvertableTo.fromRational$mcB$sp$(this, n);
      }

      public char fromRational$mcC$sp(final Rational n) {
         return ConvertableTo.fromRational$mcC$sp$(this, n);
      }

      public float fromRational$mcF$sp(final Rational n) {
         return ConvertableTo.fromRational$mcF$sp$(this, n);
      }

      public int fromRational$mcI$sp(final Rational n) {
         return ConvertableTo.fromRational$mcI$sp$(this, n);
      }

      public long fromRational$mcJ$sp(final Rational n) {
         return ConvertableTo.fromRational$mcJ$sp$(this, n);
      }

      public short fromRational$mcS$sp(final Rational n) {
         return ConvertableTo.fromRational$mcS$sp$(this, n);
      }

      public void fromRational$mcV$sp(final Rational n) {
         ConvertableTo.fromRational$mcV$sp$(this, n);
      }

      public boolean fromAlgebraic$mcZ$sp(final Algebraic n) {
         return ConvertableTo.fromAlgebraic$mcZ$sp$(this, n);
      }

      public byte fromAlgebraic$mcB$sp(final Algebraic n) {
         return ConvertableTo.fromAlgebraic$mcB$sp$(this, n);
      }

      public char fromAlgebraic$mcC$sp(final Algebraic n) {
         return ConvertableTo.fromAlgebraic$mcC$sp$(this, n);
      }

      public float fromAlgebraic$mcF$sp(final Algebraic n) {
         return ConvertableTo.fromAlgebraic$mcF$sp$(this, n);
      }

      public int fromAlgebraic$mcI$sp(final Algebraic n) {
         return ConvertableTo.fromAlgebraic$mcI$sp$(this, n);
      }

      public long fromAlgebraic$mcJ$sp(final Algebraic n) {
         return ConvertableTo.fromAlgebraic$mcJ$sp$(this, n);
      }

      public short fromAlgebraic$mcS$sp(final Algebraic n) {
         return ConvertableTo.fromAlgebraic$mcS$sp$(this, n);
      }

      public void fromAlgebraic$mcV$sp(final Algebraic n) {
         ConvertableTo.fromAlgebraic$mcV$sp$(this, n);
      }

      public boolean fromReal$mcZ$sp(final Real n) {
         return ConvertableTo.fromReal$mcZ$sp$(this, n);
      }

      public byte fromReal$mcB$sp(final Real n) {
         return ConvertableTo.fromReal$mcB$sp$(this, n);
      }

      public char fromReal$mcC$sp(final Real n) {
         return ConvertableTo.fromReal$mcC$sp$(this, n);
      }

      public float fromReal$mcF$sp(final Real n) {
         return ConvertableTo.fromReal$mcF$sp$(this, n);
      }

      public int fromReal$mcI$sp(final Real n) {
         return ConvertableTo.fromReal$mcI$sp$(this, n);
      }

      public long fromReal$mcJ$sp(final Real n) {
         return ConvertableTo.fromReal$mcJ$sp$(this, n);
      }

      public short fromReal$mcS$sp(final Real n) {
         return ConvertableTo.fromReal$mcS$sp$(this, n);
      }

      public void fromReal$mcV$sp(final Real n) {
         ConvertableTo.fromReal$mcV$sp$(this, n);
      }

      public boolean fromType$mcZ$sp(final Object b, final ConvertableFrom evidence$1) {
         return ConvertableTo.fromType$mcZ$sp$(this, b, evidence$1);
      }

      public byte fromType$mcB$sp(final Object b, final ConvertableFrom evidence$1) {
         return ConvertableTo.fromType$mcB$sp$(this, b, evidence$1);
      }

      public char fromType$mcC$sp(final Object b, final ConvertableFrom evidence$1) {
         return ConvertableTo.fromType$mcC$sp$(this, b, evidence$1);
      }

      public float fromType$mcF$sp(final Object b, final ConvertableFrom evidence$1) {
         return ConvertableTo.fromType$mcF$sp$(this, b, evidence$1);
      }

      public int fromType$mcI$sp(final Object b, final ConvertableFrom evidence$1) {
         return ConvertableTo.fromType$mcI$sp$(this, b, evidence$1);
      }

      public long fromType$mcJ$sp(final Object b, final ConvertableFrom evidence$1) {
         return ConvertableTo.fromType$mcJ$sp$(this, b, evidence$1);
      }

      public short fromType$mcS$sp(final Object b, final ConvertableFrom evidence$1) {
         return ConvertableTo.fromType$mcS$sp$(this, b, evidence$1);
      }

      public void fromType$mcV$sp(final Object b, final ConvertableFrom evidence$1) {
         ConvertableTo.fromType$mcV$sp$(this, b, evidence$1);
      }

      public {
         spire.math.ConvertableToDouble.$init$(this);
      }
   };
   private static final ConvertableTo ConvertableToBigDecimal = new ConvertableToBigDecimal() {
      public BigDecimal fromByte(final byte a) {
         return spire.math.ConvertableToBigDecimal.fromByte$(this, a);
      }

      public BigDecimal fromShort(final short a) {
         return spire.math.ConvertableToBigDecimal.fromShort$(this, a);
      }

      public BigDecimal fromInt(final int a) {
         return spire.math.ConvertableToBigDecimal.fromInt$(this, a);
      }

      public BigDecimal fromLong(final long a) {
         return spire.math.ConvertableToBigDecimal.fromLong$(this, a);
      }

      public BigDecimal fromFloat(final float a) {
         return spire.math.ConvertableToBigDecimal.fromFloat$(this, a);
      }

      public BigDecimal fromDouble(final double a) {
         return spire.math.ConvertableToBigDecimal.fromDouble$(this, a);
      }

      public BigDecimal fromBigInt(final BigInt a) {
         return spire.math.ConvertableToBigDecimal.fromBigInt$(this, a);
      }

      public BigDecimal fromBigDecimal(final BigDecimal a) {
         return spire.math.ConvertableToBigDecimal.fromBigDecimal$(this, a);
      }

      public BigDecimal fromRational(final Rational a) {
         return spire.math.ConvertableToBigDecimal.fromRational$(this, a);
      }

      public BigDecimal fromAlgebraic(final Algebraic a) {
         return spire.math.ConvertableToBigDecimal.fromAlgebraic$(this, a);
      }

      public BigDecimal fromReal(final Real a) {
         return spire.math.ConvertableToBigDecimal.fromReal$(this, a);
      }

      public BigDecimal fromType(final Object b, final ConvertableFrom evidence$9) {
         return spire.math.ConvertableToBigDecimal.fromType$(this, b, evidence$9);
      }

      public boolean fromByte$mcZ$sp(final byte n) {
         return ConvertableTo.fromByte$mcZ$sp$(this, n);
      }

      public byte fromByte$mcB$sp(final byte n) {
         return ConvertableTo.fromByte$mcB$sp$(this, n);
      }

      public char fromByte$mcC$sp(final byte n) {
         return ConvertableTo.fromByte$mcC$sp$(this, n);
      }

      public double fromByte$mcD$sp(final byte n) {
         return ConvertableTo.fromByte$mcD$sp$(this, n);
      }

      public float fromByte$mcF$sp(final byte n) {
         return ConvertableTo.fromByte$mcF$sp$(this, n);
      }

      public int fromByte$mcI$sp(final byte n) {
         return ConvertableTo.fromByte$mcI$sp$(this, n);
      }

      public long fromByte$mcJ$sp(final byte n) {
         return ConvertableTo.fromByte$mcJ$sp$(this, n);
      }

      public short fromByte$mcS$sp(final byte n) {
         return ConvertableTo.fromByte$mcS$sp$(this, n);
      }

      public void fromByte$mcV$sp(final byte n) {
         ConvertableTo.fromByte$mcV$sp$(this, n);
      }

      public boolean fromShort$mcZ$sp(final short n) {
         return ConvertableTo.fromShort$mcZ$sp$(this, n);
      }

      public byte fromShort$mcB$sp(final short n) {
         return ConvertableTo.fromShort$mcB$sp$(this, n);
      }

      public char fromShort$mcC$sp(final short n) {
         return ConvertableTo.fromShort$mcC$sp$(this, n);
      }

      public double fromShort$mcD$sp(final short n) {
         return ConvertableTo.fromShort$mcD$sp$(this, n);
      }

      public float fromShort$mcF$sp(final short n) {
         return ConvertableTo.fromShort$mcF$sp$(this, n);
      }

      public int fromShort$mcI$sp(final short n) {
         return ConvertableTo.fromShort$mcI$sp$(this, n);
      }

      public long fromShort$mcJ$sp(final short n) {
         return ConvertableTo.fromShort$mcJ$sp$(this, n);
      }

      public short fromShort$mcS$sp(final short n) {
         return ConvertableTo.fromShort$mcS$sp$(this, n);
      }

      public void fromShort$mcV$sp(final short n) {
         ConvertableTo.fromShort$mcV$sp$(this, n);
      }

      public boolean fromInt$mcZ$sp(final int n) {
         return ConvertableTo.fromInt$mcZ$sp$(this, n);
      }

      public byte fromInt$mcB$sp(final int n) {
         return ConvertableTo.fromInt$mcB$sp$(this, n);
      }

      public char fromInt$mcC$sp(final int n) {
         return ConvertableTo.fromInt$mcC$sp$(this, n);
      }

      public double fromInt$mcD$sp(final int n) {
         return ConvertableTo.fromInt$mcD$sp$(this, n);
      }

      public float fromInt$mcF$sp(final int n) {
         return ConvertableTo.fromInt$mcF$sp$(this, n);
      }

      public int fromInt$mcI$sp(final int n) {
         return ConvertableTo.fromInt$mcI$sp$(this, n);
      }

      public long fromInt$mcJ$sp(final int n) {
         return ConvertableTo.fromInt$mcJ$sp$(this, n);
      }

      public short fromInt$mcS$sp(final int n) {
         return ConvertableTo.fromInt$mcS$sp$(this, n);
      }

      public void fromInt$mcV$sp(final int n) {
         ConvertableTo.fromInt$mcV$sp$(this, n);
      }

      public boolean fromLong$mcZ$sp(final long n) {
         return ConvertableTo.fromLong$mcZ$sp$(this, n);
      }

      public byte fromLong$mcB$sp(final long n) {
         return ConvertableTo.fromLong$mcB$sp$(this, n);
      }

      public char fromLong$mcC$sp(final long n) {
         return ConvertableTo.fromLong$mcC$sp$(this, n);
      }

      public double fromLong$mcD$sp(final long n) {
         return ConvertableTo.fromLong$mcD$sp$(this, n);
      }

      public float fromLong$mcF$sp(final long n) {
         return ConvertableTo.fromLong$mcF$sp$(this, n);
      }

      public int fromLong$mcI$sp(final long n) {
         return ConvertableTo.fromLong$mcI$sp$(this, n);
      }

      public long fromLong$mcJ$sp(final long n) {
         return ConvertableTo.fromLong$mcJ$sp$(this, n);
      }

      public short fromLong$mcS$sp(final long n) {
         return ConvertableTo.fromLong$mcS$sp$(this, n);
      }

      public void fromLong$mcV$sp(final long n) {
         ConvertableTo.fromLong$mcV$sp$(this, n);
      }

      public boolean fromFloat$mcZ$sp(final float n) {
         return ConvertableTo.fromFloat$mcZ$sp$(this, n);
      }

      public byte fromFloat$mcB$sp(final float n) {
         return ConvertableTo.fromFloat$mcB$sp$(this, n);
      }

      public char fromFloat$mcC$sp(final float n) {
         return ConvertableTo.fromFloat$mcC$sp$(this, n);
      }

      public double fromFloat$mcD$sp(final float n) {
         return ConvertableTo.fromFloat$mcD$sp$(this, n);
      }

      public float fromFloat$mcF$sp(final float n) {
         return ConvertableTo.fromFloat$mcF$sp$(this, n);
      }

      public int fromFloat$mcI$sp(final float n) {
         return ConvertableTo.fromFloat$mcI$sp$(this, n);
      }

      public long fromFloat$mcJ$sp(final float n) {
         return ConvertableTo.fromFloat$mcJ$sp$(this, n);
      }

      public short fromFloat$mcS$sp(final float n) {
         return ConvertableTo.fromFloat$mcS$sp$(this, n);
      }

      public void fromFloat$mcV$sp(final float n) {
         ConvertableTo.fromFloat$mcV$sp$(this, n);
      }

      public boolean fromDouble$mcZ$sp(final double n) {
         return ConvertableTo.fromDouble$mcZ$sp$(this, n);
      }

      public byte fromDouble$mcB$sp(final double n) {
         return ConvertableTo.fromDouble$mcB$sp$(this, n);
      }

      public char fromDouble$mcC$sp(final double n) {
         return ConvertableTo.fromDouble$mcC$sp$(this, n);
      }

      public double fromDouble$mcD$sp(final double n) {
         return ConvertableTo.fromDouble$mcD$sp$(this, n);
      }

      public float fromDouble$mcF$sp(final double n) {
         return ConvertableTo.fromDouble$mcF$sp$(this, n);
      }

      public int fromDouble$mcI$sp(final double n) {
         return ConvertableTo.fromDouble$mcI$sp$(this, n);
      }

      public long fromDouble$mcJ$sp(final double n) {
         return ConvertableTo.fromDouble$mcJ$sp$(this, n);
      }

      public short fromDouble$mcS$sp(final double n) {
         return ConvertableTo.fromDouble$mcS$sp$(this, n);
      }

      public void fromDouble$mcV$sp(final double n) {
         ConvertableTo.fromDouble$mcV$sp$(this, n);
      }

      public boolean fromBigInt$mcZ$sp(final BigInt n) {
         return ConvertableTo.fromBigInt$mcZ$sp$(this, n);
      }

      public byte fromBigInt$mcB$sp(final BigInt n) {
         return ConvertableTo.fromBigInt$mcB$sp$(this, n);
      }

      public char fromBigInt$mcC$sp(final BigInt n) {
         return ConvertableTo.fromBigInt$mcC$sp$(this, n);
      }

      public double fromBigInt$mcD$sp(final BigInt n) {
         return ConvertableTo.fromBigInt$mcD$sp$(this, n);
      }

      public float fromBigInt$mcF$sp(final BigInt n) {
         return ConvertableTo.fromBigInt$mcF$sp$(this, n);
      }

      public int fromBigInt$mcI$sp(final BigInt n) {
         return ConvertableTo.fromBigInt$mcI$sp$(this, n);
      }

      public long fromBigInt$mcJ$sp(final BigInt n) {
         return ConvertableTo.fromBigInt$mcJ$sp$(this, n);
      }

      public short fromBigInt$mcS$sp(final BigInt n) {
         return ConvertableTo.fromBigInt$mcS$sp$(this, n);
      }

      public void fromBigInt$mcV$sp(final BigInt n) {
         ConvertableTo.fromBigInt$mcV$sp$(this, n);
      }

      public boolean fromBigDecimal$mcZ$sp(final BigDecimal n) {
         return ConvertableTo.fromBigDecimal$mcZ$sp$(this, n);
      }

      public byte fromBigDecimal$mcB$sp(final BigDecimal n) {
         return ConvertableTo.fromBigDecimal$mcB$sp$(this, n);
      }

      public char fromBigDecimal$mcC$sp(final BigDecimal n) {
         return ConvertableTo.fromBigDecimal$mcC$sp$(this, n);
      }

      public double fromBigDecimal$mcD$sp(final BigDecimal n) {
         return ConvertableTo.fromBigDecimal$mcD$sp$(this, n);
      }

      public float fromBigDecimal$mcF$sp(final BigDecimal n) {
         return ConvertableTo.fromBigDecimal$mcF$sp$(this, n);
      }

      public int fromBigDecimal$mcI$sp(final BigDecimal n) {
         return ConvertableTo.fromBigDecimal$mcI$sp$(this, n);
      }

      public long fromBigDecimal$mcJ$sp(final BigDecimal n) {
         return ConvertableTo.fromBigDecimal$mcJ$sp$(this, n);
      }

      public short fromBigDecimal$mcS$sp(final BigDecimal n) {
         return ConvertableTo.fromBigDecimal$mcS$sp$(this, n);
      }

      public void fromBigDecimal$mcV$sp(final BigDecimal n) {
         ConvertableTo.fromBigDecimal$mcV$sp$(this, n);
      }

      public boolean fromRational$mcZ$sp(final Rational n) {
         return ConvertableTo.fromRational$mcZ$sp$(this, n);
      }

      public byte fromRational$mcB$sp(final Rational n) {
         return ConvertableTo.fromRational$mcB$sp$(this, n);
      }

      public char fromRational$mcC$sp(final Rational n) {
         return ConvertableTo.fromRational$mcC$sp$(this, n);
      }

      public double fromRational$mcD$sp(final Rational n) {
         return ConvertableTo.fromRational$mcD$sp$(this, n);
      }

      public float fromRational$mcF$sp(final Rational n) {
         return ConvertableTo.fromRational$mcF$sp$(this, n);
      }

      public int fromRational$mcI$sp(final Rational n) {
         return ConvertableTo.fromRational$mcI$sp$(this, n);
      }

      public long fromRational$mcJ$sp(final Rational n) {
         return ConvertableTo.fromRational$mcJ$sp$(this, n);
      }

      public short fromRational$mcS$sp(final Rational n) {
         return ConvertableTo.fromRational$mcS$sp$(this, n);
      }

      public void fromRational$mcV$sp(final Rational n) {
         ConvertableTo.fromRational$mcV$sp$(this, n);
      }

      public boolean fromAlgebraic$mcZ$sp(final Algebraic n) {
         return ConvertableTo.fromAlgebraic$mcZ$sp$(this, n);
      }

      public byte fromAlgebraic$mcB$sp(final Algebraic n) {
         return ConvertableTo.fromAlgebraic$mcB$sp$(this, n);
      }

      public char fromAlgebraic$mcC$sp(final Algebraic n) {
         return ConvertableTo.fromAlgebraic$mcC$sp$(this, n);
      }

      public double fromAlgebraic$mcD$sp(final Algebraic n) {
         return ConvertableTo.fromAlgebraic$mcD$sp$(this, n);
      }

      public float fromAlgebraic$mcF$sp(final Algebraic n) {
         return ConvertableTo.fromAlgebraic$mcF$sp$(this, n);
      }

      public int fromAlgebraic$mcI$sp(final Algebraic n) {
         return ConvertableTo.fromAlgebraic$mcI$sp$(this, n);
      }

      public long fromAlgebraic$mcJ$sp(final Algebraic n) {
         return ConvertableTo.fromAlgebraic$mcJ$sp$(this, n);
      }

      public short fromAlgebraic$mcS$sp(final Algebraic n) {
         return ConvertableTo.fromAlgebraic$mcS$sp$(this, n);
      }

      public void fromAlgebraic$mcV$sp(final Algebraic n) {
         ConvertableTo.fromAlgebraic$mcV$sp$(this, n);
      }

      public boolean fromReal$mcZ$sp(final Real n) {
         return ConvertableTo.fromReal$mcZ$sp$(this, n);
      }

      public byte fromReal$mcB$sp(final Real n) {
         return ConvertableTo.fromReal$mcB$sp$(this, n);
      }

      public char fromReal$mcC$sp(final Real n) {
         return ConvertableTo.fromReal$mcC$sp$(this, n);
      }

      public double fromReal$mcD$sp(final Real n) {
         return ConvertableTo.fromReal$mcD$sp$(this, n);
      }

      public float fromReal$mcF$sp(final Real n) {
         return ConvertableTo.fromReal$mcF$sp$(this, n);
      }

      public int fromReal$mcI$sp(final Real n) {
         return ConvertableTo.fromReal$mcI$sp$(this, n);
      }

      public long fromReal$mcJ$sp(final Real n) {
         return ConvertableTo.fromReal$mcJ$sp$(this, n);
      }

      public short fromReal$mcS$sp(final Real n) {
         return ConvertableTo.fromReal$mcS$sp$(this, n);
      }

      public void fromReal$mcV$sp(final Real n) {
         ConvertableTo.fromReal$mcV$sp$(this, n);
      }

      public boolean fromType$mcZ$sp(final Object b, final ConvertableFrom evidence$1) {
         return ConvertableTo.fromType$mcZ$sp$(this, b, evidence$1);
      }

      public byte fromType$mcB$sp(final Object b, final ConvertableFrom evidence$1) {
         return ConvertableTo.fromType$mcB$sp$(this, b, evidence$1);
      }

      public char fromType$mcC$sp(final Object b, final ConvertableFrom evidence$1) {
         return ConvertableTo.fromType$mcC$sp$(this, b, evidence$1);
      }

      public double fromType$mcD$sp(final Object b, final ConvertableFrom evidence$1) {
         return ConvertableTo.fromType$mcD$sp$(this, b, evidence$1);
      }

      public float fromType$mcF$sp(final Object b, final ConvertableFrom evidence$1) {
         return ConvertableTo.fromType$mcF$sp$(this, b, evidence$1);
      }

      public int fromType$mcI$sp(final Object b, final ConvertableFrom evidence$1) {
         return ConvertableTo.fromType$mcI$sp$(this, b, evidence$1);
      }

      public long fromType$mcJ$sp(final Object b, final ConvertableFrom evidence$1) {
         return ConvertableTo.fromType$mcJ$sp$(this, b, evidence$1);
      }

      public short fromType$mcS$sp(final Object b, final ConvertableFrom evidence$1) {
         return ConvertableTo.fromType$mcS$sp$(this, b, evidence$1);
      }

      public void fromType$mcV$sp(final Object b, final ConvertableFrom evidence$1) {
         ConvertableTo.fromType$mcV$sp$(this, b, evidence$1);
      }

      public {
         spire.math.ConvertableToBigDecimal.$init$(this);
      }
   };
   private static final ConvertableTo ConvertableToRational = new ConvertableToRational() {
      public Rational fromByte(final byte a) {
         return spire.math.ConvertableToRational.fromByte$(this, a);
      }

      public Rational fromShort(final short a) {
         return spire.math.ConvertableToRational.fromShort$(this, a);
      }

      public Rational fromInt(final int a) {
         return spire.math.ConvertableToRational.fromInt$(this, a);
      }

      public Rational fromLong(final long a) {
         return spire.math.ConvertableToRational.fromLong$(this, a);
      }

      public Rational fromFloat(final float a) {
         return spire.math.ConvertableToRational.fromFloat$(this, a);
      }

      public Rational fromDouble(final double a) {
         return spire.math.ConvertableToRational.fromDouble$(this, a);
      }

      public Rational fromBigInt(final BigInt a) {
         return spire.math.ConvertableToRational.fromBigInt$(this, a);
      }

      public Rational fromBigDecimal(final BigDecimal a) {
         return spire.math.ConvertableToRational.fromBigDecimal$(this, a);
      }

      public Rational fromRational(final Rational a) {
         return spire.math.ConvertableToRational.fromRational$(this, a);
      }

      public Rational fromAlgebraic(final Algebraic a) {
         return spire.math.ConvertableToRational.fromAlgebraic$(this, a);
      }

      public Rational fromReal(final Real a) {
         return spire.math.ConvertableToRational.fromReal$(this, a);
      }

      public Rational fromType(final Object b, final ConvertableFrom evidence$10) {
         return spire.math.ConvertableToRational.fromType$(this, b, evidence$10);
      }

      public boolean fromByte$mcZ$sp(final byte n) {
         return ConvertableTo.fromByte$mcZ$sp$(this, n);
      }

      public byte fromByte$mcB$sp(final byte n) {
         return ConvertableTo.fromByte$mcB$sp$(this, n);
      }

      public char fromByte$mcC$sp(final byte n) {
         return ConvertableTo.fromByte$mcC$sp$(this, n);
      }

      public double fromByte$mcD$sp(final byte n) {
         return ConvertableTo.fromByte$mcD$sp$(this, n);
      }

      public float fromByte$mcF$sp(final byte n) {
         return ConvertableTo.fromByte$mcF$sp$(this, n);
      }

      public int fromByte$mcI$sp(final byte n) {
         return ConvertableTo.fromByte$mcI$sp$(this, n);
      }

      public long fromByte$mcJ$sp(final byte n) {
         return ConvertableTo.fromByte$mcJ$sp$(this, n);
      }

      public short fromByte$mcS$sp(final byte n) {
         return ConvertableTo.fromByte$mcS$sp$(this, n);
      }

      public void fromByte$mcV$sp(final byte n) {
         ConvertableTo.fromByte$mcV$sp$(this, n);
      }

      public boolean fromShort$mcZ$sp(final short n) {
         return ConvertableTo.fromShort$mcZ$sp$(this, n);
      }

      public byte fromShort$mcB$sp(final short n) {
         return ConvertableTo.fromShort$mcB$sp$(this, n);
      }

      public char fromShort$mcC$sp(final short n) {
         return ConvertableTo.fromShort$mcC$sp$(this, n);
      }

      public double fromShort$mcD$sp(final short n) {
         return ConvertableTo.fromShort$mcD$sp$(this, n);
      }

      public float fromShort$mcF$sp(final short n) {
         return ConvertableTo.fromShort$mcF$sp$(this, n);
      }

      public int fromShort$mcI$sp(final short n) {
         return ConvertableTo.fromShort$mcI$sp$(this, n);
      }

      public long fromShort$mcJ$sp(final short n) {
         return ConvertableTo.fromShort$mcJ$sp$(this, n);
      }

      public short fromShort$mcS$sp(final short n) {
         return ConvertableTo.fromShort$mcS$sp$(this, n);
      }

      public void fromShort$mcV$sp(final short n) {
         ConvertableTo.fromShort$mcV$sp$(this, n);
      }

      public boolean fromInt$mcZ$sp(final int n) {
         return ConvertableTo.fromInt$mcZ$sp$(this, n);
      }

      public byte fromInt$mcB$sp(final int n) {
         return ConvertableTo.fromInt$mcB$sp$(this, n);
      }

      public char fromInt$mcC$sp(final int n) {
         return ConvertableTo.fromInt$mcC$sp$(this, n);
      }

      public double fromInt$mcD$sp(final int n) {
         return ConvertableTo.fromInt$mcD$sp$(this, n);
      }

      public float fromInt$mcF$sp(final int n) {
         return ConvertableTo.fromInt$mcF$sp$(this, n);
      }

      public int fromInt$mcI$sp(final int n) {
         return ConvertableTo.fromInt$mcI$sp$(this, n);
      }

      public long fromInt$mcJ$sp(final int n) {
         return ConvertableTo.fromInt$mcJ$sp$(this, n);
      }

      public short fromInt$mcS$sp(final int n) {
         return ConvertableTo.fromInt$mcS$sp$(this, n);
      }

      public void fromInt$mcV$sp(final int n) {
         ConvertableTo.fromInt$mcV$sp$(this, n);
      }

      public boolean fromLong$mcZ$sp(final long n) {
         return ConvertableTo.fromLong$mcZ$sp$(this, n);
      }

      public byte fromLong$mcB$sp(final long n) {
         return ConvertableTo.fromLong$mcB$sp$(this, n);
      }

      public char fromLong$mcC$sp(final long n) {
         return ConvertableTo.fromLong$mcC$sp$(this, n);
      }

      public double fromLong$mcD$sp(final long n) {
         return ConvertableTo.fromLong$mcD$sp$(this, n);
      }

      public float fromLong$mcF$sp(final long n) {
         return ConvertableTo.fromLong$mcF$sp$(this, n);
      }

      public int fromLong$mcI$sp(final long n) {
         return ConvertableTo.fromLong$mcI$sp$(this, n);
      }

      public long fromLong$mcJ$sp(final long n) {
         return ConvertableTo.fromLong$mcJ$sp$(this, n);
      }

      public short fromLong$mcS$sp(final long n) {
         return ConvertableTo.fromLong$mcS$sp$(this, n);
      }

      public void fromLong$mcV$sp(final long n) {
         ConvertableTo.fromLong$mcV$sp$(this, n);
      }

      public boolean fromFloat$mcZ$sp(final float n) {
         return ConvertableTo.fromFloat$mcZ$sp$(this, n);
      }

      public byte fromFloat$mcB$sp(final float n) {
         return ConvertableTo.fromFloat$mcB$sp$(this, n);
      }

      public char fromFloat$mcC$sp(final float n) {
         return ConvertableTo.fromFloat$mcC$sp$(this, n);
      }

      public double fromFloat$mcD$sp(final float n) {
         return ConvertableTo.fromFloat$mcD$sp$(this, n);
      }

      public float fromFloat$mcF$sp(final float n) {
         return ConvertableTo.fromFloat$mcF$sp$(this, n);
      }

      public int fromFloat$mcI$sp(final float n) {
         return ConvertableTo.fromFloat$mcI$sp$(this, n);
      }

      public long fromFloat$mcJ$sp(final float n) {
         return ConvertableTo.fromFloat$mcJ$sp$(this, n);
      }

      public short fromFloat$mcS$sp(final float n) {
         return ConvertableTo.fromFloat$mcS$sp$(this, n);
      }

      public void fromFloat$mcV$sp(final float n) {
         ConvertableTo.fromFloat$mcV$sp$(this, n);
      }

      public boolean fromDouble$mcZ$sp(final double n) {
         return ConvertableTo.fromDouble$mcZ$sp$(this, n);
      }

      public byte fromDouble$mcB$sp(final double n) {
         return ConvertableTo.fromDouble$mcB$sp$(this, n);
      }

      public char fromDouble$mcC$sp(final double n) {
         return ConvertableTo.fromDouble$mcC$sp$(this, n);
      }

      public double fromDouble$mcD$sp(final double n) {
         return ConvertableTo.fromDouble$mcD$sp$(this, n);
      }

      public float fromDouble$mcF$sp(final double n) {
         return ConvertableTo.fromDouble$mcF$sp$(this, n);
      }

      public int fromDouble$mcI$sp(final double n) {
         return ConvertableTo.fromDouble$mcI$sp$(this, n);
      }

      public long fromDouble$mcJ$sp(final double n) {
         return ConvertableTo.fromDouble$mcJ$sp$(this, n);
      }

      public short fromDouble$mcS$sp(final double n) {
         return ConvertableTo.fromDouble$mcS$sp$(this, n);
      }

      public void fromDouble$mcV$sp(final double n) {
         ConvertableTo.fromDouble$mcV$sp$(this, n);
      }

      public boolean fromBigInt$mcZ$sp(final BigInt n) {
         return ConvertableTo.fromBigInt$mcZ$sp$(this, n);
      }

      public byte fromBigInt$mcB$sp(final BigInt n) {
         return ConvertableTo.fromBigInt$mcB$sp$(this, n);
      }

      public char fromBigInt$mcC$sp(final BigInt n) {
         return ConvertableTo.fromBigInt$mcC$sp$(this, n);
      }

      public double fromBigInt$mcD$sp(final BigInt n) {
         return ConvertableTo.fromBigInt$mcD$sp$(this, n);
      }

      public float fromBigInt$mcF$sp(final BigInt n) {
         return ConvertableTo.fromBigInt$mcF$sp$(this, n);
      }

      public int fromBigInt$mcI$sp(final BigInt n) {
         return ConvertableTo.fromBigInt$mcI$sp$(this, n);
      }

      public long fromBigInt$mcJ$sp(final BigInt n) {
         return ConvertableTo.fromBigInt$mcJ$sp$(this, n);
      }

      public short fromBigInt$mcS$sp(final BigInt n) {
         return ConvertableTo.fromBigInt$mcS$sp$(this, n);
      }

      public void fromBigInt$mcV$sp(final BigInt n) {
         ConvertableTo.fromBigInt$mcV$sp$(this, n);
      }

      public boolean fromBigDecimal$mcZ$sp(final BigDecimal n) {
         return ConvertableTo.fromBigDecimal$mcZ$sp$(this, n);
      }

      public byte fromBigDecimal$mcB$sp(final BigDecimal n) {
         return ConvertableTo.fromBigDecimal$mcB$sp$(this, n);
      }

      public char fromBigDecimal$mcC$sp(final BigDecimal n) {
         return ConvertableTo.fromBigDecimal$mcC$sp$(this, n);
      }

      public double fromBigDecimal$mcD$sp(final BigDecimal n) {
         return ConvertableTo.fromBigDecimal$mcD$sp$(this, n);
      }

      public float fromBigDecimal$mcF$sp(final BigDecimal n) {
         return ConvertableTo.fromBigDecimal$mcF$sp$(this, n);
      }

      public int fromBigDecimal$mcI$sp(final BigDecimal n) {
         return ConvertableTo.fromBigDecimal$mcI$sp$(this, n);
      }

      public long fromBigDecimal$mcJ$sp(final BigDecimal n) {
         return ConvertableTo.fromBigDecimal$mcJ$sp$(this, n);
      }

      public short fromBigDecimal$mcS$sp(final BigDecimal n) {
         return ConvertableTo.fromBigDecimal$mcS$sp$(this, n);
      }

      public void fromBigDecimal$mcV$sp(final BigDecimal n) {
         ConvertableTo.fromBigDecimal$mcV$sp$(this, n);
      }

      public boolean fromRational$mcZ$sp(final Rational n) {
         return ConvertableTo.fromRational$mcZ$sp$(this, n);
      }

      public byte fromRational$mcB$sp(final Rational n) {
         return ConvertableTo.fromRational$mcB$sp$(this, n);
      }

      public char fromRational$mcC$sp(final Rational n) {
         return ConvertableTo.fromRational$mcC$sp$(this, n);
      }

      public double fromRational$mcD$sp(final Rational n) {
         return ConvertableTo.fromRational$mcD$sp$(this, n);
      }

      public float fromRational$mcF$sp(final Rational n) {
         return ConvertableTo.fromRational$mcF$sp$(this, n);
      }

      public int fromRational$mcI$sp(final Rational n) {
         return ConvertableTo.fromRational$mcI$sp$(this, n);
      }

      public long fromRational$mcJ$sp(final Rational n) {
         return ConvertableTo.fromRational$mcJ$sp$(this, n);
      }

      public short fromRational$mcS$sp(final Rational n) {
         return ConvertableTo.fromRational$mcS$sp$(this, n);
      }

      public void fromRational$mcV$sp(final Rational n) {
         ConvertableTo.fromRational$mcV$sp$(this, n);
      }

      public boolean fromAlgebraic$mcZ$sp(final Algebraic n) {
         return ConvertableTo.fromAlgebraic$mcZ$sp$(this, n);
      }

      public byte fromAlgebraic$mcB$sp(final Algebraic n) {
         return ConvertableTo.fromAlgebraic$mcB$sp$(this, n);
      }

      public char fromAlgebraic$mcC$sp(final Algebraic n) {
         return ConvertableTo.fromAlgebraic$mcC$sp$(this, n);
      }

      public double fromAlgebraic$mcD$sp(final Algebraic n) {
         return ConvertableTo.fromAlgebraic$mcD$sp$(this, n);
      }

      public float fromAlgebraic$mcF$sp(final Algebraic n) {
         return ConvertableTo.fromAlgebraic$mcF$sp$(this, n);
      }

      public int fromAlgebraic$mcI$sp(final Algebraic n) {
         return ConvertableTo.fromAlgebraic$mcI$sp$(this, n);
      }

      public long fromAlgebraic$mcJ$sp(final Algebraic n) {
         return ConvertableTo.fromAlgebraic$mcJ$sp$(this, n);
      }

      public short fromAlgebraic$mcS$sp(final Algebraic n) {
         return ConvertableTo.fromAlgebraic$mcS$sp$(this, n);
      }

      public void fromAlgebraic$mcV$sp(final Algebraic n) {
         ConvertableTo.fromAlgebraic$mcV$sp$(this, n);
      }

      public boolean fromReal$mcZ$sp(final Real n) {
         return ConvertableTo.fromReal$mcZ$sp$(this, n);
      }

      public byte fromReal$mcB$sp(final Real n) {
         return ConvertableTo.fromReal$mcB$sp$(this, n);
      }

      public char fromReal$mcC$sp(final Real n) {
         return ConvertableTo.fromReal$mcC$sp$(this, n);
      }

      public double fromReal$mcD$sp(final Real n) {
         return ConvertableTo.fromReal$mcD$sp$(this, n);
      }

      public float fromReal$mcF$sp(final Real n) {
         return ConvertableTo.fromReal$mcF$sp$(this, n);
      }

      public int fromReal$mcI$sp(final Real n) {
         return ConvertableTo.fromReal$mcI$sp$(this, n);
      }

      public long fromReal$mcJ$sp(final Real n) {
         return ConvertableTo.fromReal$mcJ$sp$(this, n);
      }

      public short fromReal$mcS$sp(final Real n) {
         return ConvertableTo.fromReal$mcS$sp$(this, n);
      }

      public void fromReal$mcV$sp(final Real n) {
         ConvertableTo.fromReal$mcV$sp$(this, n);
      }

      public boolean fromType$mcZ$sp(final Object b, final ConvertableFrom evidence$1) {
         return ConvertableTo.fromType$mcZ$sp$(this, b, evidence$1);
      }

      public byte fromType$mcB$sp(final Object b, final ConvertableFrom evidence$1) {
         return ConvertableTo.fromType$mcB$sp$(this, b, evidence$1);
      }

      public char fromType$mcC$sp(final Object b, final ConvertableFrom evidence$1) {
         return ConvertableTo.fromType$mcC$sp$(this, b, evidence$1);
      }

      public double fromType$mcD$sp(final Object b, final ConvertableFrom evidence$1) {
         return ConvertableTo.fromType$mcD$sp$(this, b, evidence$1);
      }

      public float fromType$mcF$sp(final Object b, final ConvertableFrom evidence$1) {
         return ConvertableTo.fromType$mcF$sp$(this, b, evidence$1);
      }

      public int fromType$mcI$sp(final Object b, final ConvertableFrom evidence$1) {
         return ConvertableTo.fromType$mcI$sp$(this, b, evidence$1);
      }

      public long fromType$mcJ$sp(final Object b, final ConvertableFrom evidence$1) {
         return ConvertableTo.fromType$mcJ$sp$(this, b, evidence$1);
      }

      public short fromType$mcS$sp(final Object b, final ConvertableFrom evidence$1) {
         return ConvertableTo.fromType$mcS$sp$(this, b, evidence$1);
      }

      public void fromType$mcV$sp(final Object b, final ConvertableFrom evidence$1) {
         ConvertableTo.fromType$mcV$sp$(this, b, evidence$1);
      }

      public {
         spire.math.ConvertableToRational.$init$(this);
      }
   };
   private static final ConvertableTo ConvertableToAlgebraic = new ConvertableToAlgebraic() {
      public Algebraic fromByte(final byte a) {
         return spire.math.ConvertableToAlgebraic.fromByte$(this, a);
      }

      public Algebraic fromShort(final short a) {
         return spire.math.ConvertableToAlgebraic.fromShort$(this, a);
      }

      public Algebraic fromInt(final int a) {
         return spire.math.ConvertableToAlgebraic.fromInt$(this, a);
      }

      public Algebraic fromLong(final long a) {
         return spire.math.ConvertableToAlgebraic.fromLong$(this, a);
      }

      public Algebraic fromFloat(final float a) {
         return spire.math.ConvertableToAlgebraic.fromFloat$(this, a);
      }

      public Algebraic fromDouble(final double a) {
         return spire.math.ConvertableToAlgebraic.fromDouble$(this, a);
      }

      public Algebraic fromBigInt(final BigInt a) {
         return spire.math.ConvertableToAlgebraic.fromBigInt$(this, a);
      }

      public Algebraic fromBigDecimal(final BigDecimal a) {
         return spire.math.ConvertableToAlgebraic.fromBigDecimal$(this, a);
      }

      public Algebraic fromRational(final Rational a) {
         return spire.math.ConvertableToAlgebraic.fromRational$(this, a);
      }

      public Algebraic fromAlgebraic(final Algebraic a) {
         return spire.math.ConvertableToAlgebraic.fromAlgebraic$(this, a);
      }

      public Algebraic fromReal(final Real a) {
         return spire.math.ConvertableToAlgebraic.fromReal$(this, a);
      }

      public Algebraic fromType(final Object b, final ConvertableFrom evidence$11) {
         return spire.math.ConvertableToAlgebraic.fromType$(this, b, evidence$11);
      }

      public boolean fromByte$mcZ$sp(final byte n) {
         return ConvertableTo.fromByte$mcZ$sp$(this, n);
      }

      public byte fromByte$mcB$sp(final byte n) {
         return ConvertableTo.fromByte$mcB$sp$(this, n);
      }

      public char fromByte$mcC$sp(final byte n) {
         return ConvertableTo.fromByte$mcC$sp$(this, n);
      }

      public double fromByte$mcD$sp(final byte n) {
         return ConvertableTo.fromByte$mcD$sp$(this, n);
      }

      public float fromByte$mcF$sp(final byte n) {
         return ConvertableTo.fromByte$mcF$sp$(this, n);
      }

      public int fromByte$mcI$sp(final byte n) {
         return ConvertableTo.fromByte$mcI$sp$(this, n);
      }

      public long fromByte$mcJ$sp(final byte n) {
         return ConvertableTo.fromByte$mcJ$sp$(this, n);
      }

      public short fromByte$mcS$sp(final byte n) {
         return ConvertableTo.fromByte$mcS$sp$(this, n);
      }

      public void fromByte$mcV$sp(final byte n) {
         ConvertableTo.fromByte$mcV$sp$(this, n);
      }

      public boolean fromShort$mcZ$sp(final short n) {
         return ConvertableTo.fromShort$mcZ$sp$(this, n);
      }

      public byte fromShort$mcB$sp(final short n) {
         return ConvertableTo.fromShort$mcB$sp$(this, n);
      }

      public char fromShort$mcC$sp(final short n) {
         return ConvertableTo.fromShort$mcC$sp$(this, n);
      }

      public double fromShort$mcD$sp(final short n) {
         return ConvertableTo.fromShort$mcD$sp$(this, n);
      }

      public float fromShort$mcF$sp(final short n) {
         return ConvertableTo.fromShort$mcF$sp$(this, n);
      }

      public int fromShort$mcI$sp(final short n) {
         return ConvertableTo.fromShort$mcI$sp$(this, n);
      }

      public long fromShort$mcJ$sp(final short n) {
         return ConvertableTo.fromShort$mcJ$sp$(this, n);
      }

      public short fromShort$mcS$sp(final short n) {
         return ConvertableTo.fromShort$mcS$sp$(this, n);
      }

      public void fromShort$mcV$sp(final short n) {
         ConvertableTo.fromShort$mcV$sp$(this, n);
      }

      public boolean fromInt$mcZ$sp(final int n) {
         return ConvertableTo.fromInt$mcZ$sp$(this, n);
      }

      public byte fromInt$mcB$sp(final int n) {
         return ConvertableTo.fromInt$mcB$sp$(this, n);
      }

      public char fromInt$mcC$sp(final int n) {
         return ConvertableTo.fromInt$mcC$sp$(this, n);
      }

      public double fromInt$mcD$sp(final int n) {
         return ConvertableTo.fromInt$mcD$sp$(this, n);
      }

      public float fromInt$mcF$sp(final int n) {
         return ConvertableTo.fromInt$mcF$sp$(this, n);
      }

      public int fromInt$mcI$sp(final int n) {
         return ConvertableTo.fromInt$mcI$sp$(this, n);
      }

      public long fromInt$mcJ$sp(final int n) {
         return ConvertableTo.fromInt$mcJ$sp$(this, n);
      }

      public short fromInt$mcS$sp(final int n) {
         return ConvertableTo.fromInt$mcS$sp$(this, n);
      }

      public void fromInt$mcV$sp(final int n) {
         ConvertableTo.fromInt$mcV$sp$(this, n);
      }

      public boolean fromLong$mcZ$sp(final long n) {
         return ConvertableTo.fromLong$mcZ$sp$(this, n);
      }

      public byte fromLong$mcB$sp(final long n) {
         return ConvertableTo.fromLong$mcB$sp$(this, n);
      }

      public char fromLong$mcC$sp(final long n) {
         return ConvertableTo.fromLong$mcC$sp$(this, n);
      }

      public double fromLong$mcD$sp(final long n) {
         return ConvertableTo.fromLong$mcD$sp$(this, n);
      }

      public float fromLong$mcF$sp(final long n) {
         return ConvertableTo.fromLong$mcF$sp$(this, n);
      }

      public int fromLong$mcI$sp(final long n) {
         return ConvertableTo.fromLong$mcI$sp$(this, n);
      }

      public long fromLong$mcJ$sp(final long n) {
         return ConvertableTo.fromLong$mcJ$sp$(this, n);
      }

      public short fromLong$mcS$sp(final long n) {
         return ConvertableTo.fromLong$mcS$sp$(this, n);
      }

      public void fromLong$mcV$sp(final long n) {
         ConvertableTo.fromLong$mcV$sp$(this, n);
      }

      public boolean fromFloat$mcZ$sp(final float n) {
         return ConvertableTo.fromFloat$mcZ$sp$(this, n);
      }

      public byte fromFloat$mcB$sp(final float n) {
         return ConvertableTo.fromFloat$mcB$sp$(this, n);
      }

      public char fromFloat$mcC$sp(final float n) {
         return ConvertableTo.fromFloat$mcC$sp$(this, n);
      }

      public double fromFloat$mcD$sp(final float n) {
         return ConvertableTo.fromFloat$mcD$sp$(this, n);
      }

      public float fromFloat$mcF$sp(final float n) {
         return ConvertableTo.fromFloat$mcF$sp$(this, n);
      }

      public int fromFloat$mcI$sp(final float n) {
         return ConvertableTo.fromFloat$mcI$sp$(this, n);
      }

      public long fromFloat$mcJ$sp(final float n) {
         return ConvertableTo.fromFloat$mcJ$sp$(this, n);
      }

      public short fromFloat$mcS$sp(final float n) {
         return ConvertableTo.fromFloat$mcS$sp$(this, n);
      }

      public void fromFloat$mcV$sp(final float n) {
         ConvertableTo.fromFloat$mcV$sp$(this, n);
      }

      public boolean fromDouble$mcZ$sp(final double n) {
         return ConvertableTo.fromDouble$mcZ$sp$(this, n);
      }

      public byte fromDouble$mcB$sp(final double n) {
         return ConvertableTo.fromDouble$mcB$sp$(this, n);
      }

      public char fromDouble$mcC$sp(final double n) {
         return ConvertableTo.fromDouble$mcC$sp$(this, n);
      }

      public double fromDouble$mcD$sp(final double n) {
         return ConvertableTo.fromDouble$mcD$sp$(this, n);
      }

      public float fromDouble$mcF$sp(final double n) {
         return ConvertableTo.fromDouble$mcF$sp$(this, n);
      }

      public int fromDouble$mcI$sp(final double n) {
         return ConvertableTo.fromDouble$mcI$sp$(this, n);
      }

      public long fromDouble$mcJ$sp(final double n) {
         return ConvertableTo.fromDouble$mcJ$sp$(this, n);
      }

      public short fromDouble$mcS$sp(final double n) {
         return ConvertableTo.fromDouble$mcS$sp$(this, n);
      }

      public void fromDouble$mcV$sp(final double n) {
         ConvertableTo.fromDouble$mcV$sp$(this, n);
      }

      public boolean fromBigInt$mcZ$sp(final BigInt n) {
         return ConvertableTo.fromBigInt$mcZ$sp$(this, n);
      }

      public byte fromBigInt$mcB$sp(final BigInt n) {
         return ConvertableTo.fromBigInt$mcB$sp$(this, n);
      }

      public char fromBigInt$mcC$sp(final BigInt n) {
         return ConvertableTo.fromBigInt$mcC$sp$(this, n);
      }

      public double fromBigInt$mcD$sp(final BigInt n) {
         return ConvertableTo.fromBigInt$mcD$sp$(this, n);
      }

      public float fromBigInt$mcF$sp(final BigInt n) {
         return ConvertableTo.fromBigInt$mcF$sp$(this, n);
      }

      public int fromBigInt$mcI$sp(final BigInt n) {
         return ConvertableTo.fromBigInt$mcI$sp$(this, n);
      }

      public long fromBigInt$mcJ$sp(final BigInt n) {
         return ConvertableTo.fromBigInt$mcJ$sp$(this, n);
      }

      public short fromBigInt$mcS$sp(final BigInt n) {
         return ConvertableTo.fromBigInt$mcS$sp$(this, n);
      }

      public void fromBigInt$mcV$sp(final BigInt n) {
         ConvertableTo.fromBigInt$mcV$sp$(this, n);
      }

      public boolean fromBigDecimal$mcZ$sp(final BigDecimal n) {
         return ConvertableTo.fromBigDecimal$mcZ$sp$(this, n);
      }

      public byte fromBigDecimal$mcB$sp(final BigDecimal n) {
         return ConvertableTo.fromBigDecimal$mcB$sp$(this, n);
      }

      public char fromBigDecimal$mcC$sp(final BigDecimal n) {
         return ConvertableTo.fromBigDecimal$mcC$sp$(this, n);
      }

      public double fromBigDecimal$mcD$sp(final BigDecimal n) {
         return ConvertableTo.fromBigDecimal$mcD$sp$(this, n);
      }

      public float fromBigDecimal$mcF$sp(final BigDecimal n) {
         return ConvertableTo.fromBigDecimal$mcF$sp$(this, n);
      }

      public int fromBigDecimal$mcI$sp(final BigDecimal n) {
         return ConvertableTo.fromBigDecimal$mcI$sp$(this, n);
      }

      public long fromBigDecimal$mcJ$sp(final BigDecimal n) {
         return ConvertableTo.fromBigDecimal$mcJ$sp$(this, n);
      }

      public short fromBigDecimal$mcS$sp(final BigDecimal n) {
         return ConvertableTo.fromBigDecimal$mcS$sp$(this, n);
      }

      public void fromBigDecimal$mcV$sp(final BigDecimal n) {
         ConvertableTo.fromBigDecimal$mcV$sp$(this, n);
      }

      public boolean fromRational$mcZ$sp(final Rational n) {
         return ConvertableTo.fromRational$mcZ$sp$(this, n);
      }

      public byte fromRational$mcB$sp(final Rational n) {
         return ConvertableTo.fromRational$mcB$sp$(this, n);
      }

      public char fromRational$mcC$sp(final Rational n) {
         return ConvertableTo.fromRational$mcC$sp$(this, n);
      }

      public double fromRational$mcD$sp(final Rational n) {
         return ConvertableTo.fromRational$mcD$sp$(this, n);
      }

      public float fromRational$mcF$sp(final Rational n) {
         return ConvertableTo.fromRational$mcF$sp$(this, n);
      }

      public int fromRational$mcI$sp(final Rational n) {
         return ConvertableTo.fromRational$mcI$sp$(this, n);
      }

      public long fromRational$mcJ$sp(final Rational n) {
         return ConvertableTo.fromRational$mcJ$sp$(this, n);
      }

      public short fromRational$mcS$sp(final Rational n) {
         return ConvertableTo.fromRational$mcS$sp$(this, n);
      }

      public void fromRational$mcV$sp(final Rational n) {
         ConvertableTo.fromRational$mcV$sp$(this, n);
      }

      public boolean fromAlgebraic$mcZ$sp(final Algebraic n) {
         return ConvertableTo.fromAlgebraic$mcZ$sp$(this, n);
      }

      public byte fromAlgebraic$mcB$sp(final Algebraic n) {
         return ConvertableTo.fromAlgebraic$mcB$sp$(this, n);
      }

      public char fromAlgebraic$mcC$sp(final Algebraic n) {
         return ConvertableTo.fromAlgebraic$mcC$sp$(this, n);
      }

      public double fromAlgebraic$mcD$sp(final Algebraic n) {
         return ConvertableTo.fromAlgebraic$mcD$sp$(this, n);
      }

      public float fromAlgebraic$mcF$sp(final Algebraic n) {
         return ConvertableTo.fromAlgebraic$mcF$sp$(this, n);
      }

      public int fromAlgebraic$mcI$sp(final Algebraic n) {
         return ConvertableTo.fromAlgebraic$mcI$sp$(this, n);
      }

      public long fromAlgebraic$mcJ$sp(final Algebraic n) {
         return ConvertableTo.fromAlgebraic$mcJ$sp$(this, n);
      }

      public short fromAlgebraic$mcS$sp(final Algebraic n) {
         return ConvertableTo.fromAlgebraic$mcS$sp$(this, n);
      }

      public void fromAlgebraic$mcV$sp(final Algebraic n) {
         ConvertableTo.fromAlgebraic$mcV$sp$(this, n);
      }

      public boolean fromReal$mcZ$sp(final Real n) {
         return ConvertableTo.fromReal$mcZ$sp$(this, n);
      }

      public byte fromReal$mcB$sp(final Real n) {
         return ConvertableTo.fromReal$mcB$sp$(this, n);
      }

      public char fromReal$mcC$sp(final Real n) {
         return ConvertableTo.fromReal$mcC$sp$(this, n);
      }

      public double fromReal$mcD$sp(final Real n) {
         return ConvertableTo.fromReal$mcD$sp$(this, n);
      }

      public float fromReal$mcF$sp(final Real n) {
         return ConvertableTo.fromReal$mcF$sp$(this, n);
      }

      public int fromReal$mcI$sp(final Real n) {
         return ConvertableTo.fromReal$mcI$sp$(this, n);
      }

      public long fromReal$mcJ$sp(final Real n) {
         return ConvertableTo.fromReal$mcJ$sp$(this, n);
      }

      public short fromReal$mcS$sp(final Real n) {
         return ConvertableTo.fromReal$mcS$sp$(this, n);
      }

      public void fromReal$mcV$sp(final Real n) {
         ConvertableTo.fromReal$mcV$sp$(this, n);
      }

      public boolean fromType$mcZ$sp(final Object b, final ConvertableFrom evidence$1) {
         return ConvertableTo.fromType$mcZ$sp$(this, b, evidence$1);
      }

      public byte fromType$mcB$sp(final Object b, final ConvertableFrom evidence$1) {
         return ConvertableTo.fromType$mcB$sp$(this, b, evidence$1);
      }

      public char fromType$mcC$sp(final Object b, final ConvertableFrom evidence$1) {
         return ConvertableTo.fromType$mcC$sp$(this, b, evidence$1);
      }

      public double fromType$mcD$sp(final Object b, final ConvertableFrom evidence$1) {
         return ConvertableTo.fromType$mcD$sp$(this, b, evidence$1);
      }

      public float fromType$mcF$sp(final Object b, final ConvertableFrom evidence$1) {
         return ConvertableTo.fromType$mcF$sp$(this, b, evidence$1);
      }

      public int fromType$mcI$sp(final Object b, final ConvertableFrom evidence$1) {
         return ConvertableTo.fromType$mcI$sp$(this, b, evidence$1);
      }

      public long fromType$mcJ$sp(final Object b, final ConvertableFrom evidence$1) {
         return ConvertableTo.fromType$mcJ$sp$(this, b, evidence$1);
      }

      public short fromType$mcS$sp(final Object b, final ConvertableFrom evidence$1) {
         return ConvertableTo.fromType$mcS$sp$(this, b, evidence$1);
      }

      public void fromType$mcV$sp(final Object b, final ConvertableFrom evidence$1) {
         ConvertableTo.fromType$mcV$sp$(this, b, evidence$1);
      }

      public {
         spire.math.ConvertableToAlgebraic.$init$(this);
      }
   };
   private static final ConvertableTo ConvertableToSafeLong = new ConvertableToSafeLong() {
      public SafeLong fromByte(final byte a) {
         return spire.math.ConvertableToSafeLong.fromByte$(this, a);
      }

      public SafeLong fromShort(final short a) {
         return spire.math.ConvertableToSafeLong.fromShort$(this, a);
      }

      public SafeLong fromInt(final int a) {
         return spire.math.ConvertableToSafeLong.fromInt$(this, a);
      }

      public SafeLong fromLong(final long a) {
         return spire.math.ConvertableToSafeLong.fromLong$(this, a);
      }

      public SafeLong fromFloat(final float a) {
         return spire.math.ConvertableToSafeLong.fromFloat$(this, a);
      }

      public SafeLong fromDouble(final double a) {
         return spire.math.ConvertableToSafeLong.fromDouble$(this, a);
      }

      public SafeLong fromBigInt(final BigInt a) {
         return spire.math.ConvertableToSafeLong.fromBigInt$(this, a);
      }

      public SafeLong fromBigDecimal(final BigDecimal a) {
         return spire.math.ConvertableToSafeLong.fromBigDecimal$(this, a);
      }

      public SafeLong fromRational(final Rational a) {
         return spire.math.ConvertableToSafeLong.fromRational$(this, a);
      }

      public SafeLong fromAlgebraic(final Algebraic a) {
         return spire.math.ConvertableToSafeLong.fromAlgebraic$(this, a);
      }

      public SafeLong fromReal(final Real a) {
         return spire.math.ConvertableToSafeLong.fromReal$(this, a);
      }

      public SafeLong fromType(final Object b, final ConvertableFrom evidence$13) {
         return spire.math.ConvertableToSafeLong.fromType$(this, b, evidence$13);
      }

      public boolean fromByte$mcZ$sp(final byte n) {
         return ConvertableTo.fromByte$mcZ$sp$(this, n);
      }

      public byte fromByte$mcB$sp(final byte n) {
         return ConvertableTo.fromByte$mcB$sp$(this, n);
      }

      public char fromByte$mcC$sp(final byte n) {
         return ConvertableTo.fromByte$mcC$sp$(this, n);
      }

      public double fromByte$mcD$sp(final byte n) {
         return ConvertableTo.fromByte$mcD$sp$(this, n);
      }

      public float fromByte$mcF$sp(final byte n) {
         return ConvertableTo.fromByte$mcF$sp$(this, n);
      }

      public int fromByte$mcI$sp(final byte n) {
         return ConvertableTo.fromByte$mcI$sp$(this, n);
      }

      public long fromByte$mcJ$sp(final byte n) {
         return ConvertableTo.fromByte$mcJ$sp$(this, n);
      }

      public short fromByte$mcS$sp(final byte n) {
         return ConvertableTo.fromByte$mcS$sp$(this, n);
      }

      public void fromByte$mcV$sp(final byte n) {
         ConvertableTo.fromByte$mcV$sp$(this, n);
      }

      public boolean fromShort$mcZ$sp(final short n) {
         return ConvertableTo.fromShort$mcZ$sp$(this, n);
      }

      public byte fromShort$mcB$sp(final short n) {
         return ConvertableTo.fromShort$mcB$sp$(this, n);
      }

      public char fromShort$mcC$sp(final short n) {
         return ConvertableTo.fromShort$mcC$sp$(this, n);
      }

      public double fromShort$mcD$sp(final short n) {
         return ConvertableTo.fromShort$mcD$sp$(this, n);
      }

      public float fromShort$mcF$sp(final short n) {
         return ConvertableTo.fromShort$mcF$sp$(this, n);
      }

      public int fromShort$mcI$sp(final short n) {
         return ConvertableTo.fromShort$mcI$sp$(this, n);
      }

      public long fromShort$mcJ$sp(final short n) {
         return ConvertableTo.fromShort$mcJ$sp$(this, n);
      }

      public short fromShort$mcS$sp(final short n) {
         return ConvertableTo.fromShort$mcS$sp$(this, n);
      }

      public void fromShort$mcV$sp(final short n) {
         ConvertableTo.fromShort$mcV$sp$(this, n);
      }

      public boolean fromInt$mcZ$sp(final int n) {
         return ConvertableTo.fromInt$mcZ$sp$(this, n);
      }

      public byte fromInt$mcB$sp(final int n) {
         return ConvertableTo.fromInt$mcB$sp$(this, n);
      }

      public char fromInt$mcC$sp(final int n) {
         return ConvertableTo.fromInt$mcC$sp$(this, n);
      }

      public double fromInt$mcD$sp(final int n) {
         return ConvertableTo.fromInt$mcD$sp$(this, n);
      }

      public float fromInt$mcF$sp(final int n) {
         return ConvertableTo.fromInt$mcF$sp$(this, n);
      }

      public int fromInt$mcI$sp(final int n) {
         return ConvertableTo.fromInt$mcI$sp$(this, n);
      }

      public long fromInt$mcJ$sp(final int n) {
         return ConvertableTo.fromInt$mcJ$sp$(this, n);
      }

      public short fromInt$mcS$sp(final int n) {
         return ConvertableTo.fromInt$mcS$sp$(this, n);
      }

      public void fromInt$mcV$sp(final int n) {
         ConvertableTo.fromInt$mcV$sp$(this, n);
      }

      public boolean fromLong$mcZ$sp(final long n) {
         return ConvertableTo.fromLong$mcZ$sp$(this, n);
      }

      public byte fromLong$mcB$sp(final long n) {
         return ConvertableTo.fromLong$mcB$sp$(this, n);
      }

      public char fromLong$mcC$sp(final long n) {
         return ConvertableTo.fromLong$mcC$sp$(this, n);
      }

      public double fromLong$mcD$sp(final long n) {
         return ConvertableTo.fromLong$mcD$sp$(this, n);
      }

      public float fromLong$mcF$sp(final long n) {
         return ConvertableTo.fromLong$mcF$sp$(this, n);
      }

      public int fromLong$mcI$sp(final long n) {
         return ConvertableTo.fromLong$mcI$sp$(this, n);
      }

      public long fromLong$mcJ$sp(final long n) {
         return ConvertableTo.fromLong$mcJ$sp$(this, n);
      }

      public short fromLong$mcS$sp(final long n) {
         return ConvertableTo.fromLong$mcS$sp$(this, n);
      }

      public void fromLong$mcV$sp(final long n) {
         ConvertableTo.fromLong$mcV$sp$(this, n);
      }

      public boolean fromFloat$mcZ$sp(final float n) {
         return ConvertableTo.fromFloat$mcZ$sp$(this, n);
      }

      public byte fromFloat$mcB$sp(final float n) {
         return ConvertableTo.fromFloat$mcB$sp$(this, n);
      }

      public char fromFloat$mcC$sp(final float n) {
         return ConvertableTo.fromFloat$mcC$sp$(this, n);
      }

      public double fromFloat$mcD$sp(final float n) {
         return ConvertableTo.fromFloat$mcD$sp$(this, n);
      }

      public float fromFloat$mcF$sp(final float n) {
         return ConvertableTo.fromFloat$mcF$sp$(this, n);
      }

      public int fromFloat$mcI$sp(final float n) {
         return ConvertableTo.fromFloat$mcI$sp$(this, n);
      }

      public long fromFloat$mcJ$sp(final float n) {
         return ConvertableTo.fromFloat$mcJ$sp$(this, n);
      }

      public short fromFloat$mcS$sp(final float n) {
         return ConvertableTo.fromFloat$mcS$sp$(this, n);
      }

      public void fromFloat$mcV$sp(final float n) {
         ConvertableTo.fromFloat$mcV$sp$(this, n);
      }

      public boolean fromDouble$mcZ$sp(final double n) {
         return ConvertableTo.fromDouble$mcZ$sp$(this, n);
      }

      public byte fromDouble$mcB$sp(final double n) {
         return ConvertableTo.fromDouble$mcB$sp$(this, n);
      }

      public char fromDouble$mcC$sp(final double n) {
         return ConvertableTo.fromDouble$mcC$sp$(this, n);
      }

      public double fromDouble$mcD$sp(final double n) {
         return ConvertableTo.fromDouble$mcD$sp$(this, n);
      }

      public float fromDouble$mcF$sp(final double n) {
         return ConvertableTo.fromDouble$mcF$sp$(this, n);
      }

      public int fromDouble$mcI$sp(final double n) {
         return ConvertableTo.fromDouble$mcI$sp$(this, n);
      }

      public long fromDouble$mcJ$sp(final double n) {
         return ConvertableTo.fromDouble$mcJ$sp$(this, n);
      }

      public short fromDouble$mcS$sp(final double n) {
         return ConvertableTo.fromDouble$mcS$sp$(this, n);
      }

      public void fromDouble$mcV$sp(final double n) {
         ConvertableTo.fromDouble$mcV$sp$(this, n);
      }

      public boolean fromBigInt$mcZ$sp(final BigInt n) {
         return ConvertableTo.fromBigInt$mcZ$sp$(this, n);
      }

      public byte fromBigInt$mcB$sp(final BigInt n) {
         return ConvertableTo.fromBigInt$mcB$sp$(this, n);
      }

      public char fromBigInt$mcC$sp(final BigInt n) {
         return ConvertableTo.fromBigInt$mcC$sp$(this, n);
      }

      public double fromBigInt$mcD$sp(final BigInt n) {
         return ConvertableTo.fromBigInt$mcD$sp$(this, n);
      }

      public float fromBigInt$mcF$sp(final BigInt n) {
         return ConvertableTo.fromBigInt$mcF$sp$(this, n);
      }

      public int fromBigInt$mcI$sp(final BigInt n) {
         return ConvertableTo.fromBigInt$mcI$sp$(this, n);
      }

      public long fromBigInt$mcJ$sp(final BigInt n) {
         return ConvertableTo.fromBigInt$mcJ$sp$(this, n);
      }

      public short fromBigInt$mcS$sp(final BigInt n) {
         return ConvertableTo.fromBigInt$mcS$sp$(this, n);
      }

      public void fromBigInt$mcV$sp(final BigInt n) {
         ConvertableTo.fromBigInt$mcV$sp$(this, n);
      }

      public boolean fromBigDecimal$mcZ$sp(final BigDecimal n) {
         return ConvertableTo.fromBigDecimal$mcZ$sp$(this, n);
      }

      public byte fromBigDecimal$mcB$sp(final BigDecimal n) {
         return ConvertableTo.fromBigDecimal$mcB$sp$(this, n);
      }

      public char fromBigDecimal$mcC$sp(final BigDecimal n) {
         return ConvertableTo.fromBigDecimal$mcC$sp$(this, n);
      }

      public double fromBigDecimal$mcD$sp(final BigDecimal n) {
         return ConvertableTo.fromBigDecimal$mcD$sp$(this, n);
      }

      public float fromBigDecimal$mcF$sp(final BigDecimal n) {
         return ConvertableTo.fromBigDecimal$mcF$sp$(this, n);
      }

      public int fromBigDecimal$mcI$sp(final BigDecimal n) {
         return ConvertableTo.fromBigDecimal$mcI$sp$(this, n);
      }

      public long fromBigDecimal$mcJ$sp(final BigDecimal n) {
         return ConvertableTo.fromBigDecimal$mcJ$sp$(this, n);
      }

      public short fromBigDecimal$mcS$sp(final BigDecimal n) {
         return ConvertableTo.fromBigDecimal$mcS$sp$(this, n);
      }

      public void fromBigDecimal$mcV$sp(final BigDecimal n) {
         ConvertableTo.fromBigDecimal$mcV$sp$(this, n);
      }

      public boolean fromRational$mcZ$sp(final Rational n) {
         return ConvertableTo.fromRational$mcZ$sp$(this, n);
      }

      public byte fromRational$mcB$sp(final Rational n) {
         return ConvertableTo.fromRational$mcB$sp$(this, n);
      }

      public char fromRational$mcC$sp(final Rational n) {
         return ConvertableTo.fromRational$mcC$sp$(this, n);
      }

      public double fromRational$mcD$sp(final Rational n) {
         return ConvertableTo.fromRational$mcD$sp$(this, n);
      }

      public float fromRational$mcF$sp(final Rational n) {
         return ConvertableTo.fromRational$mcF$sp$(this, n);
      }

      public int fromRational$mcI$sp(final Rational n) {
         return ConvertableTo.fromRational$mcI$sp$(this, n);
      }

      public long fromRational$mcJ$sp(final Rational n) {
         return ConvertableTo.fromRational$mcJ$sp$(this, n);
      }

      public short fromRational$mcS$sp(final Rational n) {
         return ConvertableTo.fromRational$mcS$sp$(this, n);
      }

      public void fromRational$mcV$sp(final Rational n) {
         ConvertableTo.fromRational$mcV$sp$(this, n);
      }

      public boolean fromAlgebraic$mcZ$sp(final Algebraic n) {
         return ConvertableTo.fromAlgebraic$mcZ$sp$(this, n);
      }

      public byte fromAlgebraic$mcB$sp(final Algebraic n) {
         return ConvertableTo.fromAlgebraic$mcB$sp$(this, n);
      }

      public char fromAlgebraic$mcC$sp(final Algebraic n) {
         return ConvertableTo.fromAlgebraic$mcC$sp$(this, n);
      }

      public double fromAlgebraic$mcD$sp(final Algebraic n) {
         return ConvertableTo.fromAlgebraic$mcD$sp$(this, n);
      }

      public float fromAlgebraic$mcF$sp(final Algebraic n) {
         return ConvertableTo.fromAlgebraic$mcF$sp$(this, n);
      }

      public int fromAlgebraic$mcI$sp(final Algebraic n) {
         return ConvertableTo.fromAlgebraic$mcI$sp$(this, n);
      }

      public long fromAlgebraic$mcJ$sp(final Algebraic n) {
         return ConvertableTo.fromAlgebraic$mcJ$sp$(this, n);
      }

      public short fromAlgebraic$mcS$sp(final Algebraic n) {
         return ConvertableTo.fromAlgebraic$mcS$sp$(this, n);
      }

      public void fromAlgebraic$mcV$sp(final Algebraic n) {
         ConvertableTo.fromAlgebraic$mcV$sp$(this, n);
      }

      public boolean fromReal$mcZ$sp(final Real n) {
         return ConvertableTo.fromReal$mcZ$sp$(this, n);
      }

      public byte fromReal$mcB$sp(final Real n) {
         return ConvertableTo.fromReal$mcB$sp$(this, n);
      }

      public char fromReal$mcC$sp(final Real n) {
         return ConvertableTo.fromReal$mcC$sp$(this, n);
      }

      public double fromReal$mcD$sp(final Real n) {
         return ConvertableTo.fromReal$mcD$sp$(this, n);
      }

      public float fromReal$mcF$sp(final Real n) {
         return ConvertableTo.fromReal$mcF$sp$(this, n);
      }

      public int fromReal$mcI$sp(final Real n) {
         return ConvertableTo.fromReal$mcI$sp$(this, n);
      }

      public long fromReal$mcJ$sp(final Real n) {
         return ConvertableTo.fromReal$mcJ$sp$(this, n);
      }

      public short fromReal$mcS$sp(final Real n) {
         return ConvertableTo.fromReal$mcS$sp$(this, n);
      }

      public void fromReal$mcV$sp(final Real n) {
         ConvertableTo.fromReal$mcV$sp$(this, n);
      }

      public boolean fromType$mcZ$sp(final Object b, final ConvertableFrom evidence$1) {
         return ConvertableTo.fromType$mcZ$sp$(this, b, evidence$1);
      }

      public byte fromType$mcB$sp(final Object b, final ConvertableFrom evidence$1) {
         return ConvertableTo.fromType$mcB$sp$(this, b, evidence$1);
      }

      public char fromType$mcC$sp(final Object b, final ConvertableFrom evidence$1) {
         return ConvertableTo.fromType$mcC$sp$(this, b, evidence$1);
      }

      public double fromType$mcD$sp(final Object b, final ConvertableFrom evidence$1) {
         return ConvertableTo.fromType$mcD$sp$(this, b, evidence$1);
      }

      public float fromType$mcF$sp(final Object b, final ConvertableFrom evidence$1) {
         return ConvertableTo.fromType$mcF$sp$(this, b, evidence$1);
      }

      public int fromType$mcI$sp(final Object b, final ConvertableFrom evidence$1) {
         return ConvertableTo.fromType$mcI$sp$(this, b, evidence$1);
      }

      public long fromType$mcJ$sp(final Object b, final ConvertableFrom evidence$1) {
         return ConvertableTo.fromType$mcJ$sp$(this, b, evidence$1);
      }

      public short fromType$mcS$sp(final Object b, final ConvertableFrom evidence$1) {
         return ConvertableTo.fromType$mcS$sp$(this, b, evidence$1);
      }

      public void fromType$mcV$sp(final Object b, final ConvertableFrom evidence$1) {
         ConvertableTo.fromType$mcV$sp$(this, b, evidence$1);
      }

      public {
         spire.math.ConvertableToSafeLong.$init$(this);
      }
   };
   private static final ConvertableTo ConvertableToNumber = new ConvertableToNumber() {
      public Number fromByte(final byte a) {
         return spire.math.ConvertableToNumber.fromByte$(this, a);
      }

      public Number fromShort(final short a) {
         return spire.math.ConvertableToNumber.fromShort$(this, a);
      }

      public Number fromInt(final int a) {
         return spire.math.ConvertableToNumber.fromInt$(this, a);
      }

      public Number fromLong(final long a) {
         return spire.math.ConvertableToNumber.fromLong$(this, a);
      }

      public Number fromFloat(final float a) {
         return spire.math.ConvertableToNumber.fromFloat$(this, a);
      }

      public Number fromDouble(final double a) {
         return spire.math.ConvertableToNumber.fromDouble$(this, a);
      }

      public Number fromBigInt(final BigInt a) {
         return spire.math.ConvertableToNumber.fromBigInt$(this, a);
      }

      public Number fromBigDecimal(final BigDecimal a) {
         return spire.math.ConvertableToNumber.fromBigDecimal$(this, a);
      }

      public Number fromRational(final Rational a) {
         return spire.math.ConvertableToNumber.fromRational$(this, a);
      }

      public Number fromAlgebraic(final Algebraic a) {
         return spire.math.ConvertableToNumber.fromAlgebraic$(this, a);
      }

      public Number fromReal(final Real a) {
         return spire.math.ConvertableToNumber.fromReal$(this, a);
      }

      public Number fromType(final Object b, final ConvertableFrom evidence$14) {
         return spire.math.ConvertableToNumber.fromType$(this, b, evidence$14);
      }

      public boolean fromByte$mcZ$sp(final byte n) {
         return ConvertableTo.fromByte$mcZ$sp$(this, n);
      }

      public byte fromByte$mcB$sp(final byte n) {
         return ConvertableTo.fromByte$mcB$sp$(this, n);
      }

      public char fromByte$mcC$sp(final byte n) {
         return ConvertableTo.fromByte$mcC$sp$(this, n);
      }

      public double fromByte$mcD$sp(final byte n) {
         return ConvertableTo.fromByte$mcD$sp$(this, n);
      }

      public float fromByte$mcF$sp(final byte n) {
         return ConvertableTo.fromByte$mcF$sp$(this, n);
      }

      public int fromByte$mcI$sp(final byte n) {
         return ConvertableTo.fromByte$mcI$sp$(this, n);
      }

      public long fromByte$mcJ$sp(final byte n) {
         return ConvertableTo.fromByte$mcJ$sp$(this, n);
      }

      public short fromByte$mcS$sp(final byte n) {
         return ConvertableTo.fromByte$mcS$sp$(this, n);
      }

      public void fromByte$mcV$sp(final byte n) {
         ConvertableTo.fromByte$mcV$sp$(this, n);
      }

      public boolean fromShort$mcZ$sp(final short n) {
         return ConvertableTo.fromShort$mcZ$sp$(this, n);
      }

      public byte fromShort$mcB$sp(final short n) {
         return ConvertableTo.fromShort$mcB$sp$(this, n);
      }

      public char fromShort$mcC$sp(final short n) {
         return ConvertableTo.fromShort$mcC$sp$(this, n);
      }

      public double fromShort$mcD$sp(final short n) {
         return ConvertableTo.fromShort$mcD$sp$(this, n);
      }

      public float fromShort$mcF$sp(final short n) {
         return ConvertableTo.fromShort$mcF$sp$(this, n);
      }

      public int fromShort$mcI$sp(final short n) {
         return ConvertableTo.fromShort$mcI$sp$(this, n);
      }

      public long fromShort$mcJ$sp(final short n) {
         return ConvertableTo.fromShort$mcJ$sp$(this, n);
      }

      public short fromShort$mcS$sp(final short n) {
         return ConvertableTo.fromShort$mcS$sp$(this, n);
      }

      public void fromShort$mcV$sp(final short n) {
         ConvertableTo.fromShort$mcV$sp$(this, n);
      }

      public boolean fromInt$mcZ$sp(final int n) {
         return ConvertableTo.fromInt$mcZ$sp$(this, n);
      }

      public byte fromInt$mcB$sp(final int n) {
         return ConvertableTo.fromInt$mcB$sp$(this, n);
      }

      public char fromInt$mcC$sp(final int n) {
         return ConvertableTo.fromInt$mcC$sp$(this, n);
      }

      public double fromInt$mcD$sp(final int n) {
         return ConvertableTo.fromInt$mcD$sp$(this, n);
      }

      public float fromInt$mcF$sp(final int n) {
         return ConvertableTo.fromInt$mcF$sp$(this, n);
      }

      public int fromInt$mcI$sp(final int n) {
         return ConvertableTo.fromInt$mcI$sp$(this, n);
      }

      public long fromInt$mcJ$sp(final int n) {
         return ConvertableTo.fromInt$mcJ$sp$(this, n);
      }

      public short fromInt$mcS$sp(final int n) {
         return ConvertableTo.fromInt$mcS$sp$(this, n);
      }

      public void fromInt$mcV$sp(final int n) {
         ConvertableTo.fromInt$mcV$sp$(this, n);
      }

      public boolean fromLong$mcZ$sp(final long n) {
         return ConvertableTo.fromLong$mcZ$sp$(this, n);
      }

      public byte fromLong$mcB$sp(final long n) {
         return ConvertableTo.fromLong$mcB$sp$(this, n);
      }

      public char fromLong$mcC$sp(final long n) {
         return ConvertableTo.fromLong$mcC$sp$(this, n);
      }

      public double fromLong$mcD$sp(final long n) {
         return ConvertableTo.fromLong$mcD$sp$(this, n);
      }

      public float fromLong$mcF$sp(final long n) {
         return ConvertableTo.fromLong$mcF$sp$(this, n);
      }

      public int fromLong$mcI$sp(final long n) {
         return ConvertableTo.fromLong$mcI$sp$(this, n);
      }

      public long fromLong$mcJ$sp(final long n) {
         return ConvertableTo.fromLong$mcJ$sp$(this, n);
      }

      public short fromLong$mcS$sp(final long n) {
         return ConvertableTo.fromLong$mcS$sp$(this, n);
      }

      public void fromLong$mcV$sp(final long n) {
         ConvertableTo.fromLong$mcV$sp$(this, n);
      }

      public boolean fromFloat$mcZ$sp(final float n) {
         return ConvertableTo.fromFloat$mcZ$sp$(this, n);
      }

      public byte fromFloat$mcB$sp(final float n) {
         return ConvertableTo.fromFloat$mcB$sp$(this, n);
      }

      public char fromFloat$mcC$sp(final float n) {
         return ConvertableTo.fromFloat$mcC$sp$(this, n);
      }

      public double fromFloat$mcD$sp(final float n) {
         return ConvertableTo.fromFloat$mcD$sp$(this, n);
      }

      public float fromFloat$mcF$sp(final float n) {
         return ConvertableTo.fromFloat$mcF$sp$(this, n);
      }

      public int fromFloat$mcI$sp(final float n) {
         return ConvertableTo.fromFloat$mcI$sp$(this, n);
      }

      public long fromFloat$mcJ$sp(final float n) {
         return ConvertableTo.fromFloat$mcJ$sp$(this, n);
      }

      public short fromFloat$mcS$sp(final float n) {
         return ConvertableTo.fromFloat$mcS$sp$(this, n);
      }

      public void fromFloat$mcV$sp(final float n) {
         ConvertableTo.fromFloat$mcV$sp$(this, n);
      }

      public boolean fromDouble$mcZ$sp(final double n) {
         return ConvertableTo.fromDouble$mcZ$sp$(this, n);
      }

      public byte fromDouble$mcB$sp(final double n) {
         return ConvertableTo.fromDouble$mcB$sp$(this, n);
      }

      public char fromDouble$mcC$sp(final double n) {
         return ConvertableTo.fromDouble$mcC$sp$(this, n);
      }

      public double fromDouble$mcD$sp(final double n) {
         return ConvertableTo.fromDouble$mcD$sp$(this, n);
      }

      public float fromDouble$mcF$sp(final double n) {
         return ConvertableTo.fromDouble$mcF$sp$(this, n);
      }

      public int fromDouble$mcI$sp(final double n) {
         return ConvertableTo.fromDouble$mcI$sp$(this, n);
      }

      public long fromDouble$mcJ$sp(final double n) {
         return ConvertableTo.fromDouble$mcJ$sp$(this, n);
      }

      public short fromDouble$mcS$sp(final double n) {
         return ConvertableTo.fromDouble$mcS$sp$(this, n);
      }

      public void fromDouble$mcV$sp(final double n) {
         ConvertableTo.fromDouble$mcV$sp$(this, n);
      }

      public boolean fromBigInt$mcZ$sp(final BigInt n) {
         return ConvertableTo.fromBigInt$mcZ$sp$(this, n);
      }

      public byte fromBigInt$mcB$sp(final BigInt n) {
         return ConvertableTo.fromBigInt$mcB$sp$(this, n);
      }

      public char fromBigInt$mcC$sp(final BigInt n) {
         return ConvertableTo.fromBigInt$mcC$sp$(this, n);
      }

      public double fromBigInt$mcD$sp(final BigInt n) {
         return ConvertableTo.fromBigInt$mcD$sp$(this, n);
      }

      public float fromBigInt$mcF$sp(final BigInt n) {
         return ConvertableTo.fromBigInt$mcF$sp$(this, n);
      }

      public int fromBigInt$mcI$sp(final BigInt n) {
         return ConvertableTo.fromBigInt$mcI$sp$(this, n);
      }

      public long fromBigInt$mcJ$sp(final BigInt n) {
         return ConvertableTo.fromBigInt$mcJ$sp$(this, n);
      }

      public short fromBigInt$mcS$sp(final BigInt n) {
         return ConvertableTo.fromBigInt$mcS$sp$(this, n);
      }

      public void fromBigInt$mcV$sp(final BigInt n) {
         ConvertableTo.fromBigInt$mcV$sp$(this, n);
      }

      public boolean fromBigDecimal$mcZ$sp(final BigDecimal n) {
         return ConvertableTo.fromBigDecimal$mcZ$sp$(this, n);
      }

      public byte fromBigDecimal$mcB$sp(final BigDecimal n) {
         return ConvertableTo.fromBigDecimal$mcB$sp$(this, n);
      }

      public char fromBigDecimal$mcC$sp(final BigDecimal n) {
         return ConvertableTo.fromBigDecimal$mcC$sp$(this, n);
      }

      public double fromBigDecimal$mcD$sp(final BigDecimal n) {
         return ConvertableTo.fromBigDecimal$mcD$sp$(this, n);
      }

      public float fromBigDecimal$mcF$sp(final BigDecimal n) {
         return ConvertableTo.fromBigDecimal$mcF$sp$(this, n);
      }

      public int fromBigDecimal$mcI$sp(final BigDecimal n) {
         return ConvertableTo.fromBigDecimal$mcI$sp$(this, n);
      }

      public long fromBigDecimal$mcJ$sp(final BigDecimal n) {
         return ConvertableTo.fromBigDecimal$mcJ$sp$(this, n);
      }

      public short fromBigDecimal$mcS$sp(final BigDecimal n) {
         return ConvertableTo.fromBigDecimal$mcS$sp$(this, n);
      }

      public void fromBigDecimal$mcV$sp(final BigDecimal n) {
         ConvertableTo.fromBigDecimal$mcV$sp$(this, n);
      }

      public boolean fromRational$mcZ$sp(final Rational n) {
         return ConvertableTo.fromRational$mcZ$sp$(this, n);
      }

      public byte fromRational$mcB$sp(final Rational n) {
         return ConvertableTo.fromRational$mcB$sp$(this, n);
      }

      public char fromRational$mcC$sp(final Rational n) {
         return ConvertableTo.fromRational$mcC$sp$(this, n);
      }

      public double fromRational$mcD$sp(final Rational n) {
         return ConvertableTo.fromRational$mcD$sp$(this, n);
      }

      public float fromRational$mcF$sp(final Rational n) {
         return ConvertableTo.fromRational$mcF$sp$(this, n);
      }

      public int fromRational$mcI$sp(final Rational n) {
         return ConvertableTo.fromRational$mcI$sp$(this, n);
      }

      public long fromRational$mcJ$sp(final Rational n) {
         return ConvertableTo.fromRational$mcJ$sp$(this, n);
      }

      public short fromRational$mcS$sp(final Rational n) {
         return ConvertableTo.fromRational$mcS$sp$(this, n);
      }

      public void fromRational$mcV$sp(final Rational n) {
         ConvertableTo.fromRational$mcV$sp$(this, n);
      }

      public boolean fromAlgebraic$mcZ$sp(final Algebraic n) {
         return ConvertableTo.fromAlgebraic$mcZ$sp$(this, n);
      }

      public byte fromAlgebraic$mcB$sp(final Algebraic n) {
         return ConvertableTo.fromAlgebraic$mcB$sp$(this, n);
      }

      public char fromAlgebraic$mcC$sp(final Algebraic n) {
         return ConvertableTo.fromAlgebraic$mcC$sp$(this, n);
      }

      public double fromAlgebraic$mcD$sp(final Algebraic n) {
         return ConvertableTo.fromAlgebraic$mcD$sp$(this, n);
      }

      public float fromAlgebraic$mcF$sp(final Algebraic n) {
         return ConvertableTo.fromAlgebraic$mcF$sp$(this, n);
      }

      public int fromAlgebraic$mcI$sp(final Algebraic n) {
         return ConvertableTo.fromAlgebraic$mcI$sp$(this, n);
      }

      public long fromAlgebraic$mcJ$sp(final Algebraic n) {
         return ConvertableTo.fromAlgebraic$mcJ$sp$(this, n);
      }

      public short fromAlgebraic$mcS$sp(final Algebraic n) {
         return ConvertableTo.fromAlgebraic$mcS$sp$(this, n);
      }

      public void fromAlgebraic$mcV$sp(final Algebraic n) {
         ConvertableTo.fromAlgebraic$mcV$sp$(this, n);
      }

      public boolean fromReal$mcZ$sp(final Real n) {
         return ConvertableTo.fromReal$mcZ$sp$(this, n);
      }

      public byte fromReal$mcB$sp(final Real n) {
         return ConvertableTo.fromReal$mcB$sp$(this, n);
      }

      public char fromReal$mcC$sp(final Real n) {
         return ConvertableTo.fromReal$mcC$sp$(this, n);
      }

      public double fromReal$mcD$sp(final Real n) {
         return ConvertableTo.fromReal$mcD$sp$(this, n);
      }

      public float fromReal$mcF$sp(final Real n) {
         return ConvertableTo.fromReal$mcF$sp$(this, n);
      }

      public int fromReal$mcI$sp(final Real n) {
         return ConvertableTo.fromReal$mcI$sp$(this, n);
      }

      public long fromReal$mcJ$sp(final Real n) {
         return ConvertableTo.fromReal$mcJ$sp$(this, n);
      }

      public short fromReal$mcS$sp(final Real n) {
         return ConvertableTo.fromReal$mcS$sp$(this, n);
      }

      public void fromReal$mcV$sp(final Real n) {
         ConvertableTo.fromReal$mcV$sp$(this, n);
      }

      public boolean fromType$mcZ$sp(final Object b, final ConvertableFrom evidence$1) {
         return ConvertableTo.fromType$mcZ$sp$(this, b, evidence$1);
      }

      public byte fromType$mcB$sp(final Object b, final ConvertableFrom evidence$1) {
         return ConvertableTo.fromType$mcB$sp$(this, b, evidence$1);
      }

      public char fromType$mcC$sp(final Object b, final ConvertableFrom evidence$1) {
         return ConvertableTo.fromType$mcC$sp$(this, b, evidence$1);
      }

      public double fromType$mcD$sp(final Object b, final ConvertableFrom evidence$1) {
         return ConvertableTo.fromType$mcD$sp$(this, b, evidence$1);
      }

      public float fromType$mcF$sp(final Object b, final ConvertableFrom evidence$1) {
         return ConvertableTo.fromType$mcF$sp$(this, b, evidence$1);
      }

      public int fromType$mcI$sp(final Object b, final ConvertableFrom evidence$1) {
         return ConvertableTo.fromType$mcI$sp$(this, b, evidence$1);
      }

      public long fromType$mcJ$sp(final Object b, final ConvertableFrom evidence$1) {
         return ConvertableTo.fromType$mcJ$sp$(this, b, evidence$1);
      }

      public short fromType$mcS$sp(final Object b, final ConvertableFrom evidence$1) {
         return ConvertableTo.fromType$mcS$sp$(this, b, evidence$1);
      }

      public void fromType$mcV$sp(final Object b, final ConvertableFrom evidence$1) {
         ConvertableTo.fromType$mcV$sp$(this, b, evidence$1);
      }

      public {
         spire.math.ConvertableToNumber.$init$(this);
      }
   };
   private static final ConvertableTo ConvertableToNatural = new ConvertableToNatural() {
      public Natural fromByte(final byte a) {
         return spire.math.ConvertableToNatural.fromByte$(this, a);
      }

      public Natural fromShort(final short a) {
         return spire.math.ConvertableToNatural.fromShort$(this, a);
      }

      public Natural fromInt(final int a) {
         return spire.math.ConvertableToNatural.fromInt$(this, a);
      }

      public Natural fromLong(final long a) {
         return spire.math.ConvertableToNatural.fromLong$(this, a);
      }

      public Natural fromFloat(final float a) {
         return spire.math.ConvertableToNatural.fromFloat$(this, a);
      }

      public Natural fromDouble(final double a) {
         return spire.math.ConvertableToNatural.fromDouble$(this, a);
      }

      public Natural fromBigInt(final BigInt a) {
         return spire.math.ConvertableToNatural.fromBigInt$(this, a);
      }

      public Natural fromBigDecimal(final BigDecimal a) {
         return spire.math.ConvertableToNatural.fromBigDecimal$(this, a);
      }

      public Natural fromRational(final Rational a) {
         return spire.math.ConvertableToNatural.fromRational$(this, a);
      }

      public Natural fromAlgebraic(final Algebraic a) {
         return spire.math.ConvertableToNatural.fromAlgebraic$(this, a);
      }

      public Natural fromReal(final Real a) {
         return spire.math.ConvertableToNatural.fromReal$(this, a);
      }

      public Natural fromType(final Object b, final ConvertableFrom evidence$15) {
         return spire.math.ConvertableToNatural.fromType$(this, b, evidence$15);
      }

      public boolean fromByte$mcZ$sp(final byte n) {
         return ConvertableTo.fromByte$mcZ$sp$(this, n);
      }

      public byte fromByte$mcB$sp(final byte n) {
         return ConvertableTo.fromByte$mcB$sp$(this, n);
      }

      public char fromByte$mcC$sp(final byte n) {
         return ConvertableTo.fromByte$mcC$sp$(this, n);
      }

      public double fromByte$mcD$sp(final byte n) {
         return ConvertableTo.fromByte$mcD$sp$(this, n);
      }

      public float fromByte$mcF$sp(final byte n) {
         return ConvertableTo.fromByte$mcF$sp$(this, n);
      }

      public int fromByte$mcI$sp(final byte n) {
         return ConvertableTo.fromByte$mcI$sp$(this, n);
      }

      public long fromByte$mcJ$sp(final byte n) {
         return ConvertableTo.fromByte$mcJ$sp$(this, n);
      }

      public short fromByte$mcS$sp(final byte n) {
         return ConvertableTo.fromByte$mcS$sp$(this, n);
      }

      public void fromByte$mcV$sp(final byte n) {
         ConvertableTo.fromByte$mcV$sp$(this, n);
      }

      public boolean fromShort$mcZ$sp(final short n) {
         return ConvertableTo.fromShort$mcZ$sp$(this, n);
      }

      public byte fromShort$mcB$sp(final short n) {
         return ConvertableTo.fromShort$mcB$sp$(this, n);
      }

      public char fromShort$mcC$sp(final short n) {
         return ConvertableTo.fromShort$mcC$sp$(this, n);
      }

      public double fromShort$mcD$sp(final short n) {
         return ConvertableTo.fromShort$mcD$sp$(this, n);
      }

      public float fromShort$mcF$sp(final short n) {
         return ConvertableTo.fromShort$mcF$sp$(this, n);
      }

      public int fromShort$mcI$sp(final short n) {
         return ConvertableTo.fromShort$mcI$sp$(this, n);
      }

      public long fromShort$mcJ$sp(final short n) {
         return ConvertableTo.fromShort$mcJ$sp$(this, n);
      }

      public short fromShort$mcS$sp(final short n) {
         return ConvertableTo.fromShort$mcS$sp$(this, n);
      }

      public void fromShort$mcV$sp(final short n) {
         ConvertableTo.fromShort$mcV$sp$(this, n);
      }

      public boolean fromInt$mcZ$sp(final int n) {
         return ConvertableTo.fromInt$mcZ$sp$(this, n);
      }

      public byte fromInt$mcB$sp(final int n) {
         return ConvertableTo.fromInt$mcB$sp$(this, n);
      }

      public char fromInt$mcC$sp(final int n) {
         return ConvertableTo.fromInt$mcC$sp$(this, n);
      }

      public double fromInt$mcD$sp(final int n) {
         return ConvertableTo.fromInt$mcD$sp$(this, n);
      }

      public float fromInt$mcF$sp(final int n) {
         return ConvertableTo.fromInt$mcF$sp$(this, n);
      }

      public int fromInt$mcI$sp(final int n) {
         return ConvertableTo.fromInt$mcI$sp$(this, n);
      }

      public long fromInt$mcJ$sp(final int n) {
         return ConvertableTo.fromInt$mcJ$sp$(this, n);
      }

      public short fromInt$mcS$sp(final int n) {
         return ConvertableTo.fromInt$mcS$sp$(this, n);
      }

      public void fromInt$mcV$sp(final int n) {
         ConvertableTo.fromInt$mcV$sp$(this, n);
      }

      public boolean fromLong$mcZ$sp(final long n) {
         return ConvertableTo.fromLong$mcZ$sp$(this, n);
      }

      public byte fromLong$mcB$sp(final long n) {
         return ConvertableTo.fromLong$mcB$sp$(this, n);
      }

      public char fromLong$mcC$sp(final long n) {
         return ConvertableTo.fromLong$mcC$sp$(this, n);
      }

      public double fromLong$mcD$sp(final long n) {
         return ConvertableTo.fromLong$mcD$sp$(this, n);
      }

      public float fromLong$mcF$sp(final long n) {
         return ConvertableTo.fromLong$mcF$sp$(this, n);
      }

      public int fromLong$mcI$sp(final long n) {
         return ConvertableTo.fromLong$mcI$sp$(this, n);
      }

      public long fromLong$mcJ$sp(final long n) {
         return ConvertableTo.fromLong$mcJ$sp$(this, n);
      }

      public short fromLong$mcS$sp(final long n) {
         return ConvertableTo.fromLong$mcS$sp$(this, n);
      }

      public void fromLong$mcV$sp(final long n) {
         ConvertableTo.fromLong$mcV$sp$(this, n);
      }

      public boolean fromFloat$mcZ$sp(final float n) {
         return ConvertableTo.fromFloat$mcZ$sp$(this, n);
      }

      public byte fromFloat$mcB$sp(final float n) {
         return ConvertableTo.fromFloat$mcB$sp$(this, n);
      }

      public char fromFloat$mcC$sp(final float n) {
         return ConvertableTo.fromFloat$mcC$sp$(this, n);
      }

      public double fromFloat$mcD$sp(final float n) {
         return ConvertableTo.fromFloat$mcD$sp$(this, n);
      }

      public float fromFloat$mcF$sp(final float n) {
         return ConvertableTo.fromFloat$mcF$sp$(this, n);
      }

      public int fromFloat$mcI$sp(final float n) {
         return ConvertableTo.fromFloat$mcI$sp$(this, n);
      }

      public long fromFloat$mcJ$sp(final float n) {
         return ConvertableTo.fromFloat$mcJ$sp$(this, n);
      }

      public short fromFloat$mcS$sp(final float n) {
         return ConvertableTo.fromFloat$mcS$sp$(this, n);
      }

      public void fromFloat$mcV$sp(final float n) {
         ConvertableTo.fromFloat$mcV$sp$(this, n);
      }

      public boolean fromDouble$mcZ$sp(final double n) {
         return ConvertableTo.fromDouble$mcZ$sp$(this, n);
      }

      public byte fromDouble$mcB$sp(final double n) {
         return ConvertableTo.fromDouble$mcB$sp$(this, n);
      }

      public char fromDouble$mcC$sp(final double n) {
         return ConvertableTo.fromDouble$mcC$sp$(this, n);
      }

      public double fromDouble$mcD$sp(final double n) {
         return ConvertableTo.fromDouble$mcD$sp$(this, n);
      }

      public float fromDouble$mcF$sp(final double n) {
         return ConvertableTo.fromDouble$mcF$sp$(this, n);
      }

      public int fromDouble$mcI$sp(final double n) {
         return ConvertableTo.fromDouble$mcI$sp$(this, n);
      }

      public long fromDouble$mcJ$sp(final double n) {
         return ConvertableTo.fromDouble$mcJ$sp$(this, n);
      }

      public short fromDouble$mcS$sp(final double n) {
         return ConvertableTo.fromDouble$mcS$sp$(this, n);
      }

      public void fromDouble$mcV$sp(final double n) {
         ConvertableTo.fromDouble$mcV$sp$(this, n);
      }

      public boolean fromBigInt$mcZ$sp(final BigInt n) {
         return ConvertableTo.fromBigInt$mcZ$sp$(this, n);
      }

      public byte fromBigInt$mcB$sp(final BigInt n) {
         return ConvertableTo.fromBigInt$mcB$sp$(this, n);
      }

      public char fromBigInt$mcC$sp(final BigInt n) {
         return ConvertableTo.fromBigInt$mcC$sp$(this, n);
      }

      public double fromBigInt$mcD$sp(final BigInt n) {
         return ConvertableTo.fromBigInt$mcD$sp$(this, n);
      }

      public float fromBigInt$mcF$sp(final BigInt n) {
         return ConvertableTo.fromBigInt$mcF$sp$(this, n);
      }

      public int fromBigInt$mcI$sp(final BigInt n) {
         return ConvertableTo.fromBigInt$mcI$sp$(this, n);
      }

      public long fromBigInt$mcJ$sp(final BigInt n) {
         return ConvertableTo.fromBigInt$mcJ$sp$(this, n);
      }

      public short fromBigInt$mcS$sp(final BigInt n) {
         return ConvertableTo.fromBigInt$mcS$sp$(this, n);
      }

      public void fromBigInt$mcV$sp(final BigInt n) {
         ConvertableTo.fromBigInt$mcV$sp$(this, n);
      }

      public boolean fromBigDecimal$mcZ$sp(final BigDecimal n) {
         return ConvertableTo.fromBigDecimal$mcZ$sp$(this, n);
      }

      public byte fromBigDecimal$mcB$sp(final BigDecimal n) {
         return ConvertableTo.fromBigDecimal$mcB$sp$(this, n);
      }

      public char fromBigDecimal$mcC$sp(final BigDecimal n) {
         return ConvertableTo.fromBigDecimal$mcC$sp$(this, n);
      }

      public double fromBigDecimal$mcD$sp(final BigDecimal n) {
         return ConvertableTo.fromBigDecimal$mcD$sp$(this, n);
      }

      public float fromBigDecimal$mcF$sp(final BigDecimal n) {
         return ConvertableTo.fromBigDecimal$mcF$sp$(this, n);
      }

      public int fromBigDecimal$mcI$sp(final BigDecimal n) {
         return ConvertableTo.fromBigDecimal$mcI$sp$(this, n);
      }

      public long fromBigDecimal$mcJ$sp(final BigDecimal n) {
         return ConvertableTo.fromBigDecimal$mcJ$sp$(this, n);
      }

      public short fromBigDecimal$mcS$sp(final BigDecimal n) {
         return ConvertableTo.fromBigDecimal$mcS$sp$(this, n);
      }

      public void fromBigDecimal$mcV$sp(final BigDecimal n) {
         ConvertableTo.fromBigDecimal$mcV$sp$(this, n);
      }

      public boolean fromRational$mcZ$sp(final Rational n) {
         return ConvertableTo.fromRational$mcZ$sp$(this, n);
      }

      public byte fromRational$mcB$sp(final Rational n) {
         return ConvertableTo.fromRational$mcB$sp$(this, n);
      }

      public char fromRational$mcC$sp(final Rational n) {
         return ConvertableTo.fromRational$mcC$sp$(this, n);
      }

      public double fromRational$mcD$sp(final Rational n) {
         return ConvertableTo.fromRational$mcD$sp$(this, n);
      }

      public float fromRational$mcF$sp(final Rational n) {
         return ConvertableTo.fromRational$mcF$sp$(this, n);
      }

      public int fromRational$mcI$sp(final Rational n) {
         return ConvertableTo.fromRational$mcI$sp$(this, n);
      }

      public long fromRational$mcJ$sp(final Rational n) {
         return ConvertableTo.fromRational$mcJ$sp$(this, n);
      }

      public short fromRational$mcS$sp(final Rational n) {
         return ConvertableTo.fromRational$mcS$sp$(this, n);
      }

      public void fromRational$mcV$sp(final Rational n) {
         ConvertableTo.fromRational$mcV$sp$(this, n);
      }

      public boolean fromAlgebraic$mcZ$sp(final Algebraic n) {
         return ConvertableTo.fromAlgebraic$mcZ$sp$(this, n);
      }

      public byte fromAlgebraic$mcB$sp(final Algebraic n) {
         return ConvertableTo.fromAlgebraic$mcB$sp$(this, n);
      }

      public char fromAlgebraic$mcC$sp(final Algebraic n) {
         return ConvertableTo.fromAlgebraic$mcC$sp$(this, n);
      }

      public double fromAlgebraic$mcD$sp(final Algebraic n) {
         return ConvertableTo.fromAlgebraic$mcD$sp$(this, n);
      }

      public float fromAlgebraic$mcF$sp(final Algebraic n) {
         return ConvertableTo.fromAlgebraic$mcF$sp$(this, n);
      }

      public int fromAlgebraic$mcI$sp(final Algebraic n) {
         return ConvertableTo.fromAlgebraic$mcI$sp$(this, n);
      }

      public long fromAlgebraic$mcJ$sp(final Algebraic n) {
         return ConvertableTo.fromAlgebraic$mcJ$sp$(this, n);
      }

      public short fromAlgebraic$mcS$sp(final Algebraic n) {
         return ConvertableTo.fromAlgebraic$mcS$sp$(this, n);
      }

      public void fromAlgebraic$mcV$sp(final Algebraic n) {
         ConvertableTo.fromAlgebraic$mcV$sp$(this, n);
      }

      public boolean fromReal$mcZ$sp(final Real n) {
         return ConvertableTo.fromReal$mcZ$sp$(this, n);
      }

      public byte fromReal$mcB$sp(final Real n) {
         return ConvertableTo.fromReal$mcB$sp$(this, n);
      }

      public char fromReal$mcC$sp(final Real n) {
         return ConvertableTo.fromReal$mcC$sp$(this, n);
      }

      public double fromReal$mcD$sp(final Real n) {
         return ConvertableTo.fromReal$mcD$sp$(this, n);
      }

      public float fromReal$mcF$sp(final Real n) {
         return ConvertableTo.fromReal$mcF$sp$(this, n);
      }

      public int fromReal$mcI$sp(final Real n) {
         return ConvertableTo.fromReal$mcI$sp$(this, n);
      }

      public long fromReal$mcJ$sp(final Real n) {
         return ConvertableTo.fromReal$mcJ$sp$(this, n);
      }

      public short fromReal$mcS$sp(final Real n) {
         return ConvertableTo.fromReal$mcS$sp$(this, n);
      }

      public void fromReal$mcV$sp(final Real n) {
         ConvertableTo.fromReal$mcV$sp$(this, n);
      }

      public boolean fromType$mcZ$sp(final Object b, final ConvertableFrom evidence$1) {
         return ConvertableTo.fromType$mcZ$sp$(this, b, evidence$1);
      }

      public byte fromType$mcB$sp(final Object b, final ConvertableFrom evidence$1) {
         return ConvertableTo.fromType$mcB$sp$(this, b, evidence$1);
      }

      public char fromType$mcC$sp(final Object b, final ConvertableFrom evidence$1) {
         return ConvertableTo.fromType$mcC$sp$(this, b, evidence$1);
      }

      public double fromType$mcD$sp(final Object b, final ConvertableFrom evidence$1) {
         return ConvertableTo.fromType$mcD$sp$(this, b, evidence$1);
      }

      public float fromType$mcF$sp(final Object b, final ConvertableFrom evidence$1) {
         return ConvertableTo.fromType$mcF$sp$(this, b, evidence$1);
      }

      public int fromType$mcI$sp(final Object b, final ConvertableFrom evidence$1) {
         return ConvertableTo.fromType$mcI$sp$(this, b, evidence$1);
      }

      public long fromType$mcJ$sp(final Object b, final ConvertableFrom evidence$1) {
         return ConvertableTo.fromType$mcJ$sp$(this, b, evidence$1);
      }

      public short fromType$mcS$sp(final Object b, final ConvertableFrom evidence$1) {
         return ConvertableTo.fromType$mcS$sp$(this, b, evidence$1);
      }

      public void fromType$mcV$sp(final Object b, final ConvertableFrom evidence$1) {
         ConvertableTo.fromType$mcV$sp$(this, b, evidence$1);
      }

      public {
         spire.math.ConvertableToNatural.$init$(this);
      }
   };

   public final ConvertableTo apply(final ConvertableTo ev) {
      return ev;
   }

   public final ConvertableTo ConvertableToByte() {
      return ConvertableToByte;
   }

   public final ConvertableTo ConvertableToShort() {
      return ConvertableToShort;
   }

   public final ConvertableTo ConvertableToInt() {
      return ConvertableToInt;
   }

   public final ConvertableTo ConvertableToLong() {
      return ConvertableToLong;
   }

   public final ConvertableTo ConvertableToBigInt() {
      return ConvertableToBigInt;
   }

   public final ConvertableTo ConvertableToFloat() {
      return ConvertableToFloat;
   }

   public final ConvertableTo ConvertableToDouble() {
      return ConvertableToDouble;
   }

   public final ConvertableTo ConvertableToBigDecimal() {
      return ConvertableToBigDecimal;
   }

   public final ConvertableTo ConvertableToRational() {
      return ConvertableToRational;
   }

   public final ConvertableTo ConvertableToAlgebraic() {
      return ConvertableToAlgebraic;
   }

   public final ConvertableTo ConvertableToSafeLong() {
      return ConvertableToSafeLong;
   }

   public final ConvertableTo ConvertableToNumber() {
      return ConvertableToNumber;
   }

   public final ConvertableTo ConvertableToNatural() {
      return ConvertableToNatural;
   }

   public ConvertableToComplex convertableToComplex(final Integral evidence$16) {
      return new ConvertableToComplex(evidence$16) {
         private final Integral algebra;

         public Complex fromByte(final byte a) {
            return ConvertableToComplex.fromByte$(this, a);
         }

         public Complex fromShort(final short a) {
            return ConvertableToComplex.fromShort$(this, a);
         }

         public Complex fromInt(final int a) {
            return ConvertableToComplex.fromInt$(this, a);
         }

         public Complex fromLong(final long a) {
            return ConvertableToComplex.fromLong$(this, a);
         }

         public Complex fromFloat(final float a) {
            return ConvertableToComplex.fromFloat$(this, a);
         }

         public Complex fromDouble(final double a) {
            return ConvertableToComplex.fromDouble$(this, a);
         }

         public Complex fromBigInt(final BigInt a) {
            return ConvertableToComplex.fromBigInt$(this, a);
         }

         public Complex fromBigDecimal(final BigDecimal a) {
            return ConvertableToComplex.fromBigDecimal$(this, a);
         }

         public Complex fromRational(final Rational a) {
            return ConvertableToComplex.fromRational$(this, a);
         }

         public Complex fromAlgebraic(final Algebraic a) {
            return ConvertableToComplex.fromAlgebraic$(this, a);
         }

         public Complex fromReal(final Real a) {
            return ConvertableToComplex.fromReal$(this, a);
         }

         public Complex fromType(final Object b, final ConvertableFrom evidence$12) {
            return ConvertableToComplex.fromType$(this, b, evidence$12);
         }

         public boolean fromByte$mcZ$sp(final byte n) {
            return ConvertableTo.fromByte$mcZ$sp$(this, n);
         }

         public byte fromByte$mcB$sp(final byte n) {
            return ConvertableTo.fromByte$mcB$sp$(this, n);
         }

         public char fromByte$mcC$sp(final byte n) {
            return ConvertableTo.fromByte$mcC$sp$(this, n);
         }

         public double fromByte$mcD$sp(final byte n) {
            return ConvertableTo.fromByte$mcD$sp$(this, n);
         }

         public float fromByte$mcF$sp(final byte n) {
            return ConvertableTo.fromByte$mcF$sp$(this, n);
         }

         public int fromByte$mcI$sp(final byte n) {
            return ConvertableTo.fromByte$mcI$sp$(this, n);
         }

         public long fromByte$mcJ$sp(final byte n) {
            return ConvertableTo.fromByte$mcJ$sp$(this, n);
         }

         public short fromByte$mcS$sp(final byte n) {
            return ConvertableTo.fromByte$mcS$sp$(this, n);
         }

         public void fromByte$mcV$sp(final byte n) {
            ConvertableTo.fromByte$mcV$sp$(this, n);
         }

         public boolean fromShort$mcZ$sp(final short n) {
            return ConvertableTo.fromShort$mcZ$sp$(this, n);
         }

         public byte fromShort$mcB$sp(final short n) {
            return ConvertableTo.fromShort$mcB$sp$(this, n);
         }

         public char fromShort$mcC$sp(final short n) {
            return ConvertableTo.fromShort$mcC$sp$(this, n);
         }

         public double fromShort$mcD$sp(final short n) {
            return ConvertableTo.fromShort$mcD$sp$(this, n);
         }

         public float fromShort$mcF$sp(final short n) {
            return ConvertableTo.fromShort$mcF$sp$(this, n);
         }

         public int fromShort$mcI$sp(final short n) {
            return ConvertableTo.fromShort$mcI$sp$(this, n);
         }

         public long fromShort$mcJ$sp(final short n) {
            return ConvertableTo.fromShort$mcJ$sp$(this, n);
         }

         public short fromShort$mcS$sp(final short n) {
            return ConvertableTo.fromShort$mcS$sp$(this, n);
         }

         public void fromShort$mcV$sp(final short n) {
            ConvertableTo.fromShort$mcV$sp$(this, n);
         }

         public boolean fromInt$mcZ$sp(final int n) {
            return ConvertableTo.fromInt$mcZ$sp$(this, n);
         }

         public byte fromInt$mcB$sp(final int n) {
            return ConvertableTo.fromInt$mcB$sp$(this, n);
         }

         public char fromInt$mcC$sp(final int n) {
            return ConvertableTo.fromInt$mcC$sp$(this, n);
         }

         public double fromInt$mcD$sp(final int n) {
            return ConvertableTo.fromInt$mcD$sp$(this, n);
         }

         public float fromInt$mcF$sp(final int n) {
            return ConvertableTo.fromInt$mcF$sp$(this, n);
         }

         public int fromInt$mcI$sp(final int n) {
            return ConvertableTo.fromInt$mcI$sp$(this, n);
         }

         public long fromInt$mcJ$sp(final int n) {
            return ConvertableTo.fromInt$mcJ$sp$(this, n);
         }

         public short fromInt$mcS$sp(final int n) {
            return ConvertableTo.fromInt$mcS$sp$(this, n);
         }

         public void fromInt$mcV$sp(final int n) {
            ConvertableTo.fromInt$mcV$sp$(this, n);
         }

         public boolean fromLong$mcZ$sp(final long n) {
            return ConvertableTo.fromLong$mcZ$sp$(this, n);
         }

         public byte fromLong$mcB$sp(final long n) {
            return ConvertableTo.fromLong$mcB$sp$(this, n);
         }

         public char fromLong$mcC$sp(final long n) {
            return ConvertableTo.fromLong$mcC$sp$(this, n);
         }

         public double fromLong$mcD$sp(final long n) {
            return ConvertableTo.fromLong$mcD$sp$(this, n);
         }

         public float fromLong$mcF$sp(final long n) {
            return ConvertableTo.fromLong$mcF$sp$(this, n);
         }

         public int fromLong$mcI$sp(final long n) {
            return ConvertableTo.fromLong$mcI$sp$(this, n);
         }

         public long fromLong$mcJ$sp(final long n) {
            return ConvertableTo.fromLong$mcJ$sp$(this, n);
         }

         public short fromLong$mcS$sp(final long n) {
            return ConvertableTo.fromLong$mcS$sp$(this, n);
         }

         public void fromLong$mcV$sp(final long n) {
            ConvertableTo.fromLong$mcV$sp$(this, n);
         }

         public boolean fromFloat$mcZ$sp(final float n) {
            return ConvertableTo.fromFloat$mcZ$sp$(this, n);
         }

         public byte fromFloat$mcB$sp(final float n) {
            return ConvertableTo.fromFloat$mcB$sp$(this, n);
         }

         public char fromFloat$mcC$sp(final float n) {
            return ConvertableTo.fromFloat$mcC$sp$(this, n);
         }

         public double fromFloat$mcD$sp(final float n) {
            return ConvertableTo.fromFloat$mcD$sp$(this, n);
         }

         public float fromFloat$mcF$sp(final float n) {
            return ConvertableTo.fromFloat$mcF$sp$(this, n);
         }

         public int fromFloat$mcI$sp(final float n) {
            return ConvertableTo.fromFloat$mcI$sp$(this, n);
         }

         public long fromFloat$mcJ$sp(final float n) {
            return ConvertableTo.fromFloat$mcJ$sp$(this, n);
         }

         public short fromFloat$mcS$sp(final float n) {
            return ConvertableTo.fromFloat$mcS$sp$(this, n);
         }

         public void fromFloat$mcV$sp(final float n) {
            ConvertableTo.fromFloat$mcV$sp$(this, n);
         }

         public boolean fromDouble$mcZ$sp(final double n) {
            return ConvertableTo.fromDouble$mcZ$sp$(this, n);
         }

         public byte fromDouble$mcB$sp(final double n) {
            return ConvertableTo.fromDouble$mcB$sp$(this, n);
         }

         public char fromDouble$mcC$sp(final double n) {
            return ConvertableTo.fromDouble$mcC$sp$(this, n);
         }

         public double fromDouble$mcD$sp(final double n) {
            return ConvertableTo.fromDouble$mcD$sp$(this, n);
         }

         public float fromDouble$mcF$sp(final double n) {
            return ConvertableTo.fromDouble$mcF$sp$(this, n);
         }

         public int fromDouble$mcI$sp(final double n) {
            return ConvertableTo.fromDouble$mcI$sp$(this, n);
         }

         public long fromDouble$mcJ$sp(final double n) {
            return ConvertableTo.fromDouble$mcJ$sp$(this, n);
         }

         public short fromDouble$mcS$sp(final double n) {
            return ConvertableTo.fromDouble$mcS$sp$(this, n);
         }

         public void fromDouble$mcV$sp(final double n) {
            ConvertableTo.fromDouble$mcV$sp$(this, n);
         }

         public boolean fromBigInt$mcZ$sp(final BigInt n) {
            return ConvertableTo.fromBigInt$mcZ$sp$(this, n);
         }

         public byte fromBigInt$mcB$sp(final BigInt n) {
            return ConvertableTo.fromBigInt$mcB$sp$(this, n);
         }

         public char fromBigInt$mcC$sp(final BigInt n) {
            return ConvertableTo.fromBigInt$mcC$sp$(this, n);
         }

         public double fromBigInt$mcD$sp(final BigInt n) {
            return ConvertableTo.fromBigInt$mcD$sp$(this, n);
         }

         public float fromBigInt$mcF$sp(final BigInt n) {
            return ConvertableTo.fromBigInt$mcF$sp$(this, n);
         }

         public int fromBigInt$mcI$sp(final BigInt n) {
            return ConvertableTo.fromBigInt$mcI$sp$(this, n);
         }

         public long fromBigInt$mcJ$sp(final BigInt n) {
            return ConvertableTo.fromBigInt$mcJ$sp$(this, n);
         }

         public short fromBigInt$mcS$sp(final BigInt n) {
            return ConvertableTo.fromBigInt$mcS$sp$(this, n);
         }

         public void fromBigInt$mcV$sp(final BigInt n) {
            ConvertableTo.fromBigInt$mcV$sp$(this, n);
         }

         public boolean fromBigDecimal$mcZ$sp(final BigDecimal n) {
            return ConvertableTo.fromBigDecimal$mcZ$sp$(this, n);
         }

         public byte fromBigDecimal$mcB$sp(final BigDecimal n) {
            return ConvertableTo.fromBigDecimal$mcB$sp$(this, n);
         }

         public char fromBigDecimal$mcC$sp(final BigDecimal n) {
            return ConvertableTo.fromBigDecimal$mcC$sp$(this, n);
         }

         public double fromBigDecimal$mcD$sp(final BigDecimal n) {
            return ConvertableTo.fromBigDecimal$mcD$sp$(this, n);
         }

         public float fromBigDecimal$mcF$sp(final BigDecimal n) {
            return ConvertableTo.fromBigDecimal$mcF$sp$(this, n);
         }

         public int fromBigDecimal$mcI$sp(final BigDecimal n) {
            return ConvertableTo.fromBigDecimal$mcI$sp$(this, n);
         }

         public long fromBigDecimal$mcJ$sp(final BigDecimal n) {
            return ConvertableTo.fromBigDecimal$mcJ$sp$(this, n);
         }

         public short fromBigDecimal$mcS$sp(final BigDecimal n) {
            return ConvertableTo.fromBigDecimal$mcS$sp$(this, n);
         }

         public void fromBigDecimal$mcV$sp(final BigDecimal n) {
            ConvertableTo.fromBigDecimal$mcV$sp$(this, n);
         }

         public boolean fromRational$mcZ$sp(final Rational n) {
            return ConvertableTo.fromRational$mcZ$sp$(this, n);
         }

         public byte fromRational$mcB$sp(final Rational n) {
            return ConvertableTo.fromRational$mcB$sp$(this, n);
         }

         public char fromRational$mcC$sp(final Rational n) {
            return ConvertableTo.fromRational$mcC$sp$(this, n);
         }

         public double fromRational$mcD$sp(final Rational n) {
            return ConvertableTo.fromRational$mcD$sp$(this, n);
         }

         public float fromRational$mcF$sp(final Rational n) {
            return ConvertableTo.fromRational$mcF$sp$(this, n);
         }

         public int fromRational$mcI$sp(final Rational n) {
            return ConvertableTo.fromRational$mcI$sp$(this, n);
         }

         public long fromRational$mcJ$sp(final Rational n) {
            return ConvertableTo.fromRational$mcJ$sp$(this, n);
         }

         public short fromRational$mcS$sp(final Rational n) {
            return ConvertableTo.fromRational$mcS$sp$(this, n);
         }

         public void fromRational$mcV$sp(final Rational n) {
            ConvertableTo.fromRational$mcV$sp$(this, n);
         }

         public boolean fromAlgebraic$mcZ$sp(final Algebraic n) {
            return ConvertableTo.fromAlgebraic$mcZ$sp$(this, n);
         }

         public byte fromAlgebraic$mcB$sp(final Algebraic n) {
            return ConvertableTo.fromAlgebraic$mcB$sp$(this, n);
         }

         public char fromAlgebraic$mcC$sp(final Algebraic n) {
            return ConvertableTo.fromAlgebraic$mcC$sp$(this, n);
         }

         public double fromAlgebraic$mcD$sp(final Algebraic n) {
            return ConvertableTo.fromAlgebraic$mcD$sp$(this, n);
         }

         public float fromAlgebraic$mcF$sp(final Algebraic n) {
            return ConvertableTo.fromAlgebraic$mcF$sp$(this, n);
         }

         public int fromAlgebraic$mcI$sp(final Algebraic n) {
            return ConvertableTo.fromAlgebraic$mcI$sp$(this, n);
         }

         public long fromAlgebraic$mcJ$sp(final Algebraic n) {
            return ConvertableTo.fromAlgebraic$mcJ$sp$(this, n);
         }

         public short fromAlgebraic$mcS$sp(final Algebraic n) {
            return ConvertableTo.fromAlgebraic$mcS$sp$(this, n);
         }

         public void fromAlgebraic$mcV$sp(final Algebraic n) {
            ConvertableTo.fromAlgebraic$mcV$sp$(this, n);
         }

         public boolean fromReal$mcZ$sp(final Real n) {
            return ConvertableTo.fromReal$mcZ$sp$(this, n);
         }

         public byte fromReal$mcB$sp(final Real n) {
            return ConvertableTo.fromReal$mcB$sp$(this, n);
         }

         public char fromReal$mcC$sp(final Real n) {
            return ConvertableTo.fromReal$mcC$sp$(this, n);
         }

         public double fromReal$mcD$sp(final Real n) {
            return ConvertableTo.fromReal$mcD$sp$(this, n);
         }

         public float fromReal$mcF$sp(final Real n) {
            return ConvertableTo.fromReal$mcF$sp$(this, n);
         }

         public int fromReal$mcI$sp(final Real n) {
            return ConvertableTo.fromReal$mcI$sp$(this, n);
         }

         public long fromReal$mcJ$sp(final Real n) {
            return ConvertableTo.fromReal$mcJ$sp$(this, n);
         }

         public short fromReal$mcS$sp(final Real n) {
            return ConvertableTo.fromReal$mcS$sp$(this, n);
         }

         public void fromReal$mcV$sp(final Real n) {
            ConvertableTo.fromReal$mcV$sp$(this, n);
         }

         public boolean fromType$mcZ$sp(final Object b, final ConvertableFrom evidence$1) {
            return ConvertableTo.fromType$mcZ$sp$(this, b, evidence$1);
         }

         public byte fromType$mcB$sp(final Object b, final ConvertableFrom evidence$1) {
            return ConvertableTo.fromType$mcB$sp$(this, b, evidence$1);
         }

         public char fromType$mcC$sp(final Object b, final ConvertableFrom evidence$1) {
            return ConvertableTo.fromType$mcC$sp$(this, b, evidence$1);
         }

         public double fromType$mcD$sp(final Object b, final ConvertableFrom evidence$1) {
            return ConvertableTo.fromType$mcD$sp$(this, b, evidence$1);
         }

         public float fromType$mcF$sp(final Object b, final ConvertableFrom evidence$1) {
            return ConvertableTo.fromType$mcF$sp$(this, b, evidence$1);
         }

         public int fromType$mcI$sp(final Object b, final ConvertableFrom evidence$1) {
            return ConvertableTo.fromType$mcI$sp$(this, b, evidence$1);
         }

         public long fromType$mcJ$sp(final Object b, final ConvertableFrom evidence$1) {
            return ConvertableTo.fromType$mcJ$sp$(this, b, evidence$1);
         }

         public short fromType$mcS$sp(final Object b, final ConvertableFrom evidence$1) {
            return ConvertableTo.fromType$mcS$sp$(this, b, evidence$1);
         }

         public void fromType$mcV$sp(final Object b, final ConvertableFrom evidence$1) {
            ConvertableTo.fromType$mcV$sp$(this, b, evidence$1);
         }

         public Integral algebra() {
            return this.algebra;
         }

         public {
            ConvertableToComplex.$init$(this);
            this.algebra = Integral$.MODULE$.apply(evidence$16$1);
         }
      };
   }

   private ConvertableTo$() {
   }
}
