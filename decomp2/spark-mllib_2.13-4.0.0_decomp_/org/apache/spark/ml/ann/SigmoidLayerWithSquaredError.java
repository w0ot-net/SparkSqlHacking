package org.apache.spark.ml.ann;

import breeze.linalg.DenseVector;
import java.util.Random;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005I3Q!\u0003\u0006\u0001\u0015QAQa\b\u0001\u0005\u0002\u0005Bqa\t\u0001C\u0002\u0013\u0005C\u0005\u0003\u0004)\u0001\u0001\u0006I!\n\u0005\bS\u0001\u0011\r\u0011\"\u0011+\u0011\u0019q\u0003\u0001)A\u0005W!)q\u0006\u0001C!a!)1\u0007\u0001C!i!)Q\t\u0001C!\r\na2+[4n_&$G*Y=fe^KG\u000f[*rk\u0006\u0014X\rZ#se>\u0014(BA\u0006\r\u0003\r\tgN\u001c\u0006\u0003\u001b9\t!!\u001c7\u000b\u0005=\u0001\u0012!B:qCJ\\'BA\t\u0013\u0003\u0019\t\u0007/Y2iK*\t1#A\u0002pe\u001e\u001c2\u0001A\u000b\u001c!\t1\u0012$D\u0001\u0018\u0015\u0005A\u0012!B:dC2\f\u0017B\u0001\u000e\u0018\u0005\u0019\te.\u001f*fMB\u0011A$H\u0007\u0002\u0015%\u0011aD\u0003\u0002\u0006\u0019\u0006LXM]\u0001\u0007y%t\u0017\u000e\u001e \u0004\u0001Q\t!\u0005\u0005\u0002\u001d\u0001\u0005Qq/Z5hQR\u001c\u0016N_3\u0016\u0003\u0015\u0002\"A\u0006\u0014\n\u0005\u001d:\"aA%oi\u0006Yq/Z5hQR\u001c\u0016N_3!\u0003\u001dIg\u000e\u00157bG\u0016,\u0012a\u000b\t\u0003-1J!!L\f\u0003\u000f\t{w\u000e\\3b]\u0006A\u0011N\u001c)mC\u000e,\u0007%A\u0007hKR|U\u000f\u001e9viNK'0\u001a\u000b\u0003KEBQA\r\u0004A\u0002\u0015\n\u0011\"\u001b8qkR\u001c\u0016N_3\u0002\u0017\r\u0014X-\u0019;f\u001b>$W\r\u001c\u000b\u0003ka\u0002\"\u0001\b\u001c\n\u0005]R!A\u0003'bs\u0016\u0014Xj\u001c3fY\")\u0011h\u0002a\u0001u\u00059q/Z5hQR\u001c\bcA\u001eA\u00056\tAH\u0003\u0002>}\u00051A.\u001b8bY\u001eT\u0011aP\u0001\u0007EJ,WM_3\n\u0005\u0005c$a\u0003#f]N,g+Z2u_J\u0004\"AF\"\n\u0005\u0011;\"A\u0002#pk\ndW-A\u0005j]&$Xj\u001c3fYR\u0019Qg\u0012%\t\u000beB\u0001\u0019\u0001\u001e\t\u000b%C\u0001\u0019\u0001&\u0002\rI\fg\u000eZ8n!\tY\u0005+D\u0001M\u0015\tie*\u0001\u0003vi&d'\"A(\u0002\t)\fg/Y\u0005\u0003#2\u0013aAU1oI>l\u0007"
)
public class SigmoidLayerWithSquaredError implements Layer {
   private final int weightSize = 0;
   private final boolean inPlace = true;

   public int weightSize() {
      return this.weightSize;
   }

   public boolean inPlace() {
      return this.inPlace;
   }

   public int getOutputSize(final int inputSize) {
      return inputSize;
   }

   public LayerModel createModel(final DenseVector weights) {
      return new SigmoidLayerModelWithSquaredError();
   }

   public LayerModel initModel(final DenseVector weights, final Random random) {
      return new SigmoidLayerModelWithSquaredError();
   }
}
