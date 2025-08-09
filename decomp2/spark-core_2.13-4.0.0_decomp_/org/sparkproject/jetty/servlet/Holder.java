package org.sparkproject.jetty.servlet;

import jakarta.servlet.Registration;
import jakarta.servlet.ServletContext;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.util.annotation.ManagedAttribute;
import org.sparkproject.jetty.util.annotation.ManagedObject;
import org.sparkproject.jetty.util.thread.AutoLock;

@ManagedObject("Holder - a container for servlets and the like")
public abstract class Holder extends BaseHolder {
   private static final Logger LOG = LoggerFactory.getLogger(Holder.class);
   private final Map _initParams = new HashMap(3);
   private String _displayName;
   private boolean _asyncSupported;
   private String _name;

   protected Holder(Source source) {
      super(source);
      switch (this.getSource().getOrigin()) {
         case JAKARTA_API:
         case DESCRIPTOR:
         case ANNOTATION:
            this._asyncSupported = false;
            break;
         default:
            this._asyncSupported = true;
      }

   }

   @ManagedAttribute(
      value = "Display Name",
      readonly = true
   )
   public String getDisplayName() {
      return this._displayName;
   }

   public String getInitParameter(String param) {
      return this._initParams == null ? null : (String)this._initParams.get(param);
   }

   public Enumeration getInitParameterNames() {
      return this._initParams == null ? Collections.enumeration(Collections.EMPTY_LIST) : Collections.enumeration(this._initParams.keySet());
   }

   @ManagedAttribute(
      value = "Initial Parameters",
      readonly = true
   )
   public Map getInitParameters() {
      return this._initParams;
   }

   @ManagedAttribute(
      value = "Name",
      readonly = true
   )
   public String getName() {
      return this._name;
   }

   protected void setInstance(Object instance) {
      try (AutoLock l = this.lock()) {
         super.setInstance(instance);
         if (this.getName() == null) {
            this.setName(String.format("%s@%x", instance.getClass().getName(), instance.hashCode()));
         }
      }

   }

   public void destroyInstance(Object instance) throws Exception {
   }

   public void setClassName(String className) {
      super.setClassName(className);
      if (this._name == null) {
         this._name = className + "-" + Integer.toHexString(this.hashCode());
      }

   }

   public void setHeldClass(Class held) {
      super.setHeldClass(held);
      if (held != null && this._name == null) {
         String var10001 = held.getName();
         this._name = var10001 + "-" + Integer.toHexString(this.hashCode());
      }

   }

   public void setDisplayName(String name) {
      this._displayName = name;
   }

   public void setInitParameter(String param, String value) {
      this._initParams.put(param, value);
   }

   public void setInitParameters(Map map) {
      this._initParams.clear();
      this._initParams.putAll(map);
   }

   public void setName(String name) {
      this._name = name;
   }

   public void setAsyncSupported(boolean suspendable) {
      this._asyncSupported = suspendable;
   }

   public boolean isAsyncSupported() {
      return this._asyncSupported;
   }

   public String dump() {
      return super.dump();
   }

   public String toString() {
      return String.format("%s@%x==%s", this._name, this.hashCode(), this.getClassName());
   }

   protected class HolderConfig {
      public ServletContext getServletContext() {
         return Holder.this.getServletHandler().getServletContext();
      }

      public String getInitParameter(String param) {
         return Holder.this.getInitParameter(param);
      }

      public Enumeration getInitParameterNames() {
         return Holder.this.getInitParameterNames();
      }
   }

   protected class HolderRegistration implements Registration.Dynamic {
      public void setAsyncSupported(boolean isAsyncSupported) {
         Holder.this.illegalStateIfContextStarted();
         Holder.this.setAsyncSupported(isAsyncSupported);
      }

      public void setDescription(String description) {
         if (Holder.LOG.isDebugEnabled()) {
            Holder.LOG.debug("{} is {}", this, description);
         }

      }

      public String getClassName() {
         return Holder.this.getClassName();
      }

      public String getInitParameter(String name) {
         return Holder.this.getInitParameter(name);
      }

      public Map getInitParameters() {
         return Holder.this.getInitParameters();
      }

      public String getName() {
         return Holder.this.getName();
      }

      public boolean setInitParameter(String name, String value) {
         Holder.this.illegalStateIfContextStarted();
         if (name == null) {
            throw new IllegalArgumentException("init parameter name required");
         } else if (value == null) {
            throw new IllegalArgumentException("non-null value required for init parameter " + name);
         } else if (Holder.this.getInitParameter(name) != null) {
            return false;
         } else {
            Holder.this.setInitParameter(name, value);
            return true;
         }
      }

      public Set setInitParameters(Map initParameters) {
         Holder.this.illegalStateIfContextStarted();
         Set<String> clash = null;

         for(Map.Entry entry : initParameters.entrySet()) {
            if (entry.getKey() == null) {
               throw new IllegalArgumentException("init parameter name required");
            }

            if (entry.getValue() == null) {
               throw new IllegalArgumentException("non-null value required for init parameter " + (String)entry.getKey());
            }

            if (Holder.this.getInitParameter((String)entry.getKey()) != null) {
               if (clash == null) {
                  clash = new HashSet();
               }

               clash.add((String)entry.getKey());
            }
         }

         if (clash != null) {
            return clash;
         } else {
            Holder.this.getInitParameters().putAll(initParameters);
            return Collections.emptySet();
         }
      }
   }
}
