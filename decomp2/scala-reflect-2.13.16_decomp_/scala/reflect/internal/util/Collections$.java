package scala.reflect.internal.util;

import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Function3;
import scala.Option;
import scala.PartialFunction;
import scala.Tuple2;
import scala.collection.Iterable;
import scala.collection.IterableOnce;
import scala.collection.Iterator;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.collection.mutable.BitSet;
import scala.collection.mutable.LinkedHashMap;
import scala.collection.mutable.ListBuffer;
import scala.reflect.ClassTag;

public final class Collections$ implements Collections {
   public static final Collections$ MODULE$ = new Collections$();
   private static Tuple2 scala$reflect$internal$util$Collections$$TupleOfNil;

   static {
      Collections.$init$(MODULE$);
   }

   public final boolean corresponds3(final List xs1, final List xs2, final List xs3, final Function3 f) {
      return Collections.corresponds3$(this, xs1, xs2, xs3, f);
   }

   public final boolean mexists(final List xss, final Function1 p) {
      return Collections.mexists$(this, xss, p);
   }

   public final boolean mforall(final List xss, final Function1 p) {
      return Collections.mforall$(this, xss, p);
   }

   public final List mmap(final List xss, final Function1 f) {
      return Collections.mmap$(this, xss, f);
   }

   public final Option mfind(final List xss, final Function1 p) {
      return Collections.mfind$(this, xss, p);
   }

   public final void mforeach(final List xss, final Function1 f) {
      Collections.mforeach$(this, (List)xss, f);
   }

   public final void mforeach(final Iterable xss, final Function1 f) {
      Collections.mforeach$(this, (Iterable)xss, f);
   }

   public final List mapList(final List as, final Function1 f) {
      return Collections.mapList$(this, as, f);
   }

   public final boolean sameElementsEquals(final List thiss, final List that) {
      return Collections.sameElementsEquals$(this, thiss, that);
   }

   public final Option collectFirst(final List as, final PartialFunction pf) {
      return Collections.collectFirst$(this, as, pf);
   }

   public final List map2(final List xs1, final List xs2, final Function2 f) {
      return Collections.map2$(this, xs1, xs2, f);
   }

   public final List map2Conserve(final List xs, final List ys, final Function2 f) {
      return Collections.map2Conserve$(this, xs, ys, f);
   }

   public final List map3(final List xs1, final List xs2, final List xs3, final Function3 f) {
      return Collections.map3$(this, xs1, xs2, xs3, f);
   }

   public final List flatMap2(final List xs1, final List xs2, final Function2 f) {
      return Collections.flatMap2$(this, xs1, xs2, f);
   }

   public final Object foldLeft2(final List xs1, final List xs2, final Object z0, final Function3 f) {
      return Collections.foldLeft2$(this, xs1, xs2, z0, f);
   }

   public final List flatCollect(final List elems, final PartialFunction pf) {
      return Collections.flatCollect$(this, elems, pf);
   }

   public final List distinctBy(final List xs, final Function1 f) {
      return Collections.distinctBy$(this, xs, f);
   }

   public final boolean flattensToEmpty(final Seq xss) {
      return Collections.flattensToEmpty$(this, xss);
   }

   public final void foreachWithIndex(final List xs, final Function2 f) {
      Collections.foreachWithIndex$(this, xs, f);
   }

   public final Object findOrElse(final IterableOnce xs, final Function1 p, final Function0 orElse) {
      return Collections.findOrElse$(this, xs, p, orElse);
   }

   public final Map mapFrom(final List xs, final Function1 f) {
      return Collections.mapFrom$(this, xs, f);
   }

   public final LinkedHashMap linkedMapFrom(final List xs, final Function1 f) {
      return Collections.linkedMapFrom$(this, xs, f);
   }

   public final List mapWithIndex(final List xs, final Function2 f) {
      return Collections.mapWithIndex$(this, xs, f);
   }

   public final Map collectMap2(final List xs1, final List xs2, final Function2 p) {
      return Collections.collectMap2$(this, xs1, xs2, p);
   }

   public final void foreach2(final List xs1, final List xs2, final Function2 f) {
      Collections.foreach2$(this, xs1, xs2, f);
   }

   public final void foreach3(final List xs1, final List xs2, final List xs3, final Function3 f) {
      Collections.foreach3$(this, xs1, xs2, xs3, f);
   }

   public final boolean exists2(final List xs1, final List xs2, final Function2 f) {
      return Collections.exists2$(this, xs1, xs2, f);
   }

   public final boolean exists3(final List xs1, final List xs2, final List xs3, final Function3 f) {
      return Collections.exists3$(this, xs1, xs2, xs3, f);
   }

   public final boolean forall3(final List xs1, final List xs2, final List xs3, final Function3 f) {
      return Collections.forall3$(this, xs1, xs2, xs3, f);
   }

   public final Iterator mapFilter2(final Iterator itA, final Iterator itB, final Function2 f) {
      return Collections.mapFilter2$(this, itA, itB, f);
   }

   public final Object mapToArray(final List xs, final Function1 f, final ClassTag evidence$1) {
      return Collections.mapToArray$(this, xs, f, evidence$1);
   }

   public final List mapFromArray(final Object arr, final Function1 f) {
      return Collections.mapFromArray$(this, arr, f);
   }

   public final Option sequenceOpt(final List as) {
      return Collections.sequenceOpt$(this, as);
   }

   public final Option traverseOpt(final List as, final Function1 f) {
      return Collections.traverseOpt$(this, as, f);
   }

   public final void partitionInto(final List xs, final Function1 pred, final ListBuffer ayes, final ListBuffer nays) {
      Collections.partitionInto$(this, xs, pred, ayes, nays);
   }

   public final BitSet bitSetByPredicate(final List xs, final Function1 pred) {
      return Collections.bitSetByPredicate$(this, xs, pred);
   }

   public final Option transposeSafe(final List ass) {
      return Collections.transposeSafe$(this, ass);
   }

   public final boolean sameLength(final List xs1, final List xs2) {
      return Collections.sameLength$(this, xs1, xs2);
   }

   public final int compareLengths(final List xs1, final List xs2) {
      return Collections.compareLengths$(this, xs1, xs2);
   }

   public final boolean hasLength(final List xs, final int len) {
      return Collections.hasLength$(this, xs, len);
   }

   public final int sumSize(final List xss, final int acc) {
      return Collections.sumSize$(this, xss, acc);
   }

   public final List fillList(final int n, final Object t) {
      return Collections.fillList$(this, n, t);
   }

   public final void mapToArray(final List as, final Object arr, final int i, final Function1 f) {
      Collections.mapToArray$(this, as, arr, i, f);
   }

   public final Tuple2 partitionConserve(final List as, final Function1 p) {
      return Collections.partitionConserve$(this, as, p);
   }

   public Tuple2 scala$reflect$internal$util$Collections$$TupleOfNil() {
      return scala$reflect$internal$util$Collections$$TupleOfNil;
   }

   public final void scala$reflect$internal$util$Collections$_setter_$scala$reflect$internal$util$Collections$$TupleOfNil_$eq(final Tuple2 x$1) {
      scala$reflect$internal$util$Collections$$TupleOfNil = x$1;
   }

   private Collections$() {
   }
}
