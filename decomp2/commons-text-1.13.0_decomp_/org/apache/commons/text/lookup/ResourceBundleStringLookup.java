package org.apache.commons.text.lookup;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

final class ResourceBundleStringLookup extends AbstractStringLookup {
   static final ResourceBundleStringLookup INSTANCE = new ResourceBundleStringLookup();
   private final String bundleName;

   private ResourceBundleStringLookup() {
      this((String)null);
   }

   ResourceBundleStringLookup(String bundleName) {
      this.bundleName = bundleName;
   }

   ResourceBundle getBundle(String keyBundleName) {
      return ResourceBundle.getBundle(keyBundleName);
   }

   String getString(String keyBundleName, String bundleKey) {
      return this.getBundle(keyBundleName).getString(bundleKey);
   }

   public String lookup(String key) {
      if (key == null) {
         return null;
      } else {
         String[] keys = key.split(SPLIT_STR);
         int keyLen = keys.length;
         boolean anyBundle = this.bundleName == null;
         if (anyBundle && keyLen != 2) {
            throw IllegalArgumentExceptions.format("Bad resource bundle key format [%s]; expected format is BundleName:KeyName.", key);
         } else if (this.bundleName != null && keyLen != 1) {
            throw IllegalArgumentExceptions.format("Bad resource bundle key format [%s]; expected format is KeyName.", key);
         } else {
            String keyBundleName = anyBundle ? keys[0] : this.bundleName;
            String bundleKey = anyBundle ? keys[1] : keys[0];

            try {
               return this.getString(keyBundleName, bundleKey);
            } catch (MissingResourceException var8) {
               return null;
            } catch (Exception e) {
               throw IllegalArgumentExceptions.format(e, "Error looking up resource bundle [%s] and key [%s].", keyBundleName, bundleKey);
            }
         }
      }
   }

   public String toString() {
      return super.toString() + " [bundleName=" + this.bundleName + "]";
   }
}
