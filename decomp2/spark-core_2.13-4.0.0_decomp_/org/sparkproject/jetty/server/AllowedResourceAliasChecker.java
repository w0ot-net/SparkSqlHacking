package org.sparkproject.jetty.server;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.server.handler.ContextHandler;
import org.sparkproject.jetty.util.component.AbstractLifeCycle;
import org.sparkproject.jetty.util.component.LifeCycle;
import org.sparkproject.jetty.util.resource.PathResource;
import org.sparkproject.jetty.util.resource.Resource;

public class AllowedResourceAliasChecker extends AbstractLifeCycle implements ContextHandler.AliasCheck {
   private static final Logger LOG = LoggerFactory.getLogger(AllowedResourceAliasChecker.class);
   protected static final LinkOption[] FOLLOW_LINKS = new LinkOption[0];
   protected static final LinkOption[] NO_FOLLOW_LINKS;
   private final ContextHandler _contextHandler;
   private final Supplier _resourceBaseSupplier;
   private final List _protected;
   private final AllowedResourceAliasCheckListener _listener;
   private boolean _initialized;
   protected Path _base;

   public AllowedResourceAliasChecker(ContextHandler contextHandler) {
      Objects.requireNonNull(contextHandler);
      this(contextHandler, contextHandler::getBaseResource);
   }

   public AllowedResourceAliasChecker(ContextHandler contextHandler, Resource baseResource) {
      this(contextHandler, (Supplier)(() -> baseResource));
   }

   public AllowedResourceAliasChecker(ContextHandler contextHandler, Supplier resourceBaseSupplier) {
      this._protected = new ArrayList();
      this._listener = new AllowedResourceAliasCheckListener();
      this._contextHandler = (ContextHandler)Objects.requireNonNull(contextHandler);
      this._resourceBaseSupplier = (Supplier)Objects.requireNonNull(resourceBaseSupplier);
   }

   protected ContextHandler getContextHandler() {
      return this._contextHandler;
   }

   private void extractBaseResourceFromContext() {
      this._base = this.getPath((Resource)this._resourceBaseSupplier.get());
      if (this._base != null) {
         try {
            if (Files.exists(this._base, NO_FOLLOW_LINKS)) {
               this._base = this._base.toRealPath(FOLLOW_LINKS);
            }

            String[] protectedTargets = this._contextHandler.getProtectedTargets();
            if (protectedTargets != null) {
               for(String s : protectedTargets) {
                  this._protected.add(this._base.getFileSystem().getPath(this._base.toString(), s));
               }
            }
         } catch (IOException e) {
            LOG.warn("Base resource failure ({} is disabled): {}", new Object[]{this.getClass().getName(), this._base, e});
            this._base = null;
         }

      }
   }

   protected void initialize() {
      this.extractBaseResourceFromContext();
      this._initialized = true;
   }

   protected void doStart() throws Exception {
      if (this._contextHandler.isStarted()) {
         this.initialize();
      } else {
         this._contextHandler.addEventListener(this._listener);
      }

   }

   protected void doStop() throws Exception {
      this._contextHandler.removeEventListener(this._listener);
      this._base = null;
      this._protected.clear();
   }

   public boolean check(String pathInContext, Resource resource) {
      if (!this._initialized) {
         this.extractBaseResourceFromContext();
      }

      if (this._base == null) {
         return false;
      } else {
         try {
            if (!resource.exists()) {
               return false;
            } else {
               Path path = this.getPath(resource);
               return path == null ? false : this.check(pathInContext, path);
            }
         } catch (Throwable t) {
            if (LOG.isDebugEnabled()) {
               LOG.debug("Failed to check alias", t);
            }

            return false;
         }
      }
   }

   protected boolean check(String pathInContext, Path path) {
      return this.isAllowed(getRealPath(path));
   }

   protected boolean isAllowed(Path path) {
      if (path != null && Files.exists(path, new LinkOption[0])) {
         while(path != null) {
            if (this.isSameFile(path, this._base)) {
               return true;
            }

            for(Path p : this._protected) {
               if (this.isSameFile(path, p)) {
                  return false;
               }
            }

            path = path.getParent();
         }
      }

      return false;
   }

   protected boolean isSameFile(Path path1, Path path2) {
      if (Objects.equals(path1, path2)) {
         return true;
      } else {
         try {
            if (Files.isSameFile(path1, path2)) {
               return true;
            }
         } catch (Throwable t) {
            if (LOG.isDebugEnabled()) {
               LOG.debug("ignored", t);
            }
         }

         return false;
      }
   }

   private static Path getRealPath(Path path) {
      if (path != null && Files.exists(path, new LinkOption[0])) {
         try {
            path = path.toRealPath(FOLLOW_LINKS);
            if (Files.exists(path, new LinkOption[0])) {
               return path;
            }
         } catch (IOException e) {
            if (LOG.isDebugEnabled()) {
               LOG.debug("No real path for {}", path, e);
            }
         }

         return null;
      } else {
         return null;
      }
   }

   protected Path getPath(Resource resource) {
      try {
         if (resource instanceof PathResource) {
            return ((PathResource)resource).getPath();
         } else {
            return resource == null ? null : resource.getFile().toPath();
         }
      } catch (Throwable t) {
         LOG.trace("getPath() failed", t);
         return null;
      }
   }

   public String toString() {
      String[] protectedTargets = this._contextHandler.getProtectedTargets();
      return String.format("%s@%x{base=%s,protected=%s}", this.getClass().getSimpleName(), this.hashCode(), this._base, protectedTargets == null ? null : Arrays.asList(protectedTargets));
   }

   static {
      NO_FOLLOW_LINKS = new LinkOption[]{LinkOption.NOFOLLOW_LINKS};
   }

   private class AllowedResourceAliasCheckListener implements LifeCycle.Listener {
      public void lifeCycleStarted(LifeCycle event) {
         AllowedResourceAliasChecker.this.initialize();
      }
   }
}
