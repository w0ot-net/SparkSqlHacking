package scala.collection;

import scala.Some;
import scala.runtime.ModuleSerializationProxy;

public final class Iterable$ extends IterableFactory.Delegate {
   public static final Iterable$ MODULE$ = new Iterable$();
   private static final long serialVersionUID = 3L;

   public Iterable single(final Object a) {
      return new AbstractIterable(a) {
         private final Object a$1;

         public Iterator iterator() {
            Iterator$ var10000 = Iterator$.MODULE$;
            Object single_a = this.a$1;
            return new AbstractIterator(single_a) {
               private boolean consumed;
               private final Object a$1;

               public boolean hasNext() {
                  return !this.consumed;
               }

               public Object next() {
                  if (this.consumed) {
                     Iterator$ var10000 = Iterator$.MODULE$;
                     return Iterator$.scala$collection$Iterator$$_empty.next();
                  } else {
                     this.consumed = true;
                     return this.a$1;
                  }
               }

               public Iterator sliceIterator(final int from, final int until) {
                  if (!this.consumed && from <= 0 && until != 0) {
                     return this;
                  } else {
                     Iterator$ var10000 = Iterator$.MODULE$;
                     return Iterator$.scala$collection$Iterator$$_empty;
                  }
               }

               public {
                  this.a$1 = a$1;
                  this.consumed = false;
               }
            };
         }

         public int knownSize() {
            return 1;
         }

         public Object head() {
            return this.a$1;
         }

         public Some headOption() {
            return new Some(this.a$1);
         }

         public Object last() {
            return this.a$1;
         }

         public Some lastOption() {
            return new Some(this.a$1);
         }

         public View.Single view() {
            return new View.Single(this.a$1);
         }

         public Iterable take(final int n) {
            return (Iterable)(n > 0 ? this : (Iterable)Iterable$.MODULE$.empty());
         }

         public Iterable takeRight(final int n) {
            return (Iterable)(n > 0 ? this : (Iterable)Iterable$.MODULE$.empty());
         }

         public Iterable drop(final int n) {
            return (Iterable)(n > 0 ? (Iterable)Iterable$.MODULE$.empty() : this);
         }

         public Iterable dropRight(final int n) {
            return (Iterable)(n > 0 ? (Iterable)Iterable$.MODULE$.empty() : this);
         }

         public Iterable tail() {
            return (Iterable)Iterable$.MODULE$.empty();
         }

         public Iterable init() {
            return (Iterable)Iterable$.MODULE$.empty();
         }

         public {
            this.a$1 = a$1;
         }
      };
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Iterable$.class);
   }

   private Iterable$() {
      super(scala.collection.immutable.Iterable$.MODULE$);
   }
}
