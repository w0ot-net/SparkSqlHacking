package org.apache.spark.rpc;

import java.util.concurrent.TimeoutException;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000592Q\u0001B\u0003\u0001\u000b5A\u0001\u0002\u0007\u0001\u0003\u0002\u0003\u0006IA\u0007\u0005\tO\u0001\u0011\t\u0011)A\u0005\u001d!)\u0001\u0006\u0001C\u0001S\t\u0019\"\u000b]2US6,w.\u001e;Fq\u000e,\u0007\u000f^5p]*\u0011aaB\u0001\u0004eB\u001c'B\u0001\u0005\n\u0003\u0015\u0019\b/\u0019:l\u0015\tQ1\"\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002\u0019\u0005\u0019qN]4\u0014\u0005\u0001q\u0001CA\b\u0017\u001b\u0005\u0001\"BA\t\u0013\u0003)\u0019wN\\2veJ,g\u000e\u001e\u0006\u0003'Q\tA!\u001e;jY*\tQ#\u0001\u0003kCZ\f\u0017BA\f\u0011\u0005A!\u0016.\\3pkR,\u0005pY3qi&|g.A\u0004nKN\u001c\u0018mZ3\u0004\u0001A\u00111\u0004\n\b\u00039\t\u0002\"!\b\u0011\u000e\u0003yQ!aH\r\u0002\rq\u0012xn\u001c;?\u0015\u0005\t\u0013!B:dC2\f\u0017BA\u0012!\u0003\u0019\u0001&/\u001a3fM&\u0011QE\n\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005\r\u0002\u0013!B2bkN,\u0017A\u0002\u001fj]&$h\bF\u0002+Y5\u0002\"a\u000b\u0001\u000e\u0003\u0015AQ\u0001G\u0002A\u0002iAQaJ\u0002A\u00029\u0001"
)
public class RpcTimeoutException extends TimeoutException {
   public RpcTimeoutException(final String message, final TimeoutException cause) {
      super(message);
      this.initCause(cause);
   }
}
