package org.apache.spark.sql.types;

import scala.Option;
import scala.Some;
import scala.collection.immutable.Seq;
import scala.runtime.ScalaRunTime.;

public final class TypeCollection$ {
   public static final TypeCollection$ MODULE$ = new TypeCollection$();
   private static final TypeCollection NumericAndAnsiInterval;
   private static final TypeCollection NumericAndInterval;

   static {
      NumericAndAnsiInterval = MODULE$.apply(.MODULE$.wrapRefArray(new AbstractDataType[]{NumericType$.MODULE$, DayTimeIntervalType$.MODULE$, YearMonthIntervalType$.MODULE$}));
      NumericAndInterval = new TypeCollection((Seq)MODULE$.NumericAndAnsiInterval().org$apache$spark$sql$types$TypeCollection$$types().$colon$plus(CalendarIntervalType$.MODULE$));
   }

   public TypeCollection NumericAndAnsiInterval() {
      return NumericAndAnsiInterval;
   }

   public TypeCollection NumericAndInterval() {
      return NumericAndInterval;
   }

   public TypeCollection apply(final Seq types) {
      return new TypeCollection(types);
   }

   public Option unapply(final AbstractDataType typ) {
      if (typ instanceof TypeCollection var4) {
         return new Some(var4.org$apache$spark$sql$types$TypeCollection$$types());
      } else {
         return scala.None..MODULE$;
      }
   }

   private TypeCollection$() {
   }
}
