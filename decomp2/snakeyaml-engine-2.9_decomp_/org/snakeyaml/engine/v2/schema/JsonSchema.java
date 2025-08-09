package org.snakeyaml.engine.v2.schema;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import org.snakeyaml.engine.v2.constructor.ConstructYamlNull;
import org.snakeyaml.engine.v2.constructor.json.ConstructOptionalClass;
import org.snakeyaml.engine.v2.constructor.json.ConstructUuidClass;
import org.snakeyaml.engine.v2.constructor.json.ConstructYamlBinary;
import org.snakeyaml.engine.v2.constructor.json.ConstructYamlJsonBool;
import org.snakeyaml.engine.v2.constructor.json.ConstructYamlJsonFloat;
import org.snakeyaml.engine.v2.constructor.json.ConstructYamlJsonInt;
import org.snakeyaml.engine.v2.nodes.Tag;
import org.snakeyaml.engine.v2.resolver.JsonScalarResolver;
import org.snakeyaml.engine.v2.resolver.ScalarResolver;

public class JsonSchema implements Schema {
   private final Map tagConstructors = new HashMap();
   private final ScalarResolver scalarResolver = new JsonScalarResolver();

   public JsonSchema() {
      this.tagConstructors.put(Tag.NULL, new ConstructYamlNull());
      this.tagConstructors.put(Tag.BOOL, new ConstructYamlJsonBool());
      this.tagConstructors.put(Tag.INT, new ConstructYamlJsonInt());
      this.tagConstructors.put(Tag.FLOAT, new ConstructYamlJsonFloat());
      this.tagConstructors.put(Tag.BINARY, new ConstructYamlBinary());
      this.tagConstructors.put(new Tag(UUID.class), new ConstructUuidClass());
      this.tagConstructors.put(new Tag(Optional.class), new ConstructOptionalClass(this.getScalarResolver()));
   }

   public ScalarResolver getScalarResolver() {
      return this.scalarResolver;
   }

   public Map getSchemaTagConstructors() {
      return this.tagConstructors;
   }
}
