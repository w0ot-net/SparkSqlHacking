package spire.std;

import cats.kernel.Eq;
import cats.kernel.Monoid;
import cats.kernel.Semigroup;
import java.lang.invoke.SerializedLambda;
import scala.Function2;
import scala.Option;
import scala.Predef.;
import scala.collection.IterableOnce;
import scala.collection.immutable.Map;
import scala.reflect.ScalaSignature;
import scala.runtime.ObjectRef;

@ScalaSignature(
   bytes = "\u0006\u0005q3AAB\u0004\u0001\u0019!A!\t\u0001BC\u0002\u0013\r1\t\u0003\u0005H\u0001\t\u0005\t\u0015!\u0003E\u0011\u0015A\u0005\u0001\"\u0001J\u0011\u0015q\u0005\u0001\"\u0001P\u0011\u0015\u0001\u0006\u0001\"\u0001R\u0005%i\u0015\r]'p]>LGM\u0003\u0002\t\u0013\u0005\u00191\u000f\u001e3\u000b\u0003)\tQa\u001d9je\u0016\u001c\u0001!F\u0002\u000e]a\u001aB\u0001\u0001\b\u0015uA\u0011qBE\u0007\u0002!)\t\u0011#A\u0003tG\u0006d\u0017-\u0003\u0002\u0014!\t1\u0011I\\=SK\u001a\u00042!F\u0011%\u001d\t1bD\u0004\u0002\u001899\u0011\u0001dG\u0007\u00023)\u0011!dC\u0001\u0007yI|w\u000e\u001e \n\u0003)I!!H\u0005\u0002\u000f\u0005dw-\u001a2sC&\u0011q\u0004I\u0001\ba\u0006\u001c7.Y4f\u0015\ti\u0012\"\u0003\u0002#G\t1Qj\u001c8pS\u0012T!a\b\u0011\u0011\t\u0015JCf\u000e\b\u0003M\u001d\u0002\"\u0001\u0007\t\n\u0005!\u0002\u0012A\u0002)sK\u0012,g-\u0003\u0002+W\t\u0019Q*\u00199\u000b\u0005!\u0002\u0002CA\u0017/\u0019\u0001!Qa\f\u0001C\u0002A\u0012\u0011aS\t\u0003cQ\u0002\"a\u0004\u001a\n\u0005M\u0002\"a\u0002(pi\"Lgn\u001a\t\u0003\u001fUJ!A\u000e\t\u0003\u0007\u0005s\u0017\u0010\u0005\u0002.q\u0011)\u0011\b\u0001b\u0001a\t\ta\u000b\u0005\u0002<\u007f9\u0011AH\u0010\b\u00031uJ\u0011!E\u0005\u0003?AI!\u0001Q!\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005}\u0001\u0012AB:dC2\f'/F\u0001E!\r)RiN\u0005\u0003\r\u000e\u0012\u0011bU3nS\u001e\u0014x.\u001e9\u0002\u000fM\u001c\u0017\r\\1sA\u00051A(\u001b8jiz\"\u0012A\u0013\u000b\u0003\u00176\u0003B\u0001\u0014\u0001-o5\tq\u0001C\u0003C\u0007\u0001\u000fA)A\u0003f[B$\u00180F\u0001%\u0003\u001d\u0019w.\u001c2j]\u0016$2\u0001\n*U\u0011\u0015\u0019V\u00011\u0001%\u0003\u0005A\b\"B+\u0006\u0001\u0004!\u0013!A=)\t\u00019&l\u0017\t\u0003\u001faK!!\u0017\t\u0003!M+'/[1m-\u0016\u00148/[8o+&#\u0015!\u0002<bYV,g$\u0001\u0001"
)
public class MapMonoid implements Monoid {
   private static final long serialVersionUID = 0L;
   private final Semigroup scalar;

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

   public Semigroup scalar() {
      return this.scalar;
   }

   public Map empty() {
      return .MODULE$.Map().empty();
   }

   public Map combine(final Map x, final Map y) {
      ObjectRef xx = ObjectRef.create(x);
      Map yy = y;
      ObjectRef f = ObjectRef.create((Function2)(xxx, yx) -> this.scalar().combine(xxx, yx));
      if (x.size() < y.size()) {
         xx.elem = y;
         yy = x;
         f.elem = (xxx, yx) -> this.scalar().combine(yx, xxx);
      }

      return (Map)yy.foldLeft((Map)xx.elem, (z, kv) -> (Map)z.updated(kv._1(), ((Map)xx.elem).get(kv._1()).map((u) -> ((Function2)f.elem).apply(u, kv._2())).getOrElse(() -> kv._2())));
   }

   public MapMonoid(final Semigroup scalar) {
      this.scalar = scalar;
      Semigroup.$init$(this);
      Monoid.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
