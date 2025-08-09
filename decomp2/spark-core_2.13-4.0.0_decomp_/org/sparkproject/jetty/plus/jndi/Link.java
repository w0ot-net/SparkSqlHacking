package org.sparkproject.jetty.plus.jndi;

import javax.naming.NamingException;

public class Link extends NamingEntry {
   private final String _link;

   public Link(Object scope, String jndiName, String link) throws NamingException {
      super(scope, jndiName);
      this.save(link);
      this._link = link;
   }

   public Link(String jndiName, String link) throws NamingException {
      super(jndiName);
      this.save(link);
      this._link = link;
   }

   public void bindToENC(String localName) throws NamingException {
      throw new UnsupportedOperationException("Method not supported for Link objects");
   }

   public String getLink() {
      return this._link;
   }

   protected String toStringMetaData() {
      return this._link;
   }
}
