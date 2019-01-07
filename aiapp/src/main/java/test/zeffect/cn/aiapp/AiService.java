package test.zeffect.cn.aiapp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import test.zeffect.cn.baseaidl.IAICallback;
import test.zeffect.cn.baseaidl.IAiInterface;

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
