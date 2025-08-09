package breeze.linalg.operators;

import breeze.linalg.Vector;
import scala.Function2;

public class Vector_GenericOps$ZippedVectorValues$mcJI$sp extends Vector_GenericOps.ZippedVectorValues {
   public final Vector a$mcJ$sp;
   public final Vector b$mcI$sp;

   public Vector a$mcJ$sp() {
      return this.a$mcJ$sp;
   }

   public Vector a() {
      return this.a$mcJ$sp();
   }

   public Vector b$mcI$sp() {
      return this.b$mcI$sp;
   }

   public Vector b() {
      return this.b$mcI$sp();
   }

   public void foreach(final Function2 f) {
      this.foreach$mcJI$sp(f);
   }

   public void foreach$mcJI$sp(final Function2 f) {
      int index$macro$2 = 0;

      for(int limit$macro$4 = this.a().length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
         f.apply$mcVJI$sp(this.a().apply$mcIJ$sp(index$macro$2), this.b().apply$mcII$sp(index$macro$2));
      }

   }

   public Vector copy$default$1() {
      return this.copy$default$1$mcJ$sp();
   }

   public Vector copy$default$1$mcJ$sp() {
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
   public Vector_GenericOps breeze$linalg$operators$Vector_GenericOps$ZippedVectorValues$mcJI$sp$$$outer() {
      return this.$outer;
   }

   public Vector_GenericOps$ZippedVectorValues$mcJI$sp(final Vector_GenericOps $outer, final Vector a$mcJ$sp, final Vector b$mcI$sp) {
      super((Vector)null, (Vector)null);
      this.a$mcJ$sp = a$mcJ$sp;
      this.b$mcI$sp = b$mcI$sp;
   }
}
