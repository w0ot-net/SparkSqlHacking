package scala.collection.parallel.mutable;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.collection.generic.CanCombineFrom;
import scala.collection.generic.ParFactory;
import scala.collection.immutable.Seq;
import scala.collection.parallel.Combiner;
import scala.reflect.ClassTag;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.ScalaRunTime.;

public final class ParArray$ extends ParFactory implements Serializable {
   public static final ParArray$ MODULE$ = new ParArray$();

   public CanCombineFrom canBuildFrom() {
      return new ParFactory.GenericCanCombineFrom();
   }

   public Combiner newBuilder() {
      return this.newCombiner();
   }

   public Combiner newCombiner() {
      return package$.MODULE$.ParArrayCombiner().apply();
   }

   public ParArray handoff(final Object arr) {
      return this.wrapOrRebuild(arr, .MODULE$.array_length(arr));
   }

   public ParArray handoff(final Object arr, final int sz) {
      return this.wrapOrRebuild(arr, sz);
   }

   private ParArray wrapOrRebuild(final Object arr, final int sz) {
      return new ParArray(scala.collection.mutable.ArraySeq..MODULE$.make(.MODULE$.toObjectArray(arr)), sz);
   }

   public ParArray createFromCopy(final Object[] arr, final ClassTag evidence$1) {
      Object[] newarr = evidence$1.newArray(arr.length);
      scala.Array..MODULE$.copy(arr, 0, newarr, 0, arr.length);
      return this.handoff(newarr);
   }

   /** @deprecated */
   public final ParArray fromTraversables(final Seq xss) {
      return this.fromIterables(xss);
   }

   public ParArray fromIterables(final Seq xss) {
      ResizableParArrayCombiner cb = package$.MODULE$.ParArrayCombiner().apply();
      xss.foreach((xs) -> (ResizableParArrayCombiner)cb.$plus$plus$eq(xs));
      return (ParArray)cb.result();
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ParArray$.class);
   }

   private ParArray$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
