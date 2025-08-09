package org.sparkproject.jetty.security;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.util.IO;
import org.sparkproject.jetty.util.PathWatcher;
import org.sparkproject.jetty.util.StringUtil;
import org.sparkproject.jetty.util.resource.JarFileResource;
import org.sparkproject.jetty.util.resource.PathResource;
import org.sparkproject.jetty.util.resource.Resource;
import org.sparkproject.jetty.util.security.Credential;

public class PropertyUserStore extends UserStore implements PathWatcher.Listener {
   private static final Logger LOG = LoggerFactory.getLogger(PropertyUserStore.class);
   protected Path _configPath;
   protected PathWatcher _pathWatcher;
   protected boolean _hotReload = false;
   protected boolean _firstLoad = true;
   protected List _listeners;

   public String getConfig() {
      return this._configPath != null ? this._configPath.toString() : null;
   }

   public void setConfig(String config) {
      if (config == null) {
         this._configPath = null;
      } else {
         try {
            Resource configResource = Resource.newResource(config);
            if (configResource instanceof JarFileResource) {
               this._configPath = this.extractPackedFile((JarFileResource)configResource);
            } else if (configResource instanceof PathResource) {
               this._configPath = ((PathResource)configResource).getPath();
            } else {
               if (configResource.getFile() == null) {
                  throw new IllegalArgumentException(config);
               }

               this.setConfigFile(configResource.getFile());
            }

         } catch (Exception e) {
            this._configPath = null;
            throw new IllegalStateException(e);
         }
      }
   }

   public Path getConfigPath() {
      return this._configPath;
   }

   private Path extractPackedFile(JarFileResource configResource) throws IOException {
      String uri = configResource.getURI().toASCIIString();
      int colon = uri.lastIndexOf(":");
      int bangSlash = uri.indexOf("!/");
      if (colon >= 0 && bangSlash >= 0 && colon <= bangSlash) {
         String entryPath = StringUtil.sanitizeFileSystemName(uri.substring(colon + 2));
         Path tmpDirectory = Files.createTempDirectory("users_store");
         tmpDirectory.toFile().deleteOnExit();
         Path extractedPath = Paths.get(tmpDirectory.toString(), entryPath);
         Files.deleteIfExists(extractedPath);
         extractedPath.toFile().deleteOnExit();
         IO.copy((InputStream)configResource.getInputStream(), (OutputStream)(new FileOutputStream(extractedPath.toFile())));
         if (this.isHotReload()) {
            LOG.warn("Cannot hot reload from packed configuration: {}", configResource);
            this.setHotReload(false);
         }

         return extractedPath;
      } else {
         throw new IllegalArgumentException("Not resolved JarFile resource: " + uri);
      }
   }

   public void setConfigFile(File configFile) {
      if (configFile == null) {
         this._configPath = null;
      } else {
         this._configPath = configFile.toPath();
      }

   }

   public void setConfigPath(Path configPath) {
      this._configPath = configPath;
   }

   public Resource getConfigResource() {
      return this._configPath == null ? null : new PathResource(this._configPath);
   }

   public boolean isHotReload() {
      return this._hotReload;
   }

   public void setHotReload(boolean enable) {
      if (this.isRunning()) {
         throw new IllegalStateException("Cannot set hot reload while user store is running");
      } else {
         this._hotReload = enable;
      }
   }

   public String toString() {
      return String.format("%s[cfg=%s]", super.toString(), this._configPath);
   }

   protected void loadUsers() throws IOException {
      if (this._configPath == null) {
         throw new IllegalStateException("No config path set");
      } else {
         if (LOG.isDebugEnabled()) {
            LOG.debug("Loading {} from {}", this, this._configPath);
         }

         Resource config = this.getConfigResource();
         if (!config.exists()) {
            throw new IllegalStateException("Config does not exist: " + String.valueOf(config));
         } else {
            Properties properties = new Properties();
            InputStream inputStream = config.getInputStream();

            try {
               properties.load(inputStream);
            } catch (Throwable var13) {
               if (inputStream != null) {
                  try {
                     inputStream.close();
                  } catch (Throwable var12) {
                     var13.addSuppressed(var12);
                  }
               }

               throw var13;
            }

            if (inputStream != null) {
               inputStream.close();
            }

            Set<String> known = new HashSet();

            for(Map.Entry entry : properties.entrySet()) {
               String username = ((String)entry.getKey()).trim();
               String credentials = ((String)entry.getValue()).trim();
               String roles = null;
               int c = credentials.indexOf(44);
               if (c >= 0) {
                  roles = credentials.substring(c + 1).trim();
                  credentials = credentials.substring(0, c).trim();
               }

               if (username.length() > 0) {
                  String[] roleArray = IdentityService.NO_ROLES;
                  if (roles != null && roles.length() > 0) {
                     roleArray = StringUtil.csvSplit(roles);
                  }

                  known.add(username);
                  Credential credential = Credential.getCredential(credentials);
                  this.addUser(username, credential, roleArray);
                  this.notifyUpdate(username, credential, roleArray);
               }
            }

            List<String> currentlyKnownUsers = new ArrayList(this._users.keySet());
            if (!this._firstLoad) {
               for(String user : currentlyKnownUsers) {
                  if (!known.contains(user)) {
                     this.removeUser(user);
                     this.notifyRemove(user);
                  }
               }
            }

            this._firstLoad = false;
            if (LOG.isDebugEnabled()) {
               LOG.debug("Loaded {} from {}", this, this._configPath);
            }

         }
      }
   }

   protected void doStart() throws Exception {
      super.doStart();
      this.loadUsers();
      if (this.isHotReload() && this._configPath != null) {
         this._pathWatcher = new PathWatcher();
         this._pathWatcher.watch(this._configPath);
         this._pathWatcher.addListener(this);
         this._pathWatcher.setNotifyExistingOnStart(false);
         this._pathWatcher.start();
      }

   }

   public void onPathWatchEvent(PathWatcher.PathWatchEvent event) {
      try {
         if (LOG.isDebugEnabled()) {
            LOG.debug("Path watch event: {}", event.getType());
         }

         this.loadUsers();
      } catch (IOException e) {
         LOG.warn("Unable to load users", e);
      }

   }

   protected void doStop() throws Exception {
      super.doStop();
      if (this._pathWatcher != null) {
         this._pathWatcher.stop();
      }

      this._pathWatcher = null;
   }

   private void notifyUpdate(String username, Credential credential, String[] roleArray) {
      if (this._listeners != null) {
         for(UserListener listener : this._listeners) {
            listener.update(username, credential, roleArray);
         }
      }

   }

   private void notifyRemove(String username) {
      if (this._listeners != null) {
         for(UserListener listener : this._listeners) {
            listener.remove(username);
         }
      }

   }

   public void registerUserListener(UserListener listener) {
      if (this._listeners == null) {
         this._listeners = new ArrayList();
      }

      this._listeners.add(listener);
   }

   public interface UserListener {
      void update(String var1, Credential var2, String[] var3);

      void remove(String var1);
   }
}
