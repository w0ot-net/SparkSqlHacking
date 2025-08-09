package org.glassfish.jersey.internal.guava;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public final class Multimaps {
   private Multimaps() {
   }

   public static ListMultimap newListMultimap(Map map, Supplier factory) {
      return new CustomListMultimap(map, factory);
   }

   static boolean equalsImpl(Multimap multimap, Object object) {
      if (object == multimap) {
         return true;
      } else if (object instanceof Multimap) {
         Multimap<?, ?> that = (Multimap)object;
         return multimap.asMap().equals(that.asMap());
      } else {
         return false;
      }
   }

   private static class CustomListMultimap extends AbstractListMultimap {
      private static final long serialVersionUID = 0L;
      transient Supplier factory;

      CustomListMultimap(Map map, Supplier factory) {
         super(map);
         this.factory = (Supplier)Preconditions.checkNotNull(factory);
      }

      protected List createCollection() {
         return (List)this.factory.get();
      }

      private void writeObject(ObjectOutputStream stream) throws IOException {
         stream.defaultWriteObject();
         stream.writeObject(this.factory);
         stream.writeObject(this.backingMap());
      }

      private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
         stream.defaultReadObject();
         this.factory = (Supplier)stream.readObject();
         Map<K, Collection<V>> map = (Map)stream.readObject();
         this.setMap(map);
      }
   }

   abstract static class Entries extends AbstractCollection {
      abstract Multimap multimap();

      public int size() {
         return this.multimap().size();
      }

      public boolean contains(Object o) {
         if (o instanceof Map.Entry) {
            Map.Entry<?, ?> entry = (Map.Entry)o;
            return this.multimap().containsEntry(entry.getKey(), entry.getValue());
         } else {
            return false;
         }
      }

      public boolean remove(Object o) {
         if (o instanceof Map.Entry) {
            Map.Entry<?, ?> entry = (Map.Entry)o;
            return this.multimap().remove(entry.getKey(), entry.getValue());
         } else {
            return false;
         }
      }

      public void clear() {
         this.multimap().clear();
      }
   }
}
