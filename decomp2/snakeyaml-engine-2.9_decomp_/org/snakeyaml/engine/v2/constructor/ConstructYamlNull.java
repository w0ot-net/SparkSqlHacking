package org.snakeyaml.engine.v2.constructor;

import org.snakeyaml.engine.v2.nodes.Node;

public class ConstructYamlNull extends ConstructScalar {
   public Object construct(Node node) {
      return null;
   }
}
