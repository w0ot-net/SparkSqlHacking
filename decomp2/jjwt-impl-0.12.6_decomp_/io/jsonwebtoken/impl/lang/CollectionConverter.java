package io.jsonwebtoken.impl.lang;

import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.lang.Collections;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

class CollectionConverter implements Converter {
   private final Converter elementConverter;
   private final Function fn;

   public static CollectionConverter forList(Converter elementConverter) {
      return new CollectionConverter(elementConverter, new CreateListFunction());
   }

   public static CollectionConverter forSet(Converter elementConverter) {
      return new CollectionConverter(elementConverter, new CreateSetFunction());
   }

   public CollectionConverter(Converter elementConverter, Function fn) {
      this.elementConverter = (Converter)Assert.notNull(elementConverter, "Element converter cannot be null.");
      this.fn = (Function)Assert.notNull(fn, "Collection function cannot be null.");
   }

   public Object applyTo(Collection ts) {
      if (Collections.isEmpty(ts)) {
         return ts;
      } else {
         Collection c = (Collection)this.fn.apply(ts.size());

         for(Object element : ts) {
            Object encoded = this.elementConverter.applyTo(element);
            c.add(encoded);
         }

         return c;
      }
   }

   private Collection toElementList(Collection c) {
      Assert.notEmpty(c, "Collection cannot be null or empty.");
      C result = (C)((Collection)this.fn.apply(c.size()));

      for(Object o : c) {
         T element = (T)this.elementConverter.applyFrom(o);
         result.add(element);
      }

      return result;
   }

   public Collection applyFrom(Object value) {
      if (value == null) {
         return null;
      } else {
         Collection<?> c;
         if (value.getClass().isArray() && !value.getClass().getComponentType().isPrimitive()) {
            c = Collections.arrayToList(value);
         } else if (value instanceof Collection) {
            c = (Collection)value;
         } else {
            c = java.util.Collections.singletonList(value);
         }

         C result;
         if (Collections.isEmpty(c)) {
            result = (C)((Collection)this.fn.apply(0));
         } else {
            result = (C)this.toElementList(c);
         }

         return result;
      }
   }

   private static class CreateListFunction implements Function {
      private CreateListFunction() {
      }

      public List apply(Integer size) {
         return size > 0 ? new ArrayList(size) : new ArrayList();
      }
   }

   private static class CreateSetFunction implements Function {
      private CreateSetFunction() {
      }

      public Set apply(Integer size) {
         return size > 0 ? new LinkedHashSet(size) : new LinkedHashSet();
      }
   }
}
