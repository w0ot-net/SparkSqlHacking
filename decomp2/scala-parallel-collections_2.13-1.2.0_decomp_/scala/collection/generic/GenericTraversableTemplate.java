package scala.collection.generic;

import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.MatchError;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.Iterable;
import scala.collection.IterableOnce;
import scala.collection.immutable.IndexedSeq;
import scala.collection.mutable.Builder;
import scala.collection.mutable.Growable;
import scala.collection.parallel.ParIterable;
import scala.package.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.IntRef;
import scala.runtime.Nothing;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005]ea\u0002\b\u0010!\u0003\r\tA\u0006\u0005\u0006\u0007\u0002!\t\u0001\u0012\u0005\u0006\u0011\u00021\t!\u0013\u0005\u0006-\u00021\ta\u0016\u0005\u0006C\u00021\tA\u0019\u0005\u0006G\u00021\t\u0001\u001a\u0005\u0006Q\u00021\t!\u001b\u0005\u0007]\u0002\u0001K\u0011C8\t\u000ba\u0004A\u0011A=\t\u000f\u0005\u0005\u0001\u0001\"\u0003\u0002\u0004!9\u00111\u0002\u0001\u0005\u0002\u00055\u0001bBA\u0018\u0001\u0011\u0005\u0011\u0011\u0007\u0005\b\u0003/\u0002A\u0011AA-\u0011\u001d\tY\u0007\u0001C\u0001\u0003[\u0012!dR3oKJL7\r\u0016:bm\u0016\u00148/\u00192mKR+W\u000e\u001d7bi\u0016T!\u0001E\t\u0002\u000f\u001d,g.\u001a:jG*\u0011!cE\u0001\u000bG>dG.Z2uS>t'\"\u0001\u000b\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001U\u0019qCI\u0017\u0014\u0007\u0001AB\u0004\u0005\u0002\u001a55\t1#\u0003\u0002\u001c'\t1\u0011I\\=SK\u001a\u0004B!\b\u0010!W5\tq\"\u0003\u0002 \u001f\ti\u0001*Y:OK^\u0014U/\u001b7eKJ\u0004\"!\t\u0012\r\u0001\u001111\u0005\u0001CC\u0002\u0011\u0012\u0011!Q\t\u0003K!\u0002\"!\u0007\u0014\n\u0005\u001d\u001a\"a\u0002(pi\"Lgn\u001a\t\u00033%J!AK\n\u0003\u0007\u0005s\u0017P\u000b\u0002-uA\u0019\u0011%\f\u0011\u0005\r9\u0002AQ1\u00010\u0005\t\u00195)\u0006\u00021qE\u0011Q%\r\t\u0004eU:T\"A\u001a\u000b\u0005Q\n\u0012\u0001\u00039be\u0006dG.\u001a7\n\u0005Y\u001a$a\u0003)be&#XM]1cY\u0016\u0004\"!\t\u001d\u0005\u000bej#\u0019\u0001\u0013\u0003\u0003a[\u0013a\u000f\t\u0003y\u0005k\u0011!\u0010\u0006\u0003}}\n\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0005\u0001\u001b\u0012AC1o]>$\u0018\r^5p]&\u0011!)\u0010\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017A\u0002\u0013j]&$H\u0005F\u0001F!\tIb)\u0003\u0002H'\t!QK\\5u\u0003\r\u0019X-]\u000b\u0002\u0015B\u00191j\u0015\u0011\u000f\u00051\u000bfBA'Q\u001b\u0005q%BA(\u0016\u0003\u0019a$o\\8u}%\tA#\u0003\u0002S'\u00059\u0001/Y2lC\u001e,\u0017B\u0001+V\u0005!IE/\u001a:bE2,'B\u0001*\u0014\u0003\u001d1wN]3bG\",\"\u0001W0\u0015\u0005\u0015K\u0006\"\u0002.\u0004\u0001\u0004Y\u0016!\u00014\u0011\tea\u0006EX\u0005\u0003;N\u0011\u0011BR;oGRLwN\\\u0019\u0011\u0005\u0005zF!\u00021\u0004\u0005\u0004!#!A+\u0002\t!,\u0017\rZ\u000b\u0002A\u00059\u0011n]#naRLX#A3\u0011\u0005e1\u0017BA4\u0014\u0005\u001d\u0011un\u001c7fC:\f\u0011bY8na\u0006t\u0017n\u001c8\u0016\u0003)\u00042!H6n\u0013\tawBA\nHK:,'/[2QCJ\u001cu.\u001c9b]&|g\u000e\u0005\u0002\"[\u0005Qa.Z<Ck&dG-\u001a:\u0016\u0003A\u0004B!\u001d;wo6\t!O\u0003\u0002t#\u00059Q.\u001e;bE2,\u0017BA;s\u0005\u001d\u0011U/\u001b7eKJT#\u0001\t\u001e\u0011\u0007\u0005jc/\u0001\bhK:,'/[2Ck&dG-\u001a:\u0016\u0005ilX#A>\u0011\tE$Hp \t\u0003Cu$QA \u0005C\u0002\u0011\u0012\u0011A\u0011\t\u0004C5b\u0018AC:fcV,g\u000e^5bYV\u0011\u0011Q\u0001\t\u0005\u0017\u0006\u001d\u0001%C\u0002\u0002\nU\u0013A\"\u0013;fe\u0006\u0014G.Z(oG\u0016\fQ!\u001e8{SB,b!a\u0004\u0002\u001c\u0005\rB\u0003BA\t\u0003O\u0001r!GA\n\u0003/\ty\"C\u0002\u0002\u0016M\u0011a\u0001V;qY\u0016\u0014\u0004\u0003B\u0011.\u00033\u00012!IA\u000e\t\u0019\tiB\u0003b\u0001I\t\u0011\u0011)\r\t\u0005C5\n\t\u0003E\u0002\"\u0003G!a!!\n\u000b\u0005\u0004!#AA!3\u0011\u001d\tIC\u0003a\u0002\u0003W\ta!Y:QC&\u0014\b#B\r]A\u00055\u0002cB\r\u0002\u0014\u0005e\u0011\u0011E\u0001\u0007k:T\u0018\u000e]\u001a\u0016\u0011\u0005M\u0012qHA#\u0003\u0017\"B!!\u000e\u0002PAI\u0011$a\u000e\u0002<\u0005\u0005\u0013qI\u0005\u0004\u0003s\u0019\"A\u0002+va2,7\u0007\u0005\u0003\"[\u0005u\u0002cA\u0011\u0002@\u00111\u0011QD\u0006C\u0002\u0011\u0002B!I\u0017\u0002DA\u0019\u0011%!\u0012\u0005\r\u0005\u00152B1\u0001%!\u0011\tS&!\u0013\u0011\u0007\u0005\nY\u0005\u0002\u0004\u0002N-\u0011\r\u0001\n\u0002\u0003\u0003NBq!!\u0015\f\u0001\b\t\u0019&\u0001\u0005bgR\u0013\u0018\u000e\u001d7f!\u0015IB\fIA+!%I\u0012qGA\u001f\u0003\u0007\nI%A\u0004gY\u0006$H/\u001a8\u0016\t\u0005m\u0013\u0011\r\u000b\u0005\u0003;\n\u0019\u0007\u0005\u0003\"[\u0005}\u0003cA\u0011\u0002b\u0011)a\u0010\u0004b\u0001I!9\u0011Q\r\u0007A\u0004\u0005\u001d\u0014!D1t)J\fg/\u001a:tC\ndW\rE\u0003\u001a9\u0002\nI\u0007E\u0003L\u0003\u000f\ty&A\u0005ue\u0006t7\u000f]8tKV!\u0011qNA=)\u0011\t\t(a\u001f\u0011\t\u0005j\u00131\u000f\u0016\u0004\u0003kR\u0004\u0003B\u0011.\u0003o\u00022!IA=\t\u0015qXB1\u0001%\u0011\u001d\t)'\u0004a\u0002\u0003{\u0002R!\u0007/!\u0003\u007f\u0002RaSA\u0004\u0003oB3\"DAB\u0003\u0017\u000bi)!%\u0002\u0014B!\u0011QQAD\u001b\u0005y\u0014bAAE\u007f\tIQ.[4sCRLwN\\\u0001\b[\u0016\u001c8/Y4fC\t\ty)\u0001-aiJ\fgn\u001d9pg\u0016\u0004\u0007\u0005\u001e5s_^\u001c\b%\u00198!A&cG.Z4bY\u0006\u0013x-^7f]R,\u0005pY3qi&|g\u000e\u0019\u0011jM\u0002\u001aw\u000e\u001c7fGRLwN\\:!CJ,\u0007E\\8uAUt\u0017NZ8s[2L\be]5{K\u0012t\u0013!C2iC:<W\rZ%oC\t\t)*A\u00033]er\u0003\u0007"
)
public interface GenericTraversableTemplate extends HasNewBuilder {
   Iterable seq();

   void foreach(final Function1 f);

   Object head();

   boolean isEmpty();

   GenericParCompanion companion();

   // $FF: synthetic method
   static Builder newBuilder$(final GenericTraversableTemplate $this) {
      return $this.newBuilder();
   }

   default Builder newBuilder() {
      return this.companion().newBuilder();
   }

   // $FF: synthetic method
   static Builder genericBuilder$(final GenericTraversableTemplate $this) {
      return $this.genericBuilder();
   }

   default Builder genericBuilder() {
      return this.companion().newBuilder();
   }

   private IterableOnce sequential() {
      return this.seq();
   }

   // $FF: synthetic method
   static Tuple2 unzip$(final GenericTraversableTemplate $this, final Function1 asPair) {
      return $this.unzip(asPair);
   }

   default Tuple2 unzip(final Function1 asPair) {
      Builder b1 = this.genericBuilder();
      Builder b2 = this.genericBuilder();
      this.sequential().iterator().foreach((xy) -> {
         Tuple2 var6 = (Tuple2)asPair.apply(xy);
         if (var6 != null) {
            Object x = var6._1();
            Object y = var6._2();
            Tuple2 var5 = new Tuple2(x, y);
            Object xx = var5._1();
            Object yx = var5._2();
            b1.$plus$eq(xx);
            return (Builder)b2.$plus$eq(yx);
         } else {
            throw new MatchError(var6);
         }
      });
      return new Tuple2(b1.result(), b2.result());
   }

   // $FF: synthetic method
   static Tuple3 unzip3$(final GenericTraversableTemplate $this, final Function1 asTriple) {
      return $this.unzip3(asTriple);
   }

   default Tuple3 unzip3(final Function1 asTriple) {
      Builder b1 = this.genericBuilder();
      Builder b2 = this.genericBuilder();
      Builder b3 = this.genericBuilder();
      this.sequential().iterator().foreach((xyz) -> {
         Tuple3 var7 = (Tuple3)asTriple.apply(xyz);
         if (var7 != null) {
            Object x = var7._1();
            Object y = var7._2();
            Object z = var7._3();
            Tuple3 var6 = new Tuple3(x, y, z);
            Object x = var6._1();
            Object y = var6._2();
            Object z = var6._3();
            b1.$plus$eq(x);
            b2.$plus$eq(y);
            return (Builder)b3.$plus$eq(z);
         } else {
            throw new MatchError(var7);
         }
      });
      return new Tuple3(b1.result(), b2.result(), b3.result());
   }

   // $FF: synthetic method
   static ParIterable flatten$(final GenericTraversableTemplate $this, final Function1 asTraversable) {
      return $this.flatten(asTraversable);
   }

   default ParIterable flatten(final Function1 asTraversable) {
      Builder b = this.genericBuilder();
      this.sequential().iterator().foreach((xs) -> (Builder)b.$plus$plus$eq((IterableOnce)asTraversable.apply(xs)));
      return (ParIterable)b.result();
   }

   // $FF: synthetic method
   static ParIterable transpose$(final GenericTraversableTemplate $this, final Function1 asTraversable) {
      return $this.transpose(asTraversable);
   }

   default ParIterable transpose(final Function1 asTraversable) {
      if (this.isEmpty()) {
         return (ParIterable)this.genericBuilder().result();
      } else {
         int headSize = ((IterableOnce)asTraversable.apply(this.head())).iterator().size();
         IndexedSeq bs = (IndexedSeq).MODULE$.IndexedSeq().fill(headSize, () -> this.genericBuilder());
         this.sequential().iterator().foreach((xs) -> {
            $anonfun$transpose$2(asTraversable, headSize, bs, xs);
            return BoxedUnit.UNIT;
         });
         Builder bb = this.genericBuilder();
         bs.foreach((b) -> (Builder)bb.$plus$eq(b.result()));
         return (ParIterable)bb.result();
      }
   }

   private static Nothing fail$1() {
      throw new IllegalArgumentException("transpose requires all collections have the same size");
   }

   // $FF: synthetic method
   static void $anonfun$transpose$3(final IntRef i$1, final int headSize$1, final IndexedSeq bs$1, final Object x) {
      if (i$1.elem >= headSize$1) {
         throw fail$1();
      } else {
         ((Growable)bs$1.apply(i$1.elem)).$plus$eq(x);
         ++i$1.elem;
      }
   }

   // $FF: synthetic method
   static void $anonfun$transpose$2(final Function1 asTraversable$2, final int headSize$1, final IndexedSeq bs$1, final Object xs) {
      IntRef i = IntRef.create(0);
      ((IterableOnce)asTraversable$2.apply(xs)).iterator().foreach((x) -> {
         $anonfun$transpose$3(i, headSize$1, bs$1, x);
         return BoxedUnit.UNIT;
      });
      if (i.elem != headSize$1) {
         throw fail$1();
      }
   }

   static void $init$(final GenericTraversableTemplate $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
