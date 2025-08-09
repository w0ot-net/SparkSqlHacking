package scala.collection.immutable;

import scala.collection.IterableOnce;
import scala.collection.SeqFactory;
import scala.runtime.ModuleSerializationProxy;

public final class IndexedSeq$ extends SeqFactory.Delegate {
   public static final IndexedSeq$ MODULE$ = new IndexedSeq$();
   private static final long serialVersionUID = 3L;

   public IndexedSeq from(final IterableOnce it) {
      return it instanceof IndexedSeq ? (IndexedSeq)it : (IndexedSeq)super.from(it);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(IndexedSeq$.class);
   }

   private IndexedSeq$() {
      super(Vector$.MODULE$);
   }
}
