package org.apache.parquet.column.statistics;

import java.util.Arrays;
import org.apache.parquet.column.UnknownColumnTypeException;
import org.apache.parquet.io.api.Binary;
import org.apache.parquet.schema.Float16;
import org.apache.parquet.schema.LogicalTypeAnnotation;
import org.apache.parquet.schema.PrimitiveComparator;
import org.apache.parquet.schema.PrimitiveStringifier;
import org.apache.parquet.schema.PrimitiveType;
import org.apache.parquet.schema.Type;

public abstract class Statistics {
   private final PrimitiveType type;
   private final PrimitiveComparator comparator;
   private boolean hasNonNullValue;
   private long num_nulls;
   final PrimitiveStringifier stringifier;

   Statistics(PrimitiveType type) {
      this.type = type;
      this.comparator = type.comparator();
      this.stringifier = type.stringifier();
      this.hasNonNullValue = false;
      this.num_nulls = 0L;
   }

   /** @deprecated */
   @Deprecated
   public static Statistics getStatsBasedOnType(PrimitiveType.PrimitiveTypeName type) {
      switch (type) {
         case INT32:
            return new IntStatistics();
         case INT64:
            return new LongStatistics();
         case FLOAT:
            return new FloatStatistics();
         case DOUBLE:
            return new DoubleStatistics();
         case BOOLEAN:
            return new BooleanStatistics();
         case BINARY:
            return new BinaryStatistics();
         case INT96:
            return new BinaryStatistics();
         case FIXED_LEN_BYTE_ARRAY:
            return new BinaryStatistics();
         default:
            throw new UnknownColumnTypeException(type);
      }
   }

   public static Statistics createStats(Type type) {
      PrimitiveType primitive = type.asPrimitiveType();
      switch (primitive.getPrimitiveTypeName()) {
         case INT32:
            return new IntStatistics(primitive);
         case INT64:
            return new LongStatistics(primitive);
         case FLOAT:
            return new FloatStatistics(primitive);
         case DOUBLE:
            return new DoubleStatistics(primitive);
         case BOOLEAN:
            return new BooleanStatistics(primitive);
         case BINARY:
         case INT96:
         case FIXED_LEN_BYTE_ARRAY:
            return new BinaryStatistics(primitive);
         default:
            throw new UnknownColumnTypeException(primitive.getPrimitiveTypeName());
      }
   }

   public static Statistics noopStats(Type type) {
      return new NoopStatistics(type.asPrimitiveType());
   }

   public static Builder getBuilderForReading(PrimitiveType type) {
      switch (type.getPrimitiveTypeName()) {
         case FLOAT:
            return new FloatBuilder(type);
         case DOUBLE:
            return new DoubleBuilder(type);
         case FIXED_LEN_BYTE_ARRAY:
            LogicalTypeAnnotation logicalTypeAnnotation = type.getLogicalTypeAnnotation();
            if (logicalTypeAnnotation instanceof LogicalTypeAnnotation.Float16LogicalTypeAnnotation) {
               return new Float16Builder(type);
            }
         default:
            return new Builder(type);
      }
   }

   public void updateStats(int value) {
      throw new UnsupportedOperationException();
   }

   public void updateStats(long value) {
      throw new UnsupportedOperationException();
   }

   public void updateStats(float value) {
      throw new UnsupportedOperationException();
   }

   public void updateStats(double value) {
      throw new UnsupportedOperationException();
   }

   public void updateStats(boolean value) {
      throw new UnsupportedOperationException();
   }

   public void updateStats(Binary value) {
      throw new UnsupportedOperationException();
   }

   public boolean equals(Object other) {
      if (other == this) {
         return true;
      } else if (!(other instanceof Statistics)) {
         return false;
      } else {
         Statistics stats = (Statistics)other;
         return this.type.equals(stats.type) && Arrays.equals(stats.getMaxBytes(), this.getMaxBytes()) && Arrays.equals(stats.getMinBytes(), this.getMinBytes()) && stats.getNumNulls() == this.getNumNulls();
      }
   }

   public int hashCode() {
      return 31 * this.type.hashCode() + 31 * Arrays.hashCode(this.getMaxBytes()) + 17 * Arrays.hashCode(this.getMinBytes()) + Long.valueOf(this.getNumNulls()).hashCode();
   }

   public void mergeStatistics(Statistics stats) {
      if (!stats.isEmpty()) {
         if (this.type.equals(stats.type)) {
            this.incrementNumNulls(stats.getNumNulls());
            if (stats.hasNonNullValue()) {
               this.mergeStatisticsMinMax(stats);
               this.markAsNotEmpty();
            }

         } else {
            throw StatisticsClassException.create(this, stats);
         }
      }
   }

   protected abstract void mergeStatisticsMinMax(Statistics var1);

   /** @deprecated */
   @Deprecated
   public abstract void setMinMaxFromBytes(byte[] var1, byte[] var2);

   public abstract Comparable genericGetMin();

   public abstract Comparable genericGetMax();

   public final PrimitiveComparator comparator() {
      return this.comparator;
   }

   public final int compareMinToValue(Comparable value) {
      return this.comparator.compare(this.genericGetMin(), value);
   }

   public final int compareMaxToValue(Comparable value) {
      return this.comparator.compare(this.genericGetMax(), value);
   }

   public abstract byte[] getMaxBytes();

   public abstract byte[] getMinBytes();

   public String minAsString() {
      return this.stringify(this.genericGetMin());
   }

   public String maxAsString() {
      return this.stringify(this.genericGetMax());
   }

   abstract String stringify(Comparable var1);

   public abstract boolean isSmallerThan(long var1);

   public String toString() {
      if (this.hasNonNullValue()) {
         return this.isNumNullsSet() ? String.format("min: %s, max: %s, num_nulls: %d", this.minAsString(), this.maxAsString(), this.getNumNulls()) : String.format("min: %s, max: %s, num_nulls not defined", this.minAsString(), this.maxAsString());
      } else {
         return !this.isEmpty() ? String.format("num_nulls: %d, min/max not defined", this.getNumNulls()) : "no stats for this column";
      }
   }

   public void incrementNumNulls() {
      ++this.num_nulls;
   }

   public void incrementNumNulls(long increment) {
      this.num_nulls += increment;
   }

   public long getNumNulls() {
      return this.num_nulls;
   }

   /** @deprecated */
   @Deprecated
   public void setNumNulls(long nulls) {
      this.num_nulls = nulls;
   }

   public boolean isEmpty() {
      return !this.hasNonNullValue && !this.isNumNullsSet();
   }

   public boolean hasNonNullValue() {
      return this.hasNonNullValue;
   }

   public boolean isNumNullsSet() {
      return this.num_nulls >= 0L;
   }

   protected void markAsNotEmpty() {
      this.hasNonNullValue = true;
   }

   public abstract Statistics copy();

   public PrimitiveType type() {
      return this.type;
   }

   public static class Builder {
      private final PrimitiveType type;
      private byte[] min;
      private byte[] max;
      private long numNulls;

      private Builder(PrimitiveType type) {
         this.numNulls = -1L;
         this.type = type;
      }

      public Builder withMin(byte[] min) {
         this.min = min;
         return this;
      }

      public Builder withMax(byte[] max) {
         this.max = max;
         return this;
      }

      public Builder withNumNulls(long numNulls) {
         this.numNulls = numNulls;
         return this;
      }

      public Statistics build() {
         Statistics<?> stats = Statistics.createStats(this.type);
         if (this.min != null && this.max != null) {
            stats.setMinMaxFromBytes(this.min, this.max);
         }

         stats.num_nulls = this.numNulls;
         return stats;
      }
   }

   private static class FloatBuilder extends Builder {
      public FloatBuilder(PrimitiveType type) {
         super(type, null);

         assert type.getPrimitiveTypeName() == PrimitiveType.PrimitiveTypeName.FLOAT;

      }

      public Statistics build() {
         FloatStatistics stats = (FloatStatistics)super.build();
         if (stats.hasNonNullValue()) {
            Float min = stats.genericGetMin();
            Float max = stats.genericGetMax();
            if (!min.isNaN() && !max.isNaN()) {
               if (Float.compare(min, 0.0F) == 0) {
                  min = -0.0F;
                  stats.setMinMax(min, max);
               }

               if (Float.compare(max, -0.0F) == 0) {
                  max = 0.0F;
                  stats.setMinMax(min, max);
               }
            } else {
               stats.setMinMax(0.0F, 0.0F);
               stats.hasNonNullValue = false;
            }
         }

         return stats;
      }
   }

   private static class DoubleBuilder extends Builder {
      public DoubleBuilder(PrimitiveType type) {
         super(type, null);

         assert type.getPrimitiveTypeName() == PrimitiveType.PrimitiveTypeName.DOUBLE;

      }

      public Statistics build() {
         DoubleStatistics stats = (DoubleStatistics)super.build();
         if (stats.hasNonNullValue()) {
            Double min = stats.genericGetMin();
            Double max = stats.genericGetMax();
            if (!min.isNaN() && !max.isNaN()) {
               if (Double.compare(min, (double)0.0F) == 0) {
                  min = (double)-0.0F;
                  stats.setMinMax(min, max);
               }

               if (Double.compare(max, (double)-0.0F) == 0) {
                  max = (double)0.0F;
                  stats.setMinMax(min, max);
               }
            } else {
               stats.setMinMax((double)0.0F, (double)0.0F);
               stats.hasNonNullValue = false;
            }
         }

         return stats;
      }
   }

   private static class Float16Builder extends Builder {
      private static final Binary POSITIVE_ZERO_LITTLE_ENDIAN = Binary.fromConstantByteArray(new byte[]{0, 0});
      private static final Binary NEGATIVE_ZERO_LITTLE_ENDIAN = Binary.fromConstantByteArray(new byte[]{0, -128});

      public Float16Builder(PrimitiveType type) {
         super(type, null);

         assert type.getPrimitiveTypeName() == PrimitiveType.PrimitiveTypeName.FIXED_LEN_BYTE_ARRAY;

         assert type.getTypeLength() == 2;

      }

      public Statistics build() {
         BinaryStatistics stats = (BinaryStatistics)super.build();
         if (stats.hasNonNullValue()) {
            Binary bMin = stats.genericGetMin();
            Binary bMax = stats.genericGetMax();
            short min = bMin.get2BytesLittleEndian();
            short max = bMax.get2BytesLittleEndian();
            if (!Float16.isNaN(min) && !Float16.isNaN(max)) {
               if (min == 0) {
                  stats.setMinMax(NEGATIVE_ZERO_LITTLE_ENDIAN, bMax);
               }

               if (max == Short.MIN_VALUE) {
                  stats.setMinMax(bMin, POSITIVE_ZERO_LITTLE_ENDIAN);
               }
            } else {
               stats.setMinMax(POSITIVE_ZERO_LITTLE_ENDIAN, NEGATIVE_ZERO_LITTLE_ENDIAN);
               stats.hasNonNullValue = false;
            }
         }

         return stats;
      }
   }
}
