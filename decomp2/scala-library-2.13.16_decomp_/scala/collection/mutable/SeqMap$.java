package scala.collection.mutable;

import scala.collection.MapFactory;
import scala.runtime.ModuleSerializationProxy;

public final class SeqMap$ extends MapFactory.Delegate {
   public static final SeqMap$ MODULE$ = new SeqMap$();

   private Object writeReplace() {
      return new ModuleSerializationProxy(SeqMap$.class);
   }

   private SeqMap$() {
      super(LinkedHashMap$.MODULE$);
   }
}
