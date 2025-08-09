package org.datanucleus.store.rdbms.key;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.store.rdbms.table.Column;
import org.datanucleus.store.rdbms.table.Table;

abstract class Key {
   protected String name;
   protected Table table;
   protected List columns = new ArrayList();

   protected Key(Table table) {
      this.table = table;
   }

   public String getName() {
      return this.name;
   }

   public Table getTable() {
      return this.table;
   }

   public List getColumns() {
      return Collections.unmodifiableList(this.columns);
   }

   public String getColumnList() {
      return getColumnList(this.columns);
   }

   public void addColumn(Column col) {
      this.assertSameDatastoreObject(col);
      this.columns.add(col);
   }

   public boolean startsWith(Key key) {
      int kSize = key.columns.size();
      return kSize <= this.columns.size() && key.columns.equals(this.columns.subList(0, kSize));
   }

   public void setName(String name) {
      this.name = name;
   }

   protected void assertSameDatastoreObject(Column col) {
      if (!this.table.equals(col.getTable())) {
         throw (new NucleusException("Cannot add " + col + " as key column for " + this.table)).setFatal();
      }
   }

   public int hashCode() {
      return this.columns.hashCode();
   }

   public boolean equals(Object obj) {
      if (obj == this) {
         return true;
      } else if (!(obj instanceof Key)) {
         return false;
      } else {
         Key key = (Key)obj;
         return this.columns.containsAll(key.columns) && this.columns.size() == key.columns.size();
      }
   }

   protected static void setMinSize(List list, int size) {
      while(list.size() < size) {
         list.add((Object)null);
      }

   }

   public static String getColumnList(Collection cols) {
      StringBuilder s = new StringBuilder("(");
      Iterator i = cols.iterator();

      while(i.hasNext()) {
         Column col = (Column)i.next();
         if (col == null) {
            s.append('?');
         } else {
            s.append(col.getIdentifier());
         }

         if (i.hasNext()) {
            s.append(',');
         }
      }

      s.append(')');
      return s.toString();
   }
}
