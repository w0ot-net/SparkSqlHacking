package scala.reflect.internal;

public final class NoPhase$ extends Phase {
   public static final NoPhase$ MODULE$ = new NoPhase$();

   public String name() {
      return "<no phase>";
   }

   public boolean keepsTypeParams() {
      return false;
   }

   public void run() {
      throw new Error("NoPhase.run");
   }

   private NoPhase$() {
      super((Phase)null);
   }
}
