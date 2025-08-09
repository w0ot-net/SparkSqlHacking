package org.apache.spark.mllib.feature;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple5;
import scala.None.;
import scala.runtime.AbstractFunction5;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class VocabWord$ extends AbstractFunction5 implements Serializable {
   public static final VocabWord$ MODULE$ = new VocabWord$();

   public final String toString() {
      return "VocabWord";
   }

   public VocabWord apply(final String word, final long cn, final int[] point, final int[] code, final int codeLen) {
      return new VocabWord(word, cn, point, code, codeLen);
   }

   public Option unapply(final VocabWord x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple5(x$0.word(), BoxesRunTime.boxToLong(x$0.cn()), x$0.point(), x$0.code(), BoxesRunTime.boxToInteger(x$0.codeLen()))));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(VocabWord$.class);
   }

   private VocabWord$() {
   }
}
