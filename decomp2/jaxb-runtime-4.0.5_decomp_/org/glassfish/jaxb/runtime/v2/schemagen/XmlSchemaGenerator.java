package org.glassfish.jaxb.runtime.v2.schemagen;

import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import com.sun.xml.txw2.TXW;
import com.sun.xml.txw2.TxwException;
import com.sun.xml.txw2.TypedXmlWriter;
import com.sun.xml.txw2.output.ResultFactory;
import com.sun.xml.txw2.output.XmlSerializer;
import jakarta.activation.MimeType;
import jakarta.xml.bind.SchemaOutputResolver;
import jakarta.xml.bind.annotation.XmlElement;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;
import org.glassfish.jaxb.core.Utils;
import org.glassfish.jaxb.core.api.ErrorListener;
import org.glassfish.jaxb.core.v2.TODO;
import org.glassfish.jaxb.core.v2.model.core.Adapter;
import org.glassfish.jaxb.core.v2.model.core.ArrayInfo;
import org.glassfish.jaxb.core.v2.model.core.AttributePropertyInfo;
import org.glassfish.jaxb.core.v2.model.core.ClassInfo;
import org.glassfish.jaxb.core.v2.model.core.Element;
import org.glassfish.jaxb.core.v2.model.core.ElementInfo;
import org.glassfish.jaxb.core.v2.model.core.ElementPropertyInfo;
import org.glassfish.jaxb.core.v2.model.core.EnumConstant;
import org.glassfish.jaxb.core.v2.model.core.EnumLeafInfo;
import org.glassfish.jaxb.core.v2.model.core.MapPropertyInfo;
import org.glassfish.jaxb.core.v2.model.core.NonElement;
import org.glassfish.jaxb.core.v2.model.core.NonElementRef;
import org.glassfish.jaxb.core.v2.model.core.PropertyInfo;
import org.glassfish.jaxb.core.v2.model.core.ReferencePropertyInfo;
import org.glassfish.jaxb.core.v2.model.core.TypeInfo;
import org.glassfish.jaxb.core.v2.model.core.TypeInfoSet;
import org.glassfish.jaxb.core.v2.model.core.TypeRef;
import org.glassfish.jaxb.core.v2.model.core.ValuePropertyInfo;
import org.glassfish.jaxb.core.v2.model.core.WildcardMode;
import org.glassfish.jaxb.core.v2.model.nav.Navigator;
import org.glassfish.jaxb.core.v2.schemagen.episode.Bindings;
import org.glassfish.jaxb.runtime.api.CompositeStructure;
import org.glassfish.jaxb.runtime.v2.model.impl.ClassInfoImpl;
import org.glassfish.jaxb.runtime.v2.runtime.SwaRefAdapter;
import org.glassfish.jaxb.runtime.v2.schemagen.xmlschema.Any;
import org.glassfish.jaxb.runtime.v2.schemagen.xmlschema.AttrDecls;
import org.glassfish.jaxb.runtime.v2.schemagen.xmlschema.AttributeType;
import org.glassfish.jaxb.runtime.v2.schemagen.xmlschema.ComplexExtension;
import org.glassfish.jaxb.runtime.v2.schemagen.xmlschema.ComplexType;
import org.glassfish.jaxb.runtime.v2.schemagen.xmlschema.ComplexTypeHost;
import org.glassfish.jaxb.runtime.v2.schemagen.xmlschema.ContentModelContainer;
import org.glassfish.jaxb.runtime.v2.schemagen.xmlschema.ExplicitGroup;
import org.glassfish.jaxb.runtime.v2.schemagen.xmlschema.Import;
import org.glassfish.jaxb.runtime.v2.schemagen.xmlschema.List;
import org.glassfish.jaxb.runtime.v2.schemagen.xmlschema.LocalAttribute;
import org.glassfish.jaxb.runtime.v2.schemagen.xmlschema.LocalElement;
import org.glassfish.jaxb.runtime.v2.schemagen.xmlschema.Schema;
import org.glassfish.jaxb.runtime.v2.schemagen.xmlschema.SimpleExtension;
import org.glassfish.jaxb.runtime.v2.schemagen.xmlschema.SimpleRestrictionModel;
import org.glassfish.jaxb.runtime.v2.schemagen.xmlschema.SimpleType;
import org.glassfish.jaxb.runtime.v2.schemagen.xmlschema.SimpleTypeHost;
import org.glassfish.jaxb.runtime.v2.schemagen.xmlschema.TopLevelAttribute;
import org.glassfish.jaxb.runtime.v2.schemagen.xmlschema.TopLevelElement;
import org.glassfish.jaxb.runtime.v2.schemagen.xmlschema.TypeDefParticle;
import org.glassfish.jaxb.runtime.v2.schemagen.xmlschema.TypeHost;
import org.glassfish.jaxb.runtime.v2.util.CollisionCheckStack;
import org.glassfish.jaxb.runtime.v2.util.StackRecorder;
import org.xml.sax.Locator;
import org.xml.sax.SAXParseException;

public final class XmlSchemaGenerator {
   private static final Logger logger = Utils.getClassLogger();
   private final Map namespaces;
   private ErrorListener errorListener;
   private Navigator navigator;
   private final TypeInfoSet types;
   private final NonElement stringType;
   private final NonElement anyType;
   private final CollisionCheckStack collisionChecker;
   private static final Comparator NAMESPACE_COMPARATOR = new Comparator() {
      public int compare(String lhs, String rhs) {
         return -lhs.compareTo(rhs);
      }
   };
   private static final String newline = "\n";

   public XmlSchemaGenerator(Navigator navigator, TypeInfoSet types) {
      this.namespaces = new TreeMap(NAMESPACE_COMPARATOR);
      this.collisionChecker = new CollisionCheckStack();
      this.navigator = navigator;
      this.types = types;
      this.stringType = types.getTypeInfo(navigator.ref(String.class));
      this.anyType = types.getAnyTypeInfo();

      for(ClassInfo ci : types.beans().values()) {
         this.add(ci);
      }

      for(ElementInfo ei1 : types.getElementMappings((Object)null).values()) {
         this.add(ei1);
      }

      for(EnumLeafInfo ei : types.enums().values()) {
         this.add(ei);
      }

      for(ArrayInfo a : types.arrays().values()) {
         this.add(a);
      }

   }

   private Namespace getNamespace(String uri) {
      XmlSchemaGenerator<T, C, F, M>.Namespace n = (Namespace)this.namespaces.get(uri);
      if (n == null) {
         this.namespaces.put(uri, n = new Namespace(uri));
      }

      return n;
   }

   public void add(ClassInfo clazz) {
      assert clazz != null;

      String nsUri = null;
      if (clazz.getClazz() != this.navigator.asDecl(CompositeStructure.class)) {
         if (clazz.isElement()) {
            nsUri = clazz.getElementName().getNamespaceURI();
            XmlSchemaGenerator<T, C, F, M>.Namespace ns = this.getNamespace(nsUri);
            ns.classes.add(clazz);
            ns.addDependencyTo(clazz.getTypeName());
            this.add(clazz.getElementName(), false, clazz);
         }

         QName tn = clazz.getTypeName();
         if (tn != null) {
            nsUri = tn.getNamespaceURI();
         } else if (nsUri == null) {
            return;
         }

         XmlSchemaGenerator<T, C, F, M>.Namespace n = this.getNamespace(nsUri);
         n.classes.add(clazz);

         for(PropertyInfo p : clazz.getProperties()) {
            n.processForeignNamespaces(p, 1);
            if (p instanceof AttributePropertyInfo) {
               AttributePropertyInfo<T, C> ap = (AttributePropertyInfo)p;
               String aUri = ap.getXmlName().getNamespaceURI();
               if (aUri.length() > 0) {
                  this.getNamespace(aUri).addGlobalAttribute(ap);
                  n.addDependencyTo(ap.getXmlName());
               }
            }

            if (p instanceof ElementPropertyInfo) {
               ElementPropertyInfo<T, C> ep = (ElementPropertyInfo)p;

               for(TypeRef tref : ep.getTypes()) {
                  String eUri = tref.getTagName().getNamespaceURI();
                  if (eUri.length() > 0 && !eUri.equals(n.uri)) {
                     this.getNamespace(eUri).addGlobalElement(tref);
                     n.addDependencyTo(tref.getTagName());
                  }
               }
            }

            if (this.generateSwaRefAdapter(p)) {
               n.useSwaRef = true;
            }

            MimeType mimeType = p.getExpectedMimeType();
            if (mimeType != null) {
               n.useMimeNs = true;
            }
         }

         ClassInfo<T, C> bc = clazz.getBaseClass();
         if (bc != null) {
            this.add(bc);
            n.addDependencyTo(bc.getTypeName());
         }

      }
   }

   public void add(ElementInfo elem) {
      assert elem != null;

      boolean nillable = false;
      QName name = elem.getElementName();
      XmlSchemaGenerator<T, C, F, M>.Namespace n = this.getNamespace(name.getNamespaceURI());
      ElementInfo ei;
      if (elem.getScope() != null) {
         ei = this.types.getElementInfo(elem.getScope().getClazz(), name);
      } else {
         ei = this.types.getElementInfo((Object)null, name);
      }

      XmlElement xmlElem = (XmlElement)ei.getProperty().readAnnotation(XmlElement.class);
      if (xmlElem == null) {
         nillable = false;
      } else {
         nillable = xmlElem.nillable();
      }

      MultiMap var10000 = n.elementDecls;
      String var10001 = name.getLocalPart();
      Objects.requireNonNull(n);
      var10000.put((Comparable)var10001, n.new ElementWithType(nillable, elem.getContentType()));
      n.processForeignNamespaces(elem.getProperty(), 1);
   }

   public void add(EnumLeafInfo envm) {
      assert envm != null;

      String nsUri = null;
      if (envm.isElement()) {
         nsUri = envm.getElementName().getNamespaceURI();
         XmlSchemaGenerator<T, C, F, M>.Namespace ns = this.getNamespace(nsUri);
         ns.enums.add(envm);
         ns.addDependencyTo(envm.getTypeName());
         this.add(envm.getElementName(), false, envm);
      }

      QName typeName = envm.getTypeName();
      if (typeName != null) {
         nsUri = typeName.getNamespaceURI();
      } else if (nsUri == null) {
         return;
      }

      XmlSchemaGenerator<T, C, F, M>.Namespace n = this.getNamespace(nsUri);
      n.enums.add(envm);
      n.addDependencyTo(envm.getBaseType().getTypeName());
   }

   public void add(ArrayInfo a) {
      assert a != null;

      String namespaceURI = a.getTypeName().getNamespaceURI();
      XmlSchemaGenerator<T, C, F, M>.Namespace n = this.getNamespace(namespaceURI);
      n.arrays.add(a);
      n.addDependencyTo(a.getItemType().getTypeName());
   }

   public void add(QName tagName, boolean isNillable, NonElement type) {
      if (type == null || type.getType() != this.navigator.ref(CompositeStructure.class)) {
         XmlSchemaGenerator<T, C, F, M>.Namespace n = this.getNamespace(tagName.getNamespaceURI());
         MultiMap var10000 = n.elementDecls;
         String var10001 = tagName.getLocalPart();
         Objects.requireNonNull(n);
         var10000.put((Comparable)var10001, n.new ElementWithType(isNillable, type));
         if (type != null) {
            n.addDependencyTo(type.getTypeName());
         }

      }
   }

   public void writeEpisodeFile(XmlSerializer out) {
      Bindings root = (Bindings)TXW.create(Bindings.class, out);
      if (this.namespaces.containsKey("")) {
         root._namespace("https://jakarta.ee/xml/ns/jaxb", "jaxb");
      }

      root.version("2.1");

      for(Map.Entry e : this.namespaces.entrySet()) {
         Bindings group = root.bindings();
         String tns = (String)e.getKey();
         String prefix;
         if (!tns.equals("")) {
            group._namespace(tns, "tns");
            prefix = "tns:";
         } else {
            prefix = "";
         }

         group.scd("x-schema::" + (tns.equals("") ? "" : "tns"));
         group.schemaBindings().map(false);

         for(ClassInfo ci : ((Namespace)e.getValue()).classes) {
            if (ci.getTypeName() != null) {
               if (ci.getTypeName().getNamespaceURI().equals(tns)) {
                  Bindings child = group.bindings();
                  child.scd("~" + prefix + ci.getTypeName().getLocalPart());
                  child.klass().ref(ci.getName());
               }

               if (ci.isElement() && ci.getElementName().getNamespaceURI().equals(tns)) {
                  Bindings child = group.bindings();
                  child.scd(prefix + ci.getElementName().getLocalPart());
                  child.klass().ref(ci.getName());
               }
            }
         }

         for(EnumLeafInfo en : ((Namespace)e.getValue()).enums) {
            if (en.getTypeName() != null) {
               Bindings child = group.bindings();
               child.scd("~" + prefix + en.getTypeName().getLocalPart());
               child.klass().ref(this.navigator.getClassName(en.getClazz()));
            }
         }

         group.commit(true);
      }

      root.commit();
   }

   public void write(SchemaOutputResolver resolver, ErrorListener errorListener) throws IOException {
      if (resolver == null) {
         throw new IllegalArgumentException();
      } else {
         if (logger.isLoggable(Level.FINE)) {
            logger.log(Level.FINE, "Writing XML Schema for " + this, new StackRecorder());
         }

         resolver = new FoolProofResolver(resolver);
         this.errorListener = errorListener;
         Map<String, String> schemaLocations = this.types.getSchemaLocations();
         Map<XmlSchemaGenerator<T, C, F, M>.Namespace, Result> out = new HashMap();
         Map<XmlSchemaGenerator<T, C, F, M>.Namespace, String> systemIds = new HashMap();
         this.namespaces.remove("http://www.w3.org/2001/XMLSchema");

         for(Namespace n : this.namespaces.values()) {
            String schemaLocation = (String)schemaLocations.get(n.uri);
            if (schemaLocation != null) {
               systemIds.put(n, schemaLocation);
            } else {
               String var10001 = n.uri;
               int var10002 = out.size();
               Result output = resolver.createOutput(var10001, "schema" + (var10002 + 1) + ".xsd");
               if (output != null) {
                  out.put(n, output);
                  systemIds.put(n, output.getSystemId());
               }
            }
         }

         for(Map.Entry e : out.entrySet()) {
            Result result = (Result)e.getValue();
            ((Namespace)e.getKey()).writeTo(result, systemIds);
            if (result instanceof StreamResult) {
               OutputStream outputStream = ((StreamResult)result).getOutputStream();
               if (outputStream != null) {
                  outputStream.close();
               } else {
                  Writer writer = ((StreamResult)result).getWriter();
                  if (writer != null) {
                     writer.close();
                  }
               }
            }
         }

      }
   }

   private boolean generateSwaRefAdapter(NonElementRef typeRef) {
      return this.generateSwaRefAdapter(typeRef.getSource());
   }

   private boolean generateSwaRefAdapter(PropertyInfo prop) {
      Adapter<T, C> adapter = prop.getAdapter();
      if (adapter == null) {
         return false;
      } else {
         Object o = this.navigator.asDecl(SwaRefAdapter.class);
         return o == null ? false : o.equals(adapter.adapterType);
      }
   }

   public String toString() {
      StringBuilder buf = new StringBuilder();

      for(Namespace ns : this.namespaces.values()) {
         if (buf.length() > 0) {
            buf.append(',');
         }

         buf.append(ns.uri).append('=').append(ns);
      }

      String var10000 = super.toString();
      return var10000 + "[" + buf + "]";
   }

   private static String getProcessContentsModeName(WildcardMode wc) {
      switch (wc) {
         case LAX:
         case SKIP:
            return wc.name().toLowerCase();
         case STRICT:
            return null;
         default:
            throw new IllegalStateException();
      }
   }

   protected static String relativize(String uri, String baseUri) {
      try {
         assert uri != null;

         if (baseUri == null) {
            return uri;
         } else {
            URI theUri = new URI(Util.escapeURI(uri));
            URI theBaseUri = new URI(Util.escapeURI(baseUri));
            if (!theUri.isOpaque() && !theBaseUri.isOpaque()) {
               if (Util.equalsIgnoreCase(theUri.getScheme(), theBaseUri.getScheme()) && Util.equal(theUri.getAuthority(), theBaseUri.getAuthority())) {
                  String uriPath = theUri.getPath();
                  String basePath = theBaseUri.getPath();
                  if (!basePath.endsWith("/")) {
                     basePath = Util.normalizeUriPath(basePath);
                  }

                  if (uriPath.equals(basePath)) {
                     return ".";
                  } else {
                     String relPath = calculateRelativePath(uriPath, basePath, fixNull(theUri.getScheme()).equals("file"));
                     if (relPath == null) {
                        return uri;
                     } else {
                        StringBuilder relUri = new StringBuilder();
                        relUri.append(relPath);
                        if (theUri.getQuery() != null) {
                           relUri.append('?').append(theUri.getQuery());
                        }

                        if (theUri.getFragment() != null) {
                           relUri.append('#').append(theUri.getFragment());
                        }

                        return relUri.toString();
                     }
                  }
               } else {
                  return uri;
               }
            } else {
               return uri;
            }
         }
      } catch (URISyntaxException var8) {
         throw new InternalError("Error escaping one of these uris:\n\t" + uri + "\n\t" + baseUri);
      }
   }

   private static String fixNull(String s) {
      return s == null ? "" : s;
   }

   private static String calculateRelativePath(String uri, String base, boolean fileUrl) {
      boolean onWindows = File.pathSeparatorChar == ';';
      if (base == null) {
         return null;
      } else if ((!fileUrl || !onWindows || !startsWithIgnoreCase(uri, base)) && !uri.startsWith(base)) {
         String var10000 = calculateRelativePath(uri, Util.getParentUriPath(base), fileUrl);
         return "../" + var10000;
      } else {
         return uri.substring(base.length());
      }
   }

   private static boolean startsWithIgnoreCase(String s, String t) {
      return s.toUpperCase().startsWith(t.toUpperCase());
   }

   private class Namespace {
      @NotNull
      final String uri;
      private final Set depends = new LinkedHashSet();
      private boolean selfReference;
      private final Set classes = new LinkedHashSet();
      private final Set enums = new LinkedHashSet();
      private final Set arrays = new LinkedHashSet();
      private final MultiMap attributeDecls = new MultiMap((Object)null);
      private final MultiMap elementDecls;
      private Form attributeFormDefault;
      private Form elementFormDefault;
      private boolean useSwaRef;
      private boolean useMimeNs;

      public Namespace(String uri) {
         this.elementDecls = new MultiMap(new ElementWithType(true, XmlSchemaGenerator.this.anyType));
         this.uri = uri;

         assert !XmlSchemaGenerator.this.namespaces.containsKey(uri);

         XmlSchemaGenerator.this.namespaces.put(uri, this);
      }

      private void processForeignNamespaces(PropertyInfo p, int processingDepth) {
         for(TypeInfo t : p.ref()) {
            if (t instanceof ClassInfo && processingDepth > 0) {
               for(PropertyInfo subp : ((ClassInfo)t).getProperties()) {
                  --processingDepth;
                  this.processForeignNamespaces(subp, processingDepth);
               }
            }

            if (t instanceof Element) {
               this.addDependencyTo(((Element)t).getElementName());
            }

            if (t instanceof NonElement) {
               this.addDependencyTo(((NonElement)t).getTypeName());
            }
         }

      }

      private void addDependencyTo(@Nullable QName qname) {
         if (qname != null) {
            String nsUri = qname.getNamespaceURI();
            if (!"http://www.w3.org/2001/XMLSchema".equals(nsUri)) {
               if (nsUri.equals(this.uri)) {
                  this.selfReference = true;
               } else {
                  this.depends.add(XmlSchemaGenerator.this.getNamespace(nsUri));
               }
            }
         }
      }

      private void writeTo(Result result, Map systemIds) throws IOException {
         try {
            Schema schema = (Schema)TXW.create(Schema.class, ResultFactory.createSerializer(result));
            Map<String, String> xmlNs = XmlSchemaGenerator.this.types.getXmlNs(this.uri);

            for(Map.Entry e : xmlNs.entrySet()) {
               schema._namespace((String)e.getValue(), (String)e.getKey());
            }

            if (this.useSwaRef) {
               schema._namespace("http://ws-i.org/profiles/basic/1.1/xsd", "swaRef");
            }

            if (this.useMimeNs) {
               schema._namespace("http://www.w3.org/2005/05/xmlmime", "xmime");
            }

            this.attributeFormDefault = Form.get(XmlSchemaGenerator.this.types.getAttributeFormDefault(this.uri));
            this.attributeFormDefault.declare("attributeFormDefault", schema);
            this.elementFormDefault = Form.get(XmlSchemaGenerator.this.types.getElementFormDefault(this.uri));
            this.elementFormDefault.declare("elementFormDefault", schema);
            if (!xmlNs.containsValue("http://www.w3.org/2001/XMLSchema") && !xmlNs.containsKey("xs")) {
               schema._namespace("http://www.w3.org/2001/XMLSchema", "xs");
            }

            schema.version("1.0");
            if (this.uri.length() != 0) {
               schema.targetNamespace(this.uri);
            }

            for(Namespace ns : this.depends) {
               schema._namespace(ns.uri);
            }

            if (this.selfReference && this.uri.length() != 0) {
               schema._namespace(this.uri, "tns");
            }

            schema._pcdata("\n");

            for(Namespace n : this.depends) {
               Import imp = schema._import();
               if (n.uri.length() != 0) {
                  imp.namespace(n.uri);
               }

               String refSystemId = (String)systemIds.get(n);
               if (refSystemId != null && !refSystemId.equals("")) {
                  imp.schemaLocation(XmlSchemaGenerator.relativize(refSystemId, result.getSystemId()));
               }

               schema._pcdata("\n");
            }

            if (this.useSwaRef) {
               schema._import().namespace("http://ws-i.org/profiles/basic/1.1/xsd").schemaLocation("http://ws-i.org/profiles/basic/1.1/swaref.xsd");
            }

            if (this.useMimeNs) {
               schema._import().namespace("http://www.w3.org/2005/05/xmlmime").schemaLocation("https://www.w3.org/2005/05/xmlmime");
            }

            for(Map.Entry e : this.elementDecls.entrySet()) {
               ((ElementDeclaration)e.getValue()).writeTo((String)e.getKey(), schema);
               schema._pcdata("\n");
            }

            for(ClassInfo c : this.classes) {
               if (c.getTypeName() != null) {
                  if (this.uri.equals(c.getTypeName().getNamespaceURI())) {
                     this.writeClass(c, schema);
                  }

                  schema._pcdata("\n");
               }
            }

            for(EnumLeafInfo e : this.enums) {
               if (e.getTypeName() != null) {
                  if (this.uri.equals(e.getTypeName().getNamespaceURI())) {
                     this.writeEnum(e, schema);
                  }

                  schema._pcdata("\n");
               }
            }

            for(ArrayInfo a : this.arrays) {
               this.writeArray(a, schema);
               schema._pcdata("\n");
            }

            for(Map.Entry e : this.attributeDecls.entrySet()) {
               TopLevelAttribute a = schema.attribute();
               a.name((String)e.getKey());
               if (e.getValue() == null) {
                  this.writeTypeRef(a, (NonElement)XmlSchemaGenerator.this.stringType, "type");
               } else {
                  this.writeAttributeTypeRef((AttributePropertyInfo)e.getValue(), a);
               }

               schema._pcdata("\n");
            }

            schema.commit();
         } catch (TxwException e) {
            XmlSchemaGenerator.logger.log(Level.INFO, e.getMessage(), e);
            throw new IOException(e.getMessage());
         }
      }

      private void writeTypeRef(TypeHost th, NonElementRef typeRef, String refAttName) {
         switch (typeRef.getSource().id()) {
            case ID:
               th._attribute(refAttName, new QName("http://www.w3.org/2001/XMLSchema", "ID"));
               return;
            case IDREF:
               th._attribute(refAttName, new QName("http://www.w3.org/2001/XMLSchema", "IDREF"));
               return;
            case NONE:
               MimeType mimeType = typeRef.getSource().getExpectedMimeType();
               if (mimeType != null) {
                  th._attribute(new QName("http://www.w3.org/2005/05/xmlmime", "expectedContentTypes", "xmime"), mimeType.toString());
               }

               if (XmlSchemaGenerator.this.generateSwaRefAdapter(typeRef)) {
                  th._attribute(refAttName, new QName("http://ws-i.org/profiles/basic/1.1/xsd", "swaRef", "ref"));
                  return;
               } else {
                  if (typeRef.getSource().getSchemaType() != null) {
                     th._attribute(refAttName, typeRef.getSource().getSchemaType());
                     return;
                  }

                  this.writeTypeRef(th, typeRef.getTarget(), refAttName);
                  return;
               }
            default:
               throw new IllegalStateException();
         }
      }

      private void writeTypeRef(TypeHost th, NonElement type, String refAttName) {
         if (type.getTypeName() == null) {
            th.block();
            if (type instanceof ClassInfo) {
               if (XmlSchemaGenerator.this.collisionChecker.push((ClassInfo)type)) {
                  XmlSchemaGenerator.this.errorListener.warning(new SAXParseException(Messages.ANONYMOUS_TYPE_CYCLE.format(XmlSchemaGenerator.this.collisionChecker.getCycleString()), (Locator)null));
               } else {
                  this.writeClass((ClassInfo)type, th);
               }

               XmlSchemaGenerator.this.collisionChecker.pop();
            } else {
               this.writeEnum((EnumLeafInfo)type, (SimpleTypeHost)th);
            }
         } else {
            th._attribute(refAttName, type.getTypeName());
         }

      }

      private void writeArray(ArrayInfo a, Schema schema) {
         ComplexType ct = schema.complexType().name(a.getTypeName().getLocalPart());
         ct._final("#all");
         LocalElement le = ct.sequence().element().name("item");
         le.type(a.getItemType().getTypeName());
         le.minOccurs(0).maxOccurs("unbounded");
         le.nillable(true);
         ct.commit();
      }

      private void writeEnum(EnumLeafInfo e, SimpleTypeHost th) {
         SimpleType st = th.simpleType();
         this.writeName(e, st);
         SimpleRestrictionModel base = st.restriction();
         this.writeTypeRef(base, (NonElement)e.getBaseType(), "base");

         for(EnumConstant c : e.getConstants()) {
            base.enumeration().value(c.getLexicalValue());
         }

         st.commit();
      }

      private void writeClass(ClassInfo c, TypeHost parent) {
         if (this.containsValueProp(c)) {
            if (c.getProperties().size() == 1) {
               ValuePropertyInfo<T, C> vp = (ValuePropertyInfo)c.getProperties().get(0);
               SimpleType st = ((SimpleTypeHost)parent).simpleType();
               this.writeName(c, st);
               if (vp.isCollection()) {
                  this.writeTypeRef(st.list(), (NonElement)vp.getTarget(), "itemType");
               } else {
                  this.writeTypeRef(st.restriction(), (NonElement)vp.getTarget(), "base");
               }

            } else {
               ComplexType ct = ((ComplexTypeHost)parent).complexType();
               this.writeName(c, ct);
               if (c.isFinal()) {
                  ct._final("extension restriction");
               }

               SimpleExtension se = ct.simpleContent().extension();
               se.block();

               for(PropertyInfo p : c.getProperties()) {
                  switch (p.kind()) {
                     case ATTRIBUTE:
                        this.handleAttributeProp((AttributePropertyInfo)p, se);
                        break;
                     case VALUE:
                        TODO.checkSpec("what if vp.isCollection() == true?");
                        ValuePropertyInfo vp = (ValuePropertyInfo)p;
                        se.base(vp.getTarget().getTypeName());
                        break;
                     case ELEMENT:
                     case REFERENCE:
                     default:
                        assert false;

                        throw new IllegalStateException();
                  }
               }

               se.commit();
               TODO.schemaGenerator("figure out what to do if bc != null");
               TODO.checkSpec("handle sec 8.9.5.2, bullet #4");
            }
         } else {
            ComplexType ct = ((ComplexTypeHost)parent).complexType();
            this.writeName(c, ct);
            if (c.isFinal()) {
               ct._final("extension restriction");
            }

            if (c.isAbstract()) {
               ct._abstract(true);
            }

            AttrDecls contentModel = ct;
            TypeDefParticle contentModelOwner = ct;
            ClassInfo<T, C> bc = c.getBaseClass();
            if (bc != null) {
               if (bc.hasValueProperty()) {
                  SimpleExtension se = ct.simpleContent().extension();
                  contentModel = se;
                  contentModelOwner = null;
                  se.base(bc.getTypeName());
               } else {
                  ComplexExtension ce = ct.complexContent().extension();
                  contentModel = ce;
                  contentModelOwner = ce;
                  ce.base(bc.getTypeName());
               }
            }

            if (contentModelOwner != null) {
               ArrayList<Tree> children = new ArrayList();

               for(PropertyInfo p : c.getProperties()) {
                  if (p instanceof ReferencePropertyInfo && ((ReferencePropertyInfo)p).isMixed()) {
                     ct.mixed(true);
                  }

                  Tree t = this.buildPropertyContentModel(p);
                  if (t != null) {
                     children.add(t);
                  }
               }

               Tree top = Tree.makeGroup(c.isOrdered() ? GroupKind.SEQUENCE : GroupKind.ALL, children);
               top.write(contentModelOwner);
            }

            for(PropertyInfo p : c.getProperties()) {
               if (p instanceof AttributePropertyInfo) {
                  this.handleAttributeProp((AttributePropertyInfo)p, contentModel);
               }
            }

            if (c.hasAttributeWildcard()) {
               contentModel.anyAttribute().namespace("##other").processContents("skip");
            }

            ct.commit();
         }
      }

      private void writeName(NonElement c, TypedXmlWriter xw) {
         QName tn = c.getTypeName();
         if (tn != null) {
            xw._attribute("name", tn.getLocalPart());
         }

      }

      private boolean containsValueProp(ClassInfo c) {
         for(PropertyInfo p : c.getProperties()) {
            if (p instanceof ValuePropertyInfo) {
               return true;
            }
         }

         return false;
      }

      private Tree buildPropertyContentModel(PropertyInfo p) {
         switch (p.kind()) {
            case ATTRIBUTE:
               return null;
            case VALUE:
               assert false;

               throw new IllegalStateException();
            case ELEMENT:
               return this.handleElementProp((ElementPropertyInfo)p);
            case REFERENCE:
               return this.handleReferenceProp((ReferencePropertyInfo)p);
            case MAP:
               return this.handleMapProp((MapPropertyInfo)p);
            default:
               assert false;

               throw new IllegalStateException();
         }
      }

      private Tree handleElementProp(final ElementPropertyInfo ep) {
         if (ep.isValueList()) {
            return new Tree.Term() {
               protected void write(ContentModelContainer parent, boolean isOptional, boolean repeated) {
                  TypeRef<T, C> t = (TypeRef)ep.getTypes().get(0);
                  LocalElement e = parent.element();
                  e.block();
                  QName tn = t.getTagName();
                  e.name(tn.getLocalPart());
                  List lst = e.simpleType().list();
                  Namespace.this.writeTypeRef(lst, (NonElementRef)t, "itemType");
                  Namespace.this.elementFormDefault.writeForm(e, tn);
                  this.writeOccurs(e, isOptional || !ep.isRequired(), repeated);
               }
            };
         } else {
            ArrayList<Tree> children = new ArrayList();

            for(final TypeRef t : ep.getTypes()) {
               children.add(new Tree.Term() {
                  protected void write(ContentModelContainer parent, boolean isOptional, boolean repeated) {
                     LocalElement e = parent.element();
                     QName tn = t.getTagName();
                     PropertyInfo propInfo = t.getSource();
                     TypeInfo parentInfo = propInfo == null ? null : propInfo.parent();
                     if (Namespace.this.canBeDirectElementRef(t, tn, parentInfo)) {
                        if (!t.getTarget().isSimpleType() && t.getTarget() instanceof ClassInfo && XmlSchemaGenerator.this.collisionChecker.findDuplicate((ClassInfo)t.getTarget())) {
                           e.ref(new QName(Namespace.this.uri, tn.getLocalPart()));
                        } else {
                           QName elemName = null;
                           if (t.getTarget() instanceof Element) {
                              Element te = (Element)t.getTarget();
                              elemName = te.getElementName();
                           }

                           Collection<TypeInfo> refs = propInfo.ref();
                           if (refs != null && !refs.isEmpty() && elemName != null) {
                              ClassInfoImpl cImpl = null;

                              for(TypeInfo ref : refs) {
                                 if ((ref == null || ref instanceof ClassInfoImpl) && elemName.equals(((ClassInfoImpl)ref).getElementName())) {
                                    cImpl = (ClassInfoImpl)ref;
                                    break;
                                 }
                              }

                              if (cImpl != null) {
                                 if (tn.getNamespaceURI() != null && tn.getNamespaceURI().trim().length() != 0) {
                                    e.ref(new QName(tn.getNamespaceURI(), tn.getLocalPart()));
                                 } else {
                                    e.ref(new QName(cImpl.getElementName().getNamespaceURI(), tn.getLocalPart()));
                                 }
                              } else {
                                 e.ref(new QName("", tn.getLocalPart()));
                              }
                           } else {
                              e.ref(tn);
                           }
                        }
                     } else {
                        e.name(tn.getLocalPart());
                        Namespace.this.writeTypeRef(e, (NonElementRef)t, "type");
                        Namespace.this.elementFormDefault.writeForm(e, tn);
                     }

                     if (t.isNillable()) {
                        e.nillable(true);
                     }

                     if (t.getDefaultValue() != null) {
                        e._default(t.getDefaultValue());
                     }

                     this.writeOccurs(e, isOptional, repeated);
                  }
               });
            }

            final Tree choice = Tree.makeGroup(GroupKind.CHOICE, children).makeOptional(!ep.isRequired()).makeRepeated(ep.isCollection());
            final QName ename = ep.getXmlName();
            return (Tree)(ename != null ? new Tree.Term() {
               protected void write(ContentModelContainer parent, boolean isOptional, boolean repeated) {
                  LocalElement e = parent.element();
                  if (ename.getNamespaceURI().length() > 0 && !ename.getNamespaceURI().equals(Namespace.this.uri)) {
                     e.ref(new QName(ename.getNamespaceURI(), ename.getLocalPart()));
                  } else {
                     e.name(ename.getLocalPart());
                     Namespace.this.elementFormDefault.writeForm(e, ename);
                     if (ep.isCollectionNillable()) {
                        e.nillable(true);
                     }

                     this.writeOccurs(e, !ep.isCollectionRequired(), repeated);
                     ComplexType p = e.complexType();
                     choice.write(p);
                  }
               }
            } : choice);
         }
      }

      private boolean canBeDirectElementRef(TypeRef t, QName tn, TypeInfo parentInfo) {
         Element te = null;
         ClassInfo ci = null;
         QName targetTagName = null;
         if (!t.isNillable() && t.getDefaultValue() == null) {
            if (t.getTarget() instanceof Element) {
               te = (Element)t.getTarget();
               targetTagName = te.getElementName();
               if (te instanceof ClassInfo) {
                  ci = (ClassInfo)te;
               }
            }

            String nsUri = tn.getNamespaceURI();
            if (nsUri.equals(this.uri) || nsUri.length() <= 0 || parentInfo instanceof ClassInfo && ((ClassInfo)parentInfo).getTypeName() == null) {
               if (ci != null && targetTagName != null && te.getScope() == null && targetTagName.getNamespaceURI() == null && targetTagName.equals(tn)) {
                  return true;
               } else if (te == null) {
                  return false;
               } else {
                  return targetTagName != null && targetTagName.equals(tn);
               }
            } else {
               return true;
            }
         } else {
            return false;
         }
      }

      private void handleAttributeProp(AttributePropertyInfo ap, AttrDecls attr) {
         LocalAttribute localAttribute = attr.attribute();
         String attrURI = ap.getXmlName().getNamespaceURI();
         if (attrURI.equals("")) {
            localAttribute.name(ap.getXmlName().getLocalPart());
            this.writeAttributeTypeRef(ap, localAttribute);
            this.attributeFormDefault.writeForm(localAttribute, ap.getXmlName());
         } else {
            localAttribute.ref(ap.getXmlName());
         }

         if (ap.isRequired()) {
            localAttribute.use("required");
         }

      }

      private void writeAttributeTypeRef(AttributePropertyInfo ap, AttributeType a) {
         if (ap.isCollection()) {
            this.writeTypeRef(a.simpleType().list(), (NonElementRef)ap, "itemType");
         } else {
            this.writeTypeRef(a, (NonElementRef)ap, "type");
         }

      }

      private Tree handleReferenceProp(final ReferencePropertyInfo rp) {
         ArrayList<Tree> children = new ArrayList();

         for(final Element e : rp.getElements()) {
            children.add(new Tree.Term() {
               protected void write(ContentModelContainer parent, boolean isOptional, boolean repeated) {
                  LocalElement eref = parent.element();
                  boolean local = false;
                  QName en = e.getElementName();
                  if (e.getScope() != null) {
                     boolean qualified = en.getNamespaceURI().equals(Namespace.this.uri);
                     boolean unqualified = en.getNamespaceURI().equals("");
                     if (qualified || unqualified) {
                        if (unqualified) {
                           if (Namespace.this.elementFormDefault.isEffectivelyQualified) {
                              eref.form("unqualified");
                           }
                        } else if (!Namespace.this.elementFormDefault.isEffectivelyQualified) {
                           eref.form("qualified");
                        }

                        local = true;
                        eref.name(en.getLocalPart());
                        if (e instanceof ClassInfo) {
                           Namespace.this.writeTypeRef(eref, (NonElement)((ClassInfo)e), "type");
                        } else {
                           Namespace.this.writeTypeRef(eref, (NonElement)((ElementInfo)e).getContentType(), "type");
                        }
                     }
                  }

                  if (!local) {
                     eref.ref(en);
                  }

                  this.writeOccurs(eref, isOptional, repeated);
               }
            });
         }

         final WildcardMode wc = rp.getWildcard();
         if (wc != null) {
            children.add(new Tree.Term() {
               protected void write(ContentModelContainer parent, boolean isOptional, boolean repeated) {
                  Any any = parent.any();
                  String pcmode = XmlSchemaGenerator.getProcessContentsModeName(wc);
                  if (pcmode != null) {
                     any.processContents(pcmode);
                  }

                  any.namespace("##other");
                  this.writeOccurs(any, isOptional, repeated);
               }
            });
         }

         final Tree choice = Tree.makeGroup(GroupKind.CHOICE, children).makeRepeated(rp.isCollection()).makeOptional(!rp.isRequired());
         final QName ename = rp.getXmlName();
         return (Tree)(ename != null ? new Tree.Term() {
            protected void write(ContentModelContainer parent, boolean isOptional, boolean repeated) {
               LocalElement e = parent.element().name(ename.getLocalPart());
               Namespace.this.elementFormDefault.writeForm(e, ename);
               if (rp.isCollectionNillable()) {
                  e.nillable(true);
               }

               this.writeOccurs(e, true, repeated);
               ComplexType p = e.complexType();
               choice.write(p);
            }
         } : choice);
      }

      private Tree handleMapProp(final MapPropertyInfo mp) {
         return new Tree.Term() {
            protected void write(ContentModelContainer parent, boolean isOptional, boolean repeated) {
               QName ename = mp.getXmlName();
               LocalElement e = parent.element();
               Namespace.this.elementFormDefault.writeForm(e, ename);
               if (mp.isCollectionNillable()) {
                  e.nillable(true);
               }

               e = e.name(ename.getLocalPart());
               this.writeOccurs(e, isOptional, repeated);
               ComplexType p = e.complexType();
               e = p.sequence().element();
               e.name("entry").minOccurs(0).maxOccurs("unbounded");
               ExplicitGroup seq = e.complexType().sequence();
               Namespace.this.writeKeyOrValue(seq, "key", mp.getKeyType());
               Namespace.this.writeKeyOrValue(seq, "value", mp.getValueType());
            }
         };
      }

      private void writeKeyOrValue(ExplicitGroup seq, String tagName, NonElement typeRef) {
         LocalElement key = seq.element().name(tagName);
         key.minOccurs(0);
         this.writeTypeRef(key, (NonElement)typeRef, "type");
      }

      public void addGlobalAttribute(AttributePropertyInfo ap) {
         this.attributeDecls.put((Comparable)ap.getXmlName().getLocalPart(), ap);
         this.addDependencyTo(ap.getTarget().getTypeName());
      }

      public void addGlobalElement(TypeRef tref) {
         this.elementDecls.put((Comparable)tref.getTagName().getLocalPart(), new ElementWithType(false, tref.getTarget()));
         this.addDependencyTo(tref.getTarget().getTypeName());
      }

      public String toString() {
         StringBuilder buf = new StringBuilder();
         buf.append("[classes=").append(this.classes);
         buf.append(",elementDecls=").append(this.elementDecls);
         buf.append(",enums=").append(this.enums);
         buf.append("]");
         return buf.toString();
      }

      abstract class ElementDeclaration {
         public abstract boolean equals(Object var1);

         public abstract int hashCode();

         public abstract void writeTo(String var1, Schema var2);
      }

      class ElementWithType extends ElementDeclaration {
         private final boolean nillable;
         private final NonElement type;

         public ElementWithType(boolean nillable, NonElement type) {
            this.type = type;
            this.nillable = nillable;
         }

         public void writeTo(String localName, Schema schema) {
            TopLevelElement e = schema.element().name(localName);
            if (this.nillable) {
               e.nillable(true);
            }

            if (this.type != null) {
               Namespace.this.writeTypeRef(e, (NonElement)this.type, "type");
            } else {
               e.complexType();
            }

            e.commit();
         }

         public boolean equals(Object o) {
            if (this == o) {
               return true;
            } else if (o != null && this.getClass() == o.getClass()) {
               XmlSchemaGenerator<T, C, F, M>.Namespace.ElementWithType that = (ElementWithType)o;
               return this.type.equals(that.type);
            } else {
               return false;
            }
         }

         public int hashCode() {
            return this.type.hashCode();
         }
      }
   }
}
