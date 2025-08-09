package breeze.optimize.linear;

public final class LinearProgram$ {
   public static final LinearProgram$ MODULE$ = new LinearProgram$();
   private static final LinearProgram.Solver mySolver;

   static {
      mySolver = LinearProgram.ApacheSimplexSolver$.MODULE$;
   }

   public LinearProgram.Solver mySolver() {
      return mySolver;
   }

   private LinearProgram$() {
   }
}
