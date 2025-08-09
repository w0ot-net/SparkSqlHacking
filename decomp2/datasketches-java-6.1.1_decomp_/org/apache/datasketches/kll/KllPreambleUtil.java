package org.apache.datasketches.kll;

import java.util.Objects;
import org.apache.datasketches.common.ArrayOfItemsSerDe;
import org.apache.datasketches.common.Family;
import org.apache.datasketches.common.Util;
import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.memory.WritableMemory;

final class KllPreambleUtil {
   static final int PREAMBLE_INTS_BYTE_ADR = 0;
   static final int SER_VER_BYTE_ADR = 1;
   static final int FAMILY_BYTE_ADR = 2;
   static final int FLAGS_BYTE_ADR = 3;
   static final int K_SHORT_ADR = 4;
   static final int M_BYTE_ADR = 6;
   static final int DATA_START_ADR_SINGLE_ITEM = 8;
   static final int N_LONG_ADR = 8;
   static final int MIN_K_SHORT_ADR = 16;
   static final int NUM_LEVELS_BYTE_ADR = 18;
   static final int DATA_START_ADR = 20;
   static final byte SERIAL_VERSION_EMPTY_FULL = 1;
   static final byte SERIAL_VERSION_SINGLE = 2;
   static final byte SERIAL_VERSION_UPDATABLE = 3;
   static final byte PREAMBLE_INTS_EMPTY_SINGLE = 2;
   static final byte PREAMBLE_INTS_FULL = 5;
   static final byte KLL_FAMILY = 15;
   static final int EMPTY_BIT_MASK = 1;
   static final int LEVEL_ZERO_SORTED_BIT_MASK = 2;
   static final int SINGLE_ITEM_BIT_MASK = 4;

   private KllPreambleUtil() {
   }

   static String toString(byte[] byteArr, KllSketch.SketchType sketchType, boolean includeData) {
      Memory mem = Memory.wrap(byteArr);
      return toString((Memory)mem, sketchType, includeData, (ArrayOfItemsSerDe)null);
   }

   static String toString(byte[] byteArr, KllSketch.SketchType sketchType, boolean includeData, ArrayOfItemsSerDe serDe) {
      Memory mem = Memory.wrap(byteArr);
      return toString(mem, sketchType, includeData, serDe);
   }

   static String toString(Memory mem, KllSketch.SketchType sketchType, boolean includeData) {
      return toString((Memory)mem, sketchType, includeData, (ArrayOfItemsSerDe)null);
   }

   static String toString(Memory mem, KllSketch.SketchType sketchType, boolean includeData, ArrayOfItemsSerDe serDe) {
      if (sketchType == KllSketch.SketchType.ITEMS_SKETCH) {
         Objects.requireNonNull(serDe, "SerDe parameter must not be null for ITEMS_SKETCH.");
      }

      KllMemoryValidate memVal = new KllMemoryValidate(mem, sketchType, serDe);
      KllSketch.SketchStructure myStructure = memVal.sketchStructure;
      int flags = memVal.flags & 255;
      String flagsStr = flags + ", 0x" + Integer.toHexString(flags) + ", " + Util.zeroPad(Integer.toBinaryString(flags), 8);
      int preInts = memVal.preInts;
      boolean emptyFlag = memVal.emptyFlag;
      int sketchBytes = memVal.sketchBytes;
      int typeBytes = sketchType.getBytes();
      int familyID = getMemoryFamilyID(mem);
      String famName = Family.idToFamily(familyID).toString();
      StringBuilder sb = new StringBuilder();
      sb.append(Util.LS).append("### KLL SKETCH MEMORY SUMMARY:").append(Util.LS);
      sb.append("Sketch Type                          : ").append(sketchType.toString()).append(Util.LS);
      sb.append("SketchStructure                      : ").append(myStructure.toString()).append(Util.LS);
      sb.append("Byte   0       : Preamble Ints       : ").append(preInts).append(Util.LS);
      sb.append("Byte   1       : SerVer              : ").append(memVal.serVer).append(Util.LS);
      sb.append("Byte   2       : FamilyID            : ").append(memVal.familyID).append(Util.LS);
      sb.append("               : FamilyName          : ").append(famName).append(Util.LS);
      sb.append("Byte   3       : Flags Field         : ").append(flagsStr).append(Util.LS);
      sb.append("            Bit: Flag Name           : ").append(Util.LS);
      sb.append("              0: EMPTY               : ").append(emptyFlag).append(Util.LS);
      sb.append("              1: LEVEL_ZERO_SORTED   : ").append(memVal.level0SortedFlag).append(Util.LS);
      sb.append("Bytes  4-5     : K                   : ").append(memVal.k).append(Util.LS);
      sb.append("Byte   6       : Min Level Cap, M    : ").append(memVal.m).append(Util.LS);
      sb.append("Byte   7       : (Reserved)          : ").append(Util.LS);
      long n = memVal.n;
      int minK = memVal.minK;
      int numLevels = memVal.numLevels;
      int[] levelsArr = memVal.levelsArr;
      int retainedItems = levelsArr[numLevels] - levelsArr[0];
      if (myStructure != KllSketch.SketchStructure.COMPACT_FULL && myStructure != KllSketch.SketchStructure.UPDATABLE) {
         sb.append("Assumed        : N                   : ").append(n).append(Util.LS);
         sb.append("Assumed        : MinK                : ").append(minK).append(Util.LS);
         sb.append("Assumed        : NumLevels           : ").append(numLevels).append(Util.LS);
      } else {
         sb.append("Bytes  8-15    : N                   : ").append(n).append(Util.LS);
         sb.append("Bytes 16-17    : MinK                : ").append(minK).append(Util.LS);
         sb.append("Byte  18       : NumLevels           : ").append(numLevels).append(Util.LS);
      }

      sb.append("PreambleBytes                        : ").append(preInts * 4).append(Util.LS);
      sb.append("Sketch Bytes                         : ").append(sketchBytes).append(Util.LS);
      sb.append("Memory Capacity Bytes                : ").append(mem.getCapacity()).append(Util.LS);
      sb.append("### END KLL Sketch Memory Summary").append(Util.LS);
      if (includeData) {
         sb.append(Util.LS);
         sb.append("### START KLL DATA:").append(Util.LS);
         int offsetBytes = 0;
         if (myStructure == KllSketch.SketchStructure.UPDATABLE) {
            sb.append("LEVELS ARR:").append(Util.LS);
            offsetBytes = 20;

            for(int i = 0; i < numLevels + 1; ++i) {
               sb.append(i + ", " + mem.getInt((long)offsetBytes)).append(Util.LS);
               offsetBytes += 4;
            }

            sb.append("MIN/MAX:").append(Util.LS);
            if (sketchType == KllSketch.SketchType.DOUBLES_SKETCH) {
               sb.append(mem.getDouble((long)offsetBytes)).append(Util.LS);
               offsetBytes += typeBytes;
               sb.append(mem.getDouble((long)offsetBytes)).append(Util.LS);
               offsetBytes += typeBytes;
            } else if (sketchType == KllSketch.SketchType.FLOATS_SKETCH) {
               sb.append(mem.getFloat((long)offsetBytes)).append(Util.LS);
               offsetBytes += typeBytes;
               sb.append(mem.getFloat((long)offsetBytes)).append(Util.LS);
               offsetBytes += typeBytes;
            } else if (sketchType == KllSketch.SketchType.LONGS_SKETCH) {
               sb.append(mem.getLong((long)offsetBytes)).append(Util.LS);
               offsetBytes += typeBytes;
               sb.append(mem.getLong((long)offsetBytes)).append(Util.LS);
               offsetBytes += typeBytes;
            } else {
               sb.append("<<<Updatable Structure is not suppported by ItemsSketch>>>").append(Util.LS);
            }

            sb.append("ALL DATA (including free space)").append(Util.LS);
            int itemsSpace = (sketchBytes - offsetBytes) / typeBytes;
            if (sketchType == KllSketch.SketchType.DOUBLES_SKETCH) {
               for(int i = 0; i < itemsSpace; ++i) {
                  sb.append(i + ", " + mem.getDouble((long)offsetBytes)).append(Util.LS);
                  offsetBytes += typeBytes;
               }
            } else if (sketchType == KllSketch.SketchType.FLOATS_SKETCH) {
               for(int i = 0; i < itemsSpace; ++i) {
                  sb.append(mem.getFloat((long)offsetBytes)).append(Util.LS);
                  offsetBytes += typeBytes;
               }
            } else if (sketchType == KllSketch.SketchType.LONGS_SKETCH) {
               for(int i = 0; i < itemsSpace; ++i) {
                  sb.append(mem.getLong((long)offsetBytes)).append(Util.LS);
                  offsetBytes += typeBytes;
               }
            } else {
               sb.append("<<<Updatable Structure is not suppported by ItemsSketch>>>").append(Util.LS);
            }
         } else if (myStructure != KllSketch.SketchStructure.COMPACT_FULL) {
            if (myStructure == KllSketch.SketchStructure.COMPACT_SINGLE) {
               sb.append("SINGLE ITEM DATUM: ");
               if (sketchType == KllSketch.SketchType.DOUBLES_SKETCH) {
                  sb.append(mem.getDouble(8L)).append(Util.LS);
               } else if (sketchType == KllSketch.SketchType.FLOATS_SKETCH) {
                  sb.append(mem.getFloat(8L)).append(Util.LS);
               } else if (sketchType == KllSketch.SketchType.LONGS_SKETCH) {
                  sb.append(mem.getLong(8L)).append(Util.LS);
               } else {
                  sb.append(serDe.deserializeFromMemory(mem, 8L, 1)[0]).append(Util.LS);
               }
            } else {
               sb.append("EMPTY, NO DATA").append(Util.LS);
            }
         } else {
            sb.append("LEVELS ARR:").append(Util.LS);
            offsetBytes = 20;

            int j;
            for(j = 0; j < numLevels; ++j) {
               sb.append(j + ", " + mem.getInt((long)offsetBytes)).append(Util.LS);
               offsetBytes += 4;
            }

            sb.append(j + ", " + levelsArr[numLevels]);
            sb.append(" (Top level of Levels Array is absent in Memory)").append(Util.LS);
            sb.append("MIN/MAX:").append(Util.LS);
            if (sketchType == KllSketch.SketchType.DOUBLES_SKETCH) {
               sb.append(mem.getDouble((long)offsetBytes)).append(Util.LS);
               offsetBytes += typeBytes;
               sb.append(mem.getDouble((long)offsetBytes)).append(Util.LS);
               offsetBytes += typeBytes;
            } else if (sketchType == KllSketch.SketchType.FLOATS_SKETCH) {
               sb.append(mem.getFloat((long)offsetBytes)).append(Util.LS);
               offsetBytes += typeBytes;
               sb.append(mem.getFloat((long)offsetBytes)).append(Util.LS);
               offsetBytes += typeBytes;
            } else if (sketchType == KllSketch.SketchType.LONGS_SKETCH) {
               sb.append(mem.getLong((long)offsetBytes)).append(Util.LS);
               offsetBytes += typeBytes;
               sb.append(mem.getLong((long)offsetBytes)).append(Util.LS);
               offsetBytes += typeBytes;
            } else {
               sb.append(serDe.deserializeFromMemory(mem, (long)offsetBytes, 1)[0]).append(Util.LS);
               offsetBytes += serDe.sizeOf(mem, (long)offsetBytes, 1);
               sb.append(serDe.deserializeFromMemory(mem, (long)offsetBytes, 1)[0]).append(Util.LS);
               offsetBytes += serDe.sizeOf(mem, (long)offsetBytes, 1);
            }

            sb.append("RETAINED DATA").append(Util.LS);
            int itemSpace = (sketchBytes - offsetBytes) / (typeBytes == 0 ? 1 : typeBytes);
            if (sketchType == KllSketch.SketchType.DOUBLES_SKETCH) {
               for(int i = 0; i < itemSpace; ++i) {
                  sb.append(i + ", " + mem.getDouble((long)offsetBytes)).append(Util.LS);
                  offsetBytes += typeBytes;
               }
            } else if (sketchType == KllSketch.SketchType.FLOATS_SKETCH) {
               for(int i = 0; i < itemSpace; ++i) {
                  sb.append(i + ", " + mem.getFloat((long)offsetBytes)).append(Util.LS);
                  offsetBytes += typeBytes;
               }
            } else if (sketchType == KllSketch.SketchType.LONGS_SKETCH) {
               for(int i = 0; i < itemSpace; ++i) {
                  sb.append(i + ", " + mem.getLong((long)offsetBytes)).append(Util.LS);
                  offsetBytes += typeBytes;
               }
            } else {
               T[] itemsArr = (T[])serDe.deserializeFromMemory(mem, (long)offsetBytes, retainedItems);

               for(int i = 0; i < itemsArr.length; ++i) {
                  sb.append(i + ", " + serDe.toString(itemsArr[i])).append(Util.LS);
               }
            }
         }

         sb.append("### END KLL DATA:").append(Util.LS);
      }

      return sb.toString();
   }

   static int getMemoryPreInts(Memory mem) {
      return mem.getByte(0L) & 255;
   }

   static int getMemorySerVer(Memory mem) {
      return mem.getByte(1L) & 255;
   }

   static KllSketch.SketchStructure getMemorySketchStructure(Memory mem) {
      int preInts = getMemoryPreInts(mem);
      int serVer = getMemorySerVer(mem);
      KllSketch.SketchStructure structure = KllSketch.SketchStructure.getSketchStructure(preInts, serVer);
      return structure;
   }

   static int getMemoryFamilyID(Memory mem) {
      return mem.getByte(2L) & 255;
   }

   static int getMemoryFlags(Memory mem) {
      return mem.getByte(3L) & 255;
   }

   static boolean getMemoryEmptyFlag(Memory mem) {
      return (getMemoryFlags(mem) & 1) != 0;
   }

   static boolean getMemoryLevelZeroSortedFlag(Memory mem) {
      return (getMemoryFlags(mem) & 2) != 0;
   }

   static int getMemoryK(Memory mem) {
      return mem.getShort(4L) & '\uffff';
   }

   static int getMemoryM(Memory mem) {
      return mem.getByte(6L) & 255;
   }

   static long getMemoryN(Memory mem) {
      return mem.getLong(8L);
   }

   static int getMemoryMinK(Memory mem) {
      return mem.getShort(16L) & '\uffff';
   }

   static int getMemoryNumLevels(Memory mem) {
      return mem.getByte(18L) & 255;
   }

   static void setMemoryPreInts(WritableMemory wmem, int numPreInts) {
      wmem.putByte(0L, (byte)numPreInts);
   }

   static void setMemorySerVer(WritableMemory wmem, int serVer) {
      wmem.putByte(1L, (byte)serVer);
   }

   static void setMemoryFamilyID(WritableMemory wmem, int famId) {
      wmem.putByte(2L, (byte)famId);
   }

   static void setMemoryFlags(WritableMemory wmem, int flags) {
      wmem.putByte(3L, (byte)flags);
   }

   static void setMemoryEmptyFlag(WritableMemory wmem, boolean empty) {
      int flags = getMemoryFlags(wmem);
      setMemoryFlags(wmem, empty ? flags | 1 : flags & -2);
   }

   static void setMemoryLevelZeroSortedFlag(WritableMemory wmem, boolean levelZeroSorted) {
      int flags = getMemoryFlags(wmem);
      setMemoryFlags(wmem, levelZeroSorted ? flags | 2 : flags & -3);
   }

   static void setMemoryK(WritableMemory wmem, int memK) {
      wmem.putShort(4L, (short)memK);
   }

   static void setMemoryM(WritableMemory wmem, int memM) {
      wmem.putByte(6L, (byte)memM);
   }

   static void setMemoryN(WritableMemory wmem, long memN) {
      wmem.putLong(8L, memN);
   }

   static void setMemoryMinK(WritableMemory wmem, int memMinK) {
      wmem.putShort(16L, (short)memMinK);
   }

   static void setMemoryNumLevels(WritableMemory wmem, int memNumLevels) {
      wmem.putByte(18L, (byte)memNumLevels);
   }
}
