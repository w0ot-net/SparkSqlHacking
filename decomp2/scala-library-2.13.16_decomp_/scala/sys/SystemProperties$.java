package scala.sys;

import scala.Function0;

public final class SystemProperties$ {
   public static final SystemProperties$ MODULE$ = new SystemProperties$();
   private static BooleanProp headless;
   private static BooleanProp preferIPv4Stack;
   private static BooleanProp preferIPv6Addresses;
   private static BooleanProp noTraceSuppression;
   private static volatile byte bitmap$0;

   public synchronized Object exclusively(final Function0 body) {
      return body.apply();
   }

   public SystemProperties$ systemPropertiesToCompanion(final SystemProperties p) {
      return this;
   }

   private final String HeadlessKey() {
      return "java.awt.headless";
   }

   private final String PreferIPv4StackKey() {
      return "java.net.preferIPv4Stack";
   }

   private final String PreferIPv6AddressesKey() {
      return "java.net.preferIPv6Addresses";
   }

   private final String NoTraceSuppressionKey() {
      return "scala.control.noTraceSuppression";
   }

   public String help(final String key) {
      switch (key == null ? 0 : key.hashCode()) {
         case -301073227:
            if ("java.awt.headless".equals(key)) {
               return "system should not utilize a display device";
            }
            break;
         case -239081610:
            if ("java.net.preferIPv4Stack".equals(key)) {
               return "system should prefer IPv4 sockets";
            }
            break;
         case 1220942952:
            if ("scala.control.noTraceSuppression".equals(key)) {
               return "scala should not suppress any stack trace creation";
            }
            break;
         case 1842371534:
            if ("java.net.preferIPv6Addresses".equals(key)) {
               return "system should prefer IPv6 addresses";
            }
      }

      return "";
   }

   private BooleanProp headless$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(bitmap$0 & 1) == 0) {
            headless = BooleanProp$.MODULE$.keyExists("java.awt.headless");
            bitmap$0 = (byte)(bitmap$0 | 1);
         }
      } catch (Throwable var2) {
         throw var2;
      }

      return headless;
   }

   public BooleanProp headless() {
      return (byte)(bitmap$0 & 1) == 0 ? this.headless$lzycompute() : headless;
   }

   private BooleanProp preferIPv4Stack$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(bitmap$0 & 2) == 0) {
            preferIPv4Stack = BooleanProp$.MODULE$.keyExists("java.net.preferIPv4Stack");
            bitmap$0 = (byte)(bitmap$0 | 2);
         }
      } catch (Throwable var2) {
         throw var2;
      }

      return preferIPv4Stack;
   }

   public BooleanProp preferIPv4Stack() {
      return (byte)(bitmap$0 & 2) == 0 ? this.preferIPv4Stack$lzycompute() : preferIPv4Stack;
   }

   private BooleanProp preferIPv6Addresses$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(bitmap$0 & 4) == 0) {
            preferIPv6Addresses = BooleanProp$.MODULE$.keyExists("java.net.preferIPv6Addresses");
            bitmap$0 = (byte)(bitmap$0 | 4);
         }
      } catch (Throwable var2) {
         throw var2;
      }

      return preferIPv6Addresses;
   }

   public BooleanProp preferIPv6Addresses() {
      return (byte)(bitmap$0 & 4) == 0 ? this.preferIPv6Addresses$lzycompute() : preferIPv6Addresses;
   }

   private BooleanProp noTraceSuppression$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(bitmap$0 & 8) == 0) {
            noTraceSuppression = BooleanProp$.MODULE$.valueIsTrue("scala.control.noTraceSuppression");
            bitmap$0 = (byte)(bitmap$0 | 8);
         }
      } catch (Throwable var2) {
         throw var2;
      }

      return noTraceSuppression;
   }

   public BooleanProp noTraceSuppression() {
      return (byte)(bitmap$0 & 8) == 0 ? this.noTraceSuppression$lzycompute() : noTraceSuppression;
   }

   private SystemProperties$() {
   }
}
