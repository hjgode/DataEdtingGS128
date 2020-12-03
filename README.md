# DataEdtingGS128

This is a plugin to be used on Honeywell Handheld devices running Android. It will insert the AimID ]C1 in front of the barcode data of a scanned GS1-128 barcode. The GS (\x1d) characters inside the GS1-128 barcode are replaced by the # symbol.
The plugin can be used in Honeywell Scanner Settings and in BasicTE.

## Provisioning
There are pdf documents to download and install the apk. Another pdf to change Honeywell BasicTE Dataedit plugin to use this plugin and another one to use this plugin within the Data Collection Scanner settings. The latter is useful for testing the plugin with standard text input fields. The Plugin can be disabled and the replacement for the GS symbol can be set using Data Collection in Settings inside the Data Editing Plugin setup.

[Download and install Plugin](https://github.com/hjgode/DataEdtingGS128/raw/master/doc/Provisioner_DataEdtingGS128_apk.pdf)

[Activate this Plugin in BasicTE](https://github.com/hjgode/DataEdtingGS128/raw/master/doc/te_settings_hsm.dataeditgs128-DataEditing.pdf)

[Activate this Plugin for the internal barcode scanner](https://github.com/hjgode/DataEdtingGS128/raw/master/doc/DataCollectionService_DataEditingPlugin-hsm.dataeditgs128.pdf)

The source xml files are also available in the doc directory:

[XML: Download and install Plugin](https://github.com/hjgode/DataEdtingGS128/raw/master/doc/Provisioner_DataEdtingGS128_apk.xml)

[XML: Activate this Plugin in BasicTE](https://github.com/hjgode/DataEdtingGS128/raw/master/doc/te_settings_hsm.dataeditgs128-DataEditing.xml)

[XML: Activate this Plugin for the internal barcode scanner](https://github.com/hjgode/DataEdtingGS128/raw/master/doc/DataCollectionService_DataEditingPlugin-hsm.dataeditgs128.xml)

## Binary download
[Download Release apk](https://github.com/hjgode/DataEdtingGS128/raw/master/app/release/DataEdtingGS128.apk)

### History
version 1.3 (3)

* Will change all GS1-128 codes and remove the leading ]C1 (inserted by scan engine as default for the EAN 128 barcode starting with a GS symbol, the identifier for GS1-128)

* Replaces GS symbol inside GS1-128 if first AI is 90 (barcode data starts with ]C190)

version 1.0

* Changes all GS1-128 barcodes by removing the leading ]C1 and replacing GS symbols by a #
