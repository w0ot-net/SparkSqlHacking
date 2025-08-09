package org.apache.spark.sql;

import java.io.PrintStream;
import java.lang.invoke.SerializedLambda;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.commons.lang3.StringUtils;
import org.apache.ivy.core.settings.IvySettings;
import org.apache.spark.sql.util.ArtifactUtils$;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple3;
import scala.collection.IterableOps;
import scala.collection.immutable.;
import scala.collection.immutable.Seq;
import scala.runtime.BoxesRunTime;

public final class Artifact$ {
   public static final Artifact$ MODULE$ = new Artifact$();
   private static final Path CLASS_PREFIX = Paths.get("classes");
   private static final Path JAR_PREFIX = Paths.get("jars");
   private static final Path CACHE_PREFIX = Paths.get("cache");

   public Path CLASS_PREFIX() {
      return CLASS_PREFIX;
   }

   public Path JAR_PREFIX() {
      return JAR_PREFIX;
   }

   public Path CACHE_PREFIX() {
      return CACHE_PREFIX;
   }

   public Artifact newArtifactFromExtension(final String fileName, final Path targetFilePath, final Artifact.LocalData storage) {
      switch (fileName == null ? 0 : fileName.hashCode()) {
         default:
            if (fileName.endsWith(".jar")) {
               return this.newJarArtifact(targetFilePath, storage);
            } else if (fileName.endsWith(".class")) {
               return this.newClassArtifact(targetFilePath, storage);
            } else {
               throw new UnsupportedOperationException("Unsupported file format: " + fileName);
            }
      }
   }

   public Seq parseArtifacts(final URI uri) {
      String var3 = uri.getScheme();
      switch (var3 == null ? 0 : var3.hashCode()) {
         case 104684:
            if ("ivy".equals(var3)) {
               return this.newIvyArtifacts(uri);
            }
            break;
         case 3143036:
            if ("file".equals(var3)) {
               Path path = Paths.get(uri);
               Artifact artifact = this.newArtifactFromExtension(path.getFileName().toString(), path.getFileName(), new Artifact.LocalFile(path));
               return new .colon.colon(artifact, scala.collection.immutable.Nil..MODULE$);
            }
      }

      throw new UnsupportedOperationException("Unsupported scheme: " + var3);
   }

   public Artifact newJarArtifact(final Path targetFilePath, final Artifact.LocalData storage) {
      return this.newArtifact(this.JAR_PREFIX(), ".jar", targetFilePath, storage);
   }

   public Artifact newClassArtifact(final Path targetFilePath, final Artifact.LocalData storage) {
      return this.newArtifact(this.CLASS_PREFIX(), ".class", targetFilePath, storage);
   }

   public Artifact newCacheArtifact(final String id, final Artifact.LocalData storage) {
      return this.newArtifact(this.CACHE_PREFIX(), "", Paths.get(id), storage);
   }

   public Seq newIvyArtifacts(final URI uri) {
      PrintStream printStream = System.err;
      String authority = uri.getAuthority();
      if (authority == null) {
         throw new IllegalArgumentException("Invalid Ivy URI authority in uri " + uri.toString() + ": Expected 'org:module:version', found null.");
      } else if (authority.split(":").length != 3) {
         String var10002 = uri.toString();
         throw new IllegalArgumentException("Invalid Ivy URI authority in uri " + var10002 + ": Expected 'org:module:version', found " + authority + ".");
      } else {
         Tuple3 var6 = org.apache.spark.util.MavenUtils..MODULE$.parseQueryParams(uri);
         if (var6 != null) {
            boolean transitive = BoxesRunTime.unboxToBoolean(var6._1());
            String exclusions = (String)var6._2();
            String repos = (String)var6._3();
            Tuple3 var5 = new Tuple3(BoxesRunTime.boxToBoolean(transitive), exclusions, repos);
            boolean transitive = BoxesRunTime.unboxToBoolean(var5._1());
            String exclusions = (String)var5._2();
            String repos = (String)var5._3();
            Seq exclusionsList = (Seq)(!StringUtils.isBlank(exclusions) ? org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(exclusions.split(",")).toImmutableArraySeq() : scala.collection.immutable.Nil..MODULE$);
            IvySettings ivySettings = org.apache.spark.util.MavenUtils..MODULE$.buildIvySettings(new Some(repos), scala.None..MODULE$, org.apache.spark.util.MavenUtils..MODULE$.buildIvySettings$default$3(), printStream);
            Option x$5 = org.apache.spark.util.MavenUtils..MODULE$.resolveMavenCoordinates$default$3();
            boolean x$6 = org.apache.spark.util.MavenUtils..MODULE$.resolveMavenCoordinates$default$6();
            Seq jars = org.apache.spark.util.MavenUtils..MODULE$.resolveMavenCoordinates(authority, ivySettings, x$5, transitive, exclusionsList, x$6, printStream);
            return (Seq)((IterableOps)jars.map((p) -> Paths.get(p))).map((path) -> MODULE$.newJarArtifact(path.getFileName(), new Artifact.LocalFile(path)));
         } else {
            throw new MatchError(var6);
         }
      }
   }

   private Artifact newArtifact(final Path prefix, final String requiredSuffix, final Path targetFilePath, final Artifact.LocalData storage) {
      scala.Predef..MODULE$.require(targetFilePath.toString().endsWith(requiredSuffix));
      return new Artifact(ArtifactUtils$.MODULE$.concatenatePaths(prefix, targetFilePath), storage);
   }

   private Artifact$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
