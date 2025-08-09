package org.apache.spark.ml.recommendation;

import java.io.Serializable;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Column.;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class ALSModel$ implements MLReadable, Serializable {
   public static final ALSModel$ MODULE$ = new ALSModel$();
   private static final String org$apache$spark$ml$recommendation$ALSModel$$NaN;
   private static final String org$apache$spark$ml$recommendation$ALSModel$$Drop;
   private static final String[] supportedColdStartStrategies;

   static {
      MLReadable.$init$(MODULE$);
      org$apache$spark$ml$recommendation$ALSModel$$NaN = "nan";
      org$apache$spark$ml$recommendation$ALSModel$$Drop = "drop";
      supportedColdStartStrategies = (String[])((Object[])(new String[]{MODULE$.org$apache$spark$ml$recommendation$ALSModel$$NaN(), MODULE$.org$apache$spark$ml$recommendation$ALSModel$$Drop()}));
   }

   public String org$apache$spark$ml$recommendation$ALSModel$$NaN() {
      return org$apache$spark$ml$recommendation$ALSModel$$NaN;
   }

   public String org$apache$spark$ml$recommendation$ALSModel$$Drop() {
      return org$apache$spark$ml$recommendation$ALSModel$$Drop;
   }

   public final String[] supportedColdStartStrategies() {
      return supportedColdStartStrategies;
   }

   public Column collect_top_k(final Column e, final int num, final boolean reverse) {
      return .MODULE$.internalFn("collect_top_k", scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{e, org.apache.spark.sql.functions..MODULE$.lit(BoxesRunTime.boxToInteger(num)), org.apache.spark.sql.functions..MODULE$.lit(BoxesRunTime.boxToBoolean(reverse))})));
   }

   public MLReader read() {
      return new ALSModel.ALSModelReader();
   }

   public ALSModel load(final String path) {
      return (ALSModel)MLReadable.load$(this, path);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ALSModel$.class);
   }

   private ALSModel$() {
   }
}
