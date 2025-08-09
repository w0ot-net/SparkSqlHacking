package org.apache.spark.mllib.tree.model;

import java.io.Serializable;
import org.apache.spark.sql.Row;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.ModuleSerializationProxy;

public class DecisionTreeModel$SaveLoadV1_0$PredictData$ implements Serializable {
   public static final DecisionTreeModel$SaveLoadV1_0$PredictData$ MODULE$ = new DecisionTreeModel$SaveLoadV1_0$PredictData$();

   public DecisionTreeModel$SaveLoadV1_0$PredictData apply(final Predict p) {
      return new DecisionTreeModel$SaveLoadV1_0$PredictData(p.predict(), p.prob());
   }

   public DecisionTreeModel$SaveLoadV1_0$PredictData apply(final Row r) {
      return new DecisionTreeModel$SaveLoadV1_0$PredictData(r.getDouble(0), r.getDouble(1));
   }

   public DecisionTreeModel$SaveLoadV1_0$PredictData apply(final double predict, final double prob) {
      return new DecisionTreeModel$SaveLoadV1_0$PredictData(predict, prob);
   }

   public Option unapply(final DecisionTreeModel$SaveLoadV1_0$PredictData x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2.mcDD.sp(x$0.predict(), x$0.prob())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(DecisionTreeModel$SaveLoadV1_0$PredictData$.class);
   }
}
