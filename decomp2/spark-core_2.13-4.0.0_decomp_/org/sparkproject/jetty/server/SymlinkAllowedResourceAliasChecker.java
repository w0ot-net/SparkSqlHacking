package org.sparkproject.jetty.server;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.server.handler.ContextHandler;
import org.sparkproject.jetty.util.resource.Resource;

public class SymlinkAllowedResourceAliasChecker extends AllowedResourceAliasChecker {
   private static final Logger LOG = LoggerFactory.getLogger(SymlinkAllowedResourceAliasChecker.class);

   public SymlinkAllowedResourceAliasChecker(ContextHandler contextHandler) {
      super(contextHandler);
   }

   public SymlinkAllowedResourceAliasChecker(ContextHandler contextHandler, Resource baseResource) {
      super(contextHandler, baseResource);
   }

   protected boolean check(String pathInContext, Path path) {
      if (this._base == null) {
         return false;
      } else if (File.separatorChar != '/' && pathInContext.indexOf(File.separatorChar) >= 0) {
         return false;
      } else {
         String[] segments = pathInContext.substring(1).split("/");
         Path fromBase = this._base;
         StringBuilder realURI = new StringBuilder();

         try {
            for(String segment : segments) {
               fromBase = fromBase.resolve(segment);
               realURI.append("/").append(fromBase.toRealPath(NO_FOLLOW_LINKS).getFileName());
               if (Files.isSymbolicLink(fromBase)) {
                  return !this.getContextHandler().isProtectedTarget(realURI.toString());
               }

               if (!this.isAllowed(fromBase)) {
                  return false;
               }
            }
         } catch (Throwable t) {
            if (LOG.isDebugEnabled()) {
               LOG.debug("Failed to check alias", t);
            }

            return false;
         }

         return this.isSameFile(fromBase, path);
      }
   }
}
