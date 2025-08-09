package cats.kernel;

public interface CommutativeMonoid$mcD$sp extends CommutativeMonoid, CommutativeSemigroup$mcD$sp, Monoid$mcD$sp {
   // $FF: synthetic method
   static CommutativeMonoid reverse$(final CommutativeMonoid$mcD$sp $this) {
      return $this.reverse();
   }

   default CommutativeMonoid reverse() {
      return this.reverse$mcD$sp();
   }

   // $FF: synthetic method
   static CommutativeMonoid reverse$mcD$sp$(final CommutativeMonoid$mcD$sp $this) {
      return $this.reverse$mcD$sp();
   }

   default CommutativeMonoid reverse$mcD$sp() {
      return this;
   }
}
