package org.apache.ivy.plugins.repository.ssh;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UIKeyboardInteractive;
import com.jcraft.jsch.UserInfo;
import com.jcraft.jsch.agentproxy.Connector;
import com.jcraft.jsch.agentproxy.ConnectorFactory;
import com.jcraft.jsch.agentproxy.RemoteIdentityRepository;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import org.apache.ivy.core.IvyContext;
import org.apache.ivy.core.event.IvyEvent;
import org.apache.ivy.core.event.IvyListener;
import org.apache.ivy.util.Checks;
import org.apache.ivy.util.Credentials;
import org.apache.ivy.util.CredentialsUtil;
import org.apache.ivy.util.Message;

public final class SshCache {
   private static final int SSH_DEFAULT_PORT = 22;
   private static SshCache instance = new SshCache();
   private final Map uriCacheMap = new HashMap();
   private final Map sessionCacheMap = new HashMap();

   private SshCache() {
   }

   public static SshCache getInstance() {
      return instance;
   }

   private Entry getCacheEntry(String user, String host, int port) {
      return (Entry)this.uriCacheMap.get(createCacheKey(user, host, port));
   }

   private static String createCacheKey(String user, String host, int port) {
      String portToUse = "22";
      if (port != -1 && port != 22) {
         portToUse = Integer.toString(port);
      }

      return user.trim().toLowerCase(Locale.US) + "@" + host.trim().toLowerCase(Locale.US) + ":" + portToUse;
   }

   private Entry getCacheEntry(Session session) {
      return (Entry)this.sessionCacheMap.get(session);
   }

   private void setSession(String user, String host, int port, Session newSession) {
      Entry entry = (Entry)this.uriCacheMap.get(createCacheKey(user, host, port));
      Session oldSession = null;
      if (entry != null) {
         oldSession = entry.getSession();
      }

      if (oldSession != null && !oldSession.equals(newSession) && oldSession.isConnected()) {
         entry.releaseChannelSftp();
         String oldhost = oldSession.getHost();
         Message.verbose(":: SSH :: closing ssh connection from " + oldhost + "...");
         oldSession.disconnect();
         Message.verbose(":: SSH :: ssh connection closed from " + oldhost);
      }

      if (newSession == null && entry != null) {
         this.uriCacheMap.remove(createCacheKey(user, host, port));
         if (entry.getSession() != null) {
            this.sessionCacheMap.remove(entry.getSession());
         }
      } else {
         Entry newEntry = new Entry(newSession, user, host, port);
         this.uriCacheMap.put(createCacheKey(user, host, port), newEntry);
         this.sessionCacheMap.put(newSession, newEntry);
      }

   }

   public void clearSession(Session session) {
      Entry entry = (Entry)this.sessionCacheMap.get(session);
      if (entry != null) {
         this.setSession(entry.getUser(), entry.getHost(), entry.getPort(), (Session)null);
      }

   }

   public ChannelSftp getChannelSftp(Session session) throws IOException {
      ChannelSftp channel = null;
      Entry entry = this.getCacheEntry(session);
      if (entry != null) {
         channel = entry.getChannelSftp();
         if (channel != null && !channel.isConnected()) {
            entry.releaseChannelSftp();
            channel = null;
         }
      }

      return channel;
   }

   public void attachChannelSftp(Session session, ChannelSftp channel) {
      Entry entry = this.getCacheEntry(session);
      if (entry == null) {
         throw new IllegalArgumentException("No entry for " + session + " in the cache");
      } else {
         entry.setChannelSftp(channel);
      }
   }

   private boolean attemptAgentUse(JSch jsch) {
      try {
         Connector con = ConnectorFactory.getDefault().createConnector();
         jsch.setIdentityRepository(new RemoteIdentityRepository(con));
         return true;
      } catch (Exception e) {
         Message.verbose(":: SSH :: Failure connecting to agent :: " + e.toString());
         return false;
      }
   }

   public Session getSession(String host, int port, String username, String userPassword, File pemFile, String pemPassword, File passFile, boolean allowedAgentUse) throws IOException {
      Checks.checkNotNull(host, "host");
      Checks.checkNotNull(username, "user");
      Entry entry = this.getCacheEntry(username, host, port);
      Session session = null;
      if (entry != null) {
         session = entry.getSession();
      }

      if (session == null || !session.isConnected()) {
         Message.verbose(":: SSH :: connecting to " + host + "...");

         try {
            JSch jsch = new JSch();
            if (port != -1) {
               session = jsch.getSession(username, host, port);
            } else {
               session = jsch.getSession(username, host);
            }

            if (allowedAgentUse) {
               this.attemptAgentUse(jsch);
            }

            if (pemFile != null) {
               jsch.addIdentity(pemFile.getAbsolutePath(), pemPassword);
            }

            session.setUserInfo(new CfUserInfo(host, username, userPassword, pemFile, pemPassword, passFile));
            session.setDaemonThread(true);
            Properties config = new Properties();
            config.setProperty("PreferredAuthentications", "publickey,keyboard-interactive,password");
            session.setConfig(config);
            session.connect();
            Message.verbose(":: SSH :: connected to " + host + "!");
            this.setSession(username, host, port, session);
         } catch (JSchException e) {
            if (passFile != null && passFile.exists()) {
               passFile.delete();
            }

            throw new IOException(e.getMessage(), e);
         }
      }

      return session;
   }

   private class Entry {
      private Session session = null;
      private ChannelSftp channelSftp = null;
      private String host = null;
      private String user = null;
      private int port = 22;

      public String getHost() {
         return this.host;
      }

      public int getPort() {
         return this.port;
      }

      public String getUser() {
         return this.user;
      }

      public Entry(Session newSession, String newUser, String newHost, int newPort) {
         this.session = newSession;
         this.host = newHost;
         this.user = newUser;
         this.port = newPort;
         IvyContext.getContext().getEventManager().addIvyListener(new IvyListener() {
            public void progress(IvyEvent event) {
               event.getSource().removeIvyListener(this);
               SshCache.this.clearSession(Entry.this.session);
            }
         }, "post-resolve");
      }

      public void setChannelSftp(ChannelSftp newChannel) {
         if (this.channelSftp != null && newChannel != null) {
            throw new IllegalStateException("Only one sftp channelSftp per session allowed");
         } else {
            this.channelSftp = newChannel;
         }
      }

      public ChannelSftp getChannelSftp() {
         return this.channelSftp;
      }

      private Session getSession() {
         return this.session;
      }

      public void releaseChannelSftp() {
         if (this.channelSftp != null && this.channelSftp.isConnected()) {
            Message.verbose(":: SFTP :: closing sftp connection from " + this.host + "...");
            this.channelSftp.disconnect();
            this.channelSftp = null;
            Message.verbose(":: SFTP :: sftp connection closed from " + this.host);
         }

      }
   }

   private static class CfUserInfo implements UserInfo, UIKeyboardInteractive {
      private String userPassword;
      private String pemPassword;
      private String userName;
      private final File pemFile;
      private final String host;
      private final File passfile;

      public CfUserInfo(String host, String userName, String userPassword, File pemFile, String pemPassword, File passfile) {
         this.userPassword = userPassword;
         this.pemPassword = pemPassword;
         this.host = host;
         this.passfile = passfile;
         this.userName = userName;
         this.pemFile = pemFile;
      }

      public void showMessage(String message) {
         Message.info(message);
      }

      public boolean promptYesNo(String message) {
         return true;
      }

      public boolean promptPassword(String message) {
         return true;
      }

      public boolean promptPassphrase(String message) {
         return true;
      }

      public String getPassword() {
         if (this.userPassword == null) {
            Credentials c = CredentialsUtil.promptCredentials(new Credentials((String)null, this.host, this.userName, this.userPassword), this.passfile);
            if (c != null) {
               this.userName = c.getUserName();
               this.userPassword = c.getPasswd();
            }
         }

         return this.userPassword;
      }

      public String getPassphrase() {
         if (this.pemPassword == null && this.pemFile != null) {
            Credentials c = CredentialsUtil.promptCredentials(new Credentials((String)null, this.pemFile.getAbsolutePath(), this.userName, this.pemPassword), this.passfile);
            if (c != null) {
               this.userName = c.getUserName();
               this.pemPassword = c.getPasswd();
            }
         }

         return this.pemPassword;
      }

      public String[] promptKeyboardInteractive(String destination, String name, String instruction, String[] prompt, boolean[] echo) {
         return new String[]{this.getPassword()};
      }
   }
}
