package org.apache.spark.mllib.tree.model;

import java.io.Serializable;
import org.apache.spark.sql.Row;
import scala.Option;
import scala.Some;
import scala.Tuple4;
import scala.None.;
import scala.collection.immutable.Seq;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public class DecisionTreeModel$SaveLoadV1_0$SplitData$ implements Serializable {
   public static final DecisionTreeModel$SaveLoadV1_0$SplitData$ MODULE$ = new DecisionTreeModel$SaveLoadV1_0$SplitData$();

   public DecisionTreeModel$SaveLoadV1_0$SplitData apply(final Split s) {
      return new DecisionTreeModel$SaveLoadV1_0$SplitData(s.feature(), s.threshold(), s.featureType().id(), s.categories());
   }

   public DecisionTreeModel$SaveLoadV1_0$SplitData apply(final Row r) {
      return new DecisionTreeModel$SaveLoadV1_0$SplitData(r.getInt(0), r.getDouble(1), r.getInt(2), r.getSeq(3));
   }

   public DecisionTreeModel$SaveLoadV1_0$SplitData apply(final int feature, final double threshold, final int featureType, final Seq categories) {
      return new DecisionTreeModel$SaveLoadV1_0$SplitData(feature, threshold, featureType, categories);
   }

   public Option unapply(final DecisionTreeModel$SaveLoadV1_0$SplitData x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple4(BoxesRunTime.boxToInteger(x$0.feature()), BoxesRunTime.boxToDouble(x$0.threshold()), BoxesRunTime.boxToInteger(x$0.featureType()), x$0.categories())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(DecisionTreeModel$SaveLoadV1_0$SplitData$.class);
   }
}
