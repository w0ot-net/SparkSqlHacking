package breeze.linalg;

import java.lang.invoke.SerializedLambda;
import scala.MatchError;
import scala.Tuple2;
import scala.Predef.ArrowAssoc.;
import scala.collection.Iterator;
import scala.collection.immutable.Set;
import scala.collection.mutable.Map;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Uca\u0002\f\u0018!\u0003\r\t\u0001\b\u0005\u0006\u0007\u0002!\t\u0001\u0012\u0005\u0006\u0011\u00021\t!\u0013\u0005\u00063\u00021\tA\u0017\u0005\u00067\u0002!\t\u0005\u0018\u0005\u0006A\u0002!\t!\u0019\u0005\u0006A\u0002!\t\u0001\u001a\u0005\u0006S\u0002!\tA\u001b\u0005\u0006S\u0002!\ta\u001c\u0005\u0006g\u0002!\t\u0001\u001e\u0005\u0006g\u0002!\t\u0001\u001f\u0005\u0007y\u0002!\taF?\t\u000f\u00055\u0001\u0001\"\u0011\u0002\u0010!9\u0011\u0011\u0004\u0001\u0005B\u0005m\u0001bBA\u0010\u0001\u0011\u0005\u0013\u0011\u0005\u0005\u0007\u0003O\u0001A\u0011\u0001/\t\u000f\u0005%\u0002\u0001\"\u0001\u0002\"!9\u00111\u0006\u0001\u0005\u0002\u0005=\u0001bBA\u0017\u0001\u0011\u0005\u00111\u0004\u0005\b\u0003_\u0001A\u0011AA\u0019\u0011\u001d\t\u0019\u0004\u0001C!\u0003kAq!!\u0014\u0001\t\u0003\nyE\u0001\u0007D_VtG/\u001a:3\u0019&\\WM\u0003\u0002\u00193\u00051A.\u001b8bY\u001eT\u0011AG\u0001\u0007EJ,WM_3\u0004\u0001U1Q$L\u001c;'v\u001a2\u0001\u0001\u0010%!\ty\"%D\u0001!\u0015\u0005\t\u0013!B:dC2\f\u0017BA\u0012!\u0005\u0019\te.\u001f*fMB)QE\n\u0015:y5\tq#\u0003\u0002(/\tQA+\u001a8t_Jd\u0015n[3\u0011\t}I3FN\u0005\u0003U\u0001\u0012a\u0001V;qY\u0016\u0014\u0004C\u0001\u0017.\u0019\u0001!QA\f\u0001C\u0002=\u0012!aS\u0019\u0012\u0005A\u001a\u0004CA\u00102\u0013\t\u0011\u0004EA\u0004O_RD\u0017N\\4\u0011\u0005}!\u0014BA\u001b!\u0005\r\te.\u001f\t\u0003Y]\"Q\u0001\u000f\u0001C\u0002=\u0012!a\u0013\u001a\u0011\u00051RD!B\u001e\u0001\u0005\u0004y#!\u0001,\u0011\u00051jDA\u0002 \u0001\t\u000b\u0007qH\u0001\u0003UQ&\u001c\u0018C\u0001\u0019A!\u0015)\u0013i\u000b\u001c:\u0013\t\u0011uC\u0001\u0005D_VtG/\u001a:3\u0003\u0019!\u0013N\\5uIQ\tQ\t\u0005\u0002 \r&\u0011q\t\t\u0002\u0005+:LG/\u0001\u0003eCR\fW#\u0001&\u0011\t-\u00036FU\u0007\u0002\u0019*\u0011QJT\u0001\b[V$\u0018M\u00197f\u0015\ty\u0005%\u0001\u0006d_2dWm\u0019;j_:L!!\u0015'\u0003\u00075\u000b\u0007\u000f\u0005\u0002-'\u0012)A\u000b\u0001b\u0001+\n\tA+\u0005\u00021-B!Qe\u0016\u001c:\u0013\tAvCA\u0004D_VtG/\u001a:\u0002\u000f\u0011,g-Y;miV\t\u0011(\u0001\u0003tSj,W#A/\u0011\u0005}q\u0016BA0!\u0005\rIe\u000e^\u0001\u0006CB\u0004H.\u001f\u000b\u0003s\tDQaY\u0003A\u0002!\n\u0011!\u001b\u000b\u0004s\u0015<\u0007\"\u00024\u0007\u0001\u0004Y\u0013!A6\t\u000b!4\u0001\u0019\u0001\u001c\u0002\u0005-\u0014\u0014\u0001C2p]R\f\u0017N\\:\u0015\u0005-t\u0007CA\u0010m\u0013\ti\u0007EA\u0004C_>dW-\u00198\t\u000b\u0019<\u0001\u0019A\u0016\u0015\u0007-\u0004(\u000fC\u0003r\u0011\u0001\u00071&\u0001\u0002lc!)\u0001\u000e\u0003a\u0001m\u00051Q\u000f\u001d3bi\u0016$2!R;w\u0011\u0015\u0019\u0017\u00021\u0001)\u0011\u00159\u0018\u00021\u0001:\u0003\u00051H\u0003B#zunDQ!\u001d\u0006A\u0002-BQ\u0001\u001b\u0006A\u0002YBQa\u001e\u0006A\u0002e\nA#\u001b8oKJ<U\r^(s\u000b2\u001cX-\u00169eCR,Wc\u0001@\u0002\u0002Q)q0!\u0002\u0002\bA\u0019A&!\u0001\u0005\r\u0005\r1B1\u00010\u0005\u0005i\u0005\"\u00024\f\u0001\u0004Y\u0003bBA\u0005\u0017\u0001\u0007\u00111B\u0001\u0002[B!1\nU\u0016\u0000\u00031YW-_:Ji\u0016\u0014\u0018\r^8s+\t\t\t\u0002E\u0003\u0002\u0014\u0005U\u0001&D\u0001O\u0013\r\t9B\u0014\u0002\t\u0013R,'/\u0019;pe\u0006qa/\u00197vKNLE/\u001a:bi>\u0014XCAA\u000f!\u0015\t\u0019\"!\u0006:\u0003!IG/\u001a:bi>\u0014XCAA\u0012!\u0019\t\u0019\"!\u0006\u0002&A!q$\u000b\u0015:\u0003)\t7\r^5wKNK'0Z\u0001\u000fC\u000e$\u0018N^3Ji\u0016\u0014\u0018\r^8s\u0003I\t7\r^5wK.+\u0017p]%uKJ\fGo\u001c:\u0002)\u0005\u001cG/\u001b<f-\u0006dW/Z:Ji\u0016\u0014\u0018\r^8s\u0003\u0011\u0011X\r\u001d:\u0016\u0003q\n\u0001\u0002^8TiJLgn\u001a\u000b\u0003\u0003o\u0001B!!\u000f\u0002H9!\u00111HA\"!\r\ti\u0004I\u0007\u0003\u0003\u007fQ1!!\u0011\u001c\u0003\u0019a$o\\8u}%\u0019\u0011Q\t\u0011\u0002\rA\u0013X\rZ3g\u0013\u0011\tI%a\u0013\u0003\rM#(/\u001b8h\u0015\r\t)\u0005I\u0001\u0007KF,\u0018\r\\:\u0015\u0007-\f\t\u0006\u0003\u0004\u0002TU\u0001\raM\u0001\u0003aF\u0002"
)
public interface Counter2Like extends TensorLike {
   Map data();

   Object default();

   // $FF: synthetic method
   static int size$(final Counter2Like $this) {
      return $this.size();
   }

   default int size() {
      IntRef s = IntRef.create(0);
      this.data().valuesIterator().foreach((m) -> {
         $anonfun$size$1(s, m);
         return BoxedUnit.UNIT;
      });
      return s.elem;
   }

   // $FF: synthetic method
   static Object apply$(final Counter2Like $this, final Tuple2 i) {
      return $this.apply(i);
   }

   default Object apply(final Tuple2 i) {
      return this.apply(i._1(), i._2());
   }

   // $FF: synthetic method
   static Object apply$(final Counter2Like $this, final Object k, final Object k2) {
      return $this.apply(k, k2);
   }

   default Object apply(final Object k, final Object k2) {
      return this.data().get(k).map((t) -> t.apply(k2)).getOrElse(() -> this.default());
   }

   // $FF: synthetic method
   static boolean contains$(final Counter2Like $this, final Object k) {
      return $this.contains(k);
   }

   default boolean contains(final Object k) {
      return this.data().contains(k);
   }

   // $FF: synthetic method
   static boolean contains$(final Counter2Like $this, final Object k1, final Object k2) {
      return $this.contains(k1, k2);
   }

   default boolean contains(final Object k1, final Object k2) {
      return this.data().contains(k1) && ((CounterLike)this.data().apply(k1)).contains(k2);
   }

   // $FF: synthetic method
   static void update$(final Counter2Like $this, final Tuple2 i, final Object v) {
      $this.update(i, v);
   }

   default void update(final Tuple2 i, final Object v) {
      this.update(i._1(), i._2(), v);
   }

   // $FF: synthetic method
   static void update$(final Counter2Like $this, final Object k1, final Object k2, final Object v) {
      $this.update(k1, k2, v);
   }

   default void update(final Object k1, final Object k2, final Object v) {
      ((CounterLike)this.innerGetOrElseUpdate(k1, this.data())).update(k2, v);
   }

   // $FF: synthetic method
   static Object innerGetOrElseUpdate$(final Counter2Like $this, final Object k, final Map m) {
      return $this.innerGetOrElseUpdate(k, m);
   }

   default Object innerGetOrElseUpdate(final Object k, final Map m) {
      return m.getOrElseUpdate(k, () -> m.default(k));
   }

   // $FF: synthetic method
   static Iterator keysIterator$(final Counter2Like $this) {
      return $this.keysIterator();
   }

   default Iterator keysIterator() {
      return this.data().iterator().withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$keysIterator$1(check$ifrefutable$1))).flatMap((x$1) -> {
         if (x$1 != null) {
            Object k1 = x$1._1();
            Counter m = (Counter)x$1._2();
            Iterator var1 = m.keysIterator().map((k2) -> new Tuple2(k1, k2));
            return var1;
         } else {
            throw new MatchError(x$1);
         }
      });
   }

   // $FF: synthetic method
   static Iterator valuesIterator$(final Counter2Like $this) {
      return $this.valuesIterator();
   }

   default Iterator valuesIterator() {
      return this.data().valuesIterator().flatMap((m) -> m.valuesIterator().map((v) -> v));
   }

   // $FF: synthetic method
   static Iterator iterator$(final Counter2Like $this) {
      return $this.iterator();
   }

   default Iterator iterator() {
      return this.data().iterator().withFilter((check$ifrefutable$2) -> BoxesRunTime.boxToBoolean($anonfun$iterator$1(check$ifrefutable$2))).flatMap((x$3) -> {
         if (x$3 != null) {
            Object k1 = x$3._1();
            Counter m = (Counter)x$3._2();
            Iterator var1 = m.iterator().withFilter((check$ifrefutable$3) -> BoxesRunTime.boxToBoolean($anonfun$iterator$3(check$ifrefutable$3))).map((x$2) -> {
               if (x$2 != null) {
                  Object k2 = x$2._1();
                  Object v = x$2._2();
                  Tuple2 var2 = .MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(new Tuple2(k1, k2)), v);
                  return var2;
               } else {
                  throw new MatchError(x$2);
               }
            });
            return var1;
         } else {
            throw new MatchError(x$3);
         }
      });
   }

   // $FF: synthetic method
   static int activeSize$(final Counter2Like $this) {
      return $this.activeSize();
   }

   default int activeSize() {
      return this.size();
   }

   // $FF: synthetic method
   static Iterator activeIterator$(final Counter2Like $this) {
      return $this.activeIterator();
   }

   default Iterator activeIterator() {
      return this.iterator();
   }

   // $FF: synthetic method
   static Iterator activeKeysIterator$(final Counter2Like $this) {
      return $this.activeKeysIterator();
   }

   default Iterator activeKeysIterator() {
      return this.keysIterator();
   }

   // $FF: synthetic method
   static Iterator activeValuesIterator$(final Counter2Like $this) {
      return $this.activeValuesIterator();
   }

   default Iterator activeValuesIterator() {
      return this.valuesIterator();
   }

   // $FF: synthetic method
   static Counter2 repr$(final Counter2Like $this) {
      return $this.repr();
   }

   default Counter2 repr() {
      return (Counter2)this;
   }

   // $FF: synthetic method
   static String toString$(final Counter2Like $this) {
      return $this.toString();
   }

   default String toString() {
      return this.data().iterator().map((x0$1) -> {
         if (x0$1 != null) {
            Object k1 = x0$1._1();
            Counter c = (Counter)x0$1._2();
            String var1 = (new StringBuilder(4)).append(k1).append(" -> ").append(c.toString()).toString();
            return var1;
         } else {
            throw new MatchError(x0$1);
         }
      }).mkString("Counter2(", ",\n", ")");
   }

   // $FF: synthetic method
   static boolean equals$(final Counter2Like $this, final Object p1) {
      return $this.equals(p1);
   }

   default boolean equals(final Object p1) {
      boolean var2;
      if (p1 instanceof Counter2) {
         boolean var6;
         label21: {
            label20: {
               Counter2 var4 = (Counter2)p1;
               Set var10000 = var4.activeIterator().toSet();
               Set var5 = this.activeIterator().toSet();
               if (var10000 == null) {
                  if (var5 == null) {
                     break label20;
                  }
               } else if (var10000.equals(var5)) {
                  break label20;
               }

               var6 = false;
               break label21;
            }

            var6 = true;
         }

         var2 = var6;
      } else {
         var2 = false;
      }

      return var2;
   }

   // $FF: synthetic method
   static void $anonfun$size$1(final IntRef s$1, final Counter m) {
      s$1.elem += m.size();
   }

   // $FF: synthetic method
   static boolean $anonfun$keysIterator$1(final Tuple2 check$ifrefutable$1) {
      boolean var1;
      if (check$ifrefutable$1 != null) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   // $FF: synthetic method
   static boolean $anonfun$iterator$1(final Tuple2 check$ifrefutable$2) {
      boolean var1;
      if (check$ifrefutable$2 != null) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   // $FF: synthetic method
   static boolean $anonfun$iterator$3(final Tuple2 check$ifrefutable$3) {
      boolean var1;
      if (check$ifrefutable$3 != null) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   static void $init$(final Counter2Like $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
