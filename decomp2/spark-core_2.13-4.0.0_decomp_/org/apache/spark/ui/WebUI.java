package org.apache.spark.ui;

import jakarta.servlet.http.HttpServlet;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.SSLOptions;
import org.apache.spark.SecurityManager;
import org.apache.spark.SparkConf;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.config.package$;
import org.apache.spark.util.Utils$;
import org.slf4j.Logger;
import org.sparkproject.jetty.servlet.ServletContextHandler;
import org.sparkproject.jetty.servlet.ServletHolder;
import scala.Function0;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.collection.IterableOnceOps;
import scala.collection.StringOps;
import scala.collection.StringOps.;
import scala.collection.immutable.Seq;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.HashMap;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005\tMbA\u0002\u001b6\u0003\u00039T\b\u0003\u0005K\u0001\t\u0015\r\u0011\"\u0001M\u0011!\t\u0006A!A!\u0002\u0013i\u0005\u0002\u0003*\u0001\u0005\u000b\u0007I\u0011A*\t\u0011]\u0003!\u0011!Q\u0001\nQC\u0001\u0002\u0017\u0001\u0003\u0002\u0003\u0006I!\u0017\u0005\t9\u0002\u0011\t\u0011)A\u0005;\"A\u0001\r\u0001B\u0001B\u0003%\u0011\r\u0003\u0005m\u0001\t\u0005\t\u0015!\u0003b\u0011!i\u0007A!A!\u0002\u0013I\u0006\"\u00028\u0001\t\u0003y\u0007bB=\u0001\u0005\u0004%\tB\u001f\u0005\b\u0003\u001b\u0001\u0001\u0015!\u0003|\u0011%\ty\u0001\u0001b\u0001\n#\t\t\u0002\u0003\u0005\u0002*\u0001\u0001\u000b\u0011BA\n\u0011%\tY\u0003\u0001b\u0001\n#\ti\u0003\u0003\u0005\u0002<\u0001\u0001\u000b\u0011BA\u0018\u0011%\ti\u0004\u0001a\u0001\n#\ty\u0004C\u0005\u0002N\u0001\u0001\r\u0011\"\u0005\u0002P!A\u00111\f\u0001!B\u0013\t\t\u0005C\u0005\u0002^\u0001\u0011\r\u0011\"\u0005\u0002`!9\u0011\u0011\r\u0001!\u0002\u0013\t\u0007\"CA2\u0001\t\u0007I\u0011CA0\u0011\u001d\t)\u0007\u0001Q\u0001\n\u0005Dq!a\u001a\u0001\t\u0003\ty\u0006C\u0004\u0002j\u0001!\t!a\u001b\t\u000f\u0005}\u0004\u0001\"\u0001\u0002\u0002\"9\u0011Q\u0011\u0001\u0005\u0002\u0005\u001d\u0005bBAI\u0001\u0011\u0005\u00111\u0013\u0005\b\u00033\u0003A\u0011AAN\u0011\u001d\ty\n\u0001C\u0001\u0003CCq!a*\u0001\t\u0003\tI\u000bC\u0004\u0002.\u0002!\t!a,\t\u000f\u00055\u0006\u0001\"\u0001\u00026\"9\u0011Q\u001b\u0001\u0005\u0002\u0005]\u0007bBAk\u0001\u0011\u0005\u00111\u001c\u0005\b\u0003C\u0004A\u0011AAr\u0011%\tY\u000fAI\u0001\n\u0003\ti\u000fC\u0004\u0003\u0004\u00011\tA!\u0002\t\u000f\t\u001d\u0001\u0001\"\u0001\u0003\n!9!1\u0002\u0001\u0005\u0002\t\u0015\u0001b\u0002B\u0007\u0001\u0011\u0005!q\u0002\u0005\b\u0005/\u0001A\u0011AA0\u0011\u001d\u0011I\u0002\u0001C\u0001\u0003?BqAa\u0007\u0001\t\u0003\u0011i\u0002C\u0004\u0003 \u0001!\tA!\u0002\b\u0015\t\u0005R'!A\t\u0002]\u0012\u0019CB\u00055k\u0005\u0005\t\u0012A\u001c\u0003&!1an\fC\u0001\u0005OA\u0011B!\u000b0#\u0003%\t!!<\t\u0013\t-r&%A\u0005\u0002\u00055\b\"\u0003B\u0017_E\u0005I\u0011\u0001B\u0018\u0005\u00159VMY+J\u0015\t1t'\u0001\u0002vS*\u0011\u0001(O\u0001\u0006gB\f'o\u001b\u0006\u0003um\na!\u00199bG\",'\"\u0001\u001f\u0002\u0007=\u0014xmE\u0002\u0001}\u0011\u0003\"a\u0010\"\u000e\u0003\u0001S\u0011!Q\u0001\u0006g\u000e\fG.Y\u0005\u0003\u0007\u0002\u0013a!\u00118z%\u00164\u0007CA#I\u001b\u00051%BA$8\u0003!Ig\u000e^3s]\u0006d\u0017BA%G\u0005\u001daunZ4j]\u001e\fqb]3dkJLG/_'b]\u0006<WM]\u0002\u0001+\u0005i\u0005C\u0001(P\u001b\u00059\u0014B\u0001)8\u0005=\u0019VmY;sSRLX*\u00198bO\u0016\u0014\u0018\u0001E:fGV\u0014\u0018\u000e^=NC:\fw-\u001a:!\u0003)\u00198\u000f\\(qi&|gn]\u000b\u0002)B\u0011a*V\u0005\u0003-^\u0012!bU*M\u001fB$\u0018n\u001c8t\u0003-\u00198\u000f\\(qi&|gn\u001d\u0011\u0002\tA|'\u000f\u001e\t\u0003\u007fiK!a\u0017!\u0003\u0007%sG/\u0001\u0003d_:4\u0007C\u0001(_\u0013\tyvGA\u0005Ta\u0006\u00148nQ8oM\u0006A!-Y:f!\u0006$\b\u000e\u0005\u0002cS:\u00111m\u001a\t\u0003I\u0002k\u0011!\u001a\u0006\u0003M.\u000ba\u0001\u0010:p_Rt\u0014B\u00015A\u0003\u0019\u0001&/\u001a3fM&\u0011!n\u001b\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005!\u0004\u0015\u0001\u00028b[\u0016\f\u0001\u0002]8pYNK'0Z\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0011A\u00148\u000f^;wob\u0004\"!\u001d\u0001\u000e\u0003UBQA\u0013\u0006A\u00025CQA\u0015\u0006A\u0002QCQ\u0001\u0017\u0006A\u0002eCQ\u0001\u0018\u0006A\u0002uCq\u0001\u0019\u0006\u0011\u0002\u0003\u0007\u0011\rC\u0004m\u0015A\u0005\t\u0019A1\t\u000f5T\u0001\u0013!a\u00013\u0006!A/\u00192t+\u0005Y\b#\u0002?\u0002\u0004\u0005\u001dQ\"A?\u000b\u0005y|\u0018aB7vi\u0006\u0014G.\u001a\u0006\u0004\u0003\u0003\u0001\u0015AC2pY2,7\r^5p]&\u0019\u0011QA?\u0003\u0017\u0005\u0013(/Y=Ck\u001a4WM\u001d\t\u0004c\u0006%\u0011bAA\u0006k\tAq+\u001a2V\u0013R\u000b'-A\u0003uC\n\u001c\b%\u0001\u0005iC:$G.\u001a:t+\t\t\u0019\u0002E\u0003}\u0003\u0007\t)\u0002\u0005\u0003\u0002\u0018\u0005\u0015RBAA\r\u0015\u0011\tY\"!\b\u0002\u000fM,'O\u001e7fi*!\u0011qDA\u0011\u0003\u0015QW\r\u001e;z\u0015\r\t\u0019cO\u0001\bK\u000ed\u0017\u000e]:f\u0013\u0011\t9#!\u0007\u0003+M+'O\u001e7fi\u000e{g\u000e^3yi\"\u000bg\u000e\u001a7fe\u0006I\u0001.\u00198eY\u0016\u00148\u000fI\u0001\u000fa\u0006<W\rV8IC:$G.\u001a:t+\t\ty\u0003E\u0004}\u0003c\t)$a\u0005\n\u0007\u0005MRPA\u0004ICNDW*\u00199\u0011\u0007E\f9$C\u0002\u0002:U\u0012\u0011bV3c+&\u0003\u0016mZ3\u0002\u001fA\fw-\u001a+p\u0011\u0006tG\r\\3sg\u0002\n!b]3sm\u0016\u0014\u0018J\u001c4p+\t\t\t\u0005E\u0003@\u0003\u0007\n9%C\u0002\u0002F\u0001\u0013aa\u00149uS>t\u0007cA9\u0002J%\u0019\u00111J\u001b\u0003\u0015M+'O^3s\u0013:4w.\u0001\btKJ4XM]%oM>|F%Z9\u0015\t\u0005E\u0013q\u000b\t\u0004\u007f\u0005M\u0013bAA+\u0001\n!QK\\5u\u0011%\tIFEA\u0001\u0002\u0004\t\t%A\u0002yIE\n1b]3sm\u0016\u0014\u0018J\u001c4pA\u0005q\u0001/\u001e2mS\u000eDun\u001d;OC6,W#A1\u0002\u001fA,(\r\\5d\u0011>\u001cHOT1nK\u0002\n\u0011b\u00197bgNt\u0015-\\3\u0002\u0015\rd\u0017m]:OC6,\u0007%A\u0006hKR\u0014\u0015m]3QCRD\u0017aB4fiR\u000b'm]\u000b\u0003\u0003[\u0002b!a\u001c\u0002z\u0005\u001da\u0002BA9\u0003kr1\u0001ZA:\u0013\u0005\t\u0015bAA<\u0001\u00069\u0001/Y2lC\u001e,\u0017\u0002BA>\u0003{\u00121aU3r\u0015\r\t9\bQ\u0001\fO\u0016$\b*\u00198eY\u0016\u00148/\u0006\u0002\u0002\u0004B1\u0011qNA=\u0003+\tQcZ3u\t\u0016dWmZ1uS:<\u0007*\u00198eY\u0016\u00148/\u0006\u0002\u0002\nB1\u0011qNA=\u0003\u0017\u00032!]AG\u0013\r\ty)\u000e\u0002 \t\u0016dWmZ1uS:<7+\u001a:wY\u0016$8i\u001c8uKb$\b*\u00198eY\u0016\u0014\u0018!C1ui\u0006\u001c\u0007\u000eV1c)\u0011\t\t&!&\t\u000f\u0005]E\u00041\u0001\u0002\b\u0005\u0019A/\u00192\u0002\u0013\u0011,G/Y2i)\u0006\u0014G\u0003BA)\u0003;Cq!a&\u001e\u0001\u0004\t9!\u0001\u0006eKR\f7\r\u001b)bO\u0016$B!!\u0015\u0002$\"9\u0011Q\u0015\u0010A\u0002\u0005U\u0012\u0001\u00029bO\u0016\f!\"\u0019;uC\u000eD\u0007+Y4f)\u0011\t\t&a+\t\u000f\u0005\u0015v\u00041\u0001\u00026\u0005i\u0011\r\u001e;bG\"D\u0015M\u001c3mKJ$B!!\u0015\u00022\"9\u00111\u0017\u0011A\u0002\u0005U\u0011a\u00025b]\u0012dWM\u001d\u000b\t\u0003#\n9,a/\u0002R\"1\u0011\u0011X\u0011A\u0002\u0005\f1bY8oi\u0016DH\u000fU1uQ\"9\u0011QX\u0011A\u0002\u0005}\u0016a\u00035uiB\u001cVM\u001d<mKR\u0004B!!1\u0002N6\u0011\u00111\u0019\u0006\u0005\u0003\u000b\f9-\u0001\u0003iiR\u0004(\u0002BA\u000e\u0003\u0013T!!a3\u0002\u000f)\f7.\u0019:uC&!\u0011qZAb\u0005-AE\u000f\u001e9TKJ4H.\u001a;\t\r\u0005M\u0017\u00051\u0001b\u0003!\u0001\u0018\r\u001e5Ta\u0016\u001c\u0017!\u00043fi\u0006\u001c\u0007\u000eS1oI2,'\u000f\u0006\u0003\u0002R\u0005e\u0007bBAZE\u0001\u0007\u0011Q\u0003\u000b\u0005\u0003#\ni\u000e\u0003\u0004\u0002`\u000e\u0002\r!Y\u0001\u0005a\u0006$\b.\u0001\tbI\u0012\u001cF/\u0019;jG\"\u000bg\u000e\u001a7feR1\u0011\u0011KAs\u0003SDa!a:%\u0001\u0004\t\u0017\u0001\u0004:fg>,(oY3CCN,\u0007\u0002CApIA\u0005\t\u0019A1\u00025\u0005$Gm\u0015;bi&\u001c\u0007*\u00198eY\u0016\u0014H\u0005Z3gCVdG\u000f\n\u001a\u0016\u0005\u0005=(fA1\u0002r.\u0012\u00111\u001f\t\u0005\u0003k\fy0\u0004\u0002\u0002x*!\u0011\u0011`A~\u0003%)hn\u00195fG.,GMC\u0002\u0002~\u0002\u000b!\"\u00198o_R\fG/[8o\u0013\u0011\u0011\t!a>\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-\u0001\u0006j]&$\u0018.\u00197ju\u0016$\"!!\u0015\u0002\u0015%t\u0017\u000e^*feZ,'\u000f\u0006\u0002\u0002H\u0005!!-\u001b8e\u0003!I7oU3dkJ,WC\u0001B\t!\ry$1C\u0005\u0004\u0005+\u0001%a\u0002\"p_2,\u0017M\\\u0001\u0007g\u000eDW-\\3\u0002\r],'-\u0016:m\u0003%\u0011w.\u001e8e!>\u0014H/F\u0001Z\u0003\u0011\u0019Ho\u001c9\u0002\u000b]+'-V%\u0011\u0005E|3CA\u0018?)\t\u0011\u0019#A\u000e%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$H%N\u0001\u001cI1,7o]5oSR$sM]3bi\u0016\u0014H\u0005Z3gCVdG\u000f\n\u001c\u00027\u0011bWm]:j]&$He\u001a:fCR,'\u000f\n3fM\u0006,H\u000e\u001e\u00138+\t\u0011\tDK\u0002Z\u0003c\u0004"
)
public abstract class WebUI implements Logging {
   private final SecurityManager securityManager;
   private final SSLOptions sslOptions;
   private final int port;
   private final SparkConf conf;
   private final String basePath;
   private final String name;
   private final int poolSize;
   private final ArrayBuffer tabs;
   private final ArrayBuffer handlers;
   private final HashMap pageToHandlers;
   private Option serverInfo;
   private final String publicHostName;
   private final String className;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static int $lessinit$greater$default$7() {
      return WebUI$.MODULE$.$lessinit$greater$default$7();
   }

   public static String $lessinit$greater$default$6() {
      return WebUI$.MODULE$.$lessinit$greater$default$6();
   }

   public static String $lessinit$greater$default$5() {
      return WebUI$.MODULE$.$lessinit$greater$default$5();
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

   public SecurityManager securityManager() {
      return this.securityManager;
   }

   public SSLOptions sslOptions() {
      return this.sslOptions;
   }

   public ArrayBuffer tabs() {
      return this.tabs;
   }

   public ArrayBuffer handlers() {
      return this.handlers;
   }

   public HashMap pageToHandlers() {
      return this.pageToHandlers;
   }

   public Option serverInfo() {
      return this.serverInfo;
   }

   public void serverInfo_$eq(final Option x$1) {
      this.serverInfo = x$1;
   }

   public String publicHostName() {
      return this.publicHostName;
   }

   public String className() {
      return this.className;
   }

   public String getBasePath() {
      return this.basePath;
   }

   public Seq getTabs() {
      return this.tabs().toSeq();
   }

   public Seq getHandlers() {
      return this.handlers().toSeq();
   }

   public Seq getDelegatingHandlers() {
      return ((IterableOnceOps)this.handlers().map((x$1) -> new DelegatingServletContextHandler(x$1))).toSeq();
   }

   public void attachTab(final WebUITab tab) {
      tab.pages().foreach((page) -> {
         $anonfun$attachTab$1(this, page);
         return BoxedUnit.UNIT;
      });
      this.tabs().$plus$eq(tab);
   }

   public void detachTab(final WebUITab tab) {
      tab.pages().foreach((page) -> {
         $anonfun$detachTab$1(this, page);
         return BoxedUnit.UNIT;
      });
      this.tabs().$minus$eq(tab);
   }

   public void detachPage(final WebUIPage page) {
      this.pageToHandlers().remove(page).foreach((x$2) -> {
         $anonfun$detachPage$1(this, x$2);
         return BoxedUnit.UNIT;
      });
   }

   public void attachPage(final WebUIPage page) {
      String pagePath = "/" + page.prefix();
      ServletContextHandler renderHandler = JettyUtils$.MODULE$.createServletHandler(pagePath, JettyUtils$.MODULE$.htmlResponderToServlet((request) -> page.render(request)), this.conf, this.basePath);
      StringOps var10001 = .MODULE$;
      ServletContextHandler renderJsonHandler = JettyUtils$.MODULE$.createServletHandler(var10001.stripSuffix$extension(scala.Predef..MODULE$.augmentString(pagePath), "/") + "/json", JettyUtils$.MODULE$.jsonResponderToServlet((request) -> page.renderJson(request)), this.conf, this.basePath);
      this.attachHandler(renderHandler);
      this.attachHandler(renderJsonHandler);
      ArrayBuffer handlers = (ArrayBuffer)this.pageToHandlers().getOrElseUpdate(page, () -> (ArrayBuffer)scala.collection.mutable.ArrayBuffer..MODULE$.apply(scala.collection.immutable.Nil..MODULE$));
      handlers.$plus$eq(renderHandler);
      handlers.$plus$eq(renderJsonHandler);
   }

   public synchronized void attachHandler(final ServletContextHandler handler) {
      this.handlers().$plus$eq(handler);
      this.serverInfo().foreach((x$3) -> {
         $anonfun$attachHandler$1(this, handler, x$3);
         return BoxedUnit.UNIT;
      });
   }

   public void attachHandler(final String contextPath, final HttpServlet httpServlet, final String pathSpec) {
      ServletContextHandler ctx = new ServletContextHandler();
      ctx.setContextPath(contextPath);
      ctx.addServlet(new ServletHolder(httpServlet), pathSpec);
      this.attachHandler(ctx);
   }

   public synchronized void detachHandler(final ServletContextHandler handler) {
      this.handlers().$minus$eq(handler);
      this.serverInfo().foreach((x$4) -> {
         $anonfun$detachHandler$1(handler, x$4);
         return BoxedUnit.UNIT;
      });
   }

   public void detachHandler(final String path) {
      this.handlers().find((x$5) -> BoxesRunTime.boxToBoolean($anonfun$detachHandler$2(path, x$5))).foreach((handler) -> {
         $anonfun$detachHandler$3(this, handler);
         return BoxedUnit.UNIT;
      });
   }

   public void addStaticHandler(final String resourceBase, final String path) {
      this.attachHandler(JettyUtils$.MODULE$.createStaticHandler(resourceBase, path));
   }

   public String addStaticHandler$default$2() {
      return "/static";
   }

   public abstract void initialize();

   public ServerInfo initServer() {
      String hostName = (String)scala.Option..MODULE$.apply(this.conf.getenv("SPARK_LOCAL_IP")).getOrElse(() -> Utils$.MODULE$.preferIPv6() ? "[::]" : "0.0.0.0");
      ServerInfo server = JettyUtils$.MODULE$.startJettyServer(hostName, this.port, this.sslOptions(), this.conf, this.name, this.poolSize);
      return server;
   }

   public void bind() {
      scala.Predef..MODULE$.assert(this.serverInfo().isEmpty(), () -> "Attempted to bind " + this.className() + " more than once!");

      try {
         ServerInfo server = this.initServer();
         this.handlers().foreach((x$6) -> {
            $anonfun$bind$2(this, server, x$6);
            return BoxedUnit.UNIT;
         });
         this.serverInfo_$eq(new Some(server));
         String hostName = (String)scala.Option..MODULE$.apply(this.conf.getenv("SPARK_LOCAL_IP")).getOrElse(() -> Utils$.MODULE$.preferIPv6() ? "[::]" : "0.0.0.0");
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Bound ", " to ", ","})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CLASS_NAME..MODULE$, this.className()), new MDC(org.apache.spark.internal.LogKeys.HOST..MODULE$, hostName)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" and started at ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.WEB_URL..MODULE$, this.webUrl())}))))));
      } catch (Exception var4) {
         this.logError((LogEntry)org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Failed to bind ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CLASS_NAME..MODULE$, this.className())})))), var4);
         System.exit(1);
      }

   }

   public boolean isSecure() {
      return BoxesRunTime.unboxToBoolean(this.serverInfo().map((x$7) -> BoxesRunTime.boxToBoolean($anonfun$isSecure$1(x$7))).getOrElse((JFunction0.mcZ.sp)() -> false));
   }

   public String scheme() {
      return this.isSecure() ? "https://" : "http://";
   }

   public String webUrl() {
      String var10000 = this.scheme();
      return var10000 + this.publicHostName() + ":" + this.boundPort();
   }

   public int boundPort() {
      return BoxesRunTime.unboxToInt(this.serverInfo().map((si) -> BoxesRunTime.boxToInteger($anonfun$boundPort$1(si))).getOrElse((JFunction0.mcI.sp)() -> -1));
   }

   public void stop() {
      scala.Predef..MODULE$.assert(this.serverInfo().isDefined(), () -> "Attempted to stop " + this.className() + " before binding to a server!");
      this.serverInfo().foreach((x$8) -> {
         $anonfun$stop$2(x$8);
         return BoxedUnit.UNIT;
      });
   }

   // $FF: synthetic method
   public static final void $anonfun$attachTab$1(final WebUI $this, final WebUIPage page) {
      $this.attachPage(page);
   }

   // $FF: synthetic method
   public static final void $anonfun$detachTab$1(final WebUI $this, final WebUIPage page) {
      $this.detachPage(page);
   }

   // $FF: synthetic method
   public static final void $anonfun$detachPage$2(final WebUI $this, final ServletContextHandler handler) {
      $this.detachHandler(handler);
   }

   // $FF: synthetic method
   public static final void $anonfun$detachPage$1(final WebUI $this, final ArrayBuffer x$2) {
      x$2.foreach((handler) -> {
         $anonfun$detachPage$2($this, handler);
         return BoxedUnit.UNIT;
      });
   }

   // $FF: synthetic method
   public static final void $anonfun$attachHandler$1(final WebUI $this, final ServletContextHandler handler$1, final ServerInfo x$3) {
      x$3.addHandler(handler$1, $this.securityManager());
   }

   // $FF: synthetic method
   public static final void $anonfun$detachHandler$1(final ServletContextHandler handler$2, final ServerInfo x$4) {
      x$4.removeHandler(handler$2);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$detachHandler$2(final String path$1, final ServletContextHandler x$5) {
      boolean var3;
      label23: {
         String var10000 = x$5.getContextPath();
         if (var10000 == null) {
            if (path$1 == null) {
               break label23;
            }
         } else if (var10000.equals(path$1)) {
            break label23;
         }

         var3 = false;
         return var3;
      }

      var3 = true;
      return var3;
   }

   // $FF: synthetic method
   public static final void $anonfun$detachHandler$3(final WebUI $this, final ServletContextHandler handler) {
      $this.detachHandler(handler);
   }

   // $FF: synthetic method
   public static final void $anonfun$bind$2(final WebUI $this, final ServerInfo server$1, final ServletContextHandler x$6) {
      server$1.addHandler(x$6, $this.securityManager());
   }

   // $FF: synthetic method
   public static final boolean $anonfun$isSecure$1(final ServerInfo x$7) {
      return x$7.securePort().isDefined();
   }

   // $FF: synthetic method
   public static final int $anonfun$boundPort$1(final ServerInfo si) {
      return BoxesRunTime.unboxToInt(si.securePort().getOrElse((JFunction0.mcI.sp)() -> si.boundPort()));
   }

   // $FF: synthetic method
   public static final void $anonfun$stop$2(final ServerInfo x$8) {
      x$8.stop();
   }

   public WebUI(final SecurityManager securityManager, final SSLOptions sslOptions, final int port, final SparkConf conf, final String basePath, final String name, final int poolSize) {
      this.securityManager = securityManager;
      this.sslOptions = sslOptions;
      this.port = port;
      this.conf = conf;
      this.basePath = basePath;
      this.name = name;
      this.poolSize = poolSize;
      Logging.$init$(this);
      this.tabs = (ArrayBuffer)scala.collection.mutable.ArrayBuffer..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
      this.handlers = (ArrayBuffer)scala.collection.mutable.ArrayBuffer..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
      this.pageToHandlers = new HashMap();
      this.serverInfo = scala.None..MODULE$;
      this.publicHostName = (String)scala.Option..MODULE$.apply(conf.getenv("SPARK_PUBLIC_DNS")).getOrElse(() -> (String)this.conf.get(package$.MODULE$.DRIVER_HOST_ADDRESS()));
      this.className = Utils$.MODULE$.getFormattedClassName(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
