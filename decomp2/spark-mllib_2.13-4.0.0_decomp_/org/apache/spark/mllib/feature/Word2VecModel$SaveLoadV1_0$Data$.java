package org.apache.spark.mllib.feature;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.AbstractFunction2;
import scala.runtime.ModuleSerializationProxy;

public class Word2VecModel$SaveLoadV1_0$Data$ extends AbstractFunction2 implements Serializable {
   public static final Word2VecModel$SaveLoadV1_0$Data$ MODULE$ = new Word2VecModel$SaveLoadV1_0$Data$();

   public final String toString() {
      return "Data";
   }

   public Word2VecModel$SaveLoadV1_0$Data apply(final String word, final float[] vector) {
      return new Word2VecModel$SaveLoadV1_0$Data(word, vector);
   }

   public Option unapply(final Word2VecModel$SaveLoadV1_0$Data x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.word(), x$0.vector())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Word2VecModel$SaveLoadV1_0$Data$.class);
   }
}
