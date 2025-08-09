package org.apache.curator.framework.schema;

import java.util.List;
import org.apache.zookeeper.data.ACL;

public class DefaultSchemaValidator implements SchemaValidator {
   public boolean isValid(Schema schema, String path, byte[] data, List acl) {
      return true;
   }
}
