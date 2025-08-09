package org.apache.spark.deploy.master;

import scala.Enumeration;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u00051;a!\u0006\f\t\u0002a\u0001cA\u0002\u0012\u0017\u0011\u0003A2\u0005C\u0003+\u0003\u0011\u0005A&\u0002\u0003#\u0003\u0001i\u0003bB\u0019\u0002\u0005\u0004%\tA\r\u0005\u0007g\u0005\u0001\u000b\u0011B\u0017\t\u000fQ\n!\u0019!C\u0001e!1Q'\u0001Q\u0001\n5BqAN\u0001C\u0002\u0013\u0005!\u0007\u0003\u00048\u0003\u0001\u0006I!\f\u0005\bq\u0005\u0011\r\u0011\"\u00013\u0011\u0019I\u0014\u0001)A\u0005[!9!(\u0001b\u0001\n\u0003\u0011\u0004BB\u001e\u0002A\u0003%Q\u0006C\u0004=\u0003\t\u0007I\u0011\u0001\u001a\t\ru\n\u0001\u0015!\u0003.\u0011\u001dq\u0014A1A\u0005\u0002IBaaP\u0001!\u0002\u0013i\u0003b\u0002!\u0002\u0005\u0004%\tA\r\u0005\u0007\u0003\u0006\u0001\u000b\u0011B\u0017\t\u000f\t\u000b\u0011\u0011!C\u0005\u0007\u0006YAI]5wKJ\u001cF/\u0019;f\u0015\t9\u0002$\u0001\u0004nCN$XM\u001d\u0006\u00033i\ta\u0001Z3qY>L(BA\u000e\u001d\u0003\u0015\u0019\b/\u0019:l\u0015\tib$\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002?\u0005\u0019qN]4\u0011\u0005\u0005\nQ\"\u0001\f\u0003\u0017\u0011\u0013\u0018N^3s'R\fG/Z\n\u0003\u0003\u0011\u0002\"!\n\u0015\u000e\u0003\u0019R\u0011aJ\u0001\u0006g\u000e\fG.Y\u0005\u0003S\u0019\u00121\"\u00128v[\u0016\u0014\u0018\r^5p]\u00061A(\u001b8jiz\u001a\u0001\u0001F\u0001!!\tqs&D\u0001\u0002\u0013\t\u0001\u0004FA\u0003WC2,X-A\u0005T+\nk\u0015\n\u0016+F\tV\tQ&\u0001\u0006T+\nk\u0015\n\u0016+F\t\u0002\nqAU+O\u001d&su)\u0001\u0005S+:s\u0015JT$!\u0003!1\u0015JT%T\u0011\u0016#\u0015!\u0003$J\u001d&\u001b\u0006*\u0012#!\u0003-\u0011V\tT!V\u001d\u000eC\u0015JT$\u0002\u0019I+E*Q+O\u0007\"Kej\u0012\u0011\u0002\u000fUs5JT(X\u001d\u0006AQKT&O\u001f^s\u0005%\u0001\u0004L\u00132cU\tR\u0001\b\u0017&cE*\u0012#!\u0003\u00191\u0015)\u0013'F\t\u00069a)Q%M\u000b\u0012\u0003\u0013!B#S%>\u0013\u0016AB#S%>\u0013\u0006%\u0001\u0007xe&$XMU3qY\u0006\u001cW\rF\u0001E!\t)%*D\u0001G\u0015\t9\u0005*\u0001\u0003mC:<'\"A%\u0002\t)\fg/Y\u0005\u0003\u0017\u001a\u0013aa\u00142kK\u000e$\b"
)
public final class DriverState {
   public static Enumeration.Value ERROR() {
      return DriverState$.MODULE$.ERROR();
   }

   public static Enumeration.Value FAILED() {
      return DriverState$.MODULE$.FAILED();
   }

   public static Enumeration.Value KILLED() {
      return DriverState$.MODULE$.KILLED();
   }

   public static Enumeration.Value UNKNOWN() {
      return DriverState$.MODULE$.UNKNOWN();
   }

   public static Enumeration.Value RELAUNCHING() {
      return DriverState$.MODULE$.RELAUNCHING();
   }

   public static Enumeration.Value FINISHED() {
      return DriverState$.MODULE$.FINISHED();
   }

   public static Enumeration.Value RUNNING() {
      return DriverState$.MODULE$.RUNNING();
   }

   public static Enumeration.Value SUBMITTED() {
      return DriverState$.MODULE$.SUBMITTED();
   }

   public static Enumeration.ValueSet ValueSet() {
      return DriverState$.MODULE$.ValueSet();
   }

   public static Enumeration.ValueOrdering ValueOrdering() {
      return DriverState$.MODULE$.ValueOrdering();
   }

   public static Enumeration.Value withName(final String s) {
      return DriverState$.MODULE$.withName(s);
   }

   public static Enumeration.Value apply(final int x) {
      return DriverState$.MODULE$.apply(x);
   }

   public static int maxId() {
      return DriverState$.MODULE$.maxId();
   }

   public static Enumeration.ValueSet values() {
      return DriverState$.MODULE$.values();
   }

   public static String toString() {
      return DriverState$.MODULE$.toString();
   }
}
