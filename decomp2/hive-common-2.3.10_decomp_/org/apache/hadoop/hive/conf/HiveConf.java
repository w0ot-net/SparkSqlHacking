package org.apache.hadoop.hive.conf;

import com.google.common.base.Joiner;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.security.auth.login.LoginException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.common.FileUtils;
import org.apache.hadoop.hive.common.classification.InterfaceAudience;
import org.apache.hadoop.hive.shims.Utils;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.security.UserGroupInformation;
import org.apache.hadoop.util.StringUtils;
import org.apache.hive.common.HiveCompat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HiveConf extends Configuration {
   protected String hiveJar;
   protected Properties origProp;
   protected String auxJars;
   private static final Logger l4j = LoggerFactory.getLogger(HiveConf.class);
   private static boolean loadMetastoreConfig = false;
   private static boolean loadHiveServer2Config = false;
   private static URL hiveDefaultURL = null;
   private static URL hiveSiteURL = null;
   private static URL hivemetastoreSiteUrl = null;
   private static URL hiveServer2SiteUrl = null;
   private static byte[] confVarByteArray = null;
   private static final Map vars = new HashMap();
   private static final Map metaConfs = new HashMap();
   private final List restrictList = new ArrayList();
   private final Set hiddenSet = new HashSet();
   private Pattern modWhiteListPattern = null;
   private volatile boolean isSparkConfigUpdated = false;
   private static final int LOG_PREFIX_LENGTH = 64;
   @InterfaceAudience.Private
   public static final String PREFIX_LLAP = "llap.";
   @InterfaceAudience.Private
   public static final String PREFIX_HIVE_LLAP = "hive.llap.";
   public static final ConfVars[] metaVars;
   public static final ConfVars[] metaConfVars;
   public static final String HIVE_LLAP_DAEMON_SERVICE_PRINCIPAL_NAME = "hive.llap.daemon.service.principal";
   public static final String HIVE_SERVER2_AUTHENTICATION_LDAP_USERMEMBERSHIPKEY_NAME = "hive.server2.authentication.ldap.userMembershipKey";
   public static final ConfVars[] dbVars;
   private static final Set llapDaemonVarsSet;
   private static final String[] sqlStdAuthSafeVarNames;
   static final String[] sqlStdAuthSafeVarNameRegexes;
   private static final Object reverseMapLock;
   private static HashMap reverseMap;

   public boolean getSparkConfigUpdated() {
      return this.isSparkConfigUpdated;
   }

   public void setSparkConfigUpdated(boolean isSparkConfigUpdated) {
      this.isSparkConfigUpdated = isSparkConfigUpdated;
   }

   private static URL findConfigFile(ClassLoader classLoader, String name, boolean doLog) {
      URL result = classLoader.getResource(name);
      if (result == null) {
         String confPath = System.getenv("HIVE_CONF_DIR");
         result = checkConfigFile(new File(confPath, name));
         if (result == null) {
            String homePath = System.getenv("HIVE_HOME");
            String nameInConf = "conf" + File.separator + name;
            result = checkConfigFile(new File(homePath, nameInConf));
            if (result == null) {
               URI jarUri = null;

               try {
                  URL sourceUrl = HiveConf.class.getProtectionDomain().getCodeSource().getLocation();
                  jarUri = sourceUrl.getProtocol().equalsIgnoreCase("jar") ? new URI(sourceUrl.getPath()) : sourceUrl.toURI();
               } catch (Throwable var9) {
                  if (l4j.isInfoEnabled()) {
                     l4j.info("Cannot get jar URI", var9);
                  }

                  System.err.println("Cannot get jar URI: " + var9.getMessage());
               }

               File parent = (new File(jarUri)).getParentFile();
               if (parent != null) {
                  result = checkConfigFile(new File(parent.getParentFile(), nameInConf));
               }
            }
         }
      }

      if (doLog && l4j.isInfoEnabled()) {
         l4j.info("Found configuration file " + result);
      }

      return result;
   }

   private static URL checkConfigFile(File f) {
      try {
         return f.exists() && f.isFile() ? f.toURI().toURL() : null;
      } catch (Throwable var2) {
         if (l4j.isInfoEnabled()) {
            l4j.info("Error looking for config " + f, var2);
         }

         System.err.println("Error looking for config " + f + ": " + var2.getMessage());
         return null;
      }
   }

   private static void populateLlapDaemonVarsSet(Set llapDaemonVarsSetLocal) {
      llapDaemonVarsSetLocal.add(HiveConf.ConfVars.LLAP_IO_ENABLED.varname);
      llapDaemonVarsSetLocal.add(HiveConf.ConfVars.LLAP_IO_MEMORY_MODE.varname);
      llapDaemonVarsSetLocal.add(HiveConf.ConfVars.LLAP_ALLOCATOR_MIN_ALLOC.varname);
      llapDaemonVarsSetLocal.add(HiveConf.ConfVars.LLAP_ALLOCATOR_MAX_ALLOC.varname);
      llapDaemonVarsSetLocal.add(HiveConf.ConfVars.LLAP_ALLOCATOR_ARENA_COUNT.varname);
      llapDaemonVarsSetLocal.add(HiveConf.ConfVars.LLAP_IO_MEMORY_MAX_SIZE.varname);
      llapDaemonVarsSetLocal.add(HiveConf.ConfVars.LLAP_ALLOCATOR_DIRECT.varname);
      llapDaemonVarsSetLocal.add(HiveConf.ConfVars.LLAP_USE_LRFU.varname);
      llapDaemonVarsSetLocal.add(HiveConf.ConfVars.LLAP_LRFU_LAMBDA.varname);
      llapDaemonVarsSetLocal.add(HiveConf.ConfVars.LLAP_CACHE_ALLOW_SYNTHETIC_FILEID.varname);
      llapDaemonVarsSetLocal.add(HiveConf.ConfVars.LLAP_IO_USE_FILEID_PATH.varname);
      llapDaemonVarsSetLocal.add(HiveConf.ConfVars.LLAP_IO_DECODING_METRICS_PERCENTILE_INTERVALS.varname);
      llapDaemonVarsSetLocal.add(HiveConf.ConfVars.LLAP_ORC_ENABLE_TIME_COUNTERS.varname);
      llapDaemonVarsSetLocal.add(HiveConf.ConfVars.LLAP_IO_THREADPOOL_SIZE.varname);
      llapDaemonVarsSetLocal.add(HiveConf.ConfVars.LLAP_KERBEROS_PRINCIPAL.varname);
      llapDaemonVarsSetLocal.add(HiveConf.ConfVars.LLAP_KERBEROS_KEYTAB_FILE.varname);
      llapDaemonVarsSetLocal.add(HiveConf.ConfVars.LLAP_ZKSM_KERBEROS_PRINCIPAL.varname);
      llapDaemonVarsSetLocal.add(HiveConf.ConfVars.LLAP_ZKSM_KERBEROS_KEYTAB_FILE.varname);
      llapDaemonVarsSetLocal.add(HiveConf.ConfVars.LLAP_ZKSM_ZK_CONNECTION_STRING.varname);
      llapDaemonVarsSetLocal.add(HiveConf.ConfVars.LLAP_SECURITY_ACL.varname);
      llapDaemonVarsSetLocal.add(HiveConf.ConfVars.LLAP_MANAGEMENT_ACL.varname);
      llapDaemonVarsSetLocal.add(HiveConf.ConfVars.LLAP_SECURITY_ACL_DENY.varname);
      llapDaemonVarsSetLocal.add(HiveConf.ConfVars.LLAP_MANAGEMENT_ACL_DENY.varname);
      llapDaemonVarsSetLocal.add(HiveConf.ConfVars.LLAP_DELEGATION_TOKEN_LIFETIME.varname);
      llapDaemonVarsSetLocal.add(HiveConf.ConfVars.LLAP_MANAGEMENT_RPC_PORT.varname);
      llapDaemonVarsSetLocal.add(HiveConf.ConfVars.LLAP_WEB_AUTO_AUTH.varname);
      llapDaemonVarsSetLocal.add(HiveConf.ConfVars.LLAP_DAEMON_RPC_NUM_HANDLERS.varname);
      llapDaemonVarsSetLocal.add(HiveConf.ConfVars.LLAP_DAEMON_WORK_DIRS.varname);
      llapDaemonVarsSetLocal.add(HiveConf.ConfVars.LLAP_DAEMON_YARN_SHUFFLE_PORT.varname);
      llapDaemonVarsSetLocal.add(HiveConf.ConfVars.LLAP_DAEMON_YARN_CONTAINER_MB.varname);
      llapDaemonVarsSetLocal.add(HiveConf.ConfVars.LLAP_DAEMON_SHUFFLE_DIR_WATCHER_ENABLED.varname);
      llapDaemonVarsSetLocal.add(HiveConf.ConfVars.LLAP_DAEMON_AM_LIVENESS_HEARTBEAT_INTERVAL_MS.varname);
      llapDaemonVarsSetLocal.add(HiveConf.ConfVars.LLAP_DAEMON_AM_LIVENESS_CONNECTION_TIMEOUT_MS.varname);
      llapDaemonVarsSetLocal.add(HiveConf.ConfVars.LLAP_DAEMON_AM_LIVENESS_CONNECTION_SLEEP_BETWEEN_RETRIES_MS.varname);
      llapDaemonVarsSetLocal.add(HiveConf.ConfVars.LLAP_DAEMON_NUM_EXECUTORS.varname);
      llapDaemonVarsSetLocal.add(HiveConf.ConfVars.LLAP_DAEMON_RPC_PORT.varname);
      llapDaemonVarsSetLocal.add(HiveConf.ConfVars.LLAP_DAEMON_MEMORY_PER_INSTANCE_MB.varname);
      llapDaemonVarsSetLocal.add(HiveConf.ConfVars.LLAP_DAEMON_XMX_HEADROOM.varname);
      llapDaemonVarsSetLocal.add(HiveConf.ConfVars.LLAP_DAEMON_VCPUS_PER_INSTANCE.varname);
      llapDaemonVarsSetLocal.add(HiveConf.ConfVars.LLAP_DAEMON_NUM_FILE_CLEANER_THREADS.varname);
      llapDaemonVarsSetLocal.add(HiveConf.ConfVars.LLAP_FILE_CLEANUP_DELAY_SECONDS.varname);
      llapDaemonVarsSetLocal.add(HiveConf.ConfVars.LLAP_DAEMON_SERVICE_HOSTS.varname);
      llapDaemonVarsSetLocal.add(HiveConf.ConfVars.LLAP_DAEMON_SERVICE_REFRESH_INTERVAL.varname);
      llapDaemonVarsSetLocal.add(HiveConf.ConfVars.LLAP_ALLOW_PERMANENT_FNS.varname);
      llapDaemonVarsSetLocal.add(HiveConf.ConfVars.LLAP_DAEMON_DOWNLOAD_PERMANENT_FNS.varname);
      llapDaemonVarsSetLocal.add(HiveConf.ConfVars.LLAP_DAEMON_TASK_SCHEDULER_WAIT_QUEUE_SIZE.varname);
      llapDaemonVarsSetLocal.add(HiveConf.ConfVars.LLAP_DAEMON_WAIT_QUEUE_COMPARATOR_CLASS_NAME.varname);
      llapDaemonVarsSetLocal.add(HiveConf.ConfVars.LLAP_DAEMON_TASK_SCHEDULER_ENABLE_PREEMPTION.varname);
      llapDaemonVarsSetLocal.add(HiveConf.ConfVars.LLAP_DAEMON_TASK_PREEMPTION_METRICS_INTERVALS.varname);
      llapDaemonVarsSetLocal.add(HiveConf.ConfVars.LLAP_DAEMON_WEB_PORT.varname);
      llapDaemonVarsSetLocal.add(HiveConf.ConfVars.LLAP_DAEMON_WEB_SSL.varname);
      llapDaemonVarsSetLocal.add(HiveConf.ConfVars.LLAP_DAEMON_CONTAINER_ID.varname);
      llapDaemonVarsSetLocal.add(HiveConf.ConfVars.LLAP_VALIDATE_ACLS.varname);
      llapDaemonVarsSetLocal.add(HiveConf.ConfVars.LLAP_DAEMON_LOGGER.varname);
      llapDaemonVarsSetLocal.add(HiveConf.ConfVars.LLAP_DAEMON_AM_USE_FQDN.varname);
   }

   public static final Set getLlapDaemonConfVars() {
      return llapDaemonVarsSet;
   }

   private static synchronized InputStream getConfVarInputStream() {
      if (confVarByteArray == null) {
         try {
            Configuration conf = new Configuration(false);
            applyDefaultNonNullConfVars(conf);
            ByteArrayOutputStream confVarBaos = new ByteArrayOutputStream();
            conf.writeXml(confVarBaos);
            confVarByteArray = confVarBaos.toByteArray();
         } catch (Exception e) {
            throw new RuntimeException("Failed to initialize default Hive configuration variables!", e);
         }
      }

      return new LoopingByteArrayInputStream(confVarByteArray);
   }

   public void verifyAndSet(String name, String value) throws IllegalArgumentException {
      if (this.modWhiteListPattern != null) {
         Matcher wlMatcher = this.modWhiteListPattern.matcher(name);
         if (!wlMatcher.matches()) {
            throw new IllegalArgumentException("Cannot modify " + name + " at runtime. It is not in list of params that are allowed to be modified at runtime");
         }
      }

      if (this.restrictList.contains(name)) {
         throw new IllegalArgumentException("Cannot modify " + name + " at runtime. It is in the list of parameters that can't be modified at runtime");
      } else {
         String oldValue = name != null ? this.get(name) : null;
         if (name == null || value == null || !value.equals(oldValue)) {
            this.set(name, value);
            if (this.isSparkRelatedConfig(name)) {
               this.isSparkConfigUpdated = true;
            }
         }

      }
   }

   public boolean isHiddenConfig(String name) {
      return this.hiddenSet.contains(name);
   }

   private boolean isSparkRelatedConfig(String name) {
      boolean result = false;
      if (name.startsWith("spark")) {
         result = !name.equals("spark.app.name");
      } else if (name.startsWith("yarn")) {
         String sparkMaster = this.get("spark.master");
         if (sparkMaster != null && sparkMaster.startsWith("yarn")) {
            result = true;
         }
      } else if (name.startsWith("hive.spark")) {
         result = true;
      }

      return result;
   }

   public static int getIntVar(Configuration conf, ConfVars var) {
      assert var.valClass == Integer.class : var.varname;

      return var.altName != null ? conf.getInt(var.varname, conf.getInt(var.altName, var.defaultIntVal)) : conf.getInt(var.varname, var.defaultIntVal);
   }

   public static void setIntVar(Configuration conf, ConfVars var, int val) {
      assert var.valClass == Integer.class : var.varname;

      conf.setInt(var.varname, val);
   }

   public int getIntVar(ConfVars var) {
      return getIntVar(this, var);
   }

   public void setIntVar(ConfVars var, int val) {
      setIntVar(this, var, val);
   }

   public static long getTimeVar(Configuration conf, ConfVars var, TimeUnit outUnit) {
      return toTime(getVar(conf, var), getDefaultTimeUnit(var), outUnit);
   }

   public static void setTimeVar(Configuration conf, ConfVars var, long time, TimeUnit timeunit) {
      assert var.valClass == String.class : var.varname;

      conf.set(var.varname, time + stringFor(timeunit));
   }

   public long getTimeVar(ConfVars var, TimeUnit outUnit) {
      return getTimeVar(this, var, outUnit);
   }

   public void setTimeVar(ConfVars var, long time, TimeUnit outUnit) {
      setTimeVar(this, var, time, outUnit);
   }

   public static long getSizeVar(Configuration conf, ConfVars var) {
      return toSizeBytes(getVar(conf, var));
   }

   public long getSizeVar(ConfVars var) {
      return getSizeVar(this, var);
   }

   private static TimeUnit getDefaultTimeUnit(ConfVars var) {
      TimeUnit inputUnit = null;
      if (var.validator instanceof Validator.TimeValidator) {
         inputUnit = ((Validator.TimeValidator)var.validator).getTimeUnit();
      }

      return inputUnit;
   }

   public static long toTime(String value, TimeUnit inputUnit, TimeUnit outUnit) {
      String[] parsed = parseNumberFollowedByUnit(value.trim());
      return outUnit.convert(Long.parseLong(parsed[0].trim()), unitFor(parsed[1].trim(), inputUnit));
   }

   public static long toSizeBytes(String value) {
      String[] parsed = parseNumberFollowedByUnit(value.trim());
      return Long.parseLong(parsed[0].trim()) * multiplierFor(parsed[1].trim());
   }

   private static String[] parseNumberFollowedByUnit(String value) {
      char[] chars = value.toCharArray();

      int i;
      for(i = 0; i < chars.length && (chars[i] == '-' || Character.isDigit(chars[i])); ++i) {
      }

      return new String[]{value.substring(0, i), value.substring(i)};
   }

   public static TimeUnit unitFor(String unit, TimeUnit defaultUnit) {
      unit = unit.trim().toLowerCase();
      if (!unit.isEmpty() && !unit.equals("l")) {
         if (!unit.equals("d") && !unit.startsWith("day")) {
            if (!unit.equals("h") && !unit.startsWith("hour")) {
               if (!unit.equals("m") && !unit.startsWith("min")) {
                  if (!unit.equals("s") && !unit.startsWith("sec")) {
                     if (!unit.equals("ms") && !unit.startsWith("msec")) {
                        if (!unit.equals("us") && !unit.startsWith("usec")) {
                           if (!unit.equals("ns") && !unit.startsWith("nsec")) {
                              throw new IllegalArgumentException("Invalid time unit " + unit);
                           } else {
                              return TimeUnit.NANOSECONDS;
                           }
                        } else {
                           return TimeUnit.MICROSECONDS;
                        }
                     } else {
                        return TimeUnit.MILLISECONDS;
                     }
                  } else {
                     return TimeUnit.SECONDS;
                  }
               } else {
                  return TimeUnit.MINUTES;
               }
            } else {
               return TimeUnit.HOURS;
            }
         } else {
            return TimeUnit.DAYS;
         }
      } else if (defaultUnit == null) {
         throw new IllegalArgumentException("Time unit is not specified");
      } else {
         return defaultUnit;
      }
   }

   public static long multiplierFor(String unit) {
      unit = unit.trim().toLowerCase();
      if (!unit.isEmpty() && !unit.equals("b") && !unit.equals("bytes")) {
         if (unit.equals("kb")) {
            return 1024L;
         } else if (unit.equals("mb")) {
            return 1048576L;
         } else if (unit.equals("gb")) {
            return 1073741824L;
         } else if (unit.equals("tb")) {
            return 0L;
         } else if (unit.equals("pb")) {
            return 0L;
         } else {
            throw new IllegalArgumentException("Invalid size unit " + unit);
         }
      } else {
         return 1L;
      }
   }

   public static String stringFor(TimeUnit timeunit) {
      switch (timeunit) {
         case DAYS:
            return "day";
         case HOURS:
            return "hour";
         case MINUTES:
            return "min";
         case SECONDS:
            return "sec";
         case MILLISECONDS:
            return "msec";
         case MICROSECONDS:
            return "usec";
         case NANOSECONDS:
            return "nsec";
         default:
            throw new IllegalArgumentException("Invalid timeunit " + timeunit);
      }
   }

   public static long getLongVar(Configuration conf, ConfVars var) {
      assert var.valClass == Long.class : var.varname;

      return var.altName != null ? conf.getLong(var.varname, conf.getLong(var.altName, var.defaultLongVal)) : conf.getLong(var.varname, var.defaultLongVal);
   }

   public static long getLongVar(Configuration conf, ConfVars var, long defaultVal) {
      return var.altName != null ? conf.getLong(var.varname, conf.getLong(var.altName, defaultVal)) : conf.getLong(var.varname, defaultVal);
   }

   public static void setLongVar(Configuration conf, ConfVars var, long val) {
      assert var.valClass == Long.class : var.varname;

      conf.setLong(var.varname, val);
   }

   public long getLongVar(ConfVars var) {
      return getLongVar(this, var);
   }

   public void setLongVar(ConfVars var, long val) {
      setLongVar(this, var, val);
   }

   public static float getFloatVar(Configuration conf, ConfVars var) {
      assert var.valClass == Float.class : var.varname;

      return var.altName != null ? conf.getFloat(var.varname, conf.getFloat(var.altName, var.defaultFloatVal)) : conf.getFloat(var.varname, var.defaultFloatVal);
   }

   public static float getFloatVar(Configuration conf, ConfVars var, float defaultVal) {
      return var.altName != null ? conf.getFloat(var.varname, conf.getFloat(var.altName, defaultVal)) : conf.getFloat(var.varname, defaultVal);
   }

   public static void setFloatVar(Configuration conf, ConfVars var, float val) {
      assert var.valClass == Float.class : var.varname;

      conf.setFloat(var.varname, val);
   }

   public float getFloatVar(ConfVars var) {
      return getFloatVar(this, var);
   }

   public void setFloatVar(ConfVars var, float val) {
      setFloatVar(this, var, val);
   }

   public static boolean getBoolVar(Configuration conf, ConfVars var) {
      assert var.valClass == Boolean.class : var.varname;

      return var.altName != null ? conf.getBoolean(var.varname, conf.getBoolean(var.altName, var.defaultBoolVal)) : conf.getBoolean(var.varname, var.defaultBoolVal);
   }

   public static boolean getBoolVar(Configuration conf, ConfVars var, boolean defaultVal) {
      return var.altName != null ? conf.getBoolean(var.varname, conf.getBoolean(var.altName, defaultVal)) : conf.getBoolean(var.varname, defaultVal);
   }

   public static void setBoolVar(Configuration conf, ConfVars var, boolean val) {
      assert var.valClass == Boolean.class : var.varname;

      conf.setBoolean(var.varname, val);
   }

   public boolean getBoolVar(ConfVars var) {
      return getBoolVar(this, var);
   }

   public void setBoolVar(ConfVars var, boolean val) {
      setBoolVar(this, var, val);
   }

   public static String getVar(Configuration conf, ConfVars var) {
      assert var.valClass == String.class : var.varname;

      return var.altName != null ? conf.get(var.varname, conf.get(var.altName, var.defaultStrVal)) : conf.get(var.varname, var.defaultStrVal);
   }

   public static String getVarWithoutType(Configuration conf, ConfVars var) {
      return var.altName != null ? conf.get(var.varname, conf.get(var.altName, var.defaultExpr)) : conf.get(var.varname, var.defaultExpr);
   }

   public static String getTrimmedVar(Configuration conf, ConfVars var) {
      assert var.valClass == String.class : var.varname;

      return var.altName != null ? conf.getTrimmed(var.varname, conf.getTrimmed(var.altName, var.defaultStrVal)) : conf.getTrimmed(var.varname, var.defaultStrVal);
   }

   public static String[] getTrimmedStringsVar(Configuration conf, ConfVars var) {
      assert var.valClass == String.class : var.varname;

      String[] result = conf.getTrimmedStrings(var.varname, (String[])null);
      if (result != null) {
         return result;
      } else {
         if (var.altName != null) {
            result = conf.getTrimmedStrings(var.altName, (String[])null);
            if (result != null) {
               return result;
            }
         }

         return StringUtils.getTrimmedStrings(var.defaultStrVal);
      }
   }

   public static String getVar(Configuration conf, ConfVars var, String defaultVal) {
      String ret = var.altName != null ? conf.get(var.varname, conf.get(var.altName, defaultVal)) : conf.get(var.varname, defaultVal);
      return ret;
   }

   public static String getVar(Configuration conf, ConfVars var, EncoderDecoder encoderDecoder) {
      return (String)encoderDecoder.decode(getVar(conf, var));
   }

   public String getLogIdVar(String defaultValue) {
      String retval = this.getVar(HiveConf.ConfVars.HIVE_LOG_TRACE_ID);
      if (retval.equals("")) {
         l4j.info("Using the default value passed in for log id: " + defaultValue);
         retval = defaultValue;
      }

      if (retval.length() > 64) {
         l4j.warn("The original log id prefix is " + retval + " has been truncated to " + retval.substring(0, 63));
         retval = retval.substring(0, 63);
      }

      return retval;
   }

   public static void setVar(Configuration conf, ConfVars var, String val) {
      assert var.valClass == String.class : var.varname;

      conf.set(var.varname, val);
   }

   public static void setVar(Configuration conf, ConfVars var, String val, EncoderDecoder encoderDecoder) {
      setVar(conf, var, (String)encoderDecoder.encode(val));
   }

   public static ConfVars getConfVars(String name) {
      return (ConfVars)vars.get(name);
   }

   public static ConfVars getMetaConf(String name) {
      return (ConfVars)metaConfs.get(name);
   }

   public String getVar(ConfVars var) {
      return getVar(this, var);
   }

   public void setVar(ConfVars var, String val) {
      setVar(this, var, val);
   }

   public String getQueryString() {
      return getQueryString(this);
   }

   public static String getQueryString(Configuration conf) {
      return getVar(conf, HiveConf.ConfVars.HIVEQUERYSTRING, (EncoderDecoder)HiveConf.EncoderDecoderFactory.URL_ENCODER_DECODER);
   }

   public void setQueryString(String query) {
      setQueryString(this, query);
   }

   public static void setQueryString(Configuration conf, String query) {
      setVar(conf, HiveConf.ConfVars.HIVEQUERYSTRING, query, HiveConf.EncoderDecoderFactory.URL_ENCODER_DECODER);
   }

   public void logVars(PrintStream ps) {
      for(ConfVars one : HiveConf.ConfVars.values()) {
         ps.println(one.varname + "=" + (this.get(one.varname) != null ? this.get(one.varname) : ""));
      }

   }

   public HiveConf() {
      this.initialize(this.getClass());
   }

   public HiveConf(Class cls) {
      this.initialize(cls);
   }

   public HiveConf(Configuration other, Class cls) {
      super(other);
      this.initialize(cls);
   }

   public HiveConf(HiveConf other) {
      super(other);
      this.hiveJar = other.hiveJar;
      this.auxJars = other.auxJars;
      this.isSparkConfigUpdated = other.isSparkConfigUpdated;
      this.origProp = (Properties)other.origProp.clone();
      this.restrictList.addAll(other.restrictList);
      this.hiddenSet.addAll(other.hiddenSet);
      this.modWhiteListPattern = other.modWhiteListPattern;
   }

   public Properties getAllProperties() {
      return getProperties(this);
   }

   public static Properties getProperties(Configuration conf) {
      Iterator<Map.Entry<String, String>> iter = conf.iterator();
      Properties p = new Properties();

      while(iter.hasNext()) {
         Map.Entry<String, String> e = (Map.Entry)iter.next();
         p.setProperty((String)e.getKey(), (String)e.getValue());
      }

      return p;
   }

   private void initialize(Class cls) {
      this.hiveJar = (new JobConf(cls)).getJar();
      this.origProp = this.getAllProperties();
      this.addResource(getConfVarInputStream());
      if (hiveSiteURL != null) {
         this.addResource(hiveSiteURL);
      }

      String msUri = this.getVar(HiveConf.ConfVars.METASTOREURIS);
      if (HiveConfUtil.isEmbeddedMetaStore(msUri)) {
         setLoadMetastoreConfig(true);
      }

      if (isLoadMetastoreConfig() && hivemetastoreSiteUrl != null) {
         this.addResource(hivemetastoreSiteUrl);
      }

      if (isLoadHiveServer2Config() && hiveServer2SiteUrl != null) {
         this.addResource(hiveServer2SiteUrl);
      }

      this.applySystemProperties();
      if (this.get("hive.metastore.ds.retry.attempts") != null || this.get("hive.metastore.ds.retry.interval") != null) {
         l4j.warn("DEPRECATED: hive.metastore.ds.retry.* no longer has any effect.  Use hive.hmshandler.retry.* instead");
      }

      if (this.hiveJar == null) {
         this.hiveJar = this.get(HiveConf.ConfVars.HIVEJAR.varname);
      }

      if (this.auxJars == null) {
         this.auxJars = org.apache.commons.lang3.StringUtils.join(FileUtils.getJarFilesByPath(this.get(HiveConf.ConfVars.HIVEAUXJARS.varname), this), ',');
      }

      if (this.getBoolVar(HiveConf.ConfVars.METASTORE_SCHEMA_VERIFICATION)) {
         this.setBoolVar(HiveConf.ConfVars.METASTORE_AUTO_CREATE_ALL, false);
      }

      if (this.getBoolVar(HiveConf.ConfVars.HIVECONFVALIDATION)) {
         List<String> trimmed = new ArrayList();

         for(Map.Entry entry : this) {
            String key = (String)entry.getKey();
            if (key != null && key.startsWith("hive.")) {
               ConfVars var = getConfVars(key);
               if (var == null) {
                  var = getConfVars(key.trim());
                  if (var != null) {
                     trimmed.add(key);
                  }
               }

               if (var == null) {
                  l4j.warn("HiveConf of name " + key + " does not exist");
               } else if (!var.isType((String)entry.getValue())) {
                  l4j.warn("HiveConf " + var.varname + " expects " + var.typeString() + " type value");
               }
            }
         }

         for(String key : trimmed) {
            this.set(key.trim(), this.getRaw(key));
            this.unset(key);
         }
      }

      this.setupSQLStdAuthWhiteList();
      this.setupRestrictList();
      this.hiddenSet.clear();
      this.hiddenSet.addAll(HiveConfUtil.getHiddenSet(this));
   }

   private void setupSQLStdAuthWhiteList() {
      String whiteListParamsStr = this.getVar(HiveConf.ConfVars.HIVE_AUTHORIZATION_SQL_STD_AUTH_CONFIG_WHITELIST);
      if (whiteListParamsStr == null || whiteListParamsStr.trim().isEmpty()) {
         whiteListParamsStr = getSQLStdAuthDefaultWhiteListPattern();
      }

      this.setVar(HiveConf.ConfVars.HIVE_AUTHORIZATION_SQL_STD_AUTH_CONFIG_WHITELIST, whiteListParamsStr);
   }

   private static String getSQLStdAuthDefaultWhiteListPattern() {
      String confVarPatternStr = Joiner.on("|").join(convertVarsToRegex(sqlStdAuthSafeVarNames));
      String regexPatternStr = Joiner.on("|").join(sqlStdAuthSafeVarNameRegexes);
      return regexPatternStr + "|" + confVarPatternStr;
   }

   private static String[] convertVarsToRegex(String[] paramList) {
      String[] regexes = new String[paramList.length];

      for(int i = 0; i < paramList.length; ++i) {
         regexes[i] = paramList[i].replace(".", "\\.");
      }

      return regexes;
   }

   private void applySystemProperties() {
      Map<String, String> systemProperties = getConfSystemProperties();

      for(Map.Entry systemProperty : systemProperties.entrySet()) {
         this.set((String)systemProperty.getKey(), (String)systemProperty.getValue());
      }

   }

   public static Map getConfSystemProperties() {
      Map<String, String> systemProperties = new HashMap();

      for(ConfVars oneVar : HiveConf.ConfVars.values()) {
         if (System.getProperty(oneVar.varname) != null && System.getProperty(oneVar.varname).length() > 0) {
            systemProperties.put(oneVar.varname, System.getProperty(oneVar.varname));
         }
      }

      return systemProperties;
   }

   private static void applyDefaultNonNullConfVars(Configuration conf) {
      for(ConfVars var : HiveConf.ConfVars.values()) {
         String defaultValue = var.getDefaultValue();
         if (defaultValue != null) {
            conf.set(var.varname, defaultValue);
         }
      }

   }

   public Properties getChangedProperties() {
      Properties ret = new Properties();
      Properties newProp = this.getAllProperties();

      for(Object one : newProp.keySet()) {
         String oneProp = (String)one;
         String oldValue = this.origProp.getProperty(oneProp);
         if (!org.apache.commons.lang3.StringUtils.equals(oldValue, newProp.getProperty(oneProp))) {
            ret.setProperty(oneProp, newProp.getProperty(oneProp));
         }
      }

      return ret;
   }

   public String getJar() {
      return this.hiveJar;
   }

   public String getAuxJars() {
      return this.auxJars;
   }

   public void setAuxJars(String auxJars) {
      this.auxJars = auxJars;
      setVar(this, HiveConf.ConfVars.HIVEAUXJARS, auxJars);
   }

   public URL getHiveDefaultLocation() {
      return hiveDefaultURL;
   }

   public static void setHiveSiteLocation(URL location) {
      hiveSiteURL = location;
   }

   public static URL getHiveSiteLocation() {
      return hiveSiteURL;
   }

   public static URL getMetastoreSiteLocation() {
      return hivemetastoreSiteUrl;
   }

   public static URL getHiveServer2SiteLocation() {
      return hiveServer2SiteUrl;
   }

   public String getUser() throws IOException {
      try {
         UserGroupInformation ugi = Utils.getUGI();
         return ugi.getUserName();
      } catch (LoginException le) {
         throw new IOException(le);
      }
   }

   public static String getColumnInternalName(int pos) {
      return "_col" + pos;
   }

   public static int getPositionFromInternalName(String internalName) {
      Pattern internalPattern = Pattern.compile("_col([0-9]+)");
      Matcher m = internalPattern.matcher(internalName);
      return !m.matches() ? -1 : Integer.parseInt(m.group(1));
   }

   public void addToRestrictList(String restrictListStr) {
      if (restrictListStr != null) {
         String oldList = this.getVar(HiveConf.ConfVars.HIVE_CONF_RESTRICTED_LIST);
         if (oldList != null && !oldList.isEmpty()) {
            this.setVar(HiveConf.ConfVars.HIVE_CONF_RESTRICTED_LIST, oldList + "," + restrictListStr);
         } else {
            this.setVar(HiveConf.ConfVars.HIVE_CONF_RESTRICTED_LIST, restrictListStr);
         }

         this.setupRestrictList();
      }
   }

   @InterfaceAudience.LimitedPrivate({"Currently only for use by HiveAuthorizer"})
   public void setModifiableWhiteListRegex(String paramNameRegex) {
      if (paramNameRegex != null) {
         this.modWhiteListPattern = Pattern.compile(paramNameRegex);
      }
   }

   private void setupRestrictList() {
      String restrictListStr = this.getVar(HiveConf.ConfVars.HIVE_CONF_RESTRICTED_LIST);
      this.restrictList.clear();
      if (restrictListStr != null) {
         for(String entry : restrictListStr.split(",")) {
            this.restrictList.add(entry.trim());
         }
      }

      String internalVariableListStr = this.getVar(HiveConf.ConfVars.HIVE_CONF_INTERNAL_VARIABLE_LIST);
      if (internalVariableListStr != null) {
         for(String entry : internalVariableListStr.split(",")) {
            this.restrictList.add(entry.trim());
         }
      }

      this.restrictList.add(HiveConf.ConfVars.HIVE_IN_TEST.varname);
      this.restrictList.add(HiveConf.ConfVars.HIVE_CONF_RESTRICTED_LIST.varname);
      this.restrictList.add(HiveConf.ConfVars.HIVE_CONF_HIDDEN_LIST.varname);
      this.restrictList.add(HiveConf.ConfVars.HIVE_CONF_INTERNAL_VARIABLE_LIST.varname);
   }

   public void stripHiddenConfigurations(Configuration conf) {
      HiveConfUtil.stripConfigurations(conf, this.hiddenSet);
   }

   public boolean isWebUiEnabled() {
      return this.getIntVar(HiveConf.ConfVars.HIVE_SERVER2_WEBUI_PORT) != 0;
   }

   public boolean isWebUiQueryInfoCacheEnabled() {
      return this.isWebUiEnabled() && this.getIntVar(HiveConf.ConfVars.HIVE_SERVER2_WEBUI_MAX_HISTORIC_QUERIES) > 0;
   }

   public static boolean isLoadMetastoreConfig() {
      return loadMetastoreConfig;
   }

   public static void setLoadMetastoreConfig(boolean loadMetastoreConfig) {
      HiveConf.loadMetastoreConfig = loadMetastoreConfig;
   }

   public static boolean isLoadHiveServer2Config() {
      return loadHiveServer2Config;
   }

   public static void setLoadHiveServer2Config(boolean loadHiveServer2Config) {
      HiveConf.loadHiveServer2Config = loadHiveServer2Config;
   }

   public static String getNonMrEngines() {
      String result = "";

      for(String s : HiveConf.ConfVars.HIVE_EXECUTION_ENGINE.getValidStringValues()) {
         if (!"mr".equals(s)) {
            if (!result.isEmpty()) {
               result = result + ", ";
            }

            result = result + s;
         }
      }

      return result;
   }

   public static String generateMrDeprecationWarning() {
      return "Hive-on-MR is deprecated in Hive 2 and may not be available in the future versions. Consider using a different execution engine (i.e. " + getNonMrEngines() + ") or using Hive 1.X releases.";
   }

   public static HashMap getOrCreateReverseMap() {
      synchronized(reverseMapLock) {
         if (reverseMap != null) {
            return reverseMap;
         }
      }

      HashMap<String, ConfVars> vars = new HashMap();

      for(ConfVars val : HiveConf.ConfVars.values()) {
         vars.put(val.varname.toLowerCase(), val);
         if (val.altName != null && !val.altName.isEmpty()) {
            vars.put(val.altName.toLowerCase(), val);
         }
      }

      synchronized(reverseMapLock) {
         if (reverseMap != null) {
            return reverseMap;
         } else {
            reverseMap = vars;
            return reverseMap;
         }
      }
   }

   static {
      ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
      if (classLoader == null) {
         classLoader = HiveConf.class.getClassLoader();
      }

      hiveDefaultURL = classLoader.getResource("hive-default.xml");
      hiveSiteURL = findConfigFile(classLoader, "hive-site.xml", true);
      hivemetastoreSiteUrl = findConfigFile(classLoader, "hivemetastore-site.xml", false);
      hiveServer2SiteUrl = findConfigFile(classLoader, "hiveserver2-site.xml", false);

      for(ConfVars confVar : HiveConf.ConfVars.values()) {
         vars.put(confVar.varname, confVar);
      }

      Set<String> llapDaemonConfVarsSetLocal = new LinkedHashSet();
      populateLlapDaemonVarsSet(llapDaemonConfVarsSetLocal);
      llapDaemonVarsSet = Collections.unmodifiableSet(llapDaemonConfVarsSetLocal);
      metaVars = new ConfVars[]{HiveConf.ConfVars.METASTOREWAREHOUSE, HiveConf.ConfVars.REPLDIR, HiveConf.ConfVars.METASTOREURIS, HiveConf.ConfVars.METASTORE_SERVER_PORT, HiveConf.ConfVars.METASTORETHRIFTCONNECTIONRETRIES, HiveConf.ConfVars.METASTORETHRIFTFAILURERETRIES, HiveConf.ConfVars.METASTORE_CLIENT_CONNECT_RETRY_DELAY, HiveConf.ConfVars.METASTORE_CLIENT_SOCKET_TIMEOUT, HiveConf.ConfVars.METASTORE_CLIENT_SOCKET_LIFETIME, HiveConf.ConfVars.METASTOREPWD, HiveConf.ConfVars.METASTORECONNECTURLHOOK, HiveConf.ConfVars.METASTORECONNECTURLKEY, HiveConf.ConfVars.METASTORESERVERMINTHREADS, HiveConf.ConfVars.METASTORESERVERMAXTHREADS, HiveConf.ConfVars.METASTORE_TCP_KEEP_ALIVE, HiveConf.ConfVars.METASTORE_INT_ORIGINAL, HiveConf.ConfVars.METASTORE_INT_ARCHIVED, HiveConf.ConfVars.METASTORE_INT_EXTRACTED, HiveConf.ConfVars.METASTORE_KERBEROS_KEYTAB_FILE, HiveConf.ConfVars.METASTORE_KERBEROS_PRINCIPAL, HiveConf.ConfVars.METASTORE_USE_THRIFT_SASL, HiveConf.ConfVars.METASTORE_TOKEN_SIGNATURE, HiveConf.ConfVars.METASTORE_CACHE_PINOBJTYPES, HiveConf.ConfVars.METASTORE_CONNECTION_POOLING_TYPE, HiveConf.ConfVars.METASTORE_VALIDATE_TABLES, HiveConf.ConfVars.METASTORE_DATANUCLEUS_INIT_COL_INFO, HiveConf.ConfVars.METASTORE_VALIDATE_COLUMNS, HiveConf.ConfVars.METASTORE_VALIDATE_CONSTRAINTS, HiveConf.ConfVars.METASTORE_STORE_MANAGER_TYPE, HiveConf.ConfVars.METASTORE_AUTO_CREATE_ALL, HiveConf.ConfVars.METASTORE_TRANSACTION_ISOLATION, HiveConf.ConfVars.METASTORE_CACHE_LEVEL2, HiveConf.ConfVars.METASTORE_CACHE_LEVEL2_TYPE, HiveConf.ConfVars.METASTORE_IDENTIFIER_FACTORY, HiveConf.ConfVars.METASTORE_PLUGIN_REGISTRY_BUNDLE_CHECK, HiveConf.ConfVars.METASTORE_AUTHORIZATION_STORAGE_AUTH_CHECKS, HiveConf.ConfVars.METASTORE_BATCH_RETRIEVE_MAX, HiveConf.ConfVars.METASTORE_EVENT_LISTENERS, HiveConf.ConfVars.METASTORE_TRANSACTIONAL_EVENT_LISTENERS, HiveConf.ConfVars.METASTORE_EVENT_CLEAN_FREQ, HiveConf.ConfVars.METASTORE_EVENT_EXPIRY_DURATION, HiveConf.ConfVars.METASTORE_EVENT_MESSAGE_FACTORY, HiveConf.ConfVars.METASTORE_FILTER_HOOK, HiveConf.ConfVars.METASTORE_RAW_STORE_IMPL, HiveConf.ConfVars.METASTORE_END_FUNCTION_LISTENERS, HiveConf.ConfVars.METASTORE_PART_INHERIT_TBL_PROPS, HiveConf.ConfVars.METASTORE_BATCH_RETRIEVE_OBJECTS_MAX, HiveConf.ConfVars.METASTORE_INIT_HOOKS, HiveConf.ConfVars.METASTORE_PRE_EVENT_LISTENERS, HiveConf.ConfVars.HMSHANDLERATTEMPTS, HiveConf.ConfVars.HMSHANDLERINTERVAL, HiveConf.ConfVars.HMSHANDLERFORCERELOADCONF, HiveConf.ConfVars.METASTORE_PARTITION_NAME_WHITELIST_PATTERN, HiveConf.ConfVars.METASTORE_ORM_RETRIEVE_MAPNULLS_AS_EMPTY_STRINGS, HiveConf.ConfVars.METASTORE_DISALLOW_INCOMPATIBLE_COL_TYPE_CHANGES, HiveConf.ConfVars.USERS_IN_ADMIN_ROLE, HiveConf.ConfVars.HIVE_AUTHORIZATION_MANAGER, HiveConf.ConfVars.HIVE_TXN_MANAGER, HiveConf.ConfVars.HIVE_TXN_TIMEOUT, HiveConf.ConfVars.HIVE_TXN_OPERATIONAL_PROPERTIES, HiveConf.ConfVars.HIVE_TXN_HEARTBEAT_THREADPOOL_SIZE, HiveConf.ConfVars.HIVE_TXN_MAX_OPEN_BATCH, HiveConf.ConfVars.HIVE_TXN_RETRYABLE_SQLEX_REGEX, HiveConf.ConfVars.HIVE_METASTORE_STATS_NDV_TUNER, HiveConf.ConfVars.HIVE_METASTORE_STATS_NDV_DENSITY_FUNCTION, HiveConf.ConfVars.METASTORE_AGGREGATE_STATS_CACHE_ENABLED, HiveConf.ConfVars.METASTORE_AGGREGATE_STATS_CACHE_SIZE, HiveConf.ConfVars.METASTORE_AGGREGATE_STATS_CACHE_MAX_PARTITIONS, HiveConf.ConfVars.METASTORE_AGGREGATE_STATS_CACHE_FPP, HiveConf.ConfVars.METASTORE_AGGREGATE_STATS_CACHE_MAX_VARIANCE, HiveConf.ConfVars.METASTORE_AGGREGATE_STATS_CACHE_TTL, HiveConf.ConfVars.METASTORE_AGGREGATE_STATS_CACHE_MAX_WRITER_WAIT, HiveConf.ConfVars.METASTORE_AGGREGATE_STATS_CACHE_MAX_READER_WAIT, HiveConf.ConfVars.METASTORE_AGGREGATE_STATS_CACHE_MAX_FULL, HiveConf.ConfVars.METASTORE_AGGREGATE_STATS_CACHE_CLEAN_UNTIL, HiveConf.ConfVars.METASTORE_FASTPATH, HiveConf.ConfVars.METASTORE_HBASE_CATALOG_CACHE_SIZE, HiveConf.ConfVars.METASTORE_HBASE_AGGREGATE_STATS_CACHE_SIZE, HiveConf.ConfVars.METASTORE_HBASE_AGGREGATE_STATS_CACHE_MAX_PARTITIONS, HiveConf.ConfVars.METASTORE_HBASE_AGGREGATE_STATS_CACHE_FALSE_POSITIVE_PROBABILITY, HiveConf.ConfVars.METASTORE_HBASE_AGGREGATE_STATS_CACHE_MAX_VARIANCE, HiveConf.ConfVars.METASTORE_HBASE_CACHE_TIME_TO_LIVE, HiveConf.ConfVars.METASTORE_HBASE_CACHE_MAX_WRITER_WAIT, HiveConf.ConfVars.METASTORE_HBASE_CACHE_MAX_READER_WAIT, HiveConf.ConfVars.METASTORE_HBASE_CACHE_MAX_FULL, HiveConf.ConfVars.METASTORE_HBASE_CACHE_CLEAN_UNTIL, HiveConf.ConfVars.METASTORE_HBASE_CONNECTION_CLASS, HiveConf.ConfVars.METASTORE_HBASE_AGGR_STATS_CACHE_ENTRIES, HiveConf.ConfVars.METASTORE_HBASE_AGGR_STATS_MEMORY_TTL, HiveConf.ConfVars.METASTORE_HBASE_AGGR_STATS_INVALIDATOR_FREQUENCY, HiveConf.ConfVars.METASTORE_HBASE_AGGR_STATS_HBASE_TTL, HiveConf.ConfVars.METASTORE_HBASE_FILE_METADATA_THREADS};
      metaConfVars = new ConfVars[]{HiveConf.ConfVars.METASTORE_TRY_DIRECT_SQL, HiveConf.ConfVars.METASTORE_TRY_DIRECT_SQL_DDL, HiveConf.ConfVars.METASTORE_CLIENT_SOCKET_TIMEOUT, HiveConf.ConfVars.METASTORE_PARTITION_NAME_WHITELIST_PATTERN, HiveConf.ConfVars.METASTORE_CAPABILITY_CHECK};

      for(ConfVars confVar : metaConfVars) {
         metaConfs.put(confVar.varname, confVar);
      }

      dbVars = new ConfVars[]{HiveConf.ConfVars.HADOOPBIN, HiveConf.ConfVars.METASTOREWAREHOUSE, HiveConf.ConfVars.SCRATCHDIR};
      sqlStdAuthSafeVarNames = new String[]{HiveConf.ConfVars.AGGR_JOIN_TRANSPOSE.varname, HiveConf.ConfVars.BYTESPERREDUCER.varname, HiveConf.ConfVars.CLIENT_STATS_COUNTERS.varname, HiveConf.ConfVars.DEFAULTPARTITIONNAME.varname, HiveConf.ConfVars.DROPIGNORESNONEXISTENT.varname, HiveConf.ConfVars.HIVECOUNTERGROUP.varname, HiveConf.ConfVars.HIVEDEFAULTMANAGEDFILEFORMAT.varname, HiveConf.ConfVars.HIVEENFORCEBUCKETMAPJOIN.varname, HiveConf.ConfVars.HIVEENFORCESORTMERGEBUCKETMAPJOIN.varname, HiveConf.ConfVars.HIVEEXPREVALUATIONCACHE.varname, HiveConf.ConfVars.HIVEQUERYRESULTFILEFORMAT.varname, HiveConf.ConfVars.HIVEHASHTABLELOADFACTOR.varname, HiveConf.ConfVars.HIVEHASHTABLETHRESHOLD.varname, HiveConf.ConfVars.HIVEIGNOREMAPJOINHINT.varname, HiveConf.ConfVars.HIVELIMITMAXROWSIZE.varname, HiveConf.ConfVars.HIVEMAPREDMODE.varname, HiveConf.ConfVars.HIVEMAPSIDEAGGREGATE.varname, HiveConf.ConfVars.HIVEOPTIMIZEMETADATAQUERIES.varname, HiveConf.ConfVars.HIVEROWOFFSET.varname, HiveConf.ConfVars.HIVEVARIABLESUBSTITUTE.varname, HiveConf.ConfVars.HIVEVARIABLESUBSTITUTEDEPTH.varname, HiveConf.ConfVars.HIVE_AUTOGEN_COLUMNALIAS_PREFIX_INCLUDEFUNCNAME.varname, HiveConf.ConfVars.HIVE_AUTOGEN_COLUMNALIAS_PREFIX_LABEL.varname, HiveConf.ConfVars.HIVE_CHECK_CROSS_PRODUCT.varname, HiveConf.ConfVars.HIVE_CLI_TEZ_SESSION_ASYNC.varname, HiveConf.ConfVars.HIVE_COMPAT.varname, HiveConf.ConfVars.HIVE_CONCATENATE_CHECK_INDEX.varname, HiveConf.ConfVars.HIVE_DISPLAY_PARTITION_COLUMNS_SEPARATELY.varname, HiveConf.ConfVars.HIVE_ERROR_ON_EMPTY_PARTITION.varname, HiveConf.ConfVars.HIVE_EXECUTION_ENGINE.varname, HiveConf.ConfVars.HIVE_EXEC_COPYFILE_MAXSIZE.varname, HiveConf.ConfVars.HIVE_EXIM_URI_SCHEME_WL.varname, HiveConf.ConfVars.HIVE_FILE_MAX_FOOTER.varname, HiveConf.ConfVars.HIVE_INSERT_INTO_MULTILEVEL_DIRS.varname, HiveConf.ConfVars.HIVE_LOCALIZE_RESOURCE_NUM_WAIT_ATTEMPTS.varname, HiveConf.ConfVars.HIVE_MULTI_INSERT_MOVE_TASKS_SHARE_DEPENDENCIES.varname, HiveConf.ConfVars.HIVE_QUOTEDID_SUPPORT.varname, HiveConf.ConfVars.HIVE_RESULTSET_USE_UNIQUE_COLUMN_NAMES.varname, HiveConf.ConfVars.HIVE_STATS_COLLECT_PART_LEVEL_STATS.varname, HiveConf.ConfVars.HIVE_SCHEMA_EVOLUTION.varname, HiveConf.ConfVars.HIVE_SERVER2_LOGGING_OPERATION_LEVEL.varname, HiveConf.ConfVars.HIVE_SERVER2_THRIFT_RESULTSET_SERIALIZE_IN_TASKS.varname, HiveConf.ConfVars.HIVE_SUPPORT_SPECICAL_CHARACTERS_IN_TABLE_NAMES.varname, HiveConf.ConfVars.JOB_DEBUG_CAPTURE_STACKTRACES.varname, HiveConf.ConfVars.JOB_DEBUG_TIMEOUT.varname, HiveConf.ConfVars.LLAP_IO_ENABLED.varname, HiveConf.ConfVars.LLAP_IO_USE_FILEID_PATH.varname, HiveConf.ConfVars.LLAP_DAEMON_SERVICE_HOSTS.varname, HiveConf.ConfVars.LLAP_EXECUTION_MODE.varname, HiveConf.ConfVars.LLAP_AUTO_ALLOW_UBER.varname, HiveConf.ConfVars.LLAP_AUTO_ENFORCE_TREE.varname, HiveConf.ConfVars.LLAP_AUTO_ENFORCE_VECTORIZED.varname, HiveConf.ConfVars.LLAP_AUTO_ENFORCE_STATS.varname, HiveConf.ConfVars.LLAP_AUTO_MAX_INPUT.varname, HiveConf.ConfVars.LLAP_AUTO_MAX_OUTPUT.varname, HiveConf.ConfVars.LLAP_SKIP_COMPILE_UDF_CHECK.varname, HiveConf.ConfVars.LLAP_CLIENT_CONSISTENT_SPLITS.varname, HiveConf.ConfVars.LLAP_ENABLE_GRACE_JOIN_IN_LLAP.varname, HiveConf.ConfVars.LLAP_ALLOW_PERMANENT_FNS.varname, HiveConf.ConfVars.MAXCREATEDFILES.varname, HiveConf.ConfVars.MAXREDUCERS.varname, HiveConf.ConfVars.NWAYJOINREORDER.varname, HiveConf.ConfVars.OUTPUT_FILE_EXTENSION.varname, HiveConf.ConfVars.SHOW_JOB_FAIL_DEBUG_INFO.varname, HiveConf.ConfVars.TASKLOG_DEBUG_TIMEOUT.varname, HiveConf.ConfVars.HIVEQUERYID.varname};
      sqlStdAuthSafeVarNameRegexes = new String[]{"hive\\.auto\\..*", "hive\\.cbo\\..*", "hive\\.convert\\..*", "hive\\.exec\\.dynamic\\.partition.*", "hive\\.exec\\.max\\.dynamic\\.partitions.*", "hive\\.exec\\.compress\\..*", "hive\\.exec\\.infer\\..*", "hive\\.exec\\.mode.local\\..*", "hive\\.exec\\.orc\\..*", "hive\\.exec\\.parallel.*", "hive\\.explain\\..*", "hive\\.fetch.task\\..*", "hive\\.groupby\\..*", "hive\\.hbase\\..*", "hive\\.index\\..*", "hive\\.index\\..*", "hive\\.intermediate\\..*", "hive\\.join\\..*", "hive\\.limit\\..*", "hive\\.log\\..*", "hive\\.mapjoin\\..*", "hive\\.merge\\..*", "hive\\.optimize\\..*", "hive\\.orc\\..*", "hive\\.outerjoin\\..*", "hive\\.parquet\\..*", "hive\\.ppd\\..*", "hive\\.prewarm\\..*", "hive\\.server2\\.thrift\\.resultset\\.default\\.fetch\\.size", "hive\\.server2\\.proxy\\.user", "hive\\.skewjoin\\..*", "hive\\.smbjoin\\..*", "hive\\.stats\\..*", "hive\\.strict\\..*", "hive\\.tez\\..*", "hive\\.vectorized\\..*", "mapred\\.map\\..*", "mapred\\.reduce\\..*", "mapred\\.output\\.compression\\.codec", "mapred\\.job\\.queuename", "mapred\\.output\\.compression\\.type", "mapred\\.min\\.split\\.size", "mapreduce\\.job\\.reduce\\.slowstart\\.completedmaps", "mapreduce\\.job\\.queuename", "mapreduce\\.job\\.tags", "mapreduce\\.input\\.fileinputformat\\.split\\.minsize", "mapreduce\\.map\\..*", "mapreduce\\.reduce\\..*", "mapreduce\\.output\\.fileoutputformat\\.compress\\.codec", "mapreduce\\.output\\.fileoutputformat\\.compress\\.type", "oozie\\..*", "tez\\.am\\..*", "tez\\.task\\..*", "tez\\.runtime\\..*", "tez\\.queue\\.name"};
      reverseMapLock = new Object();
      reverseMap = null;
   }

   public static class URLEncoderDecoder implements EncoderDecoder {
      private static final String UTF_8 = "UTF-8";

      public String encode(String key) {
         try {
            return URLEncoder.encode(key, "UTF-8");
         } catch (UnsupportedEncodingException var3) {
            return key;
         }
      }

      public String decode(String value) {
         try {
            return URLDecoder.decode(value, "UTF-8");
         } catch (UnsupportedEncodingException var3) {
            return value;
         }
      }
   }

   public static class EncoderDecoderFactory {
      public static final URLEncoderDecoder URL_ENCODER_DECODER = new URLEncoderDecoder();
   }

   public static enum ConfVars {
      SCRIPTWRAPPER("hive.exec.script.wrapper", (Object)null, ""),
      PLAN("hive.exec.plan", "", ""),
      STAGINGDIR("hive.exec.stagingdir", ".hive-staging", "Directory name that will be created inside table locations in order to support HDFS encryption. This is replaces ${hive.exec.scratchdir} for query results with the exception of read-only tables. In all cases ${hive.exec.scratchdir} is still used for other temporary files, such as job plans."),
      SCRATCHDIR("hive.exec.scratchdir", "/tmp/hive", "HDFS root scratch dir for Hive jobs which gets created with write all (733) permission. For each connecting user, an HDFS scratch dir: ${hive.exec.scratchdir}/<username> is created, with ${hive.scratch.dir.permission}."),
      REPLDIR("hive.repl.rootdir", "/user/hive/repl/", "HDFS root dir for all replication dumps."),
      REPLCMENABLED("hive.repl.cm.enabled", false, "Turn on ChangeManager, so delete files will go to cmrootdir."),
      REPLCMDIR("hive.repl.cmrootdir", "/user/hive/cmroot/", "Root dir for ChangeManager, used for deleted files."),
      REPLCMRETIAN("hive.repl.cm.retain", "24h", new Validator.TimeValidator(TimeUnit.HOURS), "Time to retain removed files in cmrootdir."),
      REPLCMINTERVAL("hive.repl.cm.interval", "3600s", new Validator.TimeValidator(TimeUnit.SECONDS), "Inteval for cmroot cleanup thread."),
      LOCALSCRATCHDIR("hive.exec.local.scratchdir", "${system:java.io.tmpdir}" + File.separator + "${system:user.name}", "Local scratch space for Hive jobs"),
      DOWNLOADED_RESOURCES_DIR("hive.downloaded.resources.dir", "${system:java.io.tmpdir}" + File.separator + "${hive.session.id}_resources", "Temporary local directory for added resources in the remote file system."),
      SCRATCHDIRPERMISSION("hive.scratch.dir.permission", "700", "The permission for the user specific scratch directories that get created."),
      SUBMITVIACHILD("hive.exec.submitviachild", false, ""),
      SUBMITLOCALTASKVIACHILD("hive.exec.submit.local.task.via.child", true, "Determines whether local tasks (typically mapjoin hashtable generation phase) runs in \nseparate JVM (true recommended) or not. \nAvoids the overhead of spawning new JVM, but can lead to out-of-memory issues."),
      SCRIPTERRORLIMIT("hive.exec.script.maxerrsize", 100000, "Maximum number of bytes a script is allowed to emit to standard error (per map-reduce task). \nThis prevents runaway scripts from filling logs partitions to capacity"),
      ALLOWPARTIALCONSUMP("hive.exec.script.allow.partial.consumption", false, "When enabled, this option allows a user script to exit successfully without consuming \nall the data from the standard input."),
      STREAMREPORTERPERFIX("stream.stderr.reporter.prefix", "reporter:", "Streaming jobs that log to standard error with this prefix can log counter or status information."),
      STREAMREPORTERENABLED("stream.stderr.reporter.enabled", true, "Enable consumption of status and counter messages for streaming jobs."),
      COMPRESSRESULT("hive.exec.compress.output", false, "This controls whether the final outputs of a query (to a local/HDFS file or a Hive table) is compressed. \nThe compression codec and other options are determined from Hadoop config variables mapred.output.compress*"),
      COMPRESSINTERMEDIATE("hive.exec.compress.intermediate", false, "This controls whether intermediate files produced by Hive between multiple map-reduce jobs are compressed. \nThe compression codec and other options are determined from Hadoop config variables mapred.output.compress*"),
      COMPRESSINTERMEDIATECODEC("hive.intermediate.compression.codec", "", ""),
      COMPRESSINTERMEDIATETYPE("hive.intermediate.compression.type", "", ""),
      BYTESPERREDUCER("hive.exec.reducers.bytes.per.reducer", 256000000L, "size per reducer.The default is 256Mb, i.e if the input size is 1G, it will use 4 reducers."),
      MAXREDUCERS("hive.exec.reducers.max", 1009, "max number of reducers will be used. If the one specified in the configuration parameter mapred.reduce.tasks is\nnegative, Hive will use this one as the max number of reducers when automatically determine number of reducers."),
      PREEXECHOOKS("hive.exec.pre.hooks", "", "Comma-separated list of pre-execution hooks to be invoked for each statement. \nA pre-execution hook is specified as the name of a Java class which implements the \norg.apache.hadoop.hive.ql.hooks.ExecuteWithHookContext interface."),
      POSTEXECHOOKS("hive.exec.post.hooks", "", "Comma-separated list of post-execution hooks to be invoked for each statement. \nA post-execution hook is specified as the name of a Java class which implements the \norg.apache.hadoop.hive.ql.hooks.ExecuteWithHookContext interface."),
      ONFAILUREHOOKS("hive.exec.failure.hooks", "", "Comma-separated list of on-failure hooks to be invoked for each statement. \nAn on-failure hook is specified as the name of Java class which implements the \norg.apache.hadoop.hive.ql.hooks.ExecuteWithHookContext interface."),
      QUERYREDACTORHOOKS("hive.exec.query.redactor.hooks", "", "Comma-separated list of hooks to be invoked for each query which can \ntranform the query before it's placed in the job.xml file. Must be a Java class which \nextends from the org.apache.hadoop.hive.ql.hooks.Redactor abstract class."),
      CLIENTSTATSPUBLISHERS("hive.client.stats.publishers", "", "Comma-separated list of statistics publishers to be invoked on counters on each job. \nA client stats publisher is specified as the name of a Java class which implements the \norg.apache.hadoop.hive.ql.stats.ClientStatsPublisher interface."),
      ATSHOOKQUEUECAPACITY("hive.ats.hook.queue.capacity", 64, "Queue size for the ATS Hook executor. If the number of outstanding submissions \nto the ATS executor exceed this amount, the Hive ATS Hook will not try to log queries to ATS."),
      EXECPARALLEL("hive.exec.parallel", false, "Whether to execute jobs in parallel"),
      EXECPARALLETHREADNUMBER("hive.exec.parallel.thread.number", 8, "How many jobs at most can be executed in parallel"),
      HIVESPECULATIVEEXECREDUCERS("hive.mapred.reduce.tasks.speculative.execution", true, "Whether speculative execution for reducers should be turned on. "),
      HIVECOUNTERSPULLINTERVAL("hive.exec.counters.pull.interval", 1000L, "The interval with which to poll the JobTracker for the counters the running job. \nThe smaller it is the more load there will be on the jobtracker, the higher it is the less granular the caught will be."),
      DYNAMICPARTITIONING("hive.exec.dynamic.partition", true, "Whether or not to allow dynamic partitions in DML/DDL."),
      DYNAMICPARTITIONINGMODE("hive.exec.dynamic.partition.mode", "strict", "In strict mode, the user must specify at least one static partition\nin case the user accidentally overwrites all partitions.\nIn nonstrict mode all partitions are allowed to be dynamic."),
      DYNAMICPARTITIONMAXPARTS("hive.exec.max.dynamic.partitions", 1000, "Maximum number of dynamic partitions allowed to be created in total."),
      DYNAMICPARTITIONMAXPARTSPERNODE("hive.exec.max.dynamic.partitions.pernode", 100, "Maximum number of dynamic partitions allowed to be created in each mapper/reducer node."),
      MAXCREATEDFILES("hive.exec.max.created.files", 100000L, "Maximum number of HDFS files created by all mappers/reducers in a MapReduce job."),
      DEFAULTPARTITIONNAME("hive.exec.default.partition.name", "__HIVE_DEFAULT_PARTITION__", "The default partition name in case the dynamic partition column value is null/empty string or any other values that cannot be escaped. \nThis value must not contain any special character used in HDFS URI (e.g., ':', '%', '/' etc). \nThe user has to be aware that the dynamic partition value should not contain this value to avoid confusions."),
      DEFAULT_ZOOKEEPER_PARTITION_NAME("hive.lockmgr.zookeeper.default.partition.name", "__HIVE_DEFAULT_ZOOKEEPER_PARTITION__", ""),
      SHOW_JOB_FAIL_DEBUG_INFO("hive.exec.show.job.failure.debug.info", true, "If a job fails, whether to provide a link in the CLI to the task with the\nmost failures, along with debugging hints if applicable."),
      JOB_DEBUG_CAPTURE_STACKTRACES("hive.exec.job.debug.capture.stacktraces", true, "Whether or not stack traces parsed from the task logs of a sampled failed task \nfor each failed job should be stored in the SessionState"),
      JOB_DEBUG_TIMEOUT("hive.exec.job.debug.timeout", 30000, ""),
      TASKLOG_DEBUG_TIMEOUT("hive.exec.tasklog.debug.timeout", 20000, ""),
      OUTPUT_FILE_EXTENSION("hive.output.file.extension", (Object)null, "String used as a file extension for output files. \nIf not set, defaults to the codec extension for text files (e.g. \".gz\"), or no extension otherwise."),
      HIVE_IN_TEST("hive.in.test", false, "internal usage only, true in test mode", true),
      HIVE_IN_TEZ_TEST("hive.in.tez.test", false, "internal use only, true when in testing tez", true),
      LOCALMODEAUTO("hive.exec.mode.local.auto", false, "Let Hive determine whether to run in local mode automatically"),
      LOCALMODEMAXBYTES("hive.exec.mode.local.auto.inputbytes.max", 134217728L, "When hive.exec.mode.local.auto is true, input bytes should less than this for local mode."),
      LOCALMODEMAXINPUTFILES("hive.exec.mode.local.auto.input.files.max", 4, "When hive.exec.mode.local.auto is true, the number of tasks should less than this for local mode."),
      DROPIGNORESNONEXISTENT("hive.exec.drop.ignorenonexistent", true, "Do not report an error if DROP TABLE/VIEW/Index/Function specifies a non-existent table/view/index/function"),
      HIVEIGNOREMAPJOINHINT("hive.ignore.mapjoin.hint", true, "Ignore the mapjoin hint"),
      HIVE_FILE_MAX_FOOTER("hive.file.max.footer", 100, "maximum number of lines for footer user can define for a table file"),
      HIVE_RESULTSET_USE_UNIQUE_COLUMN_NAMES("hive.resultset.use.unique.column.names", true, "Make column names unique in the result set by qualifying column names with table alias if needed.\nTable alias will be added to column names for queries of type \"select *\" or \nif query explicitly uses table alias \"select r1.x..\"."),
      HADOOPBIN("hadoop.bin.path", findHadoopBinary(), "", true),
      YARNBIN("yarn.bin.path", findYarnBinary(), "", true),
      HIVE_FS_HAR_IMPL("fs.har.impl", "org.apache.hadoop.hive.shims.HiveHarFileSystem", "The implementation for accessing Hadoop Archives. Note that this won't be applicable to Hadoop versions less than 0.20"),
      MAPREDMAXSPLITSIZE("mapreduce.input.fileinputformat.split.maxsize", 256000000L, "", true),
      MAPREDMINSPLITSIZE("mapreduce.input.fileinputformat.split.minsize", 1L, "", true),
      MAPREDMINSPLITSIZEPERNODE("mapreduce.input.fileinputformat.split.minsize.per.node", 1L, "", true),
      MAPREDMINSPLITSIZEPERRACK("mapreduce.input.fileinputformat.split.minsize.per.rack", 1L, "", true),
      HADOOPNUMREDUCERS("mapreduce.job.reduces", -1, "", true),
      METASTOREWAREHOUSE("hive.metastore.warehouse.dir", "/user/hive/warehouse", "location of default database for the warehouse"),
      METASTOREURIS("hive.metastore.uris", "", "Thrift URI for the remote metastore. Used by metastore client to connect to remote metastore."),
      METASTORE_CAPABILITY_CHECK("hive.metastore.client.capability.check", false, "Whether to check client capabilities for potentially breaking API usage."),
      METASTORE_FASTPATH("hive.metastore.fastpath", false, "Used to avoid all of the proxies and object copies in the metastore.  Note, if this is set, you MUST use a local metastore (hive.metastore.uris must be empty) otherwise undefined and most likely undesired behavior will result"),
      METASTORE_FS_HANDLER_THREADS_COUNT("hive.metastore.fshandler.threads", 15, "Number of threads to be allocated for metastore handler for fs operations."),
      METASTORE_HBASE_CATALOG_CACHE_SIZE("hive.metastore.hbase.catalog.cache.size", 50000, "Maximum number of objects we will place in the hbase metastore catalog cache.  The objects will be divided up by types that we need to cache."),
      METASTORE_HBASE_AGGREGATE_STATS_CACHE_SIZE("hive.metastore.hbase.aggregate.stats.cache.size", 10000, "Maximum number of aggregate stats nodes that we will place in the hbase metastore aggregate stats cache."),
      METASTORE_HBASE_AGGREGATE_STATS_CACHE_MAX_PARTITIONS("hive.metastore.hbase.aggregate.stats.max.partitions", 10000, "Maximum number of partitions that are aggregated per cache node."),
      METASTORE_HBASE_AGGREGATE_STATS_CACHE_FALSE_POSITIVE_PROBABILITY("hive.metastore.hbase.aggregate.stats.false.positive.probability", 0.01F, "Maximum false positive probability for the Bloom Filter used in each aggregate stats cache node (default 1%)."),
      METASTORE_HBASE_AGGREGATE_STATS_CACHE_MAX_VARIANCE("hive.metastore.hbase.aggregate.stats.max.variance", 0.1F, "Maximum tolerable variance in number of partitions between a cached node and our request (default 10%)."),
      METASTORE_HBASE_CACHE_TIME_TO_LIVE("hive.metastore.hbase.cache.ttl", "600s", new Validator.TimeValidator(TimeUnit.SECONDS), "Number of seconds for a cached node to be active in the cache before they become stale."),
      METASTORE_HBASE_CACHE_MAX_WRITER_WAIT("hive.metastore.hbase.cache.max.writer.wait", "5000ms", new Validator.TimeValidator(TimeUnit.MILLISECONDS), "Number of milliseconds a writer will wait to acquire the writelock before giving up."),
      METASTORE_HBASE_CACHE_MAX_READER_WAIT("hive.metastore.hbase.cache.max.reader.wait", "1000ms", new Validator.TimeValidator(TimeUnit.MILLISECONDS), "Number of milliseconds a reader will wait to acquire the readlock before giving up."),
      METASTORE_HBASE_CACHE_MAX_FULL("hive.metastore.hbase.cache.max.full", 0.9F, "Maximum cache full % after which the cache cleaner thread kicks in."),
      METASTORE_HBASE_CACHE_CLEAN_UNTIL("hive.metastore.hbase.cache.clean.until", 0.8F, "The cleaner thread cleans until cache reaches this % full size."),
      METASTORE_HBASE_CONNECTION_CLASS("hive.metastore.hbase.connection.class", "org.apache.hadoop.hive.metastore.hbase.VanillaHBaseConnection", "Class used to connection to HBase"),
      METASTORE_HBASE_AGGR_STATS_CACHE_ENTRIES("hive.metastore.hbase.aggr.stats.cache.entries", 10000, "How many in stats objects to cache in memory"),
      METASTORE_HBASE_AGGR_STATS_MEMORY_TTL("hive.metastore.hbase.aggr.stats.memory.ttl", "60s", new Validator.TimeValidator(TimeUnit.SECONDS), "Number of seconds stats objects live in memory after they are read from HBase."),
      METASTORE_HBASE_AGGR_STATS_INVALIDATOR_FREQUENCY("hive.metastore.hbase.aggr.stats.invalidator.frequency", "5s", new Validator.TimeValidator(TimeUnit.SECONDS), "How often the stats cache scans its HBase entries and looks for expired entries"),
      METASTORE_HBASE_AGGR_STATS_HBASE_TTL("hive.metastore.hbase.aggr.stats.hbase.ttl", "604800s", new Validator.TimeValidator(TimeUnit.SECONDS), "Number of seconds stats entries live in HBase cache after they are created.  They may be invalided by updates or partition drops before this.  Default is one week."),
      METASTORE_HBASE_FILE_METADATA_THREADS("hive.metastore.hbase.file.metadata.threads", 1, "Number of threads to use to read file metadata in background to cache it."),
      METASTORETHRIFTCONNECTIONRETRIES("hive.metastore.connect.retries", 3, "Number of retries while opening a connection to metastore"),
      METASTORETHRIFTFAILURERETRIES("hive.metastore.failure.retries", 1, "Number of retries upon failure of Thrift metastore calls"),
      METASTORE_SERVER_PORT("hive.metastore.port", 9083, "Hive metastore listener port"),
      METASTORE_CLIENT_CONNECT_RETRY_DELAY("hive.metastore.client.connect.retry.delay", "1s", new Validator.TimeValidator(TimeUnit.SECONDS), "Number of seconds for the client to wait between consecutive connection attempts"),
      METASTORE_CLIENT_SOCKET_TIMEOUT("hive.metastore.client.socket.timeout", "600s", new Validator.TimeValidator(TimeUnit.SECONDS), "MetaStore Client socket timeout in seconds"),
      METASTORE_CLIENT_SOCKET_LIFETIME("hive.metastore.client.socket.lifetime", "0s", new Validator.TimeValidator(TimeUnit.SECONDS), "MetaStore Client socket lifetime in seconds. After this time is exceeded, client\nreconnects on the next MetaStore operation. A value of 0s means the connection\nhas an infinite lifetime."),
      METASTORE_CLIENT_CONNECTION_TIMEOUT("hive.metastore.client.connection.timeout", "600s", new Validator.TimeValidator(TimeUnit.SECONDS), "MetaStore Client connection timeout in seconds"),
      METASTOREPWD("javax.jdo.option.ConnectionPassword", "mine", "password to use against metastore database"),
      METASTORECONNECTURLHOOK("hive.metastore.ds.connection.url.hook", "", "Name of the hook to use for retrieving the JDO connection URL. If empty, the value in javax.jdo.option.ConnectionURL is used"),
      METASTOREMULTITHREADED("javax.jdo.option.Multithreaded", true, "Set this to true if multiple threads access metastore through JDO concurrently."),
      METASTORECONNECTURLKEY("javax.jdo.option.ConnectionURL", "jdbc:derby:;databaseName=metastore_db;create=true", "JDBC connect string for a JDBC metastore.\nTo use SSL to encrypt/authenticate the connection, provide database-specific SSL flag in the connection URL.\nFor example, jdbc:postgresql://myhost/db?ssl=true for postgres database."),
      METASTORE_DBACCESS_SSL_PROPS("hive.metastore.dbaccess.ssl.properties", "", "Comma-separated SSL properties for metastore to access database when JDO connection URL\nenables SSL access. e.g. javax.net.ssl.trustStore=/tmp/truststore,javax.net.ssl.trustStorePassword=pwd."),
      HMSHANDLERATTEMPTS("hive.hmshandler.retry.attempts", 10, "The number of times to retry a HMSHandler call if there were a connection error."),
      HMSHANDLERINTERVAL("hive.hmshandler.retry.interval", "2000ms", new Validator.TimeValidator(TimeUnit.MILLISECONDS), "The time between HMSHandler retry attempts on failure."),
      HMSHANDLERFORCERELOADCONF("hive.hmshandler.force.reload.conf", false, "Whether to force reloading of the HMSHandler configuration (including\nthe connection URL, before the next metastore query that accesses the\ndatastore. Once reloaded, this value is reset to false. Used for\ntesting only."),
      METASTORESERVERMAXMESSAGESIZE("hive.metastore.server.max.message.size", 104857600L, "Maximum message size in bytes a HMS will accept."),
      METASTORESERVERMINTHREADS("hive.metastore.server.min.threads", 200, "Minimum number of worker threads in the Thrift server's pool."),
      METASTORESERVERMAXTHREADS("hive.metastore.server.max.threads", 1000, "Maximum number of worker threads in the Thrift server's pool."),
      METASTORE_TCP_KEEP_ALIVE("hive.metastore.server.tcp.keepalive", true, "Whether to enable TCP keepalive for the metastore server. Keepalive will prevent accumulation of half-open connections."),
      METASTORE_INT_ORIGINAL("hive.metastore.archive.intermediate.original", "_INTERMEDIATE_ORIGINAL", "Intermediate dir suffixes used for archiving. Not important what they\nare, as long as collisions are avoided"),
      METASTORE_INT_ARCHIVED("hive.metastore.archive.intermediate.archived", "_INTERMEDIATE_ARCHIVED", ""),
      METASTORE_INT_EXTRACTED("hive.metastore.archive.intermediate.extracted", "_INTERMEDIATE_EXTRACTED", ""),
      METASTORE_KERBEROS_KEYTAB_FILE("hive.metastore.kerberos.keytab.file", "", "The path to the Kerberos Keytab file containing the metastore Thrift server's service principal."),
      METASTORE_KERBEROS_PRINCIPAL("hive.metastore.kerberos.principal", "hive-metastore/_HOST@EXAMPLE.COM", "The service principal for the metastore Thrift server. \nThe special string _HOST will be replaced automatically with the correct host name."),
      METASTORE_USE_THRIFT_SASL("hive.metastore.sasl.enabled", false, "If true, the metastore Thrift interface will be secured with SASL. Clients must authenticate with Kerberos."),
      METASTORE_USE_THRIFT_FRAMED_TRANSPORT("hive.metastore.thrift.framed.transport.enabled", false, "If true, the metastore Thrift interface will use TFramedTransport. When false (default) a standard TTransport is used."),
      METASTORE_USE_THRIFT_COMPACT_PROTOCOL("hive.metastore.thrift.compact.protocol.enabled", false, "If true, the metastore Thrift interface will use TCompactProtocol. When false (default) TBinaryProtocol will be used.\nSetting it to true will break compatibility with older clients running TBinaryProtocol."),
      METASTORE_TOKEN_SIGNATURE("hive.metastore.token.signature", "", "The delegation token service name to match when selecting a token from the current user's tokens."),
      METASTORE_CLUSTER_DELEGATION_TOKEN_STORE_CLS("hive.cluster.delegation.token.store.class", "org.apache.hadoop.hive.thrift.MemoryTokenStore", "The delegation token store implementation. Set to org.apache.hadoop.hive.thrift.ZooKeeperTokenStore for load-balanced cluster."),
      METASTORE_CLUSTER_DELEGATION_TOKEN_STORE_ZK_CONNECTSTR("hive.cluster.delegation.token.store.zookeeper.connectString", "", "The ZooKeeper token store connect string. You can re-use the configuration value\nset in hive.zookeeper.quorum, by leaving this parameter unset."),
      METASTORE_CLUSTER_DELEGATION_TOKEN_STORE_ZK_ZNODE("hive.cluster.delegation.token.store.zookeeper.znode", "/hivedelegation", "The root path for token store data. Note that this is used by both HiveServer2 and\nMetaStore to store delegation Token. One directory gets created for each of them.\nThe final directory names would have the servername appended to it (HIVESERVER2,\nMETASTORE)."),
      METASTORE_CLUSTER_DELEGATION_TOKEN_STORE_ZK_ACL("hive.cluster.delegation.token.store.zookeeper.acl", "", "ACL for token store entries. Comma separated list of ACL entries. For example:\nsasl:hive/host1@MY.DOMAIN:cdrwa,sasl:hive/host2@MY.DOMAIN:cdrwa\nDefaults to all permissions for the hiveserver2/metastore process user."),
      METASTORE_CACHE_PINOBJTYPES("hive.metastore.cache.pinobjtypes", "Table,StorageDescriptor,SerDeInfo,Partition,Database,Type,FieldSchema,Order", "List of comma separated metastore object types that should be pinned in the cache"),
      METASTORE_CONNECTION_POOLING_TYPE("datanucleus.connectionPoolingType", "BONECP", new Validator.StringSet(new String[]{"BONECP", "DBCP", "HikariCP", "NONE"}), "Specify connection pool library for datanucleus"),
      METASTORE_CONNECTION_POOLING_MAX_CONNECTIONS("datanucleus.connectionPool.maxPoolSize", 10, "Specify the maximum number of connections in the connection pool. Note: The configured size will be used by\n 2 connection pools (TxnHandler and ObjectStore). When configuring the max connection pool size, it is \nrecommended to take into account the number of metastore instances and the number of HiveServer2 instances \nconfigured with embedded metastore. To get optimal performance, set config to meet the following condition\n(2 * pool_size * metastore_instances + 2 * pool_size * HS2_instances_with_embedded_metastore) = \n(2 * physical_core_count + hard_disk_count)."),
      METASTORE_DATANUCLEUS_INIT_COL_INFO("datanucleus.rdbms.initializeColumnInfo", "NONE", "initializeColumnInfo setting for DataNucleus; set to NONE at least on Postgres."),
      METASTORE_VALIDATE_TABLES("datanucleus.schema.validateTables", false, "validates existing schema against code. turn this on if you want to verify existing schema"),
      METASTORE_VALIDATE_COLUMNS("datanucleus.schema.validateColumns", false, "validates existing schema against code. turn this on if you want to verify existing schema"),
      METASTORE_VALIDATE_CONSTRAINTS("datanucleus.schema.validateConstraints", false, "validates existing schema against code. turn this on if you want to verify existing schema"),
      METASTORE_STORE_MANAGER_TYPE("datanucleus.storeManagerType", "rdbms", "metadata store type"),
      METASTORE_AUTO_CREATE_ALL("datanucleus.schema.autoCreateAll", false, "Auto creates necessary schema on a startup if one doesn't exist. Set this to false, after creating it once.To enable auto create also set hive.metastore.schema.verification=false. Auto creation is not recommended for production use cases, run schematool command instead."),
      METASTORE_SCHEMA_VERIFICATION("hive.metastore.schema.verification", true, "Enforce metastore schema version consistency.\nTrue: Verify that version information stored in is compatible with one from Hive jars.  Also disable automatic\n      schema migration attempt. Users are required to manually migrate schema after Hive upgrade which ensures\n      proper metastore schema migration. (Default)\nFalse: Warn if the version information stored in metastore doesn't match with one from in Hive jars."),
      METASTORE_SCHEMA_VERIFICATION_RECORD_VERSION("hive.metastore.schema.verification.record.version", false, "When true the current MS version is recorded in the VERSION table. If this is disabled and verification is\n enabled the MS will be unusable."),
      METASTORE_TRANSACTION_ISOLATION("datanucleus.transactionIsolation", "read-committed", "Default transaction isolation level for identity generation."),
      METASTORE_CACHE_LEVEL2("datanucleus.cache.level2", false, "Use a level 2 cache. Turn this off if metadata is changed independently of Hive metastore server"),
      METASTORE_CACHE_LEVEL2_TYPE("datanucleus.cache.level2.type", "none", ""),
      METASTORE_IDENTIFIER_FACTORY("datanucleus.identifierFactory", "datanucleus1", "Name of the identifier factory to use when generating table/column names etc. \n'datanucleus1' is used for backward compatibility with DataNucleus v1"),
      METASTORE_USE_LEGACY_VALUE_STRATEGY("datanucleus.rdbms.useLegacyNativeValueStrategy", true, ""),
      METASTORE_QUERY_SQL_ALLOWALL("datanucleus.query.sql.allowAll", true, "In strict JDO all SQL queries must begin with \"SELECT ...\", and consequently it is not possible to execute queries that change data. This DataNucleus property when set to true allows insert, update and delete operations from JDO SQL. Default value is true."),
      METASTORE_PLUGIN_REGISTRY_BUNDLE_CHECK("datanucleus.plugin.pluginRegistryBundleCheck", "LOG", "Defines what happens when plugin bundles are found and are duplicated [EXCEPTION|LOG|NONE]"),
      METASTORE_BATCH_RETRIEVE_MAX("hive.metastore.batch.retrieve.max", 300, "Maximum number of objects (tables/partitions) can be retrieved from metastore in one batch. \nThe higher the number, the less the number of round trips is needed to the Hive metastore server, \nbut it may also cause higher memory requirement at the client side."),
      METASTORE_BATCH_RETRIEVE_OBJECTS_MAX("hive.metastore.batch.retrieve.table.partition.max", 1000, "Maximum number of objects that metastore internally retrieves in one batch."),
      METASTORE_INIT_HOOKS("hive.metastore.init.hooks", "", "A comma separated list of hooks to be invoked at the beginning of HMSHandler initialization. \nAn init hook is specified as the name of Java class which extends org.apache.hadoop.hive.metastore.MetaStoreInitListener."),
      METASTORE_PRE_EVENT_LISTENERS("hive.metastore.pre.event.listeners", "", "List of comma separated listeners for metastore events."),
      METASTORE_EVENT_LISTENERS("hive.metastore.event.listeners", "", "A comma separated list of Java classes that implement the org.apache.hadoop.hive.metastore.MetaStoreEventListener interface. The metastore event and corresponding listener method will be invoked in separate JDO transactions. Alternatively, configure hive.metastore.transactional.event.listeners to ensure both are invoked in same JDO transaction."),
      METASTORE_TRANSACTIONAL_EVENT_LISTENERS("hive.metastore.transactional.event.listeners", "", "A comma separated list of Java classes that implement the org.apache.hadoop.hive.metastore.MetaStoreEventListener interface. Both the metastore event and corresponding listener method will be invoked in the same JDO transaction."),
      METASTORE_EVENT_DB_LISTENER_TTL("hive.metastore.event.db.listener.timetolive", "86400s", new Validator.TimeValidator(TimeUnit.SECONDS), "time after which events will be removed from the database listener queue"),
      METASTORE_AUTHORIZATION_STORAGE_AUTH_CHECKS("hive.metastore.authorization.storage.checks", false, "Should the metastore do authorization checks against the underlying storage (usually hdfs) \nfor operations like drop-partition (disallow the drop-partition if the user in\nquestion doesn't have permissions to delete the corresponding directory\non the storage)."),
      METASTORE_AUTHORIZATION_EXTERNALTABLE_DROP_CHECK("hive.metastore.authorization.storage.check.externaltable.drop", true, "Should StorageBasedAuthorization check permission of the storage before dropping external table.\nStorageBasedAuthorization already does this check for managed table. For external table however,\nanyone who has read permission of the directory could drop external table, which is surprising.\nThe flag is set to false by default to maintain backward compatibility."),
      METASTORE_EVENT_CLEAN_FREQ("hive.metastore.event.clean.freq", "0s", new Validator.TimeValidator(TimeUnit.SECONDS), "Frequency at which timer task runs to purge expired events in metastore."),
      METASTORE_EVENT_EXPIRY_DURATION("hive.metastore.event.expiry.duration", "0s", new Validator.TimeValidator(TimeUnit.SECONDS), "Duration after which events expire from events table"),
      METASTORE_EVENT_MESSAGE_FACTORY("hive.metastore.event.message.factory", "org.apache.hadoop.hive.metastore.messaging.json.JSONMessageFactory", "Factory class for making encoding and decoding messages in the events generated."),
      METASTORE_EXECUTE_SET_UGI("hive.metastore.execute.setugi", true, "In unsecure mode, setting this property to true will cause the metastore to execute DFS operations using \nthe client's reported user and group permissions. Note that this property must be set on \nboth the client and server sides. Further note that its best effort. \nIf client sets its to true and server sets it to false, client setting will be ignored."),
      METASTORE_PARTITION_NAME_WHITELIST_PATTERN("hive.metastore.partition.name.whitelist.pattern", "", "Partition names will be checked against this regex pattern and rejected if not matched."),
      METASTORE_INTEGER_JDO_PUSHDOWN("hive.metastore.integral.jdo.pushdown", false, "Allow JDO query pushdown for integral partition columns in metastore. Off by default. This\nimproves metastore perf for integral columns, especially if there's a large number of partitions.\nHowever, it doesn't work correctly with integral values that are not normalized (e.g. have\nleading zeroes, like 0012). If metastore direct SQL is enabled and works, this optimization\nis also irrelevant."),
      METASTORE_TRY_DIRECT_SQL("hive.metastore.try.direct.sql", true, "Whether the Hive metastore should try to use direct SQL queries instead of the\nDataNucleus for certain read paths. This can improve metastore performance when\nfetching many partitions or column statistics by orders of magnitude; however, it\nis not guaranteed to work on all RDBMS-es and all versions. In case of SQL failures,\nthe metastore will fall back to the DataNucleus, so it's safe even if SQL doesn't\nwork for all queries on your datastore. If all SQL queries fail (for example, your\nmetastore is backed by MongoDB), you might want to disable this to save the\ntry-and-fall-back cost."),
      METASTORE_DIRECT_SQL_PARTITION_BATCH_SIZE("hive.metastore.direct.sql.batch.size", 0, "Batch size for partition and other object retrieval from the underlying DB in direct\nSQL. For some DBs like Oracle and MSSQL, there are hardcoded or perf-based limitations\nthat necessitate this. For DBs that can handle the queries, this isn't necessary and\nmay impede performance. -1 means no batching, 0 means automatic batching."),
      METASTORE_TRY_DIRECT_SQL_DDL("hive.metastore.try.direct.sql.ddl", true, "Same as hive.metastore.try.direct.sql, for read statements within a transaction that\nmodifies metastore data. Due to non-standard behavior in Postgres, if a direct SQL\nselect query has incorrect syntax or something similar inside a transaction, the\nentire transaction will fail and fall-back to DataNucleus will not be possible. You\nshould disable the usage of direct SQL inside transactions if that happens in your case."),
      METASTORE_DIRECT_SQL_MAX_QUERY_LENGTH("hive.direct.sql.max.query.length", 100, "The maximum\n size of a query string (in KB)."),
      METASTORE_DIRECT_SQL_MAX_ELEMENTS_IN_CLAUSE("hive.direct.sql.max.elements.in.clause", 1000, "The maximum number of values in a IN clause. Once exceeded, it will be broken into\n multiple OR separated IN clauses."),
      METASTORE_DIRECT_SQL_MAX_ELEMENTS_VALUES_CLAUSE("hive.direct.sql.max.elements.values.clause", 1000, "The maximum number of values in a VALUES clause for INSERT statement."),
      METASTORE_ORM_RETRIEVE_MAPNULLS_AS_EMPTY_STRINGS("hive.metastore.orm.retrieveMapNullsAsEmptyStrings", false, "Thrift does not support nulls in maps, so any nulls present in maps retrieved from ORM must either be pruned or converted to empty strings. Some backing dbs such as Oracle persist empty strings as nulls, so we should set this parameter if we wish to reverse that behaviour. For others, pruning is the correct behaviour"),
      METASTORE_DISALLOW_INCOMPATIBLE_COL_TYPE_CHANGES("hive.metastore.disallow.incompatible.col.type.changes", true, "If true (default is false), ALTER TABLE operations which change the type of a\ncolumn (say STRING) to an incompatible type (say MAP) are disallowed.\nRCFile default SerDe (ColumnarSerDe) serializes the values in such a way that the\ndatatypes can be converted from string to any type. The map is also serialized as\na string, which can be read as a string as well. However, with any binary\nserialization, this is not true. Blocking the ALTER TABLE prevents ClassCastExceptions\nwhen subsequently trying to access old partitions.\n\nPrimitive types like INT, STRING, BIGINT, etc., are compatible with each other and are\nnot blocked.\n\nSee HIVE-4409 for more details."),
      METASTORE_LIMIT_PARTITION_REQUEST("hive.metastore.limit.partition.request", -1, "This limits the number of partitions that can be requested from the metastore for a given table.\nThe default value \"-1\" means no limit."),
      NEWTABLEDEFAULTPARA("hive.table.parameters.default", "", "Default property values for newly created tables"),
      DDL_CTL_PARAMETERS_WHITELIST("hive.ddl.createtablelike.properties.whitelist", "", "Table Properties to copy over when executing a Create Table Like."),
      METASTORE_RAW_STORE_IMPL("hive.metastore.rawstore.impl", "org.apache.hadoop.hive.metastore.ObjectStore", "Name of the class that implements org.apache.hadoop.hive.metastore.rawstore interface. \nThis class is used to store and retrieval of raw metadata objects such as table, database"),
      METASTORE_TXN_STORE_IMPL("hive.metastore.txn.store.impl", "org.apache.hadoop.hive.metastore.txn.CompactionTxnHandler", "Name of class that implements org.apache.hadoop.hive.metastore.txn.TxnStore.  This class is used to store and retrieve transactions and locks"),
      METASTORE_CONNECTION_DRIVER("javax.jdo.option.ConnectionDriverName", "org.apache.derby.jdbc.EmbeddedDriver", "Driver class name for a JDBC metastore"),
      METASTORE_MANAGER_FACTORY_CLASS("javax.jdo.PersistenceManagerFactoryClass", "org.datanucleus.api.jdo.JDOPersistenceManagerFactory", "class implementing the jdo persistence"),
      METASTORE_EXPRESSION_PROXY_CLASS("hive.metastore.expression.proxy", "org.apache.hadoop.hive.ql.optimizer.ppr.PartitionExpressionForMetastore", ""),
      METASTORE_DETACH_ALL_ON_COMMIT("javax.jdo.option.DetachAllOnCommit", true, "Detaches all objects from session so that they can be used after transaction is committed"),
      METASTORE_NON_TRANSACTIONAL_READ("javax.jdo.option.NonTransactionalRead", true, "Reads outside of transactions"),
      METASTORE_CONNECTION_USER_NAME("javax.jdo.option.ConnectionUserName", "APP", "Username to use against metastore database"),
      METASTORE_END_FUNCTION_LISTENERS("hive.metastore.end.function.listeners", "", "List of comma separated listeners for the end of metastore functions."),
      METASTORE_PART_INHERIT_TBL_PROPS("hive.metastore.partition.inherit.table.properties", "", "List of comma separated keys occurring in table properties which will get inherited to newly created partitions. \n* implies all the keys will get inherited."),
      METASTORE_FILTER_HOOK("hive.metastore.filter.hook", "org.apache.hadoop.hive.metastore.DefaultMetaStoreFilterHookImpl", "Metastore hook class for filtering the metadata read results. If hive.security.authorization.manageris set to instance of HiveAuthorizerFactory, then this value is ignored."),
      FIRE_EVENTS_FOR_DML("hive.metastore.dml.events", false, "If true, the metastore will be asked to fire events for DML operations"),
      METASTORE_CLIENT_DROP_PARTITIONS_WITH_EXPRESSIONS("hive.metastore.client.drop.partitions.using.expressions", true, "Choose whether dropping partitions with HCatClient pushes the partition-predicate to the metastore, or drops partitions iteratively"),
      METASTORE_AGGREGATE_STATS_CACHE_ENABLED("hive.metastore.aggregate.stats.cache.enabled", true, "Whether aggregate stats caching is enabled or not."),
      METASTORE_AGGREGATE_STATS_CACHE_SIZE("hive.metastore.aggregate.stats.cache.size", 10000, "Maximum number of aggregate stats nodes that we will place in the metastore aggregate stats cache."),
      METASTORE_AGGREGATE_STATS_CACHE_MAX_PARTITIONS("hive.metastore.aggregate.stats.cache.max.partitions", 10000, "Maximum number of partitions that are aggregated per cache node."),
      METASTORE_AGGREGATE_STATS_CACHE_FPP("hive.metastore.aggregate.stats.cache.fpp", 0.01F, "Maximum false positive probability for the Bloom Filter used in each aggregate stats cache node (default 1%)."),
      METASTORE_AGGREGATE_STATS_CACHE_MAX_VARIANCE("hive.metastore.aggregate.stats.cache.max.variance", 0.01F, "Maximum tolerable variance in number of partitions between a cached node and our request (default 1%)."),
      METASTORE_AGGREGATE_STATS_CACHE_TTL("hive.metastore.aggregate.stats.cache.ttl", "600s", new Validator.TimeValidator(TimeUnit.SECONDS), "Number of seconds for a cached node to be active in the cache before they become stale."),
      METASTORE_AGGREGATE_STATS_CACHE_MAX_WRITER_WAIT("hive.metastore.aggregate.stats.cache.max.writer.wait", "5000ms", new Validator.TimeValidator(TimeUnit.MILLISECONDS), "Number of milliseconds a writer will wait to acquire the writelock before giving up."),
      METASTORE_AGGREGATE_STATS_CACHE_MAX_READER_WAIT("hive.metastore.aggregate.stats.cache.max.reader.wait", "1000ms", new Validator.TimeValidator(TimeUnit.MILLISECONDS), "Number of milliseconds a reader will wait to acquire the readlock before giving up."),
      METASTORE_AGGREGATE_STATS_CACHE_MAX_FULL("hive.metastore.aggregate.stats.cache.max.full", 0.9F, "Maximum cache full % after which the cache cleaner thread kicks in."),
      METASTORE_AGGREGATE_STATS_CACHE_CLEAN_UNTIL("hive.metastore.aggregate.stats.cache.clean.until", 0.8F, "The cleaner thread cleans until cache reaches this % full size."),
      METASTORE_METRICS("hive.metastore.metrics.enabled", false, "Enable metrics on the metastore."),
      METASTORE_INIT_METADATA_COUNT_ENABLED("hive.metastore.initial.metadata.count.enabled", true, "Enable a metadata count at metastore startup for metrics."),
      HIVE_METASTORE_USE_SSL("hive.metastore.use.SSL", false, "Set this to true for using SSL encryption in HMS server."),
      HIVE_METASTORE_SSL_KEYSTORE_PATH("hive.metastore.keystore.path", "", "Metastore SSL certificate keystore location."),
      HIVE_METASTORE_SSL_KEYSTORE_PASSWORD("hive.metastore.keystore.password", "", "Metastore SSL certificate keystore password."),
      HIVE_METASTORE_SSL_TRUSTSTORE_PATH("hive.metastore.truststore.path", "", "Metastore SSL certificate truststore location."),
      HIVE_METASTORE_SSL_TRUSTSTORE_PASSWORD("hive.metastore.truststore.password", "", "Metastore SSL certificate truststore password."),
      METADATA_EXPORT_LOCATION("hive.metadata.export.location", "", "When used in conjunction with the org.apache.hadoop.hive.ql.parse.MetaDataExportListener pre event listener, \nit is the location to which the metadata will be exported. The default is an empty string, which results in the \nmetadata being exported to the current user's home directory on HDFS."),
      MOVE_EXPORTED_METADATA_TO_TRASH("hive.metadata.move.exported.metadata.to.trash", true, "When used in conjunction with the org.apache.hadoop.hive.ql.parse.MetaDataExportListener pre event listener, \nthis setting determines if the metadata that is exported will subsequently be moved to the user's trash directory \nalongside the dropped table data. This ensures that the metadata will be cleaned up along with the dropped table data."),
      CLIIGNOREERRORS("hive.cli.errors.ignore", false, ""),
      CLIPRINTCURRENTDB("hive.cli.print.current.db", false, "Whether to include the current database in the Hive prompt."),
      CLIPROMPT("hive.cli.prompt", "hive", "Command line prompt configuration value. Other hiveconf can be used in this configuration value. \nVariable substitution will only be invoked at the Hive CLI startup."),
      CLIPRETTYOUTPUTNUMCOLS("hive.cli.pretty.output.num.cols", -1, "The number of columns to use when formatting output generated by the DESCRIBE PRETTY table_name command.\nIf the value of this property is -1, then Hive will use the auto-detected terminal width."),
      HIVE_METASTORE_FS_HANDLER_CLS("hive.metastore.fs.handler.class", "org.apache.hadoop.hive.metastore.HiveMetaStoreFsImpl", ""),
      HIVESESSIONID("hive.session.id", "", ""),
      HIVESESSIONSILENT("hive.session.silent", false, ""),
      HIVE_SESSION_HISTORY_ENABLED("hive.session.history.enabled", false, "Whether to log Hive query, query plan, runtime statistics etc."),
      HIVEQUERYSTRING("hive.query.string", "", "Query being executed (might be multiple per a session)"),
      HIVEQUERYID("hive.query.id", "", "ID for query being executed (might be multiple per a session)"),
      HIVEJOBNAMELENGTH("hive.jobname.length", 50, "max jobname length"),
      HIVEJAR("hive.jar.path", "", "The location of hive_cli.jar that is used when submitting jobs in a separate jvm."),
      HIVEAUXJARS("hive.aux.jars.path", "", "The location of the plugin jars that contain implementations of user defined functions and serdes."),
      HIVERELOADABLEJARS("hive.reloadable.aux.jars.path", "", "The locations of the plugin jars, which can be a comma-separated folders or jars. Jars can be renewed\nby executing reload command. And these jars can be used as the auxiliary classes like creating a UDF or SerDe."),
      HIVEADDEDFILES("hive.added.files.path", "", "This an internal parameter."),
      HIVEADDEDJARS("hive.added.jars.path", "", "This an internal parameter."),
      HIVEADDEDARCHIVES("hive.added.archives.path", "", "This an internal parameter."),
      HIVE_CURRENT_DATABASE("hive.current.database", "", "Database name used by current session. Internal usage only.", true),
      HIVES_AUTO_PROGRESS_TIMEOUT("hive.auto.progress.timeout", "0s", new Validator.TimeValidator(TimeUnit.SECONDS), "How long to run autoprogressor for the script/UDTF operators.\nSet to 0 for forever."),
      HIVESCRIPTAUTOPROGRESS("hive.script.auto.progress", false, "Whether Hive Transform/Map/Reduce Clause should automatically send progress information to TaskTracker \nto avoid the task getting killed because of inactivity.  Hive sends progress information when the script is \noutputting to stderr.  This option removes the need of periodically producing stderr messages, \nbut users should be cautious because this may prevent infinite loops in the scripts to be killed by TaskTracker."),
      HIVESCRIPTIDENVVAR("hive.script.operator.id.env.var", "HIVE_SCRIPT_OPERATOR_ID", "Name of the environment variable that holds the unique script operator ID in the user's \ntransform function (the custom mapper/reducer that the user has specified in the query)"),
      HIVESCRIPTTRUNCATEENV("hive.script.operator.truncate.env", false, "Truncate each environment variable for external script in scripts operator to 20KB (to fit system limits)"),
      HIVESCRIPT_ENV_BLACKLIST("hive.script.operator.env.blacklist", "hive.txn.valid.txns,hive.script.operator.env.blacklist", "Comma separated list of keys from the configuration file not to convert to environment variables when envoking the script operator"),
      HIVE_STRICT_CHECKS_LARGE_QUERY("hive.strict.checks.large.query", false, "Enabling strict large query checks disallows the following:\n  Orderby without limit.\n  No partition being picked up for a query against partitioned table.\nNote that these checks currently do not consider data size, only the query pattern."),
      HIVE_STRICT_CHECKS_TYPE_SAFETY("hive.strict.checks.type.safety", true, "Enabling strict type safety checks disallows the following:\n  Comparing bigints and strings.\n  Comparing bigints and doubles."),
      HIVE_STRICT_CHECKS_CARTESIAN("hive.strict.checks.cartesian.product", true, "Enabling strict Cartesian join checks disallows the following:\n  Cartesian product (cross join)."),
      HIVE_STRICT_CHECKS_BUCKETING("hive.strict.checks.bucketing", true, "Enabling strict bucketing checks disallows the following:\n  Load into bucketed tables."),
      /** @deprecated */
      @Deprecated
      HIVEMAPREDMODE("hive.mapred.mode", (Object)null, "Deprecated; use hive.strict.checks.* settings instead."),
      HIVEALIAS("hive.alias", "", ""),
      HIVEMAPSIDEAGGREGATE("hive.map.aggr", true, "Whether to use map-side aggregation in Hive Group By queries"),
      HIVEGROUPBYSKEW("hive.groupby.skewindata", false, "Whether there is skew in data to optimize group by queries"),
      HIVEJOINEMITINTERVAL("hive.join.emit.interval", 1000, "How many rows in the right-most join operand Hive should buffer before emitting the join result."),
      HIVEJOINCACHESIZE("hive.join.cache.size", 25000, "How many rows in the joining tables (except the streaming table) should be cached in memory."),
      HIVE_CBO_ENABLED("hive.cbo.enable", true, "Flag to control enabling Cost Based Optimizations using Calcite framework."),
      HIVE_CBO_CNF_NODES_LIMIT("hive.cbo.cnf.maxnodes", -1, "When converting to conjunctive normal form (CNF), fail ifthe expression exceeds this threshold; the threshold is expressed in terms of number of nodes (leaves andinterior nodes). -1 to not set up a threshold."),
      HIVE_CBO_RETPATH_HIVEOP("hive.cbo.returnpath.hiveop", false, "Flag to control calcite plan to hive operator conversion"),
      HIVE_CBO_EXTENDED_COST_MODEL("hive.cbo.costmodel.extended", false, "Flag to control enabling the extended cost model based onCPU, IO and cardinality. Otherwise, the cost model is based on cardinality."),
      HIVE_CBO_COST_MODEL_CPU("hive.cbo.costmodel.cpu", "0.000001", "Default cost of a comparison"),
      HIVE_CBO_COST_MODEL_NET("hive.cbo.costmodel.network", "150.0", "Default cost of a transfering a byte over network; expressed as multiple of CPU cost"),
      HIVE_CBO_COST_MODEL_LFS_WRITE("hive.cbo.costmodel.local.fs.write", "4.0", "Default cost of writing a byte to local FS; expressed as multiple of NETWORK cost"),
      HIVE_CBO_COST_MODEL_LFS_READ("hive.cbo.costmodel.local.fs.read", "4.0", "Default cost of reading a byte from local FS; expressed as multiple of NETWORK cost"),
      HIVE_CBO_COST_MODEL_HDFS_WRITE("hive.cbo.costmodel.hdfs.write", "10.0", "Default cost of writing a byte to HDFS; expressed as multiple of Local FS write cost"),
      HIVE_CBO_COST_MODEL_HDFS_READ("hive.cbo.costmodel.hdfs.read", "1.5", "Default cost of reading a byte from HDFS; expressed as multiple of Local FS read cost"),
      HIVE_CBO_SHOW_WARNINGS("hive.cbo.show.warnings", true, "Toggle display of CBO warnings like missing column stats"),
      AGGR_JOIN_TRANSPOSE("hive.transpose.aggr.join", false, "push aggregates through join"),
      SEMIJOIN_CONVERSION("hive.optimize.semijoin.conversion", true, "convert group by followed by inner equi join into semijoin"),
      HIVE_COLUMN_ALIGNMENT("hive.order.columnalignment", true, "Flag to control whether we want to try to aligncolumns in operators such as Aggregate or Join so that we try to reduce the number of shuffling stages"),
      HIVE_MATERIALIZED_VIEW_ENABLE_AUTO_REWRITING("hive.materializedview.rewriting", false, "Whether to try to rewrite queries using the materialized views enabled for rewriting"),
      HIVE_MATERIALIZED_VIEW_FILE_FORMAT("hive.materializedview.fileformat", "ORC", new Validator.StringSet(new String[]{"none", "TextFile", "SequenceFile", "RCfile", "ORC"}), "Default file format for CREATE MATERIALIZED VIEW statement"),
      HIVE_MATERIALIZED_VIEW_SERDE("hive.materializedview.serde", "org.apache.hadoop.hive.ql.io.orc.OrcSerde", "Default SerDe used for materialized views"),
      HIVEMAPJOINBUCKETCACHESIZE("hive.mapjoin.bucket.cache.size", 100, ""),
      HIVEMAPJOINUSEOPTIMIZEDTABLE("hive.mapjoin.optimized.hashtable", true, "Whether Hive should use memory-optimized hash table for MapJoin.\nOnly works on Tez and Spark, because memory-optimized hashtable cannot be serialized."),
      HIVEMAPJOINOPTIMIZEDTABLEPROBEPERCENT("hive.mapjoin.optimized.hashtable.probe.percent", 0.5F, "Probing space percentage of the optimized hashtable"),
      HIVEUSEHYBRIDGRACEHASHJOIN("hive.mapjoin.hybridgrace.hashtable", true, "Whether to use hybridgrace hash join as the join method for mapjoin. Tez only."),
      HIVEHYBRIDGRACEHASHJOINMEMCHECKFREQ("hive.mapjoin.hybridgrace.memcheckfrequency", 1024, "For hybrid grace hash join, how often (how many rows apart) we check if memory is full. This number should be power of 2."),
      HIVEHYBRIDGRACEHASHJOINMINWBSIZE("hive.mapjoin.hybridgrace.minwbsize", 524288, "For hybrid graceHash join, the minimum write buffer size used by optimized hashtable. Default is 512 KB."),
      HIVEHYBRIDGRACEHASHJOINMINNUMPARTITIONS("hive.mapjoin.hybridgrace.minnumpartitions", 16, "ForHybrid grace hash join, the minimum number of partitions to create."),
      HIVEHASHTABLEWBSIZE("hive.mapjoin.optimized.hashtable.wbsize", 8388608, "Optimized hashtable (see hive.mapjoin.optimized.hashtable) uses a chain of buffers to\nstore data. This is one buffer size. HT may be slightly faster if this is larger, but for small\njoins unnecessary memory will be allocated and then trimmed."),
      HIVEHYBRIDGRACEHASHJOINBLOOMFILTER("hive.mapjoin.hybridgrace.bloomfilter", true, "Whether to use BloomFilter in Hybrid grace hash join to minimize unnecessary spilling."),
      HIVESMBJOINCACHEROWS("hive.smbjoin.cache.rows", 10000, "How many rows with the same key value should be cached in memory per smb joined table."),
      HIVEGROUPBYMAPINTERVAL("hive.groupby.mapaggr.checkinterval", 100000, "Number of rows after which size of the grouping keys/aggregation classes is performed"),
      HIVEMAPAGGRHASHMEMORY("hive.map.aggr.hash.percentmemory", 0.5F, "Portion of total memory to be used by map-side group aggregation hash table"),
      HIVEMAPJOINFOLLOWEDBYMAPAGGRHASHMEMORY("hive.mapjoin.followby.map.aggr.hash.percentmemory", 0.3F, "Portion of total memory to be used by map-side group aggregation hash table, when this group by is followed by map join"),
      HIVEMAPAGGRMEMORYTHRESHOLD("hive.map.aggr.hash.force.flush.memory.threshold", 0.9F, "The max memory to be used by map-side group aggregation hash table.\nIf the memory usage is higher than this number, force to flush data"),
      HIVEMAPAGGRHASHMINREDUCTION("hive.map.aggr.hash.min.reduction", 0.5F, "Hash aggregation will be turned off if the ratio between hash  table size and input rows is bigger than this number. \nSet to 1 to make sure hash aggregation is never turned off."),
      HIVEMULTIGROUPBYSINGLEREDUCER("hive.multigroupby.singlereducer", true, "Whether to optimize multi group by query to generate single M/R  job plan. If the multi group by query has \ncommon group by keys, it will be optimized to generate single M/R job."),
      HIVE_MAP_GROUPBY_SORT("hive.map.groupby.sorted", true, "If the bucketing/sorting properties of the table exactly match the grouping key, whether to perform \nthe group by in the mapper by using BucketizedHiveInputFormat. The only downside to this\nis that it limits the number of mappers to the number of files."),
      HIVE_GROUPBY_POSITION_ALIAS("hive.groupby.position.alias", false, "Whether to enable using Column Position Alias in Group By"),
      HIVE_ORDERBY_POSITION_ALIAS("hive.orderby.position.alias", true, "Whether to enable using Column Position Alias in Order By"),
      /** @deprecated */
      @Deprecated
      HIVE_GROUPBY_ORDERBY_POSITION_ALIAS("hive.groupby.orderby.position.alias", false, "Whether to enable using Column Position Alias in Group By or Order By (deprecated).\nUse " + HIVE_ORDERBY_POSITION_ALIAS.varname + " or " + HIVE_GROUPBY_POSITION_ALIAS.varname + " instead"),
      HIVE_NEW_JOB_GROUPING_SET_CARDINALITY("hive.new.job.grouping.set.cardinality", 30, "Whether a new map-reduce job should be launched for grouping sets/rollups/cubes.\nFor a query like: select a, b, c, count(1) from T group by a, b, c with rollup;\n4 rows are created per row: (a, b, c), (a, b, null), (a, null, null), (null, null, null).\nThis can lead to explosion across map-reduce boundary if the cardinality of T is very high,\nand map-side aggregation does not do a very good job. \n\nThis parameter decides if Hive should add an additional map-reduce job. If the grouping set\ncardinality (4 in the example above), is more than this value, a new MR job is added under the\nassumption that the original group by will reduce the data size."),
      HIVE_GROUPBY_LIMIT_EXTRASTEP("hive.groupby.limit.extrastep", true, "This parameter decides if Hive should \ncreate new MR job for sorting final output"),
      HIVE_EXEC_COPYFILE_MAXNUMFILES("hive.exec.copyfile.maxnumfiles", 1L, "Maximum number of files Hive uses to do sequential HDFS copies between directories.Distributed copies (distcp) will be used instead for larger numbers of files so that copies can be done faster."),
      HIVE_EXEC_COPYFILE_MAXSIZE("hive.exec.copyfile.maxsize", 33554432L, "Maximum file size (in bytes) that Hive uses to do single HDFS copies between directories.Distributed copies (distcp) will be used instead for bigger files so that copies can be done faster."),
      HIVEUDTFAUTOPROGRESS("hive.udtf.auto.progress", false, "Whether Hive should automatically send progress information to TaskTracker \nwhen using UDTF's to prevent the task getting killed because of inactivity.  Users should be cautious \nbecause this may prevent TaskTracker from killing tasks with infinite loops."),
      HIVEDEFAULTFILEFORMAT("hive.default.fileformat", "TextFile", new Validator.StringSet(new String[]{"TextFile", "SequenceFile", "RCfile", "ORC", "parquet"}), "Default file format for CREATE TABLE statement. Users can explicitly override it by CREATE TABLE ... STORED AS [FORMAT]"),
      HIVEDEFAULTMANAGEDFILEFORMAT("hive.default.fileformat.managed", "none", new Validator.StringSet(new String[]{"none", "TextFile", "SequenceFile", "RCfile", "ORC", "parquet"}), "Default file format for CREATE TABLE statement applied to managed tables only. External tables will be \ncreated with format specified by hive.default.fileformat. Leaving this null will result in using hive.default.fileformat \nfor all tables."),
      HIVEQUERYRESULTFILEFORMAT("hive.query.result.fileformat", "SequenceFile", new Validator.StringSet(new String[]{"TextFile", "SequenceFile", "RCfile", "Llap"}), "Default file format for storing result of the query."),
      HIVECHECKFILEFORMAT("hive.fileformat.check", true, "Whether to check file format or not when loading data files"),
      HIVEDEFAULTRCFILESERDE("hive.default.rcfile.serde", "org.apache.hadoop.hive.serde2.columnar.LazyBinaryColumnarSerDe", "The default SerDe Hive will use for the RCFile format"),
      HIVEDEFAULTSERDE("hive.default.serde", "org.apache.hadoop.hive.serde2.lazy.LazySimpleSerDe", "The default SerDe Hive will use for storage formats that do not specify a SerDe."),
      SERDESUSINGMETASTOREFORSCHEMA("hive.serdes.using.metastore.for.schema", "org.apache.hadoop.hive.ql.io.orc.OrcSerde,org.apache.hadoop.hive.serde2.lazy.LazySimpleSerDe,org.apache.hadoop.hive.serde2.columnar.ColumnarSerDe,org.apache.hadoop.hive.serde2.dynamic_type.DynamicSerDe,org.apache.hadoop.hive.serde2.MetadataTypedColumnsetSerDe,org.apache.hadoop.hive.serde2.columnar.LazyBinaryColumnarSerDe,org.apache.hadoop.hive.ql.io.parquet.serde.ParquetHiveSerDe,org.apache.hadoop.hive.serde2.lazybinary.LazyBinarySerDe", "SerDes retrieving schema from metastore. This is an internal parameter."),
      HIVEHISTORYFILELOC("hive.querylog.location", "${system:java.io.tmpdir}" + File.separator + "${system:user.name}", "Location of Hive run time structured log file"),
      HIVE_LOG_INCREMENTAL_PLAN_PROGRESS("hive.querylog.enable.plan.progress", true, "Whether to log the plan's progress every time a job's progress is checked.\nThese logs are written to the location specified by hive.querylog.location"),
      HIVE_LOG_INCREMENTAL_PLAN_PROGRESS_INTERVAL("hive.querylog.plan.progress.interval", "60000ms", new Validator.TimeValidator(TimeUnit.MILLISECONDS), "The interval to wait between logging the plan's progress.\nIf there is a whole number percentage change in the progress of the mappers or the reducers,\nthe progress is logged regardless of this value.\nThe actual interval will be the ceiling of (this value divided by the value of\nhive.exec.counters.pull.interval) multiplied by the value of hive.exec.counters.pull.interval\nI.e. if it is not divide evenly by the value of hive.exec.counters.pull.interval it will be\nlogged less frequently than specified.\nThis only has an effect if hive.querylog.enable.plan.progress is set to true."),
      HIVESCRIPTSERDE("hive.script.serde", "org.apache.hadoop.hive.serde2.lazy.LazySimpleSerDe", "The default SerDe for transmitting input data to and reading output data from the user scripts. "),
      HIVESCRIPTRECORDREADER("hive.script.recordreader", "org.apache.hadoop.hive.ql.exec.TextRecordReader", "The default record reader for reading data from the user scripts. "),
      HIVESCRIPTRECORDWRITER("hive.script.recordwriter", "org.apache.hadoop.hive.ql.exec.TextRecordWriter", "The default record writer for writing data to the user scripts. "),
      HIVESCRIPTESCAPE("hive.transform.escape.input", false, "This adds an option to escape special chars (newlines, carriage returns and\ntabs) when they are passed to the user script. This is useful if the Hive tables\ncan contain data that contains special characters."),
      HIVEBINARYRECORDMAX("hive.binary.record.max.length", 1000, "Read from a binary stream and treat each hive.binary.record.max.length bytes as a record. \nThe last record before the end of stream can have less than hive.binary.record.max.length bytes"),
      HIVEHADOOPMAXMEM("hive.mapred.local.mem", 0, "mapper/reducer memory in local mode"),
      HIVESMALLTABLESFILESIZE("hive.mapjoin.smalltable.filesize", 25000000L, "The threshold for the input file size of the small tables; if the file size is smaller \nthan this threshold, it will try to convert the common join into map join"),
      HIVE_SCHEMA_EVOLUTION("hive.exec.schema.evolution", true, "Use schema evolution to convert self-describing file format's data to the schema desired by the reader."),
      HIVE_TRANSACTIONAL_TABLE_SCAN("hive.transactional.table.scan", false, "internal usage only -- do transaction (ACID) table scan.", true),
      HIVE_TRANSACTIONAL_NUM_EVENTS_IN_MEMORY("hive.transactional.events.mem", 10000000, "Vectorized ACID readers can often load all the delete events from all the delete deltas\ninto memory to optimize for performance. To prevent out-of-memory errors, this is a rough heuristic\nthat limits the total number of delete events that can be loaded into memory at once.\nRoughly it has been set to 10 million delete events per bucket (~160 MB).\n"),
      HIVESAMPLERANDOMNUM("hive.sample.seednumber", 0, "A number used to percentage sampling. By changing this number, user will change the subsets of data sampled."),
      HIVETESTMODE("hive.test.mode", false, "Whether Hive is running in test mode. If yes, it turns on sampling and prefixes the output tablename.", false),
      HIVETESTMODEPREFIX("hive.test.mode.prefix", "test_", "In test mode, specfies prefixes for the output table", false),
      HIVETESTMODESAMPLEFREQ("hive.test.mode.samplefreq", 32, "In test mode, specfies sampling frequency for table, which is not bucketed,\nFor example, the following query:\n  INSERT OVERWRITE TABLE dest SELECT col1 from src\nwould be converted to\n  INSERT OVERWRITE TABLE test_dest\n  SELECT col1 from src TABLESAMPLE (BUCKET 1 out of 32 on rand(1))", false),
      HIVETESTMODENOSAMPLE("hive.test.mode.nosamplelist", "", "In test mode, specifies comma separated table names which would not apply sampling", false),
      HIVETESTMODEDUMMYSTATAGGR("hive.test.dummystats.aggregator", "", "internal variable for test", false),
      HIVETESTMODEDUMMYSTATPUB("hive.test.dummystats.publisher", "", "internal variable for test", false),
      HIVETESTCURRENTTIMESTAMP("hive.test.currenttimestamp", (Object)null, "current timestamp for test", false),
      HIVETESTMODEROLLBACKTXN("hive.test.rollbacktxn", false, "For testing only.  Will mark every ACID transaction aborted", false),
      HIVETESTMODEFAILCOMPACTION("hive.test.fail.compaction", false, "For testing only.  Will cause CompactorMR to fail.", false),
      HIVETESTMODEFAILHEARTBEATER("hive.test.fail.heartbeater", false, "For testing only.  Will cause Heartbeater to fail.", false),
      HIVEMERGEMAPFILES("hive.merge.mapfiles", true, "Merge small files at the end of a map-only job"),
      HIVEMERGEMAPREDFILES("hive.merge.mapredfiles", false, "Merge small files at the end of a map-reduce job"),
      HIVEMERGETEZFILES("hive.merge.tezfiles", false, "Merge small files at the end of a Tez DAG"),
      HIVEMERGESPARKFILES("hive.merge.sparkfiles", false, "Merge small files at the end of a Spark DAG Transformation"),
      HIVEMERGEMAPFILESSIZE("hive.merge.size.per.task", 256000000L, "Size of merged files at the end of the job"),
      HIVEMERGEMAPFILESAVGSIZE("hive.merge.smallfiles.avgsize", 16000000L, "When the average output file size of a job is less than this number, Hive will start an additional \nmap-reduce job to merge the output files into bigger files. This is only done for map-only jobs \nif hive.merge.mapfiles is true, and for map-reduce jobs if hive.merge.mapredfiles is true."),
      HIVEMERGERCFILEBLOCKLEVEL("hive.merge.rcfile.block.level", true, ""),
      HIVEMERGEORCFILESTRIPELEVEL("hive.merge.orcfile.stripe.level", true, "When hive.merge.mapfiles, hive.merge.mapredfiles or hive.merge.tezfiles is enabled\nwhile writing a table with ORC file format, enabling this config will do stripe-level\nfast merge for small ORC files. Note that enabling this config will not honor the\npadding tolerance config (hive.exec.orc.block.padding.tolerance)."),
      HIVEUSEEXPLICITRCFILEHEADER("hive.exec.rcfile.use.explicit.header", true, "If this is set the header for RCFiles will simply be RCF.  If this is not\nset the header will be that borrowed from sequence files, e.g. SEQ- followed\nby the input and output RCFile formats."),
      HIVEUSERCFILESYNCCACHE("hive.exec.rcfile.use.sync.cache", true, ""),
      HIVE_RCFILE_RECORD_INTERVAL("hive.io.rcfile.record.interval", Integer.MAX_VALUE, ""),
      HIVE_RCFILE_COLUMN_NUMBER_CONF("hive.io.rcfile.column.number.conf", 0, ""),
      HIVE_RCFILE_TOLERATE_CORRUPTIONS("hive.io.rcfile.tolerate.corruptions", false, ""),
      HIVE_RCFILE_RECORD_BUFFER_SIZE("hive.io.rcfile.record.buffer.size", 4194304, ""),
      PARQUET_MEMORY_POOL_RATIO("parquet.memory.pool.ratio", 0.5F, "Maximum fraction of heap that can be used by Parquet file writers in one task.\nIt is for avoiding OutOfMemory error in tasks. Work with Parquet 1.6.0 and above.\nThis config parameter is defined in Parquet, so that it does not start with 'hive.'."),
      HIVE_PARQUET_TIMESTAMP_SKIP_CONVERSION("hive.parquet.timestamp.skip.conversion", true, "Current Hive implementation of parquet stores timestamps to UTC, this flag allows skipping of the conversionon reading parquet files from other tools"),
      HIVE_INT_TIMESTAMP_CONVERSION_IN_SECONDS("hive.int.timestamp.conversion.in.seconds", false, "Boolean/tinyint/smallint/int/bigint value is interpreted as milliseconds during the timestamp conversion.\nSet this flag to true to interpret the value as seconds to be consistent with float/double."),
      HIVE_ORC_BASE_DELTA_RATIO("hive.exec.orc.base.delta.ratio", 8, "The ratio of base writer and\ndelta writer in terms of STRIPE_SIZE and BUFFER_SIZE."),
      HIVE_ORC_SPLIT_STRATEGY("hive.exec.orc.split.strategy", "HYBRID", new Validator.StringSet(new String[]{"HYBRID", "BI", "ETL"}), "This is not a user level config. BI strategy is used when the requirement is to spend less time in split generation as opposed to query execution (split generation does not read or cache file footers). ETL strategy is used when spending little more time in split generation is acceptable (split generation reads and caches file footers). HYBRID chooses between the above strategies based on heuristics."),
      HIVE_ORC_MS_FOOTER_CACHE_ENABLED("hive.orc.splits.ms.footer.cache.enabled", false, "Whether to enable using file metadata cache in metastore for ORC file footers."),
      HIVE_ORC_MS_FOOTER_CACHE_PPD("hive.orc.splits.ms.footer.cache.ppd.enabled", true, "Whether to enable file footer cache PPD (hive.orc.splits.ms.footer.cache.enabled\nmust also be set to true for this to work)."),
      HIVE_ORC_INCLUDE_FILE_FOOTER_IN_SPLITS("hive.orc.splits.include.file.footer", false, "If turned on splits generated by orc will include metadata about the stripes in the file. This\ndata is read remotely (from the client or HS2 machine) and sent to all the tasks."),
      HIVE_ORC_SPLIT_DIRECTORY_BATCH_MS("hive.orc.splits.directory.batch.ms", 0, "How long, in ms, to wait to batch input directories for processing during ORC split\ngeneration. 0 means process directories individually. This can increase the number of\nmetastore calls if metastore metadata cache is used."),
      HIVE_ORC_INCLUDE_FILE_ID_IN_SPLITS("hive.orc.splits.include.fileid", true, "Include file ID in splits on file systems that support it."),
      HIVE_ORC_ALLOW_SYNTHETIC_FILE_ID_IN_SPLITS("hive.orc.splits.allow.synthetic.fileid", true, "Allow synthetic file ID in splits on file systems that don't have a native one."),
      HIVE_ORC_CACHE_STRIPE_DETAILS_MEMORY_SIZE("hive.orc.cache.stripe.details.mem.size", "256Mb", new Validator.SizeValidator(), "Maximum size of orc splits cached in the client."),
      HIVE_ORC_COMPUTE_SPLITS_NUM_THREADS("hive.orc.compute.splits.num.threads", 10, "How many threads orc should use to create splits in parallel."),
      HIVE_ORC_CACHE_USE_SOFT_REFERENCES("hive.orc.cache.use.soft.references", false, "By default, the cache that ORC input format uses to store orc file footer use hard\nreferences for the cached object. Setting this to true can help avoid out of memory\nissues under memory pressure (in some cases) at the cost of slight unpredictability in\noverall query performance."),
      HIVE_IO_SARG_CACHE_MAX_WEIGHT_MB("hive.io.sarg.cache.max.weight.mb", 10, "The max weight allowed for the SearchArgument Cache. By default, the cache allows a max-weight of 10MB, after which entries will be evicted."),
      HIVE_LAZYSIMPLE_EXTENDED_BOOLEAN_LITERAL("hive.lazysimple.extended_boolean_literal", false, "LazySimpleSerde uses this property to determine if it treats 'T', 't', 'F', 'f',\n'1', and '0' as extened, legal boolean literal, in addition to 'TRUE' and 'FALSE'.\nThe default is false, which means only 'TRUE' and 'FALSE' are treated as legal\nboolean literal."),
      HIVESKEWJOIN("hive.optimize.skewjoin", false, "Whether to enable skew join optimization. \nThe algorithm is as follows: At runtime, detect the keys with a large skew. Instead of\nprocessing those keys, store them temporarily in an HDFS directory. In a follow-up map-reduce\njob, process those skewed keys. The same key need not be skewed for all the tables, and so,\nthe follow-up map-reduce job (for the skewed keys) would be much faster, since it would be a\nmap-join."),
      HIVEDYNAMICPARTITIONHASHJOIN("hive.optimize.dynamic.partition.hashjoin", false, "Whether to enable dynamically partitioned hash join optimization. \nThis setting is also dependent on enabling hive.auto.convert.join"),
      HIVECONVERTJOIN("hive.auto.convert.join", true, "Whether Hive enables the optimization about converting common join into mapjoin based on the input file size"),
      HIVECONVERTJOINNOCONDITIONALTASK("hive.auto.convert.join.noconditionaltask", true, "Whether Hive enables the optimization about converting common join into mapjoin based on the input file size. \nIf this parameter is on, and the sum of size for n-1 of the tables/partitions for a n-way join is smaller than the\nspecified size, the join is directly converted to a mapjoin (there is no conditional task)."),
      HIVECONVERTJOINNOCONDITIONALTASKTHRESHOLD("hive.auto.convert.join.noconditionaltask.size", 10000000L, "If hive.auto.convert.join.noconditionaltask is off, this parameter does not take affect. \nHowever, if it is on, and the sum of size for n-1 of the tables/partitions for a n-way join is smaller than this size, \nthe join is directly converted to a mapjoin(there is no conditional task). The default is 10MB"),
      HIVECONVERTJOINUSENONSTAGED("hive.auto.convert.join.use.nonstaged", false, "For conditional joins, if input stream from a small alias can be directly applied to join operator without \nfiltering or projection, the alias need not to be pre-staged in distributed cache via mapred local task.\nCurrently, this is not working with vectorization or tez execution engine."),
      HIVESKEWJOINKEY("hive.skewjoin.key", 100000, "Determine if we get a skew key in join. If we see more than the specified number of rows with the same key in join operator,\nwe think the key as a skew join key. "),
      HIVESKEWJOINMAPJOINNUMMAPTASK("hive.skewjoin.mapjoin.map.tasks", 10000, "Determine the number of map task used in the follow up map join job for a skew join.\nIt should be used together with hive.skewjoin.mapjoin.min.split to perform a fine grained control."),
      HIVESKEWJOINMAPJOINMINSPLIT("hive.skewjoin.mapjoin.min.split", 33554432L, "Determine the number of map task at most used in the follow up map join job for a skew join by specifying \nthe minimum split size. It should be used together with hive.skewjoin.mapjoin.map.tasks to perform a fine grained control."),
      HIVESENDHEARTBEAT("hive.heartbeat.interval", 1000, "Send a heartbeat after this interval - used by mapjoin and filter operators"),
      HIVELIMITMAXROWSIZE("hive.limit.row.max.size", 100000L, "When trying a smaller subset of data for simple LIMIT, how much size we need to guarantee each row to have at least."),
      HIVELIMITOPTLIMITFILE("hive.limit.optimize.limit.file", 10, "When trying a smaller subset of data for simple LIMIT, maximum number of files we can sample."),
      HIVELIMITOPTENABLE("hive.limit.optimize.enable", false, "Whether to enable to optimization to trying a smaller subset of data for simple LIMIT first."),
      HIVELIMITOPTMAXFETCH("hive.limit.optimize.fetch.max", 50000, "Maximum number of rows allowed for a smaller subset of data for simple LIMIT, if it is a fetch query. \nInsert queries are not restricted by this limit."),
      HIVELIMITPUSHDOWNMEMORYUSAGE("hive.limit.pushdown.memory.usage", 0.1F, new Validator.RatioValidator(), "The fraction of available memory to be used for buffering rows in Reducesink operator for limit pushdown optimization."),
      /** @deprecated */
      @Deprecated
      HIVELIMITTABLESCANPARTITION("hive.limit.query.max.table.partition", -1, "This controls how many partitions can be scanned for each partitioned table.\nThe default value \"-1\" means no limit. (DEPRECATED: Please use " + METASTORE_LIMIT_PARTITION_REQUEST + " in the metastore instead.)"),
      HIVECONVERTJOINMAXENTRIESHASHTABLE("hive.auto.convert.join.hashtable.max.entries", 40000000L, "If hive.auto.convert.join.noconditionaltask is off, this parameter does not take affect. \nHowever, if it is on, and the predicated number of entries in hashtable for a given join \ninput is larger than this number, the join will not be converted to a mapjoin. \nThe value \"-1\" means no limit."),
      HIVEHASHTABLEKEYCOUNTADJUSTMENT("hive.hashtable.key.count.adjustment", 1.0F, "Adjustment to mapjoin hashtable size derived from table and column statistics; the estimate of the number of keys is divided by this value. If the value is 0, statistics are not usedand hive.hashtable.initialCapacity is used instead."),
      HIVEHASHTABLETHRESHOLD("hive.hashtable.initialCapacity", 100000, "Initial capacity of mapjoin hashtable if statistics are absent, or if hive.hashtable.key.count.adjustment is set to 0"),
      HIVEHASHTABLELOADFACTOR("hive.hashtable.loadfactor", 0.75F, ""),
      HIVEHASHTABLEFOLLOWBYGBYMAXMEMORYUSAGE("hive.mapjoin.followby.gby.localtask.max.memory.usage", 0.55F, "This number means how much memory the local task can take to hold the key/value into an in-memory hash table \nwhen this map join is followed by a group by. If the local task's memory usage is more than this number, \nthe local task will abort by itself. It means the data of the small table is too large to be held in memory."),
      HIVEHASHTABLEMAXMEMORYUSAGE("hive.mapjoin.localtask.max.memory.usage", 0.9F, "This number means how much memory the local task can take to hold the key/value into an in-memory hash table. \nIf the local task's memory usage is more than this number, the local task will abort by itself. \nIt means the data of the small table is too large to be held in memory."),
      HIVEHASHTABLESCALE("hive.mapjoin.check.memory.rows", 100000L, "The number means after how many rows processed it needs to check the memory usage"),
      HIVEDEBUGLOCALTASK("hive.debug.localtask", false, ""),
      HIVEINPUTFORMAT("hive.input.format", "org.apache.hadoop.hive.ql.io.CombineHiveInputFormat", "The default input format. Set this to HiveInputFormat if you encounter problems with CombineHiveInputFormat."),
      HIVETEZINPUTFORMAT("hive.tez.input.format", "org.apache.hadoop.hive.ql.io.HiveInputFormat", "The default input format for tez. Tez groups splits in the AM."),
      HIVETEZCONTAINERSIZE("hive.tez.container.size", -1, "By default Tez will spawn containers of the size of a mapper. This can be used to overwrite."),
      HIVETEZCPUVCORES("hive.tez.cpu.vcores", -1, "By default Tez will ask for however many cpus map-reduce is configured to use per container.\nThis can be used to overwrite."),
      HIVETEZJAVAOPTS("hive.tez.java.opts", (Object)null, "By default Tez will use the Java options from map tasks. This can be used to overwrite."),
      HIVETEZLOGLEVEL("hive.tez.log.level", "INFO", "The log level to use for tasks executing as part of the DAG.\nUsed only if hive.tez.java.opts is used to configure Java options."),
      HIVETEZHS2USERACCESS("hive.tez.hs2.user.access", true, "Whether to grant access to the hs2/hive user for queries"),
      HIVEQUERYNAME("hive.query.name", (Object)null, "This named is used by Tez to set the dag name. This name in turn will appear on \nthe Tez UI representing the work that was done."),
      HIVEOPTIMIZEBUCKETINGSORTING("hive.optimize.bucketingsorting", true, "Don't create a reducer for enforcing \nbucketing/sorting for queries of the form: \ninsert overwrite table T2 select * from T1;\nwhere T1 and T2 are bucketed/sorted by the same keys into the same number of buckets."),
      HIVEPARTITIONER("hive.mapred.partitioner", "org.apache.hadoop.hive.ql.io.DefaultHivePartitioner", ""),
      HIVEENFORCESORTMERGEBUCKETMAPJOIN("hive.enforce.sortmergebucketmapjoin", false, "If the user asked for sort-merge bucketed map-side join, and it cannot be performed, should the query fail or not ?"),
      HIVEENFORCEBUCKETMAPJOIN("hive.enforce.bucketmapjoin", false, "If the user asked for bucketed map-side join, and it cannot be performed, \nshould the query fail or not ? For example, if the buckets in the tables being joined are\nnot a multiple of each other, bucketed map-side join cannot be performed, and the\nquery will fail if hive.enforce.bucketmapjoin is set to true."),
      HIVE_AUTO_SORTMERGE_JOIN("hive.auto.convert.sortmerge.join", false, "Will the join be automatically converted to a sort-merge join, if the joined tables pass the criteria for sort-merge join."),
      HIVE_AUTO_SORTMERGE_JOIN_REDUCE("hive.auto.convert.sortmerge.join.reduce.side", true, "Whether hive.auto.convert.sortmerge.join (if enabled) should be applied to reduce side."),
      HIVE_AUTO_SORTMERGE_JOIN_BIGTABLE_SELECTOR("hive.auto.convert.sortmerge.join.bigtable.selection.policy", "org.apache.hadoop.hive.ql.optimizer.AvgPartitionSizeBasedBigTableSelectorForAutoSMJ", "The policy to choose the big table for automatic conversion to sort-merge join. \nBy default, the table with the largest partitions is assigned the big table. All policies are:\n. based on position of the table - the leftmost table is selected\norg.apache.hadoop.hive.ql.optimizer.LeftmostBigTableSMJ.\n. based on total size (all the partitions selected in the query) of the table \norg.apache.hadoop.hive.ql.optimizer.TableSizeBasedBigTableSelectorForAutoSMJ.\n. based on average size (all the partitions selected in the query) of the table \norg.apache.hadoop.hive.ql.optimizer.AvgPartitionSizeBasedBigTableSelectorForAutoSMJ.\nNew policies can be added in future."),
      HIVE_AUTO_SORTMERGE_JOIN_TOMAPJOIN("hive.auto.convert.sortmerge.join.to.mapjoin", false, "If hive.auto.convert.sortmerge.join is set to true, and a join was converted to a sort-merge join, \nthis parameter decides whether each table should be tried as a big table, and effectively a map-join should be\ntried. That would create a conditional task with n+1 children for a n-way join (1 child for each table as the\nbig table), and the backup task will be the sort-merge join. In some cases, a map-join would be faster than a\nsort-merge join, if there is no advantage of having the output bucketed and sorted. For example, if a very big sorted\nand bucketed table with few files (say 10 files) are being joined with a very small sorter and bucketed table\nwith few files (10 files), the sort-merge join will only use 10 mappers, and a simple map-only join might be faster\nif the complete small table can fit in memory, and a map-join can be performed."),
      HIVESCRIPTOPERATORTRUST("hive.exec.script.trust", false, ""),
      HIVEROWOFFSET("hive.exec.rowoffset", false, "Whether to provide the row offset virtual column"),
      HIVEOPTINDEXFILTER("hive.optimize.index.filter", false, "Whether to enable automatic use of indexes"),
      HIVEINDEXAUTOUPDATE("hive.optimize.index.autoupdate", false, "Whether to update stale indexes automatically"),
      HIVEOPTPPD("hive.optimize.ppd", true, "Whether to enable predicate pushdown"),
      HIVEOPTPPD_WINDOWING("hive.optimize.ppd.windowing", true, "Whether to enable predicate pushdown through windowing"),
      HIVEPPDRECOGNIZETRANSITIVITY("hive.ppd.recognizetransivity", true, "Whether to transitively replicate predicate filters over equijoin conditions."),
      HIVEPPDREMOVEDUPLICATEFILTERS("hive.ppd.remove.duplicatefilters", true, "During query optimization, filters may be pushed down in the operator tree. \nIf this config is true only pushed down filters remain in the operator tree, \nand the original filter is removed. If this config is false, the original filter \nis also left in the operator tree at the original place."),
      HIVEPOINTLOOKUPOPTIMIZER("hive.optimize.point.lookup", true, "Whether to transform OR clauses in Filter operators into IN clauses"),
      HIVEPOINTLOOKUPOPTIMIZERMIN("hive.optimize.point.lookup.min", 31, "Minimum number of OR clauses needed to transform into IN clauses"),
      HIVEPARTITIONCOLUMNSEPARATOR("hive.optimize.partition.columns.separate", true, "Extract partition columns from IN clauses"),
      HIVEOPTCONSTANTPROPAGATION("hive.optimize.constant.propagation", true, "Whether to enable constant propagation optimizer"),
      HIVEIDENTITYPROJECTREMOVER("hive.optimize.remove.identity.project", true, "Removes identity project from operator tree"),
      HIVEMETADATAONLYQUERIES("hive.optimize.metadataonly", false, "Whether to eliminate scans of the tables from which no columns are selected. Note\nthat, when selecting from empty tables with data files, this can produce incorrect\nresults, so it's disabled by default. It works correctly for normal tables."),
      HIVENULLSCANOPTIMIZE("hive.optimize.null.scan", true, "Dont scan relations which are guaranteed to not generate any rows"),
      HIVEOPTPPD_STORAGE("hive.optimize.ppd.storage", true, "Whether to push predicates down to storage handlers"),
      HIVEOPTGROUPBY("hive.optimize.groupby", true, "Whether to enable the bucketed group by from bucketed partitions/tables."),
      HIVEOPTBUCKETMAPJOIN("hive.optimize.bucketmapjoin", false, "Whether to try bucket mapjoin"),
      HIVEOPTSORTMERGEBUCKETMAPJOIN("hive.optimize.bucketmapjoin.sortedmerge", false, "Whether to try sorted bucket merge map join"),
      HIVEOPTREDUCEDEDUPLICATION("hive.optimize.reducededuplication", true, "Remove extra map-reduce jobs if the data is already clustered by the same key which needs to be used again. \nThis should always be set to true. Since it is a new feature, it has been made configurable."),
      HIVEOPTREDUCEDEDUPLICATIONMINREDUCER("hive.optimize.reducededuplication.min.reducer", 4, "Reduce deduplication merges two RSs by moving key/parts/reducer-num of the child RS to parent RS. \nThat means if reducer-num of the child RS is fixed (order by or forced bucketing) and small, it can make very slow, single MR.\nThe optimization will be automatically disabled if number of reducers would be less than specified value."),
      HIVEOPTSORTDYNAMICPARTITION("hive.optimize.sort.dynamic.partition", false, "When enabled dynamic partitioning column will be globally sorted.\nThis way we can keep only one record writer open for each partition value\nin the reducer thereby reducing the memory pressure on reducers."),
      HIVESAMPLINGFORORDERBY("hive.optimize.sampling.orderby", false, "Uses sampling on order-by clause for parallel execution."),
      HIVESAMPLINGNUMBERFORORDERBY("hive.optimize.sampling.orderby.number", 1000, "Total number of samples to be obtained."),
      HIVESAMPLINGPERCENTFORORDERBY("hive.optimize.sampling.orderby.percent", 0.1F, new Validator.RatioValidator(), "Probability with which a row will be chosen."),
      HIVEOPTIMIZEDISTINCTREWRITE("hive.optimize.distinct.rewrite", true, "When applicable this optimization rewrites distinct aggregates from a single stage to multi-stage aggregation. This may not be optimal in all cases. Ideally, whether to trigger it or not should be cost based decision. Until Hive formalizes cost model for this, this is config driven."),
      HIVE_OPTIMIZE_UNION_REMOVE("hive.optimize.union.remove", false, "Whether to remove the union and push the operators between union and the filesink above union. \nThis avoids an extra scan of the output by union. This is independently useful for union\nqueries, and specially useful when hive.optimize.skewjoin.compiletime is set to true, since an\nextra union is inserted.\n\nThe merge is triggered if either of hive.merge.mapfiles or hive.merge.mapredfiles is set to true.\nIf the user has set hive.merge.mapfiles to true and hive.merge.mapredfiles to false, the idea was the\nnumber of reducers are few, so the number of files anyway are small. However, with this optimization,\nwe are increasing the number of files possibly by a big margin. So, we merge aggressively."),
      HIVEOPTCORRELATION("hive.optimize.correlation", false, "exploit intra-query correlations."),
      HIVE_OPTIMIZE_LIMIT_TRANSPOSE("hive.optimize.limittranspose", false, "Whether to push a limit through left/right outer join or union. If the value is true and the size of the outer\ninput is reduced enough (as specified in hive.optimize.limittranspose.reduction), the limit is pushed\nto the outer input or union; to remain semantically correct, the limit is kept on top of the join or the union too."),
      HIVE_OPTIMIZE_LIMIT_TRANSPOSE_REDUCTION_PERCENTAGE("hive.optimize.limittranspose.reductionpercentage", 1.0F, "When hive.optimize.limittranspose is true, this variable specifies the minimal reduction of the\nsize of the outer input of the join or input of the union that we should get in order to apply the rule."),
      HIVE_OPTIMIZE_LIMIT_TRANSPOSE_REDUCTION_TUPLES("hive.optimize.limittranspose.reductiontuples", 0L, "When hive.optimize.limittranspose is true, this variable specifies the minimal reduction in the\nnumber of tuples of the outer input of the join or the input of the union that you should get in order to apply the rule."),
      HIVE_OPTIMIZE_REDUCE_WITH_STATS("hive.optimize.filter.stats.reduction", false, "Whether to simplify comparison\nexpressions in filter operators using column stats"),
      HIVE_OPTIMIZE_SKEWJOIN_COMPILETIME("hive.optimize.skewjoin.compiletime", false, "Whether to create a separate plan for skewed keys for the tables in the join.\nThis is based on the skewed keys stored in the metadata. At compile time, the plan is broken\ninto different joins: one for the skewed keys, and the other for the remaining keys. And then,\na union is performed for the 2 joins generated above. So unless the same skewed key is present\nin both the joined tables, the join for the skewed key will be performed as a map-side join.\n\nThe main difference between this parameter and hive.optimize.skewjoin is that this parameter\nuses the skew information stored in the metastore to optimize the plan at compile time itself.\nIf there is no skew information in the metadata, this parameter will not have any affect.\nBoth hive.optimize.skewjoin.compiletime and hive.optimize.skewjoin should be set to true.\nIdeally, hive.optimize.skewjoin should be renamed as hive.optimize.skewjoin.runtime, but not doing\nso for backward compatibility.\n\nIf the skew information is correctly stored in the metadata, hive.optimize.skewjoin.compiletime\nwould change the query plan to take care of it, and hive.optimize.skewjoin will be a no-op."),
      HIVE_CTE_MATERIALIZE_THRESHOLD("hive.optimize.cte.materialize.threshold", -1, "If the number of references to a CTE clause exceeds this threshold, Hive will materialize it\nbefore executing the main query block. -1 will disable this feature."),
      HIVEOPTINDEXFILTER_COMPACT_MINSIZE("hive.optimize.index.filter.compact.minsize", 5368709120L, "Minimum size (in bytes) of the inputs on which a compact index is automatically used."),
      HIVEOPTINDEXFILTER_COMPACT_MAXSIZE("hive.optimize.index.filter.compact.maxsize", -1L, "Maximum size (in bytes) of the inputs on which a compact index is automatically used.  A negative number is equivalent to infinity."),
      HIVE_INDEX_COMPACT_QUERY_MAX_ENTRIES("hive.index.compact.query.max.entries", 10000000L, "The maximum number of index entries to read during a query that uses the compact index. Negative value is equivalent to infinity."),
      HIVE_INDEX_COMPACT_QUERY_MAX_SIZE("hive.index.compact.query.max.size", 10737418240L, "The maximum number of bytes that a query using the compact index can read. Negative value is equivalent to infinity."),
      HIVE_INDEX_COMPACT_BINARY_SEARCH("hive.index.compact.binary.search", true, "Whether or not to use a binary search to find the entries in an index table that match the filter, where possible"),
      HIVESTATSAUTOGATHER("hive.stats.autogather", true, "A flag to gather statistics (only basic) automatically during the INSERT OVERWRITE command."),
      HIVESTATSCOLAUTOGATHER("hive.stats.column.autogather", false, "A flag to gather column statistics automatically."),
      HIVESTATSDBCLASS("hive.stats.dbclass", "fs", new Validator.PatternSet(new String[]{"custom", "fs"}), "The storage that stores temporary Hive statistics. In filesystem based statistics collection ('fs'), \neach task writes statistics it has collected in a file on the filesystem, which will be aggregated \nafter the job has finished. Supported values are fs (filesystem) and custom as defined in StatsSetupConst.java."),
      HIVE_STATS_DEFAULT_PUBLISHER("hive.stats.default.publisher", "", "The Java class (implementing the StatsPublisher interface) that is used by default if hive.stats.dbclass is custom type."),
      HIVE_STATS_DEFAULT_AGGREGATOR("hive.stats.default.aggregator", "", "The Java class (implementing the StatsAggregator interface) that is used by default if hive.stats.dbclass is custom type."),
      HIVE_STATS_ATOMIC("hive.stats.atomic", false, "whether to update metastore stats only if all stats are available"),
      CLIENT_STATS_COUNTERS("hive.client.stats.counters", "", "Subset of counters that should be of interest for hive.client.stats.publishers (when one wants to limit their publishing). \nNon-display names should be used"),
      HIVE_STATS_RELIABLE("hive.stats.reliable", false, "Whether queries will fail because stats cannot be collected completely accurately. \nIf this is set to true, reading/writing from/into a partition may fail because the stats\ncould not be computed accurately."),
      HIVE_STATS_COLLECT_PART_LEVEL_STATS("hive.analyze.stmt.collect.partlevel.stats", true, "analyze table T compute statistics for columns. Queries like these should compute partitionlevel stats for partitioned table even when no part spec is specified."),
      HIVE_STATS_GATHER_NUM_THREADS("hive.stats.gather.num.threads", 10, "Number of threads used by partialscan/noscan analyze command for partitioned tables.\nThis is applicable only for file formats that implement StatsProvidingRecordReader (like ORC)."),
      HIVE_STATS_COLLECT_TABLEKEYS("hive.stats.collect.tablekeys", false, "Whether join and group by keys on tables are derived and maintained in the QueryPlan.\nThis is useful to identify how tables are accessed and to determine if they should be bucketed."),
      HIVE_STATS_COLLECT_SCANCOLS("hive.stats.collect.scancols", false, "Whether column accesses are tracked in the QueryPlan.\nThis is useful to identify how tables are accessed and to determine if there are wasted columns that can be trimmed."),
      HIVE_STATS_NDV_ERROR("hive.stats.ndv.error", 20.0F, "Standard error expressed in percentage. Provides a tradeoff between accuracy and compute cost. \nA lower value for error indicates higher accuracy and a higher compute cost."),
      HIVE_METASTORE_STATS_NDV_TUNER("hive.metastore.stats.ndv.tuner", 0.0F, "Provides a tunable parameter between the lower bound and the higher bound of ndv for aggregate ndv across all the partitions. \nThe lower bound is equal to the maximum of ndv of all the partitions. The higher bound is equal to the sum of ndv of all the partitions.\nIts value should be between 0.0 (i.e., choose lower bound) and 1.0 (i.e., choose higher bound)"),
      HIVE_METASTORE_STATS_NDV_DENSITY_FUNCTION("hive.metastore.stats.ndv.densityfunction", false, "Whether to use density function to estimate the NDV for the whole table based on the NDV of partitions"),
      HIVE_STATS_KEY_PREFIX("hive.stats.key.prefix", "", "", true),
      HIVE_STATS_MAX_VARIABLE_LENGTH("hive.stats.max.variable.length", 100, "To estimate the size of data flowing through operators in Hive/Tez(for reducer estimation etc.),\naverage row size is multiplied with the total number of rows coming out of each operator.\nAverage row size is computed from average column size of all columns in the row. In the absence\nof column statistics, for variable length columns (like string, bytes etc.), this value will be\nused. For fixed length columns their corresponding Java equivalent sizes are used\n(float - 4 bytes, double - 8 bytes etc.)."),
      HIVE_STATS_LIST_NUM_ENTRIES("hive.stats.list.num.entries", 10, "To estimate the size of data flowing through operators in Hive/Tez(for reducer estimation etc.),\naverage row size is multiplied with the total number of rows coming out of each operator.\nAverage row size is computed from average column size of all columns in the row. In the absence\nof column statistics and for variable length complex columns like list, the average number of\nentries/values can be specified using this config."),
      HIVE_STATS_MAP_NUM_ENTRIES("hive.stats.map.num.entries", 10, "To estimate the size of data flowing through operators in Hive/Tez(for reducer estimation etc.),\naverage row size is multiplied with the total number of rows coming out of each operator.\nAverage row size is computed from average column size of all columns in the row. In the absence\nof column statistics and for variable length complex columns like map, the average number of\nentries/values can be specified using this config."),
      HIVE_STATS_FETCH_PARTITION_STATS("hive.stats.fetch.partition.stats", true, "Annotation of operator tree with statistics information requires partition level basic\nstatistics like number of rows, data size and file size. Partition statistics are fetched from\nmetastore. Fetching partition statistics for each needed partition can be expensive when the\nnumber of partitions is high. This flag can be used to disable fetching of partition statistics\nfrom metastore. When this flag is disabled, Hive will make calls to filesystem to get file sizes\nand will estimate the number of rows from row schema."),
      HIVE_STATS_FETCH_COLUMN_STATS("hive.stats.fetch.column.stats", false, "Annotation of operator tree with statistics information requires column statistics.\nColumn statistics are fetched from metastore. Fetching column statistics for each needed column\ncan be expensive when the number of columns is high. This flag can be used to disable fetching\nof column statistics from metastore."),
      HIVE_STATS_JOIN_FACTOR("hive.stats.join.factor", 1.1F, "Hive/Tez optimizer estimates the data size flowing through each of the operators. JOIN operator\nuses column statistics to estimate the number of rows flowing out of it and hence the data size.\nIn the absence of column statistics, this factor determines the amount of rows that flows out\nof JOIN operator."),
      HIVE_STATS_DESERIALIZATION_FACTOR("hive.stats.deserialization.factor", 1.0F, "Hive/Tez optimizer estimates the data size flowing through each of the operators. In the absence\nof basic statistics like number of rows and data size, file size is used to estimate the number\nof rows and data size. Since files in tables/partitions are serialized (and optionally\ncompressed) the estimates of number of rows and data size cannot be reliably determined.\nThis factor is multiplied with the file size to account for serialization and compression."),
      HIVE_STATS_IN_CLAUSE_FACTOR("hive.stats.filter.in.factor", 1.0F, "Currently column distribution is assumed to be uniform. This can lead to overestimation/underestimation\nin the number of rows filtered by a certain operator, which in turn might lead to overprovision or\nunderprovision of resources. This factor is applied to the cardinality estimation of IN clauses in\nfilter operators."),
      HIVE_SUPPORT_CONCURRENCY("hive.support.concurrency", false, "Whether Hive supports concurrency control or not. \nA ZooKeeper instance must be up and running when using zookeeper Hive lock manager "),
      HIVE_LOCK_MANAGER("hive.lock.manager", "org.apache.hadoop.hive.ql.lockmgr.zookeeper.ZooKeeperHiveLockManager", ""),
      HIVE_LOCK_NUMRETRIES("hive.lock.numretries", 100, "The number of times you want to try to get all the locks"),
      HIVE_UNLOCK_NUMRETRIES("hive.unlock.numretries", 10, "The number of times you want to retry to do one unlock"),
      HIVE_LOCK_SLEEP_BETWEEN_RETRIES("hive.lock.sleep.between.retries", "60s", new Validator.TimeValidator(TimeUnit.SECONDS, 0L, false, Long.MAX_VALUE, false), "The maximum sleep time between various retries"),
      HIVE_LOCK_MAPRED_ONLY("hive.lock.mapred.only.operation", false, "This param is to control whether or not only do lock on queries\nthat need to execute at least one mapred job."),
      HIVE_ZOOKEEPER_QUORUM("hive.zookeeper.quorum", "", "List of ZooKeeper servers to talk to. This is needed for: \n1. Read/write locks - when hive.lock.manager is set to \norg.apache.hadoop.hive.ql.lockmgr.zookeeper.ZooKeeperHiveLockManager, \n2. When HiveServer2 supports service discovery via Zookeeper.\n3. For delegation token storage if zookeeper store is used, if\nhive.cluster.delegation.token.store.zookeeper.connectString is not set\n4. LLAP daemon registry service"),
      HIVE_ZOOKEEPER_CLIENT_PORT("hive.zookeeper.client.port", "2181", "The port of ZooKeeper servers to talk to.\nIf the list of Zookeeper servers specified in hive.zookeeper.quorum\ndoes not contain port numbers, this value is used."),
      HIVE_ZOOKEEPER_SESSION_TIMEOUT("hive.zookeeper.session.timeout", "1200000ms", new Validator.TimeValidator(TimeUnit.MILLISECONDS), "ZooKeeper client's session timeout (in milliseconds). The client is disconnected, and as a result, all locks released, \nif a heartbeat is not sent in the timeout."),
      HIVE_ZOOKEEPER_NAMESPACE("hive.zookeeper.namespace", "hive_zookeeper_namespace", "The parent node under which all ZooKeeper nodes are created."),
      HIVE_ZOOKEEPER_CLEAN_EXTRA_NODES("hive.zookeeper.clean.extra.nodes", false, "Clean extra nodes at the end of the session."),
      HIVE_ZOOKEEPER_CONNECTION_MAX_RETRIES("hive.zookeeper.connection.max.retries", 3, "Max number of times to retry when connecting to the ZooKeeper server."),
      HIVE_ZOOKEEPER_CONNECTION_BASESLEEPTIME("hive.zookeeper.connection.basesleeptime", "1000ms", new Validator.TimeValidator(TimeUnit.MILLISECONDS), "Initial amount of time (in milliseconds) to wait between retries\nwhen connecting to the ZooKeeper server when using ExponentialBackoffRetry policy."),
      HIVE_TXN_MANAGER("hive.txn.manager", "org.apache.hadoop.hive.ql.lockmgr.DummyTxnManager", "Set to org.apache.hadoop.hive.ql.lockmgr.DbTxnManager as part of turning on Hive\ntransactions, which also requires appropriate settings for hive.compactor.initiator.on,\nhive.compactor.worker.threads, hive.support.concurrency (true),\nand hive.exec.dynamic.partition.mode (nonstrict).\nThe default DummyTxnManager replicates pre-Hive-0.13 behavior and provides\nno transactions."),
      HIVE_TXN_STRICT_LOCKING_MODE("hive.txn.strict.locking.mode", true, "In strict mode non-ACID\nresources use standard R/W lock semantics, e.g. INSERT will acquire exclusive lock.\nIn nonstrict mode, for non-ACID resources, INSERT will only acquire shared lock, which\nallows two concurrent writes to the same partition but still lets lock manager prevent\nDROP TABLE etc. when the table is being written to"),
      HIVE_TXN_TIMEOUT("hive.txn.timeout", "300s", new Validator.TimeValidator(TimeUnit.SECONDS), "time after which transactions are declared aborted if the client has not sent a heartbeat."),
      HIVE_TXN_HEARTBEAT_THREADPOOL_SIZE("hive.txn.heartbeat.threadpool.size", 5, "The number of threads to use for heartbeating. For Hive CLI, 1 is enough. For HiveServer2, we need a few"),
      TXN_MGR_DUMP_LOCK_STATE_ON_ACQUIRE_TIMEOUT("hive.txn.manager.dump.lock.state.on.acquire.timeout", false, "Set this to true so that when attempt to acquire a lock on resource times out, the current state of the lock manager is dumped to log file.  This is for debugging.  See also hive.lock.numretries and hive.lock.sleep.between.retries."),
      HIVE_TXN_OPERATIONAL_PROPERTIES("hive.txn.operational.properties", 0, "Sets the operational properties that control the appropriate behavior for various\nversions of the Hive ACID subsystem. Setting it to zero will turn on the legacy mode\nfor ACID, while setting it to one will enable a split-update feature found in the newer\nversion of Hive ACID subsystem. Mostly it is intended to be used as an internal property\nfor future versions of ACID. (See HIVE-14035 for details.)"),
      HIVE_MAX_OPEN_TXNS("hive.max.open.txns", 100000, "Maximum number of open transactions. If \ncurrent open transactions reach this limit, future open transaction requests will be \nrejected, until this number goes below the limit."),
      HIVE_COUNT_OPEN_TXNS_INTERVAL("hive.count.open.txns.interval", "1s", new Validator.TimeValidator(TimeUnit.SECONDS), "Time in seconds between checks to count open transactions."),
      HIVE_TXN_MAX_OPEN_BATCH("hive.txn.max.open.batch", 1000, "Maximum number of transactions that can be fetched in one call to open_txns().\nThis controls how many transactions streaming agents such as Flume or Storm open\nsimultaneously. The streaming agent then writes that number of entries into a single\nfile (per Flume agent or Storm bolt). Thus increasing this value decreases the number\nof delta files created by streaming agents. But it also increases the number of open\ntransactions that Hive has to track at any given time, which may negatively affect\nread performance."),
      HIVE_TXN_RETRYABLE_SQLEX_REGEX("hive.txn.retryable.sqlex.regex", "", "Comma separated list\nof regular expression patterns for SQL state, error code, and error message of\nretryable SQLExceptions, that's suitable for the metastore DB.\nFor example: Can't serialize.*,40001$,^Deadlock,.*ORA-08176.*\nThe string that the regex will be matched against is of the following form, where ex is a SQLException:\nex.getMessage() + \" (SQLState=\" + ex.getSQLState() + \", ErrorCode=\" + ex.getErrorCode() + \")\""),
      HIVE_COMPACTOR_INITIATOR_ON("hive.compactor.initiator.on", false, "Whether to run the initiator and cleaner threads on this metastore instance or not.\nSet this to true on one instance of the Thrift metastore service as part of turning\non Hive transactions. For a complete list of parameters required for turning on\ntransactions, see hive.txn.manager."),
      HIVE_COMPACTOR_WORKER_THREADS("hive.compactor.worker.threads", 0, "How many compactor worker threads to run on this metastore instance. Set this to a\npositive number on one or more instances of the Thrift metastore service as part of\nturning on Hive transactions. For a complete list of parameters required for turning\non transactions, see hive.txn.manager.\nWorker threads spawn MapReduce jobs to do compactions. They do not do the compactions\nthemselves. Increasing the number of worker threads will decrease the time it takes\ntables or partitions to be compacted once they are determined to need compaction.\nIt will also increase the background load on the Hadoop cluster as more MapReduce jobs\nwill be running in the background."),
      HIVE_COMPACTOR_WORKER_TIMEOUT("hive.compactor.worker.timeout", "86400s", new Validator.TimeValidator(TimeUnit.SECONDS), "Time in seconds after which a compaction job will be declared failed and the\ncompaction re-queued."),
      HIVE_COMPACTOR_CHECK_INTERVAL("hive.compactor.check.interval", "300s", new Validator.TimeValidator(TimeUnit.SECONDS), "Time in seconds between checks to see if any tables or partitions need to be\ncompacted. This should be kept high because each check for compaction requires\nmany calls against the NameNode.\nDecreasing this value will reduce the time it takes for compaction to be started\nfor a table or partition that requires compaction. However, checking if compaction\nis needed requires several calls to the NameNode for each table or partition that\nhas had a transaction done on it since the last major compaction. So decreasing this\nvalue will increase the load on the NameNode."),
      HIVE_COMPACTOR_DELTA_NUM_THRESHOLD("hive.compactor.delta.num.threshold", 10, "Number of delta directories in a table or partition that will trigger a minor\ncompaction."),
      HIVE_COMPACTOR_DELTA_PCT_THRESHOLD("hive.compactor.delta.pct.threshold", 0.1F, "Percentage (fractional) size of the delta files relative to the base that will trigger\na major compaction. (1.0 = 100%, so the default 0.1 = 10%.)"),
      COMPACTOR_MAX_NUM_DELTA("hive.compactor.max.num.delta", 500, "Maximum number of delta files that the compactor will attempt to handle in a single job."),
      HIVE_COMPACTOR_ABORTEDTXN_THRESHOLD("hive.compactor.abortedtxn.threshold", 1000, "Number of aborted transactions involving a given table or partition that will trigger\na major compaction."),
      COMPACTOR_INITIATOR_FAILED_THRESHOLD("hive.compactor.initiator.failed.compacts.threshold", 2, new Validator.RangeValidator(1, 20), "Number of consecutive compaction failures (per table/partition) after which automatic compactions will not be scheduled any more.  Note that this must be less than hive.compactor.history.retention.failed."),
      HIVE_COMPACTOR_CLEANER_RUN_INTERVAL("hive.compactor.cleaner.run.interval", "5000ms", new Validator.TimeValidator(TimeUnit.MILLISECONDS), "Time between runs of the cleaner thread"),
      COMPACTOR_JOB_QUEUE("hive.compactor.job.queue", "", "Used to specify name of Hadoop queue to which\nCompaction jobs will be submitted.  Set to empty string to let Hadoop choose the queue."),
      COMPACTOR_HISTORY_RETENTION_SUCCEEDED("hive.compactor.history.retention.succeeded", 3, new Validator.RangeValidator(0, 100), "Determines how many successful compaction records will be retained in compaction history for a given table/partition."),
      COMPACTOR_HISTORY_RETENTION_FAILED("hive.compactor.history.retention.failed", 3, new Validator.RangeValidator(0, 100), "Determines how many failed compaction records will be retained in compaction history for a given table/partition."),
      COMPACTOR_HISTORY_RETENTION_ATTEMPTED("hive.compactor.history.retention.attempted", 2, new Validator.RangeValidator(0, 100), "Determines how many attempted compaction records will be retained in compaction history for a given table/partition."),
      COMPACTOR_HISTORY_REAPER_INTERVAL("hive.compactor.history.reaper.interval", "2m", new Validator.TimeValidator(TimeUnit.MILLISECONDS), "Determines how often compaction history reaper runs"),
      HIVE_TIMEDOUT_TXN_REAPER_START("hive.timedout.txn.reaper.start", "100s", new Validator.TimeValidator(TimeUnit.MILLISECONDS), "Time delay of 1st reaper run after metastore start"),
      HIVE_TIMEDOUT_TXN_REAPER_INTERVAL("hive.timedout.txn.reaper.interval", "180s", new Validator.TimeValidator(TimeUnit.MILLISECONDS), "Time interval describing how often the reaper runs"),
      WRITE_SET_REAPER_INTERVAL("hive.writeset.reaper.interval", "60s", new Validator.TimeValidator(TimeUnit.MILLISECONDS), "Frequency of WriteSet reaper runs"),
      MERGE_CARDINALITY_VIOLATION_CHECK("hive.merge.cardinality.check", true, "Set to true to ensure that each SQL Merge statement ensures that for each row in the target\ntable there is at most 1 matching row in the source table per SQL Specification."),
      HIVE_DRUID_INDEXING_GRANULARITY("hive.druid.indexer.segments.granularity", "DAY", new Validator.PatternSet(new String[]{"YEAR", "MONTH", "WEEK", "DAY", "HOUR", "MINUTE", "SECOND"}), "Granularity for the segments created by the Druid storage handler"),
      HIVE_DRUID_MAX_PARTITION_SIZE("hive.druid.indexer.partition.size.max", 5000000, "Maximum number of records per segment partition"),
      HIVE_DRUID_MAX_ROW_IN_MEMORY("hive.druid.indexer.memory.rownum.max", 75000, "Maximum number of records in memory while storing data in Druid"),
      HIVE_DRUID_BROKER_DEFAULT_ADDRESS("hive.druid.broker.address.default", "localhost:8082", "Address of the Druid broker. If we are querying Druid from Hive, this address needs to be\ndeclared"),
      HIVE_DRUID_COORDINATOR_DEFAULT_ADDRESS("hive.druid.coordinator.address.default", "localhost:8081", "Address of the Druid coordinator. It is used to check the load status of newly created segments"),
      HIVE_DRUID_SELECT_DISTRIBUTE("hive.druid.select.distribute", true, "If it is set to true, we distribute the execution of Druid Select queries. Concretely, we retrieve\nthe result for Select queries directly from the Druid nodes containing the segments data.\nIn particular, first we contact the Druid broker node to obtain the nodes containing the segments\nfor the given query, and then we contact those nodes to retrieve the results for the query.\nIf it is set to false, we do not execute the Select queries in a distributed fashion. Instead, results\nfor those queries are returned by the Druid broker node."),
      HIVE_DRUID_SELECT_THRESHOLD("hive.druid.select.threshold", 10000, "Takes only effect when hive.druid.select.distribute is set to false. \nWhen we can split a Select query, this is the maximum number of rows that we try to retrieve\nper query. In order to do that, we obtain the estimated size for the complete result. If the\nnumber of records of the query results is larger than this threshold, we split the query in\ntotal number of rows/threshold parts across the time dimension. Note that we assume the\nrecords to be split uniformly across the time dimension."),
      HIVE_DRUID_NUM_HTTP_CONNECTION("hive.druid.http.numConnection", 20, "Number of connections used by\nthe HTTP client."),
      HIVE_DRUID_HTTP_READ_TIMEOUT("hive.druid.http.read.timeout", "PT1M", "Read timeout period for the HTTP\nclient in ISO8601 format (for example P2W, P3M, PT1H30M, PT0.750S), default is period of 1 minute."),
      HIVE_DRUID_SLEEP_TIME("hive.druid.sleep.time", "PT10S", "Sleep time between retries in ISO8601 format (for example P2W, P3M, PT1H30M, PT0.750S), default is period of 10 seconds."),
      HIVE_DRUID_BASE_PERSIST_DIRECTORY("hive.druid.basePersistDirectory", "", "Local temporary directory used to persist intermediate indexing state, will default to JVM system property java.io.tmpdir."),
      DRUID_SEGMENT_DIRECTORY("hive.druid.storage.storageDirectory", "/druid/segments", "druid deep storage location."),
      DRUID_METADATA_BASE("hive.druid.metadata.base", "druid", "Default prefix for metadata tables"),
      DRUID_METADATA_DB_TYPE("hive.druid.metadata.db.type", "mysql", new Validator.PatternSet(new String[]{"mysql", "postgresql"}), "Type of the metadata database."),
      DRUID_METADATA_DB_USERNAME("hive.druid.metadata.username", "", "Username to connect to Type of the metadata DB."),
      DRUID_METADATA_DB_PASSWORD("hive.druid.metadata.password", "", "Password to connect to Type of the metadata DB."),
      DRUID_METADATA_DB_URI("hive.druid.metadata.uri", "", "URI to connect to the database (for example jdbc:mysql://hostname:port/DBName)."),
      DRUID_WORKING_DIR("hive.druid.working.directory", "/tmp/workingDirectory", "Default hdfs working directory used to store some intermediate metadata"),
      HIVE_DRUID_MAX_TRIES("hive.druid.maxTries", 5, "Maximum number of retries before giving up"),
      HIVE_DRUID_PASSIVE_WAIT_TIME("hive.druid.passiveWaitTimeMs", 30000, "Wait time in ms default to 30 seconds."),
      HIVE_HBASE_WAL_ENABLED("hive.hbase.wal.enabled", true, "Whether writes to HBase should be forced to the write-ahead log. \nDisabling this improves HBase write performance at the risk of lost writes in case of a crash."),
      HIVE_HBASE_GENERATE_HFILES("hive.hbase.generatehfiles", false, "True when HBaseStorageHandler should generate hfiles instead of operate against the online table."),
      HIVE_HBASE_SNAPSHOT_NAME("hive.hbase.snapshot.name", (Object)null, "The HBase table snapshot name to use."),
      HIVE_HBASE_SNAPSHOT_RESTORE_DIR("hive.hbase.snapshot.restoredir", "/tmp", "The directory in which to restore the HBase table snapshot."),
      HIVEARCHIVEENABLED("hive.archive.enabled", false, "Whether archiving operations are permitted"),
      HIVEOPTGBYUSINGINDEX("hive.optimize.index.groupby", false, "Whether to enable optimization of group-by queries using Aggregate indexes."),
      HIVEFETCHTASKCONVERSION("hive.fetch.task.conversion", "more", new Validator.StringSet(new String[]{"none", "minimal", "more"}), "Some select queries can be converted to single FETCH task minimizing latency.\nCurrently the query should be single sourced not having any subquery and should not have\nany aggregations or distincts (which incurs RS), lateral views and joins.\n0. none : disable hive.fetch.task.conversion\n1. minimal : SELECT STAR, FILTER on partition columns, LIMIT only\n2. more    : SELECT, FILTER, LIMIT only (support TABLESAMPLE and virtual columns)"),
      HIVEFETCHTASKCONVERSIONTHRESHOLD("hive.fetch.task.conversion.threshold", 1073741824L, "Input threshold for applying hive.fetch.task.conversion. If target table is native, input length\nis calculated by summation of file lengths. If it's not native, storage handler for the table\ncan optionally implement org.apache.hadoop.hive.ql.metadata.InputEstimator interface."),
      HIVEFETCHTASKAGGR("hive.fetch.task.aggr", false, "Aggregation queries with no group-by clause (for example, select count(*) from src) execute\nfinal aggregations in single reduce task. If this is set true, Hive delegates final aggregation\nstage to fetch task, possibly decreasing the query time."),
      HIVEOPTIMIZEMETADATAQUERIES("hive.compute.query.using.stats", true, "When set to true Hive will answer a few queries like count(1) purely using stats\nstored in metastore. For basic stats collection turn on the config hive.stats.autogather to true.\nFor more advanced stats collection need to run analyze table queries."),
      HIVEFETCHOUTPUTSERDE("hive.fetch.output.serde", "org.apache.hadoop.hive.serde2.DelimitedJSONSerDe", "The SerDe used by FetchTask to serialize the fetch output."),
      HIVEEXPREVALUATIONCACHE("hive.cache.expr.evaluation", true, "If true, the evaluation result of a deterministic expression referenced twice or more\nwill be cached.\nFor example, in a filter condition like '.. where key + 10 = 100 or key + 10 = 0'\nthe expression 'key + 10' will be evaluated/cached once and reused for the following\nexpression ('key + 10 = 0'). Currently, this is applied only to expressions in select\nor filter operators."),
      HIVEVARIABLESUBSTITUTE("hive.variable.substitute", true, "This enables substitution using syntax like ${var} ${system:var} and ${env:var}."),
      HIVEVARIABLESUBSTITUTEDEPTH("hive.variable.substitute.depth", 40, "The maximum replacements the substitution engine will do."),
      HIVECONFVALIDATION("hive.conf.validation", true, "Enables type checking for registered Hive configurations"),
      SEMANTIC_ANALYZER_HOOK("hive.semantic.analyzer.hook", "", ""),
      HIVE_TEST_AUTHORIZATION_SQLSTD_HS2_MODE("hive.test.authz.sstd.hs2.mode", false, "test hs2 mode from .q tests", true),
      HIVE_AUTHORIZATION_ENABLED("hive.security.authorization.enabled", false, "enable or disable the Hive client authorization"),
      HIVE_AUTHORIZATION_MANAGER("hive.security.authorization.manager", "org.apache.hadoop.hive.ql.security.authorization.plugin.sqlstd.SQLStdHiveAuthorizerFactory", "The Hive client authorization manager class name. The user defined authorization class should implement \ninterface org.apache.hadoop.hive.ql.security.authorization.HiveAuthorizationProvider."),
      HIVE_AUTHENTICATOR_MANAGER("hive.security.authenticator.manager", "org.apache.hadoop.hive.ql.security.HadoopDefaultAuthenticator", "hive client authenticator manager class name. The user defined authenticator should implement \ninterface org.apache.hadoop.hive.ql.security.HiveAuthenticationProvider."),
      HIVE_METASTORE_AUTHORIZATION_MANAGER("hive.security.metastore.authorization.manager", "org.apache.hadoop.hive.ql.security.authorization.DefaultHiveMetastoreAuthorizationProvider", "Names of authorization manager classes (comma separated) to be used in the metastore\nfor authorization. The user defined authorization class should implement interface\norg.apache.hadoop.hive.ql.security.authorization.HiveMetastoreAuthorizationProvider.\nAll authorization manager classes have to successfully authorize the metastore API\ncall for the command execution to be allowed."),
      HIVE_METASTORE_AUTHORIZATION_AUTH_READS("hive.security.metastore.authorization.auth.reads", true, "If this is true, metastore authorizer authorizes read actions on database, table"),
      HIVE_METASTORE_AUTHENTICATOR_MANAGER("hive.security.metastore.authenticator.manager", "org.apache.hadoop.hive.ql.security.HadoopDefaultMetastoreAuthenticator", "authenticator manager class name to be used in the metastore for authentication. \nThe user defined authenticator should implement interface org.apache.hadoop.hive.ql.security.HiveAuthenticationProvider."),
      HIVE_AUTHORIZATION_TABLE_USER_GRANTS("hive.security.authorization.createtable.user.grants", "", "the privileges automatically granted to some users whenever a table gets created.\nAn example like \"userX,userY:select;userZ:create\" will grant select privilege to userX and userY,\nand grant create privilege to userZ whenever a new table created."),
      HIVE_AUTHORIZATION_TABLE_GROUP_GRANTS("hive.security.authorization.createtable.group.grants", "", "the privileges automatically granted to some groups whenever a table gets created.\nAn example like \"groupX,groupY:select;groupZ:create\" will grant select privilege to groupX and groupY,\nand grant create privilege to groupZ whenever a new table created."),
      HIVE_AUTHORIZATION_TABLE_ROLE_GRANTS("hive.security.authorization.createtable.role.grants", "", "the privileges automatically granted to some roles whenever a table gets created.\nAn example like \"roleX,roleY:select;roleZ:create\" will grant select privilege to roleX and roleY,\nand grant create privilege to roleZ whenever a new table created."),
      HIVE_AUTHORIZATION_TABLE_OWNER_GRANTS("hive.security.authorization.createtable.owner.grants", "", "The privileges automatically granted to the owner whenever a table gets created.\nAn example like \"select,drop\" will grant select and drop privilege to the owner\nof the table. Note that the default gives the creator of a table no access to the\ntable (but see HIVE-8067)."),
      HIVE_AUTHORIZATION_TASK_FACTORY("hive.security.authorization.task.factory", "org.apache.hadoop.hive.ql.parse.authorization.HiveAuthorizationTaskFactoryImpl", "Authorization DDL task factory implementation"),
      HIVE_AUTHORIZATION_SQL_STD_AUTH_CONFIG_WHITELIST("hive.security.authorization.sqlstd.confwhitelist", "", "List of comma separated Java regexes. Configurations parameters that match these\nregexes can be modified by user when SQL standard authorization is enabled.\nTo get the default value, use the 'set <param>' command.\nNote that the hive.conf.restricted.list checks are still enforced after the white list\ncheck"),
      HIVE_AUTHORIZATION_SQL_STD_AUTH_CONFIG_WHITELIST_APPEND("hive.security.authorization.sqlstd.confwhitelist.append", "", "List of comma separated Java regexes, to be appended to list set in\nhive.security.authorization.sqlstd.confwhitelist. Using this list instead\nof updating the original list means that you can append to the defaults\nset by SQL standard authorization instead of replacing it entirely."),
      HIVE_CLI_PRINT_HEADER("hive.cli.print.header", false, "Whether to print the names of the columns in query output."),
      HIVE_CLI_TEZ_SESSION_ASYNC("hive.cli.tez.session.async", true, "Whether to start Tez\nsession in background when running CLI with Tez, allowing CLI to be available earlier."),
      HIVE_ERROR_ON_EMPTY_PARTITION("hive.error.on.empty.partition", false, "Whether to throw an exception if dynamic partition insert generates empty results."),
      HIVE_INDEX_COMPACT_FILE("hive.index.compact.file", "", "internal variable"),
      HIVE_INDEX_BLOCKFILTER_FILE("hive.index.blockfilter.file", "", "internal variable"),
      HIVE_INDEX_IGNORE_HDFS_LOC("hive.index.compact.file.ignore.hdfs", false, "When true the HDFS location stored in the index file will be ignored at runtime.\nIf the data got moved or the name of the cluster got changed, the index data should still be usable."),
      HIVE_EXIM_URI_SCHEME_WL("hive.exim.uri.scheme.whitelist", "hdfs,pfile,file,s3,s3a", "A comma separated list of acceptable URI schemes for import and export."),
      HIVE_EXIM_RESTRICT_IMPORTS_INTO_REPLICATED_TABLES("hive.exim.strict.repl.tables", true, "Parameter that determines if 'regular' (non-replication) export dumps can be\nimported on to tables that are the target of replication. If this parameter is\nset, regular imports will check if the destination table(if it exists) has a 'repl.last.id' set on it. If so, it will fail."),
      HIVE_REPL_TASK_FACTORY("hive.repl.task.factory", "org.apache.hive.hcatalog.api.repl.exim.EximReplicationTaskFactory", "Parameter that can be used to override which ReplicationTaskFactory will be\nused to instantiate ReplicationTask events. Override for third party repl plugins"),
      HIVE_MAPPER_CANNOT_SPAN_MULTIPLE_PARTITIONS("hive.mapper.cannot.span.multiple.partitions", false, ""),
      HIVE_REWORK_MAPREDWORK("hive.rework.mapredwork", false, "should rework the mapred work or not.\nThis is first introduced by SymlinkTextInputFormat to replace symlink files with real paths at compile time."),
      HIVE_CONCATENATE_CHECK_INDEX("hive.exec.concatenate.check.index", true, "If this is set to true, Hive will throw error when doing\n'alter table tbl_name [partSpec] concatenate' on a table/partition\nthat has indexes on it. The reason the user want to set this to true\nis because it can help user to avoid handling all index drop, recreation,\nrebuild work. This is very helpful for tables with thousands of partitions."),
      HIVE_IO_EXCEPTION_HANDLERS("hive.io.exception.handlers", "", "A list of io exception handler class names. This is used\nto construct a list exception handlers to handle exceptions thrown\nby record readers"),
      HIVE_LOG4J_FILE("hive.log4j.file", "", "Hive log4j configuration file.\nIf the property is not set, then logging will be initialized using hive-log4j2.properties found on the classpath.\nIf the property is set, the value must be a valid URI (java.net.URI, e.g. \"file:///tmp/my-logging.xml\"), \nwhich you can then extract a URL from and pass to PropertyConfigurator.configure(URL)."),
      HIVE_EXEC_LOG4J_FILE("hive.exec.log4j.file", "", "Hive log4j configuration file for execution mode(sub command).\nIf the property is not set, then logging will be initialized using hive-exec-log4j2.properties found on the classpath.\nIf the property is set, the value must be a valid URI (java.net.URI, e.g. \"file:///tmp/my-logging.xml\"), \nwhich you can then extract a URL from and pass to PropertyConfigurator.configure(URL)."),
      HIVE_ASYNC_LOG_ENABLED("hive.async.log.enabled", true, "Whether to enable Log4j2's asynchronous logging. Asynchronous logging can give\n significant performance improvement as logging will be handled in separate thread\n that uses LMAX disruptor queue for buffering log messages.\n Refer https://logging.apache.org/log4j/2.x/manual/async.html for benefits and\n drawbacks."),
      HIVE_LOG_EXPLAIN_OUTPUT("hive.log.explain.output", false, "Whether to log explain output for every query.\nWhen enabled, will log EXPLAIN EXTENDED output for the query at INFO log4j log level."),
      HIVE_EXPLAIN_USER("hive.explain.user", true, "Whether to show explain result at user level.\nWhen enabled, will log EXPLAIN output for the query at user level."),
      HIVE_AUTOGEN_COLUMNALIAS_PREFIX_LABEL("hive.autogen.columnalias.prefix.label", "_c", "String used as a prefix when auto generating column alias.\nBy default the prefix label will be appended with a column position number to form the column alias. \nAuto generation would happen if an aggregate function is used in a select clause without an explicit alias."),
      HIVE_AUTOGEN_COLUMNALIAS_PREFIX_INCLUDEFUNCNAME("hive.autogen.columnalias.prefix.includefuncname", false, "Whether to include function name in the column alias auto generated by Hive."),
      HIVE_METRICS_CLASS("hive.service.metrics.class", "org.apache.hadoop.hive.common.metrics.metrics2.CodahaleMetrics", new Validator.StringSet(new String[]{"org.apache.hadoop.hive.common.metrics.metrics2.CodahaleMetrics", "org.apache.hadoop.hive.common.metrics.LegacyMetrics"}), "Hive metrics subsystem implementation class."),
      HIVE_METRICS_REPORTER("hive.service.metrics.reporter", "JSON_FILE, JMX", "Reporter type for metric class org.apache.hadoop.hive.common.metrics.metrics2.CodahaleMetrics, comma separated list of JMX, CONSOLE, JSON_FILE, HADOOP2"),
      HIVE_METRICS_JSON_FILE_LOCATION("hive.service.metrics.file.location", "/tmp/report.json", "For metric class org.apache.hadoop.hive.common.metrics.metrics2.CodahaleMetrics JSON_FILE reporter, the location of local JSON metrics file.  This file will get overwritten at every interval."),
      HIVE_METRICS_JSON_FILE_INTERVAL("hive.service.metrics.file.frequency", "5s", new Validator.TimeValidator(TimeUnit.MILLISECONDS), "For metric class org.apache.hadoop.hive.common.metrics.metrics2.CodahaleMetrics JSON_FILE reporter, the frequency of updating JSON metrics file."),
      HIVE_METRICS_HADOOP2_INTERVAL("hive.service.metrics.hadoop2.frequency", "30s", new Validator.TimeValidator(TimeUnit.SECONDS), "For metric class org.apache.hadoop.hive.common.metrics.metrics2.CodahaleMetrics HADOOP2 reporter, the frequency of updating the HADOOP2 metrics system."),
      HIVE_METRICS_HADOOP2_COMPONENT_NAME("hive.service.metrics.hadoop2.component", "hive", "Component name to provide to Hadoop2 Metrics system. Ideally 'hivemetastore' for the MetaStore  and and 'hiveserver2' for HiveServer2."),
      HIVE_PERF_LOGGER("hive.exec.perf.logger", "org.apache.hadoop.hive.ql.log.PerfLogger", "The class responsible for logging client side performance metrics. \nMust be a subclass of org.apache.hadoop.hive.ql.log.PerfLogger"),
      HIVE_START_CLEANUP_SCRATCHDIR("hive.start.cleanup.scratchdir", false, "To cleanup the Hive scratchdir when starting the Hive Server"),
      HIVE_SCRATCH_DIR_LOCK("hive.scratchdir.lock", false, "To hold a lock file in scratchdir to prevent to be removed by cleardanglingscratchdir"),
      HIVE_INSERT_INTO_MULTILEVEL_DIRS("hive.insert.into.multilevel.dirs", false, "Where to insert into multilevel directories like\n\"insert directory '/HIVEFT25686/chinna/' from table\""),
      HIVE_WAREHOUSE_SUBDIR_INHERIT_PERMS("hive.warehouse.subdir.inherit.perms", true, "Set this to false if the table directories should be created\nwith the permissions derived from dfs umask instead of\ninheriting the permission of the warehouse or database directory."),
      HIVE_INSERT_INTO_EXTERNAL_TABLES("hive.insert.into.external.tables", true, "whether insert into external tables is allowed"),
      HIVE_TEMPORARY_TABLE_STORAGE("hive.exec.temporary.table.storage", "default", new Validator.StringSet(new String[]{"memory", "ssd", "default"}), "Define the storage policy for temporary tables.Choices between memory, ssd and default"),
      HIVE_QUERY_LIFETIME_HOOKS("hive.query.lifetime.hooks", "", "A comma separated list of hooks which implement QueryLifeTimeHook. These will be triggered before/after query compilation and before/after query execution, in the order specified"),
      HIVE_DRIVER_RUN_HOOKS("hive.exec.driver.run.hooks", "", "A comma separated list of hooks which implement HiveDriverRunHook. Will be run at the beginning and end of Driver.run, these will be run in the order specified."),
      HIVE_DDL_OUTPUT_FORMAT("hive.ddl.output.format", (Object)null, "The data format to use for DDL output.  One of \"text\" (for human\nreadable text) or \"json\" (for a json object)."),
      HIVE_ENTITY_SEPARATOR("hive.entity.separator", "@", "Separator used to construct names of tables and partitions. For example, dbname@tablename@partitionname"),
      HIVE_CAPTURE_TRANSFORM_ENTITY("hive.entity.capture.transform", false, "Compiler to capture transform URI referred in the query"),
      HIVE_DISPLAY_PARTITION_COLUMNS_SEPARATELY("hive.display.partition.cols.separately", true, "In older Hive version (0.10 and earlier) no distinction was made between\npartition columns or non-partition columns while displaying columns in describe\ntable. From 0.12 onwards, they are displayed separately. This flag will let you\nget old behavior, if desired. See, test-case in patch for HIVE-6689."),
      HIVE_SSL_PROTOCOL_BLACKLIST("hive.ssl.protocol.blacklist", "SSLv2,SSLv3", "SSL Versions to disable for all Hive Servers"),
      HIVE_SERVER2_CLEAR_DANGLING_SCRATCH_DIR("hive.server2.clear.dangling.scratchdir", false, "Clear dangling scratch dir periodically in HS2"),
      HIVE_SERVER2_CLEAR_DANGLING_SCRATCH_DIR_INTERVAL("hive.server2.clear.dangling.scratchdir.interval", "1800s", new Validator.TimeValidator(TimeUnit.SECONDS), "Interval to clear dangling scratch dir periodically in HS2"),
      HIVE_SERVER2_SLEEP_INTERVAL_BETWEEN_START_ATTEMPTS("hive.server2.sleep.interval.between.start.attempts", "60s", new Validator.TimeValidator(TimeUnit.MILLISECONDS, 0L, true, Long.MAX_VALUE, true), "Amount of time to sleep between HiveServer2 start attempts. Primarily meant for tests"),
      HIVE_SERVER2_MAX_START_ATTEMPTS("hive.server2.max.start.attempts", 30L, new Validator.RangeValidator(0L, (Object)null), "Number of times HiveServer2 will attempt to start before exiting. The sleep interval between retries is determined by " + HIVE_SERVER2_SLEEP_INTERVAL_BETWEEN_START_ATTEMPTS.varname + "\n The default of 30 will keep trying for 30 minutes."),
      HIVE_SERVER2_SUPPORT_DYNAMIC_SERVICE_DISCOVERY("hive.server2.support.dynamic.service.discovery", false, "Whether HiveServer2 supports dynamic service discovery for its clients. To support this, each instance of HiveServer2 currently uses ZooKeeper to register itself, when it is brought up. JDBC/ODBC clients should use the ZooKeeper ensemble: hive.zookeeper.quorum in their connection string."),
      HIVE_SERVER2_ZOOKEEPER_NAMESPACE("hive.server2.zookeeper.namespace", "hiveserver2", "The parent node in ZooKeeper used by HiveServer2 when supporting dynamic service discovery."),
      HIVE_SERVER2_ZOOKEEPER_PUBLISH_CONFIGS("hive.server2.zookeeper.publish.configs", true, "Whether we should publish HiveServer2's configs to ZooKeeper."),
      HIVE_SERVER2_GLOBAL_INIT_FILE_LOCATION("hive.server2.global.init.file.location", "${env:HIVE_CONF_DIR}", "Either the location of a HS2 global init file or a directory containing a .hiverc file. If the \nproperty is set, the value must be a valid path to an init file or directory where the init file is located."),
      HIVE_SERVER2_TRANSPORT_MODE("hive.server2.transport.mode", "binary", new Validator.StringSet(new String[]{"binary", "http"}), "Transport mode of HiveServer2."),
      HIVE_SERVER2_THRIFT_BIND_HOST("hive.server2.thrift.bind.host", "", "Bind host on which to run the HiveServer2 Thrift service."),
      HIVE_SERVER2_PARALLEL_COMPILATION("hive.driver.parallel.compilation", false, "Whether to\nenable parallel compilation of the queries between sessions and within the same session on HiveServer2. The default is false."),
      HIVE_SERVER2_COMPILE_LOCK_TIMEOUT("hive.server2.compile.lock.timeout", "0s", new Validator.TimeValidator(TimeUnit.SECONDS), "Number of seconds a request will wait to acquire the compile lock before giving up. Setting it to 0s disables the timeout."),
      HIVE_SERVER2_PARALLEL_OPS_IN_SESSION("hive.server2.parallel.ops.in.session", true, "Whether to allow several parallel operations (such as SQL statements) in one session."),
      HIVE_SERVER2_WEBUI_BIND_HOST("hive.server2.webui.host", "0.0.0.0", "The host address the HiveServer2 WebUI will listen on"),
      HIVE_SERVER2_WEBUI_PORT("hive.server2.webui.port", 10002, "The port the HiveServer2 WebUI will listen on. This can beset to 0 or a negative integer to disable the web UI"),
      HIVE_SERVER2_WEBUI_MAX_THREADS("hive.server2.webui.max.threads", 50, "The max HiveServer2 WebUI threads"),
      HIVE_SERVER2_WEBUI_USE_SSL("hive.server2.webui.use.ssl", false, "Set this to true for using SSL encryption for HiveServer2 WebUI."),
      HIVE_SERVER2_WEBUI_SSL_KEYSTORE_PATH("hive.server2.webui.keystore.path", "", "SSL certificate keystore location for HiveServer2 WebUI."),
      HIVE_SERVER2_WEBUI_SSL_KEYSTORE_PASSWORD("hive.server2.webui.keystore.password", "", "SSL certificate keystore password for HiveServer2 WebUI."),
      HIVE_SERVER2_WEBUI_USE_SPNEGO("hive.server2.webui.use.spnego", false, "If true, the HiveServer2 WebUI will be secured with SPNEGO. Clients must authenticate with Kerberos."),
      HIVE_SERVER2_WEBUI_SPNEGO_KEYTAB("hive.server2.webui.spnego.keytab", "", "The path to the Kerberos Keytab file containing the HiveServer2 WebUI SPNEGO service principal."),
      HIVE_SERVER2_WEBUI_SPNEGO_PRINCIPAL("hive.server2.webui.spnego.principal", "HTTP/_HOST@EXAMPLE.COM", "The HiveServer2 WebUI SPNEGO service principal.\nThe special string _HOST will be replaced automatically with \nthe value of hive.server2.webui.host or the correct host name."),
      HIVE_SERVER2_WEBUI_MAX_HISTORIC_QUERIES("hive.server2.webui.max.historic.queries", 25, "The maximum number of past queries to show in HiverSever2 WebUI."),
      HIVE_SERVER2_TEZ_DEFAULT_QUEUES("hive.server2.tez.default.queues", "", "A list of comma separated values corresponding to YARN queues of the same name.\nWhen HiveServer2 is launched in Tez mode, this configuration needs to be set\nfor multiple Tez sessions to run in parallel on the cluster."),
      HIVE_SERVER2_TEZ_SESSIONS_PER_DEFAULT_QUEUE("hive.server2.tez.sessions.per.default.queue", 1, "A positive integer that determines the number of Tez sessions that should be\nlaunched on each of the queues specified by \"hive.server2.tez.default.queues\".\nDetermines the parallelism on each queue."),
      HIVE_SERVER2_TEZ_INITIALIZE_DEFAULT_SESSIONS("hive.server2.tez.initialize.default.sessions", false, "This flag is used in HiveServer2 to enable a user to use HiveServer2 without\nturning on Tez for HiveServer2. The user could potentially want to run queries\nover Tez without the pool of sessions."),
      HIVE_SERVER2_TEZ_SESSION_LIFETIME("hive.server2.tez.session.lifetime", "162h", new Validator.TimeValidator(TimeUnit.HOURS), "The lifetime of the Tez sessions launched by HS2 when default sessions are enabled.\nSet to 0 to disable session expiration."),
      HIVE_SERVER2_TEZ_SESSION_LIFETIME_JITTER("hive.server2.tez.session.lifetime.jitter", "3h", new Validator.TimeValidator(TimeUnit.HOURS), "The jitter for Tez session lifetime; prevents all the sessions from restarting at once."),
      HIVE_SERVER2_TEZ_SESSION_MAX_INIT_THREADS("hive.server2.tez.sessions.init.threads", 16, "If hive.server2.tez.initialize.default.sessions is enabled, the maximum number of\nthreads to use to initialize the default sessions."),
      HIVE_SERVER2_TEZ_SESSION_RESTRICTED_CONFIGS("hive.server2.tez.sessions.restricted.configs", "", "The configuration settings that cannot be set when submitting jobs to HiveServer2. If\nany of these are set to values different from those in the server configuration, an\nexception will be thrown."),
      HIVE_SERVER2_TEZ_SESSION_CUSTOM_QUEUE_ALLOWED("hive.server2.tez.sessions.custom.queue.allowed", "true", new Validator.StringSet(new String[]{"true", "false", "ignore"}), "Whether Tez session pool should allow submitting queries to custom queues. The options\nare true, false (error out), ignore (accept the query but ignore the queue setting)."),
      HIVE_SERVER2_LOGGING_OPERATION_ENABLED("hive.server2.logging.operation.enabled", true, "When true, HS2 will save operation logs and make them available for clients"),
      HIVE_SERVER2_LOGGING_OPERATION_LOG_LOCATION("hive.server2.logging.operation.log.location", "${system:java.io.tmpdir}" + File.separator + "${system:user.name}" + File.separator + "operation_logs", "Top level directory where operation logs are stored if logging functionality is enabled"),
      HIVE_SERVER2_LOGGING_OPERATION_LEVEL("hive.server2.logging.operation.level", "EXECUTION", new Validator.StringSet(new String[]{"NONE", "EXECUTION", "PERFORMANCE", "VERBOSE"}), "HS2 operation logging mode available to clients to be set at session level.\nFor this to work, hive.server2.logging.operation.enabled should be set to true.\n  NONE: Ignore any logging\n  EXECUTION: Log completion of tasks\n  PERFORMANCE: Execution + Performance logs \n  VERBOSE: All logs"),
      HIVE_SERVER2_METRICS_ENABLED("hive.server2.metrics.enabled", false, "Enable metrics on the HiveServer2."),
      HIVE_SERVER2_THRIFT_HTTP_PORT("hive.server2.thrift.http.port", 10001, "Port number of HiveServer2 Thrift interface when hive.server2.transport.mode is 'http'."),
      HIVE_SERVER2_THRIFT_HTTP_PATH("hive.server2.thrift.http.path", "cliservice", "Path component of URL endpoint when in HTTP mode."),
      HIVE_SERVER2_THRIFT_MAX_MESSAGE_SIZE("hive.server2.thrift.max.message.size", 104857600, "Maximum message size in bytes a HS2 server will accept."),
      HIVE_SERVER2_THRIFT_HTTP_MAX_IDLE_TIME("hive.server2.thrift.http.max.idle.time", "1800s", new Validator.TimeValidator(TimeUnit.MILLISECONDS), "Maximum idle time for a connection on the server when in HTTP mode."),
      HIVE_SERVER2_THRIFT_HTTP_WORKER_KEEPALIVE_TIME("hive.server2.thrift.http.worker.keepalive.time", "60s", new Validator.TimeValidator(TimeUnit.SECONDS), "Keepalive time for an idle http worker thread. When the number of workers exceeds min workers, excessive threads are killed after this time interval."),
      HIVE_SERVER2_THRIFT_HTTP_REQUEST_HEADER_SIZE("hive.server2.thrift.http.request.header.size", 6144, "Request header size in bytes, when using HTTP transport mode. Jetty defaults used."),
      HIVE_SERVER2_THRIFT_HTTP_RESPONSE_HEADER_SIZE("hive.server2.thrift.http.response.header.size", 6144, "Response header size in bytes, when using HTTP transport mode. Jetty defaults used."),
      HIVE_SERVER2_THRIFT_HTTP_COOKIE_AUTH_ENABLED("hive.server2.thrift.http.cookie.auth.enabled", true, "When true, HiveServer2 in HTTP transport mode, will use cookie based authentication mechanism."),
      HIVE_SERVER2_THRIFT_HTTP_COOKIE_MAX_AGE("hive.server2.thrift.http.cookie.max.age", "86400s", new Validator.TimeValidator(TimeUnit.SECONDS), "Maximum age in seconds for server side cookie used by HS2 in HTTP mode."),
      HIVE_SERVER2_THRIFT_HTTP_COOKIE_DOMAIN("hive.server2.thrift.http.cookie.domain", (Object)null, "Domain for the HS2 generated cookies"),
      HIVE_SERVER2_THRIFT_HTTP_COOKIE_PATH("hive.server2.thrift.http.cookie.path", (Object)null, "Path for the HS2 generated cookies"),
      /** @deprecated */
      @Deprecated
      HIVE_SERVER2_THRIFT_HTTP_COOKIE_IS_SECURE("hive.server2.thrift.http.cookie.is.secure", true, "Deprecated: Secure attribute of the HS2 generated cookie (this is automatically enabled for SSL enabled HiveServer2)."),
      HIVE_SERVER2_THRIFT_HTTP_COOKIE_IS_HTTPONLY("hive.server2.thrift.http.cookie.is.httponly", true, "HttpOnly attribute of the HS2 generated cookie."),
      HIVE_SERVER2_THRIFT_PORT("hive.server2.thrift.port", 10000, "Port number of HiveServer2 Thrift interface when hive.server2.transport.mode is 'binary'."),
      HIVE_SERVER2_THRIFT_SASL_QOP("hive.server2.thrift.sasl.qop", "auth", new Validator.StringSet(new String[]{"auth", "auth-int", "auth-conf"}), "Sasl QOP value; set it to one of following values to enable higher levels of\nprotection for HiveServer2 communication with clients.\nSetting hadoop.rpc.protection to a higher level than HiveServer2 does not\nmake sense in most situations. HiveServer2 ignores hadoop.rpc.protection in favor\nof hive.server2.thrift.sasl.qop.\n  \"auth\" - authentication only (default)\n  \"auth-int\" - authentication plus integrity protection\n  \"auth-conf\" - authentication plus integrity and confidentiality protection\nThis is applicable only if HiveServer2 is configured to use Kerberos authentication."),
      HIVE_SERVER2_THRIFT_MIN_WORKER_THREADS("hive.server2.thrift.min.worker.threads", 5, "Minimum number of Thrift worker threads"),
      HIVE_SERVER2_THRIFT_MAX_WORKER_THREADS("hive.server2.thrift.max.worker.threads", 500, "Maximum number of Thrift worker threads"),
      HIVE_SERVER2_THRIFT_LOGIN_BEBACKOFF_SLOT_LENGTH("hive.server2.thrift.exponential.backoff.slot.length", "100ms", new Validator.TimeValidator(TimeUnit.MILLISECONDS), "Binary exponential backoff slot time for Thrift clients during login to HiveServer2,\nfor retries until hitting Thrift client timeout"),
      HIVE_SERVER2_THRIFT_LOGIN_TIMEOUT("hive.server2.thrift.login.timeout", "20s", new Validator.TimeValidator(TimeUnit.SECONDS), "Timeout for Thrift clients during login to HiveServer2"),
      HIVE_SERVER2_THRIFT_WORKER_KEEPALIVE_TIME("hive.server2.thrift.worker.keepalive.time", "60s", new Validator.TimeValidator(TimeUnit.SECONDS), "Keepalive time (in seconds) for an idle worker thread. When the number of workers exceeds min workers, excessive threads are killed after this time interval."),
      HIVE_SERVER2_ASYNC_EXEC_THREADS("hive.server2.async.exec.threads", 100, "Number of threads in the async thread pool for HiveServer2"),
      HIVE_SERVER2_ASYNC_EXEC_SHUTDOWN_TIMEOUT("hive.server2.async.exec.shutdown.timeout", "10s", new Validator.TimeValidator(TimeUnit.SECONDS), "How long HiveServer2 shutdown will wait for async threads to terminate."),
      HIVE_SERVER2_ASYNC_EXEC_WAIT_QUEUE_SIZE("hive.server2.async.exec.wait.queue.size", 100, "Size of the wait queue for async thread pool in HiveServer2.\nAfter hitting this limit, the async thread pool will reject new requests."),
      HIVE_SERVER2_ASYNC_EXEC_KEEPALIVE_TIME("hive.server2.async.exec.keepalive.time", "10s", new Validator.TimeValidator(TimeUnit.SECONDS), "Time that an idle HiveServer2 async thread (from the thread pool) will wait for a new task\nto arrive before terminating"),
      HIVE_SERVER2_ASYNC_EXEC_ASYNC_COMPILE("hive.server2.async.exec.async.compile", false, "Whether to enable compiling async query asynchronously. If enabled, it is unknown if the query will have any resultset before compilation completed."),
      HIVE_SERVER2_LONG_POLLING_TIMEOUT("hive.server2.long.polling.timeout", "5000ms", new Validator.TimeValidator(TimeUnit.MILLISECONDS), "Time that HiveServer2 will wait before responding to asynchronous calls that use long polling"),
      HIVE_SESSION_IMPL_CLASSNAME("hive.session.impl.classname", (Object)null, "Classname for custom implementation of hive session"),
      HIVE_SESSION_IMPL_WITH_UGI_CLASSNAME("hive.session.impl.withugi.classname", (Object)null, "Classname for custom implementation of hive session with UGI"),
      HIVE_SERVER2_AUTHENTICATION("hive.server2.authentication", "NONE", new Validator.StringSet(new String[]{"NOSASL", "NONE", "LDAP", "KERBEROS", "PAM", "CUSTOM"}), "Client authentication types.\n  NONE: no authentication check\n  LDAP: LDAP/AD based authentication\n  KERBEROS: Kerberos/GSSAPI authentication\n  CUSTOM: Custom authentication provider\n          (Use with property hive.server2.custom.authentication.class)\n  PAM: Pluggable authentication module\n  NOSASL:  Raw transport"),
      HIVE_SERVER2_ALLOW_USER_SUBSTITUTION("hive.server2.allow.user.substitution", true, "Allow alternate user to be specified as part of HiveServer2 open connection request."),
      HIVE_SERVER2_KERBEROS_KEYTAB("hive.server2.authentication.kerberos.keytab", "", "Kerberos keytab file for server principal"),
      HIVE_SERVER2_KERBEROS_PRINCIPAL("hive.server2.authentication.kerberos.principal", "", "Kerberos server principal"),
      HIVE_SERVER2_SPNEGO_KEYTAB("hive.server2.authentication.spnego.keytab", "", "keytab file for SPNego principal, optional,\ntypical value would look like /etc/security/keytabs/spnego.service.keytab,\nThis keytab would be used by HiveServer2 when Kerberos security is enabled and \nHTTP transport mode is used.\nThis needs to be set only if SPNEGO is to be used in authentication.\nSPNego authentication would be honored only if valid\n  hive.server2.authentication.spnego.principal\nand\n  hive.server2.authentication.spnego.keytab\nare specified."),
      HIVE_SERVER2_SPNEGO_PRINCIPAL("hive.server2.authentication.spnego.principal", "", "SPNego service principal, optional,\ntypical value would look like HTTP/_HOST@EXAMPLE.COM\nSPNego service principal would be used by HiveServer2 when Kerberos security is enabled\nand HTTP transport mode is used.\nThis needs to be set only if SPNEGO is to be used in authentication."),
      HIVE_SERVER2_PLAIN_LDAP_URL("hive.server2.authentication.ldap.url", (Object)null, "LDAP connection URL(s),\nthis value could contain URLs to mutiple LDAP servers instances for HA,\neach LDAP URL is separated by a SPACE character. URLs are used in the \n order specified until a connection is successful."),
      HIVE_SERVER2_PLAIN_LDAP_BASEDN("hive.server2.authentication.ldap.baseDN", (Object)null, "LDAP base DN"),
      HIVE_SERVER2_PLAIN_LDAP_DOMAIN("hive.server2.authentication.ldap.Domain", (Object)null, ""),
      HIVE_SERVER2_PLAIN_LDAP_GROUPDNPATTERN("hive.server2.authentication.ldap.groupDNPattern", (Object)null, "COLON-separated list of patterns to use to find DNs for group entities in this directory.\nUse %s where the actual group name is to be substituted for.\nFor example: CN=%s,CN=Groups,DC=subdomain,DC=domain,DC=com."),
      HIVE_SERVER2_PLAIN_LDAP_GROUPFILTER("hive.server2.authentication.ldap.groupFilter", (Object)null, "COMMA-separated list of LDAP Group names (short name not full DNs).\nFor example: HiveAdmins,HadoopAdmins,Administrators"),
      HIVE_SERVER2_PLAIN_LDAP_USERDNPATTERN("hive.server2.authentication.ldap.userDNPattern", (Object)null, "COLON-separated list of patterns to use to find DNs for users in this directory.\nUse %s where the actual group name is to be substituted for.\nFor example: CN=%s,CN=Users,DC=subdomain,DC=domain,DC=com."),
      HIVE_SERVER2_PLAIN_LDAP_USERFILTER("hive.server2.authentication.ldap.userFilter", (Object)null, "COMMA-separated list of LDAP usernames (just short names, not full DNs).\nFor example: hiveuser,impalauser,hiveadmin,hadoopadmin"),
      HIVE_SERVER2_PLAIN_LDAP_GUIDKEY("hive.server2.authentication.ldap.guidKey", "uid", "LDAP attribute name whose values are unique in this LDAP server.\nFor example: uid or CN."),
      HIVE_SERVER2_PLAIN_LDAP_GROUPMEMBERSHIP_KEY("hive.server2.authentication.ldap.groupMembershipKey", "member", "LDAP attribute name on the group object that contains the list of distinguished names\nfor the user, group, and contact objects that are members of the group.\nFor example: member, uniqueMember or memberUid"),
      HIVE_SERVER2_PLAIN_LDAP_USERMEMBERSHIP_KEY("hive.server2.authentication.ldap.userMembershipKey", (Object)null, "LDAP attribute name on the user object that contains groups of which the user is\na direct member, except for the primary group, which is represented by the\nprimaryGroupId.\nFor example: memberOf"),
      HIVE_SERVER2_PLAIN_LDAP_GROUPCLASS_KEY("hive.server2.authentication.ldap.groupClassKey", "groupOfNames", "LDAP attribute name on the group entry that is to be used in LDAP group searches.\nFor example: group, groupOfNames or groupOfUniqueNames."),
      HIVE_SERVER2_PLAIN_LDAP_CUSTOMLDAPQUERY("hive.server2.authentication.ldap.customLDAPQuery", (Object)null, "A full LDAP query that LDAP Atn provider uses to execute against LDAP Server.\nIf this query returns a null resultset, the LDAP Provider fails the Authentication\nrequest, succeeds if the user is part of the resultset.For example: (&(objectClass=group)(objectClass=top)(instanceType=4)(cn=Domain*)) \n(&(objectClass=person)(|(sAMAccountName=admin)(|(memberOf=CN=Domain Admins,CN=Users,DC=domain,DC=com)(memberOf=CN=Administrators,CN=Builtin,DC=domain,DC=com))))"),
      HIVE_SERVER2_CUSTOM_AUTHENTICATION_CLASS("hive.server2.custom.authentication.class", (Object)null, "Custom authentication class. Used when property\n'hive.server2.authentication' is set to 'CUSTOM'. Provided class\nmust be a proper implementation of the interface\norg.apache.hive.service.auth.PasswdAuthenticationProvider. HiveServer2\nwill call its Authenticate(user, passed) method to authenticate requests.\nThe implementation may optionally implement Hadoop's\norg.apache.hadoop.conf.Configurable class to grab Hive's Configuration object."),
      HIVE_SERVER2_PAM_SERVICES("hive.server2.authentication.pam.services", (Object)null, "List of the underlying pam services that should be used when auth type is PAM\nA file with the same name must exist in /etc/pam.d"),
      HIVE_SERVER2_ENABLE_DOAS("hive.server2.enable.doAs", true, "Setting this property to true will have HiveServer2 execute\nHive operations as the user making the calls to it."),
      HIVE_SERVER2_TABLE_TYPE_MAPPING("hive.server2.table.type.mapping", "CLASSIC", new Validator.StringSet(new String[]{"CLASSIC", "HIVE"}), "This setting reflects how HiveServer2 will report the table types for JDBC and other\nclient implementations that retrieve the available tables and supported table types\n  HIVE : Exposes Hive's native table types like MANAGED_TABLE, EXTERNAL_TABLE, VIRTUAL_VIEW\n  CLASSIC : More generic types like TABLE and VIEW"),
      HIVE_SERVER2_SESSION_HOOK("hive.server2.session.hook", "", ""),
      HIVE_SERVER2_USE_SSL("hive.server2.use.SSL", false, "Set this to true for using SSL encryption in HiveServer2."),
      HIVE_SERVER2_SSL_KEYSTORE_PATH("hive.server2.keystore.path", "", "SSL certificate keystore location."),
      HIVE_SERVER2_SSL_KEYSTORE_PASSWORD("hive.server2.keystore.password", "", "SSL certificate keystore password."),
      HIVE_SERVER2_MAP_FAIR_SCHEDULER_QUEUE("hive.server2.map.fair.scheduler.queue", true, "If the YARN fair scheduler is configured and HiveServer2 is running in non-impersonation mode,\nthis setting determines the user for fair scheduler queue mapping.\nIf set to true (default), the logged-in user determines the fair scheduler queue\nfor submitted jobs, so that map reduce resource usage can be tracked by user.\nIf set to false, all Hive jobs go to the 'hive' user's queue."),
      HIVE_SERVER2_BUILTIN_UDF_WHITELIST("hive.server2.builtin.udf.whitelist", "", "Comma separated list of builtin udf names allowed in queries.\nAn empty whitelist allows all builtin udfs to be executed.  The udf black list takes precedence over udf white list"),
      HIVE_SERVER2_BUILTIN_UDF_BLACKLIST("hive.server2.builtin.udf.blacklist", "", "Comma separated list of udfs names. These udfs will not be allowed in queries. The udf black list takes precedence over udf white list"),
      HIVE_ALLOW_UDF_LOAD_ON_DEMAND("hive.allow.udf.load.on.demand", false, "Whether enable loading UDFs from metastore on demand; this is mostly relevant for\nHS2 and was the default behavior before Hive 1.2. Off by default."),
      HIVE_SERVER2_SESSION_CHECK_INTERVAL("hive.server2.session.check.interval", "6h", new Validator.TimeValidator(TimeUnit.MILLISECONDS, 3000L, true, (Long)null, false), "The check interval for session/operation timeout, which can be disabled by setting to zero or negative value."),
      HIVE_SERVER2_CLOSE_SESSION_ON_DISCONNECT("hive.server2.close.session.on.disconnect", true, "Session will be closed when connection is closed. Set this to false to have session outlive its parent connection."),
      HIVE_SERVER2_IDLE_SESSION_TIMEOUT("hive.server2.idle.session.timeout", "7d", new Validator.TimeValidator(TimeUnit.MILLISECONDS), "Session will be closed when it's not accessed for this duration, which can be disabled by setting to zero or negative value."),
      HIVE_SERVER2_IDLE_OPERATION_TIMEOUT("hive.server2.idle.operation.timeout", "5d", new Validator.TimeValidator(TimeUnit.MILLISECONDS), "Operation will be closed when it's not accessed for this duration of time, which can be disabled by setting to zero value.\n  With positive value, it's checked for operations in terminal state only (FINISHED, CANCELED, CLOSED, ERROR).\n  With negative value, it's checked for all of the operations regardless of state."),
      HIVE_SERVER2_IDLE_SESSION_CHECK_OPERATION("hive.server2.idle.session.check.operation", true, "Session will be considered to be idle only if there is no activity, and there is no pending operation.\n This setting takes effect only if session idle timeout (hive.server2.idle.session.timeout) and checking\n(hive.server2.session.check.interval) are enabled."),
      HIVE_SERVER2_THRIFT_CLIENT_RETRY_LIMIT("hive.server2.thrift.client.retry.limit", 1, "Number of retries upon failure of Thrift HiveServer2 calls"),
      HIVE_SERVER2_THRIFT_CLIENT_CONNECTION_RETRY_LIMIT("hive.server2.thrift.client.connect.retry.limit", 1, "Number of retries while opening a connection to HiveServe2"),
      HIVE_SERVER2_THRIFT_CLIENT_RETRY_DELAY_SECONDS("hive.server2.thrift.client.retry.delay.seconds", "1s", new Validator.TimeValidator(TimeUnit.SECONDS), "Number of seconds for the HiveServer2 thrift client to wait between consecutive connection attempts. Also specifies the time to wait between retrying thrift calls upon failures"),
      HIVE_SERVER2_THRIFT_CLIENT_USER("hive.server2.thrift.client.user", "anonymous", "Username to use against thrift client"),
      HIVE_SERVER2_THRIFT_CLIENT_PASSWORD("hive.server2.thrift.client.password", "anonymous", "Password to use against thrift client"),
      HIVE_SERVER2_THRIFT_RESULTSET_SERIALIZE_IN_TASKS("hive.server2.thrift.resultset.serialize.in.tasks", false, "Whether we should serialize the Thrift structures used in JDBC ResultSet RPC in task nodes.\n We use SequenceFile and ThriftJDBCBinarySerDe to read and write the final results if this is true."),
      HIVE_SERVER2_THRIFT_RESULTSET_MAX_FETCH_SIZE("hive.server2.thrift.resultset.max.fetch.size", 10000, "Max number of rows sent in one Fetch RPC call by the server to the client."),
      HIVE_SERVER2_THRIFT_RESULTSET_DEFAULT_FETCH_SIZE("hive.server2.thrift.resultset.default.fetch.size", 1000, "The number of rows sent in one Fetch RPC call by the server to the client, if not\nspecified by the client."),
      HIVE_SERVER2_XSRF_FILTER_ENABLED("hive.server2.xsrf.filter.enabled", false, "If enabled, HiveServer2 will block any requests made to it over http if an X-XSRF-HEADER header is not present"),
      HIVE_SECURITY_COMMAND_WHITELIST("hive.security.command.whitelist", "set,reset,dfs,add,list,delete,reload,compile", "Comma separated list of non-SQL Hive commands users are authorized to execute"),
      HIVE_SERVER2_JOB_CREDENTIAL_PROVIDER_PATH("hive.server2.job.credential.provider.path", "", "If set, this configuration property should provide a comma-separated list of URLs that indicates the type and location of providers to be used by hadoop credential provider API. It provides HiveServer2 the ability to provide job-specific credential providers for jobs run using MR and Spark execution engines. This functionality has not been tested against Tez."),
      HIVE_MOVE_FILES_THREAD_COUNT("hive.mv.files.thread", 15, new Validator.SizeValidator(0L, true, 1024L, true), "Number of threads used to move files in move task. Set it to 0 to disable multi-threaded file moves. This parameter is also used by MSCK to check tables."),
      HIVE_LOAD_DYNAMIC_PARTITIONS_THREAD_COUNT("hive.load.dynamic.partitions.thread", 15, new Validator.SizeValidator(1L, true, 1024L, true), "Number of threads used to load dynamic partitions."),
      HIVE_MULTI_INSERT_MOVE_TASKS_SHARE_DEPENDENCIES("hive.multi.insert.move.tasks.share.dependencies", false, "If this is set all move tasks for tables/partitions (not directories) at the end of a\nmulti-insert query will only begin once the dependencies for all these move tasks have been\nmet.\nAdvantages: If concurrency is enabled, the locks will only be released once the query has\n            finished, so with this config enabled, the time when the table/partition is\n            generated will be much closer to when the lock on it is released.\nDisadvantages: If concurrency is not enabled, with this disabled, the tables/partitions which\n               are produced by this query and finish earlier will be available for querying\n               much earlier.  Since the locks are only released once the query finishes, this\n               does not apply if concurrency is enabled."),
      HIVE_INFER_BUCKET_SORT("hive.exec.infer.bucket.sort", false, "If this is set, when writing partitions, the metadata will include the bucketing/sorting\nproperties with which the data was written if any (this will not overwrite the metadata\ninherited from the table if the table is bucketed/sorted)"),
      HIVE_INFER_BUCKET_SORT_NUM_BUCKETS_POWER_TWO("hive.exec.infer.bucket.sort.num.buckets.power.two", false, "If this is set, when setting the number of reducers for the map reduce task which writes the\nfinal output files, it will choose a number which is a power of two, unless the user specifies\nthe number of reducers to use using mapred.reduce.tasks.  The number of reducers\nmay be set to a power of two, only to be followed by a merge task meaning preventing\nanything from being inferred.\nWith hive.exec.infer.bucket.sort set to true:\nAdvantages:  If this is not set, the number of buckets for partitions will seem arbitrary,\n             which means that the number of mappers used for optimized joins, for example, will\n             be very low.  With this set, since the number of buckets used for any partition is\n             a power of two, the number of mappers used for optimized joins will be the least\n             number of buckets used by any partition being joined.\nDisadvantages: This may mean a much larger or much smaller number of reducers being used in the\n               final map reduce job, e.g. if a job was originally going to take 257 reducers,\n               it will now take 512 reducers, similarly if the max number of reducers is 511,\n               and a job was going to use this many, it will now use 256 reducers."),
      HIVEOPTLISTBUCKETING("hive.optimize.listbucketing", false, "Enable list bucketing optimizer. Default value is false so that we disable it by default."),
      SERVER_READ_SOCKET_TIMEOUT("hive.server.read.socket.timeout", "10s", new Validator.TimeValidator(TimeUnit.SECONDS), "Timeout for the HiveServer to close the connection if no response from the client. By default, 10 seconds."),
      SERVER_TCP_KEEP_ALIVE("hive.server.tcp.keepalive", true, "Whether to enable TCP keepalive for the Hive Server. Keepalive will prevent accumulation of half-open connections."),
      HIVE_DECODE_PARTITION_NAME("hive.decode.partition.name", false, "Whether to show the unquoted partition names in query results."),
      HIVE_EXECUTION_ENGINE("hive.execution.engine", "mr", new Validator.StringSet(new String[]{"mr", "tez", "spark"}), "Chooses execution engine. Options are: mr (Map reduce, default), tez, spark. While MR\nremains the default engine for historical reasons, it is itself a historical engine\nand is deprecated in Hive 2 line. It may be removed without further warning."),
      HIVE_EXECUTION_MODE("hive.execution.mode", "container", new Validator.StringSet(new String[]{"container", "llap"}), "Chooses whether query fragments will run in container or in llap"),
      HIVE_JAR_DIRECTORY("hive.jar.directory", (Object)null, "This is the location hive in tez mode will look for to find a site wide \ninstalled hive instance."),
      HIVE_USER_INSTALL_DIR("hive.user.install.directory", "/user/", "If hive (in tez mode only) cannot find a usable hive jar in \"hive.jar.directory\", \nit will upload the hive jar to \"hive.user.install.directory/user.name\"\nand use it to run queries."),
      HIVE_VECTORIZATION_ENABLED("hive.vectorized.execution.enabled", false, "This flag should be set to true to enable vectorized mode of query execution.\nThe default value is false."),
      HIVE_VECTORIZATION_REDUCE_ENABLED("hive.vectorized.execution.reduce.enabled", true, "This flag should be set to true to enable vectorized mode of the reduce-side of query execution.\nThe default value is true."),
      HIVE_VECTORIZATION_REDUCE_GROUPBY_ENABLED("hive.vectorized.execution.reduce.groupby.enabled", true, "This flag should be set to true to enable vectorized mode of the reduce-side GROUP BY query execution.\nThe default value is true."),
      HIVE_VECTORIZATION_MAPJOIN_NATIVE_ENABLED("hive.vectorized.execution.mapjoin.native.enabled", true, "This flag should be set to true to enable native (i.e. non-pass through) vectorization\nof queries using MapJoin.\nThe default value is true."),
      HIVE_VECTORIZATION_MAPJOIN_NATIVE_MULTIKEY_ONLY_ENABLED("hive.vectorized.execution.mapjoin.native.multikey.only.enabled", false, "This flag should be set to true to restrict use of native vector map join hash tables to\nthe MultiKey in queries using MapJoin.\nThe default value is false."),
      HIVE_VECTORIZATION_MAPJOIN_NATIVE_MINMAX_ENABLED("hive.vectorized.execution.mapjoin.minmax.enabled", false, "This flag should be set to true to enable vector map join hash tables to\nuse max / max filtering for integer join queries using MapJoin.\nThe default value is false."),
      HIVE_VECTORIZATION_MAPJOIN_NATIVE_OVERFLOW_REPEATED_THRESHOLD("hive.vectorized.execution.mapjoin.overflow.repeated.threshold", -1, "The number of small table rows for a match in vector map join hash tables\nwhere we use the repeated field optimization in overflow vectorized row batch for join queries using MapJoin.\nA value of -1 means do use the join result optimization.  Otherwise, threshold value can be 0 to maximum integer."),
      HIVE_VECTORIZATION_MAPJOIN_NATIVE_FAST_HASHTABLE_ENABLED("hive.vectorized.execution.mapjoin.native.fast.hashtable.enabled", false, "This flag should be set to true to enable use of native fast vector map join hash tables in\nqueries using MapJoin.\nThe default value is false."),
      HIVE_VECTORIZATION_GROUPBY_CHECKINTERVAL("hive.vectorized.groupby.checkinterval", 100000, "Number of entries added to the group by aggregation hash before a recomputation of average entry size is performed."),
      HIVE_VECTORIZATION_GROUPBY_MAXENTRIES("hive.vectorized.groupby.maxentries", 1000000, "Max number of entries in the vector group by aggregation hashtables. \nExceeding this will trigger a flush irrelevant of memory pressure condition."),
      HIVE_VECTORIZATION_GROUPBY_FLUSH_PERCENT("hive.vectorized.groupby.flush.percent", 0.1F, "Percent of entries in the group by aggregation hash flushed when the memory threshold is exceeded."),
      HIVE_VECTORIZATION_REDUCESINK_NEW_ENABLED("hive.vectorized.execution.reducesink.new.enabled", true, "This flag should be set to true to enable the new vectorization\nof queries using ReduceSink.\niThe default value is true."),
      HIVE_VECTORIZATION_USE_VECTORIZED_INPUT_FILE_FORMAT("hive.vectorized.use.vectorized.input.format", true, "This flag should be set to true to enable vectorizing with vectorized input file format capable SerDe.\nThe default value is true."),
      HIVE_VECTORIZATION_USE_VECTOR_DESERIALIZE("hive.vectorized.use.vector.serde.deserialize", true, "This flag should be set to true to enable vectorizing rows using vector deserialize.\nThe default value is true."),
      HIVE_VECTORIZATION_USE_ROW_DESERIALIZE("hive.vectorized.use.row.serde.deserialize", false, "This flag should be set to true to enable vectorizing using row deserialize.\nThe default value is false."),
      HIVE_VECTOR_ADAPTOR_USAGE_MODE("hive.vectorized.adaptor.usage.mode", "all", new Validator.StringSet(new String[]{"none", "chosen", "all"}), "Specifies the extent to which the VectorUDFAdaptor will be used for UDFs that do not have a cooresponding vectorized class.\n0. none   : disable any usage of VectorUDFAdaptor\n1. chosen : use VectorUDFAdaptor for a small set of UDFs that were choosen for good performance\n2. all    : use VectorUDFAdaptor for all UDFs"),
      HIVE_TYPE_CHECK_ON_INSERT("hive.typecheck.on.insert", true, "This property has been extended to control whether to check, convert, and normalize partition value to conform to its column type in partition operations including but not limited to insert, such as alter, describe etc."),
      HIVE_HADOOP_CLASSPATH("hive.hadoop.classpath", (Object)null, "For Windows OS, we need to pass HIVE_HADOOP_CLASSPATH Java parameter while starting HiveServer2 \nusing \"-hiveconf hive.hadoop.classpath=%HIVE_LIB%\"."),
      HIVE_RPC_QUERY_PLAN("hive.rpc.query.plan", false, "Whether to send the query plan via local resource or RPC"),
      HIVE_AM_SPLIT_GENERATION("hive.compute.splits.in.am", true, "Whether to generate the splits locally or in the AM (tez only)"),
      HIVE_TEZ_GENERATE_CONSISTENT_SPLITS("hive.tez.input.generate.consistent.splits", true, "Whether to generate consistent split locations when generating splits in the AM"),
      HIVE_PREWARM_ENABLED("hive.prewarm.enabled", false, "Enables container prewarm for Tez/Spark (Hadoop 2 only)"),
      HIVE_PREWARM_NUM_CONTAINERS("hive.prewarm.numcontainers", 10, "Controls the number of containers to prewarm for Tez/Spark (Hadoop 2 only)"),
      HIVESTAGEIDREARRANGE("hive.stageid.rearrange", "none", new Validator.StringSet(new String[]{"none", "idonly", "traverse", "execution"}), ""),
      HIVEEXPLAINDEPENDENCYAPPENDTASKTYPES("hive.explain.dependency.append.tasktype", false, ""),
      HIVECOUNTERGROUP("hive.counters.group.name", "HIVE", "The name of counter group for internal Hive variables (CREATED_FILE, FATAL_ERROR, etc.)"),
      HIVE_QUOTEDID_SUPPORT("hive.support.quoted.identifiers", "column", new Validator.StringSet(new String[]{"none", "column"}), "Whether to use quoted identifier. 'none' or 'column' can be used. \n  none: default(past) behavior. Implies only alphaNumeric and underscore are valid characters in identifiers.\n  column: implies column names can contain any character."),
      HIVE_SUPPORT_SPECICAL_CHARACTERS_IN_TABLE_NAMES("hive.support.special.characters.tablename", true, "This flag should be set to true to enable support for special characters in table names.\nWhen it is set to false, only [a-zA-Z_0-9]+ are supported.\nThe only supported special character right now is '/'. This flag applies only to quoted table names.\nThe default value is true."),
      USERS_IN_ADMIN_ROLE("hive.users.in.admin.role", "", false, "Comma separated list of users who are in admin role for bootstrapping.\nMore users can be added in ADMIN role later."),
      HIVE_COMPAT("hive.compat", HiveCompat.DEFAULT_COMPAT_LEVEL, "Enable (configurable) deprecated behaviors by setting desired level of backward compatibility.\nSetting to 0.12:\n  Maintains division behavior: int / int = double"),
      HIVE_CONVERT_JOIN_BUCKET_MAPJOIN_TEZ("hive.convert.join.bucket.mapjoin.tez", false, "Whether joins can be automatically converted to bucket map joins in hive \nwhen tez is used as the execution engine."),
      HIVE_CHECK_CROSS_PRODUCT("hive.exec.check.crossproducts", true, "Check if a plan contains a Cross Product. If there is one, output a warning to the Session's console."),
      HIVE_LOCALIZE_RESOURCE_WAIT_INTERVAL("hive.localize.resource.wait.interval", "5000ms", new Validator.TimeValidator(TimeUnit.MILLISECONDS), "Time to wait for another thread to localize the same resource for hive-tez."),
      HIVE_LOCALIZE_RESOURCE_NUM_WAIT_ATTEMPTS("hive.localize.resource.num.wait.attempts", 5, "The number of attempts waiting for localizing a resource in hive-tez."),
      TEZ_AUTO_REDUCER_PARALLELISM("hive.tez.auto.reducer.parallelism", false, "Turn on Tez' auto reducer parallelism feature. When enabled, Hive will still estimate data sizes\nand set parallelism estimates. Tez will sample source vertices' output sizes and adjust the estimates at runtime as\nnecessary."),
      TEZ_MAX_PARTITION_FACTOR("hive.tez.max.partition.factor", 2.0F, "When auto reducer parallelism is enabled this factor will be used to over-partition data in shuffle edges."),
      TEZ_MIN_PARTITION_FACTOR("hive.tez.min.partition.factor", 0.25F, "When auto reducer parallelism is enabled this factor will be used to put a lower limit to the number\nof reducers that tez specifies."),
      TEZ_OPTIMIZE_BUCKET_PRUNING("hive.tez.bucket.pruning", false, "When pruning is enabled, filters on bucket columns will be processed by \nfiltering the splits against a bitset of included buckets. This needs predicates \nproduced by hive.optimize.ppd and hive.optimize.index.filters."),
      TEZ_OPTIMIZE_BUCKET_PRUNING_COMPAT("hive.tez.bucket.pruning.compat", true, "When pruning is enabled, handle possibly broken inserts due to negative hashcodes.\nThis occasionally doubles the data scan cost, but is default enabled for safety"),
      TEZ_DYNAMIC_PARTITION_PRUNING("hive.tez.dynamic.partition.pruning", true, "When dynamic pruning is enabled, joins on partition keys will be processed by sending\nevents from the processing vertices to the Tez application master. These events will be\nused to prune unnecessary partitions."),
      TEZ_DYNAMIC_PARTITION_PRUNING_MAX_EVENT_SIZE("hive.tez.dynamic.partition.pruning.max.event.size", 1048576L, "Maximum size of events sent by processors in dynamic pruning. If this size is crossed no pruning will take place."),
      TEZ_DYNAMIC_PARTITION_PRUNING_MAX_DATA_SIZE("hive.tez.dynamic.partition.pruning.max.data.size", 104857600L, "Maximum total data size of events in dynamic pruning."),
      TEZ_DYNAMIC_SEMIJOIN_REDUCTION("hive.tez.dynamic.semijoin.reduction", true, "When dynamic semijoin is enabled, shuffle joins will perform a leaky semijoin before shuffle. This requires hive.tez.dynamic.partition.pruning to be enabled."),
      TEZ_MIN_BLOOM_FILTER_ENTRIES("hive.tez.min.bloom.filter.entries", 1000000L, "Bloom filter should be of at min certain size to be effective"),
      TEZ_MAX_BLOOM_FILTER_ENTRIES("hive.tez.max.bloom.filter.entries", 100000000L, "Bloom filter should be of at max certain size to be effective"),
      TEZ_BLOOM_FILTER_FACTOR("hive.tez.bloom.filter.factor", 2.0F, "Bloom filter should be a multiple of this factor with nDV"),
      TEZ_BIGTABLE_MIN_SIZE_SEMIJOIN_REDUCTION("hive.tez.bigtable.minsize.semijoin.reduction", 1000000L, "Big table for runtime filteting should be of atleast this size"),
      TEZ_DYNAMIC_SEMIJOIN_REDUCTION_THRESHOLD("hive.tez.dynamic.semijoin.reduction.threshold", 0.5F, "Only perform semijoin optimization if the estimated benefit at or above this fraction of the target table"),
      TEZ_SMB_NUMBER_WAVES("hive.tez.smb.number.waves", 0.5F, "The number of waves in which to run the SMB join. Account for cluster being occupied. Ideally should be 1 wave."),
      TEZ_EXEC_SUMMARY("hive.tez.exec.print.summary", false, "Display breakdown of execution steps, for every query executed by the shell."),
      TEZ_EXEC_INPLACE_PROGRESS("hive.tez.exec.inplace.progress", true, "Updates tez job execution progress in-place in the terminal when hive-cli is used."),
      HIVE_SERVER2_INPLACE_PROGRESS("hive.server2.in.place.progress", true, "Allows hive server 2 to send progress bar update information. This is currently available only if the execution engine is tez."),
      SPARK_EXEC_INPLACE_PROGRESS("hive.spark.exec.inplace.progress", true, "Updates spark job execution progress in-place in the terminal."),
      TEZ_CONTAINER_MAX_JAVA_HEAP_FRACTION("hive.tez.container.max.java.heap.fraction", 0.8F, "This is to override the tez setting with the same name"),
      TEZ_TASK_SCALE_MEMORY_RESERVE_FRACTION_MIN("hive.tez.task.scale.memory.reserve-fraction.min", 0.3F, "This is to override the tez setting tez.task.scale.memory.reserve-fraction"),
      TEZ_TASK_SCALE_MEMORY_RESERVE_FRACTION_MAX("hive.tez.task.scale.memory.reserve.fraction.max", 0.5F, "The maximum fraction of JVM memory which Tez will reserve for the processor"),
      TEZ_TASK_SCALE_MEMORY_RESERVE_FRACTION("hive.tez.task.scale.memory.reserve.fraction", -1.0F, "The customized fraction of JVM memory which Tez will reserve for the processor"),
      LLAP_IO_ENABLED("hive.llap.io.enabled", (Object)null, "Whether the LLAP IO layer is enabled."),
      LLAP_IO_NONVECTOR_WRAPPER_ENABLED("hive.llap.io.nonvector.wrapper.enabled", true, "Whether the LLAP IO layer is enabled for non-vectorized queries that read inputs\nthat can be vectorized"),
      LLAP_IO_MEMORY_MODE("hive.llap.io.memory.mode", "cache", new Validator.StringSet(new String[]{"cache", "none"}), "LLAP IO memory usage; 'cache' (the default) uses data and metadata cache with a\ncustom off-heap allocator, 'none' doesn't use either (this mode may result in\nsignificant performance degradation)"),
      LLAP_ALLOCATOR_MIN_ALLOC("hive.llap.io.allocator.alloc.min", "256Kb", new Validator.SizeValidator(), "Minimum allocation possible from LLAP buddy allocator. Allocations below that are\npadded to minimum allocation. For ORC, should generally be the same as the expected\ncompression buffer size, or next lowest power of 2. Must be a power of 2."),
      LLAP_ALLOCATOR_MAX_ALLOC("hive.llap.io.allocator.alloc.max", "16Mb", new Validator.SizeValidator(), "Maximum allocation possible from LLAP buddy allocator. For ORC, should be as large as\nthe largest expected ORC compression buffer size. Must be a power of 2."),
      /** @deprecated */
      @Deprecated
      LLAP_IO_METADATA_FRACTION("hive.llap.io.metadata.fraction", 0.1F, "Temporary setting for on-heap metadata cache fraction of xmx, set to avoid potential\nheap problems on very large datasets when on-heap metadata cache takes over\neverything. -1 managed metadata and data together (which is more flexible). This\nsetting will be removed (in effect become -1) once ORC metadata cache is moved off-heap."),
      LLAP_ALLOCATOR_ARENA_COUNT("hive.llap.io.allocator.arena.count", 8, "Arena count for LLAP low-level cache; cache will be allocated in the steps of\n(size/arena_count) bytes. This size must be <= 1Gb and >= max allocation; if it is\nnot the case, an adjusted size will be used. Using powers of 2 is recommended."),
      LLAP_IO_MEMORY_MAX_SIZE("hive.llap.io.memory.size", "1Gb", new Validator.SizeValidator(), "Maximum size for IO allocator or ORC low-level cache.", "hive.llap.io.cache.orc.size"),
      LLAP_ALLOCATOR_DIRECT("hive.llap.io.allocator.direct", true, "Whether ORC low-level cache should use direct allocation."),
      LLAP_ALLOCATOR_MAPPED("hive.llap.io.allocator.mmap", false, "Whether ORC low-level cache should use memory mapped allocation (direct I/O). \nThis is recommended to be used along-side NVDIMM (DAX) or NVMe flash storage."),
      LLAP_ALLOCATOR_MAPPED_PATH("hive.llap.io.allocator.mmap.path", "/tmp", new Validator.WritableDirectoryValidator(), "The directory location for mapping NVDIMM/NVMe flash storage into the ORC low-level cache."),
      LLAP_USE_LRFU("hive.llap.io.use.lrfu", true, "Whether ORC low-level cache should use LRFU cache policy instead of default (FIFO)."),
      LLAP_LRFU_LAMBDA("hive.llap.io.lrfu.lambda", 0.01F, "Lambda for ORC low-level cache LRFU cache policy. Must be in [0, 1]. 0 makes LRFU\nbehave like LFU, 1 makes it behave like LRU, values in between balance accordingly."),
      LLAP_CACHE_ALLOW_SYNTHETIC_FILEID("hive.llap.cache.allow.synthetic.fileid", false, "Whether LLAP cache should use synthetic file ID if real one is not available. Systems\nlike HDFS, Isilon, etc. provide a unique file/inode ID. On other FSes (e.g. local\nFS), the cache would not work by default because LLAP is unable to uniquely track the\nfiles; enabling this setting allows LLAP to generate file ID from the path, size and\nmodification time, which is almost certain to identify file uniquely. However, if you\nuse a FS without file IDs and rewrite files a lot (or are paranoid), you might want\nto avoid this setting."),
      LLAP_CACHE_ENABLE_ORC_GAP_CACHE("hive.llap.orc.gap.cache", true, "Whether LLAP cache for ORC should remember gaps in ORC compression buffer read\nestimates, to avoid re-reading the data that was read once and discarded because it\nis unneeded. This is only necessary for ORC files written before HIVE-9660."),
      LLAP_IO_USE_FILEID_PATH("hive.llap.io.use.fileid.path", true, "Whether LLAP should use fileId (inode)-based path to ensure better consistency for the\ncases of file overwrites. This is supported on HDFS."),
      LLAP_IO_ENCODE_ENABLED("hive.llap.io.encode.enabled", true, "Whether LLAP should try to re-encode and cache data for non-ORC formats. This is used\non LLAP Server side to determine if the infrastructure for that is initialized."),
      LLAP_IO_ENCODE_FORMATS("hive.llap.io.encode.formats", "org.apache.hadoop.mapred.TextInputFormat,", "The table input formats for which LLAP IO should re-encode and cache data.\nComma-separated list."),
      LLAP_IO_ENCODE_ALLOC_SIZE("hive.llap.io.encode.alloc.size", "256Kb", new Validator.SizeValidator(), "Allocation size for the buffers used to cache encoded data from non-ORC files. Must\nbe a power of two between " + LLAP_ALLOCATOR_MIN_ALLOC + " and\n" + LLAP_ALLOCATOR_MAX_ALLOC + "."),
      LLAP_IO_ENCODE_VECTOR_SERDE_ENABLED("hive.llap.io.encode.vector.serde.enabled", true, "Whether LLAP should use vectorized SerDe reader to read text data when re-encoding."),
      LLAP_IO_ENCODE_VECTOR_SERDE_ASYNC_ENABLED("hive.llap.io.encode.vector.serde.async.enabled", true, "Whether LLAP should use async mode in vectorized SerDe reader to read text data."),
      LLAP_IO_ENCODE_SLICE_ROW_COUNT("hive.llap.io.encode.slice.row.count", 100000, "Row count to use to separate cache slices when reading encoded data from row-based\ninputs into LLAP cache, if this feature is enabled."),
      LLAP_IO_ENCODE_SLICE_LRR("hive.llap.io.encode.slice.lrr", true, "Whether to separate cache slices when reading encoded data from text inputs via MR\nMR LineRecordRedader into LLAP cache, if this feature is enabled. Safety flag."),
      LLAP_ORC_ENABLE_TIME_COUNTERS("hive.llap.io.orc.time.counters", true, "Whether to enable time counters for LLAP IO layer (time spent in HDFS, etc.)"),
      LLAP_AUTO_ALLOW_UBER("hive.llap.auto.allow.uber", false, "Whether or not to allow the planner to run vertices in the AM."),
      LLAP_AUTO_ENFORCE_TREE("hive.llap.auto.enforce.tree", true, "Enforce that all parents are in llap, before considering vertex"),
      LLAP_AUTO_ENFORCE_VECTORIZED("hive.llap.auto.enforce.vectorized", true, "Enforce that inputs are vectorized, before considering vertex"),
      LLAP_AUTO_ENFORCE_STATS("hive.llap.auto.enforce.stats", true, "Enforce that col stats are available, before considering vertex"),
      LLAP_AUTO_MAX_INPUT("hive.llap.auto.max.input.size", 10737418240L, "Check input size, before considering vertex (-1 disables check)"),
      LLAP_AUTO_MAX_OUTPUT("hive.llap.auto.max.output.size", 1073741824L, "Check output size, before considering vertex (-1 disables check)"),
      LLAP_SKIP_COMPILE_UDF_CHECK("hive.llap.skip.compile.udf.check", false, "Whether to skip the compile-time check for non-built-in UDFs when deciding whether to\nexecute tasks in LLAP. Skipping the check allows executing UDFs from pre-localized\njars in LLAP; if the jars are not pre-localized, the UDFs will simply fail to load."),
      LLAP_ALLOW_PERMANENT_FNS("hive.llap.allow.permanent.fns", true, "Whether LLAP decider should allow permanent UDFs."),
      LLAP_EXECUTION_MODE("hive.llap.execution.mode", "none", new Validator.StringSet(new String[]{"auto", "none", "all", "map", "only"}), "Chooses whether query fragments will run in container or in llap"),
      LLAP_OBJECT_CACHE_ENABLED("hive.llap.object.cache.enabled", true, "Cache objects (plans, hashtables, etc) in llap"),
      LLAP_IO_DECODING_METRICS_PERCENTILE_INTERVALS("hive.llap.io.decoding.metrics.percentiles.intervals", "30", "Comma-delimited set of integers denoting the desired rollover intervals (in seconds)\nfor percentile latency metrics on the LLAP daemon IO decoding time.\nhive.llap.queue.metrics.percentiles.intervals"),
      LLAP_IO_THREADPOOL_SIZE("hive.llap.io.threadpool.size", 10, "Specify the number of threads to use for low-level IO thread pool."),
      LLAP_KERBEROS_PRINCIPAL("hive.llap.daemon.service.principal", "", "The name of the LLAP daemon's service principal."),
      LLAP_KERBEROS_KEYTAB_FILE("hive.llap.daemon.keytab.file", "", "The path to the Kerberos Keytab file containing the LLAP daemon's service principal."),
      LLAP_ZKSM_KERBEROS_PRINCIPAL("hive.llap.zk.sm.principal", "", "The name of the principal to use to talk to ZooKeeper for ZooKeeper SecretManager."),
      LLAP_ZKSM_KERBEROS_KEYTAB_FILE("hive.llap.zk.sm.keytab.file", "", "The path to the Kerberos Keytab file containing the principal to use to talk to\nZooKeeper for ZooKeeper SecretManager."),
      LLAP_WEBUI_SPNEGO_KEYTAB_FILE("hive.llap.webui.spnego.keytab", "", "The path to the Kerberos Keytab file containing the LLAP WebUI SPNEGO principal.\nTypical value would look like /etc/security/keytabs/spnego.service.keytab."),
      LLAP_WEBUI_SPNEGO_PRINCIPAL("hive.llap.webui.spnego.principal", "", "The LLAP WebUI SPNEGO service principal. Configured similarly to\nhive.server2.webui.spnego.principal"),
      LLAP_FS_KERBEROS_PRINCIPAL("hive.llap.task.principal", "", "The name of the principal to use to run tasks. By default, the clients are required\nto provide tokens to access HDFS/etc."),
      LLAP_FS_KERBEROS_KEYTAB_FILE("hive.llap.task.keytab.file", "", "The path to the Kerberos Keytab file containing the principal to use to run tasks.\nBy default, the clients are required to provide tokens to access HDFS/etc."),
      LLAP_ZKSM_ZK_CONNECTION_STRING("hive.llap.zk.sm.connectionString", "", "ZooKeeper connection string for ZooKeeper SecretManager."),
      LLAP_ZK_REGISTRY_USER("hive.llap.zk.registry.user", "", "In the LLAP ZooKeeper-based registry, specifies the username in the Zookeeper path.\nThis should be the hive user or whichever user is running the LLAP daemon."),
      LLAP_ZK_REGISTRY_NAMESPACE("hive.llap.zk.registry.namespace", (Object)null, "In the LLAP ZooKeeper-based registry, overrides the ZK path namespace. Note that\nusing this makes the path management (e.g. setting correct ACLs) your responsibility."),
      LLAP_SECURITY_ACL("hive.llap.daemon.acl", "*", "The ACL for LLAP daemon."),
      LLAP_SECURITY_ACL_DENY("hive.llap.daemon.acl.blocked", "", "The deny ACL for LLAP daemon."),
      LLAP_MANAGEMENT_ACL("hive.llap.management.acl", "*", "The ACL for LLAP daemon management."),
      LLAP_MANAGEMENT_ACL_DENY("hive.llap.management.acl.blocked", "", "The deny ACL for LLAP daemon management."),
      LLAP_REMOTE_TOKEN_REQUIRES_SIGNING("hive.llap.remote.token.requires.signing", "true", new Validator.StringSet(new String[]{"false", "except_llap_owner", "true"}), "Whether the token returned from LLAP management API should require fragment signing.\nTrue by default; can be disabled to allow CLI to get tokens from LLAP in a secure\ncluster by setting it to true or 'except_llap_owner' (the latter returns such tokens\nto everyone except the user LLAP cluster is authenticating under)."),
      LLAP_DELEGATION_TOKEN_LIFETIME("hive.llap.daemon.delegation.token.lifetime", "14d", new Validator.TimeValidator(TimeUnit.SECONDS), "LLAP delegation token lifetime, in seconds if specified without a unit."),
      LLAP_MANAGEMENT_RPC_PORT("hive.llap.management.rpc.port", 15004, "RPC port for LLAP daemon management service."),
      LLAP_WEB_AUTO_AUTH("hive.llap.auto.auth", false, "Whether or not to set Hadoop configs to enable auth in LLAP web app."),
      LLAP_DAEMON_RPC_NUM_HANDLERS("hive.llap.daemon.rpc.num.handlers", 5, "Number of RPC handlers for LLAP daemon.", "llap.daemon.rpc.num.handlers"),
      LLAP_DAEMON_WORK_DIRS("hive.llap.daemon.work.dirs", "", "Working directories for the daemon. This should not be set if running as a YARN\napplication via Slider. It must be set when not running via Slider on YARN. If the value\nis set when running as a Slider YARN application, the specified value will be used.", "llap.daemon.work.dirs"),
      LLAP_DAEMON_YARN_SHUFFLE_PORT("hive.llap.daemon.yarn.shuffle.port", 15551, "YARN shuffle port for LLAP-daemon-hosted shuffle.", "llap.daemon.yarn.shuffle.port"),
      LLAP_DAEMON_YARN_CONTAINER_MB("hive.llap.daemon.yarn.container.mb", -1, "llap server yarn container size in MB. Used in LlapServiceDriver and package.py", "llap.daemon.yarn.container.mb"),
      LLAP_DAEMON_QUEUE_NAME("hive.llap.daemon.queue.name", (Object)null, "Queue name within which the llap slider application will run. Used in LlapServiceDriver and package.py"),
      LLAP_DAEMON_CONTAINER_ID("hive.llap.daemon.container.id", (Object)null, "ContainerId of a running LlapDaemon. Used to publish to the registry"),
      LLAP_DAEMON_NM_ADDRESS("hive.llap.daemon.nm.address", (Object)null, "NM Address host:rpcPort for the NodeManager on which the instance of the daemon is running.\nPublished to the llap registry. Should never be set by users"),
      LLAP_DAEMON_SHUFFLE_DIR_WATCHER_ENABLED("hive.llap.daemon.shuffle.dir.watcher.enabled", false, "TODO doc", "llap.daemon.shuffle.dir-watcher.enabled"),
      LLAP_DAEMON_AM_LIVENESS_HEARTBEAT_INTERVAL_MS("hive.llap.daemon.am.liveness.heartbeat.interval.ms", "10000ms", new Validator.TimeValidator(TimeUnit.MILLISECONDS), "Tez AM-LLAP heartbeat interval (milliseconds). This needs to be below the task timeout\ninterval, but otherwise as high as possible to avoid unnecessary traffic.", "llap.daemon.am.liveness.heartbeat.interval-ms"),
      LLAP_DAEMON_AM_LIVENESS_CONNECTION_TIMEOUT_MS("hive.llap.am.liveness.connection.timeout.ms", "10000ms", new Validator.TimeValidator(TimeUnit.MILLISECONDS), "Amount of time to wait on connection failures to the AM from an LLAP daemon before\nconsidering the AM to be dead.", "llap.am.liveness.connection.timeout-millis"),
      LLAP_DAEMON_AM_USE_FQDN("hive.llap.am.use.fqdn", false, "Whether to use FQDN of the AM machine when submitting work to LLAP."),
      LLAP_DAEMON_AM_LIVENESS_CONNECTION_SLEEP_BETWEEN_RETRIES_MS("hive.llap.am.liveness.connection.sleep.between.retries.ms", "2000ms", new Validator.TimeValidator(TimeUnit.MILLISECONDS), "Sleep duration while waiting to retry connection failures to the AM from the daemon for\nthe general keep-alive thread (milliseconds).", "llap.am.liveness.connection.sleep-between-retries-millis"),
      LLAP_DAEMON_TASK_SCHEDULER_TIMEOUT_SECONDS("hive.llap.task.scheduler.timeout.seconds", "60s", new Validator.TimeValidator(TimeUnit.SECONDS), "Amount of time to wait before failing the query when there are no llap daemons running\n(alive) in the cluster.", "llap.daemon.scheduler.timeout.seconds"),
      LLAP_DAEMON_NUM_EXECUTORS("hive.llap.daemon.num.executors", 4, "Number of executors to use in LLAP daemon; essentially, the number of tasks that can be\nexecuted in parallel.", "llap.daemon.num.executors"),
      LLAP_DAEMON_AM_REPORTER_MAX_THREADS("hive.llap.daemon.am-reporter.max.threads", 4, "Maximum number of threads to be used for AM reporter. If this is lower than number of\nexecutors in llap daemon, it would be set to number of executors at runtime.", "llap.daemon.am-reporter.max.threads"),
      LLAP_DAEMON_RPC_PORT("hive.llap.daemon.rpc.port", 0, "The LLAP daemon RPC port.", "llap.daemon.rpc.port. A value of 0 indicates a dynamic port"),
      LLAP_DAEMON_MEMORY_PER_INSTANCE_MB("hive.llap.daemon.memory.per.instance.mb", 4096, "The total amount of memory to use for the executors inside LLAP (in megabytes).", "llap.daemon.memory.per.instance.mb"),
      LLAP_DAEMON_XMX_HEADROOM("hive.llap.daemon.xmx.headroom", "5%", "The total amount of heap memory set aside by LLAP and not used by the executors. Can\nbe specified as size (e.g. '512Mb'), or percentage (e.g. '5%'). Note that the latter is\nderived from the total daemon XMX, which can be different from the total executor\nmemory if the cache is on-heap; although that's not the default configuration."),
      LLAP_DAEMON_VCPUS_PER_INSTANCE("hive.llap.daemon.vcpus.per.instance", 4, "The total number of vcpus to use for the executors inside LLAP.", "llap.daemon.vcpus.per.instance"),
      LLAP_DAEMON_NUM_FILE_CLEANER_THREADS("hive.llap.daemon.num.file.cleaner.threads", 1, "Number of file cleaner threads in LLAP.", "llap.daemon.num.file.cleaner.threads"),
      LLAP_FILE_CLEANUP_DELAY_SECONDS("hive.llap.file.cleanup.delay.seconds", "300s", new Validator.TimeValidator(TimeUnit.SECONDS), "How long to delay before cleaning up query files in LLAP (in seconds, for debugging).", "llap.file.cleanup.delay-seconds"),
      LLAP_DAEMON_SERVICE_HOSTS("hive.llap.daemon.service.hosts", (Object)null, "Explicitly specified hosts to use for LLAP scheduling. Useful for testing. By default,\nYARN registry is used.", "llap.daemon.service.hosts"),
      LLAP_DAEMON_SERVICE_REFRESH_INTERVAL("hive.llap.daemon.service.refresh.interval.sec", "60s", new Validator.TimeValidator(TimeUnit.SECONDS), "LLAP YARN registry service list refresh delay, in seconds.", "llap.daemon.service.refresh.interval"),
      LLAP_DAEMON_COMMUNICATOR_NUM_THREADS("hive.llap.daemon.communicator.num.threads", 10, "Number of threads to use in LLAP task communicator in Tez AM.", "llap.daemon.communicator.num.threads"),
      LLAP_DAEMON_DOWNLOAD_PERMANENT_FNS("hive.llap.daemon.download.permanent.fns", false, "Whether LLAP daemon should localize the resources for permanent UDFs."),
      LLAP_TASK_SCHEDULER_NODE_REENABLE_MIN_TIMEOUT_MS("hive.llap.task.scheduler.node.reenable.min.timeout.ms", "200ms", new Validator.TimeValidator(TimeUnit.MILLISECONDS), "Minimum time after which a previously disabled node will be re-enabled for scheduling,\nin milliseconds. This may be modified by an exponential back-off if failures persist.", "llap.task.scheduler.node.re-enable.min.timeout.ms"),
      LLAP_TASK_SCHEDULER_NODE_REENABLE_MAX_TIMEOUT_MS("hive.llap.task.scheduler.node.reenable.max.timeout.ms", "10000ms", new Validator.TimeValidator(TimeUnit.MILLISECONDS), "Maximum time after which a previously disabled node will be re-enabled for scheduling,\nin milliseconds. This may be modified by an exponential back-off if failures persist.", "llap.task.scheduler.node.re-enable.max.timeout.ms"),
      LLAP_TASK_SCHEDULER_NODE_DISABLE_BACK_OFF_FACTOR("hive.llap.task.scheduler.node.disable.backoff.factor", 1.5F, "Backoff factor on successive blacklists of a node due to some failures. Blacklist times\nstart at the min timeout and go up to the max timeout based on this backoff factor.", "llap.task.scheduler.node.disable.backoff.factor"),
      LLAP_TASK_SCHEDULER_NUM_SCHEDULABLE_TASKS_PER_NODE("hive.llap.task.scheduler.num.schedulable.tasks.per.node", 0, "The number of tasks the AM TaskScheduler will try allocating per node. 0 indicates that\nthis should be picked up from the Registry. -1 indicates unlimited capacity; positive\nvalues indicate a specific bound.", "llap.task.scheduler.num.schedulable.tasks.per.node"),
      LLAP_TASK_SCHEDULER_LOCALITY_DELAY("hive.llap.task.scheduler.locality.delay", "0ms", new Validator.TimeValidator(TimeUnit.MILLISECONDS, -1L, true, Long.MAX_VALUE, true), "Amount of time to wait before allocating a request which contains location information, to a location other than the ones requested. Set to -1 for an infinite delay, 0for no delay."),
      LLAP_DAEMON_TASK_PREEMPTION_METRICS_INTERVALS("hive.llap.daemon.task.preemption.metrics.intervals", "30,60,300", "Comma-delimited set of integers denoting the desired rollover intervals (in seconds)\n for percentile latency metrics. Used by LLAP daemon task scheduler metrics for\n time taken to kill task (due to pre-emption) and useful time wasted by the task that\n is about to be preempted."),
      LLAP_DAEMON_TASK_SCHEDULER_WAIT_QUEUE_SIZE("hive.llap.daemon.task.scheduler.wait.queue.size", 10, "LLAP scheduler maximum queue size.", "llap.daemon.task.scheduler.wait.queue.size"),
      LLAP_DAEMON_WAIT_QUEUE_COMPARATOR_CLASS_NAME("hive.llap.daemon.wait.queue.comparator.class.name", "org.apache.hadoop.hive.llap.daemon.impl.comparator.ShortestJobFirstComparator", "The priority comparator to use for LLAP scheduler prioroty queue. The built-in options\nare org.apache.hadoop.hive.llap.daemon.impl.comparator.ShortestJobFirstComparator and\n.....FirstInFirstOutComparator", "llap.daemon.wait.queue.comparator.class.name"),
      LLAP_DAEMON_TASK_SCHEDULER_ENABLE_PREEMPTION("hive.llap.daemon.task.scheduler.enable.preemption", true, "Whether non-finishable running tasks (e.g. a reducer waiting for inputs) should be\npreempted by finishable tasks inside LLAP scheduler.", "llap.daemon.task.scheduler.enable.preemption"),
      LLAP_TASK_COMMUNICATOR_CONNECTION_TIMEOUT_MS("hive.llap.task.communicator.connection.timeout.ms", "16000ms", new Validator.TimeValidator(TimeUnit.MILLISECONDS), "Connection timeout (in milliseconds) before a failure to an LLAP daemon from Tez AM.", "llap.task.communicator.connection.timeout-millis"),
      LLAP_TASK_COMMUNICATOR_LISTENER_THREAD_COUNT("hive.llap.task.communicator.listener.thread-count", 30, "The number of task communicator listener threads."),
      LLAP_TASK_COMMUNICATOR_CONNECTION_SLEEP_BETWEEN_RETRIES_MS("hive.llap.task.communicator.connection.sleep.between.retries.ms", "2000ms", new Validator.TimeValidator(TimeUnit.MILLISECONDS), "Sleep duration (in milliseconds) to wait before retrying on error when obtaining a\nconnection to LLAP daemon from Tez AM.", "llap.task.communicator.connection.sleep-between-retries-millis"),
      LLAP_DAEMON_WEB_PORT("hive.llap.daemon.web.port", 15002, "LLAP daemon web UI port.", "llap.daemon.service.port"),
      LLAP_DAEMON_WEB_SSL("hive.llap.daemon.web.ssl", false, "Whether LLAP daemon web UI should use SSL.", "llap.daemon.service.ssl"),
      LLAP_CLIENT_CONSISTENT_SPLITS("hive.llap.client.consistent.splits", false, "Whether to setup split locations to match nodes on which llap daemons are running, instead of using the locations provided by the split itself. If there is no llap daemon running, fall back to locations provided by the split. This is effective only if hive.execution.mode is llap"),
      LLAP_VALIDATE_ACLS("hive.llap.validate.acls", true, "Whether LLAP should reject permissive ACLs in some cases (e.g. its own management\nprotocol or ZK paths), similar to how ssh refuses a key with bad access permissions."),
      LLAP_DAEMON_OUTPUT_SERVICE_PORT("hive.llap.daemon.output.service.port", 15003, "LLAP daemon output service port"),
      LLAP_DAEMON_OUTPUT_STREAM_TIMEOUT("hive.llap.daemon.output.stream.timeout", "120s", new Validator.TimeValidator(TimeUnit.SECONDS), "The timeout for the client to connect to LLAP output service and start the fragment\noutput after sending the fragment. The fragment will fail if its output is not claimed."),
      LLAP_DAEMON_OUTPUT_SERVICE_SEND_BUFFER_SIZE("hive.llap.daemon.output.service.send.buffer.size", 131072, "Send buffer size to be used by LLAP daemon output service"),
      LLAP_DAEMON_OUTPUT_SERVICE_MAX_PENDING_WRITES("hive.llap.daemon.output.service.max.pending.writes", 8, "Maximum number of queued writes allowed per connection when sending data\n via the LLAP output service to external clients."),
      LLAP_ENABLE_GRACE_JOIN_IN_LLAP("hive.llap.enable.grace.join.in.llap", false, "Override if grace join should be allowed to run in llap."),
      LLAP_HS2_ENABLE_COORDINATOR("hive.llap.hs2.coordinator.enabled", true, "Whether to create the LLAP coordinator; since execution engine and container vs llap\nsettings are both coming from job configs, we don't know at start whether this should\nbe created. Default true."),
      LLAP_DAEMON_LOGGER("hive.llap.daemon.logger", "query-routing", new Validator.StringSet(new String[]{"query-routing", "RFA", "console"}), "logger used for llap-daemons."),
      SPARK_USE_OP_STATS("hive.spark.use.op.stats", true, "Whether to use operator stats to determine reducer parallelism for Hive on Spark. If this is false, Hive will use source table stats to determine reducer parallelism for all first level reduce tasks, and the maximum reducer parallelism from all parents for all the rest (second level and onward) reducer tasks."),
      SPARK_USE_FILE_SIZE_FOR_MAPJOIN("hive.spark.use.file.size.for.mapjoin", false, "If this is set to true, mapjoin optimization in Hive/Spark will use source file sizes associated with TableScan operator on the root of operator tree, instead of using operator statistics."),
      SPARK_CLIENT_FUTURE_TIMEOUT("hive.spark.client.future.timeout", "60s", new Validator.TimeValidator(TimeUnit.SECONDS), "Timeout for requests from Hive client to remote Spark driver."),
      SPARK_JOB_MONITOR_TIMEOUT("hive.spark.job.monitor.timeout", "60s", new Validator.TimeValidator(TimeUnit.SECONDS), "Timeout for job monitor to get Spark job state."),
      SPARK_RPC_CLIENT_CONNECT_TIMEOUT("hive.spark.client.connect.timeout", "1000ms", new Validator.TimeValidator(TimeUnit.MILLISECONDS), "Timeout for remote Spark driver in connecting back to Hive client."),
      SPARK_RPC_CLIENT_HANDSHAKE_TIMEOUT("hive.spark.client.server.connect.timeout", "90000ms", new Validator.TimeValidator(TimeUnit.MILLISECONDS), "Timeout for handshake between Hive client and remote Spark driver.  Checked by both processes."),
      SPARK_RPC_SECRET_RANDOM_BITS("hive.spark.client.secret.bits", "256", "Number of bits of randomness in the generated secret for communication between Hive client and remote Spark driver. Rounded down to the nearest multiple of 8."),
      SPARK_RPC_MAX_THREADS("hive.spark.client.rpc.threads", 8, "Maximum number of threads for remote Spark driver's RPC event loop."),
      SPARK_RPC_MAX_MESSAGE_SIZE("hive.spark.client.rpc.max.size", 52428800, "Maximum message size in bytes for communication between Hive client and remote Spark driver. Default is 50MB."),
      SPARK_RPC_CHANNEL_LOG_LEVEL("hive.spark.client.channel.log.level", (Object)null, "Channel logging level for remote Spark driver.  One of {DEBUG, ERROR, INFO, TRACE, WARN}."),
      SPARK_RPC_SASL_MECHANISM("hive.spark.client.rpc.sasl.mechanisms", "DIGEST-MD5", "Name of the SASL mechanism to use for authentication."),
      SPARK_RPC_SERVER_ADDRESS("hive.spark.client.rpc.server.address", "", "The server address of HiverServer2 host to be used for communication between Hive client and remote Spark driver. Default is empty, which means the address will be determined in the same way as for hive.server2.thrift.bind.host.This is only necessary if the host has mutiple network addresses and if a different network address other than hive.server2.thrift.bind.host is to be used."),
      SPARK_RPC_SERVER_PORT("hive.spark.client.rpc.server.port", "", "A list of port ranges which can be used by RPC server with the format of 49152-49222,49228 and a random one is selected from the list. Default is empty, which randomly selects one port from all available ones."),
      SPARK_DYNAMIC_PARTITION_PRUNING("hive.spark.dynamic.partition.pruning", false, "When dynamic pruning is enabled, joins on partition keys will be processed by writing\nto a temporary HDFS file, and read later for removing unnecessary partitions."),
      SPARK_DYNAMIC_PARTITION_PRUNING_MAX_DATA_SIZE("hive.spark.dynamic.partition.pruning.max.data.size", 104857600L, "Maximum total data size in dynamic pruning."),
      SPARK_USE_GROUPBY_SHUFFLE("hive.spark.use.groupby.shuffle", true, "Spark groupByKey transformation has better performance but uses unbounded memory.Turn this off when there is a memory issue."),
      NWAYJOINREORDER("hive.reorder.nway.joins", true, "Runs reordering of tables within single n-way join (i.e.: picks streamtable)"),
      HIVE_MERGE_NWAY_JOINS("hive.merge.nway.joins", true, "Merge adjacent joins into a single n-way join"),
      HIVE_LOG_N_RECORDS("hive.log.every.n.records", 0L, new Validator.RangeValidator(0L, (Object)null), "If value is greater than 0 logs in fixed intervals of size n rather than exponentially."),
      HIVE_MSCK_PATH_VALIDATION("hive.msck.path.validation", "throw", new Validator.StringSet(new String[]{"throw", "skip", "ignore"}), "The approach msck should take with HDFS directories that are partition-like but contain unsupported characters. 'throw' (an exception) is the default; 'skip' will skip the invalid directories and still repair the others; 'ignore' will skip the validation (legacy behavior, causes bugs in many cases)"),
      HIVE_MSCK_REPAIR_BATCH_SIZE("hive.msck.repair.batch.size", 0, "Batch size for the msck repair command. If the value is greater than zero, it will execute batch wise with the configured batch size. The default value is zero. Zero means it will execute directly (Not batch wise)"),
      HIVE_SERVER2_LLAP_CONCURRENT_QUERIES("hive.server2.llap.concurrent.queries", -1, "The number of queries allowed in parallel via llap. Negative number implies 'infinite'."),
      HIVE_TEZ_ENABLE_MEMORY_MANAGER("hive.tez.enable.memory.manager", true, "Enable memory manager for tez"),
      HIVE_HASH_TABLE_INFLATION_FACTOR("hive.hash.table.inflation.factor", 2.0F, "Expected inflation factor between disk/in memory representation of hash tables"),
      HIVE_LOG_TRACE_ID("hive.log.trace.id", "", "Log tracing id that can be used by upstream clients for tracking respective logs. Truncated to 64 characters. Defaults to use auto-generated session id."),
      HIVE_CONF_RESTRICTED_LIST("hive.conf.restricted.list", "hive.security.authenticator.manager,hive.security.authorization.manager,hive.security.metastore.authorization.manager,hive.security.metastore.authenticator.manager,hive.users.in.admin.role,hive.server2.xsrf.filter.enabled,hive.security.authorization.enabled,hive.server2.authentication.ldap.baseDN,hive.server2.authentication.ldap.url,hive.server2.authentication.ldap.Domain,hive.server2.authentication.ldap.groupDNPattern,hive.server2.authentication.ldap.groupFilter,hive.server2.authentication.ldap.userDNPattern,hive.server2.authentication.ldap.userFilter,hive.server2.authentication.ldap.groupMembershipKey,hive.server2.authentication.ldap.userMembershipKey,hive.server2.authentication.ldap.groupClassKey,hive.server2.authentication.ldap.customLDAPQuery", "Comma separated list of configuration options which are immutable at runtime"),
      HIVE_CONF_HIDDEN_LIST("hive.conf.hidden.list", METASTOREPWD.varname + "," + HIVE_SERVER2_SSL_KEYSTORE_PASSWORD.varname + ",fs.s3.awsAccessKeyId,fs.s3.awsSecretAccessKey,fs.s3n.awsAccessKeyId,fs.s3n.awsSecretAccessKey,fs.s3a.access.key,fs.s3a.secret.key,fs.s3a.proxy.password", "Comma separated list of configuration options which should not be read by normal user like passwords"),
      HIVE_CONF_INTERNAL_VARIABLE_LIST("hive.conf.internal.variable.list", "hive.added.files.path,hive.added.jars.path,hive.added.archives.path", "Comma separated list of variables which are used internally and should not be configurable."),
      HIVE_QUERY_TIMEOUT_SECONDS("hive.query.timeout.seconds", "0s", new Validator.TimeValidator(TimeUnit.SECONDS), "Timeout for Running Query in seconds. A nonpositive value means infinite. If the query timeout is also set by thrift API call, the smaller one will be taken."),
      HIVE_EXEC_INPUT_LISTING_MAX_THREADS("hive.exec.input.listing.max.threads", 0, new Validator.SizeValidator(0L, true, 1024L, true), "Maximum number of threads that Hive uses to list file information from file systems (recommended > 1 for blobstore)."),
      HIVE_BLOBSTORE_SUPPORTED_SCHEMES("hive.blobstore.supported.schemes", "s3,s3a,s3n", "Comma-separated list of supported blobstore schemes."),
      HIVE_BLOBSTORE_USE_BLOBSTORE_AS_SCRATCHDIR("hive.blobstore.use.blobstore.as.scratchdir", false, "Enable the use of scratch directories directly on blob storage systems (it may cause performance penalties)."),
      HIVE_BLOBSTORE_OPTIMIZATIONS_ENABLED("hive.blobstore.optimizations.enabled", true, "This parameter enables a number of optimizations when running on blobstores:\n(1) If hive.blobstore.use.blobstore.as.scratchdir is false, force the last Hive job to write to the blobstore.\nThis is a performance optimization that forces the final FileSinkOperator to write to the blobstore.\nSee HIVE-15121 for details.");

      public final String varname;
      public final String altName;
      private final String defaultExpr;
      public final String defaultStrVal;
      public final int defaultIntVal;
      public final long defaultLongVal;
      public final float defaultFloatVal;
      public final boolean defaultBoolVal;
      private final Class valClass;
      private final VarType valType;
      private final Validator validator;
      private final String description;
      private final boolean excluded;
      private final boolean caseSensitive;

      private ConfVars(String varname, Object defaultVal, String description) {
         this(varname, defaultVal, (Validator)null, description, true, false, (String)null);
      }

      private ConfVars(String varname, Object defaultVal, String description, String altName) {
         this(varname, defaultVal, (Validator)null, description, true, false, altName);
      }

      private ConfVars(String varname, Object defaultVal, Validator validator, String description, String altName) {
         this(varname, defaultVal, validator, description, true, false, altName);
      }

      private ConfVars(String varname, Object defaultVal, String description, boolean excluded) {
         this(varname, defaultVal, (Validator)null, description, true, excluded, (String)null);
      }

      private ConfVars(String varname, String defaultVal, boolean caseSensitive, String description) {
         this(varname, defaultVal, (Validator)null, description, caseSensitive, false, (String)null);
      }

      private ConfVars(String varname, Object defaultVal, Validator validator, String description) {
         this(varname, defaultVal, validator, description, true, false, (String)null);
      }

      private ConfVars(String varname, Object defaultVal, Validator validator, String description, boolean caseSensitive, boolean excluded, String altName) {
         this.varname = varname;
         this.validator = validator;
         this.description = description;
         this.defaultExpr = defaultVal == null ? null : String.valueOf(defaultVal);
         this.excluded = excluded;
         this.caseSensitive = caseSensitive;
         this.altName = altName;
         if (defaultVal != null && !(defaultVal instanceof String)) {
            if (defaultVal instanceof Integer) {
               this.valClass = Integer.class;
               this.valType = HiveConf.ConfVars.VarType.INT;
               this.defaultStrVal = null;
               this.defaultIntVal = (Integer)defaultVal;
               this.defaultLongVal = -1L;
               this.defaultFloatVal = -1.0F;
               this.defaultBoolVal = false;
            } else if (defaultVal instanceof Long) {
               this.valClass = Long.class;
               this.valType = HiveConf.ConfVars.VarType.LONG;
               this.defaultStrVal = null;
               this.defaultIntVal = -1;
               this.defaultLongVal = (Long)defaultVal;
               this.defaultFloatVal = -1.0F;
               this.defaultBoolVal = false;
            } else if (defaultVal instanceof Float) {
               this.valClass = Float.class;
               this.valType = HiveConf.ConfVars.VarType.FLOAT;
               this.defaultStrVal = null;
               this.defaultIntVal = -1;
               this.defaultLongVal = -1L;
               this.defaultFloatVal = (Float)defaultVal;
               this.defaultBoolVal = false;
            } else {
               if (!(defaultVal instanceof Boolean)) {
                  throw new IllegalArgumentException("Not supported type value " + defaultVal.getClass() + " for name " + varname);
               }

               this.valClass = Boolean.class;
               this.valType = HiveConf.ConfVars.VarType.BOOLEAN;
               this.defaultStrVal = null;
               this.defaultIntVal = -1;
               this.defaultLongVal = -1L;
               this.defaultFloatVal = -1.0F;
               this.defaultBoolVal = (Boolean)defaultVal;
            }
         } else {
            this.valClass = String.class;
            this.valType = HiveConf.ConfVars.VarType.STRING;
            this.defaultStrVal = SystemVariables.substitute((String)defaultVal);
            this.defaultIntVal = -1;
            this.defaultLongVal = -1L;
            this.defaultFloatVal = -1.0F;
            this.defaultBoolVal = false;
         }

      }

      public boolean isType(String value) {
         return this.valType.isType(value);
      }

      public Validator getValidator() {
         return this.validator;
      }

      public String validate(String value) {
         return this.validator == null ? null : this.validator.validate(value);
      }

      public String validatorDescription() {
         return this.validator == null ? null : this.validator.toDescription();
      }

      public String typeString() {
         String type = this.valType.typeString();
         if (this.valType == HiveConf.ConfVars.VarType.STRING && this.validator != null && this.validator instanceof Validator.TimeValidator) {
            type = type + "(TIME)";
         }

         return type;
      }

      public String getRawDescription() {
         return this.description;
      }

      public String getDescription() {
         String validator = this.validatorDescription();
         return validator != null ? validator + ".\n" + this.description : this.description;
      }

      public boolean isExcluded() {
         return this.excluded;
      }

      public boolean isCaseSensitive() {
         return this.caseSensitive;
      }

      public String toString() {
         return this.varname;
      }

      private static String findHadoopBinary() {
         String val = findHadoopHome();
         val = (val == null ? File.separator + "usr" : val) + File.separator + "bin" + File.separator + "hadoop";
         return val;
      }

      private static String findYarnBinary() {
         String val = findHadoopHome();
         val = val == null ? "yarn" : val + File.separator + "bin" + File.separator + "yarn";
         return val;
      }

      private static String findHadoopHome() {
         String val = System.getenv("HADOOP_HOME");
         if (val == null) {
            val = System.getenv("HADOOP_PREFIX");
         }

         return val;
      }

      public String getDefaultValue() {
         return this.valType.defaultValueString(this);
      }

      public String getDefaultExpr() {
         return this.defaultExpr;
      }

      private Set getValidStringValues() {
         if (this.validator != null && this.validator instanceof Validator.StringSet) {
            return ((Validator.StringSet)this.validator).getExpected();
         } else {
            throw new RuntimeException(this.varname + " does not specify a list of valid values");
         }
      }

      static enum VarType {
         STRING {
            void checkType(String value) throws Exception {
            }

            String defaultValueString(ConfVars confVar) {
               return confVar.defaultStrVal;
            }
         },
         INT {
            void checkType(String value) throws Exception {
               Integer.valueOf(value);
            }
         },
         LONG {
            void checkType(String value) throws Exception {
               Long.valueOf(value);
            }
         },
         FLOAT {
            void checkType(String value) throws Exception {
               Float.valueOf(value);
            }
         },
         BOOLEAN {
            void checkType(String value) throws Exception {
               Boolean.valueOf(value);
            }
         };

         private VarType() {
         }

         boolean isType(String value) {
            try {
               this.checkType(value);
               return true;
            } catch (Exception var3) {
               return false;
            }
         }

         String typeString() {
            return this.name().toUpperCase();
         }

         String defaultValueString(ConfVars confVar) {
            return confVar.defaultExpr;
         }

         abstract void checkType(String var1) throws Exception;
      }
   }

   public static class StrictChecks {
      private static final String NO_LIMIT_MSG;
      private static final String NO_PARTITIONLESS_MSG;
      private static final String NO_COMPARES_MSG;
      private static final String NO_CARTESIAN_MSG;
      private static final String NO_BUCKETING_MSG;

      private static String makeMessage(String what, ConfVars setting) {
         return what + " are disabled for safety reasons. If you know what you are doing, please set" + setting.varname + " to false and that " + HiveConf.ConfVars.HIVEMAPREDMODE.varname + " is not set to 'strict' to proceed. Note that if you may get errors or incorrect results if you make a mistake while using some of the unsafe features.";
      }

      public static String checkNoLimit(Configuration conf) {
         return isAllowed(conf, HiveConf.ConfVars.HIVE_STRICT_CHECKS_LARGE_QUERY) ? null : NO_LIMIT_MSG;
      }

      public static String checkNoPartitionFilter(Configuration conf) {
         return isAllowed(conf, HiveConf.ConfVars.HIVE_STRICT_CHECKS_LARGE_QUERY) ? null : NO_PARTITIONLESS_MSG;
      }

      public static String checkTypeSafety(Configuration conf) {
         return isAllowed(conf, HiveConf.ConfVars.HIVE_STRICT_CHECKS_TYPE_SAFETY) ? null : NO_COMPARES_MSG;
      }

      public static String checkCartesian(Configuration conf) {
         return isAllowed(conf, HiveConf.ConfVars.HIVE_STRICT_CHECKS_CARTESIAN) ? null : NO_CARTESIAN_MSG;
      }

      public static String checkBucketing(Configuration conf) {
         return isAllowed(conf, HiveConf.ConfVars.HIVE_STRICT_CHECKS_BUCKETING) ? null : NO_BUCKETING_MSG;
      }

      private static boolean isAllowed(Configuration conf, ConfVars setting) {
         String mode = HiveConf.getVar(conf, HiveConf.ConfVars.HIVEMAPREDMODE, (String)null);
         return mode != null ? !"strict".equals(mode) : !HiveConf.getBoolVar(conf, setting);
      }

      static {
         NO_LIMIT_MSG = makeMessage("Order by-s without limit", HiveConf.ConfVars.HIVE_STRICT_CHECKS_LARGE_QUERY);
         NO_PARTITIONLESS_MSG = makeMessage("Queries against partitioned tables without a partition filter", HiveConf.ConfVars.HIVE_STRICT_CHECKS_LARGE_QUERY);
         NO_COMPARES_MSG = makeMessage("Unsafe compares between different types", HiveConf.ConfVars.HIVE_STRICT_CHECKS_TYPE_SAFETY);
         NO_CARTESIAN_MSG = makeMessage("Cartesian products", HiveConf.ConfVars.HIVE_STRICT_CHECKS_CARTESIAN);
         NO_BUCKETING_MSG = makeMessage("Load into bucketed tables", HiveConf.ConfVars.HIVE_STRICT_CHECKS_BUCKETING);
      }
   }

   public interface EncoderDecoder {
      Object encode(Object var1);

      Object decode(Object var1);
   }
}
