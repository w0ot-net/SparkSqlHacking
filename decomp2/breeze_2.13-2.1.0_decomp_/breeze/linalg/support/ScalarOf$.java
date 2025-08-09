package breeze.linalg.support;

public final class ScalarOf$ {
   public static final ScalarOf$ MODULE$ = new ScalarOf$();

   public ScalarOf dummy() {
      return ScalarOf.DummyInstance$.MODULE$;
   }

   public ScalarOf scalarOfArray() {
      return this.dummy();
   }

   private ScalarOf$() {
   }
}
