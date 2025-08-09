package org.sparkproject.jetty.server;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.server.handler.ContextHandler;
import org.sparkproject.jetty.util.resource.PathResource;
import org.sparkproject.jetty.util.resource.Resource;

/** @deprecated */
@Deprecated
public class SameFileAliasChecker implements ContextHandler.AliasCheck {
   private static final Logger LOG = LoggerFactory.getLogger(SameFileAliasChecker.class);

   public SameFileAliasChecker() {
      LOG.warn("SameFileAliasChecker is deprecated");
   }

   public boolean check(String pathInContext, Resource resource) {
      if (File.separatorChar != '/' && pathInContext.indexOf(File.separatorChar) >= 0) {
         return false;
      } else if (!(resource instanceof PathResource)) {
         return false;
      } else {
         try {
            PathResource pathResource = (PathResource)resource;
            Path path = pathResource.getPath();
            Path alias = pathResource.getAliasPath();
            if (Files.isSameFile(path, alias)) {
               if (LOG.isDebugEnabled()) {
                  LOG.debug("Allow alias to same file {} --> {}", path, alias);
               }

               return true;
            }
         } catch (IOException e) {
            LOG.trace("IGNORED", e);
         }

         return false;
      }
   }
}
