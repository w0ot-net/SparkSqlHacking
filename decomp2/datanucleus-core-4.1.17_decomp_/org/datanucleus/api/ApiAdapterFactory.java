package org.datanucleus.api;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.plugin.PluginManager;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;

public class ApiAdapterFactory {
   Map adapters = new HashMap();
   static ApiAdapterFactory adapterFactory = new ApiAdapterFactory();

   public static ApiAdapterFactory getInstance() {
      return adapterFactory;
   }

   protected ApiAdapterFactory() {
   }

   private void addAdapter(String name, ApiAdapter apiAdapter) {
      if (name != null && apiAdapter != null) {
         this.adapters.put(name, apiAdapter);
      }
   }

   public ApiAdapter getApiAdapter(String name, PluginManager pluginMgr) {
      ApiAdapter api = (ApiAdapter)this.adapters.get(name);
      if (api == null) {
         try {
            api = (ApiAdapter)pluginMgr.createExecutableExtension("org.datanucleus.api_adapter", (String)"name", (String)name, "class-name", (Class[])null, (Object[])null);
            if (api == null) {
               String msg = Localiser.msg("022001", name);
               NucleusLogger.PERSISTENCE.error(msg);
               throw new NucleusUserException(msg);
            }

            adapterFactory.addAdapter(name, api);
         } catch (Error err) {
            String className = pluginMgr.getAttributeValueForExtension("org.datanucleus.api_adapter", "name", name, "class-name");
            String msg = Localiser.msg("022000", className, err.getMessage());
            NucleusLogger.PERSISTENCE.error(msg, err);
            throw new NucleusUserException(msg);
         } catch (InvocationTargetException e) {
            String className = pluginMgr.getAttributeValueForExtension("org.datanucleus.api_adapter", "name", name, "class-name");
            String msg = Localiser.msg("022000", className, e.getTargetException());
            NucleusLogger.PERSISTENCE.error(msg, e);
            throw new NucleusUserException(msg);
         } catch (NucleusUserException nue) {
            throw nue;
         } catch (Exception e) {
            String className = pluginMgr.getAttributeValueForExtension("org.datanucleus.api_adapter", "name", name, "class-name");
            String msg = Localiser.msg("022000", className, e.getMessage());
            NucleusLogger.PERSISTENCE.error(msg, e);
            throw new NucleusUserException(msg);
         }
      }

      return api;
   }
}
