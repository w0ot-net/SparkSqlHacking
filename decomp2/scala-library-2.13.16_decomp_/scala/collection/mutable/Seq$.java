package scala.collection.mutable;

import scala.collection.SeqFactory;
import scala.runtime.ModuleSerializationProxy;

public final class Seq$ extends SeqFactory.Delegate {
   public static final Seq$ MODULE$ = new Seq$();
   private static final long serialVersionUID = 3L;

   private Object writeReplace() {
      return new ModuleSerializationProxy(Seq$.class);
   }

   private Seq$() {
      super(ArrayBuffer$.MODULE$);
   }
}
