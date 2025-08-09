package org.apache.spark.ui;

import java.io.Serializable;
import org.apache.spark.SparkConf;
import org.sparkproject.jetty.server.Server;
import org.sparkproject.jetty.server.handler.ContextHandlerCollection;
import scala.Option;
import scala.Some;
import scala.Tuple5;
import scala.None.;
import scala.runtime.AbstractFunction5;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class ServerInfo$ extends AbstractFunction5 implements Serializable {
   public static final ServerInfo$ MODULE$ = new ServerInfo$();

   public final String toString() {
      return "ServerInfo";
   }

   public ServerInfo apply(final Server server, final int boundPort, final Option securePort, final SparkConf conf, final ContextHandlerCollection rootHandler) {
      return new ServerInfo(server, boundPort, securePort, conf, rootHandler);
   }

   public Option unapply(final ServerInfo x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple5(x$0.server(), BoxesRunTime.boxToInteger(x$0.boundPort()), x$0.securePort(), x$0.org$apache$spark$ui$ServerInfo$$conf(), x$0.org$apache$spark$ui$ServerInfo$$rootHandler())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ServerInfo$.class);
   }

   private ServerInfo$() {
   }
}
