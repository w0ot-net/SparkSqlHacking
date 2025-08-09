package scala.collection;

import scala.runtime.ModuleSerializationProxy;

public final class IndexedSeq$ extends SeqFactory.Delegate {
   public static final IndexedSeq$ MODULE$ = new IndexedSeq$();
   private static final long serialVersionUID = 3L;

   private Object writeReplace() {
      return new ModuleSerializationProxy(IndexedSeq$.class);
   }

   private IndexedSeq$() {
      super(scala.collection.immutable.IndexedSeq$.MODULE$);
   }
}
