package org.apache.spark.ml.recommendation;

public class ALS$Rating$mcJ$sp extends ALS.Rating {
   public final long user$mcJ$sp;
   public final long item$mcJ$sp;

   public long user$mcJ$sp() {
      return this.user$mcJ$sp;
   }

   public long user() {
      return this.user$mcJ$sp();
   }

   public long item$mcJ$sp() {
      return this.item$mcJ$sp;
   }

   public long item() {
      return this.item$mcJ$sp();
   }

   public long copy$default$1() {
      return this.copy$default$1$mcJ$sp();
   }

   public long copy$default$1$mcJ$sp() {
      return this.user();
   }

   public long copy$default$2() {
      return this.copy$default$2$mcJ$sp();
   }

   public long copy$default$2$mcJ$sp() {
      return this.item();
   }

   public boolean specInstance$() {
      return true;
   }

   public ALS$Rating$mcJ$sp(final long user$mcJ$sp, final long item$mcJ$sp, final float rating) {
      super((Object)null, (Object)null, rating);
      this.user$mcJ$sp = user$mcJ$sp;
      this.item$mcJ$sp = item$mcJ$sp;
   }
}
