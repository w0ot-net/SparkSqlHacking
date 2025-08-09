package scala.collection.parallel;

import scala.Function1;
import scala.collection.Iterable;
import scala.collection.IterableOnce;
import scala.collection.immutable.Seq;
import scala.collection.mutable.Builder;
import scala.collection.mutable.Growable;
import scala.collection.mutable.UnrolledBuffer;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.sys.package.;

@ScalaSignature(
   bytes = "\u0006\u0005m4aa\u0004\t\u0002\u0002A1\u0002\u0002C\u0018\u0001\u0005\u000b\u0007I\u0011\u0002\u0019\t\u0011Q\u0002!\u0011!Q\u0001\nEBQ!\u000e\u0001\u0005\u0002YBq\u0001\u0011\u0001A\u0002\u0013E\u0011\tC\u0004L\u0001\u0001\u0007I\u0011\u0003'\t\rI\u0003\u0001\u0015)\u0003C\u0011\u001d\u0019\u0006\u00011A\u0005\u0012ABq\u0001\u0016\u0001A\u0002\u0013EQ\u000b\u0003\u0004X\u0001\u0001\u0006K!\r\u0005\u00061\u0002!\t\u0001\r\u0005\u00063\u0002!\tA\u0017\u0005\u00067\u0002!\t\u0001\u0018\u0005\u0006S\u0002!\tA\u001b\u0005\u0006e\u0002!\ta\u001d\u0002\u000f\u0005V\u001c7.\u001a;D_6\u0014\u0017N\\3s\u0015\t\t\"#\u0001\u0005qCJ\fG\u000e\\3m\u0015\t\u0019B#\u0001\u0006d_2dWm\u0019;j_:T\u0011!F\u0001\u0006g\u000e\fG.Y\u000b\u0006/\tj\u0013\bP\n\u0004\u0001aa\u0002CA\r\u001b\u001b\u0005!\u0012BA\u000e\u0015\u0005\u0019\te.\u001f*fMB!QD\b\u0011-\u001b\u0005\u0001\u0012BA\u0010\u0011\u0005!\u0019u.\u001c2j]\u0016\u0014\bCA\u0011#\u0019\u0001!aa\t\u0001\t\u0006\u0004)#\u0001B#mK6\u001c\u0001!\u0005\u0002'SA\u0011\u0011dJ\u0005\u0003QQ\u0011qAT8uQ&tw\r\u0005\u0002\u001aU%\u00111\u0006\u0006\u0002\u0004\u0003:L\bCA\u0011.\t\u0019q\u0003\u0001\"b\u0001K\t\u0011Ak\\\u0001\rEV\u001c7.\u001a;ok6\u0014WM]\u000b\u0002cA\u0011\u0011DM\u0005\u0003gQ\u00111!\u00138u\u00035\u0011WoY6fi:,XNY3sA\u00051A(\u001b8jiz\"\"aN \u0011\ru\u0001\u0001\u0005\f\u001d<!\t\t\u0013\bB\u0003;\u0001\t\u0007QE\u0001\u0003Ck\u000e\\\u0007CA\u0011=\t\u0019i\u0004\u0001\"b\u0001}\ta1i\\7cS:,'\u000fV=qKF\u0011ae\u000e\u0005\u0006_\r\u0001\r!M\u0001\bEV\u001c7.\u001a;t+\u0005\u0011\u0005cA\rD\u000b&\u0011A\t\u0006\u0002\u0006\u0003J\u0014\u0018-\u001f\t\u0004\r&CT\"A$\u000b\u0005!\u0013\u0012aB7vi\u0006\u0014G.Z\u0005\u0003\u0015\u001e\u0013a\"\u00168s_2dW\r\u001a\"vM\u001a,'/A\u0006ck\u000e\\W\r^:`I\u0015\fHCA'Q!\tIb*\u0003\u0002P)\t!QK\\5u\u0011\u001d\tV!!AA\u0002\t\u000b1\u0001\u001f\u00132\u0003!\u0011WoY6fiN\u0004\u0013AA:{\u0003\u0019\u0019(p\u0018\u0013fcR\u0011QJ\u0016\u0005\b#\"\t\t\u00111\u00012\u0003\r\u0019(\u0010I\u0001\u0005g&TX-A\u0003dY\u0016\f'\u000fF\u0001N\u00035\u0011WMZ8sK\u000e{WNY5oKV\u0019QL\u00194\u0015\u00055s\u0006\"B0\r\u0001\u0004\u0001\u0017!B8uQ\u0016\u0014\b\u0003B\u000f\u001fC\u0016\u0004\"!\t2\u0005\u000b\rd!\u0019\u00013\u0003\u00039\u000b\"A\n\u0011\u0011\u0005\u00052G!B4\r\u0005\u0004A'!\u0002(foR{\u0017C\u0001\u0017*\u00031\tg\r^3s\u0007>l'-\u001b8f+\rYw.\u001d\u000b\u0003\u001b2DQaX\u0007A\u00025\u0004B!\b\u0010oaB\u0011\u0011e\u001c\u0003\u0006G6\u0011\r\u0001\u001a\t\u0003CE$QaZ\u0007C\u0002!\fqaY8nE&tW-F\u0002uof$\"!\u001e>\u0011\tuqb\u000f\u001f\t\u0003C]$Qa\u0019\bC\u0002\u0011\u0004\"!I=\u0005\u000b\u001dt!\u0019\u00015\t\u000b}s\u0001\u0019A;"
)
public abstract class BucketCombiner implements Combiner {
   private final int bucketnumber;
   private UnrolledBuffer[] buckets;
   private int sz;
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

   private int bucketnumber() {
      return this.bucketnumber;
   }

   public UnrolledBuffer[] buckets() {
      return this.buckets;
   }

   public void buckets_$eq(final UnrolledBuffer[] x$1) {
      this.buckets = x$1;
   }

   public int sz() {
      return this.sz;
   }

   public void sz_$eq(final int x$1) {
      this.sz = x$1;
   }

   public int size() {
      return this.sz();
   }

   public void clear() {
      this.buckets_$eq(new UnrolledBuffer[this.bucketnumber()]);
      this.sz_$eq(0);
   }

   public void beforeCombine(final Combiner other) {
   }

   public void afterCombine(final Combiner other) {
   }

   public Combiner combine(final Combiner other) {
      if (this == other) {
         return this;
      } else if (other instanceof BucketCombiner) {
         this.beforeCombine(other);
         BucketCombiner that = (BucketCombiner)other;

         for(int i = 0; i < this.bucketnumber(); ++i) {
            if (this.buckets()[i] == null) {
               this.buckets()[i] = that.buckets()[i];
               BoxedUnit var10000 = BoxedUnit.UNIT;
            } else if (that.buckets()[i] != null) {
               this.buckets()[i].concat(that.buckets()[i]);
            } else {
               BoxedUnit var6 = BoxedUnit.UNIT;
            }
         }

         this.sz_$eq(this.sz() + that.size());
         this.afterCombine(other);
         return this;
      } else {
         throw .MODULE$.error("Unexpected combiner type.");
      }
   }

   public BucketCombiner(final int bucketnumber) {
      this.bucketnumber = bucketnumber;
      Growable.$init$(this);
      Builder.$init$(this);
      Combiner.$init$(this);
      this.buckets = new UnrolledBuffer[bucketnumber];
      this.sz = 0;
   }
}
