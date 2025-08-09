package org.apache.spark.ui;

import java.lang.invoke.SerializedLambda;
import java.util.EnumSet;
import org.sparkproject.jetty.servlet.FilterHolder;
import org.sparkproject.jetty.servlet.FilterMapping;
import org.sparkproject.jetty.servlet.ServletContextHandler;
import scala.MatchError;
import scala.Tuple2;
import scala.collection.immutable.Map;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005y3Qa\u0002\u0005\u0001\u0015AA\u0001b\u0006\u0001\u0003\u0002\u0003\u0006I!\u0007\u0005\u0006G\u0001!\t\u0001\n\u0005\u0006Q\u0001!\t!\u000b\u0005\u0006\u001b\u0002!\tA\u0014\u0005\u0006/\u0002!\t\u0001\u0017\u0005\u00069\u0002!\t!\u0018\u0002 \t\u0016dWmZ1uS:<7+\u001a:wY\u0016$8i\u001c8uKb$\b*\u00198eY\u0016\u0014(BA\u0005\u000b\u0003\t)\u0018N\u0003\u0002\f\u0019\u0005)1\u000f]1sW*\u0011QBD\u0001\u0007CB\f7\r[3\u000b\u0003=\t1a\u001c:h'\t\u0001\u0011\u0003\u0005\u0002\u0013+5\t1CC\u0001\u0015\u0003\u0015\u00198-\u00197b\u0013\t12C\u0001\u0004B]f\u0014VMZ\u0001\bQ\u0006tG\r\\3s\u0007\u0001\u0001\"AG\u0011\u000e\u0003mQ!\u0001H\u000f\u0002\u000fM,'O\u001e7fi*\u0011adH\u0001\u0006U\u0016$H/\u001f\u0006\u0003A9\tq!Z2mSB\u001cX-\u0003\u0002#7\t)2+\u001a:wY\u0016$8i\u001c8uKb$\b*\u00198eY\u0016\u0014\u0018A\u0002\u001fj]&$h\b\u0006\u0002&OA\u0011a\u0005A\u0007\u0002\u0011!)qC\u0001a\u00013\u0005!\u0002O]3qK:$g)\u001b7uKJl\u0015\r\u001d9j]\u001e$BAK\u0017;yA\u0011!cK\u0005\u0003YM\u0011A!\u00168ji\")af\u0001a\u0001_\u0005Qa-\u001b7uKJt\u0015-\\3\u0011\u0005A:dBA\u00196!\t\u00114#D\u00014\u0015\t!\u0004$\u0001\u0004=e>|GOP\u0005\u0003mM\ta\u0001\u0015:fI\u00164\u0017B\u0001\u001d:\u0005\u0019\u0019FO]5oO*\u0011ag\u0005\u0005\u0006w\r\u0001\raL\u0001\u0005gB,7\rC\u0003>\u0007\u0001\u0007a(A\u0003usB,7\u000fE\u0002@\t\u001ak\u0011\u0001\u0011\u0006\u0003\u0003\n\u000bA!\u001e;jY*\t1)\u0001\u0003kCZ\f\u0017BA#A\u0005\u001d)e.^7TKR\u0004\"aR&\u000e\u0003!S!\u0001H%\u000b\u0003)\u000bqA[1lCJ$\u0018-\u0003\u0002M\u0011\nqA)[:qCR\u001c\u0007.\u001a:UsB,\u0017!C1eI\u001aKG\u000e^3s)\u0011Qs\n\u0015*\t\u000b9\"\u0001\u0019A\u0018\t\u000bE#\u0001\u0019A\u0018\u0002\u0013\rd\u0017m]:OC6,\u0007\"B*\u0005\u0001\u0004!\u0016\u0001\u00044jYR,'\u000fU1sC6\u001c\b\u0003\u0002\u0019V_=J!AV\u001d\u0003\u00075\u000b\u0007/A\u0006gS2$XM]\"pk:$H#A-\u0011\u0005IQ\u0016BA.\u0014\u0005\rIe\u000e^\u0001\u000fO\u0016$8i\u001c8uKb$\b+\u0019;i)\u0005y\u0003"
)
public class DelegatingServletContextHandler {
   private final ServletContextHandler handler;

   public void prependFilterMapping(final String filterName, final String spec, final EnumSet types) {
      FilterMapping mapping = new FilterMapping();
      mapping.setFilterName(filterName);
      mapping.setPathSpec(spec);
      mapping.setDispatcherTypes(types);
      this.handler.getServletHandler().prependFilterMapping(mapping);
   }

   public void addFilter(final String filterName, final String className, final Map filterParams) {
      FilterHolder filterHolder = new FilterHolder();
      filterHolder.setName(filterName);
      filterHolder.setClassName(className);
      filterParams.foreach((x0$1) -> {
         $anonfun$addFilter$1(filterHolder, x0$1);
         return BoxedUnit.UNIT;
      });
      this.handler.getServletHandler().addFilter(filterHolder);
   }

   public int filterCount() {
      return this.handler.getServletHandler().getFilters().length;
   }

   public String getContextPath() {
      return this.handler.getContextPath();
   }

   // $FF: synthetic method
   public static final void $anonfun$addFilter$1(final FilterHolder filterHolder$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         String k = (String)x0$1._1();
         String v = (String)x0$1._2();
         filterHolder$1.setInitParameter(k, v);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$1);
      }
   }

   public DelegatingServletContextHandler(final ServletContextHandler handler) {
      this.handler = handler;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
