package org.sparkproject.jetty.security;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.util.Loader;
import org.sparkproject.jetty.util.resource.Resource;
import org.sparkproject.jetty.util.security.Credential;

public class JDBCLoginService extends AbstractLoginService {
   private static final Logger LOG = LoggerFactory.getLogger(JDBCLoginService.class);
   protected String _config;
   protected String _jdbcDriver;
   protected String _url;
   protected String _userName;
   protected String _password;
   protected String _userTableKey;
   protected String _userTablePasswordField;
   protected String _roleTableRoleField;
   protected String _userSql;
   protected String _roleSql;
   protected Connection _con;

   public JDBCLoginService() {
   }

   public JDBCLoginService(String name) {
      this.setName(name);
   }

   public JDBCLoginService(String name, String config) {
      this.setName(name);
      this.setConfig(config);
   }

   public JDBCLoginService(String name, IdentityService identityService, String config) {
      this.setName(name);
      this.setIdentityService(identityService);
      this.setConfig(config);
   }

   protected void doStart() throws Exception {
      Properties properties = new Properties();
      Resource resource = Resource.newResource(this._config);
      InputStream in = resource.getInputStream();

      try {
         properties.load(in);
      } catch (Throwable var11) {
         if (in != null) {
            try {
               in.close();
            } catch (Throwable var10) {
               var11.addSuppressed(var10);
            }
         }

         throw var11;
      }

      if (in != null) {
         in.close();
      }

      this._jdbcDriver = properties.getProperty("jdbcdriver");
      this._url = properties.getProperty("url");
      this._userName = properties.getProperty("username");
      this._password = properties.getProperty("password");
      this._userTableKey = properties.getProperty("usertablekey");
      this._userTablePasswordField = properties.getProperty("usertablepasswordfield");
      this._roleTableRoleField = properties.getProperty("roletablerolefield");
      String userTable = properties.getProperty("usertable");
      String userTableUserField = properties.getProperty("usertableuserfield");
      String roleTable = properties.getProperty("roletable");
      String roleTableKey = properties.getProperty("roletablekey");
      String userRoleTable = properties.getProperty("userroletable");
      String userRoleTableUserKey = properties.getProperty("userroletableuserkey");
      String userRoleTableRoleKey = properties.getProperty("userroletablerolekey");
      if (this._jdbcDriver == null || this._jdbcDriver.isEmpty() || this._url == null || this._url.isEmpty() || this._userName == null || this._userName.isEmpty() || this._password == null) {
         LOG.warn("UserRealm {} has not been properly configured", this.getName());
      }

      this._userSql = "select " + this._userTableKey + "," + this._userTablePasswordField + " from " + userTable + " where " + userTableUserField + " = ?";
      this._roleSql = "select r." + this._roleTableRoleField + " from " + roleTable + " r, " + userRoleTable + " u where u." + userRoleTableUserKey + " = ? and r." + roleTableKey + " = u." + userRoleTableRoleKey;
      Loader.loadClass(this._jdbcDriver).getDeclaredConstructor().newInstance();
      super.doStart();
   }

   public String getConfig() {
      return this._config;
   }

   public void setConfig(String config) {
      if (this.isRunning()) {
         throw new IllegalStateException("Running");
      } else {
         this._config = config;
      }
   }

   public Connection connectDatabase() throws SQLException {
      return DriverManager.getConnection(this._url, this._userName, this._password);
   }

   public UserPrincipal loadUserInfo(String username) {
      try {
         if (null == this._con) {
            this._con = this.connectDatabase();
         }

         PreparedStatement stat1 = this._con.prepareStatement(this._userSql);

         JDBCUserPrincipal var6;
         label87: {
            try {
               stat1.setObject(1, username);
               ResultSet rs1 = stat1.executeQuery();

               label80: {
                  try {
                     if (!rs1.next()) {
                        break label80;
                     }

                     int key = rs1.getInt(this._userTableKey);
                     String credentials = rs1.getString(this._userTablePasswordField);
                     var6 = new JDBCUserPrincipal(username, Credential.getCredential(credentials), key);
                  } catch (Throwable var9) {
                     if (rs1 != null) {
                        try {
                           rs1.close();
                        } catch (Throwable var8) {
                           var9.addSuppressed(var8);
                        }
                     }

                     throw var9;
                  }

                  if (rs1 != null) {
                     rs1.close();
                  }
                  break label87;
               }

               if (rs1 != null) {
                  rs1.close();
               }
            } catch (Throwable var10) {
               if (stat1 != null) {
                  try {
                     stat1.close();
                  } catch (Throwable var7) {
                     var10.addSuppressed(var7);
                  }
               }

               throw var10;
            }

            if (stat1 != null) {
               stat1.close();
            }

            return null;
         }

         if (stat1 != null) {
            stat1.close();
         }

         return var6;
      } catch (SQLException e) {
         LOG.warn("LoginService {} could not load user {}", new Object[]{this.getName(), username, e});
         this.closeConnection();
         return null;
      }
   }

   public List loadRoleInfo(UserPrincipal user) {
      if (user == null) {
         return null;
      } else {
         JDBCUserPrincipal jdbcUser = (JDBCUserPrincipal)user;

         try {
            if (null == this._con) {
               this._con = this.connectDatabase();
            }

            List<String> roles = new ArrayList();
            PreparedStatement stat2 = this._con.prepareStatement(this._roleSql);

            List var6;
            try {
               stat2.setInt(1, jdbcUser.getUserKey());
               ResultSet rs2 = stat2.executeQuery();

               try {
                  while(rs2.next()) {
                     roles.add(rs2.getString(this._roleTableRoleField));
                  }

                  var6 = (List)roles.stream().map(RolePrincipal::new).collect(Collectors.toList());
               } catch (Throwable var10) {
                  if (rs2 != null) {
                     try {
                        rs2.close();
                     } catch (Throwable var9) {
                        var10.addSuppressed(var9);
                     }
                  }

                  throw var10;
               }

               if (rs2 != null) {
                  rs2.close();
               }
            } catch (Throwable var11) {
               if (stat2 != null) {
                  try {
                     stat2.close();
                  } catch (Throwable var8) {
                     var11.addSuppressed(var8);
                  }
               }

               throw var11;
            }

            if (stat2 != null) {
               stat2.close();
            }

            return var6;
         } catch (SQLException e) {
            LOG.warn("LoginService {} could not load roles for user {}", new Object[]{this.getName(), user.getName(), e});
            this.closeConnection();
            return null;
         }
      }
   }

   protected void doStop() throws Exception {
      this.closeConnection();
      super.doStop();
   }

   private void closeConnection() {
      if (this._con != null) {
         if (LOG.isDebugEnabled()) {
            LOG.debug("Closing db connection for JDBCLoginService");
         }

         try {
            this._con.close();
         } catch (Exception e) {
            LOG.trace("IGNORED", e);
         }
      }

      this._con = null;
   }

   public class JDBCUserPrincipal extends UserPrincipal {
      final int _userKey;

      public JDBCUserPrincipal(String name, Credential credential, int key) {
         super(name, credential);
         this._userKey = key;
      }

      public int getUserKey() {
         return this._userKey;
      }
   }
}
