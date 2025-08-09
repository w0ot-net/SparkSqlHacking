package scala.sys;

import scala.Function0;

public final class ShutdownHookThread$ {
   public static final ShutdownHookThread$ MODULE$ = new ShutdownHookThread$();
   private static int hookNameCount = 0;

   private synchronized String hookName() {
      ++hookNameCount;
      return (new StringBuilder(12)).append("shutdownHook").append(hookNameCount).toString();
   }

   public ShutdownHookThread apply(final Function0 body) {
      ShutdownHookThread t = new ShutdownHookThread(() -> body.apply$mcV$sp(), this.hookName());
      Runtime.getRuntime().addShutdownHook(t);
      return t;
   }

   private ShutdownHookThread$() {
   }
}
