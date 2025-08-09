package org.apache.ws.commons.schema;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import javax.xml.namespace.NamespaceContext;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.ws.commons.schema.extensions.ExtensionRegistry;
import org.apache.ws.commons.schema.utils.NodeNamespaceContext;
import org.apache.ws.commons.schema.utils.TargetNamespaceValidator;
import org.apache.ws.commons.schema.utils.XDOMUtil;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class SchemaBuilder {
   static ThreadLocal resolvedSchemas = new ThreadLocal();
   private static final Set RESERVED_ATTRIBUTES = new HashSet();
   private static final String[] RESERVED_ATTRIBUTES_LIST = new String[]{"name", "type", "default", "fixed", "form", "id", "use", "ref"};
   XmlSchemaCollection collection;
   Document currentDocument;
   XmlSchema currentSchema;
   DocumentBuilderFactory docFac;
   private final TargetNamespaceValidator currentValidator;
   private ExtensionRegistry extReg;

   SchemaBuilder(XmlSchemaCollection collection, TargetNamespaceValidator validator) {
      this.collection = collection;
      this.currentValidator = validator;
      if (collection.getExtReg() != null) {
         this.extReg = collection.getExtReg();
      }

      this.currentSchema = new XmlSchema();
   }

   public static void clearCache() {
      Map<String, SoftReference<XmlSchema>> threadResolvedSchemas = (Map)resolvedSchemas.get();
      if (threadResolvedSchemas != null) {
         threadResolvedSchemas.clear();
         resolvedSchemas.remove();
      }

   }

   public static void initCache() {
      Map<String, SoftReference<XmlSchema>> threadResolvedSchemas = (Map)resolvedSchemas.get();
      if (threadResolvedSchemas == null) {
         threadResolvedSchemas = Collections.synchronizedMap(new HashMap());
         resolvedSchemas.set(threadResolvedSchemas);
      }

   }

   public ExtensionRegistry getExtReg() {
      return this.extReg;
   }

   public void setExtReg(ExtensionRegistry extReg) {
      this.extReg = extReg;
   }

   XmlSchema build(Document doc, String uri) {
      Element schemaEl = doc.getDocumentElement();
      XmlSchema xmlSchema = this.handleXmlSchemaElement(schemaEl, uri);
      xmlSchema.setInputEncoding(doc.getInputEncoding());
      return xmlSchema;
   }

   XmlSchemaDerivationMethod getDerivation(Element el, String attrName) {
      if (el.hasAttribute(attrName) && !el.getAttribute(attrName).equals("")) {
         String derivationMethod = el.getAttribute(attrName).trim();
         return XmlSchemaDerivationMethod.schemaValueOf(derivationMethod);
      } else {
         return XmlSchemaDerivationMethod.NONE;
      }
   }

   String getEnumString(Element el, String attrName) {
      return el.hasAttribute(attrName) ? el.getAttribute(attrName).trim() : "none";
   }

   XmlSchemaForm getFormDefault(Element el, String attrName) {
      if (el.getAttributeNode(attrName) != null) {
         String value = el.getAttribute(attrName);
         return XmlSchemaForm.schemaValueOf(value);
      } else {
         return XmlSchemaForm.UNQUALIFIED;
      }
   }

   long getMaxOccurs(Element el) {
      try {
         if (el.getAttributeNode("maxOccurs") != null) {
            String value = el.getAttribute("maxOccurs");
            return "unbounded".equals(value) ? Long.MAX_VALUE : Long.parseLong(value);
         } else {
            return 1L;
         }
      } catch (NumberFormatException var3) {
         return 1L;
      }
   }

   long getMinOccurs(Element el) {
      try {
         if (el.getAttributeNode("minOccurs") != null) {
            String value = el.getAttribute("minOccurs");
            return "unbounded".equals(value) ? Long.MAX_VALUE : Long.parseLong(value);
         } else {
            return 1L;
         }
      } catch (NumberFormatException var3) {
         return 1L;
      }
   }

   XmlSchemaAnnotation handleAnnotation(Element annotEl) {
      XmlSchemaAnnotation annotation = new XmlSchemaAnnotation();
      List<XmlSchemaAnnotationItem> content = annotation.getItems();

      for(Element appinfo = XDOMUtil.getFirstChildElementNS(annotEl, "http://www.w3.org/2001/XMLSchema", "appinfo"); appinfo != null; appinfo = XDOMUtil.getNextSiblingElementNS(appinfo, "http://www.w3.org/2001/XMLSchema", "appinfo")) {
         XmlSchemaAppInfo appInfoObj = this.handleAppInfo(appinfo);
         if (appInfoObj != null) {
            content.add(appInfoObj);
         }
      }

      for(Element documentation = XDOMUtil.getFirstChildElementNS(annotEl, "http://www.w3.org/2001/XMLSchema", "documentation"); documentation != null; documentation = XDOMUtil.getNextSiblingElementNS(documentation, "http://www.w3.org/2001/XMLSchema", "documentation")) {
         XmlSchemaDocumentation docsObj = this.handleDocumentation(documentation);
         if (docsObj != null) {
            content.add(docsObj);
         }
      }

      this.processExtensibilityComponents(annotation, annotEl, true);
      return annotation;
   }

   XmlSchemaAppInfo handleAppInfo(Element content) {
      XmlSchemaAppInfo appInfo = new XmlSchemaAppInfo();
      NodeList markup = new DocumentFragmentNodeList(content);
      if (!content.hasAttribute("source") && markup.getLength() == 0) {
         return null;
      } else {
         appInfo.setSource(this.getAttribute(content, "source"));
         appInfo.setMarkup(markup);
         return appInfo;
      }
   }

   XmlSchemaComplexType handleComplexType(XmlSchema schema, Element complexEl, Element schemaEl, boolean topLevel) {
      XmlSchemaComplexType ct = new XmlSchemaComplexType(schema, topLevel);
      if (complexEl.hasAttribute("name")) {
         ct.setName(complexEl.getAttribute("name"));
      }

      for(Element el = XDOMUtil.getFirstChildElementNS(complexEl, "http://www.w3.org/2001/XMLSchema"); el != null; el = XDOMUtil.getNextSiblingElementNS(el, "http://www.w3.org/2001/XMLSchema")) {
         if (el.getLocalName().equals("sequence")) {
            ct.setParticle(this.handleSequence(schema, el, schemaEl));
         } else if (el.getLocalName().equals("choice")) {
            ct.setParticle(this.handleChoice(schema, el, schemaEl));
         } else if (el.getLocalName().equals("all")) {
            ct.setParticle(this.handleAll(schema, el, schemaEl));
         } else if (el.getLocalName().equals("attribute")) {
            ct.getAttributes().add(this.handleAttribute(schema, el, schemaEl));
         } else if (el.getLocalName().equals("attributeGroup")) {
            ct.getAttributes().add(this.handleAttributeGroupRef(schema, el));
         } else if (el.getLocalName().equals("group")) {
            XmlSchemaGroupRef group = this.handleGroupRef(schema, el, schemaEl);
            if (group.getParticle() == null) {
               ct.setParticle(group);
            } else {
               ct.setParticle(group.getParticle());
            }
         } else if (el.getLocalName().equals("simpleContent")) {
            ct.setContentModel(this.handleSimpleContent(schema, el, schemaEl));
         } else if (el.getLocalName().equals("complexContent")) {
            ct.setContentModel(this.handleComplexContent(schema, el, schemaEl));
         } else if (el.getLocalName().equals("annotation")) {
            ct.setAnnotation(this.handleAnnotation(el));
         } else if (el.getLocalName().equals("anyAttribute")) {
            ct.setAnyAttribute(this.handleAnyAttribute(schema, el, schemaEl));
         }
      }

      if (complexEl.hasAttribute("block")) {
         String blockStr = complexEl.getAttribute("block");
         ct.setBlock(XmlSchemaDerivationMethod.schemaValueOf(blockStr));
      }

      if (complexEl.hasAttribute("final")) {
         String finalstr = complexEl.getAttribute("final");
         ct.setFinal(XmlSchemaDerivationMethod.schemaValueOf(finalstr));
      }

      if (complexEl.hasAttribute("abstract")) {
         String abs = complexEl.getAttribute("abstract");
         if (abs.equalsIgnoreCase("true")) {
            ct.setAbstract(true);
         } else {
            ct.setAbstract(false);
         }
      }

      if (complexEl.hasAttribute("mixed")) {
         String mixed = complexEl.getAttribute("mixed");
         if (mixed.equalsIgnoreCase("true")) {
            ct.setMixed(true);
         } else {
            ct.setMixed(false);
         }
      }

      this.processExtensibilityComponents(ct, complexEl, true);
      return ct;
   }

   XmlSchemaDocumentation handleDocumentation(Element content) {
      XmlSchemaDocumentation documentation = new XmlSchemaDocumentation();
      List<Node> markup = this.getChildren(content);
      if (!content.hasAttribute("source") && !content.hasAttribute("xml:lang") && markup == null) {
         return null;
      } else {
         documentation.setSource(this.getAttribute(content, "source"));
         documentation.setLanguage(this.getAttribute(content, "xml:lang"));
         documentation.setMarkup(new DocumentFragmentNodeList(content));
         return documentation;
      }
   }

   XmlSchemaElement handleElement(XmlSchema schema, Element el, Element schemaEl, boolean isGlobal) {
      XmlSchemaElement element = new XmlSchemaElement(schema, isGlobal);
      if (el.getAttributeNode("name") != null) {
         element.setName(el.getAttribute("name"));
      }

      boolean isQualified = schema.getElementFormDefault() == XmlSchemaForm.QUALIFIED;
      isQualified = this.handleElementForm(el, element, isQualified);
      this.handleElementName(isGlobal, element, isQualified);
      this.handleElementAnnotation(el, element);
      this.handleElementGlobalType(el, element);
      Element simpleTypeEl = XDOMUtil.getFirstChildElementNS(el, "http://www.w3.org/2001/XMLSchema", "simpleType");
      if (simpleTypeEl != null) {
         XmlSchemaSimpleType simpleType = this.handleSimpleType(schema, simpleTypeEl, schemaEl, false);
         element.setSchemaType(simpleType);
         element.setSchemaTypeName(simpleType.getQName());
      } else {
         Element complexTypeEl = XDOMUtil.getFirstChildElementNS(el, "http://www.w3.org/2001/XMLSchema", "complexType");
         if (complexTypeEl != null) {
            element.setSchemaType(this.handleComplexType(schema, complexTypeEl, schemaEl, false));
         }
      }

      Element keyEl = XDOMUtil.getFirstChildElementNS(el, "http://www.w3.org/2001/XMLSchema", "key");
      if (keyEl != null) {
         while(keyEl != null) {
            element.getConstraints().add(this.handleConstraint(keyEl, XmlSchemaKey.class));
            keyEl = XDOMUtil.getNextSiblingElementNS(keyEl, "http://www.w3.org/2001/XMLSchema", "key");
         }
      }

      Element keyrefEl = XDOMUtil.getFirstChildElementNS(el, "http://www.w3.org/2001/XMLSchema", "keyref");
      if (keyrefEl != null) {
         while(keyrefEl != null) {
            XmlSchemaKeyref keyRef = (XmlSchemaKeyref)this.handleConstraint(keyrefEl, XmlSchemaKeyref.class);
            if (keyrefEl.hasAttribute("refer")) {
               String name = keyrefEl.getAttribute("refer");
               keyRef.refer = this.getRefQName(name, (Node)el);
            }

            element.getConstraints().add(keyRef);
            keyrefEl = XDOMUtil.getNextSiblingElementNS(keyrefEl, "http://www.w3.org/2001/XMLSchema", "keyref");
         }
      }

      Element uniqueEl = XDOMUtil.getFirstChildElementNS(el, "http://www.w3.org/2001/XMLSchema", "unique");
      if (uniqueEl != null) {
         while(uniqueEl != null) {
            element.getConstraints().add(this.handleConstraint(uniqueEl, XmlSchemaUnique.class));
            uniqueEl = XDOMUtil.getNextSiblingElementNS(uniqueEl, "http://www.w3.org/2001/XMLSchema", "unique");
         }
      }

      if (el.hasAttribute("abstract")) {
         element.setAbstractElement(Boolean.valueOf(el.getAttribute("abstract")));
      }

      if (el.hasAttribute("block")) {
         element.setBlock(this.getDerivation(el, "block"));
      }

      if (el.hasAttribute("default")) {
         element.setDefaultValue(el.getAttribute("default"));
      }

      if (el.hasAttribute("final")) {
         element.setFinalDerivation(this.getDerivation(el, "final"));
      }

      if (el.hasAttribute("fixed")) {
         element.setFixedValue(el.getAttribute("fixed"));
      }

      if (el.hasAttribute("id")) {
         element.setId(el.getAttribute("id"));
      }

      if (el.hasAttribute("nillable")) {
         element.setNillable(Boolean.valueOf(el.getAttribute("nillable")));
      }

      if (el.hasAttribute("substitutionGroup")) {
         String substitutionGroup = el.getAttribute("substitutionGroup");
         element.setSubstitutionGroup(this.getRefQName(substitutionGroup, (Node)el));
      }

      element.setMinOccurs(this.getMinOccurs(el));
      element.setMaxOccurs(this.getMaxOccurs(el));
      this.processExtensibilityComponents(element, el, true);
      return element;
   }

   XmlSchemaImport handleImport(XmlSchema schema, Element importEl, Element schemaEl) {
      XmlSchemaImport schemaImport = new XmlSchemaImport(schema);
      Element annotationEl = XDOMUtil.getFirstChildElementNS(importEl, "http://www.w3.org/2001/XMLSchema", "annotation");
      if (annotationEl != null) {
         XmlSchemaAnnotation importAnnotation = this.handleAnnotation(annotationEl);
         schemaImport.setAnnotation(importAnnotation);
      }

      schemaImport.namespace = importEl.getAttribute("namespace");
      final String uri = schemaImport.namespace;
      schemaImport.schemaLocation = importEl.getAttribute("schemaLocation");
      TargetNamespaceValidator validator = new TargetNamespaceValidator() {
         public void validate(XmlSchema pSchema) {
            boolean valid;
            if (this.isEmpty(uri)) {
               valid = this.isEmpty(pSchema.getSyntacticalTargetNamespace());
            } else {
               valid = pSchema.getSyntacticalTargetNamespace().equals(uri);
            }

            if (!valid) {
               throw new XmlSchemaException("An imported schema was announced to have the namespace " + uri + ", but has the namespace " + pSchema.getSyntacticalTargetNamespace());
            }
         }

         private boolean isEmpty(String pValue) {
            return pValue == null || "".equals(pValue);
         }
      };
      if (schema.getSourceURI() != null) {
         schemaImport.schema = this.resolveXmlSchema(uri, schemaImport.schemaLocation, schema.getSourceURI(), validator);
      } else {
         schemaImport.schema = this.resolveXmlSchema(schemaImport.namespace, schemaImport.schemaLocation, validator);
      }

      return schemaImport;
   }

   XmlSchemaInclude handleInclude(XmlSchema schema, Element includeEl, Element schemaEl) {
      XmlSchemaInclude include = new XmlSchemaInclude(schema);
      Element annotationEl = XDOMUtil.getFirstChildElementNS(includeEl, "http://www.w3.org/2001/XMLSchema", "annotation");
      if (annotationEl != null) {
         XmlSchemaAnnotation includeAnnotation = this.handleAnnotation(annotationEl);
         include.setAnnotation(includeAnnotation);
      }

      include.schemaLocation = includeEl.getAttribute("schemaLocation");
      TargetNamespaceValidator validator = this.newIncludeValidator(schema);
      if (schema.getSourceURI() != null) {
         include.schema = this.resolveXmlSchema(schema.getLogicalTargetNamespace(), include.schemaLocation, schema.getSourceURI(), validator);
      } else {
         include.schema = this.resolveXmlSchema(schema.getLogicalTargetNamespace(), include.schemaLocation, validator);
      }

      this.processExtensibilityComponents(include, includeEl, true);
      return include;
   }

   XmlSchemaSimpleType handleSimpleType(XmlSchema schema, Element simpleEl, Element schemaEl, boolean topLevel) {
      XmlSchemaSimpleType simpleType = new XmlSchemaSimpleType(schema, topLevel);
      if (simpleEl.hasAttribute("name")) {
         simpleType.setName(simpleEl.getAttribute("name"));
      }

      this.handleSimpleTypeFinal(simpleEl, simpleType);
      Element simpleTypeAnnotationEl = XDOMUtil.getFirstChildElementNS(simpleEl, "http://www.w3.org/2001/XMLSchema", "annotation");
      if (simpleTypeAnnotationEl != null) {
         XmlSchemaAnnotation simpleTypeAnnotation = this.handleAnnotation(simpleTypeAnnotationEl);
         simpleType.setAnnotation(simpleTypeAnnotation);
      }

      Element restrictionEl = XDOMUtil.getFirstChildElementNS(simpleEl, "http://www.w3.org/2001/XMLSchema", "restriction");
      Element listEl = XDOMUtil.getFirstChildElementNS(simpleEl, "http://www.w3.org/2001/XMLSchema", "list");
      Element unionEl = XDOMUtil.getFirstChildElementNS(simpleEl, "http://www.w3.org/2001/XMLSchema", "union");
      if (restrictionEl != null) {
         this.handleSimpleTypeRestriction(schema, schemaEl, simpleType, restrictionEl);
      } else if (listEl != null) {
         this.handleSimpleTypeList(schema, schemaEl, simpleType, listEl);
      } else if (unionEl != null) {
         this.handleSimpleTypeUnion(schema, schemaEl, simpleType, unionEl);
      }

      this.processExtensibilityComponents(simpleType, simpleEl, true);
      return simpleType;
   }

   XmlSchema handleXmlSchemaElement(Element schemaEl, String systemId) {
      this.currentSchema.setNamespaceContext(NodeNamespaceContext.getNamespaceContext(schemaEl));
      this.setNamespaceAttributes(this.currentSchema, schemaEl);
      XmlSchemaCollection.SchemaKey schemaKey = new XmlSchemaCollection.SchemaKey(this.currentSchema.getLogicalTargetNamespace(), systemId);
      this.handleSchemaElementBasics(schemaEl, systemId, schemaKey);
      Element el = XDOMUtil.getFirstChildElementNS(schemaEl, "http://www.w3.org/2001/XMLSchema");
      if (el == null && XDOMUtil.getFirstChildElementNS(schemaEl, "http://www.w3.org/1999/XMLSchema") != null) {
         throw new XmlSchemaException("Schema defined using \"http://www.w3.org/1999/XMLSchema\" is not supported. Please update the schema to the \"http://www.w3.org/2001/XMLSchema\" namespace");
      } else {
         while(el != null) {
            this.handleSchemaElementChild(schemaEl, el);
            el = XDOMUtil.getNextSiblingElementNS(el, "http://www.w3.org/2001/XMLSchema");
         }

         this.processExtensibilityComponents(this.currentSchema, schemaEl, false);
         return this.currentSchema;
      }
   }

   XmlSchema resolveXmlSchema(String targetNamespace, String schemaLocation, String baseUri, TargetNamespaceValidator validator) {
      if (this.getCachedSchema(targetNamespace, schemaLocation, baseUri) != null) {
         return this.getCachedSchema(targetNamespace, schemaLocation, baseUri);
      } else {
         if (schemaLocation != null && !"".equals(schemaLocation)) {
            InputSource source = this.collection.getSchemaResolver().resolveEntity(targetNamespace, schemaLocation, baseUri);
            if (source == null) {
               return this.collection.getKnownSchema(targetNamespace);
            }

            String systemId = source.getSystemId() == null ? schemaLocation : source.getSystemId();
            source.setSystemId(systemId);
            XmlSchemaCollection.SchemaKey key = new XmlSchemaCollection.SchemaKey(targetNamespace, systemId);
            XmlSchema schema = this.collection.getSchema(key);
            if (schema != null) {
               return schema;
            }

            if (this.collection.check(key)) {
               this.collection.push(key);

               XmlSchema var10;
               try {
                  XmlSchema readSchema = this.collection.read(source, validator);
                  this.putCachedSchema(targetNamespace, schemaLocation, baseUri, readSchema);
                  var10 = readSchema;
               } finally {
                  this.collection.pop();
               }

               return var10;
            }
         } else {
            XmlSchema schema = this.collection.getKnownSchema(targetNamespace);
            if (schema != null) {
               return schema;
            }
         }

         return null;
      }
   }

   XmlSchema resolveXmlSchema(String targetNamespace, String schemaLocation, TargetNamespaceValidator validator) {
      return this.resolveXmlSchema(targetNamespace, schemaLocation, this.collection.baseUri, validator);
   }

   void setNamespaceAttributes(XmlSchema schema, Element schemaEl) {
      if (schemaEl.getAttributeNode("targetNamespace") != null) {
         String contain = schemaEl.getAttribute("targetNamespace");
         schema.setTargetNamespace(contain);
      }

      if (this.currentValidator != null) {
         this.currentValidator.validate(schema);
      }

   }

   private String getAttribute(Element content, String attrName) {
      return content.hasAttribute(attrName) ? content.getAttribute(attrName) : null;
   }

   private XmlSchema getCachedSchema(String targetNamespace, String schemaLocation, String baseUri) {
      XmlSchema resolvedSchema = null;
      if (resolvedSchemas != null) {
         Map<String, SoftReference<XmlSchema>> threadResolvedSchemas = (Map)resolvedSchemas.get();
         if (threadResolvedSchemas != null) {
            String schemaKey = targetNamespace + schemaLocation + baseUri;
            SoftReference<XmlSchema> softref = (SoftReference)threadResolvedSchemas.get(schemaKey);
            if (softref != null) {
               resolvedSchema = (XmlSchema)softref.get();
            }
         }
      }

      return resolvedSchema;
   }

   private List getChildren(Element content) {
      List<Node> result = new ArrayList();

      for(Node n = content.getFirstChild(); n != null; n = n.getNextSibling()) {
         result.add(n);
      }

      return result.size() == 0 ? null : result;
   }

   private QName getRefQName(String pName, NamespaceContext pContext) {
      int offset = pName.indexOf(58);
      String uri;
      String localName;
      String prefix;
      if (offset == -1) {
         uri = pContext.getNamespaceURI("");
         if ("".equals(uri)) {
            if (this.currentSchema.getTargetNamespace() == null && !this.currentSchema.getLogicalTargetNamespace().isEmpty()) {
               return new QName(this.currentSchema.getLogicalTargetNamespace(), pName);
            }

            return new QName("", pName);
         }

         localName = pName;
         prefix = "";
      } else {
         prefix = pName.substring(0, offset);
         uri = pContext.getNamespaceURI(prefix);
         if (uri == null || "".equals(uri) && this.currentSchema.getParent() != null && this.currentSchema.getParent().getNamespaceContext() != null) {
            uri = this.currentSchema.getParent().getNamespaceContext().getNamespaceURI(prefix);
         }

         if (uri == null || "".equals(uri)) {
            throw new IllegalStateException("The prefix " + prefix + " is not bound.");
         }

         localName = pName.substring(offset + 1);
      }

      return new QName(uri, localName, prefix);
   }

   private QName getRefQName(String pName, Node pNode) {
      return this.getRefQName(pName, (NamespaceContext)NodeNamespaceContext.getNamespaceContext(pNode));
   }

   private XmlSchemaAll handleAll(XmlSchema schema, Element allEl, Element schemaEl) {
      XmlSchemaAll all = new XmlSchemaAll();
      all.setMinOccurs(this.getMinOccurs(allEl));
      all.setMaxOccurs(this.getMaxOccurs(allEl));

      for(Element el = XDOMUtil.getFirstChildElementNS(allEl, "http://www.w3.org/2001/XMLSchema"); el != null; el = XDOMUtil.getNextSiblingElementNS(el, "http://www.w3.org/2001/XMLSchema")) {
         if (el.getLocalName().equals("element")) {
            XmlSchemaElement element = this.handleElement(schema, el, schemaEl, false);
            all.getItems().add(element);
         } else if (el.getLocalName().equals("annotation")) {
            XmlSchemaAnnotation annotation = this.handleAnnotation(el);
            all.setAnnotation(annotation);
         }
      }

      return all;
   }

   private XmlSchemaAny handleAny(XmlSchema schema, Element anyEl, Element schemaEl) {
      XmlSchemaAny any = new XmlSchemaAny();
      any.setTargetNamespace(schema.getLogicalTargetNamespace());
      if (anyEl.hasAttribute("namespace")) {
         any.setNamespace(anyEl.getAttribute("namespace"));
      }

      if (anyEl.hasAttribute("processContents")) {
         String processContent = this.getEnumString(anyEl, "processContents");
         any.setProcessContent(XmlSchemaContentProcessing.schemaValueOf(processContent));
      }

      Element annotationEl = XDOMUtil.getFirstChildElementNS(anyEl, "http://www.w3.org/2001/XMLSchema", "annotation");
      if (annotationEl != null) {
         XmlSchemaAnnotation annotation = this.handleAnnotation(annotationEl);
         any.setAnnotation(annotation);
      }

      any.setMinOccurs(this.getMinOccurs(anyEl));
      any.setMaxOccurs(this.getMaxOccurs(anyEl));
      return any;
   }

   private XmlSchemaAnyAttribute handleAnyAttribute(XmlSchema schema, Element anyAttrEl, Element schemaEl) {
      XmlSchemaAnyAttribute anyAttr = new XmlSchemaAnyAttribute();
      if (anyAttrEl.hasAttribute("namespace")) {
         anyAttr.namespace = anyAttrEl.getAttribute("namespace");
      }

      if (anyAttrEl.hasAttribute("processContents")) {
         String contentProcessing = this.getEnumString(anyAttrEl, "processContents");
         anyAttr.processContent = XmlSchemaContentProcessing.schemaValueOf(contentProcessing);
      }

      if (anyAttrEl.hasAttribute("id")) {
         anyAttr.setId(anyAttrEl.getAttribute("id"));
      }

      Element annotationEl = XDOMUtil.getFirstChildElementNS(anyAttrEl, "http://www.w3.org/2001/XMLSchema", "annotation");
      if (annotationEl != null) {
         XmlSchemaAnnotation annotation = this.handleAnnotation(annotationEl);
         anyAttr.setAnnotation(annotation);
      }

      return anyAttr;
   }

   private XmlSchemaAttribute handleAttribute(XmlSchema schema, Element attrEl, Element schemaEl) {
      return this.handleAttribute(schema, attrEl, schemaEl, false);
   }

   private XmlSchemaAttribute handleAttribute(XmlSchema schema, Element attrEl, Element schemaEl, boolean topLevel) {
      XmlSchemaAttribute attr = new XmlSchemaAttribute(schema, topLevel);
      if (attrEl.hasAttribute("name")) {
         String name = attrEl.getAttribute("name");
         attr.setName(name);
      }

      if (attrEl.hasAttribute("type")) {
         String name = attrEl.getAttribute("type");
         attr.setSchemaTypeName(this.getRefQName(name, (Node)attrEl));
      }

      if (attrEl.hasAttribute("default")) {
         attr.setDefaultValue(attrEl.getAttribute("default"));
      }

      if (attrEl.hasAttribute("fixed")) {
         attr.setFixedValue(attrEl.getAttribute("fixed"));
      }

      if (attrEl.hasAttribute("form")) {
         String formValue = this.getEnumString(attrEl, "form");
         attr.setForm(XmlSchemaForm.schemaValueOf(formValue));
      }

      if (attrEl.hasAttribute("id")) {
         attr.setId(attrEl.getAttribute("id"));
      }

      if (attrEl.hasAttribute("use")) {
         String useType = this.getEnumString(attrEl, "use");
         attr.setUse(XmlSchemaUse.schemaValueOf(useType));
      }

      if (attrEl.hasAttribute("ref")) {
         String name = attrEl.getAttribute("ref");
         attr.getRef().setTargetQName(this.getRefQName(name, (Node)attrEl));
      }

      Element simpleTypeEl = XDOMUtil.getFirstChildElementNS(attrEl, "http://www.w3.org/2001/XMLSchema", "simpleType");
      if (simpleTypeEl != null) {
         attr.setSchemaType(this.handleSimpleType(schema, simpleTypeEl, schemaEl, false));
      }

      Element annotationEl = XDOMUtil.getFirstChildElementNS(attrEl, "http://www.w3.org/2001/XMLSchema", "annotation");
      if (annotationEl != null) {
         XmlSchemaAnnotation annotation = this.handleAnnotation(annotationEl);
         attr.setAnnotation(annotation);
      }

      NamedNodeMap attrNodes = attrEl.getAttributes();
      List<Attr> attrs = new ArrayList();
      NodeNamespaceContext ctx = null;

      for(int i = 0; i < attrNodes.getLength(); ++i) {
         Attr att = (Attr)attrNodes.item(i);
         String attName = att.getName();
         if (!RESERVED_ATTRIBUTES.contains(attName)) {
            attrs.add(att);
            String value = att.getValue();
            if (value.indexOf(":") > -1) {
               String prefix = value.substring(0, value.indexOf(":"));
               if (ctx == null) {
                  ctx = NodeNamespaceContext.getNamespaceContext(attrEl);
               }

               String namespace = ctx.getNamespaceURI(prefix);
               if (!"".equals(namespace)) {
                  Attr nsAttr = attrEl.getOwnerDocument().createAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:" + prefix);
                  nsAttr.setValue(namespace);
                  attrs.add(nsAttr);
               }
            }
         }
      }

      if (attrs.size() > 0) {
         attr.setUnhandledAttributes((Attr[])attrs.toArray(new Attr[attrs.size()]));
      }

      this.processExtensibilityComponents(attr, attrEl, true);
      return attr;
   }

   private XmlSchemaAttributeGroup handleAttributeGroup(XmlSchema schema, Element groupEl, Element schemaEl) {
      XmlSchemaAttributeGroup attrGroup = new XmlSchemaAttributeGroup(schema);
      if (groupEl.hasAttribute("name")) {
         attrGroup.setName(groupEl.getAttribute("name"));
      }

      if (groupEl.hasAttribute("id")) {
         attrGroup.setId(groupEl.getAttribute("id"));
      }

      for(Element el = XDOMUtil.getFirstChildElementNS(groupEl, "http://www.w3.org/2001/XMLSchema"); el != null; el = XDOMUtil.getNextSiblingElementNS(el, "http://www.w3.org/2001/XMLSchema")) {
         if (el.getLocalName().equals("attribute")) {
            XmlSchemaAttribute attr = this.handleAttribute(schema, el, schemaEl);
            attrGroup.getAttributes().add(attr);
         } else if (el.getLocalName().equals("attributeGroup")) {
            XmlSchemaAttributeGroupRef attrGroupRef = this.handleAttributeGroupRef(schema, el);
            attrGroup.getAttributes().add(attrGroupRef);
         } else if (el.getLocalName().equals("anyAttribute")) {
            attrGroup.setAnyAttribute(this.handleAnyAttribute(schema, el, schemaEl));
         } else if (el.getLocalName().equals("annotation")) {
            XmlSchemaAnnotation ann = this.handleAnnotation(el);
            attrGroup.setAnnotation(ann);
         }
      }

      return attrGroup;
   }

   private XmlSchemaAttributeGroupRef handleAttributeGroupRef(XmlSchema schema, Element attrGroupEl) {
      XmlSchemaAttributeGroupRef attrGroup = new XmlSchemaAttributeGroupRef(schema);
      if (attrGroupEl.hasAttribute("ref")) {
         String ref = attrGroupEl.getAttribute("ref");
         attrGroup.getRef().setTargetQName(this.getRefQName(ref, (Node)attrGroupEl));
      }

      if (attrGroupEl.hasAttribute("id")) {
         attrGroup.setId(attrGroupEl.getAttribute("id"));
      }

      Element annotationEl = XDOMUtil.getFirstChildElementNS(attrGroupEl, "http://www.w3.org/2001/XMLSchema", "annotation");
      if (annotationEl != null) {
         XmlSchemaAnnotation annotation = this.handleAnnotation(annotationEl);
         attrGroup.setAnnotation(annotation);
      }

      return attrGroup;
   }

   private XmlSchemaChoice handleChoice(XmlSchema schema, Element choiceEl, Element schemaEl) {
      XmlSchemaChoice choice = new XmlSchemaChoice();
      if (choiceEl.hasAttribute("id")) {
         choice.setId(choiceEl.getAttribute("id"));
      }

      choice.setMinOccurs(this.getMinOccurs(choiceEl));
      choice.setMaxOccurs(this.getMaxOccurs(choiceEl));

      for(Element el = XDOMUtil.getFirstChildElementNS(choiceEl, "http://www.w3.org/2001/XMLSchema"); el != null; el = XDOMUtil.getNextSiblingElementNS(el, "http://www.w3.org/2001/XMLSchema")) {
         if (el.getLocalName().equals("sequence")) {
            XmlSchemaSequence seq = this.handleSequence(schema, el, schemaEl);
            choice.getItems().add(seq);
         } else if (el.getLocalName().equals("element")) {
            XmlSchemaElement element = this.handleElement(schema, el, schemaEl, false);
            choice.getItems().add(element);
         } else if (el.getLocalName().equals("group")) {
            XmlSchemaGroupRef group = this.handleGroupRef(schema, el, schemaEl);
            choice.getItems().add(group);
         } else if (el.getLocalName().equals("choice")) {
            XmlSchemaChoice choiceItem = this.handleChoice(schema, el, schemaEl);
            choice.getItems().add(choiceItem);
         } else if (el.getLocalName().equals("any")) {
            XmlSchemaAny any = this.handleAny(schema, el, schemaEl);
            choice.getItems().add(any);
         } else if (el.getLocalName().equals("annotation")) {
            XmlSchemaAnnotation annotation = this.handleAnnotation(el);
            choice.setAnnotation(annotation);
         }
      }

      return choice;
   }

   private XmlSchemaComplexContent handleComplexContent(XmlSchema schema, Element complexEl, Element schemaEl) {
      XmlSchemaComplexContent complexContent = new XmlSchemaComplexContent();

      for(Element el = XDOMUtil.getFirstChildElementNS(complexEl, "http://www.w3.org/2001/XMLSchema"); el != null; el = XDOMUtil.getNextSiblingElementNS(el, "http://www.w3.org/2001/XMLSchema")) {
         if (el.getLocalName().equals("restriction")) {
            complexContent.content = this.handleComplexContentRestriction(schema, el, schemaEl);
         } else if (el.getLocalName().equals("extension")) {
            complexContent.content = this.handleComplexContentExtension(schema, el, schemaEl);
         } else if (el.getLocalName().equals("annotation")) {
            complexContent.setAnnotation(this.handleAnnotation(el));
         }
      }

      if (complexEl.hasAttribute("mixed")) {
         String mixed = complexEl.getAttribute("mixed");
         if (mixed.equalsIgnoreCase("true")) {
            complexContent.setMixed(true);
         } else {
            complexContent.setMixed(false);
         }
      }

      return complexContent;
   }

   private XmlSchemaComplexContentExtension handleComplexContentExtension(XmlSchema schema, Element extEl, Element schemaEl) {
      XmlSchemaComplexContentExtension ext = new XmlSchemaComplexContentExtension();
      if (extEl.hasAttribute("base")) {
         String name = extEl.getAttribute("base");
         ext.setBaseTypeName(this.getRefQName(name, (Node)extEl));
      }

      for(Element el = XDOMUtil.getFirstChildElementNS(extEl, "http://www.w3.org/2001/XMLSchema"); el != null; el = XDOMUtil.getNextSiblingElementNS(el, "http://www.w3.org/2001/XMLSchema")) {
         if (el.getLocalName().equals("sequence")) {
            ext.setParticle(this.handleSequence(schema, el, schemaEl));
         } else if (el.getLocalName().equals("choice")) {
            ext.setParticle(this.handleChoice(schema, el, schemaEl));
         } else if (el.getLocalName().equals("all")) {
            ext.setParticle(this.handleAll(schema, el, schemaEl));
         } else if (el.getLocalName().equals("attribute")) {
            ext.getAttributes().add(this.handleAttribute(schema, el, schemaEl));
         } else if (el.getLocalName().equals("attributeGroup")) {
            ext.getAttributes().add(this.handleAttributeGroupRef(schema, el));
         } else if (el.getLocalName().equals("group")) {
            ext.setParticle(this.handleGroupRef(schema, el, schemaEl));
         } else if (el.getLocalName().equals("anyAttribute")) {
            ext.setAnyAttribute(this.handleAnyAttribute(schema, el, schemaEl));
         } else if (el.getLocalName().equals("annotation")) {
            ext.setAnnotation(this.handleAnnotation(el));
         }
      }

      return ext;
   }

   private XmlSchemaComplexContentRestriction handleComplexContentRestriction(XmlSchema schema, Element restrictionEl, Element schemaEl) {
      XmlSchemaComplexContentRestriction restriction = new XmlSchemaComplexContentRestriction();
      if (restrictionEl.hasAttribute("base")) {
         String name = restrictionEl.getAttribute("base");
         restriction.setBaseTypeName(this.getRefQName(name, (Node)restrictionEl));
      }

      for(Element el = XDOMUtil.getFirstChildElementNS(restrictionEl, "http://www.w3.org/2001/XMLSchema"); el != null; el = XDOMUtil.getNextSiblingElementNS(el, "http://www.w3.org/2001/XMLSchema")) {
         if (el.getLocalName().equals("sequence")) {
            restriction.setParticle(this.handleSequence(schema, el, schemaEl));
         } else if (el.getLocalName().equals("choice")) {
            restriction.setParticle(this.handleChoice(schema, el, schemaEl));
         } else if (el.getLocalName().equals("all")) {
            restriction.setParticle(this.handleAll(schema, el, schemaEl));
         } else if (el.getLocalName().equals("attribute")) {
            restriction.getAttributes().add(this.handleAttribute(schema, el, schemaEl));
         } else if (el.getLocalName().equals("attributeGroup")) {
            restriction.getAttributes().add(this.handleAttributeGroupRef(schema, el));
         } else if (el.getLocalName().equals("group")) {
            restriction.setParticle(this.handleGroupRef(schema, el, schemaEl));
         } else if (el.getLocalName().equals("anyAttribute")) {
            restriction.setAnyAttribute(this.handleAnyAttribute(schema, el, schemaEl));
         } else if (el.getLocalName().equals("annotation")) {
            restriction.setAnnotation(this.handleAnnotation(el));
         }
      }

      return restriction;
   }

   private XmlSchemaIdentityConstraint handleConstraint(Element constraintEl, Class typeClass) {
      try {
         XmlSchemaIdentityConstraint constraint = (XmlSchemaIdentityConstraint)typeClass.newInstance();
         if (constraintEl.hasAttribute("name")) {
            constraint.setName(constraintEl.getAttribute("name"));
         }

         if (constraintEl.hasAttribute("refer")) {
            String name = constraintEl.getAttribute("refer");
            ((XmlSchemaKeyref)constraint).refer = this.getRefQName(name, (Node)constraintEl);
         }

         for(Element el = XDOMUtil.getFirstChildElementNS(constraintEl, "http://www.w3.org/2001/XMLSchema"); el != null; el = XDOMUtil.getNextSiblingElementNS(el, "http://www.w3.org/2001/XMLSchema")) {
            if (el.getLocalName().equals("selector")) {
               XmlSchemaXPath selectorXPath = new XmlSchemaXPath();
               selectorXPath.xpath = el.getAttribute("xpath");
               Element annotationEl = XDOMUtil.getFirstChildElementNS(el, "http://www.w3.org/2001/XMLSchema", "annotation");
               if (annotationEl != null) {
                  XmlSchemaAnnotation annotation = this.handleAnnotation(annotationEl);
                  selectorXPath.setAnnotation(annotation);
               }

               constraint.setSelector(selectorXPath);
            } else if (el.getLocalName().equals("field")) {
               XmlSchemaXPath fieldXPath = new XmlSchemaXPath();
               fieldXPath.xpath = el.getAttribute("xpath");
               constraint.getFields().add(fieldXPath);
               Element annotationEl = XDOMUtil.getFirstChildElementNS(el, "http://www.w3.org/2001/XMLSchema", "annotation");
               if (annotationEl != null) {
                  XmlSchemaAnnotation annotation = this.handleAnnotation(annotationEl);
                  fieldXPath.setAnnotation(annotation);
               }
            } else if (el.getLocalName().equals("annotation")) {
               XmlSchemaAnnotation constraintAnnotation = this.handleAnnotation(el);
               constraint.setAnnotation(constraintAnnotation);
            }
         }

         return constraint;
      } catch (InstantiationException e) {
         throw new XmlSchemaException(e.getMessage());
      } catch (IllegalAccessException e) {
         throw new XmlSchemaException(e.getMessage());
      }
   }

   private void handleElementAnnotation(Element el, XmlSchemaElement element) {
      Element annotationEl = XDOMUtil.getFirstChildElementNS(el, "http://www.w3.org/2001/XMLSchema", "annotation");
      if (annotationEl != null) {
         XmlSchemaAnnotation annotation = this.handleAnnotation(annotationEl);
         element.setAnnotation(annotation);
      }

   }

   private boolean handleElementForm(Element el, XmlSchemaElement element, boolean isQualified) {
      if (el.hasAttribute("form")) {
         String formDef = el.getAttribute("form");
         element.setForm(XmlSchemaForm.schemaValueOf(formDef));
      }

      isQualified = element.getForm() == XmlSchemaForm.QUALIFIED;
      return isQualified;
   }

   private void handleElementGlobalType(Element el, XmlSchemaElement element) {
      if (el.getAttributeNode("type") != null) {
         String typeName = el.getAttribute("type");
         element.setSchemaTypeName(this.getRefQName(typeName, (Node)el));
         QName typeQName = element.getSchemaTypeName();
         XmlSchemaType type = this.collection.getTypeByQName(typeQName);
         if (type == null) {
            this.collection.addUnresolvedType(typeQName, element);
         }

         element.setSchemaType(type);
      } else if (el.getAttributeNode("ref") != null) {
         String refName = el.getAttribute("ref");
         QName refQName = this.getRefQName(refName, (Node)el);
         element.getRef().setTargetQName(refQName);
      }

   }

   private void handleElementName(boolean isGlobal, XmlSchemaElement element, boolean isQualified) {
   }

   private XmlSchemaGroup handleGroup(XmlSchema schema, Element groupEl, Element schemaEl) {
      XmlSchemaGroup group = new XmlSchemaGroup(schema);
      group.setName(groupEl.getAttribute("name"));

      for(Element el = XDOMUtil.getFirstChildElementNS(groupEl, "http://www.w3.org/2001/XMLSchema"); el != null; el = XDOMUtil.getNextSiblingElementNS(el, "http://www.w3.org/2001/XMLSchema")) {
         if (el.getLocalName().equals("all")) {
            group.setParticle(this.handleAll(schema, el, schemaEl));
         } else if (el.getLocalName().equals("sequence")) {
            group.setParticle(this.handleSequence(schema, el, schemaEl));
         } else if (el.getLocalName().equals("choice")) {
            group.setParticle(this.handleChoice(schema, el, schemaEl));
         } else if (el.getLocalName().equals("annotation")) {
            XmlSchemaAnnotation groupAnnotation = this.handleAnnotation(el);
            group.setAnnotation(groupAnnotation);
         }
      }

      return group;
   }

   private XmlSchemaGroupRef handleGroupRef(XmlSchema schema, Element groupEl, Element schemaEl) {
      XmlSchemaGroupRef group = new XmlSchemaGroupRef();
      group.setMaxOccurs(this.getMaxOccurs(groupEl));
      group.setMinOccurs(this.getMinOccurs(groupEl));
      Element annotationEl = XDOMUtil.getFirstChildElementNS(groupEl, "http://www.w3.org/2001/XMLSchema", "annotation");
      if (annotationEl != null) {
         XmlSchemaAnnotation annotation = this.handleAnnotation(annotationEl);
         group.setAnnotation(annotation);
      }

      if (groupEl.hasAttribute("ref")) {
         String ref = groupEl.getAttribute("ref");
         group.setRefName(this.getRefQName(ref, (Node)groupEl));
         return group;
      } else {
         for(Element el = XDOMUtil.getFirstChildElementNS(groupEl, "http://www.w3.org/2001/XMLSchema"); el != null; el = XDOMUtil.getNextSiblingElement(el)) {
            if (el.getLocalName().equals("sequence")) {
               group.setParticle(this.handleSequence(schema, el, schemaEl));
            } else if (el.getLocalName().equals("all")) {
               group.setParticle(this.handleAll(schema, el, schemaEl));
            } else if (el.getLocalName().equals("choice")) {
               group.setParticle(this.handleChoice(schema, el, schemaEl));
            }
         }

         return group;
      }
   }

   private XmlSchemaNotation handleNotation(XmlSchema schema, Element notationEl) {
      XmlSchemaNotation notation = new XmlSchemaNotation(schema);
      if (notationEl.hasAttribute("id")) {
         notation.setId(notationEl.getAttribute("id"));
      }

      if (notationEl.hasAttribute("name")) {
         notation.setName(notationEl.getAttribute("name"));
      }

      if (notationEl.hasAttribute("public")) {
         notation.setPublicNotation(notationEl.getAttribute("public"));
      }

      if (notationEl.hasAttribute("system")) {
         notation.setSystem(notationEl.getAttribute("system"));
      }

      Element annotationEl = XDOMUtil.getFirstChildElementNS(notationEl, "http://www.w3.org/2001/XMLSchema", "annotation");
      if (annotationEl != null) {
         XmlSchemaAnnotation annotation = this.handleAnnotation(annotationEl);
         notation.setAnnotation(annotation);
      }

      return notation;
   }

   private XmlSchemaRedefine handleRedefine(XmlSchema schema, Element redefineEl, Element schemaEl) {
      XmlSchemaRedefine redefine = new XmlSchemaRedefine(schema);
      redefine.schemaLocation = redefineEl.getAttribute("schemaLocation");
      TargetNamespaceValidator validator = this.newIncludeValidator(schema);
      if (schema.getSourceURI() != null) {
         redefine.schema = this.resolveXmlSchema(schema.getLogicalTargetNamespace(), redefine.schemaLocation, schema.getSourceURI(), validator);
      } else {
         redefine.schema = this.resolveXmlSchema(schema.getLogicalTargetNamespace(), redefine.schemaLocation, validator);
      }

      for(Element el = XDOMUtil.getFirstChildElementNS(redefineEl, "http://www.w3.org/2001/XMLSchema"); el != null; el = XDOMUtil.getNextSiblingElementNS(el, "http://www.w3.org/2001/XMLSchema")) {
         if (el.getLocalName().equals("simpleType")) {
            XmlSchemaType type = this.handleSimpleType(schema, el, schemaEl, false);
            redefine.getSchemaTypes().put(type.getQName(), type);
            redefine.getItems().add(type);
         } else if (el.getLocalName().equals("complexType")) {
            XmlSchemaType type = this.handleComplexType(schema, el, schemaEl, true);
            redefine.getSchemaTypes().put(type.getQName(), type);
            redefine.getItems().add(type);
         } else if (el.getLocalName().equals("group")) {
            XmlSchemaGroup group = this.handleGroup(schema, el, schemaEl);
            redefine.getGroups().put(group.getQName(), group);
            redefine.getItems().add(group);
         } else if (el.getLocalName().equals("attributeGroup")) {
            XmlSchemaAttributeGroup group = this.handleAttributeGroup(schema, el, schemaEl);
            redefine.getAttributeGroups().put(group.getQName(), group);
            redefine.getItems().add(group);
         } else if (el.getLocalName().equals("annotation")) {
            XmlSchemaAnnotation annotation = this.handleAnnotation(el);
            redefine.setAnnotation(annotation);
         }
      }

      return redefine;
   }

   private void handleSchemaElementBasics(Element schemaEl, String systemId, XmlSchemaCollection.SchemaKey schemaKey) {
      if (!this.collection.containsSchema(schemaKey)) {
         this.collection.addSchema(schemaKey, this.currentSchema);
         this.currentSchema.setParent(this.collection);
         this.currentSchema.setElementFormDefault(this.getFormDefault(schemaEl, "elementFormDefault"));
         this.currentSchema.setAttributeFormDefault(this.getFormDefault(schemaEl, "attributeFormDefault"));
         this.currentSchema.setBlockDefault(this.getDerivation(schemaEl, "blockDefault"));
         this.currentSchema.setFinalDefault(this.getDerivation(schemaEl, "finalDefault"));
         if (schemaEl.hasAttribute("id")) {
            this.currentSchema.setId(schemaEl.getAttribute("id"));
         }

         if (schemaEl.hasAttribute("version")) {
            this.currentSchema.setVersion(schemaEl.getAttribute("version"));
         }

         this.currentSchema.setSourceURI(systemId);
      } else {
         throw new XmlSchemaException("Schema name conflict in collection. Namespace: " + this.currentSchema.getLogicalTargetNamespace());
      }
   }

   private void handleSchemaElementChild(Element schemaEl, Element el) {
      if (el.getLocalName().equals("simpleType")) {
         XmlSchemaType type = this.handleSimpleType(this.currentSchema, el, schemaEl, true);
         this.collection.resolveType(type.getQName(), type);
      } else if (el.getLocalName().equals("complexType")) {
         XmlSchemaType type = this.handleComplexType(this.currentSchema, el, schemaEl, true);
         this.collection.resolveType(type.getQName(), type);
      } else if (el.getLocalName().equals("element")) {
         this.handleElement(this.currentSchema, el, schemaEl, true);
      } else if (el.getLocalName().equals("include")) {
         this.handleInclude(this.currentSchema, el, schemaEl);
      } else if (el.getLocalName().equals("import")) {
         this.handleImport(this.currentSchema, el, schemaEl);
      } else if (el.getLocalName().equals("group")) {
         this.handleGroup(this.currentSchema, el, schemaEl);
      } else if (el.getLocalName().equals("attributeGroup")) {
         this.handleAttributeGroup(this.currentSchema, el, schemaEl);
      } else if (el.getLocalName().equals("attribute")) {
         this.handleAttribute(this.currentSchema, el, schemaEl, true);
      } else if (el.getLocalName().equals("redefine")) {
         this.handleRedefine(this.currentSchema, el, schemaEl);
      } else if (el.getLocalName().equals("notation")) {
         this.handleNotation(this.currentSchema, el);
      } else if (el.getLocalName().equals("annotation")) {
         XmlSchemaAnnotation annotation = this.handleAnnotation(el);
         this.currentSchema.setAnnotation(annotation);
      }

   }

   private XmlSchemaSequence handleSequence(XmlSchema schema, Element sequenceEl, Element schemaEl) {
      XmlSchemaSequence sequence = new XmlSchemaSequence();
      sequence.setMinOccurs(this.getMinOccurs(sequenceEl));
      sequence.setMaxOccurs(this.getMaxOccurs(sequenceEl));

      for(Element el = XDOMUtil.getFirstChildElementNS(sequenceEl, "http://www.w3.org/2001/XMLSchema"); el != null; el = XDOMUtil.getNextSiblingElementNS(el, "http://www.w3.org/2001/XMLSchema")) {
         if (el.getLocalName().equals("sequence")) {
            XmlSchemaSequence seq = this.handleSequence(schema, el, schemaEl);
            sequence.getItems().add(seq);
         } else if (el.getLocalName().equals("element")) {
            XmlSchemaElement element = this.handleElement(schema, el, schemaEl, false);
            sequence.getItems().add(element);
         } else if (el.getLocalName().equals("group")) {
            XmlSchemaGroupRef group = this.handleGroupRef(schema, el, schemaEl);
            sequence.getItems().add(group);
         } else if (el.getLocalName().equals("choice")) {
            XmlSchemaChoice choice = this.handleChoice(schema, el, schemaEl);
            sequence.getItems().add(choice);
         } else if (el.getLocalName().equals("any")) {
            XmlSchemaAny any = this.handleAny(schema, el, schemaEl);
            sequence.getItems().add(any);
         } else if (el.getLocalName().equals("annotation")) {
            XmlSchemaAnnotation annotation = this.handleAnnotation(el);
            sequence.setAnnotation(annotation);
         }
      }

      return sequence;
   }

   private XmlSchemaSimpleContent handleSimpleContent(XmlSchema schema, Element simpleEl, Element schemaEl) {
      XmlSchemaSimpleContent simpleContent = new XmlSchemaSimpleContent();

      for(Element el = XDOMUtil.getFirstChildElementNS(simpleEl, "http://www.w3.org/2001/XMLSchema"); el != null; el = XDOMUtil.getNextSiblingElementNS(el, "http://www.w3.org/2001/XMLSchema")) {
         if (el.getLocalName().equals("restriction")) {
            simpleContent.content = this.handleSimpleContentRestriction(schema, el, schemaEl);
         } else if (el.getLocalName().equals("extension")) {
            simpleContent.content = this.handleSimpleContentExtension(schema, el, schemaEl);
         } else if (el.getLocalName().equals("annotation")) {
            simpleContent.setAnnotation(this.handleAnnotation(el));
         }
      }

      return simpleContent;
   }

   private XmlSchemaSimpleContentExtension handleSimpleContentExtension(XmlSchema schema, Element extEl, Element schemaEl) {
      XmlSchemaSimpleContentExtension ext = new XmlSchemaSimpleContentExtension();
      if (extEl.hasAttribute("base")) {
         String name = extEl.getAttribute("base");
         ext.setBaseTypeName(this.getRefQName(name, (Node)extEl));
      }

      for(Element el = XDOMUtil.getFirstChildElementNS(extEl, "http://www.w3.org/2001/XMLSchema"); el != null; el = XDOMUtil.getNextSiblingElementNS(el, "http://www.w3.org/2001/XMLSchema")) {
         if (el.getLocalName().equals("attribute")) {
            XmlSchemaAttribute attr = this.handleAttribute(schema, el, schemaEl);
            ext.getAttributes().add(attr);
         } else if (el.getLocalName().equals("attributeGroup")) {
            XmlSchemaAttributeGroupRef attrGroup = this.handleAttributeGroupRef(schema, el);
            ext.getAttributes().add(attrGroup);
         } else if (el.getLocalName().equals("anyAttribute")) {
            ext.setAnyAttribute(this.handleAnyAttribute(schema, el, schemaEl));
         } else if (el.getLocalName().equals("annotation")) {
            XmlSchemaAnnotation ann = this.handleAnnotation(el);
            ext.setAnnotation(ann);
         }
      }

      return ext;
   }

   private XmlSchemaSimpleContentRestriction handleSimpleContentRestriction(XmlSchema schema, Element restrictionEl, Element schemaEl) {
      XmlSchemaSimpleContentRestriction restriction = new XmlSchemaSimpleContentRestriction();
      if (restrictionEl.hasAttribute("base")) {
         String name = restrictionEl.getAttribute("base");
         restriction.setBaseTypeName(this.getRefQName(name, (Node)restrictionEl));
      }

      if (restrictionEl.hasAttribute("id")) {
         restriction.setId(restrictionEl.getAttribute("id"));
      }

      for(Element el = XDOMUtil.getFirstChildElementNS(restrictionEl, "http://www.w3.org/2001/XMLSchema"); el != null; el = XDOMUtil.getNextSiblingElementNS(el, "http://www.w3.org/2001/XMLSchema")) {
         if (el.getLocalName().equals("attribute")) {
            XmlSchemaAttribute attr = this.handleAttribute(schema, el, schemaEl);
            restriction.getAttributes().add(attr);
         } else if (el.getLocalName().equals("attributeGroup")) {
            XmlSchemaAttributeGroupRef attrGroup = this.handleAttributeGroupRef(schema, el);
            restriction.getAttributes().add(attrGroup);
         } else if (el.getLocalName().equals("simpleType")) {
            restriction.setBaseType(this.handleSimpleType(schema, el, schemaEl, false));
         } else if (el.getLocalName().equals("anyAttribute")) {
            restriction.anyAttribute = this.handleAnyAttribute(schema, el, schemaEl);
         } else if (el.getLocalName().equals("annotation")) {
            restriction.setAnnotation(this.handleAnnotation(el));
         } else {
            XmlSchemaFacet facet = XmlSchemaFacet.construct(el);
            Element annotation = XDOMUtil.getFirstChildElementNS(el, "http://www.w3.org/2001/XMLSchema", "annotation");
            if (annotation != null) {
               XmlSchemaAnnotation facetAnnotation = this.handleAnnotation(annotation);
               facet.setAnnotation(facetAnnotation);
            }

            restriction.getFacets().add(facet);
            this.processExtensibilityComponents(facet, el, true);
         }
      }

      return restriction;
   }

   private void handleSimpleTypeFinal(Element simpleEl, XmlSchemaSimpleType simpleType) {
      if (simpleEl.hasAttribute("final")) {
         String finalstr = simpleEl.getAttribute("final");
         simpleType.setFinal(XmlSchemaDerivationMethod.schemaValueOf(finalstr));
      }

   }

   private void handleSimpleTypeList(XmlSchema schema, Element schemaEl, XmlSchemaSimpleType simpleType, Element listEl) {
      XmlSchemaSimpleTypeList list = new XmlSchemaSimpleTypeList();
      Element inlineListType = XDOMUtil.getFirstChildElementNS(listEl, "http://www.w3.org/2001/XMLSchema", "simpleType");
      if (listEl.hasAttribute("itemType")) {
         String name = listEl.getAttribute("itemType");
         list.itemTypeName = this.getRefQName(name, (Node)listEl);
      } else if (inlineListType != null) {
         list.itemType = this.handleSimpleType(schema, inlineListType, schemaEl, false);
      }

      Element listAnnotationEl = XDOMUtil.getFirstChildElementNS(listEl, "http://www.w3.org/2001/XMLSchema", "annotation");
      if (listAnnotationEl != null) {
         XmlSchemaAnnotation listAnnotation = this.handleAnnotation(listAnnotationEl);
         list.setAnnotation(listAnnotation);
      }

      simpleType.content = list;
   }

   private void handleSimpleTypeRestriction(XmlSchema schema, Element schemaEl, XmlSchemaSimpleType simpleType, Element restrictionEl) {
      XmlSchemaSimpleTypeRestriction restriction = new XmlSchemaSimpleTypeRestriction();
      Element restAnnotationEl = XDOMUtil.getFirstChildElementNS(restrictionEl, "http://www.w3.org/2001/XMLSchema", "annotation");
      if (restAnnotationEl != null) {
         XmlSchemaAnnotation restAnnotation = this.handleAnnotation(restAnnotationEl);
         restriction.setAnnotation(restAnnotation);
      }

      Element inlineSimpleType = XDOMUtil.getFirstChildElementNS(restrictionEl, "http://www.w3.org/2001/XMLSchema", "simpleType");
      if (restrictionEl.hasAttribute("base")) {
         NamespaceContext ctx = NodeNamespaceContext.getNamespaceContext(restrictionEl);
         restriction.setBaseTypeName(this.getRefQName(restrictionEl.getAttribute("base"), ctx));
      } else if (inlineSimpleType != null) {
         restriction.setBaseType(this.handleSimpleType(schema, inlineSimpleType, schemaEl, false));
      }

      for(Element el = XDOMUtil.getFirstChildElementNS(restrictionEl, "http://www.w3.org/2001/XMLSchema"); el != null; el = XDOMUtil.getNextSiblingElementNS(el, "http://www.w3.org/2001/XMLSchema")) {
         if (!el.getLocalName().equals("annotation") && !el.getLocalName().equals("simpleType")) {
            XmlSchemaFacet facet = XmlSchemaFacet.construct(el);
            Element annotation = XDOMUtil.getFirstChildElementNS(el, "http://www.w3.org/2001/XMLSchema", "annotation");
            if (annotation != null) {
               XmlSchemaAnnotation facetAnnotation = this.handleAnnotation(annotation);
               facet.setAnnotation(facetAnnotation);
            }

            this.processExtensibilityComponents(facet, el, true);
            restriction.getFacets().add(facet);
         }
      }

      simpleType.content = restriction;
   }

   private void handleSimpleTypeUnion(XmlSchema schema, Element schemaEl, XmlSchemaSimpleType simpleType, Element unionEl) {
      XmlSchemaSimpleTypeUnion union = new XmlSchemaSimpleTypeUnion();
      if (unionEl.hasAttribute("memberTypes")) {
         String memberTypes = unionEl.getAttribute("memberTypes");
         union.setMemberTypesSource(memberTypes);
         List<QName> v = new ArrayList();
         StringTokenizer tokenizer = new StringTokenizer(memberTypes, " ");

         while(tokenizer.hasMoreTokens()) {
            String member = tokenizer.nextToken();
            v.add(this.getRefQName(member, (Node)unionEl));
         }

         union.setMemberTypesQNames((QName[])v.toArray(new QName[v.size()]));
      }

      for(Element inlineUnionType = XDOMUtil.getFirstChildElementNS(unionEl, "http://www.w3.org/2001/XMLSchema", "simpleType"); inlineUnionType != null; inlineUnionType = XDOMUtil.getNextSiblingElementNS(inlineUnionType, "http://www.w3.org/2001/XMLSchema", "simpleType")) {
         XmlSchemaSimpleType unionSimpleType = this.handleSimpleType(schema, inlineUnionType, schemaEl, false);
         union.getBaseTypes().add(unionSimpleType);
         if (!unionSimpleType.isAnonymous()) {
            union.setMemberTypesSource(union.getMemberTypesSource() + " " + unionSimpleType.getName());
         }
      }

      Element unionAnnotationEl = XDOMUtil.getFirstChildElementNS(unionEl, "http://www.w3.org/2001/XMLSchema", "annotation");
      if (unionAnnotationEl != null) {
         XmlSchemaAnnotation unionAnnotation = this.handleAnnotation(unionAnnotationEl);
         union.setAnnotation(unionAnnotation);
      }

      simpleType.content = union;
   }

   private TargetNamespaceValidator newIncludeValidator(final XmlSchema schema) {
      return new TargetNamespaceValidator() {
         public void validate(XmlSchema pSchema) {
            if (this.isEmpty(pSchema.getSyntacticalTargetNamespace())) {
               pSchema.setLogicalTargetNamespace(schema.getLogicalTargetNamespace());
            } else if (!pSchema.getSyntacticalTargetNamespace().equals(schema.getLogicalTargetNamespace())) {
               String msg = "An included schema was announced to have the default target namespace";
               if (!this.isEmpty(schema.getLogicalTargetNamespace())) {
                  msg = msg + " or the target namespace " + schema.getLogicalTargetNamespace();
               }

               throw new XmlSchemaException(msg + ", but has the target namespace " + pSchema.getLogicalTargetNamespace());
            }

         }

         private boolean isEmpty(String pValue) {
            return pValue == null || "".equals(pValue);
         }
      };
   }

   private void processExtensibilityComponents(XmlSchemaObject schemaObject, Element parentElement, boolean namespaces) {
      if (this.extReg != null) {
         NamedNodeMap attributes = parentElement.getAttributes();

         for(int i = 0; i < attributes.getLength(); ++i) {
            Attr attribute = (Attr)attributes.item(i);
            String namespaceURI = attribute.getNamespaceURI();
            String name = attribute.getLocalName();
            if (namespaceURI != null && !"".equals(namespaceURI) && (namespaces || !namespaceURI.startsWith("http://www.w3.org/2000/xmlns/")) && !"http://www.w3.org/2001/XMLSchema".equals(namespaceURI)) {
               QName qName = new QName(namespaceURI, name);
               this.extReg.deserializeExtension(schemaObject, qName, attribute);
            }
         }

         for(Node child = parentElement.getFirstChild(); child != null; child = child.getNextSibling()) {
            if (child.getNodeType() == 1) {
               Element extElement = (Element)child;
               String namespaceURI = extElement.getNamespaceURI();
               String name = extElement.getLocalName();
               if (namespaceURI != null && !"http://www.w3.org/2001/XMLSchema".equals(namespaceURI)) {
                  QName qName = new QName(namespaceURI, name);
                  this.extReg.deserializeExtension(schemaObject, qName, extElement);
               }
            }
         }
      }

   }

   private void putCachedSchema(String targetNamespace, String schemaLocation, String baseUri, XmlSchema readSchema) {
      if (resolvedSchemas != null) {
         Map<String, SoftReference<XmlSchema>> threadResolvedSchemas = (Map)resolvedSchemas.get();
         if (threadResolvedSchemas != null) {
            String schemaKey = targetNamespace + schemaLocation + baseUri;
            threadResolvedSchemas.put(schemaKey, new SoftReference(readSchema));
         }
      }

   }

   static {
      for(String s : RESERVED_ATTRIBUTES_LIST) {
         RESERVED_ATTRIBUTES.add(s);
      }

   }
}
