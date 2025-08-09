package org.snakeyaml.engine.v2.resolver;

import org.snakeyaml.engine.v2.nodes.Tag;

public interface ScalarResolver {
   Tag resolve(String var1, Boolean var2);
}
