package org.apache.spark.ml.recommendation;

import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.runtime.BoxesRunTime;

public class ALS$KeyWrapper$mcI$sp extends ALS.KeyWrapper {
   public int key$mcI$sp;

   public int key$mcI$sp() {
      return this.key$mcI$sp;
   }

   public int key() {
      return this.key$mcI$sp();
   }

   public void key$mcI$sp_$eq(final int x$1) {
      this.key$mcI$sp = x$1;
   }

   public void key_$eq(final int x$1) {
      this.key$mcI$sp_$eq(x$1);
   }

   public int compare(final ALS.KeyWrapper that) {
      return this.compare$mcI$sp(that);
   }

   public int compare$mcI$sp(final ALS.KeyWrapper that) {
      return this.org$apache$spark$ml$recommendation$ALS$KeyWrapper$$ord.compare(BoxesRunTime.boxToInteger(this.key()), BoxesRunTime.boxToInteger(that.key$mcI$sp()));
   }

   public ALS$KeyWrapper$mcI$sp setKey(final int key) {
      return this.setKey$mcI$sp(key);
   }

   public ALS$KeyWrapper$mcI$sp setKey$mcI$sp(final int key) {
      this.key_$eq(key);
      return this;
   }

   public boolean specInstance$() {
      return true;
   }

   public ALS$KeyWrapper$mcI$sp(final ClassTag evidence$8, final Ordering ord) {
      super(evidence$8, ord);
   }
}
