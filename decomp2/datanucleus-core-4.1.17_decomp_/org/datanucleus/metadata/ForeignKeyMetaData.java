package org.datanucleus.metadata;

import java.util.ArrayList;
import java.util.List;
import org.datanucleus.util.StringUtils;

public class ForeignKeyMetaData extends ConstraintMetaData {
   private static final long serialVersionUID = 3207934394330383432L;
   protected List columns = null;
   protected boolean unique = false;
   protected boolean deferred = false;
   protected ForeignKeyAction deleteAction;
   protected ForeignKeyAction updateAction;
   protected String fkDefinition = null;
   protected boolean fkDefinitionApplies = false;

   public ForeignKeyMetaData() {
   }

   public ForeignKeyMetaData(ForeignKeyMetaData fkmd) {
      super(fkmd);
      if (fkmd.columns != null) {
         for(ColumnMetaData colmd : fkmd.columns) {
            this.addColumn(new ColumnMetaData(colmd));
         }
      }

      this.unique = fkmd.unique;
      this.deferred = fkmd.deferred;
      this.deleteAction = fkmd.deleteAction;
      this.updateAction = fkmd.updateAction;
   }

   public void addColumn(ColumnMetaData colmd) {
      if (this.columns == null) {
         this.columns = new ArrayList();
      }

      this.columns.add(colmd);
      this.addColumn(colmd.getName());
      colmd.parent = this;
   }

   public ColumnMetaData newColumnMetaData() {
      ColumnMetaData colmd = new ColumnMetaData();
      this.addColumn(colmd);
      return colmd;
   }

   public final ColumnMetaData[] getColumnMetaData() {
      return this.columns == null ? null : (ColumnMetaData[])this.columns.toArray(new ColumnMetaData[this.columns.size()]);
   }

   public final boolean isDeferred() {
      return this.deferred;
   }

   public ForeignKeyMetaData setDeferred(boolean deferred) {
      this.deferred = deferred;
      return this;
   }

   public ForeignKeyMetaData setDeferred(String deferred) {
      if (!StringUtils.isWhitespace(deferred)) {
         this.deferred = Boolean.parseBoolean(deferred);
      }

      return this;
   }

   public final ForeignKeyAction getDeleteAction() {
      return this.deleteAction;
   }

   public void setDeleteAction(ForeignKeyAction deleteAction) {
      this.deleteAction = deleteAction;
   }

   public final boolean isUnique() {
      return this.unique;
   }

   public ForeignKeyMetaData setUnique(boolean unique) {
      this.unique = unique;
      return this;
   }

   public ForeignKeyMetaData setUnique(String unique) {
      if (!StringUtils.isWhitespace(unique)) {
         this.deferred = Boolean.parseBoolean(unique);
      }

      return this;
   }

   public final ForeignKeyAction getUpdateAction() {
      return this.updateAction;
   }

   public ForeignKeyMetaData setUpdateAction(ForeignKeyAction updateAction) {
      this.updateAction = updateAction;
      return this;
   }

   public void setFkDefinition(String def) {
      if (!StringUtils.isWhitespace(def)) {
         this.fkDefinition = def;
         this.fkDefinitionApplies = true;
         this.updateAction = null;
         this.deleteAction = null;
      }
   }

   public String getFkDefinition() {
      return this.fkDefinition;
   }

   public void setFkDefinitionApplies(boolean flag) {
      this.fkDefinitionApplies = flag;
   }

   public boolean getFkDefinitionApplies() {
      return this.fkDefinitionApplies;
   }

   public String toString(String prefix, String indent) {
      if (!StringUtils.isWhitespace(this.fkDefinition)) {
         return "<foreign-key name=\"" + this.name + "\" definition=\"" + this.fkDefinition + "\" definition-applies=" + this.fkDefinitionApplies + "/>";
      } else {
         StringBuilder sb = new StringBuilder();
         sb.append(prefix).append("<foreign-key deferred=\"" + this.deferred + "\"\n");
         sb.append(prefix).append("       unique=\"" + this.unique + "\"");
         if (this.updateAction != null) {
            sb.append("\n").append(prefix).append("       update-action=\"" + this.updateAction + "\"");
         }

         if (this.deleteAction != null) {
            sb.append("\n").append(prefix).append("       delete-action=\"" + this.deleteAction + "\"");
         }

         if (this.table != null) {
            sb.append("\n").append(prefix).append("       table=\"" + this.table + "\"");
         }

         if (this.name != null) {
            sb.append("\n").append(prefix).append("       name=\"" + this.name + "\"");
         }

         sb.append(">\n");
         if (this.memberNames != null) {
            for(String memberName : this.memberNames) {
               sb.append(prefix).append(indent).append("<field name=\"" + memberName + "\"/>");
            }
         }

         if (this.columns != null) {
            for(ColumnMetaData colmd : this.columns) {
               sb.append(colmd.toString(prefix + indent, indent));
            }
         }

         sb.append(super.toString(prefix + indent, indent));
         sb.append(prefix).append("</foreign-key>\n");
         return sb.toString();
      }
   }
}
