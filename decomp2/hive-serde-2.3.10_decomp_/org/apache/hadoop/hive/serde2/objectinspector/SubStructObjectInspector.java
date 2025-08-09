package org.apache.hadoop.hive.serde2.objectinspector;

import java.util.ArrayList;
import java.util.List;

public class SubStructObjectInspector extends StructObjectInspector {
   protected StructObjectInspector baseOI;
   protected int startCol;
   protected int numCols;
   protected List fields;

   public SubStructObjectInspector(StructObjectInspector baseOI, int startCol, int numCols) {
      this.baseOI = baseOI;
      this.startCol = startCol;
      this.numCols = numCols;
      List<? extends StructField> baseFields = baseOI.getAllStructFieldRefs();

      assert startCol < baseFields.size() && startCol + numCols < baseFields.size();

      this.fields = new ArrayList(numCols);
      this.fields.addAll(baseOI.getAllStructFieldRefs().subList(startCol, startCol + numCols));
   }

   public String getTypeName() {
      return ObjectInspectorUtils.getStandardStructTypeName(this);
   }

   public ObjectInspector.Category getCategory() {
      return ObjectInspector.Category.STRUCT;
   }

   public List getAllStructFieldRefs() {
      return this.fields;
   }

   public StructField getStructFieldRef(String fieldName) {
      return ObjectInspectorUtils.getStandardStructFieldRef(fieldName, this.fields);
   }

   public Object getStructFieldData(Object data, StructField fieldRef) {
      return this.baseOI.getStructFieldData(data, fieldRef);
   }

   public List getStructFieldsDataAsList(Object data) {
      return this.baseOI.getStructFieldsDataAsList(data).subList(this.startCol, this.startCol + this.numCols);
   }
}
