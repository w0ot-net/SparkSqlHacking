package org.apache.hadoop.hive.serde2.lazy.objectinspector;

import java.util.List;
import java.util.Map;
import org.apache.hadoop.hive.serde2.lazy.LazyMap;
import org.apache.hadoop.hive.serde2.lazy.objectinspector.primitive.LazyObjectInspectorParameters;
import org.apache.hadoop.hive.serde2.lazy.objectinspector.primitive.LazyObjectInspectorParametersImpl;
import org.apache.hadoop.hive.serde2.objectinspector.MapObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.io.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LazyMapObjectInspector implements MapObjectInspector {
   public static final Logger LOG = LoggerFactory.getLogger(LazyMapObjectInspector.class.getName());
   private ObjectInspector mapKeyObjectInspector;
   private ObjectInspector mapValueObjectInspector;
   private byte itemSeparator;
   private byte keyValueSeparator;
   private LazyObjectInspectorParameters lazyParams;

   protected LazyMapObjectInspector() {
      this.lazyParams = new LazyObjectInspectorParametersImpl();
   }

   protected LazyMapObjectInspector(ObjectInspector mapKeyObjectInspector, ObjectInspector mapValueObjectInspector, byte itemSeparator, byte keyValueSeparator, Text nullSequence, boolean escaped, byte escapeChar) {
      this.mapKeyObjectInspector = mapKeyObjectInspector;
      this.mapValueObjectInspector = mapValueObjectInspector;
      this.itemSeparator = itemSeparator;
      this.keyValueSeparator = keyValueSeparator;
      this.lazyParams = new LazyObjectInspectorParametersImpl(escaped, escapeChar, false, (List)null, (byte[])null, nullSequence);
   }

   protected LazyMapObjectInspector(ObjectInspector mapKeyObjectInspector, ObjectInspector mapValueObjectInspector, byte itemSeparator, byte keyValueSeparator, LazyObjectInspectorParameters lazyParams) {
      this.mapKeyObjectInspector = mapKeyObjectInspector;
      this.mapValueObjectInspector = mapValueObjectInspector;
      this.itemSeparator = itemSeparator;
      this.keyValueSeparator = keyValueSeparator;
      this.lazyParams = lazyParams;
   }

   public final ObjectInspector.Category getCategory() {
      return ObjectInspector.Category.MAP;
   }

   public String getTypeName() {
      return "map<" + this.mapKeyObjectInspector.getTypeName() + "," + this.mapValueObjectInspector.getTypeName() + ">";
   }

   public ObjectInspector getMapKeyObjectInspector() {
      return this.mapKeyObjectInspector;
   }

   public ObjectInspector getMapValueObjectInspector() {
      return this.mapValueObjectInspector;
   }

   public Object getMapValueElement(Object data, Object key) {
      return data != null && key != null ? ((LazyMap)data).getMapValueElement(key) : null;
   }

   public Map getMap(Object data) {
      return data == null ? null : ((LazyMap)data).getMap();
   }

   public int getMapSize(Object data) {
      return data == null ? -1 : ((LazyMap)data).getMapSize();
   }

   public byte getItemSeparator() {
      return this.itemSeparator;
   }

   public byte getKeyValueSeparator() {
      return this.keyValueSeparator;
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
