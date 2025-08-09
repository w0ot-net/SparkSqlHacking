package spire.algebra;

public final class Involution$ {
   public static final Involution$ MODULE$ = new Involution$();

   public Involution apply(final Involution ev) {
      return ev;
   }

   public Involution selfAdjoint() {
      return new Involution.SelfAdjoint();
   }

   public Involution fromIsReal(final IsReal evidence$1) {
      return this.selfAdjoint();
   }

   public Involution selfAdjoint$mDc$sp() {
      return new Involution$SelfAdjoint$mcD$sp();
   }

   public Involution selfAdjoint$mFc$sp() {
      return new Involution$SelfAdjoint$mcF$sp();
   }

   public Involution selfAdjoint$mIc$sp() {
      return new Involution$SelfAdjoint$mcI$sp();
   }

   public Involution selfAdjoint$mJc$sp() {
      return new Involution$SelfAdjoint$mcJ$sp();
   }

   public Involution fromIsReal$mDc$sp(final IsReal evidence$1) {
      return this.selfAdjoint$mDc$sp();
   }

   public Involution fromIsReal$mFc$sp(final IsReal evidence$1) {
      return this.selfAdjoint$mFc$sp();
   }

   public Involution fromIsReal$mIc$sp(final IsReal evidence$1) {
      return this.selfAdjoint$mIc$sp();
   }

   public Involution fromIsReal$mJc$sp(final IsReal evidence$1) {
      return this.selfAdjoint$mJc$sp();
   }

   private Involution$() {
   }
}
