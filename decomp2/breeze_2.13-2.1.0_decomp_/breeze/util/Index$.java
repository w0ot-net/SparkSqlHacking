package breeze.util;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.collection.Iterable;
import scala.collection.Iterator;
import scala.collection.StringOps.;
import scala.io.Source;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class Index$ implements Serializable {
   public static final Index$ MODULE$ = new Index$();

   public MutableIndex apply() {
      return new HashIndex();
   }

   public Index apply(final Iterator iterator) {
      MutableIndex index = this.apply();
      iterator.foreach((element) -> BoxesRunTime.boxToInteger($anonfun$apply$2(index, element)));
      return index;
   }

   public Index apply(final Iterable iterable) {
      MutableIndex index = this.apply();
      iterable.foreach((element) -> BoxesRunTime.boxToInteger($anonfun$apply$3(index, element)));
      return index;
   }

   public Index load(final Source source) {
      return this.apply(source.getLines().map((x$5) -> .MODULE$.stripLineEnd$extension(scala.Predef..MODULE$.augmentString(x$5))));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Index$.class);
   }

   // $FF: synthetic method
   public static final int $anonfun$apply$2(final MutableIndex index$1, final Object element) {
      return index$1.index(element);
   }

   // $FF: synthetic method
   public static final int $anonfun$apply$3(final MutableIndex index$2, final Object element) {
      return index$2.index(element);
   }

   private Index$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
