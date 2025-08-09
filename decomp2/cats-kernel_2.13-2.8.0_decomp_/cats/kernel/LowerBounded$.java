package cats.kernel;

import cats.kernel.instances.unit.package$;

public final class LowerBounded$ implements LowerBoundedFunctions {
   public static final LowerBounded$ MODULE$ = new LowerBounded$();

   static {
      LowerBoundedFunctions.$init$(MODULE$);
   }

   public Object minBound(final LowerBounded ev) {
      return LowerBoundedFunctions.minBound$(this, ev);
   }

   public boolean minBound$mZc$sp(final LowerBounded ev) {
      return LowerBoundedFunctions.minBound$mZc$sp$(this, ev);
   }

   public byte minBound$mBc$sp(final LowerBounded ev) {
      return LowerBoundedFunctions.minBound$mBc$sp$(this, ev);
   }

   public char minBound$mCc$sp(final LowerBounded ev) {
      return LowerBoundedFunctions.minBound$mCc$sp$(this, ev);
   }

   public double minBound$mDc$sp(final LowerBounded ev) {
      return LowerBoundedFunctions.minBound$mDc$sp$(this, ev);
   }

   public float minBound$mFc$sp(final LowerBounded ev) {
      return LowerBoundedFunctions.minBound$mFc$sp$(this, ev);
   }

   public int minBound$mIc$sp(final LowerBounded ev) {
      return LowerBoundedFunctions.minBound$mIc$sp$(this, ev);
   }

   public long minBound$mJc$sp(final LowerBounded ev) {
      return LowerBoundedFunctions.minBound$mJc$sp$(this, ev);
   }

   public short minBound$mSc$sp(final LowerBounded ev) {
      return LowerBoundedFunctions.minBound$mSc$sp$(this, ev);
   }

   public void minBound$mVc$sp(final LowerBounded ev) {
      LowerBoundedFunctions.minBound$mVc$sp$(this, ev);
   }

   public LowerBounded apply(final LowerBounded l) {
      return l;
   }

   public LowerBounded catsKernelLowerBoundedForUnit() {
      return (LowerBounded)package$.MODULE$.catsKernelStdOrderForUnit();
   }

   public LowerBounded catsKernelLowerBoundedForBoolean() {
      return (LowerBounded)cats.kernel.instances.boolean.package$.MODULE$.catsKernelStdOrderForBoolean();
   }

   public LowerBounded catsKernelLowerBoundedForByte() {
      return (LowerBounded)cats.kernel.instances.byte.package$.MODULE$.catsKernelStdOrderForByte();
   }

   public LowerBounded catsKernelLowerBoundedForInt() {
      return (LowerBounded)cats.kernel.instances.int.package$.MODULE$.catsKernelStdOrderForInt();
   }

   public LowerBounded catsKernelLowerBoundedForShort() {
      return (LowerBounded)cats.kernel.instances.short.package$.MODULE$.catsKernelStdOrderForShort();
   }

   public LowerBounded catsKernelLowerBoundedForLong() {
      return (LowerBounded)cats.kernel.instances.long.package$.MODULE$.catsKernelStdOrderForLong();
   }

   public LowerBounded catsKernelLowerBoundedForDuration() {
      return (LowerBounded)cats.kernel.instances.duration.package$.MODULE$.catsKernelStdOrderForDuration();
   }

   public LowerBounded catsKernelLowerBoundedForFiniteDuration() {
      return (LowerBounded)cats.kernel.instances.all.package$.MODULE$.catsKernelStdOrderForFiniteDuration();
   }

   public LowerBounded catsKernelLowerBoundedForChar() {
      return cats.kernel.instances.char.package$.MODULE$.catsKernelStdOrderForChar();
   }

   public LowerBounded catsKernelLowerBoundedForString() {
      return (LowerBounded)cats.kernel.instances.string.package$.MODULE$.catsKernelStdOrderForString();
   }

   public LowerBounded catsKernelLowerBoundedForSymbol() {
      return (LowerBounded)cats.kernel.instances.symbol.package$.MODULE$.catsKernelStdOrderForSymbol();
   }

   public LowerBounded catsKernelLowerBoundedForUUID() {
      return (LowerBounded)cats.kernel.instances.uuid.package$.MODULE$.catsKernelStdOrderForUUID();
   }

   private LowerBounded$() {
   }
}
