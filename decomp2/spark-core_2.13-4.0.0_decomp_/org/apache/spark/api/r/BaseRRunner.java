package org.apache.spark.api.r;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import org.apache.spark.SparkEnv;
import org.apache.spark.SparkEnv$;
import org.apache.spark.SparkException;
import org.apache.spark.TaskContext;
import org.apache.spark.TaskContext$;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.config.package$;
import org.slf4j.Logger;
import scala.;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.PartialFunction;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.BufferedIterator;
import scala.collection.Factory;
import scala.collection.Iterable;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.Stepper;
import scala.collection.StepperShape;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.collection.immutable.Stream;
import scala.collection.immutable.Vector;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.StringBuilder;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005\t]eA\u0002 @\u0003\u0003\u0019\u0015\n\u0003\u0005X\u0001\t\u0005\t\u0015!\u0003Z\u0011!y\u0006A!A!\u0002\u0013\u0001\u0007\u0002C6\u0001\u0005\u0003\u0005\u000b\u0011\u00021\t\u00111\u0004!\u0011!Q\u0001\neC\u0001\"\u001c\u0001\u0003\u0002\u0003\u0006IA\u001c\u0005\t{\u0002\u0011\t\u0011)A\u0005}\"Q\u00111\u0001\u0001\u0003\u0002\u0003\u0006I!!\u0002\t\u0015\u0005-\u0001A!A!\u0002\u0013\ti\u0001C\u0005\u0002\u0010\u0001\u0011\t\u0011)A\u0005}\"9\u0011\u0011\u0003\u0001\u0005\u0002\u0005M\u0001bCA$\u0001\u0001\u0007\t\u0019!C\t\u0003\u0013B1\"!\u0015\u0001\u0001\u0004\u0005\r\u0011\"\u0005\u0002T!Y\u0011q\f\u0001A\u0002\u0003\u0005\u000b\u0015BA&\u0011-\t\t\u0007\u0001a\u0001\u0002\u0004%\t\"a\u0019\t\u0017\u0005E\u0004\u00011AA\u0002\u0013E\u00111\u000f\u0005\f\u0003o\u0002\u0001\u0019!A!B\u0013\t)\u0007C\u0004\u0002z\u0001!\t!a\u001f\t\u000f\u0005e\u0005A\"\u0005\u0002\u001c\"9\u00111\u001e\u0001\u0007\u0012\u00055haBAQ\u0001\u0005\u0005\u00111\u0015\u0005\u000b\u0003K#\"\u0011!Q\u0001\n\u0005\u0015\u0004BCAT)\t\u0005\t\u0015!\u0003\u0002*\"9\u0011\u0011\u0003\u000b\u0005\u0002\u0005=\u0006bCA[)\u0001\u0007\t\u0019!C\u0005\u0003oC1\"!/\u0015\u0001\u0004\u0005\r\u0011\"\u0003\u0002<\"Y\u0011q\u0018\u000bA\u0002\u0003\u0005\u000b\u0015BA\u0018\u0011%\t\t\r\u0006a\u0001\n#\t\u0019\rC\u0005\u0002FR\u0001\r\u0011\"\u0005\u0002H\"A\u00111\u001a\u000b!B\u0013\t)\u0001C\u0004\u0002NR!\t%a1\t\u000f\u0005=G\u0003\"\u0011\u0002R\"9\u00111\u001b\u000b\u0007\u0012\u0005E\u0007\"CAk)\t\u0007I\u0011CAl\u0011!\t)\u000f\u0006Q\u0001\n\u0005egaBAy\u0001\u0005\u0005\u00111\u001f\u0005\u000b\u0003w\u001c#\u0011!Q\u0001\n\u0005u\bB\u0003B\u0002G\t\u0005\t\u0015!\u0003\u0002\u0014\"I\u0011qS\u0012\u0003\u0002\u0003\u0006IA \u0005\b\u0003#\u0019C\u0011\u0001B\u0003\u0011%\u0011ia\tb\u0001\n\u0013\u0011y\u0001\u0003\u0005\u0003\u001a\r\u0002\u000b\u0011\u0002B\t\u0011%\u0011Yb\tb\u0001\n\u0013\u0011i\u0002\u0003\u0005\u0003&\r\u0002\u000b\u0011\u0002B\u0010\u0011%\u00119c\tb\u0001\n\u0013\u0011I\u0003C\u0004\u0003,\r\u0002\u000b\u0011\u0002@\t\u0013\u0005\u00156E1A\u0005\n\t5\u0002\u0002\u0003B\u001bG\u0001\u0006IAa\f\t\u0015\t]2\u0005#b\u0001\n#\u0011I\u0004\u0003\u0006\u0003B\rB)\u0019!C\t\u0005\u0007BqAa\u0013$\t\u0003\u0012i\u0005C\u0004\u0003P\r2\tB!\u0015\b\u0011\tms\b#\u0001@\u0005;2qAP \t\u0002}\u0012y\u0006C\u0004\u0002\u0012U\"\tA!\u0019\t\u0017\u0005\u001dV\u00071A\u0001B\u0003&\u0011\u0011\u0016\u0005\f\u0005G*\u0004\u0019!A!B\u0013\u0011Y\u0004\u0003\u0006\u0003fUB)\u0019!C\u0005\u0005OBqAa\u001c6\t\u0013\u0011\t\b\u0003\u0005\u0003~U\"\ta\u0010B@\u0011\u001d\u0011))\u000eC\u0005\u0005\u000fCqA!%6\t\u0003\u0011\u0019JA\u0006CCN,'KU;o]\u0016\u0014(B\u0001!B\u0003\u0005\u0011(B\u0001\"D\u0003\r\t\u0007/\u001b\u0006\u0003\t\u0016\u000bQa\u001d9be.T!AR$\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005A\u0015aA8sOV)!*!\b\u00022M\u0019\u0001aS)\u0011\u00051{U\"A'\u000b\u00039\u000bQa]2bY\u0006L!\u0001U'\u0003\r\u0005s\u0017PU3g!\t\u0011V+D\u0001T\u0015\t!6)\u0001\u0005j]R,'O\\1m\u0013\t16KA\u0004M_\u001e<\u0017N\\4\u0002\t\u0019,hnY\u0002\u0001!\ra%\fX\u0005\u000376\u0013Q!\u0011:sCf\u0004\"\u0001T/\n\u0005yk%\u0001\u0002\"zi\u0016\fA\u0002Z3tKJL\u0017\r\\5{KJ\u0004\"!\u00195\u000f\u0005\t4\u0007CA2N\u001b\u0005!'BA3Y\u0003\u0019a$o\\8u}%\u0011q-T\u0001\u0007!J,G-\u001a4\n\u0005%T'AB*ue&twM\u0003\u0002h\u001b\u0006Q1/\u001a:jC2L'0\u001a:\u0002\u0019A\f7m[1hK:\u000bW.Z:\u0002\u001b\t\u0014x.\u00193dCN$h+\u0019:t!\ra%l\u001c\t\u0004aN,X\"A9\u000b\u0005I\u001c\u0015!\u00032s_\u0006$7-Y:u\u0013\t!\u0018OA\u0005Ce>\fGmY1tiB\u0011ao_\u0007\u0002o*\u0011\u00010_\u0001\u0005Y\u0006twMC\u0001{\u0003\u0011Q\u0017M^1\n\u0005q<(AB(cU\u0016\u001cG/A\u0007ok6\u0004\u0016M\u001d;ji&|gn\u001d\t\u0003\u0019~L1!!\u0001N\u0005\rIe\u000e^\u0001\fSN$\u0015\r^1Ge\u0006lW\rE\u0002M\u0003\u000fI1!!\u0003N\u0005\u001d\u0011un\u001c7fC:\f\u0001bY8m\u001d\u0006lWm\u001d\t\u0004\u0019j\u0003\u0017\u0001B7pI\u0016\fa\u0001P5oSRtD\u0003FA\u000b\u0003k\t9$!\u000f\u0002<\u0005u\u0012qHA!\u0003\u0007\n)\u0005E\u0004\u0002\u0018\u0001\tI\"a\f\u000e\u0003}\u0002B!a\u0007\u0002\u001e1\u0001AaBA\u0010\u0001\t\u0007\u0011\u0011\u0005\u0002\u0003\u0013:\u000bB!a\t\u0002*A\u0019A*!\n\n\u0007\u0005\u001dRJA\u0004O_RD\u0017N\\4\u0011\u00071\u000bY#C\u0002\u0002.5\u00131!\u00118z!\u0011\tY\"!\r\u0005\u000f\u0005M\u0002A1\u0001\u0002\"\t\u0019q*\u0016+\t\u000b]S\u0001\u0019A-\t\u000b}S\u0001\u0019\u00011\t\u000b-T\u0001\u0019\u00011\t\u000b1T\u0001\u0019A-\t\u000b5T\u0001\u0019\u00018\t\u000buT\u0001\u0019\u0001@\t\u000f\u0005\r!\u00021\u0001\u0002\u0006!9\u00111\u0002\u0006A\u0002\u00055\u0001BBA\b\u0015\u0001\u0007a0\u0001\u0005c_>$H+[7f+\t\tY\u0005E\u0002M\u0003\u001bJ1!a\u0014N\u0005\u0019!u.\u001e2mK\u0006a!m\\8u)&lWm\u0018\u0013fcR!\u0011QKA.!\ra\u0015qK\u0005\u0004\u00033j%\u0001B+oSRD\u0011\"!\u0018\r\u0003\u0003\u0005\r!a\u0013\u0002\u0007a$\u0013'A\u0005c_>$H+[7fA\u0005QA-\u0019;b'R\u0014X-Y7\u0016\u0005\u0005\u0015\u0004\u0003BA4\u0003[j!!!\u001b\u000b\u0007\u0005-\u00140\u0001\u0002j_&!\u0011qNA5\u0005=!\u0015\r^1J]B,Ho\u0015;sK\u0006l\u0017A\u00043bi\u0006\u001cFO]3b[~#S-\u001d\u000b\u0005\u0003+\n)\bC\u0005\u0002^=\t\t\u00111\u0001\u0002f\u0005YA-\u0019;b'R\u0014X-Y7!\u0003\u001d\u0019w.\u001c9vi\u0016$b!! \u0002\u0010\u0006U\u0005CBA@\u0003\u0013\u000byC\u0004\u0003\u0002\u0002\u0006\u0015ebA2\u0002\u0004&\ta*C\u0002\u0002\b6\u000bq\u0001]1dW\u0006<W-\u0003\u0003\u0002\f\u00065%\u0001C%uKJ\fGo\u001c:\u000b\u0007\u0005\u001dU\nC\u0004\u0002\u0012F\u0001\r!a%\u0002\u001b%t\u0007/\u001e;Ji\u0016\u0014\u0018\r^8s!\u0019\ty(!#\u0002\u001a!1\u0011qS\tA\u0002y\fa\u0002]1si&$\u0018n\u001c8J]\u0012,\u00070A\toK^\u0014V-\u00193fe&#XM]1u_J$b!!(\u0002h\u0006%\bcAAP)5\t\u0001A\u0001\bSK\u0006$WM]%uKJ\fGo\u001c:\u0014\tQY\u0015QP\u0001\u0007gR\u0014X-Y7\u0002\u0013\u0015\u0014(\u000f\u00165sK\u0006$\u0007\u0003BA\f\u0003WK1!!,@\u0005Q\u0011UO\u001a4fe\u0016$7\u000b\u001e:fC6$\u0006N]3bIR1\u0011QTAY\u0003gCq!!*\u0018\u0001\u0004\t)\u0007C\u0004\u0002(^\u0001\r!!+\u0002\u000f9,\u0007\u0010^(cUV\u0011\u0011qF\u0001\f]\u0016DHo\u00142k?\u0012*\u0017\u000f\u0006\u0003\u0002V\u0005u\u0006\"CA/3\u0005\u0005\t\u0019AA\u0018\u0003!qW\r\u001f;PE*\u0004\u0013aA3pgV\u0011\u0011QA\u0001\bK>\u001cx\fJ3r)\u0011\t)&!3\t\u0013\u0005uC$!AA\u0002\u0005\u0015\u0011\u0001B3pg\u0002\nq\u0001[1t\u001d\u0016DH/\u0001\u0003oKb$HCAA\u0018\u0003\u0011\u0011X-\u00193\u0002\u001f!\fg\u000e\u001a7f\u000bb\u001cW\r\u001d;j_:,\"!!7\u0011\u000f1\u000bY.a8\u00020%\u0019\u0011Q\\'\u0003\u001fA\u000b'\u000f^5bY\u001a+hn\u0019;j_:\u0004B!a \u0002b&!\u00111]AG\u0005%!\u0006N]8xC\ndW-\u0001\tiC:$G.Z#yG\u0016\u0004H/[8oA!9\u0011\u0011\r\nA\u0002\u0005\u0015\u0004bBAT%\u0001\u0007\u0011\u0011V\u0001\u0010]\u0016<xK]5uKJ$\u0006N]3bIRA\u0011q\u001eB+\u0005/\u0012I\u0006E\u0002\u0002 \u000e\u0012Ab\u0016:ji\u0016\u0014H\u000b\u001b:fC\u0012\u001c2aIA{!\r1\u0018q_\u0005\u0004\u0003s<(A\u0002+ie\u0016\fG-\u0001\u0004pkR\u0004X\u000f\u001e\t\u0005\u0003O\ny0\u0003\u0003\u0003\u0002\u0005%$\u0001D(viB,Ho\u0015;sK\u0006l\u0017\u0001B5uKJ$\u0002\"a<\u0003\b\t%!1\u0002\u0005\b\u0003w<\u0003\u0019AA\u007f\u0011\u001d\u0011\u0019a\na\u0001\u0003'Ca!a&(\u0001\u0004q\u0018aA3omV\u0011!\u0011\u0003\t\u0005\u0005'\u0011)\"D\u0001D\u0013\r\u00119b\u0011\u0002\t'B\f'o[#om\u0006!QM\u001c<!\u0003-!\u0018m]6D_:$X\r\u001f;\u0016\u0005\t}\u0001\u0003\u0002B\n\u0005CI1Aa\tD\u0005-!\u0016m]6D_:$X\r\u001f;\u0002\u0019Q\f7o[\"p]R,\u0007\u0010\u001e\u0011\u0002\u0015\t,hMZ3s'&TX-F\u0001\u007f\u0003-\u0011WO\u001a4feNK'0\u001a\u0011\u0016\u0005\t=\u0002\u0003BA4\u0005cIAAa\r\u0002j\t!\")\u001e4gKJ,GmT;uaV$8\u000b\u001e:fC6\fqa\u001d;sK\u0006l\u0007%A\u0004eCR\fw*\u001e;\u0016\u0005\tm\u0002\u0003BA4\u0005{IAAa\u0010\u0002j\t\u0001B)\u0019;b\u001fV$\b/\u001e;TiJ,\u0017-\\\u0001\taJLg\u000e^(viV\u0011!Q\t\t\u0005\u0003O\u00129%\u0003\u0003\u0003J\u0005%$a\u0003)sS:$8\u000b\u001e:fC6\f1A];o)\t\t)&A\u000bxe&$X-\u0013;fe\u0006$xN\u001d+p'R\u0014X-Y7\u0015\t\u0005U#1\u000b\u0005\b\u0005o\u0019\u0004\u0019\u0001B\u001e\u0011\u001d\tYp\u0005a\u0001\u0003{DqAa\u0001\u0014\u0001\u0004\t\u0019\n\u0003\u0004\u0002\u0018N\u0001\rA`\u0001\f\u0005\u0006\u001cXM\u0015*v]:,'\u000fE\u0002\u0002\u0018U\u001a\"!N&\u0015\u0005\tu\u0013!\u00043bK6|gn\u00115b]:,G.\u0001\u0006bkRD\u0007*\u001a7qKJ,\"A!\u001b\u0011\t\u0005]!1N\u0005\u0004\u0005[z$a\u0003*BkRD\u0007*\u001a7qKJ\f\u0011c\u001d;beR\u001cF\u000fZ8viRC'/Z1e)\u0011\tIKa\u001d\t\u000f\tU$\b1\u0001\u0003x\u0005!\u0001O]8d!\r1(\u0011P\u0005\u0004\u0005w:(a\u0002)s_\u000e,7o]\u0001\fO\u0016$(k\u00149uS>t7\u000fF\u0002a\u0005\u0003CaAa!<\u0001\u0004\u0001\u0017\u0001\u0003:D_6l\u0017M\u001c3\u0002\u001d\r\u0014X-\u0019;f%B\u0013xnY3tgR1\u0011\u0011\u0016BE\u0005\u001bCaAa#=\u0001\u0004q\u0018\u0001\u00029peRDaAa$=\u0001\u0004\u0001\u0017AB:de&\u0004H/A\u0007de\u0016\fG/\u001a*X_J\\WM\u001d\u000b\u0005\u0003S\u0013)\n\u0003\u0004\u0003\fv\u0002\rA "
)
public abstract class BaseRRunner implements Logging {
   public final byte[] org$apache$spark$api$r$BaseRRunner$$func;
   public final String org$apache$spark$api$r$BaseRRunner$$deserializer;
   public final String org$apache$spark$api$r$BaseRRunner$$serializer;
   public final byte[] org$apache$spark$api$r$BaseRRunner$$packageNames;
   public final Broadcast[] org$apache$spark$api$r$BaseRRunner$$broadcastVars;
   public final int org$apache$spark$api$r$BaseRRunner$$numPartitions;
   public final boolean org$apache$spark$api$r$BaseRRunner$$isDataFrame;
   public final String[] org$apache$spark$api$r$BaseRRunner$$colNames;
   public final int org$apache$spark$api$r$BaseRRunner$$mode;
   private double bootTime;
   private DataInputStream dataStream;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static BufferedStreamThread createRWorker(final int port) {
      return BaseRRunner$.MODULE$.createRWorker(port);
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

   public double bootTime() {
      return this.bootTime;
   }

   public void bootTime_$eq(final double x$1) {
      this.bootTime = x$1;
   }

   public DataInputStream dataStream() {
      return this.dataStream;
   }

   public void dataStream_$eq(final DataInputStream x$1) {
      this.dataStream = x$1;
   }

   public Iterator compute(final Iterator inputIterator, final int partitionIndex) {
      this.bootTime_$eq((double)System.currentTimeMillis() / (double)1000.0F);
      ServerSocket serverSocket = new ServerSocket(0, 2, InetAddress.getByName("localhost"));
      int listenPort = serverSocket.getLocalPort();
      BufferedStreamThread errThread = BaseRRunner$.MODULE$.createRWorker(listenPort);
      serverSocket.setSoTimeout(10000);

      DataInputStream var10001;
      try {
         Socket inSocket = serverSocket.accept();
         BaseRRunner$.MODULE$.org$apache$spark$api$r$BaseRRunner$$authHelper().authClient(inSocket);
         this.newWriterThread(inSocket.getOutputStream(), inputIterator, partitionIndex).start();
         Socket outSocket = serverSocket.accept();
         BaseRRunner$.MODULE$.org$apache$spark$api$r$BaseRRunner$$authHelper().authClient(outSocket);
         BufferedInputStream inputStream = new BufferedInputStream(outSocket.getInputStream());
         var10001 = new DataInputStream(inputStream);
      } finally {
         serverSocket.close();
      }

      this.dataStream_$eq(var10001);
      return this.newReaderIterator(this.dataStream(), errThread);
   }

   public abstract ReaderIterator newReaderIterator(final DataInputStream dataStream, final BufferedStreamThread errThread);

   public abstract WriterThread newWriterThread(final OutputStream output, final Iterator iter, final int partitionIndex);

   public BaseRRunner(final byte[] func, final String deserializer, final String serializer, final byte[] packageNames, final Broadcast[] broadcastVars, final int numPartitions, final boolean isDataFrame, final String[] colNames, final int mode) {
      this.org$apache$spark$api$r$BaseRRunner$$func = func;
      this.org$apache$spark$api$r$BaseRRunner$$deserializer = deserializer;
      this.org$apache$spark$api$r$BaseRRunner$$serializer = serializer;
      this.org$apache$spark$api$r$BaseRRunner$$packageNames = packageNames;
      this.org$apache$spark$api$r$BaseRRunner$$broadcastVars = broadcastVars;
      this.org$apache$spark$api$r$BaseRRunner$$numPartitions = numPartitions;
      this.org$apache$spark$api$r$BaseRRunner$$isDataFrame = isDataFrame;
      this.org$apache$spark$api$r$BaseRRunner$$colNames = colNames;
      this.org$apache$spark$api$r$BaseRRunner$$mode = mode;
      Logging.$init$(this);
   }

   public abstract class ReaderIterator implements Iterator {
      public final BufferedStreamThread org$apache$spark$api$r$BaseRRunner$ReaderIterator$$errThread;
      private Object nextObj;
      private boolean eos;
      private final PartialFunction handleException;
      // $FF: synthetic field
      public final BaseRRunner $outer;

      /** @deprecated */
      public final boolean hasDefiniteSize() {
         return Iterator.hasDefiniteSize$(this);
      }

      public final Iterator iterator() {
         return Iterator.iterator$(this);
      }

      public Option nextOption() {
         return Iterator.nextOption$(this);
      }

      public boolean contains(final Object elem) {
         return Iterator.contains$(this, elem);
      }

      public BufferedIterator buffered() {
         return Iterator.buffered$(this);
      }

      public Iterator padTo(final int len, final Object elem) {
         return Iterator.padTo$(this, len, elem);
      }

      public Tuple2 partition(final Function1 p) {
         return Iterator.partition$(this, p);
      }

      public Iterator.GroupedIterator grouped(final int size) {
         return Iterator.grouped$(this, size);
      }

      public Iterator.GroupedIterator sliding(final int size, final int step) {
         return Iterator.sliding$(this, size, step);
      }

      public int sliding$default$2() {
         return Iterator.sliding$default$2$(this);
      }

      public Iterator scanLeft(final Object z, final Function2 op) {
         return Iterator.scanLeft$(this, z, op);
      }

      /** @deprecated */
      public Iterator scanRight(final Object z, final Function2 op) {
         return Iterator.scanRight$(this, z, op);
      }

      public int indexWhere(final Function1 p, final int from) {
         return Iterator.indexWhere$(this, p, from);
      }

      public int indexWhere$default$2() {
         return Iterator.indexWhere$default$2$(this);
      }

      public int indexOf(final Object elem) {
         return Iterator.indexOf$(this, elem);
      }

      public int indexOf(final Object elem, final int from) {
         return Iterator.indexOf$(this, elem, from);
      }

      public final int length() {
         return Iterator.length$(this);
      }

      public boolean isEmpty() {
         return Iterator.isEmpty$(this);
      }

      public Iterator filter(final Function1 p) {
         return Iterator.filter$(this, p);
      }

      public Iterator filterNot(final Function1 p) {
         return Iterator.filterNot$(this, p);
      }

      public Iterator filterImpl(final Function1 p, final boolean isFlipped) {
         return Iterator.filterImpl$(this, p, isFlipped);
      }

      public Iterator withFilter(final Function1 p) {
         return Iterator.withFilter$(this, p);
      }

      public Iterator collect(final PartialFunction pf) {
         return Iterator.collect$(this, pf);
      }

      public Iterator distinct() {
         return Iterator.distinct$(this);
      }

      public Iterator distinctBy(final Function1 f) {
         return Iterator.distinctBy$(this, f);
      }

      public Iterator map(final Function1 f) {
         return Iterator.map$(this, f);
      }

      public Iterator flatMap(final Function1 f) {
         return Iterator.flatMap$(this, f);
      }

      public Iterator flatten(final Function1 ev) {
         return Iterator.flatten$(this, ev);
      }

      public Iterator concat(final Function0 xs) {
         return Iterator.concat$(this, xs);
      }

      public final Iterator $plus$plus(final Function0 xs) {
         return Iterator.$plus$plus$(this, xs);
      }

      public Iterator take(final int n) {
         return Iterator.take$(this, n);
      }

      public Iterator takeWhile(final Function1 p) {
         return Iterator.takeWhile$(this, p);
      }

      public Iterator drop(final int n) {
         return Iterator.drop$(this, n);
      }

      public Iterator dropWhile(final Function1 p) {
         return Iterator.dropWhile$(this, p);
      }

      public Tuple2 span(final Function1 p) {
         return Iterator.span$(this, p);
      }

      public Iterator slice(final int from, final int until) {
         return Iterator.slice$(this, from, until);
      }

      public Iterator sliceIterator(final int from, final int until) {
         return Iterator.sliceIterator$(this, from, until);
      }

      public Iterator zip(final IterableOnce that) {
         return Iterator.zip$(this, that);
      }

      public Iterator zipAll(final IterableOnce that, final Object thisElem, final Object thatElem) {
         return Iterator.zipAll$(this, that, thisElem, thatElem);
      }

      public Iterator zipWithIndex() {
         return Iterator.zipWithIndex$(this);
      }

      public boolean sameElements(final IterableOnce that) {
         return Iterator.sameElements$(this, that);
      }

      public Tuple2 duplicate() {
         return Iterator.duplicate$(this);
      }

      public Iterator patch(final int from, final Iterator patchElems, final int replaced) {
         return Iterator.patch$(this, from, patchElems, replaced);
      }

      public Iterator tapEach(final Function1 f) {
         return Iterator.tapEach$(this, f);
      }

      public String toString() {
         return Iterator.toString$(this);
      }

      /** @deprecated */
      public Iterator seq() {
         return Iterator.seq$(this);
      }

      public Tuple2 splitAt(final int n) {
         return IterableOnceOps.splitAt$(this, n);
      }

      public boolean isTraversableAgain() {
         return IterableOnceOps.isTraversableAgain$(this);
      }

      public void foreach(final Function1 f) {
         IterableOnceOps.foreach$(this, f);
      }

      public boolean forall(final Function1 p) {
         return IterableOnceOps.forall$(this, p);
      }

      public boolean exists(final Function1 p) {
         return IterableOnceOps.exists$(this, p);
      }

      public int count(final Function1 p) {
         return IterableOnceOps.count$(this, p);
      }

      public Option find(final Function1 p) {
         return IterableOnceOps.find$(this, p);
      }

      public Object foldLeft(final Object z, final Function2 op) {
         return IterableOnceOps.foldLeft$(this, z, op);
      }

      public Object foldRight(final Object z, final Function2 op) {
         return IterableOnceOps.foldRight$(this, z, op);
      }

      /** @deprecated */
      public final Object $div$colon(final Object z, final Function2 op) {
         return IterableOnceOps.$div$colon$(this, z, op);
      }

      /** @deprecated */
      public final Object $colon$bslash(final Object z, final Function2 op) {
         return IterableOnceOps.$colon$bslash$(this, z, op);
      }

      public Object fold(final Object z, final Function2 op) {
         return IterableOnceOps.fold$(this, z, op);
      }

      public Object reduce(final Function2 op) {
         return IterableOnceOps.reduce$(this, op);
      }

      public Option reduceOption(final Function2 op) {
         return IterableOnceOps.reduceOption$(this, op);
      }

      public Object reduceLeft(final Function2 op) {
         return IterableOnceOps.reduceLeft$(this, op);
      }

      public Object reduceRight(final Function2 op) {
         return IterableOnceOps.reduceRight$(this, op);
      }

      public Option reduceLeftOption(final Function2 op) {
         return IterableOnceOps.reduceLeftOption$(this, op);
      }

      public Option reduceRightOption(final Function2 op) {
         return IterableOnceOps.reduceRightOption$(this, op);
      }

      public boolean nonEmpty() {
         return IterableOnceOps.nonEmpty$(this);
      }

      public int size() {
         return IterableOnceOps.size$(this);
      }

      /** @deprecated */
      public final void copyToBuffer(final Buffer dest) {
         IterableOnceOps.copyToBuffer$(this, dest);
      }

      public int copyToArray(final Object xs) {
         return IterableOnceOps.copyToArray$(this, xs);
      }

      public int copyToArray(final Object xs, final int start) {
         return IterableOnceOps.copyToArray$(this, xs, start);
      }

      public int copyToArray(final Object xs, final int start, final int len) {
         return IterableOnceOps.copyToArray$(this, xs, start, len);
      }

      public Object sum(final Numeric num) {
         return IterableOnceOps.sum$(this, num);
      }

      public Object product(final Numeric num) {
         return IterableOnceOps.product$(this, num);
      }

      public Object min(final Ordering ord) {
         return IterableOnceOps.min$(this, ord);
      }

      public Option minOption(final Ordering ord) {
         return IterableOnceOps.minOption$(this, ord);
      }

      public Object max(final Ordering ord) {
         return IterableOnceOps.max$(this, ord);
      }

      public Option maxOption(final Ordering ord) {
         return IterableOnceOps.maxOption$(this, ord);
      }

      public Object maxBy(final Function1 f, final Ordering ord) {
         return IterableOnceOps.maxBy$(this, f, ord);
      }

      public Option maxByOption(final Function1 f, final Ordering ord) {
         return IterableOnceOps.maxByOption$(this, f, ord);
      }

      public Object minBy(final Function1 f, final Ordering ord) {
         return IterableOnceOps.minBy$(this, f, ord);
      }

      public Option minByOption(final Function1 f, final Ordering ord) {
         return IterableOnceOps.minByOption$(this, f, ord);
      }

      public Option collectFirst(final PartialFunction pf) {
         return IterableOnceOps.collectFirst$(this, pf);
      }

      /** @deprecated */
      public Object aggregate(final Function0 z, final Function2 seqop, final Function2 combop) {
         return IterableOnceOps.aggregate$(this, z, seqop, combop);
      }

      public boolean corresponds(final IterableOnce that, final Function2 p) {
         return IterableOnceOps.corresponds$(this, that, p);
      }

      public final String mkString(final String start, final String sep, final String end) {
         return IterableOnceOps.mkString$(this, start, sep, end);
      }

      public final String mkString(final String sep) {
         return IterableOnceOps.mkString$(this, sep);
      }

      public final String mkString() {
         return IterableOnceOps.mkString$(this);
      }

      public StringBuilder addString(final StringBuilder b, final String start, final String sep, final String end) {
         return IterableOnceOps.addString$(this, b, start, sep, end);
      }

      public final StringBuilder addString(final StringBuilder b, final String sep) {
         return IterableOnceOps.addString$(this, b, sep);
      }

      public final StringBuilder addString(final StringBuilder b) {
         return IterableOnceOps.addString$(this, b);
      }

      public Object to(final Factory factory) {
         return IterableOnceOps.to$(this, factory);
      }

      /** @deprecated */
      public final Iterator toIterator() {
         return IterableOnceOps.toIterator$(this);
      }

      public List toList() {
         return IterableOnceOps.toList$(this);
      }

      public Vector toVector() {
         return IterableOnceOps.toVector$(this);
      }

      public scala.collection.immutable.Map toMap(final .less.colon.less ev) {
         return IterableOnceOps.toMap$(this, ev);
      }

      public Set toSet() {
         return IterableOnceOps.toSet$(this);
      }

      public Seq toSeq() {
         return IterableOnceOps.toSeq$(this);
      }

      public IndexedSeq toIndexedSeq() {
         return IterableOnceOps.toIndexedSeq$(this);
      }

      /** @deprecated */
      public final Stream toStream() {
         return IterableOnceOps.toStream$(this);
      }

      public final Buffer toBuffer() {
         return IterableOnceOps.toBuffer$(this);
      }

      public Object toArray(final ClassTag evidence$2) {
         return IterableOnceOps.toArray$(this, evidence$2);
      }

      public Iterable reversed() {
         return IterableOnceOps.reversed$(this);
      }

      public Stepper stepper(final StepperShape shape) {
         return IterableOnce.stepper$(this, shape);
      }

      public int knownSize() {
         return IterableOnce.knownSize$(this);
      }

      private Object nextObj() {
         return this.nextObj;
      }

      private void nextObj_$eq(final Object x$1) {
         this.nextObj = x$1;
      }

      public boolean eos() {
         return this.eos;
      }

      public void eos_$eq(final boolean x$1) {
         this.eos = x$1;
      }

      public boolean hasNext() {
         boolean var10000;
         if (this.nextObj() == null) {
            label29: {
               if (!this.eos()) {
                  this.nextObj_$eq(this.read());
                  if (this.hasNext()) {
                     break label29;
                  }
               } else if (false) {
                  break label29;
               }

               var10000 = false;
               return var10000;
            }
         }

         var10000 = true;
         return var10000;
      }

      public Object next() {
         if (this.hasNext()) {
            Object obj = this.nextObj();
            this.nextObj_$eq((Object)null);
            return obj;
         } else {
            return scala.package..MODULE$.Iterator().empty().next();
         }
      }

      public abstract Object read();

      public PartialFunction handleException() {
         return this.handleException;
      }

      // $FF: synthetic method
      public BaseRRunner org$apache$spark$api$r$BaseRRunner$ReaderIterator$$$outer() {
         return this.$outer;
      }

      public ReaderIterator(final DataInputStream stream, final BufferedStreamThread errThread) {
         this.org$apache$spark$api$r$BaseRRunner$ReaderIterator$$errThread = errThread;
         if (BaseRRunner.this == null) {
            throw null;
         } else {
            this.$outer = BaseRRunner.this;
            super();
            IterableOnce.$init$(this);
            IterableOnceOps.$init$(this);
            Iterator.$init$(this);
            this.eos = false;
            this.handleException = new Serializable() {
               private static final long serialVersionUID = 0L;
               // $FF: synthetic field
               private final ReaderIterator $outer;

               public final Object applyOrElse(final Throwable x1, final Function1 default) {
                  if (x1 instanceof Exception var5) {
                     String msg = "R unexpectedly exited.";
                     String lines = this.$outer.org$apache$spark$api$r$BaseRRunner$ReaderIterator$$errThread.getLines();
                     if (scala.collection.StringOps..MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString(lines.trim()))) {
                        msg = msg + "\nR worker produced errors: " + lines + "\n";
                     }

                     throw new SparkException(msg, var5);
                  } else {
                     return default.apply(x1);
                  }
               }

               public final boolean isDefinedAt(final Throwable x1) {
                  return x1 instanceof Exception;
               }

               public {
                  if (ReaderIterator.this == null) {
                     throw null;
                  } else {
                     this.$outer = ReaderIterator.this;
                  }
               }
            };
         }
      }
   }

   public abstract class WriterThread extends Thread {
      private DataOutputStream dataOut;
      private PrintStream printOut;
      private final OutputStream output;
      private final Iterator iter;
      private final int partitionIndex;
      private final SparkEnv env;
      private final TaskContext taskContext;
      private final int bufferSize;
      private final BufferedOutputStream stream;
      private volatile byte bitmap$0;
      // $FF: synthetic field
      public final BaseRRunner $outer;

      private SparkEnv env() {
         return this.env;
      }

      private TaskContext taskContext() {
         return this.taskContext;
      }

      private int bufferSize() {
         return this.bufferSize;
      }

      private BufferedOutputStream stream() {
         return this.stream;
      }

      private DataOutputStream dataOut$lzycompute() {
         synchronized(this){}

         try {
            if ((byte)(this.bitmap$0 & 1) == 0) {
               this.dataOut = new DataOutputStream(this.stream());
               this.bitmap$0 = (byte)(this.bitmap$0 | 1);
            }
         } catch (Throwable var3) {
            throw var3;
         }

         return this.dataOut;
      }

      public DataOutputStream dataOut() {
         return (byte)(this.bitmap$0 & 1) == 0 ? this.dataOut$lzycompute() : this.dataOut;
      }

      private PrintStream printOut$lzycompute() {
         synchronized(this){}

         try {
            if ((byte)(this.bitmap$0 & 2) == 0) {
               this.printOut = new PrintStream(this.stream());
               this.bitmap$0 = (byte)(this.bitmap$0 | 2);
            }
         } catch (Throwable var3) {
            throw var3;
         }

         return this.printOut;
      }

      public PrintStream printOut() {
         return (byte)(this.bitmap$0 & 2) == 0 ? this.printOut$lzycompute() : this.printOut;
      }

      public void run() {
         try {
            SparkEnv$.MODULE$.set(this.env());
            TaskContext$.MODULE$.setTaskContext(this.taskContext());
            this.dataOut().writeInt(this.partitionIndex);
            SerDe$.MODULE$.writeString(this.dataOut(), this.org$apache$spark$api$r$BaseRRunner$WriterThread$$$outer().org$apache$spark$api$r$BaseRRunner$$deserializer);
            SerDe$.MODULE$.writeString(this.dataOut(), this.org$apache$spark$api$r$BaseRRunner$WriterThread$$$outer().org$apache$spark$api$r$BaseRRunner$$serializer);
            this.dataOut().writeInt(this.org$apache$spark$api$r$BaseRRunner$WriterThread$$$outer().org$apache$spark$api$r$BaseRRunner$$packageNames.length);
            this.dataOut().write(this.org$apache$spark$api$r$BaseRRunner$WriterThread$$$outer().org$apache$spark$api$r$BaseRRunner$$packageNames);
            this.dataOut().writeInt(this.org$apache$spark$api$r$BaseRRunner$WriterThread$$$outer().org$apache$spark$api$r$BaseRRunner$$func.length);
            this.dataOut().write(this.org$apache$spark$api$r$BaseRRunner$WriterThread$$$outer().org$apache$spark$api$r$BaseRRunner$$func);
            this.dataOut().writeInt(this.org$apache$spark$api$r$BaseRRunner$WriterThread$$$outer().org$apache$spark$api$r$BaseRRunner$$broadcastVars.length);
            scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps(this.org$apache$spark$api$r$BaseRRunner$WriterThread$$$outer().org$apache$spark$api$r$BaseRRunner$$broadcastVars), (broadcast) -> {
               $anonfun$run$1(this, broadcast);
               return BoxedUnit.UNIT;
            });
            this.dataOut().writeInt(this.org$apache$spark$api$r$BaseRRunner$WriterThread$$$outer().org$apache$spark$api$r$BaseRRunner$$numPartitions);
            this.dataOut().writeInt(this.org$apache$spark$api$r$BaseRRunner$WriterThread$$$outer().org$apache$spark$api$r$BaseRRunner$$mode);
            if (this.org$apache$spark$api$r$BaseRRunner$WriterThread$$$outer().org$apache$spark$api$r$BaseRRunner$$isDataFrame) {
               SerDe$.MODULE$.writeObject(this.dataOut(), this.org$apache$spark$api$r$BaseRRunner$WriterThread$$$outer().org$apache$spark$api$r$BaseRRunner$$colNames, (JVMObjectTracker)null);
            }

            if (!this.iter.hasNext()) {
               this.dataOut().writeInt(0);
            } else {
               this.dataOut().writeInt(1);
            }

            this.writeIteratorToStream(this.dataOut());
            this.stream().flush();
         } catch (Exception var5) {
            this.org$apache$spark$api$r$BaseRRunner$WriterThread$$$outer().logError((Function0)(() -> "R Writer thread got an exception"), var5);
         } finally {
            scala.util.Try..MODULE$.apply((JFunction0.mcV.sp)() -> this.output.close());
         }

      }

      public abstract void writeIteratorToStream(final DataOutputStream dataOut);

      // $FF: synthetic method
      public BaseRRunner org$apache$spark$api$r$BaseRRunner$WriterThread$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public static final void $anonfun$run$1(final WriterThread $this, final Broadcast broadcast) {
         $this.dataOut().writeInt((int)broadcast.id());
         byte[] broadcastByteArr = (byte[])broadcast.value();
         $this.dataOut().writeInt(broadcastByteArr.length);
         $this.dataOut().write(broadcastByteArr);
      }

      public WriterThread(final OutputStream output, final Iterator iter, final int partitionIndex) {
         this.output = output;
         this.iter = iter;
         this.partitionIndex = partitionIndex;
         if (BaseRRunner.this == null) {
            throw null;
         } else {
            this.$outer = BaseRRunner.this;
            super("writer for R");
            this.env = SparkEnv$.MODULE$.get();
            this.taskContext = TaskContext$.MODULE$.get();
            this.bufferSize = scala.collection.StringOps..MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(System.getProperty(package$.MODULE$.BUFFER_SIZE().key(), package$.MODULE$.BUFFER_SIZE().defaultValueString())));
            this.stream = new BufferedOutputStream(output, this.bufferSize());
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
