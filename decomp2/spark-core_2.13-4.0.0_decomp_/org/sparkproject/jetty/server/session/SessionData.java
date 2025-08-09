package org.sparkproject.jetty.server.session;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.util.ClassLoadingObjectInputStream;
import org.sparkproject.jetty.util.ClassVisibilityChecker;

public class SessionData implements Serializable {
   private static final Logger LOG = LoggerFactory.getLogger(SessionData.class);
   private static final long serialVersionUID = 1L;
   protected String _id;
   protected String _contextPath;
   protected String _vhost;
   protected String _lastNode;
   protected long _expiry;
   protected long _created;
   protected long _cookieSet;
   protected long _accessed;
   protected long _lastAccessed;
   protected long _maxInactiveMs;
   protected Map _attributes;
   protected boolean _dirty;
   protected long _lastSaved;
   protected boolean _metaDataDirty;

   public static void serializeAttributes(SessionData data, ObjectOutputStream out) throws IOException {
      int entries = data._attributes.size();
      out.writeObject(entries);

      for(Map.Entry entry : data._attributes.entrySet()) {
         out.writeUTF((String)entry.getKey());
         Class<?> clazz = entry.getValue().getClass();
         ClassLoader loader = clazz.getClassLoader();
         ClassLoader contextLoader = Thread.currentThread().getContextClassLoader();
         boolean isContextLoader;
         if (loader == contextLoader) {
            isContextLoader = true;
         } else if (contextLoader == null) {
            isContextLoader = false;
         } else if (contextLoader instanceof ClassVisibilityChecker) {
            ClassVisibilityChecker checker = (ClassVisibilityChecker)contextLoader;
            isContextLoader = checker.isSystemClass(clazz) && !checker.isServerClass(clazz);
         } else {
            try {
               Class<?> result = contextLoader.loadClass(clazz.getName());
               isContextLoader = result == clazz;
            } catch (Throwable var10) {
               isContextLoader = false;
            }
         }

         if (LOG.isDebugEnabled()) {
            LOG.debug("Attribute {} class={} isServerLoader={}", new Object[]{entry.getKey(), clazz.getName(), !isContextLoader});
         }

         out.writeBoolean(!isContextLoader);
         out.writeObject(entry.getValue());
      }

   }

   public static void deserializeAttributes(SessionData data, ObjectInputStream in) throws IOException, ClassNotFoundException {
      Object o = in.readObject();
      if (o instanceof Integer) {
         if (!ClassLoadingObjectInputStream.class.isAssignableFrom(in.getClass())) {
            throw new IOException("Not ClassLoadingObjectInputStream");
         }

         data._attributes = new ConcurrentHashMap();
         int entries = (Integer)o;
         ClassLoader contextLoader = Thread.currentThread().getContextClassLoader();
         ClassLoader serverLoader = SessionData.class.getClassLoader();

         for(int i = 0; i < entries; ++i) {
            String name = in.readUTF();
            boolean isServerClassLoader = in.readBoolean();
            if (LOG.isDebugEnabled()) {
               LOG.debug("Deserialize {} isServerLoader={} serverLoader={} tccl={}", new Object[]{name, isServerClassLoader, serverLoader, contextLoader});
            }

            Object value = ((ClassLoadingObjectInputStream)in).readObject(isServerClassLoader ? serverLoader : contextLoader);
            data._attributes.put(name, value);
         }
      } else {
         LOG.info("Legacy serialization detected for {}", data.getId());
         data._attributes = new ConcurrentHashMap();
         data.putAllAttributes((Map)o);
      }

   }

   public SessionData(String id, String cpath, String vhost, long created, long accessed, long lastAccessed, long maxInactiveMs) {
      this._id = id;
      this.setContextPath(cpath);
      this.setVhost(vhost);
      this._created = created;
      this._accessed = accessed;
      this._lastAccessed = lastAccessed;
      this._maxInactiveMs = maxInactiveMs;
      this.calcAndSetExpiry();
      this._attributes = new ConcurrentHashMap();
   }

   public SessionData(String id, String cpath, String vhost, long created, long accessed, long lastAccessed, long maxInactiveMs, Map attributes) {
      this(id, cpath, vhost, created, accessed, lastAccessed, maxInactiveMs);
      this.putAllAttributes(attributes);
   }

   public void copy(SessionData data) {
      if (data != null) {
         if (data.getId() != null && this.getId().equals(data.getId())) {
            if (data != this) {
               this.setLastNode(data.getLastNode());
               this.setContextPath(data.getContextPath());
               this.setVhost(data.getVhost());
               this.setCookieSet(data.getCookieSet());
               this.setCreated(data.getCreated());
               this.setAccessed(data.getAccessed());
               this.setLastAccessed(data.getLastAccessed());
               this.setMaxInactiveMs(data.getMaxInactiveMs());
               this.setExpiry(data.getExpiry());
               this.setLastSaved(data.getLastSaved());
               this.clearAllAttributes();
               this.putAllAttributes(data.getAllAttributes());
            }
         } else {
            throw new IllegalStateException("Can only copy data for same session id");
         }
      }
   }

   public long getLastSaved() {
      return this._lastSaved;
   }

   public void setLastSaved(long lastSaved) {
      this._lastSaved = lastSaved;
   }

   public boolean isDirty() {
      return this._dirty;
   }

   public void setDirty(boolean dirty) {
      this._dirty = dirty;
   }

   public void setDirty(String name) {
      this.setDirty(true);
   }

   public boolean isMetaDataDirty() {
      return this._metaDataDirty;
   }

   public void setMetaDataDirty(boolean metaDataDirty) {
      this._metaDataDirty = metaDataDirty;
   }

   public Object getAttribute(String name) {
      return this._attributes.get(name);
   }

   public Set getKeys() {
      return this._attributes.keySet();
   }

   public Object setAttribute(String name, Object value) {
      Object old = value == null ? this._attributes.remove(name) : this._attributes.put(name, value);
      if (value == null && old == null) {
         return old;
      } else {
         this.setDirty(name);
         return old;
      }
   }

   public void clean() {
      this.setDirty(false);
      this.setMetaDataDirty(false);
   }

   public void putAllAttributes(Map attributes) {
      this._attributes.putAll(attributes);
   }

   public void clearAllAttributes() {
      this._attributes.clear();
   }

   public Map getAllAttributes() {
      return Collections.unmodifiableMap(this._attributes);
   }

   public String getId() {
      return this._id;
   }

   public void setId(String id) {
      this._id = id;
   }

   public String getContextPath() {
      return this._contextPath;
   }

   public void setContextPath(String contextPath) {
      this._contextPath = contextPath;
   }

   public String getVhost() {
      return this._vhost;
   }

   public void setVhost(String vhost) {
      this._vhost = vhost;
   }

   public String getLastNode() {
      return this._lastNode;
   }

   public void setLastNode(String lastNode) {
      this._lastNode = lastNode;
   }

   public long getExpiry() {
      return this._expiry;
   }

   public void setExpiry(long expiry) {
      this._expiry = expiry;
   }

   public long calcExpiry() {
      return this.calcExpiry(System.currentTimeMillis());
   }

   public long calcExpiry(long time) {
      return this.getMaxInactiveMs() <= 0L ? 0L : time + this.getMaxInactiveMs();
   }

   public void calcAndSetExpiry(long time) {
      this.setExpiry(this.calcExpiry(time));
      this.setMetaDataDirty(true);
   }

   public void calcAndSetExpiry() {
      this.setExpiry(this.calcExpiry());
      this.setMetaDataDirty(true);
   }

   public long getCreated() {
      return this._created;
   }

   public void setCreated(long created) {
      this._created = created;
   }

   public long getCookieSet() {
      return this._cookieSet;
   }

   public void setCookieSet(long cookieSet) {
      this._cookieSet = cookieSet;
   }

   public long getAccessed() {
      return this._accessed;
   }

   public void setAccessed(long accessed) {
      this._accessed = accessed;
   }

   public long getLastAccessed() {
      return this._lastAccessed;
   }

   public void setLastAccessed(long lastAccessed) {
      this._lastAccessed = lastAccessed;
   }

   public long getMaxInactiveMs() {
      return this._maxInactiveMs;
   }

   public void setMaxInactiveMs(long maxInactive) {
      this._maxInactiveMs = maxInactive;
   }

   private void writeObject(ObjectOutputStream out) throws IOException {
      out.writeUTF(this._id);
      out.writeUTF(this._contextPath);
      out.writeUTF(this._vhost);
      out.writeLong(this._accessed);
      out.writeLong(this._lastAccessed);
      out.writeLong(this._created);
      out.writeLong(this._cookieSet);
      out.writeUTF(this._lastNode);
      out.writeLong(this._expiry);
      out.writeLong(this._maxInactiveMs);
      serializeAttributes(this, out);
   }

   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
      this._id = in.readUTF();
      this._contextPath = in.readUTF();
      this._vhost = in.readUTF();
      this._accessed = in.readLong();
      this._lastAccessed = in.readLong();
      this._created = in.readLong();
      this._cookieSet = in.readLong();
      this._lastNode = in.readUTF();
      this._expiry = in.readLong();
      this._maxInactiveMs = in.readLong();
      deserializeAttributes(this, in);
   }

   public boolean isExpiredAt(long time) {
      if (LOG.isDebugEnabled()) {
         LOG.debug("Testing expiry on session {}: expires at {} now {} maxIdle {}", new Object[]{this._id, this.getExpiry(), time, this.getMaxInactiveMs()});
      }

      if (this.getMaxInactiveMs() <= 0L) {
         return false;
      } else {
         return this.getExpiry() <= time;
      }
   }

   public String toString() {
      StringBuilder builder = new StringBuilder();
      builder.append("id=" + this._id);
      builder.append(", contextpath=" + this._contextPath);
      builder.append(", vhost=" + this._vhost);
      builder.append(", accessed=" + this._accessed);
      builder.append(", lastaccessed=" + this._lastAccessed);
      builder.append(", created=" + this._created);
      builder.append(", cookieset=" + this._cookieSet);
      builder.append(", lastnode=" + this._lastNode);
      builder.append(", expiry=" + this._expiry);
      builder.append(", maxinactive=" + this._maxInactiveMs);
      return builder.toString();
   }
}
