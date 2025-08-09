package algebra.instances;

import algebra.ring.Semiring;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005U2qa\u0001\u0003\u0011\u0002\u0007\u0005\u0011\u0002C\u0003\u0015\u0001\u0011\u0005Q\u0003C\u0003\u001a\u0001\u0011\r!DA\u0007NCBLen\u001d;b]\u000e,7O\r\u0006\u0003\u000b\u0019\t\u0011\"\u001b8ti\u0006t7-Z:\u000b\u0003\u001d\tq!\u00197hK\n\u0014\u0018m\u0001\u0001\u0014\u0007\u0001Q\u0001\u0003\u0005\u0002\f\u001d5\tABC\u0001\u000e\u0003\u0015\u00198-\u00197b\u0013\tyAB\u0001\u0004B]f\u0014VM\u001a\t\u0003#Ii\u0011\u0001B\u0005\u0003'\u0011\u0011Q\"T1q\u0013:\u001cH/\u00198dKN\f\u0014A\u0002\u0013j]&$H\u0005F\u0001\u0017!\tYq#\u0003\u0002\u0019\u0019\t!QK\\5u\u0003-i\u0017\r]*f[&\u0014\u0018N\\4\u0016\u0007m\t3\u0006\u0006\u0002\u001d[A!\u0011#H\u0010+\u0013\tqBAA\u0006NCB\u001cV-\\5sS:<\u0007C\u0001\u0011\"\u0019\u0001!QA\t\u0002C\u0002\r\u0012\u0011aS\t\u0003I\u001d\u0002\"aC\u0013\n\u0005\u0019b!a\u0002(pi\"Lgn\u001a\t\u0003\u0017!J!!\u000b\u0007\u0003\u0007\u0005s\u0017\u0010\u0005\u0002!W\u0011)AF\u0001b\u0001G\t\ta\u000bC\u0004/\u0005\u0005\u0005\t9A\u0018\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$\u0013\u0007E\u00021g)j\u0011!\r\u0006\u0003e\u0019\tAA]5oO&\u0011A'\r\u0002\t'\u0016l\u0017N]5oO\u0002"
)
public interface MapInstances2 extends MapInstances1 {
   // $FF: synthetic method
   static MapSemiring mapSemiring$(final MapInstances2 $this, final Semiring evidence$1) {
      return $this.mapSemiring(evidence$1);
   }

   default MapSemiring mapSemiring(final Semiring evidence$1) {
      return new MapSemiring(evidence$1);
   }

   static void $init$(final MapInstances2 $this) {
   }
}
