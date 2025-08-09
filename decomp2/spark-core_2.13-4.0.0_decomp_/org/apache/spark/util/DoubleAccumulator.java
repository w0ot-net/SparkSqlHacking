package org.apache.spark.util;

import scala.Predef.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005\u00014Aa\u0005\u000b\u0001;!)!\u0006\u0001C\u0001W!9Q\u0006\u0001a\u0001\n\u0013q\u0003b\u0002\u001b\u0001\u0001\u0004%I!\u000e\u0005\u0007w\u0001\u0001\u000b\u0015B\u0018\t\u000fq\u0002\u0001\u0019!C\u0005{!9\u0011\t\u0001a\u0001\n\u0013\u0011\u0005B\u0002#\u0001A\u0003&a\bC\u0003F\u0001\u0011\u0005c\tC\u0003K\u0001\u0011\u00053\u0006C\u0003L\u0001\u0011\u0005C\nC\u0003N\u0001\u0011\u0005c\nC\u0003N\u0001\u0011\u0005\u0011\u000bC\u0003T\u0001\u0011\u0005Q\bC\u0003U\u0001\u0011\u0005a\u0006C\u0003V\u0001\u0011\u0005a\u0006C\u0003W\u0001\u0011\u0005s\u000b\u0003\u0004[\u0001\u0011\u0005ac\u0017\u0005\u0006=\u0002!\te\u0018\u0002\u0012\t>,(\r\\3BG\u000e,X.\u001e7bi>\u0014(BA\u000b\u0017\u0003\u0011)H/\u001b7\u000b\u0005]A\u0012!B:qCJ\\'BA\r\u001b\u0003\u0019\t\u0007/Y2iK*\t1$A\u0002pe\u001e\u001c\u0001a\u0005\u0002\u0001=A!q\u0004\t\u0012#\u001b\u0005!\u0012BA\u0011\u0015\u00055\t5mY;nk2\fGo\u001c:WeA\u00111\u0005K\u0007\u0002I)\u0011QEJ\u0001\u0005Y\u0006twMC\u0001(\u0003\u0011Q\u0017M^1\n\u0005%\"#A\u0002#pk\ndW-\u0001\u0004=S:LGO\u0010\u000b\u0002YA\u0011q\u0004A\u0001\u0005?N,X.F\u00010!\t\u00014'D\u00012\u0015\u0005\u0011\u0014!B:dC2\f\u0017BA\u00152\u0003!y6/^7`I\u0015\fHC\u0001\u001c:!\t\u0001t'\u0003\u00029c\t!QK\\5u\u0011\u001dQ4!!AA\u0002=\n1\u0001\u001f\u00132\u0003\u0015y6/^7!\u0003\u0019y6m\\;oiV\ta\b\u0005\u00021\u007f%\u0011\u0001)\r\u0002\u0005\u0019>tw-\u0001\u0006`G>,h\u000e^0%KF$\"AN\"\t\u000fi2\u0011\u0011!a\u0001}\u00059qlY8v]R\u0004\u0013AB5t5\u0016\u0014x.F\u0001H!\t\u0001\u0004*\u0003\u0002Jc\t9!i\\8mK\u0006t\u0017\u0001B2paf\fQA]3tKR$\u0012AN\u0001\u0004C\u0012$GC\u0001\u001cP\u0011\u0015\u00016\u00021\u0001#\u0003\u00051HC\u0001\u001cS\u0011\u0015\u0001F\u00021\u00010\u0003\u0015\u0019w.\u001e8u\u0003\r\u0019X/\\\u0001\u0004CZ<\u0017!B7fe\u001e,GC\u0001\u001cY\u0011\u0015I\u0006\u00031\u0001\u001f\u0003\u0015yG\u000f[3s\u0003!\u0019X\r\u001e,bYV,GC\u0001\u001c]\u0011\u0015i\u0016\u00031\u00010\u0003!qWm\u001e,bYV,\u0017!\u0002<bYV,W#\u0001\u0012"
)
public class DoubleAccumulator extends AccumulatorV2 {
   private double _sum = (double)0.0F;
   private long _count = 0L;

   private double _sum() {
      return this._sum;
   }

   private void _sum_$eq(final double x$1) {
      this._sum = x$1;
   }

   private long _count() {
      return this._count;
   }

   private void _count_$eq(final long x$1) {
      this._count = x$1;
   }

   public boolean isZero() {
      return this._sum() == (double)0.0F && this._count() == 0L;
   }

   public DoubleAccumulator copy() {
      DoubleAccumulator newAcc = new DoubleAccumulator();
      newAcc._count_$eq(this._count());
      newAcc._sum_$eq(this._sum());
      return newAcc;
   }

   public void reset() {
      this._sum_$eq((double)0.0F);
      this._count_$eq(0L);
   }

   public void add(final Double v) {
      this._sum_$eq(this._sum() + .MODULE$.Double2double(v));
      this._count_$eq(this._count() + 1L);
   }

   public void add(final double v) {
      this._sum_$eq(this._sum() + v);
      this._count_$eq(this._count() + 1L);
   }

   public long count() {
      return this._count();
   }

   public double sum() {
      return this._sum();
   }

   public double avg() {
      return this._sum() / (double)this._count();
   }

   public void merge(final AccumulatorV2 other) {
      if (other instanceof DoubleAccumulator var4) {
         this._sum_$eq(this._sum() + var4.sum());
         this._count_$eq(this._count() + var4.count());
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         String var10002 = this.getClass().getName();
         throw new UnsupportedOperationException("Cannot merge " + var10002 + " with " + other.getClass().getName());
      }
   }

   public void setValue(final double newValue) {
      this._sum_$eq(newValue);
   }

   public Double value() {
      return .MODULE$.double2Double(this._sum());
   }
}
