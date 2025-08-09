package org.apache.datasketches.tuple;

import org.apache.datasketches.common.Family;
import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.memory.Memory;

public final class SerializerDeserializer {
   static final int TYPE_BYTE_OFFSET = 3;

   public static void validateFamily(byte familyId, byte preambleLongs) {
      Family family = Family.idToFamily(familyId);
      if (family.equals(Family.TUPLE)) {
         if (preambleLongs < Family.TUPLE.getMinPreLongs() || preambleLongs > Family.TUPLE.getMaxPreLongs()) {
            throw new SketchesArgumentException("Possible corruption: Invalid PreambleLongs value for family TUPLE: " + preambleLongs);
         }
      } else {
         throw new SketchesArgumentException("Possible corruption: Invalid Family: " + family.toString());
      }
   }

   public static void validateType(byte sketchTypeByte, SketchType expectedType) {
      SketchType sketchType = getSketchType(sketchTypeByte);
      if (!sketchType.equals(expectedType)) {
         throw new SketchesArgumentException("Sketch Type mismatch. Expected " + expectedType.name() + ", got " + sketchType.name());
      }
   }

   public static SketchType getSketchType(Memory mem) {
      byte sketchTypeByte = mem.getByte(3L);
      return getSketchType(sketchTypeByte);
   }

   private static SketchType getSketchType(byte sketchTypeByte) {
      if (sketchTypeByte >= 0 && sketchTypeByte < SerializerDeserializer.SketchType.values().length) {
         return SerializerDeserializer.SketchType.values()[sketchTypeByte];
      } else {
         throw new SketchesArgumentException("Invalid Sketch Type " + sketchTypeByte);
      }
   }

   public static enum SketchType {
      QuickSelectSketch,
      CompactSketch,
      ArrayOfDoublesQuickSelectSketch,
      ArrayOfDoublesCompactSketch,
      ArrayOfDoublesUnion;
   }
}
