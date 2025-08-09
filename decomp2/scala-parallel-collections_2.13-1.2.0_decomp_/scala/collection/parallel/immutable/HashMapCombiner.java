package scala.collection.parallel.immutable;

import java.lang.invoke.SerializedLambda;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.collection.Hashing.;
import scala.collection.immutable.List;
import scala.collection.immutable.ListMap;
import scala.collection.immutable.OldHashMap;
import scala.collection.immutable.OldHashMap$;
import scala.collection.mutable.Builder;
import scala.collection.mutable.UnrolledBuffer;
import scala.collection.parallel.BucketCombiner;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.Task;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ehA\u0002\u0017.\u0003\u0003yS\u0007C\u0003T\u0001\u0011\u0005A\u000bC\u0004V\u0001\t\u0007I\u0011\u0001,\t\rq\u0003\u0001\u0015!\u0003X\u0011\u0015i\u0006\u0001\"\u0001_\u0011\u0015\u0011\u0007\u0001\"\u0001d\u0011\u0015!\u0007\u0001\"\u0001f\u0011\u0015\u0019\b\u0001\"\u0011u\r\u0011i\b\u0001\u0001@\t\u0015\u0005M\u0001B!A!\u0002\u0013\t)\u0002\u0003\u0006\u0002@!\u0011\t\u0011)A\u0005\u0003\u0003B!\"a\u0011\t\u0005\u0003\u0005\u000b\u0011BA#\u0011)\tY\u0005\u0003B\u0001B\u0003%\u0011Q\t\u0005\u0007'\"!\t!!\u0014\t\u0011\tD\u0001\u0019!C\u0001\u0003/B\u0011\"!\u0017\t\u0001\u0004%\t!a\u0017\t\u0011\u0005\u0005\u0004\u0002)Q\u0005\u0003\u0017Aq!a\u001b\t\t\u0003\ti\u0007C\u0004\u0002z!!I!a\u001f\t\u000f\u0005\u0005\u0005\u0002\"\u0001\u0002\u0004\"9\u00111\u0012\u0005\u0005\u0002\u00055eABAK\u0001\u0001\t9\nC\u0005m+\t\u0005\t\u0015!\u0003\u0002$\"Q\u00111C\u000b\u0003\u0002\u0003\u0006I!!\u0006\t\u0015\u0005}RC!A!\u0002\u0013\t9\u000b\u0003\u0006\u0002DU\u0011\t\u0011)A\u0005\u0003\u000bB!\"a\u0013\u0016\u0005\u0003\u0005\u000b\u0011BA#\u0011\u0019\u0019V\u0003\"\u0001\u0002,\"A!-\u0006a\u0001\n\u0003\t9\u0006C\u0005\u0002ZU\u0001\r\u0011\"\u0001\u00028\"A\u0011\u0011M\u000b!B\u0013\tY\u0001C\u0004\u0002lU!\t!!0\t\u000f\u0005\u0005W\u0003\"\u0003\u0002D\"9\u0011\u0011Z\u000b\u0005\n\u0005-\u0007bBAA+\u0011\u0005\u00111\u001b\u0005\b\u0003\u0017+B\u0011AAG\u000f!\t9.\fE\u0001_\u0005ega\u0002\u0017.\u0011\u0003y\u00131\u001c\u0005\u0007'\u0016\"\t!!8\t\u000f\u0005}W\u0005\"\u0001\u0002b\"Q\u0011q^\u0013C\u0002\u0013\u0005Q&!=\t\u0011\u0005MX\u0005)A\u0005\u0003\u000bB!\"!>&\u0005\u0004%\t!LAy\u0011!\t90\nQ\u0001\n\u0005\u0015#a\u0004%bg\"l\u0015\r]\"p[\nLg.\u001a:\u000b\u00059z\u0013!C5n[V$\u0018M\u00197f\u0015\t\u0001\u0014'\u0001\u0005qCJ\fG\u000e\\3m\u0015\t\u00114'\u0001\u0006d_2dWm\u0019;j_:T\u0011\u0001N\u0001\u0006g\u000e\fG.Y\u000b\u0004m\u0005c5C\u0001\u00018!\u0019A\u0014h\u000f(<%6\tq&\u0003\u0002;_\tq!)^2lKR\u001cu.\u001c2j]\u0016\u0014\b\u0003\u0002\u001f>\u007f-k\u0011aM\u0005\u0003}M\u0012a\u0001V;qY\u0016\u0014\u0004C\u0001!B\u0019\u0001!QA\u0011\u0001C\u0002\u0011\u0013\u0011aS\u0002\u0001#\t)\u0005\n\u0005\u0002=\r&\u0011qi\r\u0002\b\u001d>$\b.\u001b8h!\ta\u0014*\u0003\u0002Kg\t\u0019\u0011I\\=\u0011\u0005\u0001cE!B'\u0001\u0005\u0004!%!\u0001,\u0011\t=\u0003vhS\u0007\u0002[%\u0011\u0011+\f\u0002\u000b!\u0006\u0014\b*Y:i\u001b\u0006\u0004\b\u0003B(\u0001\u007f-\u000ba\u0001P5oSRtD#\u0001*\u0002\u0013\u0015l\u0007\u000f^=Ue&,W#A,\u0011\taSvhS\u0007\u00023*\u0011a&M\u0005\u00037f\u0013!b\u00147e\u0011\u0006\u001c\b.T1q\u0003))W\u000e\u001d;z)JLW\rI\u0001\u0007C\u0012$wJ\\3\u0015\u0005}\u0003W\"\u0001\u0001\t\u000b\u0005$\u0001\u0019A\u001e\u0002\t\u0015dW-\\\u0001\u0007e\u0016\u001cX\u000f\u001c;\u0015\u00039\u000b!b\u001a:pkB\u0014\u0015pS3z+\t1\u0017\u000e\u0006\u0002hWB!q\nU i!\t\u0001\u0015\u000eB\u0003k\r\t\u0007AI\u0001\u0003SKB\u0014\b\"\u00027\u0007\u0001\u0004i\u0017aA2cMB\u0019AH\u001c9\n\u0005=\u001c$!\u0003$v]\u000e$\u0018n\u001c81!\u0011A\u0014o\u00135\n\u0005I|#\u0001C\"p[\nLg.\u001a:\u0002\u0011Q|7\u000b\u001e:j]\u001e$\u0012!\u001e\t\u0003mnl\u0011a\u001e\u0006\u0003qf\fA\u0001\\1oO*\t!0\u0001\u0003kCZ\f\u0017B\u0001?x\u0005\u0019\u0019FO]5oO\nQ1I]3bi\u0016$&/[3\u0014\t!y\u0018Q\u0001\t\u0004y\u0005\u0005\u0011bAA\u0002g\t1\u0011I\\=SK\u001a\u0004r\u0001OA\u0004\u0003\u0017\t\t\"C\u0002\u0002\n=\u0012A\u0001V1tWB\u0019A(!\u0004\n\u0007\u0005=1G\u0001\u0003V]&$\bCA0\t\u0003\u0015\u0011WoY6t!\u0015a\u0014qCA\u000e\u0013\r\tIb\r\u0002\u0006\u0003J\u0014\u0018-\u001f\t\u0006\u0003;\tId\u000f\b\u0005\u0003?\t\u0019D\u0004\u0003\u0002\"\u0005=b\u0002BA\u0012\u0003[qA!!\n\u0002,5\u0011\u0011q\u0005\u0006\u0004\u0003S\u0019\u0015A\u0002\u001fs_>$h(C\u00015\u0013\t\u00114'C\u0002\u00022E\nq!\\;uC\ndW-\u0003\u0003\u00026\u0005]\u0012AD+oe>dG.\u001a3Ck\u001a4WM\u001d\u0006\u0004\u0003c\t\u0014\u0002BA\u001e\u0003{\u0011\u0001\"\u00168s_2dW\r\u001a\u0006\u0005\u0003k\t9$\u0001\u0003s_>$\b\u0003\u0002\u001f\u0002\u0018]\u000baa\u001c4gg\u0016$\bc\u0001\u001f\u0002H%\u0019\u0011\u0011J\u001a\u0003\u0007%sG/A\u0004i_^l\u0017M\\=\u0015\u0015\u0005E\u0011qJA)\u0003'\n)\u0006C\u0004\u0002\u00145\u0001\r!!\u0006\t\u000f\u0005}R\u00021\u0001\u0002B!9\u00111I\u0007A\u0002\u0005\u0015\u0003bBA&\u001b\u0001\u0007\u0011QI\u000b\u0003\u0003\u0017\t!B]3tk2$x\fJ3r)\u0011\tY!!\u0018\t\u0013\u0005}s\"!AA\u0002\u0005-\u0011a\u0001=%c\u00059!/Z:vYR\u0004\u0003f\u0001\t\u0002fA\u0019A(a\u001a\n\u0007\u0005%4G\u0001\u0005w_2\fG/\u001b7f\u0003\u0011aW-\u00194\u0015\t\u0005-\u0011q\u000e\u0005\b\u0003c\n\u0002\u0019AA:\u0003\u0011\u0001(/\u001a<\u0011\u000bq\n)(a\u0003\n\u0007\u0005]4G\u0001\u0004PaRLwN\\\u0001\u000bGJ,\u0017\r^3Ue&,GcA,\u0002~!9\u0011q\u0010\nA\u0002\u0005m\u0011!B3mK6\u001c\u0018!B:qY&$XCAAC!\u0015A\u0016qQA\t\u0013\r\tI)\u0017\u0002\u0005\u0019&\u001cH/\u0001\ntQ>,H\u000eZ*qY&$h)\u001e:uQ\u0016\u0014XCAAH!\ra\u0014\u0011S\u0005\u0004\u0003'\u001b$a\u0002\"p_2,\u0017M\u001c\u0002\u0012\u0007J,\u0017\r^3He>,\b/\u001a3Ue&,W\u0003BAM\u0003C\u001bB!F@\u0002\u001cB9\u0001(a\u0002\u0002\f\u0005u\u0005\u0003B0\u0016\u0003?\u00032\u0001QAQ\t\u0015QWC1\u0001E!\u0011ad.!*\u0011\u000ba\n8*a(\u0011\u000bq\n9\"!+\u0011\taSvh \u000b\r\u0003;\u000bi+a,\u00022\u0006M\u0016Q\u0017\u0005\u0007Yn\u0001\r!a)\t\u000f\u0005M1\u00041\u0001\u0002\u0016!9\u0011qH\u000eA\u0002\u0005\u001d\u0006bBA\"7\u0001\u0007\u0011Q\t\u0005\b\u0003\u0017Z\u0002\u0019AA#)\u0011\tY!!/\t\u0013\u0005}S$!AA\u0002\u0005-\u0001f\u0001\u0010\u0002fQ!\u00111BA`\u0011\u001d\t\th\ba\u0001\u0003g\n\u0011c\u0019:fCR,wI]8va\u0016$GK]5f)\u0011\t)-a2\u0011\u000baSv(a(\t\u000f\u0005}\u0004\u00051\u0001\u0002\u001c\u0005\tRM^1mk\u0006$XmQ8nE&tWM]:\u0015\t\u0005\u0015\u0017Q\u001a\u0005\b\u0003\u001f\f\u0003\u0019AAi\u0003\u0011!(/[3\u0011\u000baSv(!*\u0016\u0005\u0005U\u0007#\u0002-\u0002\b\u0006u\u0015a\u0004%bg\"l\u0015\r]\"p[\nLg.\u001a:\u0011\u0005=+3CA\u0013\u0000)\t\tI.A\u0003baBd\u00170\u0006\u0004\u0002d\u0006%\u0018Q^\u000b\u0003\u0003K\u0004ba\u0014\u0001\u0002h\u0006-\bc\u0001!\u0002j\u0012)!i\nb\u0001\tB\u0019\u0001)!<\u0005\u000b5;#\u0019\u0001#\u0002\u0011I|w\u000e\u001e2jiN,\"!!\u0012\u0002\u0013I|w\u000e\u001e2jiN\u0004\u0013\u0001\u0003:p_R\u001c\u0018N_3\u0002\u0013I|w\u000e^:ju\u0016\u0004\u0003"
)
public abstract class HashMapCombiner extends BucketCombiner {
   private final OldHashMap emptyTrie;

   public static HashMapCombiner apply() {
      return HashMapCombiner$.MODULE$.apply();
   }

   public OldHashMap emptyTrie() {
      return this.emptyTrie;
   }

   public HashMapCombiner addOne(final Tuple2 elem) {
      this.sz_$eq(this.sz() + 1);
      int hc = .MODULE$.computeHash(elem._1());
      int pos = hc & 31;
      if (this.buckets()[pos] == null) {
         this.buckets()[pos] = new UnrolledBuffer(scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
      }

      this.buckets()[pos].$plus$eq(elem);
      return this;
   }

   public ParHashMap result() {
      UnrolledBuffer.Unrolled[] bucks = (UnrolledBuffer.Unrolled[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.filter$extension(scala.Predef..MODULE$.refArrayOps((Object[])this.buckets()), (x$3) -> BoxesRunTime.boxToBoolean($anonfun$result$1(x$3)))), (x$4) -> x$4.headPtr(), scala.reflect.ClassTag..MODULE$.apply(UnrolledBuffer.Unrolled.class));
      OldHashMap[] root = new OldHashMap[bucks.length];
      this.combinerTaskSupport().executeAndWaitResult(new CreateTrie(bucks, root, 0, bucks.length));
      int bitmap = 0;

      for(int i = 0; i < HashMapCombiner$.MODULE$.rootsize(); ++i) {
         if (this.buckets()[i] != null) {
            bitmap |= 1 << i;
         }
      }

      int sz = BoxesRunTime.unboxToInt(scala.collection.ArrayOps..MODULE$.foldLeft$extension(scala.Predef..MODULE$.refArrayOps((Object[])root), BoxesRunTime.boxToInteger(0), (x$5, x$6) -> BoxesRunTime.boxToInteger($anonfun$result$3(BoxesRunTime.unboxToInt(x$5), x$6))));
      if (sz == 0) {
         return new ParHashMap();
      } else if (sz == 1) {
         return new ParHashMap(root[0]);
      } else {
         OldHashMap.HashTrieMap trie = new OldHashMap.HashTrieMap(bitmap, root, sz);
         return new ParHashMap(trie);
      }
   }

   public ParHashMap groupByKey(final Function0 cbf) {
      UnrolledBuffer.Unrolled[] bucks = (UnrolledBuffer.Unrolled[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.filter$extension(scala.Predef..MODULE$.refArrayOps((Object[])this.buckets()), (x$7) -> BoxesRunTime.boxToBoolean($anonfun$groupByKey$1(x$7)))), (x$8) -> x$8.headPtr(), scala.reflect.ClassTag..MODULE$.apply(UnrolledBuffer.Unrolled.class));
      OldHashMap[] root = new OldHashMap[bucks.length];
      this.combinerTaskSupport().executeAndWaitResult(new CreateGroupedTrie(cbf, bucks, root, 0, bucks.length));
      int bitmap = 0;

      for(int i = 0; i < HashMapCombiner$.MODULE$.rootsize(); ++i) {
         if (this.buckets()[i] != null) {
            bitmap |= 1 << i;
         }
      }

      int sz = BoxesRunTime.unboxToInt(scala.collection.ArrayOps..MODULE$.foldLeft$extension(scala.Predef..MODULE$.refArrayOps((Object[])root), BoxesRunTime.boxToInteger(0), (x$9, x$10) -> BoxesRunTime.boxToInteger($anonfun$groupByKey$3(BoxesRunTime.unboxToInt(x$9), x$10))));
      if (sz == 0) {
         return new ParHashMap();
      } else if (sz == 1) {
         return new ParHashMap(root[0]);
      } else {
         OldHashMap.HashTrieMap trie = new OldHashMap.HashTrieMap(bitmap, root, sz);
         return new ParHashMap(trie);
      }
   }

   public String toString() {
      return (new StringBuilder(22)).append("HashTrieCombiner(sz: ").append(this.size()).append(")").toString();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$result$1(final UnrolledBuffer x$3) {
      return x$3 != null;
   }

   // $FF: synthetic method
   public static final int $anonfun$result$3(final int x$5, final OldHashMap x$6) {
      return x$5 + x$6.size();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$groupByKey$1(final UnrolledBuffer x$7) {
      return x$7 != null;
   }

   // $FF: synthetic method
   public static final int $anonfun$groupByKey$3(final int x$9, final OldHashMap x$10) {
      return x$9 + x$10.size();
   }

   public HashMapCombiner() {
      super(HashMapCombiner$.MODULE$.rootsize());
      this.emptyTrie = OldHashMap$.MODULE$.empty();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public class CreateTrie implements Task {
      private final UnrolledBuffer.Unrolled[] bucks;
      private final OldHashMap[] root;
      private final int offset;
      private final int howmany;
      private volatile BoxedUnit result;
      private volatile Throwable throwable;
      // $FF: synthetic field
      public final HashMapCombiner $outer;

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

         this.result();
         this.result_$eq(BoxedUnit.UNIT);
      }

      private OldHashMap createTrie(final UnrolledBuffer.Unrolled elems) {
         OldHashMap trie = OldHashMap$.MODULE$.empty();
         UnrolledBuffer.Unrolled unrolled = elems;

         for(int i = 0; unrolled != null; unrolled = unrolled.next()) {
            Tuple2[] chunkarr = (Tuple2[])unrolled.array();

            for(int chunksz = unrolled.size(); i < chunksz; ++i) {
               Tuple2 kv = chunkarr[i];
               int hc = .MODULE$.computeHash(kv._1());
               trie = trie.updated0(kv._1(), hc, HashMapCombiner$.MODULE$.rootbits(), kv._2(), kv, (OldHashMap.Merger)null);
            }

            i = 0;
         }

         return trie;
      }

      public List split() {
         int fp = this.howmany / 2;
         return new scala.collection.immutable..colon.colon(this.scala$collection$parallel$immutable$HashMapCombiner$CreateTrie$$$outer().new CreateTrie(this.bucks, this.root, this.offset, fp), new scala.collection.immutable..colon.colon(this.scala$collection$parallel$immutable$HashMapCombiner$CreateTrie$$$outer().new CreateTrie(this.bucks, this.root, this.offset + fp, this.howmany - fp), scala.collection.immutable.Nil..MODULE$));
      }

      public boolean shouldSplitFurther() {
         return this.howmany > scala.collection.parallel.package$.MODULE$.thresholdFromSize(this.root.length, this.scala$collection$parallel$immutable$HashMapCombiner$CreateTrie$$$outer().combinerTaskSupport().parallelismLevel());
      }

      // $FF: synthetic method
      public HashMapCombiner scala$collection$parallel$immutable$HashMapCombiner$CreateTrie$$$outer() {
         return this.$outer;
      }

      public CreateTrie(final UnrolledBuffer.Unrolled[] bucks, final OldHashMap[] root, final int offset, final int howmany) {
         this.bucks = bucks;
         this.root = root;
         this.offset = offset;
         this.howmany = howmany;
         if (HashMapCombiner.this == null) {
            throw null;
         } else {
            this.$outer = HashMapCombiner.this;
            super();
            Task.$init$(this);
            this.result = BoxedUnit.UNIT;
         }
      }
   }

   public class CreateGroupedTrie implements Task {
      private final Function0 cbf;
      private final UnrolledBuffer.Unrolled[] bucks;
      private final OldHashMap[] root;
      private final int offset;
      private final int howmany;
      private volatile BoxedUnit result;
      private volatile Throwable throwable;
      // $FF: synthetic field
      public final HashMapCombiner $outer;

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
            this.root[i] = this.createGroupedTrie(this.bucks[i]);
         }

         this.result();
         this.result_$eq(BoxedUnit.UNIT);
      }

      private OldHashMap createGroupedTrie(final UnrolledBuffer.Unrolled elems) {
         OldHashMap trie = OldHashMap$.MODULE$.empty();
         UnrolledBuffer.Unrolled unrolled = elems;

         for(int i = 0; unrolled != null; unrolled = unrolled.next()) {
            Tuple2[] chunkarr = (Tuple2[])unrolled.array();

            for(int chunksz = unrolled.size(); i < chunksz; ++i) {
               Tuple2 kv = chunkarr[i];
               int hc = .MODULE$.computeHash(kv._1());
               Option var11 = trie.get0(kv._1(), hc, HashMapCombiner$.MODULE$.rootbits());
               Combiner var10000;
               if (var11 instanceof Some) {
                  Some var12 = (Some)var11;
                  Combiner cmb = (Combiner)var12.value();
                  var10000 = cmb;
               } else {
                  if (!scala.None..MODULE$.equals(var11)) {
                     throw new MatchError(var11);
                  }

                  Combiner cmb = (Combiner)this.cbf.apply();
                  trie = trie.updated0(kv._1(), hc, HashMapCombiner$.MODULE$.rootbits(), cmb, (Tuple2)null, (OldHashMap.Merger)null);
                  var10000 = cmb;
               }

               Combiner cmb = var10000;
               cmb.$plus$eq(kv._2());
            }

            i = 0;
         }

         return this.evaluateCombiners(trie);
      }

      private OldHashMap evaluateCombiners(final OldHashMap trie) {
         if (trie instanceof OldHashMap.OldHashMap1) {
            OldHashMap.OldHashMap1 var4 = (OldHashMap.OldHashMap1)trie;
            Object evaledvalue = ((Builder)var4.value()).result();
            return new OldHashMap.OldHashMap1(var4.key(), var4.hash(), evaledvalue, (Tuple2)null);
         } else if (trie instanceof OldHashMap.OldHashMapCollision1) {
            OldHashMap.OldHashMapCollision1 var6 = (OldHashMap.OldHashMapCollision1)trie;
            ListMap evaledkvs = (ListMap)var6.kvs().map((p) -> new Tuple2(p._1(), ((Builder)p._2()).result()));
            return new OldHashMap.OldHashMapCollision1(var6.hash(), evaledkvs);
         } else if (!(trie instanceof OldHashMap.HashTrieMap)) {
            return trie;
         } else {
            OldHashMap.HashTrieMap var8 = (OldHashMap.HashTrieMap)trie;

            for(int i = 0; i < var8.elems().length; ++i) {
               var8.elems()[i] = this.evaluateCombiners(var8.elems()[i]);
            }

            return var8;
         }
      }

      public List split() {
         int fp = this.howmany / 2;
         return new scala.collection.immutable..colon.colon(this.scala$collection$parallel$immutable$HashMapCombiner$CreateGroupedTrie$$$outer().new CreateGroupedTrie(this.cbf, this.bucks, this.root, this.offset, fp), new scala.collection.immutable..colon.colon(this.scala$collection$parallel$immutable$HashMapCombiner$CreateGroupedTrie$$$outer().new CreateGroupedTrie(this.cbf, this.bucks, this.root, this.offset + fp, this.howmany - fp), scala.collection.immutable.Nil..MODULE$));
      }

      public boolean shouldSplitFurther() {
         return this.howmany > scala.collection.parallel.package$.MODULE$.thresholdFromSize(this.root.length, this.scala$collection$parallel$immutable$HashMapCombiner$CreateGroupedTrie$$$outer().combinerTaskSupport().parallelismLevel());
      }

      // $FF: synthetic method
      public HashMapCombiner scala$collection$parallel$immutable$HashMapCombiner$CreateGroupedTrie$$$outer() {
         return this.$outer;
      }

      public CreateGroupedTrie(final Function0 cbf, final UnrolledBuffer.Unrolled[] bucks, final OldHashMap[] root, final int offset, final int howmany) {
         this.cbf = cbf;
         this.bucks = bucks;
         this.root = root;
         this.offset = offset;
         this.howmany = howmany;
         if (HashMapCombiner.this == null) {
            throw null;
         } else {
            this.$outer = HashMapCombiner.this;
            super();
            Task.$init$(this);
            this.result = BoxedUnit.UNIT;
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
