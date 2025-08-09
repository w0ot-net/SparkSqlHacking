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
import scala.Predef.;
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

@ScalaSignature(
   bytes = "\u0006\u0005E4QAC\u0006\u0001\u0017eA\u0001\"\n\u0001\u0003\u0002\u0003\u0006Ia\n\u0005\u0006W\u0001!\t\u0001\f\u0005\b_\u0001\u0011\r\u0011\"\u00011\u0011\u0019!\u0004\u0001)A\u0005c!9Q\u0007\u0001b\u0001\n\u00131\u0004BB \u0001A\u0003%q\u0007C\u0003A\u0001\u0011\u0005\u0011\tC\u0003c\u0001\u0011%1\rC\u0003e\u0001\u0011%QMA\fUQJLg\r^*feZ,'oU3tg&|g\u000eU1hK*\u0011A\"D\u0001\u0003k&T!AD\b\u0002\u0019QD'/\u001b4ug\u0016\u0014h/\u001a:\u000b\u0005A\t\u0012\u0001\u00025jm\u0016T!AE\n\u0002\u0007M\fHN\u0003\u0002\u0015+\u0005)1\u000f]1sW*\u0011acF\u0001\u0007CB\f7\r[3\u000b\u0003a\t1a\u001c:h'\r\u0001!d\b\t\u00037ui\u0011\u0001\b\u0006\u0003\u0019MI!A\b\u000f\u0003\u0013]+'-V%QC\u001e,\u0007C\u0001\u0011$\u001b\u0005\t#B\u0001\u0012\u0014\u0003!Ig\u000e^3s]\u0006d\u0017B\u0001\u0013\"\u0005\u001daunZ4j]\u001e\fa\u0001]1sK:$8\u0001\u0001\t\u0003Q%j\u0011aC\u0005\u0003U-\u0011q\u0002\u00165sS\u001a$8+\u001a:wKJ$\u0016MY\u0001\u0007y%t\u0017\u000e\u001e \u0015\u00055r\u0003C\u0001\u0015\u0001\u0011\u0015)#\u00011\u0001(\u0003\u0015\u0019Ho\u001c:f+\u0005\t\u0004C\u0001\u00153\u0013\t\u00194BA\u0010ISZ,G\u000b\u001b:jMR\u001cVM\u001d<feJ\n\u0005\u000f]*uCR,8o\u0015;pe\u0016\faa\u001d;pe\u0016\u0004\u0013!C:uCJ$H+[7f+\u00059\u0004C\u0001\u001d>\u001b\u0005I$B\u0001\u001e<\u0003\u0011)H/\u001b7\u000b\u0003q\nAA[1wC&\u0011a(\u000f\u0002\u0005\t\u0006$X-\u0001\u0006ti\u0006\u0014H\u000fV5nK\u0002\naA]3oI\u0016\u0014HC\u0001\"W!\r\u0019U\n\u0015\b\u0003\t*s!!\u0012%\u000e\u0003\u0019S!a\u0012\u0014\u0002\rq\u0012xn\u001c;?\u0013\u0005I\u0015!B:dC2\f\u0017BA&M\u0003\u001d\u0001\u0018mY6bO\u0016T\u0011!S\u0005\u0003\u001d>\u00131aU3r\u0015\tYE\n\u0005\u0002R)6\t!K\u0003\u0002T\u0019\u0006\u0019\u00010\u001c7\n\u0005U\u0013&\u0001\u0002(pI\u0016DQaV\u0004A\u0002a\u000bqA]3rk\u0016\u001cH\u000f\u0005\u0002ZA6\t!L\u0003\u0002\\9\u0006!\u0001\u000e\u001e;q\u0015\tif,A\u0004tKJ4H.\u001a;\u000b\u0003}\u000bqA[1lCJ$\u0018-\u0003\u0002b5\n\u0011\u0002\n\u001e;q'\u0016\u0014h\u000f\\3u%\u0016\fX/Z:u\u0003I9WM\\3sCR,')Y:jGN#\u0018\r^:\u0015\u0003\t\u000bQcZ3oKJ\fG/Z*R\u0019N#\u0018\r^:UC\ndW\rF\u0002CM\u001eDQaV\u0005A\u0002aCQ\u0001[\u0005A\u0002%\f\u0011b]3tg&|g.\u0013#\u0011\u0005)tgBA6m!\t)E*\u0003\u0002n\u0019\u00061\u0001K]3eK\u001aL!a\u001c9\u0003\rM#(/\u001b8h\u0015\tiG\n"
)
public class ThriftServerSessionPage extends WebUIPage implements Logging {
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

   public HiveThriftServer2AppStatusStore store() {
      return this.store;
   }

   private Date startTime() {
      return this.startTime;
   }

   public Seq render(final HttpServletRequest request) {
      String parameterId = request.getParameter("id");
      .MODULE$.require(parameterId != null && scala.collection.StringOps..MODULE$.nonEmpty$extension(.MODULE$.augmentString(parameterId)), () -> "Missing id parameter");
      synchronized(this.store()){}

      Seq var5;
      try {
         SessionInfo sessionStat = (SessionInfo)this.store().getSession(parameterId).orNull(scala..less.colon.less..MODULE$.refl());
         .MODULE$.require(sessionStat != null, () -> "Invalid sessionID[" + parameterId + "]");
         IterableOps var10000 = (IterableOps)this.generateBasicStats().$plus$plus(new Elem((String)null, "br", scala.xml.Null..MODULE$, scala.xml.TopScope..MODULE$, true, scala.collection.immutable.Nil..MODULE$));
         Null var10005 = scala.xml.Null..MODULE$;
         TopScope var10006 = scala.xml.TopScope..MODULE$;
         NodeSeq var10008 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n        User "));
         $buf.$amp$plus(sessionStat.userName());
         $buf.$amp$plus(new Text(",\n        IP "));
         $buf.$amp$plus(sessionStat.ip());
         $buf.$amp$plus(new Text(",\n        Session created at "));
         $buf.$amp$plus(org.apache.spark.ui.UIUtils..MODULE$.formatDate(sessionStat.startTimestamp()));
         $buf.$amp$plus(new Text(",\n        Total run "));
         $buf.$amp$plus(BoxesRunTime.boxToLong(sessionStat.totalExecution()));
         $buf.$amp$plus(new Text(" SQL\n        "));
         var5 = (Seq)((IterableOps)var10000.$plus$plus(new Elem((String)null, "h4", var10005, var10006, false, var10008.seqToNodeSeq($buf)))).$plus$plus(this.generateSQLStatsTable(request, sessionStat.sessionId()));
      } catch (Throwable var9) {
         throw var9;
      }

      return org.apache.spark.ui.UIUtils..MODULE$.headerSparkPage(request, "JDBC/ODBC Session", () -> var5, this.parent, org.apache.spark.ui.UIUtils..MODULE$.headerSparkPage$default$5(), org.apache.spark.ui.UIUtils..MODULE$.headerSparkPage$default$6(), org.apache.spark.ui.UIUtils..MODULE$.headerSparkPage$default$7());
   }

   private Seq generateBasicStats() {
      long timeSinceStart = System.currentTimeMillis() - this.startTime().getTime();
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var9 = new UnprefixedAttribute("class", new Text("list-unstyled"), $md);
      TopScope var10005 = scala.xml.TopScope..MODULE$;
      NodeSeq var10007 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n      "));
      Null var10013 = scala.xml.Null..MODULE$;
      TopScope var10014 = scala.xml.TopScope..MODULE$;
      NodeSeq var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n        "));
      Null var10022 = scala.xml.Null..MODULE$;
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
      var10013 = scala.xml.Null..MODULE$;
      var10014 = scala.xml.TopScope..MODULE$;
      var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n        "));
      var10022 = scala.xml.Null..MODULE$;
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

   private Seq generateSQLStatsTable(final HttpServletRequest request, final String sessionID) {
      Seq executionList = (Seq)this.store().getExecutionList().filter((x$1) -> BoxesRunTime.boxToBoolean($anonfun$generateSQLStatsTable$1(sessionID, x$1)));
      int numStatement = executionList.size();
      Object var10000;
      if (numStatement > 0) {
         String sqlTableTag = "sqlsessionstat";
         int sqlTablePage = BoxesRunTime.unboxToInt(scala.Option..MODULE$.apply(request.getParameter(sqlTableTag + ".page")).map((x$2) -> BoxesRunTime.boxToInteger($anonfun$generateSQLStatsTable$2(x$2))).getOrElse((JFunction0.mcI.sp)() -> 1));

         try {
            var10000 = new Some((new SqlStatsPagedTable(request, this.parent, executionList, "sqlserver/session", org.apache.spark.ui.UIUtils..MODULE$.prependBaseUri(request, this.parent.basePath(), org.apache.spark.ui.UIUtils..MODULE$.prependBaseUri$default$3()), sqlTableTag)).table(sqlTablePage));
         } catch (Throwable var24) {
            if (!(var24 instanceof IllegalArgumentException ? true : var24 instanceof IndexOutOfBoundsException)) {
               throw var24;
            }

            MetaData $md = scala.xml.Null..MODULE$;
            MetaData var25 = new UnprefixedAttribute("class", new Text("alert alert-error"), $md);
            TopScope var10007 = scala.xml.TopScope..MODULE$;
            NodeSeq var10009 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("\n            "));
            Null var10015 = scala.xml.Null..MODULE$;
            TopScope var10016 = scala.xml.TopScope..MODULE$;
            NodeSeq var10018 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("Error while rendering job table:"));
            $buf.$amp$plus(new Elem((String)null, "p", var10015, var10016, false, var10018.seqToNodeSeq($buf)));
            $buf.$amp$plus(new Text("\n            "));
            var10015 = scala.xml.Null..MODULE$;
            var10016 = scala.xml.TopScope..MODULE$;
            var10018 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("\n              "));
            $buf.$amp$plus(org.apache.spark.util.Utils..MODULE$.exceptionString(var24));
            $buf.$amp$plus(new Text("\n            "));
            $buf.$amp$plus(new Elem((String)null, "pre", var10015, var10016, false, var10018.seqToNodeSeq($buf)));
            $buf.$amp$plus(new Text("\n          "));
            var10000 = new Some(new Elem((String)null, "div", var25, var10007, false, var10009.seqToNodeSeq($buf)));
         }
      } else {
         var10000 = scala.None..MODULE$;
      }

      Option table = (Option)var10000;
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var26 = new UnprefixedAttribute("onClick", new Text("collapseTable('collapse-aggregated-sqlsessionstat',\n                'aggregated-sqlsessionstat')"), $md);
      var26 = new UnprefixedAttribute("class", new Text("collapse-aggregated-sqlsessionstat collapse-table"), var26);
      var26 = new UnprefixedAttribute("id", new Text("sqlsessionstat"), var26);
      TopScope var10005 = scala.xml.TopScope..MODULE$;
      NodeSeq var32 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n        "));
      Null var10013 = scala.xml.Null..MODULE$;
      TopScope var10014 = scala.xml.TopScope..MODULE$;
      NodeSeq var35 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n          "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var29 = new UnprefixedAttribute("class", new Text("collapse-table-arrow arrow-open"), $md);
      $buf.$amp$plus(new Elem((String)null, "span", var29, scala.xml.TopScope..MODULE$, false, scala.collection.immutable.Nil..MODULE$));
      $buf.$amp$plus(new Text("\n          "));
      Null var10022 = scala.xml.Null..MODULE$;
      TopScope var10023 = scala.xml.TopScope..MODULE$;
      NodeSeq var10025 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("SQL Statistics"));
      $buf.$amp$plus(new Elem((String)null, "a", var10022, var10023, false, var10025.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n        "));
      $buf.$amp$plus(new Elem((String)null, "h4", var10013, var10014, false, var35.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      Elem var31 = new Elem((String)null, "span", var26, var10005, false, var32.seqToNodeSeq($buf));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var30 = new UnprefixedAttribute("class", new Text("aggregated-sqlsessionstat collapsible-table"), $md);
      TopScope var10006 = scala.xml.TopScope..MODULE$;
      NodeSeq var10008 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n          "));
      $buf.$amp$plus(table.getOrElse(() -> "No statistics have been generated yet."));
      $buf.$amp$plus(new Text("\n        "));
      NodeSeq content = var31.$plus$plus(new Elem((String)null, "div", var30, var10006, false, var10008.seqToNodeSeq($buf)));
      return content;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$generateSQLStatsTable$1(final String sessionID$1, final ExecutionInfo x$1) {
      boolean var3;
      label23: {
         String var10000 = x$1.sessionId();
         if (var10000 == null) {
            if (sessionID$1 == null) {
               break label23;
            }
         } else if (var10000.equals(sessionID$1)) {
            break label23;
         }

         var3 = false;
         return var3;
      }

      var3 = true;
      return var3;
   }

   // $FF: synthetic method
   public static final int $anonfun$generateSQLStatsTable$2(final String x$2) {
      return scala.collection.StringOps..MODULE$.toInt$extension(.MODULE$.augmentString(x$2));
   }

   public ThriftServerSessionPage(final ThriftServerTab parent) {
      super("session");
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
