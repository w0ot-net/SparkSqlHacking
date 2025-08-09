package scala.collection;

import scala.runtime.ModuleSerializationProxy;

public final class SeqMap$ extends MapFactory.Delegate {
   public static final SeqMap$ MODULE$ = new SeqMap$();

   private Object writeReplace() {
      return new ModuleSerializationProxy(SeqMap$.class);
   }

   private SeqMap$() {
      super(scala.collection.immutable.SeqMap$.MODULE$);
   }
}
