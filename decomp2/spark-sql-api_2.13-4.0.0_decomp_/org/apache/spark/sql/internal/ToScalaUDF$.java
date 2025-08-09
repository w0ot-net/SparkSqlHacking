package org.apache.spark.sql.internal;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.api.java.function.CoGroupFunction;
import org.apache.spark.api.java.function.FilterFunction;
import org.apache.spark.api.java.function.FlatMapGroupsFunction;
import org.apache.spark.api.java.function.FlatMapGroupsWithStateFunction;
import org.apache.spark.api.java.function.ForeachFunction;
import org.apache.spark.api.java.function.ForeachPartitionFunction;
import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.api.java.function.MapGroupsFunction;
import org.apache.spark.api.java.function.MapGroupsWithStateFunction;
import org.apache.spark.api.java.function.MapPartitionsFunction;
import org.apache.spark.api.java.function.ReduceFunction;
import org.apache.spark.sql.api.java.UDF0;
import org.apache.spark.sql.api.java.UDF1;
import org.apache.spark.sql.api.java.UDF10;
import org.apache.spark.sql.api.java.UDF11;
import org.apache.spark.sql.api.java.UDF12;
import org.apache.spark.sql.api.java.UDF13;
import org.apache.spark.sql.api.java.UDF14;
import org.apache.spark.sql.api.java.UDF15;
import org.apache.spark.sql.api.java.UDF16;
import org.apache.spark.sql.api.java.UDF17;
import org.apache.spark.sql.api.java.UDF18;
import org.apache.spark.sql.api.java.UDF19;
import org.apache.spark.sql.api.java.UDF2;
import org.apache.spark.sql.api.java.UDF20;
import org.apache.spark.sql.api.java.UDF21;
import org.apache.spark.sql.api.java.UDF22;
import org.apache.spark.sql.api.java.UDF3;
import org.apache.spark.sql.api.java.UDF4;
import org.apache.spark.sql.api.java.UDF5;
import org.apache.spark.sql.api.java.UDF6;
import org.apache.spark.sql.api.java.UDF7;
import org.apache.spark.sql.api.java.UDF8;
import org.apache.spark.sql.api.java.UDF9;
import scala.Function0;
import scala.Function1;
import scala.Function10;
import scala.Function11;
import scala.Function12;
import scala.Function13;
import scala.Function14;
import scala.Function15;
import scala.Function16;
import scala.Function17;
import scala.Function18;
import scala.Function19;
import scala.Function2;
import scala.Function20;
import scala.Function21;
import scala.Function22;
import scala.Function3;
import scala.Function4;
import scala.Function5;
import scala.Function6;
import scala.Function7;
import scala.Function8;
import scala.Function9;
import scala.collection.Iterator;
import scala.jdk.CollectionConverters.;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class ToScalaUDF$ implements Serializable {
   public static final ToScalaUDF$ MODULE$ = new ToScalaUDF$();
   private static final long serialVersionUID = 2019907615267866045L;

   public Function1 apply(final FilterFunction f) {
      return (x$1) -> BoxesRunTime.boxToBoolean($anonfun$apply$1(f, x$1));
   }

   public Function2 apply(final ReduceFunction f) {
      return (x$1, x$2) -> f.call(x$1, x$2);
   }

   public Function1 apply(final MapFunction f) {
      return (x$1) -> f.call(x$1);
   }

   public Function2 apply(final MapGroupsFunction f) {
      return (key, values) -> f.call(key, .MODULE$.IteratorHasAsJava(values).asJava());
   }

   public Function3 apply(final MapGroupsWithStateFunction f) {
      return (key, values, state) -> f.call(key, .MODULE$.IteratorHasAsJava(values).asJava(), state);
   }

   public Function1 apply(final MapPartitionsFunction f) {
      return (values) -> .MODULE$.IteratorHasAsScala(f.call(.MODULE$.IteratorHasAsJava(values).asJava())).asScala();
   }

   public Function2 apply(final FlatMapGroupsFunction f) {
      return (key, values) -> .MODULE$.IteratorHasAsScala(f.call(key, .MODULE$.IteratorHasAsJava(values).asJava())).asScala();
   }

   public Function3 apply(final FlatMapGroupsWithStateFunction f) {
      return (key, values, state) -> .MODULE$.IteratorHasAsScala(f.call(key, .MODULE$.IteratorHasAsJava(values).asJava(), state)).asScala();
   }

   public Function3 apply(final CoGroupFunction f) {
      return (key, left, right) -> .MODULE$.IteratorHasAsScala(f.call(key, .MODULE$.IteratorHasAsJava(left).asJava(), .MODULE$.IteratorHasAsJava(right).asJava())).asScala();
   }

   public Function1 apply(final ForeachFunction f) {
      return (x$1) -> {
         $anonfun$apply$10(f, x$1);
         return BoxedUnit.UNIT;
      };
   }

   public Function1 apply(final ForeachPartitionFunction f) {
      return (values) -> {
         $anonfun$apply$11(f, values);
         return BoxedUnit.UNIT;
      };
   }

   public Function0 apply(final UDF0 f) {
      return () -> f.call();
   }

   public Function1 apply(final UDF1 f) {
      return (x$1) -> f.call(x$1);
   }

   public Function2 apply(final UDF2 f) {
      return (x$2, x$3) -> f.call(x$2, x$3);
   }

   public Function3 apply(final UDF3 f) {
      return (x$4, x$5, x$6) -> f.call(x$4, x$5, x$6);
   }

   public Function4 apply(final UDF4 f) {
      return (x$7, x$8, x$9, x$10) -> f.call(x$7, x$8, x$9, x$10);
   }

   public Function5 apply(final UDF5 f) {
      return (x$11, x$12, x$13, x$14, x$15) -> f.call(x$11, x$12, x$13, x$14, x$15);
   }

   public Function6 apply(final UDF6 f) {
      return (x$16, x$17, x$18, x$19, x$20, x$21) -> f.call(x$16, x$17, x$18, x$19, x$20, x$21);
   }

   public Function7 apply(final UDF7 f) {
      return (x$22, x$23, x$24, x$25, x$26, x$27, x$28) -> f.call(x$22, x$23, x$24, x$25, x$26, x$27, x$28);
   }

   public Function8 apply(final UDF8 f) {
      return (x$29, x$30, x$31, x$32, x$33, x$34, x$35, x$36) -> f.call(x$29, x$30, x$31, x$32, x$33, x$34, x$35, x$36);
   }

   public Function9 apply(final UDF9 f) {
      return (x$37, x$38, x$39, x$40, x$41, x$42, x$43, x$44, x$45) -> f.call(x$37, x$38, x$39, x$40, x$41, x$42, x$43, x$44, x$45);
   }

   public Function10 apply(final UDF10 f) {
      return (x$46, x$47, x$48, x$49, x$50, x$51, x$52, x$53, x$54, x$55) -> f.call(x$46, x$47, x$48, x$49, x$50, x$51, x$52, x$53, x$54, x$55);
   }

   public Function11 apply(final UDF11 f) {
      return (x$56, x$57, x$58, x$59, x$60, x$61, x$62, x$63, x$64, x$65, x$66) -> f.call(x$56, x$57, x$58, x$59, x$60, x$61, x$62, x$63, x$64, x$65, x$66);
   }

   public Function12 apply(final UDF12 f) {
      return (x$67, x$68, x$69, x$70, x$71, x$72, x$73, x$74, x$75, x$76, x$77, x$78) -> f.call(x$67, x$68, x$69, x$70, x$71, x$72, x$73, x$74, x$75, x$76, x$77, x$78);
   }

   public Function13 apply(final UDF13 f) {
      return (x$79, x$80, x$81, x$82, x$83, x$84, x$85, x$86, x$87, x$88, x$89, x$90, x$91) -> f.call(x$79, x$80, x$81, x$82, x$83, x$84, x$85, x$86, x$87, x$88, x$89, x$90, x$91);
   }

   public Function14 apply(final UDF14 f) {
      return (x$92, x$93, x$94, x$95, x$96, x$97, x$98, x$99, x$100, x$101, x$102, x$103, x$104, x$105) -> f.call(x$92, x$93, x$94, x$95, x$96, x$97, x$98, x$99, x$100, x$101, x$102, x$103, x$104, x$105);
   }

   public Function15 apply(final UDF15 f) {
      return (x$106, x$107, x$108, x$109, x$110, x$111, x$112, x$113, x$114, x$115, x$116, x$117, x$118, x$119, x$120) -> f.call(x$106, x$107, x$108, x$109, x$110, x$111, x$112, x$113, x$114, x$115, x$116, x$117, x$118, x$119, x$120);
   }

   public Function16 apply(final UDF16 f) {
      return (x$121, x$122, x$123, x$124, x$125, x$126, x$127, x$128, x$129, x$130, x$131, x$132, x$133, x$134, x$135, x$136) -> f.call(x$121, x$122, x$123, x$124, x$125, x$126, x$127, x$128, x$129, x$130, x$131, x$132, x$133, x$134, x$135, x$136);
   }

   public Function17 apply(final UDF17 f) {
      return (x$137, x$138, x$139, x$140, x$141, x$142, x$143, x$144, x$145, x$146, x$147, x$148, x$149, x$150, x$151, x$152, x$153) -> f.call(x$137, x$138, x$139, x$140, x$141, x$142, x$143, x$144, x$145, x$146, x$147, x$148, x$149, x$150, x$151, x$152, x$153);
   }

   public Function18 apply(final UDF18 f) {
      return (x$154, x$155, x$156, x$157, x$158, x$159, x$160, x$161, x$162, x$163, x$164, x$165, x$166, x$167, x$168, x$169, x$170, x$171) -> f.call(x$154, x$155, x$156, x$157, x$158, x$159, x$160, x$161, x$162, x$163, x$164, x$165, x$166, x$167, x$168, x$169, x$170, x$171);
   }

   public Function19 apply(final UDF19 f) {
      return (x$172, x$173, x$174, x$175, x$176, x$177, x$178, x$179, x$180, x$181, x$182, x$183, x$184, x$185, x$186, x$187, x$188, x$189, x$190) -> f.call(x$172, x$173, x$174, x$175, x$176, x$177, x$178, x$179, x$180, x$181, x$182, x$183, x$184, x$185, x$186, x$187, x$188, x$189, x$190);
   }

   public Function20 apply(final UDF20 f) {
      return (x$191, x$192, x$193, x$194, x$195, x$196, x$197, x$198, x$199, x$200, x$201, x$202, x$203, x$204, x$205, x$206, x$207, x$208, x$209, x$210) -> f.call(x$191, x$192, x$193, x$194, x$195, x$196, x$197, x$198, x$199, x$200, x$201, x$202, x$203, x$204, x$205, x$206, x$207, x$208, x$209, x$210);
   }

   public Function21 apply(final UDF21 f) {
      return (x$211, x$212, x$213, x$214, x$215, x$216, x$217, x$218, x$219, x$220, x$221, x$222, x$223, x$224, x$225, x$226, x$227, x$228, x$229, x$230, x$231) -> f.call(x$211, x$212, x$213, x$214, x$215, x$216, x$217, x$218, x$219, x$220, x$221, x$222, x$223, x$224, x$225, x$226, x$227, x$228, x$229, x$230, x$231);
   }

   public Function22 apply(final UDF22 f) {
      return (x$232, x$233, x$234, x$235, x$236, x$237, x$238, x$239, x$240, x$241, x$242, x$243, x$244, x$245, x$246, x$247, x$248, x$249, x$250, x$251, x$252, x$253) -> f.call(x$232, x$233, x$234, x$235, x$236, x$237, x$238, x$239, x$240, x$241, x$242, x$243, x$244, x$245, x$246, x$247, x$248, x$249, x$250, x$251, x$252, x$253);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ToScalaUDF$.class);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$apply$1(final FilterFunction f$1, final Object x$1) {
      return f$1.call(x$1);
   }

   // $FF: synthetic method
   public static final void $anonfun$apply$10(final ForeachFunction f$10, final Object x$1) {
      f$10.call(x$1);
   }

   // $FF: synthetic method
   public static final void $anonfun$apply$11(final ForeachPartitionFunction f$11, final Iterator values) {
      f$11.call(.MODULE$.IteratorHasAsJava(values).asJava());
   }

   private ToScalaUDF$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
