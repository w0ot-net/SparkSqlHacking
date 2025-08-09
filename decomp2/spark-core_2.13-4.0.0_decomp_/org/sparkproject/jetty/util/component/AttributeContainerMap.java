package org.sparkproject.jetty.util.component;

import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.sparkproject.jetty.util.Attributes;
import org.sparkproject.jetty.util.thread.AutoLock;

public class AttributeContainerMap extends ContainerLifeCycle implements Attributes {
   private final AutoLock _lock = new AutoLock();
   private final Map _map = new HashMap();

   public void setAttribute(String name, Object attribute) {
      try (AutoLock l = this._lock.lock()) {
         Object old = this._map.put(name, attribute);
         this.updateBean(old, attribute);
      }

   }

   public void removeAttribute(String name) {
      try (AutoLock l = this._lock.lock()) {
         Object removed = this._map.remove(name);
         if (removed != null) {
            this.removeBean(removed);
         }
      }

   }

   public Object getAttribute(String name) {
      try (AutoLock l = this._lock.lock()) {
         return this._map.get(name);
      }
   }

   public Enumeration getAttributeNames() {
      try (AutoLock l = this._lock.lock()) {
         return Collections.enumeration(this._map.keySet());
      }
   }

   public Set getAttributeNameSet() {
      try (AutoLock l = this._lock.lock()) {
         return this._map.keySet();
      }
   }

   public void clearAttributes() {
      try (AutoLock l = this._lock.lock()) {
         this._map.clear();
         this.removeBeans();
      }

   }

   public void dump(Appendable out, String indent) throws IOException {
      Dumpable.dumpObject(out, this);
      Dumpable.dumpMapEntries(out, indent, this._map, true);
   }

   public String toString() {
      return String.format("%s@%x{size=%d}", this.getClass().getSimpleName(), this.hashCode(), this._map.size());
   }
}
