package breeze.generic;

import breeze.util.ReflectionUtil$;
import java.lang.invoke.SerializedLambda;
import java.util.concurrent.ConcurrentHashMap;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.HashMap;
import scala.collection.mutable.Queue;
import scala.collection.mutable.Set;
import scala.collection.mutable.Set.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ObjectRef;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015ga\u0002\u0005\n!\u0003\r\tA\u0004\u0005\u0006-\u0001!\ta\u0006\u0005\b7\u0001\u0011\r\u0011\"\u0005\u001d\u0011\u001dI\u0005A1A\u0005\u0012)CQA\u001a\u0001\u0005\u0002\u001dDq!!\u0005\u0001\t\u0013\t\u0019\u0002C\u0004\u00026\u0001!\t!a\u000e\t\u000f\u0005\r\u0005\u0001\"\u0005\u0002\u0006\nYQ*\u0014*fO&\u001cHO]=4\u0015\tQ1\"A\u0004hK:,'/[2\u000b\u00031\taA\u0019:fKj,7\u0001A\u000b\u0003\u001f\u001d\u001b\"\u0001\u0001\t\u0011\u0005E!R\"\u0001\n\u000b\u0003M\tQa]2bY\u0006L!!\u0006\n\u0003\r\u0005s\u0017PU3g\u0003\u0019!\u0013N\\5uIQ\t\u0001\u0004\u0005\u0002\u00123%\u0011!D\u0005\u0002\u0005+:LG/A\u0002paN,\u0012!\b\t\u0005=\r*c)D\u0001 \u0015\t\u0001\u0013%A\u0004nkR\f'\r\\3\u000b\u0005\t\u0012\u0012AC2pY2,7\r^5p]&\u0011Ae\b\u0002\b\u0011\u0006\u001c\b.T1q!\u0015\tb\u0005\u000b\u001fB\u0013\t9#C\u0001\u0004UkBdWm\r\u0019\u0003SM\u00022AK\u00182\u001b\u0005Y#B\u0001\u0017.\u0003\u0011a\u0017M\\4\u000b\u00039\nAA[1wC&\u0011\u0001g\u000b\u0002\u0006\u00072\f7o\u001d\t\u0003eMb\u0001\u0001B\u00055\u0005\u0005\u0005\t\u0011!B\u0001k\t!q\f\n\u001a4#\t1\u0014\b\u0005\u0002\u0012o%\u0011\u0001H\u0005\u0002\b\u001d>$\b.\u001b8h!\t\t\"(\u0003\u0002<%\t\u0019\u0011I\\=1\u0005uz\u0004c\u0001\u00160}A\u0011!g\u0010\u0003\n\u0001\n\t\t\u0011!A\u0003\u0002U\u0012Aa\u0018\u00133iA\u0012!\t\u0012\t\u0004U=\u001a\u0005C\u0001\u001aE\t%)%!!A\u0001\u0002\u000b\u0005QG\u0001\u0003`II*\u0004C\u0001\u001aH\t\u0015A\u0005A1\u00016\u0005\u0005\u0011\u0016!B2bG\",W#A&\u0011\t1\u000b6kY\u0007\u0002\u001b*\u0011ajT\u0001\u000bG>t7-\u001e:sK:$(B\u0001).\u0003\u0011)H/\u001b7\n\u0005Ik%!E\"p]\u000e,(O]3oi\"\u000b7\u000f['baB)\u0011C\n+Z=B\u0012Qk\u0016\t\u0004U=2\u0006C\u0001\u001aX\t%A6!!A\u0001\u0002\u000b\u0005QG\u0001\u0003`II2\u0004G\u0001.]!\rQsf\u0017\t\u0003eq#\u0011\"X\u0002\u0002\u0002\u0003\u0005)\u0011A\u001b\u0003\t}##g\u000e\u0019\u0003?\u0006\u00042AK\u0018a!\t\u0011\u0014\rB\u0005c\u0007\u0005\u0005\t\u0011!B\u0001k\t!q\f\n\u001a9!\r\tBMR\u0005\u0003KJ\u0011aa\u00149uS>t\u0017\u0001\u0003:fO&\u001cH/\u001a:\u0015\raA\u0007p`A\u0007\u0011\u0015IG\u00011\u0001k\u0003\u0005\t\u0007GA6w!\ra7/\u001e\b\u0003[F\u0004\"A\u001c\n\u000e\u0003=T!\u0001]\u0007\u0002\rq\u0012xn\u001c;?\u0013\t\u0011(#\u0001\u0004Qe\u0016$WMZ\u0005\u0003aQT!A\u001d\n\u0011\u0005I2H!C<i\u0003\u0003\u0005\tQ!\u00016\u0005\u0011yFEM\u001d\t\u000be$\u0001\u0019\u0001>\u0002\u0003\t\u0004$a_?\u0011\u00071\u001cH\u0010\u0005\u00023{\u0012Ia\u0010_A\u0001\u0002\u0003\u0015\t!\u000e\u0002\u0005?\u0012\u001a\u0004\u0007C\u0004\u0002\u0002\u0011\u0001\r!a\u0001\u0002\u0003\r\u0004D!!\u0002\u0002\nA!An]A\u0004!\r\u0011\u0014\u0011\u0002\u0003\u000b\u0003\u0017y\u0018\u0011!A\u0001\u0006\u0003)$\u0001B0%gEBa!a\u0004\u0005\u0001\u00041\u0015AA8q\u0003=\u0019Gn\\:f'V\u0004XM\u001d;za\u0016\u001cH\u0003BA\u000b\u0003S\u0001RAHA\f\u00037I1!!\u0007 \u0005\r\u0019V\r\u001e\u0019\u0005\u0003;\t\t\u0003\u0005\u0003+_\u0005}\u0001c\u0001\u001a\u0002\"\u0011Y\u00111EA\u0013\u0003\u0003\u0005\tQ!\u00016\u0005\u0011yFe\r\u001b\t\r\u0005\u001dR\u0001AA\u000b\u0003\u0019\u0011Xm];mi\"1\u0011.\u0002a\u0001\u0003W\u0001D!!\f\u00022A!An]A\u0018!\r\u0011\u0014\u0011\u0007\u0003\f\u0003g\tI#!A\u0001\u0002\u000b\u0005QG\u0001\u0003`IM\u001a\u0014a\u0002:fg>dg/\u001a\u000b\t\u0003s\ty&a\u001b\u0002xA1A.a\u000f\u0002@\u0019K1!!\u0010u\u0005\ri\u0015\r\u001d\t\t#\u0019\n\t%a\u0013\u0002VA\"\u00111IA$!\u0011a7/!\u0012\u0011\u0007I\n9\u0005\u0002\u0006\u0002J\u0019\t\t\u0011!A\u0003\u0002U\u0012Aa\u0018\u00134sA\"\u0011QJA)!\u0011a7/a\u0014\u0011\u0007I\n\t\u0006\u0002\u0006\u0002T\u0019\t\t\u0011!A\u0003\u0002U\u0012Aa\u0018\u00135aA\"\u0011qKA.!\u0011a7/!\u0017\u0011\u0007I\nY\u0006\u0002\u0006\u0002^\u0019\t\t\u0011!A\u0003\u0002U\u0012Aa\u0018\u00135c!1\u0011N\u0002a\u0001\u0003C\u0002D!a\u0019\u0002hA!An]A3!\r\u0011\u0014q\r\u0003\f\u0003S\ny&!A\u0001\u0002\u000b\u0005QG\u0001\u0003`IM2\u0004BB=\u0007\u0001\u0004\ti\u0007\r\u0003\u0002p\u0005M\u0004\u0003\u00027t\u0003c\u00022AMA:\t-\t)(a\u001b\u0002\u0002\u0003\u0005)\u0011A\u001b\u0003\t}#3g\u000e\u0005\b\u0003\u00031\u0001\u0019AA=a\u0011\tY(a \u0011\t1\u001c\u0018Q\u0010\t\u0004e\u0005}DaCAA\u0003o\n\t\u0011!A\u0003\u0002U\u0012Aa\u0018\u00134q\u0005\u00012/\u001a7fGR\u0014Um\u001d;PaRLwN\u001c\u000b\u0005\u0003\u000f\u000bi\nE\u0004\u0002\n\u0006=\u0015\u0011\u0013$\u000e\u0005\u0005-%bAAGC\u0005I\u0011.\\7vi\u0006\u0014G.Z\u0005\u0005\u0003{\tY\t\u0005\u0005\u0012M\u0005M\u0015QXAaa\u0011\t)*!'\u0011\t)z\u0013q\u0013\t\u0004e\u0005eEaCAN\u0003;\u000b\t\u0011!A\u0003\u0002U\u0012Aa\u0018\u00135q!9\u0011qT\u0004A\u0002\u0005\u0005\u0016aB8qi&|gn\u001d\t\u0007Y\u0006m\u00121\u0015$\u0011\u0011E1\u0013QUAU\u0003g\u0003D!a*\u0002\u001aB!An]ALa\u0011\tY+a,\u0011\t1\u001c\u0018Q\u0016\t\u0004e\u0005=FaCAY\u0003;\u000b\t\u0011!A\u0003\u0002U\u0012Aa\u0018\u00135sA\"\u0011QWA]!\u0011a7/a.\u0011\u0007I\nI\fB\u0006\u0002<\u0006u\u0015\u0011!A\u0001\u0006\u0003)$\u0001B0%kA\u0002D!a0\u00020B!!fLAWa\u0011\t\u0019-!/\u0011\t)z\u0013q\u0017"
)
public interface MMRegistry3 {
   void breeze$generic$MMRegistry3$_setter_$ops_$eq(final HashMap x$1);

   void breeze$generic$MMRegistry3$_setter_$cache_$eq(final ConcurrentHashMap x$1);

   HashMap ops();

   ConcurrentHashMap cache();

   // $FF: synthetic method
   static void register$(final MMRegistry3 $this, final Class a, final Class b, final Class c, final Object op) {
      $this.register(a, b, c, op);
   }

   default void register(final Class a, final Class b, final Class c, final Object op) {
      this.ops().update(new Tuple3(a, b, c), op);
      choicesFor$1(a).foreach((ac) -> {
         $anonfun$register$1(this, b, c, op, ac);
         return BoxedUnit.UNIT;
      });
      this.cache().clear();
   }

   private Set closeSupertypes(final Class a) {
      Set result = (Set).MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
      Queue queue = new Queue(scala.collection.mutable.Queue..MODULE$.$lessinit$greater$default$1());
      queue.enqueue(a);

      Class t;
      for(; queue.nonEmpty(); scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps((Object[])t.getInterfaces()), (i) -> !result.apply(i) ? queue.$plus$eq(i) : BoxedUnit.UNIT)) {
         t = (Class)queue.dequeue();
         result.$plus$eq(t);
         Class s = t.getSuperclass();
         if (s != null) {
            queue.$plus$eq(s);
         } else {
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }
      }

      return result;
   }

   // $FF: synthetic method
   static Map resolve$(final MMRegistry3 $this, final Class a, final Class b, final Class c) {
      return $this.resolve(a, b, c);
   }

   default Map resolve(final Class a, final Class b, final Class c) {
      Option var5 = this.ops().get(new Tuple3(a, b, c));
      Map var4;
      if (var5 instanceof Some) {
         Some var6 = (Some)var5;
         Object m = var6.value();
         var4 = (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(new Tuple3(a, b, c)), m)})));
      } else {
         if (!scala.None..MODULE$.equals(var5)) {
            throw new MatchError(var5);
         }

         Set sa = this.closeSupertypes(a);
         Set sb = this.closeSupertypes(b);
         Set sc = this.closeSupertypes(c);
         ArrayBuffer candidates = (ArrayBuffer)scala.collection.mutable.ArrayBuffer..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
         sa.foreach((aa) -> {
            $anonfun$resolve$4(this, sb, sc, candidates, aa);
            return BoxedUnit.UNIT;
         });
         var4 = candidates.toMap(scala..less.colon.less..MODULE$.refl());
      }

      return var4;
   }

   // $FF: synthetic method
   static Map selectBestOption$(final MMRegistry3 $this, final Map options) {
      return $this.selectBestOption(options);
   }

   default Map selectBestOption(final Map options) {
      ObjectRef bestCandidates = ObjectRef.create((scala.collection.immutable.Set)scala.Predef..MODULE$.Set().apply(scala.collection.immutable.Nil..MODULE$));
      options.keys().withFilter((check$ifrefutable$2) -> BoxesRunTime.boxToBoolean($anonfun$selectBestOption$5(check$ifrefutable$2))).foreach((x$2) -> {
         $anonfun$selectBestOption$6(bestCandidates, x$2);
         return BoxedUnit.UNIT;
      });
      return options.view().filterKeys((scala.collection.immutable.Set)bestCandidates.elem).toMap(scala..less.colon.less..MODULE$.refl());
   }

   private static Seq choicesFor$1(final Class a) {
      return a.isPrimitive() ? (Seq)scala.package..MODULE$.Seq().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Class[]{a, ReflectionUtil$.MODULE$.boxedFromPrimitive(a)}))) : (Seq)scala.package..MODULE$.Seq().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Class[]{a})));
   }

   // $FF: synthetic method
   static void $anonfun$register$3(final MMRegistry3 $this, final Class ac$1, final Class bc$1, final Object op$1, final Class cc) {
      $this.ops().update(new Tuple3(ac$1, bc$1, cc), op$1);
   }

   // $FF: synthetic method
   static void $anonfun$register$2(final MMRegistry3 $this, final Class c$1, final Class ac$1, final Object op$1, final Class bc) {
      choicesFor$1(c$1).foreach((cc) -> {
         $anonfun$register$3($this, ac$1, bc, op$1, cc);
         return BoxedUnit.UNIT;
      });
   }

   // $FF: synthetic method
   static void $anonfun$register$1(final MMRegistry3 $this, final Class b$1, final Class c$1, final Object op$1, final Class ac) {
      choicesFor$1(b$1).foreach((bc) -> {
         $anonfun$register$2($this, c$1, ac, op$1, bc);
         return BoxedUnit.UNIT;
      });
   }

   // $FF: synthetic method
   static void $anonfun$resolve$6(final MMRegistry3 $this, final Class aa$3, final Class bb$3, final ArrayBuffer candidates$2, final Class cc) {
      $this.ops().get(new Tuple3(aa$3, bb$3, cc)).foreach((op) -> (ArrayBuffer)candidates$2.$plus$eq(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(new Tuple3(aa$3, bb$3, cc)), op)));
   }

   // $FF: synthetic method
   static void $anonfun$resolve$5(final MMRegistry3 $this, final Set sc$1, final Class aa$3, final ArrayBuffer candidates$2, final Class bb) {
      sc$1.foreach((cc) -> {
         $anonfun$resolve$6($this, aa$3, bb, candidates$2, cc);
         return BoxedUnit.UNIT;
      });
   }

   // $FF: synthetic method
   static void $anonfun$resolve$4(final MMRegistry3 $this, final Set sb$2, final Set sc$1, final ArrayBuffer candidates$2, final Class aa) {
      sb$2.foreach((bb) -> {
         $anonfun$resolve$5($this, sc$1, aa, candidates$2, bb);
         return BoxedUnit.UNIT;
      });
   }

   // $FF: synthetic method
   static boolean $anonfun$selectBestOption$5(final Tuple3 check$ifrefutable$2) {
      boolean var1;
      if (check$ifrefutable$2 != null) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   // $FF: synthetic method
   static boolean $anonfun$selectBestOption$7(final Class aa$4, final Class bb$4, final Tuple3 pair) {
      return aa$4.isAssignableFrom((Class)pair._1()) && bb$4.isAssignableFrom((Class)pair._2());
   }

   // $FF: synthetic method
   static boolean $anonfun$selectBestOption$8(final Class aa$4, final Class bb$4, final Class cc$2, final Tuple3 pair) {
      return ((Class)pair._1()).isAssignableFrom(aa$4) && ((Class)pair._2()).isAssignableFrom(bb$4) && ((Class)pair._3()).isAssignableFrom(cc$2);
   }

   // $FF: synthetic method
   static void $anonfun$selectBestOption$6(final ObjectRef bestCandidates$2, final Tuple3 x$2) {
      if (x$2 == null) {
         throw new MatchError(x$2);
      } else {
         Class aa = (Class)x$2._1();
         Class bb = (Class)x$2._2();
         Class cc = (Class)x$2._3();
         if (!((scala.collection.immutable.Set)bestCandidates$2.elem).exists((pair) -> BoxesRunTime.boxToBoolean($anonfun$selectBestOption$7(aa, bb, pair))) && cc.isAssignableFrom((Class)x$2._3())) {
            bestCandidates$2.elem = (scala.collection.immutable.Set)((scala.collection.immutable.Set)bestCandidates$2.elem).filterNot((pair) -> BoxesRunTime.boxToBoolean($anonfun$selectBestOption$8(aa, bb, cc, pair)));
            bestCandidates$2.elem = (scala.collection.immutable.Set)((scala.collection.immutable.Set)bestCandidates$2.elem).$plus(x$2);
            BoxedUnit var7 = BoxedUnit.UNIT;
         } else {
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }

      }
   }

   static void $init$(final MMRegistry3 $this) {
      $this.breeze$generic$MMRegistry3$_setter_$ops_$eq((HashMap)scala.collection.mutable.HashMap..MODULE$.apply(scala.collection.immutable.Nil..MODULE$));
      $this.breeze$generic$MMRegistry3$_setter_$cache_$eq(new ConcurrentHashMap());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
