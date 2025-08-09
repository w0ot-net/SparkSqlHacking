package org.apache.orc.impl.mask;

import java.util.List;
import org.apache.orc.DataMask;
import org.apache.orc.DataMaskDescription;
import org.apache.orc.TypeDescription;

public abstract class MaskFactory {
   protected abstract DataMask buildBooleanMask(TypeDescription var1);

   protected abstract DataMask buildLongMask(TypeDescription var1);

   protected abstract DataMask buildDecimalMask(TypeDescription var1);

   protected abstract DataMask buildDoubleMask(TypeDescription var1);

   protected abstract DataMask buildStringMask(TypeDescription var1);

   protected abstract DataMask buildDateMask(TypeDescription var1);

   protected abstract DataMask buildTimestampMask(TypeDescription var1);

   protected abstract DataMask buildBinaryMask(TypeDescription var1);

   public DataMask build(TypeDescription schema, DataMask.MaskOverrides overrides) {
      switch (schema.getCategory()) {
         case BOOLEAN:
            return this.buildBooleanMask(schema);
         case BYTE:
         case SHORT:
         case INT:
         case LONG:
            return this.buildLongMask(schema);
         case FLOAT:
         case DOUBLE:
            return this.buildDoubleMask(schema);
         case DECIMAL:
            return this.buildDecimalMask(schema);
         case STRING:
         case CHAR:
         case VARCHAR:
            return this.buildStringMask(schema);
         case TIMESTAMP:
         case TIMESTAMP_INSTANT:
            return this.buildTimestampMask(schema);
         case DATE:
            return this.buildDateMask(schema);
         case BINARY:
            return this.buildBinaryMask(schema);
         case UNION:
            return this.buildUnionMask(schema, overrides);
         case STRUCT:
            return this.buildStructMask(schema, overrides);
         case LIST:
            return this.buildListMask(schema, overrides);
         case MAP:
            return this.buildMapMask(schema, overrides);
         default:
            throw new IllegalArgumentException("Unhandled type " + String.valueOf(schema));
      }
   }

   protected DataMask[] buildChildren(List children, DataMask.MaskOverrides overrides) {
      DataMask[] result = new DataMask[children.size()];

      for(int i = 0; i < result.length; ++i) {
         TypeDescription child = (TypeDescription)children.get(i);
         DataMaskDescription over = overrides.hasOverride(child);
         if (over != null) {
            result[i] = DataMask.Factory.build(over, child, overrides);
         } else {
            result[i] = this.build(child, overrides);
         }
      }

      return result;
   }

   protected DataMask buildStructMask(TypeDescription schema, DataMask.MaskOverrides overrides) {
      return new StructIdentity(this.buildChildren(schema.getChildren(), overrides));
   }

   DataMask buildListMask(TypeDescription schema, DataMask.MaskOverrides overrides) {
      return new ListIdentity(this.buildChildren(schema.getChildren(), overrides));
   }

   DataMask buildMapMask(TypeDescription schema, DataMask.MaskOverrides overrides) {
      return new MapIdentity(this.buildChildren(schema.getChildren(), overrides));
   }

   DataMask buildUnionMask(TypeDescription schema, DataMask.MaskOverrides overrides) {
      return new UnionIdentity(this.buildChildren(schema.getChildren(), overrides));
   }
}
