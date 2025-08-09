package org.apache.hadoop.hive.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.conf.HiveConf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StatsSetupConst {
   protected static final Logger LOG = LoggerFactory.getLogger(StatsSetupConst.class.getName());
   public static final String NUM_FILES = "numFiles";
   public static final String NUM_PARTITIONS = "numPartitions";
   public static final String TOTAL_SIZE = "totalSize";
   public static final String ROW_COUNT = "numRows";
   public static final String RUN_TIME_ROW_COUNT = "runTimeNumRows";
   public static final String RAW_DATA_SIZE = "rawDataSize";
   public static final String STATS_TMP_LOC = "hive.stats.tmp.loc";
   public static final String STATS_FILE_PREFIX = "tmpstats-";
   public static final String[] supportedStats = new String[]{"numFiles", "numRows", "totalSize", "rawDataSize"};
   public static final String[] statsRequireCompute = new String[]{"numRows", "rawDataSize"};
   public static final String[] fastStats = new String[]{"numFiles", "totalSize"};
   public static final String STATS_GENERATED = "STATS_GENERATED";
   public static final String TASK = "TASK";
   public static final String USER = "USER";
   public static final String DO_NOT_UPDATE_STATS = "DO_NOT_UPDATE_STATS";
   public static final String COLUMN_STATS_ACCURATE = "COLUMN_STATS_ACCURATE";
   public static final String COLUMN_STATS = "COLUMN_STATS";
   public static final String BASIC_STATS = "BASIC_STATS";
   public static final String CASCADE = "CASCADE";
   public static final String TRUE = "true";
   public static final String FALSE = "false";
   public static final String[] TABLE_PARAMS_STATS_KEYS = new String[]{"COLUMN_STATS_ACCURATE", "numFiles", "totalSize", "numRows", "rawDataSize", "numPartitions"};

   public static boolean areBasicStatsUptoDate(Map params) {
      if (params == null) {
         return false;
      } else {
         ColumnStatsAccurate stats = parseStatsAcc((String)params.get("COLUMN_STATS_ACCURATE"));
         return stats.basicStats;
      }
   }

   public static boolean areColumnStatsUptoDate(Map params, String colName) {
      if (params == null) {
         return false;
      } else {
         ColumnStatsAccurate stats = parseStatsAcc((String)params.get("COLUMN_STATS_ACCURATE"));
         return stats.columnStats.containsKey(colName);
      }
   }

   public static void setBasicStatsState(Map params, String setting) {
      if (setting.equals("false")) {
         if (params != null && params.containsKey("COLUMN_STATS_ACCURATE")) {
            params.remove("COLUMN_STATS_ACCURATE");
         }

      } else if (params == null) {
         throw new RuntimeException("params are null...cant set columnstatstate!");
      } else {
         ColumnStatsAccurate stats = parseStatsAcc((String)params.get("COLUMN_STATS_ACCURATE"));
         stats.basicStats = true;

         try {
            params.put("COLUMN_STATS_ACCURATE", StatsSetupConst.ColumnStatsAccurate.objectWriter.writeValueAsString(stats));
         } catch (JsonProcessingException e) {
            throw new RuntimeException("can't serialize column stats", e);
         }
      }
   }

   public static void setColumnStatsState(Map params, List colNames) {
      if (params == null) {
         throw new RuntimeException("params are null...cant set columnstatstate!");
      } else {
         ColumnStatsAccurate stats = parseStatsAcc((String)params.get("COLUMN_STATS_ACCURATE"));

         for(String colName : colNames) {
            if (!stats.columnStats.containsKey(colName)) {
               stats.columnStats.put(colName, true);
            }
         }

         try {
            params.put("COLUMN_STATS_ACCURATE", StatsSetupConst.ColumnStatsAccurate.objectWriter.writeValueAsString(stats));
         } catch (JsonProcessingException e) {
            LOG.trace(e.getMessage());
         }

      }
   }

   public static void clearColumnStatsState(Map params) {
      if (params != null) {
         ColumnStatsAccurate stats = parseStatsAcc((String)params.get("COLUMN_STATS_ACCURATE"));
         stats.columnStats.clear();

         try {
            params.put("COLUMN_STATS_ACCURATE", StatsSetupConst.ColumnStatsAccurate.objectWriter.writeValueAsString(stats));
         } catch (JsonProcessingException e) {
            LOG.trace(e.getMessage());
         }

      }
   }

   public static void removeColumnStatsState(Map params, List colNames) {
      if (params != null) {
         try {
            ColumnStatsAccurate stats = parseStatsAcc((String)params.get("COLUMN_STATS_ACCURATE"));

            for(String string : colNames) {
               stats.columnStats.remove(string);
            }

            params.put("COLUMN_STATS_ACCURATE", StatsSetupConst.ColumnStatsAccurate.objectWriter.writeValueAsString(stats));
         } catch (JsonProcessingException e) {
            LOG.trace(e.getMessage());
         }

      }
   }

   public static void setBasicStatsStateForCreateTable(Map params, String setting) {
      if ("true".equals(setting)) {
         for(String stat : supportedStats) {
            params.put(stat, "0");
         }
      }

      setBasicStatsState(params, setting);
   }

   private static ColumnStatsAccurate parseStatsAcc(String statsAcc) {
      if (statsAcc == null) {
         return new ColumnStatsAccurate();
      } else {
         try {
            return (ColumnStatsAccurate)StatsSetupConst.ColumnStatsAccurate.objectReader.readValue(statsAcc);
         } catch (Exception var3) {
            ColumnStatsAccurate ret = new ColumnStatsAccurate();
            if ("true".equalsIgnoreCase(statsAcc)) {
               ret.basicStats = true;
            }

            return ret;
         }
      }
   }

   public static enum StatDB {
      fs {
         public String getPublisher(Configuration conf) {
            return "org.apache.hadoop.hive.ql.stats.fs.FSStatsPublisher";
         }

         public String getAggregator(Configuration conf) {
            return "org.apache.hadoop.hive.ql.stats.fs.FSStatsAggregator";
         }
      },
      custom {
         public String getPublisher(Configuration conf) {
            return HiveConf.getVar(conf, HiveConf.ConfVars.HIVE_STATS_DEFAULT_PUBLISHER);
         }

         public String getAggregator(Configuration conf) {
            return HiveConf.getVar(conf, HiveConf.ConfVars.HIVE_STATS_DEFAULT_AGGREGATOR);
         }
      };

      private StatDB() {
      }

      public abstract String getPublisher(Configuration var1);

      public abstract String getAggregator(Configuration var1);
   }

   private static class ColumnStatsAccurate {
      private static ObjectReader objectReader;
      private static ObjectWriter objectWriter;
      @JsonInclude(Include.NON_DEFAULT)
      @JsonSerialize(
         using = BooleanSerializer.class
      )
      @JsonDeserialize(
         using = BooleanDeserializer.class
      )
      @JsonProperty("BASIC_STATS")
      boolean basicStats;
      @JsonInclude(Include.NON_EMPTY)
      @JsonProperty("COLUMN_STATS")
      @JsonSerialize(
         contentUsing = BooleanSerializer.class
      )
      @JsonDeserialize(
         contentUsing = BooleanDeserializer.class
      )
      TreeMap columnStats;

      private ColumnStatsAccurate() {
         this.columnStats = new TreeMap();
      }

      static {
         ObjectMapper objectMapper = new ObjectMapper();
         objectReader = objectMapper.readerFor(ColumnStatsAccurate.class);
         objectWriter = objectMapper.writerFor(ColumnStatsAccurate.class);
      }

      static class BooleanSerializer extends JsonSerializer {
         public void serialize(Boolean value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
            jsonGenerator.writeString(value.toString());
         }
      }

      static class BooleanDeserializer extends JsonDeserializer {
         public Boolean deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            return Boolean.valueOf(jsonParser.getValueAsString());
         }
      }
   }
}
