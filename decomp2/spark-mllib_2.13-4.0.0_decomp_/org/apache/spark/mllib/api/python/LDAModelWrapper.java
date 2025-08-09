package org.apache.spark.mllib.api.python;

import java.lang.invoke.SerializedLambda;
import java.util.List;
import org.apache.spark.SparkContext;
import org.apache.spark.mllib.clustering.LDAModel;
import org.apache.spark.mllib.linalg.Matrix;
import scala.MatchError;
import scala.collection.ArrayOps.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005e3Q\u0001C\u0005\u0001\u0013UA\u0001\u0002\b\u0001\u0003\u0002\u0003\u0006IA\b\u0005\u0006I\u0001!\t!\n\u0005\u0006S\u0001!\tA\u000b\u0005\u0006c\u0001!\tA\r\u0005\u0006m\u0001!\ta\u000e\u0005\u0006m\u0001!\tA\u0010\u0005\u0006\u0003\u0002!\tA\u0011\u0002\u0010\u0019\u0012\u000bUj\u001c3fY^\u0013\u0018\r\u001d9fe*\u0011!bC\u0001\u0007af$\bn\u001c8\u000b\u00051i\u0011aA1qS*\u0011abD\u0001\u0006[2d\u0017N\u0019\u0006\u0003!E\tQa\u001d9be.T!AE\n\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005!\u0012aA8sON\u0011\u0001A\u0006\t\u0003/ii\u0011\u0001\u0007\u0006\u00023\u0005)1oY1mC&\u00111\u0004\u0007\u0002\u0007\u0003:L(+\u001a4\u0002\u000b5|G-\u001a7\u0004\u0001A\u0011qDI\u0007\u0002A)\u0011\u0011%D\u0001\u000bG2,8\u000f^3sS:<\u0017BA\u0012!\u0005!aE)Q'pI\u0016d\u0017A\u0002\u001fj]&$h\b\u0006\u0002'QA\u0011q\u0005A\u0007\u0002\u0013!)AD\u0001a\u0001=\u0005aAo\u001c9jGNl\u0015\r\u001e:jqR\t1\u0006\u0005\u0002-_5\tQF\u0003\u0002/\u001b\u00051A.\u001b8bY\u001eL!\u0001M\u0017\u0003\r5\u000bGO]5y\u0003%1xnY1c'&TX\rF\u00014!\t9B'\u0003\u000261\t\u0019\u0011J\u001c;\u0002\u001d\u0011,7o\u0019:jE\u0016$v\u000e]5dgR\t\u0001\bE\u0002\u0018smJ!A\u000f\r\u0003\u000b\u0005\u0013(/Y=\u0011\u0005]a\u0014BA\u001f\u0019\u0005\u0011\u0011\u0015\u0010^3\u0015\u0005az\u0004\"\u0002!\u0007\u0001\u0004\u0019\u0014\u0001E7bqR+'/\\:QKJ$v\u000e]5d\u0003\u0011\u0019\u0018M^3\u0015\u0007\r3E\n\u0005\u0002\u0018\t&\u0011Q\t\u0007\u0002\u0005+:LG\u000fC\u0003H\u000f\u0001\u0007\u0001*\u0001\u0002tGB\u0011\u0011JS\u0007\u0002\u001f%\u00111j\u0004\u0002\r'B\f'o[\"p]R,\u0007\u0010\u001e\u0005\u0006\u001b\u001e\u0001\rAT\u0001\u0005a\u0006$\b\u000e\u0005\u0002P-:\u0011\u0001\u000b\u0016\t\u0003#bi\u0011A\u0015\u0006\u0003'v\ta\u0001\u0010:p_Rt\u0014BA+\u0019\u0003\u0019\u0001&/\u001a3fM&\u0011q\u000b\u0017\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005UC\u0002"
)
public class LDAModelWrapper {
   private final LDAModel model;

   public Matrix topicsMatrix() {
      return this.model.topicsMatrix();
   }

   public int vocabSize() {
      return this.model.vocabSize();
   }

   public byte[] describeTopics() {
      return this.describeTopics(this.model.vocabSize());
   }

   public byte[] describeTopics(final int maxTermsPerTopic) {
      Object[][] topics = .MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])this.model.describeTopics(maxTermsPerTopic)), (x0$1) -> {
         if (x0$1 != null) {
            int[] terms = (int[])x0$1._1();
            double[] termWeights = (double[])x0$1._2();
            List jTerms = scala.jdk.CollectionConverters..MODULE$.SeqHasAsJava(org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(terms).toImmutableArraySeq()).asJava();
            List jTermWeights = scala.jdk.CollectionConverters..MODULE$.SeqHasAsJava(org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(termWeights).toImmutableArraySeq()).asJava();
            return new Object[]{jTerms, jTermWeights};
         } else {
            throw new MatchError(x0$1);
         }
      }, scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Object.class)));
      return SerDe$.MODULE$.dumps(scala.jdk.CollectionConverters..MODULE$.SeqHasAsJava(org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(topics).toImmutableArraySeq()).asJava());
   }

   public void save(final SparkContext sc, final String path) {
      this.model.save(sc, path);
   }

   public LDAModelWrapper(final LDAModel model) {
      this.model = model;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
