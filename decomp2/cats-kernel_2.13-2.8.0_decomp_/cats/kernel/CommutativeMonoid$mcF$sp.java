package cats.kernel;

public interface CommutativeMonoid$mcF$sp extends CommutativeMonoid, CommutativeSemigroup$mcF$sp, Monoid$mcF$sp {
   // $FF: synthetic method
   static CommutativeMonoid reverse$(final CommutativeMonoid$mcF$sp $this) {
      return $this.reverse();
   }

   default CommutativeMonoid reverse() {
      return this.reverse$mcF$sp();
   }

   // $FF: synthetic method
   static CommutativeMonoid reverse$mcF$sp$(final CommutativeMonoid$mcF$sp $this) {
      return $this.reverse$mcF$sp();
   }

   default CommutativeMonoid reverse$mcF$sp() {
      return this;
   }
}
