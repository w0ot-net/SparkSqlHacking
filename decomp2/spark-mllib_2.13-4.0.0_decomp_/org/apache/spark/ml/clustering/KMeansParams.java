package org.apache.spark.ml.clustering;

import org.apache.spark.ml.param.IntParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamPair;
import org.apache.spark.ml.param.ParamValidators$;
import org.apache.spark.ml.param.shared.HasDistanceMeasure;
import org.apache.spark.ml.param.shared.HasFeaturesCol;
import org.apache.spark.ml.param.shared.HasMaxBlockSizeInMB;
import org.apache.spark.ml.param.shared.HasMaxIter;
import org.apache.spark.ml.param.shared.HasPredictionCol;
import org.apache.spark.ml.param.shared.HasSeed;
import org.apache.spark.ml.param.shared.HasSolver;
import org.apache.spark.ml.param.shared.HasTol;
import org.apache.spark.ml.param.shared.HasWeightCol;
import org.apache.spark.ml.util.SchemaUtils$;
import org.apache.spark.sql.types.StructType;
import org.apache.spark.sql.types.IntegerType.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015a\u0001\u0003\u0006\f!\u0003\r\taC\u000b\t\u000b\u0001\u0003A\u0011\u0001\"\t\u000f\u0019\u0003!\u0019!C\u0003\u000f\")A\u000b\u0001C\u0001+\"9!\f\u0001b\u0001\n\u000bY\u0006\"B6\u0001\t\u0003a\u0007b\u00028\u0001\u0005\u0004%)a\u0012\u0005\u0006a\u0002!\t!\u0016\u0005\be\u0002\u0011\r\u0011\"\u0012\\\u0011\u00151\b\u0001\"\u0005x\u00051YU*Z1ogB\u000b'/Y7t\u0015\taQ\"\u0001\u0006dYV\u001cH/\u001a:j]\u001eT!AD\b\u0002\u00055d'B\u0001\t\u0012\u0003\u0015\u0019\b/\u0019:l\u0015\t\u00112#\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002)\u0005\u0019qN]4\u0014\u0019\u00011BD\t\u0015,]E\"tGO\u001f\u0011\u0005]QR\"\u0001\r\u000b\u0003e\tQa]2bY\u0006L!a\u0007\r\u0003\r\u0005s\u0017PU3g!\ti\u0002%D\u0001\u001f\u0015\tyR\"A\u0003qCJ\fW.\u0003\u0002\"=\t1\u0001+\u0019:b[N\u0004\"a\t\u0014\u000e\u0003\u0011R!!\n\u0010\u0002\rMD\u0017M]3e\u0013\t9CE\u0001\u0006ICNl\u0015\r_%uKJ\u0004\"aI\u0015\n\u0005)\"#A\u0004%bg\u001a+\u0017\r^;sKN\u001cu\u000e\u001c\t\u0003G1J!!\f\u0013\u0003\u000f!\u000b7oU3fIB\u00111eL\u0005\u0003a\u0011\u0012\u0001\u0003S1t!J,G-[2uS>t7i\u001c7\u0011\u0005\r\u0012\u0014BA\u001a%\u0005\u0019A\u0015m\u001d+pYB\u00111%N\u0005\u0003m\u0011\u0012!\u0003S1t\t&\u001cH/\u00198dK6+\u0017m];sKB\u00111\u0005O\u0005\u0003s\u0011\u0012A\u0002S1t/\u0016Lw\r\u001b;D_2\u0004\"aI\u001e\n\u0005q\"#!\u0003%bgN{GN^3s!\t\u0019c(\u0003\u0002@I\t\u0019\u0002*Y:NCb\u0014En\\2l'&TX-\u00138N\u0005\u00061A%\u001b8ji\u0012\u001a\u0001\u0001F\u0001D!\t9B)\u0003\u0002F1\t!QK\\5u\u0003\u0005YW#\u0001%\u0011\u0005uI\u0015B\u0001&\u001f\u0005!Ie\u000e\u001e)be\u0006l\u0007f\u0001\u0002M%B\u0011Q\nU\u0007\u0002\u001d*\u0011qjD\u0001\u000bC:tw\u000e^1uS>t\u0017BA)O\u0005\u0015\u0019\u0016N\\2fC\u0005\u0019\u0016!B\u0019/k9\u0002\u0014\u0001B4fi.+\u0012A\u0016\t\u0003/]K!\u0001\u0017\r\u0003\u0007%sG\u000fK\u0002\u0004\u0019J\u000b\u0001\"\u001b8ji6{G-Z\u000b\u00029B\u0019Q$X0\n\u0005ys\"!\u0002)be\u0006l\u0007C\u00011h\u001d\t\tW\r\u0005\u0002c15\t1M\u0003\u0002e\u0003\u00061AH]8pizJ!A\u001a\r\u0002\rA\u0013X\rZ3g\u0013\tA\u0017N\u0001\u0004TiJLgn\u001a\u0006\u0003MbA3\u0001\u0002'S\u0003-9W\r^%oSRlu\u000eZ3\u0016\u0003}C3!\u0002'S\u0003%Ig.\u001b;Ti\u0016\u00048\u000fK\u0002\u0007\u0019J\u000bAbZ3u\u0013:LGo\u0015;faND3a\u0002'S\u0003\u0019\u0019x\u000e\u001c<fe\"\u001a\u0001\u0002\u0014;\"\u0003U\fQa\r\u00185]A\n!D^1mS\u0012\fG/Z!oIR\u0013\u0018M\\:g_Jl7k\u00195f[\u0006$2\u0001_A\u0001!\tIh0D\u0001{\u0015\tYH0A\u0003usB,7O\u0003\u0002~\u001f\u0005\u00191/\u001d7\n\u0005}T(AC*ueV\u001cG\u000fV=qK\"1\u00111A\u0005A\u0002a\faa]2iK6\f\u0007"
)
public interface KMeansParams extends HasMaxIter, HasFeaturesCol, HasSeed, HasPredictionCol, HasTol, HasDistanceMeasure, HasWeightCol, HasSolver, HasMaxBlockSizeInMB {
   void org$apache$spark$ml$clustering$KMeansParams$_setter_$k_$eq(final IntParam x$1);

   void org$apache$spark$ml$clustering$KMeansParams$_setter_$initMode_$eq(final Param x$1);

   void org$apache$spark$ml$clustering$KMeansParams$_setter_$initSteps_$eq(final IntParam x$1);

   void org$apache$spark$ml$clustering$KMeansParams$_setter_$solver_$eq(final Param x$1);

   IntParam k();

   // $FF: synthetic method
   static int getK$(final KMeansParams $this) {
      return $this.getK();
   }

   default int getK() {
      return BoxesRunTime.unboxToInt(this.$(this.k()));
   }

   Param initMode();

   // $FF: synthetic method
   static String getInitMode$(final KMeansParams $this) {
      return $this.getInitMode();
   }

   default String getInitMode() {
      return (String)this.$(this.initMode());
   }

   IntParam initSteps();

   // $FF: synthetic method
   static int getInitSteps$(final KMeansParams $this) {
      return $this.getInitSteps();
   }

   default int getInitSteps() {
      return BoxesRunTime.unboxToInt(this.$(this.initSteps()));
   }

   Param solver();

   // $FF: synthetic method
   static StructType validateAndTransformSchema$(final KMeansParams $this, final StructType schema) {
      return $this.validateAndTransformSchema(schema);
   }

   default StructType validateAndTransformSchema(final StructType schema) {
      SchemaUtils$.MODULE$.validateVectorCompatibleColumn(schema, this.getFeaturesCol());
      return SchemaUtils$.MODULE$.appendColumn(schema, (String)this.$(this.predictionCol()), .MODULE$, SchemaUtils$.MODULE$.appendColumn$default$4());
   }

   static void $init$(final KMeansParams $this) {
      $this.org$apache$spark$ml$clustering$KMeansParams$_setter_$k_$eq(new IntParam($this, "k", "The number of clusters to create. Must be > 1.", ParamValidators$.MODULE$.gt((double)1.0F)));
      $this.org$apache$spark$ml$clustering$KMeansParams$_setter_$initMode_$eq(new Param($this, "initMode", "The initialization algorithm. Supported options: 'random' and 'k-means||'.", ParamValidators$.MODULE$.inArray((Object)KMeans$.MODULE$.supportedInitModes()), scala.reflect.ClassTag..MODULE$.apply(String.class)));
      $this.org$apache$spark$ml$clustering$KMeansParams$_setter_$initSteps_$eq(new IntParam($this, "initSteps", "The number of steps for k-means|| initialization mode. Must be > 0.", ParamValidators$.MODULE$.gt((double)0.0F)));
      $this.org$apache$spark$ml$clustering$KMeansParams$_setter_$solver_$eq(new Param($this, "solver", "The solver algorithm for optimization. Supported options: " + scala.Predef..MODULE$.wrapRefArray((Object[])KMeans$.MODULE$.supportedSolvers()).mkString(", ") + ". (Default auto)", ParamValidators$.MODULE$.inArray((Object)KMeans$.MODULE$.supportedSolvers()), scala.reflect.ClassTag..MODULE$.apply(String.class)));
      $this.setDefault(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new ParamPair[]{$this.k().$minus$greater(BoxesRunTime.boxToInteger(2)), $this.maxIter().$minus$greater(BoxesRunTime.boxToInteger(20)), $this.initMode().$minus$greater(KMeans$.MODULE$.K_MEANS_PARALLEL()), $this.initSteps().$minus$greater(BoxesRunTime.boxToInteger(2)), $this.tol().$minus$greater(BoxesRunTime.boxToDouble(1.0E-4)), $this.distanceMeasure().$minus$greater(KMeans$.MODULE$.EUCLIDEAN()), $this.solver().$minus$greater(KMeans$.MODULE$.AUTO()), $this.maxBlockSizeInMB().$minus$greater(BoxesRunTime.boxToDouble((double)0.0F))}));
   }
}
