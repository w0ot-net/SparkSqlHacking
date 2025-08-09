package scala.collection.parallel.immutable;

import java.lang.invoke.SerializedLambda;
import scala.Option;
import scala.collection.Hashing.;
import scala.collection.immutable.List;
import scala.collection.immutable.OldHashSet;
import scala.collection.immutable.OldHashSet$;
import scala.collection.mutable.UnrolledBuffer;
import scala.collection.parallel.BucketCombiner;
import scala.collection.parallel.Task;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005-cAB\u000e\u001d\u0003\u0003aB\u0005C\u0003=\u0001\u0011\u0005Q\bC\u0004?\u0001\t\u0007I\u0011A \t\r\u0015\u0003\u0001\u0015!\u0003A\u0011\u00151\u0005\u0001\"\u0001H\u0011\u0015Y\u0005\u0001\"\u0001M\r\u0011i\u0005\u0001\u0001(\t\u0011e3!\u0011!Q\u0001\niC\u0001b\u001c\u0004\u0003\u0002\u0003\u0006I\u0001\u001d\u0005\tc\u001a\u0011\t\u0011)A\u0005e\"AQO\u0002B\u0001B\u0003%!\u000fC\u0003=\r\u0011\u0005a\u000fC\u0004L\r\u0001\u0007I\u0011A>\t\u000fq4\u0001\u0019!C\u0001{\"9\u0011\u0011\u0001\u0004!B\u0013)\u0006bBA\u0002\r\u0011\u0005\u0011Q\u0001\u0005\b\u0003#1A\u0011BA\n\u0011\u001d\tIB\u0002C\u0001\u00037Aq!a\t\u0007\t\u0003\t)cB\u0004\u0002.qA\t!a\f\u0007\rma\u0002\u0012AA\u0019\u0011\u0019aD\u0003\"\u0001\u00024!9\u0011Q\u0007\u000b\u0005\u0002\u0005]\u0002BCA!)\t\u0007I\u0011\u0001\u000f\u0002D!9\u0011Q\t\u000b!\u0002\u0013\u0011\bBCA$)\t\u0007I\u0011\u0001\u000f\u0002D!9\u0011\u0011\n\u000b!\u0002\u0013\u0011(a\u0004%bg\"\u001cV\r^\"p[\nLg.\u001a:\u000b\u0005uq\u0012!C5n[V$\u0018M\u00197f\u0015\ty\u0002%\u0001\u0005qCJ\fG\u000e\\3m\u0015\t\t#%\u0001\u0006d_2dWm\u0019;j_:T\u0011aI\u0001\u0006g\u000e\fG.Y\u000b\u0003K1\u001a\"\u0001\u0001\u0014\u0011\r\u001dB#f\u000e\u001b<\u001b\u0005q\u0012BA\u0015\u001f\u00059\u0011UoY6fi\u000e{WNY5oKJ\u0004\"a\u000b\u0017\r\u0001\u0011)Q\u0006\u0001b\u0001_\t\tAk\u0001\u0001\u0012\u0005A\"\u0004CA\u00193\u001b\u0005\u0011\u0013BA\u001a#\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"!M\u001b\n\u0005Y\u0012#aA!osB\u0019\u0001(\u000f\u0016\u000e\u0003qI!A\u000f\u000f\u0003\u0015A\u000b'\u000fS1tQN+G\u000fE\u00029\u0001)\na\u0001P5oSRtD#A\u001e\u0002\u0013\u0015l\u0007\u000f^=Ue&,W#\u0001!\u0011\u0007\u0005\u001b%&D\u0001C\u0015\ti\u0002%\u0003\u0002E\u0005\nQq\n\u001c3ICND7+\u001a;\u0002\u0015\u0015l\u0007\u000f^=Ue&,\u0007%\u0001\u0004bI\u0012|e.\u001a\u000b\u0003\u0011&k\u0011\u0001\u0001\u0005\u0006\u0015\u0012\u0001\rAK\u0001\u0005K2,W.\u0001\u0004sKN,H\u000e\u001e\u000b\u0002o\tQ1I]3bi\u0016$&/[3\u0014\u0007\u0019y%\u000b\u0005\u00022!&\u0011\u0011K\t\u0002\u0007\u0003:L(+\u001a4\u0011\t\u001d\u001aV\u000bW\u0005\u0003)z\u0011A\u0001V1tWB\u0011\u0011GV\u0005\u0003/\n\u0012A!\u00168jiB\u0011\u0001JB\u0001\u0006EV\u001c7n\u001d\t\u0004cmk\u0016B\u0001/#\u0005\u0015\t%O]1z!\rqF\u000e\u000e\b\u0003?&t!\u0001Y4\u000f\u0005\u00054gB\u00012f\u001b\u0005\u0019'B\u00013/\u0003\u0019a$o\\8u}%\t1%\u0003\u0002\"E%\u0011\u0001\u000eI\u0001\b[V$\u0018M\u00197f\u0013\tQ7.\u0001\bV]J|G\u000e\\3e\u0005V4g-\u001a:\u000b\u0005!\u0004\u0013BA7o\u0005!)fN]8mY\u0016$'B\u00016l\u0003\u0011\u0011xn\u001c;\u0011\u0007EZ\u0006)\u0001\u0004pM\u001a\u001cX\r\u001e\t\u0003cML!\u0001\u001e\u0012\u0003\u0007%sG/A\u0004i_^l\u0017M\\=\u0015\u000ba;\b0\u001f>\t\u000be[\u0001\u0019\u0001.\t\u000b=\\\u0001\u0019\u00019\t\u000bE\\\u0001\u0019\u0001:\t\u000bU\\\u0001\u0019\u0001:\u0016\u0003U\u000b!B]3tk2$x\fJ3r)\t)f\u0010C\u0004\u0000\u001b\u0005\u0005\t\u0019A+\u0002\u0007a$\u0013'A\u0004sKN,H\u000e\u001e\u0011\u0002\t1,\u0017M\u001a\u000b\u0004+\u0006\u001d\u0001bBA\u0005\u001f\u0001\u0007\u00111B\u0001\u0005aJ,g\u000f\u0005\u00032\u0003\u001b)\u0016bAA\bE\t1q\n\u001d;j_:\f!b\u0019:fCR,GK]5f)\r\u0001\u0015Q\u0003\u0005\u0007\u0003/\u0001\u0002\u0019A/\u0002\u000b\u0015dW-\\:\u0002\u000bM\u0004H.\u001b;\u0016\u0005\u0005u\u0001\u0003B!\u0002 aK1!!\tC\u0005\u0011a\u0015n\u001d;\u0002%MDw.\u001e7e'Bd\u0017\u000e\u001e$veRDWM]\u000b\u0003\u0003O\u00012!MA\u0015\u0013\r\tYC\t\u0002\b\u0005>|G.Z1o\u0003=A\u0015m\u001d5TKR\u001cu.\u001c2j]\u0016\u0014\bC\u0001\u001d\u0015'\t!r\n\u0006\u0002\u00020\u0005)\u0011\r\u001d9msV!\u0011\u0011HA +\t\tY\u0004\u0005\u00039\u0001\u0005u\u0002cA\u0016\u0002@\u0011)QF\u0006b\u0001_\u0005A!o\\8uE&$8/F\u0001s\u0003%\u0011xn\u001c;cSR\u001c\b%\u0001\u0005s_>$8/\u001b>f\u0003%\u0011xn\u001c;tSj,\u0007\u0005"
)
public abstract class HashSetCombiner extends BucketCombiner {
   private final OldHashSet emptyTrie;

   public static HashSetCombiner apply() {
      return HashSetCombiner$.MODULE$.apply();
   }

   public OldHashSet emptyTrie() {
      return this.emptyTrie;
   }

   public HashSetCombiner addOne(final Object elem) {
      this.sz_$eq(this.sz() + 1);
      int hc = .MODULE$.computeHash(elem);
      int pos = hc & 31;
      if (this.buckets()[pos] == null) {
         this.buckets()[pos] = new UnrolledBuffer(scala.reflect.ClassTag..MODULE$.Any());
      }

      this.buckets()[pos].$plus$eq(elem);
      return this;
   }

   public ParHashSet result() {
      UnrolledBuffer.Unrolled[] bucks = (UnrolledBuffer.Unrolled[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.filter$extension(scala.Predef..MODULE$.refArrayOps((Object[])this.buckets()), (x$3) -> BoxesRunTime.boxToBoolean($anonfun$result$1(x$3)))), (x$4) -> x$4.headPtr(), scala.reflect.ClassTag..MODULE$.apply(UnrolledBuffer.Unrolled.class));
      OldHashSet[] root = new OldHashSet[bucks.length];
      this.combinerTaskSupport().executeAndWaitResult(new CreateTrie(bucks, root, 0, bucks.length));
      int bitmap = 0;

      for(int i = 0; i < HashSetCombiner$.MODULE$.rootsize(); ++i) {
         if (this.buckets()[i] != null) {
            bitmap |= 1 << i;
         }
      }

      int sz = BoxesRunTime.unboxToInt(scala.collection.ArrayOps..MODULE$.foldLeft$extension(scala.Predef..MODULE$.refArrayOps((Object[])root), BoxesRunTime.boxToInteger(0), (x$5, x$6) -> BoxesRunTime.boxToInteger($anonfun$result$3(BoxesRunTime.unboxToInt(x$5), x$6))));
      if (sz == 0) {
         return new ParHashSet();
      } else if (sz == 1) {
         return new ParHashSet(root[0]);
      } else {
         OldHashSet.HashTrieSet trie = new OldHashSet.HashTrieSet(bitmap, root, sz);
         return new ParHashSet(trie);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$result$1(final UnrolledBuffer x$3) {
      return x$3 != null;
   }

   // $FF: synthetic method
   public static final int $anonfun$result$3(final int x$5, final OldHashSet x$6) {
      return x$5 + x$6.size();
   }

   public HashSetCombiner() {
      super(HashSetCombiner$.MODULE$.rootsize());
      this.emptyTrie = OldHashSet$.MODULE$.empty();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public class CreateTrie implements Task {
      private final UnrolledBuffer.Unrolled[] bucks;
      private final OldHashSet[] root;
      private final int offset;
      private final int howmany;
      private BoxedUnit result;
      private volatile Throwable throwable;
      // $FF: synthetic field
      public final HashSetCombiner $outer;

      public Object repr() {
         return Task.repr$(this);
      }

      public void merge(final Object that) {
         Task.merge$(this, that);
      }

      public void forwardThrowable() {
         Task.forwardThrowable$(this);
      }

      public void tryLeaf(final Option lastres) {
         Task.tryLeaf$(this, lastres);
      }

      public void tryMerge(final Object t) {
         Task.tryMerge$(this, t);
      }

      public void mergeThrowables(final Task that) {
         Task.mergeThrowables$(this, that);
      }

      public void signalAbort() {
         Task.signalAbort$(this);
      }

      public Throwable throwable() {
         return this.throwable;
      }

      public void throwable_$eq(final Throwable x$1) {
         this.throwable = x$1;
      }

      public void result() {
         BoxedUnit var10000 = this.result;
      }

      public void result_$eq(final BoxedUnit x$1) {
         this.result = x$1;
      }

      public void leaf(final Option prev) {
         int i = this.offset;

         for(int until = this.offset + this.howmany; i < until; ++i) {
            this.root[i] = this.createTrie(this.bucks[i]);
         }

      }

      private OldHashSet createTrie(final UnrolledBuffer.Unrolled elems) {
         OldHashSet trie = OldHashSet$.MODULE$.empty();
         UnrolledBuffer.Unrolled unrolled = elems;

         for(int i = 0; unrolled != null; unrolled = unrolled.next()) {
            Object[] chunkarr = unrolled.array();

            for(int chunksz = unrolled.size(); i < chunksz; ++i) {
               Object v = chunkarr[i];
               int hc = .MODULE$.computeHash(v);
               trie = trie.updated0(v, hc, HashSetCombiner$.MODULE$.rootbits());
            }

            i = 0;
         }

         return trie;
      }

      public List split() {
         int fp = this.howmany / 2;
         return new scala.collection.immutable..colon.colon(this.scala$collection$parallel$immutable$HashSetCombiner$CreateTrie$$$outer().new CreateTrie(this.bucks, this.root, this.offset, fp), new scala.collection.immutable..colon.colon(this.scala$collection$parallel$immutable$HashSetCombiner$CreateTrie$$$outer().new CreateTrie(this.bucks, this.root, this.offset + fp, this.howmany - fp), scala.collection.immutable.Nil..MODULE$));
      }

      public boolean shouldSplitFurther() {
         return this.howmany > scala.collection.parallel.package$.MODULE$.thresholdFromSize(this.root.length, this.scala$collection$parallel$immutable$HashSetCombiner$CreateTrie$$$outer().combinerTaskSupport().parallelismLevel());
      }

      // $FF: synthetic method
      public HashSetCombiner scala$collection$parallel$immutable$HashSetCombiner$CreateTrie$$$outer() {
         return this.$outer;
      }

      public CreateTrie(final UnrolledBuffer.Unrolled[] bucks, final OldHashSet[] root, final int offset, final int howmany) {
         this.bucks = bucks;
         this.root = root;
         this.offset = offset;
         this.howmany = howmany;
         if (HashSetCombiner.this == null) {
            throw null;
         } else {
            this.$outer = HashSetCombiner.this;
            super();
            Task.$init$(this);
            this.result = BoxedUnit.UNIT;
         }
      }
   }
}
