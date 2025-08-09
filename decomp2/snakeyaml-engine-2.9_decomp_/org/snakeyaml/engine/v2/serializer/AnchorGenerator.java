package org.snakeyaml.engine.v2.serializer;

import org.snakeyaml.engine.v2.common.Anchor;
import org.snakeyaml.engine.v2.nodes.Node;

public interface AnchorGenerator {
   Anchor nextAnchor(Node var1);
}
