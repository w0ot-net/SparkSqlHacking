package spire.std;

import cats.kernel.Eq;
import cats.kernel.Monoid;
import cats.kernel.Semigroup;
import java.lang.invoke.SerializedLambda;
import scala.Option;
import scala.collection.Factory;
import scala.collection.IterableOnce;
import scala.collection.IterableOps;
import scala.collection.mutable.Builder;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\r4AAB\u0004\u0003\u0019!AA\t\u0001B\u0001B\u0003-Q\tC\u0003I\u0001\u0011\u0005\u0011\nC\u0003O\u0001\u0011\u0005q\nC\u0003Q\u0001\u0011\u0005\u0011\u000bC\u0003W\u0001\u0011\u0005sK\u0001\bJi\u0016\u0014\u0018M\u00197f\u001b>tw.\u001b3\u000b\u0005!I\u0011aA:uI*\t!\"A\u0003ta&\u0014Xm\u0001\u0001\u0016\u00075\u0019de\u0005\u0003\u0001\u001dQ\t\u0005CA\b\u0013\u001b\u0005\u0001\"\"A\t\u0002\u000bM\u001c\u0017\r\\1\n\u0005M\u0001\"AB!osJ+g\rE\u0002\u0016C\u0011r!A\u0006\u0010\u000f\u0005]abB\u0001\r\u001c\u001b\u0005I\"B\u0001\u000e\f\u0003\u0019a$o\\8u}%\t!\"\u0003\u0002\u001e\u0013\u00059\u0011\r\\4fEJ\f\u0017BA\u0010!\u0003\u001d\u0001\u0018mY6bO\u0016T!!H\u0005\n\u0005\t\u001a#AB'p]>LGM\u0003\u0002 AA\u0011QE\n\u0007\u0001\t\u00159\u0003A1\u0001)\u0005\t\u0019\u0016)\u0005\u0002*YA\u0011qBK\u0005\u0003WA\u0011qAT8uQ&tw\rE\u0003.aIJD%D\u0001/\u0015\ty\u0003#\u0001\u0006d_2dWm\u0019;j_:L!!\r\u0018\u0003\u0017%#XM]1cY\u0016|\u0005o\u001d\t\u0003KM\"Q\u0001\u000e\u0001C\u0002U\u0012\u0011!Q\t\u0003SY\u0002\"aD\u001c\n\u0005a\u0002\"aA!osB\u0011!H\u0010\b\u0003wur!\u0001\u0007\u001f\n\u0003EI!a\b\t\n\u0005}\u0002%\u0001C%uKJ\f'\r\\3\u000b\u0005}\u0001\u0002C\u0001\u001eC\u0013\t\u0019\u0005I\u0001\u0007TKJL\u0017\r\\5{C\ndW-A\u0002dE\u001a\u0004B!\f$3I%\u0011qI\f\u0002\b\r\u0006\u001cGo\u001c:z\u0003\u0019a\u0014N\\5u}Q\t!\n\u0006\u0002L\u001bB!A\n\u0001\u001a%\u001b\u00059\u0001\"\u0002#\u0003\u0001\b)\u0015!B3naRLX#\u0001\u0013\u0002\u000f\r|WNY5oKR\u0019AE\u0015+\t\u000bM#\u0001\u0019\u0001\u0013\u0002\u0003aDQ!\u0016\u0003A\u0002\u0011\n\u0011!_\u0001\u000bG>l'-\u001b8f\u00032dGC\u0001\u0013Y\u0011\u0015IV\u00011\u0001[\u0003\tA8\u000fE\u0002;7\u0012J!\u0001\u0018!\u0003\u0019%#XM]1cY\u0016|enY3)\t\u0001q\u0016M\u0019\t\u0003\u001f}K!\u0001\u0019\t\u0003!M+'/[1m-\u0016\u00148/[8o+&#\u0015!\u0002<bYV,g$\u0001\u0001"
)
public final class IterableMonoid implements Monoid {
   private static final long serialVersionUID = 0L;
   private final Factory cbf;

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

   public IterableOps empty() {
      return (IterableOps)this.cbf.newBuilder().result();
   }

   public IterableOps combine(final IterableOps x, final IterableOps y) {
      Builder b = this.cbf.newBuilder();
      b.$plus$plus$eq(x);
      b.$plus$plus$eq(y);
      return (IterableOps)b.result();
   }

   public IterableOps combineAll(final IterableOnce xs) {
      Builder b = this.cbf.newBuilder();
      xs.iterator().foreach((x$1) -> (Builder)b.$plus$plus$eq(x$1));
      return (IterableOps)b.result();
   }

   public IterableMonoid(final Factory cbf) {
      this.cbf = cbf;
      Semigroup.$init$(this);
      Monoid.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
