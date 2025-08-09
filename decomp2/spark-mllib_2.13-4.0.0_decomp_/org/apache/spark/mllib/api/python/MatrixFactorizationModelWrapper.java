package org.apache.spark.mllib.api.python;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.mllib.linalg.Vectors$;
import org.apache.spark.mllib.recommendation.MatrixFactorizationModel;
import org.apache.spark.rdd.RDD;
import scala.MatchError;
import scala.Tuple2;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u000593Q\u0001C\u0005\u0001\u0013UA\u0001\u0002\b\u0001\u0003\u0002\u0003\u0006IA\u0006\u0005\u0006=\u0001!\ta\b\u0005\u0006G\u0001!\t\u0001\n\u0005\u0006\u0001\u0002!\t!\u0011\u0005\u0006\u0007\u0002!\t!\u0011\u0005\u0006\t\u0002!\t!\u0012\u0005\u0006\u0017\u0002!\t\u0001\u0014\u0002 \u001b\u0006$(/\u001b=GC\u000e$xN]5{CRLwN\\'pI\u0016dwK]1qa\u0016\u0014(B\u0001\u0006\f\u0003\u0019\u0001\u0018\u0010\u001e5p]*\u0011A\"D\u0001\u0004CBL'B\u0001\b\u0010\u0003\u0015iG\u000e\\5c\u0015\t\u0001\u0012#A\u0003ta\u0006\u00148N\u0003\u0002\u0013'\u00051\u0011\r]1dQ\u0016T\u0011\u0001F\u0001\u0004_J<7C\u0001\u0001\u0017!\t9\"$D\u0001\u0019\u0015\tIR\"\u0001\bsK\u000e|W.\\3oI\u0006$\u0018n\u001c8\n\u0005mA\"\u0001G'biJL\u0007PR1di>\u0014\u0018N_1uS>tWj\u001c3fY\u0006)Qn\u001c3fY\u000e\u0001\u0011A\u0002\u001fj]&$h\b\u0006\u0002!EA\u0011\u0011\u0005A\u0007\u0002\u0013!)AD\u0001a\u0001-\u00059\u0001O]3eS\u000e$HCA\u0013/!\r1\u0013fK\u0007\u0002O)\u0011\u0001fD\u0001\u0004e\u0012$\u0017B\u0001\u0016(\u0005\r\u0011F\t\u0012\t\u0003/1J!!\f\r\u0003\rI\u000bG/\u001b8h\u0011\u0015y3\u00011\u00011\u0003=)8/\u001a:B]\u0012\u0004&o\u001c3vGR\u001c\bcA\u00196o5\t!G\u0003\u00024i\u0005!!.\u0019<b\u0015\taq\"\u0003\u00027e\t9!*\u0019<b%\u0012#\u0005c\u0001\u001d<{5\t\u0011HC\u0001;\u0003\u0015\u00198-\u00197b\u0013\ta\u0014HA\u0003BeJ\f\u0017\u0010\u0005\u00029}%\u0011q(\u000f\u0002\u0004\u0003:L\u0018aD4fiV\u001bXM\u001d$fCR,(/Z:\u0016\u0003\t\u00032AJ\u00158\u0003I9W\r\u001e)s_\u0012,8\r\u001e$fCR,(/Z:\u0002A]\u0014\u0018\r\u001d9fIJ+7m\\7nK:$\u0007K]8ek\u000e$8OR8s+N,'o\u001d\u000b\u0003\u0005\u001aCQa\u0012\u0004A\u0002!\u000b1A\\;n!\tA\u0014*\u0003\u0002Ks\t\u0019\u0011J\u001c;\u0002A]\u0014\u0018\r\u001d9fIJ+7m\\7nK:$Wk]3sg\u001a{'\u000f\u0015:pIV\u001cGo\u001d\u000b\u0003\u00056CQaR\u0004A\u0002!\u0003"
)
public class MatrixFactorizationModelWrapper extends MatrixFactorizationModel {
   public RDD predict(final JavaRDD userAndProducts) {
      return this.predict(SerDe$.MODULE$.asTupleRDD(userAndProducts.rdd()));
   }

   public RDD getUserFeatures() {
      return SerDe$.MODULE$.fromTuple2RDD(this.userFeatures().map((x0$1) -> {
         if (x0$1 != null) {
            int user = x0$1._1$mcI$sp();
            double[] feature = (double[])x0$1._2();
            return new Tuple2(BoxesRunTime.boxToInteger(user), Vectors$.MODULE$.dense(feature));
         } else {
            throw new MatchError(x0$1);
         }
      }, .MODULE$.apply(Tuple2.class)));
   }

   public RDD getProductFeatures() {
      return SerDe$.MODULE$.fromTuple2RDD(this.productFeatures().map((x0$1) -> {
         if (x0$1 != null) {
            int product = x0$1._1$mcI$sp();
            double[] feature = (double[])x0$1._2();
            return new Tuple2(BoxesRunTime.boxToInteger(product), Vectors$.MODULE$.dense(feature));
         } else {
            throw new MatchError(x0$1);
         }
      }, .MODULE$.apply(Tuple2.class)));
   }

   public RDD wrappedRecommendProductsForUsers(final int num) {
      return SerDe$.MODULE$.fromTuple2RDD(this.recommendProductsForUsers(num));
   }

   public RDD wrappedRecommendUsersForProducts(final int num) {
      return SerDe$.MODULE$.fromTuple2RDD(this.recommendUsersForProducts(num));
   }

   public MatrixFactorizationModelWrapper(final MatrixFactorizationModel model) {
      super(model.rank(), model.userFeatures(), model.productFeatures());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
