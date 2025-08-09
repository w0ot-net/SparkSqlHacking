package org.apache.zookeeper;

import [Ljava.lang.String;;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.zookeeper.common.Time;
import org.apache.zookeeper.server.ExitCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Shell {
   private static final Logger LOG = LoggerFactory.getLogger(Shell.class);
   public static final String USER_NAME_COMMAND = "whoami";
   public static final String SET_PERMISSION_COMMAND = "chmod";
   public static final String SET_OWNER_COMMAND = "chown";
   public static final String SET_GROUP_COMMAND = "chgrp";
   protected long timeOutInterval;
   private AtomicBoolean timedOut;
   public static final String ULIMIT_COMMAND = "ulimit";
   public static final boolean WINDOWS = System.getProperty("os.name").startsWith("Windows");
   private long interval;
   private long lastTime;
   private Map environment;
   private File dir;
   private Process process;
   private int exitCode;
   private volatile AtomicBoolean completed;

   public static String[] getGroupsCommand() {
      return new String[]{"bash", "-c", "groups"};
   }

   public static String[] getGroupsForUserCommand(String user) {
      return new String[]{"bash", "-c", "id -Gn " + user};
   }

   public static String[] getGET_PERMISSION_COMMAND() {
      return new String[]{WINDOWS ? "ls" : "/bin/ls", "-ld"};
   }

   public static String[] getUlimitMemoryCommand(int memoryLimit) {
      return WINDOWS ? null : new String[]{"ulimit", "-v", String.valueOf(memoryLimit)};
   }

   public Shell() {
      this(0L);
   }

   public Shell(long interval) {
      this.timeOutInterval = 0L;
      this.interval = interval;
      this.lastTime = interval < 0L ? 0L : -interval;
   }

   protected void setEnvironment(Map env) {
      this.environment = env;
   }

   protected void setWorkingDirectory(File dir) {
      this.dir = dir;
   }

   protected void run() throws IOException {
      if (this.lastTime + this.interval <= Time.currentElapsedTime()) {
         this.exitCode = ExitCode.EXECUTION_FINISHED.getValue();
         this.runCommand();
      }
   }

   private void runCommand() throws IOException {
      ProcessBuilder builder = new ProcessBuilder(this.getExecString());
      Timer timeOutTimer = null;
      ShellTimeoutTimerTask timeoutTimerTask = null;
      this.timedOut = new AtomicBoolean(false);
      this.completed = new AtomicBoolean(false);
      if (this.environment != null) {
         builder.environment().putAll(this.environment);
      }

      if (this.dir != null) {
         builder.directory(this.dir);
      }

      this.process = builder.start();
      if (this.timeOutInterval > 0L) {
         timeOutTimer = new Timer();
         timeoutTimerTask = new ShellTimeoutTimerTask(this);
         timeOutTimer.schedule(timeoutTimerTask, this.timeOutInterval);
      }

      final BufferedReader errReader = new BufferedReader(new InputStreamReader(this.process.getErrorStream()));
      BufferedReader inReader = new BufferedReader(new InputStreamReader(this.process.getInputStream()));
      final StringBuffer errMsg = new StringBuffer();
      Thread errThread = new Thread() {
         public void run() {
            try {
               for(String line = errReader.readLine(); line != null && !this.isInterrupted(); line = errReader.readLine()) {
                  errMsg.append(line);
                  errMsg.append(System.getProperty("line.separator"));
               }
            } catch (IOException ioe) {
               Shell.LOG.warn("Error reading the error stream", ioe);
            }

         }
      };

      try {
         errThread.start();
      } catch (IllegalStateException var23) {
      }

      try {
         this.parseExecResult(inReader);

         for(String line = inReader.readLine(); line != null; line = inReader.readLine()) {
         }

         this.exitCode = this.process.waitFor();

         try {
            errThread.join();
         } catch (InterruptedException ie) {
            LOG.warn("Interrupted while reading the error stream", ie);
         }

         this.completed.set(true);
         if (this.exitCode != ExitCode.EXECUTION_FINISHED.getValue()) {
            throw new ExitCodeException(this.exitCode, errMsg.toString());
         }
      } catch (InterruptedException ie) {
         throw new IOException(ie.toString());
      } finally {
         if (timeOutTimer != null && !this.timedOut.get()) {
            timeOutTimer.cancel();
         }

         try {
            inReader.close();
         } catch (IOException ioe) {
            LOG.warn("Error while closing the input stream", ioe);
         }

         if (!this.completed.get()) {
            errThread.interrupt();
         }

         try {
            errReader.close();
         } catch (IOException ioe) {
            LOG.warn("Error while closing the error stream", ioe);
         }

         this.process.destroy();
         this.lastTime = Time.currentElapsedTime();
      }

   }

   protected abstract String[] getExecString();

   protected abstract void parseExecResult(BufferedReader var1) throws IOException;

   public Process getProcess() {
      return this.process;
   }

   public int getExitCode() {
      return this.exitCode;
   }

   public boolean isTimedOut() {
      return this.timedOut.get();
   }

   private void setTimedOut() {
      this.timedOut.set(true);
   }

   public static String execCommand(String... cmd) throws IOException {
      return execCommand((Map)null, cmd, 0L);
   }

   public static String execCommand(Map env, String[] cmd, long timeout) throws IOException {
      ShellCommandExecutor exec = new ShellCommandExecutor(cmd, (File)null, env, timeout);
      exec.execute();
      return exec.getOutput();
   }

   public static String execCommand(Map env, String... cmd) throws IOException {
      return execCommand(env, cmd, 0L);
   }

   public static class ExitCodeException extends IOException {
      int exitCode;

      public ExitCodeException(int exitCode, String message) {
         super(message);
         this.exitCode = exitCode;
      }

      public int getExitCode() {
         return this.exitCode;
      }
   }

   public static class ShellCommandExecutor extends Shell {
      private String[] command;
      private StringBuffer output;

      public ShellCommandExecutor(String[] execString) {
         this(execString, (File)null);
      }

      public ShellCommandExecutor(String[] execString, File dir) {
         this(execString, dir, (Map)null);
      }

      public ShellCommandExecutor(String[] execString, File dir, Map env) {
         this(execString, dir, env, 0L);
      }

      public ShellCommandExecutor(String[] execString, File dir, Map env, long timeout) {
         this.command = (String[])((String;)execString).clone();
         if (dir != null) {
            this.setWorkingDirectory(dir);
         }

         if (env != null) {
            this.setEnvironment(env);
         }

         this.timeOutInterval = timeout;
      }

      public void execute() throws IOException {
         this.run();
      }

      protected String[] getExecString() {
         return this.command;
      }

      protected void parseExecResult(BufferedReader lines) throws IOException {
         this.output = new StringBuffer();
         char[] buf = new char[512];

         int nRead;
         while((nRead = lines.read(buf, 0, buf.length)) > 0) {
            this.output.append(buf, 0, nRead);
         }

      }

      public String getOutput() {
         return this.output == null ? "" : this.output.toString();
      }

      public String toString() {
         StringBuilder builder = new StringBuilder();
         String[] args = this.getExecString();

         for(String s : args) {
            if (s.indexOf(32) >= 0) {
               builder.append('"').append(s).append('"');
            } else {
               builder.append(s);
            }

            builder.append(' ');
         }

         return builder.toString();
      }
   }

   private static class ShellTimeoutTimerTask extends TimerTask {
      private Shell shell;

      public ShellTimeoutTimerTask(Shell shell) {
         this.shell = shell;
      }

      public void run() {
         Process p = this.shell.getProcess();

         try {
            p.exitValue();
         } catch (Exception var3) {
            if (p != null && !this.shell.completed.get()) {
               this.shell.setTimedOut();
               p.destroy();
            }
         }

      }
   }
}
