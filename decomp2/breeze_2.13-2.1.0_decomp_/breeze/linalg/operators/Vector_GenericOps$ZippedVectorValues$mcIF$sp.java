package breeze.linalg.operators;

import breeze.linalg.Vector;
import scala.Function2;
import scala.runtime.BoxesRunTime;

public class Vector_GenericOps$ZippedVectorValues$mcIF$sp extends Vector_GenericOps.ZippedVectorValues {
   public final Vector a$mcI$sp;
   public final Vector b$mcF$sp;

   public Vector a$mcI$sp() {
      return this.a$mcI$sp;
   }

   public Vector a() {
      return this.a$mcI$sp();
   }

   public Vector b$mcF$sp() {
      return this.b$mcF$sp;
   }

   public Vector b() {
      return this.b$mcF$sp();
   }

   public void foreach(final Function2 f) {
      this.foreach$mcIF$sp(f);
   }

   public void foreach$mcIF$sp(final Function2 f) {
      int index$macro$2 = 0;

      for(int limit$macro$4 = this.a().length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
         f.apply(BoxesRunTime.boxToInteger(this.a().apply$mcII$sp(index$macro$2)), BoxesRunTime.boxToFloat(this.b().apply$mcIF$sp(index$macro$2)));
      }

   }

   public Vector copy$default$1() {
      return this.copy$default$1$mcI$sp();
   }

   public Vector copy$default$1$mcI$sp() {
      return this.a();
   }

   public Vector copy$default$2() {
      return this.copy$default$2$mcF$sp();
   }

   public Vector copy$default$2$mcF$sp() {
      return this.b();
   }

   public boolean specInstance$() {
      return true;
   }

   // $FF: synthetic method
   public Vector_GenericOps breeze$linalg$operators$Vector_GenericOps$ZippedVectorValues$mcIF$sp$$$outer() {
      return this.$outer;
   }

   public Vector_GenericOps$ZippedVectorValues$mcIF$sp(final Vector_GenericOps $outer, final Vector a$mcI$sp, final Vector b$mcF$sp) {
      super((Vector)null, (Vector)null);
      this.a$mcI$sp = a$mcI$sp;
      this.b$mcF$sp = b$mcF$sp;
   }
}
