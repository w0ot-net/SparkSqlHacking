package spire.algebra;

import algebra.ring.Field;
import java.io.Serializable;
import scala.collection.Factory;
import scala.reflect.ClassTag;
import scala.runtime.ModuleSerializationProxy;
import spire.std.ArrayCoordinateSpace;
import spire.std.ArrayCoordinateSpace$mcD$sp;
import spire.std.ArrayCoordinateSpace$mcF$sp;
import spire.std.SeqCoordinateSpace;

public final class CoordinateSpace$ implements Serializable {
   public static final CoordinateSpace$ MODULE$ = new CoordinateSpace$();

   public final CoordinateSpace apply(final CoordinateSpace V) {
      return V;
   }

   public SeqCoordinateSpace seq(final int dimensions, final Field evidence$1, final Factory cbf0) {
      return new SeqCoordinateSpace(dimensions, evidence$1, cbf0);
   }

   public CoordinateSpace array(final int dimensions, final Field evidence$2, final ClassTag evidence$3) {
      return new ArrayCoordinateSpace(dimensions, evidence$3, evidence$2);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(CoordinateSpace$.class);
   }

   public final CoordinateSpace apply$mDc$sp(final CoordinateSpace V) {
      return V;
   }

   public final CoordinateSpace apply$mFc$sp(final CoordinateSpace V) {
      return V;
   }

   public CoordinateSpace array$mDc$sp(final int dimensions, final Field evidence$2, final ClassTag evidence$3) {
      return new ArrayCoordinateSpace$mcD$sp(dimensions, evidence$3, evidence$2);
   }

   public CoordinateSpace array$mFc$sp(final int dimensions, final Field evidence$2, final ClassTag evidence$3) {
      return new ArrayCoordinateSpace$mcF$sp(dimensions, evidence$3, evidence$2);
   }

   private CoordinateSpace$() {
   }
}
