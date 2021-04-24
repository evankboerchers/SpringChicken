#!/bin/bash
source error_handling.bash

echo "
********************************************************
Executing `basename $0`
********************************************************
"

export SDKMAN_DIR="$HOME/bin/sdkman" && curl -s "https://get.sdkman.io" | bash
