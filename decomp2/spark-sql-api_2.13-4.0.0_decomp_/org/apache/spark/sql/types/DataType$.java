package org.apache.spark.sql.types;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkIllegalArgumentException;
import org.apache.spark.SparkThrowable;
import org.apache.spark.annotation.Stable;
import org.apache.spark.sql.catalyst.parser.DataTypeParser$;
import org.apache.spark.sql.catalyst.util.CollationFactory;
import org.apache.spark.sql.catalyst.util.StringConcat;
import org.apache.spark.sql.errors.DataTypeErrors$;
import org.json4s.JArray;
import org.json4s.JBool;
import org.json4s.JObject;
import org.json4s.JString;
import org.json4s.JValue;
import scala.Array;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.collection.IterableOnceOps;
import scala.collection.LinearSeqOps;
import scala.collection.StringOps.;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.util.matching.Regex;

@Stable
public final class DataType$ {
   public static final DataType$ MODULE$ = new DataType$();
   private static final Regex FIXED_DECIMAL;
   private static final Regex CHAR_TYPE;
   private static final Regex VARCHAR_TYPE;
   private static final String COLLATIONS_METADATA_KEY;
   private static final Map otherTypes;

   static {
      FIXED_DECIMAL = .MODULE$.r$extension(scala.Predef..MODULE$.augmentString("decimal\\(\\s*(\\d+)\\s*,\\s*(\\-?\\d+)\\s*\\)"));
      CHAR_TYPE = .MODULE$.r$extension(scala.Predef..MODULE$.augmentString("char\\(\\s*(\\d+)\\s*\\)"));
      VARCHAR_TYPE = .MODULE$.r$extension(scala.Predef..MODULE$.augmentString("varchar\\(\\s*(\\d+)\\s*\\)"));
      COLLATIONS_METADATA_KEY = "__COLLATIONS";
      otherTypes = ((IterableOnceOps)scala.package..MODULE$.Seq().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new DataType[]{NullType$.MODULE$, DateType$.MODULE$, TimestampType$.MODULE$, BinaryType$.MODULE$, IntegerType$.MODULE$, BooleanType$.MODULE$, LongType$.MODULE$, DoubleType$.MODULE$, FloatType$.MODULE$, ShortType$.MODULE$, ByteType$.MODULE$, StringType$.MODULE$, CalendarIntervalType$.MODULE$, DayTimeIntervalType$.MODULE$.apply(DayTimeIntervalType$.MODULE$.DAY()), new DayTimeIntervalType(DayTimeIntervalType$.MODULE$.DAY(), DayTimeIntervalType$.MODULE$.HOUR()), new DayTimeIntervalType(DayTimeIntervalType$.MODULE$.DAY(), DayTimeIntervalType$.MODULE$.MINUTE()), new DayTimeIntervalType(DayTimeIntervalType$.MODULE$.DAY(), DayTimeIntervalType$.MODULE$.SECOND()), DayTimeIntervalType$.MODULE$.apply(DayTimeIntervalType$.MODULE$.HOUR()), new DayTimeIntervalType(DayTimeIntervalType$.MODULE$.HOUR(), DayTimeIntervalType$.MODULE$.MINUTE()), new DayTimeIntervalType(DayTimeIntervalType$.MODULE$.HOUR(), DayTimeIntervalType$.MODULE$.SECOND()), DayTimeIntervalType$.MODULE$.apply(DayTimeIntervalType$.MODULE$.MINUTE()), new DayTimeIntervalType(DayTimeIntervalType$.MODULE$.MINUTE(), DayTimeIntervalType$.MODULE$.SECOND()), DayTimeIntervalType$.MODULE$.apply(DayTimeIntervalType$.MODULE$.SECOND()), YearMonthIntervalType$.MODULE$.apply(YearMonthIntervalType$.MODULE$.YEAR()), YearMonthIntervalType$.MODULE$.apply(YearMonthIntervalType$.MODULE$.MONTH()), new YearMonthIntervalType(YearMonthIntervalType$.MODULE$.YEAR(), YearMonthIntervalType$.MODULE$.MONTH()), TimestampNTZType$.MODULE$, VariantType$.MODULE$})).map((t) -> scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(t.typeName()), t))).toMap(scala..less.colon.less..MODULE$.refl());
   }

   private Regex FIXED_DECIMAL() {
      return FIXED_DECIMAL;
   }

   private Regex CHAR_TYPE() {
      return CHAR_TYPE;
   }

   private Regex VARCHAR_TYPE() {
      return VARCHAR_TYPE;
   }

   public String COLLATIONS_METADATA_KEY() {
      return COLLATIONS_METADATA_KEY;
   }

   public DataType fromDDL(final String ddl) {
      return this.parseTypeWithFallback(ddl, (sqlText) -> DataTypeParser$.MODULE$.parseDataType(sqlText), (str) -> DataTypeParser$.MODULE$.parseTableSchema(str));
   }

   public DataType parseTypeWithFallback(final String schema, final Function1 parser, final Function1 fallbackParser) {
      DataType var10000;
      try {
         var10000 = (DataType)parser.apply(schema);
      } catch (Throwable var13) {
         if (var13 == null || !scala.util.control.NonFatal..MODULE$.apply(var13)) {
            throw var13;
         }

         try {
            var10000 = (DataType)fallbackParser.apply(schema);
         } catch (Throwable var12) {
            if (var12 != null && scala.util.control.NonFatal..MODULE$.apply(var12)) {
               var13.addSuppressed(var12);
               if (var13 instanceof SparkThrowable) {
                  throw var13;
               }

               throw DataTypeErrors$.MODULE$.schemaFailToParseError(schema, var13);
            }

            throw var12;
         }
      }

      return var10000;
   }

   public DataType fromJson(final String json) {
      return this.parseDataType(org.json4s.jackson.JsonMethods..MODULE$.parse(json, org.json4s.jackson.JsonMethods..MODULE$.parse$default$2(), org.json4s.jackson.JsonMethods..MODULE$.parse$default$3(), org.json4s.AsJsonInput..MODULE$.stringAsJsonInput()), this.parseDataType$default$2(), this.parseDataType$default$3());
   }

   private Map otherTypes() {
      return otherTypes;
   }

   private DataType nameToType(final String name) {
      if ("decimal".equals(name)) {
         return DecimalType$.MODULE$.USER_DEFAULT();
      } else {
         if (name != null) {
            Option var4 = this.FIXED_DECIMAL().unapplySeq(name);
            if (!var4.isEmpty() && var4.get() != null && ((List)var4.get()).lengthCompare(2) == 0) {
               String precision = (String)((LinearSeqOps)var4.get()).apply(0);
               String scale = (String)((LinearSeqOps)var4.get()).apply(1);
               return new DecimalType(.MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(precision)), .MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(scale)));
            }
         }

         if (name != null) {
            Option var7 = this.CHAR_TYPE().unapplySeq(name);
            if (!var7.isEmpty() && var7.get() != null && ((List)var7.get()).lengthCompare(1) == 0) {
               String length = (String)((LinearSeqOps)var7.get()).apply(0);
               return new CharType(.MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(length)));
            }
         }

         if (name != null) {
            Option var9 = this.VARCHAR_TYPE().unapplySeq(name);
            if (!var9.isEmpty() && var9.get() != null && ((List)var9.get()).lengthCompare(1) == 0) {
               String length = (String)((LinearSeqOps)var9.get()).apply(0);
               return new VarcharType(.MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(length)));
            }
         }

         if ("null".equals(name)) {
            return NullType$.MODULE$;
         } else {
            return (DataType)("timestamp_ltz".equals(name) ? TimestampType$.MODULE$ : (DataType)this.otherTypes().getOrElse(name, () -> {
               throw new SparkIllegalArgumentException("INVALID_JSON_DATA_TYPE", (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("invalidType"), name)}))));
            }));
         }
      }
   }

   public DataType parseDataType(final JValue json, final String fieldPath, final Map collationsMap) {
      if (json instanceof JString var7) {
         String name = var7.s();
         Option var9 = collationsMap.get(fieldPath);
         if (var9 instanceof Some var10) {
            String collation = (String)var10.value();
            this.assertValidTypeForCollations(fieldPath, name, collationsMap);
            return this.stringTypeWithCollation(collation);
         } else {
            return this.nameToType(name);
         }
      } else {
         if (json != null) {
            Option var12 = DataType.JSortedObject$.MODULE$.unapplySeq(json);
            if (!var12.isEmpty() && var12.get() != null && ((List)var12.get()).lengthCompare(3) == 0) {
               Tuple2 var13 = (Tuple2)((LinearSeqOps)var12.get()).apply(0);
               Tuple2 var14 = (Tuple2)((LinearSeqOps)var12.get()).apply(1);
               Tuple2 var15 = (Tuple2)((LinearSeqOps)var12.get()).apply(2);
               if (var13 != null) {
                  String var16 = (String)var13._1();
                  JValue var17 = (JValue)var13._2();
                  if ("containsNull".equals(var16) && var17 instanceof JBool) {
                     JBool var18 = (JBool)var17;
                     boolean n = var18.value();
                     if (var14 != null) {
                        String var20 = (String)var14._1();
                        JValue t = (JValue)var14._2();
                        if ("elementType".equals(var20) && t != null && var15 != null) {
                           String var23 = (String)var15._1();
                           JValue var24 = (JValue)var15._2();
                           if ("type".equals(var23) && var24 instanceof JString) {
                              JString var25 = (JString)var24;
                              String var26 = var25.s();
                              if ("array".equals(var26)) {
                                 this.assertValidTypeForCollations(fieldPath, "array", collationsMap);
                                 DataType elementType = this.parseDataType(t, this.appendFieldToPath(fieldPath, "element"), collationsMap);
                                 return new ArrayType(elementType, n);
                              }
                           }
                        }
                     }
                  }
               }
            }
         }

         if (json != null) {
            Option var28 = DataType.JSortedObject$.MODULE$.unapplySeq(json);
            if (!var28.isEmpty() && var28.get() != null && ((List)var28.get()).lengthCompare(4) == 0) {
               Tuple2 var29 = (Tuple2)((LinearSeqOps)var28.get()).apply(0);
               Tuple2 var30 = (Tuple2)((LinearSeqOps)var28.get()).apply(1);
               Tuple2 var31 = (Tuple2)((LinearSeqOps)var28.get()).apply(2);
               Tuple2 var32 = (Tuple2)((LinearSeqOps)var28.get()).apply(3);
               if (var29 != null) {
                  String var33 = (String)var29._1();
                  JValue k = (JValue)var29._2();
                  if ("keyType".equals(var33) && k != null && var30 != null) {
                     String var36 = (String)var30._1();
                     JValue var37 = (JValue)var30._2();
                     if ("type".equals(var36) && var37 instanceof JString) {
                        JString var38 = (JString)var37;
                        String var39 = var38.s();
                        if ("map".equals(var39) && var31 != null) {
                           String var40 = (String)var31._1();
                           JValue var41 = (JValue)var31._2();
                           if ("valueContainsNull".equals(var40) && var41 instanceof JBool) {
                              JBool var42 = (JBool)var41;
                              boolean n = var42.value();
                              if (var32 != null) {
                                 String var44 = (String)var32._1();
                                 JValue v = (JValue)var32._2();
                                 if ("valueType".equals(var44) && v != null) {
                                    this.assertValidTypeForCollations(fieldPath, "map", collationsMap);
                                    DataType keyType = this.parseDataType(k, this.appendFieldToPath(fieldPath, "key"), collationsMap);
                                    DataType valueType = this.parseDataType(v, this.appendFieldToPath(fieldPath, "value"), collationsMap);
                                    return new MapType(keyType, valueType, n);
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            }
         }

         if (json != null) {
            Option var49 = DataType.JSortedObject$.MODULE$.unapplySeq(json);
            if (!var49.isEmpty() && var49.get() != null && ((List)var49.get()).lengthCompare(2) == 0) {
               Tuple2 var50 = (Tuple2)((LinearSeqOps)var49.get()).apply(0);
               Tuple2 var51 = (Tuple2)((LinearSeqOps)var49.get()).apply(1);
               if (var50 != null) {
                  String var52 = (String)var50._1();
                  JValue var53 = (JValue)var50._2();
                  if ("fields".equals(var52) && var53 instanceof JArray) {
                     JArray var54 = (JArray)var53;
                     List fields = var54.arr();
                     if (var51 != null) {
                        String var56 = (String)var51._1();
                        JValue var57 = (JValue)var51._2();
                        if ("type".equals(var56) && var57 instanceof JString) {
                           JString var58 = (JString)var57;
                           String var59 = var58.s();
                           if ("struct".equals(var59)) {
                              this.assertValidTypeForCollations(fieldPath, "struct", collationsMap);
                              return StructType$.MODULE$.apply((Seq)fields.map((jsonx) -> MODULE$.parseStructField(jsonx)));
                           }
                        }
                     }
                  }
               }
            }
         }

         if (json != null) {
            Option var60 = DataType.JSortedObject$.MODULE$.unapplySeq(json);
            if (!var60.isEmpty() && var60.get() != null && ((List)var60.get()).lengthCompare(4) == 0) {
               Tuple2 var61 = (Tuple2)((LinearSeqOps)var60.get()).apply(0);
               Tuple2 var62 = (Tuple2)((LinearSeqOps)var60.get()).apply(1);
               Tuple2 var63 = (Tuple2)((LinearSeqOps)var60.get()).apply(2);
               Tuple2 var64 = (Tuple2)((LinearSeqOps)var60.get()).apply(3);
               if (var61 != null) {
                  String var65 = (String)var61._1();
                  JValue var66 = (JValue)var61._2();
                  if ("class".equals(var65) && var66 instanceof JString) {
                     JString var67 = (JString)var66;
                     String udtClass = var67.s();
                     if (var62 != null) {
                        String var69 = (String)var62._1();
                        if ("pyClass".equals(var69) && var63 != null) {
                           String var70 = (String)var63._1();
                           if ("sqlType".equals(var70) && var64 != null) {
                              String var71 = (String)var64._1();
                              JValue var72 = (JValue)var64._2();
                              if ("type".equals(var71) && var72 instanceof JString) {
                                 JString var73 = (JString)var72;
                                 String var74 = var73.s();
                                 if ("udt".equals(var74)) {
                                    return (DataType)org.apache.spark.util.SparkClassUtils..MODULE$.classForName(udtClass, org.apache.spark.util.SparkClassUtils..MODULE$.classForName$default$2(), org.apache.spark.util.SparkClassUtils..MODULE$.classForName$default$3()).getConstructor().newInstance();
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            }
         }

         if (json != null) {
            Option var75 = DataType.JSortedObject$.MODULE$.unapplySeq(json);
            if (!var75.isEmpty() && var75.get() != null && ((List)var75.get()).lengthCompare(4) == 0) {
               Tuple2 var76 = (Tuple2)((LinearSeqOps)var75.get()).apply(0);
               Tuple2 var77 = (Tuple2)((LinearSeqOps)var75.get()).apply(1);
               Tuple2 var78 = (Tuple2)((LinearSeqOps)var75.get()).apply(2);
               Tuple2 var79 = (Tuple2)((LinearSeqOps)var75.get()).apply(3);
               if (var76 != null) {
                  String var80 = (String)var76._1();
                  JValue var81 = (JValue)var76._2();
                  if ("pyClass".equals(var80) && var81 instanceof JString) {
                     JString var82 = (JString)var81;
                     String pyClass = var82.s();
                     if (var77 != null) {
                        String var84 = (String)var77._1();
                        JValue var85 = (JValue)var77._2();
                        if ("serializedClass".equals(var84) && var85 instanceof JString) {
                           JString var86 = (JString)var85;
                           String serialized = var86.s();
                           if (var78 != null) {
                              String var88 = (String)var78._1();
                              JValue v = (JValue)var78._2();
                              if ("sqlType".equals(var88) && v != null && var79 != null) {
                                 String var91 = (String)var79._1();
                                 JValue var92 = (JValue)var79._2();
                                 if ("type".equals(var91) && var92 instanceof JString) {
                                    JString var93 = (JString)var92;
                                    String var94 = var93.s();
                                    if ("udt".equals(var94)) {
                                       return new PythonUserDefinedType(this.parseDataType(v, this.parseDataType$default$2(), this.parseDataType$default$3()), pyClass, serialized);
                                    }
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            }
         }

         throw new SparkIllegalArgumentException("INVALID_JSON_DATA_TYPE", (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("invalidType"), org.json4s.jackson.JsonMethods..MODULE$.compact(org.json4s.jackson.JsonMethods..MODULE$.render(json, org.json4s.jackson.JsonMethods..MODULE$.render$default$2(), org.json4s.jackson.JsonMethods..MODULE$.render$default$3())))}))));
      }
   }

   public String parseDataType$default$2() {
      return "";
   }

   public Map parseDataType$default$3() {
      return scala.Predef..MODULE$.Map().empty();
   }

   private StructField parseStructField(final JValue json) {
      if (json != null) {
         Option var4 = DataType.JSortedObject$.MODULE$.unapplySeq(json);
         if (!var4.isEmpty() && var4.get() != null && ((List)var4.get()).lengthCompare(4) == 0) {
            Tuple2 var5 = (Tuple2)((LinearSeqOps)var4.get()).apply(0);
            Tuple2 var6 = (Tuple2)((LinearSeqOps)var4.get()).apply(1);
            Tuple2 var7 = (Tuple2)((LinearSeqOps)var4.get()).apply(2);
            Tuple2 var8 = (Tuple2)((LinearSeqOps)var4.get()).apply(3);
            if (var5 != null) {
               String var9 = (String)var5._1();
               JValue var10 = (JValue)var5._2();
               if ("metadata".equals(var9) && var10 instanceof JObject) {
                  JObject var11 = (JObject)var10;
                  List metadataFields = var11.obj();
                  if (var6 != null) {
                     String var13 = (String)var6._1();
                     JValue var14 = (JValue)var6._2();
                     if ("name".equals(var13) && var14 instanceof JString) {
                        JString var15 = (JString)var14;
                        String name = var15.s();
                        if (var7 != null) {
                           String var17 = (String)var7._1();
                           JValue var18 = (JValue)var7._2();
                           if ("nullable".equals(var17) && var18 instanceof JBool) {
                              JBool var19 = (JBool)var18;
                              boolean nullable = var19.value();
                              if (var8 != null) {
                                 String var21 = (String)var8._1();
                                 JValue dataType = (JValue)var8._2();
                                 if ("type".equals(var21) && dataType != null) {
                                    Map collationsMap = this.getCollationsMap(metadataFields);
                                    JObject metadataWithoutCollations = new JObject(metadataFields.filterNot((x$2) -> BoxesRunTime.boxToBoolean($anonfun$parseStructField$1(x$2))));
                                    return new StructField(name, this.parseDataType(dataType, name, collationsMap), nullable, Metadata$.MODULE$.fromJObject(metadataWithoutCollations));
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
      }

      if (json != null) {
         Option var26 = DataType.JSortedObject$.MODULE$.unapplySeq(json);
         if (!var26.isEmpty() && var26.get() != null && ((List)var26.get()).lengthCompare(3) == 0) {
            Tuple2 var27 = (Tuple2)((LinearSeqOps)var26.get()).apply(0);
            Tuple2 var28 = (Tuple2)((LinearSeqOps)var26.get()).apply(1);
            Tuple2 var29 = (Tuple2)((LinearSeqOps)var26.get()).apply(2);
            if (var27 != null) {
               String var30 = (String)var27._1();
               JValue var31 = (JValue)var27._2();
               if ("name".equals(var30) && var31 instanceof JString) {
                  JString var32 = (JString)var31;
                  String name = var32.s();
                  if (var28 != null) {
                     String var34 = (String)var28._1();
                     JValue var35 = (JValue)var28._2();
                     if ("nullable".equals(var34) && var35 instanceof JBool) {
                        JBool var36 = (JBool)var35;
                        boolean nullable = var36.value();
                        if (var29 != null) {
                           String var38 = (String)var29._1();
                           JValue dataType = (JValue)var29._2();
                           if ("type".equals(var38) && dataType != null) {
                              return new StructField(name, this.parseDataType(dataType, this.parseDataType$default$2(), this.parseDataType$default$3()), nullable, StructField$.MODULE$.apply$default$4());
                           }
                        }
                     }
                  }
               }
            }
         }
      }

      if (json != null) {
         Option var41 = DataType.JSortedObject$.MODULE$.unapplySeq(json);
         if (!var41.isEmpty() && var41.get() != null && ((List)var41.get()).lengthCompare(2) == 0) {
            Tuple2 var42 = (Tuple2)((LinearSeqOps)var41.get()).apply(0);
            Tuple2 var43 = (Tuple2)((LinearSeqOps)var41.get()).apply(1);
            if (var42 != null) {
               String var44 = (String)var42._1();
               JValue var45 = (JValue)var42._2();
               if ("name".equals(var44) && var45 instanceof JString) {
                  JString var46 = (JString)var45;
                  String name = var46.s();
                  if (var43 != null) {
                     String var48 = (String)var43._1();
                     JValue dataType = (JValue)var43._2();
                     if ("type".equals(var48) && dataType != null) {
                        return new StructField(name, this.parseDataType(dataType, this.parseDataType$default$2(), this.parseDataType$default$3()), StructField$.MODULE$.apply$default$3(), StructField$.MODULE$.apply$default$4());
                     }
                  }
               }
            }
         }
      }

      throw new SparkIllegalArgumentException("INVALID_JSON_DATA_TYPE", (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("invalidType"), org.json4s.jackson.JsonMethods..MODULE$.compact(org.json4s.jackson.JsonMethods..MODULE$.render(json, org.json4s.jackson.JsonMethods..MODULE$.render$default$2(), org.json4s.jackson.JsonMethods..MODULE$.render$default$3())))}))));
   }

   private void assertValidTypeForCollations(final String fieldPath, final String fieldType, final Map collationMap) {
      if (collationMap.contains(fieldPath)) {
         String var4 = "string";
         if (fieldType == null) {
            if (var4 != null) {
               throw new SparkIllegalArgumentException("INVALID_JSON_DATA_TYPE_FOR_COLLATIONS", (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("jsonType"), fieldType)}))));
            }
         } else if (!fieldType.equals(var4)) {
            throw new SparkIllegalArgumentException("INVALID_JSON_DATA_TYPE_FOR_COLLATIONS", (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("jsonType"), fieldType)}))));
         }
      }

   }

   private String appendFieldToPath(final String basePath, final String fieldName) {
      return basePath.isEmpty() ? fieldName : basePath + "." + fieldName;
   }

   private Map getCollationsMap(final List metadataFields) {
      Option collationsJsonOpt = metadataFields.find((x$3) -> BoxesRunTime.boxToBoolean($anonfun$getCollationsMap$1(x$3))).map((x$4) -> (JValue)x$4._2());
      if (collationsJsonOpt instanceof Some var5) {
         JValue var6 = (JValue)var5.value();
         if (var6 instanceof JObject var7) {
            List fields = var7.obj();
            return fields.collect(new Serializable() {
               private static final long serialVersionUID = 0L;

               public final Object applyOrElse(final Tuple2 x1, final Function1 default) {
                  if (x1 != null) {
                     String fieldPath = (String)x1._1();
                     JValue var7 = (JValue)x1._2();
                     if (var7 instanceof JString) {
                        JString var8 = (JString)var7;
                        String collation = var8.s();
                        String[] var10 = collation.split("\\.", 2);
                        if (var10 != null) {
                           Object var11 = scala.Array..MODULE$.unapplySeq(var10);
                           if (!scala.Array.UnapplySeqWrapper..MODULE$.isEmpty$extension(var11) && new Array.UnapplySeqWrapper(scala.Array.UnapplySeqWrapper..MODULE$.get$extension(var11)) != null && scala.Array.UnapplySeqWrapper..MODULE$.lengthCompare$extension(scala.Array.UnapplySeqWrapper..MODULE$.get$extension(var11), 2) == 0) {
                              String provider = (String)scala.Array.UnapplySeqWrapper..MODULE$.apply$extension(scala.Array.UnapplySeqWrapper..MODULE$.get$extension(var11), 0);
                              String collationName = (String)scala.Array.UnapplySeqWrapper..MODULE$.apply$extension(scala.Array.UnapplySeqWrapper..MODULE$.get$extension(var11), 1);
                              if (provider != null && collationName != null) {
                                 CollationFactory.assertValidProvider(provider);
                                 return scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(fieldPath), collationName);
                              }
                           }
                        }

                        throw new MatchError(var10);
                     }
                  }

                  return default.apply(x1);
               }

               public final boolean isDefinedAt(final Tuple2 x1) {
                  if (x1 != null) {
                     JValue var4 = (JValue)x1._2();
                     if (var4 instanceof JString) {
                        return true;
                     }
                  }

                  return false;
               }
            }).toMap(scala..less.colon.less..MODULE$.refl());
         }
      }

      return scala.Predef..MODULE$.Map().empty();
   }

   private StringType stringTypeWithCollation(final String collationName) {
      return StringType$.MODULE$.apply(CollationFactory.collationNameToId(collationName));
   }

   public void buildFormattedString(final DataType dataType, final String prefix, final StringConcat stringConcat, final int maxDepth) {
      if (dataType instanceof ArrayType var7) {
         var7.buildFormattedString(prefix, stringConcat, maxDepth - 1);
         BoxedUnit var12 = BoxedUnit.UNIT;
      } else if (dataType instanceof StructType var8) {
         var8.buildFormattedString(prefix, stringConcat, maxDepth - 1);
         BoxedUnit var11 = BoxedUnit.UNIT;
      } else if (dataType instanceof MapType var9) {
         var9.buildFormattedString(prefix, stringConcat, maxDepth - 1);
         BoxedUnit var10 = BoxedUnit.UNIT;
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }
   }

   public boolean equalsIgnoreCompatibleNullability(final DataType from, final DataType to) {
      return this.equalsIgnoreCompatibleNullability(from, to, false);
   }

   public boolean equalsIgnoreNameAndCompatibleNullability(final DataType from, final DataType to) {
      return this.equalsIgnoreCompatibleNullability(from, to, true);
   }

   private boolean equalsIgnoreCompatibleNullability(final DataType from, final DataType to, final boolean ignoreName) {
      while(true) {
         Tuple2 var6 = new Tuple2(from, to);
         if (var6 != null) {
            DataType var7 = (DataType)var6._1();
            DataType var8 = (DataType)var6._2();
            if (var7 instanceof ArrayType) {
               ArrayType var9 = (ArrayType)var7;
               DataType fromElement = var9.elementType();
               boolean fn = var9.containsNull();
               if (var8 instanceof ArrayType) {
                  ArrayType var12 = (ArrayType)var8;
                  DataType toElement = var12.elementType();
                  boolean tn = var12.containsNull();
                  if (!tn && fn) {
                     return false;
                  }

                  ignoreName = ignoreName;
                  to = toElement;
                  from = fromElement;
                  continue;
               }
            }
         }

         if (var6 != null) {
            DataType var15 = (DataType)var6._1();
            DataType var16 = (DataType)var6._2();
            if (var15 instanceof MapType) {
               MapType var17 = (MapType)var15;
               DataType fromKey = var17.keyType();
               DataType fromValue = var17.valueType();
               boolean fn = var17.valueContainsNull();
               if (var16 instanceof MapType) {
                  MapType var21 = (MapType)var16;
                  DataType toKey = var21.keyType();
                  DataType toValue = var21.valueType();
                  boolean tn = var21.valueContainsNull();
                  if ((tn || !fn) && this.equalsIgnoreCompatibleNullability(fromKey, toKey, ignoreName)) {
                     ignoreName = ignoreName;
                     to = toValue;
                     from = fromValue;
                     continue;
                  }

                  return false;
               }
            }
         }

         if (var6 != null) {
            DataType var25 = (DataType)var6._1();
            DataType var26 = (DataType)var6._2();
            if (var25 instanceof StructType) {
               StructType var27 = (StructType)var25;
               StructField[] fromFields = var27.fields();
               if (var26 instanceof StructType) {
                  StructType var29 = (StructType)var26;
                  StructField[] toFields = var29.fields();
                  return fromFields.length == toFields.length && scala.collection.ArrayOps..MODULE$.forall$extension(scala.Predef..MODULE$.refArrayOps((Object[])scala.collection.ArrayOps..MODULE$.zip$extension(scala.Predef..MODULE$.refArrayOps(fromFields), scala.Predef..MODULE$.wrapRefArray(toFields))), (x0$1) -> BoxesRunTime.boxToBoolean($anonfun$equalsIgnoreCompatibleNullability$1(ignoreName, x0$1)));
               }
            }
         }

         if (var6 == null) {
            throw new MatchError(var6);
         }

         boolean var10000;
         label92: {
            DataType fromDataType = (DataType)var6._1();
            DataType toDataType = (DataType)var6._2();
            if (fromDataType == null) {
               if (toDataType == null) {
                  break label92;
               }
            } else if (fromDataType.equals(toDataType)) {
               break label92;
            }

            var10000 = false;
            return var10000;
         }

         var10000 = true;
         return var10000;
      }
   }

   private boolean equalsIgnoreCompatibleNullability$default$3() {
      return false;
   }

   public boolean equalsIgnoreCompatibleCollation(final DataType from, final DataType to) {
      Tuple2 var4 = new Tuple2(from, to);
      if (var4 != null) {
         DataType a = (DataType)var4._1();
         DataType b = (DataType)var4._2();
         if (a instanceof StringType) {
            StringType var7 = (StringType)a;
            if (b instanceof StringType) {
               boolean var14;
               label58: {
                  StringType var8 = (StringType)b;
                  StringConstraint var13 = var7.constraint();
                  StringConstraint var9 = var8.constraint();
                  if (var13 == null) {
                     if (var9 == null) {
                        break label58;
                     }
                  } else if (var13.equals(var9)) {
                     break label58;
                  }

                  var14 = false;
                  return var14;
               }

               var14 = true;
               return var14;
            }
         }
      }

      if (var4 == null) {
         throw new MatchError(var4);
      } else {
         boolean var10000;
         label60: {
            DataType fromDataType = (DataType)var4._1();
            DataType toDataType = (DataType)var4._2();
            if (fromDataType == null) {
               if (toDataType == null) {
                  break label60;
               }
            } else if (fromDataType.equals(toDataType)) {
               break label60;
            }

            var10000 = false;
            return var10000;
         }

         var10000 = true;
         return var10000;
      }
   }

   public boolean equalsStructurally(final DataType from, final DataType to, final boolean ignoreNullability) {
      Tuple2 var5 = new Tuple2(from, to);
      if (var5 != null) {
         DataType left = (DataType)var5._1();
         DataType right = (DataType)var5._2();
         if (left instanceof ArrayType) {
            ArrayType var8 = (ArrayType)left;
            if (right instanceof ArrayType) {
               ArrayType var9 = (ArrayType)right;
               return this.equalsStructurally(var8.elementType(), var9.elementType(), ignoreNullability) && (ignoreNullability || var8.containsNull() == var9.containsNull());
            }
         }
      }

      if (var5 != null) {
         DataType left = (DataType)var5._1();
         DataType right = (DataType)var5._2();
         if (left instanceof MapType) {
            MapType var12 = (MapType)left;
            if (right instanceof MapType) {
               MapType var13 = (MapType)right;
               return this.equalsStructurally(var12.keyType(), var13.keyType(), ignoreNullability) && this.equalsStructurally(var12.valueType(), var13.valueType(), ignoreNullability) && (ignoreNullability || var12.valueContainsNull() == var13.valueContainsNull());
            }
         }
      }

      if (var5 != null) {
         DataType var14 = (DataType)var5._1();
         DataType var15 = (DataType)var5._2();
         if (var14 instanceof StructType) {
            StructType var16 = (StructType)var14;
            StructField[] fromFields = var16.fields();
            if (var15 instanceof StructType) {
               StructType var18 = (StructType)var15;
               StructField[] toFields = var18.fields();
               return fromFields.length == toFields.length && scala.collection.ArrayOps..MODULE$.forall$extension(scala.Predef..MODULE$.refArrayOps((Object[])scala.collection.ArrayOps..MODULE$.zip$extension(scala.Predef..MODULE$.refArrayOps(fromFields), scala.Predef..MODULE$.wrapRefArray(toFields))), (x0$1) -> BoxesRunTime.boxToBoolean($anonfun$equalsStructurally$1(ignoreNullability, x0$1)));
            }
         }
      }

      if (var5 == null) {
         throw new MatchError(var5);
      } else {
         boolean var10000;
         label113: {
            DataType fromDataType = (DataType)var5._1();
            DataType toDataType = (DataType)var5._2();
            if (fromDataType == null) {
               if (toDataType == null) {
                  break label113;
               }
            } else if (fromDataType.equals(toDataType)) {
               break label113;
            }

            var10000 = false;
            return var10000;
         }

         var10000 = true;
         return var10000;
      }
   }

   public boolean equalsStructurally$default$3() {
      return false;
   }

   public boolean equalsStructurallyByName(final DataType from, final DataType to, final Function2 resolver) {
      while(true) {
         Tuple2 var6 = new Tuple2(from, to);
         if (var6 != null) {
            DataType left = (DataType)var6._1();
            DataType right = (DataType)var6._2();
            if (left instanceof ArrayType) {
               ArrayType var9 = (ArrayType)left;
               if (right instanceof ArrayType) {
                  ArrayType var10 = (ArrayType)right;
                  DataType var21 = var9.elementType();
                  DataType var22 = var10.elementType();
                  resolver = resolver;
                  to = var22;
                  from = var21;
                  continue;
               }
            }
         }

         if (var6 != null) {
            DataType left = (DataType)var6._1();
            DataType right = (DataType)var6._2();
            if (left instanceof MapType) {
               MapType var13 = (MapType)left;
               if (right instanceof MapType) {
                  MapType var14 = (MapType)right;
                  if (this.equalsStructurallyByName(var13.keyType(), var14.keyType(), resolver)) {
                     DataType var10000 = var13.valueType();
                     DataType var10001 = var14.valueType();
                     resolver = resolver;
                     to = var10001;
                     from = var10000;
                     continue;
                  }

                  return false;
               }
            }
         }

         if (var6 != null) {
            DataType var15 = (DataType)var6._1();
            DataType var16 = (DataType)var6._2();
            if (var15 instanceof StructType) {
               StructType var17 = (StructType)var15;
               StructField[] fromFields = var17.fields();
               if (var16 instanceof StructType) {
                  StructType var19 = (StructType)var16;
                  StructField[] toFields = var19.fields();
                  return fromFields.length == toFields.length && scala.collection.ArrayOps..MODULE$.forall$extension(scala.Predef..MODULE$.refArrayOps((Object[])scala.collection.ArrayOps..MODULE$.zip$extension(scala.Predef..MODULE$.refArrayOps(fromFields), scala.Predef..MODULE$.wrapRefArray(toFields))), (x0$1) -> BoxesRunTime.boxToBoolean($anonfun$equalsStructurallyByName$1(resolver, x0$1)));
               }
            }
         }

         return true;
      }
   }

   public boolean equalsIgnoreNullability(final DataType left, final DataType right) {
      while(true) {
         Tuple2 var5 = new Tuple2(left, right);
         if (var5 != null) {
            DataType var6 = (DataType)var5._1();
            DataType var7 = (DataType)var5._2();
            if (var6 instanceof ArrayType) {
               ArrayType var8 = (ArrayType)var6;
               DataType leftElementType = var8.elementType();
               if (var7 instanceof ArrayType) {
                  ArrayType var10 = (ArrayType)var7;
                  DataType rightElementType = var10.elementType();
                  right = rightElementType;
                  left = leftElementType;
                  continue;
               }
            }
         }

         if (var5 != null) {
            DataType var12 = (DataType)var5._1();
            DataType var13 = (DataType)var5._2();
            if (var12 instanceof MapType) {
               MapType var14 = (MapType)var12;
               DataType leftKeyType = var14.keyType();
               DataType leftValueType = var14.valueType();
               if (var13 instanceof MapType) {
                  MapType var17 = (MapType)var13;
                  DataType rightKeyType = var17.keyType();
                  DataType rightValueType = var17.valueType();
                  if (this.equalsIgnoreNullability(leftKeyType, rightKeyType)) {
                     right = rightValueType;
                     left = leftValueType;
                     continue;
                  }

                  return false;
               }
            }
         }

         if (var5 != null) {
            DataType var20 = (DataType)var5._1();
            DataType var21 = (DataType)var5._2();
            if (var20 instanceof StructType) {
               StructType var22 = (StructType)var20;
               StructField[] leftFields = var22.fields();
               if (var21 instanceof StructType) {
                  StructType var24 = (StructType)var21;
                  StructField[] rightFields = var24.fields();
                  return leftFields.length == rightFields.length && scala.collection.ArrayOps..MODULE$.forall$extension(scala.Predef..MODULE$.refArrayOps((Object[])scala.collection.ArrayOps..MODULE$.zip$extension(scala.Predef..MODULE$.refArrayOps(leftFields), scala.Predef..MODULE$.wrapRefArray(rightFields))), (x0$1) -> BoxesRunTime.boxToBoolean($anonfun$equalsIgnoreNullability$1(x0$1)));
               }
            }
         }

         if (var5 == null) {
            throw new MatchError(var5);
         }

         boolean var10000;
         label79: {
            DataType l = (DataType)var5._1();
            DataType r = (DataType)var5._2();
            if (l == null) {
               if (r == null) {
                  break label79;
               }
            } else if (l.equals(r)) {
               break label79;
            }

            var10000 = false;
            return var10000;
         }

         var10000 = true;
         return var10000;
      }
   }

   public boolean equalsIgnoreCaseAndNullability(final DataType from, final DataType to) {
      while(true) {
         Tuple2 var5 = new Tuple2(from, to);
         if (var5 != null) {
            DataType var6 = (DataType)var5._1();
            DataType var7 = (DataType)var5._2();
            if (var6 instanceof ArrayType) {
               ArrayType var8 = (ArrayType)var6;
               DataType fromElement = var8.elementType();
               if (var7 instanceof ArrayType) {
                  ArrayType var10 = (ArrayType)var7;
                  DataType toElement = var10.elementType();
                  to = toElement;
                  from = fromElement;
                  continue;
               }
            }
         }

         if (var5 != null) {
            DataType var12 = (DataType)var5._1();
            DataType var13 = (DataType)var5._2();
            if (var12 instanceof MapType) {
               MapType var14 = (MapType)var12;
               DataType fromKey = var14.keyType();
               DataType fromValue = var14.valueType();
               if (var13 instanceof MapType) {
                  MapType var17 = (MapType)var13;
                  DataType toKey = var17.keyType();
                  DataType toValue = var17.valueType();
                  if (this.equalsIgnoreCaseAndNullability(fromKey, toKey)) {
                     to = toValue;
                     from = fromValue;
                     continue;
                  }

                  return false;
               }
            }
         }

         if (var5 != null) {
            DataType var20 = (DataType)var5._1();
            DataType var21 = (DataType)var5._2();
            if (var20 instanceof StructType) {
               StructType var22 = (StructType)var20;
               StructField[] fromFields = var22.fields();
               if (var21 instanceof StructType) {
                  StructType var24 = (StructType)var21;
                  StructField[] toFields = var24.fields();
                  return fromFields.length == toFields.length && scala.collection.ArrayOps..MODULE$.forall$extension(scala.Predef..MODULE$.refArrayOps((Object[])scala.collection.ArrayOps..MODULE$.zip$extension(scala.Predef..MODULE$.refArrayOps(fromFields), scala.Predef..MODULE$.wrapRefArray(toFields))), (x0$1) -> BoxesRunTime.boxToBoolean($anonfun$equalsIgnoreCaseAndNullability$1(x0$1)));
               }
            }
         }

         if (var5 == null) {
            throw new MatchError(var5);
         }

         boolean var10000;
         label79: {
            DataType fromDataType = (DataType)var5._1();
            DataType toDataType = (DataType)var5._2();
            if (fromDataType == null) {
               if (toDataType == null) {
                  break label79;
               }
            } else if (fromDataType.equals(toDataType)) {
               break label79;
            }

            var10000 = false;
            return var10000;
         }

         var10000 = true;
         return var10000;
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$parseStructField$1(final Tuple2 x$2) {
      boolean var2;
      label23: {
         Object var10000 = x$2._1();
         String var1 = MODULE$.COLLATIONS_METADATA_KEY();
         if (var10000 == null) {
            if (var1 == null) {
               break label23;
            }
         } else if (var10000.equals(var1)) {
            break label23;
         }

         var2 = false;
         return var2;
      }

      var2 = true;
      return var2;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getCollationsMap$1(final Tuple2 x$3) {
      boolean var2;
      label23: {
         Object var10000 = x$3._1();
         String var1 = MODULE$.COLLATIONS_METADATA_KEY();
         if (var10000 == null) {
            if (var1 == null) {
               break label23;
            }
         } else if (var10000.equals(var1)) {
            break label23;
         }

         var2 = false;
         return var2;
      }

      var2 = true;
      return var2;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$equalsIgnoreCompatibleNullability$1(final boolean ignoreName$1, final Tuple2 x0$1) {
      if (x0$1 == null) {
         throw new MatchError(x0$1);
      } else {
         boolean var7;
         label35: {
            StructField fromField = (StructField)x0$1._1();
            StructField toField = (StructField)x0$1._2();
            if (!ignoreName$1) {
               String var10000 = fromField.name();
               String var6 = toField.name();
               if (var10000 == null) {
                  if (var6 != null) {
                     break label35;
                  }
               } else if (!var10000.equals(var6)) {
                  break label35;
               }
            }

            if ((toField.nullable() || !fromField.nullable()) && MODULE$.equalsIgnoreCompatibleNullability(fromField.dataType(), toField.dataType(), ignoreName$1)) {
               var7 = true;
               return var7;
            }
         }

         var7 = false;
         return var7;
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$equalsStructurally$1(final boolean ignoreNullability$1, final Tuple2 x0$1) {
      if (x0$1 == null) {
         throw new MatchError(x0$1);
      } else {
         StructField l = (StructField)x0$1._1();
         StructField r = (StructField)x0$1._2();
         return MODULE$.equalsStructurally(l.dataType(), r.dataType(), ignoreNullability$1) && (ignoreNullability$1 || l.nullable() == r.nullable());
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$equalsStructurallyByName$1(final Function2 resolver$1, final Tuple2 x0$1) {
      if (x0$1 == null) {
         throw new MatchError(x0$1);
      } else {
         StructField l = (StructField)x0$1._1();
         StructField r = (StructField)x0$1._2();
         return BoxesRunTime.unboxToBoolean(resolver$1.apply(l.name(), r.name())) && MODULE$.equalsStructurallyByName(l.dataType(), r.dataType(), resolver$1);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$equalsIgnoreNullability$1(final Tuple2 x0$1) {
      if (x0$1 == null) {
         throw new MatchError(x0$1);
      } else {
         boolean var6;
         label22: {
            StructField l = (StructField)x0$1._1();
            StructField r = (StructField)x0$1._2();
            String var10000 = l.name();
            String var5 = r.name();
            if (var10000 == null) {
               if (var5 != null) {
                  break label22;
               }
            } else if (!var10000.equals(var5)) {
               break label22;
            }

            if (MODULE$.equalsIgnoreNullability(l.dataType(), r.dataType())) {
               var6 = true;
               return var6;
            }
         }

         var6 = false;
         return var6;
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$equalsIgnoreCaseAndNullability$1(final Tuple2 x0$1) {
      if (x0$1 == null) {
         throw new MatchError(x0$1);
      } else {
         StructField l = (StructField)x0$1._1();
         StructField r = (StructField)x0$1._2();
         return l.name().equalsIgnoreCase(r.name()) && MODULE$.equalsIgnoreCaseAndNullability(l.dataType(), r.dataType());
      }
   }

   private DataType$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
