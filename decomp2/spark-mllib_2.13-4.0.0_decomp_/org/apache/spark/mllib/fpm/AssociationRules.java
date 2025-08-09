package org.apache.spark.mllib.fpm;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.List;
import java.util.Map;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.rdd.RDD;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.immutable.ArraySeq;
import scala.collection.immutable.Set;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Null;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\t\u0015b\u0001B\u000f\u001f\u0001%B\u0001B\u0011\u0001\u0003\u0002\u0004%Ia\u0011\u0005\t\u000f\u0002\u0011\t\u0019!C\u0005\u0011\"Aa\n\u0001B\u0001B\u0003&A\t\u0003\u0004P\u0001\u0011\u0005a\u0004\u0015\u0005\u0006\u001f\u0002!\t\u0001\u0016\u0005\u0006=\u0002!\ta\u0018\u0005\u0006G\u0002!\t\u0001\u001a\u0005\u0007G\u0002!\t!a6\t\r\r\u0004A\u0011\u0001B\u0002\u000f\u0015Ih\u0004#\u0001{\r\u0015ib\u0004#\u0001|\u0011\u0019y5\u0002\"\u0001\u0002\b\u00191\u0011\u0011B\u0006\u0001\u0003\u0017A!\"a\u0004\u000e\u0005\u000b\u0007I\u0011AA\t\u0011)\t\t$\u0004B\u0001B\u0003%\u00111\u0003\u0005\u000b\u0003ki!Q1A\u0005\u0002\u0005E\u0001BCA\u001d\u001b\t\u0005\t\u0015!\u0003\u0002\u0014!Q\u0011QH\u0007\u0003\u0006\u0004%\tAI\"\t\u0013\u0005}RB!A!\u0002\u0013!\u0005\"CA!\u001b\t\u0005\t\u0015!\u0003E\u0011)\t\u0019%\u0004B\u0001B\u0003%\u0011Q\t\u0005\b\u001f6!\tAHA&\u0011\u0019\ty&\u0004C\u0001\u0007\"9\u00111M\u0007\u0005\u0002\u0005\u0015\u0004bBA7\u001b\u0011\u0005\u0011q\u000e\u0005\b\u0003\u007fjA\u0011AA8\u0011\u001d\t\u0019)\u0004C!\u0003\u000bC\u0011\"!'\f\u0003\u0003%I!a'\u0003!\u0005\u001b8o\\2jCRLwN\u001c*vY\u0016\u001c(BA\u0010!\u0003\r1\u0007/\u001c\u0006\u0003C\t\nQ!\u001c7mS\nT!a\t\u0013\u0002\u000bM\u0004\u0018M]6\u000b\u0005\u00152\u0013AB1qC\u000eDWMC\u0001(\u0003\ry'oZ\u0002\u0001'\u0011\u0001!\u0006\r\u001c\u0011\u0005-rS\"\u0001\u0017\u000b\u00035\nQa]2bY\u0006L!a\f\u0017\u0003\r\u0005s\u0017PU3g!\t\tD'D\u00013\u0015\t\u0019$%\u0001\u0005j]R,'O\\1m\u0013\t)$GA\u0004M_\u001e<\u0017N\\4\u0011\u0005]zdB\u0001\u001d>\u001d\tID(D\u0001;\u0015\tY\u0004&\u0001\u0004=e>|GOP\u0005\u0002[%\u0011a\bL\u0001\ba\u0006\u001c7.Y4f\u0013\t\u0001\u0015I\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0002?Y\u0005iQ.\u001b8D_:4\u0017\u000eZ3oG\u0016,\u0012\u0001\u0012\t\u0003W\u0015K!A\u0012\u0017\u0003\r\u0011{WO\u00197f\u0003Ei\u0017N\\\"p]\u001aLG-\u001a8dK~#S-\u001d\u000b\u0003\u00132\u0003\"a\u000b&\n\u0005-c#\u0001B+oSRDq!\u0014\u0002\u0002\u0002\u0003\u0007A)A\u0002yIE\na\"\\5o\u0007>tg-\u001b3f]\u000e,\u0007%\u0001\u0004=S:LGO\u0010\u000b\u0003#N\u0003\"A\u0015\u0001\u000e\u0003yAQA\u0011\u0003A\u0002\u0011#\u0012!\u0015\u0015\u0004\u000bYc\u0006CA,[\u001b\u0005A&BA-#\u0003)\tgN\\8uCRLwN\\\u0005\u00037b\u0013QaU5oG\u0016\f\u0013!X\u0001\u0006c9*d\u0006M\u0001\u0011g\u0016$X*\u001b8D_:4\u0017\u000eZ3oG\u0016$\"\u0001Y1\u000e\u0003\u0001AQA\u0011\u0004A\u0002\u0011C3A\u0002,]\u0003\r\u0011XO\\\u000b\u0004K\u0006=Fc\u00014\u0002BR\u0019q-!-\u0011\u0007!\\W.D\u0001j\u0015\tQ'%A\u0002sI\u0012L!\u0001\\5\u0003\u0007I#E\t\u0005\u0003o\u001b\u00055fBA8\u000b\u001d\t\u0001\bP\u0004\u0002ro:\u0011!O\u001e\b\u0003gVt!!\u000f;\n\u0003\u001dJ!!\n\u0014\n\u0005\r\"\u0013BA\u0011#\u0013\ty\u0002%\u0001\tBgN|7-[1uS>t'+\u001e7fgB\u0011!kC\n\u0004\u0017)b\bcA?\u0002\u00065\taPC\u0002\u0000\u0003\u0003\t!![8\u000b\u0005\u0005\r\u0011\u0001\u00026bm\u0006L!\u0001\u0011@\u0015\u0003i\u0014AAU;mKV!\u0011QBA\u000f'\ri!FN\u0001\u000bC:$XmY3eK:$XCAA\n!\u0015Y\u0013QCA\r\u0013\r\t9\u0002\f\u0002\u0006\u0003J\u0014\u0018-\u001f\t\u0005\u00037\ti\u0002\u0004\u0001\u0005\u000f\u0005}QB1\u0001\u0002\"\t!\u0011\n^3n#\u0011\t\u0019#!\u000b\u0011\u0007-\n)#C\u0002\u0002(1\u0012qAT8uQ&tw\rE\u0002,\u0003WI1!!\f-\u0005\r\te.\u001f\u0015\u0004\u001dYc\u0016aC1oi\u0016\u001cW\rZ3oi\u0002B3a\u0004,]\u0003)\u0019wN\\:fcV,g\u000e\u001e\u0015\u0004!Yc\u0016aC2p]N,\u0017/^3oi\u0002B3!\u0005,]\u0003%1'/Z9V]&|g.\u0001\u0006ge\u0016\fXK\\5p]\u0002\naB\u001a:fc\u0006sG/Z2fI\u0016tG/\u0001\bge\u0016\f8i\u001c8tKF,XM\u001c;\u0011\t-\n9\u0005R\u0005\u0004\u0003\u0013b#AB(qi&|g\u000e\u0006\u0007\u0002N\u0005E\u0013QKA-\u00037\ni\u0006E\u0003\u0002P5\tI\"D\u0001\f\u0011\u001d\tyA\u0006a\u0001\u0003'AC!!\u0015W9\"9\u0011Q\u0007\fA\u0002\u0005M\u0001\u0006BA+-rCa!!\u0010\u0017\u0001\u0004!\u0005BBA!-\u0001\u0007A\tC\u0004\u0002DY\u0001\r!!\u0012\u0002\u0015\r|gNZ5eK:\u001cW\rK\u0002\u0018-r\u000bA\u0001\\5giV\u0011\u0011Q\t\u0015\u00051Y\u000bI'\t\u0002\u0002l\u0005)!G\f\u001b/a\u0005q!.\u0019<b\u0003:$XmY3eK:$XCAA9!\u0019\t\u0019(!\u001f\u0002\u001a5\u0011\u0011Q\u000f\u0006\u0005\u0003o\n\t!\u0001\u0003vi&d\u0017\u0002BA>\u0003k\u0012A\u0001T5ti\"\u001a\u0011D\u0016/\u0002\u001d)\fg/Y\"p]N,\u0017/^3oi\"\u001a!D\u0016/\u0002\u0011Q|7\u000b\u001e:j]\u001e$\"!a\"\u0011\t\u0005%\u0015\u0011\u0013\b\u0005\u0003\u0017\u000bi\t\u0005\u0002:Y%\u0019\u0011q\u0012\u0017\u0002\rA\u0013X\rZ3g\u0013\u0011\t\u0019*!&\u0003\rM#(/\u001b8h\u0015\r\ty\t\f\u0015\u0004\u001bYc\u0016\u0001D<sSR,'+\u001a9mC\u000e,GCAAO!\u0011\ty*!*\u000e\u0005\u0005\u0005&\u0002BAR\u0003\u0003\tA\u0001\\1oO&!\u0011qUAQ\u0005\u0019y%M[3di\"\u001a1B\u0016/)\u0007)1F\f\u0005\u0003\u0002\u001c\u0005=FaBA\u0010\u000f\t\u0007\u0011\u0011\u0005\u0005\n\u0003g;\u0011\u0011!a\u0002\u0003k\u000b!\"\u001a<jI\u0016t7-\u001a\u00132!\u0019\t9,!0\u0002.6\u0011\u0011\u0011\u0018\u0006\u0004\u0003wc\u0013a\u0002:fM2,7\r^\u0005\u0005\u0003\u007f\u000bIL\u0001\u0005DY\u0006\u001c8\u000fV1h\u0011\u001d\t\u0019m\u0002a\u0001\u0003\u000b\fAB\u001a:fc&#X-\\:fiN\u0004B\u0001[6\u0002HB1\u0011\u0011ZAh\u0003[s1a\\Af\u0013\r\tiMH\u0001\t\rB;%o\\<uQ&!\u0011\u0011[Aj\u0005-1%/Z9Ji\u0016l7/\u001a;\u000b\u0007\u00055g\u0004K\u0002\b-r+B!!7\u0002dR1\u00111\\Av\u0003c$B!!8\u0002fB!\u0001n[Ap!\u0011qW\"!9\u0011\t\u0005m\u00111\u001d\u0003\b\u0003?A!\u0019AA\u0011\u0011%\t9\u000fCA\u0001\u0002\b\tI/\u0001\u0006fm&$WM\\2fII\u0002b!a.\u0002>\u0006\u0005\bbBAb\u0011\u0001\u0007\u0011Q\u001e\t\u0005Q.\fy\u000f\u0005\u0004\u0002J\u0006=\u0017\u0011\u001d\u0005\b\u0003gD\u0001\u0019AA{\u0003-IG/Z7TkB\u0004xN\u001d;\u0011\u000f\u0005]\u0018Q`Aq\t6\u0011\u0011\u0011 \u0006\u0004\u0003wd\u0013AC2pY2,7\r^5p]&!\u0011q`A}\u0005\ri\u0015\r\u001d\u0015\u0005\u0011Y\u000bI'\u0006\u0003\u0003\u0006\teA\u0003\u0002B\u0004\u00057\u0001bA!\u0003\u0003\u0012\tUQB\u0001B\u0006\u0015\u0011\t\u0019A!\u0004\u000b\u0007\t=!%A\u0002ba&LAAa\u0005\u0003\f\t9!*\u0019<b%\u0012#\u0005\u0003\u00028\u000e\u0005/\u0001B!a\u0007\u0003\u001a\u00119\u0011qD\u0005C\u0002\u0005\u0005\u0002bBAb\u0013\u0001\u0007!Q\u0004\t\u0007\u0005\u0013\u0011\tBa\b\u0011\r\u0005%\u0017q\u001aB\fQ\rIa\u000b\u0018\u0015\u0004\u0001Yc\u0006"
)
public class AssociationRules implements Logging, Serializable {
   private double minConfidence;
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

   private double minConfidence() {
      return this.minConfidence;
   }

   private void minConfidence_$eq(final double x$1) {
      this.minConfidence = x$1;
   }

   public AssociationRules setMinConfidence(final double minConfidence) {
      .MODULE$.require(minConfidence >= (double)0.0F && minConfidence <= (double)1.0F, () -> "Minimal confidence must be in range [0, 1] but got " + minConfidence);
      this.minConfidence_$eq(minConfidence);
      return this;
   }

   public RDD run(final RDD freqItemsets, final ClassTag evidence$1) {
      return this.run(freqItemsets, .MODULE$.Map().empty(), evidence$1);
   }

   public RDD run(final RDD freqItemsets, final scala.collection.Map itemSupport, final ClassTag evidence$2) {
      RDD candidates = freqItemsets.flatMap((itemset) -> {
         Object items = itemset.items();
         return .MODULE$.wrapRefArray(scala.collection.ArrayOps..MODULE$.flatMap$extension(.MODULE$.genericArrayOps(items), (item) -> {
            Tuple2 var4 = scala.collection.ArrayOps..MODULE$.partition$extension(.MODULE$.genericArrayOps(items), (x$1) -> BoxesRunTime.boxToBoolean($anonfun$run$3(item, x$1)));
            if (var4 != null) {
               Object consequent = var4._1();
               Object antecedent = var4._2();
               if (!scala.collection.ArrayOps..MODULE$.isEmpty$extension(.MODULE$.genericArrayOps(antecedent))) {
                  return new Some(new Tuple2(org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(antecedent).toImmutableArraySeq(), new Tuple2(org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(consequent).toImmutableArraySeq(), BoxesRunTime.boxToLong(itemset.freq()))));
               }
            }

            return scala.None..MODULE$;
         }, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)));
      }, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
      ClassTag x$2 = scala.reflect.ClassTag..MODULE$.apply(ArraySeq.class);
      ClassTag x$3 = scala.reflect.ClassTag..MODULE$.apply(Tuple2.class);
      Null x$4 = org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions$default$4(candidates);
      return org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(candidates, x$2, x$3, (Ordering)null).join(freqItemsets.map((x) -> new Tuple2(org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(x.items()).toImmutableArraySeq(), BoxesRunTime.boxToLong(x.freq())), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class))).map((x0$1) -> {
         if (x0$1 != null) {
            ArraySeq antecedent = (ArraySeq)x0$1._1();
            Tuple2 var6 = (Tuple2)x0$1._2();
            if (var6 != null) {
               Tuple2 var7 = (Tuple2)var6._1();
               long freqAntecedent = var6._2$mcJ$sp();
               if (var7 != null) {
                  ArraySeq consequent = (ArraySeq)var7._1();
                  long freqUnion = var7._2$mcJ$sp();
                  return new Rule(antecedent.toArray(evidence$2), consequent.toArray(evidence$2), (double)freqUnion, (double)freqAntecedent, itemSupport.get(consequent.head()));
               }
            }
         }

         throw new MatchError(x0$1);
      }, scala.reflect.ClassTag..MODULE$.apply(Rule.class)).filter((x$2x) -> BoxesRunTime.boxToBoolean($anonfun$run$6(this, x$2x)));
   }

   public JavaRDD run(final JavaRDD freqItemsets) {
      ClassTag tag = org.apache.spark.api.java.JavaSparkContext..MODULE$.fakeClassTag();
      return org.apache.spark.api.java.JavaRDD..MODULE$.fromRDD(this.run(freqItemsets.rdd(), tag), scala.reflect.ClassTag..MODULE$.apply(Rule.class));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$run$3(final Object item$1, final Object x$1) {
      return BoxesRunTime.equals(x$1, item$1);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$run$6(final AssociationRules $this, final Rule x$2) {
      return x$2.confidence() >= $this.minConfidence();
   }

   public AssociationRules(final double minConfidence) {
      this.minConfidence = minConfidence;
      super();
      Logging.$init$(this);
   }

   public AssociationRules() {
      this(0.8);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class Rule implements Serializable {
      private final Object antecedent;
      private final Object consequent;
      private final double freqUnion;
      private final double freqAntecedent;
      private final Option freqConsequent;

      public Object antecedent() {
         return this.antecedent;
      }

      public Object consequent() {
         return this.consequent;
      }

      public double freqUnion() {
         return this.freqUnion;
      }

      public double confidence() {
         return this.freqUnion() / this.freqAntecedent;
      }

      public Option lift() {
         return this.freqConsequent.map((JFunction1.mcDD.sp)(fCons) -> this.confidence() / fCons);
      }

      public List javaAntecedent() {
         return scala.jdk.CollectionConverters..MODULE$.SeqHasAsJava(.MODULE$.genericWrapArray(this.antecedent()).toList()).asJava();
      }

      public List javaConsequent() {
         return scala.jdk.CollectionConverters..MODULE$.SeqHasAsJava(.MODULE$.genericWrapArray(this.consequent()).toList()).asJava();
      }

      public String toString() {
         String var10000 = .MODULE$.genericWrapArray(this.antecedent()).mkString("{", ",", "}");
         return var10000 + " => " + .MODULE$.genericWrapArray(this.consequent()).mkString("{", ",", "}") + ": (confidence: " + this.confidence() + "; lift: " + this.lift() + ")";
      }

      public Rule(final Object antecedent, final Object consequent, final double freqUnion, final double freqAntecedent, final Option freqConsequent) {
         this.antecedent = antecedent;
         this.consequent = consequent;
         this.freqUnion = freqUnion;
         this.freqAntecedent = freqAntecedent;
         this.freqConsequent = freqConsequent;
         .MODULE$.require(.MODULE$.genericWrapArray(antecedent).toSet().intersect(.MODULE$.genericWrapArray(consequent).toSet()).isEmpty(), () -> {
            Set sharedItems = (Set).MODULE$.genericWrapArray(this.antecedent()).toSet().intersect(.MODULE$.genericWrapArray(this.consequent()).toSet());
            return "A valid association rule must have disjoint antecedent and consequent but " + sharedItems + " is present in both.";
         });
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
