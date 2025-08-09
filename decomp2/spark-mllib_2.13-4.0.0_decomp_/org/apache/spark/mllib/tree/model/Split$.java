package org.apache.spark.mllib.tree.model;

import java.io.Serializable;
import scala.Enumeration;
import scala.Option;
import scala.Some;
import scala.Tuple4;
import scala.None.;
import scala.collection.immutable.List;
import scala.runtime.AbstractFunction4;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class Split$ extends AbstractFunction4 implements Serializable {
   public static final Split$ MODULE$ = new Split$();

   public final String toString() {
      return "Split";
   }

   public Split apply(final int feature, final double threshold, final Enumeration.Value featureType, final List categories) {
      return new Split(feature, threshold, featureType, categories);
   }

   public Option unapply(final Split x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple4(BoxesRunTime.boxToInteger(x$0.feature()), BoxesRunTime.boxToDouble(x$0.threshold()), x$0.featureType(), x$0.categories())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Split$.class);
   }

   private Split$() {
   }
}
