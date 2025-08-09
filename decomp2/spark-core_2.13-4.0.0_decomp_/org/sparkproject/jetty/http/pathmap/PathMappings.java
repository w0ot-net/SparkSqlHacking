package org.sparkproject.jetty.http.pathmap;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Predicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.util.Index;
import org.sparkproject.jetty.util.annotation.ManagedAttribute;
import org.sparkproject.jetty.util.annotation.ManagedObject;
import org.sparkproject.jetty.util.component.Dumpable;

@ManagedObject("Path Mappings")
public class PathMappings implements Iterable, Dumpable {
   private static final Logger LOG = LoggerFactory.getLogger(PathMappings.class);
   private static final int PREFIX_TAIL_LEN = 3;
   private final Set _mappings = new TreeSet(Comparator.comparing(MappedResource::getPathSpec));
   private boolean _orderIsSignificant;
   private boolean _optimizedExact = true;
   private final Map _exactMap = new HashMap();
   private boolean _optimizedPrefix = true;
   private final Index.Mutable _prefixMap = (new Index.Builder()).caseSensitive(true).mutable().build();
   private boolean _optimizedSuffix = true;
   private final Index.Mutable _suffixMap = (new Index.Builder()).caseSensitive(true).mutable().build();
   private MappedResource _servletRoot;
   private MappedResource _servletDefault;

   public String dump() {
      return Dumpable.dump(this);
   }

   public void dump(Appendable out, String indent) throws IOException {
      Dumpable.dumpObjects(out, indent, this.toString(), this._mappings);
   }

   @ManagedAttribute(
      value = "mappings",
      readonly = true
   )
   public List getMappings() {
      return new ArrayList(this._mappings);
   }

   public int size() {
      return this._mappings.size();
   }

   public void reset() {
      this._mappings.clear();
      this._prefixMap.clear();
      this._suffixMap.clear();
      this._optimizedExact = true;
      this._optimizedPrefix = true;
      this._optimizedSuffix = true;
      this._orderIsSignificant = false;
      this._servletRoot = null;
      this._servletDefault = null;
   }

   public void removeIf(Predicate predicate) {
      this._mappings.removeIf(predicate);
   }

   public List getMatchedList(String path) {
      List<MatchedResource<E>> ret = new ArrayList();

      for(MappedResource mr : this._mappings) {
         MatchedPath matchedPath = mr.getPathSpec().matched(path);
         if (matchedPath != null) {
            ret.add(new MatchedResource(mr.getResource(), mr.getPathSpec(), matchedPath));
         }
      }

      return ret;
   }

   public List getMatches(String path) {
      if (this._mappings.isEmpty()) {
         return Collections.emptyList();
      } else {
         boolean isRootPath = "/".equals(path);
         List<MappedResource<E>> matches = null;

         for(MappedResource mr : this._mappings) {
            switch (mr.getPathSpec().getGroup()) {
               case ROOT:
                  if (isRootPath) {
                     if (matches == null) {
                        matches = new ArrayList();
                     }

                     matches.add(mr);
                  }
                  break;
               case DEFAULT:
                  if (isRootPath || mr.getPathSpec().matched(path) != null) {
                     if (matches == null) {
                        matches = new ArrayList();
                     }

                     matches.add(mr);
                  }
                  break;
               default:
                  if (mr.getPathSpec().matched(path) != null) {
                     if (matches == null) {
                        matches = new ArrayList();
                     }

                     matches.add(mr);
                  }
            }
         }

         return matches == null ? Collections.emptyList() : matches;
      }
   }

   public MatchedResource getMatched(String path) {
      if (this._mappings.isEmpty()) {
         return null;
      } else if (this._orderIsSignificant) {
         return this.getMatchedIteratively(path);
      } else if (this._servletRoot != null && "/".equals(path)) {
         return this._servletRoot.getPreMatched();
      } else {
         MappedResource<E> exact = (MappedResource)this._exactMap.get(path);
         if (exact != null) {
            return exact.getPreMatched();
         } else {
            int specLength;
            for(MappedResource<E> prefix = (MappedResource)this._prefixMap.getBest(path); prefix != null; prefix = specLength > 3 ? (MappedResource)this._prefixMap.getBest(path, 0, specLength - 3) : null) {
               PathSpec pathSpec = prefix.getPathSpec();
               MatchedPath matchedPath = pathSpec.matched(path);
               if (matchedPath != null) {
                  return new MatchedResource(prefix.getResource(), pathSpec, matchedPath);
               }

               specLength = pathSpec.getSpecLength();
            }

            if (!this._suffixMap.isEmpty()) {
               int i = Math.max(0, path.lastIndexOf("/"));

               while((i = path.indexOf(46, i + 1)) > 0) {
                  MappedResource<E> suffix = (MappedResource)this._suffixMap.get(path, i + 1, path.length() - i - 1);
                  if (suffix != null) {
                     MatchedPath matchedPath = suffix.getPathSpec().matched(path);
                     if (matchedPath != null) {
                        return new MatchedResource(suffix.getResource(), suffix.getPathSpec(), matchedPath);
                     }
                  }
               }
            }

            return this._servletDefault != null ? new MatchedResource(this._servletDefault.getResource(), this._servletDefault.getPathSpec(), this._servletDefault.getPathSpec().matched(path)) : null;
         }
      }
   }

   private MatchedResource getMatchedIteratively(String path) {
      PathSpecGroup lastGroup = null;
      boolean skipRestOfGroup = false;

      for(MappedResource mr : this._mappings) {
         PathSpecGroup group = mr.getPathSpec().getGroup();
         if (group != lastGroup || !skipRestOfGroup) {
            if (group != lastGroup) {
               skipRestOfGroup = false;
               switch (group) {
                  case EXACT:
                     if (this._optimizedExact) {
                        MappedResource<E> exact = (MappedResource)this._exactMap.get(path);
                        if (exact != null) {
                           return exact.getPreMatched();
                        }

                        skipRestOfGroup = true;
                     }
                     break;
                  case PREFIX_GLOB:
                     if (!this._optimizedPrefix) {
                        break;
                     }

                     int specLength;
                     for(MappedResource<E> prefix = (MappedResource)this._prefixMap.getBest(path); prefix != null; prefix = specLength > 3 ? (MappedResource)this._prefixMap.getBest(path, 0, specLength - 3) : null) {
                        PathSpec pathSpec = prefix.getPathSpec();
                        MatchedPath matchedPath = pathSpec.matched(path);
                        if (matchedPath != null) {
                           return new MatchedResource(prefix.getResource(), pathSpec, matchedPath);
                        }

                        specLength = pathSpec.getSpecLength();
                     }

                     skipRestOfGroup = true;
                     break;
                  case SUFFIX_GLOB:
                     if (this._optimizedSuffix) {
                        int i = 0;

                        while((i = path.indexOf(46, i + 1)) > 0) {
                           MappedResource<E> suffix = (MappedResource)this._suffixMap.get(path, i + 1, path.length() - i - 1);
                           if (suffix != null) {
                              MatchedPath matchedPath = suffix.getPathSpec().matched(path);
                              if (matchedPath != null) {
                                 return new MatchedResource(suffix.getResource(), suffix.getPathSpec(), matchedPath);
                              }
                           }
                        }

                        skipRestOfGroup = true;
                     }
               }
            }

            MatchedPath matchedPath = mr.getPathSpec().matched(path);
            if (matchedPath != null) {
               return new MatchedResource(mr.getResource(), mr.getPathSpec(), matchedPath);
            }

            lastGroup = group;
         }
      }

      return null;
   }

   /** @deprecated */
   @Deprecated(
      forRemoval = true
   )
   public MappedResource getMatch(String path) {
      MatchedResource<E> matchedPath = this.getMatched(path);
      return new MappedResource(matchedPath.getPathSpec(), matchedPath.getResource());
   }

   public Iterator iterator() {
      return this._mappings.iterator();
   }

   /** @deprecated */
   @Deprecated(
      forRemoval = true
   )
   public static PathSpec asPathSpec(String pathSpecString) {
      return PathSpec.from(pathSpecString);
   }

   public Object get(PathSpec spec) {
      return this._mappings.stream().filter((mappedResource) -> mappedResource.getPathSpec().equals(spec)).map(MappedResource::getResource).findFirst().orElse((Object)null);
   }

   public boolean put(String pathSpecString, Object resource) {
      return this.put(PathSpec.from(pathSpecString), resource);
   }

   public boolean put(PathSpec pathSpec, Object resource) {
      MappedResource<E> entry = new MappedResource(pathSpec, resource);
      boolean added = this._mappings.add(entry);
      if (LOG.isDebugEnabled()) {
         LOG.debug("{} {} to {}", new Object[]{added ? "Added" : "Ignored", entry, this});
      }

      if (added) {
         switch (pathSpec.getGroup()) {
            case ROOT:
               if (pathSpec instanceof ServletPathSpec) {
                  if (this._servletRoot == null) {
                     this._servletRoot = entry;
                  }
               } else {
                  this._orderIsSignificant = true;
               }
               break;
            case DEFAULT:
               if (pathSpec instanceof ServletPathSpec) {
                  if (this._servletDefault == null) {
                     this._servletDefault = entry;
                  }
               } else {
                  this._orderIsSignificant = true;
               }
               break;
            case EXACT:
               if (pathSpec instanceof ServletPathSpec) {
                  String exact = pathSpec.getDeclaration();
                  if (exact != null) {
                     this._exactMap.put(exact, entry);
                  }
               } else {
                  this._optimizedExact = false;
                  this._orderIsSignificant = true;
               }
               break;
            case PREFIX_GLOB:
               if (pathSpec instanceof ServletPathSpec) {
                  String prefix = pathSpec.getPrefix();
                  if (prefix != null) {
                     this._prefixMap.put(prefix, entry);
                  }
               } else {
                  this._optimizedPrefix = false;
                  this._orderIsSignificant = true;
               }
               break;
            case SUFFIX_GLOB:
               if (pathSpec instanceof ServletPathSpec) {
                  String suffix = pathSpec.getSuffix();
                  if (suffix != null) {
                     this._suffixMap.put(suffix, entry);
                  }
               } else {
                  this._optimizedSuffix = false;
                  this._orderIsSignificant = true;
               }
         }
      }

      return added;
   }

   public boolean remove(PathSpec pathSpec) {
      Iterator<MappedResource<E>> iter = this._mappings.iterator();
      boolean removed = false;

      while(iter.hasNext()) {
         if (((MappedResource)iter.next()).getPathSpec().equals(pathSpec)) {
            removed = true;
            iter.remove();
            break;
         }
      }

      if (LOG.isDebugEnabled()) {
         LOG.debug("{} {} to {}", new Object[]{removed ? "Removed" : "Ignored", pathSpec, this});
      }

      if (removed) {
         switch (pathSpec.getGroup()) {
            case ROOT:
               this._servletRoot = (MappedResource)this._mappings.stream().filter((mapping) -> mapping.getPathSpec().getGroup() == PathSpecGroup.ROOT).filter((mapping) -> mapping.getPathSpec() instanceof ServletPathSpec).findFirst().orElse((Object)null);
               this._orderIsSignificant = this.nonServletPathSpec();
               break;
            case DEFAULT:
               this._servletDefault = (MappedResource)this._mappings.stream().filter((mapping) -> mapping.getPathSpec().getGroup() == PathSpecGroup.DEFAULT).filter((mapping) -> mapping.getPathSpec() instanceof ServletPathSpec).findFirst().orElse((Object)null);
               this._orderIsSignificant = this.nonServletPathSpec();
               break;
            case EXACT:
               String exact = pathSpec.getDeclaration();
               if (exact != null) {
                  this._exactMap.remove(exact);
                  this._optimizedExact = this.canBeOptimized(PathSpecGroup.EXACT);
                  this._orderIsSignificant = this.nonServletPathSpec();
               }
               break;
            case PREFIX_GLOB:
               String prefix = pathSpec.getPrefix();
               if (prefix != null) {
                  this._prefixMap.remove(prefix);
                  this._optimizedPrefix = this.canBeOptimized(PathSpecGroup.PREFIX_GLOB);
                  this._orderIsSignificant = this.nonServletPathSpec();
               }
               break;
            case SUFFIX_GLOB:
               String suffix = pathSpec.getSuffix();
               if (suffix != null) {
                  this._suffixMap.remove(suffix);
                  this._optimizedSuffix = this.canBeOptimized(PathSpecGroup.SUFFIX_GLOB);
                  this._orderIsSignificant = this.nonServletPathSpec();
               }
         }
      }

      return removed;
   }

   private boolean canBeOptimized(PathSpecGroup suffixGlob) {
      return this._mappings.stream().filter((mapping) -> mapping.getPathSpec().getGroup() == suffixGlob).allMatch((mapping) -> mapping.getPathSpec() instanceof ServletPathSpec);
   }

   private boolean nonServletPathSpec() {
      return this._mappings.stream().allMatch((mapping) -> mapping.getPathSpec() instanceof ServletPathSpec);
   }

   public String toString() {
      return String.format("%s[size=%d]", this.getClass().getSimpleName(), this._mappings.size());
   }
}
