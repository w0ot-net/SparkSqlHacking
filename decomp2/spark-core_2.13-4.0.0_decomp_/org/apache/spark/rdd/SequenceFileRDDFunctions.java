package org.apache.spark.rdd;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.SequenceFileOutputFormat;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.LogEntry.;
import org.slf4j.Logger;
import scala.Function0;
import scala.Function1;
import scala.Option;
import scala.StringContext;
import scala.Tuple2;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.Null;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015d\u0001B\u0006\r\u0001UA\u0001b\f\u0001\u0003\u0002\u0003\u0006I\u0001\r\u0005\t\u000b\u0002\u0011\t\u0011)A\u0005\r\"A1\f\u0001B\u0001B\u0003%A\f\u0003\u0005b\u0001\t\r\t\u0015a\u0003c\u0011!A\u0007AaA!\u0002\u0017I\u0007\u0002C8\u0001\u0005\u0007\u0005\u000b1\u00029\t\u0011E\u0004!1!Q\u0001\fIDQa\u001d\u0001\u0005\u0002QDq!!\u0004\u0001\t\u0003\ty\u0001C\u0005\u0002D\u0001\t\n\u0011\"\u0001\u0002F\tA2+Z9vK:\u001cWMR5mKJ#EIR;oGRLwN\\:\u000b\u00055q\u0011a\u0001:eI*\u0011q\u0002E\u0001\u0006gB\f'o\u001b\u0006\u0003#I\ta!\u00199bG\",'\"A\n\u0002\u0007=\u0014xm\u0001\u0001\u0016\u0007YI4i\u0005\u0003\u0001/u\u0019\u0003C\u0001\r\u001c\u001b\u0005I\"\"\u0001\u000e\u0002\u000bM\u001c\u0017\r\\1\n\u0005qI\"AB!osJ+g\r\u0005\u0002\u001fC5\tqD\u0003\u0002!\u001d\u0005A\u0011N\u001c;fe:\fG.\u0003\u0002#?\t9Aj\\4hS:<\u0007C\u0001\u0013-\u001d\t)#F\u0004\u0002'S5\tqE\u0003\u0002))\u00051AH]8pizJ\u0011AG\u0005\u0003We\tq\u0001]1dW\u0006<W-\u0003\u0002.]\ta1+\u001a:jC2L'0\u00192mK*\u00111&G\u0001\u0005g\u0016dg\rE\u00022eQj\u0011\u0001D\u0005\u0003g1\u00111A\u0015#E!\u0011ARg\u000e\"\n\u0005YJ\"A\u0002+va2,'\u0007\u0005\u00029s1\u0001A!\u0002\u001e\u0001\u0005\u0004Y$!A&\u0012\u0005qz\u0004C\u0001\r>\u0013\tq\u0014DA\u0004O_RD\u0017N\\4\u0011\u0005a\u0001\u0015BA!\u001a\u0005\r\te.\u001f\t\u0003q\r#Q\u0001\u0012\u0001C\u0002m\u0012\u0011AV\u0001\u0012?.,\u0017p\u0016:ji\u0006\u0014G.Z\"mCN\u001c\bGA$Q!\rAEj\u0014\b\u0003\u0013*\u0003\"AJ\r\n\u0005-K\u0012A\u0002)sK\u0012,g-\u0003\u0002N\u001d\n)1\t\\1tg*\u00111*\u0007\t\u0003qA#\u0011\"\u0015\u0002\u0002\u0002\u0003\u0005)\u0011\u0001*\u0003\u0007}#\u0013'\u0005\u0002='B\u0011A+W\u0007\u0002+*\u0011akV\u0001\u0003S>T!\u0001\u0017\t\u0002\r!\fGm\\8q\u0013\tQVK\u0001\u0005Xe&$\u0018M\u00197f\u0003Myf/\u00197vK^\u0013\u0018\u000e^1cY\u0016\u001cE.Y:ta\tiv\fE\u0002I\u0019z\u0003\"\u0001O0\u0005\u0013\u0001\u001c\u0011\u0011!A\u0001\u0006\u0003\u0011&aA0%e\u0005QQM^5eK:\u001cW\rJ\u0019\u0011\u0007\r,wG\u0004\u00022I&\u00111\u0006D\u0005\u0003M\u001e\u0014!\"S:Xe&$\u0018M\u00197f\u0015\tYC\"\u0001\u0006fm&$WM\\2fII\u00022A[78\u001b\u0005Y'B\u00017\u001a\u0003\u001d\u0011XM\u001a7fGRL!A\\6\u0003\u0011\rc\u0017m]:UC\u001e\f!\"\u001a<jI\u0016t7-\u001a\u00134!\r\u0019WMQ\u0001\u000bKZLG-\u001a8dK\u0012\"\u0004c\u00016n\u0005\u00061A(\u001b8jiz\"R!^>}\u0003\u0007!RA^<ysj\u0004B!\r\u00018\u0005\")\u0011\r\u0003a\u0002E\")\u0001\u000e\u0003a\u0002S\")q\u000e\u0003a\u0002a\")\u0011\u000f\u0003a\u0002e\")q\u0006\u0003a\u0001a!)Q\t\u0003a\u0001{B\u001aa0!\u0001\u0011\u0007!cu\u0010E\u00029\u0003\u0003!\u0011\"\u0015?\u0002\u0002\u0003\u0005)\u0011\u0001*\t\rmC\u0001\u0019AA\u0003a\u0011\t9!a\u0003\u0011\t!c\u0015\u0011\u0002\t\u0004q\u0005-AA\u00031\u0002\u0004\u0005\u0005\t\u0011!B\u0001%\u0006\u00112/\u0019<f\u0003N\u001cV-];f]\u000e,g)\u001b7f)\u0019\t\t\"a\u0006\u0002\"A\u0019\u0001$a\u0005\n\u0007\u0005U\u0011D\u0001\u0003V]&$\bbBA\r\u0013\u0001\u0007\u00111D\u0001\u0005a\u0006$\b\u000eE\u0002I\u0003;I1!a\bO\u0005\u0019\u0019FO]5oO\"I\u00111E\u0005\u0011\u0002\u0003\u0007\u0011QE\u0001\u0006G>$Wm\u0019\t\u00061\u0005\u001d\u00121F\u0005\u0004\u0003SI\"AB(qi&|g\u000e\r\u0003\u0002.\u0005E\u0002\u0003\u0002%M\u0003_\u00012\u0001OA\u0019\t1\t\u0019$!\t\u0002\u0002\u0003\u0005)\u0011AA\u001b\u0005\ryFeM\t\u0004y\u0005]\u0002\u0003BA\u001d\u0003\u007fi!!a\u000f\u000b\u0007\u0005uR+\u0001\u0005d_6\u0004(/Z:t\u0013\u0011\t\t%a\u000f\u0003!\r{W\u000e\u001d:fgNLwN\\\"pI\u0016\u001c\u0017\u0001H:bm\u0016\f5oU3rk\u0016t7-\u001a$jY\u0016$C-\u001a4bk2$HEM\u000b\u0003\u0003\u000fRC!!\u0013\u0002TA)\u0001$a\n\u0002LA\"\u0011QJA)!\u0011AE*a\u0014\u0011\u0007a\n\t\u0006B\u0006\u00024)\t\t\u0011!A\u0003\u0002\u0005U2FAA+!\u0011\t9&!\u0019\u000e\u0005\u0005e#\u0002BA.\u0003;\n\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0007\u0005}\u0013$\u0001\u0006b]:|G/\u0019;j_:LA!a\u0019\u0002Z\t\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3"
)
public class SequenceFileRDDFunctions implements Logging, Serializable {
   private final RDD self;
   private final Class _keyWritableClass;
   private final Class _valueWritableClass;
   private final Function1 evidence$1;
   private final ClassTag evidence$2;
   private final Function1 evidence$3;
   private final ClassTag evidence$4;
   private transient Logger org$apache$spark$internal$Logging$$log_;

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

   public void saveAsSequenceFile(final String path, final Option codec) {
      this.self.withScope((JFunction0.mcV.sp)() -> {
         boolean var33;
         label55: {
            label54: {
               RDD x$1 = this.self;
               ClassTag x$2 = this.evidence$2;
               ClassTag x$3 = this.evidence$4;
               Null x$4 = RDD$.MODULE$.rddToPairRDDFunctions$default$4(x$1);
               Class var10000 = RDD$.MODULE$.rddToPairRDDFunctions(x$1, x$2, x$3, (Ordering)null).keyClass();
               Class var4 = this._keyWritableClass;
               if (var10000 == null) {
                  if (var4 != null) {
                     break label54;
                  }
               } else if (!var10000.equals(var4)) {
                  break label54;
               }

               var33 = false;
               break label55;
            }

            var33 = true;
         }

         boolean convertKey;
         label47: {
            label46: {
               convertKey = var33;
               RDD x$5 = this.self;
               ClassTag x$6 = this.evidence$2;
               ClassTag x$7 = this.evidence$4;
               Null x$8 = RDD$.MODULE$.rddToPairRDDFunctions$default$4(x$5);
               Class var34 = RDD$.MODULE$.rddToPairRDDFunctions(x$5, x$6, x$7, (Ordering)null).valueClass();
               Class var10 = this._valueWritableClass;
               if (var34 == null) {
                  if (var10 != null) {
                     break label46;
                  }
               } else if (!var34.equals(var10)) {
                  break label46;
               }

               var33 = false;
               break label47;
            }

            var33 = true;
         }

         boolean convertValue = var33;
         this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Saving as sequence file of type "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"(", ","})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.KEY..MODULE$, this._keyWritableClass.getSimpleName())})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ")"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.VALUE..MODULE$, this._valueWritableClass.getSimpleName())}))))));
         Class format = SequenceFileOutputFormat.class;
         JobConf jobConf = new JobConf(this.self.context().hadoopConfiguration());
         if (!convertKey && !convertValue) {
            RDD x$9 = this.self;
            ClassTag x$10 = this.evidence$2;
            ClassTag x$11 = this.evidence$4;
            Null x$12 = RDD$.MODULE$.rddToPairRDDFunctions$default$4(x$9);
            RDD$.MODULE$.rddToPairRDDFunctions(x$9, x$10, x$11, (Ordering)null).saveAsHadoopFile(path, this._keyWritableClass, this._valueWritableClass, format, jobConf, codec);
         } else if (!convertKey && convertValue) {
            RDD x$13 = this.self.map((x) -> new Tuple2(x._1(), anyToWritable$1(x._2(), this.evidence$3)), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
            ClassTag x$14 = this.evidence$2;
            ClassTag x$15 = scala.reflect.ClassTag..MODULE$.apply(Writable.class);
            Null x$16 = RDD$.MODULE$.rddToPairRDDFunctions$default$4(x$13);
            RDD$.MODULE$.rddToPairRDDFunctions(x$13, x$14, x$15, (Ordering)null).saveAsHadoopFile(path, this._keyWritableClass, this._valueWritableClass, format, jobConf, codec);
         } else if (convertKey && !convertValue) {
            RDD x$17 = this.self.map((x) -> new Tuple2(anyToWritable$1(x._1(), this.evidence$1), x._2()), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
            ClassTag x$18 = scala.reflect.ClassTag..MODULE$.apply(Writable.class);
            ClassTag x$19 = this.evidence$4;
            Null x$20 = RDD$.MODULE$.rddToPairRDDFunctions$default$4(x$17);
            RDD$.MODULE$.rddToPairRDDFunctions(x$17, x$18, x$19, (Ordering)null).saveAsHadoopFile(path, this._keyWritableClass, this._valueWritableClass, format, jobConf, codec);
         } else if (convertKey && convertValue) {
            RDD x$21 = this.self.map((x) -> new Tuple2(anyToWritable$1(x._1(), this.evidence$1), anyToWritable$1(x._2(), this.evidence$3)), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
            ClassTag x$22 = scala.reflect.ClassTag..MODULE$.apply(Writable.class);
            ClassTag x$23 = scala.reflect.ClassTag..MODULE$.apply(Writable.class);
            Null x$24 = RDD$.MODULE$.rddToPairRDDFunctions$default$4(x$21);
            RDD$.MODULE$.rddToPairRDDFunctions(x$21, x$22, x$23, (Ordering)null).saveAsHadoopFile(path, this._keyWritableClass, this._valueWritableClass, format, jobConf, codec);
         }
      });
   }

   public Option saveAsSequenceFile$default$2() {
      return scala.None..MODULE$;
   }

   private static final Writable anyToWritable$1(final Object u, final Function1 evidence$5) {
      return (Writable)evidence$5.apply(u);
   }

   public SequenceFileRDDFunctions(final RDD self, final Class _keyWritableClass, final Class _valueWritableClass, final Function1 evidence$1, final ClassTag evidence$2, final Function1 evidence$3, final ClassTag evidence$4) {
      this.self = self;
      this._keyWritableClass = _keyWritableClass;
      this._valueWritableClass = _valueWritableClass;
      this.evidence$1 = evidence$1;
      this.evidence$2 = evidence$2;
      this.evidence$3 = evidence$3;
      this.evidence$4 = evidence$4;
      Logging.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
