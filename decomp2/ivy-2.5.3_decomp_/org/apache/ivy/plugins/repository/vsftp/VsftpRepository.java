package org.apache.ivy.plugins.repository.vsftp;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import org.apache.ivy.Ivy;
import org.apache.ivy.core.IvyContext;
import org.apache.ivy.core.IvyThread;
import org.apache.ivy.core.event.IvyEvent;
import org.apache.ivy.core.event.IvyListener;
import org.apache.ivy.core.settings.TimeoutConstraint;
import org.apache.ivy.plugins.repository.AbstractRepository;
import org.apache.ivy.plugins.repository.BasicResource;
import org.apache.ivy.plugins.repository.Resource;
import org.apache.ivy.util.Checks;
import org.apache.ivy.util.Message;

public class VsftpRepository extends AbstractRepository {
   private static final int LS_DATE_INDEX4 = 7;
   private static final int LS_DATE_INDEX3 = 6;
   private static final int LS_DATE_INDEX2 = 5;
   private static final int LS_DATE_INDEX1 = 4;
   private static final int LS_SIZE_INDEX = 3;
   private static final int LS_PARTS_NUMBER = 9;
   private static final int DISCONNECT_COMMAND_TIMEOUT = 300;
   private static final int REUSE_CONNECTION_SLEEP_TIME = 10;
   private static final int READER_ALIVE_SLEEP_TIME = 100;
   private static final int MAX_READER_ALIVE_ATTEMPT = 5;
   private static final int ERROR_SLEEP_TIME = 30;
   private static final int PROMPT_SLEEP_TIME = 50;
   private static final int MAX_READ_PROMPT_ATTEMPT = 5;
   private static final int GET_JOIN_MAX_TIME = 100;
   private static final int DEFAULT_REUSE_CONNECTION_TIME = 300000;
   private static final int DEFAULT_READ_TIMEOUT = 30000;
   private static final String PROMPT = "vsftp> ";
   private static final SimpleDateFormat FORMAT;
   private String host;
   private String username;
   private String authentication = "gssapi";
   private Reader in;
   private Reader err;
   private PrintWriter out;
   private volatile StringBuilder errors = new StringBuilder();
   private long readTimeout = 30000L;
   private long reuseConnection = 300000L;
   private volatile long lastCommand;
   private volatile boolean inCommand;
   private Process process;
   private Thread connectionCleaner;
   private Thread errorsReader;
   private volatile long errorsLastUpdateTime;
   private Ivy ivy = null;

   public VsftpRepository() {
   }

   public VsftpRepository(TimeoutConstraint timeoutConstraint) {
      super(timeoutConstraint);
   }

   public Resource getResource(String source) throws IOException {
      this.initIvy();
      return new VsftpResource(this, source);
   }

   private void initIvy() {
      this.ivy = IvyContext.getContext().getIvy();
   }

   protected Resource getInitResource(String source) throws IOException {
      Resource var2;
      try {
         var2 = this.lslToResource(source, this.sendCommand("ls -l " + source, true, true));
      } catch (IOException ex) {
         this.cleanup(ex);
         throw ex;
      } finally {
         this.cleanup();
      }

      return var2;
   }

   public void get(final String source, File destination) throws IOException {
      this.initIvy();

      try {
         this.fireTransferInitiated(this.getResource(source), 5);
         File destDir = destination.getParentFile();
         if (destDir != null) {
            this.sendCommand("lcd " + destDir.getAbsolutePath());
         }

         if (destination.exists()) {
            destination.delete();
         }

         int index = source.lastIndexOf(47);
         String srcName = index == -1 ? source : source.substring(index + 1);
         final File to = destDir == null ? Checks.checkAbsolute(srcName, "source") : new File(destDir, srcName);
         final IOException[] ex = new IOException[1];
         Thread get = new IvyThread() {
            public void run() {
               this.initContext();

               try {
                  VsftpRepository.this.sendCommand("get " + source, VsftpRepository.this.getExpectedDownloadMessage(source, to), 0L);
               } catch (IOException e) {
                  ex[0] = e;
               }

            }
         };
         get.start();
         long prevLength = 0L;
         long lastUpdate = System.currentTimeMillis();
         long timeout = this.readTimeout;

         while(get.isAlive()) {
            this.checkInterrupted();
            long length = to.exists() ? to.length() : 0L;
            if (length > prevLength) {
               this.fireTransferProgress(length - prevLength);
               lastUpdate = System.currentTimeMillis();
               prevLength = length;
            } else if (System.currentTimeMillis() - lastUpdate > timeout) {
               Message.verbose("download hang for more than " + timeout + "ms. Interrupting.");
               get.interrupt();
               if (to.exists()) {
                  to.delete();
               }

               throw new IOException(source + " download timeout from " + this.getHost());
            }

            try {
               get.join(100L);
            } catch (InterruptedException var22) {
               if (to.exists()) {
                  to.delete();
               }

               return;
            }
         }

         if (ex[0] != null) {
            if (to.exists()) {
               to.delete();
            }

            throw ex[0];
         } else {
            to.renameTo(destination);
            this.fireTransferCompleted(destination.length());
         }
      } catch (IOException ex) {
         this.fireTransferError(ex);
         this.cleanup(ex);
         throw ex;
      } finally {
         this.cleanup();
      }
   }

   public List list(String parent) throws IOException {
      this.initIvy();

      Object var3;
      try {
         if (!parent.endsWith("/")) {
            parent = parent + "/";
         }

         String response = this.sendCommand("ls -l " + parent, true, true);
         if (!response.startsWith("ls")) {
            String[] lines = response.split("\n");
            List<String> ret = new ArrayList(lines.length);
            String[] var5 = lines;
            int var6 = lines.length;

            for(int var7 = 0; var7 < var6; ++var7) {
               String line;
               for(line = var5[var7]; line.endsWith("\r") || line.endsWith("\n"); line = line.substring(0, line.length() - 1)) {
               }

               if (!line.trim().isEmpty()) {
                  ret.add(parent + line.substring(line.lastIndexOf(32) + 1));
               }
            }

            Object var15 = ret;
            return (List)var15;
         }

         var3 = null;
      } catch (IOException ex) {
         this.cleanup(ex);
         throw ex;
      } finally {
         this.cleanup();
      }

      return (List)var3;
   }

   public void put(File source, String destination, boolean overwrite) throws IOException {
      this.initIvy();

      try {
         if (this.getResource(destination).exists()) {
            if (!overwrite) {
               return;
            }

            this.sendCommand("rm " + destination, this.getExpectedRemoveMessage(destination));
         }

         int index = destination.lastIndexOf(47);
         String destDir = null;
         if (index != -1) {
            destDir = destination.substring(0, index);
            this.mkdirs(destDir);
            this.sendCommand("cd " + destDir);
         }

         String to = destDir != null ? destDir + "/" + source.getName() : source.getName();
         this.sendCommand("put " + source.getAbsolutePath(), this.getExpectedUploadMessage(source, to), 0L);
         this.sendCommand("mv " + to + " " + destination);
      } catch (IOException ex) {
         this.cleanup(ex);
         throw ex;
      } finally {
         this.cleanup();
      }
   }

   private void mkdirs(String destDir) throws IOException {
      if (!this.dirExists(destDir)) {
         if (destDir.endsWith("/")) {
            destDir = destDir.substring(0, destDir.length() - 1);
         }

         int index = destDir.lastIndexOf(47);
         if (index != -1) {
            this.mkdirs(destDir.substring(0, index));
         }

         this.sendCommand("mkdir " + destDir);
      }
   }

   private boolean dirExists(String dir) throws IOException {
      return !this.sendCommand("ls " + dir, true).startsWith("ls: ");
   }

   protected String sendCommand(String command) throws IOException {
      return this.sendCommand(command, false, this.readTimeout);
   }

   protected void sendCommand(String command, Pattern expectedResponse) throws IOException {
      this.sendCommand(command, expectedResponse, this.readTimeout);
   }

   protected void sendCommand(String command, Pattern expectedResponse, long timeout) throws IOException {
      String response = this.sendCommand(command, true, timeout);
      if (!expectedResponse.matcher(response).matches()) {
         Message.debug("invalid response from server:");
         Message.debug("expected: '" + expectedResponse + "'");
         Message.debug("was:      '" + response + "'");
         throw new IOException(response);
      }
   }

   protected String sendCommand(String command, boolean sendErrorAsResponse) throws IOException {
      return this.sendCommand(command, sendErrorAsResponse, this.readTimeout);
   }

   protected String sendCommand(String command, boolean sendErrorAsResponse, boolean single) throws IOException {
      return this.sendCommand(command, sendErrorAsResponse, single, this.readTimeout);
   }

   protected String sendCommand(String command, boolean sendErrorAsResponse, long timeout) throws IOException {
      return this.sendCommand(command, sendErrorAsResponse, false, timeout);
   }

   protected String sendCommand(String command, boolean sendErrorAsResponse, boolean single, long timeout) throws IOException {
      single = false;
      this.checkInterrupted();
      this.inCommand = true;
      this.errorsLastUpdateTime = 0L;
      synchronized(this) {
         if (single && this.in == null) {
            this.sendSingleCommand(command);
         } else {
            this.ensureConnectionOpened();
            Message.debug("sending command '" + command + "' to " + this.getHost());
            this.updateLastCommandTime();
            this.out.println(command);
            this.out.flush();
         }
      }

      String var6;
      try {
         var6 = this.readResponse(sendErrorAsResponse, timeout);
      } finally {
         this.inCommand = false;
         if (single) {
            this.closeConnection();
         }

      }

      return var6;
   }

   protected String readResponse(boolean sendErrorAsResponse) throws IOException {
      return this.readResponse(sendErrorAsResponse, this.readTimeout);
   }

   protected synchronized String readResponse(final boolean sendErrorAsResponse, long timeout) throws IOException {
      final StringBuilder response = new StringBuilder();
      final IOException[] exc = new IOException[1];
      final boolean[] done = new boolean[1];
      Runnable r = new Runnable() {
         public void run() {
            synchronized(VsftpRepository.this) {
               try {
                  boolean getPrompt = false;

                  for(int attempt = 0; !getPrompt && attempt < 5; ++attempt) {
                     int c;
                     while((c = VsftpRepository.this.in.read()) != -1) {
                        attempt = 0;
                        response.append((char)c);
                        if (response.length() >= "vsftp> ".length() && response.substring(response.length() - "vsftp> ".length(), response.length()).equals("vsftp> ")) {
                           response.setLength(response.length() - "vsftp> ".length());
                           getPrompt = true;
                           break;
                        }
                     }

                     if (!getPrompt) {
                        try {
                           Thread.sleep(50L);
                        } catch (InterruptedException var14) {
                           break;
                        }
                     }
                  }

                  if (getPrompt) {
                     if (VsftpRepository.this.errorsLastUpdateTime == 0L) {
                        VsftpRepository.this.errorsLastUpdateTime = VsftpRepository.this.lastCommand;
                     }

                     while(System.currentTimeMillis() - VsftpRepository.this.errorsLastUpdateTime < 50L) {
                        try {
                           Thread.sleep(30L);
                        } catch (InterruptedException var13) {
                           break;
                        }
                     }
                  }

                  if (VsftpRepository.this.errors.length() > 0) {
                     if (!sendErrorAsResponse) {
                        throw new IOException(VsftpRepository.chomp(VsftpRepository.this.errors).toString());
                     }

                     response.append(VsftpRepository.this.errors);
                     VsftpRepository.this.errors.setLength(0);
                  }

                  VsftpRepository.chomp(response);
                  done[0] = true;
               } catch (IOException e) {
                  exc[0] = e;
               } finally {
                  VsftpRepository.this.notify();
               }

            }
         }
      };
      Thread reader = null;
      if (timeout == 0L) {
         r.run();
      } else {
         reader = new IvyThread(r);
         reader.start();

         try {
            this.wait(timeout);
         } catch (InterruptedException var11) {
         }
      }

      this.updateLastCommandTime();
      if (exc[0] != null) {
         throw exc[0];
      } else if (done[0]) {
         if ("Not connected.".equals(response.toString())) {
            Message.info("vsftp connection to " + this.getHost() + " reset");
            this.closeConnection();
            throw new IOException("not connected to " + this.getHost());
         } else {
            Message.debug("received response '" + response + "' from " + this.getHost());
            return response.toString();
         }
      } else {
         if (reader != null && reader.isAlive()) {
            reader.interrupt();

            for(int attempt = 0; attempt < 5 && reader.isAlive(); ++attempt) {
               try {
                  Thread.sleep(100L);
               } catch (InterruptedException var12) {
                  break;
               }
            }

            if (reader.isAlive()) {
               reader.stop();
            }
         }

         throw new IOException("connection timeout to " + this.getHost());
      }
   }

   private synchronized void sendSingleCommand(String command) throws IOException {
      this.exec(this.getSingleCommand(command));
   }

   protected synchronized void ensureConnectionOpened() throws IOException {
      if (this.in == null) {
         Message.verbose("connecting to " + this.getUsername() + "@" + this.getHost() + "... ");
         String connectionCommand = this.getConnectionCommand();
         this.exec(connectionCommand);

         try {
            this.readResponse(false);
            if (this.reuseConnection > 0L) {
               this.connectionCleaner = new IvyThread() {
                  public void run() {
                     this.initContext();

                     try {
                        long sleep = 10L;

                        while(VsftpRepository.this.in != null && sleep > 0L) {
                           sleep(sleep);
                           sleep = VsftpRepository.this.reuseConnection - (System.currentTimeMillis() - VsftpRepository.this.lastCommand);
                           if (VsftpRepository.this.inCommand && sleep <= 0L) {
                              sleep = VsftpRepository.this.reuseConnection;
                           }
                        }
                     } catch (InterruptedException var3) {
                     }

                     VsftpRepository.this.disconnect();
                  }
               };
               this.connectionCleaner.start();
            }

            if (this.ivy != null) {
               this.ivy.getEventManager().addIvyListener(new IvyListener() {
                  public void progress(IvyEvent event) {
                     VsftpRepository.this.disconnect();
                     event.getSource().removeIvyListener(this);
                  }
               }, "post-resolve");
            }
         } catch (IOException ex) {
            this.closeConnection();
            throw new IOException("impossible to connect to " + this.getUsername() + "@" + this.getHost() + " using " + this.getAuthentication() + ": " + ex.getMessage());
         }

         Message.verbose("connected to " + this.getHost());
      }

   }

   private void updateLastCommandTime() {
      this.lastCommand = System.currentTimeMillis();
   }

   private void exec(String command) throws IOException {
      Message.debug("launching '" + command + "'");
      this.process = Runtime.getRuntime().exec(command);
      this.in = new InputStreamReader(this.process.getInputStream());
      this.err = new InputStreamReader(this.process.getErrorStream());
      this.out = new PrintWriter(this.process.getOutputStream());
      this.errorsReader = new IvyThread() {
         public void run() {
            this.initContext();

            int c;
            try {
               while(VsftpRepository.this.err != null && (c = VsftpRepository.this.err.read()) != -1) {
                  VsftpRepository.this.errors.append((char)c);
                  VsftpRepository.this.errorsLastUpdateTime = System.currentTimeMillis();
               }
            } catch (IOException var3) {
            }

         }
      };
      this.errorsReader.start();
   }

   private void checkInterrupted() {
      if (this.ivy != null) {
         this.ivy.checkInterrupted();
      }

   }

   private void cleanup(Exception ex) {
      if (ex.getMessage().equals("connection timeout to " + this.getHost())) {
         this.closeConnection();
      } else {
         this.disconnect();
      }

   }

   private void cleanup() {
      if (this.reuseConnection == 0L) {
         this.disconnect();
      }

   }

   public synchronized void disconnect() {
      if (this.in != null) {
         Message.verbose("disconnecting from " + this.getHost() + "... ");

         try {
            this.sendCommand("exit", false, 300L);
         } catch (IOException var5) {
         } finally {
            this.closeConnection();
            Message.verbose("disconnected of " + this.getHost());
         }
      }

   }

   private synchronized void closeConnection() {
      if (this.connectionCleaner != null) {
         this.connectionCleaner.interrupt();
      }

      if (this.errorsReader != null) {
         this.errorsReader.interrupt();
      }

      try {
         this.process.destroy();
      } catch (Exception var5) {
      }

      try {
         this.in.close();
      } catch (Exception var4) {
      }

      try {
         this.err.close();
      } catch (Exception var3) {
      }

      try {
         this.out.close();
      } catch (Exception var2) {
      }

      this.connectionCleaner = null;
      this.errorsReader = null;
      this.process = null;
      this.in = null;
      this.out = null;
      this.err = null;
      Message.debug("connection to " + this.getHost() + " closed");
   }

   protected Resource lslToResource(String file, String responseLine) {
      if (responseLine != null && !responseLine.startsWith("ls")) {
         String[] parts = responseLine.split("\\s+");
         if (parts.length != 9) {
            Message.debug("unrecognized ls format: " + responseLine);
            return new BasicResource(file, false, 0L, 0L, false);
         } else {
            try {
               long contentLength = Long.parseLong(parts[3]);
               String date = parts[4] + " " + parts[5] + " " + parts[6] + " " + parts[7];
               return new BasicResource(file, true, contentLength, FORMAT.parse(date).getTime(), false);
            } catch (Exception ex) {
               Message.warn("impossible to parse server response: " + responseLine, ex);
               return new BasicResource(file, false, 0L, 0L, false);
            }
         }
      } else {
         return new BasicResource(file, false, 0L, 0L, false);
      }
   }

   protected String getSingleCommand(String command) {
      return "vsh -noprompt -auth " + this.authentication + " " + this.username + "@" + this.host + " " + command;
   }

   protected String getConnectionCommand() {
      return "vsftp -noprompt -auth " + this.authentication + " " + this.username + "@" + this.host;
   }

   protected Pattern getExpectedDownloadMessage(String source, File to) {
      return Pattern.compile("Downloading " + to.getName() + " from [^\\s]+");
   }

   protected Pattern getExpectedRemoveMessage(String destination) {
      return Pattern.compile("Removing [^\\s]+");
   }

   protected Pattern getExpectedUploadMessage(File source, String to) {
      return Pattern.compile("Uploading " + source.getName() + " to [^\\s]+");
   }

   public String getAuthentication() {
      return this.authentication;
   }

   public void setAuthentication(String authentication) {
      this.authentication = authentication;
   }

   public String getHost() {
      return this.host;
   }

   public void setHost(String host) {
      this.host = host;
   }

   public String getUsername() {
      return this.username;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   private static StringBuilder chomp(StringBuilder str) {
      if (str != null && str.length() != 0) {
         while("\n".equals(str.substring(str.length() - 1)) || "\r".equals(str.substring(str.length() - 1))) {
            str.setLength(str.length() - 1);
         }

         return str;
      } else {
         return str;
      }
   }

   public String toString() {
      return this.getName() + " " + this.getUsername() + "@" + this.getHost() + " (" + this.getAuthentication() + ")";
   }

   public void setReuseConnection(long time) {
      this.reuseConnection = time;
   }

   public long getReadTimeout() {
      return this.readTimeout;
   }

   public void setReadTimeout(long readTimeout) {
      this.readTimeout = readTimeout;
   }

   static {
      FORMAT = new SimpleDateFormat("MMM dd, yyyy HH:mm", Locale.US);
   }
}
