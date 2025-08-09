package org.apache.commons.text.lookup;

import java.util.Map;
import java.util.Objects;
import java.util.function.BiFunction;

final class BiFunctionStringLookup implements BiStringLookup {
   private final BiFunction biFunction;

   static BiFunctionStringLookup on(BiFunction biFunction) {
      return new BiFunctionStringLookup(biFunction);
   }

   static BiFunctionStringLookup on(Map map) {
      return on((BiFunction)((key, u) -> map.get(key)));
   }

   private BiFunctionStringLookup(BiFunction biFunction) {
      this.biFunction = biFunction;
   }

   public String lookup(String key) {
      return this.lookup(key, (Object)null);
   }

   public String lookup(String key, Object object) {
      if (this.biFunction == null) {
         return null;
      } else {
         R obj;
         try {
            obj = (R)this.biFunction.apply(key, object);
         } catch (NullPointerException | IllegalArgumentException | SecurityException var5) {
            return null;
         }

         return Objects.toString(obj, (String)null);
      }
   }

   public String toString() {
      return super.toString() + " [function=" + this.biFunction + "]";
   }
}
