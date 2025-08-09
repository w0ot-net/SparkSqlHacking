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

public class LocalLDAModel$SaveLoadV1_0$Data$ extends AbstractFunction2 implements Serializable {
   public static final LocalLDAModel$SaveLoadV1_0$Data$ MODULE$ = new LocalLDAModel$SaveLoadV1_0$Data$();

   public final String toString() {
      return "Data";
   }

   public LocalLDAModel$SaveLoadV1_0$Data apply(final Vector topic, final int index) {
      return new LocalLDAModel$SaveLoadV1_0$Data(topic, index);
   }

   public Option unapply(final LocalLDAModel$SaveLoadV1_0$Data x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.topic(), BoxesRunTime.boxToInteger(x$0.index()))));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(LocalLDAModel$SaveLoadV1_0$Data$.class);
   }
}
