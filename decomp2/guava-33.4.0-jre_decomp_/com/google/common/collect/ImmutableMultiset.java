package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.DoNotCall;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.ToIntFunction;
import java.util.stream.Collector;
import javax.annotation.CheckForNull;
import org.checkerframework.checker.nullness.qual.Nullable;

@ElementTypesAreNonnullByDefault
@GwtCompatible(
   serializable = true,
   emulated = true
)
public abstract class ImmutableMultiset extends ImmutableMultisetGwtSerializationDependencies implements Multiset {
   @LazyInit
   @CheckForNull
   private transient ImmutableList asList;
   @LazyInit
   @CheckForNull
   private transient ImmutableSet entrySet;
   private static final long serialVersionUID = -889275714L;

   public static Collector toImmutableMultiset() {
      return CollectCollectors.toImmutableMultiset(Function.identity(), (e) -> 1);
   }

   public static Collector toImmutableMultiset(Function elementFunction, ToIntFunction countFunction) {
      return CollectCollectors.toImmutableMultiset(elementFunction, countFunction);
   }

   public static ImmutableMultiset of() {
      return RegularImmutableMultiset.EMPTY;
   }

   public static ImmutableMultiset of(Object e1) {
      return copyFromElements(e1);
   }

   public static ImmutableMultiset of(Object e1, Object e2) {
      return copyFromElements(e1, e2);
   }

   public static ImmutableMultiset of(Object e1, Object e2, Object e3) {
      return copyFromElements(e1, e2, e3);
   }

   public static ImmutableMultiset of(Object e1, Object e2, Object e3, Object e4) {
      return copyFromElements(e1, e2, e3, e4);
   }

   public static ImmutableMultiset of(Object e1, Object e2, Object e3, Object e4, Object e5) {
      return copyFromElements(e1, e2, e3, e4, e5);
   }

   public static ImmutableMultiset of(Object e1, Object e2, Object e3, Object e4, Object e5, Object e6, Object... others) {
      return (new Builder()).add(e1).add(e2).add(e3).add(e4).add(e5).add(e6).add(others).build();
   }

   public static ImmutableMultiset copyOf(Object[] elements) {
      return copyFromElements(elements);
   }

   public static ImmutableMultiset copyOf(Iterable elements) {
      if (elements instanceof ImmutableMultiset) {
         ImmutableMultiset<E> result = (ImmutableMultiset)elements;
         if (!result.isPartialView()) {
            return result;
         }
      }

      Multiset<? extends E> multiset = (Multiset<? extends E>)(elements instanceof Multiset ? Multisets.cast(elements) : LinkedHashMultiset.create(elements));
      return copyFromEntries(multiset.entrySet());
   }

   public static ImmutableMultiset copyOf(Iterator elements) {
      Multiset<E> multiset = LinkedHashMultiset.create();
      Iterators.addAll(multiset, elements);
      return copyFromEntries(multiset.entrySet());
   }

   private static ImmutableMultiset copyFromElements(Object... elements) {
      Multiset<E> multiset = LinkedHashMultiset.create();
      Collections.addAll(multiset, elements);
      return copyFromEntries(multiset.entrySet());
   }

   static ImmutableMultiset copyFromEntries(Collection entries) {
      return entries.isEmpty() ? of() : RegularImmutableMultiset.create(entries);
   }

   ImmutableMultiset() {
   }

   public UnmodifiableIterator iterator() {
      final Iterator<Multiset.Entry<E>> entryIterator = this.entrySet().iterator();
      return new UnmodifiableIterator() {
         int remaining;
         @CheckForNull
         Object element;

         public boolean hasNext() {
            return this.remaining > 0 || entryIterator.hasNext();
         }

         public Object next() {
            if (this.remaining <= 0) {
               Multiset.Entry<E> entry = (Multiset.Entry)entryIterator.next();
               this.element = entry.getElement();
               this.remaining = entry.getCount();
            }

            --this.remaining;
            return Objects.requireNonNull(this.element);
         }
      };
   }

   public ImmutableList asList() {
      ImmutableList<E> result = this.asList;
      return result == null ? (this.asList = super.asList()) : result;
   }

   public boolean contains(@CheckForNull Object object) {
      return this.count(object) > 0;
   }

   /** @deprecated */
   @Deprecated
   @CanIgnoreReturnValue
   @DoNotCall("Always throws UnsupportedOperationException")
   public final int add(Object element, int occurrences) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   @CanIgnoreReturnValue
   @DoNotCall("Always throws UnsupportedOperationException")
   public final int remove(@CheckForNull Object element, int occurrences) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   @CanIgnoreReturnValue
   @DoNotCall("Always throws UnsupportedOperationException")
   public final int setCount(Object element, int count) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   @CanIgnoreReturnValue
   @DoNotCall("Always throws UnsupportedOperationException")
   public final boolean setCount(Object element, int oldCount, int newCount) {
      throw new UnsupportedOperationException();
   }

   @GwtIncompatible
   int copyIntoArray(@Nullable Object[] dst, int offset) {
      for(Multiset.Entry entry : this.entrySet()) {
         Arrays.fill(dst, offset, offset + entry.getCount(), entry.getElement());
         offset += entry.getCount();
      }

      return offset;
   }

   public boolean equals(@CheckForNull Object object) {
      return Multisets.equalsImpl(this, object);
   }

   public int hashCode() {
      return Sets.hashCodeImpl(this.entrySet());
   }

   public String toString() {
      return this.entrySet().toString();
   }

   public abstract ImmutableSet elementSet();

   public ImmutableSet entrySet() {
      ImmutableSet<Multiset.Entry<E>> es = this.entrySet;
      return es == null ? (this.entrySet = this.createEntrySet()) : es;
   }

   private ImmutableSet createEntrySet() {
      return (ImmutableSet)(this.isEmpty() ? ImmutableSet.of() : new EntrySet());
   }

   abstract Multiset.Entry getEntry(int index);

   @GwtIncompatible
   @J2ktIncompatible
   Object writeReplace() {
      return new SerializedForm(this);
   }

   @GwtIncompatible
   @J2ktIncompatible
   private void readObject(ObjectInputStream stream) throws InvalidObjectException {
      throw new InvalidObjectException("Use SerializedForm");
   }

   public static Builder builder() {
      return new Builder();
   }

   private final class EntrySet extends IndexedImmutableSet {
      @J2ktIncompatible
      private static final long serialVersionUID = 0L;

      private EntrySet() {
      }

      boolean isPartialView() {
         return ImmutableMultiset.this.isPartialView();
      }

      Multiset.Entry get(int index) {
         return ImmutableMultiset.this.getEntry(index);
      }

      public int size() {
         return ImmutableMultiset.this.elementSet().size();
      }

      public boolean contains(@CheckForNull Object o) {
         if (o instanceof Multiset.Entry) {
            Multiset.Entry<?> entry = (Multiset.Entry)o;
            if (entry.getCount() <= 0) {
               return false;
            } else {
               int count = ImmutableMultiset.this.count(entry.getElement());
               return count == entry.getCount();
            }
         } else {
            return false;
         }
      }

      public int hashCode() {
         return ImmutableMultiset.this.hashCode();
      }

      @GwtIncompatible
      @J2ktIncompatible
      Object writeReplace() {
         return new EntrySetSerializedForm(ImmutableMultiset.this);
      }

      @GwtIncompatible
      @J2ktIncompatible
      private void readObject(ObjectInputStream stream) throws InvalidObjectException {
         throw new InvalidObjectException("Use EntrySetSerializedForm");
      }
   }

   @GwtIncompatible
   @J2ktIncompatible
   static class EntrySetSerializedForm implements Serializable {
      final ImmutableMultiset multiset;

      EntrySetSerializedForm(ImmutableMultiset multiset) {
         this.multiset = multiset;
      }

      Object readResolve() {
         return this.multiset.entrySet();
      }
   }

   public static class Builder extends ImmutableCollection.Builder {
      final Multiset contents;

      public Builder() {
         this(LinkedHashMultiset.create());
      }

      Builder(Multiset contents) {
         this.contents = contents;
      }

      @CanIgnoreReturnValue
      public Builder add(Object element) {
         this.contents.add(Preconditions.checkNotNull(element));
         return this;
      }

      @CanIgnoreReturnValue
      public Builder add(Object... elements) {
         super.add(elements);
         return this;
      }

      @CanIgnoreReturnValue
      public Builder addCopies(Object element, int occurrences) {
         this.contents.add(Preconditions.checkNotNull(element), occurrences);
         return this;
      }

      @CanIgnoreReturnValue
      public Builder setCount(Object element, int count) {
         this.contents.setCount(Preconditions.checkNotNull(element), count);
         return this;
      }

      @CanIgnoreReturnValue
      public Builder addAll(Iterable elements) {
         if (elements instanceof Multiset) {
            Multiset<? extends E> multiset = Multisets.cast(elements);
            multiset.forEachEntry((e, n) -> this.contents.add(Preconditions.checkNotNull(e), n));
         } else {
            super.addAll(elements);
         }

         return this;
      }

      @CanIgnoreReturnValue
      public Builder addAll(Iterator elements) {
         super.addAll(elements);
         return this;
      }

      public ImmutableMultiset build() {
         return ImmutableMultiset.copyOf((Iterable)this.contents);
      }

      @VisibleForTesting
      ImmutableMultiset buildJdkBacked() {
         return this.contents.isEmpty() ? ImmutableMultiset.of() : JdkBackedImmutableMultiset.create(this.contents.entrySet());
      }
   }

   static final class ElementSet extends ImmutableSet.Indexed {
      private final List entries;
      private final Multiset delegate;

      ElementSet(List entries, Multiset delegate) {
         this.entries = entries;
         this.delegate = delegate;
      }

      Object get(int index) {
         return ((Multiset.Entry)this.entries.get(index)).getElement();
      }

      public boolean contains(@CheckForNull Object object) {
         return this.delegate.contains(object);
      }

      boolean isPartialView() {
         return true;
      }

      public int size() {
         return this.entries.size();
      }

      @J2ktIncompatible
      @GwtIncompatible
      Object writeReplace() {
         return super.writeReplace();
      }
   }

   @J2ktIncompatible
   static final class SerializedForm implements Serializable {
      final Object[] elements;
      final int[] counts;
      private static final long serialVersionUID = 0L;

      SerializedForm(Multiset multiset) {
         int distinct = multiset.entrySet().size();
         this.elements = new Object[distinct];
         this.counts = new int[distinct];
         int i = 0;

         for(Multiset.Entry entry : multiset.entrySet()) {
            this.elements[i] = entry.getElement();
            this.counts[i] = entry.getCount();
            ++i;
         }

      }

      Object readResolve() {
         LinkedHashMultiset<Object> multiset = LinkedHashMultiset.create(this.elements.length);

         for(int i = 0; i < this.elements.length; ++i) {
            multiset.add(this.elements[i], this.counts[i]);
         }

         return ImmutableMultiset.copyOf((Iterable)multiset);
      }
   }
}
