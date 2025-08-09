package org.apache.hadoop.hive.serde2.lazy.objectinspector;

import java.util.List;
import org.apache.hadoop.hive.serde2.lazy.LazyArray;
import org.apache.hadoop.hive.serde2.lazy.objectinspector.primitive.LazyObjectInspectorParameters;
import org.apache.hadoop.hive.serde2.objectinspector.ListObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.io.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LazyListObjectInspector implements ListObjectInspector {
   public static final Logger LOG = LoggerFactory.getLogger(LazyListObjectInspector.class.getName());
   private ObjectInspector listElementObjectInspector;
   private byte separator;
   private LazyObjectInspectorParameters lazyParams;

   protected LazyListObjectInspector() {
   }

   protected LazyListObjectInspector(ObjectInspector listElementObjectInspector, byte separator, LazyObjectInspectorParameters lazyParams) {
      this.listElementObjectInspector = listElementObjectInspector;
      this.separator = separator;
      this.lazyParams = lazyParams;
   }

   public final ObjectInspector.Category getCategory() {
      return ObjectInspector.Category.LIST;
   }

   public ObjectInspector getListElementObjectInspector() {
      return this.listElementObjectInspector;
   }

   public Object getListElement(Object data, int index) {
      if (data == null) {
         return null;
      } else {
         LazyArray array = (LazyArray)data;
         return array.getListElementObject(index);
      }
   }

   public int getListLength(Object data) {
      if (data == null) {
         return -1;
      } else {
         LazyArray array = (LazyArray)data;
         return array.getListLength();
      }
   }

   public List getList(Object data) {
      if (data == null) {
         return null;
      } else {
         LazyArray array = (LazyArray)data;
         return array.getList();
      }
   }

   public String getTypeName() {
      return "array<" + this.listElementObjectInspector.getTypeName() + ">";
   }

   public byte getSeparator() {
      return this.separator;
   }

   public Text getNullSequence() {
      return this.lazyParams.getNullSequence();
   }

   public boolean isEscaped() {
      return this.lazyParams.isEscaped();
   }

   public byte getEscapeChar() {
      return this.lazyParams.getEscapeChar();
   }

   public LazyObjectInspectorParameters getLazyParams() {
      return this.lazyParams;
   }
}
