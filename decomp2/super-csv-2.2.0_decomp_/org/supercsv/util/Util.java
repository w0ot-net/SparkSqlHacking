package org.supercsv.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.exception.SuperCsvException;

public final class Util {
   private Util() {
   }

   public static void executeCellProcessors(List destination, List source, CellProcessor[] processors, int lineNo, int rowNo) {
      if (destination == null) {
         throw new NullPointerException("destination should not be null");
      } else if (source == null) {
         throw new NullPointerException("source should not be null");
      } else if (processors == null) {
         throw new NullPointerException("processors should not be null");
      } else {
         CsvContext context = new CsvContext(lineNo, rowNo, 1);
         context.setRowSource(new ArrayList(source));
         if (source.size() != processors.length) {
            throw new SuperCsvException(String.format("The number of columns to be processed (%d) must match the number of CellProcessors (%d): check that the number of CellProcessors you have defined matches the expected number of columns being read/written", source.size(), processors.length), context);
         } else {
            destination.clear();

            for(int i = 0; i < source.size(); ++i) {
               context.setColumnNumber(i + 1);
               if (processors[i] == null) {
                  destination.add(source.get(i));
               } else {
                  destination.add(processors[i].execute(source.get(i), context));
               }
            }

         }
      }
   }

   public static void filterListToMap(Map destinationMap, String[] nameMapping, List sourceList) {
      if (destinationMap == null) {
         throw new NullPointerException("destinationMap should not be null");
      } else if (nameMapping == null) {
         throw new NullPointerException("nameMapping should not be null");
      } else if (sourceList == null) {
         throw new NullPointerException("sourceList should not be null");
      } else if (nameMapping.length != sourceList.size()) {
         throw new SuperCsvException(String.format("the nameMapping array and the sourceList should be the same size (nameMapping length = %d, sourceList size = %d)", nameMapping.length, sourceList.size()));
      } else {
         destinationMap.clear();

         for(int i = 0; i < nameMapping.length; ++i) {
            String key = nameMapping[i];
            if (key != null) {
               if (destinationMap.containsKey(key)) {
                  throw new SuperCsvException(String.format("duplicate nameMapping '%s' at index %d", key, i));
               }

               destinationMap.put(key, sourceList.get(i));
            }
         }

      }
   }

   public static List filterMapToList(Map map, String[] nameMapping) {
      if (map == null) {
         throw new NullPointerException("map should not be null");
      } else if (nameMapping == null) {
         throw new NullPointerException("nameMapping should not be null");
      } else {
         List<Object> result = new ArrayList(nameMapping.length);

         for(String key : nameMapping) {
            result.add(map.get(key));
         }

         return result;
      }
   }

   public static Object[] filterMapToObjectArray(Map values, String[] nameMapping) {
      if (values == null) {
         throw new NullPointerException("values should not be null");
      } else if (nameMapping == null) {
         throw new NullPointerException("nameMapping should not be null");
      } else {
         Object[] targetArray = new Object[nameMapping.length];
         int i = 0;

         for(String name : nameMapping) {
            targetArray[i++] = values.get(name);
         }

         return targetArray;
      }
   }

   public static String[] objectArrayToStringArray(Object[] objectArray) {
      if (objectArray == null) {
         return null;
      } else {
         String[] stringArray = new String[objectArray.length];

         for(int i = 0; i < objectArray.length; ++i) {
            stringArray[i] = objectArray[i] != null ? objectArray[i].toString() : null;
         }

         return stringArray;
      }
   }

   public static String[] objectListToStringArray(List objectList) {
      if (objectList == null) {
         return null;
      } else {
         String[] stringArray = new String[objectList.size()];

         for(int i = 0; i < objectList.size(); ++i) {
            stringArray[i] = objectList.get(i) != null ? objectList.get(i).toString() : null;
         }

         return stringArray;
      }
   }
}
