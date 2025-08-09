package io.jsonwebtoken.impl;

import io.jsonwebtoken.ClaimsMutator;
import io.jsonwebtoken.lang.Assert;
import java.util.Collection;

public class DelegateAudienceCollection implements ClaimsMutator.AudienceCollection {
   private final ClaimsMutator.AudienceCollection delegate;
   private final Object parent;

   public DelegateAudienceCollection(Object parent, ClaimsMutator.AudienceCollection delegate) {
      this.parent = Assert.notNull(parent, "Parent cannot be null.");
      this.delegate = (ClaimsMutator.AudienceCollection)Assert.notNull(delegate, "Delegate cannot be null.");
   }

   public Object single(String aud) {
      this.delegate.single(aud);
      return this.parent;
   }

   public ClaimsMutator.AudienceCollection add(String s) {
      this.delegate.add(s);
      return this;
   }

   public ClaimsMutator.AudienceCollection add(Collection c) {
      this.delegate.add(c);
      return this;
   }

   public ClaimsMutator.AudienceCollection clear() {
      this.delegate.clear();
      return this;
   }

   public ClaimsMutator.AudienceCollection remove(String s) {
      this.delegate.remove(s);
      return this;
   }

   public Object and() {
      this.delegate.and();
      return this.parent;
   }
}
