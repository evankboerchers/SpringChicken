#!/bin/bash
source error_handling.bash

echo "
********************************************************
Executing `basename $0`
********************************************************
"

# Figure out what the latest jetbrains toolbox is
sudo apt install -y libgbm-dev jq

latestToolboxUrl=$(curl 'https://data.services.jetbrains.com/products/releases?code=TBA&latest=true&type=release&build=&_=1603808949189' --compressed | jq -r '.TBA | .[].downloads.linux.link')
wget -O /tmp/jetbrains-toolbox.tgz $latestToolboxUrl
mkdir -p /tmp/jetbrains-toolbox
tar -zxvf /tmp/jetbrains-toolbox.tgz -C /tmp/jetbrains-toolbox
mkdir -p ~/bin
find /tmp/jetbrains-toolbox/ -type f -exec cp {} ~/bin \;
