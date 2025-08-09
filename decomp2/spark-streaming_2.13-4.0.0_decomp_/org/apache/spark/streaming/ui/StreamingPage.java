package org.apache.spark.streaming.ui;

import jakarta.servlet.http.HttpServletRequest;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.ui.GraphUIData;
import org.apache.spark.ui.JsCollector;
import org.apache.spark.ui.WebUIPage;
import org.apache.spark.ui.UIUtils.;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.SeqOps;
import scala.collection.StringOps;
import scala.collection.immutable.List;
import scala.collection.immutable.Seq;
import scala.collection.mutable.ListBuffer;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;
import scala.runtime.java8.JFunction2;
import scala.xml.Elem;
import scala.xml.MetaData;
import scala.xml.NodeBuffer;
import scala.xml.NodeSeq;
import scala.xml.Null;
import scala.xml.Text;
import scala.xml.TopScope;
import scala.xml.UnprefixedAttribute;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015f!B\u000e\u001d\u0001q1\u0003\u0002\u0003\u001a\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u001b\t\u000ba\u0002A\u0011A\u001d\t\u000fq\u0002!\u0019!C\u0005{!1\u0011\t\u0001Q\u0001\nyBQA\u0011\u0001\u0005\n\rCQA\u0013\u0001\u0005\u0002-CQA\u001b\u0001\u0005\n-DQ!\u001c\u0001\u0005\n9DQ\u0001\u001d\u0001\u0005\nEDQA\u001d\u0001\u0005\nMDQa\u001e\u0001\u0005\naDQA\u001f\u0001\u0005\nmDQ! \u0001\u0005\nyDq!a\u0007\u0001\t\u0013\ti\u0002C\u0004\u0002B\u0001!I!a\u0011\t\u000f\u0005\u001d\u0004\u0001\"\u0003\u0002j\u001dA\u0011Q\u000e\u000f\t\u0002q\tyGB\u0004\u001c9!\u0005A$!\u001d\t\ra\u0012B\u0011AA=\u0011%\tYH\u0005b\u0001\n\u0003\ti\b\u0003\u0005\u0002\u000eJ\u0001\u000b\u0011BA@\u0011%\tyI\u0005b\u0001\n\u0003\ti\b\u0003\u0005\u0002\u0012J\u0001\u000b\u0011BA@\u0011%\t\u0019J\u0005b\u0001\n\u0003\ti\b\u0003\u0005\u0002\u0016J\u0001\u000b\u0011BA@\u0011\u001d\t9J\u0005C\u0001\u00033\u0013Qb\u0015;sK\u0006l\u0017N\\4QC\u001e,'BA\u000f\u001f\u0003\t)\u0018N\u0003\u0002 A\u0005I1\u000f\u001e:fC6Lgn\u001a\u0006\u0003C\t\nQa\u001d9be.T!a\t\u0013\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005)\u0013aA8sON\u0019\u0001a\n\u0017\u0011\u0005!RS\"A\u0015\u000b\u0005u\u0001\u0013BA\u0016*\u0005%9VMY+J!\u0006<W\r\u0005\u0002.a5\taF\u0003\u00020A\u0005A\u0011N\u001c;fe:\fG.\u0003\u00022]\t9Aj\\4hS:<\u0017A\u00029be\u0016tGo\u0001\u0001\u0011\u0005U2T\"\u0001\u000f\n\u0005]b\"\u0001D*ue\u0016\fW.\u001b8h)\u0006\u0014\u0017A\u0002\u001fj]&$h\b\u0006\u0002;wA\u0011Q\u0007\u0001\u0005\u0006e\t\u0001\r\u0001N\u0001\tY&\u001cH/\u001a8feV\ta\b\u0005\u00026\u007f%\u0011\u0001\t\b\u0002\u001d'R\u0014X-Y7j]\u001eTuN\u0019)s_\u001e\u0014Xm]:MSN$XM\\3s\u0003%a\u0017n\u001d;f]\u0016\u0014\b%A\u0005ti\u0006\u0014H\u000fV5nKV\tA\t\u0005\u0002F\u00116\taIC\u0001H\u0003\u0015\u00198-\u00197b\u0013\tIeI\u0001\u0003M_:<\u0017A\u0002:f]\u0012,'\u000f\u0006\u0002M=B\u0019Q*\u0016-\u000f\u00059\u001bfBA(S\u001b\u0005\u0001&BA)4\u0003\u0019a$o\\8u}%\tq)\u0003\u0002U\r\u00069\u0001/Y2lC\u001e,\u0017B\u0001,X\u0005\r\u0019V-\u001d\u0006\u0003)\u001a\u0003\"!\u0017/\u000e\u0003iS!a\u0017$\u0002\u0007alG.\u0003\u0002^5\n!aj\u001c3f\u0011\u0015yf\u00011\u0001a\u0003\u001d\u0011X-];fgR\u0004\"!\u00195\u000e\u0003\tT!a\u00193\u0002\t!$H\u000f\u001d\u0006\u0003K\u001a\fqa]3sm2,GOC\u0001h\u0003\u001dQ\u0017m[1si\u0006L!!\u001b2\u0003%!#H\u000f]*feZdW\r\u001e*fcV,7\u000f^\u0001\u0016O\u0016tWM]1uK2{\u0017\r\u001a*fg>,(oY3t)\taE\u000eC\u0003`\u000f\u0001\u0007\u0001-A\u0010hK:,'/\u0019;f\u001f:\u001cE.[2l)&lW\r\\5oK\u001a+hn\u0019;j_:$\"\u0001T8\t\u000b}C\u0001\u0019\u00011\u0002#\u001d,g.\u001a:bi\u0016\u0014\u0015m]5d\u0013:4w\u000eF\u0001M\u0003=9WM\\3sCR,G+[7f\u001b\u0006\u0004HC\u0001'u\u0011\u0015)(\u00021\u0001w\u0003\u0015!\u0018.\\3t!\riU\u000bR\u0001\u0017O\u0016tWM]1uKRKW.\u001a+jaN#(/\u001b8hgR\u0011A*\u001f\u0005\u0006k.\u0001\rA^\u0001\u0012O\u0016tWM]1uKN#\u0018\r\u001e+bE2,GC\u0001'}\u0011\u0015yF\u00021\u0001a\u0003i9WM\\3sCR,\u0017J\u001c9vi\u0012\u001bFO]3b[N$\u0016M\u00197f)!au0!\u0003\u0002\u000e\u0005E\u0001bBA\u0001\u001b\u0001\u0007\u00111A\u0001\fUN\u001cu\u000e\u001c7fGR|'\u000fE\u0002)\u0003\u000bI1!a\u0002*\u0005-Q5oQ8mY\u0016\u001cGo\u001c:\t\r\u0005-Q\u00021\u0001E\u0003\u0011i\u0017N\u001c-\t\r\u0005=Q\u00021\u0001E\u0003\u0011i\u0017\r\u001f-\t\u000f\u0005MQ\u00021\u0001\u0002\u0016\u0005!Q.\u001b8Z!\r)\u0015qC\u0005\u0004\u000331%A\u0002#pk\ndW-A\fhK:,'/\u0019;f\u0013:\u0004X\u000f\u001e#TiJ,\u0017-\u001c*poRyA*a\b\u0002\"\u0005-\u0012qGA\u001d\u0003w\ti\u0004C\u0004\u0002\u00029\u0001\r!a\u0001\t\u000f\u0005\rb\u00021\u0001\u0002&\u0005A1\u000f\u001e:fC6LE\rE\u0002F\u0003OI1!!\u000bG\u0005\rIe\u000e\u001e\u0005\b\u0003[q\u0001\u0019AA\u0018\u0003-\u0011XmY8sIJ\u000bG/Z:\u0011\t5+\u0016\u0011\u0007\t\u0007\u000b\u0006MB)!\u0006\n\u0007\u0005UbI\u0001\u0004UkBdWM\r\u0005\u0007\u0003\u0017q\u0001\u0019\u0001#\t\r\u0005=a\u00021\u0001E\u0011\u001d\t\u0019B\u0004a\u0001\u0003+Aq!a\u0010\u000f\u0001\u0004\t)\"\u0001\u0003nCbL\u0016AD:ue\u0016\fW.\u001b8h)\u0006\u0014G.\u001a\u000b\b\u0019\u0006\u0015\u0013qIA*\u0011\u0015yv\u00021\u0001a\u0011\u001d\tIe\u0004a\u0001\u0003\u0017\nqAY1uG\",7\u000f\u0005\u0003N+\u00065\u0003cA\u001b\u0002P%\u0019\u0011\u0011\u000b\u000f\u0003\u0017\t\u000bGo\u00195V\u0013\u0012\u000bG/\u0019\u0005\b\u0003+z\u0001\u0019AA,\u0003!!\u0018M\u00197f)\u0006<\u0007\u0003BA-\u0003CrA!a\u0017\u0002^A\u0011qJR\u0005\u0004\u0003?2\u0015A\u0002)sK\u0012,g-\u0003\u0003\u0002d\u0005\u0015$AB*ue&twMC\u0002\u0002`\u0019\u000bqcZ3oKJ\fG/\u001a\"bi\u000eDG*[:u)\u0006\u0014G.Z:\u0015\u00071\u000bY\u0007C\u0003`!\u0001\u0007\u0001-A\u0007TiJ,\u0017-\\5oOB\u000bw-\u001a\t\u0003kI\u00192AEA:!\r)\u0015QO\u0005\u0004\u0003o2%AB!osJ+g\r\u0006\u0002\u0002p\u0005I\"\tT!D\u0017~\u0013\u0016j\u0012%U?R\u0013\u0016*\u0011(H\u0019\u0016{\u0006\nV'M+\t\ty\b\u0005\u0003\u0002\u0002\u0006-UBAAB\u0015\u0011\t))a\"\u0002\t1\fgn\u001a\u0006\u0003\u0003\u0013\u000bAA[1wC&!\u00111MAB\u0003i\u0011E*Q\"L?JKu\t\u0013+`)JK\u0015IT$M\u000b~CE+\u0014'!\u0003a\u0011E*Q\"L?\u0012{uKT0U%&\u000bej\u0012'F?\"#V\nT\u0001\u001a\u00052\u000b5iS0E\u001f^su\f\u0016*J\u0003:;E*R0I)6c\u0005%A\u0005f[B$\u0018pQ3mY\u0006QQ-\u001c9us\u000e+G\u000e\u001c\u0011\u0002)\u0019|'/\\1u\tV\u0014\u0018\r^5p]>\u0003H/[8o)\u0011\t9&a'\t\u000f\u0005u%\u00041\u0001\u0002 \u0006AQn](qi&|g\u000e\u0005\u0003F\u0003C#\u0015bAAR\r\n1q\n\u001d;j_:\u0004"
)
public class StreamingPage extends WebUIPage implements Logging {
   private final StreamingTab parent;
   private final StreamingJobProgressListener listener;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static String formatDurationOption(final Option msOption) {
      return StreamingPage$.MODULE$.formatDurationOption(msOption);
   }

   public static String emptyCell() {
      return StreamingPage$.MODULE$.emptyCell();
   }

   public static String BLACK_DOWN_TRIANGLE_HTML() {
      return StreamingPage$.MODULE$.BLACK_DOWN_TRIANGLE_HTML();
   }

   public static String BLACK_RIGHT_TRIANGLE_HTML() {
      return StreamingPage$.MODULE$.BLACK_RIGHT_TRIANGLE_HTML();
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

   private StreamingJobProgressListener listener() {
      return this.listener;
   }

   private long startTime() {
      return this.listener().startTime();
   }

   public Seq render(final HttpServletRequest request) {
      Seq resources = this.generateLoadResources(request);
      Seq onClickTimelineFunc = this.generateOnClickTimelineFunction(request);
      Seq basicInfo = this.generateBasicInfo();
      IterableOps var10000 = (IterableOps)((IterableOps)resources.$plus$plus(onClickTimelineFunc)).$plus$plus(basicInfo);
      synchronized(this.listener()){}

      Seq var7;
      try {
         var7 = (Seq)this.generateStatTable(request).$plus$plus(this.generateBatchListTables(request));
      } catch (Throwable var9) {
         throw var9;
      }

      Seq content = (Seq)var10000.$plus$plus(var7);
      return .MODULE$.headerSparkPage(request, "Streaming Statistics", () -> content, this.parent, .MODULE$.headerSparkPage$default$5(), .MODULE$.headerSparkPage$default$6(), .MODULE$.headerSparkPage$default$7());
   }

   private Seq generateLoadResources(final HttpServletRequest request) {
      NodeBuffer $buf = new NodeBuffer();
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var6 = new UnprefixedAttribute("src", .MODULE$.prependBaseUri(request, "/static/d3.min.js", .MODULE$.prependBaseUri$default$3()), $md);
      $buf.$amp$plus(new Elem((String)null, "script", var6, scala.xml.TopScope..MODULE$, false, scala.collection.immutable.Nil..MODULE$));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var7 = new UnprefixedAttribute("type", new Text("text/css"), $md);
      var7 = new UnprefixedAttribute("href", .MODULE$.prependBaseUri(request, "/static/streaming-page.css", .MODULE$.prependBaseUri$default$3()), var7);
      var7 = new UnprefixedAttribute("rel", new Text("stylesheet"), var7);
      $buf.$amp$plus(new Elem((String)null, "link", var7, scala.xml.TopScope..MODULE$, true, scala.collection.immutable.Nil..MODULE$));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var10 = new UnprefixedAttribute("src", .MODULE$.prependBaseUri(request, "/static/streaming-page.js", .MODULE$.prependBaseUri$default$3()), $md);
      var10 = new UnprefixedAttribute("type", new Text("module"), var10);
      $buf.$amp$plus(new Elem((String)null, "script", var10, scala.xml.TopScope..MODULE$, false, scala.collection.immutable.Nil..MODULE$));
      return scala.xml.NodeSeq..MODULE$.seqToNodeSeq($buf);
   }

   private Seq generateOnClickTimelineFunction(final HttpServletRequest request) {
      String imported = .MODULE$.formatImportJavaScript(request, "/static/streaming-page.js", scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"getOnClickTimelineFunction"})));
      String js = scala.collection.StringOps..MODULE$.stripMargin$extension(scala.Predef..MODULE$.augmentString("\n         |" + imported + "\n         |\n         |onClickTimeline = getOnClickTimelineFunction();\n         |"));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var6 = new UnprefixedAttribute("type", new Text("module"), $md);
      TopScope var10005 = scala.xml.TopScope..MODULE$;
      NodeSeq var10007 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(scala.xml.Unparsed..MODULE$.apply(js));
      return new Elem((String)null, "script", var6, var10005, false, var10007.seqToNodeSeq($buf));
   }

   private Seq generateBasicInfo() {
      long timeSinceStart = System.currentTimeMillis() - this.startTime();
      NodeBuffer $buf = new NodeBuffer();
      Null var10005 = scala.xml.Null..MODULE$;
      TopScope var10006 = scala.xml.TopScope..MODULE$;
      NodeSeq var10008 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("Running batches of\n      "));
      Null var10014 = scala.xml.Null..MODULE$;
      TopScope var10015 = scala.xml.TopScope..MODULE$;
      NodeSeq var10017 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n        "));
      $buf.$amp$plus(.MODULE$.formatDurationVerbose(this.listener().batchDuration()));
      $buf.$amp$plus(new Text("\n      "));
      $buf.$amp$plus(new Elem((String)null, "strong", var10014, var10015, false, var10017.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      for\n      "));
      var10014 = scala.xml.Null..MODULE$;
      var10015 = scala.xml.TopScope..MODULE$;
      var10017 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n        "));
      $buf.$amp$plus(.MODULE$.formatDurationVerbose(timeSinceStart));
      $buf.$amp$plus(new Text("\n      "));
      $buf.$amp$plus(new Elem((String)null, "strong", var10014, var10015, false, var10017.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      since\n      "));
      var10014 = scala.xml.Null..MODULE$;
      var10015 = scala.xml.TopScope..MODULE$;
      var10017 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n        "));
      $buf.$amp$plus(.MODULE$.formatDate(this.startTime()));
      $buf.$amp$plus(new Text("\n      "));
      $buf.$amp$plus(new Elem((String)null, "strong", var10014, var10015, false, var10017.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      ("));
      var10014 = scala.xml.Null..MODULE$;
      var10015 = scala.xml.TopScope..MODULE$;
      var10017 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(BoxesRunTime.boxToLong(this.listener().numTotalCompletedBatches()));
      $buf.$amp$plus(new Elem((String)null, "strong", var10014, var10015, false, var10017.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      completed batches, "));
      var10014 = scala.xml.Null..MODULE$;
      var10015 = scala.xml.TopScope..MODULE$;
      var10017 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(BoxesRunTime.boxToLong(this.listener().numTotalReceivedRecords()));
      $buf.$amp$plus(new Elem((String)null, "strong", var10014, var10015, false, var10017.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text(" records)\n    "));
      $buf.$amp$plus(new Elem((String)null, "div", var10005, var10006, false, var10008.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Elem((String)null, "br", scala.xml.Null..MODULE$, scala.xml.TopScope..MODULE$, true, scala.collection.immutable.Nil..MODULE$));
      return scala.xml.NodeSeq..MODULE$.seqToNodeSeq($buf);
   }

   private Seq generateTimeMap(final Seq times) {
      IterableOnceOps var10000 = (IterableOnceOps)times.map((time) -> $anonfun$generateTimeMap$1(this, BoxesRunTime.unboxToLong(time)));
      String js = "var timeFormat = {};\n" + var10000.mkString("\n");
      Null var10004 = scala.xml.Null..MODULE$;
      TopScope var10005 = scala.xml.TopScope..MODULE$;
      NodeSeq var10007 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(scala.xml.Unparsed..MODULE$.apply(js));
      return new Elem((String)null, "script", var10004, var10005, false, var10007.seqToNodeSeq($buf));
   }

   private Seq generateTimeTipStrings(final Seq times) {
      IterableOnceOps var10000 = (IterableOnceOps)times.map((time) -> $anonfun$generateTimeTipStrings$1(BoxesRunTime.unboxToLong(time)));
      String js = "var timeTipStrings = {};\n" + var10000.mkString("\n");
      Null var10004 = scala.xml.Null..MODULE$;
      TopScope var10005 = scala.xml.TopScope..MODULE$;
      NodeSeq var10007 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(scala.xml.Unparsed..MODULE$.apply(js));
      return new Elem((String)null, "script", var10004, var10005, false, var10007.seqToNodeSeq($buf));
   }

   private Seq generateStatTable(final HttpServletRequest request) {
      Seq batches = this.listener().retainedBatches();
      Seq batchTimes = (Seq)batches.map((x$6) -> BoxesRunTime.boxToLong($anonfun$generateStatTable$1(x$6)));
      long minBatchTime = batchTimes.isEmpty() ? this.startTime() : BoxesRunTime.unboxToLong(batchTimes.min(scala.math.Ordering.Long..MODULE$));
      long maxBatchTime = batchTimes.isEmpty() ? this.startTime() : BoxesRunTime.unboxToLong(batchTimes.max(scala.math.Ordering.Long..MODULE$));
      RecordRateUIData recordRateForAllStreams = new RecordRateUIData((Seq)batches.map((batchInfo) -> new Tuple2.mcJD.sp(batchInfo.batchTime().milliseconds(), (double)batchInfo.numRecords() * (double)1000.0F / (double)this.listener().batchDuration())));
      MillisecondsStatUIData schedulingDelay = new MillisecondsStatUIData((Seq)batches.flatMap((batchInfo) -> batchInfo.schedulingDelay().map((x$7) -> $anonfun$generateStatTable$4(batchInfo, BoxesRunTime.unboxToLong(x$7)))));
      MillisecondsStatUIData processingTime = new MillisecondsStatUIData((Seq)batches.flatMap((batchInfo) -> batchInfo.processingDelay().map((x$8) -> $anonfun$generateStatTable$6(batchInfo, BoxesRunTime.unboxToLong(x$8)))));
      MillisecondsStatUIData totalDelay = new MillisecondsStatUIData((Seq)batches.flatMap((batchInfo) -> batchInfo.totalDelay().map((x$9) -> $anonfun$generateStatTable$8(batchInfo, BoxesRunTime.unboxToLong(x$9)))));
      long _maxTime = BoxesRunTime.unboxToLong(schedulingDelay.max().flatMap((m1) -> $anonfun$generateStatTable$9(processingTime, totalDelay, BoxesRunTime.unboxToLong(m1))).getOrElse((JFunction0.mcJ.sp)() -> 0L));
      long minTime = 0L;
      Tuple2 var18 = UIUtils$.MODULE$.normalizeDuration(_maxTime);
      if (var18 != null) {
         double maxTime = var18._1$mcD$sp();
         TimeUnit normalizedUnit = (TimeUnit)var18._2();
         Tuple2 var17 = new Tuple2(BoxesRunTime.boxToDouble(maxTime), normalizedUnit);
         double maxTime = var17._1$mcD$sp();
         TimeUnit normalizedUnit = (TimeUnit)var17._2();
         String formattedUnit = UIUtils$.MODULE$.shortTimeUnitString(normalizedUnit);
         long maxRecordRate = BoxesRunTime.unboxToLong(recordRateForAllStreams.max().map((JFunction1.mcJD.sp)(x$11) -> (long)scala.runtime.RichDouble..MODULE$.ceil$extension(scala.Predef..MODULE$.doubleWrapper(x$11))).getOrElse((JFunction0.mcJ.sp)() -> 0L));
         long minRecordRate = 0L;
         double batchInterval = UIUtils$.MODULE$.convertToTimeUnit(this.listener().batchDuration(), normalizedUnit);
         JsCollector jsCollector = new JsCollector(request);
         GraphUIData graphUIDataForRecordRateOfAllStreams = new GraphUIData("all-stream-records-timeline", "all-stream-records-histogram", recordRateForAllStreams.data(), minBatchTime, maxBatchTime, (double)minRecordRate, (double)maxRecordRate, "records/sec", org.apache.spark.ui.GraphUIData..MODULE$.$lessinit$greater$default$9());
         graphUIDataForRecordRateOfAllStreams.generateDataJs(jsCollector);
         GraphUIData graphUIDataForSchedulingDelay = new GraphUIData("scheduling-delay-timeline", "scheduling-delay-histogram", schedulingDelay.timelineData(normalizedUnit), minBatchTime, maxBatchTime, (double)minTime, maxTime, formattedUnit, org.apache.spark.ui.GraphUIData..MODULE$.$lessinit$greater$default$9());
         graphUIDataForSchedulingDelay.generateDataJs(jsCollector);
         GraphUIData graphUIDataForProcessingTime = new GraphUIData("processing-time-timeline", "processing-time-histogram", processingTime.timelineData(normalizedUnit), minBatchTime, maxBatchTime, (double)minTime, maxTime, formattedUnit, new Some(BoxesRunTime.boxToDouble(batchInterval)));
         graphUIDataForProcessingTime.generateDataJs(jsCollector);
         GraphUIData graphUIDataForTotalDelay = new GraphUIData("total-delay-timeline", "total-delay-histogram", totalDelay.timelineData(normalizedUnit), minBatchTime, maxBatchTime, (double)minTime, maxTime, formattedUnit, org.apache.spark.ui.GraphUIData..MODULE$.$lessinit$greater$default$9());
         graphUIDataForTotalDelay.generateDataJs(jsCollector);
         boolean hasStream = this.listener().streamIds().nonEmpty();
         int numCompletedBatches = this.listener().retainedCompletedBatches().size();
         int numActiveBatches = batchTimes.length() - numCompletedBatches;
         int numReceivers = this.listener().numInactiveReceivers() + this.listener().numActiveReceivers();
         Null $md = scala.xml.Null..MODULE$;
         MetaData var111 = new UnprefixedAttribute("style", new Text("width: auto"), $md);
         var111 = new UnprefixedAttribute("class", new Text("table table-bordered"), var111);
         var111 = new UnprefixedAttribute("id", new Text("stat-table"), var111);
         Elem var10000 = new Elem;
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
         $buf.$amp$plus(new Text("\n          "));
         Null $md = scala.xml.Null..MODULE$;
         MetaData var114 = new UnprefixedAttribute("style", new Text("width: 160px;"), $md);
         $buf.$amp$plus(new Elem((String)null, "th", var114, scala.xml.TopScope..MODULE$, false, scala.collection.immutable.Nil..MODULE$));
         $buf.$amp$plus(new Text("\n          "));
         Null $md = scala.xml.Null..MODULE$;
         MetaData var115 = new UnprefixedAttribute("style", new Text("width: 492px;"), $md);
         TopScope var10032 = scala.xml.TopScope..MODULE$;
         NodeSeq var10034 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("Timelines (Last "));
         $buf.$amp$plus(BoxesRunTime.boxToInteger(batchTimes.length()));
         $buf.$amp$plus(new Text(" batches, "));
         $buf.$amp$plus(BoxesRunTime.boxToInteger(numActiveBatches));
         $buf.$amp$plus(new Text(" active, "));
         $buf.$amp$plus(BoxesRunTime.boxToInteger(numCompletedBatches));
         $buf.$amp$plus(new Text(" completed)"));
         $buf.$amp$plus(new Elem((String)null, "th", var115, var10032, false, var10034.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n          "));
         Null $md = scala.xml.Null..MODULE$;
         MetaData var116 = new UnprefixedAttribute("style", new Text("width: 350px;"), $md);
         var10032 = scala.xml.TopScope..MODULE$;
         var10034 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("Histograms"));
         $buf.$amp$plus(new Elem((String)null, "th", var116, var10032, false, var10034.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Elem((String)null, "tr", var10022, var10023, false, var10025.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n      "));
         $buf.$amp$plus(new Elem((String)null, "thead", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n      "));
         Elem var10009 = new Elem;
         var10013 = scala.xml.Null..MODULE$;
         var10014 = scala.xml.TopScope..MODULE$;
         var10016 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n        "));
         Elem var10018 = new Elem;
         var10022 = scala.xml.Null..MODULE$;
         var10023 = scala.xml.TopScope..MODULE$;
         var10025 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n          "));
         Null $md = scala.xml.Null..MODULE$;
         MetaData var117 = new UnprefixedAttribute("style", new Text("vertical-align: middle;"), $md);
         Elem var10027 = new Elem;
         var10032 = scala.xml.TopScope..MODULE$;
         var10034 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n            "));
         Null $md = scala.xml.Null..MODULE$;
         MetaData var118 = new UnprefixedAttribute("style", new Text("width: 160px;"), $md);
         Elem var10036 = new Elem;
         TopScope var10041 = scala.xml.TopScope..MODULE$;
         NodeSeq var10043 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n              "));
         Elem var10045 = new Elem;
         Null var10049 = scala.xml.Null..MODULE$;
         TopScope var10050 = scala.xml.TopScope..MODULE$;
         NodeSeq var10052 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n              "));
         Elem var10054;
         if (hasStream) {
            MetaData $md = scala.xml.Null..MODULE$;
            MetaData var119 = new UnprefixedAttribute("class", new Text("expand-input-rate"), $md);
            TopScope var10059 = scala.xml.TopScope..MODULE$;
            NodeSeq var10061 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("\n                    "));
            MetaData $md = scala.xml.Null..MODULE$;
            MetaData var120 = new UnprefixedAttribute("class", new Text("expand-input-rate-arrow arrow-closed"), $md);
            $buf.$amp$plus(new Elem((String)null, "span", var120, scala.xml.TopScope..MODULE$, false, scala.collection.immutable.Nil..MODULE$));
            $buf.$amp$plus(new Text("\n                    "));
            MetaData $md = scala.xml.Null..MODULE$;
            MetaData var121 = new UnprefixedAttribute("data-placement", new Text("top"), $md);
            var121 = new UnprefixedAttribute("title", new Text("Show/hide details of each receiver"), var121);
            var121 = new UnprefixedAttribute("data-toggle", new Text("tooltip"), var121);
            TopScope var10068 = scala.xml.TopScope..MODULE$;
            NodeSeq var10070 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("\n                      "));
            Null var10076 = scala.xml.Null..MODULE$;
            TopScope var10077 = scala.xml.TopScope..MODULE$;
            NodeSeq var10079 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("Input Rate"));
            $buf.$amp$plus(new Elem((String)null, "strong", var10076, var10077, false, var10079.seqToNodeSeq($buf)));
            $buf.$amp$plus(new Text("\n                    "));
            $buf.$amp$plus(new Elem((String)null, "a", var121, var10068, false, var10070.seqToNodeSeq($buf)));
            $buf.$amp$plus(new Text("\n                  "));
            var10054 = new Elem((String)null, "span", var119, var10059, false, var10061.seqToNodeSeq($buf));
         } else {
            Null var10058 = scala.xml.Null..MODULE$;
            TopScope var221 = scala.xml.TopScope..MODULE$;
            NodeSeq var225 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("Input Rate"));
            var10054 = new Elem((String)null, "strong", var10058, var221, false, var225.seqToNodeSeq($buf));
         }

         $buf.$amp$plus(var10054);
         $buf.$amp$plus(new Text("\n              "));
         var10045.<init>((String)null, "div", var10049, var10050, false, var10052.seqToNodeSeq($buf));
         $buf.$amp$plus(var10045);
         $buf.$amp$plus(new Text("\n              "));
         Object var193;
         if (numReceivers > 0) {
            var10049 = scala.xml.Null..MODULE$;
            var10050 = scala.xml.TopScope..MODULE$;
            var10052 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("Receivers: "));
            $buf.$amp$plus(BoxesRunTime.boxToInteger(this.listener().numActiveReceivers()));
            $buf.$amp$plus(new Text(" / "));
            $buf.$amp$plus(BoxesRunTime.boxToInteger(numReceivers));
            $buf.$amp$plus(new Text(" active"));
            var193 = new Elem((String)null, "div", var10049, var10050, false, var10052.seqToNodeSeq($buf));
         } else {
            var193 = BoxedUnit.UNIT;
         }

         $buf.$amp$plus(var193);
         $buf.$amp$plus(new Text("\n              "));
         var10049 = scala.xml.Null..MODULE$;
         var10050 = scala.xml.TopScope..MODULE$;
         var10052 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("Avg: "));
         $buf.$amp$plus(recordRateForAllStreams.formattedAvg());
         $buf.$amp$plus(new Text(" records/sec"));
         $buf.$amp$plus(new Elem((String)null, "div", var10049, var10050, false, var10052.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n            "));
         var10036.<init>((String)null, "div", var118, var10041, false, var10043.seqToNodeSeq($buf));
         $buf.$amp$plus(var10036);
         $buf.$amp$plus(new Text("\n          "));
         var10027.<init>((String)null, "td", var117, var10032, false, var10034.seqToNodeSeq($buf));
         $buf.$amp$plus(var10027);
         $buf.$amp$plus(new Text("\n          "));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var124 = new UnprefixedAttribute("class", new Text("timeline"), $md);
         var10032 = scala.xml.TopScope..MODULE$;
         var10034 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(graphUIDataForRecordRateOfAllStreams.generateTimelineHtml(jsCollector));
         $buf.$amp$plus(new Elem((String)null, "td", var124, var10032, false, var10034.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n          "));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var125 = new UnprefixedAttribute("class", new Text("histogram"), $md);
         var10032 = scala.xml.TopScope..MODULE$;
         var10034 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(graphUIDataForRecordRateOfAllStreams.generateHistogramHtml(jsCollector));
         $buf.$amp$plus(new Elem((String)null, "td", var125, var10032, false, var10034.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n        "));
         var10018.<init>((String)null, "tr", var10022, var10023, false, var10025.seqToNodeSeq($buf));
         $buf.$amp$plus(var10018);
         $buf.$amp$plus(new Text("\n      "));
         Object var144;
         if (hasStream) {
            MetaData $md = scala.xml.Null..MODULE$;
            MetaData var126 = new UnprefixedAttribute("style", new Text("display: none;"), $md);
            var126 = new UnprefixedAttribute("id", new Text("inputs-table"), var126);
            var10023 = scala.xml.TopScope..MODULE$;
            var10025 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("\n          "));
            MetaData $md = scala.xml.Null..MODULE$;
            MetaData var128 = new UnprefixedAttribute("colspan", new Text("3"), $md);
            var10032 = scala.xml.TopScope..MODULE$;
            var10034 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("\n            "));
            $buf.$amp$plus(this.generateInputDStreamsTable(jsCollector, minBatchTime, maxBatchTime, (double)minRecordRate));
            $buf.$amp$plus(new Text("\n          "));
            $buf.$amp$plus(new Elem((String)null, "td", var128, var10032, false, var10034.seqToNodeSeq($buf)));
            $buf.$amp$plus(new Text("\n        "));
            var144 = new Elem((String)null, "tr", var126, var10023, false, var10025.seqToNodeSeq($buf));
         } else {
            var144 = BoxedUnit.UNIT;
         }

         $buf.$amp$plus(var144);
         $buf.$amp$plus(new Text("\n        "));
         var10022 = scala.xml.Null..MODULE$;
         var10023 = scala.xml.TopScope..MODULE$;
         var10025 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n          "));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var129 = new UnprefixedAttribute("style", new Text("vertical-align: middle;"), $md);
         var10032 = scala.xml.TopScope..MODULE$;
         var10034 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n            "));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var130 = new UnprefixedAttribute("style", new Text("width: 160px;"), $md);
         var10041 = scala.xml.TopScope..MODULE$;
         var10043 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n              "));
         var10049 = scala.xml.Null..MODULE$;
         var10050 = scala.xml.TopScope..MODULE$;
         var10052 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         Null var218 = scala.xml.Null..MODULE$;
         TopScope var222 = scala.xml.TopScope..MODULE$;
         NodeSeq var226 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("Scheduling Delay "));
         $buf.$amp$plus(.MODULE$.tooltip("Time taken by Streaming scheduler to submit jobs of a batch", "top"));
         $buf.$amp$plus(new Elem((String)null, "strong", var218, var222, false, var226.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Elem((String)null, "div", var10049, var10050, false, var10052.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n              "));
         var10049 = scala.xml.Null..MODULE$;
         var10050 = scala.xml.TopScope..MODULE$;
         var10052 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("Avg: "));
         $buf.$amp$plus(schedulingDelay.formattedAvg());
         $buf.$amp$plus(new Elem((String)null, "div", var10049, var10050, false, var10052.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n            "));
         $buf.$amp$plus(new Elem((String)null, "div", var130, var10041, false, var10043.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n          "));
         $buf.$amp$plus(new Elem((String)null, "td", var129, var10032, false, var10034.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n          "));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var131 = new UnprefixedAttribute("class", new Text("timeline"), $md);
         var10032 = scala.xml.TopScope..MODULE$;
         var10034 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(graphUIDataForSchedulingDelay.generateTimelineHtml(jsCollector));
         $buf.$amp$plus(new Elem((String)null, "td", var131, var10032, false, var10034.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n          "));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var132 = new UnprefixedAttribute("class", new Text("histogram"), $md);
         var10032 = scala.xml.TopScope..MODULE$;
         var10034 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(graphUIDataForSchedulingDelay.generateHistogramHtml(jsCollector));
         $buf.$amp$plus(new Elem((String)null, "td", var132, var10032, false, var10034.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n        "));
         $buf.$amp$plus(new Elem((String)null, "tr", var10022, var10023, false, var10025.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n        "));
         var10022 = scala.xml.Null..MODULE$;
         var10023 = scala.xml.TopScope..MODULE$;
         var10025 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n          "));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var133 = new UnprefixedAttribute("style", new Text("vertical-align: middle;"), $md);
         var10032 = scala.xml.TopScope..MODULE$;
         var10034 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n            "));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var134 = new UnprefixedAttribute("style", new Text("width: 160px;"), $md);
         var10041 = scala.xml.TopScope..MODULE$;
         var10043 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n              "));
         var10049 = scala.xml.Null..MODULE$;
         var10050 = scala.xml.TopScope..MODULE$;
         var10052 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         var218 = scala.xml.Null..MODULE$;
         var222 = scala.xml.TopScope..MODULE$;
         var226 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("Processing Time "));
         $buf.$amp$plus(.MODULE$.tooltip("Time taken to process all jobs of a batch", "top"));
         $buf.$amp$plus(new Elem((String)null, "strong", var218, var222, false, var226.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Elem((String)null, "div", var10049, var10050, false, var10052.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n              "));
         var10049 = scala.xml.Null..MODULE$;
         var10050 = scala.xml.TopScope..MODULE$;
         var10052 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("Avg: "));
         $buf.$amp$plus(processingTime.formattedAvg());
         $buf.$amp$plus(new Elem((String)null, "div", var10049, var10050, false, var10052.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n            "));
         $buf.$amp$plus(new Elem((String)null, "div", var134, var10041, false, var10043.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n          "));
         $buf.$amp$plus(new Elem((String)null, "td", var133, var10032, false, var10034.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n          "));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var135 = new UnprefixedAttribute("class", new Text("timeline"), $md);
         var10032 = scala.xml.TopScope..MODULE$;
         var10034 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(graphUIDataForProcessingTime.generateTimelineHtml(jsCollector));
         $buf.$amp$plus(new Elem((String)null, "td", var135, var10032, false, var10034.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n          "));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var136 = new UnprefixedAttribute("class", new Text("histogram"), $md);
         var10032 = scala.xml.TopScope..MODULE$;
         var10034 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(graphUIDataForProcessingTime.generateHistogramHtml(jsCollector));
         $buf.$amp$plus(new Elem((String)null, "td", var136, var10032, false, var10034.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n        "));
         $buf.$amp$plus(new Elem((String)null, "tr", var10022, var10023, false, var10025.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n        "));
         var10022 = scala.xml.Null..MODULE$;
         var10023 = scala.xml.TopScope..MODULE$;
         var10025 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n          "));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var137 = new UnprefixedAttribute("style", new Text("vertical-align: middle;"), $md);
         var10032 = scala.xml.TopScope..MODULE$;
         var10034 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n            "));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var138 = new UnprefixedAttribute("style", new Text("width: 160px;"), $md);
         var10041 = scala.xml.TopScope..MODULE$;
         var10043 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n              "));
         var10049 = scala.xml.Null..MODULE$;
         var10050 = scala.xml.TopScope..MODULE$;
         var10052 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         var218 = scala.xml.Null..MODULE$;
         var222 = scala.xml.TopScope..MODULE$;
         var226 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("Total Delay "));
         $buf.$amp$plus(.MODULE$.tooltip("Total time taken to handle a batch", "top"));
         $buf.$amp$plus(new Elem((String)null, "strong", var218, var222, false, var226.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Elem((String)null, "div", var10049, var10050, false, var10052.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n              "));
         var10049 = scala.xml.Null..MODULE$;
         var10050 = scala.xml.TopScope..MODULE$;
         var10052 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("Avg: "));
         $buf.$amp$plus(totalDelay.formattedAvg());
         $buf.$amp$plus(new Elem((String)null, "div", var10049, var10050, false, var10052.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n            "));
         $buf.$amp$plus(new Elem((String)null, "div", var138, var10041, false, var10043.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n          "));
         $buf.$amp$plus(new Elem((String)null, "td", var137, var10032, false, var10034.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n          "));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var139 = new UnprefixedAttribute("class", new Text("timeline"), $md);
         var10032 = scala.xml.TopScope..MODULE$;
         var10034 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(graphUIDataForTotalDelay.generateTimelineHtml(jsCollector));
         $buf.$amp$plus(new Elem((String)null, "td", var139, var10032, false, var10034.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n          "));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var140 = new UnprefixedAttribute("class", new Text("histogram"), $md);
         var10032 = scala.xml.TopScope..MODULE$;
         var10034 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(graphUIDataForTotalDelay.generateHistogramHtml(jsCollector));
         $buf.$amp$plus(new Elem((String)null, "td", var140, var10032, false, var10034.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n        "));
         $buf.$amp$plus(new Elem((String)null, "tr", var10022, var10023, false, var10025.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n      "));
         var10009.<init>((String)null, "tbody", var10013, var10014, false, var10016.seqToNodeSeq($buf));
         $buf.$amp$plus(var10009);
         $buf.$amp$plus(new Text("\n    "));
         var10000.<init>((String)null, "table", var111, var10005, false, var10007.seqToNodeSeq($buf));
         Elem table = var10000;
         return (Seq)((IterableOps)((IterableOps)this.generateTimeMap(batchTimes).$plus$plus(this.generateTimeTipStrings(batchTimes))).$plus$plus(table)).$plus$plus(jsCollector.toHtml());
      } else {
         throw new MatchError(var18);
      }
   }

   private Seq generateInputDStreamsTable(final JsCollector jsCollector, final long minX, final long maxX, final double minY) {
      long maxYCalculated = BoxesRunTime.unboxToLong(((IterableOnceOps)this.listener().receivedRecordRateWithBatchTime().values().flatMap((x0$1) -> (Seq)x0$1.map((x0$2) -> BoxesRunTime.boxToDouble($anonfun$generateInputDStreamsTable$2(x0$2))))).reduceOption((JFunction2.mcDDD.sp)(x, y) -> scala.math.package..MODULE$.max(x, y)).map((JFunction1.mcJD.sp)(x$12) -> (long)scala.runtime.RichDouble..MODULE$.ceil$extension(scala.Predef..MODULE$.doubleWrapper(x$12))).getOrElse((JFunction0.mcJ.sp)() -> 0L));
      Seq content = ((List)this.listener().receivedRecordRateWithBatchTime().toList().sortBy((x$13) -> BoxesRunTime.boxToInteger($anonfun$generateInputDStreamsTable$6(x$13)), scala.math.Ordering.Int..MODULE$)).flatMap((x0$3) -> {
         if (x0$3 != null) {
            int streamId = x0$3._1$mcI$sp();
            Seq recordRates = (Seq)x0$3._2();
            return this.generateInputDStreamRow(jsCollector, streamId, recordRates, minX, maxX, minY, (double)maxYCalculated);
         } else {
            throw new MatchError(x0$3);
         }
      });
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var30 = new UnprefixedAttribute("style", new Text("width: auto"), $md);
      var30 = new UnprefixedAttribute("class", new Text("table table-bordered"), var30);
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
      $buf.$amp$plus(new Text("\n          "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var32 = new UnprefixedAttribute("style", new Text("width: 151px;"), $md);
      $buf.$amp$plus(new Elem((String)null, "th", var32, scala.xml.TopScope..MODULE$, false, scala.collection.immutable.Nil..MODULE$));
      $buf.$amp$plus(new Text("\n          "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var33 = new UnprefixedAttribute("style", new Text("width: 167px; padding: 8px 0 8px 0"), $md);
      TopScope var10032 = scala.xml.TopScope..MODULE$;
      NodeSeq var10034 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var34 = new UnprefixedAttribute("style", new Text("margin: 0 8px 0 8px"), $md);
      TopScope var10041 = scala.xml.TopScope..MODULE$;
      NodeSeq var10043 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("Status"));
      $buf.$amp$plus(new Elem((String)null, "div", var34, var10041, false, var10043.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Elem((String)null, "th", var33, var10032, false, var10034.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n          "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var35 = new UnprefixedAttribute("style", new Text("width: 167px; padding: 8px 0 8px 0"), $md);
      var10032 = scala.xml.TopScope..MODULE$;
      var10034 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var36 = new UnprefixedAttribute("style", new Text("margin: 0 8px 0 8px"), $md);
      var10041 = scala.xml.TopScope..MODULE$;
      var10043 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("Executor ID / Host"));
      $buf.$amp$plus(new Elem((String)null, "div", var36, var10041, false, var10043.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Elem((String)null, "th", var35, var10032, false, var10034.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n          "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var37 = new UnprefixedAttribute("style", new Text("width: 166px; padding: 8px 0 8px 0"), $md);
      var10032 = scala.xml.TopScope..MODULE$;
      var10034 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var38 = new UnprefixedAttribute("style", new Text("margin: 0 8px 0 8px"), $md);
      var10041 = scala.xml.TopScope..MODULE$;
      var10043 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("Last Error Time"));
      $buf.$amp$plus(new Elem((String)null, "div", var38, var10041, false, var10043.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Elem((String)null, "th", var37, var10032, false, var10034.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n          "));
      Null var10031 = scala.xml.Null..MODULE$;
      var10032 = scala.xml.TopScope..MODULE$;
      var10034 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("Last Error Message"));
      $buf.$amp$plus(new Elem((String)null, "th", var10031, var10032, false, var10034.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n        "));
      $buf.$amp$plus(new Elem((String)null, "tr", var10022, var10023, false, var10025.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      $buf.$amp$plus(new Elem((String)null, "thead", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      var10013 = scala.xml.Null..MODULE$;
      var10014 = scala.xml.TopScope..MODULE$;
      var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n        "));
      $buf.$amp$plus(content);
      $buf.$amp$plus(new Text("\n      "));
      $buf.$amp$plus(new Elem((String)null, "tbody", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n    "));
      return new Elem((String)null, "table", var30, var10005, false, var10007.seqToNodeSeq($buf));
   }

   private Seq generateInputDStreamRow(final JsCollector jsCollector, final int streamId, final Seq recordRates, final long minX, final long maxX, final double minY, final double maxY) {
      Option receiverInfo = this.listener().receiverInfo(streamId);
      String receiverName = (String)receiverInfo.map((x$14) -> x$14.name()).orElse(() -> this.listener().streamName(streamId)).getOrElse(() -> "Stream-" + streamId);
      String receiverActive = (String)receiverInfo.map((info) -> info.active() ? "ACTIVE" : "INACTIVE").getOrElse(() -> StreamingPage$.MODULE$.emptyCell());
      String receiverLocation = (String)receiverInfo.map((info) -> {
         String executorId = info.executorId().isEmpty() ? StreamingPage$.MODULE$.emptyCell() : info.executorId();
         String location = info.location().isEmpty() ? StreamingPage$.MODULE$.emptyCell() : info.location();
         return executorId + " / " + location;
      }).getOrElse(() -> StreamingPage$.MODULE$.emptyCell());
      String receiverLastError = (String)receiverInfo.map((info) -> {
         String var10000 = info.lastErrorMessage();
         String msg = var10000 + " - " + info.lastError();
         if (msg.length() > 100) {
            StringOps var2 = scala.collection.StringOps..MODULE$;
            String var10001 = scala.Predef..MODULE$.augmentString(msg);
            return var2.take$extension(var10001, 97) + "...";
         } else {
            return msg;
         }
      }).getOrElse(() -> StreamingPage$.MODULE$.emptyCell());
      String receiverLastErrorTime = (String)receiverInfo.map((r) -> r.lastErrorTime() < 0L ? "-" : .MODULE$.formatDate(r.lastErrorTime())).getOrElse(() -> StreamingPage$.MODULE$.emptyCell());
      RecordRateUIData receivedRecords = new RecordRateUIData(recordRates);
      GraphUIData graphUIDataForRecordRate = new GraphUIData("stream-" + streamId + "-records-timeline", "stream-" + streamId + "-records-histogram", receivedRecords.data(), minX, maxX, minY, maxY, "records/sec", org.apache.spark.ui.GraphUIData..MODULE$.$lessinit$greater$default$9());
      graphUIDataForRecordRate.generateDataJs(jsCollector);
      NodeBuffer $buf = new NodeBuffer();
      Null var10005 = scala.xml.Null..MODULE$;
      TopScope var10006 = scala.xml.TopScope..MODULE$;
      NodeSeq var10008 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n      "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var41 = new UnprefixedAttribute("style", new Text("vertical-align: middle; width: 151px;"), $md);
      var41 = new UnprefixedAttribute("rowspan", new Text("2"), var41);
      TopScope var10015 = scala.xml.TopScope..MODULE$;
      NodeSeq var10017 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n        "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var43 = new UnprefixedAttribute("style", new Text("width: 151px;"), $md);
      TopScope var10024 = scala.xml.TopScope..MODULE$;
      NodeSeq var10026 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n          "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var44 = new UnprefixedAttribute("style", new Text("word-wrap: break-word;"), $md);
      TopScope var10033 = scala.xml.TopScope..MODULE$;
      NodeSeq var10035 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      Null var10041 = scala.xml.Null..MODULE$;
      TopScope var10042 = scala.xml.TopScope..MODULE$;
      NodeSeq var10044 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(receiverName);
      $buf.$amp$plus(new Elem((String)null, "strong", var10041, var10042, false, var10044.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Elem((String)null, "div", var44, var10033, false, var10035.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n          "));
      Null var10032 = scala.xml.Null..MODULE$;
      var10033 = scala.xml.TopScope..MODULE$;
      var10035 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("Avg: "));
      $buf.$amp$plus(receivedRecords.formattedAvg());
      $buf.$amp$plus(new Text(" records/sec"));
      $buf.$amp$plus(new Elem((String)null, "div", var10032, var10033, false, var10035.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n        "));
      $buf.$amp$plus(new Elem((String)null, "div", var43, var10024, false, var10026.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      $buf.$amp$plus(new Elem((String)null, "td", var41, var10015, false, var10017.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      Null var10014 = scala.xml.Null..MODULE$;
      var10015 = scala.xml.TopScope..MODULE$;
      var10017 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(receiverActive);
      $buf.$amp$plus(new Elem((String)null, "td", var10014, var10015, false, var10017.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      var10014 = scala.xml.Null..MODULE$;
      var10015 = scala.xml.TopScope..MODULE$;
      var10017 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(receiverLocation);
      $buf.$amp$plus(new Elem((String)null, "td", var10014, var10015, false, var10017.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      var10014 = scala.xml.Null..MODULE$;
      var10015 = scala.xml.TopScope..MODULE$;
      var10017 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(receiverLastErrorTime);
      $buf.$amp$plus(new Elem((String)null, "td", var10014, var10015, false, var10017.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      var10014 = scala.xml.Null..MODULE$;
      var10015 = scala.xml.TopScope..MODULE$;
      var10017 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var45 = new UnprefixedAttribute("style", new Text("width: 342px;"), $md);
      var10024 = scala.xml.TopScope..MODULE$;
      var10026 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(receiverLastError);
      $buf.$amp$plus(new Elem((String)null, "div", var45, var10024, false, var10026.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Elem((String)null, "td", var10014, var10015, false, var10017.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n    "));
      $buf.$amp$plus(new Elem((String)null, "tr", var10005, var10006, false, var10008.seqToNodeSeq($buf)));
      var10005 = scala.xml.Null..MODULE$;
      var10006 = scala.xml.TopScope..MODULE$;
      var10008 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n      "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var46 = new UnprefixedAttribute("class", new Text("timeline"), $md);
      var46 = new UnprefixedAttribute("colspan", new Text("3"), var46);
      var10015 = scala.xml.TopScope..MODULE$;
      var10017 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n        "));
      $buf.$amp$plus(graphUIDataForRecordRate.generateTimelineHtml(jsCollector));
      $buf.$amp$plus(new Text("\n      "));
      $buf.$amp$plus(new Elem((String)null, "td", var46, var10015, false, var10017.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var48 = new UnprefixedAttribute("class", new Text("histogram"), $md);
      var10015 = scala.xml.TopScope..MODULE$;
      var10017 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(graphUIDataForRecordRate.generateHistogramHtml(jsCollector));
      $buf.$amp$plus(new Elem((String)null, "td", var48, var10015, false, var10017.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n    "));
      $buf.$amp$plus(new Elem((String)null, "tr", var10005, var10006, false, var10008.seqToNodeSeq($buf)));
      return scala.xml.NodeSeq..MODULE$.seqToNodeSeq($buf);
   }

   private Seq streamingTable(final HttpServletRequest request, final Seq batches, final String tableTag) {
      long interval = this.listener().batchDuration();
      int streamingPage = BoxesRunTime.unboxToInt(scala.Option..MODULE$.apply(request.getParameter(tableTag + ".page")).map((x$15) -> BoxesRunTime.boxToInteger($anonfun$streamingTable$1(x$15))).getOrElse((JFunction0.mcI.sp)() -> 1));

      Object var10000;
      try {
         var10000 = (new StreamingPagedTable(request, tableTag, batches, .MODULE$.prependBaseUri(request, this.parent.basePath(), .MODULE$.prependBaseUri$default$3()), "streaming", interval)).table(streamingPage);
      } catch (Throwable var15) {
         if (!(var15 instanceof IllegalArgumentException ? true : var15 instanceof IndexOutOfBoundsException)) {
            throw var15;
         }

         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var16 = new UnprefixedAttribute("class", new Text("alert alert-error"), $md);
         TopScope var10005 = scala.xml.TopScope..MODULE$;
         NodeSeq var10007 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n          "));
         Null var10013 = scala.xml.Null..MODULE$;
         TopScope var10014 = scala.xml.TopScope..MODULE$;
         NodeSeq var10016 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("Error while rendering streaming table:"));
         $buf.$amp$plus(new Elem((String)null, "p", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n          "));
         var10013 = scala.xml.Null..MODULE$;
         var10014 = scala.xml.TopScope..MODULE$;
         var10016 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n            "));
         $buf.$amp$plus(org.apache.spark.util.Utils..MODULE$.exceptionString(var15));
         $buf.$amp$plus(new Text("\n          "));
         $buf.$amp$plus(new Elem((String)null, "pre", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n        "));
         var10000 = new Elem((String)null, "div", var16, var10005, false, var10007.seqToNodeSeq($buf));
      }

      return (Seq)var10000;
   }

   private Seq generateBatchListTables(final HttpServletRequest request) {
      Seq runningBatches = (Seq)((SeqOps)this.listener().runningBatches().sortBy((x$16) -> BoxesRunTime.boxToLong($anonfun$generateBatchListTables$1(x$16)), scala.math.Ordering.Long..MODULE$)).reverse();
      Seq waitingBatches = (Seq)((SeqOps)this.listener().waitingBatches().sortBy((x$17) -> BoxesRunTime.boxToLong($anonfun$generateBatchListTables$2(x$17)), scala.math.Ordering.Long..MODULE$)).reverse();
      Seq completedBatches = (Seq)((SeqOps)this.listener().retainedCompletedBatches().sortBy((x$18) -> BoxesRunTime.boxToLong($anonfun$generateBatchListTables$3(x$18)), scala.math.Ordering.Long..MODULE$)).reverse();
      ListBuffer content = (ListBuffer)scala.collection.mutable.ListBuffer..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
      if (runningBatches.nonEmpty()) {
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var39 = new UnprefixedAttribute("class", new Text("row"), $md);
         TopScope var10006 = scala.xml.TopScope..MODULE$;
         NodeSeq var10008 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n          "));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var40 = new UnprefixedAttribute("class", new Text("col-12"), $md);
         TopScope var10015 = scala.xml.TopScope..MODULE$;
         NodeSeq var10017 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n            "));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var41 = new UnprefixedAttribute("onClick", new Text("collapseTable('collapse-aggregated-runningBatches',\n                  'aggregated-runningBatches')"), $md);
         var41 = new UnprefixedAttribute("class", new Text("collapse-aggregated-runningBatches collapse-table"), var41);
         var41 = new UnprefixedAttribute("id", new Text("runningBatches"), var41);
         TopScope var10024 = scala.xml.TopScope..MODULE$;
         NodeSeq var10026 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n              "));
         Null var10032 = scala.xml.Null..MODULE$;
         TopScope var10033 = scala.xml.TopScope..MODULE$;
         NodeSeq var10035 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n                "));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var44 = new UnprefixedAttribute("class", new Text("collapse-table-arrow arrow-open"), $md);
         $buf.$amp$plus(new Elem((String)null, "span", var44, scala.xml.TopScope..MODULE$, false, scala.collection.immutable.Nil..MODULE$));
         $buf.$amp$plus(new Text("\n                "));
         Null var10041 = scala.xml.Null..MODULE$;
         TopScope var10042 = scala.xml.TopScope..MODULE$;
         NodeSeq var10044 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("Running Batches ("));
         $buf.$amp$plus(BoxesRunTime.boxToInteger(runningBatches.size()));
         $buf.$amp$plus(new Text(")"));
         $buf.$amp$plus(new Elem((String)null, "a", var10041, var10042, false, var10044.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n              "));
         $buf.$amp$plus(new Elem((String)null, "h4", var10032, var10033, false, var10035.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n            "));
         $buf.$amp$plus(new Elem((String)null, "span", var41, var10024, false, var10026.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n            "));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var45 = new UnprefixedAttribute("class", new Text("aggregated-runningBatches collapsible-table"), $md);
         var10024 = scala.xml.TopScope..MODULE$;
         var10026 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n              "));
         $buf.$amp$plus(this.streamingTable(request, runningBatches, "runningBatches"));
         $buf.$amp$plus(new Text("\n            "));
         $buf.$amp$plus(new Elem((String)null, "div", var45, var10024, false, var10026.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n          "));
         $buf.$amp$plus(new Elem((String)null, "div", var40, var10015, false, var10017.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n        "));
         content.$plus$plus$eq(new Elem((String)null, "div", var39, var10006, false, var10008.seqToNodeSeq($buf)));
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      if (waitingBatches.nonEmpty()) {
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var46 = new UnprefixedAttribute("class", new Text("row"), $md);
         TopScope var62 = scala.xml.TopScope..MODULE$;
         NodeSeq var64 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n          "));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var47 = new UnprefixedAttribute("class", new Text("col-12"), $md);
         TopScope var66 = scala.xml.TopScope..MODULE$;
         NodeSeq var68 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n            "));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var48 = new UnprefixedAttribute("onClick", new Text("collapseTable('collapse-aggregated-waitingBatches',\n                  'aggregated-waitingBatches')"), $md);
         var48 = new UnprefixedAttribute("class", new Text("collapse-aggregated-waitingBatches collapse-table"), var48);
         var48 = new UnprefixedAttribute("id", new Text("waitingBatches"), var48);
         TopScope var71 = scala.xml.TopScope..MODULE$;
         NodeSeq var76 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n              "));
         Null var80 = scala.xml.Null..MODULE$;
         TopScope var82 = scala.xml.TopScope..MODULE$;
         NodeSeq var84 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n                "));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var51 = new UnprefixedAttribute("class", new Text("collapse-table-arrow arrow-open"), $md);
         $buf.$amp$plus(new Elem((String)null, "span", var51, scala.xml.TopScope..MODULE$, false, scala.collection.immutable.Nil..MODULE$));
         $buf.$amp$plus(new Text("\n                "));
         Null var86 = scala.xml.Null..MODULE$;
         TopScope var88 = scala.xml.TopScope..MODULE$;
         NodeSeq var90 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("Waiting Batches ("));
         $buf.$amp$plus(BoxesRunTime.boxToInteger(waitingBatches.size()));
         $buf.$amp$plus(new Text(")"));
         $buf.$amp$plus(new Elem((String)null, "a", var86, var88, false, var90.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n              "));
         $buf.$amp$plus(new Elem((String)null, "h4", var80, var82, false, var84.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n            "));
         $buf.$amp$plus(new Elem((String)null, "span", var48, var71, false, var76.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n            "));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var52 = new UnprefixedAttribute("class", new Text("aggregated-waitingBatches collapsible-table"), $md);
         var71 = scala.xml.TopScope..MODULE$;
         var76 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n              "));
         $buf.$amp$plus(this.streamingTable(request, waitingBatches, "waitingBatches"));
         $buf.$amp$plus(new Text("\n            "));
         $buf.$amp$plus(new Elem((String)null, "div", var52, var71, false, var76.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n          "));
         $buf.$amp$plus(new Elem((String)null, "div", var47, var66, false, var68.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n        "));
         content.$plus$plus$eq(new Elem((String)null, "div", var46, var62, false, var64.seqToNodeSeq($buf)));
      } else {
         BoxedUnit var60 = BoxedUnit.UNIT;
      }

      if (completedBatches.nonEmpty()) {
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var53 = new UnprefixedAttribute("class", new Text("row"), $md);
         TopScope var63 = scala.xml.TopScope..MODULE$;
         NodeSeq var65 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n          "));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var54 = new UnprefixedAttribute("class", new Text("col-12"), $md);
         TopScope var67 = scala.xml.TopScope..MODULE$;
         NodeSeq var69 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n            "));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var55 = new UnprefixedAttribute("onClick", new Text("collapseTable('collapse-aggregated-completedBatches',\n                  'aggregated-completedBatches')"), $md);
         var55 = new UnprefixedAttribute("class", new Text("collapse-aggregated-completedBatches collapse-table"), var55);
         var55 = new UnprefixedAttribute("id", new Text("completedBatches"), var55);
         TopScope var73 = scala.xml.TopScope..MODULE$;
         NodeSeq var78 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n              "));
         Null var81 = scala.xml.Null..MODULE$;
         TopScope var83 = scala.xml.TopScope..MODULE$;
         NodeSeq var85 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n                "));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var58 = new UnprefixedAttribute("class", new Text("collapse-table-arrow arrow-open"), $md);
         $buf.$amp$plus(new Elem((String)null, "span", var58, scala.xml.TopScope..MODULE$, false, scala.collection.immutable.Nil..MODULE$));
         $buf.$amp$plus(new Text("\n                "));
         Null var87 = scala.xml.Null..MODULE$;
         TopScope var89 = scala.xml.TopScope..MODULE$;
         NodeSeq var91 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("Completed Batches (last "));
         $buf.$amp$plus(BoxesRunTime.boxToInteger(completedBatches.size()));
         $buf.$amp$plus(new Text("\n                  out of "));
         $buf.$amp$plus(BoxesRunTime.boxToLong(this.listener().numTotalCompletedBatches()));
         $buf.$amp$plus(new Text(")"));
         $buf.$amp$plus(new Elem((String)null, "a", var87, var89, false, var91.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n              "));
         $buf.$amp$plus(new Elem((String)null, "h4", var81, var83, false, var85.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n            "));
         $buf.$amp$plus(new Elem((String)null, "span", var55, var73, false, var78.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n            "));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var59 = new UnprefixedAttribute("class", new Text("aggregated-completedBatches collapsible-table"), $md);
         var73 = scala.xml.TopScope..MODULE$;
         var78 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n              "));
         $buf.$amp$plus(this.streamingTable(request, completedBatches, "completedBatches"));
         $buf.$amp$plus(new Text("\n            "));
         $buf.$amp$plus(new Elem((String)null, "div", var59, var73, false, var78.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n          "));
         $buf.$amp$plus(new Elem((String)null, "div", var54, var67, false, var69.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n        "));
         content.$plus$plus$eq(new Elem((String)null, "div", var53, var63, false, var65.seqToNodeSeq($buf)));
      } else {
         BoxedUnit var61 = BoxedUnit.UNIT;
      }

      return scala.xml.NodeSeq..MODULE$.seqToNodeSeq(content);
   }

   // $FF: synthetic method
   public static final String $anonfun$generateTimeMap$1(final StreamingPage $this, final long time) {
      String formattedTime = .MODULE$.formatBatchTime(time, $this.listener().batchDuration(), false, .MODULE$.formatBatchTime$default$4());
      return "timeFormat[" + time + "] = '" + formattedTime + "';";
   }

   // $FF: synthetic method
   public static final String $anonfun$generateTimeTipStrings$1(final long time) {
      return "timeTipStrings[" + time + "] = timeFormat[" + time + "];";
   }

   // $FF: synthetic method
   public static final long $anonfun$generateStatTable$1(final BatchUIData x$6) {
      return x$6.batchTime().milliseconds();
   }

   // $FF: synthetic method
   public static final Tuple2 $anonfun$generateStatTable$4(final BatchUIData batchInfo$1, final long x$7) {
      return scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(BoxesRunTime.boxToLong(batchInfo$1.batchTime().milliseconds())), BoxesRunTime.boxToLong(x$7));
   }

   // $FF: synthetic method
   public static final Tuple2 $anonfun$generateStatTable$6(final BatchUIData batchInfo$2, final long x$8) {
      return scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(BoxesRunTime.boxToLong(batchInfo$2.batchTime().milliseconds())), BoxesRunTime.boxToLong(x$8));
   }

   // $FF: synthetic method
   public static final Tuple2 $anonfun$generateStatTable$8(final BatchUIData batchInfo$3, final long x$9) {
      return scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(BoxesRunTime.boxToLong(batchInfo$3.batchTime().milliseconds())), BoxesRunTime.boxToLong(x$9));
   }

   // $FF: synthetic method
   public static final Option $anonfun$generateStatTable$10(final MillisecondsStatUIData totalDelay$1, final long m1$1, final long m2) {
      return totalDelay$1.max().map((JFunction1.mcJJ.sp)(m3) -> scala.runtime.RichLong..MODULE$.max$extension(scala.Predef..MODULE$.longWrapper(scala.runtime.RichLong..MODULE$.max$extension(scala.Predef..MODULE$.longWrapper(m1$1), m2)), m3));
   }

   // $FF: synthetic method
   public static final Option $anonfun$generateStatTable$9(final MillisecondsStatUIData processingTime$1, final MillisecondsStatUIData totalDelay$1, final long m1) {
      return processingTime$1.max().flatMap((m2) -> $anonfun$generateStatTable$10(totalDelay$1, m1, BoxesRunTime.unboxToLong(m2)));
   }

   // $FF: synthetic method
   public static final double $anonfun$generateInputDStreamsTable$2(final Tuple2 x0$2) {
      if (x0$2 != null) {
         double recordRate = x0$2._2$mcD$sp();
         return recordRate;
      } else {
         throw new MatchError(x0$2);
      }
   }

   // $FF: synthetic method
   public static final int $anonfun$generateInputDStreamsTable$6(final Tuple2 x$13) {
      return x$13._1$mcI$sp();
   }

   // $FF: synthetic method
   public static final int $anonfun$streamingTable$1(final String x$15) {
      return scala.collection.StringOps..MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(x$15));
   }

   // $FF: synthetic method
   public static final long $anonfun$generateBatchListTables$1(final BatchUIData x$16) {
      return x$16.batchTime().milliseconds();
   }

   // $FF: synthetic method
   public static final long $anonfun$generateBatchListTables$2(final BatchUIData x$17) {
      return x$17.batchTime().milliseconds();
   }

   // $FF: synthetic method
   public static final long $anonfun$generateBatchListTables$3(final BatchUIData x$18) {
      return x$18.batchTime().milliseconds();
   }

   public StreamingPage(final StreamingTab parent) {
      super("");
      this.parent = parent;
      Logging.$init$(this);
      this.listener = parent.listener();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
