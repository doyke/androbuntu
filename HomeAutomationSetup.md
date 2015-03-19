## Introduction ##

Upon arrival at home, I have the system perform a couple of actions:
  * Wake computer from suspend mode
  * Turn on lights

## Wake-On-LAN Configuration ##
When your Android phone and suspended computer are on the same subnet behind a single router, [Wake-On-LAN](http://en.wikipedia.org/wiki/Wake_on_lan) (WOL) is straightforward.  WOL must first be enabled in your computer's BIOS.  Then a "magic packet" is sent to the router's "broadcast address" on that subnet (192.168.1.255 or similar), targeting the MAC address of the suspended computer.

I recommend installing the [DD-WRT](http://www.dd-wrt.com/site/support/router-database) firmware on your router. DD-WRT has a page that will allow you to test WOL ([see screenshot](http://androbuntu.googlecode.com/svn/screenshots/ddwrt/wol.png)).

### Multi-router configuration ###
Setting up WOL was somewhat complicated for my arrangement, as I have two "daisy-chained" WRT54GL routers, with DD-WRT on both.  Both routers use NAT.

The "top-level" router provides wireless internet for the apartment, while the second router serves as a wireless **client**, providing hard-wired ethernet in my room.

Since the Android device connects to the top-level router, the "Magic Packet" must be relayed across the second router. Normally, the magic packet is transmitted on the "broadcast address" of the subnet, but I was unable set up port forwarding to the broadcast address on the secondary router.  I was able to forward port 9 to a specific IP address on the subnet, but as I learned, the magic packet targets a specific MAC address, and the association between MAC and IP can be lost when a host is suspended.  To prevent this, I followed [these instructions](http://www.dd-wrt.com/wiki/index.php/WOL#Remote_Wake_On_LAN_via_Port_Forwarding) to create a static ARP entry, using my own IP and MAC address:
```
ip neigh change 192.168.1.17 lladdr 90:e6:ba:5d:16:4e nud permanent dev br0
ip neigh add 192.168.1.17 lladdr 90:e6:ba:5d:16:4e nud permanent dev br0
```
where `192.168.1.17` is the suspended computer's statically-assigned IP address and `90:e6:ba:5d:16:4e` is its MAC address.

## X10 Lighting Control ##
I use a [CM19A](http://www.x10.com/products/x10_cm19a.htm) (obtained [cheaply](http://shop.ebay.com/?&_nkw=cm19a) on eBay), TM751 (X10 Wireless transceiver), and several lamp (LM465) and appliance (AM466) modules.

I tweaked [one of the several](http://m.lemays.org/projects/x10-cm19a-linux-driver) Python USB scripts for operating the CM19A.

## USB LED indicators ##

  * https://www.delcomproducts.com/products_usblmp.asp
    * 80-90 bucks
  * http://www.computercolorcannon.com/
    * 100 bucks
  * http://store.mp3car.com/USB_LED_1_RGB_LED_Controller_p/com-143.htm
    * 50 Bucks
    * not sure if Linux support
  * PacDrive: http://www.ultimarc.com/pacdrive.html
    * 30 bucks
    * PacDrive is an HID device.
    * Order: http://www.ultimarc.com/JShopServer/section.php?xSec=5&xPage=1
  * USBMicro U401, U421, U451
    * 30 bucks, 30 bucks, 40 bucks, respectively
    * These are HID devices.
    * The first two are Single In-line Package and Dual In-line Package of the same thing.  General purpose I/O.
    * http://www.circuitgizmos.com/products.shtml
    * The U451 controls relays.