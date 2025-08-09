package org.apache.commons.text.lookup;

import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

final class FunctionStringLookup extends AbstractStringLookup {
   private final Function function;

   static FunctionStringLookup on(Function function) {
      return new FunctionStringLookup(function);
   }

   static FunctionStringLookup on(Map map) {
      Map var10000 = StringLookupFactory.toMap(map);
      Objects.requireNonNull(var10000);
      return on(var10000::get);
   }

   private FunctionStringLookup(Function function) {
      this.function = function;
   }

   public String lookup(String key) {
      if (this.function == null) {
         return null;
      } else {
         V obj;
         try {
            obj = (V)this.function.apply(key);
         } catch (NullPointerException | IllegalArgumentException | SecurityException var4) {
            return null;
         }

         return Objects.toString(obj, (String)null);
      }
   }

   public String toString() {
      return super.toString() + " [function=" + this.function + "]";
   }
}
