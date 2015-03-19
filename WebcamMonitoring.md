# Hardware #
  * [046d:08cc Logitech Orbit (PTZ)](http://www.logitech.com/en-us/38/3480) **x2**
  * [Foscam](http://www.zoneminder.com/wiki/index.php/Foscam) **x1**

# Controlling the Logitech Orbit #

For the Logitech Orbit, I found an [old guide](http://wiki.brokennexus.com/Zoneminder_and_Motion_setup_for_Logitech_Orbit_Sphere_PTZ), but things have changed for Oneiric and later.

## GUI Control ##
The guvcview application allows pan-tilt control.

`sudo apt-get install guvcview`


## Command-line Control ##
I wanted to enable Pan/Tilt control and toggle the LED light. First, I ran

`uvcdynctrl --clist`

to see whether those functions were supported. They were not. So I ran

`uvcdynctrl --import=/usr/share/uvcdynctrl/data/046d/logitech.xml`

The `--clist` option thereafter showed support for Pan/Tilt and LED control.

## Pan/Tilt Control ##
Tilt down:

`uvcdynctrl --device=video1 --set="Tilt (relative)" 100`

To tilt in the opposite direction:

`uvcdynctrl --device=video1 --set="Tilt (relative)" -- -100`

## LED Control ##
  * **OFF** `uvcdynctrl --device=video1 --set="LED1 Mode" 0`
  * **ON** `uvcdynctrl --device=video1 --set="LED1 Mode" 1`
  * **BLINKING** `uvcdynctrl --device=video1 --set="LED1 Mode" 2`
The command spits out some warning messages, which you can ignore.

## Zoneminder compatibility ##
To retrieve Zoneminder info about your webcam:

`zmu -d /dev/video0 -q -v`

# Streaming Video #
## Source command ##
`gst-launch v4l2src device=/dev/video0 ! 'video/x-raw-yuv,width=640,height=480,framerate=30/1' ! ffmpegcolorspace ! videoscale ! ffenc_h263 ! video/x-h263 ! rtph263ppay pt=96 ! udpsink host=localhost port=5000 sync=false`

## Sink command ##
`gst-launch udpsrc port=5000 ! application/x-rtp, clock-rate=90000,payload=96,encoding-name=H263-2000 ! rtph263pdepay queue-delay=0 ! ffdec_h263 ! xvimagesink`