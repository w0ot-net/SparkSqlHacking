package org.apache.hadoop.hive.serde2;

import java.io.IOException;
import java.util.Properties;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.common.JavaUtils;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.conf.HiveConf.ConfVars;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hive.common.util.ReflectionUtil;

public class DefaultFetchFormatter implements FetchFormatter {
   private AbstractSerDe mSerde;

   public void initialize(Configuration hconf, Properties props) throws SerDeException {
      this.mSerde = this.initializeSerde(hconf, props);
   }

   private AbstractSerDe initializeSerde(Configuration conf, Properties props) throws SerDeException {
      String serdeName = HiveConf.getVar(conf, ConfVars.HIVEFETCHOUTPUTSERDE);

      Class<? extends AbstractSerDe> serdeClass;
      try {
         serdeClass = Class.forName(serdeName, true, JavaUtils.getClassLoader()).asSubclass(AbstractSerDe.class);
      } catch (ClassNotFoundException e) {
         throw new SerDeException(e);
      }

      AbstractSerDe serde = (AbstractSerDe)ReflectionUtil.newInstance(serdeClass, (Configuration)null);
      Properties serdeProps = new Properties();
      if (serde instanceof DelimitedJSONSerDe) {
         serdeProps.put("serialization.format", props.getProperty("serialization.format"));
         serdeProps.put("serialization.null.format", props.getProperty("serialization.null.format"));
      }

      SerDeUtils.initializeSerDe(serde, conf, serdeProps, (Properties)null);
      return serde;
   }

   public String convert(Object row, ObjectInspector rowOI) throws Exception {
      return this.mSerde.serialize(row, rowOI).toString();
   }

   public void close() throws IOException {
   }
}
