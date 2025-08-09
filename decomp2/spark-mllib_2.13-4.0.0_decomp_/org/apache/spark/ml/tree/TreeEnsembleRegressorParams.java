package org.apache.spark.ml.tree;

import org.apache.spark.ml.linalg.VectorUDT;
import org.apache.spark.ml.util.SchemaUtils$;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.StructType;
import scala.collection.StringOps.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005y2\u0001\u0002B\u0003\u0011\u0002\u0007\u0005qa\u0004\u0005\u00065\u0001!\t\u0001\b\u0005\u0006A\u0001!\t&\t\u0005\fm\u0001\u0001\n1!A\u0001\n\u001394HA\u000eUe\u0016,WI\\:f[\ndWMU3he\u0016\u001c8o\u001c:QCJ\fWn\u001d\u0006\u0003\r\u001d\tA\u0001\u001e:fK*\u0011\u0001\"C\u0001\u0003[2T!AC\u0006\u0002\u000bM\u0004\u0018M]6\u000b\u00051i\u0011AB1qC\u000eDWMC\u0001\u000f\u0003\ry'oZ\n\u0004\u0001A1\u0002CA\t\u0015\u001b\u0005\u0011\"\"A\n\u0002\u000bM\u001c\u0017\r\\1\n\u0005U\u0011\"AB!osJ+g\r\u0005\u0002\u001815\tQ!\u0003\u0002\u001a\u000b\t\u0011BK]3f\u000b:\u001cX-\u001c2mKB\u000b'/Y7t\u0003\u0019!\u0013N\\5uI\r\u0001A#A\u000f\u0011\u0005Eq\u0012BA\u0010\u0013\u0005\u0011)f.\u001b;\u00025Y\fG.\u001b3bi\u0016\fe\u000e\u001a+sC:\u001chm\u001c:n'\u000eDW-\\1\u0015\t\tRC&\r\t\u0003G!j\u0011\u0001\n\u0006\u0003K\u0019\nQ\u0001^=qKNT!aJ\u0005\u0002\u0007M\fH.\u0003\u0002*I\tQ1\u000b\u001e:vGR$\u0016\u0010]3\t\u000b-\u0012\u0001\u0019\u0001\u0012\u0002\rM\u001c\u0007.Z7b\u0011\u0015i#\u00011\u0001/\u0003\u001d1\u0017\u000e\u001e;j]\u001e\u0004\"!E\u0018\n\u0005A\u0012\"a\u0002\"p_2,\u0017M\u001c\u0005\u0006e\t\u0001\raM\u0001\u0011M\u0016\fG/\u001e:fg\u0012\u000bG/\u0019+za\u0016\u0004\"a\t\u001b\n\u0005U\"#\u0001\u0003#bi\u0006$\u0016\u0010]3\u0002AM,\b/\u001a:%m\u0006d\u0017\u000eZ1uK\u0006sG\r\u0016:b]N4wN]7TG\",W.\u0019\u000b\u0005EaJ$\bC\u0003,\u0007\u0001\u0007!\u0005C\u0003.\u0007\u0001\u0007a\u0006C\u00033\u0007\u0001\u00071'\u0003\u0002!y%\u0011Qh\u0002\u0002\u0010!J,G-[2u_J\u0004\u0016M]1ng\u0002"
)
public interface TreeEnsembleRegressorParams extends TreeEnsembleParams {
   // $FF: synthetic method
   StructType org$apache$spark$ml$tree$TreeEnsembleRegressorParams$$super$validateAndTransformSchema(final StructType schema, final boolean fitting, final DataType featuresDataType);

   // $FF: synthetic method
   static StructType validateAndTransformSchema$(final TreeEnsembleRegressorParams $this, final StructType schema, final boolean fitting, final DataType featuresDataType) {
      return $this.validateAndTransformSchema(schema, fitting, featuresDataType);
   }

   default StructType validateAndTransformSchema(final StructType schema, final boolean fitting, final DataType featuresDataType) {
      StructType outputSchema = this.org$apache$spark$ml$tree$TreeEnsembleRegressorParams$$super$validateAndTransformSchema(schema, fitting, featuresDataType);
      if (.MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString((String)this.$(this.leafCol())))) {
         outputSchema = SchemaUtils$.MODULE$.appendColumn(outputSchema, (String)this.$(this.leafCol()), new VectorUDT(), SchemaUtils$.MODULE$.appendColumn$default$4());
      }

      return outputSchema;
   }

   static void $init$(final TreeEnsembleRegressorParams $this) {
   }
}
