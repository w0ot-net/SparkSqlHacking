package org.snakeyaml.engine.v2.resolver;

import org.snakeyaml.engine.v2.nodes.Tag;

public class FailsafeScalarResolver extends BaseScalarResolver {
   protected void addImplicitResolvers() {
      this.addImplicitResolver(Tag.NULL, EMPTY, (String)null);
   }
}
