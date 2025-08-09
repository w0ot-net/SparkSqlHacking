package io.vertx.core.impl.launcher.commands;

import io.vertx.core.Handler;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import java.io.File;
import java.lang.ProcessBuilder.Redirect;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Watcher implements Runnable {
   private static final Logger LOGGER = LoggerFactory.getLogger(Watcher.class);
   private final long gracePeriod;
   private final Map fileMap = new LinkedHashMap();
   private final Set filesToWatch = new HashSet();
   private final long scanPeriod;
   private final List roots;
   private final File cwd;
   private long lastChange = -1L;
   private final List includes;
   private final Handler deploy;
   private final Handler undeploy;
   private final String cmd;
   private volatile boolean closed;

   public Watcher(File root, List includes, Handler deploy, Handler undeploy, String onRedeployCommand, long gracePeriod, long scanPeriod) {
      this.gracePeriod = gracePeriod;
      this.includes = this.sanitizeIncludePatterns(includes);
      this.roots = extractRoots(root, this.includes);
      this.cwd = root;
      LOGGER.info("Watched paths: " + this.roots);
      this.deploy = deploy;
      this.undeploy = undeploy;
      this.cmd = onRedeployCommand;
      this.scanPeriod = scanPeriod;
      this.addFilesToWatchedList(this.roots);
   }

   static List extractRoots(File root, List includes) {
      return (List)((Set)includes.stream().map((s) -> {
         if (s.startsWith("*")) {
            return root.getAbsolutePath();
         } else {
            if (s.contains("*")) {
               s = s.substring(0, s.indexOf("*"));
            }

            File file = new File(s);
            return file.isAbsolute() ? file.getAbsolutePath() : (new File(root, s)).getAbsolutePath();
         }
      }).collect(Collectors.toSet())).stream().map(File::new).collect(Collectors.toList());
   }

   private List sanitizeIncludePatterns(List includes) {
      return (List)includes.stream().map((p) -> ExecUtils.isWindows() ? p.replace('/', File.separatorChar) : p.replace('\\', File.separatorChar)).collect(Collectors.toList());
   }

   private void addFilesToWatchedList(List roots) {
      roots.forEach(this::addFileToWatchedList);
   }

   private void addFileToWatchedList(File file) {
      this.filesToWatch.add(file);
      Map<File, FileInfo> map = new HashMap();
      if (file.isDirectory()) {
         File[] children = file.listFiles();
         if (children != null) {
            for(File child : children) {
               map.put(child, new FileInfo(child.lastModified(), child.length()));
               if (child.isDirectory()) {
                  this.addFileToWatchedList(child);
               }
            }
         }
      } else {
         map.put(file, new FileInfo(file.lastModified(), file.length()));
      }

      this.fileMap.put(file, map);
   }

   private boolean changesHaveOccurred() {
      boolean changed = false;

      for(File toWatch : new HashSet(this.filesToWatch)) {
         Map<File, File> newFiles = new LinkedHashMap();
         if (toWatch.isDirectory()) {
            File[] files = toWatch.exists() ? toWatch.listFiles() : new File[0];
            if (files == null) {
               throw new IllegalStateException("Cannot scan the file system to detect file changes");
            }

            for(File file : files) {
               newFiles.put(file, file);
            }
         } else {
            newFiles.put(toWatch, toWatch);
         }

         Map<File, FileInfo> currentFileMap = (Map)this.fileMap.get(toWatch);

         for(Map.Entry currentEntry : (new HashMap(currentFileMap)).entrySet()) {
            File currFile = (File)currentEntry.getKey();
            FileInfo currInfo = (FileInfo)currentEntry.getValue();
            File newFile = (File)newFiles.get(currFile);
            if (newFile == null) {
               currentFileMap.remove(currFile);
               if (currentFileMap.isEmpty()) {
                  this.fileMap.remove(toWatch);
                  this.filesToWatch.remove(toWatch);
               }

               LOGGER.trace("File: " + currFile + " has been deleted");
               if (this.match(currFile)) {
                  changed = true;
               }
            } else if (newFile.lastModified() != currInfo.lastModified || newFile.length() != currInfo.length) {
               currentFileMap.put(newFile, new FileInfo(newFile.lastModified(), newFile.length()));
               LOGGER.trace("File: " + currFile + " has been modified");
               if (this.match(currFile)) {
                  changed = true;
               }
            }
         }

         for(File newFile : newFiles.keySet()) {
            if (!currentFileMap.containsKey(newFile)) {
               currentFileMap.put(newFile, new FileInfo(newFile.lastModified(), newFile.length()));
               if (newFile.isDirectory()) {
                  this.addFileToWatchedList(newFile);
               }

               LOGGER.trace("File was added: " + newFile);
               if (this.match(newFile)) {
                  changed = true;
               }
            }
         }
      }

      long now = System.currentTimeMillis();
      if (changed) {
         this.lastChange = now;
      }

      if (this.lastChange != -1L && now - this.lastChange >= this.gracePeriod) {
         this.lastChange = -1L;
         return true;
      } else {
         return false;
      }
   }

   protected boolean match(File file) {
      String rel = null;
      String relFromCwd = null;

      for(File root : this.roots) {
         if (file.getAbsolutePath().startsWith(root.getAbsolutePath())) {
            if (file.getAbsolutePath().equals(root.getAbsolutePath())) {
               rel = file.getAbsolutePath();
            } else {
               rel = file.getAbsolutePath().substring(root.getAbsolutePath().length() + 1);
            }
         }
      }

      if (rel == null) {
         LOGGER.warn("A change in " + file.getAbsolutePath() + " has been detected, but the file does not belong to a watched roots: " + this.roots);
         return false;
      } else {
         if (file.getAbsolutePath().startsWith(this.cwd.getAbsolutePath())) {
            relFromCwd = file.getAbsolutePath().substring(this.cwd.getAbsolutePath().length() + 1);
         }

         for(String include : this.includes) {
            if (relFromCwd != null && FileSelector.matchPath(include, relFromCwd, !ExecUtils.isWindows()) || FileSelector.matchPath(include, file.getAbsolutePath(), !ExecUtils.isWindows())) {
               return true;
            }
         }

         return false;
      }
   }

   public Watcher watch() {
      (new Thread(this)).start();
      LOGGER.info("Starting the vert.x application in redeploy mode");
      this.deploy.handle((Object)null);
      return this;
   }

   public void close() {
      LOGGER.info("Stopping redeployment");
      this.closed = true;
      this.undeploy.handle((Object)null);
   }

   public void run() {
      try {
         for(; !this.closed; Thread.sleep(this.scanPeriod)) {
            if (this.changesHaveOccurred()) {
               this.trigger();
            }
         }
      } catch (Throwable e) {
         LOGGER.error("An error have been encountered while watching resources - leaving the redeploy mode", e);
         this.close();
      }

   }

   private void trigger() {
      long begin = System.currentTimeMillis();
      LOGGER.info("Redeploying!");
      this.undeploy.handle((Handler)(v1) -> this.executeUserCommand((v2) -> this.deploy.handle((Handler)(v3) -> {
               long end = System.currentTimeMillis();
               LOGGER.info("Redeployment done in " + (end - begin) + " ms.");
            })));
   }

   private void executeUserCommand(Handler onCompletion) {
      if (this.cmd != null) {
         try {
            List<String> command = new ArrayList();
            if (ExecUtils.isWindows()) {
               ExecUtils.addArgument(command, "cmd");
               ExecUtils.addArgument(command, "/c");
            } else {
               ExecUtils.addArgument(command, "sh");
               ExecUtils.addArgument(command, "-c");
            }

            command.add(this.cmd);
            Process process = (new ProcessBuilder(command)).redirectError(Redirect.INHERIT).redirectOutput(Redirect.INHERIT).start();
            int status = process.waitFor();
            LOGGER.info("User command terminated with status " + status);
         } catch (Throwable e) {
            LOGGER.error("Error while executing the on-redeploy command : '" + this.cmd + "'", e);
         }
      }

      onCompletion.handle((Object)null);
   }

   private static final class FileInfo {
      long lastModified;
      long length;

      private FileInfo(long lastModified, long length) {
         this.lastModified = lastModified;
         this.length = length;
      }
   }
}
