package org.datanucleus.enhancer;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Currency;
import java.util.Date;
import java.util.EventListener;
import java.util.EventObject;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import org.datanucleus.enhancement.Persistable;
import org.datanucleus.enhancement.StateManager;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.exceptions.NucleusUserException;

public class EnhancementHelper {
   private static EnhancementHelper singletonHelper = new EnhancementHelper();
   private static Map registeredClasses = Collections.synchronizedMap(new HashMap());
   private static final Map authorizedStateManagerClasses = new WeakHashMap();
   private static final List listeners = new ArrayList();
   private static String dateFormatPattern;
   private static DateFormat dateFormat;
   static final Map stringConstructorMap;

   private EnhancementHelper() {
   }

   public static EnhancementHelper getInstance() {
      return singletonHelper;
   }

   public String[] getFieldNames(Class pcClass) {
      Meta meta = getMeta(pcClass);
      return meta.getFieldNames();
   }

   public Class[] getFieldTypes(Class pcClass) {
      Meta meta = getMeta(pcClass);
      return meta.getFieldTypes();
   }

   public byte[] getFieldFlags(Class pcClass) {
      Meta meta = getMeta(pcClass);
      return meta.getFieldFlags();
   }

   public Class getPersistableSuperclass(Class pcClass) {
      Meta meta = getMeta(pcClass);
      return meta.getPersistableSuperclass();
   }

   public Persistable newInstance(Class pcClass, StateManager sm) {
      Meta meta = getMeta(pcClass);
      Persistable pcInstance = meta.getPC();
      return pcInstance == null ? null : pcInstance.dnNewInstance(sm);
   }

   public Persistable newInstance(Class pcClass, StateManager sm, Object oid) {
      Meta meta = getMeta(pcClass);
      Persistable pcInstance = meta.getPC();
      return pcInstance == null ? null : pcInstance.dnNewInstance(sm, oid);
   }

   public Object newObjectIdInstance(Class pcClass) {
      Meta meta = getMeta(pcClass);
      Persistable pcInstance = meta.getPC();
      return pcInstance == null ? null : pcInstance.dnNewObjectIdInstance();
   }

   public Object newObjectIdInstance(Class pcClass, Object obj) {
      Meta meta = getMeta(pcClass);
      Persistable pcInstance = meta.getPC();
      return pcInstance == null ? null : pcInstance.dnNewObjectIdInstance(obj);
   }

   public void copyKeyFieldsToObjectId(Class pcClass, Persistable.ObjectIdFieldSupplier fm, Object oid) {
      Meta meta = getMeta(pcClass);
      Persistable pcInstance = meta.getPC();
      if (pcInstance == null) {
         throw (new NucleusException("Class " + pcClass.getName() + " has no identity!")).setFatal();
      } else {
         pcInstance.dnCopyKeyFieldsToObjectId(fm, oid);
      }
   }

   public void copyKeyFieldsFromObjectId(Class pcClass, Persistable.ObjectIdFieldConsumer fm, Object oid) {
      Meta meta = getMeta(pcClass);
      Persistable pcInstance = meta.getPC();
      if (pcInstance == null) {
         throw (new NucleusException("Class " + pcClass.getName() + " has no identity!")).setFatal();
      } else {
         pcInstance.dnCopyKeyFieldsFromObjectId(fm, oid);
      }
   }

   public static void registerClass(Class pcClass, String[] fieldNames, Class[] fieldTypes, byte[] fieldFlags, Class persistableSuperclass, Persistable pc) {
      if (pcClass == null) {
         throw new NullPointerException("Attempt to register class with null class type");
      } else {
         Meta meta = new Meta(fieldNames, fieldTypes, fieldFlags, persistableSuperclass, pc);
         registeredClasses.put(pcClass, meta);
         synchronized(listeners) {
            if (!listeners.isEmpty()) {
               RegisterClassEvent event = new RegisterClassEvent(singletonHelper, pcClass, fieldNames, fieldTypes, fieldFlags, persistableSuperclass);

               for(RegisterClassListener crl : listeners) {
                  if (crl != null) {
                     crl.registerClass(event);
                  }
               }
            }

         }
      }
   }

   public void unregisterClasses(ClassLoader cl) {
      synchronized(registeredClasses) {
         Iterator i = registeredClasses.keySet().iterator();

         while(i.hasNext()) {
            Class pcClass = (Class)i.next();
            if (pcClass != null && pcClass.getClassLoader() == cl) {
               i.remove();
            }
         }

      }
   }

   public void unregisterClass(Class pcClass) {
      if (pcClass == null) {
         throw new NullPointerException("Cannot unregisterClass on null");
      } else {
         registeredClasses.remove(pcClass);
      }
   }

   public void addRegisterClassListener(RegisterClassListener crl) {
      Set alreadyRegisteredClasses = null;
      HashSet var8;
      synchronized(listeners) {
         listeners.add(crl);
         var8 = new HashSet(registeredClasses.keySet());
      }

      for(Class pcClass : var8) {
         Meta meta = getMeta(pcClass);
         RegisterClassEvent event = new RegisterClassEvent(this, pcClass, meta.getFieldNames(), meta.getFieldTypes(), meta.getFieldFlags(), meta.getPersistableSuperclass());
         crl.registerClass(event);
      }

   }

   public void removeRegisterClassListener(RegisterClassListener crl) {
      synchronized(listeners) {
         listeners.remove(crl);
      }
   }

   public Collection getRegisteredClasses() {
      return Collections.unmodifiableCollection(registeredClasses.keySet());
   }

   private static Meta getMeta(Class pcClass) {
      Meta ret = (Meta)registeredClasses.get(pcClass);
      if (ret == null) {
         throw (new NucleusUserException("Cannot lookup meta info for " + pcClass + " - nothing found")).setFatal();
      } else {
         return ret;
      }
   }

   public static void registerAuthorizedStateManagerClass(Class smClass) {
      if (smClass == null) {
         throw new NullPointerException("Cannot register StateManager class with null input!");
      } else {
         synchronized(authorizedStateManagerClasses) {
            authorizedStateManagerClasses.put(smClass, (Object)null);
         }
      }
   }

   public static void registerAuthorizedStateManagerClasses(Collection smClasses) {
      synchronized(authorizedStateManagerClasses) {
         Iterator it = smClasses.iterator();

         while(it.hasNext()) {
            Object smClass = it.next();
            if (!(smClass instanceof Class)) {
               throw new ClassCastException("Cannot register StateManager class passing in object of type " + smClass.getClass().getName());
            }

            registerAuthorizedStateManagerClass((Class)it.next());
         }

      }
   }

   public static void checkAuthorizedStateManager(StateManager sm) {
      checkAuthorizedStateManagerClass(sm.getClass());
   }

   public static void checkAuthorizedStateManagerClass(Class smClass) {
      synchronized(authorizedStateManagerClasses) {
         if (!authorizedStateManagerClasses.containsKey(smClass)) {
            ;
         }
      }
   }

   public Object registerStringConstructor(Class cls, StringConstructor sc) {
      synchronized(stringConstructorMap) {
         return stringConstructorMap.put(cls, sc);
      }
   }

   public static Object construct(String className, String keyString) {
      try {
         Class<?> keyClass = Class.forName(className);
         StringConstructor stringConstructor;
         synchronized(stringConstructorMap) {
            stringConstructor = (StringConstructor)stringConstructorMap.get(keyClass);
         }

         return stringConstructor != null ? stringConstructor.construct(keyString) : keyClass.getConstructor(String.class).newInstance(keyString);
      } catch (Exception ex) {
         throw new NucleusUserException("Exception in Object identity String constructor", ex);
      }
   }

   static DateFormat getDateTimeInstance() {
      DateFormat result = null;

      try {
         result = (DateFormat)AccessController.doPrivileged(new PrivilegedAction() {
            public DateFormat run() {
               return DateFormat.getDateTimeInstance();
            }
         });
      } catch (Exception var2) {
         result = DateFormat.getInstance();
      }

      return result;
   }

   public synchronized void registerDateFormat(DateFormat df) {
      dateFormat = df;
      if (df instanceof SimpleDateFormat) {
         dateFormatPattern = ((SimpleDateFormat)df).toPattern();
      } else {
         dateFormatPattern = "Unknown message";
      }

   }

   static {
      singletonHelper.registerDateFormat(getDateTimeInstance());
      stringConstructorMap = new HashMap();
      singletonHelper.registerStringConstructor(Currency.class, new StringConstructor() {
         public Object construct(String s) {
            try {
               return Currency.getInstance(s);
            } catch (Exception ex) {
               throw new NucleusUserException("Exception in Currency identity String constructor", ex);
            }
         }
      });
      singletonHelper.registerStringConstructor(Locale.class, new StringConstructor() {
         public Object construct(String s) {
            try {
               int firstUnderbar = s.indexOf(95);
               if (firstUnderbar == -1) {
                  return new Locale(s);
               } else {
                  String lang = s.substring(0, firstUnderbar);
                  int secondUnderbar = s.indexOf(95, firstUnderbar + 1);
                  if (secondUnderbar == -1) {
                     String country = s.substring(firstUnderbar + 1);
                     return new Locale(lang, country);
                  } else {
                     String country = s.substring(firstUnderbar + 1, secondUnderbar);
                     String variant = s.substring(secondUnderbar + 1);
                     return new Locale(lang, country, variant);
                  }
               }
            } catch (Exception ex) {
               throw new NucleusUserException("Exception in Locale identity String constructor", ex);
            }
         }
      });
      singletonHelper.registerStringConstructor(Date.class, new StringConstructor() {
         public synchronized Object construct(String s) {
            try {
               return new Date(Long.parseLong(s));
            } catch (NumberFormatException var5) {
               ParsePosition pp = new ParsePosition(0);
               Date result = EnhancementHelper.dateFormat.parse(s, pp);
               if (result == null) {
                  throw new NucleusUserException("Exception in Date identity String constructor", new Object[]{s, pp.getErrorIndex(), EnhancementHelper.dateFormatPattern});
               } else {
                  return result;
               }
            }
         }
      });
   }

   public static class RegisterClassEvent extends EventObject {
      private static final long serialVersionUID = -8336171250765467347L;
      protected Class pcClass;
      protected String[] fieldNames;
      protected Class[] fieldTypes;
      protected byte[] fieldFlags;
      protected Class persistableSuperclass;

      public RegisterClassEvent(EnhancementHelper helper, Class registeredClass, String[] fieldNames, Class[] fieldTypes, byte[] fieldFlags, Class persistableSuperclass) {
         super(helper);
         this.pcClass = registeredClass;
         this.fieldNames = fieldNames;
         this.fieldTypes = fieldTypes;
         this.fieldFlags = fieldFlags;
         this.persistableSuperclass = persistableSuperclass;
      }

      public Class getRegisteredClass() {
         return this.pcClass;
      }

      public String[] getFieldNames() {
         return this.fieldNames;
      }

      public Class[] getFieldTypes() {
         return this.fieldTypes;
      }

      public byte[] getFieldFlags() {
         return this.fieldFlags;
      }

      public Class getPersistableSuperclass() {
         return this.persistableSuperclass;
      }
   }

   static class Meta {
      Persistable pc;
      String[] fieldNames;
      Class[] fieldTypes;
      byte[] fieldFlags;
      Class persistableSuperclass;

      Meta(String[] fieldNames, Class[] fieldTypes, byte[] fieldFlags, Class persistableSuperclass, Persistable pc) {
         this.fieldNames = fieldNames;
         this.fieldTypes = fieldTypes;
         this.fieldFlags = fieldFlags;
         this.persistableSuperclass = persistableSuperclass;
         this.pc = pc;
      }

      String[] getFieldNames() {
         return this.fieldNames;
      }

      Class[] getFieldTypes() {
         return this.fieldTypes;
      }

      byte[] getFieldFlags() {
         return this.fieldFlags;
      }

      Class getPersistableSuperclass() {
         return this.persistableSuperclass;
      }

      Persistable getPC() {
         return this.pc;
      }

      public String toString() {
         return "Meta-" + this.pc.getClass().getName();
      }
   }

   public interface RegisterClassListener extends EventListener {
      void registerClass(RegisterClassEvent var1);
   }

   public interface StringConstructor {
      Object construct(String var1);
   }
}
