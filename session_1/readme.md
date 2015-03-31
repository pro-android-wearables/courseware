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

## Make Your First App

Apps for Wear devices have to be bundled to an application running on a mobile device. Therefore, when you create the project using Android Studio to run your first application, you will need to create an app for both mobile and wear. Follow these steps:

1. Open Android Studio
2. Create a new project ![Create a new project](./instructions/app1_1.png?raw=true "Create a new project")
3. Choose a folder where to store it and give it a name ![Give it a name](./instructions/app1_2.png?raw=true "Give it a name")
4. Make a bundle of mobile and wear app ![Make a bundle](./instructions/app1_3.png?raw=true "Make a bundle")
5. Choose the activity type for the mobile app ![Take an activity](./instructions/app1_4.png?raw=true "Take an activity")
6. Customize the activity ... it would be good if you gave it a differentiating name ![Customize the activity](./instructions/app1_5.png?raw=true "Customize the activity")
7. Choose the activity for the wear app ![Take an activity](./instructions/app1_6.png?raw=true "Take an activity")
8. Customize the activity, as usual, get a nice name. Note the two layouts, one for the squared devices, one for the round ones ![Customize the wear activity](./instructions/app1_7.png?raw=true "Customize the wear activity")
9. The wizard will create things for you ![Oh, a progress bar](./instructions/app1_8.png?raw=true "Yeah a progress bar")
10. You've got a project! By default Android studio will open a couple of windows and layouts for you ![Your first project](./instructions/app1_9.png?raw=true "Your first project")
11. Choose the right layout file for the wear device, you will have got a Nexus phone by default ![Choose the right layout file](./instructions/app1_10.png?raw=true "Choose the right layout file")
12. You will have three layout files, the stub and two more. You will work on those other two. You will have to make equivalent layouts on each device. We recommend you name the items properly to save you time when coding. E.g. a button with equivalent functions on both layouts, should be called the same ![Check the different layouts](./instructions/app1_11.png?raw=true "Check the diffferent layouts")
13. Drag&Drop a button in there ![Drag and Drop a button](./instructions/app1_12.png?raw=true "Drag and Drop a button")
14. Place it wherever you feel like ![Place it on the layout](./instructions/app1_13.png?raw=true "Place it on the layout")
15. Make sure you name it properly, you will need to refer to it from the code. Note that we are using a technique of first designing the layout of our program and adding code to make the different elements in the UI work. This makes quite some sense when programming for Wear because you just want to make small interactions with it, notifications, etc. UI is the most important thing on this device, you want users to "glance" the UI and get instantly what is going on. The watch is not a device where to spend time writing on a minimal keyboard! ![Give it an instance name](./instructions/app1_14.png?raw=true "Give it an instance name")
16. Do the same in the other layout and make sure to give the same name to this button ![Repeat for the round layout](./instructions/app1_15.png?raw=true "Repeat for the round layout")
17. Go to the activity file for the wear device, you will now create a listener to wait for the button to be clicked and then execute an action. Note: an activity in Android is a screen in a program. An application can typically have many activities ![Check the code for the activity](./instructions/app1_16.png?raw=true "Check the code for the activity")
18. Within the onCreate method, that is called when the application starts, you will declare the need for the program to add a listener for the button you added earlier on the UI. You will notice that you lack some of the libraries needed because the code will be highlighted in red. A small pop-up dialog will inform that pressing Alt+Enter Android Studio will add that library for you automatically ... so why not doing it? ![Add a call to the listener](./instructions/app1_17.png?raw=true "Add a call to the listener")
19. Declare an object for the button ![Declare the object holding the button](./instructions/app1_18.png?raw=true "Declare the object holding the button")
20. Add the method with the listener. If it is your first time programming for Android, or in Java, a listener is a method in a class that will be called when a certain event happens. It is the equivalent to a callback function in C or Javascript ![Declare your listener](./instructions/app1_19.png?raw=true "Declare your listener")
21. Add the library for View ![Add the View lib](./instructions/app1_20.png?raw=true "Add the View lib")
22. It is time to compile your app and run it on an emulator. Select the right target for the compilation ![Select compilation target](./instructions/app1_21.png?raw=true "Select compilation target")
23. Press the Play button ![Press the play button](./instructions/app1_22.png?raw=true "Press the play button")
24. At first, you will have no emulator ready to run a wear device, you need to declare a new one ![Declare a wear image](./instructions/app1_23.png?raw=true "Declare a wear image")
25. First you get the view of your available emulators, you might have just one phone, which is the default one. I had a SmartWatch I used for testing code for the book ![List of emulator images](./instructions/app1_24.png?raw=true "List of emulator images")
26. Create a new one with the "Create Virtual Device" button ![Create a new image](./instructions/app1_25.png?raw=true "Create a new image")
27. Select the API version and the architecture ![Select an architecture](./instructions/app1_26.png?raw=true "Select an architecture")
28. Give it a meaningful name (if you get an error about not have selected a skin, click on the "Show Advanced Settings", browse down, and select the skin for the right device in the corresponding drop-down list) ![Give ia name](./instructions/app1_27.png?raw=true "Give it a name")
29. The list, after refreshing it, will now reflect your new virtual device ![Refresh the list](./instructions/app1_28.png?raw=true "Refresh the list")
30. Close the list and choose your new virtual device from the drop-down list ![Choose the virtual machine](./instructions/app1_29.png?raw=true "Choose the virtual machine")
31. The machine will take a little to boot the first time, remember that you don't need to close it every time you make changes in the code, because this is an emulator, in other words, it replicates the exact same functionality as the real device. It lacks some small features like voice input, but otherwise it is the same. Whenever you recompile your application, it will erase the previous one from the emulator and load the new one instead ![Load the emulator](./instructions/app1_30.png?raw=true "Load the emulator")
32. The thing will boot ![Booting](./instructions/app1_31.png?raw=true "Booting")
33. The emulator will load with the app, you will also see some information on the console related to the app ![The app is on](./instructions/app1_32.png?raw=true "The app is on")
34. If you click on the button, it will just work ![Clicked](./instructions/app1_32.png?raw=true "Clicked")
