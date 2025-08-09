package org.apache.commons.math3.geometry.partitioning;

public interface SubHyperplane {
   SubHyperplane copySelf();

   Hyperplane getHyperplane();

   boolean isEmpty();

   double getSize();

   /** @deprecated */
   @Deprecated
   Side side(Hyperplane var1);

   SplitSubHyperplane split(Hyperplane var1);

   SubHyperplane reunite(SubHyperplane var1);

   public static class SplitSubHyperplane {
      private final SubHyperplane plus;
      private final SubHyperplane minus;

      public SplitSubHyperplane(SubHyperplane plus, SubHyperplane minus) {
         this.plus = plus;
         this.minus = minus;
      }

      public SubHyperplane getPlus() {
         return this.plus;
      }

      public SubHyperplane getMinus() {
         return this.minus;
      }

      public Side getSide() {
         if (this.plus != null && !this.plus.isEmpty()) {
            return this.minus != null && !this.minus.isEmpty() ? Side.BOTH : Side.PLUS;
         } else {
            return this.minus != null && !this.minus.isEmpty() ? Side.MINUS : Side.HYPER;
         }
      }
   }
}
