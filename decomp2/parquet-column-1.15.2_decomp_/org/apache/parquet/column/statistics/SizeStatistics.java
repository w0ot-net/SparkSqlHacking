package org.apache.parquet.column.statistics;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.apache.parquet.Preconditions;
import org.apache.parquet.io.api.Binary;
import org.apache.parquet.schema.PrimitiveType;
import shaded.parquet.it.unimi.dsi.fastutil.longs.LongArrayList;

public class SizeStatistics {
   private final PrimitiveType type;
   private long unencodedByteArrayDataBytes;
   private final List repetitionLevelHistogram;
   private final List definitionLevelHistogram;
   private boolean valid = true;

   public static Builder newBuilder(PrimitiveType type, int maxRepetitionLevel, int maxDefinitionLevel) {
      return new Builder(type, maxRepetitionLevel, maxDefinitionLevel);
   }

   public SizeStatistics(PrimitiveType type, long unencodedByteArrayDataBytes, List repetitionLevelHistogram, List definitionLevelHistogram) {
      this.type = type;
      this.unencodedByteArrayDataBytes = unencodedByteArrayDataBytes;
      this.repetitionLevelHistogram = repetitionLevelHistogram == null ? Collections.emptyList() : repetitionLevelHistogram;
      this.definitionLevelHistogram = definitionLevelHistogram == null ? Collections.emptyList() : definitionLevelHistogram;
   }

   public void mergeStatistics(SizeStatistics other) {
      if (this.valid) {
         if (other != null && other.isValid()) {
            Preconditions.checkArgument(this.type.equals(other.type), "Cannot merge SizeStatistics of different types");
            this.unencodedByteArrayDataBytes = Math.addExact(this.unencodedByteArrayDataBytes, other.unencodedByteArrayDataBytes);

            for(int i = 0; i < this.repetitionLevelHistogram.size(); ++i) {
               this.repetitionLevelHistogram.set(i, Math.addExact((Long)this.repetitionLevelHistogram.get(i), (Long)other.repetitionLevelHistogram.get(i)));
            }

            for(int i = 0; i < this.definitionLevelHistogram.size(); ++i) {
               this.definitionLevelHistogram.set(i, Math.addExact((Long)this.definitionLevelHistogram.get(i), (Long)other.getDefinitionLevelHistogram().get(i)));
            }

         } else {
            this.valid = false;
            this.unencodedByteArrayDataBytes = 0L;
            this.repetitionLevelHistogram.clear();
            this.definitionLevelHistogram.clear();
         }
      }
   }

   public PrimitiveType getType() {
      return this.type;
   }

   public Optional getUnencodedByteArrayDataBytes() {
      return this.type.getPrimitiveTypeName() != PrimitiveType.PrimitiveTypeName.BINARY ? Optional.empty() : Optional.of(this.unencodedByteArrayDataBytes);
   }

   public List getRepetitionLevelHistogram() {
      return Collections.unmodifiableList(this.repetitionLevelHistogram);
   }

   public List getDefinitionLevelHistogram() {
      return Collections.unmodifiableList(this.definitionLevelHistogram);
   }

   public SizeStatistics copy() {
      return new SizeStatistics(this.type, this.unencodedByteArrayDataBytes, new LongArrayList(this.repetitionLevelHistogram), new LongArrayList(this.definitionLevelHistogram));
   }

   public boolean isValid() {
      return this.valid;
   }

   public static Builder noopBuilder(PrimitiveType type, int maxRepetitionLevel, int maxDefinitionLevel) {
      return new NoopBuilder(type, maxRepetitionLevel, maxDefinitionLevel);
   }

   public static class Builder {
      protected final PrimitiveType type;
      private long unencodedByteArrayDataBytes;
      private final long[] repetitionLevelHistogram;
      private final long[] definitionLevelHistogram;

      private Builder(PrimitiveType type, int maxRepetitionLevel, int maxDefinitionLevel) {
         this.type = type;
         this.unencodedByteArrayDataBytes = 0L;
         this.repetitionLevelHistogram = new long[maxRepetitionLevel + 1];
         this.definitionLevelHistogram = new long[maxDefinitionLevel + 1];
      }

      public void add(int repetitionLevel, int definitionLevel) {
         int var10002 = this.repetitionLevelHistogram[repetitionLevel]++;
         var10002 = this.definitionLevelHistogram[definitionLevel]++;
      }

      public void add(int repetitionLevel, int definitionLevel, Binary value) {
         this.add(repetitionLevel, definitionLevel);
         if (this.type.getPrimitiveTypeName() == PrimitiveType.PrimitiveTypeName.BINARY && value != null) {
            this.unencodedByteArrayDataBytes += (long)value.length();
         }

      }

      public SizeStatistics build() {
         return new SizeStatistics(this.type, this.unencodedByteArrayDataBytes, new LongArrayList(this.repetitionLevelHistogram), new LongArrayList(this.definitionLevelHistogram));
      }
   }

   private static class NoopBuilder extends Builder {
      private NoopBuilder(PrimitiveType type, int maxRepetitionLevel, int maxDefinitionLevel) {
         super(type, maxRepetitionLevel, maxDefinitionLevel, null);
      }

      public void add(int repetitionLevel, int definitionLevel) {
      }

      public void add(int repetitionLevel, int definitionLevel, Binary value) {
      }

      public SizeStatistics build() {
         SizeStatistics stats = new SizeStatistics(this.type, 0L, Collections.emptyList(), Collections.emptyList());
         stats.valid = false;
         return stats;
      }
   }
}
