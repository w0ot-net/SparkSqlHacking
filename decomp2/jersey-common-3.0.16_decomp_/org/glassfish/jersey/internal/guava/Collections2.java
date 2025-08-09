package org.glassfish.jersey.internal.guava;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.Function;

final class Collections2 {
   static final Joiner STANDARD_JOINER = Joiner.on();

   private Collections2() {
   }

   static boolean safeContains(Collection collection, Object object) {
      Preconditions.checkNotNull(collection);

      try {
         return collection.contains(object);
      } catch (ClassCastException var3) {
         return false;
      } catch (NullPointerException var4) {
         return false;
      }
   }

   static boolean safeRemove(Collection collection, Object object) {
      Preconditions.checkNotNull(collection);

      try {
         return collection.remove(object);
      } catch (ClassCastException var3) {
         return false;
      } catch (NullPointerException var4) {
         return false;
      }
   }

   public static Collection transform(Collection fromCollection, Function function) {
      return new TransformedCollection(fromCollection, function);
   }

   static StringBuilder newStringBuilderForCollection(int size) {
      CollectPreconditions.checkNonnegative(size, "size");
      return new StringBuilder((int)Math.min((long)size * 8L, 1073741824L));
   }

   static Collection cast(Iterable iterable) {
      return (Collection)iterable;
   }

   static class TransformedCollection extends AbstractCollection {
      final Collection fromCollection;
      final Function function;

      TransformedCollection(Collection fromCollection, Function function) {
         this.fromCollection = (Collection)Preconditions.checkNotNull(fromCollection);
         this.function = (Function)Preconditions.checkNotNull(function);
      }

      public void clear() {
         this.fromCollection.clear();
      }

      public boolean isEmpty() {
         return this.fromCollection.isEmpty();
      }

      public Iterator iterator() {
         return Iterators.transform(this.fromCollection.iterator(), this.function);
      }

      public int size() {
         return this.fromCollection.size();
      }
   }
}
