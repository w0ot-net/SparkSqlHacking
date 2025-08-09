package org.snakeyaml.engine.v2.schema;

import java.util.HashMap;
import java.util.Map;
import org.snakeyaml.engine.v2.resolver.FailsafeScalarResolver;
import org.snakeyaml.engine.v2.resolver.ScalarResolver;

public class FailsafeSchema implements Schema {
   public ScalarResolver getScalarResolver() {
      return new FailsafeScalarResolver();
   }

   public Map getSchemaTagConstructors() {
      return new HashMap();
   }
}
