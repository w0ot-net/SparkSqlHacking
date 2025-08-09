package org.apache.spark.ml.classification;

import org.apache.spark.ml.PredictorParams;
import org.apache.spark.ml.param.DoubleParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamPair;
import org.apache.spark.ml.param.ParamValidators$;
import org.apache.spark.ml.param.shared.HasWeightCol;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u001d3\u0001BB\u0004\u0011\u0002\u0007\u0005q!\u0005\u0005\u0006I\u0001!\tA\n\u0005\bU\u0001\u0011\r\u0011\"\u0002,\u0011\u0015\u0001\u0004\u0001\"\u00022\u0011\u001d)\u0004A1A\u0005\u0006YBQ!\u0012\u0001\u0005\u0006\u0019\u0013\u0001CT1jm\u0016\u0014\u0015-_3t!\u0006\u0014\u0018-\\:\u000b\u0005!I\u0011AD2mCN\u001c\u0018NZ5dCRLwN\u001c\u0006\u0003\u0015-\t!!\u001c7\u000b\u00051i\u0011!B:qCJ\\'B\u0001\b\u0010\u0003\u0019\t\u0007/Y2iK*\t\u0001#A\u0002pe\u001e\u001cB\u0001\u0001\n\u00199A\u00111CF\u0007\u0002))\tQ#A\u0003tG\u0006d\u0017-\u0003\u0002\u0018)\t1\u0011I\\=SK\u001a\u0004\"!\u0007\u000e\u000e\u0003%I!aG\u0005\u0003\u001fA\u0013X\rZ5di>\u0014\b+\u0019:b[N\u0004\"!\b\u0012\u000e\u0003yQ!a\b\u0011\u0002\rMD\u0017M]3e\u0015\t\t\u0013\"A\u0003qCJ\fW.\u0003\u0002$=\ta\u0001*Y:XK&<\u0007\u000e^\"pY\u00061A%\u001b8ji\u0012\u001a\u0001\u0001F\u0001(!\t\u0019\u0002&\u0003\u0002*)\t!QK\\5u\u0003%\u0019Xn\\8uQ&tw-F\u0001-!\tic&D\u0001!\u0013\ty\u0003EA\u0006E_V\u0014G.\u001a)be\u0006l\u0017\u0001D4fiNkwn\u001c;iS:<W#\u0001\u001a\u0011\u0005M\u0019\u0014B\u0001\u001b\u0015\u0005\u0019!u.\u001e2mK\u0006IQn\u001c3fYRK\b/Z\u000b\u0002oA\u0019Q\u0006\u000f\u001e\n\u0005e\u0002#!\u0002)be\u0006l\u0007CA\u001eC\u001d\ta\u0004\t\u0005\u0002>)5\taH\u0003\u0002@K\u00051AH]8pizJ!!\u0011\u000b\u0002\rA\u0013X\rZ3g\u0013\t\u0019EI\u0001\u0004TiJLgn\u001a\u0006\u0003\u0003R\tAbZ3u\u001b>$W\r\u001c+za\u0016,\u0012A\u000f"
)
public interface NaiveBayesParams extends PredictorParams, HasWeightCol {
   void org$apache$spark$ml$classification$NaiveBayesParams$_setter_$smoothing_$eq(final DoubleParam x$1);

   void org$apache$spark$ml$classification$NaiveBayesParams$_setter_$modelType_$eq(final Param x$1);

   DoubleParam smoothing();

   // $FF: synthetic method
   static double getSmoothing$(final NaiveBayesParams $this) {
      return $this.getSmoothing();
   }

   default double getSmoothing() {
      return BoxesRunTime.unboxToDouble(this.$(this.smoothing()));
   }

   Param modelType();

   // $FF: synthetic method
   static String getModelType$(final NaiveBayesParams $this) {
      return $this.getModelType();
   }

   default String getModelType() {
      return (String)this.$(this.modelType());
   }

   static void $init$(final NaiveBayesParams $this) {
      $this.org$apache$spark$ml$classification$NaiveBayesParams$_setter_$smoothing_$eq(new DoubleParam($this, "smoothing", "The smoothing parameter.", ParamValidators$.MODULE$.gtEq((double)0.0F)));
      $this.org$apache$spark$ml$classification$NaiveBayesParams$_setter_$modelType_$eq(new Param($this, "modelType", "The model type which is a string (case-sensitive). Supported options: multinomial (default), complement, bernoulli and gaussian.", ParamValidators$.MODULE$.inArray(NaiveBayes$.MODULE$.supportedModelTypes().toArray(.MODULE$.apply(String.class))), .MODULE$.apply(String.class)));
      $this.setDefault(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new ParamPair[]{$this.smoothing().$minus$greater(BoxesRunTime.boxToDouble((double)1.0F)), $this.modelType().$minus$greater(NaiveBayes$.MODULE$.Multinomial())}));
   }
}
