package org.apache.commons.collections4.list;

import java.util.List;
import java.util.Objects;
import org.apache.commons.collections4.Factory;
import org.apache.commons.collections4.Transformer;

public class LazyList extends AbstractSerializableListDecorator {
   private static final long serialVersionUID = -3677737457567429713L;
   private final Factory factory;
   private final Transformer transformer;

   public static LazyList lazyList(List list, Factory factory) {
      return new LazyList(list, factory);
   }

   public static LazyList lazyList(List list, Transformer transformer) {
      return new LazyList(list, transformer);
   }

   protected LazyList(List list, Factory factory) {
      super(list);
      this.factory = (Factory)Objects.requireNonNull(factory);
      this.transformer = null;
   }

   protected LazyList(List list, Transformer transformer) {
      super(list);
      this.factory = null;
      this.transformer = (Transformer)Objects.requireNonNull(transformer);
   }

   public Object get(int index) {
      int size = this.decorated().size();
      if (index < size) {
         E object = (E)this.decorated().get(index);
         if (object == null) {
            object = (E)this.element(index);
            this.decorated().set(index, object);
            return object;
         } else {
            return object;
         }
      } else {
         for(int i = size; i < index; ++i) {
            this.decorated().add((Object)null);
         }

         E object = (E)this.element(index);
         this.decorated().add(object);
         return object;
      }
   }

   public List subList(int fromIndex, int toIndex) {
      List<E> sub = this.decorated().subList(fromIndex, toIndex);
      if (this.factory != null) {
         return new LazyList(sub, this.factory);
      } else if (this.transformer != null) {
         return new LazyList(sub, this.transformer);
      } else {
         throw new IllegalStateException("Factory and Transformer are both null!");
      }
   }

   private Object element(int index) {
      if (this.factory != null) {
         return this.factory.create();
      } else if (this.transformer != null) {
         return this.transformer.transform(index);
      } else {
         throw new IllegalStateException("Factory and Transformer are both null!");
      }
   }
}
