package org.apache.parquet.conf;

import java.util.Iterator;
import org.apache.hadoop.conf.Configuration;

public class HadoopParquetConfiguration implements ParquetConfiguration {
   private final Configuration configuration;

   public HadoopParquetConfiguration() {
      this(true);
   }

   public HadoopParquetConfiguration(boolean loadDefaults) {
      this.configuration = new Configuration(loadDefaults);
   }

   public HadoopParquetConfiguration(Configuration conf) {
      this.configuration = conf;
   }

   public Configuration getConfiguration() {
      return this.configuration;
   }

   public void set(String name, String value) {
      this.configuration.set(name, value);
   }

   public void setLong(String name, long value) {
      this.configuration.setLong(name, value);
   }

   public void setInt(String name, int value) {
      this.configuration.setInt(name, value);
   }

   public void setBoolean(String name, boolean value) {
      this.configuration.setBoolean(name, value);
   }

   public void setStrings(String name, String... values) {
      this.configuration.setStrings(name, values);
   }

   public void setClass(String name, Class value, Class xface) {
      this.configuration.setClass(name, value, xface);
   }

   public String get(String name) {
      return this.configuration.get(name);
   }

   public String get(String name, String defaultValue) {
      return this.configuration.get(name, defaultValue);
   }

   public long getLong(String name, long defaultValue) {
      return this.configuration.getLong(name, defaultValue);
   }

   public int getInt(String name, int defaultValue) {
      return this.configuration.getInt(name, defaultValue);
   }

   public boolean getBoolean(String name, boolean defaultValue) {
      return this.configuration.getBoolean(name, defaultValue);
   }

   public String getTrimmed(String name) {
      return this.configuration.getTrimmed(name);
   }

   public String getTrimmed(String name, String defaultValue) {
      return this.configuration.getTrimmed(name, defaultValue);
   }

   public String[] getStrings(String name, String[] defaultValue) {
      return this.configuration.getStrings(name, defaultValue);
   }

   public Class getClass(String name, Class defaultValue) {
      return this.configuration.getClass(name, defaultValue);
   }

   public Class getClass(String name, Class defaultValue, Class xface) {
      return this.configuration.getClass(name, defaultValue, xface);
   }

   public Class getClassByName(String name) throws ClassNotFoundException {
      return this.configuration.getClassByName(name);
   }

   public Iterator iterator() {
      return this.configuration.iterator();
   }
}
