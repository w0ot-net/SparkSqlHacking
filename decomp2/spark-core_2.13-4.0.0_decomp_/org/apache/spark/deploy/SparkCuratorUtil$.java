package org.apache.spark.deploy;

import java.lang.invoke.SerializedLambda;
import java.util.List;
import java.util.Map;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.spark.SparkConf;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.config.Deploy$;
import org.apache.zookeeper.KeeperException;
import org.slf4j.Logger;
import scala.Function0;
import scala.StringContext;
import scala.jdk.CollectionConverters.;

public final class SparkCuratorUtil$ implements Logging {
   public static final SparkCuratorUtil$ MODULE$ = new SparkCuratorUtil$();
   private static final int ZK_CONNECTION_TIMEOUT_MILLIS;
   private static final int ZK_SESSION_TIMEOUT_MILLIS;
   private static final int RETRY_WAIT_MILLIS;
   private static final int MAX_RECONNECT_ATTEMPTS;
   private static transient Logger org$apache$spark$internal$Logging$$log_;

   static {
      Logging.$init$(MODULE$);
      ZK_CONNECTION_TIMEOUT_MILLIS = 15000;
      ZK_SESSION_TIMEOUT_MILLIS = 60000;
      RETRY_WAIT_MILLIS = 5000;
      MAX_RECONNECT_ATTEMPTS = 3;
   }

   public String logName() {
      return Logging.logName$(this);
   }

   public Logger log() {
      return Logging.log$(this);
   }

   public Logging.LogStringContext LogStringContext(final StringContext sc) {
      return Logging.LogStringContext$(this, sc);
   }

   public void withLogContext(final Map context, final Function0 body) {
      Logging.withLogContext$(this, context, body);
   }

   public void logInfo(final Function0 msg) {
      Logging.logInfo$(this, msg);
   }

   public void logInfo(final LogEntry entry) {
      Logging.logInfo$(this, entry);
   }

   public void logInfo(final LogEntry entry, final Throwable throwable) {
      Logging.logInfo$(this, entry, throwable);
   }

   public void logDebug(final Function0 msg) {
      Logging.logDebug$(this, msg);
   }

   public void logDebug(final LogEntry entry) {
      Logging.logDebug$(this, entry);
   }

   public void logDebug(final LogEntry entry, final Throwable throwable) {
      Logging.logDebug$(this, entry, throwable);
   }

   public void logTrace(final Function0 msg) {
      Logging.logTrace$(this, msg);
   }

   public void logTrace(final LogEntry entry) {
      Logging.logTrace$(this, entry);
   }

   public void logTrace(final LogEntry entry, final Throwable throwable) {
      Logging.logTrace$(this, entry, throwable);
   }

   public void logWarning(final Function0 msg) {
      Logging.logWarning$(this, msg);
   }

   public void logWarning(final LogEntry entry) {
      Logging.logWarning$(this, entry);
   }

   public void logWarning(final LogEntry entry, final Throwable throwable) {
      Logging.logWarning$(this, entry, throwable);
   }

   public void logError(final Function0 msg) {
      Logging.logError$(this, msg);
   }

   public void logError(final LogEntry entry) {
      Logging.logError$(this, entry);
   }

   public void logError(final LogEntry entry, final Throwable throwable) {
      Logging.logError$(this, entry, throwable);
   }

   public void logInfo(final Function0 msg, final Throwable throwable) {
      Logging.logInfo$(this, msg, throwable);
   }

   public void logDebug(final Function0 msg, final Throwable throwable) {
      Logging.logDebug$(this, msg, throwable);
   }

   public void logTrace(final Function0 msg, final Throwable throwable) {
      Logging.logTrace$(this, msg, throwable);
   }

   public void logWarning(final Function0 msg, final Throwable throwable) {
      Logging.logWarning$(this, msg, throwable);
   }

   public void logError(final Function0 msg, final Throwable throwable) {
      Logging.logError$(this, msg, throwable);
   }

   public boolean isTraceEnabled() {
      return Logging.isTraceEnabled$(this);
   }

   public void initializeLogIfNecessary(final boolean isInterpreter) {
      Logging.initializeLogIfNecessary$(this, isInterpreter);
   }

   public boolean initializeLogIfNecessary(final boolean isInterpreter, final boolean silent) {
      return Logging.initializeLogIfNecessary$(this, isInterpreter, silent);
   }

   public boolean initializeLogIfNecessary$default$2() {
      return Logging.initializeLogIfNecessary$default$2$(this);
   }

   public void initializeForcefully(final boolean isInterpreter, final boolean silent) {
      Logging.initializeForcefully$(this, isInterpreter, silent);
   }

   public Logger org$apache$spark$internal$Logging$$log_() {
      return org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      org$apache$spark$internal$Logging$$log_ = x$1;
   }

   private int ZK_CONNECTION_TIMEOUT_MILLIS() {
      return ZK_CONNECTION_TIMEOUT_MILLIS;
   }

   private int ZK_SESSION_TIMEOUT_MILLIS() {
      return ZK_SESSION_TIMEOUT_MILLIS;
   }

   private int RETRY_WAIT_MILLIS() {
      return RETRY_WAIT_MILLIS;
   }

   private int MAX_RECONNECT_ATTEMPTS() {
      return MAX_RECONNECT_ATTEMPTS;
   }

   public CuratorFramework newClient(final SparkConf conf, final String zkUrlConf) {
      String ZK_URL = conf.get(zkUrlConf);
      CuratorFramework zk = CuratorFrameworkFactory.newClient(ZK_URL, this.ZK_SESSION_TIMEOUT_MILLIS(), this.ZK_CONNECTION_TIMEOUT_MILLIS(), new ExponentialBackoffRetry(this.RETRY_WAIT_MILLIS(), this.MAX_RECONNECT_ATTEMPTS()));
      zk.start();
      return zk;
   }

   public String newClient$default$2() {
      return Deploy$.MODULE$.ZOOKEEPER_URL().key();
   }

   public void mkdir(final CuratorFramework zk, final String path) {
      if (zk.checkExists().forPath(path) == null) {
         try {
            zk.create().creatingParentsIfNeeded().forPath(path);
         } catch (KeeperException.NodeExistsException var5) {
         } catch (Exception var6) {
            throw var6;
         }

      }
   }

   public void deleteRecursive(final CuratorFramework zk, final String path) {
      if (zk.checkExists().forPath(path) != null) {
         .MODULE$.ListHasAsScala((List)zk.getChildren().forPath(path)).asScala().foreach((child) -> (Void)zk.delete().forPath(path + "/" + child));
         zk.delete().forPath(path);
      }
   }

   private SparkCuratorUtil$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
