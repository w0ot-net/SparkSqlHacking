package net.razorvine.pickle.objects;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import net.razorvine.pickle.IObjectConstructor;
import net.razorvine.pickle.PickleException;

public class DateTimeConstructor implements IObjectConstructor {
   public static final int DATETIME = 1;
   public static final int DATE = 2;
   public static final int TIME = 3;
   public static final int TIMEDELTA = 4;
   private final int pythontype;

   public DateTimeConstructor(int pythontype) {
      this.pythontype = pythontype;
   }

   public Object construct(Object[] args) {
      if (this.pythontype == 2) {
         return this.createDate(args);
      } else if (this.pythontype == 3) {
         return this.createTime(args);
      } else if (this.pythontype == 1) {
         return this.createDateTime(args);
      } else if (this.pythontype == 4) {
         return this.createTimedelta(args);
      } else {
         throw new PickleException("invalid object type");
      }
   }

   private TimeDelta createTimedelta(Object[] args) {
      if (args.length != 3) {
         throw new PickleException("invalid pickle data for timedelta; expected 3 args, got " + args.length);
      } else {
         int days = ((Number)args[0]).intValue();
         int seconds = ((Number)args[1]).intValue();
         int micro = ((Number)args[2]).intValue();
         return new TimeDelta(days, seconds, micro);
      }
   }

   private Calendar createDateTime(Object[] args) {
      if (args.length != 7 && args.length != 8) {
         if (args.length != 1 && args.length != 2) {
            throw new PickleException("invalid pickle data for datetime; expected 1, 2, 7 or 8 args, got " + args.length);
         } else {
            int yhi;
            int ylo;
            int month;
            int day;
            int hour;
            int minute;
            int second;
            int microsec;
            if (args[0] instanceof String) {
               String params = (String)args[0];
               if (params.length() != 10) {
                  throw new PickleException("invalid pickle data for datetime; expected arg of length 10, got length " + params.length());
               }

               yhi = params.charAt(0);
               ylo = params.charAt(1);
               month = params.charAt(2) - 1;
               day = params.charAt(3);
               hour = params.charAt(4);
               minute = params.charAt(5);
               second = params.charAt(6);
               int ms1 = params.charAt(7);
               int ms2 = params.charAt(8);
               int ms3 = params.charAt(9);
               microsec = (ms1 << 8 | ms2) << 8 | ms3;
            } else {
               byte[] params = (byte[])args[0];
               if (params.length != 10) {
                  throw new PickleException("invalid pickle data for datetime; expected arg of length 10, got length " + params.length);
               }

               yhi = params[0] & 255;
               ylo = params[1] & 255;
               month = (params[2] & 255) - 1;
               day = params[3] & 255;
               hour = params[4] & 255;
               minute = params[5] & 255;
               second = params[6] & 255;
               int ms1 = params[7] & 255;
               int ms2 = params[8] & 255;
               int ms3 = params[9] & 255;
               microsec = (ms1 << 8 | ms2) << 8 | ms3;
            }

            Calendar cal = new GregorianCalendar(yhi * 256 + ylo, month, day, hour, minute, second);
            cal.set(14, microsec / 1000);
            if (args.length == 2) {
               if (args[1] instanceof TimeZone) {
                  cal.setTimeZone((TimeZone)args[1]);
               } else {
                  if (!(args[1] instanceof Tzinfo)) {
                     throw new PickleException("invalid pickle data for datetime; expected arg 2 to be a Tzinfo or TimeZone");
                  }

                  cal.setTimeZone(((Tzinfo)args[1]).getTimeZone());
               }
            }

            return cal;
         }
      } else {
         int year = (Integer)args[0];
         int month = (Integer)args[1] - 1;
         int day = (Integer)args[2];
         int hour = (Integer)args[3];
         int minute = (Integer)args[4];
         int second = (Integer)args[5];
         int microsec = (Integer)args[6];
         TimeZone tz = null;
         if (args.length == 8) {
            tz = (TimeZone)args[7];
         }

         Calendar cal = new GregorianCalendar(year, month, day, hour, minute, second);
         cal.set(14, microsec / 1000);
         if (tz != null) {
            cal.setTimeZone(tz);
         }

         return cal;
      }
   }

   private Time createTime(Object[] args) {
      if (args.length == 4) {
         int hour = (Integer)args[0];
         int minute = (Integer)args[1];
         int second = (Integer)args[2];
         int microsec = (Integer)args[3];
         return new Time(hour, minute, second, microsec);
      } else if (args.length != 1) {
         throw new PickleException("invalid pickle data for time; expected 1 or 4 args, got " + args.length);
      } else {
         int hour;
         int minute;
         int second;
         int microsec;
         if (args[0] instanceof String) {
            String params = (String)args[0];
            if (params.length() != 6) {
               throw new PickleException("invalid pickle data for time; expected arg of length 6, got length " + params.length());
            }

            hour = params.charAt(0);
            minute = params.charAt(1);
            second = params.charAt(2);
            int ms1 = params.charAt(3);
            int ms2 = params.charAt(4);
            int ms3 = params.charAt(5);
            microsec = (ms1 << 8 | ms2) << 8 | ms3;
         } else {
            byte[] params = (byte[])args[0];
            if (params.length != 6) {
               throw new PickleException("invalid pickle data for datetime; expected arg of length 6, got length " + params.length);
            }

            hour = params[0] & 255;
            minute = params[1] & 255;
            second = params[2] & 255;
            int ms1 = params[3] & 255;
            int ms2 = params[4] & 255;
            int ms3 = params[5] & 255;
            microsec = (ms1 << 8 | ms2) << 8 | ms3;
         }

         return new Time(hour, minute, second, microsec);
      }
   }

   private Calendar createDate(Object[] args) {
      if (args.length == 3) {
         int year = (Integer)args[0];
         int month = (Integer)args[1] - 1;
         int day = (Integer)args[2];
         return new GregorianCalendar(year, month, day);
      } else if (args.length != 1) {
         throw new PickleException("invalid pickle data for date; expected 1 arg, got " + args.length);
      } else {
         int yhi;
         int ylo;
         int month;
         int day;
         if (args[0] instanceof String) {
            String params = (String)args[0];
            if (params.length() != 4) {
               throw new PickleException("invalid pickle data for date; expected arg of length 4, got length " + params.length());
            }

            yhi = params.charAt(0);
            ylo = params.charAt(1);
            month = params.charAt(2) - 1;
            day = params.charAt(3);
         } else {
            byte[] params = (byte[])args[0];
            if (params.length != 4) {
               throw new PickleException("invalid pickle data for date; expected arg of length 4, got length " + params.length);
            }

            yhi = params[0] & 255;
            ylo = params[1] & 255;
            month = (params[2] & 255) - 1;
            day = params[3] & 255;
         }

         return new GregorianCalendar(yhi * 256 + ylo, month, day);
      }
   }
}
