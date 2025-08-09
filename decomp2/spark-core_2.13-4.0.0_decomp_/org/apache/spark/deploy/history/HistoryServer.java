package org.apache.spark.deploy.history;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.lang.invoke.SerializedLambda;
import java.util.NoSuchElementException;
import java.util.zip.ZipOutputStream;
import org.apache.spark.SSLOptions;
import org.apache.spark.SecurityManager;
import org.apache.spark.SparkConf;
import org.apache.spark.deploy.Utils$;
import org.apache.spark.internal.config.History$;
import org.apache.spark.status.api.v1.ApiRootResource$;
import org.apache.spark.status.api.v1.ApplicationAttemptInfo;
import org.apache.spark.status.api.v1.UIRoot;
import org.apache.spark.ui.ServerInfo;
import org.apache.spark.ui.SparkUI;
import org.apache.spark.ui.SparkUI$;
import org.apache.spark.ui.UIUtils$;
import org.apache.spark.ui.WebUI;
import org.apache.spark.ui.WebUI$;
import org.apache.spark.util.SystemClock;
import org.sparkproject.jetty.servlet.ServletContextHandler;
import org.sparkproject.jetty.servlet.ServletHolder;
import scala.Function1;
import scala.Option;
import scala.Some;
import scala.Predef.;
import scala.collection.Iterator;
import scala.collection.StringOps;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.xml.Elem;
import scala.xml.MetaData;
import scala.xml.Node;
import scala.xml.NodeBuffer;
import scala.xml.NodeSeq;
import scala.xml.Text;
import scala.xml.TopScope;
import scala.xml.UnprefixedAttribute;

@ScalaSignature(
   bytes = "\u0006\u0005\t}d\u0001\u0002\u00180\u0001iB\u0001\"\u0016\u0001\u0003\u0002\u0003\u0006IA\u0016\u0005\t5\u0002\u0011\t\u0011)A\u00057\"Aa\f\u0001B\u0001B\u0003%q\f\u0003\u0005c\u0001\t\u0005\t\u0015!\u0003d\u0011\u0015I\u0007\u0001\"\u0001k\u0011\u001d\u0001\bA1A\u0005\u0002EDa! \u0001!\u0002\u0013\u0011\bb\u0002@\u0001\u0005\u0004%Ia \u0005\b\u0003\u0003\u0001\u0001\u0015!\u0003d\u0011%\t\u0019\u0001\u0001b\u0001\n\u0003ys\u0010C\u0004\u0002\u0006\u0001\u0001\u000b\u0011B2\t\u0013\u0005\u001d\u0001A1A\u0005\n\u0005%\u0001\u0002CA\t\u0001\u0001\u0006I!a\u0003\t\u0013\u0005M\u0001A1A\u0005\u0002\u0005U\u0001\u0002CA\u000f\u0001\u0001\u0006I!a\u0006\t\u0013\u0005}\u0001A1A\u0005\n\u0005\u0005\u0002\u0002CA\u001c\u0001\u0001\u0006I!a\t\t\u000f\u0005e\u0002\u0001\"\u0011\u0002<!9\u0011Q\u000f\u0001\u0005B\u0005]\u0004bBAD\u0001\u0011\u0005\u0011\u0011\u0012\u0005\b\u0003#\u0003A\u0011IAE\u0011\u001d\t\u0019\n\u0001C!\u0003\u0013Cq!!&\u0001\t\u0003\n9\nC\u0004\u0002$\u0002!\t%!*\t\u000f\u00055\u0006\u0001\"\u0011\u00020\"9\u0011Q\u0018\u0001\u0005\u0002\u0005}\u0006bBAm\u0001\u0011\u0005\u00111\u001c\u0005\b\u0003;\u0004A\u0011AAp\u0011\u001d\t9\u000f\u0001C\u0001\u0003SDq!a;\u0001\t\u0003\ti\u000fC\u0004\u0002t\u0002!\t%!>\t\u000f\tM\u0001\u0001\"\u0001\u0003\u0016!9!\u0011\u0006\u0001\u0005\u0002\t-\u0002b\u0002B\u001a\u0001\u0011%!Q\u0007\u0005\b\u0005w\u0001A\u0011\tB\u001f\u000f\u001d\u0011yd\fE\u0001\u0005\u00032aAL\u0018\t\u0002\t\r\u0003BB5&\t\u0003\u0011Y\u0005C\u0005VK!\u0015\r\u0011\"\u0003\u0003N!I!qJ\u0013C\u0002\u0013\u0005!\u0011\u000b\u0005\t\u0005;*\u0003\u0015!\u0003\u0003T!9!qL\u0013\u0005\u0002\t\u0005\u0004\u0002\u0003B7K\u0011\u0005qFa\u001c\t\u000f\tUT\u0005\"\u0001\u0002\n\"A!qO\u0013\u0005\u0002=\u0012IHA\u0007ISN$xN]=TKJ4XM\u001d\u0006\u0003aE\nq\u0001[5ti>\u0014\u0018P\u0003\u00023g\u00051A-\u001a9m_fT!\u0001N\u001b\u0002\u000bM\u0004\u0018M]6\u000b\u0005Y:\u0014AB1qC\u000eDWMC\u00019\u0003\ry'oZ\u0002\u0001'\u0015\u00011(Q$R!\tat(D\u0001>\u0015\tq4'\u0001\u0002vS&\u0011\u0001)\u0010\u0002\u0006/\u0016\u0014W+\u0013\t\u0003\u0005\u0016k\u0011a\u0011\u0006\u0003\tN\n\u0001\"\u001b8uKJt\u0017\r\\\u0005\u0003\r\u000e\u0013q\u0001T8hO&tw\r\u0005\u0002I\u001f6\t\u0011J\u0003\u0002K\u0017\u0006\u0011a/\r\u0006\u0003\u00196\u000b1!\u00199j\u0015\tq5'\u0001\u0004ti\u0006$Xo]\u0005\u0003!&\u0013a!V%S_>$\bC\u0001*T\u001b\u0005y\u0013B\u0001+0\u0005i\t\u0005\u000f\u001d7jG\u0006$\u0018n\u001c8DC\u000eDWm\u00149fe\u0006$\u0018n\u001c8t\u0003\u0011\u0019wN\u001c4\u0011\u0005]CV\"A\u001a\n\u0005e\u001b$!C*qCJ\\7i\u001c8g\u0003!\u0001(o\u001c<jI\u0016\u0014\bC\u0001*]\u0013\tivF\u0001\u000eBaBd\u0017nY1uS>t\u0007*[:u_JL\bK]8wS\u0012,'/A\btK\u000e,(/\u001b;z\u001b\u0006t\u0017mZ3s!\t9\u0006-\u0003\u0002bg\ty1+Z2ve&$\u00180T1oC\u001e,'/\u0001\u0003q_J$\bC\u00013h\u001b\u0005)'\"\u00014\u0002\u000bM\u001c\u0017\r\\1\n\u0005!,'aA%oi\u00061A(\u001b8jiz\"Ra\u001b7n]>\u0004\"A\u0015\u0001\t\u000bU+\u0001\u0019\u0001,\t\u000bi+\u0001\u0019A.\t\u000by+\u0001\u0019A0\t\u000b\t,\u0001\u0019A2\u0002\u000bQLG\u000f\\3\u0016\u0003I\u0004\"a\u001d>\u000f\u0005QD\bCA;f\u001b\u00051(BA<:\u0003\u0019a$o\\8u}%\u0011\u00110Z\u0001\u0007!J,G-\u001a4\n\u0005md(AB*ue&twM\u0003\u0002zK\u00061A/\u001b;mK\u0002\nAC]3uC&tW\rZ!qa2L7-\u0019;j_:\u001cX#A2\u0002+I,G/Y5oK\u0012\f\u0005\u000f\u001d7jG\u0006$\u0018n\u001c8tA\u0005yQ.\u0019=BaBd\u0017nY1uS>t7/\u0001\tnCb\f\u0005\u000f\u001d7jG\u0006$\u0018n\u001c8tA\u0005A\u0011\r\u001d9DC\u000eDW-\u0006\u0002\u0002\fA\u0019!+!\u0004\n\u0007\u0005=qF\u0001\tBaBd\u0017nY1uS>t7)Y2iK\u0006I\u0011\r\u001d9DC\u000eDW\rI\u0001\rG\u0006\u001c\u0007.Z'fiJL7m]\u000b\u0003\u0003/\u00012AUA\r\u0013\r\tYb\f\u0002\r\u0007\u0006\u001c\u0007.Z'fiJL7m]\u0001\u000eG\u0006\u001c\u0007.Z'fiJL7m\u001d\u0011\u0002\u001b1|\u0017\rZ3s'\u0016\u0014h\u000f\\3u+\t\t\u0019\u0003\u0005\u0003\u0002&\u0005MRBAA\u0014\u0015\u0011\tI#a\u000b\u0002\t!$H\u000f\u001d\u0006\u0005\u0003[\ty#A\u0004tKJ4H.\u001a;\u000b\u0005\u0005E\u0012a\u00026bW\u0006\u0014H/Y\u0005\u0005\u0003k\t9CA\u0006IiR\u00048+\u001a:wY\u0016$\u0018A\u00047pC\u0012,'oU3sm2,G\u000fI\u0001\fo&$\bn\u00159be.,\u0016*\u0006\u0003\u0002>\u0005\u0015CCBA \u0003O\nY\u0007\u0006\u0003\u0002B\u0005]\u0003\u0003BA\"\u0003\u000bb\u0001\u0001B\u0004\u0002HI\u0011\r!!\u0013\u0003\u0003Q\u000bB!a\u0013\u0002RA\u0019A-!\u0014\n\u0007\u0005=SMA\u0004O_RD\u0017N\\4\u0011\u0007\u0011\f\u0019&C\u0002\u0002V\u0015\u00141!\u00118z\u0011\u001d\tIF\u0005a\u0001\u00037\n!A\u001a8\u0011\u000f\u0011\fi&!\u0019\u0002B%\u0019\u0011qL3\u0003\u0013\u0019+hn\u0019;j_:\f\u0004c\u0001\u001f\u0002d%\u0019\u0011QM\u001f\u0003\u000fM\u0003\u0018M]6V\u0013\"1\u0011\u0011\u000e\nA\u0002I\fQ!\u00199q\u0013\u0012Dq!!\u001c\u0013\u0001\u0004\ty'A\u0005biR,W\u000e\u001d;JIB!A-!\u001ds\u0013\r\t\u0019(\u001a\u0002\u0007\u001fB$\u0018n\u001c8\u0002-\rDWmY6V\u0013ZKWm\u001e)fe6L7o]5p]N$\u0002\"!\u001f\u0002\u0000\u0005\u0005\u00151\u0011\t\u0004I\u0006m\u0014bAA?K\n9!i\\8mK\u0006t\u0007BBA5'\u0001\u0007!\u000fC\u0004\u0002nM\u0001\r!a\u001c\t\r\u0005\u00155\u00031\u0001s\u0003\u0011)8/\u001a:\u0002\u0015%t\u0017\u000e^5bY&TX\r\u0006\u0002\u0002\fB\u0019A-!$\n\u0007\u0005=UM\u0001\u0003V]&$\u0018\u0001\u00022j]\u0012\fAa\u001d;pa\u0006i\u0011\r\u001e;bG\"\u001c\u0006/\u0019:l+&#\"\"a#\u0002\u001a\u0006m\u0015QTAP\u0011\u0019\tIg\u0006a\u0001e\"9\u0011QN\fA\u0002\u0005=\u0004B\u0002 \u0018\u0001\u0004\t\t\u0007C\u0004\u0002\"^\u0001\r!!\u001f\u0002\u0013\r|W\u000e\u001d7fi\u0016$\u0017!\u00043fi\u0006\u001c\u0007n\u00159be.,\u0016\n\u0006\u0005\u0002\f\u0006\u001d\u0016\u0011VAV\u0011\u0019\tI\u0007\u0007a\u0001e\"9\u0011Q\u000e\rA\u0002\u0005=\u0004B\u0002 \u0019\u0001\u0004\t\t'\u0001\u0005hKR\f\u0005\u000f]+J)\u0019\t\t,!/\u0002<B)A-!\u001d\u00024B\u0019!+!.\n\u0007\u0005]vFA\u0006M_\u0006$W\rZ!qaVK\u0005BBA53\u0001\u0007!\u000fC\u0004\u0002ne\u0001\r!a\u001c\u0002%\u001d,G/\u00119qY&\u001c\u0017\r^5p]2K7\u000f\u001e\u000b\u0003\u0003\u0003\u0004b!a1\u0002N\u0006Mg\u0002BAc\u0003\u0013t1!^Ad\u0013\u00051\u0017bAAfK\u00069\u0001/Y2lC\u001e,\u0017\u0002BAh\u0003#\u0014\u0001\"\u0013;fe\u0006$xN\u001d\u0006\u0004\u0003\u0017,\u0007c\u0001%\u0002V&\u0019\u0011q[%\u0003\u001f\u0005\u0003\b\u000f\\5dCRLwN\\%oM>\f\u0001dZ3u\u000bZ,g\u000e\u001e'pON,f\u000eZ3s!J|7-Z:t)\u0005\u0019\u0017AE4fi2\u000b7\u000f^+qI\u0006$X\r\u001a+j[\u0016$\"!!9\u0011\u0007\u0011\f\u0019/C\u0002\u0002f\u0016\u0014A\u0001T8oO\u00061r-\u001a;BaBd\u0017nY1uS>t\u0017J\u001c4p\u0019&\u001cH/\u0006\u0002\u0002B\u0006\u0011r-\u001a;BaBd\u0017nY1uS>t\u0017J\u001c4p)\u0011\ty/!=\u0011\u000b\u0011\f\t(a5\t\r\u0005%d\u00041\u0001s\u000399(/\u001b;f\u000bZ,g\u000e\u001e'pON$\u0002\"a#\u0002x\u0006e\u00181 \u0005\u0007\u0003Sz\u0002\u0019\u0001:\t\u000f\u00055t\u00041\u0001\u0002p!9\u0011Q`\u0010A\u0002\u0005}\u0018!\u0003>jaN#(/Z1n!\u0011\u0011\tAa\u0004\u000e\u0005\t\r!\u0002\u0002B\u0003\u0005\u000f\t1A_5q\u0015\u0011\u0011IAa\u0003\u0002\tU$\u0018\u000e\u001c\u0006\u0003\u0005\u001b\tAA[1wC&!!\u0011\u0003B\u0002\u0005=Q\u0016\u000e](viB,Ho\u0015;sK\u0006l\u0017\u0001E3naRLH*[:uS:<\u0007\n^7m)\t\u00119\u0002\u0005\u0004\u0002D\ne!QD\u0005\u0005\u00057\t\tNA\u0002TKF\u0004BAa\b\u0003&5\u0011!\u0011\u0005\u0006\u0004\u0005G)\u0017a\u0001=nY&!!q\u0005B\u0011\u0005\u0011qu\u000eZ3\u0002#\u001d,G\u000f\u0015:pm&$WM]\"p]\u001aLw\r\u0006\u0002\u0003.A)1Oa\fse&\u0019!\u0011\u0007?\u0003\u00075\u000b\u0007/A\u0005m_\u0006$\u0017\t\u001d9VSR1\u0011\u0011\u0010B\u001c\u0005sAa!!\u001b#\u0001\u0004\u0011\bbBA7E\u0001\u0007\u0011qN\u0001\ti>\u001cFO]5oOR\t!/A\u0007ISN$xN]=TKJ4XM\u001d\t\u0003%\u0016\u001aB!\nB#\u0003B\u0019AMa\u0012\n\u0007\t%SM\u0001\u0004B]f\u0014VM\u001a\u000b\u0003\u0005\u0003*\u0012AV\u0001\u000f+&{\u0006+\u0011+I?B\u0013VIR%Y+\t\u0011\u0019\u0006\u0005\u0003\u0003V\tmSB\u0001B,\u0015\u0011\u0011IFa\u0003\u0002\t1\fgnZ\u0005\u0004w\n]\u0013aD+J?B\u000bE\u000bS0Q%\u00163\u0015\n\u0017\u0011\u0002\t5\f\u0017N\u001c\u000b\u0005\u0003\u0017\u0013\u0019\u0007C\u0004\u0003f)\u0002\rAa\u001a\u0002\u0015\u0005\u0014xm\u0015;sS:<7\u000f\u0005\u0003e\u0005S\u0012\u0018b\u0001B6K\n)\u0011I\u001d:bs\u0006)2M]3bi\u0016\u001cVmY;sSRLX*\u00198bO\u0016\u0014HcA0\u0003r!1!1O\u0016A\u0002Y\u000baaY8oM&<\u0017\u0001D5oSR\u001cVmY;sSRL\u0018!D4fi\u0006#H/Z7qiV\u0013\u0016\nF\u0003s\u0005w\u0012i\b\u0003\u0004\u0002j5\u0002\rA\u001d\u0005\b\u0003[j\u0003\u0019AA8\u0001"
)
public class HistoryServer extends WebUI implements UIRoot, ApplicationCacheOperations {
   private final SparkConf conf;
   public final ApplicationHistoryProvider org$apache$spark$deploy$history$HistoryServer$$provider;
   private final String title;
   private final int retainedApplications;
   private final int maxApplications;
   private final ApplicationCache appCache;
   private final CacheMetrics cacheMetrics;
   private final HttpServlet loaderServlet;

   public static void initSecurity() {
      HistoryServer$.MODULE$.initSecurity();
   }

   public static void main(final String[] argStrings) {
      HistoryServer$.MODULE$.main(argStrings);
   }

   public static String UI_PATH_PREFIX() {
      return HistoryServer$.MODULE$.UI_PATH_PREFIX();
   }

   public String title() {
      return this.title;
   }

   private int retainedApplications() {
      return this.retainedApplications;
   }

   public int maxApplications() {
      return this.maxApplications;
   }

   private ApplicationCache appCache() {
      return this.appCache;
   }

   public CacheMetrics cacheMetrics() {
      return this.cacheMetrics;
   }

   private HttpServlet loaderServlet() {
      return this.loaderServlet;
   }

   public Object withSparkUI(final String appId, final Option attemptId, final Function1 fn) {
      return this.appCache().withSparkUI(appId, attemptId, fn);
   }

   public boolean checkUIViewPermissions(final String appId, final Option attemptId, final String user) {
      return this.org$apache$spark$deploy$history$HistoryServer$$provider.checkUIViewPermissions(appId, attemptId, user);
   }

   public void initialize() {
      this.attachPage(new HistoryPage(this));
      this.attachPage(new LogPage(this.conf));
      this.attachHandler(ApiRootResource$.MODULE$.getServletHandler(this));
      this.addStaticHandler(SparkUI$.MODULE$.STATIC_RESOURCE_DIR(), this.addStaticHandler$default$2());
      Utils$.MODULE$.addRenderLogHandler(this, this.conf);
      ServletContextHandler contextHandler = new ServletContextHandler();
      contextHandler.setContextPath(HistoryServer$.MODULE$.UI_PATH_PREFIX());
      contextHandler.addServlet(new ServletHolder(this.loaderServlet()), "/*");
      this.attachHandler(contextHandler);
   }

   public void bind() {
      super.bind();
   }

   public void stop() {
      super.stop();
      this.org$apache$spark$deploy$history$HistoryServer$$provider.stop();
   }

   public void attachSparkUI(final String appId, final Option attemptId, final SparkUI ui, final boolean completed) {
      .MODULE$.assert(this.serverInfo().isDefined(), () -> "HistoryServer must be bound before attaching SparkUIs");
      ui.getHandlers().foreach((handler) -> {
         $anonfun$attachSparkUI$2(this, ui, handler);
         return BoxedUnit.UNIT;
      });
   }

   public void detachSparkUI(final String appId, final Option attemptId, final SparkUI ui) {
      .MODULE$.assert(this.serverInfo().isDefined(), () -> "HistoryServer must be bound before detaching SparkUIs");
      ui.getHandlers().foreach((handler) -> {
         $anonfun$detachSparkUI$2(this, handler);
         return BoxedUnit.UNIT;
      });
      this.org$apache$spark$deploy$history$HistoryServer$$provider.onUIDetached(appId, attemptId, ui);
   }

   public Option getAppUI(final String appId, final Option attemptId) {
      return this.org$apache$spark$deploy$history$HistoryServer$$provider.getAppUI(appId, attemptId);
   }

   public Iterator getApplicationList() {
      return this.org$apache$spark$deploy$history$HistoryServer$$provider.getListing();
   }

   public int getEventLogsUnderProcess() {
      return this.org$apache$spark$deploy$history$HistoryServer$$provider.getEventLogsUnderProcess();
   }

   public long getLastUpdatedTime() {
      return this.org$apache$spark$deploy$history$HistoryServer$$provider.getLastUpdatedTime();
   }

   public Iterator getApplicationInfoList() {
      return this.getApplicationList();
   }

   public Option getApplicationInfo(final String appId) {
      return this.org$apache$spark$deploy$history$HistoryServer$$provider.getApplicationInfo(appId);
   }

   public void writeEventLogs(final String appId, final Option attemptId, final ZipOutputStream zipStream) {
      this.org$apache$spark$deploy$history$HistoryServer$$provider.writeEventLogs(appId, attemptId, zipStream);
   }

   public Seq emptyListingHtml() {
      return this.org$apache$spark$deploy$history$HistoryServer$$provider.getEmptyListingHtml();
   }

   public Map getProviderConfig() {
      return this.org$apache$spark$deploy$history$HistoryServer$$provider.getConfig();
   }

   public boolean org$apache$spark$deploy$history$HistoryServer$$loadAppUi(final String appId, final Option attemptId) {
      boolean var10000;
      try {
         this.appCache().withSparkUI(appId, attemptId, (x$3) -> {
            $anonfun$loadAppUi$1(x$3);
            return BoxedUnit.UNIT;
         });
         var10000 = true;
      } catch (Throwable var8) {
         if (var8 != null) {
            Option var6 = scala.util.control.NonFatal..MODULE$.unapply(var8);
            if (!var6.isEmpty()) {
               Throwable e = (Throwable)var6.get();
               if (e instanceof NoSuchElementException) {
                  var10000 = false;
                  return var10000;
               }
            }
         }

         throw var8;
      }

      return var10000;
   }

   public String toString() {
      ApplicationHistoryProvider var10002 = this.org$apache$spark$deploy$history$HistoryServer$$provider;
      return scala.collection.StringOps..MODULE$.stripMargin$extension(.MODULE$.augmentString("\n      | History Server;\n      | provider = " + var10002 + "\n      | cache = " + this.appCache() + "\n    "));
   }

   // $FF: synthetic method
   public static final void $anonfun$attachSparkUI$2(final HistoryServer $this, final SparkUI ui$1, final ServletContextHandler handler) {
      ((ServerInfo)$this.serverInfo().get()).addHandler(handler, ui$1.securityManager());
   }

   // $FF: synthetic method
   public static final void $anonfun$detachSparkUI$2(final HistoryServer $this, final ServletContextHandler handler) {
      $this.detachHandler(handler);
   }

   // $FF: synthetic method
   public static final void $anonfun$loadAppUi$1(final SparkUI x$3) {
   }

   public HistoryServer(final SparkConf conf, final ApplicationHistoryProvider provider, final SecurityManager securityManager, final int port) {
      this.conf = conf;
      this.org$apache$spark$deploy$history$HistoryServer$$provider = provider;
      SSLOptions x$2 = securityManager.getSSLOptions("historyServer");
      String x$5 = "HistoryServerUI";
      int x$6 = 1000;
      String x$7 = WebUI$.MODULE$.$lessinit$greater$default$5();
      super(securityManager, x$2, port, conf, x$7, "HistoryServerUI", 1000);
      UIRoot.$init$(this);
      this.title = (String)conf.get(History$.MODULE$.HISTORY_SERVER_UI_TITLE());
      this.retainedApplications = BoxesRunTime.unboxToInt(conf.get(History$.MODULE$.RETAINED_APPLICATIONS()));
      this.maxApplications = BoxesRunTime.unboxToInt(conf.get(History$.MODULE$.HISTORY_UI_MAX_APPS()));
      this.appCache = new ApplicationCache(this, this.retainedApplications(), new SystemClock());
      this.cacheMetrics = this.appCache().metrics();
      this.loaderServlet = new HttpServlet() {
         // $FF: synthetic field
         private final HistoryServer $outer;

         public void doGet(final HttpServletRequest req, final HttpServletResponse res) {
            res.setContentType("text/html;charset=utf-8");
            String[] parts = ((String)scala.Option..MODULE$.apply(req.getPathInfo()).getOrElse(() -> "")).split("/");
            if (parts.length < 2) {
               res.sendRedirect("/");
            }

            String appId = parts[1];
            boolean shouldAppendAttemptId = false;
            Object var10000;
            if (parts.length >= 3) {
               var10000 = new Some(parts[2]);
            } else {
               Option lastAttemptId = this.$outer.org$apache$spark$deploy$history$HistoryServer$$provider.getApplicationInfo(appId).flatMap((x$1) -> ((ApplicationAttemptInfo)x$1.attempts().head()).attemptId());
               if (lastAttemptId.isDefined()) {
                  shouldAppendAttemptId = true;
                  var10000 = lastAttemptId;
               } else {
                  var10000 = scala.None..MODULE$;
               }
            }

            Option attemptId = (Option)var10000;
            if (this.$outer.org$apache$spark$deploy$history$HistoryServer$$loadAppUi(appId, scala.None..MODULE$) || !attemptId.isEmpty() && this.$outer.org$apache$spark$deploy$history$HistoryServer$$loadAppUi(appId, attemptId)) {
               String var15;
               if (shouldAppendAttemptId) {
                  StringOps var14 = scala.collection.StringOps..MODULE$;
                  var15 = var14.stripSuffix$extension(.MODULE$.augmentString(req.getRequestURI()), "/") + "/" + attemptId.get() + "/";
               } else {
                  StringOps var16 = scala.collection.StringOps..MODULE$;
                  var15 = var16.stripSuffix$extension(.MODULE$.augmentString(req.getRequestURI()), "/") + "/";
               }

               String redirect = var15;
               String query = (String)scala.Option..MODULE$.apply(req.getQueryString()).map((x$2) -> "?" + x$2).getOrElse(() -> "");
               res.sendRedirect(res.encodeRedirectURL(redirect + query));
            } else {
               MetaData $md = scala.xml.Null..MODULE$;
               MetaData var13 = new UnprefixedAttribute("class", new Text("row"), $md);
               TopScope var10005 = scala.xml.TopScope..MODULE$;
               NodeSeq var10007 = scala.xml.NodeSeq..MODULE$;
               NodeBuffer $buf = new NodeBuffer();
               $buf.$amp$plus(new Text("Application "));
               $buf.$amp$plus(appId);
               $buf.$amp$plus(new Text(" not found."));
               Elem msg = new Elem((String)null, "div", var13, var10005, false, var10007.seqToNodeSeq($buf));
               res.setStatus(404);
               UIUtils$.MODULE$.basicSparkPage(req, () -> msg, "Not Found", UIUtils$.MODULE$.basicSparkPage$default$4()).foreach((n) -> {
                  $anonfun$doGet$4(res, n);
                  return BoxedUnit.UNIT;
               });
            }
         }

         public void doTrace(final HttpServletRequest req, final HttpServletResponse res) {
            res.sendError(405);
         }

         // $FF: synthetic method
         public static final void $anonfun$doGet$4(final HttpServletResponse res$1, final Node n) {
            res$1.getWriter().write(n.toString());
         }

         public {
            if (HistoryServer.this == null) {
               throw null;
            } else {
               this.$outer = HistoryServer.this;
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
      this.initialize();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
