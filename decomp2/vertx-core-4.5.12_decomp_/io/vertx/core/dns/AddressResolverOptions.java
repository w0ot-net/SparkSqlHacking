package io.vertx.core.dns;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.codegen.json.annotations.JsonGen;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.impl.AddressResolver;
import io.vertx.core.json.JsonObject;
import java.util.ArrayList;
import java.util.List;

@DataObject
@JsonGen(
   publicConverter = false
)
public class AddressResolverOptions {
   public static final List DEFAULT_SERVERS = null;
   public static final boolean DEFAULT_OPT_RESOURCE_ENABLED = false;
   public static final int DEFAULT_CACHE_MIN_TIME_TO_LIVE = 0;
   public static final int DEFAULT_CACHE_MAX_TIME_TO_LIVE = Integer.MAX_VALUE;
   public static final int DEFAULT_CACHE_NEGATIVE_TIME_TO_LIVE = 0;
   public static final int DEFAULT_QUERY_TIMEOUT = 5000;
   public static final int DEFAULT_HOSTS_REFRESH_PERIOD = 0;
   public static final int DEFAULT_MAX_QUERIES = 4;
   public static final boolean DEFAULT_RD_FLAG = true;
   /** @deprecated */
   @Deprecated
   public static final List DEFAULT_SEACH_DOMAINS = null;
   public static final List DEFAULT_SEARCH_DOMAINS = null;
   public static final int DEFAULT_NDOTS;
   public static final boolean DEFAULT_ROTATE_SERVERS;
   public static final boolean DEFAULT_ROUND_ROBIN_INET_ADDRESS = false;
   private String hostsPath;
   private Buffer hostsValue;
   private int hostsRefreshPeriod;
   private List servers;
   private boolean optResourceEnabled;
   private int cacheMinTimeToLive;
   private int cacheMaxTimeToLive;
   private int cacheNegativeTimeToLive;
   private long queryTimeout;
   private int maxQueries;
   private boolean rdFlag;
   private List searchDomains;
   private int ndots;
   private boolean rotateServers;
   private boolean roundRobinInetAddress;

   public AddressResolverOptions() {
      this.servers = DEFAULT_SERVERS;
      this.optResourceEnabled = false;
      this.cacheMinTimeToLive = 0;
      this.cacheMaxTimeToLive = Integer.MAX_VALUE;
      this.cacheNegativeTimeToLive = 0;
      this.queryTimeout = 5000L;
      this.maxQueries = 4;
      this.rdFlag = true;
      this.searchDomains = DEFAULT_SEARCH_DOMAINS;
      this.ndots = DEFAULT_NDOTS;
      this.rotateServers = DEFAULT_ROTATE_SERVERS;
      this.roundRobinInetAddress = false;
      this.hostsRefreshPeriod = 0;
   }

   public AddressResolverOptions(AddressResolverOptions other) {
      this.hostsPath = other.hostsPath;
      this.hostsValue = other.hostsValue != null ? other.hostsValue.copy() : null;
      this.hostsRefreshPeriod = other.hostsRefreshPeriod;
      this.servers = other.servers != null ? new ArrayList(other.servers) : null;
      this.optResourceEnabled = other.optResourceEnabled;
      this.cacheMinTimeToLive = other.cacheMinTimeToLive;
      this.cacheMaxTimeToLive = other.cacheMaxTimeToLive;
      this.cacheNegativeTimeToLive = other.cacheNegativeTimeToLive;
      this.queryTimeout = other.queryTimeout;
      this.maxQueries = other.maxQueries;
      this.rdFlag = other.rdFlag;
      this.searchDomains = other.searchDomains != null ? new ArrayList(other.searchDomains) : null;
      this.ndots = other.ndots;
      this.rotateServers = other.rotateServers;
      this.roundRobinInetAddress = other.roundRobinInetAddress;
   }

   public AddressResolverOptions(JsonObject json) {
      this();
      AddressResolverOptionsConverter.fromJson(json, this);
   }

   public String getHostsPath() {
      return this.hostsPath;
   }

   public AddressResolverOptions setHostsPath(String hostsPath) {
      this.hostsPath = hostsPath;
      return this;
   }

   public Buffer getHostsValue() {
      return this.hostsValue;
   }

   public AddressResolverOptions setHostsValue(Buffer hostsValue) {
      this.hostsValue = hostsValue;
      return this;
   }

   public int getHostsRefreshPeriod() {
      return this.hostsRefreshPeriod;
   }

   public AddressResolverOptions setHostsRefreshPeriod(int hostsRefreshPeriod) {
      if (hostsRefreshPeriod < 0) {
         throw new IllegalArgumentException("hostsRefreshPeriod must be >= 0");
      } else {
         this.hostsRefreshPeriod = hostsRefreshPeriod;
         return this;
      }
   }

   public List getServers() {
      return this.servers;
   }

   public AddressResolverOptions setServers(List servers) {
      this.servers = servers;
      return this;
   }

   public AddressResolverOptions addServer(String server) {
      if (this.servers == null) {
         this.servers = new ArrayList();
      }

      this.servers.add(server);
      return this;
   }

   public boolean isOptResourceEnabled() {
      return this.optResourceEnabled;
   }

   public AddressResolverOptions setOptResourceEnabled(boolean optResourceEnabled) {
      this.optResourceEnabled = optResourceEnabled;
      return this;
   }

   public int getCacheMinTimeToLive() {
      return this.cacheMinTimeToLive;
   }

   public AddressResolverOptions setCacheMinTimeToLive(int cacheMinTimeToLive) {
      if (cacheMinTimeToLive < 0) {
         throw new IllegalArgumentException("cacheMinTimeToLive must be >= 0");
      } else {
         this.cacheMinTimeToLive = cacheMinTimeToLive;
         return this;
      }
   }

   public int getCacheMaxTimeToLive() {
      return this.cacheMaxTimeToLive;
   }

   public AddressResolverOptions setCacheMaxTimeToLive(int cacheMaxTimeToLive) {
      if (cacheMaxTimeToLive < 0) {
         throw new IllegalArgumentException("cacheMaxTimeToLive must be >= 0");
      } else {
         this.cacheMaxTimeToLive = cacheMaxTimeToLive;
         return this;
      }
   }

   public int getCacheNegativeTimeToLive() {
      return this.cacheNegativeTimeToLive;
   }

   public AddressResolverOptions setCacheNegativeTimeToLive(int cacheNegativeTimeToLive) {
      if (cacheNegativeTimeToLive < 0) {
         throw new IllegalArgumentException("cacheNegativeTimeToLive must be >= 0");
      } else {
         this.cacheNegativeTimeToLive = cacheNegativeTimeToLive;
         return this;
      }
   }

   public long getQueryTimeout() {
      return this.queryTimeout;
   }

   public AddressResolverOptions setQueryTimeout(long queryTimeout) {
      if (queryTimeout < 1L) {
         throw new IllegalArgumentException("queryTimeout must be > 0");
      } else {
         this.queryTimeout = queryTimeout;
         return this;
      }
   }

   public int getMaxQueries() {
      return this.maxQueries;
   }

   public AddressResolverOptions setMaxQueries(int maxQueries) {
      if (maxQueries < 1) {
         throw new IllegalArgumentException("maxQueries must be > 0");
      } else {
         this.maxQueries = maxQueries;
         return this;
      }
   }

   public boolean getRdFlag() {
      return this.rdFlag;
   }

   public AddressResolverOptions setRdFlag(boolean rdFlag) {
      this.rdFlag = rdFlag;
      return this;
   }

   public List getSearchDomains() {
      return this.searchDomains;
   }

   public AddressResolverOptions setSearchDomains(List searchDomains) {
      this.searchDomains = searchDomains;
      return this;
   }

   public AddressResolverOptions addSearchDomain(String searchDomain) {
      if (this.searchDomains == null) {
         this.searchDomains = new ArrayList();
      }

      this.searchDomains.add(searchDomain);
      return this;
   }

   public int getNdots() {
      return this.ndots;
   }

   public AddressResolverOptions setNdots(int ndots) {
      if (ndots < -1) {
         throw new IllegalArgumentException("ndots must be >= -1");
      } else {
         this.ndots = ndots;
         return this;
      }
   }

   public boolean isRotateServers() {
      return this.rotateServers;
   }

   public AddressResolverOptions setRotateServers(boolean rotateServers) {
      this.rotateServers = rotateServers;
      return this;
   }

   public boolean isRoundRobinInetAddress() {
      return this.roundRobinInetAddress;
   }

   public AddressResolverOptions setRoundRobinInetAddress(boolean roundRobinInetAddress) {
      this.roundRobinInetAddress = roundRobinInetAddress;
      return this;
   }

   public JsonObject toJson() {
      JsonObject json = new JsonObject();
      AddressResolverOptionsConverter.toJson(this, json);
      return json;
   }

   static {
      DEFAULT_NDOTS = AddressResolver.DEFAULT_NDOTS_RESOLV_OPTION;
      DEFAULT_ROTATE_SERVERS = AddressResolver.DEFAULT_ROTATE_RESOLV_OPTION;
   }
}
