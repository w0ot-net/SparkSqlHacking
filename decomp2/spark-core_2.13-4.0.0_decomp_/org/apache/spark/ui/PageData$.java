package org.apache.spark.ui;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.collection.Seq;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class PageData$ implements Serializable {
   public static final PageData$ MODULE$ = new PageData$();

   public final String toString() {
      return "PageData";
   }

   public PageData apply(final int totalPage, final Seq data) {
      return new PageData(totalPage, data);
   }

   public Option unapply(final PageData x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(BoxesRunTime.boxToInteger(x$0.totalPage()), x$0.data())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(PageData$.class);
   }

   private PageData$() {
   }
}
