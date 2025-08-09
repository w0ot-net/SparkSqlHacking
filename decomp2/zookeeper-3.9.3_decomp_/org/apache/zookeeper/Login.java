package org.apache.zookeeper;

import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;
import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.kerberos.KerberosPrincipal;
import javax.security.auth.kerberos.KerberosTicket;
import javax.security.auth.login.AppConfigurationEntry;
import javax.security.auth.login.Configuration;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import org.apache.zookeeper.client.ZKClientConfig;
import org.apache.zookeeper.common.Time;
import org.apache.zookeeper.common.ZKConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Login {
   private static final String KINIT_COMMAND_DEFAULT = "/usr/bin/kinit";
   private static final Logger LOG = LoggerFactory.getLogger(Login.class);
   public static final String SYSTEM_USER = System.getProperty("user.name", "<NA>");
   private final Supplier callbackHandlerSupplier;
   private static final float TICKET_RENEW_WINDOW = 0.8F;
   private static final float TICKET_RENEW_JITTER = 0.05F;
   private static final long DEFAULT_MIN_TIME_BEFORE_RELOGIN = 60000L;
   public static final String MIN_TIME_BEFORE_RELOGIN_CONFIG_KEY = "zookeeper.kerberos.minReLoginTimeMs";
   private static final long MIN_TIME_BEFORE_RELOGIN = Long.getLong("zookeeper.kerberos.minReLoginTimeMs", 60000L);
   private Subject subject = null;
   private Thread t = null;
   private boolean isKrbTicket = false;
   private boolean isUsingTicketCache = false;
   private LoginContext login = null;
   private String loginContextName = null;
   private String principal = null;
   private long lastLogin;
   private final ZKConfig zkConfig;

   public Login(String loginContextName, Supplier callbackHandlerSupplier, final ZKConfig zkConfig) throws LoginException {
      this.lastLogin = Time.currentElapsedTime() - MIN_TIME_BEFORE_RELOGIN;
      this.zkConfig = zkConfig;
      this.callbackHandlerSupplier = callbackHandlerSupplier;
      this.login = this.login(loginContextName);
      this.loginContextName = loginContextName;
      this.subject = this.login.getSubject();
      this.isKrbTicket = !this.subject.getPrivateCredentials(KerberosTicket.class).isEmpty();
      AppConfigurationEntry[] entries = Configuration.getConfiguration().getAppConfigurationEntry(loginContextName);
      int var6 = entries.length;
      byte var7 = 0;
      if (var7 < var6) {
         AppConfigurationEntry entry = entries[var7];
         if (entry.getOptions().get("useTicketCache") != null) {
            String val = (String)entry.getOptions().get("useTicketCache");
            if (val.equals("true")) {
               this.isUsingTicketCache = true;
            }
         }

         if (entry.getOptions().get("principal") != null) {
            this.principal = (String)entry.getOptions().get("principal");
         }
      }

      if (this.isKrbTicket) {
         this.t = new Thread(new Runnable() {
            public void run() {
               Login.LOG.info("TGT refresh thread started.");

               while(true) {
                  KerberosTicket tgt = Login.this.getTGT();
                  long now = Time.currentWallTime();
                  Date nextRefreshDate;
                  long nextRefresh;
                  if (tgt == null) {
                     nextRefresh = now + Login.MIN_TIME_BEFORE_RELOGIN;
                     nextRefreshDate = new Date(nextRefresh);
                     Login.LOG.warn("No TGT found: will try again at {}", nextRefreshDate);
                  } else {
                     nextRefresh = Login.this.getRefreshTime(tgt);
                     long expiry = tgt.getEndTime().getTime();
                     Date expiryDate = new Date(expiry);
                     if (Login.this.isUsingTicketCache && tgt.getEndTime().equals(tgt.getRenewTill())) {
                        Login.LOG.error("The TGT cannot be renewed beyond the next expiry date: {}.This process will not be able to authenticate new SASL connections after that time (for example, it will not be authenticate a new connection with a Zookeeper Quorum member).  Ask your system administrator to either increase the 'renew until' time by doing : 'modprinc -maxrenewlife {}' within kadmin, or instead, to generate a keytab for {}. Because the TGT's expiry cannot be further extended by refreshing, exiting refresh thread now.", new Object[]{expiryDate, Login.this.principal, Login.this.principal});
                        return;
                     }

                     if (nextRefresh <= expiry && now + Login.MIN_TIME_BEFORE_RELOGIN <= expiry) {
                        if (nextRefresh < now + Login.MIN_TIME_BEFORE_RELOGIN) {
                           Date until = new Date(nextRefresh);
                           Date newuntil = new Date(now + Login.MIN_TIME_BEFORE_RELOGIN);
                           Login.LOG.warn("TGT refresh thread time adjusted from : {} to : {} since the former is sooner than the minimum refresh interval ({} seconds) from now.", new Object[]{until, newuntil, Login.MIN_TIME_BEFORE_RELOGIN / 1000L});
                        }

                        nextRefresh = Math.max(nextRefresh, now + Login.MIN_TIME_BEFORE_RELOGIN);
                     } else {
                        nextRefresh = now;
                     }

                     nextRefreshDate = new Date(nextRefresh);
                     if (nextRefresh > expiry) {
                        Login.LOG.error("next refresh: {} is later than expiry {}. This may indicate a clock skew problem. Check that this host and the KDC's hosts' clocks are in sync. Exiting refresh thread.", nextRefreshDate, expiryDate);
                        return;
                     }
                  }

                  if (now == nextRefresh) {
                     Login.LOG.info("refreshing now because expiry is before next scheduled refresh time.");
                  } else {
                     if (now >= nextRefresh) {
                        Login.LOG.error("nextRefresh:{} is in the past: exiting refresh thread. Check clock sync between this host and KDC - (KDC's clock is likely ahead of this host). Manual intervention will be required for this client to successfully authenticate. Exiting refresh thread.", nextRefreshDate);
                        break;
                     }

                     Date until = new Date(nextRefresh);
                     Login.LOG.info("TGT refresh sleeping until: {}", until.toString());

                     try {
                        Thread.sleep(nextRefresh - now);
                     } catch (InterruptedException var14) {
                        Login.LOG.warn("TGT renewal thread has been interrupted and will exit.");
                        break;
                     }
                  }

                  if (Login.this.isUsingTicketCache) {
                     String cmd = zkConfig.getProperty("zookeeper.kinit", "/usr/bin/kinit");
                     String kinitArgs = "-R";
                     int retry = 1;

                     while(retry >= 0) {
                        try {
                           Login.LOG.debug("running ticket cache refresh command: {} {}", cmd, kinitArgs);
                           Shell.execCommand(cmd, kinitArgs);
                           break;
                        } catch (Exception e) {
                           if (retry <= 0) {
                              Login.LOG.warn("Could not renew TGT due to problem running shell command: '{} {}'. Exiting refresh thread.", new Object[]{cmd, kinitArgs, e});
                              return;
                           }

                           --retry;

                           try {
                              Login.this.sleepBeforeRetryFailedRefresh();
                           } catch (InterruptedException var13) {
                              Login.LOG.error("Interrupted while renewing TGT, exiting Login thread");
                              return;
                           }
                        }
                     }
                  }

                  try {
                     int retry = 1;

                     while(retry >= 0) {
                        try {
                           Login.this.reLogin();
                           break;
                        } catch (LoginException le) {
                           if (retry > 0) {
                              --retry;

                              try {
                                 Login.this.sleepBeforeRetryFailedRefresh();
                              } catch (InterruptedException var12) {
                                 Login.LOG.error("Interrupted during login retry after LoginException:", le);
                                 throw le;
                              }
                           } else {
                              Login.LOG.error("Could not refresh TGT for principal: {}.", Login.this.principal, le);
                           }
                        }
                     }
                  } catch (LoginException le) {
                     Login.LOG.error("Failed to refresh TGT: refresh thread exiting now.", le);
                     break;
                  }
               }

            }
         });
         this.t.setDaemon(true);
      }
   }

   public CallbackHandler newCallbackHandler() {
      return (CallbackHandler)this.callbackHandlerSupplier.get();
   }

   public void startThreadIfNeeded() {
      if (this.t != null) {
         this.t.start();
      }

   }

   public void shutdown() {
      if (this.t != null && this.t.isAlive()) {
         this.t.interrupt();

         try {
            this.t.join();
         } catch (InterruptedException e) {
            LOG.warn("error while waiting for Login thread to shutdown.", e);
         }
      }

   }

   public Subject getSubject() {
      return this.subject;
   }

   public String getUserName() {
      return this.principal != null && !this.principal.isEmpty() ? this.principal : SYSTEM_USER;
   }

   public String getLoginContextName() {
      return this.loginContextName;
   }

   private synchronized LoginContext login(String loginContextName) throws LoginException {
      if (loginContextName == null) {
         throw new LoginException("loginContext name (JAAS file section header) was null. Please check your java.security.login.auth.config (=" + System.getProperty("java.security.login.auth.config") + ") and your " + this.getLoginContextMessage());
      } else {
         LoginContext loginContext = new LoginContext(loginContextName, this.newCallbackHandler());
         loginContext.login();
         LOG.info("{} successfully logged in.", loginContextName);
         return loginContext;
      }
   }

   private String getLoginContextMessage() {
      return this.zkConfig instanceof ZKClientConfig ? "zookeeper.sasl.clientconfig(=" + this.zkConfig.getProperty("zookeeper.sasl.clientconfig", "Client") + ")" : "zookeeper.sasl.serverconfig(=" + System.getProperty("zookeeper.sasl.serverconfig", "Server") + ")";
   }

   private long getRefreshTime(KerberosTicket tgt) {
      long start = tgt.getStartTime().getTime();
      long expires = tgt.getEndTime().getTime();
      LOG.info("TGT valid starting at:        {}", tgt.getStartTime().toString());
      LOG.info("TGT expires:                  {}", tgt.getEndTime().toString());
      long proposedRefresh = start + (long)((double)(expires - start) * ((double)0.8F + (double)0.05F * ThreadLocalRandom.current().nextDouble()));
      return proposedRefresh > expires ? Time.currentWallTime() : proposedRefresh;
   }

   private synchronized KerberosTicket getTGT() {
      for(KerberosTicket ticket : this.subject.getPrivateCredentials(KerberosTicket.class)) {
         KerberosPrincipal server = ticket.getServer();
         if (server.getName().equals("krbtgt/" + server.getRealm() + "@" + server.getRealm())) {
            LOG.debug("Client principal is \"{}\".", ticket.getClient().getName());
            LOG.debug("Server principal is \"{}\".", ticket.getServer().getName());
            return ticket;
         }
      }

      return null;
   }

   private boolean hasSufficientTimeElapsed() {
      long now = Time.currentElapsedTime();
      if (now - this.getLastLogin() < MIN_TIME_BEFORE_RELOGIN) {
         LOG.warn("Not attempting to re-login since the last re-login was attempted less than {} seconds before.", MIN_TIME_BEFORE_RELOGIN / 1000L);
         return false;
      } else {
         this.setLastLogin(now);
         return true;
      }
   }

   private LoginContext getLogin() {
      return this.login;
   }

   private void setLogin(LoginContext login) {
      this.login = login;
   }

   private void setLastLogin(long time) {
      this.lastLogin = time;
   }

   public long getLastLogin() {
      return this.lastLogin;
   }

   private synchronized void reLogin() throws LoginException {
      if (this.isKrbTicket) {
         LoginContext login = this.getLogin();
         if (login == null) {
            throw new LoginException("login must be done first");
         } else if (this.hasSufficientTimeElapsed()) {
            LOG.info("Initiating logout for {}", this.principal);
            synchronized(Login.class) {
               this.logout();
               login = new LoginContext(this.loginContextName, this.getSubject());
               LOG.info("Initiating re-login for {}", this.principal);
               login.login();
               this.setLogin(login);
            }
         }
      }
   }

   protected synchronized void logout() throws LoginException {
      if (this.subject != null && !this.subject.getPrincipals().isEmpty()) {
         this.login.logout();
      }

   }

   protected void sleepBeforeRetryFailedRefresh() throws InterruptedException {
      Thread.sleep(10000L);
   }
}
