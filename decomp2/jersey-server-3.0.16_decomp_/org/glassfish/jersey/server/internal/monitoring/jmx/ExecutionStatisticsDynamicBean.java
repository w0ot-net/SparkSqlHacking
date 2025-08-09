package org.glassfish.jersey.server.internal.monitoring.jmx;

import java.util.HashMap;
import java.util.Map;
import javax.management.Attribute;
import javax.management.AttributeList;
import javax.management.AttributeNotFoundException;
import javax.management.DynamicMBean;
import javax.management.InvalidAttributeValueException;
import javax.management.MBeanAttributeInfo;
import javax.management.MBeanConstructorInfo;
import javax.management.MBeanException;
import javax.management.MBeanInfo;
import javax.management.MBeanNotificationInfo;
import javax.management.MBeanOperationInfo;
import javax.management.ReflectionException;
import org.glassfish.jersey.internal.util.collection.Value;
import org.glassfish.jersey.server.monitoring.ExecutionStatistics;
import org.glassfish.jersey.server.monitoring.TimeWindowStatistics;

public class ExecutionStatisticsDynamicBean implements DynamicMBean {
   private volatile ExecutionStatistics executionStatistics;
   private final Map attributeValues = new HashMap();
   private final MBeanInfo mBeanInfo;

   private MBeanInfo initMBeanInfo(ExecutionStatistics initialStatistics) {
      Map<Long, TimeWindowStatistics> statsMap = initialStatistics.getTimeWindowStatistics();
      MBeanAttributeInfo[] attrs = new MBeanAttributeInfo[statsMap.size() * 5];
      int i = 0;

      for(TimeWindowStatistics stats : statsMap.values()) {
         final long interval = stats.getTimeWindow();
         String postfix = this.convertIntervalToString((int)interval);
         String name = "MinTime[ms]_" + postfix;
         attrs[i++] = new MBeanAttributeInfo(name, "long", "Minimum request processing time in milliseconds in last " + postfix + ".", true, false, false);
         this.attributeValues.put(name, new Value() {
            public Object get() {
               return ((TimeWindowStatistics)ExecutionStatisticsDynamicBean.this.executionStatistics.getTimeWindowStatistics().get(interval)).getMinimumDuration();
            }
         });
         name = "MaxTime[ms]_" + postfix;
         attrs[i++] = new MBeanAttributeInfo(name, "long", "Minimum request processing time  in milliseconds in last " + postfix + ".", true, false, false);
         this.attributeValues.put(name, new Value() {
            public Object get() {
               return ((TimeWindowStatistics)ExecutionStatisticsDynamicBean.this.executionStatistics.getTimeWindowStatistics().get(interval)).getMaximumDuration();
            }
         });
         name = "AverageTime[ms]_" + postfix;
         attrs[i++] = new MBeanAttributeInfo(name, "long", "Average request processing time in milliseconds in last " + postfix + ".", true, false, false);
         this.attributeValues.put(name, new Value() {
            public Object get() {
               return ((TimeWindowStatistics)ExecutionStatisticsDynamicBean.this.executionStatistics.getTimeWindowStatistics().get(interval)).getAverageDuration();
            }
         });
         name = "RequestRate[requestsPerSeconds]_" + postfix;
         attrs[i++] = new MBeanAttributeInfo(name, "double", "Average requests per second in last " + postfix + ".", true, false, false);
         this.attributeValues.put(name, new Value() {
            public Object get() {
               return ((TimeWindowStatistics)ExecutionStatisticsDynamicBean.this.executionStatistics.getTimeWindowStatistics().get(interval)).getRequestsPerSecond();
            }
         });
         name = "RequestCount_" + postfix;
         attrs[i++] = new MBeanAttributeInfo(name, "double", "Request count in last " + postfix + ".", true, false, false);
         this.attributeValues.put(name, new Value() {
            public Object get() {
               return ((TimeWindowStatistics)ExecutionStatisticsDynamicBean.this.executionStatistics.getTimeWindowStatistics().get(interval)).getRequestCount();
            }
         });
      }

      return new MBeanInfo(this.getClass().getName(), "Execution statistics", attrs, (MBeanConstructorInfo[])null, (MBeanOperationInfo[])null, (MBeanNotificationInfo[])null);
   }

   private String convertIntervalToString(int interval) {
      int hours = interval / 3600000;
      interval -= hours * 3600000;
      int minutes = interval / '\uea60';
      interval -= minutes * '\uea60';
      int seconds = interval / 1000;
      StringBuffer sb = new StringBuffer();
      if (hours > 0) {
         sb.append(hours).append("h_");
      }

      if (minutes > 0) {
         sb.append(minutes).append("m_");
      }

      if (seconds > 0) {
         sb.append(seconds).append("s_");
      }

      if (sb.length() == 0) {
         sb.append("total");
      } else {
         sb.setLength(sb.length() - 1);
      }

      return sb.toString();
   }

   public ExecutionStatisticsDynamicBean(ExecutionStatistics executionStatistics, MBeanExposer mBeanExposer, String parentBeanName, String beanName) {
      this.executionStatistics = executionStatistics;
      this.mBeanInfo = this.initMBeanInfo(executionStatistics);
      mBeanExposer.registerMBean(this, parentBeanName + ",executionTimes=" + beanName);
   }

   public void updateExecutionStatistics(ExecutionStatistics executionStatistics) {
      this.executionStatistics = executionStatistics;
   }

   public Object getAttribute(String attribute) throws AttributeNotFoundException, MBeanException, ReflectionException {
      return ((Value)this.attributeValues.get(attribute)).get();
   }

   public void setAttribute(Attribute attribute) throws AttributeNotFoundException, InvalidAttributeValueException, MBeanException, ReflectionException {
   }

   public AttributeList getAttributes(String[] attributes) {
      AttributeList x = new AttributeList();
      if (attributes == null) {
         return x;
      } else {
         for(String k : attributes) {
            Value<?> value = (Value)this.attributeValues.get(k);
            if (value != null) {
               x.add(new Attribute(k, value.get()));
            }
         }

         return x;
      }
   }

   public AttributeList setAttributes(AttributeList attributes) {
      return null;
   }

   public Object invoke(String actionName, Object[] params, String[] signature) throws MBeanException, ReflectionException {
      return null;
   }

   public MBeanInfo getMBeanInfo() {
      return this.mBeanInfo;
   }
}
