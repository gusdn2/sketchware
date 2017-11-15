## Sketchware Projects
### Material Design for Sketchware
You can apply default material design (or other theme) by create a class named 'Activity'.
for example:
``` java
package com.lhw.sketchware.test;
//...
public class MainActivity extends Activity // replace this class
{
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContetView(R.layout.main);
    initialize();
    initializeLogic();
  }
  
  private void initiize(){
  }
  
  private void initializeLogic(){
    //user custom code
  }
}
class Activity extends android.app.Activity
{
  protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    //set theme
    if(Build.VERSION.SDK_INT < 21) setTheme(android.R.style.Theme_Holo_Light);
    else setTheme(android.R.style.Theme_Material_Light_DarkActionBar);
    //auto inserted brackets
  }
  //...
}
```
You should paste this only in ONE ACTIVITY and paste in last moreblock(if moreblock not exist, last event)
But if you paste this in some activity, in that activity, you can't use block: Toast, getDip, getDisplayWidthPixels, getDisplayHeightPixels, getRandom.
Only in ONE ACTIVITY.
