package org.apache.spark.ml.tree;

import org.apache.spark.ml.classification.ProbabilisticClassifierParams;
import org.apache.spark.ml.linalg.VectorUDT;
import org.apache.spark.ml.util.SchemaUtils$;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.StructType;
import scala.collection.StringOps.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\t3\u0001\u0002B\u0003\u0011\u0002\u0007\u0005qa\u0004\u0005\u0006A\u0001!\tA\t\u0005\u0006M\u0001!\tf\n\u0005\fy\u0001\u0001\n1!A\u0001\n\u0013i\u0014I\u0001\u000fUe\u0016,WI\\:f[\ndWm\u00117bgNLg-[3s!\u0006\u0014\u0018-\\:\u000b\u0005\u00199\u0011\u0001\u0002;sK\u0016T!\u0001C\u0005\u0002\u00055d'B\u0001\u0006\f\u0003\u0015\u0019\b/\u0019:l\u0015\taQ\"\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002\u001d\u0005\u0019qN]4\u0014\t\u0001\u0001bC\u0007\t\u0003#Qi\u0011A\u0005\u0006\u0002'\u0005)1oY1mC&\u0011QC\u0005\u0002\u0007\u0003:L(+\u001a4\u0011\u0005]AR\"A\u0003\n\u0005e)!A\u0005+sK\u0016,en]3nE2,\u0007+\u0019:b[N\u0004\"a\u0007\u0010\u000e\u0003qQ!!H\u0004\u0002\u001d\rd\u0017m]:jM&\u001c\u0017\r^5p]&\u0011q\u0004\b\u0002\u001e!J|'-\u00192jY&\u001cH/[2DY\u0006\u001c8/\u001b4jKJ\u0004\u0016M]1ng\u00061A%\u001b8ji\u0012\u001a\u0001\u0001F\u0001$!\t\tB%\u0003\u0002&%\t!QK\\5u\u0003i1\u0018\r\\5eCR,\u0017I\u001c3Ue\u0006t7OZ8s[N\u001b\u0007.Z7b)\u0011A\u0003GM\u001c\u0011\u0005%rS\"\u0001\u0016\u000b\u0005-b\u0013!\u0002;za\u0016\u001c(BA\u0017\n\u0003\r\u0019\u0018\u000f\\\u0005\u0003_)\u0012!b\u0015;sk\u000e$H+\u001f9f\u0011\u0015\t$\u00011\u0001)\u0003\u0019\u00198\r[3nC\")1G\u0001a\u0001i\u00059a-\u001b;uS:<\u0007CA\t6\u0013\t1$CA\u0004C_>dW-\u00198\t\u000ba\u0012\u0001\u0019A\u001d\u0002!\u0019,\u0017\r^;sKN$\u0015\r^1UsB,\u0007CA\u0015;\u0013\tY$F\u0001\u0005ECR\fG+\u001f9f\u0003\u0001\u001aX\u000f]3sIY\fG.\u001b3bi\u0016\fe\u000e\u001a+sC:\u001chm\u001c:n'\u000eDW-\\1\u0015\t!rt\b\u0011\u0005\u0006c\r\u0001\r\u0001\u000b\u0005\u0006g\r\u0001\r\u0001\u000e\u0005\u0006q\r\u0001\r!O\u0005\u0003My\u0001"
)
public interface TreeEnsembleClassifierParams extends TreeEnsembleParams, ProbabilisticClassifierParams {
   // $FF: synthetic method
   StructType org$apache$spark$ml$tree$TreeEnsembleClassifierParams$$super$validateAndTransformSchema(final StructType schema, final boolean fitting, final DataType featuresDataType);

   // $FF: synthetic method
   static StructType validateAndTransformSchema$(final TreeEnsembleClassifierParams $this, final StructType schema, final boolean fitting, final DataType featuresDataType) {
      return $this.validateAndTransformSchema(schema, fitting, featuresDataType);
   }

   default StructType validateAndTransformSchema(final StructType schema, final boolean fitting, final DataType featuresDataType) {
      StructType outputSchema = this.org$apache$spark$ml$tree$TreeEnsembleClassifierParams$$super$validateAndTransformSchema(schema, fitting, featuresDataType);
      if (.MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString((String)this.$(this.leafCol())))) {
         outputSchema = SchemaUtils$.MODULE$.appendColumn(outputSchema, (String)this.$(this.leafCol()), new VectorUDT(), SchemaUtils$.MODULE$.appendColumn$default$4());
      }

      return outputSchema;
   }

   static void $init$(final TreeEnsembleClassifierParams $this) {
   }
}
