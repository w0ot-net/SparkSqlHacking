package org.jline.reader;

public class Reference implements Binding {
   private final String name;

   public Reference(String name) {
      this.name = name;
   }

   public String name() {
      return this.name;
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         Reference func = (Reference)o;
         return this.name.equals(func.name);
      } else {
         return false;
      }
   }

   public int hashCode() {
      return this.name.hashCode();
   }

   public String toString() {
      return "Reference[" + this.name + ']';
   }
}
