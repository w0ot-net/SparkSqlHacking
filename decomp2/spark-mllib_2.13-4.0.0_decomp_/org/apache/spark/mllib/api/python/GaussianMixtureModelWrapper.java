package org.apache.spark.mllib.api.python;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkContext;
import org.apache.spark.mllib.clustering.GaussianMixtureModel;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors$;
import scala.collection.ArrayOps.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00014Qa\u0003\u0007\u0001\u0019aA\u0001b\b\u0001\u0003\u0002\u0003\u0006I!\t\u0005\u0006O\u0001!\t\u0001\u000b\u0005\bY\u0001\u0011\r\u0011\"\u0001.\u0011\u0019!\u0004\u0001)A\u0005]!9Q\u0007\u0001b\u0001\n\u00031\u0004B\u0002\u001e\u0001A\u0003%q\u0007C\u0004<\u0001\t\u0007I\u0011\u0001\u001f\t\r\r\u0003\u0001\u0015!\u0003>\u0011\u0015!\u0005\u0001\"\u0001F\u0011\u0015A\u0005\u0001\"\u0001J\u0005m9\u0015-^:tS\u0006tW*\u001b=ukJ,Wj\u001c3fY^\u0013\u0018\r\u001d9fe*\u0011QBD\u0001\u0007af$\bn\u001c8\u000b\u0005=\u0001\u0012aA1qS*\u0011\u0011CE\u0001\u0006[2d\u0017N\u0019\u0006\u0003'Q\tQa\u001d9be.T!!\u0006\f\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u00059\u0012aA8sON\u0011\u0001!\u0007\t\u00035ui\u0011a\u0007\u0006\u00029\u0005)1oY1mC&\u0011ad\u0007\u0002\u0007\u0003:L(+\u001a4\u0002\u000b5|G-\u001a7\u0004\u0001A\u0011!%J\u0007\u0002G)\u0011A\u0005E\u0001\u000bG2,8\u000f^3sS:<\u0017B\u0001\u0014$\u0005Q9\u0015-^:tS\u0006tW*\u001b=ukJ,Wj\u001c3fY\u00061A(\u001b8jiz\"\"!K\u0016\u0011\u0005)\u0002Q\"\u0001\u0007\t\u000b}\u0011\u0001\u0019A\u0011\u0002\u000f],\u0017n\u001a5ugV\ta\u0006\u0005\u00020e5\t\u0001G\u0003\u00022!\u00051A.\u001b8bY\u001eL!a\r\u0019\u0003\rY+7\r^8s\u0003!9X-[4iiN\u0004\u0013!A6\u0016\u0003]\u0002\"A\u0007\u001d\n\u0005eZ\"aA%oi\u0006\u00111\u000eI\u0001\nO\u0006,8o]5b]N,\u0012!\u0010\t\u00045y\u0002\u0015BA \u001c\u0005\u0015\t%O]1z!\tQ\u0012)\u0003\u0002C7\t!!)\u001f;f\u0003)9\u0017-^:tS\u0006t7\u000fI\u0001\faJ,G-[2u'>4G\u000f\u0006\u0002/\r\")q)\u0003a\u0001]\u0005)\u0001o\\5oi\u0006!1/\u0019<f)\rQUj\u0015\t\u00035-K!\u0001T\u000e\u0003\tUs\u0017\u000e\u001e\u0005\u0006\u001d*\u0001\raT\u0001\u0003g\u000e\u0004\"\u0001U)\u000e\u0003II!A\u0015\n\u0003\u0019M\u0003\u0018M]6D_:$X\r\u001f;\t\u000bQS\u0001\u0019A+\u0002\tA\fG\u000f\u001b\t\u0003-vs!aV.\u0011\u0005a[R\"A-\u000b\u0005i\u0003\u0013A\u0002\u001fs_>$h(\u0003\u0002]7\u00051\u0001K]3eK\u001aL!AX0\u0003\rM#(/\u001b8h\u0015\ta6\u0004"
)
public class GaussianMixtureModelWrapper {
   private final GaussianMixtureModel model;
   private final Vector weights;
   private final int k;
   private final byte[] gaussians;

   public Vector weights() {
      return this.weights;
   }

   public int k() {
      return this.k;
   }

   public byte[] gaussians() {
      return this.gaussians;
   }

   public Vector predictSoft(final Vector point) {
      return Vectors$.MODULE$.dense(this.model.predictSoft(point));
   }

   public void save(final SparkContext sc, final String path) {
      this.model.save(sc, path);
   }

   public GaussianMixtureModelWrapper(final GaussianMixtureModel model) {
      this.model = model;
      this.weights = Vectors$.MODULE$.dense(model.weights());
      this.k = this.weights().size();
      Object[][] modelGaussians = .MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(model.gaussians()), (gaussian) -> new Object[]{gaussian.mu(), gaussian.sigma()}, scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Object.class)));
      this.gaussians = SerDe$.MODULE$.dumps(scala.jdk.CollectionConverters..MODULE$.SeqHasAsJava(org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(modelGaussians).toImmutableArraySeq()).asJava());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
