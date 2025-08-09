package org.sparkproject.jetty.plus.jndi;

import javax.naming.NamingException;

public class EnvEntry extends NamingEntry {
   private boolean overrideWebXml;

   public EnvEntry(Object scope, String jndiName, Object objToBind, boolean overrideWebXml) throws NamingException {
      super(scope, jndiName);
      this.save(objToBind);
      this.overrideWebXml = overrideWebXml;
   }

   public EnvEntry(String jndiName, Object objToBind, boolean overrideWebXml) throws NamingException {
      super(jndiName);
      this.save(objToBind);
      this.overrideWebXml = overrideWebXml;
   }

   public EnvEntry(String jndiName, Object objToBind) throws NamingException {
      this(jndiName, objToBind, false);
   }

   public boolean isOverrideWebXml() {
      return this.overrideWebXml;
   }

   protected String toStringMetaData() {
      return "OverrideWebXml=" + this.overrideWebXml;
   }
}
