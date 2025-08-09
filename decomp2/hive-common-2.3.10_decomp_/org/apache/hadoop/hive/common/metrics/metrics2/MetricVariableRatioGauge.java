package org.apache.hadoop.hive.common.metrics.metrics2;

import com.codahale.metrics.RatioGauge;
import com.codahale.metrics.RatioGauge.Ratio;
import org.apache.hadoop.hive.common.metrics.common.MetricsVariable;

public class MetricVariableRatioGauge extends RatioGauge {
   private final MetricsVariable numerator;
   private final MetricsVariable denominator;

   public MetricVariableRatioGauge(MetricsVariable numerator, MetricsVariable denominator) {
      this.numerator = numerator;
      this.denominator = denominator;
   }

   protected RatioGauge.Ratio getRatio() {
      Integer numValue = (Integer)this.numerator.getValue();
      Integer denomValue = (Integer)this.denominator.getValue();
      return numValue != null && denomValue != null ? Ratio.of(numValue.doubleValue(), denomValue.doubleValue()) : Ratio.of((double)0.0F, (double)0.0F);
   }
}
