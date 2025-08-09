package org.apache.spark.ui;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.lang.invoke.SerializedLambda;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.zip.ZipOutputStream;
import org.apache.spark.SecurityManager;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.config.ConfigEntry;
import org.apache.spark.internal.config.UI$;
import org.apache.spark.internal.config.package$;
import org.apache.spark.scheduler.SparkListener;
import org.apache.spark.status.AppStatusStore;
import org.apache.spark.status.api.v1.ApiRootResource$;
import org.apache.spark.status.api.v1.ApplicationAttemptInfo;
import org.apache.spark.status.api.v1.ApplicationInfo;
import org.apache.spark.status.api.v1.PrometheusResource$;
import org.apache.spark.status.api.v1.UIRoot;
import org.apache.spark.ui.env.EnvironmentTab;
import org.apache.spark.ui.exec.ExecutorsTab;
import org.apache.spark.ui.jobs.JobsTab;
import org.apache.spark.ui.jobs.StagesTab;
import org.apache.spark.ui.storage.StorageTab;
import org.sparkproject.jetty.servlet.ServletContextHandler;
import scala.Function1;
import scala.None;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.Predef.;
import scala.collection.Iterator;
import scala.collection.immutable.Set;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005\t\re!B\u001d;\u0001q\u0012\u0005\u0002C,\u0001\u0005\u000b\u0007I\u0011A-\t\u0011y\u0003!\u0011!Q\u0001\niC\u0001b\u0018\u0001\u0003\u0006\u0004%\t\u0001\u0019\u0005\tW\u0002\u0011\t\u0011)A\u0005C\"AA\u000e\u0001BC\u0002\u0013\u0005Q\u000e\u0003\u0005r\u0001\t\u0005\t\u0015!\u0003o\u0011%\u0011\bA!A!\u0002\u0013\u0019h\u000f\u0003\u0005x\u0001\t\u0005\r\u0011\"\u0001y\u0011)\tI\u0001\u0001BA\u0002\u0013\u0005\u00111\u0002\u0005\n\u0003/\u0001!\u0011!Q!\neD\u0011\"!\u0007\u0001\u0005\u000b\u0007I\u0011\u0001=\t\u0013\u0005m\u0001A!A!\u0002\u0013I\bBCA\u000f\u0001\t\u0015\r\u0011\"\u0001\u0002 !Q\u0011q\u0005\u0001\u0003\u0002\u0003\u0006I!!\t\t\u0013\u0005%\u0002A!b\u0001\n\u0003A\b\"CA\u0016\u0001\t\u0005\t\u0015!\u0003z\u0011\u001d\ti\u0003\u0001C\u0005\u0003_A\u0011\"a\u0011\u0001\u0005\u0004%\t!!\u0012\t\u0011\u00055\u0003\u0001)A\u0005\u0003\u000fB!\"a\u0014\u0001\u0001\u0004\u0005\r\u0011\"\u0001y\u0011-\t\t\u0006\u0001a\u0001\u0002\u0004%\t!a\u0015\t\u0015\u0005]\u0003\u00011A\u0001B\u0003&\u0011\u0010C\u0005\u0002Z\u0001\u0001\r\u0011\"\u0003\u0002\\!I\u00111\u000e\u0001A\u0002\u0013%\u0011Q\u000e\u0005\t\u0003c\u0002\u0001\u0015)\u0003\u0002^!I\u00111\u000f\u0001C\u0002\u0013%\u0011Q\u000f\u0005\t\u0003\u0017\u0003\u0001\u0015!\u0003\u0002x!I\u0011Q\u0012\u0001A\u0002\u0013%\u0011Q\t\u0005\n\u0003\u001f\u0003\u0001\u0019!C\u0005\u0003#C\u0001\"!&\u0001A\u0003&\u0011q\t\u0005\b\u0003/\u0003A\u0011AAM\u0011\u001d\tY\n\u0001C!\u0003;Cq!a)\u0001\t\u0003\tI\n\u0003\u0004\u0002&\u0002!\t\u0001\u001f\u0005\u0007\u0003O\u0003A\u0011\u0001=\t\u000f\u0005%\u0006\u0001\"\u0001\u0002,\"9\u0011\u0011\u0017\u0001\u0005B\u0005e\u0005bBAZ\u0001\u0011\u0005\u0013\u0011\u0014\u0005\b\u0003k\u0003A\u0011IA\\\u0011\u001d\t)\u000f\u0001C!\u0003ODq!!=\u0001\t\u0003\t\u0019\u0010C\u0004\u0003\u000e\u0001!\tAa\u0004\t\u000f\tU\u0001\u0001\"\u0001\u0002\\!9!q\u0003\u0001\u0005\u0002\te\u0001b\u0002B\u0010\u0001\u0011\u0005\u0011\u0011\u0014\u0005\u000e\u0005C\u0001\u0001\u0013aA\u0001\u0002\u0013%!1\u0005<\b\u0011\t\u0015\"\b#\u0001=\u0005O1q!\u000f\u001e\t\u0002q\u0012I\u0003C\u0004\u0002.A\"\tA!\r\t\u0013\tM\u0002G1A\u0005\u0002\tU\u0002\u0002\u0003B#a\u0001\u0006IAa\u000e\t\u0013\t\u001d\u0003G1A\u0005\u0002\tU\u0002\u0002\u0003B%a\u0001\u0006IAa\u000e\t\u000f\t-\u0003\u0007\"\u0001\u0003N!9!q\u000b\u0019\u0005\u0002\te\u0003\"\u0003B6aE\u0005I\u0011\u0001B7\u0005\u001d\u0019\u0006/\u0019:l+&S!a\u000f\u001f\u0002\u0005UL'BA\u001f?\u0003\u0015\u0019\b/\u0019:l\u0015\ty\u0004)\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002\u0003\u0006\u0019qN]4\u0014\t\u0001\u0019u)\u0014\t\u0003\t\u0016k\u0011AO\u0005\u0003\rj\u0012QaV3c+&\u0003\"\u0001S&\u000e\u0003%S!A\u0013\u001f\u0002\u0011%tG/\u001a:oC2L!\u0001T%\u0003\u000f1{wmZ5oOB\u0011a*V\u0007\u0002\u001f*\u0011\u0001+U\u0001\u0003mFR!AU*\u0002\u0007\u0005\u0004\u0018N\u0003\u0002Uy\u000511\u000f^1ukNL!AV(\u0003\rUK%k\\8u\u0003\u0015\u0019Ho\u001c:f\u0007\u0001)\u0012A\u0017\t\u00037rk\u0011aU\u0005\u0003;N\u0013a\"\u00119q'R\fG/^:Ti>\u0014X-\u0001\u0004ti>\u0014X\rI\u0001\u0003g\u000e,\u0012!\u0019\t\u0004E\u0016<W\"A2\u000b\u0003\u0011\fQa]2bY\u0006L!AZ2\u0003\r=\u0003H/[8o!\tA\u0017.D\u0001=\u0013\tQGH\u0001\u0007Ta\u0006\u00148nQ8oi\u0016DH/A\u0002tG\u0002\nAaY8oMV\ta\u000e\u0005\u0002i_&\u0011\u0001\u000f\u0010\u0002\n'B\f'o[\"p]\u001a\fQaY8oM\u0002\nqb]3dkJLG/_'b]\u0006<WM\u001d\t\u0003QRL!!\u001e\u001f\u0003\u001fM+7-\u001e:jifl\u0015M\\1hKJL!A]#\u0002\u000f\u0005\u0004\bOT1nKV\t\u0011\u0010E\u0002{\u0003\u0007q!a_@\u0011\u0005q\u001cW\"A?\u000b\u0005yD\u0016A\u0002\u001fs_>$h(C\u0002\u0002\u0002\r\fa\u0001\u0015:fI\u00164\u0017\u0002BA\u0003\u0003\u000f\u0011aa\u0015;sS:<'bAA\u0001G\u0006Y\u0011\r\u001d9OC6,w\fJ3r)\u0011\ti!a\u0005\u0011\u0007\t\fy!C\u0002\u0002\u0012\r\u0014A!\u00168ji\"A\u0011QC\u0005\u0002\u0002\u0003\u0007\u00110A\u0002yIE\n\u0001\"\u00199q\u001d\u0006lW\rI\u0001\tE\u0006\u001cX\rU1uQ\u0006I!-Y:f!\u0006$\b\u000eI\u0001\ngR\f'\u000f\u001e+j[\u0016,\"!!\t\u0011\u0007\t\f\u0019#C\u0002\u0002&\r\u0014A\u0001T8oO\u0006Q1\u000f^1siRKW.\u001a\u0011\u0002\u001f\u0005\u0004\bo\u00159be.4VM]:j_:\f\u0001#\u00199q'B\f'o\u001b,feNLwN\u001c\u0011\u0002\rqJg.\u001b;?)I\t\t$a\r\u00026\u0005]\u0012\u0011HA\u001e\u0003{\ty$!\u0011\u0011\u0005\u0011\u0003\u0001\"B,\u0012\u0001\u0004Q\u0006\"B0\u0012\u0001\u0004\t\u0007\"\u00027\u0012\u0001\u0004q\u0007\"\u0002:\u0012\u0001\u0004\u0019\b\"B<\u0012\u0001\u0004I\bBBA\r#\u0001\u0007\u0011\u0010C\u0004\u0002\u001eE\u0001\r!!\t\t\r\u0005%\u0012\u00031\u0001z\u0003-Y\u0017\u000e\u001c7F]\u0006\u0014G.\u001a3\u0016\u0005\u0005\u001d\u0003c\u00012\u0002J%\u0019\u00111J2\u0003\u000f\t{w\u000e\\3b]\u0006a1.\u001b7m\u000b:\f'\r\\3eA\u0005)\u0011\r\u001d9JI\u0006I\u0011\r\u001d9JI~#S-\u001d\u000b\u0005\u0003\u001b\t)\u0006\u0003\u0005\u0002\u0016U\t\t\u00111\u0001z\u0003\u0019\t\u0007\u000f]%eA\u0005a2\u000f\u001e:fC6Lgn\u001a&pEB\u0013xn\u001a:fgNd\u0015n\u001d;f]\u0016\u0014XCAA/!\u0011\u0011W-a\u0018\u0011\t\u0005\u0005\u0014qM\u0007\u0003\u0003GR1!!\u001a=\u0003%\u00198\r[3ek2,'/\u0003\u0003\u0002j\u0005\r$!D*qCJ\\G*[:uK:,'/\u0001\u0011tiJ,\u0017-\\5oO*{'\r\u0015:pOJ,7o\u001d'jgR,g.\u001a:`I\u0015\fH\u0003BA\u0007\u0003_B\u0011\"!\u0006\u0019\u0003\u0003\u0005\r!!\u0018\u0002;M$(/Z1nS:<'j\u001c2Qe><'/Z:t\u0019&\u001cH/\u001a8fe\u0002\n1\"\u001b8ji\"\u000bg\u000e\u001a7feV\u0011\u0011q\u000f\t\u0005\u0003s\n9)\u0004\u0002\u0002|)!\u0011QPA@\u0003\u001d\u0019XM\u001d<mKRTA!!!\u0002\u0004\u0006)!.\u001a;us*\u0019\u0011Q\u0011!\u0002\u000f\u0015\u001cG.\u001b9tK&!\u0011\u0011RA>\u0005U\u0019VM\u001d<mKR\u001cuN\u001c;fqRD\u0015M\u001c3mKJ\fA\"\u001b8ji\"\u000bg\u000e\u001a7fe\u0002\nQC]3bIf$v.\u0011;uC\u000eD\u0007*\u00198eY\u0016\u00148/A\rsK\u0006$\u0017\u0010V8BiR\f7\r\u001b%b]\u0012dWM]:`I\u0015\fH\u0003BA\u0007\u0003'C\u0011\"!\u0006\u001e\u0003\u0003\u0005\r!a\u0012\u0002-I,\u0017\rZ=U_\u0006#H/Y2i\u0011\u0006tG\r\\3sg\u0002\n\u0011#\u0019;uC\u000eD\u0017\t\u001c7IC:$G.\u001a:t)\t\ti!A\u0007biR\f7\r\u001b%b]\u0012dWM\u001d\u000b\u0005\u0003\u001b\ty\nC\u0004\u0002\"\u0002\u0002\r!a\u001e\u0002\u000f!\fg\u000e\u001a7fe\u0006Q\u0011N\\5uS\u0006d\u0017N_3\u0002\u0019\u001d,Go\u00159be.,6/\u001a:\u0002\u0015\u001d,G/\u00119q\u001d\u0006lW-\u0001\u0005tKR\f\u0005\u000f]%e)\u0011\ti!!,\t\r\u0005=F\u00051\u0001z\u0003\tIG-\u0001\u0003cS:$\u0017\u0001B:u_B\f1b^5uQN\u0003\u0018M]6V\u0013V!\u0011\u0011XAa)\u0019\tY,!8\u0002`R!\u0011QXAj!\u0011\ty,!1\r\u0001\u00119\u00111Y\u0014C\u0002\u0005\u0015'!\u0001+\u0012\t\u0005\u001d\u0017Q\u001a\t\u0004E\u0006%\u0017bAAfG\n9aj\u001c;iS:<\u0007c\u00012\u0002P&\u0019\u0011\u0011[2\u0003\u0007\u0005s\u0017\u0010C\u0004\u0002V\u001e\u0002\r!a6\u0002\u0005\u0019t\u0007c\u00022\u0002Z\u0006E\u0012QX\u0005\u0004\u00037\u001c'!\u0003$v]\u000e$\u0018n\u001c82\u0011\u0019\tye\na\u0001s\"9\u0011\u0011]\u0014A\u0002\u0005\r\u0018!C1ui\u0016l\u0007\u000f^%e!\r\u0011W-_\u0001\u0017G\",7m[+J-&,w\u000fU3s[&\u001c8/[8ogRA\u0011qIAu\u0003W\fi\u000f\u0003\u0004\u0002P!\u0002\r!\u001f\u0005\b\u0003CD\u0003\u0019AAr\u0011\u0019\ty\u000f\u000ba\u0001s\u0006!Qo]3s\u0003Y9W\r^!qa2L7-\u0019;j_:LeNZ8MSN$XCAA{!\u0019\t9P!\u0001\u0003\b9!\u0011\u0011`A\u007f\u001d\ra\u00181`\u0005\u0002I&\u0019\u0011q`2\u0002\u000fA\f7m[1hK&!!1\u0001B\u0003\u0005!IE/\u001a:bi>\u0014(bAA\u0000GB\u0019aJ!\u0003\n\u0007\t-qJA\bBaBd\u0017nY1uS>t\u0017J\u001c4p\u0003I9W\r^!qa2L7-\u0019;j_:LeNZ8\u0015\t\tE!1\u0003\t\u0005E\u0016\u00149\u0001\u0003\u0004\u0002P)\u0002\r!_\u0001 O\u0016$8\u000b\u001e:fC6Lgn\u001a&pEB\u0013xn\u001a:fgNd\u0015n\u001d;f]\u0016\u0014\u0018aH:fiN#(/Z1nS:<'j\u001c2Qe><'/Z:t\u0019&\u001cH/\u001a8feR!\u0011Q\u0002B\u000e\u0011\u001d\u0011i\u0002\fa\u0001\u0003?\nQb\u001d9be.d\u0015n\u001d;f]\u0016\u0014\u0018!I2mK\u0006\u00148\u000b\u001e:fC6Lgn\u001a&pEB\u0013xn\u001a:fgNd\u0015n\u001d;f]\u0016\u0014\u0018!F:va\u0016\u0014He]3dkJLG/_'b]\u0006<WM]\u000b\u0002g\u000691\u000b]1sWVK\u0005C\u0001#1'\r\u0001$1\u0006\t\u0004E\n5\u0012b\u0001B\u0018G\n1\u0011I\\=SK\u001a$\"Aa\n\u0002'M#\u0016\tV%D?J+5kT+S\u0007\u0016{F)\u0013*\u0016\u0005\t]\u0002\u0003\u0002B\u001d\u0005\u0007j!Aa\u000f\u000b\t\tu\"qH\u0001\u0005Y\u0006twM\u0003\u0002\u0003B\u0005!!.\u0019<b\u0013\u0011\t)Aa\u000f\u0002)M#\u0016\tV%D?J+5kT+S\u0007\u0016{F)\u0013*!\u0003E!UIR!V\u0019R{\u0006kT(M?:\u000bU*R\u0001\u0013\t\u00163\u0015)\u0016'U?B{u\nT0O\u00036+\u0005%A\u0005hKR,\u0016\nU8siR!!q\nB+!\r\u0011'\u0011K\u0005\u0004\u0005'\u001a'aA%oi\")AN\u000ea\u0001]\u000611M]3bi\u0016$\"#!\r\u0003\\\tu#q\fB1\u0005G\u0012)Ga\u001a\u0003j!)ql\u000ea\u0001C\")qk\u000ea\u00015\")An\u000ea\u0001]\")!o\u000ea\u0001g\")qo\u000ea\u0001s\"1\u0011\u0011D\u001cA\u0002eDq!!\b8\u0001\u0004\t\t\u0003\u0003\u0005\u0002*]\u0002\n\u00111\u0001z\u0003A\u0019'/Z1uK\u0012\"WMZ1vYR$\u0003(\u0006\u0002\u0003p)\u001a\u0011P!\u001d,\u0005\tM\u0004\u0003\u0002B;\u0005\u007fj!Aa\u001e\u000b\t\te$1P\u0001\nk:\u001c\u0007.Z2lK\u0012T1A! d\u0003)\tgN\\8uCRLwN\\\u0005\u0005\u0005\u0003\u00139HA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\u0004"
)
public class SparkUI extends WebUI implements UIRoot {
   private final AppStatusStore store;
   private final Option sc;
   private final SparkConf conf;
   private String appName;
   private final String basePath;
   private final long startTime;
   private final String appSparkVersion;
   private final boolean killEnabled;
   private String appId;
   private Option streamingJobProgressListener;
   private final ServletContextHandler initHandler;
   private boolean readyToAttachHandlers;

   public static String create$default$8() {
      return SparkUI$.MODULE$.create$default$8();
   }

   public static SparkUI create(final Option sc, final AppStatusStore store, final SparkConf conf, final SecurityManager securityManager, final String appName, final String basePath, final long startTime, final String appSparkVersion) {
      return SparkUI$.MODULE$.create(sc, store, conf, securityManager, appName, basePath, startTime, appSparkVersion);
   }

   public static int getUIPort(final SparkConf conf) {
      return SparkUI$.MODULE$.getUIPort(conf);
   }

   public static String DEFAULT_POOL_NAME() {
      return SparkUI$.MODULE$.DEFAULT_POOL_NAME();
   }

   public static String STATIC_RESOURCE_DIR() {
      return SparkUI$.MODULE$.STATIC_RESOURCE_DIR();
   }

   public void writeEventLogs(final String appId, final Option attemptId, final ZipOutputStream zipStream) {
      UIRoot.writeEventLogs$(this, appId, attemptId, zipStream);
   }

   // $FF: synthetic method
   private SecurityManager super$securityManager() {
      return super.securityManager();
   }

   public AppStatusStore store() {
      return this.store;
   }

   public Option sc() {
      return this.sc;
   }

   public SparkConf conf() {
      return this.conf;
   }

   public String appName() {
      return this.appName;
   }

   public void appName_$eq(final String x$1) {
      this.appName = x$1;
   }

   public String basePath() {
      return this.basePath;
   }

   public long startTime() {
      return this.startTime;
   }

   public String appSparkVersion() {
      return this.appSparkVersion;
   }

   public boolean killEnabled() {
      return this.killEnabled;
   }

   public String appId() {
      return this.appId;
   }

   public void appId_$eq(final String x$1) {
      this.appId = x$1;
   }

   private Option streamingJobProgressListener() {
      return this.streamingJobProgressListener;
   }

   private void streamingJobProgressListener_$eq(final Option x$1) {
      this.streamingJobProgressListener = x$1;
   }

   private ServletContextHandler initHandler() {
      return this.initHandler;
   }

   private boolean readyToAttachHandlers() {
      return this.readyToAttachHandlers;
   }

   private void readyToAttachHandlers_$eq(final boolean x$1) {
      this.readyToAttachHandlers = x$1;
   }

   public void attachAllHandlers() {
      this.serverInfo().foreach((server) -> {
         $anonfun$attachAllHandlers$1(this, server);
         return BoxedUnit.UNIT;
      });
      this.readyToAttachHandlers_$eq(true);
   }

   public synchronized void attachHandler(final ServletContextHandler handler) {
      this.handlers().$plus$eq(handler);
      if (this.readyToAttachHandlers()) {
         this.serverInfo().foreach((x$4) -> {
            $anonfun$attachHandler$1(this, handler, x$4);
            return BoxedUnit.UNIT;
         });
      }
   }

   public void initialize() {
      JobsTab jobsTab = new JobsTab(this, this.store());
      this.attachTab(jobsTab);
      StagesTab stagesTab = new StagesTab(this, this.store());
      this.attachTab(stagesTab);
      this.attachTab(new StorageTab(this, this.store()));
      this.attachTab(new EnvironmentTab(this, this.store()));
      if (BoxesRunTime.unboxToBoolean(this.sc().map((x$5x) -> BoxesRunTime.boxToBoolean($anonfun$initialize$1(x$5x))).getOrElse((JFunction0.mcZ.sp)() -> false))) {
         DriverLogTab driverLogTab = new DriverLogTab(this);
         this.attachTab(driverLogTab);
         this.attachHandler(JettyUtils$.MODULE$.createServletHandler("/log", JettyUtils$.MODULE$.textResponderToServlet((request) -> driverLogTab.getPage().renderLog(request)), ((SparkContext)this.sc().get()).conf(), JettyUtils$.MODULE$.createServletHandler$default$4()));
      }

      this.attachTab(new ExecutorsTab(this));
      this.addStaticHandler(SparkUI$.MODULE$.STATIC_RESOURCE_DIR(), this.addStaticHandler$default$2());
      String x$1 = "/";
      String x$2 = "/jobs/";
      String x$3 = this.basePath();
      Function1 x$4 = JettyUtils$.MODULE$.createRedirectHandler$default$3();
      Set x$5 = JettyUtils$.MODULE$.createRedirectHandler$default$5();
      this.attachHandler(JettyUtils$.MODULE$.createRedirectHandler("/", "/jobs/", x$4, x$3, x$5));
      this.attachHandler(ApiRootResource$.MODULE$.getServletHandler(this));
      if (BoxesRunTime.unboxToBoolean(this.sc().map((x$6x) -> BoxesRunTime.boxToBoolean($anonfun$initialize$4(x$6x))).getOrElse((JFunction0.mcZ.sp)() -> false))) {
         this.attachHandler(PrometheusResource$.MODULE$.getServletHandler(this));
      }

      String x$6 = "/jobs/job/kill";
      String x$7 = "/jobs/";
      Function1 x$8 = (request) -> {
         $anonfun$initialize$6(jobsTab, request);
         return BoxedUnit.UNIT;
      };
      Set x$9 = (Set).MODULE$.Set().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"GET", "POST"})));
      String x$10 = JettyUtils$.MODULE$.createRedirectHandler$default$4();
      this.attachHandler(JettyUtils$.MODULE$.createRedirectHandler("/jobs/job/kill", "/jobs/", x$8, x$10, x$9));
      String x$11 = "/stages/stage/kill";
      String x$12 = "/stages/";
      Function1 x$13 = (request) -> {
         $anonfun$initialize$7(stagesTab, request);
         return BoxedUnit.UNIT;
      };
      Set x$14 = (Set).MODULE$.Set().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"GET", "POST"})));
      String x$15 = JettyUtils$.MODULE$.createRedirectHandler$default$4();
      this.attachHandler(JettyUtils$.MODULE$.createRedirectHandler("/stages/stage/kill", "/stages/", x$13, x$15, x$14));
   }

   public String getSparkUser() {
      String var10000;
      try {
         var10000 = (String)scala.Option..MODULE$.apply(((ApplicationAttemptInfo)this.store().applicationInfo().attempts().head()).sparkUser()).orElse(() -> this.store().environmentInfo().systemProperties().toMap(scala..less.colon.less..MODULE$.refl()).get("user.name")).getOrElse(() -> "<unknown>");
      } catch (NoSuchElementException var1) {
         var10000 = "<unknown>";
      }

      return var10000;
   }

   public String getAppName() {
      return this.appName();
   }

   public void setAppId(final String id) {
      this.appId_$eq(id);
   }

   public void bind() {
      .MODULE$.assert(this.serverInfo().isEmpty(), () -> "Attempted to bind " + this.className() + " more than once!");

      try {
         ServerInfo server = this.initServer();
         server.addHandler(this.initHandler(), super.securityManager());
         this.serverInfo_$eq(new Some(server));
      } catch (Exception var3) {
         this.logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Failed to bind ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CLASS_NAME..MODULE$, this.className())})))), var3);
         System.exit(1);
      }

   }

   public void stop() {
      super.stop();
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Stopped Spark web UI at ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.WEB_URL..MODULE$, this.webUrl())})))));
   }

   public Object withSparkUI(final String appId, final Option attemptId, final Function1 fn) {
      String var4 = this.appId();
      if (appId == null) {
         if (var4 == null) {
            return fn.apply(this);
         }
      } else if (appId.equals(var4)) {
         return fn.apply(this);
      }

      throw new NoSuchElementException();
   }

   public boolean checkUIViewPermissions(final String appId, final Option attemptId, final String user) {
      return super.securityManager().checkUIViewPermissions(user);
   }

   public Iterator getApplicationInfoList() {
      Iterator var10000 = scala.package..MODULE$.Iterator();
      ScalaRunTime var10001 = scala.runtime.ScalaRunTime..MODULE$;
      ApplicationInfo[] var10002 = new ApplicationInfo[1];
      String var10007 = this.appId();
      String var10008 = this.appName();
      None var10009 = scala.None..MODULE$;
      None var10010 = scala.None..MODULE$;
      None var10011 = scala.None..MODULE$;
      None x$1;
      None var10012 = x$1 = scala.None..MODULE$;
      Date x$2 = new Date(this.startTime());
      Date x$3 = new Date(-1L);
      long x$4 = System.currentTimeMillis() - this.startTime();
      Date x$5 = new Date(this.startTime());
      String x$6 = this.getSparkUser();
      boolean x$7 = false;
      String x$8 = this.appSparkVersion();
      var10002[0] = new ApplicationInfo(var10007, var10008, var10009, var10010, var10011, var10012, new scala.collection.immutable..colon.colon(new ApplicationAttemptInfo(x$1, x$2, x$3, x$5, x$4, x$6, false, x$8), scala.collection.immutable.Nil..MODULE$));
      return var10000.apply(var10001.wrapRefArray(var10002));
   }

   public Option getApplicationInfo(final String appId) {
      return this.getApplicationInfoList().find((x$7) -> BoxesRunTime.boxToBoolean($anonfun$getApplicationInfo$1(appId, x$7)));
   }

   public Option getStreamingJobProgressListener() {
      return this.streamingJobProgressListener();
   }

   public void setStreamingJobProgressListener(final SparkListener sparkListener) {
      this.streamingJobProgressListener_$eq(scala.Option..MODULE$.apply(sparkListener));
   }

   public void clearStreamingJobProgressListener() {
      this.streamingJobProgressListener_$eq(scala.None..MODULE$);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$killEnabled$1(final SparkContext x$1) {
      return BoxesRunTime.unboxToBoolean(x$1.conf().get(UI$.MODULE$.UI_KILL_ENABLED()));
   }

   // $FF: synthetic method
   public static final void $anonfun$attachAllHandlers$2(final SparkUI $this, final ServerInfo server$1, final ServletContextHandler x$3) {
      server$1.addHandler(x$3, $this.super$securityManager());
   }

   // $FF: synthetic method
   public static final void $anonfun$attachAllHandlers$1(final SparkUI $this, final ServerInfo server) {
      server.removeHandler($this.initHandler());
      $this.handlers().foreach((x$3) -> {
         $anonfun$attachAllHandlers$2($this, server, x$3);
         return BoxedUnit.UNIT;
      });
   }

   // $FF: synthetic method
   public static final void $anonfun$attachHandler$1(final SparkUI $this, final ServletContextHandler handler$1, final ServerInfo x$4) {
      x$4.addHandler(handler$1, $this.super$securityManager());
   }

   // $FF: synthetic method
   public static final boolean $anonfun$initialize$1(final SparkContext x$5) {
      return ((Option)x$5.conf().get((ConfigEntry)package$.MODULE$.DRIVER_LOG_LOCAL_DIR())).nonEmpty();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$initialize$4(final SparkContext x$6) {
      return BoxesRunTime.unboxToBoolean(x$6.conf().get(UI$.MODULE$.UI_PROMETHEUS_ENABLED()));
   }

   // $FF: synthetic method
   public static final void $anonfun$initialize$6(final JobsTab jobsTab$1, final HttpServletRequest request) {
      jobsTab$1.handleKillRequest(request);
   }

   // $FF: synthetic method
   public static final void $anonfun$initialize$7(final StagesTab stagesTab$1, final HttpServletRequest request) {
      stagesTab$1.handleKillRequest(request);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getApplicationInfo$1(final String appId$1, final ApplicationInfo x$7) {
      boolean var3;
      label23: {
         String var10000 = x$7.id();
         if (var10000 == null) {
            if (appId$1 == null) {
               break label23;
            }
         } else if (var10000.equals(appId$1)) {
            break label23;
         }

         var3 = false;
         return var3;
      }

      var3 = true;
      return var3;
   }

   public SparkUI(final AppStatusStore store, final Option sc, final SparkConf conf, final SecurityManager securityManager, final String appName, final String basePath, final long startTime, final String appSparkVersion) {
      this.store = store;
      this.sc = sc;
      this.conf = conf;
      this.appName = appName;
      this.basePath = basePath;
      this.startTime = startTime;
      this.appSparkVersion = appSparkVersion;
      super(securityManager, securityManager.getSSLOptions("ui"), SparkUI$.MODULE$.getUIPort(conf), conf, basePath, "SparkUI", WebUI$.MODULE$.$lessinit$greater$default$7());
      UIRoot.$init$(this);
      this.killEnabled = BoxesRunTime.unboxToBoolean(sc.map((x$1) -> BoxesRunTime.boxToBoolean($anonfun$killEnabled$1(x$1))).getOrElse((JFunction0.mcZ.sp)() -> false));
      this.streamingJobProgressListener = scala.None..MODULE$;
      HttpServlet servlet = new HttpServlet() {
         public void doGet(final HttpServletRequest req, final HttpServletResponse res) {
            res.setContentType("text/html;charset=utf-8");
            res.getWriter().write("Spark is starting up. Please wait a while until it's ready.");
         }
      };
      this.initHandler = JettyUtils$.MODULE$.createServletHandler("/", servlet, basePath);
      this.readyToAttachHandlers = false;
      this.initialize();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
