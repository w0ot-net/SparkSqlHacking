package org.apache.ivy.plugins.repository.ssh;

import com.jcraft.jsch.ConfigRepository;
import com.jcraft.jsch.OpenSSHConfig;
import com.jcraft.jsch.Session;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.apache.ivy.core.settings.TimeoutConstraint;
import org.apache.ivy.plugins.repository.AbstractRepository;
import org.apache.ivy.util.Credentials;
import org.apache.ivy.util.CredentialsUtil;
import org.apache.ivy.util.Message;

public abstract class AbstractSshBasedRepository extends AbstractRepository {
   private File keyFile = null;
   private File passFile = null;
   private String userPassword = null;
   private String keyFilePassword = null;
   private String user = null;
   private String host = null;
   private int port = -1;
   private boolean allowedAgentUse = false;
   private String sshConfig = null;
   private static final Map credentialsCache = new HashMap();
   private static final int MAX_CREDENTIALS_CACHE_SIZE = 100;

   public AbstractSshBasedRepository() {
   }

   public AbstractSshBasedRepository(TimeoutConstraint timeoutConstraint) {
      super(timeoutConstraint);
   }

   protected Session getSession(String pathOrUri) throws IOException {
      URI uri = this.parseURI(pathOrUri);
      String host = this.getHost();
      int port = this.getPort();
      String user = this.getUser();
      String userPassword = this.getUserPassword();
      String sshConfig = this.getSshConfig();
      File keyFile = this.getKeyFile();
      if (uri != null && uri.getScheme() != null) {
         if (uri.getHost() != null) {
            host = uri.getHost();
         }

         if (uri.getPort() != -1) {
            port = uri.getPort();
         }

         if (uri.getUserInfo() != null) {
            String userInfo = uri.getUserInfo();
            if (!userInfo.contains(":")) {
               user = userInfo;
            } else {
               user = userInfo.substring(0, userInfo.indexOf(":"));
               userPassword = userInfo.substring(userInfo.indexOf(":") + 1);
            }
         }
      }

      if (sshConfig != null) {
         ConfigRepository configRepository = OpenSSHConfig.parseFile(sshConfig);
         ConfigRepository.Config config = configRepository.getConfig(host);
         host = config.getHostname();
         if (user == null) {
            user = config.getUser();
         }

         String keyFilePath = config.getValue("IdentityFile");
         if (keyFilePath != null && keyFile == null) {
            keyFile = new File(keyFilePath);
         }
      }

      if (host == null) {
         throw new IllegalArgumentException("missing host information. host should be provided either directly on the repository or in the connection URI , or in the openssh config file specified by sshConfig");
      } else {
         if (user == null) {
            Credentials c = this.requestCredentials(host);
            if (c != null) {
               user = c.getUserName();
               userPassword = c.getPasswd();
            } else {
               Message.error("username is not set");
            }
         }

         return SshCache.getInstance().getSession(host, port, user, userPassword, keyFile, this.getKeyFilePassword(), this.getPassFile(), this.isAllowedAgentUse());
      }
   }

   private URI parseURI(String source) {
      try {
         URI uri = new URI(source);
         if (uri.getScheme() != null && !uri.getScheme().toLowerCase(Locale.US).equals(this.getRepositoryScheme().toLowerCase(Locale.US))) {
            throw new URISyntaxException(source, "Wrong scheme in URI. Expected " + this.getRepositoryScheme() + " as scheme!");
         } else if (uri.getHost() == null && this.getHost() == null) {
            throw new URISyntaxException(source, "Missing host in URI or in resolver");
         } else if (uri.getPath() == null) {
            throw new URISyntaxException(source, "Missing path in URI");
         } else {
            return uri;
         }
      } catch (URISyntaxException e) {
         Message.error(e.getMessage());
         Message.error("The uri '" + source + "' is in the wrong format.");
         Message.error("Please use " + this.getRepositoryScheme() + "://user:pass@hostname/path/to/repository");
         return null;
      }
   }

   private Credentials requestCredentials(String host) {
      Credentials c = (Credentials)credentialsCache.get(host);
      if (c == null) {
         c = CredentialsUtil.promptCredentials(new Credentials((String)null, host, this.user, this.userPassword), this.getPassFile());
         if (c != null) {
            if (credentialsCache.size() > 100) {
               credentialsCache.clear();
            }

            credentialsCache.put(host, c);
         }
      }

      return c;
   }

   protected void releaseSession(Session session, String pathOrUri) {
      session.disconnect();
      SshCache.getInstance().clearSession(session);
   }

   public void setUser(String user) {
      this.user = user;
   }

   public String getUser() {
      return this.user;
   }

   public void setKeyFile(File filePath) {
      this.keyFile = filePath;
      if (!this.keyFile.exists()) {
         Message.warn("Pemfile " + this.keyFile.getAbsolutePath() + " doesn't exist.");
         this.keyFile = null;
      } else if (!this.keyFile.canRead()) {
         Message.warn("Pemfile " + this.keyFile.getAbsolutePath() + " not readable.");
         this.keyFile = null;
      } else {
         Message.debug("Using " + this.keyFile.getAbsolutePath() + " as keyfile.");
      }

   }

   public File getKeyFile() {
      return this.keyFile;
   }

   public void setUserPassword(String password) {
      this.userPassword = password;
   }

   public String getKeyFilePassword() {
      return this.keyFilePassword;
   }

   public void setKeyFilePassword(String keyFilePassword) {
      this.keyFilePassword = keyFilePassword;
   }

   public String getUserPassword() {
      return this.userPassword;
   }

   public String getHost() {
      return this.host;
   }

   public void setHost(String host) {
      this.host = host;
   }

   public int getPort() {
      return this.port;
   }

   public void setPort(int port) {
      this.port = port;
   }

   public void setPassFile(File passFile) {
      this.passFile = passFile;
   }

   public File getPassFile() {
      return this.passFile;
   }

   public boolean isAllowedAgentUse() {
      return this.allowedAgentUse;
   }

   public void setAllowedAgentUse(boolean allowedAgentUse) {
      this.allowedAgentUse = allowedAgentUse;
   }

   public String getSshConfig() {
      return this.sshConfig;
   }

   public void setSshConfig(String sshConfig) {
      this.sshConfig = sshConfig;
   }

   protected abstract String getRepositoryScheme();
}
