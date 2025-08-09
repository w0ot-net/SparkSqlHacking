package au.com.bytecode.opencsv.bean;

import au.com.bytecode.opencsv.CSVReader;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HeaderColumnNameMappingStrategy implements MappingStrategy {
   protected String[] header;
   protected Map descriptorMap = null;
   protected Class type;

   public void captureHeader(CSVReader reader) throws IOException {
      this.header = reader.readNext();
   }

   public PropertyDescriptor findDescriptor(int col) throws IntrospectionException {
      String columnName = this.getColumnName(col);
      return null != columnName && columnName.trim().length() > 0 ? this.findDescriptor(columnName) : null;
   }

   protected String getColumnName(int col) {
      return null != this.header && col < this.header.length ? this.header[col] : null;
   }

   protected PropertyDescriptor findDescriptor(String name) throws IntrospectionException {
      if (null == this.descriptorMap) {
         this.descriptorMap = this.loadDescriptorMap(this.getType());
      }

      return (PropertyDescriptor)this.descriptorMap.get(name.toUpperCase().trim());
   }

   protected boolean matches(String name, PropertyDescriptor desc) {
      return desc.getName().equals(name.trim());
   }

   protected Map loadDescriptorMap(Class cls) throws IntrospectionException {
      Map<String, PropertyDescriptor> map = new HashMap();
      PropertyDescriptor[] descriptors = this.loadDescriptors(this.getType());

      for(PropertyDescriptor descriptor : descriptors) {
         map.put(descriptor.getName().toUpperCase().trim(), descriptor);
      }

      return map;
   }

   private PropertyDescriptor[] loadDescriptors(Class cls) throws IntrospectionException {
      BeanInfo beanInfo = Introspector.getBeanInfo(cls);
      return beanInfo.getPropertyDescriptors();
   }

   public Object createBean() throws InstantiationException, IllegalAccessException {
      return this.type.newInstance();
   }

   public Class getType() {
      return this.type;
   }

   public void setType(Class type) {
      this.type = type;
   }
}
