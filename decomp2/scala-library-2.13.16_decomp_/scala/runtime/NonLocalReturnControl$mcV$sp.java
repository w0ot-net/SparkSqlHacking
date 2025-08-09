package scala.runtime;

public class NonLocalReturnControl$mcV$sp extends NonLocalReturnControl {
   public final BoxedUnit value$mcV$sp;

   public void value$mcV$sp() {
   }

   public void value() {
      this.value$mcV$sp();
   }

   public boolean specInstance$() {
      return true;
   }

   public NonLocalReturnControl$mcV$sp(final Object key, final BoxedUnit value$mcV$sp) {
      super(key, BoxedUnit.UNIT);
      this.value$mcV$sp = value$mcV$sp;
   }
}
