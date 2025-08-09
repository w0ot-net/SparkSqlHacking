package org.sparkproject.jetty.util;

import java.util.Collections;
import java.util.Enumeration;
import java.util.Set;

public interface Attributes {
   void removeAttribute(String var1);

   void setAttribute(String var1, Object var2);

   Object getAttribute(String var1);

   Set getAttributeNameSet();

   default Enumeration getAttributeNames() {
      return Collections.enumeration(this.getAttributeNameSet());
   }

   void clearAttributes();

   static Attributes unwrap(Attributes attributes) {
      while(attributes instanceof Wrapper) {
         attributes = ((Wrapper)attributes).getAttributes();
      }

      return attributes;
   }

   static Wrapper unwrap(Attributes attributes, Class target) {
      while(attributes instanceof Wrapper) {
         if (target.isAssignableFrom(attributes.getClass())) {
            return (Wrapper)attributes;
         }

         attributes = ((Wrapper)attributes).getAttributes();
      }

      return null;
   }

   public abstract static class Wrapper implements Attributes {
      protected final Attributes _attributes;

      public Wrapper(Attributes attributes) {
         this._attributes = attributes;
      }

      public Attributes getAttributes() {
         return this._attributes;
      }

      public void removeAttribute(String name) {
         this._attributes.removeAttribute(name);
      }

      public void setAttribute(String name, Object attribute) {
         this._attributes.setAttribute(name, attribute);
      }

      public Object getAttribute(String name) {
         return this._attributes.getAttribute(name);
      }

      public Set getAttributeNameSet() {
         return this._attributes.getAttributeNameSet();
      }

      public void clearAttributes() {
         this._attributes.clearAttributes();
      }
   }
}
