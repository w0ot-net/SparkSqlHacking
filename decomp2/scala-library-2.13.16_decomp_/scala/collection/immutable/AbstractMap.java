package scala.collection.immutable;

import scala.$less$colon$less;
import scala.Function1;
import scala.Function2;
import scala.Tuple2;
import scala.collection.IterableFactory;
import scala.collection.IterableOnce;
import scala.collection.MapFactory;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00152QAA\u0002\u0002\u0002)AQA\t\u0001\u0005\u0002\r\u00121\"\u00112tiJ\f7\r^'ba*\u0011A!B\u0001\nS6lW\u000f^1cY\u0016T!AB\u0004\u0002\u0015\r|G\u000e\\3di&|gNC\u0001\t\u0003\u0015\u00198-\u00197b\u0007\u0001)2aC\t\u001d'\r\u0001AB\b\t\u0005\u001b9y1$D\u0001\u0006\u0013\t\u0011Q\u0001\u0005\u0002\u0011#1\u0001A!\u0002\n\u0001\u0005\u0004\u0019\"!A&\u0012\u0005QA\u0002CA\u000b\u0017\u001b\u00059\u0011BA\f\b\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"!F\r\n\u0005i9!aA!osB\u0011\u0001\u0003\b\u0003\u0007;\u0001!)\u0019A\n\u0003\u0003Y\u0003Ba\b\u0011\u001075\t1!\u0003\u0002\"\u0007\t\u0019Q*\u00199\u0002\rqJg.\u001b;?)\u0005!\u0003\u0003B\u0010\u0001\u001fm\u0001"
)
public abstract class AbstractMap extends scala.collection.AbstractMap implements Map {
   public MapFactory mapFactory() {
      return Map.mapFactory$(this);
   }

   public final Map toMap(final $less$colon$less ev) {
      return Map.toMap$(this, ev);
   }

   public Map withDefault(final Function1 d) {
      return Map.withDefault$(this, d);
   }

   public Map withDefaultValue(final Object d) {
      return Map.withDefaultValue$(this, d);
   }

   public final MapOps $minus(final Object key) {
      return MapOps.$minus$(this, key);
   }

   /** @deprecated */
   public MapOps $minus(final Object key1, final Object key2, final Seq keys) {
      return MapOps.$minus$(this, key1, key2, keys);
   }

   public MapOps removedAll(final IterableOnce keys) {
      return MapOps.removedAll$(this, keys);
   }

   public final MapOps $minus$minus(final IterableOnce keys) {
      return MapOps.$minus$minus$(this, keys);
   }

   public MapOps updatedWith(final Object key, final Function1 remappingFunction) {
      return MapOps.updatedWith$(this, key, remappingFunction);
   }

   public MapOps $plus(final Tuple2 kv) {
      return MapOps.$plus$(this, kv);
   }

   public MapOps transform(final Function2 f) {
      return MapOps.transform$(this, f);
   }

   public Set keySet() {
      return MapOps.keySet$(this);
   }

   public IterableFactory iterableFactory() {
      return Iterable.iterableFactory$(this);
   }
}
