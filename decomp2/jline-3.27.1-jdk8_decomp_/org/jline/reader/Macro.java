package org.jline.reader;

public class Macro implements Binding {
   private final String sequence;

   public Macro(String sequence) {
      this.sequence = sequence;
   }

   public String getSequence() {
      return this.sequence;
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         Macro macro = (Macro)o;
         return this.sequence.equals(macro.sequence);
      } else {
         return false;
      }
   }

   public int hashCode() {
      return this.sequence.hashCode();
   }

   public String toString() {
      return "Macro[" + this.sequence + ']';
   }
}
