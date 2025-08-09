package org.apache.spark.ml.clustering;

import org.apache.spark.ml.linalg.VectorUDT;
import org.apache.spark.ml.param.IntParam;
import org.apache.spark.ml.param.ParamPair;
import org.apache.spark.ml.param.ParamValidators$;
import org.apache.spark.ml.param.shared.HasAggregationDepth;
import org.apache.spark.ml.param.shared.HasFeaturesCol;
import org.apache.spark.ml.param.shared.HasMaxIter;
import org.apache.spark.ml.param.shared.HasPredictionCol;
import org.apache.spark.ml.param.shared.HasProbabilityCol;
import org.apache.spark.ml.param.shared.HasSeed;
import org.apache.spark.ml.param.shared.HasTol;
import org.apache.spark.ml.param.shared.HasWeightCol;
import org.apache.spark.ml.util.SchemaUtils$;
import org.apache.spark.sql.types.StructType;
import org.apache.spark.sql.types.IntegerType.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005y3\u0001\"\u0002\u0004\u0011\u0002\u0007\u0005a\u0001\u0005\u0005\u0006q\u0001!\tA\u000f\u0005\b}\u0001\u0011\r\u0011\"\u0002@\u0011\u0015a\u0005\u0001\"\u0001N\u0011\u0015\u0011\u0006\u0001\"\u0005T\u0005U9\u0015-^:tS\u0006tW*\u001b=ukJ,\u0007+\u0019:b[NT!a\u0002\u0005\u0002\u0015\rdWo\u001d;fe&twM\u0003\u0002\n\u0015\u0005\u0011Q\u000e\u001c\u0006\u0003\u00171\tQa\u001d9be.T!!\u0004\b\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005y\u0011aA8sONY\u0001!E\f\u001eG\u0019JCf\f\u001a6!\t\u0011R#D\u0001\u0014\u0015\u0005!\u0012!B:dC2\f\u0017B\u0001\f\u0014\u0005\u0019\te.\u001f*fMB\u0011\u0001dG\u0007\u00023)\u0011!\u0004C\u0001\u0006a\u0006\u0014\u0018-\\\u0005\u00039e\u0011a\u0001U1sC6\u001c\bC\u0001\u0010\"\u001b\u0005y\"B\u0001\u0011\u001a\u0003\u0019\u0019\b.\u0019:fI&\u0011!e\b\u0002\u000b\u0011\u0006\u001cX*\u0019=Ji\u0016\u0014\bC\u0001\u0010%\u0013\t)sD\u0001\bICN4U-\u0019;ve\u0016\u001c8i\u001c7\u0011\u0005y9\u0013B\u0001\u0015 \u0005\u001dA\u0015m]*fK\u0012\u0004\"A\b\u0016\n\u0005-z\"\u0001\u0005%bgB\u0013X\rZ5di&|gnQ8m!\tqR&\u0003\u0002/?\ta\u0001*Y:XK&<\u0007\u000e^\"pYB\u0011a\u0004M\u0005\u0003c}\u0011\u0011\u0003S1t!J|'-\u00192jY&$\u0018pQ8m!\tq2'\u0003\u00025?\t1\u0001*Y:U_2\u0004\"A\b\u001c\n\u0005]z\"a\u0005%bg\u0006;wM]3hCRLwN\u001c#faRD\u0017A\u0002\u0013j]&$He\u0001\u0001\u0015\u0003m\u0002\"A\u0005\u001f\n\u0005u\u001a\"\u0001B+oSR\f\u0011a[\u000b\u0002\u0001B\u0011\u0001$Q\u0005\u0003\u0005f\u0011\u0001\"\u00138u!\u0006\u0014\u0018-\u001c\u0015\u0004\u0005\u0011S\u0005CA#I\u001b\u00051%BA$\u000b\u0003)\tgN\\8uCRLwN\\\u0005\u0003\u0013\u001a\u0013QaU5oG\u0016\f\u0013aS\u0001\u0006e9\u0002d\u0006M\u0001\u0005O\u0016$8*F\u0001O!\t\u0011r*\u0003\u0002Q'\t\u0019\u0011J\u001c;)\u0007\r!%*\u0001\u000ewC2LG-\u0019;f\u0003:$GK]1og\u001a|'/\\*dQ\u0016l\u0017\r\u0006\u0002U9B\u0011QKW\u0007\u0002-*\u0011q\u000bW\u0001\u0006if\u0004Xm\u001d\u0006\u00033*\t1a]9m\u0013\tYfK\u0001\u0006TiJ,8\r\u001e+za\u0016DQ!\u0018\u0003A\u0002Q\u000baa]2iK6\f\u0007"
)
public interface GaussianMixtureParams extends HasMaxIter, HasFeaturesCol, HasSeed, HasPredictionCol, HasWeightCol, HasProbabilityCol, HasTol, HasAggregationDepth {
   void org$apache$spark$ml$clustering$GaussianMixtureParams$_setter_$k_$eq(final IntParam x$1);

   IntParam k();

   // $FF: synthetic method
   static int getK$(final GaussianMixtureParams $this) {
      return $this.getK();
   }

   default int getK() {
      return BoxesRunTime.unboxToInt(this.$(this.k()));
   }

   // $FF: synthetic method
   static StructType validateAndTransformSchema$(final GaussianMixtureParams $this, final StructType schema) {
      return $this.validateAndTransformSchema(schema);
   }

   default StructType validateAndTransformSchema(final StructType schema) {
      SchemaUtils$.MODULE$.validateVectorCompatibleColumn(schema, this.getFeaturesCol());
      StructType schemaWithPredictionCol = SchemaUtils$.MODULE$.appendColumn(schema, (String)this.$(this.predictionCol()), .MODULE$, SchemaUtils$.MODULE$.appendColumn$default$4());
      return SchemaUtils$.MODULE$.appendColumn(schemaWithPredictionCol, (String)this.$(this.probabilityCol()), new VectorUDT(), SchemaUtils$.MODULE$.appendColumn$default$4());
   }

   static void $init$(final GaussianMixtureParams $this) {
      $this.org$apache$spark$ml$clustering$GaussianMixtureParams$_setter_$k_$eq(new IntParam($this, "k", "Number of independent Gaussians in the mixture model. Must be > 1.", ParamValidators$.MODULE$.gt((double)1.0F)));
      $this.setDefault(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new ParamPair[]{$this.k().$minus$greater(BoxesRunTime.boxToInteger(2)), $this.maxIter().$minus$greater(BoxesRunTime.boxToInteger(100)), $this.tol().$minus$greater(BoxesRunTime.boxToDouble(0.01))}));
   }
}
