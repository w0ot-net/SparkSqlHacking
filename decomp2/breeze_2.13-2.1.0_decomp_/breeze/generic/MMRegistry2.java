package breeze.generic;

import breeze.util.ReflectionUtil$;
import java.lang.invoke.SerializedLambda;
import java.util.concurrent.ConcurrentHashMap;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.Predef.ArrowAssoc.;
import scala.collection.MapView;
import scala.collection.immutable.Map;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.HashMap;
import scala.collection.mutable.Queue;
import scala.collection.mutable.Set;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ObjectRef;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005uda\u0002\u0005\n!\u0003\r\tA\u0004\u0005\u0006-\u0001!\ta\u0006\u0005\b7\u0001\u0011\r\u0011\"\u0005\u001d\u0011\u001d!\u0005A1A\u0005\u0012\u0015CQ\u0001\u0018\u0001\u0005\u0002uCQa\u001e\u0001\u0005\naDq!a\u0005\u0001\t\u0003\t)\u0002C\u0004\u0002L\u0001!\t\"!\u0014\u0003\u00175k%+Z4jgR\u0014\u0018P\r\u0006\u0003\u0015-\tqaZ3oKJL7MC\u0001\r\u0003\u0019\u0011'/Z3{K\u000e\u0001QCA\bC'\t\u0001\u0001\u0003\u0005\u0002\u0012)5\t!CC\u0001\u0014\u0003\u0015\u00198-\u00197b\u0013\t)\"C\u0001\u0004B]f\u0014VMZ\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003a\u0001\"!E\r\n\u0005i\u0011\"\u0001B+oSR\f1a\u001c9t+\u0005i\u0002\u0003\u0002\u0010$K\u0005k\u0011a\b\u0006\u0003A\u0005\nq!\\;uC\ndWM\u0003\u0002#%\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\u0005\u0011z\"a\u0002%bg\"l\u0015\r\u001d\t\u0005#\u0019BC(\u0003\u0002(%\t1A+\u001e9mKJ\u0002$!K\u001a\u0011\u0007)z\u0013'D\u0001,\u0015\taS&\u0001\u0003mC:<'\"\u0001\u0018\u0002\t)\fg/Y\u0005\u0003a-\u0012Qa\u00117bgN\u0004\"AM\u001a\r\u0001\u0011IAGAA\u0001\u0002\u0003\u0015\t!\u000e\u0002\u0004?\u0012\u0012\u0014C\u0001\u001c:!\t\tr'\u0003\u00029%\t9aj\u001c;iS:<\u0007CA\t;\u0013\tY$CA\u0002B]f\u0004$!P \u0011\u0007)zc\b\u0005\u00023\u007f\u0011I\u0001IAA\u0001\u0002\u0003\u0015\t!\u000e\u0002\u0004?\u0012\u001a\u0004C\u0001\u001aC\t\u0015\u0019\u0005A1\u00016\u0005\u0005\u0011\u0016!B2bG\",W#\u0001$\u0011\t\u001dce*W\u0007\u0002\u0011*\u0011\u0011JS\u0001\u000bG>t7-\u001e:sK:$(BA&.\u0003\u0011)H/\u001b7\n\u00055C%!E\"p]\u000e,(O]3oi\"\u000b7\u000f['baB!\u0011CJ(Ua\t\u0001&\u000bE\u0002+_E\u0003\"A\r*\u0005\u0013M\u001b\u0011\u0011!A\u0001\u0006\u0003)$aA0%iA\u0012Qk\u0016\t\u0004U=2\u0006C\u0001\u001aX\t%A6!!A\u0001\u0002\u000b\u0005QGA\u0002`IU\u00022!\u0005.B\u0013\tY&C\u0001\u0004PaRLwN\\\u0001\te\u0016<\u0017n\u001d;feR!\u0001D\u00188v\u0011\u0015yF\u00011\u0001a\u0003\u0005\t\u0007GA1m!\r\u0011\u0017n\u001b\b\u0003G\u001e\u0004\"\u0001\u001a\n\u000e\u0003\u0015T!AZ\u0007\u0002\rq\u0012xn\u001c;?\u0013\tA'#\u0001\u0004Qe\u0016$WMZ\u0005\u0003a)T!\u0001\u001b\n\u0011\u0005IbG!C7_\u0003\u0003\u0005\tQ!\u00016\u0005\ryFE\u000e\u0005\u0006_\u0012\u0001\r\u0001]\u0001\u0002EB\u0012\u0011o\u001d\t\u0004E&\u0014\bC\u0001\u001at\t%!h.!A\u0001\u0002\u000b\u0005QGA\u0002`I]BQA\u001e\u0003A\u0002\u0005\u000b!a\u001c9\u0002\u001f\rdwn]3TkB,'\u000f^=qKN$2!_A\u0004!\rq\"\u0010`\u0005\u0003w~\u00111aU3ua\tix\u0010E\u0002+_y\u0004\"AM@\u0005\u0017\u0005\u0005\u00111AA\u0001\u0002\u0003\u0015\t!\u000e\u0002\u0004?\u0012J\u0004\"BA\u0003\u000b\u0001I\u0018A\u0002:fgVdG\u000f\u0003\u0004`\u000b\u0001\u0007\u0011\u0011\u0002\u0019\u0005\u0003\u0017\ty\u0001\u0005\u0003cS\u00065\u0001c\u0001\u001a\u0002\u0010\u0011Y\u0011\u0011CA\u0004\u0003\u0003\u0005\tQ!\u00016\u0005\ryF\u0005O\u0001\be\u0016\u001cx\u000e\u001c<f)\u0019\t9\"a\r\u0002@A1!-!\u0007\u0002\u001e\u0005K1!a\u0007k\u0005\ri\u0015\r\u001d\t\u0007#\u0019\ny\"!\u000b1\t\u0005\u0005\u0012Q\u0005\t\u0005E&\f\u0019\u0003E\u00023\u0003K!!\"a\n\u0007\u0003\u0003\u0005\tQ!\u00016\u0005\u0011yF%M\u001a1\t\u0005-\u0012q\u0006\t\u0005E&\fi\u0003E\u00023\u0003_!!\"!\r\u0007\u0003\u0003\u0005\tQ!\u00016\u0005\u0011yF%\r\u001b\t\r}3\u0001\u0019AA\u001ba\u0011\t9$a\u000f\u0011\t\tL\u0017\u0011\b\t\u0004e\u0005mBaCA\u001f\u0003g\t\t\u0011!A\u0003\u0002U\u0012Aa\u0018\u00132c!1qN\u0002a\u0001\u0003\u0003\u0002D!a\u0011\u0002HA!!-[A#!\r\u0011\u0014q\t\u0003\f\u0003\u0013\ny$!A\u0001\u0002\u000b\u0005QG\u0001\u0003`IE\u0012\u0014\u0001E:fY\u0016\u001cGOQ3ti>\u0003H/[8o)\u0011\ty%a\u0019\u0011\u000f\u0005E\u00131KA,\u00036\t\u0011%C\u0002\u0002V\u0005\u0012q!T1q-&,w\u000f\u0005\u0004\u0012M\u0005e\u0013\u0011\u0010\u0019\u0005\u00037\ny\u0006\u0005\u0003+_\u0005u\u0003c\u0001\u001a\u0002`\u0011Y\u0011\u0011MA2\u0003\u0003\u0005\tQ!\u00016\u0005\u0011yF%M\u001d\t\u000f\u0005\u0015t\u00011\u0001\u0002h\u00059q\u000e\u001d;j_:\u001c\bC\u00022\u0002\u001a\u0005%\u0014\t\u0005\u0004\u0012M\u0005-\u0014q\u000e\u0019\u0005\u0003[\ny\u0006\u0005\u0003cS\u0006u\u0003\u0007BA9\u0003k\u0002BAY5\u0002tA\u0019!'!\u001e\u0005\u0017\u0005]\u00141MA\u0001\u0002\u0003\u0015\t!\u000e\u0002\u0005?\u0012\u0012\u0004\u0007\r\u0003\u0002|\u0005U\u0004\u0003\u0002\u00160\u0003g\u0002"
)
public interface MMRegistry2 {
   void breeze$generic$MMRegistry2$_setter_$ops_$eq(final HashMap x$1);

   void breeze$generic$MMRegistry2$_setter_$cache_$eq(final ConcurrentHashMap x$1);

   HashMap ops();

   ConcurrentHashMap cache();

   // $FF: synthetic method
   static void register$(final MMRegistry2 $this, final Class a, final Class b, final Object op) {
      $this.register(a, b, op);
   }

   default void register(final Class a, final Class b, final Object op) {
      this.ops().update(.MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(a), b), op);
      if (b.isPrimitive()) {
         this.ops().update(.MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(a), ReflectionUtil$.MODULE$.boxedFromPrimitive(b)), op);
      }

      if (a.isPrimitive()) {
         this.ops().update(.MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(ReflectionUtil$.MODULE$.boxedFromPrimitive(a)), b), op);
         if (b.isPrimitive()) {
            this.ops().update(.MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(ReflectionUtil$.MODULE$.boxedFromPrimitive(a)), ReflectionUtil$.MODULE$.boxedFromPrimitive(b)), op);
         }
      }

      this.cache().clear();
   }

   private Set closeSupertypes(final Class a) {
      Set result = (Set)scala.collection.mutable.Set..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
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
   static Map resolve$(final MMRegistry2 $this, final Class a, final Class b) {
      return $this.resolve(a, b);
   }

   default Map resolve(final Class a, final Class b) {
      Option var4 = this.ops().get(.MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(a), b));
      Map var3;
      if (var4 instanceof Some) {
         Some var5 = (Some)var4;
         Object m = var5.value();
         var3 = (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{.MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(.MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(a), b)), m)})));
      } else {
         if (!scala.None..MODULE$.equals(var4)) {
            throw new MatchError(var4);
         }

         Set sa = this.closeSupertypes(a);
         Set sb = this.closeSupertypes(b);
         ArrayBuffer candidates = (ArrayBuffer)scala.collection.mutable.ArrayBuffer..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
         sa.foreach((aa) -> {
            $anonfun$resolve$1(this, sb, candidates, aa);
            return BoxedUnit.UNIT;
         });
         var3 = candidates.toMap(scala..less.colon.less..MODULE$.refl());
      }

      return var3;
   }

   // $FF: synthetic method
   static MapView selectBestOption$(final MMRegistry2 $this, final Map options) {
      return $this.selectBestOption(options);
   }

   default MapView selectBestOption(final Map options) {
      ObjectRef bestCandidates = ObjectRef.create((scala.collection.immutable.Set)scala.Predef..MODULE$.Set().apply(scala.collection.immutable.Nil..MODULE$));
      options.keys().withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$selectBestOption$1(check$ifrefutable$1))).foreach((x$1) -> {
         $anonfun$selectBestOption$2(bestCandidates, x$1);
         return BoxedUnit.UNIT;
      });
      return options.filterKeys((scala.collection.immutable.Set)bestCandidates.elem);
   }

   // $FF: synthetic method
   static void $anonfun$resolve$2(final MMRegistry2 $this, final Class aa$1, final ArrayBuffer candidates$1, final Class bb) {
      $this.ops().get(.MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(aa$1), bb)).foreach((op) -> (ArrayBuffer)candidates$1.$plus$eq(.MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(.MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(aa$1), bb)), op)));
   }

   // $FF: synthetic method
   static void $anonfun$resolve$1(final MMRegistry2 $this, final Set sb$1, final ArrayBuffer candidates$1, final Class aa) {
      sb$1.foreach((bb) -> {
         $anonfun$resolve$2($this, aa, candidates$1, bb);
         return BoxedUnit.UNIT;
      });
   }

   // $FF: synthetic method
   static boolean $anonfun$selectBestOption$1(final Tuple2 check$ifrefutable$1) {
      boolean var1;
      if (check$ifrefutable$1 != null) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   // $FF: synthetic method
   static boolean $anonfun$selectBestOption$3(final Class aa$2, final Class bb$2, final Tuple2 pair) {
      return aa$2.isAssignableFrom((Class)pair._1()) && bb$2.isAssignableFrom((Class)pair._2());
   }

   // $FF: synthetic method
   static boolean $anonfun$selectBestOption$4(final Class aa$2, final Class bb$2, final Tuple2 pair) {
      return ((Class)pair._1()).isAssignableFrom(aa$2) && ((Class)pair._2()).isAssignableFrom(bb$2);
   }

   // $FF: synthetic method
   static void $anonfun$selectBestOption$2(final ObjectRef bestCandidates$1, final Tuple2 x$1) {
      if (x$1 != null) {
         Class aa = (Class)x$1._1();
         Class bb = (Class)x$1._2();
         if (!((scala.collection.immutable.Set)bestCandidates$1.elem).exists((pair) -> BoxesRunTime.boxToBoolean($anonfun$selectBestOption$3(aa, bb, pair)))) {
            bestCandidates$1.elem = (scala.collection.immutable.Set)((scala.collection.immutable.Set)bestCandidates$1.elem).filterNot((pair) -> BoxesRunTime.boxToBoolean($anonfun$selectBestOption$4(aa, bb, pair)));
            bestCandidates$1.elem = (scala.collection.immutable.Set)((scala.collection.immutable.Set)bestCandidates$1.elem).$plus(x$1);
            BoxedUnit var10000 = BoxedUnit.UNIT;
         } else {
            BoxedUnit var6 = BoxedUnit.UNIT;
         }

      } else {
         throw new MatchError(x$1);
      }
   }

   static void $init$(final MMRegistry2 $this) {
      $this.breeze$generic$MMRegistry2$_setter_$ops_$eq((HashMap)scala.collection.mutable.HashMap..MODULE$.apply(scala.collection.immutable.Nil..MODULE$));
      $this.breeze$generic$MMRegistry2$_setter_$cache_$eq(new ConcurrentHashMap());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
