package cats.kernel.instances;

import cats.kernel.CommutativeGroup;
import cats.kernel.CommutativeMonoid;
import cats.kernel.CommutativeSemigroup;
import cats.kernel.Eq;
import cats.kernel.Group;
import cats.kernel.Monoid;
import cats.kernel.Semigroup;
import scala.Option;
import scala.collection.IterableOnce;
import scala.concurrent.duration.Duration;
import scala.concurrent.duration.Duration.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005Q2AAB\u0004\u0001\u001d!)\u0011\u0005\u0001C\u0001E!)Q\u0005\u0001C\u0001M!)q\u0005\u0001C\u0001Q!)1\u0006\u0001C\u0001Y!)\u0001\u0007\u0001C!c\tiA)\u001e:bi&|gn\u0012:pkBT!\u0001C\u0005\u0002\u0013%t7\u000f^1oG\u0016\u001c(B\u0001\u0006\f\u0003\u0019YWM\u001d8fY*\tA\"\u0001\u0003dCR\u001c8\u0001A\n\u0004\u0001=)\u0002C\u0001\t\u0014\u001b\u0005\t\"\"\u0001\n\u0002\u000bM\u001c\u0017\r\\1\n\u0005Q\t\"AB!osJ+g\rE\u0002\u0017/ei\u0011!C\u0005\u00031%\u0011\u0001cQ8n[V$\u0018\r^5wK\u001e\u0013x.\u001e9\u0011\u0005iyR\"A\u000e\u000b\u0005qi\u0012\u0001\u00033ve\u0006$\u0018n\u001c8\u000b\u0005y\t\u0012AC2p]\u000e,(O]3oi&\u0011\u0001e\u0007\u0002\t\tV\u0014\u0018\r^5p]\u00061A(\u001b8jiz\"\u0012a\t\t\u0003I\u0001i\u0011aB\u0001\u0006K6\u0004H/_\u000b\u00023\u00059\u0011N\u001c<feN,GCA\r*\u0011\u0015Q3\u00011\u0001\u001a\u0003\u0005A\u0018aB2p[\nLg.\u001a\u000b\u000435r\u0003\"\u0002\u0016\u0005\u0001\u0004I\u0002\"B\u0018\u0005\u0001\u0004I\u0012!A=\u0002\rI,Wn\u001c<f)\rI\"g\r\u0005\u0006U\u0015\u0001\r!\u0007\u0005\u0006_\u0015\u0001\r!\u0007"
)
public class DurationGroup implements CommutativeGroup {
   public CommutativeMonoid reverse() {
      return CommutativeMonoid.reverse$(this);
   }

   public CommutativeMonoid reverse$mcD$sp() {
      return CommutativeMonoid.reverse$mcD$sp$(this);
   }

   public CommutativeMonoid reverse$mcF$sp() {
      return CommutativeMonoid.reverse$mcF$sp$(this);
   }

   public CommutativeMonoid reverse$mcI$sp() {
      return CommutativeMonoid.reverse$mcI$sp$(this);
   }

   public CommutativeMonoid reverse$mcJ$sp() {
      return CommutativeMonoid.reverse$mcJ$sp$(this);
   }

   public CommutativeSemigroup intercalate(final Object middle) {
      return CommutativeSemigroup.intercalate$(this, middle);
   }

   public CommutativeSemigroup intercalate$mcD$sp(final double middle) {
      return CommutativeSemigroup.intercalate$mcD$sp$(this, middle);
   }

   public CommutativeSemigroup intercalate$mcF$sp(final float middle) {
      return CommutativeSemigroup.intercalate$mcF$sp$(this, middle);
   }

   public CommutativeSemigroup intercalate$mcI$sp(final int middle) {
      return CommutativeSemigroup.intercalate$mcI$sp$(this, middle);
   }

   public CommutativeSemigroup intercalate$mcJ$sp(final long middle) {
      return CommutativeSemigroup.intercalate$mcJ$sp$(this, middle);
   }

   public double inverse$mcD$sp(final double a) {
      return Group.inverse$mcD$sp$(this, a);
   }

   public float inverse$mcF$sp(final float a) {
      return Group.inverse$mcF$sp$(this, a);
   }

   public int inverse$mcI$sp(final int a) {
      return Group.inverse$mcI$sp$(this, a);
   }

   public long inverse$mcJ$sp(final long a) {
      return Group.inverse$mcJ$sp$(this, a);
   }

   public double remove$mcD$sp(final double a, final double b) {
      return Group.remove$mcD$sp$(this, a, b);
   }

   public float remove$mcF$sp(final float a, final float b) {
      return Group.remove$mcF$sp$(this, a, b);
   }

   public int remove$mcI$sp(final int a, final int b) {
      return Group.remove$mcI$sp$(this, a, b);
   }

   public long remove$mcJ$sp(final long a, final long b) {
      return Group.remove$mcJ$sp$(this, a, b);
   }

   public Object combineN(final Object a, final int n) {
      return Group.combineN$(this, a, n);
   }

   public double combineN$mcD$sp(final double a, final int n) {
      return Group.combineN$mcD$sp$(this, a, n);
   }

   public float combineN$mcF$sp(final float a, final int n) {
      return Group.combineN$mcF$sp$(this, a, n);
   }

   public int combineN$mcI$sp(final int a, final int n) {
      return Group.combineN$mcI$sp$(this, a, n);
   }

   public long combineN$mcJ$sp(final long a, final int n) {
      return Group.combineN$mcJ$sp$(this, a, n);
   }

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

   public Object combineAll(final IterableOnce as) {
      return Monoid.combineAll$(this, as);
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

   public Duration empty() {
      return .MODULE$.Zero();
   }

   public Duration inverse(final Duration x) {
      return x.unary_$minus();
   }

   public Duration combine(final Duration x, final Duration y) {
      return x.$plus(y);
   }

   public Duration remove(final Duration x, final Duration y) {
      return x.$minus(y);
   }

   public DurationGroup() {
      Semigroup.$init$(this);
      Monoid.$init$(this);
      Group.$init$(this);
      CommutativeSemigroup.$init$(this);
      CommutativeMonoid.$init$(this);
   }
}
