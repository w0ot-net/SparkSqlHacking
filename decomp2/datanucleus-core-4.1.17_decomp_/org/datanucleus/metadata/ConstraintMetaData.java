package org.datanucleus.metadata;

import java.util.ArrayList;
import java.util.List;
import org.datanucleus.util.StringUtils;

public class ConstraintMetaData extends MetaData {
   private static final long serialVersionUID = 7230726771198108950L;
   protected String name;
   protected String table;
   protected List memberNames = null;
   protected List columnNames = null;

   public ConstraintMetaData() {
   }

   public ConstraintMetaData(ConstraintMetaData acmd) {
      super((MetaData)null, acmd);
      this.name = acmd.name;
      this.table = acmd.table;
      if (acmd.memberNames != null) {
         for(String memberName : acmd.memberNames) {
            this.addMember(memberName);
         }
      }

      if (acmd.columnNames != null) {
         for(String columnName : acmd.columnNames) {
            this.addColumn(columnName);
         }
      }

   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = StringUtils.isWhitespace(name) ? null : name;
   }

   public String getTable() {
      return this.table;
   }

   public void setTable(String table) {
      this.table = StringUtils.isWhitespace(table) ? null : table;
   }

   public void addMember(String memberName) {
      if (this.memberNames == null) {
         this.memberNames = new ArrayList();
      }

      this.memberNames.add(memberName);
   }

   public final String[] getMemberNames() {
      return this.memberNames == null ? null : (String[])this.memberNames.toArray(new String[this.memberNames.size()]);
   }

   public int getNumberOfMembers() {
      return this.memberNames != null ? this.memberNames.size() : 0;
   }

   public void addColumn(String columnName) {
      if (this.columnNames == null) {
         this.columnNames = new ArrayList();
      }

      this.columnNames.add(columnName);
   }

   public final String[] getColumnNames() {
      return this.columnNames == null ? null : (String[])this.columnNames.toArray(new String[this.columnNames.size()]);
   }

   public int getNumberOfColumns() {
      return this.columnNames != null ? this.columnNames.size() : 0;
   }
}
