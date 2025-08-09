package io.jsonwebtoken.security;

import java.io.InputStream;

public interface AssociatedDataSupplier {
   InputStream getAssociatedData();
}
