package breeze.linalg.operators;

import breeze.linalg.Vector;
import scala.Function2;

public class Vector_GenericOps$ZippedVectorValues$mcDJ$sp extends Vector_GenericOps.ZippedVectorValues {
   public final Vector a$mcD$sp;
   public final Vector b$mcJ$sp;

   public Vector a$mcD$sp() {
      return this.a$mcD$sp;
   }

   public Vector a() {
      return this.a$mcD$sp();
   }

   public Vector b$mcJ$sp() {
      return this.b$mcJ$sp;
   }

   public Vector b() {
      return this.b$mcJ$sp();
   }

   public void foreach(final Function2 f) {
      this.foreach$mcDJ$sp(f);
   }

   public void foreach$mcDJ$sp(final Function2 f) {
      int index$macro$2 = 0;

      for(int limit$macro$4 = this.a().length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
         f.apply$mcVDJ$sp(this.a().apply$mcID$sp(index$macro$2), this.b().apply$mcIJ$sp(index$macro$2));
      }

   }

   public Vector copy$default$1() {
      return this.copy$default$1$mcD$sp();
   }

   public Vector copy$default$1$mcD$sp() {
      return this.a();
   }

   public Vector copy$default$2() {
      return this.copy$default$2$mcJ$sp();
   }

   public Vector copy$default$2$mcJ$sp() {
      return this.b();
   }

   public boolean specInstance$() {
      return true;
   }

   // $FF: synthetic method
   public Vector_GenericOps breeze$linalg$operators$Vector_GenericOps$ZippedVectorValues$mcDJ$sp$$$outer() {
      return this.$outer;
   }

   public Vector_GenericOps$ZippedVectorValues$mcDJ$sp(final Vector_GenericOps $outer, final Vector a$mcD$sp, final Vector b$mcJ$sp) {
      super((Vector)null, (Vector)null);
      this.a$mcD$sp = a$mcD$sp;
      this.b$mcJ$sp = b$mcJ$sp;
   }
}
