package org.apache.spark.mllib.regression;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Arrays;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaDoubleRDD;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors$;
import org.apache.spark.mllib.util.Loader$;
import org.apache.spark.mllib.util.Saveable;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.StructType;
import org.json4s.JValue;
import scala.MatchError;
import scala.Tuple1;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.StrictOptimizedIterableOps;
import scala.collection.immutable.ArraySeq;
import scala.collection.immutable.List;
import scala.math.Ordering;
import scala.reflect.ScalaSignature;
import scala.reflect.api.JavaUniverse;
import scala.reflect.api.Mirror;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\tEg\u0001B\u001c9\u0001\rC\u0001\u0002\u0017\u0001\u0003\u0006\u0004%\t!\u0017\u0005\tS\u0002\u0011\t\u0011)A\u00055\"A1\u000e\u0001BC\u0002\u0013\u0005\u0011\f\u0003\u0005n\u0001\t\u0005\t\u0015!\u0003[\u0011!y\u0007A!b\u0001\n\u0003\u0001\b\u0002C;\u0001\u0005\u0003\u0005\u000b\u0011B9\t\u000b]\u0004A\u0011\u0001=\t\u0013\u0005\u0015\u0001A1A\u0005\n\u0005\u001d\u0001\u0002CA\u000b\u0001\u0001\u0006I!!\u0003\t\r]\u0004A\u0011AA\f\u0011\u001d\t)\u0004\u0001C\u0005\u0003oAq!a\u0018\u0001\t\u0003\t\t\u0007C\u0004\u0002`\u0001!\t!!\u001e\t\u000f\u0005}\u0003\u0001\"\u0001\u0002\n\"A\u0011q\u0012\u0001\u0005\u0002i\n\t\n\u0003\u0005\u0002 \u0002!\tAOAI\u0011\u001d\t\t\u000b\u0001C!\u0003G;q!!39\u0011\u0003\tYM\u0002\u00048q!\u0005\u0011Q\u001a\u0005\u0007oN!\t!!6\b\u000f\u0005]7\u0003#\u0003\u0002Z\u001a9\u0011Q\\\n\t\n\u0005}\u0007BB<\u0017\t\u0003\t\t\u000fC\u0004\u0002dZ!\t!!:\t\u000f\u0005\u001dh\u0003\"\u0001\u0002f\u001a1\u0011\u0011\u001e\fA\u0003WD!\"a>\u001b\u0005+\u0007I\u0011AA}\u0011%\tYP\u0007B\tB\u0003%Q\f\u0003\u0006\u0002~j\u0011)\u001a!C\u0001\u0003sD\u0011\"a@\u001b\u0005#\u0005\u000b\u0011B/\t\r]TB\u0011\u0001B\u0001\u0011%\u0011YAGA\u0001\n\u0003\u0011i\u0001C\u0005\u0003\u0014i\t\n\u0011\"\u0001\u0003\u0016!I!\u0011\u0006\u000e\u0012\u0002\u0013\u0005!Q\u0003\u0005\n\u0005WQ\u0012\u0011!C!\u0005[A\u0011Ba\r\u001b\u0003\u0003%\tA!\u000e\t\u0013\tu\"$!A\u0005\u0002\t}\u0002\"\u0003B&5\u0005\u0005I\u0011\tB'\u0011%\u0011YFGA\u0001\n\u0003\u0011i\u0006C\u0005\u0003bi\t\t\u0011\"\u0011\u0003d!I!q\r\u000e\u0002\u0002\u0013\u0005#\u0011\u000e\u0005\n\u0005WR\u0012\u0011!C!\u0005[B\u0011Ba\u001c\u001b\u0003\u0003%\tE!\u001d\b\u0013\tUd#!A\t\u0002\t]d!CAu-\u0005\u0005\t\u0012\u0001B=\u0011\u00199X\u0006\"\u0001\u0003\b\"I!1N\u0017\u0002\u0002\u0013\u0015#Q\u000e\u0005\n\u0005\u0013k\u0013\u0011!CA\u0005\u0017C\u0011B!%.\u0003\u0003%\tIa%\t\u0013\t\u0015V&!A\u0005\n\t\u001d\u0006bBAQ-\u0011\u0005!q\u0016\u0005\b\u0005w3B\u0011\u0001B_\u0011\u001d\u0011Yl\u0005C!\u0005\u000bD\u0011B!*\u0014\u0003\u0003%IAa*\u0003/%\u001bx\u000e^8oS\u000e\u0014Vm\u001a:fgNLwN\\'pI\u0016d'BA\u001d;\u0003)\u0011Xm\u001a:fgNLwN\u001c\u0006\u0003wq\nQ!\u001c7mS\nT!!\u0010 \u0002\u000bM\u0004\u0018M]6\u000b\u0005}\u0002\u0015AB1qC\u000eDWMC\u0001B\u0003\ry'oZ\u0002\u0001'\u0011\u0001AI\u0013*\u0011\u0005\u0015CU\"\u0001$\u000b\u0003\u001d\u000bQa]2bY\u0006L!!\u0013$\u0003\r\u0005s\u0017PU3g!\tY\u0005+D\u0001M\u0015\tie*\u0001\u0002j_*\tq*\u0001\u0003kCZ\f\u0017BA)M\u00051\u0019VM]5bY&T\u0018M\u00197f!\t\u0019f+D\u0001U\u0015\t)&(\u0001\u0003vi&d\u0017BA,U\u0005!\u0019\u0016M^3bE2,\u0017A\u00032pk:$\u0017M]5fgV\t!\fE\u0002F7vK!\u0001\u0018$\u0003\u000b\u0005\u0013(/Y=\u0011\u0005\u0015s\u0016BA0G\u0005\u0019!u.\u001e2mK\"\u001a\u0011!Y4\u0011\u0005\t,W\"A2\u000b\u0005\u0011d\u0014AC1o]>$\u0018\r^5p]&\u0011am\u0019\u0002\u0006'&t7-Z\u0011\u0002Q\u0006)\u0011GL\u001a/a\u0005Y!m\\;oI\u0006\u0014\u0018.Z:!Q\r\u0011\u0011mZ\u0001\faJ,G-[2uS>t7\u000fK\u0002\u0004C\u001e\fA\u0002\u001d:fI&\u001cG/[8og\u0002B3\u0001B1h\u0003!I7o\u001c;p]&\u001cW#A9\u0011\u0005\u0015\u0013\u0018BA:G\u0005\u001d\u0011un\u001c7fC:D3!B1h\u0003%I7o\u001c;p]&\u001c\u0007\u0005K\u0002\u0007C\u001e\fa\u0001P5oSRtD\u0003B=|{~\u0004\"A\u001f\u0001\u000e\u0003aBQ\u0001W\u0004A\u0002iC3a_1h\u0011\u0015Yw\u00011\u0001[Q\ri\u0018m\u001a\u0005\u0006_\u001e\u0001\r!\u001d\u0015\u0004\u007f\u0006<\u0007fA\u0004bO\u0006i\u0001O]3eS\u000e$\u0018n\u001c8Pe\u0012,\"!!\u0003\u0011\u000b\u0005-\u0011\u0011C/\u000e\u0005\u00055!bAA\b\r\u0006!Q.\u0019;i\u0013\u0011\t\u0019\"!\u0004\u0003\u0011=\u0013H-\u001a:j]\u001e\fa\u0002\u001d:fI&\u001cG/[8o\u001fJ$\u0007\u0005F\u0004z\u00033\t9#!\u000b\t\raS\u0001\u0019AA\u000e!\u0015\ti\"a\t^\u001b\t\tyBC\u0002\u0002\"9\u000bA\u0001\\1oO&!\u0011QEA\u0010\u0005!IE/\u001a:bE2,\u0007BB6\u000b\u0001\u0004\tY\u0002\u0003\u0004p\u0015\u0001\u0007\u00111\u0006\t\u0005\u0003;\ti#C\u0002t\u0003?ACAC1\u00022\u0005\u0012\u00111G\u0001\u0006c9\"d\u0006M\u0001\u000eCN\u001cXM\u001d;Pe\u0012,'/\u001a3\u0015\t\u0005e\u00121\f\u000b\u0005\u0003w\t\t\u0005E\u0002F\u0003{I1!a\u0010G\u0005\u0011)f.\u001b;\t\u000f\u0005\r3\u0002q\u0001\u0002F\u0005\u0019qN\u001d3\u0011\u000b\u0005\u001d\u0013qK/\u000f\t\u0005%\u00131\u000b\b\u0005\u0003\u0017\n\t&\u0004\u0002\u0002N)\u0019\u0011q\n\"\u0002\rq\u0012xn\u001c;?\u0013\u00059\u0015bAA+\r\u00069\u0001/Y2lC\u001e,\u0017\u0002BA\n\u00033R1!!\u0016G\u0011\u0019\tif\u0003a\u00015\u0006\u0011\u0001p]\u0001\baJ,G-[2u)\u0011\t\u0019'a\u001c\u0011\u000b\u0005\u0015\u00141N/\u000e\u0005\u0005\u001d$bAA5y\u0005\u0019!\u000f\u001a3\n\t\u00055\u0014q\r\u0002\u0004%\u0012#\u0005bBA9\u0019\u0001\u0007\u00111M\u0001\ti\u0016\u001cH\u000fR1uC\"\u001aA\"Y4\u0015\t\u0005]\u0014Q\u0011\t\u0005\u0003s\n\t)\u0004\u0002\u0002|)\u0019q*! \u000b\u0007\u0005}D(A\u0002ba&LA!a!\u0002|\ti!*\u0019<b\t>,(\r\\3S\t\u0012Cq!!\u001d\u000e\u0001\u0004\t9\bK\u0002\u000eC\u001e$2!XAF\u0011\u0019\t\tH\u0004a\u0001;\"\u001aa\"Y4\u0002\u001d\t|WO\u001c3bef4Vm\u0019;peV\u0011\u00111\u0013\t\u0005\u0003+\u000bY*\u0004\u0002\u0002\u0018*\u0019\u0011\u0011\u0014\u001e\u0002\r1Lg.\u00197h\u0013\u0011\ti*a&\u0003\rY+7\r^8s\u0003A\u0001(/\u001a3jGRLwN\u001c,fGR|'/\u0001\u0003tCZ,GCBA\u001e\u0003K\u000b\t\fC\u0004\u0002(F\u0001\r!!+\u0002\u0005M\u001c\u0007\u0003BAV\u0003[k\u0011\u0001P\u0005\u0004\u0003_c$\u0001D*qCJ\\7i\u001c8uKb$\bbBAZ#\u0001\u0007\u0011QW\u0001\u0005a\u0006$\b\u000e\u0005\u0003\u00028\u0006}f\u0002BA]\u0003w\u00032!a\u0013G\u0013\r\tiLR\u0001\u0007!J,G-\u001a4\n\t\u0005\u0005\u00171\u0019\u0002\u0007'R\u0014\u0018N\\4\u000b\u0007\u0005uf\t\u000b\u0003\u0012C\u0006E\u0002f\u0001\u0001bO\u00069\u0012j]8u_:L7MU3he\u0016\u001c8/[8o\u001b>$W\r\u001c\t\u0003uN\u0019Ra\u0005#\u0002P*\u0003BaUAis&\u0019\u00111\u001b+\u0003\r1{\u0017\rZ3s)\t\tY-\u0001\u0007TCZ,Gj\\1e-Fz\u0006\u0007E\u0002\u0002\\Zi\u0011a\u0005\u0002\r'\u00064X\rT8bIZ\u000bt\fM\n\u0003-\u0011#\"!!7\u0002#QD\u0017n\u001d$pe6\fGOV3sg&|g.\u0006\u0002\u00026\u0006iA\u000f[5t\u00072\f7o\u001d(b[\u0016\u0014A\u0001R1uCN1!\u0004RAw\u0003g\u00042!RAx\u0013\r\t\tP\u0012\u0002\b!J|G-^2u!\u0011\t9%!>\n\u0007E\u000bI&\u0001\u0005c_VtG-\u0019:z+\u0005i\u0016!\u00032pk:$\u0017M]=!\u0003)\u0001(/\u001a3jGRLwN\\\u0001\faJ,G-[2uS>t\u0007\u0005\u0006\u0004\u0003\u0004\t\u001d!\u0011\u0002\t\u0004\u0005\u000bQR\"\u0001\f\t\r\u0005]x\u00041\u0001^\u0011\u0019\tip\ba\u0001;\u0006!1m\u001c9z)\u0019\u0011\u0019Aa\u0004\u0003\u0012!A\u0011q\u001f\u0011\u0011\u0002\u0003\u0007Q\f\u0003\u0005\u0002~\u0002\u0002\n\u00111\u0001^\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\"Aa\u0006+\u0007u\u0013Ib\u000b\u0002\u0003\u001cA!!Q\u0004B\u0013\u001b\t\u0011yB\u0003\u0003\u0003\"\t\r\u0012!C;oG\",7m[3e\u0015\t!g)\u0003\u0003\u0003(\t}!!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\u0012\u0014!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070\u0006\u0002\u00030A!\u0011Q\u0004B\u0019\u0013\u0011\t\t-a\b\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0016\u0005\t]\u0002cA#\u0003:%\u0019!1\b$\u0003\u0007%sG/\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\t\t\u0005#q\t\t\u0004\u000b\n\r\u0013b\u0001B#\r\n\u0019\u0011I\\=\t\u0013\t%S%!AA\u0002\t]\u0012a\u0001=%c\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/\u0006\u0002\u0003PA1!\u0011\u000bB,\u0005\u0003j!Aa\u0015\u000b\u0007\tUc)\u0001\u0006d_2dWm\u0019;j_:LAA!\u0017\u0003T\tA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\r\t(q\f\u0005\n\u0005\u0013:\u0013\u0011!a\u0001\u0005\u0003\n!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR!!q\u0006B3\u0011%\u0011I\u0005KA\u0001\u0002\u0004\u00119$\u0001\u0005iCND7i\u001c3f)\t\u00119$\u0001\u0005u_N#(/\u001b8h)\t\u0011y#\u0001\u0004fcV\fGn\u001d\u000b\u0004c\nM\u0004\"\u0003B%W\u0005\u0005\t\u0019\u0001B!\u0003\u0011!\u0015\r^1\u0011\u0007\t\u0015Qf\u0005\u0003.\u0005wR\u0005\u0003\u0003B?\u0005\u0007kVLa\u0001\u000e\u0005\t}$b\u0001BA\r\u00069!/\u001e8uS6,\u0017\u0002\u0002BC\u0005\u007f\u0012\u0011#\u00112tiJ\f7\r\u001e$v]\u000e$\u0018n\u001c83)\t\u00119(A\u0003baBd\u0017\u0010\u0006\u0004\u0003\u0004\t5%q\u0012\u0005\u0007\u0003o\u0004\u0004\u0019A/\t\r\u0005u\b\u00071\u0001^\u0003\u001d)h.\u00199qYf$BA!&\u0003\"B)QIa&\u0003\u001c&\u0019!\u0011\u0014$\u0003\r=\u0003H/[8o!\u0015)%QT/^\u0013\r\u0011yJ\u0012\u0002\u0007)V\u0004H.\u001a\u001a\t\u0013\t\r\u0016'!AA\u0002\t\r\u0011a\u0001=%a\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011!\u0011\u0016\t\u0005\u0003;\u0011Y+\u0003\u0003\u0003.\u0006}!AB(cU\u0016\u001cG\u000f\u0006\u0007\u0002<\tE&1\u0017B[\u0005o\u0013I\fC\u0004\u0002(N\u0002\r!!+\t\u000f\u0005M6\u00071\u0001\u00026\")\u0001l\ra\u00015\")1n\ra\u00015\")qn\ra\u0001c\u0006!An\\1e)\u0019\u0011yL!1\u0003DB)QI!([5\"9\u0011q\u0015\u001bA\u0002\u0005%\u0006bBAZi\u0001\u0007\u0011Q\u0017\u000b\u0006s\n\u001d'\u0011\u001a\u0005\b\u0003O+\u0004\u0019AAU\u0011\u001d\t\u0019,\u000ea\u0001\u0003kCC!N1\u00022!\"1#YA\u0019Q\u0011\u0011\u0012-!\r"
)
public class IsotonicRegressionModel implements Serializable, Saveable {
   private final double[] boundaries;
   private final double[] predictions;
   private final boolean isotonic;
   private final Ordering predictionOrd;

   public static IsotonicRegressionModel load(final SparkContext sc, final String path) {
      return IsotonicRegressionModel$.MODULE$.load(sc, path);
   }

   public double[] boundaries() {
      return this.boundaries;
   }

   public double[] predictions() {
      return this.predictions;
   }

   public boolean isotonic() {
      return this.isotonic;
   }

   private Ordering predictionOrd() {
      return this.predictionOrd;
   }

   private void assertOrdered(final double[] xs, final Ordering ord) {
      IntRef i = IntRef.create(1);

      for(int len = xs.length; i.elem < len; ++i.elem) {
         .MODULE$.require(ord.compare(BoxesRunTime.boxToDouble(xs[i.elem - 1]), BoxesRunTime.boxToDouble(xs[i.elem])) <= 0, () -> {
            double var10000 = xs[i.elem - 1];
            return "Elements (" + var10000 + ", " + xs[i.elem] + ") are not ordered.";
         });
      }

   }

   public RDD predict(final RDD testData) {
      return testData.map((JFunction1.mcDD.sp)(testDatax) -> this.predict(testDatax), scala.reflect.ClassTag..MODULE$.Double());
   }

   public JavaDoubleRDD predict(final JavaDoubleRDD testData) {
      return org.apache.spark.api.java.JavaDoubleRDD..MODULE$.fromRDD(this.predict(testData.rdd().retag(scala.reflect.ClassTag..MODULE$.apply(Double.class))));
   }

   public double predict(final double testData) {
      int foundIndex = Arrays.binarySearch(this.boundaries(), testData);
      int insertIndex = -foundIndex - 1;
      if (insertIndex == 0) {
         return BoxesRunTime.unboxToDouble(scala.collection.ArrayOps..MODULE$.head$extension(.MODULE$.doubleArrayOps(this.predictions())));
      } else if (insertIndex == this.boundaries().length) {
         return BoxesRunTime.unboxToDouble(scala.collection.ArrayOps..MODULE$.last$extension(.MODULE$.doubleArrayOps(this.predictions())));
      } else {
         return foundIndex < 0 ? linearInterpolation$1(this.boundaries()[insertIndex - 1], this.predictions()[insertIndex - 1], this.boundaries()[insertIndex], this.predictions()[insertIndex], testData) : this.predictions()[foundIndex];
      }
   }

   public Vector boundaryVector() {
      return Vectors$.MODULE$.dense(this.boundaries());
   }

   public Vector predictionVector() {
      return Vectors$.MODULE$.dense(this.predictions());
   }

   public void save(final SparkContext sc, final String path) {
      IsotonicRegressionModel.SaveLoadV1_0$.MODULE$.save(sc, path, this.boundaries(), this.predictions(), this.isotonic());
   }

   private static final double linearInterpolation$1(final double x1, final double y1, final double x2, final double y2, final double x) {
      return y1 + (y2 - y1) * (x - x1) / (x2 - x1);
   }

   public IsotonicRegressionModel(final double[] boundaries, final double[] predictions, final boolean isotonic) {
      this.boundaries = boundaries;
      this.predictions = predictions;
      this.isotonic = isotonic;
      this.predictionOrd = isotonic ? scala.package..MODULE$.Ordering().apply(scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$) : scala.package..MODULE$.Ordering().apply(scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$).reverse();
      .MODULE$.require(boundaries.length == predictions.length);
      this.assertOrdered(boundaries, scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$);
      this.assertOrdered(predictions, this.predictionOrd());
   }

   public IsotonicRegressionModel(final Iterable boundaries, final Iterable predictions, final Boolean isotonic) {
      this((double[])scala.jdk.CollectionConverters..MODULE$.IterableHasAsScala(boundaries).asScala().toArray(scala.reflect.ClassTag..MODULE$.Double()), (double[])scala.jdk.CollectionConverters..MODULE$.IterableHasAsScala(predictions).asScala().toArray(scala.reflect.ClassTag..MODULE$.Double()), .MODULE$.Boolean2boolean(isotonic));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   private static class SaveLoadV1_0$ {
      public static final SaveLoadV1_0$ MODULE$ = new SaveLoadV1_0$();

      public String thisFormatVersion() {
         return "1.0";
      }

      public String thisClassName() {
         return "org.apache.spark.mllib.regression.IsotonicRegressionModel";
      }

      public void save(final SparkContext sc, final String path, final double[] boundaries, final double[] predictions, final boolean isotonic) {
         SparkSession spark = org.apache.spark.sql.SparkSession..MODULE$.builder().sparkContext(sc).getOrCreate();
         String metadata = org.json4s.jackson.JsonMethods..MODULE$.compact(org.json4s.jackson.JsonMethods..MODULE$.render(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.pair2Assoc(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("class"), this.thisClassName()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("version"), this.thisFormatVersion()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("isotonic"), BoxesRunTime.boxToBoolean(isotonic)), (x) -> $anonfun$save$4(BoxesRunTime.unboxToBoolean(x)))), org.json4s.jackson.JsonMethods..MODULE$.render$default$2(), org.json4s.jackson.JsonMethods..MODULE$.render$default$3()));
         scala.collection.immutable..colon.colon var10001 = new scala.collection.immutable..colon.colon(new Tuple1(metadata), scala.collection.immutable.Nil..MODULE$);
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(this.getClass().getClassLoader());

         final class $typecreator1$1 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala").asModule().moduleClass()), $m$untyped.staticClass("scala.Tuple1"), new scala.collection.immutable..colon.colon($u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().SingleType($m$untyped.staticPackage("scala").asModule().moduleClass().asType().toTypeConstructor(), $m$untyped.staticModule("scala.Predef")), $u.internal().reificationSupport().selectType($m$untyped.staticModule("scala.Predef").asModule().moduleClass(), "String"), scala.collection.immutable.Nil..MODULE$), scala.collection.immutable.Nil..MODULE$));
            }

            public $typecreator1$1() {
            }
         }

         spark.createDataFrame(var10001, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$1())).write().text(Loader$.MODULE$.metadataPath(path));
         ArraySeq var12 = org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(boundaries).toImmutableArraySeq().zip(.MODULE$.wrapDoubleArray(predictions)).map((x0$1) -> {
            if (x0$1 != null) {
               double b = x0$1._1$mcD$sp();
               double p = x0$1._2$mcD$sp();
               return new IsotonicRegressionModel$SaveLoadV1_0$Data(b, p);
            } else {
               throw new MatchError(x0$1);
            }
         });
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(this.getClass().getClassLoader());

         final class $typecreator2$1 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $m$untyped.staticClass("org.apache.spark.mllib.regression.IsotonicRegressionModel.SaveLoadV1_0.Data").asType().toTypeConstructor();
            }

            public $typecreator2$1() {
            }
         }

         spark.createDataFrame(var12, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator2$1())).write().parquet(Loader$.MODULE$.dataPath(path));
      }

      public Tuple2 load(final SparkContext sc, final String path) {
         SparkSession spark = org.apache.spark.sql.SparkSession..MODULE$.builder().sparkContext(sc).getOrCreate();
         Dataset dataRDD = spark.read().parquet(Loader$.MODULE$.dataPath(path));
         Loader$ var10000 = Loader$.MODULE$;
         StructType var10001 = dataRDD.schema();
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(this.getClass().getClassLoader());

         final class $typecreator1$2 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $m$untyped.staticClass("org.apache.spark.mllib.regression.IsotonicRegressionModel.SaveLoadV1_0.Data").asType().toTypeConstructor();
            }

            public $typecreator1$2() {
            }
         }

         var10000.checkSchema(var10001, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$2()));
         Row[] dataArray = (Row[])dataRDD.select("boundary", scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"prediction"}))).collect();
         Tuple2 var10 = ((StrictOptimizedIterableOps).MODULE$.wrapRefArray(scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.refArrayOps((Object[])dataArray), (x) -> new Tuple2.mcDD.sp(x.getDouble(0), x.getDouble(1)), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class))).toList().sortBy((x$1) -> BoxesRunTime.boxToDouble($anonfun$load$2(x$1)), scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$)).unzip(.MODULE$.$conforms());
         if (var10 != null) {
            List boundaries = (List)var10._1();
            List predictions = (List)var10._2();
            Tuple2 var9 = new Tuple2(boundaries, predictions);
            List boundaries = (List)var9._1();
            List predictions = (List)var9._2();
            return new Tuple2(boundaries.toArray(scala.reflect.ClassTag..MODULE$.Double()), predictions.toArray(scala.reflect.ClassTag..MODULE$.Double()));
         } else {
            throw new MatchError(var10);
         }
      }

      // $FF: synthetic method
      public static final JValue $anonfun$save$4(final boolean x) {
         return org.json4s.JsonDSL..MODULE$.boolean2jvalue(x);
      }

      // $FF: synthetic method
      public static final double $anonfun$load$2(final Tuple2 x$1) {
         return x$1._1$mcD$sp();
      }

      public SaveLoadV1_0$() {
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
