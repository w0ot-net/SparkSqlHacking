package org.apache.ivy.plugins.repository.ssh;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import org.apache.ivy.core.settings.TimeoutConstraint;
import org.apache.ivy.plugins.repository.Resource;
import org.apache.ivy.util.Message;

public class SshRepository extends AbstractSshBasedRepository {
   private static final int BUFFER_SIZE = 65536;
   private static final String ARGUMENT_PLACEHOLDER = "%arg";
   private static final int POLL_SLEEP_TIME = 500;
   private char fileSeparator = '/';
   private String listCommand = "ls -1";
   private String existCommand = "ls";
   private String createDirCommand = "mkdir";
   private String publishPermissions = null;

   public SshRepository() {
   }

   public SshRepository(TimeoutConstraint timeoutConstraint) {
      super(timeoutConstraint);
   }

   public Resource getResource(String source) {
      Message.debug("SShRepository:getResource called: " + source);
      return new SshResource(this, source);
   }

   public SshResource resolveResource(String source) {
      Message.debug("SShRepository:resolveResource called: " + source);
      SshResource result = null;
      Session session = null;

      try {
         session = this.getSession(source);
         Scp myCopy = new Scp(session);
         Scp.FileInfo fileInfo = myCopy.getFileinfo((new URI(source)).getPath());
         result = new SshResource(this, source, true, fileInfo.getLength(), fileInfo.getLastModified());
      } catch (URISyntaxException | IOException var6) {
         if (session != null) {
            this.releaseSession(session, source);
         }

         result = new SshResource();
      } catch (RemoteScpException var7) {
         result = new SshResource();
      }

      Message.debug("SShRepository:resolveResource end.");
      return result;
   }

   private void readSessionOutput(ChannelExec channel, StringBuilder strStdout, StringBuilder strStderr) throws IOException {
      InputStream stdout = channel.getInputStream();
      InputStream stderr = channel.getErrStream();

      try {
         channel.connect();
      } catch (JSchException jsche) {
         throw new IOException("Channel connection problems", jsche);
      }

      byte[] buffer = new byte[65536];

      while(true) {
         int avail = 0;

         while((avail = stdout.available()) > 0) {
            int len = stdout.read(buffer, 0, avail > 65535 ? 65536 : avail);
            strStdout.append(new String(buffer, 0, len));
         }

         while((avail = stderr.available()) > 0) {
            int len = stderr.read(buffer, 0, avail > 65535 ? 65536 : avail);
            strStderr.append(new String(buffer, 0, len));
         }

         if (channel.isClosed()) {
            avail = 0;

            while((avail = stdout.available()) > 0) {
               int len = stdout.read(buffer, 0, avail > 65535 ? 65536 : avail);
               strStdout.append(new String(buffer, 0, len));
            }

            while((avail = stderr.available()) > 0) {
               int len = stderr.read(buffer, 0, avail > 65535 ? 65536 : avail);
               strStderr.append(new String(buffer, 0, len));
            }

            return;
         }

         try {
            Thread.sleep(500L);
         } catch (Exception var9) {
         }
      }
   }

   public List list(String parent) throws IOException {
      Message.debug("SShRepository:list called: " + parent);
      List<String> result = new ArrayList();
      Session session = null;
      ChannelExec channel = null;
      session = this.getSession(parent);
      channel = this.getExecChannel(session);
      URI parentUri = null;

      try {
         parentUri = new URI(parent);
      } catch (URISyntaxException e) {
         throw new IOException("The uri '" + parent + "' is not valid!", e);
      }

      String fullCmd = this.replaceArgument(this.listCommand, parentUri.getPath());
      channel.setCommand(fullCmd);
      StringBuilder stdOut = new StringBuilder();
      StringBuilder stdErr = new StringBuilder();
      this.readSessionOutput(channel, stdOut, stdErr);
      if (channel.getExitStatus() != 0) {
         Message.error("Ssh ListCommand exited with status != 0");
         Message.error(stdErr.toString());
         return null;
      } else {
         BufferedReader br = new BufferedReader(new StringReader(stdOut.toString()));
         String line = null;

         while((line = br.readLine()) != null) {
            result.add(line);
         }

         return result;
      }
   }

   private ChannelExec getExecChannel(Session session) throws IOException {
      try {
         ChannelExec channel = (ChannelExec)session.openChannel("exec");
         return channel;
      } catch (JSchException var4) {
         throw new IOException();
      }
   }

   private String replaceArgument(String command, String argument) {
      String fullCmd;
      if (!command.contains("%arg")) {
         fullCmd = command + " " + argument;
      } else {
         fullCmd = command.replaceAll("%arg", argument);
      }

      return fullCmd;
   }

   public void put(File source, String destination, boolean overwrite) throws IOException {
      Message.debug("SShRepository:put called: " + destination);
      Session session = this.getSession(destination);
      URI destinationUri = null;

      try {
         destinationUri = new URI(destination);
      } catch (URISyntaxException e) {
         throw new IOException("The uri '" + destination + "' is not valid!", e);
      }

      try {
         String filePath = destinationUri.getPath();
         int lastSep = filePath.lastIndexOf(this.fileSeparator);
         String path;
         String name;
         if (lastSep == -1) {
            name = filePath;
            path = null;
         } else {
            name = filePath.substring(lastSep + 1);
            path = filePath.substring(0, lastSep);
         }

         if (!overwrite && this.checkExistence(filePath, session)) {
            throw new IOException("destination file exists and overwrite == false");
         } else {
            if (path != null) {
               this.makePath(path, session);
            }

            Scp myCopy = new Scp(session);
            myCopy.put(source.getCanonicalPath(), path, name, this.publishPermissions);
         }
      } catch (IOException e) {
         if (session != null) {
            this.releaseSession(session, destination);
         }

         throw e;
      } catch (RemoteScpException e) {
         throw new IOException(e.getMessage());
      }
   }

   private void makePath(String path, Session session) throws IOException {
      ChannelExec channel = null;
      String trimmed = path;

      try {
         while(trimmed.length() > 0 && trimmed.charAt(trimmed.length() - 1) == this.fileSeparator) {
            trimmed = trimmed.substring(0, trimmed.length() - 1);
         }

         if (trimmed.length() != 0 && !this.checkExistence(trimmed, session)) {
            int nextSlash = trimmed.lastIndexOf(this.fileSeparator);
            if (nextSlash > 0) {
               String parent = trimmed.substring(0, nextSlash);
               this.makePath(parent, session);
            }

            channel = this.getExecChannel(session);
            String mkdir = this.replaceArgument(this.createDirCommand, trimmed);
            Message.debug("SShRepository: trying to create path: " + mkdir);
            channel.setCommand(mkdir);
            StringBuilder stdOut = new StringBuilder();
            StringBuilder stdErr = new StringBuilder();
            this.readSessionOutput(channel, stdOut, stdErr);
            return;
         }
      } finally {
         if (channel != null) {
            channel.disconnect();
         }

      }

   }

   private boolean checkExistence(String filePath, Session session) throws IOException {
      Message.debug("SShRepository: checkExistence called: " + filePath);
      ChannelExec channel = null;
      channel = this.getExecChannel(session);
      String fullCmd = this.replaceArgument(this.existCommand, filePath);
      channel.setCommand(fullCmd);
      StringBuilder stdOut = new StringBuilder();
      StringBuilder stdErr = new StringBuilder();
      this.readSessionOutput(channel, stdOut, stdErr);
      return channel.getExitStatus() == 0;
   }

   public void get(String source, File destination) throws IOException {
      Message.debug("SShRepository:get called: " + source + " to " + destination.getCanonicalPath());
      if (destination.getParentFile() != null) {
         destination.getParentFile().mkdirs();
      }

      Session session = this.getSession(source);
      URI sourceUri = null;

      try {
         sourceUri = new URI(source);
      } catch (URISyntaxException e) {
         throw new IOException("The uri '" + source + "' is not valid!", e);
      }

      try {
         Scp myCopy = new Scp(session);
         myCopy.get(sourceUri.getPath(), destination.getCanonicalPath());
      } catch (IOException e) {
         if (session != null) {
            this.releaseSession(session, source);
         }

         throw e;
      } catch (RemoteScpException e) {
         throw new IOException(e.getMessage());
      }
   }

   public void setListCommand(String cmd) {
      this.listCommand = cmd.trim();
   }

   public String getListCommand() {
      return this.listCommand;
   }

   public String getCreateDirCommand() {
      return this.createDirCommand;
   }

   public void setCreateDirCommand(String createDirCommand) {
      this.createDirCommand = createDirCommand;
   }

   public String getExistCommand() {
      return this.existCommand;
   }

   public void setExistCommand(String existCommand) {
      this.existCommand = existCommand;
   }

   public void setFileSeparator(char fileSeparator) {
      this.fileSeparator = fileSeparator;
   }

   public void setPublishPermissions(String permissions) {
      this.publishPermissions = permissions;
   }

   protected String getRepositoryScheme() {
      return "ssh";
   }

   public InputStream openStream(SshResource resource) throws IOException {
      Session session = this.getSession(resource.getName());
      Scp scp = new Scp(session);
      ByteArrayOutputStream os = new ByteArrayOutputStream();

      try {
         scp.get(resource.getName(), (OutputStream)os);
      } catch (IOException e) {
         if (session != null) {
            this.releaseSession(session, resource.getName());
         }

         throw e;
      } catch (RemoteScpException e) {
         throw new IOException(e.getMessage());
      }

      return new ByteArrayInputStream(os.toByteArray());
   }
}
