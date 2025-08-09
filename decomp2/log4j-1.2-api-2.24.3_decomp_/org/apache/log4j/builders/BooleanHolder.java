package org.apache.log4j.builders;

/** @deprecated */
@Deprecated
public class BooleanHolder extends Holder {
   public BooleanHolder() {
      super(Boolean.FALSE);
   }

   public void set(final Boolean value) {
      if (value != null) {
         super.set(value);
      }

   }
}
