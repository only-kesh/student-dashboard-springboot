#!/bin/sh
set -eu

GRADLE_VERSION="9.1.0"
BASE_DIR="${GRADLE_USER_HOME:-$HOME/.gradle}/bootstrap-wrapper"
DIST_DIR="$BASE_DIR/gradle-$GRADLE_VERSION"
ZIP_FILE="$BASE_DIR/gradle-$GRADLE_VERSION-bin.zip"
GRADLE_BIN="$DIST_DIR/gradle-$GRADLE_VERSION/bin/gradle"

if [ ! -x "$GRADLE_BIN" ]; then
    mkdir -p "$DIST_DIR"
    if [ ! -f "$ZIP_FILE" ]; then
        echo "Downloading Gradle $GRADLE_VERSION..."
        if command -v curl >/dev/null 2>&1; then
            curl -fL "https://services.gradle.org/distributions/gradle-$GRADLE_VERSION-bin.zip" -o "$ZIP_FILE"
        elif command -v wget >/dev/null 2>&1; then
            wget -O "$ZIP_FILE" "https://services.gradle.org/distributions/gradle-$GRADLE_VERSION-bin.zip"
        else
            echo "Error: curl or wget is required to download Gradle." >&2
            exit 1
        fi
    fi

    echo "Installing Gradle $GRADLE_VERSION..."
    if command -v unzip >/dev/null 2>&1; then
        rm -rf "$DIST_DIR/gradle-$GRADLE_VERSION"
        unzip -q "$ZIP_FILE" -d "$DIST_DIR"
    else
        echo "Error: unzip is required to install Gradle." >&2
        exit 1
    fi
fi

exec "$GRADLE_BIN" "$@"
