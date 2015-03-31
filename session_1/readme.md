# session_1

## Are There Slides?

Yes we have slides!!

[HERE on GDocs] (https://docs.google.com/presentation/d/1eT6zzQF7ObETz2T2lCAi985uCiwjhLf1bXaYh3VamfA/edit?usp=sharing)

## Agenda

* People introductions: who are you?!?
* Kind introduction to Android Wear: what is it?!?
* Revising your setup, is Android Studio properly installed?
* Make your first Android Wear App and run it on an emulator

## Preparations

You need to bring Android Studio installed from home, get it [here](https://developer.android.com/sdk/index.html). It is a little different for each operating system.

### Windows

1. Launch the .exe file you just downloaded.
2. Follow the setup wizard to install Android Studio and any necessary SDK tools.

> On some Windows systems, the launcher script does not find where Java is installed. If you encounter this problem, you need to set an environment variable indicating the correct location.
> Select Start menu > Computer > System Properties > Advanced System Properties. Then open Advanced tab > Environment Variables and add a new system variable JAVA_HOME that points to your JDK folder, for example C:\Program Files\Java\jdk1.7.0_21.

### Mac OS

1. Launch the .dmg file you just downloaded.
2. Drag and drop Android Studio into the Applications folder.
3. Open Android Studio and follow the setup wizard to install any necessary SDK tools.

> Depending on your security settings, when you attempt to open Android Studio, you might see a warning that says the package is damaged and should be moved to the trash. If this happens, go to System Preferences > Security & Privacy and under Allow applications downloaded from, select Anywhere. Then open Android Studio again.

### Linux

1. Unpack the downloaded ZIP file into an appropriate location for your applications.
2. To launch Android Studio, navigate to the `android-studio/bin/` directory in a terminal and execute `studio.sh`.
3. If the SDK is not already installed, follow the setup wizard to install the SDK and any necessary SDK tools.

> Note: You may also need to install the ia32-libs, lib32ncurses5-dev, and lib32stdc++6 packages. These packages are required to support 32-bit apps on a 64-bit machine.

## Adding Android SDK's

1. Open Android Studio
2. Open SDK Manager ![Open SDK Manager](./instructions/img1.png?raw=true "Open SDK Manager")
3. Make sure to update the latest of:
  * Android SDK Tools
  * Android SDK Platform-tools
  * Android SDK Build-tools ![Update the Tools](./instructions/img2.png?raw=true "Update the Tools")
4. Install the latest SDK platform, right now it is 5.1 (API 22) ![Install SDK platform](./instructions/img3.png?raw=true "Install SDK platform")
5. Install Support Library & Support Repository ![Install Support Libs](./instructions/img4.png?raw=true "Install Support Libs")
6. Install Google Repository ![Install Google](./instructions/img5.png?raw=true "Install Google")
7. Install Google Play services ![Install Play Services](./instructions/img6.png?raw=true "Install Play Services")
8. Restart SDK Manager to verify that all installations worked
