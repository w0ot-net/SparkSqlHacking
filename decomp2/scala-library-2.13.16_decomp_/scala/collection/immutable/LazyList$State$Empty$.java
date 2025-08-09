package scala.collection.immutable;

import java.util.NoSuchElementException;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Nothing$;

public class LazyList$State$Empty$ implements LazyList.State {
   public static final LazyList$State$Empty$ MODULE$ = new LazyList$State$Empty$();
   private static final long serialVersionUID = 3L;

   public Nothing$ head() {
      throw new NoSuchElementException("head of empty lazy list");
   }

   public LazyList tail() {
      throw new UnsupportedOperationException("tail of empty lazy list");
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(LazyList$State$Empty$.class);
   }
}
