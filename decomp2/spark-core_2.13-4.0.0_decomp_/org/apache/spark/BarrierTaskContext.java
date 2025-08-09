package org.apache.spark;

import java.io.Closeable;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import java.util.Properties;
import java.util.TimerTask;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.apache.spark.annotation.Experimental;
import org.apache.spark.executor.TaskMetrics;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.MessageWithContext;
import org.apache.spark.memory.TaskMemoryManager;
import org.apache.spark.rpc.AbortableRpcFuture;
import org.apache.spark.rpc.RpcEndpointRef;
import org.apache.spark.rpc.RpcTimeout;
import org.apache.spark.shuffle.FetchFailedException;
import org.apache.spark.util.AccumulatorV2;
import org.apache.spark.util.RpcUtils$;
import org.apache.spark.util.TaskCompletionListener;
import org.apache.spark.util.TaskFailureListener;
import org.apache.spark.util.Utils$;
import org.slf4j.Logger;
import scala.Enumeration;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime.;
import scala.runtime.java8.JFunction0;
import scala.util.Failure;
import scala.util.Try;

@Experimental
@ScalaSignature(
   bytes = "\u0006\u0005\r%a\u0001\u0002\u001a4\u0001iB\u0001\"\u0012\u0001\u0003\u0002\u0003\u0006Ia\u000f\u0005\u0007\r\u0002!\taM$\t\u000f)\u0003!\u0019!C\u0005\u0017\"1!\u000b\u0001Q\u0001\n1Cqa\u0015\u0001A\u0002\u0013%A\u000bC\u0004\\\u0001\u0001\u0007I\u0011\u0002/\t\r\t\u0004\u0001\u0015)\u0003V\u0011\u0015\u0019\u0007\u0001\"\u0003e\u0011\u0015\u0011\b\u0001\"\u0003t\u0011\u001d\ti\u0002\u0001C\u0001\u0003?Aq!a\u000f\u0001\t\u0003\ti\u0004C\u0004\u0002J\u0001!\t!a\u0013\t\u000f\u0005e\u0003\u0001\"\u0011\u0002\\!9\u00111\r\u0001\u0005B\u0005m\u0003bBA3\u0001\u0011\u0005\u00131\f\u0005\b\u0003O\u0002A\u0011IA5\u0011\u001d\ti\b\u0001C!\u0003\u007fBq!!#\u0001\t\u0003\nY\tC\u0004\u0002\u000e\u0002!\t%a#\t\u000f\u0005=\u0005\u0001\"\u0011\u0002\f\"9\u0011\u0011\u0013\u0001\u0005B\u0005-\u0005bBAJ\u0001\u0011\u0005\u00131\u0012\u0005\b\u0003+\u0003A\u0011IAL\u0011\u001d\tI\n\u0001C!\u00037Cq!!)\u0001\t\u0003\n\u0019\u000bC\u0004\u00022\u0002!\t%a-\t\u000f\u0005m\u0007\u0001\"\u0011\u0002\f\"9\u0011Q\u001c\u0001\u0005B\u0005}\u0007bBAz\u0001\u0011\u0005\u0013Q\u001f\u0005\t\u0005\u0007\u0001A\u0011I\u001a\u0002 !A!Q\u0001\u0001\u0005BM\u00129\u0001\u0003\u0005\u0003\f\u0001!\te\rB\u0007\u0011!\u0011Y\u0002\u0001C!g\tu\u0001\u0002\u0003B$\u0001\u0011\u00053G!\u0013\t\u0011\tm\u0003\u0001\"\u00114\u0005;B\u0001Ba\u0019\u0001\t\u0003\u001a$Q\r\u0005\t\u0005c\u0002A\u0011I\u001a\u0003t!A!Q\n\u0001\u0005BM\u0012I\b\u0003\u0005\u0003~\u0001!\te\rB@\u0011!\u00119\t\u0001C!g\u0005m\u0003\u0002\u0003BE\u0001\u0011\u00053Ga#\t\u0011\t\u0005\u0006\u0001\"\u00114\u0005G;qA!34\u0011\u0003\u0011YM\u0002\u00043g!\u0005!Q\u001a\u0005\u0007\r2\"\tAa7\t\u000f\tuG\u0006\"\u0001\u0003`\"I!Q\u001d\u0017C\u0002\u0013%!q\u001d\u0005\t\u0005kd\u0003\u0015!\u0003\u0003j\"I!q\u001f\u0017\u0002\u0002\u0013%!\u0011 \u0002\u0013\u0005\u0006\u0014(/[3s)\u0006\u001c8nQ8oi\u0016DHO\u0003\u00025k\u0005)1\u000f]1sW*\u0011agN\u0001\u0007CB\f7\r[3\u000b\u0003a\n1a\u001c:h\u0007\u0001\u00192\u0001A\u001e@!\taT(D\u00014\u0013\tq4GA\u0006UCN\\7i\u001c8uKb$\bC\u0001!D\u001b\u0005\t%B\u0001\"4\u0003!Ig\u000e^3s]\u0006d\u0017B\u0001#B\u0005\u001daunZ4j]\u001e\f1\u0002^1tW\u000e{g\u000e^3yi\u00061A(\u001b8jiz\"\"\u0001S%\u0011\u0005q\u0002\u0001\"B#\u0003\u0001\u0004Y\u0014A\u00052beJLWM]\"p_J$\u0017N\\1u_J,\u0012\u0001\u0014\t\u0003\u001bBk\u0011A\u0014\u0006\u0003\u001fN\n1A\u001d9d\u0013\t\tfJ\u0001\bSa\u000e,e\u000e\u001a9pS:$(+\u001a4\u0002'\t\f'O]5fe\u000e{wN\u001d3j]\u0006$xN\u001d\u0011\u0002\u0019\t\f'O]5fe\u0016\u0003xn\u00195\u0016\u0003U\u0003\"AV-\u000e\u0003]S\u0011\u0001W\u0001\u0006g\u000e\fG.Y\u0005\u00035^\u00131!\u00138u\u0003A\u0011\u0017M\u001d:jKJ,\u0005o\\2i?\u0012*\u0017\u000f\u0006\u0002^AB\u0011aKX\u0005\u0003?^\u0013A!\u00168ji\"9\u0011MBA\u0001\u0002\u0004)\u0016a\u0001=%c\u0005i!-\u0019:sS\u0016\u0014X\t]8dQ\u0002\nq\u0002\\8h!J|wM]3tg&sgm\u001c\u000b\u0004;\u0016T\u0007\"\u00024\t\u0001\u00049\u0017aA7tOB\u0011\u0001\t[\u0005\u0003S\u0006\u0013!#T3tg\u0006<WmV5uQ\u000e{g\u000e^3yi\")1\u000e\u0003a\u0001Y\u0006I1\u000f^1siRKW.\u001a\t\u0004-6|\u0017B\u00018X\u0005\u0019y\u0005\u000f^5p]B\u0011a\u000b]\u0005\u0003c^\u0013A\u0001T8oO\u0006Q!/\u001e8CCJ\u0014\u0018.\u001a:\u0015\u000bQ\f)!!\u0003\u0011\u0007Y+x/\u0003\u0002w/\n)\u0011I\u001d:bsB\u0011\u0001p \b\u0003sv\u0004\"A_,\u000e\u0003mT!\u0001`\u001d\u0002\rq\u0012xn\u001c;?\u0013\tqx+\u0001\u0004Qe\u0016$WMZ\u0005\u0005\u0003\u0003\t\u0019A\u0001\u0004TiJLgn\u001a\u0006\u0003}^Ca!a\u0002\n\u0001\u00049\u0018aB7fgN\fw-\u001a\u0005\b\u0003\u0017I\u0001\u0019AA\u0007\u00035\u0011X-];fgRlU\r\u001e5pIB!\u0011qBA\u000b\u001d\ra\u0014\u0011C\u0005\u0004\u0003'\u0019\u0014!\u0004*fcV,7\u000f^'fi\"|G-\u0003\u0003\u0002\u0018\u0005e!!\u0002,bYV,\u0017bAA\u000e/\nYQI\\;nKJ\fG/[8o\u0003\u001d\u0011\u0017M\u001d:jKJ$\u0012!\u0018\u0015\u0004\u0015\u0005\r\u0002\u0003BA\u0013\u0003Wi!!a\n\u000b\u0007\u0005%2'\u0001\u0006b]:|G/\u0019;j_:LA!!\f\u0002(\taQ\t\u001f9fe&lWM\u001c;bY\"*!\"!\r\u00028A!\u0011QEA\u001a\u0013\u0011\t)$a\n\u0003\u000bMKgnY3\"\u0005\u0005e\u0012!\u0002\u001a/i9\u0002\u0014!C1mY\u001e\u000bG\u000f[3s)\r!\u0018q\b\u0005\u0007\u0003\u000fY\u0001\u0019A<)\u0007-\t\u0019\u0003K\u0003\f\u0003c\t)%\t\u0002\u0002H\u0005)1G\f\u0019/a\u0005aq-\u001a;UCN\\\u0017J\u001c4pgR\u0011\u0011Q\n\t\u0005-V\fy\u0005E\u0002=\u0003#J1!a\u00154\u0005=\u0011\u0015M\u001d:jKJ$\u0016m]6J]\u001a|\u0007f\u0001\u0007\u0002$!*A\"!\r\u00028\u0005Y\u0011n]\"p[BdW\r^3e)\t\ti\u0006E\u0002W\u0003?J1!!\u0019X\u0005\u001d\u0011un\u001c7fC:\f\u0001\"[:GC&dW\rZ\u0001\u000eSNLe\u000e^3seV\u0004H/\u001a3\u00023\u0005$G\rV1tW\u000e{W\u000e\u001d7fi&|g\u000eT5ti\u0016tWM\u001d\u000b\u0005\u0003W\ni'D\u0001\u0001\u0011\u001d\ty\u0007\u0005a\u0001\u0003c\n\u0001\u0002\\5ti\u0016tWM\u001d\t\u0005\u0003g\nI(\u0004\u0002\u0002v)\u0019\u0011qO\u001a\u0002\tU$\u0018\u000e\\\u0005\u0005\u0003w\n)H\u0001\fUCN\\7i\\7qY\u0016$\u0018n\u001c8MSN$XM\\3s\u0003Y\tG\r\u001a+bg.4\u0015-\u001b7ve\u0016d\u0015n\u001d;f]\u0016\u0014H\u0003BA6\u0003\u0003Cq!a\u001c\u0012\u0001\u0004\t\u0019\t\u0005\u0003\u0002t\u0005\u0015\u0015\u0002BAD\u0003k\u00121\u0003V1tW\u001a\u000b\u0017\u000e\\;sK2K7\u000f^3oKJ\fqa\u001d;bO\u0016LE\rF\u0001V\u0003I\u0019H/Y4f\u0003R$X-\u001c9u\u001dVl'-\u001a:\u0002\u0017A\f'\u000f^5uS>t\u0017\nZ\u0001\u000e]Vl\u0007+\u0019:uSRLwN\\:\u0002\u001b\u0005$H/Z7qi:+XNY3s\u00035!\u0018m]6BiR,W\u000e\u001d;JIR\tq.\u0001\thKRdunY1m!J|\u0007/\u001a:usR\u0019q/!(\t\r\u0005}\u0005\u00041\u0001x\u0003\rYW-_\u0001\fi\u0006\u001c8.T3ue&\u001c7\u000f\u0006\u0002\u0002&B!\u0011qUAW\u001b\t\tIKC\u0002\u0002,N\n\u0001\"\u001a=fGV$xN]\u0005\u0005\u0003_\u000bIKA\u0006UCN\\W*\u001a;sS\u000e\u001c\u0018!E4fi6+GO]5dgN{WO]2fgR!\u0011QWAl!\u0019\t9,!1\u0002H:!\u0011\u0011XA_\u001d\rQ\u00181X\u0005\u00021&\u0019\u0011qX,\u0002\u000fA\f7m[1hK&!\u00111YAc\u0005\r\u0019V-\u001d\u0006\u0004\u0003\u007f;\u0006\u0003BAe\u0003'l!!a3\u000b\t\u00055\u0017qZ\u0001\u0007g>,(oY3\u000b\u0007\u0005E7'A\u0004nKR\u0014\u0018nY:\n\t\u0005U\u00171\u001a\u0002\u0007'>,(oY3\t\r\u0005e'\u00041\u0001x\u0003)\u0019x.\u001e:dK:\u000bW.Z\u0001\u0005GB,8/A\u0005sKN|WO]2fgR\u0011\u0011\u0011\u001d\t\u0007q\u0006\rx/a:\n\t\u0005\u0015\u00181\u0001\u0002\u0004\u001b\u0006\u0004\b\u0003BAu\u0003_l!!a;\u000b\u0007\u000558'\u0001\u0005sKN|WO]2f\u0013\u0011\t\t0a;\u0003'I+7o\\;sG\u0016LeNZ8s[\u0006$\u0018n\u001c8\u0002\u001bI,7o\\;sG\u0016\u001c(*T1q)\t\t9\u0010E\u0004\u0002z\n\u0005q/a:\u000e\u0005\u0005m(\u0002BA<\u0003{T!!a@\u0002\t)\fg/Y\u0005\u0005\u0003K\fY0A\u000blS2dG+Y:l\u0013\u001aLe\u000e^3seV\u0004H/\u001a3\u0002\u001b\u001d,GoS5mYJ+\u0017m]8o)\t\u0011I\u0001E\u0002W[^\f\u0011\u0003^1tW6+Wn\u001c:z\u001b\u0006t\u0017mZ3s)\t\u0011y\u0001\u0005\u0003\u0003\u0012\t]QB\u0001B\n\u0015\r\u0011)bM\u0001\u0007[\u0016lwN]=\n\t\te!1\u0003\u0002\u0012)\u0006\u001c8.T3n_JLX*\u00198bO\u0016\u0014\u0018a\u0005:fO&\u001cH/\u001a:BG\u000e,X.\u001e7bi>\u0014HcA/\u0003 !9!\u0011E\u0011A\u0002\t\r\u0012!A11\r\t\u0015\"q\u0006B\"!!\t\u0019Ha\n\u0003,\t\u0005\u0013\u0002\u0002B\u0015\u0003k\u0012Q\"Q2dk6,H.\u0019;peZ\u0013\u0004\u0003\u0002B\u0017\u0005_a\u0001\u0001\u0002\u0007\u00032\t}\u0011\u0011!A\u0001\u0006\u0003\u0011\u0019DA\u0002`IE\nBA!\u000e\u0003<A\u0019aKa\u000e\n\u0007\terKA\u0004O_RD\u0017N\\4\u0011\u0007Y\u0013i$C\u0002\u0003@]\u00131!\u00118z!\u0011\u0011iCa\u0011\u0005\u0019\t\u0015#qDA\u0001\u0002\u0003\u0015\tAa\r\u0003\u0007}##'\u0001\btKR4U\r^2i\r\u0006LG.\u001a3\u0015\u0007u\u0013Y\u0005C\u0004\u0003N\t\u0002\rAa\u0014\u0002\u0017\u0019,Go\u00195GC&dW\r\u001a\t\u0005\u0005#\u00129&\u0004\u0002\u0003T)\u0019!QK\u001a\u0002\u000fMDWO\u001a4mK&!!\u0011\fB*\u0005Q1U\r^2i\r\u0006LG.\u001a3Fq\u000e,\u0007\u000f^5p]\u0006yQ.\u0019:l\u0013:$XM\u001d:vaR,G\rF\u0002^\u0005?BaA!\u0019$\u0001\u00049\u0018A\u0002:fCN|g.\u0001\bnCJ\\G+Y:l\r\u0006LG.\u001a3\u0015\u0007u\u00139\u0007C\u0004\u0003j\u0011\u0002\rAa\u001b\u0002\u000b\u0015\u0014(o\u001c:\u0011\t\u0005]&QN\u0005\u0005\u0005_\n)MA\u0005UQJ|w/\u00192mK\u0006\tR.\u0019:l)\u0006\u001c8nQ8na2,G/\u001a3\u0015\u0007u\u0013)\bC\u0004\u0003j\u0015\u0002\rAa\u001e\u0011\tYk'1N\u000b\u0003\u0005w\u0002BAV7\u0003P\u0005\u0011r-\u001a;M_\u000e\fG\u000e\u0015:pa\u0016\u0014H/[3t+\t\u0011\t\t\u0005\u0003\u0002z\n\r\u0015\u0002\u0002BC\u0003w\u0014!\u0002\u0015:pa\u0016\u0014H/[3t\u00035Ig\u000e^3seV\u0004H/\u001b2mK\u0006\u0001\u0002/\u001a8eS:<\u0017J\u001c;feJ,\b\u000f\u001e\u000b\u0006;\n5%q\u0014\u0005\b\u0005\u001fK\u0003\u0019\u0001BI\u0003E!\bN]3bIR{\u0017J\u001c;feJ,\b\u000f\u001e\t\u0005-6\u0014\u0019\n\u0005\u0003\u0003\u0016\nmUB\u0001BL\u0015\u0011\u0011I*!@\u0002\t1\fgnZ\u0005\u0005\u0005;\u00139J\u0001\u0004UQJ,\u0017\r\u001a\u0005\u0007\u0005CJ\u0003\u0019A<\u0002;\r\u0014X-\u0019;f%\u0016\u001cx.\u001e:dKVs\u0017N\u001c;feJ,\b\u000f^5cYf,BA!*\u0003*R!!q\u0015B^!\u0011\u0011iC!+\u0005\u000f\t-&F1\u0001\u0003.\n\tA+\u0005\u0003\u00036\t=\u0006\u0003\u0002BY\u0005ok!Aa-\u000b\t\tU\u0016Q`\u0001\u0003S>LAA!/\u00034\nI1\t\\8tK\u0006\u0014G.\u001a\u0005\t\u0005{SC\u00111\u0001\u0003@\u0006y!/Z:pkJ\u001cWMQ;jY\u0012,'\u000fE\u0003W\u0005\u0003\u00149+C\u0002\u0003D^\u0013\u0001\u0002\u00102z]\u0006lWM\u0010\u0015\u0004\u0001\u0005\r\u0002&\u0002\u0001\u00022\u0005]\u0012A\u0005\"beJLWM\u001d+bg.\u001cuN\u001c;fqR\u0004\"\u0001\u0010\u0017\u0014\u000b1\u0012yM!6\u0011\u0007Y\u0013\t.C\u0002\u0003T^\u0013a!\u00118z%\u00164\u0007\u0003\u0002BY\u0005/LAA!7\u00034\na1+\u001a:jC2L'0\u00192mKR\u0011!1Z\u0001\u0004O\u0016$H#\u0001%)\u00079\n\u0019\u0003K\u0003/\u0003c\t9$A\u0003uS6,'/\u0006\u0002\u0003jB!!1\u001eBy\u001b\t\u0011iO\u0003\u0003\u0003p\u0006m\u0018AC2p]\u000e,(O]3oi&!!1\u001fBw\u0005m\u00196\r[3ek2,G\r\u00165sK\u0006$\u0007k\\8m\u000bb,7-\u001e;pe\u00061A/[7fe\u0002\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"Aa?\u0011\t\tU%Q`\u0005\u0005\u0005\u007f\u00149J\u0001\u0004PE*,7\r\u001e\u0015\u0004Y\u0005\r\u0002&\u0002\u0017\u00022\u0005]\u0002fA\u0016\u0002$!*1&!\r\u00028\u0001"
)
public class BarrierTaskContext extends TaskContext implements Logging {
   private final TaskContext taskContext;
   private final RpcEndpointRef barrierCoordinator;
   private int barrierEpoch;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   @Experimental
   public static BarrierTaskContext get() {
      return BarrierTaskContext$.MODULE$.get();
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

   private RpcEndpointRef barrierCoordinator() {
      return this.barrierCoordinator;
   }

   private int barrierEpoch() {
      return this.barrierEpoch;
   }

   private void barrierEpoch_$eq(final int x$1) {
      this.barrierEpoch = x$1;
   }

   public void org$apache$spark$BarrierTaskContext$$logProgressInfo(final MessageWithContext msg, final Option startTime) {
      MessageWithContext waitMsg = (MessageWithContext)startTime.fold(() -> this.LogStringContext(new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{""})))).log(scala.collection.immutable.Nil..MODULE$), (st) -> $anonfun$logProgressInfo$2(this, BoxesRunTime.unboxToLong(st)));
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"Task ", ""})))).log(.MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TASK_ATTEMPT_ID..MODULE$, BoxesRunTime.boxToLong(this.taskAttemptId()))}))).$plus(this.LogStringContext(new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{" from Stage ", ""})))).log(.MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STAGE_ID..MODULE$, BoxesRunTime.boxToInteger(this.stageId()))})))).$plus(this.LogStringContext(new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"(Attempt ", ") "})))).log(.MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STAGE_ATTEMPT_ID..MODULE$, BoxesRunTime.boxToInteger(this.stageAttemptNumber()))})))).$plus(msg).$plus(waitMsg).$plus(this.LogStringContext(new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{" current barrier epoch is ", "."})))).log(.MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BARRIER_EPOCH..MODULE$, BoxesRunTime.boxToInteger(this.barrierEpoch()))}))))));
   }

   private String[] runBarrier(final String message, final Enumeration.Value requestMethod) {
      this.org$apache$spark$BarrierTaskContext$$logProgressInfo(this.LogStringContext(new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"has entered the global sync"})))).log(scala.collection.immutable.Nil..MODULE$), scala.None..MODULE$);
      this.logTrace((Function0)(() -> {
         Utils$ var10000 = Utils$.MODULE$;
         return "Current callSite: " + var10000.getCallSite(Utils$.MODULE$.getCallSite$default$1());
      }));
      long startTime = System.currentTimeMillis();
      TimerTask timerTask = new TimerTask(startTime) {
         // $FF: synthetic field
         private final BarrierTaskContext $outer;
         private final long startTime$1;

         public void run() {
            this.$outer.org$apache$spark$BarrierTaskContext$$logProgressInfo(this.$outer.LogStringContext(new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"waiting under the global sync since ", ""})))).log(.MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TIME..MODULE$, BoxesRunTime.boxToLong(this.startTime$1))}))), new Some(BoxesRunTime.boxToLong(this.startTime$1)));
         }

         public {
            if (BarrierTaskContext.this == null) {
               throw null;
            } else {
               this.$outer = BarrierTaskContext.this;
               this.startTime$1 = startTime$1;
            }
         }
      };
      BarrierTaskContext$.MODULE$.org$apache$spark$BarrierTaskContext$$timer().scheduleAtFixedRate(timerTask, 1L, 1L, TimeUnit.MINUTES);

      ScheduledThreadPoolExecutor var28;
      try {
         AbortableRpcFuture abortableRpcFuture = this.barrierCoordinator().askAbortable(new RequestToSync(this.numPartitions(), this.stageId(), this.stageAttemptNumber(), this.taskAttemptId(), this.barrierEpoch(), this.partitionId(), message, requestMethod), new RpcTimeout((new scala.concurrent.duration.package.DurationInt(scala.concurrent.duration.package..MODULE$.DurationInt(365))).days(), "barrierTimeout"), scala.reflect.ClassTag..MODULE$.apply(.MODULE$.arrayClass(String.class)));

         while(!abortableRpcFuture.future().isCompleted()) {
            try {
               Thread.sleep(1000L);
            } catch (InterruptedException var23) {
            } finally {
               Try var9 = scala.util.Try..MODULE$.apply((JFunction0.mcV.sp)() -> this.taskContext.killTaskIfInterrupted());
               if (var9 instanceof scala.util.Success) {
                  BoxedUnit var27 = BoxedUnit.UNIT;
               } else {
                  if (!(var9 instanceof Failure)) {
                     throw new MatchError(var9);
                  }

                  Failure var10 = (Failure)var9;
                  Throwable e = var10.exception();
                  abortableRpcFuture.abort(e);
                  BoxedUnit var10000 = BoxedUnit.UNIT;
               }

            }
         }

         String[] messages = (String[])((Try)abortableRpcFuture.future().value().get()).get();
         this.barrierEpoch_$eq(this.barrierEpoch() + 1);
         this.org$apache$spark$BarrierTaskContext$$logProgressInfo(this.LogStringContext(new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"finished global sync successfully"})))).log(scala.collection.immutable.Nil..MODULE$), new Some(BoxesRunTime.boxToLong(startTime)));
      } catch (SparkException var25) {
         this.org$apache$spark$BarrierTaskContext$$logProgressInfo(this.LogStringContext(new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"failed to perform global sync"})))).log(scala.collection.immutable.Nil..MODULE$), new Some(BoxesRunTime.boxToLong(startTime)));
         throw var25;
      } finally {
         timerTask.cancel();
         var28 = BarrierTaskContext$.MODULE$.org$apache$spark$BarrierTaskContext$$timer();
         var28.purge();
      }

      return var28;
   }

   @Experimental
   public void barrier() {
      this.runBarrier("", RequestMethod$.MODULE$.BARRIER());
   }

   @Experimental
   public String[] allGather(final String message) {
      return this.runBarrier(message, RequestMethod$.MODULE$.ALL_GATHER());
   }

   @Experimental
   public BarrierTaskInfo[] getTaskInfos() {
      String addressesStr = (String)scala.Option..MODULE$.apply(this.taskContext.getLocalProperty("addresses")).getOrElse(() -> "");
      return (BarrierTaskInfo[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])addressesStr.split(",")), (x$1) -> x$1.trim(), scala.reflect.ClassTag..MODULE$.apply(String.class))), (x$2) -> new BarrierTaskInfo(x$2), scala.reflect.ClassTag..MODULE$.apply(BarrierTaskInfo.class));
   }

   public boolean isCompleted() {
      return this.taskContext.isCompleted();
   }

   public boolean isFailed() {
      return this.taskContext.isFailed();
   }

   public boolean isInterrupted() {
      return this.taskContext.isInterrupted();
   }

   public BarrierTaskContext addTaskCompletionListener(final TaskCompletionListener listener) {
      this.taskContext.addTaskCompletionListener(listener);
      return this;
   }

   public BarrierTaskContext addTaskFailureListener(final TaskFailureListener listener) {
      this.taskContext.addTaskFailureListener(listener);
      return this;
   }

   public int stageId() {
      return this.taskContext.stageId();
   }

   public int stageAttemptNumber() {
      return this.taskContext.stageAttemptNumber();
   }

   public int partitionId() {
      return this.taskContext.partitionId();
   }

   public int numPartitions() {
      return this.taskContext.numPartitions();
   }

   public int attemptNumber() {
      return this.taskContext.attemptNumber();
   }

   public long taskAttemptId() {
      return this.taskContext.taskAttemptId();
   }

   public String getLocalProperty(final String key) {
      return this.taskContext.getLocalProperty(key);
   }

   public TaskMetrics taskMetrics() {
      return this.taskContext.taskMetrics();
   }

   public Seq getMetricsSources(final String sourceName) {
      return this.taskContext.getMetricsSources(sourceName);
   }

   public int cpus() {
      return this.taskContext.cpus();
   }

   public scala.collection.immutable.Map resources() {
      return this.taskContext.resources();
   }

   public Map resourcesJMap() {
      return scala.jdk.CollectionConverters..MODULE$.MapHasAsJava(this.resources()).asJava();
   }

   public void killTaskIfInterrupted() {
      this.taskContext.killTaskIfInterrupted();
   }

   public Option getKillReason() {
      return this.taskContext.getKillReason();
   }

   public TaskMemoryManager taskMemoryManager() {
      return this.taskContext.taskMemoryManager();
   }

   public void registerAccumulator(final AccumulatorV2 a) {
      this.taskContext.registerAccumulator(a);
   }

   public void setFetchFailed(final FetchFailedException fetchFailed) {
      this.taskContext.setFetchFailed(fetchFailed);
   }

   public void markInterrupted(final String reason) {
      this.taskContext.markInterrupted(reason);
   }

   public void markTaskFailed(final Throwable error) {
      this.taskContext.markTaskFailed(error);
   }

   public void markTaskCompleted(final Option error) {
      this.taskContext.markTaskCompleted(error);
   }

   public Option fetchFailed() {
      return this.taskContext.fetchFailed();
   }

   public Properties getLocalProperties() {
      return this.taskContext.getLocalProperties();
   }

   public boolean interruptible() {
      return this.taskContext.interruptible();
   }

   public void pendingInterrupt(final Option threadToInterrupt, final String reason) {
      this.taskContext.pendingInterrupt(threadToInterrupt, reason);
   }

   public Closeable createResourceUninterruptibly(final Function0 resourceBuilder) {
      return this.taskContext.createResourceUninterruptibly(resourceBuilder);
   }

   // $FF: synthetic method
   public static final MessageWithContext $anonfun$logProgressInfo$2(final BarrierTaskContext $this, final long st) {
      return $this.LogStringContext(new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{", waited "})))).log(scala.collection.immutable.Nil..MODULE$).$plus($this.LogStringContext(new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"for ", " ms,"})))).log(.MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TOTAL_TIME..MODULE$, BoxesRunTime.boxToLong(System.currentTimeMillis() - st))}))));
   }

   public BarrierTaskContext(final TaskContext taskContext) {
      this.taskContext = taskContext;
      Logging.$init$(this);
      SparkEnv env = SparkEnv$.MODULE$.get();
      this.barrierCoordinator = RpcUtils$.MODULE$.makeDriverRef("barrierSync", env.conf(), env.rpcEnv());
      this.barrierEpoch = 0;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
