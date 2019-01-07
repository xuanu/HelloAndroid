package gcxy.zeffect.cn.helloandroid;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import gcxy.zeffect.cn.helloandroid.utils.MetaUtils;
import test.zeffect.cn.baseaidl.IAICallback;
import test.zeffect.cn.baseaidl.IAiInterface;

public class MainActivity extends Activity implements View.OnClickListener {
    private TextView showMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showMsg = (TextView) findViewById(R.id.showMsg);
        findViewById(R.id.getMetaValue).setOnClickListener(this);
        findViewById(R.id.getAidlValue).setOnClickListener(this);
        connectAidl();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(serviceConnection);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.getMetaValue) {
            show("custom_key_1 value =  " + MetaUtils.getMetaValue(this, "test_custom_key_1", ""));
            show("custom_key_2 value =  " + MetaUtils.getMetaValue(this, "test_custom_key_2", 0));
            show("custom_key_3 value =   " + MetaUtils.getMetaValue(this, "test_custom_key_3", ""));
            show("custom_key_4 value =   " + MetaUtils.getMetaValue(this, "test_custom_key_4", ""));
        } else if (v.getId() == R.id.getAidlValue) {
            if (aiInterface != null) {
                try {
                    show(aiInterface.getToken());
                    aiInterface.init(callback);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void show(String msg) {
        if (TextUtils.isEmpty(msg)) return;
        showMsg.setText(msg + "\n" + showMsg.getText());
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
}
