package org.snakeyaml.engine.v2.constructor;

import java.util.HashMap;
import java.util.Map;
import org.snakeyaml.engine.v2.api.ConstructNode;
import org.snakeyaml.engine.v2.nodes.Node;
import org.snakeyaml.engine.v2.nodes.ScalarNode;

public abstract class ConstructScalar implements ConstructNode {
   protected static final Map BOOL_VALUES = new HashMap();

   protected String constructScalar(Node node) {
      return ((ScalarNode)node).getValue();
   }

   static {
      BOOL_VALUES.put("true", Boolean.TRUE);
      BOOL_VALUES.put("false", Boolean.FALSE);
   }
}
