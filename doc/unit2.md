# 项目结构   
1. HelloAndroid是项目   
2. app是项目模块   
> 一个程序可以拆分成不同的模块，这也是未来的趋势。   
> 不同的模块组合，还能创造新的应用。   
> 模块创建时可以是app还library.  
> 模块引用后面再讲    
下面的文件先不讲    

## App模块结构  
1. lib项目引用JAR包  
2. src 项目代码     
> androidTest和test是做单元测试时用的，先不管       
3  main 目录     
> assets  存放不参与编译的文件，可以在程序运行时拿到文件内容。     
> java    文件是项目代码     
> jniLibs so库，     
> res     资源文件     

## res文件夹介绍  
1. drawable     存放图片之类的文件  
2. layout       存放界面的布局   
> layout-port,layout-land   
3. mimap        存放图标，一般也只用于存放应用桌面图标  
4. values       存放数据
> colors,strings,styles等等

## dpi简单介绍   
mdpi 160  
hdpi 240  
xhdpi 320  
xxhdpi 480   
xxxhdpi 640   
基准dpi是160即mdpi    
在 mdpi下1px=1dp;   
在 hdpi下1px=1*240/160px=1.5px;    

//获取当前设备的dpi   
//adb获取   
```
adb shell wm size   
adb shell wm density   
```   
//代码获取    
```
 DisplayMetrics dm = getResources().getDisplayMetrics();   
 "当前设备DPI为：" + dm.densityDpi + "dpi" + "，为mdpi的" + dm.density + "倍"   
```    


