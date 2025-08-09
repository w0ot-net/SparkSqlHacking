package com.fasterxml.jackson.module.scala.deser;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.MapLikeType;
import com.fasterxml.jackson.module.scala.modifiers.MapTypeModifierModule;
import java.lang.invoke.SerializedLambda;
import scala.Tuple2;
import scala.collection.Map;
import scala.collection.MapFactory;
import scala.collection.concurrent.TrieMap;
import scala.collection.immutable.HashMap;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.ListMap;
import scala.collection.immutable.Seq;
import scala.collection.immutable.TreeSeqMap;
import scala.collection.mutable.Builder;
import scala.collection.mutable.LinkedHashMap;
import scala.package.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005\u00112qAA\u0002\u0011\u0002\u0007\u0005\u0001\u0003C\u0003\u001e\u0001\u0011\u0005aDA\u000fV]N|'\u000f^3e\u001b\u0006\u0004H)Z:fe&\fG.\u001b>fe6{G-\u001e7f\u0015\t!Q!A\u0003eKN,'O\u0003\u0002\u0007\u000f\u0005)1oY1mC*\u0011\u0001\"C\u0001\u0007[>$W\u000f\\3\u000b\u0005)Y\u0011a\u00026bG.\u001cxN\u001c\u0006\u0003\u00195\t\u0011BZ1ti\u0016\u0014\b0\u001c7\u000b\u00039\t1aY8n\u0007\u0001\u00192\u0001A\t\u0018!\t\u0011R#D\u0001\u0014\u0015\t!\u0012\"\u0001\u0005eCR\f'-\u001b8e\u0013\t12C\u0001\u0004N_\u0012,H.\u001a\t\u00031mi\u0011!\u0007\u0006\u00035\u0015\t\u0011\"\\8eS\u001aLWM]:\n\u0005qI\"!F'baRK\b/Z'pI&4\u0017.\u001a:N_\u0012,H.Z\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003}\u0001\"\u0001\t\u0012\u000e\u0003\u0005R\u0011AB\u0005\u0003G\u0005\u0012A!\u00168ji\u0002"
)
public interface UnsortedMapDeserializerModule extends MapTypeModifierModule {
   // $FF: synthetic method
   static void $anonfun$$init$$1(final Module.SetupContext x$1) {
      x$1.addDeserializers(new GenericMapFactoryDeserializerResolver() {
         private final Class CLASS_DOMAIN = Map.class;
         private final Seq factories;

         public Class CLASS_DOMAIN() {
            return this.CLASS_DOMAIN;
         }

         public Seq factories() {
            return this.factories;
         }

         public Builder builderFor(final MapFactory factory, final JavaType keyType, final JavaType valueType) {
            return factory.newBuilder();
         }

         public JsonDeserializer findMapLikeDeserializer(final MapLikeType theType, final DeserializationConfig config, final BeanDescription beanDesc, final KeyDeserializer keyDeserializer, final TypeDeserializer elementTypeDeserializer, final JsonDeserializer elementDeserializer) {
            JsonDeserializer deserializer = LongMapDeserializerResolver$.MODULE$.findMapLikeDeserializer(theType, config, beanDesc, keyDeserializer, elementTypeDeserializer, elementDeserializer);
            if (deserializer == null) {
               deserializer = IntMapDeserializerResolver$.MODULE$.findMapLikeDeserializer(theType, config, beanDesc, keyDeserializer, elementTypeDeserializer, elementDeserializer);
               if (deserializer == null) {
                  deserializer = super.findMapLikeDeserializer(theType, config, beanDesc, keyDeserializer, elementTypeDeserializer, elementDeserializer);
               }
            }

            return deserializer;
         }

         public {
            this.factories = this.sortFactories((IndexedSeq).MODULE$.Vector().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{new Tuple2(Map.class, scala.collection.Map..MODULE$), new Tuple2(HashMap.class, scala.collection.immutable.HashMap..MODULE$), new Tuple2(ListMap.class, scala.collection.immutable.ListMap..MODULE$), new Tuple2(scala.collection.immutable.Map.class, scala.collection.immutable.Map..MODULE$), new Tuple2(scala.collection.mutable.HashMap.class, scala.collection.mutable.HashMap..MODULE$), new Tuple2(LinkedHashMap.class, scala.collection.mutable.LinkedHashMap..MODULE$), new Tuple2(scala.collection.mutable.ListMap.class, scala.collection.mutable.ListMap..MODULE$), new Tuple2(scala.collection.mutable.Map.class, scala.collection.mutable.Map..MODULE$), new Tuple2(TreeSeqMap.class, scala.collection.immutable.TreeSeqMap..MODULE$), new Tuple2(TrieMap.class, scala.collection.concurrent.TrieMap..MODULE$)}))));
         }
      });
   }

   static void $init$(final UnsortedMapDeserializerModule $this) {
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
