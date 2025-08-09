package org.apache.spark.ml.classification;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.mllib.evaluation.BinaryClassificationMetrics;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SQLImplicits;
import org.apache.spark.sql.SparkSession;
import scala.MatchError;
import scala.Product;
import scala.Some;
import scala.Tuple3;
import scala.collection.SeqOps;
import scala.reflect.ScalaSignature;
import scala.reflect.api.JavaUniverse;
import scala.reflect.api.Mirror;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;
import scala.reflect.runtime.package.;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005]4\u0001b\u0003\u0007\u0011\u0002\u0007\u0005\u0001C\u0006\u0005\u0006C\u0001!\ta\t\u0005\bO\u0001\u0011\r\u0011\"\u0003)\u0011\u0015y\u0003\u0001\"\u00011\u0011\u001da\u0004A1A\u0005\nuB\u0001B\u0013\u0001\t\u0006\u0004%\ta\u0013\u0005\tK\u0002A)\u0019!C\u0001M\"A1\u000e\u0001EC\u0002\u0013\u00051\n\u0003\u0005o\u0001!\u0015\r\u0011\"\u0001L\u0011!\t\b\u0001#b\u0001\n\u0003Y\u0005\u0002\u0003;\u0001\u0011\u000b\u0007I\u0011A&\u00037\tKg.\u0019:z\u00072\f7o]5gS\u000e\fG/[8o'VlW.\u0019:z\u0015\tia\"\u0001\bdY\u0006\u001c8/\u001b4jG\u0006$\u0018n\u001c8\u000b\u0005=\u0001\u0012AA7m\u0015\t\t\"#A\u0003ta\u0006\u00148N\u0003\u0002\u0014)\u00051\u0011\r]1dQ\u0016T\u0011!F\u0001\u0004_J<7c\u0001\u0001\u0018;A\u0011\u0001dG\u0007\u00023)\t!$A\u0003tG\u0006d\u0017-\u0003\u0002\u001d3\t1\u0011I\\=SK\u001a\u0004\"AH\u0010\u000e\u00031I!\u0001\t\u0007\u0003+\rc\u0017m]:jM&\u001c\u0017\r^5p]N+X.\\1ss\u00061A%\u001b8ji\u0012\u001a\u0001\u0001F\u0001%!\tAR%\u0003\u0002'3\t!QK\\5u\u00031\u0019\b/\u0019:l'\u0016\u001c8/[8o+\u0005I\u0003C\u0001\u0016.\u001b\u0005Y#B\u0001\u0017\u0011\u0003\r\u0019\u0018\u000f\\\u0005\u0003]-\u0012Ab\u00159be.\u001cVm]:j_:\f\u0001b]2pe\u0016\u001cu\u000e\\\u000b\u0002cA\u0011!'\u000f\b\u0003g]\u0002\"\u0001N\r\u000e\u0003UR!A\u000e\u0012\u0002\rq\u0012xn\u001c;?\u0013\tA\u0014$\u0001\u0004Qe\u0016$WMZ\u0005\u0003um\u0012aa\u0015;sS:<'B\u0001\u001d\u001a\u00035\u0011\u0017N\\1ss6+GO]5dgV\ta\b\u0005\u0002@\t6\t\u0001I\u0003\u0002B\u0005\u0006QQM^1mk\u0006$\u0018n\u001c8\u000b\u0005\r\u0003\u0012!B7mY&\u0014\u0017BA#A\u0005m\u0011\u0015N\\1ss\u000ec\u0017m]:jM&\u001c\u0017\r^5p]6+GO]5dg\"\u0012Aa\u0012\t\u00031!K!!S\r\u0003\u0013Q\u0014\u0018M\\:jK:$\u0018a\u0001:pGV\tA\n\u0005\u0002N1:\u0011aJ\u0016\b\u0003\u001fVs!\u0001\u0015+\u000f\u0005E\u001bfB\u0001\u001bS\u0013\u0005)\u0012BA\n\u0015\u0013\t\t\"#\u0003\u0002-!%\u0011qkK\u0001\ba\u0006\u001c7.Y4f\u0013\tI&LA\u0005ECR\fgI]1nK*\u0011qk\u000b\u0015\u0004\u000bq\u0013\u0007CA/a\u001b\u0005q&BA0\u0011\u0003)\tgN\\8uCRLwN\\\u0005\u0003Cz\u0013QaU5oG\u0016\f\u0013aY\u0001\u0006g9\nd\u0006\r\u0015\u0003\u000b\u001d\u000bA\"\u0019:fCVsG-\u001a:S\u001f\u000e+\u0012a\u001a\t\u00031!L!![\r\u0003\r\u0011{WO\u00197fQ\r1ALY\u0001\u0003aJD3a\u0002/cQ\t9q)A\ng\u001b\u0016\f7/\u001e:f\u0005f$\u0006N]3tQ>dG\rK\u0002\t9\nD#\u0001C$\u0002)A\u0014XmY5tS>t')\u001f+ie\u0016\u001c\bn\u001c7eQ\rIAL\u0019\u0015\u0003\u0013\u001d\u000b\u0011C]3dC2d')\u001f+ie\u0016\u001c\bn\u001c7eQ\rQAL\u0019\u0015\u0003\u0015\u001d\u0003"
)
public interface BinaryClassificationSummary extends ClassificationSummary {
   void org$apache$spark$ml$classification$BinaryClassificationSummary$_setter_$org$apache$spark$ml$classification$BinaryClassificationSummary$$sparkSession_$eq(final SparkSession x$1);

   void org$apache$spark$ml$classification$BinaryClassificationSummary$_setter_$org$apache$spark$ml$classification$BinaryClassificationSummary$$binaryMetrics_$eq(final BinaryClassificationMetrics x$1);

   SparkSession org$apache$spark$ml$classification$BinaryClassificationSummary$$sparkSession();

   // $FF: synthetic method
   static String scoreCol$(final BinaryClassificationSummary $this) {
      return $this.scoreCol();
   }

   default String scoreCol() {
      return null;
   }

   BinaryClassificationMetrics org$apache$spark$ml$classification$BinaryClassificationSummary$$binaryMetrics();

   // $FF: synthetic method
   static Dataset roc$(final BinaryClassificationSummary $this) {
      return $this.roc();
   }

   default Dataset roc() {
      SQLImplicits var10000 = this.org$apache$spark$ml$classification$BinaryClassificationSummary$$sparkSession().implicits();
      RDD var10001 = this.org$apache$spark$ml$classification$BinaryClassificationSummary$$binaryMetrics().roc();
      SQLImplicits var10002 = this.org$apache$spark$ml$classification$BinaryClassificationSummary$$sparkSession().implicits();
      JavaUniverse $u = .MODULE$.universe();
      JavaUniverse.JavaMirror $m = .MODULE$.universe().runtimeMirror(BinaryClassificationSummary.class.getClassLoader());
      return var10000.rddToDatasetHolder(var10001, var10002.newProductEncoder(((TypeTags)$u).TypeTag().apply((Mirror)$m, null.new $typecreator10$1()))).toDF(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"FPR", "TPR"})));
   }

   // $FF: synthetic method
   static double areaUnderROC$(final BinaryClassificationSummary $this) {
      return $this.areaUnderROC();
   }

   default double areaUnderROC() {
      return this.org$apache$spark$ml$classification$BinaryClassificationSummary$$binaryMetrics().areaUnderROC();
   }

   // $FF: synthetic method
   static Dataset pr$(final BinaryClassificationSummary $this) {
      return $this.pr();
   }

   default Dataset pr() {
      SQLImplicits var10000 = this.org$apache$spark$ml$classification$BinaryClassificationSummary$$sparkSession().implicits();
      RDD var10001 = this.org$apache$spark$ml$classification$BinaryClassificationSummary$$binaryMetrics().pr();
      SQLImplicits var10002 = this.org$apache$spark$ml$classification$BinaryClassificationSummary$$sparkSession().implicits();
      JavaUniverse $u = .MODULE$.universe();
      JavaUniverse.JavaMirror $m = .MODULE$.universe().runtimeMirror(BinaryClassificationSummary.class.getClassLoader());
      return var10000.rddToDatasetHolder(var10001, var10002.newProductEncoder(((TypeTags)$u).TypeTag().apply((Mirror)$m, null.new $typecreator10$2()))).toDF(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"recall", "precision"})));
   }

   // $FF: synthetic method
   static Dataset fMeasureByThreshold$(final BinaryClassificationSummary $this) {
      return $this.fMeasureByThreshold();
   }

   default Dataset fMeasureByThreshold() {
      SQLImplicits var10000 = this.org$apache$spark$ml$classification$BinaryClassificationSummary$$sparkSession().implicits();
      RDD var10001 = this.org$apache$spark$ml$classification$BinaryClassificationSummary$$binaryMetrics().fMeasureByThreshold();
      SQLImplicits var10002 = this.org$apache$spark$ml$classification$BinaryClassificationSummary$$sparkSession().implicits();
      JavaUniverse $u = .MODULE$.universe();
      JavaUniverse.JavaMirror $m = .MODULE$.universe().runtimeMirror(BinaryClassificationSummary.class.getClassLoader());
      return var10000.rddToDatasetHolder(var10001, var10002.newProductEncoder(((TypeTags)$u).TypeTag().apply((Mirror)$m, null.new $typecreator10$3()))).toDF(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"threshold", "F-Measure"})));
   }

   // $FF: synthetic method
   static Dataset precisionByThreshold$(final BinaryClassificationSummary $this) {
      return $this.precisionByThreshold();
   }

   default Dataset precisionByThreshold() {
      SQLImplicits var10000 = this.org$apache$spark$ml$classification$BinaryClassificationSummary$$sparkSession().implicits();
      RDD var10001 = this.org$apache$spark$ml$classification$BinaryClassificationSummary$$binaryMetrics().precisionByThreshold();
      SQLImplicits var10002 = this.org$apache$spark$ml$classification$BinaryClassificationSummary$$sparkSession().implicits();
      JavaUniverse $u = .MODULE$.universe();
      JavaUniverse.JavaMirror $m = .MODULE$.universe().runtimeMirror(BinaryClassificationSummary.class.getClassLoader());
      return var10000.rddToDatasetHolder(var10001, var10002.newProductEncoder(((TypeTags)$u).TypeTag().apply((Mirror)$m, null.new $typecreator10$4()))).toDF(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"threshold", "precision"})));
   }

   // $FF: synthetic method
   static Dataset recallByThreshold$(final BinaryClassificationSummary $this) {
      return $this.recallByThreshold();
   }

   default Dataset recallByThreshold() {
      SQLImplicits var10000 = this.org$apache$spark$ml$classification$BinaryClassificationSummary$$sparkSession().implicits();
      RDD var10001 = this.org$apache$spark$ml$classification$BinaryClassificationSummary$$binaryMetrics().recallByThreshold();
      SQLImplicits var10002 = this.org$apache$spark$ml$classification$BinaryClassificationSummary$$sparkSession().implicits();
      JavaUniverse $u = .MODULE$.universe();
      JavaUniverse.JavaMirror $m = .MODULE$.universe().runtimeMirror(BinaryClassificationSummary.class.getClassLoader());
      return var10000.rddToDatasetHolder(var10001, var10002.newProductEncoder(((TypeTags)$u).TypeTag().apply((Mirror)$m, null.new $typecreator10$5()))).toDF(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"threshold", "recall"})));
   }

   static void $init$(final BinaryClassificationSummary $this) {
      $this.org$apache$spark$ml$classification$BinaryClassificationSummary$_setter_$org$apache$spark$ml$classification$BinaryClassificationSummary$$sparkSession_$eq($this.predictions().sparkSession());
      Column weightColumn = scala.collection.ArrayOps..MODULE$.contains$extension(scala.Predef..MODULE$.refArrayOps((Object[])$this.predictions().schema().fieldNames()), $this.weightCol()) ? org.apache.spark.sql.functions..MODULE$.col($this.weightCol()).cast(org.apache.spark.sql.types.DoubleType..MODULE$) : org.apache.spark.sql.functions..MODULE$.lit(BoxesRunTime.boxToDouble((double)1.0F));
      $this.org$apache$spark$ml$classification$BinaryClassificationSummary$_setter_$org$apache$spark$ml$classification$BinaryClassificationSummary$$binaryMetrics_$eq(new BinaryClassificationMetrics($this.predictions().select(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{org.apache.spark.sql.functions..MODULE$.col($this.scoreCol()), org.apache.spark.sql.functions..MODULE$.col($this.labelCol()).cast(org.apache.spark.sql.types.DoubleType..MODULE$), weightColumn}))).rdd().map((x0$1) -> {
         if (x0$1 != null) {
            Some var3 = org.apache.spark.sql.Row..MODULE$.unapplySeq(x0$1);
            if (!var3.isEmpty() && var3.get() != null && ((SeqOps)var3.get()).lengthCompare(3) == 0) {
               Object score = ((SeqOps)var3.get()).apply(0);
               Object label = ((SeqOps)var3.get()).apply(1);
               Object weight = ((SeqOps)var3.get()).apply(2);
               if (score instanceof Vector) {
                  Vector var7 = (Vector)score;
                  if (label instanceof Double) {
                     double var8 = BoxesRunTime.unboxToDouble(label);
                     if (weight instanceof Double) {
                        double var10 = BoxesRunTime.unboxToDouble(weight);
                        return new Tuple3(BoxesRunTime.boxToDouble(var7.apply(1)), BoxesRunTime.boxToDouble(var8), BoxesRunTime.boxToDouble(var10));
                     }
                  }
               }
            }
         }

         throw new MatchError(x0$1);
      }, scala.reflect.ClassTag..MODULE$.apply(Product.class)), 1000));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }

   public final class $typecreator10$1 extends TypeCreator {
      public Types.TypeApi apply(final Mirror $m$untyped) {
         Universe $u = $m$untyped.universe();
         return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala").asModule().moduleClass()), $m$untyped.staticClass("scala.Tuple2"), new scala.collection.immutable..colon.colon($m$untyped.staticClass("scala.Double").asType().toTypeConstructor(), new scala.collection.immutable..colon.colon($m$untyped.staticClass("scala.Double").asType().toTypeConstructor(), scala.collection.immutable.Nil..MODULE$)));
      }
   }

   public final class $typecreator10$2 extends TypeCreator {
      public Types.TypeApi apply(final Mirror $m$untyped) {
         Universe $u = $m$untyped.universe();
         return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala").asModule().moduleClass()), $m$untyped.staticClass("scala.Tuple2"), new scala.collection.immutable..colon.colon($m$untyped.staticClass("scala.Double").asType().toTypeConstructor(), new scala.collection.immutable..colon.colon($m$untyped.staticClass("scala.Double").asType().toTypeConstructor(), scala.collection.immutable.Nil..MODULE$)));
      }
   }

   public final class $typecreator10$3 extends TypeCreator {
      public Types.TypeApi apply(final Mirror $m$untyped) {
         Universe $u = $m$untyped.universe();
         return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala").asModule().moduleClass()), $m$untyped.staticClass("scala.Tuple2"), new scala.collection.immutable..colon.colon($m$untyped.staticClass("scala.Double").asType().toTypeConstructor(), new scala.collection.immutable..colon.colon($m$untyped.staticClass("scala.Double").asType().toTypeConstructor(), scala.collection.immutable.Nil..MODULE$)));
      }
   }

   public final class $typecreator10$4 extends TypeCreator {
      public Types.TypeApi apply(final Mirror $m$untyped) {
         Universe $u = $m$untyped.universe();
         return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala").asModule().moduleClass()), $m$untyped.staticClass("scala.Tuple2"), new scala.collection.immutable..colon.colon($m$untyped.staticClass("scala.Double").asType().toTypeConstructor(), new scala.collection.immutable..colon.colon($m$untyped.staticClass("scala.Double").asType().toTypeConstructor(), scala.collection.immutable.Nil..MODULE$)));
      }
   }

   public final class $typecreator10$5 extends TypeCreator {
      public Types.TypeApi apply(final Mirror $m$untyped) {
         Universe $u = $m$untyped.universe();
         return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala").asModule().moduleClass()), $m$untyped.staticClass("scala.Tuple2"), new scala.collection.immutable..colon.colon($m$untyped.staticClass("scala.Double").asType().toTypeConstructor(), new scala.collection.immutable..colon.colon($m$untyped.staticClass("scala.Double").asType().toTypeConstructor(), scala.collection.immutable.Nil..MODULE$)));
      }
   }
}
