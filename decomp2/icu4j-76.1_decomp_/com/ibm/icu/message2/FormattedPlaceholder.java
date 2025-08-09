package com.ibm.icu.message2;

import com.ibm.icu.text.FormattedValue;

/** @deprecated */
@Deprecated
public class FormattedPlaceholder {
   private final FormattedValue formattedValue;
   private final Object inputValue;

   /** @deprecated */
   @Deprecated
   public FormattedPlaceholder(Object inputValue, FormattedValue formattedValue) {
      if (formattedValue == null) {
         throw new IllegalAccessError("Should not try to wrap a null formatted value");
      } else {
         this.inputValue = inputValue;
         this.formattedValue = formattedValue;
      }
   }

   /** @deprecated */
   @Deprecated
   public Object getInput() {
      return this.inputValue;
   }

   /** @deprecated */
   @Deprecated
   public FormattedValue getFormattedValue() {
      return this.formattedValue;
   }

   /** @deprecated */
   @Deprecated
   public String toString() {
      return this.formattedValue.toString();
   }
}
