package org.apache.spark.status.api.v1;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.StreamingOutput;
import java.io.OutputStream;
import java.lang.invoke.SerializedLambda;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.zip.ZipOutputStream;
import org.apache.spark.SparkContext;
import org.apache.spark.SparkContext$;
import org.apache.spark.util.Utils$;
import scala.Function1;
import scala.Option;
import scala.Some;
import scala.collection.immutable.Seq;
import scala.math.Ordering.String.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@Produces({"application/json"})
@ScalaSignature(
   bytes = "\u0006\u0005\t5a!B\t\u0013\u0001Iq\u0002\"B\u0015\u0001\t\u0003Y\u0003\"B\u0017\u0001\t\u0003q\u0003\"B2\u0001\t\u0003!\u0007\"B:\u0001\t\u0003!\b\"B?\u0001\t\u0003q\bbBA\u0017\u0001\u0011\u0005\u0011q\u0006\u0005\u0007\u0003\u0017\u0002A\u0011\u0001;\t\u000f\u0005U\u0003\u0001\"\u0001\u0002X!9\u0011\u0011\u000e\u0001\u0005\u0002\u0005-\u0004bBA?\u0001\u0011\u0005\u0011q\u0010\u0005\b\u0003#\u0003A\u0011AAJ\u0011\u001d\t)\u000b\u0001C\u0001\u0003OCq!a.\u0001\t\u0003\tI\fC\u0004\u0002^\u0002!\t!a8\t\u000f\u0005=\b\u0001\"\u0003\u0002r\"9\u00111 \u0001\u0005\n\u0005u(aG!cgR\u0014\u0018m\u0019;BaBd\u0017nY1uS>t'+Z:pkJ\u001cWM\u0003\u0002\u0014)\u0005\u0011a/\r\u0006\u0003+Y\t1!\u00199j\u0015\t9\u0002$\u0001\u0004ti\u0006$Xo\u001d\u0006\u00033i\tQa\u001d9be.T!a\u0007\u000f\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005i\u0012aA8sON\u0019\u0001aH\u0013\u0011\u0005\u0001\u001aS\"A\u0011\u000b\u0003\t\nQa]2bY\u0006L!\u0001J\u0011\u0003\r\u0005s\u0017PU3g!\t1s%D\u0001\u0013\u0013\tA#CA\bCCN,\u0017\t\u001d9SKN|WO]2f\u0003\u0019a\u0014N\\5u}\r\u0001A#\u0001\u0017\u0011\u0005\u0019\u0002\u0011\u0001\u00036pENd\u0015n\u001d;\u0015\u0005=r\u0004c\u0001\u00199w9\u0011\u0011G\u000e\b\u0003eUj\u0011a\r\u0006\u0003i)\na\u0001\u0010:p_Rt\u0014\"\u0001\u0012\n\u0005]\n\u0013a\u00029bG.\fw-Z\u0005\u0003si\u00121aU3r\u0015\t9\u0014\u0005\u0005\u0002'y%\u0011QH\u0005\u0002\b\u0015>\u0014G)\u0019;b\u0011\u0015y$\u00011\u0001A\u0003!\u0019H/\u0019;vg\u0016\u001c\bcA!G\u00116\t!I\u0003\u0002D\t\u0006!Q\u000f^5m\u0015\u0005)\u0015\u0001\u00026bm\u0006L!a\u0012\"\u0003\t1K7\u000f\u001e\t\u0003\u0013*k\u0011\u0001G\u0005\u0003\u0017b\u0011!CS8c\u000bb,7-\u001e;j_:\u001cF/\u0019;vg\"\"a(T,Y!\tqU+D\u0001P\u0015\t\u0001\u0016+\u0001\u0002sg*\u0011!kU\u0001\u0003oNT\u0011\u0001V\u0001\bU\u0006\\\u0017M\u001d;b\u0013\t1vJ\u0001\u0006Rk\u0016\u0014\u0018\u0010U1sC6\fQA^1mk\u0016\f\u0013a\u0006\u0015\u0003\u0005i\u0003\"AT.\n\u0005q{%aA$F)\"\"!AX,b!\tqu,\u0003\u0002a\u001f\n!\u0001+\u0019;iC\u0005\u0011\u0017\u0001\u00026pEN\faa\u001c8f\u0015>\u0014GCA\u001ef\u0011\u001517\u00011\u0001h\u0003\u0015QwNY%e!\t\u0001\u0003.\u0003\u0002jC\t\u0019\u0011J\u001c;)\t\u0015\\wK\u001c\t\u0003\u001d2L!!\\(\u0003\u0013A\u000bG\u000f\u001b)be\u0006l\u0017%\u00014)\u0005\rQ\u0006\u0006B\u0002_/F\f\u0013A]\u0001\u0012U>\u00147oL>k_\nLEM\u000f\u0011]I.j\u0018\u0001D3yK\u000e,Ho\u001c:MSN$H#A;\u0011\u0007ABd\u000f\u0005\u0002'o&\u0011\u0001P\u0005\u0002\u0010\u000bb,7-\u001e;peN+X.\\1ss\"\u0012AA\u0017\u0015\u0005\ty;60I\u0001}\u0003%)\u00070Z2vi>\u00148/\u0001\u0006uQJ,\u0017\r\u001a#v[B$2a`A\u0006!\u0015\u0001\u0013\u0011AA\u0003\u0013\r\t\u0019!\t\u0002\u0006\u0003J\u0014\u0018-\u001f\t\u0004M\u0005\u001d\u0011bAA\u0005%\t\u0001B\u000b\u001b:fC\u0012\u001cF/Y2l)J\f7-\u001a\u0005\b\u0003\u001b)\u0001\u0019AA\b\u0003\u0019)\u00070Z2JIB!\u0011\u0011CA\r\u001d\u0011\t\u0019\"!\u0006\u0011\u0005I\n\u0013bAA\fC\u00051\u0001K]3eK\u001aLA!a\u0007\u0002\u001e\t11\u000b\u001e:j]\u001eT1!a\u0006\"Q\u0019\tYa[,\u0002\"\u0005\u0012\u00111E\u0001\u000bKb,7-\u001e;pe&#\u0007FA\u0003[Q\u0015)alVA\u0015C\t\tY#\u0001\u0010fq\u0016\u001cW\u000f^8sg>ZX\r_3dkR|'/\u00133~_QD'/Z1eg\u0006\tr-\u001a;UCN\\G\u000b\u001b:fC\u0012$U/\u001c9\u0015\r\u0005\u0015\u0011\u0011GA \u0011\u001d\t\u0019D\u0002a\u0001\u0003k\ta\u0001^1tW&#\u0007c\u0001\u0011\u00028%\u0019\u0011\u0011H\u0011\u0003\t1{gn\u001a\u0015\u0007\u0003ciu+!\u0010\"\u0005\u0005M\u0002bBA\u0007\r\u0001\u0007\u0011q\u0002\u0015\u0007\u0003\u007fiu+!\t)\u0005\u0019Q\u0006&\u0002\u0004_/\u0006\u001d\u0013EAA%\u0003\u001d!\bN]3bIN\fq\"\u00197m\u000bb,7-\u001e;pe2K7\u000f\u001e\u0015\u0003\u000fiCSa\u00020X\u0003#\n#!a\u0015\u0002\u0019\u0005dG.\u001a=fGV$xN]:\u0002\u001d\u0005dG\u000e\u0015:pG\u0016\u001c8\u000fT5tiR\u0011\u0011\u0011\f\t\u0005aa\nY\u0006E\u0002'\u0003;J1!a\u0018\u0013\u00059\u0001&o\\2fgN\u001cV/\\7befD#\u0001\u0003.)\u000b!qv+!\u001a\"\u0005\u0005\u001d\u0014aF1mY6L7oY3mY\u0006tWm\\;taJ|7-Z:t\u0003\u0019\u0019H/Y4fgR\u0011\u0011Q\u000e\t\u0007\u0003#\ty'a\u001d\n\t\u0005E\u0014Q\u0004\u0002\u0006\u00072\f7o\u001d\t\u0004M\u0005U\u0014bAA<%\tq1\u000b^1hKN\u0014Vm]8ve\u000e,\u0007&B\u0005_/\u0006m\u0014EAA5\u0003\u001d\u0011H\r\u001a'jgR$\"!!!\u0011\tAB\u00141\u0011\t\u0004M\u0005\u0015\u0015bAAD%\tq!\u000b\u0012#Ti>\u0014\u0018mZ3J]\u001a|\u0007F\u0001\u0006[Q\u0015QalVAGC\t\ty)A\u0006ti>\u0014\u0018mZ30e\u0012$\u0017a\u0002:eI\u0012\u000bG/\u0019\u000b\u0005\u0003\u0007\u000b)\n\u0003\u0004\u0002\u0018.\u0001\raZ\u0001\u0006e\u0012$\u0017\n\u001a\u0015\u0007\u0003+[w+a'\"\u0005\u0005]\u0005FA\u0006[Q\u0015YalVAQC\t\t\u0019+\u0001\rti>\u0014\u0018mZ30e\u0012$wf\u001f:eI&#'\b\t/eWu\fq\"\u001a8wSJ|g.\\3oi&sgm\u001c\u000b\u0003\u0003S\u00032AJAV\u0013\r\tiK\u0005\u0002\u001b\u0003B\u0004H.[2bi&|g.\u00128wSJ|g.\\3oi&sgm\u001c\u0015\u0003\u0019iCS\u0001\u00040X\u0003g\u000b#!!.\u0002\u0017\u0015tg/\u001b:p]6,g\u000e^\u0001\rO\u0016$XI^3oi2{wm\u001d\u000b\u0003\u0003w\u0003B!!0\u0002D6\u0011\u0011q\u0018\u0006\u0004\u0003\u0003|\u0015\u0001B2pe\u0016LA!!2\u0002@\nA!+Z:q_:\u001cX\r\u000b\u0002\u000e5\"*QBX,\u0002L\u0006\u0012\u0011QZ\u0001\u0005Y><7\u000f\u000b\u0004\u000e\u0003#<\u0016q\u001b\t\u0004\u001d\u0006M\u0017bAAk\u001f\nA\u0001K]8ek\u000e,7\u000f\f\u0002\u0002Z\u0006\u0012\u00111\\\u0001\u0019CB\u0004H.[2bi&|gnL8di\u0016$Xf\u001d;sK\u0006l\u0017AE1qa2L7-\u0019;j_:\fE\u000f^3naR$\"!!9\u0011\r\u0005E\u0011qNAr!\r1\u0013Q]\u0005\u0004\u0003O\u0014\"!H(oK\u0006\u0003\b\u000f\\5dCRLwN\\!ui\u0016l\u0007\u000f\u001e*fg>,(oY3)\u000b9qv+a;\"\u0005\u00055\u0018aC>biR,W\u000e\u001d;JIv\fqb\u00195fG.,\u00050Z2vi>\u0014\u0018\n\u001a\u000b\u0005\u0003g\fI\u0010E\u0002!\u0003kL1!a>\"\u0005\u0011)f.\u001b;\t\u000f\u00055q\u00021\u0001\u0002\u0010\u000592\r[3dW\u0006sGmR3u'B\f'o[\"p]R,\u0007\u0010\u001e\u000b\u0003\u0003\u007f\u00042!\u0013B\u0001\u0013\r\u0011\u0019\u0001\u0007\u0002\r'B\f'o[\"p]R,\u0007\u0010\u001e\u0015\u0007\u0001\u0005EwKa\u0002-\u0005\t%\u0011E\u0001B\u0006\u0003A\t\u0007\u000f\u001d7jG\u0006$\u0018n\u001c80UN|g\u000e"
)
public class AbstractApplicationResource implements BaseAppResource {
   @PathParam("appId")
   private String appId;
   @PathParam("attemptId")
   private String attemptId;
   @Context
   private ServletContext servletContext;
   @Context
   private HttpServletRequest httpRequest;

   public Object withUI(final Function1 fn) {
      return BaseAppResource.withUI$(this, fn);
   }

   public void checkUIViewPermissions() {
      BaseAppResource.checkUIViewPermissions$(this);
   }

   public UIRoot uiRoot() {
      return ApiRequestContext.uiRoot$(this);
   }

   public String appId() {
      return this.appId;
   }

   public void appId_$eq(final String x$1) {
      this.appId = x$1;
   }

   public String attemptId() {
      return this.attemptId;
   }

   public void attemptId_$eq(final String x$1) {
      this.attemptId = x$1;
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
   @Path("jobs")
   public Seq jobsList(@QueryParam("status") final List statuses) {
      return (Seq)this.withUI((x$1) -> x$1.store().jobsList(statuses));
   }

   @GET
   @Path("jobs/{jobId: \\d+}")
   public JobData oneJob(@PathParam("jobId") final int jobId) {
      return (JobData)this.withUI((ui) -> {
         try {
            return ui.store().job(jobId);
         } catch (NoSuchElementException var2) {
            throw new NotFoundException("unknown job: " + jobId);
         }
      });
   }

   @GET
   @Path("executors")
   public Seq executorList() {
      return (Seq)this.withUI((x$2) -> x$2.store().executorList(true));
   }

   @GET
   @Path("executors/{executorId}/threads")
   public ThreadStackTrace[] threadDump(@PathParam("executorId") final String execId) {
      return (ThreadStackTrace[])this.withUI((ui) -> {
         this.checkExecutorId(execId);
         SparkContext safeSparkContext = this.checkAndGetSparkContext();
         boolean var5 = false;
         Object var6 = null;
         Option var7 = ui.store().asOption(() -> ui.store().executorSummary(execId));
         if (var7 instanceof Some) {
            var5 = true;
            Some var10 = (Some)var7;
            ExecutorSummary executorSummary = (ExecutorSummary)var10.value();
            if (executorSummary.isActive()) {
               ThreadStackTrace[] safeThreadDump = (ThreadStackTrace[])safeSparkContext.getExecutorThreadDump(execId).getOrElse(() -> {
                  throw new NotFoundException("No thread dump is available.");
               });
               return safeThreadDump;
            }
         }

         if (var5) {
            throw new BadParameterException("Executor is not active.");
         } else {
            throw new NotFoundException("Executor does not exist.");
         }
      });
   }

   @GET
   @Path("threads")
   public ThreadStackTrace getTaskThreadDump(@QueryParam("taskId") final long taskId, @QueryParam("executorId") final String execId) {
      this.checkExecutorId(execId);
      SparkContext safeSparkContext = this.checkAndGetSparkContext();
      return (ThreadStackTrace)safeSparkContext.getTaskThreadDump(taskId, execId).getOrElse(() -> {
         throw new NotFoundException("Task '" + taskId + "' is not running on Executor '" + execId + "' right now");
      });
   }

   @GET
   @Path("allexecutors")
   public Seq allExecutorList() {
      return (Seq)this.withUI((x$3) -> x$3.store().executorList(false));
   }

   @GET
   @Path("allmiscellaneousprocess")
   public Seq allProcessList() {
      return (Seq)this.withUI((x$4) -> x$4.store().miscellaneousProcessList(false));
   }

   @Path("stages")
   public Class stages() {
      return StagesResource.class;
   }

   @GET
   @Path("storage/rdd")
   public Seq rddList() {
      return (Seq)this.withUI((x$5) -> x$5.store().rddList(x$5.store().rddList$default$1()));
   }

   @GET
   @Path("storage/rdd/{rddId: \\d+}")
   public RDDStorageInfo rddData(@PathParam("rddId") final int rddId) {
      return (RDDStorageInfo)this.withUI((ui) -> {
         try {
            return ui.store().rdd(rddId);
         } catch (NoSuchElementException var2) {
            throw new NotFoundException("no rdd found w/ id " + rddId);
         }
      });
   }

   @GET
   @Path("environment")
   public ApplicationEnvironmentInfo environmentInfo() {
      return (ApplicationEnvironmentInfo)this.withUI((ui) -> {
         ApplicationEnvironmentInfo envInfo = ui.store().environmentInfo();
         Seq resourceProfileInfo = ui.store().resourceProfileInfo();
         return new ApplicationEnvironmentInfo(envInfo.runtime(), (scala.collection.Seq)Utils$.MODULE$.redact(ui.conf(), envInfo.sparkProperties()).sortBy((x$6) -> (String)x$6._1(), .MODULE$), (scala.collection.Seq)Utils$.MODULE$.redact(ui.conf(), envInfo.hadoopProperties()).sortBy((x$7) -> (String)x$7._1(), .MODULE$), (scala.collection.Seq)Utils$.MODULE$.redact(ui.conf(), envInfo.systemProperties()).sortBy((x$8) -> (String)x$8._1(), .MODULE$), (scala.collection.Seq)Utils$.MODULE$.redact(ui.conf(), envInfo.metricsProperties()).sortBy((x$9) -> (String)x$9._1(), .MODULE$), (scala.collection.Seq)envInfo.classpathEntries().sortBy((x$10) -> (String)x$10._1(), .MODULE$), resourceProfileInfo);
      });
   }

   @GET
   @Path("logs")
   @Produces({"application/octet-stream"})
   public Response getEventLogs() {
      try {
         this.checkUIViewPermissions();
      } catch (Throwable var11) {
         if (!(var11 instanceof NotFoundException) || this.attemptId() != null) {
            throw var11;
         }

         this.attemptId_$eq("1");
         this.checkUIViewPermissions();
         this.attemptId_$eq((String)null);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      try {
         String fileName = this.attemptId() != null ? "eventLogs-" + this.appId() + "-" + this.attemptId() + ".zip" : "eventLogs-" + this.appId() + ".zip";
         StreamingOutput stream = new StreamingOutput() {
            // $FF: synthetic field
            private final AbstractApplicationResource $outer;

            public void write(final OutputStream output) {
               ZipOutputStream zipStream = new ZipOutputStream(output);

               try {
                  this.$outer.uiRoot().writeEventLogs(this.$outer.appId(), scala.Option..MODULE$.apply(this.$outer.attemptId()), zipStream);
               } finally {
                  zipStream.close();
               }

            }

            public {
               if (AbstractApplicationResource.this == null) {
                  throw null;
               } else {
                  this.$outer = AbstractApplicationResource.this;
               }
            }
         };
         return Response.ok(stream).header("Content-Disposition", "attachment; filename=" + fileName).header("Content-Type", "application/octet-stream").build();
      } catch (Throwable var10) {
         if (var10 != null) {
            Option var9 = scala.util.control.NonFatal..MODULE$.unapply(var10);
            if (!var9.isEmpty()) {
               throw new ServiceUnavailable("Event logs are not available for app: " + this.appId() + ".");
            }
         }

         throw var10;
      }
   }

   @Path("{attemptId}")
   public Class applicationAttempt() {
      if (this.attemptId() != null) {
         throw new NotFoundException(this.httpRequest().getRequestURI());
      } else {
         return OneApplicationAttemptResource.class;
      }
   }

   private void checkExecutorId(final String execId) {
      String var2 = SparkContext$.MODULE$.DRIVER_IDENTIFIER();
      if (execId == null) {
         if (var2 == null) {
            return;
         }
      } else if (execId.equals(var2)) {
         return;
      }

      if (!scala.collection.StringOps..MODULE$.forall$extension(scala.Predef..MODULE$.augmentString(execId), (x$1) -> BoxesRunTime.boxToBoolean($anonfun$checkExecutorId$1(BoxesRunTime.unboxToChar(x$1))))) {
         throw new BadParameterException("Invalid executorId: neither '" + SparkContext$.MODULE$.DRIVER_IDENTIFIER() + "' nor number.");
      }
   }

   private SparkContext checkAndGetSparkContext() {
      return (SparkContext)this.withUI((ui) -> (SparkContext)ui.sc().getOrElse(() -> {
            throw new ServiceUnavailable("Thread dumps not available through the history server.");
         }));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$checkExecutorId$1(final char x$1) {
      return Character.isDigit(x$1);
   }

   public AbstractApplicationResource() {
      ApiRequestContext.$init$(this);
      BaseAppResource.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
