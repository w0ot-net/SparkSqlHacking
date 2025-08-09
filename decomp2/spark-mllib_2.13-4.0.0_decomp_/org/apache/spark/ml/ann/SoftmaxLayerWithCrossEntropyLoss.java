package org.apache.spark.ml.ann;

import breeze.linalg.DenseVector;
import java.util.Random;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005I3Q!\u0003\u0006\u0001\u0015QAQa\b\u0001\u0005\u0002\u0005Bqa\t\u0001C\u0002\u0013\u0005C\u0005\u0003\u0004)\u0001\u0001\u0006I!\n\u0005\bS\u0001\u0011\r\u0011\"\u0011+\u0011\u0019q\u0003\u0001)A\u0005W!)q\u0006\u0001C!a!)1\u0007\u0001C!i!)Q\t\u0001C!\r\n\u00013k\u001c4u[\u0006DH*Y=fe^KG\u000f[\"s_N\u001cXI\u001c;s_BLHj\\:t\u0015\tYA\"A\u0002b]:T!!\u0004\b\u0002\u00055d'BA\b\u0011\u0003\u0015\u0019\b/\u0019:l\u0015\t\t\"#\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002'\u0005\u0019qN]4\u0014\u0007\u0001)2\u0004\u0005\u0002\u001735\tqCC\u0001\u0019\u0003\u0015\u00198-\u00197b\u0013\tQrC\u0001\u0004B]f\u0014VM\u001a\t\u00039ui\u0011AC\u0005\u0003=)\u0011Q\u0001T1zKJ\fa\u0001P5oSRt4\u0001\u0001\u000b\u0002EA\u0011A\u0004A\u0001\u000bo\u0016Lw\r\u001b;TSj,W#A\u0013\u0011\u0005Y1\u0013BA\u0014\u0018\u0005\rIe\u000e^\u0001\fo\u0016Lw\r\u001b;TSj,\u0007%A\u0004j]Bc\u0017mY3\u0016\u0003-\u0002\"A\u0006\u0017\n\u00055:\"a\u0002\"p_2,\u0017M\\\u0001\tS:\u0004F.Y2fA\u0005iq-\u001a;PkR\u0004X\u000f^*ju\u0016$\"!J\u0019\t\u000bI2\u0001\u0019A\u0013\u0002\u0013%t\u0007/\u001e;TSj,\u0017aC2sK\u0006$X-T8eK2$\"!\u000e\u001d\u0011\u0005q1\u0014BA\u001c\u000b\u0005)a\u0015-_3s\u001b>$W\r\u001c\u0005\u0006s\u001d\u0001\rAO\u0001\bo\u0016Lw\r\u001b;t!\rY\u0004IQ\u0007\u0002y)\u0011QHP\u0001\u0007Y&t\u0017\r\\4\u000b\u0003}\naA\u0019:fKj,\u0017BA!=\u0005-!UM\\:f-\u0016\u001cGo\u001c:\u0011\u0005Y\u0019\u0015B\u0001#\u0018\u0005\u0019!u.\u001e2mK\u0006I\u0011N\\5u\u001b>$W\r\u001c\u000b\u0004k\u001dC\u0005\"B\u001d\t\u0001\u0004Q\u0004\"B%\t\u0001\u0004Q\u0015A\u0002:b]\u0012|W\u000e\u0005\u0002L!6\tAJ\u0003\u0002N\u001d\u0006!Q\u000f^5m\u0015\u0005y\u0015\u0001\u00026bm\u0006L!!\u0015'\u0003\rI\u000bg\u000eZ8n\u0001"
)
public class SoftmaxLayerWithCrossEntropyLoss implements Layer {
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
      return new SoftmaxLayerModelWithCrossEntropyLoss();
   }

   public LayerModel initModel(final DenseVector weights, final Random random) {
      return new SoftmaxLayerModelWithCrossEntropyLoss();
   }
}
