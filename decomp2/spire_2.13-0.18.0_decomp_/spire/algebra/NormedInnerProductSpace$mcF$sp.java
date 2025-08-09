package spire.algebra;

import algebra.ring.Field;

public interface NormedInnerProductSpace$mcF$sp extends NormedInnerProductSpace, NormedVectorSpace$mcF$sp {
   // $FF: synthetic method
   static Field scalar$(final NormedInnerProductSpace$mcF$sp $this) {
      return $this.scalar();
   }

   default Field scalar() {
      return this.scalar$mcF$sp();
   }

   // $FF: synthetic method
   static Field scalar$mcF$sp$(final NormedInnerProductSpace$mcF$sp $this) {
      return $this.scalar$mcF$sp();
   }

   default Field scalar$mcF$sp() {
      return this.space$mcF$sp().scalar$mcF$sp();
   }

   // $FF: synthetic method
   static Object timesl$(final NormedInnerProductSpace$mcF$sp $this, final float f, final Object v) {
      return $this.timesl(f, v);
   }

   default Object timesl(final float f, final Object v) {
      return this.timesl$mcF$sp(f, v);
   }

   // $FF: synthetic method
   static Object timesl$mcF$sp$(final NormedInnerProductSpace$mcF$sp $this, final float f, final Object v) {
      return $this.timesl$mcF$sp(f, v);
   }

   default Object timesl$mcF$sp(final float f, final Object v) {
      return this.space$mcF$sp().timesl$mcF$sp(f, v);
   }

   // $FF: synthetic method
   static Object divr$(final NormedInnerProductSpace$mcF$sp $this, final Object v, final float f) {
      return $this.divr(v, f);
   }

   default Object divr(final Object v, final float f) {
      return this.divr$mcF$sp(v, f);
   }

   // $FF: synthetic method
   static Object divr$mcF$sp$(final NormedInnerProductSpace$mcF$sp $this, final Object v, final float f) {
      return $this.divr$mcF$sp(v, f);
   }

   default Object divr$mcF$sp(final Object v, final float f) {
      return this.space$mcF$sp().divr$mcF$sp(v, f);
   }

   // $FF: synthetic method
   static float norm$(final NormedInnerProductSpace$mcF$sp $this, final Object v) {
      return $this.norm(v);
   }

   default float norm(final Object v) {
      return this.norm$mcF$sp(v);
   }

   // $FF: synthetic method
   static float norm$mcF$sp$(final NormedInnerProductSpace$mcF$sp $this, final Object v) {
      return $this.norm$mcF$sp(v);
   }

   default float norm$mcF$sp(final Object v) {
      return this.nroot$mcF$sp().sqrt$mcF$sp(this.space$mcF$sp().dot$mcF$sp(v, v));
   }
}
