package cats.kernel;

import cats.kernel.instances.unit.package$;

public final class UpperBounded$ implements UpperBoundedFunctions {
   public static final UpperBounded$ MODULE$ = new UpperBounded$();

   static {
      UpperBoundedFunctions.$init$(MODULE$);
   }

   public Object maxBound(final UpperBounded ev) {
      return UpperBoundedFunctions.maxBound$(this, ev);
   }

   public boolean maxBound$mZc$sp(final UpperBounded ev) {
      return UpperBoundedFunctions.maxBound$mZc$sp$(this, ev);
   }

   public byte maxBound$mBc$sp(final UpperBounded ev) {
      return UpperBoundedFunctions.maxBound$mBc$sp$(this, ev);
   }

   public char maxBound$mCc$sp(final UpperBounded ev) {
      return UpperBoundedFunctions.maxBound$mCc$sp$(this, ev);
   }

   public double maxBound$mDc$sp(final UpperBounded ev) {
      return UpperBoundedFunctions.maxBound$mDc$sp$(this, ev);
   }

   public float maxBound$mFc$sp(final UpperBounded ev) {
      return UpperBoundedFunctions.maxBound$mFc$sp$(this, ev);
   }

   public int maxBound$mIc$sp(final UpperBounded ev) {
      return UpperBoundedFunctions.maxBound$mIc$sp$(this, ev);
   }

   public long maxBound$mJc$sp(final UpperBounded ev) {
      return UpperBoundedFunctions.maxBound$mJc$sp$(this, ev);
   }

   public short maxBound$mSc$sp(final UpperBounded ev) {
      return UpperBoundedFunctions.maxBound$mSc$sp$(this, ev);
   }

   public void maxBound$mVc$sp(final UpperBounded ev) {
      UpperBoundedFunctions.maxBound$mVc$sp$(this, ev);
   }

   public UpperBounded apply(final UpperBounded u) {
      return u;
   }

   public UpperBounded catsKernelUpperBoundedForUnit() {
      return (UpperBounded)package$.MODULE$.catsKernelStdOrderForUnit();
   }

   public UpperBounded catsKernelUpperBoundedForBoolean() {
      return (UpperBounded)cats.kernel.instances.boolean.package$.MODULE$.catsKernelStdOrderForBoolean();
   }

   public UpperBounded catsKernelUpperBoundedForByte() {
      return (UpperBounded)cats.kernel.instances.byte.package$.MODULE$.catsKernelStdOrderForByte();
   }

   public UpperBounded catsKernelUpperBoundedForInt() {
      return (UpperBounded)cats.kernel.instances.int.package$.MODULE$.catsKernelStdOrderForInt();
   }

   public UpperBounded catsKernelUpperBoundedForShort() {
      return (UpperBounded)cats.kernel.instances.short.package$.MODULE$.catsKernelStdOrderForShort();
   }

   public UpperBounded catsKernelUpperBoundedForLong() {
      return (UpperBounded)cats.kernel.instances.long.package$.MODULE$.catsKernelStdOrderForLong();
   }

   public UpperBounded catsKernelUpperBoundedForDuration() {
      return (UpperBounded)cats.kernel.instances.duration.package$.MODULE$.catsKernelStdOrderForDuration();
   }

   public UpperBounded catsKernelUpperBoundedForFiniteDuration() {
      return (UpperBounded)cats.kernel.instances.all.package$.MODULE$.catsKernelStdOrderForFiniteDuration();
   }

   public UpperBounded catsKernelUpperBoundedForChar() {
      return cats.kernel.instances.char.package$.MODULE$.catsKernelStdOrderForChar();
   }

   public UpperBounded catsKernelUpperBoundedForUUID() {
      return (UpperBounded)cats.kernel.instances.uuid.package$.MODULE$.catsKernelStdOrderForUUID();
   }

   private UpperBounded$() {
   }
}
