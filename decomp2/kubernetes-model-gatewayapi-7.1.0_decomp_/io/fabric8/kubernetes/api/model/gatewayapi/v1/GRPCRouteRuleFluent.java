package io.fabric8.kubernetes.api.model.gatewayapi.v1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

public class GRPCRouteRuleFluent extends BaseFluent {
   private ArrayList backendRefs = new ArrayList();
   private ArrayList filters = new ArrayList();
   private ArrayList matches = new ArrayList();
   private String name;
   private SessionPersistenceBuilder sessionPersistence;
   private Map additionalProperties;

   public GRPCRouteRuleFluent() {
   }

   public GRPCRouteRuleFluent(GRPCRouteRule instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(GRPCRouteRule instance) {
      instance = instance != null ? instance : new GRPCRouteRule();
      if (instance != null) {
         this.withBackendRefs(instance.getBackendRefs());
         this.withFilters(instance.getFilters());
         this.withMatches(instance.getMatches());
         this.withName(instance.getName());
         this.withSessionPersistence(instance.getSessionPersistence());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public GRPCRouteRuleFluent addToBackendRefs(int index, GRPCBackendRef item) {
      if (this.backendRefs == null) {
         this.backendRefs = new ArrayList();
      }

      GRPCBackendRefBuilder builder = new GRPCBackendRefBuilder(item);
      if (index >= 0 && index < this.backendRefs.size()) {
         this._visitables.get("backendRefs").add(index, builder);
         this.backendRefs.add(index, builder);
      } else {
         this._visitables.get("backendRefs").add(builder);
         this.backendRefs.add(builder);
      }

      return this;
   }

   public GRPCRouteRuleFluent setToBackendRefs(int index, GRPCBackendRef item) {
      if (this.backendRefs == null) {
         this.backendRefs = new ArrayList();
      }

      GRPCBackendRefBuilder builder = new GRPCBackendRefBuilder(item);
      if (index >= 0 && index < this.backendRefs.size()) {
         this._visitables.get("backendRefs").set(index, builder);
         this.backendRefs.set(index, builder);
      } else {
         this._visitables.get("backendRefs").add(builder);
         this.backendRefs.add(builder);
      }

      return this;
   }

   public GRPCRouteRuleFluent addToBackendRefs(GRPCBackendRef... items) {
      if (this.backendRefs == null) {
         this.backendRefs = new ArrayList();
      }

      for(GRPCBackendRef item : items) {
         GRPCBackendRefBuilder builder = new GRPCBackendRefBuilder(item);
         this._visitables.get("backendRefs").add(builder);
         this.backendRefs.add(builder);
      }

      return this;
   }

   public GRPCRouteRuleFluent addAllToBackendRefs(Collection items) {
      if (this.backendRefs == null) {
         this.backendRefs = new ArrayList();
      }

      for(GRPCBackendRef item : items) {
         GRPCBackendRefBuilder builder = new GRPCBackendRefBuilder(item);
         this._visitables.get("backendRefs").add(builder);
         this.backendRefs.add(builder);
      }

      return this;
   }

   public GRPCRouteRuleFluent removeFromBackendRefs(GRPCBackendRef... items) {
      if (this.backendRefs == null) {
         return this;
      } else {
         for(GRPCBackendRef item : items) {
            GRPCBackendRefBuilder builder = new GRPCBackendRefBuilder(item);
            this._visitables.get("backendRefs").remove(builder);
            this.backendRefs.remove(builder);
         }

         return this;
      }
   }

   public GRPCRouteRuleFluent removeAllFromBackendRefs(Collection items) {
      if (this.backendRefs == null) {
         return this;
      } else {
         for(GRPCBackendRef item : items) {
            GRPCBackendRefBuilder builder = new GRPCBackendRefBuilder(item);
            this._visitables.get("backendRefs").remove(builder);
            this.backendRefs.remove(builder);
         }

         return this;
      }
   }

   public GRPCRouteRuleFluent removeMatchingFromBackendRefs(Predicate predicate) {
      if (this.backendRefs == null) {
         return this;
      } else {
         Iterator<GRPCBackendRefBuilder> each = this.backendRefs.iterator();
         List visitables = this._visitables.get("backendRefs");

         while(each.hasNext()) {
            GRPCBackendRefBuilder builder = (GRPCBackendRefBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildBackendRefs() {
      return this.backendRefs != null ? build(this.backendRefs) : null;
   }

   public GRPCBackendRef buildBackendRef(int index) {
      return ((GRPCBackendRefBuilder)this.backendRefs.get(index)).build();
   }

   public GRPCBackendRef buildFirstBackendRef() {
      return ((GRPCBackendRefBuilder)this.backendRefs.get(0)).build();
   }

   public GRPCBackendRef buildLastBackendRef() {
      return ((GRPCBackendRefBuilder)this.backendRefs.get(this.backendRefs.size() - 1)).build();
   }

   public GRPCBackendRef buildMatchingBackendRef(Predicate predicate) {
      for(GRPCBackendRefBuilder item : this.backendRefs) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingBackendRef(Predicate predicate) {
      for(GRPCBackendRefBuilder item : this.backendRefs) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public GRPCRouteRuleFluent withBackendRefs(List backendRefs) {
      if (this.backendRefs != null) {
         this._visitables.get("backendRefs").clear();
      }

      if (backendRefs != null) {
         this.backendRefs = new ArrayList();

         for(GRPCBackendRef item : backendRefs) {
            this.addToBackendRefs(item);
         }
      } else {
         this.backendRefs = null;
      }

      return this;
   }

   public GRPCRouteRuleFluent withBackendRefs(GRPCBackendRef... backendRefs) {
      if (this.backendRefs != null) {
         this.backendRefs.clear();
         this._visitables.remove("backendRefs");
      }

      if (backendRefs != null) {
         for(GRPCBackendRef item : backendRefs) {
            this.addToBackendRefs(item);
         }
      }

      return this;
   }

   public boolean hasBackendRefs() {
      return this.backendRefs != null && !this.backendRefs.isEmpty();
   }

   public BackendRefsNested addNewBackendRef() {
      return new BackendRefsNested(-1, (GRPCBackendRef)null);
   }

   public BackendRefsNested addNewBackendRefLike(GRPCBackendRef item) {
      return new BackendRefsNested(-1, item);
   }

   public BackendRefsNested setNewBackendRefLike(int index, GRPCBackendRef item) {
      return new BackendRefsNested(index, item);
   }

   public BackendRefsNested editBackendRef(int index) {
      if (this.backendRefs.size() <= index) {
         throw new RuntimeException("Can't edit backendRefs. Index exceeds size.");
      } else {
         return this.setNewBackendRefLike(index, this.buildBackendRef(index));
      }
   }

   public BackendRefsNested editFirstBackendRef() {
      if (this.backendRefs.size() == 0) {
         throw new RuntimeException("Can't edit first backendRefs. The list is empty.");
      } else {
         return this.setNewBackendRefLike(0, this.buildBackendRef(0));
      }
   }

   public BackendRefsNested editLastBackendRef() {
      int index = this.backendRefs.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last backendRefs. The list is empty.");
      } else {
         return this.setNewBackendRefLike(index, this.buildBackendRef(index));
      }
   }

   public BackendRefsNested editMatchingBackendRef(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.backendRefs.size(); ++i) {
         if (predicate.test((GRPCBackendRefBuilder)this.backendRefs.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching backendRefs. No match found.");
      } else {
         return this.setNewBackendRefLike(index, this.buildBackendRef(index));
      }
   }

   public GRPCRouteRuleFluent addToFilters(int index, GRPCRouteFilter item) {
      if (this.filters == null) {
         this.filters = new ArrayList();
      }

      GRPCRouteFilterBuilder builder = new GRPCRouteFilterBuilder(item);
      if (index >= 0 && index < this.filters.size()) {
         this._visitables.get("filters").add(index, builder);
         this.filters.add(index, builder);
      } else {
         this._visitables.get("filters").add(builder);
         this.filters.add(builder);
      }

      return this;
   }

   public GRPCRouteRuleFluent setToFilters(int index, GRPCRouteFilter item) {
      if (this.filters == null) {
         this.filters = new ArrayList();
      }

      GRPCRouteFilterBuilder builder = new GRPCRouteFilterBuilder(item);
      if (index >= 0 && index < this.filters.size()) {
         this._visitables.get("filters").set(index, builder);
         this.filters.set(index, builder);
      } else {
         this._visitables.get("filters").add(builder);
         this.filters.add(builder);
      }

      return this;
   }

   public GRPCRouteRuleFluent addToFilters(GRPCRouteFilter... items) {
      if (this.filters == null) {
         this.filters = new ArrayList();
      }

      for(GRPCRouteFilter item : items) {
         GRPCRouteFilterBuilder builder = new GRPCRouteFilterBuilder(item);
         this._visitables.get("filters").add(builder);
         this.filters.add(builder);
      }

      return this;
   }

   public GRPCRouteRuleFluent addAllToFilters(Collection items) {
      if (this.filters == null) {
         this.filters = new ArrayList();
      }

      for(GRPCRouteFilter item : items) {
         GRPCRouteFilterBuilder builder = new GRPCRouteFilterBuilder(item);
         this._visitables.get("filters").add(builder);
         this.filters.add(builder);
      }

      return this;
   }

   public GRPCRouteRuleFluent removeFromFilters(GRPCRouteFilter... items) {
      if (this.filters == null) {
         return this;
      } else {
         for(GRPCRouteFilter item : items) {
            GRPCRouteFilterBuilder builder = new GRPCRouteFilterBuilder(item);
            this._visitables.get("filters").remove(builder);
            this.filters.remove(builder);
         }

         return this;
      }
   }

   public GRPCRouteRuleFluent removeAllFromFilters(Collection items) {
      if (this.filters == null) {
         return this;
      } else {
         for(GRPCRouteFilter item : items) {
            GRPCRouteFilterBuilder builder = new GRPCRouteFilterBuilder(item);
            this._visitables.get("filters").remove(builder);
            this.filters.remove(builder);
         }

         return this;
      }
   }

   public GRPCRouteRuleFluent removeMatchingFromFilters(Predicate predicate) {
      if (this.filters == null) {
         return this;
      } else {
         Iterator<GRPCRouteFilterBuilder> each = this.filters.iterator();
         List visitables = this._visitables.get("filters");

         while(each.hasNext()) {
            GRPCRouteFilterBuilder builder = (GRPCRouteFilterBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildFilters() {
      return this.filters != null ? build(this.filters) : null;
   }

   public GRPCRouteFilter buildFilter(int index) {
      return ((GRPCRouteFilterBuilder)this.filters.get(index)).build();
   }

   public GRPCRouteFilter buildFirstFilter() {
      return ((GRPCRouteFilterBuilder)this.filters.get(0)).build();
   }

   public GRPCRouteFilter buildLastFilter() {
      return ((GRPCRouteFilterBuilder)this.filters.get(this.filters.size() - 1)).build();
   }

   public GRPCRouteFilter buildMatchingFilter(Predicate predicate) {
      for(GRPCRouteFilterBuilder item : this.filters) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingFilter(Predicate predicate) {
      for(GRPCRouteFilterBuilder item : this.filters) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public GRPCRouteRuleFluent withFilters(List filters) {
      if (this.filters != null) {
         this._visitables.get("filters").clear();
      }

      if (filters != null) {
         this.filters = new ArrayList();

         for(GRPCRouteFilter item : filters) {
            this.addToFilters(item);
         }
      } else {
         this.filters = null;
      }

      return this;
   }

   public GRPCRouteRuleFluent withFilters(GRPCRouteFilter... filters) {
      if (this.filters != null) {
         this.filters.clear();
         this._visitables.remove("filters");
      }

      if (filters != null) {
         for(GRPCRouteFilter item : filters) {
            this.addToFilters(item);
         }
      }

      return this;
   }

   public boolean hasFilters() {
      return this.filters != null && !this.filters.isEmpty();
   }

   public FiltersNested addNewFilter() {
      return new FiltersNested(-1, (GRPCRouteFilter)null);
   }

   public FiltersNested addNewFilterLike(GRPCRouteFilter item) {
      return new FiltersNested(-1, item);
   }

   public FiltersNested setNewFilterLike(int index, GRPCRouteFilter item) {
      return new FiltersNested(index, item);
   }

   public FiltersNested editFilter(int index) {
      if (this.filters.size() <= index) {
         throw new RuntimeException("Can't edit filters. Index exceeds size.");
      } else {
         return this.setNewFilterLike(index, this.buildFilter(index));
      }
   }

   public FiltersNested editFirstFilter() {
      if (this.filters.size() == 0) {
         throw new RuntimeException("Can't edit first filters. The list is empty.");
      } else {
         return this.setNewFilterLike(0, this.buildFilter(0));
      }
   }

   public FiltersNested editLastFilter() {
      int index = this.filters.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last filters. The list is empty.");
      } else {
         return this.setNewFilterLike(index, this.buildFilter(index));
      }
   }

   public FiltersNested editMatchingFilter(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.filters.size(); ++i) {
         if (predicate.test((GRPCRouteFilterBuilder)this.filters.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching filters. No match found.");
      } else {
         return this.setNewFilterLike(index, this.buildFilter(index));
      }
   }

   public GRPCRouteRuleFluent addToMatches(int index, GRPCRouteMatch item) {
      if (this.matches == null) {
         this.matches = new ArrayList();
      }

      GRPCRouteMatchBuilder builder = new GRPCRouteMatchBuilder(item);
      if (index >= 0 && index < this.matches.size()) {
         this._visitables.get("matches").add(index, builder);
         this.matches.add(index, builder);
      } else {
         this._visitables.get("matches").add(builder);
         this.matches.add(builder);
      }

      return this;
   }

   public GRPCRouteRuleFluent setToMatches(int index, GRPCRouteMatch item) {
      if (this.matches == null) {
         this.matches = new ArrayList();
      }

      GRPCRouteMatchBuilder builder = new GRPCRouteMatchBuilder(item);
      if (index >= 0 && index < this.matches.size()) {
         this._visitables.get("matches").set(index, builder);
         this.matches.set(index, builder);
      } else {
         this._visitables.get("matches").add(builder);
         this.matches.add(builder);
      }

      return this;
   }

   public GRPCRouteRuleFluent addToMatches(GRPCRouteMatch... items) {
      if (this.matches == null) {
         this.matches = new ArrayList();
      }

      for(GRPCRouteMatch item : items) {
         GRPCRouteMatchBuilder builder = new GRPCRouteMatchBuilder(item);
         this._visitables.get("matches").add(builder);
         this.matches.add(builder);
      }

      return this;
   }

   public GRPCRouteRuleFluent addAllToMatches(Collection items) {
      if (this.matches == null) {
         this.matches = new ArrayList();
      }

      for(GRPCRouteMatch item : items) {
         GRPCRouteMatchBuilder builder = new GRPCRouteMatchBuilder(item);
         this._visitables.get("matches").add(builder);
         this.matches.add(builder);
      }

      return this;
   }

   public GRPCRouteRuleFluent removeFromMatches(GRPCRouteMatch... items) {
      if (this.matches == null) {
         return this;
      } else {
         for(GRPCRouteMatch item : items) {
            GRPCRouteMatchBuilder builder = new GRPCRouteMatchBuilder(item);
            this._visitables.get("matches").remove(builder);
            this.matches.remove(builder);
         }

         return this;
      }
   }

   public GRPCRouteRuleFluent removeAllFromMatches(Collection items) {
      if (this.matches == null) {
         return this;
      } else {
         for(GRPCRouteMatch item : items) {
            GRPCRouteMatchBuilder builder = new GRPCRouteMatchBuilder(item);
            this._visitables.get("matches").remove(builder);
            this.matches.remove(builder);
         }

         return this;
      }
   }

   public GRPCRouteRuleFluent removeMatchingFromMatches(Predicate predicate) {
      if (this.matches == null) {
         return this;
      } else {
         Iterator<GRPCRouteMatchBuilder> each = this.matches.iterator();
         List visitables = this._visitables.get("matches");

         while(each.hasNext()) {
            GRPCRouteMatchBuilder builder = (GRPCRouteMatchBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildMatches() {
      return this.matches != null ? build(this.matches) : null;
   }

   public GRPCRouteMatch buildMatch(int index) {
      return ((GRPCRouteMatchBuilder)this.matches.get(index)).build();
   }

   public GRPCRouteMatch buildFirstMatch() {
      return ((GRPCRouteMatchBuilder)this.matches.get(0)).build();
   }

   public GRPCRouteMatch buildLastMatch() {
      return ((GRPCRouteMatchBuilder)this.matches.get(this.matches.size() - 1)).build();
   }

   public GRPCRouteMatch buildMatchingMatch(Predicate predicate) {
      for(GRPCRouteMatchBuilder item : this.matches) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingMatch(Predicate predicate) {
      for(GRPCRouteMatchBuilder item : this.matches) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public GRPCRouteRuleFluent withMatches(List matches) {
      if (this.matches != null) {
         this._visitables.get("matches").clear();
      }

      if (matches != null) {
         this.matches = new ArrayList();

         for(GRPCRouteMatch item : matches) {
            this.addToMatches(item);
         }
      } else {
         this.matches = null;
      }

      return this;
   }

   public GRPCRouteRuleFluent withMatches(GRPCRouteMatch... matches) {
      if (this.matches != null) {
         this.matches.clear();
         this._visitables.remove("matches");
      }

      if (matches != null) {
         for(GRPCRouteMatch item : matches) {
            this.addToMatches(item);
         }
      }

      return this;
   }

   public boolean hasMatches() {
      return this.matches != null && !this.matches.isEmpty();
   }

   public MatchesNested addNewMatch() {
      return new MatchesNested(-1, (GRPCRouteMatch)null);
   }

   public MatchesNested addNewMatchLike(GRPCRouteMatch item) {
      return new MatchesNested(-1, item);
   }

   public MatchesNested setNewMatchLike(int index, GRPCRouteMatch item) {
      return new MatchesNested(index, item);
   }

   public MatchesNested editMatch(int index) {
      if (this.matches.size() <= index) {
         throw new RuntimeException("Can't edit matches. Index exceeds size.");
      } else {
         return this.setNewMatchLike(index, this.buildMatch(index));
      }
   }

   public MatchesNested editFirstMatch() {
      if (this.matches.size() == 0) {
         throw new RuntimeException("Can't edit first matches. The list is empty.");
      } else {
         return this.setNewMatchLike(0, this.buildMatch(0));
      }
   }

   public MatchesNested editLastMatch() {
      int index = this.matches.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last matches. The list is empty.");
      } else {
         return this.setNewMatchLike(index, this.buildMatch(index));
      }
   }

   public MatchesNested editMatchingMatch(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.matches.size(); ++i) {
         if (predicate.test((GRPCRouteMatchBuilder)this.matches.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching matches. No match found.");
      } else {
         return this.setNewMatchLike(index, this.buildMatch(index));
      }
   }

   public String getName() {
      return this.name;
   }

   public GRPCRouteRuleFluent withName(String name) {
      this.name = name;
      return this;
   }

   public boolean hasName() {
      return this.name != null;
   }

   public SessionPersistence buildSessionPersistence() {
      return this.sessionPersistence != null ? this.sessionPersistence.build() : null;
   }

   public GRPCRouteRuleFluent withSessionPersistence(SessionPersistence sessionPersistence) {
      this._visitables.remove("sessionPersistence");
      if (sessionPersistence != null) {
         this.sessionPersistence = new SessionPersistenceBuilder(sessionPersistence);
         this._visitables.get("sessionPersistence").add(this.sessionPersistence);
      } else {
         this.sessionPersistence = null;
         this._visitables.get("sessionPersistence").remove(this.sessionPersistence);
      }

      return this;
   }

   public boolean hasSessionPersistence() {
      return this.sessionPersistence != null;
   }

   public SessionPersistenceNested withNewSessionPersistence() {
      return new SessionPersistenceNested((SessionPersistence)null);
   }

   public SessionPersistenceNested withNewSessionPersistenceLike(SessionPersistence item) {
      return new SessionPersistenceNested(item);
   }

   public SessionPersistenceNested editSessionPersistence() {
      return this.withNewSessionPersistenceLike((SessionPersistence)Optional.ofNullable(this.buildSessionPersistence()).orElse((Object)null));
   }

   public SessionPersistenceNested editOrNewSessionPersistence() {
      return this.withNewSessionPersistenceLike((SessionPersistence)Optional.ofNullable(this.buildSessionPersistence()).orElse((new SessionPersistenceBuilder()).build()));
   }

   public SessionPersistenceNested editOrNewSessionPersistenceLike(SessionPersistence item) {
      return this.withNewSessionPersistenceLike((SessionPersistence)Optional.ofNullable(this.buildSessionPersistence()).orElse(item));
   }

   public GRPCRouteRuleFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public GRPCRouteRuleFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public GRPCRouteRuleFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public GRPCRouteRuleFluent removeFromAdditionalProperties(Map map) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (map != null) {
            for(Object key : map.keySet()) {
               if (this.additionalProperties != null) {
                  this.additionalProperties.remove(key);
               }
            }
         }

         return this;
      }
   }

   public Map getAdditionalProperties() {
      return this.additionalProperties;
   }

   public GRPCRouteRuleFluent withAdditionalProperties(Map additionalProperties) {
      if (additionalProperties == null) {
         this.additionalProperties = null;
      } else {
         this.additionalProperties = new LinkedHashMap(additionalProperties);
      }

      return this;
   }

   public boolean hasAdditionalProperties() {
      return this.additionalProperties != null;
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         if (!super.equals(o)) {
            return false;
         } else {
            GRPCRouteRuleFluent that = (GRPCRouteRuleFluent)o;
            if (!Objects.equals(this.backendRefs, that.backendRefs)) {
               return false;
            } else if (!Objects.equals(this.filters, that.filters)) {
               return false;
            } else if (!Objects.equals(this.matches, that.matches)) {
               return false;
            } else if (!Objects.equals(this.name, that.name)) {
               return false;
            } else if (!Objects.equals(this.sessionPersistence, that.sessionPersistence)) {
               return false;
            } else {
               return Objects.equals(this.additionalProperties, that.additionalProperties);
            }
         }
      } else {
         return false;
      }
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.backendRefs, this.filters, this.matches, this.name, this.sessionPersistence, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.backendRefs != null && !this.backendRefs.isEmpty()) {
         sb.append("backendRefs:");
         sb.append(this.backendRefs + ",");
      }

      if (this.filters != null && !this.filters.isEmpty()) {
         sb.append("filters:");
         sb.append(this.filters + ",");
      }

      if (this.matches != null && !this.matches.isEmpty()) {
         sb.append("matches:");
         sb.append(this.matches + ",");
      }

      if (this.name != null) {
         sb.append("name:");
         sb.append(this.name + ",");
      }

      if (this.sessionPersistence != null) {
         sb.append("sessionPersistence:");
         sb.append(this.sessionPersistence + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class BackendRefsNested extends GRPCBackendRefFluent implements Nested {
      GRPCBackendRefBuilder builder;
      int index;

      BackendRefsNested(int index, GRPCBackendRef item) {
         this.index = index;
         this.builder = new GRPCBackendRefBuilder(this, item);
      }

      public Object and() {
         return GRPCRouteRuleFluent.this.setToBackendRefs(this.index, this.builder.build());
      }

      public Object endBackendRef() {
         return this.and();
      }
   }

   public class FiltersNested extends GRPCRouteFilterFluent implements Nested {
      GRPCRouteFilterBuilder builder;
      int index;

      FiltersNested(int index, GRPCRouteFilter item) {
         this.index = index;
         this.builder = new GRPCRouteFilterBuilder(this, item);
      }

      public Object and() {
         return GRPCRouteRuleFluent.this.setToFilters(this.index, this.builder.build());
      }

      public Object endFilter() {
         return this.and();
      }
   }

   public class MatchesNested extends GRPCRouteMatchFluent implements Nested {
      GRPCRouteMatchBuilder builder;
      int index;

      MatchesNested(int index, GRPCRouteMatch item) {
         this.index = index;
         this.builder = new GRPCRouteMatchBuilder(this, item);
      }

      public Object and() {
         return GRPCRouteRuleFluent.this.setToMatches(this.index, this.builder.build());
      }

      public Object endMatch() {
         return this.and();
      }
   }

   public class SessionPersistenceNested extends SessionPersistenceFluent implements Nested {
      SessionPersistenceBuilder builder;

      SessionPersistenceNested(SessionPersistence item) {
         this.builder = new SessionPersistenceBuilder(this, item);
      }

      public Object and() {
         return GRPCRouteRuleFluent.this.withSessionPersistence(this.builder.build());
      }

      public Object endSessionPersistence() {
         return this.and();
      }
   }
}
