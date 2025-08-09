package scala.util.control;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Function0;
import scala.Function1;
import scala.None$;
import scala.Option;
import scala.PartialFunction;
import scala.Some;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.Seq;
import scala.reflect.ClassTag;
import scala.reflect.ClassTag$;
import scala.reflect.package$;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

public final class Exception$ {
   public static final Exception$ MODULE$ = new Exception$();
   private static final PartialFunction nothingCatcher;
   private static final Exception.Catch noCatch;

   static {
      Exception$ var10000 = MODULE$;
      Function1 var6 = (x$5) -> BoxesRunTime.boxToBoolean($anonfun$nothingCatcher$1(x$5));
      Function1 mkThrowableCatcher_f = (x$6) -> {
         throw x$6;
      };
      Function1 mkThrowableCatcher_isDef = var6;
      ClassTag mkThrowableCatcher_mkCatcher_evidence$1 = ClassTag$.MODULE$.apply(Throwable.class);
      PartialFunction var7 = new PartialFunction(mkThrowableCatcher_mkCatcher_evidence$1, mkThrowableCatcher_isDef, mkThrowableCatcher_f) {
         private final ClassTag evidence$1$1;
         private final Function1 isDef$1;
         private final Function1 f$1;

         public Option unapply(final Object a) {
            return PartialFunction.unapply$(this, a);
         }

         public PartialFunction elementWise() {
            return PartialFunction.elementWise$(this);
         }

         public PartialFunction orElse(final PartialFunction that) {
            return PartialFunction.orElse$(this, that);
         }

         public PartialFunction andThen(final Function1 k) {
            return PartialFunction.andThen$(this, (Function1)k);
         }

         public PartialFunction andThen(final PartialFunction k) {
            return PartialFunction.andThen$(this, (PartialFunction)k);
         }

         public PartialFunction compose(final PartialFunction k) {
            return PartialFunction.compose$(this, k);
         }

         public Function1 lift() {
            return PartialFunction.lift$(this);
         }

         public Object applyOrElse(final Object x, final Function1 default) {
            return PartialFunction.applyOrElse$(this, x, default);
         }

         public Function1 runWith(final Function1 action) {
            return PartialFunction.runWith$(this, action);
         }

         public boolean apply$mcZD$sp(final double v1) {
            return Function1.apply$mcZD$sp$(this, v1);
         }

         public double apply$mcDD$sp(final double v1) {
            return Function1.apply$mcDD$sp$(this, v1);
         }

         public float apply$mcFD$sp(final double v1) {
            return Function1.apply$mcFD$sp$(this, v1);
         }

         public int apply$mcID$sp(final double v1) {
            return Function1.apply$mcID$sp$(this, v1);
         }

         public long apply$mcJD$sp(final double v1) {
            return Function1.apply$mcJD$sp$(this, v1);
         }

         public void apply$mcVD$sp(final double v1) {
            Function1.apply$mcVD$sp$(this, v1);
         }

         public boolean apply$mcZF$sp(final float v1) {
            return Function1.apply$mcZF$sp$(this, v1);
         }

         public double apply$mcDF$sp(final float v1) {
            return Function1.apply$mcDF$sp$(this, v1);
         }

         public float apply$mcFF$sp(final float v1) {
            return Function1.apply$mcFF$sp$(this, v1);
         }

         public int apply$mcIF$sp(final float v1) {
            return Function1.apply$mcIF$sp$(this, v1);
         }

         public long apply$mcJF$sp(final float v1) {
            return Function1.apply$mcJF$sp$(this, v1);
         }

         public void apply$mcVF$sp(final float v1) {
            Function1.apply$mcVF$sp$(this, v1);
         }

         public boolean apply$mcZI$sp(final int v1) {
            return Function1.apply$mcZI$sp$(this, v1);
         }

         public double apply$mcDI$sp(final int v1) {
            return Function1.apply$mcDI$sp$(this, v1);
         }

         public float apply$mcFI$sp(final int v1) {
            return Function1.apply$mcFI$sp$(this, v1);
         }

         public int apply$mcII$sp(final int v1) {
            return Function1.apply$mcII$sp$(this, v1);
         }

         public long apply$mcJI$sp(final int v1) {
            return Function1.apply$mcJI$sp$(this, v1);
         }

         public void apply$mcVI$sp(final int v1) {
            Function1.apply$mcVI$sp$(this, v1);
         }

         public boolean apply$mcZJ$sp(final long v1) {
            return Function1.apply$mcZJ$sp$(this, v1);
         }

         public double apply$mcDJ$sp(final long v1) {
            return Function1.apply$mcDJ$sp$(this, v1);
         }

         public float apply$mcFJ$sp(final long v1) {
            return Function1.apply$mcFJ$sp$(this, v1);
         }

         public int apply$mcIJ$sp(final long v1) {
            return Function1.apply$mcIJ$sp$(this, v1);
         }

         public long apply$mcJJ$sp(final long v1) {
            return Function1.apply$mcJJ$sp$(this, v1);
         }

         public void apply$mcVJ$sp(final long v1) {
            Function1.apply$mcVJ$sp$(this, v1);
         }

         public Function1 compose(final Function1 g) {
            return Function1.compose$(this, g);
         }

         public String toString() {
            return Function1.toString$(this);
         }

         private Option downcast(final Throwable x) {
            package$ var10000 = package$.MODULE$;
            return (Option)(this.evidence$1$1.runtimeClass().isAssignableFrom(x.getClass()) ? new Some(x) : None$.MODULE$);
         }

         public boolean isDefinedAt(final Throwable x) {
            Option var10000 = this.downcast(x);
            Function1 exists_p = this.isDef$1;
            if (var10000 == null) {
               throw null;
            } else {
               Option exists_this = var10000;
               return !exists_this.isEmpty() && BoxesRunTime.unboxToBoolean(exists_p.apply(exists_this.get()));
            }
         }

         public Object apply(final Throwable x) {
            return this.f$1.apply(this.downcast(x).get());
         }

         public {
            this.evidence$1$1 = evidence$1$1;
            this.isDef$1 = isDef$1;
            this.f$1 = f$1;
         }
      };
      mkThrowableCatcher_mkCatcher_evidence$1 = null;
      mkThrowableCatcher_isDef = null;
      mkThrowableCatcher_f = null;
      nothingCatcher = var7;
      PartialFunction var10002 = MODULE$.nothingCatcher();
      Exception.Catch$ var10003 = Exception.Catch$.MODULE$;
      noCatch = (Exception.Catch)(new Exception.Catch(var10002, None$.MODULE$, Exception.Catch$.MODULE$.$lessinit$greater$default$3())).withDesc("<nothing>");
   }

   public PartialFunction mkCatcher(final Function1 isDef, final Function1 f, final ClassTag evidence$1) {
      return new PartialFunction(evidence$1, isDef, f) {
         private final ClassTag evidence$1$1;
         private final Function1 isDef$1;
         private final Function1 f$1;

         public Option unapply(final Object a) {
            return PartialFunction.unapply$(this, a);
         }

         public PartialFunction elementWise() {
            return PartialFunction.elementWise$(this);
         }

         public PartialFunction orElse(final PartialFunction that) {
            return PartialFunction.orElse$(this, that);
         }

         public PartialFunction andThen(final Function1 k) {
            return PartialFunction.andThen$(this, (Function1)k);
         }

         public PartialFunction andThen(final PartialFunction k) {
            return PartialFunction.andThen$(this, (PartialFunction)k);
         }

         public PartialFunction compose(final PartialFunction k) {
            return PartialFunction.compose$(this, k);
         }

         public Function1 lift() {
            return PartialFunction.lift$(this);
         }

         public Object applyOrElse(final Object x, final Function1 default) {
            return PartialFunction.applyOrElse$(this, x, default);
         }

         public Function1 runWith(final Function1 action) {
            return PartialFunction.runWith$(this, action);
         }

         public boolean apply$mcZD$sp(final double v1) {
            return Function1.apply$mcZD$sp$(this, v1);
         }

         public double apply$mcDD$sp(final double v1) {
            return Function1.apply$mcDD$sp$(this, v1);
         }

         public float apply$mcFD$sp(final double v1) {
            return Function1.apply$mcFD$sp$(this, v1);
         }

         public int apply$mcID$sp(final double v1) {
            return Function1.apply$mcID$sp$(this, v1);
         }

         public long apply$mcJD$sp(final double v1) {
            return Function1.apply$mcJD$sp$(this, v1);
         }

         public void apply$mcVD$sp(final double v1) {
            Function1.apply$mcVD$sp$(this, v1);
         }

         public boolean apply$mcZF$sp(final float v1) {
            return Function1.apply$mcZF$sp$(this, v1);
         }

         public double apply$mcDF$sp(final float v1) {
            return Function1.apply$mcDF$sp$(this, v1);
         }

         public float apply$mcFF$sp(final float v1) {
            return Function1.apply$mcFF$sp$(this, v1);
         }

         public int apply$mcIF$sp(final float v1) {
            return Function1.apply$mcIF$sp$(this, v1);
         }

         public long apply$mcJF$sp(final float v1) {
            return Function1.apply$mcJF$sp$(this, v1);
         }

         public void apply$mcVF$sp(final float v1) {
            Function1.apply$mcVF$sp$(this, v1);
         }

         public boolean apply$mcZI$sp(final int v1) {
            return Function1.apply$mcZI$sp$(this, v1);
         }

         public double apply$mcDI$sp(final int v1) {
            return Function1.apply$mcDI$sp$(this, v1);
         }

         public float apply$mcFI$sp(final int v1) {
            return Function1.apply$mcFI$sp$(this, v1);
         }

         public int apply$mcII$sp(final int v1) {
            return Function1.apply$mcII$sp$(this, v1);
         }

         public long apply$mcJI$sp(final int v1) {
            return Function1.apply$mcJI$sp$(this, v1);
         }

         public void apply$mcVI$sp(final int v1) {
            Function1.apply$mcVI$sp$(this, v1);
         }

         public boolean apply$mcZJ$sp(final long v1) {
            return Function1.apply$mcZJ$sp$(this, v1);
         }

         public double apply$mcDJ$sp(final long v1) {
            return Function1.apply$mcDJ$sp$(this, v1);
         }

         public float apply$mcFJ$sp(final long v1) {
            return Function1.apply$mcFJ$sp$(this, v1);
         }

         public int apply$mcIJ$sp(final long v1) {
            return Function1.apply$mcIJ$sp$(this, v1);
         }

         public long apply$mcJJ$sp(final long v1) {
            return Function1.apply$mcJJ$sp$(this, v1);
         }

         public void apply$mcVJ$sp(final long v1) {
            Function1.apply$mcVJ$sp$(this, v1);
         }

         public Function1 compose(final Function1 g) {
            return Function1.compose$(this, g);
         }

         public String toString() {
            return Function1.toString$(this);
         }

         private Option downcast(final Throwable x) {
            package$ var10000 = package$.MODULE$;
            return (Option)(this.evidence$1$1.runtimeClass().isAssignableFrom(x.getClass()) ? new Some(x) : None$.MODULE$);
         }

         public boolean isDefinedAt(final Throwable x) {
            Option var10000 = this.downcast(x);
            Function1 exists_p = this.isDef$1;
            if (var10000 == null) {
               throw null;
            } else {
               Option exists_this = var10000;
               return !exists_this.isEmpty() && BoxesRunTime.unboxToBoolean(exists_p.apply(exists_this.get()));
            }
         }

         public Object apply(final Throwable x) {
            return this.f$1.apply(this.downcast(x).get());
         }

         public {
            this.evidence$1$1 = evidence$1$1;
            this.isDef$1 = isDef$1;
            this.f$1 = f$1;
         }
      };
   }

   public PartialFunction mkThrowableCatcher(final Function1 isDef, final Function1 f) {
      ClassTag mkCatcher_evidence$1 = ClassTag$.MODULE$.apply(Throwable.class);
      return new PartialFunction(mkCatcher_evidence$1, isDef, f) {
         private final ClassTag evidence$1$1;
         private final Function1 isDef$1;
         private final Function1 f$1;

         public Option unapply(final Object a) {
            return PartialFunction.unapply$(this, a);
         }

         public PartialFunction elementWise() {
            return PartialFunction.elementWise$(this);
         }

         public PartialFunction orElse(final PartialFunction that) {
            return PartialFunction.orElse$(this, that);
         }

         public PartialFunction andThen(final Function1 k) {
            return PartialFunction.andThen$(this, (Function1)k);
         }

         public PartialFunction andThen(final PartialFunction k) {
            return PartialFunction.andThen$(this, (PartialFunction)k);
         }

         public PartialFunction compose(final PartialFunction k) {
            return PartialFunction.compose$(this, k);
         }

         public Function1 lift() {
            return PartialFunction.lift$(this);
         }

         public Object applyOrElse(final Object x, final Function1 default) {
            return PartialFunction.applyOrElse$(this, x, default);
         }

         public Function1 runWith(final Function1 action) {
            return PartialFunction.runWith$(this, action);
         }

         public boolean apply$mcZD$sp(final double v1) {
            return Function1.apply$mcZD$sp$(this, v1);
         }

         public double apply$mcDD$sp(final double v1) {
            return Function1.apply$mcDD$sp$(this, v1);
         }

         public float apply$mcFD$sp(final double v1) {
            return Function1.apply$mcFD$sp$(this, v1);
         }

         public int apply$mcID$sp(final double v1) {
            return Function1.apply$mcID$sp$(this, v1);
         }

         public long apply$mcJD$sp(final double v1) {
            return Function1.apply$mcJD$sp$(this, v1);
         }

         public void apply$mcVD$sp(final double v1) {
            Function1.apply$mcVD$sp$(this, v1);
         }

         public boolean apply$mcZF$sp(final float v1) {
            return Function1.apply$mcZF$sp$(this, v1);
         }

         public double apply$mcDF$sp(final float v1) {
            return Function1.apply$mcDF$sp$(this, v1);
         }

         public float apply$mcFF$sp(final float v1) {
            return Function1.apply$mcFF$sp$(this, v1);
         }

         public int apply$mcIF$sp(final float v1) {
            return Function1.apply$mcIF$sp$(this, v1);
         }

         public long apply$mcJF$sp(final float v1) {
            return Function1.apply$mcJF$sp$(this, v1);
         }

         public void apply$mcVF$sp(final float v1) {
            Function1.apply$mcVF$sp$(this, v1);
         }

         public boolean apply$mcZI$sp(final int v1) {
            return Function1.apply$mcZI$sp$(this, v1);
         }

         public double apply$mcDI$sp(final int v1) {
            return Function1.apply$mcDI$sp$(this, v1);
         }

         public float apply$mcFI$sp(final int v1) {
            return Function1.apply$mcFI$sp$(this, v1);
         }

         public int apply$mcII$sp(final int v1) {
            return Function1.apply$mcII$sp$(this, v1);
         }

         public long apply$mcJI$sp(final int v1) {
            return Function1.apply$mcJI$sp$(this, v1);
         }

         public void apply$mcVI$sp(final int v1) {
            Function1.apply$mcVI$sp$(this, v1);
         }

         public boolean apply$mcZJ$sp(final long v1) {
            return Function1.apply$mcZJ$sp$(this, v1);
         }

         public double apply$mcDJ$sp(final long v1) {
            return Function1.apply$mcDJ$sp$(this, v1);
         }

         public float apply$mcFJ$sp(final long v1) {
            return Function1.apply$mcFJ$sp$(this, v1);
         }

         public int apply$mcIJ$sp(final long v1) {
            return Function1.apply$mcIJ$sp$(this, v1);
         }

         public long apply$mcJJ$sp(final long v1) {
            return Function1.apply$mcJJ$sp$(this, v1);
         }

         public void apply$mcVJ$sp(final long v1) {
            Function1.apply$mcVJ$sp$(this, v1);
         }

         public Function1 compose(final Function1 g) {
            return Function1.compose$(this, g);
         }

         public String toString() {
            return Function1.toString$(this);
         }

         private Option downcast(final Throwable x) {
            package$ var10000 = package$.MODULE$;
            return (Option)(this.evidence$1$1.runtimeClass().isAssignableFrom(x.getClass()) ? new Some(x) : None$.MODULE$);
         }

         public boolean isDefinedAt(final Throwable x) {
            Option var10000 = this.downcast(x);
            Function1 exists_p = this.isDef$1;
            if (var10000 == null) {
               throw null;
            } else {
               Option exists_this = var10000;
               return !exists_this.isEmpty() && BoxesRunTime.unboxToBoolean(exists_p.apply(exists_this.get()));
            }
         }

         public Object apply(final Throwable x) {
            return this.f$1.apply(this.downcast(x).get());
         }

         public {
            this.evidence$1$1 = evidence$1$1;
            this.isDef$1 = isDef$1;
            this.f$1 = f$1;
         }
      };
   }

   public PartialFunction throwableSubtypeToCatcher(final PartialFunction pf, final ClassTag evidence$2) {
      Function1 var10000 = (x) -> BoxesRunTime.boxToBoolean($anonfun$throwableSubtypeToCatcher$1(pf, x));
      Function1 mkCatcher_f = (v1) -> pf.apply(v1);
      Function1 mkCatcher_isDef = var10000;
      return new PartialFunction(evidence$2, mkCatcher_isDef, mkCatcher_f) {
         private final ClassTag evidence$1$1;
         private final Function1 isDef$1;
         private final Function1 f$1;

         public Option unapply(final Object a) {
            return PartialFunction.unapply$(this, a);
         }

         public PartialFunction elementWise() {
            return PartialFunction.elementWise$(this);
         }

         public PartialFunction orElse(final PartialFunction that) {
            return PartialFunction.orElse$(this, that);
         }

         public PartialFunction andThen(final Function1 k) {
            return PartialFunction.andThen$(this, (Function1)k);
         }

         public PartialFunction andThen(final PartialFunction k) {
            return PartialFunction.andThen$(this, (PartialFunction)k);
         }

         public PartialFunction compose(final PartialFunction k) {
            return PartialFunction.compose$(this, k);
         }

         public Function1 lift() {
            return PartialFunction.lift$(this);
         }

         public Object applyOrElse(final Object x, final Function1 default) {
            return PartialFunction.applyOrElse$(this, x, default);
         }

         public Function1 runWith(final Function1 action) {
            return PartialFunction.runWith$(this, action);
         }

         public boolean apply$mcZD$sp(final double v1) {
            return Function1.apply$mcZD$sp$(this, v1);
         }

         public double apply$mcDD$sp(final double v1) {
            return Function1.apply$mcDD$sp$(this, v1);
         }

         public float apply$mcFD$sp(final double v1) {
            return Function1.apply$mcFD$sp$(this, v1);
         }

         public int apply$mcID$sp(final double v1) {
            return Function1.apply$mcID$sp$(this, v1);
         }

         public long apply$mcJD$sp(final double v1) {
            return Function1.apply$mcJD$sp$(this, v1);
         }

         public void apply$mcVD$sp(final double v1) {
            Function1.apply$mcVD$sp$(this, v1);
         }

         public boolean apply$mcZF$sp(final float v1) {
            return Function1.apply$mcZF$sp$(this, v1);
         }

         public double apply$mcDF$sp(final float v1) {
            return Function1.apply$mcDF$sp$(this, v1);
         }

         public float apply$mcFF$sp(final float v1) {
            return Function1.apply$mcFF$sp$(this, v1);
         }

         public int apply$mcIF$sp(final float v1) {
            return Function1.apply$mcIF$sp$(this, v1);
         }

         public long apply$mcJF$sp(final float v1) {
            return Function1.apply$mcJF$sp$(this, v1);
         }

         public void apply$mcVF$sp(final float v1) {
            Function1.apply$mcVF$sp$(this, v1);
         }

         public boolean apply$mcZI$sp(final int v1) {
            return Function1.apply$mcZI$sp$(this, v1);
         }

         public double apply$mcDI$sp(final int v1) {
            return Function1.apply$mcDI$sp$(this, v1);
         }

         public float apply$mcFI$sp(final int v1) {
            return Function1.apply$mcFI$sp$(this, v1);
         }

         public int apply$mcII$sp(final int v1) {
            return Function1.apply$mcII$sp$(this, v1);
         }

         public long apply$mcJI$sp(final int v1) {
            return Function1.apply$mcJI$sp$(this, v1);
         }

         public void apply$mcVI$sp(final int v1) {
            Function1.apply$mcVI$sp$(this, v1);
         }

         public boolean apply$mcZJ$sp(final long v1) {
            return Function1.apply$mcZJ$sp$(this, v1);
         }

         public double apply$mcDJ$sp(final long v1) {
            return Function1.apply$mcDJ$sp$(this, v1);
         }

         public float apply$mcFJ$sp(final long v1) {
            return Function1.apply$mcFJ$sp$(this, v1);
         }

         public int apply$mcIJ$sp(final long v1) {
            return Function1.apply$mcIJ$sp$(this, v1);
         }

         public long apply$mcJJ$sp(final long v1) {
            return Function1.apply$mcJJ$sp$(this, v1);
         }

         public void apply$mcVJ$sp(final long v1) {
            Function1.apply$mcVJ$sp$(this, v1);
         }

         public Function1 compose(final Function1 g) {
            return Function1.compose$(this, g);
         }

         public String toString() {
            return Function1.toString$(this);
         }

         private Option downcast(final Throwable x) {
            package$ var10000 = package$.MODULE$;
            return (Option)(this.evidence$1$1.runtimeClass().isAssignableFrom(x.getClass()) ? new Some(x) : None$.MODULE$);
         }

         public boolean isDefinedAt(final Throwable x) {
            Option var10000 = this.downcast(x);
            Function1 exists_p = this.isDef$1;
            if (var10000 == null) {
               throw null;
            } else {
               Option exists_this = var10000;
               return !exists_this.isEmpty() && BoxesRunTime.unboxToBoolean(exists_p.apply(exists_this.get()));
            }
         }

         public Object apply(final Throwable x) {
            return this.f$1.apply(this.downcast(x).get());
         }

         public {
            this.evidence$1$1 = evidence$1$1;
            this.isDef$1 = isDef$1;
            this.f$1 = f$1;
         }
      };
   }

   public boolean shouldRethrow(final Throwable x) {
      if (x instanceof ControlThrowable) {
         return true;
      } else {
         return x instanceof InterruptedException;
      }
   }

   public final PartialFunction nothingCatcher() {
      return nothingCatcher;
   }

   public final PartialFunction nonFatalCatcher() {
      Function1 var10000 = (x0$1) -> BoxesRunTime.boxToBoolean($anonfun$nonFatalCatcher$1(x0$1));
      Function1 mkThrowableCatcher_f = (x$7) -> {
         throw x$7;
      };
      Function1 mkThrowableCatcher_isDef = var10000;
      ClassTag mkThrowableCatcher_mkCatcher_evidence$1 = ClassTag$.MODULE$.apply(Throwable.class);
      return new PartialFunction(mkThrowableCatcher_mkCatcher_evidence$1, mkThrowableCatcher_isDef, mkThrowableCatcher_f) {
         private final ClassTag evidence$1$1;
         private final Function1 isDef$1;
         private final Function1 f$1;

         public Option unapply(final Object a) {
            return PartialFunction.unapply$(this, a);
         }

         public PartialFunction elementWise() {
            return PartialFunction.elementWise$(this);
         }

         public PartialFunction orElse(final PartialFunction that) {
            return PartialFunction.orElse$(this, that);
         }

         public PartialFunction andThen(final Function1 k) {
            return PartialFunction.andThen$(this, (Function1)k);
         }

         public PartialFunction andThen(final PartialFunction k) {
            return PartialFunction.andThen$(this, (PartialFunction)k);
         }

         public PartialFunction compose(final PartialFunction k) {
            return PartialFunction.compose$(this, k);
         }

         public Function1 lift() {
            return PartialFunction.lift$(this);
         }

         public Object applyOrElse(final Object x, final Function1 default) {
            return PartialFunction.applyOrElse$(this, x, default);
         }

         public Function1 runWith(final Function1 action) {
            return PartialFunction.runWith$(this, action);
         }

         public boolean apply$mcZD$sp(final double v1) {
            return Function1.apply$mcZD$sp$(this, v1);
         }

         public double apply$mcDD$sp(final double v1) {
            return Function1.apply$mcDD$sp$(this, v1);
         }

         public float apply$mcFD$sp(final double v1) {
            return Function1.apply$mcFD$sp$(this, v1);
         }

         public int apply$mcID$sp(final double v1) {
            return Function1.apply$mcID$sp$(this, v1);
         }

         public long apply$mcJD$sp(final double v1) {
            return Function1.apply$mcJD$sp$(this, v1);
         }

         public void apply$mcVD$sp(final double v1) {
            Function1.apply$mcVD$sp$(this, v1);
         }

         public boolean apply$mcZF$sp(final float v1) {
            return Function1.apply$mcZF$sp$(this, v1);
         }

         public double apply$mcDF$sp(final float v1) {
            return Function1.apply$mcDF$sp$(this, v1);
         }

         public float apply$mcFF$sp(final float v1) {
            return Function1.apply$mcFF$sp$(this, v1);
         }

         public int apply$mcIF$sp(final float v1) {
            return Function1.apply$mcIF$sp$(this, v1);
         }

         public long apply$mcJF$sp(final float v1) {
            return Function1.apply$mcJF$sp$(this, v1);
         }

         public void apply$mcVF$sp(final float v1) {
            Function1.apply$mcVF$sp$(this, v1);
         }

         public boolean apply$mcZI$sp(final int v1) {
            return Function1.apply$mcZI$sp$(this, v1);
         }

         public double apply$mcDI$sp(final int v1) {
            return Function1.apply$mcDI$sp$(this, v1);
         }

         public float apply$mcFI$sp(final int v1) {
            return Function1.apply$mcFI$sp$(this, v1);
         }

         public int apply$mcII$sp(final int v1) {
            return Function1.apply$mcII$sp$(this, v1);
         }

         public long apply$mcJI$sp(final int v1) {
            return Function1.apply$mcJI$sp$(this, v1);
         }

         public void apply$mcVI$sp(final int v1) {
            Function1.apply$mcVI$sp$(this, v1);
         }

         public boolean apply$mcZJ$sp(final long v1) {
            return Function1.apply$mcZJ$sp$(this, v1);
         }

         public double apply$mcDJ$sp(final long v1) {
            return Function1.apply$mcDJ$sp$(this, v1);
         }

         public float apply$mcFJ$sp(final long v1) {
            return Function1.apply$mcFJ$sp$(this, v1);
         }

         public int apply$mcIJ$sp(final long v1) {
            return Function1.apply$mcIJ$sp$(this, v1);
         }

         public long apply$mcJJ$sp(final long v1) {
            return Function1.apply$mcJJ$sp$(this, v1);
         }

         public void apply$mcVJ$sp(final long v1) {
            Function1.apply$mcVJ$sp$(this, v1);
         }

         public Function1 compose(final Function1 g) {
            return Function1.compose$(this, g);
         }

         public String toString() {
            return Function1.toString$(this);
         }

         private Option downcast(final Throwable x) {
            package$ var10000 = package$.MODULE$;
            return (Option)(this.evidence$1$1.runtimeClass().isAssignableFrom(x.getClass()) ? new Some(x) : None$.MODULE$);
         }

         public boolean isDefinedAt(final Throwable x) {
            Option var10000 = this.downcast(x);
            Function1 exists_p = this.isDef$1;
            if (var10000 == null) {
               throw null;
            } else {
               Option exists_this = var10000;
               return !exists_this.isEmpty() && BoxesRunTime.unboxToBoolean(exists_p.apply(exists_this.get()));
            }
         }

         public Object apply(final Throwable x) {
            return this.f$1.apply(this.downcast(x).get());
         }

         public {
            this.evidence$1$1 = evidence$1$1;
            this.isDef$1 = isDef$1;
            this.f$1 = f$1;
         }
      };
   }

   public final PartialFunction allCatcher() {
      Function1 var10000 = (x$8) -> BoxesRunTime.boxToBoolean($anonfun$allCatcher$1(x$8));
      Function1 mkThrowableCatcher_f = (x$9) -> {
         throw x$9;
      };
      Function1 mkThrowableCatcher_isDef = var10000;
      ClassTag mkThrowableCatcher_mkCatcher_evidence$1 = ClassTag$.MODULE$.apply(Throwable.class);
      return new PartialFunction(mkThrowableCatcher_mkCatcher_evidence$1, mkThrowableCatcher_isDef, mkThrowableCatcher_f) {
         private final ClassTag evidence$1$1;
         private final Function1 isDef$1;
         private final Function1 f$1;

         public Option unapply(final Object a) {
            return PartialFunction.unapply$(this, a);
         }

         public PartialFunction elementWise() {
            return PartialFunction.elementWise$(this);
         }

         public PartialFunction orElse(final PartialFunction that) {
            return PartialFunction.orElse$(this, that);
         }

         public PartialFunction andThen(final Function1 k) {
            return PartialFunction.andThen$(this, (Function1)k);
         }

         public PartialFunction andThen(final PartialFunction k) {
            return PartialFunction.andThen$(this, (PartialFunction)k);
         }

         public PartialFunction compose(final PartialFunction k) {
            return PartialFunction.compose$(this, k);
         }

         public Function1 lift() {
            return PartialFunction.lift$(this);
         }

         public Object applyOrElse(final Object x, final Function1 default) {
            return PartialFunction.applyOrElse$(this, x, default);
         }

         public Function1 runWith(final Function1 action) {
            return PartialFunction.runWith$(this, action);
         }

         public boolean apply$mcZD$sp(final double v1) {
            return Function1.apply$mcZD$sp$(this, v1);
         }

         public double apply$mcDD$sp(final double v1) {
            return Function1.apply$mcDD$sp$(this, v1);
         }

         public float apply$mcFD$sp(final double v1) {
            return Function1.apply$mcFD$sp$(this, v1);
         }

         public int apply$mcID$sp(final double v1) {
            return Function1.apply$mcID$sp$(this, v1);
         }

         public long apply$mcJD$sp(final double v1) {
            return Function1.apply$mcJD$sp$(this, v1);
         }

         public void apply$mcVD$sp(final double v1) {
            Function1.apply$mcVD$sp$(this, v1);
         }

         public boolean apply$mcZF$sp(final float v1) {
            return Function1.apply$mcZF$sp$(this, v1);
         }

         public double apply$mcDF$sp(final float v1) {
            return Function1.apply$mcDF$sp$(this, v1);
         }

         public float apply$mcFF$sp(final float v1) {
            return Function1.apply$mcFF$sp$(this, v1);
         }

         public int apply$mcIF$sp(final float v1) {
            return Function1.apply$mcIF$sp$(this, v1);
         }

         public long apply$mcJF$sp(final float v1) {
            return Function1.apply$mcJF$sp$(this, v1);
         }

         public void apply$mcVF$sp(final float v1) {
            Function1.apply$mcVF$sp$(this, v1);
         }

         public boolean apply$mcZI$sp(final int v1) {
            return Function1.apply$mcZI$sp$(this, v1);
         }

         public double apply$mcDI$sp(final int v1) {
            return Function1.apply$mcDI$sp$(this, v1);
         }

         public float apply$mcFI$sp(final int v1) {
            return Function1.apply$mcFI$sp$(this, v1);
         }

         public int apply$mcII$sp(final int v1) {
            return Function1.apply$mcII$sp$(this, v1);
         }

         public long apply$mcJI$sp(final int v1) {
            return Function1.apply$mcJI$sp$(this, v1);
         }

         public void apply$mcVI$sp(final int v1) {
            Function1.apply$mcVI$sp$(this, v1);
         }

         public boolean apply$mcZJ$sp(final long v1) {
            return Function1.apply$mcZJ$sp$(this, v1);
         }

         public double apply$mcDJ$sp(final long v1) {
            return Function1.apply$mcDJ$sp$(this, v1);
         }

         public float apply$mcFJ$sp(final long v1) {
            return Function1.apply$mcFJ$sp$(this, v1);
         }

         public int apply$mcIJ$sp(final long v1) {
            return Function1.apply$mcIJ$sp$(this, v1);
         }

         public long apply$mcJJ$sp(final long v1) {
            return Function1.apply$mcJJ$sp$(this, v1);
         }

         public void apply$mcVJ$sp(final long v1) {
            Function1.apply$mcVJ$sp$(this, v1);
         }

         public Function1 compose(final Function1 g) {
            return Function1.compose$(this, g);
         }

         public String toString() {
            return Function1.toString$(this);
         }

         private Option downcast(final Throwable x) {
            package$ var10000 = package$.MODULE$;
            return (Option)(this.evidence$1$1.runtimeClass().isAssignableFrom(x.getClass()) ? new Some(x) : None$.MODULE$);
         }

         public boolean isDefinedAt(final Throwable x) {
            Option var10000 = this.downcast(x);
            Function1 exists_p = this.isDef$1;
            if (var10000 == null) {
               throw null;
            } else {
               Option exists_this = var10000;
               return !exists_this.isEmpty() && BoxesRunTime.unboxToBoolean(exists_p.apply(exists_this.get()));
            }
         }

         public Object apply(final Throwable x) {
            return this.f$1.apply(this.downcast(x).get());
         }

         public {
            this.evidence$1$1 = evidence$1$1;
            this.isDef$1 = isDef$1;
            this.f$1 = f$1;
         }
      };
   }

   public final Exception.Catch noCatch() {
      return noCatch;
   }

   public final Exception.Catch allCatch() {
      PartialFunction var10002 = this.allCatcher();
      Exception.Catch$ var10003 = Exception.Catch$.MODULE$;
      return (Exception.Catch)(new Exception.Catch(var10002, None$.MODULE$, Exception.Catch$.MODULE$.$lessinit$greater$default$3())).withDesc("<everything>");
   }

   public final Exception.Catch nonFatalCatch() {
      PartialFunction var10002 = this.nonFatalCatcher();
      Exception.Catch$ var10003 = Exception.Catch$.MODULE$;
      return (Exception.Catch)(new Exception.Catch(var10002, None$.MODULE$, Exception.Catch$.MODULE$.$lessinit$greater$default$3())).withDesc("<non-fatal>");
   }

   public Exception.Catch catching(final Seq exceptions) {
      Serializable var10002 = new Serializable(exceptions) {
         private static final long serialVersionUID = 0L;
         private final Seq exceptions$3;

         public final Object applyOrElse(final Throwable x1, final Function1 default) {
            if (Exception$.MODULE$.scala$util$control$Exception$$wouldMatch(x1, this.exceptions$3)) {
               throw x1;
            } else {
               return default.apply(x1);
            }
         }

         public final boolean isDefinedAt(final Throwable x1) {
            return Exception$.MODULE$.scala$util$control$Exception$$wouldMatch(x1, this.exceptions$3);
         }

         public {
            this.exceptions$3 = exceptions$3;
         }
      };
      Exception.Catch$ var10003 = Exception.Catch$.MODULE$;
      Exception.Catch var10000 = new Exception.Catch(var10002, None$.MODULE$, Exception.Catch$.MODULE$.$lessinit$greater$default$3());
      IterableOnceOps var10001 = (IterableOnceOps)exceptions.map((x$10) -> x$10.getName());
      String mkString_sep = ", ";
      if (var10001 == null) {
         throw null;
      } else {
         String var4 = var10001.mkString("", mkString_sep, "");
         Object var3 = null;
         return (Exception.Catch)var10000.withDesc(var4);
      }
   }

   public Exception.Catch catching(final PartialFunction c) {
      Exception.Catch$ var10003 = Exception.Catch$.MODULE$;
      return new Exception.Catch(c, None$.MODULE$, Exception.Catch$.MODULE$.$lessinit$greater$default$3());
   }

   public Exception.Catch catchingPromiscuously(final Seq exceptions) {
      return this.catchingPromiscuously((PartialFunction)(new Serializable(exceptions) {
         private static final long serialVersionUID = 0L;
         private final Seq exceptions$3;

         public final Object applyOrElse(final Throwable x1, final Function1 default) {
            if (Exception$.MODULE$.scala$util$control$Exception$$wouldMatch(x1, this.exceptions$3)) {
               throw x1;
            } else {
               return default.apply(x1);
            }
         }

         public final boolean isDefinedAt(final Throwable x1) {
            return Exception$.MODULE$.scala$util$control$Exception$$wouldMatch(x1, this.exceptions$3);
         }

         public {
            this.exceptions$3 = exceptions$3;
         }
      }));
   }

   public Exception.Catch catchingPromiscuously(final PartialFunction c) {
      return new Exception.Catch(c, None$.MODULE$, (x$11) -> BoxesRunTime.boxToBoolean($anonfun$catchingPromiscuously$1(x$11)));
   }

   public Exception.Catch ignoring(final Seq exceptions) {
      return this.catching(exceptions).withApply((x$12) -> {
         $anonfun$ignoring$1(x$12);
         return BoxedUnit.UNIT;
      });
   }

   public Exception.Catch failing(final Seq exceptions) {
      return this.catching(exceptions).withApply((x$13) -> None$.MODULE$);
   }

   public Exception.Catch failAsValue(final Seq exceptions, final Function0 value) {
      return this.catching(exceptions).withApply((x$14) -> value.apply());
   }

   public Exception.By handling(final Seq exceptions) {
      return new Exception.By((f) -> this.fun$1(f, exceptions));
   }

   public Exception.Catch ultimately(final Function0 body) {
      return this.noCatch().andFinally(body);
   }

   public Exception.Catch unwrapping(final Seq exceptions) {
      return this.catching(exceptions).withApply((x) -> {
         throw this.unwrap$1(x, exceptions);
      });
   }

   public boolean scala$util$control$Exception$$wouldMatch(final Throwable x, final scala.collection.Seq classes) {
      return classes.exists((x$15) -> BoxesRunTime.boxToBoolean($anonfun$wouldMatch$1(x, x$15)));
   }

   private PartialFunction pfFromExceptions(final Seq exceptions) {
      return new Serializable(exceptions) {
         private static final long serialVersionUID = 0L;
         private final Seq exceptions$3;

         public final Object applyOrElse(final Throwable x1, final Function1 default) {
            if (Exception$.MODULE$.scala$util$control$Exception$$wouldMatch(x1, this.exceptions$3)) {
               throw x1;
            } else {
               return default.apply(x1);
            }
         }

         public final boolean isDefinedAt(final Throwable x1) {
            return Exception$.MODULE$.scala$util$control$Exception$$wouldMatch(x1, this.exceptions$3);
         }

         public {
            this.exceptions$3 = exceptions$3;
         }
      };
   }

   // $FF: synthetic method
   public static final boolean $anonfun$throwableSubtypeToCatcher$1(final PartialFunction pf$1, final Throwable x) {
      return pf$1.isDefinedAt(x);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$nothingCatcher$1(final Throwable x$5) {
      return false;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$nonFatalCatcher$1(final Throwable x0$1) {
      return x0$1 != null && !NonFatal$.MODULE$.unapply(x0$1).isEmpty();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$allCatcher$1(final Throwable x$8) {
      return true;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$catchingPromiscuously$1(final Throwable x$11) {
      return false;
   }

   // $FF: synthetic method
   public static final void $anonfun$ignoring$1(final Throwable x$12) {
   }

   private final Exception.Catch fun$1(final Function1 f, final Seq exceptions$1) {
      return this.catching(exceptions$1).withApply(f);
   }

   private final Throwable unwrap$1(final Throwable x, final Seq exceptions$2) {
      while(this.scala$util$control$Exception$$wouldMatch(x, exceptions$2) && x.getCause() != null) {
         x = x.getCause();
      }

      return x;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$wouldMatch$1(final Throwable x$16, final Class x$15) {
      return x$15.isAssignableFrom(x$16.getClass());
   }

   private Exception$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
