package scala.collection.parallel.immutable;

import java.lang.invoke.SerializedLambda;
import scala.;
import scala.Function1;
import scala.Tuple2;
import scala.collection.Factory;
import scala.collection.generic.CanCombineFrom;
import scala.collection.generic.GenericParCompanion;
import scala.collection.generic.GenericParMapCompanion;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005%haB\r\u001b!\u0003\r\ta\t\u0005\u0006#\u0002!\tA\u0015\u0005\u0006-\u0002!\te\u0016\u0005\u00067\u0002!\t\u0005\u0018\u0005\u0006;\u0002!\tE\u0018\u0005\u0006O\u0002!\t\u0001\u001b\u0005\u0006i\u0002!\t!^\u0004\u0006wjA\t\u0001 \u0004\u00063iA\t! \u0005\b\u0003\u000bAA\u0011AA\u0004\u0011\u0019Y\u0006\u0002\"\u0001\u0002\n!9\u0011q\u0003\u0005\u0005\u0002\u0005e\u0001bBA\u0018\u0011\u0011\r\u0011\u0011\u0007\u0004\u0007\u0003+B\u0001!a\u0016\t\u0015\u0005\u0005UB!A!\u0002\u0013\ty\bC\u0005q\u001b\t\u0005\t\u0015!\u0003\u0002\u0004\"9\u0011QA\u0007\u0005\u0002\u0005\u0015\u0005bBAH\u001b\u0011\u0005\u0013\u0011\u0013\u0005\u000776!\t%!'\t\u000f\u0005mU\u0002\"\u0011\u0002\u001e\"9\u0011\u0011W\u0007\u0005B\u0005M\u0006bBAb\u001b\u0011\u0005\u0013Q\u0019\u0005\u0007O6!\t%!3\t\rQlA\u0011IAl\u0011\u001d\t\u0019/\u0004C!\u0003K\u0014a\u0001U1s\u001b\u0006\u0004(BA\u000e\u001d\u0003%IW.\\;uC\ndWM\u0003\u0002\u001e=\u0005A\u0001/\u0019:bY2,GN\u0003\u0002 A\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\u000b\u0003\u0005\nQa]2bY\u0006\u001c\u0001!F\u0002%cm\u001ab\u0001A\u0013*\u007f\tC\u0005C\u0001\u0014(\u001b\u0005\u0001\u0013B\u0001\u0015!\u0005\u0019\te.\u001f*fMB)!&L\u0018;{5\t1F\u0003\u0002-=\u00059q-\u001a8fe&\u001c\u0017B\u0001\u0018,\u0005U9UM\\3sS\u000e\u0004\u0016M]'baR+W\u000e\u001d7bi\u0016\u0004\"\u0001M\u0019\r\u0001\u0011)!\u0007\u0001b\u0001g\t\t1*\u0005\u00025oA\u0011a%N\u0005\u0003m\u0001\u0012qAT8uQ&tw\r\u0005\u0002'q%\u0011\u0011\b\t\u0002\u0004\u0003:L\bC\u0001\u0019<\t\u0019a\u0004\u0001\"b\u0001g\t\ta\u000b\u0005\u0002?\u00015\t!\u0004\u0005\u0003A\u0003>RT\"\u0001\u000f\n\u0005ea\u0002c\u0001 D\u000b&\u0011AI\u0007\u0002\f!\u0006\u0014\u0018\n^3sC\ndW\r\u0005\u0003'\r>R\u0014BA$!\u0005\u0019!V\u000f\u001d7feA9a(S\u0018;{-c\u0015B\u0001&\u001b\u0005)\u0001\u0016M]'ba2K7.\u001a\t\u0005}\u0001y#\b\u0005\u0003N\u001f>RT\"\u0001(\u000b\u0005mq\u0012B\u0001)O\u0005\ri\u0015\r]\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003M\u0003\"A\n+\n\u0005U\u0003#\u0001B+oSR\fA\"\\1q\u0007>l\u0007/\u00198j_:,\u0012\u0001\u0017\t\u0004Uek\u0014B\u0001.,\u0005Y9UM\\3sS\u000e\u0004\u0016M]'ba\u000e{W\u000e]1oS>t\u0017!B3naRLX#A&\u0002\u0019M$(/\u001b8h!J,g-\u001b=\u0016\u0003}\u0003\"\u0001Y3\u000e\u0003\u0005T!AY2\u0002\t1\fgn\u001a\u0006\u0002I\u0006!!.\u0019<b\u0013\t1\u0017M\u0001\u0004TiJLgnZ\u0001\fo&$\b\u000eR3gCVdG/\u0006\u0002jYR\u0011!n\u001c\t\u0005}\u0001y3\u000e\u0005\u00021Y\u0012)Q.\u0002b\u0001]\n\tQ+\u0005\u0002;o!)\u0001/\u0002a\u0001c\u0006\tA\r\u0005\u0003'e>Z\u0017BA:!\u0005%1UO\\2uS>t\u0017'\u0001\txSRDG)\u001a4bk2$h+\u00197vKV\u0011a/\u001f\u000b\u0003oj\u0004BA\u0010\u00010qB\u0011\u0001'\u001f\u0003\u0006[\u001a\u0011\rA\u001c\u0005\u0006a\u001a\u0001\r\u0001_\u0001\u0007!\u0006\u0014X*\u00199\u0011\u0005yB1C\u0001\u0005\u007f!\u0015Qs0PA\u0002\u0013\r\t\ta\u000b\u0002\u000e!\u0006\u0014X*\u00199GC\u000e$xN]=\u0011\u00055{\u0015A\u0002\u001fj]&$h\bF\u0001}+\u0019\tY!!\u0005\u0002\u0016U\u0011\u0011Q\u0002\t\u0007}\u0001\ty!a\u0005\u0011\u0007A\n\t\u0002B\u00033\u0015\t\u00071\u0007E\u00021\u0003+!Q\u0001\u0010\u0006C\u0002M\n1B\\3x\u0007>l'-\u001b8feV1\u00111DA\u0014\u0003W)\"!!\b\u0011\u000f\u0001\u000by\"a\t\u0002.%\u0019\u0011\u0011\u0005\u000f\u0003\u0011\r{WNY5oKJ\u0004bA\n$\u0002&\u0005%\u0002c\u0001\u0019\u0002(\u0011)!g\u0003b\u0001gA\u0019\u0001'a\u000b\u0005\u000bqZ!\u0019A\u001a\u0011\ry\u0002\u0011QEA\u0015\u00031\u0019\u0017M\u001c\"vS2$gI]8n+)\t\u0019$a\u0010\u0002F\u00055\u0013\u0011K\u000b\u0003\u0003k\u0001\u0012BKA\u001c\u0003w\tI%a\u0015\n\u0007\u0005e2F\u0001\bDC:\u001cu.\u001c2j]\u00164%o\\7\u0011\ry\u0002\u0011QHA\"!\r\u0001\u0014q\b\u0003\u0007\u0003\u0003b!\u0019A\u001a\u0003\u000b\u0019\u0013x.\\&\u0011\u0007A\n)\u0005\u0002\u0004\u0002H1\u0011\ra\r\u0002\u0006\rJ|WN\u0016\t\u0007M\u0019\u000bY%a\u0014\u0011\u0007A\ni\u0005B\u00033\u0019\t\u00071\u0007E\u00021\u0003#\"Q\u0001\u0010\u0007C\u0002M\u0002bA\u0010\u0001\u0002L\u0005=#aC,ji\"$UMZ1vYR,b!!\u0017\u0002z\u0005u4#B\u0007\u0002\\\u0005}\u0004\u0003CA/\u0003g\n9(a\u001f\u000f\t\u0005}\u0013\u0011\u000f\b\u0005\u0003C\nyG\u0004\u0003\u0002d\u00055d\u0002BA3\u0003Wj!!a\u001a\u000b\u0007\u0005%$%\u0001\u0004=e>|GOP\u0005\u0002C%\u0011q\u0004I\u0005\u0003;yI!a\u001f\u000f\n\t\u0005U\u0013Q\u000f\u0006\u0003wr\u00012\u0001MA=\t\u0015\u0011TB1\u00014!\r\u0001\u0014Q\u0010\u0003\u0007y5!)\u0019A\u001a\u0011\ry\u0002\u0011qOA>\u0003))h\u000eZ3sYfLgn\u001a\t\u0007MI\f9(a\u001f\u0015\r\u0005\u001d\u00151RAG!\u001d\tI)DA<\u0003wj\u0011\u0001\u0003\u0005\b\u0003\u0003\u0003\u0002\u0019AA@\u0011\u0019\u0001\b\u00031\u0001\u0002\u0004\u0006I1N\\8x]NK'0Z\u000b\u0003\u0003'\u00032AJAK\u0013\r\t9\n\t\u0002\u0004\u0013:$XCAAD\u0003\u001d)\b\u000fZ1uK\u0012,B!a(\u0002&R1\u0011\u0011UAU\u0003[\u0003r!!#\u000e\u0003o\n\u0019\u000bE\u00021\u0003K#a!\\\nC\u0002\u0005\u001d\u0016cAA>o!9\u00111V\nA\u0002\u0005]\u0014aA6fs\"9\u0011qV\nA\u0002\u0005\r\u0016!\u0002<bYV,\u0017!\u0002\u0013qYV\u001cX\u0003BA[\u0003w#B!a.\u0002>B9\u0011\u0011R\u0007\u0002x\u0005e\u0006c\u0001\u0019\u0002<\u00121Q\u000e\u0006b\u0001\u0003OCq!a0\u0015\u0001\u0004\t\t-\u0001\u0002lmB1aERA<\u0003s\u000ba\u0001J7j]V\u001cH\u0003BAD\u0003\u000fDq!a+\u0016\u0001\u0004\t9(\u0006\u0003\u0002L\u0006EG\u0003BAg\u0003'\u0004bA\u0010\u0001\u0002x\u0005=\u0007c\u0001\u0019\u0002R\u00121QN\u0006b\u0001\u0003OCa\u0001\u001d\fA\u0002\u0005U\u0007C\u0002\u0014s\u0003o\ny-\u0006\u0003\u0002Z\u0006}G\u0003BAn\u0003C\u0004bA\u0010\u0001\u0002x\u0005u\u0007c\u0001\u0019\u0002`\u00121Qn\u0006b\u0001\u0003OCa\u0001]\fA\u0002\u0005u\u0017aA:fcV\u0011\u0011q\u001d\t\u0007\u001b>\u000b9(a\u001f"
)
public interface ParMap extends scala.collection.parallel.ParMap, ParIterable, ParMapLike {
   static CanCombineFrom canBuildFrom() {
      return ParMap$.MODULE$.canBuildFrom();
   }

   static Factory toFactory() {
      return ParMap$.MODULE$.toFactory();
   }

   default GenericParMapCompanion mapCompanion() {
      return ParMap$.MODULE$;
   }

   // $FF: synthetic method
   static ParMap empty$(final ParMap $this) {
      return $this.empty();
   }

   default ParMap empty() {
      return new ParHashMap();
   }

   default String stringPrefix() {
      return "ParMap";
   }

   // $FF: synthetic method
   static ParMap withDefault$(final ParMap $this, final Function1 d) {
      return $this.withDefault(d);
   }

   default ParMap withDefault(final Function1 d) {
      return new WithDefault(this, d);
   }

   // $FF: synthetic method
   static ParMap withDefaultValue$(final ParMap $this, final Object d) {
      return $this.withDefaultValue(d);
   }

   default ParMap withDefaultValue(final Object d) {
      return new WithDefault(this, (x) -> d);
   }

   static void $init$(final ParMap $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class WithDefault extends scala.collection.parallel.ParMap.WithDefault implements ParMap {
      private final ParMap underlying;
      private final Function1 d;

      public GenericParMapCompanion mapCompanion() {
         return ParMap.super.mapCompanion();
      }

      public String stringPrefix() {
         return ParMap.super.stringPrefix();
      }

      public ParMap toMap(final .less.colon.less ev) {
         return ParMapLike.toMap$(this, ev);
      }

      public GenericParCompanion companion() {
         return ParIterable.companion$(this);
      }

      public ParIterable toIterable() {
         return ParIterable.toIterable$(this);
      }

      public ParSeq toSeq() {
         return ParIterable.toSeq$(this);
      }

      public int knownSize() {
         return this.underlying.knownSize();
      }

      public WithDefault empty() {
         return new WithDefault(this.underlying.empty(), this.d);
      }

      public WithDefault updated(final Object key, final Object value) {
         return new WithDefault(this.underlying.updated(key, value), this.d);
      }

      public WithDefault $plus(final Tuple2 kv) {
         return this.updated(kv._1(), kv._2());
      }

      public WithDefault $minus(final Object key) {
         return new WithDefault(this.underlying.$minus(key), this.d);
      }

      public ParMap withDefault(final Function1 d) {
         return new WithDefault(this.underlying, d);
      }

      public ParMap withDefaultValue(final Object d) {
         return new WithDefault(this.underlying, (x) -> d);
      }

      public scala.collection.immutable.Map seq() {
         return ((scala.collection.immutable.Map)this.underlying.seq()).withDefault(this.d);
      }

      public WithDefault(final ParMap underlying, final Function1 d) {
         super(underlying, d);
         this.underlying = underlying;
         this.d = d;
         ParIterable.$init$(this);
         ParMapLike.$init$(this);
         ParMap.$init$(this);
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
