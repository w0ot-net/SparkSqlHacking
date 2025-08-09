package org.apache.hadoop.hive.serde2.objectinspector;

import java.util.ArrayList;
import java.util.List;

public class StandardUnionObjectInspector extends SettableUnionObjectInspector {
   private List ois;

   protected StandardUnionObjectInspector() {
   }

   public StandardUnionObjectInspector(List ois) {
      this.ois = ois;
   }

   public List getObjectInspectors() {
      return this.ois;
   }

   public byte getTag(Object o) {
      return o == null ? -1 : ((UnionObject)o).getTag();
   }

   public Object getField(Object o) {
      return o == null ? null : ((UnionObject)o).getObject();
   }

   public ObjectInspector.Category getCategory() {
      return ObjectInspector.Category.UNION;
   }

   public String getTypeName() {
      return ObjectInspectorUtils.getStandardUnionTypeName(this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append(this.getClass().getName());
      sb.append(this.getTypeName());
      return sb.toString();
   }

   public Object create() {
      ArrayList<Object> a = new ArrayList();
      return a;
   }

   public Object addField(Object union, Object field) {
      ArrayList<Object> a = (ArrayList)union;
      a.add(field);
      return a;
   }

   public static class StandardUnion implements UnionObject {
      protected byte tag;
      protected Object object;

      public StandardUnion() {
      }

      public StandardUnion(byte tag, Object object) {
         this.tag = tag;
         this.object = object;
      }

      public void setObject(Object o) {
         this.object = o;
      }

      public void setTag(byte tag) {
         this.tag = tag;
      }

      public Object getObject() {
         return this.object;
      }

      public byte getTag() {
         return this.tag;
      }

      public String toString() {
         return this.tag + ":" + this.object;
      }
   }
}
