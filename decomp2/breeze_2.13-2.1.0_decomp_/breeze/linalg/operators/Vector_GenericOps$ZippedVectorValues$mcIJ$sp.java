package breeze.linalg.operators;

import breeze.linalg.Vector;
import scala.Function2;

public class Vector_GenericOps$ZippedVectorValues$mcIJ$sp extends Vector_GenericOps.ZippedVectorValues {
   public final Vector a$mcI$sp;
   public final Vector b$mcJ$sp;

   public Vector a$mcI$sp() {
      return this.a$mcI$sp;
   }

   public Vector a() {
      return this.a$mcI$sp();
   }

   public Vector b$mcJ$sp() {
      return this.b$mcJ$sp;
   }

   public Vector b() {
      return this.b$mcJ$sp();
   }

   public void foreach(final Function2 f) {
      this.foreach$mcIJ$sp(f);
   }

   public void foreach$mcIJ$sp(final Function2 f) {
      int index$macro$2 = 0;

      for(int limit$macro$4 = this.a().length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
         f.apply$mcVIJ$sp(this.a().apply$mcII$sp(index$macro$2), this.b().apply$mcIJ$sp(index$macro$2));
      }

   }

   public Vector copy$default$1() {
      return this.copy$default$1$mcI$sp();
   }

   public Vector copy$default$1$mcI$sp() {
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
   public Vector_GenericOps breeze$linalg$operators$Vector_GenericOps$ZippedVectorValues$mcIJ$sp$$$outer() {
      return this.$outer;
   }

   public Vector_GenericOps$ZippedVectorValues$mcIJ$sp(final Vector_GenericOps $outer, final Vector a$mcI$sp, final Vector b$mcJ$sp) {
      super((Vector)null, (Vector)null);
      this.a$mcI$sp = a$mcI$sp;
      this.b$mcJ$sp = b$mcJ$sp;
   }
}
