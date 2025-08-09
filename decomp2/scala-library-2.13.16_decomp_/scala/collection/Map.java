package scala.collection;

import java.lang.invoke.SerializedLambda;
import scala.Equals;
import scala.Tuple2;
import scala.collection.mutable.Builder;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.util.hashing.MurmurHash3$;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Mca\u0002\n\u0014!\u0003\r\t\u0001\u0007\u0005\u0006\u007f\u0001!\t\u0001\u0011\u0005\u0006\t\u0002!\t!\u0012\u0005\u0006\u0013\u0002!\tA\u0013\u0005\u0006!\u0002!\t%\u0015\u0005\u0006)\u0002!\t%\u0016\u0005\u00063\u00021\tA\u0017\u0005\u00063\u00021\ta\u001a\u0005\u0007i\u0002\u0001K\u0011K;\t\u000f\u0005\r\u0001\u0001\"\u0011\u0002\u0006\u001d9\u0011qA\n\t\u0002\u0005%aA\u0002\n\u0014\u0011\u0003\tY\u0001C\u0004\u0002\u001c-!\t!!\b\t\u0013\u0005}1B1A\u0005\n\u0005\u0005\u0002bBA\u0012\u0017\u0001\u0006IA\u0007\u0005\n\u0003KY!\u0019!C\u0005\u0003OA\u0001\"a\f\fA\u0003%\u0011\u0011\u0006\u0005\n\u0003cY\u0011\u0011!C\u0005\u0003g\u00111!T1q\u0015\t!R#\u0001\u0006d_2dWm\u0019;j_:T\u0011AF\u0001\u0006g\u000e\fG.Y\u0002\u0001+\rIr%M\n\u0007\u0001iq2\u0007\u000f\u001f\u0011\u0005maR\"A\u000b\n\u0005u)\"AB!osJ+g\rE\u0002 A\tj\u0011aE\u0005\u0003CM\u0011\u0001\"\u0013;fe\u0006\u0014G.\u001a\t\u00057\r*\u0003'\u0003\u0002%+\t1A+\u001e9mKJ\u0002\"AJ\u0014\r\u0001\u0011)\u0001\u0006\u0001b\u0001S\t\t1*\u0005\u0002+[A\u00111dK\u0005\u0003YU\u0011qAT8uQ&tw\r\u0005\u0002\u001c]%\u0011q&\u0006\u0002\u0004\u0003:L\bC\u0001\u00142\t\u0019\u0011\u0004\u0001\"b\u0001S\t\ta\u000b\u0005\u0004 i\u0015\u0002dgN\u0005\u0003kM\u0011a!T1q\u001fB\u001c\bCA\u0010\u0001!\u0011y\u0002!\n\u0019\u0011\r}IT\u0005\r\u001c<\u0013\tQ4C\u0001\nNCB4\u0015m\u0019;pef$UMZ1vYR\u001c\bCA\u0010!!\tYR(\u0003\u0002?+\t1Q)];bYN\fa\u0001J5oSR$C#A!\u0011\u0005m\u0011\u0015BA\"\u0016\u0005\u0011)f.\u001b;\u0002\u00155\f\u0007OR1di>\u0014\u00180F\u0001G!\ryrIN\u0005\u0003\u0011N\u0011!\"T1q\r\u0006\u001cGo\u001c:z\u0003!\u0019\u0017M\\#rk\u0006dGCA&O!\tYB*\u0003\u0002N+\t9!i\\8mK\u0006t\u0007\"B(\u0004\u0001\u0004i\u0013\u0001\u0002;iCR\fa!Z9vC2\u001cHCA&S\u0011\u0015\u0019F\u00011\u0001.\u0003\u0005y\u0017\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0003Y\u0003\"aG,\n\u0005a+\"aA%oi\u00061A%\\5okN$\"aN.\t\u000bq3\u0001\u0019A\u0013\u0002\u0007-,\u0017\u0010\u000b\u0004\u0007=\u0006\u0014G-\u001a\t\u00037}K!\u0001Y\u000b\u0003\u0015\u0011,\u0007O]3dCR,G-A\u0004nKN\u001c\u0018mZ3\"\u0003\r\fA%V:fA5\u0002sN\u001d\u0011sK6|g/\u001a3!_:\u0004\u0013M\u001c\u0011j[6,H/\u00192mK\u0002j\u0015\r]\u0001\u0006g&t7-Z\u0011\u0002M\u00061!GL\u00194]A\"Ba\u000e5kY\")\u0011n\u0002a\u0001K\u0005!1.Z=2\u0011\u0015Yw\u00011\u0001&\u0003\u0011YW-\u001f\u001a\t\u000b5<\u0001\u0019\u00018\u0002\t-,\u0017p\u001d\t\u00047=,\u0013B\u00019\u0016\u0005)a$/\u001a9fCR,GM\u0010\u0015\u0007\u000fy\u000b'\u000fZ3\"\u0003M\f\u0001&V:fA5j\u0003e\u001c:!e\u0016lwN^3e\u00032d\u0007e\u001c8!C:\u0004\u0013.\\7vi\u0006\u0014G.\u001a\u0011NCB\fAb\u001d;sS:<\u0007K]3gSb,\u0012A\u001e\t\u0003ozt!\u0001\u001f?\u0011\u0005e,R\"\u0001>\u000b\u0005m<\u0012A\u0002\u001fs_>$h(\u0003\u0002~+\u00051\u0001K]3eK\u001aL1a`A\u0001\u0005\u0019\u0019FO]5oO*\u0011Q0F\u0001\ti>\u001cFO]5oOR\ta/A\u0002NCB\u0004\"aH\u0006\u0014\u0007-\ti\u0001E\u0003\u0002\u0010\u0005UaGD\u0002 \u0003#I1!a\u0005\u0014\u0003)i\u0015\r\u001d$bGR|'/_\u0005\u0005\u0003/\tIB\u0001\u0005EK2,w-\u0019;f\u0015\r\t\u0019bE\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005\u0005%\u0011a\u0004#fM\u0006,H\u000e^*f]RLg.\u001a7\u0016\u0003i\t\u0001\u0003R3gCVdGoU3oi&tW\r\u001c\u0011\u0002#\u0011+g-Y;miN+g\u000e^5oK24e.\u0006\u0002\u0002*A!1$a\u000b\u001b\u0013\r\ti#\u0006\u0002\n\rVt7\r^5p]B\n!\u0003R3gCVdGoU3oi&tW\r\u001c$oA\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u0011Q\u0007\t\u0005\u0003o\t\t%\u0004\u0002\u0002:)!\u00111HA\u001f\u0003\u0011a\u0017M\\4\u000b\u0005\u0005}\u0012\u0001\u00026bm\u0006LA!a\u0011\u0002:\t1qJ\u00196fGRDsaCA$\u0003\u001b\ny\u0005E\u0002\u001c\u0003\u0013J1!a\u0013\u0016\u0005A\u0019VM]5bYZ+'o]5p]VKE)A\u0003wC2,XMH\u0001\u0004Q\u001dQ\u0011qIA'\u0003\u001f\u0002"
)
public interface Map extends Iterable, MapFactoryDefaults, Equals {
   static Builder newBuilder() {
      return Map$.MODULE$.newBuilder();
   }

   static Object from(final IterableOnce it) {
      return Map$.MODULE$.from(it);
   }

   // $FF: synthetic method
   static MapFactory mapFactory$(final Map $this) {
      return $this.mapFactory();
   }

   default MapFactory mapFactory() {
      return Map$.MODULE$;
   }

   // $FF: synthetic method
   static boolean canEqual$(final Map $this, final Object that) {
      return $this.canEqual(that);
   }

   default boolean canEqual(final Object that) {
      return true;
   }

   // $FF: synthetic method
   static boolean equals$(final Map $this, final Object o) {
      return $this.equals(o);
   }

   default boolean equals(final Object o) {
      if (this != o) {
         boolean var10000;
         label29: {
            if (o instanceof Map) {
               Map var2 = (Map)o;
               if (var2.canEqual(this)) {
                  if (this.size() == var2.size()) {
                     try {
                        var10000 = this.forall((kv) -> BoxesRunTime.boxToBoolean($anonfun$equals$1(var2, kv)));
                     } catch (ClassCastException var3) {
                        var10000 = false;
                     }

                     if (var10000) {
                        var10000 = true;
                        break label29;
                     }
                  }

                  var10000 = false;
                  break label29;
               }
            }

            var10000 = false;
         }

         if (!var10000) {
            return false;
         }
      }

      return true;
   }

   // $FF: synthetic method
   static int hashCode$(final Map $this) {
      return $this.hashCode();
   }

   default int hashCode() {
      return MurmurHash3$.MODULE$.mapHash(this);
   }

   /** @deprecated */
   Map $minus(final Object key);

   /** @deprecated */
   Map $minus(final Object key1, final Object key2, final scala.collection.immutable.Seq keys);

   // $FF: synthetic method
   static String stringPrefix$(final Map $this) {
      return $this.stringPrefix();
   }

   default String stringPrefix() {
      return "Map";
   }

   // $FF: synthetic method
   static String toString$(final Map $this) {
      return $this.toString();
   }

   default String toString() {
      return Iterable.toString$(this);
   }

   // $FF: synthetic method
   static boolean $anonfun$equals$1(final Map x2$1, final Tuple2 kv) {
      return BoxesRunTime.equals(x2$1.getOrElse(kv._1(), Map$.MODULE$.scala$collection$Map$$DefaultSentinelFn()), kv._2());
   }

   static void $init$(final Map $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
