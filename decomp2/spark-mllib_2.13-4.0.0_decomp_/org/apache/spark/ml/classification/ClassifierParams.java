package org.apache.spark.ml.classification;

import org.apache.spark.ml.PredictorParams;
import org.apache.spark.ml.linalg.VectorUDT;
import org.apache.spark.ml.param.shared.HasRawPredictionCol;
import org.apache.spark.ml.util.SchemaUtils$;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.StructType;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00113\u0001\u0002B\u0003\u0011\u0002\u0007\u0005\u0011b\u0004\u0005\u0006E\u0001!\t\u0001\n\u0005\u0006Q\u0001!\t&\u000b\u0005\f}\u0001\u0001\n1!A\u0001\n\u0013y4I\u0001\tDY\u0006\u001c8/\u001b4jKJ\u0004\u0016M]1ng*\u0011aaB\u0001\u000fG2\f7o]5gS\u000e\fG/[8o\u0015\tA\u0011\"\u0001\u0002nY*\u0011!bC\u0001\u0006gB\f'o\u001b\u0006\u0003\u00195\ta!\u00199bG\",'\"\u0001\b\u0002\u0007=\u0014xm\u0005\u0003\u0001!YQ\u0002CA\t\u0015\u001b\u0005\u0011\"\"A\n\u0002\u000bM\u001c\u0017\r\\1\n\u0005U\u0011\"AB!osJ+g\r\u0005\u0002\u001815\tq!\u0003\u0002\u001a\u000f\ty\u0001K]3eS\u000e$xN\u001d)be\u0006l7\u000f\u0005\u0002\u001cA5\tAD\u0003\u0002\u001e=\u000511\u000f[1sK\u0012T!aH\u0004\u0002\u000bA\f'/Y7\n\u0005\u0005b\"a\u0005%bgJ\u000bw\u000f\u0015:fI&\u001cG/[8o\u0007>d\u0017A\u0002\u0013j]&$He\u0001\u0001\u0015\u0003\u0015\u0002\"!\u0005\u0014\n\u0005\u001d\u0012\"\u0001B+oSR\f!D^1mS\u0012\fG/Z!oIR\u0013\u0018M\\:g_Jl7k\u00195f[\u0006$BA\u000b\u001a5sA\u00111\u0006M\u0007\u0002Y)\u0011QFL\u0001\u0006if\u0004Xm\u001d\u0006\u0003_%\t1a]9m\u0013\t\tDF\u0001\u0006TiJ,8\r\u001e+za\u0016DQa\r\u0002A\u0002)\naa]2iK6\f\u0007\"B\u001b\u0003\u0001\u00041\u0014a\u00024jiRLgn\u001a\t\u0003#]J!\u0001\u000f\n\u0003\u000f\t{w\u000e\\3b]\")!H\u0001a\u0001w\u0005\u0001b-Z1ukJ,7\u000fR1uCRK\b/\u001a\t\u0003WqJ!!\u0010\u0017\u0003\u0011\u0011\u000bG/\u0019+za\u0016\f\u0001e];qKJ$c/\u00197jI\u0006$X-\u00118e)J\fgn\u001d4pe6\u001c6\r[3nCR!!\u0006Q!C\u0011\u0015\u00194\u00011\u0001+\u0011\u0015)4\u00011\u00017\u0011\u0015Q4\u00011\u0001<\u0013\tA\u0003\u0004"
)
public interface ClassifierParams extends PredictorParams, HasRawPredictionCol {
   // $FF: synthetic method
   StructType org$apache$spark$ml$classification$ClassifierParams$$super$validateAndTransformSchema(final StructType schema, final boolean fitting, final DataType featuresDataType);

   // $FF: synthetic method
   static StructType validateAndTransformSchema$(final ClassifierParams $this, final StructType schema, final boolean fitting, final DataType featuresDataType) {
      return $this.validateAndTransformSchema(schema, fitting, featuresDataType);
   }

   default StructType validateAndTransformSchema(final StructType schema, final boolean fitting, final DataType featuresDataType) {
      StructType parentSchema = this.org$apache$spark$ml$classification$ClassifierParams$$super$validateAndTransformSchema(schema, fitting, featuresDataType);
      return SchemaUtils$.MODULE$.appendColumn(parentSchema, (String)this.$(this.rawPredictionCol()), new VectorUDT(), SchemaUtils$.MODULE$.appendColumn$default$4());
   }

   static void $init$(final ClassifierParams $this) {
   }
}
