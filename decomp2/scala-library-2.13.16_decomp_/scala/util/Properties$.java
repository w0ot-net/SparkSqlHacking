package scala.util;

import java.util.jar.Attributes;
import scala.Function0;
import scala.Option;

public final class Properties$ implements PropertiesTrait {
   public static final Properties$ MODULE$ = new Properties$();
   private static final Attributes.Name ScalaCompilerVersion;
   private static String propFilename;
   private static java.util.Properties scalaProps;
   private static Option releaseVersion;
   private static Option developmentVersion;
   private static String versionString;
   private static String copyrightString;
   private static boolean isWin;
   private static boolean isMac;
   private static boolean isLinux;
   private static boolean isAvian;
   private static boolean consoleIsTerminal;
   private static volatile byte bitmap$0;

   static {
      PropertiesTrait.$init$(MODULE$);
      ScalaCompilerVersion = new Attributes.Name("Scala-Compiler-Version");
   }

   public boolean propIsSet(final String name) {
      return PropertiesTrait.propIsSet$(this, name);
   }

   public boolean propIsSetTo(final String name, final String value) {
      return PropertiesTrait.propIsSetTo$(this, name, value);
   }

   public String propOrElse(final String name, final Function0 alt) {
      return PropertiesTrait.propOrElse$(this, name, alt);
   }

   public String propOrEmpty(final String name) {
      return PropertiesTrait.propOrEmpty$(this, name);
   }

   public String propOrNull(final String name) {
      return PropertiesTrait.propOrNull$(this, name);
   }

   public Option propOrNone(final String name) {
      return PropertiesTrait.propOrNone$(this, name);
   }

   public boolean propOrFalse(final String name) {
      return PropertiesTrait.propOrFalse$(this, name);
   }

   public String setProp(final String name, final String value) {
      return PropertiesTrait.setProp$(this, name, value);
   }

   public String clearProp(final String name) {
      return PropertiesTrait.clearProp$(this, name);
   }

   public String envOrElse(final String name, final Function0 alt) {
      return PropertiesTrait.envOrElse$(this, name, alt);
   }

   public Option envOrNone(final String name) {
      return PropertiesTrait.envOrNone$(this, name);
   }

   public Option envOrSome(final String name, final Function0 alt) {
      return PropertiesTrait.envOrSome$(this, name, alt);
   }

   public String scalaPropOrElse(final String name, final Function0 alt) {
      return PropertiesTrait.scalaPropOrElse$(this, name, alt);
   }

   public String scalaPropOrEmpty(final String name) {
      return PropertiesTrait.scalaPropOrEmpty$(this, name);
   }

   public Option scalaPropOrNone(final String name) {
      return PropertiesTrait.scalaPropOrNone$(this, name);
   }

   public String versionNumberString() {
      return PropertiesTrait.versionNumberString$(this);
   }

   public String sourceEncoding() {
      return PropertiesTrait.sourceEncoding$(this);
   }

   public String sourceReader() {
      return PropertiesTrait.sourceReader$(this);
   }

   public String encodingString() {
      return PropertiesTrait.encodingString$(this);
   }

   public String lineSeparator() {
      return PropertiesTrait.lineSeparator$(this);
   }

   public String javaClassPath() {
      return PropertiesTrait.javaClassPath$(this);
   }

   public String javaHome() {
      return PropertiesTrait.javaHome$(this);
   }

   public String javaVendor() {
      return PropertiesTrait.javaVendor$(this);
   }

   public String javaVersion() {
      return PropertiesTrait.javaVersion$(this);
   }

   public String javaVmInfo() {
      return PropertiesTrait.javaVmInfo$(this);
   }

   public String javaVmName() {
      return PropertiesTrait.javaVmName$(this);
   }

   public String javaVmVendor() {
      return PropertiesTrait.javaVmVendor$(this);
   }

   public String javaVmVersion() {
      return PropertiesTrait.javaVmVersion$(this);
   }

   public String javaSpecVersion() {
      return PropertiesTrait.javaSpecVersion$(this);
   }

   public String javaSpecVendor() {
      return PropertiesTrait.javaSpecVendor$(this);
   }

   public String javaSpecName() {
      return PropertiesTrait.javaSpecName$(this);
   }

   public String osName() {
      return PropertiesTrait.osName$(this);
   }

   public String scalaHome() {
      return PropertiesTrait.scalaHome$(this);
   }

   public String tmpDir() {
      return PropertiesTrait.tmpDir$(this);
   }

   public String userDir() {
      return PropertiesTrait.userDir$(this);
   }

   public String userHome() {
      return PropertiesTrait.userHome$(this);
   }

   public String userName() {
      return PropertiesTrait.userName$(this);
   }

   public boolean coloredOutputEnabled() {
      return PropertiesTrait.coloredOutputEnabled$(this);
   }

   public String jdkHome() {
      return PropertiesTrait.jdkHome$(this);
   }

   public String versionFor(final String command) {
      return PropertiesTrait.versionFor$(this, command);
   }

   public String versionMsg() {
      return PropertiesTrait.versionMsg$(this);
   }

   public String scalaCmd() {
      return PropertiesTrait.scalaCmd$(this);
   }

   public String scalacCmd() {
      return PropertiesTrait.scalacCmd$(this);
   }

   public boolean isJavaAtLeast(final String version) {
      return PropertiesTrait.isJavaAtLeast$(this, version);
   }

   public boolean isJavaAtLeast(final int version) {
      return PropertiesTrait.isJavaAtLeast$(this, version);
   }

   public void main(final String[] args) {
      PropertiesTrait.main$(this, args);
   }

   public String propFilename() {
      return propFilename;
   }

   private java.util.Properties scalaProps$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(bitmap$0 & 1) == 0) {
            scalaProps = PropertiesTrait.scalaProps$(this);
            bitmap$0 = (byte)(bitmap$0 | 1);
         }
      } catch (Throwable var2) {
         throw var2;
      }

      return scalaProps;
   }

   public java.util.Properties scalaProps() {
      return (byte)(bitmap$0 & 1) == 0 ? this.scalaProps$lzycompute() : scalaProps;
   }

   public Option releaseVersion() {
      return releaseVersion;
   }

   public Option developmentVersion() {
      return developmentVersion;
   }

   public String versionString() {
      return versionString;
   }

   public String copyrightString() {
      return copyrightString;
   }

   private boolean isWin$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(bitmap$0 & 2) == 0) {
            isWin = PropertiesTrait.isWin$(this);
            bitmap$0 = (byte)(bitmap$0 | 2);
         }
      } catch (Throwable var2) {
         throw var2;
      }

      return isWin;
   }

   public boolean isWin() {
      return (byte)(bitmap$0 & 2) == 0 ? this.isWin$lzycompute() : isWin;
   }

   private boolean isMac$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(bitmap$0 & 4) == 0) {
            isMac = PropertiesTrait.isMac$(this);
            bitmap$0 = (byte)(bitmap$0 | 4);
         }
      } catch (Throwable var2) {
         throw var2;
      }

      return isMac;
   }

   public boolean isMac() {
      return (byte)(bitmap$0 & 4) == 0 ? this.isMac$lzycompute() : isMac;
   }

   private boolean isLinux$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(bitmap$0 & 8) == 0) {
            isLinux = PropertiesTrait.isLinux$(this);
            bitmap$0 = (byte)(bitmap$0 | 8);
         }
      } catch (Throwable var2) {
         throw var2;
      }

      return isLinux;
   }

   public boolean isLinux() {
      return (byte)(bitmap$0 & 8) == 0 ? this.isLinux$lzycompute() : isLinux;
   }

   private boolean isAvian$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(bitmap$0 & 16) == 0) {
            isAvian = PropertiesTrait.isAvian$(this);
            bitmap$0 = (byte)(bitmap$0 | 16);
         }
      } catch (Throwable var2) {
         throw var2;
      }

      return isAvian;
   }

   public boolean isAvian() {
      return (byte)(bitmap$0 & 16) == 0 ? this.isAvian$lzycompute() : isAvian;
   }

   private boolean consoleIsTerminal$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(bitmap$0 & 32) == 0) {
            consoleIsTerminal = PropertiesTrait.consoleIsTerminal$(this);
            bitmap$0 = (byte)(bitmap$0 | 32);
         }
      } catch (Throwable var2) {
         throw var2;
      }

      return consoleIsTerminal;
   }

   public boolean consoleIsTerminal() {
      return (byte)(bitmap$0 & 32) == 0 ? this.consoleIsTerminal$lzycompute() : consoleIsTerminal;
   }

   public void scala$util$PropertiesTrait$_setter_$propFilename_$eq(final String x$1) {
      propFilename = x$1;
   }

   public void scala$util$PropertiesTrait$_setter_$releaseVersion_$eq(final Option x$1) {
      releaseVersion = x$1;
   }

   public void scala$util$PropertiesTrait$_setter_$developmentVersion_$eq(final Option x$1) {
      developmentVersion = x$1;
   }

   public void scala$util$PropertiesTrait$_setter_$versionString_$eq(final String x$1) {
      versionString = x$1;
   }

   public void scala$util$PropertiesTrait$_setter_$copyrightString_$eq(final String x$1) {
      copyrightString = x$1;
   }

   public String propCategory() {
      return "library";
   }

   public Class pickJarBasedOn() {
      return Option.class;
   }

   public Attributes.Name ScalaCompilerVersion() {
      return ScalaCompilerVersion;
   }

   private Properties$() {
   }
}
