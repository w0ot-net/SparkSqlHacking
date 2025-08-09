package scala.collection.mutable;

import scala.collection.SeqFactory;
import scala.runtime.ModuleSerializationProxy;

public final class IndexedBuffer$ extends SeqFactory.Delegate {
   public static final IndexedBuffer$ MODULE$ = new IndexedBuffer$();
   private static final long serialVersionUID = 3L;

   private Object writeReplace() {
      return new ModuleSerializationProxy(IndexedBuffer$.class);
   }

   private IndexedBuffer$() {
      super(ArrayBuffer$.MODULE$);
   }
}
