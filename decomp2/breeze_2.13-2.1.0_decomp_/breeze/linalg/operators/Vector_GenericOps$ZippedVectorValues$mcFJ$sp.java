package breeze.linalg.operators;

import breeze.linalg.Vector;
import scala.Function2;
import scala.runtime.BoxesRunTime;

public class Vector_GenericOps$ZippedVectorValues$mcFJ$sp extends Vector_GenericOps.ZippedVectorValues {
   public final Vector a$mcF$sp;
   public final Vector b$mcJ$sp;

   public Vector a$mcF$sp() {
      return this.a$mcF$sp;
   }

   public Vector a() {
      return this.a$mcF$sp();
   }

   public Vector b$mcJ$sp() {
      return this.b$mcJ$sp;
   }

   public Vector b() {
      return this.b$mcJ$sp();
   }

   public void foreach(final Function2 f) {
      this.foreach$mcFJ$sp(f);
   }

   public void foreach$mcFJ$sp(final Function2 f) {
      int index$macro$2 = 0;

      for(int limit$macro$4 = this.a().length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
         f.apply(BoxesRunTime.boxToFloat(this.a().apply$mcIF$sp(index$macro$2)), BoxesRunTime.boxToLong(this.b().apply$mcIJ$sp(index$macro$2)));
      }

   }

   public Vector copy$default$1() {
      return this.copy$default$1$mcF$sp();
   }

   public Vector copy$default$1$mcF$sp() {
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
   public Vector_GenericOps breeze$linalg$operators$Vector_GenericOps$ZippedVectorValues$mcFJ$sp$$$outer() {
      return this.$outer;
   }

   public Vector_GenericOps$ZippedVectorValues$mcFJ$sp(final Vector_GenericOps $outer, final Vector a$mcF$sp, final Vector b$mcJ$sp) {
      super((Vector)null, (Vector)null);
      this.a$mcF$sp = a$mcF$sp;
      this.b$mcJ$sp = b$mcJ$sp;
   }
}
