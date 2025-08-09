package org.apache.spark.mllib.clustering;

import java.io.Serializable;
import org.apache.spark.mllib.linalg.Vector;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.AbstractFunction2;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public class DistributedLDAModel$SaveLoadV1_0$VertexData$ extends AbstractFunction2 implements Serializable {
   public static final DistributedLDAModel$SaveLoadV1_0$VertexData$ MODULE$ = new DistributedLDAModel$SaveLoadV1_0$VertexData$();

   public final String toString() {
      return "VertexData";
   }

   public DistributedLDAModel$SaveLoadV1_0$VertexData apply(final long id, final Vector topicWeights) {
      return new DistributedLDAModel$SaveLoadV1_0$VertexData(id, topicWeights);
   }

   public Option unapply(final DistributedLDAModel$SaveLoadV1_0$VertexData x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(BoxesRunTime.boxToLong(x$0.id()), x$0.topicWeights())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(DistributedLDAModel$SaveLoadV1_0$VertexData$.class);
   }
}
