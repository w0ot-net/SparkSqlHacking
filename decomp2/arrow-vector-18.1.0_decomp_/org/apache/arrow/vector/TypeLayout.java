package org.apache.arrow.vector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.apache.arrow.util.Preconditions;
import org.apache.arrow.vector.types.pojo.ArrowType;

public class TypeLayout {
   private final List bufferLayouts;
   private final boolean isFixedBufferCount;

   public static TypeLayout getTypeLayout(ArrowType arrowType) {
      TypeLayout layout = (TypeLayout)arrowType.accept(new ArrowType.ArrowTypeVisitor() {
         public TypeLayout visit(ArrowType.Int type) {
            return this.newFixedWidthTypeLayout(BufferLayout.dataBuffer(type.getBitWidth()));
         }

         public TypeLayout visit(ArrowType.Union type) {
            List<BufferLayout> vectors;
            switch (type.getMode()) {
               case Dense:
                  vectors = Arrays.asList(BufferLayout.typeBuffer(), BufferLayout.offsetBuffer());
                  break;
               case Sparse:
                  vectors = Arrays.asList(BufferLayout.typeBuffer());
                  break;
               default:
                  throw new UnsupportedOperationException("Unsupported Union Mode: " + String.valueOf(type.getMode()));
            }

            return new TypeLayout(vectors);
         }

         public TypeLayout visit(ArrowType.Struct type) {
            List<BufferLayout> vectors = Arrays.asList(BufferLayout.validityVector());
            return new TypeLayout(vectors);
         }

         public TypeLayout visit(ArrowType.Timestamp type) {
            return this.newFixedWidthTypeLayout(BufferLayout.dataBuffer(64));
         }

         public TypeLayout visit(ArrowType.List type) {
            List<BufferLayout> vectors = Arrays.asList(BufferLayout.validityVector(), BufferLayout.offsetBuffer());
            return new TypeLayout(vectors);
         }

         public TypeLayout visit(ArrowType.ListView type) {
            List<BufferLayout> vectors = Arrays.asList(BufferLayout.validityVector(), BufferLayout.offsetBuffer(), BufferLayout.sizeBuffer());
            return new TypeLayout(vectors);
         }

         public TypeLayout visit(ArrowType.LargeListView type) {
            List<BufferLayout> vectors = Arrays.asList(BufferLayout.validityVector(), BufferLayout.largeOffsetBuffer(), BufferLayout.largeSizeBuffer());
            return new TypeLayout(vectors);
         }

         public TypeLayout visit(ArrowType.LargeList type) {
            List<BufferLayout> vectors = Arrays.asList(BufferLayout.validityVector(), BufferLayout.largeOffsetBuffer());
            return new TypeLayout(vectors);
         }

         public TypeLayout visit(ArrowType.FixedSizeList type) {
            List<BufferLayout> vectors = Arrays.asList(BufferLayout.validityVector());
            return new TypeLayout(vectors);
         }

         public TypeLayout visit(ArrowType.Map type) {
            List<BufferLayout> vectors = Arrays.asList(BufferLayout.validityVector(), BufferLayout.offsetBuffer());
            return new TypeLayout(vectors);
         }

         public TypeLayout visit(ArrowType.FloatingPoint type) {
            int bitWidth;
            switch (type.getPrecision()) {
               case HALF:
                  bitWidth = 16;
                  break;
               case SINGLE:
                  bitWidth = 32;
                  break;
               case DOUBLE:
                  bitWidth = 64;
                  break;
               default:
                  throw new UnsupportedOperationException("Unsupported Precision: " + String.valueOf(type.getPrecision()));
            }

            return this.newFixedWidthTypeLayout(BufferLayout.dataBuffer(bitWidth));
         }

         public TypeLayout visit(ArrowType.Decimal type) {
            return this.newFixedWidthTypeLayout(BufferLayout.dataBuffer(type.getBitWidth()));
         }

         public TypeLayout visit(ArrowType.FixedSizeBinary type) {
            return this.newFixedWidthTypeLayout(new BufferLayout(BufferLayout.BufferType.DATA, type.getByteWidth() * 8));
         }

         public TypeLayout visit(ArrowType.Bool type) {
            return this.newFixedWidthTypeLayout(BufferLayout.booleanVector());
         }

         public TypeLayout visit(ArrowType.Binary type) {
            return this.newVariableWidthTypeLayout();
         }

         public TypeLayout visit(ArrowType.BinaryView type) {
            return this.newVariableWidthViewTypeLayout();
         }

         public TypeLayout visit(ArrowType.Utf8 type) {
            return this.newVariableWidthTypeLayout();
         }

         public TypeLayout visit(ArrowType.Utf8View type) {
            return this.newVariableWidthViewTypeLayout();
         }

         public TypeLayout visit(ArrowType.LargeUtf8 type) {
            return this.newLargeVariableWidthTypeLayout();
         }

         public TypeLayout visit(ArrowType.LargeBinary type) {
            return this.newLargeVariableWidthTypeLayout();
         }

         private TypeLayout newVariableWidthTypeLayout() {
            return this.newPrimitiveTypeLayout(BufferLayout.validityVector(), BufferLayout.offsetBuffer(), BufferLayout.byteVector());
         }

         private TypeLayout newVariableWidthViewTypeLayout() {
            return new TypeLayout(false, new BufferLayout[]{BufferLayout.validityVector(), BufferLayout.viewVector()});
         }

         private TypeLayout newLargeVariableWidthTypeLayout() {
            return this.newPrimitiveTypeLayout(BufferLayout.validityVector(), BufferLayout.largeOffsetBuffer(), BufferLayout.byteVector());
         }

         private TypeLayout newPrimitiveTypeLayout(BufferLayout... vectors) {
            return new TypeLayout(Arrays.asList(vectors));
         }

         public TypeLayout newFixedWidthTypeLayout(BufferLayout dataVector) {
            return this.newPrimitiveTypeLayout(BufferLayout.validityVector(), dataVector);
         }

         public TypeLayout visit(ArrowType.Null type) {
            return new TypeLayout(Collections.emptyList());
         }

         public TypeLayout visit(ArrowType.Date type) {
            switch (type.getUnit()) {
               case DAY:
                  return this.newFixedWidthTypeLayout(BufferLayout.dataBuffer(32));
               case MILLISECOND:
                  return this.newFixedWidthTypeLayout(BufferLayout.dataBuffer(64));
               default:
                  throw new UnsupportedOperationException("Unknown unit " + String.valueOf(type.getUnit()));
            }
         }

         public TypeLayout visit(ArrowType.Time type) {
            return this.newFixedWidthTypeLayout(BufferLayout.dataBuffer(type.getBitWidth()));
         }

         public TypeLayout visit(ArrowType.Interval type) {
            switch (type.getUnit()) {
               case DAY_TIME:
                  return this.newFixedWidthTypeLayout(BufferLayout.dataBuffer(64));
               case YEAR_MONTH:
                  return this.newFixedWidthTypeLayout(BufferLayout.dataBuffer(32));
               case MONTH_DAY_NANO:
                  return this.newFixedWidthTypeLayout(BufferLayout.dataBuffer(128));
               default:
                  throw new UnsupportedOperationException("Unknown unit " + String.valueOf(type.getUnit()));
            }
         }

         public TypeLayout visit(ArrowType.Duration type) {
            return this.newFixedWidthTypeLayout(BufferLayout.dataBuffer(64));
         }

         public TypeLayout visit(ArrowType.RunEndEncoded type) {
            return new TypeLayout(Collections.emptyList());
         }
      });
      return layout;
   }

   public static int getTypeBufferCount(ArrowType arrowType) {
      return (Integer)arrowType.accept(new ArrowType.ArrowTypeVisitor() {
         static final int FIXED_WIDTH_BUFFER_COUNT = 2;
         static final int VARIABLE_WIDTH_BUFFER_COUNT = 3;

         public Integer visit(ArrowType.Int type) {
            return 2;
         }

         public Integer visit(ArrowType.Union type) {
            switch (type.getMode()) {
               case Dense:
                  return 2;
               case Sparse:
                  return 1;
               default:
                  throw new UnsupportedOperationException("Unsupported Union Mode: " + String.valueOf(type.getMode()));
            }
         }

         public Integer visit(ArrowType.Struct type) {
            return 1;
         }

         public Integer visit(ArrowType.Timestamp type) {
            return 2;
         }

         public Integer visit(ArrowType.List type) {
            return 2;
         }

         public Integer visit(ArrowType.ListView type) {
            return 3;
         }

         public Integer visit(ArrowType.LargeList type) {
            return 2;
         }

         public Integer visit(ArrowType.LargeListView type) {
            return 3;
         }

         public Integer visit(ArrowType.FixedSizeList type) {
            return 1;
         }

         public Integer visit(ArrowType.Map type) {
            return 2;
         }

         public Integer visit(ArrowType.FloatingPoint type) {
            return 2;
         }

         public Integer visit(ArrowType.Decimal type) {
            return 2;
         }

         public Integer visit(ArrowType.FixedSizeBinary type) {
            return 2;
         }

         public Integer visit(ArrowType.Bool type) {
            return 2;
         }

         public Integer visit(ArrowType.Binary type) {
            return 3;
         }

         public Integer visit(ArrowType.BinaryView type) {
            return 2;
         }

         public Integer visit(ArrowType.Utf8 type) {
            return 3;
         }

         public Integer visit(ArrowType.Utf8View type) {
            return 2;
         }

         public Integer visit(ArrowType.LargeUtf8 type) {
            return 3;
         }

         public Integer visit(ArrowType.LargeBinary type) {
            return 3;
         }

         public Integer visit(ArrowType.Null type) {
            return 0;
         }

         public Integer visit(ArrowType.Date type) {
            return 2;
         }

         public Integer visit(ArrowType.Time type) {
            return 2;
         }

         public Integer visit(ArrowType.Interval type) {
            return 2;
         }

         public Integer visit(ArrowType.Duration type) {
            return 2;
         }

         public Integer visit(ArrowType.RunEndEncoded type) {
            return 0;
         }
      });
   }

   public TypeLayout(List bufferLayouts, boolean isFixedBufferCount) {
      this.bufferLayouts = (List)Preconditions.checkNotNull(bufferLayouts);
      this.isFixedBufferCount = isFixedBufferCount;
   }

   public TypeLayout(List bufferLayouts) {
      this(bufferLayouts, true);
   }

   public TypeLayout(BufferLayout... bufferLayouts) {
      this(Arrays.asList(bufferLayouts), true);
   }

   public TypeLayout(boolean isFixedBufferCount, BufferLayout... bufferLayouts) {
      this(Arrays.asList(bufferLayouts), isFixedBufferCount);
   }

   public List getBufferLayouts() {
      return this.bufferLayouts;
   }

   public List getBufferTypes() {
      List<BufferLayout.BufferType> types = new ArrayList(this.bufferLayouts.size());

      for(BufferLayout vector : this.bufferLayouts) {
         types.add(vector.getType());
      }

      return types;
   }

   public boolean isFixedBufferCount() {
      return this.isFixedBufferCount;
   }

   public String toString() {
      return this.bufferLayouts.toString();
   }

   public int hashCode() {
      return this.bufferLayouts.hashCode();
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (obj == null) {
         return false;
      } else if (!(obj instanceof TypeLayout)) {
         return false;
      } else {
         TypeLayout other = (TypeLayout)obj;
         return this.bufferLayouts.equals(other.bufferLayouts);
      }
   }
}
