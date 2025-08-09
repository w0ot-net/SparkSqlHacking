package org.apache.logging.log4j.spi;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.apache.logging.log4j.internal.map.UnmodifiableArrayBackedMap;
import org.apache.logging.log4j.util.BiConsumer;
import org.apache.logging.log4j.util.PropertiesUtil;
import org.apache.logging.log4j.util.ReadOnlyStringMap;
import org.apache.logging.log4j.util.TriConsumer;

public class DefaultThreadContextMap implements ThreadContextMap, ReadOnlyStringMap {
   private static final long serialVersionUID = -2635197170958057849L;
   public static final String INHERITABLE_MAP = "isThreadContextMapInheritable";
   private ThreadLocal localState;

   public DefaultThreadContextMap() {
      this(PropertiesUtil.getProperties());
   }

   /** @deprecated */
   @Deprecated
   public DefaultThreadContextMap(final boolean ignored) {
      this(PropertiesUtil.getProperties());
   }

   DefaultThreadContextMap(final PropertiesUtil properties) {
      this.localState = (ThreadLocal)(properties.getBooleanProperty("isThreadContextMapInheritable") ? new InheritableThreadLocal() {
         protected Object[] childValue(final Object[] parentValue) {
            return parentValue;
         }
      } : new ThreadLocal());
   }

   public void put(final String key, final String value) {
      Object[] state = this.localState.get();
      this.localState.set(UnmodifiableArrayBackedMap.getMap(state).copyAndPut(key, value).getBackingArray());
   }

   public void putAll(final Map m) {
      Object[] state = this.localState.get();
      this.localState.set(UnmodifiableArrayBackedMap.getMap(state).copyAndPutAll(m).getBackingArray());
   }

   public String get(final String key) {
      Object[] state = this.localState.get();
      return state == null ? null : UnmodifiableArrayBackedMap.getMap(state).get(key);
   }

   public void remove(final String key) {
      Object[] state = this.localState.get();
      if (state != null) {
         this.localState.set(UnmodifiableArrayBackedMap.getMap(state).copyAndRemove(key).getBackingArray());
      }

   }

   public void removeAll(final Iterable keys) {
      Object[] state = this.localState.get();
      if (state != null) {
         this.localState.set(UnmodifiableArrayBackedMap.getMap(state).copyAndRemoveAll(keys).getBackingArray());
      }

   }

   public void clear() {
      this.localState.remove();
   }

   public Map toMap() {
      return this.getCopy();
   }

   public boolean containsKey(final String key) {
      Object[] state = this.localState.get();
      return state != null && UnmodifiableArrayBackedMap.getMap(state).containsKey(key);
   }

   public void forEach(final BiConsumer action) {
      Object[] state = this.localState.get();
      if (state != null) {
         UnmodifiableArrayBackedMap.getMap(state).forEach(action);
      }
   }

   public void forEach(final TriConsumer action, final Object state) {
      Object[] localState = this.localState.get();
      if (localState != null) {
         UnmodifiableArrayBackedMap.getMap(localState).forEach(action, state);
      }
   }

   public Object getValue(final String key) {
      return this.get(key);
   }

   public Map getCopy() {
      Object[] state = this.localState.get();
      return state == null ? new HashMap(0) : new HashMap(UnmodifiableArrayBackedMap.getMap(state));
   }

   public Map getImmutableMapOrNull() {
      Object[] state = this.localState.get();
      return state == null ? null : UnmodifiableArrayBackedMap.getMap(state);
   }

   public boolean isEmpty() {
      return this.size() == 0;
   }

   public int size() {
      Object[] state = this.localState.get();
      return UnmodifiableArrayBackedMap.getMap(state).size();
   }

   public String toString() {
      Object[] state = this.localState.get();
      return state == null ? "{}" : UnmodifiableArrayBackedMap.getMap(state).toString();
   }

   public int hashCode() {
      int prime = 31;
      int result = 1;
      Object[] state = this.localState.get();
      result = 31 * result + (state == null ? 0 : UnmodifiableArrayBackedMap.getMap(state).hashCode());
      return result;
   }

   public boolean equals(final Object obj) {
      if (this == obj) {
         return true;
      } else if (obj == null) {
         return false;
      } else if (!(obj instanceof ThreadContextMap)) {
         return false;
      } else {
         ThreadContextMap other = (ThreadContextMap)obj;
         Map<String, String> map = UnmodifiableArrayBackedMap.getMap(this.localState.get());
         Map<String, String> otherMap = other.getImmutableMapOrNull();
         return Objects.equals(map, otherMap);
      }
   }
}
