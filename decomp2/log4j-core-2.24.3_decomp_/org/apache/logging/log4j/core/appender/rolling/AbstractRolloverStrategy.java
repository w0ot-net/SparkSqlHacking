package org.apache.logging.log4j.core.appender.rolling;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LoggingException;
import org.apache.logging.log4j.core.appender.rolling.action.Action;
import org.apache.logging.log4j.core.appender.rolling.action.CompositeAction;
import org.apache.logging.log4j.core.lookup.StrSubstitutor;
import org.apache.logging.log4j.core.pattern.NotANumber;
import org.apache.logging.log4j.core.util.Integers;
import org.apache.logging.log4j.status.StatusLogger;

public abstract class AbstractRolloverStrategy implements RolloverStrategy {
   protected static final Logger LOGGER = StatusLogger.getLogger();
   public static final Pattern PATTERN_COUNTER = Pattern.compile(".*%(?<ZEROPAD>0)?(?<PADDING>\\d+)?i.*");
   protected final StrSubstitutor strSubstitutor;

   protected AbstractRolloverStrategy(final StrSubstitutor strSubstitutor) {
      this.strSubstitutor = strSubstitutor;
   }

   public StrSubstitutor getStrSubstitutor() {
      return this.strSubstitutor;
   }

   protected Action merge(final Action compressAction, final List custom, final boolean stopOnError) {
      if (custom.isEmpty()) {
         return compressAction;
      } else if (compressAction == null) {
         return new CompositeAction(custom, stopOnError);
      } else {
         List<Action> all = new ArrayList();
         all.add(compressAction);
         all.addAll(custom);
         return new CompositeAction(all, stopOnError);
      }
   }

   protected int suffixLength(final String lowFilename) {
      for(FileExtension extension : FileExtension.values()) {
         if (extension.isExtensionFor(lowFilename)) {
            return extension.length();
         }
      }

      return 0;
   }

   protected SortedMap getEligibleFiles(final RollingFileManager manager) {
      return this.getEligibleFiles(manager, true);
   }

   protected SortedMap getEligibleFiles(final RollingFileManager manager, final boolean isAscending) {
      StringBuilder buf = new StringBuilder();
      String pattern = manager.getPatternProcessor().getPattern();
      manager.getPatternProcessor().formatFileName(this.strSubstitutor, buf, NotANumber.NAN);
      String fileName = manager.isDirectWrite() ? "" : manager.getFileName();
      return this.getEligibleFiles(fileName, buf.toString(), pattern, isAscending);
   }

   protected SortedMap getEligibleFiles(final String path, final String pattern) {
      return this.getEligibleFiles("", path, pattern, true);
   }

   /** @deprecated */
   @Deprecated
   protected SortedMap getEligibleFiles(final String path, final String logfilePattern, final boolean isAscending) {
      return this.getEligibleFiles("", path, logfilePattern, isAscending);
   }

   @SuppressFBWarnings(
      value = {"PATH_TRAVERSAL_IN"},
      justification = "The file path should be specified in the configuration file."
   )
   protected SortedMap getEligibleFiles(final String currentFile, final String path, final String logfilePattern, final boolean isAscending) {
      TreeMap<Integer, Path> eligibleFiles = new TreeMap();
      File file = new File(path);
      File parent = file.getParentFile();
      if (parent == null) {
         parent = new File(".");
      } else {
         parent.mkdirs();
      }

      if (!PATTERN_COUNTER.matcher(logfilePattern).find()) {
         return eligibleFiles;
      } else {
         Path dir = parent.toPath();
         String fileName = file.getName();
         int suffixLength = this.suffixLength(fileName);
         if (suffixLength > 0) {
            fileName = Pattern.quote(fileName.substring(0, fileName.length() - suffixLength)) + ".*";
         } else {
            fileName = Pattern.quote(fileName);
         }

         String filePattern = fileName.replaceFirst("0*\\u0000", "\\\\E(0?\\\\d+)\\\\Q");
         Pattern pattern = Pattern.compile(filePattern);
         Path current = currentFile.length() > 0 ? (new File(currentFile)).toPath() : null;
         LOGGER.debug("Current file: {}", currentFile);

         try {
            DirectoryStream<Path> stream = Files.newDirectoryStream(dir);

            try {
               for(Path entry : stream) {
                  Matcher matcher = pattern.matcher(entry.toFile().getName());
                  if (matcher.matches() && !entry.equals(current)) {
                     try {
                        Integer index = Integers.parseInt(matcher.group(1));
                        eligibleFiles.put(index, entry);
                     } catch (NumberFormatException var20) {
                        LOGGER.debug("Ignoring file {} which matches pattern but the index is invalid.", entry.toFile().getName());
                     }
                  }
               }
            } catch (Throwable var21) {
               if (stream != null) {
                  try {
                     stream.close();
                  } catch (Throwable var19) {
                     var21.addSuppressed(var19);
                  }
               }

               throw var21;
            }

            if (stream != null) {
               stream.close();
            }
         } catch (IOException ioe) {
            throw new LoggingException("Error reading folder " + dir + " " + ioe.getMessage(), ioe);
         }

         return (SortedMap)(isAscending ? eligibleFiles : eligibleFiles.descendingMap());
      }
   }
}
