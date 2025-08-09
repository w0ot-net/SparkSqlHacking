package org.apache.spark.mllib.evaluation;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.ml.stat.SummarizerBuffer;
import org.apache.spark.mllib.linalg.Vectors$;
import org.apache.spark.mllib.stat.Statistics$;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.SeqOps;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u00055b\u0001B\t\u0013\u0001uA\u0001B\u000b\u0001\u0003\u0002\u0003\u0006Ia\u000b\u0005\t{\u0001\u0011\t\u0011)A\u0005}!)\u0011\t\u0001C\u0001\u0005\")\u0011\t\u0001C\u0001)\"1\u0011\t\u0001C\u0001)yC\u0001\u0002\u001e\u0001\t\u0006\u0004%I!\u001e\u0005\t}\u0002A)\u0019!C\u0005\u007f\"I\u0011q\u0001\u0001\t\u0006\u0004%Ia \u0005\n\u0003\u0013\u0001\u0001R1A\u0005\n}D\u0011\"a\u0003\u0001\u0011\u000b\u0007I\u0011B@\t\r\u00055\u0001\u0001\"\u0001\u0000\u0011\u0019\t\t\u0002\u0001C\u0001\u007f\"1\u0011Q\u0003\u0001\u0005\u0002}Da!!\u0007\u0001\t\u0003y\bBBA\u000f\u0001\u0011\u0005q\u0010\u0003\u0005\u0002\"\u0001!\tAFA\u0012\u0005E\u0011Vm\u001a:fgNLwN\\'fiJL7m\u001d\u0006\u0003'Q\t!\"\u001a<bYV\fG/[8o\u0015\t)b#A\u0003nY2L'M\u0003\u0002\u00181\u0005)1\u000f]1sW*\u0011\u0011DG\u0001\u0007CB\f7\r[3\u000b\u0003m\t1a\u001c:h\u0007\u0001\u00192\u0001\u0001\u0010%!\ty\"%D\u0001!\u0015\u0005\t\u0013!B:dC2\f\u0017BA\u0012!\u0005\u0019\te.\u001f*fMB\u0011Q\u0005K\u0007\u0002M)\u0011qEF\u0001\tS:$XM\u001d8bY&\u0011\u0011F\n\u0002\b\u0019><w-\u001b8h\u0003e\u0001(/\u001a3jGRLwN\\!oI>\u00137/\u001a:wCRLwN\\:1\u00051\"\u0004cA\u00171e5\taF\u0003\u00020-\u0005\u0019!\u000f\u001a3\n\u0005Er#a\u0001*E\tB\u00111\u0007\u000e\u0007\u0001\t%)\u0014!!A\u0001\u0002\u000b\u0005aGA\u0002`IE\n\"a\u000e\u001e\u0011\u0005}A\u0014BA\u001d!\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"aH\u001e\n\u0005q\u0002#a\u0002)s_\u0012,8\r^\u0001\u000ei\"\u0014x.^4i\u001fJLw-\u001b8\u0011\u0005}y\u0014B\u0001!!\u0005\u001d\u0011un\u001c7fC:\fa\u0001P5oSRtDcA\"F\u0015B\u0011A\tA\u0007\u0002%!)!f\u0001a\u0001\rB\u0012q)\u0013\t\u0004[AB\u0005CA\u001aJ\t%)T)!A\u0001\u0002\u000b\u0005a\u0007C\u0003>\u0007\u0001\u0007a\bK\u0002\u0004\u0019J\u0003\"!\u0014)\u000e\u00039S!a\u0014\f\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002R\u001d\n)1+\u001b8dK\u0006\n1+A\u00033]Ar\u0003\u0007\u0006\u0002D+\")!\u0006\u0002a\u0001-B\u0012q+\u0017\t\u0004[AB\u0006CA\u001aZ\t%QV+!A\u0001\u0002\u000b\u0005aGA\u0002`IIB3\u0001\u0002']C\u0005i\u0016!B\u0019/e9\u0002DCA\"`\u0011\u0015QS\u00011\u0001a!\t\t\u0017O\u0004\u0002c]:\u00111\r\u001c\b\u0003I.t!!\u001a6\u000f\u0005\u0019LW\"A4\u000b\u0005!d\u0012A\u0002\u001fs_>$h(C\u0001\u001c\u0013\tI\"$\u0003\u0002\u00181%\u0011QNF\u0001\u0004gFd\u0017BA8q\u0003\u001d\u0001\u0018mY6bO\u0016T!!\u001c\f\n\u0005I\u001c(!\u0003#bi\u00064%/Y7f\u0015\ty\u0007/A\u0004tk6l\u0017M]=\u0016\u0003Y\u0004\"a\u001e?\u000e\u0003aT!!\u001f>\u0002\tM$\u0018\r\u001e\u0006\u0003wZ\t!!\u001c7\n\u0005uD(\u0001E*v[6\f'/\u001b>fe\n+hMZ3s\u0003\r\u00196+_\u000b\u0003\u0003\u0003\u00012aHA\u0002\u0013\r\t)\u0001\t\u0002\u0007\t>,(\r\\3\u0002\u000bM\u001bVM\u001d:\u0002\u000bM\u001bFo\u001c;\u0002\u000bM\u001b&/Z4\u0002#\u0015D\b\u000f\\1j]\u0016$g+\u0019:jC:\u001cW\rK\u0002\f\u0019r\u000b\u0011#\\3b]\u0006\u00137o\u001c7vi\u0016,%O]8sQ\raA\nX\u0001\u0011[\u0016\fgnU9vCJ,G-\u0012:s_JD3!\u0004']\u0003Q\u0011xn\u001c;NK\u0006t7+];be\u0016$WI\u001d:pe\"\u001aa\u0002\u0014/\u0002\u0005I\u0014\u0004fA\bM9\u0006)1m\\;oiV\u0011\u0011Q\u0005\t\u0004?\u0005\u001d\u0012bAA\u0015A\t!Aj\u001c8hQ\r\u0001A\n\u0018"
)
public class RegressionMetrics implements Logging {
   private SummarizerBuffer summary;
   private double SSy;
   private double SSerr;
   private double SStot;
   private double SSreg;
   private final RDD predictionAndObservations;
   private final boolean throughOrigin;
   private transient Logger org$apache$spark$internal$Logging$$log_;
   private volatile byte bitmap$0;

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

   private SummarizerBuffer summary$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 1) == 0) {
            RDD weightedVectors = this.predictionAndObservations.map((x0$1) -> {
               if (x0$1 instanceof Tuple3 var3) {
                  Object prediction = var3._1();
                  Object observation = var3._2();
                  Object weight = var3._3();
                  if (prediction instanceof Double) {
                     double var7 = BoxesRunTime.unboxToDouble(prediction);
                     if (observation instanceof Double) {
                        double var9 = BoxesRunTime.unboxToDouble(observation);
                        if (weight instanceof Double) {
                           double var11 = BoxesRunTime.unboxToDouble(weight);
                           return new Tuple2(Vectors$.MODULE$.dense(var9, (Seq).MODULE$.wrapDoubleArray(new double[]{var9 - var7, var7})), BoxesRunTime.boxToDouble(var11));
                        }
                     }
                  }
               }

               if (x0$1 instanceof Tuple2 var13) {
                  Object prediction = var13._1();
                  Object observation = var13._2();
                  if (prediction instanceof Double) {
                     double var16 = BoxesRunTime.unboxToDouble(prediction);
                     if (observation instanceof Double) {
                        double var18 = BoxesRunTime.unboxToDouble(observation);
                        return new Tuple2(Vectors$.MODULE$.dense(var18, (Seq).MODULE$.wrapDoubleArray(new double[]{var18 - var16, var16})), BoxesRunTime.boxToDouble((double)1.0F));
                     }
                  }
               }

               throw new MatchError(x0$1);
            }, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
            this.summary = Statistics$.MODULE$.colStats(weightedVectors, new scala.collection.immutable..colon.colon("mean", new scala.collection.immutable..colon.colon("normL1", new scala.collection.immutable..colon.colon("normL2", new scala.collection.immutable..colon.colon("variance", scala.collection.immutable.Nil..MODULE$)))));
            this.bitmap$0 = (byte)(this.bitmap$0 | 1);
         }
      } catch (Throwable var4) {
         throw var4;
      }

      return this.summary;
   }

   private SummarizerBuffer summary() {
      return (byte)(this.bitmap$0 & 1) == 0 ? this.summary$lzycompute() : this.summary;
   }

   private double SSy$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 2) == 0) {
            this.SSy = scala.math.package..MODULE$.pow(this.summary().normL2().apply(0), (double)2.0F);
            this.bitmap$0 = (byte)(this.bitmap$0 | 2);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.SSy;
   }

   private double SSy() {
      return (byte)(this.bitmap$0 & 2) == 0 ? this.SSy$lzycompute() : this.SSy;
   }

   private double SSerr$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 4) == 0) {
            this.SSerr = scala.math.package..MODULE$.pow(this.summary().normL2().apply(1), (double)2.0F);
            this.bitmap$0 = (byte)(this.bitmap$0 | 4);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.SSerr;
   }

   private double SSerr() {
      return (byte)(this.bitmap$0 & 4) == 0 ? this.SSerr$lzycompute() : this.SSerr;
   }

   private double SStot$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 8) == 0) {
            this.SStot = this.summary().variance().apply(0) * (this.summary().weightSum() - (double)1);
            this.bitmap$0 = (byte)(this.bitmap$0 | 8);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.SStot;
   }

   private double SStot() {
      return (byte)(this.bitmap$0 & 8) == 0 ? this.SStot$lzycompute() : this.SStot;
   }

   private double SSreg$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 16) == 0) {
            this.SSreg = scala.math.package..MODULE$.pow(this.summary().normL2().apply(2), (double)2.0F) + scala.math.package..MODULE$.pow(this.summary().mean().apply(0), (double)2.0F) * this.summary().weightSum() - (double)2 * this.summary().mean().apply(0) * this.summary().mean().apply(2) * this.summary().weightSum();
            this.bitmap$0 = (byte)(this.bitmap$0 | 16);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.SSreg;
   }

   private double SSreg() {
      return (byte)(this.bitmap$0 & 16) == 0 ? this.SSreg$lzycompute() : this.SSreg;
   }

   public double explainedVariance() {
      return this.SSreg() / this.summary().weightSum();
   }

   public double meanAbsoluteError() {
      return this.summary().normL1().apply(1) / this.summary().weightSum();
   }

   public double meanSquaredError() {
      return this.SSerr() / this.summary().weightSum();
   }

   public double rootMeanSquaredError() {
      return scala.math.package..MODULE$.sqrt(this.meanSquaredError());
   }

   public double r2() {
      return this.throughOrigin ? (double)1 - this.SSerr() / this.SSy() : (double)1 - this.SSerr() / this.SStot();
   }

   public long count() {
      return this.summary().count();
   }

   public RegressionMetrics(final RDD predictionAndObservations, final boolean throughOrigin) {
      this.predictionAndObservations = predictionAndObservations;
      this.throughOrigin = throughOrigin;
      Logging.$init$(this);
   }

   public RegressionMetrics(final RDD predictionAndObservations) {
      this(predictionAndObservations, false);
   }

   public RegressionMetrics(final Dataset predictionAndObservations) {
      this(predictionAndObservations.rdd().map(new Serializable() {
         private static final long serialVersionUID = 0L;

         public final Tuple3 apply(final Row x0$1) {
            if (x0$1 != null) {
               Some var4 = org.apache.spark.sql.Row..MODULE$.unapplySeq(x0$1);
               if (!var4.isEmpty() && var4.get() != null && ((SeqOps)var4.get()).lengthCompare(3) == 0) {
                  Object prediction = ((SeqOps)var4.get()).apply(0);
                  Object label = ((SeqOps)var4.get()).apply(1);
                  Object weight = ((SeqOps)var4.get()).apply(2);
                  if (prediction instanceof Double) {
                     double var8 = BoxesRunTime.unboxToDouble(prediction);
                     if (label instanceof Double) {
                        double var10 = BoxesRunTime.unboxToDouble(label);
                        if (weight instanceof Double) {
                           double var12 = BoxesRunTime.unboxToDouble(weight);
                           return new Tuple3(BoxesRunTime.boxToDouble(var8), BoxesRunTime.boxToDouble(var10), BoxesRunTime.boxToDouble(var12));
                        }
                     }
                  }
               }
            }

            if (x0$1 != null) {
               Some var14 = org.apache.spark.sql.Row..MODULE$.unapplySeq(x0$1);
               if (!var14.isEmpty() && var14.get() != null && ((SeqOps)var14.get()).lengthCompare(2) == 0) {
                  Object prediction = ((SeqOps)var14.get()).apply(0);
                  Object label = ((SeqOps)var14.get()).apply(1);
                  if (prediction instanceof Double) {
                     double var17 = BoxesRunTime.unboxToDouble(prediction);
                     if (label instanceof Double) {
                        double var19 = BoxesRunTime.unboxToDouble(label);
                        return new Tuple3(BoxesRunTime.boxToDouble(var17), BoxesRunTime.boxToDouble(var19), BoxesRunTime.boxToDouble((double)1.0F));
                     }
                  }
               }
            }

            throw new IllegalArgumentException("Expected Row of tuples, got " + x0$1);
         }
      }, scala.reflect.ClassTag..MODULE$.apply(Tuple3.class)));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
