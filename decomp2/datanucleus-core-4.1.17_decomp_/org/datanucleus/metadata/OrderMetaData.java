package org.datanucleus.metadata;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.util.StringUtils;

public class OrderMetaData extends MetaData implements ColumnMetaDataContainer {
   private static final long serialVersionUID = 2673343183786417980L;
   protected String columnName = null;
   protected List columns = null;
   protected IndexMetaData indexMetaData;
   protected IndexedValue indexed = null;
   protected String mappedBy = null;
   protected String ordering = null;
   protected FieldOrder[] fieldOrders = null;

   public OrderMetaData(OrderMetaData omd) {
      super((MetaData)null, omd);
      this.indexed = omd.indexed;
      this.columnName = omd.columnName;
      if (omd.indexMetaData != null) {
         this.indexMetaData = omd.indexMetaData;
         this.indexMetaData.parent = this;
      }

      if (omd.columns != null) {
         for(ColumnMetaData colmd : omd.columns) {
            this.addColumn(colmd);
         }
      }

      this.mappedBy = omd.mappedBy;
      this.ordering = omd.ordering;
   }

   public OrderMetaData() {
   }

   public void initialise(ClassLoaderResolver clr, MetaDataManager mmgr) {
      if (this.hasExtension("list-ordering")) {
         String val = this.getValueForExtension("list-ordering");
         if (!StringUtils.isWhitespace(val)) {
            this.ordering = val;
         }
      }

      if (this.indexMetaData == null && this.columns != null && this.indexed != null && this.indexed != IndexedValue.FALSE) {
         this.indexMetaData = new IndexMetaData();
         this.indexMetaData.setUnique(this.indexed == IndexedValue.UNIQUE);
         this.indexMetaData.parent = this;

         for(ColumnMetaData colmd : this.columns) {
            this.indexMetaData.addColumn(colmd.getName());
         }
      }

      if (this.mappedBy != null) {
         AbstractMemberMetaData fmd = (AbstractMemberMetaData)this.parent;
         AbstractClassMetaData elementCmd = fmd.getCollection().element.classMetaData;
         if (elementCmd != null && !elementCmd.hasMember(this.mappedBy)) {
            throw new InvalidMemberMetaDataException("044137", new Object[]{fmd.getClassName(), fmd.getName(), elementCmd.getFullClassName(), this.mappedBy});
         }
      }

      this.setInitialised();
   }

   public final OrderMetaData setIndexed(IndexedValue val) {
      this.indexed = val;
      return this;
   }

   public final OrderMetaData setIndexMetaData(IndexMetaData indexMetaData) {
      this.indexMetaData = indexMetaData;
      this.indexMetaData.parent = this;
      return this;
   }

   public IndexMetaData newIndexMetaData() {
      IndexMetaData idxmd = new IndexMetaData();
      this.setIndexMetaData(idxmd);
      return idxmd;
   }

   public String getMappedBy() {
      return this.mappedBy;
   }

   public OrderMetaData setMappedBy(String mappedby) {
      this.mappedBy = StringUtils.isWhitespace(mappedby) ? null : mappedby;
      return this;
   }

   public void addColumn(ColumnMetaData colmd) {
      if (this.columns == null) {
         this.columns = new ArrayList();
      }

      this.columns.add(colmd);
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

   public final String getColumnName() {
      return this.columnName;
   }

   public OrderMetaData setColumnName(String column) {
      if (!StringUtils.isWhitespace(column)) {
         ColumnMetaData colmd = new ColumnMetaData();
         colmd.setName(column);
         colmd.parent = this;
         this.addColumn(colmd);
         this.columnName = column;
      } else {
         this.columnName = null;
      }

      return this;
   }

   public final IndexMetaData getIndexMetaData() {
      return this.indexMetaData;
   }

   public boolean isIndexedList() {
      return this.ordering == null;
   }

   public String getOrdering() {
      return this.ordering;
   }

   public OrderMetaData setOrdering(String ordering) {
      this.ordering = ordering;
      return this;
   }

   public FieldOrder[] getFieldOrders() {
      if (this.ordering != null && this.fieldOrders == null) {
         FieldOrder[] theOrders = null;
         AbstractMemberMetaData fmd = (AbstractMemberMetaData)this.parent;
         AbstractClassMetaData elementCmd = fmd.hasCollection() ? fmd.getCollection().element.classMetaData : fmd.getArray().element.classMetaData;
         if (elementCmd != null && this.ordering.equals("#PK")) {
            theOrders = new FieldOrder[elementCmd.getNoOfPrimaryKeyMembers()];
            String[] pkFieldNames = elementCmd.getPrimaryKeyMemberNames();
            int i = 0;

            for(int pkFieldNum = 0; pkFieldNum < theOrders.length; ++pkFieldNum) {
               theOrders[i++] = new FieldOrder(pkFieldNames[pkFieldNum]);
            }
         } else if (elementCmd != null) {
            StringTokenizer tokeniser = new StringTokenizer(this.ordering, ",");
            int num = tokeniser.countTokens();
            theOrders = new FieldOrder[num];

            for(int i = 0; tokeniser.hasMoreTokens(); ++i) {
               String nextToken = tokeniser.nextToken().trim();
               String fieldName = null;
               boolean forward = true;
               int spacePos = nextToken.indexOf(32);
               if (spacePos > 0) {
                  fieldName = nextToken.substring(0, spacePos);
                  String direction = nextToken.substring(spacePos + 1).trim();
                  if (direction.equalsIgnoreCase("DESC")) {
                     forward = false;
                  } else if (!direction.equalsIgnoreCase("ASC")) {
                     throw new InvalidMemberMetaDataException("044139", new Object[]{fmd.getClassName(), fmd.getName(), direction});
                  }
               } else {
                  fieldName = nextToken;
               }

               if (!elementCmd.hasMember(fieldName)) {
                  throw new InvalidMemberMetaDataException("044138", new Object[]{fmd.getClassName(), fmd.getName(), elementCmd.getFullClassName(), fieldName});
               }

               theOrders[i] = new FieldOrder(fieldName);
               if (!forward) {
                  theOrders[i].setBackward();
               }
            }
         } else {
            theOrders = new FieldOrder[0];
         }

         this.fieldOrders = theOrders;
      }

      return this.fieldOrders;
   }

   public String toString(String prefix, String indent) {
      StringBuilder sb = new StringBuilder();
      sb.append(prefix).append("<order");
      if (this.columnName != null) {
         sb.append(" column=\"" + this.columnName + "\"");
      }

      if (this.indexed != null) {
         sb.append(" indexed=\"" + this.indexed.toString() + "\"");
      }

      if (this.mappedBy != null) {
         sb.append(" mapped-by=\"" + this.mappedBy + "\"");
      }

      sb.append(">\n");
      if (this.columns != null) {
         for(int i = 0; i < this.columns.size(); ++i) {
            ColumnMetaData c = (ColumnMetaData)this.columns.get(i);
            sb.append(c.toString(prefix + indent, indent));
         }
      }

      if (this.indexMetaData != null) {
         sb.append(this.indexMetaData.toString(prefix + indent, indent));
      }

      sb.append(super.toString(prefix + indent, indent));
      sb.append(prefix).append("</order>\n");
      return sb.toString();
   }

   public static class FieldOrder implements Serializable {
      private static final long serialVersionUID = 338498690476594298L;
      String fieldName;
      boolean forward = true;

      public FieldOrder(String name) {
         this.fieldName = name;
      }

      public void setBackward() {
         this.forward = false;
      }

      public String getFieldName() {
         return this.fieldName;
      }

      public boolean isForward() {
         return this.forward;
      }

      public String toString() {
         return this.fieldName + " " + (this.forward ? "ASC" : "DESC");
      }
   }
}
