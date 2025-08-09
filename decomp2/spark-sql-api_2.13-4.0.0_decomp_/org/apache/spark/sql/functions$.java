package org.apache.spark.sql;

import java.lang.invoke.SerializedLambda;
import java.util.Collections;
import org.apache.spark.annotation.Stable;
import org.apache.spark.sql.api.java.UDF0;
import org.apache.spark.sql.api.java.UDF1;
import org.apache.spark.sql.api.java.UDF10;
import org.apache.spark.sql.api.java.UDF2;
import org.apache.spark.sql.api.java.UDF3;
import org.apache.spark.sql.api.java.UDF4;
import org.apache.spark.sql.api.java.UDF5;
import org.apache.spark.sql.api.java.UDF6;
import org.apache.spark.sql.api.java.UDF7;
import org.apache.spark.sql.api.java.UDF8;
import org.apache.spark.sql.api.java.UDF9;
import org.apache.spark.sql.catalyst.ScalaReflection$;
import org.apache.spark.sql.catalyst.encoders.AgnosticEncoders;
import org.apache.spark.sql.catalyst.trees.Origin;
import org.apache.spark.sql.errors.CompilationErrors$;
import org.apache.spark.sql.expressions.Aggregator;
import org.apache.spark.sql.expressions.SparkUserDefinedFunction;
import org.apache.spark.sql.expressions.SparkUserDefinedFunction$;
import org.apache.spark.sql.expressions.UserDefinedAggregator;
import org.apache.spark.sql.expressions.UserDefinedAggregator$;
import org.apache.spark.sql.expressions.UserDefinedFunction;
import org.apache.spark.sql.internal.CaseWhenOtherwise;
import org.apache.spark.sql.internal.CaseWhenOtherwise$;
import org.apache.spark.sql.internal.ColumnNode;
import org.apache.spark.sql.internal.LambdaFunction$;
import org.apache.spark.sql.internal.Literal;
import org.apache.spark.sql.internal.Literal$;
import org.apache.spark.sql.internal.SqlApiConf$;
import org.apache.spark.sql.internal.SqlExpression;
import org.apache.spark.sql.internal.SqlExpression$;
import org.apache.spark.sql.internal.ToScalaUDF$;
import org.apache.spark.sql.internal.UnresolvedFunction;
import org.apache.spark.sql.internal.UnresolvedFunction$;
import org.apache.spark.sql.internal.UnresolvedNamedLambdaVariable;
import org.apache.spark.sql.internal.UnresolvedNamedLambdaVariable$;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.StructType;
import scala.Function0;
import scala.Function1;
import scala.Function10;
import scala.Function2;
import scala.Function3;
import scala.Function4;
import scala.Function5;
import scala.Function6;
import scala.Function7;
import scala.Function8;
import scala.Function9;
import scala.Option;
import scala.Symbol;
import scala.collection.IterableOps;
import scala.collection.Iterator;
import scala.collection.SeqOps;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.reflect.api.TypeTags;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime.;

@Stable
public final class functions$ {
   public static final functions$ MODULE$ = new functions$();

   public Column countDistinct(final Column expr, final Column... exprs) {
      return this.countDistinct((Column)expr, (Seq).MODULE$.wrapRefArray(exprs));
   }

   public Column countDistinct(final String columnName, final String... columnNames) {
      return this.countDistinct((String)columnName, (Seq).MODULE$.wrapRefArray((Object[])columnNames));
   }

   public Column count_distinct(final Column expr, final Column... exprs) {
      return this.count_distinct(expr, (Seq).MODULE$.wrapRefArray(exprs));
   }

   public Column grouping_id(final Column... cols) {
      return this.grouping_id((Seq).MODULE$.wrapRefArray(cols));
   }

   public Column grouping_id(final String colName, final String... colNames) {
      return this.grouping_id(colName, (Seq).MODULE$.wrapRefArray((Object[])colNames));
   }

   public Column array(final Column... cols) {
      return this.array((Seq).MODULE$.wrapRefArray(cols));
   }

   public Column array(final String colName, final String... colNames) {
      return this.array(colName, (Seq).MODULE$.wrapRefArray((Object[])colNames));
   }

   public Column map(final Column... cols) {
      return this.map((Seq).MODULE$.wrapRefArray(cols));
   }

   public Column named_struct(final Column... cols) {
      return this.named_struct((Seq).MODULE$.wrapRefArray(cols));
   }

   public Column coalesce(final Column... e) {
      return this.coalesce((Seq).MODULE$.wrapRefArray(e));
   }

   public Column struct(final Column... cols) {
      return this.struct((Seq).MODULE$.wrapRefArray(cols));
   }

   public Column struct(final String colName, final String... colNames) {
      return this.struct(colName, (Seq).MODULE$.wrapRefArray((Object[])colNames));
   }

   public Column greatest(final Column... exprs) {
      return this.greatest((Seq).MODULE$.wrapRefArray(exprs));
   }

   public Column greatest(final String columnName, final String... columnNames) {
      return this.greatest(columnName, (Seq).MODULE$.wrapRefArray((Object[])columnNames));
   }

   public Column least(final Column... exprs) {
      return this.least((Seq).MODULE$.wrapRefArray(exprs));
   }

   public Column least(final String columnName, final String... columnNames) {
      return this.least(columnName, (Seq).MODULE$.wrapRefArray((Object[])columnNames));
   }

   public Column hash(final Column... cols) {
      return this.hash((Seq).MODULE$.wrapRefArray(cols));
   }

   public Column xxhash64(final Column... cols) {
      return this.xxhash64((Seq).MODULE$.wrapRefArray(cols));
   }

   public Column reflect(final Column... cols) {
      return this.reflect((Seq).MODULE$.wrapRefArray(cols));
   }

   public Column java_method(final Column... cols) {
      return this.java_method((Seq).MODULE$.wrapRefArray(cols));
   }

   public Column try_reflect(final Column... cols) {
      return this.try_reflect((Seq).MODULE$.wrapRefArray(cols));
   }

   public Column stack(final Column... cols) {
      return this.stack((Seq).MODULE$.wrapRefArray(cols));
   }

   public Column concat_ws(final String sep, final Column... exprs) {
      return this.concat_ws(sep, (Seq).MODULE$.wrapRefArray(exprs));
   }

   public Column format_string(final String format, final Column... arguments) {
      return this.format_string(format, (Seq).MODULE$.wrapRefArray(arguments));
   }

   public Column printf(final Column format, final Column... arguments) {
      return this.printf(format, (Seq).MODULE$.wrapRefArray(arguments));
   }

   public Column elt(final Column... inputs) {
      return this.elt((Seq).MODULE$.wrapRefArray(inputs));
   }

   public Column concat(final Column... exprs) {
      return this.concat((Seq).MODULE$.wrapRefArray(exprs));
   }

   public Column json_tuple(final Column json, final String... fields) {
      return this.json_tuple(json, (Seq).MODULE$.wrapRefArray((Object[])fields));
   }

   public Column arrays_zip(final Column... e) {
      return this.arrays_zip((Seq).MODULE$.wrapRefArray(e));
   }

   public Column map_concat(final Column... cols) {
      return this.map_concat((Seq).MODULE$.wrapRefArray(cols));
   }

   public Column callUDF(final String udfName, final Column... cols) {
      return this.callUDF(udfName, (Seq).MODULE$.wrapRefArray(cols));
   }

   public Column call_udf(final String udfName, final Column... cols) {
      return this.call_udf(udfName, (Seq).MODULE$.wrapRefArray(cols));
   }

   public Column call_function(final String funcName, final Column... cols) {
      return this.call_function(funcName, (Seq).MODULE$.wrapRefArray(cols));
   }

   public Column col(final String colName) {
      return Column$.MODULE$.apply(colName);
   }

   public Column column(final String colName) {
      return Column$.MODULE$.apply(colName);
   }

   public Column lit(final Object literal) {
      if (literal instanceof Column var4) {
         return var4;
      } else if (literal instanceof Symbol var5) {
         return new ColumnName(var5.name());
      } else {
         return Column$.MODULE$.apply((Function0)(() -> new Literal(literal, Literal$.MODULE$.apply$default$2(), Literal$.MODULE$.apply$default$3())));
      }
   }

   public Column typedLit(final Object literal, final TypeTags.TypeTag evidence$1) {
      return this.typedlit(literal, evidence$1);
   }

   public Column typedlit(final Object literal, final TypeTags.TypeTag evidence$2) {
      if (literal instanceof Column var5) {
         return var5;
      } else if (literal instanceof Symbol var6) {
         return new ColumnName(var6.name());
      } else {
         Option dataType = scala.util.Try..MODULE$.apply(() -> ScalaReflection$.MODULE$.schemaFor(evidence$2).dataType()).toOption();
         return Column$.MODULE$.apply((Function0)(() -> new Literal(literal, dataType, Literal$.MODULE$.apply$default$3())));
      }
   }

   public Column asc(final String columnName) {
      return Column$.MODULE$.apply(columnName).asc();
   }

   public Column asc_nulls_first(final String columnName) {
      return Column$.MODULE$.apply(columnName).asc_nulls_first();
   }

   public Column asc_nulls_last(final String columnName) {
      return Column$.MODULE$.apply(columnName).asc_nulls_last();
   }

   public Column desc(final String columnName) {
      return Column$.MODULE$.apply(columnName).desc();
   }

   public Column desc_nulls_first(final String columnName) {
      return Column$.MODULE$.apply(columnName).desc_nulls_first();
   }

   public Column desc_nulls_last(final String columnName) {
      return Column$.MODULE$.apply(columnName).desc_nulls_last();
   }

   /** @deprecated */
   public Column approxCountDistinct(final Column e) {
      return this.approx_count_distinct(e);
   }

   /** @deprecated */
   public Column approxCountDistinct(final String columnName) {
      return this.approx_count_distinct(columnName);
   }

   /** @deprecated */
   public Column approxCountDistinct(final Column e, final double rsd) {
      return this.approx_count_distinct(e, rsd);
   }

   /** @deprecated */
   public Column approxCountDistinct(final String columnName, final double rsd) {
      return this.approx_count_distinct(Column$.MODULE$.apply(columnName), rsd);
   }

   public Column approx_count_distinct(final Column e) {
      return Column$.MODULE$.fn("approx_count_distinct", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column approx_count_distinct(final String columnName) {
      return this.approx_count_distinct(this.column(columnName));
   }

   public Column approx_count_distinct(final Column e, final double rsd) {
      return Column$.MODULE$.fn("approx_count_distinct", .MODULE$.wrapRefArray(new Column[]{e, this.lit(BoxesRunTime.boxToDouble(rsd))}));
   }

   public Column approx_count_distinct(final String columnName, final double rsd) {
      return this.approx_count_distinct(Column$.MODULE$.apply(columnName), rsd);
   }

   public Column avg(final Column e) {
      return Column$.MODULE$.fn("avg", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column avg(final String columnName) {
      return this.avg(Column$.MODULE$.apply(columnName));
   }

   public Column collect_list(final Column e) {
      return Column$.MODULE$.fn("collect_list", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column collect_list(final String columnName) {
      return this.collect_list(Column$.MODULE$.apply(columnName));
   }

   public Column collect_set(final Column e) {
      return Column$.MODULE$.fn("collect_set", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column collect_set(final String columnName) {
      return this.collect_set(Column$.MODULE$.apply(columnName));
   }

   public Column count_min_sketch(final Column e, final Column eps, final Column confidence, final Column seed) {
      return Column$.MODULE$.fn("count_min_sketch", .MODULE$.wrapRefArray(new Column[]{e, eps, confidence, seed}));
   }

   public Column count_min_sketch(final Column e, final Column eps, final Column confidence) {
      return this.count_min_sketch(e, eps, confidence, this.lit(BoxesRunTime.boxToLong(org.apache.spark.util.SparkClassUtils..MODULE$.random().nextLong())));
   }

   public Column corr(final Column column1, final Column column2) {
      return Column$.MODULE$.fn("corr", .MODULE$.wrapRefArray(new Column[]{column1, column2}));
   }

   public Column corr(final String columnName1, final String columnName2) {
      return this.corr(Column$.MODULE$.apply(columnName1), Column$.MODULE$.apply(columnName2));
   }

   public Column count(final Column e) {
      return Column$.MODULE$.fn("count", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public TypedColumn count(final String columnName) {
      return this.count(Column$.MODULE$.apply(columnName)).as((Encoder)AgnosticEncoders.PrimitiveLongEncoder$.MODULE$);
   }

   public Column countDistinct(final Column expr, final Seq exprs) {
      return this.count_distinct(expr, exprs);
   }

   public Column countDistinct(final String columnName, final Seq columnNames) {
      return this.count_distinct(Column$.MODULE$.apply(columnName), (Seq)columnNames.map((colName) -> Column$.MODULE$.apply(colName)));
   }

   public Column count_distinct(final Column expr, final Seq exprs) {
      return Column$.MODULE$.fn("count", true, (Seq)exprs.$plus$colon(expr));
   }

   public Column covar_pop(final Column column1, final Column column2) {
      return Column$.MODULE$.fn("covar_pop", .MODULE$.wrapRefArray(new Column[]{column1, column2}));
   }

   public Column covar_pop(final String columnName1, final String columnName2) {
      return this.covar_pop(Column$.MODULE$.apply(columnName1), Column$.MODULE$.apply(columnName2));
   }

   public Column covar_samp(final Column column1, final Column column2) {
      return Column$.MODULE$.fn("covar_samp", .MODULE$.wrapRefArray(new Column[]{column1, column2}));
   }

   public Column covar_samp(final String columnName1, final String columnName2) {
      return this.covar_samp(Column$.MODULE$.apply(columnName1), Column$.MODULE$.apply(columnName2));
   }

   public Column first(final Column e, final boolean ignoreNulls) {
      return Column$.MODULE$.fn("first", false, .MODULE$.wrapRefArray(new Column[]{e, this.lit(BoxesRunTime.boxToBoolean(ignoreNulls))}));
   }

   public Column first(final String columnName, final boolean ignoreNulls) {
      return this.first(Column$.MODULE$.apply(columnName), ignoreNulls);
   }

   public Column first(final Column e) {
      return this.first(e, false);
   }

   public Column first(final String columnName) {
      return this.first(Column$.MODULE$.apply(columnName));
   }

   public Column first_value(final Column e) {
      return Column$.MODULE$.fn("first_value", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column first_value(final Column e, final Column ignoreNulls) {
      return Column$.MODULE$.fn("first_value", .MODULE$.wrapRefArray(new Column[]{e, ignoreNulls}));
   }

   public Column grouping(final Column e) {
      return Column$.MODULE$.fn("grouping", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column grouping(final String columnName) {
      return this.grouping(Column$.MODULE$.apply(columnName));
   }

   public Column grouping_id(final Seq cols) {
      return Column$.MODULE$.fn("grouping_id", cols);
   }

   public Column grouping_id(final String colName, final Seq colNames) {
      return this.grouping_id((Seq)((IterableOps)(new scala.collection.immutable..colon.colon(colName, scala.collection.immutable.Nil..MODULE$)).$plus$plus(colNames)).map((n) -> Column$.MODULE$.apply(n)));
   }

   public Column hll_sketch_agg(final Column e, final Column lgConfigK) {
      return Column$.MODULE$.fn("hll_sketch_agg", .MODULE$.wrapRefArray(new Column[]{e, lgConfigK}));
   }

   public Column hll_sketch_agg(final Column e, final int lgConfigK) {
      return Column$.MODULE$.fn("hll_sketch_agg", .MODULE$.wrapRefArray(new Column[]{e, this.lit(BoxesRunTime.boxToInteger(lgConfigK))}));
   }

   public Column hll_sketch_agg(final String columnName, final int lgConfigK) {
      return this.hll_sketch_agg(Column$.MODULE$.apply(columnName), lgConfigK);
   }

   public Column hll_sketch_agg(final Column e) {
      return Column$.MODULE$.fn("hll_sketch_agg", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column hll_sketch_agg(final String columnName) {
      return this.hll_sketch_agg(Column$.MODULE$.apply(columnName));
   }

   public Column hll_union_agg(final Column e, final Column allowDifferentLgConfigK) {
      return Column$.MODULE$.fn("hll_union_agg", .MODULE$.wrapRefArray(new Column[]{e, allowDifferentLgConfigK}));
   }

   public Column hll_union_agg(final Column e, final boolean allowDifferentLgConfigK) {
      return Column$.MODULE$.fn("hll_union_agg", .MODULE$.wrapRefArray(new Column[]{e, this.lit(BoxesRunTime.boxToBoolean(allowDifferentLgConfigK))}));
   }

   public Column hll_union_agg(final String columnName, final boolean allowDifferentLgConfigK) {
      return this.hll_union_agg(Column$.MODULE$.apply(columnName), allowDifferentLgConfigK);
   }

   public Column hll_union_agg(final Column e) {
      return Column$.MODULE$.fn("hll_union_agg", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column hll_union_agg(final String columnName) {
      return this.hll_union_agg(Column$.MODULE$.apply(columnName));
   }

   public Column kurtosis(final Column e) {
      return Column$.MODULE$.fn("kurtosis", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column kurtosis(final String columnName) {
      return this.kurtosis(Column$.MODULE$.apply(columnName));
   }

   public Column last(final Column e, final boolean ignoreNulls) {
      return Column$.MODULE$.fn("last", false, .MODULE$.wrapRefArray(new Column[]{e, this.lit(BoxesRunTime.boxToBoolean(ignoreNulls))}));
   }

   public Column last(final String columnName, final boolean ignoreNulls) {
      return this.last(Column$.MODULE$.apply(columnName), ignoreNulls);
   }

   public Column last(final Column e) {
      return this.last(e, false);
   }

   public Column last(final String columnName) {
      return this.last(Column$.MODULE$.apply(columnName), false);
   }

   public Column last_value(final Column e) {
      return Column$.MODULE$.fn("last_value", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column last_value(final Column e, final Column ignoreNulls) {
      return Column$.MODULE$.fn("last_value", .MODULE$.wrapRefArray(new Column[]{e, ignoreNulls}));
   }

   public Column mode(final Column e) {
      return Column$.MODULE$.fn("mode", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column mode(final Column e, final boolean deterministic) {
      return Column$.MODULE$.fn("mode", .MODULE$.wrapRefArray(new Column[]{e, this.lit(BoxesRunTime.boxToBoolean(deterministic))}));
   }

   public Column max(final Column e) {
      return Column$.MODULE$.fn("max", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column max(final String columnName) {
      return this.max(Column$.MODULE$.apply(columnName));
   }

   public Column max_by(final Column e, final Column ord) {
      return Column$.MODULE$.fn("max_by", .MODULE$.wrapRefArray(new Column[]{e, ord}));
   }

   public Column mean(final Column e) {
      return this.avg(e);
   }

   public Column mean(final String columnName) {
      return this.avg(columnName);
   }

   public Column median(final Column e) {
      return Column$.MODULE$.fn("median", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column min(final Column e) {
      return Column$.MODULE$.fn("min", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column min(final String columnName) {
      return this.min(Column$.MODULE$.apply(columnName));
   }

   public Column min_by(final Column e, final Column ord) {
      return Column$.MODULE$.fn("min_by", .MODULE$.wrapRefArray(new Column[]{e, ord}));
   }

   public Column percentile(final Column e, final Column percentage) {
      return Column$.MODULE$.fn("percentile", .MODULE$.wrapRefArray(new Column[]{e, percentage}));
   }

   public Column percentile(final Column e, final Column percentage, final Column frequency) {
      return Column$.MODULE$.fn("percentile", .MODULE$.wrapRefArray(new Column[]{e, percentage, frequency}));
   }

   public Column percentile_approx(final Column e, final Column percentage, final Column accuracy) {
      return Column$.MODULE$.fn("percentile_approx", .MODULE$.wrapRefArray(new Column[]{e, percentage, accuracy}));
   }

   public Column approx_percentile(final Column e, final Column percentage, final Column accuracy) {
      return Column$.MODULE$.fn("approx_percentile", .MODULE$.wrapRefArray(new Column[]{e, percentage, accuracy}));
   }

   public Column product(final Column e) {
      return Column$.MODULE$.internalFn("product", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column skewness(final Column e) {
      return Column$.MODULE$.fn("skewness", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column skewness(final String columnName) {
      return this.skewness(Column$.MODULE$.apply(columnName));
   }

   public Column std(final Column e) {
      return Column$.MODULE$.fn("std", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column stddev(final Column e) {
      return Column$.MODULE$.fn("stddev", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column stddev(final String columnName) {
      return this.stddev(Column$.MODULE$.apply(columnName));
   }

   public Column stddev_samp(final Column e) {
      return Column$.MODULE$.fn("stddev_samp", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column stddev_samp(final String columnName) {
      return this.stddev_samp(Column$.MODULE$.apply(columnName));
   }

   public Column stddev_pop(final Column e) {
      return Column$.MODULE$.fn("stddev_pop", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column stddev_pop(final String columnName) {
      return this.stddev_pop(Column$.MODULE$.apply(columnName));
   }

   public Column sum(final Column e) {
      return Column$.MODULE$.fn("sum", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column sum(final String columnName) {
      return this.sum(Column$.MODULE$.apply(columnName));
   }

   /** @deprecated */
   public Column sumDistinct(final Column e) {
      return this.sum_distinct(e);
   }

   /** @deprecated */
   public Column sumDistinct(final String columnName) {
      return this.sum_distinct(Column$.MODULE$.apply(columnName));
   }

   public Column sum_distinct(final Column e) {
      return Column$.MODULE$.fn("sum", true, .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column listagg(final Column e) {
      return Column$.MODULE$.fn("listagg", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column listagg(final Column e, final Column delimiter) {
      return Column$.MODULE$.fn("listagg", .MODULE$.wrapRefArray(new Column[]{e, delimiter}));
   }

   public Column listagg_distinct(final Column e) {
      return Column$.MODULE$.fn("listagg", true, .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column listagg_distinct(final Column e, final Column delimiter) {
      return Column$.MODULE$.fn("listagg", true, .MODULE$.wrapRefArray(new Column[]{e, delimiter}));
   }

   public Column string_agg(final Column e) {
      return Column$.MODULE$.fn("string_agg", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column string_agg(final Column e, final Column delimiter) {
      return Column$.MODULE$.fn("string_agg", .MODULE$.wrapRefArray(new Column[]{e, delimiter}));
   }

   public Column string_agg_distinct(final Column e) {
      return Column$.MODULE$.fn("string_agg", true, .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column string_agg_distinct(final Column e, final Column delimiter) {
      return Column$.MODULE$.fn("string_agg", true, .MODULE$.wrapRefArray(new Column[]{e, delimiter}));
   }

   public Column variance(final Column e) {
      return Column$.MODULE$.fn("variance", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column variance(final String columnName) {
      return this.variance(Column$.MODULE$.apply(columnName));
   }

   public Column var_samp(final Column e) {
      return Column$.MODULE$.fn("var_samp", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column var_samp(final String columnName) {
      return this.var_samp(Column$.MODULE$.apply(columnName));
   }

   public Column var_pop(final Column e) {
      return Column$.MODULE$.fn("var_pop", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column var_pop(final String columnName) {
      return this.var_pop(Column$.MODULE$.apply(columnName));
   }

   public Column regr_avgx(final Column y, final Column x) {
      return Column$.MODULE$.fn("regr_avgx", .MODULE$.wrapRefArray(new Column[]{y, x}));
   }

   public Column regr_avgy(final Column y, final Column x) {
      return Column$.MODULE$.fn("regr_avgy", .MODULE$.wrapRefArray(new Column[]{y, x}));
   }

   public Column regr_count(final Column y, final Column x) {
      return Column$.MODULE$.fn("regr_count", .MODULE$.wrapRefArray(new Column[]{y, x}));
   }

   public Column regr_intercept(final Column y, final Column x) {
      return Column$.MODULE$.fn("regr_intercept", .MODULE$.wrapRefArray(new Column[]{y, x}));
   }

   public Column regr_r2(final Column y, final Column x) {
      return Column$.MODULE$.fn("regr_r2", .MODULE$.wrapRefArray(new Column[]{y, x}));
   }

   public Column regr_slope(final Column y, final Column x) {
      return Column$.MODULE$.fn("regr_slope", .MODULE$.wrapRefArray(new Column[]{y, x}));
   }

   public Column regr_sxx(final Column y, final Column x) {
      return Column$.MODULE$.fn("regr_sxx", .MODULE$.wrapRefArray(new Column[]{y, x}));
   }

   public Column regr_sxy(final Column y, final Column x) {
      return Column$.MODULE$.fn("regr_sxy", .MODULE$.wrapRefArray(new Column[]{y, x}));
   }

   public Column regr_syy(final Column y, final Column x) {
      return Column$.MODULE$.fn("regr_syy", .MODULE$.wrapRefArray(new Column[]{y, x}));
   }

   public Column any_value(final Column e) {
      return Column$.MODULE$.fn("any_value", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column any_value(final Column e, final Column ignoreNulls) {
      return Column$.MODULE$.fn("any_value", .MODULE$.wrapRefArray(new Column[]{e, ignoreNulls}));
   }

   public Column count_if(final Column e) {
      return Column$.MODULE$.fn("count_if", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column histogram_numeric(final Column e, final Column nBins) {
      return Column$.MODULE$.fn("histogram_numeric", .MODULE$.wrapRefArray(new Column[]{e, nBins}));
   }

   public Column every(final Column e) {
      return Column$.MODULE$.fn("every", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column bool_and(final Column e) {
      return Column$.MODULE$.fn("bool_and", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column some(final Column e) {
      return Column$.MODULE$.fn("some", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column any(final Column e) {
      return Column$.MODULE$.fn("any", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column bool_or(final Column e) {
      return Column$.MODULE$.fn("bool_or", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column bit_and(final Column e) {
      return Column$.MODULE$.fn("bit_and", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column bit_or(final Column e) {
      return Column$.MODULE$.fn("bit_or", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column bit_xor(final Column e) {
      return Column$.MODULE$.fn("bit_xor", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column cume_dist() {
      return Column$.MODULE$.fn("cume_dist", scala.collection.immutable.Nil..MODULE$);
   }

   public Column dense_rank() {
      return Column$.MODULE$.fn("dense_rank", scala.collection.immutable.Nil..MODULE$);
   }

   public Column lag(final Column e, final int offset) {
      return this.lag((Column)e, offset, (Object)null);
   }

   public Column lag(final String columnName, final int offset) {
      return this.lag((String)columnName, offset, (Object)null);
   }

   public Column lag(final String columnName, final int offset, final Object defaultValue) {
      return this.lag(Column$.MODULE$.apply(columnName), offset, defaultValue);
   }

   public Column lag(final Column e, final int offset, final Object defaultValue) {
      return this.lag(e, offset, defaultValue, false);
   }

   public Column lag(final Column e, final int offset, final Object defaultValue, final boolean ignoreNulls) {
      return Column$.MODULE$.fn("lag", false, .MODULE$.wrapRefArray(new Column[]{e, this.lit(BoxesRunTime.boxToInteger(offset)), this.lit(defaultValue), this.lit(BoxesRunTime.boxToBoolean(ignoreNulls))}));
   }

   public Column lead(final String columnName, final int offset) {
      return this.lead((String)columnName, offset, (Object)null);
   }

   public Column lead(final Column e, final int offset) {
      return this.lead((Column)e, offset, (Object)null);
   }

   public Column lead(final String columnName, final int offset, final Object defaultValue) {
      return this.lead(Column$.MODULE$.apply(columnName), offset, defaultValue);
   }

   public Column lead(final Column e, final int offset, final Object defaultValue) {
      return this.lead(e, offset, defaultValue, false);
   }

   public Column lead(final Column e, final int offset, final Object defaultValue, final boolean ignoreNulls) {
      return Column$.MODULE$.fn("lead", false, .MODULE$.wrapRefArray(new Column[]{e, this.lit(BoxesRunTime.boxToInteger(offset)), this.lit(defaultValue), this.lit(BoxesRunTime.boxToBoolean(ignoreNulls))}));
   }

   public Column nth_value(final Column e, final int offset, final boolean ignoreNulls) {
      return Column$.MODULE$.fn("nth_value", false, .MODULE$.wrapRefArray(new Column[]{e, this.lit(BoxesRunTime.boxToInteger(offset)), this.lit(BoxesRunTime.boxToBoolean(ignoreNulls))}));
   }

   public Column nth_value(final Column e, final int offset) {
      return this.nth_value(e, offset, false);
   }

   public Column ntile(final int n) {
      return Column$.MODULE$.fn("ntile", .MODULE$.wrapRefArray(new Column[]{this.lit(BoxesRunTime.boxToInteger(n))}));
   }

   public Column percent_rank() {
      return Column$.MODULE$.fn("percent_rank", scala.collection.immutable.Nil..MODULE$);
   }

   public Column rank() {
      return Column$.MODULE$.fn("rank", scala.collection.immutable.Nil..MODULE$);
   }

   public Column row_number() {
      return Column$.MODULE$.fn("row_number", scala.collection.immutable.Nil..MODULE$);
   }

   public Column array(final Seq cols) {
      return Column$.MODULE$.fn("array", cols);
   }

   public Column array(final String colName, final Seq colNames) {
      return this.array((Seq)((IterableOps)colNames.$plus$colon(colName)).map((colNamex) -> MODULE$.col(colNamex)));
   }

   public Column map(final Seq cols) {
      return Column$.MODULE$.fn("map", cols);
   }

   public Column named_struct(final Seq cols) {
      return Column$.MODULE$.fn("named_struct", cols);
   }

   public Column map_from_arrays(final Column keys, final Column values) {
      return Column$.MODULE$.fn("map_from_arrays", .MODULE$.wrapRefArray(new Column[]{keys, values}));
   }

   public Column str_to_map(final Column text, final Column pairDelim, final Column keyValueDelim) {
      return Column$.MODULE$.fn("str_to_map", .MODULE$.wrapRefArray(new Column[]{text, pairDelim, keyValueDelim}));
   }

   public Column str_to_map(final Column text, final Column pairDelim) {
      return Column$.MODULE$.fn("str_to_map", .MODULE$.wrapRefArray(new Column[]{text, pairDelim}));
   }

   public Column str_to_map(final Column text) {
      return Column$.MODULE$.fn("str_to_map", .MODULE$.wrapRefArray(new Column[]{text}));
   }

   public Dataset broadcast(final Dataset df) {
      return df.hint("broadcast", (Seq)scala.collection.immutable.Nil..MODULE$);
   }

   public Column coalesce(final Seq e) {
      return Column$.MODULE$.fn("coalesce", e);
   }

   public Column input_file_name() {
      return Column$.MODULE$.fn("input_file_name", scala.collection.immutable.Nil..MODULE$);
   }

   public Column isnan(final Column e) {
      return e.isNaN();
   }

   public Column isnull(final Column e) {
      return e.isNull();
   }

   /** @deprecated */
   public Column monotonicallyIncreasingId() {
      return this.monotonically_increasing_id();
   }

   public Column monotonically_increasing_id() {
      return Column$.MODULE$.fn("monotonically_increasing_id", scala.collection.immutable.Nil..MODULE$);
   }

   public Column nanvl(final Column col1, final Column col2) {
      return Column$.MODULE$.fn("nanvl", .MODULE$.wrapRefArray(new Column[]{col1, col2}));
   }

   public Column negate(final Column e) {
      return e.unary_$minus();
   }

   public Column not(final Column e) {
      return e.unary_$bang();
   }

   public Column rand(final long seed) {
      return Column$.MODULE$.fn("rand", .MODULE$.wrapRefArray(new Column[]{this.lit(BoxesRunTime.boxToLong(seed))}));
   }

   public Column rand() {
      return this.rand(org.apache.spark.util.SparkClassUtils..MODULE$.random().nextLong());
   }

   public Column randn(final long seed) {
      return Column$.MODULE$.fn("randn", .MODULE$.wrapRefArray(new Column[]{this.lit(BoxesRunTime.boxToLong(seed))}));
   }

   public Column randn() {
      return this.randn(org.apache.spark.util.SparkClassUtils..MODULE$.random().nextLong());
   }

   public Column randstr(final Column length) {
      return this.randstr(length, this.lit(BoxesRunTime.boxToLong(org.apache.spark.util.SparkClassUtils..MODULE$.random().nextLong())));
   }

   public Column randstr(final Column length, final Column seed) {
      return Column$.MODULE$.fn("randstr", .MODULE$.wrapRefArray(new Column[]{length, seed}));
   }

   public Column spark_partition_id() {
      return Column$.MODULE$.fn("spark_partition_id", scala.collection.immutable.Nil..MODULE$);
   }

   public Column sqrt(final Column e) {
      return Column$.MODULE$.fn("sqrt", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column sqrt(final String colName) {
      return this.sqrt(Column$.MODULE$.apply(colName));
   }

   public Column try_add(final Column left, final Column right) {
      return Column$.MODULE$.fn("try_add", .MODULE$.wrapRefArray(new Column[]{left, right}));
   }

   public Column try_avg(final Column e) {
      return Column$.MODULE$.fn("try_avg", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column try_divide(final Column left, final Column right) {
      return Column$.MODULE$.fn("try_divide", .MODULE$.wrapRefArray(new Column[]{left, right}));
   }

   public Column try_mod(final Column left, final Column right) {
      return Column$.MODULE$.fn("try_mod", .MODULE$.wrapRefArray(new Column[]{left, right}));
   }

   public Column try_multiply(final Column left, final Column right) {
      return Column$.MODULE$.fn("try_multiply", .MODULE$.wrapRefArray(new Column[]{left, right}));
   }

   public Column try_subtract(final Column left, final Column right) {
      return Column$.MODULE$.fn("try_subtract", .MODULE$.wrapRefArray(new Column[]{left, right}));
   }

   public Column try_sum(final Column e) {
      return Column$.MODULE$.fn("try_sum", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column struct(final Seq cols) {
      return Column$.MODULE$.fn("struct", cols);
   }

   public Column struct(final String colName, final Seq colNames) {
      return this.struct((Seq)((IterableOps)colNames.$plus$colon(colName)).map((colNamex) -> MODULE$.col(colNamex)));
   }

   public Column when(final Column condition, final Object value) {
      return Column$.MODULE$.apply((Function0)(() -> new CaseWhenOtherwise(new scala.collection.immutable..colon.colon(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(condition.node()), MODULE$.lit(value).node()), scala.collection.immutable.Nil..MODULE$), CaseWhenOtherwise$.MODULE$.apply$default$2(), CaseWhenOtherwise$.MODULE$.apply$default$3())));
   }

   /** @deprecated */
   public Column bitwiseNOT(final Column e) {
      return this.bitwise_not(e);
   }

   public Column bitwise_not(final Column e) {
      return Column$.MODULE$.fn("~", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column bit_count(final Column e) {
      return Column$.MODULE$.fn("bit_count", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column bit_get(final Column e, final Column pos) {
      return Column$.MODULE$.fn("bit_get", .MODULE$.wrapRefArray(new Column[]{e, pos}));
   }

   public Column getbit(final Column e, final Column pos) {
      return Column$.MODULE$.fn("getbit", .MODULE$.wrapRefArray(new Column[]{e, pos}));
   }

   public Column expr(final String expr) {
      return Column$.MODULE$.apply((Function0)(() -> new SqlExpression(expr, SqlExpression$.MODULE$.apply$default$2())));
   }

   public Column abs(final Column e) {
      return Column$.MODULE$.fn("abs", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column acos(final Column e) {
      return Column$.MODULE$.fn("acos", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column acos(final String columnName) {
      return this.acos(Column$.MODULE$.apply(columnName));
   }

   public Column acosh(final Column e) {
      return Column$.MODULE$.fn("acosh", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column acosh(final String columnName) {
      return this.acosh(Column$.MODULE$.apply(columnName));
   }

   public Column asin(final Column e) {
      return Column$.MODULE$.fn("asin", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column asin(final String columnName) {
      return this.asin(Column$.MODULE$.apply(columnName));
   }

   public Column asinh(final Column e) {
      return Column$.MODULE$.fn("asinh", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column asinh(final String columnName) {
      return this.asinh(Column$.MODULE$.apply(columnName));
   }

   public Column atan(final Column e) {
      return Column$.MODULE$.fn("atan", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column atan(final String columnName) {
      return this.atan(Column$.MODULE$.apply(columnName));
   }

   public Column atan2(final Column y, final Column x) {
      return Column$.MODULE$.fn("atan2", .MODULE$.wrapRefArray(new Column[]{y, x}));
   }

   public Column atan2(final Column y, final String xName) {
      return this.atan2(y, Column$.MODULE$.apply(xName));
   }

   public Column atan2(final String yName, final Column x) {
      return this.atan2(Column$.MODULE$.apply(yName), x);
   }

   public Column atan2(final String yName, final String xName) {
      return this.atan2(Column$.MODULE$.apply(yName), Column$.MODULE$.apply(xName));
   }

   public Column atan2(final Column y, final double xValue) {
      return this.atan2(y, this.lit(BoxesRunTime.boxToDouble(xValue)));
   }

   public Column atan2(final String yName, final double xValue) {
      return this.atan2(Column$.MODULE$.apply(yName), xValue);
   }

   public Column atan2(final double yValue, final Column x) {
      return this.atan2(this.lit(BoxesRunTime.boxToDouble(yValue)), x);
   }

   public Column atan2(final double yValue, final String xName) {
      return this.atan2(yValue, Column$.MODULE$.apply(xName));
   }

   public Column atanh(final Column e) {
      return Column$.MODULE$.fn("atanh", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column atanh(final String columnName) {
      return this.atanh(Column$.MODULE$.apply(columnName));
   }

   public Column bin(final Column e) {
      return Column$.MODULE$.fn("bin", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column bin(final String columnName) {
      return this.bin(Column$.MODULE$.apply(columnName));
   }

   public Column cbrt(final Column e) {
      return Column$.MODULE$.fn("cbrt", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column cbrt(final String columnName) {
      return this.cbrt(Column$.MODULE$.apply(columnName));
   }

   public Column ceil(final Column e, final Column scale) {
      return Column$.MODULE$.fn("ceil", .MODULE$.wrapRefArray(new Column[]{e, scale}));
   }

   public Column ceil(final Column e) {
      return Column$.MODULE$.fn("ceil", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column ceil(final String columnName) {
      return this.ceil(Column$.MODULE$.apply(columnName));
   }

   public Column ceiling(final Column e, final Column scale) {
      return Column$.MODULE$.fn("ceiling", .MODULE$.wrapRefArray(new Column[]{e, scale}));
   }

   public Column ceiling(final Column e) {
      return Column$.MODULE$.fn("ceiling", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column conv(final Column num, final int fromBase, final int toBase) {
      return Column$.MODULE$.fn("conv", .MODULE$.wrapRefArray(new Column[]{num, this.lit(BoxesRunTime.boxToInteger(fromBase)), this.lit(BoxesRunTime.boxToInteger(toBase))}));
   }

   public Column cos(final Column e) {
      return Column$.MODULE$.fn("cos", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column cos(final String columnName) {
      return this.cos(Column$.MODULE$.apply(columnName));
   }

   public Column cosh(final Column e) {
      return Column$.MODULE$.fn("cosh", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column cosh(final String columnName) {
      return this.cosh(Column$.MODULE$.apply(columnName));
   }

   public Column cot(final Column e) {
      return Column$.MODULE$.fn("cot", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column csc(final Column e) {
      return Column$.MODULE$.fn("csc", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column e() {
      return Column$.MODULE$.fn("e", scala.collection.immutable.Nil..MODULE$);
   }

   public Column exp(final Column e) {
      return Column$.MODULE$.fn("exp", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column exp(final String columnName) {
      return this.exp(Column$.MODULE$.apply(columnName));
   }

   public Column expm1(final Column e) {
      return Column$.MODULE$.fn("expm1", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column expm1(final String columnName) {
      return this.expm1(Column$.MODULE$.apply(columnName));
   }

   public Column factorial(final Column e) {
      return Column$.MODULE$.fn("factorial", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column floor(final Column e, final Column scale) {
      return Column$.MODULE$.fn("floor", .MODULE$.wrapRefArray(new Column[]{e, scale}));
   }

   public Column floor(final Column e) {
      return Column$.MODULE$.fn("floor", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column floor(final String columnName) {
      return this.floor(Column$.MODULE$.apply(columnName));
   }

   public Column greatest(final Seq exprs) {
      return Column$.MODULE$.fn("greatest", exprs);
   }

   public Column greatest(final String columnName, final Seq columnNames) {
      return this.greatest((Seq)((IterableOps)columnNames.$plus$colon(columnName)).map((colName) -> Column$.MODULE$.apply(colName)));
   }

   public Column hex(final Column column) {
      return Column$.MODULE$.fn("hex", .MODULE$.wrapRefArray(new Column[]{column}));
   }

   public Column unhex(final Column column) {
      return Column$.MODULE$.fn("unhex", .MODULE$.wrapRefArray(new Column[]{column}));
   }

   public Column hypot(final Column l, final Column r) {
      return Column$.MODULE$.fn("hypot", .MODULE$.wrapRefArray(new Column[]{l, r}));
   }

   public Column hypot(final Column l, final String rightName) {
      return this.hypot(l, Column$.MODULE$.apply(rightName));
   }

   public Column hypot(final String leftName, final Column r) {
      return this.hypot(Column$.MODULE$.apply(leftName), r);
   }

   public Column hypot(final String leftName, final String rightName) {
      return this.hypot(Column$.MODULE$.apply(leftName), Column$.MODULE$.apply(rightName));
   }

   public Column hypot(final Column l, final double r) {
      return this.hypot(l, this.lit(BoxesRunTime.boxToDouble(r)));
   }

   public Column hypot(final String leftName, final double r) {
      return this.hypot(Column$.MODULE$.apply(leftName), r);
   }

   public Column hypot(final double l, final Column r) {
      return this.hypot(this.lit(BoxesRunTime.boxToDouble(l)), r);
   }

   public Column hypot(final double l, final String rightName) {
      return this.hypot(l, Column$.MODULE$.apply(rightName));
   }

   public Column least(final Seq exprs) {
      return Column$.MODULE$.fn("least", exprs);
   }

   public Column least(final String columnName, final Seq columnNames) {
      return this.least((Seq)((IterableOps)columnNames.$plus$colon(columnName)).map((colName) -> Column$.MODULE$.apply(colName)));
   }

   public Column ln(final Column e) {
      return Column$.MODULE$.fn("ln", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column log(final Column e) {
      return this.ln(e);
   }

   public Column log(final String columnName) {
      return this.log(Column$.MODULE$.apply(columnName));
   }

   public Column log(final double base, final Column a) {
      return Column$.MODULE$.fn("log", .MODULE$.wrapRefArray(new Column[]{this.lit(BoxesRunTime.boxToDouble(base)), a}));
   }

   public Column log(final double base, final String columnName) {
      return this.log(base, Column$.MODULE$.apply(columnName));
   }

   public Column log10(final Column e) {
      return Column$.MODULE$.fn("log10", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column log10(final String columnName) {
      return this.log10(Column$.MODULE$.apply(columnName));
   }

   public Column log1p(final Column e) {
      return Column$.MODULE$.fn("log1p", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column log1p(final String columnName) {
      return this.log1p(Column$.MODULE$.apply(columnName));
   }

   public Column log2(final Column expr) {
      return Column$.MODULE$.fn("log2", .MODULE$.wrapRefArray(new Column[]{expr}));
   }

   public Column log2(final String columnName) {
      return this.log2(Column$.MODULE$.apply(columnName));
   }

   public Column negative(final Column e) {
      return Column$.MODULE$.fn("negative", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column pi() {
      return Column$.MODULE$.fn("pi", scala.collection.immutable.Nil..MODULE$);
   }

   public Column positive(final Column e) {
      return Column$.MODULE$.fn("positive", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column pow(final Column l, final Column r) {
      return Column$.MODULE$.fn("power", .MODULE$.wrapRefArray(new Column[]{l, r}));
   }

   public Column pow(final Column l, final String rightName) {
      return this.pow(l, Column$.MODULE$.apply(rightName));
   }

   public Column pow(final String leftName, final Column r) {
      return this.pow(Column$.MODULE$.apply(leftName), r);
   }

   public Column pow(final String leftName, final String rightName) {
      return this.pow(Column$.MODULE$.apply(leftName), Column$.MODULE$.apply(rightName));
   }

   public Column pow(final Column l, final double r) {
      return this.pow(l, this.lit(BoxesRunTime.boxToDouble(r)));
   }

   public Column pow(final String leftName, final double r) {
      return this.pow(Column$.MODULE$.apply(leftName), r);
   }

   public Column pow(final double l, final Column r) {
      return this.pow(this.lit(BoxesRunTime.boxToDouble(l)), r);
   }

   public Column pow(final double l, final String rightName) {
      return this.pow(l, Column$.MODULE$.apply(rightName));
   }

   public Column power(final Column l, final Column r) {
      return Column$.MODULE$.fn("power", .MODULE$.wrapRefArray(new Column[]{l, r}));
   }

   public Column pmod(final Column dividend, final Column divisor) {
      return Column$.MODULE$.fn("pmod", .MODULE$.wrapRefArray(new Column[]{dividend, divisor}));
   }

   public Column rint(final Column e) {
      return Column$.MODULE$.fn("rint", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column rint(final String columnName) {
      return this.rint(Column$.MODULE$.apply(columnName));
   }

   public Column round(final Column e) {
      return this.round(e, 0);
   }

   public Column round(final Column e, final int scale) {
      return Column$.MODULE$.fn("round", .MODULE$.wrapRefArray(new Column[]{e, this.lit(BoxesRunTime.boxToInteger(scale))}));
   }

   public Column round(final Column e, final Column scale) {
      return Column$.MODULE$.fn("round", .MODULE$.wrapRefArray(new Column[]{e, scale}));
   }

   public Column bround(final Column e) {
      return this.bround(e, 0);
   }

   public Column bround(final Column e, final int scale) {
      return Column$.MODULE$.fn("bround", .MODULE$.wrapRefArray(new Column[]{e, this.lit(BoxesRunTime.boxToInteger(scale))}));
   }

   public Column bround(final Column e, final Column scale) {
      return Column$.MODULE$.fn("bround", .MODULE$.wrapRefArray(new Column[]{e, scale}));
   }

   public Column sec(final Column e) {
      return Column$.MODULE$.fn("sec", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   /** @deprecated */
   public Column shiftLeft(final Column e, final int numBits) {
      return this.shiftleft(e, numBits);
   }

   public Column shiftleft(final Column e, final int numBits) {
      return Column$.MODULE$.fn("shiftleft", .MODULE$.wrapRefArray(new Column[]{e, this.lit(BoxesRunTime.boxToInteger(numBits))}));
   }

   /** @deprecated */
   public Column shiftRight(final Column e, final int numBits) {
      return this.shiftright(e, numBits);
   }

   public Column shiftright(final Column e, final int numBits) {
      return Column$.MODULE$.fn("shiftright", .MODULE$.wrapRefArray(new Column[]{e, this.lit(BoxesRunTime.boxToInteger(numBits))}));
   }

   /** @deprecated */
   public Column shiftRightUnsigned(final Column e, final int numBits) {
      return this.shiftrightunsigned(e, numBits);
   }

   public Column shiftrightunsigned(final Column e, final int numBits) {
      return Column$.MODULE$.fn("shiftrightunsigned", .MODULE$.wrapRefArray(new Column[]{e, this.lit(BoxesRunTime.boxToInteger(numBits))}));
   }

   public Column sign(final Column e) {
      return Column$.MODULE$.fn("sign", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column signum(final Column e) {
      return Column$.MODULE$.fn("signum", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column signum(final String columnName) {
      return this.signum(Column$.MODULE$.apply(columnName));
   }

   public Column sin(final Column e) {
      return Column$.MODULE$.fn("sin", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column sin(final String columnName) {
      return this.sin(Column$.MODULE$.apply(columnName));
   }

   public Column sinh(final Column e) {
      return Column$.MODULE$.fn("sinh", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column sinh(final String columnName) {
      return this.sinh(Column$.MODULE$.apply(columnName));
   }

   public Column tan(final Column e) {
      return Column$.MODULE$.fn("tan", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column tan(final String columnName) {
      return this.tan(Column$.MODULE$.apply(columnName));
   }

   public Column tanh(final Column e) {
      return Column$.MODULE$.fn("tanh", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column tanh(final String columnName) {
      return this.tanh(Column$.MODULE$.apply(columnName));
   }

   /** @deprecated */
   public Column toDegrees(final Column e) {
      return this.degrees(e);
   }

   /** @deprecated */
   public Column toDegrees(final String columnName) {
      return this.degrees(Column$.MODULE$.apply(columnName));
   }

   public Column degrees(final Column e) {
      return Column$.MODULE$.fn("degrees", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column degrees(final String columnName) {
      return this.degrees(Column$.MODULE$.apply(columnName));
   }

   /** @deprecated */
   public Column toRadians(final Column e) {
      return this.radians(e);
   }

   /** @deprecated */
   public Column toRadians(final String columnName) {
      return this.radians(Column$.MODULE$.apply(columnName));
   }

   public Column radians(final Column e) {
      return Column$.MODULE$.fn("radians", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column radians(final String columnName) {
      return this.radians(Column$.MODULE$.apply(columnName));
   }

   public Column width_bucket(final Column v, final Column min, final Column max, final Column numBucket) {
      return Column$.MODULE$.fn("width_bucket", .MODULE$.wrapRefArray(new Column[]{v, min, max, numBucket}));
   }

   public Column current_catalog() {
      return Column$.MODULE$.fn("current_catalog", scala.collection.immutable.Nil..MODULE$);
   }

   public Column current_database() {
      return Column$.MODULE$.fn("current_database", scala.collection.immutable.Nil..MODULE$);
   }

   public Column current_schema() {
      return Column$.MODULE$.fn("current_schema", scala.collection.immutable.Nil..MODULE$);
   }

   public Column current_user() {
      return Column$.MODULE$.fn("current_user", scala.collection.immutable.Nil..MODULE$);
   }

   public Column md5(final Column e) {
      return Column$.MODULE$.fn("md5", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column sha1(final Column e) {
      return Column$.MODULE$.fn("sha1", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column sha2(final Column e, final int numBits) {
      scala.Predef..MODULE$.require(scala.package..MODULE$.Seq().apply(.MODULE$.wrapIntArray(new int[]{0, 224, 256, 384, 512})).contains(BoxesRunTime.boxToInteger(numBits)), () -> "numBits " + numBits + " is not in the permitted values (0, 224, 256, 384, 512)");
      return Column$.MODULE$.fn("sha2", .MODULE$.wrapRefArray(new Column[]{e, this.lit(BoxesRunTime.boxToInteger(numBits))}));
   }

   public Column crc32(final Column e) {
      return Column$.MODULE$.fn("crc32", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column hash(final Seq cols) {
      return Column$.MODULE$.fn("hash", cols);
   }

   public Column xxhash64(final Seq cols) {
      return Column$.MODULE$.fn("xxhash64", cols);
   }

   public Column assert_true(final Column c) {
      return Column$.MODULE$.fn("assert_true", .MODULE$.wrapRefArray(new Column[]{c}));
   }

   public Column assert_true(final Column c, final Column e) {
      return Column$.MODULE$.fn("assert_true", .MODULE$.wrapRefArray(new Column[]{c, e}));
   }

   public Column raise_error(final Column c) {
      return Column$.MODULE$.fn("raise_error", .MODULE$.wrapRefArray(new Column[]{c}));
   }

   public Column hll_sketch_estimate(final Column c) {
      return Column$.MODULE$.fn("hll_sketch_estimate", .MODULE$.wrapRefArray(new Column[]{c}));
   }

   public Column hll_sketch_estimate(final String columnName) {
      return this.hll_sketch_estimate(Column$.MODULE$.apply(columnName));
   }

   public Column hll_union(final Column c1, final Column c2) {
      return Column$.MODULE$.fn("hll_union", .MODULE$.wrapRefArray(new Column[]{c1, c2}));
   }

   public Column hll_union(final String columnName1, final String columnName2) {
      return this.hll_union(Column$.MODULE$.apply(columnName1), Column$.MODULE$.apply(columnName2));
   }

   public Column hll_union(final Column c1, final Column c2, final boolean allowDifferentLgConfigK) {
      return Column$.MODULE$.fn("hll_union", .MODULE$.wrapRefArray(new Column[]{c1, c2, this.lit(BoxesRunTime.boxToBoolean(allowDifferentLgConfigK))}));
   }

   public Column hll_union(final String columnName1, final String columnName2, final boolean allowDifferentLgConfigK) {
      return this.hll_union(Column$.MODULE$.apply(columnName1), Column$.MODULE$.apply(columnName2), allowDifferentLgConfigK);
   }

   public Column user() {
      return Column$.MODULE$.fn("user", scala.collection.immutable.Nil..MODULE$);
   }

   public Column session_user() {
      return Column$.MODULE$.fn("session_user", scala.collection.immutable.Nil..MODULE$);
   }

   public Column uuid() {
      return Column$.MODULE$.fn("uuid", .MODULE$.wrapRefArray(new Column[]{this.lit(BoxesRunTime.boxToLong(org.apache.spark.util.SparkClassUtils..MODULE$.random().nextLong()))}));
   }

   public Column aes_encrypt(final Column input, final Column key, final Column mode, final Column padding, final Column iv, final Column aad) {
      return Column$.MODULE$.fn("aes_encrypt", .MODULE$.wrapRefArray(new Column[]{input, key, mode, padding, iv, aad}));
   }

   public Column aes_encrypt(final Column input, final Column key, final Column mode, final Column padding, final Column iv) {
      return Column$.MODULE$.fn("aes_encrypt", .MODULE$.wrapRefArray(new Column[]{input, key, mode, padding, iv}));
   }

   public Column aes_encrypt(final Column input, final Column key, final Column mode, final Column padding) {
      return Column$.MODULE$.fn("aes_encrypt", .MODULE$.wrapRefArray(new Column[]{input, key, mode, padding}));
   }

   public Column aes_encrypt(final Column input, final Column key, final Column mode) {
      return Column$.MODULE$.fn("aes_encrypt", .MODULE$.wrapRefArray(new Column[]{input, key, mode}));
   }

   public Column aes_encrypt(final Column input, final Column key) {
      return Column$.MODULE$.fn("aes_encrypt", .MODULE$.wrapRefArray(new Column[]{input, key}));
   }

   public Column aes_decrypt(final Column input, final Column key, final Column mode, final Column padding, final Column aad) {
      return Column$.MODULE$.fn("aes_decrypt", .MODULE$.wrapRefArray(new Column[]{input, key, mode, padding, aad}));
   }

   public Column aes_decrypt(final Column input, final Column key, final Column mode, final Column padding) {
      return Column$.MODULE$.fn("aes_decrypt", .MODULE$.wrapRefArray(new Column[]{input, key, mode, padding}));
   }

   public Column aes_decrypt(final Column input, final Column key, final Column mode) {
      return Column$.MODULE$.fn("aes_decrypt", .MODULE$.wrapRefArray(new Column[]{input, key, mode}));
   }

   public Column aes_decrypt(final Column input, final Column key) {
      return Column$.MODULE$.fn("aes_decrypt", .MODULE$.wrapRefArray(new Column[]{input, key}));
   }

   public Column try_aes_decrypt(final Column input, final Column key, final Column mode, final Column padding, final Column aad) {
      return Column$.MODULE$.fn("try_aes_decrypt", .MODULE$.wrapRefArray(new Column[]{input, key, mode, padding, aad}));
   }

   public Column try_aes_decrypt(final Column input, final Column key, final Column mode, final Column padding) {
      return Column$.MODULE$.fn("try_aes_decrypt", .MODULE$.wrapRefArray(new Column[]{input, key, mode, padding}));
   }

   public Column try_aes_decrypt(final Column input, final Column key, final Column mode) {
      return Column$.MODULE$.fn("try_aes_decrypt", .MODULE$.wrapRefArray(new Column[]{input, key, mode}));
   }

   public Column try_aes_decrypt(final Column input, final Column key) {
      return Column$.MODULE$.fn("try_aes_decrypt", .MODULE$.wrapRefArray(new Column[]{input, key}));
   }

   public Column sha(final Column col) {
      return Column$.MODULE$.fn("sha", .MODULE$.wrapRefArray(new Column[]{col}));
   }

   public Column input_file_block_length() {
      return Column$.MODULE$.fn("input_file_block_length", scala.collection.immutable.Nil..MODULE$);
   }

   public Column input_file_block_start() {
      return Column$.MODULE$.fn("input_file_block_start", scala.collection.immutable.Nil..MODULE$);
   }

   public Column reflect(final Seq cols) {
      return Column$.MODULE$.fn("reflect", cols);
   }

   public Column java_method(final Seq cols) {
      return Column$.MODULE$.fn("java_method", cols);
   }

   public Column try_reflect(final Seq cols) {
      return Column$.MODULE$.fn("try_reflect", cols);
   }

   public Column version() {
      return Column$.MODULE$.fn("version", scala.collection.immutable.Nil..MODULE$);
   }

   public Column typeof(final Column col) {
      return Column$.MODULE$.fn("typeof", .MODULE$.wrapRefArray(new Column[]{col}));
   }

   public Column stack(final Seq cols) {
      return Column$.MODULE$.fn("stack", cols);
   }

   public Column uniform(final Column min, final Column max) {
      return this.uniform(min, max, this.lit(BoxesRunTime.boxToLong(org.apache.spark.util.SparkClassUtils..MODULE$.random().nextLong())));
   }

   public Column uniform(final Column min, final Column max, final Column seed) {
      return Column$.MODULE$.fn("uniform", .MODULE$.wrapRefArray(new Column[]{min, max, seed}));
   }

   public Column random(final Column seed) {
      return Column$.MODULE$.fn("random", .MODULE$.wrapRefArray(new Column[]{seed}));
   }

   public Column random() {
      return this.random(this.lit(BoxesRunTime.boxToLong(org.apache.spark.util.SparkClassUtils..MODULE$.random().nextLong())));
   }

   public Column bitmap_bit_position(final Column col) {
      return Column$.MODULE$.fn("bitmap_bit_position", .MODULE$.wrapRefArray(new Column[]{col}));
   }

   public Column bitmap_bucket_number(final Column col) {
      return Column$.MODULE$.fn("bitmap_bucket_number", .MODULE$.wrapRefArray(new Column[]{col}));
   }

   public Column bitmap_construct_agg(final Column col) {
      return Column$.MODULE$.fn("bitmap_construct_agg", .MODULE$.wrapRefArray(new Column[]{col}));
   }

   public Column bitmap_count(final Column col) {
      return Column$.MODULE$.fn("bitmap_count", .MODULE$.wrapRefArray(new Column[]{col}));
   }

   public Column bitmap_or_agg(final Column col) {
      return Column$.MODULE$.fn("bitmap_or_agg", .MODULE$.wrapRefArray(new Column[]{col}));
   }

   public Column ascii(final Column e) {
      return Column$.MODULE$.fn("ascii", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column base64(final Column e) {
      return Column$.MODULE$.fn("base64", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column bit_length(final Column e) {
      return Column$.MODULE$.fn("bit_length", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column concat_ws(final String sep, final Seq exprs) {
      Column$ var10000 = Column$.MODULE$;
      Column var3 = this.lit(sep);
      return var10000.fn("concat_ws", (Seq)exprs.$plus$colon(var3));
   }

   public Column decode(final Column value, final String charset) {
      return Column$.MODULE$.fn("decode", .MODULE$.wrapRefArray(new Column[]{value, this.lit(charset)}));
   }

   public Column encode(final Column value, final String charset) {
      return Column$.MODULE$.fn("encode", .MODULE$.wrapRefArray(new Column[]{value, this.lit(charset)}));
   }

   public Column is_valid_utf8(final Column str) {
      return Column$.MODULE$.fn("is_valid_utf8", .MODULE$.wrapRefArray(new Column[]{str}));
   }

   public Column make_valid_utf8(final Column str) {
      return Column$.MODULE$.fn("make_valid_utf8", .MODULE$.wrapRefArray(new Column[]{str}));
   }

   public Column validate_utf8(final Column str) {
      return Column$.MODULE$.fn("validate_utf8", .MODULE$.wrapRefArray(new Column[]{str}));
   }

   public Column try_validate_utf8(final Column str) {
      return Column$.MODULE$.fn("try_validate_utf8", .MODULE$.wrapRefArray(new Column[]{str}));
   }

   public Column format_number(final Column x, final int d) {
      return Column$.MODULE$.fn("format_number", .MODULE$.wrapRefArray(new Column[]{x, this.lit(BoxesRunTime.boxToInteger(d))}));
   }

   public Column format_string(final String format, final Seq arguments) {
      Column$ var10000 = Column$.MODULE$;
      Column var3 = this.lit(format);
      return var10000.fn("format_string", (Seq)arguments.$plus$colon(var3));
   }

   public Column initcap(final Column e) {
      return Column$.MODULE$.fn("initcap", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column instr(final Column str, final String substring) {
      return this.instr(str, this.lit(substring));
   }

   public Column instr(final Column str, final Column substring) {
      return Column$.MODULE$.fn("instr", .MODULE$.wrapRefArray(new Column[]{str, substring}));
   }

   public Column length(final Column e) {
      return Column$.MODULE$.fn("length", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column len(final Column e) {
      return Column$.MODULE$.fn("len", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column lower(final Column e) {
      return Column$.MODULE$.fn("lower", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column levenshtein(final Column l, final Column r, final int threshold) {
      return Column$.MODULE$.fn("levenshtein", .MODULE$.wrapRefArray(new Column[]{l, r, this.lit(BoxesRunTime.boxToInteger(threshold))}));
   }

   public Column levenshtein(final Column l, final Column r) {
      return Column$.MODULE$.fn("levenshtein", .MODULE$.wrapRefArray(new Column[]{l, r}));
   }

   public Column locate(final String substr, final Column str) {
      return Column$.MODULE$.fn("locate", .MODULE$.wrapRefArray(new Column[]{this.lit(substr), str}));
   }

   public Column locate(final String substr, final Column str, final int pos) {
      return Column$.MODULE$.fn("locate", .MODULE$.wrapRefArray(new Column[]{this.lit(substr), str, this.lit(BoxesRunTime.boxToInteger(pos))}));
   }

   public Column lpad(final Column str, final int len, final String pad) {
      return this.lpad(str, this.lit(BoxesRunTime.boxToInteger(len)), this.lit(pad));
   }

   public Column lpad(final Column str, final int len, final byte[] pad) {
      return this.lpad(str, this.lit(BoxesRunTime.boxToInteger(len)), this.lit(pad));
   }

   public Column lpad(final Column str, final Column len, final Column pad) {
      return Column$.MODULE$.fn("lpad", .MODULE$.wrapRefArray(new Column[]{str, len, pad}));
   }

   public Column ltrim(final Column e) {
      return Column$.MODULE$.fn("ltrim", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column ltrim(final Column e, final String trimString) {
      return this.ltrim(e, this.lit(trimString));
   }

   public Column ltrim(final Column e, final Column trim) {
      return Column$.MODULE$.fn("ltrim", .MODULE$.wrapRefArray(new Column[]{trim, e}));
   }

   public Column octet_length(final Column e) {
      return Column$.MODULE$.fn("octet_length", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column collate(final Column e, final String collation) {
      return Column$.MODULE$.fn("collate", .MODULE$.wrapRefArray(new Column[]{e, this.lit(collation)}));
   }

   public Column collation(final Column e) {
      return Column$.MODULE$.fn("collation", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column rlike(final Column str, final Column regexp) {
      return Column$.MODULE$.fn("rlike", .MODULE$.wrapRefArray(new Column[]{str, regexp}));
   }

   public Column regexp(final Column str, final Column regexp) {
      return Column$.MODULE$.fn("regexp", .MODULE$.wrapRefArray(new Column[]{str, regexp}));
   }

   public Column regexp_like(final Column str, final Column regexp) {
      return Column$.MODULE$.fn("regexp_like", .MODULE$.wrapRefArray(new Column[]{str, regexp}));
   }

   public Column regexp_count(final Column str, final Column regexp) {
      return Column$.MODULE$.fn("regexp_count", .MODULE$.wrapRefArray(new Column[]{str, regexp}));
   }

   public Column regexp_extract(final Column e, final String exp, final int groupIdx) {
      return Column$.MODULE$.fn("regexp_extract", .MODULE$.wrapRefArray(new Column[]{e, this.lit(exp), this.lit(BoxesRunTime.boxToInteger(groupIdx))}));
   }

   public Column regexp_extract_all(final Column str, final Column regexp) {
      return Column$.MODULE$.fn("regexp_extract_all", .MODULE$.wrapRefArray(new Column[]{str, regexp}));
   }

   public Column regexp_extract_all(final Column str, final Column regexp, final Column idx) {
      return Column$.MODULE$.fn("regexp_extract_all", .MODULE$.wrapRefArray(new Column[]{str, regexp, idx}));
   }

   public Column regexp_replace(final Column e, final String pattern, final String replacement) {
      return this.regexp_replace(e, this.lit(pattern), this.lit(replacement));
   }

   public Column regexp_replace(final Column e, final Column pattern, final Column replacement) {
      return Column$.MODULE$.fn("regexp_replace", .MODULE$.wrapRefArray(new Column[]{e, pattern, replacement}));
   }

   public Column regexp_substr(final Column str, final Column regexp) {
      return Column$.MODULE$.fn("regexp_substr", .MODULE$.wrapRefArray(new Column[]{str, regexp}));
   }

   public Column regexp_instr(final Column str, final Column regexp) {
      return Column$.MODULE$.fn("regexp_instr", .MODULE$.wrapRefArray(new Column[]{str, regexp}));
   }

   public Column regexp_instr(final Column str, final Column regexp, final Column idx) {
      return Column$.MODULE$.fn("regexp_instr", .MODULE$.wrapRefArray(new Column[]{str, regexp, idx}));
   }

   public Column unbase64(final Column e) {
      return Column$.MODULE$.fn("unbase64", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column rpad(final Column str, final int len, final String pad) {
      return this.rpad(str, this.lit(BoxesRunTime.boxToInteger(len)), this.lit(pad));
   }

   public Column rpad(final Column str, final int len, final byte[] pad) {
      return this.rpad(str, this.lit(BoxesRunTime.boxToInteger(len)), this.lit(pad));
   }

   public Column rpad(final Column str, final Column len, final Column pad) {
      return Column$.MODULE$.fn("rpad", .MODULE$.wrapRefArray(new Column[]{str, len, pad}));
   }

   public Column repeat(final Column str, final int n) {
      return Column$.MODULE$.fn("repeat", .MODULE$.wrapRefArray(new Column[]{str, this.lit(BoxesRunTime.boxToInteger(n))}));
   }

   public Column repeat(final Column str, final Column n) {
      return Column$.MODULE$.fn("repeat", .MODULE$.wrapRefArray(new Column[]{str, n}));
   }

   public Column rtrim(final Column e) {
      return Column$.MODULE$.fn("rtrim", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column rtrim(final Column e, final String trimString) {
      return this.rtrim(e, this.lit(trimString));
   }

   public Column rtrim(final Column e, final Column trim) {
      return Column$.MODULE$.fn("rtrim", .MODULE$.wrapRefArray(new Column[]{trim, e}));
   }

   public Column soundex(final Column e) {
      return Column$.MODULE$.fn("soundex", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column split(final Column str, final String pattern) {
      return Column$.MODULE$.fn("split", .MODULE$.wrapRefArray(new Column[]{str, this.lit(pattern)}));
   }

   public Column split(final Column str, final Column pattern) {
      return Column$.MODULE$.fn("split", .MODULE$.wrapRefArray(new Column[]{str, pattern}));
   }

   public Column split(final Column str, final String pattern, final int limit) {
      return Column$.MODULE$.fn("split", .MODULE$.wrapRefArray(new Column[]{str, this.lit(pattern), this.lit(BoxesRunTime.boxToInteger(limit))}));
   }

   public Column split(final Column str, final Column pattern, final Column limit) {
      return Column$.MODULE$.fn("split", .MODULE$.wrapRefArray(new Column[]{str, pattern, limit}));
   }

   public Column substring(final Column str, final int pos, final int len) {
      return Column$.MODULE$.fn("substring", .MODULE$.wrapRefArray(new Column[]{str, this.lit(BoxesRunTime.boxToInteger(pos)), this.lit(BoxesRunTime.boxToInteger(len))}));
   }

   public Column substring(final Column str, final Column pos, final Column len) {
      return Column$.MODULE$.fn("substring", .MODULE$.wrapRefArray(new Column[]{str, pos, len}));
   }

   public Column substring_index(final Column str, final String delim, final int count) {
      return Column$.MODULE$.fn("substring_index", .MODULE$.wrapRefArray(new Column[]{str, this.lit(delim), this.lit(BoxesRunTime.boxToInteger(count))}));
   }

   public Column overlay(final Column src, final Column replace, final Column pos, final Column len) {
      return Column$.MODULE$.fn("overlay", .MODULE$.wrapRefArray(new Column[]{src, replace, pos, len}));
   }

   public Column overlay(final Column src, final Column replace, final Column pos) {
      return Column$.MODULE$.fn("overlay", .MODULE$.wrapRefArray(new Column[]{src, replace, pos}));
   }

   public Column sentences(final Column string, final Column language, final Column country) {
      return Column$.MODULE$.fn("sentences", .MODULE$.wrapRefArray(new Column[]{string, language, country}));
   }

   public Column sentences(final Column string, final Column language) {
      return Column$.MODULE$.fn("sentences", .MODULE$.wrapRefArray(new Column[]{string, language}));
   }

   public Column sentences(final Column string) {
      return Column$.MODULE$.fn("sentences", .MODULE$.wrapRefArray(new Column[]{string}));
   }

   public Column translate(final Column src, final String matchingString, final String replaceString) {
      return Column$.MODULE$.fn("translate", .MODULE$.wrapRefArray(new Column[]{src, this.lit(matchingString), this.lit(replaceString)}));
   }

   public Column trim(final Column e) {
      return Column$.MODULE$.fn("trim", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column trim(final Column e, final String trimString) {
      return this.trim(e, this.lit(trimString));
   }

   public Column trim(final Column e, final Column trim) {
      return Column$.MODULE$.fn("trim", .MODULE$.wrapRefArray(new Column[]{trim, e}));
   }

   public Column upper(final Column e) {
      return Column$.MODULE$.fn("upper", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column to_binary(final Column e, final Column f) {
      return Column$.MODULE$.fn("to_binary", .MODULE$.wrapRefArray(new Column[]{e, f}));
   }

   public Column to_binary(final Column e) {
      return Column$.MODULE$.fn("to_binary", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column to_char(final Column e, final Column format) {
      return Column$.MODULE$.fn("to_char", .MODULE$.wrapRefArray(new Column[]{e, format}));
   }

   public Column to_varchar(final Column e, final Column format) {
      return Column$.MODULE$.fn("to_varchar", .MODULE$.wrapRefArray(new Column[]{e, format}));
   }

   public Column to_number(final Column e, final Column format) {
      return Column$.MODULE$.fn("to_number", .MODULE$.wrapRefArray(new Column[]{e, format}));
   }

   public Column replace(final Column src, final Column search, final Column replace) {
      return Column$.MODULE$.fn("replace", .MODULE$.wrapRefArray(new Column[]{src, search, replace}));
   }

   public Column replace(final Column src, final Column search) {
      return Column$.MODULE$.fn("replace", .MODULE$.wrapRefArray(new Column[]{src, search}));
   }

   public Column split_part(final Column str, final Column delimiter, final Column partNum) {
      return Column$.MODULE$.fn("split_part", .MODULE$.wrapRefArray(new Column[]{str, delimiter, partNum}));
   }

   public Column substr(final Column str, final Column pos, final Column len) {
      return Column$.MODULE$.fn("substr", .MODULE$.wrapRefArray(new Column[]{str, pos, len}));
   }

   public Column substr(final Column str, final Column pos) {
      return Column$.MODULE$.fn("substr", .MODULE$.wrapRefArray(new Column[]{str, pos}));
   }

   public Column try_parse_url(final Column url, final Column partToExtract, final Column key) {
      return Column$.MODULE$.fn("try_parse_url", .MODULE$.wrapRefArray(new Column[]{url, partToExtract, key}));
   }

   public Column try_parse_url(final Column url, final Column partToExtract) {
      return Column$.MODULE$.fn("try_parse_url", .MODULE$.wrapRefArray(new Column[]{url, partToExtract}));
   }

   public Column parse_url(final Column url, final Column partToExtract, final Column key) {
      return Column$.MODULE$.fn("parse_url", .MODULE$.wrapRefArray(new Column[]{url, partToExtract, key}));
   }

   public Column parse_url(final Column url, final Column partToExtract) {
      return Column$.MODULE$.fn("parse_url", .MODULE$.wrapRefArray(new Column[]{url, partToExtract}));
   }

   public Column printf(final Column format, final Seq arguments) {
      return Column$.MODULE$.fn("printf", (Seq)arguments.$plus$colon(format));
   }

   public Column url_decode(final Column str) {
      return Column$.MODULE$.fn("url_decode", .MODULE$.wrapRefArray(new Column[]{str}));
   }

   public Column try_url_decode(final Column str) {
      return Column$.MODULE$.fn("try_url_decode", .MODULE$.wrapRefArray(new Column[]{str}));
   }

   public Column url_encode(final Column str) {
      return Column$.MODULE$.fn("url_encode", .MODULE$.wrapRefArray(new Column[]{str}));
   }

   public Column position(final Column substr, final Column str, final Column start) {
      return Column$.MODULE$.fn("position", .MODULE$.wrapRefArray(new Column[]{substr, str, start}));
   }

   public Column position(final Column substr, final Column str) {
      return Column$.MODULE$.fn("position", .MODULE$.wrapRefArray(new Column[]{substr, str}));
   }

   public Column endswith(final Column str, final Column suffix) {
      return Column$.MODULE$.fn("endswith", .MODULE$.wrapRefArray(new Column[]{str, suffix}));
   }

   public Column startswith(final Column str, final Column prefix) {
      return Column$.MODULE$.fn("startswith", .MODULE$.wrapRefArray(new Column[]{str, prefix}));
   }

   public Column char(final Column n) {
      return Column$.MODULE$.fn("char", .MODULE$.wrapRefArray(new Column[]{n}));
   }

   public Column btrim(final Column str) {
      return Column$.MODULE$.fn("btrim", .MODULE$.wrapRefArray(new Column[]{str}));
   }

   public Column btrim(final Column str, final Column trim) {
      return Column$.MODULE$.fn("btrim", .MODULE$.wrapRefArray(new Column[]{str, trim}));
   }

   public Column try_to_binary(final Column e, final Column f) {
      return Column$.MODULE$.fn("try_to_binary", .MODULE$.wrapRefArray(new Column[]{e, f}));
   }

   public Column try_to_binary(final Column e) {
      return Column$.MODULE$.fn("try_to_binary", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column try_to_number(final Column e, final Column format) {
      return Column$.MODULE$.fn("try_to_number", .MODULE$.wrapRefArray(new Column[]{e, format}));
   }

   public Column char_length(final Column str) {
      return Column$.MODULE$.fn("char_length", .MODULE$.wrapRefArray(new Column[]{str}));
   }

   public Column character_length(final Column str) {
      return Column$.MODULE$.fn("character_length", .MODULE$.wrapRefArray(new Column[]{str}));
   }

   public Column chr(final Column n) {
      return Column$.MODULE$.fn("chr", .MODULE$.wrapRefArray(new Column[]{n}));
   }

   public Column contains(final Column left, final Column right) {
      return Column$.MODULE$.fn("contains", .MODULE$.wrapRefArray(new Column[]{left, right}));
   }

   public Column elt(final Seq inputs) {
      return Column$.MODULE$.fn("elt", inputs);
   }

   public Column find_in_set(final Column str, final Column strArray) {
      return Column$.MODULE$.fn("find_in_set", .MODULE$.wrapRefArray(new Column[]{str, strArray}));
   }

   public Column like(final Column str, final Column pattern, final Column escapeChar) {
      return Column$.MODULE$.fn("like", .MODULE$.wrapRefArray(new Column[]{str, pattern, escapeChar}));
   }

   public Column like(final Column str, final Column pattern) {
      return Column$.MODULE$.fn("like", .MODULE$.wrapRefArray(new Column[]{str, pattern}));
   }

   public Column ilike(final Column str, final Column pattern, final Column escapeChar) {
      return Column$.MODULE$.fn("ilike", .MODULE$.wrapRefArray(new Column[]{str, pattern, escapeChar}));
   }

   public Column ilike(final Column str, final Column pattern) {
      return Column$.MODULE$.fn("ilike", .MODULE$.wrapRefArray(new Column[]{str, pattern}));
   }

   public Column lcase(final Column str) {
      return Column$.MODULE$.fn("lcase", .MODULE$.wrapRefArray(new Column[]{str}));
   }

   public Column ucase(final Column str) {
      return Column$.MODULE$.fn("ucase", .MODULE$.wrapRefArray(new Column[]{str}));
   }

   public Column left(final Column str, final Column len) {
      return Column$.MODULE$.fn("left", .MODULE$.wrapRefArray(new Column[]{str, len}));
   }

   public Column right(final Column str, final Column len) {
      return Column$.MODULE$.fn("right", .MODULE$.wrapRefArray(new Column[]{str, len}));
   }

   public Column add_months(final Column startDate, final int numMonths) {
      return this.add_months(startDate, this.lit(BoxesRunTime.boxToInteger(numMonths)));
   }

   public Column add_months(final Column startDate, final Column numMonths) {
      return Column$.MODULE$.fn("add_months", .MODULE$.wrapRefArray(new Column[]{startDate, numMonths}));
   }

   public Column curdate() {
      return Column$.MODULE$.fn("curdate", scala.collection.immutable.Nil..MODULE$);
   }

   public Column current_date() {
      return Column$.MODULE$.fn("current_date", scala.collection.immutable.Nil..MODULE$);
   }

   public Column current_timezone() {
      return Column$.MODULE$.fn("current_timezone", scala.collection.immutable.Nil..MODULE$);
   }

   public Column current_timestamp() {
      return Column$.MODULE$.fn("current_timestamp", scala.collection.immutable.Nil..MODULE$);
   }

   public Column now() {
      return Column$.MODULE$.fn("now", scala.collection.immutable.Nil..MODULE$);
   }

   public Column localtimestamp() {
      return Column$.MODULE$.fn("localtimestamp", scala.collection.immutable.Nil..MODULE$);
   }

   public Column date_format(final Column dateExpr, final String format) {
      return Column$.MODULE$.fn("date_format", .MODULE$.wrapRefArray(new Column[]{dateExpr, this.lit(format)}));
   }

   public Column date_add(final Column start, final int days) {
      return this.date_add(start, this.lit(BoxesRunTime.boxToInteger(days)));
   }

   public Column date_add(final Column start, final Column days) {
      return Column$.MODULE$.fn("date_add", .MODULE$.wrapRefArray(new Column[]{start, days}));
   }

   public Column dateadd(final Column start, final Column days) {
      return Column$.MODULE$.fn("dateadd", .MODULE$.wrapRefArray(new Column[]{start, days}));
   }

   public Column date_sub(final Column start, final int days) {
      return this.date_sub(start, this.lit(BoxesRunTime.boxToInteger(days)));
   }

   public Column date_sub(final Column start, final Column days) {
      return Column$.MODULE$.fn("date_sub", .MODULE$.wrapRefArray(new Column[]{start, days}));
   }

   public Column datediff(final Column end, final Column start) {
      return Column$.MODULE$.fn("datediff", .MODULE$.wrapRefArray(new Column[]{end, start}));
   }

   public Column date_diff(final Column end, final Column start) {
      return Column$.MODULE$.fn("date_diff", .MODULE$.wrapRefArray(new Column[]{end, start}));
   }

   public Column date_from_unix_date(final Column days) {
      return Column$.MODULE$.fn("date_from_unix_date", .MODULE$.wrapRefArray(new Column[]{days}));
   }

   public Column year(final Column e) {
      return Column$.MODULE$.fn("year", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column quarter(final Column e) {
      return Column$.MODULE$.fn("quarter", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column month(final Column e) {
      return Column$.MODULE$.fn("month", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column dayofweek(final Column e) {
      return Column$.MODULE$.fn("dayofweek", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column dayofmonth(final Column e) {
      return Column$.MODULE$.fn("dayofmonth", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column day(final Column e) {
      return Column$.MODULE$.fn("day", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column dayofyear(final Column e) {
      return Column$.MODULE$.fn("dayofyear", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column hour(final Column e) {
      return Column$.MODULE$.fn("hour", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column extract(final Column field, final Column source) {
      return Column$.MODULE$.fn("extract", .MODULE$.wrapRefArray(new Column[]{field, source}));
   }

   public Column date_part(final Column field, final Column source) {
      return Column$.MODULE$.fn("date_part", .MODULE$.wrapRefArray(new Column[]{field, source}));
   }

   public Column datepart(final Column field, final Column source) {
      return Column$.MODULE$.fn("datepart", .MODULE$.wrapRefArray(new Column[]{field, source}));
   }

   public Column last_day(final Column e) {
      return Column$.MODULE$.fn("last_day", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column minute(final Column e) {
      return Column$.MODULE$.fn("minute", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column weekday(final Column e) {
      return Column$.MODULE$.fn("weekday", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column make_date(final Column year, final Column month, final Column day) {
      return Column$.MODULE$.fn("make_date", .MODULE$.wrapRefArray(new Column[]{year, month, day}));
   }

   public Column months_between(final Column end, final Column start) {
      return Column$.MODULE$.fn("months_between", .MODULE$.wrapRefArray(new Column[]{end, start}));
   }

   public Column months_between(final Column end, final Column start, final boolean roundOff) {
      return Column$.MODULE$.fn("months_between", .MODULE$.wrapRefArray(new Column[]{end, start, this.lit(BoxesRunTime.boxToBoolean(roundOff))}));
   }

   public Column next_day(final Column date, final String dayOfWeek) {
      return this.next_day(date, this.lit(dayOfWeek));
   }

   public Column next_day(final Column date, final Column dayOfWeek) {
      return Column$.MODULE$.fn("next_day", .MODULE$.wrapRefArray(new Column[]{date, dayOfWeek}));
   }

   public Column second(final Column e) {
      return Column$.MODULE$.fn("second", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column weekofyear(final Column e) {
      return Column$.MODULE$.fn("weekofyear", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column from_unixtime(final Column ut) {
      return Column$.MODULE$.fn("from_unixtime", .MODULE$.wrapRefArray(new Column[]{ut}));
   }

   public Column from_unixtime(final Column ut, final String f) {
      return Column$.MODULE$.fn("from_unixtime", .MODULE$.wrapRefArray(new Column[]{ut, this.lit(f)}));
   }

   public Column unix_timestamp() {
      return this.unix_timestamp(this.current_timestamp());
   }

   public Column unix_timestamp(final Column s) {
      return Column$.MODULE$.fn("unix_timestamp", .MODULE$.wrapRefArray(new Column[]{s}));
   }

   public Column unix_timestamp(final Column s, final String p) {
      return Column$.MODULE$.fn("unix_timestamp", .MODULE$.wrapRefArray(new Column[]{s, this.lit(p)}));
   }

   public Column to_timestamp(final Column s) {
      return Column$.MODULE$.fn("to_timestamp", .MODULE$.wrapRefArray(new Column[]{s}));
   }

   public Column to_timestamp(final Column s, final String fmt) {
      return Column$.MODULE$.fn("to_timestamp", .MODULE$.wrapRefArray(new Column[]{s, this.lit(fmt)}));
   }

   public Column try_to_timestamp(final Column s, final Column format) {
      return Column$.MODULE$.fn("try_to_timestamp", .MODULE$.wrapRefArray(new Column[]{s, format}));
   }

   public Column try_to_timestamp(final Column s) {
      return Column$.MODULE$.fn("try_to_timestamp", .MODULE$.wrapRefArray(new Column[]{s}));
   }

   public Column to_date(final Column e) {
      return Column$.MODULE$.fn("to_date", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column to_date(final Column e, final String fmt) {
      return Column$.MODULE$.fn("to_date", .MODULE$.wrapRefArray(new Column[]{e, this.lit(fmt)}));
   }

   public Column unix_date(final Column e) {
      return Column$.MODULE$.fn("unix_date", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column unix_micros(final Column e) {
      return Column$.MODULE$.fn("unix_micros", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column unix_millis(final Column e) {
      return Column$.MODULE$.fn("unix_millis", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column unix_seconds(final Column e) {
      return Column$.MODULE$.fn("unix_seconds", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column trunc(final Column date, final String format) {
      return Column$.MODULE$.fn("trunc", .MODULE$.wrapRefArray(new Column[]{date, this.lit(format)}));
   }

   public Column date_trunc(final String format, final Column timestamp) {
      return Column$.MODULE$.fn("date_trunc", .MODULE$.wrapRefArray(new Column[]{this.lit(format), timestamp}));
   }

   public Column from_utc_timestamp(final Column ts, final String tz) {
      return this.from_utc_timestamp(ts, this.lit(tz));
   }

   public Column from_utc_timestamp(final Column ts, final Column tz) {
      return Column$.MODULE$.fn("from_utc_timestamp", .MODULE$.wrapRefArray(new Column[]{ts, tz}));
   }

   public Column to_utc_timestamp(final Column ts, final String tz) {
      return this.to_utc_timestamp(ts, this.lit(tz));
   }

   public Column to_utc_timestamp(final Column ts, final Column tz) {
      return Column$.MODULE$.fn("to_utc_timestamp", .MODULE$.wrapRefArray(new Column[]{ts, tz}));
   }

   public Column window(final Column timeColumn, final String windowDuration, final String slideDuration, final String startTime) {
      return Column$.MODULE$.fn("window", .MODULE$.wrapRefArray(new Column[]{timeColumn, this.lit(windowDuration), this.lit(slideDuration), this.lit(startTime)}));
   }

   public Column window(final Column timeColumn, final String windowDuration, final String slideDuration) {
      return this.window(timeColumn, windowDuration, slideDuration, "0 second");
   }

   public Column window(final Column timeColumn, final String windowDuration) {
      return this.window(timeColumn, windowDuration, windowDuration, "0 second");
   }

   public Column window_time(final Column windowColumn) {
      return Column$.MODULE$.fn("window_time", .MODULE$.wrapRefArray(new Column[]{windowColumn}));
   }

   public Column session_window(final Column timeColumn, final String gapDuration) {
      return this.session_window(timeColumn, this.lit(gapDuration));
   }

   public Column session_window(final Column timeColumn, final Column gapDuration) {
      return Column$.MODULE$.fn("session_window", .MODULE$.wrapRefArray(new Column[]{timeColumn, gapDuration}));
   }

   public Column timestamp_seconds(final Column e) {
      return Column$.MODULE$.fn("timestamp_seconds", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column timestamp_millis(final Column e) {
      return Column$.MODULE$.fn("timestamp_millis", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column timestamp_micros(final Column e) {
      return Column$.MODULE$.fn("timestamp_micros", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column timestamp_diff(final String unit, final Column start, final Column end) {
      return Column$.MODULE$.internalFn("timestampdiff", .MODULE$.wrapRefArray(new Column[]{this.lit(unit), start, end}));
   }

   public Column timestamp_add(final String unit, final Column quantity, final Column ts) {
      return Column$.MODULE$.internalFn("timestampadd", .MODULE$.wrapRefArray(new Column[]{this.lit(unit), quantity, ts}));
   }

   public Column to_timestamp_ltz(final Column timestamp, final Column format) {
      return Column$.MODULE$.fn("to_timestamp_ltz", .MODULE$.wrapRefArray(new Column[]{timestamp, format}));
   }

   public Column to_timestamp_ltz(final Column timestamp) {
      return Column$.MODULE$.fn("to_timestamp_ltz", .MODULE$.wrapRefArray(new Column[]{timestamp}));
   }

   public Column to_timestamp_ntz(final Column timestamp, final Column format) {
      return Column$.MODULE$.fn("to_timestamp_ntz", .MODULE$.wrapRefArray(new Column[]{timestamp, format}));
   }

   public Column to_timestamp_ntz(final Column timestamp) {
      return Column$.MODULE$.fn("to_timestamp_ntz", .MODULE$.wrapRefArray(new Column[]{timestamp}));
   }

   public Column to_unix_timestamp(final Column timeExp, final Column format) {
      return Column$.MODULE$.fn("to_unix_timestamp", .MODULE$.wrapRefArray(new Column[]{timeExp, format}));
   }

   public Column to_unix_timestamp(final Column timeExp) {
      return Column$.MODULE$.fn("to_unix_timestamp", .MODULE$.wrapRefArray(new Column[]{timeExp}));
   }

   public Column monthname(final Column timeExp) {
      return Column$.MODULE$.fn("monthname", .MODULE$.wrapRefArray(new Column[]{timeExp}));
   }

   public Column dayname(final Column timeExp) {
      return Column$.MODULE$.fn("dayname", .MODULE$.wrapRefArray(new Column[]{timeExp}));
   }

   public Column array_contains(final Column column, final Object value) {
      return Column$.MODULE$.fn("array_contains", .MODULE$.wrapRefArray(new Column[]{column, this.lit(value)}));
   }

   public Column array_append(final Column column, final Object element) {
      return Column$.MODULE$.fn("array_append", .MODULE$.wrapRefArray(new Column[]{column, this.lit(element)}));
   }

   public Column arrays_overlap(final Column a1, final Column a2) {
      return Column$.MODULE$.fn("arrays_overlap", .MODULE$.wrapRefArray(new Column[]{a1, a2}));
   }

   public Column slice(final Column x, final int start, final int length) {
      return this.slice(x, this.lit(BoxesRunTime.boxToInteger(start)), this.lit(BoxesRunTime.boxToInteger(length)));
   }

   public Column slice(final Column x, final Column start, final Column length) {
      return Column$.MODULE$.fn("slice", .MODULE$.wrapRefArray(new Column[]{x, start, length}));
   }

   public Column array_join(final Column column, final String delimiter, final String nullReplacement) {
      return Column$.MODULE$.fn("array_join", .MODULE$.wrapRefArray(new Column[]{column, this.lit(delimiter), this.lit(nullReplacement)}));
   }

   public Column array_join(final Column column, final String delimiter) {
      return Column$.MODULE$.fn("array_join", .MODULE$.wrapRefArray(new Column[]{column, this.lit(delimiter)}));
   }

   public Column concat(final Seq exprs) {
      return Column$.MODULE$.fn("concat", exprs);
   }

   public Column array_position(final Column column, final Object value) {
      return Column$.MODULE$.fn("array_position", .MODULE$.wrapRefArray(new Column[]{column, this.lit(value)}));
   }

   public Column element_at(final Column column, final Object value) {
      return Column$.MODULE$.fn("element_at", .MODULE$.wrapRefArray(new Column[]{column, this.lit(value)}));
   }

   public Column try_element_at(final Column column, final Column value) {
      return Column$.MODULE$.fn("try_element_at", .MODULE$.wrapRefArray(new Column[]{column, value}));
   }

   public Column get(final Column column, final Column index) {
      return Column$.MODULE$.fn("get", .MODULE$.wrapRefArray(new Column[]{column, index}));
   }

   public Column array_sort(final Column e) {
      return Column$.MODULE$.fn("array_sort", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column array_sort(final Column e, final Function2 comparator) {
      return Column$.MODULE$.fn("array_sort", .MODULE$.wrapRefArray(new Column[]{e, this.createLambda(comparator)}));
   }

   public Column array_remove(final Column column, final Object element) {
      return Column$.MODULE$.fn("array_remove", .MODULE$.wrapRefArray(new Column[]{column, this.lit(element)}));
   }

   public Column array_compact(final Column column) {
      return Column$.MODULE$.fn("array_compact", .MODULE$.wrapRefArray(new Column[]{column}));
   }

   public Column array_prepend(final Column column, final Object element) {
      return Column$.MODULE$.fn("array_prepend", .MODULE$.wrapRefArray(new Column[]{column, this.lit(element)}));
   }

   public Column array_distinct(final Column e) {
      return Column$.MODULE$.fn("array_distinct", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column array_intersect(final Column col1, final Column col2) {
      return Column$.MODULE$.fn("array_intersect", .MODULE$.wrapRefArray(new Column[]{col1, col2}));
   }

   public Column array_insert(final Column arr, final Column pos, final Column value) {
      return Column$.MODULE$.fn("array_insert", .MODULE$.wrapRefArray(new Column[]{arr, pos, value}));
   }

   public Column array_union(final Column col1, final Column col2) {
      return Column$.MODULE$.fn("array_union", .MODULE$.wrapRefArray(new Column[]{col1, col2}));
   }

   public Column array_except(final Column col1, final Column col2) {
      return Column$.MODULE$.fn("array_except", .MODULE$.wrapRefArray(new Column[]{col1, col2}));
   }

   private Column createLambda(final Function1 f) {
      UnresolvedNamedLambdaVariable x = UnresolvedNamedLambdaVariable$.MODULE$.apply("x");
      ColumnNode function = ((Column)f.apply(Column$.MODULE$.apply((Function0)(() -> x)))).node();
      return Column$.MODULE$.apply((Function0)(() -> LambdaFunction$.MODULE$.apply(function, new scala.collection.immutable..colon.colon(x, scala.collection.immutable.Nil..MODULE$))));
   }

   private Column createLambda(final Function2 f) {
      UnresolvedNamedLambdaVariable x = UnresolvedNamedLambdaVariable$.MODULE$.apply("x");
      UnresolvedNamedLambdaVariable y = UnresolvedNamedLambdaVariable$.MODULE$.apply("y");
      ColumnNode function = ((Column)f.apply(Column$.MODULE$.apply((Function0)(() -> x)), Column$.MODULE$.apply((Function0)(() -> y)))).node();
      return Column$.MODULE$.apply((Function0)(() -> LambdaFunction$.MODULE$.apply(function, new scala.collection.immutable..colon.colon(x, new scala.collection.immutable..colon.colon(y, scala.collection.immutable.Nil..MODULE$)))));
   }

   private Column createLambda(final Function3 f) {
      UnresolvedNamedLambdaVariable x = UnresolvedNamedLambdaVariable$.MODULE$.apply("x");
      UnresolvedNamedLambdaVariable y = UnresolvedNamedLambdaVariable$.MODULE$.apply("y");
      UnresolvedNamedLambdaVariable z = UnresolvedNamedLambdaVariable$.MODULE$.apply("z");
      ColumnNode function = ((Column)f.apply(Column$.MODULE$.apply((Function0)(() -> x)), Column$.MODULE$.apply((Function0)(() -> y)), Column$.MODULE$.apply((Function0)(() -> z)))).node();
      return Column$.MODULE$.apply((Function0)(() -> LambdaFunction$.MODULE$.apply(function, new scala.collection.immutable..colon.colon(x, new scala.collection.immutable..colon.colon(y, new scala.collection.immutable..colon.colon(z, scala.collection.immutable.Nil..MODULE$))))));
   }

   public Column transform(final Column column, final Function1 f) {
      return Column$.MODULE$.fn("transform", .MODULE$.wrapRefArray(new Column[]{column, this.createLambda(f)}));
   }

   public Column transform(final Column column, final Function2 f) {
      return Column$.MODULE$.fn("transform", .MODULE$.wrapRefArray(new Column[]{column, this.createLambda(f)}));
   }

   public Column exists(final Column column, final Function1 f) {
      return Column$.MODULE$.fn("exists", .MODULE$.wrapRefArray(new Column[]{column, this.createLambda(f)}));
   }

   public Column forall(final Column column, final Function1 f) {
      return Column$.MODULE$.fn("forall", .MODULE$.wrapRefArray(new Column[]{column, this.createLambda(f)}));
   }

   public Column filter(final Column column, final Function1 f) {
      return Column$.MODULE$.fn("filter", .MODULE$.wrapRefArray(new Column[]{column, this.createLambda(f)}));
   }

   public Column filter(final Column column, final Function2 f) {
      return Column$.MODULE$.fn("filter", .MODULE$.wrapRefArray(new Column[]{column, this.createLambda(f)}));
   }

   public Column aggregate(final Column expr, final Column initialValue, final Function2 merge, final Function1 finish) {
      return Column$.MODULE$.fn("aggregate", .MODULE$.wrapRefArray(new Column[]{expr, initialValue, this.createLambda(merge), this.createLambda(finish)}));
   }

   public Column aggregate(final Column expr, final Column initialValue, final Function2 merge) {
      return this.aggregate(expr, initialValue, merge, (c) -> c);
   }

   public Column reduce(final Column expr, final Column initialValue, final Function2 merge, final Function1 finish) {
      return Column$.MODULE$.fn("reduce", .MODULE$.wrapRefArray(new Column[]{expr, initialValue, this.createLambda(merge), this.createLambda(finish)}));
   }

   public Column reduce(final Column expr, final Column initialValue, final Function2 merge) {
      return this.reduce(expr, initialValue, merge, (c) -> c);
   }

   public Column zip_with(final Column left, final Column right, final Function2 f) {
      return Column$.MODULE$.fn("zip_with", .MODULE$.wrapRefArray(new Column[]{left, right, this.createLambda(f)}));
   }

   public Column transform_keys(final Column expr, final Function2 f) {
      return Column$.MODULE$.fn("transform_keys", .MODULE$.wrapRefArray(new Column[]{expr, this.createLambda(f)}));
   }

   public Column transform_values(final Column expr, final Function2 f) {
      return Column$.MODULE$.fn("transform_values", .MODULE$.wrapRefArray(new Column[]{expr, this.createLambda(f)}));
   }

   public Column map_filter(final Column expr, final Function2 f) {
      return Column$.MODULE$.fn("map_filter", .MODULE$.wrapRefArray(new Column[]{expr, this.createLambda(f)}));
   }

   public Column map_zip_with(final Column left, final Column right, final Function3 f) {
      return Column$.MODULE$.fn("map_zip_with", .MODULE$.wrapRefArray(new Column[]{left, right, this.createLambda(f)}));
   }

   public Column explode(final Column e) {
      return Column$.MODULE$.fn("explode", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column explode_outer(final Column e) {
      return Column$.MODULE$.fn("explode_outer", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column posexplode(final Column e) {
      return Column$.MODULE$.fn("posexplode", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column posexplode_outer(final Column e) {
      return Column$.MODULE$.fn("posexplode_outer", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column inline(final Column e) {
      return Column$.MODULE$.fn("inline", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column inline_outer(final Column e) {
      return Column$.MODULE$.fn("inline_outer", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column get_json_object(final Column e, final String path) {
      return Column$.MODULE$.fn("get_json_object", .MODULE$.wrapRefArray(new Column[]{e, this.lit(path)}));
   }

   public Column json_tuple(final Column json, final Seq fields) {
      scala.Predef..MODULE$.require(fields.nonEmpty(), () -> "at least 1 field name should be given.");
      return Column$.MODULE$.fn("json_tuple", (Seq)((SeqOps)fields.map((literal) -> MODULE$.lit(literal))).$plus$colon(json));
   }

   public Column from_json(final Column e, final StructType schema, final Map options) {
      return this.from_json(e, (DataType)schema, (Map)options);
   }

   public Column from_json(final Column e, final DataType schema, final Map options) {
      return this.from_json(e, this.lit(schema.sql()), options.iterator());
   }

   public Column from_json(final Column e, final StructType schema, final java.util.Map options) {
      return this.from_json(e, schema, scala.jdk.CollectionConverters..MODULE$.MapHasAsScala(options).asScala().toMap(scala..less.colon.less..MODULE$.refl()));
   }

   public Column from_json(final Column e, final DataType schema, final java.util.Map options) {
      return this.from_json(e, schema, scala.jdk.CollectionConverters..MODULE$.MapHasAsScala(options).asScala().toMap(scala..less.colon.less..MODULE$.refl()));
   }

   public Column from_json(final Column e, final StructType schema) {
      return this.from_json(e, schema, scala.Predef..MODULE$.Map().empty());
   }

   public Column from_json(final Column e, final DataType schema) {
      return this.from_json(e, schema, scala.Predef..MODULE$.Map().empty());
   }

   public Column from_json(final Column e, final String schema, final java.util.Map options) {
      return this.from_json(e, schema, scala.jdk.CollectionConverters..MODULE$.MapHasAsScala(options).asScala().toMap(scala..less.colon.less..MODULE$.refl()));
   }

   public Column from_json(final Column e, final String schema, final Map options) {
      return this.from_json(e, this.lit(schema), scala.jdk.CollectionConverters..MODULE$.MapHasAsJava(options).asJava());
   }

   public Column from_json(final Column e, final Column schema) {
      return this.from_json(e, schema, scala.jdk.CollectionConverters..MODULE$.MapHasAsJava(scala.Predef..MODULE$.Map().empty()).asJava());
   }

   public Column from_json(final Column e, final Column schema, final java.util.Map options) {
      return this.from_json(e, schema, scala.jdk.CollectionConverters..MODULE$.MapHasAsScala(options).asScala().iterator());
   }

   private Column from_json(final Column e, final Column schema, final Iterator options) {
      return Column$.MODULE$.fnWithOptions("from_json", options, .MODULE$.wrapRefArray(new Column[]{e, schema}));
   }

   public Column try_parse_json(final Column json) {
      return Column$.MODULE$.fn("try_parse_json", .MODULE$.wrapRefArray(new Column[]{json}));
   }

   public Column parse_json(final Column json) {
      return Column$.MODULE$.fn("parse_json", .MODULE$.wrapRefArray(new Column[]{json}));
   }

   public Column to_variant_object(final Column col) {
      return Column$.MODULE$.fn("to_variant_object", .MODULE$.wrapRefArray(new Column[]{col}));
   }

   public Column is_variant_null(final Column v) {
      return Column$.MODULE$.fn("is_variant_null", .MODULE$.wrapRefArray(new Column[]{v}));
   }

   public Column variant_get(final Column v, final String path, final String targetType) {
      return Column$.MODULE$.fn("variant_get", .MODULE$.wrapRefArray(new Column[]{v, this.lit(path), this.lit(targetType)}));
   }

   public Column variant_get(final Column v, final Column path, final String targetType) {
      return Column$.MODULE$.fn("variant_get", .MODULE$.wrapRefArray(new Column[]{v, path, this.lit(targetType)}));
   }

   public Column try_variant_get(final Column v, final String path, final String targetType) {
      return Column$.MODULE$.fn("try_variant_get", .MODULE$.wrapRefArray(new Column[]{v, this.lit(path), this.lit(targetType)}));
   }

   public Column try_variant_get(final Column v, final Column path, final String targetType) {
      return Column$.MODULE$.fn("try_variant_get", .MODULE$.wrapRefArray(new Column[]{v, this.lit(path), this.lit(targetType)}));
   }

   public Column schema_of_variant(final Column v) {
      return Column$.MODULE$.fn("schema_of_variant", .MODULE$.wrapRefArray(new Column[]{v}));
   }

   public Column schema_of_variant_agg(final Column v) {
      return Column$.MODULE$.fn("schema_of_variant_agg", .MODULE$.wrapRefArray(new Column[]{v}));
   }

   public Column schema_of_json(final String json) {
      return this.schema_of_json(this.lit(json));
   }

   public Column schema_of_json(final Column json) {
      return Column$.MODULE$.fn("schema_of_json", .MODULE$.wrapRefArray(new Column[]{json}));
   }

   public Column schema_of_json(final Column json, final java.util.Map options) {
      return Column$.MODULE$.fnWithOptions("schema_of_json", scala.jdk.CollectionConverters..MODULE$.MapHasAsScala(options).asScala().iterator(), .MODULE$.wrapRefArray(new Column[]{json}));
   }

   public Column json_array_length(final Column e) {
      return Column$.MODULE$.fn("json_array_length", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column json_object_keys(final Column e) {
      return Column$.MODULE$.fn("json_object_keys", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column to_json(final Column e, final Map options) {
      return Column$.MODULE$.fnWithOptions("to_json", options.iterator(), .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column to_json(final Column e, final java.util.Map options) {
      return this.to_json(e, scala.jdk.CollectionConverters..MODULE$.MapHasAsScala(options).asScala().toMap(scala..less.colon.less..MODULE$.refl()));
   }

   public Column to_json(final Column e) {
      return this.to_json(e, scala.Predef..MODULE$.Map().empty());
   }

   public Column mask(final Column input) {
      return Column$.MODULE$.fn("mask", .MODULE$.wrapRefArray(new Column[]{input}));
   }

   public Column mask(final Column input, final Column upperChar) {
      return Column$.MODULE$.fn("mask", .MODULE$.wrapRefArray(new Column[]{input, upperChar}));
   }

   public Column mask(final Column input, final Column upperChar, final Column lowerChar) {
      return Column$.MODULE$.fn("mask", .MODULE$.wrapRefArray(new Column[]{input, upperChar, lowerChar}));
   }

   public Column mask(final Column input, final Column upperChar, final Column lowerChar, final Column digitChar) {
      return Column$.MODULE$.fn("mask", .MODULE$.wrapRefArray(new Column[]{input, upperChar, lowerChar, digitChar}));
   }

   public Column mask(final Column input, final Column upperChar, final Column lowerChar, final Column digitChar, final Column otherChar) {
      return Column$.MODULE$.fn("mask", .MODULE$.wrapRefArray(new Column[]{input, upperChar, lowerChar, digitChar, otherChar}));
   }

   public Column size(final Column e) {
      return Column$.MODULE$.fn("size", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column cardinality(final Column e) {
      return Column$.MODULE$.fn("cardinality", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column sort_array(final Column e) {
      return this.sort_array(e, true);
   }

   public Column sort_array(final Column e, final boolean asc) {
      return Column$.MODULE$.fn("sort_array", .MODULE$.wrapRefArray(new Column[]{e, this.lit(BoxesRunTime.boxToBoolean(asc))}));
   }

   public Column array_min(final Column e) {
      return Column$.MODULE$.fn("array_min", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column array_max(final Column e) {
      return Column$.MODULE$.fn("array_max", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column array_size(final Column e) {
      return Column$.MODULE$.fn("array_size", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column array_agg(final Column e) {
      return Column$.MODULE$.fn("array_agg", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column shuffle(final Column e) {
      return this.shuffle(e, this.lit(BoxesRunTime.boxToLong(org.apache.spark.util.SparkClassUtils..MODULE$.random().nextLong())));
   }

   public Column shuffle(final Column e, final Column seed) {
      return Column$.MODULE$.fn("shuffle", .MODULE$.wrapRefArray(new Column[]{e, seed}));
   }

   public Column reverse(final Column e) {
      return Column$.MODULE$.fn("reverse", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column flatten(final Column e) {
      return Column$.MODULE$.fn("flatten", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column sequence(final Column start, final Column stop, final Column step) {
      return Column$.MODULE$.fn("sequence", .MODULE$.wrapRefArray(new Column[]{start, stop, step}));
   }

   public Column sequence(final Column start, final Column stop) {
      return Column$.MODULE$.fn("sequence", .MODULE$.wrapRefArray(new Column[]{start, stop}));
   }

   public Column array_repeat(final Column left, final Column right) {
      return Column$.MODULE$.fn("array_repeat", .MODULE$.wrapRefArray(new Column[]{left, right}));
   }

   public Column array_repeat(final Column e, final int count) {
      return this.array_repeat(e, this.lit(BoxesRunTime.boxToInteger(count)));
   }

   public Column map_contains_key(final Column column, final Object key) {
      return Column$.MODULE$.fn("map_contains_key", .MODULE$.wrapRefArray(new Column[]{column, this.lit(key)}));
   }

   public Column map_keys(final Column e) {
      return Column$.MODULE$.fn("map_keys", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column map_values(final Column e) {
      return Column$.MODULE$.fn("map_values", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column map_entries(final Column e) {
      return Column$.MODULE$.fn("map_entries", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column map_from_entries(final Column e) {
      return Column$.MODULE$.fn("map_from_entries", .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column arrays_zip(final Seq e) {
      return Column$.MODULE$.fn("arrays_zip", e);
   }

   public Column map_concat(final Seq cols) {
      return Column$.MODULE$.fn("map_concat", cols);
   }

   public Column from_csv(final Column e, final StructType schema, final Map options) {
      return this.from_csv(e, this.lit(schema.toDDL()), options.iterator());
   }

   public Column from_csv(final Column e, final Column schema, final java.util.Map options) {
      return this.from_csv(e, schema, scala.jdk.CollectionConverters..MODULE$.MapHasAsScala(options).asScala().iterator());
   }

   private Column from_csv(final Column e, final Column schema, final Iterator options) {
      return Column$.MODULE$.fnWithOptions("from_csv", options, .MODULE$.wrapRefArray(new Column[]{e, schema}));
   }

   public Column schema_of_csv(final String csv) {
      return this.schema_of_csv(this.lit(csv));
   }

   public Column schema_of_csv(final Column csv) {
      return this.schema_of_csv(csv, Collections.emptyMap());
   }

   public Column schema_of_csv(final Column csv, final java.util.Map options) {
      return Column$.MODULE$.fnWithOptions("schema_of_csv", scala.jdk.CollectionConverters..MODULE$.MapHasAsScala(options).asScala().iterator(), .MODULE$.wrapRefArray(new Column[]{csv}));
   }

   public Column to_csv(final Column e, final java.util.Map options) {
      return Column$.MODULE$.fnWithOptions("to_csv", scala.jdk.CollectionConverters..MODULE$.MapHasAsScala(options).asScala().iterator(), .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column to_csv(final Column e) {
      return this.to_csv(e, scala.jdk.CollectionConverters..MODULE$.MapHasAsJava(scala.Predef..MODULE$.Map().empty()).asJava());
   }

   public Column from_xml(final Column e, final StructType schema, final java.util.Map options) {
      return this.from_xml(e, this.lit(schema.sql()), scala.jdk.CollectionConverters..MODULE$.MapHasAsScala(options).asScala().iterator());
   }

   public Column from_xml(final Column e, final String schema, final java.util.Map options) {
      return this.from_xml(e, this.lit(schema), options);
   }

   public Column from_xml(final Column e, final Column schema) {
      return this.from_xml(e, schema, scala.package..MODULE$.Iterator().empty());
   }

   public Column from_xml(final Column e, final Column schema, final java.util.Map options) {
      return this.from_xml(e, schema, scala.jdk.CollectionConverters..MODULE$.MapHasAsScala(options).asScala().iterator());
   }

   public Column from_xml(final Column e, final StructType schema) {
      return this.from_xml(e, schema, scala.jdk.CollectionConverters..MODULE$.MapHasAsJava(scala.Predef..MODULE$.Map().empty()).asJava());
   }

   private Column from_xml(final Column e, final Column schema, final Iterator options) {
      return Column$.MODULE$.fnWithOptions("from_xml", options, .MODULE$.wrapRefArray(new Column[]{e, schema}));
   }

   public Column schema_of_xml(final String xml) {
      return this.schema_of_xml(this.lit(xml));
   }

   public Column schema_of_xml(final Column xml) {
      return Column$.MODULE$.fn("schema_of_xml", .MODULE$.wrapRefArray(new Column[]{xml}));
   }

   public Column schema_of_xml(final Column xml, final java.util.Map options) {
      return Column$.MODULE$.fnWithOptions("schema_of_xml", scala.jdk.CollectionConverters..MODULE$.MapHasAsScala(options).asScala().iterator(), .MODULE$.wrapRefArray(new Column[]{xml}));
   }

   public Column to_xml(final Column e, final java.util.Map options) {
      return Column$.MODULE$.fnWithOptions("to_xml", scala.jdk.CollectionConverters..MODULE$.MapHasAsScala(options).asScala().iterator(), .MODULE$.wrapRefArray(new Column[]{e}));
   }

   public Column to_xml(final Column e) {
      return this.to_xml(e, scala.jdk.CollectionConverters..MODULE$.MapHasAsJava(scala.Predef..MODULE$.Map().empty()).asJava());
   }

   public Column years(final Column e) {
      return functions.partitioning$.MODULE$.years(e);
   }

   public Column months(final Column e) {
      return functions.partitioning$.MODULE$.months(e);
   }

   public Column days(final Column e) {
      return functions.partitioning$.MODULE$.days(e);
   }

   public Column xpath(final Column xml, final Column path) {
      return Column$.MODULE$.fn("xpath", .MODULE$.wrapRefArray(new Column[]{xml, path}));
   }

   public Column xpath_boolean(final Column xml, final Column path) {
      return Column$.MODULE$.fn("xpath_boolean", .MODULE$.wrapRefArray(new Column[]{xml, path}));
   }

   public Column xpath_double(final Column xml, final Column path) {
      return Column$.MODULE$.fn("xpath_double", .MODULE$.wrapRefArray(new Column[]{xml, path}));
   }

   public Column xpath_number(final Column xml, final Column path) {
      return Column$.MODULE$.fn("xpath_number", .MODULE$.wrapRefArray(new Column[]{xml, path}));
   }

   public Column xpath_float(final Column xml, final Column path) {
      return Column$.MODULE$.fn("xpath_float", .MODULE$.wrapRefArray(new Column[]{xml, path}));
   }

   public Column xpath_int(final Column xml, final Column path) {
      return Column$.MODULE$.fn("xpath_int", .MODULE$.wrapRefArray(new Column[]{xml, path}));
   }

   public Column xpath_long(final Column xml, final Column path) {
      return Column$.MODULE$.fn("xpath_long", .MODULE$.wrapRefArray(new Column[]{xml, path}));
   }

   public Column xpath_short(final Column xml, final Column path) {
      return Column$.MODULE$.fn("xpath_short", .MODULE$.wrapRefArray(new Column[]{xml, path}));
   }

   public Column xpath_string(final Column xml, final Column path) {
      return Column$.MODULE$.fn("xpath_string", .MODULE$.wrapRefArray(new Column[]{xml, path}));
   }

   public Column hours(final Column e) {
      return functions.partitioning$.MODULE$.hours(e);
   }

   public Column convert_timezone(final Column sourceTz, final Column targetTz, final Column sourceTs) {
      return Column$.MODULE$.fn("convert_timezone", .MODULE$.wrapRefArray(new Column[]{sourceTz, targetTz, sourceTs}));
   }

   public Column convert_timezone(final Column targetTz, final Column sourceTs) {
      return Column$.MODULE$.fn("convert_timezone", .MODULE$.wrapRefArray(new Column[]{targetTz, sourceTs}));
   }

   public Column make_dt_interval(final Column days, final Column hours, final Column mins, final Column secs) {
      return Column$.MODULE$.fn("make_dt_interval", .MODULE$.wrapRefArray(new Column[]{days, hours, mins, secs}));
   }

   public Column make_dt_interval(final Column days, final Column hours, final Column mins) {
      return Column$.MODULE$.fn("make_dt_interval", .MODULE$.wrapRefArray(new Column[]{days, hours, mins}));
   }

   public Column make_dt_interval(final Column days, final Column hours) {
      return Column$.MODULE$.fn("make_dt_interval", .MODULE$.wrapRefArray(new Column[]{days, hours}));
   }

   public Column make_dt_interval(final Column days) {
      return Column$.MODULE$.fn("make_dt_interval", .MODULE$.wrapRefArray(new Column[]{days}));
   }

   public Column make_dt_interval() {
      return Column$.MODULE$.fn("make_dt_interval", scala.collection.immutable.Nil..MODULE$);
   }

   public Column try_make_interval(final Column years, final Column months, final Column weeks, final Column days, final Column hours, final Column mins, final Column secs) {
      return Column$.MODULE$.fn("try_make_interval", .MODULE$.wrapRefArray(new Column[]{years, months, weeks, days, hours, mins, secs}));
   }

   public Column make_interval(final Column years, final Column months, final Column weeks, final Column days, final Column hours, final Column mins, final Column secs) {
      return Column$.MODULE$.fn("make_interval", .MODULE$.wrapRefArray(new Column[]{years, months, weeks, days, hours, mins, secs}));
   }

   public Column try_make_interval(final Column years, final Column months, final Column weeks, final Column days, final Column hours, final Column mins) {
      return Column$.MODULE$.fn("try_make_interval", .MODULE$.wrapRefArray(new Column[]{years, months, weeks, days, hours, mins}));
   }

   public Column make_interval(final Column years, final Column months, final Column weeks, final Column days, final Column hours, final Column mins) {
      return Column$.MODULE$.fn("make_interval", .MODULE$.wrapRefArray(new Column[]{years, months, weeks, days, hours, mins}));
   }

   public Column try_make_interval(final Column years, final Column months, final Column weeks, final Column days, final Column hours) {
      return Column$.MODULE$.fn("try_make_interval", .MODULE$.wrapRefArray(new Column[]{years, months, weeks, days, hours}));
   }

   public Column make_interval(final Column years, final Column months, final Column weeks, final Column days, final Column hours) {
      return Column$.MODULE$.fn("make_interval", .MODULE$.wrapRefArray(new Column[]{years, months, weeks, days, hours}));
   }

   public Column try_make_interval(final Column years, final Column months, final Column weeks, final Column days) {
      return Column$.MODULE$.fn("try_make_interval", .MODULE$.wrapRefArray(new Column[]{years, months, weeks, days}));
   }

   public Column make_interval(final Column years, final Column months, final Column weeks, final Column days) {
      return Column$.MODULE$.fn("make_interval", .MODULE$.wrapRefArray(new Column[]{years, months, weeks, days}));
   }

   public Column try_make_interval(final Column years, final Column months, final Column weeks) {
      return Column$.MODULE$.fn("try_make_interval", .MODULE$.wrapRefArray(new Column[]{years, months, weeks}));
   }

   public Column make_interval(final Column years, final Column months, final Column weeks) {
      return Column$.MODULE$.fn("make_interval", .MODULE$.wrapRefArray(new Column[]{years, months, weeks}));
   }

   public Column try_make_interval(final Column years, final Column months) {
      return Column$.MODULE$.fn("try_make_interval", .MODULE$.wrapRefArray(new Column[]{years, months}));
   }

   public Column make_interval(final Column years, final Column months) {
      return Column$.MODULE$.fn("make_interval", .MODULE$.wrapRefArray(new Column[]{years, months}));
   }

   public Column try_make_interval(final Column years) {
      return Column$.MODULE$.fn("try_make_interval", .MODULE$.wrapRefArray(new Column[]{years}));
   }

   public Column make_interval(final Column years) {
      return Column$.MODULE$.fn("make_interval", .MODULE$.wrapRefArray(new Column[]{years}));
   }

   public Column make_interval() {
      return Column$.MODULE$.fn("make_interval", scala.collection.immutable.Nil..MODULE$);
   }

   public Column make_timestamp(final Column years, final Column months, final Column days, final Column hours, final Column mins, final Column secs, final Column timezone) {
      return Column$.MODULE$.fn("make_timestamp", .MODULE$.wrapRefArray(new Column[]{years, months, days, hours, mins, secs, timezone}));
   }

   public Column make_timestamp(final Column years, final Column months, final Column days, final Column hours, final Column mins, final Column secs) {
      return Column$.MODULE$.fn("make_timestamp", .MODULE$.wrapRefArray(new Column[]{years, months, days, hours, mins, secs}));
   }

   public Column try_make_timestamp(final Column years, final Column months, final Column days, final Column hours, final Column mins, final Column secs, final Column timezone) {
      return Column$.MODULE$.fn("try_make_timestamp", .MODULE$.wrapRefArray(new Column[]{years, months, days, hours, mins, secs, timezone}));
   }

   public Column try_make_timestamp(final Column years, final Column months, final Column days, final Column hours, final Column mins, final Column secs) {
      return Column$.MODULE$.fn("try_make_timestamp", .MODULE$.wrapRefArray(new Column[]{years, months, days, hours, mins, secs}));
   }

   public Column make_timestamp_ltz(final Column years, final Column months, final Column days, final Column hours, final Column mins, final Column secs, final Column timezone) {
      return Column$.MODULE$.fn("make_timestamp_ltz", .MODULE$.wrapRefArray(new Column[]{years, months, days, hours, mins, secs, timezone}));
   }

   public Column make_timestamp_ltz(final Column years, final Column months, final Column days, final Column hours, final Column mins, final Column secs) {
      return Column$.MODULE$.fn("make_timestamp_ltz", .MODULE$.wrapRefArray(new Column[]{years, months, days, hours, mins, secs}));
   }

   public Column try_make_timestamp_ltz(final Column years, final Column months, final Column days, final Column hours, final Column mins, final Column secs, final Column timezone) {
      return Column$.MODULE$.fn("try_make_timestamp_ltz", .MODULE$.wrapRefArray(new Column[]{years, months, days, hours, mins, secs, timezone}));
   }

   public Column try_make_timestamp_ltz(final Column years, final Column months, final Column days, final Column hours, final Column mins, final Column secs) {
      return Column$.MODULE$.fn("try_make_timestamp_ltz", .MODULE$.wrapRefArray(new Column[]{years, months, days, hours, mins, secs}));
   }

   public Column make_timestamp_ntz(final Column years, final Column months, final Column days, final Column hours, final Column mins, final Column secs) {
      return Column$.MODULE$.fn("make_timestamp_ntz", .MODULE$.wrapRefArray(new Column[]{years, months, days, hours, mins, secs}));
   }

   public Column try_make_timestamp_ntz(final Column years, final Column months, final Column days, final Column hours, final Column mins, final Column secs) {
      return Column$.MODULE$.fn("try_make_timestamp_ntz", .MODULE$.wrapRefArray(new Column[]{years, months, days, hours, mins, secs}));
   }

   public Column make_ym_interval(final Column years, final Column months) {
      return Column$.MODULE$.fn("make_ym_interval", .MODULE$.wrapRefArray(new Column[]{years, months}));
   }

   public Column make_ym_interval(final Column years) {
      return Column$.MODULE$.fn("make_ym_interval", .MODULE$.wrapRefArray(new Column[]{years}));
   }

   public Column make_ym_interval() {
      return Column$.MODULE$.fn("make_ym_interval", scala.collection.immutable.Nil..MODULE$);
   }

   public Column bucket(final Column numBuckets, final Column e) {
      return functions.partitioning$.MODULE$.bucket(numBuckets, e);
   }

   public Column bucket(final int numBuckets, final Column e) {
      return functions.partitioning$.MODULE$.bucket(numBuckets, e);
   }

   public Column ifnull(final Column col1, final Column col2) {
      return Column$.MODULE$.fn("ifnull", .MODULE$.wrapRefArray(new Column[]{col1, col2}));
   }

   public Column isnotnull(final Column col) {
      return Column$.MODULE$.fn("isnotnull", .MODULE$.wrapRefArray(new Column[]{col}));
   }

   public Column equal_null(final Column col1, final Column col2) {
      return Column$.MODULE$.fn("equal_null", .MODULE$.wrapRefArray(new Column[]{col1, col2}));
   }

   public Column nullif(final Column col1, final Column col2) {
      return Column$.MODULE$.fn("nullif", .MODULE$.wrapRefArray(new Column[]{col1, col2}));
   }

   public Column nullifzero(final Column col) {
      return Column$.MODULE$.fn("nullifzero", .MODULE$.wrapRefArray(new Column[]{col}));
   }

   public Column nvl(final Column col1, final Column col2) {
      return Column$.MODULE$.fn("nvl", .MODULE$.wrapRefArray(new Column[]{col1, col2}));
   }

   public Column nvl2(final Column col1, final Column col2, final Column col3) {
      return Column$.MODULE$.fn("nvl2", .MODULE$.wrapRefArray(new Column[]{col1, col2, col3}));
   }

   public Column zeroifnull(final Column col) {
      return Column$.MODULE$.fn("zeroifnull", .MODULE$.wrapRefArray(new Column[]{col}));
   }

   public UserDefinedFunction udaf(final Aggregator agg, final TypeTags.TypeTag evidence$3) {
      return this.udaf(agg, (Encoder)ScalaReflection$.MODULE$.encoderFor(evidence$3));
   }

   public UserDefinedFunction udaf(final Aggregator agg, final Encoder inputEncoder) {
      return new UserDefinedAggregator(agg, inputEncoder, UserDefinedAggregator$.MODULE$.apply$default$3(), UserDefinedAggregator$.MODULE$.apply$default$4(), UserDefinedAggregator$.MODULE$.apply$default$5());
   }

   public UserDefinedFunction udf(final Function0 f, final TypeTags.TypeTag evidence$4) {
      return SparkUserDefinedFunction$.MODULE$.apply(f, (TypeTags.TypeTag)((TypeTags.TypeTag)scala.Predef..MODULE$.implicitly(evidence$4)), (Seq)scala.collection.immutable.Nil..MODULE$);
   }

   public UserDefinedFunction udf(final Function1 f, final TypeTags.TypeTag evidence$5, final TypeTags.TypeTag evidence$6) {
      return SparkUserDefinedFunction$.MODULE$.apply(f, (TypeTags.TypeTag)((TypeTags.TypeTag)scala.Predef..MODULE$.implicitly(evidence$5)), (Seq).MODULE$.wrapRefArray((Object[])(new TypeTags.TypeTag[]{(TypeTags.TypeTag)scala.Predef..MODULE$.implicitly(evidence$6)})));
   }

   public UserDefinedFunction udf(final Function2 f, final TypeTags.TypeTag evidence$7, final TypeTags.TypeTag evidence$8, final TypeTags.TypeTag evidence$9) {
      return SparkUserDefinedFunction$.MODULE$.apply(f, (TypeTags.TypeTag)((TypeTags.TypeTag)scala.Predef..MODULE$.implicitly(evidence$7)), (Seq).MODULE$.wrapRefArray((Object[])(new TypeTags.TypeTag[]{(TypeTags.TypeTag)scala.Predef..MODULE$.implicitly(evidence$8), (TypeTags.TypeTag)scala.Predef..MODULE$.implicitly(evidence$9)})));
   }

   public UserDefinedFunction udf(final Function3 f, final TypeTags.TypeTag evidence$10, final TypeTags.TypeTag evidence$11, final TypeTags.TypeTag evidence$12, final TypeTags.TypeTag evidence$13) {
      return SparkUserDefinedFunction$.MODULE$.apply(f, (TypeTags.TypeTag)((TypeTags.TypeTag)scala.Predef..MODULE$.implicitly(evidence$10)), (Seq).MODULE$.wrapRefArray((Object[])(new TypeTags.TypeTag[]{(TypeTags.TypeTag)scala.Predef..MODULE$.implicitly(evidence$11), (TypeTags.TypeTag)scala.Predef..MODULE$.implicitly(evidence$12), (TypeTags.TypeTag)scala.Predef..MODULE$.implicitly(evidence$13)})));
   }

   public UserDefinedFunction udf(final Function4 f, final TypeTags.TypeTag evidence$14, final TypeTags.TypeTag evidence$15, final TypeTags.TypeTag evidence$16, final TypeTags.TypeTag evidence$17, final TypeTags.TypeTag evidence$18) {
      return SparkUserDefinedFunction$.MODULE$.apply(f, (TypeTags.TypeTag)((TypeTags.TypeTag)scala.Predef..MODULE$.implicitly(evidence$14)), (Seq).MODULE$.wrapRefArray((Object[])(new TypeTags.TypeTag[]{(TypeTags.TypeTag)scala.Predef..MODULE$.implicitly(evidence$15), (TypeTags.TypeTag)scala.Predef..MODULE$.implicitly(evidence$16), (TypeTags.TypeTag)scala.Predef..MODULE$.implicitly(evidence$17), (TypeTags.TypeTag)scala.Predef..MODULE$.implicitly(evidence$18)})));
   }

   public UserDefinedFunction udf(final Function5 f, final TypeTags.TypeTag evidence$19, final TypeTags.TypeTag evidence$20, final TypeTags.TypeTag evidence$21, final TypeTags.TypeTag evidence$22, final TypeTags.TypeTag evidence$23, final TypeTags.TypeTag evidence$24) {
      return SparkUserDefinedFunction$.MODULE$.apply(f, (TypeTags.TypeTag)((TypeTags.TypeTag)scala.Predef..MODULE$.implicitly(evidence$19)), (Seq).MODULE$.wrapRefArray((Object[])(new TypeTags.TypeTag[]{(TypeTags.TypeTag)scala.Predef..MODULE$.implicitly(evidence$20), (TypeTags.TypeTag)scala.Predef..MODULE$.implicitly(evidence$21), (TypeTags.TypeTag)scala.Predef..MODULE$.implicitly(evidence$22), (TypeTags.TypeTag)scala.Predef..MODULE$.implicitly(evidence$23), (TypeTags.TypeTag)scala.Predef..MODULE$.implicitly(evidence$24)})));
   }

   public UserDefinedFunction udf(final Function6 f, final TypeTags.TypeTag evidence$25, final TypeTags.TypeTag evidence$26, final TypeTags.TypeTag evidence$27, final TypeTags.TypeTag evidence$28, final TypeTags.TypeTag evidence$29, final TypeTags.TypeTag evidence$30, final TypeTags.TypeTag evidence$31) {
      return SparkUserDefinedFunction$.MODULE$.apply(f, (TypeTags.TypeTag)((TypeTags.TypeTag)scala.Predef..MODULE$.implicitly(evidence$25)), (Seq).MODULE$.wrapRefArray((Object[])(new TypeTags.TypeTag[]{(TypeTags.TypeTag)scala.Predef..MODULE$.implicitly(evidence$26), (TypeTags.TypeTag)scala.Predef..MODULE$.implicitly(evidence$27), (TypeTags.TypeTag)scala.Predef..MODULE$.implicitly(evidence$28), (TypeTags.TypeTag)scala.Predef..MODULE$.implicitly(evidence$29), (TypeTags.TypeTag)scala.Predef..MODULE$.implicitly(evidence$30), (TypeTags.TypeTag)scala.Predef..MODULE$.implicitly(evidence$31)})));
   }

   public UserDefinedFunction udf(final Function7 f, final TypeTags.TypeTag evidence$32, final TypeTags.TypeTag evidence$33, final TypeTags.TypeTag evidence$34, final TypeTags.TypeTag evidence$35, final TypeTags.TypeTag evidence$36, final TypeTags.TypeTag evidence$37, final TypeTags.TypeTag evidence$38, final TypeTags.TypeTag evidence$39) {
      return SparkUserDefinedFunction$.MODULE$.apply(f, (TypeTags.TypeTag)((TypeTags.TypeTag)scala.Predef..MODULE$.implicitly(evidence$32)), (Seq).MODULE$.wrapRefArray((Object[])(new TypeTags.TypeTag[]{(TypeTags.TypeTag)scala.Predef..MODULE$.implicitly(evidence$33), (TypeTags.TypeTag)scala.Predef..MODULE$.implicitly(evidence$34), (TypeTags.TypeTag)scala.Predef..MODULE$.implicitly(evidence$35), (TypeTags.TypeTag)scala.Predef..MODULE$.implicitly(evidence$36), (TypeTags.TypeTag)scala.Predef..MODULE$.implicitly(evidence$37), (TypeTags.TypeTag)scala.Predef..MODULE$.implicitly(evidence$38), (TypeTags.TypeTag)scala.Predef..MODULE$.implicitly(evidence$39)})));
   }

   public UserDefinedFunction udf(final Function8 f, final TypeTags.TypeTag evidence$40, final TypeTags.TypeTag evidence$41, final TypeTags.TypeTag evidence$42, final TypeTags.TypeTag evidence$43, final TypeTags.TypeTag evidence$44, final TypeTags.TypeTag evidence$45, final TypeTags.TypeTag evidence$46, final TypeTags.TypeTag evidence$47, final TypeTags.TypeTag evidence$48) {
      return SparkUserDefinedFunction$.MODULE$.apply(f, (TypeTags.TypeTag)((TypeTags.TypeTag)scala.Predef..MODULE$.implicitly(evidence$40)), (Seq).MODULE$.wrapRefArray((Object[])(new TypeTags.TypeTag[]{(TypeTags.TypeTag)scala.Predef..MODULE$.implicitly(evidence$41), (TypeTags.TypeTag)scala.Predef..MODULE$.implicitly(evidence$42), (TypeTags.TypeTag)scala.Predef..MODULE$.implicitly(evidence$43), (TypeTags.TypeTag)scala.Predef..MODULE$.implicitly(evidence$44), (TypeTags.TypeTag)scala.Predef..MODULE$.implicitly(evidence$45), (TypeTags.TypeTag)scala.Predef..MODULE$.implicitly(evidence$46), (TypeTags.TypeTag)scala.Predef..MODULE$.implicitly(evidence$47), (TypeTags.TypeTag)scala.Predef..MODULE$.implicitly(evidence$48)})));
   }

   public UserDefinedFunction udf(final Function9 f, final TypeTags.TypeTag evidence$49, final TypeTags.TypeTag evidence$50, final TypeTags.TypeTag evidence$51, final TypeTags.TypeTag evidence$52, final TypeTags.TypeTag evidence$53, final TypeTags.TypeTag evidence$54, final TypeTags.TypeTag evidence$55, final TypeTags.TypeTag evidence$56, final TypeTags.TypeTag evidence$57, final TypeTags.TypeTag evidence$58) {
      return SparkUserDefinedFunction$.MODULE$.apply(f, (TypeTags.TypeTag)((TypeTags.TypeTag)scala.Predef..MODULE$.implicitly(evidence$49)), (Seq).MODULE$.wrapRefArray((Object[])(new TypeTags.TypeTag[]{(TypeTags.TypeTag)scala.Predef..MODULE$.implicitly(evidence$50), (TypeTags.TypeTag)scala.Predef..MODULE$.implicitly(evidence$51), (TypeTags.TypeTag)scala.Predef..MODULE$.implicitly(evidence$52), (TypeTags.TypeTag)scala.Predef..MODULE$.implicitly(evidence$53), (TypeTags.TypeTag)scala.Predef..MODULE$.implicitly(evidence$54), (TypeTags.TypeTag)scala.Predef..MODULE$.implicitly(evidence$55), (TypeTags.TypeTag)scala.Predef..MODULE$.implicitly(evidence$56), (TypeTags.TypeTag)scala.Predef..MODULE$.implicitly(evidence$57), (TypeTags.TypeTag)scala.Predef..MODULE$.implicitly(evidence$58)})));
   }

   public UserDefinedFunction udf(final Function10 f, final TypeTags.TypeTag evidence$59, final TypeTags.TypeTag evidence$60, final TypeTags.TypeTag evidence$61, final TypeTags.TypeTag evidence$62, final TypeTags.TypeTag evidence$63, final TypeTags.TypeTag evidence$64, final TypeTags.TypeTag evidence$65, final TypeTags.TypeTag evidence$66, final TypeTags.TypeTag evidence$67, final TypeTags.TypeTag evidence$68, final TypeTags.TypeTag evidence$69) {
      return SparkUserDefinedFunction$.MODULE$.apply(f, (TypeTags.TypeTag)((TypeTags.TypeTag)scala.Predef..MODULE$.implicitly(evidence$59)), (Seq).MODULE$.wrapRefArray((Object[])(new TypeTags.TypeTag[]{(TypeTags.TypeTag)scala.Predef..MODULE$.implicitly(evidence$60), (TypeTags.TypeTag)scala.Predef..MODULE$.implicitly(evidence$61), (TypeTags.TypeTag)scala.Predef..MODULE$.implicitly(evidence$62), (TypeTags.TypeTag)scala.Predef..MODULE$.implicitly(evidence$63), (TypeTags.TypeTag)scala.Predef..MODULE$.implicitly(evidence$64), (TypeTags.TypeTag)scala.Predef..MODULE$.implicitly(evidence$65), (TypeTags.TypeTag)scala.Predef..MODULE$.implicitly(evidence$66), (TypeTags.TypeTag)scala.Predef..MODULE$.implicitly(evidence$67), (TypeTags.TypeTag)scala.Predef..MODULE$.implicitly(evidence$68), (TypeTags.TypeTag)scala.Predef..MODULE$.implicitly(evidence$69)})));
   }

   public UserDefinedFunction udf(final UDF0 f, final DataType returnType) {
      return SparkUserDefinedFunction$.MODULE$.apply(ToScalaUDF$.MODULE$.apply(f), returnType, 0);
   }

   public UserDefinedFunction udf(final UDF1 f, final DataType returnType) {
      return SparkUserDefinedFunction$.MODULE$.apply(ToScalaUDF$.MODULE$.apply(f), returnType, 1);
   }

   public UserDefinedFunction udf(final UDF2 f, final DataType returnType) {
      return SparkUserDefinedFunction$.MODULE$.apply(ToScalaUDF$.MODULE$.apply(f), returnType, 2);
   }

   public UserDefinedFunction udf(final UDF3 f, final DataType returnType) {
      return SparkUserDefinedFunction$.MODULE$.apply(ToScalaUDF$.MODULE$.apply(f), returnType, 3);
   }

   public UserDefinedFunction udf(final UDF4 f, final DataType returnType) {
      return SparkUserDefinedFunction$.MODULE$.apply(ToScalaUDF$.MODULE$.apply(f), returnType, 4);
   }

   public UserDefinedFunction udf(final UDF5 f, final DataType returnType) {
      return SparkUserDefinedFunction$.MODULE$.apply(ToScalaUDF$.MODULE$.apply(f), returnType, 5);
   }

   public UserDefinedFunction udf(final UDF6 f, final DataType returnType) {
      return SparkUserDefinedFunction$.MODULE$.apply(ToScalaUDF$.MODULE$.apply(f), returnType, 6);
   }

   public UserDefinedFunction udf(final UDF7 f, final DataType returnType) {
      return SparkUserDefinedFunction$.MODULE$.apply(ToScalaUDF$.MODULE$.apply(f), returnType, 7);
   }

   public UserDefinedFunction udf(final UDF8 f, final DataType returnType) {
      return SparkUserDefinedFunction$.MODULE$.apply(ToScalaUDF$.MODULE$.apply(f), returnType, 8);
   }

   public UserDefinedFunction udf(final UDF9 f, final DataType returnType) {
      return SparkUserDefinedFunction$.MODULE$.apply(ToScalaUDF$.MODULE$.apply(f), returnType, 9);
   }

   public UserDefinedFunction udf(final UDF10 f, final DataType returnType) {
      return SparkUserDefinedFunction$.MODULE$.apply(ToScalaUDF$.MODULE$.apply(f), returnType, 10);
   }

   /** @deprecated */
   public UserDefinedFunction udf(final Object f, final DataType dataType) {
      if (!SqlApiConf$.MODULE$.get().legacyAllowUntypedScalaUDFs()) {
         throw CompilationErrors$.MODULE$.usingUntypedScalaUDFError();
      } else {
         return new SparkUserDefinedFunction(f, dataType, scala.collection.immutable.Nil..MODULE$, SparkUserDefinedFunction$.MODULE$.apply$default$4(), SparkUserDefinedFunction$.MODULE$.apply$default$5(), SparkUserDefinedFunction$.MODULE$.apply$default$6(), SparkUserDefinedFunction$.MODULE$.apply$default$7());
      }
   }

   /** @deprecated */
   public Column callUDF(final String udfName, final Seq cols) {
      return this.call_function(udfName, cols);
   }

   public Column call_udf(final String udfName, final Seq cols) {
      return this.call_function(udfName, cols);
   }

   public Column call_function(final String funcName, final Seq cols) {
      return Column$.MODULE$.apply((Function0)(() -> {
         Seq x$2 = (Seq)cols.map((x$1) -> x$1.node());
         boolean x$3 = true;
         boolean x$4 = UnresolvedFunction$.MODULE$.apply$default$3();
         boolean x$5 = UnresolvedFunction$.MODULE$.apply$default$5();
         Origin x$6 = UnresolvedFunction$.MODULE$.apply$default$6();
         return new UnresolvedFunction(funcName, x$2, x$4, true, x$5, x$6);
      }));
   }

   public Column unwrap_udt(final Column column) {
      return Column$.MODULE$.internalFn("unwrap_udt", .MODULE$.wrapRefArray(new Column[]{column}));
   }

   private functions$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
