package org.snakeyaml.engine.v2.constructor.json;

import org.snakeyaml.engine.v2.constructor.ConstructScalar;
import org.snakeyaml.engine.v2.nodes.Node;

public class ConstructYamlJsonBool extends ConstructScalar {
   public Object construct(Node node) {
      String val = this.constructScalar(node);
      return BOOL_VALUES.get(val);
   }
}
