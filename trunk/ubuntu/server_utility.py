#!/usr/bin/env python

import pygtk
pygtk.require('2.0')
import gtk, gobject
import pynotify
import threading
import socket
import os

from master import port


# =========================

class WebServerThread(threading.Thread):

	"""Web Server thread"""

	stopthread = threading.Event()
	def __init__(self):
		threading.Thread.__init__(self)

		self.setDaemon(True)


		hostname = ''

		backlog = 5
		self.size = 1024
		self.s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
		self.s.bind((hostname, port))
		self.s.listen(backlog)


	def run(self):

		while not self.stopthread.isSet():

			client, address = self.s.accept()
			data = client.recv(self.size)
			if data:

				print "Got a message:", data

				if data == "quit":

					client.close()
					break

				string = 'xte "key ' + data + '"'
				os.system( string )

				client.send(data+"\n")


			client.close()

	def stop(self):
		"""send QUIT request to http server running on localhost:<port>"""

		self.stopthread.set()

# =========================

class HelloWorld(gtk.Window):

	X11_KEY_DEFINITIONS = "/usr/share/X11/XKeysymDB"
	ANDROID_ICON = "android_normal.png"

	# This is a callback function. The data arguments are ignored
	# in this example. More on callbacks below.
	def hello(self, widget, data=None):

		n = pynotify.Notification("Title", "message")

		pixbuf = gtk.gdk.pixbuf_new_from_file( self.ANDROID_ICON )
		n.set_icon_from_pixbuf( pixbuf )
		n.set_timeout(3000)
		n.attach_to_status_icon( self.my_status_icon )
		n.show()


	def delete_event(self, widget, event, data=None):
		return False

	def destroy(self, widget, data=None):
		print "destroy signal occurred"
		gtk.main_quit()



		import socket
		from master import port

#		host = 'localhost'
		host = '192.168.0.9'

		s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
		s.connect( (host, port) )

		s.send( "quit" )
		s.close()
		print 'Received:', data




	def cb_row_activated(self, treeview, path, view_column):

		ts = treeview.get_model()
		command_to_send = ts.get_value(ts.get_iter(path), 0)


		import socket
		from master import port

		host = 'localhost'

		size = 1024
		s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
		s.connect( (host, port) )

		s.send( command_to_send )
		data = s.recv(size)
		s.close()
		print 'Received:', data,






	def __init__(self):
		gtk.Window.__init__(self)

		self.connect("delete_event", self.delete_event)
		self.connect("destroy", self.destroy)
		self.set_border_width(10)

		self.hidden_window = False
		self.pynotify_enabled = False

		
		
		try:

			if pynotify.init("My Application Name"):

				self.pynotify_enabled = True

			else:
				print "there was a problem initializing the pynotify module"

		except:
			print "you don't seem to have pynotify installed"






		vbox = gtk.VBox()

		button = gtk.Button("Hello World")
		button.connect("clicked", self.hello, None)
		vbox.pack_start(button, False, False)


		vbox.pack_start(gtk.Label("Double-click a row to test the server command:"), False, False)


		xf86_list = [line.split()[0] for line in open(self.X11_KEY_DEFINITIONS) if "XF86" in line and line[0] != "!"]	# Is this ugly or what?

		ls = gtk.ListStore(str)
		for item in xf86_list:
			if "Audio" in item:
				ls.append( [item] )


		tv = gtk.TreeView(ls)
		col = gtk.TreeViewColumn("Command List", gtk.CellRendererText(), text=0)
		tv.append_column(col)

		tv.connect("row-activated", self.cb_row_activated)


		sw = gtk.ScrolledWindow()
		sw.set_policy(gtk.POLICY_NEVER, gtk.POLICY_AUTOMATIC)
		sw.add(tv)
		vbox.pack_start(sw)


		self.add(vbox)



		self.my_status_icon = gtk.StatusIcon()
		self.my_status_icon.set_from_stock(gtk.STOCK_NO)
		self.my_status_icon.set_tooltip("Table: Free")

		self.my_status_icon.connect("activate", self.toggle_window)
		self.my_status_icon.connect("popup-menu", self.systray_popup_callback)

		self.set_icon_from_file( self.ANDROID_ICON )
		self.set_title("AndroBuntu Server")


		self.show_all()

	# -----------------------------

	def build_menu(self):
		menu = gtk.Menu()

		temp_item = gtk.MenuItem("Simulate bounce detection")
#		temp_item.connect("activate", self.receive_bounce)
		menu.append(temp_item)

		temp_item = gtk.MenuItem("Quit")
		temp_item.connect("activate", self.destroy)
		menu.append(temp_item)

		menu.show_all()
		return menu
	# -----------------------------

	def systray_popup_callback(self, status_icon, button, activate_time):
		my_popup_menu = self.build_menu()
		my_popup_menu.popup(None, None, None, button, activate_time, data=None)

	# -----------------------------

	def toggle_window(self, status_icon):
		if self.hidden_window:
			self.window.show()
			self.hidden_window = False
		else:
			self.window.hide()
			self.hidden_window = True


if __name__ == "__main__":

	gobject.threads_init()

	web_server_thread = WebServerThread()
	web_server_thread.start()

	hello = HelloWorld()
	gtk.main()

	web_server_thread.stop()

