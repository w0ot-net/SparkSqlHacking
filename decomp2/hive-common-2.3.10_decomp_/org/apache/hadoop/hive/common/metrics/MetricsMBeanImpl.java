package org.apache.hadoop.hive.common.metrics;

import java.util.HashMap;
import java.util.Map;
import javax.management.Attribute;
import javax.management.AttributeList;
import javax.management.AttributeNotFoundException;
import javax.management.InvalidAttributeValueException;
import javax.management.JMException;
import javax.management.MBeanAttributeInfo;
import javax.management.MBeanConstructorInfo;
import javax.management.MBeanException;
import javax.management.MBeanInfo;
import javax.management.MBeanNotificationInfo;
import javax.management.MBeanOperationInfo;
import javax.management.MBeanParameterInfo;
import javax.management.ReflectionException;

public class MetricsMBeanImpl implements MetricsMBean {
   private final Map metricsMap = new HashMap();
   private MBeanAttributeInfo[] attributeInfos;
   private boolean dirtyAttributeInfoCache = true;
   private static final MBeanConstructorInfo[] ctors = null;
   private static final MBeanOperationInfo[] ops = new MBeanOperationInfo[]{new MBeanOperationInfo("reset", "Sets the values of all Attributes to 0", (MBeanParameterInfo[])null, "void", 1)};
   private static final MBeanNotificationInfo[] notifs = null;

   public Object getAttribute(String arg0) throws AttributeNotFoundException, MBeanException, ReflectionException {
      synchronized(this.metricsMap) {
         if (this.metricsMap.containsKey(arg0)) {
            return this.metricsMap.get(arg0);
         } else {
            throw new AttributeNotFoundException("Key [" + arg0 + "] not found/tracked");
         }
      }
   }

   public AttributeList getAttributes(String[] arg0) {
      AttributeList results = new AttributeList();
      synchronized(this.metricsMap) {
         for(String key : arg0) {
            results.add(new Attribute(key, this.metricsMap.get(key)));
         }

         return results;
      }
   }

   public MBeanInfo getMBeanInfo() {
      if (this.dirtyAttributeInfoCache) {
         synchronized(this.metricsMap) {
            this.attributeInfos = new MBeanAttributeInfo[this.metricsMap.size()];
            int i = 0;

            for(String key : this.metricsMap.keySet()) {
               this.attributeInfos[i] = new MBeanAttributeInfo(key, this.metricsMap.get(key).getClass().getName(), key, true, true, false);
               ++i;
            }

            this.dirtyAttributeInfoCache = false;
         }
      }

      return new MBeanInfo(this.getClass().getName(), "metrics information", this.attributeInfos, ctors, ops, notifs);
   }

   public Object invoke(String name, Object[] args, String[] signature) throws MBeanException, ReflectionException {
      if (name.equals("reset")) {
         this.reset();
         return null;
      } else {
         throw new ReflectionException(new NoSuchMethodException(name));
      }
   }

   public void setAttribute(Attribute attr) throws AttributeNotFoundException, InvalidAttributeValueException, MBeanException, ReflectionException {
      try {
         this.put(attr.getName(), attr.getValue());
      } catch (Exception e) {
         throw new MBeanException(e);
      }
   }

   public AttributeList setAttributes(AttributeList arg0) {
      AttributeList attributesSet = new AttributeList();

      for(Attribute attr : arg0.asList()) {
         try {
            this.setAttribute(attr);
            attributesSet.add(attr);
         } catch (AttributeNotFoundException var6) {
         } catch (InvalidAttributeValueException var7) {
         } catch (MBeanException var8) {
         } catch (ReflectionException var9) {
         }
      }

      return attributesSet;
   }

   public boolean hasKey(String name) {
      synchronized(this.metricsMap) {
         return this.metricsMap.containsKey(name);
      }
   }

   public void put(String name, Object value) {
      synchronized(this.metricsMap) {
         if (!this.metricsMap.containsKey(name)) {
            this.dirtyAttributeInfoCache = true;
         }

         this.metricsMap.put(name, value);
      }
   }

   public Object get(String name) throws JMException {
      return this.getAttribute(name);
   }

   public void reset() {
      synchronized(this.metricsMap) {
         for(String key : this.metricsMap.keySet()) {
            this.metricsMap.put(key, 0L);
         }

      }
   }

   public void clear() {
      synchronized(this.metricsMap) {
         this.attributeInfos = null;
         this.dirtyAttributeInfoCache = true;
         this.metricsMap.clear();
      }
   }
}
