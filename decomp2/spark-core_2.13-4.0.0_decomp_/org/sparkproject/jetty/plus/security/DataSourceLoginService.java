package org.sparkproject.jetty.plus.security;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import javax.naming.InitialContext;
import javax.naming.NameNotFoundException;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.plus.jndi.NamingEntryUtil;
import org.sparkproject.jetty.security.AbstractLoginService;
import org.sparkproject.jetty.security.IdentityService;
import org.sparkproject.jetty.security.RolePrincipal;
import org.sparkproject.jetty.security.UserPrincipal;
import org.sparkproject.jetty.server.Server;
import org.sparkproject.jetty.util.security.Credential;

public class DataSourceLoginService extends AbstractLoginService {
   private static final Logger LOG = LoggerFactory.getLogger(DataSourceLoginService.class);
   private String _jndiName = "javax.sql.DataSource/default";
   private DataSource _datasource;
   private Server _server;
   private String _userTableName = "users";
   private String _userTableKey = "id";
   private String _userTableUserField = "username";
   private String _userTablePasswordField = "pwd";
   private String _roleTableName = "roles";
   private String _roleTableKey = "id";
   private String _roleTableRoleField = "role";
   private String _userRoleTableName = "user_roles";
   private String _userRoleTableUserKey = "user_id";
   private String _userRoleTableRoleKey = "role_id";
   private String _userSql;
   private String _roleSql;
   private boolean _createTables = false;

   public DataSourceLoginService() {
   }

   public DataSourceLoginService(String name) {
      this.setName(name);
   }

   public DataSourceLoginService(String name, IdentityService identityService) {
      this.setName(name);
      this.setIdentityService(identityService);
   }

   public void setJndiName(String jndi) {
      this._jndiName = jndi;
   }

   public String getJndiName() {
      return this._jndiName;
   }

   public void setServer(Server server) {
      this._server = server;
   }

   public Server getServer() {
      return this._server;
   }

   public void setCreateTables(boolean createTables) {
      this._createTables = createTables;
   }

   public boolean getCreateTables() {
      return this._createTables;
   }

   public void setUserTableName(String name) {
      this._userTableName = name;
   }

   public String getUserTableName() {
      return this._userTableName;
   }

   public String getUserTableKey() {
      return this._userTableKey;
   }

   public void setUserTableKey(String tableKey) {
      this._userTableKey = tableKey;
   }

   public String getUserTableUserField() {
      return this._userTableUserField;
   }

   public void setUserTableUserField(String tableUserField) {
      this._userTableUserField = tableUserField;
   }

   public String getUserTablePasswordField() {
      return this._userTablePasswordField;
   }

   public void setUserTablePasswordField(String tablePasswordField) {
      this._userTablePasswordField = tablePasswordField;
   }

   public String getRoleTableName() {
      return this._roleTableName;
   }

   public void setRoleTableName(String tableName) {
      this._roleTableName = tableName;
   }

   public String getRoleTableKey() {
      return this._roleTableKey;
   }

   public void setRoleTableKey(String tableKey) {
      this._roleTableKey = tableKey;
   }

   public String getRoleTableRoleField() {
      return this._roleTableRoleField;
   }

   public void setRoleTableRoleField(String tableRoleField) {
      this._roleTableRoleField = tableRoleField;
   }

   public String getUserRoleTableName() {
      return this._userRoleTableName;
   }

   public void setUserRoleTableName(String roleTableName) {
      this._userRoleTableName = roleTableName;
   }

   public String getUserRoleTableUserKey() {
      return this._userRoleTableUserKey;
   }

   public void setUserRoleTableUserKey(String roleTableUserKey) {
      this._userRoleTableUserKey = roleTableUserKey;
   }

   public String getUserRoleTableRoleKey() {
      return this._userRoleTableRoleKey;
   }

   public void setUserRoleTableRoleKey(String roleTableRoleKey) {
      this._userRoleTableRoleKey = roleTableRoleKey;
   }

   public UserPrincipal loadUserInfo(String username) {
      try {
         Connection connection = this.getConnection();

         DBUserPrincipal var7;
         label113: {
            try {
               PreparedStatement statement1;
               label105: {
                  statement1 = connection.prepareStatement(this._userSql);

                  try {
                     statement1.setObject(1, username);
                     ResultSet rs1 = statement1.executeQuery();

                     label87: {
                        try {
                           if (rs1.next()) {
                              int key = rs1.getInt(this._userTableKey);
                              String credentials = rs1.getString(this._userTablePasswordField);
                              var7 = new DBUserPrincipal(username, Credential.getCredential(credentials), key);
                              break label87;
                           }
                        } catch (Throwable var11) {
                           if (rs1 != null) {
                              try {
                                 rs1.close();
                              } catch (Throwable var10) {
                                 var11.addSuppressed(var10);
                              }
                           }

                           throw var11;
                        }

                        if (rs1 != null) {
                           rs1.close();
                        }
                        break label105;
                     }

                     if (rs1 != null) {
                        rs1.close();
                     }
                  } catch (Throwable var12) {
                     if (statement1 != null) {
                        try {
                           statement1.close();
                        } catch (Throwable var9) {
                           var12.addSuppressed(var9);
                        }
                     }

                     throw var12;
                  }

                  if (statement1 != null) {
                     statement1.close();
                  }
                  break label113;
               }

               if (statement1 != null) {
                  statement1.close();
               }
            } catch (Throwable var13) {
               if (connection != null) {
                  try {
                     connection.close();
                  } catch (Throwable var8) {
                     var13.addSuppressed(var8);
                  }
               }

               throw var13;
            }

            if (connection != null) {
               connection.close();
            }

            return null;
         }

         if (connection != null) {
            connection.close();
         }

         return var7;
      } catch (NamingException e) {
         LOG.warn("No datasource for {}", this._jndiName, e);
      } catch (SQLException e) {
         LOG.warn("Problem loading user info for {}", username, e);
      }

      return null;
   }

   public List loadRoleInfo(UserPrincipal user) {
      DBUserPrincipal dbuser = (DBUserPrincipal)user;

      try {
         Connection connection = this.getConnection();

         List var7;
         try {
            PreparedStatement statement2 = connection.prepareStatement(this._roleSql);

            try {
               List<String> roles = new ArrayList();
               statement2.setInt(1, dbuser.getKey());
               ResultSet rs2 = statement2.executeQuery();

               try {
                  while(rs2.next()) {
                     roles.add(rs2.getString(this._roleTableRoleField));
                  }

                  var7 = (List)roles.stream().map(RolePrincipal::new).collect(Collectors.toList());
               } catch (Throwable var12) {
                  if (rs2 != null) {
                     try {
                        rs2.close();
                     } catch (Throwable var11) {
                        var12.addSuppressed(var11);
                     }
                  }

                  throw var12;
               }

               if (rs2 != null) {
                  rs2.close();
               }
            } catch (Throwable var13) {
               if (statement2 != null) {
                  try {
                     statement2.close();
                  } catch (Throwable var10) {
                     var13.addSuppressed(var10);
                  }
               }

               throw var13;
            }

            if (statement2 != null) {
               statement2.close();
            }
         } catch (Throwable var14) {
            if (connection != null) {
               try {
                  connection.close();
               } catch (Throwable var9) {
                  var14.addSuppressed(var9);
               }
            }

            throw var14;
         }

         if (connection != null) {
            connection.close();
         }

         return var7;
      } catch (NamingException e) {
         LOG.warn("No datasource for {}", this._jndiName, e);
      } catch (SQLException e) {
         LOG.warn("Problem loading user info for {}", user.getName(), e);
      }

      return null;
   }

   public void initDb() throws NamingException, SQLException {
      if (this._datasource == null) {
         InitialContext ic = new InitialContext();

         assert ic != null;

         if (this._server != null) {
            try {
               this._datasource = (DataSource)NamingEntryUtil.lookup(this._server, this._jndiName);
            } catch (NameNotFoundException var3) {
            }
         }

         if (this._datasource == null) {
            this._datasource = (DataSource)NamingEntryUtil.lookup((Object)null, this._jndiName);
         }

         this._userSql = "select " + this._userTableKey + "," + this._userTablePasswordField + " from " + this._userTableName + " where " + this._userTableUserField + " = ?";
         this._roleSql = "select r." + this._roleTableRoleField + " from " + this._roleTableName + " r, " + this._userRoleTableName + " u where u." + this._userRoleTableUserKey + " = ? and r." + this._roleTableKey + " = u." + this._userRoleTableRoleKey;
         this.prepareTables();
      }
   }

   private void prepareTables() throws NamingException, SQLException {
      if (this._createTables) {
         boolean autocommit = true;
         Connection connection = this.getConnection();

         try {
            Statement stmt = connection.createStatement();

            try {
               autocommit = connection.getAutoCommit();
               connection.setAutoCommit(false);
               DatabaseMetaData metaData = connection.getMetaData();
               String tableName = metaData.storesLowerCaseIdentifiers() ? this._userTableName.toLowerCase(Locale.ENGLISH) : (metaData.storesUpperCaseIdentifiers() ? this._userTableName.toUpperCase(Locale.ENGLISH) : this._userTableName);
               ResultSet result = metaData.getTables((String)null, (String)null, tableName, (String[])null);

               try {
                  if (!result.next()) {
                     stmt.executeUpdate("create table " + this._userTableName + "(" + this._userTableKey + " integer," + this._userTableUserField + " varchar(100) not null unique," + this._userTablePasswordField + " varchar(20) not null, primary key(" + this._userTableKey + "))");
                     if (LOG.isDebugEnabled()) {
                        LOG.debug("Created table {}", this._userTableName);
                     }
                  }
               } catch (Throwable var75) {
                  if (result != null) {
                     try {
                        result.close();
                     } catch (Throwable var69) {
                        var75.addSuppressed(var69);
                     }
                  }

                  throw var75;
               }

               if (result != null) {
                  result.close();
               }

               tableName = metaData.storesLowerCaseIdentifiers() ? this._roleTableName.toLowerCase(Locale.ENGLISH) : (metaData.storesUpperCaseIdentifiers() ? this._roleTableName.toUpperCase(Locale.ENGLISH) : this._roleTableName);
               result = metaData.getTables((String)null, (String)null, tableName, (String[])null);

               try {
                  if (!result.next()) {
                     String str = "create table " + this._roleTableName + " (" + this._roleTableKey + " integer, " + this._roleTableRoleField + " varchar(100) not null unique, primary key(" + this._roleTableKey + "))";
                     stmt.executeUpdate(str);
                     if (LOG.isDebugEnabled()) {
                        LOG.debug("Created table {}", this._roleTableName);
                     }
                  }
               } catch (Throwable var74) {
                  if (result != null) {
                     try {
                        result.close();
                     } catch (Throwable var68) {
                        var74.addSuppressed(var68);
                     }
                  }

                  throw var74;
               }

               if (result != null) {
                  result.close();
               }

               tableName = metaData.storesLowerCaseIdentifiers() ? this._userRoleTableName.toLowerCase(Locale.ENGLISH) : (metaData.storesUpperCaseIdentifiers() ? this._userRoleTableName.toUpperCase(Locale.ENGLISH) : this._userRoleTableName);
               result = metaData.getTables((String)null, (String)null, tableName, (String[])null);

               try {
                  if (!result.next()) {
                     stmt.executeUpdate("create table " + this._userRoleTableName + " (" + this._userRoleTableUserKey + " integer, " + this._userRoleTableRoleKey + " integer, primary key (" + this._userRoleTableUserKey + ", " + this._userRoleTableRoleKey + "))");
                     stmt.executeUpdate("create index indx_user_role on " + this._userRoleTableName + "(" + this._userRoleTableUserKey + ")");
                     if (LOG.isDebugEnabled()) {
                        LOG.debug("Created table {} and index", this._userRoleTableName);
                     }
                  }
               } catch (Throwable var73) {
                  if (result != null) {
                     try {
                        result.close();
                     } catch (Throwable var67) {
                        var73.addSuppressed(var67);
                     }
                  }

                  throw var73;
               }

               if (result != null) {
                  result.close();
               }

               connection.commit();
            } catch (Throwable var76) {
               if (stmt != null) {
                  try {
                     stmt.close();
                  } catch (Throwable var66) {
                     var76.addSuppressed(var66);
                  }
               }

               throw var76;
            }

            if (stmt != null) {
               stmt.close();
            }
         } finally {
            try {
               connection.setAutoCommit(autocommit);
            } catch (SQLException e) {
               if (LOG.isDebugEnabled()) {
                  LOG.debug("Prepare tables", e);
               }
            } finally {
               try {
                  connection.close();
               } catch (SQLException e) {
                  if (LOG.isDebugEnabled()) {
                     LOG.debug("Prepare tables", e);
                  }
               }

            }

         }
      } else if (LOG.isDebugEnabled()) {
         LOG.debug("createTables false");
      }

   }

   private Connection getConnection() throws NamingException, SQLException {
      this.initDb();
      return this._datasource.getConnection();
   }

   public class DBUserPrincipal extends UserPrincipal {
      private int _key;

      public DBUserPrincipal(String name, Credential credential, int key) {
         super(name, credential);
         this._key = key;
      }

      public int getKey() {
         return this._key;
      }
   }
}
