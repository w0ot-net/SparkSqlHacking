package org.apache.spark.ml.ann;

import breeze.linalg.DenseVector;
import java.util.Random;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005m3Qa\u0003\u0007\u0001\u0019YA\u0001\"\t\u0001\u0003\u0006\u0004%\ta\t\u0005\tO\u0001\u0011\t\u0011)A\u0005I!)\u0001\u0006\u0001C\u0001S!9A\u0006\u0001b\u0001\n\u0003j\u0003BB\u0019\u0001A\u0003%a\u0006C\u00033\u0001\u0011\u00053\u0007C\u00047\u0001\t\u0007I\u0011I\u001c\t\rm\u0002\u0001\u0015!\u00039\u0011\u0015a\u0004\u0001\"\u0011>\u0011\u0015q\u0005\u0001\"\u0011P\u0005=1UO\\2uS>t\u0017\r\u001c'bs\u0016\u0014(BA\u0007\u000f\u0003\r\tgN\u001c\u0006\u0003\u001fA\t!!\u001c7\u000b\u0005E\u0011\u0012!B:qCJ\\'BA\n\u0015\u0003\u0019\t\u0007/Y2iK*\tQ#A\u0002pe\u001e\u001c2\u0001A\f\u001e!\tA2$D\u0001\u001a\u0015\u0005Q\u0012!B:dC2\f\u0017B\u0001\u000f\u001a\u0005\u0019\te.\u001f*fMB\u0011adH\u0007\u0002\u0019%\u0011\u0001\u0005\u0004\u0002\u0006\u0019\u0006LXM]\u0001\u0013C\u000e$\u0018N^1uS>tg)\u001e8di&|gn\u0001\u0001\u0016\u0003\u0011\u0002\"AH\u0013\n\u0005\u0019b!AE!di&4\u0018\r^5p]\u001a+hn\u0019;j_:\f1#Y2uSZ\fG/[8o\rVt7\r^5p]\u0002\na\u0001P5oSRtDC\u0001\u0016,!\tq\u0002\u0001C\u0003\"\u0007\u0001\u0007A%\u0001\u0006xK&<\u0007\u000e^*ju\u0016,\u0012A\f\t\u00031=J!\u0001M\r\u0003\u0007%sG/A\u0006xK&<\u0007\u000e^*ju\u0016\u0004\u0013!D4fi>+H\u000f];u'&TX\r\u0006\u0002/i!)QG\u0002a\u0001]\u0005I\u0011N\u001c9viNK'0Z\u0001\bS:\u0004F.Y2f+\u0005A\u0004C\u0001\r:\u0013\tQ\u0014DA\u0004C_>dW-\u00198\u0002\u0011%t\u0007\u000b\\1dK\u0002\n1b\u0019:fCR,Wj\u001c3fYR\u0011a(\u0011\t\u0003=}J!\u0001\u0011\u0007\u0003\u00151\u000b\u00170\u001a:N_\u0012,G\u000eC\u0003C\u0013\u0001\u00071)A\u0004xK&<\u0007\u000e^:\u0011\u0007\u0011K5*D\u0001F\u0015\t1u)\u0001\u0004mS:\fGn\u001a\u0006\u0002\u0011\u00061!M]3fu\u0016L!AS#\u0003\u0017\u0011+gn]3WK\u000e$xN\u001d\t\u000311K!!T\r\u0003\r\u0011{WO\u00197f\u0003%Ig.\u001b;N_\u0012,G\u000eF\u0002?!FCQA\u0011\u0006A\u0002\rCQA\u0015\u0006A\u0002M\u000baA]1oI>l\u0007C\u0001+Z\u001b\u0005)&B\u0001,X\u0003\u0011)H/\u001b7\u000b\u0003a\u000bAA[1wC&\u0011!,\u0016\u0002\u0007%\u0006tGm\\7"
)
public class FunctionalLayer implements Layer {
   private final ActivationFunction activationFunction;
   private final int weightSize;
   private final boolean inPlace;

   public ActivationFunction activationFunction() {
      return this.activationFunction;
   }

   public int weightSize() {
      return this.weightSize;
   }

   public int getOutputSize(final int inputSize) {
      return inputSize;
   }

   public boolean inPlace() {
      return this.inPlace;
   }

   public LayerModel createModel(final DenseVector weights) {
      return new FunctionalLayerModel(this);
   }

   public LayerModel initModel(final DenseVector weights, final Random random) {
      return this.createModel(weights);
   }

   public FunctionalLayer(final ActivationFunction activationFunction) {
      this.activationFunction = activationFunction;
      this.weightSize = 0;
      this.inPlace = true;
   }
}
