package org.datanucleus.metadata;

import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.util.StringUtils;

public class ElementMetaData extends AbstractElementMetaData {
   private static final long serialVersionUID = 512052075696338985L;

   public ElementMetaData(ElementMetaData emd) {
      super(emd);
   }

   public ElementMetaData() {
   }

   public void populate(ClassLoaderResolver clr, ClassLoader primary, MetaDataManager mmgr) {
      AbstractMemberMetaData fmd = (AbstractMemberMetaData)this.parent;
      if (fmd.hasCollection()) {
         fmd.getCollection().element.populate(fmd.getAbstractClassMetaData().getPackageName(), clr, primary, mmgr);
      } else if (fmd.hasArray()) {
         fmd.getArray().element.populate(fmd.getAbstractClassMetaData().getPackageName(), clr, primary, mmgr);
      }

      if (this.embeddedMetaData == null && ((AbstractMemberMetaData)this.parent).hasCollection() && ((AbstractMemberMetaData)this.parent).getCollection().isEmbeddedElement() && ((AbstractMemberMetaData)this.parent).getJoinMetaData() != null && ((AbstractMemberMetaData)this.parent).getCollection().elementIsPersistent()) {
         this.embeddedMetaData = new EmbeddedMetaData();
         this.embeddedMetaData.parent = this;
      }

      super.populate(clr, primary, mmgr);
   }

   public String toString(String prefix, String indent) {
      StringBuilder sb = new StringBuilder();
      sb.append(prefix).append("<element");
      if (this.mappedBy != null) {
         sb.append(" mapped-by=\"" + this.mappedBy + "\"");
      }

      if (!StringUtils.isWhitespace(this.table)) {
         sb.append(" table=\"" + this.table + "\"");
      }

      if (!StringUtils.isWhitespace(this.columnName)) {
         sb.append(" column=\"" + this.columnName + "\"");
      }

      sb.append(">\n");
      if (this.columns != null) {
         for(ColumnMetaData colmd : this.columns) {
            sb.append(colmd.toString(prefix + indent, indent));
         }
      }

      if (this.indexMetaData != null) {
         sb.append(this.indexMetaData.toString(prefix + indent, indent));
      }

      if (this.uniqueMetaData != null) {
         sb.append(this.uniqueMetaData.toString(prefix + indent, indent));
      }

      if (this.embeddedMetaData != null) {
         sb.append(this.embeddedMetaData.toString(prefix + indent, indent));
      }

      if (this.foreignKeyMetaData != null) {
         sb.append(this.foreignKeyMetaData.toString(prefix + indent, indent));
      }

      sb.append(super.toString(prefix + indent, indent));
      sb.append(prefix).append("</element>\n");
      return sb.toString();
   }
}
