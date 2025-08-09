package io.jsonwebtoken.impl;

import io.jsonwebtoken.JweHeader;
import io.jsonwebtoken.impl.lang.Parameter;
import io.jsonwebtoken.security.PublicJwk;
import java.net.URI;
import java.util.List;
import java.util.Set;

public class DefaultMutableJweHeader extends DefaultJweHeaderMutator implements JweHeader {
   public DefaultMutableJweHeader(DefaultJweHeaderMutator src) {
      super(src);
   }

   private Object get(Parameter param) {
      return ((ParameterMap)this.DELEGATE).get(param);
   }

   public String getAlgorithm() {
      return (String)this.get(DefaultHeader.ALGORITHM);
   }

   public String getContentType() {
      return (String)this.get(DefaultHeader.CONTENT_TYPE);
   }

   public String getType() {
      return (String)this.get(DefaultHeader.TYPE);
   }

   public String getCompressionAlgorithm() {
      return (String)this.get(DefaultHeader.COMPRESSION_ALGORITHM);
   }

   public URI getJwkSetUrl() {
      return (URI)this.get(DefaultProtectedHeader.JKU);
   }

   public PublicJwk getJwk() {
      return (PublicJwk)this.get(DefaultProtectedHeader.JWK);
   }

   public String getKeyId() {
      return (String)this.get(DefaultProtectedHeader.KID);
   }

   public Set getCritical() {
      return (Set)this.get(DefaultProtectedHeader.CRIT);
   }

   public URI getX509Url() {
      return (URI)this.get(DefaultProtectedHeader.X5U);
   }

   public List getX509Chain() {
      return (List)this.get(DefaultProtectedHeader.X5C);
   }

   public byte[] getX509Sha1Thumbprint() {
      return (byte[])this.get(DefaultProtectedHeader.X5T);
   }

   public byte[] getX509Sha256Thumbprint() {
      return (byte[])this.get(DefaultProtectedHeader.X5T_S256);
   }

   public byte[] getAgreementPartyUInfo() {
      return (byte[])this.get(DefaultJweHeader.APU);
   }

   public byte[] getAgreementPartyVInfo() {
      return (byte[])this.get(DefaultJweHeader.APV);
   }

   public Integer getPbes2Count() {
      return (Integer)this.get(DefaultJweHeader.P2C);
   }

   public String getEncryptionAlgorithm() {
      return (String)this.get(DefaultJweHeader.ENCRYPTION_ALGORITHM);
   }

   public PublicJwk getEphemeralPublicKey() {
      return (PublicJwk)this.get(DefaultJweHeader.EPK);
   }

   public byte[] getInitializationVector() {
      return (byte[])this.get(DefaultJweHeader.IV);
   }

   public byte[] getAuthenticationTag() {
      return (byte[])this.get(DefaultJweHeader.TAG);
   }

   public byte[] getPbes2Salt() {
      return (byte[])this.get(DefaultJweHeader.P2S);
   }
}
