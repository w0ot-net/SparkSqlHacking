package spire.math;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class Numeric$ implements Serializable {
   public static final Numeric$ MODULE$ = new Numeric$();
   private static final Numeric ByteIsNumeric = new ByteIsNumeric();
   private static final Numeric ShortIsNumeric = new ShortIsNumeric();
   private static final Numeric IntIsNumeric = new IntIsNumeric();
   private static final Numeric LongIsNumeric = new LongIsNumeric();
   private static final Numeric FloatIsNumeric = new FloatIsNumeric();
   private static final Numeric DoubleIsNumeric = new DoubleIsNumeric();
   private static final Numeric BigIntIsNumeric = new BigIntIsNumeric();
   private static final Numeric BigDecimalIsNumeric = new BigDecimalIsNumeric();
   private static final Numeric AlgebraicIsNumeric = new AlgebraicIsNumeric();
   private static final Numeric RealIsNumeric = new RealIsNumeric();
   private static final Numeric RationalIsNumeric = new RationalIsNumeric();

   public final Numeric ByteIsNumeric() {
      return ByteIsNumeric;
   }

   public final Numeric ShortIsNumeric() {
      return ShortIsNumeric;
   }

   public final Numeric IntIsNumeric() {
      return IntIsNumeric;
   }

   public final Numeric LongIsNumeric() {
      return LongIsNumeric;
   }

   public final Numeric FloatIsNumeric() {
      return FloatIsNumeric;
   }

   public final Numeric DoubleIsNumeric() {
      return DoubleIsNumeric;
   }

   public final Numeric BigIntIsNumeric() {
      return BigIntIsNumeric;
   }

   public final Numeric BigDecimalIsNumeric() {
      return BigDecimalIsNumeric;
   }

   public final Numeric AlgebraicIsNumeric() {
      return AlgebraicIsNumeric;
   }

   public final Numeric RealIsNumeric() {
      return RealIsNumeric;
   }

   public final Numeric RationalIsNumeric() {
      return RationalIsNumeric;
   }

   public final Numeric apply(final Numeric ev) {
      return ev;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Numeric$.class);
   }

   private Numeric$() {
   }
}
