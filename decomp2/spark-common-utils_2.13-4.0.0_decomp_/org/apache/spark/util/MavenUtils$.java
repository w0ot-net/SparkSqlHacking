package org.apache.spark.util;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.invoke.SerializedLambda;
import java.net.URI;
import java.text.ParseException;
import java.util.Map;
import java.util.UUID;
import org.apache.commons.lang3.StringUtils;
import org.apache.ivy.Ivy;
import org.apache.ivy.core.module.descriptor.Artifact;
import org.apache.ivy.core.module.descriptor.DefaultDependencyDescriptor;
import org.apache.ivy.core.module.descriptor.DefaultExcludeRule;
import org.apache.ivy.core.module.descriptor.DefaultModuleDescriptor;
import org.apache.ivy.core.module.descriptor.ExcludeRule;
import org.apache.ivy.core.module.id.ArtifactId;
import org.apache.ivy.core.module.id.ModuleId;
import org.apache.ivy.core.module.id.ModuleRevisionId;
import org.apache.ivy.core.report.ArtifactDownloadReport;
import org.apache.ivy.core.report.DownloadStatus;
import org.apache.ivy.core.report.ResolveReport;
import org.apache.ivy.core.resolve.ResolveOptions;
import org.apache.ivy.core.retrieve.RetrieveOptions;
import org.apache.ivy.core.settings.IvySettings;
import org.apache.ivy.plugins.matcher.GlobPatternMatcher;
import org.apache.ivy.plugins.repository.file.FileRepository;
import org.apache.ivy.plugins.resolver.ChainResolver;
import org.apache.ivy.plugins.resolver.DependencyResolver;
import org.apache.ivy.plugins.resolver.FileSystemResolver;
import org.apache.ivy.plugins.resolver.IBiblioResolver;
import org.apache.spark.SparkException;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.LogEntry$;
import org.apache.spark.internal.LogKeys;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.StringContext;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.package.;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ObjectRef;
import scala.runtime.java8.JFunction0;

public final class MavenUtils$ implements Logging {
   public static final MavenUtils$ MODULE$ = new MavenUtils$();
   private static final String JAR_IVY_SETTING_PATH_KEY;
   private static final Seq IVY_DEFAULT_EXCLUDES;
   private static transient Logger org$apache$spark$internal$Logging$$log_;

   static {
      Logging.$init$(MODULE$);
      JAR_IVY_SETTING_PATH_KEY = "spark.jars.ivySettings";
      IVY_DEFAULT_EXCLUDES = (Seq).MODULE$.Seq().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"catalyst_", "core_", "graphx_", "kvstore_", "launcher_", "mllib_", "mllib-local_", "network-common_", "network-shuffle_", "repl_", "sketch_", "sql_", "streaming_", "tags_", "unsafe_"})));
   }

   public String logName() {
      return Logging.logName$(this);
   }

   public Logger log() {
      return Logging.log$(this);
   }

   public Logging.LogStringContext LogStringContext(final StringContext sc) {
      return Logging.LogStringContext$(this, sc);
   }

   public void withLogContext(final Map context, final Function0 body) {
      Logging.withLogContext$(this, context, body);
   }

   public void logInfo(final Function0 msg) {
      Logging.logInfo$(this, (Function0)msg);
   }

   public void logInfo(final LogEntry entry) {
      Logging.logInfo$(this, (LogEntry)entry);
   }

   public void logInfo(final LogEntry entry, final Throwable throwable) {
      Logging.logInfo$(this, (LogEntry)entry, throwable);
   }

   public void logDebug(final Function0 msg) {
      Logging.logDebug$(this, (Function0)msg);
   }

   public void logDebug(final LogEntry entry) {
      Logging.logDebug$(this, (LogEntry)entry);
   }

   public void logDebug(final LogEntry entry, final Throwable throwable) {
      Logging.logDebug$(this, (LogEntry)entry, throwable);
   }

   public void logTrace(final Function0 msg) {
      Logging.logTrace$(this, (Function0)msg);
   }

   public void logTrace(final LogEntry entry) {
      Logging.logTrace$(this, (LogEntry)entry);
   }

   public void logTrace(final LogEntry entry, final Throwable throwable) {
      Logging.logTrace$(this, (LogEntry)entry, throwable);
   }

   public void logWarning(final Function0 msg) {
      Logging.logWarning$(this, (Function0)msg);
   }

   public void logWarning(final LogEntry entry) {
      Logging.logWarning$(this, (LogEntry)entry);
   }

   public void logWarning(final LogEntry entry, final Throwable throwable) {
      Logging.logWarning$(this, (LogEntry)entry, throwable);
   }

   public void logError(final Function0 msg) {
      Logging.logError$(this, (Function0)msg);
   }

   public void logError(final LogEntry entry) {
      Logging.logError$(this, (LogEntry)entry);
   }

   public void logError(final LogEntry entry, final Throwable throwable) {
      Logging.logError$(this, (LogEntry)entry, throwable);
   }

   public void logInfo(final Function0 msg, final Throwable throwable) {
      Logging.logInfo$(this, (Function0)msg, throwable);
   }

   public void logDebug(final Function0 msg, final Throwable throwable) {
      Logging.logDebug$(this, (Function0)msg, throwable);
   }

   public void logTrace(final Function0 msg, final Throwable throwable) {
      Logging.logTrace$(this, (Function0)msg, throwable);
   }

   public void logWarning(final Function0 msg, final Throwable throwable) {
      Logging.logWarning$(this, (Function0)msg, throwable);
   }

   public void logError(final Function0 msg, final Throwable throwable) {
      Logging.logError$(this, (Function0)msg, throwable);
   }

   public boolean isTraceEnabled() {
      return Logging.isTraceEnabled$(this);
   }

   public void initializeLogIfNecessary(final boolean isInterpreter) {
      Logging.initializeLogIfNecessary$(this, isInterpreter);
   }

   public boolean initializeLogIfNecessary(final boolean isInterpreter, final boolean silent) {
      return Logging.initializeLogIfNecessary$(this, isInterpreter, silent);
   }

   public boolean initializeLogIfNecessary$default$2() {
      return Logging.initializeLogIfNecessary$default$2$(this);
   }

   public void initializeForcefully(final boolean isInterpreter, final boolean silent) {
      Logging.initializeForcefully$(this, isInterpreter, silent);
   }

   public Logger org$apache$spark$internal$Logging$$log_() {
      return org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      org$apache$spark$internal$Logging$$log_ = x$1;
   }

   public String JAR_IVY_SETTING_PATH_KEY() {
      return JAR_IVY_SETTING_PATH_KEY;
   }

   public Seq IVY_DEFAULT_EXCLUDES() {
      return IVY_DEFAULT_EXCLUDES;
   }

   public Seq extractMavenCoordinates(final String coordinates) {
      return ArrayImplicits$.MODULE$.SparkArrayOps(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])coordinates.split(",")), (p) -> {
         String[] splits = p.replace("/", ":").split(":");
         scala.Predef..MODULE$.require(splits.length == 3, () -> "Provided Maven Coordinates must be in the form 'groupId:artifactId:version'. The coordinate provided is: " + p);
         scala.Predef..MODULE$.require(splits[0] != null && scala.collection.StringOps..MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString(splits[0].trim())), () -> "The groupId cannot be null or be whitespace. The groupId provided is: " + splits[0]);
         scala.Predef..MODULE$.require(splits[1] != null && scala.collection.StringOps..MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString(splits[1].trim())), () -> "The artifactId cannot be null or be whitespace. The artifactId provided is: " + splits[1]);
         scala.Predef..MODULE$.require(splits[2] != null && scala.collection.StringOps..MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString(splits[2].trim())), () -> "The version cannot be null or be whitespace. The version provided is: " + splits[2]);
         return new MavenUtils.MavenCoordinate(splits[0], splits[1], splits[2]);
      }, scala.reflect.ClassTag..MODULE$.apply(MavenUtils.MavenCoordinate.class))).toImmutableArraySeq();
   }

   public File m2Path() {
      return SparkEnvUtils$.MODULE$.isTesting() ? new File("dummy", ".m2" + File.separator + "repository") : new File(System.getProperty("user.home"), ".m2" + File.separator + "repository");
   }

   public ChainResolver createRepoResolvers(final File defaultIvyUserDir, final boolean useLocalM2AsCache) {
      ChainResolver cr = new ChainResolver();
      cr.setName("spark-list");
      if (useLocalM2AsCache) {
         IBiblioResolver localM2 = new IBiblioResolver();
         localM2.setM2compatible(true);
         localM2.setRoot(this.m2Path().toURI().toString());
         localM2.setUsepoms(true);
         localM2.setName("local-m2-cache");
         cr.add(localM2);
      }

      FileSystemResolver localIvy = new FileSystemResolver();
      File localIvyRoot = new File(defaultIvyUserDir, "local");
      localIvy.setLocal(true);
      localIvy.setRepository(new FileRepository(localIvyRoot));
      String ivyPattern = (new scala.collection.immutable..colon.colon(localIvyRoot.getAbsolutePath(), new scala.collection.immutable..colon.colon("[organisation]", new scala.collection.immutable..colon.colon("[module]", new scala.collection.immutable..colon.colon("[revision]", new scala.collection.immutable..colon.colon("ivys", new scala.collection.immutable..colon.colon("ivy.xml", scala.collection.immutable.Nil..MODULE$))))))).mkString(File.separator);
      localIvy.addIvyPattern(ivyPattern);
      String artifactPattern = (new scala.collection.immutable..colon.colon(localIvyRoot.getAbsolutePath(), new scala.collection.immutable..colon.colon("[organisation]", new scala.collection.immutable..colon.colon("[module]", new scala.collection.immutable..colon.colon("[revision]", new scala.collection.immutable..colon.colon("[type]s", new scala.collection.immutable..colon.colon("[artifact](-[classifier]).[ext]", scala.collection.immutable.Nil..MODULE$))))))).mkString(File.separator);
      localIvy.addArtifactPattern(artifactPattern);
      localIvy.setName("local-ivy-cache");
      cr.add(localIvy);
      IBiblioResolver br = new IBiblioResolver();
      br.setM2compatible(true);
      br.setUsepoms(true);
      Option defaultInternalRepo = scala.sys.package..MODULE$.env().get("DEFAULT_ARTIFACT_REPOSITORY");
      br.setRoot((String)defaultInternalRepo.getOrElse(() -> "https://repo1.maven.org/maven2/"));
      br.setName("central");
      cr.add(br);
      IBiblioResolver sp = new IBiblioResolver();
      sp.setM2compatible(true);
      sp.setUsepoms(true);
      sp.setRoot((String)scala.sys.package..MODULE$.env().getOrElse("DEFAULT_ARTIFACT_REPOSITORY", () -> "https://repos.spark-packages.org/"));
      sp.setName("spark-packages");
      cr.add(sp);
      return cr;
   }

   public boolean createRepoResolvers$default$2() {
      return true;
   }

   public Seq resolveDependencyPaths(final Object[] artifacts, final File cacheDirectory) {
      return ArrayImplicits$.MODULE$.SparkArrayOps(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.filter$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(artifacts), (x$1) -> (Artifact)x$1, scala.reflect.ClassTag..MODULE$.apply(Artifact.class))), (artifactInfo) -> BoxesRunTime.boxToBoolean($anonfun$resolveDependencyPaths$2(artifactInfo)))), (artifactInfo) -> {
         ModuleRevisionId artifact = artifactInfo.getModuleRevisionId();
         Map extraAttrs = artifactInfo.getExtraAttributes();
         String classifier = extraAttrs.containsKey("classifier") ? "-" + extraAttrs.get("classifier") : "";
         String var10000 = cacheDirectory.getAbsolutePath();
         return var10000 + File.separator + artifact.getOrganisation() + "_" + artifact.getName() + "-" + artifact.getRevision() + classifier + ".jar";
      }, scala.reflect.ClassTag..MODULE$.apply(String.class))).toImmutableArraySeq();
   }

   public void addDependenciesToIvy(final DefaultModuleDescriptor md, final Seq artifacts, final String ivyConfName, final PrintStream printStream) {
      artifacts.foreach((mvn) -> {
         $anonfun$addDependenciesToIvy$1(ivyConfName, printStream, md, mvn);
         return BoxedUnit.UNIT;
      });
   }

   private void addExclusionRules(final IvySettings ivySettings, final String ivyConfName, final DefaultModuleDescriptor md) {
      md.addExcludeRule(this.createExclusion("*:scala-library:*", ivySettings, ivyConfName));
      this.IVY_DEFAULT_EXCLUDES().foreach((comp) -> {
         $anonfun$addExclusionRules$1(md, ivySettings, ivyConfName, comp);
         return BoxedUnit.UNIT;
      });
   }

   public IvySettings buildIvySettings(final Option remoteRepos, final Option ivyPath, final boolean useLocalM2AsCache, final PrintStream printStream) {
      IvySettings ivySettings = new IvySettings();
      this.processIvyPathArg(ivySettings, ivyPath);
      ivySettings.addMatcher(new GlobPatternMatcher());
      ChainResolver repoResolver = this.createRepoResolvers(ivySettings.getDefaultIvyUserDir(), useLocalM2AsCache);
      ivySettings.addResolver(repoResolver);
      ivySettings.setDefaultResolver(repoResolver.getName());
      this.processRemoteRepoArg(ivySettings, remoteRepos, printStream);
      ivySettings.setVariable("ivy.maven.lookup.sources", "false");
      ivySettings.setVariable("ivy.maven.lookup.javadoc", "false");
      return ivySettings;
   }

   public boolean buildIvySettings$default$3() {
      return true;
   }

   public IvySettings loadIvySettings(final String settingsFile, final Option remoteRepos, final Option ivyPath, final PrintStream printStream) {
      URI uri = new URI(settingsFile);
      String var10 = (String)scala.Option..MODULE$.apply(uri.getScheme()).getOrElse(() -> "file");
      switch (var10 == null ? 0 : var10.hashCode()) {
         case 3143036:
            if ("file".equals(var10)) {
               File file = new File(uri.getPath());
               scala.Predef..MODULE$.require(file.exists(), () -> "Ivy settings file " + file + " does not exist");
               scala.Predef..MODULE$.require(file.isFile(), () -> "Ivy settings file " + file + " is not a normal file");
               IvySettings ivySettings = new IvySettings();

               try {
                  ivySettings.load(file);
                  if (ivySettings.getDefaultIvyUserDir() == null && ivySettings.getDefaultCache() == null) {
                     String var10001 = System.getProperty("user.home");
                     String alternateIvyDir = System.getProperty("ivy.home", var10001 + File.separator + ".ivy2.5.2");
                     ivySettings.setDefaultIvyUserDir(new File(alternateIvyDir));
                     ivySettings.setDefaultCache(new File(alternateIvyDir, "cache"));
                  }
               } catch (Throwable var15) {
                  if (var15 instanceof IOException ? true : var15 instanceof ParseException) {
                     throw new SparkException("Failed when loading Ivy settings from " + settingsFile, var15);
                  }

                  throw var15;
               }

               this.processIvyPathArg(ivySettings, ivyPath);
               this.processRemoteRepoArg(ivySettings, remoteRepos, printStream);
               return ivySettings;
            }
         default:
            throw new IllegalArgumentException("Scheme " + var10 + " not supported in " + this.JAR_IVY_SETTING_PATH_KEY());
      }
   }

   public void processIvyPathArg(final IvySettings ivySettings, final Option ivyPath) {
      String alternateIvyDir = (String)ivyPath.filterNot((x$2) -> BoxesRunTime.boxToBoolean($anonfun$processIvyPathArg$1(x$2))).getOrElse(() -> {
         String var10001 = System.getProperty("user.home");
         return System.getProperty("ivy.home", var10001 + File.separator + ".ivy2.5.2");
      });
      ivySettings.setDefaultIvyUserDir(new File(alternateIvyDir));
      ivySettings.setDefaultCache(new File(alternateIvyDir, "cache"));
   }

   private void processRemoteRepoArg(final IvySettings ivySettings, final Option remoteRepos, final PrintStream printStream) {
      remoteRepos.filterNot((x$3) -> BoxesRunTime.boxToBoolean($anonfun$processRemoteRepoArg$1(x$3))).map((x$4) -> x$4.split(",")).foreach((repositoryList) -> {
         $anonfun$processRemoteRepoArg$3(ivySettings, printStream, repositoryList);
         return BoxedUnit.UNIT;
      });
   }

   public DefaultModuleDescriptor getModuleDescriptor() {
      return DefaultModuleDescriptor.newDefaultInstance(ModuleRevisionId.newInstance("org.apache.spark", "spark-submit-parent-" + UUID.randomUUID().toString(), "1.0"));
   }

   private void clearIvyResolutionFiles(final ModuleRevisionId mdId, final File defaultCacheFile, final String ivyConfName) {
      String var10002 = mdId.getOrganisation() + "-" + mdId.getName() + "-" + ivyConfName + ".xml";
      String var10005 = "resolved-" + mdId.getOrganisation() + "-" + mdId.getName() + "-" + mdId.getRevision() + ".xml";
      String var10008 = mdId.getOrganisation();
      Seq currentResolutionFiles = new scala.collection.immutable..colon.colon(var10002, new scala.collection.immutable..colon.colon(var10005, new scala.collection.immutable..colon.colon("resolved-" + var10008 + "-" + mdId.getName() + "-" + mdId.getRevision() + ".properties", scala.collection.immutable.Nil..MODULE$)));
      currentResolutionFiles.foreach((filename) -> BoxesRunTime.boxToBoolean($anonfun$clearIvyResolutionFiles$1(defaultCacheFile, filename)));
   }

   private void clearInvalidIvyCacheFiles(final ModuleRevisionId mdId, final File defaultCacheFile) {
      String var10002 = mdId.getOrganisation() + File.separator + mdId.getName() + File.separator + "ivy-" + mdId.getRevision() + ".xml";
      String var10005 = mdId.getOrganisation() + File.separator + mdId.getName() + File.separator + "ivy-" + mdId.getRevision() + ".xml.original";
      String var10008 = mdId.getOrganisation();
      Seq cacheFiles = new scala.collection.immutable..colon.colon(var10002, new scala.collection.immutable..colon.colon(var10005, new scala.collection.immutable..colon.colon(var10008 + File.separator + mdId.getName() + File.separator + "ivydata-" + mdId.getRevision() + ".properties", scala.collection.immutable.Nil..MODULE$)));
      cacheFiles.foreach((filename) -> BoxesRunTime.boxToBoolean($anonfun$clearInvalidIvyCacheFiles$1(defaultCacheFile, filename)));
   }

   public Seq resolveMavenCoordinates(final String coordinates, final IvySettings ivySettings, final Option noCacheIvySettings, final boolean transitive, final Seq exclusions, final boolean isTest, final PrintStream printStream) {
      if (coordinates != null && !coordinates.trim().isEmpty()) {
         PrintStream sysOut = System.out;
         String ivyConfName = "default";
         ObjectRef md = ObjectRef.create((Object)null);

         Seq var10000;
         try {
            System.setOut(printStream);
            md.elem = this.getModuleDescriptor();
            ((DefaultModuleDescriptor)md.elem).setDefaultConf(ivyConfName);
            Seq artifacts = this.extractMavenCoordinates(coordinates);
            File packagesDirectory = new File(ivySettings.getDefaultIvyUserDir(), "jars");
            printStream.println("Ivy Default Cache set to: " + ivySettings.getDefaultCache().getAbsolutePath());
            printStream.println("The jars for the packages stored in: " + packagesDirectory);
            Ivy ivy = Ivy.newInstance(ivySettings);
            ivy.pushContext();
            ResolveOptions resolveOptions = new ResolveOptions();
            resolveOptions.setTransitive(transitive);
            RetrieveOptions retrieveOptions = new RetrieveOptions();
            if (isTest) {
               resolveOptions.setDownload(false);
               resolveOptions.setLog("quiet");
               retrieveOptions.setLog("quiet");
            } else {
               resolveOptions.setDownload(true);
            }

            String var10001 = packagesDirectory.getAbsolutePath();
            retrieveOptions.setDestArtifactPattern(var10001 + File.separator + "[organization]_[artifact]-[revision](-[classifier]).[ext]");
            retrieveOptions.setConfs((String[])((Object[])(new String[]{ivyConfName})));
            this.addExclusionRules(ivySettings, ivyConfName, (DefaultModuleDescriptor)md.elem);
            this.addDependenciesToIvy((DefaultModuleDescriptor)md.elem, artifacts, ivyConfName, printStream);
            exclusions.foreach((e) -> {
               $anonfun$resolveMavenCoordinates$1(md, ivySettings, ivyConfName, e);
               return BoxedUnit.UNIT;
            });
            ResolveReport rr = ivy.resolve((DefaultModuleDescriptor)md.elem, resolveOptions);
            if (rr.hasError()) {
               ArtifactDownloadReport[] failedReports = rr.getArtifactsReports(DownloadStatus.FAILED, true);
               if (!scala.collection.ArrayOps..MODULE$.nonEmpty$extension(scala.Predef..MODULE$.refArrayOps((Object[])failedReports)) || !noCacheIvySettings.isDefined()) {
                  throw new RuntimeException(rr.getAllProblemMessages().toString());
               }

               Artifact[] failedArtifacts = (Artifact[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])failedReports), (r) -> r.getArtifact(), scala.reflect.ClassTag..MODULE$.apply(Artifact.class));
               this.logInfo(LogEntry$.MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Download failed: "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ", "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new MDC[]{new MDC(LogKeys.ARTIFACTS$.MODULE$, scala.Predef..MODULE$.wrapRefArray((Object[])failedArtifacts).mkString("[", ", ", "]"))}))).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"attempt to retry while skipping local-m2-cache."})))).log(scala.collection.immutable.Nil..MODULE$))));
               scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps((Object[])failedArtifacts), (artifact) -> {
                  $anonfun$resolveMavenCoordinates$4(ivySettings, artifact);
                  return BoxedUnit.UNIT;
               });
               ivy.popContext();
               Ivy noCacheIvy = Ivy.newInstance((IvySettings)noCacheIvySettings.get());
               noCacheIvy.pushContext();
               ResolveReport noCacheRr = noCacheIvy.resolve((DefaultModuleDescriptor)md.elem, resolveOptions);
               if (noCacheRr.hasError()) {
                  throw new RuntimeException(noCacheRr.getAllProblemMessages().toString());
               }

               noCacheIvy.retrieve(noCacheRr.getModuleDescriptor().getModuleRevisionId(), retrieveOptions);
               Seq dependencyPaths = this.resolveDependencyPaths(noCacheRr.getArtifacts().toArray(), packagesDirectory);
               noCacheIvy.popContext();
               var10000 = dependencyPaths;
            } else {
               ivy.retrieve(rr.getModuleDescriptor().getModuleRevisionId(), retrieveOptions);
               Seq dependencyPaths = this.resolveDependencyPaths(rr.getArtifacts().toArray(), packagesDirectory);
               ivy.popContext();
               var10000 = dependencyPaths;
            }
         } finally {
            System.setOut(sysOut);
            if ((DefaultModuleDescriptor)md.elem != null) {
               this.clearIvyResolutionFiles(((DefaultModuleDescriptor)md.elem).getModuleRevisionId(), ivySettings.getDefaultCache(), ivyConfName);
            }

         }

         return var10000;
      } else {
         return scala.collection.immutable.Nil..MODULE$;
      }
   }

   public Option resolveMavenCoordinates$default$3() {
      return scala.None..MODULE$;
   }

   public Seq resolveMavenCoordinates$default$5() {
      return scala.collection.immutable.Nil..MODULE$;
   }

   public boolean resolveMavenCoordinates$default$6() {
      return false;
   }

   public ExcludeRule createExclusion(final String coords, final IvySettings ivySettings, final String ivyConfName) {
      MavenUtils.MavenCoordinate c = (MavenUtils.MavenCoordinate)this.extractMavenCoordinates(coords).head();
      ArtifactId id = new ArtifactId(new ModuleId(c.groupId(), c.artifactId()), "*", "*", "*");
      DefaultExcludeRule rule = new DefaultExcludeRule(id, ivySettings.getMatcher("glob"), (Map)null);
      rule.addConfiguration(ivyConfName);
      return rule;
   }

   private boolean isInvalidQueryString(final String[] tokens) {
      return tokens.length != 2 || StringUtils.isBlank(tokens[0]) || StringUtils.isBlank(tokens[1]);
   }

   public Tuple3 parseQueryParams(final URI uri) {
      String uriQuery = uri.getQuery();
      if (uriQuery == null) {
         return new Tuple3(BoxesRunTime.boxToBoolean(true), "", "");
      } else {
         String[][] mapTokens = (String[][])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])uriQuery.split("&")), (x$5) -> x$5.split("="), scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(String.class)));
         if (scala.collection.ArrayOps..MODULE$.exists$extension(scala.Predef..MODULE$.refArrayOps((Object[])mapTokens), (tokens) -> BoxesRunTime.boxToBoolean($anonfun$parseQueryParams$2(tokens)))) {
            String var10002 = uri.toString();
            throw new IllegalArgumentException("Invalid query string in Ivy URI " + var10002 + ": " + uriQuery);
         } else {
            scala.collection.immutable.Map groupedParams = scala.collection.ArrayOps..MODULE$.groupBy$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])mapTokens), (kv) -> new Tuple2(kv[0], kv[1]), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class))), (x$6) -> (String)x$6._1());
            Option transitiveParams = groupedParams.get("transitive");
            if (BoxesRunTime.unboxToInt(transitiveParams.map((x$7) -> BoxesRunTime.boxToInteger($anonfun$parseQueryParams$5(x$7))).getOrElse((JFunction0.mcI.sp)() -> 0)) > 1) {
               this.logWarning((Function0)(() -> "It's best to specify `transitive` parameter in ivy URI query only once. If there are multiple `transitive` parameter, we will select the last one"));
            }

            boolean transitive = BoxesRunTime.unboxToBoolean(transitiveParams.flatMap((x$8) -> scala.collection.ArrayOps..MODULE$.headOption$extension(scala.Predef..MODULE$.booleanArrayOps((boolean[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.takeRight$extension(scala.Predef..MODULE$.refArrayOps((Object[])x$8), 1)), (x$9) -> BoxesRunTime.boxToBoolean($anonfun$parseQueryParams$9(x$9)), scala.reflect.ClassTag..MODULE$.Boolean())))).getOrElse((JFunction0.mcZ.sp)() -> true));
            String exclusionList = (String)groupedParams.get("exclude").map((params) -> scala.Predef..MODULE$.wrapRefArray(scala.collection.ArrayOps..MODULE$.flatMap$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])params), (x$10) -> (String)x$10._2(), scala.reflect.ClassTag..MODULE$.apply(String.class))), (excludeString) -> {
                  String[] excludes = excludeString.split(",");
                  if (scala.collection.ArrayOps..MODULE$.exists$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])excludes), (x$11) -> x$11.split(":"), scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(String.class)))), (tokens) -> BoxesRunTime.boxToBoolean($anonfun$parseQueryParams$15(tokens)))) {
                     String var10002 = uri.toString();
                     throw new IllegalArgumentException("Invalid exclude string in Ivy URI " + var10002 + ": expected 'org:module,org:module,..', found " + excludeString);
                  } else {
                     return excludes;
                  }
               }, (xs) -> scala.Predef..MODULE$.wrapRefArray((Object[])xs), scala.reflect.ClassTag..MODULE$.apply(String.class))).mkString(",")).getOrElse(() -> "");
            String repos = (String)groupedParams.get("repos").map((params) -> scala.Predef..MODULE$.wrapRefArray(scala.collection.ArrayOps..MODULE$.flatMap$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])params), (x$12) -> (String)x$12._2(), scala.reflect.ClassTag..MODULE$.apply(String.class))), (x$13) -> x$13.split(","), (xs) -> scala.Predef..MODULE$.wrapRefArray((Object[])xs), scala.reflect.ClassTag..MODULE$.apply(String.class))).mkString(",")).getOrElse(() -> "");
            Set validParams = (Set)scala.Predef..MODULE$.Set().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"transitive", "exclude", "repos"})));
            Seq invalidParams = ((IterableOnceOps)groupedParams.keys().filterNot((elem) -> BoxesRunTime.boxToBoolean($anonfun$parseQueryParams$23(validParams, elem)))).toSeq();
            if (invalidParams.nonEmpty()) {
               this.logWarning(LogEntry$.MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Invalid parameters `", "` "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new MDC[]{new MDC(LogKeys.INVALID_PARAMS$.MODULE$, ((IterableOnceOps)invalidParams.sorted(scala.math.Ordering.String..MODULE$)).mkString(","))})).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"found in Ivy URI query `", "`."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new MDC[]{new MDC(LogKeys.URI$.MODULE$, uriQuery)})))));
            }

            return new Tuple3(BoxesRunTime.boxToBoolean(transitive), exclusionList, repos);
         }
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$resolveDependencyPaths$2(final Artifact artifactInfo) {
      String var10000 = artifactInfo.getExt();
      String var1 = "jar";
      if (var10000 == null) {
         if (var1 == null) {
            return true;
         }
      } else if (var10000.equals(var1)) {
         return true;
      }

      MODULE$.logInfo(LogEntry$.MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Skipping non-jar dependency ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new MDC[]{new MDC(LogKeys.ARTIFACT_ID$.MODULE$, artifactInfo.getId())}))));
      return false;
   }

   // $FF: synthetic method
   public static final void $anonfun$addDependenciesToIvy$1(final String ivyConfName$1, final PrintStream printStream$1, final DefaultModuleDescriptor md$1, final MavenUtils.MavenCoordinate mvn) {
      ModuleRevisionId ri = ModuleRevisionId.newInstance(mvn.groupId(), mvn.artifactId(), mvn.version());
      DefaultDependencyDescriptor dd = new DefaultDependencyDescriptor(ri, false, false);
      dd.addDependencyConfiguration(ivyConfName$1, ivyConfName$1 + "(runtime)");
      printStream$1.println(dd.getDependencyId() + " added as a dependency");
      md$1.addDependency(dd);
   }

   // $FF: synthetic method
   public static final void $anonfun$addExclusionRules$1(final DefaultModuleDescriptor md$2, final IvySettings ivySettings$1, final String ivyConfName$2, final String comp) {
      md$2.addExcludeRule(MODULE$.createExclusion("org.apache.spark:spark-" + comp + "*:*", ivySettings$1, ivyConfName$2));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$processIvyPathArg$1(final String x$2) {
      return x$2.trim().isEmpty();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$processRemoteRepoArg$1(final String x$3) {
      return x$3.trim().isEmpty();
   }

   // $FF: synthetic method
   public static final void $anonfun$processRemoteRepoArg$4(final ChainResolver cr$1, final DependencyResolver x$1) {
      cr$1.add(x$1);
   }

   // $FF: synthetic method
   public static final void $anonfun$processRemoteRepoArg$5(final ChainResolver cr$1, final PrintStream printStream$2, final Tuple2 x0$1) {
      if (x0$1 != null) {
         String repo = (String)x0$1._1();
         int i = x0$1._2$mcI$sp();
         IBiblioResolver brr = new IBiblioResolver();
         brr.setM2compatible(true);
         brr.setUsepoms(true);
         brr.setRoot(repo);
         brr.setName("repo-" + (i + 1));
         cr$1.add(brr);
         printStream$2.println(repo + " added as a remote repository with the name: " + brr.getName());
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$processRemoteRepoArg$3(final IvySettings ivySettings$2, final PrintStream printStream$2, final String[] repositoryList) {
      ChainResolver cr = new ChainResolver();
      cr.setName("user-list");
      scala.Option..MODULE$.apply(ivySettings$2.getDefaultResolver()).foreach((x$1) -> {
         $anonfun$processRemoteRepoArg$4(cr, x$1);
         return BoxedUnit.UNIT;
      });
      scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps((Object[])scala.collection.ArrayOps..MODULE$.zipWithIndex$extension(scala.Predef..MODULE$.refArrayOps((Object[])repositoryList))), (x0$1) -> {
         $anonfun$processRemoteRepoArg$5(cr, printStream$2, x0$1);
         return BoxedUnit.UNIT;
      });
      ivySettings$2.addResolver(cr);
      ivySettings$2.setDefaultResolver(cr.getName());
   }

   // $FF: synthetic method
   public static final boolean $anonfun$clearIvyResolutionFiles$1(final File defaultCacheFile$1, final String filename) {
      return (new File(defaultCacheFile$1, filename)).delete();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$clearInvalidIvyCacheFiles$1(final File defaultCacheFile$2, final String filename) {
      return (new File(defaultCacheFile$2, filename)).delete();
   }

   // $FF: synthetic method
   public static final void $anonfun$resolveMavenCoordinates$1(final ObjectRef md$3, final IvySettings ivySettings$3, final String ivyConfName$3, final String e) {
      ((DefaultModuleDescriptor)md$3.elem).addExcludeRule(MODULE$.createExclusion(e + ":*", ivySettings$3, ivyConfName$3));
   }

   // $FF: synthetic method
   public static final void $anonfun$resolveMavenCoordinates$4(final IvySettings ivySettings$3, final Artifact artifact) {
      MODULE$.clearInvalidIvyCacheFiles(artifact.getModuleRevisionId(), ivySettings$3.getDefaultCache());
   }

   // $FF: synthetic method
   public static final boolean $anonfun$parseQueryParams$2(final String[] tokens) {
      return MODULE$.isInvalidQueryString(tokens);
   }

   // $FF: synthetic method
   public static final int $anonfun$parseQueryParams$5(final Tuple2[] x$7) {
      return x$7.length;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$parseQueryParams$9(final Tuple2 x$9) {
      return ((String)x$9._2()).equalsIgnoreCase("true");
   }

   // $FF: synthetic method
   public static final boolean $anonfun$parseQueryParams$15(final String[] tokens) {
      return MODULE$.isInvalidQueryString(tokens);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$parseQueryParams$23(final Set validParams$1, final String elem) {
      return validParams$1.contains(elem);
   }

   private MavenUtils$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
