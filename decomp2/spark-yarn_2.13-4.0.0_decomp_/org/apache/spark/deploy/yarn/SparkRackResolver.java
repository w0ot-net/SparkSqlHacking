package org.apache.spark.deploy.yarn;

import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.net.CachedDNSToSwitchMapping;
import org.apache.hadoop.net.DNSToSwitchMapping;
import org.apache.hadoop.net.Node;
import org.apache.hadoop.net.NodeBase;
import org.apache.hadoop.net.ScriptBasedMapping;
import org.apache.hadoop.util.ReflectionUtils;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.slf4j.Logger;
import org.sparkproject.guava.base.Strings;
import scala.Function0;
import scala.MatchError;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.IterableOps;
import scala.collection.immutable.;
import scala.collection.immutable.Seq;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.Buffer;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005U4Qa\u0004\t\u0001)iA\u0001b\n\u0001\u0003\u0002\u0003\u0006I!\u000b\u0005\u0006a\u0001!\t!\r\u0005\bk\u0001\u0011\r\u0011\"\u00037\u0011\u0019i\u0004\u0001)A\u0005o!)a\b\u0001C\u0001\u007f!)a\b\u0001C\u0001\u001b\")Q\f\u0001C\u0005=\u001e)\u0001\r\u0005E\u0001C\u001a)q\u0002\u0005E\u0001E\")\u0001'\u0003C\u0001G\"IA-\u0003a\u0001\u0002\u0004%I!\u001a\u0005\nM&\u0001\r\u00111A\u0005\n\u001dD\u0011\"\\\u0005A\u0002\u0003\u0005\u000b\u0015\u0002\u001a\t\u000bILA\u0011A:\u0003#M\u0003\u0018M]6SC\u000e\\'+Z:pYZ,'O\u0003\u0002\u0012%\u0005!\u00110\u0019:o\u0015\t\u0019B#\u0001\u0004eKBdw.\u001f\u0006\u0003+Y\tQa\u001d9be.T!a\u0006\r\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005I\u0012aA8sON\u0019\u0001aG\u0011\u0011\u0005qyR\"A\u000f\u000b\u0003y\tQa]2bY\u0006L!\u0001I\u000f\u0003\r\u0005s\u0017PU3g!\t\u0011S%D\u0001$\u0015\t!C#\u0001\u0005j]R,'O\\1m\u0013\t13EA\u0004M_\u001e<\u0017N\\4\u0002\t\r|gNZ\u0002\u0001!\tQc&D\u0001,\u0015\t9CF\u0003\u0002.-\u00051\u0001.\u00193p_BL!aL\u0016\u0003\u001b\r{gNZ5hkJ\fG/[8o\u0003\u0019a\u0014N\\5u}Q\u0011!\u0007\u000e\t\u0003g\u0001i\u0011\u0001\u0005\u0005\u0006O\t\u0001\r!K\u0001\u0013I:\u001cHk\\*xSR\u001c\u0007.T1qa&tw-F\u00018!\tA4(D\u0001:\u0015\tQD&A\u0002oKRL!\u0001P\u001d\u0003%\u0011s5\u000bV8To&$8\r['baBLgnZ\u0001\u0014I:\u001cHk\\*xSR\u001c\u0007.T1qa&tw\rI\u0001\be\u0016\u001cx\u000e\u001c<f)\t\u00015\n\u0005\u0002B\u0011:\u0011!I\u0012\t\u0003\u0007vi\u0011\u0001\u0012\u0006\u0003\u000b\"\na\u0001\u0010:p_Rt\u0014BA$\u001e\u0003\u0019\u0001&/\u001a3fM&\u0011\u0011J\u0013\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005\u001dk\u0002\"\u0002'\u0006\u0001\u0004\u0001\u0015\u0001\u00035pgRt\u0015-\\3\u0015\u00059S\u0006cA(U/:\u0011\u0001K\u0015\b\u0003\u0007FK\u0011AH\u0005\u0003'v\tq\u0001]1dW\u0006<W-\u0003\u0002V-\n\u00191+Z9\u000b\u0005Mk\u0002C\u0001\u001dY\u0013\tI\u0016H\u0001\u0003O_\u0012,\u0007\"B.\u0007\u0001\u0004a\u0016!\u00035pgRt\u0015-\\3t!\ryE\u000bQ\u0001\fG>\u0014XMU3t_24X\r\u0006\u0002O?\")1l\u0002a\u00019\u0006\t2\u000b]1sWJ\u000b7m\u001b*fg>dg/\u001a:\u0011\u0005MJ1cA\u0005\u001cCQ\t\u0011-\u0001\u0005j]N$\u0018M\\2f+\u0005\u0011\u0014\u0001D5ogR\fgnY3`I\u0015\fHC\u00015l!\ta\u0012.\u0003\u0002k;\t!QK\\5u\u0011\u001daG\"!AA\u0002I\n1\u0001\u001f\u00132\u0003%Ign\u001d;b]\u000e,\u0007\u0005\u000b\u0002\u000e_B\u0011A\u0004]\u0005\u0003cv\u0011\u0001B^8mCRLG.Z\u0001\u0004O\u0016$HC\u0001\u001au\u0011\u00159c\u00021\u0001*\u0001"
)
public class SparkRackResolver implements Logging {
   private final DNSToSwitchMapping dnsToSwitchMapping;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static SparkRackResolver get(final Configuration conf) {
      return SparkRackResolver$.MODULE$.get(conf);
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
      return this.org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      this.org$apache$spark$internal$Logging$$log_ = x$1;
   }

   private DNSToSwitchMapping dnsToSwitchMapping() {
      return this.dnsToSwitchMapping;
   }

   public String resolve(final String hostName) {
      return ((Node)this.coreResolve(new .colon.colon(hostName, scala.collection.immutable.Nil..MODULE$)).head()).getNetworkLocation();
   }

   public Seq resolve(final Seq hostNames) {
      return this.coreResolve(hostNames);
   }

   private Seq coreResolve(final Seq hostNames) {
      if (hostNames.isEmpty()) {
         return (Seq)scala.package..MODULE$.Seq().empty();
      } else {
         ArrayBuffer nodes = new ArrayBuffer();
         Buffer rNameList = scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(this.dnsToSwitchMapping().resolve(scala.jdk.CollectionConverters..MODULE$.SeqHasAsJava(hostNames.toList()).asJava())).asScala();
         if (rNameList != null && !rNameList.isEmpty()) {
            ((IterableOps)hostNames.zip(rNameList)).withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$coreResolve$3(check$ifrefutable$1))).foreach((x$2) -> {
               if (x$2 != null) {
                  String hostName = (String)x$2._1();
                  String rName = (String)x$2._2();
                  if (Strings.isNullOrEmpty(rName)) {
                     nodes.$plus$eq(new NodeBase(hostName, "/default-rack"));
                     this.logDebug((Function0)(() -> "Could not resolve " + hostName + ". Falling back to /default-rack"));
                     return BoxedUnit.UNIT;
                  } else {
                     return nodes.$plus$eq(new NodeBase(hostName, rName));
                  }
               } else {
                  throw new MatchError(x$2);
               }
            });
         } else {
            hostNames.foreach((x$1) -> (ArrayBuffer)nodes.$plus$eq(new NodeBase(x$1, "/default-rack")));
            this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Got an error when resolving hostNames. "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Falling back to ", " for all"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NODE_LOCATION..MODULE$, "/default-rack")}))))));
         }

         return nodes.toList();
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$coreResolve$3(final Tuple2 check$ifrefutable$1) {
      return check$ifrefutable$1 != null;
   }

   public SparkRackResolver(final Configuration conf) {
      Logging.$init$(this);
      Class dnsToSwitchMappingClass = conf.getClass("net.topology.node.switch.mapping.impl", ScriptBasedMapping.class, DNSToSwitchMapping.class);
      DNSToSwitchMapping var4 = (DNSToSwitchMapping)ReflectionUtils.newInstance(dnsToSwitchMappingClass, conf);
      CachedDNSToSwitchMapping var10001;
      if (var4 instanceof CachedDNSToSwitchMapping var5) {
         var10001 = var5;
      } else {
         var10001 = new CachedDNSToSwitchMapping(var4);
      }

      this.dnsToSwitchMapping = var10001;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
