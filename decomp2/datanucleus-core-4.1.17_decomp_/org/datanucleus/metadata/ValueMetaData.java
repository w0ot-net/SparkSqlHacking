package org.datanucleus.metadata;

import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.exceptions.ClassNotResolvedException;
import org.datanucleus.exceptions.NucleusFatalUserException;
import org.datanucleus.util.StringUtils;

public class ValueMetaData extends AbstractElementMetaData {
   private static final long serialVersionUID = -3179830024157613599L;

   public ValueMetaData(ValueMetaData vmd) {
      super(vmd);
   }

   public ValueMetaData() {
   }

   public void populate(ClassLoaderResolver clr, ClassLoader primary, MetaDataManager mmgr) {
      AbstractMemberMetaData fmd = (AbstractMemberMetaData)this.parent;
      if (fmd.getMap() == null) {
         throw new NucleusFatalUserException("The field " + fmd.getFullFieldName() + " is defined with <value>, however no <map> definition was found.");
      } else {
         fmd.getMap().value.populate(fmd.getAbstractClassMetaData().getPackageName(), clr, primary, mmgr);
         String valueType = fmd.getMap().getValueType();
         Class valueTypeClass = null;

         try {
            valueTypeClass = clr.classForName(valueType, primary);
         } catch (ClassNotResolvedException var8) {
            throw new InvalidMemberMetaDataException("044150", new Object[]{fmd.getClassName(), fmd.getName(), valueType});
         }

         if (this.embeddedMetaData == null || !valueTypeClass.isInterface() && !valueTypeClass.getName().equals("java.lang.Object")) {
            if (this.embeddedMetaData == null && ((AbstractMemberMetaData)this.parent).hasMap() && ((AbstractMemberMetaData)this.parent).getMap().isEmbeddedValue() && ((AbstractMemberMetaData)this.parent).getJoinMetaData() != null && ((AbstractMemberMetaData)this.parent).getMap().valueIsPersistent()) {
               this.embeddedMetaData = new EmbeddedMetaData();
               this.embeddedMetaData.parent = this;
            }

            super.populate(clr, primary, mmgr);
         } else {
            throw new InvalidMemberMetaDataException("044152", new Object[]{fmd.getClassName(), fmd.getName(), valueTypeClass.getName()});
         }
      }
   }

   public String toString(String prefix, String indent) {
      StringBuilder sb = new StringBuilder();
      sb.append(prefix).append("<value");
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
      sb.append(prefix).append("</value>\n");
      return sb.toString();
   }
}
