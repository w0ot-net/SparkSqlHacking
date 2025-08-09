package org.glassfish.jaxb.runtime.v2.schemagen;

import jakarta.xml.bind.SchemaOutputResolver;
import java.io.IOException;
import java.util.logging.Logger;
import javax.xml.transform.Result;
import org.glassfish.jaxb.core.Utils;

final class FoolProofResolver extends SchemaOutputResolver {
   private static final Logger logger = Utils.getClassLogger();
   private final SchemaOutputResolver resolver;

   public FoolProofResolver(SchemaOutputResolver resolver) {
      assert resolver != null;

      this.resolver = resolver;
   }

   public Result createOutput(String namespaceUri, String suggestedFileName) throws IOException {
      logger.entering(this.getClass().getName(), "createOutput", new Object[]{namespaceUri, suggestedFileName});
      Result r = this.resolver.createOutput(namespaceUri, suggestedFileName);
      if (r != null) {
         String sysId = r.getSystemId();
         logger.finer("system ID = " + sysId);
         if (sysId == null) {
            throw new AssertionError("system ID cannot be null");
         }
      }

      logger.exiting(this.getClass().getName(), "createOutput", r);
      return r;
   }
}
