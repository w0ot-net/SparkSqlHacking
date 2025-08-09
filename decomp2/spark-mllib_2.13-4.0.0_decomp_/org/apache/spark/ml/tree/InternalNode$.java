package org.apache.spark.ml.tree;

import java.io.Serializable;
import scala.MatchError;
import scala.Predef.;
import scala.runtime.ModuleSerializationProxy;

public final class InternalNode$ implements Serializable {
   public static final InternalNode$ MODULE$ = new InternalNode$();

   public String org$apache$spark$ml$tree$InternalNode$$splitToString(final Split split, final boolean left) {
      String featureStr = "feature " + split.featureIndex();
      if (split instanceof ContinuousSplit var6) {
         return left ? featureStr + " <= " + var6.threshold() : featureStr + " > " + var6.threshold();
      } else if (split instanceof CategoricalSplit var7) {
         String categoriesStr = .MODULE$.wrapDoubleArray(var7.leftCategories()).mkString("{", ",", "}");
         return left ? featureStr + " in " + categoriesStr : featureStr + " not in " + categoriesStr;
      } else {
         throw new MatchError(split);
      }
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(InternalNode$.class);
   }

   private InternalNode$() {
   }
}
