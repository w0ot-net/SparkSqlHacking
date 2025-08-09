package org.datanucleus;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Map;
import java.util.Random;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.exceptions.TransactionIsolationNotSupportedException;
import org.datanucleus.plugin.ConfigurationElement;
import org.datanucleus.plugin.Extension;
import org.datanucleus.store.StoreManager;
import org.datanucleus.util.Localiser;

public class NucleusContextHelper {
   public static final Random random = new Random();

   public static String getTransactionIsolationForStoreManager(StoreManager storeMgr, String transactionIsolation) {
      if (transactionIsolation != null) {
         Collection<String> supportedOptions = storeMgr.getSupportedOptions();
         if (!supportedOptions.contains("TransactionIsolationLevel." + transactionIsolation)) {
            if (transactionIsolation.equals("read-uncommitted")) {
               if (supportedOptions.contains("TransactionIsolationLevel.read-committed")) {
                  return "read-committed";
               }

               if (supportedOptions.contains("TransactionIsolationLevel.repeatable-read")) {
                  return "repeatable-read";
               }

               if (supportedOptions.contains("TransactionIsolationLevel.serializable")) {
                  return "serializable";
               }
            } else if (transactionIsolation.equals("read-committed")) {
               if (supportedOptions.contains("TransactionIsolationLevel.repeatable-read")) {
                  return "repeatable-read";
               }

               if (supportedOptions.contains("TransactionIsolationLevel.serializable")) {
                  return "serializable";
               }
            } else {
               if (!transactionIsolation.equals("repeatable-read")) {
                  throw new TransactionIsolationNotSupportedException(transactionIsolation);
               }

               if (supportedOptions.contains("TransactionIsolationLevel.serializable")) {
                  return "serializable";
               }
            }
         }
      }

      return transactionIsolation;
   }

   public static StoreManager createStoreManagerForProperties(Map props, Map datastoreProps, ClassLoaderResolver clr, NucleusContext nucCtx) {
      Extension[] exts = nucCtx.getPluginManager().getExtensionPoint("org.datanucleus.store_manager").getExtensions();
      Class[] ctrArgTypes = new Class[]{ClassConstants.CLASS_LOADER_RESOLVER, ClassConstants.PERSISTENCE_NUCLEUS_CONTEXT, Map.class};
      Object[] ctrArgs = new Object[]{clr, nucCtx, datastoreProps};
      StoreManager storeMgr = null;
      String storeManagerType = (String)props.get("datanucleus.storeManagerType".toLowerCase());
      if (storeManagerType != null) {
         for(int e = 0; storeMgr == null && e < exts.length; ++e) {
            ConfigurationElement[] confElm = exts[e].getConfigurationElements();

            for(int c = 0; storeMgr == null && c < confElm.length; ++c) {
               String key = confElm[c].getAttribute("key");
               if (key.equalsIgnoreCase(storeManagerType)) {
                  try {
                     storeMgr = (StoreManager)nucCtx.getPluginManager().createExecutableExtension("org.datanucleus.store_manager", "key", storeManagerType, "class-name", ctrArgTypes, ctrArgs);
                  } catch (InvocationTargetException ex) {
                     Throwable t = ex.getTargetException();
                     if (t instanceof RuntimeException) {
                        throw (RuntimeException)t;
                     }

                     if (t instanceof Error) {
                        throw (Error)t;
                     }

                     throw (new NucleusException(t.getMessage(), t)).setFatal();
                  } catch (Exception ex) {
                     throw (new NucleusException(ex.getMessage(), ex)).setFatal();
                  }
               }
            }
         }

         if (storeMgr == null) {
            throw (new NucleusUserException(Localiser.msg("008004", storeManagerType))).setFatal();
         }
      }

      if (storeMgr == null) {
         String url = (String)props.get("datanucleus.ConnectionURL".toLowerCase());
         if (url != null) {
            int idx = url.indexOf(58);
            if (idx > -1) {
               url = url.substring(0, idx);
            }
         }

         for(int e = 0; storeMgr == null && e < exts.length; ++e) {
            ConfigurationElement[] confElm = exts[e].getConfigurationElements();

            for(int c = 0; storeMgr == null && c < confElm.length; ++c) {
               String urlKey = confElm[c].getAttribute("url-key");
               if (url == null || urlKey.equalsIgnoreCase(url)) {
                  try {
                     storeMgr = (StoreManager)nucCtx.getPluginManager().createExecutableExtension("org.datanucleus.store_manager", "url-key", url == null ? urlKey : url, "class-name", ctrArgTypes, ctrArgs);
                  } catch (InvocationTargetException ex) {
                     Throwable t = ex.getTargetException();
                     if (t instanceof RuntimeException) {
                        throw (RuntimeException)t;
                     }

                     if (t instanceof Error) {
                        throw (Error)t;
                     }

                     throw (new NucleusException(t.getMessage(), t)).setFatal();
                  } catch (Exception ex) {
                     throw (new NucleusException(ex.getMessage(), ex)).setFatal();
                  }
               }
            }
         }

         if (storeMgr == null) {
            throw (new NucleusUserException(Localiser.msg("008004", url))).setFatal();
         }
      }

      return storeMgr;
   }
}
