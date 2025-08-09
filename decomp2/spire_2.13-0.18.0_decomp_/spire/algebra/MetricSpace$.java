package spire.algebra;

import algebra.ring.Rng;

public final class MetricSpace$ implements MetricSpace0 {
   public static final MetricSpace$ MODULE$ = new MetricSpace$();

   static {
      MetricSpace0.$init$(MODULE$);
   }

   public MetricSpace realMetricSpace(final IsReal R0, final Rng R1) {
      return MetricSpace0.realMetricSpace$(this, R0, R1);
   }

   public MetricSpace realMetricSpace$mDc$sp(final IsReal R0, final Rng R1) {
      return MetricSpace0.realMetricSpace$mDc$sp$(this, R0, R1);
   }

   public MetricSpace realMetricSpace$mFc$sp(final IsReal R0, final Rng R1) {
      return MetricSpace0.realMetricSpace$mFc$sp$(this, R0, R1);
   }

   public MetricSpace realMetricSpace$mIc$sp(final IsReal R0, final Rng R1) {
      return MetricSpace0.realMetricSpace$mIc$sp$(this, R0, R1);
   }

   public MetricSpace realMetricSpace$mJc$sp(final IsReal R0, final Rng R1) {
      return MetricSpace0.realMetricSpace$mJc$sp$(this, R0, R1);
   }

   public final MetricSpace apply(final MetricSpace V) {
      return V;
   }

   public Object distance(final Object v, final Object w, final MetricSpace metric) {
      return metric.distance(v, w);
   }

   public boolean closeTo(final Object x, final Object y, final double tolerance, final IsReal R, final MetricSpace metric) {
      return R.toDouble(metric.distance(x, y)) <= tolerance;
   }

   public final MetricSpace apply$mDc$sp(final MetricSpace V) {
      return V;
   }

   public final MetricSpace apply$mFc$sp(final MetricSpace V) {
      return V;
   }

   public final MetricSpace apply$mIc$sp(final MetricSpace V) {
      return V;
   }

   public final MetricSpace apply$mJc$sp(final MetricSpace V) {
      return V;
   }

   public double distance$mDc$sp(final Object v, final Object w, final MetricSpace metric) {
      return metric.distance$mcD$sp(v, w);
   }

   public float distance$mFc$sp(final Object v, final Object w, final MetricSpace metric) {
      return metric.distance$mcF$sp(v, w);
   }

   public int distance$mIc$sp(final Object v, final Object w, final MetricSpace metric) {
      return metric.distance$mcI$sp(v, w);
   }

   public long distance$mJc$sp(final Object v, final Object w, final MetricSpace metric) {
      return metric.distance$mcJ$sp(v, w);
   }

   public boolean closeTo$mDc$sp(final Object x, final Object y, final double tolerance, final IsReal R, final MetricSpace metric) {
      return R.toDouble$mcD$sp(metric.distance$mcD$sp(x, y)) <= tolerance;
   }

   public boolean closeTo$mFc$sp(final Object x, final Object y, final double tolerance, final IsReal R, final MetricSpace metric) {
      return R.toDouble$mcF$sp(metric.distance$mcF$sp(x, y)) <= tolerance;
   }

   public boolean closeTo$mIc$sp(final Object x, final Object y, final double tolerance, final IsReal R, final MetricSpace metric) {
      return R.toDouble$mcI$sp(metric.distance$mcI$sp(x, y)) <= tolerance;
   }

   public boolean closeTo$mJc$sp(final Object x, final Object y, final double tolerance, final IsReal R, final MetricSpace metric) {
      return R.toDouble$mcJ$sp(metric.distance$mcJ$sp(x, y)) <= tolerance;
   }

   private MetricSpace$() {
   }
}
