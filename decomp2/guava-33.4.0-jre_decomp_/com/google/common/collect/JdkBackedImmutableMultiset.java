package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Ints;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.util.Collection;
import java.util.Map;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@GwtCompatible
final class JdkBackedImmutableMultiset extends ImmutableMultiset {
   private final Map delegateMap;
   private final ImmutableList entries;
   private final long size;
   @LazyInit
   @CheckForNull
   private transient ImmutableSet elementSet;

   static ImmutableMultiset create(Collection entries) {
      Multiset.Entry<E>[] entriesArray = (Multiset.Entry[])entries.toArray(new Multiset.Entry[0]);
      Map<E, Integer> delegateMap = Maps.newHashMapWithExpectedSize(entriesArray.length);
      long size = 0L;

      for(int i = 0; i < entriesArray.length; ++i) {
         Multiset.Entry<E> entry = entriesArray[i];
         int count = entry.getCount();
         size += (long)count;
         E element = (E)Preconditions.checkNotNull(entry.getElement());
         delegateMap.put(element, count);
         if (!(entry instanceof Multisets.ImmutableEntry)) {
            entriesArray[i] = Multisets.immutableEntry(element, count);
         }
      }

      return new JdkBackedImmutableMultiset(delegateMap, ImmutableList.asImmutableList(entriesArray), size);
   }

   private JdkBackedImmutableMultiset(Map delegateMap, ImmutableList entries, long size) {
      this.delegateMap = delegateMap;
      this.entries = entries;
      this.size = size;
   }

   public int count(@CheckForNull Object element) {
      return (Integer)this.delegateMap.getOrDefault(element, 0);
   }

   public ImmutableSet elementSet() {
      ImmutableSet<E> result = this.elementSet;
      return result == null ? (this.elementSet = new ImmutableMultiset.ElementSet(this.entries, this)) : result;
   }

   Multiset.Entry getEntry(int index) {
      return (Multiset.Entry)this.entries.get(index);
   }

   boolean isPartialView() {
      return false;
   }

   public int size() {
      return Ints.saturatedCast(this.size);
   }

   @J2ktIncompatible
   @GwtIncompatible
   Object writeReplace() {
      return super.writeReplace();
   }
}
