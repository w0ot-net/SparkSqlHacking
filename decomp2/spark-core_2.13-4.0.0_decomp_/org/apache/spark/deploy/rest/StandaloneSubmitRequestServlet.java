package org.apache.spark.deploy.rest;

import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkConf;
import org.apache.spark.deploy.ClientArguments$;
import org.apache.spark.deploy.Command;
import org.apache.spark.deploy.DeployMessages;
import org.apache.spark.deploy.DriverDescription;
import org.apache.spark.deploy.SparkSubmit;
import org.apache.spark.internal.config.package$;
import org.apache.spark.launcher.JavaModuleOptions;
import org.apache.spark.resource.ResourceUtils$;
import org.apache.spark.rpc.RpcEndpointRef;
import org.apache.spark.util.Utils$;
import scala.MatchError;
import scala.Option;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.IterableOps;
import scala.collection.MapOps;
import scala.collection.SeqOps;
import scala.collection.immutable.ArraySeq;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime.;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u000594Q\u0001C\u0005\u0001\u0013MA\u0001\u0002\u0007\u0001\u0003\u0002\u0003\u0006IA\u0007\u0005\tA\u0001\u0011\t\u0011)A\u0005C!Aa\u0006\u0001B\u0001B\u0003%q\u0006C\u00034\u0001\u0011\u0005A\u0007C\u0003:\u0001\u0011%!\b\u0003\u0004E\u0001\u0011\u0005\u0011\"\u0012\u0005\u0006-\u0002!\tf\u0016\u0002\u001f'R\fg\u000eZ1m_:,7+\u001e2nSR\u0014V-];fgR\u001cVM\u001d<mKRT!AC\u0006\u0002\tI,7\u000f\u001e\u0006\u0003\u00195\ta\u0001Z3qY>L(B\u0001\b\u0010\u0003\u0015\u0019\b/\u0019:l\u0015\t\u0001\u0012#\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002%\u0005\u0019qN]4\u0014\u0005\u0001!\u0002CA\u000b\u0017\u001b\u0005I\u0011BA\f\n\u0005Q\u0019VOY7jiJ+\u0017/^3tiN+'O\u001e7fi\u0006qQ.Y:uKJ,e\u000e\u001a9pS:$8\u0001\u0001\t\u00037yi\u0011\u0001\b\u0006\u0003;5\t1A\u001d9d\u0013\tyBD\u0001\bSa\u000e,e\u000e\u001a9pS:$(+\u001a4\u0002\u00135\f7\u000f^3s+Jd\u0007C\u0001\u0012,\u001d\t\u0019\u0013\u0006\u0005\u0002%O5\tQE\u0003\u0002'3\u00051AH]8pizR\u0011\u0001K\u0001\u0006g\u000e\fG.Y\u0005\u0003U\u001d\na\u0001\u0015:fI\u00164\u0017B\u0001\u0017.\u0005\u0019\u0019FO]5oO*\u0011!fJ\u0001\u0005G>tg\r\u0005\u00021c5\tQ\"\u0003\u00023\u001b\tI1\u000b]1sW\u000e{gNZ\u0001\u0007y%t\u0017\u000e\u001e \u0015\tU2t\u0007\u000f\t\u0003+\u0001AQ\u0001\u0007\u0003A\u0002iAQ\u0001\t\u0003A\u0002\u0005BQA\f\u0003A\u0002=\n!C]3qY\u0006\u001cW\r\u00157bG\u0016Du\u000e\u001c3feR\u00111H\u0011\t\u0003y\u0005k\u0011!\u0010\u0006\u0003}}\nA\u0001\\1oO*\t\u0001)\u0001\u0003kCZ\f\u0017B\u0001\u0017>\u0011\u0015\u0019U\u00011\u0001\"\u0003!1\u0018M]5bE2,\u0017A\u00062vS2$GI]5wKJ$Um]2sSB$\u0018n\u001c8\u0015\t\u0019Su\n\u0015\t\u0003\u000f\"k\u0011aC\u0005\u0003\u0013.\u0011\u0011\u0003\u0012:jm\u0016\u0014H)Z:de&\u0004H/[8o\u0011\u0015Ye\u00011\u0001M\u0003\u001d\u0011X-];fgR\u0004\"!F'\n\u00059K!aF\"sK\u0006$XmU;c[&\u001c8/[8o%\u0016\fX/Z:u\u0011\u0015\u0001c\u00011\u0001\"\u0011\u0015\tf\u00011\u0001S\u00039i\u0017m\u001d;feJ+7\u000f\u001e)peR\u0004\"a\u0015+\u000e\u0003\u001dJ!!V\u0014\u0003\u0007%sG/\u0001\u0007iC:$G.Z*vE6LG\u000f\u0006\u0003Y7v\u0013\u0007CA\u000bZ\u0013\tQ\u0016B\u0001\u000eTk\nl\u0017\u000e\u001e*fgR\u0004&o\u001c;pG>d'+Z:q_:\u001cX\rC\u0003]\u000f\u0001\u0007\u0011%\u0001\nsKF,Xm\u001d;NKN\u001c\u0018mZ3Kg>t\u0007\"\u00020\b\u0001\u0004y\u0016A\u0004:fcV,7\u000f^'fgN\fw-\u001a\t\u0003+\u0001L!!Y\u0005\u00033M+(-\\5u%\u0016\u001cH\u000f\u0015:pi>\u001cw\u000e\\'fgN\fw-\u001a\u0005\u0006G\u001e\u0001\r\u0001Z\u0001\u0010e\u0016\u001c\bo\u001c8tKN+'O\u001e7fiB\u0011Q\r\\\u0007\u0002M*\u0011q\r[\u0001\u0005QR$\bO\u0003\u0002jU\u000691/\u001a:wY\u0016$(\"A6\u0002\u000f)\f7.\u0019:uC&\u0011QN\u001a\u0002\u0014\u0011R$\boU3sm2,GOU3ta>t7/\u001a"
)
public class StandaloneSubmitRequestServlet extends SubmitRequestServlet {
   private final RpcEndpointRef masterEndpoint;
   private final String masterUrl;
   private final SparkConf conf;

   private String replacePlaceHolder(final String variable) {
      if (variable != null) {
         Option var4 = (new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"{{", "}}"})))).s().unapplySeq(variable);
         if (!var4.isEmpty() && var4.get() != null && ((SeqOps)var4.get()).lengthCompare(1) == 0) {
            String name = (String)((SeqOps)var4.get()).apply(0);
            if (System.getenv(name) != null) {
               return System.getenv(name);
            }
         }
      }

      return variable;
   }

   public DriverDescription buildDriverDescription(final CreateSubmissionRequest request, final String masterUrl, final int masterRestPort) {
      String appResource = (String)scala.Option..MODULE$.apply(request.appResource()).getOrElse(() -> {
         throw new SubmitRestMissingFieldException("Application jar is missing.");
      });
      String mainClass = (String)scala.Option..MODULE$.apply(request.mainClass()).getOrElse(() -> {
         throw new SubmitRestMissingFieldException("Main class is missing.");
      });
      Map sparkProperties = (Map)request.sparkProperties().map((x) -> new Tuple2(x._1(), this.replacePlaceHolder((String)x._2())));
      Option driverMemory = sparkProperties.get(package$.MODULE$.DRIVER_MEMORY().key());
      Option driverCores = sparkProperties.get(package$.MODULE$.DRIVER_CORES().key());
      Option driverDefaultJavaOptions = sparkProperties.get("spark.driver.defaultJavaOptions");
      Option driverExtraJavaOptions = sparkProperties.get(package$.MODULE$.DRIVER_JAVA_OPTIONS().key());
      Option driverExtraClassPath = sparkProperties.get(package$.MODULE$.DRIVER_CLASS_PATH().key());
      Option driverExtraLibraryPath = sparkProperties.get(package$.MODULE$.DRIVER_LIBRARY_PATH().key());
      Option superviseDriver = sparkProperties.get(package$.MODULE$.DRIVER_SUPERVISE().key());
      Option masters = sparkProperties.get("spark.master");
      Tuple2 var17 = Utils$.MODULE$.extractHostPortFromSparkUrl(masterUrl);
      if (var17 != null) {
         int masterPort = var17._2$mcI$sp();
         String updatedMasters = (String)masters.map((x$3) -> x$3.replace(":" + masterRestPort, ":" + masterPort)).getOrElse(() -> masterUrl);
         String[] appArgs = (String[])scala.Option..MODULE$.apply(request.appArgs()).getOrElse(() -> (String[])scala.Array..MODULE$.apply(scala.collection.immutable.Nil..MODULE$, scala.reflect.ClassTag..MODULE$.apply(String.class)));
         Map environmentVariables = (Map)((MapOps)((IterableOps)scala.Option..MODULE$.apply(request.environmentVariables()).getOrElse(() -> scala.Predef..MODULE$.Map().empty())).filterNot((x) -> BoxesRunTime.boxToBoolean($anonfun$buildDriverDescription$8(x)))).map((x) -> new Tuple2(x._1(), this.replacePlaceHolder((String)x._2())));
         SparkConf conf = (new SparkConf(false)).setAll(sparkProperties).set("spark.master", updatedMasters);
         Seq extraClassPath = (Seq)scala.Option..MODULE$.option2Iterable(driverExtraClassPath).toSeq().flatMap((x$4) -> scala.Predef..MODULE$.wrapRefArray((Object[])x$4.split(File.pathSeparator)));
         Seq extraLibraryPath = (Seq)scala.Option..MODULE$.option2Iterable(driverExtraLibraryPath).toSeq().flatMap((x$5) -> scala.Predef..MODULE$.wrapRefArray((Object[])x$5.split(File.pathSeparator)));
         Seq defaultJavaOpts = (Seq)driverDefaultJavaOptions.map((s) -> Utils$.MODULE$.splitCommandString(s)).getOrElse(() -> (Seq)scala.package..MODULE$.Seq().empty());
         Seq extraJavaOpts = (Seq)driverExtraJavaOptions.map((s) -> Utils$.MODULE$.splitCommandString(s)).getOrElse(() -> (Seq)scala.package..MODULE$.Seq().empty());
         Seq sparkJavaOpts = Utils$.MODULE$.sparkJavaOpts(conf, Utils$.MODULE$.sparkJavaOpts$default$2());
         ArraySeq javaModuleOptions = org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(JavaModuleOptions.defaultModuleOptionArray()).toImmutableArraySeq();
         ArraySeq javaOpts = (ArraySeq)((IterableOps)((IterableOps)javaModuleOptions.$plus$plus(sparkJavaOpts)).$plus$plus(defaultJavaOpts)).$plus$plus(extraJavaOpts);
         Seq sparkSubmitOpts = mainClass.equals(SparkSubmit.class.getName()) ? (Seq)sparkProperties.get("spark.app.name").map((v) -> new scala.collection.immutable..colon.colon("-c", new scala.collection.immutable..colon.colon("spark.app.name=" + v, scala.collection.immutable.Nil..MODULE$))).getOrElse(() -> (Seq)scala.package..MODULE$.Seq().empty()) : (Seq)scala.package..MODULE$.Seq().empty();
         Command command = new Command("org.apache.spark.deploy.worker.DriverWrapper", (Seq)((IterableOps)(new scala.collection.immutable..colon.colon("{{WORKER_URL}}", new scala.collection.immutable..colon.colon("{{USER_JAR}}", new scala.collection.immutable..colon.colon(mainClass, scala.collection.immutable.Nil..MODULE$)))).$plus$plus(sparkSubmitOpts)).$plus$plus(scala.Predef..MODULE$.wrapRefArray((Object[])appArgs)), environmentVariables, extraClassPath, extraLibraryPath, javaOpts);
         int actualDriverMemory = BoxesRunTime.unboxToInt(driverMemory.map((str) -> BoxesRunTime.boxToInteger($anonfun$buildDriverDescription$18(str))).getOrElse((JFunction0.mcI.sp)() -> ClientArguments$.MODULE$.DEFAULT_MEMORY()));
         int actualDriverCores = BoxesRunTime.unboxToInt(driverCores.map((x$6) -> BoxesRunTime.boxToInteger($anonfun$buildDriverDescription$20(x$6))).getOrElse((JFunction0.mcI.sp)() -> ClientArguments$.MODULE$.DEFAULT_CORES()));
         boolean actualSuperviseDriver = BoxesRunTime.unboxToBoolean(superviseDriver.map((x$7) -> BoxesRunTime.boxToBoolean($anonfun$buildDriverDescription$22(x$7))).getOrElse((JFunction0.mcZ.sp)() -> ClientArguments$.MODULE$.DEFAULT_SUPERVISE()));
         Seq driverResourceReqs = ResourceUtils$.MODULE$.parseResourceRequirements(conf, package$.MODULE$.SPARK_DRIVER_PREFIX());
         return new DriverDescription(appResource, actualDriverMemory, actualDriverCores, actualSuperviseDriver, command, driverResourceReqs);
      } else {
         throw new MatchError(var17);
      }
   }

   public SubmitRestProtocolResponse handleSubmit(final String requestMessageJson, final SubmitRestProtocolMessage requestMessage, final HttpServletResponse responseServlet) {
      if (requestMessage instanceof CreateSubmissionRequest var6) {
         DriverDescription driverDescription = this.buildDriverDescription(var6, this.masterUrl, BoxesRunTime.unboxToInt(this.conf.get(package$.MODULE$.MASTER_REST_SERVER_PORT())));
         DeployMessages.SubmitDriverResponse response = (DeployMessages.SubmitDriverResponse)this.masterEndpoint.askSync(new DeployMessages.RequestSubmitDriver(driverDescription), scala.reflect.ClassTag..MODULE$.apply(DeployMessages.SubmitDriverResponse.class));
         CreateSubmissionResponse submitResponse = new CreateSubmissionResponse();
         submitResponse.serverSparkVersion_$eq(org.apache.spark.package$.MODULE$.SPARK_VERSION());
         submitResponse.message_$eq(response.message());
         submitResponse.success_$eq(scala.Predef..MODULE$.boolean2Boolean(response.success()));
         submitResponse.submissionId_$eq((String)response.driverId().orNull(scala..less.colon.less..MODULE$.refl()));
         String[] unknownFields = this.findUnknownFields(requestMessageJson, requestMessage);
         if (scala.collection.ArrayOps..MODULE$.nonEmpty$extension(scala.Predef..MODULE$.refArrayOps((Object[])unknownFields))) {
            submitResponse.unknownFields_$eq(unknownFields);
         }

         return submitResponse;
      } else {
         responseServlet.setStatus(400);
         return this.handleError("Received message of unexpected type " + requestMessage.messageType() + ".");
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$buildDriverDescription$8(final Tuple2 x) {
      return ((String)x._1()).matches("SPARK_LOCAL_(IP|HOSTNAME)");
   }

   // $FF: synthetic method
   public static final int $anonfun$buildDriverDescription$18(final String str) {
      return Utils$.MODULE$.memoryStringToMb(str);
   }

   // $FF: synthetic method
   public static final int $anonfun$buildDriverDescription$20(final String x$6) {
      return scala.collection.StringOps..MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(x$6));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$buildDriverDescription$22(final String x$7) {
      return scala.collection.StringOps..MODULE$.toBoolean$extension(scala.Predef..MODULE$.augmentString(x$7));
   }

   public StandaloneSubmitRequestServlet(final RpcEndpointRef masterEndpoint, final String masterUrl, final SparkConf conf) {
      this.masterEndpoint = masterEndpoint;
      this.masterUrl = masterUrl;
      this.conf = conf;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
