package com.zaxxer.hikari;

import com.zaxxer.hikari.util.PropertyElf;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Set;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.Name;
import javax.naming.NamingException;
import javax.naming.RefAddr;
import javax.naming.Reference;
import javax.naming.spi.ObjectFactory;
import javax.sql.DataSource;

public class HikariJNDIFactory implements ObjectFactory {
   public synchronized Object getObjectInstance(Object obj, Name name, Context nameCtx, Hashtable environment) throws Exception {
      if (!(obj instanceof Reference)) {
         return null;
      } else {
         Reference ref = (Reference)obj;
         if (!"javax.sql.DataSource".equals(ref.getClassName())) {
            throw new NamingException(ref.getClassName() + " is not a valid class name/type for this JNDI factory.");
         } else {
            Set<String> hikariPropSet = PropertyElf.getPropertyNames(HikariConfig.class);
            Properties properties = new Properties();
            Enumeration<RefAddr> enumeration = ref.getAll();

            while(enumeration.hasMoreElements()) {
               RefAddr element = (RefAddr)enumeration.nextElement();
               String type = element.getType();
               if (type.startsWith("dataSource.") || hikariPropSet.contains(type)) {
                  properties.setProperty(type, element.getContent().toString());
               }
            }

            return this.createDataSource(properties, nameCtx);
         }
      }
   }

   private DataSource createDataSource(Properties properties, Context context) throws NamingException {
      String jndiName = properties.getProperty("dataSourceJNDI");
      return (DataSource)(jndiName != null ? this.lookupJndiDataSource(properties, context, jndiName) : new HikariDataSource(new HikariConfig(properties)));
   }

   private DataSource lookupJndiDataSource(Properties properties, Context context, String jndiName) throws NamingException {
      if (context == null) {
         throw new RuntimeException("JNDI context does not found for dataSourceJNDI : " + jndiName);
      } else {
         DataSource jndiDS = (DataSource)context.lookup(jndiName);
         if (jndiDS == null) {
            Context ic = new InitialContext();
            jndiDS = (DataSource)ic.lookup(jndiName);
            ic.close();
         }

         if (jndiDS != null) {
            HikariConfig config = new HikariConfig(properties);
            config.setDataSource(jndiDS);
            return new HikariDataSource(config);
         } else {
            return null;
         }
      }
   }
}
