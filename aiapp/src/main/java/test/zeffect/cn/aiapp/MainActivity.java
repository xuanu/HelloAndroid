package test.zeffect.cn.aiapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startService(new Intent(this, AiService.class));
        Toast.makeText(this, "成功开启服务", Toast.LENGTH_SHORT).show();
    }
}
