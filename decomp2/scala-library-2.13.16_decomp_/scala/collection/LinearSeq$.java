package scala.collection;

import scala.runtime.ModuleSerializationProxy;

public final class LinearSeq$ extends SeqFactory.Delegate {
   public static final LinearSeq$ MODULE$ = new LinearSeq$();
   private static final long serialVersionUID = 3L;

   private Object writeReplace() {
      return new ModuleSerializationProxy(LinearSeq$.class);
   }

   private LinearSeq$() {
      super(scala.collection.immutable.LinearSeq$.MODULE$);
   }
}
