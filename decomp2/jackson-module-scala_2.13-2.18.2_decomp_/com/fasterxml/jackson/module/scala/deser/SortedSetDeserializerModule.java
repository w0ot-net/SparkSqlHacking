package com.fasterxml.jackson.module.scala.deser;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.CollectionLikeType;
import com.fasterxml.jackson.module.scala.introspect.OrderingLocator$;
import com.fasterxml.jackson.module.scala.modifiers.ScalaTypeModifierModule;
import java.lang.invoke.SerializedLambda;
import scala.Tuple2;
import scala.None.;
import scala.collection.Iterable;
import scala.collection.SortedIterableFactory;
import scala.collection.SortedSet;
import scala.collection.immutable.BitSet;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.TreeSet;
import scala.collection.mutable.Builder;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005I2qa\u0001\u0003\u0011\u0002\u0007\u0005\u0011\u0003C\u0003\u001f\u0001\u0011\u0005q\u0004C\u0003&\u0001\u0011\u0005cEA\u000eT_J$X\rZ*fi\u0012+7/\u001a:jC2L'0\u001a:N_\u0012,H.\u001a\u0006\u0003\u000b\u0019\tQ\u0001Z3tKJT!a\u0002\u0005\u0002\u000bM\u001c\u0017\r\\1\u000b\u0005%Q\u0011AB7pIVdWM\u0003\u0002\f\u0019\u00059!.Y2lg>t'BA\u0007\u000f\u0003%1\u0017m\u001d;feblGNC\u0001\u0010\u0003\r\u0019w.\\\u0002\u0001'\r\u0001!\u0003\u0007\t\u0003'Yi\u0011\u0001\u0006\u0006\u0003+)\t\u0001\u0002Z1uC\nLg\u000eZ\u0005\u0003/Q\u0011a!T8ek2,\u0007CA\r\u001d\u001b\u0005Q\"BA\u000e\u0007\u0003%iw\u000eZ5gS\u0016\u00148/\u0003\u0002\u001e5\t92kY1mCRK\b/Z'pI&4\u0017.\u001a:N_\u0012,H.Z\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003\u0001\u0002\"!I\u0012\u000e\u0003\tR\u0011aB\u0005\u0003I\t\u0012A!\u00168ji\u0006iq-\u001a;N_\u0012,H.\u001a(b[\u0016$\u0012a\n\t\u0003Q=r!!K\u0017\u0011\u0005)\u0012S\"A\u0016\u000b\u00051\u0002\u0012A\u0002\u001fs_>$h(\u0003\u0002/E\u00051\u0001K]3eK\u001aL!\u0001M\u0019\u0003\rM#(/\u001b8h\u0015\tq#\u0005"
)
public interface SortedSetDeserializerModule extends ScalaTypeModifierModule {
   // $FF: synthetic method
   static String getModuleName$(final SortedSetDeserializerModule $this) {
      return $this.getModuleName();
   }

   default String getModuleName() {
      return "SortedSetDeserializerModule";
   }

   // $FF: synthetic method
   static void $anonfun$$init$$1(final Module.SetupContext x$1) {
      x$1.addDeserializers(new GenericFactoryDeserializerResolver() {
         private final Class IMMUTABLE_BITSET_CLASS = BitSet.class;
         private final Class MUTABLE_BITSET_CLASS = scala.collection.mutable.BitSet.class;
         private final Class CLASS_DOMAIN = SortedSet.class;
         private final Iterable factories;

         private Class IMMUTABLE_BITSET_CLASS() {
            return this.IMMUTABLE_BITSET_CLASS;
         }

         private Class MUTABLE_BITSET_CLASS() {
            return this.MUTABLE_BITSET_CLASS;
         }

         public Class CLASS_DOMAIN() {
            return this.CLASS_DOMAIN;
         }

         public Iterable factories() {
            return this.factories;
         }

         public Builder builderFor(final SortedIterableFactory cf, final JavaType valueType) {
            return cf.newBuilder(OrderingLocator$.MODULE$.locate(valueType));
         }

         public JsonDeserializer findCollectionLikeDeserializer(final CollectionLikeType collectionType, final DeserializationConfig config, final BeanDescription beanDesc, final TypeDeserializer elementTypeDeserializer, final JsonDeserializer elementDeserializer) {
            Class rawClass = collectionType.getRawClass();
            if (this.IMMUTABLE_BITSET_CLASS().isAssignableFrom(rawClass)) {
               return (JsonDeserializer).MODULE$.orNull(scala..less.colon.less..MODULE$.refl());
            } else {
               return this.MUTABLE_BITSET_CLASS().isAssignableFrom(rawClass) ? (JsonDeserializer).MODULE$.orNull(scala..less.colon.less..MODULE$.refl()) : super.findCollectionLikeDeserializer(collectionType, config, beanDesc, elementTypeDeserializer, elementDeserializer);
            }
         }

         public {
            this.factories = this.sortFactories((IndexedSeq)scala.package..MODULE$.Vector().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{new Tuple2(SortedSet.class, scala.collection.SortedSet..MODULE$), new Tuple2(TreeSet.class, scala.collection.immutable.TreeSet..MODULE$), new Tuple2(scala.collection.immutable.SortedSet.class, scala.collection.immutable.SortedSet..MODULE$), new Tuple2(scala.collection.mutable.TreeSet.class, scala.collection.mutable.TreeSet..MODULE$), new Tuple2(scala.collection.mutable.SortedSet.class, scala.collection.mutable.SortedSet..MODULE$)}))));
         }
      });
   }

   static void $init$(final SortedSetDeserializerModule $this) {
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
