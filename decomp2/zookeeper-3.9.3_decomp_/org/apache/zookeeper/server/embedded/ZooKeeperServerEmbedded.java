package org.apache.zookeeper.server.embedded;

import java.nio.file.Path;
import java.util.Objects;
import java.util.Properties;
import org.apache.yetus.audience.InterfaceAudience.Public;
import org.apache.yetus.audience.InterfaceStability.Evolving;

@Public
@Evolving
public interface ZooKeeperServerEmbedded extends AutoCloseable {
   static ZookKeeperServerEmbeddedBuilder builder() {
      return new ZookKeeperServerEmbeddedBuilder();
   }

   void start() throws Exception;

   void start(long var1) throws Exception;

   String getConnectionString() throws Exception;

   String getSecureConnectionString() throws Exception;

   void close();

   public static class ZookKeeperServerEmbeddedBuilder {
      private Path baseDir;
      private Properties configuration;
      private ExitHandler exitHandler;

      public ZookKeeperServerEmbeddedBuilder() {
         this.exitHandler = ExitHandler.EXIT;
      }

      public ZookKeeperServerEmbeddedBuilder baseDir(Path baseDir) {
         this.baseDir = (Path)Objects.requireNonNull(baseDir);
         return this;
      }

      public ZookKeeperServerEmbeddedBuilder configuration(Properties configuration) {
         this.configuration = (Properties)Objects.requireNonNull(configuration);
         return this;
      }

      public ZookKeeperServerEmbeddedBuilder exitHandler(ExitHandler exitHandler) {
         this.exitHandler = (ExitHandler)Objects.requireNonNull(exitHandler);
         return this;
      }

      public ZooKeeperServerEmbedded build() throws Exception {
         if (this.baseDir == null) {
            throw new IllegalStateException("baseDir is null");
         } else if (this.configuration == null) {
            throw new IllegalStateException("configuration is null");
         } else {
            return new ZooKeeperServerEmbeddedImpl(this.configuration, this.baseDir, this.exitHandler);
         }
      }
   }
}
