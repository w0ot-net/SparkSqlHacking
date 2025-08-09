package cats.kernel;

public interface CommutativeMonoid$mcJ$sp extends CommutativeMonoid, CommutativeSemigroup$mcJ$sp, Monoid$mcJ$sp {
   // $FF: synthetic method
   static CommutativeMonoid reverse$(final CommutativeMonoid$mcJ$sp $this) {
      return $this.reverse();
   }

   default CommutativeMonoid reverse() {
      return this.reverse$mcJ$sp();
   }

   // $FF: synthetic method
   static CommutativeMonoid reverse$mcJ$sp$(final CommutativeMonoid$mcJ$sp $this) {
      return $this.reverse$mcJ$sp();
   }

   default CommutativeMonoid reverse$mcJ$sp() {
      return this;
   }
}
