package org.apache.spark.executor;

import java.lang.invoke.SerializedLambda;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.None.;
import scala.collection.immutable.Set;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.util.matching.Regex;

@ScalaSignature(
   bytes = "\u0006\u0005I4Q!\u0004\b\u0001!YA\u0001b\t\u0001\u0003\u0002\u0003\u0006I!\n\u0005\u0006g\u0001!\t\u0001\u000e\u0005\bq\u0001\u0011\r\u0011\"\u0003:\u0011\u00191\u0005\u0001)A\u0005u!)q\t\u0001C\u0001\u0011\")\u0001\u000b\u0001C\u0005#\")a\u000b\u0001C\u0005/\u001e1AM\u0004E\u0001!\u00154a!\u0004\b\t\u0002A1\u0007\"B\u001a\n\t\u00039\u0007b\u00025\n\u0005\u0004%\t!\u001b\u0005\u0007c&\u0001\u000b\u0011\u00026\u0003+\u0015CXmY;u_JdunZ+sY\"\u000bg\u000e\u001a7fe*\u0011q\u0002E\u0001\tKb,7-\u001e;pe*\u0011\u0011CE\u0001\u0006gB\f'o\u001b\u0006\u0003'Q\ta!\u00199bG\",'\"A\u000b\u0002\u0007=\u0014xmE\u0002\u0001/u\u0001\"\u0001G\u000e\u000e\u0003eQ\u0011AG\u0001\u0006g\u000e\fG.Y\u0005\u00039e\u0011a!\u00118z%\u00164\u0007C\u0001\u0010\"\u001b\u0005y\"B\u0001\u0011\u0011\u0003!Ig\u000e^3s]\u0006d\u0017B\u0001\u0012 \u0005\u001daunZ4j]\u001e\fQ\u0002\\8h+Jd\u0007+\u0019;uKJt7\u0001\u0001\t\u00041\u0019B\u0013BA\u0014\u001a\u0005\u0019y\u0005\u000f^5p]B\u0011\u0011\u0006\r\b\u0003U9\u0002\"aK\r\u000e\u00031R!!\f\u0013\u0002\rq\u0012xn\u001c;?\u0013\ty\u0013$\u0001\u0004Qe\u0016$WMZ\u0005\u0003cI\u0012aa\u0015;sS:<'BA\u0018\u001a\u0003\u0019a\u0014N\\5u}Q\u0011Qg\u000e\t\u0003m\u0001i\u0011A\u0004\u0005\u0006G\t\u0001\r!J\u0001\u001dS:4wN]7fI\u001a{'/T5tg&tw-\u0011;ue&\u0014W\u000f^3t+\u0005Q\u0004CA\u001eE\u001b\u0005a$BA\u001f?\u0003\u0019\tGo\\7jG*\u0011q\bQ\u0001\u000bG>t7-\u001e:sK:$(BA!C\u0003\u0011)H/\u001b7\u000b\u0003\r\u000bAA[1wC&\u0011Q\t\u0010\u0002\u000e\u0003R|W.[2C_>dW-\u00198\u0002;%tgm\u001c:nK\u00124uN]'jgNLgnZ!uiJL'-\u001e;fg\u0002\nA\"\u00199qYf\u0004\u0016\r\u001e;fe:$2!\u0013'O!\u0011I#\n\u000b\u0015\n\u0005-\u0013$aA'ba\")Q*\u0002a\u0001\u0013\u00069An\\4Ve2\u001c\b\"B(\u0006\u0001\u0004I\u0015AC1uiJL'-\u001e;fg\u0006qAm\\!qa2L\b+\u0019;uKJtG\u0003B%S'RCQ!\u0014\u0004A\u0002%CQa\u0014\u0004A\u0002%CQ!\u0016\u0004A\u0002!\n!\"\u001e:m!\u0006$H/\u001a:o\u0003Uawn\u001a$bS2$vNU3oK^dunZ+sYN$B\u0001W.^EB\u0011\u0001$W\u0005\u00035f\u0011A!\u00168ji\")Al\u0002a\u0001Q\u00051!/Z1t_:DQAX\u0004A\u0002}\u000b1\"\u00197m!\u0006$H/\u001a:ogB\u0019\u0011\u0006\u0019\u0015\n\u0005\u0005\u0014$aA*fi\")1m\u0002a\u0001?\u0006i\u0011\r\u001c7BiR\u0014\u0018NY;uKN\fQ#\u0012=fGV$xN\u001d'pOV\u0013H\u000eS1oI2,'\u000f\u0005\u00027\u0013M\u0011\u0011b\u0006\u000b\u0002K\u0006A2)V*U\u001f6{VK\u0015'`!\u0006#F+\u0012*O?J+u)\u0012-\u0016\u0003)\u0004\"a[8\u000e\u00031T!!\u001c8\u0002\u00115\fGo\u00195j]\u001eT!!Q\r\n\u0005Ad'!\u0002*fO\u0016D\u0018!G\"V'R{UjX+S\u0019~\u0003\u0016\t\u0016+F%:{&+R$F1\u0002\u0002"
)
public class ExecutorLogUrlHandler implements Logging {
   private final Option logUrlPattern;
   private final AtomicBoolean informedForMissingAttributes;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static Regex CUSTOM_URL_PATTERN_REGEX() {
      return ExecutorLogUrlHandler$.MODULE$.CUSTOM_URL_PATTERN_REGEX();
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

   private AtomicBoolean informedForMissingAttributes() {
      return this.informedForMissingAttributes;
   }

   public scala.collection.immutable.Map applyPattern(final scala.collection.immutable.Map logUrls, final scala.collection.immutable.Map attributes) {
      Option var4 = this.logUrlPattern;
      if (var4 instanceof Some var5) {
         String pattern = (String)var5.value();
         return this.doApplyPattern(logUrls, attributes, pattern);
      } else if (.MODULE$.equals(var4)) {
         return logUrls;
      } else {
         throw new MatchError(var4);
      }
   }

   private scala.collection.immutable.Map doApplyPattern(final scala.collection.immutable.Map logUrls, final scala.collection.immutable.Map attributes, final String urlPattern) {
      Set allPatterns = ExecutorLogUrlHandler$.MODULE$.CUSTOM_URL_PATTERN_REGEX().findAllMatchIn(urlPattern).map((x$1) -> x$1.group(1)).toSet();
      Set allPatternsExceptFileName = (Set)allPatterns.filter((x$2) -> BoxesRunTime.boxToBoolean($anonfun$doApplyPattern$2(x$2)));
      Set allAttributeKeys = attributes.keySet();
      Set allAttributeKeysExceptLogFiles = (Set)allAttributeKeys.filter((x$3) -> BoxesRunTime.boxToBoolean($anonfun$doApplyPattern$3(x$3)));
      if (allPatternsExceptFileName.diff(allAttributeKeysExceptLogFiles).nonEmpty()) {
         this.logFailToRenewLogUrls("some of required attributes are missing in app's event log.", allPatternsExceptFileName, allAttributeKeys);
         return logUrls;
      } else if (allPatterns.contains("FILE_NAME") && !allAttributeKeys.contains("LOG_FILES")) {
         this.logFailToRenewLogUrls("'FILE_NAME' parameter is provided, but file information is missing in app's event log.", allPatternsExceptFileName, allAttributeKeys);
         return logUrls;
      } else {
         String updatedUrl = (String)allPatternsExceptFileName.foldLeft(urlPattern, (x0$1, x1$1) -> {
            Tuple2 var4 = new Tuple2(x0$1, x1$1);
            if (var4 != null) {
               String orig = (String)var4._1();
               String patt = (String)var4._2();
               return orig.replace("{{" + patt + "}}", (CharSequence)attributes.apply(patt));
            } else {
               throw new MatchError(var4);
            }
         });
         return allPatterns.contains("FILE_NAME") ? scala.Predef..MODULE$.wrapRefArray(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])((String)attributes.apply("LOG_FILES")).split(",")), (file) -> scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(file), updatedUrl.replace("{{FILE_NAME}}", file)), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class))).toMap(scala..less.colon.less..MODULE$.refl()) : (scala.collection.immutable.Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("log"), updatedUrl)})));
      }
   }

   private void logFailToRenewLogUrls(final String reason, final Set allPatterns, final Set allAttributes) {
      if (this.informedForMissingAttributes().compareAndSet(false, true)) {
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Fail to renew executor log urls: ", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.REASON..MODULE$, reason)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" Required: ", " / "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.REGEX..MODULE$, allPatterns)})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"available: ", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.ATTRIBUTE_MAP..MODULE$, allAttributes)})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" Falling back to show app's original log urls."})))).log(scala.collection.immutable.Nil..MODULE$))));
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$doApplyPattern$2(final String x$2) {
      boolean var10000;
      label23: {
         String var1 = "FILE_NAME";
         if (x$2 == null) {
            if (var1 != null) {
               break label23;
            }
         } else if (!x$2.equals(var1)) {
            break label23;
         }

         var10000 = false;
         return var10000;
      }

      var10000 = true;
      return var10000;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$doApplyPattern$3(final String x$3) {
      boolean var10000;
      label23: {
         String var1 = "LOG_FILES";
         if (x$3 == null) {
            if (var1 != null) {
               break label23;
            }
         } else if (!x$3.equals(var1)) {
            break label23;
         }

         var10000 = false;
         return var10000;
      }

      var10000 = true;
      return var10000;
   }

   public ExecutorLogUrlHandler(final Option logUrlPattern) {
      this.logUrlPattern = logUrlPattern;
      Logging.$init$(this);
      this.informedForMissingAttributes = new AtomicBoolean(false);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
