#!/bin/bash

set -e

REPO_URL="https://github.com/JetBrains/intellij-community.git"
REPO_DIR="/tmp/intellij-community"
ENGINE_DIR="/tmp/intellij-community/plugins/java-decompiler/engine"
INSTALL_JAR="/usr/bin/fernflower.jar"
INSTALL_WRAPPER="/usr/bin/fernflower"

# Ensure running as root
if [ "$(/usr/bin/id -u)" -ne 0 ]; then
    /bin/echo "This script must be run as root." >&2
    exit 1
fi

# Check that Java is available
if ! /usr/bin/which java >/dev/null 2>&1; then
    /bin/echo "Error: java is not installed or not in PATH." >&2
    exit 1
fi

# Check that Git is available
if ! /usr/bin/which git >/dev/null 2>&1; then
    /bin/echo "Error: git is not installed or not in PATH." >&2
    exit 1
fi

# Clone repository only if it does not already exist
if [ ! -d "$REPO_DIR" ]; then
    /usr/bin/git clone "$REPO_URL" "$REPO_DIR"
else
    /bin/echo "Repository already exists at $REPO_DIR, skipping clone."
fi

# Build fernflower via Gradle wrapper
cd "$ENGINE_DIR"
/usr/bin/env bash ./gradlew build

# Locate the built jar
JAR_PATH=$(/usr/bin/find "$ENGINE_DIR/build/libs" -name "fernflower*.jar" | /usr/bin/head -n 1)
if [ ! -f "$JAR_PATH" ]; then
    /bin/echo "Build failed: fernflower jar not found." >&2
    exit 1
fi

# Install the jar
/bin/cp "$JAR_PATH" "$INSTALL_JAR"
/bin/chmod 755 "$INSTALL_JAR"

# Create the wrapper launcher at /usr/bin/fernflower
/bin/cat >"$INSTALL_WRAPPER" <<'EOF'
#!/bin/sh
exec /usr/bin/java -jar /usr/bin/fernflower.jar "$@"
EOF
/bin/chmod 755 "$INSTALL_WRAPPER"

/bin/echo "Installed: $INSTALL_JAR and $INSTALL_WRAPPER"

# Cleanup repository
cd /
/bin/rm -rf "$REPO_DIR"

/bin/echo "Cleanup complete. You can run: /usr/bin/fernflower input.jar output_dir/"

