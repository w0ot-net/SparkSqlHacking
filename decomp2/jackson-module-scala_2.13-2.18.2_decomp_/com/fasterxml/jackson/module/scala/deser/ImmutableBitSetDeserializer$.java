package com.fasterxml.jackson.module.scala.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.JsonNodeDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import java.lang.invoke.SerializedLambda;
import scala.collection.IterableOnceOps;
import scala.collection.JavaConverters.;
import scala.collection.immutable.BitSet;
import scala.collection.immutable.Seq;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class ImmutableBitSetDeserializer$ extends StdDeserializer {
   public static final ImmutableBitSetDeserializer$ MODULE$ = new ImmutableBitSetDeserializer$();

   public BitSet deserialize(final JsonParser p, final DeserializationContext ctxt) {
      JsonDeserializer arrayNodeDeserializer = JsonNodeDeserializer.getDeserializer(ArrayNode.class);
      ArrayNode arrayNode = (ArrayNode)arrayNodeDeserializer.deserialize(p, ctxt);
      Seq elements = (Seq)((IterableOnceOps).MODULE$.asScalaIteratorConverter(arrayNode.elements()).asScala()).toSeq().map((x$1) -> BoxesRunTime.boxToInteger($anonfun$deserialize$1(x$1)));
      return (BitSet)scala.collection.immutable.BitSet..MODULE$.apply(elements);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ImmutableBitSetDeserializer$.class);
   }

   // $FF: synthetic method
   public static final int $anonfun$deserialize$1(final JsonNode x$1) {
      return x$1.asInt();
   }

   private ImmutableBitSetDeserializer$() {
      super(BitSet.class);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
