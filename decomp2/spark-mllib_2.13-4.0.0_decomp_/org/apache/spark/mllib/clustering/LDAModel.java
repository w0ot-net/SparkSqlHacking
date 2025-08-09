package org.apache.spark.mllib.clustering;

import org.apache.spark.mllib.linalg.Matrix;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.util.Saveable;
import scala.Tuple2;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005}3QAC\u0006\u0002\u0002YAaa\t\u0001\u0005\u0002-!\u0003\"B\u0014\u0001\r\u0003A\u0003\"B\u001b\u0001\r\u0003A\u0003\"B\u001c\u0001\r\u0003A\u0004\"\u0002\"\u0001\r\u0003\u0019\u0005\"\u0002%\u0001\r#\u0019\u0005\"B%\u0001\r\u0003Q\u0005\"B(\u0001\r\u0003\u0001\u0006\"B(\u0001\t\u0003a&\u0001\u0003'E\u00036{G-\u001a7\u000b\u00051i\u0011AC2mkN$XM]5oO*\u0011abD\u0001\u0006[2d\u0017N\u0019\u0006\u0003!E\tQa\u001d9be.T!AE\n\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005!\u0012aA8sO\u000e\u00011c\u0001\u0001\u0018;A\u0011\u0001dG\u0007\u00023)\t!$A\u0003tG\u0006d\u0017-\u0003\u0002\u001d3\t1\u0011I\\=SK\u001a\u0004\"AH\u0011\u000e\u0003}Q!\u0001I\u0007\u0002\tU$\u0018\u000e\\\u0005\u0003E}\u0011\u0001bU1wK\u0006\u0014G.Z\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003\u0015\u0002\"A\n\u0001\u000e\u0003-\t\u0011a[\u000b\u0002SA\u0011\u0001DK\u0005\u0003We\u00111!\u00138uQ\r\u0011Qf\r\t\u0003]Ej\u0011a\f\u0006\u0003a=\t!\"\u00198o_R\fG/[8o\u0013\t\u0011tFA\u0003TS:\u001cW-I\u00015\u0003\u0015\tdf\r\u00181\u0003%1xnY1c'&TX\rK\u0002\u0004[M\n\u0001\u0003Z8d\u0007>t7-\u001a8ue\u0006$\u0018n\u001c8\u0016\u0003e\u0002\"AO\u001f\u000e\u0003mR!\u0001P\u0007\u0002\r1Lg.\u00197h\u0013\tq4H\u0001\u0004WK\u000e$xN\u001d\u0015\u0004\t5\u0002\u0015%A!\u0002\u000bErSG\f\u0019\u0002%Q|\u0007/[2D_:\u001cWM\u001c;sCRLwN\\\u000b\u0002\tB\u0011\u0001$R\u0005\u0003\rf\u0011a\u0001R8vE2,\u0007fA\u0003.\u0001\u0006Qq-Y7nCNC\u0017\r]3\u0002\u0019Q|\u0007/[2t\u001b\u0006$(/\u001b=\u0016\u0003-\u0003\"A\u000f'\n\u00055[$AB'biJL\u0007\u0010K\u0002\b[M\na\u0002Z3tGJL'-\u001a+pa&\u001c7\u000f\u0006\u0002R3B\u0019\u0001D\u0015+\n\u0005MK\"!B!se\u0006L\b\u0003\u0002\rV/bK!AV\r\u0003\rQ+\b\u000f\\33!\rA\"+\u000b\t\u00041I#\u0005\"\u0002.\t\u0001\u0004I\u0013\u0001E7bqR+'/\\:QKJ$v\u000e]5dQ\rAQf\r\u000b\u0002#\"\u001a\u0011\"L\u001a)\u0007\u0001i3\u0007"
)
public abstract class LDAModel implements Saveable {
   public abstract int k();

   public abstract int vocabSize();

   public abstract Vector docConcentration();

   public abstract double topicConcentration();

   public abstract double gammaShape();

   public abstract Matrix topicsMatrix();

   public abstract Tuple2[] describeTopics(final int maxTermsPerTopic);

   public Tuple2[] describeTopics() {
      return this.describeTopics(this.vocabSize());
   }
}
