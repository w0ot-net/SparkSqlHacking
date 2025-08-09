package org.apache.ivy.osgi.repo;

import java.io.IOException;
import java.net.URI;
import java.text.ParseException;
import java.util.Date;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.jar.JarInputStream;
import java.util.jar.Manifest;
import org.apache.ivy.core.event.EventManager;
import org.apache.ivy.core.module.descriptor.Artifact;
import org.apache.ivy.core.module.descriptor.DefaultDependencyDescriptor;
import org.apache.ivy.core.module.descriptor.ModuleDescriptor;
import org.apache.ivy.core.module.id.ModuleRevisionId;
import org.apache.ivy.core.resolve.ResolveData;
import org.apache.ivy.core.resolve.ResolveEngine;
import org.apache.ivy.core.resolve.ResolveOptions;
import org.apache.ivy.core.resolve.ResolvedModuleRevision;
import org.apache.ivy.core.search.ModuleEntry;
import org.apache.ivy.core.search.OrganisationEntry;
import org.apache.ivy.core.search.RevisionEntry;
import org.apache.ivy.core.settings.IvySettings;
import org.apache.ivy.core.sort.SortEngine;
import org.apache.ivy.osgi.core.BundleInfoAdapter;
import org.apache.ivy.plugins.resolver.BasicResolver;
import org.apache.ivy.plugins.resolver.util.ResolvedResource;
import org.apache.ivy.util.Message;

public class ResolverManifestIterable implements Iterable {
   private final BasicResolver resolver;

   public ResolverManifestIterable(BasicResolver resolver) {
      this.resolver = resolver;
   }

   public Iterator iterator() {
      return new ResolverManifestIterator();
   }

   class ResolverManifestIterator implements Iterator {
      private OrganisationEntry[] organisations;
      private int indexOrganisation = 0;
      private OrganisationEntry organisation;
      private ModuleEntry[] modules;
      private int indexModule = -1;
      private ModuleEntry module;
      private ManifestAndLocation next = null;
      private RevisionEntry[] revisions;
      private int indexRevision;
      private RevisionEntry revision;
      private Artifact[] artifacts;
      private int indexArtifact;
      private Artifact artifact;
      private ModuleRevisionId mrid;
      private ResolveData data;

      public ResolverManifestIterator() {
         this.organisations = ResolverManifestIterable.this.resolver.listOrganisations();
         IvySettings settings = new IvySettings();
         ResolveEngine engine = new ResolveEngine(settings, new EventManager(), new SortEngine(settings));
         this.data = new ResolveData(engine, new ResolveOptions());
      }

      public boolean hasNext() {
         while(this.next == null) {
            if (this.organisation == null) {
               if (this.indexOrganisation >= this.organisations.length) {
                  return false;
               }

               this.organisation = this.organisations[this.indexOrganisation++];
               this.modules = ResolverManifestIterable.this.resolver.listModules(this.organisation);
               this.indexModule = 0;
               this.module = null;
            }

            if (this.module == null) {
               if (this.indexModule >= this.modules.length) {
                  this.organisation = null;
                  continue;
               }

               this.module = this.modules[this.indexModule++];
               this.revisions = ResolverManifestIterable.this.resolver.listRevisions(this.module);
               this.indexRevision = 0;
               this.revision = null;
            }

            if (this.revision == null) {
               if (this.indexRevision >= this.revisions.length) {
                  this.module = null;
                  continue;
               }

               this.revision = this.revisions[this.indexRevision++];
               this.mrid = ModuleRevisionId.newInstance(this.organisation.getOrganisation(), this.module.getModule(), this.revision.getRevision());
               DefaultDependencyDescriptor dd = new DefaultDependencyDescriptor(this.mrid, false);

               ResolvedModuleRevision dependency;
               try {
                  dependency = ResolverManifestIterable.this.resolver.getDependency(dd, this.data);
               } catch (ParseException e) {
                  Message.error("Error while resolving " + this.mrid + " : " + e.getMessage());
                  this.revision = null;
                  continue;
               }

               if (dependency == null) {
                  this.revision = null;
                  continue;
               }

               ModuleDescriptor md = dependency.getDescriptor();
               this.mrid = md.getModuleRevisionId();
               this.artifacts = md.getAllArtifacts();
               this.indexArtifact = 0;
               this.artifact = null;
            }

            if (this.artifact == null) {
               if (this.indexArtifact >= this.artifacts.length) {
                  this.revision = null;
                  continue;
               }

               this.artifact = this.artifacts[this.indexArtifact++];
            }

            ResolvedResource resource = ResolverManifestIterable.this.resolver.doFindArtifactRef(this.artifact, (Date)null);
            if (resource == null) {
               this.artifact = null;
            } else {
               JarInputStream in;
               try {
                  in = new JarInputStream(resource.getResource().openStream());
               } catch (IOException e) {
                  Message.warn("Unreadable jar " + resource.getResource().getName() + " (" + e.getMessage() + ")");
                  this.artifact = null;
                  continue;
               }

               Manifest manifest;
               try {
                  manifest = in.getManifest();
               } finally {
                  try {
                     in.close();
                  } catch (IOException var12) {
                  }

               }

               if (manifest == null) {
                  Message.debug("No manifest on " + this.artifact);
               } else {
                  URI uri = BundleInfoAdapter.buildIvyURI(this.artifact);
                  this.next = new ManifestAndLocation(manifest, uri, (URI)null);
               }

               this.artifact = null;
            }
         }

         return true;
      }

      public ManifestAndLocation next() {
         if (!this.hasNext()) {
            throw new NoSuchElementException();
         } else {
            ManifestAndLocation manifest = this.next;
            this.next = null;
            return manifest;
         }
      }

      public void remove() {
         throw new UnsupportedOperationException();
      }
   }
}
