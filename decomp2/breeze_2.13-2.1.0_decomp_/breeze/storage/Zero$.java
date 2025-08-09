package breeze.storage;

import breeze.math.Semiring;
import java.io.Serializable;
import java.math.BigDecimal;
import scala.Option;
import scala.Some;
import scala.package.;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class Zero$ implements Serializable {
   public static final Zero$ MODULE$ = new Zero$();
   private static Zero BigDecimalZero;
   private static final Zero IntZero = new Zero$mcI$sp(0);
   private static final Zero ShortZero = new Zero$mcS$sp((short)0);
   private static final Zero LongZero = new Zero$mcJ$sp(0L);
   private static final Zero ByteZero = new Zero$mcB$sp((byte)0);
   private static final Zero CharZero = new Zero$mcC$sp((char)0);
   private static final Zero FloatZero = new Zero$mcF$sp(0.0F);
   private static final Zero DoubleZero = new Zero$mcD$sp((double)0.0F);
   private static final Zero BooleanZero = new Zero$mcZ$sp(false);
   private static final Zero BigIntZero;
   private static final Zero StringZero;
   private static final Zero refDefault;
   private static volatile boolean bitmap$0;

   static {
      BigIntZero = new Zero(.MODULE$.BigInt().apply(0));
      StringZero = new Zero("");
      refDefault = new Zero((Object)null);
   }

   public Zero IntZero() {
      return IntZero;
   }

   public Zero ShortZero() {
      return ShortZero;
   }

   public Zero LongZero() {
      return LongZero;
   }

   public Zero ByteZero() {
      return ByteZero;
   }

   public Zero CharZero() {
      return CharZero;
   }

   public Zero FloatZero() {
      return FloatZero;
   }

   public Zero DoubleZero() {
      return DoubleZero;
   }

   public Zero BooleanZero() {
      return BooleanZero;
   }

   public Zero BigIntZero() {
      return BigIntZero;
   }

   private Zero BigDecimalZero$lzycompute() {
      synchronized(this){}

      try {
         if (!bitmap$0) {
            BigDecimalZero = new Zero(.MODULE$.BigDecimal().apply(BigDecimal.ZERO));
            bitmap$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return BigDecimalZero;
   }

   public Zero BigDecimalZero() {
      return !bitmap$0 ? this.BigDecimalZero$lzycompute() : BigDecimalZero;
   }

   public Zero StringZero() {
      return StringZero;
   }

   public Zero zeroFromSemiring(final Semiring ring) {
      return new Zero(ring.zero());
   }

   public Zero refDefault() {
      return refDefault;
   }

   public Zero apply(final Object zero) {
      return new Zero(zero);
   }

   public Option unapply(final Zero x$0) {
      return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(x$0.zero()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Zero$.class);
   }

   public Zero apply$mZc$sp(final boolean zero) {
      return new Zero$mcZ$sp(zero);
   }

   public Zero apply$mBc$sp(final byte zero) {
      return new Zero$mcB$sp(zero);
   }

   public Zero apply$mCc$sp(final char zero) {
      return new Zero$mcC$sp(zero);
   }

   public Zero apply$mDc$sp(final double zero) {
      return new Zero$mcD$sp(zero);
   }

   public Zero apply$mFc$sp(final float zero) {
      return new Zero$mcF$sp(zero);
   }

   public Zero apply$mIc$sp(final int zero) {
      return new Zero$mcI$sp(zero);
   }

   public Zero apply$mJc$sp(final long zero) {
      return new Zero$mcJ$sp(zero);
   }

   public Zero apply$mSc$sp(final short zero) {
      return new Zero$mcS$sp(zero);
   }

   public Zero apply$mVc$sp(final BoxedUnit zero) {
      return new Zero$mcV$sp(zero);
   }

   public Option unapply$mZc$sp(final Zero x$0) {
      return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(BoxesRunTime.boxToBoolean(x$0.zero$mcZ$sp())));
   }

   public Option unapply$mBc$sp(final Zero x$0) {
      return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(BoxesRunTime.boxToByte(x$0.zero$mcB$sp())));
   }

   public Option unapply$mCc$sp(final Zero x$0) {
      return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(BoxesRunTime.boxToCharacter(x$0.zero$mcC$sp())));
   }

   public Option unapply$mDc$sp(final Zero x$0) {
      return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(BoxesRunTime.boxToDouble(x$0.zero$mcD$sp())));
   }

   public Option unapply$mFc$sp(final Zero x$0) {
      return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(BoxesRunTime.boxToFloat(x$0.zero$mcF$sp())));
   }

   public Option unapply$mIc$sp(final Zero x$0) {
      return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(BoxesRunTime.boxToInteger(x$0.zero$mcI$sp())));
   }

   public Option unapply$mJc$sp(final Zero x$0) {
      return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(BoxesRunTime.boxToLong(x$0.zero$mcJ$sp())));
   }

   public Option unapply$mSc$sp(final Zero x$0) {
      return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(BoxesRunTime.boxToShort(x$0.zero$mcS$sp())));
   }

   public Option unapply$mVc$sp(final Zero x$0) {
      return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(BoxedUnit.UNIT));
   }

   private Zero$() {
   }
}
