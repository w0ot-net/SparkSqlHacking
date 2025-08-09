package spire.optional;

import cats.kernel.Eq;
import cats.kernel.Order;
import spire.algebra.CModule;
import spire.std.ArrayVectorEq;
import spire.std.ArrayVectorOrder;
import spire.std.ArrayVectorOrder$mcD$sp;
import spire.std.ArrayVectorOrder$mcF$sp;
import spire.std.ArrayVectorOrder$mcI$sp;
import spire.std.ArrayVectorOrder$mcJ$sp;
import spire.std.MapVectorEq;
import spire.std.SeqVectorEq;
import spire.std.SeqVectorOrder;

public final class vectorOrder$ implements VectorOrderLow {
   public static final vectorOrder$ MODULE$ = new vectorOrder$();

   static {
      VectorOrderLow.$init$(MODULE$);
   }

   public SeqVectorEq seqEq(final Eq A0, final CModule module) {
      return VectorOrderLow.seqEq$(this, A0, module);
   }

   public ArrayVectorEq arrayEq(final Eq ev, final CModule module) {
      return VectorOrderLow.arrayEq$(this, ev, module);
   }

   public ArrayVectorEq arrayEq$mDc$sp(final Eq ev, final CModule module) {
      return VectorOrderLow.arrayEq$mDc$sp$(this, ev, module);
   }

   public ArrayVectorEq arrayEq$mFc$sp(final Eq ev, final CModule module) {
      return VectorOrderLow.arrayEq$mFc$sp$(this, ev, module);
   }

   public ArrayVectorEq arrayEq$mIc$sp(final Eq ev, final CModule module) {
      return VectorOrderLow.arrayEq$mIc$sp$(this, ev, module);
   }

   public ArrayVectorEq arrayEq$mJc$sp(final Eq ev, final CModule module) {
      return VectorOrderLow.arrayEq$mJc$sp$(this, ev, module);
   }

   public MapVectorEq mapEq(final Eq V0, final CModule module) {
      return VectorOrderLow.mapEq$(this, V0, module);
   }

   public SeqVectorOrder seqOrder(final Order A0, final CModule module) {
      return new SeqVectorOrder(A0, module.scalar());
   }

   public ArrayVectorOrder arrayOrder(final Order ev, final CModule module) {
      return new ArrayVectorOrder(ev, module.scalar());
   }

   public ArrayVectorOrder arrayOrder$mDc$sp(final Order ev, final CModule module) {
      return new ArrayVectorOrder$mcD$sp(ev, module.scalar$mcD$sp());
   }

   public ArrayVectorOrder arrayOrder$mFc$sp(final Order ev, final CModule module) {
      return new ArrayVectorOrder$mcF$sp(ev, module.scalar$mcF$sp());
   }

   public ArrayVectorOrder arrayOrder$mIc$sp(final Order ev, final CModule module) {
      return new ArrayVectorOrder$mcI$sp(ev, module.scalar$mcI$sp());
   }

   public ArrayVectorOrder arrayOrder$mJc$sp(final Order ev, final CModule module) {
      return new ArrayVectorOrder$mcJ$sp(ev, module.scalar$mcJ$sp());
   }

   private vectorOrder$() {
   }
}
