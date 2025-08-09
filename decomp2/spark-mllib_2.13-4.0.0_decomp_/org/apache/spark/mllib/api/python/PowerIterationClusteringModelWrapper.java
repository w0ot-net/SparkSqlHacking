package org.apache.spark.mllib.api.python;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.mllib.clustering.PowerIterationClusteringModel;
import org.apache.spark.rdd.RDD;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005A2Q\u0001B\u0003\u0001\u000bEA\u0001\u0002\u0007\u0001\u0003\u0002\u0003\u0006IA\u0005\u0005\u00065\u0001!\ta\u0007\u0005\u0006?\u0001!\t\u0001\t\u0002%!><XM]%uKJ\fG/[8o\u00072,8\u000f^3sS:<Wj\u001c3fY^\u0013\u0018\r\u001d9fe*\u0011aaB\u0001\u0007af$\bn\u001c8\u000b\u0005!I\u0011aA1qS*\u0011!bC\u0001\u0006[2d\u0017N\u0019\u0006\u0003\u00195\tQa\u001d9be.T!AD\b\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\u0001\u0012aA8sON\u0011\u0001A\u0005\t\u0003'Yi\u0011\u0001\u0006\u0006\u0003+%\t!b\u00197vgR,'/\u001b8h\u0013\t9BCA\u000fQ_^,'/\u0013;fe\u0006$\u0018n\u001c8DYV\u001cH/\u001a:j]\u001elu\u000eZ3m\u0003\u0015iw\u000eZ3m\u0007\u0001\ta\u0001P5oSRtDC\u0001\u000f\u001f!\ti\u0002!D\u0001\u0006\u0011\u0015A\"\u00011\u0001\u0013\u000399W\r^!tg&<g.\\3oiN,\u0012!\t\t\u0004E\u0015:S\"A\u0012\u000b\u0005\u0011Z\u0011a\u0001:eI&\u0011ae\t\u0002\u0004%\u0012#\u0005c\u0001\u0015,[5\t\u0011FC\u0001+\u0003\u0015\u00198-\u00197b\u0013\ta\u0013FA\u0003BeJ\f\u0017\u0010\u0005\u0002)]%\u0011q&\u000b\u0002\u0004\u0003:L\b"
)
public class PowerIterationClusteringModelWrapper extends PowerIterationClusteringModel {
   private final PowerIterationClusteringModel model;

   public RDD getAssignments() {
      return this.model.assignments().map((x) -> new Object[]{BoxesRunTime.boxToLong(x.id()), BoxesRunTime.boxToInteger(x.cluster())}, .MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Object.class)));
   }

   public PowerIterationClusteringModelWrapper(final PowerIterationClusteringModel model) {
      super(model.k(), model.assignments());
      this.model = model;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
