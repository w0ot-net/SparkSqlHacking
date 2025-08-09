package org.apache.ws.commons.schema;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.ws.commons.schema.extensions.ExtensionRegistry;
import org.apache.ws.commons.schema.utils.NamespacePrefixList;
import org.w3c.dom.Attr;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

public class XmlSchemaSerializer {
   public static final String XSD_NAMESPACE = "http://www.w3.org/2001/XMLSchema";
   private static final String XMLNS_NAMESPACE_URI = "http://www.w3.org/2000/xmlns/";
   String xsdPrefix = "xs";
   List docs = new ArrayList();
   Element schemaElement;
   private ExtensionRegistry extReg;
   private Map schemaNamespace = Collections.synchronizedMap(new HashMap());

   private static String[] getParts(String name) {
      String[] parts = new String[2];
      int index = name.indexOf(":");
      if (index > -1) {
         parts[0] = name.substring(0, index);
         parts[1] = name.substring(index + 1);
      } else {
         parts[0] = "";
         parts[1] = name;
      }

      return parts;
   }

   public ExtensionRegistry getExtReg() {
      return this.extReg;
   }

   public Document[] serializeSchema(XmlSchema schemaObj, boolean serializeIncluded) throws XmlSchemaSerializerException {
      return this.serializeSchemaElement(schemaObj, serializeIncluded);
   }

   public void setExtReg(ExtensionRegistry extReg) {
      this.extReg = extReg;
   }

   Element serializeAll(Document doc, XmlSchemaAll allObj, XmlSchema schema) throws XmlSchemaSerializerException {
      Element allEl = this.createNewElement(doc, "all", schema.getSchemaNamespacePrefix(), "http://www.w3.org/2001/XMLSchema");
      this.serializeMaxMinOccurs(allObj, allEl);
      if (allObj.getAnnotation() != null) {
         Element annotation = this.serializeAnnotation(doc, allObj.getAnnotation(), schema);
         allEl.appendChild(annotation);
      }

      List<XmlSchemaAllMember> itemColl = allObj.getItems();
      if (itemColl != null) {
         int itemLength = itemColl.size();

         for(int i = 0; i < itemLength; ++i) {
            XmlSchemaAllMember obj = (XmlSchemaAllMember)itemColl.get(i);
            if (obj instanceof XmlSchemaElement) {
               Element el = this.serializeElement(doc, (XmlSchemaElement)obj, schema);
               allEl.appendChild(el);
            } else if (obj instanceof XmlSchemaGroupRef) {
               Element group = this.serializeGroupRef(doc, (XmlSchemaGroupRef)obj, schema);
               allEl.appendChild(group);
            } else {
               if (!(obj instanceof XmlSchemaAny)) {
                  throw new XmlSchemaSerializerException("Only element allowed as child of all model type");
               }

               Element any = this.serializeAny(doc, (XmlSchemaAny)obj, schema);
               allEl.appendChild(any);
            }
         }
      }

      this.processExtensibilityComponents(allObj, allEl);
      return allEl;
   }

   Element serializeAnnotation(Document doc, XmlSchemaAnnotation annotationObj, XmlSchema schema) {
      Element annotation = this.createNewElement(doc, "annotation", schema.getSchemaNamespacePrefix(), "http://www.w3.org/2001/XMLSchema");
      List<XmlSchemaAnnotationItem> contents = annotationObj.getItems();
      int contentLength = contents.size();

      for(int i = 0; i < contentLength; ++i) {
         XmlSchemaObject obj = (XmlSchemaObject)contents.get(i);
         if (obj instanceof XmlSchemaAppInfo) {
            XmlSchemaAppInfo appinfo = (XmlSchemaAppInfo)obj;
            Element appInfoEl = this.serializeAppInfo(doc, appinfo, schema);
            annotation.appendChild(appInfoEl);
         } else if (obj instanceof XmlSchemaDocumentation) {
            XmlSchemaDocumentation documentation = (XmlSchemaDocumentation)obj;
            Element documentationEl = this.serializeDocumentation(doc, documentation, schema);
            annotation.appendChild(documentationEl);
         }
      }

      this.processExtensibilityComponents(annotationObj, annotation);
      return annotation;
   }

   Element serializeAny(Document doc, XmlSchemaAny anyObj, XmlSchema schema) {
      Element anyEl = this.createNewElement(doc, "any", schema.getSchemaNamespacePrefix(), "http://www.w3.org/2001/XMLSchema");
      if (anyObj.getId() != null && anyObj.getId().length() > 0) {
         anyEl.setAttributeNS((String)null, "id", anyObj.getId());
      }

      this.serializeMaxMinOccurs(anyObj, anyEl);
      if (anyObj.getNamespace() != null) {
         anyEl.setAttributeNS((String)null, "namespace", anyObj.getNamespace());
      }

      if (anyObj.getProcessContent() != null && anyObj.getProcessContent() != XmlSchemaContentProcessing.NONE) {
         anyEl.setAttributeNS((String)null, "processContents", anyObj.getProcessContent().toString());
      }

      if (anyObj.getAnnotation() != null) {
         Element annotation = this.serializeAnnotation(doc, anyObj.getAnnotation(), schema);
         anyEl.appendChild(annotation);
      }

      this.processExtensibilityComponents(anyObj, anyEl);
      return anyEl;
   }

   Element serializeAnyAttribute(Document doc, XmlSchemaAnyAttribute anyAttributeObj, XmlSchema schema) {
      Element anyAttribute = this.createNewElement(doc, "anyAttribute", schema.getSchemaNamespacePrefix(), "http://www.w3.org/2001/XMLSchema");
      if (anyAttributeObj.namespace != null) {
         anyAttribute.setAttributeNS((String)null, "namespace", anyAttributeObj.namespace);
      }

      if (anyAttributeObj.getId() != null) {
         anyAttribute.setAttributeNS((String)null, "id", anyAttributeObj.getId());
      }

      if (anyAttributeObj.processContent != null && anyAttributeObj.processContent != XmlSchemaContentProcessing.NONE) {
         anyAttribute.setAttributeNS((String)null, "processContents", anyAttributeObj.processContent.toString());
      }

      if (anyAttributeObj.getAnnotation() != null) {
         Element annotation = this.serializeAnnotation(doc, anyAttributeObj.getAnnotation(), schema);
         anyAttribute.appendChild(annotation);
      }

      this.processExtensibilityComponents(anyAttributeObj, anyAttribute);
      return anyAttribute;
   }

   Element serializeAppInfo(Document doc, XmlSchemaAppInfo appInfoObj, XmlSchema schema) {
      Element appInfoEl = this.createNewElement(doc, "appinfo", schema.getSchemaNamespacePrefix(), "http://www.w3.org/2001/XMLSchema");
      if (appInfoObj.source != null) {
         appInfoEl.setAttributeNS((String)null, "source", appInfoObj.source);
      }

      if (appInfoObj.markup != null) {
         int markupLength = appInfoObj.markup.getLength();

         for(int j = 0; j < markupLength; ++j) {
            Node n = appInfoObj.markup.item(j);
            appInfoEl.appendChild(doc.importNode(n, true));
         }
      }

      this.processExtensibilityComponents(appInfoObj, appInfoEl);
      return appInfoEl;
   }

   Element serializeAttribute(Document doc, XmlSchemaAttribute attributeObj, XmlSchema schema) throws XmlSchemaSerializerException {
      boolean refPresent = attributeObj.getRef().getTargetQName() != null;
      Element attribute = this.createNewElement(doc, "attribute", schema.getSchemaNamespacePrefix(), "http://www.w3.org/2001/XMLSchema");
      if (refPresent) {
         String refName = this.resolveQName(attributeObj.getRef().getTargetQName(), schema);
         attribute.setAttributeNS((String)null, "ref", refName);
      } else if (!attributeObj.isAnonymous()) {
         attribute.setAttributeNS((String)null, "name", attributeObj.getName());
      }

      if (attributeObj.getSchemaTypeName() != null && !refPresent) {
         String typeName = this.resolveQName(attributeObj.getSchemaTypeName(), schema);
         attribute.setAttributeNS((String)null, "type", typeName);
      }

      if (attributeObj.getDefaultValue() != null) {
         attribute.setAttributeNS((String)null, "default", attributeObj.getDefaultValue());
      }

      if (attributeObj.getFixedValue() != null) {
         attribute.setAttributeNS((String)null, "fixed", attributeObj.getFixedValue());
      }

      if (attributeObj.isFormSpecified() && !refPresent) {
         attribute.setAttributeNS((String)null, "form", attributeObj.getForm().toString());
      }

      if (attributeObj.getId() != null) {
         attribute.setAttributeNS((String)null, "id", attributeObj.getId());
      }

      if (attributeObj.getUse() != null && attributeObj.getUse() != XmlSchemaUse.NONE) {
         attribute.setAttributeNS((String)null, "use", attributeObj.getUse().toString());
      }

      if (attributeObj.getAnnotation() != null) {
         Element annotation = this.serializeAnnotation(doc, attributeObj.getAnnotation(), schema);
         attribute.appendChild(annotation);
      }

      if (attributeObj.getSchemaType() != null && !refPresent) {
         try {
            XmlSchemaSimpleType simpleType = attributeObj.getSchemaType();
            Element simpleTypeEl = this.serializeSimpleType(doc, simpleType, schema);
            attribute.appendChild(simpleTypeEl);
         } catch (ClassCastException var23) {
            throw new XmlSchemaSerializerException("Only an inline simple type is allowed as an attribute's inline type");
         }
      }

      Attr[] unhandled = attributeObj.getUnhandledAttributes();
      Map<String, String> namespaces = Collections.synchronizedMap(new HashMap());
      if (unhandled != null) {
         for(Attr element : unhandled) {
            String name = element.getNodeName();
            String value = element.getNodeValue();
            if ("xmlns".equals(name)) {
               namespaces.put("", value);
            } else if (name.startsWith("xmlns")) {
               namespaces.put(name.substring(name.indexOf(":") + 1), value);
            }
         }

         for(Attr element : unhandled) {
            String value = element.getNodeValue();
            String nodeName = element.getNodeName();
            if (value.indexOf(":") > -1 && !nodeName.startsWith("xmlns")) {
               String prefix = value.substring(0, value.indexOf(":"));
               String oldNamespace = (String)namespaces.get(prefix);
               if (oldNamespace != null) {
                  value = value.substring(value.indexOf(":") + 1);
                  NamespacePrefixList ctx = schema.getNamespaceContext();
                  String[] prefixes = ctx.getDeclaredPrefixes();

                  for(String pref : prefixes) {
                     String uri = ctx.getNamespaceURI(pref);
                     if (uri.equals(oldNamespace)) {
                        value = prefix + ":" + value;
                     }
                  }
               }
            }

            if (element.getNamespaceURI() != null) {
               attribute.setAttributeNS(element.getNamespaceURI(), nodeName, value);
            } else {
               attribute.setAttributeNS((String)null, nodeName, value);
            }
         }
      }

      this.processExtensibilityComponents(attributeObj, attribute);
      return attribute;
   }

   Element serializeAttributeGroup(Document doc, XmlSchemaAttributeGroup attributeGroupObj, XmlSchema schema) throws XmlSchemaSerializerException {
      Element attributeGroup = this.createNewElement(doc, "attributeGroup", schema.getSchemaNamespacePrefix(), "http://www.w3.org/2001/XMLSchema");
      if (!attributeGroupObj.isAnonymous()) {
         String attGroupName = attributeGroupObj.getName();
         attributeGroup.setAttributeNS((String)null, "name", attGroupName);
         if (attributeGroupObj.getId() != null) {
            attributeGroup.setAttributeNS((String)null, "id", attributeGroupObj.getId());
         }

         if (attributeGroupObj.getAnnotation() != null) {
            Element annotation = this.serializeAnnotation(doc, attributeGroupObj.getAnnotation(), schema);
            attributeGroup.appendChild(annotation);
         }

         int attributesLength = attributeGroupObj.getAttributes().size();

         for(int i = 0; i < attributesLength; ++i) {
            XmlSchemaAttributeGroupMember obj = (XmlSchemaAttributeGroupMember)attributeGroupObj.getAttributes().get(i);
            if (obj instanceof XmlSchemaAttribute) {
               Element attr = this.serializeAttribute(doc, (XmlSchemaAttribute)obj, schema);
               attributeGroup.appendChild(attr);
            } else if (obj instanceof XmlSchemaAttributeGroupRef) {
               Element attrGroup = this.serializeAttributeGroupRef(doc, (XmlSchemaAttributeGroupRef)obj, schema);
               attributeGroup.appendChild(attrGroup);
            }
         }

         if (attributeGroupObj.getAnyAttribute() != null) {
            Element anyAttribute = this.serializeAnyAttribute(doc, attributeGroupObj.getAnyAttribute(), schema);
            attributeGroup.appendChild(anyAttribute);
         }

         this.processExtensibilityComponents(attributeGroupObj, attributeGroup);
         return attributeGroup;
      } else {
         throw new XmlSchemaSerializerException("Attribute group must have name");
      }
   }

   Element serializeAttributeGroupRef(Document doc, XmlSchemaAttributeGroupRef attributeGroupObj, XmlSchema schema) throws XmlSchemaSerializerException {
      Element attributeGroupRef = this.createNewElement(doc, "attributeGroup", schema.getSchemaNamespacePrefix(), "http://www.w3.org/2001/XMLSchema");
      if (attributeGroupObj.getRef().getTarget() != null) {
         String refName = this.resolveQName(attributeGroupObj.getRef().getTargetQName(), schema);
         attributeGroupRef.setAttributeNS((String)null, "ref", refName);
         if (attributeGroupObj.getId() != null) {
            attributeGroupRef.setAttributeNS((String)null, "id", attributeGroupObj.getId());
         }

         if (attributeGroupObj.getAnnotation() != null) {
            Element annotation = this.serializeAnnotation(doc, attributeGroupObj.getAnnotation(), schema);
            attributeGroupRef.appendChild(annotation);
         }

         this.processExtensibilityComponents(attributeGroupObj, attributeGroupRef);
         return attributeGroupRef;
      } else {
         throw new XmlSchemaSerializerException("Attribute group must have ref name set");
      }
   }

   Element serializeChoice(Document doc, XmlSchemaChoice choiceObj, XmlSchema schema) throws XmlSchemaSerializerException {
      Element choice = this.createNewElement(doc, "choice", schema.getSchemaNamespacePrefix(), "http://www.w3.org/2001/XMLSchema");
      if (choiceObj.getId() != null && choiceObj.getId().length() > 0) {
         choice.setAttributeNS((String)null, "id", choiceObj.getId());
      }

      this.serializeMaxMinOccurs(choiceObj, choice);
      if (choiceObj.getAnnotation() != null) {
         Element annotation = this.serializeAnnotation(doc, choiceObj.getAnnotation(), schema);
         choice.appendChild(annotation);
      }

      List<XmlSchemaChoiceMember> itemColl = choiceObj.getItems();
      if (itemColl != null) {
         int itemLength = itemColl.size();

         for(int i = 0; i < itemLength; ++i) {
            XmlSchemaChoiceMember obj = (XmlSchemaChoiceMember)itemColl.get(i);
            if (obj instanceof XmlSchemaElement) {
               Element el = this.serializeElement(doc, (XmlSchemaElement)obj, schema);
               choice.appendChild(el);
            } else if (obj instanceof XmlSchemaGroupRef) {
               Element group = this.serializeGroupRef(doc, (XmlSchemaGroupRef)obj, schema);
               choice.appendChild(group);
            } else if (obj instanceof XmlSchemaChoice) {
               Element inlineChoice = this.serializeChoice(doc, (XmlSchemaChoice)obj, schema);
               choice.appendChild(inlineChoice);
            } else if (obj instanceof XmlSchemaSequence) {
               Element inlineSequence = this.serializeSequence(doc, (XmlSchemaSequence)obj, schema);
               choice.appendChild(inlineSequence);
            } else if (obj instanceof XmlSchemaAny) {
               Element any = this.serializeAny(doc, (XmlSchemaAny)obj, schema);
               choice.appendChild(any);
            }
         }
      }

      this.processExtensibilityComponents(choiceObj, choice);
      return choice;
   }

   Element serializeComplexContent(Document doc, XmlSchemaComplexContent complexContentObj, XmlSchema schema) throws XmlSchemaSerializerException {
      Element complexContent = this.createNewElement(doc, "complexContent", schema.getSchemaNamespacePrefix(), "http://www.w3.org/2001/XMLSchema");
      if (complexContentObj.getAnnotation() != null) {
         Element annotation = this.serializeAnnotation(doc, complexContentObj.getAnnotation(), schema);
         complexContent.appendChild(annotation);
      }

      if (complexContentObj.isMixed()) {
         complexContent.setAttributeNS((String)null, "mixed", "true");
      }

      if (complexContentObj.getId() != null) {
         complexContent.setAttributeNS((String)null, "id", complexContentObj.getId());
      }

      Element content;
      if (complexContentObj.content instanceof XmlSchemaComplexContentRestriction) {
         content = this.serializeComplexContentRestriction(doc, (XmlSchemaComplexContentRestriction)complexContentObj.content, schema);
      } else {
         if (!(complexContentObj.content instanceof XmlSchemaComplexContentExtension)) {
            throw new XmlSchemaSerializerException("content of complexContent must be restriction or extension");
         }

         content = this.serializeComplexContentExtension(doc, (XmlSchemaComplexContentExtension)complexContentObj.content, schema);
      }

      complexContent.appendChild(content);
      this.processExtensibilityComponents(complexContentObj, complexContent);
      return complexContent;
   }

   Element serializeComplexContentExtension(Document doc, XmlSchemaComplexContentExtension extensionObj, XmlSchema schema) throws XmlSchemaSerializerException {
      Element extension = this.createNewElement(doc, "extension", schema.getSchemaNamespacePrefix(), "http://www.w3.org/2001/XMLSchema");
      if (extensionObj.getBaseTypeName() != null) {
         String baseType = this.resolveQName(extensionObj.getBaseTypeName(), schema);
         extension.setAttributeNS((String)null, "base", baseType);
      }

      if (extensionObj.getAnnotation() != null) {
         Element annotation = this.serializeAnnotation(doc, extensionObj.getAnnotation(), schema);
         extension.appendChild(annotation);
      }

      if (extensionObj.getParticle() instanceof XmlSchemaSequence) {
         Element sequenceParticle = this.serializeSequence(doc, (XmlSchemaSequence)extensionObj.getParticle(), schema);
         extension.appendChild(sequenceParticle);
      } else if (extensionObj.getParticle() instanceof XmlSchemaChoice) {
         Element choiceParticle = this.serializeChoice(doc, (XmlSchemaChoice)extensionObj.getParticle(), schema);
         extension.appendChild(choiceParticle);
      } else if (extensionObj.getParticle() instanceof XmlSchemaAll) {
         Element allParticle = this.serializeAll(doc, (XmlSchemaAll)extensionObj.getParticle(), schema);
         extension.appendChild(allParticle);
      } else if (extensionObj.getParticle() instanceof XmlSchemaGroupRef) {
         Element groupRefParticle = this.serializeGroupRef(doc, (XmlSchemaGroupRef)extensionObj.getParticle(), schema);
         extension.appendChild(groupRefParticle);
      }

      int attributesLength = extensionObj.getAttributes().size();

      for(int i = 0; i < attributesLength; ++i) {
         XmlSchemaObject obj = (XmlSchemaObject)extensionObj.getAttributes().get(i);
         if (obj instanceof XmlSchemaAttribute) {
            Element attr = this.serializeAttribute(doc, (XmlSchemaAttribute)obj, schema);
            extension.appendChild(attr);
         } else if (obj instanceof XmlSchemaAttributeGroupRef) {
            Element attrGroup = this.serializeAttributeGroupRef(doc, (XmlSchemaAttributeGroupRef)obj, schema);
            extension.appendChild(attrGroup);
         }
      }

      if (extensionObj.getAnyAttribute() != null) {
         Element anyAttribute = this.serializeAnyAttribute(doc, extensionObj.getAnyAttribute(), schema);
         extension.appendChild(anyAttribute);
      }

      this.processExtensibilityComponents(extensionObj, extension);
      return extension;
   }

   Element serializeComplexContentRestriction(Document doc, XmlSchemaComplexContentRestriction restrictionObj, XmlSchema schema) throws XmlSchemaSerializerException {
      Element restriction = this.createNewElement(doc, "restriction", schema.getSchemaNamespacePrefix(), "http://www.w3.org/2001/XMLSchema");
      if (restrictionObj.getBaseTypeName() != null) {
         String baseTypeName = this.resolveQName(restrictionObj.getBaseTypeName(), schema);
         restriction.setAttributeNS((String)null, "base", baseTypeName);
      }

      if (restrictionObj.getId() != null) {
         restriction.setAttributeNS((String)null, "id", restrictionObj.getId());
      }

      if (restrictionObj.getAnnotation() != null) {
         Element annotation = this.serializeAnnotation(doc, restrictionObj.getAnnotation(), schema);
         restriction.appendChild(annotation);
      }

      if (restrictionObj.getParticle() instanceof XmlSchemaSequence) {
         Element sequenceParticle = this.serializeSequence(doc, (XmlSchemaSequence)restrictionObj.getParticle(), schema);
         restriction.appendChild(sequenceParticle);
      } else if (restrictionObj.getParticle() instanceof XmlSchemaChoice) {
         Element choiceParticle = this.serializeChoice(doc, (XmlSchemaChoice)restrictionObj.getParticle(), schema);
         restriction.appendChild(choiceParticle);
      } else if (restrictionObj.getParticle() instanceof XmlSchemaAll) {
         Element allParticle = this.serializeAll(doc, (XmlSchemaAll)restrictionObj.getParticle(), schema);
         restriction.appendChild(allParticle);
      } else if (restrictionObj.getParticle() instanceof XmlSchemaGroupRef) {
         Element groupRefParticle = this.serializeGroupRef(doc, (XmlSchemaGroupRef)restrictionObj.getParticle(), schema);
         restriction.appendChild(groupRefParticle);
      }

      int attributesLength = restrictionObj.getAttributes().size();

      for(int i = 0; i < attributesLength; ++i) {
         XmlSchemaAttributeOrGroupRef obj = (XmlSchemaAttributeOrGroupRef)restrictionObj.getAttributes().get(i);
         if (obj instanceof XmlSchemaAttribute) {
            Element attr = this.serializeAttribute(doc, (XmlSchemaAttribute)obj, schema);
            restriction.appendChild(attr);
         } else if (obj instanceof XmlSchemaAttributeGroupRef) {
            Element attrGroup = this.serializeAttributeGroupRef(doc, (XmlSchemaAttributeGroupRef)obj, schema);
            restriction.appendChild(attrGroup);
         }
      }

      if (restrictionObj.getAnyAttribute() != null) {
         Element anyAttribute = this.serializeAnyAttribute(doc, restrictionObj.getAnyAttribute(), schema);
         restriction.appendChild(anyAttribute);
      }

      this.processExtensibilityComponents(restrictionObj, restriction);
      return restriction;
   }

   Element serializeComplexType(Document doc, XmlSchemaComplexType complexTypeObj, XmlSchema schema) throws XmlSchemaSerializerException {
      Element serializedComplexType = this.createNewElement(doc, "complexType", schema.getSchemaNamespacePrefix(), "http://www.w3.org/2001/XMLSchema");
      if (!complexTypeObj.isAnonymous()) {
         serializedComplexType.setAttributeNS((String)null, "name", complexTypeObj.getName());
      }

      if (complexTypeObj.isMixed()) {
         serializedComplexType.setAttributeNS((String)null, "mixed", "true");
      }

      if (complexTypeObj.isAbstract()) {
         serializedComplexType.setAttributeNS((String)null, "abstract", "true");
      }

      if (complexTypeObj.getId() != null) {
         serializedComplexType.setAttributeNS((String)null, "id", complexTypeObj.getId());
      }

      if (complexTypeObj.getAnnotation() != null) {
         Element annotationEl = this.serializeAnnotation(doc, complexTypeObj.getAnnotation(), schema);
         serializedComplexType.appendChild(annotationEl);
      }

      if (complexTypeObj.getContentModel() instanceof XmlSchemaSimpleContent) {
         Element simpleContent = this.serializeSimpleContent(doc, (XmlSchemaSimpleContent)complexTypeObj.getContentModel(), schema);
         serializedComplexType.appendChild(simpleContent);
      } else if (complexTypeObj.getContentModel() instanceof XmlSchemaComplexContent) {
         Element complexContent = this.serializeComplexContent(doc, (XmlSchemaComplexContent)complexTypeObj.getContentModel(), schema);
         serializedComplexType.appendChild(complexContent);
      }

      if (complexTypeObj.getParticle() instanceof XmlSchemaSequence) {
         Element sequence = this.serializeSequence(doc, (XmlSchemaSequence)complexTypeObj.getParticle(), schema);
         serializedComplexType.appendChild(sequence);
      } else if (complexTypeObj.getParticle() instanceof XmlSchemaChoice) {
         Element choice = this.serializeChoice(doc, (XmlSchemaChoice)complexTypeObj.getParticle(), schema);
         serializedComplexType.appendChild(choice);
      } else if (complexTypeObj.getParticle() instanceof XmlSchemaAll) {
         Element all = this.serializeAll(doc, (XmlSchemaAll)complexTypeObj.getParticle(), schema);
         serializedComplexType.appendChild(all);
      } else if (complexTypeObj.getParticle() instanceof XmlSchemaGroupRef) {
         Element group = this.serializeGroupRef(doc, (XmlSchemaGroupRef)complexTypeObj.getParticle(), schema);
         serializedComplexType.appendChild(group);
      }

      if (complexTypeObj.getBlock() != null && complexTypeObj.getBlock() != XmlSchemaDerivationMethod.NONE) {
         serializedComplexType.setAttributeNS((String)null, "block", complexTypeObj.toString());
      }

      if (complexTypeObj.getFinalDerivation() != null && complexTypeObj.getFinalDerivation() != XmlSchemaDerivationMethod.NONE) {
         serializedComplexType.setAttributeNS((String)null, "final", complexTypeObj.getFinalDerivation().toString());
      }

      List<XmlSchemaAttributeOrGroupRef> attrColl = complexTypeObj.getAttributes();
      if (attrColl.size() > 0) {
         this.setupAttr(doc, attrColl, schema, serializedComplexType);
      }

      XmlSchemaAnyAttribute anyAttribute = complexTypeObj.getAnyAttribute();
      if (anyAttribute != null) {
         serializedComplexType.appendChild(this.serializeAnyAttribute(doc, anyAttribute, schema));
      }

      this.processExtensibilityComponents(complexTypeObj, serializedComplexType);
      return serializedComplexType;
   }

   Element serializeDocumentation(Document doc, XmlSchemaDocumentation documentationObj, XmlSchema schema) {
      Element documentationEl = this.createNewElement(doc, "documentation", schema.getSchemaNamespacePrefix(), "http://www.w3.org/2001/XMLSchema");
      if (documentationObj.source != null) {
         documentationEl.setAttributeNS((String)null, "source", documentationObj.source);
      }

      if (documentationObj.language != null) {
         documentationEl.setAttributeNS("http://www.w3.org/XML/1998/namespace", "xml:lang", documentationObj.language);
      }

      if (documentationObj.markup != null) {
         int markupLength = documentationObj.markup.getLength();

         for(int j = 0; j < markupLength; ++j) {
            Node n = documentationObj.markup.item(j);
            switch (n.getNodeType()) {
               case 1:
                  this.appendElement(doc, documentationEl, n, schema);
               case 2:
               case 5:
               case 6:
               case 7:
               default:
                  break;
               case 3:
                  Text t = doc.createTextNode(n.getNodeValue());
                  documentationEl.appendChild(t);
                  break;
               case 4:
                  CDATASection s = doc.createCDATASection(n.getNodeValue());
                  documentationEl.appendChild(s);
                  break;
               case 8:
                  Comment c = doc.createComment(n.getNodeValue());
                  documentationEl.appendChild(c);
            }
         }
      }

      this.processExtensibilityComponents(documentationObj, documentationEl);
      return documentationEl;
   }

   Element serializeElement(Document doc, XmlSchemaElement elementObj, XmlSchema schema) throws XmlSchemaSerializerException {
      Element serializedEl = this.createNewElement(doc, "element", schema.getSchemaNamespacePrefix(), "http://www.w3.org/2001/XMLSchema");
      if (elementObj.getRef().getTargetQName() != null) {
         String resolvedName = this.resolveQName(elementObj.getRef().getTargetQName(), schema);
         serializedEl.setAttributeNS((String)null, "ref", resolvedName);
      } else if (!elementObj.isAnonymous()) {
         serializedEl.setAttributeNS((String)null, "name", elementObj.getName());
      }

      if (elementObj.isAbstractElement()) {
         serializedEl.setAttributeNS((String)null, "abstract", "true");
      }

      if (elementObj.getBlock() != null && elementObj.getBlock() != XmlSchemaDerivationMethod.NONE) {
         serializedEl.setAttributeNS((String)null, "block", elementObj.getBlock().toString());
      }

      if (elementObj.getDefaultValue() != null) {
         serializedEl.setAttributeNS((String)null, "default", elementObj.getDefaultValue());
      }

      if (elementObj.getFinalDerivation() != null && elementObj.getFinalDerivation() != XmlSchemaDerivationMethod.NONE) {
         serializedEl.setAttributeNS((String)null, "final", elementObj.getFinalDerivation().toString());
      }

      if (elementObj.getFixedValue() != null) {
         serializedEl.setAttributeNS((String)null, "fixed", elementObj.getFixedValue());
      }

      if (elementObj.isFormSpecified()) {
         serializedEl.setAttributeNS((String)null, "form", elementObj.getForm().toString());
      }

      if (elementObj.getId() != null) {
         serializedEl.setAttributeNS((String)null, "id", elementObj.getId());
      }

      this.serializeMaxMinOccurs(elementObj, serializedEl);
      if (elementObj.getSubstitutionGroup() != null) {
         String resolvedQName = this.resolveQName(elementObj.getSubstitutionGroup(), schema);
         serializedEl.setAttributeNS((String)null, "substitutionGroup", resolvedQName);
      }

      if (elementObj.getSchemaTypeName() != null) {
         String resolvedName = this.resolveQName(elementObj.getSchemaTypeName(), schema);
         serializedEl.setAttributeNS((String)null, "type", resolvedName);
      }

      if (elementObj.getAnnotation() != null) {
         Element annotationEl = this.serializeAnnotation(doc, elementObj.getAnnotation(), schema);
         serializedEl.appendChild(annotationEl);
      }

      if (elementObj.getSchemaType() != null && elementObj.getSchemaTypeName() == null) {
         if (elementObj.getSchemaType() instanceof XmlSchemaComplexType) {
            Element complexType = this.serializeComplexType(doc, (XmlSchemaComplexType)elementObj.getSchemaType(), schema);
            serializedEl.appendChild(complexType);
         } else if (elementObj.getSchemaType() instanceof XmlSchemaSimpleType) {
            Element simpleType = this.serializeSimpleType(doc, (XmlSchemaSimpleType)elementObj.getSchemaType(), schema);
            serializedEl.appendChild(simpleType);
         }
      }

      if (elementObj.getConstraints().size() > 0) {
         for(int i = 0; i < elementObj.getConstraints().size(); ++i) {
            Element constraint = this.serializeIdentityConstraint(doc, (XmlSchemaIdentityConstraint)elementObj.getConstraints().get(i), schema);
            serializedEl.appendChild(constraint);
         }
      }

      if (elementObj.isNillable()) {
         serializedEl.setAttributeNS((String)null, "nillable", "true");
      }

      this.processExtensibilityComponents(elementObj, serializedEl);
      return serializedEl;
   }

   Element serializeFacet(Document doc, XmlSchemaFacet facetObj, XmlSchema schema) throws XmlSchemaSerializerException {
      Element serializedFacet;
      if (facetObj instanceof XmlSchemaMinExclusiveFacet) {
         serializedFacet = this.constructFacet(facetObj, doc, schema, "minExclusive");
      } else if (facetObj instanceof XmlSchemaMinInclusiveFacet) {
         serializedFacet = this.constructFacet(facetObj, doc, schema, "minInclusive");
      } else if (facetObj instanceof XmlSchemaMaxExclusiveFacet) {
         serializedFacet = this.constructFacet(facetObj, doc, schema, "maxExclusive");
      } else if (facetObj instanceof XmlSchemaMaxInclusiveFacet) {
         serializedFacet = this.constructFacet(facetObj, doc, schema, "maxInclusive");
      } else if (facetObj instanceof XmlSchemaTotalDigitsFacet) {
         serializedFacet = this.constructFacet(facetObj, doc, schema, "totalDigits");
      } else if (facetObj instanceof XmlSchemaFractionDigitsFacet) {
         serializedFacet = this.constructFacet(facetObj, doc, schema, "fractionDigits");
      } else if (facetObj instanceof XmlSchemaLengthFacet) {
         serializedFacet = this.constructFacet(facetObj, doc, schema, "length");
      } else if (facetObj instanceof XmlSchemaMinLengthFacet) {
         serializedFacet = this.constructFacet(facetObj, doc, schema, "minLength");
      } else if (facetObj instanceof XmlSchemaMaxLengthFacet) {
         serializedFacet = this.constructFacet(facetObj, doc, schema, "maxLength");
      } else if (facetObj instanceof XmlSchemaEnumerationFacet) {
         serializedFacet = this.constructFacet(facetObj, doc, schema, "enumeration");
      } else if (facetObj instanceof XmlSchemaWhiteSpaceFacet) {
         serializedFacet = this.constructFacet(facetObj, doc, schema, "whiteSpace");
      } else {
         if (!(facetObj instanceof XmlSchemaPatternFacet)) {
            throw new XmlSchemaSerializerException("facet not exist " + facetObj.getClass().getName());
         }

         serializedFacet = this.constructFacet(facetObj, doc, schema, "pattern");
      }

      if (facetObj.getId() != null) {
         serializedFacet.setAttributeNS((String)null, "id", facetObj.getId());
      }

      this.processExtensibilityComponents(facetObj, serializedFacet);
      return serializedFacet;
   }

   Element serializeField(Document doc, XmlSchemaXPath fieldObj, XmlSchema schema) throws XmlSchemaSerializerException {
      Element field = this.createNewElement(doc, "field", schema.getSchemaNamespacePrefix(), "http://www.w3.org/2001/XMLSchema");
      if (fieldObj.xpath != null) {
         field.setAttributeNS((String)null, "xpath", fieldObj.xpath);
         if (fieldObj.getAnnotation() != null) {
            Element annotation = this.serializeAnnotation(doc, fieldObj.getAnnotation(), schema);
            field.appendChild(annotation);
         }

         this.processExtensibilityComponents(fieldObj, field);
         return field;
      } else {
         throw new XmlSchemaSerializerException("xpath can't be null");
      }
   }

   Element serializeGroup(Document doc, XmlSchemaGroup groupObj, XmlSchema schema) throws XmlSchemaSerializerException {
      Element group = this.createNewElement(doc, "group", schema.getSchemaNamespacePrefix(), "http://www.w3.org/2001/XMLSchema");
      if (!groupObj.isAnonymous()) {
         String grpName = groupObj.getName();
         if (grpName.length() > 0) {
            group.setAttributeNS((String)null, "name", grpName);
         }

         if (groupObj.getAnnotation() != null) {
            Element annotation = this.serializeAnnotation(doc, groupObj.getAnnotation(), schema);
            group.appendChild(annotation);
         }

         if (groupObj.getParticle() instanceof XmlSchemaSequence) {
            Element sequence = this.serializeSequence(doc, (XmlSchemaSequence)groupObj.getParticle(), schema);
            group.appendChild(sequence);
         } else if (groupObj.getParticle() instanceof XmlSchemaChoice) {
            Element choice = this.serializeChoice(doc, (XmlSchemaChoice)groupObj.getParticle(), schema);
            group.appendChild(choice);
         } else if (groupObj.getParticle() instanceof XmlSchemaAll) {
            Element all = this.serializeAll(doc, (XmlSchemaAll)groupObj.getParticle(), schema);
            group.appendChild(all);
         }

         this.processExtensibilityComponents(groupObj, group);
         return group;
      } else {
         throw new XmlSchemaSerializerException("Group must have name or ref");
      }
   }

   Element serializeGroupRef(Document doc, XmlSchemaGroupRef groupRefObj, XmlSchema schema) throws XmlSchemaSerializerException {
      Element groupRef = this.createNewElement(doc, "group", schema.getSchemaNamespacePrefix(), "http://www.w3.org/2001/XMLSchema");
      if (groupRefObj.getRefName() != null) {
         String groupRefName = this.resolveQName(groupRefObj.getRefName(), schema);
         groupRef.setAttributeNS((String)null, "ref", groupRefName);
         this.serializeMaxMinOccurs(groupRefObj, groupRef);
         if (groupRefObj.getParticle() != null) {
            if (groupRefObj.getParticle() instanceof XmlSchemaChoice) {
               this.serializeChoice(doc, (XmlSchemaChoice)groupRefObj.getParticle(), schema);
            } else if (groupRefObj.getParticle() instanceof XmlSchemaSequence) {
               this.serializeSequence(doc, (XmlSchemaSequence)groupRefObj.getParticle(), schema);
            } else {
               if (!(groupRefObj.getParticle() instanceof XmlSchemaAll)) {
                  throw new XmlSchemaSerializerException("The content of group ref particle should be sequence, choice or all reference:  www.w3.org/TR/xmlschema-1#element-group-3.7.2");
               }

               this.serializeAll(doc, (XmlSchemaAll)groupRefObj.getParticle(), schema);
            }
         }

         if (groupRefObj.getAnnotation() != null) {
            Element annotation = this.serializeAnnotation(doc, groupRefObj.getAnnotation(), schema);
            groupRef.appendChild(annotation);
         }

         this.processExtensibilityComponents(groupRefObj, groupRef);
         return groupRef;
      } else {
         throw new XmlSchemaSerializerException("Group must have name or ref");
      }
   }

   Element serializeIdentityConstraint(Document doc, XmlSchemaIdentityConstraint constraintObj, XmlSchema schema) throws XmlSchemaSerializerException {
      Element constraint;
      if (constraintObj instanceof XmlSchemaUnique) {
         constraint = this.createNewElement(doc, "unique", schema.getSchemaNamespacePrefix(), "http://www.w3.org/2001/XMLSchema");
      } else if (constraintObj instanceof XmlSchemaKey) {
         constraint = this.createNewElement(doc, "key", schema.getSchemaNamespacePrefix(), "http://www.w3.org/2001/XMLSchema");
      } else {
         if (!(constraintObj instanceof XmlSchemaKeyref)) {
            throw new XmlSchemaSerializerException("not valid identity constraint");
         }

         constraint = this.createNewElement(doc, "keyref", schema.getSchemaNamespacePrefix(), "http://www.w3.org/2001/XMLSchema");
         XmlSchemaKeyref keyref = (XmlSchemaKeyref)constraintObj;
         if (keyref.refer != null) {
            String keyrefStr = this.resolveQName(keyref.refer, schema);
            constraint.setAttributeNS((String)null, "refer", keyrefStr);
         }
      }

      if (constraintObj.getName() != null) {
         constraint.setAttributeNS((String)null, "name", constraintObj.getName());
      }

      if (constraintObj.getAnnotation() != null) {
         Element annotation = this.serializeAnnotation(doc, constraintObj.getAnnotation(), schema);
         constraint.appendChild(annotation);
      }

      if (constraintObj.getSelector() != null) {
         Element selector = this.serializeSelector(doc, constraintObj.getSelector(), schema);
         constraint.appendChild(selector);
      }

      List<XmlSchemaXPath> fieldColl = constraintObj.getFields();
      if (fieldColl != null) {
         int fieldLength = fieldColl.size();

         for(int i = 0; i < fieldLength; ++i) {
            Element field = this.serializeField(doc, (XmlSchemaXPath)fieldColl.get(i), schema);
            constraint.appendChild(field);
         }
      }

      this.processExtensibilityComponents(constraintObj, constraint);
      return constraint;
   }

   Element serializeImport(Document doc, XmlSchemaImport importObj, XmlSchema schema, boolean serializeIncluded) throws XmlSchemaSerializerException {
      Element importEl = this.createNewElement(doc, "import", schema.getSchemaNamespacePrefix(), "http://www.w3.org/2001/XMLSchema");
      if (importObj.namespace != null && !"".equals(importObj.namespace)) {
         importEl.setAttributeNS((String)null, "namespace", importObj.namespace);
      }

      if (importObj.schemaLocation != null && !importObj.schemaLocation.trim().equals("")) {
         importEl.setAttributeNS((String)null, "schemaLocation", importObj.schemaLocation);
      }

      if (importObj.getId() != null) {
         importEl.setAttributeNS((String)null, "id", importObj.getId());
      }

      if (importObj.getAnnotation() != null) {
         Element annotation = this.serializeAnnotation(doc, importObj.getAnnotation(), schema);
         importEl.appendChild(annotation);
      }

      if (importObj.schema != null && serializeIncluded) {
         XmlSchemaSerializer importSeri = new XmlSchemaSerializer();
         importSeri.serializeSchemaElement(importObj.schema, serializeIncluded);
         this.docs.addAll(importSeri.docs);
      }

      this.processExtensibilityComponents(importObj, importEl);
      return importEl;
   }

   Element serializeInclude(Document doc, XmlSchemaInclude includeObj, XmlSchema schema, boolean serializeIncluded) throws XmlSchemaSerializerException {
      Element includeEl = this.createNewElement(doc, "include", schema.getSchemaNamespacePrefix(), "http://www.w3.org/2001/XMLSchema");
      if (includeObj.schemaLocation != null) {
         includeEl.setAttributeNS((String)null, "schemaLocation", includeObj.schemaLocation);
      }

      if (includeObj.getId() != null) {
         includeEl.setAttributeNS((String)null, "id", includeObj.getId());
      }

      if (includeObj.getAnnotation() != null) {
         Element annotation = this.serializeAnnotation(doc, includeObj.getAnnotation(), schema);
         includeEl.appendChild(annotation);
      }

      XmlSchema includedSchemaObj = includeObj.getSchema();
      if (includedSchemaObj != null && serializeIncluded) {
         XmlSchemaSerializer includeSeri = new XmlSchemaSerializer();
         includeSeri.serializeSchemaElement(includedSchemaObj, true);
         this.docs.addAll(includeSeri.docs);
      }

      this.processExtensibilityComponents(includeObj, includeEl);
      return includeEl;
   }

   Element serializeRedefine(Document doc, XmlSchemaRedefine redefineObj, XmlSchema schema) throws XmlSchemaSerializerException {
      Element redefine = this.createNewElement(doc, "redefine", schema.getSchemaNamespacePrefix(), "http://www.w3.org/2001/XMLSchema");
      if (redefineObj.schemaLocation != null) {
         redefine.setAttributeNS((String)null, "schemaLocation", redefineObj.schemaLocation);
         if (redefineObj.getId() != null) {
            redefine.setAttributeNS((String)null, "id", redefineObj.getId());
         }

         if (redefineObj.getAnnotation() != null) {
            Element annotation = this.serializeAnnotation(doc, redefineObj.getAnnotation(), schema);
            redefine.appendChild(annotation);
         }

         int itemsLength = redefineObj.getItems().size();

         for(int i = 0; i < itemsLength; ++i) {
            XmlSchemaObject obj = (XmlSchemaObject)redefineObj.getItems().get(i);
            if (obj instanceof XmlSchemaSimpleType) {
               Element simpleType = this.serializeSimpleType(doc, (XmlSchemaSimpleType)obj, schema);
               redefine.appendChild(simpleType);
            } else if (obj instanceof XmlSchemaComplexType) {
               Element complexType = this.serializeComplexType(doc, (XmlSchemaComplexType)obj, schema);
               redefine.appendChild(complexType);
            } else if (obj instanceof XmlSchemaGroupRef) {
               Element groupRef = this.serializeGroupRef(doc, (XmlSchemaGroupRef)obj, schema);
               redefine.appendChild(groupRef);
            } else if (obj instanceof XmlSchemaGroup) {
               Element group = this.serializeGroup(doc, (XmlSchemaGroup)obj, schema);
               redefine.appendChild(group);
            } else if (obj instanceof XmlSchemaAttributeGroup) {
               Element attributeGroup = this.serializeAttributeGroup(doc, (XmlSchemaAttributeGroup)obj, schema);
               redefine.appendChild(attributeGroup);
            } else if (obj instanceof XmlSchemaAttributeGroupRef) {
               Element attributeGroupRef = this.serializeAttributeGroupRef(doc, (XmlSchemaAttributeGroupRef)obj, schema);
               redefine.appendChild(attributeGroupRef);
            }
         }

         this.processExtensibilityComponents(redefineObj, redefine);
         return redefine;
      } else {
         throw new XmlSchemaSerializerException("redefine must have schemaLocation fields fill");
      }
   }

   Document[] serializeSchemaElement(XmlSchema schemaObj, boolean serializeIncluded) throws XmlSchemaSerializerException {
      List<XmlSchemaObject> items = schemaObj.getItems();

      Document serializedSchemaDocs;
      try {
         DocumentBuilderFactory docFac = DocumentBuilderFactory.newInstance();
         docFac.setFeature("http://javax.xml.XMLConstants/feature/secure-processing", Boolean.TRUE);
         docFac.setNamespaceAware(true);
         DocumentBuilder builder = docFac.newDocumentBuilder();
         serializedSchemaDocs = builder.newDocument();
      } catch (ParserConfigurationException e) {
         throw new XmlSchemaException(e.getMessage());
      }

      Element serializedSchema = this.setupNamespaces(serializedSchemaDocs, schemaObj);
      this.schemaElement = serializedSchema;
      if (schemaObj.getSyntacticalTargetNamespace() != null) {
         String targetNamespace = schemaObj.getSyntacticalTargetNamespace();
         if (targetNamespace != null && !"".equals(targetNamespace)) {
            serializedSchema.setAttributeNS((String)null, "targetNamespace", targetNamespace);
         }

         String targetNS = (String)this.schemaNamespace.get(targetNamespace);
         if (targetNS == null) {
            String prefix = null;
            if (schemaObj.getNamespaceContext() != null) {
               prefix = schemaObj.getNamespaceContext().getPrefix(schemaObj.getSyntacticalTargetNamespace());
            }

            if (prefix == null && schemaObj.getParent() != null && schemaObj.getParent().getNamespaceContext() != null) {
               prefix = schemaObj.getParent().getNamespaceContext().getPrefix(schemaObj.getSyntacticalTargetNamespace());
            }

            if (prefix == null) {
               if (serializedSchema.getAttributeNode("xmlns") == null) {
                  prefix = "";
               }
            } else {
               String ns = serializedSchema.getAttribute("xmlns:" + prefix);
               if (ns != null && !"".equals(ns)) {
                  prefix = null;
               }
            }

            if (prefix == null) {
               int count = 0;
               prefix = "tns";

               for(String ns = serializedSchema.getAttribute("xmlns:" + prefix); ns != null && !"".equals(ns); ns = serializedSchema.getAttribute("xmlns:" + prefix)) {
                  ++count;
                  prefix = "tns" + count;
               }
            }

            if ("".equals(prefix)) {
               serializedSchema.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns", schemaObj.getSyntacticalTargetNamespace());
            } else {
               serializedSchema.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:" + prefix, schemaObj.getSyntacticalTargetNamespace());
            }

            this.schemaNamespace.put(schemaObj.getSyntacticalTargetNamespace(), prefix);
         }
      }

      if (schemaObj.getAttributeFormDefault() != null) {
         String formQualified = schemaObj.getAttributeFormDefault().toString();
         if (!formQualified.equals(XmlSchemaForm.NONE.toString())) {
            serializedSchema.setAttributeNS((String)null, "attributeFormDefault", formQualified);
         }
      }

      if (schemaObj.getElementFormDefault() != null) {
         String formQualified = schemaObj.getElementFormDefault().toString();
         if (!formQualified.equals(XmlSchemaForm.NONE.toString())) {
            serializedSchema.setAttributeNS((String)null, "elementFormDefault", formQualified);
         }
      }

      if (schemaObj.getAnnotation() != null) {
         Element annotation = this.serializeAnnotation(serializedSchemaDocs, schemaObj.getAnnotation(), schemaObj);
         serializedSchema.appendChild(annotation);
      }

      if (schemaObj.getId() != null) {
         serializedSchema.setAttributeNS((String)null, "id", schemaObj.getId());
      }

      if (schemaObj.getBlockDefault() != XmlSchemaDerivationMethod.NONE) {
         String blockDefault = schemaObj.getBlockDefault().toString();
         serializedSchema.setAttributeNS((String)null, "blockDefault", blockDefault);
      }

      if (schemaObj.getFinalDefault() != XmlSchemaDerivationMethod.NONE) {
         String finalDefault = schemaObj.getFinalDefault().toString();
         serializedSchema.setAttributeNS((String)null, "finalDefault", finalDefault);
      }

      if (schemaObj.getVersion() != null) {
         serializedSchema.setAttributeNS((String)null, "version", schemaObj.getVersion());
      }

      this.serializeSchemaChild(items, serializedSchema, serializedSchemaDocs, schemaObj, serializeIncluded);
      this.processExtensibilityComponents(schemaObj, serializedSchema);
      serializedSchemaDocs.appendChild(serializedSchema);
      this.docs.add(serializedSchemaDocs);
      Document[] serializedDocs = new Document[this.docs.size()];
      this.docs.toArray(serializedDocs);
      return serializedDocs;
   }

   Element serializeSelector(Document doc, XmlSchemaXPath selectorObj, XmlSchema schema) throws XmlSchemaSerializerException {
      Element selector = this.createNewElement(doc, "selector", schema.getSchemaNamespacePrefix(), "http://www.w3.org/2001/XMLSchema");
      if (selectorObj.xpath != null) {
         selector.setAttributeNS((String)null, "xpath", selectorObj.xpath);
         if (selectorObj.getAnnotation() != null) {
            Element annotation = this.serializeAnnotation(doc, selectorObj.getAnnotation(), schema);
            selector.appendChild(annotation);
         }

         this.processExtensibilityComponents(selectorObj, selector);
         return selector;
      } else {
         throw new XmlSchemaSerializerException("xpath can't be null");
      }
   }

   Element serializeSequence(Document doc, XmlSchemaSequence sequenceObj, XmlSchema schema) throws XmlSchemaSerializerException {
      Element sequence = this.createNewElement(doc, "sequence", schema.getSchemaNamespacePrefix(), "http://www.w3.org/2001/XMLSchema");
      if (sequenceObj.getId() != null) {
         sequence.setAttributeNS((String)null, "id", sequenceObj.getId());
      }

      this.serializeMaxMinOccurs(sequenceObj, sequence);
      List<XmlSchemaSequenceMember> seqColl = sequenceObj.getItems();
      int containLength = seqColl.size();

      for(int i = 0; i < containLength; ++i) {
         XmlSchemaSequenceMember obj = (XmlSchemaSequenceMember)seqColl.get(i);
         if (obj instanceof XmlSchemaElement) {
            Element el = this.serializeElement(doc, (XmlSchemaElement)obj, schema);
            sequence.appendChild(el);
         } else if (obj instanceof XmlSchemaGroupRef) {
            Element group = this.serializeGroupRef(doc, (XmlSchemaGroupRef)obj, schema);
            sequence.appendChild(group);
         } else if (obj instanceof XmlSchemaChoice) {
            Element choice = this.serializeChoice(doc, (XmlSchemaChoice)obj, schema);
            sequence.appendChild(choice);
         } else if (obj instanceof XmlSchemaSequence) {
            Element sequenceChild = this.serializeSequence(doc, (XmlSchemaSequence)obj, schema);
            sequence.appendChild(sequenceChild);
         } else if (obj instanceof XmlSchemaAny) {
            Element any = this.serializeAny(doc, (XmlSchemaAny)obj, schema);
            sequence.appendChild(any);
         }
      }

      this.processExtensibilityComponents(sequenceObj, sequence);
      return sequence;
   }

   Element serializeSimpleContent(Document doc, XmlSchemaSimpleContent simpleContentObj, XmlSchema schema) throws XmlSchemaSerializerException {
      Element simpleContent = this.createNewElement(doc, "simpleContent", schema.getSchemaNamespacePrefix(), "http://www.w3.org/2001/XMLSchema");
      if (simpleContentObj.getAnnotation() != null) {
         Element annotation = this.serializeAnnotation(doc, simpleContentObj.getAnnotation(), schema);
         simpleContent.appendChild(annotation);
      }

      Element content;
      if (simpleContentObj.content instanceof XmlSchemaSimpleContentRestriction) {
         content = this.serializeSimpleContentRestriction(doc, (XmlSchemaSimpleContentRestriction)simpleContentObj.content, schema);
      } else {
         if (!(simpleContentObj.content instanceof XmlSchemaSimpleContentExtension)) {
            throw new XmlSchemaSerializerException("content of simple content must be restriction or extension");
         }

         content = this.serializeSimpleContentExtension(doc, (XmlSchemaSimpleContentExtension)simpleContentObj.content, schema);
      }

      simpleContent.appendChild(content);
      this.processExtensibilityComponents(simpleContentObj, simpleContent);
      return simpleContent;
   }

   Element serializeSimpleContentExtension(Document doc, XmlSchemaSimpleContentExtension extensionObj, XmlSchema schema) throws XmlSchemaSerializerException {
      Element extension = this.createNewElement(doc, "extension", schema.getSchemaNamespacePrefix(), "http://www.w3.org/2001/XMLSchema");
      if (extensionObj.getBaseTypeName() != null) {
         String baseTypeName = this.resolveQName(extensionObj.getBaseTypeName(), schema);
         extension.setAttributeNS((String)null, "base", baseTypeName);
      }

      if (extensionObj.getId() != null) {
         extension.setAttributeNS((String)null, "id", extensionObj.getId());
      }

      if (extensionObj.getAnnotation() != null) {
         Element annotation = this.serializeAnnotation(doc, extensionObj.getAnnotation(), schema);
         extension.appendChild(annotation);
      }

      List<XmlSchemaAttributeOrGroupRef> attributes = extensionObj.getAttributes();
      int attributeLength = attributes.size();

      for(int i = 0; i < attributeLength; ++i) {
         XmlSchemaObject obj = (XmlSchemaObject)attributes.get(i);
         if (obj instanceof XmlSchemaAttribute) {
            Element attribute = this.serializeAttribute(doc, (XmlSchemaAttribute)obj, schema);
            extension.appendChild(attribute);
         } else if (obj instanceof XmlSchemaAttributeGroupRef) {
            Element attributeGroupRef = this.serializeAttributeGroupRef(doc, (XmlSchemaAttributeGroupRef)obj, schema);
            extension.appendChild(attributeGroupRef);
         }
      }

      if (extensionObj.getAnyAttribute() != null) {
         Element anyAttribute = this.serializeAnyAttribute(doc, extensionObj.getAnyAttribute(), schema);
         extension.appendChild(anyAttribute);
      }

      this.processExtensibilityComponents(extensionObj, extension);
      return extension;
   }

   Element serializeSimpleContentRestriction(Document doc, XmlSchemaSimpleContentRestriction restrictionObj, XmlSchema schema) throws XmlSchemaSerializerException {
      Element restriction = this.createNewElement(doc, "restriction", schema.getSchemaNamespacePrefix(), "http://www.w3.org/2001/XMLSchema");
      if (restrictionObj.getBaseTypeName() != null) {
         String baseTypeName = this.resolveQName(restrictionObj.getBaseTypeName(), schema);
         restriction.setAttributeNS((String)null, "base", baseTypeName);
      }

      if (restrictionObj.getId() != null) {
         restriction.setAttributeNS((String)null, "id", restrictionObj.getId());
      }

      if (restrictionObj.getAnnotation() != null) {
         Element annotation = this.serializeAnnotation(doc, restrictionObj.getAnnotation(), schema);
         restriction.appendChild(annotation);
      }

      if (restrictionObj.getBaseType() != null) {
         Element inlineSimpleType = this.serializeSimpleType(doc, restrictionObj.getBaseType(), schema);
         restriction.appendChild(inlineSimpleType);
      }

      List<XmlSchemaFacet> facets = restrictionObj.getFacets();
      int facetLength = facets.size();

      for(int i = 0; i < facetLength; ++i) {
         Element facet = this.serializeFacet(doc, (XmlSchemaFacet)facets.get(i), schema);
         restriction.appendChild(facet);
      }

      int attrCollLength = restrictionObj.getAttributes().size();

      for(int i = 0; i < attrCollLength; ++i) {
         XmlSchemaAnnotated obj = (XmlSchemaAnnotated)restrictionObj.getAttributes().get(i);
         if (obj instanceof XmlSchemaAttribute) {
            Element attribute = this.serializeAttribute(doc, (XmlSchemaAttribute)obj, schema);
            restriction.appendChild(attribute);
         } else if (obj instanceof XmlSchemaAttributeGroupRef) {
            Element attributeGroup = this.serializeAttributeGroupRef(doc, (XmlSchemaAttributeGroupRef)obj, schema);
            restriction.appendChild(attributeGroup);
         }
      }

      if (restrictionObj.anyAttribute != null) {
         Element anyAttribute = this.serializeAnyAttribute(doc, restrictionObj.anyAttribute, schema);
         restriction.appendChild(anyAttribute);
      }

      this.processExtensibilityComponents(restrictionObj, restriction);
      return restriction;
   }

   Element serializeSimpleType(Document doc, XmlSchemaSimpleType simpleTypeObj, XmlSchema schema) throws XmlSchemaSerializerException {
      Element serializedSimpleType = this.createNewElement(doc, "simpleType", schema.getSchemaNamespacePrefix(), "http://www.w3.org/2001/XMLSchema");
      if (simpleTypeObj.getFinalDerivation() != null && simpleTypeObj.getFinalDerivation() != XmlSchemaDerivationMethod.NONE) {
         serializedSimpleType.setAttributeNS((String)null, "final", simpleTypeObj.getFinalDerivation().toString());
      }

      if (simpleTypeObj.getId() != null) {
         serializedSimpleType.setAttributeNS((String)null, "id", simpleTypeObj.getId());
      }

      if (!simpleTypeObj.isAnonymous()) {
         serializedSimpleType.setAttributeNS((String)null, "name", simpleTypeObj.getName());
      }

      if (simpleTypeObj.getAnnotation() != null) {
         Element annotationEl = this.serializeAnnotation(doc, simpleTypeObj.getAnnotation(), schema);
         serializedSimpleType.appendChild(annotationEl);
      }

      if (simpleTypeObj.content != null) {
         if (simpleTypeObj.content instanceof XmlSchemaSimpleTypeRestriction) {
            Element restEl = this.serializeSimpleTypeRestriction(doc, (XmlSchemaSimpleTypeRestriction)simpleTypeObj.content, schema);
            serializedSimpleType.appendChild(restEl);
         } else if (simpleTypeObj.content instanceof XmlSchemaSimpleTypeList) {
            Element listEl = this.serializeSimpleTypeList(doc, (XmlSchemaSimpleTypeList)simpleTypeObj.content, schema);
            serializedSimpleType.appendChild(listEl);
         } else if (simpleTypeObj.content instanceof XmlSchemaSimpleTypeUnion) {
            Element unionEl = this.serializeSimpleTypeUnion(doc, (XmlSchemaSimpleTypeUnion)simpleTypeObj.content, schema);
            serializedSimpleType.appendChild(unionEl);
         }
      }

      this.processExtensibilityComponents(simpleTypeObj, serializedSimpleType);
      return serializedSimpleType;
   }

   Element serializeSimpleTypeList(Document doc, XmlSchemaSimpleTypeList listObj, XmlSchema schema) throws XmlSchemaSerializerException {
      Element list = this.createNewElement(doc, "list", schema.getSchemaNamespacePrefix(), "http://www.w3.org/2001/XMLSchema");
      if (listObj.itemTypeName != null) {
         String listItemType = this.resolveQName(listObj.itemTypeName, schema);
         list.setAttributeNS((String)null, "itemType", listItemType);
      }

      if (listObj.getId() != null) {
         list.setAttributeNS((String)null, "id", listObj.getId());
      } else if (listObj.itemType != null) {
         Element inlineSimpleEl = this.serializeSimpleType(doc, listObj.itemType, schema);
         list.appendChild(inlineSimpleEl);
      }

      if (listObj.getAnnotation() != null) {
         Element annotation = this.serializeAnnotation(doc, listObj.getAnnotation(), schema);
         list.appendChild(annotation);
      }

      this.processExtensibilityComponents(listObj, list);
      return list;
   }

   Element serializeSimpleTypeRestriction(Document doc, XmlSchemaSimpleTypeRestriction restrictionObj, XmlSchema schema) throws XmlSchemaSerializerException {
      Element serializedRestriction = this.createNewElement(doc, "restriction", schema.getSchemaNamespacePrefix(), "http://www.w3.org/2001/XMLSchema");
      if (schema.getSchemaNamespacePrefix().length() > 0) {
         serializedRestriction.setPrefix(schema.getSchemaNamespacePrefix());
      }

      if (restrictionObj.getBaseTypeName() != null) {
         String baseType = this.resolveQName(restrictionObj.getBaseTypeName(), schema);
         serializedRestriction.setAttributeNS((String)null, "base", baseType);
      } else {
         if (!(restrictionObj.getBaseType() instanceof XmlSchemaSimpleType)) {
            throw new XmlSchemaSerializerException("restriction must be define with specifying base or inline simpleType");
         }

         Element inlineSimpleType = this.serializeSimpleType(doc, restrictionObj.getBaseType(), schema);
         serializedRestriction.appendChild(inlineSimpleType);
      }

      if (restrictionObj.getId() != null) {
         serializedRestriction.setAttributeNS((String)null, "id", restrictionObj.getId());
      }

      if (restrictionObj.getAnnotation() != null) {
         Element annotation = this.serializeAnnotation(doc, restrictionObj.getAnnotation(), schema);
         serializedRestriction.appendChild(annotation);
      }

      if (restrictionObj.getFacets().size() > 0) {
         int facetsNum = restrictionObj.getFacets().size();

         for(int i = 0; i < facetsNum; ++i) {
            Element facetEl = this.serializeFacet(doc, (XmlSchemaFacet)restrictionObj.getFacets().get(i), schema);
            serializedRestriction.appendChild(facetEl);
         }
      }

      this.processExtensibilityComponents(restrictionObj, serializedRestriction);
      return serializedRestriction;
   }

   Element serializeSimpleTypeUnion(Document doc, XmlSchemaSimpleTypeUnion unionObj, XmlSchema schema) throws XmlSchemaSerializerException {
      Element union = this.createNewElement(doc, "union", schema.getSchemaNamespacePrefix(), "http://www.w3.org/2001/XMLSchema");
      if (unionObj.getId() != null) {
         union.setAttributeNS((String)null, "id", unionObj.getId());
      }

      if (unionObj.getMemberTypesSource() != null) {
         QName[] memberTypesQNames = unionObj.getMemberTypesQNames();

         for(QName qn : memberTypesQNames) {
            String namespace = qn.getNamespaceURI();
            if (namespace.length() != 0 && qn.getPrefix().length() != 0) {
               String prefix = (String)this.schemaNamespace.get(namespace);
               if (!qn.getPrefix().equals(prefix)) {
                  prefix = union.lookupPrefix(namespace);
               }

               if (!qn.getPrefix().equals(prefix)) {
                  union.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:" + qn.getPrefix(), qn.getNamespaceURI());
               }
            }
         }

         union.setAttributeNS((String)null, "memberTypes", unionObj.getMemberTypesSource());
      } else {
         QName[] memberTypesQNames = unionObj.getMemberTypesQNames();
         if (memberTypesQNames != null && memberTypesQNames.length > 0) {
            StringBuilder memberTypes = new StringBuilder();
            int i = 0;

            for(int n = memberTypesQNames.length - 1; i <= n; ++i) {
               QName memberTypesQName = memberTypesQNames[i];
               String namespace = memberTypesQName.getNamespaceURI();
               if (namespace.length() != 0) {
                  String prefix = (String)this.schemaNamespace.get(namespace);
                  if (prefix.length() != 0) {
                     memberTypes.append(prefix).append(':');
                  }
               }

               memberTypes.append(memberTypesQName.getLocalPart());
               if (i != n) {
                  memberTypes.append(' ');
               }
            }

            union.setAttributeNS((String)null, "memberTypes", memberTypes.toString());
         }
      }

      if (unionObj.getBaseTypes().size() > 0) {
         int baseTypesLength = unionObj.getBaseTypes().size();

         for(int i = 0; i < baseTypesLength; ++i) {
            try {
               Element baseType = this.serializeSimpleType(doc, (XmlSchemaSimpleType)unionObj.getBaseTypes().get(i), schema);
               union.appendChild(baseType);
            } catch (ClassCastException var12) {
               throw new XmlSchemaSerializerException("only inline simple type allow as attribute's inline type");
            }
         }
      }

      if (unionObj.getAnnotation() != null) {
         Element annotation = this.serializeAnnotation(doc, unionObj.getAnnotation(), schema);
         union.appendChild(annotation);
      }

      this.processExtensibilityComponents(unionObj, union);
      return union;
   }

   void setupAttr(Document doc, List attrColl, XmlSchema schema, Element container) throws XmlSchemaSerializerException {
      int collectionLength = attrColl.size();

      for(int i = 0; i < collectionLength; ++i) {
         XmlSchemaAttributeOrGroupRef obj = (XmlSchemaAttributeOrGroupRef)attrColl.get(i);
         if (obj instanceof XmlSchemaAttribute) {
            XmlSchemaAttribute attr = (XmlSchemaAttribute)obj;
            Element attrEl = this.serializeAttribute(doc, attr, schema);
            container.appendChild(attrEl);
         } else if (obj instanceof XmlSchemaAttributeGroupRef) {
            XmlSchemaAttributeGroupRef attr = (XmlSchemaAttributeGroupRef)obj;
            Element attrEl = this.serializeAttributeGroupRef(doc, attr, schema);
            container.appendChild(attrEl);
         }
      }

   }

   private void appendElement(Document doc, Element parent, Node children, XmlSchema schema) {
      Element elTmp = (Element)children;
      Element el = this.createNewElement(doc, elTmp.getLocalName(), schema.getSchemaNamespacePrefix(), "http://www.w3.org/2001/XMLSchema");
      NamedNodeMap attributes = el.getAttributes();
      int attributeLength = attributes.getLength();

      for(int i = 0; i < attributeLength; ++i) {
         Node n = attributes.item(i);
         el.setAttributeNS((String)null, n.getNodeName(), n.getNodeValue());
      }

      NodeList decendants = el.getChildNodes();
      int decendantLength = decendants.getLength();

      for(int i = 0; i < decendantLength; ++i) {
         Node n = decendants.item(i);
         short nodeType = n.getNodeType();
         if (nodeType == 3) {
            String nValue = n.getNodeValue();
            Text t = doc.createTextNode(nValue);
            el.appendChild(t);
         } else if (nodeType == 4) {
            String nValue = n.getNodeValue();
            CDATASection s = doc.createCDATASection(nValue);
            el.appendChild(s);
         } else if (nodeType == 1) {
            this.appendElement(doc, el, n, schema);
         }
      }

   }

   private Element constructFacet(XmlSchemaFacet facetObj, Document doc, XmlSchema schema, String tagName) {
      Element facetEl = this.createNewElement(doc, tagName, schema.getSchemaNamespacePrefix(), "http://www.w3.org/2001/XMLSchema");
      facetEl.setAttributeNS((String)null, "value", facetObj.value.toString());
      if (facetObj.fixed) {
         facetEl.setAttributeNS((String)null, "fixed", "true");
      }

      if (facetObj.getAnnotation() != null) {
         Element annotation = this.serializeAnnotation(doc, facetObj.getAnnotation(), schema);
         facetEl.appendChild(annotation);
      }

      return facetEl;
   }

   private Element createNewElement(Document document, String localName, String prefix, String namespace) {
      if (prefix.length() > 0) {
         prefix = prefix + ":";
      }

      String elementName = prefix + localName;
      return document.createElementNS(namespace, elementName);
   }

   private void processExtensibilityComponents(XmlSchemaObject schemaObject, Element parentElement) {
      if (this.extReg != null) {
         Map<Object, Object> metaInfoMap = schemaObject.getMetaInfoMap();
         if (metaInfoMap != null && !metaInfoMap.isEmpty()) {
            for(Object key : metaInfoMap.keySet()) {
               this.extReg.serializeExtension(schemaObject, metaInfoMap.get(key).getClass(), parentElement);
            }
         }
      }

   }

   private String resolveQName(QName names, XmlSchema schemaObj) {
      String namespace = names.getNamespaceURI();
      String[] type = getParts(names.getLocalPart());
      String typeName = type.length > 1 ? type[1] : type[0];
      String prefix = "".equals(namespace) ? "" : (String)this.schemaNamespace.get(namespace);
      if (prefix == null) {
         if ("http://www.w3.org/XML/1998/namespace".equals(namespace)) {
            prefix = "xml";
         } else {
            int magicNumber = 0;

            for(Collection<String> prefixes = this.schemaNamespace.values(); prefixes.contains("ns" + magicNumber); ++magicNumber) {
            }

            prefix = "ns" + magicNumber;
            this.schemaNamespace.put(namespace, prefix);
            this.schemaElement.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:" + prefix.toString(), namespace);
         }
      }

      String prefixStr = prefix.toString();
      prefixStr = prefixStr.trim().length() > 0 ? prefixStr + ":" : "";
      return prefixStr + typeName;
   }

   private void serializeMaxMinOccurs(XmlSchemaParticle particle, Element element) {
      if (particle.getMaxOccurs() >= Long.MAX_VALUE || particle.getMaxOccurs() <= 1L && particle.getMaxOccurs() != 0L) {
         if (particle.getMaxOccurs() == Long.MAX_VALUE) {
            element.setAttributeNS((String)null, "maxOccurs", "unbounded");
         }
      } else {
         element.setAttributeNS((String)null, "maxOccurs", particle.getMaxOccurs() + "");
      }

      if (particle.getMinOccurs() > 1L || particle.getMinOccurs() == 0L) {
         element.setAttributeNS((String)null, "minOccurs", particle.getMinOccurs() + "");
      }

   }

   private void serializeSchemaChild(List items, Element serializedSchema, Document serializedSchemaDocs, XmlSchema schemaObj, boolean serializeIncluded) throws XmlSchemaSerializerException {
      int itemsLength = items.size();

      for(int i = 0; i < itemsLength; ++i) {
         XmlSchemaObject obj = (XmlSchemaObject)items.get(i);
         if (obj instanceof XmlSchemaInclude) {
            Element e = this.serializeInclude(serializedSchemaDocs, (XmlSchemaInclude)obj, schemaObj, serializeIncluded);
            serializedSchema.appendChild(e);
         } else if (obj instanceof XmlSchemaImport) {
            Element e = this.serializeImport(serializedSchemaDocs, (XmlSchemaImport)obj, schemaObj, serializeIncluded);
            serializedSchema.appendChild(e);
         }
      }

      for(int i = 0; i < itemsLength; ++i) {
         XmlSchemaObject obj = (XmlSchemaObject)items.get(i);
         if (obj instanceof XmlSchemaElement) {
            Element e = this.serializeElement(serializedSchemaDocs, (XmlSchemaElement)obj, schemaObj);
            serializedSchema.appendChild(e);
         } else if (obj instanceof XmlSchemaSimpleType) {
            Element e = this.serializeSimpleType(serializedSchemaDocs, (XmlSchemaSimpleType)obj, schemaObj);
            serializedSchema.appendChild(e);
         } else if (obj instanceof XmlSchemaComplexType) {
            Element e = this.serializeComplexType(serializedSchemaDocs, (XmlSchemaComplexType)obj, schemaObj);
            serializedSchema.appendChild(e);
         } else if (obj instanceof XmlSchemaGroup) {
            Element e = this.serializeGroup(serializedSchemaDocs, (XmlSchemaGroup)obj, schemaObj);
            serializedSchema.appendChild(e);
         } else if (obj instanceof XmlSchemaAttributeGroup) {
            Element e = this.serializeAttributeGroup(serializedSchemaDocs, (XmlSchemaAttributeGroup)obj, schemaObj);
            serializedSchema.appendChild(e);
         } else if (obj instanceof XmlSchemaAttribute) {
            Element e = this.serializeAttribute(serializedSchemaDocs, (XmlSchemaAttribute)obj, schemaObj);
            serializedSchema.appendChild(e);
         } else if (obj instanceof XmlSchemaRedefine) {
            Element e = this.serializeRedefine(serializedSchemaDocs, (XmlSchemaRedefine)obj, schemaObj);
            serializedSchema.appendChild(e);
         }
      }

   }

   private Element setupNamespaces(Document schemaDocs, XmlSchema schemaObj) {
      NamespacePrefixList ctx = schemaObj.getNamespaceContext();
      if (ctx != null) {
         this.xsdPrefix = ctx.getPrefix("http://www.w3.org/2001/XMLSchema");
      } else {
         this.xsdPrefix = null;
      }

      if (this.xsdPrefix == null) {
         this.xsdPrefix = "";
         if (ctx != null && ctx.getNamespaceURI(this.xsdPrefix).length() > 0) {
            this.xsdPrefix = "xsd";
         }

         StringBuilder var10001;
         for(int count = 0; ctx != null && ctx.getNamespaceURI(this.xsdPrefix).length() > 0; this.xsdPrefix = var10001.append(count).toString()) {
            var10001 = (new StringBuilder()).append("xsd");
            ++count;
         }
      }

      schemaObj.setSchemaNamespacePrefix(this.xsdPrefix);
      Element schemaEl = this.createNewElement(schemaDocs, "schema", schemaObj.getSchemaNamespacePrefix(), "http://www.w3.org/2001/XMLSchema");
      if (ctx != null) {
         String[] prefixes = ctx.getDeclaredPrefixes();

         for(int i = 0; i < prefixes.length; ++i) {
            String prefix = prefixes[i];
            if (prefix != null) {
               String uri = ctx.getNamespaceURI(prefix);
               if (uri.length() > 0) {
                  if ("".equals(prefix) || !this.schemaNamespace.containsKey(uri)) {
                     this.schemaNamespace.put(uri, prefix);
                  }

                  prefix = prefix.length() > 0 ? "xmlns:" + prefix : "xmlns";
                  schemaEl.setAttributeNS("http://www.w3.org/2000/xmlns/", prefix, uri);
               }
            }
         }
      }

      if (this.schemaNamespace.get("http://www.w3.org/2001/XMLSchema") == null) {
         this.schemaNamespace.put("http://www.w3.org/2001/XMLSchema", this.xsdPrefix);
         if ("".equals(this.xsdPrefix)) {
            schemaEl.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns", "http://www.w3.org/2001/XMLSchema");
         } else {
            schemaEl.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:" + this.xsdPrefix, "http://www.w3.org/2001/XMLSchema");
         }

         schemaObj.setSchemaNamespacePrefix(this.xsdPrefix);
      }

      return schemaEl;
   }

   public static class XmlSchemaSerializerException extends Exception {
      private static final long serialVersionUID = 1L;

      public XmlSchemaSerializerException(String msg) {
         super(msg);
      }
   }
}
