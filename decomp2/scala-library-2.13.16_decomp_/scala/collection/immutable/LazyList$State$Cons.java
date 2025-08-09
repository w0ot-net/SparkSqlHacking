package scala.collection.immutable;

public final class LazyList$State$Cons implements LazyList.State {
   private static final long serialVersionUID = 3L;
   private final Object head;
   private final LazyList tail;

   public Object head() {
      return this.head;
   }

   public LazyList tail() {
      return this.tail;
   }

   public LazyList$State$Cons(final Object head, final LazyList tail) {
      this.head = head;
      this.tail = tail;
   }
}
