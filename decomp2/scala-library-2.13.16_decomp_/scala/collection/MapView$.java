package scala.collection;

import java.lang.invoke.SerializedLambda;
import scala.$less$colon$less$;
import scala.Function1;
import scala.None$;
import scala.Option;
import scala.Tuple2;
import scala.collection.mutable.Builder;
import scala.collection.mutable.HashMap$;
import scala.runtime.ModuleSerializationProxy;

public final class MapView$ implements MapViewFactory {
   public static final MapView$ MODULE$ = new MapView$();
   private static final MapView EmptyMapView;

   static {
      MapView$ var10000 = MODULE$;
      var10000 = MODULE$;
      EmptyMapView = new AbstractMapView() {
         public Option get(final Object key) {
            return None$.MODULE$;
         }

         public Iterator iterator() {
            Iterator$ var10000 = Iterator$.MODULE$;
            return Iterator$.scala$collection$Iterator$$_empty;
         }

         public int knownSize() {
            return 0;
         }

         public boolean isEmpty() {
            return true;
         }

         public MapView filterKeys(final Function1 p) {
            return this;
         }

         public MapView mapValues(final Function1 f) {
            return this;
         }

         public MapView filter(final Function1 pred) {
            return this;
         }

         public MapView filterNot(final Function1 pred) {
            return this;
         }

         public Tuple2 partition(final Function1 p) {
            return new Tuple2(this, this);
         }
      };
   }

   public Factory mapFactory() {
      return MapFactory.mapFactory$(this);
   }

   private MapView EmptyMapView() {
      return EmptyMapView;
   }

   public Builder newBuilder() {
      return HashMap$.MODULE$.newBuilder().mapResult((x$1) -> x$1.view());
   }

   public MapView empty() {
      return this.EmptyMapView();
   }

   public View from(final IterableOnce it) {
      return View$.MODULE$.from(it);
   }

   public MapView from(final MapOps it) {
      return (MapView)(it instanceof MapView ? (MapView)it : new MapView.Id(it));
   }

   public MapView apply(final scala.collection.immutable.Seq elems) {
      return this.from((MapOps)elems.toMap($less$colon$less$.MODULE$.refl()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(MapView$.class);
   }

   private MapView$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
