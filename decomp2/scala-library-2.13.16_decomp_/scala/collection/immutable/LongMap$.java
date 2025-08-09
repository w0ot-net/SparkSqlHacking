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

public final class LongMap$ implements Serializable {
   public static final LongMap$ MODULE$ = new LongMap$();

   public LongMap empty() {
      return LongMap.Nil$.MODULE$;
   }

   public LongMap singleton(final long key, final Object value) {
      return new LongMap.Tip(key, value);
   }

   public LongMap apply(final Seq elems) {
      return (LongMap)elems.foldLeft(LongMap.Nil$.MODULE$, (x, y) -> x.updated(y._1$mcJ$sp(), y._2()));
   }

   public LongMap from(final IterableOnce coll) {
      return (LongMap)((Builder)(new ImmutableBuilder() {
         public <undefinedtype> addOne(final Tuple2 elem) {
            this.elems_$eq(((LongMap)this.elems()).$plus(elem));
            return this;
         }

         public {
            LongMap$ var10001 = LongMap$.MODULE$;
         }
      }).addAll(coll)).result();
   }

   public Builder newBuilder() {
      return new ImmutableBuilder() {
         public <undefinedtype> addOne(final Tuple2 elem) {
            this.elems_$eq(((LongMap)this.elems()).$plus(elem));
            return this;
         }

         public {
            LongMap$ var10001 = LongMap$.MODULE$;
         }
      };
   }

   public Factory toFactory(final LongMap$ dummy) {
      return LongMap.ToFactory$.MODULE$;
   }

   public BuildFrom toBuildFrom(final LongMap$ factory) {
      return LongMap.ToBuildFrom$.MODULE$;
   }

   public Factory iterableFactory() {
      return LongMap.ToFactory$.MODULE$;
   }

   public BuildFrom buildFromLongMap() {
      return LongMap.ToBuildFrom$.MODULE$;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(LongMap$.class);
   }

   private LongMap$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
