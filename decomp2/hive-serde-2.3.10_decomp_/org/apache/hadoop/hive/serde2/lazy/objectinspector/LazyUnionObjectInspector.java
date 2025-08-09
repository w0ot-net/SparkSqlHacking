package org.apache.hadoop.hive.serde2.lazy.objectinspector;

import java.util.ArrayList;
import java.util.List;
import org.apache.hadoop.hive.serde2.lazy.LazyUnion;
import org.apache.hadoop.hive.serde2.lazy.objectinspector.primitive.LazyObjectInspectorParameters;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorUtils;
import org.apache.hadoop.hive.serde2.objectinspector.UnionObjectInspector;
import org.apache.hadoop.io.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LazyUnionObjectInspector implements UnionObjectInspector {
   public static final Logger LOG = LoggerFactory.getLogger(LazyUnionObjectInspector.class.getName());
   private List ois;
   private byte separator;
   private LazyObjectInspectorParameters lazyParams;

   protected LazyUnionObjectInspector() {
   }

   protected LazyUnionObjectInspector(List ois, byte separator, LazyObjectInspectorParameters lazyParams) {
      this.init(ois, separator, lazyParams);
   }

   public String getTypeName() {
      return ObjectInspectorUtils.getStandardUnionTypeName(this);
   }

   protected void init(List ois, byte separator, LazyObjectInspectorParameters lazyParams) {
      this.separator = separator;
      this.lazyParams = lazyParams;
      this.ois = new ArrayList();
      this.ois.addAll(ois);
   }

   public final ObjectInspector.Category getCategory() {
      return ObjectInspector.Category.UNION;
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

   public Object getField(Object data) {
      return data == null ? null : ((LazyUnion)data).getField();
   }

   public List getObjectInspectors() {
      return this.ois;
   }

   public byte getTag(Object data) {
      return data == null ? -1 : ((LazyUnion)data).getTag();
   }
}
