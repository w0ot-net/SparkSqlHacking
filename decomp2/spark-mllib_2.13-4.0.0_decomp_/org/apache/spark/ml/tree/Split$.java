package org.apache.spark.ml.tree;

import java.io.Serializable;
import org.apache.spark.mllib.tree.configuration.FeatureType$;
import scala.Enumeration;
import scala.MatchError;
import scala.collection.immutable.Map;
import scala.reflect.ClassTag.;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class Split$ implements Serializable {
   public static final Split$ MODULE$ = new Split$();

   public Split fromOld(final org.apache.spark.mllib.tree.model.Split oldSplit, final Map categoricalFeatures) {
      Enumeration.Value var4 = oldSplit.featureType();
      Enumeration.Value var10000 = FeatureType$.MODULE$.Categorical();
      if (var10000 == null) {
         if (var4 == null) {
            return new CategoricalSplit(oldSplit.feature(), (double[])oldSplit.categories().toArray(.MODULE$.Double()), BoxesRunTime.unboxToInt(categoricalFeatures.apply(BoxesRunTime.boxToInteger(oldSplit.feature()))));
         }
      } else if (var10000.equals(var4)) {
         return new CategoricalSplit(oldSplit.feature(), (double[])oldSplit.categories().toArray(.MODULE$.Double()), BoxesRunTime.unboxToInt(categoricalFeatures.apply(BoxesRunTime.boxToInteger(oldSplit.feature()))));
      }

      var10000 = FeatureType$.MODULE$.Continuous();
      if (var10000 == null) {
         if (var4 == null) {
            return new ContinuousSplit(oldSplit.feature(), oldSplit.threshold());
         }
      } else if (var10000.equals(var4)) {
         return new ContinuousSplit(oldSplit.feature(), oldSplit.threshold());
      }

      throw new MatchError(var4);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Split$.class);
   }

   private Split$() {
   }
}
