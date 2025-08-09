package org.apache.hadoop.hive.thrift;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;
import org.apache.hadoop.conf.Configurable;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.security.SaslRpcServer;

public class HadoopThriftAuthBridge23 extends HadoopThriftAuthBridge {
   private static Field SASL_PROPS_FIELD = null;
   private static Class SASL_PROPERTIES_RESOLVER_CLASS = null;
   private static Method RES_GET_INSTANCE_METHOD;
   private static Method GET_DEFAULT_PROP_METHOD;

   public Map getHadoopSaslProperties(Configuration conf) {
      if (SASL_PROPS_FIELD != null) {
         SaslRpcServer.init(conf);

         try {
            return (Map)SASL_PROPS_FIELD.get((Object)null);
         } catch (Exception e) {
            throw new IllegalStateException("Error finding hadoop SASL properties", e);
         }
      } else {
         try {
            Configurable saslPropertiesResolver = (Configurable)RES_GET_INSTANCE_METHOD.invoke((Object)null, conf);
            saslPropertiesResolver.setConf(conf);
            return (Map)GET_DEFAULT_PROP_METHOD.invoke(saslPropertiesResolver);
         } catch (Exception e) {
            throw new IllegalStateException("Error finding hadoop SASL properties", e);
         }
      }
   }

   static {
      String SASL_PROP_RES_CLASSNAME = "org.apache.hadoop.security.SaslPropertiesResolver";

      try {
         SASL_PROPERTIES_RESOLVER_CLASS = Class.forName("org.apache.hadoop.security.SaslPropertiesResolver");
      } catch (ClassNotFoundException var4) {
      }

      if (SASL_PROPERTIES_RESOLVER_CLASS != null) {
         try {
            RES_GET_INSTANCE_METHOD = SASL_PROPERTIES_RESOLVER_CLASS.getMethod("getInstance", Configuration.class);
            GET_DEFAULT_PROP_METHOD = SASL_PROPERTIES_RESOLVER_CLASS.getMethod("getDefaultProperties");
         } catch (Exception var3) {
         }
      }

      if (SASL_PROPERTIES_RESOLVER_CLASS == null || GET_DEFAULT_PROP_METHOD == null) {
         try {
            SASL_PROPS_FIELD = SaslRpcServer.class.getField("SASL_PROPS");
         } catch (NoSuchFieldException e) {
            throw new IllegalStateException("Error finding hadoop SASL_PROPS field in " + SaslRpcServer.class.getSimpleName(), e);
         }
      }

   }
}
