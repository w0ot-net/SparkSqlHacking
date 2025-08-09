package org.apache.logging.log4j.core.util;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.StringTokenizer;
import java.util.TimeZone;
import java.util.TreeSet;
import org.apache.logging.log4j.util.Strings;

public final class CronExpression {
   protected static final int SECOND = 0;
   protected static final int MINUTE = 1;
   protected static final int HOUR = 2;
   protected static final int DAY_OF_MONTH = 3;
   protected static final int MONTH = 4;
   protected static final int DAY_OF_WEEK = 5;
   protected static final int YEAR = 6;
   protected static final int ALL_SPEC_INT = 99;
   protected static final int NO_SPEC_INT = 98;
   protected static final Integer ALL_SPEC = 99;
   protected static final Integer NO_SPEC = 98;
   protected static final Map monthMap = new HashMap(20);
   protected static final Map dayMap = new HashMap(60);
   private final String cronExpression;
   private TimeZone timeZone = null;
   protected transient TreeSet seconds;
   protected transient TreeSet minutes;
   protected transient TreeSet hours;
   protected transient TreeSet daysOfMonth;
   protected transient TreeSet months;
   protected transient TreeSet daysOfWeek;
   protected transient TreeSet years;
   protected transient boolean lastdayOfWeek = false;
   protected transient int nthdayOfWeek = 0;
   protected transient boolean lastdayOfMonth = false;
   protected transient boolean nearestWeekday = false;
   protected transient int lastdayOffset = 0;
   protected transient boolean expressionParsed = false;
   public static final int MAX_YEAR;
   public static final Calendar MIN_CAL;
   public static final Date MIN_DATE;

   public CronExpression(final String cronExpression) throws ParseException {
      if (cronExpression == null) {
         throw new IllegalArgumentException("cronExpression cannot be null");
      } else {
         this.cronExpression = Strings.toRootUpperCase(cronExpression);
         this.buildExpression(this.cronExpression);
      }
   }

   public boolean isSatisfiedBy(final Date date) {
      Calendar testDateCal = Calendar.getInstance(this.getTimeZone());
      testDateCal.setTime(date);
      testDateCal.set(14, 0);
      Date originalDate = testDateCal.getTime();
      testDateCal.add(13, -1);
      Date timeAfter = this.getTimeAfter(testDateCal.getTime());
      return timeAfter != null && timeAfter.equals(originalDate);
   }

   public Date getNextValidTimeAfter(final Date date) {
      return this.getTimeAfter(date);
   }

   public Date getNextInvalidTimeAfter(final Date date) {
      long difference = 1000L;
      Calendar adjustCal = Calendar.getInstance(this.getTimeZone());
      adjustCal.setTime(date);
      adjustCal.set(14, 0);
      Date lastDate = adjustCal.getTime();

      while(difference == 1000L) {
         Date newDate = this.getTimeAfter(lastDate);
         if (newDate == null) {
            break;
         }

         difference = newDate.getTime() - lastDate.getTime();
         if (difference == 1000L) {
            lastDate = newDate;
         }
      }

      return new Date(lastDate.getTime() + 1000L);
   }

   public TimeZone getTimeZone() {
      if (this.timeZone == null) {
         this.timeZone = TimeZone.getDefault();
      }

      return this.timeZone;
   }

   public void setTimeZone(final TimeZone timeZone) {
      this.timeZone = timeZone;
   }

   public String toString() {
      return this.cronExpression;
   }

   public static boolean isValidExpression(final String cronExpression) {
      try {
         new CronExpression(cronExpression);
         return true;
      } catch (ParseException var2) {
         return false;
      }
   }

   public static void validateExpression(final String cronExpression) throws ParseException {
      new CronExpression(cronExpression);
   }

   protected void buildExpression(final String expression) throws ParseException {
      this.expressionParsed = true;

      try {
         if (this.seconds == null) {
            this.seconds = new TreeSet();
         }

         if (this.minutes == null) {
            this.minutes = new TreeSet();
         }

         if (this.hours == null) {
            this.hours = new TreeSet();
         }

         if (this.daysOfMonth == null) {
            this.daysOfMonth = new TreeSet();
         }

         if (this.months == null) {
            this.months = new TreeSet();
         }

         if (this.daysOfWeek == null) {
            this.daysOfWeek = new TreeSet();
         }

         if (this.years == null) {
            this.years = new TreeSet();
         }

         int exprOn = 0;

         for(StringTokenizer exprsTok = new StringTokenizer(expression, " \t", false); exprsTok.hasMoreTokens() && exprOn <= 6; ++exprOn) {
            String expr = exprsTok.nextToken().trim();
            if (exprOn == 3 && expr.indexOf(76) != -1 && expr.length() > 1 && expr.contains(",")) {
               throw new ParseException("Support for specifying 'L' and 'LW' with other days of the month is not implemented", -1);
            }

            if (exprOn == 5 && expr.indexOf(76) != -1 && expr.length() > 1 && expr.contains(",")) {
               throw new ParseException("Support for specifying 'L' with other days of the week is not implemented", -1);
            }

            if (exprOn == 5 && expr.indexOf(35) != -1 && expr.indexOf(35, expr.indexOf(35) + 1) != -1) {
               throw new ParseException("Support for specifying multiple \"nth\" days is not implemented.", -1);
            }

            StringTokenizer vTok = new StringTokenizer(expr, ",");

            while(vTok.hasMoreTokens()) {
               String v = vTok.nextToken();
               this.storeExpressionVals(0, v, exprOn);
            }
         }

         if (exprOn <= 5) {
            throw new ParseException("Unexpected end of expression.", expression.length());
         } else {
            if (exprOn <= 6) {
               this.storeExpressionVals(0, "*", 6);
            }

            TreeSet<Integer> dow = this.getSet(5);
            TreeSet<Integer> dom = this.getSet(3);
            boolean dayOfMSpec = !dom.contains(NO_SPEC);
            boolean dayOfWSpec = !dow.contains(NO_SPEC);
            if ((!dayOfMSpec || dayOfWSpec) && (!dayOfWSpec || dayOfMSpec)) {
               throw new ParseException("Support for specifying both a day-of-week AND a day-of-month parameter is not implemented.", 0);
            }
         }
      } catch (ParseException pe) {
         throw pe;
      } catch (Exception e) {
         throw new ParseException("Illegal cron expression format (" + e.toString() + ")", 0);
      }
   }

   protected int storeExpressionVals(final int pos, final String s, final int type) throws ParseException {
      int incr = 0;
      int i = this.skipWhiteSpace(pos, s);
      if (i >= s.length()) {
         return i;
      } else {
         char c = s.charAt(i);
         if (c >= 'A' && c <= 'Z' && !s.equals("L") && !s.equals("LW") && !s.matches("^L-[0-9]*[W]?")) {
            String sub = s.substring(i, i + 3);
            int sval = -1;
            int eval = -1;
            if (type == 4) {
               sval = this.getMonthNumber(sub) + 1;
               if (sval <= 0) {
                  throw new ParseException("Invalid Month value: '" + sub + "'", i);
               }

               if (s.length() > i + 3) {
                  c = s.charAt(i + 3);
                  if (c == '-') {
                     i += 4;
                     sub = s.substring(i, i + 3);
                     eval = this.getMonthNumber(sub) + 1;
                     if (eval <= 0) {
                        throw new ParseException("Invalid Month value: '" + sub + "'", i);
                     }
                  }
               }
            } else {
               if (type != 5) {
                  throw new ParseException("Illegal characters for this position: '" + sub + "'", i);
               }

               sval = this.getDayOfWeekNumber(sub);
               if (sval < 0) {
                  throw new ParseException("Invalid Day-of-Week value: '" + sub + "'", i);
               }

               if (s.length() > i + 3) {
                  c = s.charAt(i + 3);
                  switch (c) {
                     case '#':
                        try {
                           i += 4;
                           this.nthdayOfWeek = Integers.parseInt(s.substring(i));
                           if (this.nthdayOfWeek >= 1 && this.nthdayOfWeek <= 5) {
                              break;
                           }

                           throw new Exception();
                        } catch (Exception var11) {
                           throw new ParseException("A numeric value between 1 and 5 must follow the '#' option", i);
                        }
                     case '-':
                        i += 4;
                        sub = s.substring(i, i + 3);
                        eval = this.getDayOfWeekNumber(sub);
                        if (eval < 0) {
                           throw new ParseException("Invalid Day-of-Week value: '" + sub + "'", i);
                        }
                        break;
                     case 'L':
                        this.lastdayOfWeek = true;
                        ++i;
                  }
               }
            }

            if (eval != -1) {
               incr = 1;
            }

            this.addToSet(sval, eval, incr, type);
            return i + 3;
         } else {
            switch (c) {
               case '*':
               case '/':
                  if (c == '*' && i + 1 >= s.length()) {
                     this.addToSet(99, -1, incr, type);
                     return i + 1;
                  } else {
                     if (c != '/' || i + 1 < s.length() && s.charAt(i + 1) != ' ' && s.charAt(i + 1) != '\t') {
                        if (c == '*') {
                           ++i;
                        }

                        c = s.charAt(i);
                        if (c == '/') {
                           ++i;
                           if (i >= s.length()) {
                              throw new ParseException("Unexpected end of string.", i);
                           }

                           incr = this.getNumericValue(s, i);
                           ++i;
                           if (incr > 10) {
                              ++i;
                           }

                           if (incr > 59 && (type == 0 || type == 1)) {
                              throw new ParseException("Increment > 60 : " + incr, i);
                           }

                           if (incr > 23 && type == 2) {
                              throw new ParseException("Increment > 24 : " + incr, i);
                           }

                           if (incr > 31 && type == 3) {
                              throw new ParseException("Increment > 31 : " + incr, i);
                           }

                           if (incr > 7 && type == 5) {
                              throw new ParseException("Increment > 7 : " + incr, i);
                           }

                           if (incr > 12 && type == 4) {
                              throw new ParseException("Increment > 12 : " + incr, i);
                           }
                        } else {
                           incr = 1;
                        }

                        this.addToSet(99, -1, incr, type);
                        return i;
                     }

                     throw new ParseException("'/' must be followed by an integer.", i);
                  }
               case '?':
                  ++i;
                  if (i + 1 < s.length() && s.charAt(i) != ' ' && s.charAt(i + 1) != '\t') {
                     throw new ParseException("Illegal character after '?': " + s.charAt(i), i);
                  } else if (type != 5 && type != 3) {
                     throw new ParseException("'?' can only be specfied for Day-of-Month or Day-of-Week.", i);
                  } else {
                     if (type == 5 && !this.lastdayOfMonth) {
                        int val = (Integer)this.daysOfMonth.last();
                        if (val == 98) {
                           throw new ParseException("'?' can only be specfied for Day-of-Month -OR- Day-of-Week.", i);
                        }
                     }

                     this.addToSet(98, -1, 0, type);
                     return i;
                  }
               case 'L':
                  ++i;
                  if (type == 3) {
                     this.lastdayOfMonth = true;
                  }

                  if (type == 5) {
                     this.addToSet(7, 7, 0, type);
                  }

                  if (type == 3 && s.length() > i) {
                     c = s.charAt(i);
                     if (c == '-') {
                        ValueSet vs = this.getValue(0, s, i + 1);
                        this.lastdayOffset = vs.value;
                        if (this.lastdayOffset > 30) {
                           throw new ParseException("Offset from last day must be <= 30", i + 1);
                        }

                        i = vs.pos;
                     }

                     if (s.length() > i) {
                        c = s.charAt(i);
                        if (c == 'W') {
                           this.nearestWeekday = true;
                           ++i;
                        }
                     }
                  }

                  return i;
               default:
                  if (c >= '0' && c <= '9') {
                     int val = Integer.parseInt(String.valueOf(c));
                     ++i;
                     if (i >= s.length()) {
                        this.addToSet(val, -1, -1, type);
                        return i;
                     } else {
                        c = s.charAt(i);
                        if (c >= '0' && c <= '9') {
                           ValueSet vs = this.getValue(val, s, i);
                           val = vs.value;
                           i = vs.pos;
                        }

                        i = this.checkNext(i, s, val, type);
                        return i;
                     }
                  } else {
                     throw new ParseException("Unexpected character: " + c, i);
                  }
            }
         }
      }
   }

   protected int checkNext(final int pos, final String s, final int val, final int type) throws ParseException {
      int end = -1;
      if (pos >= s.length()) {
         this.addToSet(val, end, -1, type);
         return pos;
      } else {
         char c = s.charAt(pos);
         if (c == 'L') {
            if (type == 5) {
               if (val >= 1 && val <= 7) {
                  this.lastdayOfWeek = true;
                  TreeSet<Integer> set = this.getSet(type);
                  set.add(val);
                  int var25 = pos + 1;
                  return var25;
               } else {
                  throw new ParseException("Day-of-Week values must be between 1 and 7", -1);
               }
            } else {
               throw new ParseException("'L' option is not valid here. (pos=" + pos + ")", pos);
            }
         } else if (c == 'W') {
            if (type == 3) {
               this.nearestWeekday = true;
               if (val > 31) {
                  throw new ParseException("The 'W' option does not make sense with values larger than 31 (max number of days in a month)", pos);
               } else {
                  TreeSet<Integer> set = this.getSet(type);
                  set.add(val);
                  int var24 = pos + 1;
                  return var24;
               }
            } else {
               throw new ParseException("'W' option is not valid here. (pos=" + pos + ")", pos);
            }
         } else {
            switch (c) {
               case '#':
                  if (type != 5) {
                     throw new ParseException("'#' option is not valid here. (pos=" + pos + ")", pos);
                  } else {
                     int var21 = pos + 1;

                     try {
                        this.nthdayOfWeek = Integers.parseInt(s.substring(var21));
                        if (this.nthdayOfWeek < 1 || this.nthdayOfWeek > 5) {
                           throw new Exception();
                        }
                     } catch (Exception var13) {
                        throw new ParseException("A numeric value between 1 and 5 must follow the '#' option", var21);
                     }

                     TreeSet<Integer> set = this.getSet(type);
                     set.add(val);
                     ++var21;
                     return var21;
                  }
               case '-':
                  int var17 = pos + 1;
                  c = s.charAt(var17);
                  int v = Integer.parseInt(String.valueOf(c));
                  end = v;
                  ++var17;
                  if (var17 >= s.length()) {
                     this.addToSet(val, v, 1, type);
                     return var17;
                  } else {
                     c = s.charAt(var17);
                     if (c >= '0' && c <= '9') {
                        ValueSet vs = this.getValue(v, s, var17);
                        end = vs.value;
                        var17 = vs.pos;
                     }

                     if (var17 < s.length() && s.charAt(var17) == '/') {
                        ++var17;
                        c = s.charAt(var17);
                        int v2 = Integer.parseInt(String.valueOf(c));
                        ++var17;
                        if (var17 >= s.length()) {
                           this.addToSet(val, end, v2, type);
                           return var17;
                        }

                        c = s.charAt(var17);
                        if (c >= '0' && c <= '9') {
                           ValueSet vs = this.getValue(v2, s, var17);
                           int v3 = vs.value;
                           this.addToSet(val, end, v3, type);
                           var17 = vs.pos;
                        } else {
                           this.addToSet(val, end, v2, type);
                        }

                        return var17;
                     }

                     this.addToSet(val, end, 1, type);
                     return var17;
                  }
               case '/':
                  int i = pos + 1;
                  c = s.charAt(i);
                  int v2 = Integer.parseInt(String.valueOf(c));
                  ++i;
                  if (i >= s.length()) {
                     this.addToSet(val, end, v2, type);
                     return i;
                  } else {
                     c = s.charAt(i);
                     if (c >= '0' && c <= '9') {
                        ValueSet vs = this.getValue(v2, s, i);
                        int v3 = vs.value;
                        this.addToSet(val, end, v3, type);
                        i = vs.pos;
                        return i;
                     }

                     throw new ParseException("Unexpected character '" + c + "' after '/'", i);
                  }
               default:
                  this.addToSet(val, end, 0, type);
                  int var23 = pos + 1;
                  return var23;
            }
         }
      }
   }

   public String getCronExpression() {
      return this.cronExpression;
   }

   public String getExpressionSummary() {
      StringBuilder buf = new StringBuilder();
      buf.append("seconds: ");
      buf.append(this.getExpressionSetSummary((Set)this.seconds));
      buf.append("\n");
      buf.append("minutes: ");
      buf.append(this.getExpressionSetSummary((Set)this.minutes));
      buf.append("\n");
      buf.append("hours: ");
      buf.append(this.getExpressionSetSummary((Set)this.hours));
      buf.append("\n");
      buf.append("daysOfMonth: ");
      buf.append(this.getExpressionSetSummary((Set)this.daysOfMonth));
      buf.append("\n");
      buf.append("months: ");
      buf.append(this.getExpressionSetSummary((Set)this.months));
      buf.append("\n");
      buf.append("daysOfWeek: ");
      buf.append(this.getExpressionSetSummary((Set)this.daysOfWeek));
      buf.append("\n");
      buf.append("lastdayOfWeek: ");
      buf.append(this.lastdayOfWeek);
      buf.append("\n");
      buf.append("nearestWeekday: ");
      buf.append(this.nearestWeekday);
      buf.append("\n");
      buf.append("NthDayOfWeek: ");
      buf.append(this.nthdayOfWeek);
      buf.append("\n");
      buf.append("lastdayOfMonth: ");
      buf.append(this.lastdayOfMonth);
      buf.append("\n");
      buf.append("years: ");
      buf.append(this.getExpressionSetSummary((Set)this.years));
      buf.append("\n");
      return buf.toString();
   }

   protected String getExpressionSetSummary(final Set set) {
      if (set.contains(NO_SPEC)) {
         return "?";
      } else if (set.contains(ALL_SPEC)) {
         return "*";
      } else {
         StringBuilder buf = new StringBuilder();
         Iterator<Integer> itr = set.iterator();

         for(boolean first = true; itr.hasNext(); first = false) {
            Integer iVal = (Integer)itr.next();
            String val = iVal.toString();
            if (!first) {
               buf.append(",");
            }

            buf.append(val);
         }

         return buf.toString();
      }
   }

   protected String getExpressionSetSummary(final ArrayList list) {
      if (list.contains(NO_SPEC)) {
         return "?";
      } else if (list.contains(ALL_SPEC)) {
         return "*";
      } else {
         StringBuilder buf = new StringBuilder();
         Iterator<Integer> itr = list.iterator();

         for(boolean first = true; itr.hasNext(); first = false) {
            Integer iVal = (Integer)itr.next();
            String val = iVal.toString();
            if (!first) {
               buf.append(",");
            }

            buf.append(val);
         }

         return buf.toString();
      }
   }

   protected int skipWhiteSpace(int i, final String s) {
      while(i < s.length() && (s.charAt(i) == ' ' || s.charAt(i) == '\t')) {
         ++i;
      }

      return i;
   }

   protected int findNextWhiteSpace(int i, final String s) {
      while(i < s.length() && (s.charAt(i) != ' ' || s.charAt(i) != '\t')) {
         ++i;
      }

      return i;
   }

   protected void addToSet(final int val, final int end, int incr, final int type) throws ParseException {
      TreeSet<Integer> set = this.getSet(type);
      switch (type) {
         case 0:
         case 1:
            if ((val < 0 || val > 59 || end > 59) && val != 99) {
               throw new ParseException("Minute and Second values must be between 0 and 59", -1);
            }
            break;
         case 2:
            if ((val < 0 || val > 23 || end > 23) && val != 99) {
               throw new ParseException("Hour values must be between 0 and 23", -1);
            }
            break;
         case 3:
            if ((val < 1 || val > 31 || end > 31) && val != 99 && val != 98) {
               throw new ParseException("Day of month values must be between 1 and 31", -1);
            }
            break;
         case 4:
            if ((val < 1 || val > 12 || end > 12) && val != 99) {
               throw new ParseException("Month values must be between 1 and 12", -1);
            }
            break;
         case 5:
            if ((val == 0 || val > 7 || end > 7) && val != 99 && val != 98) {
               throw new ParseException("Day-of-Week values must be between 1 and 7", -1);
            }
      }

      if ((incr == 0 || incr == -1) && val != 99) {
         if (val != -1) {
            set.add(val);
         } else {
            set.add(NO_SPEC);
         }

      } else {
         int startAt = val;
         int stopAt = end;
         if (val == 99 && incr <= 0) {
            incr = 1;
            set.add(ALL_SPEC);
         }

         switch (type) {
            case 0:
            case 1:
               if (end == -1) {
                  stopAt = 59;
               }

               if (val == -1 || val == 99) {
                  startAt = 0;
               }
               break;
            case 2:
               if (end == -1) {
                  stopAt = 23;
               }

               if (val == -1 || val == 99) {
                  startAt = 0;
               }
               break;
            case 3:
               if (end == -1) {
                  stopAt = 31;
               }

               if (val == -1 || val == 99) {
                  startAt = 1;
               }
               break;
            case 4:
               if (end == -1) {
                  stopAt = 12;
               }

               if (val == -1 || val == 99) {
                  startAt = 1;
               }
               break;
            case 5:
               if (end == -1) {
                  stopAt = 7;
               }

               if (val == -1 || val == 99) {
                  startAt = 1;
               }
               break;
            case 6:
               if (end == -1) {
                  stopAt = MAX_YEAR;
               }

               if (val == -1 || val == 99) {
                  startAt = 1970;
               }
         }

         int max = -1;
         if (stopAt < startAt) {
            switch (type) {
               case 0:
                  max = 60;
                  break;
               case 1:
                  max = 60;
                  break;
               case 2:
                  max = 24;
                  break;
               case 3:
                  max = 31;
                  break;
               case 4:
                  max = 12;
                  break;
               case 5:
                  max = 7;
                  break;
               case 6:
                  throw new IllegalArgumentException("Start year must be less than stop year");
               default:
                  throw new IllegalArgumentException("Unexpected type encountered");
            }

            stopAt += max;
         }

         for(int i = startAt; i <= stopAt; i += incr) {
            if (max == -1) {
               set.add(i);
            } else {
               int i2 = i % max;
               if (i2 == 0 && (type == 4 || type == 5 || type == 3)) {
                  i2 = max;
               }

               set.add(i2);
            }
         }

      }
   }

   TreeSet getSet(final int type) {
      switch (type) {
         case 0:
            return this.seconds;
         case 1:
            return this.minutes;
         case 2:
            return this.hours;
         case 3:
            return this.daysOfMonth;
         case 4:
            return this.months;
         case 5:
            return this.daysOfWeek;
         case 6:
            return this.years;
         default:
            return null;
      }
   }

   protected ValueSet getValue(final int v, final String s, int i) {
      char c = s.charAt(i);

      StringBuilder s1;
      for(s1 = new StringBuilder(String.valueOf(v)); c >= '0' && c <= '9'; c = s.charAt(i)) {
         s1.append(c);
         ++i;
         if (i >= s.length()) {
            break;
         }
      }

      ValueSet val = new ValueSet();
      val.pos = i < s.length() ? i : i + 1;
      val.value = Integers.parseInt(s1.toString());
      return val;
   }

   protected int getNumericValue(final String s, final int i) {
      int endOfVal = this.findNextWhiteSpace(i, s);
      String val = s.substring(i, endOfVal);
      return Integers.parseInt(val);
   }

   protected int getMonthNumber(final String s) {
      Integer integer = (Integer)monthMap.get(s);
      return integer == null ? -1 : integer;
   }

   protected int getDayOfWeekNumber(final String s) {
      Integer integer = (Integer)dayMap.get(s);
      return integer == null ? -1 : integer;
   }

   public Date getTimeAfter(Date afterTime) {
      Calendar cl = new GregorianCalendar(this.getTimeZone());
      afterTime = new Date(afterTime.getTime() + 1000L);
      cl.setTime(afterTime);
      cl.set(14, 0);
      boolean gotOne = false;

      while(!gotOne) {
         if (cl.get(1) > 2999) {
            return null;
         }

         int sec = cl.get(13);
         int min = cl.get(12);
         SortedSet<Integer> st = this.seconds.tailSet(sec);
         if (st != null && st.size() != 0) {
            sec = (Integer)st.first();
         } else {
            sec = (Integer)this.seconds.first();
            ++min;
            cl.set(12, min);
         }

         cl.set(13, sec);
         min = cl.get(12);
         int hr = cl.get(11);
         int t = -1;
         st = this.minutes.tailSet(min);
         if (st != null && st.size() != 0) {
            t = min;
            min = (Integer)st.first();
         } else {
            min = (Integer)this.minutes.first();
            ++hr;
         }

         if (min == t) {
            cl.set(12, min);
            hr = cl.get(11);
            int day = cl.get(5);
            t = -1;
            st = this.hours.tailSet(hr);
            if (st != null && st.size() != 0) {
               t = hr;
               hr = (Integer)st.first();
            } else {
               hr = (Integer)this.hours.first();
               ++day;
            }

            if (hr == t) {
               cl.set(11, hr);
               day = cl.get(5);
               int mon = cl.get(2) + 1;
               t = -1;
               int tmon = mon;
               boolean dayOfMSpec = !this.daysOfMonth.contains(NO_SPEC);
               boolean dayOfWSpec = !this.daysOfWeek.contains(NO_SPEC);
               if (dayOfMSpec && !dayOfWSpec) {
                  st = this.daysOfMonth.tailSet(day);
                  if (this.lastdayOfMonth) {
                     if (!this.nearestWeekday) {
                        t = day;
                        day = this.getLastDayOfMonth(mon, cl.get(1));
                        day -= this.lastdayOffset;
                        if (t > day) {
                           ++mon;
                           if (mon > 12) {
                              mon = 1;
                              tmon = 3333;
                              cl.add(1, 1);
                           }

                           day = 1;
                        }
                     } else {
                        t = day;
                        int var40 = this.getLastDayOfMonth(mon, cl.get(1));
                        day = var40 - this.lastdayOffset;
                        Calendar tcal = Calendar.getInstance(this.getTimeZone());
                        tcal.set(13, 0);
                        tcal.set(12, 0);
                        tcal.set(11, 0);
                        tcal.set(5, day);
                        tcal.set(2, mon - 1);
                        tcal.set(1, cl.get(1));
                        int ldom = this.getLastDayOfMonth(mon, cl.get(1));
                        int dow = tcal.get(7);
                        if (dow == 7 && day == 1) {
                           day += 2;
                        } else if (dow == 7) {
                           --day;
                        } else if (dow == 1 && day == ldom) {
                           day -= 2;
                        } else if (dow == 1) {
                           ++day;
                        }

                        tcal.set(13, sec);
                        tcal.set(12, min);
                        tcal.set(11, hr);
                        tcal.set(5, day);
                        tcal.set(2, mon - 1);
                        Date nTime = tcal.getTime();
                        if (nTime.before(afterTime)) {
                           day = 1;
                           ++mon;
                        }
                     }
                  } else if (this.nearestWeekday) {
                     t = day;
                     day = (Integer)this.daysOfMonth.first();
                     Calendar tcal = Calendar.getInstance(this.getTimeZone());
                     tcal.set(13, 0);
                     tcal.set(12, 0);
                     tcal.set(11, 0);
                     tcal.set(5, day);
                     tcal.set(2, mon - 1);
                     tcal.set(1, cl.get(1));
                     int ldom = this.getLastDayOfMonth(mon, cl.get(1));
                     int dow = tcal.get(7);
                     if (dow == 7 && day == 1) {
                        day += 2;
                     } else if (dow == 7) {
                        --day;
                     } else if (dow == 1 && day == ldom) {
                        day -= 2;
                     } else if (dow == 1) {
                        ++day;
                     }

                     tcal.set(13, sec);
                     tcal.set(12, min);
                     tcal.set(11, hr);
                     tcal.set(5, day);
                     tcal.set(2, mon - 1);
                     Date nTime = tcal.getTime();
                     if (nTime.before(afterTime)) {
                        day = (Integer)this.daysOfMonth.first();
                        ++mon;
                     }
                  } else if (st != null && st.size() != 0) {
                     t = day;
                     day = (Integer)st.first();
                     int lastDay = this.getLastDayOfMonth(mon, cl.get(1));
                     if (day > lastDay) {
                        day = (Integer)this.daysOfMonth.first();
                        ++mon;
                     }
                  } else {
                     day = (Integer)this.daysOfMonth.first();
                     ++mon;
                  }

                  if (day != t || mon != tmon) {
                     cl.set(13, 0);
                     cl.set(12, 0);
                     cl.set(11, 0);
                     cl.set(5, day);
                     cl.set(2, mon - 1);
                     continue;
                  }
               } else {
                  if (!dayOfWSpec || dayOfMSpec) {
                     throw new UnsupportedOperationException("Support for specifying both a day-of-week AND a day-of-month parameter is not implemented.");
                  }

                  if (this.lastdayOfWeek) {
                     int dow = (Integer)this.daysOfWeek.first();
                     int cDow = cl.get(7);
                     int daysToAdd = 0;
                     if (cDow < dow) {
                        daysToAdd = dow - cDow;
                     }

                     if (cDow > dow) {
                        daysToAdd = dow + (7 - cDow);
                     }

                     int lDay = this.getLastDayOfMonth(mon, cl.get(1));
                     if (day + daysToAdd > lDay) {
                        cl.set(13, 0);
                        cl.set(12, 0);
                        cl.set(11, 0);
                        cl.set(5, 1);
                        cl.set(2, mon);
                        continue;
                     }

                     while(day + daysToAdd + 7 <= lDay) {
                        daysToAdd += 7;
                     }

                     day += daysToAdd;
                     if (daysToAdd > 0) {
                        cl.set(13, 0);
                        cl.set(12, 0);
                        cl.set(11, 0);
                        cl.set(5, day);
                        cl.set(2, mon - 1);
                        continue;
                     }
                  } else if (this.nthdayOfWeek != 0) {
                     int dow = (Integer)this.daysOfWeek.first();
                     int cDow = cl.get(7);
                     int daysToAdd = 0;
                     if (cDow < dow) {
                        daysToAdd = dow - cDow;
                     } else if (cDow > dow) {
                        daysToAdd = dow + (7 - cDow);
                     }

                     boolean dayShifted = false;
                     if (daysToAdd > 0) {
                        dayShifted = true;
                     }

                     day += daysToAdd;
                     int weekOfMonth = day / 7;
                     if (day % 7 > 0) {
                        ++weekOfMonth;
                     }

                     daysToAdd = (this.nthdayOfWeek - weekOfMonth) * 7;
                     day += daysToAdd;
                     if (daysToAdd < 0 || day > this.getLastDayOfMonth(mon, cl.get(1))) {
                        cl.set(13, 0);
                        cl.set(12, 0);
                        cl.set(11, 0);
                        cl.set(5, 1);
                        cl.set(2, mon);
                        continue;
                     }

                     if (daysToAdd > 0 || dayShifted) {
                        cl.set(13, 0);
                        cl.set(12, 0);
                        cl.set(11, 0);
                        cl.set(5, day);
                        cl.set(2, mon - 1);
                        continue;
                     }
                  } else {
                     int cDow = cl.get(7);
                     int dow = (Integer)this.daysOfWeek.first();
                     st = this.daysOfWeek.tailSet(cDow);
                     if (st != null && st.size() > 0) {
                        dow = (Integer)st.first();
                     }

                     int daysToAdd = 0;
                     if (cDow < dow) {
                        daysToAdd = dow - cDow;
                     }

                     if (cDow > dow) {
                        daysToAdd = dow + (7 - cDow);
                     }

                     int lDay = this.getLastDayOfMonth(mon, cl.get(1));
                     if (day + daysToAdd > lDay) {
                        cl.set(13, 0);
                        cl.set(12, 0);
                        cl.set(11, 0);
                        cl.set(5, 1);
                        cl.set(2, mon);
                        continue;
                     }

                     if (daysToAdd > 0) {
                        cl.set(13, 0);
                        cl.set(12, 0);
                        cl.set(11, 0);
                        cl.set(5, day + daysToAdd);
                        cl.set(2, mon - 1);
                        continue;
                     }
                  }
               }

               cl.set(5, day);
               mon = cl.get(2) + 1;
               int year = cl.get(1);
               t = -1;
               if (year > MAX_YEAR) {
                  return null;
               }

               st = this.months.tailSet(mon);
               if (st != null && st.size() != 0) {
                  t = mon;
                  mon = (Integer)st.first();
               } else {
                  mon = (Integer)this.months.first();
                  ++year;
               }

               if (mon != t) {
                  cl.set(13, 0);
                  cl.set(12, 0);
                  cl.set(11, 0);
                  cl.set(5, 1);
                  cl.set(2, mon - 1);
                  cl.set(1, year);
               } else {
                  cl.set(2, mon - 1);
                  year = cl.get(1);
                  t = -1;
                  st = this.years.tailSet(year);
                  if (st == null || st.size() == 0) {
                     return null;
                  }

                  year = (Integer)st.first();
                  if (year != year) {
                     cl.set(13, 0);
                     cl.set(12, 0);
                     cl.set(11, 0);
                     cl.set(5, 1);
                     cl.set(2, 0);
                     cl.set(1, year);
                  } else {
                     cl.set(1, year);
                     gotOne = true;
                  }
               }
            } else {
               cl.set(13, 0);
               cl.set(12, 0);
               cl.set(5, day);
               this.setCalendarHour(cl, hr);
            }
         } else {
            cl.set(13, 0);
            cl.set(12, min);
            this.setCalendarHour(cl, hr);
         }
      }

      return cl.getTime();
   }

   protected void setCalendarHour(final Calendar cal, final int hour) {
      cal.set(11, hour);
      if (cal.get(11) != hour && hour != 24) {
         cal.set(11, hour + 1);
      }

   }

   protected Date getTimeBefore(final Date targetDate) {
      Calendar cl = Calendar.getInstance(this.getTimeZone());
      cl.setTime(targetDate);
      cl.set(14, 0);
      Date targetDateNoMs = cl.getTime();
      Date start = targetDateNoMs;
      long minIncrement = this.findMinIncrement();

      Date prevFireTime;
      do {
         Date prevCheckDate = new Date(start.getTime() - minIncrement);
         prevFireTime = this.getTimeAfter(prevCheckDate);
         if (prevFireTime == null || prevFireTime.before(MIN_DATE)) {
            return null;
         }

         start = prevCheckDate;
      } while(prevFireTime.compareTo(targetDateNoMs) >= 0);

      return prevFireTime;
   }

   public Date getPrevFireTime(final Date targetDate) {
      return this.getTimeBefore(targetDate);
   }

   private long findMinIncrement() {
      if (this.seconds.size() != 1) {
         return (long)(this.minInSet(this.seconds) * 1000);
      } else if ((Integer)this.seconds.first() == 99) {
         return 1000L;
      } else if (this.minutes.size() != 1) {
         return (long)(this.minInSet(this.minutes) * '\uea60');
      } else if ((Integer)this.minutes.first() == 99) {
         return 60000L;
      } else if (this.hours.size() != 1) {
         return (long)(this.minInSet(this.hours) * 3600000);
      } else {
         return (Integer)this.hours.first() == 99 ? 3600000L : 86400000L;
      }
   }

   private int minInSet(final TreeSet set) {
      int previous = 0;
      int min = Integer.MAX_VALUE;
      boolean first = true;

      for(int value : set) {
         if (first) {
            previous = value;
            first = false;
         } else {
            int diff = value - previous;
            if (diff < min) {
               min = diff;
            }
         }
      }

      return min;
   }

   public Date getFinalFireTime() {
      return null;
   }

   protected boolean isLeapYear(final int year) {
      return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
   }

   protected int getLastDayOfMonth(final int monthNum, final int year) {
      switch (monthNum) {
         case 1:
            return 31;
         case 2:
            return this.isLeapYear(year) ? 29 : 28;
         case 3:
            return 31;
         case 4:
            return 30;
         case 5:
            return 31;
         case 6:
            return 30;
         case 7:
            return 31;
         case 8:
            return 31;
         case 9:
            return 30;
         case 10:
            return 31;
         case 11:
            return 30;
         case 12:
            return 31;
         default:
            throw new IllegalArgumentException("Illegal month number: " + monthNum);
      }
   }

   static {
      monthMap.put("JAN", 0);
      monthMap.put("FEB", 1);
      monthMap.put("MAR", 2);
      monthMap.put("APR", 3);
      monthMap.put("MAY", 4);
      monthMap.put("JUN", 5);
      monthMap.put("JUL", 6);
      monthMap.put("AUG", 7);
      monthMap.put("SEP", 8);
      monthMap.put("OCT", 9);
      monthMap.put("NOV", 10);
      monthMap.put("DEC", 11);
      dayMap.put("SUN", 1);
      dayMap.put("MON", 2);
      dayMap.put("TUE", 3);
      dayMap.put("WED", 4);
      dayMap.put("THU", 5);
      dayMap.put("FRI", 6);
      dayMap.put("SAT", 7);
      MAX_YEAR = Calendar.getInstance().get(1) + 100;
      MIN_CAL = Calendar.getInstance();
      MIN_CAL.set(1970, 0, 1);
      MIN_DATE = MIN_CAL.getTime();
   }

   private class ValueSet {
      public int value;
      public int pos;

      private ValueSet() {
      }
   }
}
