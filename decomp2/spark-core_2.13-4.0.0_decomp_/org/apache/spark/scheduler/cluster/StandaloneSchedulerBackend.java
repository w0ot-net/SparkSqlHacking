package org.apache.spark.scheduler.cluster;

import java.io.File;
import java.lang.invoke.SerializedLambda;
import java.util.Locale;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkConf$;
import org.apache.spark.SparkContext;
import org.apache.spark.deploy.ApplicationDescription;
import org.apache.spark.deploy.ApplicationDescription$;
import org.apache.spark.deploy.Command;
import org.apache.spark.deploy.client.StandaloneAppClient;
import org.apache.spark.deploy.client.StandaloneAppClientListener;
import org.apache.spark.executor.ExecutorExitCode$;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.config.ConfigEntry;
import org.apache.spark.internal.config.Tests$;
import org.apache.spark.internal.config.package$;
import org.apache.spark.launcher.LauncherBackend;
import org.apache.spark.launcher.SparkAppHandle;
import org.apache.spark.launcher.SparkAppHandle.State;
import org.apache.spark.resource.ResourceProfile;
import org.apache.spark.rpc.RpcAddress;
import org.apache.spark.rpc.RpcEndpointAddress$;
import org.apache.spark.scheduler.ExecutorDecommissionInfo;
import org.apache.spark.scheduler.ExecutorExited;
import org.apache.spark.scheduler.ExecutorLossReason;
import org.apache.spark.scheduler.ExecutorProcessLost;
import org.apache.spark.scheduler.ExecutorProcessLost$;
import org.apache.spark.scheduler.SchedulerBackend;
import org.apache.spark.scheduler.TaskSchedulerImpl;
import org.apache.spark.util.ThreadUtils$;
import org.apache.spark.util.Utils$;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.MapOps;
import scala.collection.immutable.AbstractSeq;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.concurrent.Future;
import scala.package.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005\t]e!\u0002\u001b6\u0001ez\u0004\u0002\u0003\u001d\u0001\u0005\u0003\u0005\u000b\u0011B*\t\u0011]\u0003!\u0011!Q\u0001\naC\u0001\u0002\u0018\u0001\u0003\u0002\u0003\u0006I!\u0018\u0005\u0006]\u0002!\ta\u001c\u0005\t\u000f\u0002\u0001\r\u0011\"\u0001:i\"A\u0001\u0010\u0001a\u0001\n\u0003I\u0014\u0010\u0003\u0004\u0000\u0001\u0001\u0006K!\u001e\u0005\n\u0003\u0003\u0001!\u0019!C\u0005\u0003\u0007A\u0001\"!\b\u0001A\u0003%\u0011Q\u0001\u0005\n\u0003?\u0001!\u0019!C\u0005\u0003CA\u0001\"a\f\u0001A\u0003%\u00111\u0005\u0005\f\u0003c\u0001\u0001\u0019!a\u0001\n\u0003\t\u0019\u0004C\u0006\u0002<\u0001\u0001\r\u00111A\u0005\u0002\u0005u\u0002bCA!\u0001\u0001\u0007\t\u0011)Q\u0005\u0003kA1\"a\u0013\u0001\u0001\u0004\u0005\r\u0011\"\u0003\u0002N!Y\u0011q\n\u0001A\u0002\u0003\u0007I\u0011BA)\u0011)\t)\u0006\u0001a\u0001\u0002\u0003\u0006Ka\u0019\u0005\n\u00033\u0002!\u0019!C\u0005\u00037B\u0001\"!\u001a\u0001A\u0003%\u0011Q\f\u0005\n\u0003O\u0002!\u0019!C\u0005\u0003SB\u0001\"a\u001e\u0001A\u0003%\u00111\u000e\u0005\n\u0003s\u0002!\u0019!C\u0005\u0003wB\u0001\"! \u0001A\u0003%\u0011\u0011\u000f\u0005\n\u0003\u007f\u0002!\u0019!C\u0005\u0003\u0003C\u0001\"a$\u0001A\u0003%\u00111\u0011\u0005\n\u0003#\u0003!\u0019!C\u0005\u0003'C\u0001\"a'\u0001A\u0003%\u0011Q\u0013\u0005\n\u0003;\u0003!\u0019!C\u0005\u0003?C\u0001\"a*\u0001A\u0003%\u0011\u0011\u0015\u0005\b\u0003S\u0003A\u0011IAV\u0011\u001d\ti\u000b\u0001C!\u0003WCq!a,\u0001\t\u0003\n\t\fC\u0004\u00026\u0002!\t%a+\t\u000f\u0005]\u0006\u0001\"\u0011\u0002:\"9\u0011q\u0018\u0001\u0005B\u0005\u0005\u0007bBAl\u0001\u0011\u0005\u0013\u0011\u001c\u0005\b\u0003W\u0004A\u0011IAw\u0011\u001d\tY\u0010\u0001C!\u0003{DqAa\u0002\u0001\t\u0003\u0012I\u0001C\u0004\u0003\u0012\u0001!\tEa\u0005\t\u000f\tU\u0001\u0001\"\u0015\u0003\u0018!9!Q\u0006\u0001\u0005R\t=\u0002b\u0002B$\u0001\u0011\u0005#\u0011\n\u0005\b\u0005\u001f\u0002A\u0011BAV\u0011\u001d\u0011\t\u0006\u0001C\u0005\u0003WCq!!,\u0001\t\u0013\u0011\u0019\u0006C\u0004\u0003h\u0001!\tE!\u001b\u0007\r\tM\u0004\u0001\u0002B;\u0011\u0019q\u0007\u0007\"\u0001\u0003x!9!1\u0010\u0019\u0005B\tu\u0004B\u0004BH\u0001A\u0005\u0019\u0011!A\u0005\n\tM!\u0011\u0013\u0002\u001b'R\fg\u000eZ1m_:,7k\u00195fIVdWM\u001d\"bG.,g\u000e\u001a\u0006\u0003m]\nqa\u00197vgR,'O\u0003\u00029s\u0005I1o\u00195fIVdWM\u001d\u0006\u0003um\nQa\u001d9be.T!\u0001P\u001f\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005q\u0014aA8sON!\u0001\u0001\u0011#M!\t\t%)D\u00016\u0013\t\u0019UGA\u000fD_\u0006\u00148/Z$sC&tW\rZ*dQ\u0016$W\u000f\\3s\u0005\u0006\u001c7.\u001a8e!\t)%*D\u0001G\u0015\t9\u0005*\u0001\u0004dY&,g\u000e\u001e\u0006\u0003\u0013f\na\u0001Z3qY>L\u0018BA&G\u0005m\u0019F/\u00198eC2|g.Z!qa\u000ec\u0017.\u001a8u\u0019&\u001cH/\u001a8feB\u0011Q\nU\u0007\u0002\u001d*\u0011q*O\u0001\tS:$XM\u001d8bY&\u0011\u0011K\u0014\u0002\b\u0019><w-\u001b8h\u0007\u0001\u0001\"\u0001V+\u000e\u0003]J!AV\u001c\u0003#Q\u000b7o[*dQ\u0016$W\u000f\\3s\u00136\u0004H.\u0001\u0002tGB\u0011\u0011LW\u0007\u0002s%\u00111,\u000f\u0002\r'B\f'o[\"p]R,\u0007\u0010^\u0001\b[\u0006\u001cH/\u001a:t!\rq\u0016mY\u0007\u0002?*\t\u0001-A\u0003tG\u0006d\u0017-\u0003\u0002c?\n)\u0011I\u001d:bsB\u0011Am\u001b\b\u0003K&\u0004\"AZ0\u000e\u0003\u001dT!\u0001\u001b*\u0002\rq\u0012xn\u001c;?\u0013\tQw,\u0001\u0004Qe\u0016$WMZ\u0005\u0003Y6\u0014aa\u0015;sS:<'B\u00016`\u0003\u0019a\u0014N\\5u}Q!\u0001/\u001d:t!\t\t\u0005\u0001C\u00039\t\u0001\u00071\u000bC\u0003X\t\u0001\u0007\u0001\fC\u0003]\t\u0001\u0007Q,F\u0001v!\t)e/\u0003\u0002x\r\n\u00192\u000b^1oI\u0006dwN\\3BaB\u001cE.[3oi\u0006Q1\r\\5f]R|F%Z9\u0015\u0005il\bC\u00010|\u0013\taxL\u0001\u0003V]&$\bb\u0002@\u0007\u0003\u0003\u0005\r!^\u0001\u0004q\u0012\n\u0014aB2mS\u0016tG\u000fI\u0001\tgR|\u0007\u000f]5oOV\u0011\u0011Q\u0001\t\u0005\u0003\u000f\tI\"\u0004\u0002\u0002\n)!\u00111BA\u0007\u0003\u0019\tGo\\7jG*!\u0011qBA\t\u0003)\u0019wN\\2veJ,g\u000e\u001e\u0006\u0005\u0003'\t)\"\u0001\u0003vi&d'BAA\f\u0003\u0011Q\u0017M^1\n\t\u0005m\u0011\u0011\u0002\u0002\u000e\u0003R|W.[2C_>dW-\u00198\u0002\u0013M$x\u000e\u001d9j]\u001e\u0004\u0013a\u00047bk:\u001c\u0007.\u001a:CC\u000e\\WM\u001c3\u0016\u0005\u0005\r\u0002\u0003BA\u0013\u0003Wi!!a\n\u000b\u0007\u0005%\u0012(\u0001\u0005mCVt7\r[3s\u0013\u0011\ti#a\n\u0003\u001f1\u000bWO\\2iKJ\u0014\u0015mY6f]\u0012\f\u0001\u0003\\1v]\u000eDWM\u001d\"bG.,g\u000e\u001a\u0011\u0002!MDW\u000f\u001e3po:\u001c\u0015\r\u001c7cC\u000e\\WCAA\u001b!\u0015q\u0016q\u00079{\u0013\r\tId\u0018\u0002\n\rVt7\r^5p]F\nAc\u001d5vi\u0012|wO\\\"bY2\u0014\u0017mY6`I\u0015\fHc\u0001>\u0002@!Aa0DA\u0001\u0002\u0004\t)$A\ttQV$Hm\\<o\u0007\u0006dGNY1dW\u0002B3ADA#!\rq\u0016qI\u0005\u0004\u0003\u0013z&\u0001\u0003<pY\u0006$\u0018\u000e\\3\u0002\u000b\u0005\u0004\b/\u00133\u0016\u0003\r\f\u0011\"\u00199q\u0013\u0012|F%Z9\u0015\u0007i\f\u0019\u0006C\u0004\u007f!\u0005\u0005\t\u0019A2\u0002\r\u0005\u0004\b/\u00133!Q\r\t\u0012QI\u0001\u0014e\u0016<\u0017n\u001d;sCRLwN\u001c\"beJLWM]\u000b\u0003\u0003;\u0002B!a\u0018\u0002b5\u0011\u0011QB\u0005\u0005\u0003G\niAA\u0005TK6\f\u0007\u000f[8sK\u0006!\"/Z4jgR\u0014\u0018\r^5p]\n\u000b'O]5fe\u0002\n\u0001\"\\1y\u0007>\u0014Xm]\u000b\u0003\u0003W\u0002RAXA7\u0003cJ1!a\u001c`\u0005\u0019y\u0005\u000f^5p]B\u0019a,a\u001d\n\u0007\u0005UtLA\u0002J]R\f\u0011\"\\1y\u0007>\u0014Xm\u001d\u0011\u0002%Q|G/\u00197FqB,7\r^3e\u0007>\u0014Xm]\u000b\u0003\u0003c\n1\u0003^8uC2,\u0005\u0010]3di\u0016$7i\u001c:fg\u0002\n1\u0002Z3gCVdG\u000f\u0015:pMV\u0011\u00111\u0011\t\u0005\u0003\u000b\u000bY)\u0004\u0002\u0002\b*\u0019\u0011\u0011R\u001d\u0002\u0011I,7o\\;sG\u0016LA!!$\u0002\b\ny!+Z:pkJ\u001cW\r\u0015:pM&dW-\u0001\u0007eK\u001a\fW\u000f\u001c;Qe>4\u0007%A\rfq\u0016\u001cW\u000f^8s\t\u0016d\u0017-\u001f*f[>4X\r\u00165sK\u0006$WCAAK!\u0011\ty&a&\n\t\u0005e\u0015Q\u0002\u0002\u0019'\u000eDW\rZ;mK\u0012,\u00050Z2vi>\u00148+\u001a:wS\u000e,\u0017AG3yK\u000e,Ho\u001c:EK2\f\u0017PU3n_Z,G\u000b\u001b:fC\u0012\u0004\u0013\u0001F0fq\u0016\u001cW\u000f^8s%\u0016lwN^3EK2\f\u00170\u0006\u0002\u0002\"B\u0019a,a)\n\u0007\u0005\u0015vL\u0001\u0003M_:<\u0017!F0fq\u0016\u001cW\u000f^8s%\u0016lwN^3EK2\f\u0017\u0010I\u0001\u0006gR\f'\u000f\u001e\u000b\u0002u\u0006!1\u000f^8q\u0003%\u0019wN\u001c8fGR,G\rF\u0002{\u0003gCa!a\u0013!\u0001\u0004\u0019\u0017\u0001\u00043jg\u000e|gN\\3di\u0016$\u0017\u0001\u00023fC\u0012$2A_A^\u0011\u0019\tiL\ta\u0001G\u00061!/Z1t_:\fQ\"\u001a=fGV$xN]!eI\u0016$Gc\u0003>\u0002D\u0006\u001d\u00171ZAh\u0003'Da!!2$\u0001\u0004\u0019\u0017A\u00024vY2LE\r\u0003\u0004\u0002J\u000e\u0002\raY\u0001\to>\u00148.\u001a:JI\"1\u0011QZ\u0012A\u0002\r\f\u0001\u0002[8tiB{'\u000f\u001e\u0005\b\u0003#\u001c\u0003\u0019AA9\u0003\u0015\u0019wN]3t\u0011\u001d\t)n\ta\u0001\u0003c\na!\\3n_JL\u0018aD3yK\u000e,Ho\u001c:SK6|g/\u001a3\u0015\u0013i\fY.!8\u0002b\u0006\u0015\bBBAcI\u0001\u00071\r\u0003\u0004\u0002`\u0012\u0002\raY\u0001\b[\u0016\u001c8/Y4f\u0011\u001d\t\u0019\u000f\na\u0001\u0003W\n!\"\u001a=jiN#\u0018\r^;t\u0011\u001d\t9\u000f\na\u0001\u0003S\f!b^8sW\u0016\u0014\bj\\:u!\u0011q\u0016QN2\u0002-\u0015DXmY;u_J$UmY8n[&\u001c8/[8oK\u0012$RA_Ax\u0003cDa!!2&\u0001\u0004\u0019\u0007bBAzK\u0001\u0007\u0011Q_\u0001\u0011I\u0016\u001cw.\\7jgNLwN\\%oM>\u00042\u0001VA|\u0013\r\tIp\u000e\u0002\u0019\u000bb,7-\u001e;pe\u0012+7m\\7nSN\u001c\u0018n\u001c8J]\u001a|\u0017!D<pe.,'OU3n_Z,G\rF\u0004{\u0003\u007f\u0014\tA!\u0002\t\r\u0005%g\u00051\u0001d\u0011\u0019\u0011\u0019A\na\u0001G\u0006!\u0001n\\:u\u0011\u0019\tyN\na\u0001G\u0006i2/\u001e4gS\u000eLWM\u001c;SKN|WO]2fgJ+w-[:uKJ,G\r\u0006\u0002\u0003\fA\u0019aL!\u0004\n\u0007\t=qLA\u0004C_>dW-\u00198\u0002\u001b\u0005\u0004\b\u000f\\5dCRLwN\\%e)\u0005\u0019\u0017a\u00063p%\u0016\fX/Z:u)>$\u0018\r\\#yK\u000e,Ho\u001c:t)\u0011\u0011IBa\t\u0011\r\tm!q\u0004B\u0006\u001b\t\u0011iBC\u0002\u0002\u0010}KAA!\t\u0003\u001e\t1a)\u001e;ve\u0016DqA!\n*\u0001\u0004\u00119#A\u000esKN|WO]2f!J|g-\u001b7f)>$v\u000e^1m\u000bb,7m\u001d\t\bI\n%\u00121QA9\u0013\r\u0011Y#\u001c\u0002\u0004\u001b\u0006\u0004\u0018a\u00043p\u0017&dG.\u0012=fGV$xN]:\u0015\t\te!\u0011\u0007\u0005\b\u0005gQ\u0003\u0019\u0001B\u001b\u0003-)\u00070Z2vi>\u0014\u0018\nZ:\u0011\u000b\t]\"\u0011I2\u000f\t\te\"Q\b\b\u0004M\nm\u0012\"\u00011\n\u0007\t}r,A\u0004qC\u000e\\\u0017mZ3\n\t\t\r#Q\t\u0002\u0004'\u0016\f(b\u0001B ?\u0006\u0001r-\u001a;Ee&4XM\u001d'pOV\u0013Hn]\u000b\u0003\u0005\u0017\u0002RAXA7\u0005\u001b\u0002R\u0001\u001aB\u0015G\u000e\f1c^1ji\u001a{'OU3hSN$(/\u0019;j_:\fQB\\8uS\u001aL8i\u001c8uKb$Hc\u0001>\u0003V!9!q\u000b\u0018A\u0002\te\u0013A\u00034j]\u0006d7\u000b^1uKB!!1\fB1\u001d\u0011\t)C!\u0018\n\t\t}\u0013qE\u0001\u000f'B\f'o[!qa\"\u000bg\u000e\u001a7f\u0013\u0011\u0011\u0019G!\u001a\u0003\u000bM#\u0018\r^3\u000b\t\t}\u0013qE\u0001\u0015GJ,\u0017\r^3Ee&4XM]#oIB|\u0017N\u001c;\u0015\u0005\t-\u0004\u0003\u0002B7\u0005_j\u0011\u0001A\u0005\u0004\u0005c\u0012%A\u0004#sSZ,'/\u00128ea>Lg\u000e\u001e\u0002\u0019'R\fg\u000eZ1m_:,GI]5wKJ,e\u000e\u001a9pS:$8c\u0001\u0019\u0003lQ\u0011!\u0011\u0010\t\u0004\u0005[\u0002\u0014AD8o\t&\u001c8m\u001c8oK\u000e$X\r\u001a\u000b\u0004u\n}\u0004b\u0002BAe\u0001\u0007!1Q\u0001\u000ee\u0016lw\u000e^3BI\u0012\u0014Xm]:\u0011\t\t\u0015%1R\u0007\u0003\u0005\u000fS1A!#:\u0003\r\u0011\boY\u0005\u0005\u0005\u001b\u00139I\u0001\u0006Sa\u000e\fE\r\u001a:fgN\f1c];qKJ$\u0013\r\u001d9mS\u000e\fG/[8o\u0013\u0012LAA!\u0005\u0003\u0014&\u0019!QS\u001c\u0003!M\u001b\u0007.\u001a3vY\u0016\u0014()Y2lK:$\u0007"
)
public class StandaloneSchedulerBackend extends CoarseGrainedSchedulerBackend implements StandaloneAppClientListener {
   private final TaskSchedulerImpl scheduler;
   public final SparkContext org$apache$spark$scheduler$cluster$StandaloneSchedulerBackend$$sc;
   private final String[] masters;
   private StandaloneAppClient client;
   private final AtomicBoolean org$apache$spark$scheduler$cluster$StandaloneSchedulerBackend$$stopping;
   private final LauncherBackend launcherBackend;
   private volatile Function1 shutdownCallback;
   private volatile String appId;
   private final Semaphore registrationBarrier;
   private final Option maxCores;
   private final int totalExpectedCores;
   private final ResourceProfile defaultProf;
   private final ScheduledExecutorService org$apache$spark$scheduler$cluster$StandaloneSchedulerBackend$$executorDelayRemoveThread;
   private final long org$apache$spark$scheduler$cluster$StandaloneSchedulerBackend$$_executorRemoveDelay;

   // $FF: synthetic method
   private String super$applicationId() {
      return SchedulerBackend.applicationId$(this);
   }

   public StandaloneAppClient client() {
      return this.client;
   }

   public void client_$eq(final StandaloneAppClient x$1) {
      this.client = x$1;
   }

   public AtomicBoolean org$apache$spark$scheduler$cluster$StandaloneSchedulerBackend$$stopping() {
      return this.org$apache$spark$scheduler$cluster$StandaloneSchedulerBackend$$stopping;
   }

   private LauncherBackend launcherBackend() {
      return this.launcherBackend;
   }

   public Function1 shutdownCallback() {
      return this.shutdownCallback;
   }

   public void shutdownCallback_$eq(final Function1 x$1) {
      this.shutdownCallback = x$1;
   }

   private String appId() {
      return this.appId;
   }

   private void appId_$eq(final String x$1) {
      this.appId = x$1;
   }

   private Semaphore registrationBarrier() {
      return this.registrationBarrier;
   }

   private Option maxCores() {
      return this.maxCores;
   }

   private int totalExpectedCores() {
      return this.totalExpectedCores;
   }

   private ResourceProfile defaultProf() {
      return this.defaultProf;
   }

   public ScheduledExecutorService org$apache$spark$scheduler$cluster$StandaloneSchedulerBackend$$executorDelayRemoveThread() {
      return this.org$apache$spark$scheduler$cluster$StandaloneSchedulerBackend$$executorDelayRemoveThread;
   }

   public long org$apache$spark$scheduler$cluster$StandaloneSchedulerBackend$$_executorRemoveDelay() {
      return this.org$apache$spark$scheduler$cluster$StandaloneSchedulerBackend$$_executorRemoveDelay;
   }

   public void start() {
      label28: {
         super.start();
         String var10000 = this.org$apache$spark$scheduler$cluster$StandaloneSchedulerBackend$$sc.deployMode();
         String var1 = "client";
         if (var10000 == null) {
            if (var1 != null) {
               break label28;
            }
         } else if (!var10000.equals(var1)) {
            break label28;
         }

         this.launcherBackend().connect();
      }

      String driverUrl = RpcEndpointAddress$.MODULE$.apply((String)this.org$apache$spark$scheduler$cluster$StandaloneSchedulerBackend$$sc.conf().get(package$.MODULE$.DRIVER_HOST_ADDRESS()), BoxesRunTime.unboxToInt(this.org$apache$spark$scheduler$cluster$StandaloneSchedulerBackend$$sc.conf().get(package$.MODULE$.DRIVER_PORT())), CoarseGrainedSchedulerBackend$.MODULE$.ENDPOINT_NAME()).toString();
      Seq args = (Seq).MODULE$.Seq().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"--driver-url", driverUrl, "--executor-id", "{{EXECUTOR_ID}}", "--hostname", "{{HOSTNAME}}", "--cores", "{{CORES}}", "--app-id", "{{APP_ID}}", "--worker-url", "{{WORKER_URL}}", "--resourceProfileId", "{{RESOURCE_PROFILE_ID}}"})));
      Seq extraJavaOpts = (Seq)((Option)this.org$apache$spark$scheduler$cluster$StandaloneSchedulerBackend$$sc.conf().get((ConfigEntry)package$.MODULE$.EXECUTOR_JAVA_OPTIONS())).map((s) -> Utils$.MODULE$.splitCommandString(s)).getOrElse(() -> (Seq).MODULE$.Seq().empty());
      AbstractSeq classPathEntries = (AbstractSeq)((Option)this.org$apache$spark$scheduler$cluster$StandaloneSchedulerBackend$$sc.conf().get((ConfigEntry)package$.MODULE$.EXECUTOR_CLASS_PATH())).map((x$3) -> org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(x$3.split(File.pathSeparator)).toImmutableArraySeq()).getOrElse(() -> scala.collection.immutable.Nil..MODULE$);
      AbstractSeq libraryPathEntries = (AbstractSeq)((Option)this.org$apache$spark$scheduler$cluster$StandaloneSchedulerBackend$$sc.conf().get((ConfigEntry)package$.MODULE$.EXECUTOR_LIBRARY_PATH())).map((x$4) -> org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(x$4.split(File.pathSeparator)).toImmutableArraySeq()).getOrElse(() -> scala.collection.immutable.Nil..MODULE$);
      AbstractSeq testingClassPath = (AbstractSeq)(scala.sys.package..MODULE$.props().contains(Tests$.MODULE$.IS_TESTING().key()) ? org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(((String)scala.sys.package..MODULE$.props().apply("java.class.path")).split(File.pathSeparator)).toImmutableArraySeq() : scala.collection.immutable.Nil..MODULE$);
      Seq sparkJavaOpts = Utils$.MODULE$.sparkJavaOpts(this.conf(), (name) -> BoxesRunTime.boxToBoolean($anonfun$start$7(name)));
      Seq javaOpts = (Seq)sparkJavaOpts.$plus$plus(extraJavaOpts);
      Command command = new Command("org.apache.spark.executor.CoarseGrainedExecutorBackend", args, this.org$apache$spark$scheduler$cluster$StandaloneSchedulerBackend$$sc.executorEnvs(), (Seq)classPathEntries.$plus$plus(testingClassPath), libraryPathEntries, javaOpts);
      String webUrl = (String)this.org$apache$spark$scheduler$cluster$StandaloneSchedulerBackend$$sc.ui().map((x$5) -> x$5.webUrl()).getOrElse(() -> "");
      Option coresPerExecutor = this.conf().getOption(package$.MODULE$.EXECUTOR_CORES().key()).map((x$6) -> BoxesRunTime.boxToInteger($anonfun$start$10(x$6)));
      Object var15;
      if (Utils$.MODULE$.isDynamicAllocationEnabled(this.conf())) {
         if (coresPerExecutor.isEmpty()) {
            this.logWarning(() -> "Dynamic allocation enabled without spark.executor.cores explicitly set, you may get more executors allocated than expected. It's recommended to set spark.executor.cores explicitly. Please check SPARK-30299 for more details.");
         }

         var15 = new Some(BoxesRunTime.boxToInteger(0));
      } else {
         var15 = scala.None..MODULE$;
      }

      Option initialExecutorLimit = (Option)var15;
      ApplicationDescription appDesc = new ApplicationDescription(this.org$apache$spark$scheduler$cluster$StandaloneSchedulerBackend$$sc.appName(), this.maxCores(), command, webUrl, this.defaultProf(), this.org$apache$spark$scheduler$cluster$StandaloneSchedulerBackend$$sc.eventLogDir(), this.org$apache$spark$scheduler$cluster$StandaloneSchedulerBackend$$sc.eventLogCodec(), initialExecutorLimit, ApplicationDescription$.MODULE$.apply$default$9());
      this.client_$eq(new StandaloneAppClient(this.org$apache$spark$scheduler$cluster$StandaloneSchedulerBackend$$sc.env().rpcEnv(), this.masters, appDesc, this, this.conf()));
      this.client().start();
      this.launcherBackend().setState(State.SUBMITTED);
      this.waitForRegistration();
      this.launcherBackend().setState(State.RUNNING);
   }

   public void stop() {
      this.org$apache$spark$scheduler$cluster$StandaloneSchedulerBackend$$stop(State.FINISHED);
   }

   public void connected(final String appId) {
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Connected to Spark cluster with app ID ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.APP_ID..MODULE$, appId)})))));
      this.appId_$eq(appId);
      this.notifyContext();
      this.launcherBackend().setAppId(appId);
   }

   public void disconnected() {
      this.notifyContext();
      if (!this.org$apache$spark$scheduler$cluster$StandaloneSchedulerBackend$$stopping().get()) {
         this.logWarning(() -> "Disconnected from Spark cluster! Waiting for reconnection...");
      }
   }

   public void dead(final String reason) {
      this.notifyContext();
      if (!this.org$apache$spark$scheduler$cluster$StandaloneSchedulerBackend$$stopping().get()) {
         this.launcherBackend().setState(State.KILLED);
         this.logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Application has been killed. Reason: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.REASON..MODULE$, reason)})))));

         try {
            this.scheduler.error(reason);
         } finally {
            this.org$apache$spark$scheduler$cluster$StandaloneSchedulerBackend$$sc.stopInNewThread();
         }

      }
   }

   public void executorAdded(final String fullId, final String workerId, final String hostPort, final int cores, final int memory) {
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Granted executor ID ", " on hostPort "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_ID..MODULE$, fullId)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " with ", " core(s), "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.HOST_PORT..MODULE$, hostPort), new MDC(org.apache.spark.internal.LogKeys.NUM_CORES..MODULE$, BoxesRunTime.boxToInteger(cores))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " RAM"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MEMORY_SIZE..MODULE$, Utils$.MODULE$.megabytesToString((long)memory))}))))));
   }

   public void executorRemoved(final String fullId, final String message, final Option exitStatus, final Option workerHost) {
      Object var10000;
      label43: {
         boolean var7 = false;
         Some var8 = null;
         if (exitStatus instanceof Some) {
            var7 = true;
            var8 = (Some)exitStatus;
            int var10 = BoxesRunTime.unboxToInt(var8.value());
            if (ExecutorExitCode$.MODULE$.HEARTBEAT_FAILURE() == var10) {
               var10000 = new ExecutorExited(ExecutorExitCode$.MODULE$.HEARTBEAT_FAILURE(), false, message);
               break label43;
            }
         }

         if (var7) {
            int var11 = BoxesRunTime.unboxToInt(var8.value());
            if (ExecutorExitCode$.MODULE$.BLOCK_MANAGER_REREGISTRATION_FAILED() == var11) {
               var10000 = new ExecutorExited(ExecutorExitCode$.MODULE$.BLOCK_MANAGER_REREGISTRATION_FAILED(), false, message);
               break label43;
            }
         }

         if (var7) {
            int var12 = BoxesRunTime.unboxToInt(var8.value());
            if (ExecutorExitCode$.MODULE$.DISK_STORE_FAILED_TO_CREATE_DIR() == var12) {
               var10000 = new ExecutorExited(ExecutorExitCode$.MODULE$.DISK_STORE_FAILED_TO_CREATE_DIR(), false, message);
               break label43;
            }
         }

         if (var7) {
            int code = BoxesRunTime.unboxToInt(var8.value());
            var10000 = new ExecutorExited(code, true, message);
         } else {
            if (!scala.None..MODULE$.equals(exitStatus)) {
               throw new MatchError(exitStatus);
            }

            var10000 = new ExecutorProcessLost(message, workerHost, workerHost.isEmpty());
         }
      }

      ExecutorLossReason reason = (ExecutorLossReason)var10000;
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Executor ", " removed: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_ID..MODULE$, fullId), new MDC(org.apache.spark.internal.LogKeys.MESSAGE..MODULE$, message)})))));
      this.removeExecutor(fullId.split("/")[1], reason);
   }

   public void executorDecommissioned(final String fullId, final ExecutorDecommissionInfo decommissionInfo) {
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Asked to decommission executor ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_ID..MODULE$, fullId)})))));
      String execId = fullId.split("/")[1];
      this.decommissionExecutors((Tuple2[])((Object[])(new Tuple2[]{new Tuple2(execId, decommissionInfo)})), false, false);
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Executor ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_ID..MODULE$, fullId)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"decommissioned: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.DESCRIPTION..MODULE$, decommissionInfo)}))))));
   }

   public void workerRemoved(final String workerId, final String host, final String message) {
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Worker ", " removed: "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.WORKER_ID..MODULE$, workerId)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MESSAGE..MODULE$, message)}))))));
      this.removeWorker(workerId, host, message);
   }

   public boolean sufficientResourcesRegistered() {
      return (double)this.totalCoreCount().get() >= (double)this.totalExpectedCores() * this.minRegisteredRatio();
   }

   public String applicationId() {
      return (String)scala.Option..MODULE$.apply(this.appId()).getOrElse(() -> {
         this.logWarning(() -> "Application ID is not initialized yet.");
         return this.super$applicationId();
      });
   }

   public Future doRequestTotalExecutors(final Map resourceProfileToTotalExecs) {
      Option var3 = scala.Option..MODULE$.apply(this.client());
      if (var3 instanceof Some var4) {
         StandaloneAppClient c = (StandaloneAppClient)var4.value();
         return c.requestTotalExecutors(resourceProfileToTotalExecs);
      } else if (scala.None..MODULE$.equals(var3)) {
         this.logWarning(() -> "Attempted to request executors before driver fully initialized.");
         return scala.concurrent.Future..MODULE$.successful(BoxesRunTime.boxToBoolean(false));
      } else {
         throw new MatchError(var3);
      }
   }

   public Future doKillExecutors(final Seq executorIds) {
      Option var3 = scala.Option..MODULE$.apply(this.client());
      if (var3 instanceof Some var4) {
         StandaloneAppClient c = (StandaloneAppClient)var4.value();
         return c.killExecutors(executorIds);
      } else if (scala.None..MODULE$.equals(var3)) {
         this.logWarning(() -> "Attempted to kill executors before driver fully initialized.");
         return scala.concurrent.Future..MODULE$.successful(BoxesRunTime.boxToBoolean(false));
      } else {
         throw new MatchError(var3);
      }
   }

   public Option getDriverLogUrls() {
      String prefix = "SPARK_DRIVER_LOG_URL_";
      Map driverLogUrls = (Map)((MapOps)scala.sys.package..MODULE$.env().filter((x0$1) -> BoxesRunTime.boxToBoolean($anonfun$getDriverLogUrls$1(prefix, x0$1)))).map((e) -> new Tuple2(((String)e._1()).substring(prefix.length()).toLowerCase(Locale.ROOT), e._2()));
      return (Option)(driverLogUrls.nonEmpty() ? new Some(driverLogUrls) : scala.None..MODULE$);
   }

   private void waitForRegistration() {
      this.registrationBarrier().acquire();
   }

   private void notifyContext() {
      this.registrationBarrier().release();
   }

   public void org$apache$spark$scheduler$cluster$StandaloneSchedulerBackend$$stop(final SparkAppHandle.State finalState) {
      if (this.org$apache$spark$scheduler$cluster$StandaloneSchedulerBackend$$stopping().compareAndSet(false, true)) {
         try {
            this.org$apache$spark$scheduler$cluster$StandaloneSchedulerBackend$$executorDelayRemoveThread().shutdownNow();
            super.stop();
            if (this.client() != null) {
               this.client().stop();
            }

            Function1 callback = this.shutdownCallback();
            if (callback != null) {
               callback.apply(this);
            }
         } finally {
            this.launcherBackend().setState(finalState);
            this.launcherBackend().close();
         }

      }
   }

   public CoarseGrainedSchedulerBackend.DriverEndpoint createDriverEndpoint() {
      return new StandaloneDriverEndpoint();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$start$7(final String name) {
      return SparkConf$.MODULE$.isExecutorStartupConf(name);
   }

   // $FF: synthetic method
   public static final int $anonfun$start$10(final String x$6) {
      return scala.collection.StringOps..MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(x$6));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getDriverLogUrls$1(final String prefix$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         String k = (String)x0$1._1();
         return k.startsWith(prefix$1);
      } else {
         throw new MatchError(x0$1);
      }
   }

   public StandaloneSchedulerBackend(final TaskSchedulerImpl scheduler, final SparkContext sc, final String[] masters) {
      super(scheduler, sc.env().rpcEnv());
      this.scheduler = scheduler;
      this.org$apache$spark$scheduler$cluster$StandaloneSchedulerBackend$$sc = sc;
      this.masters = masters;
      this.client = null;
      this.org$apache$spark$scheduler$cluster$StandaloneSchedulerBackend$$stopping = new AtomicBoolean(false);
      this.launcherBackend = new LauncherBackend() {
         // $FF: synthetic field
         private final StandaloneSchedulerBackend $outer;

         public SparkConf conf() {
            return this.$outer.org$apache$spark$scheduler$cluster$StandaloneSchedulerBackend$$sc.conf();
         }

         public void onStopRequest() {
            this.$outer.org$apache$spark$scheduler$cluster$StandaloneSchedulerBackend$$stop(State.KILLED);
         }

         public {
            if (StandaloneSchedulerBackend.this == null) {
               throw null;
            } else {
               this.$outer = StandaloneSchedulerBackend.this;
            }
         }
      };
      this.registrationBarrier = new Semaphore(0);
      this.maxCores = (Option)this.conf().get((ConfigEntry)package$.MODULE$.CORES_MAX());
      this.totalExpectedCores = BoxesRunTime.unboxToInt(this.maxCores().getOrElse((JFunction0.mcI.sp)() -> 0));
      this.defaultProf = sc.resourceProfileManager().defaultResourceProfile();
      this.org$apache$spark$scheduler$cluster$StandaloneSchedulerBackend$$executorDelayRemoveThread = ThreadUtils$.MODULE$.newDaemonSingleThreadScheduledExecutor("driver-executor-delay-remove-thread");
      this.org$apache$spark$scheduler$cluster$StandaloneSchedulerBackend$$_executorRemoveDelay = BoxesRunTime.unboxToLong(this.conf().get(package$.MODULE$.EXECUTOR_REMOVE_DELAY()));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   private class StandaloneDriverEndpoint extends CoarseGrainedSchedulerBackend.DriverEndpoint {
      public void onDisconnected(final RpcAddress remoteAddress) {
         this.addressToExecutorId().get(remoteAddress).foreach((executorId) -> {
            this.org$apache$spark$scheduler$cluster$StandaloneSchedulerBackend$StandaloneDriverEndpoint$$$outer().executorsPendingLossReason().$plus$eq(executorId);
            ExecutorProcessLost lossReason = new ExecutorProcessLost("Remote RPC client disassociated. Likely due to containers exceeding thresholds, or network issues. Check driver logs for WARN messages.", ExecutorProcessLost$.MODULE$.apply$default$2(), ExecutorProcessLost$.MODULE$.apply$default$3());
            Runnable removeExecutorTask = new Runnable(executorId, lossReason) {
               // $FF: synthetic field
               private final StandaloneDriverEndpoint $outer;
               private final String executorId$1;
               private final ExecutorProcessLost lossReason$1;

               public void run() {
                  Utils$.MODULE$.tryLogNonFatalError((JFunction0.mcV.sp)() -> {
                     if (this.$outer.org$apache$spark$scheduler$cluster$StandaloneSchedulerBackend$StandaloneDriverEndpoint$$$outer().executorsPendingLossReason().contains(this.executorId$1)) {
                        this.$outer.org$apache$spark$scheduler$cluster$StandaloneSchedulerBackend$StandaloneDriverEndpoint$$$outer().driverEndpoint().send(new CoarseGrainedClusterMessages.RemoveExecutor(this.executorId$1, this.lossReason$1));
                     }
                  });
               }

               public {
                  if (StandaloneDriverEndpoint.this == null) {
                     throw null;
                  } else {
                     this.$outer = StandaloneDriverEndpoint.this;
                     this.executorId$1 = executorId$1;
                     this.lossReason$1 = lossReason$1;
                  }
               }

               // $FF: synthetic method
               private static Object $deserializeLambda$(SerializedLambda var0) {
                  return var0.lambdaDeserialize<invokedynamic>(var0);
               }
            };

            Object var10000;
            try {
               var10000 = this.org$apache$spark$scheduler$cluster$StandaloneSchedulerBackend$StandaloneDriverEndpoint$$$outer().org$apache$spark$scheduler$cluster$StandaloneSchedulerBackend$$executorDelayRemoveThread().schedule(removeExecutorTask, this.org$apache$spark$scheduler$cluster$StandaloneSchedulerBackend$StandaloneDriverEndpoint$$$outer().org$apache$spark$scheduler$cluster$StandaloneSchedulerBackend$$_executorRemoveDelay(), TimeUnit.MILLISECONDS);
            } catch (Throwable var7) {
               if (!(var7 instanceof RejectedExecutionException) || !this.org$apache$spark$scheduler$cluster$StandaloneSchedulerBackend$StandaloneDriverEndpoint$$$outer().org$apache$spark$scheduler$cluster$StandaloneSchedulerBackend$$stopping().get()) {
                  throw var7;
               }

               this.logWarning(() -> "Skipping onDisconnected RemoveExecutor call because the scheduler is stopping");
               var10000 = BoxedUnit.UNIT;
            }

            return var10000;
         });
      }

      // $FF: synthetic method
      public StandaloneSchedulerBackend org$apache$spark$scheduler$cluster$StandaloneSchedulerBackend$StandaloneDriverEndpoint$$$outer() {
         return (StandaloneSchedulerBackend)this.$outer;
      }

      public StandaloneDriverEndpoint() {
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
