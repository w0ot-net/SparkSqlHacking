package breeze.linalg.operators;

import breeze.linalg.Vector;
import scala.Function2;

public class Vector_GenericOps$ZippedVectorValues$mcDI$sp extends Vector_GenericOps.ZippedVectorValues {
   public final Vector a$mcD$sp;
   public final Vector b$mcI$sp;

   public Vector a$mcD$sp() {
      return this.a$mcD$sp;
   }

   public Vector a() {
      return this.a$mcD$sp();
   }

   public Vector b$mcI$sp() {
      return this.b$mcI$sp;
   }

   public Vector b() {
      return this.b$mcI$sp();
   }

   public void foreach(final Function2 f) {
      this.foreach$mcDI$sp(f);
   }

   public void foreach$mcDI$sp(final Function2 f) {
      int index$macro$2 = 0;

      for(int limit$macro$4 = this.a().length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
         f.apply$mcVDI$sp(this.a().apply$mcID$sp(index$macro$2), this.b().apply$mcII$sp(index$macro$2));
      }

   }

   public Vector copy$default$1() {
      return this.copy$default$1$mcD$sp();
   }

   public Vector copy$default$1$mcD$sp() {
      return this.a();
   }

   public Vector copy$default$2() {
      return this.copy$default$2$mcI$sp();
   }

   public Vector copy$default$2$mcI$sp() {
      return this.b();
   }

   public boolean specInstance$() {
      return true;
   }

   // $FF: synthetic method
   public Vector_GenericOps breeze$linalg$operators$Vector_GenericOps$ZippedVectorValues$mcDI$sp$$$outer() {
      return this.$outer;
   }

   public Vector_GenericOps$ZippedVectorValues$mcDI$sp(final Vector_GenericOps $outer, final Vector a$mcD$sp, final Vector b$mcI$sp) {
      super((Vector)null, (Vector)null);
      this.a$mcD$sp = a$mcD$sp;
      this.b$mcI$sp = b$mcI$sp;
   }
}
