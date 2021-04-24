#!/bin/bash
source error_handling.bash

# Following official instructions from: https://docs.docker.com/engine/install/ubuntu/

echo "
********************************************************
Executing `basename $0`
********************************************************
"

sudo apt-get update
sudo apt-get remove docker docker.io containerd runc
sudo apt-get install -y \
    apt-transport-https \
    ca-certificates \
    curl \
    gnupg-agent \
    software-properties-common
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
sudo apt-key fingerprint 0EBFCD88
sudo add-apt-repository \
   "deb [arch=amd64] https://download.docker.com/linux/ubuntu \
   $(lsb_release -cs) \
   stable"
sudo apt-get update
sudo apt-get install -y \
        docker-ce \
        docker-ce-cli \
        containerd.io
sudo usermod -aG docker $USER
echo "You will need to log out and log back into the system for docker to be accessible as your user"

sudo mkdir -p /etc/docker
echo '{
 "default-address-pools":
  [
   {"base":"198.18.0.0/22","size":26}
  ]
}' | sudo tee /etc/docker/daemon.json > /dev/null
sudo systemctl restart docker

# Following official instructions from: https://docs.docker.com/compose/install/
# Small tweak - always get the latest version of compose at the time this is run using github's url rules
mkdir -p ~/bin
curl -L "https://github.com/docker/compose/releases/latest/download/docker-compose-$(uname -s)-$(uname -m)" -o ~/bin/docker-compose
chmod +x ~/bin/docker-compose
