package com.sun.istack.localization;

import java.util.Locale;
import java.util.ResourceBundle;

public final class NullLocalizable implements Localizable {
   private final String msg;

   public NullLocalizable(String msg) {
      if (msg == null) {
         throw new IllegalArgumentException();
      } else {
         this.msg = msg;
      }
   }

   public String getKey() {
      return "\u0000";
   }

   public Object[] getArguments() {
      return new Object[]{this.msg};
   }

   public String getResourceBundleName() {
      return "";
   }

   public ResourceBundle getResourceBundle(Locale locale) {
      return null;
   }
}
