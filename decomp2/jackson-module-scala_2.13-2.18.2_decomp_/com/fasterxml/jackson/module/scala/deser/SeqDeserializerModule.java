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
import scala.Option;
import scala.Tuple2;
import scala.collection.ClassTagIterableFactory;
import scala.collection.Iterable;
import scala.collection.IterableFactory;
import scala.collection.LinearSeq;
import scala.collection.Seq;
import scala.collection.Set;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.LazyList;
import scala.collection.immutable.List;
import scala.collection.immutable.Queue;
import scala.collection.immutable.Stream;
import scala.collection.immutable.Vector;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.ArrayDeque;
import scala.collection.mutable.ArraySeq;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Builder;
import scala.collection.mutable.ListBuffer;
import scala.collection.mutable.Stack;
import scala.collection.mutable.UnrolledBuffer;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u00112qAA\u0002\u0011\u0002\u0007\u0005\u0001\u0003C\u0003\u001e\u0001\u0011\u0005aDA\u000bTKF$Um]3sS\u0006d\u0017N_3s\u001b>$W\u000f\\3\u000b\u0005\u0011)\u0011!\u00023fg\u0016\u0014(B\u0001\u0004\b\u0003\u0015\u00198-\u00197b\u0015\tA\u0011\"\u0001\u0004n_\u0012,H.\u001a\u0006\u0003\u0015-\tqA[1dWN|gN\u0003\u0002\r\u001b\u0005Ia-Y:uKJDX\u000e\u001c\u0006\u0002\u001d\u0005\u00191m\\7\u0004\u0001M\u0019\u0001!E\f\u0011\u0005I)R\"A\n\u000b\u0005QI\u0011\u0001\u00033bi\u0006\u0014\u0017N\u001c3\n\u0005Y\u0019\"AB'pIVdW\r\u0005\u0002\u001975\t\u0011D\u0003\u0002\u001b\u000b\u0005IQn\u001c3jM&,'o]\u0005\u00039e\u0011qcU2bY\u0006$\u0016\u0010]3N_\u0012Lg-[3s\u001b>$W\u000f\\3\u0002\r\u0011Jg.\u001b;%)\u0005y\u0002C\u0001\u0011#\u001b\u0005\t#\"\u0001\u0004\n\u0005\r\n#\u0001B+oSR\u0004"
)
public interface SeqDeserializerModule extends ScalaTypeModifierModule {
   // $FF: synthetic method
   static void $anonfun$$init$$1(final Module.SetupContext x$1) {
      x$1.addDeserializers(new GenericFactoryDeserializerResolver() {
         private final Class CLASS_DOMAIN = Iterable.class;
         private final Class IGNORE_CLASS_DOMAIN = Set.class;
         private final Iterable factories;
         private final Iterable tagFactories;

         // $FF: synthetic method
         private Builder super$builderFor(final Class cls, final JavaType valueType) {
            return super.builderFor(cls, valueType);
         }

         public Class CLASS_DOMAIN() {
            return this.CLASS_DOMAIN;
         }

         private Class IGNORE_CLASS_DOMAIN() {
            return this.IGNORE_CLASS_DOMAIN;
         }

         public Iterable factories() {
            return this.factories;
         }

         public Builder builderFor(final IterableFactory cf, final JavaType valueType) {
            return cf.newBuilder();
         }

         private Iterable tagFactories() {
            return this.tagFactories;
         }

         private Builder builderFor(final ClassTagIterableFactory cf, final JavaType valueType) {
            return cf.newBuilder(.MODULE$.apply(valueType.getRawClass()));
         }

         private Option tryTagFactory(final Class cls, final JavaType valueType) {
            return this.tagFactories().find((x$2) -> BoxesRunTime.boxToBoolean($anonfun$tryTagFactory$1(cls, x$2))).map((x$3) -> (ClassTagIterableFactory)x$3._2()).map((x$4) -> this.builderFor(x$4, valueType));
         }

         public Builder builderFor(final Class cls, final JavaType valueType) {
            return (Builder)this.tryTagFactory(cls, valueType).getOrElse(() -> this.super$builderFor(cls, valueType));
         }

         public JsonDeserializer findCollectionLikeDeserializer(final CollectionLikeType collectionType, final DeserializationConfig config, final BeanDescription beanDesc, final TypeDeserializer elementTypeDeserializer, final JsonDeserializer elementDeserializer) {
            Class rawClass = collectionType.getRawClass();
            return this.IGNORE_CLASS_DOMAIN().isAssignableFrom(rawClass) ? (JsonDeserializer)scala.None..MODULE$.orNull(scala..less.colon.less..MODULE$.refl()) : super.findCollectionLikeDeserializer(collectionType, config, beanDesc, elementTypeDeserializer, elementDeserializer);
         }

         // $FF: synthetic method
         public static final boolean $anonfun$tryTagFactory$1(final Class cls$1, final Tuple2 x$2) {
            return ((Class)x$2._1()).isAssignableFrom(cls$1);
         }

         public {
            this.factories = this.sortFactories((IndexedSeq)scala.package..MODULE$.Vector().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{new Tuple2(scala.collection.IndexedSeq.class, scala.collection.IndexedSeq..MODULE$), new Tuple2(Iterable.class, scala.collection.Iterable..MODULE$), new Tuple2(Seq.class, scala.collection.Seq..MODULE$), new Tuple2(LinearSeq.class, scala.collection.LinearSeq..MODULE$), new Tuple2(scala.collection.immutable.Iterable.class, scala.collection.immutable.Iterable..MODULE$), new Tuple2(IndexedSeq.class, scala.collection.immutable.IndexedSeq..MODULE$), new Tuple2(LazyList.class, scala.collection.immutable.LazyList..MODULE$), new Tuple2(scala.collection.immutable.LinearSeq.class, scala.collection.immutable.LinearSeq..MODULE$), new Tuple2(List.class, scala.collection.immutable.List..MODULE$), new Tuple2(Queue.class, scala.collection.immutable.Queue..MODULE$), new Tuple2(Stream.class, scala.collection.immutable.Stream..MODULE$), new Tuple2(scala.collection.immutable.Seq.class, scala.collection.immutable.Seq..MODULE$), new Tuple2(Vector.class, scala.collection.immutable.Vector..MODULE$), new Tuple2(ArrayBuffer.class, scala.collection.mutable.ArrayBuffer..MODULE$), new Tuple2(ArrayDeque.class, scala.collection.mutable.ArrayDeque..MODULE$), new Tuple2(Buffer.class, scala.collection.mutable.Buffer..MODULE$), new Tuple2(scala.collection.mutable.IndexedSeq.class, scala.collection.mutable.IndexedSeq..MODULE$), new Tuple2(scala.collection.mutable.Iterable.class, scala.collection.mutable.Iterable..MODULE$), new Tuple2(ListBuffer.class, scala.collection.mutable.ListBuffer..MODULE$), new Tuple2(scala.collection.mutable.Queue.class, scala.collection.mutable.Queue..MODULE$), new Tuple2(scala.collection.mutable.Seq.class, scala.collection.mutable.Seq..MODULE$), new Tuple2(Stack.class, scala.collection.mutable.Stack..MODULE$)}))));
            this.tagFactories = new scala.collection.immutable..colon.colon(new Tuple2(ArraySeq.class, scala.collection.mutable.ArraySeq..MODULE$), new scala.collection.immutable..colon.colon(new Tuple2(scala.collection.immutable.ArraySeq.class, scala.collection.immutable.ArraySeq..MODULE$), new scala.collection.immutable..colon.colon(new Tuple2(UnrolledBuffer.class, scala.collection.mutable.UnrolledBuffer..MODULE$), scala.collection.immutable.Nil..MODULE$)));
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
   }

   static void $init$(final SeqDeserializerModule $this) {
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
