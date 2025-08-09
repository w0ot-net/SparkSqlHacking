package org.snakeyaml.engine.v2.constructor.json;

import java.util.UUID;
import org.snakeyaml.engine.v2.constructor.ConstructScalar;
import org.snakeyaml.engine.v2.nodes.Node;

public class ConstructUuidClass extends ConstructScalar {
   public Object construct(Node node) {
      String uuidValue = this.constructScalar(node);
      return UUID.fromString(uuidValue);
   }
}
