package org.datanucleus.store.rdbms.mapping.java;

import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.store.rdbms.mapping.datastore.DatastoreMapping;

public abstract class MultiMapping extends JavaTypeMapping {
   protected JavaTypeMapping[] javaTypeMappings = new JavaTypeMapping[0];
   protected int numberOfDatastoreMappings = 0;

   public void addJavaTypeMapping(JavaTypeMapping mapping) {
      JavaTypeMapping[] jtm = this.javaTypeMappings;
      this.javaTypeMappings = new JavaTypeMapping[jtm.length + 1];
      System.arraycopy(jtm, 0, this.javaTypeMappings, 0, jtm.length);
      this.javaTypeMappings[jtm.length] = mapping;
   }

   public JavaTypeMapping[] getJavaTypeMapping() {
      return this.javaTypeMappings;
   }

   public int getNumberOfDatastoreMappings() {
      if (this.numberOfDatastoreMappings == 0) {
         int numDatastoreTmp = 0;

         for(int i = 0; i < this.javaTypeMappings.length; ++i) {
            numDatastoreTmp += this.javaTypeMappings[i].getNumberOfDatastoreMappings();
         }

         this.numberOfDatastoreMappings = numDatastoreTmp;
      }

      return this.numberOfDatastoreMappings;
   }

   public DatastoreMapping[] getDatastoreMappings() {
      if (this.datastoreMappings.length == 0) {
         DatastoreMapping[] colMappings = new DatastoreMapping[this.getNumberOfDatastoreMappings()];
         int num = 0;

         for(int i = 0; i < this.javaTypeMappings.length; ++i) {
            for(int j = 0; j < this.javaTypeMappings[i].getNumberOfDatastoreMappings(); ++j) {
               colMappings[num++] = this.javaTypeMappings[i].getDatastoreMapping(j);
            }
         }

         this.datastoreMappings = colMappings;
      }

      return super.getDatastoreMappings();
   }

   public DatastoreMapping getDatastoreMapping(int index) {
      if (index >= this.getNumberOfDatastoreMappings()) {
         throw (new NucleusException("Attempt to get DatastoreMapping with index " + index + " when total number of mappings is " + this.numberOfDatastoreMappings + " for field=" + this.mmd)).setFatal();
      } else {
         int currentIndex = 0;
         int numberJavaMappings = this.javaTypeMappings.length;

         for(int i = 0; i < numberJavaMappings; ++i) {
            int numberDatastoreMappings = this.javaTypeMappings[i].getNumberOfDatastoreMappings();

            for(int j = 0; j < numberDatastoreMappings; ++j) {
               if (currentIndex == index) {
                  return this.javaTypeMappings[i].getDatastoreMapping(j);
               }

               ++currentIndex;
            }
         }

         throw (new NucleusException("Invalid index " + index + " for DatastoreMapping (numColumns=" + this.getNumberOfDatastoreMappings() + "), for field=" + this.mmd)).setFatal();
      }
   }
}
