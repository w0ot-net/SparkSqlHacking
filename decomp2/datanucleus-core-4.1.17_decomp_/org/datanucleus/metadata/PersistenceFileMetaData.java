package org.datanucleus.metadata;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class PersistenceFileMetaData extends MetaData {
   private static final long serialVersionUID = -5448349113062382507L;
   protected String filename = null;
   protected Set persistenceUnits = new HashSet();

   public PersistenceFileMetaData(String filename) {
      this.filename = filename;
   }

   public String getFilename() {
      return this.filename;
   }

   public int getNoOfPersistenceUnits() {
      return this.persistenceUnits.size();
   }

   public PersistenceUnitMetaData getPersistenceUnit(String name) {
      for(PersistenceUnitMetaData p : this.persistenceUnits) {
         if (p.name.equals(name)) {
            return p;
         }
      }

      return null;
   }

   public PersistenceUnitMetaData[] getPersistenceUnits() {
      return this.persistenceUnits.isEmpty() ? null : (PersistenceUnitMetaData[])this.persistenceUnits.toArray(new PersistenceUnitMetaData[this.persistenceUnits.size()]);
   }

   public void setFilename(String filename) {
      this.filename = filename;
   }

   public void addPersistenceUnit(PersistenceUnitMetaData pumd) {
      if (pumd != null) {
         pumd.parent = this;

         for(PersistenceUnitMetaData p : this.persistenceUnits) {
            if (pumd.getName().equals(p.getName())) {
               return;
            }
         }

         this.persistenceUnits.add(pumd);
      }
   }

   public String toString(String indent) {
      if (indent == null) {
         indent = "";
      }

      StringBuilder sb = new StringBuilder();
      sb.append("<persistence>\n");
      Iterator<PersistenceUnitMetaData> iter = this.persistenceUnits.iterator();

      while(iter.hasNext()) {
         sb.append(((PersistenceUnitMetaData)iter.next()).toString(indent, indent));
      }

      sb.append("</persistence>");
      return sb.toString();
   }
}
