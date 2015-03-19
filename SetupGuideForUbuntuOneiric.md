# Installation #
The firecracker control code is compatible only with a specific version of the [PyUSB wrapper](https://sourceforge.net/apps/trac/pyusb/): **1.0.0-alpha-0**

Download the .tar.gz from SourceForge then install it with `sudo ./setup.py install`

## Packaging and installing the server application ##

`sudo apt-get install cdbs devscripts gdebi subversion`

`svn checkout https://androbuntu.googlecode.com/svn/trunk androbuntu`

This step will create the Debian-style package, and at the end will ask you whether you want to install it.

`./make_binary_packages.sh`


## Running the server application ##
`sudo apt-get install bottlerocket`

`ubudroid-server`

The server icon should appear in the system tray.

## Automatic Startup ##

There is a checkbox in the application for enabling it upon startup. Toggling the box adds or removes a symbolic link to the desktop launcher file in `~/.config/autostart`.

To manually add the application to startup:

`cd ~/.config/autostart`

`ln -s /usr/share/applications/ubudroid-server.desktop`

# Firewall Configuration #
I like to use the Firestarter GUI firewall configuration application for Ubuntu, since it provides real-time feedback when a connection is rejected by the firewall.

However, to get Firestarter working correctly in Ubuntu 11.10, you need to follow [these instructions](http://askubuntu.com/questions/52817/system-log-error-when-running-firestarter-configuration-program/52832#52832).

# Router Port Forwarding #

The default port for the Android app is `46645`. You'll need to forward that port to the computer that's running the Ubudroid Server application.