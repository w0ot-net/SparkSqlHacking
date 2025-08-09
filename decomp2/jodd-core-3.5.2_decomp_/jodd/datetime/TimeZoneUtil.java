package jodd.datetime;

import java.util.TimeZone;

public class TimeZoneUtil {
   public static int getRawOffsetDifference(TimeZone from, TimeZone to) {
      int offsetBefore = from.getRawOffset();
      int offsetAfter = to.getRawOffset();
      return offsetAfter - offsetBefore;
   }

   public static int getOffsetDifference(long now, TimeZone from, TimeZone to) {
      int offsetBefore = from.getOffset(now);
      int offsetAfter = to.getOffset(now);
      return offsetAfter - offsetBefore;
   }

   public static int getOffset(JDateTime jdt, TimeZone tz) {
      return tz.getOffset(jdt.getEra(), jdt.getYear(), jdt.getMonth() - 1, jdt.getDay(), TimeUtil.toCalendarDayOfWeek(jdt.getDayOfWeek()), jdt.getMillisOfDay());
   }

   public static int getOffsetDifference(JDateTime jdt, TimeZone from, TimeZone to) {
      int offsetBefore = getOffset(jdt, from);
      int offsetAfter = getOffset(jdt, to);
      return offsetAfter - offsetBefore;
   }
}
