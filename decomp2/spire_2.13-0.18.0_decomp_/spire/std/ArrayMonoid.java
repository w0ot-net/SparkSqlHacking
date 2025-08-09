package spire.std;

import cats.kernel.Eq;
import cats.kernel.Monoid;
import cats.kernel.Semigroup;
import scala.Option;
import scala.collection.IterableOnce;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000554A!\u0002\u0004\u0007\u0017!A\u0011\u000b\u0001B\u0002B\u0003-!\u000bC\u0003Z\u0001\u0011\u0005!\fC\u0003`\u0001\u0011\u0005\u0001\rC\u0003b\u0001\u0011\u0005!MA\u0006BeJ\f\u00170T8o_&$'BA\u0004\t\u0003\r\u0019H\u000f\u001a\u0006\u0002\u0013\u0005)1\u000f]5sK\u000e\u0001QC\u0001\u0007)'\u0011\u0001QbE&\u0011\u00059\tR\"A\b\u000b\u0003A\tQa]2bY\u0006L!AE\b\u0003\r\u0005s\u0017PU3g!\r!\u0002e\t\b\u0003+uq!AF\u000e\u000f\u0005]QR\"\u0001\r\u000b\u0005eQ\u0011A\u0002\u001fs_>$h(C\u0001\n\u0013\ta\u0002\"A\u0004bY\u001e,'M]1\n\u0005yy\u0012a\u00029bG.\fw-\u001a\u0006\u00039!I!!\t\u0012\u0003\r5{gn\\5e\u0015\tqr\u0004E\u0002\u000fI\u0019J!!J\b\u0003\u000b\u0005\u0013(/Y=\u0011\u0005\u001dBC\u0002\u0001\u0003\nS\u0001\u0001\u000b\u0011!AC\u0002)\u0012\u0011!Q\t\u0003W9\u0002\"A\u0004\u0017\n\u00055z!a\u0002(pi\"Lgn\u001a\t\u0003\u001d=J!\u0001M\b\u0003\u0007\u0005s\u0017\u0010\u000b\u0004)eUb\u0014I\u0012\t\u0003\u001dMJ!\u0001N\b\u0003\u0017M\u0004XmY5bY&TX\rZ\u0019\u0006GY:\u0014\b\u000f\b\u0003\u001d]J!\u0001O\b\u0002\u0007%sG/\r\u0003%um\u0002bBA\f<\u0013\u0005\u0001\u0012'B\u0012>}\u0001{dB\u0001\b?\u0013\tyt\"A\u0003GY>\fG/\r\u0003%um\u0002\u0012'B\u0012C\u0007\u0016#eB\u0001\bD\u0013\t!u\"\u0001\u0003M_:<\u0017\u0007\u0002\u0013;wA\tTaI$I\u0015&s!A\u0004%\n\u0005%{\u0011A\u0002#pk\ndW-\r\u0003%um\u0002\u0002C\u0001'O\u001d\tQT*\u0003\u0002\u001f\u001f%\u0011q\n\u0015\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003==\t1\"\u001a<jI\u0016t7-\u001a\u00134iA\u00191K\u0016\u0014\u000f\u0005Q+V\"\u0001\u0005\n\u0005yA\u0011BA,Y\u0005!\u0019E.Y:t)\u0006<'B\u0001\u0010\t\u0003\u0019a\u0014N\\5u}Q\t1\f\u0006\u0002]=B\u0019Q\f\u0001\u0014\u000e\u0003\u0019AQ!\u0015\u0002A\u0004I\u000bQ!Z7qif,\u0012aI\u0001\bG>l'-\u001b8f)\r\u00193-\u001a\u0005\u0006I\u0012\u0001\raI\u0001\u0002q\")a\r\u0002a\u0001G\u0005\t\u0011\u0010\u000b\u0003\u0001Q.d\u0007C\u0001\bj\u0013\tQwB\u0001\tTKJL\u0017\r\u001c,feNLwN\\+J\t\u0006)a/\u00197vKz\t\u0001\u0001"
)
public class ArrayMonoid implements Monoid {
   private static final long serialVersionUID = 0L;
   public final ClassTag spire$std$ArrayMonoid$$evidence$34;

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

   public Object combineN(final Object a, final int n) {
      return Monoid.combineN$(this, a, n);
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

   public Object empty() {
      return this.spire$std$ArrayMonoid$$evidence$34.newArray(0);
   }

   public Object combine(final Object x, final Object y) {
      return ArraySupport$.MODULE$.concat(x, y, this.spire$std$ArrayMonoid$$evidence$34);
   }

   public double[] empty$mcD$sp() {
      return (double[])this.empty();
   }

   public float[] empty$mcF$sp() {
      return (float[])this.empty();
   }

   public int[] empty$mcI$sp() {
      return (int[])this.empty();
   }

   public long[] empty$mcJ$sp() {
      return (long[])this.empty();
   }

   public double[] combine$mcD$sp(final double[] x, final double[] y) {
      return (double[])this.combine(x, y);
   }

   public float[] combine$mcF$sp(final float[] x, final float[] y) {
      return (float[])this.combine(x, y);
   }

   public int[] combine$mcI$sp(final int[] x, final int[] y) {
      return (int[])this.combine(x, y);
   }

   public long[] combine$mcJ$sp(final long[] x, final long[] y) {
      return (long[])this.combine(x, y);
   }

   public ArrayMonoid(final ClassTag evidence$34) {
      this.spire$std$ArrayMonoid$$evidence$34 = evidence$34;
      Semigroup.$init$(this);
      Monoid.$init$(this);
   }
}
