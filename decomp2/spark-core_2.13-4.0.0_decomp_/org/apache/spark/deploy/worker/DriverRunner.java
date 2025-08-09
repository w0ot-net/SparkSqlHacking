package org.apache.spark.deploy.worker;

import java.io.File;
import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import org.apache.spark.SecurityManager;
import org.apache.spark.SparkConf;
import org.apache.spark.deploy.DeployMessages;
import org.apache.spark.deploy.DriverDescription;
import org.apache.spark.deploy.SparkHadoopUtil$;
import org.apache.spark.deploy.StandaloneResourceUtils$;
import org.apache.spark.deploy.master.DriverState$;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.LogEntry.;
import org.apache.spark.internal.config.UI$;
import org.apache.spark.internal.config.package$;
import org.apache.spark.rpc.RpcEndpointRef;
import org.apache.spark.ui.UIUtils$;
import org.apache.spark.util.Clock;
import org.apache.spark.util.ShutdownHookManager$;
import org.apache.spark.util.SystemClock;
import org.apache.spark.util.Utils$;
import org.slf4j.Logger;
import org.sparkproject.guava.io.FileWriteMode;
import org.sparkproject.guava.io.Files;
import scala.Enumeration;
import scala.Function0;
import scala.Function1;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.collection.IterableOnce;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\t]e!\u0002\u001c8\u0001e\n\u0005\u0002\u0003(\u0001\u0005\u0003\u0005\u000b\u0011\u0002)\t\u0011Q\u0003!Q1A\u0005\u0002UC\u0001\"\u0019\u0001\u0003\u0002\u0003\u0006IA\u0016\u0005\tE\u0002\u0011)\u0019!C\u0001G\"AA\u000e\u0001B\u0001B\u0003%A\r\u0003\u0005n\u0001\t\u0015\r\u0011\"\u0001d\u0011!q\u0007A!A!\u0002\u0013!\u0007\u0002C8\u0001\u0005\u000b\u0007I\u0011\u00019\t\u0011U\u0004!\u0011!Q\u0001\nED\u0001\u0002\u000f\u0001\u0003\u0006\u0004%\tA\u001e\u0005\t{\u0002\u0011\t\u0011)A\u0005o\"Aa\u0010\u0001BC\u0002\u0013\u0005Q\u000b\u0003\u0005\u0000\u0001\t\u0005\t\u0015!\u0003W\u0011%\t\t\u0001\u0001BC\u0002\u0013\u0005Q\u000bC\u0005\u0002\u0004\u0001\u0011\t\u0011)A\u0005-\"Q\u0011Q\u0001\u0001\u0003\u0006\u0004%\t!a\u0002\t\u0015\u0005=\u0001A!A!\u0002\u0013\tI\u0001\u0003\u0006\u0002\u0012\u0001\u0011)\u0019!C\u0001\u0003'A!\"a\n\u0001\u0005\u0003\u0005\u000b\u0011BA\u000b\u0011\u001d\tI\u0003\u0001C\u0001\u0003WA\u0011\"!\u0012\u0001\u0001\u0004%I!a\u0012\t\u0013\u0005m\u0003\u00011A\u0005\n\u0005u\u0003\u0002CA5\u0001\u0001\u0006K!!\u0013\t\u0013\u0005M\u0004\u00011A\u0005\n\u0005U\u0004\"CA?\u0001\u0001\u0007I\u0011BA@\u0011!\t\u0019\t\u0001Q!\n\u0005]\u0004BCAD\u0001\u0001\u0007I\u0011A\u001c\u0002\n\"Q\u00111\u0017\u0001A\u0002\u0013\u0005q'!.\t\u0011\u0005e\u0006\u0001)Q\u0005\u0003\u0017C!\"!0\u0001\u0001\u0004%\taNA`\u0011)\t)\u000e\u0001a\u0001\n\u00039\u0014q\u001b\u0005\t\u00037\u0004\u0001\u0015)\u0003\u0002B\"I\u0011q\u001c\u0001C\u0002\u0013%\u0011\u0011\u001d\u0005\t\u0003S\u0004\u0001\u0015!\u0003\u0002d\"9\u00111\u001e\u0001\u0005\u0002\u00055\bbBA\u0000\u0001\u0011\u0005!\u0011\u0001\u0005\n\u0005\u001b\u0001\u0001\u0019!C\u0005\u0005\u001fA\u0011B!\u0005\u0001\u0001\u0004%IAa\u0005\t\u0011\t]\u0001\u0001)Q\u0005\u0003gD\u0011B!\u0007\u0001\u0001\u0004%IAa\u0007\t\u0013\t\u0015\u0002\u00011A\u0005\n\t\u001d\u0002\u0002\u0003B\u0012\u0001\u0001\u0006KA!\b\t\u0011\t-\u0002\u0001\"\u00018\u0005[A\u0001Ba\f\u0001\t\u00039$Q\u0006\u0005\b\u0005c\u0001A\u0011\u0002B\u001a\u0011\u001d\u0011)\u0004\u0001C\u0005\u0005oA\u0001B!\u0010\u0001\t\u00039$q\b\u0005\b\u0005\u000f\u0002A\u0011\u0002B%\u0011!\u0011i\u0006\u0001C\u0001o\t}sA\u0003B<o\u0005\u0005\t\u0012A\u001d\u0003z\u0019IagNA\u0001\u0012\u0003I$1\u0010\u0005\b\u0003S\u0019D\u0011\u0001B?\u0011%\u0011yhMI\u0001\n\u0003\u0011\tI\u0001\u0007Ee&4XM\u001d*v]:,'O\u0003\u00029s\u00051qo\u001c:lKJT!AO\u001e\u0002\r\u0011,\u0007\u000f\\8z\u0015\taT(A\u0003ta\u0006\u00148N\u0003\u0002?\u007f\u00051\u0011\r]1dQ\u0016T\u0011\u0001Q\u0001\u0004_J<7c\u0001\u0001C\u0011B\u00111IR\u0007\u0002\t*\tQ)A\u0003tG\u0006d\u0017-\u0003\u0002H\t\n1\u0011I\\=SK\u001a\u0004\"!\u0013'\u000e\u0003)S!aS\u001e\u0002\u0011%tG/\u001a:oC2L!!\u0014&\u0003\u000f1{wmZ5oO\u0006!1m\u001c8g\u0007\u0001\u0001\"!\u0015*\u000e\u0003mJ!aU\u001e\u0003\u0013M\u0003\u0018M]6D_:4\u0017\u0001\u00033sSZ,'/\u00133\u0016\u0003Y\u0003\"a\u00160\u000f\u0005ac\u0006CA-E\u001b\u0005Q&BA.P\u0003\u0019a$o\\8u}%\u0011Q\fR\u0001\u0007!J,G-\u001a4\n\u0005}\u0003'AB*ue&twM\u0003\u0002^\t\u0006IAM]5wKJLE\rI\u0001\bo>\u00148\u000eR5s+\u0005!\u0007CA3k\u001b\u00051'BA4i\u0003\tIwNC\u0001j\u0003\u0011Q\u0017M^1\n\u0005-4'\u0001\u0002$jY\u0016\f\u0001b^8sW\u0012K'\u000fI\u0001\ngB\f'o\u001b%p[\u0016\f!b\u001d9be.Du.\\3!\u0003)!'/\u001b<fe\u0012+7oY\u000b\u0002cB\u0011!o]\u0007\u0002s%\u0011A/\u000f\u0002\u0012\tJLg/\u001a:EKN\u001c'/\u001b9uS>t\u0017a\u00033sSZ,'\u000fR3tG\u0002*\u0012a\u001e\t\u0003qnl\u0011!\u001f\u0006\u0003un\n1A\u001d9d\u0013\ta\u0018P\u0001\bSa\u000e,e\u000e\u001a9pS:$(+\u001a4\u0002\u000f]|'o[3sA\u0005Iqo\u001c:lKJ,&\u000f\\\u0001\u000bo>\u00148.\u001a:Ve2\u0004\u0013AD<pe.,'oV3c+&,&\u000f\\\u0001\u0010o>\u00148.\u001a:XK\n,\u0016.\u0016:mA\u0005y1/Z2ve&$\u00180T1oC\u001e,'/\u0006\u0002\u0002\nA\u0019\u0011+a\u0003\n\u0007\u000551HA\bTK\u000e,(/\u001b;z\u001b\u0006t\u0017mZ3s\u0003A\u0019XmY;sSRLX*\u00198bO\u0016\u0014\b%A\u0005sKN|WO]2fgV\u0011\u0011Q\u0003\t\u0007/\u0006]a+a\u0007\n\u0007\u0005e\u0001MA\u0002NCB\u0004B!!\b\u0002$5\u0011\u0011q\u0004\u0006\u0004\u0003CY\u0014\u0001\u0003:fg>,(oY3\n\t\u0005\u0015\u0012q\u0004\u0002\u0014%\u0016\u001cx.\u001e:dK&sgm\u001c:nCRLwN\\\u0001\u000be\u0016\u001cx.\u001e:dKN\u0004\u0013A\u0002\u001fj]&$h\b\u0006\f\u0002.\u0005E\u00121GA\u001b\u0003o\tI$a\u000f\u0002>\u0005}\u0012\u0011IA\"!\r\ty\u0003A\u0007\u0002o!)a\n\u0006a\u0001!\")A\u000b\u0006a\u0001-\")!\r\u0006a\u0001I\")Q\u000e\u0006a\u0001I\")q\u000e\u0006a\u0001c\")\u0001\b\u0006a\u0001o\")a\u0010\u0006a\u0001-\"1\u0011\u0011\u0001\u000bA\u0002YCq!!\u0002\u0015\u0001\u0004\tI\u0001C\u0005\u0002\u0012Q\u0001\n\u00111\u0001\u0002\u0016\u00059\u0001O]8dKN\u001cXCAA%!\u0015\u0019\u00151JA(\u0013\r\ti\u0005\u0012\u0002\u0007\u001fB$\u0018n\u001c8\u0011\t\u0005E\u0013qK\u0007\u0003\u0003'R1!!\u0016i\u0003\u0011a\u0017M\\4\n\t\u0005e\u00131\u000b\u0002\b!J|7-Z:t\u0003-\u0001(o\\2fgN|F%Z9\u0015\t\u0005}\u0013Q\r\t\u0004\u0007\u0006\u0005\u0014bAA2\t\n!QK\\5u\u0011%\t9GFA\u0001\u0002\u0004\tI%A\u0002yIE\n\u0001\u0002\u001d:pG\u0016\u001c8\u000f\t\u0015\u0004/\u00055\u0004cA\"\u0002p%\u0019\u0011\u0011\u000f#\u0003\u0011Y|G.\u0019;jY\u0016\faa[5mY\u0016$WCAA<!\r\u0019\u0015\u0011P\u0005\u0004\u0003w\"%a\u0002\"p_2,\u0017M\\\u0001\u000bW&dG.\u001a3`I\u0015\fH\u0003BA0\u0003\u0003C\u0011\"a\u001a\u001a\u0003\u0003\u0005\r!a\u001e\u0002\u000f-LG\u000e\\3eA!\u001a!$!\u001c\u0002\u0015\u0019Lg.\u00197Ti\u0006$X-\u0006\u0002\u0002\fB)1)a\u0013\u0002\u000eB!\u0011qRAW\u001d\u0011\t\t*a*\u000f\t\u0005M\u00151\u0015\b\u0005\u0003+\u000b\tK\u0004\u0003\u0002\u0018\u0006}e\u0002BAM\u0003;s1!WAN\u0013\u0005\u0001\u0015B\u0001 @\u0013\taT(\u0003\u0002;w%\u0019\u0011QU\u001d\u0002\r5\f7\u000f^3s\u0013\u0011\tI+a+\u0002\u0017\u0011\u0013\u0018N^3s'R\fG/\u001a\u0006\u0004\u0003KK\u0014\u0002BAX\u0003c\u00131\u0002\u0012:jm\u0016\u00148\u000b^1uK*!\u0011\u0011VAV\u000391\u0017N\\1m'R\fG/Z0%KF$B!a\u0018\u00028\"I\u0011q\r\u000f\u0002\u0002\u0003\u0007\u00111R\u0001\fM&t\u0017\r\\*uCR,\u0007\u0005K\u0002\u001e\u0003[\naBZ5oC2,\u0005pY3qi&|g.\u0006\u0002\u0002BB)1)a\u0013\u0002DB!\u0011QYAh\u001d\u0011\t9-a3\u000f\u0007e\u000bI-C\u0001F\u0013\r\ti\rR\u0001\ba\u0006\u001c7.Y4f\u0013\u0011\t\t.a5\u0003\u0013\u0015C8-\u001a9uS>t'bAAg\t\u0006\u0011b-\u001b8bY\u0016C8-\u001a9uS>tw\fJ3r)\u0011\ty&!7\t\u0013\u0005\u001dt$!AA\u0002\u0005\u0005\u0017a\u00044j]\u0006dW\t_2faRLwN\u001c\u0011)\u0007\u0001\ni'\u0001\ree&4XM\u001d+fe6Lg.\u0019;f)&lWm\\;u\u001bN,\"!a9\u0011\u0007\r\u000b)/C\u0002\u0002h\u0012\u0013A\u0001T8oO\u0006IBM]5wKJ$VM]7j]\u0006$X\rV5nK>,H/T:!\u0003!\u0019X\r^\"m_\u000e\\G\u0003BA0\u0003_Dq!!=$\u0001\u0004\t\u00190\u0001\u0004`G2|7m\u001b\t\u0005\u0003k\fY0\u0004\u0002\u0002x*\u0019\u0011\u0011`\u001e\u0002\tU$\u0018\u000e\\\u0005\u0005\u0003{\f9PA\u0003DY>\u001c7.\u0001\u0006tKR\u001cF.Z3qKJ$B!a\u0018\u0003\u0004!9!Q\u0001\u0013A\u0002\t\u001d\u0011\u0001C0tY\u0016,\u0007/\u001a:\u0011\t\u0005=\"\u0011B\u0005\u0004\u0005\u00179$aB*mK\u0016\u0004XM]\u0001\u0006G2|7m[\u000b\u0003\u0003g\f\u0011b\u00197pG.|F%Z9\u0015\t\u0005}#Q\u0003\u0005\n\u0003O2\u0013\u0011!a\u0001\u0003g\faa\u00197pG.\u0004\u0013aB:mK\u0016\u0004XM]\u000b\u0003\u0005;\u0011RAa\bC\u0005\u000f1aA!\t+\u0001\tu!\u0001\u0004\u001fsK\u001aLg.Z7f]Rt\u0014\u0001C:mK\u0016\u0004XM\u001d\u0011\u0002\u0017MdW-\u001a9fe~#S-\u001d\u000b\u0005\u0003?\u0012I\u0003C\u0005\u0002h%\n\t\u00111\u0001\u0003\u001e\u0005)1\u000f^1siR\u0011\u0011qL\u0001\u0005W&dG.\u0001\fde\u0016\fG/Z,pe.Lgn\u001a#je\u0016\u001cGo\u001c:z)\u0005!\u0017a\u00043po:dw.\u00193Vg\u0016\u0014(*\u0019:\u0015\u0007Y\u0013I\u0004\u0003\u0004\u0003<9\u0002\r\u0001Z\u0001\nIJLg/\u001a:ESJ\f1\u0003\u001d:fa\u0006\u0014X-\u00118e%VtGI]5wKJ$\"A!\u0011\u0011\u0007\r\u0013\u0019%C\u0002\u0003F\u0011\u00131!\u00138u\u0003%\u0011XO\u001c#sSZ,'\u000f\u0006\u0005\u0003B\t-#Q\u000bB-\u0011\u001d\u0011i\u0005\ra\u0001\u0005\u001f\nqAY;jY\u0012,'\u000f\u0005\u0003\u0002R\tE\u0013\u0002\u0002B*\u0003'\u0012a\u0002\u0015:pG\u0016\u001c8OQ;jY\u0012,'\u000f\u0003\u0004\u0003XA\u0002\r\u0001Z\u0001\bE\u0006\u001cX\rR5s\u0011\u001d\u0011Y\u0006\ra\u0001\u0003o\n\u0011b];qKJ4\u0018n]3\u0002'I,hnQ8n[\u0006tGmV5uQJ+GO]=\u0015\u0011\t\u0005#\u0011\rB6\u0005kBqAa\u00192\u0001\u0004\u0011)'A\u0004d_6l\u0017M\u001c3\u0011\t\u0005=\"qM\u0005\u0004\u0005S:$A\u0005)s_\u000e,7o\u001d\"vS2$WM\u001d'jW\u0016DqA!\u001c2\u0001\u0004\u0011y'\u0001\u0006j]&$\u0018.\u00197ju\u0016\u0004ra\u0011B9\u0003\u001f\ny&C\u0002\u0003t\u0011\u0013\u0011BR;oGRLwN\\\u0019\t\u000f\tm\u0013\u00071\u0001\u0002x\u0005aAI]5wKJ\u0014VO\u001c8feB\u0019\u0011qF\u001a\u0014\u0005M\u0012EC\u0001B=\u0003q!C.Z:tS:LG\u000fJ4sK\u0006$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%cA*\"Aa!+\t\u0005U!QQ\u0016\u0003\u0005\u000f\u0003BA!#\u0003\u00146\u0011!1\u0012\u0006\u0005\u0005\u001b\u0013y)A\u0005v]\u000eDWmY6fI*\u0019!\u0011\u0013#\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u0003\u0016\n-%!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0002"
)
public class DriverRunner implements Logging {
   private final SparkConf conf;
   private final String driverId;
   private final File workDir;
   private final File sparkHome;
   private final DriverDescription driverDesc;
   private final RpcEndpointRef worker;
   private final String workerUrl;
   private final String workerWebUiUrl;
   private final SecurityManager securityManager;
   private final Map resources;
   private volatile Option process;
   private volatile boolean org$apache$spark$deploy$worker$DriverRunner$$killed;
   private volatile Option finalState;
   private volatile Option finalException;
   private final long driverTerminateTimeoutMs;
   private Clock clock;
   private Sleeper sleeper;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static Map $lessinit$greater$default$10() {
      return DriverRunner$.MODULE$.$lessinit$greater$default$10();
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

   public void withLogContext(final java.util.Map context, final Function0 body) {
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

   public String driverId() {
      return this.driverId;
   }

   public File workDir() {
      return this.workDir;
   }

   public File sparkHome() {
      return this.sparkHome;
   }

   public DriverDescription driverDesc() {
      return this.driverDesc;
   }

   public RpcEndpointRef worker() {
      return this.worker;
   }

   public String workerUrl() {
      return this.workerUrl;
   }

   public String workerWebUiUrl() {
      return this.workerWebUiUrl;
   }

   public SecurityManager securityManager() {
      return this.securityManager;
   }

   public Map resources() {
      return this.resources;
   }

   private Option process() {
      return this.process;
   }

   private void process_$eq(final Option x$1) {
      this.process = x$1;
   }

   public boolean org$apache$spark$deploy$worker$DriverRunner$$killed() {
      return this.org$apache$spark$deploy$worker$DriverRunner$$killed;
   }

   private void killed_$eq(final boolean x$1) {
      this.org$apache$spark$deploy$worker$DriverRunner$$killed = x$1;
   }

   public Option finalState() {
      return this.finalState;
   }

   public void finalState_$eq(final Option x$1) {
      this.finalState = x$1;
   }

   public Option finalException() {
      return this.finalException;
   }

   public void finalException_$eq(final Option x$1) {
      this.finalException = x$1;
   }

   private long driverTerminateTimeoutMs() {
      return this.driverTerminateTimeoutMs;
   }

   public void setClock(final Clock _clock) {
      this.clock_$eq(_clock);
   }

   public void setSleeper(final Sleeper _sleeper) {
      this.sleeper_$eq(_sleeper);
   }

   private Clock clock() {
      return this.clock;
   }

   private void clock_$eq(final Clock x$1) {
      this.clock = x$1;
   }

   private Sleeper sleeper() {
      return this.sleeper;
   }

   private void sleeper_$eq(final Sleeper x$1) {
      this.sleeper = x$1;
   }

   public void start() {
      (new Thread() {
         // $FF: synthetic field
         private final DriverRunner $outer;

         public void run() {
            Object shutdownHook = null;

            try {
               shutdownHook = ShutdownHookManager$.MODULE$.addShutdownHook((JFunction0.mcV.sp)() -> {
                  this.$outer.logInfo(.MODULE$.from(() -> this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Worker shutting down, killing driver ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.DRIVER_ID..MODULE$, this.$outer.driverId())})))));
                  this.$outer.kill();
               });
               int exitCode = this.$outer.prepareAndRunDriver();
               this.$outer.finalState_$eq(exitCode == 0 ? new Some(DriverState$.MODULE$.FINISHED()) : (this.$outer.org$apache$spark$deploy$worker$DriverRunner$$killed() ? new Some(DriverState$.MODULE$.KILLED()) : new Some(DriverState$.MODULE$.FAILED())));
            } catch (Exception var7) {
               this.$outer.kill();
               this.$outer.finalState_$eq(new Some(DriverState$.MODULE$.ERROR()));
               this.$outer.finalException_$eq(new Some(var7));
            } finally {
               if (shutdownHook != null) {
                  ShutdownHookManager$.MODULE$.removeShutdownHook(shutdownHook);
               }

            }

            this.$outer.worker().send(new DeployMessages.DriverStateChanged(this.$outer.driverId(), (Enumeration.Value)this.$outer.finalState().get(), this.$outer.finalException()));
         }

         public {
            if (DriverRunner.this == null) {
               throw null;
            } else {
               this.$outer = DriverRunner.this;
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      }).start();
   }

   public void kill() {
      this.logInfo((Function0)(() -> "Killing driver process!"));
      this.killed_$eq(true);
      synchronized(this){}

      try {
         this.process().foreach((p) -> {
            $anonfun$kill$2(this, p);
            return BoxedUnit.UNIT;
         });
      } catch (Throwable var3) {
         throw var3;
      }

   }

   private File createWorkingDirectory() {
      File driverDir = new File(this.workDir(), this.driverId());
      if (!driverDir.exists() && !driverDir.mkdirs()) {
         throw new IOException("Failed to create directory " + driverDir);
      } else {
         return driverDir;
      }
   }

   private String downloadUserJar(final File driverDir) {
      String jarFileName = (String)scala.collection.ArrayOps..MODULE$.last$extension(scala.Predef..MODULE$.refArrayOps((Object[])(new URI(this.driverDesc().jarUrl())).getPath().split("/")));
      File localJarFile = new File(driverDir, jarFileName);
      if (!localJarFile.exists()) {
         this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Copying user jar ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.JAR_URL..MODULE$, this.driverDesc().jarUrl())}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" to ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.FILE_NAME..MODULE$, localJarFile)}))))));
         Utils$.MODULE$.fetchFile(this.driverDesc().jarUrl(), driverDir, this.conf, SparkHadoopUtil$.MODULE$.get().newConfiguration(this.conf), System.currentTimeMillis(), false, Utils$.MODULE$.fetchFile$default$7());
         if (!localJarFile.exists()) {
            throw new IOException("Can not find expected jar " + jarFileName + " which should have been loaded in " + driverDir);
         }
      }

      return localJarFile.getAbsolutePath();
   }

   public int prepareAndRunDriver() {
      File driverDir = this.createWorkingDirectory();
      String localJarFilename = this.downloadUserJar(driverDir);
      Option resourceFileOpt = StandaloneResourceUtils$.MODULE$.prepareResourcesFile(package$.MODULE$.SPARK_DRIVER_PREFIX(), this.resources(), driverDir);
      Seq javaOpts = (Seq)this.driverDesc().command().javaOpts().$plus$plus((IterableOnce)resourceFileOpt.map((f) -> new scala.collection.immutable..colon.colon("-D" + package$.MODULE$.DRIVER_RESOURCES_FILE().key() + "=" + f.getAbsolutePath(), scala.collection.immutable.Nil..MODULE$)).getOrElse(() -> (Seq)scala.package..MODULE$.Seq().empty()));
      CommandUtils$ var10000 = CommandUtils$.MODULE$;
      String x$2 = this.driverDesc().command().copy$default$1();
      Seq x$3 = this.driverDesc().command().copy$default$2();
      scala.collection.Map x$4 = this.driverDesc().command().copy$default$3();
      Seq x$5 = this.driverDesc().command().copy$default$4();
      Seq x$6 = this.driverDesc().command().copy$default$5();
      ProcessBuilder builder = var10000.buildProcessBuilder(this.driverDesc().command().copy(x$2, x$3, x$4, x$5, x$6, javaOpts), this.securityManager(), this.driverDesc().mem(), this.sparkHome().getAbsolutePath(), (argument) -> this.substituteVariables$1(argument, localJarFilename), CommandUtils$.MODULE$.buildProcessBuilder$default$6(), CommandUtils$.MODULE$.buildProcessBuilder$default$7());
      boolean reverseProxy = BoxesRunTime.unboxToBoolean(this.conf.get(UI$.MODULE$.UI_REVERSE_PROXY()));
      String workerUrlRef = UIUtils$.MODULE$.makeHref(reverseProxy, this.driverId(), this.workerWebUiUrl());
      builder.environment().put("SPARK_DRIVER_LOG_URL_STDOUT", workerUrlRef + "/logPage/?driverId=" + this.driverId() + "&logType=stdout");
      builder.environment().put("SPARK_DRIVER_LOG_URL_STDERR", workerUrlRef + "/logPage/?driverId=" + this.driverId() + "&logType=stderr");
      return this.runDriver(builder, driverDir, this.driverDesc().supervise());
   }

   private int runDriver(final ProcessBuilder builder, final File baseDir, final boolean supervise) {
      builder.directory(baseDir);
      return this.runCommandWithRetry(ProcessBuilderLike$.MODULE$.apply(builder), (process) -> {
         $anonfun$runDriver$1(this, baseDir, builder, process);
         return BoxedUnit.UNIT;
      }, supervise);
   }

   public int runCommandWithRetry(final ProcessBuilderLike command, final Function1 initialize, final boolean supervise) {
      IntRef exitCode = IntRef.create(-1);
      IntRef waitSeconds = IntRef.create(1);
      int successfulRunDuration = 5;
      boolean keepTrying = !this.org$apache$spark$deploy$worker$DriverRunner$$killed();
      String redactedCommand = Utils$.MODULE$.redactCommandLineArgs(this.conf, command.command()).mkString("\"", "\" \"", "\"");

      while(keepTrying) {
         this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Launch Command: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.COMMAND..MODULE$, redactedCommand)})))));
         synchronized(this){}

         try {
            if (this.org$apache$spark$deploy$worker$DriverRunner$$killed()) {
               int var10 = exitCode.elem;
               return var10;
            }

            this.process_$eq(new Some(command.start()));
            initialize.apply(this.process().get());
         } catch (Throwable var14) {
            throw var14;
         }

         long processStart = this.clock().getTimeMillis();
         exitCode.elem = ((Process)this.process().get()).waitFor();
         keepTrying = supervise && exitCode.elem != 0 && !this.org$apache$spark$deploy$worker$DriverRunner$$killed();
         if (keepTrying) {
            if (this.clock().getTimeMillis() - processStart > (long)successfulRunDuration * 1000L) {
               waitSeconds.elem = 1;
            }

            this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Command exited with status ", ","})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXIT_CODE..MODULE$, BoxesRunTime.boxToInteger(exitCode.elem))}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" re-launching after ", " s."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TIME_UNITS..MODULE$, BoxesRunTime.boxToInteger(waitSeconds.elem))}))))));
            this.sleeper().sleep(waitSeconds.elem);
            waitSeconds.elem *= 2;
         }
      }

      return exitCode.elem;
   }

   // $FF: synthetic method
   public static final void $anonfun$kill$2(final DriverRunner $this, final Process p) {
      Option exitCode = Utils$.MODULE$.terminateProcess(p, $this.driverTerminateTimeoutMs());
      if (exitCode.isEmpty()) {
         $this.logWarning(.MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Failed to terminate driver process: ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PROCESS..MODULE$, p)}))).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{". This process will likely be orphaned."})))).log(scala.collection.immutable.Nil..MODULE$))));
      }
   }

   private final String substituteVariables$1(final String argument, final String localJarFilename$1) {
      switch (argument == null ? 0 : argument.hashCode()) {
         case -160836466:
            if ("{{WORKER_URL}}".equals(argument)) {
               return this.workerUrl();
            }
            break;
         case 42792135:
            if ("{{USER_JAR}}".equals(argument)) {
               return localJarFilename$1;
            }
      }

      return argument;
   }

   private final void initialize$1(final Process process, final File baseDir$1, final ProcessBuilder builder$1) {
      File stdout = new File(baseDir$1, "stdout");
      CommandUtils$.MODULE$.redirectStream(process.getInputStream(), stdout);
      File stderr = new File(baseDir$1, "stderr");
      String redactedCommand = Utils$.MODULE$.redactCommandLineArgs(this.conf, scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(builder$1.command()).asScala().toSeq()).mkString("\"", "\" \"", "\"");
      String header = scala.collection.StringOps..MODULE$.format$extension(scala.Predef..MODULE$.augmentString("Launch Command: %s\n%s\n\n"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{redactedCommand, scala.collection.StringOps..MODULE$.$times$extension(scala.Predef..MODULE$.augmentString("="), 40)}));
      Files.asCharSink(stderr, StandardCharsets.UTF_8, new FileWriteMode[]{FileWriteMode.APPEND}).write(header);
      CommandUtils$.MODULE$.redirectStream(process.getErrorStream(), stderr);
   }

   // $FF: synthetic method
   public static final void $anonfun$runDriver$1(final DriverRunner $this, final File baseDir$1, final ProcessBuilder builder$1, final Process process) {
      $this.initialize$1(process, baseDir$1, builder$1);
   }

   public DriverRunner(final SparkConf conf, final String driverId, final File workDir, final File sparkHome, final DriverDescription driverDesc, final RpcEndpointRef worker, final String workerUrl, final String workerWebUiUrl, final SecurityManager securityManager, final Map resources) {
      this.conf = conf;
      this.driverId = driverId;
      this.workDir = workDir;
      this.sparkHome = sparkHome;
      this.driverDesc = driverDesc;
      this.worker = worker;
      this.workerUrl = workerUrl;
      this.workerWebUiUrl = workerWebUiUrl;
      this.securityManager = securityManager;
      this.resources = resources;
      Logging.$init$(this);
      this.process = scala.None..MODULE$;
      this.org$apache$spark$deploy$worker$DriverRunner$$killed = false;
      this.finalState = scala.None..MODULE$;
      this.finalException = scala.None..MODULE$;
      this.driverTerminateTimeoutMs = BoxesRunTime.unboxToLong(conf.get(org.apache.spark.internal.config.Worker$.MODULE$.WORKER_DRIVER_TERMINATE_TIMEOUT()));
      this.clock = new SystemClock();
      this.sleeper = new Sleeper() {
         // $FF: synthetic field
         private final DriverRunner $outer;

         public void sleep(final int seconds) {
            scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), seconds).takeWhile((JFunction1.mcZI.sp)(x$1) -> {
               Thread.sleep(1000L);
               return !this.$outer.org$apache$spark$deploy$worker$DriverRunner$$killed();
            });
         }

         public {
            if (DriverRunner.this == null) {
               throw null;
            } else {
               this.$outer = DriverRunner.this;
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
