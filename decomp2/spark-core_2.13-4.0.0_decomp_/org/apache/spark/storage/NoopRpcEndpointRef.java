package org.apache.spark.storage;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkConf;
import org.apache.spark.rpc.RpcAddress;
import org.apache.spark.rpc.RpcEndpointRef;
import org.apache.spark.rpc.RpcTimeout;
import scala.concurrent.Future;
import scala.concurrent.Future.;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u00054Qa\u0002\u0005\u0001\u0011AA\u0001b\u0006\u0001\u0003\u0002\u0003\u0006I!\u0007\u0005\u0006;\u0001!\tA\b\u0005\u0006E\u0001!\te\t\u0005\u0006O\u0001!\t\u0005\u000b\u0005\u0006m\u0001!\te\u000e\u0005\u0006\u0003\u0002!\tE\u0011\u0002\u0013\u001d>|\u0007O\u00159d\u000b:$\u0007o\\5oiJ+gM\u0003\u0002\n\u0015\u000591\u000f^8sC\u001e,'BA\u0006\r\u0003\u0015\u0019\b/\u0019:l\u0015\tia\"\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002\u001f\u0005\u0019qN]4\u0014\u0005\u0001\t\u0002C\u0001\n\u0016\u001b\u0005\u0019\"B\u0001\u000b\u000b\u0003\r\u0011\boY\u0005\u0003-M\u0011aB\u00159d\u000b:$\u0007o\\5oiJ+g-\u0001\u0003d_:47\u0001\u0001\t\u00035mi\u0011AC\u0005\u00039)\u0011\u0011b\u00159be.\u001cuN\u001c4\u0002\rqJg.\u001b;?)\ty\u0012\u0005\u0005\u0002!\u00015\t\u0001\u0002C\u0003\u0018\u0005\u0001\u0007\u0011$A\u0004bI\u0012\u0014Xm]:\u0016\u0003\u0011\u0002\"AE\u0013\n\u0005\u0019\u001a\"A\u0003*qG\u0006#GM]3tg\u0006!a.Y7f+\u0005I\u0003C\u0001\u00164\u001d\tY\u0013\u0007\u0005\u0002-_5\tQF\u0003\u0002/1\u00051AH]8pizR\u0011\u0001M\u0001\u0006g\u000e\fG.Y\u0005\u0003e=\na\u0001\u0015:fI\u00164\u0017B\u0001\u001b6\u0005\u0019\u0019FO]5oO*\u0011!gL\u0001\u0005g\u0016tG\r\u0006\u00029yA\u0011\u0011HO\u0007\u0002_%\u00111h\f\u0002\u0005+:LG\u000fC\u0003>\u000b\u0001\u0007a(A\u0004nKN\u001c\u0018mZ3\u0011\u0005ez\u0014B\u0001!0\u0005\r\te._\u0001\u0004CN\\WCA\"N)\r!5\f\u0018\u000b\u0003\u000bN\u00032AR%L\u001b\u00059%B\u0001%0\u0003)\u0019wN\\2veJ,g\u000e^\u0005\u0003\u0015\u001e\u0013aAR;ukJ,\u0007C\u0001'N\u0019\u0001!QA\u0014\u0004C\u0002=\u0013\u0011\u0001V\t\u0003!z\u0002\"!O)\n\u0005I{#a\u0002(pi\"Lgn\u001a\u0005\b)\u001a\t\t\u0011q\u0001V\u0003))g/\u001b3f]\u000e,G%\r\t\u0004-f[U\"A,\u000b\u0005a{\u0013a\u0002:fM2,7\r^\u0005\u00035^\u0013\u0001b\u00117bgN$\u0016m\u001a\u0005\u0006{\u0019\u0001\rA\u0010\u0005\u0006;\u001a\u0001\rAX\u0001\bi&lWm\\;u!\t\u0011r,\u0003\u0002a'\tQ!\u000b]2US6,w.\u001e;"
)
public class NoopRpcEndpointRef extends RpcEndpointRef {
   public RpcAddress address() {
      return null;
   }

   public String name() {
      return "fallback";
   }

   public void send(final Object message) {
   }

   public Future ask(final Object message, final RpcTimeout timeout, final ClassTag evidence$1) {
      return .MODULE$.apply(() -> BoxesRunTime.boxToBoolean(true), scala.concurrent.ExecutionContext.Implicits..MODULE$.global());
   }

   public NoopRpcEndpointRef(final SparkConf conf) {
      super(conf);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
