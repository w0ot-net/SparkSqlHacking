package org.apache.spark.streaming.api.java;

import java.io.Serializable;
import java.util.Map;
import scala.Option;
import scala.Some;
import scala.Tuple4;
import scala.None.;
import scala.runtime.AbstractFunction4;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class JavaStreamInputInfo$ extends AbstractFunction4 implements Serializable {
   public static final JavaStreamInputInfo$ MODULE$ = new JavaStreamInputInfo$();

   public final String toString() {
      return "JavaStreamInputInfo";
   }

   public JavaStreamInputInfo apply(final int inputStreamId, final long numRecords, final Map metadata, final String metadataDescription) {
      return new JavaStreamInputInfo(inputStreamId, numRecords, metadata, metadataDescription);
   }

   public Option unapply(final JavaStreamInputInfo x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple4(BoxesRunTime.boxToInteger(x$0.inputStreamId()), BoxesRunTime.boxToLong(x$0.numRecords()), x$0.metadata(), x$0.metadataDescription())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(JavaStreamInputInfo$.class);
   }

   private JavaStreamInputInfo$() {
   }
}
