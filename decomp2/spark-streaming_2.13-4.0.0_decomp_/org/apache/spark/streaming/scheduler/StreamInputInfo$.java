package org.apache.spark.streaming.scheduler;

import java.io.Serializable;
import org.apache.spark.annotation.DeveloperApi;
import scala.Option;
import scala.Some;
import scala.Tuple3;
import scala.Predef.;
import scala.collection.immutable.Map;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

@DeveloperApi
public final class StreamInputInfo$ implements Serializable {
   public static final StreamInputInfo$ MODULE$ = new StreamInputInfo$();
   private static final String METADATA_KEY_DESCRIPTION = "Description";

   public Map $lessinit$greater$default$3() {
      return .MODULE$.Map().empty();
   }

   public String METADATA_KEY_DESCRIPTION() {
      return METADATA_KEY_DESCRIPTION;
   }

   public StreamInputInfo apply(final int inputStreamId, final long numRecords, final Map metadata) {
      return new StreamInputInfo(inputStreamId, numRecords, metadata);
   }

   public Map apply$default$3() {
      return .MODULE$.Map().empty();
   }

   public Option unapply(final StreamInputInfo x$0) {
      return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple3(BoxesRunTime.boxToInteger(x$0.inputStreamId()), BoxesRunTime.boxToLong(x$0.numRecords()), x$0.metadata())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(StreamInputInfo$.class);
   }

   private StreamInputInfo$() {
   }
}
