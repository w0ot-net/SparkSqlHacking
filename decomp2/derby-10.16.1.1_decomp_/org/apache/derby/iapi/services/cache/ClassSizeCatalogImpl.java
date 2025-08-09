package org.apache.derby.iapi.services.cache;

class ClassSizeCatalogImpl extends ClassSizeCatalog {
   public ClassSizeCatalogImpl() {
      this.put("java.util.Vector", new int[]{12, 3});
      this.put("java.util.ArrayList", new int[]{8, 3});
      this.put("java.util.GregorianCalendar", new int[]{76, 11});
      this.put("org.apache.derby.iapi.store.raw.ContainerKey", new int[]{16, 2});
      this.put("org.apache.derby.impl.store.raw.data.RecordId", new int[]{8, 3});
      this.put("java.math.BigDecimal", new int[]{16, 4});
      this.put("java.lang.ref.WeakReference", new int[]{0, 6});
   }
}
