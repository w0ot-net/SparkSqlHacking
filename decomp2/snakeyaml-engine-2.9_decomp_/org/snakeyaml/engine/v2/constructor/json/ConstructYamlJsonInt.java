package org.snakeyaml.engine.v2.constructor.json;

import java.math.BigInteger;
import org.snakeyaml.engine.v2.constructor.ConstructScalar;
import org.snakeyaml.engine.v2.nodes.Node;

public class ConstructYamlJsonInt extends ConstructScalar {
   public Object construct(Node node) {
      String value = this.constructScalar(node);
      return this.createIntNumber(value);
   }

   protected Number createIntNumber(String number) {
      Number result;
      try {
         result = Integer.valueOf(number);
      } catch (NumberFormatException var6) {
         try {
            result = Long.valueOf(number);
         } catch (NumberFormatException var5) {
            result = new BigInteger(number);
         }
      }

      return result;
   }
}
