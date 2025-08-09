package org.apache.spark.ml.util;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkIllegalArgumentException;
import org.apache.spark.ml.attribute.AttributeGroup;
import org.apache.spark.ml.attribute.NominalAttribute;
import org.apache.spark.ml.attribute.NominalAttribute$;
import org.apache.spark.ml.attribute.NumericAttribute;
import org.apache.spark.ml.attribute.NumericAttribute$;
import org.apache.spark.ml.linalg.VectorUDT;
import org.apache.spark.sql.catalyst.trees.Origin;
import org.apache.spark.sql.types.ArrayType;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.Metadata;
import org.apache.spark.sql.types.MetadataBuilder;
import org.apache.spark.sql.types.NumericType;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import scala.Function2;
import scala.Option;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.runtime.BoxesRunTime;

public final class SchemaUtils$ {
   public static final SchemaUtils$ MODULE$ = new SchemaUtils$();

   public void checkColumnType(final StructType schema, final String colName, final DataType dataType, final String msg) {
      DataType actualDataType = this.getSchemaField(schema, colName).dataType();
      String message = msg != null && msg.trim().length() > 0 ? " " + msg : "";
      .MODULE$.require(actualDataType.equals(dataType), () -> "Column " + colName + " must be of type " + dataType.getClass() + ":" + dataType.catalogString() + " but was actually " + actualDataType.getClass() + ":" + actualDataType.catalogString() + "." + message);
   }

   public String checkColumnType$default$4() {
      return "";
   }

   public void checkColumnTypes(final StructType schema, final String colName, final Seq dataTypes, final String msg) {
      DataType actualDataType = schema.apply(colName).dataType();
      String message = msg != null && msg.trim().length() > 0 ? " " + msg : "";
      .MODULE$.require(dataTypes.exists((x$1) -> BoxesRunTime.boxToBoolean($anonfun$checkColumnTypes$1(actualDataType, x$1))), () -> "Column " + colName + " must be of type equal to one of the following types: " + ((IterableOnceOps)dataTypes.map((x$1) -> x$1.catalogString())).mkString("[", ", ", "]") + " but was actually of type " + actualDataType.catalogString() + "." + message);
   }

   public String checkColumnTypes$default$4() {
      return "";
   }

   public void checkNumericType(final StructType schema, final String colName, final String msg) {
      DataType actualDataType = this.getSchemaFieldType(schema, colName);
      String message = msg != null && msg.trim().length() > 0 ? " " + msg : "";
      .MODULE$.require(actualDataType instanceof NumericType, () -> "Column " + colName + " must be of type " + org.apache.spark.sql.types.NumericType..MODULE$.simpleString() + " but was actually of type " + actualDataType.catalogString() + "." + message);
   }

   public String checkNumericType$default$3() {
      return "";
   }

   public StructType appendColumn(final StructType schema, final String colName, final DataType dataType, final boolean nullable) {
      return colName.isEmpty() ? schema : this.appendColumn(schema, new StructField(colName, dataType, nullable, org.apache.spark.sql.types.StructField..MODULE$.apply$default$4()));
   }

   public StructType appendColumn(final StructType schema, final StructField col) {
      .MODULE$.require(!scala.collection.ArrayOps..MODULE$.contains$extension(.MODULE$.refArrayOps((Object[])schema.fieldNames()), col.name()), () -> "Column " + col.name() + " already exists.");
      return new StructType((StructField[])scala.collection.ArrayOps..MODULE$.$colon$plus$extension(.MODULE$.refArrayOps((Object[])schema.fields()), col, scala.reflect.ClassTag..MODULE$.apply(StructField.class)));
   }

   public boolean appendColumn$default$4() {
      return false;
   }

   public StructType updateAttributeGroupSize(final StructType schema, final String colName, final int size) {
      .MODULE$.require(size > 0);
      AttributeGroup attrGroup = new AttributeGroup(colName, size);
      StructField field = attrGroup.toStructField();
      return this.updateField(schema, field, true);
   }

   public StructType updateNumValues(final StructType schema, final String colName, final int numValues) {
      NominalAttribute attr = NominalAttribute$.MODULE$.defaultAttr().withName(colName).withNumValues(numValues);
      StructField field = attr.toStructField();
      return this.updateField(schema, field, true);
   }

   public StructType updateNumeric(final StructType schema, final String colName) {
      NumericAttribute attr = NumericAttribute$.MODULE$.defaultAttr().withName(colName);
      StructField field = attr.toStructField();
      return this.updateField(schema, field, true);
   }

   public StructType updateField(final StructType schema, final StructField field, final boolean overwriteMetadata) {
      if (scala.collection.ArrayOps..MODULE$.contains$extension(.MODULE$.refArrayOps((Object[])schema.fieldNames()), field.name())) {
         StructField[] newFields = (StructField[])scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.refArrayOps((Object[])schema.fields()), (f) -> {
            String var10000 = f.name();
            String var3 = field.name();
            if (var10000 == null) {
               if (var3 != null) {
                  return f;
               }
            } else if (!var10000.equals(var3)) {
               return f;
            }

            if (overwriteMetadata) {
               return field;
            } else {
               Metadata newMeta = (new MetadataBuilder()).withMetadata(field.metadata()).withMetadata(f.metadata()).build();
               return new StructField(field.name(), field.dataType(), field.nullable(), newMeta);
            }
         }, scala.reflect.ClassTag..MODULE$.apply(StructField.class));
         return new StructType(newFields);
      } else {
         return this.appendColumn(schema, field);
      }
   }

   public boolean updateField$default$3() {
      return true;
   }

   public void validateVectorCompatibleColumn(final StructType schema, final String colName) {
      List typeCandidates = new scala.collection.immutable..colon.colon(new VectorUDT(), new scala.collection.immutable..colon.colon(new ArrayType(org.apache.spark.sql.types.DoubleType..MODULE$, false), new scala.collection.immutable..colon.colon(new ArrayType(org.apache.spark.sql.types.FloatType..MODULE$, false), scala.collection.immutable.Nil..MODULE$)));
      this.checkColumnTypes(schema, colName, typeCandidates, this.checkColumnTypes$default$4());
   }

   public String toSQLId(final String parts) {
      return ((IterableOnceOps)org.apache.spark.sql.catalyst.util.AttributeNameParser..MODULE$.parseAttributeName(parts).map((name) -> org.apache.spark.sql.catalyst.util.QuotingUtils..MODULE$.quoteIdentifier(name))).mkString(".");
   }

   public StructField getSchemaField(final StructType schema, final String colName) {
      Seq colSplits = org.apache.spark.sql.catalyst.util.AttributeNameParser..MODULE$.parseAttributeName(colName);
      Function2 x$2 = org.apache.spark.sql.internal.SQLConf..MODULE$.get().resolver();
      boolean x$3 = schema.findNestedField$default$2();
      Origin x$4 = schema.findNestedField$default$4();
      Option fieldOpt = schema.findNestedField(colSplits, x$3, x$2, x$4);
      if (fieldOpt.isEmpty()) {
         throw new SparkIllegalArgumentException("FIELD_NOT_FOUND", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("fieldName"), this.toSQLId(colName)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("fields"), .MODULE$.wrapRefArray(scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.refArrayOps((Object[])schema.fields()), (f) -> MODULE$.toSQLId(f.name()), scala.reflect.ClassTag..MODULE$.apply(String.class))).mkString(", "))}))));
      } else {
         return (StructField)((Tuple2)fieldOpt.get())._2();
      }
   }

   public DataType getSchemaFieldType(final StructType schema, final String colName) {
      return this.getSchemaField(schema, colName).dataType();
   }

   public boolean checkSchemaFieldExist(final StructType schema, final String colName) {
      Seq colSplits = org.apache.spark.sql.catalyst.util.AttributeNameParser..MODULE$.parseAttributeName(colName);
      Function2 x$2 = org.apache.spark.sql.internal.SQLConf..MODULE$.get().resolver();
      boolean x$3 = schema.findNestedField$default$2();
      Origin x$4 = schema.findNestedField$default$4();
      Option fieldOpt = schema.findNestedField(colSplits, x$3, x$2, x$4);
      return fieldOpt.isDefined();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$checkColumnTypes$1(final DataType actualDataType$2, final Object x$1) {
      return actualDataType$2.equals(x$1);
   }

   private SchemaUtils$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
