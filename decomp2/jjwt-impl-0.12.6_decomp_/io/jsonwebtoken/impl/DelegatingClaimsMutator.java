package io.jsonwebtoken.impl;

import io.jsonwebtoken.ClaimsMutator;
import io.jsonwebtoken.impl.lang.DelegatingMapMutator;
import io.jsonwebtoken.impl.lang.Parameter;
import io.jsonwebtoken.impl.lang.Parameters;
import io.jsonwebtoken.lang.Collections;
import io.jsonwebtoken.lang.MapMutator;
import io.jsonwebtoken.lang.Strings;
import java.util.Date;
import java.util.Map;
import java.util.Set;

public class DelegatingClaimsMutator extends DelegatingMapMutator implements ClaimsMutator {
   private static final Parameter AUDIENCE_STRING;

   protected DelegatingClaimsMutator() {
      super(new ParameterMap(DefaultClaims.PARAMS));
   }

   MapMutator put(Parameter param, Object value) {
      ((ParameterMap)this.DELEGATE).put(param, value);
      return this.self();
   }

   public Object put(String key, Object value) {
      if (AUDIENCE_STRING.getId().equals(key)) {
         if (value instanceof String) {
            Object existing = this.get(key);
            this.audience().single((String)value);
            return existing;
         }

         this.getAudience();
      }

      return super.put(key, value);
   }

   public void putAll(Map m) {
      if (m != null) {
         for(Map.Entry entry : m.entrySet()) {
            String s = (String)entry.getKey();
            this.put(s, entry.getValue());
         }

      }
   }

   Object get(Parameter param) {
      return ((ParameterMap)this.DELEGATE).get(param);
   }

   public MapMutator setIssuer(String iss) {
      return this.issuer(iss);
   }

   public MapMutator issuer(String iss) {
      return this.put((Parameter)DefaultClaims.ISSUER, iss);
   }

   public MapMutator setSubject(String sub) {
      return this.subject(sub);
   }

   public MapMutator subject(String sub) {
      return this.put((Parameter)DefaultClaims.SUBJECT, sub);
   }

   public MapMutator setAudience(String aud) {
      return (MapMutator)this.audience().single(aud);
   }

   private Set getAudience() {
      if (!((Parameter)((ParameterMap)this.DELEGATE).PARAMS.get(AUDIENCE_STRING.getId())).supports(Collections.emptySet())) {
         String existing = (String)this.get(AUDIENCE_STRING);
         this.remove(AUDIENCE_STRING.getId());
         this.setDelegate(((ParameterMap)this.DELEGATE).replace(DefaultClaims.AUDIENCE));
         this.put((Parameter)DefaultClaims.AUDIENCE, Collections.setOf(new String[]{existing}));
      }

      return (Set)this.get(DefaultClaims.AUDIENCE);
   }

   private MapMutator audienceSingle(String aud) {
      if (!Strings.hasText(aud)) {
         return this.put((Parameter)DefaultClaims.AUDIENCE, (Object)null);
      } else {
         this.remove(AUDIENCE_STRING.getId());
         this.setDelegate(((ParameterMap)this.DELEGATE).replace(AUDIENCE_STRING));
         return this.put((Parameter)AUDIENCE_STRING, aud);
      }
   }

   public ClaimsMutator.AudienceCollection audience() {
      // $FF: Couldn't be decompiled
   }

   public MapMutator setExpiration(Date exp) {
      return this.expiration(exp);
   }

   public MapMutator expiration(Date exp) {
      return this.put((Parameter)DefaultClaims.EXPIRATION, exp);
   }

   public MapMutator setNotBefore(Date nbf) {
      return this.notBefore(nbf);
   }

   public MapMutator notBefore(Date nbf) {
      return this.put((Parameter)DefaultClaims.NOT_BEFORE, nbf);
   }

   public MapMutator setIssuedAt(Date iat) {
      return this.issuedAt(iat);
   }

   public MapMutator issuedAt(Date iat) {
      return this.put((Parameter)DefaultClaims.ISSUED_AT, iat);
   }

   public MapMutator setId(String jti) {
      return this.id(jti);
   }

   public MapMutator id(String jti) {
      return this.put((Parameter)DefaultClaims.JTI, jti);
   }

   // $FF: synthetic method
   static MapMutator access$000(DelegatingClaimsMutator x0, String x1) {
      return x0.audienceSingle(x1);
   }

   static {
      AUDIENCE_STRING = Parameters.string(DefaultClaims.AUDIENCE.getId(), DefaultClaims.AUDIENCE.getName());
   }
}
