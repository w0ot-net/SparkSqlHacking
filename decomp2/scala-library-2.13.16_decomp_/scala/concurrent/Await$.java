package scala.concurrent;

import java.lang.invoke.SerializedLambda;
import java.util.concurrent.TimeoutException;
import scala.Function0;
import scala.concurrent.duration.Duration;

public final class Await$ {
   public static final Await$ MODULE$ = new Await$();

   public final Awaitable ready(final Awaitable awaitable, final Duration atMost) throws TimeoutException, InterruptedException {
      if (awaitable instanceof Future && ((Future)awaitable).isCompleted()) {
         return awaitable.ready(atMost, AwaitPermission$.MODULE$);
      } else {
         package$ var10000 = package$.MODULE$;
         Function0 blocking_body = () -> awaitable.ready(atMost, AwaitPermission$.MODULE$);
         return (Awaitable)BlockContext$.MODULE$.current().blockOn(blocking_body, AwaitPermission$.MODULE$);
      }
   }

   public final Object result(final Awaitable awaitable, final Duration atMost) throws TimeoutException, InterruptedException {
      if (awaitable instanceof Future) {
         Future var3 = (Future)awaitable;
         if (var3.isCompleted()) {
            return var3.result(atMost, AwaitPermission$.MODULE$);
         }
      }

      package$ var10000 = package$.MODULE$;
      Function0 blocking_body = () -> awaitable.result(atMost, AwaitPermission$.MODULE$);
      return BlockContext$.MODULE$.current().blockOn(blocking_body, AwaitPermission$.MODULE$);
   }

   private Await$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
