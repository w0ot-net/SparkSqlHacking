package org.apache.spark.mllib.linalg.distributed;

import java.io.Serializable;
import org.apache.spark.mllib.linalg.Vector;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.AbstractFunction2;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class IndexedRow$ extends AbstractFunction2 implements Serializable {
   public static final IndexedRow$ MODULE$ = new IndexedRow$();

   public final String toString() {
      return "IndexedRow";
   }

   public IndexedRow apply(final long index, final Vector vector) {
      return new IndexedRow(index, vector);
   }

   public Option unapply(final IndexedRow x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(BoxesRunTime.boxToLong(x$0.index()), x$0.vector())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(IndexedRow$.class);
   }

   private IndexedRow$() {
   }
}
