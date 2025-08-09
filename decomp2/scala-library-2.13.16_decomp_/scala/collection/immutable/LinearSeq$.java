package scala.collection.immutable;

import scala.collection.IterableOnce;
import scala.collection.SeqFactory;
import scala.runtime.ModuleSerializationProxy;

public final class LinearSeq$ extends SeqFactory.Delegate {
   public static final LinearSeq$ MODULE$ = new LinearSeq$();
   private static final long serialVersionUID = 3L;

   public LinearSeq from(final IterableOnce it) {
      return it instanceof LinearSeq ? (LinearSeq)it : (LinearSeq)super.from(it);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(LinearSeq$.class);
   }

   private LinearSeq$() {
      super(List$.MODULE$);
   }
}
