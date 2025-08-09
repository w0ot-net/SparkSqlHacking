package org.apache.spark.graphx.impl;

import java.io.Serializable;
import org.apache.spark.util.collection.SortDataFormat;
import scala.Function1;
import scala.Option;
import scala.Some;
import scala.Tuple5;
import scala.None.;
import scala.math.Ordering;
import scala.math.PartialOrdering;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class EdgeWithLocalIds$ implements Serializable {
   public static final EdgeWithLocalIds$ MODULE$ = new EdgeWithLocalIds$();

   public Ordering lexicographicOrdering() {
      return new Ordering() {
         private static final long serialVersionUID = 0L;

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

         public final int compare(final EdgeWithLocalIds x, final EdgeWithLocalIds y) {
            return EdgeWithLocalIds$.org$apache$spark$graphx$impl$EdgeWithLocalIds$$$anonfun$lexicographicOrdering$1(x, y);
         }

         public {
            PartialOrdering.$init$(this);
            Ordering.$init$(this);
         }
      };
   }

   public SortDataFormat edgeArraySortDataFormat() {
      return new SortDataFormat() {
         public EdgeWithLocalIds getKey(final EdgeWithLocalIds[] data, final int pos) {
            return data[pos];
         }

         public void swap(final EdgeWithLocalIds[] data, final int pos0, final int pos1) {
            EdgeWithLocalIds tmp = data[pos0];
            data[pos0] = data[pos1];
            data[pos1] = tmp;
         }

         public void copyElement(final EdgeWithLocalIds[] src, final int srcPos, final EdgeWithLocalIds[] dst, final int dstPos) {
            dst[dstPos] = src[srcPos];
         }

         public void copyRange(final EdgeWithLocalIds[] src, final int srcPos, final EdgeWithLocalIds[] dst, final int dstPos, final int length) {
            System.arraycopy(src, srcPos, dst, dstPos, length);
         }

         public EdgeWithLocalIds[] allocate(final int length) {
            return new EdgeWithLocalIds[length];
         }
      };
   }

   public EdgeWithLocalIds apply(final long srcId, final long dstId, final int localSrcId, final int localDstId, final Object attr) {
      return new EdgeWithLocalIds(srcId, dstId, localSrcId, localDstId, attr);
   }

   public Option unapply(final EdgeWithLocalIds x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple5(BoxesRunTime.boxToLong(x$0.srcId()), BoxesRunTime.boxToLong(x$0.dstId()), BoxesRunTime.boxToInteger(x$0.localSrcId()), BoxesRunTime.boxToInteger(x$0.localDstId()), x$0.attr())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(EdgeWithLocalIds$.class);
   }

   public EdgeWithLocalIds apply$mZc$sp(final long srcId, final long dstId, final int localSrcId, final int localDstId, final boolean attr) {
      return new EdgeWithLocalIds$mcZ$sp(srcId, dstId, localSrcId, localDstId, attr);
   }

   public EdgeWithLocalIds apply$mBc$sp(final long srcId, final long dstId, final int localSrcId, final int localDstId, final byte attr) {
      return new EdgeWithLocalIds$mcB$sp(srcId, dstId, localSrcId, localDstId, attr);
   }

   public EdgeWithLocalIds apply$mCc$sp(final long srcId, final long dstId, final int localSrcId, final int localDstId, final char attr) {
      return new EdgeWithLocalIds$mcC$sp(srcId, dstId, localSrcId, localDstId, attr);
   }

   public EdgeWithLocalIds apply$mDc$sp(final long srcId, final long dstId, final int localSrcId, final int localDstId, final double attr) {
      return new EdgeWithLocalIds$mcD$sp(srcId, dstId, localSrcId, localDstId, attr);
   }

   public EdgeWithLocalIds apply$mFc$sp(final long srcId, final long dstId, final int localSrcId, final int localDstId, final float attr) {
      return new EdgeWithLocalIds$mcF$sp(srcId, dstId, localSrcId, localDstId, attr);
   }

   public EdgeWithLocalIds apply$mIc$sp(final long srcId, final long dstId, final int localSrcId, final int localDstId, final int attr) {
      return new EdgeWithLocalIds$mcI$sp(srcId, dstId, localSrcId, localDstId, attr);
   }

   public EdgeWithLocalIds apply$mJc$sp(final long srcId, final long dstId, final int localSrcId, final int localDstId, final long attr) {
      return new EdgeWithLocalIds$mcJ$sp(srcId, dstId, localSrcId, localDstId, attr);
   }

   public EdgeWithLocalIds apply$mSc$sp(final long srcId, final long dstId, final int localSrcId, final int localDstId, final short attr) {
      return new EdgeWithLocalIds$mcS$sp(srcId, dstId, localSrcId, localDstId, attr);
   }

   public EdgeWithLocalIds apply$mVc$sp(final long srcId, final long dstId, final int localSrcId, final int localDstId, final BoxedUnit attr) {
      return new EdgeWithLocalIds$mcV$sp(srcId, dstId, localSrcId, localDstId, attr);
   }

   public Option unapply$mZc$sp(final EdgeWithLocalIds x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple5(BoxesRunTime.boxToLong(x$0.srcId()), BoxesRunTime.boxToLong(x$0.dstId()), BoxesRunTime.boxToInteger(x$0.localSrcId()), BoxesRunTime.boxToInteger(x$0.localDstId()), BoxesRunTime.boxToBoolean(x$0.attr$mcZ$sp()))));
   }

   public Option unapply$mBc$sp(final EdgeWithLocalIds x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple5(BoxesRunTime.boxToLong(x$0.srcId()), BoxesRunTime.boxToLong(x$0.dstId()), BoxesRunTime.boxToInteger(x$0.localSrcId()), BoxesRunTime.boxToInteger(x$0.localDstId()), BoxesRunTime.boxToByte(x$0.attr$mcB$sp()))));
   }

   public Option unapply$mCc$sp(final EdgeWithLocalIds x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple5(BoxesRunTime.boxToLong(x$0.srcId()), BoxesRunTime.boxToLong(x$0.dstId()), BoxesRunTime.boxToInteger(x$0.localSrcId()), BoxesRunTime.boxToInteger(x$0.localDstId()), BoxesRunTime.boxToCharacter(x$0.attr$mcC$sp()))));
   }

   public Option unapply$mDc$sp(final EdgeWithLocalIds x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple5(BoxesRunTime.boxToLong(x$0.srcId()), BoxesRunTime.boxToLong(x$0.dstId()), BoxesRunTime.boxToInteger(x$0.localSrcId()), BoxesRunTime.boxToInteger(x$0.localDstId()), BoxesRunTime.boxToDouble(x$0.attr$mcD$sp()))));
   }

   public Option unapply$mFc$sp(final EdgeWithLocalIds x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple5(BoxesRunTime.boxToLong(x$0.srcId()), BoxesRunTime.boxToLong(x$0.dstId()), BoxesRunTime.boxToInteger(x$0.localSrcId()), BoxesRunTime.boxToInteger(x$0.localDstId()), BoxesRunTime.boxToFloat(x$0.attr$mcF$sp()))));
   }

   public Option unapply$mIc$sp(final EdgeWithLocalIds x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple5(BoxesRunTime.boxToLong(x$0.srcId()), BoxesRunTime.boxToLong(x$0.dstId()), BoxesRunTime.boxToInteger(x$0.localSrcId()), BoxesRunTime.boxToInteger(x$0.localDstId()), BoxesRunTime.boxToInteger(x$0.attr$mcI$sp()))));
   }

   public Option unapply$mJc$sp(final EdgeWithLocalIds x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple5(BoxesRunTime.boxToLong(x$0.srcId()), BoxesRunTime.boxToLong(x$0.dstId()), BoxesRunTime.boxToInteger(x$0.localSrcId()), BoxesRunTime.boxToInteger(x$0.localDstId()), BoxesRunTime.boxToLong(x$0.attr$mcJ$sp()))));
   }

   public Option unapply$mSc$sp(final EdgeWithLocalIds x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple5(BoxesRunTime.boxToLong(x$0.srcId()), BoxesRunTime.boxToLong(x$0.dstId()), BoxesRunTime.boxToInteger(x$0.localSrcId()), BoxesRunTime.boxToInteger(x$0.localDstId()), BoxesRunTime.boxToShort(x$0.attr$mcS$sp()))));
   }

   public Option unapply$mVc$sp(final EdgeWithLocalIds x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple5(BoxesRunTime.boxToLong(x$0.srcId()), BoxesRunTime.boxToLong(x$0.dstId()), BoxesRunTime.boxToInteger(x$0.localSrcId()), BoxesRunTime.boxToInteger(x$0.localDstId()), BoxedUnit.UNIT)));
   }

   // $FF: synthetic method
   public static final int org$apache$spark$graphx$impl$EdgeWithLocalIds$$$anonfun$lexicographicOrdering$1(final EdgeWithLocalIds a, final EdgeWithLocalIds b) {
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

   private EdgeWithLocalIds$() {
   }
}
