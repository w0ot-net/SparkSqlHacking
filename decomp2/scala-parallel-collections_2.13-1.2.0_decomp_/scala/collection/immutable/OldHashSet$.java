package scala.collection.immutable;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Function3;
import scala.Function4;
import scala.Function5;
import scala.collection.Factory;
import scala.collection.IterableFactory;
import scala.collection.IterableOnce;
import scala.collection.mutable.Builder;
import scala.collection.mutable.ImmutableBuilder;
import scala.math.Integral;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.RichInt.;

public final class OldHashSet$ implements IterableFactory {
   public static final OldHashSet$ MODULE$ = new OldHashSet$();
   private static final long serialVersionUID = 3L;

   static {
      IterableFactory.$init$(MODULE$);
   }

   public Object apply(final Seq elems) {
      return IterableFactory.apply$(this, elems);
   }

   public Object iterate(final Object start, final int len, final Function1 f) {
      return IterableFactory.iterate$(this, start, len, f);
   }

   public Object unfold(final Object init, final Function1 f) {
      return IterableFactory.unfold$(this, init, f);
   }

   public Object range(final Object start, final Object end, final Integral evidence$3) {
      return IterableFactory.range$(this, start, end, evidence$3);
   }

   public Object range(final Object start, final Object end, final Object step, final Integral evidence$4) {
      return IterableFactory.range$(this, start, end, step, evidence$4);
   }

   public Object fill(final int n, final Function0 elem) {
      return IterableFactory.fill$(this, n, elem);
   }

   public Object fill(final int n1, final int n2, final Function0 elem) {
      return IterableFactory.fill$(this, n1, n2, elem);
   }

   public Object fill(final int n1, final int n2, final int n3, final Function0 elem) {
      return IterableFactory.fill$(this, n1, n2, n3, elem);
   }

   public Object fill(final int n1, final int n2, final int n3, final int n4, final Function0 elem) {
      return IterableFactory.fill$(this, n1, n2, n3, n4, elem);
   }

   public Object fill(final int n1, final int n2, final int n3, final int n4, final int n5, final Function0 elem) {
      return IterableFactory.fill$(this, n1, n2, n3, n4, n5, elem);
   }

   public Object tabulate(final int n, final Function1 f) {
      return IterableFactory.tabulate$(this, n, f);
   }

   public Object tabulate(final int n1, final int n2, final Function2 f) {
      return IterableFactory.tabulate$(this, n1, n2, f);
   }

   public Object tabulate(final int n1, final int n2, final int n3, final Function3 f) {
      return IterableFactory.tabulate$(this, n1, n2, n3, f);
   }

   public Object tabulate(final int n1, final int n2, final int n3, final int n4, final Function4 f) {
      return IterableFactory.tabulate$(this, n1, n2, n3, n4, f);
   }

   public Object tabulate(final int n1, final int n2, final int n3, final int n4, final int n5, final Function5 f) {
      return IterableFactory.tabulate$(this, n1, n2, n3, n4, n5, f);
   }

   public Object concat(final Seq xss) {
      return IterableFactory.concat$(this, xss);
   }

   public Factory iterableFactory() {
      return IterableFactory.iterableFactory$(this);
   }

   public OldHashSet from(final IterableOnce it) {
      if (it instanceof OldHashSet) {
         OldHashSet var4 = (OldHashSet)it;
         return var4;
      } else {
         return (OldHashSet)((Builder)this.newBuilder().$plus$plus$eq(it)).result();
      }
   }

   public OldHashSet empty() {
      return OldHashSet.EmptyOldHashSet$.MODULE$;
   }

   public Builder newBuilder() {
      return new ImmutableBuilder() {
         public <undefinedtype> addOne(final Object elem) {
            this.elems_$eq(((SetOps)this.elems()).$plus(elem));
            return this;
         }
      };
   }

   public OldHashSet.HashTrieSet scala$collection$immutable$OldHashSet$$makeHashTrieSet(final int hash0, final OldHashSet elem0, final int hash1, final OldHashSet elem1, final int level) {
      int index0 = hash0 >>> level & 31;
      int index1 = hash1 >>> level & 31;
      if (index0 != index1) {
         int bitmap = 1 << index0 | 1 << index1;
         OldHashSet[] elems = new OldHashSet[2];
         if (index0 < index1) {
            elems[0] = elem0;
            elems[1] = elem1;
         } else {
            elems[0] = elem1;
            elems[1] = elem0;
         }

         return new OldHashSet.HashTrieSet(bitmap, elems, elem0.size() + elem1.size());
      } else {
         OldHashSet[] elems = new OldHashSet[1];
         int bitmap = 1 << index0;
         OldHashSet.HashTrieSet child = this.scala$collection$immutable$OldHashSet$$makeHashTrieSet(hash0, elem0, hash1, elem1, level + 5);
         elems[0] = child;
         return new OldHashSet.HashTrieSet(bitmap, elems, child.size());
      }
   }

   public int scala$collection$immutable$OldHashSet$$bufferSize(final int size) {
      return .MODULE$.min$extension(scala.Predef..MODULE$.intWrapper(size + 6), 224);
   }

   public OldHashSet scala$collection$immutable$OldHashSet$$nullToEmpty(final OldHashSet s) {
      return s == null ? this.empty() : s;
   }

   private void writeObject(final ObjectOutputStream out) {
   }

   private void readObject(final ObjectInputStream in) {
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(OldHashSet$.class);
   }

   private OldHashSet$() {
   }
}
