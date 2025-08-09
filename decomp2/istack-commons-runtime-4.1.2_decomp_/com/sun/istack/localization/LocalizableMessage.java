package com.sun.istack.localization;

import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;

public final class LocalizableMessage implements Localizable {
   private final String _bundlename;
   private final LocalizableMessageFactory.ResourceBundleSupplier _rbSupplier;
   private final String _key;
   private final Object[] _args;

   /** @deprecated */
   @Deprecated
   public LocalizableMessage(String bundlename, String key, Object... args) {
      this(bundlename, (LocalizableMessageFactory.ResourceBundleSupplier)null, key, args);
   }

   public LocalizableMessage(String bundlename, LocalizableMessageFactory.ResourceBundleSupplier rbSupplier, String key, Object... args) {
      this._bundlename = bundlename;
      this._rbSupplier = rbSupplier;
      this._key = key;
      if (args == null) {
         args = new Object[0];
      }

      this._args = args;
   }

   public String getKey() {
      return this._key;
   }

   public Object[] getArguments() {
      return Arrays.copyOf(this._args, this._args.length);
   }

   public String getResourceBundleName() {
      return this._bundlename;
   }

   public ResourceBundle getResourceBundle(Locale locale) {
      return this._rbSupplier == null ? null : this._rbSupplier.getResourceBundle(locale);
   }
}
