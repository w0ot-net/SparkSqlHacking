package org.datanucleus.store.rdbms.mapping;

import java.sql.PreparedStatement;
import org.datanucleus.ExecutionContext;
import org.datanucleus.api.ApiAdapter;
import org.datanucleus.state.AppIdObjectIdFieldConsumer;
import org.datanucleus.store.fieldmanager.AbstractFieldManager;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.mapping.java.PersistableMapping;

public class AppIDObjectIdFieldManager extends AbstractFieldManager {
   private int[] params;
   private int nextParam;
   private ExecutionContext ec;
   private PreparedStatement statement;
   private JavaTypeMapping[] javaTypeMappings;
   private int mappingNum = 0;

   public AppIDObjectIdFieldManager(int[] param, ExecutionContext ec, PreparedStatement statement, JavaTypeMapping[] javaTypeMappings) {
      this.params = param;
      this.nextParam = 0;
      this.ec = ec;
      this.statement = statement;
      int numMappings = 0;

      for(int i = 0; i < javaTypeMappings.length; ++i) {
         if (javaTypeMappings[i] instanceof PersistableMapping) {
            numMappings += ((PersistableMapping)javaTypeMappings[i]).getJavaTypeMapping().length;
         } else {
            ++numMappings;
         }
      }

      this.javaTypeMappings = new JavaTypeMapping[numMappings];
      int mappingNum = 0;

      for(int i = 0; i < javaTypeMappings.length; ++i) {
         if (javaTypeMappings[i] instanceof PersistableMapping) {
            PersistableMapping m = (PersistableMapping)javaTypeMappings[i];
            JavaTypeMapping[] subMappings = m.getJavaTypeMapping();

            for(int j = 0; j < subMappings.length; ++j) {
               this.javaTypeMappings[mappingNum++] = subMappings[j];
            }
         } else {
            this.javaTypeMappings[mappingNum++] = javaTypeMappings[i];
         }
      }

   }

   private int[] getParamsForField(JavaTypeMapping mapping) {
      if (this.javaTypeMappings.length == 1) {
         return this.params;
      } else {
         int numCols = mapping.getNumberOfDatastoreMappings();
         int[] fieldParams = new int[numCols];

         for(int i = 0; i < numCols; ++i) {
            fieldParams[i] = this.params[this.nextParam++];
         }

         return fieldParams;
      }
   }

   public void storeBooleanField(int fieldNumber, boolean value) {
      JavaTypeMapping mapping = this.javaTypeMappings[this.mappingNum++];
      mapping.setBoolean(this.ec, this.statement, this.getParamsForField(mapping), value);
   }

   public void storeByteField(int fieldNumber, byte value) {
      JavaTypeMapping mapping = this.javaTypeMappings[this.mappingNum++];
      mapping.setByte(this.ec, this.statement, this.getParamsForField(mapping), value);
   }

   public void storeCharField(int fieldNumber, char value) {
      JavaTypeMapping mapping = this.javaTypeMappings[this.mappingNum++];
      mapping.setChar(this.ec, this.statement, this.getParamsForField(mapping), value);
   }

   public void storeDoubleField(int fieldNumber, double value) {
      JavaTypeMapping mapping = this.javaTypeMappings[this.mappingNum++];
      mapping.setDouble(this.ec, this.statement, this.getParamsForField(mapping), value);
   }

   public void storeFloatField(int fieldNumber, float value) {
      JavaTypeMapping mapping = this.javaTypeMappings[this.mappingNum++];
      mapping.setFloat(this.ec, this.statement, this.getParamsForField(mapping), value);
   }

   public void storeIntField(int fieldNumber, int value) {
      JavaTypeMapping mapping = this.javaTypeMappings[this.mappingNum++];
      mapping.setInt(this.ec, this.statement, this.getParamsForField(mapping), value);
   }

   public void storeLongField(int fieldNumber, long value) {
      JavaTypeMapping mapping = this.javaTypeMappings[this.mappingNum++];
      mapping.setLong(this.ec, this.statement, this.getParamsForField(mapping), value);
   }

   public void storeShortField(int fieldNumber, short value) {
      JavaTypeMapping mapping = this.javaTypeMappings[this.mappingNum++];
      mapping.setShort(this.ec, this.statement, this.getParamsForField(mapping), value);
   }

   public void storeStringField(int fieldNumber, String value) {
      JavaTypeMapping mapping = this.javaTypeMappings[this.mappingNum++];
      mapping.setString(this.ec, this.statement, this.getParamsForField(mapping), value);
   }

   public void storeObjectField(int fieldNumber, Object value) {
      ApiAdapter api = this.ec.getApiAdapter();
      if (api.isPersistable(value)) {
         api.copyKeyFieldsFromIdToObject(value, new AppIdObjectIdFieldConsumer(api, this), api.getIdForObject(value));
      } else {
         JavaTypeMapping mapping = this.javaTypeMappings[this.mappingNum++];
         mapping.setObject(this.ec, this.statement, this.getParamsForField(mapping), value);
      }

   }
}
