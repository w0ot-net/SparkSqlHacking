package org.apache.spark.ml.classification;

import org.apache.spark.ml.Predictor;
import org.apache.spark.ml.PredictorParams;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.shared.HasRawPredictionCol;
import org.apache.spark.ml.util.DatasetUtils$;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.StructType;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u00154Q!\u0002\u0004\u0002\u0002EAQ!\u000e\u0001\u0005\u0002YBQa\u000e\u0001\u0005\u0012aBqA\u0013\u0001\u0012\u0002\u0013E1\nC\u0003W\u0001\u0011\u0005qK\u0001\u0006DY\u0006\u001c8/\u001b4jKJT!a\u0002\u0005\u0002\u001d\rd\u0017m]:jM&\u001c\u0017\r^5p]*\u0011\u0011BC\u0001\u0003[2T!a\u0003\u0007\u0002\u000bM\u0004\u0018M]6\u000b\u00055q\u0011AB1qC\u000eDWMC\u0001\u0010\u0003\ry'oZ\u0002\u0001+\u0011\u0011\u0012D\n\u0017\u0014\u0007\u0001\u0019\"\u0007E\u0003\u0015+])3&D\u0001\t\u0013\t1\u0002BA\u0005Qe\u0016$\u0017n\u0019;peB\u0011\u0001$\u0007\u0007\u0001\t\u0015Q\u0002A1\u0001\u001c\u000511U-\u0019;ve\u0016\u001cH+\u001f9f#\ta\"\u0005\u0005\u0002\u001eA5\taDC\u0001 \u0003\u0015\u00198-\u00197b\u0013\t\tcDA\u0004O_RD\u0017N\\4\u0011\u0005u\u0019\u0013B\u0001\u0013\u001f\u0005\r\te.\u001f\t\u00031\u0019\"Qa\n\u0001C\u0002!\u0012\u0011!R\t\u00039%\u0002RA\u000b\u0001\u0018K-j\u0011A\u0002\t\u000311\"Q!\f\u0001C\u00029\u0012\u0011!T\t\u00039=\u0002BA\u000b\u0019\u0018W%\u0011\u0011G\u0002\u0002\u0014\u00072\f7o]5gS\u000e\fG/[8o\u001b>$W\r\u001c\t\u0003UMJ!\u0001\u000e\u0004\u0003!\rc\u0017m]:jM&,'\u000fU1sC6\u001c\u0018A\u0002\u001fj]&$h\bF\u0001*\u000359W\r\u001e(v[\u000ec\u0017m]:fgR\u0019\u0011\b\u0010%\u0011\u0005uQ\u0014BA\u001e\u001f\u0005\rIe\u000e\u001e\u0005\u0006{\t\u0001\rAP\u0001\bI\u0006$\u0018m]3ua\tyd\tE\u0002A\u0007\u0016k\u0011!\u0011\u0006\u0003\u0005*\t1a]9m\u0013\t!\u0015IA\u0004ECR\f7/\u001a;\u0011\u0005a1E!C$=\u0003\u0003\u0005\tQ!\u0001\u001c\u0005\ryF%\r\u0005\b\u0013\n\u0001\n\u00111\u0001:\u00035i\u0017\r\u001f(v[\u000ec\u0017m]:fg\u00069r-\u001a;Ok6\u001cE.Y:tKN$C-\u001a4bk2$HEM\u000b\u0002\u0019*\u0012\u0011(T\u0016\u0002\u001dB\u0011q\nV\u0007\u0002!*\u0011\u0011KU\u0001\nk:\u001c\u0007.Z2lK\u0012T!a\u0015\u0010\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002V!\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002'M,GOU1x!J,G-[2uS>t7i\u001c7\u0015\u0005\u0015B\u0006\"B-\u0005\u0001\u0004Q\u0016!\u0002<bYV,\u0007CA.c\u001d\ta\u0006\r\u0005\u0002^=5\taL\u0003\u0002`!\u00051AH]8pizJ!!\u0019\u0010\u0002\rA\u0013X\rZ3g\u0013\t\u0019GM\u0001\u0004TiJLgn\u001a\u0006\u0003Cz\u0001"
)
public abstract class Classifier extends Predictor implements ClassifierParams {
   private Param rawPredictionCol;

   // $FF: synthetic method
   public StructType org$apache$spark$ml$classification$ClassifierParams$$super$validateAndTransformSchema(final StructType schema, final boolean fitting, final DataType featuresDataType) {
      return PredictorParams.validateAndTransformSchema$(this, schema, fitting, featuresDataType);
   }

   public StructType validateAndTransformSchema(final StructType schema, final boolean fitting, final DataType featuresDataType) {
      return ClassifierParams.validateAndTransformSchema$(this, schema, fitting, featuresDataType);
   }

   public final String getRawPredictionCol() {
      return HasRawPredictionCol.getRawPredictionCol$(this);
   }

   public final Param rawPredictionCol() {
      return this.rawPredictionCol;
   }

   public final void org$apache$spark$ml$param$shared$HasRawPredictionCol$_setter_$rawPredictionCol_$eq(final Param x$1) {
      this.rawPredictionCol = x$1;
   }

   public int getNumClasses(final Dataset dataset, final int maxNumClasses) {
      return DatasetUtils$.MODULE$.getNumClasses(dataset, (String)this.$(this.labelCol()), maxNumClasses);
   }

   public int getNumClasses$default$2() {
      return 100;
   }

   public Classifier setRawPredictionCol(final String value) {
      return (Classifier)this.set(this.rawPredictionCol(), value);
   }

   public Classifier() {
      HasRawPredictionCol.$init$(this);
      ClassifierParams.$init$(this);
      Statics.releaseFence();
   }
}
