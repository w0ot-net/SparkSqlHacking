package org.apache.spark.sql.hive.thriftserver;

import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.conf.HiveConf.ConfVars;
import org.apache.hive.service.cli.thrift.ThriftBinaryCLIService;
import org.apache.hive.service.cli.thrift.ThriftCLIService;
import org.apache.hive.service.cli.thrift.ThriftHttpCLIService;
import org.apache.hive.service.server.HiveServer2;
import org.apache.spark.annotation.DeveloperApi;
import org.apache.spark.internal.Logging;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.hive.thriftserver.ui.HiveThriftServer2EventManager;
import org.apache.spark.sql.hive.thriftserver.ui.HiveThriftServer2Listener;
import scala.Enumeration;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.StringContext;
import scala.reflect.ScalaSignature;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\t\u001dr!\u0002\u00180\u0011\u0003ad!\u0002 0\u0011\u0003y\u0004\"\u0002'\u0002\t\u0003i\u0005b\u0002(\u0002\u0001\u0004%\ta\u0014\u0005\b3\u0006\u0001\r\u0011\"\u0001[\u0011\u0019\u0001\u0017\u0001)Q\u0005!\"I\u0011-\u0001a\u0001\u0002\u0004%\tA\u0019\u0005\nM\u0006\u0001\r\u00111A\u0005\u0002\u001dD\u0011\"[\u0001A\u0002\u0003\u0005\u000b\u0015B2\t\u0013)\f\u0001\u0019!a\u0001\n\u0003Y\u0007\"C8\u0002\u0001\u0004\u0005\r\u0011\"\u0001q\u0011%\u0011\u0018\u00011A\u0001B\u0003&A\u000eC\u0004t\u0003\t\u0007I\u0011\u0001;\t\u000f\u0005\r\u0011\u0001)A\u0005k\"9\u0011QA\u0001\u0005\u0002\u0005\u001d\u0001bBAB\u0003\u0011\u0005\u0011Q\u0011\u0005\b\u0003S\u000bA\u0011BAV\u0011\u001d\tY,\u0001C\u0001\u0003{;\u0001\"a8\u0002\u0011\u0003y\u0013\u0011\u001d\u0004\t\u0003K\f\u0001\u0012A\u0018\u0002h\"1Aj\u0005C\u0001\u0003_D\u0011\"!=\u0014\u0005\u0004%\t!a=\t\u0011\u0005u8\u0003)A\u0005\u0003kD\u0011\"a@\u0014\u0005\u0004%\t!a=\t\u0011\t\u00051\u0003)A\u0005\u0003kD\u0011Ba\u0001\u0014\u0005\u0004%\t!a=\t\u0011\t\u00151\u0003)A\u0005\u0003kD\u0011Ba\u0002\u0014\u0005\u0004%\t!a=\t\u0011\t%1\u0003)A\u0005\u0003kD\u0011Ba\u0003\u0014\u0005\u0004%\t!a=\t\u0011\t51\u0003)A\u0005\u0003kD\u0011Ba\u0004\u0014\u0005\u0004%\t!a=\t\u0011\tE1\u0003)A\u0005\u0003kD\u0011Ba\u0005\u0014\u0005\u0004%\t!a=\t\u0011\tU1\u0003)A\u0005\u0003k,a!!:\u0014\u0001\u0005U\b\"\u0003B\f'\u0005\u0005I\u0011\u0002B\r\r\u0019qt\u0006A\u0019\u0002\f!Q\u0011QE\u0013\u0003\u0002\u0003\u0006I!a\n\t\r1+C\u0011AA\u0018\u0011!\t\u0019$\nb\u0001\n\u0013!\bbBA\u001bK\u0001\u0006I!\u001e\u0005\b\u0003o)C\u0011IA\u001d\u0011\u001d\t\t&\nC\u0005\u0003'Bq!!\u0018&\t\u0003\ny\u0006C\u0004\u0002b\u0015\"\t%a\u0018\u0002#!Kg/\u001a+ie&4GoU3sm\u0016\u0014(G\u0003\u00021c\u0005aA\u000f\u001b:jMR\u001cXM\u001d<fe*\u0011!gM\u0001\u0005Q&4XM\u0003\u00025k\u0005\u00191/\u001d7\u000b\u0005Y:\u0014!B:qCJ\\'B\u0001\u001d:\u0003\u0019\t\u0007/Y2iK*\t!(A\u0002pe\u001e\u001c\u0001\u0001\u0005\u0002>\u00035\tqFA\tISZ,G\u000b\u001b:jMR\u001cVM\u001d<feJ\u001a2!\u0001!G!\t\tE)D\u0001C\u0015\u0005\u0019\u0015!B:dC2\f\u0017BA#C\u0005\u0019\te.\u001f*fMB\u0011qIS\u0007\u0002\u0011*\u0011\u0011*N\u0001\tS:$XM\u001d8bY&\u00111\n\u0013\u0002\b\u0019><w-\u001b8h\u0003\u0019a\u0014N\\5u}Q\tA(A\u0003vSR\u000b'-F\u0001Q!\r\t\u0015kU\u0005\u0003%\n\u0013aa\u00149uS>t\u0007C\u0001+X\u001b\u0005)&B\u0001,0\u0003\t)\u0018.\u0003\u0002Y+\nyA\u000b\u001b:jMR\u001cVM\u001d<feR\u000b'-A\u0005vSR\u000b'm\u0018\u0013fcR\u00111L\u0018\t\u0003\u0003rK!!\u0018\"\u0003\tUs\u0017\u000e\u001e\u0005\b?\u0012\t\t\u00111\u0001Q\u0003\rAH%M\u0001\u0007k&$\u0016M\u0019\u0011\u0002\u00111L7\u000f^3oKJ,\u0012a\u0019\t\u0003)\u0012L!!Z+\u00033!Kg/\u001a+ie&4GoU3sm\u0016\u0014(\u0007T5ti\u0016tWM]\u0001\rY&\u001cH/\u001a8fe~#S-\u001d\u000b\u00037\"DqaX\u0004\u0002\u0002\u0003\u00071-A\u0005mSN$XM\\3sA\u0005aQM^3oi6\u000bg.Y4feV\tA\u000e\u0005\u0002U[&\u0011a.\u0016\u0002\u001e\u0011&4X\r\u00165sS\u001a$8+\u001a:wKJ\u0014TI^3oi6\u000bg.Y4fe\u0006\u0001RM^3oi6\u000bg.Y4fe~#S-\u001d\u000b\u00037FDqa\u0018\u0006\u0002\u0002\u0003\u0007A.A\u0007fm\u0016tG/T1oC\u001e,'\u000fI\u0001\u0012gf\u001cH/Z7Fq&$xJ\\#se>\u0014X#A;\u0011\u0005Y|X\"A<\u000b\u0005aL\u0018AB1u_6L7M\u0003\u0002{w\u0006Q1m\u001c8dkJ\u0014XM\u001c;\u000b\u0005ql\u0018\u0001B;uS2T\u0011A`\u0001\u0005U\u00064\u0018-C\u0002\u0002\u0002]\u0014Q\"\u0011;p[&\u001c'i\\8mK\u0006t\u0017AE:zgR,W.\u0012=ji>sWI\u001d:pe\u0002\nQc\u001d;beR<\u0016\u000e\u001e5Ta\u0006\u00148nU3tg&|g\u000e\u0006\u0004\u0002\n\u0005\r\u0014Q\r\t\u0003{\u0015\u001aR!JA\u0007\u0003?\u0001B!a\u0004\u0002\u001c5\u0011\u0011\u0011\u0003\u0006\u0005\u0003'\t)\"\u0001\u0004tKJ4XM\u001d\u0006\u0005\u0003/\tI\"A\u0004tKJ4\u0018nY3\u000b\u0005I:\u0014\u0002BA\u000f\u0003#\u00111\u0002S5wKN+'O^3seA\u0019Q(!\t\n\u0007\u0005\rrFA\rSK\u001adWm\u0019;fI\u000e{W\u000e]8tSR,7+\u001a:wS\u000e,\u0017\u0001D:qCJ\\7+Z:tS>t\u0007\u0003BA\u0015\u0003Wi\u0011aM\u0005\u0004\u0003[\u0019$\u0001D*qCJ\\7+Z:tS>tG\u0003BA\u0005\u0003cAq!!\n(\u0001\u0004\t9#A\u0004ti\u0006\u0014H/\u001a3\u0002\u0011M$\u0018M\u001d;fI\u0002\nA!\u001b8jiR\u00191,a\u000f\t\u000f\u0005u\"\u00061\u0001\u0002@\u0005A\u0001.\u001b<f\u0007>tg\r\u0005\u0003\u0002B\u00055SBAA\"\u0015\u0011\t)%a\u0012\u0002\t\r|gN\u001a\u0006\u0004e\u0005%#bAA&o\u00051\u0001.\u00193p_BLA!a\u0014\u0002D\tA\u0001*\u001b<f\u0007>tg-A\njg\"#F\u000b\u0015+sC:\u001c\bo\u001c:u\u001b>$W\r\u0006\u0003\u0002V\u0005m\u0003cA!\u0002X%\u0019\u0011\u0011\f\"\u0003\u000f\t{w\u000e\\3b]\"9\u0011QH\u0016A\u0002\u0005}\u0012!B:uCJ$H#A.\u0002\tM$x\u000e\u001d\u0005\b\u0003Kq\u0001\u0019AA\u0014\u0011\u001d\t9G\u0004a\u0001\u0003+\n1\"\u001a=ji>sWI\u001d:pe\"*a\"a\u001b\u0002xA!\u0011QNA:\u001b\t\tyGC\u0002\u0002rU\n!\"\u00198o_R\fG/[8o\u0013\u0011\t)(a\u001c\u0003\u000bMKgnY3\"\u0005\u0005e\u0014!\u0002\u001b/a9\u0002\u0004f\u0001\b\u0002~A!\u0011QNA@\u0013\u0011\t\t)a\u001c\u0003\u0019\u0011+g/\u001a7pa\u0016\u0014\u0018\t]5\u0002!M$\u0018M\u001d;XSRD7i\u001c8uKb$H\u0003BA\u0005\u0003\u000fCq!!#\u0010\u0001\u0004\tY)\u0001\u0006tc2\u001cuN\u001c;fqR\u0004B!!\u000b\u0002\u000e&\u0019\u0011qR\u001a\u0003\u0015M\u000bFjQ8oi\u0016DH\u000fK\u0006\u0010\u0003'\u000bI*a'\u0002 \u0006]\u0004cA!\u0002\u0016&\u0019\u0011q\u0013\"\u0003\u0015\u0011,\u0007O]3dCR,G-A\u0004nKN\u001c\u0018mZ3\"\u0005\u0005u\u0015!I+tK\u0002\u001aH/\u0019:u/&$\bn\u00159be.\u001cVm]:j_:\u0004\u0013N\\:uK\u0006$\u0017!B:j]\u000e,\u0007&B\b\u0002l\u0005\r\u0016EAAS\u0003\u0015\u0011d\u0006\r\u00181Q\ry\u0011QP\u0001\u0014GJ,\u0017\r^3MSN$XM\\3s\u0003:$W+\u0013\u000b\u00067\u00065\u0016q\u0016\u0005\b\u0003'\u0001\u0002\u0019AA\u0005\u0011\u001d\t\t\f\u0005a\u0001\u0003g\u000b!a]2\u0011\t\u0005U\u0016qW\u0007\u0002k%\u0019\u0011\u0011X\u001b\u0003\u0019M\u0003\u0018M]6D_:$X\r\u001f;\u0002\t5\f\u0017N\u001c\u000b\u00047\u0006}\u0006bBAa#\u0001\u0007\u00111Y\u0001\u0005CJ<7\u000fE\u0003B\u0003\u000b\fI-C\u0002\u0002H\n\u0013Q!\u0011:sCf\u0004B!a3\u0002Z:!\u0011QZAk!\r\tyMQ\u0007\u0003\u0003#T1!a5<\u0003\u0019a$o\\8u}%\u0019\u0011q\u001b\"\u0002\rA\u0013X\rZ3g\u0013\u0011\tY.!8\u0003\rM#(/\u001b8h\u0015\r\t9NQ\u0001\u000f\u000bb,7-\u001e;j_:\u001cF/\u0019;f!\r\t\u0019oE\u0007\u0002\u0003\tqQ\t_3dkRLwN\\*uCR,7cA\n\u0002jB\u0019\u0011)a;\n\u0007\u00055(IA\u0006F]VlWM]1uS>tGCAAq\u0003\u001d\u0019F+\u0011*U\u000b\u0012+\"!!>\u0011\t\u0005]\u0018\u0011`\u0007\u0002'%!\u00111`Av\u0005\u00151\u0016\r\\;f\u0003!\u0019F+\u0011*U\u000b\u0012\u0003\u0013\u0001C\"P\u001bBKE*\u0012#\u0002\u0013\r{U\nU%M\u000b\u0012\u0003\u0013\u0001C\"B\u001d\u000e+E*\u0012#\u0002\u0013\r\u000bejQ#M\u000b\u0012\u0003\u0013\u0001\u0003+J\u001b\u0016#u*\u0016+\u0002\u0013QKU*\u0012#P+R\u0003\u0013A\u0002$B\u00132+E)A\u0004G\u0003&cU\t\u0012\u0011\u0002\u0011\u0019Ke*S*I\u000b\u0012\u000b\u0011BR%O\u0013NCU\t\u0012\u0011\u0002\r\rcujU#E\u0003\u001d\u0019EjT*F\t\u0002\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"Aa\u0007\u0011\t\tu!1E\u0007\u0003\u0005?Q1A!\t~\u0003\u0011a\u0017M\\4\n\t\t\u0015\"q\u0004\u0002\u0007\u001f\nTWm\u0019;"
)
public class HiveThriftServer2 extends HiveServer2 implements ReflectedCompositeService {
   private final SparkSession sparkSession;
   private final AtomicBoolean started;
   private Function1 org$apache$spark$sql$hive$thriftserver$ReflectedCompositeService$$logInfo;
   private Function2 org$apache$spark$sql$hive$thriftserver$ReflectedCompositeService$$logError;

   public static void main(final String[] args) {
      HiveThriftServer2$.MODULE$.main(args);
   }

   /** @deprecated */
   @DeveloperApi
   public static HiveThriftServer2 startWithContext(final SQLContext sqlContext) {
      return HiveThriftServer2$.MODULE$.startWithContext(sqlContext);
   }

   @DeveloperApi
   public static HiveThriftServer2 startWithSparkSession(final SparkSession sparkSession, final boolean exitOnError) {
      return HiveThriftServer2$.MODULE$.startWithSparkSession(sparkSession, exitOnError);
   }

   public static AtomicBoolean systemExitOnError() {
      return HiveThriftServer2$.MODULE$.systemExitOnError();
   }

   public static void eventManager_$eq(final HiveThriftServer2EventManager x$1) {
      HiveThriftServer2$.MODULE$.eventManager_$eq(x$1);
   }

   public static HiveThriftServer2EventManager eventManager() {
      return HiveThriftServer2$.MODULE$.eventManager();
   }

   public static void listener_$eq(final HiveThriftServer2Listener x$1) {
      HiveThriftServer2$.MODULE$.listener_$eq(x$1);
   }

   public static HiveThriftServer2Listener listener() {
      return HiveThriftServer2$.MODULE$.listener();
   }

   public static void uiTab_$eq(final Option x$1) {
      HiveThriftServer2$.MODULE$.uiTab_$eq(x$1);
   }

   public static Option uiTab() {
      return HiveThriftServer2$.MODULE$.uiTab();
   }

   public static Logging.LogStringContext LogStringContext(final StringContext sc) {
      return HiveThriftServer2$.MODULE$.LogStringContext(sc);
   }

   public void initCompositeService(final HiveConf hiveConf) {
      ReflectedCompositeService.initCompositeService$(this, hiveConf);
   }

   public void startCompositeService() {
      ReflectedCompositeService.startCompositeService$(this);
   }

   public Function1 org$apache$spark$sql$hive$thriftserver$ReflectedCompositeService$$logInfo() {
      return this.org$apache$spark$sql$hive$thriftserver$ReflectedCompositeService$$logInfo;
   }

   public Function2 org$apache$spark$sql$hive$thriftserver$ReflectedCompositeService$$logError() {
      return this.org$apache$spark$sql$hive$thriftserver$ReflectedCompositeService$$logError;
   }

   public final void org$apache$spark$sql$hive$thriftserver$ReflectedCompositeService$_setter_$org$apache$spark$sql$hive$thriftserver$ReflectedCompositeService$$logInfo_$eq(final Function1 x$1) {
      this.org$apache$spark$sql$hive$thriftserver$ReflectedCompositeService$$logInfo = x$1;
   }

   public final void org$apache$spark$sql$hive$thriftserver$ReflectedCompositeService$_setter_$org$apache$spark$sql$hive$thriftserver$ReflectedCompositeService$$logError_$eq(final Function2 x$1) {
      this.org$apache$spark$sql$hive$thriftserver$ReflectedCompositeService$$logError = x$1;
   }

   private AtomicBoolean started() {
      return this.started;
   }

   public void init(final HiveConf hiveConf) {
      SparkSQLCLIService sparkSqlCliService = new SparkSQLCLIService(this, this.sparkSession);
      ReflectionUtils$.MODULE$.setSuperField(this, "cliService", sparkSqlCliService);
      this.addService(sparkSqlCliService);
      ThriftCLIService thriftCliService = (ThriftCLIService)(this.isHTTPTransportMode(hiveConf) ? new ThriftHttpCLIService(sparkSqlCliService) : new ThriftBinaryCLIService(sparkSqlCliService));
      ReflectionUtils$.MODULE$.setSuperField(this, "thriftCLIService", thriftCliService);
      this.addService(thriftCliService);
      this.initCompositeService(hiveConf);
   }

   private boolean isHTTPTransportMode(final HiveConf hiveConf) {
      String transportMode = hiveConf.getVar(ConfVars.HIVE_SERVER2_TRANSPORT_MODE);
      return transportMode.toLowerCase(Locale.ROOT).equals("http");
   }

   public void start() {
      super.start();
      this.started().set(true);
   }

   public void stop() {
      if (this.started().getAndSet(false)) {
         super.stop();
      }
   }

   public HiveThriftServer2(final SparkSession sparkSession) {
      this.sparkSession = sparkSession;
      ReflectedCompositeService.$init$(this);
      this.started = new AtomicBoolean(false);
      Statics.releaseFence();
   }

   public static class ExecutionState$ extends Enumeration {
      public static final ExecutionState$ MODULE$ = new ExecutionState$();
      private static final Enumeration.Value STARTED;
      private static final Enumeration.Value COMPILED;
      private static final Enumeration.Value CANCELED;
      private static final Enumeration.Value TIMEDOUT;
      private static final Enumeration.Value FAILED;
      private static final Enumeration.Value FINISHED;
      private static final Enumeration.Value CLOSED;

      static {
         STARTED = MODULE$.Value();
         COMPILED = MODULE$.Value();
         CANCELED = MODULE$.Value();
         TIMEDOUT = MODULE$.Value();
         FAILED = MODULE$.Value();
         FINISHED = MODULE$.Value();
         CLOSED = MODULE$.Value();
      }

      public Enumeration.Value STARTED() {
         return STARTED;
      }

      public Enumeration.Value COMPILED() {
         return COMPILED;
      }

      public Enumeration.Value CANCELED() {
         return CANCELED;
      }

      public Enumeration.Value TIMEDOUT() {
         return TIMEDOUT;
      }

      public Enumeration.Value FAILED() {
         return FAILED;
      }

      public Enumeration.Value FINISHED() {
         return FINISHED;
      }

      public Enumeration.Value CLOSED() {
         return CLOSED;
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(ExecutionState$.class);
      }
   }
}
