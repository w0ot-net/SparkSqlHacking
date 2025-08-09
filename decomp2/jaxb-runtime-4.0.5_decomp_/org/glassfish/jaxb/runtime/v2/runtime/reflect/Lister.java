package org.glassfish.jaxb.runtime.v2.runtime.reflect;

import com.sun.istack.SAXException2;
import jakarta.xml.bind.JAXBException;
import java.lang.ref.WeakReference;
import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.TreeSet;
import java.util.WeakHashMap;
import java.util.concurrent.Callable;
import org.glassfish.jaxb.core.v2.ClassFactory;
import org.glassfish.jaxb.core.v2.TODO;
import org.glassfish.jaxb.core.v2.model.core.Adapter;
import org.glassfish.jaxb.core.v2.model.core.ID;
import org.glassfish.jaxb.core.v2.runtime.unmarshaller.LocatorEx;
import org.glassfish.jaxb.runtime.api.AccessorException;
import org.glassfish.jaxb.runtime.v2.runtime.XMLSerializer;
import org.glassfish.jaxb.runtime.v2.runtime.unmarshaller.Patcher;
import org.glassfish.jaxb.runtime.v2.runtime.unmarshaller.UnmarshallingContext;
import org.xml.sax.SAXException;

public abstract class Lister {
   private static final Map arrayListerCache = Collections.synchronizedMap(new WeakHashMap());
   static final Map primitiveArrayListers = new HashMap();
   public static final Lister ERROR;
   private static final ListIterator EMPTY_ITERATOR;
   private static final Class[] COLLECTION_IMPL_CLASSES;

   protected Lister() {
   }

   public abstract ListIterator iterator(Object var1, XMLSerializer var2);

   public abstract Object startPacking(Object var1, Accessor var2) throws AccessorException;

   public abstract void addToPack(Object var1, Object var2) throws AccessorException;

   public abstract void endPacking(Object var1, Object var2, Accessor var3) throws AccessorException;

   public abstract void reset(Object var1, Accessor var2) throws AccessorException;

   public static Lister create(Type fieldType, ID idness, Adapter adapter) {
      Class rawType = (Class)Utils.REFLECTION_NAVIGATOR.erasure(fieldType);
      Class itemType;
      Lister l;
      if (rawType.isArray()) {
         itemType = rawType.getComponentType();
         l = getArrayLister(itemType);
      } else {
         if (!Collection.class.isAssignableFrom(rawType)) {
            return null;
         }

         Type bt = (Type)Utils.REFLECTION_NAVIGATOR.getBaseClass(fieldType, Collection.class);
         if (bt instanceof ParameterizedType) {
            itemType = (Class)Utils.REFLECTION_NAVIGATOR.erasure(((ParameterizedType)bt).getActualTypeArguments()[0]);
         } else {
            itemType = Object.class;
         }

         l = new CollectionLister(getImplClass(rawType));
      }

      if (idness == ID.IDREF) {
         l = new IDREFS(l, itemType);
      }

      if (adapter != null) {
         l = new AdaptedLister(l, (Class)adapter.adapterType);
      }

      return l;
   }

   private static Class getImplClass(Class fieldType) {
      return ClassFactory.inferImplClass(fieldType, COLLECTION_IMPL_CLASSES);
   }

   private static Lister getArrayLister(Class componentType) {
      Lister l = null;
      if (componentType.isPrimitive()) {
         l = (Lister)primitiveArrayListers.get(componentType);
      } else {
         WeakReference<Lister> wr = (WeakReference)arrayListerCache.get(componentType);
         if (wr != null) {
            l = (Lister)wr.get();
         }

         if (l == null) {
            l = new ArrayLister(componentType);
            arrayListerCache.put(componentType, new WeakReference(l));
         }
      }

      assert l != null;

      return l;
   }

   public static Lister getErrorInstance() {
      return ERROR;
   }

   static {
      PrimitiveArrayListerBoolean.register();
      PrimitiveArrayListerByte.register();
      PrimitiveArrayListerCharacter.register();
      PrimitiveArrayListerDouble.register();
      PrimitiveArrayListerFloat.register();
      PrimitiveArrayListerInteger.register();
      PrimitiveArrayListerLong.register();
      PrimitiveArrayListerShort.register();
      ERROR = new Lister() {
         public ListIterator iterator(Object o, XMLSerializer context) {
            return Lister.EMPTY_ITERATOR;
         }

         public Object startPacking(Object o, Accessor accessor) {
            return null;
         }

         public void addToPack(Object o, Object o1) {
         }

         public void endPacking(Object o, Object o1, Accessor accessor) {
         }

         public void reset(Object o, Accessor accessor) {
         }
      };
      EMPTY_ITERATOR = new ListIterator() {
         public boolean hasNext() {
            return false;
         }

         public Object next() {
            throw new IllegalStateException();
         }
      };
      COLLECTION_IMPL_CLASSES = new Class[]{ArrayList.class, LinkedList.class, HashSet.class, TreeSet.class, Stack.class};
   }

   private static final class ArrayLister extends Lister {
      private final Class itemType;

      public ArrayLister(Class itemType) {
         this.itemType = itemType;
      }

      public ListIterator iterator(final Object[] objects, XMLSerializer context) {
         return new ListIterator() {
            int idx = 0;

            public boolean hasNext() {
               return this.idx < objects.length;
            }

            public Object next() {
               return objects[this.idx++];
            }
         };
      }

      public Pack startPacking(Object current, Accessor acc) {
         return new Pack(this.itemType);
      }

      public void addToPack(Pack objects, Object o) {
         objects.add(o);
      }

      public void endPacking(Pack pack, Object bean, Accessor acc) throws AccessorException {
         acc.set(bean, pack.build());
      }

      public void reset(Object o, Accessor acc) throws AccessorException {
         acc.set(o, Array.newInstance(this.itemType, 0));
      }
   }

   public static final class Pack extends ArrayList {
      private static final long serialVersionUID = 8543908122652908717L;
      private final Class itemType;

      public Pack(Class itemType) {
         this.itemType = itemType;
      }

      public Object[] build() {
         return super.toArray(Array.newInstance(this.itemType, this.size()));
      }
   }

   public static final class CollectionLister extends Lister {
      private final Class implClass;

      public CollectionLister(Class implClass) {
         this.implClass = implClass;
      }

      public ListIterator iterator(Collection collection, XMLSerializer context) {
         final Iterator<?> itr = collection.iterator();
         return new ListIterator() {
            public boolean hasNext() {
               return itr.hasNext();
            }

            public Object next() {
               return itr.next();
            }
         };
      }

      public Collection startPacking(Object bean, Accessor acc) throws AccessorException {
         T collection = (T)((Collection)acc.get(bean));
         if (collection == null) {
            collection = (T)((Collection)ClassFactory.create(this.implClass));
            if (!acc.isAdapted()) {
               acc.set(bean, collection);
            }
         }

         collection.clear();
         return collection;
      }

      public void addToPack(Collection collection, Object o) {
         collection.add(o);
      }

      public void endPacking(Collection collection, Object bean, Accessor acc) throws AccessorException {
         try {
            if (acc.isAdapted()) {
               acc.set(bean, collection);
            }
         } catch (AccessorException ae) {
            if (acc.isAdapted()) {
               throw ae;
            }
         }

      }

      public void reset(Object bean, Accessor acc) throws AccessorException {
         T collection = (T)((Collection)acc.get(bean));
         if (collection != null) {
            collection.clear();
         }
      }
   }

   private static final class IDREFS extends Lister {
      private final Lister core;
      private final Class itemType;

      public IDREFS(Lister core, Class itemType) {
         this.core = core;
         this.itemType = itemType;
      }

      public ListIterator iterator(Object prop, XMLSerializer context) {
         ListIterator i = this.core.iterator(prop, context);
         return new IDREFSIterator(i, context);
      }

      public Pack startPacking(Object bean, Accessor acc) {
         return new Pack(bean, acc);
      }

      public void addToPack(Pack pack, String item) {
         pack.add(item);
      }

      public void endPacking(Pack pack, Object bean, Accessor acc) {
      }

      public void reset(Object bean, Accessor acc) throws AccessorException {
         this.core.reset(bean, acc);
      }

      private class Pack implements Patcher {
         private final Object bean;
         private final List idrefs = new ArrayList();
         private final UnmarshallingContext context;
         private final Accessor acc;
         private final LocatorEx location;

         public Pack(Object bean, Accessor acc) {
            this.bean = bean;
            this.acc = acc;
            this.context = UnmarshallingContext.getInstance();
            this.location = new LocatorEx.Snapshot(this.context.getLocator());
            this.context.addPatcher(this);
         }

         public void add(String item) {
            this.idrefs.add(item);
         }

         public void run() throws SAXException {
            try {
               Object pack = IDREFS.this.core.startPacking(this.bean, this.acc);

               for(String id : this.idrefs) {
                  Callable callable = this.context.getObjectFromId(id, IDREFS.this.itemType);

                  Object t;
                  try {
                     t = callable != null ? callable.call() : null;
                  } catch (SAXException e) {
                     throw e;
                  } catch (Exception e) {
                     throw new SAXException2(e);
                  }

                  if (t == null) {
                     this.context.errorUnresolvedIDREF(this.bean, id, this.location);
                  } else {
                     TODO.prototype();
                     IDREFS.this.core.addToPack(pack, t);
                  }
               }

               IDREFS.this.core.endPacking(pack, this.bean, this.acc);
            } catch (AccessorException e) {
               this.context.handleError((Exception)e);
            }

         }
      }
   }

   public static final class IDREFSIterator implements ListIterator {
      private final ListIterator i;
      private final XMLSerializer context;
      private Object last;

      private IDREFSIterator(ListIterator i, XMLSerializer context) {
         this.i = i;
         this.context = context;
      }

      public boolean hasNext() {
         return this.i.hasNext();
      }

      public Object last() {
         return this.last;
      }

      public String next() throws SAXException, JAXBException {
         this.last = this.i.next();
         String id = this.context.grammar.getBeanInfo(this.last, true).getId(this.last, this.context);
         if (id == null) {
            this.context.errorMissingId(this.last);
         }

         return id;
      }
   }
}
