package org.datanucleus.metadata;

import org.datanucleus.util.StringUtils;

public class PropertyMetaData extends AbstractMemberMetaData {
   private static final long serialVersionUID = -1281091318359894652L;
   protected String fieldName;

   public PropertyMetaData(MetaData parent, PropertyMetaData fmd) {
      super(parent, (AbstractMemberMetaData)fmd);
      this.fieldName = fmd.fieldName;
   }

   public PropertyMetaData(MetaData parent, String name) {
      super(parent, name);
   }

   public String getFieldName() {
      return this.fieldName;
   }

   public PropertyMetaData setFieldName(String name) {
      this.fieldName = name;
      return this;
   }

   public String toString(String prefix, String indent) {
      if (this.isStatic()) {
         return "";
      } else {
         StringBuilder sb = new StringBuilder();
         sb.append(prefix).append("<property name=\"" + this.name + "\"");
         if (this.persistenceModifier != null && !StringUtils.isWhitespace(this.persistenceModifier.toString())) {
            sb.append("\n").append(prefix).append("       persistence-modifier=\"" + this.persistenceModifier + "\"");
         }

         if (!StringUtils.isWhitespace(this.table)) {
            sb.append("\n").append(prefix).append("       table=\"" + this.table + "\"");
         }

         if (this.primaryKey != null && this.primaryKey) {
            sb.append("\n").append(prefix).append("       primary-key=\"" + this.primaryKey + "\"");
         }

         sb.append("\n").append(prefix).append("       null-value=\"" + this.nullValue + "\"");
         if (this.defaultFetchGroup != null && !StringUtils.isWhitespace(this.defaultFetchGroup.toString())) {
            sb.append("\n").append(prefix).append("       default-fetch-group=\"" + this.defaultFetchGroup + "\"");
         }

         if (this.embedded != null && !StringUtils.isWhitespace(this.embedded.toString())) {
            sb.append("\n").append(prefix).append("       embedded=\"" + this.embedded + "\"");
         }

         if (this.serialized != null && !StringUtils.isWhitespace(this.serialized.toString())) {
            sb.append("\n").append(prefix).append("       serialized=\"" + this.serialized + "\"");
         }

         if (this.dependent != null) {
            sb.append("\n").append(prefix).append("       dependent=\"" + this.dependent + "\"");
         }

         if (this.mappedBy != null) {
            sb.append("\n").append(prefix).append("       mapped-by=\"" + this.mappedBy + "\"");
         }

         String[] fieldTypes = this.getFieldTypes();
         if (fieldTypes != null) {
            sb.append("\n").append(prefix).append("       field-type=\"");

            for(int i = 0; i < fieldTypes.length; ++i) {
               sb.append(fieldTypes[i]);
            }

            sb.append("\"");
         }

         if (!StringUtils.isWhitespace(this.loadFetchGroup)) {
            sb.append("\n").append(prefix).append("       load-fetch-group=\"" + this.loadFetchGroup + "\"");
         }

         if (this.recursionDepth != 1 && this.recursionDepth != 0) {
            sb.append("\n").append(prefix).append("       recursion-depth=\"" + this.recursionDepth + "\"");
         }

         if (this.valueStrategy != null) {
            sb.append("\n").append(prefix).append("       value-strategy=\"" + this.valueStrategy + "\"");
         }

         if (this.sequence != null) {
            sb.append("\n").append(prefix).append("       sequence=\"" + this.sequence + "\"");
         }

         if (this.fieldName != null) {
            sb.append("\n").append(prefix).append("       field-name=\"" + this.fieldName + "\"");
         }

         if (this.table != null) {
            sb.append("\n").append(prefix).append("       table=\"" + this.table + "\"");
         }

         sb.append(">\n");
         if (this.containerMetaData != null) {
            if (this.containerMetaData instanceof CollectionMetaData) {
               CollectionMetaData c = (CollectionMetaData)this.containerMetaData;
               sb.append(c.toString(prefix + indent, indent));
            } else if (this.containerMetaData instanceof ArrayMetaData) {
               ArrayMetaData c = (ArrayMetaData)this.containerMetaData;
               sb.append(c.toString(prefix + indent, indent));
            } else if (this.containerMetaData instanceof MapMetaData) {
               MapMetaData c = (MapMetaData)this.containerMetaData;
               sb.append(c.toString(prefix + indent, indent));
            }
         }

         if (this.columnMetaData != null) {
            for(int i = 0; i < this.columnMetaData.length; ++i) {
               sb.append(this.columnMetaData[i].toString(prefix + indent, indent));
            }
         }

         if (this.joinMetaData != null) {
            sb.append(this.joinMetaData.toString(prefix + indent, indent));
         }

         if (this.elementMetaData != null) {
            sb.append(this.elementMetaData.toString(prefix + indent, indent));
         }

         if (this.keyMetaData != null) {
            sb.append(this.keyMetaData.toString(prefix + indent, indent));
         }

         if (this.valueMetaData != null) {
            sb.append(this.valueMetaData.toString(prefix + indent, indent));
         }

         if (this.orderMetaData != null) {
            sb.append(this.orderMetaData.toString(prefix + indent, indent));
         }

         if (this.embeddedMetaData != null) {
            sb.append(this.embeddedMetaData.toString(prefix + indent, indent));
         }

         if (this.indexMetaData != null) {
            sb.append(this.indexMetaData.toString(prefix + indent, indent));
         }

         if (this.uniqueMetaData != null) {
            sb.append(this.uniqueMetaData.toString(prefix + indent, indent));
         }

         if (this.foreignKeyMetaData != null) {
            sb.append(this.foreignKeyMetaData.toString(prefix + indent, indent));
         }

         sb.append(super.toString(prefix + indent, indent));
         sb.append(prefix).append("</property>\n");
         return sb.toString();
      }
   }
}
