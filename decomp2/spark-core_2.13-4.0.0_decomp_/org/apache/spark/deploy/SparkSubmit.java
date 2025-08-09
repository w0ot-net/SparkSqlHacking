package org.apache.spark.deploy;

import java.io.File;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.UndeclaredThrowableException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.security.PrivilegedExceptionAction;
import java.util.Map;
import java.util.jar.JarInputStream;
import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.security.UserGroupInformation;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.SparkContext$;
import org.apache.spark.SparkException;
import org.apache.spark.SparkUserAppException;
import org.apache.spark.package$;
import org.apache.spark.api.r.RUtils$;
import org.apache.spark.deploy.rest.RestSubmissionClient;
import org.apache.spark.deploy.rest.RestSubmissionClient$;
import org.apache.spark.deploy.rest.SubmitRestConnectionException;
import org.apache.spark.deploy.rest.SubmitRestProtocolResponse;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.Logging.;
import org.apache.spark.internal.config.ConfigEntry;
import org.apache.spark.internal.config.UI$;
import org.apache.spark.util.ChildFirstURLClassLoader;
import org.apache.spark.util.DependencyUtils$;
import org.apache.spark.util.MutableURLClassLoader;
import org.slf4j.Logger;
import scala.Enumeration;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.Option;
import scala.Predef;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.Tuple4;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.immutable.List;
import scala.collection.immutable.Seq;
import scala.collection.mutable.ArrayBuffer;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005\t=c!B!C\u0001\u0011S\u0005\"B,\u0001\t\u0003I\u0006\"\u0002/\u0001\t#j\u0006\"B5\u0001\t\u0003Q\u0007\"B:\u0001\t#!\b\"B=\u0001\t\u0013Q\bbBA\u0003\u0001\u0011%\u0011q\u0001\u0005\b\u0003\u001b\u0001A\u0011BA\b\u0011\u001d\t\t\u0002\u0001C\u0005\u0003'A\u0001\"!\r\u0001\t\u0003\u0011\u00151\u0007\u0005\u000b\u0003O\u0002\u0011\u0013!C\u0001\u0005\u0006%\u0004bBA>\u0001\u0011%\u0011Q\u0010\u0005\b\u0003\u0003\u0003A\u0011BAB\u0011\u001d\t\u0019\n\u0001C\u0005\u0003+Cq!a'\u0001\t\u0013\tijB\u0004\u0002$\nC\t!!*\u0007\r\u0005\u0013\u0005\u0012AAT\u0011\u00199\u0006\u0003\"\u0001\u00020\"I\u0011\u0011\u0017\tC\u0002\u0013%\u00111\u0017\u0005\t\u0003w\u0003\u0002\u0015!\u0003\u00026\"I\u0011Q\u0018\tC\u0002\u0013%\u00111\u0017\u0005\t\u0003\u007f\u0003\u0002\u0015!\u0003\u00026\"I\u0011\u0011\u0019\tC\u0002\u0013%\u00111\u0017\u0005\t\u0003\u0007\u0004\u0002\u0015!\u0003\u00026\"I\u0011Q\u0019\tC\u0002\u0013%\u00111\u0017\u0005\t\u0003\u000f\u0004\u0002\u0015!\u0003\u00026\"I\u0011\u0011\u001a\tC\u0002\u0013%\u00111\u0017\u0005\t\u0003\u0017\u0004\u0002\u0015!\u0003\u00026\"I\u0011Q\u001a\tC\u0002\u0013%\u00111\u0017\u0005\t\u0003\u001f\u0004\u0002\u0015!\u0003\u00026\"I\u0011\u0011\u001b\tC\u0002\u0013%\u00111\u0017\u0005\t\u0003'\u0004\u0002\u0015!\u0003\u00026\"I\u0011Q\u001b\tC\u0002\u0013%\u00111\u0017\u0005\t\u0003/\u0004\u0002\u0015!\u0003\u00026\"I\u0011\u0011\u001c\tC\u0002\u0013%\u00111\u001c\u0005\t\u0003W\u0004\u0002\u0015!\u0003\u0002^\"I\u0011Q\u001e\tC\u0002\u0013%\u00111\u001c\u0005\t\u0003_\u0004\u0002\u0015!\u0003\u0002^\"I\u0011\u0011\u001f\tC\u0002\u0013%\u00111\u001c\u0005\t\u0003g\u0004\u0002\u0015!\u0003\u0002^\"I\u0011Q\u001f\tC\u0002\u0013%\u00111\u001c\u0005\t\u0003o\u0004\u0002\u0015!\u0003\u0002^\"I\u0011\u0011 \tC\u0002\u0013%\u00111\u001c\u0005\t\u0003w\u0004\u0002\u0015!\u0003\u0002^\"I\u0011Q \tC\u0002\u0013%\u00111\u001c\u0005\t\u0003\u007f\u0004\u0002\u0015!\u0003\u0002^\"I!\u0011\u0001\tC\u0002\u0013%\u00111\u0017\u0005\t\u0005\u0007\u0001\u0002\u0015!\u0003\u00026\"Q!Q\u0001\tC\u0002\u0013\u0005!)a7\t\u0011\t\u001d\u0001\u0003)A\u0005\u0003;D!B!\u0003\u0011\u0005\u0004%\tAQAn\u0011!\u0011Y\u0001\u0005Q\u0001\n\u0005u\u0007B\u0003B\u0007!\t\u0007I\u0011\u0001\"\u0002\\\"A!q\u0002\t!\u0002\u0013\ti\u000e\u0003\u0006\u0003\u0012A\u0011\r\u0011\"\u0001C\u00037D\u0001Ba\u0005\u0011A\u0003%\u0011Q\u001c\u0005\b\u0005+\u0001B\u0011\tB\f\u0011!\u0011Y\u0002\u0005C\u0001\u0005\nu\u0001\u0002\u0003B\u0012!\u0011\u0005!I!\n\t\u0011\t%\u0002\u0003\"\u0001C\u0005WAqA!\r\u0011\t\u0013\u0011\u0019\u0004C\u0004\u00038A!IA!\u000f\t\u0011\tu\u0002\u0003\"\u0001C\u0005\u007fA\u0001Ba\u0011\u0011\t\u0003\u0011%Q\t\u0005\t\u0005\u0013\u0002B\u0011\u0001\"\u0003L\tY1\u000b]1sWN+(-\\5u\u0015\t\u0019E)\u0001\u0004eKBdw.\u001f\u0006\u0003\u000b\u001a\u000bQa\u001d9be.T!a\u0012%\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005I\u0015aA8sON\u0019\u0001aS)\u0011\u00051{U\"A'\u000b\u00039\u000bQa]2bY\u0006L!\u0001U'\u0003\r\u0005s\u0017PU3g!\t\u0011V+D\u0001T\u0015\t!F)\u0001\u0005j]R,'O\\1m\u0013\t16KA\u0004M_\u001e<\u0017N\\4\u0002\rqJg.\u001b;?\u0007\u0001!\u0012A\u0017\t\u00037\u0002i\u0011AQ\u0001\bY><g*Y7f+\u0005q\u0006CA0g\u001d\t\u0001G\r\u0005\u0002b\u001b6\t!M\u0003\u0002d1\u00061AH]8pizJ!!Z'\u0002\rA\u0013X\rZ3g\u0013\t9\u0007N\u0001\u0004TiJLgn\u001a\u0006\u0003K6\u000b\u0001\u0002Z8Tk\nl\u0017\u000e\u001e\u000b\u0003W:\u0004\"\u0001\u00147\n\u00055l%\u0001B+oSRDQa\\\u0002A\u0002A\fA!\u0019:hgB\u0019A*\u001d0\n\u0005Il%!B!se\u0006L\u0018A\u00049beN,\u0017I]4v[\u0016tGo\u001d\u000b\u0003kb\u0004\"a\u0017<\n\u0005]\u0014%\u0001F*qCJ\\7+\u001e2nSR\f%oZ;nK:$8\u000fC\u0003p\t\u0001\u0007\u0001/\u0001\u0003lS2dGcA6|y\")q.\u0002a\u0001k\")Q0\u0002a\u0001}\u0006I1\u000f]1sW\u000e{gN\u001a\t\u0004\u007f\u0006\u0005Q\"\u0001#\n\u0007\u0005\rAIA\u0005Ta\u0006\u00148nQ8oM\u0006i!/Z9vKN$8\u000b^1ukN$Ra[A\u0005\u0003\u0017AQa\u001c\u0004A\u0002UDQ! \u0004A\u0002y\fA\u0002\u001d:j]R4VM]:j_:$\u0012a[\u0001\u0007gV\u0014W.\u001b;\u0015\u000f-\f)\"a\u0006\u0002\"!)q\u000e\u0003a\u0001k\"9\u0011\u0011\u0004\u0005A\u0002\u0005m\u0011!C;oS:LG\u000fT8h!\ra\u0015QD\u0005\u0004\u0003?i%a\u0002\"p_2,\u0017M\u001c\u0005\u0006{\"\u0001\rA \u0015\u0004\u0011\u0005\u0015\u0002\u0003BA\u0014\u0003[i!!!\u000b\u000b\u0007\u0005-R*\u0001\u0006b]:|G/\u0019;j_:LA!a\f\u0002*\t9A/Y5me\u0016\u001c\u0017\u0001\u00079sKB\f'/Z*vE6LG/\u00128wSJ|g.\\3oiR1\u0011QGA'\u0003\u001f\u0002\u0012\u0002TA\u001c\u0003w\tYD 0\n\u0007\u0005eRJ\u0001\u0004UkBdW\r\u000e\t\u0006\u0003{\t9E\u0018\b\u0005\u0003\u007f\t\u0019ED\u0002b\u0003\u0003J\u0011AT\u0005\u0004\u0003\u000bj\u0015a\u00029bG.\fw-Z\u0005\u0005\u0003\u0013\nYEA\u0002TKFT1!!\u0012N\u0011\u0015y\u0017\u00021\u0001v\u0011%\t\t&\u0003I\u0001\u0002\u0004\t\u0019&\u0001\u0003d_:4\u0007#\u0002'\u0002V\u0005e\u0013bAA,\u001b\n1q\n\u001d;j_:\u0004B!a\u0017\u0002d5\u0011\u0011Q\f\u0006\u0005\u0003#\nyFC\u0002\u0002b\u0019\u000ba\u0001[1e_>\u0004\u0018\u0002BA3\u0003;\u0012QbQ8oM&<WO]1uS>t\u0017A\t9sKB\f'/Z*vE6LG/\u00128wSJ|g.\\3oi\u0012\"WMZ1vYR$#'\u0006\u0002\u0002l)\"\u00111KA7W\t\ty\u0007\u0005\u0003\u0002r\u0005]TBAA:\u0015\u0011\t)(!\u000b\u0002\u0013Ut7\r[3dW\u0016$\u0017\u0002BA=\u0003g\u0012\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u00039\u0019X\r\u001e*N!JLgnY5qC2$2a[A@\u0011\u0015i8\u00021\u0001\u007f\u0003Q9W\r^*vE6LGo\u00117bgNdu.\u00193feR!\u0011QQAI!\u0011\t9)!$\u000e\u0005\u0005%%bAAF\t\u0006!Q\u000f^5m\u0013\u0011\ty)!#\u0003+5+H/\u00192mKV\u0013Fj\u00117bgNdu.\u00193fe\")Q\u0010\u0004a\u0001}\u00069!/\u001e8NC&tG#B6\u0002\u0018\u0006e\u0005\"B8\u000e\u0001\u0004)\bbBA\r\u001b\u0001\u0007\u00111D\u0001\u0006KJ\u0014xN\u001d\u000b\u0004W\u0006}\u0005BBAQ\u001d\u0001\u0007a,A\u0002ng\u001e\f1b\u00159be.\u001cVOY7jiB\u00111\fE\n\u0006!-\u000bI+\u0015\t\u0005\u0003\u000f\u000bY+\u0003\u0003\u0002.\u0006%%\u0001E\"p[6\fg\u000e\u001a'j]\u0016,F/\u001b7t)\t\t)+\u0001\u0003Z\u0003JsUCAA[!\ra\u0015qW\u0005\u0004\u0003sk%aA%oi\u0006)\u0011,\u0011*OA\u0005Q1\u000bV!O\t\u0006cuJT#\u0002\u0017M#\u0016I\u0014#B\u0019>sU\tI\u0001\u0006\u0019>\u001b\u0015\tT\u0001\u0007\u0019>\u001b\u0015\t\u0014\u0011\u0002\u0015-+&)\u0012*O\u000bR+5+A\u0006L+\n+%KT#U\u000bN\u0003\u0013\u0001E!M\u0019~\u001bE*V*U\u000bJ{Vj\u0012*T\u0003E\tE\nT0D\u0019V\u001bF+\u0012*`\u001b\u001e\u00136\u000bI\u0001\u0007\u00072KUI\u0014+\u0002\u000f\rc\u0015*\u0012(UA\u000591\tT+T)\u0016\u0013\u0016\u0001C\"M+N#VI\u0015\u0011\u0002!\u0005cEj\u0018#F!2{\u0015lX'P\t\u0016\u001b\u0016!E!M\u0019~#U\t\u0015'P3~ku\nR#TA\u0005Y1\u000bU!S\u0017~\u001b\u0006*\u0012'M+\t\ti\u000e\u0005\u0003\u0002`\u0006%XBAAq\u0015\u0011\t\u0019/!:\u0002\t1\fgn\u001a\u0006\u0003\u0003O\fAA[1wC&\u0019q-!9\u0002\u0019M\u0003\u0016IU&`'\"+E\n\u0014\u0011\u0002\u001bAK6\u000bU!S\u0017~\u001b\u0006*\u0012'M\u00039\u0001\u0016l\u0015)B%.{6\u000bS#M\u0019\u0002\nAb\u0015)B%.\u0013vl\u0015%F\u00192\u000bQb\u0015)B%.\u0013vl\u0015%F\u00192\u0003\u0013!D\"P\u001d:+5\tV0T\u0011\u0016cE*\u0001\bD\u001f:sUi\u0011+`'\"+E\n\u0014\u0011\u0002-M\u0003\u0016IU&S?B\u000b5iS!H\u000b~\u000b%k\u0011%J-\u0016\u000bqc\u0015)B%.\u0013v\fU!D\u0017\u0006;UiX!S\u0007\"Ke+\u0012\u0011\u0002#I{\u0006+Q\"L\u0003\u001e+u,\u0011*D\u0011&3V)\u0001\nS?B\u000b5iS!H\u000b~\u000b%k\u0011%J-\u0016\u0003\u0013aG\"M\u0003N\u001bvLT(U?\u001a{UK\u0014#`\u000bbKEkX*U\u0003R+6+\u0001\u000fD\u0019\u0006\u001b6k\u0018(P)~3u*\u0016(E?\u0016C\u0016\nV0T)\u0006#Vk\u0015\u0011\u00023e\u000b%KT0D\u0019V\u001bF+\u0012*`'V\u0013U*\u0013+`\u00072\u000b5kU\u0001\u001b3\u0006\u0013fjX\"M+N#VIU0T+\nk\u0015\nV0D\u0019\u0006\u001b6\u000bI\u0001\u001a%\u0016\u001bFkX\"M+N#VIU0T+\nk\u0015\nV0D\u0019\u0006\u001b6+\u0001\u000eS\u000bN#vl\u0011'V'R+%kX*V\u00056KEkX\"M\u0003N\u001b\u0006%A\u0010T)\u0006sE)\u0011'P\u001d\u0016{6\tT+T)\u0016\u0013vlU+C\u001b&#vl\u0011'B'N\u000b\u0001e\u0015+B\u001d\u0012\u000bEj\u0014(F?\u000ecUk\u0015+F%~\u001bVKQ'J)~\u001bE*Q*TA\u0005y2*\u0016\"F%:+E+R*`\u00072+6\u000bV#S?N+&)T%U?\u000ec\u0015iU*\u0002A-+&)\u0012*O\u000bR+5kX\"M+N#VIU0T+\nk\u0015\nV0D\u0019\u0006\u001b6\u000bI\u0001\u0005[\u0006Lg\u000eF\u0002l\u00053AQa\u001c\u001dA\u0002A\f\u0011\"[:Vg\u0016\u0014(*\u0019:\u0015\t\u0005m!q\u0004\u0005\u0007\u0005CI\u0004\u0019\u00010\u0002\u0007I,7/A\u0004jgNCW\r\u001c7\u0015\t\u0005m!q\u0005\u0005\u0007\u0005CQ\u0004\u0019\u00010\u0002\u0015%\u001c8+\u001d7TQ\u0016dG\u000e\u0006\u0003\u0002\u001c\t5\u0002B\u0002B\u0018w\u0001\u0007a,A\u0005nC&t7\t\\1tg\u0006q\u0011n\u001d+ie&4GoU3sm\u0016\u0014H\u0003BA\u000e\u0005kAaAa\f=\u0001\u0004q\u0016aD5t\u0007>tg.Z2u'\u0016\u0014h/\u001a:\u0015\t\u0005m!1\b\u0005\u0007\u0005_i\u0004\u0019\u00010\u0002\u0011%\u001c\b+\u001f;i_:$B!a\u0007\u0003B!1!\u0011\u0005 A\u0002y\u000b1![:S)\u0011\tYBa\u0012\t\r\t\u0005r\b1\u0001_\u0003)I7/\u00138uKJt\u0017\r\u001c\u000b\u0005\u00037\u0011i\u0005\u0003\u0004\u0003\"\u0001\u0003\rA\u0018"
)
public class SparkSubmit implements Logging {
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static void main(final String[] args) {
      SparkSubmit$.MODULE$.main(args);
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

   public String logName() {
      return SparkSubmit.class.getName();
   }

   public void doSubmit(final String[] args) {
      SparkSubmitArguments appArgs = this.parseArguments(args);
      SparkConf sparkConf = appArgs.toSparkConf(appArgs.toSparkConf$default$1());
      if (!SparkSubmit$.MODULE$.isShell(appArgs.primaryResource()) && !SparkSubmit$.MODULE$.isSqlShell(appArgs.mainClass())) {
         org.apache.spark.util.Utils$.MODULE$.resetStructuredLogging(sparkConf);
      } else {
         .MODULE$.disableStructuredLogging();
      }

      .MODULE$.uninitialize();
      boolean uninitLog = this.initializeLogIfNecessary(true, true);
      if (appArgs.verbose()) {
         this.logInfo((Function0)(() -> appArgs.toString()));
      }

      label71: {
         Enumeration.Value var6 = appArgs.action();
         Enumeration.Value var10000 = SparkSubmitAction$.MODULE$.SUBMIT();
         if (var10000 == null) {
            if (var6 == null) {
               break label71;
            }
         } else if (var10000.equals(var6)) {
            break label71;
         }

         label72: {
            var10000 = SparkSubmitAction$.MODULE$.KILL();
            if (var10000 == null) {
               if (var6 == null) {
                  break label72;
               }
            } else if (var10000.equals(var6)) {
               break label72;
            }

            label73: {
               var10000 = SparkSubmitAction$.MODULE$.REQUEST_STATUS();
               if (var10000 == null) {
                  if (var6 == null) {
                     break label73;
                  }
               } else if (var10000.equals(var6)) {
                  break label73;
               }

               label41: {
                  var10000 = SparkSubmitAction$.MODULE$.PRINT_VERSION();
                  if (var10000 == null) {
                     if (var6 == null) {
                        break label41;
                     }
                  } else if (var10000.equals(var6)) {
                     break label41;
                  }

                  throw new MatchError(var6);
               }

               this.printVersion();
               BoxedUnit var14 = BoxedUnit.UNIT;
               return;
            }

            this.requestStatus(appArgs, sparkConf);
            BoxedUnit var15 = BoxedUnit.UNIT;
            return;
         }

         this.kill(appArgs, sparkConf);
         BoxedUnit var16 = BoxedUnit.UNIT;
         return;
      }

      this.submit(appArgs, uninitLog, sparkConf);
      BoxedUnit var17 = BoxedUnit.UNIT;
   }

   public SparkSubmitArguments parseArguments(final String[] args) {
      return new SparkSubmitArguments(org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(args).toImmutableArraySeq(), SparkSubmitArguments$.MODULE$.$lessinit$greater$default$2());
   }

   private void kill(final SparkSubmitArguments args, final SparkConf sparkConf) {
      if (RestSubmissionClient$.MODULE$.supportsRestClient(args.master())) {
         SubmitRestProtocolResponse response = (new RestSubmissionClient(args.master())).killSubmission(args.submissionToKill());
         if (scala.Predef..MODULE$.Boolean2boolean(response.success())) {
            this.logInfo((Function0)(() -> args.submissionToKill() + " is killed successfully."));
         } else {
            this.logError((Function0)(() -> response.message()));
         }
      } else {
         sparkConf.set("spark.master", args.master());
         SparkSubmitUtils$.MODULE$.getSubmitOperations(args.master()).kill(args.submissionToKill(), sparkConf);
      }
   }

   private void requestStatus(final SparkSubmitArguments args, final SparkConf sparkConf) {
      if (RestSubmissionClient$.MODULE$.supportsRestClient(args.master())) {
         RestSubmissionClient qual$1 = new RestSubmissionClient(args.master());
         String x$1 = args.submissionToRequestStatusFor();
         boolean x$2 = qual$1.requestSubmissionStatus$default$2();
         qual$1.requestSubmissionStatus(x$1, x$2);
      } else {
         sparkConf.set("spark.master", args.master());
         SparkSubmitUtils$.MODULE$.getSubmitOperations(args.master()).printSubmissionStatus(args.submissionToRequestStatusFor(), sparkConf);
      }
   }

   private void printVersion() {
      this.logInfo((Function0)(() -> scala.collection.StringOps..MODULE$.format$extension(scala.Predef..MODULE$.augmentString("Welcome to\n      ____              __\n     / __/__  ___ _____/ /__\n    _\\ \\/ _ \\/ _ `/ __/  '_/\n   /___/ .__/\\_,_/_/ /_/\\_\\   version %s\n      /_/\n                        "), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{package$.MODULE$.SPARK_VERSION()}))));
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Using Scala ", ","})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SCALA_VERSION..MODULE$, scala.util.Properties..MODULE$.versionString())}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" ", ","})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.JAVA_VM_NAME..MODULE$, scala.util.Properties..MODULE$.javaVmName())})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.JAVA_VERSION..MODULE$, scala.util.Properties..MODULE$.javaVersion())}))))));
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Branch ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SPARK_BRANCH..MODULE$, package$.MODULE$.SPARK_BRANCH())})))));
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Compiled by user ", " on"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SPARK_BUILD_USER..MODULE$, package$.MODULE$.SPARK_BUILD_USER())}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SPARK_BUILD_DATE..MODULE$, package$.MODULE$.SPARK_BUILD_DATE())}))))));
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Revision ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SPARK_REVISION..MODULE$, package$.MODULE$.SPARK_REVISION())})))));
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Url ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SPARK_REPO_URL..MODULE$, package$.MODULE$.SPARK_REPO_URL())})))));
      this.logInfo((Function0)(() -> "Type --help for more information."));
   }

   private void submit(final SparkSubmitArguments args, final boolean uninitLog, final SparkConf sparkConf) {
      while(true) {
         if (!args.isStandaloneCluster() || !args.useRest()) {
            this.doRunMain$1(args, sparkConf, uninitLog);
            BoxedUnit var7 = BoxedUnit.UNIT;
            break;
         }

         try {
            this.logInfo((Function0)(() -> "Running Spark using the REST application submission protocol."));
            this.doRunMain$1(args, sparkConf, uninitLog);
            BoxedUnit var10000 = BoxedUnit.UNIT;
            break;
         } catch (SubmitRestConnectionException var6) {
            this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Master endpoint ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MASTER_URL..MODULE$, args.master())}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"was not a REST server. Falling back to legacy submission gateway instead."})))).log(scala.collection.immutable.Nil..MODULE$))));
            args.useRest_$eq(false);
            sparkConf = sparkConf;
            uninitLog = false;
            args = args;
         }
      }

   }

   public Tuple4 prepareSubmitEnvironment(final SparkSubmitArguments args, final Option conf) {
      ArrayBuffer childArgs = new ArrayBuffer();
      ArrayBuffer childClasspath = new ArrayBuffer();
      SparkConf sparkConf = args.toSparkConf(args.toSparkConf$default$1());
      String childMainClass = "";
      Option var13 = args.maybeMaster();
      int var10000;
      if (var13 instanceof Some) {
         Some var14 = (Some)var13;
         String v = (String)var14.value();
         scala.Predef..MODULE$.assert(args.maybeRemote().isEmpty());
         switch (v == null ? 0 : v.hashCode()) {
            case 3701572:
               if ("yarn".equals(v)) {
                  var10000 = SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$YARN();
                  break;
               }
            default:
               if (v.startsWith("spark")) {
                  var10000 = SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$STANDALONE();
               } else if (v.startsWith("k8s")) {
                  var10000 = SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$KUBERNETES();
               } else if (v.startsWith("local")) {
                  var10000 = SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$LOCAL();
               } else {
                  this.error("Master must either be yarn or start with spark, k8s, or local");
                  var10000 = -1;
               }
         }
      } else {
         if (!scala.None..MODULE$.equals(var13)) {
            throw new MatchError(var13);
         }

         var10000 = SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$LOCAL();
      }

      int clusterManager;
      label611: {
         label610: {
            clusterManager = var10000;
            String var18 = args.deployMode();
            switch (var18 == null ? 0 : var18.hashCode()) {
               case -1357712437:
                  if ("client".equals(var18)) {
                     break label610;
                  }
                  break;
               case 0:
                  if (var18 == null) {
                     break label610;
                  }
                  break;
               case 872092154:
                  if ("cluster".equals(var18)) {
                     var10000 = SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$CLUSTER();
                     break label611;
                  }
            }

            this.error("Deploy mode must be either client or cluster");
            var10000 = -1;
            break label611;
         }

         var10000 = SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$CLIENT();
      }

      int deployMode = var10000;
      if (clusterManager == SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$YARN() && !org.apache.spark.util.Utils$.MODULE$.classIsLoadable(SparkSubmit$.MODULE$.YARN_CLUSTER_SUBMIT_CLASS()) && !org.apache.spark.util.Utils$.MODULE$.isTesting()) {
         this.error("Could not load YARN classes. This copy of Spark may not have been compiled with YARN support.");
      }

      if (clusterManager == SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$KUBERNETES()) {
         args.maybeMaster_$eq(scala.Option..MODULE$.apply(org.apache.spark.util.Utils$.MODULE$.checkAndGetK8sMasterUrl(args.master())));
         if (!org.apache.spark.util.Utils$.MODULE$.classIsLoadable(SparkSubmit$.MODULE$.KUBERNETES_CLUSTER_SUBMIT_CLASS()) && !org.apache.spark.util.Utils$.MODULE$.isTesting()) {
            this.error("Could not load KUBERNETES classes. This copy of Spark may not have been compiled with KUBERNETES support.");
         }
      }

      label621: {
         Tuple2.mcII.sp var19 = new Tuple2.mcII.sp(clusterManager, deployMode);
         if (var19 != null) {
            int var20 = ((Tuple2)var19)._1$mcI$sp();
            int var21 = ((Tuple2)var19)._2$mcI$sp();
            if (SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$STANDALONE() == var20 && SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$CLUSTER() == var21 && args.isPython()) {
               this.error("Cluster deploy mode is currently not supported for python applications on standalone clusters.");
               BoxedUnit var288 = BoxedUnit.UNIT;
               break label621;
            }
         }

         if (var19 != null) {
            int var22 = ((Tuple2)var19)._1$mcI$sp();
            int var23 = ((Tuple2)var19)._2$mcI$sp();
            if (SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$STANDALONE() == var22 && SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$CLUSTER() == var23 && args.isR()) {
               this.error("Cluster deploy mode is currently not supported for R applications on standalone clusters.");
               BoxedUnit var287 = BoxedUnit.UNIT;
               break label621;
            }
         }

         if (var19 != null) {
            int var24 = ((Tuple2)var19)._1$mcI$sp();
            int var25 = ((Tuple2)var19)._2$mcI$sp();
            if (SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$LOCAL() == var24 && SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$CLUSTER() == var25) {
               this.error("Cluster deploy mode is not compatible with master \"local\"");
               BoxedUnit var286 = BoxedUnit.UNIT;
               break label621;
            }
         }

         if (var19 != null) {
            int var26 = ((Tuple2)var19)._2$mcI$sp();
            if (SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$CLUSTER() == var26 && SparkSubmit$.MODULE$.isShell(args.primaryResource())) {
               this.error("Cluster deploy mode is not applicable to Spark shells.");
               BoxedUnit var285 = BoxedUnit.UNIT;
               break label621;
            }
         }

         if (var19 != null) {
            int var27 = ((Tuple2)var19)._2$mcI$sp();
            if (SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$CLUSTER() == var27 && SparkSubmit$.MODULE$.isSqlShell(args.mainClass())) {
               this.error("Cluster deploy mode is not applicable to Spark SQL shell.");
               BoxedUnit var284 = BoxedUnit.UNIT;
               break label621;
            }
         }

         if (var19 != null) {
            int var28 = ((Tuple2)var19)._2$mcI$sp();
            if (SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$CLUSTER() == var28 && SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$isThriftServer(args.mainClass())) {
               this.error("Cluster deploy mode is not applicable to Spark Thrift server.");
               BoxedUnit var283 = BoxedUnit.UNIT;
               break label621;
            }
         }

         if (var19 != null) {
            int var29 = ((Tuple2)var19)._2$mcI$sp();
            if (SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$CLUSTER() == var29 && SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$isConnectServer(args.mainClass())) {
               this.error("Cluster deploy mode is not applicable to Spark Connect server.");
               BoxedUnit var282 = BoxedUnit.UNIT;
               break label621;
            }
         }

         BoxedUnit var281 = BoxedUnit.UNIT;
      }

      label622: {
         Tuple2 var30 = new Tuple2(args.deployMode(), BoxesRunTime.boxToInteger(deployMode));
         if (var30 != null) {
            String var31 = (String)var30._1();
            int var32 = var30._2$mcI$sp();
            if (var31 == null && SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$CLIENT() == var32) {
               args.deployMode_$eq("client");
               BoxedUnit var291 = BoxedUnit.UNIT;
               break label622;
            }
         }

         if (var30 != null) {
            String var33 = (String)var30._1();
            int var34 = var30._2$mcI$sp();
            if (var33 == null && SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$CLUSTER() == var34) {
               args.deployMode_$eq("cluster");
               BoxedUnit var290 = BoxedUnit.UNIT;
               break label622;
            }
         }

         BoxedUnit var289 = BoxedUnit.UNIT;
      }

      boolean isYarnCluster = clusterManager == SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$YARN() && deployMode == SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$CLUSTER();
      boolean isStandAloneCluster = clusterManager == SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$STANDALONE() && deployMode == SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$CLUSTER();
      boolean isKubernetesCluster = clusterManager == SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$KUBERNETES() && deployMode == SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$CLUSTER();
      boolean isKubernetesClient = clusterManager == SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$KUBERNETES() && deployMode == SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$CLIENT();
      boolean isKubernetesClusterModeDriver = isKubernetesClient && sparkConf.getBoolean("spark.kubernetes.submitInDriver", false);
      boolean isCustomClasspathInClusterModeDisallowed = !BoxesRunTime.unboxToBoolean(sparkConf.get(org.apache.spark.internal.config.package$.MODULE$.ALLOW_CUSTOM_CLASSPATH_BY_PROXY_USER_IN_CLUSTER_MODE())) && args.proxyUser() != null && (isYarnCluster || isStandAloneCluster || isKubernetesCluster);
      if (!isStandAloneCluster) {
         Seq resolvedMavenCoordinates = DependencyUtils$.MODULE$.resolveMavenDependencies(true, args.packagesExclusions(), args.packages(), args.repositories(), args.ivyRepoPath(), args.ivySettingsPath());
         if (!resolvedMavenCoordinates.nonEmpty()) {
            BoxedUnit var293 = BoxedUnit.UNIT;
         } else if (isKubernetesCluster) {
            childClasspath.$plus$plus$eq(resolvedMavenCoordinates);
         } else {
            if (isKubernetesClusterModeDriver) {
               MutableURLClassLoader loader = this.getSubmitClassLoader(sparkConf);
               resolvedMavenCoordinates.foreach((jar) -> {
                  $anonfun$prepareSubmitEnvironment$1(loader, jar);
                  return BoxedUnit.UNIT;
               });
            }

            args.jars_$eq(DependencyUtils$.MODULE$.mergeFileLists(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{args.jars(), DependencyUtils$.MODULE$.mergeFileLists(resolvedMavenCoordinates)}))));
            if (args.isPython() || SparkSubmit$.MODULE$.isInternal(args.primaryResource())) {
               args.pyFiles_$eq(DependencyUtils$.MODULE$.mergeFileLists(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{args.pyFiles(), DependencyUtils$.MODULE$.mergeFileLists(resolvedMavenCoordinates)}))));
            }

            BoxedUnit var292 = BoxedUnit.UNIT;
         }

         if (args.isR() && !StringUtils.isBlank(args.jars())) {
            RPackageUtils$.MODULE$.checkAndBuildRPackage(args.jars(), SparkSubmit$.MODULE$.printStream(), args.verbose());
         }
      }

      args.toSparkConf(scala.Option..MODULE$.apply(sparkConf));
      Configuration hadoopConf = (Configuration)conf.getOrElse(() -> SparkHadoopUtil$.MODULE$.newConfiguration(sparkConf));
      File targetDir = org.apache.spark.util.Utils$.MODULE$.createTempDir();
      if (clusterManager != SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$STANDALONE() && args.principal() != null && args.keytab() != null) {
         if (deployMode == SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$CLIENT() && org.apache.spark.util.Utils$.MODULE$.isLocalUri(args.keytab())) {
            args.keytab_$eq((new URI(args.keytab())).getPath());
         }

         if (!org.apache.spark.util.Utils$.MODULE$.isLocalUri(args.keytab())) {
            scala.Predef..MODULE$.require((new File(args.keytab())).exists(), () -> "Keytab file: " + args.keytab() + " does not exist");
            UserGroupInformation.loginUserFromKeytab(args.principal(), args.keytab());
         }
      }

      args.jars_$eq((String)scala.Option..MODULE$.apply(args.jars()).map((x$1) -> DependencyUtils$.MODULE$.resolveGlobPaths(x$1, hadoopConf)).orNull(scala..less.colon.less..MODULE$.refl()));
      args.files_$eq((String)scala.Option..MODULE$.apply(args.files()).map((x$2) -> DependencyUtils$.MODULE$.resolveGlobPaths(x$2, hadoopConf)).orNull(scala..less.colon.less..MODULE$.refl()));
      args.pyFiles_$eq((String)scala.Option..MODULE$.apply(args.pyFiles()).map((x$3) -> DependencyUtils$.MODULE$.resolveGlobPaths(x$3, hadoopConf)).orNull(scala..less.colon.less..MODULE$.refl()));
      args.archives_$eq((String)scala.Option..MODULE$.apply(args.archives()).map((x$4x) -> DependencyUtils$.MODULE$.resolveGlobPaths(x$4x, hadoopConf)).orNull(scala..less.colon.less..MODULE$.refl()));
      String localPrimaryResource = null;
      String localJars = null;
      String localPyFiles = null;
      if (deployMode == SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$CLIENT()) {
         localPrimaryResource = (String)scala.Option..MODULE$.apply(args.primaryResource()).map((x$5x) -> DependencyUtils$.MODULE$.downloadFile(x$5x, targetDir, sparkConf, hadoopConf)).orNull(scala..less.colon.less..MODULE$.refl());
         localJars = (String)scala.Option..MODULE$.apply(args.jars()).map((x$6x) -> DependencyUtils$.MODULE$.downloadFileList(x$6x, targetDir, sparkConf, hadoopConf)).orNull(scala..less.colon.less..MODULE$.refl());
         localPyFiles = (String)scala.Option..MODULE$.apply(args.pyFiles()).map((x$7x) -> DependencyUtils$.MODULE$.downloadFileList(x$7x, targetDir, sparkConf, hadoopConf)).orNull(scala..less.colon.less..MODULE$.refl());
         if (isKubernetesClusterModeDriver) {
            String workingDirectory = ".";
            childClasspath.$plus$eq(workingDirectory);
            Seq avoidJarDownloadSchemes = (Seq)sparkConf.get(org.apache.spark.internal.config.package$.MODULE$.KUBERNETES_JARS_AVOID_DOWNLOAD_SCHEMES());
            String filesLocalFiles = (String)scala.Option..MODULE$.apply(args.files()).map((x$12x) -> this.downloadResourcesToCurrentDirectory$1(x$12x, downloadResourcesToCurrentDirectory$default$2$1(), downloadResourcesToCurrentDirectory$default$3$1(), targetDir, sparkConf, hadoopConf, workingDirectory)).orNull(scala..less.colon.less..MODULE$.refl());
            String updatedJars = (String)scala.Option..MODULE$.apply(args.jars()).map((x$13x) -> {
               Function1 x$2 = (scheme) -> BoxesRunTime.boxToBoolean($anonfun$prepareSubmitEnvironment$21(avoidJarDownloadSchemes, scheme));
               boolean x$3 = downloadResourcesToCurrentDirectory$default$2$1();
               return this.downloadResourcesToCurrentDirectory$1(x$13x, x$3, x$2, targetDir, sparkConf, hadoopConf, workingDirectory);
            }).orNull(scala..less.colon.less..MODULE$.refl());
            String archiveLocalFiles = (String)scala.Option..MODULE$.apply(args.archives()).map((x$14x) -> this.downloadResourcesToCurrentDirectory$1(x$14x, true, downloadResourcesToCurrentDirectory$default$3$1(), targetDir, sparkConf, hadoopConf, workingDirectory)).orNull(scala..less.colon.less..MODULE$.refl());
            String pyLocalFiles = (String)scala.Option..MODULE$.apply(args.pyFiles()).map((x$15x) -> this.downloadResourcesToCurrentDirectory$1(x$15x, downloadResourcesToCurrentDirectory$default$2$1(), downloadResourcesToCurrentDirectory$default$3$1(), targetDir, sparkConf, hadoopConf, workingDirectory)).orNull(scala..less.colon.less..MODULE$.refl());
            args.files_$eq(filesLocalFiles);
            args.archives_$eq(archiveLocalFiles);
            args.pyFiles_$eq(pyLocalFiles);
            args.jars_$eq(updatedJars);
         }
      }

      if (clusterManager == SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$YARN()) {
         Seq forceDownloadSchemes = (Seq)sparkConf.get(org.apache.spark.internal.config.package$.MODULE$.FORCE_DOWNLOAD_SCHEMES());
         args.primaryResource_$eq((String)scala.Option..MODULE$.apply(args.primaryResource()).map((resource) -> downloadResource$1(resource, targetDir, sparkConf, hadoopConf, forceDownloadSchemes)).orNull(scala..less.colon.less..MODULE$.refl()));
         args.files_$eq((String)scala.Option..MODULE$.apply(args.files()).map((files) -> ((IterableOnceOps)org.apache.spark.util.Utils$.MODULE$.stringToSeq(files).map((resource) -> downloadResource$1(resource, targetDir, sparkConf, hadoopConf, forceDownloadSchemes))).mkString(",")).orNull(scala..less.colon.less..MODULE$.refl()));
         args.pyFiles_$eq((String)scala.Option..MODULE$.apply(args.pyFiles()).map((pyFilesx) -> ((IterableOnceOps)org.apache.spark.util.Utils$.MODULE$.stringToSeq(pyFilesx).map((resource) -> downloadResource$1(resource, targetDir, sparkConf, hadoopConf, forceDownloadSchemes))).mkString(",")).orNull(scala..less.colon.less..MODULE$.refl()));
         args.jars_$eq((String)scala.Option..MODULE$.apply(args.jars()).map((jarsx) -> ((IterableOnceOps)org.apache.spark.util.Utils$.MODULE$.stringToSeq(jarsx).map((resource) -> downloadResource$1(resource, targetDir, sparkConf, hadoopConf, forceDownloadSchemes))).mkString(",")).orNull(scala..less.colon.less..MODULE$.refl()));
         args.archives_$eq((String)scala.Option..MODULE$.apply(args.archives()).map((archives) -> ((IterableOnceOps)org.apache.spark.util.Utils$.MODULE$.stringToSeq(archives).map((resource) -> downloadResource$1(resource, targetDir, sparkConf, hadoopConf, forceDownloadSchemes))).mkString(",")).orNull(scala..less.colon.less..MODULE$.refl()));
      }

      if (args.mainClass() == null && !args.isPython() && !args.isR()) {
         try {
            URI uri = new URI((String)scala.Option..MODULE$.apply(localPrimaryResource).getOrElse(() -> args.primaryResource()));
            FileSystem fs = FileSystem.get(uri, hadoopConf);
            org.apache.spark.util.Utils$.MODULE$.tryWithResource(() -> new JarInputStream(fs.open(new Path(uri))), (jar) -> {
               $anonfun$prepareSubmitEnvironment$36(args, jar);
               return BoxedUnit.UNIT;
            });
         } catch (Throwable var279) {
            this.error("Failed to get main class in JAR with error '" + var279.getMessage() + "'.  Please specify one with --class.");
            BoxedUnit var294 = BoxedUnit.UNIT;
         }

         if (args.mainClass() == null) {
            this.error("No main class set in JAR; please specify one with --class.");
         }
      }

      if (args.isPython() && deployMode == SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$CLIENT()) {
         label489: {
            label488: {
               String var295 = args.primaryResource();
               String var58 = SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$PYSPARK_SHELL();
               if (var295 == null) {
                  if (var58 == null) {
                     break label488;
                  }
               } else if (var295.equals(var58)) {
                  break label488;
               }

               args.mainClass_$eq("org.apache.spark.deploy.PythonRunner");
               args.childArgs_$eq((ArrayBuffer)((IterableOps)scala.collection.mutable.ArrayBuffer..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{localPrimaryResource, localPyFiles})))).$plus$plus(args.childArgs()));
               break label489;
            }

            args.mainClass_$eq("org.apache.spark.api.python.PythonGatewayServer");
         }
      }

      if (deployMode == SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$CLIENT() && clusterManager != SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$YARN()) {
         args.files_$eq(DependencyUtils$.MODULE$.mergeFileLists(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{args.files(), args.pyFiles()}))));
      }

      if (localPyFiles != null) {
         sparkConf.set((ConfigEntry)org.apache.spark.internal.config.package$.MODULE$.SUBMIT_PYTHON_FILES(), (Object)org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(localPyFiles.split(",")).toImmutableArraySeq());
      } else {
         BoxedUnit var296 = BoxedUnit.UNIT;
      }

      if (args.isR() && clusterManager == SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$YARN()) {
         Option sparkRPackagePath = RUtils$.MODULE$.localSparkRPackagePath();
         if (sparkRPackagePath.isEmpty()) {
            this.error("SPARK_HOME does not exist for R application in YARN mode.");
         }

         File sparkRPackageFile = new File((String)sparkRPackagePath.get(), SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$SPARKR_PACKAGE_ARCHIVE());
         if (!sparkRPackageFile.exists()) {
            this.error(SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$SPARKR_PACKAGE_ARCHIVE() + " does not exist for R application in YARN mode.");
         }

         String sparkRPackageURI = org.apache.spark.util.Utils$.MODULE$.resolveURI(sparkRPackageFile.getAbsolutePath()).toString();
         args.archives_$eq(DependencyUtils$.MODULE$.mergeFileLists(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{args.archives(), sparkRPackageURI + "#sparkr"}))));
         if (!RUtils$.MODULE$.rPackages().isEmpty()) {
            File rPackageFile = RPackageUtils$.MODULE$.zipRLibraries(new File((String)RUtils$.MODULE$.rPackages().get()), SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$R_PACKAGE_ARCHIVE());
            if (!rPackageFile.exists()) {
               this.error("Failed to zip all the built R packages.");
            }

            String rPackageURI = org.apache.spark.util.Utils$.MODULE$.resolveURI(rPackageFile.getAbsolutePath()).toString();
            args.archives_$eq(DependencyUtils$.MODULE$.mergeFileLists(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{args.archives(), rPackageURI + "#rpkg"}))));
         }
      }

      if (args.isR() && clusterManager == SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$STANDALONE() && !RUtils$.MODULE$.rPackages().isEmpty()) {
         this.error("Distributing R packages with standalone cluster is not supported.");
      }

      if (args.isR() && deployMode == SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$CLIENT()) {
         label473: {
            label472: {
               String var297 = args.primaryResource();
               String var64 = SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$SPARKR_SHELL();
               if (var297 == null) {
                  if (var64 == null) {
                     break label472;
                  }
               } else if (var297.equals(var64)) {
                  break label472;
               }

               args.mainClass_$eq("org.apache.spark.deploy.RRunner");
               args.childArgs_$eq((ArrayBuffer)((IterableOps)scala.collection.mutable.ArrayBuffer..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{localPrimaryResource})))).$plus$plus(args.childArgs()));
               args.files_$eq(DependencyUtils$.MODULE$.mergeFileLists(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{args.files(), args.primaryResource()}))));
               break label473;
            }

            args.mainClass_$eq("org.apache.spark.api.r.RBackend");
         }
      }

      if (isYarnCluster && args.isR()) {
         args.files_$eq(DependencyUtils$.MODULE$.mergeFileLists(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{args.files(), args.primaryResource()}))));
      }

      scala.sys.package..MODULE$.props().update("SPARK_SUBMIT", "true");
      List var298 = scala.package..MODULE$.List();
      ScalaRunTime var10001 = scala.runtime.ScalaRunTime..MODULE$;
      OptionAssigner[] var10002 = new OptionAssigner[34];
      String x$4 = args.maybeRemote().isEmpty() ? args.master() : (String)args.maybeMaster().orNull(scala..less.colon.less..MODULE$.refl());
      int x$5 = SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$ALL_CLUSTER_MGRS();
      int x$6 = SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$ALL_DEPLOY_MODES();
      String x$7 = "spark.master";
      String x$8 = OptionAssigner$.MODULE$.apply$default$4();
      Option x$9 = OptionAssigner$.MODULE$.apply$default$6();
      var10002[0] = new OptionAssigner(x$4, x$5, x$6, x$8, "spark.master", x$9);
      String x$10 = (String)args.maybeRemote().orNull(scala..less.colon.less..MODULE$.refl());
      int x$11 = SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$ALL_CLUSTER_MGRS();
      int x$12 = SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$ALL_DEPLOY_MODES();
      String x$13 = "spark.remote";
      String x$14 = OptionAssigner$.MODULE$.apply$default$4();
      Option x$15 = OptionAssigner$.MODULE$.apply$default$6();
      var10002[1] = new OptionAssigner(x$10, x$11, x$12, x$14, "spark.remote", x$15);
      String x$16 = args.deployMode();
      int x$17 = SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$ALL_CLUSTER_MGRS();
      int x$18 = SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$ALL_DEPLOY_MODES();
      String x$19 = org.apache.spark.internal.config.package$.MODULE$.SUBMIT_DEPLOY_MODE().key();
      String x$20 = OptionAssigner$.MODULE$.apply$default$4();
      Option x$21 = OptionAssigner$.MODULE$.apply$default$6();
      var10002[2] = new OptionAssigner(x$16, x$17, x$18, x$20, x$19, x$21);
      String x$22 = args.name();
      int x$23 = SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$ALL_CLUSTER_MGRS();
      int x$24 = SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$ALL_DEPLOY_MODES();
      String x$25 = "spark.app.name";
      String x$26 = OptionAssigner$.MODULE$.apply$default$4();
      Option x$27 = OptionAssigner$.MODULE$.apply$default$6();
      var10002[3] = new OptionAssigner(x$22, x$23, x$24, x$26, "spark.app.name", x$27);
      String x$28 = args.ivyRepoPath();
      int x$29 = SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$ALL_CLUSTER_MGRS();
      int x$30 = SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$CLIENT();
      String x$31 = org.apache.spark.internal.config.package$.MODULE$.JAR_IVY_REPO_PATH().key();
      String x$32 = OptionAssigner$.MODULE$.apply$default$4();
      Option x$33 = OptionAssigner$.MODULE$.apply$default$6();
      var10002[4] = new OptionAssigner(x$28, x$29, x$30, x$32, x$31, x$33);
      String x$34 = args.driverMemory();
      int x$35 = SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$ALL_CLUSTER_MGRS();
      int x$36 = SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$CLIENT();
      String x$37 = org.apache.spark.internal.config.package$.MODULE$.DRIVER_MEMORY().key();
      String x$38 = OptionAssigner$.MODULE$.apply$default$4();
      Option x$39 = OptionAssigner$.MODULE$.apply$default$6();
      var10002[5] = new OptionAssigner(x$34, x$35, x$36, x$38, x$37, x$39);
      String x$40 = args.driverExtraClassPath();
      int x$41 = SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$ALL_CLUSTER_MGRS();
      int x$42 = SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$ALL_DEPLOY_MODES();
      String x$43 = org.apache.spark.internal.config.package$.MODULE$.DRIVER_CLASS_PATH().key();
      String x$44 = OptionAssigner$.MODULE$.apply$default$4();
      Option x$45 = OptionAssigner$.MODULE$.apply$default$6();
      var10002[6] = new OptionAssigner(x$40, x$41, x$42, x$44, x$43, x$45);
      String x$46 = args.driverExtraJavaOptions();
      int x$47 = SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$ALL_CLUSTER_MGRS();
      int x$48 = SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$ALL_DEPLOY_MODES();
      String x$49 = org.apache.spark.internal.config.package$.MODULE$.DRIVER_JAVA_OPTIONS().key();
      String x$50 = OptionAssigner$.MODULE$.apply$default$4();
      Option x$51 = OptionAssigner$.MODULE$.apply$default$6();
      var10002[7] = new OptionAssigner(x$46, x$47, x$48, x$50, x$49, x$51);
      String x$52 = args.driverExtraLibraryPath();
      int x$53 = SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$ALL_CLUSTER_MGRS();
      int x$54 = SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$ALL_DEPLOY_MODES();
      String x$55 = org.apache.spark.internal.config.package$.MODULE$.DRIVER_LIBRARY_PATH().key();
      String x$56 = OptionAssigner$.MODULE$.apply$default$4();
      Option x$57 = OptionAssigner$.MODULE$.apply$default$6();
      var10002[8] = new OptionAssigner(x$52, x$53, x$54, x$56, x$55, x$57);
      String x$58 = args.principal();
      int x$59 = SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$ALL_CLUSTER_MGRS();
      int x$60 = SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$ALL_DEPLOY_MODES();
      String x$61 = org.apache.spark.internal.config.package$.MODULE$.PRINCIPAL().key();
      String x$62 = OptionAssigner$.MODULE$.apply$default$4();
      Option x$63 = OptionAssigner$.MODULE$.apply$default$6();
      var10002[9] = new OptionAssigner(x$58, x$59, x$60, x$62, x$61, x$63);
      String x$64 = args.keytab();
      int x$65 = SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$ALL_CLUSTER_MGRS();
      int x$66 = SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$ALL_DEPLOY_MODES();
      String x$67 = org.apache.spark.internal.config.package$.MODULE$.KEYTAB().key();
      String x$68 = OptionAssigner$.MODULE$.apply$default$4();
      Option x$69 = OptionAssigner$.MODULE$.apply$default$6();
      var10002[10] = new OptionAssigner(x$64, x$65, x$66, x$68, x$67, x$69);
      String x$70 = args.pyFiles();
      int x$71 = SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$ALL_CLUSTER_MGRS();
      int x$72 = SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$CLUSTER();
      String x$73 = org.apache.spark.internal.config.package$.MODULE$.SUBMIT_PYTHON_FILES().key();
      String x$74 = OptionAssigner$.MODULE$.apply$default$4();
      Option x$75 = OptionAssigner$.MODULE$.apply$default$6();
      var10002[11] = new OptionAssigner(x$70, x$71, x$72, x$74, x$73, x$75);
      String x$76 = args.packages();
      int x$77 = SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$STANDALONE() | SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$KUBERNETES();
      int x$78 = SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$CLUSTER();
      String x$79 = org.apache.spark.internal.config.package$.MODULE$.JAR_PACKAGES().key();
      String x$80 = OptionAssigner$.MODULE$.apply$default$4();
      Option x$81 = OptionAssigner$.MODULE$.apply$default$6();
      var10002[12] = new OptionAssigner(x$76, x$77, x$78, x$80, x$79, x$81);
      String x$82 = args.repositories();
      int x$83 = SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$STANDALONE() | SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$KUBERNETES();
      int x$84 = SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$CLUSTER();
      String x$85 = org.apache.spark.internal.config.package$.MODULE$.JAR_REPOSITORIES().key();
      String x$86 = OptionAssigner$.MODULE$.apply$default$4();
      Option x$87 = OptionAssigner$.MODULE$.apply$default$6();
      var10002[13] = new OptionAssigner(x$82, x$83, x$84, x$86, x$85, x$87);
      String x$88 = args.ivyRepoPath();
      int x$89 = SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$STANDALONE() | SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$KUBERNETES();
      int x$90 = SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$CLUSTER();
      String x$91 = org.apache.spark.internal.config.package$.MODULE$.JAR_IVY_REPO_PATH().key();
      String x$92 = OptionAssigner$.MODULE$.apply$default$4();
      Option x$93 = OptionAssigner$.MODULE$.apply$default$6();
      var10002[14] = new OptionAssigner(x$88, x$89, x$90, x$92, x$91, x$93);
      String x$94 = args.packagesExclusions();
      int x$95 = SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$STANDALONE() | SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$KUBERNETES();
      int x$96 = SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$CLUSTER();
      String x$97 = org.apache.spark.internal.config.package$.MODULE$.JAR_PACKAGES_EXCLUSIONS().key();
      String x$98 = OptionAssigner$.MODULE$.apply$default$4();
      Option x$99 = OptionAssigner$.MODULE$.apply$default$6();
      var10002[15] = new OptionAssigner(x$94, x$95, x$96, x$98, x$97, x$99);
      String x$100 = args.queue();
      int x$101 = SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$YARN();
      int x$102 = SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$ALL_DEPLOY_MODES();
      String x$103 = "spark.yarn.queue";
      String x$104 = OptionAssigner$.MODULE$.apply$default$4();
      Option x$105 = OptionAssigner$.MODULE$.apply$default$6();
      var10002[16] = new OptionAssigner(x$100, x$101, x$102, x$104, "spark.yarn.queue", x$105);
      String x$106 = args.pyFiles();
      int x$107 = SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$YARN();
      int x$108 = SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$ALL_DEPLOY_MODES();
      String x$109 = "spark.yarn.dist.pyFiles";
      Some x$110 = new Some((Function2)(x$16x, x$17x) -> DependencyUtils$.MODULE$.mergeFileLists(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{x$16x, x$17x}))));
      String x$111 = OptionAssigner$.MODULE$.apply$default$4();
      var10002[17] = new OptionAssigner(x$106, x$107, x$108, x$111, "spark.yarn.dist.pyFiles", x$110);
      String x$112 = args.jars();
      int x$113 = SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$YARN();
      int x$114 = SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$ALL_DEPLOY_MODES();
      String x$115 = "spark.yarn.dist.jars";
      Some x$116 = new Some((Function2)(x$18x, x$19x) -> DependencyUtils$.MODULE$.mergeFileLists(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{x$18x, x$19x}))));
      String x$117 = OptionAssigner$.MODULE$.apply$default$4();
      var10002[18] = new OptionAssigner(x$112, x$113, x$114, x$117, "spark.yarn.dist.jars", x$116);
      String x$118 = args.files();
      int x$119 = SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$YARN();
      int x$120 = SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$ALL_DEPLOY_MODES();
      String x$121 = "spark.yarn.dist.files";
      Some x$122 = new Some((Function2)(x$20x, x$21x) -> DependencyUtils$.MODULE$.mergeFileLists(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{x$20x, x$21x}))));
      String x$123 = OptionAssigner$.MODULE$.apply$default$4();
      var10002[19] = new OptionAssigner(x$118, x$119, x$120, x$123, "spark.yarn.dist.files", x$122);
      String x$124 = args.archives();
      int x$125 = SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$YARN();
      int x$126 = SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$ALL_DEPLOY_MODES();
      String x$127 = "spark.yarn.dist.archives";
      Some x$128 = new Some((Function2)(x$22x, x$23x) -> DependencyUtils$.MODULE$.mergeFileLists(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{x$22x, x$23x}))));
      String x$129 = OptionAssigner$.MODULE$.apply$default$4();
      var10002[20] = new OptionAssigner(x$124, x$125, x$126, x$129, "spark.yarn.dist.archives", x$128);
      String x$130 = args.numExecutors();
      int x$131 = SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$YARN() | SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$KUBERNETES();
      int x$132 = SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$ALL_DEPLOY_MODES();
      String x$133 = org.apache.spark.internal.config.package$.MODULE$.EXECUTOR_INSTANCES().key();
      String x$134 = OptionAssigner$.MODULE$.apply$default$4();
      Option x$135 = OptionAssigner$.MODULE$.apply$default$6();
      var10002[21] = new OptionAssigner(x$130, x$131, x$132, x$134, x$133, x$135);
      String x$136 = args.executorCores();
      int x$137 = SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$STANDALONE() | SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$YARN() | SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$KUBERNETES();
      int x$138 = SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$ALL_DEPLOY_MODES();
      String x$139 = org.apache.spark.internal.config.package$.MODULE$.EXECUTOR_CORES().key();
      String x$140 = OptionAssigner$.MODULE$.apply$default$4();
      Option x$141 = OptionAssigner$.MODULE$.apply$default$6();
      var10002[22] = new OptionAssigner(x$136, x$137, x$138, x$140, x$139, x$141);
      String x$142 = args.executorMemory();
      int x$143 = SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$STANDALONE() | SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$YARN() | SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$KUBERNETES();
      int x$144 = SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$ALL_DEPLOY_MODES();
      String x$145 = org.apache.spark.internal.config.package$.MODULE$.EXECUTOR_MEMORY().key();
      String x$146 = OptionAssigner$.MODULE$.apply$default$4();
      Option x$147 = OptionAssigner$.MODULE$.apply$default$6();
      var10002[23] = new OptionAssigner(x$142, x$143, x$144, x$146, x$145, x$147);
      String x$148 = args.totalExecutorCores();
      int x$149 = SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$STANDALONE();
      int x$150 = SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$ALL_DEPLOY_MODES();
      String x$151 = org.apache.spark.internal.config.package$.MODULE$.CORES_MAX().key();
      String x$152 = OptionAssigner$.MODULE$.apply$default$4();
      Option x$153 = OptionAssigner$.MODULE$.apply$default$6();
      var10002[24] = new OptionAssigner(x$148, x$149, x$150, x$152, x$151, x$153);
      String x$154 = args.files();
      int x$155 = SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$LOCAL() | SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$STANDALONE() | SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$KUBERNETES();
      int x$156 = SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$ALL_DEPLOY_MODES();
      String x$157 = org.apache.spark.internal.config.package$.MODULE$.FILES().key();
      String x$158 = OptionAssigner$.MODULE$.apply$default$4();
      Option x$159 = OptionAssigner$.MODULE$.apply$default$6();
      var10002[25] = new OptionAssigner(x$154, x$155, x$156, x$158, x$157, x$159);
      String x$160 = args.archives();
      int x$161 = SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$LOCAL() | SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$STANDALONE() | SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$KUBERNETES();
      int x$162 = SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$ALL_DEPLOY_MODES();
      String x$163 = org.apache.spark.internal.config.package$.MODULE$.ARCHIVES().key();
      String x$164 = OptionAssigner$.MODULE$.apply$default$4();
      Option x$165 = OptionAssigner$.MODULE$.apply$default$6();
      var10002[26] = new OptionAssigner(x$160, x$161, x$162, x$164, x$163, x$165);
      String x$166 = args.jars();
      int x$167 = SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$LOCAL();
      int x$168 = SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$CLIENT();
      String x$169 = org.apache.spark.internal.config.package$.MODULE$.JARS().key();
      String x$170 = OptionAssigner$.MODULE$.apply$default$4();
      Option x$171 = OptionAssigner$.MODULE$.apply$default$6();
      var10002[27] = new OptionAssigner(x$166, x$167, x$168, x$170, x$169, x$171);
      String x$172 = args.jars();
      int x$173 = SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$STANDALONE() | SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$KUBERNETES();
      int x$174 = SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$ALL_DEPLOY_MODES();
      String x$175 = org.apache.spark.internal.config.package$.MODULE$.JARS().key();
      String x$176 = OptionAssigner$.MODULE$.apply$default$4();
      Option x$177 = OptionAssigner$.MODULE$.apply$default$6();
      var10002[28] = new OptionAssigner(x$172, x$173, x$174, x$176, x$175, x$177);
      String x$178 = args.driverMemory();
      int x$179 = SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$STANDALONE() | SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$YARN() | SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$KUBERNETES();
      int x$180 = SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$CLUSTER();
      String x$181 = org.apache.spark.internal.config.package$.MODULE$.DRIVER_MEMORY().key();
      String x$182 = OptionAssigner$.MODULE$.apply$default$4();
      Option x$183 = OptionAssigner$.MODULE$.apply$default$6();
      var10002[29] = new OptionAssigner(x$178, x$179, x$180, x$182, x$181, x$183);
      String x$184 = args.driverCores();
      int x$185 = SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$STANDALONE() | SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$YARN() | SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$KUBERNETES();
      int x$186 = SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$CLUSTER();
      String x$187 = org.apache.spark.internal.config.package$.MODULE$.DRIVER_CORES().key();
      String x$188 = OptionAssigner$.MODULE$.apply$default$4();
      Option x$189 = OptionAssigner$.MODULE$.apply$default$6();
      var10002[30] = new OptionAssigner(x$184, x$185, x$186, x$188, x$187, x$189);
      String x$190 = Boolean.toString(args.supervise());
      int x$191 = SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$STANDALONE();
      int x$192 = SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$CLUSTER();
      String x$193 = org.apache.spark.internal.config.package$.MODULE$.DRIVER_SUPERVISE().key();
      String x$194 = OptionAssigner$.MODULE$.apply$default$4();
      Option x$195 = OptionAssigner$.MODULE$.apply$default$6();
      var10002[31] = new OptionAssigner(x$190, x$191, x$192, x$194, x$193, x$195);
      String x$196 = args.ivyRepoPath();
      int x$197 = SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$STANDALONE();
      int x$198 = SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$CLUSTER();
      String x$199 = org.apache.spark.internal.config.package$.MODULE$.JAR_IVY_REPO_PATH().key();
      String x$200 = OptionAssigner$.MODULE$.apply$default$4();
      Option x$201 = OptionAssigner$.MODULE$.apply$default$6();
      var10002[32] = new OptionAssigner(x$196, x$197, x$198, x$200, x$199, x$201);
      int x$203 = SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$ALL_CLUSTER_MGRS();
      int x$204 = SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$CLIENT();
      String x$205 = "spark.repl.local.jars";
      String x$206 = OptionAssigner$.MODULE$.apply$default$4();
      Option x$207 = OptionAssigner$.MODULE$.apply$default$6();
      var10002[33] = new OptionAssigner(localJars, x$203, x$204, x$206, "spark.repl.local.jars", x$207);
      List options = (List)var298.apply(var10001.wrapRefArray(var10002));
      if (deployMode == SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$CLIENT()) {
         childMainClass = args.mainClass();
         if (localPrimaryResource != null && SparkSubmit$.MODULE$.isUserJar(localPrimaryResource)) {
            childClasspath.$plus$eq(localPrimaryResource);
         } else {
            BoxedUnit var299 = BoxedUnit.UNIT;
         }

         if (localJars != null) {
            childClasspath.$plus$plus$eq(scala.Predef..MODULE$.wrapRefArray((Object[])localJars.split(",")));
         } else {
            BoxedUnit var300 = BoxedUnit.UNIT;
         }
      } else {
         BoxedUnit var301 = BoxedUnit.UNIT;
      }

      if (isYarnCluster) {
         if (SparkSubmit$.MODULE$.isUserJar(args.primaryResource())) {
            childClasspath.$plus$eq(args.primaryResource());
         } else {
            BoxedUnit var302 = BoxedUnit.UNIT;
         }

         if (args.jars() != null) {
            childClasspath.$plus$plus$eq(scala.Predef..MODULE$.wrapRefArray((Object[])args.jars().split(",")));
         } else {
            BoxedUnit var303 = BoxedUnit.UNIT;
         }
      } else {
         BoxedUnit var304 = BoxedUnit.UNIT;
      }

      if (deployMode == SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$CLIENT()) {
         if (args.childArgs() != null) {
            childArgs.$plus$plus$eq(args.childArgs());
         } else {
            BoxedUnit var305 = BoxedUnit.UNIT;
         }
      } else {
         BoxedUnit var306 = BoxedUnit.UNIT;
      }

      options.foreach((opt) -> {
         if (opt.value() != null && (deployMode & opt.deployMode()) != 0 && (clusterManager & opt.clusterManager()) != 0) {
            if (opt.clOption() != null) {
               childArgs.$plus$eq(opt.clOption()).$plus$eq(opt.value());
            } else {
               BoxedUnit var10000 = BoxedUnit.UNIT;
            }

            if (opt.confKey() == null) {
               return BoxedUnit.UNIT;
            } else {
               label50: {
                  String var6 = opt.confKey();
                  String var5 = "spark.remote";
                  if (var6 == null) {
                     if (var5 == null) {
                        break label50;
                     }
                  } else if (var6.equals(var5)) {
                     break label50;
                  }

                  BoxedUnit var7 = BoxedUnit.UNIT;
                  return opt.mergeFn().isDefined() && sparkConf.contains(opt.confKey()) ? sparkConf.set(opt.confKey(), (String)((Function2)opt.mergeFn().get()).apply(sparkConf.get(opt.confKey()), opt.value())) : sparkConf.set(opt.confKey(), opt.value());
               }

               System.setProperty("spark.remote", opt.value());
               return opt.mergeFn().isDefined() && sparkConf.contains(opt.confKey()) ? sparkConf.set(opt.confKey(), (String)((Function2)opt.mergeFn().get()).apply(sparkConf.get(opt.confKey()), opt.value())) : sparkConf.set(opt.confKey(), opt.value());
            }
         } else {
            return BoxedUnit.UNIT;
         }
      });
      if (SparkSubmit$.MODULE$.isShell(args.primaryResource()) && !sparkConf.contains(UI$.MODULE$.UI_SHOW_CONSOLE_PROGRESS())) {
         sparkConf.set((ConfigEntry)UI$.MODULE$.UI_SHOW_CONSOLE_PROGRESS(), (Object)BoxesRunTime.boxToBoolean(true));
      } else {
         BoxedUnit var307 = BoxedUnit.UNIT;
      }

      if (!isKubernetesClusterModeDriver && !isYarnCluster && !args.isPython() && !args.isR()) {
         Seq jars = (Seq)sparkConf.get(org.apache.spark.internal.config.package$.MODULE$.JARS());
         if (SparkSubmit$.MODULE$.isUserJar(args.primaryResource())) {
            jars = (Seq)jars.$plus$plus(new scala.collection.immutable..colon.colon(args.primaryResource(), scala.collection.immutable.Nil..MODULE$));
         }

         sparkConf.set((ConfigEntry)org.apache.spark.internal.config.package$.MODULE$.JARS(), (Object)jars);
      } else {
         BoxedUnit var308 = BoxedUnit.UNIT;
      }

      if (args.isStandaloneCluster()) {
         if (args.useRest()) {
            childMainClass = SparkSubmit$.MODULE$.REST_CLUSTER_SUBMIT_CLASS();
            childArgs.$plus$eq(args.primaryResource()).$plus$eq(args.mainClass());
         } else {
            childMainClass = SparkSubmit$.MODULE$.STANDALONE_CLUSTER_SUBMIT_CLASS();
            if (args.supervise()) {
               childArgs.$plus$eq("--supervise");
            } else {
               BoxedUnit var309 = BoxedUnit.UNIT;
            }

            scala.Option..MODULE$.apply(args.driverMemory()).foreach((m) -> (ArrayBuffer)childArgs.$plus$eq("--memory").$plus$eq(m));
            scala.Option..MODULE$.apply(args.driverCores()).foreach((c) -> (ArrayBuffer)childArgs.$plus$eq("--cores").$plus$eq(c));
            childArgs.$plus$eq("launch");
            childArgs.$plus$eq(args.master()).$plus$eq(args.primaryResource()).$plus$eq(args.mainClass());
         }

         if (args.childArgs() != null) {
            childArgs.$plus$plus$eq(args.childArgs());
         } else {
            BoxedUnit var310 = BoxedUnit.UNIT;
         }
      } else {
         BoxedUnit var311 = BoxedUnit.UNIT;
      }

      if (clusterManager == SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$YARN()) {
         if (args.isPython()) {
            sparkConf.set("spark.yarn.isPython", "true");
         } else {
            BoxedUnit var312 = BoxedUnit.UNIT;
         }
      } else {
         BoxedUnit var313 = BoxedUnit.UNIT;
      }

      if (clusterManager == SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$KUBERNETES() && UserGroupInformation.isSecurityEnabled()) {
         this.setRMPrincipal(sparkConf);
      }

      if (isYarnCluster) {
         childMainClass = SparkSubmit$.MODULE$.YARN_CLUSTER_SUBMIT_CLASS();
         if (args.isPython()) {
            childArgs.$plus$eq("--primary-py-file").$plus$eq(args.primaryResource());
            childArgs.$plus$eq("--class").$plus$eq("org.apache.spark.deploy.PythonRunner");
         } else if (args.isR()) {
            String mainFile = (new Path(args.primaryResource())).getName();
            childArgs.$plus$eq("--primary-r-file").$plus$eq(mainFile);
            childArgs.$plus$eq("--class").$plus$eq("org.apache.spark.deploy.RRunner");
         } else {
            label435: {
               label434: {
                  String var314 = args.primaryResource();
                  String var272 = "spark-internal";
                  if (var314 == null) {
                     if (var272 != null) {
                        break label434;
                     }
                  } else if (!var314.equals(var272)) {
                     break label434;
                  }

                  BoxedUnit var315 = BoxedUnit.UNIT;
                  break label435;
               }

               childArgs.$plus$eq("--jar").$plus$eq(args.primaryResource());
            }

            childArgs.$plus$eq("--class").$plus$eq(args.mainClass());
         }

         if (args.childArgs() != null) {
            args.childArgs().foreach((arg) -> (ArrayBuffer)childArgs.$plus$eq("--arg").$plus$eq(arg));
         }
      }

      if (isKubernetesCluster) {
         label423: {
            label422: {
               childMainClass = SparkSubmit$.MODULE$.KUBERNETES_CLUSTER_SUBMIT_CLASS();
               String var316 = args.primaryResource();
               String var273 = "spark-internal";
               if (var316 == null) {
                  if (var273 == null) {
                     break label422;
                  }
               } else if (var316.equals(var273)) {
                  break label422;
               }

               if (args.isPython()) {
                  childArgs.$plus$plus$eq(scala.Predef..MODULE$.wrapRefArray((Object[])(new String[]{"--primary-py-file", args.primaryResource()})));
                  childArgs.$plus$plus$eq(scala.Predef..MODULE$.wrapRefArray((Object[])(new String[]{"--main-class", "org.apache.spark.deploy.PythonRunner"})));
               } else if (args.isR()) {
                  childArgs.$plus$plus$eq(scala.Predef..MODULE$.wrapRefArray((Object[])(new String[]{"--primary-r-file", args.primaryResource()})));
                  childArgs.$plus$plus$eq(scala.Predef..MODULE$.wrapRefArray((Object[])(new String[]{"--main-class", "org.apache.spark.deploy.RRunner"})));
               } else {
                  childArgs.$plus$plus$eq(scala.Predef..MODULE$.wrapRefArray((Object[])(new String[]{"--primary-java-resource", args.primaryResource()})));
                  childArgs.$plus$plus$eq(scala.Predef..MODULE$.wrapRefArray((Object[])(new String[]{"--main-class", args.mainClass()})));
               }
               break label423;
            }

            childArgs.$plus$plus$eq(scala.Predef..MODULE$.wrapRefArray((Object[])(new String[]{"--main-class", args.mainClass()})));
         }

         if (args.childArgs() != null) {
            args.childArgs().foreach((arg) -> (ArrayBuffer)childArgs.$plus$eq("--arg").$plus$eq(arg));
         }

         if (args.proxyUser() != null) {
            childArgs.$plus$eq("--proxy-user").$plus$eq(args.proxyUser());
         } else {
            BoxedUnit var317 = BoxedUnit.UNIT;
         }
      } else {
         BoxedUnit var318 = BoxedUnit.UNIT;
      }

      args.sparkProperties().withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$prepareSubmitEnvironment$46(check$ifrefutable$1))).foreach((x$24x) -> {
         if (x$24x != null) {
            String k = (String)x$24x._1();
            String v = (String)x$24x._2();
            return sparkConf.setIfMissing(k, v);
         } else {
            throw new MatchError(x$24x);
         }
      });
      if (deployMode == SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$CLUSTER()) {
         sparkConf.remove(org.apache.spark.internal.config.package$.MODULE$.DRIVER_HOST_ADDRESS());
      } else {
         BoxedUnit var319 = BoxedUnit.UNIT;
      }

      Seq pathConfigs = new scala.collection.immutable..colon.colon(org.apache.spark.internal.config.package$.MODULE$.JARS().key(), new scala.collection.immutable..colon.colon(org.apache.spark.internal.config.package$.MODULE$.FILES().key(), new scala.collection.immutable..colon.colon(org.apache.spark.internal.config.package$.MODULE$.ARCHIVES().key(), new scala.collection.immutable..colon.colon("spark.yarn.dist.files", new scala.collection.immutable..colon.colon("spark.yarn.dist.archives", new scala.collection.immutable..colon.colon("spark.yarn.dist.jars", scala.collection.immutable.Nil..MODULE$))))));
      pathConfigs.foreach((config) -> {
         $anonfun$prepareSubmitEnvironment$48(sparkConf, config);
         return BoxedUnit.UNIT;
      });
      Seq pyFiles = (Seq)sparkConf.get(org.apache.spark.internal.config.package$.MODULE$.SUBMIT_PYTHON_FILES());
      String resolvedPyFiles = org.apache.spark.util.Utils$.MODULE$.resolveURIs(pyFiles.mkString(","));
      String formattedPyFiles = deployMode != SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$CLUSTER() ? scala.Predef..MODULE$.wrapRefArray((Object[])PythonRunner$.MODULE$.formatPaths(resolvedPyFiles, PythonRunner$.MODULE$.formatPaths$default$2())).mkString(",") : resolvedPyFiles;
      sparkConf.set((ConfigEntry)org.apache.spark.internal.config.package$.MODULE$.SUBMIT_PYTHON_FILES(), (Object)org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(formattedPyFiles.split(",")).toImmutableArraySeq());
      if (args.verbose() && SparkSubmit$.MODULE$.isSqlShell(childMainClass)) {
         childArgs.$plus$plus$eq(new scala.collection.immutable..colon.colon("--verbose", scala.collection.immutable.Nil..MODULE$));
      } else {
         BoxedUnit var320 = BoxedUnit.UNIT;
      }

      boolean setSubmitTimeInClusterModeDriver = sparkConf.getBoolean("spark.kubernetes.setSubmitTimeInDriver", true);
      if (sparkConf.contains("spark.app.submitTime") && (!isKubernetesClusterModeDriver || !setSubmitTimeInClusterModeDriver)) {
         BoxedUnit var321 = BoxedUnit.UNIT;
      } else {
         sparkConf.set("spark.app.submitTime", Long.toString(System.currentTimeMillis()));
      }

      if (childClasspath.nonEmpty() && isCustomClasspathInClusterModeDisallowed) {
         childClasspath.clear();
         this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Ignore classpath "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CLASS_PATH..MODULE$, childClasspath.mkString(", "))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"with proxy user specified in Cluster mode when "})))).log(scala.collection.immutable.Nil..MODULE$)).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " is "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CONFIG..MODULE$, org.apache.spark.internal.config.package$.MODULE$.ALLOW_CUSTOM_CLASSPATH_BY_PROXY_USER_IN_CLUSTER_MODE().key())})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"disabled"})))).log(scala.collection.immutable.Nil..MODULE$))));
      }

      return new Tuple4(childArgs.toSeq(), childClasspath.toSeq(), sparkConf, childMainClass);
   }

   public Option prepareSubmitEnvironment$default$2() {
      return scala.None..MODULE$;
   }

   private void setRMPrincipal(final SparkConf sparkConf) {
      String shortUserName = UserGroupInformation.getCurrentUser().getShortUserName();
      String key = "spark.hadoop.yarn.resourcemanager.principal";
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Setting ", " to ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.KEY..MODULE$, key), new MDC(org.apache.spark.internal.LogKeys.SHORT_USER_NAME..MODULE$, shortUserName)})))));
      sparkConf.set(key, shortUserName);
   }

   private MutableURLClassLoader getSubmitClassLoader(final SparkConf sparkConf) {
      MutableURLClassLoader loader = (MutableURLClassLoader)(BoxesRunTime.unboxToBoolean(sparkConf.get(org.apache.spark.internal.config.package$.MODULE$.DRIVER_USER_CLASS_PATH_FIRST())) ? new ChildFirstURLClassLoader(new URL[0], Thread.currentThread().getContextClassLoader()) : new MutableURLClassLoader(new URL[0], Thread.currentThread().getContextClassLoader()));
      Thread.currentThread().setContextClassLoader(loader);
      return loader;
   }

   public void org$apache$spark$deploy$SparkSubmit$$runMain(final SparkSubmitArguments args, final boolean uninitLog) {
      Tuple4 var5 = this.prepareSubmitEnvironment(args, this.prepareSubmitEnvironment$default$2());
      if (var5 == null) {
         throw new MatchError(var5);
      } else {
         Seq childArgs = (Seq)var5._1();
         Seq childClasspath = (Seq)var5._2();
         SparkConf sparkConf = (SparkConf)var5._3();
         String childMainClass = (String)var5._4();
         Tuple4 var4 = new Tuple4(childArgs, childClasspath, sparkConf, childMainClass);
         Seq childArgs = (Seq)var4._1();
         Seq childClasspath = (Seq)var4._2();
         SparkConf sparkConf = (SparkConf)var4._3();
         String childMainClass = (String)var4._4();
         if (uninitLog) {
            .MODULE$.uninitialize();
         }

         if (args.verbose()) {
            this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Main class:\\n", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CLASS_NAME..MODULE$, childMainClass)})))));
            this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Arguments:\\n", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.ARGS..MODULE$, childArgs.mkString("\n"))})))));
            this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Spark config:\\n"})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CONFIG..MODULE$, ((IterableOnceOps)org.apache.spark.util.Utils$.MODULE$.redact(scala.Predef..MODULE$.wrapRefArray((Object[])sparkConf.getAll()).toMap(scala..less.colon.less..MODULE$.refl())).sorted(scala.math.Ordering..MODULE$.Tuple2(scala.math.Ordering.String..MODULE$, scala.math.Ordering.String..MODULE$))).mkString("\n"))}))))));
            this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Classpath elements:\\n", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CLASS_PATHS..MODULE$, childClasspath.mkString("\n"))})))));
            this.logInfo((Function0)(() -> "\n"));
         }

         boolean var35;
         Predef var10000;
         label198: {
            label197: {
               var10000 = scala.Predef..MODULE$;
               String var10001 = args.deployMode();
               String var14 = "cluster";
               if (var10001 == null) {
                  if (var14 != null) {
                     break label197;
                  }
               } else if (!var10001.equals(var14)) {
                  break label197;
               }

               if (args.proxyUser() != null && childClasspath.nonEmpty() && !BoxesRunTime.unboxToBoolean(sparkConf.get(org.apache.spark.internal.config.package$.MODULE$.ALLOW_CUSTOM_CLASSPATH_BY_PROXY_USER_IN_CLUSTER_MODE()))) {
                  var35 = false;
                  break label198;
               }
            }

            var35 = true;
         }

         var10000.assert(var35, () -> "Classpath of spark-submit should not change in cluster mode if proxy user is specified when " + org.apache.spark.internal.config.package$.MODULE$.ALLOW_CUSTOM_CLASSPATH_BY_PROXY_USER_IN_CLUSTER_MODE().key() + " is disabled");
         MutableURLClassLoader loader = this.getSubmitClassLoader(sparkConf);
         childClasspath.foreach((jar) -> {
            $anonfun$runMain$7(loader, jar);
            return BoxedUnit.UNIT;
         });
         Class mainClass = null;

         try {
            mainClass = org.apache.spark.util.Utils$.MODULE$.classForName(childMainClass, org.apache.spark.util.Utils$.MODULE$.classForName$default$2(), org.apache.spark.util.Utils$.MODULE$.classForName$default$3());
         } catch (ClassNotFoundException var32) {
            this.logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Failed to load class ", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CLASS_NAME..MODULE$, childMainClass)})))));
            if (childMainClass.contains("thriftserver")) {
               this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Failed to load main class ", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CLASS_NAME..MODULE$, childMainClass)})))));
               this.logInfo((Function0)(() -> "You need to build Spark with -Phive and -Phive-thriftserver."));
            } else if (childMainClass.contains("org.apache.spark.sql.connect")) {
               this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Failed to load main class ", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CLASS_NAME..MODULE$, childMainClass)})))));
               this.logInfo((Function0)(() -> "You need to specify Spark Connect jars with --jars or --packages."));
            }

            throw new SparkUserAppException(SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$CLASS_NOT_FOUND_EXIT_STATUS());
         } catch (NoClassDefFoundError var33) {
            this.logError((LogEntry)org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Failed to load ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CLASS_NAME..MODULE$, childMainClass)})))), var33);
            if (var33.getMessage().contains("org/apache/hadoop/hive")) {
               this.logInfo((Function0)(() -> "Failed to load hive class."));
               this.logInfo((Function0)(() -> "You need to build Spark with -Phive and -Phive-thriftserver."));
            }

            throw new SparkUserAppException(SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$CLASS_NOT_FOUND_EXIT_STATUS());
         }

         SparkApplication app = (SparkApplication)(SparkApplication.class.isAssignableFrom(mainClass) ? (SparkApplication)mainClass.getConstructor().newInstance() : new JavaMainApplication(mainClass));

         try {
            app.start((String[])childArgs.toArray(scala.reflect.ClassTag..MODULE$.apply(String.class)), sparkConf);
         } catch (Throwable var30) {
            throw this.findCause$1(var30);
         } finally {
            if (args.master().startsWith("k8s") && !SparkSubmit$.MODULE$.isShell(args.primaryResource()) && !SparkSubmit$.MODULE$.isSqlShell(args.mainClass()) && !SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$isThriftServer(args.mainClass()) && !SparkSubmit$.MODULE$.org$apache$spark$deploy$SparkSubmit$$isConnectServer(args.mainClass())) {
               try {
                  SparkContext$.MODULE$.getActive().foreach((x$26) -> {
                     $anonfun$runMain$16(x$26);
                     return BoxedUnit.UNIT;
                  });
               } catch (Throwable var29) {
                  this.logError((Function0)(() -> "Failed to close SparkContext"), var29);
               }
            }

         }

      }
   }

   private void error(final String msg) {
      throw new SparkException(msg);
   }

   private final void doRunMain$1(final SparkSubmitArguments args$2, final SparkConf sparkConf$1, final boolean uninitLog$1) {
      if (args$2.proxyUser() == null) {
         this.org$apache$spark$deploy$SparkSubmit$$runMain(args$2, uninitLog$1);
      } else {
         boolean isKubernetesClusterModeDriver = args$2.master().startsWith("k8s") && "client".equals(args$2.deployMode()) && sparkConf$1.getBoolean("spark.kubernetes.submitInDriver", false);
         if (isKubernetesClusterModeDriver) {
            this.logInfo((Function0)(() -> "Running driver with proxy user. Cluster manager: Kubernetes"));
            SparkHadoopUtil$.MODULE$.get().runAsSparkUser((JFunction0.mcV.sp)() -> this.org$apache$spark$deploy$SparkSubmit$$runMain(args$2, uninitLog$1));
         } else {
            UserGroupInformation proxyUser = UserGroupInformation.createProxyUser(args$2.proxyUser(), UserGroupInformation.getCurrentUser());

            try {
               proxyUser.doAs(new PrivilegedExceptionAction(args$2, uninitLog$1) {
                  // $FF: synthetic field
                  private final SparkSubmit $outer;
                  private final SparkSubmitArguments args$2;
                  private final boolean uninitLog$1;

                  public void run() {
                     this.$outer.org$apache$spark$deploy$SparkSubmit$$runMain(this.args$2, this.uninitLog$1);
                  }

                  public {
                     if (SparkSubmit.this == null) {
                        throw null;
                     } else {
                        this.$outer = SparkSubmit.this;
                        this.args$2 = args$2;
                        this.uninitLog$1 = uninitLog$1;
                     }
                  }
               });
            } catch (Exception var10) {
               if (var10.getStackTrace().length != 0) {
                  throw var10;
               }

               String var10001 = var10.getClass().getName();
               this.error("ERROR: " + var10001 + ": " + var10.getMessage());
            } finally {
               FileSystem.closeAllForUGI(proxyUser);
            }

         }
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$prepareSubmitEnvironment$1(final MutableURLClassLoader loader$1, final String jar) {
      DependencyUtils$.MODULE$.addJarToClasspath(jar, loader$1);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$prepareSubmitEnvironment$12(final Function1 avoidDownload$1, final URI uri) {
      return BoxesRunTime.unboxToBoolean(avoidDownload$1.apply(uri.getScheme()));
   }

   private final String downloadResourcesToCurrentDirectory$1(final String uris, final boolean isArchive, final Function1 avoidDownload, final File targetDir$1, final SparkConf sparkConf$2, final Configuration hadoopConf$1, final String workingDirectory$1) {
      Seq resolvedUris = (Seq)org.apache.spark.util.Utils$.MODULE$.stringToSeq(uris).map((path) -> org.apache.spark.util.Utils$.MODULE$.resolveURI(path));
      Tuple2 var11 = resolvedUris.partition((uri) -> BoxesRunTime.boxToBoolean($anonfun$prepareSubmitEnvironment$12(avoidDownload, uri)));
      if (var11 != null) {
         Seq avoidDownloads = (Seq)var11._1();
         Seq toDownloads = (Seq)var11._2();
         Tuple2 var10 = new Tuple2(avoidDownloads, toDownloads);
         Seq avoidDownloads = (Seq)var10._1();
         Seq toDownloads = (Seq)var10._2();
         String localResources = DependencyUtils$.MODULE$.downloadFileList(((IterableOnceOps)toDownloads.map((x$10) -> org.apache.spark.util.Utils$.MODULE$.getUriBuilder(x$10).fragment((String)null).build(new Object[0]).toString())).mkString(","), targetDir$1, sparkConf$2, hadoopConf$1);
         return ((IterableOnceOps)((IterableOps)((IterableOps)((IterableOps)org.apache.spark.util.Utils$.MODULE$.stringToSeq(localResources).map((path) -> org.apache.spark.util.Utils$.MODULE$.resolveURI(path))).zip(toDownloads)).map((x0$1) -> {
            if (x0$1 != null) {
               URI localResources = (URI)x0$1._1();
               URI resolvedUri = (URI)x0$1._2();
               File source = (new File(localResources.getPath())).getCanonicalFile();
               File dest = (new File(workingDirectory$1, resolvedUri.getFragment() != null ? resolvedUri.getFragment() : source.getName())).getCanonicalFile();
               this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Files ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.URI..MODULE$, resolvedUri)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" from ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SOURCE_PATH..MODULE$, source)})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" to ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.DESTINATION_PATH..MODULE$, dest)}))))));
               org.apache.spark.util.Utils$.MODULE$.deleteRecursively(dest);
               if (isArchive) {
                  org.apache.spark.util.Utils$.MODULE$.unpack(source, dest);
                  BoxedUnit var10000 = BoxedUnit.UNIT;
               } else {
                  Files.copy(source.toPath(), dest.toPath());
               }

               return org.apache.spark.util.Utils$.MODULE$.getUriBuilder(localResources).fragment(resolvedUri.getFragment()).build(new Object[0]).toString();
            } else {
               throw new MatchError(x0$1);
            }
         })).$plus$plus((IterableOnce)avoidDownloads.map((x$11) -> x$11.toString()))).mkString(",");
      } else {
         throw new MatchError(var11);
      }
   }

   private static final boolean downloadResourcesToCurrentDirectory$default$2$1() {
      return false;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$prepareSubmitEnvironment$18(final String x$8) {
      return false;
   }

   private static final Function1 downloadResourcesToCurrentDirectory$default$3$1() {
      return (x$8) -> BoxesRunTime.boxToBoolean($anonfun$prepareSubmitEnvironment$18(x$8));
   }

   private static final boolean avoidJarDownload$1(final String scheme, final Seq avoidJarDownloadSchemes$1) {
      return avoidJarDownloadSchemes$1.contains("*") || avoidJarDownloadSchemes$1.contains(scheme);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$prepareSubmitEnvironment$21(final Seq avoidJarDownloadSchemes$1, final String scheme) {
      return avoidJarDownload$1(scheme, avoidJarDownloadSchemes$1);
   }

   private static final boolean shouldDownload$1(final String scheme, final Seq forceDownloadSchemes$1, final Configuration hadoopConf$1) {
      return forceDownloadSchemes$1.contains("*") || forceDownloadSchemes$1.contains(scheme) || scala.util.Try..MODULE$.apply(() -> FileSystem.getFileSystemClass(scheme, hadoopConf$1)).isFailure();
   }

   private static final String downloadResource$1(final String resource, final File targetDir$1, final SparkConf sparkConf$2, final Configuration hadoopConf$1, final Seq forceDownloadSchemes$1) {
      URI uri = org.apache.spark.util.Utils$.MODULE$.resolveURI(resource);
      String var7 = uri.getScheme();
      switch (var7 == null ? 0 : var7.hashCode()) {
         case 3143036:
            if ("file".equals(var7)) {
               return resource;
            }
            break;
         case 103145323:
            if ("local".equals(var7)) {
               return resource;
            }
      }

      if (shouldDownload$1(var7, forceDownloadSchemes$1, hadoopConf$1)) {
         File file = new File(targetDir$1, (new Path(uri)).getName());
         if (file.exists()) {
            return file.toURI().toString();
         } else {
            return DependencyUtils$.MODULE$.downloadFile(resource, targetDir$1, sparkConf$2, hadoopConf$1);
         }
      } else {
         return uri.toString();
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$prepareSubmitEnvironment$36(final SparkSubmitArguments args$3, final JarInputStream jar) {
      args$3.mainClass_$eq(jar.getManifest().getMainAttributes().getValue("Main-Class"));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$prepareSubmitEnvironment$46(final Tuple2 check$ifrefutable$1) {
      return check$ifrefutable$1 != null;
   }

   // $FF: synthetic method
   public static final void $anonfun$prepareSubmitEnvironment$48(final SparkConf sparkConf$2, final String config) {
      sparkConf$2.getOption(config).foreach((oldValue) -> sparkConf$2.set(config, org.apache.spark.util.Utils$.MODULE$.resolveURIs(oldValue)));
   }

   // $FF: synthetic method
   public static final void $anonfun$runMain$7(final MutableURLClassLoader loader$2, final String jar) {
      DependencyUtils$.MODULE$.addJarToClasspath(jar, loader$2);
   }

   private final Throwable findCause$1(final Throwable t) {
      while(true) {
         if (t instanceof UndeclaredThrowableException var5) {
            if (var5.getCause() == null) {
               return var5;
            }

            t = var5.getCause();
         } else {
            if (t instanceof InvocationTargetException var6) {
               if (var6.getCause() != null) {
                  t = var6.getCause();
                  continue;
               }

               return var6;
            }

            if (t != null) {
               return t;
            }

            throw new MatchError(t);
         }
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$runMain$16(final SparkContext x$26) {
      x$26.stop();
   }

   public SparkSubmit() {
      Logging.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
