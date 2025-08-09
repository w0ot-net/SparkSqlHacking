package spire.math;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class Integral$ implements Serializable {
   public static final Integral$ MODULE$ = new Integral$();
   private static final Integral ByteIsIntegral = new ByteIsIntegral();
   private static final Integral ShortIsIntegral = new ShortIsIntegral();
   private static final Integral IntIsIntegral = new IntIsIntegral();
   private static final Integral LongIsIntegral = new LongIsIntegral();
   private static final Integral BigIntIsIntegral = new BigIntIsIntegral();
   private static final Integral SafeLongIsIntegral = new SafeLongIsIntegral();

   public final Integral ByteIsIntegral() {
      return ByteIsIntegral;
   }

   public final Integral ShortIsIntegral() {
      return ShortIsIntegral;
   }

   public final Integral IntIsIntegral() {
      return IntIsIntegral;
   }

   public final Integral LongIsIntegral() {
      return LongIsIntegral;
   }

   public final Integral BigIntIsIntegral() {
      return BigIntIsIntegral;
   }

   public final Integral SafeLongIsIntegral() {
      return SafeLongIsIntegral;
   }

   public final Integral apply(final Integral ev) {
      return ev;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Integral$.class);
   }

   private Integral$() {
   }
}
