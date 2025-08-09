package breeze.collection.mutable;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Function0;
import scala.Function2;
import scala.reflect.ClassTag;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.RichInt.;
import scala.runtime.java8.JFunction1;

public final class TriangularArray$ implements Serializable {
   public static final TriangularArray$ MODULE$ = new TriangularArray$();

   public TriangularArray tabulate(final int dim, final Function2 fill, final ClassTag evidence$3) {
      TriangularArray array = new TriangularArray(dim, evidence$3);
      .MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), dim).foreach$mVc$sp((JFunction1.mcVI.sp)(c) -> .MODULE$.to$extension(scala.Predef..MODULE$.intWrapper(0), c).foreach$mVc$sp((JFunction1.mcVI.sp)(r) -> scala.runtime.ScalaRunTime..MODULE$.array_update(array.data(), MODULE$.index(r, c), fill.apply(BoxesRunTime.boxToInteger(r), BoxesRunTime.boxToInteger(c)))));
      return array;
   }

   public TriangularArray fill(final int dim, final Function0 fill, final ClassTag evidence$4) {
      TriangularArray array = new TriangularArray(dim, evidence$4);
      .MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), dim).foreach$mVc$sp((JFunction1.mcVI.sp)(c) -> .MODULE$.to$extension(scala.Predef..MODULE$.intWrapper(0), c).foreach$mVc$sp((JFunction1.mcVI.sp)(r) -> scala.runtime.ScalaRunTime..MODULE$.array_update(array.data(), MODULE$.index(r, c), fill.apply())));
      return array;
   }

   public int index(final int r, final int c) {
      if (r > c) {
         scala.Predef..MODULE$.require(r <= c, () -> "row must be less than column!");
      }

      return c * (c + 1) / 2 + r;
   }

   public Object raw(final int dim, final Function0 fill, final ClassTag evidence$5) {
      int numElems = this.arraySize(dim);
      Object data = scala.Array..MODULE$.fill(numElems, fill, evidence$5);
      return data;
   }

   public int arraySize(final int dim) {
      return dim * (dim + 1) / 2;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(TriangularArray$.class);
   }

   private TriangularArray$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
