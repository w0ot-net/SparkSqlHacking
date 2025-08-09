package org.apache.ws.commons.schema.utils;

import java.util.Arrays;
import javax.xml.namespace.QName;
import org.apache.ws.commons.schema.XmlSchema;
import org.apache.ws.commons.schema.XmlSchemaException;

public class XmlSchemaNamedImpl implements XmlSchemaNamed {
   protected XmlSchema parentSchema;
   protected XmlSchemaRefBase refTwin;
   private QName qname;
   private boolean topLevel;

   public XmlSchemaNamedImpl(XmlSchema parent, boolean topLevel) {
      this.parentSchema = parent;
      this.topLevel = topLevel;
   }

   public boolean equals(Object what) {
      if (what == this) {
         return true;
      } else if (!(what instanceof XmlSchemaNamedImpl)) {
         return false;
      } else {
         XmlSchemaNamedImpl xsn = (XmlSchemaNamedImpl)what;
         boolean isTopLevelEq = this.topLevel == xsn.topLevel;
         boolean isParentSchemaEq = UtilObjects.equals(this.parentSchema, xsn.parentSchema);
         boolean isRefTwinEq = UtilObjects.equals(this.refTwin, xsn.refTwin);
         boolean isQNameEq = UtilObjects.equals(this.qname, xsn.qname);
         return isTopLevelEq && isParentSchemaEq && isRefTwinEq && isQNameEq;
      }
   }

   public int hashCode() {
      Object[] hashObjects = new Object[]{this.parentSchema, this.refTwin, this.qname};
      int hash = Arrays.hashCode(hashObjects);
      hash += this.topLevel ? 39 : 107;
      hash ^= super.hashCode();
      return hash;
   }

   public void setRefObject(XmlSchemaRefBase refBase) {
      this.refTwin = refBase;
   }

   public String getName() {
      return this.qname == null ? null : this.qname.getLocalPart();
   }

   public boolean isAnonymous() {
      return this.qname == null;
   }

   public void setName(String name) {
      if (name == null) {
         this.qname = null;
      } else {
         if ("".equals(name)) {
            throw new XmlSchemaException("Attempt to set empty name.");
         }

         if (this.refTwin != null && this.refTwin.getTargetQName() != null) {
            throw new XmlSchemaException("Attempt to set name on object with ref='xxx'");
         }

         this.qname = new QName(this.parentSchema.getLogicalTargetNamespace(), name);
      }

   }

   public XmlSchema getParent() {
      return this.parentSchema;
   }

   public QName getQName() {
      return this.qname;
   }

   public boolean isTopLevel() {
      return this.topLevel;
   }
}
