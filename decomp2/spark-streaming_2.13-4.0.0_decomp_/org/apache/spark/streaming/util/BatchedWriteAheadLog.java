package org.apache.spark.streaming.util;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.spark.SparkConf;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.immutable.Seq;
import scala.collection.mutable.ArrayBuffer;
import scala.concurrent.Promise;
import scala.concurrent.Promise.;
import scala.concurrent.duration.package;
import scala.reflect.ScalaSignature;
import scala.runtime.AbstractFunction3;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.ObjectRef;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\t]e!\u0002\u001c8\u0001]\n\u0005\u0002\u0003'\u0001\u0005\u000b\u0007I\u0011\u0001(\t\u0011=\u0003!\u0011!Q\u0001\n\tC\u0001\u0002\u0015\u0001\u0003\u0002\u0003\u0006I!\u0015\u0005\u0006+\u0002!\tA\u0016\u0005\b5\u0002\u0011\r\u0011\"\u0003\\\u0011\u001d\u0011i\u0003\u0001Q\u0001\nqC\u0011Ba\f\u0001\u0005\u0004%IA!\r\t\u0011\t}\u0002\u0001)A\u0005\u0005gA\u0011Ba\u000b\u0001\u0005\u0004%IA!\u0011\t\u0011\t=\u0003\u0001)A\u0005\u0005\u0007B\u0011B!\u0015\u0001\u0005\u0004%IAa\u0015\t\u0011\tm\u0003\u0001)A\u0005\u0005+BqA!\u0018\u0001\t\u0003\u0012y\u0006C\u0004\u0003h\u0001!\tE!\u001b\t\u000f\t=\u0004\u0001\"\u0011\u0003r!9!\u0011\u0010\u0001\u0005B\tm\u0004b\u0002BF\u0001\u0011\u0005#Q\u0012\u0005\b\u0005\u001f\u0003A\u0011\u0002BI\u0011\u001d\u0011\u0019\n\u0001C\u0005\u0005\u001bCqA!&\u0001\t\u0013\tyl\u0002\u0004ho!\u0005q\u0007\u001b\u0004\u0007m]B\taN5\t\u000bU3B\u0011\u00019\u0007\tE4\u0002I\u001d\u0005\u000b\u0003\u000bA\"Q3A\u0005\u0002\u0005\u001d\u0001BCA\u000b1\tE\t\u0015!\u0003\u0002\n!Q\u0011q\u0003\r\u0003\u0016\u0004%\t!!\u0007\t\u0015\u0005\u0005\u0002D!E!\u0002\u0013\tY\u0002\u0003\u0006\u0002$a\u0011)\u001a!C\u0001\u0003KA!\"a\u000e\u0019\u0005#\u0005\u000b\u0011BA\u0014\u0011\u0019)\u0006\u0004\"\u0001\u0002:!I\u0011Q\t\r\u0002\u0002\u0013\u0005\u0011q\t\u0005\n\u0003\u001fB\u0012\u0013!C\u0001\u0003#B\u0011\"a\u001a\u0019#\u0003%\t!!\u001b\t\u0013\u00055\u0004$%A\u0005\u0002\u0005=\u0004\"CA:1\u0005\u0005I\u0011IA;\u0011%\t\u0019\tGA\u0001\n\u0003\t)\tC\u0005\u0002\u000eb\t\t\u0011\"\u0001\u0002\u0010\"I\u00111\u0014\r\u0002\u0002\u0013\u0005\u0013Q\u0014\u0005\n\u0003WC\u0012\u0011!C\u0001\u0003[C\u0011\"a.\u0019\u0003\u0003%\t%!/\t\u0013\u0005u\u0006$!A\u0005B\u0005}\u0006\"CAa1\u0005\u0005I\u0011IAb\u0011%\t)\rGA\u0001\n\u0003\n9mB\u0005\u0002LZ\t\t\u0011#\u0001\u0002N\u001aA\u0011OFA\u0001\u0012\u0003\ty\r\u0003\u0004V]\u0011\u0005\u0011q\u001d\u0005\n\u0003\u0003t\u0013\u0011!C#\u0003\u0007D\u0011\"!;/\u0003\u0003%\t)a;\t\u0013\u0005Mh&!A\u0005\u0002\u0006U\b\"\u0003B\u0004]\u0005\u0005I\u0011\u0002B\u0005\u0011\u001d\u0011\tB\u0006C\u0001\u0005'AqAa\b\u0017\t\u0003\u0011\tC\u0001\u000bCCR\u001c\u0007.\u001a3Xe&$X-\u00115fC\u0012dun\u001a\u0006\u0003qe\nA!\u001e;jY*\u0011!hO\u0001\ngR\u0014X-Y7j]\u001eT!\u0001P\u001f\u0002\u000bM\u0004\u0018M]6\u000b\u0005yz\u0014AB1qC\u000eDWMC\u0001A\u0003\ry'oZ\n\u0004\u0001\t3\u0005CA\"E\u001b\u00059\u0014BA#8\u000559&/\u001b;f\u0003\",\u0017\r\u001a'pOB\u0011qIS\u0007\u0002\u0011*\u0011\u0011jO\u0001\tS:$XM\u001d8bY&\u00111\n\u0013\u0002\b\u0019><w-\u001b8h\u0003)9(/\u00199qK\u0012dunZ\u0002\u0001+\u0005\u0011\u0015aC<sCB\u0004X\r\u001a'pO\u0002\nAaY8oMB\u0011!kU\u0007\u0002w%\u0011Ak\u000f\u0002\n'B\f'o[\"p]\u001a\fa\u0001P5oSRtDcA,Y3B\u00111\t\u0001\u0005\u0006\u0019\u0012\u0001\rA\u0011\u0005\u0006!\u0012\u0001\r!U\u0001\u000eo\u0006dwK]5uKF+X-^3\u0016\u0003q\u00032!X2f\u001b\u0005q&BA0a\u0003)\u0019wN\\2veJ,g\u000e\u001e\u0006\u0003q\u0005T\u0011AY\u0001\u0005U\u00064\u0018-\u0003\u0002e=\n\u0019B*\u001b8lK\u0012\u0014En\\2lS:<\u0017+^3vKB\u0011a\r\u0007\b\u0003\u0007V\tACQ1uG\",Gm\u0016:ji\u0016\f\u0005.Z1e\u0019><\u0007CA\"\u0017'\t1\"\u000e\u0005\u0002l]6\tANC\u0001n\u0003\u0015\u00198-\u00197b\u0013\tyGN\u0001\u0004B]f\u0014VM\u001a\u000b\u0002Q\n1!+Z2pe\u0012\u001cB\u0001\u00076tmB\u00111\u000e^\u0005\u0003k2\u0014q\u0001\u0015:pIV\u001cG\u000f\u0005\u0002x\u007f:\u0011\u00010 \b\u0003srl\u0011A\u001f\u0006\u0003w6\u000ba\u0001\u0010:p_Rt\u0014\"A7\n\u0005yd\u0017a\u00029bG.\fw-Z\u0005\u0005\u0003\u0003\t\u0019A\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0002\u007fY\u0006!A-\u0019;b+\t\tI\u0001\u0005\u0003\u0002\f\u0005EQBAA\u0007\u0015\r\ty!Y\u0001\u0004]&|\u0017\u0002BA\n\u0003\u001b\u0011!BQ=uK\n+hMZ3s\u0003\u0015!\u0017\r^1!\u0003\u0011!\u0018.\\3\u0016\u0005\u0005m\u0001cA6\u0002\u001e%\u0019\u0011q\u00047\u0003\t1{gnZ\u0001\u0006i&lW\rI\u0001\baJ|W.[:f+\t\t9\u0003\u0005\u0004\u0002*\u00055\u0012\u0011G\u0007\u0003\u0003WQ!a\u00187\n\t\u0005=\u00121\u0006\u0002\b!J|W.[:f!\r\u0019\u00151G\u0005\u0004\u0003k9$!G,sSR,\u0017\t[3bI2{wMU3d_J$\u0007*\u00198eY\u0016\f\u0001\u0002\u001d:p[&\u001cX\r\t\u000b\t\u0003w\ty$!\u0011\u0002DA\u0019\u0011Q\b\r\u000e\u0003YAq!!\u0002 \u0001\u0004\tI\u0001C\u0004\u0002\u0018}\u0001\r!a\u0007\t\u000f\u0005\rr\u00041\u0001\u0002(\u0005!1m\u001c9z)!\tY$!\u0013\u0002L\u00055\u0003\"CA\u0003AA\u0005\t\u0019AA\u0005\u0011%\t9\u0002\tI\u0001\u0002\u0004\tY\u0002C\u0005\u0002$\u0001\u0002\n\u00111\u0001\u0002(\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012\nTCAA*U\u0011\tI!!\u0016,\u0005\u0005]\u0003\u0003BA-\u0003Gj!!a\u0017\u000b\t\u0005u\u0013qL\u0001\nk:\u001c\u0007.Z2lK\u0012T1!!\u0019m\u0003)\tgN\\8uCRLwN\\\u0005\u0005\u0003K\nYFA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fabY8qs\u0012\"WMZ1vYR$#'\u0006\u0002\u0002l)\"\u00111DA+\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIM*\"!!\u001d+\t\u0005\u001d\u0012QK\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0005\u0005]\u0004\u0003BA=\u0003\u007fj!!a\u001f\u000b\u0007\u0005u\u0014-\u0001\u0003mC:<\u0017\u0002BAA\u0003w\u0012aa\u0015;sS:<\u0017\u0001\u00049s_\u0012,8\r^!sSRLXCAAD!\rY\u0017\u0011R\u0005\u0004\u0003\u0017c'aA%oi\u0006q\u0001O]8ek\u000e$X\t\\3nK:$H\u0003BAI\u0003/\u00032a[AJ\u0013\r\t)\n\u001c\u0002\u0004\u0003:L\b\"CAMM\u0005\u0005\t\u0019AAD\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\u0011\u0011q\u0014\t\u0007\u0003C\u000b9+!%\u000e\u0005\u0005\r&bAASY\u0006Q1m\u001c7mK\u000e$\u0018n\u001c8\n\t\u0005%\u00161\u0015\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0003\u00020\u0006U\u0006cA6\u00022&\u0019\u00111\u00177\u0003\u000f\t{w\u000e\\3b]\"I\u0011\u0011\u0014\u0015\u0002\u0002\u0003\u0007\u0011\u0011S\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\r\u0006\u0003\u0002x\u0005m\u0006\"CAMS\u0005\u0005\t\u0019AAD\u0003!A\u0017m\u001d5D_\u0012,GCAAD\u0003!!xn\u0015;sS:<GCAA<\u0003\u0019)\u0017/^1mgR!\u0011qVAe\u0011%\tI\nLA\u0001\u0002\u0004\t\t*\u0001\u0004SK\u000e|'\u000f\u001a\t\u0004\u0003{q3#\u0002\u0018\u0002R\u0006u\u0007\u0003DAj\u00033\fI!a\u0007\u0002(\u0005mRBAAk\u0015\r\t9\u000e\\\u0001\beVtG/[7f\u0013\u0011\tY.!6\u0003#\u0005\u00137\u000f\u001e:bGR4UO\\2uS>t7\u0007\u0005\u0003\u0002`\u0006\u0015XBAAq\u0015\r\t\u0019/Y\u0001\u0003S>LA!!\u0001\u0002bR\u0011\u0011QZ\u0001\u0006CB\u0004H.\u001f\u000b\t\u0003w\ti/a<\u0002r\"9\u0011QA\u0019A\u0002\u0005%\u0001bBA\fc\u0001\u0007\u00111\u0004\u0005\b\u0003G\t\u0004\u0019AA\u0014\u0003\u001d)h.\u00199qYf$B!a>\u0003\u0004A)1.!?\u0002~&\u0019\u00111 7\u0003\r=\u0003H/[8o!%Y\u0017q`A\u0005\u00037\t9#C\u0002\u0003\u00021\u0014a\u0001V;qY\u0016\u001c\u0004\"\u0003B\u0003e\u0005\u0005\t\u0019AA\u001e\u0003\rAH\u0005M\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0005\u0017\u0001B!!\u001f\u0003\u000e%!!qBA>\u0005\u0019y%M[3di\u0006I\u0011mZ4sK\u001e\fG/\u001a\u000b\u0005\u0003\u0013\u0011)\u0002C\u0004\u0003\u0018Q\u0002\rA!\u0007\u0002\u000fI,7m\u001c:egB)qOa\u0007\u0002<%!!QDA\u0002\u0005\r\u0019V-]\u0001\fI\u0016\fwm\u001a:fO\u0006$X\r\u0006\u0003\u0003$\t%\u0002#B6\u0003&\u0005%\u0011b\u0001B\u0014Y\n)\u0011I\u001d:bs\"9!1F\u001bA\u0002\u0005%\u0011A\u00022vM\u001a,'/\u0001\bxC2<&/\u001b;f#V,W/\u001a\u0011\u0002\r\u0005\u001cG/\u001b<f+\t\u0011\u0019\u0004\u0005\u0003\u00036\tmRB\u0001B\u001c\u0015\r\u0011IDX\u0001\u0007CR|W.[2\n\t\tu\"q\u0007\u0002\u000e\u0003R|W.[2C_>dW-\u00198\u0002\u000f\u0005\u001cG/\u001b<fAU\u0011!1\t\t\u0006\u0005\u000b\u0012Y%Z\u0007\u0003\u0005\u000fRAA!\u0013\u0002$\u00069Q.\u001e;bE2,\u0017\u0002\u0002B'\u0005\u000f\u00121\"\u0011:sCf\u0014UO\u001a4fe\u00069!-\u001e4gKJ\u0004\u0013a\u00052bi\u000eDW\rZ,sSR,'\u000f\u00165sK\u0006$WC\u0001B+!\u0011\tIHa\u0016\n\t\te\u00131\u0010\u0002\u0007)\"\u0014X-\u00193\u0002)\t\fGo\u00195fI^\u0013\u0018\u000e^3s)\"\u0014X-\u00193!\u0003\u00159(/\u001b;f)\u0019\t\tD!\u0019\u0003f!9!1M\u0007A\u0002\u0005%\u0011A\u00032zi\u0016\u0014UO\u001a4fe\"9\u0011qC\u0007A\u0002\u0005m\u0011\u0001\u0002:fC\u0012$B!!\u0003\u0003l!9!Q\u000e\bA\u0002\u0005E\u0012aB:fO6,g\u000e^\u0001\be\u0016\fG-\u00117m)\t\u0011\u0019\b\u0005\u0004\u0003v\t]\u0014\u0011B\u0007\u0002A&\u0019\u0011\u0011\u00161\u0002\u000b\rdW-\u00198\u0015\r\tu$1\u0011BD!\rY'qP\u0005\u0004\u0005\u0003c'\u0001B+oSRDqA!\"\u0011\u0001\u0004\tY\"\u0001\u0006uQJ,7\u000f\u001b+j[\u0016DqA!#\u0011\u0001\u0004\ty+A\txC&$hi\u001c:D_6\u0004H.\u001a;j_:\fQa\u00197pg\u0016$\"A! \u00021M$\u0018M\u001d;CCR\u001c\u0007.\u001a3Xe&$XM\u001d+ie\u0016\fG\r\u0006\u0002\u0003V\u0005aa\r\\;tQJ+7m\u001c:eg\u0006qq-\u001a;Rk\u0016,X\rT3oORD\u0007"
)
public class BatchedWriteAheadLog extends WriteAheadLog implements Logging {
   private final WriteAheadLog wrappedLog;
   private final SparkConf conf;
   private final LinkedBlockingQueue walWriteQueue;
   private final AtomicBoolean active;
   private final ArrayBuffer buffer;
   private final Thread batchedWriterThread;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static ByteBuffer[] deaggregate(final ByteBuffer buffer) {
      return BatchedWriteAheadLog$.MODULE$.deaggregate(buffer);
   }

   public static ByteBuffer aggregate(final Seq records) {
      return BatchedWriteAheadLog$.MODULE$.aggregate(records);
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

   public WriteAheadLog wrappedLog() {
      return this.wrappedLog;
   }

   private LinkedBlockingQueue walWriteQueue() {
      return this.walWriteQueue;
   }

   private AtomicBoolean active() {
      return this.active;
   }

   private ArrayBuffer buffer() {
      return this.buffer;
   }

   private Thread batchedWriterThread() {
      return this.batchedWriterThread;
   }

   public WriteAheadLogRecordHandle write(final ByteBuffer byteBuffer, final long time) {
      Promise promise = .MODULE$.apply();
      synchronized(this){}

      boolean var7;
      try {
         boolean var10000;
         if (this.active().get()) {
            this.walWriteQueue().offer(new Record(byteBuffer, time, promise));
            var10000 = true;
         } else {
            var10000 = false;
         }

         var7 = var10000;
      } catch (Throwable var9) {
         throw var9;
      }

      if (var7) {
         return (WriteAheadLogRecordHandle)org.apache.spark.util.ThreadUtils..MODULE$.awaitResult(promise.future(), (new package.DurationLong(scala.concurrent.duration.package..MODULE$.DurationLong(WriteAheadLogUtils$.MODULE$.getBatchingTimeout(this.conf)))).milliseconds());
      } else {
         throw new IllegalStateException("close() was called on BatchedWriteAheadLog before write request with time " + time + " could be fulfilled.");
      }
   }

   public ByteBuffer read(final WriteAheadLogRecordHandle segment) {
      throw new UnsupportedOperationException("read() is not supported for BatchedWriteAheadLog as the data may require de-aggregation.");
   }

   public Iterator readAll() {
      return scala.jdk.CollectionConverters..MODULE$.IteratorHasAsJava(scala.jdk.CollectionConverters..MODULE$.IteratorHasAsScala(this.wrappedLog().readAll()).asScala().flatMap((buffer) -> scala.Predef..MODULE$.wrapRefArray((Object[])BatchedWriteAheadLog$.MODULE$.deaggregate(buffer)))).asJava();
   }

   public void clean(final long threshTime, final boolean waitForCompletion) {
      this.wrappedLog().clean(threshTime, waitForCompletion);
   }

   public void close() {
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"BatchedWriteAheadLog shutting down at time: "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TIME..MODULE$, BoxesRunTime.boxToLong(System.currentTimeMillis()))}))))));
      if (this.active().getAndSet(false)) {
         this.batchedWriterThread().interrupt();
         this.batchedWriterThread().join();

         while(!this.walWriteQueue().isEmpty()) {
            Record var3 = (Record)this.walWriteQueue().poll();
            if (var3 == null) {
               throw new MatchError(var3);
            }

            long time = var3.time();
            Promise promise = var3.promise();
            Tuple2 var2 = new Tuple2(BoxesRunTime.boxToLong(time), promise);
            long time = var2._1$mcJ$sp();
            Promise promise = (Promise)var2._2();
            promise.failure(new IllegalStateException("close() was called on BatchedWriteAheadLog before write request with time " + time + " could be fulfilled."));
         }

         this.wrappedLog().close();
      }
   }

   private Thread startBatchedWriterThread() {
      Thread thread = new Thread(() -> {
         while(this.active().get()) {
            try {
               this.flushRecords();
            } catch (Throwable var5) {
               if (var5 == null || !scala.util.control.NonFatal..MODULE$.apply(var5)) {
                  throw var5;
               }

               this.logWarning((Function0)(() -> "Encountered exception in Batched Writer Thread."), var5);
               BoxedUnit var10000 = BoxedUnit.UNIT;
            }
         }

         this.logInfo((Function0)(() -> "BatchedWriteAheadLog Writer thread exiting."));
      }, "BatchedWriteAheadLog Writer");
      thread.setDaemon(true);
      thread.start();
      return thread;
   }

   private void flushRecords() {
      try {
         this.buffer().$plus$eq(this.walWriteQueue().take());
         int numBatched = this.walWriteQueue().drainTo(scala.jdk.CollectionConverters..MODULE$.BufferHasAsJava(this.buffer()).asJava()) + 1;
         this.logDebug((Function0)(() -> "Received " + numBatched + " records from queue"));
      } catch (InterruptedException var15) {
         this.logWarning((Function0)(() -> "BatchedWriteAheadLog Writer queue interrupted."));
      }

      try {
         ObjectRef segment = ObjectRef.create((Object)null);
         if (this.buffer().nonEmpty()) {
            this.logDebug((Function0)(() -> "Batched " + this.buffer().length() + " records for Write Ahead Log write"));
            ArrayBuffer sortedByTime = (ArrayBuffer)this.buffer().sortBy((x$2) -> BoxesRunTime.boxToLong($anonfun$flushRecords$4(x$2)), scala.math.Ordering.Long..MODULE$);
            long time = ((Record)sortedByTime.last()).time();
            segment.elem = this.wrappedLog().write(BatchedWriteAheadLog$.MODULE$.aggregate(sortedByTime.toSeq()), time);
         }

         this.buffer().foreach((x$3) -> x$3.promise().success((WriteAheadLogRecordHandle)segment.elem));
      } catch (Throwable var16) {
         if (var16 instanceof InterruptedException var9) {
            this.logWarning((Function0)(() -> "BatchedWriteAheadLog Writer queue interrupted."), var9);
            this.buffer().foreach((x$4) -> x$4.promise().failure(var9));
            BoxedUnit var18 = BoxedUnit.UNIT;
         } else {
            if (var16 == null || !scala.util.control.NonFatal..MODULE$.apply(var16)) {
               throw var16;
            }

            this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"BatchedWriteAheadLog Writer failed to write ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.RECORDS..MODULE$, this.buffer())})))), var16);
            this.buffer().foreach((x$5) -> x$5.promise().failure(var16));
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }
      } finally {
         this.buffer().clear();
      }

   }

   private int getQueueLength() {
      return this.walWriteQueue().size();
   }

   // $FF: synthetic method
   public static final long $anonfun$flushRecords$4(final Record x$2) {
      return x$2.time();
   }

   public BatchedWriteAheadLog(final WriteAheadLog wrappedLog, final SparkConf conf) {
      this.wrappedLog = wrappedLog;
      this.conf = conf;
      Logging.$init$(this);
      this.walWriteQueue = new LinkedBlockingQueue();
      this.active = new AtomicBoolean(true);
      this.buffer = new ArrayBuffer();
      this.batchedWriterThread = this.startBatchedWriterThread();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class Record implements Product, Serializable {
      private final ByteBuffer data;
      private final long time;
      private final Promise promise;

      public scala.collection.Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public ByteBuffer data() {
         return this.data;
      }

      public long time() {
         return this.time;
      }

      public Promise promise() {
         return this.promise;
      }

      public Record copy(final ByteBuffer data, final long time, final Promise promise) {
         return new Record(data, time, promise);
      }

      public ByteBuffer copy$default$1() {
         return this.data();
      }

      public long copy$default$2() {
         return this.time();
      }

      public Promise copy$default$3() {
         return this.promise();
      }

      public String productPrefix() {
         return "Record";
      }

      public int productArity() {
         return 3;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return this.data();
            }
            case 1 -> {
               return BoxesRunTime.boxToLong(this.time());
            }
            case 2 -> {
               return this.promise();
            }
            default -> {
               return Statics.ioobe(x$1);
            }
         }
      }

      public scala.collection.Iterator productIterator() {
         return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof Record;
      }

      public String productElementName(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return "data";
            }
            case 1 -> {
               return "time";
            }
            case 2 -> {
               return "promise";
            }
            default -> {
               return (String)Statics.ioobe(x$1);
            }
         }
      }

      public int hashCode() {
         int var1 = -889275714;
         var1 = Statics.mix(var1, this.productPrefix().hashCode());
         var1 = Statics.mix(var1, Statics.anyHash(this.data()));
         var1 = Statics.mix(var1, Statics.longHash(this.time()));
         var1 = Statics.mix(var1, Statics.anyHash(this.promise()));
         return Statics.finalizeHash(var1, 3);
      }

      public String toString() {
         return scala.runtime.ScalaRunTime..MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var8;
         if (this != x$1) {
            label59: {
               if (x$1 instanceof Record) {
                  Record var4 = (Record)x$1;
                  if (this.time() == var4.time()) {
                     label52: {
                        ByteBuffer var10000 = this.data();
                        ByteBuffer var5 = var4.data();
                        if (var10000 == null) {
                           if (var5 != null) {
                              break label52;
                           }
                        } else if (!var10000.equals(var5)) {
                           break label52;
                        }

                        Promise var7 = this.promise();
                        Promise var6 = var4.promise();
                        if (var7 == null) {
                           if (var6 != null) {
                              break label52;
                           }
                        } else if (!var7.equals(var6)) {
                           break label52;
                        }

                        if (var4.canEqual(this)) {
                           break label59;
                        }
                     }
                  }
               }

               var8 = false;
               return var8;
            }
         }

         var8 = true;
         return var8;
      }

      public Record(final ByteBuffer data, final long time, final Promise promise) {
         this.data = data;
         this.time = time;
         this.promise = promise;
         Product.$init$(this);
      }
   }

   public static class Record$ extends AbstractFunction3 implements Serializable {
      public static final Record$ MODULE$ = new Record$();

      public final String toString() {
         return "Record";
      }

      public Record apply(final ByteBuffer data, final long time, final Promise promise) {
         return new Record(data, time, promise);
      }

      public Option unapply(final Record x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple3(x$0.data(), BoxesRunTime.boxToLong(x$0.time()), x$0.promise())));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Record$.class);
      }
   }
}
