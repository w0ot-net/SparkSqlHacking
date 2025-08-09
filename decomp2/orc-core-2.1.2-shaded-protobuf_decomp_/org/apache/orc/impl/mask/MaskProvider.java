package org.apache.orc.impl.mask;

import org.apache.orc.DataMask;
import org.apache.orc.DataMaskDescription;
import org.apache.orc.TypeDescription;

public class MaskProvider implements DataMask.Provider {
   public DataMask build(DataMaskDescription description, TypeDescription schema, DataMask.MaskOverrides overrides) {
      String name = description.getName();
      if (name.equals(DataMask.Standard.NULLIFY.getName())) {
         return new NullifyMask();
      } else if (name.equals(DataMask.Standard.REDACT.getName())) {
         return (new RedactMaskFactory(description.getParameters())).build(schema, overrides);
      } else {
         return name.equals(DataMask.Standard.SHA256.getName()) ? (new SHA256MaskFactory()).build(schema, overrides) : null;
      }
   }
}
