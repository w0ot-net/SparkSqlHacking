package shaded.parquet.com.fasterxml.jackson.databind.jsontype.impl;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import shaded.parquet.com.fasterxml.jackson.annotation.JsonTypeInfo;
import shaded.parquet.com.fasterxml.jackson.databind.BeanDescription;
import shaded.parquet.com.fasterxml.jackson.databind.DatabindContext;
import shaded.parquet.com.fasterxml.jackson.databind.JavaType;
import shaded.parquet.com.fasterxml.jackson.databind.MapperFeature;
import shaded.parquet.com.fasterxml.jackson.databind.cfg.MapperConfig;
import shaded.parquet.com.fasterxml.jackson.databind.jsontype.NamedType;

public class SimpleNameIdResolver extends TypeIdResolverBase implements Serializable {
   private static final long serialVersionUID = 1L;
   protected final MapperConfig _config;
   protected final ConcurrentHashMap _typeToId;
   protected final Map _idToType;
   protected final boolean _caseInsensitive;

   protected SimpleNameIdResolver(MapperConfig config, JavaType baseType, ConcurrentHashMap typeToId, HashMap idToType) {
      super(baseType, config.getTypeFactory());
      this._config = config;
      this._typeToId = typeToId;
      this._idToType = idToType;
      this._caseInsensitive = config.isEnabled(MapperFeature.ACCEPT_CASE_INSENSITIVE_VALUES);
   }

   public static SimpleNameIdResolver construct(MapperConfig config, JavaType baseType, Collection subtypes, boolean forSer, boolean forDeser) {
      if (forSer == forDeser) {
         throw new IllegalArgumentException();
      } else {
         ConcurrentHashMap<String, String> typeToId;
         HashMap<String, JavaType> idToType;
         if (forSer) {
            typeToId = new ConcurrentHashMap();
            idToType = null;
         } else {
            idToType = new HashMap();
            typeToId = new ConcurrentHashMap(4);
         }

         boolean caseInsensitive = config.isEnabled(MapperFeature.ACCEPT_CASE_INSENSITIVE_VALUES);
         if (subtypes != null) {
            for(NamedType t : subtypes) {
               Class<?> cls = t.getType();
               String id = t.hasName() ? t.getName() : _defaultTypeId(cls);
               if (forSer) {
                  typeToId.put(cls.getName(), id);
               }

               if (forDeser) {
                  if (caseInsensitive) {
                     id = id.toLowerCase();
                  }

                  JavaType prev = (JavaType)idToType.get(id);
                  if (prev == null || !cls.isAssignableFrom(prev.getRawClass())) {
                     idToType.put(id, config.constructType(cls));
                  }
               }
            }
         }

         return new SimpleNameIdResolver(config, baseType, typeToId, idToType);
      }
   }

   public JsonTypeInfo.Id getMechanism() {
      return JsonTypeInfo.Id.SIMPLE_NAME;
   }

   public String idFromValue(Object value) {
      return this.idFromClass(value.getClass());
   }

   protected String idFromClass(Class clazz) {
      if (clazz == null) {
         return null;
      } else {
         String key = clazz.getName();
         String name = (String)this._typeToId.get(key);
         if (name == null) {
            Class<?> cls = this._typeFactory.constructType((Type)clazz).getRawClass();
            if (this._config.isAnnotationProcessingEnabled()) {
               BeanDescription beanDesc = this._config.introspectClassAnnotations(cls);
               name = this._config.getAnnotationIntrospector().findTypeName(beanDesc.getClassInfo());
            }

            if (name == null) {
               name = _defaultTypeId(cls);
            }

            this._typeToId.put(key, name);
         }

         return name;
      }
   }

   public String idFromValueAndType(Object value, Class type) {
      return value == null ? this.idFromClass(type) : this.idFromValue(value);
   }

   public JavaType typeFromId(DatabindContext context, String id) {
      return this._typeFromId(id);
   }

   protected JavaType _typeFromId(String id) {
      if (this._caseInsensitive) {
         id = id.toLowerCase();
      }

      return (JavaType)this._idToType.get(id);
   }

   public String getDescForKnownTypeIds() {
      TreeSet<String> ids = new TreeSet();

      for(Map.Entry entry : this._idToType.entrySet()) {
         if (((JavaType)entry.getValue()).isConcrete()) {
            ids.add(entry.getKey());
         }
      }

      return ids.toString();
   }

   public String toString() {
      return String.format("[%s; id-to-type=%s]", this.getClass().getName(), this._idToType);
   }

   protected static String _defaultTypeId(Class cls) {
      String n = cls.getName();
      int ix = Math.max(n.lastIndexOf(46), n.lastIndexOf(36));
      return ix < 0 ? n : n.substring(ix + 1);
   }
}
