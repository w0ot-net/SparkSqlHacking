package scala.collection.parallel.mutable;

import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Option;
import scala.Tuple2;
import scala.collection.Factory;
import scala.collection.IterableOnce;
import scala.collection.generic.CanCombineFrom;
import scala.collection.generic.GenericParCompanion;
import scala.collection.generic.GenericParMapCompanion;
import scala.collection.immutable.Seq;
import scala.collection.mutable.Cloneable;
import scala.collection.mutable.Growable;
import scala.collection.mutable.Shrinkable;
import scala.collection.parallel.Combiner;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\rhaB\u0010!!\u0003\r\t!\u000b\u0005\u0006/\u0002!\t\u0001\u0017\u0005\u00069\u0002!\t%\u0018\u0005\u0007C\u0002\u0001K\u0011\u000b2\t\u000b\u0019\u0004A\u0011I4\t\u000b-\u0004A\u0011\t7\t\u000b5\u0004a\u0011\u00018\t\u000b=\u0004A\u0011\u00019\t\u000bY\u0004A\u0011A<\b\u000be\u0004\u0003\u0012\u0001>\u0007\u000b}\u0001\u0003\u0012A>\t\u000f\u0005\u0005!\u0002\"\u0001\u0002\u0004!11N\u0003C\u0001\u0003\u000bAa!\u0019\u0006\u0005\u0002\u0005M\u0001bBA\u0013\u0015\u0011\r\u0011q\u0005\u0004\u0007\u0003\u0017R\u0001!!\u0014\t\u0015\u0005]tB!A!\u0002\u0013\t)\bC\u0005s\u001f\t\u0005\t\u0015!\u0003\u0002z!9\u0011\u0011A\b\u0005\u0002\u0005m\u0004\"\u0002/\u0010\t\u0003j\u0006bBAC\u001f\u0011\u0005\u0011q\u0011\u0005\b\u0003#{A\u0011AAJ\u0011\u0019Yw\u0002\"\u0011\u0002\u001a\"9\u00111T\b\u0005B\u0005u\u0005bBAY\u001f\u0011\u0005\u00131\u0017\u0005\b\u0003\u0003|A\u0011IAb\u0011\u0019iw\u0002\"\u0011\u0002H\"1\u00111Z\b\u0005\u0002aCq!!4\u0010\t\u0003\ty\r\u0003\u0004p\u001f\u0011\u0005\u00131\u001c\u0005\u0007m>!\t%a8\u0003\rA\u000b'/T1q\u0015\t\t#%A\u0004nkR\f'\r\\3\u000b\u0005\r\"\u0013\u0001\u00039be\u0006dG.\u001a7\u000b\u0005\u00152\u0013AC2pY2,7\r^5p]*\tq%A\u0003tG\u0006d\u0017m\u0001\u0001\u0016\u0007)\"dh\u0005\u0004\u0001W=\u0002uI\u0014\t\u0003Y5j\u0011AJ\u0005\u0003]\u0019\u0012a!\u00118z%\u00164\u0007\u0003\u0002\u00192euj\u0011AI\u0005\u0003?\t\u0002\"a\r\u001b\r\u0001\u0011)Q\u0007\u0001b\u0001m\t\t1*\u0005\u00028uA\u0011A\u0006O\u0005\u0003s\u0019\u0012qAT8uQ&tw\r\u0005\u0002-w%\u0011AH\n\u0002\u0004\u0003:L\bCA\u001a?\t\u0015y\u0004A1\u00017\u0005\u00051\u0006cA!C\t6\t\u0001%\u0003\u0002DA\tY\u0001+\u0019:Ji\u0016\u0014\u0018M\u00197f!\u0011aSIM\u001f\n\u0005\u00193#A\u0002+va2,'\u0007E\u0003I\u0017JjT*D\u0001J\u0015\tQE%A\u0004hK:,'/[2\n\u00051K%!F$f]\u0016\u0014\u0018n\u0019)be6\u000b\u0007\u000fV3na2\fG/\u001a\t\u0003\u0003\u0002\u0001r!Q(3{5\u000b&+\u0003\u0002QA\tQ\u0001+\u0019:NCBd\u0015n[3\u0011\t\u0005\u0003!'\u0010\t\u0005'V\u0013T(D\u0001U\u0015\t\tC%\u0003\u0002W)\n\u0019Q*\u00199\u0002\r\u0011Jg.\u001b;%)\u0005I\u0006C\u0001\u0017[\u0013\tYfE\u0001\u0003V]&$\u0018!C6o_^t7+\u001b>f+\u0005q\u0006C\u0001\u0017`\u0013\t\u0001gEA\u0002J]R\f1B\\3x\u0007>l'-\u001b8feV\t1\r\u0005\u00031I\u0012\u000b\u0016BA3#\u0005!\u0019u.\u001c2j]\u0016\u0014\u0018\u0001D7ba\u000e{W\u000e]1oS>tW#\u00015\u0011\u0007!KW*\u0003\u0002k\u0013\n1r)\u001a8fe&\u001c\u0007+\u0019:NCB\u001cu.\u001c9b]&|g.A\u0003f[B$\u00180F\u0001R\u0003\r\u0019X-]\u000b\u0002%\u0006Yq/\u001b;i\t\u00164\u0017-\u001e7u)\t\t\u0016\u000fC\u0003s\u000f\u0001\u00071/A\u0001e!\u0011aCOM\u001f\n\u0005U4#!\u0003$v]\u000e$\u0018n\u001c82\u0003A9\u0018\u000e\u001e5EK\u001a\fW\u000f\u001c;WC2,X\r\u0006\u0002Rq\")!\u000f\u0003a\u0001{\u00051\u0001+\u0019:NCB\u0004\"!\u0011\u0006\u0014\u0005)a\b\u0003\u0002%~\u001b~L!A`%\u0003\u001bA\u000b'/T1q\r\u0006\u001cGo\u001c:z!\t\u0019V+\u0001\u0004=S:LGO\u0010\u000b\u0002uV1\u0011qAA\u0007\u0003#)\"!!\u0003\u0011\r\u0005\u0003\u00111BA\b!\r\u0019\u0014Q\u0002\u0003\u0006k1\u0011\rA\u000e\t\u0004g\u0005EA!B \r\u0005\u00041TCBA\u000b\u0003;\t\t#\u0006\u0002\u0002\u0018A1\u0001\u0007ZA\r\u0003G\u0001b\u0001L#\u0002\u001c\u0005}\u0001cA\u001a\u0002\u001e\u0011)Q'\u0004b\u0001mA\u00191'!\t\u0005\u000b}j!\u0019\u0001\u001c\u0011\r\u0005\u0003\u00111DA\u0010\u00031\u0019\u0017M\u001c\"vS2$gI]8n+)\tI#!\u000e\u0002<\u0005\r\u0013qI\u000b\u0003\u0003W\u0001\u0012\u0002SA\u0017\u0003c\ty$!\u0013\n\u0007\u0005=\u0012J\u0001\bDC:\u001cu.\u001c2j]\u00164%o\\7\u0011\r\u0005\u0003\u00111GA\u001d!\r\u0019\u0014Q\u0007\u0003\u0007\u0003oq!\u0019\u0001\u001c\u0003\u000b\u0019\u0013x.\\&\u0011\u0007M\nY\u0004\u0002\u0004\u0002>9\u0011\rA\u000e\u0002\u0006\rJ|WN\u0016\t\u0007Y\u0015\u000b\t%!\u0012\u0011\u0007M\n\u0019\u0005B\u00036\u001d\t\u0007a\u0007E\u00024\u0003\u000f\"Qa\u0010\bC\u0002Y\u0002b!\u0011\u0001\u0002B\u0005\u0015#aC,ji\"$UMZ1vYR,b!a\u0014\u0002p\u0005M4#B\b\u0002R\u0005U\u0004\u0003CA*\u0003S\ni'!\u001d\u000f\t\u0005U\u0013q\r\b\u0005\u0003/\n)G\u0004\u0003\u0002Z\u0005\rd\u0002BA.\u0003Cj!!!\u0018\u000b\u0007\u0005}\u0003&\u0001\u0004=e>|GOP\u0005\u0002O%\u0011QEJ\u0005\u0003G\u0011J!!\u001f\u0012\n\t\u0005-\u00131\u000e\u0006\u0003s\n\u00022aMA8\t\u0015)tB1\u00017!\r\u0019\u00141\u000f\u0003\u0006\u007f=\u0011\rA\u000e\t\u0007\u0003\u0002\ti'!\u001d\u0002\u0015UtG-\u001a:ms&tw\r\u0005\u0004-i\u00065\u0014\u0011\u000f\u000b\u0007\u0003{\n\t)a!\u0011\u000f\u0005}t\"!\u001c\u0002r5\t!\u0002C\u0004\u0002xI\u0001\r!!\u001e\t\rI\u0014\u0002\u0019AA=\u0003\u0019\tG\rZ(oKR!\u0011\u0011RAF\u001b\u0005y\u0001bBAG)\u0001\u0007\u0011qR\u0001\u0003WZ\u0004b\u0001L#\u0002n\u0005E\u0014aC:vER\u0014\u0018m\u0019;P]\u0016$B!!#\u0002\u0016\"9\u0011qS\u000bA\u0002\u00055\u0014aA6fsV\u0011\u0011QP\u0001\bkB$\u0017\r^3e+\u0011\ty*!*\u0015\r\u0005\u0005\u00161VAW!\u001d\tyhDA7\u0003G\u00032aMAS\t\u001d\t9k\u0006b\u0001\u0003S\u0013\u0011!V\t\u0004\u0003cR\u0004bBAL/\u0001\u0007\u0011Q\u000e\u0005\b\u0003_;\u0002\u0019AAR\u0003\u00151\u0018\r\\;f\u0003\u0015!\u0003\u000f\\;t+\u0011\t),a/\u0015\t\u0005]\u0016Q\u0018\t\b\u0003\u007fz\u0011QNA]!\r\u0019\u00141\u0018\u0003\b\u0003OC\"\u0019AAU\u0011\u001d\ti\t\u0007a\u0001\u0003\u007f\u0003b\u0001L#\u0002n\u0005e\u0016A\u0002\u0013nS:,8\u000f\u0006\u0003\u0002~\u0005\u0015\u0007bBAL3\u0001\u0007\u0011QN\u000b\u0003\u0003\u0013\u0004baU+\u0002n\u0005E\u0014!B2mK\u0006\u0014\u0018a\u00019viR1\u0011\u0011[Al\u00033\u0004R\u0001LAj\u0003cJ1!!6'\u0005\u0019y\u0005\u000f^5p]\"9\u0011q\u0013\u000fA\u0002\u00055\u0004bBAX9\u0001\u0007\u0011\u0011\u000f\u000b\u0005\u0003k\ni\u000e\u0003\u0004s;\u0001\u0007\u0011\u0011\u0010\u000b\u0005\u0003k\n\t\u000f\u0003\u0004s=\u0001\u0007\u0011\u0011\u000f"
)
public interface ParMap extends scala.collection.parallel.ParMap, ParIterable, ParMapLike {
   static CanCombineFrom canBuildFrom() {
      return ParMap$.MODULE$.canBuildFrom();
   }

   static Factory toFactory() {
      return ParMap$.MODULE$.toFactory();
   }

   // $FF: synthetic method
   static int knownSize$(final ParMap $this) {
      return $this.knownSize();
   }

   default int knownSize() {
      return -1;
   }

   default Combiner newCombiner() {
      return ParMap$.MODULE$.newCombiner();
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

   scala.collection.mutable.Map seq();

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

      public Combiner newCombiner() {
         return ParMap.super.newCombiner();
      }

      public GenericParMapCompanion mapCompanion() {
         return ParMap.super.mapCompanion();
      }

      public ParMap clone() {
         return ParMapLike.clone$(this);
      }

      // $FF: synthetic method
      public Object scala$collection$mutable$Cloneable$$super$clone() {
         return super.clone();
      }

      public final Shrinkable $minus$eq(final Object elem) {
         return Shrinkable.$minus$eq$(this, elem);
      }

      /** @deprecated */
      public Shrinkable $minus$eq(final Object elem1, final Object elem2, final Seq elems) {
         return Shrinkable.$minus$eq$(this, elem1, elem2, elems);
      }

      public Shrinkable subtractAll(final IterableOnce xs) {
         return Shrinkable.subtractAll$(this, xs);
      }

      public final Shrinkable $minus$minus$eq(final IterableOnce xs) {
         return Shrinkable.$minus$minus$eq$(this, xs);
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

      public WithDefault addOne(final Tuple2 kv) {
         this.underlying.$plus$eq(kv);
         return this;
      }

      public WithDefault subtractOne(final Object key) {
         this.underlying.$minus$eq(key);
         return this;
      }

      public WithDefault empty() {
         return new WithDefault(this.underlying.empty(), this.d);
      }

      public WithDefault updated(final Object key, final Object value) {
         return new WithDefault((ParMap)this.underlying.updated(key, value), this.d);
      }

      public WithDefault $plus(final Tuple2 kv) {
         return this.updated(kv._1(), kv._2());
      }

      public WithDefault $minus(final Object key) {
         return new WithDefault(this.underlying.$minus(key), this.d);
      }

      public scala.collection.mutable.Map seq() {
         return this.underlying.seq().withDefault(this.d);
      }

      public void clear() {
         this.underlying.clear();
      }

      public Option put(final Object key, final Object value) {
         return this.underlying.put(key, value);
      }

      public ParMap withDefault(final Function1 d) {
         return new WithDefault(this.underlying, d);
      }

      public ParMap withDefaultValue(final Object d) {
         return new WithDefault(this.underlying, (x) -> d);
      }

      public WithDefault(final ParMap underlying, final Function1 d) {
         super(underlying, d);
         this.underlying = underlying;
         this.d = d;
         ParIterable.$init$(this);
         Growable.$init$(this);
         Shrinkable.$init$(this);
         Cloneable.$init$(this);
         ParMapLike.$init$(this);
         ParMap.$init$(this);
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
