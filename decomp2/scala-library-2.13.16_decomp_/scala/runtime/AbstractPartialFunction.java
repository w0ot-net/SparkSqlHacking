package scala.runtime;

import scala.Function1;
import scala.Option;
import scala.PartialFunction;
import scala.PartialFunction$;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005a3Qa\u0001\u0003\u0002\u0002%AQ\u0001\u0015\u0001\u0005\u0002ECQ\u0001\u0016\u0001\u0005\u0002U\u0013q#\u00112tiJ\f7\r\u001e)beRL\u0017\r\u001c$v]\u000e$\u0018n\u001c8\u000b\u0005\u00151\u0011a\u0002:v]RLW.\u001a\u0006\u0002\u000f\u0005)1oY1mC\u000e\u0001Qc\u0001\u0006\u0015yM!\u0001aC\bN!\taQ\"D\u0001\u0007\u0013\tqaA\u0001\u0004B]f\u0014VM\u001a\t\u0005\u0019A\u00112(\u0003\u0002\u0012\r\tIa)\u001e8di&|g.\r\t\u0003'Qa\u0001\u0001B\u0005\u0016\u0001\u0001\u0006\t\u0011#b\u0001-\t\u0011A+M\t\u0003/i\u0001\"\u0001\u0004\r\n\u0005e1!a\u0002(pi\"Lgn\u001a\t\u0003\u0019mI!\u0001\b\u0004\u0003\u0007\u0005s\u0017\u0010K\u0002\u0015=\u0005\u0002\"\u0001D\u0010\n\u0005\u00012!aC:qK\u000eL\u0017\r\\5{K\u0012\fTa\t\u00129ue\u00022a\t\u0014*\u001d\taA%\u0003\u0002&\r\u0005i1\u000b]3dS\u0006d\u0017N_1cY\u0016L!a\n\u0015\u0003\u000b\u001d\u0013x.\u001e9\u000b\u0005\u00152\u0001C\u0002\u0007+Y=\u0012T'\u0003\u0002,\r\t1A+\u001e9mKR\u0002\"\u0001D\u0017\n\u000592!aA%oiB\u0011A\u0002M\u0005\u0003c\u0019\u0011A\u0001T8oOB\u0011AbM\u0005\u0003i\u0019\u0011QA\u00127pCR\u0004\"\u0001\u0004\u001c\n\u0005]2!A\u0002#pk\ndW-\u0003\u0002:Q\u0005\u0019\u0011I]42\t\u0011\u001aC%\n\t\u0003'q\"\u0011\"\u0010\u0001!\u0002\u0003%)\u0019\u0001\f\u0003\u0003IC3\u0001\u0010\u0010@c\u0015\u0019\u0003I\u0013'L!\r\u0019c%\u0011\t\t\u0019\tcsFM\u001bE\u000f&\u00111I\u0002\u0002\u0007)V\u0004H.\u001a\u001c\u0011\u00051)\u0015B\u0001$\u0007\u0005\u001d\u0011un\u001c7fC:\u0004\"\u0001\u0004%\n\u0005%3!\u0001B+oSRL!a\u0013\u0015\u0002\rI+G/\u001e:oc\u0011!3\u0005J\u0013\u0011\t1q%cO\u0005\u0003\u001f\u001a\u0011q\u0002U1si&\fGNR;oGRLwN\\\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003I\u0003Ba\u0015\u0001\u0013w5\tA!A\u0003baBd\u0017\u0010\u0006\u0002<-\")qK\u0001a\u0001%\u0005\t\u0001\u0010"
)
public abstract class AbstractPartialFunction implements PartialFunction {
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

   public Function1 compose(final Function1 g) {
      return Function1.compose$(this, g);
   }

   public String toString() {
      return Function1.toString$(this);
   }

   public Object apply(final Object x) {
      return this.applyOrElse(x, PartialFunction$.MODULE$.empty());
   }

   public boolean apply$mcZD$sp(final double x) {
      return BoxesRunTime.unboxToBoolean(this.apply(x));
   }

   public double apply$mcDD$sp(final double x) {
      return BoxesRunTime.unboxToDouble(this.apply(x));
   }

   public float apply$mcFD$sp(final double x) {
      return BoxesRunTime.unboxToFloat(this.apply(x));
   }

   public int apply$mcID$sp(final double x) {
      return BoxesRunTime.unboxToInt(this.apply(x));
   }

   public long apply$mcJD$sp(final double x) {
      return BoxesRunTime.unboxToLong(this.apply(x));
   }

   public void apply$mcVD$sp(final double x) {
      this.apply(x);
   }

   public boolean apply$mcZF$sp(final float x) {
      return BoxesRunTime.unboxToBoolean(this.apply(x));
   }

   public double apply$mcDF$sp(final float x) {
      return BoxesRunTime.unboxToDouble(this.apply(x));
   }

   public float apply$mcFF$sp(final float x) {
      return BoxesRunTime.unboxToFloat(this.apply(x));
   }

   public int apply$mcIF$sp(final float x) {
      return BoxesRunTime.unboxToInt(this.apply(x));
   }

   public long apply$mcJF$sp(final float x) {
      return BoxesRunTime.unboxToLong(this.apply(x));
   }

   public void apply$mcVF$sp(final float x) {
      this.apply(x);
   }

   public boolean apply$mcZI$sp(final int x) {
      return BoxesRunTime.unboxToBoolean(this.apply(x));
   }

   public double apply$mcDI$sp(final int x) {
      return BoxesRunTime.unboxToDouble(this.apply(x));
   }

   public float apply$mcFI$sp(final int x) {
      return BoxesRunTime.unboxToFloat(this.apply(x));
   }

   public int apply$mcII$sp(final int x) {
      return BoxesRunTime.unboxToInt(this.apply(x));
   }

   public long apply$mcJI$sp(final int x) {
      return BoxesRunTime.unboxToLong(this.apply(x));
   }

   public void apply$mcVI$sp(final int x) {
      this.apply(x);
   }

   public boolean apply$mcZJ$sp(final long x) {
      return BoxesRunTime.unboxToBoolean(this.apply(x));
   }

   public double apply$mcDJ$sp(final long x) {
      return BoxesRunTime.unboxToDouble(this.apply(x));
   }

   public float apply$mcFJ$sp(final long x) {
      return BoxesRunTime.unboxToFloat(this.apply(x));
   }

   public int apply$mcIJ$sp(final long x) {
      return BoxesRunTime.unboxToInt(this.apply(x));
   }

   public long apply$mcJJ$sp(final long x) {
      return BoxesRunTime.unboxToLong(this.apply(x));
   }

   public void apply$mcVJ$sp(final long x) {
      this.apply(x);
   }
}
