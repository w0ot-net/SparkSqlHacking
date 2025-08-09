package org.apache.spark.deploy.master;

import scala.Enumeration;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0011;a!\u0005\n\t\u0002IabA\u0002\u0010\u0013\u0011\u0003\u0011r\u0004C\u0003'\u0003\u0011\u0005\u0001&\u0002\u0003\u001f\u0003\u0001I\u0003bB\u0017\u0002\u0005\u0004%\tA\f\u0005\u0007_\u0005\u0001\u000b\u0011B\u0015\t\u000fA\n!\u0019!C\u0001]!1\u0011'\u0001Q\u0001\n%BqAM\u0001C\u0002\u0013\u0005a\u0006\u0003\u00044\u0003\u0001\u0006I!\u000b\u0005\bi\u0005\u0011\r\u0011\"\u0001/\u0011\u0019)\u0014\u0001)A\u0005S!9a'\u0001b\u0001\n\u0003q\u0003BB\u001c\u0002A\u0003%\u0011\u0006C\u00049\u0003\t\u0007I\u0011\u0001\u0018\t\re\n\u0001\u0015!\u0003*\u0011\u001dQ\u0014!!A\u0005\nm\n\u0001#\u00119qY&\u001c\u0017\r^5p]N#\u0018\r^3\u000b\u0005M!\u0012AB7bgR,'O\u0003\u0002\u0016-\u00051A-\u001a9m_fT!a\u0006\r\u0002\u000bM\u0004\u0018M]6\u000b\u0005eQ\u0012AB1qC\u000eDWMC\u0001\u001c\u0003\ry'o\u001a\t\u0003;\u0005i\u0011A\u0005\u0002\u0011\u0003B\u0004H.[2bi&|gn\u0015;bi\u0016\u001c\"!\u0001\u0011\u0011\u0005\u0005\"S\"\u0001\u0012\u000b\u0003\r\nQa]2bY\u0006L!!\n\u0012\u0003\u0017\u0015sW/\\3sCRLwN\\\u0001\u0007y%t\u0017\u000e\u001e \u0004\u0001Q\tA\u0004\u0005\u0002+W5\t\u0011!\u0003\u0002-I\t)a+\u00197vK\u00069q+Q%U\u0013:;U#A\u0015\u0002\u0011]\u000b\u0015\nV%O\u000f\u0002\nqAU+O\u001d&su)\u0001\u0005S+:s\u0015JT$!\u0003!1\u0015JT%T\u0011\u0016#\u0015!\u0003$J\u001d&\u001b\u0006*\u0012#!\u0003\u00191\u0015)\u0013'F\t\u00069a)Q%M\u000b\u0012\u0003\u0013AB&J\u00192+E)A\u0004L\u00132cU\t\u0012\u0011\u0002\u000fUs5JT(X\u001d\u0006AQKT&O\u001f^s\u0005%\u0001\u0007xe&$XMU3qY\u0006\u001cW\rF\u0001=!\ti$)D\u0001?\u0015\ty\u0004)\u0001\u0003mC:<'\"A!\u0002\t)\fg/Y\u0005\u0003\u0007z\u0012aa\u00142kK\u000e$\b"
)
public final class ApplicationState {
   public static Enumeration.Value UNKNOWN() {
      return ApplicationState$.MODULE$.UNKNOWN();
   }

   public static Enumeration.Value KILLED() {
      return ApplicationState$.MODULE$.KILLED();
   }

   public static Enumeration.Value FAILED() {
      return ApplicationState$.MODULE$.FAILED();
   }

   public static Enumeration.Value FINISHED() {
      return ApplicationState$.MODULE$.FINISHED();
   }

   public static Enumeration.Value RUNNING() {
      return ApplicationState$.MODULE$.RUNNING();
   }

   public static Enumeration.Value WAITING() {
      return ApplicationState$.MODULE$.WAITING();
   }

   public static Enumeration.ValueSet ValueSet() {
      return ApplicationState$.MODULE$.ValueSet();
   }

   public static Enumeration.ValueOrdering ValueOrdering() {
      return ApplicationState$.MODULE$.ValueOrdering();
   }

   public static Enumeration.Value withName(final String s) {
      return ApplicationState$.MODULE$.withName(s);
   }

   public static Enumeration.Value apply(final int x) {
      return ApplicationState$.MODULE$.apply(x);
   }

   public static int maxId() {
      return ApplicationState$.MODULE$.maxId();
   }

   public static Enumeration.ValueSet values() {
      return ApplicationState$.MODULE$.values();
   }

   public static String toString() {
      return ApplicationState$.MODULE$.toString();
   }
}
