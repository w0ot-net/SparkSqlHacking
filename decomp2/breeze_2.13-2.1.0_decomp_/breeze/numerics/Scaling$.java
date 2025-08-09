package breeze.numerics;

public final class Scaling$ implements Scaling {
   public static final Scaling$ MODULE$ = new Scaling$();
   private static final int scaleConstant;

   static {
      Scaling.$init$(MODULE$);
      scaleConstant = 145;
   }

   public int scaleArray(final double[] scores, final int currentScale) {
      return Scaling.scaleArray$(this, scores, currentScale);
   }

   public int computeScaleDelta(final double[] scores) {
      return Scaling.computeScaleDelta$(this, scores);
   }

   public int determineScale(final double score, final int oldScale) {
      return Scaling.determineScale$(this, score, oldScale);
   }

   public void scaleArrayToScale(final double[] scores, final int currentScale, final int targetScale) {
      Scaling.scaleArrayToScale$(this, scores, currentScale, targetScale);
   }

   public int sumArrays(final double[] src, final int srcScale, final double[] dest, final int destScale) {
      return Scaling.sumArrays$(this, src, srcScale, dest, destScale);
   }

   public double unscaleValue(final double score, final int currentScale) {
      return Scaling.unscaleValue$(this, score, currentScale);
   }

   public double scaleValue(final double score, final int currentScale, final int targetScale) {
      return Scaling.scaleValue$(this, score, currentScale, targetScale);
   }

   public double toLogSpace(final double score, final int currentScale) {
      return Scaling.toLogSpace$(this, score, currentScale);
   }

   public int scaleConstant() {
      return scaleConstant;
   }

   private Scaling$() {
   }
}
