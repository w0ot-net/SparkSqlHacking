package org.apache.spark.rpc;

import org.apache.spark.SparkException;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00152Qa\u0001\u0003\u0001\t1A\u0001\"\u0005\u0001\u0003\u0002\u0003\u0006Ia\u0005\u0005\u0006A\u0001!\t!\t\u0002\u001d%B\u001cWI\u001c3q_&tGOT8u\r>,h\u000eZ#yG\u0016\u0004H/[8o\u0015\t)a!A\u0002sa\u000eT!a\u0002\u0005\u0002\u000bM\u0004\u0018M]6\u000b\u0005%Q\u0011AB1qC\u000eDWMC\u0001\f\u0003\ry'oZ\n\u0003\u00015\u0001\"AD\b\u000e\u0003\u0019I!\u0001\u0005\u0004\u0003\u001dM\u0003\u0018M]6Fq\u000e,\u0007\u000f^5p]\u0006\u0019QO]5\u0004\u0001A\u0011A#\b\b\u0003+m\u0001\"AF\r\u000e\u0003]Q!\u0001\u0007\n\u0002\rq\u0012xn\u001c;?\u0015\u0005Q\u0012!B:dC2\f\u0017B\u0001\u000f\u001a\u0003\u0019\u0001&/\u001a3fM&\u0011ad\b\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005qI\u0012A\u0002\u001fj]&$h\b\u0006\u0002#IA\u00111\u0005A\u0007\u0002\t!)\u0011C\u0001a\u0001'\u0001"
)
public class RpcEndpointNotFoundException extends SparkException {
   public RpcEndpointNotFoundException(final String uri) {
      super("Cannot find endpoint: " + uri);
   }
}
