package org.apache.hadoop.hive.common.jsonexplain;

import org.apache.hadoop.hive.common.jsonexplain.tez.TezJsonParser;
import org.apache.hadoop.hive.conf.HiveConf;

public class JsonParserFactory {
   private JsonParserFactory() {
   }

   public static JsonParser getParser(HiveConf conf) {
      return HiveConf.getVar(conf, HiveConf.ConfVars.HIVE_EXECUTION_ENGINE).equals("tez") ? new TezJsonParser() : null;
   }
}
