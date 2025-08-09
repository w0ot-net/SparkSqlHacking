package org.sparkproject.guava.collect;

import com.google.errorprone.annotations.concurrent.LazyInit;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Spliterator;
import java.util.function.Consumer;
import javax.annotation.CheckForNull;
import org.sparkproject.guava.annotations.GwtCompatible;
import org.sparkproject.guava.annotations.J2ktIncompatible;

@ElementTypesAreNonnullByDefault
@GwtCompatible(
   serializable = true,
   emulated = true
)
final class ImmutableEnumSet extends ImmutableSet {
   private final transient EnumSet delegate;
   @LazyInit
   private transient int hashCode;

   static ImmutableSet asImmutable(EnumSet set) {
      switch (set.size()) {
         case 0:
            return ImmutableSet.of();
         case 1:
            return ImmutableSet.of((Enum)Iterables.getOnlyElement(set));
         default:
            return new ImmutableEnumSet(set);
      }
   }

   private ImmutableEnumSet(EnumSet delegate) {
      this.delegate = delegate;
   }

   boolean isPartialView() {
      return false;
   }

   public UnmodifiableIterator iterator() {
      return Iterators.unmodifiableIterator(this.delegate.iterator());
   }

   public Spliterator spliterator() {
      return this.delegate.spliterator();
   }

   public void forEach(Consumer action) {
      this.delegate.forEach(action);
   }

   public int size() {
      return this.delegate.size();
   }

   public boolean contains(@CheckForNull Object object) {
      return this.delegate.contains(object);
   }

   public boolean containsAll(Collection collection) {
      if (collection instanceof ImmutableEnumSet) {
         collection = ((ImmutableEnumSet)collection).delegate;
      }

      return this.delegate.containsAll(collection);
   }

   public boolean isEmpty() {
      return this.delegate.isEmpty();
   }

   public boolean equals(@CheckForNull Object object) {
      if (object == this) {
         return true;
      } else {
         if (object instanceof ImmutableEnumSet) {
            object = ((ImmutableEnumSet)object).delegate;
         }

         return this.delegate.equals(object);
      }
   }

   boolean isHashCodeFast() {
      return true;
   }

   public int hashCode() {
      int result = this.hashCode;
      return result == 0 ? (this.hashCode = this.delegate.hashCode()) : result;
   }

   public String toString() {
      return this.delegate.toString();
   }

   @J2ktIncompatible
   Object writeReplace() {
      return new EnumSerializedForm(this.delegate);
   }

   @J2ktIncompatible
   private void readObject(ObjectInputStream stream) throws InvalidObjectException {
      throw new InvalidObjectException("Use SerializedForm");
   }

   @J2ktIncompatible
   private static class EnumSerializedForm implements Serializable {
      final EnumSet delegate;
      private static final long serialVersionUID = 0L;

      EnumSerializedForm(EnumSet delegate) {
         this.delegate = delegate;
      }

      Object readResolve() {
         return new ImmutableEnumSet(this.delegate.clone());
      }
   }
}
