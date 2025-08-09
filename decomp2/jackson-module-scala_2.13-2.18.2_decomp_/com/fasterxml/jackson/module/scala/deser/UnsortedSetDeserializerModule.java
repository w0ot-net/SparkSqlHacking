package com.fasterxml.jackson.module.scala.deser;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.CollectionLikeType;
import com.fasterxml.jackson.module.scala.modifiers.ScalaTypeModifierModule;
import java.lang.invoke.SerializedLambda;
import scala.Tuple2;
import scala.None.;
import scala.collection.Iterable;
import scala.collection.IterableFactory;
import scala.collection.Set;
import scala.collection.SortedSet;
import scala.collection.immutable.HashSet;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.ListSet;
import scala.collection.mutable.Builder;
import scala.collection.mutable.LinkedHashSet;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005I2qa\u0001\u0003\u0011\u0002\u0007\u0005\u0011\u0003C\u0003\u001f\u0001\u0011\u0005q\u0004C\u0003&\u0001\u0011\u0005cEA\u000fV]N|'\u000f^3e'\u0016$H)Z:fe&\fG.\u001b>fe6{G-\u001e7f\u0015\t)a!A\u0003eKN,'O\u0003\u0002\b\u0011\u0005)1oY1mC*\u0011\u0011BC\u0001\u0007[>$W\u000f\\3\u000b\u0005-a\u0011a\u00026bG.\u001cxN\u001c\u0006\u0003\u001b9\t\u0011BZ1ti\u0016\u0014\b0\u001c7\u000b\u0003=\t1aY8n\u0007\u0001\u00192\u0001\u0001\n\u0019!\t\u0019b#D\u0001\u0015\u0015\t)\"\"\u0001\u0005eCR\f'-\u001b8e\u0013\t9BC\u0001\u0004N_\u0012,H.\u001a\t\u00033qi\u0011A\u0007\u0006\u00037\u0019\t\u0011\"\\8eS\u001aLWM]:\n\u0005uQ\"aF*dC2\fG+\u001f9f\u001b>$\u0017NZ5fe6{G-\u001e7f\u0003\u0019!\u0013N\\5uIQ\t\u0001\u0005\u0005\u0002\"G5\t!EC\u0001\b\u0013\t!#E\u0001\u0003V]&$\u0018!D4fi6{G-\u001e7f\u001d\u0006lW\rF\u0001(!\tAsF\u0004\u0002*[A\u0011!FI\u0007\u0002W)\u0011A\u0006E\u0001\u0007yI|w\u000e\u001e \n\u00059\u0012\u0013A\u0002)sK\u0012,g-\u0003\u00021c\t11\u000b\u001e:j]\u001eT!A\f\u0012"
)
public interface UnsortedSetDeserializerModule extends ScalaTypeModifierModule {
   // $FF: synthetic method
   static String getModuleName$(final UnsortedSetDeserializerModule $this) {
      return $this.getModuleName();
   }

   default String getModuleName() {
      return "UnsortedSetDeserializerModule";
   }

   // $FF: synthetic method
   static void $anonfun$$init$$1(final Module.SetupContext x$1) {
      x$1.addDeserializers(new GenericFactoryDeserializerResolver() {
         private final Class CLASS_DOMAIN = Set.class;
         private final Class IGNORE_CLASS_DOMAIN = SortedSet.class;
         private final Iterable factories;

         public Class CLASS_DOMAIN() {
            return this.CLASS_DOMAIN;
         }

         private Class IGNORE_CLASS_DOMAIN() {
            return this.IGNORE_CLASS_DOMAIN;
         }

         public Iterable factories() {
            return this.factories;
         }

         public Builder builderFor(final IterableFactory cf, final JavaType javaType) {
            return cf.newBuilder();
         }

         public JsonDeserializer findCollectionLikeDeserializer(final CollectionLikeType collectionType, final DeserializationConfig config, final BeanDescription beanDesc, final TypeDeserializer elementTypeDeserializer, final JsonDeserializer elementDeserializer) {
            Class rawClass = collectionType.getRawClass();
            return this.IGNORE_CLASS_DOMAIN().isAssignableFrom(rawClass) ? (JsonDeserializer).MODULE$.orNull(scala..less.colon.less..MODULE$.refl()) : super.findCollectionLikeDeserializer(collectionType, config, beanDesc, elementTypeDeserializer, elementDeserializer);
         }

         public {
            this.factories = this.sortFactories((IndexedSeq)scala.package..MODULE$.Vector().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{new Tuple2(Set.class, scala.collection.Set..MODULE$), new Tuple2(HashSet.class, scala.collection.immutable.HashSet..MODULE$), new Tuple2(ListSet.class, scala.collection.immutable.ListSet..MODULE$), new Tuple2(scala.collection.immutable.Set.class, scala.collection.immutable.Set..MODULE$), new Tuple2(scala.collection.mutable.HashSet.class, scala.collection.mutable.HashSet..MODULE$), new Tuple2(LinkedHashSet.class, scala.collection.mutable.LinkedHashSet..MODULE$), new Tuple2(scala.collection.mutable.Set.class, scala.collection.mutable.Set..MODULE$)}))));
         }
      });
   }

   static void $init$(final UnsortedSetDeserializerModule $this) {
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
