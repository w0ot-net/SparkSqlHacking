package org.apache.spark.rpc;

import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import org.apache.spark.SparkException;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.ModuleSerializationProxy;

public final class RpcEndpointAddress$ implements Serializable {
   public static final RpcEndpointAddress$ MODULE$ = new RpcEndpointAddress$();

   public RpcEndpointAddress apply(final String host, final int port, final String name) {
      return new RpcEndpointAddress(host, port, name);
   }

   public RpcEndpointAddress apply(final String sparkUrl) {
      try {
         URI uri = new URI(sparkUrl);
         String host = uri.getHost();
         int port = uri.getPort();
         String name = uri.getUserInfo();
         String var10000 = uri.getScheme();
         String var6 = "spark";
         if (var10000 == null) {
            if (var6 != null) {
               throw new SparkException("Invalid Spark URL: " + sparkUrl);
            }
         } else if (!var10000.equals(var6)) {
            throw new SparkException("Invalid Spark URL: " + sparkUrl);
         }

         if (host != null && port >= 0 && name != null && (uri.getPath() == null || uri.getPath().isEmpty()) && uri.getFragment() == null && uri.getQuery() == null) {
            return new RpcEndpointAddress(host, port, name);
         } else {
            throw new SparkException("Invalid Spark URL: " + sparkUrl);
         }
      } catch (URISyntaxException var8) {
         throw new SparkException("Invalid Spark URL: " + sparkUrl, var8);
      }
   }

   public RpcEndpointAddress apply(final RpcAddress rpcAddress, final String name) {
      return new RpcEndpointAddress(rpcAddress, name);
   }

   public Option unapply(final RpcEndpointAddress x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.rpcAddress(), x$0.name())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(RpcEndpointAddress$.class);
   }

   private RpcEndpointAddress$() {
   }
}
