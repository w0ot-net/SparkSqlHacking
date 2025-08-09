package shaded.parquet.com.fasterxml.jackson.databind.ser;

import shaded.parquet.com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;

public abstract class FilterProvider {
   /** @deprecated */
   @Deprecated
   public abstract BeanPropertyFilter findFilter(Object var1);

   public PropertyFilter findPropertyFilter(Object filterId, Object valueToFilter) {
      BeanPropertyFilter old = this.findFilter(filterId);
      return old == null ? null : SimpleBeanPropertyFilter.from(old);
   }
}
