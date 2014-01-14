#!/usr/bin/env bash

if [ -f ./boxes/ringo.box ]
then
    echo "Ringo Box starting..."
	cp -f ./conf/Vagrantfile.ringo ./Vagrantfile
	vagrant up

	echo "Ringo Box running!!!"
else
	echo "Ringo Box installing..."
	
	cp -f ./conf/Vagrantfile.docker ./Vagrantfile
	vagrant up

	echo "Ringo Box packaging..."
	mkdir boxes
	vagrant package --output ./boxes/ringo.box
	vagrant destroy

	echo "Ringo Box adding..."
	vagrant box add ringo ./boxes/ringo.box

	echo "Ringo Box installed!!!"
fi

