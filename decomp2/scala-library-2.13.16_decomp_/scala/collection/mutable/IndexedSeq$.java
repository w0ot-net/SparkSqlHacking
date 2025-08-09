package scala.collection.mutable;

import scala.collection.SeqFactory;
import scala.runtime.ModuleSerializationProxy;

public final class IndexedSeq$ extends SeqFactory.Delegate {
   public static final IndexedSeq$ MODULE$ = new IndexedSeq$();
   private static final long serialVersionUID = 3L;

   private Object writeReplace() {
      return new ModuleSerializationProxy(IndexedSeq$.class);
   }

   private IndexedSeq$() {
      super(ArrayBuffer$.MODULE$);
   }
}
