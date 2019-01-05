package gcxy.zeffect.cn.helloandroid;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import gcxy.zeffect.cn.helloandroid.utils.MetaUtils;

public class MainActivity extends Activity implements View.OnClickListener {
    private TextView showMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showMsg = (TextView) findViewById(R.id.showMsg);
        findViewById(R.id.getMetaValue).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.getMetaValue) {
            show("custom_key_1 value =  " + MetaUtils.getMetaValue(this, "test_custom_key_1", ""));
            show("custom_key_2 value =  " + MetaUtils.getMetaValue(this, "test_custom_key_2", 0));
            show("custom_key_3 value =   " + MetaUtils.getMetaValue(this, "test_custom_key_3", ""));
            show("custom_key_4 value =   " + MetaUtils.getMetaValue(this, "test_custom_key_4", ""));
        }
    }

    private void show(String msg) {
        if (TextUtils.isEmpty(msg)) return;
        showMsg.setText(msg + "\n" + showMsg.getText());
    }
}
