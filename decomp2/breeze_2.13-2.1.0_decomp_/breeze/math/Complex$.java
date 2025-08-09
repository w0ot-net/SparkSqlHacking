package breeze.math;

import breeze.generic.UFunc;
import breeze.storage.Zero;
import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.ModuleSerializationProxy;

public final class Complex$ implements Serializable {
   public static final Complex$ MODULE$ = new Complex$();
   private static final Complex zero = new Complex((double)0.0F, (double)0.0F);
   private static final Complex one = new Complex((double)1.0F, (double)0.0F);
   private static final Complex nan = new Complex(Double.NaN, Double.NaN);
   private static final Complex i = new Complex((double)0.0F, (double)1.0F);
   private static final UFunc.UImpl complexNorm = new UFunc.UImpl() {
      public double apply$mcDD$sp(final double v) {
         return UFunc.UImpl.apply$mcDD$sp$(this, v);
      }

      public float apply$mcDF$sp(final double v) {
         return UFunc.UImpl.apply$mcDF$sp$(this, v);
      }

      public int apply$mcDI$sp(final double v) {
         return UFunc.UImpl.apply$mcDI$sp$(this, v);
      }

      public double apply$mcFD$sp(final float v) {
         return UFunc.UImpl.apply$mcFD$sp$(this, v);
      }

      public float apply$mcFF$sp(final float v) {
         return UFunc.UImpl.apply$mcFF$sp$(this, v);
      }

      public int apply$mcFI$sp(final float v) {
         return UFunc.UImpl.apply$mcFI$sp$(this, v);
      }

      public double apply$mcID$sp(final int v) {
         return UFunc.UImpl.apply$mcID$sp$(this, v);
      }

      public float apply$mcIF$sp(final int v) {
         return UFunc.UImpl.apply$mcIF$sp$(this, v);
      }

      public int apply$mcII$sp(final int v) {
         return UFunc.UImpl.apply$mcII$sp$(this, v);
      }

      public double apply(final Complex v1) {
         return v1.abs();
      }
   };
   private static final Zero ComplexZero;

   static {
      ComplexZero = new Zero(MODULE$.zero());
   }

   public Complex zero() {
      return zero;
   }

   public Complex one() {
      return one;
   }

   public Complex nan() {
      return nan;
   }

   public Complex i() {
      return i;
   }

   public UFunc.UImpl complexNorm() {
      return complexNorm;
   }

   public Zero ComplexZero() {
      return ComplexZero;
   }

   public Complex apply(final double real, final double imag) {
      return new Complex(real, imag);
   }

   public Option unapply(final Complex x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2.mcDD.sp(x$0.real(), x$0.imag())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Complex$.class);
   }

   private Complex$() {
   }
}
