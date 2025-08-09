package org.glassfish.jaxb.runtime.v2.runtime;

import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;

public final class AssociationMap {
   private final Map byElement = new IdentityHashMap();
   private final Map byPeer = new IdentityHashMap();
   private final Set usedNodes = new HashSet();

   public void addInner(Object element, Object inner) {
      Entry<XmlNode> e = (Entry)this.byElement.get(element);
      if (e != null) {
         if (e.inner != null) {
            this.byPeer.remove(e.inner);
         }

         e.inner = inner;
      } else {
         e = new Entry();
         e.element = element;
         e.inner = inner;
      }

      this.byElement.put(element, e);
      Entry<XmlNode> old = (Entry)this.byPeer.put(inner, e);
      if (old != null) {
         if (old.outer != null) {
            this.byPeer.remove(old.outer);
         }

         if (old.element != null) {
            this.byElement.remove(old.element);
         }
      }

   }

   public void addOuter(Object element, Object outer) {
      Entry<XmlNode> e = (Entry)this.byElement.get(element);
      if (e != null) {
         if (e.outer != null) {
            this.byPeer.remove(e.outer);
         }
      } else {
         e = new Entry();
         e.element = element;
      }

      e.outer = outer;
      this.byElement.put(element, e);
      Entry<XmlNode> old = (Entry)this.byPeer.put(outer, e);
      if (old != null) {
         old.outer = null;
         if (old.inner == null) {
            this.byElement.remove(old.element);
         }
      }

   }

   public void addUsed(Object n) {
      this.usedNodes.add(n);
   }

   public Entry byElement(Object e) {
      return (Entry)this.byElement.get(e);
   }

   public Entry byPeer(Object o) {
      return (Entry)this.byPeer.get(o);
   }

   public Object getInnerPeer(Object element) {
      Entry<XmlNode> e = this.byElement(element);
      return e == null ? null : e.inner;
   }

   public Object getOuterPeer(Object element) {
      Entry<XmlNode> e = this.byElement(element);
      return e == null ? null : e.outer;
   }

   public static final class Entry {
      private Object element;
      private Object inner;
      private Object outer;

      private Entry() {
      }

      public Object element() {
         return this.element;
      }

      public Object inner() {
         return this.inner;
      }

      public Object outer() {
         return this.outer;
      }
   }
}
