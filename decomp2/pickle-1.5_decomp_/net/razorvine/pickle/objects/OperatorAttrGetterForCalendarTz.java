package net.razorvine.pickle.objects;

import java.util.Calendar;
import java.util.TimeZone;
import net.razorvine.pickle.IObjectConstructor;
import net.razorvine.pickle.PickleException;

public class OperatorAttrGetterForCalendarTz implements IObjectConstructor {
   public Object construct(Object[] args) {
      if (args.length != 1) {
         throw new PickleException("expected exactly one string argument for construction of AttrGetter");
      } else if ("localize".equals(args[0])) {
         return new AttrGetterForTz();
      } else {
         throw new PickleException("expected 'localize' string argument for construction of AttrGetter");
      }
   }

   static class AttrGetterForTz implements IObjectConstructor {
      public AttrGetterForTz() {
      }

      public Object construct(Object[] args) {
         if (args.length == 1 && args[0] instanceof TimeZone) {
            TimeZone tz = (TimeZone)args[0];
            return new CalendarLocalizer(tz);
         } else {
            throw new PickleException("expected exactly one TimeZone argument for construction of CalendarLocalizer");
         }
      }
   }

   static class CalendarLocalizer implements IObjectConstructor {
      final TimeZone tz;

      public CalendarLocalizer(TimeZone tz) {
         this.tz = tz;
      }

      public Object construct(Object[] args) {
         if (args.length == 1 && args[0] instanceof Calendar) {
            Calendar cal = (Calendar)args[0];
            cal.setTimeZone(this.tz);
            return cal;
         } else {
            throw new PickleException("expected exactly one Calendar argument for construction of Calendar with timezone");
         }
      }
   }
}
