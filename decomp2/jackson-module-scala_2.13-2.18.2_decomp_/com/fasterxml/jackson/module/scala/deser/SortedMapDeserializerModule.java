package com.fasterxml.jackson.module.scala.deser;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.module.scala.introspect.OrderingLocator$;
import com.fasterxml.jackson.module.scala.modifiers.MapTypeModifierModule;
import java.lang.invoke.SerializedLambda;
import scala.Tuple2;
import scala.collection.Iterable;
import scala.collection.SortedMap;
import scala.collection.SortedMapFactory;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.TreeMap;
import scala.collection.mutable.Builder;
import scala.package.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005\u00112qAA\u0002\u0011\u0002\u0007\u0005\u0001\u0003C\u0003\u001e\u0001\u0011\u0005aDA\u000eT_J$X\rZ'ba\u0012+7/\u001a:jC2L'0\u001a:N_\u0012,H.\u001a\u0006\u0003\t\u0015\tQ\u0001Z3tKJT!AB\u0004\u0002\u000bM\u001c\u0017\r\\1\u000b\u0005!I\u0011AB7pIVdWM\u0003\u0002\u000b\u0017\u00059!.Y2lg>t'B\u0001\u0007\u000e\u0003%1\u0017m\u001d;feblGNC\u0001\u000f\u0003\r\u0019w.\\\u0002\u0001'\r\u0001\u0011c\u0006\t\u0003%Ui\u0011a\u0005\u0006\u0003)%\t\u0001\u0002Z1uC\nLg\u000eZ\u0005\u0003-M\u0011a!T8ek2,\u0007C\u0001\r\u001c\u001b\u0005I\"B\u0001\u000e\u0006\u0003%iw\u000eZ5gS\u0016\u00148/\u0003\u0002\u001d3\t)R*\u00199UsB,Wj\u001c3jM&,'/T8ek2,\u0017A\u0002\u0013j]&$H\u0005F\u0001 !\t\u0001#%D\u0001\"\u0015\u00051\u0011BA\u0012\"\u0005\u0011)f.\u001b;"
)
public interface SortedMapDeserializerModule extends MapTypeModifierModule {
   // $FF: synthetic method
   static void $anonfun$$init$$1(final Module.SetupContext x$1) {
      x$1.addDeserializers(new GenericMapFactoryDeserializerResolver() {
         private final Class CLASS_DOMAIN = SortedMap.class;
         private final Iterable factories;

         public Class CLASS_DOMAIN() {
            return this.CLASS_DOMAIN;
         }

         public Iterable factories() {
            return this.factories;
         }

         public Builder builderFor(final SortedMapFactory factory, final JavaType keyType, final JavaType valueType) {
            return factory.newBuilder(OrderingLocator$.MODULE$.locate(keyType));
         }

         public {
            this.factories = this.sortFactories((IndexedSeq).MODULE$.Vector().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{new Tuple2(SortedMap.class, scala.collection.SortedMap..MODULE$), new Tuple2(scala.collection.immutable.SortedMap.class, scala.collection.immutable.SortedMap..MODULE$), new Tuple2(TreeMap.class, scala.collection.immutable.TreeMap..MODULE$), new Tuple2(scala.collection.mutable.SortedMap.class, scala.collection.mutable.SortedMap..MODULE$), new Tuple2(scala.collection.mutable.TreeMap.class, scala.collection.mutable.TreeMap..MODULE$)}))));
         }
      });
   }

   static void $init$(final SortedMapDeserializerModule $this) {
      $this.$plus$eq((x$1) -> {
         $anonfun$$init$$1(x$1);
         return BoxedUnit.UNIT;
      });
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
