package org.glassfish.jaxb.runtime.v2;

import com.sun.istack.FinalArrayList;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.logging.Level;
import org.glassfish.jaxb.core.Utils;
import org.glassfish.jaxb.core.v2.Messages;
import org.glassfish.jaxb.runtime.api.JAXBRIContext;
import org.glassfish.jaxb.runtime.api.TypeReference;
import org.glassfish.jaxb.runtime.v2.model.annotation.RuntimeAnnotationReader;
import org.glassfish.jaxb.runtime.v2.runtime.JAXBContextImpl;
import org.glassfish.jaxb.runtime.v2.util.TypeCast;

public class ContextFactory {
   public static final String USE_JAXB_PROPERTIES = "_useJAXBProperties";

   private ContextFactory() {
   }

   public static JAXBContext createContext(Class[] classes, Map properties) throws JAXBException {
      MUtils.open(classes);
      Object var17;
      if (properties == null) {
         var17 = Collections.emptyMap();
      } else {
         var17 = new HashMap(properties);
      }

      String defaultNsUri = (String)getPropertyValue((Map)var17, "org.glassfish.jaxb.defaultNamespaceRemap", String.class);
      Boolean c14nSupport = (Boolean)getPropertyValue((Map)var17, "org.glassfish.jaxb.c14n", Boolean.class);
      if (c14nSupport == null) {
         c14nSupport = false;
      }

      Boolean disablesecurityProcessing = (Boolean)getPropertyValue((Map)var17, "org.glassfish.jaxb.disableXmlSecurity", Boolean.class);
      if (disablesecurityProcessing == null) {
         disablesecurityProcessing = false;
      }

      Boolean allNillable = (Boolean)getPropertyValue((Map)var17, "org.glassfish.jaxb.treatEverythingNillable", Boolean.class);
      if (allNillable == null) {
         allNillable = false;
      }

      Boolean retainPropertyInfo = (Boolean)getPropertyValue((Map)var17, "retainReferenceToInfo", Boolean.class);
      if (retainPropertyInfo == null) {
         retainPropertyInfo = false;
      }

      Boolean supressAccessorWarnings = (Boolean)getPropertyValue((Map)var17, "supressAccessorWarnings", Boolean.class);
      if (supressAccessorWarnings == null) {
         supressAccessorWarnings = false;
      }

      Boolean improvedXsiTypeHandling = (Boolean)getPropertyValue((Map)var17, "org.glassfish.jaxb.improvedXsiTypeHandling", Boolean.class);
      if (improvedXsiTypeHandling == null) {
         String improvedXsiSystemProperty = Utils.getSystemProperty("org.glassfish.jaxb.improvedXsiTypeHandling");
         if (improvedXsiSystemProperty == null) {
            improvedXsiTypeHandling = true;
         } else {
            improvedXsiTypeHandling = Boolean.valueOf(improvedXsiSystemProperty);
         }
      }

      Boolean xmlAccessorFactorySupport = (Boolean)getPropertyValue((Map)var17, "org.glassfish.jaxb.XmlAccessorFactory", Boolean.class);
      if (xmlAccessorFactorySupport == null) {
         xmlAccessorFactorySupport = false;
         Utils.getClassLogger().log(Level.FINE, "Property org.glassfish.jaxb.XmlAccessorFactoryis not active.  Using JAXB's implementation");
      }

      Boolean backupWithParentNamespace = (Boolean)getPropertyValue((Map)var17, "org.glassfish.jaxb.backupWithParentNamespace", Boolean.class);
      RuntimeAnnotationReader ar = (RuntimeAnnotationReader)getPropertyValue((Map)var17, JAXBRIContext.ANNOTATION_READER, RuntimeAnnotationReader.class);
      Collection<TypeReference> tr = (Collection)getPropertyValue((Map)var17, "org.glassfish.jaxb.typeReferences", Collection.class);
      if (tr == null) {
         tr = Collections.emptyList();
      }

      Map<Class, Class> subclassReplacements;
      try {
         subclassReplacements = TypeCast.checkedCast((Map)getPropertyValue((Map)var17, "org.glassfish.jaxb.subclassReplacements", Map.class), Class.class, Class.class);
      } catch (ClassCastException e) {
         throw new JAXBException(Messages.INVALID_TYPE_IN_MAP.format(new Object[0]), e);
      }

      Integer maxErrorsCount = (Integer)getPropertyValue((Map)var17, "org.glassfish.jaxb.maxErrorsCount", Integer.class);
      if (maxErrorsCount == null) {
         maxErrorsCount = 10;
      } else if (maxErrorsCount < 0) {
         maxErrorsCount = Integer.MAX_VALUE;
      }

      if (!((Map)var17).isEmpty()) {
         throw new JAXBException(Messages.UNSUPPORTED_PROPERTY.format(new Object[]{((Map)var17).keySet().iterator().next()}));
      } else {
         JAXBContextImpl.JAXBContextBuilder builder = new JAXBContextImpl.JAXBContextBuilder();
         builder.setClasses(classes);
         builder.setTypeRefs(tr);
         builder.setSubclassReplacements(subclassReplacements);
         builder.setDefaultNsUri(defaultNsUri);
         builder.setC14NSupport(c14nSupport);
         builder.setAnnotationReader(ar);
         builder.setXmlAccessorFactorySupport(xmlAccessorFactorySupport);
         builder.setAllNillable(allNillable);
         builder.setRetainPropertyInfo(retainPropertyInfo);
         builder.setSupressAccessorWarnings(supressAccessorWarnings);
         builder.setImprovedXsiTypeHandling(improvedXsiTypeHandling);
         builder.setDisableSecurityProcessing(disablesecurityProcessing);
         builder.setBackupWithParentNamespace(backupWithParentNamespace);
         builder.setMaxErrorsCount(maxErrorsCount);
         return builder.build();
      }
   }

   private static Object getPropertyValue(Map properties, String keyName, Class type) throws JAXBException {
      Object o = properties.get(keyName);
      if (o == null) {
         return null;
      } else {
         properties.remove(keyName);
         if (!type.isInstance(o)) {
            throw new JAXBException(Messages.INVALID_PROPERTY_VALUE.format(new Object[]{keyName, o}));
         } else {
            return type.cast(o);
         }
      }
   }

   /** @deprecated */
   @Deprecated
   public static JAXBRIContext createContext(Class[] classes, Collection typeRefs, Map subclassReplacements, String defaultNsUri, boolean c14nSupport, RuntimeAnnotationReader ar, boolean xmlAccessorFactorySupport, boolean allNillable, boolean retainPropertyInfo) throws JAXBException {
      return createContext(classes, typeRefs, subclassReplacements, defaultNsUri, c14nSupport, ar, xmlAccessorFactorySupport, allNillable, retainPropertyInfo, false);
   }

   /** @deprecated */
   @Deprecated
   public static JAXBRIContext createContext(Class[] classes, Collection typeRefs, Map subclassReplacements, String defaultNsUri, boolean c14nSupport, RuntimeAnnotationReader ar, boolean xmlAccessorFactorySupport, boolean allNillable, boolean retainPropertyInfo, boolean improvedXsiTypeHandling) throws JAXBException {
      MUtils.open(classes);
      JAXBContextImpl.JAXBContextBuilder builder = new JAXBContextImpl.JAXBContextBuilder();
      builder.setClasses(classes);
      builder.setTypeRefs(typeRefs);
      builder.setSubclassReplacements(subclassReplacements);
      builder.setDefaultNsUri(defaultNsUri);
      builder.setC14NSupport(c14nSupport);
      builder.setAnnotationReader(ar);
      builder.setXmlAccessorFactorySupport(xmlAccessorFactorySupport);
      builder.setAllNillable(allNillable);
      builder.setRetainPropertyInfo(retainPropertyInfo);
      builder.setImprovedXsiTypeHandling(improvedXsiTypeHandling);
      return builder.build();
   }

   public static JAXBContext createContext(String contextPath, ClassLoader classLoader, Map properties) throws JAXBException {
      FinalArrayList<Class> classes = new FinalArrayList();
      StringTokenizer tokens = new StringTokenizer(contextPath, ":");

      while(tokens.hasMoreTokens()) {
         boolean foundJaxbIndex = false;
         boolean foundObjectFactory = false;
         String pkg = tokens.nextToken();

         try {
            Class<?> o = classLoader.loadClass(pkg + ".ObjectFactory");
            classes.add(o);
            foundObjectFactory = true;
         } catch (ClassNotFoundException var12) {
         }

         List<Class> indexedClasses;
         try {
            indexedClasses = loadIndexedClasses(pkg, classLoader);
         } catch (IOException e) {
            throw new JAXBException(e);
         }

         if (indexedClasses != null) {
            classes.addAll(indexedClasses);
            foundJaxbIndex = true;
         }

         if (!foundObjectFactory && !foundJaxbIndex) {
            throw new JAXBException(Messages.BROKEN_CONTEXTPATH.format(new Object[]{pkg}));
         }
      }

      return createContext((Class[])classes.toArray(new Class[0]), properties);
   }

   private static List loadIndexedClasses(String pkg, ClassLoader classLoader) throws IOException, JAXBException {
      String resource = pkg.replace('.', '/') + "/jaxb.index";
      InputStream resourceAsStream = classLoader.getResourceAsStream(resource);
      if (resourceAsStream == null) {
         return null;
      } else {
         BufferedReader in = new BufferedReader(new InputStreamReader(resourceAsStream, StandardCharsets.UTF_8));

         FinalArrayList e;
         try {
            FinalArrayList<Class> classes = new FinalArrayList();
            String className = in.readLine();

            while(className != null) {
               className = className.trim();
               if (!className.startsWith("#") && className.length() != 0) {
                  if (className.endsWith(".class")) {
                     throw new JAXBException(Messages.ILLEGAL_ENTRY.format(new Object[]{className}));
                  }

                  try {
                     classes.add(classLoader.loadClass(pkg + "." + className));
                  } catch (ClassNotFoundException e) {
                     throw new JAXBException(Messages.ERROR_LOADING_CLASS.format(new Object[]{className, resource}), e);
                  }

                  className = in.readLine();
               } else {
                  className = in.readLine();
               }
            }

            e = classes;
         } catch (Throwable var10) {
            try {
               in.close();
            } catch (Throwable var8) {
               var10.addSuppressed(var8);
            }

            throw var10;
         }

         in.close();
         return e;
      }
   }
}
