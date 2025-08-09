package org.apache.parquet.filter2.recordlevel;

import java.util.Objects;
import org.apache.parquet.column.Dictionary;
import org.apache.parquet.io.api.Binary;
import org.apache.parquet.io.api.PrimitiveConverter;

public class FilteringPrimitiveConverter extends PrimitiveConverter {
   private final PrimitiveConverter delegate;
   private final IncrementallyUpdatedFilterPredicate.ValueInspector[] valueInspectors;

   public FilteringPrimitiveConverter(PrimitiveConverter delegate, IncrementallyUpdatedFilterPredicate.ValueInspector[] valueInspectors) {
      this.delegate = (PrimitiveConverter)Objects.requireNonNull(delegate, "delegate cannot be null");
      this.valueInspectors = (IncrementallyUpdatedFilterPredicate.ValueInspector[])Objects.requireNonNull(valueInspectors, "valueInspectors cannot be null");
   }

   public boolean hasDictionarySupport() {
      return false;
   }

   public void setDictionary(Dictionary dictionary) {
      throw new UnsupportedOperationException("FilteringPrimitiveConverter doesn't have dictionary support");
   }

   public void addValueFromDictionary(int dictionaryId) {
      throw new UnsupportedOperationException("FilteringPrimitiveConverter doesn't have dictionary support");
   }

   public void addBinary(Binary value) {
      for(IncrementallyUpdatedFilterPredicate.ValueInspector valueInspector : this.valueInspectors) {
         valueInspector.update(value);
      }

      this.delegate.addBinary(value);
   }

   public void addBoolean(boolean value) {
      for(IncrementallyUpdatedFilterPredicate.ValueInspector valueInspector : this.valueInspectors) {
         valueInspector.update(value);
      }

      this.delegate.addBoolean(value);
   }

   public void addDouble(double value) {
      for(IncrementallyUpdatedFilterPredicate.ValueInspector valueInspector : this.valueInspectors) {
         valueInspector.update(value);
      }

      this.delegate.addDouble(value);
   }

   public void addFloat(float value) {
      for(IncrementallyUpdatedFilterPredicate.ValueInspector valueInspector : this.valueInspectors) {
         valueInspector.update(value);
      }

      this.delegate.addFloat(value);
   }

   public void addInt(int value) {
      for(IncrementallyUpdatedFilterPredicate.ValueInspector valueInspector : this.valueInspectors) {
         valueInspector.update(value);
      }

      this.delegate.addInt(value);
   }

   public void addLong(long value) {
      for(IncrementallyUpdatedFilterPredicate.ValueInspector valueInspector : this.valueInspectors) {
         valueInspector.update(value);
      }

      this.delegate.addLong(value);
   }
}
