package org.sparkproject.jetty.server.handler;

import java.nio.file.Files;
import java.nio.file.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.util.resource.PathResource;
import org.sparkproject.jetty.util.resource.Resource;

/** @deprecated */
@Deprecated
public class AllowSymLinkAliasChecker implements ContextHandler.AliasCheck {
   private static final Logger LOG = LoggerFactory.getLogger(AllowSymLinkAliasChecker.class);

   public AllowSymLinkAliasChecker() {
      LOG.warn("Deprecated, use SymlinkAllowedResourceAliasChecker instead.");
   }

   public boolean check(String pathInContext, Resource resource) {
      if (!(resource instanceof PathResource)) {
         return false;
      } else {
         PathResource pathResource = (PathResource)resource;

         try {
            Path path = pathResource.getPath();
            Path alias = pathResource.getAliasPath();
            if (PathResource.isSameName(alias, path)) {
               return false;
            }

            if (this.hasSymbolicLink(path) && Files.isSameFile(path, alias)) {
               if (LOG.isDebugEnabled()) {
                  LOG.debug("Allow symlink {} --> {}", resource, pathResource.getAliasPath());
               }

               return true;
            }
         } catch (Exception e) {
            LOG.trace("IGNORED", e);
         }

         return false;
      }
   }

   private boolean hasSymbolicLink(Path path) {
      if (Files.isSymbolicLink(path)) {
         return true;
      } else {
         Path base = path.getRoot();

         for(Path segment : path) {
            base = base.resolve(segment);
            if (Files.isSymbolicLink(base)) {
               return true;
            }
         }

         return false;
      }
   }
}
