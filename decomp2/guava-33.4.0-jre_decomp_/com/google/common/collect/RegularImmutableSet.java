package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.annotations.VisibleForTesting;
import java.util.Spliterator;
import java.util.Spliterators;
import javax.annotation.CheckForNull;
import org.checkerframework.checker.nullness.qual.Nullable;

@ElementTypesAreNonnullByDefault
@GwtCompatible(
   serializable = true,
   emulated = true
)
final class RegularImmutableSet extends ImmutableSet.CachingAsList {
   private static final Object[] EMPTY_ARRAY = new Object[0];
   static final RegularImmutableSet EMPTY;
   private final transient Object[] elements;
   private final transient int hashCode;
   @VisibleForTesting
   final transient @Nullable Object[] table;
   private final transient int mask;

   RegularImmutableSet(Object[] elements, int hashCode, @Nullable Object[] table, int mask) {
      this.elements = elements;
      this.hashCode = hashCode;
      this.table = table;
      this.mask = mask;
   }

   public boolean contains(@CheckForNull Object target) {
      Object[] table = this.table;
      if (target != null && table.length != 0) {
         int i = Hashing.smearedHash(target);

         while(true) {
            i &= this.mask;
            Object candidate = table[i];
            if (candidate == null) {
               return false;
            }

            if (candidate.equals(target)) {
               return true;
            }

            ++i;
         }
      } else {
         return false;
      }
   }

   public int size() {
      return this.elements.length;
   }

   public UnmodifiableIterator iterator() {
      return Iterators.forArray(this.elements);
   }

   public Spliterator spliterator() {
      return Spliterators.spliterator(this.elements, 1297);
   }

   Object[] internalArray() {
      return this.elements;
   }

   int internalArrayStart() {
      return 0;
   }

   int internalArrayEnd() {
      return this.elements.length;
   }

   int copyIntoArray(@Nullable Object[] dst, int offset) {
      System.arraycopy(this.elements, 0, dst, offset, this.elements.length);
      return offset + this.elements.length;
   }

   ImmutableList createAsList() {
      return (ImmutableList)(this.table.length == 0 ? ImmutableList.of() : new RegularImmutableAsList(this, this.elements));
   }

   boolean isPartialView() {
      return false;
   }

   public int hashCode() {
      return this.hashCode;
   }

   boolean isHashCodeFast() {
      return true;
   }

   @J2ktIncompatible
   @GwtIncompatible
   Object writeReplace() {
      return super.writeReplace();
   }

   static {
      EMPTY = new RegularImmutableSet(EMPTY_ARRAY, 0, EMPTY_ARRAY, 0);
   }
}
