package org.datanucleus.metadata;

import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.exceptions.ClassNotResolvedException;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.util.StringUtils;

public class KeyMetaData extends AbstractElementMetaData {
   private static final long serialVersionUID = -3379637846354140692L;

   public KeyMetaData(KeyMetaData kmd) {
      super(kmd);
   }

   public KeyMetaData() {
   }

   public void populate(ClassLoaderResolver clr, ClassLoader primary, MetaDataManager mmgr) {
      AbstractMemberMetaData fmd = (AbstractMemberMetaData)this.parent;
      if (fmd.getMap() == null) {
         throw (new NucleusUserException("The field " + fmd.getFullFieldName() + " is defined with <key>, however no <map> definition was found.")).setFatal();
      } else {
         fmd.getMap().key.populate(fmd.getAbstractClassMetaData().getPackageName(), clr, primary, mmgr);
         String keyType = fmd.getMap().getKeyType();
         Class keyTypeClass = null;

         try {
            keyTypeClass = clr.classForName(keyType, primary);
         } catch (ClassNotResolvedException var8) {
            throw new InvalidMemberMetaDataException("044147", new Object[]{fmd.getClassName(), fmd.getName(), keyType});
         }

         if (this.embeddedMetaData == null || !keyTypeClass.isInterface() && !keyTypeClass.getName().equals("java.lang.Object")) {
            if (this.embeddedMetaData == null && ((AbstractMemberMetaData)this.parent).hasMap() && ((AbstractMemberMetaData)this.parent).getMap().isEmbeddedKey() && ((AbstractMemberMetaData)this.parent).getJoinMetaData() != null && ((AbstractMemberMetaData)this.parent).getMap().keyIsPersistent()) {
               this.embeddedMetaData = new EmbeddedMetaData();
               this.embeddedMetaData.parent = this;
            }

            super.populate(clr, primary, mmgr);
         } else {
            throw new InvalidMemberMetaDataException("044152", new Object[]{fmd.getClassName(), fmd.getName(), keyTypeClass.getName()});
         }
      }
   }

   public String toString(String prefix, String indent) {
      StringBuilder sb = new StringBuilder();
      sb.append(prefix).append("<key");
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
      sb.append(prefix).append("</key>\n");
      return sb.toString();
   }
}
