package org.apache.spark.deploy.yarn;

import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.slf4j.Logger;
import scala.Function0;
import scala.StringContext;
import scala.collection.immutable.;
import scala.collection.immutable.List;
import scala.collection.mutable.ArrayBuffer;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015b!\u0002\r\u001a\u0001u\u0019\u0003\u0002\u0003\u0019\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u001a\t\u000b\u0001\u0003A\u0011A!\t\u000f\u0015\u0003\u0001\u0019!C\u0001\r\"9q\t\u0001a\u0001\n\u0003A\u0005B\u0002(\u0001A\u0003&Q\u0007C\u0004P\u0001\u0001\u0007I\u0011\u0001$\t\u000fA\u0003\u0001\u0019!C\u0001#\"11\u000b\u0001Q!\nUBq\u0001\u0016\u0001A\u0002\u0013\u0005a\tC\u0004V\u0001\u0001\u0007I\u0011\u0001,\t\ra\u0003\u0001\u0015)\u00036\u0011\u001dI\u0006\u00011A\u0005\u0002\u0019CqA\u0017\u0001A\u0002\u0013\u00051\f\u0003\u0004^\u0001\u0001\u0006K!\u000e\u0005\b=\u0002\u0001\r\u0011\"\u0001`\u0011\u001dA\u0007\u00011A\u0005\u0002%Daa\u001b\u0001!B\u0013\u0001\u0007b\u00027\u0001\u0001\u0004%\t!\u001c\u0005\bc\u0002\u0001\r\u0011\"\u0001s\u0011\u0019!\b\u0001)Q\u0005]\")Q\u000f\u0001C\u0005m\"9\u0011Q\u0001\u0001\u0005\n\u0005\u001d\u0001\"CA\u0007\u0001E\u0005I\u0011BA\b\u0005=\u0019E.[3oi\u0006\u0013x-^7f]R\u001c(B\u0001\u000e\u001c\u0003\u0011I\u0018M\u001d8\u000b\u0005qi\u0012A\u00023fa2|\u0017P\u0003\u0002\u001f?\u0005)1\u000f]1sW*\u0011\u0001%I\u0001\u0007CB\f7\r[3\u000b\u0003\t\n1a\u001c:h'\r\u0001AE\u000b\t\u0003K!j\u0011A\n\u0006\u0002O\u0005)1oY1mC&\u0011\u0011F\n\u0002\u0007\u0003:L(+\u001a4\u0011\u0005-rS\"\u0001\u0017\u000b\u00055j\u0012\u0001C5oi\u0016\u0014h.\u00197\n\u0005=b#a\u0002'pO\u001eLgnZ\u0001\u0005CJ<7o\u0001\u0001\u0011\u0007\u0015\u001aT'\u0003\u00025M\t)\u0011I\u001d:bsB\u0011a'\u0010\b\u0003om\u0002\"\u0001\u000f\u0014\u000e\u0003eR!AO\u0019\u0002\rq\u0012xn\u001c;?\u0013\tad%\u0001\u0004Qe\u0016$WMZ\u0005\u0003}}\u0012aa\u0015;sS:<'B\u0001\u001f'\u0003\u0019a\u0014N\\5u}Q\u0011!\t\u0012\t\u0003\u0007\u0002i\u0011!\u0007\u0005\u0006a\t\u0001\rAM\u0001\bkN,'OS1s+\u0005)\u0014aC;tKJT\u0015M]0%KF$\"!\u0013'\u0011\u0005\u0015R\u0015BA&'\u0005\u0011)f.\u001b;\t\u000f5#\u0011\u0011!a\u0001k\u0005\u0019\u0001\u0010J\u0019\u0002\u0011U\u001cXM\u001d&be\u0002\n\u0011\"^:fe\u000ec\u0017m]:\u0002\u001bU\u001cXM]\"mCN\u001cx\fJ3r)\tI%\u000bC\u0004N\u000f\u0005\u0005\t\u0019A\u001b\u0002\u0015U\u001cXM]\"mCN\u001c\b%A\u0007qe&l\u0017M]=Qs\u001aKG.Z\u0001\u0012aJLW.\u0019:z!f4\u0015\u000e\\3`I\u0015\fHCA%X\u0011\u001di%\"!AA\u0002U\na\u0002\u001d:j[\u0006\u0014\u0018\u0010U=GS2,\u0007%\u0001\u0007qe&l\u0017M]=S\r&dW-\u0001\tqe&l\u0017M]=S\r&dWm\u0018\u0013fcR\u0011\u0011\n\u0018\u0005\b\u001b6\t\t\u00111\u00016\u00035\u0001(/[7bef\u0014f)\u001b7fA\u0005AQo]3s\u0003J<7/F\u0001a!\r\tg-N\u0007\u0002E*\u00111\rZ\u0001\b[V$\u0018M\u00197f\u0015\t)g%\u0001\u0006d_2dWm\u0019;j_:L!a\u001a2\u0003\u0017\u0005\u0013(/Y=Ck\u001a4WM]\u0001\rkN,'/\u0011:hg~#S-\u001d\u000b\u0003\u0013*Dq!\u0014\t\u0002\u0002\u0003\u0007\u0001-A\u0005vg\u0016\u0014\u0018I]4tA\u00059a/\u001a:c_N,W#\u00018\u0011\u0005\u0015z\u0017B\u00019'\u0005\u001d\u0011un\u001c7fC:\f1B^3sE>\u001cXm\u0018\u0013fcR\u0011\u0011j\u001d\u0005\b\u001bN\t\t\u00111\u0001o\u0003!1XM\u001d2pg\u0016\u0004\u0013!\u00039beN,\u0017I]4t)\tIu\u000fC\u0003y+\u0001\u0007\u00110A\u0005j]B,H/\u0011:hgB\u0019!p`\u001b\u000f\u0005mlhB\u0001\u001d}\u0013\u00059\u0013B\u0001@'\u0003\u001d\u0001\u0018mY6bO\u0016LA!!\u0001\u0002\u0004\t!A*[:u\u0015\tqh%A\bhKR,6/Y4f\u001b\u0016\u001c8/Y4f)\r)\u0014\u0011\u0002\u0005\t\u0003\u00171\u0002\u0013!a\u0001s\u0006aQO\\6o_^t\u0007+\u0019:b[\u0006Ir-\u001a;Vg\u0006<W-T3tg\u0006<W\r\n3fM\u0006,H\u000e\u001e\u00132+\t\t\tBK\u0002z\u0003'Y#!!\u0006\u0011\t\u0005]\u0011\u0011E\u0007\u0003\u00033QA!a\u0007\u0002\u001e\u0005IQO\\2iK\u000e\\W\r\u001a\u0006\u0004\u0003?1\u0013AC1o]>$\u0018\r^5p]&!\u00111EA\r\u0005E)hn\u00195fG.,GMV1sS\u0006t7-\u001a"
)
public class ClientArguments implements Logging {
   private String userJar;
   private String userClass;
   private String primaryPyFile;
   private String primaryRFile;
   private ArrayBuffer userArgs;
   private boolean verbose;
   private transient Logger org$apache$spark$internal$Logging$$log_;

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

   public String userJar() {
      return this.userJar;
   }

   public void userJar_$eq(final String x$1) {
      this.userJar = x$1;
   }

   public String userClass() {
      return this.userClass;
   }

   public void userClass_$eq(final String x$1) {
      this.userClass = x$1;
   }

   public String primaryPyFile() {
      return this.primaryPyFile;
   }

   public void primaryPyFile_$eq(final String x$1) {
      this.primaryPyFile = x$1;
   }

   public String primaryRFile() {
      return this.primaryRFile;
   }

   public void primaryRFile_$eq(final String x$1) {
      this.primaryRFile = x$1;
   }

   public ArrayBuffer userArgs() {
      return this.userArgs;
   }

   public void userArgs_$eq(final ArrayBuffer x$1) {
      this.userArgs = x$1;
   }

   public boolean verbose() {
      return this.verbose;
   }

   public void verbose_$eq(final boolean x$1) {
      this.verbose = x$1;
   }

   private void parseArgs(final List inputArgs) {
      List args = inputArgs;

      while(!args.isEmpty()) {
         boolean var5 = false;
         .colon.colon var6 = null;
         if (args instanceof .colon.colon) {
            var5 = true;
            var6 = (.colon.colon)args;
            String var8 = (String)var6.head();
            List var9 = var6.next$access$1();
            if ("--jar".equals(var8) && var9 instanceof .colon.colon) {
               .colon.colon var10 = (.colon.colon)var9;
               String value = (String)var10.head();
               List tail = var10.next$access$1();
               this.userJar_$eq(value);
               args = tail;
               BoxedUnit var40 = BoxedUnit.UNIT;
               continue;
            }
         }

         if (var5) {
            String var13 = (String)var6.head();
            List var14 = var6.next$access$1();
            if ("--class".equals(var13) && var14 instanceof .colon.colon) {
               .colon.colon var15 = (.colon.colon)var14;
               String value = (String)var15.head();
               List tail = var15.next$access$1();
               this.userClass_$eq(value);
               args = tail;
               BoxedUnit var39 = BoxedUnit.UNIT;
               continue;
            }
         }

         if (var5) {
            String var18 = (String)var6.head();
            List var19 = var6.next$access$1();
            if ("--primary-py-file".equals(var18) && var19 instanceof .colon.colon) {
               .colon.colon var20 = (.colon.colon)var19;
               String value = (String)var20.head();
               List tail = var20.next$access$1();
               this.primaryPyFile_$eq(value);
               args = tail;
               BoxedUnit var38 = BoxedUnit.UNIT;
               continue;
            }
         }

         if (var5) {
            String var23 = (String)var6.head();
            List var24 = var6.next$access$1();
            if ("--primary-r-file".equals(var23) && var24 instanceof .colon.colon) {
               .colon.colon var25 = (.colon.colon)var24;
               String value = (String)var25.head();
               List tail = var25.next$access$1();
               this.primaryRFile_$eq(value);
               args = tail;
               BoxedUnit var37 = BoxedUnit.UNIT;
               continue;
            }
         }

         if (var5) {
            String var28 = (String)var6.head();
            List var29 = var6.next$access$1();
            if ("--arg".equals(var28) && var29 instanceof .colon.colon) {
               .colon.colon var30 = (.colon.colon)var29;
               String value = (String)var30.head();
               List tail = var30.next$access$1();
               this.userArgs().$plus$eq(value);
               args = tail;
               BoxedUnit var36 = BoxedUnit.UNIT;
               continue;
            }
         }

         if (var5) {
            String var33 = (String)var6.head();
            List tail = var6.next$access$1();
            if ("--verbose".equals(var33) ? true : "-v".equals(var33)) {
               this.verbose_$eq(true);
               args = tail;
               BoxedUnit var35 = BoxedUnit.UNIT;
               continue;
            }
         }

         if (!scala.collection.immutable.Nil..MODULE$.equals(args)) {
            throw new IllegalArgumentException(this.getUsageMessage(args));
         }

         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      if (this.primaryPyFile() != null && this.primaryRFile() != null) {
         throw new IllegalArgumentException("Cannot have primary-py-file and primary-r-file at the same time");
      } else if (this.verbose()) {
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Parsed user args for YARN application: [", "]"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.ARGS..MODULE$, this.userArgs().mkString(" "))})))));
      }
   }

   private String getUsageMessage(final List unknownParam) {
      String message = unknownParam != null ? "Unknown/unsupported param " + unknownParam + "\n" : "";
      return message + scala.collection.StringOps..MODULE$.stripMargin$extension(scala.Predef..MODULE$.augmentString("\n      |Usage: org.apache.spark.deploy.yarn.Client [options]\n      |Options:\n      |  --jar JAR_PATH           Path to your application's JAR file (required in YARN cluster\n      |                           mode)\n      |  --class CLASS_NAME       Name of your application's main class (required)\n      |  --primary-py-file        A main Python file\n      |  --primary-r-file         A main R file\n      |  --arg ARG                Argument to be passed to your application's main class.\n      |                           Multiple invocations are possible, each will be passed in order.\n      |  --verbose, -v            Print additional debug output.\n      "));
   }

   private List getUsageMessage$default$1() {
      return null;
   }

   public ClientArguments(final String[] args) {
      Logging.$init$(this);
      this.userJar = null;
      this.userClass = null;
      this.primaryPyFile = null;
      this.primaryRFile = null;
      this.userArgs = new ArrayBuffer();
      this.verbose = false;
      this.parseArgs(scala.Predef..MODULE$.wrapRefArray((Object[])args).toList());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
