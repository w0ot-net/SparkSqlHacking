package org.apache.spark.ml.tree;

import org.apache.spark.ml.param.shared.HasVarianceCol;
import org.apache.spark.ml.util.SchemaUtils$;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.StructType;
import scala.collection.StringOps.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005%3\u0001\u0002B\u0003\u0011\u0002\u0007\u0005qa\u0004\u0005\u0006K\u0001!\ta\n\u0005\u0006W\u0001!\t\u0006\f\u0005\f\u0003\u0002\u0001\n1!A\u0001\n\u0013\u0011eIA\u000eEK\u000eL7/[8o)J,WMU3he\u0016\u001c8o\u001c:QCJ\fWn\u001d\u0006\u0003\r\u001d\tA\u0001\u001e:fK*\u0011\u0001\"C\u0001\u0003[2T!AC\u0006\u0002\u000bM\u0004\u0018M]6\u000b\u00051i\u0011AB1qC\u000eDWMC\u0001\u000f\u0003\ry'oZ\n\u0006\u0001A1\"$\b\t\u0003#Qi\u0011A\u0005\u0006\u0002'\u0005)1oY1mC&\u0011QC\u0005\u0002\u0007\u0003:L(+\u001a4\u0011\u0005]AR\"A\u0003\n\u0005e)!A\u0005#fG&\u001c\u0018n\u001c8Ue\u0016,\u0007+\u0019:b[N\u0004\"aF\u000e\n\u0005q)!a\u0005+sK\u0016\u0014Vm\u001a:fgN|'\u000fU1sC6\u001c\bC\u0001\u0010$\u001b\u0005y\"B\u0001\u0011\"\u0003\u0019\u0019\b.\u0019:fI*\u0011!eB\u0001\u0006a\u0006\u0014\u0018-\\\u0005\u0003I}\u0011a\u0002S1t-\u0006\u0014\u0018.\u00198dK\u000e{G.\u0001\u0004%S:LG\u000fJ\u0002\u0001)\u0005A\u0003CA\t*\u0013\tQ#C\u0001\u0003V]&$\u0018A\u0007<bY&$\u0017\r^3B]\u0012$&/\u00198tM>\u0014XnU2iK6\fG\u0003B\u00176oq\u0002\"AL\u001a\u000e\u0003=R!\u0001M\u0019\u0002\u000bQL\b/Z:\u000b\u0005IJ\u0011aA:rY&\u0011Ag\f\u0002\u000b'R\u0014Xo\u0019;UsB,\u0007\"\u0002\u001c\u0003\u0001\u0004i\u0013AB:dQ\u0016l\u0017\rC\u00039\u0005\u0001\u0007\u0011(A\u0004gSR$\u0018N\\4\u0011\u0005EQ\u0014BA\u001e\u0013\u0005\u001d\u0011un\u001c7fC:DQ!\u0010\u0002A\u0002y\n\u0001CZ3biV\u0014Xm\u001d#bi\u0006$\u0016\u0010]3\u0011\u00059z\u0014B\u0001!0\u0005!!\u0015\r^1UsB,\u0017\u0001I:va\u0016\u0014HE^1mS\u0012\fG/Z!oIR\u0013\u0018M\\:g_Jl7k\u00195f[\u0006$B!L\"E\u000b\")ag\u0001a\u0001[!)\u0001h\u0001a\u0001s!)Qh\u0001a\u0001}%\u00111fR\u0005\u0003\u0011\u001e\u0011q\u0002\u0015:fI&\u001cGo\u001c:QCJ\fWn\u001d"
)
public interface DecisionTreeRegressorParams extends DecisionTreeParams, TreeRegressorParams, HasVarianceCol {
   // $FF: synthetic method
   StructType org$apache$spark$ml$tree$DecisionTreeRegressorParams$$super$validateAndTransformSchema(final StructType schema, final boolean fitting, final DataType featuresDataType);

   // $FF: synthetic method
   static StructType validateAndTransformSchema$(final DecisionTreeRegressorParams $this, final StructType schema, final boolean fitting, final DataType featuresDataType) {
      return $this.validateAndTransformSchema(schema, fitting, featuresDataType);
   }

   default StructType validateAndTransformSchema(final StructType schema, final boolean fitting, final DataType featuresDataType) {
      StructType outputSchema = this.org$apache$spark$ml$tree$DecisionTreeRegressorParams$$super$validateAndTransformSchema(schema, fitting, featuresDataType);
      if (this.isDefined(this.varianceCol()) && .MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString((String)this.$(this.varianceCol())))) {
         outputSchema = SchemaUtils$.MODULE$.appendColumn(outputSchema, (String)this.$(this.varianceCol()), org.apache.spark.sql.types.DoubleType..MODULE$, SchemaUtils$.MODULE$.appendColumn$default$4());
      }

      if (.MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString((String)this.$(this.leafCol())))) {
         outputSchema = SchemaUtils$.MODULE$.appendColumn(outputSchema, (String)this.$(this.leafCol()), org.apache.spark.sql.types.DoubleType..MODULE$, SchemaUtils$.MODULE$.appendColumn$default$4());
      }

      return outputSchema;
   }

   static void $init$(final DecisionTreeRegressorParams $this) {
   }
}
