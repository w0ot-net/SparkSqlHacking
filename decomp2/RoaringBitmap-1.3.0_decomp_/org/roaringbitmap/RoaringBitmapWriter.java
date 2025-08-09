package org.roaringbitmap;

import java.util.function.Supplier;
import org.roaringbitmap.buffer.MappeableArrayContainer;
import org.roaringbitmap.buffer.MappeableRunContainer;
import org.roaringbitmap.buffer.MutableRoaringArray;
import org.roaringbitmap.buffer.MutableRoaringBitmap;

public interface RoaringBitmapWriter extends Supplier {
   static Wizard writer() {
      return new RoaringBitmapWizard();
   }

   static Wizard bufferWriter() {
      return new BufferWizard();
   }

   BitmapDataProvider getUnderlying();

   void add(int var1);

   void add(long var1, long var3);

   void addMany(int... var1);

   void flush();

   default BitmapDataProvider get() {
      this.flush();
      return this.getUnderlying();
   }

   void reset();

   public abstract static class Wizard implements Supplier {
      protected int initialCapacity = 4;
      protected boolean constantMemory;
      protected boolean partiallySortValues = false;
      protected boolean runCompress = true;
      protected Supplier containerSupplier = this.arraySupplier();
      protected int expectedContainerSize = 16;

      Wizard() {
      }

      public Wizard optimiseForArrays() {
         this.containerSupplier = this.arraySupplier();
         return this;
      }

      public Wizard optimiseForRuns() {
         this.containerSupplier = this.runSupplier();
         return this;
      }

      public Wizard runCompress(boolean runCompress) {
         this.runCompress = runCompress;
         return this;
      }

      public Wizard expectedValuesPerContainer(int count) {
         sanityCheck(count);
         this.expectedContainerSize = count;
         if (count < 4096) {
            return this.optimiseForArrays();
         } else {
            return count < 16384 ? this.constantMemory() : this.optimiseForRuns();
         }
      }

      public Wizard fastRank() {
         throw new IllegalStateException("Fast rank not yet implemented for byte buffers");
      }

      public Wizard constantMemory() {
         this.constantMemory = true;
         return this;
      }

      public Wizard expectedDensity(double density) {
         return this.expectedValuesPerContainer((int)((double)65535.0F * density));
      }

      public Wizard expectedRange(long min, long max) {
         return this.initialCapacity((int)(max - min >>> 16) + 1);
      }

      public Wizard initialCapacity(int count) {
         sanityCheck(count);
         this.initialCapacity = count;
         return this;
      }

      public Wizard doPartialRadixSort() {
         this.partiallySortValues = true;
         return this;
      }

      protected abstract Supplier arraySupplier();

      protected abstract Supplier runSupplier();

      protected abstract BitmapDataProvider createUnderlying(int var1);

      public RoaringBitmapWriter get() {
         int capacity = this.initialCapacity;
         return new ContainerAppender(this.partiallySortValues, this.runCompress, () -> this.createUnderlying(capacity), this.containerSupplier);
      }

      private static void sanityCheck(int count) {
         if (count >= 65535) {
            throw new IllegalArgumentException(count + " > 65536");
         } else if (count < 0) {
            throw new IllegalArgumentException(count + " < 0");
         }
      }
   }

   public static class BufferWizard extends Wizard {
      protected Supplier arraySupplier() {
         int size = this.expectedContainerSize;
         return () -> new MappeableArrayContainer(size);
      }

      protected Supplier runSupplier() {
         return MappeableRunContainer::new;
      }

      protected MutableRoaringBitmap createUnderlying(int initialCapacity) {
         return new MutableRoaringBitmap(new MutableRoaringArray(initialCapacity));
      }
   }

   public abstract static class RoaringWizard extends Wizard {
      protected Supplier arraySupplier() {
         int size = this.expectedContainerSize;
         return () -> new ArrayContainer(size);
      }

      protected Supplier runSupplier() {
         return RunContainer::new;
      }

      public Wizard fastRank() {
         return new FastRankRoaringBitmapWizard(this);
      }

      public RoaringBitmapWriter get() {
         if (this.constantMemory) {
            int capacity = this.initialCapacity;
            return new ConstantMemoryContainerAppender(this.partiallySortValues, this.runCompress, () -> (RoaringBitmap)this.createUnderlying(capacity));
         } else {
            return super.get();
         }
      }
   }

   public static class FastRankRoaringBitmapWizard extends RoaringWizard {
      FastRankRoaringBitmapWizard(Wizard wizard) {
         this.constantMemory = wizard.constantMemory;
         this.initialCapacity = wizard.initialCapacity;
         this.containerSupplier = wizard.containerSupplier;
         this.partiallySortValues = wizard.partiallySortValues;
      }

      protected FastRankRoaringBitmap createUnderlying(int initialCapacity) {
         return new FastRankRoaringBitmap(new RoaringArray(initialCapacity));
      }
   }

   public static class RoaringBitmapWizard extends RoaringWizard {
      protected RoaringBitmap createUnderlying(int initialCapacity) {
         return new RoaringBitmap(new RoaringArray(initialCapacity));
      }
   }
}
