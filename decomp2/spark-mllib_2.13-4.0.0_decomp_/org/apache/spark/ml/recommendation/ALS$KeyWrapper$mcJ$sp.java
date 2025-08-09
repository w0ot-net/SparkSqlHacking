package org.apache.spark.ml.recommendation;

import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.runtime.BoxesRunTime;

public class ALS$KeyWrapper$mcJ$sp extends ALS.KeyWrapper {
   public long key$mcJ$sp;

   public long key$mcJ$sp() {
      return this.key$mcJ$sp;
   }

   public long key() {
      return this.key$mcJ$sp();
   }

   public void key$mcJ$sp_$eq(final long x$1) {
      this.key$mcJ$sp = x$1;
   }

   public void key_$eq(final long x$1) {
      this.key$mcJ$sp_$eq(x$1);
   }

   public int compare(final ALS.KeyWrapper that) {
      return this.compare$mcJ$sp(that);
   }

   public int compare$mcJ$sp(final ALS.KeyWrapper that) {
      return this.org$apache$spark$ml$recommendation$ALS$KeyWrapper$$ord.compare(BoxesRunTime.boxToLong(this.key()), BoxesRunTime.boxToLong(that.key$mcJ$sp()));
   }

   public ALS$KeyWrapper$mcJ$sp setKey(final long key) {
      return this.setKey$mcJ$sp(key);
   }

   public ALS$KeyWrapper$mcJ$sp setKey$mcJ$sp(final long key) {
      this.key_$eq(key);
      return this;
   }

   public boolean specInstance$() {
      return true;
   }

   public ALS$KeyWrapper$mcJ$sp(final ClassTag evidence$8, final Ordering ord) {
      super(evidence$8, ord);
   }
}
