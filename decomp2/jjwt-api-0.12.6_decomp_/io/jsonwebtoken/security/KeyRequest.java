package io.jsonwebtoken.security;

import io.jsonwebtoken.JweHeader;

public interface KeyRequest extends Request {
   AeadAlgorithm getEncryptionAlgorithm();

   JweHeader getHeader();
}
