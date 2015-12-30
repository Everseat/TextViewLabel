# TextViewLabel
A simple TextView that is styled like a label

# Install
To add this library to your project, you must add the JitPack repo to your root build.gradle file...

```groovy
allprojects {
 repositories {
    ...
    maven { url "https://jitpack.io" }
 }
}
```

Then include this line in your dependencies block...

```
compile 'com.github.everseat:TextViewLabel:0.1'
```

# Usage
Simply include in your layout like so...

```xml
<com.everseat.textviewlabel.TextViewLabel
  android:layout_width="wrap_content"
  android:layout_height="wrap_content"
  android:text="Hello World!"
  android:textSize="14sp"
  android:textColor="#FFF"
  app:labelColor="@color/red"/>
```
