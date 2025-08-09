package org.apache.parquet.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.parquet.ParquetReadOptions;
import org.apache.parquet.io.ParquetDecodingException;
import org.apache.parquet.io.api.RecordMaterializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UnmaterializableRecordCounter {
   public static final String BAD_RECORD_THRESHOLD_CONF_KEY = "parquet.read.bad.record.threshold";
   private static final Logger LOG = LoggerFactory.getLogger(UnmaterializableRecordCounter.class);
   private static final float DEFAULT_THRESHOLD = 0.0F;
   private long numErrors;
   private final double errorThreshold;
   private final long totalNumRecords;

   public UnmaterializableRecordCounter(Configuration conf, long totalNumRecords) {
      this((double)conf.getFloat("parquet.read.bad.record.threshold", 0.0F), totalNumRecords);
   }

   public UnmaterializableRecordCounter(ParquetReadOptions options, long totalNumRecords) {
      this((double)getFloat(options, "parquet.read.bad.record.threshold", 0.0F), totalNumRecords);
   }

   public UnmaterializableRecordCounter(double errorThreshold, long totalNumRecords) {
      this.errorThreshold = errorThreshold;
      this.totalNumRecords = totalNumRecords;
      this.numErrors = 0L;
   }

   public void incErrors(RecordMaterializer.RecordMaterializationException cause) throws ParquetDecodingException {
      ++this.numErrors;
      LOG.warn(String.format("Error while reading an input record (%s out of %s): ", this.numErrors, this.totalNumRecords), cause);
      if (this.numErrors > 0L && this.errorThreshold <= (double)0.0F) {
         throw new ParquetDecodingException("Error while decoding records", cause);
      } else {
         double errRate = (double)this.numErrors / (double)this.totalNumRecords;
         if (errRate > this.errorThreshold) {
            String message = String.format("Decoding error rate of at least %s/%s crosses configured threshold of %s", this.numErrors, this.totalNumRecords, this.errorThreshold);
            LOG.error(message);
            throw new ParquetDecodingException(message, cause);
         }
      }
   }

   private static float getFloat(ParquetReadOptions options, String key, float defaultValue) {
      String value = options.getProperty(key);
      return value != null ? Float.parseFloat(value) : defaultValue;
   }
}
