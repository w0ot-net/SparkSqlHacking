package scala.reflect.internal.util;

import scala.collection.immutable.Seq;

public final class TableDef$ {
   public static final TableDef$ MODULE$ = new TableDef$();

   public TableDef apply(final Seq cols) {
      return new TableDef(cols);
   }

   private TableDef$() {
   }
}
