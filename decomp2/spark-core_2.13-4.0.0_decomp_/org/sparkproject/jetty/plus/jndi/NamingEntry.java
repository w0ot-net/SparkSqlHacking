package org.sparkproject.jetty.plus.jndi;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.LinkRef;
import javax.naming.Name;
import javax.naming.NameParser;
import javax.naming.NamingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.jndi.NamingUtil;

public abstract class NamingEntry {
   private static final Logger LOG = LoggerFactory.getLogger(NamingEntry.class);
   public static final String __contextName = "__";
   protected final Object _scope;
   protected final String _jndiName;
   protected String _namingEntryNameString;
   protected String _objectNameString;

   protected NamingEntry(Object scope, String jndiName) throws NamingException {
      if (jndiName == null) {
         throw new NamingException("jndi name is null");
      } else {
         this._scope = scope;
         this._jndiName = jndiName;
      }
   }

   protected NamingEntry(String jndiName) throws NamingException {
      this((Object)null, jndiName);
   }

   public void bindToENC(String localName) throws NamingException {
      InitialContext ic = new InitialContext();
      Context env = (Context)ic.lookup("java:comp/env");
      if (LOG.isDebugEnabled()) {
         LOG.debug("Binding java:comp/env/{} to {}", localName, this._objectNameString);
      }

      NamingUtil.bind(env, localName, new LinkRef(this._objectNameString));
   }

   public void unbindENC() {
      try {
         InitialContext ic = new InitialContext();
         Context env = (Context)ic.lookup("java:comp/env");
         if (LOG.isDebugEnabled()) {
            LOG.debug("Unbinding java:comp/env/{}", this.getJndiName());
         }

         env.unbind(this.getJndiName());
      } catch (NamingException e) {
         LOG.warn("Unable to unbind ENC", e);
      }

   }

   public void release() {
      try {
         InitialContext ic = new InitialContext();
         ic.unbind(this._objectNameString);
         ic.unbind(this._namingEntryNameString);
         this._namingEntryNameString = null;
         this._objectNameString = null;
      } catch (NamingException e) {
         LOG.warn("Unable to release: {} and {}", new Object[]{this._objectNameString, this._namingEntryNameString, e});
      }

   }

   public String getJndiName() {
      return this._jndiName;
   }

   public String getJndiNameInScope() {
      return this._objectNameString;
   }

   protected void save(Object object) throws NamingException {
      if (LOG.isDebugEnabled()) {
         LOG.debug("SAVE {} in {}", this, this._scope);
      }

      InitialContext ic = new InitialContext();
      NameParser parser = ic.getNameParser("");
      Name prefix = NamingEntryUtil.getNameForScope(this._scope);
      Name namingEntryName = NamingEntryUtil.makeNamingEntryName(parser, this.getJndiName());
      namingEntryName.addAll(0, prefix);
      this._namingEntryNameString = namingEntryName.toString();
      NamingUtil.bind(ic, this._namingEntryNameString, this);
      Name objectName = parser.parse(this.getJndiName());
      objectName.addAll(0, prefix);
      this._objectNameString = objectName.toString();
      NamingUtil.bind(ic, this._objectNameString, object);
   }

   protected String toStringMetaData() {
      return null;
   }

   public String toString() {
      String metadata = this.toStringMetaData();
      return metadata == null ? String.format("%s@%x{name=%s}", this.getClass().getName(), this.hashCode(), this.getJndiName()) : String.format("%s@%x{name=%s,%s}", this.getClass().getName(), this.hashCode(), this.getJndiName(), metadata);
   }
}
