# AIDL的使用   
> AIDL的好处，跨进程通信。    
> 两个APP，一个当服务端，一个当客户端     

####  项目结构     
app:     充当客户端    
aiapp:   充当服务端    
baseaidl:公用AIDL    

###     
1. 在**baseaidl的模块名**上右键，新键AIDL文件    
> 异步需要回调，所以我加了个异步回调的AIDL,不需要回调的，可以不用加。    

IAiInterface.aidl.....     
```
package test.zeffect.cn.baseaidl;
import test.zeffect.cn.baseaidl.IAICallback;
interface IAiInterface {
    void init(IAICallback callback);
    String getToken();
}
```   
IAiCallback.aidl.....
```
package test.zeffect.cn.baseaidl;
interface IAICallback {
    void initStatus(boolean isSuccess);
}
```    

2.  在AiApp中新增Service用来充当服务端    
> aiapp需要引用baseaidl   

AiService.java    
```
public class AiService extends Service {
    private static final IAiInterface.Stub mbinder = new IAiInterface.Stub() {
        @Override
        public void init(IAICallback callback) throws RemoteException {
            if (callback != null) {
                callback.initStatus(true);
            }
        }

        @Override
        public String getToken() throws RemoteException {
            return "token：" + System.currentTimeMillis();
        }
    };
    @Override
    public IBinder onBind(Intent intent) {
        return mbinder;
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }
}
```    
AndroidManifest.xml中注册刚才写的服务   
> 需要注意添加~android:exported="true"~     
```
<service
            android:name=".AiService"
            **android:exported="true"**></service>
```    
在程序启动时，开启服务：~startService(new Intent(this,AiService.class))~   

3. 客户端操作    
> 可以在连接前开启服务器     
```
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        connectAidl();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(serviceConnection);
    }
  private void connectAidl() {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("test.zeffect.cn.aiapp", "test.zeffect.cn.aiapp.AiService"));
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    private IAiInterface aiInterface;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            aiInterface = IAiInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            aiInterface = null;
        }
    };

    private IAICallback.Stub callback = new IAICallback.Stub() {
        @Override
        public void initStatus(boolean isSuccess) throws RemoteException {
            show("初始化：" + isSuccess);
        }
    };
```    
