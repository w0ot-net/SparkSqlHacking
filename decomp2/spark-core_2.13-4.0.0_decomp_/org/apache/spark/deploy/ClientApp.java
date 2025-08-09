package org.apache.spark.deploy;

import java.lang.invoke.SerializedLambda;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.apache.spark.SecurityManager;
import org.apache.spark.SecurityManager$;
import org.apache.spark.SparkConf;
import org.apache.spark.deploy.master.Master$;
import org.apache.spark.internal.config.ConfigEntry;
import org.apache.spark.internal.config.Network$;
import org.apache.spark.internal.config.OptionalConfigEntry;
import org.apache.spark.rpc.RpcAddress;
import org.apache.spark.rpc.RpcAddress$;
import org.apache.spark.rpc.RpcEndpointRef;
import org.apache.spark.rpc.RpcEnv;
import org.apache.spark.rpc.RpcEnv$;
import scala.collection.ArrayOps.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005Y2Qa\u0001\u0003\u0001\r1AQa\u0006\u0001\u0005\u0002eAQa\u0007\u0001\u0005Bq\u0011\u0011b\u00117jK:$\u0018\t\u001d9\u000b\u0005\u00151\u0011A\u00023fa2|\u0017P\u0003\u0002\b\u0011\u0005)1\u000f]1sW*\u0011\u0011BC\u0001\u0007CB\f7\r[3\u000b\u0003-\t1a\u001c:h'\r\u0001Qb\u0005\t\u0003\u001dEi\u0011a\u0004\u0006\u0002!\u0005)1oY1mC&\u0011!c\u0004\u0002\u0007\u0003:L(+\u001a4\u0011\u0005Q)R\"\u0001\u0003\n\u0005Y!!\u0001E*qCJ\\\u0017\t\u001d9mS\u000e\fG/[8o\u0003\u0019a\u0014N\\5u}\r\u0001A#\u0001\u000e\u0011\u0005Q\u0001\u0011!B:uCJ$HcA\u000f!aA\u0011aBH\u0005\u0003?=\u0011A!\u00168ji\")\u0011E\u0001a\u0001E\u0005!\u0011M]4t!\rq1%J\u0005\u0003I=\u0011Q!\u0011:sCf\u0004\"AJ\u0017\u000f\u0005\u001dZ\u0003C\u0001\u0015\u0010\u001b\u0005I#B\u0001\u0016\u0019\u0003\u0019a$o\\8u}%\u0011AfD\u0001\u0007!J,G-\u001a4\n\u00059z#AB*ue&twM\u0003\u0002-\u001f!)\u0011G\u0001a\u0001e\u0005!1m\u001c8g!\t\u0019D'D\u0001\u0007\u0013\t)dAA\u0005Ta\u0006\u00148nQ8oM\u0002"
)
public class ClientApp implements SparkApplication {
   public void start(final String[] args, final SparkConf conf) {
      ClientArguments driverArgs = new ClientArguments(args);
      if (!conf.contains((ConfigEntry)Network$.MODULE$.RPC_ASK_TIMEOUT())) {
         conf.set((OptionalConfigEntry)Network$.MODULE$.RPC_ASK_TIMEOUT(), (Object)"10s");
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      ((Logger)LogManager.getRootLogger()).setLevel(driverArgs.logLevel());
      RpcEnv rpcEnv = RpcEnv$.MODULE$.create("driverClient", org.apache.spark.util.Utils$.MODULE$.localHostName(), 0, conf, new SecurityManager(conf, SecurityManager$.MODULE$.$lessinit$greater$default$2(), SecurityManager$.MODULE$.$lessinit$greater$default$3()), RpcEnv$.MODULE$.create$default$6());
      RpcEndpointRef[] masterEndpoints = (RpcEndpointRef[]).MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(.MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])driverArgs.masters()), (sparkUrl) -> RpcAddress$.MODULE$.fromSparkURL(sparkUrl), scala.reflect.ClassTag..MODULE$.apply(RpcAddress.class))), (x$1) -> rpcEnv.setupEndpointRef(x$1, Master$.MODULE$.ENDPOINT_NAME()), scala.reflect.ClassTag..MODULE$.apply(RpcEndpointRef.class));
      rpcEnv.setupEndpoint("client", new ClientEndpoint(rpcEnv, driverArgs, org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(masterEndpoints).toImmutableArraySeq(), conf));
      rpcEnv.awaitTermination();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
