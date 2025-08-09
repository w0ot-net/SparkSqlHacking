package org.apache.spark.rpc;

import java.io.Serializable;
import java.net.URI;
import org.apache.spark.util.Utils$;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class RpcAddress$ implements Serializable {
   public static final RpcAddress$ MODULE$ = new RpcAddress$();

   public RpcAddress apply(final String host, final int port) {
      return new RpcAddress(Utils$.MODULE$.normalizeIpIfNeeded(host), port);
   }

   public RpcAddress fromUrlString(final String uri) {
      URI uriObj = new URI(uri);
      return this.apply(uriObj.getHost(), uriObj.getPort());
   }

   public RpcAddress fromSparkURL(final String sparkUrl) {
      Tuple2 var4 = Utils$.MODULE$.extractHostPortFromSparkUrl(sparkUrl);
      if (var4 != null) {
         String host = (String)var4._1();
         int port = var4._2$mcI$sp();
         Tuple2 var3 = new Tuple2(host, BoxesRunTime.boxToInteger(port));
         String host = (String)var3._1();
         int port = var3._2$mcI$sp();
         return this.apply(host, port);
      } else {
         throw new MatchError(var4);
      }
   }

   public Option unapply(final RpcAddress x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.host(), BoxesRunTime.boxToInteger(x$0.port()))));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(RpcAddress$.class);
   }

   private RpcAddress$() {
   }
}
