package org.snakeyaml.engine.v2.constructor.core;

import org.snakeyaml.engine.v2.constructor.json.ConstructYamlJsonFloat;
import org.snakeyaml.engine.v2.nodes.Node;
import org.snakeyaml.engine.v2.nodes.ScalarNode;

public class ConstructYamlCoreFloat extends ConstructYamlJsonFloat {
   protected String constructScalar(Node node) {
      return ((ScalarNode)node).getValue().toLowerCase();
   }
}
