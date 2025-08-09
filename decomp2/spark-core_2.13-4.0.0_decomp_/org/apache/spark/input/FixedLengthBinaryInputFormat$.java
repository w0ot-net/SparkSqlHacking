package org.apache.spark.input;

import org.apache.hadoop.mapreduce.JobContext;
import scala.collection.StringOps.;

public final class FixedLengthBinaryInputFormat$ {
   public static final FixedLengthBinaryInputFormat$ MODULE$ = new FixedLengthBinaryInputFormat$();
   private static final String RECORD_LENGTH_PROPERTY = "org.apache.spark.input.FixedLengthBinaryInputFormat.recordLength";

   public String RECORD_LENGTH_PROPERTY() {
      return RECORD_LENGTH_PROPERTY;
   }

   public int getRecordLength(final JobContext context) {
      return .MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(context.getConfiguration().get(this.RECORD_LENGTH_PROPERTY())));
   }

   private FixedLengthBinaryInputFormat$() {
   }
}
