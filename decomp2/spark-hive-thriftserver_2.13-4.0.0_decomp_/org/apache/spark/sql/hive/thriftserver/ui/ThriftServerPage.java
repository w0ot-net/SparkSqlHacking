package org.apache.spark.sql.hive.thriftserver.ui;

import jakarta.servlet.http.HttpServletRequest;
import java.lang.invoke.SerializedLambda;
import java.util.Date;
import java.util.Map;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.ui.WebUIPage;
import org.slf4j.Logger;
import scala.Function0;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.collection.IterableOps;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;
import scala.xml.Elem;
import scala.xml.MetaData;
import scala.xml.NodeBuffer;
import scala.xml.NodeSeq;
import scala.xml.Null;
import scala.xml.Text;
import scala.xml.TopScope;
import scala.xml.UnprefixedAttribute;
import scala.xml.Null.;

@ScalaSignature(
   bytes = "\u0006\u0005-4Qa\u0003\u0007\u0001\u0019iA\u0001B\n\u0001\u0003\u0002\u0003\u0006I\u0001\u000b\u0005\u0006Y\u0001!\t!\f\u0005\ba\u0001\u0011\r\u0011\"\u00032\u0011\u0019)\u0004\u0001)A\u0005e!9a\u0007\u0001b\u0001\n\u00139\u0004B\u0002!\u0001A\u0003%\u0001\bC\u0003B\u0001\u0011\u0005!\tC\u0003d\u0001\u0011%A\rC\u0003f\u0001\u0011%a\rC\u0003i\u0001\u0011%\u0011N\u0001\tUQJLg\r^*feZ,'\u000fU1hK*\u0011QBD\u0001\u0003k&T!a\u0004\t\u0002\u0019QD'/\u001b4ug\u0016\u0014h/\u001a:\u000b\u0005E\u0011\u0012\u0001\u00025jm\u0016T!a\u0005\u000b\u0002\u0007M\fHN\u0003\u0002\u0016-\u0005)1\u000f]1sW*\u0011q\u0003G\u0001\u0007CB\f7\r[3\u000b\u0003e\t1a\u001c:h'\r\u00011\u0004\t\t\u00039yi\u0011!\b\u0006\u0003\u001bQI!aH\u000f\u0003\u0013]+'-V%QC\u001e,\u0007CA\u0011%\u001b\u0005\u0011#BA\u0012\u0015\u0003!Ig\u000e^3s]\u0006d\u0017BA\u0013#\u0005\u001daunZ4j]\u001e\fa\u0001]1sK:$8\u0001\u0001\t\u0003S)j\u0011\u0001D\u0005\u0003W1\u0011q\u0002\u00165sS\u001a$8+\u001a:wKJ$\u0016MY\u0001\u0007y%t\u0017\u000e\u001e \u0015\u00059z\u0003CA\u0015\u0001\u0011\u00151#\u00011\u0001)\u0003\u0015\u0019Ho\u001c:f+\u0005\u0011\u0004CA\u00154\u0013\t!DBA\u0010ISZ,G\u000b\u001b:jMR\u001cVM\u001d<feJ\n\u0005\u000f]*uCR,8o\u0015;pe\u0016\faa\u001d;pe\u0016\u0004\u0013!C:uCJ$H+[7f+\u0005A\u0004CA\u001d?\u001b\u0005Q$BA\u001e=\u0003\u0011)H/\u001b7\u000b\u0003u\nAA[1wC&\u0011qH\u000f\u0002\u0005\t\u0006$X-\u0001\u0006ti\u0006\u0014H\u000fV5nK\u0002\naA]3oI\u0016\u0014HCA\"X!\r!e*\u0015\b\u0003\u000b.s!AR%\u000e\u0003\u001dS!\u0001S\u0014\u0002\rq\u0012xn\u001c;?\u0013\u0005Q\u0015!B:dC2\f\u0017B\u0001'N\u0003\u001d\u0001\u0018mY6bO\u0016T\u0011AS\u0005\u0003\u001fB\u00131aU3r\u0015\taU\n\u0005\u0002S+6\t1K\u0003\u0002U\u001b\u0006\u0019\u00010\u001c7\n\u0005Y\u001b&\u0001\u0002(pI\u0016DQ\u0001W\u0004A\u0002e\u000bqA]3rk\u0016\u001cH\u000f\u0005\u0002[C6\t1L\u0003\u0002];\u0006!\u0001\u000e\u001e;q\u0015\tqv,A\u0004tKJ4H.\u001a;\u000b\u0003\u0001\fqA[1lCJ$\u0018-\u0003\u0002c7\n\u0011\u0002\n\u001e;q'\u0016\u0014h\u000f\\3u%\u0016\fX/Z:u\u0003I9WM\\3sCR,')Y:jGN#\u0018\r^:\u0015\u0003\r\u000bQcZ3oKJ\fG/Z*R\u0019N#\u0018\r^:UC\ndW\r\u0006\u0002DO\")\u0001,\u0003a\u00013\u0006Ir-\u001a8fe\u0006$XmU3tg&|gn\u0015;biN$\u0016M\u00197f)\t\u0019%\u000eC\u0003Y\u0015\u0001\u0007\u0011\f"
)
public class ThriftServerPage extends WebUIPage implements Logging {
   private final ThriftServerTab parent;
   private final HiveThriftServer2AppStatusStore store;
   private final Date startTime;
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

   private HiveThriftServer2AppStatusStore store() {
      return this.store;
   }

   private Date startTime() {
      return this.startTime;
   }

   public Seq render(final HttpServletRequest request) {
      synchronized(this.store()){}

      Seq var4;
      try {
         IterableOps var10000 = (IterableOps)this.generateBasicStats().$plus$plus(new Elem((String)null, "br", .MODULE$, scala.xml.TopScope..MODULE$, true, scala.collection.immutable.Nil..MODULE$));
         Null var10005 = .MODULE$;
         TopScope var10006 = scala.xml.TopScope..MODULE$;
         NodeSeq var10008 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n          "));
         $buf.$amp$plus(BoxesRunTime.boxToInteger(this.store().getOnlineSessionNum()));
         $buf.$amp$plus(new Text("\n          session(s) are online,\n          running\n          "));
         $buf.$amp$plus(BoxesRunTime.boxToInteger(this.store().getTotalRunning()));
         $buf.$amp$plus(new Text("\n          SQL statement(s)\n        "));
         var4 = (Seq)((IterableOps)((IterableOps)var10000.$plus$plus(new Elem((String)null, "h4", var10005, var10006, false, var10008.seqToNodeSeq($buf)))).$plus$plus(this.generateSessionStatsTable(request))).$plus$plus(this.generateSQLStatsTable(request));
      } catch (Throwable var7) {
         throw var7;
      }

      return org.apache.spark.ui.UIUtils..MODULE$.headerSparkPage(request, "JDBC/ODBC Server", () -> var4, this.parent, org.apache.spark.ui.UIUtils..MODULE$.headerSparkPage$default$5(), org.apache.spark.ui.UIUtils..MODULE$.headerSparkPage$default$6(), org.apache.spark.ui.UIUtils..MODULE$.headerSparkPage$default$7());
   }

   private Seq generateBasicStats() {
      long timeSinceStart = System.currentTimeMillis() - this.startTime().getTime();
      MetaData $md = .MODULE$;
      MetaData var9 = new UnprefixedAttribute("class", new Text("list-unstyled"), $md);
      TopScope var10005 = scala.xml.TopScope..MODULE$;
      NodeSeq var10007 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n      "));
      Null var10013 = .MODULE$;
      TopScope var10014 = scala.xml.TopScope..MODULE$;
      NodeSeq var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n        "));
      Null var10022 = .MODULE$;
      TopScope var10023 = scala.xml.TopScope..MODULE$;
      NodeSeq var10025 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("Started at: "));
      $buf.$amp$plus(new Elem((String)null, "strong", var10022, var10023, false, var10025.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text(" "));
      $buf.$amp$plus(org.apache.spark.ui.UIUtils..MODULE$.formatDate(this.startTime()));
      $buf.$amp$plus(new Text("\n      "));
      $buf.$amp$plus(new Elem((String)null, "li", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      var10013 = .MODULE$;
      var10014 = scala.xml.TopScope..MODULE$;
      var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n        "));
      var10022 = .MODULE$;
      var10023 = scala.xml.TopScope..MODULE$;
      var10025 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("Time since start: "));
      $buf.$amp$plus(new Elem((String)null, "strong", var10022, var10023, false, var10025.seqToNodeSeq($buf)));
      $buf.$amp$plus(org.apache.spark.ui.UIUtils..MODULE$.formatDurationVerbose(timeSinceStart));
      $buf.$amp$plus(new Text("\n      "));
      $buf.$amp$plus(new Elem((String)null, "li", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n    "));
      return new Elem((String)null, "ul", var9, var10005, false, var10007.seqToNodeSeq($buf));
   }

   private Seq generateSQLStatsTable(final HttpServletRequest request) {
      int numStatement = this.store().getExecutionList().size();
      Object var10000;
      if (numStatement > 0) {
         String sqlTableTag = "sqlstat";
         int sqlTablePage = BoxesRunTime.unboxToInt(scala.Option..MODULE$.apply(request.getParameter(sqlTableTag + ".page")).map((x$1) -> BoxesRunTime.boxToInteger($anonfun$generateSQLStatsTable$1(x$1))).getOrElse((JFunction0.mcI.sp)() -> 1));

         try {
            var10000 = new Some((new SqlStatsPagedTable(request, this.parent, this.store().getExecutionList(), "sqlserver", org.apache.spark.ui.UIUtils..MODULE$.prependBaseUri(request, this.parent.basePath(), org.apache.spark.ui.UIUtils..MODULE$.prependBaseUri$default$3()), sqlTableTag)).table(sqlTablePage));
         } catch (Throwable var22) {
            if (!(var22 instanceof IllegalArgumentException ? true : var22 instanceof IndexOutOfBoundsException)) {
               throw var22;
            }

            MetaData $md = .MODULE$;
            MetaData var23 = new UnprefixedAttribute("class", new Text("alert alert-error"), $md);
            TopScope var10007 = scala.xml.TopScope..MODULE$;
            NodeSeq var10009 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("\n            "));
            Null var10015 = .MODULE$;
            TopScope var10016 = scala.xml.TopScope..MODULE$;
            NodeSeq var10018 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("Error while rendering job table:"));
            $buf.$amp$plus(new Elem((String)null, "p", var10015, var10016, false, var10018.seqToNodeSeq($buf)));
            $buf.$amp$plus(new Text("\n            "));
            var10015 = .MODULE$;
            var10016 = scala.xml.TopScope..MODULE$;
            var10018 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("\n              "));
            $buf.$amp$plus(org.apache.spark.util.Utils..MODULE$.exceptionString(var22));
            $buf.$amp$plus(new Text("\n            "));
            $buf.$amp$plus(new Elem((String)null, "pre", var10015, var10016, false, var10018.seqToNodeSeq($buf)));
            $buf.$amp$plus(new Text("\n          "));
            var10000 = new Some(new Elem((String)null, "div", var23, var10007, false, var10009.seqToNodeSeq($buf)));
         }
      } else {
         var10000 = scala.None..MODULE$;
      }

      Option table = (Option)var10000;
      MetaData $md = .MODULE$;
      MetaData var24 = new UnprefixedAttribute("onClick", new Text("collapseTable('collapse-aggregated-sqlstat',\n                'aggregated-sqlstat')"), $md);
      var24 = new UnprefixedAttribute("class", new Text("collapse-aggregated-sqlstat collapse-table"), var24);
      var24 = new UnprefixedAttribute("id", new Text("sqlstat"), var24);
      TopScope var10005 = scala.xml.TopScope..MODULE$;
      NodeSeq var30 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n        "));
      Null var10013 = .MODULE$;
      TopScope var10014 = scala.xml.TopScope..MODULE$;
      NodeSeq var33 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n          "));
      MetaData $md = .MODULE$;
      MetaData var27 = new UnprefixedAttribute("class", new Text("collapse-table-arrow arrow-open"), $md);
      $buf.$amp$plus(new Elem((String)null, "span", var27, scala.xml.TopScope..MODULE$, false, scala.collection.immutable.Nil..MODULE$));
      $buf.$amp$plus(new Text("\n          "));
      Null var10022 = .MODULE$;
      TopScope var10023 = scala.xml.TopScope..MODULE$;
      NodeSeq var10025 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("SQL Statistics ("));
      $buf.$amp$plus(BoxesRunTime.boxToInteger(numStatement));
      $buf.$amp$plus(new Text(")"));
      $buf.$amp$plus(new Elem((String)null, "a", var10022, var10023, false, var10025.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n        "));
      $buf.$amp$plus(new Elem((String)null, "h4", var10013, var10014, false, var33.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      Elem var29 = new Elem((String)null, "span", var24, var10005, false, var30.seqToNodeSeq($buf));
      MetaData $md = .MODULE$;
      MetaData var28 = new UnprefixedAttribute("class", new Text("aggregated-sqlstat collapsible-table"), $md);
      TopScope var10006 = scala.xml.TopScope..MODULE$;
      NodeSeq var10008 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n          "));
      $buf.$amp$plus(table.getOrElse(() -> "No statistics have been generated yet."));
      $buf.$amp$plus(new Text("\n        "));
      NodeSeq content = var29.$plus$plus(new Elem((String)null, "div", var28, var10006, false, var10008.seqToNodeSeq($buf)));
      return content;
   }

   private Seq generateSessionStatsTable(final HttpServletRequest request) {
      int numSessions = this.store().getSessionList().size();
      Object var10000;
      if (numSessions > 0) {
         String sessionTableTag = "sessionstat";
         int sessionTablePage = BoxesRunTime.unboxToInt(scala.Option..MODULE$.apply(request.getParameter(sessionTableTag + ".page")).map((x$2) -> BoxesRunTime.boxToInteger($anonfun$generateSessionStatsTable$1(x$2))).getOrElse((JFunction0.mcI.sp)() -> 1));

         try {
            var10000 = new Some((new SessionStatsPagedTable(request, this.parent, this.store().getSessionList(), "sqlserver", org.apache.spark.ui.UIUtils..MODULE$.prependBaseUri(request, this.parent.basePath(), org.apache.spark.ui.UIUtils..MODULE$.prependBaseUri$default$3()), sessionTableTag)).table(sessionTablePage));
         } catch (Throwable var22) {
            if (!(var22 instanceof IllegalArgumentException ? true : var22 instanceof IndexOutOfBoundsException)) {
               throw var22;
            }

            MetaData $md = .MODULE$;
            MetaData var23 = new UnprefixedAttribute("class", new Text("alert alert-error"), $md);
            TopScope var10007 = scala.xml.TopScope..MODULE$;
            NodeSeq var10009 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("\n            "));
            Null var10015 = .MODULE$;
            TopScope var10016 = scala.xml.TopScope..MODULE$;
            NodeSeq var10018 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("Error while rendering job table:"));
            $buf.$amp$plus(new Elem((String)null, "p", var10015, var10016, false, var10018.seqToNodeSeq($buf)));
            $buf.$amp$plus(new Text("\n            "));
            var10015 = .MODULE$;
            var10016 = scala.xml.TopScope..MODULE$;
            var10018 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("\n              "));
            $buf.$amp$plus(org.apache.spark.util.Utils..MODULE$.exceptionString(var22));
            $buf.$amp$plus(new Text("\n            "));
            $buf.$amp$plus(new Elem((String)null, "pre", var10015, var10016, false, var10018.seqToNodeSeq($buf)));
            $buf.$amp$plus(new Text("\n          "));
            var10000 = new Some(new Elem((String)null, "div", var23, var10007, false, var10009.seqToNodeSeq($buf)));
         }
      } else {
         var10000 = scala.None..MODULE$;
      }

      Option table = (Option)var10000;
      MetaData $md = .MODULE$;
      MetaData var24 = new UnprefixedAttribute("onClick", new Text("collapseTable('collapse-aggregated-sessionstat',\n                'aggregated-sessionstat')"), $md);
      var24 = new UnprefixedAttribute("class", new Text("collapse-aggregated-sessionstat collapse-table"), var24);
      var24 = new UnprefixedAttribute("id", new Text("sessionstat"), var24);
      TopScope var10005 = scala.xml.TopScope..MODULE$;
      NodeSeq var30 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n      "));
      Null var10013 = .MODULE$;
      TopScope var10014 = scala.xml.TopScope..MODULE$;
      NodeSeq var33 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n        "));
      MetaData $md = .MODULE$;
      MetaData var27 = new UnprefixedAttribute("class", new Text("collapse-table-arrow arrow-open"), $md);
      $buf.$amp$plus(new Elem((String)null, "span", var27, scala.xml.TopScope..MODULE$, false, scala.collection.immutable.Nil..MODULE$));
      $buf.$amp$plus(new Text("\n        "));
      Null var10022 = .MODULE$;
      TopScope var10023 = scala.xml.TopScope..MODULE$;
      NodeSeq var10025 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("Session Statistics ("));
      $buf.$amp$plus(BoxesRunTime.boxToInteger(numSessions));
      $buf.$amp$plus(new Text(")"));
      $buf.$amp$plus(new Elem((String)null, "a", var10022, var10023, false, var10025.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      $buf.$amp$plus(new Elem((String)null, "h4", var10013, var10014, false, var33.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n    "));
      Elem var29 = new Elem((String)null, "span", var24, var10005, false, var30.seqToNodeSeq($buf));
      MetaData $md = .MODULE$;
      MetaData var28 = new UnprefixedAttribute("class", new Text("aggregated-sessionstat collapsible-table"), $md);
      TopScope var10006 = scala.xml.TopScope..MODULE$;
      NodeSeq var10008 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n        "));
      $buf.$amp$plus(table.getOrElse(() -> "No statistics have been generated yet."));
      $buf.$amp$plus(new Text("\n      "));
      NodeSeq content = var29.$plus$plus(new Elem((String)null, "div", var28, var10006, false, var10008.seqToNodeSeq($buf)));
      return content;
   }

   // $FF: synthetic method
   public static final int $anonfun$generateSQLStatsTable$1(final String x$1) {
      return scala.collection.StringOps..MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(x$1));
   }

   // $FF: synthetic method
   public static final int $anonfun$generateSessionStatsTable$1(final String x$2) {
      return scala.collection.StringOps..MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(x$2));
   }

   public ThriftServerPage(final ThriftServerTab parent) {
      super("");
      this.parent = parent;
      Logging.$init$(this);
      this.store = parent.store();
      this.startTime = parent.startTime();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
