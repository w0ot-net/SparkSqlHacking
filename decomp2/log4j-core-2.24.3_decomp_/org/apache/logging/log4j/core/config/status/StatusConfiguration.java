package org.apache.logging.log4j.core.config.status;

import edu.umd.cs.findbugs.annotations.Nullable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.util.FileUtils;
import org.apache.logging.log4j.core.util.NetUtils;
import org.apache.logging.log4j.status.StatusConsoleListener;
import org.apache.logging.log4j.status.StatusLogger;

public class StatusConfiguration {
   private static final StatusLogger LOGGER = StatusLogger.getLogger();
   private final Lock lock = new ReentrantLock();
   private volatile boolean initialized;
   @Nullable
   private PrintStream output;
   @Nullable
   private Level level;

   /** @deprecated */
   @Deprecated
   public void error(final String message) {
      LOGGER.error(message);
   }

   public StatusConfiguration withDestination(@Nullable final String destination) {
      try {
         this.output = parseStreamName(destination);
      } catch (URISyntaxException error) {
         LOGGER.error("Could not parse provided URI: {}", destination, error);
      } catch (FileNotFoundException error) {
         LOGGER.error("File could not be found: {}", destination, error);
      }

      return this;
   }

   @Nullable
   private static PrintStream parseStreamName(@Nullable final String name) throws URISyntaxException, FileNotFoundException {
      if (name != null) {
         if (name.equalsIgnoreCase("out")) {
            return System.out;
         }

         if (name.equalsIgnoreCase("err")) {
            return System.err;
         }

         URI destUri = NetUtils.toURI(name);
         File output = FileUtils.fileFromUri(destUri);
         if (output != null) {
            FileOutputStream fos = new FileOutputStream(output);
            return new PrintStream(fos, true);
         }
      }

      return null;
   }

   public StatusConfiguration withStatus(@Nullable final String level) {
      this.level = Level.toLevel(level, (Level)null);
      if (this.level == null) {
         LOGGER.error("Invalid status level: {}", level);
      }

      return this;
   }

   public StatusConfiguration withStatus(@Nullable final Level level) {
      this.level = level;
      return this;
   }

   /** @deprecated */
   @Deprecated
   public StatusConfiguration withVerbosity(final String verbosity) {
      return this;
   }

   /** @deprecated */
   @Deprecated
   public StatusConfiguration withVerboseClasses(final String... verboseClasses) {
      return this;
   }

   public void initialize() {
      this.lock.lock();

      try {
         if (!this.initialized) {
            StatusConsoleListener fallbackListener = LOGGER.getFallbackListener();
            if (this.output != null) {
               fallbackListener.setStream(this.output);
            }

            if (this.level != null) {
               fallbackListener.setLevel(this.level);
            }

            this.initialized = true;
         }
      } finally {
         this.lock.unlock();
      }

   }

   /** @deprecated */
   @Deprecated
   public static enum Verbosity {
      QUIET,
      VERBOSE;

      /** @deprecated */
      @Deprecated
      public static Verbosity toVerbosity(final String value) {
         return Boolean.parseBoolean(value) ? VERBOSE : QUIET;
      }

      // $FF: synthetic method
      private static Verbosity[] $values() {
         return new Verbosity[]{QUIET, VERBOSE};
      }
   }
}
