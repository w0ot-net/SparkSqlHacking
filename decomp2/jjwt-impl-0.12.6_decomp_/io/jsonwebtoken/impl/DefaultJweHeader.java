package io.jsonwebtoken.impl;

import io.jsonwebtoken.JweHeader;
import io.jsonwebtoken.Jwts.SIG;
import io.jsonwebtoken.impl.lang.Converters;
import io.jsonwebtoken.impl.lang.Parameter;
import io.jsonwebtoken.impl.lang.Parameters;
import io.jsonwebtoken.impl.lang.PositiveIntegerConverter;
import io.jsonwebtoken.impl.lang.RequiredBitLengthConverter;
import io.jsonwebtoken.impl.security.JwkConverter;
import io.jsonwebtoken.lang.Registry;
import io.jsonwebtoken.lang.Strings;
import io.jsonwebtoken.security.PublicJwk;
import java.util.Map;

public class DefaultJweHeader extends DefaultProtectedHeader implements JweHeader {
   static final Parameter ENCRYPTION_ALGORITHM = Parameters.string("enc", "Encryption Algorithm");
   public static final Parameter EPK;
   static final Parameter APU;
   static final Parameter APV;
   public static final Parameter IV;
   public static final Parameter TAG;
   public static final Parameter P2S;
   public static final Parameter P2C;
   static final Registry PARAMS;

   static boolean isCandidate(ParameterMap map) {
      String id = (String)map.get(DefaultHeader.ALGORITHM);
      return Strings.hasText(id) && !id.equalsIgnoreCase(SIG.NONE.getId()) && Strings.hasText((String)map.get(ENCRYPTION_ALGORITHM));
   }

   public DefaultJweHeader(Map map) {
      super(PARAMS, map);
   }

   public String getName() {
      return "JWE header";
   }

   public String getEncryptionAlgorithm() {
      return (String)this.get(ENCRYPTION_ALGORITHM);
   }

   public PublicJwk getEphemeralPublicKey() {
      return (PublicJwk)this.get(EPK);
   }

   public byte[] getAgreementPartyUInfo() {
      return (byte[])this.get(APU);
   }

   public byte[] getAgreementPartyVInfo() {
      return (byte[])this.get(APV);
   }

   public byte[] getInitializationVector() {
      return (byte[])this.get(IV);
   }

   public byte[] getAuthenticationTag() {
      return (byte[])this.get(TAG);
   }

   public byte[] getPbes2Salt() {
      return (byte[])this.get(P2S);
   }

   public Integer getPbes2Count() {
      return (Integer)this.get(P2C);
   }

   static {
      EPK = (Parameter)Parameters.builder(JwkConverter.PUBLIC_JWK_CLASS).setId("epk").setName("Ephemeral Public Key").setConverter(JwkConverter.PUBLIC_JWK).build();
      APU = (Parameter)Parameters.bytes("apu", "Agreement PartyUInfo").build();
      APV = (Parameter)Parameters.bytes("apv", "Agreement PartyVInfo").build();
      IV = (Parameter)Parameters.bytes("iv", "Initialization Vector").setConverter(new RequiredBitLengthConverter(Converters.BASE64URL_BYTES, 96)).build();
      TAG = (Parameter)Parameters.bytes("tag", "Authentication Tag").setConverter(new RequiredBitLengthConverter(Converters.BASE64URL_BYTES, 128)).build();
      P2S = (Parameter)Parameters.bytes("p2s", "PBES2 Salt Input").setConverter(new RequiredBitLengthConverter(Converters.BASE64URL_BYTES, 64, false)).build();
      P2C = (Parameter)Parameters.builder(Integer.class).setConverter(PositiveIntegerConverter.INSTANCE).setId("p2c").setName("PBES2 Count").build();
      PARAMS = Parameters.registry(DefaultProtectedHeader.PARAMS, ENCRYPTION_ALGORITHM, EPK, APU, APV, IV, TAG, P2S, P2C);
   }
}
