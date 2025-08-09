package breeze.generic;

import breeze.util.ReflectionUtil$;
import java.lang.invoke.SerializedLambda;
import java.util.concurrent.ConcurrentHashMap;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.immutable.Map;
import scala.collection.immutable.Set;
import scala.collection.mutable.HashMap;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ObjectRef;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Uba\u0002\u0005\n!\u0003\r\tA\u0004\u0005\u0006-\u0001!\ta\u0006\u0005\b7\u0001\u0011\r\u0011\"\u0005\u001d\u0011\u001da\u0004A1A\u0005\u0012uBQa\u0013\u0001\u0005\u00021CQa\u0018\u0001\u0005\u0012\u0001Dq!\u001f\u0001\u0012\u0002\u0013E!\u0010C\u0004\u0002\u0016\u0001!\t\"a\u0006\u0003\u00175k%+Z4jgR\u0014\u00180\r\u0006\u0003\u0015-\tqaZ3oKJL7MC\u0001\r\u0003\u0019\u0011'/Z3{K\u000e\u0001QCA\b;'\t\u0001\u0001\u0003\u0005\u0002\u0012)5\t!CC\u0001\u0014\u0003\u0015\u00198-\u00197b\u0013\t)\"C\u0001\u0004B]f\u0014VMZ\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003a\u0001\"!E\r\n\u0005i\u0011\"\u0001B+oSR\f1a\u001c9t+\u0005i\u0002\u0003\u0002\u0010$Kej\u0011a\b\u0006\u0003A\u0005\nq!\\;uC\ndWM\u0003\u0002#%\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\u0005\u0011z\"a\u0002%bg\"l\u0015\r\u001d\u0019\u0003MA\u00022a\n\u0017/\u001b\u0005A#BA\u0015+\u0003\u0011a\u0017M\\4\u000b\u0003-\nAA[1wC&\u0011Q\u0006\u000b\u0002\u0006\u00072\f7o\u001d\t\u0003_Ab\u0001\u0001B\u00052\u0005\u0005\u0005\t\u0011!B\u0001e\t!q\fJ\u001b5#\t\u0019d\u0007\u0005\u0002\u0012i%\u0011QG\u0005\u0002\b\u001d>$\b.\u001b8h!\t\tr'\u0003\u00029%\t\u0019\u0011I\\=\u0011\u0005=RD!B\u001e\u0001\u0005\u0004\u0011$!A'\u0002\u000b\r\f7\r[3\u0016\u0003y\u0002Ba\u0010#Gs5\t\u0001I\u0003\u0002B\u0005\u0006Q1m\u001c8dkJ\u0014XM\u001c;\u000b\u0005\rS\u0013\u0001B;uS2L!!\u0012!\u0003#\r{gnY;se\u0016tG\u000fS1tQ6\u000b\u0007\u000f\r\u0002H\u0013B\u0019q\u0005\f%\u0011\u0005=JE!\u0003&\u0004\u0003\u0003\u0005\tQ!\u00013\u0005\u0011yF%N\u001b\u0002\u0011I,w-[:uKJ$2\u0001G'^\u0011\u0015qE\u00011\u0001P\u0003\u0005\t\u0007G\u0001)\\!\r\t\u0006L\u0017\b\u0003%Z\u0003\"a\u0015\n\u000e\u0003QS!!V\u0007\u0002\rq\u0012xn\u001c;?\u0013\t9&#\u0001\u0004Qe\u0016$WMZ\u0005\u0003[eS!a\u0016\n\u0011\u0005=ZF!\u0003/N\u0003\u0003\u0005\tQ!\u00013\u0005\u0011yF%\u000e\u001c\t\u000by#\u0001\u0019A\u001d\u0002\u0005=\u0004\u0018a\u0002:fg>dg/\u001a\u000b\u0004C&|\u0007\u0003B)cIfJ!aY-\u0003\u00075\u000b\u0007\u000f\r\u0002fOB\u0019\u0011\u000b\u00174\u0011\u0005=:G!\u00035\u0006\u0003\u0003\u0005\tQ!\u00013\u0005\u0011yF%N\u001d\t\u000b9+\u0001\u0019\u000161\u0005-l\u0007cA)YYB\u0011q&\u001c\u0003\n]&\f\t\u0011!A\u0003\u0002I\u0012Aa\u0018\u00136o!9\u0001/\u0002I\u0001\u0002\u0004\t\u0018\u0001C2iK\u000e\\W\rZ!\u0011\u0007E\u0013H/\u0003\u0002t3\n\u00191+\u001a;1\u0005U<\bcA)YmB\u0011qf\u001e\u0003\nq>\f\t\u0011!A\u0003\u0002I\u0012Aa\u0018\u00136q\u0005\t\"/Z:pYZ,G\u0005Z3gCVdG\u000f\n\u001a\u0016\u0003mT3\u0001`A\u0002!\r\t&/ \u0019\u0004}\u0006\u0005\u0001cA)Y\u007fB\u0019q&!\u0001\u0005\u0013a4\u0011\u0011!A\u0001\u0006\u0003\u00114FAA\u0003!\u0011\t9!!\u0005\u000e\u0005\u0005%!\u0002BA\u0006\u0003\u001b\t\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0007\u0005=!#\u0001\u0006b]:|G/\u0019;j_:LA!a\u0005\u0002\n\t\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002!M,G.Z2u\u0005\u0016\u001cHo\u00149uS>tG\u0003BA\r\u0003K\u0001R!\u00152\u0002\u001ce\u0002D!!\b\u0002\"A!\u0011\u000bWA\u0010!\ry\u0013\u0011\u0005\u0003\u000b\u0003G9\u0011\u0011!A\u0001\u0006\u0003\u0011$\u0001B0%mEBq!a\n\b\u0001\u0004\tI#A\u0004paRLwN\\:\u0011\u000bE\u0013\u00171F\u001d1\t\u00055\u0012\u0011\u0007\t\u0005#b\u000by\u0003E\u00020\u0003c!1\"a\r\u0002&\u0005\u0005\t\u0011!B\u0001e\t!q\f\n\u001c1\u0001"
)
public interface MMRegistry1 {
   void breeze$generic$MMRegistry1$_setter_$ops_$eq(final HashMap x$1);

   void breeze$generic$MMRegistry1$_setter_$cache_$eq(final ConcurrentHashMap x$1);

   HashMap ops();

   ConcurrentHashMap cache();

   // $FF: synthetic method
   static void register$(final MMRegistry1 $this, final Class a, final Object op) {
      $this.register(a, op);
   }

   default void register(final Class a, final Object op) {
      this.ops().update(a, op);
      if (a.isPrimitive()) {
         this.ops().update(ReflectionUtil$.MODULE$.boxedFromPrimitive(a), op);
      }

      this.cache().clear();
   }

   // $FF: synthetic method
   static Map resolve$(final MMRegistry1 $this, final Class a, final Set checkedA) {
      return $this.resolve(a, checkedA);
   }

   default Map resolve(final Class a, final Set checkedA) {
      Option var4 = this.ops().get(a);
      Map var3;
      if (var4 instanceof Some) {
         Some var5 = (Some)var4;
         Object m = var5.value();
         var3 = (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(a), m)})));
      } else {
         if (!scala.None..MODULE$.equals(var4)) {
            throw new MatchError(var4);
         }

         Set newCA = (Set)checkedA.$plus$plus(.MODULE$.wrapRefArray((Object[])a.getInterfaces()));
         Class var9 = a.getSuperclass();
         Class[] sa = (Class[])scala.collection.ArrayOps..MODULE$.$plus$colon$extension(.MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.filterNot$extension(.MODULE$.refArrayOps((Object[])a.getInterfaces()), checkedA)), var9, scala.reflect.ClassTag..MODULE$.apply(Class.class));
         Tuple2[] allParents = (Tuple2[])scala.collection.ArrayOps..MODULE$.withFilter$extension(.MODULE$.refArrayOps((Object[])sa), (aa) -> BoxesRunTime.boxToBoolean($anonfun$resolve$8(aa))).flatMap((aa) -> (Map)this.resolve(aa, newCA).map((m) -> m), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
         var3 = .MODULE$.wrapRefArray((Object[])allParents).toMap(scala..less.colon.less..MODULE$.refl());
      }

      return var3;
   }

   // $FF: synthetic method
   static Set resolve$default$2$(final MMRegistry1 $this) {
      return $this.resolve$default$2();
   }

   default Set resolve$default$2() {
      return .MODULE$.Set().empty();
   }

   // $FF: synthetic method
   static Map selectBestOption$(final MMRegistry1 $this, final Map options) {
      return $this.selectBestOption(options);
   }

   default Map selectBestOption(final Map options) {
      ObjectRef bestCandidates = ObjectRef.create((Set).MODULE$.Set().apply(scala.collection.immutable.Nil..MODULE$));
      options.keys().foreach((aa) -> {
         $anonfun$selectBestOption$9(bestCandidates, aa);
         return BoxedUnit.UNIT;
      });
      return options.view().filterKeys((Set)bestCandidates.elem).toMap(scala..less.colon.less..MODULE$.refl());
   }

   // $FF: synthetic method
   static boolean $anonfun$resolve$8(final Class aa) {
      return aa != null;
   }

   // $FF: synthetic method
   static boolean $anonfun$selectBestOption$10(final Class aa$5, final Class c) {
      return aa$5.isAssignableFrom(c);
   }

   // $FF: synthetic method
   static boolean $anonfun$selectBestOption$11(final Class aa$5, final Class c) {
      return c.isAssignableFrom(aa$5);
   }

   // $FF: synthetic method
   static void $anonfun$selectBestOption$9(final ObjectRef bestCandidates$3, final Class aa) {
      if (!((Set)bestCandidates$3.elem).exists((c) -> BoxesRunTime.boxToBoolean($anonfun$selectBestOption$10(aa, c)))) {
         bestCandidates$3.elem = (Set)((Set)bestCandidates$3.elem).filterNot((c) -> BoxesRunTime.boxToBoolean($anonfun$selectBestOption$11(aa, c)));
         bestCandidates$3.elem = (Set)((Set)bestCandidates$3.elem).$plus(aa);
      }

   }

   static void $init$(final MMRegistry1 $this) {
      $this.breeze$generic$MMRegistry1$_setter_$ops_$eq((HashMap)scala.collection.mutable.HashMap..MODULE$.apply(scala.collection.immutable.Nil..MODULE$));
      $this.breeze$generic$MMRegistry1$_setter_$cache_$eq(new ConcurrentHashMap());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
