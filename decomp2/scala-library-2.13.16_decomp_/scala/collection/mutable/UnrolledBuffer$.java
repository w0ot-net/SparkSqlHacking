package scala.collection.mutable;

import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Function3;
import scala.Function4;
import scala.Function5;
import scala.collection.ClassTagIterableFactory;
import scala.collection.ClassTagSeqFactory;
import scala.collection.EvidenceIterableFactory;
import scala.collection.Factory;
import scala.collection.IterableOnce;
import scala.collection.SeqFactory;
import scala.collection.StrictOptimizedClassTagSeqFactory;
import scala.math.Integral;
import scala.reflect.ClassTag;
import scala.runtime.ModuleSerializationProxy;

public final class UnrolledBuffer$ implements StrictOptimizedClassTagSeqFactory {
   public static final UnrolledBuffer$ MODULE$ = new UnrolledBuffer$();
   private static final long serialVersionUID = 3L;
   private static final SeqFactory untagged;
   private static final int waterline;
   /** @deprecated */
   private static final int waterlineDelim;
   private static final int unrolledlength;

   static {
      UnrolledBuffer$ var10000 = MODULE$;
      var10000 = MODULE$;
      var10000 = MODULE$;
      var10000 = MODULE$;
      untagged = new ClassTagSeqFactory.AnySeqDelegate(MODULE$);
      waterline = 50;
      var10000 = MODULE$;
      waterlineDelim = 100;
      unrolledlength = 32;
   }

   public scala.collection.SeqOps fill(final int n, final Function0 elem, final ClassTag evidence$34) {
      return StrictOptimizedClassTagSeqFactory.fill$(this, n, elem, evidence$34);
   }

   public scala.collection.SeqOps tabulate(final int n, final Function1 f, final ClassTag evidence$35) {
      return StrictOptimizedClassTagSeqFactory.tabulate$(this, n, f, evidence$35);
   }

   public final scala.collection.SeqOps unapplySeq(final scala.collection.SeqOps x) {
      return ClassTagSeqFactory.unapplySeq$(this, x);
   }

   public Object range(final Object start, final Object end, final Integral evidence$22, final ClassTag evidence$23) {
      return ClassTagIterableFactory.range$(this, start, end, evidence$22, evidence$23);
   }

   public Object range(final Object start, final Object end, final Object step, final Integral evidence$24, final ClassTag evidence$25) {
      return ClassTagIterableFactory.range$(this, start, end, step, evidence$24, evidence$25);
   }

   public Object fill(final int n1, final int n2, final Function0 elem, final ClassTag evidence$26) {
      return ClassTagIterableFactory.fill$(this, n1, n2, elem, evidence$26);
   }

   public Object fill(final int n1, final int n2, final int n3, final Function0 elem, final ClassTag evidence$27) {
      return ClassTagIterableFactory.fill$(this, n1, n2, n3, elem, evidence$27);
   }

   public Object fill(final int n1, final int n2, final int n3, final int n4, final Function0 elem, final ClassTag evidence$28) {
      return ClassTagIterableFactory.fill$(this, n1, n2, n3, n4, elem, evidence$28);
   }

   public Object fill(final int n1, final int n2, final int n3, final int n4, final int n5, final Function0 elem, final ClassTag evidence$29) {
      return ClassTagIterableFactory.fill$(this, n1, n2, n3, n4, n5, elem, evidence$29);
   }

   public Object tabulate(final int n1, final int n2, final Function2 f, final ClassTag evidence$30) {
      return ClassTagIterableFactory.tabulate$(this, n1, n2, f, evidence$30);
   }

   public Object tabulate(final int n1, final int n2, final int n3, final Function3 f, final ClassTag evidence$31) {
      return ClassTagIterableFactory.tabulate$(this, n1, n2, n3, f, evidence$31);
   }

   public Object tabulate(final int n1, final int n2, final int n3, final int n4, final Function4 f, final ClassTag evidence$32) {
      return ClassTagIterableFactory.tabulate$(this, n1, n2, n3, n4, f, evidence$32);
   }

   public Object tabulate(final int n1, final int n2, final int n3, final int n4, final int n5, final Function5 f, final ClassTag evidence$33) {
      return ClassTagIterableFactory.tabulate$(this, n1, n2, n3, n4, n5, f, evidence$33);
   }

   public Object apply(final scala.collection.immutable.Seq xs, final Object evidence$7) {
      return EvidenceIterableFactory.apply$(this, xs, evidence$7);
   }

   public Object iterate(final Object start, final int len, final Function1 f, final Object evidence$10) {
      return EvidenceIterableFactory.iterate$(this, start, len, f, evidence$10);
   }

   public Object unfold(final Object init, final Function1 f, final Object evidence$11) {
      return EvidenceIterableFactory.unfold$(this, init, f, evidence$11);
   }

   public Factory evidenceIterableFactory(final Object evidence$13) {
      return EvidenceIterableFactory.evidenceIterableFactory$(this, evidence$13);
   }

   public SeqFactory untagged() {
      return untagged;
   }

   public UnrolledBuffer empty(final ClassTag evidence$1) {
      return new UnrolledBuffer(evidence$1);
   }

   public UnrolledBuffer from(final IterableOnce source, final ClassTag evidence$2) {
      return (UnrolledBuffer)(new UnrolledBuffer(evidence$2)).addAll(source);
   }

   public UnrolledBuffer newBuilder(final ClassTag evidence$3) {
      return new UnrolledBuffer(evidence$3);
   }

   public final int waterline() {
      return waterline;
   }

   public final int waterlineDenom() {
      return 100;
   }

   /** @deprecated */
   public final int waterlineDelim() {
      return waterlineDelim;
   }

   public int unrolledlength() {
      return unrolledlength;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(UnrolledBuffer$.class);
   }

   private UnrolledBuffer$() {
   }
}
