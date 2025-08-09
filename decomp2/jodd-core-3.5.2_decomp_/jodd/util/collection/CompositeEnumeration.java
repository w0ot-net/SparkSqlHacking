package jodd.util.collection;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.NoSuchElementException;

public class CompositeEnumeration implements Enumeration {
   protected final List allEnumerations = new ArrayList();
   protected int currentEnumeration = -1;

   public CompositeEnumeration() {
   }

   public CompositeEnumeration(Enumeration... enumerations) {
      for(Enumeration enumeration : enumerations) {
         this.add(enumeration);
      }

   }

   public void add(Enumeration enumeration) {
      if (this.allEnumerations.contains(enumeration)) {
         throw new IllegalArgumentException("Duplicate enumeration");
      } else {
         this.allEnumerations.add(enumeration);
      }
   }

   public boolean hasMoreElements() {
      if (this.currentEnumeration == -1) {
         this.currentEnumeration = 0;
      }

      for(int i = this.currentEnumeration; i < this.allEnumerations.size(); ++i) {
         Enumeration enumeration = (Enumeration)this.allEnumerations.get(i);
         if (enumeration.hasMoreElements()) {
            this.currentEnumeration = i;
            return true;
         }
      }

      return false;
   }

   public Object nextElement() {
      if (!this.hasMoreElements()) {
         throw new NoSuchElementException();
      } else {
         return ((Enumeration)this.allEnumerations.get(this.currentEnumeration)).nextElement();
      }
   }
}
