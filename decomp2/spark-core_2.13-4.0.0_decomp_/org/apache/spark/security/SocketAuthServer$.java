package org.apache.spark.security;

import java.io.BufferedOutputStream;
import java.lang.invoke.SerializedLambda;
import java.net.Socket;
import org.apache.spark.util.Utils$;
import scala.Function1;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;

public final class SocketAuthServer$ {
   public static final SocketAuthServer$ MODULE$ = new SocketAuthServer$();

   public Object[] serveToStream(final String threadName, final SocketAuthHelper authHelper, final Function1 writeFunc) {
      Function1 handleFunc = (sock) -> {
         $anonfun$serveToStream$1(writeFunc, sock);
         return BoxedUnit.UNIT;
      };
      SocketFuncServer server = new SocketFuncServer(authHelper, threadName, handleFunc);
      return new Object[]{BoxesRunTime.boxToInteger(server.port()), server.secret(), server};
   }

   // $FF: synthetic method
   public static final void $anonfun$serveToStream$1(final Function1 writeFunc$1, final Socket sock) {
      BufferedOutputStream out = new BufferedOutputStream(sock.getOutputStream());
      Utils$.MODULE$.tryWithSafeFinally((JFunction0.mcV.sp)() -> writeFunc$1.apply(out), (JFunction0.mcV.sp)() -> out.close());
   }

   private SocketAuthServer$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
