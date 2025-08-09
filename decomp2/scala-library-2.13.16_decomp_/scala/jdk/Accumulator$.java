package scala.jdk;

import java.lang.invoke.SerializedLambda;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Function3;
import scala.Function4;
import scala.Function5;
import scala.collection.Factory;
import scala.collection.IterableOnce;
import scala.collection.View;
import scala.collection.immutable.NumericRange;
import scala.collection.immutable.NumericRange$;
import scala.collection.immutable.Seq;
import scala.collection.mutable.Builder;
import scala.math.Integral;
import scala.runtime.BoxesRunTime;

public final class Accumulator$ {
   public static final Accumulator$ MODULE$ = new Accumulator$();

   public Factory toFactory(final Accumulator$ sa, final Accumulator.AccumulatorFactoryShape canAccumulate) {
      return canAccumulate.factory();
   }

   public Object from(final IterableOnce source, final Accumulator.AccumulatorFactoryShape canAccumulate) {
      return source.iterator().to(canAccumulate.factory());
   }

   public Object empty(final Accumulator.AccumulatorFactoryShape canAccumulate) {
      return canAccumulate.empty();
   }

   public Object apply(final Seq elems, final Accumulator.AccumulatorFactoryShape canAccumulate) {
      return canAccumulate.factory().fromSpecific(elems);
   }

   public Object iterate(final Object start, final int len, final Function1 f, final Accumulator.AccumulatorFactoryShape canAccumulate) {
      return this.from(new View.Iterate(start, len, f), canAccumulate);
   }

   public Object unfold(final Object init, final Function1 f, final Accumulator.AccumulatorFactoryShape canAccumulate) {
      return this.from(new View.Unfold(init, f), canAccumulate);
   }

   public Object range(final Object start, final Object end, final Integral evidence$1, final Accumulator.AccumulatorFactoryShape canAccumulate) {
      NumericRange$ var10001 = NumericRange$.MODULE$;
      Object apply_step = evidence$1.one();
      NumericRange.Exclusive var7 = new NumericRange.Exclusive(start, end, apply_step, evidence$1);
      apply_step = null;
      return this.from(var7, canAccumulate);
   }

   public Object range(final Object start, final Object end, final Object step, final Integral evidence$2, final Accumulator.AccumulatorFactoryShape canAccumulate) {
      NumericRange$ var10001 = NumericRange$.MODULE$;
      return this.from(new NumericRange.Exclusive(start, end, step, evidence$2), canAccumulate);
   }

   public Builder newBuilder(final Accumulator.AccumulatorFactoryShape canAccumulate) {
      return canAccumulate.factory().newBuilder();
   }

   public Object fill(final int n, final Function0 elem, final Accumulator.AccumulatorFactoryShape canAccumulate) {
      return this.from(new View.Fill(n, elem), canAccumulate);
   }

   public AnyAccumulator fill(final int n1, final int n2, final Function0 elem, final Accumulator.AccumulatorFactoryShape canAccumulate) {
      Function0 var10000 = () -> MODULE$.fill(n2, elem, canAccumulate);
      Accumulator.AccumulatorFactoryShape$ anyAccumulatorFactoryShape_this = Accumulator.AccumulatorFactoryShape$.MODULE$;
      Accumulator.AccumulatorFactoryShape var10001 = anyAccumulatorFactoryShape_this.scala$jdk$Accumulator$LowPriorityAccumulatorFactoryShape$$anyAccumulatorFactoryShapePrototype();
      Object var8 = null;
      Accumulator.AccumulatorFactoryShape fill_canAccumulate = var10001;
      Function0 fill_elem = var10000;
      return (AnyAccumulator)this.from(new View.Fill(n1, fill_elem), fill_canAccumulate);
   }

   public AnyAccumulator fill(final int n1, final int n2, final int n3, final Function0 elem, final Accumulator.AccumulatorFactoryShape canAccumulate) {
      Function0 var10000 = () -> MODULE$.fill(n2, n3, elem, canAccumulate);
      Accumulator.AccumulatorFactoryShape$ anyAccumulatorFactoryShape_this = Accumulator.AccumulatorFactoryShape$.MODULE$;
      Accumulator.AccumulatorFactoryShape var10001 = anyAccumulatorFactoryShape_this.scala$jdk$Accumulator$LowPriorityAccumulatorFactoryShape$$anyAccumulatorFactoryShapePrototype();
      Object var9 = null;
      Accumulator.AccumulatorFactoryShape fill_canAccumulate = var10001;
      Function0 fill_elem = var10000;
      return (AnyAccumulator)this.from(new View.Fill(n1, fill_elem), fill_canAccumulate);
   }

   public AnyAccumulator fill(final int n1, final int n2, final int n3, final int n4, final Function0 elem, final Accumulator.AccumulatorFactoryShape canAccumulate) {
      Function0 var10000 = () -> MODULE$.fill(n2, n3, n4, elem, canAccumulate);
      Accumulator.AccumulatorFactoryShape$ anyAccumulatorFactoryShape_this = Accumulator.AccumulatorFactoryShape$.MODULE$;
      Accumulator.AccumulatorFactoryShape var10001 = anyAccumulatorFactoryShape_this.scala$jdk$Accumulator$LowPriorityAccumulatorFactoryShape$$anyAccumulatorFactoryShapePrototype();
      Object var10 = null;
      Accumulator.AccumulatorFactoryShape fill_canAccumulate = var10001;
      Function0 fill_elem = var10000;
      return (AnyAccumulator)this.from(new View.Fill(n1, fill_elem), fill_canAccumulate);
   }

   public AnyAccumulator fill(final int n1, final int n2, final int n3, final int n4, final int n5, final Function0 elem, final Accumulator.AccumulatorFactoryShape canAccumulate) {
      Function0 var10000 = () -> MODULE$.fill(n2, n3, n4, n5, elem, canAccumulate);
      Accumulator.AccumulatorFactoryShape$ anyAccumulatorFactoryShape_this = Accumulator.AccumulatorFactoryShape$.MODULE$;
      Accumulator.AccumulatorFactoryShape var10001 = anyAccumulatorFactoryShape_this.scala$jdk$Accumulator$LowPriorityAccumulatorFactoryShape$$anyAccumulatorFactoryShapePrototype();
      Object var11 = null;
      Accumulator.AccumulatorFactoryShape fill_canAccumulate = var10001;
      Function0 fill_elem = var10000;
      return (AnyAccumulator)this.from(new View.Fill(n1, fill_elem), fill_canAccumulate);
   }

   public Object tabulate(final int n, final Function1 f, final Accumulator.AccumulatorFactoryShape canAccumulate) {
      return this.from(new View.Tabulate(n, f), canAccumulate);
   }

   public AnyAccumulator tabulate(final int n1, final int n2, final Function2 f, final Accumulator.AccumulatorFactoryShape canAccumulate) {
      Function1 var10000 = (i1x) -> $anonfun$tabulate$1(n3$4, tabulate_f, canAccumulate$6, BoxesRunTime.unboxToInt(i1x));
      Accumulator.AccumulatorFactoryShape$ anyAccumulatorFactoryShape_this = Accumulator.AccumulatorFactoryShape$.MODULE$;
      Accumulator.AccumulatorFactoryShape var10001 = anyAccumulatorFactoryShape_this.scala$jdk$Accumulator$LowPriorityAccumulatorFactoryShape$$anyAccumulatorFactoryShapePrototype();
      Object var8 = null;
      Accumulator.AccumulatorFactoryShape tabulate_canAccumulate = var10001;
      Function1 tabulate_f = var10000;
      return (AnyAccumulator)this.from(new View.Tabulate(n1, tabulate_f), tabulate_canAccumulate);
   }

   public AnyAccumulator tabulate(final int n1, final int n2, final int n3, final Function3 f, final Accumulator.AccumulatorFactoryShape canAccumulate) {
      Function1 var10000 = (i1x) -> $anonfun$tabulate$3(n3$5, n4$3, tabulate_f, canAccumulate$7, BoxesRunTime.unboxToInt(i1x));
      Accumulator.AccumulatorFactoryShape$ anyAccumulatorFactoryShape_this = Accumulator.AccumulatorFactoryShape$.MODULE$;
      Accumulator.AccumulatorFactoryShape var10001 = anyAccumulatorFactoryShape_this.scala$jdk$Accumulator$LowPriorityAccumulatorFactoryShape$$anyAccumulatorFactoryShapePrototype();
      Object var9 = null;
      Accumulator.AccumulatorFactoryShape tabulate_canAccumulate = var10001;
      Function1 tabulate_f = var10000;
      return (AnyAccumulator)this.from(new View.Tabulate(n1, tabulate_f), tabulate_canAccumulate);
   }

   public AnyAccumulator tabulate(final int n1, final int n2, final int n3, final int n4, final Function4 f, final Accumulator.AccumulatorFactoryShape canAccumulate) {
      Function1 var10000 = (i1x) -> $anonfun$tabulate$5(n3$6, n4$4, n5$2, tabulate_f, canAccumulate$8, BoxesRunTime.unboxToInt(i1x));
      Accumulator.AccumulatorFactoryShape$ anyAccumulatorFactoryShape_this = Accumulator.AccumulatorFactoryShape$.MODULE$;
      Accumulator.AccumulatorFactoryShape var10001 = anyAccumulatorFactoryShape_this.scala$jdk$Accumulator$LowPriorityAccumulatorFactoryShape$$anyAccumulatorFactoryShapePrototype();
      Object var10 = null;
      Accumulator.AccumulatorFactoryShape tabulate_canAccumulate = var10001;
      Function1 tabulate_f = var10000;
      return (AnyAccumulator)this.from(new View.Tabulate(n1, tabulate_f), tabulate_canAccumulate);
   }

   public AnyAccumulator tabulate(final int n1, final int n2, final int n3, final int n4, final int n5, final Function5 f, final Accumulator.AccumulatorFactoryShape canAccumulate) {
      Function1 var10000 = (i1) -> $anonfun$tabulate$7(n2, n3, n4, n5, f, canAccumulate, BoxesRunTime.unboxToInt(i1));
      Accumulator.AccumulatorFactoryShape$ anyAccumulatorFactoryShape_this = Accumulator.AccumulatorFactoryShape$.MODULE$;
      Accumulator.AccumulatorFactoryShape var10001 = anyAccumulatorFactoryShape_this.scala$jdk$Accumulator$LowPriorityAccumulatorFactoryShape$$anyAccumulatorFactoryShapePrototype();
      Object var11 = null;
      Accumulator.AccumulatorFactoryShape tabulate_canAccumulate = var10001;
      Function1 tabulate_f = var10000;
      return (AnyAccumulator)this.from(new View.Tabulate(n1, tabulate_f), tabulate_canAccumulate);
   }

   public Object concat(final Seq xss, final Accumulator.AccumulatorFactoryShape canAccumulate) {
      if (xss.isEmpty()) {
         return canAccumulate.empty();
      } else {
         Builder b = canAccumulate.factory().newBuilder();
         xss.foreach((x$11) -> (Builder)b.$plus$plus$eq(x$11));
         return b.result();
      }
   }

   // $FF: synthetic method
   public static final Object $anonfun$tabulate$2(final Function2 f$1, final int i1$1, final int x$1) {
      return f$1.apply(BoxesRunTime.boxToInteger(i1$1), BoxesRunTime.boxToInteger(x$1));
   }

   // $FF: synthetic method
   public static final Object $anonfun$tabulate$1(final int n2$5, final Function2 f$1, final Accumulator.AccumulatorFactoryShape canAccumulate$5, final int i1) {
      Accumulator$ var10000 = MODULE$;
      Function1 tabulate_f = (x$1) -> $anonfun$tabulate$2(f$1, i1, BoxesRunTime.unboxToInt(x$1));
      return var10000.from(new View.Tabulate(n2$5, tabulate_f), canAccumulate$5);
   }

   // $FF: synthetic method
   public static final Object $anonfun$tabulate$4(final Function3 f$2, final int i1$2, final int x$2, final int x$3) {
      return f$2.apply(BoxesRunTime.boxToInteger(i1$2), BoxesRunTime.boxToInteger(x$2), BoxesRunTime.boxToInteger(x$3));
   }

   // $FF: synthetic method
   public static final AnyAccumulator $anonfun$tabulate$3(final int n2$6, final int n3$4, final Function3 f$2, final Accumulator.AccumulatorFactoryShape canAccumulate$6, final int i1) {
      Accumulator$ var10000 = MODULE$;
      Function2 tabulate_f = (x$2, x$3) -> $anonfun$tabulate$4(f$2, i1, BoxesRunTime.unboxToInt(x$2), BoxesRunTime.unboxToInt(x$3));
      Accumulator$ tabulate_this = var10000;
      Function1 var11 = (i1x) -> $anonfun$tabulate$1(n3$4, tabulate_f, canAccumulate$6, BoxesRunTime.unboxToInt(i1x));
      Accumulator.AccumulatorFactoryShape$ tabulate_anyAccumulatorFactoryShape_this = Accumulator.AccumulatorFactoryShape$.MODULE$;
      Accumulator.AccumulatorFactoryShape var10001 = tabulate_anyAccumulatorFactoryShape_this.scala$jdk$Accumulator$LowPriorityAccumulatorFactoryShape$$anyAccumulatorFactoryShapePrototype();
      Object var10 = null;
      Accumulator.AccumulatorFactoryShape tabulate_tabulate_canAccumulate = var10001;
      Function1 tabulate_tabulate_f = var11;
      return (AnyAccumulator)tabulate_this.from(new View.Tabulate(n2$6, tabulate_tabulate_f), tabulate_tabulate_canAccumulate);
   }

   // $FF: synthetic method
   public static final Object $anonfun$tabulate$6(final Function4 f$3, final int i1$3, final int x$4, final int x$5, final int x$6) {
      return f$3.apply(BoxesRunTime.boxToInteger(i1$3), BoxesRunTime.boxToInteger(x$4), BoxesRunTime.boxToInteger(x$5), BoxesRunTime.boxToInteger(x$6));
   }

   // $FF: synthetic method
   public static final AnyAccumulator $anonfun$tabulate$5(final int n2$7, final int n3$5, final int n4$3, final Function4 f$3, final Accumulator.AccumulatorFactoryShape canAccumulate$7, final int i1) {
      Accumulator$ var10000 = MODULE$;
      Function3 tabulate_f = (x$4, x$5, x$6) -> $anonfun$tabulate$6(f$3, i1, BoxesRunTime.unboxToInt(x$4), BoxesRunTime.unboxToInt(x$5), BoxesRunTime.unboxToInt(x$6));
      Accumulator$ tabulate_this = var10000;
      Function1 var12 = (i1x) -> $anonfun$tabulate$3(n3$5, n4$3, tabulate_f, canAccumulate$7, BoxesRunTime.unboxToInt(i1x));
      Accumulator.AccumulatorFactoryShape$ tabulate_anyAccumulatorFactoryShape_this = Accumulator.AccumulatorFactoryShape$.MODULE$;
      Accumulator.AccumulatorFactoryShape var10001 = tabulate_anyAccumulatorFactoryShape_this.scala$jdk$Accumulator$LowPriorityAccumulatorFactoryShape$$anyAccumulatorFactoryShapePrototype();
      Object var11 = null;
      Accumulator.AccumulatorFactoryShape tabulate_tabulate_canAccumulate = var10001;
      Function1 tabulate_tabulate_f = var12;
      return (AnyAccumulator)tabulate_this.from(new View.Tabulate(n2$7, tabulate_tabulate_f), tabulate_tabulate_canAccumulate);
   }

   // $FF: synthetic method
   public static final Object $anonfun$tabulate$8(final Function5 f$4, final int i1$4, final int x$7, final int x$8, final int x$9, final int x$10) {
      return f$4.apply(BoxesRunTime.boxToInteger(i1$4), BoxesRunTime.boxToInteger(x$7), BoxesRunTime.boxToInteger(x$8), BoxesRunTime.boxToInteger(x$9), BoxesRunTime.boxToInteger(x$10));
   }

   // $FF: synthetic method
   public static final AnyAccumulator $anonfun$tabulate$7(final int n2$8, final int n3$6, final int n4$4, final int n5$2, final Function5 f$4, final Accumulator.AccumulatorFactoryShape canAccumulate$8, final int i1) {
      Accumulator$ var10000 = MODULE$;
      Function4 tabulate_f = (x$7, x$8, x$9, x$10) -> $anonfun$tabulate$8(f$4, i1, BoxesRunTime.unboxToInt(x$7), BoxesRunTime.unboxToInt(x$8), BoxesRunTime.unboxToInt(x$9), BoxesRunTime.unboxToInt(x$10));
      Accumulator$ tabulate_this = var10000;
      Function1 var13 = (i1x) -> $anonfun$tabulate$5(n3$6, n4$4, n5$2, tabulate_f, canAccumulate$8, BoxesRunTime.unboxToInt(i1x));
      Accumulator.AccumulatorFactoryShape$ tabulate_anyAccumulatorFactoryShape_this = Accumulator.AccumulatorFactoryShape$.MODULE$;
      Accumulator.AccumulatorFactoryShape var10001 = tabulate_anyAccumulatorFactoryShape_this.scala$jdk$Accumulator$LowPriorityAccumulatorFactoryShape$$anyAccumulatorFactoryShapePrototype();
      Object var12 = null;
      Accumulator.AccumulatorFactoryShape tabulate_tabulate_canAccumulate = var10001;
      Function1 tabulate_tabulate_f = var13;
      return (AnyAccumulator)tabulate_this.from(new View.Tabulate(n2$8, tabulate_tabulate_f), tabulate_tabulate_canAccumulate);
   }

   private Accumulator$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
