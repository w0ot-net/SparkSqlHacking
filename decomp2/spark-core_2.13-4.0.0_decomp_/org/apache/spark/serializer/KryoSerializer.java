package org.apache.spark.serializer;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.io.UnsafeOutput;
import com.esotericsoftware.kryo.pool.KryoCallback;
import com.esotericsoftware.kryo.pool.KryoFactory;
import com.esotericsoftware.kryo.pool.KryoPool;
import com.twitter.chill.AllScalaRegistrar;
import com.twitter.chill.EmptyScalaKryoInstantiator;
import com.twitter.chill.KryoBase;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.spark.SerializableWritable;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkException;
import org.apache.spark.SparkIllegalArgumentException;
import org.apache.spark.api.python.PythonBroadcast;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.config.Kryo$;
import org.apache.spark.network.util.ByteUnit;
import org.apache.spark.util.SerializableConfiguration;
import org.apache.spark.util.SerializableJobConf;
import org.apache.spark.util.Utils$;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.StringContext;
import scala.Tuple1;
import scala.Tuple10;
import scala.Tuple11;
import scala.Tuple12;
import scala.Tuple13;
import scala.Tuple14;
import scala.Tuple15;
import scala.Tuple16;
import scala.Tuple17;
import scala.Tuple18;
import scala.Tuple19;
import scala.Tuple2;
import scala.Tuple20;
import scala.Tuple21;
import scala.Tuple22;
import scala.Tuple3;
import scala.Tuple4;
import scala.Tuple5;
import scala.Tuple6;
import scala.Tuple7;
import scala.Tuple8;
import scala.Tuple9;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.collection.mutable.ArrayBuffer;
import scala.math.package.;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005\tmc\u0001\u0002\u001b6\u0001yB\u0001\"\u0015\u0001\u0003\u0002\u0003\u0006IA\u0015\u0005\u0006-\u0002!\ta\u0016\u0005\b5\u0002\u0011\r\u0011\"\u0003\\\u0011\u0019\u0011\u0007\u0001)A\u00059\"91\r\u0001b\u0001\n\u0013!\u0007B\u00025\u0001A\u0003%Q\rC\u0004j\u0001\t\u0007I\u0011\u00013\t\r)\u0004\u0001\u0015!\u0003f\u0011\u001dY\u0007A1A\u0005\n\u0011Da\u0001\u001c\u0001!\u0002\u0013)\u0007bB7\u0001\u0005\u0004%IA\u001c\u0005\u0007e\u0002\u0001\u000b\u0011B8\t\u000fM\u0004!\u0019!C\u0005]\"1A\u000f\u0001Q\u0001\n=Dq!\u001e\u0001C\u0002\u0013%a\u000fC\u0004\u0002\f\u0001\u0001\u000b\u0011B<\t\u0011\u00055\u0001A1A\u0005\nYDq!a\u0004\u0001A\u0003%q\u000fC\u0005\u0002\u0012\u0001\u0011\r\u0011\"\u0003\u0002\u0014!A\u0011q\u0006\u0001!\u0002\u0013\t)\u0002\u0003\u0005\u00022\u0001\u0011\r\u0011\"\u0003o\u0011\u001d\t\u0019\u0004\u0001Q\u0001\n=D\u0001\"!\u000e\u0001\u0005\u0004%IA\u001c\u0005\b\u0003o\u0001\u0001\u0015!\u0003p\u0011\u001d\tI\u0004\u0001C\u0001\u0003wA!\"a\u0015\u0001\u0011\u000b\u0007I\u0011BA+\r\u0019\tY\u0007\u0001\u0003\u0002n!1ak\u0007C\u0001\u0003wB\u0011\"!\u0018\u001c\u0001\u0004%I!!!\t\u0013\u0005\r5\u00041A\u0005\n\u0005\u0015\u0005\u0002CAI7\u0001\u0006K!!\u001e\t\u000f\u0005M5\u0004\"\u0011\u0002\u0016\"9\u0011qT\u000e\u0005B\u0005\u0005\u0006bBAS7\u0011\u0005\u0013q\u0015\u0005\b\u0003\u0017\\B\u0011AAg\u0011\u001d\tym\u0007C\u0005\u0003\u0003C!\"!5\u0001\u0011\u000b\u0007I\u0011BAj\u0011\u001d\ti\u0006\u0001C\u0001\u0003\u0003Cq!a6\u0001\t\u0003\t)\nC\u0004\u0002Z\u0002!\t%a7\t\u000f\u0005\u001d\b\u0001\"\u0011\u0002j\"Q\u0011\u0011\u001f\u0001\t\u0006\u0004%\te\u000e8\b\u0011\u0005MX\u0007#\u00016\u0003k4q\u0001N\u001b\t\u0002U\n9\u0010\u0003\u0004WY\u0011\u0005\u0011q \u0005\n\u0005\u0003a#\u0019!C\u0005\u0005\u0007A\u0001Ba\t-A\u0003%!Q\u0001\u0005\n\u0005Ka#\u0019!C\u0005\u0005OA\u0001B!\u000f-A\u0003%!\u0011\u0006\u0005\u000b\u0005\u000fb\u0003R1A\u0005\n\t%\u0003\"\u0003B,Y\u0005\u0005I\u0011\u0002B-\u00059Y%/_8TKJL\u0017\r\\5{KJT!AN\u001c\u0002\u0015M,'/[1mSj,'O\u0003\u00029s\u0005)1\u000f]1sW*\u0011!hO\u0001\u0007CB\f7\r[3\u000b\u0003q\n1a\u001c:h\u0007\u0001\u0019B\u0001A D\u0013B\u0011\u0001)Q\u0007\u0002k%\u0011!)\u000e\u0002\u000b'\u0016\u0014\u0018.\u00197ju\u0016\u0014\bC\u0001#H\u001b\u0005)%B\u0001$8\u0003!Ig\u000e^3s]\u0006d\u0017B\u0001%F\u0005\u001daunZ4j]\u001e\u0004\"AS(\u000e\u0003-S!\u0001T'\u0002\u0005%|'\"\u0001(\u0002\t)\fg/Y\u0005\u0003!.\u0013AbU3sS\u0006d\u0017N_1cY\u0016\fAaY8oMB\u00111\u000bV\u0007\u0002o%\u0011Qk\u000e\u0002\n'B\f'o[\"p]\u001a\fa\u0001P5oSRtDC\u0001-Z!\t\u0001\u0005\u0001C\u0003R\u0005\u0001\u0007!+\u0001\u0007ck\u001a4WM]*ju\u0016\\%-F\u0001]!\ti\u0006-D\u0001_\u0015\u0005y\u0016!B:dC2\f\u0017BA1_\u0005\u0011auN\\4\u0002\u001b\t,hMZ3s'&TXm\u00132!\u0003)\u0011WO\u001a4feNK'0Z\u000b\u0002KB\u0011QLZ\u0005\u0003Oz\u00131!\u00138u\u0003-\u0011WO\u001a4feNK'0\u001a\u0011\u0002\u001f5\f\u0007PQ;gM\u0016\u00148+\u001b>f\u001b\n\f\u0001#\\1y\u0005V4g-\u001a:TSj,WJ\u0019\u0011\u0002\u001b5\f\u0007PQ;gM\u0016\u00148+\u001b>f\u00039i\u0017\r\u001f\"vM\u001a,'oU5{K\u0002\n\u0011C]3gKJ,gnY3Ue\u0006\u001c7.\u001b8h+\u0005y\u0007CA/q\u0013\t\thLA\u0004C_>dW-\u00198\u0002%I,g-\u001a:f]\u000e,GK]1dW&tw\rI\u0001\u0015e\u0016<\u0017n\u001d;sCRLwN\u001c*fcVL'/\u001a3\u0002+I,w-[:ue\u0006$\u0018n\u001c8SKF,\u0018N]3eA\u0005\u0001Ro]3s%\u0016<\u0017n\u001d;sCR|'o]\u000b\u0002oB\u0019\u00010`@\u000e\u0003eT!A_>\u0002\u0013%lW.\u001e;bE2,'B\u0001?_\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0003}f\u00141aU3r!\u0011\t\t!a\u0002\u000e\u0005\u0005\r!bAA\u0003\u001b\u0006!A.\u00198h\u0013\u0011\tI!a\u0001\u0003\rM#(/\u001b8h\u0003E)8/\u001a:SK\u001eL7\u000f\u001e:bi>\u00148\u000fI\u0001\u0012G2\f7o]3t)>\u0014VmZ5ti\u0016\u0014\u0018AE2mCN\u001cXm\u001d+p%\u0016<\u0017n\u001d;fe\u0002\n1\"\u0019<s_N\u001b\u0007.Z7bgV\u0011\u0011Q\u0003\t\b\u0003/\t)\u0003XA\u0016\u001d\u0011\tI\"!\t\u0011\u0007\u0005ma,\u0004\u0002\u0002\u001e)\u0019\u0011qD\u001f\u0002\rq\u0012xn\u001c;?\u0013\r\t\u0019CX\u0001\u0007!J,G-\u001a4\n\t\u0005\u001d\u0012\u0011\u0006\u0002\u0004\u001b\u0006\u0004(bAA\u0012=B!\u0011qCA\u0017\u0013\u0011\tI!!\u000b\u0002\u0019\u00054(o\\*dQ\u0016l\u0017m\u001d\u0011\u0002\u0013U\u001cX-\u00168tC\u001a,\u0017AC;tKVs7/\u00194fA\u00059Qo]3Q_>d\u0017\u0001C;tKB{w\u000e\u001c\u0011\u0002\u001b9,wo\u0013:z_>+H\u000f];u)\t\ti\u0004\u0005\u0003\u0002@\u0005=SBAA!\u0015\ra\u00151\t\u0006\u0005\u0003\u000b\n9%\u0001\u0003lef|'\u0002BA%\u0003\u0017\n\u0001#Z:pi\u0016\u0014\u0018nY:pMR<\u0018M]3\u000b\u0005\u00055\u0013aA2p[&!\u0011\u0011KA!\u0005\u0019yU\u000f\u001e9vi\u00069a-Y2u_JLXCAA,!\u0011\tI&a\u0018\u000e\u0005\u0005m#\u0002BA/\u0003\u0007\nA\u0001]8pY&!\u0011\u0011MA.\u0005-Y%/_8GC\u000e$xN]=)\u0007i\t)\u0007E\u0002^\u0003OJ1!!\u001b_\u0005%!(/\u00198tS\u0016tGOA\u0006Q_>dwK]1qa\u0016\u00148#B\u000e\u0002p\u0005U\u0004\u0003BA\u0001\u0003cJA!a\u001d\u0002\u0004\t1qJ\u00196fGR\u0004B!!\u0017\u0002x%!\u0011\u0011PA.\u0005!Y%/_8Q_>dGCAA?!\r\tyhG\u0007\u0002\u0001U\u0011\u0011QO\u0001\ta>|Gn\u0018\u0013fcR!\u0011qQAG!\ri\u0016\u0011R\u0005\u0004\u0003\u0017s&\u0001B+oSRD\u0011\"a$\u001f\u0003\u0003\u0005\r!!\u001e\u0002\u0007a$\u0013'A\u0003q_>d\u0007%\u0001\u0004c_J\u0014xn\u001e\u000b\u0003\u0003/\u0003B!!'\u0002\u001c6\u0011\u00111I\u0005\u0005\u0003;\u000b\u0019E\u0001\u0003Lef|\u0017a\u0002:fY\u0016\f7/\u001a\u000b\u0005\u0003\u000f\u000b\u0019\u000bC\u0004\u0002F\u0005\u0002\r!a&\u0002\u0007I,h.\u0006\u0003\u0002*\u0006=F\u0003BAV\u0003\u0003\u0004B!!,\u000202\u0001AaBAYE\t\u0007\u00111\u0017\u0002\u0002)F!\u0011QWA^!\ri\u0016qW\u0005\u0004\u0003ss&a\u0002(pi\"Lgn\u001a\t\u0004;\u0006u\u0016bAA`=\n\u0019\u0011I\\=\t\u000f\u0005\r'\u00051\u0001\u0002F\u0006a1N]=p\u0007\u0006dGNY1dWB1\u0011\u0011LAd\u0003WKA!!3\u0002\\\ta1J]=p\u0007\u0006dGNY1dW\u0006)!/Z:fiR\u0011\u0011qQ\u0001\bO\u0016$\bk\\8m\u00031Ig\u000e^3s]\u0006d\u0007k\\8m+\t\ti\bK\u0002&\u0003K\nqA\\3x\u0017JLx.A\u000btKR$UMZ1vYR\u001cE.Y:t\u0019>\fG-\u001a:\u0015\u0007}\ni\u000eC\u0004\u0002`\"\u0002\r!!9\u0002\u0017\rd\u0017m]:M_\u0006$WM\u001d\t\u0005\u0003\u0003\t\u0019/\u0003\u0003\u0002f\u0006\r!aC\"mCN\u001cHj\\1eKJ\f1B\\3x\u0013:\u001cH/\u00198dKR\u0011\u00111\u001e\t\u0004\u0001\u00065\u0018bAAxk\t\u00112+\u001a:jC2L'0\u001a:J]N$\u0018M\\2f\u0003\u0015\u001aX\u000f\u001d9peR\u001c(+\u001a7pG\u0006$\u0018n\u001c8PMN+'/[1mSj,Gm\u00142kK\u000e$8/\u0001\bLef|7+\u001a:jC2L'0\u001a:\u0011\u0005\u0001c3\u0003\u0002\u0017\u0002z&\u00032!XA~\u0013\r\tiP\u0018\u0002\u0007\u0003:L(+\u001a4\u0015\u0005\u0005U\u0018A\u0003;p%\u0016<\u0017n\u001d;feV\u0011!Q\u0001\t\u0007\u0005\u000f\u0011\tB!\u0006\u000f\t\t%!Q\u0002\b\u0005\u00037\u0011Y!C\u0001`\u0013\r\u0011yAX\u0001\ba\u0006\u001c7.Y4f\u0013\rq(1\u0003\u0006\u0004\u0005\u001fq\u0006\u0007\u0002B\f\u0005?\u0001b!a\u0006\u0003\u001a\tu\u0011\u0002\u0002B\u000e\u0003S\u0011Qa\u00117bgN\u0004B!!,\u0003 \u0011Y!\u0011E\u0018\u0002\u0002\u0003\u0005)\u0011AAZ\u0005\ryFeM\u0001\fi>\u0014VmZ5ti\u0016\u0014\b%\u0001\u000bu_J+w-[:uKJ\u001cVM]5bY&TXM]\u000b\u0003\u0005S\u0001r\u0001\u001fB\u0016\u0005[\u0011Y$C\u0002\u0002(e\u0004DAa\f\u00036A1\u0011\u0011\u0001B\u0019\u0005gIAAa\u0007\u0002\u0004A!\u0011Q\u0016B\u001b\t-\u00119$MA\u0001\u0002\u0003\u0015\t!a-\u0003\u0007}#c'A\u000bu_J+w-[:uKJ\u001cVM]5bY&TXM\u001d\u00111\t\tu\"1\t\t\u0007\u00033\u0013yD!\u0011\n\u0007\t\u000b\u0019\u0005\u0005\u0003\u0002.\n\rCa\u0003B#c\u0005\u0005\t\u0011!B\u0001\u0003g\u00131a\u0018\u00138\u0003Qaw.\u00193bE2,7\u000b]1sW\u000ec\u0017m]:fgV\u0011!1\n\t\u0007\u0005\u000f\u0011\tB!\u00141\t\t=#1\u000b\t\u0007\u0003/\u0011IB!\u0015\u0011\t\u00055&1\u000b\u0003\f\u0005+\u0012\u0014\u0011!A\u0001\u0006\u0003\t\u0019LA\u0002`Ia\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!a\u001c"
)
public class KryoSerializer extends Serializer implements Logging, Serializable {
   private transient KryoFactory org$apache$spark$serializer$KryoSerializer$$factory;
   private transient PoolWrapper internalPool;
   private boolean supportsRelocationOfSerializedObjects;
   private final long bufferSizeKb;
   private final int bufferSize;
   private final int maxBufferSizeMb;
   private final int maxBufferSize;
   private final boolean referenceTracking;
   private final boolean registrationRequired;
   private final Seq userRegistrators;
   private final Seq classesToRegister;
   private final Map avroSchemas;
   private final boolean useUnsafe;
   private final boolean usePool;
   private transient Logger org$apache$spark$internal$Logging$$log_;
   private volatile boolean bitmap$0;
   private transient volatile byte bitmap$trans$0;

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

   private long bufferSizeKb() {
      return this.bufferSizeKb;
   }

   private int bufferSize() {
      return this.bufferSize;
   }

   public int maxBufferSizeMb() {
      return this.maxBufferSizeMb;
   }

   private int maxBufferSize() {
      return this.maxBufferSize;
   }

   private boolean referenceTracking() {
      return this.referenceTracking;
   }

   private boolean registrationRequired() {
      return this.registrationRequired;
   }

   private Seq userRegistrators() {
      return this.userRegistrators;
   }

   private Seq classesToRegister() {
      return this.classesToRegister;
   }

   private Map avroSchemas() {
      return this.avroSchemas;
   }

   private boolean useUnsafe() {
      return this.useUnsafe;
   }

   private boolean usePool() {
      return this.usePool;
   }

   public Output newKryoOutput() {
      return (Output)(this.useUnsafe() ? new UnsafeOutput(this.bufferSize(), .MODULE$.max(this.bufferSize(), this.maxBufferSize())) : new Output(this.bufferSize(), .MODULE$.max(this.bufferSize(), this.maxBufferSize())));
   }

   private KryoFactory factory$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$trans$0 & 1) == 0) {
            this.org$apache$spark$serializer$KryoSerializer$$factory = new KryoFactory() {
               // $FF: synthetic field
               private final KryoSerializer $outer;

               public Kryo create() {
                  return this.$outer.newKryo();
               }

               public {
                  if (KryoSerializer.this == null) {
                     throw null;
                  } else {
                     this.$outer = KryoSerializer.this;
                  }
               }
            };
            this.bitmap$trans$0 = (byte)(this.bitmap$trans$0 | 1);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.org$apache$spark$serializer$KryoSerializer$$factory;
   }

   public KryoFactory org$apache$spark$serializer$KryoSerializer$$factory() {
      return (byte)(this.bitmap$trans$0 & 1) == 0 ? this.factory$lzycompute() : this.org$apache$spark$serializer$KryoSerializer$$factory;
   }

   private PoolWrapper internalPool$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$trans$0 & 2) == 0) {
            this.internalPool = new PoolWrapper();
            this.bitmap$trans$0 = (byte)(this.bitmap$trans$0 | 2);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.internalPool;
   }

   private PoolWrapper internalPool() {
      return (byte)(this.bitmap$trans$0 & 2) == 0 ? this.internalPool$lzycompute() : this.internalPool;
   }

   public KryoPool pool() {
      return this.internalPool();
   }

   public Kryo newKryo() {
      EmptyScalaKryoInstantiator instantiator = new EmptyScalaKryoInstantiator();
      KryoBase kryo = instantiator.newKryo();
      kryo.setRegistrationRequired(this.registrationRequired());
      ClassLoader classLoader = (ClassLoader)this.defaultClassLoader().getOrElse(() -> Thread.currentThread().getContextClassLoader());
      kryo.setReferences(this.referenceTracking());
      KryoSerializer$.MODULE$.org$apache$spark$serializer$KryoSerializer$$toRegister().foreach((cls) -> kryo.register(cls));
      KryoSerializer$.MODULE$.org$apache$spark$serializer$KryoSerializer$$toRegisterSerializer().withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$newKryo$3(check$ifrefutable$1))).foreach((x$5) -> {
         if (x$5 != null) {
            Class cls = (Class)x$5._1();
            com.esotericsoftware.kryo.Serializer ser = (com.esotericsoftware.kryo.Serializer)x$5._2();
            return kryo.register(cls, ser);
         } else {
            throw new MatchError(x$5);
         }
      });
      kryo.register(JavaIterableWrapperSerializer$.MODULE$.wrapperClass(), new JavaIterableWrapperSerializer());
      kryo.register(SerializableWritable.class, new com.esotericsoftware.kryo.serializers.JavaSerializer());
      kryo.register(SerializableConfiguration.class, new com.esotericsoftware.kryo.serializers.JavaSerializer());
      kryo.register(SerializableJobConf.class, new com.esotericsoftware.kryo.serializers.JavaSerializer());
      kryo.register(PythonBroadcast.class, new com.esotericsoftware.kryo.serializers.JavaSerializer());
      this.registerAvro$1(scala.reflect.ClassTag..MODULE$.apply(GenericRecord.class), kryo);
      this.registerAvro$1(scala.reflect.ClassTag..MODULE$.apply(GenericData.Record.class), kryo);
      this.registerAvro$1(scala.reflect.ClassTag..MODULE$.apply(GenericData.Array.class), kryo);
      this.registerAvro$1(scala.reflect.ClassTag..MODULE$.apply(GenericData.EnumSymbol.class), kryo);
      this.registerAvro$1(scala.reflect.ClassTag..MODULE$.apply(GenericData.Fixed.class), kryo);
      Utils$.MODULE$.withContextClassLoader(classLoader, (JFunction0.mcV.sp)() -> {
         try {
            this.classesToRegister().foreach((className) -> {
               boolean x$2 = true;
               boolean x$3 = Utils$.MODULE$.classForName$default$2();
               return kryo.register(Utils$.MODULE$.classForName(className, x$3, true));
            });
            ((IterableOnceOps)this.userRegistrators().map((x$6) -> {
               boolean x$5 = true;
               boolean x$6 = Utils$.MODULE$.classForName$default$2();
               return (KryoRegistrator)Utils$.MODULE$.classForName(x$6, x$6, true).getConstructor().newInstance();
            })).foreach((reg) -> {
               $anonfun$newKryo$8(kryo, reg);
               return BoxedUnit.UNIT;
            });
         } catch (Exception var3) {
            throw new SparkException("FAILED_REGISTER_CLASS_WITH_KRYO", scala.Predef..MODULE$.Map().empty(), var3);
         }
      });
      (new AllScalaRegistrar()).apply(kryo);
      kryo.register(Tuple1[].class);
      kryo.register(Tuple2[].class);
      kryo.register(Tuple3[].class);
      kryo.register(Tuple4[].class);
      kryo.register(Tuple5[].class);
      kryo.register(Tuple6[].class);
      kryo.register(Tuple7[].class);
      kryo.register(Tuple8[].class);
      kryo.register(Tuple9[].class);
      kryo.register(Tuple10[].class);
      kryo.register(Tuple11[].class);
      kryo.register(Tuple12[].class);
      kryo.register(Tuple13[].class);
      kryo.register(Tuple14[].class);
      kryo.register(Tuple15[].class);
      kryo.register(Tuple16[].class);
      kryo.register(Tuple17[].class);
      kryo.register(Tuple18[].class);
      kryo.register(Tuple19[].class);
      kryo.register(Tuple20[].class);
      kryo.register(Tuple21[].class);
      kryo.register(Tuple22[].class);
      kryo.register(Utils$.MODULE$.classForName("scala.collection.immutable.ArraySeq$ofRef", Utils$.MODULE$.classForName$default$2(), Utils$.MODULE$.classForName$default$3()));
      kryo.register(Utils$.MODULE$.classForName("scala.collection.immutable.Map$EmptyMap$", Utils$.MODULE$.classForName$default$2(), Utils$.MODULE$.classForName$default$3()));
      kryo.register(Utils$.MODULE$.classForName("scala.math.Ordering$Reverse", Utils$.MODULE$.classForName$default$2(), Utils$.MODULE$.classForName$default$3()));
      kryo.register(Utils$.MODULE$.classForName("scala.reflect.ClassTag$GenericClassTag", Utils$.MODULE$.classForName$default$2(), Utils$.MODULE$.classForName$default$3()));
      kryo.register(ArrayBuffer.class);
      kryo.register(byte[][].class);
      KryoSerializer$.MODULE$.org$apache$spark$serializer$KryoSerializer$$loadableSparkClasses().foreach((clazz) -> {
         Object var10000;
         try {
            var10000 = kryo.register(clazz);
         } catch (Throwable var6) {
            if (var6 != null) {
               Option var5 = scala.util.control.NonFatal..MODULE$.unapply(var6);
               if (!var5.isEmpty()) {
                  var10000 = BoxedUnit.UNIT;
                  return var10000;
               }
            }

            if (!(var6 instanceof NoClassDefFoundError) || !Utils$.MODULE$.isTesting()) {
               throw var6;
            }

            var10000 = BoxedUnit.UNIT;
         }

         return var10000;
      });
      kryo.setClassLoader(classLoader);
      return kryo;
   }

   public Serializer setDefaultClassLoader(final ClassLoader classLoader) {
      super.setDefaultClassLoader(classLoader);
      this.internalPool().reset();
      return this;
   }

   public SerializerInstance newInstance() {
      return new KryoSerializerInstance(this, this.useUnsafe(), this.usePool());
   }

   private boolean supportsRelocationOfSerializedObjects$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$0) {
            this.supportsRelocationOfSerializedObjects = ((KryoSerializerInstance)this.newInstance()).getAutoReset();
            this.bitmap$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.supportsRelocationOfSerializedObjects;
   }

   public boolean supportsRelocationOfSerializedObjects() {
      return !this.bitmap$0 ? this.supportsRelocationOfSerializedObjects$lzycompute() : this.supportsRelocationOfSerializedObjects;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$userRegistrators$2(final String x$2) {
      return !x$2.isEmpty();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$classesToRegister$2(final String x$4) {
      return !x$4.isEmpty();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$newKryo$3(final Tuple2 check$ifrefutable$1) {
      return check$ifrefutable$1 != null;
   }

   private final void registerAvro$1(final ClassTag ct, final KryoBase kryo$1) {
      kryo$1.register(ct.runtimeClass(), new GenericAvroSerializer(this.avroSchemas()));
   }

   // $FF: synthetic method
   public static final void $anonfun$newKryo$8(final KryoBase kryo$1, final KryoRegistrator reg) {
      reg.registerClasses(kryo$1);
   }

   public KryoSerializer(final SparkConf conf) {
      Logging.$init$(this);
      this.bufferSizeKb = BoxesRunTime.unboxToLong(conf.get(Kryo$.MODULE$.KRYO_SERIALIZER_BUFFER_SIZE()));
      if (this.bufferSizeKb() >= ByteUnit.GiB.toKiB(2L)) {
         throw new SparkIllegalArgumentException("INVALID_KRYO_SERIALIZER_BUFFER_SIZE", (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("bufferSizeConfKey"), Kryo$.MODULE$.KRYO_SERIALIZER_BUFFER_SIZE().key()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("bufferSizeConfValue"), Long.toString(ByteUnit.KiB.toMiB(this.bufferSizeKb())))}))));
      } else {
         this.bufferSize = (int)ByteUnit.KiB.toBytes(this.bufferSizeKb());
         this.maxBufferSizeMb = (int)BoxesRunTime.unboxToLong(conf.get(Kryo$.MODULE$.KRYO_SERIALIZER_MAX_BUFFER_SIZE()));
         if ((long)this.maxBufferSizeMb() >= ByteUnit.GiB.toMiB(2L)) {
            throw new SparkIllegalArgumentException("INVALID_KRYO_SERIALIZER_BUFFER_SIZE", (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("bufferSizeConfKey"), Kryo$.MODULE$.KRYO_SERIALIZER_MAX_BUFFER_SIZE().key()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("bufferSizeConfValue"), Integer.toString(this.maxBufferSizeMb()))}))));
         } else {
            this.maxBufferSize = (int)ByteUnit.MiB.toBytes((long)this.maxBufferSizeMb());
            this.referenceTracking = BoxesRunTime.unboxToBoolean(conf.get(Kryo$.MODULE$.KRYO_REFERENCE_TRACKING()));
            this.registrationRequired = BoxesRunTime.unboxToBoolean(conf.get(Kryo$.MODULE$.KRYO_REGISTRATION_REQUIRED()));
            this.userRegistrators = (Seq)((IterableOps)((IterableOps)conf.get(Kryo$.MODULE$.KRYO_USER_REGISTRATORS())).map((x$1) -> x$1.trim())).filter((x$2) -> BoxesRunTime.boxToBoolean($anonfun$userRegistrators$2(x$2)));
            this.classesToRegister = (Seq)((IterableOps)((IterableOps)conf.get(Kryo$.MODULE$.KRYO_CLASSES_TO_REGISTER())).map((x$3) -> x$3.trim())).filter((x$4) -> BoxesRunTime.boxToBoolean($anonfun$classesToRegister$2(x$4)));
            this.avroSchemas = conf.getAvroSchema();
            this.useUnsafe = BoxesRunTime.unboxToBoolean(conf.get(Kryo$.MODULE$.KRYO_USE_UNSAFE()));
            this.usePool = BoxesRunTime.unboxToBoolean(conf.get(Kryo$.MODULE$.KRYO_USE_POOL()));
         }
      }
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   private class PoolWrapper implements KryoPool {
      private KryoPool pool;
      // $FF: synthetic field
      public final KryoSerializer $outer;

      private KryoPool pool() {
         return this.pool;
      }

      private void pool_$eq(final KryoPool x$1) {
         this.pool = x$1;
      }

      public Kryo borrow() {
         return this.pool().borrow();
      }

      public void release(final Kryo kryo) {
         this.pool().release(kryo);
      }

      public Object run(final KryoCallback kryoCallback) {
         return this.pool().run(kryoCallback);
      }

      public void reset() {
         this.pool_$eq(this.getPool());
      }

      private KryoPool getPool() {
         return (new KryoPool.Builder(this.org$apache$spark$serializer$KryoSerializer$PoolWrapper$$$outer().org$apache$spark$serializer$KryoSerializer$$factory())).softReferences().build();
      }

      // $FF: synthetic method
      public KryoSerializer org$apache$spark$serializer$KryoSerializer$PoolWrapper$$$outer() {
         return this.$outer;
      }

      public PoolWrapper() {
         if (KryoSerializer.this == null) {
            throw null;
         } else {
            this.$outer = KryoSerializer.this;
            super();
            this.pool = this.getPool();
         }
      }
   }
}
