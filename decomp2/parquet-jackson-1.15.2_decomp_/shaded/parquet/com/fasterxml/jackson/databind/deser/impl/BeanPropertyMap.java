package shaded.parquet.com.fasterxml.jackson.databind.deser.impl;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;
import shaded.parquet.com.fasterxml.jackson.core.JacksonException;
import shaded.parquet.com.fasterxml.jackson.core.JsonParser;
import shaded.parquet.com.fasterxml.jackson.databind.DeserializationContext;
import shaded.parquet.com.fasterxml.jackson.databind.DeserializationFeature;
import shaded.parquet.com.fasterxml.jackson.databind.JsonDeserializer;
import shaded.parquet.com.fasterxml.jackson.databind.JsonMappingException;
import shaded.parquet.com.fasterxml.jackson.databind.MapperFeature;
import shaded.parquet.com.fasterxml.jackson.databind.PropertyName;
import shaded.parquet.com.fasterxml.jackson.databind.cfg.MapperConfig;
import shaded.parquet.com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import shaded.parquet.com.fasterxml.jackson.databind.util.ClassUtil;
import shaded.parquet.com.fasterxml.jackson.databind.util.IgnorePropertiesUtil;
import shaded.parquet.com.fasterxml.jackson.databind.util.NameTransformer;

public class BeanPropertyMap implements Iterable, Serializable {
   private static final long serialVersionUID = 2L;
   protected final boolean _caseInsensitive;
   private int _hashMask;
   private int _size;
   private int _spillCount;
   private Object[] _hashArea;
   private final SettableBeanProperty[] _propsInOrder;
   private final Map _aliasDefs;
   private final Map _aliasMapping;
   private final Locale _locale;

   public BeanPropertyMap(boolean caseInsensitive, Collection props, Map aliasDefs, Locale locale) {
      this._caseInsensitive = caseInsensitive;
      this._propsInOrder = (SettableBeanProperty[])props.toArray(new SettableBeanProperty[props.size()]);
      this._aliasDefs = aliasDefs;
      this._locale = locale;
      this._aliasMapping = this._buildAliasMapping(aliasDefs, caseInsensitive, locale);
      this.init(props);
   }

   /** @deprecated */
   @Deprecated
   public BeanPropertyMap(boolean caseInsensitive, Collection props, Map aliasDefs) {
      this(caseInsensitive, props, aliasDefs, Locale.getDefault());
   }

   private BeanPropertyMap(BeanPropertyMap src, SettableBeanProperty newProp, int hashIndex, int orderedIndex) {
      this._caseInsensitive = src._caseInsensitive;
      this._locale = src._locale;
      this._hashMask = src._hashMask;
      this._size = src._size;
      this._spillCount = src._spillCount;
      this._aliasDefs = src._aliasDefs;
      this._aliasMapping = src._aliasMapping;
      this._hashArea = Arrays.copyOf(src._hashArea, src._hashArea.length);
      this._propsInOrder = (SettableBeanProperty[])Arrays.copyOf(src._propsInOrder, src._propsInOrder.length);
      this._hashArea[hashIndex] = newProp;
      this._propsInOrder[orderedIndex] = newProp;
   }

   private BeanPropertyMap(BeanPropertyMap src, SettableBeanProperty newProp, String key, int slot) {
      this._caseInsensitive = src._caseInsensitive;
      this._locale = src._locale;
      this._hashMask = src._hashMask;
      this._size = src._size;
      this._spillCount = src._spillCount;
      this._aliasDefs = src._aliasDefs;
      this._aliasMapping = src._aliasMapping;
      this._hashArea = Arrays.copyOf(src._hashArea, src._hashArea.length);
      int last = src._propsInOrder.length;
      this._propsInOrder = (SettableBeanProperty[])Arrays.copyOf(src._propsInOrder, last + 1);
      this._propsInOrder[last] = newProp;
      int hashSize = this._hashMask + 1;
      int ix = slot << 1;
      if (this._hashArea[ix] != null) {
         ix = hashSize + (slot >> 1) << 1;
         if (this._hashArea[ix] != null) {
            ix = (hashSize + (hashSize >> 1) << 1) + this._spillCount;
            this._spillCount += 2;
            if (ix >= this._hashArea.length) {
               this._hashArea = Arrays.copyOf(this._hashArea, this._hashArea.length + 4);
            }
         }
      }

      this._hashArea[ix] = key;
      this._hashArea[ix + 1] = newProp;
   }

   protected BeanPropertyMap(BeanPropertyMap base, boolean caseInsensitive) {
      this._caseInsensitive = caseInsensitive;
      this._locale = base._locale;
      this._aliasDefs = base._aliasDefs;
      this._aliasMapping = base._aliasMapping;
      this._propsInOrder = (SettableBeanProperty[])Arrays.copyOf(base._propsInOrder, base._propsInOrder.length);
      this.init(Arrays.asList(this._propsInOrder));
   }

   public BeanPropertyMap withCaseInsensitivity(boolean state) {
      return this._caseInsensitive == state ? this : new BeanPropertyMap(this, state);
   }

   protected void init(Collection props) {
      this._size = props.size();
      int hashSize = findSize(this._size);
      this._hashMask = hashSize - 1;
      int alloc = (hashSize + (hashSize >> 1)) * 2;
      Object[] hashed = new Object[alloc];
      int spillCount = 0;

      for(SettableBeanProperty prop : props) {
         if (prop != null) {
            String key = this.getPropertyName(prop);
            int slot = this._hashCode(key);
            int ix = slot << 1;
            if (hashed[ix] != null) {
               ix = hashSize + (slot >> 1) << 1;
               if (hashed[ix] != null) {
                  ix = (hashSize + (hashSize >> 1) << 1) + spillCount;
                  spillCount += 2;
                  if (ix >= hashed.length) {
                     hashed = Arrays.copyOf(hashed, hashed.length + 4);
                  }
               }
            }

            hashed[ix] = key;
            hashed[ix + 1] = prop;
         }
      }

      this._hashArea = hashed;
      this._spillCount = spillCount;
   }

   private static final int findSize(int size) {
      if (size <= 5) {
         return 8;
      } else if (size <= 12) {
         return 16;
      } else {
         int needed = size + (size >> 2);

         int result;
         for(result = 32; result < needed; result += result) {
         }

         return result;
      }
   }

   public static BeanPropertyMap construct(MapperConfig config, Collection props, Map aliasMapping, boolean caseInsensitive) {
      return new BeanPropertyMap(caseInsensitive, props, aliasMapping, config.getLocale());
   }

   /** @deprecated */
   @Deprecated
   public static BeanPropertyMap construct(MapperConfig config, Collection props, Map aliasMapping) {
      return new BeanPropertyMap(config.isEnabled(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES), props, aliasMapping, config.getLocale());
   }

   /** @deprecated */
   @Deprecated
   public static BeanPropertyMap construct(Collection props, boolean caseInsensitive, Map aliasMapping) {
      return new BeanPropertyMap(caseInsensitive, props, aliasMapping);
   }

   public BeanPropertyMap withProperty(SettableBeanProperty newProp) {
      String key = this.getPropertyName(newProp);
      int i = 1;

      for(int end = this._hashArea.length; i < end; i += 2) {
         SettableBeanProperty prop = (SettableBeanProperty)this._hashArea[i];
         if (prop != null && prop.getName().equals(key)) {
            return new BeanPropertyMap(this, newProp, i, this._findFromOrdered(prop));
         }
      }

      i = this._hashCode(key);
      return new BeanPropertyMap(this, newProp, key, i);
   }

   public BeanPropertyMap assignIndexes() {
      int index = 0;
      int i = 1;

      for(int end = this._hashArea.length; i < end; i += 2) {
         SettableBeanProperty prop = (SettableBeanProperty)this._hashArea[i];
         if (prop != null) {
            prop.assignIndex(index++);
         }
      }

      return this;
   }

   public BeanPropertyMap renameAll(NameTransformer transformer) {
      if (transformer != null && transformer != NameTransformer.NOP) {
         int len = this._propsInOrder.length;
         ArrayList<SettableBeanProperty> newProps = new ArrayList(len);

         for(int i = 0; i < len; ++i) {
            SettableBeanProperty prop = this._propsInOrder[i];
            if (prop == null) {
               newProps.add(prop);
            } else {
               newProps.add(this._rename(prop, transformer));
            }
         }

         return new BeanPropertyMap(this._caseInsensitive, newProps, this._aliasDefs, this._locale);
      } else {
         return this;
      }
   }

   public BeanPropertyMap withoutProperties(Collection toExclude) {
      return this.withoutProperties(toExclude, (Collection)null);
   }

   public BeanPropertyMap withoutProperties(Collection toExclude, Collection toInclude) {
      if ((toExclude == null || toExclude.isEmpty()) && toInclude == null) {
         return this;
      } else {
         int len = this._propsInOrder.length;
         ArrayList<SettableBeanProperty> newProps = new ArrayList(len);

         for(int i = 0; i < len; ++i) {
            SettableBeanProperty prop = this._propsInOrder[i];
            if (prop != null && !IgnorePropertiesUtil.shouldIgnore(prop.getName(), toExclude, toInclude)) {
               newProps.add(prop);
            }
         }

         return new BeanPropertyMap(this._caseInsensitive, newProps, this._aliasDefs, this._locale);
      }
   }

   public void replace(SettableBeanProperty origProp, SettableBeanProperty newProp) {
      int i = 1;

      for(int end = this._hashArea.length; i < end; i += 2) {
         if (this._hashArea[i] == origProp) {
            this._hashArea[i] = newProp;
            this._propsInOrder[this._findFromOrdered(origProp)] = newProp;
            return;
         }
      }

      throw new NoSuchElementException("No entry '" + origProp.getName() + "' found, can't replace");
   }

   public void remove(SettableBeanProperty propToRm) {
      ArrayList<SettableBeanProperty> props = new ArrayList(this._size);
      String key = this.getPropertyName(propToRm);
      boolean found = false;
      int i = 1;

      for(int end = this._hashArea.length; i < end; i += 2) {
         SettableBeanProperty prop = (SettableBeanProperty)this._hashArea[i];
         if (prop != null) {
            if (!found) {
               found = key.equals(this._hashArea[i - 1]);
               if (found) {
                  this._propsInOrder[this._findFromOrdered(prop)] = null;
                  continue;
               }
            }

            props.add(prop);
         }
      }

      if (!found) {
         throw new NoSuchElementException("No entry '" + propToRm.getName() + "' found, can't remove");
      } else {
         this.init(props);
      }
   }

   public int size() {
      return this._size;
   }

   public boolean isCaseInsensitive() {
      return this._caseInsensitive;
   }

   public boolean hasAliases() {
      return !this._aliasDefs.isEmpty();
   }

   public Iterator iterator() {
      return this._properties().iterator();
   }

   private List _properties() {
      ArrayList<SettableBeanProperty> p = new ArrayList(this._size);
      int i = 1;

      for(int end = this._hashArea.length; i < end; i += 2) {
         SettableBeanProperty prop = (SettableBeanProperty)this._hashArea[i];
         if (prop != null) {
            p.add(prop);
         }
      }

      return p;
   }

   public SettableBeanProperty[] getPropertiesInInsertionOrder() {
      return this._propsInOrder;
   }

   protected final String getPropertyName(SettableBeanProperty prop) {
      return this._caseInsensitive ? prop.getName().toLowerCase(this._locale) : prop.getName();
   }

   public SettableBeanProperty find(int index) {
      int i = 1;

      for(int end = this._hashArea.length; i < end; i += 2) {
         SettableBeanProperty prop = (SettableBeanProperty)this._hashArea[i];
         if (prop != null && index == prop.getPropertyIndex()) {
            return prop;
         }
      }

      return null;
   }

   public SettableBeanProperty find(String key) {
      if (key == null) {
         throw new IllegalArgumentException("Cannot pass null property name");
      } else {
         if (this._caseInsensitive) {
            key = key.toLowerCase(this._locale);
         }

         int slot = key.hashCode() & this._hashMask;
         int ix = slot << 1;
         Object match = this._hashArea[ix];
         return match != key && !key.equals(match) ? this._find2(key, slot, match) : (SettableBeanProperty)this._hashArea[ix + 1];
      }
   }

   private final SettableBeanProperty _find2(String key, int slot, Object match) {
      if (match == null) {
         return this._findWithAlias((String)this._aliasMapping.get(key));
      } else {
         int hashSize = this._hashMask + 1;
         int ix = hashSize + (slot >> 1) << 1;
         match = this._hashArea[ix];
         if (key.equals(match)) {
            return (SettableBeanProperty)this._hashArea[ix + 1];
         } else {
            if (match != null) {
               int i = hashSize + (hashSize >> 1) << 1;

               for(int end = i + this._spillCount; i < end; i += 2) {
                  match = this._hashArea[i];
                  if (match == key || key.equals(match)) {
                     return (SettableBeanProperty)this._hashArea[i + 1];
                  }
               }
            }

            return this._findWithAlias((String)this._aliasMapping.get(key));
         }
      }
   }

   private SettableBeanProperty _findWithAlias(String keyFromAlias) {
      if (keyFromAlias == null) {
         return null;
      } else {
         int slot = this._hashCode(keyFromAlias);
         int ix = slot << 1;
         Object match = this._hashArea[ix];
         if (keyFromAlias.equals(match)) {
            return (SettableBeanProperty)this._hashArea[ix + 1];
         } else {
            return match == null ? null : this._find2ViaAlias(keyFromAlias, slot, match);
         }
      }
   }

   private SettableBeanProperty _find2ViaAlias(String key, int slot, Object match) {
      int hashSize = this._hashMask + 1;
      int ix = hashSize + (slot >> 1) << 1;
      match = this._hashArea[ix];
      if (key.equals(match)) {
         return (SettableBeanProperty)this._hashArea[ix + 1];
      } else {
         if (match != null) {
            int i = hashSize + (hashSize >> 1) << 1;

            for(int end = i + this._spillCount; i < end; i += 2) {
               match = this._hashArea[i];
               if (match == key || key.equals(match)) {
                  return (SettableBeanProperty)this._hashArea[i + 1];
               }
            }
         }

         return null;
      }
   }

   public boolean findDeserializeAndSet(JsonParser p, DeserializationContext ctxt, Object bean, String key) throws IOException {
      SettableBeanProperty prop = this.find(key);
      if (prop == null) {
         return false;
      } else {
         try {
            prop.deserializeAndSet(p, ctxt, bean);
         } catch (Exception e) {
            this.wrapAndThrow(e, bean, key, ctxt);
         }

         return true;
      }
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("Properties=[");
      int count = 0;

      for(SettableBeanProperty prop : this) {
         if (count++ > 0) {
            sb.append(", ");
         }

         sb.append(prop.getName());
         sb.append('(');
         sb.append(prop.getType());
         sb.append(')');
      }

      sb.append(']');
      if (!this._aliasDefs.isEmpty()) {
         sb.append("(aliases: ");
         sb.append(this._aliasDefs);
         sb.append(")");
      }

      return sb.toString();
   }

   protected SettableBeanProperty _rename(SettableBeanProperty prop, NameTransformer xf) {
      if (prop == null) {
         return prop;
      } else {
         String newName = xf.transform(prop.getName());
         prop = prop.withSimpleName(newName);
         JsonDeserializer<?> deser = prop.getValueDeserializer();
         if (deser != null) {
            JsonDeserializer<Object> newDeser = deser.unwrappingDeserializer(xf);
            if (newDeser != deser) {
               prop = prop.withValueDeserializer(newDeser);
            }
         }

         return prop;
      }
   }

   protected void wrapAndThrow(Throwable t, Object bean, String fieldName, DeserializationContext ctxt) throws IOException {
      while(t instanceof InvocationTargetException && t.getCause() != null) {
         t = t.getCause();
      }

      ClassUtil.throwIfError(t);
      boolean wrap = ctxt == null || ctxt.isEnabled(DeserializationFeature.WRAP_EXCEPTIONS);
      if (t instanceof IOException) {
         if (!wrap || !(t instanceof JacksonException)) {
            throw (IOException)t;
         }
      } else if (!wrap) {
         ClassUtil.throwIfRTE(t);
      }

      throw JsonMappingException.wrapWithPath(t, bean, fieldName);
   }

   private final int _findFromOrdered(SettableBeanProperty prop) {
      int i = 0;

      for(int end = this._propsInOrder.length; i < end; ++i) {
         if (this._propsInOrder[i] == prop) {
            return i;
         }
      }

      throw new IllegalStateException("Illegal state: property '" + prop.getName() + "' missing from _propsInOrder");
   }

   private final int _hashCode(String key) {
      return key.hashCode() & this._hashMask;
   }

   private Map _buildAliasMapping(Map defs, boolean caseInsensitive, Locale loc) {
      if (defs != null && !defs.isEmpty()) {
         Map<String, String> aliases = new HashMap();

         for(Map.Entry entry : defs.entrySet()) {
            String key = (String)entry.getKey();
            if (caseInsensitive) {
               key = key.toLowerCase(loc);
            }

            for(PropertyName pn : (List)entry.getValue()) {
               String mapped = pn.getSimpleName();
               if (caseInsensitive) {
                  mapped = mapped.toLowerCase(loc);
               }

               aliases.put(mapped, key);
            }
         }

         return aliases;
      } else {
         return Collections.emptyMap();
      }
   }
}
