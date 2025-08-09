package org.glassfish.jersey.server;

import java.util.Set;
import org.glassfish.jersey.ExtendedConfig;

public interface ServerConfig extends ExtendedConfig {
   Set getResources();
}
