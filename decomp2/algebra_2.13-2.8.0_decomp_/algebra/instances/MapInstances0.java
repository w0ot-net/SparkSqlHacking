package algebra.instances;

import algebra.ring.AdditiveSemigroup;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005I2qa\u0001\u0003\u0011\u0002\u0007\u0005\u0011\u0002C\u0003\u0011\u0001\u0011\u0005\u0011\u0003C\u0003\u0016\u0001\u0011\raCA\u0007NCBLen\u001d;b]\u000e,7\u000f\r\u0006\u0003\u000b\u0019\t\u0011\"\u001b8ti\u0006t7-Z:\u000b\u0003\u001d\tq!\u00197hK\n\u0014\u0018m\u0001\u0001\u0014\u0005\u0001Q\u0001CA\u0006\u000f\u001b\u0005a!\"A\u0007\u0002\u000bM\u001c\u0017\r\\1\n\u0005=a!AB!osJ+g-\u0001\u0004%S:LG\u000f\n\u000b\u0002%A\u00111bE\u0005\u0003)1\u0011A!\u00168ji\u0006\tR.\u00199BI\u0012LG/\u001b<f\u001b>tw.\u001b3\u0016\u0007]q\u0002\u0006\u0006\u0002\u0019UA!\u0011D\u0007\u000f(\u001b\u0005!\u0011BA\u000e\u0005\u0005Ei\u0015\r]!eI&$\u0018N^3N_:|\u0017\u000e\u001a\t\u0003;ya\u0001\u0001B\u0003 \u0005\t\u0007\u0001EA\u0001L#\t\tC\u0005\u0005\u0002\fE%\u00111\u0005\u0004\u0002\b\u001d>$\b.\u001b8h!\tYQ%\u0003\u0002'\u0019\t\u0019\u0011I\\=\u0011\u0005uAC!B\u0015\u0003\u0005\u0004\u0001#!\u0001,\t\u000f-\u0012\u0011\u0011!a\u0002Y\u0005QQM^5eK:\u001cW\r\n\u001a\u0011\u00075\u0002t%D\u0001/\u0015\tyc!\u0001\u0003sS:<\u0017BA\u0019/\u0005E\tE\rZ5uSZ,7+Z7jOJ|W\u000f\u001d"
)
public interface MapInstances0 {
   // $FF: synthetic method
   static MapAdditiveMonoid mapAdditiveMonoid$(final MapInstances0 $this, final AdditiveSemigroup evidence$2) {
      return $this.mapAdditiveMonoid(evidence$2);
   }

   default MapAdditiveMonoid mapAdditiveMonoid(final AdditiveSemigroup evidence$2) {
      return new MapAdditiveMonoid(evidence$2);
   }

   static void $init$(final MapInstances0 $this) {
   }
}
