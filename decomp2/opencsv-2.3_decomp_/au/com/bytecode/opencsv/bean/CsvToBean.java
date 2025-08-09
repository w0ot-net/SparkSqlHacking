package au.com.bytecode.opencsv.bean;

import au.com.bytecode.opencsv.CSVReader;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.beans.PropertyEditor;
import java.beans.PropertyEditorManager;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CsvToBean {
   private Map editorMap = null;

   public List parse(MappingStrategy mapper, Reader reader) {
      return this.parse(mapper, new CSVReader(reader));
   }

   public List parse(MappingStrategy mapper, CSVReader csv) {
      try {
         mapper.captureHeader(csv);
         List<T> list = new ArrayList();

         String[] line;
         while(null != (line = csv.readNext())) {
            T obj = (T)this.processLine(mapper, line);
            list.add(obj);
         }

         return list;
      } catch (Exception e) {
         throw new RuntimeException("Error parsing CSV!", e);
      }
   }

   protected Object processLine(MappingStrategy mapper, String[] line) throws IllegalAccessException, InvocationTargetException, InstantiationException, IntrospectionException {
      T bean = (T)mapper.createBean();

      for(int col = 0; col < line.length; ++col) {
         PropertyDescriptor prop = mapper.findDescriptor(col);
         if (null != prop) {
            String value = this.checkForTrim(line[col], prop);
            Object obj = this.convertValue(value, prop);
            prop.getWriteMethod().invoke(bean, obj);
         }
      }

      return bean;
   }

   private String checkForTrim(String s, PropertyDescriptor prop) {
      return this.trimmableProperty(prop) ? s.trim() : s;
   }

   private boolean trimmableProperty(PropertyDescriptor prop) {
      return !prop.getPropertyType().getName().contains("String");
   }

   protected Object convertValue(String value, PropertyDescriptor prop) throws InstantiationException, IllegalAccessException {
      PropertyEditor editor = this.getPropertyEditor(prop);
      Object obj = value;
      if (null != editor) {
         editor.setAsText(value);
         obj = editor.getValue();
      }

      return obj;
   }

   private PropertyEditor getPropertyEditorValue(Class cls) {
      if (this.editorMap == null) {
         this.editorMap = new HashMap();
      }

      PropertyEditor editor = (PropertyEditor)this.editorMap.get(cls);
      if (editor == null) {
         editor = PropertyEditorManager.findEditor(cls);
         this.addEditorToMap(cls, editor);
      }

      return editor;
   }

   private void addEditorToMap(Class cls, PropertyEditor editor) {
      if (editor != null) {
         this.editorMap.put(cls, editor);
      }

   }

   protected PropertyEditor getPropertyEditor(PropertyDescriptor desc) throws InstantiationException, IllegalAccessException {
      Class<?> cls = desc.getPropertyEditorClass();
      return null != cls ? (PropertyEditor)cls.newInstance() : this.getPropertyEditorValue(desc.getPropertyType());
   }
}
