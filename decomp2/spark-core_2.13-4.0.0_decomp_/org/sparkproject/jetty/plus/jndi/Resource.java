package org.sparkproject.jetty.plus.jndi;

import javax.naming.NamingException;

public class Resource extends NamingEntry {
   public Resource(Object scope, String jndiName, Object objToBind) throws NamingException {
      super(scope, jndiName);
      this.save(objToBind);
   }

   public Resource(String jndiName, Object objToBind) throws NamingException {
      super(jndiName);
      this.save(objToBind);
   }
}
