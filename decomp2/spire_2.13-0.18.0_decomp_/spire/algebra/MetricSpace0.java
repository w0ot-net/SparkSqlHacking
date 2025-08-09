package spire.algebra;

import algebra.ring.Rng;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005I3\u0001b\u0001\u0003\u0011\u0002\u0007\u0005A\u0001\u0003\u0005\u0006\u001f\u0001!\t!\u0005\u0005\u0006+\u0001!\u0019A\u0006\u0002\r\u001b\u0016$(/[2Ta\u0006\u001cW\r\r\u0006\u0003\u000b\u0019\tq!\u00197hK\n\u0014\u0018MC\u0001\b\u0003\u0015\u0019\b/\u001b:f'\t\u0001\u0011\u0002\u0005\u0002\u000b\u001b5\t1BC\u0001\r\u0003\u0015\u00198-\u00197b\u0013\tq1B\u0001\u0004B]f\u0014VMZ\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0004\u0001Q\t!\u0003\u0005\u0002\u000b'%\u0011Ac\u0003\u0002\u0005+:LG/A\bsK\u0006dW*\u001a;sS\u000e\u001c\u0006/Y2f+\t9b\u0004F\u0002\u0019\t&\u0003B!\u0007\u000e\u001d95\tA!\u0003\u0002\u001c\t\tYQ*\u001a;sS\u000e\u001c\u0006/Y2f!\tib\u0004\u0004\u0001\u0005\u0013}\u0011\u0001\u0015!A\u0001\u0006\u0004\u0001#!\u0001*\u0012\u0005\u0005\"\u0003C\u0001\u0006#\u0013\t\u00193BA\u0004O_RD\u0017N\\4\u0011\u0005))\u0013B\u0001\u0014\f\u0005\r\te.\u001f\u0015\u0007=!ZSGO \u0011\u0005)I\u0013B\u0001\u0016\f\u0005-\u0019\b/Z2jC2L'0\u001a32\u000b\rbSf\f\u0018\u000f\u0005)i\u0013B\u0001\u0018\f\u0003\rIe\u000e^\u0019\u0005IA\"DB\u0004\u00022i5\t!G\u0003\u00024!\u00051AH]8pizJ\u0011\u0001D\u0019\u0006GY:\u0014\b\u000f\b\u0003\u0015]J!\u0001O\u0006\u0002\t1{gnZ\u0019\u0005IA\"D\"M\u0003$wqrTH\u0004\u0002\u000by%\u0011QhC\u0001\u0006\r2|\u0017\r^\u0019\u0005IA\"D\"M\u0003$\u0001\u0006\u001b%I\u0004\u0002\u000b\u0003&\u0011!iC\u0001\u0007\t>,(\r\\32\t\u0011\u0002D\u0007\u0004\u0005\u0006\u000b\n\u0001\u001dAR\u0001\u0003%B\u00022!G$\u001d\u0013\tAEA\u0001\u0004JgJ+\u0017\r\u001c\u0005\u0006\u0015\n\u0001\u001daS\u0001\u0003%F\u00022\u0001T(\u001d\u001d\tIR*\u0003\u0002O\t\u00059\u0001/Y2lC\u001e,\u0017B\u0001)R\u0005\r\u0011fn\u001a\u0006\u0003\u001d\u0012\u0001"
)
public interface MetricSpace0 {
   // $FF: synthetic method
   static MetricSpace realMetricSpace$(final MetricSpace0 $this, final IsReal R0, final Rng R1) {
      return $this.realMetricSpace(R0, R1);
   }

   default MetricSpace realMetricSpace(final IsReal R0, final Rng R1) {
      return new MetricSpace(R0, R1) {
         private final IsReal R0$1;
         private final Rng R1$1;

         public double distance$mcD$sp(final Object v, final Object w) {
            return MetricSpace.distance$mcD$sp$(this, v, w);
         }

         public float distance$mcF$sp(final Object v, final Object w) {
            return MetricSpace.distance$mcF$sp$(this, v, w);
         }

         public int distance$mcI$sp(final Object v, final Object w) {
            return MetricSpace.distance$mcI$sp$(this, v, w);
         }

         public long distance$mcJ$sp(final Object v, final Object w) {
            return MetricSpace.distance$mcJ$sp$(this, v, w);
         }

         public Object distance(final Object v, final Object w) {
            return this.R0$1.abs(this.R1$1.minus(v, w));
         }

         public {
            this.R0$1 = R0$1;
            this.R1$1 = R1$1;
         }
      };
   }

   // $FF: synthetic method
   static MetricSpace realMetricSpace$mDc$sp$(final MetricSpace0 $this, final IsReal R0, final Rng R1) {
      return $this.realMetricSpace$mDc$sp(R0, R1);
   }

   default MetricSpace realMetricSpace$mDc$sp(final IsReal R0, final Rng R1) {
      return new MetricSpace$mcD$sp(R0, R1) {
         private final IsReal R0$2;
         private final Rng R1$2;

         public float distance$mcF$sp(final Object v, final Object w) {
            return MetricSpace.distance$mcF$sp$(this, v, w);
         }

         public int distance$mcI$sp(final Object v, final Object w) {
            return MetricSpace.distance$mcI$sp$(this, v, w);
         }

         public long distance$mcJ$sp(final Object v, final Object w) {
            return MetricSpace.distance$mcJ$sp$(this, v, w);
         }

         public double distance(final double v, final double w) {
            return this.distance$mcD$sp(v, w);
         }

         public double distance$mcD$sp(final double v, final double w) {
            return this.R0$2.abs$mcD$sp(this.R1$2.minus$mcD$sp(v, w));
         }

         public {
            this.R0$2 = R0$2;
            this.R1$2 = R1$2;
         }
      };
   }

   // $FF: synthetic method
   static MetricSpace realMetricSpace$mFc$sp$(final MetricSpace0 $this, final IsReal R0, final Rng R1) {
      return $this.realMetricSpace$mFc$sp(R0, R1);
   }

   default MetricSpace realMetricSpace$mFc$sp(final IsReal R0, final Rng R1) {
      return new MetricSpace$mcF$sp(R0, R1) {
         private final IsReal R0$3;
         private final Rng R1$3;

         public double distance$mcD$sp(final Object v, final Object w) {
            return MetricSpace.distance$mcD$sp$(this, v, w);
         }

         public int distance$mcI$sp(final Object v, final Object w) {
            return MetricSpace.distance$mcI$sp$(this, v, w);
         }

         public long distance$mcJ$sp(final Object v, final Object w) {
            return MetricSpace.distance$mcJ$sp$(this, v, w);
         }

         public float distance(final float v, final float w) {
            return this.distance$mcF$sp(v, w);
         }

         public float distance$mcF$sp(final float v, final float w) {
            return this.R0$3.abs$mcF$sp(this.R1$3.minus$mcF$sp(v, w));
         }

         public {
            this.R0$3 = R0$3;
            this.R1$3 = R1$3;
         }
      };
   }

   // $FF: synthetic method
   static MetricSpace realMetricSpace$mIc$sp$(final MetricSpace0 $this, final IsReal R0, final Rng R1) {
      return $this.realMetricSpace$mIc$sp(R0, R1);
   }

   default MetricSpace realMetricSpace$mIc$sp(final IsReal R0, final Rng R1) {
      return new MetricSpace$mcI$sp(R0, R1) {
         private final IsReal R0$4;
         private final Rng R1$4;

         public double distance$mcD$sp(final Object v, final Object w) {
            return MetricSpace.distance$mcD$sp$(this, v, w);
         }

         public float distance$mcF$sp(final Object v, final Object w) {
            return MetricSpace.distance$mcF$sp$(this, v, w);
         }

         public long distance$mcJ$sp(final Object v, final Object w) {
            return MetricSpace.distance$mcJ$sp$(this, v, w);
         }

         public int distance(final int v, final int w) {
            return this.distance$mcI$sp(v, w);
         }

         public int distance$mcI$sp(final int v, final int w) {
            return this.R0$4.abs$mcI$sp(this.R1$4.minus$mcI$sp(v, w));
         }

         public {
            this.R0$4 = R0$4;
            this.R1$4 = R1$4;
         }
      };
   }

   // $FF: synthetic method
   static MetricSpace realMetricSpace$mJc$sp$(final MetricSpace0 $this, final IsReal R0, final Rng R1) {
      return $this.realMetricSpace$mJc$sp(R0, R1);
   }

   default MetricSpace realMetricSpace$mJc$sp(final IsReal R0, final Rng R1) {
      return new MetricSpace$mcJ$sp(R0, R1) {
         private final IsReal R0$5;
         private final Rng R1$5;

         public double distance$mcD$sp(final Object v, final Object w) {
            return MetricSpace.distance$mcD$sp$(this, v, w);
         }

         public float distance$mcF$sp(final Object v, final Object w) {
            return MetricSpace.distance$mcF$sp$(this, v, w);
         }

         public int distance$mcI$sp(final Object v, final Object w) {
            return MetricSpace.distance$mcI$sp$(this, v, w);
         }

         public long distance(final long v, final long w) {
            return this.distance$mcJ$sp(v, w);
         }

         public long distance$mcJ$sp(final long v, final long w) {
            return this.R0$5.abs$mcJ$sp(this.R1$5.minus$mcJ$sp(v, w));
         }

         public {
            this.R0$5 = R0$5;
            this.R1$5 = R1$5;
         }
      };
   }

   static void $init$(final MetricSpace0 $this) {
   }
}
