# session_4

## Is there slides for this session?

Nope, this time we had a live coding session, so there were no slides. However we have notes.

## Notes for the session


* some things have got deprecated from the API
* today we are going to look into activities
* also looking at sensors
* Andreas had problems and accidentally erased the project he had prepared for today's session
* create a new project
* the app we are making here is mimicing some of the functionalities you can find at the FIT App from Android
* there are a lot of new things in the UI components, but somehow the wear items aren't listed by default. Therefore you need to go into the Custom-view objects, where you will see the ones having the "wearable" name in the package
* there are some extra functionalities embedded in there that Andreas guides everyone through like the different layouts, overlay views, etc
* we work with the CircledImageView
* Andreas recommends removing the <view> tag and just keep the android.support.wearable.view.CircledImageView
* change the layout width and height to "match_parent"
* in order to access some attributes, we will need to add the namespace of wear in the RelativeLayout tag: xmlns:wear="http://schemas.android.com/apk/res-auto"
* this will allow us adding some of the wear tags back at our new item like: wear:circle_color="#f00", wear:circle_border_color="#0f0", wear:circle_border_width="5dp", wear:circle_radius="80dp" ... in this way you can change the attributes of the object
* the idea is to use the border as a progress bar in our goal
* we are going to set up a goal in our system so that we can achieve a certain goal
* continue by making the color of the circle transparent: wear:circle_color="@android:color/transparent", this is a trick for you to get transparent colors instead of having to set an alpha layer
* then you will create a textview that we will use to show the amount of steps done during the day
* it is important to add IDs to the items where so that we can access them from the code: @+id/detector and @+id/counter
* you need to replicate the layout for the other layout, so that you can focus on the activity from now on
* we go back to the WearActivity to work with the code, in the book we wrote about the InsetActivity that could be used without the stub and would add the big red closing button so that you could use if from within your applications, but it has been removed from the API
* therefore we will just extend Activity in the code
* after adding the objects needed for the counter and for the progress bar, we can make a quick test to see how things work graphically
* the trick here is that the circledImageView allows adding the 
* using sensors is really simple in Android, unfortunately the step sensor is not available in all devices
* first we will print out the list of sensors available by using a method called "systemService" and in there the "sensorService" once in there we can get the list of the sensors with "sensorManager.getSensorList(Sensor.TYPE_ALL)"
* all of those will be printed out to the logcat, in order to get a good overview about what kind of sensors there are 
* we will be accessing the step counter and the step detector sensor
* the step detector is a simple sensor that is firing every time there is a detection of a step, it is not very good for anything that high pace. This is a boolean
* if you want to accumulate the actual amount of steps, you should use the step counter instead. This is a device that counts the amount of steps since the device started
* we can then use this very simple sensor information and use it as part of our code
* we will use a listener that we will register for each sensor we want to use within the device, we will need to have as parameters the listener, the sensor and the refresh rate or interval. If we want the interval to be as quick as possible, we will make this last value "0" which means "get it as soon as possible"
* you need to declare two objects to address the two sensors and before declaring the listeners, you will need to add the handle for each sensor
* there might be more sensors than a single one of the same type
* just to make a test on the detector, we add a log to see how the sensor is fired
* add an onDestroy method to unregister the listeners when the application closes
* in order to keep track of the daily step counting, we will add a goal as a constant (GOAL) at the beginning of the program and a counter (steps) to increment the amount of steps. This second variable has to be defined as a float, because the set-progress is taking a value between 0 and 1
* this first half example is built around the detector and not the counter, in order to look at the progress of the step counter
* the second half of the example is going to look at the step counter in order to show the value on the screen
* the listener will look at an event that will be returning an array with different values depending on the type of sensor you have. The first position in the array will give you the step counter
* then we just add this to the view et-voila ... our FIT clone is done!
* Note: in your production apps you might not want to have update values for your listeners at "0", as that might be draining batteries
