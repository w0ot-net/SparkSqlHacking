package scala.collection.immutable;

import scala.collection.IterableOnce;
import scala.collection.SeqFactory;
import scala.runtime.ModuleSerializationProxy;

public final class Seq$ extends SeqFactory.Delegate {
   public static final Seq$ MODULE$ = new Seq$();
   private static final long serialVersionUID = 3L;

   public Seq from(final IterableOnce it) {
      return it instanceof Seq ? (Seq)it : (Seq)super.from(it);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Seq$.class);
   }

   private Seq$() {
      super(List$.MODULE$);
   }
}
