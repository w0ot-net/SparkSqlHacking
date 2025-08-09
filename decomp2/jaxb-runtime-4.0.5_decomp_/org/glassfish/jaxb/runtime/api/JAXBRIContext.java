package org.glassfish.jaxb.runtime.api;

import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.SchemaOutputResolver;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.namespace.QName;
import javax.xml.transform.Result;
import org.glassfish.jaxb.core.api.impl.NameConverter;
import org.glassfish.jaxb.runtime.v2.ContextFactory;
import org.glassfish.jaxb.runtime.v2.model.annotation.RuntimeAnnotationReader;
import org.glassfish.jaxb.runtime.v2.model.runtime.RuntimeTypeInfoSet;

public abstract class JAXBRIContext extends JAXBContext {
   public static final String DEFAULT_NAMESPACE_REMAP = "org.glassfish.jaxb.defaultNamespaceRemap";
   public static final String TYPE_REFERENCES = "org.glassfish.jaxb.typeReferences";
   public static final String CANONICALIZATION_SUPPORT = "org.glassfish.jaxb.c14n";
   public static final String TREAT_EVERYTHING_NILLABLE = "org.glassfish.jaxb.treatEverythingNillable";
   public static final String ANNOTATION_READER = RuntimeAnnotationReader.class.getName();
   public static final String ENABLE_XOP = "org.glassfish.jaxb.XOP";
   public static final String SUBCLASS_REPLACEMENTS = "org.glassfish.jaxb.subclassReplacements";
   public static final String XMLACCESSORFACTORY_SUPPORT = "org.glassfish.jaxb.XmlAccessorFactory";
   public static final String RETAIN_REFERENCE_TO_INFO = "retainReferenceToInfo";
   public static final String SUPRESS_ACCESSOR_WARNINGS = "supressAccessorWarnings";
   public static final String IMPROVED_XSI_TYPE_HANDLING = "org.glassfish.jaxb.improvedXsiTypeHandling";
   public static final String DISABLE_XML_SECURITY = "org.glassfish.jaxb.disableXmlSecurity";
   public static final String BACKUP_WITH_PARENT_NAMESPACE = "org.glassfish.jaxb.backupWithParentNamespace";
   public static final String MAX_ERRORS = "org.glassfish.jaxb.maxErrorsCount";

   protected JAXBRIContext() {
   }

   public static JAXBRIContext newInstance(@NotNull Class[] classes, @Nullable Collection typeRefs, @Nullable Map subclassReplacements, @Nullable String defaultNamespaceRemap, boolean c14nSupport, @Nullable RuntimeAnnotationReader ar) throws JAXBException {
      return newInstance(classes, typeRefs, subclassReplacements, defaultNamespaceRemap, c14nSupport, ar, false, false, false, false);
   }

   public static JAXBRIContext newInstance(@NotNull Class[] classes, @Nullable Collection typeRefs, @Nullable Map subclassReplacements, @Nullable String defaultNamespaceRemap, boolean c14nSupport, @Nullable RuntimeAnnotationReader ar, boolean xmlAccessorFactorySupport, boolean allNillable, boolean retainPropertyInfo, boolean supressAccessorWarnings) throws JAXBException {
      Map<String, Object> properties = new HashMap();
      if (typeRefs != null) {
         properties.put("org.glassfish.jaxb.typeReferences", typeRefs);
      }

      if (subclassReplacements != null) {
         properties.put("org.glassfish.jaxb.subclassReplacements", subclassReplacements);
      }

      if (defaultNamespaceRemap != null) {
         properties.put("org.glassfish.jaxb.defaultNamespaceRemap", defaultNamespaceRemap);
      }

      if (ar != null) {
         properties.put(ANNOTATION_READER, ar);
      }

      properties.put("org.glassfish.jaxb.c14n", c14nSupport);
      properties.put("org.glassfish.jaxb.XmlAccessorFactory", xmlAccessorFactorySupport);
      properties.put("org.glassfish.jaxb.treatEverythingNillable", allNillable);
      properties.put("retainReferenceToInfo", retainPropertyInfo);
      properties.put("supressAccessorWarnings", supressAccessorWarnings);
      return (JAXBRIContext)ContextFactory.createContext(classes, properties);
   }

   public abstract boolean hasSwaRef();

   @Nullable
   public abstract QName getElementName(@NotNull Object var1) throws JAXBException;

   @Nullable
   public abstract QName getElementName(@NotNull Class var1) throws JAXBException;

   public abstract Bridge createBridge(@NotNull TypeReference var1);

   public abstract RawAccessor getElementPropertyAccessor(Class var1, String var2, String var3) throws JAXBException;

   @NotNull
   public abstract List getKnownNamespaceURIs();

   public abstract void generateSchema(@NotNull SchemaOutputResolver var1) throws IOException;

   public abstract QName getTypeName(@NotNull TypeReference var1);

   @NotNull
   public abstract String getBuildId();

   public abstract void generateEpisode(Result var1);

   public abstract RuntimeTypeInfoSet getRuntimeTypeInfoSet();

   @NotNull
   public static String mangleNameToVariableName(@NotNull String localName) {
      return NameConverter.standard.toVariableName(localName);
   }

   @NotNull
   public static String mangleNameToClassName(@NotNull String localName) {
      return NameConverter.standard.toClassName(localName);
   }

   @NotNull
   public static String mangleNameToPropertyName(@NotNull String localName) {
      return NameConverter.standard.toPropertyName(localName);
   }

   @Nullable
   public static Type getBaseType(@NotNull Type type, @NotNull Class baseType) {
      return (Type)Utils.REFLECTION_NAVIGATOR.getBaseClass(type, baseType);
   }
}
