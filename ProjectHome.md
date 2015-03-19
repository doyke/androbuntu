**androbuntu** is an open source utility for remote control of a GNOME desktop, targeted specifically at Ubuntu Linux.

<img src='http://androbuntu.googlecode.com/svn/trunk/graphics/system_diagram/tm751_standalone.jpg' />


## Organization ##
It consists of two components:
  * Ubuntu server
  * Android client

## Features ##
Implemented:
  * Uses the XFree86 "media keys" for playback and volume control
  * Scripted "bedtime mode" button, which blanks the computer screen or suspends the computer, shuts off lights, and starts the clock app
  * "I'm home" function that wakes up the computer from suspend and turns on all of the lights
  * X10 device control
    * ppower
      * [amd64 Ubuntu package](https://launchpad.net/~kostmo/+archive/ppa/+files/ppower_0.1.5-0ubuntu1_amd64.deb)
      * [i386 Ubuntu package](https://launchpad.net/~kostmo/+archive/ppa/+files/ppower_0.1.5-0ubuntu1_i386.deb)
    * [heyu](http://heyu.tanj.com/) likely [will never get an Ubuntu package](https://bugs.launchpad.net/ubuntu/+bug/300130)
    * "bottlerocket" works, if you have a CM17A module (mine's a CM11A)
  * A "beam text blurb" function that will append to a pre-specified file on the server

Planned:
  * Implement "Server is listening" check on client startup
  * Automatically open firewall port w/ .deb using "dfw" - see [UbuntuFirewall](https://wiki.ubuntu.com/UbuntuFirewall#Package%20Integration)
  * Register a list of custom scripts
  * Use the [MediaController widget](http://developer.android.com/reference/android/widget/MediaController.html) with a custom implementation of [MediaController.MediaPlayerControl](http://developer.android.com/reference/android/widget/MediaController.MediaPlayerControl.html), remote-linked to the server]
  * Remote touchpad

Ideas for script list:
  * Take a picture with the webcam and send it to the phone

Other feature suggestions:
  * Torrent monitor (rtorrent?)

## Related Projects ##
  * http://gmote.org
    * Currently, Gmote [doesn't support media keys](http://groups.google.com/group/gmote-users/msg/7ec5eae328c20076)
  * [Android Garage Door Opener](http://brad.livejournal.com/2394707.html)
    * This does use X10!

Projects I haven't really looked at:
  * [android-vlc-remote](http://code.google.com/p/android-vlc-remote/)
  * [socontrol](http://code.google.com/p/socontrol/)
  * [androidpcremotecontrol](http://code.google.com/p/androidpcremotecontrol/)
  * [taiyaki](http://code.google.com/p/taiyaki/) - also X10 on Android!



  * A list of software for X10 home automation
    * http://www.linuxha.com/athome/index.html#Software

![http://androbuntu.googlecode.com/svn/screenshots/android/5-13-2009/main.png](http://androbuntu.googlecode.com/svn/screenshots/android/5-13-2009/main.png)
![http://androbuntu.googlecode.com/svn/screenshots/android/5-13-2009/x10.png](http://androbuntu.googlecode.com/svn/screenshots/android/5-13-2009/x10.png)
![http://androbuntu.googlecode.com/svn/screenshots/android/5-13-2009/flinger.png](http://androbuntu.googlecode.com/svn/screenshots/android/5-13-2009/flinger.png)