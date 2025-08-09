package org.apache.spark.security;

import java.net.Socket;
import scala.Function1;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00053QAB\u0004\u0001\u0013=A\u0001B\u0007\u0001\u0003\u0002\u0003\u0006I\u0001\b\u0005\t?\u0001\u0011\t\u0011)A\u0005A!A1\u0006\u0001B\u0001B\u0003%A\u0006C\u00038\u0001\u0011\u0005\u0001\bC\u0003>\u0001\u0011\u0005cH\u0001\tT_\u000e\\W\r\u001e$v]\u000e\u001cVM\u001d<fe*\u0011\u0001\"C\u0001\tg\u0016\u001cWO]5us*\u0011!bC\u0001\u0006gB\f'o\u001b\u0006\u0003\u00195\ta!\u00199bG\",'\"\u0001\b\u0002\u0007=\u0014xm\u0005\u0002\u0001!A\u0019\u0011C\u0005\u000b\u000e\u0003\u001dI!aE\u0004\u0003!M{7m[3u\u0003V$\bnU3sm\u0016\u0014\bCA\u000b\u0019\u001b\u00051\"\"A\f\u0002\u000bM\u001c\u0017\r\\1\n\u0005e1\"\u0001B+oSR\f!\"Y;uQ\"+G\u000e]3s\u0007\u0001\u0001\"!E\u000f\n\u0005y9!\u0001E*pG.,G/Q;uQ\"+G\u000e]3s\u0003)!\bN]3bI:\u000bW.\u001a\t\u0003C!r!A\t\u0014\u0011\u0005\r2R\"\u0001\u0013\u000b\u0005\u0015Z\u0012A\u0002\u001fs_>$h(\u0003\u0002(-\u00051\u0001K]3eK\u001aL!!\u000b\u0016\u0003\rM#(/\u001b8h\u0015\t9c#\u0001\u0003gk:\u001c\u0007\u0003B\u000b._QI!A\f\f\u0003\u0013\u0019+hn\u0019;j_:\f\u0004C\u0001\u00196\u001b\u0005\t$B\u0001\u001a4\u0003\rqW\r\u001e\u0006\u0002i\u0005!!.\u0019<b\u0013\t1\u0014G\u0001\u0004T_\u000e\\W\r^\u0001\u0007y%t\u0017\u000e\u001e \u0015\teR4\b\u0010\t\u0003#\u0001AQA\u0007\u0003A\u0002qAQa\b\u0003A\u0002\u0001BQa\u000b\u0003A\u00021\n\u0001\u0003[1oI2,7i\u001c8oK\u000e$\u0018n\u001c8\u0015\u0005Qy\u0004\"\u0002!\u0006\u0001\u0004y\u0013\u0001B:pG.\u0004"
)
public class SocketFuncServer extends SocketAuthServer {
   private final Function1 func;

   public void handleConnection(final Socket sock) {
      this.func.apply(sock);
   }

   public SocketFuncServer(final SocketAuthHelper authHelper, final String threadName, final Function1 func) {
      super(authHelper, threadName);
      this.func = func;
   }
}
