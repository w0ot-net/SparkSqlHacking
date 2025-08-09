package org.glassfish.jaxb.runtime.v2.runtime;

import com.sun.istack.NotNull;
import com.sun.istack.Pool;
import com.sun.xml.txw2.output.ResultFactory;
import jakarta.xml.bind.Binder;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.JAXBIntrospector;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.SchemaOutputResolver;
import jakarta.xml.bind.Unmarshaller;
import jakarta.xml.bind.annotation.XmlAttachmentRef;
import jakarta.xml.bind.annotation.XmlList;
import jakarta.xml.bind.annotation.XmlSchema;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import org.glassfish.jaxb.core.api.ErrorListener;
import org.glassfish.jaxb.core.unmarshaller.DOMScanner;
import org.glassfish.jaxb.core.util.Which;
import org.glassfish.jaxb.core.v2.model.annotation.Locatable;
import org.glassfish.jaxb.core.v2.model.core.Adapter;
import org.glassfish.jaxb.core.v2.model.core.NonElement;
import org.glassfish.jaxb.core.v2.model.core.Ref;
import org.glassfish.jaxb.core.v2.model.nav.Navigator;
import org.glassfish.jaxb.core.v2.runtime.RuntimeUtil;
import org.glassfish.jaxb.core.v2.util.EditDistance;
import org.glassfish.jaxb.core.v2.util.XmlFactory;
import org.glassfish.jaxb.runtime.api.AccessorException;
import org.glassfish.jaxb.runtime.api.Bridge;
import org.glassfish.jaxb.runtime.api.CompositeStructure;
import org.glassfish.jaxb.runtime.api.JAXBRIContext;
import org.glassfish.jaxb.runtime.api.RawAccessor;
import org.glassfish.jaxb.runtime.api.TypeReference;
import org.glassfish.jaxb.runtime.v2.model.annotation.RuntimeAnnotationReader;
import org.glassfish.jaxb.runtime.v2.model.annotation.RuntimeInlineAnnotationReader;
import org.glassfish.jaxb.runtime.v2.model.impl.RuntimeBuiltinLeafInfoImpl;
import org.glassfish.jaxb.runtime.v2.model.impl.RuntimeModelBuilder;
import org.glassfish.jaxb.runtime.v2.model.runtime.RuntimeArrayInfo;
import org.glassfish.jaxb.runtime.v2.model.runtime.RuntimeBuiltinLeafInfo;
import org.glassfish.jaxb.runtime.v2.model.runtime.RuntimeClassInfo;
import org.glassfish.jaxb.runtime.v2.model.runtime.RuntimeElementInfo;
import org.glassfish.jaxb.runtime.v2.model.runtime.RuntimeEnumLeafInfo;
import org.glassfish.jaxb.runtime.v2.model.runtime.RuntimeLeafInfo;
import org.glassfish.jaxb.runtime.v2.model.runtime.RuntimeTypeInfo;
import org.glassfish.jaxb.runtime.v2.model.runtime.RuntimeTypeInfoSet;
import org.glassfish.jaxb.runtime.v2.runtime.output.Encoded;
import org.glassfish.jaxb.runtime.v2.runtime.property.AttributeProperty;
import org.glassfish.jaxb.runtime.v2.runtime.property.Property;
import org.glassfish.jaxb.runtime.v2.runtime.reflect.Accessor;
import org.glassfish.jaxb.runtime.v2.runtime.unmarshaller.Loader;
import org.glassfish.jaxb.runtime.v2.runtime.unmarshaller.TagName;
import org.glassfish.jaxb.runtime.v2.runtime.unmarshaller.UnmarshallerImpl;
import org.glassfish.jaxb.runtime.v2.runtime.unmarshaller.UnmarshallingContext;
import org.glassfish.jaxb.runtime.v2.schemagen.XmlSchemaGenerator;
import org.glassfish.jaxb.runtime.v2.util.QNameMap;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public final class JAXBContextImpl extends JAXBRIContext {
   private final Map bridges = new LinkedHashMap();
   private static DocumentBuilder db;
   private final QNameMap rootMap = new QNameMap();
   private final HashMap typeMap = new HashMap();
   private final Map beanInfoMap = new LinkedHashMap();
   protected Map beanInfos = new LinkedHashMap();
   private final Map elements = new LinkedHashMap();
   public final Pool marshallerPool = new Pool.Impl() {
      @NotNull
      protected Marshaller create() {
         return JAXBContextImpl.this.createMarshaller();
      }
   };
   public final Pool unmarshallerPool = new Pool.Impl() {
      @NotNull
      protected Unmarshaller create() {
         return JAXBContextImpl.this.createUnmarshaller();
      }
   };
   public NameBuilder nameBuilder = new NameBuilder();
   public final NameList nameList;
   private final String defaultNsUri;
   private final Class[] classes;
   protected final boolean c14nSupport;
   public final boolean xmlAccessorFactorySupport;
   public final boolean allNillable;
   public final boolean retainPropertyInfo;
   public final boolean supressAccessorWarnings;
   public final boolean improvedXsiTypeHandling;
   public final boolean disableSecurityProcessing;
   private WeakReference typeInfoSetCache;
   @NotNull
   private RuntimeAnnotationReader annotationReader;
   private boolean hasSwaRef;
   @NotNull
   private final Map subclassReplacements;
   public final boolean fastBoot;
   private Set xmlNsSet = null;
   public Boolean backupWithParentNamespace = null;
   public final int maxErrorsCount;
   private Encoded[] utf8nameTable;
   private static final Comparator QNAME_COMPARATOR = new Comparator() {
      public int compare(QName lhs, QName rhs) {
         int r = lhs.getLocalPart().compareTo(rhs.getLocalPart());
         return r != 0 ? r : lhs.getNamespaceURI().compareTo(rhs.getNamespaceURI());
      }
   };

   public Set getXmlNsSet() {
      return this.xmlNsSet;
   }

   private JAXBContextImpl(JAXBContextBuilder builder) throws JAXBException {
      this.defaultNsUri = builder.defaultNsUri;
      this.retainPropertyInfo = builder.retainPropertyInfo;
      this.annotationReader = builder.annotationReader;
      this.subclassReplacements = builder.subclassReplacements;
      this.c14nSupport = builder.c14nSupport;
      this.classes = builder.classes;
      this.xmlAccessorFactorySupport = builder.xmlAccessorFactorySupport;
      this.allNillable = builder.allNillable;
      this.supressAccessorWarnings = builder.supressAccessorWarnings;
      this.improvedXsiTypeHandling = builder.improvedXsiTypeHandling;
      this.disableSecurityProcessing = builder.disableSecurityProcessing;
      this.backupWithParentNamespace = builder.backupWithParentNamespace;
      this.maxErrorsCount = builder.maxErrorsCount;
      Collection<TypeReference> typeRefs = builder.typeRefs;

      boolean fastB;
      try {
         fastB = Boolean.getBoolean(JAXBContextImpl.class.getName() + ".fastBoot");
      } catch (SecurityException var14) {
         fastB = false;
      }

      this.fastBoot = fastB;
      RuntimeTypeInfoSet typeSet = this.getTypeInfoSet();
      this.elements.put((Object)null, new LinkedHashMap());

      for(RuntimeBuiltinLeafInfo leaf : RuntimeBuiltinLeafInfoImpl.builtinBeanInfos) {
         LeafBeanInfoImpl<?> bi = new LeafBeanInfoImpl(this, leaf);
         this.beanInfoMap.put(leaf.getClazz(), bi);

         for(QName t : bi.getTypeNames()) {
            this.typeMap.put(t, bi);
         }
      }

      for(RuntimeEnumLeafInfo e : typeSet.enums().values()) {
         JaxBeanInfo<?> bi = this.getOrCreate(e);

         for(QName qn : bi.getTypeNames()) {
            this.typeMap.put(qn, bi);
         }

         if (e.isElement()) {
            this.rootMap.put((QName)e.getElementName(), bi);
         }
      }

      for(RuntimeArrayInfo a : typeSet.arrays().values()) {
         JaxBeanInfo<?> ai = this.getOrCreate(a);

         for(QName qn : ai.getTypeNames()) {
            this.typeMap.put(qn, ai);
         }
      }

      for(Map.Entry e : typeSet.beans().entrySet()) {
         ClassBeanInfoImpl<?> bi = this.getOrCreate((RuntimeClassInfo)e.getValue());
         XmlSchema xs = (XmlSchema)this.annotationReader.getPackageAnnotation(XmlSchema.class, (Class)e.getKey(), (Locatable)null);
         if (xs != null && xs.xmlns() != null && xs.xmlns().length > 0) {
            if (this.xmlNsSet == null) {
               this.xmlNsSet = new HashSet();
            }

            this.xmlNsSet.addAll(Arrays.asList(xs.xmlns()));
         }

         if (bi.isElement()) {
            this.rootMap.put((QName)((RuntimeClassInfo)e.getValue()).getElementName(), bi);
         }

         for(QName qn : bi.getTypeNames()) {
            this.typeMap.put(qn, bi);
         }
      }

      for(RuntimeElementInfo n : typeSet.getAllElements()) {
         ElementBeanInfoImpl bi = this.getOrCreate(n);
         if (n.getScope() == null) {
            this.rootMap.put((QName)n.getElementName(), bi);
         }

         RuntimeClassInfo scope = n.getScope();
         Class scopeClazz = scope == null ? null : (Class)scope.getClazz();
         Map<QName, ElementBeanInfoImpl> m = (Map)this.elements.computeIfAbsent(scopeClazz, (k) -> new LinkedHashMap());
         m.put(n.getElementName(), bi);
      }

      this.beanInfoMap.put(JAXBElement.class, new ElementBeanInfoImpl(this));
      this.beanInfoMap.put(CompositeStructure.class, new CompositeStructureBeanInfo(this));
      this.getOrCreate((RuntimeTypeInfo)typeSet.getAnyTypeInfo());

      for(JaxBeanInfo bi : this.beanInfos.values()) {
         bi.link(this);
      }

      for(Map.Entry e : RuntimeUtil.primitiveToBox.entrySet()) {
         this.beanInfoMap.put((Class)e.getKey(), (JaxBeanInfo)this.beanInfoMap.get(e.getValue()));
      }

      Navigator<Type, Class, Field, Method> nav = typeSet.getNavigator();

      for(TypeReference tr : typeRefs) {
         XmlJavaTypeAdapter xjta = (XmlJavaTypeAdapter)tr.get(XmlJavaTypeAdapter.class);
         Adapter<Type, Class> a = null;
         XmlList xl = (XmlList)tr.get(XmlList.class);
         Class erasedType = (Class)nav.erasure(tr.type);
         if (xjta != null) {
            a = new Adapter(xjta.value(), nav);
         }

         if (tr.get(XmlAttachmentRef.class) != null) {
            a = new Adapter(SwaRefAdapter.class, nav);
            this.hasSwaRef = true;
         }

         if (a != null) {
            erasedType = (Class)nav.erasure((Type)a.defaultType);
         }

         Name name = this.nameBuilder.createElementName(tr.tagName);
         InternalBridge bridge;
         if (xl == null) {
            bridge = new BridgeImpl(this, name, this.getBeanInfo(erasedType, true), tr);
         } else {
            bridge = new BridgeImpl(this, name, new ValueListBeanInfoImpl(this, erasedType), tr);
         }

         if (a != null) {
            bridge = new BridgeAdapter(bridge, (Class)a.adapterType);
         }

         this.bridges.put(tr, bridge);
      }

      this.nameList = this.nameBuilder.conclude();

      for(JaxBeanInfo bi : this.beanInfos.values()) {
         bi.wrapUp();
      }

      this.nameBuilder = null;
      this.beanInfos = null;
   }

   public boolean hasSwaRef() {
      return this.hasSwaRef;
   }

   public RuntimeTypeInfoSet getRuntimeTypeInfoSet() {
      try {
         return this.getTypeInfoSet();
      } catch (IllegalAnnotationsException e) {
         throw new AssertionError(e);
      }
   }

   public RuntimeTypeInfoSet getTypeInfoSet() throws IllegalAnnotationsException {
      if (this.typeInfoSetCache != null) {
         RuntimeTypeInfoSet r = (RuntimeTypeInfoSet)this.typeInfoSetCache.get();
         if (r != null) {
            return r;
         }
      }

      RuntimeModelBuilder builder = new RuntimeModelBuilder(this, this.annotationReader, this.subclassReplacements, this.defaultNsUri);
      IllegalAnnotationsException.Builder errorHandler = new IllegalAnnotationsException.Builder();
      builder.setErrorHandler(errorHandler);

      for(Class c : this.classes) {
         if (c != CompositeStructure.class) {
            builder.getTypeInfo(new Ref(c));
         }
      }

      this.hasSwaRef |= builder.hasSwaRef;
      RuntimeTypeInfoSet r = builder.link();
      errorHandler.check();

      assert r != null : "if no error was reported, the link must be a success";

      this.typeInfoSetCache = new WeakReference(r);
      return r;
   }

   public ElementBeanInfoImpl getElement(Class scope, QName name) {
      Map<QName, ElementBeanInfoImpl> m = (Map)this.elements.get(scope);
      if (m != null) {
         ElementBeanInfoImpl bi = (ElementBeanInfoImpl)m.get(name);
         if (bi != null) {
            return bi;
         }
      }

      m = (Map)this.elements.get((Object)null);
      return (ElementBeanInfoImpl)m.get(name);
   }

   private ElementBeanInfoImpl getOrCreate(RuntimeElementInfo rei) {
      JaxBeanInfo bi = (JaxBeanInfo)this.beanInfos.get(rei);
      return bi != null ? (ElementBeanInfoImpl)bi : new ElementBeanInfoImpl(this, rei);
   }

   protected JaxBeanInfo getOrCreate(RuntimeEnumLeafInfo eli) {
      JaxBeanInfo bi = (JaxBeanInfo)this.beanInfos.get(eli);
      if (bi != null) {
         return bi;
      } else {
         bi = new LeafBeanInfoImpl(this, eli);
         this.beanInfoMap.put(bi.jaxbType, bi);
         return bi;
      }
   }

   protected ClassBeanInfoImpl getOrCreate(RuntimeClassInfo ci) {
      ClassBeanInfoImpl bi = (ClassBeanInfoImpl)this.beanInfos.get(ci);
      if (bi != null) {
         return bi;
      } else {
         bi = new ClassBeanInfoImpl(this, ci);
         this.beanInfoMap.put(bi.jaxbType, bi);
         return bi;
      }
   }

   protected JaxBeanInfo getOrCreate(RuntimeArrayInfo ai) {
      JaxBeanInfo abi = (JaxBeanInfo)this.beanInfos.get(ai);
      if (abi != null) {
         return abi;
      } else {
         abi = new ArrayBeanInfoImpl(this, ai);
         this.beanInfoMap.put(ai.getType(), abi);
         return abi;
      }
   }

   public JaxBeanInfo getOrCreate(RuntimeTypeInfo e) {
      if (e instanceof RuntimeElementInfo) {
         return this.getOrCreate((RuntimeElementInfo)e);
      } else if (e instanceof RuntimeClassInfo) {
         return this.getOrCreate((RuntimeClassInfo)e);
      } else if (e instanceof RuntimeLeafInfo) {
         JaxBeanInfo bi = (JaxBeanInfo)this.beanInfos.get(e);

         assert bi != null;

         return bi;
      } else if (e instanceof RuntimeArrayInfo) {
         return this.getOrCreate((RuntimeArrayInfo)e);
      } else if (e.getType() == Object.class) {
         JaxBeanInfo bi = (JaxBeanInfo)this.beanInfoMap.get(Object.class);
         if (bi == null) {
            bi = new AnyTypeBeanInfo(this, e);
            this.beanInfoMap.put(Object.class, bi);
         }

         return bi;
      } else {
         throw new IllegalArgumentException();
      }
   }

   public JaxBeanInfo getBeanInfo(Object o) {
      for(Class c = o.getClass(); c != Object.class; c = c.getSuperclass()) {
         JaxBeanInfo bi = (JaxBeanInfo)this.beanInfoMap.get(c);
         if (bi != null) {
            return bi;
         }
      }

      if (o instanceof Element) {
         return (JaxBeanInfo)this.beanInfoMap.get(Object.class);
      } else {
         for(Class c : o.getClass().getInterfaces()) {
            JaxBeanInfo bi = (JaxBeanInfo)this.beanInfoMap.get(c);
            if (bi != null) {
               return bi;
            }
         }

         return null;
      }
   }

   public JaxBeanInfo getBeanInfo(Object o, boolean fatal) throws JAXBException {
      JaxBeanInfo bi = this.getBeanInfo(o);
      if (bi != null) {
         return bi;
      } else if (fatal) {
         if (o instanceof Document) {
            throw new JAXBException(Messages.ELEMENT_NEEDED_BUT_FOUND_DOCUMENT.format(o.getClass()));
         } else {
            throw new JAXBException(Messages.UNKNOWN_CLASS.format(o.getClass()));
         }
      } else {
         return null;
      }
   }

   public JaxBeanInfo getBeanInfo(Class clazz) {
      return (JaxBeanInfo)this.beanInfoMap.get(clazz);
   }

   public JaxBeanInfo getBeanInfo(Class clazz, boolean fatal) throws JAXBException {
      JaxBeanInfo<T> bi = this.getBeanInfo(clazz);
      if (bi != null) {
         return bi;
      } else if (fatal) {
         throw new JAXBException(clazz.getName() + " is not known to this context");
      } else {
         return null;
      }
   }

   public Loader selectRootLoader(UnmarshallingContext.State state, TagName tag) {
      JaxBeanInfo beanInfo = (JaxBeanInfo)this.rootMap.get(tag.uri, tag.local);
      return beanInfo == null ? null : beanInfo.getLoader(this, true);
   }

   public JaxBeanInfo getGlobalType(QName name) {
      return (JaxBeanInfo)this.typeMap.get(name);
   }

   public String getNearestTypeName(QName name) {
      String[] all = new String[this.typeMap.size()];
      int i = 0;

      for(QName qn : this.typeMap.keySet()) {
         if (qn.getLocalPart().equals(name.getLocalPart())) {
            return qn.toString();
         }

         all[i++] = qn.toString();
      }

      String nearest = EditDistance.findNearest(name.toString(), all);
      if (EditDistance.editDistance(nearest, name.toString()) > 10) {
         return null;
      } else {
         return nearest;
      }
   }

   public Set getValidRootNames() {
      Set<QName> r = new TreeSet(QNAME_COMPARATOR);

      for(QNameMap.Entry e : this.rootMap.entrySet()) {
         r.add(e.createQName());
      }

      return r;
   }

   public synchronized Encoded[] getUTF8NameTable() {
      if (this.utf8nameTable == null) {
         Encoded[] x = new Encoded[this.nameList.localNames.length];

         for(int i = 0; i < x.length; ++i) {
            Encoded e = new Encoded(this.nameList.localNames[i]);
            e.compact();
            x[i] = e;
         }

         this.utf8nameTable = x;
      }

      return this.utf8nameTable;
   }

   public int getNumberOfLocalNames() {
      return this.nameList.localNames.length;
   }

   public int getNumberOfElementNames() {
      return this.nameList.numberOfElementNames;
   }

   public int getNumberOfAttributeNames() {
      return this.nameList.numberOfAttributeNames;
   }

   static Transformer createTransformer(boolean disableSecureProcessing) {
      try {
         SAXTransformerFactory tf = (SAXTransformerFactory)XmlFactory.createTransformerFactory(disableSecureProcessing);
         return tf.newTransformer();
      } catch (TransformerConfigurationException e) {
         throw new Error(e);
      }
   }

   public static TransformerHandler createTransformerHandler(boolean disableSecureProcessing) {
      try {
         SAXTransformerFactory tf = (SAXTransformerFactory)XmlFactory.createTransformerFactory(disableSecureProcessing);
         return tf.newTransformerHandler();
      } catch (TransformerConfigurationException e) {
         throw new Error(e);
      }
   }

   static Document createDom(boolean disableSecurityProcessing) {
      synchronized(JAXBContextImpl.class) {
         if (db == null) {
            try {
               DocumentBuilderFactory dbf = XmlFactory.createDocumentBuilderFactory(disableSecurityProcessing);
               db = dbf.newDocumentBuilder();
            } catch (ParserConfigurationException e) {
               throw new FactoryConfigurationError(e);
            }
         }

         return db.newDocument();
      }
   }

   public MarshallerImpl createMarshaller() {
      return new MarshallerImpl(this, (AssociationMap)null);
   }

   public UnmarshallerImpl createUnmarshaller() {
      return new UnmarshallerImpl(this, (AssociationMap)null);
   }

   public JAXBIntrospector createJAXBIntrospector() {
      return new JAXBIntrospector() {
         public boolean isElement(Object object) {
            return this.getElementName(object) != null;
         }

         public QName getElementName(Object jaxbElement) {
            try {
               return JAXBContextImpl.this.getElementName(jaxbElement);
            } catch (JAXBException var3) {
               return null;
            }
         }
      };
   }

   private NonElement getXmlType(RuntimeTypeInfoSet tis, TypeReference tr) {
      if (tr == null) {
         throw new IllegalArgumentException();
      } else {
         XmlJavaTypeAdapter xjta = (XmlJavaTypeAdapter)tr.get(XmlJavaTypeAdapter.class);
         XmlList xl = (XmlList)tr.get(XmlList.class);
         Ref<Type, Class> ref = new Ref(this.annotationReader, tis.getNavigator(), tr.type, xjta, xl);
         return tis.getTypeInfo((Ref)ref);
      }
   }

   public void generateEpisode(Result output) {
      if (output == null) {
         throw new IllegalArgumentException();
      } else {
         this.createSchemaGenerator().writeEpisodeFile(ResultFactory.createSerializer(output));
      }
   }

   public void generateSchema(SchemaOutputResolver outputResolver) throws IOException {
      if (outputResolver == null) {
         throw new IOException(Messages.NULL_OUTPUT_RESOLVER.format());
      } else {
         final SAXParseException[] e = new SAXParseException[1];
         final SAXParseException[] w = new SAXParseException[1];
         this.createSchemaGenerator().write(outputResolver, new ErrorListener() {
            public void error(SAXParseException exception) {
               e[0] = exception;
            }

            public void fatalError(SAXParseException exception) {
               e[0] = exception;
            }

            public void warning(SAXParseException exception) {
               w[0] = exception;
            }

            public void info(SAXParseException exception) {
            }
         });
         if (e[0] != null) {
            throw new IOException(Messages.FAILED_TO_GENERATE_SCHEMA.format(), e[0]);
         } else if (w[0] != null) {
            throw new IOException(Messages.ERROR_PROCESSING_SCHEMA.format(), w[0]);
         }
      }
   }

   private XmlSchemaGenerator createSchemaGenerator() {
      RuntimeTypeInfoSet tis;
      try {
         tis = this.getTypeInfoSet();
      } catch (IllegalAnnotationsException e) {
         throw new AssertionError(e);
      }

      XmlSchemaGenerator<Type, Class, Field, Method> xsdgen = new XmlSchemaGenerator(tis.getNavigator(), tis);
      Set<QName> rootTagNames = new HashSet();

      for(RuntimeElementInfo ei : tis.getAllElements()) {
         rootTagNames.add(ei.getElementName());
      }

      for(RuntimeClassInfo ci : tis.beans().values()) {
         if (ci.isElement()) {
            rootTagNames.add(ci.asElement().getElementName());
         }
      }

      for(TypeReference tr : this.bridges.keySet()) {
         if (!rootTagNames.contains(tr.tagName)) {
            if (tr.type != Void.TYPE && tr.type != Void.class) {
               if (tr.type != CompositeStructure.class) {
                  NonElement<Type, Class> typeInfo = this.getXmlType(tis, tr);
                  xsdgen.add(tr.tagName, !tis.getNavigator().isPrimitive(tr.type), typeInfo);
               }
            } else {
               xsdgen.add(tr.tagName, false, (NonElement)null);
            }
         }
      }

      return xsdgen;
   }

   public QName getTypeName(TypeReference tr) {
      try {
         NonElement<Type, Class> xt = this.getXmlType(this.getTypeInfoSet(), tr);
         if (xt == null) {
            throw new IllegalArgumentException();
         } else {
            return xt.getTypeName();
         }
      } catch (IllegalAnnotationsException e) {
         throw new AssertionError(e);
      }
   }

   public Binder createBinder(Class domType) {
      return domType == Node.class ? this.createBinder() : super.createBinder(domType);
   }

   public Binder createBinder() {
      return new BinderImpl(this, new DOMScanner());
   }

   public QName getElementName(Object o) throws JAXBException {
      JaxBeanInfo bi = this.getBeanInfo(o, true);
      return !bi.isElement() ? null : new QName(bi.getElementNamespaceURI(o), bi.getElementLocalName(o));
   }

   public QName getElementName(Class o) throws JAXBException {
      JaxBeanInfo bi = this.getBeanInfo(o, true);
      return !bi.isElement() ? null : new QName(bi.getElementNamespaceURI(o), bi.getElementLocalName(o));
   }

   public Bridge createBridge(TypeReference ref) {
      return (Bridge)this.bridges.get(ref);
   }

   public RawAccessor getElementPropertyAccessor(Class wrapperBean, String nsUri, String localName) throws JAXBException {
      JaxBeanInfo bi = this.getBeanInfo(wrapperBean, true);
      if (!(bi instanceof ClassBeanInfoImpl)) {
         throw new JAXBException(wrapperBean + " is not a bean");
      } else {
         for(ClassBeanInfoImpl cb = (ClassBeanInfoImpl)bi; cb != null; cb = cb.superClazz) {
            for(Property p : cb.properties) {
               final Accessor acc = p.getElementPropertyAccessor(nsUri, localName);
               if (acc != null) {
                  return new RawAccessor() {
                     public Object get(Object bean) throws AccessorException {
                        return acc.getUnadapted(bean);
                     }

                     public void set(Object bean, Object value) throws AccessorException {
                        acc.setUnadapted(bean, value);
                     }
                  };
               }
            }
         }

         QName var10002 = new QName(nsUri, localName);
         throw new JAXBException(var10002 + " is not a valid property on " + wrapperBean);
      }
   }

   public List getKnownNamespaceURIs() {
      return Arrays.asList(this.nameList.namespaceURIs);
   }

   public String getBuildId() {
      Package pkg = this.getClass().getPackage();
      return pkg == null ? null : pkg.getImplementationVersion();
   }

   public String toString() {
      String var10002 = Which.which(this.getClass());
      StringBuilder buf = new StringBuilder(var10002 + " Build-Id: " + this.getBuildId());
      buf.append("\nClasses known to this context:\n");
      Set<String> names = new TreeSet();

      for(Class key : this.beanInfoMap.keySet()) {
         names.add(key.getName());
      }

      for(String name : names) {
         buf.append("  ").append(name).append('\n');
      }

      return buf.toString();
   }

   public String getXMIMEContentType(Object o) {
      JaxBeanInfo bi = this.getBeanInfo(o);
      if (!(bi instanceof ClassBeanInfoImpl)) {
         return null;
      } else {
         ClassBeanInfoImpl cb = (ClassBeanInfoImpl)bi;

         for(Property p : cb.properties) {
            if (p instanceof AttributeProperty) {
               AttributeProperty ap = (AttributeProperty)p;
               if (ap.attName.equals("http://www.w3.org/2005/05/xmlmime", "contentType")) {
                  try {
                     return (String)ap.xacc.print(o);
                  } catch (ClassCastException | SAXException | AccessorException var10) {
                     return null;
                  }
               }
            }
         }

         return null;
      }
   }

   public JAXBContextImpl createAugmented(Class clazz) throws JAXBException {
      Class[] newList = new Class[this.classes.length + 1];
      System.arraycopy(this.classes, 0, newList, 0, this.classes.length);
      newList[this.classes.length] = clazz;
      JAXBContextBuilder builder = new JAXBContextBuilder(this);
      builder.setClasses(newList);
      return builder.build();
   }

   public static class JAXBContextBuilder {
      private boolean retainPropertyInfo = false;
      private boolean supressAccessorWarnings = false;
      private String defaultNsUri = "";
      @NotNull
      private RuntimeAnnotationReader annotationReader = new RuntimeInlineAnnotationReader();
      @NotNull
      private Map subclassReplacements = Collections.emptyMap();
      private boolean c14nSupport = false;
      private Class[] classes;
      private Collection typeRefs;
      private boolean xmlAccessorFactorySupport = false;
      private boolean allNillable;
      private boolean improvedXsiTypeHandling = true;
      private boolean disableSecurityProcessing = true;
      private Boolean backupWithParentNamespace = null;
      private int maxErrorsCount;

      public JAXBContextBuilder() {
      }

      public JAXBContextBuilder(JAXBContextImpl baseImpl) {
         this.supressAccessorWarnings = baseImpl.supressAccessorWarnings;
         this.retainPropertyInfo = baseImpl.retainPropertyInfo;
         this.defaultNsUri = baseImpl.defaultNsUri;
         this.annotationReader = baseImpl.annotationReader;
         this.subclassReplacements = baseImpl.subclassReplacements;
         this.c14nSupport = baseImpl.c14nSupport;
         this.classes = baseImpl.classes;
         this.typeRefs = baseImpl.bridges.keySet();
         this.xmlAccessorFactorySupport = baseImpl.xmlAccessorFactorySupport;
         this.allNillable = baseImpl.allNillable;
         this.disableSecurityProcessing = baseImpl.disableSecurityProcessing;
         this.backupWithParentNamespace = baseImpl.backupWithParentNamespace;
         this.maxErrorsCount = baseImpl.maxErrorsCount;
      }

      public JAXBContextBuilder setRetainPropertyInfo(boolean val) {
         this.retainPropertyInfo = val;
         return this;
      }

      public JAXBContextBuilder setSupressAccessorWarnings(boolean val) {
         this.supressAccessorWarnings = val;
         return this;
      }

      public JAXBContextBuilder setC14NSupport(boolean val) {
         this.c14nSupport = val;
         return this;
      }

      public JAXBContextBuilder setXmlAccessorFactorySupport(boolean val) {
         this.xmlAccessorFactorySupport = val;
         return this;
      }

      public JAXBContextBuilder setDefaultNsUri(String val) {
         this.defaultNsUri = val;
         return this;
      }

      public JAXBContextBuilder setAllNillable(boolean val) {
         this.allNillable = val;
         return this;
      }

      public JAXBContextBuilder setClasses(Class[] val) {
         this.classes = val;
         return this;
      }

      public JAXBContextBuilder setAnnotationReader(RuntimeAnnotationReader val) {
         this.annotationReader = val;
         return this;
      }

      public JAXBContextBuilder setSubclassReplacements(Map val) {
         this.subclassReplacements = val;
         return this;
      }

      public JAXBContextBuilder setTypeRefs(Collection val) {
         this.typeRefs = val;
         return this;
      }

      public JAXBContextBuilder setImprovedXsiTypeHandling(boolean val) {
         this.improvedXsiTypeHandling = val;
         return this;
      }

      public JAXBContextBuilder setDisableSecurityProcessing(boolean val) {
         this.disableSecurityProcessing = val;
         return this;
      }

      public JAXBContextBuilder setBackupWithParentNamespace(Boolean backupWithParentNamespace) {
         this.backupWithParentNamespace = backupWithParentNamespace;
         return this;
      }

      public JAXBContextBuilder setMaxErrorsCount(int maxErrorsCount) {
         this.maxErrorsCount = maxErrorsCount;
         return this;
      }

      public JAXBContextImpl build() throws JAXBException {
         if (this.defaultNsUri == null) {
            this.defaultNsUri = "";
         }

         if (this.subclassReplacements == null) {
            this.subclassReplacements = Collections.emptyMap();
         }

         if (this.annotationReader == null) {
            this.annotationReader = new RuntimeInlineAnnotationReader();
         }

         if (this.typeRefs == null) {
            this.typeRefs = Collections.emptyList();
         }

         return new JAXBContextImpl(this);
      }
   }
}
