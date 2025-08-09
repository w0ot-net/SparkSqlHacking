package org.apache.spark.ui;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.Response;
import java.lang.invoke.SerializedLambda;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import org.apache.spark.package$;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.ui.scope.RDDOperationGraph$;
import org.glassfish.jersey.internal.util.collection.MultivaluedStringMap;
import org.slf4j.Logger;
import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.Iterable;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.StringOps;
import scala.collection.StringOps.;
import scala.collection.immutable.LazyList;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.NonLocalReturnControl;
import scala.util.matching.Regex;
import scala.xml.Elem;
import scala.xml.MetaData;
import scala.xml.Node;
import scala.xml.NodeBuffer;
import scala.xml.NodeSeq;
import scala.xml.Null;
import scala.xml.Text;
import scala.xml.TopScope;
import scala.xml.UnprefixedAttribute;
import scala.xml.transform.RewriteRule;
import scala.xml.transform.RuleTransformer;

public final class UIUtils$ implements Logging {
   public static final UIUtils$ MODULE$ = new UIUtils$();
   private static final String TABLE_CLASS_NOT_STRIPED;
   private static final String TABLE_CLASS_STRIPED;
   private static final String TABLE_CLASS_STRIPED_SORTABLE;
   private static final DateTimeFormatter dateTimeFormatter;
   private static final DateTimeFormatter batchTimeFormat;
   private static final DateTimeFormatter batchTimeFormatWithMilliseconds;
   private static final Regex ERROR_CLASS_REGEX;
   private static transient Logger org$apache$spark$internal$Logging$$log_;

   static {
      Logging.$init$(MODULE$);
      TABLE_CLASS_NOT_STRIPED = "table table-bordered table-sm";
      TABLE_CLASS_STRIPED = MODULE$.TABLE_CLASS_NOT_STRIPED() + " table-striped";
      TABLE_CLASS_STRIPED_SORTABLE = MODULE$.TABLE_CLASS_STRIPED() + " sortable";
      dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss", Locale.US).withZone(ZoneId.systemDefault());
      batchTimeFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss", Locale.US).withZone(ZoneId.systemDefault());
      batchTimeFormatWithMilliseconds = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss.SSS", Locale.US).withZone(ZoneId.systemDefault());
      ERROR_CLASS_REGEX = .MODULE$.r$extension(scala.Predef..MODULE$.augmentString("\\[(?<errorClass>[A-Z][A-Z_.]+[A-Z])]"));
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

   public String TABLE_CLASS_NOT_STRIPED() {
      return TABLE_CLASS_NOT_STRIPED;
   }

   public String TABLE_CLASS_STRIPED() {
      return TABLE_CLASS_STRIPED;
   }

   public String TABLE_CLASS_STRIPED_SORTABLE() {
      return TABLE_CLASS_STRIPED_SORTABLE;
   }

   private DateTimeFormatter dateTimeFormatter() {
      return dateTimeFormatter;
   }

   public String formatDate(final Date date) {
      return this.dateTimeFormatter().format(date.toInstant());
   }

   public String formatDate(final long timestamp) {
      return this.dateTimeFormatter().format(Instant.ofEpochMilli(timestamp));
   }

   public String formatDuration(final long milliseconds) {
      if (milliseconds < 100L) {
         return .MODULE$.format$extension(scala.Predef..MODULE$.augmentString("%d ms"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToLong(milliseconds)}));
      } else {
         double seconds = (double)milliseconds / (double)1000;
         if (seconds < (double)1) {
            return .MODULE$.format$extension(scala.Predef..MODULE$.augmentString("%.1f s"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToDouble(seconds)}));
         } else if (seconds < (double)60) {
            return .MODULE$.format$extension(scala.Predef..MODULE$.augmentString("%.0f s"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToDouble(seconds)}));
         } else {
            double minutes = seconds / (double)60;
            if (minutes < (double)10) {
               return .MODULE$.format$extension(scala.Predef..MODULE$.augmentString("%.1f min"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToDouble(minutes)}));
            } else if (minutes < (double)60) {
               return .MODULE$.format$extension(scala.Predef..MODULE$.augmentString("%.0f min"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToDouble(minutes)}));
            } else {
               double hours = minutes / (double)60;
               return .MODULE$.format$extension(scala.Predef..MODULE$.augmentString("%.1f h"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToDouble(hours)}));
            }
         }
      }
   }

   public String formatDurationVerbose(final long ms) {
      Object var3 = new Object();

      String var10000;
      try {
         try {
            long second = 1000L;
            long minute = 60L * second;
            long hour = 60L * minute;
            long day = 24L * hour;
            long week = 7L * day;
            long year = 365L * day;
            String millisecondsString = ms >= second && ms % second == 0L ? "" : ms % second + " ms";
            String secondString = toString$1(ms % minute / second, "second");
            String minuteString = toString$1(ms % hour / minute, "minute");
            String hourString = toString$1(ms % day / hour, "hour");
            String dayString = toString$1(ms % week / day, "day");
            String weekString = toString$1(ms % year / week, "week");
            String yearString = toString$1(ms / year, "year");
            (new scala.collection.immutable..colon.colon(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(BoxesRunTime.boxToLong(second)), millisecondsString), new scala.collection.immutable..colon.colon(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(BoxesRunTime.boxToLong(minute)), secondString + " " + millisecondsString), new scala.collection.immutable..colon.colon(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(BoxesRunTime.boxToLong(hour)), minuteString + " " + secondString), new scala.collection.immutable..colon.colon(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(BoxesRunTime.boxToLong(day)), hourString + " " + minuteString + " " + secondString), new scala.collection.immutable..colon.colon(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(BoxesRunTime.boxToLong(week)), dayString + " " + hourString + " " + minuteString), new scala.collection.immutable..colon.colon(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(BoxesRunTime.boxToLong(year)), weekString + " " + dayString + " " + hourString), scala.collection.immutable.Nil..MODULE$))))))).foreach((x0$1) -> {
               $anonfun$formatDurationVerbose$1(ms, var3, x0$1);
               return BoxedUnit.UNIT;
            });
            var10000 = yearString + " " + weekString + " " + dayString;
         } catch (Exception var25) {
            this.logError((Function0)(() -> "Error converting time to string"), var25);
            var10000 = "";
         }
      } catch (NonLocalReturnControl var26) {
         if (var26.key() != var3) {
            throw var26;
         }

         var10000 = (String)var26.value();
      }

      return var10000;
   }

   private DateTimeFormatter batchTimeFormat() {
      return batchTimeFormat;
   }

   private DateTimeFormatter batchTimeFormatWithMilliseconds() {
      return batchTimeFormatWithMilliseconds;
   }

   public String formatBatchTime(final long batchTime, final long batchInterval, final boolean showYYYYMMSS, final TimeZone timezone) {
      DateTimeFormatter format = batchInterval < 1000L ? this.batchTimeFormatWithMilliseconds() : this.batchTimeFormat();
      DateTimeFormatter formatWithZone = timezone == null ? format : format.withZone(timezone.toZoneId());
      String formattedBatchTime = formatWithZone.format(Instant.ofEpochMilli(batchTime));
      return showYYYYMMSS ? formattedBatchTime : formattedBatchTime.substring(formattedBatchTime.indexOf(32) + 1);
   }

   public boolean formatBatchTime$default$3() {
      return true;
   }

   public TimeZone formatBatchTime$default$4() {
      return null;
   }

   public String formatNumber(final double records) {
      double trillion = 1.0E12;
      double billion = (double)1.0E9F;
      double million = (double)1000000.0F;
      double thousand = (double)1000.0F;
      Tuple2 var13 = records >= (double)2 * trillion ? new Tuple2(BoxesRunTime.boxToDouble(records / trillion), " T") : (records >= (double)2 * billion ? new Tuple2(BoxesRunTime.boxToDouble(records / billion), " B") : (records >= (double)2 * million ? new Tuple2(BoxesRunTime.boxToDouble(records / million), " M") : (records >= (double)2 * thousand ? new Tuple2(BoxesRunTime.boxToDouble(records / thousand), " K") : new Tuple2(BoxesRunTime.boxToDouble(records), ""))));
      if (var13 != null) {
         double value = var13._1$mcD$sp();
         String unit = (String)var13._2();
         Tuple2 var12 = new Tuple2(BoxesRunTime.boxToDouble(value), unit);
         double value = var12._1$mcD$sp();
         String unit = (String)var12._2();
         return unit.isEmpty() ? .MODULE$.formatLocal$extension(scala.Predef..MODULE$.augmentString("%d"), Locale.US, scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger((int)value)})) : .MODULE$.formatLocal$extension(scala.Predef..MODULE$.augmentString("%.1f%s"), Locale.US, scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToDouble(value), unit}));
      } else {
         throw new MatchError(var13);
      }
   }

   public String uiRoot(final HttpServletRequest request) {
      Option knoxBasePath = scala.Option..MODULE$.apply(request.getHeader("X-Forwarded-Context"));
      return (String)scala.sys.package..MODULE$.props().get("spark.ui.proxyBase").orElse(() -> scala.sys.package..MODULE$.env().get("APPLICATION_WEB_PROXY_BASE")).orElse(() -> knoxBasePath).getOrElse(() -> "");
   }

   public String prependBaseUri(final HttpServletRequest request, final String basePath, final String resource) {
      return this.uiRoot(request) + basePath + resource;
   }

   public String prependBaseUri$default$2() {
      return "";
   }

   public String prependBaseUri$default$3() {
      return "";
   }

   public Seq commonHeaderNodes(final HttpServletRequest request) {
      NodeBuffer $buf = new NodeBuffer();
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var19 = new UnprefixedAttribute("content", new Text("text/html; charset=utf-8"), $md);
      var19 = new UnprefixedAttribute("http-equiv", new Text("Content-type"), var19);
      $buf.$amp$plus(new Elem((String)null, "meta", var19, scala.xml.TopScope..MODULE$, true, scala.collection.immutable.Nil..MODULE$));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var21 = new UnprefixedAttribute("content", new Text("width=device-width, initial-scale=1"), $md);
      var21 = new UnprefixedAttribute("name", new Text("viewport"), var21);
      $buf.$amp$plus(new Elem((String)null, "meta", var21, scala.xml.TopScope..MODULE$, true, scala.collection.immutable.Nil..MODULE$));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var23 = new UnprefixedAttribute("type", new Text("text/css"), $md);
      var23 = new UnprefixedAttribute("href", this.prependBaseUri(request, "/static/bootstrap.min.css", this.prependBaseUri$default$3()), var23);
      var23 = new UnprefixedAttribute("rel", new Text("stylesheet"), var23);
      $buf.$amp$plus(new Elem((String)null, "link", var23, scala.xml.TopScope..MODULE$, true, scala.collection.immutable.Nil..MODULE$));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var26 = new UnprefixedAttribute("type", new Text("text/css"), $md);
      var26 = new UnprefixedAttribute("href", this.prependBaseUri(request, "/static/vis-timeline-graph2d.min.css", this.prependBaseUri$default$3()), var26);
      var26 = new UnprefixedAttribute("rel", new Text("stylesheet"), var26);
      $buf.$amp$plus(new Elem((String)null, "link", var26, scala.xml.TopScope..MODULE$, true, scala.collection.immutable.Nil..MODULE$));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var29 = new UnprefixedAttribute("type", new Text("text/css"), $md);
      var29 = new UnprefixedAttribute("href", this.prependBaseUri(request, "/static/webui.css", this.prependBaseUri$default$3()), var29);
      var29 = new UnprefixedAttribute("rel", new Text("stylesheet"), var29);
      $buf.$amp$plus(new Elem((String)null, "link", var29, scala.xml.TopScope..MODULE$, true, scala.collection.immutable.Nil..MODULE$));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var32 = new UnprefixedAttribute("type", new Text("text/css"), $md);
      var32 = new UnprefixedAttribute("href", this.prependBaseUri(request, "/static/timeline-view.css", this.prependBaseUri$default$3()), var32);
      var32 = new UnprefixedAttribute("rel", new Text("stylesheet"), var32);
      $buf.$amp$plus(new Elem((String)null, "link", var32, scala.xml.TopScope..MODULE$, true, scala.collection.immutable.Nil..MODULE$));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var35 = new UnprefixedAttribute("src", this.prependBaseUri(request, "/static/sorttable.js", this.prependBaseUri$default$3()), $md);
      $buf.$amp$plus(new Elem((String)null, "script", var35, scala.xml.TopScope..MODULE$, false, scala.collection.immutable.Nil..MODULE$));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var36 = new UnprefixedAttribute("src", this.prependBaseUri(request, "/static/jquery-3.5.1.min.js", this.prependBaseUri$default$3()), $md);
      $buf.$amp$plus(new Elem((String)null, "script", var36, scala.xml.TopScope..MODULE$, false, scala.collection.immutable.Nil..MODULE$));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var37 = new UnprefixedAttribute("src", this.prependBaseUri(request, "/static/vis-timeline-graph2d.min.js", this.prependBaseUri$default$3()), $md);
      $buf.$amp$plus(new Elem((String)null, "script", var37, scala.xml.TopScope..MODULE$, false, scala.collection.immutable.Nil..MODULE$));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var38 = new UnprefixedAttribute("src", this.prependBaseUri(request, "/static/bootstrap.bundle.min.js", this.prependBaseUri$default$3()), $md);
      $buf.$amp$plus(new Elem((String)null, "script", var38, scala.xml.TopScope..MODULE$, false, scala.collection.immutable.Nil..MODULE$));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var39 = new UnprefixedAttribute("src", this.prependBaseUri(request, "/static/initialize-tooltips.js", this.prependBaseUri$default$3()), $md);
      $buf.$amp$plus(new Elem((String)null, "script", var39, scala.xml.TopScope..MODULE$, false, scala.collection.immutable.Nil..MODULE$));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var40 = new UnprefixedAttribute("src", this.prependBaseUri(request, "/static/table.js", this.prependBaseUri$default$3()), $md);
      $buf.$amp$plus(new Elem((String)null, "script", var40, scala.xml.TopScope..MODULE$, false, scala.collection.immutable.Nil..MODULE$));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var41 = new UnprefixedAttribute("src", this.prependBaseUri(request, "/static/timeline-view.js", this.prependBaseUri$default$3()), $md);
      $buf.$amp$plus(new Elem((String)null, "script", var41, scala.xml.TopScope..MODULE$, false, scala.collection.immutable.Nil..MODULE$));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var42 = new UnprefixedAttribute("src", this.prependBaseUri(request, "/static/log-view.js", this.prependBaseUri$default$3()), $md);
      $buf.$amp$plus(new Elem((String)null, "script", var42, scala.xml.TopScope..MODULE$, false, scala.collection.immutable.Nil..MODULE$));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var43 = new UnprefixedAttribute("src", this.prependBaseUri(request, "/static/webui.js", this.prependBaseUri$default$3()), $md);
      $buf.$amp$plus(new Elem((String)null, "script", var43, scala.xml.TopScope..MODULE$, false, scala.collection.immutable.Nil..MODULE$));
      Null var10005 = scala.xml.Null..MODULE$;
      TopScope var10006 = scala.xml.TopScope..MODULE$;
      NodeSeq var10008 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("setUIRoot('"));
      $buf.$amp$plus(this.uiRoot(request));
      $buf.$amp$plus(new Text("')"));
      $buf.$amp$plus(new Elem((String)null, "script", var10005, var10006, false, var10008.seqToNodeSeq($buf)));
      return scala.xml.NodeSeq..MODULE$.seqToNodeSeq($buf);
   }

   public Seq vizHeaderNodes(final HttpServletRequest request) {
      NodeBuffer $buf = new NodeBuffer();
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var8 = new UnprefixedAttribute("type", new Text("text/css"), $md);
      var8 = new UnprefixedAttribute("href", this.prependBaseUri(request, "/static/spark-dag-viz.css", this.prependBaseUri$default$3()), var8);
      var8 = new UnprefixedAttribute("rel", new Text("stylesheet"), var8);
      $buf.$amp$plus(new Elem((String)null, "link", var8, scala.xml.TopScope..MODULE$, true, scala.collection.immutable.Nil..MODULE$));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var11 = new UnprefixedAttribute("src", this.prependBaseUri(request, "/static/d3.min.js", this.prependBaseUri$default$3()), $md);
      $buf.$amp$plus(new Elem((String)null, "script", var11, scala.xml.TopScope..MODULE$, false, scala.collection.immutable.Nil..MODULE$));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var12 = new UnprefixedAttribute("src", this.prependBaseUri(request, "/static/dagre-d3.min.js", this.prependBaseUri$default$3()), $md);
      $buf.$amp$plus(new Elem((String)null, "script", var12, scala.xml.TopScope..MODULE$, false, scala.collection.immutable.Nil..MODULE$));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var13 = new UnprefixedAttribute("src", this.prependBaseUri(request, "/static/graphlib-dot.min.js", this.prependBaseUri$default$3()), $md);
      $buf.$amp$plus(new Elem((String)null, "script", var13, scala.xml.TopScope..MODULE$, false, scala.collection.immutable.Nil..MODULE$));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var14 = new UnprefixedAttribute("src", this.prependBaseUri(request, "/static/spark-dag-viz.js", this.prependBaseUri$default$3()), $md);
      $buf.$amp$plus(new Elem((String)null, "script", var14, scala.xml.TopScope..MODULE$, false, scala.collection.immutable.Nil..MODULE$));
      return scala.xml.NodeSeq..MODULE$.seqToNodeSeq($buf);
   }

   public Seq dataTablesHeaderNodes(final HttpServletRequest request) {
      NodeBuffer $buf = new NodeBuffer();
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var11 = new UnprefixedAttribute("type", new Text("text/css"), $md);
      var11 = new UnprefixedAttribute("href", this.prependBaseUri(request, "/static/dataTables.bootstrap4.min.css", this.prependBaseUri$default$3()), var11);
      var11 = new UnprefixedAttribute("rel", new Text("stylesheet"), var11);
      $buf.$amp$plus(new Elem((String)null, "link", var11, scala.xml.TopScope..MODULE$, true, scala.collection.immutable.Nil..MODULE$));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var14 = new UnprefixedAttribute("type", new Text("text/css"), $md);
      var14 = new UnprefixedAttribute("href", this.prependBaseUri(request, "/static/jquery.dataTables.min.css", this.prependBaseUri$default$3()), var14);
      var14 = new UnprefixedAttribute("rel", new Text("stylesheet"), var14);
      $buf.$amp$plus(new Elem((String)null, "link", var14, scala.xml.TopScope..MODULE$, true, scala.collection.immutable.Nil..MODULE$));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var17 = new UnprefixedAttribute("type", new Text("text/css"), $md);
      var17 = new UnprefixedAttribute("href", this.prependBaseUri(request, "/static/webui-dataTables.css", this.prependBaseUri$default$3()), var17);
      var17 = new UnprefixedAttribute("rel", new Text("stylesheet"), var17);
      $buf.$amp$plus(new Elem((String)null, "link", var17, scala.xml.TopScope..MODULE$, true, scala.collection.immutable.Nil..MODULE$));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var20 = new UnprefixedAttribute("src", this.prependBaseUri(request, "/static/jquery.dataTables.min.js", this.prependBaseUri$default$3()), $md);
      $buf.$amp$plus(new Elem((String)null, "script", var20, scala.xml.TopScope..MODULE$, false, scala.collection.immutable.Nil..MODULE$));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var21 = new UnprefixedAttribute("src", this.prependBaseUri(request, "/static/jquery.cookies.2.2.0.min.js", this.prependBaseUri$default$3()), $md);
      $buf.$amp$plus(new Elem((String)null, "script", var21, scala.xml.TopScope..MODULE$, false, scala.collection.immutable.Nil..MODULE$));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var22 = new UnprefixedAttribute("src", this.prependBaseUri(request, "/static/jquery.blockUI.min.js", this.prependBaseUri$default$3()), $md);
      $buf.$amp$plus(new Elem((String)null, "script", var22, scala.xml.TopScope..MODULE$, false, scala.collection.immutable.Nil..MODULE$));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var23 = new UnprefixedAttribute("src", this.prependBaseUri(request, "/static/dataTables.bootstrap4.min.js", this.prependBaseUri$default$3()), $md);
      $buf.$amp$plus(new Elem((String)null, "script", var23, scala.xml.TopScope..MODULE$, false, scala.collection.immutable.Nil..MODULE$));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var24 = new UnprefixedAttribute("src", this.prependBaseUri(request, "/static/jquery.mustache.js", this.prependBaseUri$default$3()), $md);
      $buf.$amp$plus(new Elem((String)null, "script", var24, scala.xml.TopScope..MODULE$, false, scala.collection.immutable.Nil..MODULE$));
      return scala.xml.NodeSeq..MODULE$.seqToNodeSeq($buf);
   }

   public Seq headerSparkPage(final HttpServletRequest request, final String title, final Function0 content, final SparkUITab activeTab, final Option helpText, final boolean showVisualization, final boolean useDataTables) {
      String appName = activeTab.appName();
      String var10000;
      if (appName.length() < 36) {
         var10000 = appName;
      } else {
         StringOps var83 = .MODULE$;
         var10000 = var83.take$extension(scala.Predef..MODULE$.augmentString(appName), 32) + "...";
      }

      String shortAppName = var10000;
      Seq header = (Seq)activeTab.headerTabs().map((tab) -> {
         MetaData $md;
         UnprefixedAttribute var10000;
         String var10003;
         label17: {
            label16: {
               $md = scala.xml.Null..MODULE$;
               var10000 = new UnprefixedAttribute;
               if (tab == null) {
                  if (activeTab == null) {
                     break label16;
                  }
               } else if (tab.equals(activeTab)) {
                  break label16;
               }

               var10003 = "nav-item";
               break label17;
            }

            var10003 = "nav-item active";
         }

         var10000.<init>("class", var10003, $md);
         MetaData var8 = var10000;
         TopScope var10005 = scala.xml.TopScope..MODULE$;
         NodeSeq var10007 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n        "));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var9 = new UnprefixedAttribute("href", MODULE$.prependBaseUri(request, activeTab.basePath(), "/" + tab.prefix() + "/"), $md);
         var9 = new UnprefixedAttribute("class", new Text("nav-link"), var9);
         TopScope var10014 = scala.xml.TopScope..MODULE$;
         NodeSeq var10016 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(tab.name());
         $buf.$amp$plus(new Elem((String)null, "a", var9, var10014, false, var10016.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n      "));
         return new Elem((String)null, "li", var8, var10005, false, var10007.seqToNodeSeq($buf));
      });
      Seq helpButton = (Seq)helpText.map((x$2) -> MODULE$.tooltip(x$2, "top")).getOrElse(() -> (Seq)scala.package..MODULE$.Seq().empty());
      Null var10004 = scala.xml.Null..MODULE$;
      TopScope var10005 = scala.xml.TopScope..MODULE$;
      NodeSeq var10007 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n      "));
      Null var10013 = scala.xml.Null..MODULE$;
      TopScope var10014 = scala.xml.TopScope..MODULE$;
      NodeSeq var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n        "));
      $buf.$amp$plus(this.commonHeaderNodes(request));
      $buf.$amp$plus(new Text("\n        "));
      Null var10022 = scala.xml.Null..MODULE$;
      TopScope var10023 = scala.xml.TopScope..MODULE$;
      NodeSeq var10025 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("setAppBasePath('"));
      $buf.$amp$plus(activeTab.basePath());
      $buf.$amp$plus(new Text("')"));
      $buf.$amp$plus(new Elem((String)null, "script", var10022, var10023, false, var10025.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n        "));
      $buf.$amp$plus(showVisualization ? this.vizHeaderNodes(request) : scala.package..MODULE$.Seq().empty());
      $buf.$amp$plus(new Text("\n        "));
      $buf.$amp$plus(useDataTables ? this.dataTablesHeaderNodes(request) : scala.package..MODULE$.Seq().empty());
      $buf.$amp$plus(new Text("\n        "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var54 = new UnprefixedAttribute("href", this.prependBaseUri(request, "/static/spark-logo-77x50px-hd.png", this.prependBaseUri$default$3()), $md);
      var54 = new UnprefixedAttribute("rel", new Text("shortcut icon"), var54);
      $buf.$amp$plus(new Elem((String)null, "link", var54, scala.xml.TopScope..MODULE$, false, scala.collection.immutable.Nil..MODULE$));
      $buf.$amp$plus(new Text("\n        "));
      var10022 = scala.xml.Null..MODULE$;
      var10023 = scala.xml.TopScope..MODULE$;
      var10025 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(appName);
      $buf.$amp$plus(new Text(" - "));
      $buf.$amp$plus(title);
      $buf.$amp$plus(new Elem((String)null, "title", var10022, var10023, false, var10025.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      $buf.$amp$plus(new Elem((String)null, "head", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      var10013 = scala.xml.Null..MODULE$;
      var10014 = scala.xml.TopScope..MODULE$;
      var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n        "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var56 = new UnprefixedAttribute("class", new Text("navbar navbar-expand-md navbar-light bg-light mb-4"), $md);
      var10023 = scala.xml.TopScope..MODULE$;
      var10025 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n          "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var57 = new UnprefixedAttribute("class", new Text("navbar-header"), $md);
      TopScope var10032 = scala.xml.TopScope..MODULE$;
      NodeSeq var10034 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n            "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var58 = new UnprefixedAttribute("class", new Text("navbar-brand"), $md);
      TopScope var10041 = scala.xml.TopScope..MODULE$;
      NodeSeq var10043 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n              "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var59 = new UnprefixedAttribute("href", this.prependBaseUri(request, "/", this.prependBaseUri$default$3()), $md);
      TopScope var10050 = scala.xml.TopScope..MODULE$;
      NodeSeq var10052 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n                "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var60 = new UnprefixedAttribute("src", this.prependBaseUri(request, "/static/spark-logo-77x50px-hd.png", this.prependBaseUri$default$3()), $md);
      $buf.$amp$plus(new Elem((String)null, "img", var60, scala.xml.TopScope..MODULE$, true, scala.collection.immutable.Nil..MODULE$));
      $buf.$amp$plus(new Text("\n                "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var61 = new UnprefixedAttribute("class", new Text("version"), $md);
      TopScope var10059 = scala.xml.TopScope..MODULE$;
      NodeSeq var10061 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(activeTab.appSparkVersion());
      $buf.$amp$plus(new Elem((String)null, "span", var61, var10059, false, var10061.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n              "));
      $buf.$amp$plus(new Elem((String)null, "a", var59, var10050, false, var10052.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n            "));
      $buf.$amp$plus(new Elem((String)null, "div", var58, var10041, false, var10043.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n          "));
      $buf.$amp$plus(new Elem((String)null, "div", var57, var10032, false, var10034.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n          "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var62 = new UnprefixedAttribute("aria-label", new Text("Toggle navigation"), $md);
      var62 = new UnprefixedAttribute("aria-expanded", new Text("false"), var62);
      var62 = new UnprefixedAttribute("aria-controls", new Text("navbarCollapse"), var62);
      var62 = new UnprefixedAttribute("data-target", new Text("#navbarCollapse"), var62);
      var62 = new UnprefixedAttribute("data-toggle", new Text("collapse"), var62);
      var62 = new UnprefixedAttribute("type", new Text("button"), var62);
      var62 = new UnprefixedAttribute("class", new Text("navbar-toggler"), var62);
      var10032 = scala.xml.TopScope..MODULE$;
      var10034 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n            "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var69 = new UnprefixedAttribute("class", new Text("navbar-toggler-icon"), $md);
      $buf.$amp$plus(new Elem((String)null, "span", var69, scala.xml.TopScope..MODULE$, false, scala.collection.immutable.Nil..MODULE$));
      $buf.$amp$plus(new Text("\n          "));
      $buf.$amp$plus(new Elem((String)null, "button", var62, var10032, false, var10034.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n          "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var70 = new UnprefixedAttribute("id", new Text("navbarCollapse"), $md);
      var70 = new UnprefixedAttribute("class", new Text("collapse navbar-collapse"), var70);
      var10032 = scala.xml.TopScope..MODULE$;
      var10034 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n            "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var72 = new UnprefixedAttribute("class", new Text("navbar-nav mr-auto"), $md);
      var10041 = scala.xml.TopScope..MODULE$;
      var10043 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(header);
      $buf.$amp$plus(new Elem((String)null, "ul", var72, var10041, false, var10043.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n            "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var73 = new UnprefixedAttribute("class", new Text("navbar-text navbar-right d-none d-md-block"), $md);
      var10041 = scala.xml.TopScope..MODULE$;
      var10043 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n              "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var74 = new UnprefixedAttribute("class", new Text("text-nowrap"), $md);
      var74 = new UnprefixedAttribute("title", appName, var74);
      var10050 = scala.xml.TopScope..MODULE$;
      var10052 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(shortAppName);
      $buf.$amp$plus(new Elem((String)null, "strong", var74, var10050, false, var10052.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n              "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var76 = new UnprefixedAttribute("class", new Text("text-nowrap"), $md);
      var10050 = scala.xml.TopScope..MODULE$;
      var10052 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("application UI"));
      $buf.$amp$plus(new Elem((String)null, "span", var76, var10050, false, var10052.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n            "));
      $buf.$amp$plus(new Elem((String)null, "span", var73, var10041, false, var10043.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n          "));
      $buf.$amp$plus(new Elem((String)null, "div", var70, var10032, false, var10034.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n        "));
      $buf.$amp$plus(new Elem((String)null, "nav", var56, var10023, false, var10025.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n        "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var77 = new UnprefixedAttribute("class", new Text("container-fluid"), $md);
      var10023 = scala.xml.TopScope..MODULE$;
      var10025 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n          "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var78 = new UnprefixedAttribute("class", new Text("row"), $md);
      var10032 = scala.xml.TopScope..MODULE$;
      var10034 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n            "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var79 = new UnprefixedAttribute("class", new Text("col-12"), $md);
      var10041 = scala.xml.TopScope..MODULE$;
      var10043 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n              "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var80 = new UnprefixedAttribute("style", new Text("vertical-align: bottom; white-space: nowrap; overflow: hidden;\n                text-overflow: ellipsis;"), $md);
      var10050 = scala.xml.TopScope..MODULE$;
      var10052 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n                "));
      $buf.$amp$plus(title);
      $buf.$amp$plus(new Text("\n                "));
      $buf.$amp$plus(helpButton);
      $buf.$amp$plus(new Text("\n              "));
      $buf.$amp$plus(new Elem((String)null, "h3", var80, var10050, false, var10052.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n            "));
      $buf.$amp$plus(new Elem((String)null, "div", var79, var10041, false, var10043.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n          "));
      $buf.$amp$plus(new Elem((String)null, "div", var78, var10032, false, var10034.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n          "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var81 = new UnprefixedAttribute("class", new Text("row"), $md);
      var10032 = scala.xml.TopScope..MODULE$;
      var10034 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n            "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var82 = new UnprefixedAttribute("class", new Text("col-12"), $md);
      var10041 = scala.xml.TopScope..MODULE$;
      var10043 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n              "));
      $buf.$amp$plus(content.apply());
      $buf.$amp$plus(new Text("\n            "));
      $buf.$amp$plus(new Elem((String)null, "div", var82, var10041, false, var10043.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n          "));
      $buf.$amp$plus(new Elem((String)null, "div", var81, var10032, false, var10034.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n        "));
      $buf.$amp$plus(new Elem((String)null, "div", var77, var10023, false, var10025.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      $buf.$amp$plus(new Elem((String)null, "body", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n    "));
      return new Elem((String)null, "html", var10004, var10005, false, var10007.seqToNodeSeq($buf));
   }

   public Option headerSparkPage$default$5() {
      return scala.None..MODULE$;
   }

   public boolean headerSparkPage$default$6() {
      return false;
   }

   public boolean headerSparkPage$default$7() {
      return false;
   }

   public Seq basicSparkPage(final HttpServletRequest request, final Function0 content, final String title, final boolean useDataTables) {
      Null var10004 = scala.xml.Null..MODULE$;
      TopScope var10005 = scala.xml.TopScope..MODULE$;
      NodeSeq var10007 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n      "));
      Null var10013 = scala.xml.Null..MODULE$;
      TopScope var10014 = scala.xml.TopScope..MODULE$;
      NodeSeq var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n        "));
      $buf.$amp$plus(this.commonHeaderNodes(request));
      $buf.$amp$plus(new Text("\n        "));
      $buf.$amp$plus(useDataTables ? this.dataTablesHeaderNodes(request) : scala.package..MODULE$.Seq().empty());
      $buf.$amp$plus(new Text("\n        "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var27 = new UnprefixedAttribute("href", this.prependBaseUri(request, "/static/spark-logo-77x50px-hd.png", this.prependBaseUri$default$3()), $md);
      var27 = new UnprefixedAttribute("rel", new Text("shortcut icon"), var27);
      $buf.$amp$plus(new Elem((String)null, "link", var27, scala.xml.TopScope..MODULE$, false, scala.collection.immutable.Nil..MODULE$));
      $buf.$amp$plus(new Text("\n        "));
      Null var10022 = scala.xml.Null..MODULE$;
      TopScope var10023 = scala.xml.TopScope..MODULE$;
      NodeSeq var10025 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(title);
      $buf.$amp$plus(new Elem((String)null, "title", var10022, var10023, false, var10025.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      $buf.$amp$plus(new Elem((String)null, "head", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      var10013 = scala.xml.Null..MODULE$;
      var10014 = scala.xml.TopScope..MODULE$;
      var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n        "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var29 = new UnprefixedAttribute("class", new Text("container-fluid"), $md);
      var10023 = scala.xml.TopScope..MODULE$;
      var10025 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n          "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var30 = new UnprefixedAttribute("class", new Text("row"), $md);
      TopScope var10032 = scala.xml.TopScope..MODULE$;
      NodeSeq var10034 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n            "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var31 = new UnprefixedAttribute("class", new Text("col-12"), $md);
      TopScope var10041 = scala.xml.TopScope..MODULE$;
      NodeSeq var10043 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n              "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var32 = new UnprefixedAttribute("style", new Text("vertical-align: middle; display: inline-block;"), $md);
      TopScope var10050 = scala.xml.TopScope..MODULE$;
      NodeSeq var10052 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n                "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var33 = new UnprefixedAttribute("href", this.prependBaseUri(request, "/", this.prependBaseUri$default$3()), $md);
      var33 = new UnprefixedAttribute("style", new Text("text-decoration: none"), var33);
      TopScope var10059 = scala.xml.TopScope..MODULE$;
      NodeSeq var10061 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n                  "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var35 = new UnprefixedAttribute("src", this.prependBaseUri(request, "/static/spark-logo-77x50px-hd.png", this.prependBaseUri$default$3()), $md);
      $buf.$amp$plus(new Elem((String)null, "img", var35, scala.xml.TopScope..MODULE$, true, scala.collection.immutable.Nil..MODULE$));
      $buf.$amp$plus(new Text("\n                  "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var36 = new UnprefixedAttribute("style", new Text("margin-right: 15px;"), $md);
      var36 = new UnprefixedAttribute("class", new Text("version"), var36);
      TopScope var10068 = scala.xml.TopScope..MODULE$;
      NodeSeq var10070 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(package$.MODULE$.SPARK_VERSION());
      $buf.$amp$plus(new Elem((String)null, "span", var36, var10068, false, var10070.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n                "));
      $buf.$amp$plus(new Elem((String)null, "a", var33, var10059, false, var10061.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n                "));
      $buf.$amp$plus(title);
      $buf.$amp$plus(new Text("\n              "));
      $buf.$amp$plus(new Elem((String)null, "h3", var32, var10050, false, var10052.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n            "));
      $buf.$amp$plus(new Elem((String)null, "div", var31, var10041, false, var10043.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n          "));
      $buf.$amp$plus(new Elem((String)null, "div", var30, var10032, false, var10034.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n          "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var38 = new UnprefixedAttribute("class", new Text("row"), $md);
      var10032 = scala.xml.TopScope..MODULE$;
      var10034 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n            "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var39 = new UnprefixedAttribute("class", new Text("col-12"), $md);
      var10041 = scala.xml.TopScope..MODULE$;
      var10043 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n              "));
      $buf.$amp$plus(content.apply());
      $buf.$amp$plus(new Text("\n            "));
      $buf.$amp$plus(new Elem((String)null, "div", var39, var10041, false, var10043.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n          "));
      $buf.$amp$plus(new Elem((String)null, "div", var38, var10032, false, var10034.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n        "));
      $buf.$amp$plus(new Elem((String)null, "div", var29, var10023, false, var10025.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      $buf.$amp$plus(new Elem((String)null, "body", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n    "));
      return new Elem((String)null, "html", var10004, var10005, false, var10007.seqToNodeSeq($buf));
   }

   public boolean basicSparkPage$default$4() {
      return false;
   }

   public Seq listingTable(final Seq headers, final Function1 generateDataRow, final Iterable data, final boolean fixedWidth, final Option id, final Seq headerClasses, final boolean stripeRowsWithCss, final boolean sortable, final Seq tooltipHeaders) {
      String _tableClass = stripeRowsWithCss ? this.TABLE_CLASS_STRIPED() : this.TABLE_CLASS_NOT_STRIPED();
      String listingTableClass = sortable ? _tableClass + " sortable" : _tableClass;
      double colWidth = (double)100.0F / (double)headers.size();
      String colWidthAttr = fixedWidth ? colWidth + "%" : "";
      boolean newlinesInHeader = headers.exists((x$3) -> BoxesRunTime.boxToBoolean($anonfun$listingTable$1(x$3)));
      Seq headerRow = ((LazyList)headers.to(scala.collection.IterableFactory..MODULE$.toFactory(scala.package..MODULE$.LazyList()))).zipWithIndex().map((x) -> {
         Option var6 = getTooltip$1(x._2$mcI$sp(), tooltipHeaders);
         if (var6 instanceof Some var7) {
            String tooltip = (String)var7.value();
            MetaData $md = scala.xml.Null..MODULE$;
            MetaData var15 = new UnprefixedAttribute("class", getClass$1(x._2$mcI$sp(), headerClasses), $md);
            var15 = new UnprefixedAttribute("width", colWidthAttr, var15);
            TopScope var21 = scala.xml.TopScope..MODULE$;
            NodeSeq var22 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("\n              "));
            MetaData $md = scala.xml.Null..MODULE$;
            MetaData var17 = new UnprefixedAttribute("title", tooltip, $md);
            var17 = new UnprefixedAttribute("data-toggle", new Text("tooltip"), var17);
            TopScope var10014 = scala.xml.TopScope..MODULE$;
            NodeSeq var10016 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("\n                "));
            $buf.$amp$plus(getHeaderContent$1((String)x._1(), newlinesInHeader));
            $buf.$amp$plus(new Text("\n              "));
            $buf.$amp$plus(new Elem((String)null, "span", var17, var10014, false, var10016.seqToNodeSeq($buf)));
            $buf.$amp$plus(new Text("\n            "));
            return new Elem((String)null, "th", var15, var21, false, var22.seqToNodeSeq($buf));
         } else if (scala.None..MODULE$.equals(var6)) {
            MetaData $md = scala.xml.Null..MODULE$;
            MetaData var19 = new UnprefixedAttribute("class", getClass$1(x._2$mcI$sp(), headerClasses), $md);
            var19 = new UnprefixedAttribute("width", colWidthAttr, var19);
            TopScope var10005 = scala.xml.TopScope..MODULE$;
            NodeSeq var10007 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(getHeaderContent$1((String)x._1(), newlinesInHeader));
            return new Elem((String)null, "th", var19, var10005, false, var10007.seqToNodeSeq($buf));
         } else {
            throw new MatchError(var6);
         }
      });
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var21 = new UnprefixedAttribute("id", id.map((datax) -> scala.xml.Text..MODULE$.apply(datax)), $md);
      var21 = new UnprefixedAttribute("class", listingTableClass, var21);
      TopScope var10005 = scala.xml.TopScope..MODULE$;
      NodeSeq var10007 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n      "));
      Null var10013 = scala.xml.Null..MODULE$;
      TopScope var10014 = scala.xml.TopScope..MODULE$;
      NodeSeq var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(headerRow);
      $buf.$amp$plus(new Elem((String)null, "thead", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      var10013 = scala.xml.Null..MODULE$;
      var10014 = scala.xml.TopScope..MODULE$;
      var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n        "));
      $buf.$amp$plus(data.map((r) -> (Seq)generateDataRow.apply(r)));
      $buf.$amp$plus(new Text("\n      "));
      $buf.$amp$plus(new Elem((String)null, "tbody", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n    "));
      return new Elem((String)null, "table", var21, var10005, false, var10007.seqToNodeSeq($buf));
   }

   public boolean listingTable$default$4() {
      return false;
   }

   public Option listingTable$default$5() {
      return scala.None..MODULE$;
   }

   public Seq listingTable$default$6() {
      return (Seq)scala.package..MODULE$.Seq().empty();
   }

   public boolean listingTable$default$7() {
      return true;
   }

   public boolean listingTable$default$8() {
      return true;
   }

   public Seq listingTable$default$9() {
      return (Seq)scala.package..MODULE$.Seq().empty();
   }

   public Seq makeProgressBar(final int started, final int completed, final int failed, final int skipped, final scala.collection.immutable.Map reasonToNumKilled, final int total) {
      double ratio = total == 0 ? (double)100.0F : (double)completed / (double)total * (double)100;
      String completeWidth = .MODULE$.format$extension(scala.Predef..MODULE$.augmentString("width: %s%%"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToDouble(ratio)}));
      int boundedStarted = scala.math.package..MODULE$.min(started, total - completed);
      double startRatio = total == 0 ? (double)0.0F : (double)boundedStarted / (double)total * (double)100;
      String startWidth = .MODULE$.format$extension(scala.Predef..MODULE$.augmentString("width: %s%%"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToDouble(startRatio)}));
      String killTaskReasonText = ((IterableOnceOps)((IterableOps)reasonToNumKilled.toSeq().sortBy((x$4) -> BoxesRunTime.boxToInteger($anonfun$makeProgressBar$1(x$4)), scala.math.Ordering.Int..MODULE$)).map((x0$1) -> {
         if (x0$1 != null) {
            String reason = (String)x0$1._1();
            int count = x0$1._2$mcI$sp();
            return " (" + count + " killed: " + reason + ")";
         } else {
            throw new MatchError(x0$1);
         }
      })).mkString();
      String progressTitle = completed + "/" + total + (started > 0 ? " (" + started + " running)" : "") + (failed > 0 ? " (" + failed + " failed)" : "") + (skipped > 0 ? " (" + skipped + " skipped)" : "") + killTaskReasonText;
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var22 = new UnprefixedAttribute("class", new Text("progress"), $md);
      TopScope var10005 = scala.xml.TopScope..MODULE$;
      NodeSeq var10007 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n      "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var23 = new UnprefixedAttribute("title", progressTitle, $md);
      var23 = new UnprefixedAttribute("style", new Text("text-align:center; position:absolute; width:100%;"), var23);
      TopScope var10014 = scala.xml.TopScope..MODULE$;
      NodeSeq var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n        "));
      $buf.$amp$plus(BoxesRunTime.boxToInteger(completed));
      $buf.$amp$plus(new Text("/"));
      $buf.$amp$plus(BoxesRunTime.boxToInteger(total));
      $buf.$amp$plus(new Text("\n        "));
      $buf.$amp$plus(failed == 0 && skipped == 0 && started > 0 ? "(" + started + " running)" : BoxedUnit.UNIT);
      $buf.$amp$plus(new Text("\n        "));
      $buf.$amp$plus(failed > 0 ? "(" + failed + " failed)" : BoxedUnit.UNIT);
      $buf.$amp$plus(new Text("\n        "));
      $buf.$amp$plus(skipped > 0 ? "(" + skipped + " skipped)" : BoxedUnit.UNIT);
      $buf.$amp$plus(new Text("\n        "));
      $buf.$amp$plus(killTaskReasonText);
      $buf.$amp$plus(new Text("\n      "));
      $buf.$amp$plus(new Elem((String)null, "span", var23, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var25 = new UnprefixedAttribute("style", completeWidth, $md);
      var25 = new UnprefixedAttribute("class", new Text("progress-bar progress-completed"), var25);
      $buf.$amp$plus(new Elem((String)null, "div", var25, scala.xml.TopScope..MODULE$, false, scala.collection.immutable.Nil..MODULE$));
      $buf.$amp$plus(new Text("\n      "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var27 = new UnprefixedAttribute("style", startWidth, $md);
      var27 = new UnprefixedAttribute("class", new Text("progress-bar progress-started"), var27);
      $buf.$amp$plus(new Elem((String)null, "div", var27, scala.xml.TopScope..MODULE$, false, scala.collection.immutable.Nil..MODULE$));
      $buf.$amp$plus(new Text("\n    "));
      return new Elem((String)null, "div", var22, var10005, false, var10007.seqToNodeSeq($buf));
   }

   public Seq showDagVizForStage(final int stageId, final Option graph) {
      return scala.xml.NodeSeq..MODULE$.seqToNodeSeq(this.showDagViz(scala.Option..MODULE$.option2Iterable(graph).toSeq(), false));
   }

   public scala.collection.Seq showDagVizForJob(final int jobId, final scala.collection.Seq graphs) {
      return this.showDagViz(graphs, true);
   }

   private scala.collection.Seq showDagViz(final scala.collection.Seq graphs, final boolean forJob) {
      Null var10004 = scala.xml.Null..MODULE$;
      TopScope var10005 = scala.xml.TopScope..MODULE$;
      NodeSeq var10007 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n      "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var12 = new UnprefixedAttribute("onclick", "toggleDagViz(" + forJob + ");", $md);
      var12 = new UnprefixedAttribute("class", new Text("expand-dag-viz"), var12);
      var12 = new UnprefixedAttribute("id", forJob ? "job-dag-viz" : "stage-dag-viz", var12);
      TopScope var10014 = scala.xml.TopScope..MODULE$;
      NodeSeq var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n        "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var15 = new UnprefixedAttribute("class", new Text("expand-dag-viz-arrow arrow-closed"), $md);
      $buf.$amp$plus(new Elem((String)null, "span", var15, scala.xml.TopScope..MODULE$, false, scala.collection.immutable.Nil..MODULE$));
      $buf.$amp$plus(new Text("\n        "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var16 = new UnprefixedAttribute("data-placement", new Text("top"), $md);
      var16 = new UnprefixedAttribute("title", forJob ? ToolTips$.MODULE$.JOB_DAG() : ToolTips$.MODULE$.STAGE_DAG(), var16);
      var16 = new UnprefixedAttribute("data-toggle", new Text("tooltip"), var16);
      TopScope var10023 = scala.xml.TopScope..MODULE$;
      NodeSeq var10025 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n          DAG Visualization\n        "));
      $buf.$amp$plus(new Elem((String)null, "a", var16, var10023, false, var10025.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      $buf.$amp$plus(new Elem((String)null, "span", var12, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var19 = new UnprefixedAttribute("id", new Text("dag-viz-graph"), $md);
      $buf.$amp$plus(new Elem((String)null, "div", var19, scala.xml.TopScope..MODULE$, false, scala.collection.immutable.Nil..MODULE$));
      $buf.$amp$plus(new Text("\n      "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var20 = new UnprefixedAttribute("style", new Text("display:none"), $md);
      var20 = new UnprefixedAttribute("id", new Text("dag-viz-metadata"), var20);
      var10014 = scala.xml.TopScope..MODULE$;
      var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n        "));
      $buf.$amp$plus(graphs.map((g) -> {
         String stageId = g.rootCluster().id().replaceAll(RDDOperationGraph$.MODULE$.STAGE_CLUSTER_PREFIX(), "");
         String skipped = Boolean.toString(g.rootCluster().name().contains("skipped"));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var7 = new UnprefixedAttribute("skipped", skipped, $md);
         var7 = new UnprefixedAttribute("stage-id", stageId, var7);
         var7 = new UnprefixedAttribute("class", new Text("stage-metadata"), var7);
         TopScope var10005 = scala.xml.TopScope..MODULE$;
         NodeSeq var10007 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n              "));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var10 = new UnprefixedAttribute("class", new Text("dot-file"), $md);
         TopScope var10014 = scala.xml.TopScope..MODULE$;
         NodeSeq var10016 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(RDDOperationGraph$.MODULE$.makeDotFile(g));
         $buf.$amp$plus(new Elem((String)null, "div", var10, var10014, false, var10016.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n              "));
         $buf.$amp$plus(g.incomingEdges().map((e) -> {
            MetaData $md = scala.xml.Null..MODULE$;
            MetaData var3 = new UnprefixedAttribute("class", new Text("incoming-edge"), $md);
            TopScope var10005 = scala.xml.TopScope..MODULE$;
            NodeSeq var10007 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(BoxesRunTime.boxToInteger(e.fromId()));
            $buf.$amp$plus(new Text(","));
            $buf.$amp$plus(BoxesRunTime.boxToInteger(e.toId()));
            return new Elem((String)null, "div", var3, var10005, false, var10007.seqToNodeSeq($buf));
         }));
         $buf.$amp$plus(new Text("\n              "));
         $buf.$amp$plus(g.outgoingEdges().map((e) -> {
            MetaData $md = scala.xml.Null..MODULE$;
            MetaData var3 = new UnprefixedAttribute("class", new Text("outgoing-edge"), $md);
            TopScope var10005 = scala.xml.TopScope..MODULE$;
            NodeSeq var10007 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(BoxesRunTime.boxToInteger(e.fromId()));
            $buf.$amp$plus(new Text(","));
            $buf.$amp$plus(BoxesRunTime.boxToInteger(e.toId()));
            return new Elem((String)null, "div", var3, var10005, false, var10007.seqToNodeSeq($buf));
         }));
         $buf.$amp$plus(new Text("\n              "));
         $buf.$amp$plus(((IterableOps)((IterableOps)g.rootCluster().getCachedNodes().map((n) -> {
            MetaData $md = scala.xml.Null..MODULE$;
            MetaData var3 = new UnprefixedAttribute("class", new Text("cached-rdd"), $md);
            TopScope var10005 = scala.xml.TopScope..MODULE$;
            NodeSeq var10007 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(BoxesRunTime.boxToInteger(n.id()));
            return new Elem((String)null, "div", var3, var10005, false, var10007.seqToNodeSeq($buf));
         })).$plus$plus((IterableOnce)g.rootCluster().getBarrierClusters().map((c) -> {
            MetaData $md = scala.xml.Null..MODULE$;
            MetaData var3 = new UnprefixedAttribute("class", new Text("barrier-rdd"), $md);
            TopScope var10005 = scala.xml.TopScope..MODULE$;
            NodeSeq var10007 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(c.id());
            return new Elem((String)null, "div", var3, var10005, false, var10007.seqToNodeSeq($buf));
         }))).$plus$plus((IterableOnce)g.rootCluster().getIndeterminateNodes().map((n) -> {
            MetaData $md = scala.xml.Null..MODULE$;
            MetaData var3 = new UnprefixedAttribute("class", new Text("indeterminate-rdd"), $md);
            TopScope var10005 = scala.xml.TopScope..MODULE$;
            NodeSeq var10007 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(BoxesRunTime.boxToInteger(n.id()));
            return new Elem((String)null, "div", var3, var10005, false, var10007.seqToNodeSeq($buf));
         })));
         $buf.$amp$plus(new Text("\n            "));
         return new Elem((String)null, "div", var7, var10005, false, var10007.seqToNodeSeq($buf));
      }));
      $buf.$amp$plus(new Text("\n      "));
      $buf.$amp$plus(new Elem((String)null, "div", var20, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n    "));
      return new Elem((String)null, "div", var10004, var10005, false, var10007.seqToNodeSeq($buf));
   }

   public Seq tooltip(final String text, final String position) {
      Null var10004 = scala.xml.Null..MODULE$;
      TopScope var10005 = scala.xml.TopScope..MODULE$;
      NodeSeq var10007 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n      ("));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var6 = new UnprefixedAttribute("title", text, $md);
      var6 = new UnprefixedAttribute("data-placement", position, var6);
      var6 = new UnprefixedAttribute("data-toggle", new Text("tooltip"), var6);
      TopScope var10014 = scala.xml.TopScope..MODULE$;
      NodeSeq var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("?"));
      $buf.$amp$plus(new Elem((String)null, "a", var6, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text(")\n    "));
      return new Elem((String)null, "sup", var10004, var10005, false, var10007.seqToNodeSeq($buf));
   }

   public NodeSeq makeDescription(final String desc, final String basePathUri, final boolean plainText) {
      Object var10000;
      try {
         Elem xml = (Elem)scala.xml.XML..MODULE$.loadString("<span class=\"description-input\">" + desc + "</span>");
         Set allowedNodeLabels = (Set)scala.Predef..MODULE$.Set().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"a", "span", "br"})));
         Set allowedAttributes = (Set)scala.Predef..MODULE$.Set().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"class", "href"})));
         NodeSeq illegalNodes = (NodeSeq)xml.$bslash$bslash("_").filterNot((node) -> BoxesRunTime.boxToBoolean($anonfun$makeDescription$1(allowedNodeLabels, allowedAttributes, node)));
         if (illegalNodes.nonEmpty()) {
            IterableOnceOps var10002 = (IterableOnceOps)illegalNodes.map((n) -> {
               String var10000 = n.label();
               return var10000 + " in " + n;
            });
            throw new IllegalArgumentException("Only HTML anchors allowed in job descriptions\n" + var10002.mkString("\n\t"));
         }

         Seq allLinks = (Seq)((IterableOps)((IterableOps)xml.$bslash$bslash("a").flatMap((x$6) -> x$6.attributes())).filter((x$7) -> BoxesRunTime.boxToBoolean($anonfun$makeDescription$6(x$7)))).map((x$8) -> x$8.value().toString());
         if (allLinks.exists((x$9) -> BoxesRunTime.boxToBoolean($anonfun$makeDescription$8(x$9)))) {
            throw new IllegalArgumentException("Links in job descriptions must be root-relative:\n" + allLinks.mkString("\n\t"));
         }

         RewriteRule rule = plainText ? new RewriteRule() {
            public Seq transform(final Node n) {
               boolean var3 = false;
               Elem var4 = null;
               if (n instanceof Elem) {
                  var3 = true;
                  var4 = (Elem)n;
                  if (var4.child().isEmpty()) {
                     return scala.xml.Text..MODULE$.apply(var4.text());
                  }
               }

               return (Seq)(var3 ? scala.xml.Text..MODULE$.apply(scala.xml.NodeSeq..MODULE$.seqToNodeSeq((scala.collection.Seq)var4.child().flatMap((nx) -> this.transform(nx))).text()) : n);
            }

            // $FF: synthetic method
            private static Object $deserializeLambda$(SerializedLambda var0) {
               return var0.lambdaDeserialize<invokedynamic>(var0);
            }
         } : new RewriteRule(basePathUri) {
            private final String basePathUri$1;

            public Seq transform(final Node n) {
               if (n instanceof Elem var4) {
                  if (var4.$bslash("@href").nonEmpty()) {
                     String relativePath = ((scala.collection.Seq)var4.attribute("href").get()).toString();
                     String var10000 = .MODULE$.stripSuffix$extension(scala.Predef..MODULE$.augmentString(this.basePathUri$1), "/");
                     String fullUri = var10000 + "/" + .MODULE$.stripPrefix$extension(scala.Predef..MODULE$.augmentString(relativePath), "/");
                     return var4.$percent((MetaData)scala.xml.Attribute..MODULE$.apply((String)null, "href", fullUri, scala.xml.Null..MODULE$));
                  }
               }

               return n;
            }

            public {
               this.basePathUri$1 = basePathUri$1;
            }
         };
         var10000 = scala.xml.NodeSeq..MODULE$.seqToNodeSeq((new RuleTransformer(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new RewriteRule[]{rule})))).transform(xml));
      } catch (Throwable var16) {
         if (var16 == null || !scala.util.control.NonFatal..MODULE$.apply(var16)) {
            throw var16;
         }

         if (plainText) {
            var10000 = scala.xml.Text..MODULE$.apply(desc);
         } else {
            MetaData $md = scala.xml.Null..MODULE$;
            MetaData var17 = new UnprefixedAttribute("class", new Text("description-input"), $md);
            TopScope var10005 = scala.xml.TopScope..MODULE$;
            NodeSeq var10007 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(desc);
            var10000 = new Elem((String)null, "span", var17, var10005, false, var10007.seqToNodeSeq($buf));
         }
      }

      return (NodeSeq)var10000;
   }

   public boolean makeDescription$default$3() {
      return false;
   }

   public String decodeURLParameter(final String urlParam) {
      String param = urlParam;
      String decodedParam = URLDecoder.decode(urlParam, StandardCharsets.UTF_8.name());

      while(true) {
         if (param == null) {
            if (decodedParam == null) {
               break;
            }
         } else if (param.equals(decodedParam)) {
            break;
         }

         param = decodedParam;
         decodedParam = URLDecoder.decode(decodedParam, StandardCharsets.UTF_8.name());
      }

      return param;
   }

   public MultivaluedStringMap decodeURLParameter(final MultivaluedMap params) {
      MultivaluedStringMap decodedParameters = new MultivaluedStringMap();
      params.forEach((encodeKey, encodeValues) -> {
         String decodeKey = MODULE$.decodeURLParameter(encodeKey);
         LinkedList decodeValues = new LinkedList();
         encodeValues.forEach((v) -> decodeValues.add(MODULE$.decodeURLParameter(v)));
         decodedParameters.addAll(decodeKey, decodeValues);
      });
      return decodedParameters;
   }

   public int getTimeZoneOffset() {
      return TimeZone.getDefault().getOffset(System.currentTimeMillis()) / 1000 / 60;
   }

   public String makeHref(final boolean proxy, final String id, final String origHref) {
      if (proxy) {
         String proxyPrefix = (String)scala.sys.package..MODULE$.props().getOrElse("spark.ui.proxyBase", () -> "");
         return proxyPrefix + "/proxy/" + id;
      } else {
         return origHref;
      }
   }

   public Response buildErrorResponse(final Response.Status status, final String msg) {
      return Response.status(status).entity(msg).type("text/plain").build();
   }

   public Tuple2[] durationDataPadding(final Tuple2[] values) {
      Set operationLabels = scala.Predef..MODULE$.wrapRefArray(scala.collection.ArrayOps..MODULE$.flatMap$extension(scala.Predef..MODULE$.refArrayOps((Object[])values), (x$10) -> scala.jdk.CollectionConverters..MODULE$.SetHasAsScala(((Map)x$10._2()).keySet()).asScala(), scala.reflect.ClassTag..MODULE$.apply(String.class))).toSet();
      return (Tuple2[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])values), (x0$1) -> {
         if (x0$1 != null) {
            long xValue = x0$1._1$mcJ$sp();
            Map yValue = (Map)x0$1._2();
            Set dataPadding = (Set)operationLabels.map((opLabel) -> yValue.containsKey(opLabel) ? new Tuple2(opLabel, BoxesRunTime.boxToDouble((double)scala.Predef..MODULE$.Long2long((Long)yValue.get(opLabel)))) : new Tuple2(opLabel, BoxesRunTime.boxToDouble((double)0.0F)));
            return new Tuple2(BoxesRunTime.boxToLong(xValue), dataPadding.toMap(scala..less.colon.less..MODULE$.refl()));
         } else {
            throw new MatchError(x0$1);
         }
      }, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
   }

   public Seq detailsUINode(final boolean isMultiline, final String message) {
      if (isMultiline) {
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var8 = new UnprefixedAttribute("class", new Text("expand-details"), $md);
         var8 = new UnprefixedAttribute("onclick", new Text("this.parentNode.querySelector('.stacktrace-details').classList.toggle('collapsed')"), var8);
         TopScope var10005 = scala.xml.TopScope..MODULE$;
         NodeSeq var10007 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n        +details\n      "));
         Elem var10000 = new Elem((String)null, "span", var8, var10005, false, var10007.seqToNodeSeq($buf));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var10 = new UnprefixedAttribute("class", new Text("stacktrace-details collapsed"), $md);
         TopScope var10006 = scala.xml.TopScope..MODULE$;
         NodeSeq var10008 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n          "));
         Null var10014 = scala.xml.Null..MODULE$;
         TopScope var10015 = scala.xml.TopScope..MODULE$;
         NodeSeq var10017 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(message);
         $buf.$amp$plus(new Elem((String)null, "pre", var10014, var10015, false, var10017.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n        "));
         return var10000.$plus$plus(new Elem((String)null, "div", var10, var10006, false, var10008.seqToNodeSeq($buf)));
      } else {
         return (Seq)scala.package..MODULE$.Seq().empty();
      }
   }

   private final Regex ERROR_CLASS_REGEX() {
      return ERROR_CLASS_REGEX;
   }

   public Tuple2 errorSummary(final String errorMessage) {
      boolean isMultiline = true;
      Option maybeErrorClass = this.ERROR_CLASS_REGEX().findFirstMatchIn(errorMessage).map((x$11) -> x$11.group("errorClass"));
      String var10000;
      if (maybeErrorClass.nonEmpty() && .MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString((String)maybeErrorClass.get()))) {
         var10000 = (String)maybeErrorClass.get();
      } else if (errorMessage.indexOf(10) >= 0) {
         var10000 = errorMessage.substring(0, errorMessage.indexOf(10));
      } else if (errorMessage.indexOf(":") >= 0) {
         var10000 = errorMessage.substring(0, errorMessage.indexOf(":"));
      } else {
         isMultiline = false;
         var10000 = errorMessage;
      }

      String errorClassOrBrief = var10000;
      return new Tuple2(errorClassOrBrief, BoxesRunTime.boxToBoolean(isMultiline));
   }

   public Seq errorMessageCell(final String errorMessage) {
      Tuple2 var4 = this.errorSummary(errorMessage);
      if (var4 != null) {
         String summary = (String)var4._1();
         boolean isMultiline = var4._2$mcZ$sp();
         Tuple2 var3 = new Tuple2(summary, BoxesRunTime.boxToBoolean(isMultiline));
         String summary = (String)var3._1();
         boolean isMultiline = var3._2$mcZ$sp();
         Seq details = this.detailsUINode(isMultiline, errorMessage);
         Null var10004 = scala.xml.Null..MODULE$;
         TopScope var10005 = scala.xml.TopScope..MODULE$;
         NodeSeq var10007 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(summary);
         $buf.$amp$plus(details);
         return new Elem((String)null, "td", var10004, var10005, false, var10007.seqToNodeSeq($buf));
      } else {
         throw new MatchError(var4);
      }
   }

   public String formatImportJavaScript(final HttpServletRequest request, final String sourceFile, final Seq methods) {
      String methodsStr = methods.mkString("{", ", ", "}");
      String sourceFileStr = this.prependBaseUri(request, sourceFile, this.prependBaseUri$default$3());
      return "import " + methodsStr + " from \"" + sourceFileStr + "\";";
   }

   private static final String toString$1(final long num, final String unit) {
      if (num == 0L) {
         return "";
      } else {
         return num == 1L ? num + " " + unit : num + " " + unit + "s";
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$formatDurationVerbose$1(final long ms$1, final Object nonLocalReturnKey1$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         long durationLimit = x0$1._1$mcJ$sp();
         String durationString = (String)x0$1._2();
         if (ms$1 < durationLimit) {
            throw new NonLocalReturnControl(nonLocalReturnKey1$1, durationString);
         } else {
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }
      } else {
         throw new MatchError(x0$1);
      }
   }

   private static final String getClass$1(final int index, final Seq headerClasses$1) {
      return index < headerClasses$1.size() ? (String)headerClasses$1.apply(index) : "";
   }

   private static final Option getTooltip$1(final int index, final Seq tooltipHeaders$1) {
      return (Option)(index < tooltipHeaders$1.size() ? (Option)tooltipHeaders$1.apply(index) : scala.None..MODULE$);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$listingTable$1(final String x$3) {
      return x$3.contains("\n");
   }

   private static final Seq getHeaderContent$1(final String header, final boolean newlinesInHeader$1) {
      if (newlinesInHeader$1) {
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var4 = new UnprefixedAttribute("class", new Text("list-unstyled"), $md);
         TopScope var10005 = scala.xml.TopScope..MODULE$;
         NodeSeq var10007 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n          "));
         $buf.$amp$plus(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])header.split("\n")), (t) -> {
            Null var10004 = scala.xml.Null..MODULE$;
            TopScope var10005 = scala.xml.TopScope..MODULE$;
            NodeSeq var10007 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text(" "));
            $buf.$amp$plus(t);
            $buf.$amp$plus(new Text(" "));
            return new Elem((String)null, "li", var10004, var10005, false, var10007.seqToNodeSeq($buf));
         }, scala.reflect.ClassTag..MODULE$.apply(Elem.class)));
         $buf.$amp$plus(new Text("\n        "));
         return new Elem((String)null, "ul", var4, var10005, false, var10007.seqToNodeSeq($buf));
      } else {
         return scala.xml.Text..MODULE$.apply(header);
      }
   }

   // $FF: synthetic method
   public static final int $anonfun$makeProgressBar$1(final Tuple2 x$4) {
      return -x$4._2$mcI$sp();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$makeDescription$3(final Set allowedAttributes$1, final String elem) {
      return allowedAttributes$1.contains(elem);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$makeDescription$1(final Set allowedNodeLabels$1, final Set allowedAttributes$1, final Node node) {
      return allowedNodeLabels$1.contains(node.label()) && ((IterableOnceOps)node.attributes().map((x$5) -> x$5.key())).forall((elem) -> BoxesRunTime.boxToBoolean($anonfun$makeDescription$3(allowedAttributes$1, elem)));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$makeDescription$6(final MetaData x$7) {
      boolean var2;
      label23: {
         String var10000 = x$7.key();
         String var1 = "href";
         if (var10000 == null) {
            if (var1 == null) {
               break label23;
            }
         } else if (var10000.equals(var1)) {
            break label23;
         }

         var2 = false;
         return var2;
      }

      var2 = true;
      return var2;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$makeDescription$8(final String x$9) {
      return !x$9.startsWith("/");
   }

   private UIUtils$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
