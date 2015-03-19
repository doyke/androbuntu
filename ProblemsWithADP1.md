# Phone says I don't have enough space left to download the .apk #

I had installed an 8 Gigabyte SDHC card, which had ample free space.  However, reverting back to the original 1GB card resolved the issue.

Reference: http://groups.google.com/group/android-developers/browse_thread/thread/82ba3ee4aa947de2?pli=1


# Front USB ports offer unreliable connection #

I've found that the USB ports in the back of my computer are more reliable than the front ports.  When I was using the front ports, often Eclipse was not able to detect the phone to deploy an application, and often Gnome/Nautilus was not able to mount the SD card.  There have been no such issues since I switched to the back ports.

# HTC device will not prompt with "USB connected message" and/or be absent from "lsusb" and "./adb devices" #

When this happens, it seems to fail independently of which computer it is plugged into or which operating system.  The tether connection to the phone itself seems unreliable.  My temporary solution has been to insert the plug into the phone's bottom jack with upward pressure (i.e. - in the direction that the screen faces), and keep that pressure applied by setting the phone such that the cable rests on a surface higher than the phone.

Symptoms of this issue are mysterious absence of "" from the list of "lsusb" devices.  It may appear for a short time when initially connected, but be absent when attempted later.