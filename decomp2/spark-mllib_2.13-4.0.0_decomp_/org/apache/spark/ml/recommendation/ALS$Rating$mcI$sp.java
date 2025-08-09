package org.apache.spark.ml.recommendation;

public class ALS$Rating$mcI$sp extends ALS.Rating {
   public final int user$mcI$sp;
   public final int item$mcI$sp;

   public int user$mcI$sp() {
      return this.user$mcI$sp;
   }

   public int user() {
      return this.user$mcI$sp();
   }

   public int item$mcI$sp() {
      return this.item$mcI$sp;
   }

   public int item() {
      return this.item$mcI$sp();
   }

   public int copy$default$1() {
      return this.copy$default$1$mcI$sp();
   }

   public int copy$default$1$mcI$sp() {
      return this.user();
   }

   public int copy$default$2() {
      return this.copy$default$2$mcI$sp();
   }

   public int copy$default$2$mcI$sp() {
      return this.item();
   }

   public boolean specInstance$() {
      return true;
   }

   public ALS$Rating$mcI$sp(final int user$mcI$sp, final int item$mcI$sp, final float rating) {
      super((Object)null, (Object)null, rating);
      this.user$mcI$sp = user$mcI$sp;
      this.item$mcI$sp = item$mcI$sp;
   }
}
