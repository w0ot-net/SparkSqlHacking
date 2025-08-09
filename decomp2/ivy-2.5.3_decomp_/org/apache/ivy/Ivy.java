package org.apache.ivy;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.apache.ivy.core.IvyContext;
import org.apache.ivy.core.cache.ResolutionCacheManager;
import org.apache.ivy.core.check.CheckEngine;
import org.apache.ivy.core.deliver.DeliverEngine;
import org.apache.ivy.core.deliver.DeliverOptions;
import org.apache.ivy.core.event.EventManager;
import org.apache.ivy.core.install.InstallEngine;
import org.apache.ivy.core.install.InstallOptions;
import org.apache.ivy.core.module.descriptor.ModuleDescriptor;
import org.apache.ivy.core.module.id.ModuleId;
import org.apache.ivy.core.module.id.ModuleRevisionId;
import org.apache.ivy.core.publish.PublishEngine;
import org.apache.ivy.core.publish.PublishOptions;
import org.apache.ivy.core.report.ResolveReport;
import org.apache.ivy.core.repository.RepositoryManagementEngine;
import org.apache.ivy.core.resolve.IvyNode;
import org.apache.ivy.core.resolve.ResolveData;
import org.apache.ivy.core.resolve.ResolveEngine;
import org.apache.ivy.core.resolve.ResolveOptions;
import org.apache.ivy.core.resolve.ResolvedModuleRevision;
import org.apache.ivy.core.retrieve.RetrieveEngine;
import org.apache.ivy.core.retrieve.RetrieveOptions;
import org.apache.ivy.core.retrieve.RetrieveReport;
import org.apache.ivy.core.search.ModuleEntry;
import org.apache.ivy.core.search.OrganisationEntry;
import org.apache.ivy.core.search.RevisionEntry;
import org.apache.ivy.core.search.SearchEngine;
import org.apache.ivy.core.settings.IvySettings;
import org.apache.ivy.core.sort.SortEngine;
import org.apache.ivy.core.sort.SortOptions;
import org.apache.ivy.plugins.matcher.PatternMatcher;
import org.apache.ivy.plugins.repository.TransferEvent;
import org.apache.ivy.plugins.repository.TransferListener;
import org.apache.ivy.plugins.resolver.BasicResolver;
import org.apache.ivy.plugins.resolver.DependencyResolver;
import org.apache.ivy.plugins.trigger.Trigger;
import org.apache.ivy.util.HostUtil;
import org.apache.ivy.util.Message;
import org.apache.ivy.util.MessageLoggerEngine;
import org.apache.ivy.util.filter.Filter;

public class Ivy {
   private static final int KILO = 1024;
   /** @deprecated */
   @Deprecated
   public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMddHHmmss");
   private static final String IVY_VERSION;
   private static final String IVY_DATE;
   private boolean interrupted;
   private boolean bound;
   private IvySettings settings;
   private EventManager eventManager;
   private SortEngine sortEngine;
   private SearchEngine searchEngine;
   private CheckEngine checkEngine;
   private ResolveEngine resolveEngine;
   private RetrieveEngine retrieveEngine;
   private DeliverEngine deliverEngine;
   private PublishEngine publishEngine;
   private InstallEngine installEngine;
   private RepositoryManagementEngine repositoryEngine;
   private MessageLoggerEngine loggerEngine = new MessageLoggerEngine();

   public static String getIvyVersion() {
      return IVY_VERSION;
   }

   public static String getIvyDate() {
      return IVY_DATE;
   }

   public static String getIvyHomeURL() {
      return "https://ant.apache.org/ivy/";
   }

   public static Ivy newInstance() {
      Ivy ivy = new Ivy();
      ivy.bind();
      return ivy;
   }

   public static Ivy newInstance(IvySettings settings) {
      Ivy ivy = new Ivy();
      ivy.setSettings(settings);
      ivy.bind();
      return ivy;
   }

   public void bind() {
      this.pushContext();

      try {
         if (this.settings == null) {
            this.settings = new IvySettings();
         }

         if (this.eventManager == null) {
            this.eventManager = new EventManager();
         }

         if (this.sortEngine == null) {
            this.sortEngine = new SortEngine(this.settings);
         }

         if (this.searchEngine == null) {
            this.searchEngine = new SearchEngine(this.settings);
         }

         if (this.resolveEngine == null) {
            this.resolveEngine = new ResolveEngine(this.settings, this.eventManager, this.sortEngine);
         }

         if (this.retrieveEngine == null) {
            this.retrieveEngine = new RetrieveEngine(this.settings, this.eventManager);
         }

         if (this.deliverEngine == null) {
            this.deliverEngine = new DeliverEngine(this.settings);
         }

         if (this.publishEngine == null) {
            this.publishEngine = new PublishEngine(this.settings, this.eventManager);
         }

         if (this.installEngine == null) {
            this.installEngine = new InstallEngine(this.settings, this.searchEngine, this.resolveEngine);
         }

         if (this.repositoryEngine == null) {
            this.repositoryEngine = new RepositoryManagementEngine(this.settings, this.searchEngine, this.resolveEngine);
         }

         this.eventManager.addTransferListener(new TransferListener() {
            public void transferProgress(TransferEvent evt) {
               switch (evt.getEventType()) {
                  case 2:
                     ResolveData resolve = IvyContext.getContext().getResolveData();
                     if (resolve == null || !"quiet".equals(resolve.getOptions().getLog())) {
                        Message.endProgress(" (" + evt.getTotalLength() / 1024L + "kB)");
                     }
                     break;
                  case 3:
                     ResolveData resolve = IvyContext.getContext().getResolveData();
                     if (resolve == null || !"quiet".equals(resolve.getOptions().getLog())) {
                        Message.progress();
                     }
               }

            }
         });
         this.bound = true;
      } finally {
         this.popContext();
      }

   }

   public Object execute(IvyCallback callback) {
      this.pushContext();

      Object var2;
      try {
         var2 = callback.doInIvyContext(this, IvyContext.getContext());
      } finally {
         this.popContext();
      }

      return var2;
   }

   public void pushContext() {
      if (IvyContext.getContext().peekIvy() != this) {
         IvyContext.pushNewContext();
         IvyContext.getContext().setIvy(this);
      } else {
         IvyContext.pushContext(IvyContext.getContext());
      }

   }

   public void popContext() {
      IvyContext.popContext();
   }

   public void configure(File settingsFile) throws ParseException, IOException {
      this.pushContext();

      try {
         this.assertBound();
         this.settings.load(settingsFile);
         this.postConfigure();
      } finally {
         this.popContext();
      }

   }

   public void configure(URL settingsURL) throws ParseException, IOException {
      this.pushContext();

      try {
         this.assertBound();
         this.settings.load(settingsURL);
         this.postConfigure();
      } finally {
         this.popContext();
      }

   }

   public void configureDefault() throws ParseException, IOException {
      this.pushContext();

      try {
         this.assertBound();
         this.settings.loadDefault();
         this.postConfigure();
      } finally {
         this.popContext();
      }

   }

   public void configureDefault14() throws ParseException, IOException {
      this.pushContext();

      try {
         this.assertBound();
         this.settings.loadDefault14();
         this.postConfigure();
      } finally {
         this.popContext();
      }

   }

   public boolean check(URL ivyFile, String resolvername) {
      this.pushContext();

      boolean var3;
      try {
         var3 = this.checkEngine.check(ivyFile, resolvername);
      } finally {
         this.popContext();
      }

      return var3;
   }

   public ResolveReport resolve(File ivySource) throws ParseException, IOException {
      this.pushContext();

      ResolveReport var2;
      try {
         var2 = this.resolveEngine.resolve(ivySource);
      } finally {
         this.popContext();
      }

      return var2;
   }

   public ResolveReport resolve(URL ivySource) throws ParseException, IOException {
      this.pushContext();

      ResolveReport var2;
      try {
         var2 = this.resolveEngine.resolve(ivySource);
      } finally {
         this.popContext();
      }

      return var2;
   }

   public ResolveReport resolve(ModuleRevisionId mrid, ResolveOptions options, boolean changing) throws ParseException, IOException {
      this.pushContext();

      ResolveReport var4;
      try {
         var4 = this.resolveEngine.resolve(mrid, options, changing);
      } finally {
         this.popContext();
      }

      return var4;
   }

   public ResolveReport resolve(URL ivySource, ResolveOptions options) throws ParseException, IOException {
      this.pushContext();

      ResolveReport var3;
      try {
         var3 = this.resolveEngine.resolve(ivySource, options);
      } finally {
         this.popContext();
      }

      return var3;
   }

   public ResolveReport resolve(File ivySource, ResolveOptions options) throws ParseException, IOException {
      return this.resolve(ivySource.toURI().toURL(), options);
   }

   public ResolveReport resolve(ModuleDescriptor md, ResolveOptions options) throws ParseException, IOException {
      this.pushContext();

      ResolveReport var3;
      try {
         var3 = this.resolveEngine.resolve(md, options);
      } finally {
         this.popContext();
      }

      return var3;
   }

   public ResolveReport install(ModuleRevisionId mrid, String from, String to, InstallOptions options) throws IOException {
      this.pushContext();

      ResolveReport var5;
      try {
         var5 = this.installEngine.install(mrid, from, to, options);
      } finally {
         this.popContext();
      }

      return var5;
   }

   /** @deprecated */
   @Deprecated
   public int retrieve(ModuleRevisionId mrid, String destFilePattern, RetrieveOptions options) throws IOException {
      this.pushContext();

      int var4;
      try {
         var4 = this.retrieveEngine.retrieve(mrid, destFilePattern, options);
      } finally {
         this.popContext();
      }

      return var4;
   }

   public RetrieveReport retrieve(ModuleRevisionId mrid, RetrieveOptions options) throws IOException {
      this.pushContext();

      RetrieveReport var3;
      try {
         var3 = this.retrieveEngine.retrieve(mrid, options);
      } finally {
         this.popContext();
      }

      return var3;
   }

   public void deliver(ModuleRevisionId mrid, String revision, String destIvyPattern) throws IOException, ParseException {
      this.pushContext();

      try {
         this.deliverEngine.deliver(mrid, revision, destIvyPattern, DeliverOptions.newInstance(this.settings));
      } finally {
         this.popContext();
      }

   }

   public void deliver(String revision, String destIvyPattern, DeliverOptions options) throws IOException, ParseException {
      this.pushContext();

      try {
         this.deliverEngine.deliver(revision, destIvyPattern, options);
      } finally {
         this.popContext();
      }

   }

   public void deliver(ModuleRevisionId mrid, String revision, String destIvyPattern, DeliverOptions options) throws IOException, ParseException {
      this.pushContext();

      try {
         this.deliverEngine.deliver(mrid, revision, destIvyPattern, options);
      } finally {
         this.popContext();
      }

   }

   public Collection publish(ModuleRevisionId mrid, Collection srcArtifactPattern, String resolverName, PublishOptions options) throws IOException {
      this.pushContext();

      Collection var5;
      try {
         var5 = this.publishEngine.publish(mrid, srcArtifactPattern, resolverName, options);
      } finally {
         this.popContext();
      }

      return var5;
   }

   public List sortNodes(Collection nodes, SortOptions options) {
      this.pushContext();

      List var3;
      try {
         var3 = this.getSortEngine().sortNodes(nodes, options);
      } finally {
         this.popContext();
      }

      return var3;
   }

   public List sortModuleDescriptors(Collection moduleDescriptors, SortOptions options) {
      this.pushContext();

      List var3;
      try {
         var3 = this.getSortEngine().sortModuleDescriptors(moduleDescriptors, options);
      } finally {
         this.popContext();
      }

      return var3;
   }

   public ResolvedModuleRevision findModule(ModuleRevisionId mrid) {
      this.pushContext();

      ResolvedModuleRevision var3;
      try {
         ResolveOptions options = new ResolveOptions();
         options.setValidate(false);
         var3 = this.resolveEngine.findModule(mrid, options);
      } finally {
         this.popContext();
      }

      return var3;
   }

   public ModuleEntry[] listModuleEntries(OrganisationEntry org) {
      this.pushContext();

      ModuleEntry[] var2;
      try {
         var2 = this.searchEngine.listModuleEntries(org);
      } finally {
         this.popContext();
      }

      return var2;
   }

   public ModuleId[] listModules(ModuleId criteria, PatternMatcher matcher) {
      this.pushContext();

      ModuleId[] var3;
      try {
         var3 = this.searchEngine.listModules(criteria, matcher);
      } finally {
         this.popContext();
      }

      return var3;
   }

   public ModuleRevisionId[] listModules(ModuleRevisionId criteria, PatternMatcher matcher) {
      this.pushContext();

      ModuleRevisionId[] var3;
      try {
         var3 = this.searchEngine.listModules(criteria, matcher);
      } finally {
         this.popContext();
      }

      return var3;
   }

   public String[] listModules(String org) {
      this.pushContext();

      String[] var2;
      try {
         var2 = this.searchEngine.listModules(org);
      } finally {
         this.popContext();
      }

      return var2;
   }

   public OrganisationEntry[] listOrganisationEntries() {
      this.pushContext();

      OrganisationEntry[] var1;
      try {
         var1 = this.searchEngine.listOrganisationEntries();
      } finally {
         this.popContext();
      }

      return var1;
   }

   public String[] listOrganisations() {
      this.pushContext();

      String[] var1;
      try {
         var1 = this.searchEngine.listOrganisations();
      } finally {
         this.popContext();
      }

      return var1;
   }

   public RevisionEntry[] listRevisionEntries(ModuleEntry module) {
      this.pushContext();

      RevisionEntry[] var2;
      try {
         var2 = this.searchEngine.listRevisionEntries(module);
      } finally {
         this.popContext();
      }

      return var2;
   }

   public String[] listRevisions(String org, String module) {
      this.pushContext();

      String[] var3;
      try {
         var3 = this.searchEngine.listRevisions(org, module);
      } finally {
         this.popContext();
      }

      return var3;
   }

   public String[] listTokenValues(String token, Map otherTokenValues) {
      this.pushContext();

      String[] var3;
      try {
         var3 = this.searchEngine.listTokenValues(token, otherTokenValues);
      } finally {
         this.popContext();
      }

      return var3;
   }

   public void interrupt() {
      Thread operatingThread = IvyContext.getContext().getOperatingThread();
      this.interrupt(operatingThread);
   }

   public void interrupt(Thread operatingThread) {
      if (operatingThread != null && operatingThread.isAlive()) {
         if (operatingThread == Thread.currentThread()) {
            throw new IllegalStateException("cannot call interrupt from ivy operating thread");
         }

         Message.verbose("interrupting operating thread...");
         operatingThread.interrupt();
         synchronized(this) {
            this.interrupted = true;
         }

         try {
            Message.verbose("waiting clean interruption of operating thread");
            operatingThread.join(this.settings.getInterruptTimeout());
         } catch (InterruptedException var6) {
            Thread.currentThread().interrupt();
         }

         if (operatingThread.isAlive()) {
            Message.warn("waited clean interruption for too long: stopping operating thread");
            operatingThread.stop();
         }

         synchronized(this) {
            this.interrupted = false;
         }
      }

   }

   public synchronized boolean isInterrupted() {
      return this.interrupted;
   }

   public void checkInterrupted() {
      if (this.isInterrupted()) {
         Message.info("operation interrupted");
         throw new RuntimeException("operation interrupted");
      }
   }

   public static String getWorkingRevision() {
      return "working@" + HostUtil.getLocalHostName();
   }

   public ResolutionCacheManager getResolutionCacheManager() {
      return this.settings.getResolutionCacheManager();
   }

   private void assertBound() {
      if (!this.bound) {
         this.bind();
      }

   }

   private void postConfigure() {
      for(Trigger trigger : this.settings.getTriggers()) {
         this.eventManager.addIvyListener(trigger, (Filter)trigger.getEventFilter());
      }

      for(DependencyResolver resolver : this.settings.getResolvers()) {
         if (resolver instanceof BasicResolver) {
            ((BasicResolver)resolver).setEventManager(this.eventManager);
         }
      }

   }

   public String getVariable(String name) {
      this.pushContext();

      String var2;
      try {
         this.assertBound();
         var2 = this.settings.getVariable(name);
      } finally {
         this.popContext();
      }

      return var2;
   }

   public String substitute(String str) {
      this.pushContext();

      String var2;
      try {
         this.assertBound();
         var2 = this.settings.substitute(str);
      } finally {
         this.popContext();
      }

      return var2;
   }

   public void setVariable(String varName, String value) {
      this.pushContext();

      try {
         this.assertBound();
         this.settings.setVariable(varName, value);
      } finally {
         this.popContext();
      }

   }

   public IvySettings getSettings() {
      return this.settings;
   }

   public EventManager getEventManager() {
      return this.eventManager;
   }

   public CheckEngine getCheckEngine() {
      return this.checkEngine;
   }

   public void setCheckEngine(CheckEngine checkEngine) {
      this.checkEngine = checkEngine;
   }

   public DeliverEngine getDeliverEngine() {
      return this.deliverEngine;
   }

   public void setDeliverEngine(DeliverEngine deliverEngine) {
      this.deliverEngine = deliverEngine;
   }

   public InstallEngine getInstallEngine() {
      return this.installEngine;
   }

   public void setInstallEngine(InstallEngine installEngine) {
      this.installEngine = installEngine;
   }

   public PublishEngine getPublishEngine() {
      return this.publishEngine;
   }

   public void setPublishEngine(PublishEngine publishEngine) {
      this.publishEngine = publishEngine;
   }

   public ResolveEngine getResolveEngine() {
      return this.resolveEngine;
   }

   public void setResolveEngine(ResolveEngine resolveEngine) {
      this.resolveEngine = resolveEngine;
   }

   public RetrieveEngine getRetrieveEngine() {
      return this.retrieveEngine;
   }

   public void setRetrieveEngine(RetrieveEngine retrieveEngine) {
      this.retrieveEngine = retrieveEngine;
   }

   public SearchEngine getSearchEngine() {
      return this.searchEngine;
   }

   public void setSearchEngine(SearchEngine searchEngine) {
      this.searchEngine = searchEngine;
   }

   public SortEngine getSortEngine() {
      return this.sortEngine;
   }

   public void setSortEngine(SortEngine sortEngine) {
      this.sortEngine = sortEngine;
   }

   public RepositoryManagementEngine getRepositoryEngine() {
      return this.repositoryEngine;
   }

   public void setRepositoryEngine(RepositoryManagementEngine repositoryEngine) {
      this.repositoryEngine = repositoryEngine;
   }

   public void setEventManager(EventManager eventManager) {
      this.eventManager = eventManager;
   }

   public void setSettings(IvySettings settings) {
      this.settings = settings;
   }

   public MessageLoggerEngine getLoggerEngine() {
      return this.loggerEngine;
   }

   static {
      Properties props = new Properties();
      URL moduleURL = Message.class.getResource("/module.properties");
      if (moduleURL != null) {
         try {
            InputStream module = moduleURL.openStream();
            props.load(module);
            module.close();
         } catch (IOException var3) {
         }
      }

      IVY_VERSION = props.getProperty("version", "non official version");
      IVY_DATE = props.getProperty("date", "");
   }

   public interface IvyCallback {
      Object doInIvyContext(Ivy var1, IvyContext var2);
   }
}
