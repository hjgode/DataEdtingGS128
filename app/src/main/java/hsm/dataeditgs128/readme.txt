hsm.dataeditgs128
DataEditing

A 'total freddom' data editing plugin for Honeywell handheld devices with built-in barcode scanners
(CT50 Android 4.x and 6.x, CN51 Android 6).

The plugin allows to alter scanned barcode data before it ist transmitted as keyboard input
(Keyboard Wedge).

This plugin replaces all scanned data by it's hex representation and so a scanned barcode of
'10110' will be changed to '3130313130' and is used to demonstrate the function.

# Installation

Copy and install the plugin apk on the device.
Ensure that a recent CommonES is installed on the device.
Go to "Settings" > "Scanning" > "Internal Scanner" > "Default Profile" > "Data Processing Settings"
and open "Data Editing Plugin".
Enter the plugin's application and class name, for example "hsm.dataeditgs128/.DataEditing".
Start scanning into a text box.

Instead of "Default Profile" another, custom, application dependent profile may be selected.
