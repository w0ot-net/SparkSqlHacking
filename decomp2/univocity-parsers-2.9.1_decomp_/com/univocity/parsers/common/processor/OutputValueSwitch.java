package com.univocity.parsers.common.processor;

import com.univocity.parsers.annotations.helpers.AnnotationHelper;
import com.univocity.parsers.annotations.helpers.MethodFilter;
import com.univocity.parsers.common.ArgumentUtils;
import com.univocity.parsers.common.NormalizedString;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class OutputValueSwitch extends RowWriterProcessorSwitch {
   private Switch defaultSwitch;
   private Switch[] switches;
   private Switch selectedSwitch;
   private Class[] types;
   private final int columnIndex;
   private final String headerName;
   private Comparator comparator;

   public OutputValueSwitch() {
      this(0);
   }

   public OutputValueSwitch(int columnIndex) {
      this.switches = new Switch[0];
      this.types = new Class[0];
      this.comparator = new Comparator() {
         public int compare(Object o1, Object o2) {
            return o1 == o2 ? 0 : (o1 != null && o1.equals(o2) ? 0 : 1);
         }
      };
      this.columnIndex = this.getValidatedIndex(columnIndex);
      this.headerName = null;
   }

   public OutputValueSwitch(String headerName) {
      this.switches = new Switch[0];
      this.types = new Class[0];
      this.comparator = new Comparator() {
         public int compare(Object o1, Object o2) {
            return o1 == o2 ? 0 : (o1 != null && o1.equals(o2) ? 0 : 1);
         }
      };
      this.headerName = this.getValidatedHeaderName(headerName);
      this.columnIndex = 0;
   }

   public OutputValueSwitch(String headerName, int columnIndex) {
      this.switches = new Switch[0];
      this.types = new Class[0];
      this.comparator = new Comparator() {
         public int compare(Object o1, Object o2) {
            return o1 == o2 ? 0 : (o1 != null && o1.equals(o2) ? 0 : 1);
         }
      };
      this.columnIndex = this.getValidatedIndex(columnIndex);
      this.headerName = this.getValidatedHeaderName(headerName);
   }

   private int getValidatedIndex(int columnIndex) {
      if (columnIndex < 0) {
         throw new IllegalArgumentException("Column index must be positive");
      } else {
         return columnIndex;
      }
   }

   private String getValidatedHeaderName(String headerName) {
      if (headerName != null && headerName.trim().length() != 0) {
         return headerName;
      } else {
         throw new IllegalArgumentException("Header name cannot be blank");
      }
   }

   public void setComparator(Comparator comparator) {
      if (comparator == null) {
         throw new IllegalArgumentException("Comparator must not be null");
      } else {
         this.comparator = comparator;
      }
   }

   public void setDefaultSwitch(RowWriterProcessor rowProcessor, String... headersToUse) {
      this.defaultSwitch = new Switch(rowProcessor, headersToUse, (int[])null, (Object)null);
   }

   public void setDefaultSwitch(RowWriterProcessor rowProcessor, int... indexesToUse) {
      this.defaultSwitch = new Switch(rowProcessor, (String[])null, indexesToUse, (Object)null);
   }

   protected NormalizedString[] getHeaders() {
      return this.selectedSwitch != null ? this.selectedSwitch.headers : null;
   }

   protected int[] getIndexes() {
      return this.selectedSwitch != null ? this.selectedSwitch.indexes : null;
   }

   private Switch getSwitch(Object value) {
      if (value instanceof Object[]) {
         Object[] row = value;
         if (row.length < this.columnIndex) {
            return this.defaultSwitch;
         }

         value = row[this.columnIndex];
      }

      for(int i = 0; i < this.switches.length; ++i) {
         Switch s = this.switches[i];
         Class type = this.types[i];
         if (type != null) {
            if (type.isAssignableFrom(value.getClass())) {
               return s;
            }
         } else if (this.comparator.compare(value, s.value) == 0) {
            return s;
         }
      }

      return this.defaultSwitch;
   }

   protected RowWriterProcessor switchRowProcessor(Object row) {
      this.selectedSwitch = this.getSwitch(row);
      return this.selectedSwitch != null ? this.selectedSwitch.processor : null;
   }

   public void addSwitchForValue(Object value, RowWriterProcessor rowProcessor, String... headersToUse) {
      this.addSwitch(new Switch(rowProcessor, headersToUse, (int[])null, value));
   }

   public void addSwitchForValue(Object value, RowWriterProcessor rowProcessor) {
      this.addSwitch(new Switch(rowProcessor, (String[])null, (int[])null, value));
   }

   public void addSwitchForValue(Object value, RowWriterProcessor rowProcessor, int... indexesToUse) {
      this.addSwitch(new Switch(rowProcessor, (String[])null, indexesToUse, value));
   }

   public void addSwitchForType(Class beanType, String... headersToUse) {
      this.addSwitch(new Switch(headersToUse, (int[])null, beanType));
   }

   public void addSwitchForType(Class beanType, int... indexesToUse) {
      this.addSwitch(new Switch((String[])null, indexesToUse, beanType));
   }

   public void addSwitchForType(Class beanType) {
      this.addSwitch(new Switch((String[])null, (int[])null, beanType));
   }

   private void addSwitch(Switch newSwitch) {
      this.switches = (Switch[])Arrays.copyOf(this.switches, this.switches.length + 1);
      this.switches[this.switches.length - 1] = newSwitch;
      this.types = (Class[])Arrays.copyOf(this.types, this.types.length + 1);
      if (newSwitch.value != null && newSwitch.value.getClass() == Class.class) {
         this.types[this.types.length - 1] = (Class)newSwitch.value;
      }

   }

   private Object getValue(Map map, int index) {
      int i = 0;

      for(Map.Entry e : map.entrySet()) {
         if (i == index) {
            return e.getValue();
         }
      }

      return null;
   }

   private NormalizedString[] getHeadersFromSwitch(Object value) {
      for(int i = 0; i < this.switches.length; ++i) {
         Switch s = this.getSwitch(value);
         if (s != null) {
            return s.headers;
         }
      }

      return null;
   }

   public NormalizedString[] getHeaders(Object input) {
      if (input instanceof Object[]) {
         Object[] row = input;
         return this.columnIndex < row.length ? this.getHeadersFromSwitch(row[this.columnIndex]) : null;
      } else {
         return this.getHeadersFromSwitch(input);
      }
   }

   public NormalizedString[] getHeaders(Map headerMapping, Map mapInput) {
      Object mapValue = null;
      if (mapInput != null && !mapInput.isEmpty()) {
         String headerToUse = this.headerName;
         if (headerMapping != null) {
            if (this.headerName != null) {
               Object value = headerMapping.get(this.headerName);
               headerToUse = value == null ? null : value.toString();
            } else if (this.columnIndex != -1) {
               Object value = this.getValue(headerMapping, this.columnIndex);
               headerToUse = value == null ? null : value.toString();
            }
         }

         if (headerToUse != null) {
            mapValue = mapInput.get(headerToUse);
         } else {
            mapValue = this.getValue(mapInput, this.columnIndex);
         }
      }

      return this.getHeadersFromSwitch(mapValue);
   }

   public int getColumnIndex() {
      return this.columnIndex;
   }

   private List getSwitchValues() {
      List<Object> values = new ArrayList(this.switches.length);

      for(Switch s : this.switches) {
         values.add(s.value);
      }

      return values;
   }

   protected String describeSwitch() {
      return "Expecting one of values: " + this.getSwitchValues() + " at column index " + this.getColumnIndex();
   }

   private static class Switch {
      final RowWriterProcessor processor;
      final NormalizedString[] headers;
      final int[] indexes;
      final Object value;

      Switch(RowWriterProcessor processor, String[] headers, int[] indexes, Object value) {
         this(processor, headers, indexes, value, (Class)null);
      }

      Switch(String[] headers, int[] indexes, Class type) {
         this((RowWriterProcessor)null, headers, indexes, type, type);
      }

      private Switch(RowWriterProcessor processor, String[] headers, int[] indexes, Object value, Class type) {
         if (type != null) {
            processor = new BeanWriterProcessor(type);
            if (headers == null && indexes == null) {
               headers = AnnotationHelper.deriveHeaderNamesFromFields(type, MethodFilter.ONLY_GETTERS);
               indexes = ArgumentUtils.toIntArray(Arrays.asList(AnnotationHelper.getSelectedIndexes(type, MethodFilter.ONLY_GETTERS)));
            }
         }

         this.processor = processor;
         this.headers = headers != null && headers.length != 0 ? NormalizedString.toIdentifierGroupArray(headers) : null;
         this.indexes = indexes != null && indexes.length != 0 ? indexes : null;
         this.value = value;
      }
   }
}
