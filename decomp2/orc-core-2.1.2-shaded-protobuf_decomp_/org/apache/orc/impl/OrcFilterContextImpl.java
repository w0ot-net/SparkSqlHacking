package org.apache.orc.impl;

import java.util.HashMap;
import java.util.Map;
import org.apache.hadoop.hive.ql.exec.vector.ColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.VectorizedRowBatch;
import org.apache.orc.OrcFilterContext;
import org.apache.orc.TypeDescription;
import org.jetbrains.annotations.NotNull;

public class OrcFilterContextImpl implements OrcFilterContext {
   private VectorizedRowBatch batch = null;
   private final Map vectors;
   private final TypeDescription readSchema;
   private final boolean isSchemaCaseAware;

   public OrcFilterContextImpl(TypeDescription readSchema, boolean isSchemaCaseAware) {
      this.readSchema = readSchema;
      this.isSchemaCaseAware = isSchemaCaseAware;
      this.vectors = new HashMap();
   }

   public OrcFilterContext setBatch(@NotNull VectorizedRowBatch batch) {
      if (batch != this.batch) {
         this.batch = batch;
         this.vectors.clear();
      }

      return this;
   }

   VectorizedRowBatch getBatch() {
      return this.batch;
   }

   public void setFilterContext(boolean selectedInUse, int[] selected, int selectedSize) {
      this.batch.setFilterContext(selectedInUse, selected, selectedSize);
   }

   public boolean validateSelected() {
      return this.batch.validateSelected();
   }

   public int[] updateSelected(int i) {
      return this.batch.updateSelected(i);
   }

   public void setSelectedInUse(boolean b) {
      this.batch.setSelectedInUse(b);
   }

   public void setSelected(int[] ints) {
      this.batch.setSelected(ints);
   }

   public void setSelectedSize(int i) {
      this.batch.setSelectedSize(i);
   }

   public void reset() {
      this.batch.reset();
   }

   public boolean isSelectedInUse() {
      return this.batch.isSelectedInUse();
   }

   public int[] getSelected() {
      return this.batch.getSelected();
   }

   public int getSelectedSize() {
      return this.batch.getSelectedSize();
   }

   public ColumnVector[] getCols() {
      return this.batch.cols;
   }

   public ColumnVector[] findColumnVector(String name) {
      return (ColumnVector[])this.vectors.computeIfAbsent(name, (key) -> ParserUtils.findColumnVectors(this.readSchema, new ParserUtils.StringPosition(key), this.isSchemaCaseAware, this.batch));
   }
}
