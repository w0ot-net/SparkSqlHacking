package scala.collection.immutable;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Tuple2;
import scala.collection.BuildFrom;
import scala.collection.Factory;
import scala.collection.IterableOnce;
import scala.collection.mutable.Builder;
import scala.collection.mutable.ImmutableBuilder;
import scala.runtime.ModuleSerializationProxy;

public final class IntMap$ implements Serializable {
   public static final IntMap$ MODULE$ = new IntMap$();

   public IntMap empty() {
      return IntMap.Nil$.MODULE$;
   }

   public IntMap singleton(final int key, final Object value) {
      return new IntMap.Tip(key, value);
   }

   public IntMap apply(final Seq elems) {
      return (IntMap)elems.foldLeft(IntMap.Nil$.MODULE$, (x, y) -> x.updated(y._1$mcI$sp(), y._2()));
   }

   public IntMap from(final IterableOnce coll) {
      return (IntMap)((Builder)(new ImmutableBuilder() {
         public <undefinedtype> addOne(final Tuple2 elem) {
            this.elems_$eq(((IntMap)this.elems()).$plus(elem));
            return this;
         }

         public {
            IntMap$ var10001 = IntMap$.MODULE$;
         }
      }).addAll(coll)).result();
   }

   public Builder newBuilder() {
      return new ImmutableBuilder() {
         public <undefinedtype> addOne(final Tuple2 elem) {
            this.elems_$eq(((IntMap)this.elems()).$plus(elem));
            return this;
         }

         public {
            IntMap$ var10001 = IntMap$.MODULE$;
         }
      };
   }

   public Factory toFactory(final IntMap$ dummy) {
      return IntMap.ToFactory$.MODULE$;
   }

   public BuildFrom toBuildFrom(final IntMap$ factory) {
      return IntMap.ToBuildFrom$.MODULE$;
   }

   public Factory iterableFactory() {
      return IntMap.ToFactory$.MODULE$;
   }

   public BuildFrom buildFromIntMap() {
      return IntMap.ToBuildFrom$.MODULE$;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(IntMap$.class);
   }

   private IntMap$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
