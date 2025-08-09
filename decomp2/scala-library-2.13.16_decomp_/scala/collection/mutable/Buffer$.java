package scala.collection.mutable;

import scala.collection.SeqFactory;
import scala.runtime.ModuleSerializationProxy;

public final class Buffer$ extends SeqFactory.Delegate {
   public static final Buffer$ MODULE$ = new Buffer$();
   private static final long serialVersionUID = 3L;

   private Object writeReplace() {
      return new ModuleSerializationProxy(Buffer$.class);
   }

   private Buffer$() {
      super(ArrayBuffer$.MODULE$);
   }
}
