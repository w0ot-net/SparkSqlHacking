package org.apache.commons.math3.ode.events;

import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Precision;

enum Transformer {
   UNINITIALIZED {
      protected double transformed(double g) {
         return (double)0.0F;
      }
   },
   PLUS {
      protected double transformed(double g) {
         return g;
      }
   },
   MINUS {
      protected double transformed(double g) {
         return -g;
      }
   },
   MIN {
      protected double transformed(double g) {
         return FastMath.min(-Precision.SAFE_MIN, FastMath.min(-g, g));
      }
   },
   MAX {
      protected double transformed(double g) {
         return FastMath.max(Precision.SAFE_MIN, FastMath.max(-g, g));
      }
   };

   private Transformer() {
   }

   protected abstract double transformed(double var1);
}
