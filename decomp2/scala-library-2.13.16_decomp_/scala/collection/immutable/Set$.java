package scala.collection.immutable;

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
import scala.math.Integral;
import scala.runtime.ModuleSerializationProxy;

public final class Set$ implements IterableFactory {
   public static final Set$ MODULE$ = new Set$();
   private static final long serialVersionUID = 3L;

   static {
      Set$ var10000 = MODULE$;
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

   public Set empty() {
      return Set.EmptySet$.MODULE$;
   }

   public Set from(final IterableOnce it) {
      if (it.knownSize() == 0) {
         return Set.EmptySet$.MODULE$;
      } else if (it instanceof HashSet) {
         return (HashSet)it;
      } else if (it instanceof ListSet) {
         return (ListSet)it;
      } else if (it instanceof Set.Set1) {
         return (Set.Set1)it;
      } else if (it instanceof Set.Set2) {
         return (Set.Set2)it;
      } else if (it instanceof Set.Set3) {
         return (Set.Set3)it;
      } else if (it instanceof Set.Set4) {
         return (Set.Set4)it;
      } else if (it instanceof HashMap.HashKeySet) {
         return (HashMap.HashKeySet)it;
      } else {
         return (Set)(it instanceof MapOps.ImmutableKeySet ? (MapOps.ImmutableKeySet)it : (Set)(new SetBuilderImpl()).addAll(it).result());
      }
   }

   public Builder newBuilder() {
      return new SetBuilderImpl();
   }

   public Set emptyInstance() {
      return Set.EmptySet$.MODULE$;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Set$.class);
   }

   private Set$() {
   }
}
