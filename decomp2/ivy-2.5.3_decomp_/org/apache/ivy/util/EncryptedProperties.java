package org.apache.ivy.util;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class EncryptedProperties extends Properties {
   public synchronized Object setProperty(String key, String value) {
      return StringUtils.decrypt((String)super.setProperty(key, StringUtils.encrypt(value)));
   }

   public String getProperty(String key) {
      return StringUtils.decrypt(super.getProperty(key));
   }

   public String getProperty(String key, String defaultValue) {
      return StringUtils.decrypt(super.getProperty(key, StringUtils.encrypt(defaultValue)));
   }

   public boolean containsValue(Object value) {
      return super.containsValue(StringUtils.encrypt((String)value));
   }

   public synchronized boolean contains(Object value) {
      return super.contains(StringUtils.encrypt((String)value));
   }

   public Collection values() {
      List<Object> ret = new LinkedList(super.values());

      for(Object value : ret) {
         ret.set(ret.indexOf(value), StringUtils.decrypt((String)value));
      }

      return ret;
   }
}
