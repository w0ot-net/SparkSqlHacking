package org.apache.ivy.plugins.repository.ssh;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Scp {
   private static final int MODE_LENGTH = 4;
   private static final int SEND_FILE_BUFFER_LENGTH = 40000;
   private static final int SEND_BYTES_BUFFER_LENGTH = 512;
   private static final int MIN_TLINE_LENGTH = 8;
   private static final int CLINE_SPACE_INDEX2 = 5;
   private static final int CLINE_SPACE_INDEX1 = 4;
   private static final int MIN_C_LINE_LENGTH = 8;
   private static final int DEFAULT_LINE_BUFFER_LENGTH = 30;
   private static final int BUFFER_SIZE = 65536;
   private static final int MAX_SCP_LINE_LENGTH = 8192;
   private Session session;

   public Scp(Session session) {
      if (session == null) {
         throw new IllegalArgumentException("Cannot accept null argument!");
      } else {
         this.session = session;
      }
   }

   private void readResponse(InputStream is) throws IOException, RemoteScpException {
      int c = is.read();
      if (c != 0) {
         if (c == -1) {
            throw new RemoteScpException("Remote scp terminated unexpectedly.");
         } else if (c != 1 && c != 2) {
            throw new RemoteScpException("Remote scp sent illegal error code.");
         } else if (c == 2) {
            throw new RemoteScpException("Remote scp terminated with error.");
         } else {
            String err = this.receiveLine(is);
            throw new RemoteScpException("Remote scp terminated with error (" + err + ").");
         }
      }
   }

   private String receiveLine(InputStream is) throws IOException, RemoteScpException {
      StringBuilder sb = new StringBuilder(30);

      while(sb.length() <= 8192) {
         int c = is.read();
         if (c < 0) {
            throw new RemoteScpException("Remote scp terminated unexpectedly.");
         }

         if (c == 10) {
            return sb.toString();
         }

         sb.append((char)c);
      }

      throw new RemoteScpException("Remote scp sent a too long line");
   }

   private void parseCLine(String line, FileInfo fileInfo) throws RemoteScpException {
      if (line.length() < 8) {
         throw new RemoteScpException("Malformed C line sent by remote SCP binary, line too short.");
      } else if (line.charAt(4) == ' ' && line.charAt(5) != ' ') {
         int lengthNameSep = line.indexOf(32, 5);
         if (lengthNameSep == -1) {
            throw new RemoteScpException("Malformed C line sent by remote SCP binary.");
         } else {
            String lengthSubstring = line.substring(5, lengthNameSep);
            String nameSubstring = line.substring(lengthNameSep + 1);
            if (lengthSubstring.length() > 0 && nameSubstring.length() > 0) {
               if (6 + lengthSubstring.length() + nameSubstring.length() != line.length()) {
                  throw new RemoteScpException("Malformed C line sent by remote SCP binary.");
               } else {
                  long len;
                  try {
                     len = Long.parseLong(lengthSubstring);
                  } catch (NumberFormatException var9) {
                     throw new RemoteScpException("Malformed C line sent by remote SCP binary, cannot parse file length.");
                  }

                  if (len < 0L) {
                     throw new RemoteScpException("Malformed C line sent by remote SCP binary, illegal file length.");
                  } else {
                     fileInfo.setLength(len);
                     fileInfo.setFilename(nameSubstring);
                  }
               }
            } else {
               throw new RemoteScpException("Malformed C line sent by remote SCP binary.");
            }
         }
      } else {
         throw new RemoteScpException("Malformed C line sent by remote SCP binary.");
      }
   }

   private void parseTLine(String line, FileInfo fileInfo) throws RemoteScpException {
      if (line.length() < 8) {
         throw new RemoteScpException("Malformed T line sent by remote SCP binary, line too short.");
      } else {
         int firstMsecBegin = line.indexOf(" ") + 1;
         if (firstMsecBegin != 0 && firstMsecBegin < line.length()) {
            int atimeBegin = line.indexOf(" ", firstMsecBegin + 1) + 1;
            if (atimeBegin != 0 && atimeBegin < line.length()) {
               int secondMsecBegin = line.indexOf(" ", atimeBegin + 1) + 1;
               if (secondMsecBegin != 0 && secondMsecBegin < line.length()) {
                  long modtime;
                  long firstMsec;
                  long atime;
                  long secondMsec;
                  try {
                     modtime = Long.parseLong(line.substring(0, firstMsecBegin - 1));
                     firstMsec = Long.parseLong(line.substring(firstMsecBegin, atimeBegin - 1));
                     atime = Long.parseLong(line.substring(atimeBegin, secondMsecBegin - 1));
                     secondMsec = Long.parseLong(line.substring(secondMsecBegin));
                  } catch (NumberFormatException var15) {
                     throw new RemoteScpException("Malformed C line sent by remote SCP binary, cannot parse file length.");
                  }

                  if (modtime >= 0L && firstMsec >= 0L && atime >= 0L && secondMsec >= 0L) {
                     fileInfo.setLastModified(modtime);
                  } else {
                     throw new RemoteScpException("Malformed C line sent by remote SCP binary, illegal file length.");
                  }
               } else {
                  throw new RemoteScpException("Malformed T line sent by remote SCP binary, line not enough data.");
               }
            } else {
               throw new RemoteScpException("Malformed T line sent by remote SCP binary, line not enough data.");
            }
         } else {
            throw new RemoteScpException("Malformed T line sent by remote SCP binary, line not enough data.");
         }
      }
   }

   private void sendFile(Channel channel, String localFile, String remoteName, String mode) throws IOException, RemoteScpException {
      byte[] buffer = new byte[65536];
      OutputStream os = new BufferedOutputStream(channel.getOutputStream(), 40000);
      InputStream is = new BufferedInputStream(channel.getInputStream(), 512);

      try {
         if (channel.isConnected()) {
            channel.start();
         } else {
            channel.connect();
         }
      } catch (JSchException jsche) {
         throw new IOException("Channel connection problems", jsche);
      }

      this.readResponse(is);
      File f = new File(localFile);
      long remain = f.length();
      String cMode = mode;
      if (mode == null) {
         cMode = "0600";
      }

      String cline = "C" + cMode + " " + remain + " " + remoteName + "\n";
      os.write(cline.getBytes());
      os.flush();
      this.readResponse(is);
      FileInputStream fis = new FileInputStream(f);
      Throwable var14 = null;

      try {
         while(remain > 0L) {
            int trans;
            if (remain > (long)buffer.length) {
               trans = buffer.length;
            } else {
               trans = (int)remain;
            }

            if (fis.read(buffer, 0, trans) != trans) {
               throw new IOException("Cannot read enough from local file " + localFile);
            }

            os.write(buffer, 0, trans);
            remain -= (long)trans;
         }
      } catch (Throwable var25) {
         var14 = var25;
         throw var25;
      } finally {
         if (fis != null) {
            if (var14 != null) {
               try {
                  fis.close();
               } catch (Throwable var23) {
                  var14.addSuppressed(var23);
               }
            } else {
               fis.close();
            }
         }

      }

      os.write(0);
      os.flush();
      this.readResponse(is);
      os.write("E\n".getBytes());
      os.flush();
   }

   private FileInfo receiveStream(Channel channel, String file, OutputStream targetStream) throws IOException, RemoteScpException {
      byte[] buffer = new byte[65536];
      OutputStream os = channel.getOutputStream();
      InputStream is = channel.getInputStream();

      try {
         if (channel.isConnected()) {
            channel.start();
         } else {
            channel.connect();
         }
      } catch (JSchException jsche) {
         throw new IOException("Channel connection problems", jsche);
      }

      os.write(0);
      os.flush();
      FileInfo fileInfo = new FileInfo();

      while(true) {
         int c = is.read();
         if (c < 0) {
            throw new RemoteScpException("Remote scp terminated unexpectedly.");
         }

         String line = this.receiveLine(is);
         if (c != 84) {
            if (c != 1 && c != 2) {
               if (c != 67) {
                  throw new RemoteScpException("Remote SCP error: " + (char)c + line);
               }

               this.parseCLine(line, fileInfo);
               if (targetStream != null) {
                  os.write(0);
                  os.flush();

                  try {
                     int thisTimeReceived;
                     for(long remain = fileInfo.getLength(); remain > 0L; remain -= (long)thisTimeReceived) {
                        int trans;
                        if (remain > (long)buffer.length) {
                           trans = buffer.length;
                        } else {
                           trans = (int)remain;
                        }

                        thisTimeReceived = is.read(buffer, 0, trans);
                        if (thisTimeReceived < 0) {
                           throw new IOException("Remote scp terminated connection unexpectedly");
                        }

                        targetStream.write(buffer, 0, thisTimeReceived);
                     }

                     targetStream.close();
                  } catch (IOException e) {
                     if (targetStream != null) {
                        targetStream.close();
                     }

                     throw e;
                  }

                  this.readResponse(is);
                  os.write(0);
                  os.flush();
               }

               return fileInfo;
            }

            throw new RemoteScpException("Remote SCP error: " + line);
         }

         this.parseTLine(line, fileInfo);
         os.write(0);
         os.flush();
      }
   }

   private ChannelExec getExecChannel() throws JSchException {
      ChannelExec channel = (ChannelExec)this.session.openChannel("exec");
      return channel;
   }

   public void put(String localFile, String remoteTargetDir, String remoteTargetName, String mode) throws IOException, RemoteScpException {
      ChannelExec channel = null;
      if (localFile != null && remoteTargetName != null) {
         if (mode != null) {
            if (mode.length() != 4) {
               throw new IllegalArgumentException("Invalid mode.");
            }

            for(char c : mode.toCharArray()) {
               if (!Character.isDigit(c)) {
                  throw new IllegalArgumentException("Invalid mode.");
               }
            }
         }

         String cmd = "scp -t ";
         if (mode != null) {
            cmd = cmd + "-p ";
         }

         if (remoteTargetDir != null && remoteTargetDir.length() > 0) {
            cmd = cmd + "-d " + remoteTargetDir;
         }

         try {
            channel = this.getExecChannel();
            channel.setCommand(cmd);
            this.sendFile(channel, localFile, remoteTargetName, mode);
            channel.disconnect();
         } catch (JSchException e) {
            if (channel != null) {
               channel.disconnect();
            }

            throw new IOException("Error during SCP transfer." + e.getMessage(), e);
         }
      } else {
         throw new IllegalArgumentException("Null argument.");
      }
   }

   public void get(String remoteFile, String localTarget) throws IOException, RemoteScpException {
      File f = new File(localTarget);
      FileOutputStream fop = new FileOutputStream(f);
      this.get(remoteFile, (OutputStream)fop);
   }

   public void get(String remoteFile, OutputStream localTarget) throws IOException, RemoteScpException {
      ChannelExec channel = null;
      if (remoteFile != null && localTarget != null) {
         String cmd = "scp -p -f " + remoteFile;

         try {
            channel = this.getExecChannel();
            channel.setCommand(cmd);
            this.receiveStream(channel, remoteFile, localTarget);
            channel.disconnect();
         } catch (JSchException e) {
            if (channel != null) {
               channel.disconnect();
            }

            throw new IOException("Error during SCP transfer. " + e.getMessage(), e);
         }
      } else {
         throw new IllegalArgumentException("Null argument.");
      }
   }

   public FileInfo getFileinfo(String remoteFile) throws IOException, RemoteScpException {
      ChannelExec channel = null;
      FileInfo fileInfo = null;
      if (remoteFile == null) {
         throw new IllegalArgumentException("Null argument.");
      } else {
         String cmd = "scp -p -f \"" + remoteFile + "\"";

         try {
            channel = this.getExecChannel();
            channel.setCommand(cmd);
            fileInfo = this.receiveStream(channel, remoteFile, (OutputStream)null);
            channel.disconnect();
         } catch (JSchException e) {
            throw new IOException("Error during SCP transfer. " + e.getMessage(), e);
         } finally {
            if (channel != null) {
               channel.disconnect();
            }

         }

         return fileInfo;
      }
   }

   public class FileInfo {
      private String filename;
      private long length;
      private long lastModified;

      public void setFilename(String filename) {
         this.filename = filename;
      }

      public String getFilename() {
         return this.filename;
      }

      public void setLength(long length) {
         this.length = length;
      }

      public long getLength() {
         return this.length;
      }

      public void setLastModified(long lastModified) {
         this.lastModified = lastModified;
      }

      public long getLastModified() {
         return this.lastModified;
      }
   }
}
