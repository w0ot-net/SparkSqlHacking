package spire.algebra;

import algebra.ring.Field;
import algebra.ring.Signed;
import cats.kernel.Order;
import java.io.Serializable;
import scala.collection.Factory;
import scala.runtime.ModuleSerializationProxy;

public final class NormedVectorSpace$ implements NormedVectorSpace0, NormedVectorSpaceFunctions, Serializable {
   public static final NormedVectorSpace$ MODULE$ = new NormedVectorSpace$();

   static {
      NormedVectorSpace0.$init$(MODULE$);
      NormedVectorSpaceFunctions.$init$(MODULE$);
   }

   public NormedVectorSpace max(final Order evidence$1, final Field evidence$2, final Signed evidence$3, final Factory cbf0) {
      return NormedVectorSpaceFunctions.max$(this, evidence$1, evidence$2, evidence$3, cbf0);
   }

   public NormedVectorSpace Lp(final int p, final Field evidence$4, final NRoot evidence$5, final Signed evidence$6, final Factory cbf0) {
      return NormedVectorSpaceFunctions.Lp$(this, p, evidence$4, evidence$5, evidence$6, cbf0);
   }

   public NormedVectorSpace InnerProductSpaceIsNormedVectorSpace(final InnerProductSpace space, final NRoot nroot) {
      return NormedVectorSpace0.InnerProductSpaceIsNormedVectorSpace$(this, space, nroot);
   }

   public NormedVectorSpace InnerProductSpaceIsNormedVectorSpace$mDc$sp(final InnerProductSpace space, final NRoot nroot) {
      return NormedVectorSpace0.InnerProductSpaceIsNormedVectorSpace$mDc$sp$(this, space, nroot);
   }

   public NormedVectorSpace InnerProductSpaceIsNormedVectorSpace$mFc$sp(final InnerProductSpace space, final NRoot nroot) {
      return NormedVectorSpace0.InnerProductSpaceIsNormedVectorSpace$mFc$sp$(this, space, nroot);
   }

   public NormedVectorSpace InnerProductSpaceIsNormedVectorSpace$mIc$sp(final InnerProductSpace space, final NRoot nroot) {
      return NormedVectorSpace0.InnerProductSpaceIsNormedVectorSpace$mIc$sp$(this, space, nroot);
   }

   public NormedVectorSpace InnerProductSpaceIsNormedVectorSpace$mJc$sp(final InnerProductSpace space, final NRoot nroot) {
      return NormedVectorSpace0.InnerProductSpaceIsNormedVectorSpace$mJc$sp$(this, space, nroot);
   }

   public final NormedVectorSpace apply(final NormedVectorSpace V) {
      return V;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(NormedVectorSpace$.class);
   }

   public final NormedVectorSpace apply$mDc$sp(final NormedVectorSpace V) {
      return V;
   }

   public final NormedVectorSpace apply$mFc$sp(final NormedVectorSpace V) {
      return V;
   }

   public final NormedVectorSpace apply$mIc$sp(final NormedVectorSpace V) {
      return V;
   }

   public final NormedVectorSpace apply$mJc$sp(final NormedVectorSpace V) {
      return V;
   }

   private NormedVectorSpace$() {
   }
}
