#!/bin/bash
source setup_scripts/error_handling.bash

echo "
********************************************************
Executing `basename $0`
********************************************************
"

./setup_scripts/docker.bash
./setup_scripts/sdkman.bash
./setup_scripts/jetbrains.bash
