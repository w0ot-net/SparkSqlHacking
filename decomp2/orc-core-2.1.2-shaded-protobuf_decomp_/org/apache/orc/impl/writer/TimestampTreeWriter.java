package org.apache.orc.impl.writer;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;
import java.util.function.Consumer;
import org.apache.hadoop.hive.ql.exec.vector.ColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.TimestampColumnVector;
import org.apache.hadoop.hive.ql.util.JavaDataModel;
import org.apache.orc.OrcProto;
import org.apache.orc.TypeDescription;
import org.apache.orc.OrcProto.Stream.Kind;
import org.apache.orc.impl.CryptoUtils;
import org.apache.orc.impl.IntegerWriter;
import org.apache.orc.impl.PositionRecorder;
import org.apache.orc.impl.SerializationUtils;
import org.apache.orc.impl.StreamName;

public class TimestampTreeWriter extends TreeWriterBase {
   public static final int MILLIS_PER_SECOND = 1000;
   public static final String BASE_TIMESTAMP_STRING = "2015-01-01 00:00:00";
   private static final TimeZone UTC = TimeZone.getTimeZone("UTC");
   private final IntegerWriter seconds;
   private final IntegerWriter nanos;
   private final boolean isDirectV2;
   private final boolean alwaysUTC;
   private final TimeZone localTimezone;
   private final long epoch;
   private final boolean useProleptic;

   public TimestampTreeWriter(TypeDescription schema, WriterEncryptionVariant encryption, WriterContext context, boolean instantType) throws IOException {
      super(schema, encryption, context);
      this.isDirectV2 = this.isNewWriteFormat(context);
      this.seconds = this.createIntegerWriter(context.createStream(new StreamName(this.id, Kind.DATA, encryption)), true, this.isDirectV2, context);
      this.nanos = this.createIntegerWriter(context.createStream(new StreamName(this.id, Kind.SECONDARY, encryption)), false, this.isDirectV2, context);
      if (this.rowIndexPosition != null) {
         this.recordPosition(this.rowIndexPosition);
      }

      this.alwaysUTC = instantType || context.getUseUTCTimestamp();
      DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

      try {
         if (this.alwaysUTC) {
            dateFormat.setTimeZone(UTC);
            this.localTimezone = null;
            this.epoch = dateFormat.parse("2015-01-01 00:00:00").getTime() / 1000L;
         } else {
            this.localTimezone = TimeZone.getDefault();
            dateFormat.setTimeZone(this.localTimezone);
            this.epoch = dateFormat.parse("2015-01-01 00:00:00").getTime() / 1000L;
         }
      } catch (ParseException e) {
         throw new IOException("Unable to create base timestamp tree writer", e);
      }

      this.useProleptic = context.getProlepticGregorian();
   }

   OrcProto.ColumnEncoding.Builder getEncoding() {
      OrcProto.ColumnEncoding.Builder result = super.getEncoding();
      result.setKind(this.isDirectV2 ? org.apache.orc.OrcProto.ColumnEncoding.Kind.DIRECT_V2 : org.apache.orc.OrcProto.ColumnEncoding.Kind.DIRECT);
      return result;
   }

   public void writeBatch(ColumnVector vector, int offset, int length) throws IOException {
      super.writeBatch(vector, offset, length);
      TimestampColumnVector vec = (TimestampColumnVector)vector;
      vec.changeCalendar(this.useProleptic, true);
      if (vector.isRepeating) {
         if (vector.noNulls || !vector.isNull[0]) {
            long secs = vec.time[0] / 1000L;
            int newNanos = vec.nanos[0];
            long millis = secs * 1000L + (long)(newNanos / 1000000);
            if (millis < 0L && newNanos > 999999) {
               millis -= 1000L;
            }

            long utc = !vec.isUTC() && !this.alwaysUTC ? SerializationUtils.convertToUtc(this.localTimezone, millis) : millis;
            this.indexStatistics.updateTimestamp(utc, newNanos % 1000000);
            if (this.createBloomFilter) {
               if (this.bloomFilter != null) {
                  this.bloomFilter.addLong(millis);
               }

               this.bloomFilterUtf8.addLong(utc);
            }

            long nano = formatNanos(vec.nanos[0]);

            for(int i = 0; i < length; ++i) {
               this.seconds.write(secs - this.epoch);
               this.nanos.write(nano);
            }
         }
      } else {
         for(int i = 0; i < length; ++i) {
            if (vec.noNulls || !vec.isNull[i + offset]) {
               long secs = vec.time[i + offset] / 1000L;
               int newNanos = vec.nanos[i + offset];
               long millis = secs * 1000L + (long)(newNanos / 1000000);
               if (millis < 0L && newNanos > 999999) {
                  millis -= 1000L;
               }

               long utc = !vec.isUTC() && !this.alwaysUTC ? SerializationUtils.convertToUtc(this.localTimezone, millis) : millis;
               this.seconds.write(secs - this.epoch);
               this.nanos.write(formatNanos(newNanos));
               this.indexStatistics.updateTimestamp(utc, newNanos % 1000000);
               if (this.createBloomFilter) {
                  if (this.bloomFilter != null) {
                     this.bloomFilter.addLong(millis);
                  }

                  this.bloomFilterUtf8.addLong(utc);
               }
            }
         }
      }

   }

   public void writeStripe(int requiredIndexEntries) throws IOException {
      super.writeStripe(requiredIndexEntries);
      if (this.rowIndexPosition != null) {
         this.recordPosition(this.rowIndexPosition);
      }

   }

   static long formatNanos(int nanos) {
      if (nanos == 0) {
         return 0L;
      } else if (nanos % 100 != 0) {
         return (long)nanos << 3;
      } else {
         nanos /= 100;

         int trailingZeros;
         for(trailingZeros = 1; nanos % 10 == 0 && trailingZeros < 7; ++trailingZeros) {
            nanos /= 10;
         }

         return (long)nanos << 3 | (long)trailingZeros;
      }
   }

   void recordPosition(PositionRecorder recorder) throws IOException {
      super.recordPosition(recorder);
      this.seconds.getPosition(recorder);
      this.nanos.getPosition(recorder);
   }

   public long estimateMemory() {
      return super.estimateMemory() + this.seconds.estimateMemory() + this.nanos.estimateMemory();
   }

   public long getRawDataSize() {
      return this.fileStatistics.getNumberOfValues() * (long)JavaDataModel.get().lengthOfTimestamp();
   }

   public void flushStreams() throws IOException {
      super.flushStreams();
      this.seconds.flush();
      this.nanos.flush();
   }

   public void prepareStripe(int stripeId) {
      super.prepareStripe(stripeId);
      Consumer<byte[]> updater = CryptoUtils.modifyIvForStripe((long)stripeId);
      this.seconds.changeIv(updater);
      this.nanos.changeIv(updater);
   }
}
