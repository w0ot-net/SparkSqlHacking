package org.apache.hive.service.cli;

import java.util.HashMap;
import java.util.Map;
import org.apache.hadoop.hive.serde2.typeinfo.CharTypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.DecimalTypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.PrimitiveTypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.VarcharTypeInfo;
import org.apache.hive.service.rpc.thrift.TTypeQualifierValue;
import org.apache.hive.service.rpc.thrift.TTypeQualifiers;

public class TypeQualifiers {
   private Integer characterMaximumLength;
   private Integer precision;
   private Integer scale;

   public Integer getCharacterMaximumLength() {
      return this.characterMaximumLength;
   }

   public void setCharacterMaximumLength(int characterMaximumLength) {
      this.characterMaximumLength = characterMaximumLength;
   }

   public TTypeQualifiers toTTypeQualifiers() {
      TTypeQualifiers ret = null;
      Map<String, TTypeQualifierValue> qMap = new HashMap();
      if (this.getCharacterMaximumLength() != null) {
         TTypeQualifierValue val = new TTypeQualifierValue();
         val.setI32Value(this.getCharacterMaximumLength());
         qMap.put("characterMaximumLength", val);
      }

      if (this.precision != null) {
         TTypeQualifierValue val = new TTypeQualifierValue();
         val.setI32Value(this.precision);
         qMap.put("precision", val);
      }

      if (this.scale != null) {
         TTypeQualifierValue val = new TTypeQualifierValue();
         val.setI32Value(this.scale);
         qMap.put("scale", val);
      }

      if (qMap.size() > 0) {
         ret = new TTypeQualifiers(qMap);
      }

      return ret;
   }

   public static TypeQualifiers fromTTypeQualifiers(TTypeQualifiers ttq) {
      TypeQualifiers ret = null;
      if (ttq != null) {
         ret = new TypeQualifiers();
         Map<String, TTypeQualifierValue> tqMap = ttq.getQualifiers();
         if (tqMap.containsKey("characterMaximumLength")) {
            ret.setCharacterMaximumLength(((TTypeQualifierValue)tqMap.get("characterMaximumLength")).getI32Value());
         }

         if (tqMap.containsKey("precision")) {
            ret.setPrecision(((TTypeQualifierValue)tqMap.get("precision")).getI32Value());
         }

         if (tqMap.containsKey("scale")) {
            ret.setScale(((TTypeQualifierValue)tqMap.get("scale")).getI32Value());
         }
      }

      return ret;
   }

   public static TypeQualifiers fromTypeInfo(PrimitiveTypeInfo pti) {
      TypeQualifiers result = null;
      if (pti instanceof VarcharTypeInfo) {
         result = new TypeQualifiers();
         result.setCharacterMaximumLength(((VarcharTypeInfo)pti).getLength());
      } else if (pti instanceof CharTypeInfo) {
         result = new TypeQualifiers();
         result.setCharacterMaximumLength(((CharTypeInfo)pti).getLength());
      } else if (pti instanceof DecimalTypeInfo) {
         result = new TypeQualifiers();
         result.setPrecision(((DecimalTypeInfo)pti).precision());
         result.setScale(((DecimalTypeInfo)pti).scale());
      }

      return result;
   }

   public Integer getPrecision() {
      return this.precision;
   }

   public void setPrecision(Integer precision) {
      this.precision = precision;
   }

   public Integer getScale() {
      return this.scale;
   }

   public void setScale(Integer scale) {
      this.scale = scale;
   }
}
