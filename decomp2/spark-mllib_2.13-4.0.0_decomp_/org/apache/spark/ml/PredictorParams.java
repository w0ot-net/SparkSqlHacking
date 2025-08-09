package org.apache.spark.ml;

import org.apache.spark.ml.param.shared.HasFeaturesCol;
import org.apache.spark.ml.param.shared.HasLabelCol;
import org.apache.spark.ml.param.shared.HasPredictionCol;
import org.apache.spark.ml.param.shared.HasWeightCol;
import org.apache.spark.ml.util.SchemaUtils$;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.StructType;
import scala.collection.StringOps.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005\u00053\u0001b\u0001\u0003\u0011\u0002\u0007\u0005A\u0001\u0004\u0005\u0006K\u0001!\ta\n\u0005\u0006W\u0001!\t\u0002\f\u0002\u0010!J,G-[2u_J\u0004\u0016M]1ng*\u0011QAB\u0001\u0003[2T!a\u0002\u0005\u0002\u000bM\u0004\u0018M]6\u000b\u0005%Q\u0011AB1qC\u000eDWMC\u0001\f\u0003\ry'oZ\n\u0007\u00015\u0019\u0012d\b\u0012\u0011\u00059\tR\"A\b\u000b\u0003A\tQa]2bY\u0006L!AE\b\u0003\r\u0005s\u0017PU3g!\t!r#D\u0001\u0016\u0015\t1B!A\u0003qCJ\fW.\u0003\u0002\u0019+\t1\u0001+\u0019:b[N\u0004\"AG\u000f\u000e\u0003mQ!\u0001H\u000b\u0002\rMD\u0017M]3e\u0013\tq2DA\u0006ICNd\u0015MY3m\u0007>d\u0007C\u0001\u000e!\u0013\t\t3D\u0001\bICN4U-\u0019;ve\u0016\u001c8i\u001c7\u0011\u0005i\u0019\u0013B\u0001\u0013\u001c\u0005AA\u0015m\u001d)sK\u0012L7\r^5p]\u000e{G.\u0001\u0004%S:LG\u000fJ\u0002\u0001)\u0005A\u0003C\u0001\b*\u0013\tQsB\u0001\u0003V]&$\u0018A\u0007<bY&$\u0017\r^3B]\u0012$&/\u00198tM>\u0014XnU2iK6\fG\u0003B\u00176oq\u0002\"AL\u001a\u000e\u0003=R!\u0001M\u0019\u0002\u000bQL\b/Z:\u000b\u0005I2\u0011aA:rY&\u0011Ag\f\u0002\u000b'R\u0014Xo\u0019;UsB,\u0007\"\u0002\u001c\u0003\u0001\u0004i\u0013AB:dQ\u0016l\u0017\rC\u00039\u0005\u0001\u0007\u0011(A\u0004gSR$\u0018N\\4\u0011\u00059Q\u0014BA\u001e\u0010\u0005\u001d\u0011un\u001c7fC:DQ!\u0010\u0002A\u0002y\n\u0001CZ3biV\u0014Xm\u001d#bi\u0006$\u0016\u0010]3\u0011\u00059z\u0014B\u0001!0\u0005!!\u0015\r^1UsB,\u0007"
)
public interface PredictorParams extends HasLabelCol, HasFeaturesCol, HasPredictionCol {
   // $FF: synthetic method
   static StructType validateAndTransformSchema$(final PredictorParams $this, final StructType schema, final boolean fitting, final DataType featuresDataType) {
      return $this.validateAndTransformSchema(schema, fitting, featuresDataType);
   }

   default StructType validateAndTransformSchema(final StructType schema, final boolean fitting, final DataType featuresDataType) {
      SchemaUtils$.MODULE$.checkColumnType(schema, (String)this.$(this.featuresCol()), featuresDataType, SchemaUtils$.MODULE$.checkColumnType$default$4());
      if (fitting) {
         SchemaUtils$.MODULE$.checkNumericType(schema, (String)this.$(this.labelCol()), SchemaUtils$.MODULE$.checkNumericType$default$3());
         if (this instanceof HasWeightCol) {
            if (this.isDefined(((HasWeightCol)this).weightCol()) && .MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString((String)this.$(((HasWeightCol)this).weightCol())))) {
               SchemaUtils$.MODULE$.checkNumericType(schema, (String)this.$(((HasWeightCol)this).weightCol()), SchemaUtils$.MODULE$.checkNumericType$default$3());
               BoxedUnit var7 = BoxedUnit.UNIT;
            } else {
               BoxedUnit var10000 = BoxedUnit.UNIT;
            }
         } else {
            BoxedUnit var8 = BoxedUnit.UNIT;
         }
      }

      return SchemaUtils$.MODULE$.appendColumn(schema, (String)this.$(this.predictionCol()), org.apache.spark.sql.types.DoubleType..MODULE$, SchemaUtils$.MODULE$.appendColumn$default$4());
   }

   static void $init$(final PredictorParams $this) {
   }
}
