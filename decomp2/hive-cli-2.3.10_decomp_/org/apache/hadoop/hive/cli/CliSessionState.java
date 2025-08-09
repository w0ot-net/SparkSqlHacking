package org.apache.hadoop.hive.cli;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.ql.session.SessionState;

public class CliSessionState extends SessionState {
   public String database;
   public String execString;
   public String fileName;
   public Properties cmdProperties = new Properties();
   public List initFiles = new ArrayList();

   public CliSessionState(HiveConf conf) {
      super(conf);
   }

   public void close() {
      try {
         super.close();
      } catch (IOException ioe) {
         ioe.printStackTrace();
      }

   }
}
