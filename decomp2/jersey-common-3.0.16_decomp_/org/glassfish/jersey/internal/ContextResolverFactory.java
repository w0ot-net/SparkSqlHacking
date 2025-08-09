package org.glassfish.jersey.internal;

import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.ext.ContextResolver;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.glassfish.jersey.internal.inject.Binding;
import org.glassfish.jersey.internal.inject.Bindings;
import org.glassfish.jersey.internal.inject.InjectionManager;
import org.glassfish.jersey.internal.inject.InstanceBinding;
import org.glassfish.jersey.internal.util.ReflectionHelper;
import org.glassfish.jersey.internal.util.collection.KeyComparatorHashMap;
import org.glassfish.jersey.message.internal.MediaTypes;
import org.glassfish.jersey.message.internal.MessageBodyFactory;
import org.glassfish.jersey.spi.ContextResolvers;

public class ContextResolverFactory implements ContextResolvers {
   private final Map resolver;
   private final Map cache;
   private static final NullContextResolverAdapter NULL_CONTEXT_RESOLVER = new NullContextResolverAdapter();

   private ContextResolverFactory() {
      this.resolver = new HashMap(3);
      this.cache = new HashMap(3);
   }

   private void initialize(List contextResolvers) {
      Map<Type, Map<MediaType, List<ContextResolver>>> rs = new HashMap();

      for(ContextResolver provider : contextResolvers) {
         List<MediaType> ms = MediaTypes.createFrom((Produces)provider.getClass().getAnnotation(Produces.class));
         Type type = this.getParameterizedType(provider.getClass());
         Map<MediaType, List<ContextResolver>> mr = (Map)rs.get(type);
         if (mr == null) {
            mr = new HashMap();
            rs.put(type, mr);
         }

         for(MediaType m : ms) {
            List<ContextResolver> crl = (List)mr.get(m);
            if (crl == null) {
               crl = new ArrayList();
               mr.put(m, crl);
            }

            crl.add(provider);
         }
      }

      for(Map.Entry e : rs.entrySet()) {
         Map<MediaType, ContextResolver> mr = new KeyComparatorHashMap(4, MessageBodyFactory.MEDIA_TYPE_KEY_COMPARATOR);
         this.resolver.put(e.getKey(), mr);
         this.cache.put(e.getKey(), new ConcurrentHashMap(4));

         for(Map.Entry f : ((Map)e.getValue()).entrySet()) {
            mr.put(f.getKey(), this.reduce((List)f.getValue()));
         }
      }

   }

   private Type getParameterizedType(Class c) {
      ReflectionHelper.DeclaringClassInterfacePair p = ReflectionHelper.getClass(c, ContextResolver.class);
      Type[] as = ReflectionHelper.getParameterizedTypeArguments(p);
      return (Type)(as != null ? as[0] : Object.class);
   }

   private ContextResolver reduce(List r) {
      return (ContextResolver)(r.size() == 1 ? (ContextResolver)r.iterator().next() : new ContextResolverAdapter(r));
   }

   public ContextResolver resolve(Type t, MediaType m) {
      ConcurrentHashMap<MediaType, ContextResolver> crMapCache = (ConcurrentHashMap)this.cache.get(t);
      if (crMapCache == null) {
         return null;
      } else {
         if (m == null) {
            m = MediaType.WILDCARD_TYPE;
         }

         ContextResolver<T> cr = (ContextResolver)crMapCache.get(m);
         if (cr == null) {
            Map<MediaType, ContextResolver> crMap = (Map)this.resolver.get(t);
            if (m.isWildcardType()) {
               cr = (ContextResolver)crMap.get(MediaType.WILDCARD_TYPE);
               if (cr == null) {
                  cr = NULL_CONTEXT_RESOLVER;
               }
            } else if (m.isWildcardSubtype()) {
               ContextResolver<T> subTypeWildCard = (ContextResolver)crMap.get(m);
               ContextResolver<T> wildCard = (ContextResolver)crMap.get(MediaType.WILDCARD_TYPE);
               cr = (new ContextResolverAdapter(new ContextResolver[]{subTypeWildCard, wildCard})).reduce();
            } else {
               ContextResolver<T> type = (ContextResolver)crMap.get(m);
               ContextResolver<T> subTypeWildCard = (ContextResolver)crMap.get(new MediaType(m.getType(), "*"));
               ContextResolver<T> wildCard = (ContextResolver)crMap.get(MediaType.WILDCARD_TYPE);
               cr = (new ContextResolverAdapter(new ContextResolver[]{type, subTypeWildCard, wildCard})).reduce();
            }

            ContextResolver<T> _cr = (ContextResolver)crMapCache.putIfAbsent(m, cr);
            if (_cr != null) {
               cr = _cr;
            }
         }

         return cr != NULL_CONTEXT_RESOLVER ? cr : null;
      }
   }

   public static class ContextResolversConfigurator implements BootstrapConfigurator {
      private ContextResolverFactory contextResolverFactory;

      public void init(InjectionManager injectionManager, BootstrapBag bootstrapBag) {
         this.contextResolverFactory = new ContextResolverFactory();
         InstanceBinding<ContextResolverFactory> binding = (InstanceBinding)Bindings.service((Object)this.contextResolverFactory).to(ContextResolvers.class);
         injectionManager.register((Binding)binding);
      }

      public void postInit(InjectionManager injectionManager, BootstrapBag bootstrapBag) {
         this.contextResolverFactory.initialize(injectionManager.getAllInstances(ContextResolver.class));
         bootstrapBag.setContextResolvers(this.contextResolverFactory);
      }
   }

   private static final class NullContextResolverAdapter implements ContextResolver {
      private NullContextResolverAdapter() {
      }

      public Object getContext(Class type) {
         throw new UnsupportedOperationException("Not supported yet.");
      }
   }

   private static final class ContextResolverAdapter implements ContextResolver {
      private final ContextResolver[] cra;

      ContextResolverAdapter(ContextResolver... cra) {
         this(removeNull(cra));
      }

      ContextResolverAdapter(List crl) {
         this.cra = (ContextResolver[])crl.toArray(new ContextResolver[crl.size()]);
      }

      public Object getContext(Class objectType) {
         for(ContextResolver cr : this.cra) {
            Object c = cr.getContext(objectType);
            if (c != null) {
               return c;
            }
         }

         return null;
      }

      ContextResolver reduce() {
         if (this.cra.length == 0) {
            return ContextResolverFactory.NULL_CONTEXT_RESOLVER;
         } else {
            return (ContextResolver)(this.cra.length == 1 ? this.cra[0] : this);
         }
      }

      private static List removeNull(ContextResolver... cra) {
         List<ContextResolver> crl = new ArrayList(cra.length);

         for(ContextResolver cr : cra) {
            if (cr != null) {
               crl.add(cr);
            }
         }

         return crl;
      }
   }
}
