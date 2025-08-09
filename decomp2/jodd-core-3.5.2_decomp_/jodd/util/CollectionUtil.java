package jodd.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

public class CollectionUtil {
   public static Enumeration asEnumeration(final Iterator iter) {
      return new Enumeration() {
         public boolean hasMoreElements() {
            return iter.hasNext();
         }

         public Object nextElement() {
            return iter.next();
         }
      };
   }

   public static Iterator asIterator(final Enumeration e) {
      return new Iterator() {
         public boolean hasNext() {
            return e.hasMoreElements();
         }

         public Object next() {
            return e.nextElement();
         }

         public void remove() {
            throw new UnsupportedOperationException();
         }
      };
   }

   public static Collection asCollection(Iterator iterator) {
      List<T> list = new ArrayList();

      while(iterator.hasNext()) {
         list.add(iterator.next());
      }

      return list;
   }
}
