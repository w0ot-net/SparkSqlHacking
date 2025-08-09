package spire.algebra;

import algebra.ring.Field;

public interface NormedInnerProductSpace$mcD$sp extends NormedInnerProductSpace, NormedVectorSpace$mcD$sp {
   // $FF: synthetic method
   static Field scalar$(final NormedInnerProductSpace$mcD$sp $this) {
      return $this.scalar();
   }

   default Field scalar() {
      return this.scalar$mcD$sp();
   }

   // $FF: synthetic method
   static Field scalar$mcD$sp$(final NormedInnerProductSpace$mcD$sp $this) {
      return $this.scalar$mcD$sp();
   }

   default Field scalar$mcD$sp() {
      return this.space$mcD$sp().scalar$mcD$sp();
   }

   // $FF: synthetic method
   static Object timesl$(final NormedInnerProductSpace$mcD$sp $this, final double f, final Object v) {
      return $this.timesl(f, v);
   }

   default Object timesl(final double f, final Object v) {
      return this.timesl$mcD$sp(f, v);
   }

   // $FF: synthetic method
   static Object timesl$mcD$sp$(final NormedInnerProductSpace$mcD$sp $this, final double f, final Object v) {
      return $this.timesl$mcD$sp(f, v);
   }

   default Object timesl$mcD$sp(final double f, final Object v) {
      return this.space$mcD$sp().timesl$mcD$sp(f, v);
   }

   // $FF: synthetic method
   static Object divr$(final NormedInnerProductSpace$mcD$sp $this, final Object v, final double f) {
      return $this.divr(v, f);
   }

   default Object divr(final Object v, final double f) {
      return this.divr$mcD$sp(v, f);
   }

   // $FF: synthetic method
   static Object divr$mcD$sp$(final NormedInnerProductSpace$mcD$sp $this, final Object v, final double f) {
      return $this.divr$mcD$sp(v, f);
   }

   default Object divr$mcD$sp(final Object v, final double f) {
      return this.space$mcD$sp().divr$mcD$sp(v, f);
   }

   // $FF: synthetic method
   static double norm$(final NormedInnerProductSpace$mcD$sp $this, final Object v) {
      return $this.norm(v);
   }

   default double norm(final Object v) {
      return this.norm$mcD$sp(v);
   }

   // $FF: synthetic method
   static double norm$mcD$sp$(final NormedInnerProductSpace$mcD$sp $this, final Object v) {
      return $this.norm$mcD$sp(v);
   }

   default double norm$mcD$sp(final Object v) {
      return this.nroot$mcD$sp().sqrt$mcD$sp(this.space$mcD$sp().dot$mcD$sp(v, v));
   }
}
