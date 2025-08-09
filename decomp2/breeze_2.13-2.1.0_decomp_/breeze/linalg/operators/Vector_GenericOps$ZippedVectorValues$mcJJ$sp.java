package breeze.linalg.operators;

import breeze.linalg.Vector;
import scala.Function2;

public class Vector_GenericOps$ZippedVectorValues$mcJJ$sp extends Vector_GenericOps.ZippedVectorValues {
   public final Vector a$mcJ$sp;
   public final Vector b$mcJ$sp;

   public Vector a$mcJ$sp() {
      return this.a$mcJ$sp;
   }

   public Vector a() {
      return this.a$mcJ$sp();
   }

   public Vector b$mcJ$sp() {
      return this.b$mcJ$sp;
   }

   public Vector b() {
      return this.b$mcJ$sp();
   }

   public void foreach(final Function2 f) {
      this.foreach$mcJJ$sp(f);
   }

   public void foreach$mcJJ$sp(final Function2 f) {
      int index$macro$2 = 0;

      for(int limit$macro$4 = this.a().length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
         f.apply$mcVJJ$sp(this.a().apply$mcIJ$sp(index$macro$2), this.b().apply$mcIJ$sp(index$macro$2));
      }

   }

   public Vector copy$default$1() {
      return this.copy$default$1$mcJ$sp();
   }

   public Vector copy$default$1$mcJ$sp() {
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
   public Vector_GenericOps breeze$linalg$operators$Vector_GenericOps$ZippedVectorValues$mcJJ$sp$$$outer() {
      return this.$outer;
   }

   public Vector_GenericOps$ZippedVectorValues$mcJJ$sp(final Vector_GenericOps $outer, final Vector a$mcJ$sp, final Vector b$mcJ$sp) {
      super((Vector)null, (Vector)null);
      this.a$mcJ$sp = a$mcJ$sp;
      this.b$mcJ$sp = b$mcJ$sp;
   }
}
