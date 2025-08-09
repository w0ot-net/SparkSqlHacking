package org.sparkproject.jetty.server.session;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.util.ClassLoadingObjectInputStream;
import org.sparkproject.jetty.util.StringUtil;
import org.sparkproject.jetty.util.annotation.ManagedAttribute;
import org.sparkproject.jetty.util.annotation.ManagedObject;

@ManagedObject
public class JDBCSessionDataStore extends AbstractSessionDataStore {
   private static final Logger LOG = LoggerFactory.getLogger(JDBCSessionDataStore.class);
   public static final String NULL_CONTEXT_PATH = "/";
   protected boolean _initialized = false;
   protected DatabaseAdaptor _dbAdaptor;
   protected SessionTableSchema _sessionTableSchema;
   protected boolean _schemaProvided;
   private static final ByteArrayInputStream EMPTY = new ByteArrayInputStream(new byte[0]);

   protected void doStart() throws Exception {
      if (this._dbAdaptor == null) {
         throw new IllegalStateException("No jdbc config");
      } else {
         this.initialize();
         super.doStart();
      }
   }

   protected void doStop() throws Exception {
      super.doStop();
      this._initialized = false;
      if (!this._schemaProvided) {
         this._sessionTableSchema = null;
      }

   }

   public void initialize() throws Exception {
      if (!this._initialized) {
         this._initialized = true;
         if (this._sessionTableSchema == null) {
            this._sessionTableSchema = new SessionTableSchema();
            this.addBean(this._sessionTableSchema, true);
         }

         this._dbAdaptor.initialize();
         this._sessionTableSchema.setDatabaseAdaptor(this._dbAdaptor);
         this._sessionTableSchema.prepareTables();
      }

   }

   public SessionData doLoad(String id) throws Exception {
      Connection connection = this._dbAdaptor.getConnection();

      SessionData e;
      try {
         PreparedStatement statement = this._sessionTableSchema.getLoadStatement(connection, id, this._context);

         try {
            ResultSet result = statement.executeQuery();

            try {
               SessionData data = null;
               if (result.next()) {
                  data = this.newSessionData(id, result.getLong(this._sessionTableSchema.getCreateTimeColumn()), result.getLong(this._sessionTableSchema.getAccessTimeColumn()), result.getLong(this._sessionTableSchema.getLastAccessTimeColumn()), result.getLong(this._sessionTableSchema.getMaxIntervalColumn()));
                  data.setCookieSet(result.getLong(this._sessionTableSchema.getCookieTimeColumn()));
                  data.setLastNode(result.getString(this._sessionTableSchema.getLastNodeColumn()));
                  data.setLastSaved(result.getLong(this._sessionTableSchema.getLastSavedTimeColumn()));
                  data.setExpiry(result.getLong(this._sessionTableSchema.getExpiryTimeColumn()));
                  data.setContextPath(this._context.getCanonicalContextPath());
                  data.setVhost(this._context.getVhost());

                  try {
                     InputStream is = this._dbAdaptor.getBlobInputStream(result, this._sessionTableSchema.getMapColumn());

                     try {
                        ClassLoadingObjectInputStream ois = new ClassLoadingObjectInputStream(is);

                        try {
                           SessionData.deserializeAttributes(data, ois);
                        } catch (Throwable var15) {
                           try {
                              ois.close();
                           } catch (Throwable var14) {
                              var15.addSuppressed(var14);
                           }

                           throw var15;
                        }

                        ois.close();
                     } catch (Throwable var16) {
                        if (is != null) {
                           try {
                              is.close();
                           } catch (Throwable var13) {
                              var16.addSuppressed(var13);
                           }
                        }

                        throw var16;
                     }

                     if (is != null) {
                        is.close();
                     }
                  } catch (Exception e) {
                     throw new UnreadableSessionDataException(id, this._context, e);
                  }

                  if (LOG.isDebugEnabled()) {
                     LOG.debug("LOADED session {}", data);
                  }
               } else if (LOG.isDebugEnabled()) {
                  LOG.debug("No session {}", id);
               }

               e = data;
            } catch (Throwable var18) {
               if (result != null) {
                  try {
                     result.close();
                  } catch (Throwable var12) {
                     var18.addSuppressed(var12);
                  }
               }

               throw var18;
            }

            if (result != null) {
               result.close();
            }
         } catch (Throwable var19) {
            if (statement != null) {
               try {
                  statement.close();
               } catch (Throwable var11) {
                  var19.addSuppressed(var11);
               }
            }

            throw var19;
         }

         if (statement != null) {
            statement.close();
         }
      } catch (Throwable var20) {
         if (connection != null) {
            try {
               connection.close();
            } catch (Throwable var10) {
               var20.addSuppressed(var10);
            }
         }

         throw var20;
      }

      if (connection != null) {
         connection.close();
      }

      return e;
   }

   public boolean delete(String id) throws Exception {
      Connection connection = this._dbAdaptor.getConnection();

      boolean var5;
      try {
         PreparedStatement statement = this._sessionTableSchema.getDeleteStatement(connection, id, this._context);

         try {
            connection.setAutoCommit(true);
            int rows = statement.executeUpdate();
            if (LOG.isDebugEnabled()) {
               LOG.debug("Deleted Session {}:{}", id, rows > 0);
            }

            var5 = rows > 0;
         } catch (Throwable var8) {
            if (statement != null) {
               try {
                  statement.close();
               } catch (Throwable var7) {
                  var8.addSuppressed(var7);
               }
            }

            throw var8;
         }

         if (statement != null) {
            statement.close();
         }
      } catch (Throwable var9) {
         if (connection != null) {
            try {
               connection.close();
            } catch (Throwable var6) {
               var9.addSuppressed(var6);
            }
         }

         throw var9;
      }

      if (connection != null) {
         connection.close();
      }

      return var5;
   }

   public void doStore(String id, SessionData data, long lastSaveTime) throws Exception {
      if (data != null && id != null) {
         if (lastSaveTime <= 0L) {
            this.doInsert(id, data);
         } else {
            this.doUpdate(id, data);
         }

      }
   }

   protected void doInsert(String id, SessionData data) throws Exception {
      String s = this._sessionTableSchema.getInsertSessionStatementAsString();
      Connection connection = this._dbAdaptor.getConnection();

      try {
         connection.setAutoCommit(true);
         PreparedStatement statement = connection.prepareStatement(s);

         try {
            statement.setString(1, id);
            String cp = this._context.getCanonicalContextPath();
            if (this._dbAdaptor.isEmptyStringNull() && StringUtil.isBlank(cp)) {
               cp = "/";
            }

            statement.setString(2, cp);
            statement.setString(3, this._context.getVhost());
            statement.setString(4, data.getLastNode());
            statement.setLong(5, data.getAccessed());
            statement.setLong(6, data.getLastAccessed());
            statement.setLong(7, data.getCreated());
            statement.setLong(8, data.getCookieSet());
            statement.setLong(9, data.getLastSaved());
            statement.setLong(10, data.getExpiry());
            statement.setLong(11, data.getMaxInactiveMs());
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            try {
               ObjectOutputStream oos = new ObjectOutputStream(baos);

               try {
                  SessionData.serializeAttributes(data, oos);
                  byte[] bytes = baos.toByteArray();
                  ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
                  statement.setBinaryStream(12, bais, bytes.length);
               } catch (Throwable var15) {
                  try {
                     oos.close();
                  } catch (Throwable var14) {
                     var15.addSuppressed(var14);
                  }

                  throw var15;
               }

               oos.close();
            } catch (Throwable var16) {
               try {
                  baos.close();
               } catch (Throwable var13) {
                  var16.addSuppressed(var13);
               }

               throw var16;
            }

            baos.close();
            statement.executeUpdate();
            if (LOG.isDebugEnabled()) {
               LOG.debug("Inserted session {}", data);
            }
         } catch (Throwable var17) {
            if (statement != null) {
               try {
                  statement.close();
               } catch (Throwable var12) {
                  var17.addSuppressed(var12);
               }
            }

            throw var17;
         }

         if (statement != null) {
            statement.close();
         }
      } catch (Throwable var18) {
         if (connection != null) {
            try {
               connection.close();
            } catch (Throwable var11) {
               var18.addSuppressed(var11);
            }
         }

         throw var18;
      }

      if (connection != null) {
         connection.close();
      }

   }

   protected void doUpdate(String id, SessionData data) throws Exception {
      Connection connection = this._dbAdaptor.getConnection();

      try {
         connection.setAutoCommit(true);
         PreparedStatement statement = this._sessionTableSchema.getUpdateSessionStatement(connection, data.getId(), this._context);

         try {
            statement.setString(1, data.getLastNode());
            statement.setLong(2, data.getAccessed());
            statement.setLong(3, data.getLastAccessed());
            statement.setLong(4, data.getLastSaved());
            statement.setLong(5, data.getExpiry());
            statement.setLong(6, data.getMaxInactiveMs());
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            try {
               ObjectOutputStream oos = new ObjectOutputStream(baos);

               try {
                  SessionData.serializeAttributes(data, oos);
                  byte[] bytes = baos.toByteArray();
                  ByteArrayInputStream bais = new ByteArrayInputStream(bytes);

                  try {
                     statement.setBinaryStream(7, bais, bytes.length);
                  } catch (Throwable var16) {
                     try {
                        bais.close();
                     } catch (Throwable var15) {
                        var16.addSuppressed(var15);
                     }

                     throw var16;
                  }

                  bais.close();
               } catch (Throwable var17) {
                  try {
                     oos.close();
                  } catch (Throwable var14) {
                     var17.addSuppressed(var14);
                  }

                  throw var17;
               }

               oos.close();
            } catch (Throwable var18) {
               try {
                  baos.close();
               } catch (Throwable var13) {
                  var18.addSuppressed(var13);
               }

               throw var18;
            }

            baos.close();
            statement.executeUpdate();
            if (LOG.isDebugEnabled()) {
               LOG.debug("Updated session {}", data);
            }
         } catch (Throwable var19) {
            if (statement != null) {
               try {
                  statement.close();
               } catch (Throwable var12) {
                  var19.addSuppressed(var12);
               }
            }

            throw var19;
         }

         if (statement != null) {
            statement.close();
         }
      } catch (Throwable var20) {
         if (connection != null) {
            try {
               connection.close();
            } catch (Throwable var11) {
               var20.addSuppressed(var11);
            }
         }

         throw var20;
      }

      if (connection != null) {
         connection.close();
      }

   }

   public Set doCheckExpired(Set candidates, long time) {
      if (LOG.isDebugEnabled()) {
         LOG.debug("Getting expired sessions at time {}", time);
      }

      Set<String> expiredSessionKeys = new HashSet();

      try {
         Connection connection = this._dbAdaptor.getConnection();

         Object var30;
         try {
            connection.setAutoCommit(true);
            if (LOG.isDebugEnabled()) {
               LOG.debug("{} - Searching for sessions for context {} managed by me and expired before {}", new Object[]{this._context.getWorkerName(), this._context.getCanonicalContextPath(), time});
            }

            PreparedStatement statement = this._sessionTableSchema.getMyExpiredSessionsStatement(connection, this._context, time);

            try {
               ResultSet result = statement.executeQuery();

               try {
                  while(result.next()) {
                     String sessionId = result.getString(this._sessionTableSchema.getIdColumn());
                     long exp = result.getLong(this._sessionTableSchema.getExpiryTimeColumn());
                     expiredSessionKeys.add(sessionId);
                     if (LOG.isDebugEnabled()) {
                        LOG.debug("{} - Found expired sessionId={}, in context={}, expiry={}", new Object[]{this._context.getWorkerName(), sessionId, this._context.getCanonicalContextPath(), exp});
                     }
                  }
               } catch (Throwable var22) {
                  if (result != null) {
                     try {
                        result.close();
                     } catch (Throwable var18) {
                        var22.addSuppressed(var18);
                     }
                  }

                  throw var22;
               }

               if (result != null) {
                  result.close();
               }
            } catch (Throwable var23) {
               if (statement != null) {
                  try {
                     statement.close();
                  } catch (Throwable var17) {
                     var23.addSuppressed(var17);
                  }
               }

               throw var23;
            }

            if (statement != null) {
               statement.close();
            }

            Set<String> notExpiredInDB = new HashSet();

            for(String k : candidates) {
               if (!expiredSessionKeys.contains(k)) {
                  notExpiredInDB.add(k);
               }
            }

            if (!notExpiredInDB.isEmpty()) {
               PreparedStatement checkSessionExists = this._sessionTableSchema.getCheckSessionExistsStatement(connection, this._context);

               try {
                  for(String k : notExpiredInDB) {
                     checkSessionExists.setString(1, k);

                     try {
                        ResultSet result = checkSessionExists.executeQuery();

                        try {
                           if (!result.next()) {
                              expiredSessionKeys.add(k);
                           } else if (LOG.isDebugEnabled()) {
                              LOG.debug("{} Session {} expiry fresher in db than cache, another node must be managing it", this._context.getWorkerName(), k);
                           }
                        } catch (Throwable var20) {
                           if (result != null) {
                              try {
                                 result.close();
                              } catch (Throwable var19) {
                                 var20.addSuppressed(var19);
                              }
                           }

                           throw var20;
                        }

                        if (result != null) {
                           result.close();
                        }
                     } catch (Exception e) {
                        LOG.warn("{} Problem checking if potentially expired session {} exists in db", new Object[]{this._context.getWorkerName(), k, e});
                     }
                  }
               } catch (Throwable var24) {
                  if (checkSessionExists != null) {
                     try {
                        checkSessionExists.close();
                     } catch (Throwable var16) {
                        var24.addSuppressed(var16);
                     }
                  }

                  throw var24;
               }

               if (checkSessionExists != null) {
                  checkSessionExists.close();
               }
            }

            var30 = expiredSessionKeys;
         } catch (Throwable var25) {
            if (connection != null) {
               try {
                  connection.close();
               } catch (Throwable var15) {
                  var25.addSuppressed(var15);
               }
            }

            throw var25;
         }

         if (connection != null) {
            connection.close();
         }

         return (Set)var30;
      } catch (Exception e) {
         LOG.warn("Unable to get expired sessions", e);
         return expiredSessionKeys;
      }
   }

   public Set doGetExpired(long timeLimit) {
      Set<String> expired = new HashSet();

      try {
         Connection connection = this._dbAdaptor.getConnection();

         Object var17;
         try {
            connection.setAutoCommit(true);
            PreparedStatement selectExpiredSessions = this._sessionTableSchema.getExpiredSessionsStatement(connection, this._context.getCanonicalContextPath(), this._context.getVhost(), timeLimit);

            try {
               if (LOG.isDebugEnabled()) {
                  LOG.debug("{}- Searching for sessions for context {} expired before {}", new Object[]{this._context.getWorkerName(), this._context.getCanonicalContextPath(), timeLimit});
               }

               ResultSet result = selectExpiredSessions.executeQuery();

               try {
                  while(result.next()) {
                     String sessionId = result.getString(this._sessionTableSchema.getIdColumn());
                     long exp = result.getLong(this._sessionTableSchema.getExpiryTimeColumn());
                     expired.add(sessionId);
                     if (LOG.isDebugEnabled()) {
                        LOG.debug("{}- Found expired sessionId={} for context={} expiry={}", new Object[]{this._context.getWorkerName(), sessionId, this._context.getCanonicalContextPath(), exp});
                     }
                  }
               } catch (Throwable var13) {
                  if (result != null) {
                     try {
                        result.close();
                     } catch (Throwable var12) {
                        var13.addSuppressed(var12);
                     }
                  }

                  throw var13;
               }

               if (result != null) {
                  result.close();
               }
            } catch (Throwable var14) {
               if (selectExpiredSessions != null) {
                  try {
                     selectExpiredSessions.close();
                  } catch (Throwable var11) {
                     var14.addSuppressed(var11);
                  }
               }

               throw var14;
            }

            if (selectExpiredSessions != null) {
               selectExpiredSessions.close();
            }

            var17 = expired;
         } catch (Throwable var15) {
            if (connection != null) {
               try {
                  connection.close();
               } catch (Throwable var10) {
                  var15.addSuppressed(var10);
               }
            }

            throw var15;
         }

         if (connection != null) {
            connection.close();
         }

         return (Set)var17;
      } catch (Exception e) {
         LOG.warn("Error finding sessions expired before {}", timeLimit, e);
         return expired;
      }
   }

   public void doCleanOrphans(long time) {
      try {
         Connection connection = this._dbAdaptor.getConnection();

         try {
            PreparedStatement statement = this._sessionTableSchema.getCleanOrphansStatement(connection, time);

            try {
               connection.setAutoCommit(true);
               int rows = statement.executeUpdate();
               if (LOG.isDebugEnabled()) {
                  LOG.debug("Deleted {} orphaned sessions", rows);
               }
            } catch (Throwable var9) {
               if (statement != null) {
                  try {
                     statement.close();
                  } catch (Throwable var8) {
                     var9.addSuppressed(var8);
                  }
               }

               throw var9;
            }

            if (statement != null) {
               statement.close();
            }
         } catch (Throwable var10) {
            if (connection != null) {
               try {
                  connection.close();
               } catch (Throwable var7) {
                  var10.addSuppressed(var7);
               }
            }

            throw var10;
         }

         if (connection != null) {
            connection.close();
         }
      } catch (Exception e) {
         LOG.warn("Error cleaning orphan sessions", e);
      }

   }

   public void setDatabaseAdaptor(DatabaseAdaptor dbAdaptor) {
      this.checkStarted();
      this.updateBean(this._dbAdaptor, dbAdaptor);
      this._dbAdaptor = dbAdaptor;
   }

   public void setSessionTableSchema(SessionTableSchema schema) {
      this.checkStarted();
      this.updateBean(this._sessionTableSchema, schema);
      this._sessionTableSchema = schema;
      this._schemaProvided = true;
   }

   @ManagedAttribute(
      value = "does this store serialize sessions",
      readonly = true
   )
   public boolean isPassivating() {
      return true;
   }

   public boolean doExists(String id) throws Exception {
      Connection connection = this._dbAdaptor.getConnection();

      boolean var14;
      label129: {
         boolean var15;
         label130: {
            try {
               PreparedStatement checkSessionExists;
               label132: {
                  label133: {
                     connection.setAutoCommit(true);
                     checkSessionExists = this._sessionTableSchema.getCheckSessionExistsStatement(connection, this._context);

                     try {
                        label134: {
                           checkSessionExists.setString(1, id);
                           ResultSet result = checkSessionExists.executeQuery();

                           label112: {
                              label111: {
                                 try {
                                    if (!result.next()) {
                                       var14 = false;
                                       break label112;
                                    }

                                    long expiry = result.getLong(this._sessionTableSchema.getExpiryTimeColumn());
                                    if (expiry <= 0L) {
                                       var15 = true;
                                       break label111;
                                    }

                                    var15 = expiry > System.currentTimeMillis();
                                 } catch (Throwable var11) {
                                    if (result != null) {
                                       try {
                                          result.close();
                                       } catch (Throwable var10) {
                                          var11.addSuppressed(var10);
                                       }
                                    }

                                    throw var11;
                                 }

                                 if (result != null) {
                                    result.close();
                                 }
                                 break label132;
                              }

                              if (result != null) {
                                 result.close();
                              }
                              break label134;
                           }

                           if (result != null) {
                              result.close();
                           }
                           break label133;
                        }
                     } catch (Throwable var12) {
                        if (checkSessionExists != null) {
                           try {
                              checkSessionExists.close();
                           } catch (Throwable var9) {
                              var12.addSuppressed(var9);
                           }
                        }

                        throw var12;
                     }

                     if (checkSessionExists != null) {
                        checkSessionExists.close();
                     }
                     break label130;
                  }

                  if (checkSessionExists != null) {
                     checkSessionExists.close();
                  }
                  break label129;
               }

               if (checkSessionExists != null) {
                  checkSessionExists.close();
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

            return var15;
         }

         if (connection != null) {
            connection.close();
         }

         return var15;
      }

      if (connection != null) {
         connection.close();
      }

      return var14;
   }

   public static class SessionTableSchema {
      public static final int MAX_INTERVAL_NOT_SET = -999;
      public static final String INFERRED = "INFERRED";
      protected DatabaseAdaptor _dbAdaptor;
      protected String _schemaName = null;
      protected String _catalogName = null;
      protected String _tableName = "JettySessions";
      protected String _idColumn = "sessionId";
      protected String _contextPathColumn = "contextPath";
      protected String _virtualHostColumn = "virtualHost";
      protected String _lastNodeColumn = "lastNode";
      protected String _accessTimeColumn = "accessTime";
      protected String _lastAccessTimeColumn = "lastAccessTime";
      protected String _createTimeColumn = "createTime";
      protected String _cookieTimeColumn = "cookieTime";
      protected String _lastSavedTimeColumn = "lastSavedTime";
      protected String _expiryTimeColumn = "expiryTime";
      protected String _maxIntervalColumn = "maxInterval";
      protected String _mapColumn = "map";

      protected void setDatabaseAdaptor(DatabaseAdaptor dbadaptor) {
         this._dbAdaptor = dbadaptor;
      }

      public void setCatalogName(String catalogName) {
         if (catalogName != null && StringUtil.isBlank(catalogName)) {
            this._catalogName = null;
         } else {
            this._catalogName = catalogName;
         }

      }

      public String getCatalogName() {
         return this._catalogName;
      }

      public String getSchemaName() {
         return this._schemaName;
      }

      public void setSchemaName(String schemaName) {
         if (schemaName != null && StringUtil.isBlank(schemaName)) {
            this._schemaName = null;
         } else {
            this._schemaName = schemaName;
         }

      }

      public String getTableName() {
         return this._tableName;
      }

      public void setTableName(String tableName) {
         this.checkNotNull(tableName);
         this._tableName = tableName;
      }

      private String getSchemaTableName() {
         String var10000 = this.getSchemaName() != null ? this.getSchemaName() + "." : "";
         return var10000 + this.getTableName();
      }

      public String getIdColumn() {
         return this._idColumn;
      }

      public void setIdColumn(String idColumn) {
         this.checkNotNull(idColumn);
         this._idColumn = idColumn;
      }

      public String getContextPathColumn() {
         return this._contextPathColumn;
      }

      public void setContextPathColumn(String contextPathColumn) {
         this.checkNotNull(contextPathColumn);
         this._contextPathColumn = contextPathColumn;
      }

      public String getVirtualHostColumn() {
         return this._virtualHostColumn;
      }

      public void setVirtualHostColumn(String virtualHostColumn) {
         this.checkNotNull(virtualHostColumn);
         this._virtualHostColumn = virtualHostColumn;
      }

      public String getLastNodeColumn() {
         return this._lastNodeColumn;
      }

      public void setLastNodeColumn(String lastNodeColumn) {
         this.checkNotNull(lastNodeColumn);
         this._lastNodeColumn = lastNodeColumn;
      }

      public String getAccessTimeColumn() {
         return this._accessTimeColumn;
      }

      public void setAccessTimeColumn(String accessTimeColumn) {
         this.checkNotNull(accessTimeColumn);
         this._accessTimeColumn = accessTimeColumn;
      }

      public String getLastAccessTimeColumn() {
         return this._lastAccessTimeColumn;
      }

      public void setLastAccessTimeColumn(String lastAccessTimeColumn) {
         this.checkNotNull(lastAccessTimeColumn);
         this._lastAccessTimeColumn = lastAccessTimeColumn;
      }

      public String getCreateTimeColumn() {
         return this._createTimeColumn;
      }

      public void setCreateTimeColumn(String createTimeColumn) {
         this.checkNotNull(createTimeColumn);
         this._createTimeColumn = createTimeColumn;
      }

      public String getCookieTimeColumn() {
         return this._cookieTimeColumn;
      }

      public void setCookieTimeColumn(String cookieTimeColumn) {
         this.checkNotNull(cookieTimeColumn);
         this._cookieTimeColumn = cookieTimeColumn;
      }

      public String getLastSavedTimeColumn() {
         return this._lastSavedTimeColumn;
      }

      public void setLastSavedTimeColumn(String lastSavedTimeColumn) {
         this.checkNotNull(lastSavedTimeColumn);
         this._lastSavedTimeColumn = lastSavedTimeColumn;
      }

      public String getExpiryTimeColumn() {
         return this._expiryTimeColumn;
      }

      public void setExpiryTimeColumn(String expiryTimeColumn) {
         this.checkNotNull(expiryTimeColumn);
         this._expiryTimeColumn = expiryTimeColumn;
      }

      public String getMaxIntervalColumn() {
         return this._maxIntervalColumn;
      }

      public void setMaxIntervalColumn(String maxIntervalColumn) {
         this.checkNotNull(maxIntervalColumn);
         this._maxIntervalColumn = maxIntervalColumn;
      }

      public String getMapColumn() {
         return this._mapColumn;
      }

      public void setMapColumn(String mapColumn) {
         this.checkNotNull(mapColumn);
         this._mapColumn = mapColumn;
      }

      public String getCreateStatementAsString() {
         if (this._dbAdaptor == null) {
            throw new IllegalStateException("No DBAdaptor");
         } else {
            String blobType = this._dbAdaptor.getBlobType();
            String longType = this._dbAdaptor.getLongType();
            String stringType = this._dbAdaptor.getStringType();
            return "create table " + this.getSchemaTableName() + " (" + this._idColumn + " " + stringType + "(120), " + this._contextPathColumn + " " + stringType + "(60), " + this._virtualHostColumn + " " + stringType + "(60), " + this._lastNodeColumn + " " + stringType + "(60), " + this._accessTimeColumn + " " + longType + ", " + this._lastAccessTimeColumn + " " + longType + ", " + this._createTimeColumn + " " + longType + ", " + this._cookieTimeColumn + " " + longType + ", " + this._lastSavedTimeColumn + " " + longType + ", " + this._expiryTimeColumn + " " + longType + ", " + this._maxIntervalColumn + " " + longType + ", " + this._mapColumn + " " + blobType + ", primary key(" + this._idColumn + ", " + this._contextPathColumn + "," + this._virtualHostColumn + "))";
         }
      }

      public String getCreateIndexOverExpiryStatementAsString(String indexName) {
         return "create index " + indexName + " on " + this.getSchemaTableName() + " (" + this.getExpiryTimeColumn() + ")";
      }

      public String getCreateIndexOverSessionStatementAsString(String indexName) {
         return "create index " + indexName + " on " + this.getSchemaTableName() + " (" + this.getIdColumn() + ", " + this.getContextPathColumn() + ")";
      }

      public String getAlterTableForMaxIntervalAsString() {
         if (this._dbAdaptor == null) {
            throw new IllegalStateException("No DBAdaptor");
         } else {
            String longType = this._dbAdaptor.getLongType();
            String var10000 = this.getSchemaTableName();
            String stem = "alter table " + var10000 + " add " + this.getMaxIntervalColumn() + " " + longType;
            return this._dbAdaptor.getDBName().contains("oracle") ? stem + " default -999 not null" : stem + " not null default -999";
         }
      }

      private void checkNotNull(String s) {
         if (s == null) {
            throw new IllegalArgumentException(s);
         }
      }

      public String getInsertSessionStatementAsString() {
         String var10000 = this.getSchemaTableName();
         return "insert into " + var10000 + " (" + this.getIdColumn() + ", " + this.getContextPathColumn() + ", " + this.getVirtualHostColumn() + ", " + this.getLastNodeColumn() + ", " + this.getAccessTimeColumn() + ", " + this.getLastAccessTimeColumn() + ", " + this.getCreateTimeColumn() + ", " + this.getCookieTimeColumn() + ", " + this.getLastSavedTimeColumn() + ", " + this.getExpiryTimeColumn() + ", " + this.getMaxIntervalColumn() + ", " + this.getMapColumn() + ")  values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
      }

      public PreparedStatement getUpdateSessionStatement(Connection connection, String id, SessionContext context) throws SQLException {
         String var10000 = this.getSchemaTableName();
         String s = "update " + var10000 + " set " + this.getLastNodeColumn() + " = ?, " + this.getAccessTimeColumn() + " = ?, " + this.getLastAccessTimeColumn() + " = ?, " + this.getLastSavedTimeColumn() + " = ?, " + this.getExpiryTimeColumn() + " = ?, " + this.getMaxIntervalColumn() + " = ?, " + this.getMapColumn() + " = ? where " + this.getIdColumn() + " = ? and " + this.getContextPathColumn() + " = ? and " + this.getVirtualHostColumn() + " = ?";
         String cp = context.getCanonicalContextPath();
         if (this._dbAdaptor.isEmptyStringNull() && StringUtil.isBlank(cp)) {
            cp = "/";
         }

         PreparedStatement statement = connection.prepareStatement(s);
         statement.setString(8, id);
         statement.setString(9, cp);
         statement.setString(10, context.getVhost());
         return statement;
      }

      public PreparedStatement getExpiredSessionsStatement(Connection connection, String canonicalContextPath, String vhost, long expiry) throws SQLException {
         if (this._dbAdaptor == null) {
            throw new IllegalStateException("No DB adaptor");
         } else {
            String cp = canonicalContextPath;
            if (this._dbAdaptor.isEmptyStringNull() && StringUtil.isBlank(canonicalContextPath)) {
               cp = "/";
            }

            String var10001 = this.getIdColumn();
            PreparedStatement statement = connection.prepareStatement("select " + var10001 + ", " + this.getExpiryTimeColumn() + " from " + this.getSchemaTableName() + " where " + this.getContextPathColumn() + " = ? and " + this.getVirtualHostColumn() + " = ? and " + this.getExpiryTimeColumn() + " >0 and " + this.getExpiryTimeColumn() + " <= ?");
            statement.setString(1, cp);
            statement.setString(2, vhost);
            statement.setLong(3, expiry);
            return statement;
         }
      }

      public PreparedStatement getMyExpiredSessionsStatement(Connection connection, SessionContext sessionContext, long expiry) throws SQLException {
         if (this._dbAdaptor == null) {
            throw new IllegalStateException("No DB adaptor");
         } else {
            String cp = sessionContext.getCanonicalContextPath();
            if (this._dbAdaptor.isEmptyStringNull() && StringUtil.isBlank(cp)) {
               cp = "/";
            }

            String var10001 = this.getIdColumn();
            PreparedStatement statement = connection.prepareStatement("select " + var10001 + ", " + this.getExpiryTimeColumn() + " from " + this.getSchemaTableName() + " where " + this.getLastNodeColumn() + " = ? and " + this.getContextPathColumn() + " = ? and " + this.getVirtualHostColumn() + " = ? and " + this.getExpiryTimeColumn() + " >0 and " + this.getExpiryTimeColumn() + " <= ?");
            statement.setString(1, sessionContext.getWorkerName());
            statement.setString(2, cp);
            statement.setString(3, sessionContext.getVhost());
            statement.setLong(4, expiry);
            return statement;
         }
      }

      public PreparedStatement getCheckSessionExistsStatement(Connection connection, SessionContext context) throws SQLException {
         if (this._dbAdaptor == null) {
            throw new IllegalStateException("No DB adaptor");
         } else {
            String cp = context.getCanonicalContextPath();
            if (this._dbAdaptor.isEmptyStringNull() && StringUtil.isBlank(cp)) {
               cp = "/";
            }

            String var10001 = this.getIdColumn();
            PreparedStatement statement = connection.prepareStatement("select " + var10001 + ", " + this.getExpiryTimeColumn() + " from " + this.getSchemaTableName() + " where " + this.getIdColumn() + " = ? and " + this.getContextPathColumn() + " = ? and " + this.getVirtualHostColumn() + " = ?");
            statement.setString(2, cp);
            statement.setString(3, context.getVhost());
            return statement;
         }
      }

      public PreparedStatement getLoadStatement(Connection connection, String id, SessionContext contextId) throws SQLException {
         if (this._dbAdaptor == null) {
            throw new IllegalStateException("No DB adaptor");
         } else {
            String cp = contextId.getCanonicalContextPath();
            if (this._dbAdaptor.isEmptyStringNull() && StringUtil.isBlank(cp)) {
               cp = "/";
            }

            String var10001 = this.getSchemaTableName();
            PreparedStatement statement = connection.prepareStatement("select * from " + var10001 + " where " + this.getIdColumn() + " = ? and " + this.getContextPathColumn() + " = ? and " + this.getVirtualHostColumn() + " = ?");
            statement.setString(1, id);
            statement.setString(2, cp);
            statement.setString(3, contextId.getVhost());
            return statement;
         }
      }

      public PreparedStatement getUpdateStatement(Connection connection, String id, SessionContext contextId) throws SQLException {
         if (this._dbAdaptor == null) {
            throw new IllegalStateException("No DB adaptor");
         } else {
            String cp = contextId.getCanonicalContextPath();
            if (this._dbAdaptor.isEmptyStringNull() && StringUtil.isBlank(cp)) {
               cp = "/";
            }

            String var10000 = this.getSchemaTableName();
            String s = "update " + var10000 + " set " + this.getLastNodeColumn() + " = ?, " + this.getAccessTimeColumn() + " = ?, " + this.getLastAccessTimeColumn() + " = ?, " + this.getLastSavedTimeColumn() + " = ?, " + this.getExpiryTimeColumn() + " = ?, " + this.getMaxIntervalColumn() + " = ?, " + this.getMapColumn() + " = ? where " + this.getIdColumn() + " = ? and " + this.getContextPathColumn() + " = ? and " + this.getVirtualHostColumn() + " = ?";
            PreparedStatement statement = connection.prepareStatement(s);
            statement.setString(8, id);
            statement.setString(9, cp);
            statement.setString(10, contextId.getVhost());
            return statement;
         }
      }

      public PreparedStatement getDeleteStatement(Connection connection, String id, SessionContext contextId) throws Exception {
         if (this._dbAdaptor == null) {
            throw new IllegalStateException("No DB adaptor");
         } else {
            String cp = contextId.getCanonicalContextPath();
            if (this._dbAdaptor.isEmptyStringNull() && StringUtil.isBlank(cp)) {
               cp = "/";
            }

            String var10001 = this.getSchemaTableName();
            PreparedStatement statement = connection.prepareStatement("delete from " + var10001 + " where " + this.getIdColumn() + " = ? and " + this.getContextPathColumn() + " = ? and " + this.getVirtualHostColumn() + " = ?");
            statement.setString(1, id);
            statement.setString(2, cp);
            statement.setString(3, contextId.getVhost());
            return statement;
         }
      }

      public PreparedStatement getCleanOrphansStatement(Connection connection, long timeLimit) throws Exception {
         if (this._dbAdaptor == null) {
            throw new IllegalStateException("No DB adaptor");
         } else {
            String var10001 = this.getSchemaTableName();
            PreparedStatement statement = connection.prepareStatement("delete from " + var10001 + " where " + this.getExpiryTimeColumn() + " > 0 and " + this.getExpiryTimeColumn() + " <= ?");
            statement.setLong(1, timeLimit);
            return statement;
         }
      }

      public void prepareTables() throws SQLException {
         Connection connection = this._dbAdaptor.getConnection();

         try {
            Statement statement = connection.createStatement();

            try {
               connection.setAutoCommit(true);
               DatabaseMetaData metaData = connection.getMetaData();
               this._dbAdaptor.adaptTo(metaData);
               String tableName = this._dbAdaptor.convertIdentifier(this.getTableName());
               String schemaName = this._dbAdaptor.convertIdentifier(this.getSchemaName());
               if ("INFERRED".equalsIgnoreCase(schemaName)) {
                  schemaName = connection.getSchema();
                  this.setSchemaName(schemaName);
               }

               String catalogName = this._dbAdaptor.convertIdentifier(this.getCatalogName());
               if ("INFERRED".equalsIgnoreCase(catalogName)) {
                  catalogName = connection.getCatalog();
                  this.setCatalogName(catalogName);
               }

               ResultSet result = metaData.getTables(catalogName, schemaName, tableName, (String[])null);

               try {
                  if (!result.next()) {
                     if (JDBCSessionDataStore.LOG.isDebugEnabled()) {
                        JDBCSessionDataStore.LOG.debug("Creating table {} schema={} catalog={}", new Object[]{tableName, schemaName, catalogName});
                     }

                     statement.executeUpdate(this.getCreateStatementAsString());
                  } else {
                     if (JDBCSessionDataStore.LOG.isDebugEnabled()) {
                        JDBCSessionDataStore.LOG.debug("Not creating table {} schema={} catalog={}", new Object[]{tableName, schemaName, catalogName});
                     }

                     ResultSet colResult = null;

                     try {
                        colResult = metaData.getColumns(catalogName, schemaName, tableName, this._dbAdaptor.convertIdentifier(this.getMaxIntervalColumn()));
                     } catch (SQLException sqlEx) {
                        JDBCSessionDataStore.LOG.warn("Problem checking if {} table contains {} column. Ensure table contains column with definition: long not null default -999", this.getTableName(), this.getMaxIntervalColumn());
                        throw sqlEx;
                     }

                     try {
                        if (!colResult.next()) {
                           try {
                              statement.executeUpdate(this.getAlterTableForMaxIntervalAsString());
                           } catch (SQLException sqlEx) {
                              JDBCSessionDataStore.LOG.warn("Problem adding {} column. Ensure table contains column definition: long not null default -999", this.getMaxIntervalColumn());
                              throw sqlEx;
                           }
                        }
                     } finally {
                        colResult.close();
                     }
                  }
               } catch (Throwable var32) {
                  if (result != null) {
                     try {
                        result.close();
                     } catch (Throwable var28) {
                        var32.addSuppressed(var28);
                     }
                  }

                  throw var32;
               }

               if (result != null) {
                  result.close();
               }

               String index1 = "idx_" + this.getTableName() + "_expiry";
               String index2 = "idx_" + this.getTableName() + "_session";
               boolean index1Exists = false;
               boolean index2Exists = false;
               ResultSet result = metaData.getIndexInfo(catalogName, schemaName, tableName, false, true);

               try {
                  while(result.next()) {
                     String idxName = result.getString("INDEX_NAME");
                     if (index1.equalsIgnoreCase(idxName)) {
                        index1Exists = true;
                     } else if (index2.equalsIgnoreCase(idxName)) {
                        index2Exists = true;
                     }
                  }
               } catch (Throwable var33) {
                  if (result != null) {
                     try {
                        result.close();
                     } catch (Throwable var27) {
                        var33.addSuppressed(var27);
                     }
                  }

                  throw var33;
               }

               if (result != null) {
                  result.close();
               }

               if (!index1Exists) {
                  statement.executeUpdate(this.getCreateIndexOverExpiryStatementAsString(index1));
               }

               if (!index2Exists) {
                  statement.executeUpdate(this.getCreateIndexOverSessionStatementAsString(index2));
               }
            } catch (Throwable var34) {
               if (statement != null) {
                  try {
                     statement.close();
                  } catch (Throwable var26) {
                     var34.addSuppressed(var26);
                  }
               }

               throw var34;
            }

            if (statement != null) {
               statement.close();
            }
         } catch (Throwable var35) {
            if (connection != null) {
               try {
                  connection.close();
               } catch (Throwable var25) {
                  var35.addSuppressed(var25);
               }
            }

            throw var35;
         }

         if (connection != null) {
            connection.close();
         }

      }

      public String toString() {
         return String.format("%s[%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s]", super.toString(), this._catalogName, this._schemaName, this._tableName, this._idColumn, this._contextPathColumn, this._virtualHostColumn, this._cookieTimeColumn, this._createTimeColumn, this._expiryTimeColumn, this._accessTimeColumn, this._lastAccessTimeColumn, this._lastNodeColumn, this._lastSavedTimeColumn, this._maxIntervalColumn);
      }
   }
}
