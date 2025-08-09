package org.datanucleus.store.rdbms.adapter;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.exceptions.ClassNotResolvedException;
import org.datanucleus.exceptions.NucleusDataStoreException;
import org.datanucleus.plugin.ConfigurationElement;
import org.datanucleus.plugin.PluginManager;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;

public class DatastoreAdapterFactory {
   public static DatastoreAdapterFactory getInstance() {
      return new DatastoreAdapterFactory();
   }

   protected DatastoreAdapterFactory() {
   }

   public DatastoreAdapter getDatastoreAdapter(ClassLoaderResolver clr, Connection conn, String adapterClassName, PluginManager pluginMgr) throws SQLException {
      DatastoreAdapter adapter = null;
      DatabaseMetaData metadata = conn.getMetaData();
      adapter = this.getNewDatastoreAdapter(clr, metadata, adapterClassName, pluginMgr);
      if (adapter == null) {
         NucleusLogger.DATASTORE.warn(Localiser.msg("051000"));
         adapter = new BaseDatastoreAdapter(metadata);
      }

      return adapter;
   }

   protected DatastoreAdapter getNewDatastoreAdapter(ClassLoaderResolver clr, DatabaseMetaData metadata, String adapterClassName, PluginManager pluginMgr) {
      if (metadata == null) {
         return null;
      } else {
         String productName = null;
         if (adapterClassName == null) {
            try {
               productName = metadata.getDatabaseProductName();
               if (productName == null) {
                  NucleusLogger.DATASTORE.error(Localiser.msg("051024"));
                  return null;
               }
            } catch (SQLException sqe) {
               NucleusLogger.DATASTORE.error(Localiser.msg("051025", new Object[]{sqe}));
               return null;
            }
         }

         Object adapter_obj;
         try {
            Class adapterClass = this.getAdapterClass(pluginMgr, adapterClassName, productName, clr);
            if (adapterClass == null) {
               return null;
            }

            Object[] ctr_args = new Object[]{metadata};
            Class[] ctr_args_classes = new Class[]{DatabaseMetaData.class};
            Constructor ctr = adapterClass.getConstructor(ctr_args_classes);

            try {
               adapter_obj = ctr.newInstance(ctr_args);
            } catch (InvocationTargetException ite) {
               if (ite.getTargetException() != null && ite.getTargetException() instanceof NucleusDataStoreException) {
                  throw (NucleusDataStoreException)ite.getTargetException();
               }

               return null;
            } catch (Exception e) {
               NucleusLogger.DATASTORE.error(Localiser.msg("051026", new Object[]{adapterClassName, e}));
               return null;
            }
         } catch (ClassNotResolvedException ex) {
            NucleusLogger.DATASTORE.error(Localiser.msg("051026", new Object[]{adapterClassName, ex}));
            return null;
         } catch (NoSuchMethodException nsme) {
            NucleusLogger.DATASTORE.error(Localiser.msg("051026", new Object[]{adapterClassName, nsme}));
            return null;
         }

         return (DatastoreAdapter)adapter_obj;
      }
   }

   protected Class getAdapterClass(PluginManager pluginMgr, String adapterClassName, String productName, ClassLoaderResolver clr) {
      ConfigurationElement[] elems = pluginMgr.getConfigurationElementsForExtension("org.datanucleus.store.rdbms.datastoreadapter", (String)null, (String)null);
      if (elems != null) {
         for(int i = 0; i < elems.length; ++i) {
            if (adapterClassName != null) {
               if (elems[i].getAttribute("class-name").equals(adapterClassName)) {
                  return pluginMgr.loadClass(elems[i].getExtension().getPlugin().getSymbolicName(), elems[i].getAttribute("class-name"));
               }
            } else {
               String vendorId = elems[i].getAttribute("vendor-id");
               if (productName.toLowerCase().indexOf(vendorId.toLowerCase()) >= 0) {
                  return pluginMgr.loadClass(elems[i].getExtension().getPlugin().getSymbolicName(), elems[i].getAttribute("class-name"));
               }
            }
         }
      }

      return adapterClassName != null ? clr.classForName(adapterClassName, false) : null;
   }
}
