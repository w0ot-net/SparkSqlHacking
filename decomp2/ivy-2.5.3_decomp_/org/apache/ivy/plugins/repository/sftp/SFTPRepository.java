package org.apache.ivy.plugins.repository.sftp;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpATTRS;
import com.jcraft.jsch.SftpException;
import com.jcraft.jsch.SftpProgressMonitor;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.apache.ivy.core.settings.TimeoutConstraint;
import org.apache.ivy.plugins.repository.BasicResource;
import org.apache.ivy.plugins.repository.Resource;
import org.apache.ivy.plugins.repository.ssh.AbstractSshBasedRepository;
import org.apache.ivy.plugins.repository.ssh.SshCache;
import org.apache.ivy.util.Message;

public class SFTPRepository extends AbstractSshBasedRepository {
   private static final long MILLIS_PER_SECOND = 1000L;

   public SFTPRepository() {
   }

   public SFTPRepository(TimeoutConstraint timeoutConstraint) {
      super(timeoutConstraint);
   }

   public Resource getResource(String source) {
      return new SFTPResource(this, source);
   }

   public Resource resolveResource(String path) {
      try {
         List<ChannelSftp.LsEntry> r = this.getSftpChannel(path).ls(this.getPath(path));
         if (r != null) {
            SftpATTRS attrs = ((ChannelSftp.LsEntry)r.get(0)).getAttrs();
            return new BasicResource(path, true, attrs.getSize(), (long)attrs.getMTime() * 1000L, false);
         }
      } catch (Exception e) {
         Message.debug("Error while resolving resource " + path, e);
      }

      return new BasicResource(path, false, 0L, 0L, false);
   }

   public InputStream openStream(SFTPResource resource) throws IOException {
      ChannelSftp c = this.getSftpChannel(resource.getName());

      try {
         String path = this.getPath(resource.getName());
         return c.get(path);
      } catch (URISyntaxException | SftpException var4) {
         throw new IOException("impossible to open stream for " + resource + " on " + this.getHost() + (((Exception)var4).getMessage() != null ? ": " + ((Exception)var4).getMessage() : ""), var4);
      }
   }

   public void get(String source, File destination) throws IOException {
      this.fireTransferInitiated(this.getResource(source), 5);
      ChannelSftp c = this.getSftpChannel(source);

      try {
         String path = this.getPath(source);
         c.get(path, destination.getAbsolutePath(), new MyProgressMonitor());
      } catch (URISyntaxException | SftpException var5) {
         throw new IOException("impossible to get " + source + " on " + this.getHost() + (((Exception)var5).getMessage() != null ? ": " + ((Exception)var5).getMessage() : ""), var5);
      }
   }

   public void put(File source, String destination, boolean overwrite) throws IOException {
      this.fireTransferInitiated(this.getResource(destination), 6);
      ChannelSftp c = this.getSftpChannel(destination);

      try {
         String path = this.getPath(destination);
         if (!overwrite && this.checkExistence(path, c)) {
            throw new IOException("destination file exists and overwrite == false");
         } else {
            if (path.indexOf(47) != -1) {
               this.mkdirs(path.substring(0, path.lastIndexOf(47)), c);
            }

            c.put(source.getAbsolutePath(), path, new MyProgressMonitor());
         }
      } catch (URISyntaxException | SftpException e) {
         throw new IOException(((Exception)e).getMessage(), e);
      }
   }

   private void mkdirs(String directory, ChannelSftp c) throws SftpException {
      try {
         SftpATTRS att = c.stat(directory);
         if (att != null && att.isDir()) {
            return;
         }
      } catch (SftpException var4) {
         if (directory.indexOf(47) != -1) {
            this.mkdirs(directory.substring(0, directory.lastIndexOf(47)), c);
         }

         c.mkdir(directory);
      }

   }

   private String getPath(String sftpURI) throws URISyntaxException {
      String result = null;
      URI uri = new URI(sftpURI);
      result = uri.getPath();
      if (result == null) {
         throw new URISyntaxException(sftpURI, "Missing path in URI.");
      } else {
         return result;
      }
   }

   public List list(String parent) throws IOException {
      try {
         ChannelSftp c = this.getSftpChannel(parent);
         String path = this.getPath(parent);
         Collection<ChannelSftp.LsEntry> r = c.ls(path);
         if (r != null) {
            if (!path.endsWith("/")) {
               path = parent + "/";
            }

            List<String> result = new ArrayList();

            for(ChannelSftp.LsEntry entry : r) {
               if (!".".equals(entry.getFilename()) && !"..".equals(entry.getFilename())) {
                  result.add(path + entry.getFilename());
               }
            }

            return result;
         } else {
            return null;
         }
      } catch (URISyntaxException | SftpException e) {
         throw new IOException("Failed to return a listing for '" + parent + "'", e);
      }
   }

   private boolean checkExistence(String file, ChannelSftp channel) {
      try {
         return channel.stat(file) != null;
      } catch (SftpException var4) {
         return false;
      }
   }

   private ChannelSftp getSftpChannel(String pathOrUri) throws IOException {
      Session session = this.getSession(pathOrUri);
      String host = session.getHost();
      ChannelSftp channel = SshCache.getInstance().getChannelSftp(session);
      if (channel == null) {
         try {
            channel = (ChannelSftp)session.openChannel("sftp");
            channel.connect();
            Message.verbose(":: SFTP :: connected to " + host + "!");
            SshCache.getInstance().attachChannelSftp(session, channel);
         } catch (JSchException e) {
            throw new IOException(e.getMessage(), e);
         }
      }

      return channel;
   }

   protected String getRepositoryScheme() {
      return "sftp";
   }

   private final class MyProgressMonitor implements SftpProgressMonitor {
      private long totalLength;

      private MyProgressMonitor() {
      }

      public void init(int op, String src, String dest, long max) {
         this.totalLength = max;
         SFTPRepository.this.fireTransferStarted(max);
      }

      public void end() {
         SFTPRepository.this.fireTransferCompleted(this.totalLength);
      }

      public boolean count(long count) {
         SFTPRepository.this.fireTransferProgress(count);
         return true;
      }
   }
}
