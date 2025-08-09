package org.apache.spark.util;

import scala.Predef.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005\u00014Aa\u0005\u000b\u0001;!)!\u0006\u0001C\u0001W!9Q\u0006\u0001a\u0001\n\u0013q\u0003b\u0002\u001b\u0001\u0001\u0004%I!\u000e\u0005\u0007w\u0001\u0001\u000b\u0015B\u0018\t\u000fq\u0002\u0001\u0019!C\u0005]!9Q\b\u0001a\u0001\n\u0013q\u0004B\u0002!\u0001A\u0003&q\u0006C\u0003B\u0001\u0011\u0005#\tC\u0003G\u0001\u0011\u00053\u0006C\u0003H\u0001\u0011\u0005\u0003\nC\u0003J\u0001\u0011\u0005#\nC\u0003J\u0001\u0011\u0005Q\nC\u0003P\u0001\u0011\u0005a\u0006C\u0003Q\u0001\u0011\u0005a\u0006C\u0003R\u0001\u0011\u0005!\u000bC\u0003W\u0001\u0011\u0005s\u000b\u0003\u0004[\u0001\u0011\u0005ac\u0017\u0005\u0006=\u0002!\te\u0018\u0002\u0010\u0019>tw-Q2dk6,H.\u0019;pe*\u0011QCF\u0001\u0005kRLGN\u0003\u0002\u00181\u0005)1\u000f]1sW*\u0011\u0011DG\u0001\u0007CB\f7\r[3\u000b\u0003m\t1a\u001c:h\u0007\u0001\u0019\"\u0001\u0001\u0010\u0011\t}\u0001#EI\u0007\u0002)%\u0011\u0011\u0005\u0006\u0002\u000e\u0003\u000e\u001cW/\\;mCR|'O\u0016\u001a\u0011\u0005\rBS\"\u0001\u0013\u000b\u0005\u00152\u0013\u0001\u00027b]\u001eT\u0011aJ\u0001\u0005U\u00064\u0018-\u0003\u0002*I\t!Aj\u001c8h\u0003\u0019a\u0014N\\5u}Q\tA\u0006\u0005\u0002 \u0001\u0005!ql];n+\u0005y\u0003C\u0001\u00194\u001b\u0005\t$\"\u0001\u001a\u0002\u000bM\u001c\u0017\r\\1\n\u0005%\n\u0014\u0001C0tk6|F%Z9\u0015\u0005YJ\u0004C\u0001\u00198\u0013\tA\u0014G\u0001\u0003V]&$\bb\u0002\u001e\u0004\u0003\u0003\u0005\raL\u0001\u0004q\u0012\n\u0014!B0tk6\u0004\u0013AB0d_VtG/\u0001\u0006`G>,h\u000e^0%KF$\"AN \t\u000fi2\u0011\u0011!a\u0001_\u00059qlY8v]R\u0004\u0013AB5t5\u0016\u0014x.F\u0001D!\t\u0001D)\u0003\u0002Fc\t9!i\\8mK\u0006t\u0017\u0001B2paf\fQA]3tKR$\u0012AN\u0001\u0004C\u0012$GC\u0001\u001cL\u0011\u0015a5\u00021\u0001#\u0003\u00051HC\u0001\u001cO\u0011\u0015aE\u00021\u00010\u0003\u0015\u0019w.\u001e8u\u0003\r\u0019X/\\\u0001\u0004CZ<W#A*\u0011\u0005A\"\u0016BA+2\u0005\u0019!u.\u001e2mK\u0006)Q.\u001a:hKR\u0011a\u0007\u0017\u0005\u00063B\u0001\rAH\u0001\u0006_RDWM]\u0001\tg\u0016$h+\u00197vKR\u0011a\u0007\u0018\u0005\u0006;F\u0001\raL\u0001\t]\u0016<h+\u00197vK\u0006)a/\u00197vKV\t!\u0005"
)
public class LongAccumulator extends AccumulatorV2 {
   private long _sum = 0L;
   private long _count = 0L;

   private long _sum() {
      return this._sum;
   }

   private void _sum_$eq(final long x$1) {
      this._sum = x$1;
   }

   private long _count() {
      return this._count;
   }

   private void _count_$eq(final long x$1) {
      this._count = x$1;
   }

   public boolean isZero() {
      return this._sum() == 0L && this._count() == 0L;
   }

   public LongAccumulator copy() {
      LongAccumulator newAcc = new LongAccumulator();
      newAcc._count_$eq(this._count());
      newAcc._sum_$eq(this._sum());
      return newAcc;
   }

   public void reset() {
      this._sum_$eq(0L);
      this._count_$eq(0L);
   }

   public void add(final Long v) {
      this._sum_$eq(this._sum() + .MODULE$.Long2long(v));
      this._count_$eq(this._count() + 1L);
   }

   public void add(final long v) {
      this._sum_$eq(this._sum() + v);
      this._count_$eq(this._count() + 1L);
   }

   public long count() {
      return this._count();
   }

   public long sum() {
      return this._sum();
   }

   public double avg() {
      return (double)this._sum() / (double)this._count();
   }

   public void merge(final AccumulatorV2 other) {
      if (other instanceof LongAccumulator var4) {
         this._sum_$eq(this._sum() + var4.sum());
         this._count_$eq(this._count() + var4.count());
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         String var10002 = this.getClass().getName();
         throw new UnsupportedOperationException("Cannot merge " + var10002 + " with " + other.getClass().getName());
      }
   }

   public void setValue(final long newValue) {
      this._sum_$eq(newValue);
   }

   public Long value() {
      return .MODULE$.long2Long(this._sum());
   }
}
