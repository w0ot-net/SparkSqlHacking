package org.apache.ws.commons.schema;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.xml.namespace.QName;

public class XmlSchemaComplexType extends XmlSchemaType {
   private XmlSchemaAnyAttribute anyAttribute;
   private XmlSchemaAnyAttribute attributeWildcard;
   private List attributes = Collections.synchronizedList(new ArrayList());
   private XmlSchemaDerivationMethod block;
   private XmlSchemaDerivationMethod blockResolved;
   private XmlSchemaContentModel contentModel;
   private XmlSchemaContentType contentType;
   private XmlSchemaParticle particleType;
   private XmlSchemaParticle particle;
   private boolean isAbstract;
   private boolean isMixed;

   public XmlSchemaComplexType(XmlSchema schema, boolean topLevel) {
      super(schema, topLevel);
      this.block = XmlSchemaDerivationMethod.NONE;
      this.isAbstract = false;
      this.isMixed = false;
   }

   public XmlSchemaAnyAttribute getAnyAttribute() {
      return this.anyAttribute;
   }

   public void setAnyAttribute(XmlSchemaAnyAttribute anyAttribute) {
      this.anyAttribute = anyAttribute;
   }

   public List getAttributes() {
      return this.attributes;
   }

   public XmlSchemaAnyAttribute getAttributeWildcard() {
      return this.attributeWildcard;
   }

   public XmlSchemaDerivationMethod getBlock() {
      return this.block;
   }

   public void setBlock(XmlSchemaDerivationMethod block) {
      this.block = block;
   }

   public XmlSchemaDerivationMethod getBlockResolved() {
      return this.blockResolved;
   }

   public XmlSchemaContentModel getContentModel() {
      return this.contentModel;
   }

   public void setContentModel(XmlSchemaContentModel contentModel) {
      this.contentModel = contentModel;
   }

   public XmlSchemaContentType getContentType() {
      return this.contentType;
   }

   public void setContentType(XmlSchemaContentType contentType) {
      this.contentType = contentType;
   }

   public XmlSchemaParticle getContentTypeParticle() {
      return this.particleType;
   }

   public boolean isAbstract() {
      return this.isAbstract;
   }

   public void setAbstract(boolean b) {
      this.isAbstract = b;
   }

   public boolean isMixed() {
      return this.isMixed;
   }

   public void setMixed(boolean b) {
      this.isMixed = b;
   }

   public XmlSchemaParticle getParticle() {
      return this.particle;
   }

   public void setParticle(XmlSchemaParticle particle) {
      this.particle = particle;
   }

   public QName getBaseSchemaTypeName() {
      XmlSchemaContentModel model = this.getContentModel();
      if (model == null) {
         return null;
      } else {
         XmlSchemaContent content = model.getContent();
         if (content == null) {
            return null;
         } else if (content instanceof XmlSchemaComplexContentExtension) {
            return ((XmlSchemaComplexContentExtension)content).getBaseTypeName();
         } else {
            return content instanceof XmlSchemaComplexContentRestriction ? ((XmlSchemaComplexContentRestriction)content).getBaseTypeName() : null;
         }
      }
   }

   void setAttributeWildcard(XmlSchemaAnyAttribute attributeWildcard) {
      this.attributeWildcard = attributeWildcard;
   }

   void setAttributes(List attributes) {
      this.attributes = attributes;
   }

   void setBlockResolved(XmlSchemaDerivationMethod blockResolved) {
      this.blockResolved = blockResolved;
   }

   void setParticleType(XmlSchemaParticle particleType) {
      this.particleType = particleType;
   }

   XmlSchemaParticle getParticleType() {
      return this.particleType;
   }
}
