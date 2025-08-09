package io.netty.handler.codec.http;

import io.netty.util.internal.ObjectUtil;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

/** @deprecated */
@Deprecated
public class DefaultCookie extends io.netty.handler.codec.http.cookie.DefaultCookie implements Cookie {
   private String comment;
   private String commentUrl;
   private boolean discard;
   private Set ports = Collections.emptySet();
   private Set unmodifiablePorts;
   private int version;

   public DefaultCookie(String name, String value) {
      super(name, value);
      this.unmodifiablePorts = this.ports;
   }

   /** @deprecated */
   @Deprecated
   public String getName() {
      return this.name();
   }

   /** @deprecated */
   @Deprecated
   public String getValue() {
      return this.value();
   }

   /** @deprecated */
   @Deprecated
   public String getDomain() {
      return this.domain();
   }

   /** @deprecated */
   @Deprecated
   public String getPath() {
      return this.path();
   }

   /** @deprecated */
   @Deprecated
   public String getComment() {
      return this.comment();
   }

   /** @deprecated */
   @Deprecated
   public String comment() {
      return this.comment;
   }

   /** @deprecated */
   @Deprecated
   public void setComment(String comment) {
      this.comment = this.validateValue("comment", comment);
   }

   /** @deprecated */
   @Deprecated
   public String getCommentUrl() {
      return this.commentUrl();
   }

   /** @deprecated */
   @Deprecated
   public String commentUrl() {
      return this.commentUrl;
   }

   /** @deprecated */
   @Deprecated
   public void setCommentUrl(String commentUrl) {
      this.commentUrl = this.validateValue("commentUrl", commentUrl);
   }

   /** @deprecated */
   @Deprecated
   public boolean isDiscard() {
      return this.discard;
   }

   /** @deprecated */
   @Deprecated
   public void setDiscard(boolean discard) {
      this.discard = discard;
   }

   /** @deprecated */
   @Deprecated
   public Set getPorts() {
      return this.ports();
   }

   /** @deprecated */
   @Deprecated
   public Set ports() {
      if (this.unmodifiablePorts == null) {
         this.unmodifiablePorts = Collections.unmodifiableSet(this.ports);
      }

      return this.unmodifiablePorts;
   }

   /** @deprecated */
   @Deprecated
   public void setPorts(int... ports) {
      ObjectUtil.checkNotNull(ports, "ports");
      int[] portsCopy = (int[])(([I)ports).clone();
      if (portsCopy.length == 0) {
         this.unmodifiablePorts = this.ports = Collections.emptySet();
      } else {
         Set<Integer> newPorts = new TreeSet();

         for(int p : portsCopy) {
            if (p <= 0 || p > 65535) {
               throw new IllegalArgumentException("port out of range: " + p);
            }

            newPorts.add(p);
         }

         this.ports = newPorts;
         this.unmodifiablePorts = null;
      }

   }

   /** @deprecated */
   @Deprecated
   public void setPorts(Iterable ports) {
      Set<Integer> newPorts = new TreeSet();

      for(int p : ports) {
         if (p <= 0 || p > 65535) {
            throw new IllegalArgumentException("port out of range: " + p);
         }

         newPorts.add(p);
      }

      if (newPorts.isEmpty()) {
         this.unmodifiablePorts = this.ports = Collections.emptySet();
      } else {
         this.ports = newPorts;
         this.unmodifiablePorts = null;
      }

   }

   /** @deprecated */
   @Deprecated
   public long getMaxAge() {
      return this.maxAge();
   }

   /** @deprecated */
   @Deprecated
   public int getVersion() {
      return this.version();
   }

   /** @deprecated */
   @Deprecated
   public int version() {
      return this.version;
   }

   /** @deprecated */
   @Deprecated
   public void setVersion(int version) {
      this.version = version;
   }
}
