package breeze.linalg.operators;

import breeze.linalg.Vector;
import breeze.linalg.ZippedValues$mcDD$sp;
import scala.Function2;

public class Vector_GenericOps$ZippedVectorValues$mcDD$sp extends Vector_GenericOps.ZippedVectorValues implements ZippedValues$mcDD$sp {
   public final Vector a$mcD$sp;
   public final Vector b$mcD$sp;

   public boolean exists(final Function2 f) {
      return ZippedValues$mcDD$sp.exists$(this, f);
   }

   public boolean exists$mcDD$sp(final Function2 f) {
      return ZippedValues$mcDD$sp.exists$mcDD$sp$(this, f);
   }

   public boolean forall(final Function2 f) {
      return ZippedValues$mcDD$sp.forall$(this, f);
   }

   public boolean forall$mcDD$sp(final Function2 f) {
      return ZippedValues$mcDD$sp.forall$mcDD$sp$(this, f);
   }

   public Vector a$mcD$sp() {
      return this.a$mcD$sp;
   }

   public Vector a() {
      return this.a$mcD$sp();
   }

   public Vector b$mcD$sp() {
      return this.b$mcD$sp;
   }

   public Vector b() {
      return this.b$mcD$sp();
   }

   public void foreach(final Function2 f) {
      this.foreach$mcDD$sp(f);
   }

   public void foreach$mcDD$sp(final Function2 f) {
      int index$macro$2 = 0;

      for(int limit$macro$4 = this.a().length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
         f.apply$mcVDD$sp(this.a().apply$mcID$sp(index$macro$2), this.b().apply$mcID$sp(index$macro$2));
      }

   }

   public Vector copy$default$1() {
      return this.copy$default$1$mcD$sp();
   }

   public Vector copy$default$1$mcD$sp() {
      return this.a();
   }

   public Vector copy$default$2() {
      return this.copy$default$2$mcD$sp();
   }

   public Vector copy$default$2$mcD$sp() {
      return this.b();
   }

   public boolean specInstance$() {
      return true;
   }

   // $FF: synthetic method
   public Vector_GenericOps breeze$linalg$operators$Vector_GenericOps$ZippedVectorValues$mcDD$sp$$$outer() {
      return this.$outer;
   }

   public Vector_GenericOps$ZippedVectorValues$mcDD$sp(final Vector_GenericOps $outer, final Vector a$mcD$sp, final Vector b$mcD$sp) {
      super((Vector)null, (Vector)null);
      this.a$mcD$sp = a$mcD$sp;
      this.b$mcD$sp = b$mcD$sp;
   }
}
