package scala.collection.immutable;

import scala.collection.IterableFactory;
import scala.collection.IterableOnce;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\t2QAA\u0002\u0002\u0002)AQa\b\u0001\u0005\u0002\u0001\u00121\"\u00112tiJ\f7\r^*fi*\u0011A!B\u0001\nS6lW\u000f^1cY\u0016T!AB\u0004\u0002\u0015\r|G\u000e\\3di&|gNC\u0001\t\u0003\u0015\u00198-\u00197b\u0007\u0001)\"aC\t\u0014\u0007\u0001a1\u0004E\u0002\u000e\u001d=i\u0011!B\u0005\u0003\u0005\u0015\u0001\"\u0001E\t\r\u0001\u0011)!\u0003\u0001b\u0001'\t\t\u0011)\u0005\u0002\u00151A\u0011QCF\u0007\u0002\u000f%\u0011qc\u0002\u0002\b\u001d>$\b.\u001b8h!\t)\u0012$\u0003\u0002\u001b\u000f\t\u0019\u0011I\\=\u0011\u0007qir\"D\u0001\u0004\u0013\tq2AA\u0002TKR\fa\u0001P5oSRtD#A\u0011\u0011\u0007q\u0001q\u0002"
)
public abstract class AbstractSet extends scala.collection.AbstractSet implements Set {
   public IterableFactory iterableFactory() {
      return Set.iterableFactory$(this);
   }

   public final SetOps $plus(final Object elem) {
      return SetOps.$plus$(this, elem);
   }

   public final SetOps $minus(final Object elem) {
      return SetOps.$minus$(this, elem);
   }

   public SetOps diff(final scala.collection.Set that) {
      return SetOps.diff$(this, that);
   }

   public SetOps removedAll(final IterableOnce that) {
      return SetOps.removedAll$(this, that);
   }

   public final SetOps $minus$minus(final IterableOnce that) {
      return SetOps.$minus$minus$(this, that);
   }
}
