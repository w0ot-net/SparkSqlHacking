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

public class HTTPRouteRuleFluent extends BaseFluent {
   private ArrayList backendRefs = new ArrayList();
   private ArrayList filters = new ArrayList();
   private ArrayList matches = new ArrayList();
   private String name;
   private HTTPRouteRetryBuilder retry;
   private SessionPersistenceBuilder sessionPersistence;
   private HTTPRouteTimeoutsBuilder timeouts;
   private Map additionalProperties;

   public HTTPRouteRuleFluent() {
   }

   public HTTPRouteRuleFluent(HTTPRouteRule instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(HTTPRouteRule instance) {
      instance = instance != null ? instance : new HTTPRouteRule();
      if (instance != null) {
         this.withBackendRefs(instance.getBackendRefs());
         this.withFilters(instance.getFilters());
         this.withMatches(instance.getMatches());
         this.withName(instance.getName());
         this.withRetry(instance.getRetry());
         this.withSessionPersistence(instance.getSessionPersistence());
         this.withTimeouts(instance.getTimeouts());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public HTTPRouteRuleFluent addToBackendRefs(int index, HTTPBackendRef item) {
      if (this.backendRefs == null) {
         this.backendRefs = new ArrayList();
      }

      HTTPBackendRefBuilder builder = new HTTPBackendRefBuilder(item);
      if (index >= 0 && index < this.backendRefs.size()) {
         this._visitables.get("backendRefs").add(index, builder);
         this.backendRefs.add(index, builder);
      } else {
         this._visitables.get("backendRefs").add(builder);
         this.backendRefs.add(builder);
      }

      return this;
   }

   public HTTPRouteRuleFluent setToBackendRefs(int index, HTTPBackendRef item) {
      if (this.backendRefs == null) {
         this.backendRefs = new ArrayList();
      }

      HTTPBackendRefBuilder builder = new HTTPBackendRefBuilder(item);
      if (index >= 0 && index < this.backendRefs.size()) {
         this._visitables.get("backendRefs").set(index, builder);
         this.backendRefs.set(index, builder);
      } else {
         this._visitables.get("backendRefs").add(builder);
         this.backendRefs.add(builder);
      }

      return this;
   }

   public HTTPRouteRuleFluent addToBackendRefs(HTTPBackendRef... items) {
      if (this.backendRefs == null) {
         this.backendRefs = new ArrayList();
      }

      for(HTTPBackendRef item : items) {
         HTTPBackendRefBuilder builder = new HTTPBackendRefBuilder(item);
         this._visitables.get("backendRefs").add(builder);
         this.backendRefs.add(builder);
      }

      return this;
   }

   public HTTPRouteRuleFluent addAllToBackendRefs(Collection items) {
      if (this.backendRefs == null) {
         this.backendRefs = new ArrayList();
      }

      for(HTTPBackendRef item : items) {
         HTTPBackendRefBuilder builder = new HTTPBackendRefBuilder(item);
         this._visitables.get("backendRefs").add(builder);
         this.backendRefs.add(builder);
      }

      return this;
   }

   public HTTPRouteRuleFluent removeFromBackendRefs(HTTPBackendRef... items) {
      if (this.backendRefs == null) {
         return this;
      } else {
         for(HTTPBackendRef item : items) {
            HTTPBackendRefBuilder builder = new HTTPBackendRefBuilder(item);
            this._visitables.get("backendRefs").remove(builder);
            this.backendRefs.remove(builder);
         }

         return this;
      }
   }

   public HTTPRouteRuleFluent removeAllFromBackendRefs(Collection items) {
      if (this.backendRefs == null) {
         return this;
      } else {
         for(HTTPBackendRef item : items) {
            HTTPBackendRefBuilder builder = new HTTPBackendRefBuilder(item);
            this._visitables.get("backendRefs").remove(builder);
            this.backendRefs.remove(builder);
         }

         return this;
      }
   }

   public HTTPRouteRuleFluent removeMatchingFromBackendRefs(Predicate predicate) {
      if (this.backendRefs == null) {
         return this;
      } else {
         Iterator<HTTPBackendRefBuilder> each = this.backendRefs.iterator();
         List visitables = this._visitables.get("backendRefs");

         while(each.hasNext()) {
            HTTPBackendRefBuilder builder = (HTTPBackendRefBuilder)each.next();
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

   public HTTPBackendRef buildBackendRef(int index) {
      return ((HTTPBackendRefBuilder)this.backendRefs.get(index)).build();
   }

   public HTTPBackendRef buildFirstBackendRef() {
      return ((HTTPBackendRefBuilder)this.backendRefs.get(0)).build();
   }

   public HTTPBackendRef buildLastBackendRef() {
      return ((HTTPBackendRefBuilder)this.backendRefs.get(this.backendRefs.size() - 1)).build();
   }

   public HTTPBackendRef buildMatchingBackendRef(Predicate predicate) {
      for(HTTPBackendRefBuilder item : this.backendRefs) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingBackendRef(Predicate predicate) {
      for(HTTPBackendRefBuilder item : this.backendRefs) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public HTTPRouteRuleFluent withBackendRefs(List backendRefs) {
      if (this.backendRefs != null) {
         this._visitables.get("backendRefs").clear();
      }

      if (backendRefs != null) {
         this.backendRefs = new ArrayList();

         for(HTTPBackendRef item : backendRefs) {
            this.addToBackendRefs(item);
         }
      } else {
         this.backendRefs = null;
      }

      return this;
   }

   public HTTPRouteRuleFluent withBackendRefs(HTTPBackendRef... backendRefs) {
      if (this.backendRefs != null) {
         this.backendRefs.clear();
         this._visitables.remove("backendRefs");
      }

      if (backendRefs != null) {
         for(HTTPBackendRef item : backendRefs) {
            this.addToBackendRefs(item);
         }
      }

      return this;
   }

   public boolean hasBackendRefs() {
      return this.backendRefs != null && !this.backendRefs.isEmpty();
   }

   public BackendRefsNested addNewBackendRef() {
      return new BackendRefsNested(-1, (HTTPBackendRef)null);
   }

   public BackendRefsNested addNewBackendRefLike(HTTPBackendRef item) {
      return new BackendRefsNested(-1, item);
   }

   public BackendRefsNested setNewBackendRefLike(int index, HTTPBackendRef item) {
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
         if (predicate.test((HTTPBackendRefBuilder)this.backendRefs.get(i))) {
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

   public HTTPRouteRuleFluent addToFilters(int index, HTTPRouteFilter item) {
      if (this.filters == null) {
         this.filters = new ArrayList();
      }

      HTTPRouteFilterBuilder builder = new HTTPRouteFilterBuilder(item);
      if (index >= 0 && index < this.filters.size()) {
         this._visitables.get("filters").add(index, builder);
         this.filters.add(index, builder);
      } else {
         this._visitables.get("filters").add(builder);
         this.filters.add(builder);
      }

      return this;
   }

   public HTTPRouteRuleFluent setToFilters(int index, HTTPRouteFilter item) {
      if (this.filters == null) {
         this.filters = new ArrayList();
      }

      HTTPRouteFilterBuilder builder = new HTTPRouteFilterBuilder(item);
      if (index >= 0 && index < this.filters.size()) {
         this._visitables.get("filters").set(index, builder);
         this.filters.set(index, builder);
      } else {
         this._visitables.get("filters").add(builder);
         this.filters.add(builder);
      }

      return this;
   }

   public HTTPRouteRuleFluent addToFilters(HTTPRouteFilter... items) {
      if (this.filters == null) {
         this.filters = new ArrayList();
      }

      for(HTTPRouteFilter item : items) {
         HTTPRouteFilterBuilder builder = new HTTPRouteFilterBuilder(item);
         this._visitables.get("filters").add(builder);
         this.filters.add(builder);
      }

      return this;
   }

   public HTTPRouteRuleFluent addAllToFilters(Collection items) {
      if (this.filters == null) {
         this.filters = new ArrayList();
      }

      for(HTTPRouteFilter item : items) {
         HTTPRouteFilterBuilder builder = new HTTPRouteFilterBuilder(item);
         this._visitables.get("filters").add(builder);
         this.filters.add(builder);
      }

      return this;
   }

   public HTTPRouteRuleFluent removeFromFilters(HTTPRouteFilter... items) {
      if (this.filters == null) {
         return this;
      } else {
         for(HTTPRouteFilter item : items) {
            HTTPRouteFilterBuilder builder = new HTTPRouteFilterBuilder(item);
            this._visitables.get("filters").remove(builder);
            this.filters.remove(builder);
         }

         return this;
      }
   }

   public HTTPRouteRuleFluent removeAllFromFilters(Collection items) {
      if (this.filters == null) {
         return this;
      } else {
         for(HTTPRouteFilter item : items) {
            HTTPRouteFilterBuilder builder = new HTTPRouteFilterBuilder(item);
            this._visitables.get("filters").remove(builder);
            this.filters.remove(builder);
         }

         return this;
      }
   }

   public HTTPRouteRuleFluent removeMatchingFromFilters(Predicate predicate) {
      if (this.filters == null) {
         return this;
      } else {
         Iterator<HTTPRouteFilterBuilder> each = this.filters.iterator();
         List visitables = this._visitables.get("filters");

         while(each.hasNext()) {
            HTTPRouteFilterBuilder builder = (HTTPRouteFilterBuilder)each.next();
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

   public HTTPRouteFilter buildFilter(int index) {
      return ((HTTPRouteFilterBuilder)this.filters.get(index)).build();
   }

   public HTTPRouteFilter buildFirstFilter() {
      return ((HTTPRouteFilterBuilder)this.filters.get(0)).build();
   }

   public HTTPRouteFilter buildLastFilter() {
      return ((HTTPRouteFilterBuilder)this.filters.get(this.filters.size() - 1)).build();
   }

   public HTTPRouteFilter buildMatchingFilter(Predicate predicate) {
      for(HTTPRouteFilterBuilder item : this.filters) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingFilter(Predicate predicate) {
      for(HTTPRouteFilterBuilder item : this.filters) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public HTTPRouteRuleFluent withFilters(List filters) {
      if (this.filters != null) {
         this._visitables.get("filters").clear();
      }

      if (filters != null) {
         this.filters = new ArrayList();

         for(HTTPRouteFilter item : filters) {
            this.addToFilters(item);
         }
      } else {
         this.filters = null;
      }

      return this;
   }

   public HTTPRouteRuleFluent withFilters(HTTPRouteFilter... filters) {
      if (this.filters != null) {
         this.filters.clear();
         this._visitables.remove("filters");
      }

      if (filters != null) {
         for(HTTPRouteFilter item : filters) {
            this.addToFilters(item);
         }
      }

      return this;
   }

   public boolean hasFilters() {
      return this.filters != null && !this.filters.isEmpty();
   }

   public FiltersNested addNewFilter() {
      return new FiltersNested(-1, (HTTPRouteFilter)null);
   }

   public FiltersNested addNewFilterLike(HTTPRouteFilter item) {
      return new FiltersNested(-1, item);
   }

   public FiltersNested setNewFilterLike(int index, HTTPRouteFilter item) {
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
         if (predicate.test((HTTPRouteFilterBuilder)this.filters.get(i))) {
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

   public HTTPRouteRuleFluent addToMatches(int index, HTTPRouteMatch item) {
      if (this.matches == null) {
         this.matches = new ArrayList();
      }

      HTTPRouteMatchBuilder builder = new HTTPRouteMatchBuilder(item);
      if (index >= 0 && index < this.matches.size()) {
         this._visitables.get("matches").add(index, builder);
         this.matches.add(index, builder);
      } else {
         this._visitables.get("matches").add(builder);
         this.matches.add(builder);
      }

      return this;
   }

   public HTTPRouteRuleFluent setToMatches(int index, HTTPRouteMatch item) {
      if (this.matches == null) {
         this.matches = new ArrayList();
      }

      HTTPRouteMatchBuilder builder = new HTTPRouteMatchBuilder(item);
      if (index >= 0 && index < this.matches.size()) {
         this._visitables.get("matches").set(index, builder);
         this.matches.set(index, builder);
      } else {
         this._visitables.get("matches").add(builder);
         this.matches.add(builder);
      }

      return this;
   }

   public HTTPRouteRuleFluent addToMatches(HTTPRouteMatch... items) {
      if (this.matches == null) {
         this.matches = new ArrayList();
      }

      for(HTTPRouteMatch item : items) {
         HTTPRouteMatchBuilder builder = new HTTPRouteMatchBuilder(item);
         this._visitables.get("matches").add(builder);
         this.matches.add(builder);
      }

      return this;
   }

   public HTTPRouteRuleFluent addAllToMatches(Collection items) {
      if (this.matches == null) {
         this.matches = new ArrayList();
      }

      for(HTTPRouteMatch item : items) {
         HTTPRouteMatchBuilder builder = new HTTPRouteMatchBuilder(item);
         this._visitables.get("matches").add(builder);
         this.matches.add(builder);
      }

      return this;
   }

   public HTTPRouteRuleFluent removeFromMatches(HTTPRouteMatch... items) {
      if (this.matches == null) {
         return this;
      } else {
         for(HTTPRouteMatch item : items) {
            HTTPRouteMatchBuilder builder = new HTTPRouteMatchBuilder(item);
            this._visitables.get("matches").remove(builder);
            this.matches.remove(builder);
         }

         return this;
      }
   }

   public HTTPRouteRuleFluent removeAllFromMatches(Collection items) {
      if (this.matches == null) {
         return this;
      } else {
         for(HTTPRouteMatch item : items) {
            HTTPRouteMatchBuilder builder = new HTTPRouteMatchBuilder(item);
            this._visitables.get("matches").remove(builder);
            this.matches.remove(builder);
         }

         return this;
      }
   }

   public HTTPRouteRuleFluent removeMatchingFromMatches(Predicate predicate) {
      if (this.matches == null) {
         return this;
      } else {
         Iterator<HTTPRouteMatchBuilder> each = this.matches.iterator();
         List visitables = this._visitables.get("matches");

         while(each.hasNext()) {
            HTTPRouteMatchBuilder builder = (HTTPRouteMatchBuilder)each.next();
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

   public HTTPRouteMatch buildMatch(int index) {
      return ((HTTPRouteMatchBuilder)this.matches.get(index)).build();
   }

   public HTTPRouteMatch buildFirstMatch() {
      return ((HTTPRouteMatchBuilder)this.matches.get(0)).build();
   }

   public HTTPRouteMatch buildLastMatch() {
      return ((HTTPRouteMatchBuilder)this.matches.get(this.matches.size() - 1)).build();
   }

   public HTTPRouteMatch buildMatchingMatch(Predicate predicate) {
      for(HTTPRouteMatchBuilder item : this.matches) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingMatch(Predicate predicate) {
      for(HTTPRouteMatchBuilder item : this.matches) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public HTTPRouteRuleFluent withMatches(List matches) {
      if (this.matches != null) {
         this._visitables.get("matches").clear();
      }

      if (matches != null) {
         this.matches = new ArrayList();

         for(HTTPRouteMatch item : matches) {
            this.addToMatches(item);
         }
      } else {
         this.matches = null;
      }

      return this;
   }

   public HTTPRouteRuleFluent withMatches(HTTPRouteMatch... matches) {
      if (this.matches != null) {
         this.matches.clear();
         this._visitables.remove("matches");
      }

      if (matches != null) {
         for(HTTPRouteMatch item : matches) {
            this.addToMatches(item);
         }
      }

      return this;
   }

   public boolean hasMatches() {
      return this.matches != null && !this.matches.isEmpty();
   }

   public MatchesNested addNewMatch() {
      return new MatchesNested(-1, (HTTPRouteMatch)null);
   }

   public MatchesNested addNewMatchLike(HTTPRouteMatch item) {
      return new MatchesNested(-1, item);
   }

   public MatchesNested setNewMatchLike(int index, HTTPRouteMatch item) {
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
         if (predicate.test((HTTPRouteMatchBuilder)this.matches.get(i))) {
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

   public HTTPRouteRuleFluent withName(String name) {
      this.name = name;
      return this;
   }

   public boolean hasName() {
      return this.name != null;
   }

   public HTTPRouteRetry buildRetry() {
      return this.retry != null ? this.retry.build() : null;
   }

   public HTTPRouteRuleFluent withRetry(HTTPRouteRetry retry) {
      this._visitables.remove("retry");
      if (retry != null) {
         this.retry = new HTTPRouteRetryBuilder(retry);
         this._visitables.get("retry").add(this.retry);
      } else {
         this.retry = null;
         this._visitables.get("retry").remove(this.retry);
      }

      return this;
   }

   public boolean hasRetry() {
      return this.retry != null;
   }

   public RetryNested withNewRetry() {
      return new RetryNested((HTTPRouteRetry)null);
   }

   public RetryNested withNewRetryLike(HTTPRouteRetry item) {
      return new RetryNested(item);
   }

   public RetryNested editRetry() {
      return this.withNewRetryLike((HTTPRouteRetry)Optional.ofNullable(this.buildRetry()).orElse((Object)null));
   }

   public RetryNested editOrNewRetry() {
      return this.withNewRetryLike((HTTPRouteRetry)Optional.ofNullable(this.buildRetry()).orElse((new HTTPRouteRetryBuilder()).build()));
   }

   public RetryNested editOrNewRetryLike(HTTPRouteRetry item) {
      return this.withNewRetryLike((HTTPRouteRetry)Optional.ofNullable(this.buildRetry()).orElse(item));
   }

   public SessionPersistence buildSessionPersistence() {
      return this.sessionPersistence != null ? this.sessionPersistence.build() : null;
   }

   public HTTPRouteRuleFluent withSessionPersistence(SessionPersistence sessionPersistence) {
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

   public HTTPRouteTimeouts buildTimeouts() {
      return this.timeouts != null ? this.timeouts.build() : null;
   }

   public HTTPRouteRuleFluent withTimeouts(HTTPRouteTimeouts timeouts) {
      this._visitables.remove("timeouts");
      if (timeouts != null) {
         this.timeouts = new HTTPRouteTimeoutsBuilder(timeouts);
         this._visitables.get("timeouts").add(this.timeouts);
      } else {
         this.timeouts = null;
         this._visitables.get("timeouts").remove(this.timeouts);
      }

      return this;
   }

   public boolean hasTimeouts() {
      return this.timeouts != null;
   }

   public HTTPRouteRuleFluent withNewTimeouts(String backendRequest, String request) {
      return this.withTimeouts(new HTTPRouteTimeouts(backendRequest, request));
   }

   public TimeoutsNested withNewTimeouts() {
      return new TimeoutsNested((HTTPRouteTimeouts)null);
   }

   public TimeoutsNested withNewTimeoutsLike(HTTPRouteTimeouts item) {
      return new TimeoutsNested(item);
   }

   public TimeoutsNested editTimeouts() {
      return this.withNewTimeoutsLike((HTTPRouteTimeouts)Optional.ofNullable(this.buildTimeouts()).orElse((Object)null));
   }

   public TimeoutsNested editOrNewTimeouts() {
      return this.withNewTimeoutsLike((HTTPRouteTimeouts)Optional.ofNullable(this.buildTimeouts()).orElse((new HTTPRouteTimeoutsBuilder()).build()));
   }

   public TimeoutsNested editOrNewTimeoutsLike(HTTPRouteTimeouts item) {
      return this.withNewTimeoutsLike((HTTPRouteTimeouts)Optional.ofNullable(this.buildTimeouts()).orElse(item));
   }

   public HTTPRouteRuleFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public HTTPRouteRuleFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public HTTPRouteRuleFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public HTTPRouteRuleFluent removeFromAdditionalProperties(Map map) {
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

   public HTTPRouteRuleFluent withAdditionalProperties(Map additionalProperties) {
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
            HTTPRouteRuleFluent that = (HTTPRouteRuleFluent)o;
            if (!Objects.equals(this.backendRefs, that.backendRefs)) {
               return false;
            } else if (!Objects.equals(this.filters, that.filters)) {
               return false;
            } else if (!Objects.equals(this.matches, that.matches)) {
               return false;
            } else if (!Objects.equals(this.name, that.name)) {
               return false;
            } else if (!Objects.equals(this.retry, that.retry)) {
               return false;
            } else if (!Objects.equals(this.sessionPersistence, that.sessionPersistence)) {
               return false;
            } else if (!Objects.equals(this.timeouts, that.timeouts)) {
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
      return Objects.hash(new Object[]{this.backendRefs, this.filters, this.matches, this.name, this.retry, this.sessionPersistence, this.timeouts, this.additionalProperties, super.hashCode()});
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

      if (this.retry != null) {
         sb.append("retry:");
         sb.append(this.retry + ",");
      }

      if (this.sessionPersistence != null) {
         sb.append("sessionPersistence:");
         sb.append(this.sessionPersistence + ",");
      }

      if (this.timeouts != null) {
         sb.append("timeouts:");
         sb.append(this.timeouts + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class BackendRefsNested extends HTTPBackendRefFluent implements Nested {
      HTTPBackendRefBuilder builder;
      int index;

      BackendRefsNested(int index, HTTPBackendRef item) {
         this.index = index;
         this.builder = new HTTPBackendRefBuilder(this, item);
      }

      public Object and() {
         return HTTPRouteRuleFluent.this.setToBackendRefs(this.index, this.builder.build());
      }

      public Object endBackendRef() {
         return this.and();
      }
   }

   public class FiltersNested extends HTTPRouteFilterFluent implements Nested {
      HTTPRouteFilterBuilder builder;
      int index;

      FiltersNested(int index, HTTPRouteFilter item) {
         this.index = index;
         this.builder = new HTTPRouteFilterBuilder(this, item);
      }

      public Object and() {
         return HTTPRouteRuleFluent.this.setToFilters(this.index, this.builder.build());
      }

      public Object endFilter() {
         return this.and();
      }
   }

   public class MatchesNested extends HTTPRouteMatchFluent implements Nested {
      HTTPRouteMatchBuilder builder;
      int index;

      MatchesNested(int index, HTTPRouteMatch item) {
         this.index = index;
         this.builder = new HTTPRouteMatchBuilder(this, item);
      }

      public Object and() {
         return HTTPRouteRuleFluent.this.setToMatches(this.index, this.builder.build());
      }

      public Object endMatch() {
         return this.and();
      }
   }

   public class RetryNested extends HTTPRouteRetryFluent implements Nested {
      HTTPRouteRetryBuilder builder;

      RetryNested(HTTPRouteRetry item) {
         this.builder = new HTTPRouteRetryBuilder(this, item);
      }

      public Object and() {
         return HTTPRouteRuleFluent.this.withRetry(this.builder.build());
      }

      public Object endRetry() {
         return this.and();
      }
   }

   public class SessionPersistenceNested extends SessionPersistenceFluent implements Nested {
      SessionPersistenceBuilder builder;

      SessionPersistenceNested(SessionPersistence item) {
         this.builder = new SessionPersistenceBuilder(this, item);
      }

      public Object and() {
         return HTTPRouteRuleFluent.this.withSessionPersistence(this.builder.build());
      }

      public Object endSessionPersistence() {
         return this.and();
      }
   }

   public class TimeoutsNested extends HTTPRouteTimeoutsFluent implements Nested {
      HTTPRouteTimeoutsBuilder builder;

      TimeoutsNested(HTTPRouteTimeouts item) {
         this.builder = new HTTPRouteTimeoutsBuilder(this, item);
      }

      public Object and() {
         return HTTPRouteRuleFluent.this.withTimeouts(this.builder.build());
      }

      public Object endTimeouts() {
         return this.and();
      }
   }
}
