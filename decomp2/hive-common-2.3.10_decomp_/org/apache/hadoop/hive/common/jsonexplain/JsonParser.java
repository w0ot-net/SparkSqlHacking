package org.apache.hadoop.hive.common.jsonexplain;

import java.io.PrintStream;
import org.json.JSONObject;

public interface JsonParser {
   void print(JSONObject var1, PrintStream var2) throws Exception;
}
