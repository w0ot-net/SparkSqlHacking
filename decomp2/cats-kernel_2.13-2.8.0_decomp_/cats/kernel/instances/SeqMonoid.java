package cats.kernel.instances;

import cats.kernel.Eq;
import cats.kernel.Monoid;
import cats.kernel.Semigroup;
import scala.Option;
import scala.collection.IterableOnce;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Seq.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005E3AAB\u0004\u0001\u001d!)Q\u0006\u0001C\u0001]!)\u0011\u0007\u0001C\u0001e!)1\u0007\u0001C\u0001i!)\u0011\b\u0001C!u!)\u0011\t\u0001C!\u0005\nI1+Z9N_:|\u0017\u000e\u001a\u0006\u0003\u0011%\t\u0011\"\u001b8ti\u0006t7-Z:\u000b\u0005)Y\u0011AB6fe:,GNC\u0001\r\u0003\u0011\u0019\u0017\r^:\u0004\u0001U\u0011q\u0002J\n\u0004\u0001A1\u0002CA\t\u0015\u001b\u0005\u0011\"\"A\n\u0002\u000bM\u001c\u0017\r\\1\n\u0005U\u0011\"AB!osJ+g\rE\u0002\u00181ii\u0011!C\u0005\u00033%\u0011a!T8o_&$\u0007cA\u000e!E5\tAD\u0003\u0002\u001e=\u0005I\u0011.\\7vi\u0006\u0014G.\u001a\u0006\u0003?I\t!bY8mY\u0016\u001cG/[8o\u0013\t\tCDA\u0002TKF\u0004\"a\t\u0013\r\u0001\u0011)Q\u0005\u0001b\u0001M\t\t\u0011)\u0005\u0002(UA\u0011\u0011\u0003K\u0005\u0003SI\u0011qAT8uQ&tw\r\u0005\u0002\u0012W%\u0011AF\u0005\u0002\u0004\u0003:L\u0018A\u0002\u001fj]&$h\bF\u00010!\r\u0001\u0004AI\u0007\u0002\u000f\u0005)Q-\u001c9usV\t!$A\u0004d_6\u0014\u0017N\\3\u0015\u0007i)t\u0007C\u00037\u0007\u0001\u0007!$A\u0001y\u0011\u0015A4\u00011\u0001\u001b\u0003\u0005I\u0018\u0001C2p[\nLg.\u001a(\u0015\u0007iYD\bC\u00037\t\u0001\u0007!\u0004C\u0003>\t\u0001\u0007a(A\u0001o!\t\tr(\u0003\u0002A%\t\u0019\u0011J\u001c;\u0002\u0015\r|WNY5oK\u0006cG\u000e\u0006\u0002\u001b\u0007\")A)\u0002a\u0001\u000b\u0006\u0011\u0001p\u001d\t\u0004\r:SbBA$M\u001d\tA5*D\u0001J\u0015\tQU\"\u0001\u0004=e>|GOP\u0005\u0002'%\u0011QJE\u0001\ba\u0006\u001c7.Y4f\u0013\ty\u0005K\u0001\u0007Ji\u0016\u0014\u0018M\u00197f\u001f:\u001cWM\u0003\u0002N%\u0001"
)
public class SeqMonoid implements Monoid {
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

   public Seq empty() {
      return (Seq).MODULE$.empty();
   }

   public Seq combine(final Seq x, final Seq y) {
      return (Seq)x.$plus$plus(y);
   }

   public Seq combineN(final Seq x, final int n) {
      return (Seq)StaticMethods$.MODULE$.combineNIterable(.MODULE$.newBuilder(), x, n);
   }

   public Seq combineAll(final IterableOnce xs) {
      return (Seq)StaticMethods$.MODULE$.combineAllIterable(.MODULE$.newBuilder(), xs);
   }

   public SeqMonoid() {
      Semigroup.$init$(this);
      Monoid.$init$(this);
   }
}
