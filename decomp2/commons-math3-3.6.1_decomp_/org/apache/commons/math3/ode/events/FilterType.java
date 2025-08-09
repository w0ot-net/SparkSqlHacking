package org.apache.commons.math3.ode.events;

import org.apache.commons.math3.exception.MathInternalError;

public enum FilterType {
   TRIGGER_ONLY_DECREASING_EVENTS {
      protected boolean getTriggeredIncreasing() {
         return false;
      }

      protected Transformer selectTransformer(Transformer previous, double g, boolean forward) {
         if (forward) {
            switch (previous) {
               case UNINITIALIZED:
                  if (g > (double)0.0F) {
                     return Transformer.MAX;
                  } else {
                     if (g < (double)0.0F) {
                        return Transformer.PLUS;
                     }

                     return Transformer.UNINITIALIZED;
                  }
               case PLUS:
                  if (g >= (double)0.0F) {
                     return Transformer.MIN;
                  }

                  return previous;
               case MINUS:
                  if (g >= (double)0.0F) {
                     return Transformer.MAX;
                  }

                  return previous;
               case MIN:
                  if (g <= (double)0.0F) {
                     return Transformer.MINUS;
                  }

                  return previous;
               case MAX:
                  if (g <= (double)0.0F) {
                     return Transformer.PLUS;
                  }

                  return previous;
               default:
                  throw new MathInternalError();
            }
         } else {
            switch (previous) {
               case UNINITIALIZED:
                  if (g > (double)0.0F) {
                     return Transformer.MINUS;
                  } else {
                     if (g < (double)0.0F) {
                        return Transformer.MIN;
                     }

                     return Transformer.UNINITIALIZED;
                  }
               case PLUS:
                  if (g <= (double)0.0F) {
                     return Transformer.MAX;
                  }

                  return previous;
               case MINUS:
                  if (g <= (double)0.0F) {
                     return Transformer.MIN;
                  }

                  return previous;
               case MIN:
                  if (g >= (double)0.0F) {
                     return Transformer.PLUS;
                  }

                  return previous;
               case MAX:
                  if (g >= (double)0.0F) {
                     return Transformer.MINUS;
                  }

                  return previous;
               default:
                  throw new MathInternalError();
            }
         }
      }
   },
   TRIGGER_ONLY_INCREASING_EVENTS {
      protected boolean getTriggeredIncreasing() {
         return true;
      }

      protected Transformer selectTransformer(Transformer previous, double g, boolean forward) {
         if (forward) {
            switch (previous) {
               case UNINITIALIZED:
                  if (g > (double)0.0F) {
                     return Transformer.PLUS;
                  } else {
                     if (g < (double)0.0F) {
                        return Transformer.MIN;
                     }

                     return Transformer.UNINITIALIZED;
                  }
               case PLUS:
                  if (g <= (double)0.0F) {
                     return Transformer.MAX;
                  }

                  return previous;
               case MINUS:
                  if (g <= (double)0.0F) {
                     return Transformer.MIN;
                  }

                  return previous;
               case MIN:
                  if (g >= (double)0.0F) {
                     return Transformer.PLUS;
                  }

                  return previous;
               case MAX:
                  if (g >= (double)0.0F) {
                     return Transformer.MINUS;
                  }

                  return previous;
               default:
                  throw new MathInternalError();
            }
         } else {
            switch (previous) {
               case UNINITIALIZED:
                  if (g > (double)0.0F) {
                     return Transformer.MAX;
                  } else {
                     if (g < (double)0.0F) {
                        return Transformer.MINUS;
                     }

                     return Transformer.UNINITIALIZED;
                  }
               case PLUS:
                  if (g >= (double)0.0F) {
                     return Transformer.MIN;
                  }

                  return previous;
               case MINUS:
                  if (g >= (double)0.0F) {
                     return Transformer.MAX;
                  }

                  return previous;
               case MIN:
                  if (g <= (double)0.0F) {
                     return Transformer.MINUS;
                  }

                  return previous;
               case MAX:
                  if (g <= (double)0.0F) {
                     return Transformer.PLUS;
                  }

                  return previous;
               default:
                  throw new MathInternalError();
            }
         }
      }
   };

   private FilterType() {
   }

   protected abstract boolean getTriggeredIncreasing();

   protected abstract Transformer selectTransformer(Transformer var1, double var2, boolean var4);
}
