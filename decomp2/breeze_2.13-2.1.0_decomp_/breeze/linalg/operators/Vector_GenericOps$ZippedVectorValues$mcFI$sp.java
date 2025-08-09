package breeze.linalg.operators;

import breeze.linalg.Vector;
import scala.Function2;
import scala.runtime.BoxesRunTime;

public class Vector_GenericOps$ZippedVectorValues$mcFI$sp extends Vector_GenericOps.ZippedVectorValues {
   public final Vector a$mcF$sp;
   public final Vector b$mcI$sp;

   public Vector a$mcF$sp() {
      return this.a$mcF$sp;
   }

   public Vector a() {
      return this.a$mcF$sp();
   }

   public Vector b$mcI$sp() {
      return this.b$mcI$sp;
   }

   public Vector b() {
      return this.b$mcI$sp();
   }

   public void foreach(final Function2 f) {
      this.foreach$mcFI$sp(f);
   }

   public void foreach$mcFI$sp(final Function2 f) {
      int index$macro$2 = 0;

      for(int limit$macro$4 = this.a().length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
         f.apply(BoxesRunTime.boxToFloat(this.a().apply$mcIF$sp(index$macro$2)), BoxesRunTime.boxToInteger(this.b().apply$mcII$sp(index$macro$2)));
      }

   }

   public Vector copy$default$1() {
      return this.copy$default$1$mcF$sp();
   }

   public Vector copy$default$1$mcF$sp() {
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
   public Vector_GenericOps breeze$linalg$operators$Vector_GenericOps$ZippedVectorValues$mcFI$sp$$$outer() {
      return this.$outer;
   }

   public Vector_GenericOps$ZippedVectorValues$mcFI$sp(final Vector_GenericOps $outer, final Vector a$mcF$sp, final Vector b$mcI$sp) {
      super((Vector)null, (Vector)null);
      this.a$mcF$sp = a$mcF$sp;
      this.b$mcI$sp = b$mcI$sp;
   }
}
