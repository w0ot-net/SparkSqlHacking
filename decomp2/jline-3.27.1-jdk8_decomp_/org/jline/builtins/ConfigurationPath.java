package org.jline.builtins;

import java.io.IOException;
import java.nio.file.Path;

public class ConfigurationPath {
   private Path appConfig;
   private Path userConfig;

   public ConfigurationPath(Path appConfig, Path userConfig) {
      this.appConfig = appConfig;
      this.userConfig = userConfig;
   }

   public Path getConfig(String name) {
      Path out = null;
      if (this.userConfig != null && this.userConfig.resolve(name).toFile().exists()) {
         out = this.userConfig.resolve(name);
      } else if (this.appConfig != null && this.appConfig.resolve(name).toFile().exists()) {
         out = this.appConfig.resolve(name);
      }

      return out;
   }

   public Path getUserConfig(String name) throws IOException {
      return this.getUserConfig(name, false);
   }

   public Path getUserConfig(String name, boolean create) throws IOException {
      Path out = null;
      if (this.userConfig != null) {
         if (!this.userConfig.resolve(name).toFile().exists() && create) {
            this.userConfig.resolve(name).toFile().createNewFile();
         }

         if (this.userConfig.resolve(name).toFile().exists()) {
            out = this.userConfig.resolve(name);
         }
      }

      return out;
   }
}
