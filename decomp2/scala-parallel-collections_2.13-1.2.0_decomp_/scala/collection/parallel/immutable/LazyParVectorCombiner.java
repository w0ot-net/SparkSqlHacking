package scala.collection.parallel.immutable;

import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.collection.Iterable;
import scala.collection.IterableOnce;
import scala.collection.immutable.Seq;
import scala.collection.immutable.VectorBuilder;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.Builder;
import scala.collection.mutable.Growable;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.TaskSupport;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00154Q\u0001D\u0007\u0001\u001bUAQa\f\u0001\u0005\u0002ABqA\r\u0001A\u0002\u0013\u00051\u0007C\u00048\u0001\u0001\u0007I\u0011\u0001\u001d\t\ry\u0002\u0001\u0015)\u00035\u0011\u001dy\u0004A1A\u0005\u0002\u0001Ca\u0001\u0014\u0001!\u0002\u0013\t\u0005\"B'\u0001\t\u0003\u0019\u0004\"\u0002(\u0001\t\u0003y\u0005\"B*\u0001\t\u0003!\u0006\"B+\u0001\t\u00031\u0006\"B,\u0001\t\u0003A&!\u0006'buf\u0004\u0016M\u001d,fGR|'oQ8nE&tWM\u001d\u0006\u0003\u001d=\t\u0011\"[7nkR\f'\r\\3\u000b\u0005A\t\u0012\u0001\u00039be\u0006dG.\u001a7\u000b\u0005I\u0019\u0012AC2pY2,7\r^5p]*\tA#A\u0003tG\u0006d\u0017-\u0006\u0002\u0017CM\u0019\u0001aF\u000e\u0011\u0005aIR\"A\n\n\u0005i\u0019\"AB!osJ+g\r\u0005\u0003\u001d;}YS\"A\b\n\u0005yy!\u0001C\"p[\nLg.\u001a:\u0011\u0005\u0001\nC\u0002\u0001\u0003\u0006E\u0001\u0011\r\u0001\n\u0002\u0002)\u000e\u0001\u0011CA\u0013)!\tAb%\u0003\u0002('\t9aj\u001c;iS:<\u0007C\u0001\r*\u0013\tQ3CA\u0002B]f\u00042\u0001L\u0017 \u001b\u0005i\u0011B\u0001\u0018\u000e\u0005%\u0001\u0016M\u001d,fGR|'/\u0001\u0004=S:LGO\u0010\u000b\u0002cA\u0019A\u0006A\u0010\u0002\u0005MTX#\u0001\u001b\u0011\u0005a)\u0014B\u0001\u001c\u0014\u0005\rIe\u000e^\u0001\u0007gj|F%Z9\u0015\u0005eb\u0004C\u0001\r;\u0013\tY4C\u0001\u0003V]&$\bbB\u001f\u0004\u0003\u0003\u0005\r\u0001N\u0001\u0004q\u0012\n\u0014aA:{A\u00059a/Z2u_J\u001cX#A!\u0011\u0007\t+u)D\u0001D\u0015\t!\u0015#A\u0004nkR\f'\r\\3\n\u0005\u0019\u001b%aC!se\u0006L()\u001e4gKJ\u00042\u0001\u0013& \u001b\u0005I%B\u0001\b\u0012\u0013\tY\u0015JA\u0007WK\u000e$xN\u001d\"vS2$WM]\u0001\tm\u0016\u001cGo\u001c:tA\u0005!1/\u001b>f\u0003\u0019\tG\rZ(oKR\u0011\u0001+U\u0007\u0002\u0001!)!\u000b\u0003a\u0001?\u0005!Q\r\\3n\u0003\u0015\u0019G.Z1s)\u0005I\u0014A\u0002:fgVdG\u000fF\u0001,\u0003\u001d\u0019w.\u001c2j]\u0016,2!\u0017/a)\tQ6\r\u0005\u0003\u001d;m{\u0006C\u0001\u0011]\t\u0015i6B1\u0001_\u0005\u0005)\u0016CA\u0013 !\t\u0001\u0003\rB\u0003b\u0017\t\u0007!MA\u0003OK^$v.\u0005\u0002,Q!)Am\u0003a\u00015\u0006)q\u000e\u001e5fe\u0002"
)
public class LazyParVectorCombiner implements Combiner {
   private int sz;
   private final ArrayBuffer vectors;
   private transient volatile TaskSupport _combinerTaskSupport;

   public TaskSupport combinerTaskSupport() {
      return Combiner.combinerTaskSupport$(this);
   }

   public void combinerTaskSupport_$eq(final TaskSupport cts) {
      Combiner.combinerTaskSupport_$eq$(this, cts);
   }

   public boolean canBeShared() {
      return Combiner.canBeShared$(this);
   }

   public Object resultWithTaskSupport() {
      return Combiner.resultWithTaskSupport$(this);
   }

   public Object fromSequential(final IterableOnce seq) {
      return Combiner.fromSequential$(this, seq);
   }

   public void sizeHint(final int size) {
      Builder.sizeHint$(this, size);
   }

   public final void sizeHint(final IterableOnce coll, final int delta) {
      Builder.sizeHint$(this, coll, delta);
   }

   public final int sizeHint$default$2() {
      return Builder.sizeHint$default$2$(this);
   }

   public final void sizeHintBounded(final int size, final Iterable boundingColl) {
      Builder.sizeHintBounded$(this, size, boundingColl);
   }

   public Builder mapResult(final Function1 f) {
      return Builder.mapResult$(this, f);
   }

   public final Growable $plus$eq(final Object elem) {
      return Growable.$plus$eq$(this, elem);
   }

   /** @deprecated */
   public final Growable $plus$eq(final Object elem1, final Object elem2, final Seq elems) {
      return Growable.$plus$eq$(this, elem1, elem2, elems);
   }

   public Growable addAll(final IterableOnce elems) {
      return Growable.addAll$(this, elems);
   }

   public final Growable $plus$plus$eq(final IterableOnce elems) {
      return Growable.$plus$plus$eq$(this, elems);
   }

   public int knownSize() {
      return Growable.knownSize$(this);
   }

   public TaskSupport _combinerTaskSupport() {
      return this._combinerTaskSupport;
   }

   public void _combinerTaskSupport_$eq(final TaskSupport x$1) {
      this._combinerTaskSupport = x$1;
   }

   public int sz() {
      return this.sz;
   }

   public void sz_$eq(final int x$1) {
      this.sz = x$1;
   }

   public ArrayBuffer vectors() {
      return this.vectors;
   }

   public int size() {
      return this.sz();
   }

   public LazyParVectorCombiner addOne(final Object elem) {
      ((Growable)this.vectors().last()).$plus$eq(elem);
      this.sz_$eq(this.sz() + 1);
      return this;
   }

   public void clear() {
      this.vectors().clear();
      this.vectors().$plus$eq(new VectorBuilder());
      this.sz_$eq(0);
   }

   public ParVector result() {
      VectorBuilder rvb = new VectorBuilder();
      this.vectors().foreach((vb) -> (VectorBuilder)rvb.$plus$plus$eq(vb.result()));
      return new ParVector(rvb.result());
   }

   public Combiner combine(final Combiner other) {
      if (other == this) {
         return this;
      } else {
         LazyParVectorCombiner that = (LazyParVectorCombiner)other;
         this.sz_$eq(this.sz() + that.sz());
         this.vectors().$plus$plus$eq(that.vectors());
         return this;
      }
   }

   public LazyParVectorCombiner() {
      Growable.$init$(this);
      Builder.$init$(this);
      Combiner.$init$(this);
      this.sz = 0;
      this.vectors = (ArrayBuffer)(new ArrayBuffer()).$plus$eq(new VectorBuilder());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
