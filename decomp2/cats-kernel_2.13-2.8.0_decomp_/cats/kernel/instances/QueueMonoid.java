package cats.kernel.instances;

import cats.kernel.Eq;
import cats.kernel.Monoid;
import cats.kernel.Semigroup;
import scala.Option;
import scala.collection.IterableOnce;
import scala.collection.immutable.Queue;
import scala.collection.immutable.Queue.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005E3AAB\u0004\u0001\u001d!)Q\u0006\u0001C\u0001]!)\u0011\u0007\u0001C\u0001e!)1\u0007\u0001C\u0001i!)\u0011\b\u0001C!u!)\u0011\t\u0001C!\u0005\nY\u0011+^3vK6{gn\\5e\u0015\tA\u0011\"A\u0005j]N$\u0018M\\2fg*\u0011!bC\u0001\u0007W\u0016\u0014h.\u001a7\u000b\u00031\tAaY1ug\u000e\u0001QCA\b%'\r\u0001\u0001C\u0006\t\u0003#Qi\u0011A\u0005\u0006\u0002'\u0005)1oY1mC&\u0011QC\u0005\u0002\u0007\u0003:L(+\u001a4\u0011\u0007]A\"$D\u0001\n\u0013\tI\u0012B\u0001\u0004N_:|\u0017\u000e\u001a\t\u00047\u0001\u0012S\"\u0001\u000f\u000b\u0005uq\u0012!C5n[V$\u0018M\u00197f\u0015\ty\"#\u0001\u0006d_2dWm\u0019;j_:L!!\t\u000f\u0003\u000bE+X-^3\u0011\u0005\r\"C\u0002\u0001\u0003\u0006K\u0001\u0011\rA\n\u0002\u0002\u0003F\u0011qE\u000b\t\u0003#!J!!\u000b\n\u0003\u000f9{G\u000f[5oOB\u0011\u0011cK\u0005\u0003YI\u00111!\u00118z\u0003\u0019a\u0014N\\5u}Q\tq\u0006E\u00021\u0001\tj\u0011aB\u0001\u0006K6\u0004H/_\u000b\u00025\u000591m\\7cS:,Gc\u0001\u000e6o!)ag\u0001a\u00015\u0005\t\u0001\u0010C\u00039\u0007\u0001\u0007!$A\u0001z\u0003!\u0019w.\u001c2j]\u0016tEc\u0001\u000e<y!)a\u0007\u0002a\u00015!)Q\b\u0002a\u0001}\u0005\ta\u000e\u0005\u0002\u0012\u007f%\u0011\u0001I\u0005\u0002\u0004\u0013:$\u0018AC2p[\nLg.Z!mYR\u0011!d\u0011\u0005\u0006\t\u0016\u0001\r!R\u0001\u0003qN\u00042A\u0012(\u001b\u001d\t9EJ\u0004\u0002I\u00176\t\u0011J\u0003\u0002K\u001b\u00051AH]8pizJ\u0011aE\u0005\u0003\u001bJ\tq\u0001]1dW\u0006<W-\u0003\u0002P!\na\u0011\n^3sC\ndWm\u00148dK*\u0011QJ\u0005"
)
public class QueueMonoid implements Monoid {
   public double empty$mcD$sp() {
      return Monoid.empty$mcD$sp$(this);
   }

   public float empty$mcF$sp() {
      return Monoid.empty$mcF$sp$(this);
   }

   public int empty$mcI$sp() {
      return Monoid.empty$mcI$sp$(this);
   }

   public long empty$mcJ$sp() {
      return Monoid.empty$mcJ$sp$(this);
   }

   public boolean isEmpty(final Object a, final Eq ev) {
      return Monoid.isEmpty$(this, a, ev);
   }

   public boolean isEmpty$mcD$sp(final double a, final Eq ev) {
      return Monoid.isEmpty$mcD$sp$(this, a, ev);
   }

   public boolean isEmpty$mcF$sp(final float a, final Eq ev) {
      return Monoid.isEmpty$mcF$sp$(this, a, ev);
   }

   public boolean isEmpty$mcI$sp(final int a, final Eq ev) {
      return Monoid.isEmpty$mcI$sp$(this, a, ev);
   }

   public boolean isEmpty$mcJ$sp(final long a, final Eq ev) {
      return Monoid.isEmpty$mcJ$sp$(this, a, ev);
   }

   public double combineN$mcD$sp(final double a, final int n) {
      return Monoid.combineN$mcD$sp$(this, a, n);
   }

   public float combineN$mcF$sp(final float a, final int n) {
      return Monoid.combineN$mcF$sp$(this, a, n);
   }

   public int combineN$mcI$sp(final int a, final int n) {
      return Monoid.combineN$mcI$sp$(this, a, n);
   }

   public long combineN$mcJ$sp(final long a, final int n) {
      return Monoid.combineN$mcJ$sp$(this, a, n);
   }

   public double combineAll$mcD$sp(final IterableOnce as) {
      return Monoid.combineAll$mcD$sp$(this, as);
   }

   public float combineAll$mcF$sp(final IterableOnce as) {
      return Monoid.combineAll$mcF$sp$(this, as);
   }

   public int combineAll$mcI$sp(final IterableOnce as) {
      return Monoid.combineAll$mcI$sp$(this, as);
   }

   public long combineAll$mcJ$sp(final IterableOnce as) {
      return Monoid.combineAll$mcJ$sp$(this, as);
   }

   public Option combineAllOption(final IterableOnce as) {
      return Monoid.combineAllOption$(this, as);
   }

   public Monoid reverse() {
      return Monoid.reverse$(this);
   }

   public Monoid reverse$mcD$sp() {
      return Monoid.reverse$mcD$sp$(this);
   }

   public Monoid reverse$mcF$sp() {
      return Monoid.reverse$mcF$sp$(this);
   }

   public Monoid reverse$mcI$sp() {
      return Monoid.reverse$mcI$sp$(this);
   }

   public Monoid reverse$mcJ$sp() {
      return Monoid.reverse$mcJ$sp$(this);
   }

   public double combine$mcD$sp(final double x, final double y) {
      return Semigroup.combine$mcD$sp$(this, x, y);
   }

   public float combine$mcF$sp(final float x, final float y) {
      return Semigroup.combine$mcF$sp$(this, x, y);
   }

   public int combine$mcI$sp(final int x, final int y) {
      return Semigroup.combine$mcI$sp$(this, x, y);
   }

   public long combine$mcJ$sp(final long x, final long y) {
      return Semigroup.combine$mcJ$sp$(this, x, y);
   }

   public Object repeatedCombineN(final Object a, final int n) {
      return Semigroup.repeatedCombineN$(this, a, n);
   }

   public double repeatedCombineN$mcD$sp(final double a, final int n) {
      return Semigroup.repeatedCombineN$mcD$sp$(this, a, n);
   }

   public float repeatedCombineN$mcF$sp(final float a, final int n) {
      return Semigroup.repeatedCombineN$mcF$sp$(this, a, n);
   }

   public int repeatedCombineN$mcI$sp(final int a, final int n) {
      return Semigroup.repeatedCombineN$mcI$sp$(this, a, n);
   }

   public long repeatedCombineN$mcJ$sp(final long a, final int n) {
      return Semigroup.repeatedCombineN$mcJ$sp$(this, a, n);
   }

   public Semigroup intercalate(final Object middle) {
      return Semigroup.intercalate$(this, middle);
   }

   public Semigroup intercalate$mcD$sp(final double middle) {
      return Semigroup.intercalate$mcD$sp$(this, middle);
   }

   public Semigroup intercalate$mcF$sp(final float middle) {
      return Semigroup.intercalate$mcF$sp$(this, middle);
   }

   public Semigroup intercalate$mcI$sp(final int middle) {
      return Semigroup.intercalate$mcI$sp$(this, middle);
   }

   public Semigroup intercalate$mcJ$sp(final long middle) {
      return Semigroup.intercalate$mcJ$sp$(this, middle);
   }

   public Queue empty() {
      return .MODULE$.empty();
   }

   public Queue combine(final Queue x, final Queue y) {
      return (Queue)x.$plus$plus(y);
   }

   public Queue combineN(final Queue x, final int n) {
      return (Queue)StaticMethods$.MODULE$.combineNIterable(.MODULE$.newBuilder(), x, n);
   }

   public Queue combineAll(final IterableOnce xs) {
      return (Queue)StaticMethods$.MODULE$.combineAllIterable(.MODULE$.newBuilder(), xs);
   }

   public QueueMonoid() {
      Semigroup.$init$(this);
      Monoid.$init$(this);
   }
}
