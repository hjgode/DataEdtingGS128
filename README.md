# DataEdtingGS128

This is a plugin to be used on Honeywell Handheld devices running Android. It will insert the AimID ]C1 in front of the barcode data of a scanned GS1-128 barcode. The GS (\x1d) characters inside the GS1-128 barcode are replaced by the # symbol.
The plugin can be used in Honeywell Scanner Settings and in BasicTE.

![Main Screen](https://github.com/hjgode/DataEdtingGS128/raw/master/doc/main.jpg)    ![Main Screen](https://github.com/hjgode/DataEdtingGS128/raw/master/doc/ScannerSettings2.jpg)

## Provisioning
There are pdf documents to download and install the apk. Another pdf to change Honeywell BasicTE Dataedit plugin to use this plugin and another one to use this plugin within the Data Collection Scanner settings. The latter is useful for testing the plugin with standard text input fields. The Plugin can be disabled and the replacement for the GS symbol can be set using Data Collection in Settings inside the Data Editing Plugin setup.

![Download Barcode](https://github.com/hjgode/DataEdtingGS128/raw/master/doc/Provisioner_DataEdtingGS128_apk.png)
[Download and install Plugin](https://github.com/hjgode/DataEdtingGS128/raw/master/doc/Provisioner_DataEdtingGS128_apk.pdf)

![Activate Barcode for BasicTE](https://github.com/hjgode/DataEdtingGS128/raw/master/doc/te_settings_hsm.dataeditgs128-DataEditing.png)
[Activate this Plugin in BasicTE](https://github.com/hjgode/DataEdtingGS128/raw/master/doc/te_settings_hsm.dataeditgs128-DataEditing.pdf)

![Activate Barcode for scanner wedge](https://github.com/hjgode/DataEdtingGS128/raw/master/doc/DataCollectionService_DataEditingPlugin-hsm.dataeditgs128.png)
[Activate this Plugin for the internal barcode scanner](https://github.com/hjgode/DataEdtingGS128/raw/master/doc/DataCollectionService_DataEditingPlugin-hsm.dataeditgs128.pdf)

The source xml files are also available in the doc directory:

[XML: Download and install Plugin](https://github.com/hjgode/DataEdtingGS128/raw/master/doc/Provisioner_DataEdtingGS128_apk.xml)

[XML: Activate this Plugin in BasicTE](https://github.com/hjgode/DataEdtingGS128/raw/master/doc/te_settings_hsm.dataeditgs128-DataEditing.xml)

[XML: Activate this Plugin for the internal barcode scanner](https://github.com/hjgode/DataEdtingGS128/raw/master/doc/DataCollectionService_DataEditingPlugin-hsm.dataeditgs128.xml)

## Binary download
[Download Release apk](https://github.com/hjgode/DataEdtingGS128/raw/master/app/release/DataEdtingGS128.apk)

### History
version 1.4 (4)

Corrected code. The ]C1 is not part of barcode data and so the logic has to be changed:

* If a aimID ]C1 is set and barcode starts with 90: insert the aimId in front and replace all GS symbols by the # symbol.

* If aimId is ]C1 and barcode does not start with 90, transmit data as is but remove any ]C1 in front, if data starts with that.

* If aimId is not ]C1, transmit data as is.

version 1.3 (3)

updated on 4.12.2020

* Will change all GS1-128 codes and remove the leading ]C1 (inserted by scan engine as default for the EAN 128 barcode starting with a GS symbol, the identifier for GS1-128).
Except the GS1-128 barcode starts with 90. Then the ]C1 is inserted at front and all GS symbols are replaced by #.

* Replaces GS symbol inside GS1-128 if first AI is 90 (barcode data starts with ]C190)

version 1.0

* Changes all GS1-128 barcodes by removing the leading ]C1 and replacing GS symbols by a #
