package org.apache.commons.collections.list;

import java.util.List;
import org.apache.commons.collections.Factory;

public class LazyList extends AbstractSerializableListDecorator {
   private static final long serialVersionUID = -1708388017160694542L;
   protected final Factory factory;

   public static List decorate(List list, Factory factory) {
      return new LazyList(list, factory);
   }

   protected LazyList(List list, Factory factory) {
      super(list);
      if (factory == null) {
         throw new IllegalArgumentException("Factory must not be null");
      } else {
         this.factory = factory;
      }
   }

   public Object get(int index) {
      int size = this.getList().size();
      if (index < size) {
         Object object = this.getList().get(index);
         if (object == null) {
            object = this.factory.create();
            this.getList().set(index, object);
            return object;
         } else {
            return object;
         }
      } else {
         for(int i = size; i < index; ++i) {
            this.getList().add((Object)null);
         }

         Object object = this.factory.create();
         this.getList().add(object);
         return object;
      }
   }

   public List subList(int fromIndex, int toIndex) {
      List sub = this.getList().subList(fromIndex, toIndex);
      return new LazyList(sub, this.factory);
   }
}
