package org.apache.spark.graphx;

import java.io.Serializable;
import org.apache.spark.util.collection.SortDataFormat;
import scala.Function1;
import scala.Option;
import scala.Some;
import scala.Tuple3;
import scala.None.;
import scala.math.Ordering;
import scala.math.PartialOrdering;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class Edge$ implements Serializable {
   public static final Edge$ MODULE$ = new Edge$();

   public long $lessinit$greater$default$1() {
      return 0L;
   }

   public long $lessinit$greater$default$2() {
      return 0L;
   }

   public Object $lessinit$greater$default$3() {
      return null;
   }

   public Ordering lexicographicOrdering() {
      return new Ordering() {
         public Some tryCompare(final Object x, final Object y) {
            return Ordering.tryCompare$(this, x, y);
         }

         public boolean lteq(final Object x, final Object y) {
            return Ordering.lteq$(this, x, y);
         }

         public boolean gteq(final Object x, final Object y) {
            return Ordering.gteq$(this, x, y);
         }

         public boolean lt(final Object x, final Object y) {
            return Ordering.lt$(this, x, y);
         }

         public boolean gt(final Object x, final Object y) {
            return Ordering.gt$(this, x, y);
         }

         public boolean equiv(final Object x, final Object y) {
            return Ordering.equiv$(this, x, y);
         }

         public Object max(final Object x, final Object y) {
            return Ordering.max$(this, x, y);
         }

         public Object min(final Object x, final Object y) {
            return Ordering.min$(this, x, y);
         }

         public Ordering reverse() {
            return Ordering.reverse$(this);
         }

         public boolean isReverseOf(final Ordering other) {
            return Ordering.isReverseOf$(this, other);
         }

         public Ordering on(final Function1 f) {
            return Ordering.on$(this, f);
         }

         public Ordering orElse(final Ordering other) {
            return Ordering.orElse$(this, other);
         }

         public Ordering orElseBy(final Function1 f, final Ordering ord) {
            return Ordering.orElseBy$(this, f, ord);
         }

         public Ordering.OrderingOps mkOrderingOps(final Object lhs) {
            return Ordering.mkOrderingOps$(this, lhs);
         }

         public int compare(final Edge a, final Edge b) {
            if (a.srcId() == b.srcId()) {
               if (a.dstId() == b.dstId()) {
                  return 0;
               } else {
                  return a.dstId() < b.dstId() ? -1 : 1;
               }
            } else {
               return a.srcId() < b.srcId() ? -1 : 1;
            }
         }

         public {
            PartialOrdering.$init$(this);
            Ordering.$init$(this);
         }
      };
   }

   public SortDataFormat edgeArraySortDataFormat() {
      return new SortDataFormat() {
         public Edge getKey(final Edge[] data, final int pos) {
            return data[pos];
         }

         public void swap(final Edge[] data, final int pos0, final int pos1) {
            Edge tmp = data[pos0];
            data[pos0] = data[pos1];
            data[pos1] = tmp;
         }

         public void copyElement(final Edge[] src, final int srcPos, final Edge[] dst, final int dstPos) {
            dst[dstPos] = src[srcPos];
         }

         public void copyRange(final Edge[] src, final int srcPos, final Edge[] dst, final int dstPos, final int length) {
            System.arraycopy(src, srcPos, dst, dstPos, length);
         }

         public Edge[] allocate(final int length) {
            return new Edge[length];
         }
      };
   }

   public Edge apply(final long srcId, final long dstId, final Object attr) {
      return new Edge(srcId, dstId, attr);
   }

   public long apply$default$1() {
      return 0L;
   }

   public long apply$default$2() {
      return 0L;
   }

   public Object apply$default$3() {
      return null;
   }

   public Option unapply(final Edge x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple3(BoxesRunTime.boxToLong(x$0.srcId()), BoxesRunTime.boxToLong(x$0.dstId()), x$0.attr())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Edge$.class);
   }

   public Edge apply$mZc$sp(final long srcId, final long dstId, final boolean attr) {
      return new Edge$mcZ$sp(srcId, dstId, attr);
   }

   public Edge apply$mBc$sp(final long srcId, final long dstId, final byte attr) {
      return new Edge$mcB$sp(srcId, dstId, attr);
   }

   public Edge apply$mCc$sp(final long srcId, final long dstId, final char attr) {
      return new Edge$mcC$sp(srcId, dstId, attr);
   }

   public Edge apply$mDc$sp(final long srcId, final long dstId, final double attr) {
      return new Edge$mcD$sp(srcId, dstId, attr);
   }

   public Edge apply$mFc$sp(final long srcId, final long dstId, final float attr) {
      return new Edge$mcF$sp(srcId, dstId, attr);
   }

   public Edge apply$mIc$sp(final long srcId, final long dstId, final int attr) {
      return new Edge$mcI$sp(srcId, dstId, attr);
   }

   public Edge apply$mJc$sp(final long srcId, final long dstId, final long attr) {
      return new Edge$mcJ$sp(srcId, dstId, attr);
   }

   public Option unapply$mZc$sp(final Edge x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple3(BoxesRunTime.boxToLong(x$0.srcId()), BoxesRunTime.boxToLong(x$0.dstId()), BoxesRunTime.boxToBoolean(x$0.attr$mcZ$sp()))));
   }

   public Option unapply$mBc$sp(final Edge x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple3(BoxesRunTime.boxToLong(x$0.srcId()), BoxesRunTime.boxToLong(x$0.dstId()), BoxesRunTime.boxToByte(x$0.attr$mcB$sp()))));
   }

   public Option unapply$mCc$sp(final Edge x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple3(BoxesRunTime.boxToLong(x$0.srcId()), BoxesRunTime.boxToLong(x$0.dstId()), BoxesRunTime.boxToCharacter(x$0.attr$mcC$sp()))));
   }

   public Option unapply$mDc$sp(final Edge x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple3(BoxesRunTime.boxToLong(x$0.srcId()), BoxesRunTime.boxToLong(x$0.dstId()), BoxesRunTime.boxToDouble(x$0.attr$mcD$sp()))));
   }

   public Option unapply$mFc$sp(final Edge x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple3(BoxesRunTime.boxToLong(x$0.srcId()), BoxesRunTime.boxToLong(x$0.dstId()), BoxesRunTime.boxToFloat(x$0.attr$mcF$sp()))));
   }

   public Option unapply$mIc$sp(final Edge x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple3(BoxesRunTime.boxToLong(x$0.srcId()), BoxesRunTime.boxToLong(x$0.dstId()), BoxesRunTime.boxToInteger(x$0.attr$mcI$sp()))));
   }

   public Option unapply$mJc$sp(final Edge x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple3(BoxesRunTime.boxToLong(x$0.srcId()), BoxesRunTime.boxToLong(x$0.dstId()), BoxesRunTime.boxToLong(x$0.attr$mcJ$sp()))));
   }

   private Edge$() {
   }
}
