package org.apache.hive.service.cli.operation;

public class TableTypeMappingFactory {
   private static TableTypeMapping hiveTableTypeMapping = new HiveTableTypeMapping();
   private static TableTypeMapping classicTableTypeMapping = new ClassicTableTypeMapping();

   public static TableTypeMapping getTableTypeMapping(String mappingType) {
      return TableTypeMappingFactory.TableTypeMappings.CLASSIC.toString().equalsIgnoreCase(mappingType) ? classicTableTypeMapping : hiveTableTypeMapping;
   }

   public static enum TableTypeMappings {
      HIVE,
      CLASSIC;

      // $FF: synthetic method
      private static TableTypeMappings[] $values() {
         return new TableTypeMappings[]{HIVE, CLASSIC};
      }
   }
}
