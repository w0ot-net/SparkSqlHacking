package org.apache.derby.jdbc;

import java.util.Enumeration;
import java.util.Properties;
import javax.naming.NamingException;
import javax.naming.Reference;
import javax.naming.Referenceable;
import javax.naming.StringRefAddr;
import org.apache.derby.client.ClientDataSourceFactory;

public class ClientDataSource extends BasicClientDataSource40 implements Referenceable {
   private static final long serialVersionUID = 1894299584216955553L;
   public static final String className__ = "org.apache.derby.jdbc.ClientDataSource";

   public Reference getReference() throws NamingException {
      Reference var1 = new Reference(this.getClass().getName(), ClientDataSourceFactory.class.getName(), (String)null);
      this.addBeanProperties(var1);
      return var1;
   }

   private void addBeanProperties(Reference var1) {
      Properties var2 = getProperties(this);
      Enumeration var3 = var2.propertyNames();

      while(var3.hasMoreElements()) {
         String var4 = (String)var3.nextElement();
         String var5 = var2.getProperty(var4);
         if (var5 != null) {
            var1.add(new StringRefAddr(var4, var5.toString()));
         }
      }

   }
}
