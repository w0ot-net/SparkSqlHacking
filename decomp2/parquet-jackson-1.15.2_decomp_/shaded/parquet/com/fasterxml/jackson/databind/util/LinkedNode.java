package shaded.parquet.com.fasterxml.jackson.databind.util;

public final class LinkedNode {
   private final Object value;
   private LinkedNode next;

   public LinkedNode(Object value, LinkedNode next) {
      this.value = value;
      this.next = next;
   }

   public void linkNext(LinkedNode n) {
      if (this.next != null) {
         throw new IllegalStateException();
      } else {
         this.next = n;
      }
   }

   public LinkedNode next() {
      return this.next;
   }

   public Object value() {
      return this.value;
   }

   public static boolean contains(LinkedNode node, Object value) {
      while(node != null) {
         if (node.value() == value) {
            return true;
         }

         node = node.next();
      }

      return false;
   }
}
