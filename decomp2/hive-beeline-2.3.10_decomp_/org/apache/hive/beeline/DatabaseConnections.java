package org.apache.hive.beeline;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class DatabaseConnections {
   private final List connections = new ArrayList();
   private int index = -1;

   public DatabaseConnection current() {
      return this.index != -1 ? (DatabaseConnection)this.connections.get(this.index) : null;
   }

   public int size() {
      return this.connections.size();
   }

   public Iterator iterator() {
      return this.connections.iterator();
   }

   public void remove() {
      if (this.index != -1) {
         this.connections.remove(this.index);
      }

      while(this.index >= this.connections.size()) {
         --this.index;
      }

   }

   public void setConnection(DatabaseConnection connection) {
      if (this.connections.indexOf(connection) == -1) {
         this.connections.add(connection);
      }

      this.index = this.connections.indexOf(connection);
   }

   public int getIndex() {
      return this.index;
   }

   public boolean setIndex(int index) {
      if (index >= 0 && index < this.connections.size()) {
         this.index = index;
         return true;
      } else {
         return false;
      }
   }
}
