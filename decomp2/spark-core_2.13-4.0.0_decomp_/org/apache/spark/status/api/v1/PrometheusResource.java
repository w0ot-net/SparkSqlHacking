package org.apache.spark.status.api.v1;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.package$;
import org.apache.spark.annotation.Experimental;
import org.apache.spark.executor.ExecutorMetrics;
import org.apache.spark.status.AppStatusStore;
import org.apache.spark.ui.SparkUI;
import org.sparkproject.jetty.servlet.ServletContextHandler;
import scala.MatchError;
import scala.Tuple2;
import scala.collection.IterableOnceOps;
import scala.collection.ArrayOps.;
import scala.collection.mutable.StringBuilder;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@Experimental
@Path("/executors")
@ScalaSignature(
   bytes = "\u0006\u0005!4Qa\u0002\u0005\u0001\u0011QAQa\b\u0001\u0005\u0002\u0005BQa\t\u0001\u0005\u0002\u0011:aa\u0015\u0005\t\u00029!fAB\u0004\t\u0011\u0003qQ\u000bC\u0003 \t\u0011\u0005a\u000bC\u0003X\t\u0011\u0005\u0001L\u0001\nQe>lW\r\u001e5fkN\u0014Vm]8ve\u000e,'BA\u0005\u000b\u0003\t1\u0018G\u0003\u0002\f\u0019\u0005\u0019\u0011\r]5\u000b\u00055q\u0011AB:uCR,8O\u0003\u0002\u0010!\u0005)1\u000f]1sW*\u0011\u0011CE\u0001\u0007CB\f7\r[3\u000b\u0003M\t1a\u001c:h'\r\u0001Qc\u0007\t\u0003-ei\u0011a\u0006\u0006\u00021\u0005)1oY1mC&\u0011!d\u0006\u0002\u0007\u0003:L(+\u001a4\u0011\u0005qiR\"\u0001\u0005\n\u0005yA!!E!qSJ+\u0017/^3ti\u000e{g\u000e^3yi\u00061A(\u001b8jiz\u001a\u0001\u0001F\u0001#!\ta\u0002!A\u0005fq\u0016\u001cW\u000f^8sgR\tQ\u0005\u0005\u0002'[9\u0011qe\u000b\t\u0003Q]i\u0011!\u000b\u0006\u0003U\u0001\na\u0001\u0010:p_Rt\u0014B\u0001\u0017\u0018\u0003\u0019\u0001&/\u001a3fM&\u0011af\f\u0002\u0007'R\u0014\u0018N\\4\u000b\u00051:\u0002F\u0001\u00022!\t\u0011\u0014(D\u00014\u0015\t!T'\u0001\u0002sg*\u0011agN\u0001\u0003oNT\u0011\u0001O\u0001\bU\u0006\\\u0017M\u001d;b\u0013\tQ4GA\u0002H\u000bRCCA\u0001\u001f@\u0001B\u0011!'P\u0005\u0003}M\u0012A\u0001U1uQ\u0006)a/\u00197vK\u0006\n\u0011)\u0001\u0006qe>lW\r\u001e5fkNDCAA\"@\rB\u0011!\u0007R\u0005\u0003\u000bN\u0012\u0001\u0002\u0015:pIV\u001cWm\u001d\u0017\u0002\u000f\u0006\n\u0001*\u0001\u0006uKb$x\u0006\u001d7bS:D#\u0001\u0001&\u0011\u0005-sU\"\u0001'\u000b\u00055s\u0011AC1o]>$\u0018\r^5p]&\u0011q\n\u0014\u0002\r\u000bb\u0004XM]5nK:$\u0018\r\u001c\u0015\u0005\u0001qz\u0014+I\u0001S\u0003)yS\r_3dkR|'o]\u0001\u0013!J|W.\u001a;iKV\u001c(+Z:pkJ\u001cW\r\u0005\u0002\u001d\tM\u0011A!\u0006\u000b\u0002)\u0006\tr-\u001a;TKJ4H.\u001a;IC:$G.\u001a:\u0015\u0005e\u001b\u0007C\u0001.b\u001b\u0005Y&B\u0001/^\u0003\u001d\u0019XM\u001d<mKRT!AX0\u0002\u000b),G\u000f^=\u000b\u0005\u0001\u0014\u0012aB3dY&\u00048/Z\u0005\u0003En\u0013QcU3sm2,GoQ8oi\u0016DH\u000fS1oI2,'\u000fC\u0003e\r\u0001\u0007Q-\u0001\u0004vSJ{w\u000e\u001e\t\u00039\u0019L!a\u001a\u0005\u0003\rUK%k\\8u\u0001"
)
public class PrometheusResource implements ApiRequestContext {
   @Context
   private ServletContext servletContext;
   @Context
   private HttpServletRequest httpRequest;

   public static ServletContextHandler getServletHandler(final UIRoot uiRoot) {
      return PrometheusResource$.MODULE$.getServletHandler(uiRoot);
   }

   public UIRoot uiRoot() {
      return ApiRequestContext.uiRoot$(this);
   }

   public ServletContext servletContext() {
      return this.servletContext;
   }

   public void servletContext_$eq(final ServletContext x$1) {
      this.servletContext = x$1;
   }

   public HttpServletRequest httpRequest() {
      return this.httpRequest;
   }

   public void httpRequest_$eq(final HttpServletRequest x$1) {
      this.httpRequest = x$1;
   }

   @GET
   @Path("prometheus")
   @Produces({"text/plain"})
   public String executors() {
      StringBuilder sb = new StringBuilder();
      String var10001 = package$.MODULE$.SPARK_VERSION_SHORT();
      sb.append("spark_info{version=\"" + var10001 + "\", revision=\"" + package$.MODULE$.SPARK_REVISION() + "\"} 1.0\n");
      AppStatusStore store = ((SparkUI)this.uiRoot()).store();
      store.executorList(true).foreach((executor) -> {
         $anonfun$executors$1(store, sb, executor);
         return BoxedUnit.UNIT;
      });
      return sb.toString();
   }

   // $FF: synthetic method
   public static final void $anonfun$executors$3(final Tuple2 x0$2) {
      if (x0$2 != null) {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$2);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$executors$5(final StringBuilder sb$1, final String prefix$1, final String labels$1, final ExecutorMetrics m) {
      String[] names = (String[])((Object[])(new String[]{"JVMHeapMemory", "JVMOffHeapMemory", "OnHeapExecutionMemory", "OffHeapExecutionMemory", "OnHeapStorageMemory", "OffHeapStorageMemory", "OnHeapUnifiedMemory", "OffHeapUnifiedMemory", "DirectPoolMemory", "MappedPoolMemory", "ProcessTreeJVMVMemory", "ProcessTreeJVMRSSMemory", "ProcessTreePythonVMemory", "ProcessTreePythonRSSMemory", "ProcessTreeOtherVMemory", "ProcessTreeOtherRSSMemory"}));
      .MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps((Object[])names), (name) -> sb$1.append(prefix$1 + name + "_bytes" + labels$1 + " " + m.getMetricValue(name) + "\n"));
      (new scala.collection.immutable..colon.colon("MinorGCCount", new scala.collection.immutable..colon.colon("MajorGCCount", new scala.collection.immutable..colon.colon("ConcurrentGCCount", scala.collection.immutable.Nil..MODULE$)))).foreach((name) -> sb$1.append(prefix$1 + name + "_total" + labels$1 + " " + m.getMetricValue(name) + "\n"));
      (new scala.collection.immutable..colon.colon("MinorGCTime", new scala.collection.immutable..colon.colon("MajorGCTime", new scala.collection.immutable..colon.colon("ConcurrentGCTime", scala.collection.immutable.Nil..MODULE$)))).foreach((name) -> sb$1.append(prefix$1 + name + "_seconds_total" + labels$1 + " " + (double)m.getMetricValue(name) * 0.001 + "\n"));
   }

   // $FF: synthetic method
   public static final void $anonfun$executors$1(final AppStatusStore store$1, final StringBuilder sb$1, final ExecutorSummary executor) {
      String prefix = "metrics_executor_";
      String labels = ((IterableOnceOps)(new scala.collection.immutable..colon.colon(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("application_id"), store$1.applicationInfo().id()), new scala.collection.immutable..colon.colon(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("application_name"), store$1.applicationInfo().name()), new scala.collection.immutable..colon.colon(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("executor_id"), executor.id()), scala.collection.immutable.Nil..MODULE$)))).map((x0$1) -> {
         if (x0$1 != null) {
            String k = (String)x0$1._1();
            String v = (String)x0$1._2();
            return k + "=\"" + v + "\"";
         } else {
            throw new MatchError(x0$1);
         }
      })).mkString("{", ", ", "}");
      sb$1.append(prefix + "rddBlocks" + labels + " " + executor.rddBlocks() + "\n");
      sb$1.append(prefix + "memoryUsed_bytes" + labels + " " + executor.memoryUsed() + "\n");
      sb$1.append(prefix + "diskUsed_bytes" + labels + " " + executor.diskUsed() + "\n");
      sb$1.append(prefix + "totalCores" + labels + " " + executor.totalCores() + "\n");
      sb$1.append(prefix + "maxTasks" + labels + " " + executor.maxTasks() + "\n");
      sb$1.append(prefix + "activeTasks" + labels + " " + executor.activeTasks() + "\n");
      sb$1.append(prefix + "failedTasks_total" + labels + " " + executor.failedTasks() + "\n");
      sb$1.append(prefix + "completedTasks_total" + labels + " " + executor.completedTasks() + "\n");
      sb$1.append(prefix + "totalTasks_total" + labels + " " + executor.totalTasks() + "\n");
      sb$1.append(prefix + "totalDuration_seconds_total" + labels + " " + (double)executor.totalDuration() * 0.001 + "\n");
      sb$1.append(prefix + "totalGCTime_seconds_total" + labels + " " + (double)executor.totalGCTime() * 0.001 + "\n");
      sb$1.append(prefix + "totalInputBytes_bytes_total" + labels + " " + executor.totalInputBytes() + "\n");
      sb$1.append(prefix + "totalShuffleRead_bytes_total" + labels + " " + executor.totalShuffleRead() + "\n");
      sb$1.append(prefix + "totalShuffleWrite_bytes_total" + labels + " " + executor.totalShuffleWrite() + "\n");
      sb$1.append(prefix + "maxMemory_bytes" + labels + " " + executor.maxMemory() + "\n");
      executor.executorLogs().foreach((x0$2) -> {
         $anonfun$executors$3(x0$2);
         return BoxedUnit.UNIT;
      });
      executor.memoryMetrics().foreach((m) -> {
         sb$1.append(prefix + "usedOnHeapStorageMemory_bytes" + labels + " " + m.usedOnHeapStorageMemory() + "\n");
         sb$1.append(prefix + "usedOffHeapStorageMemory_bytes" + labels + " " + m.usedOffHeapStorageMemory() + "\n");
         sb$1.append(prefix + "totalOnHeapStorageMemory_bytes" + labels + " " + m.totalOnHeapStorageMemory() + "\n");
         return sb$1.append(prefix + "totalOffHeapStorageMemory_bytes" + labels + " " + m.totalOffHeapStorageMemory() + "\n");
      });
      executor.peakMemoryMetrics().foreach((m) -> {
         $anonfun$executors$5(sb$1, prefix, labels, m);
         return BoxedUnit.UNIT;
      });
   }

   public PrometheusResource() {
      ApiRequestContext.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
