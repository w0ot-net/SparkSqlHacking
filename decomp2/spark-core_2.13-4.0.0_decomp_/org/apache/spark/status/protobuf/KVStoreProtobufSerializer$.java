package org.apache.spark.status.protobuf;

import java.lang.invoke.SerializedLambda;
import java.lang.reflect.ParameterizedType;
import java.util.ServiceLoader;
import scala.Option;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.Map;
import scala.jdk.CollectionConverters.;

public final class KVStoreProtobufSerializer$ {
   public static final KVStoreProtobufSerializer$ MODULE$ = new KVStoreProtobufSerializer$();
   private static Map serializerMap;
   private static volatile boolean bitmap$0;

   private Map serializerMap$lzycompute() {
      synchronized(this){}

      try {
         if (!bitmap$0) {
            serializerMap = ((IterableOnceOps).MODULE$.IterableHasAsScala(ServiceLoader.load(ProtobufSerDe.class)).asScala().map((serDe) -> scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(getGenericsType$1(serDe.getClass())), serDe))).toMap(scala..less.colon.less..MODULE$.refl());
            bitmap$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return serializerMap;
   }

   private Map serializerMap() {
      return !bitmap$0 ? this.serializerMap$lzycompute() : serializerMap;
   }

   public Option getSerializer(final Class klass) {
      return this.serializerMap().get(klass);
   }

   private static final Class getGenericsType$1(final Class klass) {
      return (Class)scala.collection.ArrayOps..MODULE$.head$extension(scala.Predef..MODULE$.refArrayOps((Object[])((ParameterizedType)scala.collection.ArrayOps..MODULE$.head$extension(scala.Predef..MODULE$.refArrayOps((Object[])klass.getGenericInterfaces()))).getActualTypeArguments()));
   }

   private KVStoreProtobufSerializer$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
