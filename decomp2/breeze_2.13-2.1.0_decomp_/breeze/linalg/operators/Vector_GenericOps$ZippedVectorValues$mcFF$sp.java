package breeze.linalg.operators;

import breeze.linalg.Vector;
import scala.Function2;
import scala.runtime.BoxesRunTime;

public class Vector_GenericOps$ZippedVectorValues$mcFF$sp extends Vector_GenericOps.ZippedVectorValues {
   public final Vector a$mcF$sp;
   public final Vector b$mcF$sp;

   public Vector a$mcF$sp() {
      return this.a$mcF$sp;
   }

   public Vector a() {
      return this.a$mcF$sp();
   }

   public Vector b$mcF$sp() {
      return this.b$mcF$sp;
   }

   public Vector b() {
      return this.b$mcF$sp();
   }

   public void foreach(final Function2 f) {
      this.foreach$mcFF$sp(f);
   }

   public void foreach$mcFF$sp(final Function2 f) {
      int index$macro$2 = 0;

      for(int limit$macro$4 = this.a().length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
         f.apply(BoxesRunTime.boxToFloat(this.a().apply$mcIF$sp(index$macro$2)), BoxesRunTime.boxToFloat(this.b().apply$mcIF$sp(index$macro$2)));
      }

   }

   public Vector copy$default$1() {
      return this.copy$default$1$mcF$sp();
   }

   public Vector copy$default$1$mcF$sp() {
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
   public Vector_GenericOps breeze$linalg$operators$Vector_GenericOps$ZippedVectorValues$mcFF$sp$$$outer() {
      return this.$outer;
   }

   public Vector_GenericOps$ZippedVectorValues$mcFF$sp(final Vector_GenericOps $outer, final Vector a$mcF$sp, final Vector b$mcF$sp) {
      super((Vector)null, (Vector)null);
      this.a$mcF$sp = a$mcF$sp;
      this.b$mcF$sp = b$mcF$sp;
   }
}
