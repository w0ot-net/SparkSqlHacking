package org.apache.ws.commons.schema;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import javax.xml.namespace.QName;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.ws.commons.schema.utils.CollectionFactory;
import org.apache.ws.commons.schema.utils.NamespaceContextOwner;
import org.apache.ws.commons.schema.utils.NamespacePrefixList;
import org.w3c.dom.Document;

public class XmlSchema extends XmlSchemaAnnotated implements NamespaceContextOwner {
   static final String SCHEMA_NS = "http://www.w3.org/2001/XMLSchema";
   private static final String UTF_8_ENCODING = "UTF-8";
   private List items;
   private XmlSchemaCollection parent;
   private XmlSchemaDerivationMethod blockDefault;
   private XmlSchemaDerivationMethod finalDefault;
   private XmlSchemaForm elementFormDefault;
   private XmlSchemaForm attributeFormDefault;
   private List externals;
   private Map attributeGroups;
   private Map attributes;
   private Map elements;
   private Map groups;
   private Map notations;
   private Map schemaTypes;
   private String syntacticalTargetNamespace;
   private String schemaNamespacePrefix;
   private String logicalTargetNamespace;
   private String version;
   private NamespacePrefixList namespaceContext;
   private String inputEncoding;

   public XmlSchema() {
      this((String)null, (String)null, (XmlSchemaCollection)null);
   }

   public XmlSchema(String namespace, String systemId, XmlSchemaCollection parent) {
      this.parent = parent;
      this.attributeFormDefault = XmlSchemaForm.UNQUALIFIED;
      this.elementFormDefault = XmlSchemaForm.UNQUALIFIED;
      this.blockDefault = XmlSchemaDerivationMethod.NONE;
      this.finalDefault = XmlSchemaDerivationMethod.NONE;
      this.items = new ArrayList();
      this.externals = new ArrayList();
      this.elements = new HashMap();
      this.attributeGroups = new HashMap();
      this.attributes = new HashMap();
      this.groups = new HashMap();
      this.notations = new HashMap();
      this.schemaTypes = new HashMap();
      this.logicalTargetNamespace = namespace;
      this.syntacticalTargetNamespace = namespace;
      if (this.logicalTargetNamespace == null) {
         this.logicalTargetNamespace = "";
      }

      if (parent != null) {
         XmlSchemaCollection.SchemaKey schemaKey = new XmlSchemaCollection.SchemaKey(this.logicalTargetNamespace, systemId);
         if (parent.containsSchema(schemaKey)) {
            throw new XmlSchemaException("Schema name conflict in collection");
         }

         parent.addSchema(schemaKey, this);
      }

   }

   public XmlSchema(String namespace, XmlSchemaCollection parent) {
      this(namespace, namespace, parent);
   }

   public Document[] getAllSchemas() {
      try {
         XmlSchemaSerializer xser = new XmlSchemaSerializer();
         xser.setExtReg(this.parent.getExtReg());
         return xser.serializeSchema(this, true);
      } catch (XmlSchemaSerializer.XmlSchemaSerializerException e) {
         throw new XmlSchemaException("Error serializing schema", e);
      }
   }

   public XmlSchemaAttribute getAttributeByName(QName name) {
      return this.getAttributeByName(name, true, (Stack)null);
   }

   public XmlSchemaAttribute getAttributeByName(String name) {
      QName nameToSearchFor = new QName(this.getTargetNamespace(), name);
      return this.getAttributeByName(nameToSearchFor, false, (Stack)null);
   }

   public XmlSchemaForm getAttributeFormDefault() {
      return this.attributeFormDefault;
   }

   public XmlSchemaAttributeGroup getAttributeGroupByName(QName name) {
      return this.getAttributeGroupByName(name, true, (Stack)null);
   }

   public Map getAttributeGroups() {
      return CollectionFactory.getProtectedMap(this.attributeGroups);
   }

   public Map getAttributes() {
      return CollectionFactory.getProtectedMap(this.attributes);
   }

   public XmlSchemaDerivationMethod getBlockDefault() {
      return this.blockDefault;
   }

   public XmlSchemaElement getElementByName(QName name) {
      return this.getElementByName(name, true, (Stack)null);
   }

   public XmlSchemaElement getElementByName(String name) {
      QName nameToSearchFor = new QName(this.getTargetNamespace(), name);
      return this.getElementByName(nameToSearchFor, false, (Stack)null);
   }

   public XmlSchemaForm getElementFormDefault() {
      return this.elementFormDefault;
   }

   public Map getElements() {
      return CollectionFactory.getProtectedMap(this.elements);
   }

   public List getExternals() {
      return CollectionFactory.getProtectedList(this.externals);
   }

   public XmlSchemaDerivationMethod getFinalDefault() {
      return this.finalDefault;
   }

   public XmlSchemaGroup getGroupByName(QName name) {
      return this.getGroupByName(name, true, (Stack)null);
   }

   public Map getGroups() {
      return CollectionFactory.getProtectedMap(this.groups);
   }

   public String getInputEncoding() {
      return this.inputEncoding;
   }

   public List getItems() {
      return CollectionFactory.getProtectedList(this.items);
   }

   public String getLogicalTargetNamespace() {
      return this.logicalTargetNamespace;
   }

   public NamespacePrefixList getNamespaceContext() {
      return this.namespaceContext;
   }

   public XmlSchemaNotation getNotationByName(QName name) {
      return this.getNotationByName(name, true, (Stack)null);
   }

   public Map getNotations() {
      return CollectionFactory.getProtectedMap(this.notations);
   }

   public XmlSchemaCollection getParent() {
      return this.parent;
   }

   public Document getSchemaDocument() throws XmlSchemaSerializer.XmlSchemaSerializerException {
      XmlSchemaSerializer xser = new XmlSchemaSerializer();
      xser.setExtReg(this.parent.getExtReg());
      return xser.serializeSchema(this, false)[0];
   }

   public String getSchemaNamespacePrefix() {
      return this.schemaNamespacePrefix;
   }

   public Map getSchemaTypes() {
      return this.schemaTypes;
   }

   public String getTargetNamespace() {
      return this.syntacticalTargetNamespace;
   }

   public XmlSchemaType getTypeByName(QName name) {
      return this.getTypeByName(name, true, (Stack)null);
   }

   public XmlSchemaType getTypeByName(String name) {
      QName nameToSearchFor = new QName(this.getTargetNamespace(), name);
      return this.getTypeByName(nameToSearchFor, false, (Stack)null);
   }

   public String getVersion() {
      return this.version;
   }

   public void setVersion(String version) {
      this.version = version;
   }

   public void setAttributeFormDefault(XmlSchemaForm value) {
      this.attributeFormDefault = value;
   }

   public void setBlockDefault(XmlSchemaDerivationMethod blockDefault) {
      this.blockDefault = blockDefault;
   }

   public void setElementFormDefault(XmlSchemaForm elementFormDefault) {
      this.elementFormDefault = elementFormDefault;
   }

   public void setFinalDefault(XmlSchemaDerivationMethod finalDefault) {
      this.finalDefault = finalDefault;
   }

   public void setInputEncoding(String encoding) {
      this.inputEncoding = encoding;
   }

   public void setNamespaceContext(NamespacePrefixList namespaceContext) {
      this.namespaceContext = namespaceContext;
   }

   public void setSchemaNamespacePrefix(String schemaNamespacePrefix) {
      this.schemaNamespacePrefix = schemaNamespacePrefix;
   }

   public void setTargetNamespace(String targetNamespace) {
      if (!"".equals(targetNamespace)) {
         this.logicalTargetNamespace = targetNamespace;
         this.syntacticalTargetNamespace = targetNamespace;
      }

   }

   public String toString() {
      return super.toString() + "[" + this.logicalTargetNamespace + "]";
   }

   public void write(OutputStream out) throws UnsupportedEncodingException {
      if (this.inputEncoding != null && !"".equals(this.inputEncoding)) {
         this.write((Writer)(new OutputStreamWriter(out, this.inputEncoding)));
      } else {
         this.write((Writer)(new OutputStreamWriter(out, "UTF-8")));
      }

   }

   public void write(OutputStream out, Map options) throws UnsupportedEncodingException {
      if (this.inputEncoding != null && !"".equals(this.inputEncoding)) {
         this.write((Writer)(new OutputStreamWriter(out, this.inputEncoding)), options);
      } else {
         this.write((Writer)(new OutputStreamWriter(out, "UTF-8")), options);
      }

   }

   public void write(Writer writer) {
      this.serializeInternal(writer, (Map)null);
   }

   public void write(Writer writer, Map options) {
      this.serializeInternal(writer, options);
   }

   protected XmlSchemaAttribute getAttributeByName(QName name, boolean deep, Stack schemaStack) {
      if (schemaStack != null && schemaStack.contains(this)) {
         return null;
      } else {
         XmlSchemaAttribute attribute = (XmlSchemaAttribute)this.attributes.get(name);
         if (deep) {
            if (attribute != null) {
               return attribute;
            }

            for(XmlSchemaExternal item : this.externals) {
               XmlSchema schema = this.getSchema(item);
               if (schema != null) {
                  if (schemaStack == null) {
                     schemaStack = new Stack();
                  }

                  schemaStack.push(this);
                  attribute = schema.getAttributeByName(name, deep, schemaStack);
                  if (attribute != null) {
                     return attribute;
                  }
               }
            }
         }

         return attribute;
      }
   }

   protected XmlSchemaAttributeGroup getAttributeGroupByName(QName name, boolean deep, Stack schemaStack) {
      if (schemaStack != null && schemaStack.contains(this)) {
         return null;
      } else {
         XmlSchemaAttributeGroup group = (XmlSchemaAttributeGroup)this.attributeGroups.get(name);
         if (deep) {
            if (group != null) {
               return group;
            }

            for(XmlSchemaExternal item : this.externals) {
               XmlSchema schema = this.getSchema(item);
               if (schema != null) {
                  if (schemaStack == null) {
                     schemaStack = new Stack();
                  }

                  schemaStack.push(this);
                  group = schema.getAttributeGroupByName(name, deep, schemaStack);
                  if (group != null) {
                     return group;
                  }
               }
            }
         }

         return group;
      }
   }

   protected XmlSchemaElement getElementByName(QName name, boolean deep, Stack schemaStack) {
      if (schemaStack != null && schemaStack.contains(this)) {
         return null;
      } else {
         XmlSchemaElement element = (XmlSchemaElement)this.elements.get(name);
         if (deep) {
            if (element != null) {
               return element;
            }

            for(XmlSchemaExternal item : this.externals) {
               XmlSchema schema = this.getSchema(item);
               if (schema != null) {
                  if (schemaStack == null) {
                     schemaStack = new Stack();
                  }

                  schemaStack.push(this);
                  element = schema.getElementByName(name, deep, schemaStack);
                  if (element != null) {
                     return element;
                  }
               }
            }
         }

         return element;
      }
   }

   protected XmlSchemaGroup getGroupByName(QName name, boolean deep, Stack schemaStack) {
      if (schemaStack != null && schemaStack.contains(this)) {
         return null;
      } else {
         XmlSchemaGroup group = (XmlSchemaGroup)this.groups.get(name);
         if (deep) {
            if (group != null) {
               return group;
            }

            for(XmlSchemaExternal item : this.externals) {
               XmlSchema schema = this.getSchema(item);
               if (schema != null) {
                  if (schemaStack == null) {
                     schemaStack = new Stack();
                  }

                  schemaStack.push(this);
                  group = schema.getGroupByName(name, deep, schemaStack);
                  if (group != null) {
                     return group;
                  }
               }
            }
         }

         return group;
      }
   }

   protected XmlSchemaNotation getNotationByName(QName name, boolean deep, Stack schemaStack) {
      if (schemaStack != null && schemaStack.contains(this)) {
         return null;
      } else {
         XmlSchemaNotation notation = (XmlSchemaNotation)this.notations.get(name);
         if (deep) {
            if (notation != null) {
               return notation;
            }

            for(XmlSchemaExternal item : this.externals) {
               XmlSchema schema = this.getSchema(item);
               if (schema != null) {
                  if (schemaStack == null) {
                     schemaStack = new Stack();
                  }

                  schemaStack.push(this);
                  notation = schema.getNotationByName(name, deep, schemaStack);
                  if (notation != null) {
                     return notation;
                  }
               }
            }
         }

         return notation;
      }
   }

   protected XmlSchemaType getTypeByName(QName name, boolean deep, Stack schemaStack) {
      if (schemaStack != null && schemaStack.contains(this)) {
         return null;
      } else {
         XmlSchemaType type = (XmlSchemaType)this.schemaTypes.get(name);
         if (deep) {
            if (type != null) {
               return type;
            }

            for(XmlSchemaExternal item : this.externals) {
               XmlSchema schema = this.getSchema(item);
               if (schema != null) {
                  if (schemaStack == null) {
                     schemaStack = new Stack();
                  }

                  schemaStack.push(this);
                  type = schema.getTypeByName(name, deep, schemaStack);
                  if (type != null) {
                     return type;
                  }
               }
            }
         }

         return type;
      }
   }

   String getSyntacticalTargetNamespace() {
      return this.syntacticalTargetNamespace;
   }

   void setLogicalTargetNamespace(String logicalTargetNamespace) {
      this.logicalTargetNamespace = logicalTargetNamespace;
   }

   void setParent(XmlSchemaCollection parent) {
      this.parent = parent;
   }

   void setSyntacticalTargetNamespace(String syntacticalTargetNamespace) {
      this.syntacticalTargetNamespace = syntacticalTargetNamespace;
   }

   private XmlSchema getSchema(Object includeOrImport) {
      XmlSchema schema;
      if (includeOrImport instanceof XmlSchemaImport) {
         schema = ((XmlSchemaImport)includeOrImport).getSchema();
      } else if (includeOrImport instanceof XmlSchemaInclude) {
         schema = ((XmlSchemaInclude)includeOrImport).getSchema();
      } else {
         schema = null;
      }

      return schema;
   }

   private void loadDefaultOptions(Map options) {
      options.put("omit-xml-declaration", "yes");
      options.put("indent", "yes");
   }

   private void serializeInternal(Writer out, Map options) {
      try {
         XmlSchemaSerializer xser = new XmlSchemaSerializer();
         xser.setExtReg(this.parent.getExtReg());
         Document[] serializedSchemas = xser.serializeSchema(this, false);
         TransformerFactory trFac = TransformerFactory.newInstance();
         trFac.setFeature("http://javax.xml.XMLConstants/feature/secure-processing", Boolean.TRUE);

         try {
            trFac.setAttribute("indent-number", "4");
         } catch (IllegalArgumentException var11) {
         }

         Source source = new DOMSource(serializedSchemas[0]);
         Result result = new StreamResult(out);
         Transformer tr = trFac.newTransformer();
         if (this.inputEncoding != null && !"".equals(this.inputEncoding)) {
            tr.setOutputProperty("encoding", this.inputEncoding);
         }

         if (options == null) {
            options = new HashMap();
            this.loadDefaultOptions(options);
         }

         for(Object key : options.keySet()) {
            tr.setOutputProperty((String)key, (String)options.get(key));
         }

         tr.transform(source, result);
         out.flush();
      } catch (TransformerConfigurationException e) {
         throw new XmlSchemaException(e.getMessage());
      } catch (TransformerException e) {
         throw new XmlSchemaException(e.getMessage());
      } catch (XmlSchemaSerializer.XmlSchemaSerializerException e) {
         throw new XmlSchemaException(e.getMessage());
      } catch (IOException e) {
         throw new XmlSchemaException(e.getMessage());
      }
   }
}
