package io.jsonwebtoken;

import io.jsonwebtoken.security.PublicJwk;

public interface JweHeader extends ProtectedHeader {
   String getEncryptionAlgorithm();

   PublicJwk getEphemeralPublicKey();

   byte[] getAgreementPartyUInfo();

   byte[] getAgreementPartyVInfo();

   byte[] getInitializationVector();

   byte[] getAuthenticationTag();

   Integer getPbes2Count();

   byte[] getPbes2Salt();
}
