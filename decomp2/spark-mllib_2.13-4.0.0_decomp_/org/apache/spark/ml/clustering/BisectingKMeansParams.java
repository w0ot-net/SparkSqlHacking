package org.apache.spark.ml.clustering;

import org.apache.spark.ml.param.DoubleParam;
import org.apache.spark.ml.param.IntParam;
import org.apache.spark.ml.param.ParamPair;
import org.apache.spark.ml.param.ParamValidators$;
import org.apache.spark.ml.param.shared.HasDistanceMeasure;
import org.apache.spark.ml.param.shared.HasFeaturesCol;
import org.apache.spark.ml.param.shared.HasMaxIter;
import org.apache.spark.ml.param.shared.HasPredictionCol;
import org.apache.spark.ml.param.shared.HasSeed;
import org.apache.spark.ml.param.shared.HasWeightCol;
import org.apache.spark.ml.util.SchemaUtils$;
import org.apache.spark.sql.types.StructType;
import org.apache.spark.sql.types.IntegerType.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u00194\u0001b\u0002\u0005\u0011\u0002\u0007\u0005\u0001B\u0005\u0005\u0006i\u0001!\tA\u000e\u0005\bu\u0001\u0011\r\u0011\"\u0002<\u0011\u0015A\u0005\u0001\"\u0001J\u0011\u001dq\u0005A1A\u0005\u0006=CQ\u0001\u0016\u0001\u0005\u0002UCQA\u0017\u0001\u0005\u0012m\u0013QCQ5tK\u000e$\u0018N\\4L\u001b\u0016\fgn\u001d)be\u0006l7O\u0003\u0002\n\u0015\u0005Q1\r\\;ti\u0016\u0014\u0018N\\4\u000b\u0005-a\u0011AA7m\u0015\tia\"A\u0003ta\u0006\u00148N\u0003\u0002\u0010!\u00051\u0011\r]1dQ\u0016T\u0011!E\u0001\u0004_J<7#\u0003\u0001\u00143})\u0003f\u000b\u00182!\t!r#D\u0001\u0016\u0015\u00051\u0012!B:dC2\f\u0017B\u0001\r\u0016\u0005\u0019\te.\u001f*fMB\u0011!$H\u0007\u00027)\u0011ADC\u0001\u0006a\u0006\u0014\u0018-\\\u0005\u0003=m\u0011a\u0001U1sC6\u001c\bC\u0001\u0011$\u001b\u0005\t#B\u0001\u0012\u001c\u0003\u0019\u0019\b.\u0019:fI&\u0011A%\t\u0002\u000b\u0011\u0006\u001cX*\u0019=Ji\u0016\u0014\bC\u0001\u0011'\u0013\t9\u0013E\u0001\bICN4U-\u0019;ve\u0016\u001c8i\u001c7\u0011\u0005\u0001J\u0013B\u0001\u0016\"\u0005\u001dA\u0015m]*fK\u0012\u0004\"\u0001\t\u0017\n\u00055\n#\u0001\u0005%bgB\u0013X\rZ5di&|gnQ8m!\t\u0001s&\u0003\u00021C\t\u0011\u0002*Y:ESN$\u0018M\\2f\u001b\u0016\f7/\u001e:f!\t\u0001#'\u0003\u00024C\ta\u0001*Y:XK&<\u0007\u000e^\"pY\u00061A%\u001b8ji\u0012\u001a\u0001\u0001F\u00018!\t!\u0002(\u0003\u0002:+\t!QK\\5u\u0003\u0005YW#\u0001\u001f\u0011\u0005ii\u0014B\u0001 \u001c\u0005!Ie\u000e\u001e)be\u0006l\u0007f\u0001\u0002A\rB\u0011\u0011\tR\u0007\u0002\u0005*\u00111\tD\u0001\u000bC:tw\u000e^1uS>t\u0017BA#C\u0005\u0015\u0019\u0016N\\2fC\u00059\u0015!\u0002\u001a/a9\u0002\u0014\u0001B4fi.+\u0012A\u0013\t\u0003)-K!\u0001T\u000b\u0003\u0007%sG\u000fK\u0002\u0004\u0001\u001a\u000bq#\\5o\t&4\u0018n]5cY\u0016\u001cE.^:uKJ\u001c\u0016N_3\u0016\u0003A\u0003\"AG)\n\u0005I[\"a\u0003#pk\ndW\rU1sC6D3\u0001\u0002!G\u0003i9W\r^'j]\u0012Kg/[:jE2,7\t\\;ti\u0016\u00148+\u001b>f+\u00051\u0006C\u0001\u000bX\u0013\tAVC\u0001\u0004E_V\u0014G.\u001a\u0015\u0004\u000b\u00013\u0015A\u0007<bY&$\u0017\r^3B]\u0012$&/\u00198tM>\u0014XnU2iK6\fGC\u0001/e!\ti&-D\u0001_\u0015\ty\u0006-A\u0003usB,7O\u0003\u0002b\u0019\u0005\u00191/\u001d7\n\u0005\rt&AC*ueV\u001cG\u000fV=qK\")QM\u0002a\u00019\u000611o\u00195f[\u0006\u0004"
)
public interface BisectingKMeansParams extends HasMaxIter, HasFeaturesCol, HasSeed, HasPredictionCol, HasDistanceMeasure, HasWeightCol {
   void org$apache$spark$ml$clustering$BisectingKMeansParams$_setter_$k_$eq(final IntParam x$1);

   void org$apache$spark$ml$clustering$BisectingKMeansParams$_setter_$minDivisibleClusterSize_$eq(final DoubleParam x$1);

   IntParam k();

   // $FF: synthetic method
   static int getK$(final BisectingKMeansParams $this) {
      return $this.getK();
   }

   default int getK() {
      return BoxesRunTime.unboxToInt(this.$(this.k()));
   }

   DoubleParam minDivisibleClusterSize();

   // $FF: synthetic method
   static double getMinDivisibleClusterSize$(final BisectingKMeansParams $this) {
      return $this.getMinDivisibleClusterSize();
   }

   default double getMinDivisibleClusterSize() {
      return BoxesRunTime.unboxToDouble(this.$(this.minDivisibleClusterSize()));
   }

   // $FF: synthetic method
   static StructType validateAndTransformSchema$(final BisectingKMeansParams $this, final StructType schema) {
      return $this.validateAndTransformSchema(schema);
   }

   default StructType validateAndTransformSchema(final StructType schema) {
      SchemaUtils$.MODULE$.validateVectorCompatibleColumn(schema, this.getFeaturesCol());
      return SchemaUtils$.MODULE$.appendColumn(schema, (String)this.$(this.predictionCol()), .MODULE$, SchemaUtils$.MODULE$.appendColumn$default$4());
   }

   static void $init$(final BisectingKMeansParams $this) {
      $this.org$apache$spark$ml$clustering$BisectingKMeansParams$_setter_$k_$eq(new IntParam($this, "k", "The desired number of leaf clusters. Must be > 1.", ParamValidators$.MODULE$.gt((double)1.0F)));
      $this.org$apache$spark$ml$clustering$BisectingKMeansParams$_setter_$minDivisibleClusterSize_$eq(new DoubleParam($this, "minDivisibleClusterSize", "The minimum number of points (if >= 1.0) or the minimum proportion of points (if < 1.0) of a divisible cluster.", ParamValidators$.MODULE$.gt((double)0.0F)));
      $this.setDefault(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new ParamPair[]{$this.k().$minus$greater(BoxesRunTime.boxToInteger(4)), $this.maxIter().$minus$greater(BoxesRunTime.boxToInteger(20)), $this.minDivisibleClusterSize().$minus$greater(BoxesRunTime.boxToDouble((double)1.0F))}));
   }
}
