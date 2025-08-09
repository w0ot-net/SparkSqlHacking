package scala;

import java.lang.invoke.SerializedLambda;
import scala.collection.Seq;

public final class Function$ {
   public static final Function$ MODULE$ = new Function$();

   public Function1 chain(final Seq fs) {
      return (x) -> fs.foldLeft(x, (xx, f) -> f.apply(xx));
   }

   public Object const(final Object x, final Object y) {
      return x;
   }

   public PartialFunction unlift(final Function1 f) {
      PartialFunction$ var10000 = PartialFunction$.MODULE$;
      return (PartialFunction)(f instanceof PartialFunction.Lifted ? ((PartialFunction.Lifted)f).pf() : new PartialFunction.Unlifted(f));
   }

   public Function2 uncurried(final Function1 f) {
      return (x1, x2) -> ((Function1)f.apply(x1)).apply(x2);
   }

   public Function3 uncurried(final Function1 f) {
      return (x1, x2, x3) -> ((Function1)((Function1)f.apply(x1)).apply(x2)).apply(x3);
   }

   public Function4 uncurried(final Function1 f) {
      return (x1, x2, x3, x4) -> ((Function1)((Function1)((Function1)f.apply(x1)).apply(x2)).apply(x3)).apply(x4);
   }

   public Function5 uncurried(final Function1 f) {
      return (x1, x2, x3, x4, x5) -> ((Function1)((Function1)((Function1)((Function1)f.apply(x1)).apply(x2)).apply(x3)).apply(x4)).apply(x5);
   }

   public Function1 tupled(final Function2 f) {
      return (x0$1) -> {
         if (x0$1 != null) {
            Object x1 = x0$1._1();
            Object x2 = x0$1._2();
            return f.apply(x1, x2);
         } else {
            throw new MatchError((Object)null);
         }
      };
   }

   public Function1 tupled(final Function3 f) {
      return (x0$1) -> {
         if (x0$1 != null) {
            Object x1 = x0$1._1();
            Object x2 = x0$1._2();
            Object x3 = x0$1._3();
            return f.apply(x1, x2, x3);
         } else {
            throw new MatchError((Object)null);
         }
      };
   }

   public Function1 tupled(final Function4 f) {
      return (x0$1) -> {
         if (x0$1 != null) {
            Object x1 = x0$1._1();
            Object x2 = x0$1._2();
            Object x3 = x0$1._3();
            Object x4 = x0$1._4();
            return f.apply(x1, x2, x3, x4);
         } else {
            throw new MatchError((Object)null);
         }
      };
   }

   public Function1 tupled(final Function5 f) {
      return (x0$1) -> {
         if (x0$1 != null) {
            Object x1 = x0$1._1();
            Object x2 = x0$1._2();
            Object x3 = x0$1._3();
            Object x4 = x0$1._4();
            Object x5 = x0$1._5();
            return f.apply(x1, x2, x3, x4, x5);
         } else {
            throw new MatchError((Object)null);
         }
      };
   }

   public Function2 untupled(final Function1 f) {
      return (x1, x2) -> f.apply(new Tuple2(x1, x2));
   }

   public Function3 untupled(final Function1 f) {
      return (x1, x2, x3) -> f.apply(new Tuple3(x1, x2, x3));
   }

   public Function4 untupled(final Function1 f) {
      return (x1, x2, x3, x4) -> f.apply(new Tuple4(x1, x2, x3, x4));
   }

   public Function5 untupled(final Function1 f) {
      return (x1, x2, x3, x4, x5) -> f.apply(new Tuple5(x1, x2, x3, x4, x5));
   }

   private Function$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
