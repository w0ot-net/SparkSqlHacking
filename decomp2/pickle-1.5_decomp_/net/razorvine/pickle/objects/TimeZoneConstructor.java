package net.razorvine.pickle.objects;

import java.util.TimeZone;
import net.razorvine.pickle.IObjectConstructor;
import net.razorvine.pickle.PickleException;

public class TimeZoneConstructor implements IObjectConstructor {
   public static final int UTC = 1;
   public static final int PYTZ = 2;
   public static final int DATEUTIL_TZUTC = 3;
   public static final int DATEUTIL_TZFILE = 4;
   public static final int DATEUTIL_GETTZ = 5;
   public static final int TZINFO = 6;
   private final int pythontype;

   public TimeZoneConstructor(int pythontype) {
      this.pythontype = pythontype;
   }

   public Object construct(Object[] args) throws PickleException {
      if (this.pythontype == 1) {
         return this.createUTC();
      } else if (this.pythontype == 2) {
         return this.createZoneFromPytz(args);
      } else if (this.pythontype == 3) {
         return this.createInfoFromDateutilTzutc(args);
      } else if (this.pythontype == 4) {
         return this.createInfoFromDateutilTzfile(args);
      } else if (this.pythontype == 5) {
         return this.createInfoFromDateutilGettz(args);
      } else if (this.pythontype == 6) {
         return this.createInfo(args);
      } else {
         throw new PickleException("invalid object type");
      }
   }

   public Object reconstruct(Object baseConstructor, Object state) {
      if (!(state instanceof Tzinfo)) {
         throw new PickleException("invalid pickle data for tzinfo reconstruction; expected emtpy tzinfo state class");
      } else if (!(baseConstructor instanceof TimeZoneConstructor)) {
         throw new PickleException("invalid pickle data for tzinfo reconstruction; expected a TimeZoneConstructor from a known tzinfo subclass");
      } else if (this.pythontype == 3) {
         return TimeZone.getTimeZone("UTC");
      } else {
         throw new PickleException("unsupported pickle data for tzinfo reconstruction; support for tzinfo subclasses other than tztuc has not been implemented");
      }
   }

   private Object createInfo(Object[] args) {
      return new Tzinfo();
   }

   private Object createInfoFromDateutilTzutc(Object[] args) {
      return new Tzinfo(TimeZone.getTimeZone("UTC"));
   }

   private Object createInfoFromDateutilTzfile(Object[] args) {
      if (args.length != 1) {
         throw new PickleException("invalid pickle data for dateutil tzfile timezone; expected 1 args, got " + args.length);
      } else {
         String identifier = (String)args[0];
         int index = identifier.indexOf("zoneinfo");
         if (index != -1) {
            identifier = identifier.substring(index + 8 + 1);
            return new Tzinfo(TimeZone.getTimeZone(identifier));
         } else {
            throw new PickleException("couldn't parse timezone identifier from zoneinfo path" + identifier);
         }
      }
   }

   private Object createInfoFromDateutilGettz(Object[] args) {
      if (args.length != 1) {
         throw new PickleException("invalid pickle data for dateutil gettz call; expected 1 args, got " + args.length);
      } else {
         String identifier = (String)args[0];
         return new Tzinfo(TimeZone.getTimeZone(identifier));
      }
   }

   private Object createZoneFromPytz(Object[] args) {
      if (args.length != 4 && args.length != 1) {
         throw new PickleException("invalid pickle data for pytz timezone; expected 1 or 4 args, got " + args.length);
      } else if (!(args[0] instanceof String)) {
         throw new PickleException("invalid pickle data for pytz timezone; expected string argument as first tuple member");
      } else {
         return TimeZone.getTimeZone((String)args[0]);
      }
   }

   private Object createUTC() {
      return TimeZone.getTimeZone("UTC");
   }
}
