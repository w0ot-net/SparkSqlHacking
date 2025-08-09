package scala;

import java.io.Serializable;
import scala.collection.AbstractIterable;
import scala.collection.AbstractIterator;
import scala.collection.Iterable;
import scala.collection.Iterable$;
import scala.collection.Iterator;
import scala.collection.Iterator$;
import scala.collection.View;
import scala.runtime.ModuleSerializationProxy;

public final class Option$ implements Serializable {
   public static final Option$ MODULE$ = new Option$();

   public Iterable option2Iterable(final Option xo) {
      if (xo.isEmpty()) {
         return (Iterable)package$.MODULE$.Iterable().empty();
      } else {
         Iterable$ var10000 = package$.MODULE$.Iterable();
         Object single_a = xo.get();
         if (var10000 == null) {
            throw null;
         } else {
            return new AbstractIterable(single_a) {
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
      }
   }

   public Option apply(final Object x) {
      return (Option)(x == null ? None$.MODULE$ : new Some(x));
   }

   public Option empty() {
      return None$.MODULE$;
   }

   public Option when(final boolean cond, final Function0 a) {
      return (Option)(cond ? new Some(a.apply()) : None$.MODULE$);
   }

   public Option unless(final boolean cond, final Function0 a) {
      return (Option)(!cond ? new Some(a.apply()) : None$.MODULE$);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Option$.class);
   }

   private Option$() {
   }
}
