package org.apache.spark.ml.classification;

import org.apache.spark.ml.linalg.VectorUDT;
import org.apache.spark.ml.param.shared.HasProbabilityCol;
import org.apache.spark.ml.param.shared.HasThresholds;
import org.apache.spark.ml.util.SchemaUtils$;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.StructType;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u001d3\u0001\u0002B\u0003\u0011\u0002\u0007\u0005qa\u0004\u0005\u0006K\u0001!\ta\n\u0005\u0006W\u0001!\t\u0006\f\u0005\f\u0003\u0002\u0001\n1!A\u0001\n\u0013\u0011eIA\u000fQe>\u0014\u0017MY5mSN$\u0018nY\"mCN\u001c\u0018NZ5feB\u000b'/Y7t\u0015\t1q!\u0001\bdY\u0006\u001c8/\u001b4jG\u0006$\u0018n\u001c8\u000b\u0005!I\u0011AA7m\u0015\tQ1\"A\u0003ta\u0006\u00148N\u0003\u0002\r\u001b\u00051\u0011\r]1dQ\u0016T\u0011AD\u0001\u0004_J<7#\u0002\u0001\u0011-i\u0011\u0003CA\t\u0015\u001b\u0005\u0011\"\"A\n\u0002\u000bM\u001c\u0017\r\\1\n\u0005U\u0011\"AB!osJ+g\r\u0005\u0002\u001815\tQ!\u0003\u0002\u001a\u000b\t\u00012\t\\1tg&4\u0017.\u001a:QCJ\fWn\u001d\t\u00037\u0001j\u0011\u0001\b\u0006\u0003;y\taa\u001d5be\u0016$'BA\u0010\b\u0003\u0015\u0001\u0018M]1n\u0013\t\tCDA\tICN\u0004&o\u001c2bE&d\u0017\u000e^=D_2\u0004\"aG\u0012\n\u0005\u0011b\"!\u0004%bgRC'/Z:i_2$7/\u0001\u0004%S:LG\u000fJ\u0002\u0001)\u0005A\u0003CA\t*\u0013\tQ#C\u0001\u0003V]&$\u0018A\u0007<bY&$\u0017\r^3B]\u0012$&/\u00198tM>\u0014XnU2iK6\fG\u0003B\u00176oq\u0002\"AL\u001a\u000e\u0003=R!\u0001M\u0019\u0002\u000bQL\b/Z:\u000b\u0005IJ\u0011aA:rY&\u0011Ag\f\u0002\u000b'R\u0014Xo\u0019;UsB,\u0007\"\u0002\u001c\u0003\u0001\u0004i\u0013AB:dQ\u0016l\u0017\rC\u00039\u0005\u0001\u0007\u0011(A\u0004gSR$\u0018N\\4\u0011\u0005EQ\u0014BA\u001e\u0013\u0005\u001d\u0011un\u001c7fC:DQ!\u0010\u0002A\u0002y\n\u0001CZ3biV\u0014Xm\u001d#bi\u0006$\u0016\u0010]3\u0011\u00059z\u0014B\u0001!0\u0005!!\u0015\r^1UsB,\u0017\u0001I:va\u0016\u0014HE^1mS\u0012\fG/Z!oIR\u0013\u0018M\\:g_Jl7k\u00195f[\u0006$B!L\"E\u000b\")ag\u0001a\u0001[!)\u0001h\u0001a\u0001s!)Qh\u0001a\u0001}%\u00111\u0006\u0007"
)
public interface ProbabilisticClassifierParams extends ClassifierParams, HasProbabilityCol, HasThresholds {
   // $FF: synthetic method
   StructType org$apache$spark$ml$classification$ProbabilisticClassifierParams$$super$validateAndTransformSchema(final StructType schema, final boolean fitting, final DataType featuresDataType);

   // $FF: synthetic method
   static StructType validateAndTransformSchema$(final ProbabilisticClassifierParams $this, final StructType schema, final boolean fitting, final DataType featuresDataType) {
      return $this.validateAndTransformSchema(schema, fitting, featuresDataType);
   }

   default StructType validateAndTransformSchema(final StructType schema, final boolean fitting, final DataType featuresDataType) {
      StructType parentSchema = this.org$apache$spark$ml$classification$ProbabilisticClassifierParams$$super$validateAndTransformSchema(schema, fitting, featuresDataType);
      return SchemaUtils$.MODULE$.appendColumn(parentSchema, (String)this.$(this.probabilityCol()), new VectorUDT(), SchemaUtils$.MODULE$.appendColumn$default$4());
   }

   static void $init$(final ProbabilisticClassifierParams $this) {
   }
}
