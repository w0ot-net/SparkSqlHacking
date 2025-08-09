package scala.reflect.internal;

public final class SomePhase$ extends Phase {
   public static final SomePhase$ MODULE$ = new SomePhase$();

   public String name() {
      return "<some phase>";
   }

   public void run() {
      throw new Error("SomePhase.run");
   }

   private SomePhase$() {
      super(NoPhase$.MODULE$);
   }
}
