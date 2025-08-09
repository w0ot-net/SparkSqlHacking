package org.apache.orc.impl;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.Chronology;
import java.time.chrono.IsoChronology;
import java.util.TimeZone;
import org.apache.hadoop.hive.common.type.HiveDecimal;
import org.apache.hadoop.hive.serde2.io.DateWritable;
import org.apache.hadoop.hive.serde2.io.HiveDecimalWritable;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparator;
import org.apache.orc.BinaryColumnStatistics;
import org.apache.orc.BooleanColumnStatistics;
import org.apache.orc.CollectionColumnStatistics;
import org.apache.orc.ColumnStatistics;
import org.apache.orc.DateColumnStatistics;
import org.apache.orc.DecimalColumnStatistics;
import org.apache.orc.DoubleColumnStatistics;
import org.apache.orc.IntegerColumnStatistics;
import org.apache.orc.OrcProto;
import org.apache.orc.StringColumnStatistics;
import org.apache.orc.TimestampColumnStatistics;
import org.apache.orc.TypeDescription;
import org.apache.orc.OrcProto.BinaryStatistics;
import org.apache.orc.OrcProto.BucketStatistics;
import org.apache.orc.OrcProto.CollectionStatistics;
import org.apache.orc.OrcProto.DateStatistics;
import org.apache.orc.OrcProto.DecimalStatistics;
import org.apache.orc.OrcProto.DoubleStatistics;
import org.apache.orc.OrcProto.IntegerStatistics;
import org.apache.orc.OrcProto.StringStatistics;
import org.apache.orc.OrcProto.TimestampStatistics;
import org.threeten.extra.chrono.HybridChronology;

public class ColumnStatisticsImpl implements ColumnStatistics {
   protected long count = 0L;
   private boolean hasNull = false;
   private long bytesOnDisk = 0L;

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o instanceof ColumnStatisticsImpl) {
         ColumnStatisticsImpl that = (ColumnStatisticsImpl)o;
         if (this.count != that.count) {
            return false;
         } else if (this.hasNull != that.hasNull) {
            return false;
         } else {
            return this.bytesOnDisk == that.bytesOnDisk;
         }
      } else {
         return false;
      }
   }

   public int hashCode() {
      int result = (int)(this.count ^ this.count >>> 32);
      result = 31 * result + (this.hasNull ? 1 : 0);
      return result;
   }

   ColumnStatisticsImpl(OrcProto.ColumnStatistics stats) {
      if (stats.hasNumberOfValues()) {
         this.count = stats.getNumberOfValues();
      }

      this.bytesOnDisk = stats.hasBytesOnDisk() ? stats.getBytesOnDisk() : 0L;
      if (stats.hasHasNull()) {
         this.hasNull = stats.getHasNull();
      } else {
         this.hasNull = true;
      }

   }

   ColumnStatisticsImpl() {
   }

   public void increment() {
      ++this.count;
   }

   public void increment(int count) {
      this.count += (long)count;
   }

   public void updateByteCount(long size) {
      this.bytesOnDisk += size;
   }

   public void setNull() {
      this.hasNull = true;
   }

   public void updateCollectionLength(long value) {
      throw new UnsupportedOperationException("Can't update collection count");
   }

   public void updateBoolean(boolean value, int repetitions) {
      throw new UnsupportedOperationException("Can't update boolean");
   }

   public void updateInteger(long value, int repetitions) {
      throw new UnsupportedOperationException("Can't update integer");
   }

   public void updateDouble(double value) {
      throw new UnsupportedOperationException("Can't update double");
   }

   public void updateString(Text value) {
      throw new UnsupportedOperationException("Can't update string");
   }

   public void updateString(byte[] bytes, int offset, int length, int repetitions) {
      throw new UnsupportedOperationException("Can't update string");
   }

   public void updateBinary(BytesWritable value) {
      throw new UnsupportedOperationException("Can't update binary");
   }

   public void updateBinary(byte[] bytes, int offset, int length, int repetitions) {
      throw new UnsupportedOperationException("Can't update string");
   }

   public void updateDecimal(HiveDecimalWritable value) {
      throw new UnsupportedOperationException("Can't update decimal");
   }

   public void updateDecimal64(long value, int scale) {
      throw new UnsupportedOperationException("Can't update decimal");
   }

   public void updateDate(DateWritable value) {
      throw new UnsupportedOperationException("Can't update date");
   }

   public void updateDate(int value) {
      throw new UnsupportedOperationException("Can't update date");
   }

   public void updateTimestamp(Timestamp value) {
      throw new UnsupportedOperationException("Can't update timestamp");
   }

   public void updateTimestamp(long value, int nanos) {
      throw new UnsupportedOperationException("Can't update timestamp");
   }

   public boolean isStatsExists() {
      return this.count > 0L || this.hasNull;
   }

   public void merge(ColumnStatisticsImpl stats) {
      this.count += stats.count;
      this.hasNull |= stats.hasNull;
      this.bytesOnDisk += stats.bytesOnDisk;
   }

   public void reset() {
      this.count = 0L;
      this.bytesOnDisk = 0L;
      this.hasNull = false;
   }

   public long getNumberOfValues() {
      return this.count;
   }

   public boolean hasNull() {
      return this.hasNull;
   }

   public long getBytesOnDisk() {
      return this.bytesOnDisk;
   }

   public String toString() {
      long var10000 = this.count;
      return "count: " + var10000 + " hasNull: " + this.hasNull + (this.bytesOnDisk != 0L ? " bytesOnDisk: " + this.bytesOnDisk : "");
   }

   public OrcProto.ColumnStatistics.Builder serialize() {
      OrcProto.ColumnStatistics.Builder builder = org.apache.orc.OrcProto.ColumnStatistics.newBuilder();
      builder.setNumberOfValues(this.count);
      builder.setHasNull(this.hasNull);
      if (this.bytesOnDisk != 0L) {
         builder.setBytesOnDisk(this.bytesOnDisk);
      }

      return builder;
   }

   public static ColumnStatisticsImpl create(TypeDescription schema) {
      return create(schema, false);
   }

   public static ColumnStatisticsImpl create(TypeDescription schema, boolean convertToProleptic) {
      switch (schema.getCategory()) {
         case BOOLEAN:
            return new BooleanStatisticsImpl();
         case BYTE:
         case SHORT:
         case INT:
         case LONG:
            return new IntegerStatisticsImpl();
         case LIST:
         case MAP:
            return new CollectionColumnStatisticsImpl();
         case FLOAT:
         case DOUBLE:
            return new DoubleStatisticsImpl();
         case STRING:
         case CHAR:
         case VARCHAR:
            return new StringStatisticsImpl();
         case DECIMAL:
            if (schema.getPrecision() <= 18) {
               return new Decimal64StatisticsImpl(schema.getScale());
            }

            return new DecimalStatisticsImpl();
         case DATE:
            return new DateStatisticsImpl(convertToProleptic);
         case TIMESTAMP:
            return new TimestampStatisticsImpl();
         case TIMESTAMP_INSTANT:
            return new TimestampInstantStatisticsImpl();
         case BINARY:
            return new BinaryStatisticsImpl();
         default:
            return new ColumnStatisticsImpl();
      }
   }

   public static ColumnStatisticsImpl deserialize(TypeDescription schema, OrcProto.ColumnStatistics stats) {
      return deserialize(schema, stats, true, true);
   }

   public static ColumnStatisticsImpl deserialize(TypeDescription schema, OrcProto.ColumnStatistics stats, boolean writerUsedProlepticGregorian, boolean convertToProlepticGregorian) {
      if (stats.hasBucketStatistics()) {
         return new BooleanStatisticsImpl(stats);
      } else if (stats.hasIntStatistics()) {
         return new IntegerStatisticsImpl(stats);
      } else if (stats.hasCollectionStatistics()) {
         return new CollectionColumnStatisticsImpl(stats);
      } else if (stats.hasDoubleStatistics()) {
         return new DoubleStatisticsImpl(stats);
      } else if (stats.hasStringStatistics()) {
         return new StringStatisticsImpl(stats);
      } else if (stats.hasDecimalStatistics()) {
         return (ColumnStatisticsImpl)(schema != null && schema.getPrecision() <= 18 ? new Decimal64StatisticsImpl(schema.getScale(), stats) : new DecimalStatisticsImpl(stats));
      } else if (stats.hasDateStatistics()) {
         return new DateStatisticsImpl(stats, writerUsedProlepticGregorian, convertToProlepticGregorian);
      } else if (!stats.hasTimestampStatistics()) {
         return (ColumnStatisticsImpl)(stats.hasBinaryStatistics() ? new BinaryStatisticsImpl(stats) : new ColumnStatisticsImpl(stats));
      } else {
         return (ColumnStatisticsImpl)(schema != null && schema.getCategory() != TypeDescription.Category.TIMESTAMP ? new TimestampInstantStatisticsImpl(stats, writerUsedProlepticGregorian, convertToProlepticGregorian) : new TimestampStatisticsImpl(stats, writerUsedProlepticGregorian, convertToProlepticGregorian));
      }
   }

   private static final class BooleanStatisticsImpl extends ColumnStatisticsImpl implements BooleanColumnStatistics {
      private long trueCount = 0L;

      BooleanStatisticsImpl(OrcProto.ColumnStatistics stats) {
         super(stats);
         OrcProto.BucketStatistics bkt = stats.getBucketStatistics();
         this.trueCount = bkt.getCount(0);
      }

      BooleanStatisticsImpl() {
      }

      public void reset() {
         super.reset();
         this.trueCount = 0L;
      }

      public void updateBoolean(boolean value, int repetitions) {
         if (value) {
            this.trueCount += (long)repetitions;
         }

      }

      public void merge(ColumnStatisticsImpl other) {
         if (other instanceof BooleanStatisticsImpl bkt) {
            this.trueCount += bkt.trueCount;
         } else {
            if (!(other instanceof BooleanColumnStatistics)) {
               throw new IllegalArgumentException("Incompatible merging of boolean column statistics");
            }

            if (this.isStatsExists() && this.trueCount != 0L) {
               throw new IllegalArgumentException("Incompatible merging of boolean column statistics");
            }
         }

         super.merge(other);
      }

      public OrcProto.ColumnStatistics.Builder serialize() {
         OrcProto.ColumnStatistics.Builder builder = super.serialize();
         OrcProto.BucketStatistics.Builder bucket = BucketStatistics.newBuilder();
         bucket.addCount(this.trueCount);
         builder.setBucketStatistics(bucket);
         return builder;
      }

      public long getFalseCount() {
         return this.getNumberOfValues() - this.trueCount;
      }

      public long getTrueCount() {
         return this.trueCount;
      }

      public String toString() {
         String var10000 = super.toString();
         return var10000 + " true: " + this.trueCount;
      }

      public boolean equals(Object o) {
         if (this == o) {
            return true;
         } else if (o instanceof BooleanStatisticsImpl) {
            BooleanStatisticsImpl that = (BooleanStatisticsImpl)o;
            if (!super.equals(o)) {
               return false;
            } else {
               return this.trueCount == that.trueCount;
            }
         } else {
            return false;
         }
      }

      public int hashCode() {
         int result = super.hashCode();
         result = 31 * result + (int)(this.trueCount ^ this.trueCount >>> 32);
         return result;
      }
   }

   private static final class CollectionColumnStatisticsImpl extends ColumnStatisticsImpl implements CollectionColumnStatistics {
      protected long minimum = Long.MAX_VALUE;
      protected long maximum = 0L;
      protected long sum = 0L;

      CollectionColumnStatisticsImpl() {
      }

      CollectionColumnStatisticsImpl(OrcProto.ColumnStatistics stats) {
         super(stats);
         OrcProto.CollectionStatistics collStat = stats.getCollectionStatistics();
         this.minimum = collStat.hasMinChildren() ? collStat.getMinChildren() : Long.MAX_VALUE;
         this.maximum = collStat.hasMaxChildren() ? collStat.getMaxChildren() : 0L;
         this.sum = collStat.hasTotalChildren() ? collStat.getTotalChildren() : 0L;
      }

      public void updateCollectionLength(long length) {
         if (length < this.minimum) {
            this.minimum = length;
         }

         if (length > this.maximum) {
            this.maximum = length;
         }

         this.sum += length;
      }

      public void reset() {
         super.reset();
         this.minimum = Long.MAX_VALUE;
         this.maximum = 0L;
         this.sum = 0L;
      }

      public void merge(ColumnStatisticsImpl other) {
         if (other instanceof CollectionColumnStatisticsImpl otherColl) {
            if (this.count == 0L) {
               this.minimum = otherColl.minimum;
               this.maximum = otherColl.maximum;
            } else {
               if (otherColl.minimum < this.minimum) {
                  this.minimum = otherColl.minimum;
               }

               if (otherColl.maximum > this.maximum) {
                  this.maximum = otherColl.maximum;
               }
            }

            this.sum += otherColl.sum;
         } else {
            if (!(other instanceof CollectionColumnStatistics)) {
               throw new IllegalArgumentException("Incompatible merging of collection column statistics");
            }

            if (this.isStatsExists()) {
               throw new IllegalArgumentException("Incompatible merging of collection column statistics");
            }
         }

         super.merge(other);
      }

      public long getMinimumChildren() {
         return this.minimum;
      }

      public long getMaximumChildren() {
         return this.maximum;
      }

      public long getTotalChildren() {
         return this.sum;
      }

      public String toString() {
         StringBuilder buf = new StringBuilder(super.toString());
         if (this.count != 0L) {
            buf.append(" minChildren: ");
            buf.append(this.minimum);
            buf.append(" maxChildren: ");
            buf.append(this.maximum);
            if (this.sum != 0L) {
               buf.append(" totalChildren: ");
               buf.append(this.sum);
            }
         }

         return buf.toString();
      }

      public boolean equals(Object o) {
         if (this == o) {
            return true;
         } else if (o instanceof CollectionColumnStatisticsImpl) {
            CollectionColumnStatisticsImpl that = (CollectionColumnStatisticsImpl)o;
            if (!super.equals(o)) {
               return false;
            } else if (this.minimum != that.minimum) {
               return false;
            } else if (this.maximum != that.maximum) {
               return false;
            } else {
               return this.sum == that.sum;
            }
         } else {
            return false;
         }
      }

      public int hashCode() {
         int result = super.hashCode();
         result = 31 * result + (this.count != 0L ? (int)(this.minimum ^ this.minimum >>> 32) : 0);
         result = 31 * result + (this.count != 0L ? (int)(this.maximum ^ this.maximum >>> 32) : 0);
         result = 31 * result + (this.sum != 0L ? (int)(this.sum ^ this.sum >>> 32) : 0);
         return result;
      }

      public OrcProto.ColumnStatistics.Builder serialize() {
         OrcProto.ColumnStatistics.Builder builder = super.serialize();
         OrcProto.CollectionStatistics.Builder collectionStats = CollectionStatistics.newBuilder();
         if (this.count != 0L) {
            collectionStats.setMinChildren(this.minimum);
            collectionStats.setMaxChildren(this.maximum);
         }

         if (this.sum != 0L) {
            collectionStats.setTotalChildren(this.sum);
         }

         builder.setCollectionStatistics(collectionStats);
         return builder;
      }
   }

   private static final class IntegerStatisticsImpl extends ColumnStatisticsImpl implements IntegerColumnStatistics {
      private long minimum = Long.MAX_VALUE;
      private long maximum = Long.MIN_VALUE;
      private long sum = 0L;
      private boolean hasMinimum = false;
      private boolean overflow = false;

      IntegerStatisticsImpl() {
      }

      IntegerStatisticsImpl(OrcProto.ColumnStatistics stats) {
         super(stats);
         OrcProto.IntegerStatistics intStat = stats.getIntStatistics();
         if (intStat.hasMinimum()) {
            this.hasMinimum = true;
            this.minimum = intStat.getMinimum();
         }

         if (intStat.hasMaximum()) {
            this.maximum = intStat.getMaximum();
         }

         if (intStat.hasSum()) {
            this.sum = intStat.getSum();
         } else {
            this.overflow = true;
         }

      }

      public void reset() {
         super.reset();
         this.hasMinimum = false;
         this.minimum = Long.MAX_VALUE;
         this.maximum = Long.MIN_VALUE;
         this.sum = 0L;
         this.overflow = false;
      }

      public void updateInteger(long value, int repetitions) {
         if (!this.hasMinimum) {
            this.hasMinimum = true;
            this.minimum = value;
            this.maximum = value;
         } else if (value < this.minimum) {
            this.minimum = value;
         } else if (value > this.maximum) {
            this.maximum = value;
         }

         if (!this.overflow) {
            try {
               long increment = repetitions > 1 ? Math.multiplyExact(value, repetitions) : value;
               this.sum = Math.addExact(this.sum, increment);
            } catch (ArithmeticException var6) {
               this.overflow = true;
            }
         }

      }

      public void merge(ColumnStatisticsImpl other) {
         if (other instanceof IntegerStatisticsImpl otherInt) {
            if (!this.hasMinimum) {
               this.hasMinimum = otherInt.hasMinimum;
               this.minimum = otherInt.minimum;
               this.maximum = otherInt.maximum;
            } else if (otherInt.hasMinimum) {
               if (otherInt.minimum < this.minimum) {
                  this.minimum = otherInt.minimum;
               }

               if (otherInt.maximum > this.maximum) {
                  this.maximum = otherInt.maximum;
               }
            }

            this.overflow |= otherInt.overflow;
            if (!this.overflow) {
               try {
                  this.sum = Math.addExact(this.sum, otherInt.sum);
               } catch (ArithmeticException var4) {
                  this.overflow = true;
               }
            }
         } else {
            if (!(other instanceof IntegerColumnStatistics)) {
               throw new IllegalArgumentException("Incompatible merging of integer column statistics");
            }

            if (this.isStatsExists() && this.hasMinimum) {
               throw new IllegalArgumentException("Incompatible merging of integer column statistics");
            }
         }

         super.merge(other);
      }

      public OrcProto.ColumnStatistics.Builder serialize() {
         OrcProto.ColumnStatistics.Builder builder = super.serialize();
         OrcProto.IntegerStatistics.Builder intb = IntegerStatistics.newBuilder();
         if (this.hasMinimum) {
            intb.setMinimum(this.minimum);
            intb.setMaximum(this.maximum);
         }

         if (!this.overflow) {
            intb.setSum(this.sum);
         }

         builder.setIntStatistics(intb);
         return builder;
      }

      public long getMinimum() {
         return this.minimum;
      }

      public long getMaximum() {
         return this.maximum;
      }

      public boolean isSumDefined() {
         return !this.overflow;
      }

      public long getSum() {
         return this.sum;
      }

      public String toString() {
         StringBuilder buf = new StringBuilder(super.toString());
         if (this.hasMinimum) {
            buf.append(" min: ");
            buf.append(this.minimum);
            buf.append(" max: ");
            buf.append(this.maximum);
         }

         if (!this.overflow) {
            buf.append(" sum: ");
            buf.append(this.sum);
         }

         return buf.toString();
      }

      public boolean equals(Object o) {
         if (this == o) {
            return true;
         } else if (o instanceof IntegerStatisticsImpl) {
            IntegerStatisticsImpl that = (IntegerStatisticsImpl)o;
            if (!super.equals(o)) {
               return false;
            } else if (this.minimum != that.minimum) {
               return false;
            } else if (this.maximum != that.maximum) {
               return false;
            } else if (this.sum != that.sum) {
               return false;
            } else if (this.hasMinimum != that.hasMinimum) {
               return false;
            } else {
               return this.overflow == that.overflow;
            }
         } else {
            return false;
         }
      }

      public int hashCode() {
         int result = super.hashCode();
         result = 31 * result + (int)(this.minimum ^ this.minimum >>> 32);
         result = 31 * result + (int)(this.maximum ^ this.maximum >>> 32);
         result = 31 * result + (int)(this.sum ^ this.sum >>> 32);
         result = 31 * result + (this.hasMinimum ? 1 : 0);
         result = 31 * result + (this.overflow ? 1 : 0);
         return result;
      }
   }

   private static final class DoubleStatisticsImpl extends ColumnStatisticsImpl implements DoubleColumnStatistics {
      private boolean hasMinimum = false;
      private double minimum = Double.MAX_VALUE;
      private double maximum = Double.MIN_VALUE;
      private double sum = (double)0.0F;

      DoubleStatisticsImpl() {
      }

      DoubleStatisticsImpl(OrcProto.ColumnStatistics stats) {
         super(stats);
         OrcProto.DoubleStatistics dbl = stats.getDoubleStatistics();
         if (dbl.hasMinimum()) {
            this.hasMinimum = true;
            this.minimum = dbl.getMinimum();
         }

         if (dbl.hasMaximum()) {
            this.maximum = dbl.getMaximum();
         }

         if (dbl.hasSum()) {
            this.sum = dbl.getSum();
         }

      }

      public void reset() {
         super.reset();
         this.hasMinimum = false;
         this.minimum = Double.MAX_VALUE;
         this.maximum = Double.MIN_VALUE;
         this.sum = (double)0.0F;
      }

      public void updateDouble(double value) {
         if (!this.hasMinimum) {
            this.hasMinimum = true;
            this.minimum = value;
            this.maximum = value;
         } else if (value < this.minimum) {
            this.minimum = value;
         } else if (value > this.maximum) {
            this.maximum = value;
         }

         this.sum += value;
      }

      public void merge(ColumnStatisticsImpl other) {
         if (other instanceof DoubleStatisticsImpl dbl) {
            if (!this.hasMinimum) {
               this.hasMinimum = dbl.hasMinimum;
               this.minimum = dbl.minimum;
               this.maximum = dbl.maximum;
            } else if (dbl.hasMinimum) {
               if (dbl.minimum < this.minimum) {
                  this.minimum = dbl.minimum;
               }

               if (dbl.maximum > this.maximum) {
                  this.maximum = dbl.maximum;
               }
            }

            this.sum += dbl.sum;
         } else {
            if (!(other instanceof DoubleColumnStatistics)) {
               throw new IllegalArgumentException("Incompatible merging of double column statistics");
            }

            if (this.isStatsExists() && this.hasMinimum) {
               throw new IllegalArgumentException("Incompatible merging of double column statistics");
            }
         }

         super.merge(other);
      }

      public OrcProto.ColumnStatistics.Builder serialize() {
         OrcProto.ColumnStatistics.Builder builder = super.serialize();
         OrcProto.DoubleStatistics.Builder dbl = DoubleStatistics.newBuilder();
         if (this.hasMinimum) {
            dbl.setMinimum(this.minimum);
            dbl.setMaximum(this.maximum);
         }

         dbl.setSum(this.sum);
         builder.setDoubleStatistics(dbl);
         return builder;
      }

      public double getMinimum() {
         return this.minimum;
      }

      public double getMaximum() {
         return this.maximum;
      }

      public double getSum() {
         return this.sum;
      }

      public String toString() {
         StringBuilder buf = new StringBuilder(super.toString());
         if (this.hasMinimum) {
            buf.append(" min: ");
            buf.append(this.minimum);
            buf.append(" max: ");
            buf.append(this.maximum);
         }

         buf.append(" sum: ");
         buf.append(this.sum);
         return buf.toString();
      }

      public boolean equals(Object o) {
         if (this == o) {
            return true;
         } else if (o instanceof DoubleStatisticsImpl) {
            DoubleStatisticsImpl that = (DoubleStatisticsImpl)o;
            if (!super.equals(o)) {
               return false;
            } else if (this.hasMinimum != that.hasMinimum) {
               return false;
            } else if (Double.compare(that.minimum, this.minimum) != 0) {
               return false;
            } else if (Double.compare(that.maximum, this.maximum) != 0) {
               return false;
            } else {
               return Double.compare(that.sum, this.sum) == 0;
            }
         } else {
            return false;
         }
      }

      public int hashCode() {
         int result = super.hashCode();
         result = 31 * result + (this.hasMinimum ? 1 : 0);
         long temp = Double.doubleToLongBits(this.minimum);
         result = 31 * result + (int)(temp ^ temp >>> 32);
         temp = Double.doubleToLongBits(this.maximum);
         result = 31 * result + (int)(temp ^ temp >>> 32);
         temp = Double.doubleToLongBits(this.sum);
         result = 31 * result + (int)(temp ^ temp >>> 32);
         return result;
      }
   }

   protected static final class StringStatisticsImpl extends ColumnStatisticsImpl implements StringColumnStatistics {
      public static final int MAX_BYTES_RECORDED = 1024;
      private Text minimum = null;
      private Text maximum = null;
      private long sum = 0L;
      private boolean isLowerBoundSet = false;
      private boolean isUpperBoundSet = false;

      StringStatisticsImpl() {
      }

      StringStatisticsImpl(OrcProto.ColumnStatistics stats) {
         super(stats);
         OrcProto.StringStatistics str = stats.getStringStatistics();
         if (str.hasMaximum()) {
            this.maximum = new Text(str.getMaximum());
         } else if (str.hasUpperBound()) {
            this.maximum = new Text(str.getUpperBound());
            this.isUpperBoundSet = true;
         }

         if (str.hasMinimum()) {
            this.minimum = new Text(str.getMinimum());
         } else if (str.hasLowerBound()) {
            this.minimum = new Text(str.getLowerBound());
            this.isLowerBoundSet = true;
         }

         if (str.hasSum()) {
            this.sum = str.getSum();
         }

      }

      public void reset() {
         super.reset();
         this.minimum = null;
         this.maximum = null;
         this.isLowerBoundSet = false;
         this.isUpperBoundSet = false;
         this.sum = 0L;
      }

      public void updateString(Text value) {
         this.updateString(value.getBytes(), 0, value.getLength(), 1);
      }

      public void updateString(byte[] bytes, int offset, int length, int repetitions) {
         if (this.minimum == null) {
            if (length > 1024) {
               this.minimum = truncateLowerBound(bytes, offset);
               this.maximum = truncateUpperBound(bytes, offset);
               this.isLowerBoundSet = true;
               this.isUpperBoundSet = true;
            } else {
               this.maximum = this.minimum = new Text();
               this.maximum.set(bytes, offset, length);
               this.isLowerBoundSet = false;
               this.isUpperBoundSet = false;
            }
         } else if (WritableComparator.compareBytes(this.minimum.getBytes(), 0, this.minimum.getLength(), bytes, offset, length) > 0) {
            if (length > 1024) {
               this.minimum = truncateLowerBound(bytes, offset);
               this.isLowerBoundSet = true;
            } else {
               this.minimum = new Text();
               this.minimum.set(bytes, offset, length);
               this.isLowerBoundSet = false;
            }
         } else if (WritableComparator.compareBytes(this.maximum.getBytes(), 0, this.maximum.getLength(), bytes, offset, length) < 0) {
            if (length > 1024) {
               this.maximum = truncateUpperBound(bytes, offset);
               this.isUpperBoundSet = true;
            } else {
               this.maximum = new Text();
               this.maximum.set(bytes, offset, length);
               this.isUpperBoundSet = false;
            }
         }

         this.sum += (long)length * (long)repetitions;
      }

      public void merge(ColumnStatisticsImpl other) {
         if (other instanceof StringStatisticsImpl str) {
            if (this.count == 0L) {
               if (str.count != 0L) {
                  this.minimum = new Text(str.minimum);
                  this.isLowerBoundSet = str.isLowerBoundSet;
                  this.maximum = new Text(str.maximum);
                  this.isUpperBoundSet = str.isUpperBoundSet;
               } else {
                  this.maximum = this.minimum = null;
                  this.isLowerBoundSet = false;
                  this.isUpperBoundSet = false;
               }
            } else if (str.count != 0L) {
               if (this.minimum.compareTo(str.minimum) > 0) {
                  this.minimum = new Text(str.minimum);
                  this.isLowerBoundSet = str.isLowerBoundSet;
               }

               if (this.maximum.compareTo(str.maximum) < 0) {
                  this.maximum = new Text(str.maximum);
                  this.isUpperBoundSet = str.isUpperBoundSet;
               }
            }

            this.sum += str.sum;
         } else {
            if (!(other instanceof StringColumnStatistics)) {
               throw new IllegalArgumentException("Incompatible merging of string column statistics");
            }

            if (this.isStatsExists()) {
               throw new IllegalArgumentException("Incompatible merging of string column statistics");
            }
         }

         super.merge(other);
      }

      public OrcProto.ColumnStatistics.Builder serialize() {
         OrcProto.ColumnStatistics.Builder result = super.serialize();
         OrcProto.StringStatistics.Builder str = StringStatistics.newBuilder();
         if (this.getNumberOfValues() != 0L) {
            if (this.isLowerBoundSet) {
               str.setLowerBound(this.minimum.toString());
            } else {
               str.setMinimum(this.minimum.toString());
            }

            if (this.isUpperBoundSet) {
               str.setUpperBound(this.maximum.toString());
            } else {
               str.setMaximum(this.maximum.toString());
            }

            str.setSum(this.sum);
         }

         result.setStringStatistics(str);
         return result;
      }

      public String getMinimum() {
         if (this.isLowerBoundSet) {
            return null;
         } else {
            return this.minimum == null ? null : this.minimum.toString();
         }
      }

      public String getMaximum() {
         if (this.isUpperBoundSet) {
            return null;
         } else {
            return this.maximum == null ? null : this.maximum.toString();
         }
      }

      public String getLowerBound() {
         return this.minimum == null ? null : this.minimum.toString();
      }

      public String getUpperBound() {
         return this.maximum == null ? null : this.maximum.toString();
      }

      public long getSum() {
         return this.sum;
      }

      public String toString() {
         StringBuilder buf = new StringBuilder(super.toString());
         if (this.minimum != null) {
            if (this.isLowerBoundSet) {
               buf.append(" lower: ");
            } else {
               buf.append(" min: ");
            }

            buf.append(this.getLowerBound());
            if (this.isUpperBoundSet) {
               buf.append(" upper: ");
            } else {
               buf.append(" max: ");
            }

            buf.append(this.getUpperBound());
            buf.append(" sum: ");
            buf.append(this.sum);
         }

         return buf.toString();
      }

      public boolean equals(Object o) {
         if (this == o) {
            return true;
         } else if (!(o instanceof StringStatisticsImpl)) {
            return false;
         } else {
            StringStatisticsImpl that = (StringStatisticsImpl)o;
            if (!super.equals(o)) {
               return false;
            } else if (this.sum != that.sum) {
               return false;
            } else {
               if (this.minimum != null) {
                  if (!this.minimum.equals(that.minimum)) {
                     return false;
                  }
               } else if (that.minimum != null) {
                  return false;
               }

               return this.maximum != null ? this.maximum.equals(that.maximum) : that.maximum == null;
            }
         }
      }

      public int hashCode() {
         int result = super.hashCode();
         result = 31 * result + (this.minimum != null ? this.minimum.hashCode() : 0);
         result = 31 * result + (this.maximum != null ? this.maximum.hashCode() : 0);
         result = 31 * result + (int)(this.sum ^ this.sum >>> 32);
         return result;
      }

      private static void appendCodePoint(Text result, int codepoint) {
         if (codepoint >= 0 && codepoint <= 2097151) {
            byte[] buffer = new byte[4];
            if (codepoint < 127) {
               buffer[0] = (byte)codepoint;
               result.append(buffer, 0, 1);
            } else if (codepoint <= 2047) {
               buffer[0] = (byte)(192 | codepoint >> 6);
               buffer[1] = (byte)(128 | codepoint & 63);
               result.append(buffer, 0, 2);
            } else if (codepoint < 65535) {
               buffer[0] = (byte)(224 | codepoint >> 12);
               buffer[1] = (byte)(128 | codepoint >> 6 & 63);
               buffer[2] = (byte)(128 | codepoint & 63);
               result.append(buffer, 0, 3);
            } else {
               buffer[0] = (byte)(240 | codepoint >> 18);
               buffer[1] = (byte)(128 | codepoint >> 12 & 63);
               buffer[2] = (byte)(128 | codepoint >> 6 & 63);
               buffer[3] = (byte)(128 | codepoint & 63);
               result.append(buffer, 0, 4);
            }

         } else {
            throw new IllegalArgumentException("Codepoint out of range " + codepoint);
         }
      }

      private static Text truncateUpperBound(byte[] text, int from) {
         int followingChar = Utf8Utils.findLastCharacter(text, from, from + 1024);
         int lastChar = Utf8Utils.findLastCharacter(text, from, followingChar - 1);
         Text result = new Text();
         result.set(text, from, lastChar - from);
         appendCodePoint(result, Utf8Utils.getCodePoint(text, lastChar, followingChar - lastChar) + 1);
         return result;
      }

      private static Text truncateLowerBound(byte[] text, int from) {
         int lastChar = Utf8Utils.findLastCharacter(text, from, from + 1024);
         Text result = new Text();
         result.set(text, from, lastChar - from);
         return result;
      }
   }

   protected static final class BinaryStatisticsImpl extends ColumnStatisticsImpl implements BinaryColumnStatistics {
      private long sum = 0L;

      BinaryStatisticsImpl() {
      }

      BinaryStatisticsImpl(OrcProto.ColumnStatistics stats) {
         super(stats);
         OrcProto.BinaryStatistics binStats = stats.getBinaryStatistics();
         if (binStats.hasSum()) {
            this.sum = binStats.getSum();
         }

      }

      public void reset() {
         super.reset();
         this.sum = 0L;
      }

      public void updateBinary(BytesWritable value) {
         this.sum += (long)value.getLength();
      }

      public void updateBinary(byte[] bytes, int offset, int length, int repetitions) {
         this.sum += (long)length * (long)repetitions;
      }

      public void merge(ColumnStatisticsImpl other) {
         if (other instanceof BinaryStatisticsImpl bin) {
            this.sum += bin.sum;
         } else {
            if (!(other instanceof BinaryColumnStatistics)) {
               throw new IllegalArgumentException("Incompatible merging of binary column statistics");
            }

            if (this.isStatsExists() && this.sum != 0L) {
               throw new IllegalArgumentException("Incompatible merging of binary column statistics");
            }
         }

         super.merge(other);
      }

      public long getSum() {
         return this.sum;
      }

      public OrcProto.ColumnStatistics.Builder serialize() {
         OrcProto.ColumnStatistics.Builder result = super.serialize();
         OrcProto.BinaryStatistics.Builder bin = BinaryStatistics.newBuilder();
         bin.setSum(this.sum);
         result.setBinaryStatistics(bin);
         return result;
      }

      public String toString() {
         StringBuilder buf = new StringBuilder(super.toString());
         if (this.getNumberOfValues() != 0L) {
            buf.append(" sum: ");
            buf.append(this.sum);
         }

         return buf.toString();
      }

      public boolean equals(Object o) {
         if (this == o) {
            return true;
         } else if (o instanceof BinaryStatisticsImpl) {
            BinaryStatisticsImpl that = (BinaryStatisticsImpl)o;
            if (!super.equals(o)) {
               return false;
            } else {
               return this.sum == that.sum;
            }
         } else {
            return false;
         }
      }

      public int hashCode() {
         int result = super.hashCode();
         result = 31 * result + (int)(this.sum ^ this.sum >>> 32);
         return result;
      }
   }

   private static final class DecimalStatisticsImpl extends ColumnStatisticsImpl implements DecimalColumnStatistics {
      private HiveDecimalWritable minimum = null;
      private HiveDecimalWritable maximum = null;
      private HiveDecimalWritable sum = new HiveDecimalWritable(0L);

      DecimalStatisticsImpl() {
      }

      DecimalStatisticsImpl(OrcProto.ColumnStatistics stats) {
         super(stats);
         OrcProto.DecimalStatistics dec = stats.getDecimalStatistics();
         if (dec.hasMaximum()) {
            this.maximum = new HiveDecimalWritable(dec.getMaximum());
         }

         if (dec.hasMinimum()) {
            this.minimum = new HiveDecimalWritable(dec.getMinimum());
         }

         if (dec.hasSum()) {
            this.sum = new HiveDecimalWritable(dec.getSum());
         } else {
            this.sum = null;
         }

      }

      public void reset() {
         super.reset();
         this.minimum = null;
         this.maximum = null;
         this.sum = new HiveDecimalWritable(0L);
      }

      public void updateDecimal(HiveDecimalWritable value) {
         if (this.minimum == null) {
            this.minimum = new HiveDecimalWritable(value);
            this.maximum = new HiveDecimalWritable(value);
         } else if (this.minimum.compareTo(value) > 0) {
            this.minimum.set(value);
         } else if (this.maximum.compareTo(value) < 0) {
            this.maximum.set(value);
         }

         if (this.sum != null) {
            this.sum.mutateAdd(value);
         }

      }

      public void updateDecimal64(long value, int scale) {
         HiveDecimalWritable dValue = new HiveDecimalWritable();
         dValue.setFromLongAndScale(value, scale);
         this.updateDecimal(dValue);
      }

      public void merge(ColumnStatisticsImpl other) {
         if (other instanceof DecimalStatisticsImpl dec) {
            if (this.minimum == null) {
               this.minimum = dec.minimum != null ? new HiveDecimalWritable(dec.minimum) : null;
               this.maximum = dec.maximum != null ? new HiveDecimalWritable(dec.maximum) : null;
               this.sum = dec.sum;
            } else if (dec.minimum != null) {
               if (this.minimum.compareTo(dec.minimum) > 0) {
                  this.minimum.set(dec.minimum);
               }

               if (this.maximum.compareTo(dec.maximum) < 0) {
                  this.maximum.set(dec.maximum);
               }

               if (this.sum != null && dec.sum != null) {
                  this.sum.mutateAdd(dec.sum);
               } else {
                  this.sum = null;
               }
            }
         } else {
            if (!(other instanceof DecimalColumnStatistics)) {
               throw new IllegalArgumentException("Incompatible merging of decimal column statistics");
            }

            if (this.isStatsExists() && this.minimum != null) {
               throw new IllegalArgumentException("Incompatible merging of decimal column statistics");
            }
         }

         super.merge(other);
      }

      public OrcProto.ColumnStatistics.Builder serialize() {
         OrcProto.ColumnStatistics.Builder result = super.serialize();
         OrcProto.DecimalStatistics.Builder dec = DecimalStatistics.newBuilder();
         if (this.getNumberOfValues() != 0L && this.minimum != null) {
            dec.setMinimum(this.minimum.toString());
            dec.setMaximum(this.maximum.toString());
         }

         if (this.sum != null && this.sum.isSet()) {
            dec.setSum(this.sum.toString());
         }

         result.setDecimalStatistics(dec);
         return result;
      }

      public HiveDecimal getMinimum() {
         return this.minimum == null ? null : this.minimum.getHiveDecimal();
      }

      public HiveDecimal getMaximum() {
         return this.maximum == null ? null : this.maximum.getHiveDecimal();
      }

      public HiveDecimal getSum() {
         return this.sum == null ? null : this.sum.getHiveDecimal();
      }

      public String toString() {
         StringBuilder buf = new StringBuilder(super.toString());
         if (this.getNumberOfValues() != 0L) {
            buf.append(" min: ");
            buf.append(this.minimum);
            buf.append(" max: ");
            buf.append(this.maximum);
            if (this.sum != null) {
               buf.append(" sum: ");
               buf.append(this.sum);
            }
         }

         return buf.toString();
      }

      public boolean equals(Object o) {
         if (this == o) {
            return true;
         } else if (o instanceof DecimalStatisticsImpl) {
            DecimalStatisticsImpl that = (DecimalStatisticsImpl)o;
            if (!super.equals(o)) {
               return false;
            } else {
               if (this.minimum != null) {
                  if (!this.minimum.equals(that.minimum)) {
                     return false;
                  }
               } else if (that.minimum != null) {
                  return false;
               }

               if (this.maximum != null) {
                  if (!this.maximum.equals(that.maximum)) {
                     return false;
                  }
               } else if (that.maximum != null) {
                  return false;
               }

               return this.sum != null ? this.sum.equals(that.sum) : that.sum == null;
            }
         } else {
            return false;
         }
      }

      public int hashCode() {
         int result = super.hashCode();
         result = 31 * result + (this.minimum != null ? this.minimum.hashCode() : 0);
         result = 31 * result + (this.maximum != null ? this.maximum.hashCode() : 0);
         result = 31 * result + (this.sum != null ? this.sum.hashCode() : 0);
         return result;
      }
   }

   private static final class Decimal64StatisticsImpl extends ColumnStatisticsImpl implements DecimalColumnStatistics {
      private final int scale;
      private long minimum = Long.MAX_VALUE;
      private long maximum = Long.MIN_VALUE;
      private boolean hasSum = true;
      private long sum = 0L;
      private final HiveDecimalWritable scratch = new HiveDecimalWritable();

      Decimal64StatisticsImpl(int scale) {
         this.scale = scale;
      }

      Decimal64StatisticsImpl(int scale, OrcProto.ColumnStatistics stats) {
         super(stats);
         this.scale = scale;
         OrcProto.DecimalStatistics dec = stats.getDecimalStatistics();
         if (dec.hasMaximum()) {
            this.maximum = (new HiveDecimalWritable(dec.getMaximum())).serialize64(scale);
         } else {
            this.maximum = Long.MIN_VALUE;
         }

         if (dec.hasMinimum()) {
            this.minimum = (new HiveDecimalWritable(dec.getMinimum())).serialize64(scale);
         } else {
            this.minimum = Long.MAX_VALUE;
         }

         if (dec.hasSum()) {
            this.hasSum = true;
            HiveDecimalWritable sumTmp = new HiveDecimalWritable(dec.getSum());
            if (sumTmp.getHiveDecimal().integerDigitCount() + scale <= 18) {
               this.hasSum = true;
               this.sum = sumTmp.serialize64(scale);
               return;
            }
         }

         this.hasSum = false;
      }

      public void reset() {
         super.reset();
         this.minimum = Long.MAX_VALUE;
         this.maximum = Long.MIN_VALUE;
         this.hasSum = true;
         this.sum = 0L;
      }

      public void updateDecimal(HiveDecimalWritable value) {
         this.updateDecimal64(value.serialize64(this.scale), this.scale);
      }

      public void updateDecimal64(long value, int valueScale) {
         while(valueScale != this.scale) {
            if (valueScale > this.scale) {
               value /= 10L;
               --valueScale;
            } else {
               value *= 10L;
               ++valueScale;
            }
         }

         if (value >= -999999999999999999L && value <= 999999999999999999L) {
            if (this.minimum > value) {
               this.minimum = value;
            }

            if (this.maximum < value) {
               this.maximum = value;
            }

            if (this.hasSum) {
               this.sum += value;
               this.hasSum = this.sum <= 999999999999999999L && this.sum >= -999999999999999999L;
            }

         } else {
            throw new IllegalArgumentException("Out of bounds decimal64 " + value);
         }
      }

      public void merge(ColumnStatisticsImpl other) {
         if (other instanceof Decimal64StatisticsImpl dec) {
            if (this.getNumberOfValues() == 0L) {
               this.minimum = dec.minimum;
               this.maximum = dec.maximum;
               this.sum = dec.sum;
            } else {
               if (this.minimum > dec.minimum) {
                  this.minimum = dec.minimum;
               }

               if (this.maximum < dec.maximum) {
                  this.maximum = dec.maximum;
               }

               if (this.hasSum && dec.hasSum) {
                  this.sum += dec.sum;
                  this.hasSum = this.sum <= 999999999999999999L && this.sum >= -999999999999999999L;
               } else {
                  this.hasSum = false;
               }
            }
         } else {
            if (!(other instanceof DecimalColumnStatistics)) {
               throw new IllegalArgumentException("Incompatible merging of decimal column statistics");
            }

            if (other.getNumberOfValues() != 0L) {
               throw new IllegalArgumentException("Incompatible merging of decimal column statistics");
            }
         }

         super.merge(other);
      }

      public OrcProto.ColumnStatistics.Builder serialize() {
         OrcProto.ColumnStatistics.Builder result = super.serialize();
         OrcProto.DecimalStatistics.Builder dec = DecimalStatistics.newBuilder();
         if (this.getNumberOfValues() != 0L) {
            this.scratch.setFromLongAndScale(this.minimum, this.scale);
            dec.setMinimum(this.scratch.toString());
            this.scratch.setFromLongAndScale(this.maximum, this.scale);
            dec.setMaximum(this.scratch.toString());
         }

         if (this.hasSum) {
            this.scratch.setFromLongAndScale(this.sum, this.scale);
            dec.setSum(this.scratch.toString());
         }

         result.setDecimalStatistics(dec);
         return result;
      }

      public HiveDecimal getMinimum() {
         if (this.getNumberOfValues() > 0L) {
            this.scratch.setFromLongAndScale(this.minimum, this.scale);
            return this.scratch.getHiveDecimal();
         } else {
            return null;
         }
      }

      public HiveDecimal getMaximum() {
         if (this.getNumberOfValues() > 0L) {
            this.scratch.setFromLongAndScale(this.maximum, this.scale);
            return this.scratch.getHiveDecimal();
         } else {
            return null;
         }
      }

      public HiveDecimal getSum() {
         if (this.hasSum) {
            this.scratch.setFromLongAndScale(this.sum, this.scale);
            return this.scratch.getHiveDecimal();
         } else {
            return null;
         }
      }

      public String toString() {
         StringBuilder buf = new StringBuilder(super.toString());
         if (this.getNumberOfValues() != 0L) {
            buf.append(" min: ");
            buf.append(this.getMinimum());
            buf.append(" max: ");
            buf.append(this.getMaximum());
            if (this.hasSum) {
               buf.append(" sum: ");
               buf.append(this.getSum());
            }
         }

         return buf.toString();
      }

      public boolean equals(Object o) {
         if (this == o) {
            return true;
         } else if (o instanceof Decimal64StatisticsImpl) {
            Decimal64StatisticsImpl that = (Decimal64StatisticsImpl)o;
            if (!super.equals(o)) {
               return false;
            } else if (this.minimum == that.minimum && this.maximum == that.maximum && this.hasSum == that.hasSum) {
               return !this.hasSum || this.sum == that.sum;
            } else {
               return false;
            }
         } else {
            return false;
         }
      }

      public int hashCode() {
         int result = super.hashCode();
         boolean hasValues = this.getNumberOfValues() > 0L;
         result = 31 * result + (hasValues ? (int)this.minimum : 0);
         result = 31 * result + (hasValues ? (int)this.maximum : 0);
         result = 31 * result + (this.hasSum ? (int)this.sum : 0);
         return result;
      }
   }

   private static final class DateStatisticsImpl extends ColumnStatisticsImpl implements DateColumnStatistics {
      private int minimum = Integer.MAX_VALUE;
      private int maximum = Integer.MIN_VALUE;
      private final Chronology chronology;

      static Chronology getInstance(boolean proleptic) {
         return (Chronology)(proleptic ? IsoChronology.INSTANCE : HybridChronology.INSTANCE);
      }

      DateStatisticsImpl(boolean convertToProleptic) {
         this.chronology = getInstance(convertToProleptic);
      }

      DateStatisticsImpl(OrcProto.ColumnStatistics stats, boolean writerUsedProlepticGregorian, boolean convertToProlepticGregorian) {
         super(stats);
         this.chronology = getInstance(convertToProlepticGregorian);
         OrcProto.DateStatistics dateStats = stats.getDateStatistics();
         if (dateStats.hasMaximum()) {
            this.maximum = DateUtils.convertDate(dateStats.getMaximum(), writerUsedProlepticGregorian, convertToProlepticGregorian);
         }

         if (dateStats.hasMinimum()) {
            this.minimum = DateUtils.convertDate(dateStats.getMinimum(), writerUsedProlepticGregorian, convertToProlepticGregorian);
         }

      }

      public void reset() {
         super.reset();
         this.minimum = Integer.MAX_VALUE;
         this.maximum = Integer.MIN_VALUE;
      }

      public void updateDate(DateWritable value) {
         if (this.minimum > value.getDays()) {
            this.minimum = value.getDays();
         }

         if (this.maximum < value.getDays()) {
            this.maximum = value.getDays();
         }

      }

      public void updateDate(int value) {
         if (this.minimum > value) {
            this.minimum = value;
         }

         if (this.maximum < value) {
            this.maximum = value;
         }

      }

      public void merge(ColumnStatisticsImpl other) {
         if (other instanceof DateStatisticsImpl dateStats) {
            this.minimum = Math.min(this.minimum, dateStats.minimum);
            this.maximum = Math.max(this.maximum, dateStats.maximum);
         } else {
            if (!(other instanceof DateColumnStatistics)) {
               throw new IllegalArgumentException("Incompatible merging of date column statistics");
            }

            if (this.isStatsExists() && this.count != 0L) {
               throw new IllegalArgumentException("Incompatible merging of date column statistics");
            }
         }

         super.merge(other);
      }

      public OrcProto.ColumnStatistics.Builder serialize() {
         OrcProto.ColumnStatistics.Builder result = super.serialize();
         OrcProto.DateStatistics.Builder dateStats = DateStatistics.newBuilder();
         if (this.count != 0L) {
            dateStats.setMinimum(this.minimum);
            dateStats.setMaximum(this.maximum);
         }

         result.setDateStatistics(dateStats);
         return result;
      }

      public ChronoLocalDate getMinimumLocalDate() {
         return this.count == 0L ? null : this.chronology.dateEpochDay((long)this.minimum);
      }

      public long getMinimumDayOfEpoch() {
         return (long)this.minimum;
      }

      public ChronoLocalDate getMaximumLocalDate() {
         return this.count == 0L ? null : this.chronology.dateEpochDay((long)this.maximum);
      }

      public long getMaximumDayOfEpoch() {
         return (long)this.maximum;
      }

      public Date getMinimum() {
         if (this.count == 0L) {
            return null;
         } else {
            DateWritable minDate = new DateWritable(this.minimum);
            return minDate.get();
         }
      }

      public Date getMaximum() {
         if (this.count == 0L) {
            return null;
         } else {
            DateWritable maxDate = new DateWritable(this.maximum);
            return maxDate.get();
         }
      }

      public String toString() {
         StringBuilder buf = new StringBuilder(super.toString());
         if (this.getNumberOfValues() != 0L) {
            buf.append(" min: ");
            buf.append(this.getMinimumLocalDate());
            buf.append(" max: ");
            buf.append(this.getMaximumLocalDate());
         }

         return buf.toString();
      }

      public boolean equals(Object o) {
         if (this == o) {
            return true;
         } else if (o instanceof DateStatisticsImpl) {
            DateStatisticsImpl that = (DateStatisticsImpl)o;
            if (!super.equals(o)) {
               return false;
            } else if (this.minimum != that.minimum) {
               return false;
            } else {
               return this.maximum == that.maximum;
            }
         } else {
            return false;
         }
      }

      public int hashCode() {
         int result = super.hashCode();
         result = 31 * result + this.minimum;
         result = 31 * result + this.maximum;
         return result;
      }
   }

   private static class TimestampStatisticsImpl extends ColumnStatisticsImpl implements TimestampColumnStatistics {
      private static final int DEFAULT_MIN_NANOS = 0;
      private static final int DEFAULT_MAX_NANOS = 999999;
      private long minimum = Long.MAX_VALUE;
      private long maximum = Long.MIN_VALUE;
      private int minNanos = 0;
      private int maxNanos = 999999;

      TimestampStatisticsImpl() {
      }

      TimestampStatisticsImpl(OrcProto.ColumnStatistics stats, boolean writerUsedProlepticGregorian, boolean convertToProlepticGregorian) {
         super(stats);
         OrcProto.TimestampStatistics timestampStats = stats.getTimestampStatistics();
         if (timestampStats.hasMaximum()) {
            this.maximum = DateUtils.convertTime(SerializationUtils.convertToUtc(TimeZone.getDefault(), timestampStats.getMaximum()), writerUsedProlepticGregorian, convertToProlepticGregorian, true);
         }

         if (timestampStats.hasMinimum()) {
            this.minimum = DateUtils.convertTime(SerializationUtils.convertToUtc(TimeZone.getDefault(), timestampStats.getMinimum()), writerUsedProlepticGregorian, convertToProlepticGregorian, true);
         }

         if (timestampStats.hasMaximumUtc()) {
            this.maximum = DateUtils.convertTime(timestampStats.getMaximumUtc(), writerUsedProlepticGregorian, convertToProlepticGregorian, true);
         }

         if (timestampStats.hasMinimumUtc()) {
            this.minimum = DateUtils.convertTime(timestampStats.getMinimumUtc(), writerUsedProlepticGregorian, convertToProlepticGregorian, true);
         }

         if (timestampStats.hasMaximumNanos()) {
            this.maxNanos = timestampStats.getMaximumNanos() - 1;
         }

         if (timestampStats.hasMinimumNanos()) {
            this.minNanos = timestampStats.getMinimumNanos() - 1;
         }

      }

      public void reset() {
         super.reset();
         this.minimum = Long.MAX_VALUE;
         this.maximum = Long.MIN_VALUE;
         this.minNanos = 0;
         this.maxNanos = 999999;
      }

      public void updateTimestamp(Timestamp value) {
         long millis = SerializationUtils.convertToUtc(TimeZone.getDefault(), value.getTime());
         this.updateTimestamp(millis, value.getNanos() % 1000000);
      }

      public void updateTimestamp(long value, int nanos) {
         if (this.minimum > this.maximum) {
            this.minimum = value;
            this.maximum = value;
            this.minNanos = nanos;
            this.maxNanos = nanos;
         } else {
            if (this.minimum >= value) {
               if (this.minimum > value || nanos < this.minNanos) {
                  this.minNanos = nanos;
               }

               this.minimum = value;
            }

            if (this.maximum <= value) {
               if (this.maximum < value || nanos > this.maxNanos) {
                  this.maxNanos = nanos;
               }

               this.maximum = value;
            }
         }

      }

      public void merge(ColumnStatisticsImpl other) {
         if (other instanceof TimestampStatisticsImpl timestampStats) {
            if (this.count == 0L) {
               if (timestampStats.count != 0L) {
                  this.minimum = timestampStats.minimum;
                  this.maximum = timestampStats.maximum;
                  this.minNanos = timestampStats.minNanos;
                  this.maxNanos = timestampStats.maxNanos;
               }
            } else if (timestampStats.count != 0L) {
               if (this.minimum >= timestampStats.minimum) {
                  if (this.minimum > timestampStats.minimum || this.minNanos > timestampStats.minNanos) {
                     this.minNanos = timestampStats.minNanos;
                  }

                  this.minimum = timestampStats.minimum;
               }

               if (this.maximum <= timestampStats.maximum) {
                  if (this.maximum < timestampStats.maximum || this.maxNanos < timestampStats.maxNanos) {
                     this.maxNanos = timestampStats.maxNanos;
                  }

                  this.maximum = timestampStats.maximum;
               }
            }
         } else {
            if (!(other instanceof TimestampColumnStatistics)) {
               throw new IllegalArgumentException("Incompatible merging of timestamp column statistics");
            }

            if (this.isStatsExists() && this.count != 0L) {
               throw new IllegalArgumentException("Incompatible merging of timestamp column statistics");
            }
         }

         super.merge(other);
      }

      public OrcProto.ColumnStatistics.Builder serialize() {
         OrcProto.ColumnStatistics.Builder result = super.serialize();
         OrcProto.TimestampStatistics.Builder timestampStats = TimestampStatistics.newBuilder();
         if (this.getNumberOfValues() != 0L) {
            timestampStats.setMinimumUtc(this.minimum);
            timestampStats.setMaximumUtc(this.maximum);
            if (this.minNanos != 0) {
               timestampStats.setMinimumNanos(this.minNanos + 1);
            }

            if (this.maxNanos != 999999) {
               timestampStats.setMaximumNanos(this.maxNanos + 1);
            }
         }

         result.setTimestampStatistics(timestampStats);
         return result;
      }

      public Timestamp getMinimum() {
         if (this.minimum > this.maximum) {
            return null;
         } else {
            Timestamp ts = new Timestamp(SerializationUtils.convertFromUtc(TimeZone.getDefault(), this.minimum));
            ts.setNanos(ts.getNanos() + this.minNanos);
            return ts;
         }
      }

      public Timestamp getMaximum() {
         if (this.minimum > this.maximum) {
            return null;
         } else {
            Timestamp ts = new Timestamp(SerializationUtils.convertFromUtc(TimeZone.getDefault(), this.maximum));
            ts.setNanos(ts.getNanos() + this.maxNanos);
            return ts;
         }
      }

      public Timestamp getMinimumUTC() {
         if (this.minimum > this.maximum) {
            return null;
         } else {
            Timestamp ts = new Timestamp(this.minimum);
            ts.setNanos(ts.getNanos() + this.minNanos);
            return ts;
         }
      }

      public Timestamp getMaximumUTC() {
         if (this.minimum > this.maximum) {
            return null;
         } else {
            Timestamp ts = new Timestamp(this.maximum);
            ts.setNanos(ts.getNanos() + this.maxNanos);
            return ts;
         }
      }

      public String toString() {
         StringBuilder buf = new StringBuilder(super.toString());
         if (this.minimum <= this.maximum) {
            buf.append(" min: ");
            buf.append(this.getMinimum());
            buf.append(" max: ");
            buf.append(this.getMaximum());
         }

         return buf.toString();
      }

      public boolean equals(Object o) {
         if (this == o) {
            return true;
         } else if (o instanceof TimestampStatisticsImpl) {
            TimestampStatisticsImpl that = (TimestampStatisticsImpl)o;
            if (!super.equals(o)) {
               return false;
            } else {
               return this.minimum == that.minimum && this.maximum == that.maximum && this.minNanos == that.minNanos && this.maxNanos == that.maxNanos;
            }
         } else {
            return false;
         }
      }

      public int hashCode() {
         int prime = 31;
         int result = super.hashCode();
         result = 31 * result + (int)(this.maximum ^ this.maximum >>> 32);
         result = 31 * result + (int)(this.minimum ^ this.minimum >>> 32);
         return result;
      }
   }

   private static final class TimestampInstantStatisticsImpl extends TimestampStatisticsImpl {
      TimestampInstantStatisticsImpl() {
      }

      TimestampInstantStatisticsImpl(OrcProto.ColumnStatistics stats, boolean writerUsedProlepticGregorian, boolean convertToProlepticGregorian) {
         super(stats, writerUsedProlepticGregorian, convertToProlepticGregorian);
      }

      public void updateTimestamp(Timestamp value) {
         this.updateTimestamp(value.getTime(), value.getNanos() % 1000000);
      }

      public Timestamp getMinimum() {
         return this.getMinimumUTC();
      }

      public Timestamp getMaximum() {
         return this.getMaximumUTC();
      }
   }
}
