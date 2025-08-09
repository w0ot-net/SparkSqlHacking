package cats.kernel;

public interface CommutativeMonoid$mcI$sp extends CommutativeMonoid, CommutativeSemigroup$mcI$sp, Monoid$mcI$sp {
   // $FF: synthetic method
   static CommutativeMonoid reverse$(final CommutativeMonoid$mcI$sp $this) {
      return $this.reverse();
   }

   default CommutativeMonoid reverse() {
      return this.reverse$mcI$sp();
   }

   // $FF: synthetic method
   static CommutativeMonoid reverse$mcI$sp$(final CommutativeMonoid$mcI$sp $this) {
      return $this.reverse$mcI$sp();
   }

   default CommutativeMonoid reverse$mcI$sp() {
      return this;
   }
}
