package org.snakeyaml.engine.v2.schema;

import java.util.Map;
import org.snakeyaml.engine.v2.resolver.ScalarResolver;

public interface Schema {
   ScalarResolver getScalarResolver();

   Map getSchemaTagConstructors();
}
